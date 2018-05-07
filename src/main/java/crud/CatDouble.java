package crud;

public class CatDouble{
    String name;
    int age;
    long id;
    int type;

    public CatDouble(){}

    public CatDouble(String name, int age, int type) {
        this.name = name;
        this.age = age;
        this.type = type;
    }

    public int getType() {
        return type;
    }


    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

}
