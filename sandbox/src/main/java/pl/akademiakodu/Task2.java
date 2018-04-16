package pl.akademiakodu;

import java.util.Scanner;

public class Task2 {

    public static void main(String[] args) {
        System.out.println("Podaj wyraz");
        Scanner scanner = new Scanner(System.in);
        String word = scanner.next();
        System.out.println(word.length());
    }

}