package com.likelion.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.likelion.ecommerce.entities.User;
import com.likelion.ecommerce.repository.UserRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
	
	@Autowired
	private  UserRepo userRepo;

    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    public User getUserById(Integer id){
        Optional<User> optionalEmployee = userRepo.findById(id);
        if(optionalEmployee.isPresent()){
            return optionalEmployee.get();
        }
        return null;
    }

    public User saveUser (User user){
        User savedUser = userRepo.save(user);

        return savedUser;
    }

    public User updateUser (User user) {
        User updatedUser = userRepo.save(user);
        return updatedUser;
    }

    public void deleteUserById (Integer id) {
        userRepo.deleteById(id);
    }
    
    public User findFirstByAccountId(Integer accountId) {
    	return userRepo.findFirstByAccountId(accountId);
    }
}
