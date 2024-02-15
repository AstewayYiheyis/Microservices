package com.asteway.notificationservice.models;

import com.asteway.notificationservice.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderEvent {
    private String email;
    private Status type;
}
