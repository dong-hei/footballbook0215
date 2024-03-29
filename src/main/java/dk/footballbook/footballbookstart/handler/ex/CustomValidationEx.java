package dk.footballbook.footballbookstart.handler.ex;

import java.util.Map;

public class CustomValidationEx extends RuntimeException{

    private static final long serialVersionUID=1L;

    private Map<Object, Object> errorMap;

    public CustomValidationEx(String message, Map<Object, Object> errorMap) {
        super(message);
        this.errorMap = errorMap;
    }

    public Map<Object, Object> getErrorMap() {
        return errorMap;
    }
}
