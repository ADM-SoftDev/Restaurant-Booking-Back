package com.adm.restaurante.service.impl;

import com.adm.restaurante.dto.PaymentConfirmJsonRest;
import com.adm.restaurante.dto.PaymentIntentJsonRest;
import com.adm.restaurante.exceptions.BookingExceptions;
import com.adm.restaurante.service.EmailService;
import com.adm.restaurante.service.PaymentService;
import com.adm.restaurante.service.ReservationService;
import com.adm.restaurante.service.RestaurantService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private EmailService emailService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private RestaurantService restaurantService;

    @Value("${stripe.key.secret}")
    String secretKey;

    private int price;

    public enum Currency {
        USD, EUR, GBP,RON;
    }

    public PaymentServiceImpl() {
        this.price= 0;
    }

    @Override
    public PaymentIntent paymentIntent(PaymentIntentJsonRest paymentInicioDto) throws StripeException, BookingExceptions {
        Stripe.apiKey = secretKey;
        Map<String, Object> params = new HashMap<>();
        params.put("currency", Currency.EUR);
        params.put("amount", paymentInicioDto.getPrice());
        params.put("description", paymentInicioDto.getDescription());

        List<Object> paymentMethodTypes = new ArrayList<>();
        paymentMethodTypes.add("card");
        params.put("payment_method_types", paymentMethodTypes);

        //PaymentConfirmJsonRest payment = new PaymentConfirmJsonRest();
        //payment.setPrecio(paymentInicioDto.getPrice());

        return  PaymentIntent.create(params);
    }


    public PaymentIntent paymentConfirm(PaymentConfirmJsonRest paymentConfirmJsonRest) throws StripeException, MessagingException, BookingExceptions {
        Stripe.apiKey = secretKey;
        PaymentIntent paymentIntent = PaymentIntent.retrieve(paymentConfirmJsonRest.getPaymentId());
        Map<String, Object> params = new HashMap<>();
        params.put("payment_method", "pm_card_visa");
        paymentIntent.confirm(params);

        reservationService.actualizarPago(true, paymentConfirmJsonRest.getLocator());

        this.emailService.processSendEmail(paymentConfirmJsonRest.getEmail(), "PAYMENT",paymentConfirmJsonRest.getName() , paymentConfirmJsonRest.getLocator());

        //restaurantService.actualizaPriceRestaurant(paymentConfirmJsonRest.getLocator(), paymentConfirmJsonRest.getPrecio());

        return paymentIntent;
    }


    public PaymentIntent paymentCancel(String paymentId) throws StripeException {
        Stripe.apiKey = secretKey;
        PaymentIntent paymentIntent = PaymentIntent.retrieve(paymentId);
        paymentIntent.cancel();
        return paymentIntent;
    }
}
