package search;

public class Request {
    private String tableName;
    private String firstName;
    private String email;
    private String from;
    private String to;

//    public Request(String tableName, String firstName, String email, String from, String to) {
//        this.tableName = tableName;
//        this.firstName = firstName;
//        this.email = email;
//        this.from = from;
//        this.to = to;
//    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTo() {
        return to;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getFrom() {
        return from;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }
}