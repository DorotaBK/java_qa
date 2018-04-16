package pl.akademiakodu;

import java.util.Scanner;

public class StringBuilderExample {
    public static void main(String[] args) {
        System.out.println("Podaj wyraz:");
        Scanner scanner = new Scanner(System.in);
        String word = scanner.next();

        StringBuilder words = new StringBuilder(word);
        System.out.println(words.reverse());

        StringBuilder lastName = new StringBuilder("Basarab");
        System.out.println(lastName.reverse());
    }
}
