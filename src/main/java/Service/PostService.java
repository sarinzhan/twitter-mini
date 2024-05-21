package Service;

import Data.PostData;
import Data.UserData;
import entities.*;
import utils.Customizer;

import java.time.LocalDateTime;
import java.util.*;

public class PostService {
    private PostData postData;
    private UserData<User> userData;
    private Authentication<User> authentication;
    private Scanner scanner;
    private User user;

    public PostService(PostData postData, UserData<User> userData, Authentication<User> authentication) {
        this.postData = postData;
        this.userData = userData;
        this.authentication = authentication;
        this.scanner = new Scanner(System.in);
    }

    public void addNewPost() throws InterruptedException {
        Customizer.start("Создание новой публикации");
        if(!this.isAuth()){
            return;
        }
        Post post = new Post();

        post.setAuthor(this.user);
        post.setPostDate(LocalDateTime.now());
        System.out.print("Введите тему публикации: ");
        post.setTheme(scanner.nextLine());
        System.out.print("Введите текст публикации: ");
        post.setTextPost(scanner.nextLine());
        System.out.print("Введите теги публикации(указывать через пробелы): ");
        List<String> tags = Arrays.stream(scanner.nextLine().replaceAll(" ","").split(",")).map(x -> "#" + x).toList();
        post.setTags(tags);
        System.out.println("Создана новая публикация: ");
        System.out.println(postData.getPostById(postData.addPost(post)));
        Customizer.end("Создание новой публикации");
    }
    public void getForCurrUser(){
        Customizer.start("Ваши публикации");
        if(!this.isAuth()){
            return;
        }
        List<Post> list = postData.getAllPosts().stream().filter(x -> x.getAuthor().equals(this.user)).toList();
        if(list.isEmpty()){
            System.out.println("У вас нету публикации");
        }else{
            list.forEach(System.out::println);
        }
        Customizer.end("Ваши публикации");
    }
    public void getPostByUser(){
        Customizer.start("Публикации по логину пользователя");
        if(!this.isAuth()){
            return;
        }
        System.out.print("Введите логин пользователя: ");
        String login = scanner.next();
        Optional<User> byUserLogin = userData.getByUserLogin(login);
        if(byUserLogin.isEmpty()){
            System.err.println("Пользователя с данным логином не существует");
            return;
        }
        User u1 = byUserLogin.get();
        postData.getAllPosts().stream().filter(x -> x.getAuthor().equals(u1)).toList().forEach(System.out::println);
        Customizer.end("Публикации по логину пользователя");
    }
    public void getAllPost() {
        Customizer.start("Все публикации");
        if(!isAuth()){
            return;
        }
        List<Post> allPosts = postData.getAllPosts();
        if(allPosts.isEmpty()){
            System.err.println("Публикации нема");
        }else{
            allPosts.forEach(System.out::println);
        }
        Customizer.end("Все публикации");
    }

    public void getPostByTag() {
        Customizer.start("Публикации по тегу");
        if(!isAuth()){
            return;
        }
        List<Post> allPosts = postData.getAllPosts();
        if(allPosts.isEmpty()){
            System.err.println("Публикации нема");
        }else{
            System.out.print("Введите тег: ");
            String tag = scanner.next();
            List<Post> list = allPosts.stream().filter(x -> x.getTags().stream().anyMatch(a -> a.equals(tag))).toList();
            if(list.isEmpty()){
                System.err.println("Публикации с таким тэгом нема :(");
                return;
            }
            list.forEach(System.out::println);
        }

        Customizer.end("Публикации по тегу");
    }

    public void getPostByUserType() {
        Customizer.start("Публикации по типу пользователя");
        if(!isAuth()){
            return;
        }
        int n = 1;
        List<Post> allPosts = postData.getAllPosts();
        if(allPosts.isEmpty()){
            System.err.println("Публикации нема :(");
            return;
        }else{
            System.out.print("Введите тип пользователя(0 - человек, 1 - организация): ");
            Integer type = scanner.nextInt();
            if(type.equals(1)){
                List postList = allPosts.stream().filter(x -> x.getAuthor().getUserType().equals(UserType.PERSON)).toList();
                if(postList.isEmpty()){
                    System.err.println("Публикации с данным тэгом нема :(");
                    return;
                }else{
                    postList.forEach(System.out::println);
                }
            }else{
                List<Post> postList = allPosts.stream().filter(x -> x.getAuthor().getUserType().equals(UserType.ORGANIZATION)).toList();
                if(postList.isEmpty()){
                    System.err.println("Публикации с данным тэгом нема :(");
                    return;
                }else{
                    postList.forEach(System.out::println);
                }
            }
        }
        Customizer.end("Публикации по типу пользователя");
    }

    private boolean isAuth(){
        this.user = null;
        if(Objects.isNull( this.authentication.getCurUser())){
            System.err.println("Вы не авторизованы");
            return false;
        }
        this.user = authentication.getCurUser();
        return  true;
    }
}
