package OperatorResolver.operatorresolver.billingcontainers;

import OperatorResolver.operatorresolver.Operator;

import java.util.HashMap;
import java.util.Map;

public class Billing {

	private ServiceDetails internet;
	private Map<Operator, Services> operatorToServices;

	public Billing() {
		this.internet = new ServiceDetails();
		this.operatorToServices = new HashMap<>();
	}


	public void addConnection(Operator operator, int durationInSeconds) {
		getServices(operator).getConnections().addServiceDetails(durationInSeconds);
	}

	public void addSms(Operator operator) {
		getServices(operator).getSms().addServiceDetails(1);
	}

	public void addMms(Operator operator) {
		getServices(operator).getMms().addServiceDetails(1);
	}

	public void addInternet(int quantity) {
		internet.addServiceDetails(quantity);
	}

	private Services getServices(Operator operator){
		Services services;

		if (!operatorToServices.containsKey(operator)) {
			services = new Services();
			operatorToServices.put(operator, services);
			return services;
		}

		return operatorToServices.get(operator);
	}

	public ServiceDetails getInternet() {
		return internet;
	}

	public Map<Operator, Services> getOperatorToServices() {
		return operatorToServices;
	}

}
