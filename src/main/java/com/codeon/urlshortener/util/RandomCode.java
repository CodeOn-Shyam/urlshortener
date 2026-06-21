package com.codeon.urlshortener.util;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomCode {
    private static final RandomStringUtils RANDOM = RandomStringUtils.insecure();
    private RandomCode() {
    }
    public static String generateRandomCode(int length){
        return RANDOM.nextAlphanumeric(length);
    } 
}
