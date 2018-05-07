package crud;

public class Dog extends Animal {
    int type = 2;

    public Dog(String name, int age, int type) {
        super(name, age, type);
    }

    public Dog(){}

    public Dog(String name, int age) {
        super(name, age);
    }

    public Dog(String name, int age, long id) {
        super(name, age, id);

    }

    @Override
    public int getType() {
        return type;
    }

    public void voice() {
        System.out.println("Гав ");
    }

    @Override
    public void dyingVoice() {
        System.out.println("Gav! Gav! Aaaaaaaaaaauuuuuuuuuuu!");
    }

    @Override
    protected void finalize() throws Throwable {
        dyingVoice();
        super.finalize();
    }

}
