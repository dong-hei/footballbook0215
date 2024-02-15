package dk.footballbook.footballbookstart.handler.ex;

public class CustomApiEx extends RuntimeException{

    private static final long serialVersionUID=1L;

    public CustomApiEx(String message) {
        super(message);
    }

}
