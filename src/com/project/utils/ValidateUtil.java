package com.project.utils;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class ValidateUtil {
	private static Validator validator;
	static{
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    	validator = factory.getValidator();
	}
	/**
	 * Bean验证
	 * @param bean
	 * @param groups
	 * @return
	 */
	public static <T> String ValidateBean(T bean, Class<?>... groups) {
    	
    	Set<ConstraintViolation<T>> msgSet = null;
    	
    	msgSet = validator.validate(bean, groups);
    	List<String> msgList = new LinkedList<String>();
    	for(ConstraintViolation<T> stConstraintViolation : msgSet){
    		if(!msgList.contains(stConstraintViolation.getMessage())) {
    			msgList.add(stConstraintViolation.getMessage());
    		}
    	}
    	
    	if(msgList.size() == 0) {
    		return null;
    	}
    	
    	return msgList.toString();
	}
}
