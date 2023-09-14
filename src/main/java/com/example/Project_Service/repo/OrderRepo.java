package com.example.Project_Service.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Project_Service.entity.Orders;

public interface OrderRepo extends JpaRepository<Orders, Long> {

}
