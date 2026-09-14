package TCP;

import java.io.*;
import java.net.Socket;


public class TCP_DataStream2 {
    public static String Ceasar(String n,int s){
        StringBuilder sb=new StringBuilder();
        int dich=(s%26+26)%26;

        for(char c: n.toCharArray()){
            if(c>='a' && c<='z'){
                char giaiMa=(char) ('a'+(c-'a'-dich+26)%26);
                sb.append(giaiMa);
            }else if(c>='A' && c<='Z'){
                char giaiMa=(char)('A'+(c-'A'-dich+26)%26);
                sb.append(giaiMa);
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args){
        String serverIp="36.50.135.242";
        String studentCode="B23DCCN883";
        int port=2207;
        String qCode="fvEk35Yx";

        try(Socket socket=new Socket(serverIp,port)){
            socket.setSoTimeout(5000);

            DataInputStream in=new DataInputStream(socket.getInputStream());
            DataOutputStream out=new DataOutputStream(socket.getOutputStream());

            //a
            String request=studentCode+";"+qCode;
            out.writeUTF(request);
            out.flush();

            //b
            String ma=in.readUTF();
            int s=in.readInt();


            //c
            String giaiMa= Ceasar(ma,s);
            out.writeUTF(giaiMa);
            out.flush();

            //d
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
