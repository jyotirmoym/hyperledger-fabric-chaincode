/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.explorer.commodity.ledger;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.contract.ContractInterface;
import org.hyperledger.fabric.contract.annotation.Contract;
import org.hyperledger.fabric.contract.annotation.Default;
import org.hyperledger.fabric.contract.annotation.Transaction;
import org.hyperledger.fabric.shim.ledger.KeyModification;
import org.hyperledger.fabric.shim.ledger.QueryResultsIterator;

import com.explorer.commodity.ledger.model.CommitedCommodity;
import com.explorer.commodity.ledger.model.Commodity;
import com.fasterxml.jackson.core.JsonProcessingException;

@Default
@Contract
public class CommodityTransaction implements ContractInterface {

	@Transaction
	public void updateCount(Context context, Commodity commodity)
			throws JsonProcessingException, UnsupportedEncodingException {
		if (commodity.getName() == null || commodity.getCount() == null) {
			throw new RuntimeException("Unknown/Invalid commodity");
		}
		context.getStub().putState(commodity.getName(), BigInteger.valueOf(commodity.getCount()).toByteArray());
	}

	@Transaction
	public CommitedCommodity[] getCommodities(Context context, String commodityName) throws UnsupportedEncodingException {
		List<CommitedCommodity> commodities = new ArrayList<CommitedCommodity>();
		QueryResultsIterator<KeyModification> resultSet = context.getStub().getHistoryForKey(commodityName);
		resultSet.forEach(result -> {
			CommitedCommodity committedCommodity = new CommitedCommodity();
			committedCommodity.setData(new BigInteger(result.getValue()).longValue());
			committedCommodity.setTransactionTime(result.getTimestamp());
			committedCommodity.setTransactionId(result.getTxId());
			commodities.add(committedCommodity);
		});
		return commodities.toArray(new CommitedCommodity[commodities.size()]);
	}

}
