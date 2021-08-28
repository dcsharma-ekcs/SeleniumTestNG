package com.copperchips;

import java.util.Random;

public class MyFristTest {

    public static void main(String[] args) {

        Random rand = new Random();
        int randNumber = rand.nextInt(10000);
        System.out.println("Hello..."+randNumber);
        //System.out.printf("%04d%n", rand.nextInt(10000));


    }
}
