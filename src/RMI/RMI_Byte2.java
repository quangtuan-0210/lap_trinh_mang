package RMI;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMI_Byte2 {
    public static void main(String[] args) throws Exception {
        String studentCode="B23DCCN883";
        String qCode="zLOCggwk";

        Registry registry= LocateRegistry.getRegistry("36.50.135.242",1099);
        ByteService service=(ByteService) registry.lookup("RMIByteService");

        //a
        byte[] data=service.requestData(studentCode,qCode);

        //b
        byte Max=data[0];
        int cnt=Integer.MIN_VALUE;
        for(int i=0;i<data.length;i++){
            int dem=0;
            for(int j=0;j<data.length;j++){
                if(data[j]==data[i]){
                    dem++;
                }
            }
            if(dem>cnt){
                cnt=dem;
                Max=data[i];
            }
        }

        //c
        byte[] result={Max, (byte) cnt};
        service.submitData(studentCode,qCode,result);
    }
}
