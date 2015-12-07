package OperatorResolver.operatorresolver;

import java.math.BigDecimal;

public class Mms {
	String number;
	BigDecimal value;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}


	// Why did you commented this? Without this tests fails...
	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}
}
