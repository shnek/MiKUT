import OperatorResolver.operatorresolver.*;
import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.rule.impl.GetterMustExistRule;
import com.openpojo.validation.rule.impl.SetterMustExistRule;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;
import junit.framework.TestCase;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.mock;

/**
 * Created by kuba on 30.11.15.
 */
public class OperatorResolverTest extends TestCase {
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
        dial.setLenght(length);
        dial.setNumber(number);
        dial.setValue(value);
        assertEquals(dial.getLenght(), length);
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
    public void testInternetValue(){
        Billing billing = opResImpl.getBilling();
        assertEquals(billing.getInternet().getValue(), serviceDetails.getValue());
    }


}
