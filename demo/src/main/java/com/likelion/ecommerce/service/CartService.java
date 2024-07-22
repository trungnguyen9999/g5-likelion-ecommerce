package com.likelion.ecommerce.service;

import com.likelion.ecommerce.dto.CartDto;
import com.likelion.ecommerce.dto.ProductDetailDto;
import com.likelion.ecommerce.dto.ProductSimpleDto;
import com.likelion.ecommerce.entities.Account;
import com.likelion.ecommerce.entities.Cart;
import com.likelion.ecommerce.repository.AccountRepository;
import com.likelion.ecommerce.repository.CartRepo;
import com.likelion.ecommerce.request.CartRequest;
import com.likelion.ecommerce.response.SumQuantityCartResponse;
import com.likelion.ecommerce.security.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartService {
	
	private final CartRepo repo;

	private final ModelMapper modelMapper;

	private final ProductService productService;

	private final AccountRepository accountRepository;

	public List<CartDto> findAllProductInCartByAccountId(Integer accountId) {
		List<Cart> listCart = repo.findAllByAccountId(accountId);
		if (Objects.nonNull(listCart) && !listCart.isEmpty()) {
			List<CartDto> listCartDto = listCart.stream().map(i -> {
				CartDto dto = new CartDto();
				dto.setCartId(i.getCartId());
				dto.setAccountId(i.getAccountId());
				ProductDetailDto product = productService.getProductById(i.getProductId());
				if (Objects.nonNull(product)) {
					ProductSimpleDto productSimple = modelMapper.map(product, ProductSimpleDto.class);
					productSimple.setQuantity(i.getQuantity());
					dto.setProduct(productSimple);
				}

				return dto;
			}).collect(Collectors.toList());
			return listCartDto;
		}
		return null;
	}
	
	public Cart save(CartRequest cartRequest)
	{
		String email = JwtUtils.extractEmail();
		Account account = accountRepository.findByEmail(email).orElseThrow(() -> new NoSuchElementException("Account not found"));
		Cart cart = new Cart();
		Cart cartExists = repo.findByAccountIdAndProductId(account.getAccountId(), cartRequest.getProductId());
		if(Objects.nonNull(cartExists)) {
			int quantity = cartExists.getQuantity() + cartRequest.getQuantity();
			cart.setQuantity(quantity);
			cart.setCartId(cartExists.getCartId());
		} else {
			cart.setQuantity(cartRequest.getQuantity());
		}
		cart.setAccountId(account.getAccountId());
		cart.setProductId(cartRequest.getProductId());
		return repo.save(cart);
	}

	public List<Cart> update(List<Cart> listCart)
	{
		return listCart.stream().map(this::update).collect(Collectors.toList());
	}

	public Cart update(Cart cart)
	{
		return repo.save(cart);
	}
	
	public void deteteById(Integer id) 
	{
		repo.deleteById(id);
	}

	public Integer getSumQuantityByAccountId() {
		String email = JwtUtils.extractEmail();
		Account account = accountRepository.findByEmail(email).orElseThrow(() -> new NoSuchElementException("Account not found"));
		SumQuantityCartResponse response = repo.getSumQuantityByAccountId(account.getAccountId())
				.orElseThrow(() -> new NoSuchElementException("Cart by accoundID: " + account.getAccountId() + " not found!"));
		return response.getSumQuantity();
	}
}
