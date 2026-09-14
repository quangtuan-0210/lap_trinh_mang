package TCP;

import java.net.Socket;
import java.util.*;
import java.io.*;

public class TCP_Object_Stream2 {
    public static void main(String[] args){
        String serverIp="36.50.135.242";
        String studentCode="B23DCCN883";
        int port=2209;
        String qCode="Be8gUBDm";

        try(Socket socket =new Socket(serverIp,port)){
            socket.setSoTimeout(5000);

            ObjectOutputStream out=new ObjectOutputStream(socket.getOutputStream());
            out.flush();
            ObjectInputStream in=new ObjectInputStream(socket.getInputStream());

            //a
            String request=studentCode+";"+qCode;
            out.writeObject(request);
            out.flush();

            //b
            Customer customer=(Customer) in.readObject();

            //c
            String[] s=customer.getName().trim().toLowerCase().split("\\s+");
            String last=s[s.length-1].toUpperCase();
            StringBuilder sb= new StringBuilder();
            for(int i=0;i<s.length-1;i++){
                String w=s[i];
                sb.append(Character.toUpperCase(w.charAt(0))).append(w.substring(1));
                if(i<s.length-2){
                    sb.append(" ");
                }
            }
            String formatName=last+", "+sb.toString();
            customer.setName(formatName);

            StringBuilder userSb=new StringBuilder();
            for(int i=0;i<s.length-1;i++){
                userSb.append(s[i].charAt(0));
            }
            userSb.append(s[s.length-1]);
            customer.setUserName(userSb.toString().toLowerCase());

            String[] dobS=customer.getDayOfBirth().trim().split("-");
            String FMdob=dobS[1]+"/"+dobS[0]+"/"+dobS[2];
            customer.setDayOfBirth(FMdob);

            out.writeObject(customer);
            out.flush();

            //d
            in.close();
            out.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
