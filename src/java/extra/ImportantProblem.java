/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extra;

import java.util.ArrayList;
import object.Farm;
import object.Problem;
import object.ProgramPlan;

/**
 *
 * @author RubySenpaii
 */
public class ImportantProblem {
    private String municipalityName;
    private String barangayName;
    private double totalMinor;
    private double totalMajor;
    private double plantableArea;
    private Problem problem;
    private ArrayList<Farm> farms;
    private ProgramPlan programPlan;
    private int farmCount;
    private int farmAffected;

    public ImportantProblem() {
        
    }
    
    /**
     * @return the problem
     */
    public Problem getProblem() {
        return problem;
    }

    /**
     * @param problem the problem to set
     */
    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    /**
     * @return the farms
     */
    public ArrayList<Farm> getFarms() {
        return farms;
    }

    /**
     * @param farms the farms to set
     */
    public void setFarms(ArrayList<Farm> farms) {
        this.farms = farms;
    }

    /**
     * @return the programPlan
     */
    public ProgramPlan getProgramPlan() {
        return programPlan;
    }

    /**
     * @param programPlan the programPlan to set
     */
    public void setProgramPlan(ProgramPlan programPlan) {
        this.programPlan = programPlan;
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
     * @return the totalMinor
     */
    public double getTotalMinor() {
        return totalMinor;
    }

    /**
     * @param totalMinor the totalMinor to set
     */
    public void setTotalMinor(double totalMinor) {
        this.totalMinor = totalMinor;
    }

    /**
     * @return the totalMajor
     */
    public double getTotalMajor() {
        return totalMajor;
    }

    /**
     * @param totalMajor the totalMajor to set
     */
    public void setTotalMajor(double totalMajor) {
        this.totalMajor = totalMajor;
    }

    /**
     * @return the plantableArea
     */
    public double getPlantableArea() {
        return plantableArea;
    }

    /**
     * @param plantableArea the plantableArea to set
     */
    public void setPlantableArea(double plantableArea) {
        this.plantableArea = plantableArea;
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
     * @return the farmCount
     */
    public int getFarmCount() {
        return farmCount;
    }

    /**
     * @param farmCount the farmCount to set
     */
    public void setFarmCount(int farmCount) {
        this.farmCount = farmCount;
    }

    /**
     * @return the farmAffected
     */
    public int getFarmAffected() {
        return farmAffected;
    }

    /**
     * @param farmAffected the farmAffected to set
     */
    public void setFarmAffected(int farmAffected) {
        this.farmAffected = farmAffected;
    }
}
