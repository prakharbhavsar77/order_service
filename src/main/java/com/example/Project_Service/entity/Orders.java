package com.example.Project_Service.entity;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Orders {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO )
	private long id;
	private long productId;
	private long quantity;
	private Instant orderDate;
	private String orderstatus;
	private long amount;	

}
