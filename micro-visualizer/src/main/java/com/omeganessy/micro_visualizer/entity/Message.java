package com.omeganessy.micro_visualizer.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="message")
public class Message {
    @Id
    @GeneratedValue
    private Long id;

    @Setter
    @Getter
    @Column(name = "message", nullable = false)
    private String message;

}
