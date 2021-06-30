package com.nagarro.hrmanagement.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.hrmanagement.entities.User;

public interface UserRepo extends JpaRepository<User , Integer>{

}
