package TCP;

import java.io.*;
import java.util.*;
import java.net.Socket;

public class TCP_Character_Stream {
    public static void main(String[] args) {
        String serverIp="36.50.135.242";
        int port=2208;
        String studentCode="B23DCCN883";
        String qCode="YPKy96Qm";

        try(Socket socket =new Socket(serverIp,port)) {
            socket.setSoTimeout(5000);

            BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter out=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            //a
            String request=studentCode+";"+qCode;
            out.write(request);
            out.flush();

            //b
            String response=in.readLine();

            //c
            if(response!=null && !response.isEmpty()){
                String[] domain=response.split(",");
                List<String> edu=new ArrayList<>();
                for(String s: domain){
                    s=s.trim();
                    if(s.endsWith("edu")){
                        edu.add(s);
                    }
                }
                String result=String.join(",",edu);
                out.write(result);
                out.flush();
            }
            //d
            in.close();
            out.close();


        }catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error");
        }
    }
}
