package org.sample;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		Map<String, Object> rootMap = new HashMap<String, Object>();
		
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		
		rootMap.put("strList", list);
		
		
		Configuration cfg = new Configuration();
		
		cfg.setDirectoryForTemplateLoading( new File("D:/jee-workspace/a/ftl") );
		
		Template template = cfg.getTemplate("test.ftl");
		
		PrintWriter pw = new PrintWriter(System.out); 
		
		template.process(rootMap, pw);
	}

}
