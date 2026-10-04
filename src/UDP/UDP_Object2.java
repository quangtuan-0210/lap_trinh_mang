package UDP;

import java.net.*;
import java.io.*;
import java.util.*;

public class UDP_Object2 {
    public static void main(String[] args) throws Exception {
        String serverIp="36.50.135.242";
        int port=2209;

        DatagramSocket socket=new DatagramSocket();
        socket.setSoTimeout(10000);
        InetAddress ip=InetAddress.getByName(serverIp);

        //a
        String request=";B23DCCN883;dv6J4rgN";
        byte[] sendData=request.getBytes();
        socket.send(new DatagramPacket(sendData,sendData.length,ip,port));

        //b
        byte[] buffer=new byte[2048];
        DatagramPacket response=new DatagramPacket(buffer,buffer.length);
        socket.receive(response);

        byte[] data=response.getData();
        int n=response.getLength();

        String requestId=new String(data,0,8);
        ObjectInputStream in=new ObjectInputStream(new ByteArrayInputStream(data,8,n-8));
        Customer customer=(Customer) in.readObject();

        //c
        String[] s=customer.getName().trim().toLowerCase().split("\\s+");
        String last=s[s.length-1].toUpperCase();
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<s.length-1;i++){
            String w=s[i];
            sb.append(Character.toUpperCase(w.charAt(0)) + w.substring(1));
            if(i<s.length-2){
                sb.append(" ");
            }
        }
        String formatName=last+", "+sb.toString();
        customer.setName(formatName);

        StringBuilder sb2=new StringBuilder();
        for(int i=0;i<s.length-1;i++){
            sb2.append(s[i].charAt(0));
        }
        sb2.append(last.toLowerCase());
        customer.setUserName(sb2.toString());

        String[] dobS=customer.getDayOfBirth().trim().split("-");
        String Fdob=dobS[1]+"/"+dobS[0]+"/"+dobS[2];
        customer.setDayOfBirth(Fdob);

        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        bos.write(requestId.getBytes());
        ObjectOutputStream out=new ObjectOutputStream(bos);
        out.writeObject(customer);
        out.flush();

        byte[] result=bos.toByteArray();
        socket.send(new DatagramPacket(result,result.length,ip,port));


        socket.close();
    }
}
