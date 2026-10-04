package TCP;

import java.io.*;
import java.net.Socket;
import java.util.*;

public class TCP_Byte_Stream {
    public static void main(String[] args){
        String serverIp="36.50.135.242";
        int port=2206;
        String studentCode="B23DCCN883";
        String qCode="I7U2RJSl";

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
                a[i]=Integer.parseInt(s[i].trim());
            }

            Arrays.sort(a);
            int minDiff=Integer.MAX_VALUE;
            int num1=0,num2=0;
            for(int i=0;i<a.length-1;i++){
                int diff=a[i+1]-a[i];
                if(diff<minDiff){
                    minDiff=diff;
                }
            }

            for(int i=a.length-2;i>=0;i--){
                if(a[i+1]-a[i]==minDiff){
                    num1=a[i];
                    num2=a[i+1];
                    break;
                }
            }
            String result=minDiff+","+num1+","+num2;
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
