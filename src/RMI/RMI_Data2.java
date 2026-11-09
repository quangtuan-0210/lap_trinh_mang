package RMI;
import java.util.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Array;

public class RMI_Data2 {
    public static void main(String[] args) throws Exception{
        String studentCode="B23DCCN883";
        String qCode="ucsSl6Pc";

        Registry registry= LocateRegistry.getRegistry("36.50.135.242",1099);
        DataService service=(DataService) registry.lookup("RMIDataService");

        //a
        Object ob= service.requestData(studentCode, qCode);
        String s=(String) ob;
        //b
        String[] parts=s.split(",");
        int n= parts.length;
        double[] a=new double[n];
        for(int i=0;i<n;i++){
            a[i]=Double.parseDouble(parts[i].trim());
        }

        //c
        double sum=0;
        for(double x:a) sum+=x;
        double avg=sum/n;

        double t=0;
        for(double x:a) t+=(x-avg)*(x-avg);
        double stddev=Math.sqrt(t/n);

        Arrays.sort(a);
        int id=(int)Math.ceil(n*0.95)-1;
        double p95=a[id];

        String result=String.format("average=%.2f;stddev=%.2f;p95=%.2f",avg,stddev,p95);
        service.submitData(studentCode,qCode,result);
    }
}
