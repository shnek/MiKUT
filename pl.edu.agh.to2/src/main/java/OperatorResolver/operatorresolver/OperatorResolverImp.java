package OperatorResolver.operatorresolver;


import OperatorResolver.operatorresolver.billingcontainers.Billing;
import OperatorResolver.operatorresolver.billingdata.*;
import OperatorResolver.veryficator.verifiers.webverifier.MainNumberVerifier;
import OperatorResolver.veryficator.verifiers.prefixverifier.OtherNumberVerifier;
import OperatorResolver.veryficator.Verifiers;
import OperatorResolver.veryficator.verifiers.prefixverifier.Prefixes;
import OperatorResolver.veryficator.verifiers.webverifier.PageDownloaderImpl;

public class OperatorResolverImp implements OperatorResolver {

	private Billing billing;
	private Verifiers verifiers;

	public OperatorResolverImp(BillingLists billingList) {
		this.billing = new Billing();
		this.verifiers = new Verifiers();

		this.verifiers.add(new MainNumberVerifier(new PageDownloaderImpl()));
		this.verifiers.add(new OtherNumberVerifier(new Prefixes()));

		init(billingList);
	}

	public OperatorResolverImp(BillingLists billingList, Verifiers verifiers) {
		this.billing = new Billing();
		this.verifiers = verifiers;

		init(billingList);
	}

	private void init(BillingLists billingList) {

		for (Dial dial : billingList.getDials()) {
			Operator operator = verifiers.verify(dial.getNumber());
			billing.addConnection(operator, dial.getLength());
		}

		for (Sms sms : billingList.getSms()) {
			Operator operator = verifiers.verify(sms.getNumber());
			billing.addSms(operator);
		}

		for (Mms mms : billingList.getMms()) {
			Operator operator = verifiers.verify(mms.getNumber());
			billing.addMms(operator);
		}

		for (Transfer transfer : billingList.getTransfers()) {
			billing.addInternet(transfer.getDataSize());
		}

	}

	@Override
	public Billing getBilling() {
		return billing;
	}

}
