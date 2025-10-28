package tasks;

import animals.*;

public class Zoo {


    public static void main(String[] args) {
        Animals[] animals = new Animals[100];
        int leg_counter = 0;

        for(int i = 0; i < 100; i++) {
            animals[i] = Animals.getRandomAnimal();
            leg_counter += animals[i].legs;
        }
    System.out.println(leg_counter);
    }
}
