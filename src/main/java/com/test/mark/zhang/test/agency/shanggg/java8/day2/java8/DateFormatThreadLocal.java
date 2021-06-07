package com.test.mark.zhang.test.agency.shanggg.java8.day2.java8;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatThreadLocal {

    //threadLocal 使用注意事项
	private static final ThreadLocal<DateFormat> df = new ThreadLocal<DateFormat>(){
		
		@Override
        protected DateFormat initialValue(){
			return new SimpleDateFormat("yyyyMMdd");
		}
		
	};
	
	public static final Date convert(String source) throws ParseException{
		return df.get().parse(source);
	}

}
