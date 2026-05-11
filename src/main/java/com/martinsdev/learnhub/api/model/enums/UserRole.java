package com.martinsdev.learnhub.api.model.enums;

public enum UserRole {

    ADMIN (1),
    INSTRUCTOR (2),
    STUDENT (3);

    private int code;

    private UserRole(int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }

    //Metodo para transformar um valor númerico em elemento enumerado
    public static UserRole valueOf(int code){
        for(UserRole value : UserRole.values()){
            if(value.getCode() == code){
                return value;
            }
        }
        throw new IllegalArgumentException("This code is invalid");
    }
}
