package com.adm.restaurante.controller;

import com.adm.restaurante.dto.PaymentConfirmJsonRest;
import com.adm.restaurante.dto.PaymentIntentJsonRest;
import com.adm.restaurante.exceptions.BookingExceptions;
import com.adm.restaurante.service.PaymentService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping(path = "/booking-payment" + "/v1")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/paymentIntent", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> paymentInten(@RequestBody PaymentIntentJsonRest paymentIntentJsonRest) throws StripeException, BookingExceptions {
        PaymentIntent paymentIntent = paymentService.paymentIntent(paymentIntentJsonRest);
        String paymentString = paymentIntent.toJson();
        return new ResponseEntity<String>(paymentString, HttpStatus.OK);
    }

    /*
        @ResponseStatus(HttpStatus.OK)
        @PostMapping(value = "/paymentConfirm"+"{paymentId}", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<String> paymenConfirm(@PathVariable ("paymentId") String paymentId) throws StripeException{
            PaymentIntent paymentIntent = paymentService.paymentConfirm(paymentId);
            String paymentString = paymentIntent.toJson();
            return new ResponseEntity<String>(paymentString, HttpStatus.OK);
        }
    */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/paymentConfirm", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> paymentConfirm(@RequestBody PaymentConfirmJsonRest paymentIntentJsonRest) throws StripeException, MessagingException, BookingExceptions {
        PaymentIntent paymentIntent = paymentService.paymentConfirm(paymentIntentJsonRest);
        String paymentString = paymentIntent.toJson();
        return new ResponseEntity<String>(paymentString, HttpStatus.OK);
    }


    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/paymentCancel" + "{paymentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> paymenCancel(@PathVariable("paymentId") String paymentId) throws StripeException {
        PaymentIntent paymentIntent = paymentService.paymentCancel(paymentId);
        String paymentString = paymentIntent.toJson();
        return new ResponseEntity<String>(paymentString, HttpStatus.OK);
    }


}
