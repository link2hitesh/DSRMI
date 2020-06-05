package Servers;

import ServerImplementation.EUServerImpl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ASServer {
    private ASServer(){};

    public static void main(String[] args) throws RemoteException {

        String host = (args.length < 1) ? null : args[0];

        try{

            Registry registry= LocateRegistry.createRegistry(3999);
            registry.bind("AS",new EUServerImpl());
            System.out.println("Asia server started");

        }
        catch(Exception e)
        {
            System.out.println("client exception: " + e.toString());
            e.printStackTrace();
        }

    }

}