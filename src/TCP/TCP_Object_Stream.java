package TCP;

import java.io.*;
import java.net.Socket;
import java.util.*;
import java.lang.*;


public class TCP_Object_Stream {

    public static void main(String[] args){
        String serverIP="36.50.135.242";
        String studentCode="B23DCCN883";
        int port=2209;
        String qCode="qkyweNap";

        try(Socket socket= new Socket(serverIP,port)){
            socket.setSoTimeout(5000);

            ObjectOutputStream out= new ObjectOutputStream(socket.getOutputStream());
            out.flush();
            ObjectInputStream in= new ObjectInputStream(socket.getInputStream());

            //a
            String request=studentCode+";"+qCode;
            out.writeObject(request);
            out.flush();

            //b
            Laptop laptop=(Laptop) in.readObject();

            //c
            String[] s=laptop.getName().trim().split("\\s+");
            if(s.length>1){
                String tmp=s[0];
                s[0]=s[s.length-1];
                s[s.length-1]=tmp;
            }
            String fixName=String.join(" ",s);
            laptop.setName(fixName);

            String qlt=String.valueOf(laptop.getQuantity());
            String resqlt= new StringBuilder(qlt).reverse().toString();
            int fixQlt=Integer.parseInt(resqlt);
            laptop.setQuantity(fixQlt);

            out.writeObject(laptop);
            out.flush();

            in.close();
            out.close();



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
