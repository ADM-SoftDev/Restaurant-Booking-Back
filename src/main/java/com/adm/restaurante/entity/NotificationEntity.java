package com.adm.restaurante.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="NOTIFICATION")
@Data
public class NotificationEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Long idNotificacion;

    @Column(name = "TEMPLATE")
    private String template;

    @Column(name = "TEMPLATE_CODE")
    private String templateCode;
}
