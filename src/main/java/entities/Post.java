package entities;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;
import java.util.List;

public class Post implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Long id;
    private User author;
    private String theme;
    private String textPost;
    private List<String> tags;
    private LocalDateTime postDate;

    public Post() {
    }

    public Post(Long id, User author, String theme, String textPost, List<String> tags, LocalDateTime postDate) {
        this.id = id;
        this.author = author;
        this.theme = theme;
        this.textPost = textPost;
        this.tags = tags;
        this.postDate = postDate;
    }

    public Long getId() {
        return id;
    }

    public Post setId(Long id) {
        this.id = id;
        return this;
    }

    public User getAuthor() {
        return author;
    }

    public Post setAuthor(User author) {
        this.author = author;
        return this;
    }

    public String getTheme() {
        return theme;
    }

    public Post setTheme(String theme) {
        this.theme = theme;
        return this;
    }

    public String getTextPost() {
        return textPost;
    }

    public Post setTextPost(String textPost) {
        this.textPost = textPost;
        return this;
    }

    public List<String> getTags() {
        return tags;
    }

    public Post setTags(List<String> tags) {
        this.tags = tags;
        return this;
    }

    public LocalDateTime getPostDate() {
        return postDate;
    }

    public Post setPostDate(LocalDateTime postDate) {
        this.postDate = postDate;
        return this;
    }

    @Override
    public String toString() {
        String author;
        if(this.author.getUserType().equals(UserType.PERSON)){
            author = String.format("[PERSON] %s %s",((Person)this.author).getFirstName(),((Person)this.author).getSecondName());
        }else{
            author = String.format("[ORGANIZATION] %s",((Organization)this.author).getName());
        }
        return String.format("Публикация: {\n\tАвтор: %s;\n\tСоздано: %s;\n\tТема: %s\n\tТекст: %s;\n\tТэги: %s;\n}\n",
                                        author,this.postDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),this.theme,this.textPost,this.tags.toString().replaceAll("\\[|\\]", ""));
    }
}
