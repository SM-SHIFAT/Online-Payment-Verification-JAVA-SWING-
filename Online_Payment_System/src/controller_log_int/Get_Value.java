package controller_log_int;

public class Get_Value {
	
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    // only got here if we didn't return false
	    return true;
	}
	
	public static String value;
	
	public static String Value () {
		return value;
	}
	public static void Value (String val) {
		value = val;

	}
}
