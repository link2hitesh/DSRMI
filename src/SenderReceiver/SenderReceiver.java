package SenderReceiver;

import java.io.IOException;
import java.net.*;

public class SenderReceiver {
    public static String sendMessage(int server, int method, String username) {

        DatagramSocket aSocket = null;
        String responseString = null;
        try {
            aSocket = new DatagramSocket();
            String string = "Method 1";
            String string2=username;

            byte[] message = string.getBytes();
            byte[] message2 = string2.getBytes();
            InetAddress aHost = InetAddress.getByName("localhost");
            String sentMessage="";
            if(method==1) {
                DatagramPacket request = new DatagramPacket(message, string.length(), aHost, server);
                aSocket.send(request);
                sentMessage=new String(request.getData());
            }
            else if(method==2){
                DatagramPacket request = new DatagramPacket(message2, string2.length(), aHost, server);
                aSocket.send(request);
                sentMessage=new String(request.getData());
               // System.out.println(sentMessage);

            }
//            System.out.println("Request message sent from the client to server with port number " + server + " is: "
//                    +sentMessage);
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
