package server.database.billboard;

import common.models.Billboard;
import server.database.ObjectHandler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * This class is responsible for all the billboard object interactions with the database.
 *
 * @author Perdana Bailey
 * @author Kevin Huynh
 * @author Jamie Martin
 */
public class BillboardHandler implements ObjectHandler<Billboard> {
    // This is the database connection
    Connection connection;

    // This is the mock "database" used for testing
    List<Billboard> mockdb = new ArrayList<Billboard>();

    /**
     * The BillboardHandler Constructor.
     *
     * @param connection: This is the database connection from DataService.java.
     */
    public BillboardHandler(Connection connection) {
        this.connection = connection;
    }

    /**
     * Selects a billboard in the database based off the billboard name.
     *
     * @param id: this is the requested billboard id
     * @return Optional<Billboard>: this returns the billboard or an optional empty value.
     * @throws SQLException: this exception returns when there is an issue fetching data from the database.
     */
    public Optional<Billboard> get(int id) throws SQLException {
        // Check that it's not in testing mode
        if (this.connection != null) {
            // Attempt to query the database
            Statement sqlStatement = this.connection.createStatement();
            // Create a query that selects billboards based on the name and execute the query
            String query = "SELECT * FROM BILLBOARD WHERE billboard.id = '" + id + "'";

            ResultSet result = sqlStatement.executeQuery(query);

            // Use the result of the database query to create billboard object and return it
            while (result.next()) {
                return Optional.of(Billboard.fromSQL(result));
            }
            sqlStatement.close();
        } else {
            // Loop through and find the billboard with the requested name or return an optional empty value
            for (Billboard billboard : this.mockdb) {
                if (billboard.id == id) {
                    return Optional.of(billboard);
                }
            }
        }
        // If it fails to get a result, return Optional empty
        return Optional.empty();
    }

    /**
     * Selects a billboard in the database based off the billboard name.
     *
     * @param billboardName: this is the requested billboard name
     * @return Optional<Billboard>: this returns the billboard or an optional empty value.
     * @throws SQLException: this exception returns when there is an issue fetching data from the database.
     */
    public Optional<Billboard> get(String billboardName) throws SQLException {
        // Check that it's not in testing mode
        if (this.connection != null) {
            // Attempt to query the database
            Statement sqlStatement = this.connection.createStatement();
            // Create a query that selects billboards based on the name and execute the query
            String query = "SELECT * FROM BILLBOARD WHERE billboard.name = '" + billboardName + "'";

            ResultSet result = sqlStatement.executeQuery(query);

            // Use the result of the database query to create billboard object and return it
            while (result.next()) {
                return Optional.of(Billboard.fromSQL(result));
            }
            sqlStatement.close();
        } else {
            // Loop through and find the billboard with the requested name or return an optional empty value
            for (Billboard billboard : this.mockdb) {
                if (billboard.name.equals(billboardName)) {
                    return Optional.of(billboard);
                }
            }
        }
        // If it fails to get a result, return Optional empty
        return Optional.empty();
    }

    /**
     * Selects all billboards in the database
     *
     * @return Optional<List < Billboard>>: this returns the list of billboards requested or a optional empty value.
     * @throws SQLException: this exception returns when there is an issue fetching data from the database.
     */
    public List<Billboard> getAll() throws Exception {
        // Billboard to be returned
        List<Billboard> billboards = new ArrayList<>();

        // Check that it's not in testing mode
        if (this.connection != null) {
            // Attempt to query the database
            Statement sqlStatement = this.connection.createStatement();
            // Create a query that selects billboards based on the lock and execute the query
            String query = "SELECT * FROM BILLBOARD";

            ResultSet result = sqlStatement.executeQuery(query);

            // Use the result of the database query to create billboard object and add it to the returned list
            while (result.next()) {
                billboards.add(Billboard.fromSQL(result));
            }
            sqlStatement.close();
        } else {
            // Loop through and find the billboard with the lock status and add to billboards
            billboards = this.mockdb;
        }

      return billboards;
    }

    /**
     * Selects all billboards in the database dependant on if the billboard has been scheduled or not (locked).
     *
     * @param lock: this is the boolean that determines the type of billboard to return.
     * @return Optional<List < Billboard>>: this returns the list of billboards requested or a optional empty value.
     * @throws SQLException: this exception returns when there is an issue fetching data from the database.
     */
    public List<Billboard> getAll(boolean lock) throws Exception {
        // Billboard to be returned
        List<Billboard> billboards = new ArrayList<>();

        // Check that it's not in testing mode
        if (this.connection != null) {
            // Attempt to query the database
            Statement sqlStatement = this.connection.createStatement();
            // Create a query that selects billboards based on the lock and execute the query
            String query = "SELECT * FROM billboard WHERE billboard.locked = " + lock;

            ResultSet result = sqlStatement.executeQuery(query);

            // Use the result of the database query to create billboard object and add it to the returned list
            while (result.next()) {
                billboards.add(Billboard.fromSQL(result));
            }
        } else {
            // Loop through and find the billboard with the lock status and add to billboards
            billboards = this.mockdb;
        }

        return billboards;
    }

    /**
     * Insert a billboard into the database.
     *
     * @param billboard: this is the requested billboard to insert.
     * @throws SQLException: this exception returns when there is an issue sending data to the database.
     */
    public void insert(Billboard billboard) throws SQLException {
        // Check that it's not in testing mode
        if (this.connection != null) {
            // Attempt to query the database
            Statement sqlStatement = connection.createStatement();
            // Create a query that inserts the billboard and executes the query, return if the query executed
            String query = "INSERT INTO billboard " +
                "(userId, name, message, " +
                "messageColor, picture, backgroundColor," +
                " information, informationColor, locked)" +
                "VALUES(" + billboard.userId +
                "," + billboard.name +
                "," +  billboard.message +
                "," + billboard.messageColor +
                "," + Arrays.toString(billboard.picture) +
                "," + billboard.backgroundColor +
                "," + billboard.information +
                "," + billboard.informationColor +
                "," + billboard.locked + ")";

            sqlStatement.executeUpdate(query);
        } else {
            mockdb.add(billboard);
        }
    }

    /**
     * Update a billboard in the database.
     *
     * @param billboard: this is the requested billboard to update.
     * @throws SQLException: this exception returns when there is an issue sending data to the database.
     */
    public void update(Billboard billboard) throws SQLException {
        // Check that it's not in testing mode
        if (this.connection != null) {
            // Attempt to query the database
            Statement sqlStatement = connection.createStatement();

            // Create a query that inserts the billboard and executes the query, return if the query executed
            String query = "UPDATE billboard SET name = '" + billboard.name + "', message = '" + billboard.message + "', messageColor ='" + billboard.messageColor +
                "', picture = '" + Arrays.toString(billboard.picture) + "', backgroundColor = '" + billboard.backgroundColor +
                "', information = '" + billboard.information + "', informationColor = '" + billboard.informationColor + "', locked =" + billboard.locked +
                " WHERE ID = " + billboard.id;

            sqlStatement.executeUpdate(query);
        } else {
            // Loop through mock database and find the billboard to update, then update it and return true.
            for (Billboard mockBillboard : this.mockdb) {
                if (mockBillboard.name.equals(billboard.name)) {
                    mockBillboard = billboard;
                }
            }
        }
    }

    /**
     * Delete a billboard from the database.
     *
     * @param billboard: this is the requested billboard to delete.
     * @throws SQLException:
     */
    public void delete(Billboard billboard) throws SQLException {
        // Check that it's not in testing mode
        if (this.connection != null) {
            // Attempt to query the database
            Statement sqlStatement = connection.createStatement();
            // Create a query that inserts the billboard and executes the query, return if the query executed
            String query = "DELETE FROM billboard WHERE ID = " + billboard.id;

            sqlStatement.executeUpdate(query);
        } else {
            // Delete billboard
            mockdb.remove(billboard);
        }
    }
}
