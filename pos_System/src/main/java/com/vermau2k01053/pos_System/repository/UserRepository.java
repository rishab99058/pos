package com.vermau2k01053.pos_System.repository;

import com.vermau2k01053.pos_System.jpaModel.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    User findByEmailAndDeletedAtIsNull(String email);
    User findByIdAndDeletedAtNull(String id);
    List<User> findAllByDeletedAtIsNull();
}
