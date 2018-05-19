/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alissonrubim.Models.Util;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author alisson.rubim
 */
public class Format {
    public static String DateToString(Date d, String format){
        DateFormat df = new SimpleDateFormat(format);
        return df.format(d);
    }
    
    public static java.util.Date StringToDate(String d, String format) throws ParseException{
        DateFormat df = new SimpleDateFormat(format);
        return df.parse(d);
    }
}
