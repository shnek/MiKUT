package OperatorResolver;


import OperatorResolver.veryficator.NumberVeryficator;
import OperatorResolver.veryficator.OtherNumberVeryficator;
import OperatorResolver.veryficator.Veryficators;

public class OperatorResolverImp implements OperatorResolver {

	private Billing billing;
	private Veryficators veryficators;

	public OperatorResolverImp(BillingLists billingList) {
		this.billing = new Billing();
		this.veryficators = new Veryficators();

		this.veryficators.add(new NumberVeryficator());
		this.veryficators.add(new OtherNumberVeryficator());

		init(billingList);
	}

	private void init(BillingLists billingList) {

		for (Dial dial : billingList.getDials()) {
			Operator operator = veryficators.verify(dial.getNumber());
			billing.addConnection(operator, dial.getLenght(), dial.getValue());
		}

		for (Sms sms : billingList.getSms()) {
			Operator operator = veryficators.verify(sms.getNumber());
			billing.addSms(operator, sms.getValue());
		}

		for (Mms mms : billingList.getMms()) {
			Operator operator = veryficators.verify(mms.getNumber());
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
