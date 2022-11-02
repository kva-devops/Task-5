package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(
              new User("User1",
                      "Lastname1",
                      "user1@mail.ru",
                      new Car("WBM", 12345)));
      userService.add(
              new User("User2",
                      "Lastname2",
                      "user2@mail.ru",
                      new Car("Atoyot", 54321)));
      userService.add(
              new User("User3",
                      "Lastname3",
                      "user3@mail.ru",
                      new Car("ZAV", 67890)));
      userService.add(
              new User("User4",
                      "Lastname4",
                      "user4@mail.ru",
                      new Car("Drof", 87654)));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car model = "+user.getCar().getModel());
         System.out.println("Car series = "+user.getCar().getSeries());
         System.out.println();
      }

      User userFound = userService.getUserByCarModelAndSeries("Drof", 87654);
      System.out.println("Result method 'getUserByCarModelAndSeries': ");
      System.out.println("Id = " + userFound.getId());
      System.out.println("First Name = " + userFound.getFirstName());
      System.out.println("Last Name = " + userFound.getLastName());
      System.out.println("Email = " + userFound.getEmail());
      System.out.println("Car model = " + userFound.getCar().getModel());
      System.out.println("Car series = " + userFound.getCar().getSeries());
      System.out.println();

      context.close();
   }
}
