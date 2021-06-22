package com.explorer.commodity.ledger.model;

import java.time.Instant;

import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;

import lombok.Data;

@DataType
@Data
public class CommitedCommodity {
	
	@Property
	private Instant transactionTime;

	@Property
	private Long data;
	
	@Property
	private String transactionId;

}
