package TCP;

import java.io.*;
import java.net.Socket;

public class TCP_Data_Stream {
    public static void main(String[] args){
        String serverIp="36.50.135.242";
        int port=2207;
        String studentCode="B23DCCN883";
        String qcode="iod26AQD";

        try(Socket socket=new Socket(serverIp,port)){
            socket.setSoTimeout(5000);

            DataInputStream in=new DataInputStream(socket.getInputStream());
            DataOutputStream out=new DataOutputStream(socket.getOutputStream());

            //a
            String request=studentCode+";"+qcode;
            out.writeUTF(request);
            out.flush();

            //b
            int a=in.readInt();
            int b=in.readInt();

            //c
            int sum=a+b;
            int tich=a*b;
            out.writeInt(sum);
            out.writeInt(tich);
            out.flush();

            //d
            in.close();
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
