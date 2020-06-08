package SuppClasses;

import java.util.Scanner;

public class Validations {
    public static String checkusername() {

        Scanner sc = new Scanner(System.in);
        String username = sc.nextLine();
        boolean flag = false;
        while (!flag) {
            if (username.length() < 6) {
                System.out.println("Invalid Username.\n" +
                        "Your Username should have a minimum of 6 characters\n" +
                        "Kindly enter again");
                username = sc.next();
            } else if (username.length() > 15) {
                System.out.println("Invalid Username.\n " +
                        "Your Username should have a maximum of 6 characters\n" +
                        "Kindly enter again");
                username = sc.next();
            } else
                flag = true;

        }
        return username;

    }

    public static String checkPassword() {
        Scanner sc = new Scanner(System.in);
        String password = sc.nextLine();
        boolean flag = false;
        while (!flag) {
            if (password.length() < 6) {
                System.out.println("Invalid Password.\n" +
                        "Your password should have a minimum of 6 characters\n" +
                        "Kindly enter again");
                password = sc.next();
            }
            flag = true;

        }
        return password;

    }

    public static String checkIP(String IPaddress) {

        String checkIP = "";
        if (IPaddress.length() >= 3) {
            checkIP = IPaddress.substring(0, 3);
        }
        String checkIP2 = IPaddress.substring(0, 2);
        String server = null;
        boolean flag = false;
        while (!flag) {

            if (checkIP.equals("132")) {
                server = "NA";
                flag = true;
            } else if (checkIP2.equals("93")) {
                server = "EU";
                flag = true;
            } else if (checkIP.equals("182")) {
                server = "AS";
                flag = true;

            } else {
                server = "Invalid";
            }
        }
        return server;
    }

    public static String inputIP() {

        Scanner sc = new Scanner(System.in);
        String IPaddress = sc.nextLine();

        boolean flag = false;
        while (!flag) {
            IPaddress = IPaddress + "0000";
            String eu = IPaddress.substring(0, 2);
            String other = IPaddress.substring(0, 3);

            if (other.equals("132")) {
                flag = true;
            } else if (eu.equals("93")) {
                flag = true;
            } else if (IPaddress.substring(0, 3).equals("182")) {
                flag = true;
            } else {
                System.out.println("Invalid. Please Enter again:");
                IPaddress = sc.nextLine();
            }
        }
        return IPaddress;
    }


    public static String adminUsername() {
        Scanner sc = new Scanner(System.in);
        boolean flag = false;
        String username = sc.nextLine();
        while (!flag) {
            if (username.equals("admin")) {

                flag = true;
            } else {
                System.out.println("Incorrect username. \nEnter again");
                username = sc.nextLine();
            }
        }
        return username;
    }

    public static String adminPassword() {
        Scanner sc = new Scanner(System.in);
        boolean flag = false;
        String password = sc.nextLine();
        while (!flag) {
            if (password.equals("admin")) {

                flag = true;
            } else {
                System.out.println("Incorrect Password.. \nEnter again");
                password = sc.nextLine();
            }
        }
        return password;
    }


    public static int getValidIntegerInput() {

        Scanner sc = new Scanner(System.in);
        int value = 0;
        boolean inputValid = false;
        do {
            try {
                value = Integer.valueOf(sc.nextLine());
                inputValid = true;
//                logger.info("User selected " + value);
            } catch (Exception e) {
                System.out.println("This field requires a number value. Please try again");
            }
        } while (!inputValid);

        return value;
    }
}


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

