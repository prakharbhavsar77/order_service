package com.example.Project_Service.externalClient.request;

import java.time.Instant;


import com.example.Project_Service.model.PaymentMode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentRequest {

	
	private long orderId;
	private PaymentMode paymentMode;
	private String referenceNumber;
	
	private long amount;
	
}
