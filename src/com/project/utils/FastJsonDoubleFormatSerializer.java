package com.project.utils;
import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;

public class FastJsonDoubleFormatSerializer implements ObjectSerializer {


    public FastJsonDoubleFormatSerializer(){
    }

    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
    	if (object == null) {
    		serializer.out.writeNull();
    		return;
    	}
    	
    	String text = object.toString();
    	if(object instanceof Double) {
    		BigDecimal b = new BigDecimal(text);
    		text = b.stripTrailingZeros().toPlainString();
    	} 
        
        serializer.write(text);
    }
}