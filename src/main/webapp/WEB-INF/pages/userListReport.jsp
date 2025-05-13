<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
 "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Report</title>
</head>
<body>
	<div>IP :- ${IP}</div>
	<spring:url value="/report/?type=xls" var="xlsURL" />
	<spring:url value="/report/?type=pdfL" var="pdfURLL" />
	<spring:url value="/report/?type=pdfP" var="pdfURLP" />
	<a href="${xlsURL}">Download Excel</a>
	<a href="${pdfURLL}">Download PDF LANDSCAPE</a>
	<a href="${pdfURLP}">Download PDF PORTRAIT</a>
	<table border="1">
		<thead>
			<tr>
				<td>ID</td>
				<td>USERNAME</td>
				<td>FIRST NAME</td>
				<td>LAST NAME</td>
				<td>ADDRESS</td>
				<td>PIN</td>
				<td>PHONE</td>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="item" items="${userList}" varStatus="num" >
									<tr>
										<td>${item[0]}</td>
										<td>${item[1]}</td>
										<td>${item[2]}</td>
										<td>${item[3]}</td>
										<td>${item[4]}</td>
										<td>${item[5]}</td>
										<td>${item[6]}</td>
										
									</tr>
								</c:forEach>
			 <c:forEach items="${userList}" var="user">
				<tr>
					<td>${user[0]} </td>
					<td>${user[1]}</td>
					<td>${user[2]}</td>
					<td>${user[3]}</td>
					<td>${user[4]}</td>
					<td>${user[5]}</td>
					<td>${user[6]}</td>
				</tr>
			</c:forEach> 
		</tbody>
	</table>
	
</body>
</html>