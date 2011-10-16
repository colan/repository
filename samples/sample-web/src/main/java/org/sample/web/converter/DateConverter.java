package org.sample.web.converter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.util.StrutsTypeConverter;

import com.opensymphony.xwork2.conversion.TypeConversionException;

public class DateConverter extends StrutsTypeConverter {

	@SuppressWarnings("unchecked")
	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		
		Date date = null;
		String str = values[0];
		try {
			if(StringUtils.isNotBlank(str)) {
				str = StringUtils.trimToEmpty(str);
				if(str.length() == 10) { //yyyy-MM-dd
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					date = sdf.parse(str);
				} else if(str.length() == 16) { //yyyy-MM-dd HH:mm
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
					date = sdf.parse(str);
				} else if(str.length() == 19) { //yyyy-MM-dd HH:mm:ss
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					date = sdf.parse(str);
				} else {
					throw new TypeConversionException("error date format");
				}
			}
		} catch(Exception e) {
			throw new TypeConversionException(e);
		}
		
		return date;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String convertToString(Map context, Object o) {
		Date date = (Date) o;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
}
