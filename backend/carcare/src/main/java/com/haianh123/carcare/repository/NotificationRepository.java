package com.haianh123.carcare.repository;

import com.haianh123.carcare.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
}