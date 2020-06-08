package ServerImplementation;

import Interface.AdminInterface;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class AdminImplementation implements AdminInterface {
   static List<String>uname=new ArrayList<>();
    @Override
    public void getPlayerStatus(String AdminUsername, String AdminPassword, String IPAddress) throws RemoteException {

        if(AdminUsername.equals("Admin")&& AdminPassword.equals("Admin"))
        {
            System.out.println("Admin logged in");
        }

    }
}
