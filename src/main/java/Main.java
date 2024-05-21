import Data.PostData;
import Data.UserData;
import Service.Authentication;
import Service.PostService;
import Service.UserService;
import entities.Person;
import entities.Post;
import entities.User;
import utils.Customizer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException {

        UserData<User> userData = new UserData<>();
        PostData postData = new PostData();

        Authentication<User> userAuthentication = new Authentication<>(userData);

        UserService userService = new UserService(userData,userAuthentication);
        PostService postService = new PostService(postData,userData,userAuthentication);

        Scanner scanner = new Scanner(System.in);


        Customizer.mainMenu();
        System.out.print("Хотите ли загрузить данные?(y/n)");
        String next2 = scanner.nextLine();
        if(next2.equals("y")){
            postData.load();
            userData.load();
        }
        System.out.print("Введите команду: ");
        String next = scanner.nextLine();
        while(true){
            if(next.equals("exit")){
                System.out.println("Хотите ли сохранить данные?(y/n)");
                String next1 = scanner.next();
                if(next1.equals("y")){
                    postData.save();
                    userData.save();
                }
                Customizer.end("До новых встреч");
                break;
            }else if(next.equals("register")){
                userService.registerUser();
            }else if(next.equals("login")){
                userAuthentication.login();
            }else if(next.equals("help")){
                Customizer.commands();
            }else if(next.equals("logout")){
                userAuthentication.logout();
            }else if(next.equals("info")){
                userService.currUserInfo(userAuthentication.getCurUser());
            }else if(next.equals("info by login")){
                userService.userInfoBylogin(userAuthentication.getCurUser());
            }else if(next.equals("info all")){
                userService.userInfoAll(userAuthentication.getCurUser());
            }else if(next.equals("add post")){
                postService.addNewPost();
            }else if(next.equals("my posts")){
                postService.getForCurrUser();
            }else if(next.equals("all posts")){
                postService.getAllPost();
            }else if(next.equals("post by tag")){
                postService.getPostByTag();
            }else if(next.equals("post by login")){
                postService.getPostByUser();
            }else if(next.equals("post by user type")){
                postService.getPostByUserType();
            }else{
                System.err.println("Похоже вы ввели неправильную команду");
                Thread.sleep(100);
                Customizer.commands();
            }
            System.out.print("Введите команду: ");
            next = scanner.nextLine();
        }

    }
}
