package br.com.xlabi.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.xlabi.entity.geral.AbstractEntity;

public class Result implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<?> list;

	private Object data;
	private int countResult;

	private boolean error;
	private boolean valid = true;
	private List<Error> errors = new ArrayList<Error>();
	private List<Message> messages = new ArrayList<Message>();

	public Result() {
	}

	public void addInfoMessage(String text) {
		this.messages.add(new Message(Severity.INFO, text));
	}

	public void addWarningMessage(String text) {
		this.messages.add(new Message(Severity.WARNING, text));
	}

	public void addErrorMessage(String text) {
		this.messages.add(new Message(Severity.ERROR, text));
	}

	public void addSuccessMessage(String text) {
		this.messages.add(new Message(Severity.SUCCESS, text));
	}

	public void addError(String field, String message) {
		this.errors.add(new Error(field, message));
		this.error = errors.size() > 0;
		this.valid = errors.size() <= 0;
		
	}

	public Result(List<AbstractEntity> list) {
		this.list = list;
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

	public boolean isError() {
		return error;
	}

	public boolean isValid() {
		return !isError();
	}

	public List<Error> getErrors() {
		return errors;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public int getCountResult() {
		return countResult;
	}

	public void setCountResult(int countResult) {
		this.countResult = countResult;
	}

}
