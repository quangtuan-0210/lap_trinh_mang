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

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //a.
            String request = studentCode + ";" + qCode;
            out.write(request);
            out.newLine();
            out.flush();
            System.out.println("1. Da gui request: "+request);

            //b
            String response=in.readLine();
            System.out.println("2. Da gui response: "+response);

            //c
            if(response != null && !response.isEmpty()){
                String[] domains=response.split(",");
                List<String> eduDomains=new ArrayList<>();
                for(String d:domains){
                    d=d.trim();
                    if(d.endsWith(".edu")){
                        eduDomains.add(d);
                    }
                }
                String result=String.join(", ", eduDomains);
                out.write(result);
                out.newLine();
                out.flush();
                System.out.println("3. Da gui result: "+result);
            }


            //d
            in.close();
            out.close();
            System.out.println("4. Hoan thanh");


        }catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error");
        }
    }
}
