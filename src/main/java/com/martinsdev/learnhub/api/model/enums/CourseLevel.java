package com.martinsdev.learnhub.api.model.enums;

public enum CourseLevel {

    BEGINNER(1),
    INTERMEDIATE(2),
    ADVANCED(3);

    private Integer code;

    private CourseLevel(Integer code){
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public static CourseLevel valueOf(Integer code){
        for(CourseLevel value : CourseLevel.values()){ //Pegando todos os valores
            if(value.getCode() == code){
                return value;
            }
        }
        throw new IllegalArgumentException("This code is invalid");
    }
}
