package com.qa.pages;

import java.util.Hashtable;

public class calc_Webapp {
	
	public synchronized String calc_Webapp_page(String locator){
		String loc = null;
		try{
			
			Hashtable<String, String> h = new Hashtable<>();
			h.put("numone", "id=1");
			h.put("num5", "id=5");
			h.put("num3", "id=3");
			
			loc = h.get(locator);
			
		}catch(Exception e){
			loc = null;
		}
		
		return loc;	
	}

}
