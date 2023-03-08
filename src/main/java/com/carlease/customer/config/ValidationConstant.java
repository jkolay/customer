package com.carlease.customer.config;

/** This class contains the Validation related length,patterns */
public class ValidationConstant {
  public static final String PATTERN_NAME = "^(?:\\p{L}\\p{M}*|[',. \\-]|\\s)*$";

  public static final int MAX_LENGTH_NAME = 100;

  public static final int MAX_LENGTH_ADDRESS = 255;
  public static final String HOUSE_NUMBER_LENGTH_NAME = "^[0-9]+$";
  public static final int HOUSE_NUMBER_LENGTH = 5;
  public static final int ZIPCODE_LENGTH = 6;
  public static final int PLACE_LENGTH = 20;
  public static final int MAX_LENGTH_EMAIL = 30;
  public static final int MAX_LENGTH_PHONE_NUMBER = 10;
  public static final String PATTERN_ZIPCODE = "^[a-zA-Z0-9]+$";
  public static final String PLACE_PATTERN = "^[a-zA-Z]+$";
  public static final String PATTERN_EMAIL = "^(.+)@(\\S+)$";
  public static final String PATTERN_PHONE_NUMBER = "^\\d{10}$";
}
