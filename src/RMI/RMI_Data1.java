package RMI;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

public class RMI_Data1 {
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        String qCode="ZpPg8eUn";
        String studentCode="B23DCCN883";

        Registry registry= LocateRegistry.getRegistry("36.50.135.242", 1099);
        DataService service=(DataService) registry.lookup("RMIDataService");

        //a
        Object ob=service.requestData(studentCode,qCode);

        int[] a;
        if(ob instanceof int[]){
            a=(int[])ob;
        }else{
            List<?> list=(List<?>)ob;
            a=new int[list.size()];
            for(int i=0;i<list.size();i++){
                a[i] = (Integer) list.get(i);
            }
        }
        //b
        List<Integer> peaks = new ArrayList<>();
        int n = a.length;
        for (int i = 0; i < n; i++) {
            boolean honTrai = (i == 0)     || a[i] > a[i - 1];
            boolean honPhai = (i == n - 1) || a[i] > a[i + 1];
            if (honTrai && honPhai) {
                peaks.add(i + 1);
            }
        }
        //c
        service.submitData(studentCode,qCode,peaks);
    }
}
