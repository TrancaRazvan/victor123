package com.app.projectVictor.Repositories;

import com.app.projectVictor.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    //Partea asta este pentru login

    User findByEmail(String email);
}
