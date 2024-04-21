package com.dailychecklist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dailychecklist.entity.LoginDetails;

public interface LoginDetailsRepository extends JpaRepository<LoginDetails, String>{

}
