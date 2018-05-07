package crud;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnimalDao {


    public AnimalDao() {

    }


    public boolean add(Animal animal) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
        try (PreparedStatement statement = con.prepareStatement("INSERT INTO animal ( name, age, animal_type_id) VALUES ( ?, ?, ?)")) {
            statement.setString(1, animal.getName());
            statement.setInt(2, animal.getAge());
            statement.setLong(3, animal.getType());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            con.close();
        }

        return true;
    }

    public boolean remove(long animalId) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
        try (PreparedStatement statement = con.prepareStatement("delete from animal where id=?")) {
            statement.setLong(1, animalId);
            System.out.println(statement.executeUpdate());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            con.close();
        }
        return true;
    }

    public boolean update(Animal animal) throws SQLException {
        int update = 0;
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
        try (PreparedStatement statment = con.prepareStatement("UPDATE animal SET name=?, age=?, animal_type_id=? WHERE id =?")) {
            statment.setLong(4, animal.getId());
            statment.setString(1, animal.getName());
            statment.setInt(2, animal.getAge());
            statment.setInt(3, animal.getType());

            update = statment.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            con.close();
        }
        if(update==0){
            return false;
        }else
        return true;
    }

    public Animal findById(long animalId) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
        Animal newAnimal = null;
        try (PreparedStatement statment = con.prepareStatement("SELECT id, name , age, animal_type_id FROM animal where id=?")) {
            statment.setLong(1, animalId);
            ResultSet resultSet = statment.executeQuery();
            if (resultSet.next()) {
                long idA = resultSet.getLong("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt(3);
                int animalType = resultSet.getInt("animal_type_id");
                if (animalType == 1) {
                    newAnimal = new Cat(name, age, idA);

                } else {
                    newAnimal = new Dog(name, age, idA);
                }
            }
            statment.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            con.close();
        }
        return newAnimal;
    }

    public List<Animal> findAll() throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
        List<Animal> list = new ArrayList<Animal>();
        Animal animal = null;
        try {
            PreparedStatement statement = con.prepareStatement("select id, name, age, animal_type_id from animal");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                long idA = rs.getLong("id");
                String name = rs.getString("name");
                int age = rs.getInt(3);
                int animalType = rs.getInt("animal_type_id");
                if (animalType == 2) {
                    animal = new Dog(name, age, idA);
                } else {
                    animal = new Cat(name, age, idA);
                }
                list.add(animal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            con.close();
        }
        return list;
    }

    public List<Animal> findByType(Object o) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
        List<Animal> list = new ArrayList<Animal>();
        Animal animal = null;
        try {
            PreparedStatement statement = con.prepareStatement("select id, name, age, animal_type_id from animal");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                long idA = rs.getLong("id");
                String name = rs.getString("name");
                int age = rs.getInt(3);
                int animalType = rs.getInt("animal_type_id");

                if (animalType == 1 && o == Cat.class) {
                    animal = new Cat();
                    animal.setId(idA);
                    animal.setName(name);
                    animal.setAge(age);
                    list.add(animal);
                } else if (animalType == 2 && o == Dog.class) {
                    animal = new Dog();
                    animal.setId(idA);
                    animal.setName(name);
                    animal.setAge(age);
                    list.add(animal);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            con.close();
        }


        return list;
    }
}


    /*

    public static Animal getAnimal() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");

        try (PreparedStatement statement = con.prepareStatement("SELECT * FROM animal WHERE id = (?)")) {

            statement.setInt(1, 1);

            final ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String byName = "name: " + resultSet.getString("name");
                int age = resultSet.getInt(3);
                int animalType = resultSet.getInt("animal_type_id");
                //System.out.println(byName);
                //System.out.println("age: " + age);
                //System.out.println("animal type id: " + animalType);


                try (PreparedStatement statement2 = con.prepareStatement("SELECT * FROM animal_type WHERE id = (?)")) {
                    statement2.setInt(1, animalType);
                    final ResultSet resultSet2 = statement2.executeQuery();
                    if (resultSet2.next()) {
                        String type = resultSet2.getString("name");
                        //System.out.println("animal type: " + type);
                    }
                }

            }
        } finally {
            con.close();
        }
       // return new Cat(byName, age, animalType);
    }
}
*/
    /*
    protected List<Animal> arrayAnimal = new ArrayList<>();
    public int sizeArrayAnimal = 0;


    public boolean add(Animal animal) {
        int index = getIndex(animal.getId());
        if (index >= 0) {
            System.out.println("Животное с таким id существует");
            return false;
        } else {
            insertAnimal(animal);
//            arrayAnimal.set(sizeArrayAnimal, animal);
            sizeArrayAnimal++;
            return true;
        }
    }


    public boolean remove(long animalId) {
        int index = getIndex(animalId);
        if (index < 0) {
            System.out.println("Животного с таким id не существует");
            return false;
        } else {
            arrayAnimal.remove(index);
            sizeArrayAnimal--;
            return true;
        }
    }

    public boolean update(Animal animal) {
        int index = getIndex(animal.getId());
        if (index<0){
            System.out.println("Животного с таким id не существует");
            return false;
        }else {
            arrayAnimal.set(index, animal);
        }
        return true;
    } //<- обновление по id.

    public Animal findById(long animalId) {
        int index = getIndex(animalId);
        if (index<0){
            System.out.println("Животного с таким id не существует");
            return null;
        }else
        return arrayAnimal.get(index);
    }

    public List<Animal> findAll() {
        return arrayAnimal.stream()
                .limit(sizeArrayAnimal)
                .collect(Collectors.toList());
    }

    public List<Animal> findByType(Object type) {
        return arrayAnimal.stream()
                .filter(obj -> obj.getClass().equals(type))
                .collect(Collectors.toList());
    }


    ///--------------------another methods


    public int getSizeArrayAnimal() {
        return sizeArrayAnimal;
    }

    public int getIndex(long id) {
        for (int i = 0; i < getSizeArrayAnimal(); i++) {
            if (id == ((int) arrayAnimal.get(i).getId())) {
                return i;
            }
        }
        return -1;
    }

    public void insertAnimal(Animal animal) {
        arrayAnimal.add(getSizeArrayAnimal(), animal);
        System.out.println("Animal " + animal.toString());
    }
    */


