package com.project.utils;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;

public class FastJsonDateFormatSerializer implements ObjectSerializer {

    private final String time_pattern = "yyyy-MM-dd HH:mm:ss";
    private final String date_pattern = "yyyy-MM-dd";

    public FastJsonDateFormatSerializer(){
    }

    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
    	if (object == null) {
    		serializer.out.writeNull();
    		return;
    	}
    	
    	String text = "";
    	if(object instanceof java.sql.Date) {
    		SimpleDateFormat format = new SimpleDateFormat(date_pattern);
    		text = format.format(object);
    	} else {
    		Date date = (Date) object;
            SimpleDateFormat format = new SimpleDateFormat(time_pattern);

            text = format.format(date);
    	}
        
        serializer.write(text);
    }
}