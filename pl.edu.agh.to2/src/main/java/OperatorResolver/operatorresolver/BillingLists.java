package OperatorResolver.operatorresolver;

import java.util.List;

public class BillingLists {
	// unmodifiy list

	// D T O
	private List<Dial> dials;
	private List<Sms> sms;
	private List<Mms> mms;
	private List<Transfer> transfers;

	public List<Dial> getDials() {
		return dials;
	}

	public List<Sms> getSms() {
		return sms;
	}

	public List<Mms> getMms() {
		return mms;
	}

	public List<Transfer> getTransfers() {
		return transfers;
	}

}
