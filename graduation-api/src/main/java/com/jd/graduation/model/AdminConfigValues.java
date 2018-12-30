package com.jd.graduation.model;

import lombok.*;

import java.io.Serializable;

/**
 * designed to transfer the information of the only one admin
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AdminConfigValues implements Serializable {
    public static String ADMIN_ACCOUNT = "ADMIN_ACCOUNT";
    public static String ADMIN_PASSWORD = "ADMIN_PASSWORD";

    private String account;
    private String password;
}