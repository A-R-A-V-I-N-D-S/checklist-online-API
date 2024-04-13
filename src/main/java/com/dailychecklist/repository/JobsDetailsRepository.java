package com.dailychecklist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dailychecklist.entity.JobsDetails;

public interface JobsDetailsRepository extends JpaRepository<JobsDetails, Long> {

}
