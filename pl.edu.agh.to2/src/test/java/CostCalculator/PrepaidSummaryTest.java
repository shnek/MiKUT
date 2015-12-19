package CostCalculator;

import BillingReader.Offer;
import CostCalculator.summarizer.PrepaidSummary;
import OperatorResolver.operatorresolver.Billing;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import OperatorResolver.operatorresolver.Operator;
import OperatorResolver.operatorresolver.ServiceDetails;
import OperatorResolver.operatorresolver.Services;
import org.junit.Test;
import org.mockito.Mockito;

public class PrepaidSummaryTest {
    @Test
    public void testPrepaidInternet() throws Exception {
        Offer offer = Mockito.mock(Offer.class);
        ServiceDetails internet = Mockito.mock(ServiceDetails.class);
        Billing billing = Mockito.mock(Billing.class);

        when(offer.getInternetMbCost()).thenReturn(new BigDecimal("0.10"));
        when(internet.getQuantity()).thenReturn(1024*100);
        when(billing.getInternet()).thenReturn(internet);

        PrepaidSummary prepaidSummary = new PrepaidSummary(offer);

        assertEquals(new BigDecimal("10.00"), prepaidSummary.cost(billing));
    }

    @Test
    public void testPrepaidNoInternet() throws Exception {
        Offer offer = Mockito.mock(Offer.class);
        when(offer.getOperator()).thenReturn(BillingReader.Operator.PLUS);

        Billing billing = Mockito.mock(Billing.class);
        ServiceDetails internet = Mockito.mock(ServiceDetails.class);
        Services services = Mockito.mock(Services.class);

        ServiceDetails connections = Mockito.mock(ServiceDetails.class);
        ServiceDetails sms = Mockito.mock(ServiceDetails.class);
        ServiceDetails mms = Mockito.mock(ServiceDetails.class);

        when(offer.getInternetMbCost()).thenReturn(new BigDecimal("0.0"));
        when(internet.getQuantity()).thenReturn(0);
        when(billing.getInternet()).thenReturn(internet);

        Map<Operator, Services> operatorToServices = new HashMap<>();

        when(connections.getQuantity()).thenReturn(100);
        when(services.getConnections()).thenReturn(connections);
        // connections cost is then: 100 * 0.11 = 11.00

        when(sms.getQuantity()).thenReturn(10);
        when(services.getSms()).thenReturn(sms);
        // sms cost is then: 10 * 0.02 = 0.20

        when(mms.getQuantity()).thenReturn(5);
        when(services.getMms()).thenReturn(mms);
        // mms cost is then: 5 * 0.55 = 2.75

        when(offer.getInnerCallCost()).thenReturn(new BigDecimal("0.05"));
        when(offer.getOuterCallCost()).thenReturn(new BigDecimal("0.11"));
        when(offer.getInnerSmsCost()).thenReturn(new BigDecimal("0.01"));
        when(offer.getOuterSmsCost()).thenReturn(new BigDecimal("0.02"));
        when(offer.getInnerMmsCost()).thenReturn(new BigDecimal("0.35"));
        when(offer.getOuterMmsCost()).thenReturn(new BigDecimal("0.55"));

        Operator operator = Operator.ORANGE;
        operatorToServices.put(operator, services);

        when(billing.getOperatorToServices()).thenReturn(operatorToServices);

        PrepaidSummary prepaidSummary = new PrepaidSummary(offer);

        assertEquals(new BigDecimal("13.95"), prepaidSummary.cost(billing));
    }
}