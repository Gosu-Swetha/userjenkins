package com.cg.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.user.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Integer>{

	Admin findByUserName(String email);


}
