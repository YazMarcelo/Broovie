package com.broovie.equipe.broovie.models;

import java.util.Date;

public class DateTime extends java.util.Date {

    public DateTime(long readLong) {
        super(readLong);
    }

    public DateTime(Date date) {
        super(date.getTime());
    }       
}