package pl.akademiakodu;

import java.util.Scanner;

public class Task5and6 {
    public static void main(String[] args) {
        System.out.println("Podaj wyraz:");
        Scanner scanner = new Scanner(System.in);
        String word = scanner.next();
        int length = word.length();
        System.out.println(word.charAt(length-2));
        System.out.println(word.charAt(0));
    }
}
