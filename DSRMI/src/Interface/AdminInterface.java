package Interface;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AdminInterface extends Remote {

    public void getPlayerStatus(String AdminUsername, String AdminPassword, String IPAddress ) throws RemoteException;

}
