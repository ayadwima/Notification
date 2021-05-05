package com.example.notificationapp;

public class Api {
    private static final String ORIGIN_URL = "https://mcc-users-api.herokuapp.com/";

    public static final String URL_CREATE_USER = ORIGIN_URL + "add_new_user";
    public static final String URL_CHECK_USER = ORIGIN_URL + "login";
    public static final String URL_TOKEN_USER = ORIGIN_URL + "add_reg_token";
}
