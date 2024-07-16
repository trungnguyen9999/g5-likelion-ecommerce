package com.likelion.ecommerce.controller;

import com.likelion.ecommerce.entities.Account;
import com.likelion.ecommerce.entities.ERole;
import com.likelion.ecommerce.entities.Role;
import com.likelion.ecommerce.entities.User;
import com.likelion.ecommerce.repository.AccountRepository;
import com.likelion.ecommerce.repository.RoleRepository;
import com.likelion.ecommerce.repository.UserRepository;
import com.likelion.ecommerce.request.LoginRequest;
import com.likelion.ecommerce.request.SignupRequest;
import com.likelion.ecommerce.response.MessageResponse;
import com.likelion.ecommerce.response.UserInfoResponse;
import com.likelion.ecommerce.security.jwt.JwtUtils;
import com.likelion.ecommerce.security.services.UserDetailsImpl;
import com.likelion.ecommerce.service.CategoryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  private final RoleRepository roleRepository;

  private final PasswordEncoder encoder;

  private final JwtUtils jwtUtils;
  
  private final AccountRepository accountRepository;
  
  private final UserRepository userRepository;
  
  private static final Logger log = LoggerFactory.getLogger(AuthController.class);

  @Value("${bezkoder.app.jwtCookieName}")
  private String keyName;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager
            .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

    ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

    List<String> roles = userDetails.getAuthorities().stream()
            .map(item -> item.getAuthority())
            .collect(Collectors.toList());

    String token = extractToken(jwtCookie.toString(), keyName);
    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.getValue())
            .body(new UserInfoResponse(userDetails.getId(),
                    userDetails.getUsername(),
                    roles, token));
  }

  private static String extractToken(String cookieString, String key) {
    String prefix = key + "=";
    int startIndex = cookieString.indexOf(prefix) + prefix.length();
    int endIndex = cookieString.indexOf(";", startIndex);
    if (endIndex == -1) {
      endIndex = cookieString.length();
    }
    return cookieString.substring(startIndex, endIndex);
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
	 
	  log.info(signUpRequest.getUsername());
	 
    if (accountRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already in use!"));
    }

    // Create new user's account
    Account account = new Account(signUpRequest.getUsername(),
                         encoder.encode(signUpRequest.getPassword()), signUpRequest.getType(), signUpRequest.getStatus(), signUpRequest.getCreatedAt());

    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
          if (role.equals("admin")) {
              Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                      .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
              roles.add(adminRole);
          } else {
              Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                      .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
              roles.add(userRole);
          }
      });
    }

    account.setRoles(roles);
    accountRepository.save(account);

    Optional<Account> exitAccount = accountRepository.findByUsername(signUpRequest.getUsername());

    if (exitAccount.isPresent()) {
      User user = new User(signUpRequest.getPhoneNumber(), signUpRequest.getFullName(), signUpRequest.getEmail(), signUpRequest.getBirthdate(),
              signUpRequest.getAddressLine1(), signUpRequest.getAddressLine2(), signUpRequest.getApartment(), signUpRequest.getSuburb(),
              signUpRequest.getCity(), signUpRequest.getRegion(), signUpRequest.getAvatar());
      user.setAccountId(exitAccount.get().getAccountId());
      userRepository.save(user);

      return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    } else {
      return ResponseEntity.ok(new MessageResponse("Error: An error occurred!"));
    }
  }

  @PostMapping("/signout")
  public ResponseEntity<?> logoutUser() {
    ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
            .body(new MessageResponse("You've been signed out!"));
  }
}
