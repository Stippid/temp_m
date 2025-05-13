<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="Stylesheet" href="js/Calender/jquery-ui.css">
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>
<link href="js/JS_CSS/jquery.dataTables.min.css" rel="stylesheet">            
<link rel="stylesheet" href="js/common/nrcss.css">

<script>
	var role = "${role}";
</script>
<body onload="setMenu();">
<form:form name="fp_ack_Action" id="fp_ack_Action" action="fp_ack_Action?${_csrf.parameterName}=${_csrf.token}" method="POST" class="form-horizontal" commandName="createHeadCMD">
<div class="content mt-3" align="center">
	<div class="animated fadeIn">
		<div class="row" align="center">
			<div class="container" style="height: 500px;padding-top: 10%;">
				<h3> </h3>			
				<div class="nPopCContainer" id="nPopContainer" style="width:30%;height:300px;">
					<div class="nPopHeader">
						Important for FP Users	
					</div>
					<div class="nPopBody">												
						<div id="nMsgBoardAction"><center>
							<span id="nRetMsg" style="text-align: center;color:blue;font-size: 2.2vw;">${nmsg}</span>							
							<span style="float:center;">FP Module is being Launched very soon.<br>
								Click on Button to Identify yourself<br>
								<input type="submit" class="btn btn-primary btn-sm nGlow" value="Identify me for FP" 
    	                            title="Click to Enroll / Ack for FP">
							</span>														
						</div>
					</div>
				</div>
	 		</div>
		</div>
	</div>
</div>
</form:form>
<script> 
$(document).ready(function() {
		var a='${nmsg}';
});
</script>