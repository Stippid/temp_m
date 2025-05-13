<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="js/layout/css/style_db.css" rel="stylesheet">
<script src="js/assets/js/jquery-2.1.4.min.js"></script>
<div class="tunnel_design">
	<div class="square tunnel_active dropdown" style = "width: 120px">
		<h5 class="tunnel_text dropbtn">IT Asset</h5>
		<div class="dropdown-content">
			<a href="onHoldingDashboard">Holding/Servicable-Un-Servicable</a>  
			<a href="WarrentyDashboard">Warranty </a> 
			<a href="AntivirusDashboard">Antivirus</a> 
			<a href="ADNDashboard">ADN Functionality</a> 
		</div>
	</div>
	<div class="square tunnel_active dropdown" style = "width: 120px">
		<h5 class="tunnel_text dropbtn">A Vehicle</h5>
		<div class="dropdown-content">
			<a href="aVehicleDashboard">Holding/Servicable-Un-Servicable</a> 
			<!-- <a href="WarrentyDashboard">MMS</a>  -->
<!-- 			<a href="WarrentyDashboard"></a>  -->
			
		</div>
	</div>
	<div class="square tunnel_active dropdown" style = "width: 120px">
		<h5 class="tunnel_text dropbtn">B Vehicle</h5>
		<div class="dropdown-content">
			<a href="bVehicleDashboard">Holding/Servicable-Un-Servicable</a> 
			
		</div>
	</div>
	<div class="square tunnel_active dropdown"  style = "width: 120px">
		<h5 class="tunnel_text dropbtn">C Vehicle</h5>
		<div class="dropdown-content">
			<a href="cVehicleDashboard">Holding/Servicable-Un-Servicable</a> 
			
		</div>
	</div>
</div>