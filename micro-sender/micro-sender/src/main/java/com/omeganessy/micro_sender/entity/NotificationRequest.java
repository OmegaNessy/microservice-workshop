package com.omeganessy.micro_sender.entity;

import lombok.Data;

@Data
public class NotificationRequest {
    private String user;
    private String message;
}