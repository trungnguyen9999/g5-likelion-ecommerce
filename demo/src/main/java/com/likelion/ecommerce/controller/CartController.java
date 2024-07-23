package com.likelion.ecommerce.controller;

import com.likelion.ecommerce.dto.CartDto;
import com.likelion.ecommerce.request.CartRequest;
import com.likelion.ecommerce.response.ResponseStandard;
import com.likelion.ecommerce.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@CrossOrigin
@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
@Validated
public class CartController {

	private final CartService cartService;
	
	@GetMapping("/get-by-account")
    public ResponseEntity<ResponseStandard> getCartList()
    {
		List<CartDto> data = cartService.findAllProductInCartByAccountId();
		ResponseStandard rp = new ResponseStandard();
		rp.setMessage(Objects.isNull(data) ? "Data not found" : "success");
		rp.setData(data);
        return ResponseEntity.ok().body(rp);
    }
	
	@PostMapping("/create")
    public ResponseEntity<ResponseStandard> saveCart(@RequestBody CartRequest cart)
    {
		ResponseStandard rp = new ResponseStandard();
		rp.setMessage("Insert successful!");
		rp.setData(cartService.save(cart));
        return ResponseEntity.ok().body(rp);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseStandard> updateCart(@RequestBody List<CartRequest> listCart)
    {
    	ResponseStandard rp = new ResponseStandard();
		rp.setMessage("Update successful!");
		rp.setData(cartService.update(listCart));
        return ResponseEntity.ok().body(rp);
    }
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteProductInCart(@PathVariable Integer id)
	{
		cartService.deteteById(id);
		return ResponseEntity.ok().body("Delete successful!");
	}

	@GetMapping("/get-total-items")
	public ResponseEntity<ResponseStandard> getTotalCartItems()
	{
		Integer totalItems =  cartService.getSumQuantityByAccountId();
		ResponseStandard response = new ResponseStandard();
		response.setMessage(Objects.isNull(totalItems) ? "Data not found" : "success");
		response.setData(totalItems);
		return ResponseEntity.ok().body(response);
	}
	@GetMapping("/get-total-price")
	public ResponseEntity<ResponseStandard> getTotalPriceInCart()
	{
		Integer totalItems =  cartService.getTotalPriceInCart();
		ResponseStandard response = new ResponseStandard();
		response.setMessage(Objects.isNull(totalItems) ? "Data not found" : "success");
		response.setData(totalItems);
		return ResponseEntity.ok().body(response);
	}

}
