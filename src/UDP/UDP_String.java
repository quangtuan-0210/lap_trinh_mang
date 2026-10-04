package UDP;

import java.net.*;
import java.util.*;
import java.io.*;

public class UDP_String {
    public static void main(String[] args) throws Exception{
        String serverIp="36.50.135.242";
        int port=2208;

        DatagramSocket socket=new DatagramSocket();
        socket.setSoTimeout(5000);
        InetAddress serverAdd=InetAddress.getByName(serverIp);

        //a
        String request=";B23DCCN883;gXGmLWTv";
        byte[] sendData=request.getBytes();
        socket.send(new DatagramPacket(sendData,sendData.length,serverAdd,port));

        //b
        byte[] buffer=new byte[1024];
        DatagramPacket response=new DatagramPacket(buffer,buffer.length);
        socket.receive(response);
        String responseStr=new String(response.getData(),0,response.getLength()).trim();
        //tách data với requestId
        int sep=responseStr.indexOf(";");
        String requestId=responseStr.substring(0,sep);
        String data=responseStr.substring(sep+1).trim();

        //c
        int[] cnt=new int[256];
        for(int i=0;i<data.length();i++){
            cnt[data.charAt(i)]++;
        }

        char best=data.charAt(0);
        for(int i=0;i<data.length();i++){
            char c=data.charAt(i);
            if(cnt[c]>cnt[best]){
                best=c;
            }
        }
        String result=requestId+";"+best+":";
        for(int i=0;i<data.length();i++){
            if(data.charAt(i)==best){
                result+=(i+1)+",";
            }
        }
        byte[] resultData=result.getBytes();
        socket.send(new DatagramPacket(resultData,resultData.length,serverAdd,port));


        socket.close();
    }
}
