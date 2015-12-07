package OperatorResolver.operatorresolver;

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

		Services services;
		if (operatorToServices.containsKey(operator)) {

			services = operatorToServices.get(operator);
			services.getConnections().addServiceDetails(durationInSeconds, value);

		} else {

			services = new Services();
			services.getConnections().addServiceDetails(durationInSeconds, value);
			operatorToServices.put(operator, services);

		}

	}

	public void addSms(Operator operator, BigDecimal value) {
		Services services;
		if (operatorToServices.containsKey(operator)) {

			services = operatorToServices.get(operator);
			services.getSms().addServiceDetails(1, value);

		} else {

			services = new Services();
			services.getSms().addServiceDetails(1, value);
			operatorToServices.put(operator, services);

		}
	}

	public void addMms(Operator operator, BigDecimal value) {
		Services services;
		if (operatorToServices.containsKey(operator)) {

			services = operatorToServices.get(operator);
			services.getMms().addServiceDetails(1, value);

		} else {

			services = new Services();
			services.getMms().addServiceDetails(1, value);
			operatorToServices.put(operator, services);

		}
	}

	public void addInternet(int quantity, BigDecimal value) {
		internet.addServiceDetails(quantity, value);
	}

	public ServiceDetails getInternet() {
		return internet;
	}

	public Map<Operator, Services> getOperatorToServices() {
		return operatorToServices;
	}

}
