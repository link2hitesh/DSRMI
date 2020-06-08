package Servers;
import ServerImplementation.NAServerImpl;
import SuppClasses.loggerC;

import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Logger;

public class NAServer {

    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);// to create server logs
    private NAServer(){};
    public static void receive(NAServerImpl implementation) {
        DatagramSocket aSocket = null;
        try {
            aSocket = new DatagramSocket(4999);
            byte[] buffer = new byte[1000];

            while(true) {
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(request);
                LOGGER.info("Request received");
                String response = new String(request.getData()).trim().toLowerCase();// Choice of the method to be invoked

                if(response.equalsIgnoreCase("userstatus")) {
                    String responseString = "";
                    responseString = implementation.getLocalPlayerStatus();//to get player status
                    DatagramPacket reply = new DatagramPacket(responseString.getBytes(), responseString.length(), request.getAddress(), request.getPort());
                    aSocket.send(reply);
                    LOGGER.info("Reply sent for player status");
                } else {
                    boolean userPresent = false;
                    userPresent = implementation.userPresent(response.trim());//to check repetitive username
                    String temp = null;
                    if(userPresent){
                        temp = "t";
                    }else temp = "f";
                    DatagramPacket reply = new DatagramPacket(temp.getBytes(), temp.length(), request.getAddress(), request.getPort());
                    aSocket.send(reply);
                    LOGGER.info("Reply sent for username check");
                }
            }
        }catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            if (aSocket != null)
                aSocket.close();
        }
    }

    public static void main(String[] args) throws Exception {

        String host = (args.length < 1) ? null : args[0];
        setupLogging();

        try{
            NAServerImpl NAStub = new NAServerImpl();
            Runnable task = () -> {// to handle concurrency
                receive(NAStub);
            };
            Thread thread = new Thread(task);
            thread.start();
            Registry registry= LocateRegistry.createRegistry(4999);// registry created at port 4999
            registry.bind("NA",NAStub);// registry at port 2345 bound to EUServerImpl object
            System.out.println("North America server started");
            LOGGER.info("North America server started");

        }
        catch (Exception e)
        {
            System.out.println("client exception: " + e.toString());
            e.printStackTrace();
        }
    }
    private static void setupLogging() throws IOException {// logger setup
        File files = new File("./src/Servers/");
        if (!files.exists())
            files.mkdirs();
        files = new File("./src/ServerLogs/NorthAmerica.log");
        if(!files.exists())
            files.createNewFile();
        loggerC.setup(files.getAbsolutePath());
    }
}
