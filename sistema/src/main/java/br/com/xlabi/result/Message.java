package br.com.xlabi.result;

public class Message {

    private Severity severity;
    private String text;

    public Message(Severity severity, String text) {
        this.severity = severity;
        this.text = text;
    }

    public Severity getSeverity() {
        return severity;
    }

    public void setSeverity(Severity severity) {
        this.severity = severity;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
