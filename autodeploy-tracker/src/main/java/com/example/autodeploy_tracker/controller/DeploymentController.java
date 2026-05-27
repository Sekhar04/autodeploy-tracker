package com.example.autodeploy_tracker.controller;

import com.example.autodeploy_tracker.model.Deployment;
import com.example.autodeploy_tracker.model.DeploymentStatus;

import com.example.autodeploy_tracker.service.DeploymentService;

import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deployments")
public class DeploymentController {

    private final DeploymentService deploymentService;

    public DeploymentController(
            DeploymentService deploymentService) {

        this.deploymentService = deploymentService;
    }

    @PreAuthorize(
            "hasAnyRole('ADMIN', 'DEVOPS')"
    )
    @PostMapping
    public Deployment createDeployment(
            @RequestBody Deployment deployment) {

        return deploymentService
                .createDeployment(deployment);
    }

    @PreAuthorize(
            "hasAnyRole('ADMIN', 'DEVOPS', 'VIEWER')"
    )
    @GetMapping
    public List<Deployment> getAllDeployments() {

        return deploymentService
                .getAllDeployments();
    }

    @PreAuthorize(
            "hasAnyRole('ADMIN', 'DEVOPS', 'VIEWER')"
    )
    @GetMapping("/{id}")
    public Deployment getDeploymentById(
            @PathVariable Long id) {

        return deploymentService
                .getDeploymentById(id);
    }

    @PreAuthorize(
            "hasAnyRole('ADMIN', 'DEVOPS')"
    )
    @PutMapping("/{id}/status")
    public Deployment updateStatus(

            @PathVariable Long id,

            @RequestBody
            DeploymentStatusRequest request) {

        return deploymentService
                .updateStatus(
                        id,
                        request.getStatus()
                );
    }

    public static class DeploymentStatusRequest {

        private DeploymentStatus status;

        public DeploymentStatus getStatus() {
            return status;
        }

        public void setStatus(
                DeploymentStatus status) {

            this.status = status;
        }
    }
    @PreAuthorize(
            "hasRole('ADMIN')"
    )
    @DeleteMapping("/{id}")
    public void deleteDeployment(
            @PathVariable Long id) {

        deploymentService
                .deleteDeployment(id);
    }

}