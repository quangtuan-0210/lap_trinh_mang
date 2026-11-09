package WS;

import ptit.dblab.judge.network.server.soap.*;

import java.util.*;

public class WS_Data1 {
    public static void main(String[]args) throws Exception{
        String studentCode="B23DCCN883";
        String qCode="PXQW1jjg";

        DataService service=new DataService();
        SoapDataService port=service.getSoapDataServicePort();

        //a
        List<Integer> data=port.getData(studentCode,qCode);

        //b
        List<String> s= new ArrayList<>();
        for(int x:data) s.add(String.valueOf(x));
        s.sort((x,y)->(y+x).compareTo(x+y));
        String result=String.join("",s);
        if(result.startsWith("0")) result="0";

        //c
        port.submitDataString(studentCode,qCode,result);
    }
}
