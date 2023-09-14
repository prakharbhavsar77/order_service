package com.example.Project_Service.service;

import com.example.Project_Service.model.OrderRequest;
import com.example.Project_Service.model.OrderResponse;

public interface OrderService {

	long placeOrder(OrderRequest orderRequest);

	OrderResponse getOrderDetails(long orderid);

}
