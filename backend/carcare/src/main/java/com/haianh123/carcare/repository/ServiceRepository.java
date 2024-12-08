package com.haianh123.carcare.repository;

import com.haianh123.carcare.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Integer> {
}