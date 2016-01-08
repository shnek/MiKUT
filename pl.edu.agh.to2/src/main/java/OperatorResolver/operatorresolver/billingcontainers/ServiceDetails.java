package OperatorResolver.operatorresolver.billingcontainers;


public class ServiceDetails {
	
	private int quantity;

	
	public ServiceDetails() {
		this.quantity = 0;

	}
	
	public void addServiceDetails(int quantity){
		this.quantity += quantity;
	}

	public int getQuantity() {
		return quantity;
	}

	
}
