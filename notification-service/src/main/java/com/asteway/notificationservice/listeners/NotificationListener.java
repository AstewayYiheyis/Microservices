package com.asteway.notificationservice.listeners;

import com.asteway.notificationservice.models.OrderEvent;
import com.asteway.notificationservice.services.NotificationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationListener {
    @Autowired
    NotificationService notificationService;

    Logger logger = LogManager.getLogger(NotificationListener.class);

    @KafkaListener(topics = "orders-topic", groupId = "order-group")
    public void listen(OrderEvent orderEvent) {
        // Process the received message
        logger.info("Order Event Received! \n", orderEvent);

        notificationService.processOrderEvent(orderEvent);
    }
}
