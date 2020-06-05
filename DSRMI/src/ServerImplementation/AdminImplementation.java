package ServerImplementation;

import Interface.AdminInterface;

import java.rmi.RemoteException;

public class AdminImplementation implements AdminInterface {
    @Override
    public void getPlayerStatus(String AdminUsername, String AdminPassword, String IPAddress) throws RemoteException {

        if(AdminUsername.equals("Admin")&& AdminPassword.equals("Admin"))
        {
            System.out.println("Admin logged in");
        }

    }
}
