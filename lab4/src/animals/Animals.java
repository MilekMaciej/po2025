package animals;

import java.util.Random;

public abstract class Animals {
        public int legs;
        public String name;

        public abstract String getDescription();

        public static Animals getRandomAnimal()
        {
            Random rand = new Random();
            int n = rand.nextInt(3) + 1;
            int i = rand.nextInt(1000);
            Animals animal = null;

            if(n == 1){
                animal = new Dog("dog" + i);
            }
            if(n == 2){
                animal = new Parrot("parrot" + i);
            }
            if(n == 3){
                animal = new Snake("snake" + i);
            }

            return animal;
        }

        public abstract void makeSound();

}
