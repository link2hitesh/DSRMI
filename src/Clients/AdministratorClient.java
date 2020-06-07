package Clients;

import Interface.PlayerInfo;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.*;

import static SuppClasses.Validations.*;

public class AdministratorClient {

    public static void main(String[] args) throws Exception {

        Scanner sc= new Scanner(System.in);
        String username, password, IPAddress;

        System.out.println("***************** WELCOME ********************");
        System.out.println("Press '1' to get the player information from all the servers.\n" +
                "Press 2 to exit");


        int choice=getValidIntegerInput();

        switch(choice)
        {
            case 1:
                System.out.println("Enter your username:");
                username=adminUsername();
                System.out.println("Enter your password:");
                password=sc.nextLine();
                System.out.println("Enter your IPAddress");
                IPAddress=sc.nextLine();
                String server= checkIP(IPAddress);
                if (server.equals("NA")) {
                    Registry registry = LocateRegistry.getRegistry(4999);
                    PlayerInfo obj = (PlayerInfo) registry.lookup(server);
                    System.out.println(obj.getPlayerStatus());
                } else if (server.equals("EU")) {
                    Registry registry = LocateRegistry.getRegistry(2345);
                    PlayerInfo obj = (PlayerInfo) registry.lookup(server);
                    System.out.println(obj.getPlayerStatus());
                } else if (server.equals("AS")) {
                    Registry registry = LocateRegistry.getRegistry(3999);
                    PlayerInfo obj = (PlayerInfo) registry.lookup(server);
                    System.out.println(obj.getPlayerStatus());
                }
                break;

            case 2:
                System.out.println("Exited");
                break;

            default:
                System.out.println("Enter a correct options");

        }



    }

      }

