package game.model;

public class Civilian{
    public String fname;
    public String lname;
    public String occupation;
    public String expiration;
    public int age;
    public String dialog;
    
    public Civilian(String fname, String lname, String occupation, int age, String expiration, String dialog){
        this.fname = fname;
        this.lname = lname;
        this.occupation = occupation;
        this.age = age;
        this.expiration = expiration;
        this.dialog = dialog;
    }
}