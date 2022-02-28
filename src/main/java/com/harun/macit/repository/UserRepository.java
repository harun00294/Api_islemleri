package com.harun.macit.repository;

import com.harun.macit.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long>{


}
