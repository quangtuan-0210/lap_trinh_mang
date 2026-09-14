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
                String response=new String(buffer,0,readB).trim();
                System.out.println("2.");

                //c
                String[] parts= response.split(",");
                int n=parts.length;
                int[] a=new int[n];
                for(int i=0;i<n;i++){
                    a[i]=Integer.parseInt(parts[i].trim());
                }
                Arrays.sort(a);

                int MinDiff=Integer.MAX_VALUE;
                int num1=0,num2=0;

                for(int i=1;i<n;i++){
                    int diff=a[i]-a[i-1];
                    if(diff<MinDiff){
                        MinDiff=diff;
                        num1=a[i-1];
                        num2=a[i];
                    }
                }

                String result=MinDiff+","+num1+","+num2;
                System.out.println("c");
                out.write(result);
                out.newLine();
                out.flush();
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
