package RMI;

import java.net.URISyntaxException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMI_Character1 {
    public static void main(String[] args) throws RemoteException, NotBoundException, URISyntaxException {
        String qCode="TgmFEaFJ";
        String studentCode="B23DCCN883";

        Registry registry= LocateRegistry.getRegistry("36.50.135.242",1099);
        CharacterService service=(CharacterService) registry.lookup("RMICharacterService");

        //a
        String s=service.requestCharacter(studentCode,qCode);

        //b
        String encoded = new java.net.URI(null, null, s, null).toASCIIString();

        //c
        service.submitCharacter(studentCode, qCode, encoded);
    }

}
