package Clients;

import Interface.PlayerInfo;
import SuppClasses.Validations;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import static SuppClasses.Validations.*;

public class playerClient {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        String username, password, firstName, lastName, IPaddress;
        int age;

        System.out.println("***********Welcome***********");

        int choice = 0;
        while (choice != 5) {

            System.out.println("Please select an option from the following:" +
                    "\nPress 1 to Create a new account. " +
                    "\nPress 2 to Sign in\n" +
                    "Press 3 to Sign Out\n" +
                    "Press 4 to Get Player status\n" +
                    "Press 5 to exit");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Please enter the following information: \n" + "First Name: ");
                    firstName = sc.next();
                    System.out.print("Last Name: ");
                    lastName = sc.next();
                    System.out.print("Age: ");
                    age = getValidIntegerInput();
                    System.out.print("Username(6-15 characters): ");
                    boolean flag = false;
                    username = checkusername();
                    System.out.print("Password: ");
                    password = checkPassword();
                    System.out.print("IPaddress(132,93,182): ");
                    IPaddress = inputIP();
                    String server = checkIP(IPaddress);
                    if (server.equals("NA")) {
                        Registry registry = LocateRegistry.getRegistry(4999);
                        PlayerInfo obj = (PlayerInfo) registry.lookup(server);
                        System.out.println("\n"+"*******"+obj.createPlayerAccount(firstName, lastName, age, username, password, IPaddress)+"*******"+"\n");
                    } else if (server.equals("EU")) {
                        Registry registry = LocateRegistry.getRegistry(2345);
                        PlayerInfo obj = (PlayerInfo) registry.lookup(server);
                        System.out.println("\n"+"*******"+obj.createPlayerAccount(firstName, lastName, age, username, password, IPaddress)+"*******"+"\n");

                    } else if (server.equals("AS")) {
                        Registry registry = LocateRegistry.getRegistry(3999);
                        PlayerInfo obj = (PlayerInfo) registry.lookup(server);
                        System.out.println("\n"+"*******"+obj.createPlayerAccount(firstName, lastName, age, username, password, IPaddress)+"*******"+"\n");
                    }
                    break;

                case 2:
                    System.out.println("Please enter the following information: \n" + "Username: ");
                    username = sc.next();
                    System.out.println("Password: ");
                    password = sc.next();
                    System.out.println("IPAddress");
                    IPaddress = inputIP();
                    server = checkIP(IPaddress);
                    if (server.equals("NA")) {
                        Registry registry = LocateRegistry.getRegistry(4999);
                        PlayerInfo obj = (PlayerInfo) registry.lookup(server);
                        System.out.println("\n"+"*******"+obj.PlayerSignIn(username, password, IPaddress)+"*******"+"\n");
                    } else if (server.equals("EU")) {
                        Registry registry = LocateRegistry.getRegistry(2345);
                        PlayerInfo obj = (PlayerInfo) registry.lookup(server);
                        System.out.println("\n"+"*******"+obj.PlayerSignIn(username, password, IPaddress)+"*******"+"\n");

                    } else if (server.equals("AS")) {
                        Registry registry = LocateRegistry.getRegistry(3999);
                        PlayerInfo obj = (PlayerInfo) registry.lookup(server);
                        System.out.println("\n"+"*******"+obj.PlayerSignIn(username, password, IPaddress)+"*******"+"\n");

                    }
                    break;

                case 3:
                    System.out.println("Please enter the following information: \n" + "Username:");
                    username = sc.next();
                    System.out.println("IPAdress:  ");
                    IPaddress = inputIP();
                    server = checkIP(IPaddress);
                    if (server.equals("NA")) {
                        Registry registry = LocateRegistry.getRegistry(4999);
                        PlayerInfo obj1 = (PlayerInfo) registry.lookup(server);
                        System.out.println("\n"+"*******"+obj1.playerSignOut(username, IPaddress)+"*******"+"\n");
                    } else if (server.equals("EU")) {
                        Registry registry = LocateRegistry.getRegistry(2345);
                        PlayerInfo obj1 = (PlayerInfo) registry.lookup(server);
                        System.out.println("\n"+"*******"+obj1.playerSignOut(username, IPaddress)+"*******"+"\n");

                    } else if (server.equals("AS")) {
                        Registry registry = LocateRegistry.getRegistry(3999);
                        PlayerInfo obj1 = (PlayerInfo) registry.lookup(server);
                        System.out.println("\n"+"*******"+obj1.playerSignOut(username, IPaddress)+"*******"+"\n");

                    }
                    break;
                    default: break;

            }


        }
    }


}


