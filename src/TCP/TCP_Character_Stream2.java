package TCP;

import java.io.*;
import java.util.*;
import java.net.Socket;

public class TCP_Character_Stream2 {
    public static void main(String[] args) {
        String serverIp="36.50.135.242";
        int port=2208;
        String studentCode="B23DCCN883";
        String qCode="HtwVc5wR";

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
            System.out.println("2");

            //c
            if(response!=null && !response.isEmpty()){}{
                Map<Character,Integer> countMap=new HashMap<>();
                for(char c: response.toCharArray()){
                    if(Character.isLetterOrDigit(c)){
                        countMap.put(c, countMap.getOrDefault(c,0)+1);
                    }
                }
                StringBuilder sb=new StringBuilder();
                Set<Character> vs=new HashSet<>();
                for(char c: response.toCharArray()){
                    if(Character.isLetterOrDigit(c) && countMap.get(c)>1 &&  !vs.contains(c)){
                        sb.append(c).append(":").append(countMap.get(c)).append(",");
                        vs.add(c);
                    }
                }
                out.write(sb.toString());
                out.newLine();
                out.flush();
                System.out.println("3");
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
