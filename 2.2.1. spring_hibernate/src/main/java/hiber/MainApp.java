package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        Car lada = new Car("lada", 1234);
        Car ladapIrora = new Car("ladapIrora", 1234);
        Car ladagranta = new Car("ladagranta", 1234);
        Car ladakalina = new Car("ladakalina", 1234);
        userService.add(lada);
        userService.add(ladapIrora);
        userService.add(ladagranta);
        userService.add(ladakalina);
        userService.add(new User("User1", "Lastname1", "user1@mail.ru", lada));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru", ladapIrora));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru", ladagranta));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru", ladakalina));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar());
        }

        context.close();
    }
}
