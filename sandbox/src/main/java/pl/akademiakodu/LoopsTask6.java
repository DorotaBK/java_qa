package pl.akademiakodu;

import java.util.Scanner;

public class LoopsTask6 {
    public static void main(String[] args) {
        int secretNumber = 600;
        int userNumber;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Podaj poprawną liczbę: ");
            userNumber = scanner.nextInt();
        } while (secretNumber != userNumber);
        System.out.println("Gratulacje!!! Podałeś poprawną liczbę!");
    }
}