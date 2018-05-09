package pl.akademiakodu;

public class LoopsTasks1to3 {
    public static void main(String[] args) {

        //task 3: liczby parzyste z przedziału od 5 do 50
        for (int i = 5; i <= 50; i++) {
            if (i % 2 == 1) {
                System.out.println(i);
            }
        }

        for (int i = 5; i < 11; i++) {
            System.out.println(i);
        }

        for (int i = 1; i <= 5; i++) {
            System.out.println("Hello");
        }


        for (int i = 5; i <= 49; i += 2) {
            System.out.println(i);
        }
    }
}
