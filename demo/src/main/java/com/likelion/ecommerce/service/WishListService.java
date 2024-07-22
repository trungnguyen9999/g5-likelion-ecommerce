package com.likelion.ecommerce.service;

import com.likelion.ecommerce.entities.Account;
import com.likelion.ecommerce.entities.WishList;
import com.likelion.ecommerce.repository.AccountRepository;
import com.likelion.ecommerce.repository.WishListRepo;
import com.likelion.ecommerce.response.NumberProductsResponse;
import com.likelion.ecommerce.response.SumQuantityCartResponse;
import com.likelion.ecommerce.security.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
public class WishListService {

    private final WishListRepo repo;

    private final AccountRepository accountRepository;

    public List<WishList> findAllByAccountId(Integer accountId) {
        return repo.findAllByAccountIdOrderByWishlistId(accountId);
    }

    public WishList findById(Integer id) {
        return repo.findById(id).orElse(null);
    }

    public WishList saveWishList(WishList wishlist) {
        return repo.save(wishlist);
    }

    public WishList updateWishList(WishList wishlist) {
        return repo.save(wishlist);
    }

    public void deleteWishListById(Integer id) {
        repo.deleteById(id);
    }

    public void deleteWishListByAccountIdAndProductId(Integer accountId, Integer productId) {
        repo.deleteByAccountIdAndProductId(accountId, productId);
    }

    public Integer getNumberWishlistByAccountId() {
        String email = JwtUtils.extractEmail();
        Account account = accountRepository.findByEmail(email).orElseThrow(() -> new NoSuchElementException("Account not found"));
        NumberProductsResponse response = repo.getNumberProductsByAccountId(account.getAccountId())
                .orElseThrow(() -> new NoSuchElementException("Wishlist by accoundID: " + account.getAccountId() + " not found!"));
        return response.getNumberProducts();
    }
}
