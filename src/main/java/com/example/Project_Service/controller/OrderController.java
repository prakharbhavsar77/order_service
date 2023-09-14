package com.example.Project_Service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Project_Service.model.OrderRequest;
import com.example.Project_Service.model.OrderResponse;
import com.example.Project_Service.service.OrderService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/order")
@Log4j2
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping("/placeOrder")
	public ResponseEntity<Long> placeOrder(@RequestBody OrderRequest orderRequest)
	{
		long orderId = orderService.placeOrder(orderRequest);
		log.info("Order Id:{}",orderId);
			
		return new ResponseEntity<>(orderId,HttpStatus.OK); 
	}

	@GetMapping("/{orderid}")
	public ResponseEntity<OrderResponse> getOrderDetails(@PathVariable long orderid)
	{
		OrderResponse orderResponse = orderService.getOrderDetails(orderid);
		return new ResponseEntity<>(orderResponse,HttpStatus.OK); 
	
	}
}
