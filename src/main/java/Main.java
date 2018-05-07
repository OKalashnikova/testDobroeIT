import com.google.gson.Gson;
import crud.*;
import org.eclipse.jetty.http.HttpStatus;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.swing.*;

import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static spark.Spark.*;

// localhost:4567
public class Main {
    public static AnimalDao animalDao = new AnimalDao();

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        List<Animal> list = new ArrayList<>();
//        for (Animal a : animalDao.findAll()){
//            list.add(a);
//        }


        //       System.out.println(list.toString());

//        post("/test", (request, response)-> {
//            list.add(new Gson().fromJson(request.body(), (Type) Animal.class));
//            response.status(HttpStatus.CREATED_201);
//            return "";
//        });

        post("/test", (request, response) -> {
            animalDao.add(new Gson().fromJson(request.body(), (Type) Animal.class));
            response.status(HttpStatus.CREATED_201);
            return "";
        });

        put("/test", (request, response) -> {
            animalDao.update(new Gson().fromJson(request.body(), (Type) Animal.class));
            response.status(HttpStatus.CREATED_201);
            return "";
        });

        //https://jaxenter.com/crud-rest-apis-spark-framework-136053.html

        get("/test", (request, response) -> new Gson().toJson(animalDao.findAll().toString()));

        get("/test/:id", (request, response) -> {
            long id = Integer.valueOf(request.params(":id"));
            if (animalDao.findById(id) != null) {
                return new Gson().toJson(animalDao.findById(id).toString());
            } else response.status(HttpStatus.NOT_FOUND_404);
            return "";
        });

        delete("/test/:id", ((request, response) ->
        {
            long id = Integer.valueOf(request.params(":id"));
            if(animalDao.findById(id)!=null){
                return new Gson().toJson(animalDao.remove(id));
            }else {
                response.status(HttpStatus.NOT_FOUND_404);
            }
            return "";
        }
        ));

        get("/test/class/:n", (request, response) -> {
            int id = Integer.valueOf(request.params(":n"));
            if(id == 1)
            return new Gson().toJson(animalDao.findByType(Cat.class).toString());
            else return new Gson().toJson(animalDao.findByType(Dog.class).toString());

        });

    }

}

/*
public class Main {
    public static void main(String[] args) {
        List<Test> tests = new ArrayList<>();
        post("/test", (request, response)-> {
            tests.add(new Gson().fromJson(request.body(), Test.class));
            response.status(HttpStatus.CREATED_201);
            return "";
        });
        get("/test", (request, response)-> new Gson().toJson(tests));
//        get("/test", (request, response)-> "Получилось!");
//        get("/test:id", (request, response)-> request.params(":id"));
        get("/test/:id", (request, response)-> {
            int id = Integer.valueOf(request.params(":id"));
            return new Gson().toJson(tests.stream().filter(it-> it.id == id).findAny().orElse(null));
        });
    }
}


class Test{
    int id;
    String name;
}
*/

//https://ulearn.me/Course/Testing/Restlet_Client_2f72b80e-8085-4ee2-8dd0-94bdf396f56c