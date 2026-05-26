package com.example.autodeploy_tracker.service;
import com.example.autodeploy_tracker.exception.ResourceNotFoundException;
import java.util.Optional;

import com.example.autodeploy_tracker.model.Deployment;
import com.example.autodeploy_tracker.model.DeploymentStatus;
import com.example.autodeploy_tracker.repository.DeploymentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeploymentService {

    private final DeploymentRepository repo;

    public DeploymentService(DeploymentRepository repo) {
        this.repo = repo;
    }

    public Deployment createDeployment(Deployment deployment) {
        deployment.setStatus(DeploymentStatus.PENDING);
        deployment.setTriggeredAt(LocalDateTime.now());
        return repo.save(deployment);
    }

    public List<Deployment> getAllDeployments() {
        return repo.findAll();
    }

    public List<Deployment> getDeploymentsByStatus(DeploymentStatus status) {
        return repo.findByStatus(status);
    }
    public Deployment getDeploymentById(Long id) {
        return repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Deployment not found with id: " + id));
    }
    public Deployment updateStatus(Long id,
                                   DeploymentStatus newStatus) {

        Deployment deployment = getDeploymentById(id);

        DeploymentStatus current = deployment.getStatus();

        boolean valid =
                (current == DeploymentStatus.PENDING
                        && newStatus == DeploymentStatus.RUNNING)

                        ||

                        (current == DeploymentStatus.RUNNING
                                && (newStatus == DeploymentStatus.SUCCESS
                                || newStatus == DeploymentStatus.FAILED));

        if (!valid) {
            throw new IllegalStateException(
                    "Invalid status transition");
        }

        deployment.setStatus(newStatus);

        if (newStatus == DeploymentStatus.SUCCESS
                || newStatus == DeploymentStatus.FAILED) {

            deployment.setCompletedAt(LocalDateTime.now());
        }

        return repo.save(deployment);
    }
    public void deleteDeployment(Long id) {

        Deployment deployment = getDeploymentById(id);

        repo.delete(deployment);
    }
    public long countDeploymentsByStatus(
            DeploymentStatus status) {

        return repo.countByStatus(status);
    }

}