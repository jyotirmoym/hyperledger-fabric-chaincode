package com.explorer.commodity.ledger.model;

import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;

import lombok.Data;

@DataType
@Data
public class Commodity {
	
	@Property
	private String name;
	
	@Property
	private Long count;

}
