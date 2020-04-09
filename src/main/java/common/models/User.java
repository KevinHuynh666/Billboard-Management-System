package common.models;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class consists of the user object and its associated methods.
 *
 * @author Perdana Bailey
 * @author Kevin Huynh
 * @author Jamie Martin
 */
public class User {
    /**
     * The variables of the object User
     */
    public int id;
    public String username;
    public String password;
    public String salt;

    /**
     * An empty constructor just for creating the object.
     */
    public User() {

    }

    /**
     * User object constructor
     *
     * @param username: users username.
     * @param password: users password hash.
     * @param salt:     users password salt.
     */
    public User(String username, String password, String salt) {
        this.username = username;
        this.password = password;
        this.salt = salt;
    }

    /**
     * User object constructor
     *
     * @param id: users id.
     * @param username: users username.
     * @param password: users password.
     * @param salt: users salt.
     */
    public User(int id, String username, String password, String salt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.salt = salt;
    }


    /**
     * Change the password of the user
     *
     * @param newPass, the new password hash.
     */
    public void changePassword(String newPass) {
        this.password = newPass;
    }

    /**
     * Try login with the password for this user.
     *
     * @param pass, the password input
     */
    public boolean tryLogin(String pass) {
        return this.password.equals(pass);
    }

    /**
     * Parses the XML string and returns a User object.
     *
     * @param xml: the xml to be converted as a string.
     * @return User: the User object after converting from XML.
     */
    public static User fromXML(String xml) {
        return new User();
    }

    /**
     * Parses the User object and returns an XML string from it.
     *
     * @param user: the user to be converted to an XML string.
     * @return String: the XML after converting from user object.
     */
    public static String toXML(User user) {
        return "";
    }

    /**
     * Parses the SQL result set and returns a user object.
     *
     * @param rs: the result set from an SQL SELECT query.
     * @return Billboard: the user object after converting from SQL.
     * @throws SQLException: this is thrown when there is an issue with getting values from the query.
     */
    public static User fromSQL(ResultSet rs) throws SQLException {
        return new User(
            rs.getInt("id"),
            rs.getString("username"),
            rs.getString("password"),
            rs.getString("salt"));
    }
}
