package com.asteway.notificationservice.services;

import com.asteway.notificationservice.entities.Notification;
import com.asteway.notificationservice.enums.Status;
import com.asteway.notificationservice.models.OrderEvent;
import com.asteway.notificationservice.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NotificationService {
    EmailService emailService;

    NotificationRepository notificationRepository;

    public NotificationService(EmailService emailService, NotificationRepository notificationRepository) {
        this.emailService = emailService;
        this.notificationRepository = notificationRepository;
    }

    public void processOrderEvent(OrderEvent orderEvent) {
        if (orderEvent != null) {
            Status eventType = orderEvent.getType();

            if (eventType != null) {
                // Send notification
                sendEmailNotification(orderEvent.getEmail(), eventType.toString());
            }
        } else {
            throw new NullPointerException("Order Event is null!");
        }
    }

    private void sendEmailNotification(String email, String eventType) {
        // Compose the email message based on the event type
        String message = "Your order has been " + eventType;

        // Send email notification
        String msg = emailService.sendEmail(email, "Order Notification", message);

        notificationRepository.save(Notification.builder().msg(msg).date(new Date()).build());
    }

    public List<Notification> getNotifications(){
        return notificationRepository.findAll();
    }
}

