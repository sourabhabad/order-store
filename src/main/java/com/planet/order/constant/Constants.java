package com.planet.order.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.regex.Pattern;

/**
 * @author Sourabh.k
 * <p>
 * Store constants, prevent to create Object of class by private constructor
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {

    public static final String COUNTRY_CAMEROON = "Cameroon";
    public static final String COUNTRY_ETHIOPIA = "Ethiopia";
    public static final String COUNTRY_MOROCCO = "Morocco";
    public static final String COUNTRY_MOZAMBIQUE = "Mozambique";
    public static final String COUNTRY_UGANDA = "Uganda";

    public static final Pattern PATTERN_CAMEROON = Pattern.compile("^237 ?[2368]\\d{7,8}$");
    public static final Pattern PATTERN_ETHIOPIA = Pattern.compile("^251 ?[1-59]\\d{8}$");
    public static final Pattern PATTERN_MOROCCO = Pattern.compile("^212 ?[5-9]\\d{8}$");
    public static final Pattern PATTERN_MOZAMBIQUE = Pattern.compile("^258 ?[28]\\d{7,8}$");
    public static final Pattern PATTERN_UGANDA = Pattern.compile("^256 ?\\d{9}$");

    public static final String HEADER_ID = "id";
    public static final String HEADER_EMAIL = "email";
    public static final String HEADER_PHONE_NUMBER = "phone_number";
    public static final String HEADER_PARCEL_WEIGHT = "parcel_weight";
}
