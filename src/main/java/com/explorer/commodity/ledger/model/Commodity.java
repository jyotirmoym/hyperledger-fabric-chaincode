package com.explorer.commodity.ledger.model;

import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

@DataType
@Data
public class Commodity {
	
	@Property
	private String name;
	
	@Property
	private Long count;
	
	public String toJSONString() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(this);
    }

    public static Commodity fromJSONString(String json) throws JsonMappingException, JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
    	Commodity deal = mapper.readValue(json, Commodity.class);
        return deal;
    }

}
