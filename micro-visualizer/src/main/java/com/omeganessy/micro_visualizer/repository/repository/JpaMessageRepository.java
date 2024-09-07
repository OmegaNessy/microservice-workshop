package com.omeganessy.micro_visualizer.repository.repository;

import com.omeganessy.micro_visualizer.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMessageRepository extends JpaRepository<Message, Long> {
}
