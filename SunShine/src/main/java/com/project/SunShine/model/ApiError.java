package com.project.SunShine.model;

public class ApiError {

    public ApiError(String code, String error, String description, String detailedDescription) {
        this.code = code;
        this.error = error;
        this.description = description;
        this.detailedDescription = detailedDescription;
    }

    private String code;
    private String error;
    private String description;
    private String detailedDescription;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetailedDescription() {
        return detailedDescription;
    }

    public void setDetailedDescription(String detailedDescription) {
        this.detailedDescription = detailedDescription;
    }

    @Override
    public String toString() {
        return "Error{" +
                "code='" + code + '\'' +
                ", error='" + error + '\'' +
                ", description='" + description + '\'' +
                ", detailedDescription='" + detailedDescription + '\'' +
                '}';
    }
}
