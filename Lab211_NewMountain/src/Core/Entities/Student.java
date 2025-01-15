package Core.Entities;

import Utilities.DataValidation;

public class Student {

    private String id;
    private String name;
    private String email;
    private String phone;
    private String mountainCode;
    private int tuitionFee;

    //Constructors
    public Student(String id, String name, String phone, String email, String mountainCode) throws Exception {
        setId(id);
        setName(name);
        setPhone(phone);
        setEmail(email);
        setMountainCode(mountainCode);
    }

    public void setId(String value) throws Exception {
        if (!DataValidation.checkStringWithFormat(value, "^[CcDdHhSsQq][Ee]\\d{6}$")) {
            throw new Exception("Id is invalid. The correct format:(S/H/D/Q/C)Exxxxxx, with x is digits");
        }
        this.id = value;
    }

    public void setName(String name) throws Exception {
        if (!DataValidation.checkStringLengthInRange(name, 2, 20) || !DataValidation.checkStringExist(name)) {
            throw new Exception("Name is invalid.");
        }
        this.name = name;
    }

    public void setPhone(String phone) throws Exception {
        if (!DataValidation.checkStringWithFormat(phone, "\\d+") && !DataValidation.checkStringLengthInRange(phone, 10, 10)) {
            throw new Exception("Phone is invalid. The correct format:10 digit");
        }
        this.phone = phone;
    }

    public void setEmail(String email) throws Exception {
        if (!DataValidation.checkStringWithFormat(email, "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")) {
            throw new Exception("Email is invalid.");
        }
        this.email = email;
    }

    public void setMountainCode(String mountainCode) throws Exception {
        if (!DataValidation.checkNumberInMinMax(Integer.parseInt(mountainCode), 1, 13)) {
            throw new Exception("Mountain code is invalid.Should in range[1-13]");
        }
        this.mountainCode = mountainCode;
    }

    public void setTuitionFee(double tutionFee) {
        int defaultFee = 6000000;
        
        if (!DataValidation.checkStringWithFormat(phone, "^(086|096|097|098|039|038|037|036|035|034|033|032|081|082|083|084|085|088|091|094)\\d{7}$")) {
            this.tuitionFee = defaultFee;
        }
        this.tuitionFee = defaultFee - (defaultFee * 35)/100;

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public int getTuitionFee() {
        return tuitionFee;
    }

    public String getEmail() {
        return email;
    }

    //Methods....
    public String getMountainCode() {
        return mountainCode;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s, %s, %d", getId(), getName(), getPhone(), getEmail(), getMountainCode(),getTuitionFee());
    }
}
