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
public class ProgramRequest {
    public final static String TABLE_NAME = "ProgramRequest";
    public final static String COLUMN_REQUESTID = "RequestID";
    public final static String COLUMN_REQUESTDETAIL = "RequestDetail";
    public final static String COLUMN_REQUESTBY = "RequestedBy";
    public final static String COLUMN_DATEREQUESTED = "DateRequested";
    public final static String COLUMN_REQUESTSTATUS = "RequestStatus";
    public final static String COLUMN_PROGRAMPLANID = "ProgramPlanID";
    public final static String COLUMN_MUNICIPALITYID = "MunicipalityID";
    
    private int requestID;
    private String requestDetail;
    private String requestBy;
    private String dateRequested;
    private String requestStatus;
    private int programPlanID;
    private int municipalityID;
    
    private String programName;
    
    public ProgramRequest() {
        
    }

    /**
     * @return the requestID
     */
    public int getRequestID() {
        return requestID;
    }

    /**
     * @param requestID the requestID to set
     */
    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    /**
     * @return the requestDetail
     */
    public String getRequestDetail() {
        return requestDetail;
    }

    /**
     * @param requestDetail the requestDetail to set
     */
    public void setRequestDetail(String requestDetail) {
        this.requestDetail = requestDetail;
    }

    /**
     * @return the requestBy
     */
    public String getRequestBy() {
        return requestBy;
    }

    /**
     * @param requestBy the requestBy to set
     */
    public void setRequestBy(String requestBy) {
        this.requestBy = requestBy;
    }

    /**
     * @return the dateRequested
     */
    public String getDateRequested() {
        return dateRequested;
    }

    /**
     * @param dateRequested the dateRequested to set
     */
    public void setDateRequested(String dateRequested) {
        this.dateRequested = dateRequested;
    }

    /**
     * @return the programPlanID
     */
    public int getProgramPlanID() {
        return programPlanID;
    }

    /**
     * @param programPlanID the programPlanID to set
     */
    public void setProgramPlanID(int programPlanID) {
        this.programPlanID = programPlanID;
    }

    /**
     * @return the municipalityID
     */
    public int getMunicipalityID() {
        return municipalityID;
    }

    /**
     * @param municipalityID the municipalityID to set
     */
    public void setMunicipalityID(int municipalityID) {
        this.municipalityID = municipalityID;
    }

    /**
     * @return the requestStatus
     */
    public String getRequestStatus() {
        return requestStatus;
    }

    /**
     * @param requestStatus the requestStatus to set
     */
    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    /**
     * @return the programName
     */
    public String getProgramName() {
        return programName;
    }

    /**
     * @param programName the programName to set
     */
    public void setProgramName(String programName) {
        this.programName = programName;
    }
}
