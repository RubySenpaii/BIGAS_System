/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import db.DBConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import object.Notification;

/**
 *
 * @author RubySenpaii
 */
public class NotificationDAO {

    public boolean addNotification(Notification notification) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("INSERT INTO " + Notification.TABLE_NAME + " "
                    + "(" + Notification.COLUMN_AUDIENCE + ", " + Notification.COLUMN_DURATION + ", " + Notification.COLUMN_EMPLOYEEID + ", "
                    + Notification.COLUMN_FLAG + ", " + Notification.COLUMN_MESSAGE + ", " + Notification.COLUMN_STARTDATE + ", "
                    + Notification.COLUMN_TYPE + ") "
                    + "VALUES(?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, notification.getAudience());
            ps.setInt(2, notification.getDuration());
            ps.setInt(3, notification.getEmployeeID());
            ps.setInt(4, notification.getFlag());
            ps.setString(5, notification.getMessage());
            ps.setString(6, notification.getStartDate());
            ps.setString(7, notification.getType());

            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(NotificationDAO.class.getName()).log(Level.SEVERE, null, x);
            return false;
        }
        return true;
    }

    public ArrayList<Notification> getListOfNotifications() {
        ArrayList<Notification> notifications = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + Notification.TABLE_NAME);

            ResultSet rs = ps.executeQuery();
            notifications = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(NotificationDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return notifications;
    }

    public ArrayList<Notification> getListOfNotificationsForPAO() {
        ArrayList<Notification> notifications = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * "
                    + "FROM " + Notification.TABLE_NAME
                    + " WHERE Audience like '%PAO%'");

            ResultSet rs = ps.executeQuery();
            notifications = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(NotificationDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return notifications;
    }

    public ArrayList<Notification> getListOfNotificationsForMAO() {
        ArrayList<Notification> notifications = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * "
                    + "FROM " + Notification.TABLE_NAME
                    + " WHERE Audience like '%MAO%'");

            ResultSet rs = ps.executeQuery();
            notifications = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(NotificationDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return notifications;
    }

    public ArrayList<Notification> getListOfNotificationsForTechnician() {
        ArrayList<Notification> notifications = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * "
                    + "FROM " + Notification.TABLE_NAME
                    + " WHERE Audience like '%Technician%'");

            ResultSet rs = ps.executeQuery();
            notifications = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(NotificationDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return notifications;
    }

    private ArrayList<Notification> getDataFromResultSet(ResultSet rs) throws SQLException {
        ArrayList<Notification> notifications = new ArrayList<>();
        while (rs.next()) {
            Notification notification = new Notification();
            notification.setAudience(rs.getString(Notification.COLUMN_AUDIENCE));
            notification.setDuration(rs.getInt(Notification.COLUMN_DURATION));
            notification.setEmployeeID(rs.getInt(Notification.COLUMN_EMPLOYEEID));
            notification.setFlag(rs.getInt(Notification.COLUMN_FLAG));
            notification.setMessage(rs.getString(Notification.COLUMN_MESSAGE));
            notification.setStartDate(rs.getString(Notification.COLUMN_STARTDATE));
            notification.setType(rs.getString(Notification.COLUMN_TYPE));
            notifications.add(notification);
        }
        return notifications;
    }
}
