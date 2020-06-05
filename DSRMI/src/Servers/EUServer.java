package Servers;

import ServerImplementation.EUServerImpl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class EUServer {

    private EUServer(){};

    public static void main(String[] args) throws RemoteException {

        String host = (args.length < 1) ? null : args[0];

        try{

            Registry registry= LocateRegistry.createRegistry(2345);
            registry.bind("EU",new EUServerImpl());
            System.out.println("Europe server started");

        }
        catch(Exception e)
        {
            System.out.println("client exception: " + e.toString());
            e.printStackTrace();
        }

    }

}
