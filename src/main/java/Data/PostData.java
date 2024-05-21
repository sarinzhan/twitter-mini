package Data;

import entities.Post;
import utils.Customizer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PostData {
    private List<Post> postList;
    private Long id = 0L;
    public PostData(){
        this.postList = new ArrayList<>();
    }
    public Long addPost(Post post){
        post.setId(++this.id);
        postList.add(post);
        return this.id;
    }
    public List<Post> getAllPosts(){
        if(postList.isEmpty()){
            return null;
        }
        return postList;
    }
    public Post getPostById(Long id){
        return postList.stream().filter(x -> x.getId().equals(id)).findAny().get();
    }
    public void save() throws IOException, InterruptedException {
        Customizer.start("Сохранение публикации");

        try(FileOutputStream fos = new FileOutputStream("post.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos)){
        oos.writeObject(this.postList);
        if(postList.isEmpty()){
            throw new IOException();
        }
        }catch (Exception e){
            System.err.println("Нечего сохранять");
            Thread.sleep(100);
            return;
        }

        Customizer.end("Публикации сохранены: " + postList.size());
    }
    public void load() throws IOException, ClassNotFoundException, InterruptedException {
        Customizer.start("Загрузка публикации");
        try(FileInputStream fis = new FileInputStream("post.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);){
            this.postList= (List<Post>) ois.readObject();
            if(postList.isEmpty()){
                throw new FileNotFoundException();
            }
        }catch (Exception e){
            System.err.println("Нечего грузить");
            Thread.sleep(100);
            return;
        }
        Customizer.end("Публикации загружены: " + postList.size());
    }
}
