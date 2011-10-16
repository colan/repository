package org.sample.test;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mongodb.MongoException;


public class Test {

	/**
	 * @param args
	 * @throws MongoException 
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) {
		String str = "/ds/esdfsdf/xyz.jpg";
		str = str.replaceFirst("(^.*?:\\/\\/[^\\/]*)(.*)", "$2");
		System.out.println(str);
		
//		String reg = "(^.*?:\\/\\/[^\\/]*)";
//		Pattern pattern = Pattern.compile(reg);
//		Matcher m = pattern.matcher("http://adsfadsfasdfasdf/ds/adsfafd");
//		while(m.find()) {
//			System.out.println( m.group(0) );
//		}
	}

}
