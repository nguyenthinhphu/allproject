package net.luvina.insurance.utility;

import java.text.Normalizer;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.WordUtils;

import net.luvina.insurance.beanentity.TblInsurance;
import net.luvina.insurance.beanentity.TblUser;
/**
 * Validate
 * @author nguyenthinhphu
 * Xu ly validate du lieu MH
 */
public class Validate {
	private static char[] SOURCE_CHARACTERS = { 'À', 'Á', 'Â', 'Ã', 'È', 'É', 'Ê', 'Ì', 'Í', 'Ò', 'Ó', 'Ô', 'Õ', 'Ù',
			'Ú', 'Ý', 'à', 'á', 'â', 'ã', 'è', 'é', 'ê', 'ì', 'í', 'ò', 'ó', 'ô', 'õ', 'ù', 'ú', 'ý', 'Ă', 'ă', 'Đ',
			'đ', 'Ĩ', 'ĩ', 'Ũ', 'ũ', 'Ơ', 'ơ', 'Ư', 'ư', 'Ạ', 'ạ', 'Ả', 'ả', 'Ấ', 'ấ', 'Ầ', 'ầ', 'Ẩ', 'ẩ', 'Ẫ', 'ẫ',
			'Ậ', 'ậ', 'Ắ', 'ắ', 'Ằ', 'ằ', 'Ẳ', 'ẳ', 'Ẵ', 'ẵ', 'Ặ', 'ặ', 'Ẹ', 'ẹ', 'Ẻ', 'ẻ', 'Ẽ', 'ẽ', 'Ế', 'ế', 'Ề',
			'ề', 'Ể', 'ể', 'Ễ', 'ễ', 'Ệ', 'ệ', 'Ỉ', 'ỉ', 'Ị', 'ị', 'Ọ', 'ọ', 'Ỏ', 'ỏ', 'Ố', 'ố', 'Ồ', 'ồ', 'Ổ', 'ổ',
			'Ỗ', 'ỗ', 'Ộ', 'ộ', 'Ớ', 'ớ', 'Ờ', 'ờ', 'Ở', 'ở', 'Ỡ', 'ỡ', 'Ợ', 'ợ', 'Ụ', 'ụ', 'Ủ', 'ủ', 'Ứ', 'ứ', 'Ừ',
			'ừ', 'Ử', 'ử', 'Ữ', 'ữ', 'Ự', 'ự', };

	// Mang cac ky tu thay the khong dau
	private static char[] DESTINATION_CHARACTERS = { 'A', 'A', 'A', 'A', 'E', 'E', 'E', 'I', 'I', 'O', 'O', 'O', 'O',
			'U', 'U', 'Y', 'a', 'a', 'a', 'a', 'e', 'e', 'e', 'i', 'i', 'o', 'o', 'o', 'o', 'u', 'u', 'y', 'A', 'a',
			'D', 'd', 'I', 'i', 'U', 'u', 'O', 'o', 'U', 'u', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A',
			'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e',
			'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'I', 'i', 'I', 'i', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O',
			'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'U', 'u', 'U', 'u', 'U', 'u',
			'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u', };

	/**
	 * Check Login
	 * @param session
	 * @return String MH Login
	 */
	public static String checkLogin(HttpSession session){
		String result = "";
		if(session.getAttribute("username") == null){ // kiem tra session
			result = "redirect:/login";
		}
		return result;
	}
	
	/**
	 * Check Username va password
	 * @param username
	 * @param password
	 * @param listUser
	 * @return String message
	 */
	public static String checkUserNameAndPassword(String username, String password, List<TblUser> listUser) {
		String errorMessage = "";

		if (username.trim().equals("") && password.equals("")) {
			errorMessage = "Hãy nhập tên đăng nhập và mật khẩu";
		} else if (username.trim().equals("")) {
			errorMessage = "Hãy nhập tên đăng nhập";
		} else if (password.trim().equals("")) {
			errorMessage = "Hãy nhập mật khẩu";
		} else if (listUser.size() > 0) {
			errorMessage = "";
		} else {
			errorMessage = "Tên đăng nhập hoặc mật khẩu không đúng";
		}

		return errorMessage;
	}

	/**
	 * check Add New
	 * @param companyName
	 * @param address
	 * @return String message
	 */
	public static String checkAddNew(String companyName, String address) {
		String message = "";
		if (companyName.trim().equals("")) {
			message = "Hãy nhập thông tin tên công ty";
		} else if (address.trim().equals("")) {
			message = "Hãy nhập địa chỉ công ty";
		}
		return message;
	}

	/**
	 * check thong tin khi Add
	 * @param insuranceNumber
	 * @param listInsuranceNumber
	 * @param userFullname
	 * @param placeOfRegister
	 * @return
	 */
	public static String checkInformartionAdd(String insuranceNumber, List<TblInsurance> listInsuranceNumber,
			String userFullname, String placeOfRegister) {
		String errorMessage = "";
		char[] result = insuranceNumber.toCharArray();
		if (insuranceNumber.trim().equals("")) {
			errorMessage = "Hãy nhập mã thông tin thẻ bảo hiểm";
		}
		else if (result.length > 10) {
			errorMessage = "Mã thẻ không được lớn hơn 10!";
		}else if (listInsuranceNumber.size() > 0) {
			errorMessage = "Mã số thẻ bảo hiểm đã tồn tại";
		} else if (userFullname.equals("")) {
			errorMessage = "Hãy nhập họ và tên";
		} else if (placeOfRegister.trim().equals("")) {
			errorMessage = "Hãy nhập nơi đăng ký KCB";
		}
		return errorMessage;
	}

	/**
	 * Check Length, check do dai
	 * @param insuranceNumber
	 * @return
	 */
	public static String checkLength(String insuranceNumber) {
		String message = "";
		char[] result = insuranceNumber.toCharArray();
		if (result.length > 10) {
			message = "Mã thẻ không được lớn hơn 10!";
		}
		return message;
	}

	// sua 1 ki tu
	public static char removeAccent(char ch) {
		int index = Arrays.binarySearch(SOURCE_CHARACTERS, ch);
		if (index >= 0) {
			ch = DESTINATION_CHARACTERS[index];
		}
		return ch;
	}

	// sua ten
	public static String formatName(String name) {
		// loai bo dấu tiếng việt và kí tự đặc biệt
		  String convertedString = Normalizer .normalize(name, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").trim();
		  //loại bỏ số, dấu cách, Viết hoa chữ cái đầu mỗi word
		  return WordUtils.capitalizeFully(convertedString.replaceAll("[^A-Z a-z]", "").replaceAll("\\s+", " ")).replace("Đ", "D").replace("đ", "d");

	}

	/**
	 * Check Latin
	 * @param str
	 * @return
	 */
	public static boolean checkLatin(char str) {
		int count = (String.valueOf(str)).getBytes().length;
		if (count >= 2) {
			return false;
		}
		return true;
	}
	
	/**
	 * Escape HTML
	 * @param content
	 * @return
	 */
	public static String escapeHTML(String content) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < content.length(); i++) {
			char c = content.charAt(i);

			switch (c) {
			case '<':
				sb.append("&lt;");
				break;
			case '>':
				sb.append("&gt;");
				break;

			case '&':
				sb.append("&amp;");
				break;
			case '"':
				sb.append("&quot;");
				break;
			/*
			 * case '\'': sb.append("&apos;"); break;
			 */
			default:
				sb.append(c);
			}
		}
		content = sb.toString();
		return content;
	}

	public static String escapeInjection(String str) {
		String tem = str.replace("'", "''");
		tem = tem.replace("\\", "\\\\");
		return tem;
	}

}