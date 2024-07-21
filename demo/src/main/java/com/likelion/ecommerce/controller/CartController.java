package com.likelion.ecommerce.controller;

import com.likelion.ecommerce.dto.CartDto;
import com.likelion.ecommerce.entities.Cart;
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
    public ResponseEntity<ResponseStandard> getCartList(@RequestParam Integer accountId) 
    {
		List<CartDto> data = cartService.findAllProductInCartByAccountId(accountId);
		ResponseStandard rp = new ResponseStandard();
		rp.setMessage(Objects.isNull(data) ? "Không tìm thấy dữ liệu" : "Thành công");
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
    public ResponseEntity<ResponseStandard> updateCart(@RequestBody List<Cart> listCart)
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
}
