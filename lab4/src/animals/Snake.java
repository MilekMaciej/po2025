package animals;

public class Snake extends Animals{

    public Snake(String snake_name) {
        legs = 0;
        name = snake_name;
    }

    @Override
    public String getDescription() {
        return "this is a parrot";
    }

    @Override
    public void makeSound() {
        System.out.println("sssssss");
    }

}
