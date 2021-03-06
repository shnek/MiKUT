package OperatorResolver.operatorresolver.billingdata;

import OperatorResolver.operatorresolver.OperatorResolver;

import java.math.BigDecimal;

public class Dial {

	private String number;
	private int length;
	private BigDecimal value;

	public Dial(){

	}

	public Dial(String number, int length) {
		this.number = number;
		this.length = length;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

}
