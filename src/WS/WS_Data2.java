package WS;

import ptit.dblab.judge.network.server.soap.DataService;
import ptit.dblab.judge.network.server.soap.SoapDataService;

import java.util.List;

public class WS_Data2 {
    public static void main(String[]args) throws Exception{
        String studentCode="B23DCCN883";
        String qCode="Fyr4mfRt";

        DataService service=new DataService();
        SoapDataService port=service.getSoapDataServicePort();

        //a
        List<Integer> data=port.getData(studentCode,qCode);

        //b
        int tong=0;
        for(int x:data) tong+=x;

        //c
        port.submitDataInt(studentCode,qCode,tong);
    }
}
