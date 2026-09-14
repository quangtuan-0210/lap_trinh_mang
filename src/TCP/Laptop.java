package TCP;
import java.io.*;

public class Laptop implements Serializable {
    private static final long serialVersionUID = 20150711L;
    private int id,quantity;
    private String code,name;

    public Laptop(int id,String code,String name,int quantity){
        this.id=id;
        this.code=code;
        this.name=name;
        this.quantity=quantity;
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString(){
        return "Laptop{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                '}';
        }

}
