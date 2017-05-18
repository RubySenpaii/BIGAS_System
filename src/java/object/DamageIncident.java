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
public class DamageIncident {
    public static final String TABLE_NAME = "DamageIncident";
    public static final String COLUMN_DAMAGEINCIDENTID = "DamageIncidentID";
    public static final String COLUMN_PLANTINGREPORTID = "PlantingReportID";
    public static final String COLUMN_CROPSTAGE = "CropStage";
    public static final String COLUMN_DAMAGEDYEAR = "DamagedYear";
    public static final String COLUMN_DAMAGEDMONTH = "DamagedMonth";
    public static final String COLUMN_DAMAGEDDAY = "DamagedDay";
    public static final String COLUMN_STATUS = "IncidentStatus";
    public static final String COLUMN_PROBLEMREPORTED = "ProblemReported";
    public static final String COLUMN_ESCALATIONLEVEL = "EscalationLevel";
    public static final String COLUMN_REPORTEDBY = "ReportedBy";
    public static final String COLUMN_APPROVEDBY = "ApprovedBy";
    public static final String COLUMN_DEPLOYEDID = "DeployedID";
    public static final String COLUMN_REMARKS = "Remarks";
    
    private int damageIncidentID;
    private int plantingReportID;
    private String cropStage;
    private int damagedYear;
    private int damagedMonth;
    private int damagedDay;
    private String status;
    private int problemReported;
    private String escalationLevel;
    private int reportedBy;
    private int approvedBy;
    private int deployedID;
    private String remarks;
    
    //additional attributes
    private int farmsAffected;
    private double totalAreaDamaged;
    private double totalAreaAffected;
    private String farmName;
    private String seedVarietyName;
    private String fertilizerName;
    private double seedsPlanted;
    private String problemName;
    private String problemDescription;
    private String municipalityName;
    private String barangayName;
    private String reportedName;
    private double plantableSize;
    
    public DamageIncident() {
        
    }

    /**
     * @return the damageIncidentID
     */
    public int getDamageIncidentID() {
        return damageIncidentID;
    }

    /**
     * @param damageIncidentID the damageIncidentID to set
     */
    public void setDamageIncidentID(int damageIncidentID) {
        this.damageIncidentID = damageIncidentID;
    }

    /**
     * @return the plantingReportID
     */
    public int getPlantingReportID() {
        return plantingReportID;
    }

    /**
     * @param plantingReportID the plantingReportID to set
     */
    public void setPlantingReportID(int plantingReportID) {
        this.plantingReportID = plantingReportID;
    }

    /**
     * @return the damagedYear
     */
    public int getDamagedYear() {
        return damagedYear;
    }

    /**
     * @param damagedYear the damagedYear to set
     */
    public void setDamagedYear(int damagedYear) {
        this.damagedYear = damagedYear;
    }

    /**
     * @return the damagedMonth
     */
    public int getDamagedMonth() {
        return damagedMonth;
    }

    /**
     * @param damagedMonth the damagedMonth to set
     */
    public void setDamagedMonth(int damagedMonth) {
        this.damagedMonth = damagedMonth;
    }

    /**
     * @return the damagedDay
     */
    public int getDamagedDay() {
        return damagedDay;
    }

    /**
     * @param damagedDay the damagedDay to set
     */
    public void setDamagedDay(int damagedDay) {
        this.damagedDay = damagedDay;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the problemReported
     */
    public int getProblemReported() {
        return problemReported;
    }

    /**
     * @param problemReported the problemReported to set
     */
    public void setProblemReported(int problemReported) {
        this.problemReported = problemReported;
    }

    /**
     * @return the escalationLevel
     */
    public String getEscalationLevel() {
        return escalationLevel;
    }

    /**
     * @param escalationLevel the escalationLevel to set
     */
    public void setEscalationLevel(String escalationLevel) {
        this.escalationLevel = escalationLevel;
    }

    /**
     * @return the reportedBy
     */
    public int getReportedBy() {
        return reportedBy;
    }

    /**
     * @param reportedBy the reportedBy to set
     */
    public void setReportedBy(int reportedBy) {
        this.reportedBy = reportedBy;
    }

    /**
     * @return the approvedBy
     */
    public int getApprovedBy() {
        return approvedBy;
    }

    /**
     * @param approvedBy the approvedBy to set
     */
    public void setApprovedBy(int approvedBy) {
        this.approvedBy = approvedBy;
    }

    /**
     * @return the cropStage
     */
    public String getCropStage() {
        return cropStage;
    }

    /**
     * @param cropStage the cropStage to set
     */
    public void setCropStage(String cropStage) {
        this.cropStage = cropStage;
    }

    /**
     * @return the farmsAffected
     */
    public int getFarmsAffected() {
        return farmsAffected;
    }

    /**
     * @param farmsAffected the farmsAffected to set
     */
    public void setFarmsAffected(int farmsAffected) {
        this.farmsAffected = farmsAffected;
    }

    /**
     * @return the totalAreaDamaged
     */
    public double getTotalAreaDamaged() {
        return totalAreaDamaged;
    }

    /**
     * @param totalAreaDamaged the totalAreaDamaged to set
     */
    public void setTotalAreaDamaged(double totalAreaDamaged) {
        this.totalAreaDamaged = totalAreaDamaged;
    }

    /**
     * @return the totalAreaAffected
     */
    public double getTotalAreaAffected() {
        return totalAreaAffected;
    }

    /**
     * @param totalAreaAffected the totalAreaAffected to set
     */
    public void setTotalAreaAffected(double totalAreaAffected) {
        this.totalAreaAffected = totalAreaAffected;
    }

    /**
     * @return the farmName
     */
    public String getFarmName() {
        return farmName;
    }

    /**
     * @param farmName the farmName to set
     */
    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    /**
     * @return the seedVarietyName
     */
    public String getSeedVarietyName() {
        return seedVarietyName;
    }

    /**
     * @param seedVarietyName the seedVarietyName to set
     */
    public void setSeedVarietyName(String seedVarietyName) {
        this.seedVarietyName = seedVarietyName;
    }

    /**
     * @return the seedsPlanted
     */
    public double getSeedsPlanted() {
        return seedsPlanted;
    }

    /**
     * @param seedsPlanted the seedsPlanted to set
     */
    public void setSeedsPlanted(double seedsPlanted) {
        this.seedsPlanted = seedsPlanted;
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
     * @return the municipalityName
     */
    public String getMunicipalityName() {
        return municipalityName;
    }

    /**
     * @param municipalityName the municipalityName to set
     */
    public void setMunicipalityName(String municipalityName) {
        this.municipalityName = municipalityName;
    }

    /**
     * @return the barangayName
     */
    public String getBarangayName() {
        return barangayName;
    }

    /**
     * @param barangayName the barangayName to set
     */
    public void setBarangayName(String barangayName) {
        this.barangayName = barangayName;
    }

    /**
     * @return the problemDescription
     */
    public String getProblemDescription() {
        return problemDescription;
    }

    /**
     * @param problemDescription the problemDescription to set
     */
    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    /**
     * @return the reportedName
     */
    public String getReportedName() {
        return reportedName;
    }

    /**
     * @param reportedName the reportedName to set
     */
    public void setReportedName(String reportedName) {
        this.reportedName = reportedName;
    }

    /**
     * @return the plantableSize
     */
    public double getPlantableSize() {
        return plantableSize;
    }

    /**
     * @param plantableSize the plantableSize to set
     */
    public void setPlantableSize(double plantableSize) {
        this.plantableSize = plantableSize;
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
     * @return the fertilizerName
     */
    public String getFertilizerName() {
        return fertilizerName;
    }

    /**
     * @param fertilizerName the fertilizerName to set
     */
    public void setFertilizerName(String fertilizerName) {
        this.fertilizerName = fertilizerName;
    }

    /**
     * @return the remarks
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * @param remarks the remarks to set
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
