package animals;

import java.util.Random;

public class Dog extends Animals {

    public Dog(String dog_name) {
        legs = 4;
        name = dog_name;
    }

    @Override
    public String getDescription() {
        return "this is a parrot";
    }

    @Override
    public void makeSound() {
        System.out.println("woof woof");
    }


}
