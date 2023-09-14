package com.example.Project_Service.externalClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.Project_Service.externalClient.request.PaymentRequest;



@FeignClient(name = "PAYMENT-SERVICE/payment")
public interface Payment_Service {

	
	@PostMapping
	public ResponseEntity<Void> doPayment(@RequestBody PaymentRequest paymentRequest);
}