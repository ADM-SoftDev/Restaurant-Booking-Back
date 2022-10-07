package com.adm.restaurante.dto;

import com.adm.restaurante.config.MailConfig;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GmailSender {

    private String host;
    private Integer port;
    private String username;
    private String password;

}
