package com.adm.restaurante.service;

import com.adm.restaurante.exceptions.BookingExceptions;

import javax.mail.MessagingException;

public interface EmailService {

    public String processSendEmail(final String receiver, String templateCode,
                                   String currentName, String codeReserva) throws BookingExceptions, MessagingException;

    public String processSendEmail2(final String receiver, String templateCode,
                                  String currentName) throws BookingExceptions, MessagingException;

}
