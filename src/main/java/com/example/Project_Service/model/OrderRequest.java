package com.example.Project_Service.model;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequest {

	
	private long productId;
	private long totalAmount ;
	private long quntity;
	private PaymentMode paymentMode;
	
}
