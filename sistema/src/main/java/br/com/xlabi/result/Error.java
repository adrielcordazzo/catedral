package br.com.xlabi.result;

import java.io.Serializable;

public class Error implements Serializable{

	private static final long serialVersionUID = 1L;
	private String field;
    private String message;

    public Error(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public Error() {
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
