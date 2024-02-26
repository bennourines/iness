package test;

import java.sql.SQLException;
import services.UserService;
public class Main {

    public static void main(String[] args) {
        UserService ps = new UserService();

        try {
           // ps.create(new User(96,"ines ","bennour","bennourines00","ariana","admin","0000","2344","yes",true));
           // ps.create(new User(396,"sirine ","bennour","siii11","ariana","admin","0000","2344","yes",true));
          //  ps.update(new User(1,2222,"sirine ","bennour","siii11","ariana","admin","0000","2344","yes",true));
            //ps.delete(4);
            System.out.println(ps.read());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
