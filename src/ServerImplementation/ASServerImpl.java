package ServerImplementation;

import Interface.PlayerInfo;
import SenderReceiver.SenderReceiver;
import SuppClasses.player;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class ASServerImpl extends UnicastRemoteObject implements PlayerInfo {
    Hashtable<Character, List<player>> playerDB = new Hashtable<Character, List<player>>();
    List<String> uname = new ArrayList<String>();

    public  ASServerImpl() throws Exception { /*Comtructor to create default players*/
        super();
        player newplayer = new player("abc", "cde", "player1", "password", 21, "182.122.132.23", "Offline");

        uname.add(newplayer.userName.trim());
        uname.add("player2");
        uname.add("player3");
        char[] tempArray = newplayer.userName.toCharArray();
        char firstLetter = Character.toUpperCase(tempArray[0]);

        if (playerDB.containsKey(firstLetter)) {
            playerDB.get(firstLetter).add(newplayer);
        } else {
            List<player> newList = new ArrayList<>();
            newList.add(newplayer);
            playerDB.put(firstLetter, newList);
        }

    }

   // @Override
    public synchronized String createPlayerAccount(String FirstName, String LastName, int Age, String Username, String Password, String IPAdress) throws RemoteException {

        boolean userPresent = userPresent(Username);
        uname.add(Username.trim());
        String responseFromEU = SenderReceiver.sendMessage(2345,2,Username).trim();
        String responseFromNA = SenderReceiver.sendMessage(4999,2,Username).trim();

        //System.out.println(responseFromNA + " and " + responseFromEU);

        if (!userPresent && responseFromNA.equalsIgnoreCase("f") && responseFromEU.equalsIgnoreCase("f")) {

            player newPlayer = new player(FirstName, LastName, Username, Password, Age, IPAdress, "Offline");
            uname.add(Username.trim());
            char[] tempArray = Username.toCharArray();
            char firstLetter = Character.toUpperCase(tempArray[0]);

            if (playerDB.containsKey(firstLetter)) {
                playerDB.get(firstLetter).add(newPlayer);
            } else {
                List<player> newList = new ArrayList<>();
                newList.add(newPlayer);
                playerDB.put(firstLetter, newList);
            }
            return("New account created");

        } else {
            return("Account not created(Username already exists)");
        }
    }

   // @Override
   public synchronized String PlayerSignIn(String Username, String Password, String IPAddress) throws RemoteException {  char firstLetter = Username.toCharArray()[0];
       firstLetter = Character.toUpperCase(firstLetter);
       if (playerDB.containsKey(firstLetter)) {
           List<player> tempList = playerDB.get(firstLetter);
           for (player currentPlayer : tempList) {
               if (currentPlayer.userName.equals(Username)) {
                   if (currentPlayer.pwd.equals(Password)) {
                       if (currentPlayer.status.equals("Online")) {
                           return ("Player Already signed in");
                       } else {
                           currentPlayer.status = "Online";
                           return ("'" + Username + "'" + " signed in");
                       }
                   } else
                       return ("incorrect password");

               } else {
                   continue;
               }

           }

           return ("User does not exist");
       }
       return ("User does not exist");
   }

   // @Override
    public synchronized String playerSignOut(String Username, String IPAdress) throws RemoteException {
        char firstLetter= Username.toCharArray()[0];
        firstLetter=Character.toUpperCase(firstLetter);
        if(playerDB.containsKey(firstLetter)){
            List<player> checkingList = playerDB.get(firstLetter);

            for (player currentPlayer : checkingList) {
                if (currentPlayer.userName.equals(Username)) {
                    if (currentPlayer.status.equals("Online")) {
                        currentPlayer.status = "Offline";
                        return( "'"+Username+"'"+" signed out");
                    } else {
                        return( "'"+Username+"'"+" not signed in");
                    }
                }
                else{ continue;}
            }
              {
                return ("User does not exist");
              }
        }
        return("User does not exist");
    }

    @Override
    public synchronized String getPlayerStatus() throws RemoteException {
        String EU= SenderReceiver.sendMessage(2345,1,"invalid");
        String NA=SenderReceiver.sendMessage(4999,1, "invalid");
        String AS=getLocalPlayerStatus();
        return EU+AS+NA;
    }

    public synchronized boolean userPresent(String userName) {
        boolean flag = false;
        for(String name : uname) {
            flag = name.equals(userName.trim());
        }
        return flag;
    }

    public synchronized String getLocalPlayerStatus() throws RemoteException {

        int onlineCount = 0;
        int offlineCount=0;
        for (int i = 65; i < 91; i++) {
            char firstLetter = (char) i;

            if (playerDB.containsKey(firstLetter)) {
                List<player> checkingList = playerDB.get(firstLetter);

                for (player currentPlayer : checkingList) {
                    if (currentPlayer.status.equals("Online")) {
                        onlineCount++;
                    }
                    else if(currentPlayer.status.equals("Offline"))
                    {
                        offlineCount++;
                    }
                }
            }
        }
        String message = "AS: " + Integer.toString(onlineCount)+" Online, "+Integer.toString(offlineCount)+" Offline. ";
        return message;
    }
}

