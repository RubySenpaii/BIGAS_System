/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

/**
 *
 * @author RubySenpaii
 */
public class Problem {
    public static final String TABLE_NAME = "Problem";
    public static final String COLUMN_PROBLEMID = "ProblemID";
    public static final String COLUMN_PROBLEMNAME = "ProblemName";
    public static final String COLUMN_DESCRIPTION = "Description";
    public static final String COLUMN_IMAGE = "Image";
    
    private int problemID;
    private String problemName;
    private String description;
    private String type;
    private String image;
    
    public Problem(){
        
    }

    /**
     * @return the problemID
     */
    public int getProblemID() {
        return problemID;
    }

    /**
     * @param problemID the problemID to set
     */
    public void setProblemID(int problemID) {
        this.problemID = problemID;
    }

    /**
     * @return the problemName
     */
    public String getProblemName() {
        return problemName;
    }

    /**
     * @param problemName the problemName to set
     */
    public void setProblemName(String problemName) {
        this.problemName = problemName;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return the problemType
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the problemType to set
     */
    public void setType(String type) {
        this.type = type;
    }
}
