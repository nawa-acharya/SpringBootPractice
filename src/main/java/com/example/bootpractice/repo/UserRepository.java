package com.example.bootpractice.repo;

import com.example.bootpractice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * @author Nawa
 * on 9/21/20.
 * (c)Marathon Computer Systems
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
