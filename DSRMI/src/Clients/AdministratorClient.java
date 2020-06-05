package Clients;

import Interface.PlayerInfo;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.*;

public class AdministratorClient {

    public static void main(String[] args) throws Exception {

        Scanner sc= new Scanner(System.in);
        String username, password, IPAddress;

        System.out.println("***************** WELCOME ********************");
        System.out.println("Press '1' to get the player information from all the servers.\n" +
                "Press 2 to exit");


        int choice=0;

        switch(choice)
        {
            case 1:
                System.out.println("Enter your username:");
                username=sc.nextLine();
                System.out.println("Enter your password:");
                password=sc.nextLine();
                System.out.println("Enter your IPAddress");
                IPAddress=sc.nextLine();
                String server= checkIP(IPAddress);
                if (server.equals("NA")) {
                    Registry registry = LocateRegistry.getRegistry(4999);
                    PlayerInfo obj = (PlayerInfo) registry.lookup(server);
                    obj.PlayerSignIn(username, password, IPAddress);
                } else if (server.equals("EU")) {
                    Registry registry = LocateRegistry.getRegistry(2345);
                    PlayerInfo obj = (PlayerInfo) registry.lookup(server);
                    obj.PlayerSignIn(username, password, IPAddress);
                } else if (server.equals("AS")) {
                    Registry registry = LocateRegistry.getRegistry(3999);
                    PlayerInfo obj = (PlayerInfo) registry.lookup(server);
                    obj.PlayerSignIn(username, password, IPAddress);
                }


        }



    }

    public static String checkIP(String IPaddress) {

        String[] ip = IPaddress.split("[.]");
        System.out.println(ip[0]);
        String server = null;

        switch (ip[0]) {

            case "132":
                server = "NA";
                break;
            case "93":
                server = "EU";
                break;
            case "182":
                server = "AS";
                break;
            default:
                break;
        }
        return server;
    }
}
