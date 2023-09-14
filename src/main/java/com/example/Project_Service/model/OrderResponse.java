package com.example.Project_Service.model;

import java.time.Instant;

import javax.persistence.Entity;

import com.example.Project_Service.entity.Orders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {



	private long orderid;
	private Instant orderDate;
	private String orderstatus;
	private long amount;
	private ProductDetails productDetails;
	private PaymentDetails paymentDetails;
	
	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static  class ProductDetails {
		
		
		private long productId;
		
		private String product_name;
		
		private long price;
		
		private long quantity;
		
	}
		
		@Data
		@AllArgsConstructor
		@NoArgsConstructor
		@Builder
		public static class PaymentDetails {

			private long paymentid;
			
			private PaymentMode paymentMode;
			private String paymentStatus;
			private Instant paymentDate;
			
		

	}
}
