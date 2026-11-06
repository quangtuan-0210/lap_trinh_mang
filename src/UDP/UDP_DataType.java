package UDP;
import java.util.*;
import java.io.*;
import java.net.*;


public class UDP_DataType {
    public static void main(String []args){
        String serverIp="36.50.135.242";
        int port=2207;

        try(DatagramSocket socket=new DatagramSocket()){
            socket.setSoTimeout(5000);
            InetAddress serverAdd=InetAddress.getByName(serverIp);

            //a
            String request=";B23DCCN883;hzCsXl6c";
            byte[] sendData=request.getBytes();
            DatagramPacket sendPacket=new DatagramPacket(sendData, sendData.length,serverAdd,port);
            socket.send(sendPacket);

            //b
            byte[] receiveData=new byte[2048];
            DatagramPacket receivePacket=new DatagramPacket(receiveData, receiveData.length);
            String response=new String(receivePacket.getData(),0,receivePacket.getLength()).trim();
            socket.receive(receivePacket);
            //c

            String[] part=response.split(";");
            String requestID=part[0];
            int n=Integer.parseInt(part[1].trim());

            boolean[] vs=new boolean[n+1];
            if(part.length>2 && !part[2].trim().isEmpty()){
                String[] num=part[2].split(",");
                for(String s:num) {
                    int x = Integer.parseInt(s.trim());
                    if(x>=1 && x<=n) vs[x]=true;
                }
            }

            List<String> miss=new ArrayList<>();
            for(int i=1;i<=n;i++){
                if(!vs[i]){
                    miss.add(String.valueOf(i));
                }
            }

            String str=String.join(",",miss);
            String result=requestID+";"+str;

            byte[] resultData=result.getBytes();
            DatagramPacket resultPacket=new DatagramPacket(resultData,resultData.length,serverAdd,port);
            socket.send(resultPacket);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
