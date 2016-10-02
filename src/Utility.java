/***
 * This class provide some basic functions, like validation
 * 
 * @author zjw
 * 
 */
public class Utility {
	/***
	 * Validate if the string is null or ""
	 * 
	 * @param str
	 * @return true if the string is empty
	 */
	public static boolean isEmpty(String str) {
		if (str == null) {
			return true;
		}
		if (str.trim().equals("")) {
			return true;
		}
		return false;
	}

	/***
	 * Validate the object is null
	 * 
	 * @param obj
	 * @return true if the object is null
	 */
	public static boolean isEmpty(Object obj) {
		if (obj == null) {
			return true;
		}
		return false;
	}

	/***
	 * check if the string is positive integer form
	 * 
	 * @param str
	 * @return true if the string is integer form
	 */
	public static boolean isInteger(String str) {
		return str.matches("\\d+");
	}

	/***
	 * check if the string is digit form
	 * 
	 * @param str
	 * @return true if the string is digit form
	 */
	public static boolean isDigit(String str) {
		return str.matches("\\[0-9]+.?[0-9]+");
	}

}
