package com.xmbestone.qss.util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.text.*;
import java.util.Calendar;
import java.util.Date;

public class StringFormatter
{

    private static SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static DateFormat dateFormat4String = DateFormat.getDateInstance();

    public static String Replace(String source, String oldString, String newString)
    {
        StringBuffer output = new StringBuffer();
        int lengthOfSource = source.length();
        int lengthOfOld = oldString.length();
        int posStart;
        int pos;
        for(posStart = 0; (pos = source.indexOf(oldString, posStart)) >= 0; posStart = pos + lengthOfOld)
        {
            output.append(source.substring(posStart, pos));
            output.append(newString);
        }

        if(posStart < lengthOfSource)
            output.append(source.substring(posStart));
        return output.toString();
    }

    public static String toHtmlInput(String str)
    {
        if((str == null) || (str.equals("null")))
        {
            return null;
        } else
        {
            String html = new String(str);
            html = Replace(html, "&", "&amp;");
            html = Replace(html, "<", "&lt;");
            html = Replace(html, ">", "&gt;");
            return html;
        }
    }

    public static String toHtml(String str)
    {
    	if((str == null) || (str.equals("null")))
        {
            return null;
        } else
        {
            String html = new String(str);
            html = toHtmlInput(html);
            html = Replace(html, "\r\n", "\n");
            html = Replace(html, "\n", "<br>\n");
            html = Replace(html, "\t", "    ");
            html = Replace(html, "  ", " &nbsp;");
            return html;
        }
    }

    public static String toSql(String str)
    {
        String sql = new String(str);
        return Replace(sql, "'", "''");
    }

    public static String dateTonull(Object obj)
    {
    	if((obj == null) || (obj.equals("null")) || obj.equals(""))
            return "";
        if(obj.toString().substring(0, 10).equals("1900-01-01"))
            return "";
        else
            return obj.toString().substring(0, 10);
    }

    public static int parseInt(Object object)
    {
        try
        {
            return Integer.parseInt(object.toString());
        }
        catch(Exception e)
        {
            return 0;
        }
    }

    public static long parseLong(Object object)
    {
        try
        {
            return Long.parseLong(object.toString());
        }
        catch(Exception e)
        {
            return 0L;
        }
    }

    public static double parseDouble(Object object)
    {
        try
        {
            return Double.parseDouble(object.toString());
        }
        catch(Exception e)
        {
            return 0.0D;
        }
    }

    public static float parseFloat(Object object)
    {
        try
        {
            return Float.parseFloat(object.toString());
        }
        catch(Exception e)
        {
            return 0.0F;
        }
    }

    public static BigDecimal parseDecimal(Object object)
    {
        try
        {
            return new BigDecimal(object.toString());
        }
        catch(Exception e)
        {
            return new BigDecimal(0.0D);
        }
    }

    public static String NulltoSpace(String src)
    {
        if(src == null)
            return "";
        else
            return src;
    }

    public static String NulltoSpace(Object src)
    {
        if(src == null || src.equals("null"))
            return "";
        else
            return src.toString();
    }

    public static String formatDate(Date date)
    {
    	if((date == null) || (date.equals("null")))
            return "";
        else
            return dateFormat.format(date);
    }

    public static String formatTime(Date date)
    {
    	if((date == null) || (date.equals("null")))
            return "";
        else
            return timeFormat.format(date);
    }

    public static String dateToNull(Object obj)
    {
    	if((obj == null) || (obj.equals("null")))
            return "";
        if(obj.toString().substring(0, 10).equals("1900-01-01"))
            return "";
        else
            return obj.toString();
    }

    public static String getFormattedDate(Object date)
    {
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    	String result = "";
        if(date != null){
        	String date_ = date.toString();
        	if(!date_.contains("00:00:00.0")&&date_.contains(":")){
        		formatter  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	}
        	 result = formatter.format(date);
        }
        if(result.equals("1900-01-01"))
            return "";
        else
            return result;
    }

    public static String getStr(String s)
    {
    	if((s == null) || (s.equals("null")))
            s = "";
        String s1 = "";
        try
        {
            String s2 = s;
            byte abyte0[] = s2.getBytes("ISO8859-1");
            s1 = new String(abyte0);
            if(s1.indexOf("?") > -1)
                s1 = s;
        }
        catch(Exception exception) { }
        return s1;
    }

    public static String NulltoNullString(Object obj)
    {
    	if((obj == null) || (obj.equals("null")))
            return "null";
        else
            return obj.toString();
    }

    public static String NulltoString(String s)
    {
    	if((s == null) || (s.equals("null")))
            return "详 细";
        else
            return s;
    }

    public static String NulltoNullString(String s)
    {
    	if((s == null) || (s.equals("null")))
            return "null";
        else
            return s.toString();
    }

    public static String NulltoString(Object obj)
    {
    	if((obj == null) || (obj.equals("null")))
            return "详 细";
        else
            return obj.toString();
    }

    public StringFormatter()
    {
    }

    public static Date parseDateString(String dateString)
        throws ParseException
    {
        return dateFormat4String.parse(dateString);
    }
    public static Date parseDateStringTime(String dateString)
            throws ParseException
        {
            return timeFormat.parse(dateString);
        }
    public static String StringarrToString(String []stringArr)
    {
    	String result="";
    	
    	if(stringArr.equals(null))
    	{
    		return "";
    	}
    	int arrLen = stringArr.length;
    	result = stringArr[0];
    	if(arrLen>1)
    	{
    		for(int i=1;i<arrLen;i++)
    		{
    			result = result + "," + stringArr[i];
    		}
    	}
    	return result;
    	
    }
    /**
	 * 
	 * @param rStr 源字符串
	 * @param rFix 要查找替换的字符串
	 * @param rRep 替换成的字符串
	 * @return d
	 */
	public static String strReplace(String rStr, String rFix, String rRep) {
		int l = 0;
		String gRtnStr = rStr;
		do {
			l = rStr.indexOf(rFix, l);
			if (l == -1)
				break;
			gRtnStr = rStr.substring(0, l) + rRep + rStr.substring(l + rFix.length());
			l += rRep.length();
			rStr = gRtnStr;
		} while (true);
		return gRtnStr.substring(0, gRtnStr.length());
	}
	/**
	 * 
	 * @param str 判断的字符串
	 * @return
	 * @description: 
	 * @date: 2013-5-12 上午11:12:23
	 * @author： Administrator
	 */
	public static boolean strIsNotNull(String str){
		if(str!=null && (!str.equals(""))&&(!str.equals("null"))){
			return true;
		}else{
			return false;
		}
	}
	
	public static String getCalendarDate(Calendar cal) {
        String v_strDate = "";
        int v_intYear = cal.get(Calendar.YEAR);
        int v_intMonth = cal.get(Calendar.MONTH) + 1;
        int v_intDAY = cal.get(Calendar.DAY_OF_MONTH);
        int v_intHOUR = cal.get(Calendar.HOUR_OF_DAY);
        int v_intMINUTE = cal.get(Calendar.MINUTE);
        int v_intSECOND = cal.get(Calendar.SECOND);	        
        if (v_intMonth < 10) {
            v_strDate =v_intYear+ "-"+  "0" +v_intMonth + "-";
        }else {
            v_strDate = v_intYear+ "-"+ v_intMonth + "-";
        }
      
        if (v_intDAY < 10) {
            v_strDate =v_strDate + "0" + v_intDAY + " ";
        }else {
            v_strDate = v_strDate + v_intDAY+" ";
        }
        
        if (v_intHOUR < 10) {
            v_strDate = v_strDate + "0" + v_intHOUR + ":";
        }else {
            v_strDate = v_strDate + v_intHOUR + ":";
        }
        if (v_intMINUTE < 10) {
            v_strDate = v_strDate + "0" + v_intMINUTE + ":";
        } else {
            v_strDate = v_strDate + v_intMINUTE + ":";
        }
        if (v_intSECOND < 10) {
            v_strDate = v_strDate + "0" + v_intSECOND;
        } else {
            v_strDate = v_strDate + v_intSECOND;
        }
        cal = null;
        return v_strDate;
    }
	
	
	/**
	* 把Blob类型转换为byte数组类型
	* @param blob
	* @return
	*/
	public static byte[] blobToBytes(Blob blob) {
	
		BufferedInputStream is = null;
	
		try {
			is = new BufferedInputStream(blob.getBinaryStream());
			byte[] bytes = new byte[(int) blob.length()];
			int len = bytes.length;
			int offset = 0;
			int read = 0;
			while (offset < len && (read = is.read(bytes, offset, len - offset)) >= 0) {
				offset += read;
			}
			return bytes;
		} catch (Exception e) {
			return null;
		} finally {
			try {
				is.close();
				is = null;
			} catch (IOException e) {
				return null;
			}
		}
	}
	
	
}
