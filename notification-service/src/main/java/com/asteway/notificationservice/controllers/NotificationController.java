package com.asteway.notificationservice.controllers;

import com.asteway.notificationservice.entities.Notification;
import com.asteway.notificationservice.models.OrderEvent;
import com.asteway.notificationservice.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
    private NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping
    public ResponseEntity<List<Notification>> getNotification() {
        return ResponseEntity.ok(notificationService.getNotifications());
    }
}
