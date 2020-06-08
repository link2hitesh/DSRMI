package Clients;

import Interface.PlayerInfo;
import SuppClasses.loggerC;

import java.io.File;
import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.*;
import java.util.logging.Logger;

import static SuppClasses.Validations.*;

public class AdministratorClient {
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public static void main(String[] args) throws Exception {

        Scanner sc= new Scanner(System.in);
        String username, password, IPAddress;

        System.out.println("***************** WELCOME ADMIN ********************");
        System.out.println("Press '1' to Get Player Status\n" +
                "Press '2' to exit");


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
                    LOGGER.info("Admin verified and player status returned");
                } else if (server.equals("EU")) {
                    Registry registry = LocateRegistry.getRegistry(2345);
                    PlayerInfo obj = (PlayerInfo) registry.lookup(server);
                    System.out.println(obj.getPlayerStatus());
                    LOGGER.info("Admin verified and player status returned");
                } else if (server.equals("AS")) {
                    Registry registry = LocateRegistry.getRegistry(3999);
                    PlayerInfo obj = (PlayerInfo) registry.lookup(server);
                    System.out.println(obj.getPlayerStatus());
                    LOGGER.info("Admin verified and player status returned");
                }
                break;

            case 2:
                System.out.println("Exited");
                break;

            default:
                System.out.println("Enter a correct options");

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

