package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        Car lada = new Car("lada", 1234);
        Car ladapIrora = new Car("ladapIrora", 1335);
        Car ladagranta = new Car("ladagranta", 6666);
        Car ladakalina = new Car("ladakalina", 1777);

        User anton = new User("User1", "Lastname1", "user1@mail.ru");
        User maks = new User("User2", "Lastname2", "user2@mail.ru");
        User nikita = new User("User3", "Lastname3", "user3@mail.ru");
        User ivan = new User("User4", "Lastname4", "user4@mail.ru");

        userService.add(anton.setCar(lada).setUser(anton));
        userService.add(maks.setCar(ladapIrora).setUser(maks));
        userService.add(nikita.setCar(ladagranta).setUser(nikita));
        userService.add(ivan.setCar(ladakalina).setUser(ivan));


        for (User user : userService.listUsers()) {
            System.out.println(user + " " + user.getCar());
        }

        System.out.println(userService.getUserByCar("ladapIrora", 1335));

        context.close();
    }
}
