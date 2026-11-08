package RMI;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMI_Byte1 {
    public static void main(String[]args) throws RemoteException, NotBoundException {
        String studentCode="B23DCCN883";
        String qCode="3yN87O1f";

        Registry registry= LocateRegistry.getRegistry("36.50.135.242",1099);
        ByteService service=(ByteService) registry.lookup("RMIByteService");

        //a
        byte[] data=service.requestData(studentCode,qCode);

        //b
        byte Min = data[0];
        int cnt= Integer.MAX_VALUE;

        for (int i = 0; i < data.length; i++) {
            int dem = 0;
            for (int j = 0; j < data.length; j++) {
                if (data[j] == data[i]) {
                    dem++;
                }
            }

            if (dem < cnt) {
                cnt = dem;
                Min = data[i];
            }
        }

        //c
        byte[]result= {Min, (byte) cnt};
        service.submitData(studentCode,qCode,result);
    }
}
