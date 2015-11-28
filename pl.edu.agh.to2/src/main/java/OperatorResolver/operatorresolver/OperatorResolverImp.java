package pl.edu.agh.operatorresolver;

import pl.edu.agh.veryficator.NumberVeryficator;
import pl.edu.agh.veryficator.Veryficator;

public class OperatorResolverImp implements OperatorResolver {

	private Billing billing;
	private Veryficator veryficator;

	public OperatorResolverImp(BillingLists billingList) {
		this.billing = new Billing();
		this.veryficator = new NumberVeryficator();

		init(billingList);
	}

	private void init(BillingLists billingList) {

		for (Dial dial : billingList.getDials()) {
			Operator operator = veryficator.verify(dial.getNumber());
			billing.addConnection(operator, dial.getLenght(), dial.getValue());
		}

		for (Sms sms : billingList.getSms()) {
			Operator operator = veryficator.verify(sms.getNumber());
			billing.addSms(operator, sms.getValue());
		}

		for (Mms mms : billingList.getMms()) {
			Operator operator = veryficator.verify(mms.getNumber());
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
