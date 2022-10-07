package com.adm.restaurante.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailTemplateDto {

    private long id;
    private String template;
    private String subject;
    private String templateCode;
}
