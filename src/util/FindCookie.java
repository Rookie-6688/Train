package util;

import javax.servlet.http.Cookie;

public class FindCookie {
	public  static Cookie getCookie(String name,Cookie[] c){
		if(c!=null){
			for(Cookie cc:c){
				if(cc.getName().equals(name)){
					return cc;
				}
			}
		}
		return null;
	}
}
