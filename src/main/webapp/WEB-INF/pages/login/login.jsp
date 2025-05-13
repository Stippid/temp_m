<%-- <%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>  --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%> 
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%> 
<%@ taglib prefix="dandelion" uri="http://github.com/dandelion" %> 
 
  
<!doctype html> 
<html><head>
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
	 <title>MISO</title>
<!-- 	 <link rel="stylesheet" href="login_file/scss/style.css">  -->
	<link rel="stylesheet" href="login_file/style.css">
	
	<script src="login_file/assets/js/jquery-3.7.1.min.js"></script>
<!--  	<script src="login_file/jquery-3.4.1.min.js"></script>  -->
  	<link rel="stylesheet" href="login_file/css/bootstrap.min.css">
	
	<link rel="shortcut icon" href="login_file/img/favicon.png">
<!--     <link rel="shortcut icon" href="login_file/dgis-logo_favicon.jpg"> -->    	
    	
<!--     <script type="text/javascript" src="login_file/assets/js/vendor/jquery-2.1.4.min.js"></script>  -->
	<script src="login_file/assets/js/plugins.js"></script> 
<!-- 	<script src="login_file/assets/js/main.js"></script>  -->
<!--     <script src="login_file/jquery-2.2.3.min.js"></script>  -->

	 <link href="login_file/Calender/jquery-ui.css" rel="Stylesheet"></link>
     <script src="login_file/Calender/jquery-ui.js" type="text/javascript"></script>
     <script src="login_file/chatbot/handlebars.min.js"></script> 
	 <script src="login_file/chatbot/chatboot.js"></script>
     <link rel="stylesheet" href="login_file/chatbot/chatboot.css">
 	 <script src="login_file/custom.js"></script>
 	 <link rel="stylesheet" href="login_file/css/font-awesome.min.css">
<!--   	 <link rel="stylesheet" href="login_file/sendbtnfont-awesome.min.css"> -->
 	
 
	<script type="text/javascript">
	  	var csrfparname ="${_csrf.parameterName}";
	  	var csrfvalue="${_csrf.token}";
	  	var yuji = "<c:url value='/auth/login_check?targetUrl=${targetUrl}' />";
		var numb = [1,2,3,4,5];
		jQuery(document).ready(function(){
			var Navigation = document.getElementById("navbarSupportedContent");
			var navs = Navigation.getElementsByClassName("nav-link");
			for (var i = 0; i < navs.length; i++) {
				navs[i].addEventListener("click", function() {  /* mouseenter */
			    	var current = document.getElementsByClassName("active");
			    	if (current.length > 0) {current[0].className = current[0].className.replace(" active", "");}
					this.className += " active";
				});
			}
			$(".dropdown").click(function(){ 		/*  hover  */
				var dropdownMenu = $(this).children(".dropdown-menu");
				if(dropdownMenu.is(":visible")){dropdownMenu.parent().toggleClass("open");}
			});
			$(window).scroll(function() {
			    if ($(document).scrollTop() > 50) {$(".header_bottom").addClass("head_nav");} else {$(".header_bottom").removeClass("head_nav");}
			});
			
			
			 $(".dropdown-item").click(function() {
				 $('.nav-link').removeClass('active');
				$(this).parent().siblings().addClass("active");
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
					$("#txtInput").val(numb[0]+numb[1]+numb[2]+numb[3]+numb[4]);
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
			$("#login").hide();$("#contact_us").hide();$("#team_miso").hide();$("#about_us").show();
			$(".carousel-item img").css("height", "300px");
		}
		function teammiso(){
			$("#login").hide();	$("#contact_us").hide();$("#about_us").hide();$("#team_miso").show();
		}
		function contactus(){
			$("#login").hide();$("#about_us").hide();$("#team_miso").hide();$("#contact_us").show();
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
	</script>
	<script type="text/javascript">
		window.history.forward();
		function noBack() {window.history.forward();}
	</script>
	
	
	<!--  jankiiiii	. -->
<script>
 	var SuperPlaceholder = function(options) {  
  this.options = options;
  this.element = options.element
  this.placeholderIdx = 0;
  this.charIdx = 0;
  

  this.setPlaceholder = function() {
      placeholder = options.placeholders[this.placeholderIdx];
      var placeholderChunk = placeholder.substring(0, this.charIdx+1);
      document.querySelector(this.element).setAttribute("placeholder", this.options.preText + " " + placeholderChunk)
  };
  
  this.onTickReverse = function(afterReverse) {
    if (this.charIdx === 0) {
      afterReverse.bind(this)();
      clearInterval(this.intervalId); 
      this.init(); 
    } else {
      this.setPlaceholder();
      this.charIdx--;
    }
  };
  
  this.goReverse = function() {
      clearInterval(this.intervalId);
      this.intervalId = setInterval(this.onTickReverse.bind(this, function() {
        this.charIdx = 0;
        this.placeholderIdx++;
        if (this.placeholderIdx === options.placeholders.length) {
          // end of all placeholders reached
          this.placeholderIdx = 0;
        }
      }), this.options.speed)
  };
  
  this.onTick = function() {
      var placeholder = options.placeholders[this.placeholderIdx];
      if (this.charIdx === placeholder.length) {
        // end of a placeholder sentence reached
        setTimeout(this.goReverse.bind(this), this.options.stay);
      }
      
      this.setPlaceholder();
    
      this.charIdx++;
    }
  
  this.init = function() {
    this.intervalId = setInterval(this.onTick.bind(this), this.options.speed);
  }
  
  this.kill = function() {
    clearInterval(this.intervalId); 
  }
}
 
    window.onload = function() {
        var sp = new SuperPlaceholder({
          placeholders: ["Enter Your Text Here", "Enter Your Text Here"],
          preText: "",
          stay: 1000,
          speed: 100,
          element: '#message-to-send'
        });
        sp.init();
      }
 	</script>
 	
 	
<!--  jankiiiii	. -->
<!--end  chatbot -->
	
	
	
	
	
	
	
	
	
	
	
	
	<style type="text/css">
/* 	.about_us_text {max-width:295px;position:absolute;text-align:center;background-color:rgba(255,255,255,0.8);background-color:#1fc8db;background-image:linear-gradient(141deg,#9fb8ad 0,#1fc8db 51%,#2cb5e8 75%);border:1px solid #ccc;border-radius:10px;top:15px;opacity:.9;} */
/* 	.team_miso h3 {color:#17a2b8;font-size:23px;text-decoration:underline;padding-top:25px;margin-bottom:25px;} */
/* 	.col-sm-3.info{font-family:system-ui;text-align:center;height:50px;padding:23px 00px;} */
/* 	.p1{color:#672a2a;!important;font-family:system-ui;} */
/*    	.middle_content {height:auto;overflow-y:none;} */
/*  	@media screen and (max-width:1305px){.col-sm-3.info{padding:23px 40px;}} */
/* 	@media screen and (max-width:1200px){.col-sm-3.info{padding:23px 30px;}} */
/* 	@media screen and (max-width:1070px){.col-sm-3.info{padding:23px 20px;}} */
/* 	@media screen and (max-width:1000px){.col-sm-3.info{padding:23px 10px;}} */
</style>
</head>
<body oncontextmenu="return false">
<div class="wrapper">
<div class="main_head">
		<header id="header" class="header">
			<div class="header-menu">
	    		<div class="row">
			      	<!-- <div class="col-2 col-sm-2"><div class="header-left"><img src="login_file/indian_army.png" class="img-fluid" style="height:63px;"></div></div>
					<div class="col-8 col-sm-8"><div class="heading_content"><h1>MANAGEMENT INFORMATION SYSTEM ORGANISATION</h1><b style="display:none;" id="div_timeout">60</b></div></div>
			      	<div class="col-2 col-sm-2"><div id="language-select"> <img src="login_file/dgis-logo.jpg" class="img-fluid" style="height:63px;"></div></div> -->
			      	<div class="head_left"><div class="header-logo-left"><img src="login_file/img/indian_army.png" class="img-fluid"></div></div>
					<div class="head_center"><div class="header-title-main" ><h1>MGT INFO SYS ORG (MISO)</h1></div></div>
			      	<div class="head_right"><div class="header-logo-right"><img src="login_file/img/dgis-logo.png" class="img-fluid"></div></div>
				</div>
			</div>
	  	</header>
		 <!-- <div class="header_bottom">
			<div class="header_navigation">
				<div class="container-fluid">
					<div class="row">
						<div class="col-12 col-sm-12 col-md-12">
							<nav class="navbar navbar-expand-lg navbar-light header_nav">
								<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
									<span class="navbar-toggler-icon"></span>
								</button>
								<div class="collapse navbar-collapse" id="navbarSupportedContent">
									<ul class="navbar-nav header_list">
										<li class="nav-item" style="margin-top: -3px;"><a class="nav-link header_home active" href="login"><img alt="HOME" src="login_file/WHITE Home_15X15.png"> </a></li>
										<li class="nav-item dropdown">
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
										<li class="nav-item"><a class="nav-link" target="_blanck" href="/doc/FAQs.pdf">FAQs</a></li>
										<li class="nav-item dropdown">
											<a class="nav-link dropdown-toggle" href="" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">FAQs</a>
											<div class="dropdown-menu" aria-labelledby="navbarDropdown">
												<a class="dropdown-item" href="#">Login</a>
											  	<a class="dropdown-item" target="_blanck" href="doc/faq_3.1_pers_module_as on 27 Jul 22.pdf">Pers</a>
											</div>
										</li>
										<li class="nav-item"><a class="nav-link"  href="user_registrationUrl">User Registration</a></li>
									</ul>
								</div>
							</nav>
						</div>
					</div>
				</div>
			</div>
		</div>  -->
		
		 <div class="navbar-top-main">
							<nav class="navbar navbar-expand-lg navbar-light header_nav">
								<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
									<span class=""><i class="fa fa-bars"></i></span>
								</button>
								<div class="collapse navbar-collapse navbar-center" id="navbarSupportedContent">
									<ul class="navbar-nav header_list">
										<li class="nav-item"><a class="nav-link header_home active" href="login">Home</a></li>
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
												<a class="dropdown-item" target="_blanck" href="doc/modification_form.pdf">Modification Form</a>
											  	<a class="dropdown-item" target="_blanck" href="doc/New_User_Registration.pdf">New User Registration</a>
											  	<a class="dropdown-item" target="_blanck" href="doc/Request_for_New_Password.pdf">Request for New Password</a>
											  	<a class="dropdown-item" target="_blanck" href="/doc/Minimum_Desktop_Requirement.pdf">System Requirements</a>
											  	<a class="dropdown-item" target="_blanck" href="/doc/UserGuide3.0.pdf">Installation Guide</a>
											  	<a class="dropdown-item" target="_blanck" href="/doc/blank_census_form.pdf">CENSUS FORM - OFFRS</a>
												<a class="dropdown-item" target="_blanck" href="/doc/CENSUS FORM - JCOOR AND RECTS.pdf">CENSUS FORM - JCOOR AND RECTS</a>
												<a class="dropdown-item" target="_blanck" href="/doc/CENSUS FORM REG & NON REG CIVS.pdf">CENSUS FORM REG & NON REG CIVS</a>
												<!-- <a class="dropdown-item" target="_blanck" href="/doc/POSTING IN & OUT - OFFRS.pdf">POSTING IN & OUT - OFFRS</a>
												<a class="dropdown-item" target="_blanck" href="/doc/POSTING IN & OUT - JCOsOR & RECTS.pdf">POSTING IN & OUT - JCOsOR & RECTS</a>
												<a class="dropdown-item" target="_blanck" href="/doc/UPDT DATA - OFFR.pdf">UPDT DATA - OFFR</a>
												<a class="dropdown-item" target="_blanck" href="/doc/UPDT DATA - JCOsOR & RECTS.pdf">UPDT DATA - JCOsOR & RECTS</a>
												<a class="dropdown-item" target="_blanck" href="/doc/UPDT CENSUS FORM - JCOsOR & RECTS.pdf">UPDT CENSUS FORM - JCOsOR & RECTS</a> -->
											</div>
										</li>
										<li class="nav-item"><a href="#" class="nav-link" onclick="contactus();">Contact us</a></li>
										<li class="nav-item"><a href="https://portal.army.mil" target="_blanck" class="nav-link">Army Web</a></li>
										<li class="nav-item dropdown">
											<a class="nav-link dropdown-toggle" href="" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Tutorials</a>
											<div class="dropdown-menu" aria-labelledby="navbarDropdown">
												<a class="dropdown-item" target="_blanck" href="/doc/2FA_Login.mp4">How to login</a>
												<a class="dropdown-item" target="_blanck" href="/doc/mvcr.mp4">MVCR</a>
											  	<a class="dropdown-item" target="_blanck" href="/doc/MMS_UNIT_MCR.mp4">MCR</a>
											  	<a class="dropdown-item" target="_blanck" href="/doc/offr_census_video.mp4">HOW TO FILL OFFR CENSUS</a>
												<a class="dropdown-item" target="_blanck" href="/doc/iaff_generation.mp4">IAFF GENERATION</a>
												<a class="dropdown-item" target="_blanck" href="/doc/Jco_OR_Census_Data.mp4">JCO OR Census and Data Updt</a>
												<a class="dropdown-item" target="_blanck" href="/doc/POSTING_IN_OUT.mp4">Posting In Out</a>
												<a class="dropdown-item" target="_blanck" href="/doc/HELD_STR_JCOOR.mp4">Held Str JCOOR</a>
											</div>
										</li>
										<li class="nav-item dropdown">
											<a class="nav-link dropdown-toggle" href="" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">User Manuals</a>
											<div class="dropdown-menu" aria-labelledby="navbarDropdown">
											  	<!-- <a class="dropdown-item" target="_blanck" href="">How To Fill Offr Census</a>-->
											  	<a class="dropdown-item" target="_blanck" href="/doc/IT_ASSETS.pdf">IT ASSETS</a>
											</div>
										</li>
										<li class="nav-item dropdown">
											<a class="nav-link dropdown-toggle" href="" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">System Prerequisites</a>
											<div class="dropdown-menu" aria-labelledby="navbarDropdown">
												<a class="dropdown-item" target="_blanck" href="/doc/jre-8u261x64.exe">Java</a>
											  	<a class="dropdown-item" target="_blanck" href="/doc/Google Chrome 75.rar">Web Browser</a>
												<a class="dropdown-item" target="_blanck" href="/doc/ePassTokenSoftware.rar">Token SW</a>
												<a class="dropdown-item" target="_blanck" href="/doc/Cert.rar">Certificates</a>
												<a class="dropdown-item" target="_blanck" href="/doc/miso.bat">Batch File</a>
											</div>
										</li>
<!-- 										<li class="nav-item"><a class="nav-link" target="_blanck" href="/doc/FAQs.pdf">FAQs</a></li> -->
										
										<li class="nav-item dropdown">
											<a class="nav-link dropdown-toggle" href="" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">FAQs</a>
											<div class="dropdown-menu" aria-labelledby="navbarDropdown">
												<a class="dropdown-item" target="_blanck" href="/doc/FAQs.pdf
">MISO Appl FAQ</a>
											  	<a class="dropdown-item" target="_blanck" href="">PSG FAQ</a>
											  	<a class="dropdown-item" target="_blanck" href="/doc/miso_prtsn.pdf">Presentation on MISO</a>
											</div>
										</li>
										
<!-- 										<li class="nav-item"><a class="nav-link"  href="user_registrationUrl">User Registration</a></li> -->
									</ul>
								</div>
							</nav>
		</div> 
		</div>
		
<!-- 		<div class="ticker dash-tic"> -->
<%-- 			<h3>${layout}</h3> --%>
<!-- 	 	</div> -->

		

	  	<div class="content banner">
	  	
	  	
	  	
	<div class="" id="login">
	
	
	
	<div class="banner_div p-0">
	<div class="login-banner-main">
<!--    				<img src="login_file/img/miso_banner.jpg" class="img-fluid" alt="banner1"> -->
		</div>
	</div>
	
	<div class="bottom_div">
		<div class="bottom_head">
			<h5><i>--- एक पेड़ माँ के नाम  !  Please Login to MISO Application to Fill Details --- </i></h5>
		</div>
		
	<%-- <div class="main_eventdiv">
						<div class="event-box">
							<div class="events-feed">

								<div class="container-fluid">
							        <div id="filter-buttons">
							            <div class="filter-button" data-filter="filter1"><b>Comd</b></div>
							            <div class="filter-button" data-filter="filter2"><b>Corps</b></div>
							            <div class="filter-button" data-filter="filter3"><b>Div</b></div>
							        </div>
							        
							        <div id="filter1" class="slide-container">
							            <div class="event_img">
											<img src="login_file/img/command.png"
											class="img-fluid w-100" alt="banner">
										</div>
							        </div>
							        <div id="filter2" class="slide-container">
							            <div class="event_img">
											<img src="login_file/img/corps.png"
											class="img-fluid w-100" alt="banner">
										</div>
							        </div>
							        <div id="filter3" class="slide-container">
							            <div class="event_img">
											<img src="login_file/img/div.png"
											class="img-fluid w-100" alt="banner">
										</div>
							        </div>
							        <div class="home-title">
										<p class="">Best Updated Fmn : Jun 2024</p>
									</div>
							    </div>
							    	

							</div>
							<div class="news-feed">

								<div class="news-feed-inner" style="padding:15px; margin-top: -55px;">

								<div class="visitor-count">

													<p class="visitor-count-title">Visitor Count</p>

													<div class="row mx-1px mb-5px">

														<div class="col-4">
															<div class="count-main">
																<p class="count">Daily</p>
																<p>${todayslogin}</p>
															</div>
														</div>

														<div class="col-4">
															<div class="count-main">
																<p class="count">Monthly</p>
																<p>${montlyLogin}</p>
															</div>
														</div>

														<div class="col-4">
															<div class="count-main">
																<p class="count">Yearly</p>
																<p>${yearlyLogin}</p>
															</div>
														</div>

													</div>

												</div>

									 <div class="home-title">
										<h3 class="news-feed-tite"> IMP BULLETINS</h3>
									</div>
									<div class="marquee_div">
										<div class="marquee">
										
											<ul>
											<li class="info-list">
													<div class="date-with-list">
														<div class="custom-info-calendar-date">
															<span class="custom-info-calendar-main">18 Feb</span>
															<span class="custom-info-calendar-sub"> 2025</span>
														</div>
														<div class="list-content">													
															<a target="_blank" href="/doc/it_pers.pdf"><SPAN>new</SPAN>ONLINE SANCTION FACILITY OF PERS IT ASSET MODULE IN MISO 4.0</a>
														
														</div>
													</div>
												</li>
											
											<li class="info-list">
													<div class="date-with-list">
														<div class="custom-info-calendar-date">
															<span class="custom-info-calendar-main">06 Sep</span>
															<span class="custom-info-calendar-sub"> 2024</span>
														</div>
														<div class="list-content">													
															<a target="_blank" href="/doc/wpn_eqpt.pdf"><SPAN>new</SPAN>CO Cert Format to be Provided by Units for WPN & EQPT, in Case of sp docu not available</a>
														
														</div>
													</div>
												</li>
												<li class="info-list">
													<div class="date-with-list">
														<div class="custom-info-calendar-date">
															<span class="custom-info-calendar-main">23 April</span>
															<span class="custom-info-calendar-sub"> 2024</span>
														</div>
														<div class="list-content">
<!-- 															<a><span>new</span>How to add Non Effective / Retired Offrs as Re-emp.															</a> -->
															<a target="_blank" href="/doc/non_effective_offrs_Re-emp.pdf">How to add Non Effective / Retired Offrs as Re-emp.</a>
															
															<p>15-May-2024</p>
														</div>
													</div>
												</li>
												<li class="info-list">
													<div class="date-with-list">
														<div class="custom-info-calendar-date">
															<span class="custom-info-calendar-main">23 April</span>
															<span class="custom-info-calendar-sub"> 2024</span>
														</div>
														<div class="list-content">
<!-- 															<a><span>new</span>Steps to be followed to make an Offr Retired / Non Effective. -->
															<a target="_blank" href="/doc/Making_NE_Retired.pdf"><span>new</span>Steps to be followed to make an Offr Retired / Non Effective.</a>
													
															<p>27-May-2024</p>
														</div>
													</div>
												</li>
												<li class="info-list">
													<div class="date-with-list">
														<div class="custom-info-calendar-date">
															<span class="custom-info-calendar-main">23 April</span>
															<span class="custom-info-calendar-sub"> 2024</span>
														</div>
														<div class="list-content">													
															<a target="_blank" href="/doc/Supernumerary_str.pdf"><SPAN>new</SPAN>Steps to followed to place an Offrs under Supernumerary Str.</a>
														
														</div>
													</div>
												</li>
												<li class="info-list">
													<div class="date-with-list">
														<div class="custom-info-calendar-date">
															<span class="custom-info-calendar-main">23 April</span>
															<span class="custom-info-calendar-sub"> 2024</span>
														</div>
														<div class="list-content">
<!-- 															<a><span>new</span>Steps to be followed for Posting in / out of Offr  -->
<!-- 															</a> -->
															<a target="_blank" href="/doc/SPosting_in_Out.pdf"><span>new</span>Steps to be followed for Posting in / out of Offr</a>
														
														</div>
													</div>
												</li>
												
											</ul>
										</div>
									</div> 

								</div>

							</div>
						</div>
						<div class="event-box2">
		<div class="news-feed">
		
		<div class="news-feed-inner" style="padding:15px;margin-top: -20px;">
		<div class="home-title">
			<h3 class="news-feed-tite">Tree Plantation Drive: 22 Jul - 27 Jul</h3>
			<a href="https://misoanalytics.army.mil:8443/open-view/229935/9d99d35b709c73226b5edb1e7277fb08" style="border:0.5px solid red;padding:4px; background-color:linen;border-radius: 10px; " >View</a>

		</div>
					<!-- <div class="upd_div">
						<div class="news_upd">
						<ul>
							
							<li class="info-list">
							<div class="date-with-list">
								<div class="custom-info-calendar-date">
								<span class="custom-info-calendar-main">23 April</span> 
								</div>
								<div class="list-content">
								<a><span>Help Desk</span> For Queries wrt Login Issues pl contact MISO Help Desk 39747</a>
								<p>05-Jun-2024</p>
								</div>
								</div>
							</li>
							<li class="info-list">
							<div class="date-with-list">
								<div class="custom-info-calendar-date">
								<span class="custom-info-calendar-main">23 April</span> 
								</div>
								<div class="list-content">
								<a><span>ORBAT</span>Arty, Mech Inf, Engrs, AAD, Armd and Inf Units are req to contact&nbsp;&nbsp;G1 SD4 / Col SD4 at 33459 / 33458</a>
								<p>05-Jun-2024</p>
								</div>
								</div>
							</li>
							<li class="info-list">
							<div class="date-with-list">
								<div class="custom-info-calendar-date">
								<span class="custom-info-calendar-main">23 April</span> 
								</div>
								<div class="list-content">
								<a><span>Transport (TMS) </span>In case of an erroneous entry, users have been extended rights to amend KM run and Veh Cls for A, B & C Vehs  </a>
								<p>05-Jun-2024</p>
								</div>
								</div>
							</li>
							<li class="info-list">
							<div class="date-with-list">
								<div class="custom-info-calendar-date">
								<span class="custom-info-calendar-main">23 April</span> 
								</div>
								<div class="list-content">
								<a><span>Wpn & Eqpt (MMS)</span>Units are requested to fill regn number of Wpn & Eqpt as &nbsp;&nbsp;&nbsp;&nbsp;it is a mandatory reqmt for transfer / HTO of stores </a>
								<p>05-Jun-2024</p>
								</div>
								</div>
							</li>
							<li class="info-list">
							<div class="date-with-list">
								<div class="custom-info-calendar-date">
								<span class="custom-info-calendar-main">23 April</span>
								</div>
								<div class="list-content">
								<a><span>User Registration</span> New User Registration form is avlb under Documents Tab
								</a>
								<p>05-Jun-2024</p>
								</div>
								</div>
							</li>
							</ul>
						</div>
					</div> -->
					<!-- <img alt="" src="login_file/swat.png"> --> 
					<iframe frameborder=0 width="100%" height="600" src="https://misoanalytics.army.mil:8443/open-view/191956/c4fda4769c41c72c99ce27ecfce9c021"></iframe>
					
				</div>
			</div>
			</div>
			<div class="event-box1">
				<div class="mis_ecosys">
					<div class="mis_title">
						<h4>MISO ECOSYSTEM</h4>
					</div>
					<div class="row mt-2">
						<div class="col-6">
							<div class="mis_common">
							<div class="mis_content">
								<div class="mis_ico">
<!-- 									<i class="fa fa-bars"></i> -->
									<img src="login_file/img/ecosystem/ASAAN.png">
								</div>
								<div class="mis_back">
									<a href="https://asaan.army.mil" target="_blank"><button class="btn btn-light mis_btn">Login</button></a>
								</div>
							</div>
								<h5>ASAAN</h5>
							</div>
						</div>
						<div class="col-6">
							<div class="mis_common">
							<div class="mis_content">
								<div class="mis_ico">
									<img src="login_file/img/ecosystem/DISHA.png">
								</div>
								<div class="mis_back">
									<a href="https://disha.army.mil" target="_blank"><button class="btn btn-light mis_btn">Login</button></a>
								</div>
								</div>
								<h5>DISHA</h5>
							</div>
						</div> 
					</div>
					<div class="row mt-2 mis_second">
					<div class="col-6">
							<div class="mis_common">
							<div class="mis_content">
								<div class="mis_ico">
									<img style="width:40px;height:40px;" src="login_file/img/ecosystem/GAPS.png">
								</div>
								<div class="mis_back">
									<a href="https://gaps.army.mil" target="_blank"><button class="btn btn-light mis_btn">Login</button></a>
								</div>
							</div>
								<h5>GAPS</h5>
								
							</div>
						</div>
						<div class="col-6">
							<div class="mis_common">
							<div class="mis_content">
								<div class="mis_ico">
									<img style="width:40px;height:40px;" src="login_file/img/ecosystem/RAMS.png">
								</div>
								<div class="mis_back">
									<a href="https://rams.army.mil" target="_blank"><button class="btn btn-light mis_btn">Login</button></a>
								</div>
								</div>
								<h5>RAMS</h5>
							</div>
						</div>
					</div>
					<div class="row mt-2 mis_second">
					
						<div class="col-6">
							<div class="mis_common">
							<div class="mis_content">
								<div class="mis_ico">
									<img src="login_file/img/ecosystem/ROLLING.png">
								</div>
								<div class="mis_back">
									<a href="https://rrm.army.mil" target="_blank"><button class="btn btn-light mis_btn">Login</button></a>
								</div>
							</div>
								<h5>ROLLING</h5>
								<h5>STOCK</h5>
							</div>
						</div>
						<div class="col-6">
							<div class="mis_common">
							<div class="mis_content">
								<div class="mis_ico">
									<img src="login_file/img/ecosystem/SATARK.png">
								</div>
								<div class="mis_back">
								<a href="https://satark.army.mil" target="_blank"><button class="btn btn-light mis_btn">Login</button></a>
								</div>
							</div>
								<h5>SATARK</h5>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="event-box">
				<div class="mis_ecosys">
					<div class="mis_title">
						<h4>MISO 4.0</h4>
					</div>

								<div class="login-div-box">

									<div class="login-box-main">
<!-- 										<h3 class="login-box-title">Login</h3> -->
										<!-- <p class="login-inst">Please enter your credentials to login.</p> -->
										<p style="color: red;">
											<c:if test="${not empty error}">${error}</c:if>
										</p>
										<p style="c: or:red;">
											<c:if test="${not empty msg}">${msg}</c:if>
										</p>
										<div class="login-box-inner">


											<form role="form" name='loginForm' action="#" method='POST'
												id="myFormId">
												<div class="col-md-12">
													<div class="form-group">
														<label class="mb-0">Username</label> <input id="username"
															type='text' name='username'
															class="form-control disablecopypaste" maxlength="30"
															size="35" autocomplete="off" placeholder="Enter Username">
													</div>
												</div>

												<div class="col-md-12">
													<div class="form-group">
														<label class="mb-0"> Password </label> <input
															id="password" type='password' name='password'
															class="form-control disablecopypaste" maxlength="28"
															size="35"
															pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%!^\\&_.~*]).{8,28}$"
															autocomplete="off" placeholder="Enter Password" />
													</div>
												</div>

												<div class="col-md-12">
													<div class="row m-0">
														<div class="col-md-6 col-6 p-0">
															<div class="form-group">
																<label class="mb-0"> Captcha </label> <input type='text'
																	class="form-control disablecopypaste" size="35"
																	id="txtInput" name="txtInput"
																	placeholder="Enter Captcha" autocomplete="off" />
															</div>
														</div>
														<div class="col-md-6 col-6 center-center">
															<div class="form-group">

																<div class="input-group">
																	<canvas id="canvasCapcha" width="100%" height="45%"
																		class="form-control disablecopypaste" readonly></canvas>
																	<span class="input-group-btn align-self-center">
																		<button class="btn btn-primary btn-sm" id="btnrefresh"
																			tabindex="-1" type="button">
																			<img alt="Refersh" src="login_file/img/referesh.ico">
																		</button>
																	</span>
																</div>

															</div>
														</div>
													</div>
												</div>

												<div class="col-md-12">
													<button type="submit" class="btn btn-submit"
														onclick="return validation();">Login</button>
												</div>
											</form>

										</div>
										
									</div>

								</div>
								<div class="col-md-12 mt-2 visitor_div">

										<img alt="" style="height:140%"src="login_file/pedd.png">

												<!-- <div class="visitor-count">

													<p class="visitor-count-title">Visitor Count</p>

													<div class="row mx-1px mb-5px">

														<div class="col-4">
															<div class="count-main">
																<p class="count">Daily</p>
																<p>${todayslogin}</p>
															</div>
														</div>

														<div class="col-4">
															<div class="count-main">
																<p class="count">Monthly</p>
																<p>${montlyLogin}</p>
															</div>
														</div>

														<div class="col-4">
															<div class="count-main">
																<p class="count">Yearly</p>
																<p>${yearlyLogin}</p>
															</div>
														</div>

													</div>

												</div> -->

											</div>

							</div>
			</div>
		</div>
	</div>
	 --%>
						<div class="main_eventdiv">
						<div class="event-box1">
							<div class="mis_ecosys">
								<div class="mis_title">
									<h4>MISO ECOSYSTEM</h4>
								</div>
								<div class="row mt-2">
									<div class="col-6">
										<div class="mis_common">
											<div class="mis_content">
												<div class="mis_ico">
													<!-- 									<i class="fa fa-bars"></i> -->
													<img src="login_file/img/ecosystem/ASAAN.png">
												</div>
												<div class="mis_back">
													<a href="https://asaan.army.mil" target="_blank"><button
															class="btn btn-light mis_btn">Login</button></a>
												</div>
											</div>
											<h5>ASAAN</h5>
										</div>
									</div>
									<div class="col-6">
										<div class="mis_common">
											<div class="mis_content">
												<div class="mis_ico">
													<img src="login_file/img/ecosystem/DISHA.png">
												</div>
												<div class="mis_back">
													<a href="https://disha.army.mil" target="_blank"><button
															class="btn btn-light mis_btn">Login</button></a>
												</div>
											</div>
											<h5>DISHA</h5>
										</div>
									</div>
								</div>
								<div class="row mt-2 mis_second">
									<div class="col-6">
										<div class="mis_common">
											<div class="mis_content">
												<div class="mis_ico">
													<img style="width: 40px; height: 40px;"
														src="login_file/img/ecosystem/GAPS.png">
												</div>
												<div class="mis_back">
													<a href="https://gaps.army.mil" target="_blank"><button
															class="btn btn-light mis_btn">Login</button></a>
												</div>
											</div>
											<h5>GAPS</h5>

										</div>
									</div>
									<div class="col-6">
										<div class="mis_common">
											<div class="mis_content">
												<div class="mis_ico">
													<img style="width: 40px; height: 40px;"
														src="login_file/img/ecosystem/RAMS.png">
												</div>
												<div class="mis_back">
													<a href="https://rams.army.mil" target="_blank"><button
															class="btn btn-light mis_btn">Login</button></a>
												</div>
											</div>
											<h5>RAMS</h5>
										</div>
									</div>
								</div>
								<div class="row mt-2 mis_second">

									<div class="col-6">
										<div class="mis_common">
											<div class="mis_content">
												<div class="mis_ico">
													<img src="login_file/img/ecosystem/ROLLING.png">
												</div>
												<div class="mis_back">
													<a href="https://rrm.army.mil" target="_blank"><button
															class="btn btn-light mis_btn">Login</button></a>
												</div>
											</div>
											<h5>ROLLING</h5>
											<h5>STOCK</h5>
										</div>
									</div>
									<div class="col-6">
										<div class="mis_common">
											<div class="mis_content">
												<div class="mis_ico">
													<img src="login_file/img/ecosystem/SATARK.png">
												</div>
												<div class="mis_back">
													<a href="https://satark.army.mil" target="_blank"><button
															class="btn btn-light mis_btn">Login</button></a>
												</div>
											</div>
											<h5>SATARK</h5>
										</div>
									</div>
								</div>
								<div class="visitor-count">
									<p class="visitor-count-title">Visitor Count</p>
									<div class="row mx-1px mb-5px">
										<div class="col-4">
											<div class="count-main">
												<p class="count">Daily</p>
												<p>${todayslogin}</p>
											</div>
										</div>
										<div class="col-4">
											<div class="count-main">
												<p class="count">Monthly</p>
												<p>${montlyLogin}</p>
											</div>
										</div>
										<div class="col-4">
											<div class="count-main">
												<p class="count">Yearly</p>
												<p>${yearlyLogin}</p>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						
						<div class="event-box2">
							<div class="news-feed">

								<div class="news-feed-inner"
									style="padding: 15px; margin-top: -20px;">
									<div class="home-title">
										<h3 class="news-feed-tite" style="font-size: 22px;">Tree Plantation Drive: 22 Jul
											- 27 Jul</h3>
										<a href="https://misoanalytics.army.mil:8443/open-view/229935/9d99d35b709c73226b5edb1e7277fb08"
											style="border: 0.5px solid red; padding: 4px; background-color: linen; border-radius: 10px;">View</a>
									</div>
									<!-- <div class="upd_div">
						<div class="news_upd">
						<ul>
							
							<li class="info-list">
							<div class="date-with-list">
								<div class="custom-info-calendar-date">
								<span class="custom-info-calendar-main">23 April</span> 
								</div>
								<div class="list-content">
								<a><span>Help Desk</span> For Queries wrt Login Issues pl contact MISO Help Desk 39747</a>
								<p>05-Jun-2024</p>
								</div>
								</div>
							</li>
							<li class="info-list">
							<div class="date-with-list">
								<div class="custom-info-calendar-date">
								<span class="custom-info-calendar-main">23 April</span> 
								</div>
								<div class="list-content">
								<a><span>ORBAT</span>Arty, Mech Inf, Engrs, AAD, Armd and Inf Units are req to contact&nbsp;&nbsp;G1 SD4 / Col SD4 at 33459 / 33458</a>
								<p>05-Jun-2024</p>
								</div>
								</div>
							</li>
							<li class="info-list">
							<div class="date-with-list">
								<div class="custom-info-calendar-date">
								<span class="custom-info-calendar-main">23 April</span> 
								</div>
								<div class="list-content">
								<a><span>Transport (TMS) </span>In case of an erroneous entry, users have been extended rights to amend KM run and Veh Cls for A, B & C Vehs  </a>
								<p>05-Jun-2024</p>
								</div>
								</div>
							</li>
							<li class="info-list">
							<div class="date-with-list">
								<div class="custom-info-calendar-date">
								<span class="custom-info-calendar-main">23 April</span> 
								</div>
								<div class="list-content">
								<a><span>Wpn & Eqpt (MMS)</span>Units are requested to fill regn number of Wpn & Eqpt as &nbsp;&nbsp;&nbsp;&nbsp;it is a mandatory reqmt for transfer / HTO of stores </a>
								<p>05-Jun-2024</p>
								</div>
								</div>
							</li>
							<li class="info-list">
							<div class="date-with-list">
								<div class="custom-info-calendar-date">
								<span class="custom-info-calendar-main">23 April</span>
								</div>
								<div class="list-content">
								<a><span>User Registration</span> New User Registration form is avlb under Documents Tab
								</a>
								<p>05-Jun-2024</p>
								</div>
								</div>
							</li>
							</ul>
						</div>
					</div> -->
									<!-- <img alt="" src="login_file/swat.png"> -->
									<iframe frameborder=0 width="100%" height="600"
										src="https://misoanalytics.army.mil:8443/open-view/229935/9d99d35b709c73226b5edb1e7277fb08"></iframe>

								</div>
							</div>
						</div>
					
						<div class="event-box5">
							<!-- <div class="events-feed">

								<div class="container-fluid">
									<div id="filter-buttons">
										<div class="filter-button" data-filter="filter1">
											<b>Comd</b>
										</div>
										<div class="filter-button" data-filter="filter2">
											<b>Corps</b>
										</div>
										<div class="filter-button" data-filter="filter3">
											<b>Div</b>
										</div> 
									</div>

									<div id="filter1" class="slide-container">
										 <div class="event_img">
											<img src="login_file/img/command.png" class="img-fluid w-100"
												alt="banner">
										</div>
									</div>
									<div id="filter2" class="slide-container">
										<div class="event_img">
											<img src="login_file/img/corps.png" class="img-fluid w-100"
												alt="banner">
										</div> 
									</div>
									<div id="filter3" class="slide-container">
										 <div class="event_img">
											<img src="login_file/img/div.png" class="img-fluid w-100"
												alt="banner">
										</div>
									</div>
									 < <div class="home-title">
										<p class="">Best Updated Fmn : Jun 2024</p>
									</div> 
									<div class="home-title">
										<h3 class="news-feed-tite">QUERY ASSISTANCE</h3>
									</div> 
									
								<div class="helpdesk">
								<h5 class=""><i class="fa fa-phone-square" style="color:blue;"></i> MISO Helpdesk (Queries wrt User ID): <a href="#">39747 </a></h5> <br>
								<h5 class=""><i class="fa fa-phone-square" style="color:blue;"></i> Entitlement(UE)/Auth(Pers/Wpn/Tpt): <a href="#">39946</a></h5> <br>
								<h5 class=""><i class="fa fa-phone-square" style="color:blue;"></i> ORBAT: <a href="#">39946</a></h5> <br>
								<h5 class=""><i class="fa fa-phone-square" style="color:blue;"></i> Tpt (Holding(UH) of A,B,C Vehs & Animals): <a href="#">39854, 011-26146408</a></h5> <br>
								<h5 class=""><i class="fa fa-phone-square" style="color:blue;"></i> Holding(UH) of Wpn, Eqpt &amp; IT Assets: <a href="#">39538, 011-26146523</a></h5> <br>
								<h5 class=""><i class="fa fa-phone-square" style="color:blue;"></i> Pers (IAFF 3008 & 3009): <a href="#">39861, 011-26146414</a></h5>	<br>
								</div>
							<div class="home-title">
										<h6 class="news-feed-tite">IMP BULLETINS</h6>
									</div>
								</div>
							</div>  -->
							
							<div class="news-feed">
							<div class="home-title">
										<h6 class="news-feed-tite">IMP BULLETINS</h6>
									</div>
								<div class="news-feed-inner"
									style="padding: 15px; margin-top: -18px;">
								
									<div class="marquee_div">
										<div class="marquee">

											<ul>
											<li class="info-list">
													<div class="date-with-list">
														<div class="custom-info-calendar-date">
															<span class="custom-info-calendar-main">24 Feb</span> <span
																class="custom-info-calendar-sub"> 2025</span>
														</div>
														<div class="list-content">
															<a target="_blank" href="/doc/it_pers.pdf"><SPAN>new</SPAN>IAFIS is under devp at DGFP. FPMIS has been discontinued. 
														</div>
													</div>
												</li>
											
											<li class="info-list">
													<div class="date-with-list">
														<div class="custom-info-calendar-date">
															<span class="custom-info-calendar-main">18 Feb</span> <span
																class="custom-info-calendar-sub"> 2025</span>
														</div>
														<div class="list-content">
															<a target="_blank" href="/doc/it_pers.pdf"><SPAN>new</SPAN>ONLINE SANCTION FACILITY OF PERS IT ASSET MODULE IN MISO 
														</div>
													</div>
												</li>
											
												<li class="info-list">
													<div class="date-with-list">
														<div class="custom-info-calendar-date">
															<span class="custom-info-calendar-main">06 Sep</span> <span
																class="custom-info-calendar-sub"> 2024</span>
														</div>
														<div class="list-content">
															<a target="_blank" href="/doc/wpn_eqpt.pdf"><SPAN>new</SPAN>CO
																Cert Format to be Provided by Units for WPN & EQPT, in
																Case of sp docu not available</a>

														</div>
													</div>
												</li>
												<li class="info-list">
													<div class="date-with-list">
														<div class="custom-info-calendar-date">
															<span class="custom-info-calendar-main">23 April</span> <span
																class="custom-info-calendar-sub"> 2024</span>
														</div>
														<div class="list-content">
															<!-- 															<a><span>new</span>How to add Non Effective / Retired Offrs as Re-emp.															</a> -->
															<a target="_blank"
																href="/doc/non_effective_offrs_Re-emp.pdf"><SPAN>new</SPAN>How to
																add Non Effective / Retired Offrs as Re-emp.</a>

															<p>15-May-2024</p>
														</div>
													</div>
												</li>
												<li class="info-list">
													<div class="date-with-list">
														<div class="custom-info-calendar-date">
															<span class="custom-info-calendar-main">23 April</span> <span
																class="custom-info-calendar-sub"> 2024</span>
														</div>
														<div class="list-content">
															<!-- 															<a><span>new</span>Steps to be followed to make an Offr Retired / Non Effective. -->
															<a target="_blank" href="/doc/Making_NE_Retired.pdf"><span>new</span>Steps
																to be followed to make an Offr Retired / Non Effective.</a>

															<p>27-May-2024</p>
														</div>
													</div>
												</li>
												<li class="info-list">
													<div class="date-with-list">
														<div class="custom-info-calendar-date">
															<span class="custom-info-calendar-main">23 April</span> <span
																class="custom-info-calendar-sub"> 2024</span>
														</div>
														<div class="list-content">
															<a target="_blank" href="/doc/Supernumerary_str.pdf"><SPAN>new</SPAN>Steps
																to followed to place an Offrs under Supernumerary Str.</a>

														</div>
													</div>
												</li>
												<li class="info-list">
													<div class="date-with-list">
														<div class="custom-info-calendar-date">
															<span class="custom-info-calendar-main">23 April</span> <span
																class="custom-info-calendar-sub"> 2024</span>
														</div>
														<div class="list-content">
															<a target="_blank" href="/doc/SPosting_in_Out.pdf"><span>new</span>Steps
																to be followed for Posting in / out of Offr</a>

														</div>
													</div>
												</li>

											</ul>
										</div>
									</div>

								</div>
							</div>
						</div>
<div class="event-box">
						<div class="event-box11">
							<div class="mis_ecosys">
								<div class="mis_title">
									<h4>MISO 4.0</h4>
								</div>

								<div class="login-div-box">

									<div class="login-box-main">
										<!-- 										<h3 class="login-box-title">Login</h3> -->
										<!-- <p class="login-inst">Please enter your credentials to login.</p> -->
										<p style="color: red;">
											<c:if test="${not empty error}">${error}</c:if>
										</p>
										<p style="c: or:red;">
											<c:if test="${not empty msg}">${msg}</c:if>
										</p>
										<div class="login-box-inner">


											<form role="form" name='loginForm' action="#" method='POST'
												id="myFormId">
												<div class="col-md-12">
													<div class="form-group">
														<label class="mb-0">Username</label> <input id="username"
															type='text' name='username'
															class="form-control disablecopypaste" maxlength="30"
															size="35" autocomplete="off" placeholder="Enter Username">
													</div>
												</div>

												<div class="col-md-12">
													<div class="form-group">
														<label class="mb-0"> Password </label> <input
															id="password" type='password' name='password'
															class="form-control disablecopypaste" maxlength="28"
															size="35"
															pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%!^\\&_.~*]).{8,28}$"
															autocomplete="off" placeholder="Enter Password" />
													</div>
												</div>

												<div class="col-md-12">
													<div class="row m-0">
														<div class="col-md-6 col-6 p-0">
															<div class="form-group">
																<label class="mb-0"> Captcha </label> <input type='text'
																	class="form-control disablecopypaste" size="35"
																	id="txtInput" name="txtInput"
																	placeholder="Enter Captcha" autocomplete="off" />
															</div>
														</div>
														<div class="col-md-6 col-6 center-center">
															<div class="form-group">

																<div class="input-group">
																	<canvas id="canvasCapcha" width="100%" height="45%"
																		class="form-control disablecopypaste" readonly></canvas>
																	<span class="input-group-btn align-self-center">
																		<button class="btn btn-primary btn-sm" id="btnrefresh"
																			tabindex="-1" type="button">
																			<img alt="Refersh" src="login_file/img/referesh.ico">
																		</button>
																	</span>
																</div>

															</div>
														</div>
													</div>
												</div>

												<div class="col-md-12">
													<button type="submit" class="btn btn-submit"
														onclick="return validation();">Login</button>
												</div>
											</form>

										</div>

									</div>

								</div>
								</div>
							</div>
								
								<!-- <div class="col-md-12 mt-2 visitor_div">

									<img alt="" style="height: 140%" src="login_file/pedd.png">

									<div class="visitor-count">

													<p class="visitor-count-title">Visitor Count</p>

													<div class="row mx-1px mb-5px">

														<div class="col-4">
															<div class="count-main">
																<p class="count">Daily</p>
																<p>${todayslogin}</p>
															</div>
														</div>

														<div class="col-4">
															<div class="count-main">
																<p class="count">Monthly</p>
																<p>${montlyLogin}</p>
															</div>
														</div>

														<div class="col-4">
															<div class="count-main">
																<p class="count">Yearly</p>
																<p>${yearlyLogin}</p>
															</div>
														</div>

													</div>

												</div>

								</div> -->
								   <div class="event-box55">
									<div > 
										<h4 class="news-feed-tite" style="">QUERY ASSISTANCE</h4>
									</div>
								<div class="helpdesk" style="margin: 3px 0 0 0;">
								<i class="fa fa-phone-square" style="color:#00b050; margin: 0 0 0 3px;"></i>  <b>User ID / Login related issues: <span style="color:blue;">39747</span></b> <br>
								<i class="fa fa-phone-square" style="color:#00b050; margin: 0 0 0 3px;"></i> <b> Orbat: <span style="color:blue;">39946</span></b><br>
								<i class="fa fa-phone-square" style="color:#00b050; margin: 0 0 0 3px;"></i> <b>Entitlement (Pers/Wpn/Tpt):  <span style="color:blue;">39946</span></b><br>
								<i class="fa fa-phone-square" style="color:#00b050; margin: 0 0 0 3px;"></i> <b> Holding (A,B,C Vehs & Animals): <span style="color:blue;">39854, 011-26146408</span></b><br>
								<i class="fa fa-phone-square" style="color:#00b050; margin: 0 0 0 3px;"></i> <b>Holding (Wpn, Eqpt & IT ): <span style="color:blue;">39538, 011-26146523</span></b><br>
								<i class="fa fa-phone-square" style="color:#00b050; margin: 0 0 0 3px;"></i> <b>Pers (IAFF 3008 & 3009): <span style="color:blue;">39861, 011-26146414</span></b>
								</div>
								</div>
								 
</div>
							</div>
						</div>
					
	
	<!-- <div class="container-fluid order-3">
	<div class="mainbox_head">
		<h4> MISO ECOSYSTEM </h4>
	</div>
	<div class="main-box">
						<div class="app-box">
							<div class="flip-card" tabindex="0">
								<div class="flip-card-inner">
									<div class="flip-card-front wpmp1">
										<h3>ASAAN</h3>
									</div>
									<div class="flip-card-back wpmp1">
										<a href="" target="_blank">
											<h3>Lorem Ipsum is simply dummy text of the printing and typesetting industry.</h3>
											<button class="flip-box-button">Learn More</button>
										</a>
									</div>
								</div>
							</div>
						</div>
						
						<div class="app-box">
							<div class="flip-card" tabindex="0">
								<div class="flip-card-inner">
									<div class="flip-card-front wpmp2">
										<h3>DISHA</h3>
									</div>
									<div class="flip-card-back wpmp2">
										<a href="" target="_blank">
											<h3>Lorem Ipsum is simply dummy text of the printing and typesetting industry.</h3>
											<button class="flip-box-button">Learn More</button>
										</a>
									</div>
								</div>
							</div>
						</div>
						
						<div class="app-box">
							<div class="flip-card" tabindex="0">
								<div class="flip-card-inner">
									<div class="flip-card-front wpmp3">
										<h3>SATARK</h3>
									</div>
									<div class="flip-card-back wpmp3">
										<a href="" target="_blank">
											<h3>Lorem Ipsum is simply dummy text of the printing and typesetting industry.</h3>
											<button class="flip-box-button">Learn More</button>
										</a>
									</div>
								</div>
							</div>
						</div>
						
						<div class="app-box">
							<div class="flip-card" tabindex="0">
								<div class="flip-card-inner">
									<div class="flip-card-front wpmp4">
										<h3>GAPS</h3>
									</div>
									<div class="flip-card-back wpmp4">
										<a href="" target="_blank">
											<h3>Lorem Ipsum is simply dummy text of the printing and typesetting industry.</h3>
											<button class="flip-box-button">Learn More</button>
										</a>
									</div>
								</div>
							</div>
						</div>
						
						<div class="app-box">
							<div class="flip-card" tabindex="0">
								<div class="flip-card-inner">
									<div class="flip-card-front wpmp5">
										<h3>RAMS</h3>
									</div>
									<div class="flip-card-back wpmp5">
										<a href="" target="_blank">
											<h3>Lorem Ipsum is simply dummy text of the printing and typesetting industry.</h3>
											<button class="flip-box-button">Learn More</button>
										</a>
									</div>
								</div>
							</div>
						</div>
						
						<div class="app-box">
							<div class="flip-card" tabindex="0">
								<div class="flip-card-inner">
									<div class="flip-card-front wpmp6">
										<h3>DGOL</h3>
									</div>
									<div class="flip-card-back wpmp6">
										<a href="" target="_blank">
											<h3>Lorem Ipsum is simply dummy text of the printing and typesetting industry.</h3>
											<button class="flip-box-button">Learn More</button>
										</a>
									</div>
								</div>
							</div>
						</div>
						
						</div>
						</div> -->
				
	  		</div>
			<%-- <div class="middle_content" id="login">
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
										<!-- <div class="tooltip">
											<a>Reset Password</a>
											<span class="tooltiptext">Please click <a target="_blanck" href="/doc/Request_for_New_Password.pdf" >here</a> to download the reset password form and send it to MISO.</span>
										</div> -->
									</div>
									<input type="hidden" id="csrfIdSet" name="" value="" />
								</form>
							</div>
						</div>
					</div>
				</div>
			</div> --%>
			
			
			
			
		<div class="middle_content" align="center"  id="about_us" style="display:none;">
		<div class="">
			<div class="card card_abt">
				<div class="col-12">
						
						<div class="card-body">
							<div class="row">
								<div class="col-md-4 col-sm-6 col-12">
									<img src="login_file/img/event2.jpg" class="img_abt">
								</div>
								<div class="col-md-8 col-sm-6 col-12">
									<div class="abt_head">
										<h4 class="about_heading">About Us</h4>
									</div>
									<div class="cardbody_scr">
										<p class="about_content">Management Information Systems Organization (MISO) is the nodal agency to maintain the centralized database in respect of personnel, equipment, vehicles and health in respect of Indian Army (IA) for providing management related information to IHQ of MoD (Army) and other agencies. Data pertaining to authorization and holding of manpower, transport and equipment is received from a large number of sources e.g. all types of army units/ depots record offices, directorates, branches and the Ministry of Defence. Data is compiled and numerous reports are generated to provide information to various stakeholders.</p>
									</div>
								</div>
							</div>
					</div>
				</div>
				
				<div class="col-12">
				
					<div class="abt_head">
						<h4 class="about_heading">OUR AIM</h4>
					</div>
					<div class="card-body cardbody_scr">
						<p class="about_content">MISO aims at providing information to help in taking decisions on issues of planning, procurement and provisioning as well as on unit move, issue of vehicles and equipment to units.</p>
					</div>
				
				</div>
				<div class="col-12">
				
						<div class="abt_head">
							<h4 class="about_heading">ACHIEVEMENTS</h4>
						</div>
					
						<div class="card-body cardbody_scr">
							<div class="about_content">
								<p>MISO captures data pertaining to organizational structure (ORBAT), authorization & holding of manpower, weapons/equipment and vehicles in a centralized database hosted on Army Data Network (ADN). The major milestones achieved due to are as below.</p>
								<ul><li> Updation of data of vehicles, equipment and manpower held by IA units (over 10,000) through direct access to the application on AND. The application has resulted in management of data of over 10,000 units / establishment, 339 formation HQs, 95 Ordinance Depots, 50 Record Offices and other miscellaneous units. Application is used for generation of BA Nos, SUS Nos and issue of vehicles.</li>
								<li> The application maintains data of over 49,000 officers, 13 lakh JCOs/OR, 2.2 lakh ‘B’ vehicles, 10,000 ‘A’ vehicles, 2500 weapons / equipment and admission / discharge / OPD data of all military hospitals. The entire process leads to provisioning of data for planning and decision making on revenue procurement by MGO of approx RS 10,000 Cr per year and also for recruitment and management of personnel.</li>
								<li> The application also facilitates addressing various Parliamentary Queries (PQs) and RTI queries as received from time to time by querying the database.</li></ul>
							</div>
						</div>
				
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
	<%-- <div align="center"  class="middle_content team_miso" id="team_miso" style="display:none;">
		
		<section class="team_section">
        <div class="itembox">
            <div class="cont_head">
				<h4 class="about_heading">Team Miso</h4>
			</div>
            <div class="team_div">
                <div class="column">
                    <div class="team-sec">
                        <div class="team-img">
                            <img src="login_file/img/tm1.jpg" alt="Team Image">
                        </div>
                        <div class="team-content">
                            <h2>Viral Patel</h2>
                            <h3>Poject Manager</h3>
                        </div>
                    </div>
                </div>                            
                <div class="column">
                    <div class="team-sec">
                        <div class="team-img">
                            <img src="login_file/img/tm1.jpg" alt="Team Image">
                        </div>
                        <div class="team-content">
                            <h2>Harsh Jain</h2>
                            <h3>Developer</h3>
                        </div>
                    </div>
                </div>
                <div class="column">
                    <div class="team-sec">
                        <div class="team-img">
                            <img src="login_file/img/tm1.jpg" alt="Team Image">
                        </div>
                        <div class="team-content">
                            <h2>Raja Sinh</h2>
                            <h3>Designer</h3>
                        </div>
                    </div>
                </div>
                <div class="column">
                    <div class="team-sec">
                        <div class="team-img">
                            <img src="login_file/img/tm1.jpg" alt="Team Image">
                        </div>
                        <div class="team-content">
                            <h2>Ruchit Patel</h2>
                            <h3>Developer</h3>
                        </div>
                    </div>
                </div>                           
                <div class="column">
                    <div class="team-sec">
                        <div class="team-img">
                            <img src="login_file/img/tm1.jpg" alt="Team Image">
                        </div>
                        <div class="team-content">
                            <h2>Parth Rajput</h2>
                            <h3>Developer</h3>
                        </div>
                    </div>
                </div>
                <div class="column">
                    <div class="team-sec">
                        <div class="team-img">
                            <img src="login_file/img/tm1.jpg" alt="Team Image">
                        </div>
                        <div class="team-content">
                            <h2>Rushi Raval</h2>
                            <h3>Designer</h3>
                        </div>
                    </div>
                </div>
            </div>
        </div>

	</section>
		
	</div> --%>
	<div class="middle_content" align="center" id="contact_us" style="display:none;">
		<!-- <div style="background-color:#672a2a;color:#fff;font-size:20px;text-transform:uppercase;">
			<b>Contact Us</b>
		</div>-->
		
		<section class="blog-grid section-padding position-re bg-img" data-background="img/patrn1.png" data-scroll-index="6" style="background-image: url(&quot;img/patrn1.png&quot;);">
        <div class="container-fluid">
            <div class="row justify-content-center mt-2">
                <div class="col-lg-8 col-md-10 col-12">                    
                    <div class="cont_head">
						<h4 class="about_heading">Contact Us</h4>
					</div>                
                </div>
            </div>

            <div class="row">
                <div class="col-lg-3 col-md-4 col-sm-6 col-12">
                    <div class="item bg-img">
                       
                            <div class="item-box">
                            <span class="fa fa-phone-square"></span></div>
                            <div class="cont">
                            <h3 class="wow color-font">COL MISO, OIC AUTOCELL</h3>
                            <div class="info-text">
                                <h5><a href="#">39951</a></h5>                           
                            </div>
                        </div>
                        
                    </div>
                </div>
                <div class="col-lg-3 col-md-4 col-sm-6 col-12">
                    <div class="item bg-img">
                       
                            <div class="item-box">
                            <span class="fa fa-phone-square"></span></div>
                            <div class="cont">
                            <h3 class="color-font"> MISO Helpdesk
                            </h3>
                            <div class="info-text">
                                <h5><a href="#">39747</a></h5>                            
                            </div>
                            </div>
                            </div>
                            </div>
                    <div class="col-lg-3 col-md-4 col-sm-6 col-12">
                                <div class="item bg-img">
                                    <div class="item-box">
                                        <span class="fa fa-phone-square"></span></div>
                                        <div class="cont">
                            <h3 class="color-font"> GSO1 CUE &amp; ORBAT
                            </h3>
                            <div class="info-text">
                                <h5><a href="#">39946</a></h5>                            
                            </div>
                            </div></div></div>
                            <div class="col-lg-3 col-md-4 col-sm-6 col-12">
                                <div class="item bg-img">
                            <div class="item-box">
                                <span class="fa fa-phone-square"></span></div>
                                <div class="cont">
                            <h3 class="color-font"> GSO1 TPT SUB GP
                            </h3>
                            <div class="info-text">
                                <h5><a href="#">39854, 26146453</a></h5>                            
                            </div> 
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-md-4 col-sm-6 col-12">
                    <div class="item bg-img">
                       
                            <div class="item-box">
                            <span class="fa fa-phone-square"></span></div>
                            <div class="cont">
                            <h3 class="color-font"> GSO1 WPN &amp; EQPT SUB GP
                            </h3>
                            <div class="info-text">
                                <h5><a href="#">39538, 26146523</a></h5>                            
                            </div>
                            </div>
                            </div>
                            </div>
                     <div class="col-lg-3 col-md-4 col-sm-6 col-12">
                                <div class="item bg-img">
                                    <div class="item-box">
                                        <span class="fa fa-phone-square"></span></div>
                                        <div class="cont">
                            
                            <h3 class="color-font"> GSO1 PERSONNEL SUB GP
                            </h3>
                            <div class="info-text">
                                <h5><a href="#">39861, 26146414</a></h5>                            
                            </div> 
                            </div></div></div>
                            <div class="col-lg-3 col-md-4 col-sm-6 col-12">
                                <div class="item bg-img">
                                    <div class="item-box">
                                        <span class="fa fa-phone-square"></span></div>
                                        <div class="cont">
                            
                            <h3 class="color-font"> GSO1 MISO DATA CENTRE
                            </h3>
                            <div class="info-text">
                                <h5><a href="#">39288, 26141286</a></h5>                            
                            </div>      
                        </div>
                    </div>
                </div>

               
            </div>
           <div class="row justify-content-center mt-2">
            <div class="col-lg-8">
            <div class="our-aim">
                <h6 class="sub-title main-color text-u">Please Note</h6>
                <ul class="note-list">
                <li>Office timings are 0900h-1300h and 1400h-1730h.</li>
				<li>Offices are closed on Saturday and Sundays.</li>
                </ul>
                </div>
            </div>
</div>
           
        </div>
    </section>
		
	</div>
</div> 


<!-- chatbot janki code -->
	<div class="mask" id="mask" style="display:none;">

		<!-- Main container -->
		<div class="container-fluidC" style="padding:0;">

			<!-- Chat list view -->
			<section class="list">

				<!-- Chat list header -->
				<header class="list__header" style="background-image: url('login_file/chatbot8.gif');">
					<span>Modules</span>
					<button id="closeForm" class="conversation__btn btn btn--close" title="Close chat">
							<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><line x1="18" y1="6" x2="6" y2="18"></line><line x1="6" y1="6" x2="18" y2="18"></line></svg>
						</button>
				</header>
 
				<!-- Chat list -->
				<div class="list__body" style="background-image: url('login_file/chatbot2.png');">
					<!-- Chat conversation -->
					<a id="chat1" class="list__chat">
						<div class="list__preview">
							<div id="chatsdiv" ><!-- med or normal -->
      						</div>
						</div>
					</a>
				</div>
			</section>

			<!-- Conversation view -->
			<section class="conversation">

				<!-- Conversation view header -->
				<header class="conversation__header" style="background-image: url('login_file/chatbot8.gif');">
					<button class="conversation__btn btn  btn--list" title="Conversation list" id="click_btn" onclick="menurefresh();">
						<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><line x1="8" y1="6" x2="21" y2="6"></line><line x1="8" y1="12" x2="21" y2="12"></line><line x1="8" y1="18" x2="21" y2="18"></line><line x1="3" y1="6" x2="3.01" y2="6"></line><line x1="3" y1="12" x2="3.01" y2="12"></line><line x1="3" y1="18" x2="3.01" y2="18"></line></svg>
					</button>
					<div class="conversation__info">
						<span class="conversation__status"></span>
						<span class="conversation__name"></span>
					</div>
					<div class="conversation__header-actions">
						<button id="closechat" class="conversation__btn btn btn--close" onclick="fnclosechat()" title="Close chat"">
							<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><line x1="18" y1="6" x2="6" y2="18"></line><line x1="6" y1="6" x2="18" y2="18"></line></svg>
						</button>
					</div>
				</header>

				<!-- Conversation view messages -->
				<input type= "hidden" id="user_type" name= "user_type"/>
				<input type= "hidden" id="sid" name= "sid"/>
				<input type= "hidden" id="p_id" name= "p_id"/>
				<input type= "hidden" id="AutotextHid" name= "AutotextHid"/>
				<input type= "hidden" id="qaid" name= "qaid" />
			 	 
				
				
			  <div class="conversation__body" id="conversation__body1" style="background-image: url('login_file/chatbot5.gif');background-size: contain; background-repeat: repeat-x;background-position: bottom; ">
				<div id="main_pg_opt">
			  
			 <a class="" href="login">Home </a>
			 <a class="" onclick="aboutus();">About Us</a>
 			 <a class="" onclick="contactus();">Contact us </a> 
								 
			</div>
				
				<label id="mb" style="display: block; background-image: url('login_file/chatbot4.gif');background-size: contain;" > <b> * Please Select One Module.. </b> </label>
			  <div class="list__preview" id="menumod">
							<div id="chatsdiv" ><!-- med or normal -->
      						</div>
						</div>
			  
			  
			  
			  <div class="chat-history">
			   <label id="ns" style="display: none; background-image: url('login_file/chatbot4.gif');background-size: contain;"  ><b> * Please Select any option..</b></label>
			    <div id="scrndiv"></div>
<!--       		<div id="ansdiv" style="display: none"> </div>  -->
      		    <div id="adiv" style="display: none"></div> 
      		    <div id="rdiv" style="display: none"></div>  
      			<div id="fdiv" style="display: none">
      			</div>  
      			<div id="ansdiv4" style="display: none"></div>  
     		 
     					<ul></ul>
				</div> 
				</div>
				
				<label class="chat-tag" id = "others"  onclick = "textopen();"  style="display: none">Others </label>
	  
			<!-- Conversation view footer -->
			<footer class="conversation__footer" style="background-image: url('login_file/chatbot8.gif');background-size: contain;">
			<div id ="msg_hid" style="display: block">
					<input type="text" class="conversation__write" id="message-to-send" name="message-to-send" placeholder="Type your text here"  autocomplete="off">
<!-- 					<i class="fa fa-paper-plane" aria-hidden="true" value="Send" id="sendBtn" onclick="send_bt();" ></i> -->
					<img value="Send" id="sendBtn" onclick="send_bt();" alt="send" src="login_file/sendicon.png" style="height:25px;">
					</div>
			</footer>

		<script id="message-response-template" type="text/x-handlebars-template">
			 <li class="conversation__bubble conversation__bubble--left">
 			<div class="conversation__text">  {{response}} 
 			<div class="conversation__text timetext">{{time}}</div>
 			</div>
 			</li>
		</script>
		
		<script id="message-template" type="text/x-handlebars-template">
			<li class="conversation__bubble conversation__bubble--right">         
			<div class="conversation__text"> {{messageOutput}}  
			<div class="conversation__text">{{time}}</div>
			</div>
			</li>
		</script>
   </section>

   </div>
</div>
	
	<button id="open-button" class="bottom-right" style="position: fixed; bottom: 60px; background-color: transparent; border: 20px;  right: 15px;width: initial;"  title='Help' >
	 <!--  <img style="border-radius:50%;height:120px;box-shadow: 5px 5px 10px #123285;animation: mymove 5s infinite;" alt="logo" src="login_file/chatbot9.gif" >  -->
<!-- 	<svg height="24" viewBox="0 0 24 24" width="24" xmlns="http://www.w3.org/2000/svg"><path d="M20 2H4c-1.1 0-2 .9-2 2v18l4-4h14c1.1 0 2-.9 2-2V4c0-1.1-.9-2-2-2z"></path><path d="M0 0h24v24H0z" fill="none"></path></svg> -->
	</button>  	

	<!-- CHATBOT JANKI END  -->

</div>


<!-- <div class="digital_india" style="position:fixed;bottom:20px;"><img src="login_file/digitalindia.png" class="img-fluid"></div> -->
<!-- <footer> -->
<!-- 	<span class="content1 left">Collab: BISAG-N, MeitY, GoI | All rights reserved - MISO. App vetted by Army Cyber Gp on 25 Aug 2020 & Content Vetted by MI-11 on 01 Sep 2020.</span> -->
<%-- 	<span class="content2 right">${server} Last Updated 22-03-2024 Version 2 Visitors ${visiter_count}</span> --%>
<!-- </footer> -->

<footer>

	<div class="row align-items-center p-2px">
				<div class="col-lg-1 col-md-2 col-sm-6 col-12"><p class="logo-footer">
			    	<a href="https://www.digitalindia.gov.in/" title="Go to https://www.digitalindia.gov.in/" target="_new"><img src="login_file/img/di_logo.png" class="flimg"></a>
			    </p></div>
			   <div class="col-lg-7 col-md-6 col-sm-6 col-12">
			    <p class="p3" align="center">
			    	<b class="terms-data">
			    	<a class="cursor-pointer" onclick="terms();" title="Terms &amp; Conditions">Terms &amp; Conditions</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
			    	<a class="cursor-pointer" onclick="disclaimer();" title="Disclaimer">Disclaimer</a></b>
			    	<b class="text-center color-white">All rights reserved - MISO. App vetted by Army Cyber Gp on 25 Aug 2020 & Content Vetted by MI-11 on 01 Sep 2020.</b></p>
<!-- 			    <p>This site is developed and maintained by <u class="bisag_color"> BISAG-N </u>, MeitY, GOI.</p> -->
				</div>
				<div class="col-lg-3 col-md-2 col-sm-6 col-12 vertical-middle">
				 	<p class="p3" align="center">
			    		<b><span class="f-right color-white">${server} Last Updated 03-05-2024 Version 2 <%-- Visitors ${visiter_count} --%></span></b>
			    	</p> 
			    	
			    </div>
			    <div class="col-lg-1 col-md-2 col-sm-6 col-12">
			    	<div class="bisag_footer_logo"> <img src="login_file/img/bisag.png" class=""></div>
			    </div>
	</div>
	
</footer>

</body>


<script>
function fnclosechat() {
	window.location.reload();
} 

function menurefresh(){
	 
	 //var div=$('#conversation__body1').html();
	 //document.getElementById("conversation__body1").innerHTML = "";
	 // document.getElementById("fdiv").innerHTML = "";
 
	  $("#conversation__body1").load(location.href+" #conversation__body1>*","");
	 // Array();
	  j = [];
	 
}
function openForm() {
  document.getElementById("myForm").style.display = "block";
}
function textopen(){
	 $("#msg_hid").show();
	 $("#others").hide();
}
 
</script>
<script>
$(document).ready(function(){
	$("div#myForm").hide();
	$("#chatbot").hide();
	//$("#ansdiv").hide();
	 
	var x;
	var key = "${_csrf.parameterName}";
  	var value = "${_csrf.token}";
  	//normal or medical user
	/*  $.post("GetUserType?"+key+"="+value,function(j) {
		 	if( j.length > 0 ){
				  for (var i=1 ; i<=j.length ; i++){
						var x = i-1;
			 			var valx = j[x].user_type;
			 			var  valy = j[x].id;
			 			chat_formopen(i,valx,valy);
			 		}   
			} 
	  }).fail(function(xhr, textStatus, errorThrown) { alert("fail to fetch TER")
   	});  */ 
});


var key = "${_csrf.parameterName}";
	var value = "${_csrf.token}";


/*----------- TER Form //Module name (leave)--------*/
function chat_formopen(t,val,valy) {
 	
	 $("#others").hide();
	 $("#msg_hid").hide();
	$("input#ter_count_img").val(t);
	$("#note").show();
	$("div#chatsdiv").append(
 			'<button type="button" class="chat-tag btn-3" id="chatsetid'+t+'" value="'+valy+'" onclick = "link_fetch(this);" >'+val+'</button>'
		);
	
}

 
var qaid;
//  <!--Question shows  -->
function link_fetch(obj){
	 $("#menumod").hide();
	 //document.getElementById("scrndiv").innerHTML = "";
	
	 var user_type = obj.value;
	   
	$("#user_type").val(user_type);
	//$("#others").hide();
	//$("#msg_hid").hide();
	 
 	var key = "${_csrf.parameterName}";
  	var value = "${_csrf.token}";
  	$("#ns").show();
	$("#mb").hide();
	
 
	$.post("GetQueryType?"+key+"="+value,{ user_type : user_type }).done(function(j) {
		 
 			 if( j.length > 0 ){
				$("div#scrndiv").show();
				//$("div#ansdiv").show();
				$("#mb").hide();
				document.getElementById("scrndiv").innerHTML = "";
			 
				for (var i=1 ; i<=j.length ; i++){
						var z = i-1;
					 	var sid = j[z].id;
			 			var user_type = j[z].user_type;
			 			var question = j[z].question;
			 			$("input#sid").val(sid);
			 			scrn_formopen(i,user_type,question,sid);
			 			
			 		}
				$("div#scrndiv").append(
						'<label class="chat-tag" id = "scrnid_ot" value="ot" onclick = "ot_formopen();">Others</label>'	
				); 
				
			} 
	  }).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch TER")
   	}); 
}
function ot_formopen() {
 	 
	document.getElementById("ansdiv4").innerHTML = "";
	$("#scrndiv").hide();
	$("#ansdiv4").show();
	 $("div#ansdiv4").append(
				'<label class="chat-tag" id = "" value="" onclick = "">1.Please contact MISO helpdesk for further assistance avail at 39747,39288.</br>2.The chatbot facility for questions beyond login queries will be answered only after you login into the application.<br/>Have a Great day! </label> </br>'
				);
	 
	 
}
 
function scrn_formopen(t,user_type,question,sid) {
	$("#others").hide(); 
	$("#ansdiv4").hide();
	 $("#ns").show();
	 $("#note").hide();
	 $("#others").show();
	 $("#msg_hid").hide();
	 $("#adiv").hide();
	 $("#fdiv").hide();
	 $("#scrndiv").show();
	 $("div#scrndiv").append(
	'<label class="chat-tag" id = "scrnid'+t+'" value="'+sid+'" onclick = "main_queopen('+sid+');">'+question+' </label>'	
		);
} 
 
 function main_queopen(sid){
		var key = "${_csrf.parameterName}";
     	var value = "${_csrf.token}";
     	
	 var p_id = sid+"00";
	 
	 	$.post("GetmainQue?"+key+"="+value,{ p_id : p_id }).done(function(j) {
				 
		 	if( j.length > 0 ){
				    for (var i=1 ; i<=j.length ; i++){
						var x = i-1;
			 			var id = j[x].id;
			 			var  parent_id = j[x].parent_id;
			 			var  child_id = j[x].child_id;
			 			var  question = j[x].question;
			 			first_formopen(x,id,parent_id,child_id,question,j[0].yes,j[0].no);
			 		}     
			} 
	  }).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch TER")
   			}); 
		
 }
 
 
 function first_formopen(x,id,parent_id,child_id,question) {
	 $("#ansdiv4").hide();
     $("#ns").show();
 	 $("#note").hide();
 	 $("#others").hide();
 	 $("#msg_hid").hide();
 	 $("#scrndiv").hide();
 	 $("#adiv").show();
 	 
	      var str = question;
 		  var result = str.link(question);
 		  
 		
 		  
 		  
  		  var pattern = new RegExp('^(https?:\\/\\/)?'+ // protocol
 			    '((([a-z\\d]([a-z\\d-]*[a-z\\d])*)\\.)+[a-z]{2,}|'+ // domain name
 			    '((\\d{1,3}\\.){3}\\d{1,3}))'+ // OR ip (v4) address
 			    '(\\:\\d+)?(\\/[-a-z\\d%_.~+]*)*'+ // port and path
 			    '(\\?[;&a-z\\d%_.~+=-]*)?'+ // query string
 			    '(\\#[-a-z\\d_]*)?$','i'); // fragment locator
 		        //alert("---"+pattern.test(str));
 		 	  // return !!pattern.test(str);
 		 	if(pattern.test(str) == true){
  		 		$("div#adiv").append(
 				'<label class="chat-tag" id = "" value="" onclick = "">'+result+' </label> </br>'
  				); 
 		 	}	
		 	else{
 		 		$("div#adiv").append(
 		 			'<label class="chat-tag" id = "" value="" onclick = "">'+question+' </label> </br>' 
					//'<label class="chat-tag" id = "" value="" onclick = "">'+question+' </label> </br>'
			    ); 
		 	}
		  
		  Sec_formopen(child_id)
	}
 
 
 function Sec_formopen(parent_id){
	 var key = "${_csrf.parameterName}";
  	var value = "${_csrf.token}";
	 var p_id = parent_id;
	 	$.post("GetmainQue?"+key+"="+value,{ p_id : p_id }).done(function(j) {
		 	if( j.length > 0 ){
				    for (var i=1 ; i<=j.length ; i++){
						var x = i-1;
			 			var id = j[x].id;
			 			var  parent_id = j[x].parent_id;
			 			var  child_id = j[x].child_id;
			 			var  question = j[x].question;
			 			  
			 			if(question != "" && question != " "){
			 				Y_formopen(x,id,parent_id,child_id,question);
			 			 }
			 		}     
			} 
	  }).fail(function(xhr, textStatus, errorThrown) {alert("")
   			}); 
		
 }

  var j = new Array();
  var y = new Array();

 function Y_formopen(x,id,parent_id,child_id,question){
	 
	 
	  j.push(child_id)
 	  var t1= $("input#qaid").val(j);
	  var cars = document.getElementById("qaid").innerHTML 
	   
	  
	  $("#ansdiv4").hide();
	  $("#ns").show();
	  $("#note").hide();
	  $("#others").hide();
	  $("#msg_hid").hide();
	  $("#scrndiv").hide();
	  $("#adiv").show();
	 
	 	 if(j.length >= 2){
	 		for(var a = 0;a<(j.length - 2);a++){
	 		 	document.getElementById("yes_noRadio"+j[a]).disabled = true;	 		
	 		 }
	 	 }
	 
	 	 
	 	 
	 	 
	 	  var child_id = child_id;
          if((child_id % 2) == 1){
                 var yid = child_id;
                 
                 $(".chat-history").append(
                '<div id="yes_noDiv'+yid+'" class="yes_no"><input type="radio" id = "yes_noRadio'+yid+'" name="radio2'+parent_id+'" value="'+yid+'" class="chat-tag" onclick = "m_queopen('+yid+','+child_id+');">Yes</input></div>'
                  );
          }
         else{
                var nid = child_id;
                
                //$("yes_noDiv'+nid+'").empty();
                $(".chat-history").append(
                        '<div id="yes_noDiv'+nid+'" class="yes_no"><input type="radio" id="yes_noRadio'+nid+'" name="radio2'+parent_id+'" value="'+nid+'" class="chat-tag" onclick = "m_queopen('+nid+','+child_id+');" >No</input></div>'
                );
               } 
          
         
	 }
 
 
 function m_queopen(sid,child_id){
     y.push(sid);
 	var  count = y.length;
 	var x = y[count-2]; 
	 
		if(x!=sid){
			 var p_id = sid;
		 	 	$.post("GetchildIDQue?"+key+"="+value,{ p_id : p_id }).done(function(j) {
				 	if( j.length > 0 ){
						    for (var i=1 ; i<=j.length ; i++){
								var x = i-1;
					 			var id = j[x].id;
					 			var  parent_id = j[x].parent_id;
					 			var  child_id = j[x].child_id;
					 			var  question = j[x].question;
					 			l_formopen(x,id,parent_id,child_id,question);
					 		}     
					} 
			  }).fail(function(xhr, textStatus, errorThrown) {
		   			}); 
			 
		 
		 
	}
	 	 	
		
 }
 
 function l_formopen(x,id,parent_id,child_id,question) {
	    $("#ns").show();
	 	$("#note").hide();
	 	 $("#others").hide();
	 	 $("#msg_hid").hide();
	 	$("#scrndiv").hide();
	 	$("#adiv").show();
		$("#rdiv").hide();
	 	$("#fdiv").hide();
	 	  var str = question;
	 	  
 		  var pattern = new RegExp('^(https?:\\/\\/)?'+ // protocol
			    '((([a-z\\d]([a-z\\d-]*[a-z\\d])*)\\.)+[a-z]{2,}|'+ // domain name
			    '((\\d{1,3}\\.){3}\\d{1,3}))'+ // OR ip (v4) address
			    '(\\:\\d+)?(\\/[-a-z\\d%_.~+]*)*'+ // port and path
			    '(\\?[;&a-z\\d%_.~+=-]*)?'+ // query string
			    '(\\#[-a-z\\d_]*)?$','i'); // fragment locator
		       
		 	if(pattern.test(str) == true){
		 		 if((child_id % 2) == 1){
		  		  	var yid = child_id;
		 		  	var n = yid+1;
		 		  	
					$(".chat-history").append(
					 	'<div id="fdiv'+yid+'" class="chat-tag qDiv"><label id="child_id" name="child_id" value="child_id" class="chat-tag" ><a href="'+question+'" target="_blank">'+question+'</a></label></div>' );
		 		     var yidnext = child_id+10;
		   		   
		 				   $("div#fdiv"+n).hide();
		 		 		   $("div#yesDiv"+(yidnext+2)).hide();
		 		 		   $("div#noDiv"+(yidnext+3)).hide();
		 	 	}  
		 	 	else{ 
		 	 		 var nid = child_id;
		 	 		 var y = nid-1;
		 	 		 var nidnext = child_id+10;
		 		  	  
		 	 		 $(".chat-history").append(
		 			 	'<div id="fdiv'+nid+'" class="chat-tag qDiv" ><label class="chat-tag" id = "child_id" value="child_id" ><a href="'+question+'" target="_blank">'+question+'</a></label> </br></div>'
		 			);
		 	 		  $("div#fdiv"+y).hide();
		 	 		  $("div#yesDiv"+(nidnext-1)).hide();
		 	 		  $("div#noDiv"+(nidnext)).hide();  
		  
		 	 	}
		 	}	
		 	else{
		 		 if((child_id % 2) == 1){
		  		  	var yid = child_id;
		 		  	var n = yid+1;
		 		  	
		 		  	
		 		    $(".chat-history").append(
		 					  '<div id="fdiv'+yid+'" class="chat-tag qDiv" ><label class="chat-tag" id = "child_id" value="child_id" onclick = "">'+question+' </label> </br></div>'
		 					  
		 	 		 ); 
		 	 		
		 		     var yidnext = child_id+10;
		   		   
		 				   $("div#fdiv"+n).hide();
		 		 		   $("div#yesDiv"+(yidnext+2)).hide();
		 		 		   $("div#noDiv"+(yidnext+3)).hide();
		 	 	}  
		 	 	else{ 
		 	 		 var nid = child_id;
		 	 		 var y = nid-1;
		 	 		 var nidnext = child_id+10;
		 		  	  
		 	 		 	 
		 	 		 $(".chat-history").append(
		 			 	'<div id="fdiv'+nid+'" class="chat-tag qDiv" ><label class="chat-tag" id = "child_id" value="child_id" onclick = "">'+question+' </label> </br></div>'
		 			);
		 	 		  $("div#fdiv"+y).hide();
		 	 		  $("div#yesDiv"+(nidnext-1)).hide();
		 	 		  $("div#noDiv"+(nidnext)).hide();  
		  
		 	 	}	
		 	}
		   
	 	 
	 	 	 Sec_formopen(child_id);
	}
 

//////////////////////////////
function send_bt() {
 
	document.getElementById("scrndiv").innerHTML = "";
	 
	var AutotextHid = $("#AutotextHid").val();
 	document.getElementById("scrndiv").innerHTML = "";
 	$("div#ns").show();
 	 
 	var user_type =  $("#user_type").val();
	var key = "${_csrf.parameterName}";
  	var value = "${_csrf.token}";
  	if (AutotextHid != ""){
  		$.post("GetAutoRes?"+key+"="+value,{ AutotextHid : AutotextHid}).done(function(j) {//change
  			var answer = j[0].answer;
  			var question = j[0].question;
  		render1(answer,question);
  	 
  		}).fail(function(xhr, textStatus, errorThrown) {alert("Please select from auto suggetion")
  	   	});
  	}
  	else{
 		var p_id =  $("#user_type").val();
 		var message_to_send= $("#message-to-send").val();
  		var question = $("#message-to-send").val();
 		var answer = "We will revert you soon..Your question has been noted..Please select from auto suggetion";
  		 render1(answer,question);
 		 
 		
		var key = "${_csrf.parameterName}";
	  	var value = "${_csrf.token}";
	 
	     $.post('saveQuestionAction?' + key + "=" + value, {p_id:p_id,message_to_send:message_to_send }, function(data){
	    	 
	            if(parseInt(data)>0){
	        	
	        	  // alert("Data Saved SuccesFully")
	          } else{}
	        	  //alert(data);
	 	  		}).fail(function(xhr, textStatus, errorThrown) {
	 	  			//alert("fail to fetch")
	  		}); 
  		
  	}
  	
  	$("#message-to-send").val("");
}


function render1(answer,question) {

	    $chatHistory = $('.chat-history');
	    $button = $('#sendBtn');
	    $textarea = $('#message-to-send');
	    $chatHistoryList = $chatHistory.find('ul');
	    $chatHistory.scrollTop($chatHistory[0]);
	 
	  var templateResponse2 = Handlebars.compile($("#message-template").html());
	 
	  var templateResponse = Handlebars.compile($("#message-response-template").html());
	  var contextResponse = {
	        response: answer,
	        time: getCurrentTime(),
	    };
	    var contextResponse2 = {
	    		messageOutput: question,
		        time: getCurrentTime(),
		    };
	     setTimeout(function () {
	    	 
	        $chatHistoryList.append(templateResponse2(contextResponse2));
	  		$chatHistory.scrollTop($chatHistory[0]);
	    }.bind(this), 1000); 
	    setTimeout(function () {
	        $chatHistoryList.append(templateResponse(contextResponse));
	  		$chatHistory.scrollTop($chatHistory[0]);
	    }.bind(this), 1000);
	   
}
</script>

<!--  <script src="login_file/custom.js"></script> -->
<script>
//Autocomplete
 $(function() {
 
    var key = "${_csrf.parameterName}";
    var value = "${_csrf.token}";
              var myResponse = []; 
              var myResponse1 = []; 
          
$("input#message-to-send").keypress(function() {
	var question = this.value;
	var p_id = $("#user_type").val();
	var susNoAuto=$("#message-to-send") ;
	 
  susNoAuto.autocomplete({
		source: function(request, response) {
			$.ajax({
				type: 'POST',
				url: "getQueList?" + key + "=" + value,
				data: {question: question,p_id:p_id},
			 	success: function(data) {
			 		var p_id = data[0][0];
			 		var question = data[0][1];
			 		myResponse = []; 
			 		myResponse1 = []; 
					myResponse.push(question);
					myResponse1.push(p_id);
					response(myResponse); 
			 	}
			});
		},
                minLength: 1,
                autoFill: true,
                change: function(event, ui) {
                	if (ui.item) {                    	
//                          return true;                        
                 	} 
                    else{
                   	 $("#AutotextHid").val("");
                    }
             },
            select: function( event, ui ) {
     		$(this).val(ui.item.value);
   			var aid= myResponse1;
   			$("#AutotextHid").val(aid);
   		  } 
          
	});
});
 });
 
 $(document).ready(function(){
     var currentIndex = 0;
     var filters = $('.filter-button');
     var slideContainers = $('.slide-container');
     var interval = 7000; // 7 seconds

     function showSlide(index) {
         filters.removeClass('active');
         $(filters[index]).addClass('active');
         slideContainers.hide();
         $('#' + $(filters[index]).data('filter')).show();
     }

     // Initial display
     showSlide(currentIndex);

     // Auto slider
     setInterval(function(){
         currentIndex = (currentIndex + 1) % filters.length;
         showSlide(currentIndex);
     }, interval);

     // Manual filter button click
     filters.on('click', function(){
         currentIndex = filters.index(this);
         showSlide(currentIndex);
     });
 });

</script>

</html>
