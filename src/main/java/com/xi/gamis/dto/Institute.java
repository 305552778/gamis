package com.xi.gamis.dto;

public class Institute {
    private Integer instituteID;
    private String instituteName;

    public Institute(Integer instituteID, String instituteName) {
        this.instituteID = instituteID;
        this.instituteName = instituteName;
    }

    public Integer getInstituteID() {
        return instituteID;
    }

    public void setInstituteID(Integer instituteID) {
        this.instituteID = instituteID;
    }

    public String getInstituteName() {
        return instituteName;
    }

    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName;
    }
}
