package common.models;

import client.components.table.DisplayAs;
import client.components.table.Editable;
import common.sql.SQLITE;
import common.utils.Picture;
import common.utils.RandomFactory;

import java.awt.*;
import java.io.*;

/**
 * This class consists of the billboard object and its associated methods.
 *
 * @author Perdana Bailey
 * @author Hieu Nghia Huynh
 * @author Jamie Martin
 */
@SQLITE(type="FOREIGN KEY(userId) REFERENCES User(id)")
public class Billboard implements Serializable, Editable {
    /**
     * The variables of the object billboard.
     */

    @SQLITE(type="INTEGER PRIMARY KEY AUTOINCREMENT")
    public int id;

    @SQLITE(type="VARCHAR(255) NOT NULL UNIQUE")
    public String name;

    @SQLITE(type="VARCHAR(255)")
    public String message;

    @SQLITE(type="VARCHAR(7) DEFAULT \"#000000\"")
    public String messageColor = "#000000";

    @SQLITE(type="BLOB")
    public String picture;

    @SQLITE(type="VARCHAR(7) DEFAULT \"#ffffff\"")
    public String backgroundColor = "#ffffff";

    @SQLITE(type="VARCHAR(255)")
    public String information;

    @SQLITE(type="VARCHAR(7) DEFAULT \"#000000\"")
    public String informationColor = "#000000";

    @SQLITE(type="BOOLEAN")
    public boolean locked = false;

    @SQLITE(type="INTEGER NOT NULL")
    public int userId;

    /**
     * An empty constructor just for creating the object.
     */
    public Billboard() {

    }

    /**
     * Billboard object constructor with an id.
     *
     * @param id:               database billboard ID as an int.
     * @param name:             billboard name as a string.
     * @param message:          billboard message as a string.
     * @param messageColor:     billboard message colour as a string.
     * @param picture:          billboard picture as a byte array.
     * @param backgroundColor:  billboard background colour as a string.
     * @param information:      billboard information as a string.
     * @param informationColor: billboard information colour as a string.
     * @param locked:           billboard scheduled as a boolean.
     * @param userId:           owner ID as an int.
     */
    public Billboard(int id,
                     String name,
                     String message,
                     String messageColor,
                     String picture,
                     String backgroundColor,
                     String information,
                     String informationColor,
                     boolean locked,
                     int userId) {
        this.id = id;
        this.name = name;
        this.message = message;
        this.messageColor = messageColor;
        this.picture = picture;
        this.backgroundColor = backgroundColor;
        this.information = information;
        this.informationColor = informationColor;
        this.locked = locked;
        this.userId = userId;
    }

    /**
     * Billboard object constructor without an id.
     *
     * @param name:             billboard name as a string.
     * @param message:          billboard message as a string.
     * @param messageColor:     billboard message colour as a string.
     * @param picture:          billboard picture as a byte array.
     * @param backgroundColor:  billboard background colour as a string.
     * @param information:      billboard information as a string.
     * @param informationColor: billboard information colour as a string.
     * @param locked:           billboard scheduled as a boolean.
     * @param userId:           owner ID as an int.
     */
    public Billboard(
        String name,
        String message,
        String messageColor,
        String picture,
        String backgroundColor,
        String information,
        String informationColor,
        boolean locked,
        int userId) {
        this.name = name;
        this.message = message;
        this.messageColor = messageColor;
        this.picture = picture;
        this.backgroundColor = backgroundColor;
        this.information = information;
        this.informationColor = informationColor;
        this.locked = locked;
        this.userId = userId;
    }


    @DisplayAs(value = "Id", index = 0)
    public int getId() {
        return id;
    }

    @DisplayAs(value = "Billboard Name", index = 1)
    public String getName() {
        return name;
    }

    @DisplayAs(value = "Message", index = 2, editable = true)
    public String getMessage() {
        return message;
    }

    public void setMessage(String m) { message = m; }

    @DisplayAs(value = "Message Colour", index = 3, editable = true)
    public Color getMessageColor() {
        return Color.decode(messageColor);
    }

    public void setMessageColor(Color c) {
        messageColor = String.format("#%02x%02x%02x", c.getRed(), c.getGreen(), c.getBlue());
    }

    @DisplayAs(value = "Picture", index = 4, editable = true)
    public Picture getPicture() {
        return new Picture(picture);
    }

    public void setPicture(Picture p) {
        picture = p.data;
    }

    @DisplayAs(value = "Background Colour", index = 5, editable = true)
    public Color getBackgroundColor() {
        return Color.decode(backgroundColor);
    }

    public void setBackgroundColor(Color c) {
        backgroundColor = String.format("#%02x%02x%02x", c.getRed(), c.getGreen(), c.getBlue());
    }

    @DisplayAs(value = "Information", index = 6, editable = true)
    public String getInformation() {
        return information;
    }

    public void setInformation(String i) {
        information = i;
    }

    @DisplayAs(value = "Information Colour", index = 7, editable = true)
    public Color getInformationColor() {
        return Color.decode(informationColor);
    }

    public void setInformationColor(Color c) {
        informationColor = String.format("#%02x%02x%02x", c.getRed(), c.getGreen(), c.getBlue());
    }

    @DisplayAs(value = "Locked", index = 8)
    public Boolean getLocked() {
        return locked;
    }

    @DisplayAs(value = "User Id", index = 9)
    public int getUserId() {
        return userId;
    }

    @Override
    public boolean isEditable() {
        return true;
    }

    /**
     * Generates a random billboard object with random variables
     * @param userId: the id of the user creating the billboard
     * @return a randomised billboard
     */
    public static Billboard Random(int userId) {
        return new Billboard(
            RandomFactory.String(),
            RandomFactory.String(),
            RandomFactory.Color(),
            null,
            RandomFactory.Color(),
            RandomFactory.String(),
            RandomFactory.Color(),
            false,
            userId
        );
    }
}
