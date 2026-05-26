package com.example.autodeploy_tracker.service;

import com.example.autodeploy_tracker.exception.ResourceNotFoundException;
import com.example.autodeploy_tracker.model.Deployment;
import com.example.autodeploy_tracker.model.DeploymentStatus;
import com.example.autodeploy_tracker.repository.DeploymentRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class DeploymentServiceTest {

    @Mock
    private DeploymentRepository repo;

    @InjectMocks
    private DeploymentService service;

    @Test
    void createDeployment_setsStatusToPending() {

        Deployment deployment =
                new Deployment();

        when(repo.save(any(Deployment.class)))
                .thenAnswer(i -> i.getArgument(0));

        Deployment result =
                service.createDeployment(deployment);

        assertEquals(
                DeploymentStatus.PENDING,
                result.getStatus()
        );
    }

    @Test
    void getDeploymentById_throwsWhenNotFound() {

        when(repo.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> service.getDeploymentById(1L)
        );
    }

    @Test
    void invalidStatusTransition_throwsException() {

        Deployment deployment =
                new Deployment();

        deployment.setStatus(
                DeploymentStatus.SUCCESS
        );

        when(repo.findById(1L))
                .thenReturn(
                        Optional.of(deployment)
                );

        assertThrows(
                IllegalStateException.class,

                () -> service.updateStatus(
                        1L,
                        DeploymentStatus.PENDING
                )
        );
    }
}