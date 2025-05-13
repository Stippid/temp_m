<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

 <meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon" href="login_file/dgis-logo_favicon.jpg" >
<title>MISO</title>
<script src="login_file/jquery-3.4.1.min.js"></script>
<link rel="stylesheet" href="admin/js/miso/assets/css/bootstrap.min.css">
<link rel="stylesheet" href="admin/js/miso/assets/css/font-awesome.min.css">
<link rel="stylesheet" href="login_file/style.css">
<script src="login_file/pkiValidation/pkiValidation.js"></script>
<script>
	var key = "${_csrf.parameterName}";
	var value = "${_csrf.token}";
	/* jQuery(document).ready(function() {
		
	}); */
</script>
<div class="wrapper">
<header id="header" class="header">
			<div class="header-menu">
	    		<div class="row">
<!-- 			      	<div class="col-2 col-sm-2"><div class="header-left"><img src="login_file/indian_Army.jpg" class="img-fluid" style="height:63px;"></div></div> -->
<!-- 					<div class="col-8 col-sm-8"><div class="heading_content"><h1>MANAGEMENT INFORMATION SYSTEM ORGANISATION</h1><b style="display:none;" id="div_timeout">60</b></div></div> -->
<!-- 			      	<div class="col-2 col-sm-2"><div id="language-select"> <img src="login_file/dgis-logo.jpg" class="img-fluid" style="height:63px;"></div></div> -->
			      	<div class="head_left"><div class="header-logo-left"><img src="login_file/indian_army.png" class="img-fluid"></div></div>
					<div class="head_center"><div class="header-title-main"><h1>MANAGEMENT INFORMATION SYSTEM ORGANISATION</h1><b style="display:none;" id="div_timeout">60</b></div></div>
			      	<div class="head_right"><div class="header-logo-right"><img src="login_file/dgis-logo.png" class="img-fluid"></div></div>
				</div>
			</div>
	  	</header>
<div class="navbar-top-main">
							<nav class="navbar navbar-expand-lg navbar-light header_nav">
								<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
									<span class=""><i class="fa fa-bars"></i></span>
								</button>
								<div class="collapse navbar-collapse navbar-center" id="navbarSupportedContent">
									<ul class="navbar-nav header_list">
										<li class="nav-item"><a class="nav-link header_home active" href="login">Home</a></li>
										<!-- <li class="nav-item dropdown">
											<a class="nav-link dropdown-toggle" href="" id="navbarDropdown1" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">About Us</a>
											<div class="dropdown-menu" aria-labelledby="navbarDropdown1">
												<a class="dropdown-item" onclick="aboutus();">About MISO</a>
												<a class="dropdown-item" onclick="teammiso();">Team MISO</a>
											</div>
										</li>
										<li class="nav-item dropdown">
											<a class="nav-link dropdown-toggle" href="" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Documents</a>
											<div class="dropdown-menu" aria-labelledby="navbarDropdown">
												<a class="dropdown-item" target="_blanck" href="doc/modification_form.pdf">Modification Form</a>
											  	<a class="dropdown-item" target="_blanck" href="doc/New_User_Registration.pdf">New User Registration</a>
											  	<a class="dropdown-item" target="_blanck" href="doc/Request_for_New_Password.pdf">Request for New Password</a>
											  	<a class="dropdown-item" target="_blanck" href="/doc/Minimum_Desktop_Requirement.pdf">System Requirements</a>
											  	<a class="dropdown-item" target="_blanck" href="/doc/UserGuide3.0.pdf">Installation Guide</a>
											</div>
										</li>
										<li class="nav-item"><a href="#" class="nav-link" onclick="contactus();">Contact us</a></li>
										<li class="nav-item"><a href="https://portal.army.mil" class="nav-link">Army Web</a></li>
										<li class="nav-item dropdown">
											<a class="nav-link dropdown-toggle" href="" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Tutorials</a>
											<div class="dropdown-menu" aria-labelledby="navbarDropdown">
												<a class="dropdown-item" target="_blanck" href="/doc/2FA_Login.mp4">How to login</a>
												<a class="dropdown-item" target="_blanck" href="/doc/mvcr.mp4">MVCR</a>
											  	<a class="dropdown-item" target="_blanck" href="/doc/MMS_UNIT_MCR.mp4">MCR</a>
											</div>
										</li>
										<li class="nav-item dropdown">
											<a class="nav-link dropdown-toggle" href="" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">System Prerequisites</a>
											<div class="dropdown-menu" aria-labelledby="navbarDropdown">
												<a class="dropdown-item" target="_blanck" href="/doc/jre-8u261x64.exe">Java</a>
											  	<a class="dropdown-item" target="_blanck" href="/doc/Google Chrome 75.rar">Web Browser</a>
												<a class="dropdown-item" target="_blanck" href="/doc/ePassTokenSoftware.rar">Token SW</a>
												<a class="dropdown-item" target="_blanck" href="/doc/Cert.rar">Certificates</a>
											</div>
										</li>
										<li class="nav-item"><a class="nav-link" target="_blanck" href="/doc/FAQs.pdf">FAQs</a></li> -->
										
										<li class="nav-item"><a class="nav-link"  href="user_registrationUrl">User Registration</a></li>
									</ul>
								</div>
							</nav>
		</div> 
<form:form id="user_regitrastion">
	<div class="container" align="center" id="proposaldetails_div">
		<div class="card">
			<div class="card-header">
				<h5>USER REGISTRATION</h5>
			</div>
			<div class="card-body card-block ">
				<div class="col-md-12">
					<div class="col-md-3"></div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-6">
								<label for="text-input">User Name <strong style="color: red;">*</strong> </label>
							</div>
							<div class="col-12 col-md-6">
								<input type="text" id="user_name" name="user_name" maxlength="100" class="form-control" autocomplete="off">
							</div>
						</div>
					</div>
					<div class="col-md-3"></div>
				</div>
				<div class="col-md-12">
					<div class="col-md-3"></div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-6">
								<label for="text-input">Unit Name<strong style="color: red;">*</strong> </label>
							</div>
							<div class="col-12 col-md-6">
								<input type="text" id="unit_name" name="unit_name" maxlength="100" class="form-control" autocomplete="off">
							</div>
						</div>
					</div>
					<div class="col-md-3"></div>
				</div>
				<div class="col-md-12">
					<div class="col-md-3"></div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-6">
								<label for="text-input">SUS No<strong style="color: red;">*</strong></label>
							</div>
							<div class="col-12 col-md-6">
								<input type="text" id="sus_no" name="sus_no" maxlength="8" class="form-control" autocomplete="off">
							</div>
						</div>
					</div>
					<div class="col-md-3"></div>
				</div>
				<div class="col-md-12">
					<div class="col-md-3"></div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-6">
								<label for="text-input">Army No<strong style="color: red;">*</strong></label>
							</div>
							<div class="col-12 col-md-6">
								<input type="text" id="army_no" name="army_no" maxlength="10" class="form-control" autocomplete="off">
							</div>
						</div>
					</div>
					<div class="col-md-3"></div>
				</div>
				<div class="col-md-12">
					<div class="col-md-3"></div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-6">
								<label for="text-input">Rank<strong style="color: red;">*</strong></label>
							</div>
							<div class="col-12 col-md-6">
								<input type="text" id="rank" name="rank" maxlength="100" class="form-control" autocomplete="off">
							</div>
						</div>
					</div>
					<div class="col-md-3"></div>
				</div>
				<div class="col-md-12">
					<div class="col-md-3"></div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-6">
								<label for="text-input">Mobile No <strong style="color: red;">*</strong></label>
							</div>
							<div class="col-12 col-md-6">
								<input type="text" id="mobile_no" name="mobile_no" maxlength="10" class="form-control" autocomplete="off" onkeypress="return isNumber0_9Only(event)">
							</div>
						</div>
					</div>
					<div class="col-md-3"></div>
				</div>
				<div class="col-md-12">
					<div class="col-md-3"></div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-6">
								<label for="text-input">Upload User Regn Form<strong style="color: red;">*</strong> <br><span style="font-size: 10px;color: red;">(Counter sign by CO)</span></label>
							</div>
							<div class="col-12 col-md-6">
								<input type="file" id="doc" name="doc" autocomplete="off" />
							</div>
						</div>
					</div>
					<div class="col-md-3"></div>
				</div>
				<div class="col-md-12">
					<div class="col-md-3"></div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-6">
								<label for="text-input">Appointment<strong style="color: red;">*</strong></label>
							</div>
							<div class="col-12 col-md-6">
								<input type="text" id="appointment" name="appointment" maxlength="100" class="form-control" autocomplete="off">
							</div>
						</div>
					</div>
					<div class="col-md-3"></div>
				</div>
			</div>
			<div class="card-footer" align="center">
				<span style="display: none;"><button id='b1' onclick='runForRegistration();' class='btn btn-danger btn-sm'></button></span>
				<input type="reset" class="btn btn-success btn-sm" value="Clear"> 
				<input type="button" class="btn btn-primary btn-sm" value="Save" onclick="saveUserRegistraion();">
			</div>
		</div>
	</div>
</form:form>
<!-- <div class="digital_india" style="position:fixed;bottom:20px;"><img src="login_file/digitalindia.png" class="img-fluid"></div> -->
</div>
<!-- <footer> -->
<!-- 	<span class="content1 left">Collab: BISAG-N, MeitY, GoI | All rights reserved - MISO. App vetted by Army Cyber Gp on 25 Aug 2020 & Content Vetted by MI-11 on 01 Sep 2020.</span> -->
<%-- 	<span class="content2 right">${server} Last Updated 24-07-2021 Visitors ${visiter_count}</span> --%>
<!-- </footer> -->

<footer>

	<div class="align-items-center p-5px">
				<div class="col-md-2 col-sm-3 col-6"><p class="logo-footer">
			    	<a href="https://www.digitalindia.gov.in/" title="Go to https://www.digitalindia.gov.in/" target="_new"><img src="login_file/di_logo.png" class="flimg"></a>
			    </p></div>
			   <div class="col-md-7 col-sm-6 col-6">
			    <p class="p3" align="center">
			    	<b class="terms-data">
			    	<a class="cursor-pointer" onclick="terms();" title="Terms &amp; Conditions">Terms &amp; Conditions</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
			    	<a class="cursor-pointer" onclick="disclaimer();" title="Disclaimer">Disclaimer</a></b>
			    	<b class="text-center color-white">All rights reserved - MISO. App vetted by Army Cyber Gp on 25 Aug 2020 & Content Vetted by MI-11 on 01 Sep 2020.</b></p>
<!-- 			    <p>This site is developed and maintained by <u class="bisag_color"> BISAG-N </u>, MeitY, GOI.</p> -->
				</div>
				 <div class="col-md-3 col-sm-3 col-6 vertical-middle">
				 	<p class="p3" align="center">
			    		<b><span class="f-right color-white">${server} Last Updated 03-05-2024 Version 2 Visitors ${visiter_count}</span></b>
			    	</p> 
			    </div>
	</div>
	
</footer>

<c:url value="getDownloadJAR" var="jarDownloadUrl" />
<form:form action="${jarDownloadUrl}" method="post" id="getDownloadJarForm" name="getDownloadJarForm" modelAttribute="id"></form:form>
<script>
	function saveUserRegistraion() {
		if ($("input#user_name").val().trim() == "") {
			alert("Please Enter User Name");
			$("input#user_name").focus();
			return false;
		}else if($("input#unit_name").val().trim() == "") {
			alert("Please Enter Unit Name");
			$("input#unit_name").focus();
			return false;
		}else if($("input#sus_no").val().trim() == "") {
			alert("Please Enter SUS No");
			$("input#sus_no").focus();
			return false;
		}else if($("input#army_no").val().trim() == "") {
			alert("Please Enter Army No");
			$("input#army_no").focus();
			return false;
		}else if($("input#rank").val().trim() == "") {
			alert("Please Enter Rank");
			$("input#rank").focus();
			return false;
		}else if($("input#mobile_no").val().trim() == "") {
			alert("Please Enter Mobile No");
			$("input#mobile_no").focus();
			return false;
		}else if($("#doc").get(0).files.length == 0) {
			alert("Please Upload Document");
			$("#doc").focus();
			return false
		}else if ($("input#appointment").val().trim() == "") {
			alert("Please Enter Appointment");
			$("input#appointment").focus();
			return false;
		}else{
			getCheckPIKValidationForRegistration();
		}
	}
	function isNumber0_9Only(evt) {
		var charCode = (evt.which) ? evt.which : evt.keyCode;
		if (charCode != 46 && charCode > 31 && (charCode<48 || charCode>57)) {
			return false;
		} else {
			if (charCode == 46) {
				return false;
			}
		}
		return true;
	}
</script>