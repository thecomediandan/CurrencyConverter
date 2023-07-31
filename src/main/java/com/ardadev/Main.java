package com.ardadev;

import com.ardadev.app.App;

public class Main {
    public static void main(String[] args){
        try {
            new App();
        }catch (Exception e) {
            e.printStackTrace();

        }
    }
}