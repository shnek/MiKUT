package OperatorResolver.operatorresolver.billingcontainers;

import java.math.BigDecimal;

public class ServiceDetails {
	
	private int quantity;
	private BigDecimal value;
	
	public ServiceDetails() {
		this.quantity = 0;
		this.value = new BigDecimal(0);
	}
	
	public void addServiceDetails(int quantity, BigDecimal value){
		this.quantity += quantity;
		this.value = this.value.add(value);
	}

	public int getQuantity() {
		return quantity;
	}

	public BigDecimal getValue() {
		return value;
	}
	
}
