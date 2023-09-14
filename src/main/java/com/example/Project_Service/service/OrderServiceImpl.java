package com.example.Project_Service.service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.Project_Service.entity.Orders;
import com.example.Project_Service.exception.CustomException;
import com.example.Project_Service.externalClient.Payment_Service;
import com.example.Project_Service.externalClient.Product_Service;
import com.example.Project_Service.externalClient.request.PaymentRequest;
import com.example.Project_Service.externalClient.resposne.PaymentResponse;
import com.example.Project_Service.model.OrderRequest;
import com.example.Project_Service.model.OrderResponse;
import com.example.Project_Service.model.Product_Response;
import com.example.Project_Service.repo.OrderRepo;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {
	
	
	@Autowired
	private OrderRepo orderRepo;
	
	@Autowired
	private Payment_Service payment_Service;
	
	@Autowired
	private Product_Service product_Service;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public long placeOrder(OrderRequest orderRequest) {
		
//order_entity -> save the data with status created 
	//product service Block products Reduce quantitty	
	//payment service -> payment-> sucess-> complte	
		
		log.info("Placing order  Request:{}",orderRequest);
		
		product_Service.reduceQuantity(orderRequest.getProductId(), orderRequest.getQuntity());
		
		/*
		 
		 ModelMapper mapper = new ModelMapper();
		 
		 mapper.map(orderRequest,Order.class);
		 
		 */
		
		
		
		Orders order = Orders.builder()
			.amount(orderRequest.getTotalAmount())
			.orderstatus("CREATED")
			.productId(orderRequest.getProductId())
			.orderDate(Instant.now())
			.quantity(orderRequest.getQuntity())
			.build();
		
		order = orderRepo.save(order);
		
		log.info("Calling Payment service to complete payment");
		
		PaymentRequest paymentRequest = PaymentRequest.builder()
				.orderId(order.getId())
				.paymentMode(orderRequest.getPaymentMode())
				.amount(orderRequest.getTotalAmount())
				.build();
		
		String orderStatus = null;
		try {
			payment_Service.doPayment(paymentRequest);
			log.info("Payment done succesfully,Order placed sucessfully");		
			orderStatus="PLACED";
			
			
		}catch (Exception e) {

			log.error("Error occured in payment. Changinng order status to Payment_failed");
			orderStatus="PAYMENT FAILED";
		
		}	
		
		
		order.setOrderstatus(orderStatus);
		orderRepo.save(order);
		;
		
		log.info( "Order placed successfully with ordedr id :{}", order.getId());
		
		return order.getId();
		
	}

	@Override
	public OrderResponse getOrderDetails(long orderid) {
		log.info( "Get order details with ordedr id :{}", orderid);
		
		Orders orders = orderRepo.findById(orderid)
				.orElseThrow(()-> new CustomException("Order not found with order id{} :"+orderid,
					"NOT_FOUND",404	));
		
		
		Product_Response product_Response =
				restTemplate.getForObject("http://PRODUCT-SERVICE/product/" +
						
						orders.getProductId(),Product_Response.class);
		
		
		log.info("Getting informtion from payment service");
		
		PaymentResponse paymentResponse = 
				restTemplate.getForObject("http://PAYMENT-SERVICE/payment/order/" + orders.getId(), 
						
					PaymentResponse.class);
		
		 OrderResponse.ProductDetails productDetails =
				 OrderResponse.ProductDetails.builder()
				.product_name(product_Response.getProduct_name())
				.productId(product_Response.getProductId())
				.build();
				
		 
		OrderResponse.PaymentDetails paymentDetails =
				OrderResponse.PaymentDetails
				.builder()
				.paymentid(paymentResponse.getPaymentid())
				.paymentStatus(paymentResponse.getStatus())
				.paymentDate(paymentResponse.getPaymentDate())
				.paymentMode(paymentResponse.getPaymentMode())
				.build();
				
		
		OrderResponse orderResponse = OrderResponse.builder()
				.orderid(orders.getId())
				.orderstatus(orders.getOrderstatus())
				.amount(orders.getAmount())
				.orderDate(orders.getOrderDate())
				.productDetails(productDetails)
				.build();
		
		return orderResponse;
	}

}
