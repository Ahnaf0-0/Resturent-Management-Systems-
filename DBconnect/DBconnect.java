package DBconnect;
import java.sql.*;

public class DBconnect {
    private Connection con;
    private Statement st;
    private ResultSet result;

    private static final String URL = "jdbc:mysql://localhost:3306/rms?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public DBconnect() {}

    public void openConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            st = con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            if (result != null)
                result.close();
            if (st != null)
                st.close();
            if (con != null)
                con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}





//package DBconnect;
//import java.sql.*;
//
//    public class DBconnect {
//    public Connection con;
//    public Statement st;
//    public ResultSet result;
//
//    public DBconnect() {}
//    public void openConnection()
//    {
//        try
//        {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rms?serverTimezone=UTC", "root", "");
//            st = con.createStatement();
//        }
//        catch(Exception e){System.out.println(e.getMessage());}
//    }
//    public void closeConnection()
//    {
//        try
//        {
//            if(con!=null){con.close();}
//            if(st!=null){st.close();}
//            if(result!=null){result.close();}
//        }
//        catch(Exception e){}
//    }
//}
