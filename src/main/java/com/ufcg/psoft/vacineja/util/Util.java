package com.ufcg.psoft.vacineja.util;

import java.util.List;

public class Util {

	public static String stringfyList(List<?> objList) {
		
		StringBuilder str = new StringBuilder();
		for(Object obj:objList) {
			str.append(obj.toString());
			str.append("\n---------------------\n");
		}
		return str.toString();
	}
	
}
