package com.exam.Exception;

public class DuplicatePhoneNumberException extends Exception{
    public DuplicatePhoneNumberException(String errorMessage){
        super(errorMessage);
    }
}
