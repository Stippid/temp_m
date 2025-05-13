<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!doctype html>
<html>
<head>
	<link rel="shortcut icon" href="login_file/dgis-logo_favicon.jpg" >
	<title>MISO 3.1</title>
	<link rel="stylesheet" href="login_file/style.css">
	<script src="login_file/jquery-3.4.1.min.js"></script>
 	<link rel="stylesheet" href="admin/js/common/nrappcss.css">
    <link rel="stylesheet" href="admin/js/common/nrcss.css">
	<script type="text/javascript">
	  	var csrfparname ="${_csrf.parameterName}";
	  	var csrfvalue="${_csrf.token}";
	  	var yuji = "<c:url value='/auth/login_check?targetUrl=${targetUrl}' />";
		var numb = [1,2,3,4,5];
		jQuery(document).ready(function(){
			
		/* 	var Navigation = document.getElementById("navbarSupportedContent");
			var navs = Navigation.getElementsByClassName("nav-link");
			for (var i = 0; i < navs.length; i++) {
				navs[i].addEventListener("mouseenter", function() {
			    	var current = document.getElementsByClassName("active");
			    	if (current.length > 0) {current[0].className = current[0].className.replace(" active", "");}
					this.className += " active";
				});
			} */
			
			var Navigation = document.getElementById("navbarSupportedContent");
			var navs = Navigation.getElementsByClassName("nav-link");
			
			for (var i = 0; i < navs.length; i++) {
					navs[i].addEventListener("click", function() {
				    	var current = document.getElementsByClassName("active");
				    	if (current.length > 0) {
				    		current[0].className = current[0].className.replace(" active", "");
						}
						this.className += " active";
					});
				}
			
			$(".navbar-toggler").click(function(){
	        	$("#navbarSupportedContent").toggle();
			});
			$(".nav-item").click(function(){
	        	$("#navbarSupportedContent").hide();
			});
			
			$(".dropdown").hover(function(){
				var dropdownMenu = $(this).children(".dropdown-menu");
				if(dropdownMenu.is(":visible")){dropdownMenu.parent().toggleClass("open");}
			});
			$(window).scroll(function() {
			    if ($(document).scrollTop() > 50) {$(".header_bottom").addClass("head_nav");} else {$(".header_bottom").removeClass("head_nav");}
			});
			var msg = "";
	   		msg = jQuery('label#msg').text();
	   		if('${error}' != ""){jQuery("div#errorDiv").show();}
			if('${msg}' != ""){
				window.alert = function(al, $){
				    return function(msg) {
				        al.call(window,msg);
				        $(window).trigger("okbuttonclicked");
				    };
				}(window.alert, window.jQuery);
				$(window).on("okbuttonclicked", function() {
				    window.location = window.location.href.split("?")[0];
				});
				alert('${msg}');
				jQuery("div#errorDiv").show();
			}
			// Start Canvas Capcha
			var c = document.getElementById("canvasCapcha");
			var ctx = c.getContext("2d");
			var sets = [0,17,34,51,68];
			var hei = [35,35,35,35,35];
			function captcha() {
				var key = "${_csrf.parameterName}";
		     	var value = "${_csrf.token}";
		     	jQuery.post("capchaCode?"+key+"="+value, function(j) {
   	         		for ( var i = 0; i < j.length; i++) {
   	         			numb[i] = j[i];
    				}	
   	         		ctx.font = " italic 32px Arial";
					ctx.fillStyle = "#696969";
					//$("#txtInput").val(numb[0]+numb[1]+numb[2]+numb[3]+numb[4]);
					ctx.fillText(numb[0],sets[0],hei[0]);
					ctx.fillText(numb[1],sets[1],hei[1]);
					ctx.fillText(numb[2],sets[2],hei[2]);
					ctx.fillText(numb[3],sets[3],hei[3]);
					ctx.fillText(numb[4],sets[4],hei[4]);
    			});
			};
			function clear() { ctx.clearRect(0, 0, canvasCapcha.width, canvasCapcha.height); $("#txtInput").val(""); };
			captcha();
			$("#btnrefresh").click(function() { clear(); captcha(); })
	   		// End Canvas Capcha
		});
		function validation() {
			var ck_username = /^[A-Za-z0-9_]{1,20}$/;
			var ck_password =  /^[A-Za-z0-9!@#$%^&*()_]{6,20}$/;
			var a = document.getElementById("username");
			if (a.value == "" ||a.value == "'" || a.value == null || a.value.toString().trim() == "" ||a.value == "'''" ) {
				alert("Enter username");
				a.focus();
				return false;
			}
			var b = document.getElementById("password");
			if (b.value == "" || b.value == "'"|| b.value == null || b.value.toString().trim() == "" ) {
				alert("Enter password"); b.focus(); return false;
			}	
			if(!ValidCaptcha())
			{
				alert("Captcha Validation failed!"); return false;
			}
		}
		function ValidCaptcha(){
			var str1 = (numb[0])+(numb[1])+(numb[2])+(numb[3])+(numb[4]);
		    var str2 = removeSpaces(document.getElementById('txtInput').value);
		   	if(str1 == "" || str2 == ""){ return false; }
			if (str1 == str2) {
				jQuery('#csrfIdSet').attr('name',csrfparname);
		    	jQuery('#csrfIdSet').attr('value',csrfvalue);
		    	jQuery('#myFormId').attr('action', yuji);
		    	return true;
		    }else{
		    	return false;
		    }
		}
		function removeSpaces(string) { return string.split(' ').join('');}
		function aboutus(){
			$("#fp_mis").hide();
			$("#login").hide();$("#contact_us").hide();$("#team_miso").hide();$("#about_us").show();
			$(".carousel-item img").css("height", "300px");
		}
		function teammiso(){
			$("#fp_mis").hide();
			$("#login").hide();	$("#contact_us").hide();$("#about_us").hide();$("#team_miso").show();
		}
		function contactus(){
			$("#fp_mis").hide();
			$("#login").hide();$("#about_us").hide();$("#team_miso").hide();$("#contact_us").show();
			$(".carousel-item img").css("height", "300px");
		}
		function fpmis(){
			$("#login").hide();
			$("#about_us").hide();
			$("#team_miso").hide();
			$("#contact_us").hide();
			$("#fp_mis").show();
			$(".carousel-item img").css("height", "300px");
		}
	</script>
	<script>
		document.onkeydown = function(e) {
			if(e.keyCode == 123){return false;}
			if(e.keyCode == 44){return false;}
			if(e.ctrlKey && e.keyCode == 'X'.charCodeAt(0)){return false;}
			if(e.ctrlKey && e.keyCode == 'C'.charCodeAt(0)){return false;}
			if(e.ctrlKey && e.keyCode == 'V'.charCodeAt(0)){return false;}
			if(e.ctrlKey && e.keyCode == 'E'.charCodeAt(0)){return false;}
			if(e.ctrlKey && e.shiftKey && e.keyCode == 'I'.charCodeAt(0)){return false;}
			if(e.ctrlKey && e.shiftKey && e.keyCode == 'J'.charCodeAt(0)){return false;}
			if(e.ctrlKey && e.shiftKey && e.keyCode == 'C'.charCodeAt(0)){return false;}
			if(e.ctrlKey && e.keyCode == 'U'.charCodeAt(0)){return false;}
			if(e.ctrlKey && e.keyCode == 'S'.charCodeAt(0)){return false;}
			if(e.ctrlKey && e.keyCode == 'H'.charCodeAt(0)){return false;}
			if(e.ctrlKey && e.keyCode == 'A'.charCodeAt(0)){return false;}
			if(e.ctrlKey && e.keyCode == 'E'.charCodeAt(0)){return false;}
		} 
		function downloadDoc(id) {
			$("#whats_new_id").val(id);
			document.getElementById("getDownloadWhatsNewForm").submit();
		}
	</script>
	<script type="text/javascript">
		window.history.forward();
		function noBack() {window.history.forward();}
	</script>
	<style type="text/css">
	.about_us_text {max-width:295px;position:absolute;text-align:center;background-color:rgba(255,255,255,0.8);background-color:#1fc8db;background-image:linear-gradient(141deg,#9fb8ad 0,#1fc8db 51%,#2cb5e8 75%);border:1px solid #ccc;border-radius:10px;top:15px;opacity:.9;}
	.team_miso h3 {color:#17a2b8;font-size:23px;text-decoration:underline;padding-top:25px;margin-bottom:25px;}
	.col-sm-3.info{font-family:system-ui;text-align:center;height:50px;padding:23px 00px;}
	.p1{color:#672a2a;!important;font-family:system-ui;}
   	/* .middle_content {height:auto;overflow-y:none;} */
 	@media screen and (max-width:1305px){.col-sm-3.info{padding:23px 40px;}}
	@media screen and (max-width:1200px){.col-sm-3.info{padding:23px 30px;}}
	@media screen and (max-width:1070px){.col-sm-3.info{padding:23px 20px;}}
	@media screen and (max-width:1000px){.col-sm-3.info{padding:23px 10px;}}
	
</style>
</head>
<body oncontextmenu="return false">
		<header id="header" class="header">
			<div class="header-menu nkAppBgPlate6">
	    		<div class="row ">
			      	<div class="col-2 col-sm-1 left"><div class="header-left"><img src="login_file/indian_Army.jpg" class="img-fluid" style="border-radius:50%;"></div></div>
					<div class="col-8 col-sm-10"><div class="heading_contenta nkloginheading" ><span>M</span>ANAGEMENT <span>I</span>NFORMATION <span>S</span>YSTEM <span>O</span>RGANISATION&nbsp;<span><sup style="font-size:1rem;">Ver 3.1</sup></span><b style="display:none;" id="div_timeout">60</b></div></div>
			      	<div class="col-2 col-sm-1 right"><div id="language-select"> <img src="login_file/dgislogo.png" class="img-fluid"></div></div>
				</div>
			</div>
	  	</header>
		<div class="header_bottom">
			<div class="header_navigation">
				<div class="container-fluid">
					<div class="row">
						<div class="col-12 col-sm-12 col-md-12 loging">
							<nav class="navbar navbar-expand-lg navbar-light header_nav">
								<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
									<img alt="toggle" src="login_file/toggle.png">
								</button>
								<div class="collapse navbar-collapse" id="navbarSupportedContent">
									<ul class="navbar-nav header_list">
										<li class="nav-item" style="margin-top: -3px;"><a class="nav-link header_home active" href="login"><img alt="HOME" src="login_file/WHITE Home_15X15.png"> </a></li>
										<li class="nav-item dropdown">
											<a class="nav-link dropdown-toggle" href="" id="navbarDropdown1" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">About Us</a>
											<div class="dropdown-menu" aria-labelledby="navbarDropdown1">
												<a class="dropdown-item" onclick="aboutus();">About MISO</a>
											  	<!-- <a class="dropdown-item" onclick="teammiso();">Team MISO</a> -->
											</div>
										</li>
										<li class="nav-item dropdown">
											<a class="nav-link dropdown-toggle" href="" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Documents</a>
											<div class="dropdown-menu" aria-labelledby="navbarDropdown">
												<a class="dropdown-item" target="_blanck" href="/doc/modification_form.pdf">Modification Form</a>
											  	<a class="dropdown-item" target="_blanck" href="/doc/New_User_Registration.pdf">New User Registration</a>
											  	<a class="dropdown-item" target="_blanck" href="/doc/Request_for_New_Password.pdf">Request for New Password</a>
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
										<li class="nav-item"><a class="nav-link" target="_blanck" href="/doc/FAQs.pdf">FAQs</a></li>
										<li class="nav-item"><a href="#" class="nav-link blink" onclick="fpmis();">MISO FPMIS</a></li>
										<!-- <li class="nav-item"><a class="nav-link"  href="user_registrationUrl">User Registration</a></li> -->
									</ul>
								</div>
							</nav>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="ticker dash-tic">
			<h3>${layout}</h3>
	 	</div>
	  	<div class="content banner">
			<div class="middle_content" id="login">
				<div class="row" style="margin: 0;">
				<div class="col-2" style="background-colora:cornsilk">
					<div class="col-12" style="margin:0px;height:90%;padding:0px;">
						<h3 class="marqueehead"><i class="fa fa-bell sake"></i> What's New</h3>
						<marquee behavior="scroll" direction="up" id="mymarquee" scroll-amount="3">
							<ul id="nNews" style="border:0px solid red;color:red;" onmouseover="document.getElementById('mymarquee').stop();" onmouseout="document.getElementById('mymarquee').start();">
								${whatsnew}
							</ul>
						</marquee>
					</div>
				</div>
				<div class="col-10" style="padding: 0;">
			<div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
				<div class="carousel-inner">
					<div class="carousel-item active">
						<img src="login_file/banner_design-min.jpg" class="img-fluid" alt="banner">
					</div>
				</div>
				<a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
					<span class="carousel-control-prev-icon" aria-hidden="true"></span>
					<span class="sr-only">Previous</span>
				</a>
				<a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
					<span class="carousel-control-next-icon" aria-hidden="true"></span>
					<span class="sr-only">Next</span>
				</a>
			</div> 
			<div class="digital_india" style="position:fixed;bottom:20px;"><img src="login_file/digitalindia.png" class="img-fluid"></div>
			</div>
			</div>
			<!-- <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
				<div class="carousel-inner">
					<div class="carousel-item active">
						<img src="login_file/banner_design-min.jpg" class="img-fluid" alt="banner">
					</div>
				</div>
				<a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
					<span class="carousel-control-prev-icon" aria-hidden="true"></span>
					<span class="sr-only">Previous</span>
				</a>
				<a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
					<span class="carousel-control-next-icon" aria-hidden="true"></span>
					<span class="sr-only">Next</span>
				</a>
			</div>  -->
				<div class="row">
					<div class="container">
						<div class="login-content" >
							<div class="login-logo"> 
								<a>
									<h2 class="navbar-brand">LOGIN</h2><p>Please enter your credentials to login.</p><p style="color:red;"><c:if test="${not empty error}">${error}</c:if></p><p style="c:or:red;"><c:if test="${not empty msg}">${msg}</c:if></p>
								</a> 
							</div>
							<div class="login-form">
								<form role="form"  name='loginForm' action="#" method='POST' id="myFormId" class="login-form inputHeight">
									<div class="form-group">
										<input id="username" type='text' name='username' class="form-control disablecopypaste" maxlength="30" size="35" autocomplete="off" placeholder="Enter Username">					
									</div>
									<div class="form-group">
										<input id="password" type='password' name='password' class="form-control disablecopypaste" maxlength="28" size="35" pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%!^\\&_.~*]).{8,28}$" autocomplete="off" placeholder="Enter Password"  />
									</div>
									<div class="row">
										<div class="col-md-6 col-sm-6 col-xs-12 enter_captcha">
											<div class="form-group">
												<input type='text' class="form-control disablecopypaste" size="35" id="txtInput" name="txtInput"  placeholder="Enter Captcha"  autocomplete="off"/>	
											</div>
										</div>
										<div class="col-md-6 col-sm-6 col-xs-12 no-padding-left">
											<div class="form-group captcha">
												<div class="input-group">
													<canvas id="canvasCapcha" width="100%" height="50%"  class="form-control disablecopypaste" readonly></canvas> 
													<span class="input-group-btn">
														<button class="btn btn-primary btn-sm" id="btnrefresh" tabindex="-1" type="button"><img alt="Refersh" src="login_file/referesh.ico"></button>
													</span>
												</div>
											</div>
										</div>
									</div>
									<div class="form-group login">
										<button type="submit" class="btn btn-success btn-flat m-b-30 m-t-30" onclick="return validation();">LOGIN</button>
										<div class="tooltip">
											<a>Reset Password</a>
											<span class="tooltiptext">Please click <a target="_blanck" href="/doc/Request_for_New_Password.pdf" >here</a> to download the reset password form and send it to MISO.</span>
										</div>
									</div>
									<input type="hidden" id="csrfIdSet" name="" value="" />
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		<div class="middle_content" align="center"  id="about_us" style="display:none;">
		<div class="row" style="margin:15px 0 0;">
			<div class="col-md-3">
				<h4 class="about_heading">About Us</h4>
				<p class="about_content">Management Information Systems Organization (MISO) is the nodal agency to maintain the centralized database in respect of personnel, equipment, vehicles and health in respect of Indian Army (IA) for providing management related information to IHQ of MoD (Army) and other agencies. Data pertaining to authorization and holding of manpower, transport and equipment is received from a large number of sources e.g. all types of army units/ depots record offices, directorates, branches and the Ministry of Defence. Data is compiled and numerous reports are generated to provide information to various stakeholders.</p>
			</div>
			<div class="col-md-3">
				<h4 class="about_heading">OUR AIM</h4>
				<p class="about_content">MISO aims at providing information to help in taking decisions on issues of planning, procurement and provisioning as well as on unit move, issue of vehicles and equipment to units.</p>
			</div>
			<div class="col-md-6">
				<h4 class="about_heading">ACHIEVEMENTS</h4>
				<div class="about_content">
					<p>MISO captures data pertaining to organizational structure (ORBAT), authorization & holding of manpower, weapons/equipment and vehicles in a centralized database hosted on Army Data Network (ADN). The major milestones achieved due to are as below.</p>
					<ul><li> Updation of data of vehicles, equipment and manpower held by IA units (over 10,000) through direct access to the application on AND. The application has resulted in management of data of over 10,000 units / establishment, 339 formation HQs, 95 Ordinance Depots, 50 Record Offices and other miscellaneous units. Application is used for generation of BA Nos, SUS Nos and issue of vehicles.</li>
					<li> The application maintains data of over 49,000 officers, 13 lakh JCOs/OR, 2.2 lakh ‘B’ vehicles, 10,000 ‘A’ vehicles, 2500 weapons / equipment and admission / discharge / OPD data of all military hospitals. The entire process leads to provisioning of data for planning and decision making on revenue procurement by MGO of approx RS 10,000 Cr per year and also for recruitment and management of personnel.</li>
					<li> The application also facilitates addressing various Parliamentary Queries (PQs) and RTI queries as received from time to time by querying the database.</li></ul>
				</div>
			</div>
		</div>
	</div>
<!--<div align="center"  class="middle_content team_miso" id="team_miso" style="display:none;">	  
		<h3><b>DEVELOPMENT & COMMISSIONING TEAM</b></h3>           
        <div class="container-fluid">
           	<div class="row" style="justify-content:center;">
               	<p class="p1" style=" display:inline-block; padding:5px 10px 0px 10px; font-size:20px; margin-bottom:0;"><b> BRIG AJAY KUMAR SHARMA</b></p>
           	</div>
           	<div class="row" style="justify-content:space-around;">
				<div class="col-sm-3 info"><p class="p1" style="display:inline-block; padding:5px 10px;"><b>COL DEEPAK SISODIA</b></p></div>
				<div class="col-sm-3 info"><p class="p1" style="display:inline-block; padding:5px 10px;"><b>COL SHIV MANGAL SINGH</b></p></div>
				<div class="col-sm-3 info"><p class="p1" style="display:inline-block; padding:5px 10px;"><b>COL UMED SINGH MITHARWAL</b></p></div>
				<div class="col-sm-3 info"><p class="p1" style="display:inline-block; padding:5px 10px;"><b>COL SANJIV RAJA GOPAL</b> </p></div>
			</div>
			<div class="row" style="justify-content:space-around;">
				<div class="col-sm-3 info"><p class="p1" style=" display:inline-block; padding:5px 10px;"><b>COL SURYA KANT PANDA</b></p></div>
				<div class="col-sm-3 info"><p class="p1" style=" display:inline-block; padding:5px 10px;"><b>COL SANJEEV KUMAR</b></p></div>
				<div class="col-sm-3 info"><p class="p1" style=" display:inline-block; padding:5px 10px;"><b>LT COL ARUN KUMAR YADAV </b></p></div>
				<div class="col-sm-3 info"><p class="p1" style=" display:inline-block; padding:5px 10px;"><b>LT COL DEEPIKA MALHOTRA </b></p></div>
			</div>
          	<div class="row" style="justify-content:space-around;">
            	<div class="col-sm-3 info"><p class="p1" style="display:inline-block; padding:5px 10px;"><b>LT COL RITESH KUMAR SINGH </b></p></div>
				<div class="col-sm-3 info"><p class="p1" style=" display:inline-block; padding:5px 10px;"><b>LT COL AKHIL SINGHAL, SM</b></p></div>
				<div class="col-sm-3 info"><p class="p1" style=" display:inline-block; padding:5px 10px;"><b>COL DIVESH SINGH SPEHIA</b></p></div>
				<div class="col-sm-3 info"><p class="p1" style=" display:inline-block; padding:5px 10px;"><b>LT COL NIKHIL KHULLAR</b> </p></div>
			</div>
            <div class="row" style="justify-content:space-around;">
             	<div class="col-sm-3 info"><p class="p1" style=" display:inline-block; padding:5px 10px;"><b>LT COL SUMANT YADAV</b></p></div>  
                <div class="col-sm-3 info"><p class="p1" style=" display:inline-block; padding:5px 10px;"><b>LT COL MANINDER SINGH</b></p></div>  
                <div class="col-sm-3 info"><p class="p1" style="display:inline-block; padding:5px 10px;"><b>LT COL VINOD SINGH RAWAT</b></p></div> 
				<div class="col-sm-3 info"><p class="p1" style=" display:inline-block; padding:5px 10px;"><b>LT COL SHRUTIKA DUTTA</b></p></div> 
			</div>
          	<div class="row">
               	<div class="col-sm-3 info"><p class="p1" style="display:inline-block; padding:5px 10px;"><b>MAJ ADITYA BHOSALE, SM </b></p></div>
                <div class="col-sm-3 info"><p class="p1" style="display:inline-block; padding:5px 10px;"><b>MAJ PS RAJPUT </b></p></div>
				<div class="col-sm-3 info"><p class="p1" style=" display:inline-block; padding:5px 10px;"><b> MAJ ANKITA SHARMA</b></p></div>
				<div class="col-sm-3 info"><p class="p1" style=" display:inline-block; padding:5px 10px;"><b>MAJ ATHAR YUSUF</b></p></div>  
            </div>
           	<div class="row">
            	<div class="col-sm-3 info"><p class="p1" style="display:inline-block; padding:5px 10px;"><b> MAJ TAMANNA BHASIN</b></p></div>
            </div>
            <br/><br/>
          	<div class="row">
				<div class="col-sm-12">
					<h3><b>IN COLLABORATION WITH</b></h3>
                  	<p class="p1" style="display:inline-block; padding:5px 10px;border-radius:10px;font-size:20px;"><b>Bhaskaracharya National Institute for Space Applications and Geo-Informatics (BISAG-N)</b></p><br/>
                  	<p class="p1" style="display:inline-block; padding:5px 10px;border-radius:10px;font-size:20px;"><b>Ministry of Electronics & Information Technology (MeitY)</b></p><br/>
                  	<p class="p1" style="display:inline-block; padding:5px 10px;border-radius:10px;font-size:20px;"><b>Goverment of India</b></p><br/>
				</div>
			</div>
            <br/><br/>
		</div>
	</div> -->
	<div class="middle_content" align="center" id="contact_us" style="display:none;">
		<!-- <div style="background-color:#672a2a;color:#fff;font-size:20px;text-transform:uppercase;">
			<b>Contact Us</b>
		</div>-->
		<div class="row" style="margin:15px 0 0;">
			<div class="col-md-12"><div class="col-md-4"></div><div class="col-md-4"><div class="contact_info"><p><img alt="Refersh" src="login_file/telephone.ico"><b> COL MISO, OIC AUTOCELL : </b></p><h5>39951</h5></div></div><div class="col-md-4"></div></div>
		   	<div class="col-md-3"><div class="contact_info"><p><img alt="Refersh" src="login_file/telephone.ico"><b> MISO Helpdesk : </b></p><h5>39747</h5></div></div>
		   	<div class="col-md-3"><div class="contact_info"><p><img alt="Refersh" src="login_file/telephone.ico"><b> GSO1 CUE & ORBAT :</b></p><h5>39946</h5></div></div>
		   	<div class="col-md-3"><div class="contact_info"><p><img alt="Refersh" src="login_file/telephone.ico"><b> GSO1 TPT SUB GP  :</b></p><h5>39854, 26146453</h5></div></div>
		   	<div class="col-md-3"><div class="contact_info"><p><img alt="Refersh" src="login_file/telephone.ico"><b> GSO1 WPN & EQPT SUB GP :</b></p><h5>39538, 26146523</h5></div></div>
		   	<div class="col-md-3"></div>
		   	<div class="col-md-3"><div class="contact_info"><p><img alt="Refersh" src="login_file/telephone.ico"><b> GSO1 PERSONNEL SUB GP :</b></p><h5>39861, 26146414</h5></div></div>
		   	<div class="col-md-3"><div class="contact_info"><p><img alt="Refersh" src="login_file/telephone.ico"><b> GSO1 MISO DATA CENTRE :</b> </p><h5>39288, 26141286</h5></div></div>
		</div>
		<div style="margin-top:15px;">
			<p class="about_heading">Please Note :</p>
			<ul style="display:inline-block;list-style-type:square;color:#672a2a;font-weight:600;font-size:16px;">
			    <li style="margin-bottom:8px;">Office timings are 0900h-1300h and 1400h-1730h.</li>
				<li style="text-align:left;">Offices are closed on Saturday and Sundays.</li>
			</ul>
		</div>
	</div>
	<!--  FP MIS -->
		<div class="middle_content" align="center" id="fp_mis" style="display: none;" > 
			<div class="col-md-6">
				<!-- <h4 class="about_heading">FP MIS</h4> -->
				<div class="about_content">					
					 <p>FPMIS has been developed by MISO Programmers in consultation with staff of FP Dte. </p>
					 <p>MISO staff may be contacted for addressing technical application related queries and creation of User Ids. </p>
					 <p>Queries relating to Fund management, Allocation and Distribution may be addressed to DGFP/FP2 or GSO1 FPMIS (34514). </p>
				</div>
			</div>
			<div class="col-md-6">
				<div style="margin-top: 15px;">
					<p class="about_heading">ASCON Contact No :</p>
					<ul style="display: inline-block;list-style-type: square;color:#672a2a;font-weight: 600;font-size: 16px;">
						<li style="text-align: left;">COL MISO : 39951</li>
						<li style="text-align: left;">GSO1 MISO Coord : 39641</li>
						<li style="text-align: left;">MISO Devp Center : 34617</li><br>
						<li style="text-align: left;">COL FP : 33934 </li>
						<li style="text-align: left;">GSO1 FPMIS : 34514 </li>
					</ul>
				</div>
				<div style="margin-top: 15px;">
					<p class="about_heading">Tutorial:</p>
					<ul style="display: inline-block;list-style-type: square;color:#672a2a;font-weight: 600;font-size: 16px;">
						<!-- <a href="/doc/fpmis_tutorial.pptx">Tutorial in PPT</a><br> -->
						<a target="_blanck" href="/doc/fpmis_tutorial.pdf">Tutorial in PDF</a>
						<br><a target="_blanck" href="/doc/fpmis_tutorial.ppt">Tutorial in PPT</a>
					</ul>
				</div>
			</div>
		</div>
		<!-- FP MIS -->
</div>

<footer>
	<span class="content1">Collab: BISAG-N, MeitY, GoI </span>
	<span class="content1">All rights reserved - MISO</span>
	<span class="content1">App vetted by Army Cyber Gp on 25 Aug 2020 & Content Vetted by MI-11 on 01 Sep 2020.</span>
	<!-- <span class="content1 left">Collab: BISAG-N, MeitY, GoI | All rights reserved - MISO. App vetted by Army Cyber Gp on 25 Aug 2020 & Content Vetted by MI-11 on 01 Sep 2020.</span>
	<span class="content2 right">${server} Last Updated 25-03-2022 Visitors ${visiter_count}</span> -->
	<span class="content1">Last Updated:25-03-2022</span>
	<span class="content1"><i class='fa fa-feed'></i> ${server}</span>	
	<span class="content1 visitorscount">Visitors:${visiter_count}</span>
</footer>
<c:url value="getDownloadWhatsNew" var="downloadUrl" />
<form:form action="${downloadUrl}" method="post" id="getDownloadWhatsNewForm" name="getDownloadWhatsNewForm" modelAttribute="whats_new_id">
	<input type="hidden" name="whats_new_id" id="whats_new_id" value="0" />
</form:form>
</body>
</html>