package ServerImplementation;

import Interface.PlayerInfo;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class ASServerImpl extends UnicastRemoteObject implements PlayerInfo {
    Hashtable<Character, List<player>> playerDB = new Hashtable<Character, List<ServerImplementation.player>>();
    List<String> uname = new ArrayList<String>();




    public ASServerImpl() throws Exception{
        super();
    }



    @Override
    public void createPlayerAccount(String FirstName, String LastName, int Age, String Username, String Password, String IPAdress) throws RemoteException {

        boolean flag = false; //to check whether the username is already used
        for (String temp : uname) {
            if (Username.equals(temp)) {
                flag = true;
                System.out.println("username already exists");
            }
        }
        if (!flag) {                                        //creating a player and adding it to the playerDB
            String status = "offline";
            player newPlayer = new player(FirstName, LastName, Username, Password, Age, IPAdress, status);
            uname.add(Username); //add to the list of existing usernames
            char[] tempArray = Username.toCharArray();
            char firstLetter= Character.toUpperCase(tempArray[0]);// to create a a clean database
            System.out.println("first letter is: "+ firstLetter);
            if(playerDB.containsKey(firstLetter))
            {
                playerDB.get(firstLetter).add(newPlayer);
                System.out.println("new account created");
            }
            else
            {
                List<player> newList= new ArrayList<>();
                newList.add(newPlayer);
                playerDB.put(firstLetter,newList);
                System.out.println("new account created");
            }

        }
        else {
            System.out.println("Account not created");

        }


    }

    @Override
    public void PlayerSignIn(String Username, String Password, String IPAddress) throws RemoteException {

        for(int i=65; i<91; i++)
        {
            char firstLetter= (char)i;
            if(playerDB.containsKey(firstLetter))
            {
                List<player> tempList= playerDB.get(firstLetter);
                for(player currentPlayer : tempList)
                {
                    if (currentPlayer.userName.equals(Username)) {
                        if (currentPlayer.pwd.equals(Password)) {
                            if (currentPlayer.status.equals("online")) {
                                System.out.println("Player Already signed in");
                            } else {
                                currentPlayer.status = "online";
                                System.out.println( Username+" -signed in");
                            }
                        } else
                            System.out.println("incorrect password");

                    }

                }
            }
            else
            {
                continue;
            }
        }
    }


    @Override
    public void playerSignOut(String Username, String IPAdress) throws RemoteException {
        for (int i = 65; i < 91; i++) {
            char firstLetter = (char) i;

            if(playerDB.containsKey(firstLetter)){
                List<player> checkingList = playerDB.get(firstLetter);

                for (player currentPlayer : checkingList) {
                    if (currentPlayer.userName.equals(Username)) {
                        if (currentPlayer.status.equals("online")) {
                            currentPlayer.status = "offline";
                            System.out.println("Player " + Username + " signed out");
                        } else {
                            System.out.println("Player " + Username + "not signed in");
                        }
                    } else {
                        continue;
                    }
                }
            }
        }}
}

