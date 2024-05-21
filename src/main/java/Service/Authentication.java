package Service;

import Data.UserData;
import entities.Organization;
import entities.Person;
import entities.User;
import entities.UserType;
import utils.Customizer;

import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;

public class Authentication <T extends  User>{
    private   Scanner scanner = new Scanner(System.in);
    private UserData<T> userData;
    private User curUser;
    private String password;

    public Authentication(UserData<T> userData) {
        this.userData = userData;
    }

    public User getCurUser() {
        return curUser;
    }


    public  void login() throws InterruptedException {
        Customizer.start("Авторизация");
        if(Objects.nonNull(curUser)){
            String type;
            if(curUser.getUserType().equals(UserType.PERSON)){
                type = "[PERSON] " + ((Person)curUser).getFirstName() + " " +((Person)curUser).getSecondName();
            }else{
                type = "[ORGANIZATION] " + ((Organization)curUser).getName();
            }
            System.err.println("Вы уже авторизованы: " + type);
            Thread.sleep(150);
            return;
        }
        System.out.print("Логин: ");
        String login = scanner.next();
        System.out.print("Пароль: ");
         this.password = scanner.next();
        Optional<T> byUserLogin = userData.getByUserLogin(login);
        if (byUserLogin.isPresent()) {
            this.userIsPresent(byUserLogin.get());
        } else {
            System.out.println("Такого пользователя не существует");
        }

    }
    private void userIsPresent(User user) {
        String type;
        if(user.getUserType().equals(UserType.PERSON)){
            type = "[PERSON] " + ((Person)user).getFirstName() + " " +((Person)user).getSecondName();
        }else{
            type = "[ORGANIZATION] " + ((Organization)user).getName();
        }
        if(user.getPassword().equals(this.password)) {
            System.out.println("Успешная авторизация");
            Customizer.start("Добро пожаловать, " + type);
            this.curUser = user;
        }else{
            System.out.println("Неверный пароль");
        }
    }
    public void logout(){
        if(Objects.isNull(curUser)){
            System.err.println("Вы итак не авторизованы :)");
            return;
        }
        curUser = null;
        Customizer.end("Теперь вы не авторизованы");
    }
}
