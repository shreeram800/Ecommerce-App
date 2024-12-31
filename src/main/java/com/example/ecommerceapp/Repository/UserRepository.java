package com.example.ecommerceapp.Repository;

import com.example.ecommerceapp.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {


    User getUserByEmail(String email);

    Boolean existsUserByEmail(String email);

    User findByEmail(String email);
}
