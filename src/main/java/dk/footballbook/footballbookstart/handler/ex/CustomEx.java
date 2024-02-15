package dk.footballbook.footballbookstart.handler.ex;

public class CustomEx extends RuntimeException{

    private static final long serialVersionUID=1L;


    public CustomEx(String message) {
        super(message);
    }

}
