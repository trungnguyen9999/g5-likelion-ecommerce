package com.likelion.ecommerce.service;

import com.likelion.ecommerce.dto.CartDto;
import com.likelion.ecommerce.dto.CategoryDto;
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

import java.util.ArrayList;
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

	public List<CartDto> findAllProductInCartByAccountId() {
		String email = JwtUtils.extractEmail();
		Account account = accountRepository.findByEmail(email).orElseThrow(() -> new NoSuchElementException("Account not found"));
		Integer accountId = account.getAccountId();
		List<Cart> listCart = repo.findAllByAccountIdOrderByCartIdDesc(accountId);
		if (Objects.nonNull(listCart) && !listCart.isEmpty()) {
			List<CartDto> listCartDto = listCart.stream().map(i -> {
				CartDto dto = new CartDto();
				dto.setCartId(i.getCartId());
				dto.setAccountId(i.getAccountId());
				ProductDetailDto product = productService.getProductById(i.getProductId());
				if (Objects.nonNull(product)) {
					ProductSimpleDto productSimple = modelMapper.map(product, ProductSimpleDto.class);
					productSimple.setQuantity(i.getQuantity());
					productSimple.setSubTotal(i.getQuantity() * Integer.valueOf(product.getPrice() + ""));
					dto.setProduct(productSimple);
				}
				SumQuantityCartResponse response = repo.getSumQuantityByAccountId(account.getAccountId())
						.orElseThrow(() -> new NoSuchElementException("Cart by accoundID: " + account.getAccountId() + " not found!"));
				dto.setTotalItems(response.getSumQuantity());
				dto.setTotalPrice(repo.getTotalPriceInCartByAccountId(account.getAccountId()));
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

	public List<Cart> updateOld(List<CartRequest> listCart)
	{
		return listCart.stream().map(this::update).collect(Collectors.toList());
	}

	public List<Cart> update(List<CartDto> rq)
	{
		List<CartRequest> listCart = new ArrayList<>();
		for(CartDto dto : rq){
			CartRequest crq = new CartRequest();
			crq.setCartId(dto.getCartId());
			crq.setProductId(dto.getProduct().getProductId());
			crq.setQuantity(dto.getProduct().getQuantity());
			listCart.add(crq);
		}

		return listCart.stream().map(this::update).collect(Collectors.toList());
	}

	public Cart update(CartRequest rq)
	{
		String email = JwtUtils.extractEmail();
		Account account = accountRepository.findByEmail(email).orElseThrow(() -> new NoSuchElementException("Account not found"));
		Cart c = modelMapper.map(rq, Cart.class);
		c.setAccountId(account.getAccountId());
		return repo.save(c);
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

	public Integer getTotalPriceInCart() {
		String email = JwtUtils.extractEmail();
		Account account = accountRepository.findByEmail(email).orElseThrow(() -> new NoSuchElementException("Account not found"));
        return repo.getTotalPriceInCartByAccountId(account.getAccountId());
	}
}
