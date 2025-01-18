package Core.Entities;

import Utilities.DataValidation;

public class Mountain {

    private String mountainCode;
    private int numOfRegistration;
    private int totalCost;
    

    //Constructors

    public Mountain() {
     
    }

    public Mountain(String mountainCode, int numOfRegistration, int totalCost) {
        this.mountainCode = mountainCode;
        this.numOfRegistration = numOfRegistration;
        this.totalCost = totalCost;
    }

    public String getMountainCode() {
        return mountainCode;
    }

    public int getNumOfRegistration() {
        return numOfRegistration;
    }

    public void setNumOfRegistration(int numOfRegistration) {
        this.numOfRegistration = numOfRegistration;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public void setMountainCode(String mountainCode) throws Exception {
        if (!DataValidation.checkNumberInMinMax(Integer.parseInt(mountainCode), 1, 13)) {
            throw new Exception("Mountain code is invalid.Should in range[1-13]");
        }
        this.mountainCode = mountainCode;
    }

    @Override
    public String toString() {
        return "Mountain{" + "mountainCode=" + mountainCode + ", numOfRegistration=" + numOfRegistration + ", totalCost=" + totalCost + '}';
    }
    
    
}
