package OperatorResolver.operatorresolver;

public class Services {
	private ServiceDetails connections;
	private ServiceDetails sms;
	private ServiceDetails mms;
	
	public Services(){
		connections = new ServiceDetails();
		sms = new ServiceDetails();
		mms = new ServiceDetails();
	}
	
	public ServiceDetails getConnections() {
		return connections;
	}

	public ServiceDetails getSms() {
		return sms;
	}

	public ServiceDetails getMms() {
		return mms;
	}
		
}
