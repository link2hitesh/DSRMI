package ServerImplementation;

public class player {

        String firstName, lastName, userName, pwd, IPAdress, status;
        int age;

        player(String firstname, String lastname, String Username, String Password, int age, String status, String IPAdress)
        {
            this.age = age;
            this.firstName = firstname;
            this.lastName = lastname;
            this.pwd = Password;
            this.userName = Username;
            this.status = status;
            this.IPAdress=IPAdress;
        }

        public void assignIPAdress()
        {

        }
    }

