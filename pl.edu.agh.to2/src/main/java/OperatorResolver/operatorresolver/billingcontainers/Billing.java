package OperatorResolver.operatorresolver.billingcontainers;

import OperatorResolver.operatorresolver.Operator;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Billing {

	private ServiceDetails internet;
	private Map<Operator, Services> operatorToServices;

	public Billing() {
		this.internet = new ServiceDetails();
		this.operatorToServices = new HashMap<Operator, Services>();
	}


	public void addConnection(Operator operator, int durationInSeconds, BigDecimal value) {
		getServices(operator).getConnections().addServiceDetails(durationInSeconds, value);
	}

	public void addSms(Operator operator, BigDecimal value) {
		getServices(operator).getSms().addServiceDetails(1, value);
	}

	public void addMms(Operator operator, BigDecimal value) {
		getServices(operator).getMms().addServiceDetails(1, value);
	}

	public void addInternet(int quantity, BigDecimal value) {
		internet.addServiceDetails(quantity, value);
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
