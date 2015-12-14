package OperatorResolver.operatorresolver;


import OperatorResolver.veryficator.*;

public class OperatorResolverImp implements OperatorResolver {

	private Billing billing;
	private Verifiers verifiers;

	public OperatorResolverImp(BillingLists billingList) {
		this.billing = new Billing();
		this.verifiers = new Verifiers();

		this.verifiers.add(new CachedNumberVerifier());
		this.verifiers.add(new MainNumberVerifier());
		this.verifiers.add(new OtherNumberVerifier(new Prefixes()));

		init(billingList);
	}

	private void init(BillingLists billingList) {

		for (Dial dial : billingList.getDials()) {
			Operator operator = verifiers.verify(dial.getNumber());
			billing.addConnection(operator, dial.getLenght(), dial.getValue());
		}

		for (Sms sms : billingList.getSms()) {
			Operator operator = verifiers.verify(sms.getNumber());
			billing.addSms(operator, sms.getValue());
		}

		for (Mms mms : billingList.getMms()) {
			Operator operator = verifiers.verify(mms.getNumber());
			billing.addMms(operator, mms.getValue());
		}

		for (Transfer transfer : billingList.getTransfers()) {
			billing.addInternet(transfer.getDataSize(), transfer.getValue());
		}

	}

	@Override
	public Billing getBilling() {
		return billing;
	}

}
