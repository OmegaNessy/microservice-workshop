package com.omeganessy.micro_collector.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name="message")
public class Message {
    @Id
    private UUID id;

    @Column(name = "message", nullable = false)
    private String message;

}
