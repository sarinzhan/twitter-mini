package Service;

import Data.UserData;
import entities.Organization;
import entities.Person;
import entities.User;
import entities.UserType;
import utils.Customizer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;

public class UserService{
    private Scanner scanner;
    private UserData<User> userDate;
    private  Authentication<User> userAuthentication;

    public UserService(UserData userDate, Authentication<User> userAuthentication) {
        this.userAuthentication = userAuthentication;
        this.scanner = new Scanner(System.in);
        this.userDate = userDate;
    }
    public void registerUser(){
        Customizer.start("Регистрация нового пользователя");
        if(Objects.isNull( userAuthentication.getCurUser())){
            System.err.println("Вы не авторизованы");
            return;
        }
        System.out.print("Введите тип пользователя( 0 - человек, 1 - организация): ");
        int i = scanner.nextInt();
        if (i == 0) {
                this.registerPerson();
            } else if (i == 1) {
            this.registerOrg();
        }

    }
    private void registerPerson(){
        Person person = new Person();
        person.setUserType(UserType.PERSON);
        person.setRegisterDate(LocalDate.now());
        System.out.print("Введите логин: ");
        String login = scanner.next();
        Optional<User> byUserLogin = userDate.getByUserLogin(login);
        while(byUserLogin.isPresent()){
            System.err.println("Пользователь с данным логином уже существует выберите другой");
            login = scanner.next();
            byUserLogin = userDate.getByUserLogin(login);
        }
        person.setLogin(login);
        System.out.print("Введите пароль: ");
        person.setPassword(scanner.next());
        System.out.print("Введите имя: ");
        person.setFirstName(scanner.next());
        System.out.print("Введите фамилию: ");
        person.setSecondName(scanner.next());
        System.out.print("Введите дату рождения(гггг-мм-дд): ");
        LocalDate birthday = LocalDate.parse(scanner.next(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        person.setBirthday(birthday);
        System.out.println("Новый пользователь:");
        Long l = userDate.addUser(person);
        Optional byUserId = userDate.getByUserId(l);
        System.out.println(byUserId.get());
        Customizer.end("Регистрация прошла успешно!");
    }
    private void registerOrg(){
        Organization organization = new Organization();
        organization.setUserType(UserType.ORGANIZATION);
        organization.setRegisterDate(LocalDate.now());
        System.out.print("Введите логин: ");
        organization.setLogin(scanner.next());
        System.out.print("Введите пароль: ");
        organization.setPassword(scanner.next());
        System.out.print("Введите название: ");
        organization.setName(scanner.next());
        System.out.print("Введите род деятельности: ");
        organization.setOccupation(scanner.next());
        System.out.print("Введите дату основания(гггг-мм-дд): ");
        LocalDate foundationDate = LocalDate.parse(scanner.next(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        organization.setFoundationDate(foundationDate);
        Long orgId = userDate.addUser(organization);
        System.out.println("Новый пользователь:");
        Optional byUserId = userDate.getByUserId(orgId);
        System.out.println(byUserId.get());
        Customizer.end("Регистрация прошла успешно!");

    }
    public void currUserInfo(User user){
        Customizer.start("Информация о текущем пользователе");
        if(Objects.isNull(userAuthentication.getCurUser())){
            System.err.println("Вы не авторизованы");
            return;
        }
        if(user.getUserType().equals(UserType.PERSON)){
            System.out.println(((Person) user));
        }else{
            System.out.println(((Organization) user));
        }
        Customizer.end("Информация о текущем пользователе");

    }

    public void userInfoBylogin(User user) throws InterruptedException {
        Customizer.start("Информация о пользователе по логину");
        if(Objects.isNull(userAuthentication.getCurUser())){
            System.err.println("Вы не авторизованы");
            return;
        }
        System.out.print("Введите логин пользователя: ");
        String login = scanner.next();
        Optional<User> byUserLogin = userDate.getByUserLogin(login);
        if(byUserLogin.isPresent()){
            User user1 = byUserLogin.get();
            if(user.getUserType().equals(UserType.PERSON)){
                System.out.println(((Person) user1));
            }else{
                System.out.println(((Organization) user1));
            }
        }else{
            System.err.printf("Пользователь с логином %s не найден\n",login);
            Thread.sleep(100);
        }
        Customizer.end("Информация о пользователе по логину");
    }

    public void userInfoAll(User user) throws InterruptedException {
        Customizer.start("Информация по всем пользователям");
        if(Objects.isNull(userAuthentication.getCurUser())){
            System.err.println("Вы не авторизованы");
            Thread.sleep(100);
            return;
        }
        Customizer.start("Все пользователи");
        List <User> allUser = userDate.getAllUser();
        if(allUser.isEmpty()){
            System.err.println("Зарегистрированных пользователей нету");
            return;
        }
        for(User us : allUser){
            if(us.getUserType().equals(UserType.PERSON)){
                System.out.println(((Person) us));
            }else{
                System.out.println(((Organization) user));
            }
        }
        Customizer.end("Все пользователи");
    }
}
