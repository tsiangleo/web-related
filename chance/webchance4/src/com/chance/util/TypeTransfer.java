package com.chance.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TypeTransfer {
	
	public static long String2Long(String id){
		return Long.parseLong(id);
	
	}
	
	public static String Int2String(int id){
		return String.format("%1$08x",id);
	}

	public static int String2Int(String id){
		return (int)Long.parseLong(id, 16);
	}
	
    private static final String LOG_TAG        = "HandleMsgStringTools==>";
    public static final int     MAX_HEX_LENGTH = 4;

    public static String        hexArray[]     = {
            "", "0", "00", "000",
                                               };

    public static String intToHex(int intData)
    {
        String result = Integer.toHexString(intData);
        result = hexArray[MAX_HEX_LENGTH - result.length()] + result;

        return result;
    }

    public static int hexToInt(String hexData)
    {
        return Integer.valueOf(hexData, 16);//将字符串hexData转换成16进制的数
    }
	
    public static ArrayList<String> stringConvertToArrayList(String otherString)
    {	
        ArrayList<String> result = new ArrayList<String>();
        if (otherString == null || otherString.length() == 0)
        {
            return result;
        }

        int beginIndex = 0, endIndex = TypeTransfer.MAX_HEX_LENGTH, otherStringLen;
        if (endIndex < otherString.length())
        {
            otherStringLen = TypeTransfer.hexToInt(otherString.substring(beginIndex,endIndex));      
            beginIndex = endIndex;
            endIndex = beginIndex + otherStringLen;  
            result.add(otherString.substring(beginIndex, endIndex));
        }
        while (endIndex < otherString.length())
        {
            beginIndex = endIndex;
            endIndex = beginIndex + TypeTransfer.MAX_HEX_LENGTH;
            otherStringLen = TypeTransfer.hexToInt(otherString.substring(beginIndex,endIndex));
            beginIndex = endIndex;
            endIndex = beginIndex + otherStringLen;
            result.add(otherString.substring(beginIndex, endIndex));
        }

        return result;
    }
    
  
}
