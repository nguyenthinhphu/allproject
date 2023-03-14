package net.javaguides.springmvc.utils;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import net.javaguides.springmvc.common.CommonConstant;

public class MessageUtil {

	    private static Map<String, String> enMsgMap = new HashMap<String, String>();
	    private static Map<String, String> jpMsgMap = new HashMap<String, String>();

	    static {
	        if (jpMsgMap.isEmpty()) {
	            jpMsgMap = loadMessageData(CommonConstant.MESSAGE_JP);
	            jpMsgMap = Collections.unmodifiableMap(jpMsgMap);
	        }
	    }

	    public static String getMessageContent(String key, String language) {
	        String msgResult = null;
	        // Check two map not exist or key invalid
	        if ((enMsgMap == null && jpMsgMap == null) || key == null) {
	            return key;
	        }

	        // get message with prefix
	        if (CommonConstant.LANGUAGE_JP.equals(language)) {
	            msgResult = (String) jpMsgMap.get(key);
	        }

	        // if get msg failure then return key
	        if (msgResult == null) {
	            msgResult = key;
	        }
	        return msgResult;
	    }

	    private static HashMap<String, String> loadMessageData(String fileName) {
	        ResourceBundle resourceBundle = null;
	        String key = null;
	        String value = null;
	        HashMap<String, String> messageMap = new HashMap<String, String>();
	        try {
	            // get resource bundle
	            resourceBundle = ResourceBundle.getBundle(fileName);

	            // load all message in file
	            Enumeration<String> en  = (Enumeration<String>) resourceBundle.getKeys();
	            while (en.hasMoreElements()) {
	                key = (String) en.nextElement();
	                value = resourceBundle.getString(key);
	                messageMap.put(key, value);
	            }
	        } catch (MissingResourceException e) {
	            System.out.println(e.getMessage());
	        }
	        return messageMap;
	    }
}
