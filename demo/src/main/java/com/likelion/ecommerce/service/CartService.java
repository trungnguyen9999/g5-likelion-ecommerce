package com.likelion.ecommerce.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	public Cart save(Cart cart) 
	{	
		Cart cartExists = repo.findByAccountIdAndProductId(cart.getAccountId(), cart.getProductId());
		if(Objects.nonNull(cartExists)) {
			int quantity = cartExists.getQuantity() + cart.getQuantity();
			cart.setQuantity(quantity);
			cart.setCartId(cartExists.getCartId());
		}
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
