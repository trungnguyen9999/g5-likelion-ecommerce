package com.likelion.ecommerce.service;

import com.likelion.ecommerce.entities.WishList;
import com.likelion.ecommerce.repository.WishListRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class WishListService {

    private final WishListRepo repo;

    public List<WishList> findAll() {
        return repo.findAll();
    }

    public List<WishList> findAllByAccountId(Integer accountId) {
        return repo.findAllByAccountIdOrderByWishlistId(accountId);
    }

    public WishList findFirstByAccountIdAndProductId(Integer accountId, Integer productId) {
        return repo.findFirstByAccountIdAndProductIdOrderByWishlistId(accountId, productId);
    }

    public WishList findById(Integer id) {
        return repo.findById(id).orElse(null);
    }

    public WishList saveWishList(WishList wishlist) {
        WishList savedWishList = repo.save(wishlist);
        return savedWishList;
    }

    public WishList updateWishList(WishList wishlist) {
        WishList updatedWishList = repo.save(wishlist);
        return updatedWishList;
    }

    public void deleteWishListById(Integer id) {
        repo.deleteById(id);
    }

    public void deleteWishListByAccountIdAndProductId(Integer accountId, Integer productId) {
        repo.deleteByAccountIdAndProductId(accountId, productId);

    }
}
