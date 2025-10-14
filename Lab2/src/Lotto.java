import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;



public class Lotto {
    public static void main(String[] args) {
//        if (args.length != 6) {
//            System.out.println("zla ilosc argumentow");
//        }
//
//        ArrayList<Integer> user_input = new ArrayList<>();
//
//        for (int i = 0; i < args.length; i++) {
//            user_input.add(Integer.parseInt(args[i]));
//        }
//        System.out.println(user_input);

        ArrayList<Integer> wyniki = new ArrayList<>();
        Random rand = new Random();

        while (wyniki.size() < 6) {
            int liczba = (rand.nextInt(49) + 1);
            if (!wyniki.contains(liczba)) {
                wyniki.add(liczba);
            }
        }

        ArrayList<Integer> sim_user_input = new ArrayList<>();
        int counter = 0;
        long start = System.currentTimeMillis();
        int tries = 0;

        do {
            tries++;
            counter = 0;
            sim_user_input.clear();

            while (sim_user_input.size() < 6){
                int liczba = (rand.nextInt(49) + 1);
                if (!sim_user_input.contains(liczba)) {
                    sim_user_input.add(liczba);
                }
            }
            for (int i = 0; i < 6; i++) {
                if (wyniki.contains(sim_user_input.get(i))) {
                    counter++;
                }
            }
//            System.out.println(counter + "/6");
        } while (counter < 6);

        long end = System.currentTimeMillis();
        System.out.println("6/6!");
        float time = (end - start) / 1000F;
        System.out.println(time + " s");
        System.out.println(tries + " prob");

//    System.out.println("wynik losowania: " + wyniki);
//    System.out.println("liczby uzytkownika: " + user_input);
//    System.out.println("liczba trafien: " + counter);
    }

}
