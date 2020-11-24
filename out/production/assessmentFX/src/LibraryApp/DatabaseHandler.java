package LibraryApp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DatabaseHandler {

    private Connection conn;
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\assessmentFX\\db\\library.db";

    public DatabaseHandler() throws SQLException {
//        createConnection();
        conn = DriverManager.getConnection("jdbc:sqlite:C:\\assessmentFX\\db\\library.db");
    }

    public void createConnection() {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\assessmentFX\\db\\library.db");
                Statement statement = conn.createStatement())
        {
            statement.execute("DROP TABLE IF EXISTS PBooks" );
            statement.execute("CREATE TABLE IF NOT EXISTS PBooks " +
                    "(name TEXT, author TEXT, publicationDate TEXT, publisher TEXT, library TEXT, noCopies INTEGER, isbn TEXT, topics TEXT, " +
                    "timesRead INTEGER, edition INTEGER, bio TEXT, value REAL, keywords TEXT, isOnLoan INTEGER, isOverdue INTEGER, overDueCharge REAL, " +
                    "copiesInStock INTEGER, dueDate TEXT, url TEXT, eBookVersionLink TEXT, isDamaged INTEGER)");
            statement.execute("DROP TABLE IF EXISTS Libraries" );
            statement.execute("CREATE TABLE IF NOT EXISTS Libraries " +
                    "(name TEXT, isOpen INTEGER, openHours TEXT, location TEXT)");
            statement.execute("DROP TABLE IF EXISTS Authors" );
            statement.execute("CREATE TABLE IF NOT EXISTS Authors " +
                    "(name TEXT, isbn TEXT)");
            statement.execute("DROP TABLE IF EXISTS EBooks" );
            statement.execute("CREATE TABLE IF NOT EXISTS EBooks " +
                    "(name TEXT, author TEXT, publicationDate TEXT, publisher TEXT, library TEXT, isbn TEXT, topics TEXT, " +
                    "timesRead INTEGER, edition INTEGER, bio TEXT, value REAL, keywords TEXT, currentReaders INTEGER, url TEXT, openAccess INTEGER, accessExpiresHours TEXT, maximumConcurrentUsers INTEGER, physicalCopyLink TEXT)");
            statement.close();
            conn.close();
        } catch(SQLException e) {
            System.out.println("error connecting to the database, database already exists, ensure app folder is in the C root folder of the C drive");
        }
    }

    public void addPBook(String name, String author, String publicationDate, String publisher, String library, int noCopies, String isbn){
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\assessmentFX\\db\\library.db");)
        {
            String sql = "INSERT INTO PBooks (name, author, publicationDate, publisher, library, noCopies, isbn, topics, timesRead, edition, bio, value, keywords, isOnLoan, isOverdue, overDueCharge, copiesInStock, dueDate, url, eBookVersionLink, isDamaged) " +
                    "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, author);
            statement.setString(3, publicationDate);
            statement.setString(4, publisher);
            statement.setString(5, library);
            statement.setInt(6, noCopies);
            statement.setString(7, isbn);
            statement.setString(8, null);
            statement.setInt(9, 0);
            statement.setInt(10, 0);
            statement.setString(11, null);
            statement.setDouble(12, 0);
            statement.setString(13, null);
            statement.setInt(14, 0);
            statement.setInt(15, 0);
            statement.setDouble(16, 0);
            statement.setInt(17, noCopies);
            statement.setString(18, null);
            statement.setString(19, null);
            statement.setString(20, null);
            statement.setInt(21, 0);


            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
            }
            statement.close();
            conn.close();
        } catch(SQLException e) {
            System.out.println("issue inserting PBook into database");
        }
    }

    public void addEBook(String name, String author, String publicationDate, String publisher, String library, String isbn){
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\assessmentFX\\db\\library.db");)
        {
            String sql = "INSERT INTO EBooks (name, author, publicationDate, publisher, library, isbn, topics, timesRead, edition, bio, value, keywords, currentReaders, url, openAccess, accessExpiresHours, maximumConcurrentUsers, physicalCopyLink) " +
                    "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, author);
            statement.setString(3, publicationDate);
            statement.setString(4, publisher);
            statement.setString(5, library);
            statement.setString(6, isbn);
            statement.setString(7, null);
            statement.setInt(8, 0);
            statement.setInt(9, 0);
            statement.setString(10, null);
            statement.setDouble(11, 0);
            statement.setString(12, null);
            statement.setInt(13, 0);
            statement.setString(14, null);
            statement.setInt(15, 0);
            statement.setString(16, null);
            statement.setInt(17, 10);
            statement.setString(18, null);


            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
            }
            statement.close();
            conn.close();
        } catch(SQLException e) {
            System.out.println("issue inserting EBook into database");
        }
    }


    public void addLibrary(String name, boolean isOpen, String openHours, String location){
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\assessmentFX\\db\\library.db");)
        {
            String sql = "INSERT INTO Libraries (name, isOpen, openHours, location) " +
                    "VALUES(?,?,?,?)";

            int isOpenInt;
            if(isOpen == true) {
                isOpenInt = 1;
            } else {
                isOpenInt = 0;
            }

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            statement.setInt(2, isOpenInt);
            statement.setString(3, openHours);
            statement.setString(4, location);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
            }
            statement.close();
            conn.close();
        } catch(SQLException e) {
            System.out.println("issue inserting EBook into database");
        }
    }

    public ResultSet execQuery(String query) {
        try {
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(query);
            return result;
        } catch(SQLException e) {
            System.out.println("query failed" + e.getMessage());
            return null;
        }
    }


    public boolean execAction(String action) {
        Statement statement = null;
        try {
            statement = conn.createStatement();
            statement.execute(action);
            return true;
        } catch (SQLException e) {
            System.out.println("action failed" + e.getMessage());
            return false;
        }
    }

    public boolean openDatabase() {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
            return true;
        } catch (SQLException e) {
            System.out.println("Couldn't connect to database");
            return false;
        }
    }

    public void closeDatabase() {
        try {
            if(conn !=null) {
                conn.close();
            }
        } catch(SQLException e) {
            System.out.println("Couldn't close connection to database");
        }
    }
}
