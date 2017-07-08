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
public class ProgramSurvey {
    public static final String TABLE_NAME = "ProgramSurvey";
    public static final String COLUMN_SURVEYID = "SurveyID";
    public static final String COLUMN_TYPE = "Type";
    public static final String COLUMN_QUESTIONNO = "QuestionNo";
    public static final String COLUMN_QUESTION = "Question";
    
    private int surveyID;
    private String type;
    private int questionNo;
    private String question;
    
    public ProgramSurvey() {
        
    }

    /**
     * @return the surveyID
     */
    public int getSurveyID() {
        return surveyID;
    }

    /**
     * @param surveyID the surveyID to set
     */
    public void setSurveyID(int surveyID) {
        this.surveyID = surveyID;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the questionNo
     */
    public int getQuestionNo() {
        return questionNo;
    }

    /**
     * @param questionNo the questionNo to set
     */
    public void setQuestionNo(int questionNo) {
        this.questionNo = questionNo;
    }

    /**
     * @return the question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * @param question the question to set
     */
    public void setQuestion(String question) {
        this.question = question;
    }
}
