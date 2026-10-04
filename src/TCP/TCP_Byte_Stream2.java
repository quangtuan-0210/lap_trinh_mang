package TCP;

import java.io.*;
import java.net.Socket;

public class TCP_Byte_Stream2 {
    public static void main(String[] args){
        String serverIp="36.50.135.242";
        int port=2206;
        String studentCode="B23DCCN883";
        String qCode="zHgiEw1Q";

        try(Socket socket= new Socket(serverIp,port)){
            socket.setSoTimeout(5000);

            InputStream in=socket.getInputStream();
            OutputStream out=socket.getOutputStream();

            //a.
            String request=studentCode+";"+qCode;
            out.write(request.getBytes());
            out.flush();

            //b
            byte[] buffer=new byte[2048];
            int x=in.read(buffer);
            if(x==-1) return;

            String response=new String(buffer,0,x);

            //c
            String[] s=response.split(",");
            int[] a=new int[s.length];
            for(int i=0;i<s.length;i++){
                a[i]=Integer.parseInt(s[i]);
            }
            int Max=Integer.MIN_VALUE;
            for(int i=0;i<a.length;i++){
                if(a[i]>Max) Max=a[i];
            }
            int secondMax=Integer.MIN_VALUE;
            int pos=-1;
            for(int i=0;i<a.length;i++){
                if(a[i]<Max && a[i]>secondMax){
                    secondMax=a[i];
                    pos=i;
                }
            }
            String result=secondMax+","+pos;
            out.write(result.getBytes());
            out.flush();
            //d
            in.close();
            out.close();


        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
