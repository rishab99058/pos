package com.vermau2k01053.pos_System.service.impl;

import com.vermau2k01053.pos_System.configuration.JwtProvider;
import com.vermau2k01053.pos_System.jpaModel.User;
import com.vermau2k01053.pos_System.model.UserModel;
import com.vermau2k01053.pos_System.repository.UserRepository;
import com.vermau2k01053.pos_System.request.UserRequest;
import com.vermau2k01053.pos_System.response.UserResponse;
import com.vermau2k01053.pos_System.service.api.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @Override
    public UserResponse getUserByJwtToken(String jwtToken) {
       UserResponse response = new UserResponse();
       try{
          String email = jwtProvider.getEmailFromToken(jwtToken);
          User user= userRepository.findByEmailAndDeletedAtIsNull(email);

          if(user==null){
              throw new Exception("User not found");
          }

          UserModel userModel = new UserModel();
          userModel.setId(user.getId());
          userModel.setEmail(user.getEmail());
          userModel.setName(user.getFullName());
          userModel.setRole(user.getRole());
          userModel.setPhoneNumber(user.getPhoneNumber());

          response.setUser(userModel);
          response.setSuccess(Boolean.TRUE);
          response.setStatusCode(HttpStatus.ACCEPTED);
       }catch(Exception exception)
       {
        logger.error("UserService getUserByJwtToken exception",exception);
        response.setSuccess(Boolean.FALSE);
        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
       }
       return response;
    }

    @Override
    public UserResponse getCurrentUser() {
        UserResponse response = new UserResponse();
        try{
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            User user= userRepository.findByEmailAndDeletedAtIsNull(email);
            if(user==null){
                throw new Exception("User not found");
            }

            UserModel userModel = new UserModel();
            userModel.setId(user.getId());
            userModel.setEmail(user.getEmail());
            userModel.setName(user.getFullName());
            userModel.setRole(user.getRole());
            userModel.setPhoneNumber(user.getPhoneNumber());

            response.setUser(userModel);
            response.setSuccess(Boolean.TRUE);
            response.setStatusCode(HttpStatus.ACCEPTED);

        }catch(Exception exception){
            logger.error("UserService getCurrentUser() exception",exception);
            response.setSuccess(Boolean.FALSE);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    @Override
    public UserResponse getUserByEmail(UserRequest userRequest) {
        UserResponse response = new UserResponse();
        try{
            String email  =  userRequest.getEmail();
            User user = userRepository.findByEmailAndDeletedAtIsNull(email);
            if(user==null){
                throw new Exception("User not found");
            }

            UserModel userModel = new UserModel();
            userModel.setId(user.getId());
            userModel.setEmail(user.getEmail());
            userModel.setName(user.getFullName());
            userModel.setRole(user.getRole());
            userModel.setPhoneNumber(user.getPhoneNumber());

            response.setUser(userModel);
            response.setSuccess(Boolean.TRUE);
            response.setStatusCode(HttpStatus.ACCEPTED);

        }catch(Exception exception){
            logger.error("UserService getUserByEmail() exception",exception);
            response.setSuccess(Boolean.FALSE);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @Override
    public UserResponse getUserById(UserRequest userRequest) {
        UserResponse response = new UserResponse();
        try{
            String id = userRequest.getId();
            User user  = userRepository.findByIdAndDeletedAtNull(id);
            if(user==null){
                throw new Exception("User not found");
            }
            UserModel userModel = new UserModel();
            userModel.setId(user.getId());
            userModel.setEmail(user.getEmail());
            userModel.setName(user.getFullName());
            userModel.setRole(user.getRole());
            userModel.setPhoneNumber(user.getPhoneNumber());

            response.setUser(userModel);
            response.setSuccess(Boolean.TRUE);
            response.setStatusCode(HttpStatus.ACCEPTED);
        }catch(Exception exception){
            logger.error("UserService getUserById() exception",exception);
            response.setSuccess(Boolean.FALSE);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return response;
    }

    @Override
    public UserResponse getUserListing() {
        UserResponse response = new UserResponse();
        try{
            List<User> userList = userRepository.findAllByDeletedAtIsNull();
            if(!userList.isEmpty()){
                List<UserModel> userModelList = new ArrayList<>();
                userList.stream().forEach(u->{
                    UserModel userModel = new UserModel();
                    userModel.setId(u.getId());
                    userModel.setEmail(u.getEmail());
                    userModel.setName(u.getFullName());
                    userModel.setRole(u.getRole());
                    userModel.setPhoneNumber(u.getPhoneNumber());
                    userModelList.add(userModel);
                });

                response.setUserList(userModelList);
            }
            response.setSuccess(Boolean.TRUE);
            response.setStatusCode(HttpStatus.ACCEPTED);
        }catch(Exception exception){
            logger.error("UserService getUserListing() exception",exception);
            response.setSuccess(Boolean.FALSE);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
}
