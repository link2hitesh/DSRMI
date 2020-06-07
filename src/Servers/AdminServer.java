package Servers;

import ServerImplementation.NAServerImpl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class AdminServer {

    private AdminServer(){};

    public static void main(String[] args) throws RemoteException {
        String host = (args.length < 1) ? null : args[0];

        try{
            Registry registry = LocateRegistry.createRegistry(1111);
            registry.bind("Admin", new NAServerImpl());
            System.out.println("Admin Server started");
        }
        catch (Exception e)
        {
            System.out.println("client exception: " + e.toString());
            e.printStackTrace();
        }

    }


    }

