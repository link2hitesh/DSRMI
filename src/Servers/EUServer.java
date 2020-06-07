package Servers;

import ServerImplementation.EUServerImpl;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class EUServer {

    private EUServer(){};

    public static void receive(EUServerImpl implementation) {
        DatagramSocket aSocket = null;
        try {
            aSocket = new DatagramSocket(2345);
            byte[] buffer = new byte[1000];
           System.out.println("Server EU Started............");
            while (true) {
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(request);
                String method=new String(request.getData());
                System.out.println(method);
                String responseString = "";
//                if(method.equals("Method 1")){
                    responseString = implementation.getLocalPlayerStatus();
//                else
//                    implementation.updateUsername(method);

                DatagramPacket reply = new DatagramPacket(responseString.getBytes(), responseString.length(), request.getAddress(),
                        request.getPort());
                aSocket.send(reply);
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


    public static void main(String[] args) throws RemoteException {

        String host = (args.length < 1) ? null : args[0];

        try{


            Registry registry= LocateRegistry.createRegistry(2345);
            EUServerImpl obj= new EUServerImpl();
            registry.bind("EU",obj);
            System.out.println("Europe server started");
            receive(obj);

        }
        catch(Exception e)
        {
            System.out.println("client exception: " + e.toString());
            e.printStackTrace();
        }

    }

}
