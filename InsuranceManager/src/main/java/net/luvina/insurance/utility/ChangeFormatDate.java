package net.luvina.insurance.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Change Format Date
 * @author nguyenthinhphu
 * Xu ly chuyen doi date
 */
public class ChangeFormatDate {

	/**
	 * change Date
	 * @param date
	 * @return Str Date
	 */
	public static String changeDate(Date date) {
		String strDate = "";
		String year = "";
		String month = "";
		String day = "";

		try {
			year = date.toString().substring(0, 4);
			month = date.toString().substring(5, 7);
			day = date.toString().substring(8, 10);
			strDate = day + "/" + month + "/" + year;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return strDate;
	}
	
	public static String changeDate(String date) {
		String strDate = "";
		String year = "";
		String month = "";
		String day = "";

		try {
			day = date.substring(0, 2);
			month = date.substring(3, 5);
			year = date.substring(6, 10);
			strDate = year + "/" + month + "/" + day;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return strDate;
	}
	/**
	 * change Format Date
	 * @param str
	 * @return
	 */
	public static Date changeFormatDate(String str){
		Date d = new Date(str);
		SimpleDateFormat fm = new SimpleDateFormat("dd/MM/yyyy");
		try {
			d = fm.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return d;
		
	}
}
