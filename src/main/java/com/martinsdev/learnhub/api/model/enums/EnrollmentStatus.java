package com.martinsdev.learnhub.api.model.enums;

public enum EnrollmentStatus {

    ACTIVE(1),
    COMPLETED(2),
    CANCELED(3);

    private Integer code;

    public Integer getCode() {
        return code;
    }

    private EnrollmentStatus(Integer code){
        this.code = code;
    }

    public static EnrollmentStatus valueOf(Integer code){
        for(EnrollmentStatus value : EnrollmentStatus.values()){
            if(value.getCode() == code){
                return value;
            }
        }
        throw new IllegalArgumentException("This code is invalid");
    }
}
