<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%
	HttpSession sess = request.getSession(false);
	if (sess.getAttribute("username") == null) {
		sess.invalidate();
		response.sendRedirect("/login"); return; 
	} 
	
	String user_agentWithIp = String.valueOf(sess.getAttribute("user_agentWithIp"));
	String userAgent = request.getHeader("User-Agent");
    String ip = "";

	if (request != null) {
        ip = request.getHeader("X-FORWARDED-FOR");
        if (ip == null || "".equals(ip)) {
            ip = request.getRemoteAddr();
        }
    }
	String currentuser_agentWithIp = userAgent+"_"+ip;
	currentuser_agentWithIp = currentuser_agentWithIp.replace("&#40;","(");
	currentuser_agentWithIp = currentuser_agentWithIp.replace("&#41;",")");
	
	//out.print(currentuser_agentWithIp+"<=c = s=>"+user_agentWithIp);
	if(!user_agentWithIp.equals(currentuser_agentWithIp)){
		sess.invalidate();
		response.sendRedirect("/login"); return; 
	}
%> 
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="shortcut icon" href="login_file/dgis-logo_favicon.jpg" >
	<title>MISO</title>
	<link rel="stylesheet" href="login_file/style.css">
	<script src="login_file/jquery-3.4.1.min.js"></script> 
	<script src="login_file/pkiValidation/pkiValidation.js"></script>
	<!-- <link rel="stylesheet" href="login_file/bootstrap.min_for_ProgressBar.css"> -->
  	
	<script>
		var key = "${_csrf.parameterName}";
 		var value = "${_csrf.token}";
		jQuery(document).ready(function() {
			jQuery("#jnlpButton").attr("disabled", false);
		});
	</script>
	<style type="text/css">
		/* .loader {
		   position: fixed; 
		    left: 0px;
		    top: 0px;
		    width: 100%;
		    height: 100%;
		    z-index: 9999;
		     background: url('login_file/loader1.gif') 50% 50% no-repeat rgb(249,249,249);
		    opacity: .8;
		} */
	</style>
</head>
<body>
	<header id="header" class="header">
		<div class="header-menu">
    		<div class="row m-0">
		      	<!-- <div class="col-2 col-sm-2">
		        	<div class="header-left">
		          		<img src="login_file/indian_Army.jpg" class="img-fluid" style="height: 63px;">
		        	</div>
				</div>
				<div class="col-8 col-sm-8">
					<div class="heading_content">
						<h1>MANAGEMENT INFORMATION SYSTEM ORGANISATION</h1> <b style="display:none;" id="div_timeout">60</b>
					</div>
				</div>
		      	<div class="col-2 col-sm-2">
		        	<div id="language-select"> <img src="login_file/dgis-logo.jpg" class="img-fluid"> </div>
				</div> -->
				
				<div class="head_left"><div class="header-logo-left"><img src="login_file/indian_army.png" class="img-fluid"></div></div>
					<div class="head_center"><div class="header-title-main"><h1>MANAGEMENT INFORMATION SYSTEM ORGANISATION</h1></div></div>
			      	<div class="head_right"><div class="header-logo-right"><img src="login_file/dgis-logo.png" class="img-fluid"></div></div>
			</div>
		</div>
  	</header> 
  	<div class="header_bottom">
			<div class="header_navigation" style="height: 100%;">
				<div class="container-fluid">
					<div class="row" align="right">
						<table style="width: 100%;height: 100%;">
							<tr>
								<td><a href="login" class="btn btn-danger" type="submit" style="border-radius: 5px; position: relative; float: right !important;background-color: red;color: white;" onclick="localStorage.clear();"><b>Logout</b></a></td>
							</tr>
						</table>
					</div>
				</div> 
			</div>
		</div> 
	<div class="content mt-3" align="center">
		<div class="animated fadeIn">
			<div class="row" align="center">
				<div class="container">
					<br>
					<h2>WELCOME TO MISO 2FA</h2>
					<br>
					<h3><button id="jnlpButton" class='btn btn-success btn-flat m-b-30 m-t-30' onclick="getCheckPIKValidation();">Click here to Proceed</button> </h3> <!-- getCheckPIKValidation(); -->
					<br>
					<div id="TokenLoader" style="display: none;" align="center">
						<span style="display: none;"><button id='b1' onclick='run();' class='btn btn-danger btn-sm' >Dashboard</button></span>
						<span id="" style="font-size: 28px;">2FA app has downloaded.<br> <img alt="" src="login_file/loader1.gif">  <br>Please keep and double click the file. Allow 5 seconds for application to load...</span>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<c:url value="getDownloadJAR" var="jarDownloadUrl" />
	<form:form action="${jarDownloadUrl}" method="post" id="getDownloadJarForm" name="getDownloadJarForm" modelAttribute="id"></form:form>
</body>
</html>