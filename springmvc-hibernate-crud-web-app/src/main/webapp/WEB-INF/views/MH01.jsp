<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- Thymeleaf Namespace -->

<html xmlns:th="http://www.thymeleaf.org">
<head>
<style>
.error {
	color: red
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="/resources/css/mystyle.css" rel="stylesheet" type="text/css" />
<title>ユーザ管理</title>
</head>
<body align="center">
	<form:form action="" method="post" modelAttribute="user">
		<center>
			<table class="tbl_input" cellpadding="4" cellspacing="0"
				width="400px">
				<tr>
					<th width="120px">&nbsp;</th>
					<th></th>
				</tr>
				<tr>
					<th colspan="2" align="left">アカウント名およびパスワードを入力してください</th>
				</tr>

				<tr>
					<td class="errMsg" colspan="2">
					 ABCC
							<c:if test="${$errorMessage ne ''}">
														   
							    ${errorMessage}
							    </c:if></td>
				</tr>
				<tr align="left">
					<td class="lbl_left">アカウント名(*):</td>
					<td align="left"><form:input path="loginName" class="txBox"
							type="text" name="loginId" size="20"
							onfocus="this.style.borderColor='#0066ff';"
							onblur="this.style.borderColor='#aaaaaa';" /> <form:errors
							path="loginName" cssClass="error" /></td>
				</tr>
				<tr>
					<td class="lbl_left">パスワード(*):</td>
					<td align="left"><form:input path="password" class="txBox"
							type="password" name="password" size="22"
							onfocus="this.style.borderColor='#0066ff';"
							onblur="this.style.borderColor='#aaaaaa';" /> <form:errors
							path="password" cssClass="error" /></td>
				</tr>
				<tr>
					<td></td>
					<td align="left"><input class="btn btn_wider" type="submit"
						value="ログイン" /></td>
				</tr>
			</table>
		</center>
	</form:form>
</body>
</html>