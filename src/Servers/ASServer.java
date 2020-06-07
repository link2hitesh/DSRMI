package Servers;

import ServerImplementation.ASServerImpl;
import ServerImplementation.EUServerImpl;
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

public class ASServer {
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private ASServer(){};

    public static void receive(ASServerImpl implementation) {
        DatagramSocket aSocket = null;
        try {
            aSocket = new DatagramSocket(3999);
            byte[] buffer = new byte[1000];
             System.out.println("Server AS Started............");
            while(true) {
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(request);
                String response = new String(request.getData()).trim().toLowerCase();

                if(response.equalsIgnoreCase("userstatus")) {
                    String responseString = "";
                    responseString = implementation.getLocalPlayerStatus();
                    DatagramPacket reply = new DatagramPacket(responseString.getBytes(), responseString.length(), request.getAddress(), request.getPort());
                    aSocket.send(reply);
                } else {
                    boolean userPresent = false;
                    userPresent = implementation.userPresent(response.trim());
                    String temp = null;
                    if(userPresent){
                        temp = "t";
                    }else temp = "f";
                    DatagramPacket reply = new DatagramPacket(temp.getBytes(), temp.length(), request.getAddress(), request.getPort());
                    aSocket.send(reply);
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
            ASServerImpl ASStub = new ASServerImpl();
            Runnable task = () -> {
                receive(ASStub);
            };
            Thread thread = new Thread(task);
            thread.start();
            Registry registry= LocateRegistry.createRegistry(3999);
            registry.bind("AS",ASStub);
            System.out.println("Asia server started");
            LOGGER.info( " Asia Server started");
        }
        catch(Exception e)
        {
            System.out.println("client exception: " + e.toString());
            e.printStackTrace();
        }
    }

    private static void setupLogging() throws IOException {
        File files = new File("./src/Servers/");
        if (!files.exists())
            files.mkdirs();
        files = new File("./src/Servers/ASServerLogs.log");
        if(!files.exists())
            files.createNewFile();
        loggerC.setup(files.getAbsolutePath());
    }
}