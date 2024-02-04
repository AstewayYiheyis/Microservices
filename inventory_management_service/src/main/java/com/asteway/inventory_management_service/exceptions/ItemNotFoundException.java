package com.asteway.inventory_management_service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ItemNotFoundException extends Throwable {
    public ItemNotFoundException(String s) {
        super(s);
    }
}
