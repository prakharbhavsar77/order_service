package com.example.Project_Service.externalClient.resposne;

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
public class PaymentResponse {

	private long paymentid;
	private long orderId;
	private PaymentMode paymentMode;
	private String status;
	private Instant paymentDate;
	private long amount;
}
