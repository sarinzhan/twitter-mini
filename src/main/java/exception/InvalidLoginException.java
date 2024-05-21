package exception;

public class InvalidLoginException extends  Exception{
    public InvalidLoginException(String text) {
        super(text);
    }
}
