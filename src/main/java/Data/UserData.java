package Data;

import entities.Person;
import entities.Post;
import entities.User;
import utils.Customizer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public  class UserData <T extends  User>{
    private List<T> userList = new ArrayList<>();
    private Long id = 0L;

    public UserData(){
    }
    public List<T> getAllUser(){
        return userList.isEmpty() ?  new ArrayList<T>() : userList;
    }
    public Optional<T> getByUserId(Long id){
        return userList.stream().filter(x -> x.getId() == id).findAny();
    }
    public Optional<T> getByUserLogin(String login){
       return userList.stream().filter(x -> x.getLogin().equals(login)).findFirst();
    }
    public Long addUser(T user){
        user.setId(++this.id);
        this.userList.add(user);
        return this.id;
    }

    public boolean deleteUserByid(Long id){
        Optional<T> first = userList.stream().filter(x -> x.getId() == id).findFirst();
        if(first.isPresent()){
            T t = first.get();
            return userList.remove(t);
        }
        return false;
    }
    public void save() throws IOException, InterruptedException {
        Customizer.start("Сохранение пользователей");

        try(FileOutputStream fos = new FileOutputStream("user.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);){
            if(userList.isEmpty()){
                throw  new IOException();
            }
            oos.writeObject(this.userList);
        }catch (Exception e){
            System.err.println("Некого сохранять");
            Thread.sleep(100);
            return;
        }

        Customizer.end("Пользователи сохранены: " + userList.size());
    }
    public void load() throws IOException, ClassNotFoundException, InterruptedException {
        Customizer.start("Загрузка пользователей");
        try(FileInputStream fis = new FileInputStream("user.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);){
            this.userList= (List<T>) ois.readObject();
            if(userList.isEmpty()){
                throw  new FileNotFoundException();
            }
        }catch (Exception e){
            System.err.println("Некого грузить");
            Thread.sleep(100);
            return;
        }
        Customizer.end("Пользователи загружены: " + userList.size());
    }
}
