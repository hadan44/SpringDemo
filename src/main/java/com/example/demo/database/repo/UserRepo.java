package com.example.demo.database.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.database.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>, UserRepoCustom{
}
