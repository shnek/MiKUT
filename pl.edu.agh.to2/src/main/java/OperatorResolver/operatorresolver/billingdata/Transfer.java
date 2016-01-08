package OperatorResolver.operatorresolver.billingdata;

import OperatorResolver.operatorresolver.OperatorResolver;

import java.math.BigDecimal;

public class Transfer {
	int dataSize;

	public Transfer(){

	}

	public Transfer(int dataSize) {
		this.dataSize = dataSize;
	}

	public int getDataSize() {
		return dataSize;
	}

	public void setDataSize(int dataSize) {
		this.dataSize = dataSize;
	}

}
