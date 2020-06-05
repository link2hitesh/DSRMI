package Interface;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PlayerInfo extends Remote {

    public void createPlayerAccount(String FirstName, String LastName, int Age, String Username, String Password, String IPAdress) throws RemoteException;

    public void PlayerSignIn(String Username, String Password, String IPAddress) throws RemoteException;

    public void playerSignOut(String Username, String IPAdress) throws RemoteException;

    //public String getPlayerStatus(String adminUsername, String adminPassword, String IPAddress ) throws RemoteException;


}


