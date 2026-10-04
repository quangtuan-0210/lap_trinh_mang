package UDP;
import java.net.*;
import java.util.*;
import java.io.*;


public class UDP_Object1 {
    public static void main(String[] args) throws Exception{
        String serverId="36.50.135.242";
        int port=2209;

        DatagramSocket socket=new DatagramSocket();
        socket.setSoTimeout(5000);
        InetAddress serverAdd=InetAddress.getByName(serverId);

        //a
        String request=";B23DCCN883;OCa7bqnv";
        byte[] sendData=request.getBytes();
        socket.send(new DatagramPacket(sendData,sendData.length,serverAdd,port));

        //b
        byte[] buffer=new byte[2048];
        DatagramPacket response=new DatagramPacket(buffer,buffer.length);
        socket.receive(response);

        byte[] data=response.getData();
        int n=response.getLength();

        String requestId=new String(data,0,8);
        ObjectInputStream in=new ObjectInputStream(new ByteArrayInputStream(data,8,n-8));
        Student student=(Student)in.readObject();


        //c
        String[] s=student.getName().trim().toLowerCase().split("\\s+");
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<s.length;i++){
            String w=s[i];
            sb.append(Character.toUpperCase(w.charAt(0))).append(w.substring(1));
            if(i<s.length-1){
                sb.append(" ");
            }
        }
        student.setName(sb.toString());

        String email=s[s.length-1];
        for(int i=0;i<s.length-1;i++){
            email+=s[i].charAt(0);
        }
        email+="@ptit.edu.vn";
        student.setEmail(email);

        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        bos.write(requestId.getBytes());
        ObjectOutputStream out=new ObjectOutputStream(bos);
        out.writeObject(student);
        out.flush();

        byte[] result=bos.toByteArray();
        socket.send(new DatagramPacket(result,result.length,serverAdd,port));



        socket.close();

    }
}
