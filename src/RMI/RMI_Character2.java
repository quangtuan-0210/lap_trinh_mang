package RMI;

import java.net.URISyntaxException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMI_Character2 {
    public static void main(String[] args) throws RemoteException, NotBoundException {
        String studentCode="B23DCCN883";
        String qCode="YJ0QJSCP";

        Registry registry= LocateRegistry.getRegistry("36.50.135.242",1099);
        CharacterService service=(CharacterService) registry.lookup("RMICharacterService");

        //a
        String s=service.requestCharacter(studentCode,qCode);
        int n=Integer.parseInt(s.trim());
        //b
        String R= toRoman(n);

        //c
        service.submitCharacter(studentCode,qCode,R);
    }

    static String toRoman(int n){
        int[]    val = {1000, 900, 500, 400, 100,  90,  50,   40,  10,    9,   5,    4,   1};
        String[] sym = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < val.length; i++) {
            while (n >= val[i]) {
                sb.append(sym[i]);
                n -= val[i];
            }
        }
        return sb.toString();
    }
}
