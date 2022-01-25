package com.playtomic.tests.wallet.infrastructure.service;


import com.playtomic.tests.wallet.WalletApplicationIT;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;

import java.math.BigDecimal;
import java.net.URI;

/**
 * This test is failing with the current implementation.
 *
 * How would you test this? --> pointing to a docker with the external api or to a test env.
 */
public class StripeServiceTest extends WalletApplicationIT {

    @Value("${stripe.simulator.charges-uri}")
    private String stripeUri;

    private StripeService s;



    @BeforeEach
    public void setUp(){
        URI testUri = URI.create(stripeUri);
        s = new StripeService(testUri, testUri, new RestTemplateBuilder());
    }
    @Test
    public void test_exception() {
        Assertions.assertThrows(StripeAmountTooSmallException.class, () -> s.charge("4242 4242 4242 4242", new BigDecimal(5)));
    }

    @Test
    public void test_ok() throws StripeServiceException {
        s.charge("4242 4242 4242 4242", new BigDecimal(15));
    }
}
