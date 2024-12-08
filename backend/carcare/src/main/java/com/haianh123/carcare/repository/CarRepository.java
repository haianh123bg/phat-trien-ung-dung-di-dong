package com.haianh123.carcare.repository;

import com.haianh123.carcare.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Integer> {
}