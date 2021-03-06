package OperatorResolver.operatorresolver.billingdata;

import OperatorResolver.operatorresolver.OperatorResolver;
import OperatorResolver.operatorresolver.billingdata.Dial;
import OperatorResolver.operatorresolver.billingdata.Mms;
import OperatorResolver.operatorresolver.billingdata.Sms;
import OperatorResolver.operatorresolver.billingdata.Transfer;

import java.util.Collections;
import java.util.List;

public class BillingLists {


	// D T O
	private List<Dial> dials;
	private List<Sms> sms;
	private List<Mms> mms;
	private List<Transfer> transfers;

	public BillingLists(List<Dial> dials, List<Sms> sms, List<Mms> mms, List<Transfer> transfers) {
		this.dials = Collections.unmodifiableList(dials);
		this.sms = Collections.unmodifiableList(sms);
		this.mms = Collections.unmodifiableList(mms);
		this.transfers = Collections.unmodifiableList(transfers);
	}

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
