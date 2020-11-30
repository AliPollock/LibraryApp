package LibraryApp;

import LibraryApp.Models.*;

import java.sql.*;

/**
 * Singleton class with private constructor which handles interaction with database.
 */

public final class DatabaseHandler {

    private static DatabaseHandler handler = null;

    private Connection conn;
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\assessmentFX\\db\\library.db";


    private DatabaseHandler() throws SQLException {
//        clearDatabase();
        createConnection();
        conn = DriverManager.getConnection("jdbc:sqlite:C:\\assessmentFX\\db\\library.db");
    }

    /**
     * Static method which creates instance of this class.
     * @return an instance of DatabaseHandler
     * @throws SQLException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */

    public static DatabaseHandler getInstance() throws SQLException {
        if(handler==null) {
            handler = new DatabaseHandler();
        }
        return handler;
    }

    /**
     * Method creates connection to database and creates database tables.
     */

    public void createConnection() {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\assessmentFX\\db\\library.db");
                Statement statement = conn.createStatement())
        {
            statement.execute("CREATE TABLE IF NOT EXISTS PBooks " +
                    "(_id INTEGER PRIMARY KEY, name TEXT, author TEXT, publicationDate TEXT, publisher TEXT, topics TEXT, " +
                    "timesRead INTEGER, bio TEXT, isbn TEXT, currentUser INTEGER, isOnLoan INTEGER, isOverdue INTEGER, overDueCharge REAL, " +
                    "dueDate TEXT, damages TEXT)");
//            statement.execute("CREATE TABLE IF NOT EXISTS Libraries " +
//                    "(_libraryId INTEGER PRIMARY KEY, name TEXT, isOpen INTEGER, openHours TEXT, location TEXT)");
            statement.execute("CREATE TABLE IF NOT EXISTS Authors " +
                    "(_authorId INTEGER PRIMARY KEY, authorName TEXT)");
            statement.execute("CREATE TABLE IF NOT EXISTS EBooks " +
                    "(_eBookId INTEGER PRIMARY KEY, name TEXT, author TEXT, publicationDate TEXT, publisher TEXT, topics TEXT, timesRead INTEGER, bio TEXT," +
                    "isbn TEXT, currentUsers INTEGER, accessExpiresHours TEXT)");
            statement.execute("CREATE TABLE IF NOT EXISTS Computers " +
                    "(_computerId INTEGER PRIMARY KEY, isInUse INTEGER, location TEXT)");
            statement.execute("CREATE TABLE IF NOT EXISTS Users " +
                    "(_userId INTEGER PRIMARY KEY, userName TEXT, password TEXT, isAdmin INTEGER, libraryFees REAL, type TEXT)");
            statement.close();
            conn.close();
        } catch(SQLException e) {
            System.out.println("error connecting to the database, database already exists, ensure app folder is in the C root folder of the C drive");
        }
    }


    /**
     * Method deletes tables from database
     */
    public void clearDatabase(){
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\assessmentFX\\db\\library.db");
             Statement statement = conn.createStatement())
        {
            statement.execute("DROP TABLE IF EXISTS PBooks" );
            statement.execute("DROP TABLE IF EXISTS Authors" );
            statement.execute("DROP TABLE IF EXISTS EBooks" );
            statement.execute("DROP TABLE IF EXISTS Computers" );
            statement.execute("DROP TABLE IF EXISTS Users" );
            statement.execute("DROP TABLE IF EXISTS Libraries" );
            statement.close();
            conn.close();
        } catch(SQLException e) {
            System.out.println("error connecting to the database, database already exists, ensure app folder is in the C root folder of the C drive");
        }
    }

    /**
     * This method executes a database query.
     * @param query A valid sql query statement.
     * @return Result set containing data corresponding with query
     */

    public ResultSet execQuery(String query) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\assessmentFX\\db\\library.db");
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(query);
            return result;
        } catch(SQLException e) {
            System.out.println("query failed" + e.getMessage());
            return null;
        }
    }

    /**
     * This method executes a database action.
     * @param action A valid sql action statement.
     * @return boolean indicating if action was successful
     */

    public boolean execAction(String action) {
        Statement statement = null;
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\assessmentFX\\db\\library.db");
            statement = conn.createStatement();
            statement.execute(action);
            return true;
        } catch (SQLException e) {
            System.out.println("action failed" + e.getMessage());
            return false;
        }
    }

    /**
     * Opens connection to database.
     * @return
     */

    public boolean openDatabase() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\assessmentFX\\db\\library.db");
            conn = DriverManager.getConnection(CONNECTION_STRING);
            return true;
        } catch (SQLException e) {
            System.out.println("Couldn't connect to database");
            return false;
        }
    }

    /**
     * Closes connection to database.
     */

    public void closeDatabase() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\assessmentFX\\db\\library.db");
            if(conn !=null) {
                conn.close();
            }
        } catch(SQLException e) {
            System.out.println("Couldn't close connection to database");
        }
    }

    /**
     * This method checks if the author exists in the database and returns author object.
     * If the author doesn't exist in the database, it will be added.
     * @param bookAuthor The author name as a String.
     * @return The author object
     */

    public Author checkIfAuthorExists(String bookAuthor){

        String sqlAuthorQuery = "SELECT _authorId, authorName FROM Authors WHERE authorName = '" + bookAuthor + "'";
        ResultSet existingAuthor = execQuery(sqlAuthorQuery);


        Author authorInstance =null;
        String authorName = null;
        int existingAuthorId = 0;

        try {
            while (existingAuthor.next()) {
                existingAuthorId = existingAuthor.getInt("_authorId");
                authorName = existingAuthor.getString("authorName");
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }


        if(authorName != null) { //author already exists in db
            authorInstance = new Author(existingAuthorId, bookAuthor);
        } else { //author doesn't exist in db
            authorInstance = new Author(bookAuthor);
            String sqlAuthorString = "INSERT INTO Authors (_authorId, authorName) Values (" + authorInstance.get_id() + ", '" + authorInstance.getName() + "')";
            execAction(sqlAuthorString);
        }

        return authorInstance;
    }

    /**
     * This method checks if the User exists in the database.
     * If the author doesn't exist in the database, it will be added.
     * @param user The username of the user as a String.
     * @return boolean indicating whether the user exists in the database.
     * @throws SQLException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */

    public boolean checkIfUserExists(String user) throws SQLException {

        String sqlCheckUser = "SELECT userName, password FROM Users WHERE userName = '" + user + "'";
        ResultSet existingUser = execQuery(sqlCheckUser);


        User userInstance =null;
        String userName = null;

        try {
            while (existingUser.next()) {
                userName = existingUser.getString("userName");
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }


        if(userName != null) { //user exists in db
            return true;
        } else {
            return false; //user doesn't exist in db
        }

    }

}
