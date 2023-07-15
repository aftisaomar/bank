package com.kata.bank.exception;

import java.util.Date;

import lombok.Data;

@Data
public class ExceptionMessage {


    private String message;
    private String description;
    private int status;
    private Date timestamp;
    
}
