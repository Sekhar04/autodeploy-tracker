package com.example.autodeploy_tracker.controller;

import com.example.autodeploy_tracker.model.Deployment;
import com.example.autodeploy_tracker.model.DeploymentStatus;
import com.example.autodeploy_tracker.service.DeploymentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/deployments")

public class DeploymentController {


    private final DeploymentService service;

    public DeploymentController(DeploymentService service) {
        this.service = service;
    }


    @PostMapping
    public Deployment createDeployment(
            @RequestBody Deployment deployment) {

        return service.createDeployment(deployment);
    }

    @GetMapping
    public List<Deployment> getAllDeployments(

            @RequestParam(required = false)
            DeploymentStatus status) {

        if (status != null) {
            return service.getDeploymentsByStatus(status);
        }

        return service.getAllDeployments();
    }

    @GetMapping("/{id}")
    public Deployment getDeploymentById(
            @PathVariable Long id) {

        return service.getDeploymentById(id);
    }

    @PutMapping("/{id}/status")
    public Deployment updateStatus(

            @PathVariable Long id,

            @RequestBody Map<String, String> body) {

        DeploymentStatus status =
                DeploymentStatus.valueOf(
                        body.get("status"));

        return service.updateStatus(id, status);
    }
    @DeleteMapping("/{id}")
    public String deleteDeployment(@PathVariable Long id) {

        service.deleteDeployment(id);

        return "Deployment deleted successfully";
    }
    @GetMapping("/count/{status}")
    public long countDeploymentsByStatus(
            @PathVariable DeploymentStatus status) {

        return service.countDeploymentsByStatus(status);
    }

}