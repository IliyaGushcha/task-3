package com.gushcha.task3;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Scanner;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class Game {

    public static boolean same (String[] args) {
        for (int i = 0; i < args.length; i++) {
            for (int j = i+1; j < args.length; j++){
                if (args[i].equals(args[j])) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void menu(String[] arr) {
        System.out.println("Available moves: ");
        for (int i = 0; i < arr.length; i++)
            System.out.println((i + 1) + " - " + arr[i]);
        System.out.println("0 - exit\n");
    }

    public static void algorithm(int myMove, int comMove, int size) {
        if ((Math.abs(myMove - comMove) <= size && myMove > comMove) || (myMove < comMove && Math.abs(myMove - comMove) > size)) {
            System.out.println("You Win!");
        } else if (Math.abs(myMove - comMove) == 0)
            System.out.println("Draw!");

        else
            System.out.println("You lose!");
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {
        Scanner in = new Scanner(System.in);

        if (args.length >= 3 && args.length % 2 == 1 && same(args)) {
            while (true) {
                SecureRandom random = new SecureRandom();
                byte [] seed = random.generateSeed(16);

                Random random1 = new Random();
                int compMove = random1.nextInt(args.length);

                SecretKeySpec key = new SecretKeySpec(seed, "HmacSHA256"); //secret.getBytes(StandardCharsets.UTF_8)
                Mac mac = Mac.getInstance("HmacSHA256");
                mac.init(key);

                System.out.println("HMAC: " + HexBin.encode(mac.doFinal(args[compMove].getBytes(StandardCharsets.UTF_8))));

                menu(args);

                int myMove;
                while (true) {
                    System.out.print("Enter your move: ");
                    while (!in.hasNextInt()) {
                        System.out.print('\n');
                        menu(args);
                        System.out.print("That not a number! Please enter a number from 0 to " + args.length + ": ");
                        in.next();
                    }
                    myMove = in.nextInt();
                    if (myMove > 0 && myMove <= args.length) {
                        System.out.println("Your move: " + args[myMove - 1]);
                        System.out.println("Computer move: " + args[compMove]);
                        break;
                    }
                    else if (myMove == 0) {
                        System.out.print("Goodbye!!! :)\n");
                        return;
                    }
                    else {
                        System.out.print("\n");
                        menu(args);
                        System.out.print("Please enter a number from 0 to " + args.length + ". ");
                    }
                }
                algorithm(myMove - 1, compMove, args.length/2);
                System.out.println("HMAC key: " + HexBin.encode(seed) + "\n"); //hmac key
            }
        } else
            System.out.println("Error! Incorrect data entered");
    }
}
