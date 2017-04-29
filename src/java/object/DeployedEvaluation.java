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
public class DeployedEvaluation {
    public static final String TABLE_NAME = "DeployedEvaluation";
    public static final String COLUMN_DEPLOYEDID = "DeployedID";
    public static final String COLUMN_RESPONDENTNAME = "RespondentName";
    public static final String COLUMN_EVALUATIONVALUES = "EvaluationValues";
    
    private int deployedID;
    private String respondentName;
    private String evaluationValues;
    
    public DeployedEvaluation() {
        
    }

    /**
     * @return the deployedID
     */
    public int getDeployedID() {
        return deployedID;
    }

    /**
     * @param deployedID the deployedID to set
     */
    public void setDeployedID(int deployedID) {
        this.deployedID = deployedID;
    }

    /**
     * @return the respondentName
     */
    public String getRespondentName() {
        return respondentName;
    }

    /**
     * @param respondentName the respondentName to set
     */
    public void setRespondentName(String respondentName) {
        this.respondentName = respondentName;
    }

    /**
     * @return the evaluationValues
     */
    public String getEvaluationValues() {
        return evaluationValues;
    }

    /**
     * @param evaluationValues the evaluationValues to set
     */
    public void setEvaluationValues(String evaluationValues) {
        this.evaluationValues = evaluationValues;
    }
}
