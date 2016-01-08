package CostCalculator;

import BillingReader.Offer;
import CostCalculator.summarizer.AbonamentSummary;
import OperatorResolver.operatorresolver.billingcontainers.Billing;
import OperatorResolver.operatorresolver.billingcontainers.ServiceDetails;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class AbonamentSummaryTest {

    @Test
    public void testAbonamentFreeInternetNotExceeded() throws Exception {
        Offer offer = Mockito.mock(Offer.class);
        ServiceDetails internet = Mockito.mock(ServiceDetails.class);
        Billing billing = Mockito.mock(Billing.class);

        when(offer.getMonthlyPayment()).thenReturn(new BigDecimal("99.99"));
        when(offer.getFreeInternetMb()).thenReturn(1000.0);
        when(offer.getInternetMbCost()).thenReturn(new BigDecimal("0.10"));
        when(internet.getQuantity()).thenReturn(930000);
        when(billing.getInternet()).thenReturn(internet);

        AbonamentSummary abonamentSummary = new AbonamentSummary(offer);

        assertEquals(new BigDecimal("99.99"), abonamentSummary.cost(billing));
    }

    @Test
    public void testAbonamentFreeInternetExceeded() throws Exception {
        Offer offer = Mockito.mock(Offer.class);
        ServiceDetails internet = Mockito.mock(ServiceDetails.class);
        Billing billing = Mockito.mock(Billing.class);

        when(offer.getMonthlyPayment()).thenReturn(new BigDecimal("99.99"));
        when(offer.getFreeInternetMb()).thenReturn(1024.0);
        when(offer.getInternetMbCost()).thenReturn(new BigDecimal("0.10"));
        when(internet.getQuantity()).thenReturn(1124 * 1024);
        when(billing.getInternet()).thenReturn(internet);

        AbonamentSummary abonamentSummary = new AbonamentSummary(offer);

        assertEquals(new BigDecimal("109.99"), abonamentSummary.cost(billing));
    }
}