package com.ardadev;

import com.ardadev.presentation.AppView;

import javax.swing.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EasyTests {
    private static final String REGEX1 =
            "^(\\d+\\.?(\\d+)?)+$";
    private static final String REGEX2 =
            "(\\d\\.(\\d+)?)+";
    private static final String INPUT =
            "234.";

    public static void main(String[] args) {
        Pattern p = Pattern.compile(REGEX1);
        Matcher m = p.matcher(INPUT);
        int count = 0;
        Boolean sw;
        while (sw = m.find()) {
            System.out.println(sw);
            System.out.println(m.group());
        }
    }
}
