package OperatorResolver;

import OperatorResolver.operatorresolver.*;
import OperatorResolver.operatorresolver.billingcontainers.Billing;
import OperatorResolver.operatorresolver.billingcontainers.ServiceDetails;
import OperatorResolver.operatorresolver.billingcontainers.Services;
import OperatorResolver.operatorresolver.billingdata.*;
import OperatorResolver.numbersverificator.Verifiers;
import OperatorResolver.numbersverificator.verifiers.prefixverifier.Prefixes;
import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.rule.impl.GetterMustExistRule;
import com.openpojo.validation.rule.impl.SetterMustExistRule;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;


public class OperatorResolverTest {
    private BillingLists testList = mock(BillingLists.class);
    private ServiceDetails serviceDetails = new ServiceDetails();
    private Dial dial = new Dial();
    private Sms sms = new Sms();
    private Mms mms = new Mms();
    private Transfer transfer = new Transfer();

    OperatorResolverImp opResImpl = new OperatorResolverImp(testList);

    @Test
    public void testDial(){
        int length = 10;
        String number = "888888888";
        BigDecimal value = BigDecimal.valueOf(0);
        dial.setLength(length);
        dial.setNumber(number);
        dial.setValue(value);
        assertEquals(dial.getLength(), length);
        assertEquals(dial.getNumber(), number);
        assertEquals(dial.getValue(), value);
    }
    @Test
    public void testSms(){
        String number = "608800000";
        BigDecimal value = BigDecimal.valueOf(3);
        sms.setNumber(number);
        sms.setValue(value);
        assertEquals(sms.getNumber(), number);
        assertEquals(sms.getValue(), value);
    }
    @Test
    public void testMms(){
        String number = "792600000";
        BigDecimal value = BigDecimal.valueOf(2);
        mms.setNumber(number);
        mms.setValue(value);
    }
    @Test
    public void testTransfer(){
        int size = 30;
        BigDecimal value = BigDecimal.valueOf(0);
        transfer.setDataSize(size);
        transfer.setValue(value);
    }

    @Test
    public void testPojo(){
        PojoClass mmsPojo = PojoClassFactory.getPojoClass(Mms.class);
        PojoClass smsPojo = PojoClassFactory.getPojoClass(Sms.class);
        PojoClass trasferPojo = PojoClassFactory.getPojoClass(Transfer.class);
        PojoClass dialPojo = PojoClassFactory.getPojoClass(Dial.class);
        Validator validator = ValidatorBuilder.create()
                .with(new GetterMustExistRule())
                .with(new SetterMustExistRule())
                .with(new SetterTester())
                .with(new GetterTester()).build();
        List<PojoClass> classToTest = new LinkedList<>();
        classToTest.add(mmsPojo);
        classToTest.add(smsPojo);
        classToTest.add(trasferPojo);
        classToTest.add(dialPojo);
        validator.validate(classToTest);
    }

    @Test
    public void testServicesPojo(){
        PojoClass servicesPojo = PojoClassFactory.getPojoClass(Services.class);
        Validator validator = ValidatorBuilder.create()
                .with(new GetterMustExistRule())
                .with(new GetterTester()).build();
        validator.validate(servicesPojo);
    }

    @Test
    public void testBillingList(){
        PojoClass billingList = PojoClassFactory.getPojoClass(BillingLists.class);
        Validator validator = ValidatorBuilder.create()
                .with(new GetterMustExistRule())
                .with(new GetterTester()).build();
        validator.validate(billingList);
    }
    @Test
    public void testBillingLists(){
        List<Dial> dials = new LinkedList<>();
        List<Sms> messages = new LinkedList<>();
        List<Mms> mediaMessages = new LinkedList<>();
        List<Transfer> transfers = new LinkedList<>();

        dials.add(dial);
        messages.add(sms);
        mediaMessages.add(mms);
        transfers.add(transfer);

        Mockito.when(testList.getDials()).thenReturn(dials);
        Mockito.when(testList.getMms()).thenReturn(mediaMessages);
        Mockito.when(testList.getSms()).thenReturn(messages);
        Mockito.when(testList.getTransfers()).thenReturn(transfers);

        assertEquals(testList.getDials(), dials);
        assertEquals(testList.getMms(), mediaMessages);
        assertEquals(testList.getSms(), messages);
        assertEquals(testList.getTransfers(), transfers);

    }
    @Test
    public void testInternetQuantity(){
        Billing billing = opResImpl.getBilling();
        assertEquals(billing.getInternet().getQuantity(), serviceDetails.getQuantity());
    }

    @Test
    public void testServiceDetails(){

        ServiceDetails serviceDetails = new ServiceDetails();

        serviceDetails.addServiceDetails(10);
        serviceDetails.addServiceDetails(101);
        serviceDetails.addServiceDetails(200);

        assertEquals(serviceDetails.getQuantity(), 10+101+200);
    }

    @Test

    public void testBilling(){

        Billing billing = new Billing();

        billing.addConnection(Operator.ORANGE, 10);
        billing.addConnection(Operator.ORANGE, 25);

        billing.addInternet(15);
        billing.addInternet(50);

        billing.addMms(Operator.ORANGE);
        billing.addMms(Operator.ORANGE);

        billing.addSms(Operator.ORANGE);
        billing.addSms(Operator.ORANGE);

        assertEquals(billing.getOperatorToServices().get(Operator.ORANGE).getConnections().getQuantity(), 35);
        assertEquals(billing.getInternet().getQuantity(), 65);
        assertEquals(billing.getOperatorToServices().get(Operator.ORANGE).getMms().getQuantity(), 2);
        assertEquals(billing.getOperatorToServices().get(Operator.ORANGE).getSms().getQuantity(), 2);

    }

    @Test
    public void testPrefixes(){

        Prefixes prefixes = new Prefixes();
        assertEquals(prefixes.getAreaToPrefix("4812"), Operator.LANDLINE);

    }

    @Test
    public void testOperatorResolverImpl(){

        Dial dial = new Dial("481395416939", 15);
        Dial dial2 = new Dial("481395416939", 25);

        Sms sms = new Sms("481395416939");
        Sms sms2 = new Sms("481395416939");

        Mms mms = new Mms("481395416939");
        Mms mms2 = new Mms("481395416939");

        Transfer transfer = new Transfer(50);
        Transfer transfer2 = new Transfer(150);

        List<Dial> dList = new LinkedList<>();
        dList.add(dial);
        dList.add(dial2);

        List<Sms> sList = new LinkedList<>();
        sList.add(sms);
        sList.add(sms2);

        List<Mms> mList = new LinkedList<>();
        mList.add(mms);
        mList.add(mms2);

        List<Transfer> tList = new LinkedList<>();
        tList.add(transfer);
        tList.add(transfer2);

        BillingLists billingLists = new BillingLists(dList, sList, mList, tList);
        Verifiers verifiers =  mock(Verifiers.class);
        Mockito.when(verifiers.verify("481395416939")).thenReturn(Operator.LANDLINE);
        OperatorResolver op = new OperatorResolverImp(billingLists, verifiers);

        assertEquals(getServiceDetails(op, Operator.LANDLINE).getConnections().getQuantity(), 40);
        assertEquals(getServiceDetails(op, Operator.LANDLINE).getSms().getQuantity(), 2);
        assertEquals(getServiceDetails(op, Operator.LANDLINE).getMms().getQuantity(), 2);
        assertEquals(op.getBilling().getInternet().getQuantity(), 200);

    }

    private Services getServiceDetails(OperatorResolver operatorResolver, Operator operator){
        return operatorResolver.getBilling().getOperatorToServices().get(operator);
    }

}
