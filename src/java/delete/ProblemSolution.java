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
public class ProblemSolution {
    public static final String TABLE_NAME = "ProblemSolution";
    public static final String COLUMN_PROBLEMID = "ProblemID";
    public static final String COLUMN_PROGRAMID = "ProgramID";
    
    private int problemID;
    private int programID;
    
    public ProblemSolution() {
        
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
     * @return the programID
     */
    public int getProgramID() {
        return programID;
    }

    /**
     * @param programID the programID to set
     */
    public void setProgramID(int programID) {
        this.programID = programID;
    }
}
