package SenderReceiver;

import java.io.IOException;
import java.net.*;

public class SenderReceiver {
    public static String sendMessage(int server, int method, String username) {

        DatagramSocket aSocket = null;
        String responseString = null;
        try {
            aSocket = new DatagramSocket();
            String methodType = "Method 1";

            byte[] message = methodType.getBytes();
            byte[] message2 = username.getBytes();
            InetAddress aHost = InetAddress.getByName("localhost");

            switch (method){
                case 1:
                    DatagramPacket request = new DatagramPacket(message, methodType.length(), aHost, server);
                    aSocket.send(request);
                case 2:
                    DatagramPacket request2 = new DatagramPacket(message2, username.length(), aHost, server);
                    aSocket.send(request2);
            }

            byte[] buffer = new byte[1000];
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);

            aSocket.receive(reply);
//            System.out.println("Reply received from the server with port number " + server + " is: "
//                    + new String(reply.getData()));
            responseString = new String(reply.getData());
            //System.out.println(responseString);
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO: " + e.getMessage());
        } finally {
            if (aSocket != null)
                aSocket.close();
        }return responseString;
    }
}
