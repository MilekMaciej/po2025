package animals;

public class Parrot extends Animals{

    public Parrot(String parrot_name) {
    legs = 2;
    name = parrot_name;
    }

    @Override
    public String getDescription() {
        return "this is a parrot";
    }

    @Override
    public void makeSound() {
        System.out.println("arr arr");
    }
}
