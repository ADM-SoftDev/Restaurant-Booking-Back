package com.adm.restaurante.service;

import com.adm.restaurante.dto.PaymentConfirmJsonRest;
import com.adm.restaurante.dto.PaymentIntentJsonRest;
import com.adm.restaurante.exceptions.BookingExceptions;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

import javax.mail.MessagingException;

public interface PaymentService {

    public PaymentIntent paymentIntent(PaymentIntentJsonRest paymentInicioDto) throws StripeException, BookingExceptions;

    public PaymentIntent paymentConfirm(PaymentConfirmJsonRest paymentConfirmJsonRest) throws StripeException, MessagingException, BookingExceptions;

    public PaymentIntent paymentCancel(String paymentId) throws StripeException;


}
