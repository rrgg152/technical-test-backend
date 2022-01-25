package com.playtomic.tests.wallet;

import com.playtomic.tests.wallet.domain.*;
import com.playtomic.tests.wallet.shared.domain.UuidGenerator;
import com.playtomic.tests.wallet.shared.domain.bus.event.DomainEvent;
import com.playtomic.tests.wallet.shared.domain.bus.event.EventBus;
import com.playtomic.tests.wallet.shared.infrastructure.JavaUuidGenerator;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "test")
public class CheckWalletAcceptanceTests {
    @Autowired
    private WalletRepository repository;

    @Autowired
    private EventBus eventBus;

    private UuidGenerator generator;

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp(){
        RestAssured.port = port;
        generator = new JavaUuidGenerator();
    }

    @Test
    public void recharge_money_to_a_wallet(){
        String id = generator.generate();
        String amount = String.valueOf((int)Math.floor(Math.random()*(100-10+1)+10));
        String creditCard = String.valueOf(new Random());

        givenThereIsAWallet(id);
        WhenTopUpTheWallet(id, amount, creditCard);
        thenTheWalletContainsTheMoney(id, amount);
    }
    @Test
    public void recharge_money_once_per_payment(){
        String id = generator.generate();
        String paymentId = generator.generate();
        String amount = String.valueOf((int)Math.floor(Math.random()*(100-10+1)+10));
        givenThereIsAWallet(id);
        givenISendToTheBus(
                new PaymentFinishedDomainEvent(new WalletId(id), new WalletAmount(new BigDecimal(amount)), new PaymentId(paymentId)),
                new PaymentFinishedDomainEvent(new WalletId(id), new WalletAmount(new BigDecimal(amount)), new PaymentId(paymentId)),
                new PaymentFinishedDomainEvent(new WalletId(id), new WalletAmount(new BigDecimal(amount)), new PaymentId(paymentId))
        );

        thenTheWalletContainsTheMoney(id, amount);
    }
    private void givenThereIsAWallet(String id) {
        /* we do not have creator endpoint */
        repository.save(new Wallet(new WalletId(id), new WalletBalance(new BigDecimal("0.00"))));
    }


    private void WhenTopUpTheWallet(String id, String amount, String creditCard) {
        given()
                .body(buildTopUpRequest(amount, creditCard))
                .contentType(ContentType.JSON)
                .put(String.format("/wallet/%s", id))
                .then()
                .statusCode(202);
    }

    private String buildTopUpRequest(String amount, String creditCard) {
        JSONObject json = new JSONObject();
        try {
            json.put("amount", amount);
            json.put("creditCard", creditCard);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json.toString();
    }

    private void thenTheWalletContainsTheMoney(String id, String amount) {
        given()
                .get(String.format("/wallet/%s", id))
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("current_balance", equalTo(amount+".00"));
    }

    private void givenISendToTheBus(DomainEvent... domainEvents) {
        eventBus.publish(Arrays.asList(domainEvents));
    }

}
