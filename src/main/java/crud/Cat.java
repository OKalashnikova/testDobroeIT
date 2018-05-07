package crud;

public class Cat extends Animal {
    int type = 1;

    public Cat(String name, int age, int type) {
        super(name, age, type);
    }

    public Cat(String name, int age) {
        super(name, age);
    }

    public Cat(){}

    public Cat(String name, int age, long id) {
        super(name, age, id);
    }

    public void voice() {
        System.out.println("Мяу ");
    }

    @Override
    public void dyingVoice() {
        System.out.println("Nooooooooooo! Meeeeeeeew!");
    }

    @Override
    public int getType() {
        return type;
    }


    @Override
    protected void finalize() throws Throwable {
        dyingVoice();
        super.finalize();
    }

}
