package UDP;

import java.net.*;
import java.util.*;
import java.io.*;

public class UDP_String2 {
    public static void main(String[] args) throws Exception {
        String serverIp = "36.50.135.242";
        int port = 2208;

        DatagramSocket socket = new DatagramSocket();
        socket.setSoTimeout(5000);
        InetAddress serverAdd = InetAddress.getByName(serverIp);

        //a
        String request = ";B23DCCN883;FrDxxWVy";
        byte[] sendData = request.getBytes();
        socket.send(new DatagramPacket(sendData, sendData.length, serverAdd, port));

        //b
        byte[] buffer=new byte[1024];
        DatagramPacket response=new DatagramPacket(buffer, buffer.length);
        socket.receive(response);

        String responseStr=new String(response.getData(),0,response.getLength()).trim();
        int sep=responseStr.indexOf(";");
        String requestId=responseStr.substring(0,sep);
        String data=responseStr.substring(sep+1);

        String[] s=data.toLowerCase().trim().split("\\s+");
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<s.length;i++){
            String w=s[i];
            sb.append(Character.toUpperCase(w.charAt(0))+w.substring(1));
            if(i<s.length-1){
                sb.append(" ");
            }
        }
        String result=requestId+";"+sb.toString();
        byte[] resultData=result.getBytes();
        socket.send(new DatagramPacket(resultData,resultData.length,serverAdd,port));

        socket.close();
    }
}