package UDP;
import java.net.*;
import java.util.*;
import java.io.*;
public class UDP_Data_type2 {
    public static void main(String[] args) throws Exception{
        String serverIp="36.50.135.242";
        int port=2207;

        DatagramSocket socket=new DatagramSocket();
        socket.setSoTimeout(5000);
        InetAddress ip=InetAddress.getByName(serverIp);

        //a
        String request=";B23DCCN883;FGw6pu8D";
        byte[] sendData=request.getBytes();
        socket.send(new DatagramPacket(sendData,sendData.length,ip,port));

        //b
        byte[] buffer=new byte[2048];
        DatagramPacket response=new DatagramPacket(buffer,buffer.length);
        socket.receive(response);
        String responseData=new String(response.getData(),0,response.getLength());

        int sep=responseData.indexOf(";");
        String requestId=responseData.substring(0,sep);
        String data=responseData.substring(sep+1);

        String[] num=data.split(",");
        int max=Integer.parseInt(num[0].trim());
        int min=max;
        for(int i=0;i<num.length;i++){
            int x=Integer.parseInt(num[i].trim());
            if(x<min) min=x;
            if(x>max) max=x;
        }
        String result=requestId+";"+max+","+min;
        byte[] resultData=result.getBytes();
        socket.send(new DatagramPacket(resultData,resultData.length,ip,port));

        socket.close();
    }
}
