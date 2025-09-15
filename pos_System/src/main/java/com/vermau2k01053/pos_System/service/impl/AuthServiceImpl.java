package com.vermau2k01053.pos_System.service.impl;

import com.vermau2k01053.pos_System.configuration.JwtProvider;
import com.vermau2k01053.pos_System.domain.UserRole;
import com.vermau2k01053.pos_System.jpaModel.User;
import com.vermau2k01053.pos_System.repository.UserRepository;
import com.vermau2k01053.pos_System.request.UserRequest;
import com.vermau2k01053.pos_System.response.AuthResponse;
import com.vermau2k01053.pos_System.service.api.AuthService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final CustomUserImplementation customUserImplementation;
    private final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Override
    @Transactional
    public AuthResponse registerUser(UserRequest userRequest) {
        AuthResponse authResponse = new AuthResponse();
        try{
            User user  = userRepository.findByEmailAndDeletedAtIsNull(userRequest.getEmail());
            if (user != null) {
                throw new Exception("User already exists with the given Id");
            }
            if(userRequest.getRole().equals(UserRole.ROLE_ADMIN))
                throw new Exception("Admin Role is not allowed");

            User newUser = new User();
//            newUser.setId(UUID.randomUUID().toString());
            newUser.setEmail(userRequest.getEmail());
            newUser.setPassword(passwordEncoder.encode(userRequest.getPassword()));
            newUser.setRole(userRequest.getRole());
            newUser.setFullName(userRequest.getName());
            newUser.setCreatedDate(LocalDateTime.now());
            newUser.setUpdatedDate(LocalDateTime.now());
//            newUser = userRepository.save(newUser);
            newUser.setPhoneNumber(userRequest.getPhoneNumber());
            newUser.setLastLoggedInAt(LocalDateTime.now());
            userRepository.save(newUser);

            Authentication authentication = new UsernamePasswordAuthenticationToken(newUser.getEmail(), newUser.getPassword());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtProvider.generateToken(authentication);
            log.info("New User created with id {}", newUser.getId());
            authResponse.setSuccess(Boolean.TRUE);
            authResponse.setMessage("User has been registered successfully");
            authResponse.setJwt(token);
            authResponse.setStatusCode(HttpStatus.ACCEPTED);

        }catch (Exception exception){
           log.error("Error registering User", exception);
           authResponse.setSuccess(Boolean.FALSE);
           authResponse.setMessage(exception.getMessage());
           authResponse.setStatusCode(HttpStatus.BAD_REQUEST);
        }

        return authResponse;
    }

    @Override
    public AuthResponse loginUser(UserRequest userRequest) {
        AuthResponse authResponse = new AuthResponse();
        try{
           String email = userRequest.getEmail();
           String password = userRequest.getPassword();

           Authentication authentication = authenticate(email, password);
           SecurityContextHolder.getContext().setAuthentication(authentication);
           Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
           String role  = authorities.iterator().next().getAuthority();
           String token = jwtProvider.generateToken(authentication);

           User user = userRepository.findByEmailAndDeletedAtIsNull(email);
           if (user == null) {
               throw new Exception("User doesn't exist");
           }

           user.setLastLoggedInAt(LocalDateTime.now());
           userRepository.save(user);

           authResponse.setSuccess(Boolean.TRUE);
           authResponse.setMessage("Logged In successfully");
           authResponse.setJwt(token);
           authResponse.setStatusCode(HttpStatus.ACCEPTED);
           authResponse.setSuccess(Boolean.TRUE);
        }
        catch (Exception exception){
            log.error("Error login User", exception);
            authResponse.setSuccess(Boolean.FALSE);
            authResponse.setMessage(exception.getMessage());
            authResponse.setStatusCode(HttpStatus.BAD_REQUEST);
        }
        return authResponse;
    }

    private Authentication authenticate(String email, String password) throws Exception {
        UserDetails userDetails = customUserImplementation.loadUserByUsername(email);
        if(userDetails == null) {
            throw new Exception("Email Id doesn't exists");
        }
        if(!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new Exception("Password doesn't match");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
    }
}
