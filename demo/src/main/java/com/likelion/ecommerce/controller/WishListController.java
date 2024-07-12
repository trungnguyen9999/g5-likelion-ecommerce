package com.likelion.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.likelion.ecommerce.entities.WishList;
import com.likelion.ecommerce.service.WishListService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/wishlist")
@RequiredArgsConstructor
@Validated
public class WishListController {
	

	/**
	 * author: ntnguyen
	 */
	
	@Autowired
	private WishListService wishlistService;

    @GetMapping("/{id}")
    public ResponseEntity<WishList> getWishlistById(@PathVariable Integer id)
    {
    	WishList cate = wishlistService.findById(id);
        return ResponseEntity.ok().body(cate);
    }

    @PostMapping("/create")
    public ResponseEntity<WishList> saveWishList(@RequestBody WishList category)
    {
        return ResponseEntity.ok().body(wishlistService.saveWishList(category));
    }

    @PutMapping("/update")
    public ResponseEntity<WishList> updateWishList(@RequestBody WishList category)
    {
        return ResponseEntity.ok().body(wishlistService.updateWishList(category));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWishListById(@PathVariable Integer id)
    {
    	wishlistService.deleteWishListById(id);
        return ResponseEntity.ok().body("Deleted wishlist successfully");
    }
    
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteWishListByAccountIdAndProductId(@RequestParam Integer accountId, @RequestParam Integer productId)
    {
    	wishlistService.deleteWishListByAccountIdAndProductId(accountId, productId);
        return ResponseEntity.ok().body("Deleted wishlist successfully");
    }
}
