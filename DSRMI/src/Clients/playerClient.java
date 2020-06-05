package Clients;

import Interface.PlayerInfo;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class playerClient {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        String username, password, firstName, lastName, IPaddress;
        int age;

        System.out.println("Welcome");

        int choice=0;
        while (choice != 5) {

            System.out.println("Please select an option from the following:" +
                    "\n Press 1 to Create a new account. " +
                    "\n Press 2 to Sign in\n " +
                    "Press 3 to Sign Out\n" +
                    " Press 4 to Get Player status\n" +
                    "Press 5 to exit");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.println("Please enter the following information: \n" + "First Name:");
                    firstName = sc.next();
                    System.out.println("Last Name: ");
                    lastName = sc.next();
                    System.out.println("Username(6-15 characters): ");
                    boolean flag = false;
                    username = playerClient.checkusername();
                    System.out.println("Age: ");
                    age = sc.nextInt();
                    System.out.println("Password: ");
                    password = playerClient.checkPassword();
                    System.out.println("IPaddress: ");
                    IPaddress = sc.next();
                    String server = playerClient.checkIP(IPaddress);
                    if (server.equals("NA")) {
                        Registry registry = LocateRegistry.getRegistry(4999);
                        PlayerInfo obj = (PlayerInfo) registry.lookup(server);
                        obj.createPlayerAccount(firstName, lastName, age, username, password, IPaddress);
                    } else if (server.equals("EU")) {
                        Registry registry = LocateRegistry.getRegistry(2345);
                        PlayerInfo obj = (PlayerInfo) registry.lookup(server);
                        obj.createPlayerAccount(firstName, lastName, age, username, password, IPaddress);

                    } else if (server.equals("AS")) {
                        Registry registry = LocateRegistry.getRegistry(3999);
                        PlayerInfo obj = (PlayerInfo) registry.lookup(server);
                        obj.createPlayerAccount(firstName, lastName, age, username, password, IPaddress);
                    }
                    break;

                case 2:
                    System.out.println("Please enter the following information: \n" + "Username: ");
                    username = sc.next();
                    System.out.println("Password: ");
                    password = sc.next();
                    System.out.println("IPAddress");
                    IPaddress = sc.next();
                    server = playerClient.checkIP(IPaddress);
                    if (server.equals("NA")) {
                        Registry registry = LocateRegistry.getRegistry(4999);
                        PlayerInfo obj = (PlayerInfo) registry.lookup(server);
                        obj.PlayerSignIn(username, password, IPaddress);
                    } else if (server.equals("EU")) {
                        Registry registry = LocateRegistry.getRegistry(2345);
                        PlayerInfo obj = (PlayerInfo) registry.lookup(server);
                        obj.PlayerSignIn(username, password, IPaddress);
                    } else if (server.equals("AS")) {
                        Registry registry = LocateRegistry.getRegistry(3999);
                        PlayerInfo obj = (PlayerInfo) registry.lookup(server);
                        obj.PlayerSignIn(username, password, IPaddress);
                    }
                    break;

                case 3:
                    System.out.println("Please enter the following information: \n" + "Username:");
                    username = sc.next();
                    System.out.println("IPAdress:  ");
                    IPaddress = sc.next();
                    server = playerClient.checkIP(IPaddress);
                    if (server.equals("NA")) {
                        Registry registry = LocateRegistry.getRegistry(4999);
                        PlayerInfo obj1 = (PlayerInfo) registry.lookup(server);
                        obj1.playerSignOut(username, IPaddress);
                    } else if (server.equals("EU")) {
                        Registry registry = LocateRegistry.getRegistry(2345);
                        PlayerInfo obj1 = (PlayerInfo) registry.lookup(server);
                        obj1.playerSignOut(username, IPaddress);
                    } else if (server.equals("AS")) {
                        Registry registry = LocateRegistry.getRegistry(3999);
                        PlayerInfo obj1 = (PlayerInfo) registry.lookup(server);
                        obj1.playerSignOut(username, IPaddress);
                    }
                    break;

                case 4:
                    System.out.println("please enter the following information: " + "Username:");



            }


        }
    }

    public static String checkusername() {

        Scanner sc = new Scanner(System.in);
        String username = sc.nextLine();
        boolean flag = false;
        while (!flag) {
            if (username.length() < 6) {
                System.out.println("Invalid Username.\n " +
                        "Your Username should have a minimum of 6 characters\n" +
                        "kindly enter again");
                username = sc.next();
            } else if (username.length() > 15) {
                System.out.println("Invalid Username.\n " +
                        "Your Username should have a maximum of 6 characters\n" +
                        "kindly enter again");
                username = sc.next();
            } else
                flag = true;

        }
        return username;

    }

    public static String checkPassword(){
        Scanner sc = new Scanner(System.in);
        String password = sc.nextLine();
        boolean flag = false;
        while (!flag) {
            if (password.length() < 6) {
                System.out.println("Invalid Username.\n " +
                        "Your Username should have a minimum of 6 characters\n" +
                        "kindly enter again");
                password = sc.next();
            }
                flag = true;

        }
        return password;

    }

    public static String checkIP(String IPaddress) {

        String checkIP= IPaddress.substring(0,3);
        String server = null;

        switch (checkIP) {

            case "132":
                server = "NA";
                break;
            case "93":
                server = "EU";
                break;
            case "182":
                server = "AS";
                break;
            default:
                break;
        }
        return server;
    }

//    public static int checkAge(String Age)
//    public static String generateIP()
//    {
//        Random r= new Random();
//        String[] ipList= {"132","93","182"};
//        int a = 0;
//        int b = 2;
//        int randomNum = r.nextInt((b - a) + 1) + a;
//        String one=ipList[randomNum];
//        String two= (r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256));
//        String IPadress=one + "." + two;
//        return IPadress;
//    }
}
