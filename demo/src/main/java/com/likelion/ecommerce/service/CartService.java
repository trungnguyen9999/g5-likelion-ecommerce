package com.likelion.ecommerce.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

import com.likelion.ecommerce.entities.Account;
import com.likelion.ecommerce.entities.User;
import com.likelion.ecommerce.repository.AccountRepository;
import com.likelion.ecommerce.repository.UserRepository;
import com.likelion.ecommerce.request.CartRequest;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.likelion.ecommerce.dto.CartDto;
import com.likelion.ecommerce.dto.ProductDetailDto;
import com.likelion.ecommerce.dto.ProductSimpleDto;
import com.likelion.ecommerce.entities.Cart;
import com.likelion.ecommerce.repository.CartRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartService {
	
	private final CartRepo repo;

	private final ModelMapper modelMapper;

	private final ProductService productService;

	private final AccountRepository accountRepository;

	public List<CartDto> findAllProductInCartByAccountId(Integer accountId)
	{
		List<Cart> listCart = repo.findAllByAccountId(accountId);
		if(Objects.nonNull(listCart) && !listCart.isEmpty()) {
			List<CartDto> listCartDto = listCart.stream().map(i -> {				
				CartDto dto = new CartDto();
				dto.setCartId(i.getCartId());
				dto.setAccountId(i.getAccountId());
				ProductDetailDto product = productService.getProductById(i.getProductId());
				if(Objects.nonNull(product)) {
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
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = null;
		if (authentication != null) {
			Object principal = authentication.getPrincipal();
			if (principal instanceof UserDetails) {
				email = ((UserDetails) principal).getUsername();
			} else {
				email = principal.toString();
			}
		}
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

}
