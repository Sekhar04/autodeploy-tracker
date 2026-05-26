package com.example.autodeploy_tracker.repository;

import com.example.autodeploy_tracker.model.Deployment;
import com.example.autodeploy_tracker.model.DeploymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeploymentRepository
        extends JpaRepository<Deployment, Long> {


    List<Deployment> findByStatus(DeploymentStatus status);
    long countByStatus(DeploymentStatus status);
}