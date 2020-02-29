package com.project.exam.common;

import java.util.ResourceBundle;

public class Configuration {
	static {
		String configFile = "config";
		resourceBundle = ResourceBundle.getBundle(configFile);
	}
	private static ResourceBundle resourceBundle;

	public static final String RECORD_ATTACHMENT_SAVEDIR = "record_attachment_savedir";
	public static final String FRONT_VALID_KEY = "front_valid_key";
	
	/**
	 * @Fields UEDITOR_ATTACHMENT_SAVEDIR : 百度Ueditor文件存储根目录
	 */
	public static final String UEDITOR_FILE_SAVEDIR = "ueditor_file_savedir";
	
	public static String getUeditorFileSavedir() {
		return get(UEDITOR_FILE_SAVEDIR);
	}
	
	public static void reLoadCfg(){
		String configFile = "config";
		resourceBundle = ResourceBundle.getBundle(configFile);
	}
	
	public static String getRecordAttachmentSavedir() {
		return get(RECORD_ATTACHMENT_SAVEDIR);
	}
	
	public static String getFrontValidKey() {
		return get(FRONT_VALID_KEY);
	}
	
	public static String getFromCfg(String key, String cfgName){
		if(cfgName == null) return null;
		String val = null;
		try {
			val = ResourceBundle.getBundle(cfgName).getString(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return val;
	}
	
	public static String get(String key) {
		return resourceBundle.getString(key);
	}

}
