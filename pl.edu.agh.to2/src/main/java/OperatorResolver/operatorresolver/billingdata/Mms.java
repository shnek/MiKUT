package OperatorResolver.operatorresolver.billingdata;

import OperatorResolver.operatorresolver.OperatorResolver;

import java.math.BigDecimal;

public class Mms {

	String number;

	public Mms(){

	}

	public Mms(String number) {
		this.number = number;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}


	// Why did you commented this? Without this tests fails...
}
