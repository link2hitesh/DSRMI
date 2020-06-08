package Clients;

import Interface.PlayerInfo;
import SuppClasses.Validations;
import SuppClasses.loggerC;

import java.io.File;
import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.util.logging.Logger;

import static SuppClasses.Validations.*;

public class playerClient {
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        String username, password, firstName, lastName, IPaddress;
        int age;
        int playerCounter=0;

        System.out.println("***********Welcome***********");

        int choice = 0;
        while (choice != 5) {

            System.out.println("Please select an option from the following:" +
                    "\nPress 1 to Create a new account. " +
                    "\nPress 2 to Sign in\n" +
                    "Press 3 to Sign Out\n" +
                    "Press 4 to exit");
            choice = getValidIntegerInput();

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
                    String server = checkIP(IPaddress);//returns the correct server on which the operation is to be performed.
                    if (server.equals("NA")) {
                        Registry registry = LocateRegistry.getRegistry(4999);
                        PlayerInfo obj = (PlayerInfo) registry.lookup(server);
                        String output=obj.createPlayerAccount(firstName, lastName, age, username, password, IPaddress);
                        System.out.println("\n"+"*******"+output+"*******"+"\n");

                        if(output.equals("New account created"))
                        {
                            playerCounter++;// to create new log file for each player
                            setupLogging(Integer.toString(playerCounter));
                            LOGGER.info(username+"'s account created");
                        }
                    } else if (server.equals("EU")) {
                        Registry registry = LocateRegistry.getRegistry(2345);
                        PlayerInfo obj = (PlayerInfo) registry.lookup(server);
                        String output=obj.createPlayerAccount(firstName, lastName, age, username, password, IPaddress);
                        System.out.println("\n"+"*******"+output+"*******"+"\n");

                        if(output.equals("New account created"))// to create new log file for each player
                        {
                            playerCounter++;
                            setupLogging(Integer.toString(playerCounter));
                            LOGGER.info(username+"'s account created");
                        }

                    } else if (server.equals("AS")) {
                        Registry registry = LocateRegistry.getRegistry(3999);
                        PlayerInfo obj = (PlayerInfo) registry.lookup(server);
                        String output=obj.createPlayerAccount(firstName, lastName, age, username, password, IPaddress);
                        System.out.println("\n"+"*******"+output+"*******"+"\n");

                        if(output.equals("New account created"))// to create new log file for each player
                        {
                            playerCounter++;
                            setupLogging(Integer.toString(playerCounter));
                            LOGGER.info(username+"'s account created");
                        }

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
                        String output=obj.PlayerSignIn(username, password, IPaddress);// receives message from server and is played to client
                        System.out.println("\n"+"*******"+output+"*******"+"\n");
                        LOGGER.info(output);
                    } else if (server.equals("EU")) {
                        Registry registry = LocateRegistry.getRegistry(2345);
                        PlayerInfo obj = (PlayerInfo) registry.lookup(server);
                        String output=obj.PlayerSignIn(username, password, IPaddress);
                        System.out.println("\n"+"*******"+output+"*******"+"\n");
                        LOGGER.info(output);

                    } else if (server.equals("AS")) {
                        Registry registry = LocateRegistry.getRegistry(3999);
                        PlayerInfo obj = (PlayerInfo) registry.lookup(server);
                        String output=obj.PlayerSignIn(username, password, IPaddress);
                        System.out.println("\n"+"*******"+output+"*******"+"\n");
                        LOGGER.info(output);

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
                        String output=obj1.playerSignOut(username, IPaddress);
                        System.out.println("\n"+"*******"+output+"*******"+"\n");
                        LOGGER.info(output);
                    } else if (server.equals("EU")) {
                        Registry registry = LocateRegistry.getRegistry(2345);
                        PlayerInfo obj1 = (PlayerInfo) registry.lookup(server);
                        String output=obj1.playerSignOut(username, IPaddress);
                        System.out.println("\n"+"*******"+output+"*******"+"\n");
                        LOGGER.info(output);

                    } else if (server.equals("AS")) {
                        Registry registry = LocateRegistry.getRegistry(3999);
                        PlayerInfo obj1 = (PlayerInfo) registry.lookup(server);
                        String output=obj1.playerSignOut(username, IPaddress);
                        System.out.println("\n"+"*******"+output+"*******"+"\n");
                        LOGGER.info(output);

                    }
                    break;
                case 4: break;

                    default:
                        System.out.println("Please enter correct option");
                        break;

            }


        }
    }
    private static void setupLogging(String filename) throws IOException {
        File logFile = new File("./src/Servers/");
        if (!logFile.exists())
            logFile.mkdirs();
        String path="./src/PlayerLogs/Player_"+filename+".log";
        logFile = new File(path);
        if(!logFile.exists())
            logFile.createNewFile();
        loggerC.setup(logFile.getAbsolutePath());
    }


}


