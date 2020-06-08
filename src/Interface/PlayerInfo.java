package Interface;  /* Interface to be implemented by the servers
                        +implementation for the udp connection*/

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PlayerInfo extends Remote {

    public String createPlayerAccount(String FirstName, String LastName, int Age, String Username, String Password, String IPAdress) throws RemoteException;

    public String PlayerSignIn(String Username, String Password, String IPAddress) throws RemoteException;

    public String playerSignOut(String Username, String IPAdress) throws RemoteException;

    public String getPlayerStatus() throws RemoteException;


}


