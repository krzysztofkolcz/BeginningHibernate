package com.kkolcz.types;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GenericResponse extends BaseObject {
    @JsonProperty("Kod")
    private Integer code = 0;
    @JsonProperty("Informacja")
    private String information;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}

