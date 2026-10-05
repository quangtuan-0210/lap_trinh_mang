package UDP;
import java.net.*;
import java.util.*;
import java.io.*;


public class UDP_Data_type {
    public static void main(String[] args) throws Exception {
        String serverIp="36.50.135.242";
        int port=2207;

        DatagramSocket socket=new DatagramSocket();
        socket.setSoTimeout(5000);
        InetAddress ip=InetAddress.getByName(serverIp);

        //a
        String request=";B23DCCN883;hzCsXl6c";
        byte[] sendData=request.getBytes();
        socket.send(new DatagramPacket(sendData,sendData.length,ip,port));

        //b
        byte[] buffer=new byte[2048];
        DatagramPacket response=new DatagramPacket(buffer,buffer.length);
        socket.receive(response);

        String responseStr=new String(response.getData(),0,response.getLength()).trim();
        int sep=responseStr.indexOf(";");
        String requestId=responseStr.substring(0,sep);
        String data=responseStr.substring(sep+1);

        //c
        String[] part=data.split(";");
        int n=Integer.parseInt(part[0]);
        String[] s=part[1].split(",");

        boolean[] vs=new boolean[n+1];
        for(String x:s){
            x=x.trim();
            if(!x.isEmpty()) vs[Integer.parseInt(x)]=true;
        }
        StringBuilder sb=new StringBuilder();
        for(int i=1;i<=n;i++){
            if(!vs[i]){
                if(sb.length()>0) sb.append(",");
                sb.append(i);
            }
        }
        String result=requestId+";"+sb.toString();
        byte[] resultData=result.getBytes();
        socket.send(new DatagramPacket(resultData,resultData.length,ip,port));

        socket.close();
    }
}
