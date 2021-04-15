import java.sql.*;


public class DBConnector {
	
    public DBConnector() {
    }

    public ResultSet data;

    public Connection getDBConnection() throws SQLException {

        String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String dbURL = "jdbc:sqlserver://boyce.coe.neu.edu:1433; DatabaseName=Jaya";
        String userid = "INFO6210";
        String passwd = "NEUHusky!";
        Connection conn = null;

        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(dbURL, userid, passwd);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }
    
    public ResultSet runQuery(String query) throws SQLException {
        Connection con = null;
        con = getDBConnection();

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            return rs;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public ResultSet getAllData() throws SQLException {
        String query = "select * from Automobiles";
        // System.out.println(query);
        data = runQuery(query);
        // how to get a list of automobiles from result set
        return data;
    }

//    public ResultSet searchByBrand(String brand) throws SQLException {
//     String query = "select * from Automobiles where brand = " + brand;
//     System.out.println("running query now");
//     return runQuery(query);
//    }
//
//    public ResultSet searchByType(String type) throws SQLException {
//        String query = "select * from Automobiles where type = " + type;
//        System.out.println("running query now");
//        return runQuery(query);
//    }
    // red , color --> select * FROM Automobiles where color = red
    public ResultSet findVehicleDetailsBySearchTermInColumn(String searchTerm, String nameOfColumn) throws SQLException {
        String query = "select * from Automobiles where nameOfColumn" + nameOfColumn + " like " + searchTerm;
        System.out.println("running query now");
        return runQuery(query);
    }
}

