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

            BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter out=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            //a.
            String request=studentCode+";"+qCode;
            out.write(request);
            out.newLine();
            out.flush();
            System.out.println("1");

            //b
            char[] buffer=new char[2048];
            int readB=in.read(buffer);
            if(readB!=-1){
                String response=new String(buffer,0,readB);
                System.out.println("2");

                //c
                String[] parts=response.split(",");
                int n=parts.length;
                int[] a=new int[n];
                for(int i=0;i<n;i++){
                    a[i]=Integer.parseInt(parts[i]);
                }

                int max1 =Integer.MIN_VALUE;
                for(int i=0;i<n;i++){
                    if(a[i]> max1){
                        max1 =a[i];
                    }
                }
                int max2=Integer.MIN_VALUE;
                int pos=-1;
                for(int i=0;i<n;i++){
                    if(a[i]<max1 && a[i]>max2){
                        max2 =a[i];
                        pos=i;
                    }
                }
                String result=max2+","+pos;
                out.write(result);
                out.newLine();
                out.flush();
                System.out.println("3");
            }

            //d
            in.close();
            out.close();
            System.out.println("4. Hoan thanh");

        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Error");
        }
    }
}
