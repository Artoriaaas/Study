package Core.Entities;

import Utilities.DataValidation;

public class Mountain {

    private String mountainCode;
    private String mountain;
    private String province;
    private String description;

    //Constructors
    public Mountain() {
    }

    public Mountain(String mountainCode, String mountain, String province, String description) {
        this.mountainCode = mountainCode;
        this.mountain = mountain;
        this.province = province;
        this.description = description;
    }

    //Setter and Getter...
    //Methods...
    //--------------------------------------------------
    //--------------------------------------------------
    public String getMountainCode() {
        return mountainCode;
    }

    public void setMountainCode(String mountainCode) throws Exception{
        if (!DataValidation.checkNumberInMinMax(Integer.parseInt(mountainCode), 1, 13)) {
            throw new Exception("Mountain code is invalid.Should in range[1-13]");
        }
        this.mountainCode = mountainCode;
    }

    public String getMountain() {
        return mountain;
    }

    public void setMountain(String mountain) {
        this.mountain = mountain;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Mountain{" + "mountainCode=" + mountainCode + ", mountain=" + mountain + ", province=" + province + ", description=" + description + '}';
    }

}
