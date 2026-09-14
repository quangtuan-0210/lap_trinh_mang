package TCP;
import java.io.*;

public class Customer {
    private static final long serialVersionUID = 20170711L;
    private int id;
    private String code,name,DOB,username;

    public Customer(int id, String code, String name, String DOB, String username){
        this.id=id;
        this.code=code;
        this.name=name;
        this.DOB=DOB;
        this.username=username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", DOB='" + DOB + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
