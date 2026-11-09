package RMI;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMI_Object {
    public static void main(String[]args) throws Exception{
        String studentCode="B23DCCN883";
        String qCode="WuaA2fKu";

        Registry registry= LocateRegistry.getRegistry("36.50.135.242",1099);
        ObjectService service=(ObjectService)registry.lookup("RMIObjectService");

        //a,b
        TicketSla t=(TicketSla) service.requestObject(studentCode,qCode);
        String p=t.getPriority().trim().toUpperCase();
        int h=t.getOpenedHoursAgo();

        boolean breached;
        if(p.equals("CRITICAL")) breached=h>2;
        else if (p.equals("HIGH")) breached=h>8;
        else if(p.equals("MEDIUM")) breached=h>24;
        else breached=h>72;

        String action;
        if(!breached) action="MONITOR";
        else if(p.equals("CRITICAL") || h>96) action="ESCALATE_L2";
        else action="ESCALATE_L1";

        t.setAction(action);
        t.setBreached(breached);
        service.submitObject(studentCode,qCode,t);
    }
}
