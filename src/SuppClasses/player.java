package SuppClasses;

public class player { //player object that is added to the server database

    public String firstName, lastName, userName, pwd, IPAdress, status;
       public int age;
        public player(String firstname, String lastname, String Username, String Password, int age, String IPAddress, String status) {
            this.age = age;
            this.firstName = firstname;
            this.lastName = lastname;
            this.pwd = Password;
            this.userName = Username;
            this.status = status;
            this.IPAdress=IPAddress;
        }
    }

