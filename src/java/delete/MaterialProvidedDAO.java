/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package delete;

import db.DBConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import delete.MaterialProvided;

/**
 *
 * @author RubySenpaii
 */
public class MaterialProvidedDAO {
    
    public boolean addMaterialProvided(MaterialProvided materialProvided) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("INSERT INTO " + MaterialProvided.TABLE_NAME + " "
                    + "(" + MaterialProvided.COLUMN_DATEDISTRIBUTED + ", " + MaterialProvided.COLUMN_DATERECEIVED + ", " + MaterialProvided.COLUMN_DEPLOYEDID + ", " 
                    + MaterialProvided.COLUMN_MATERIAL + ", " + MaterialProvided.COLUMN_RECEIVEDBY + ", " + MaterialProvided.COLUMN_RECIPIENT + ") "
                    + "VALUES(?, ?, ?, ?, ?, ?)");
            ps.setString(1, materialProvided.getDateDistributed());
            ps.setString(2, materialProvided.getDateReceived());
            ps.setInt(3, materialProvided.getDeployedID());
            ps.setString(4, materialProvided.getMaterial());
            ps.setInt(5, materialProvided.getReceivedBy());
            ps.setString(6, materialProvided.getRecipient());

            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(MaterialProvidedDAO.class.getName()).log(Level.SEVERE, null, x);
            return false;
        }
        return true;
    }

    public ArrayList<MaterialProvided> getListOfMaterialsProvided() {
        ArrayList<MaterialProvided> materialsProvided = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + MaterialProvided.TABLE_NAME);

            ResultSet rs = ps.executeQuery();
            materialsProvided = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(MaterialProvidedDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return materialsProvided;
    }

    private ArrayList<MaterialProvided> getDataFromResultSet(ResultSet rs) throws SQLException {
        ArrayList<MaterialProvided> materialsProvided = new ArrayList<>();
        while (rs.next()) {
            MaterialProvided materialProvided = new MaterialProvided();
            materialProvided.setDateDistributed(rs.getString(MaterialProvided.COLUMN_DATEDISTRIBUTED));
            materialProvided.setDateReceived(rs.getString(MaterialProvided.COLUMN_DATERECEIVED));
            materialProvided.setDeployedID(rs.getInt(MaterialProvided.COLUMN_DEPLOYEDID));
            materialProvided.setMaterial(rs.getString(MaterialProvided.COLUMN_MATERIAL));
            materialProvided.setReceivedBy(rs.getInt(MaterialProvided.COLUMN_RECEIVEDBY));
            materialProvided.setRecipient(rs.getString(MaterialProvided.COLUMN_RECIPIENT));
            materialsProvided.add(materialProvided);
        }
        return materialsProvided;
    }
}
