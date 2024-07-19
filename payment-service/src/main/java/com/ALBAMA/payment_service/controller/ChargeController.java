package com.ALBAMA.payment_service.controller;

import com.ALBAMA.payment_service.PaymentServiceApplication;
import com.ALBAMA.payment_service.data.ChargeEntry;
import com.ALBAMA.payment_service.data.ChargeRequest;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.*;
import com.stripe.model.checkout.Session;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.stripe.param.checkout.SessionCreateParams;

import java.util.*;


@Controller
public class ChargeController {


    @Value(value = "${stripe.api.key}")
    private String secretKey;

    @Value(value = "${frontend.url}")
    private String frontendUrl;

    Logger logger = LoggerFactory.getLogger(PaymentServiceApplication.class);

    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
    }

    @PostMapping("/create-checkout-session")
    public ResponseEntity<Object> createCheckoutSession(
            @RequestBody @Valid ChargeRequest chargeRequest
    ) throws StripeException {
        if (chargeRequest == null) {
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("url", frontendUrl);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
        } else {
            List<SessionCreateParams.LineItem> lineItems = new ArrayList<>();
            for (ChargeEntry entry : chargeRequest.getChargeEntries()) {
                Product product = Product.retrieve(entry.getProductId());
                lineItems.add(SessionCreateParams.LineItem.builder()
                        .setQuantity(entry.getQuantity().longValue())
                        .setPrice(product.getDefaultPrice())
                        .build());
            }

            Session session = Session.create(createSessionParams(lineItems));
            logger.info(session.getStatus());
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("url", session.getUrl());
            return ResponseEntity.status(HttpStatus.OK).body(responseBody);
        }
    }

    SessionCreateParams createSessionParams(List<SessionCreateParams.LineItem> items) {
        return SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setShippingAddressCollection(
                        SessionCreateParams.ShippingAddressCollection
                                .builder()
                                .addAllowedCountry(
                                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.DE
                                )
                                .addAllowedCountry(
                                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.US
                                )
                                .build()
                )
                .addShippingOption(SessionCreateParams.ShippingOption.builder()
                        .setShippingRateData(SessionCreateParams.ShippingOption.ShippingRateData
                                .builder()
                                .setType(SessionCreateParams.ShippingOption.ShippingRateData.Type.FIXED_AMOUNT)
                                .setFixedAmount(SessionCreateParams.ShippingOption.ShippingRateData.FixedAmount
                                        .builder()
                                        .setAmount(0L)
                                        .setCurrency("eur")
                                        .build()
                                )
                                .setDisplayName("Free shipping")
                                .setDeliveryEstimate(
                                        SessionCreateParams.ShippingOption.ShippingRateData.DeliveryEstimate
                                                .builder()
                                                .setMinimum(
                                                        SessionCreateParams.ShippingOption.ShippingRateData.DeliveryEstimate.Minimum
                                                                .builder()
                                                                .setUnit(
                                                                        SessionCreateParams.ShippingOption.ShippingRateData.DeliveryEstimate.Minimum.Unit.BUSINESS_DAY
                                                                )
                                                                .setValue(10L)
                                                                .build()
                                                )
                                                .setMaximum(
                                                        SessionCreateParams.ShippingOption.ShippingRateData.DeliveryEstimate.Maximum
                                                                .builder()
                                                                .setUnit(
                                                                        SessionCreateParams.ShippingOption.ShippingRateData.DeliveryEstimate.Maximum.Unit.BUSINESS_DAY
                                                                )
                                                                .setValue(15L)
                                                                .build()
                                                )
                                                .build()
                                )
                                .build()
                        )
                        .build()
                )
                .addShippingOption(SessionCreateParams.ShippingOption.builder()
                        .setShippingRateData(SessionCreateParams.ShippingOption.ShippingRateData
                                .builder()
                                .setType(SessionCreateParams.ShippingOption.ShippingRateData.Type.FIXED_AMOUNT)
                                .setFixedAmount(SessionCreateParams.ShippingOption.ShippingRateData.FixedAmount
                                        .builder()
                                        .setAmount(500L)
                                        .setCurrency("eur")
                                        .build()
                                )
                                .setDisplayName("Fast shipping")
                                .setDeliveryEstimate(
                                        SessionCreateParams.ShippingOption.ShippingRateData.DeliveryEstimate
                                                .builder()
                                                .setMinimum(
                                                        SessionCreateParams.ShippingOption.ShippingRateData.DeliveryEstimate.Minimum
                                                                .builder()
                                                                .setUnit(
                                                                        SessionCreateParams.ShippingOption.ShippingRateData.DeliveryEstimate.Minimum.Unit.BUSINESS_DAY
                                                                )
                                                                .setValue(2L)
                                                                .build()
                                                )
                                                .setMaximum(
                                                        SessionCreateParams.ShippingOption.ShippingRateData.DeliveryEstimate.Maximum
                                                                .builder()
                                                                .setUnit(
                                                                        SessionCreateParams.ShippingOption.ShippingRateData.DeliveryEstimate.Maximum.Unit.BUSINESS_DAY
                                                                )
                                                                .setValue(4L)
                                                                .build()
                                                )
                                                .build()
                                )
                                .build()
                        )
                        .build()
                )
                .setSuccessUrl(frontendUrl + "completion")
                .setCancelUrl(frontendUrl + "payment").addAllLineItem(items).build();
    }
    @ExceptionHandler(StripeException.class)
    public ResponseEntity<Object> handleError(StripeException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
    }



}
