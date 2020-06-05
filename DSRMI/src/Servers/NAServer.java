package Servers;

import ServerImplementation.NAServerImpl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class NAServer {

    private NAServer(){};

    public static void main(String[] args) throws RemoteException {

        String host = (args.length < 1) ? null : args[0];

        try{
            Registry registry = LocateRegistry.createRegistry(4999);
            registry.bind("NA", new NAServerImpl());
            System.out.println("North America Server started");
        }
        catch (Exception e)
        {
            System.out.println("client exception: " + e.toString());
            e.printStackTrace();
        }

    }
}
