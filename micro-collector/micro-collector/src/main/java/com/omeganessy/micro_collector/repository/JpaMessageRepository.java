package com.omeganessy.micro_collector.repository;

import com.omeganessy.micro_collector.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaMessageRepository extends JpaRepository<Message, UUID> {
}
