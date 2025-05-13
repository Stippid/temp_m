<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<script type="text/javascript" src="js/miso/assets/js/vendor/jquery-2.1.4.min.js"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/common/nrcss.css">
<link rel="stylesheet" href="js/common/xLte.css"> 
<!--  <link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css">  -->
  	<link rel="stylesheet" href="js/miso/assets/css/font-awesome.min.css">
<script src="js/amchart4/core.js"></script>
<script src="js/amchart4/charts.js"></script>
<script src="js/amchart4/animated.js"></script>
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
<script>
var role = '${role}';
var roleid='${roleid}';
var sus = '${roleSusNo}';

var susNo = '${roleSusNo}';
/* alert("ssss" +sus); */
var nBrd ='${nSPara}';
/* alert("nBrd "+nBrd); */
</script>
<%
String susno1=(String)session.getAttribute("roleSusNo");
%>
<style>


.nRibbinShape {
	position:relative;
	margin:0px 0px!important;
	background:#E3E4FA;
	border-radius:10px;
	color:white;
	text-align:center;
	text-indent: 0.1em;
	color:blue;
	font-size:22px;
	display:inline-table;
	margin:10px;
	padding:1px;
	float:center;
	align-self:center;
	text-align:center;
	border:1px solid blue!important;
	box-shadow: 0 10px 8px 0 rgba(0,0,0,.8);
	min-height:110px;
}

.nRibbinHead {
	background:blue;
	color:white;
	font-size:1.6vw;
	text-align:center;
	padding:0px 5px;
	position: relative!important;
    top: -25px;
    width:120%;
    left:-10%;
    box-shadow: 0 10px 8px 0 rgba(0,0,0,.8);
}

.nRibbinData {
	font-size: 1.2vw;
	box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
}

.nRibbinData2 {
	padding-top:20px;
	font-size: .9vw!important;
	box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
	color:red;
}

.nRibbinDataDiv {
	width:90%;
	margin-left:5%;
	float:center;
	color:#800517;
	height:calc(100% - 40px);
	font-weight: bold;
	box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
}

.nRibbinTitle {
	background:whitesmoke;
	display:block;
	color:blue;
	font-size:1.8vw;
	padding:0px 5px;
	position: relative!important;
    top: -20px;    
}

.small-box  {
	font-size:2em;
	color: white!important;
	width:80%;
	background-color: #800000!important;
}
body{
    
    height: 100vh;
    background-attachment: fixed;
    background: linear-gradient(rgba(255,255,255,0),rgba(255,255,255,-2)),url(js/miso/images/sama.png);
    background-size: cover;
    background-attachment: fixed;
}
.sama{
	color: brown!important;
    width: 100%;
    font-family: sans-serif;
    font-weight: 900;
    font-size: 192%;
    margin-top: -2%;
}
.rw-container{

}
</style>



<html>
<head>
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
<c:url value="/j_spring_security_logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
	<header id="header" class="header">
		<div class="header-menu">
    		<div class="row">
		      	<div class="col-2 col-sm-2">
		        	<div class="header-left">
 		          		<img src="js/miso/images/dgislogo.png" class="img-fluid" style="height: 40px;"> 
 		          		<img src="js/miso/images/indianarmy_smrm5aaa.jpg" class="img-fluid" style="height: 35px;margin-left: 98%;margin-top: -2%;">
		        	</div>
<h1 align="center" class="sama">SAMA OL</h1><a href="javascript:formSubmit();" class="btn btn-danger" type="submit" style="border-radius: 5px; position: relative; float: right !important;background-color: red;color: white; margin-top: -1%;" onclick="localStorage.clear();"><b>Logout</b></a></td>
				</div>
				<div class="col-8 col-sm-8">
					<div class="heading_content">
						
					</div>
				</div>
				<div class="col-2 col-sm-2">
				<img src="js/miso/images/samahead.png" class="img-fluid" style="height: 37px;">
				
				</div>
			</div>
		</div>
</header>
	<div class="content mt-3" style="display:inline-flex;height:100%">
	<c:set var="susno" value="${roleSusNo}"/>
	<c:set var="username" value="${username}"/>
	<c:set var="dataf" value="fpTopDashbd"/>	
	<c:set var="panelstyle" value="width:calc(78vw - 250px);height:calc(78vh);"/>
	<c:set var="nfplay" value="N"/>
	<c:if test="${nBrd=='1'}">
		
		<c:if test="${fn:contains(role,'fp')}">	
			<c:set var="nfplay" value="Y"/>
		</c:if>
		<c:set var="nfplay1col" value="3"/>
		<c:set var="nfplay1dsp" value="block"/>
		<c:set var="panelstyle" value="width:calc(99vw - 300px);height:calc(93vh - 100px);margin-left:20px;"/>
		<c:if test="${fn:contains(role,'fp_view')}">
			<c:set var="nfplay1" value="W"/>
			<c:set var="nfplay1col" value="4"/>
			<c:set var="nfplay1dsp" value="none"/>
			<c:set var="panelstyle" value="width:calc(90vw - 180px);height:calc(93vh - 100px);margin-left:20px;"/>
		</c:if>			
	</c:if>
	<c:if test="${nfplay == 'W1'}">
		<div class="nPopContainer " id="nPopContainer" style="${panelstyle};margin:5px 10px">
			<div class="row" style="align-items: flex-start !important;${panelstyle}">								
				  <div id="ifrmDiv" class="col-md-12">	
				  		<iframe id="cdrfrm1" frameborder=0 src="" style="width:98%;height:calc(100vh - 150px);top:0px;left:0px;"></iframe>
				  </div>
			</div>
		</div>
	</c:if>
	
	<c:if test="${fn:contains(role,'sama')}"> 
									
<!-- 				<div><img src="js/miso/indian_army.png" style="width: 15%;height: 20%"> </div> -->
							
								
<!-- 							<div class="status_card_container" style=";margin:5px 10px;display:inline-block;width:96%!important;"> -->
			
<!-- 			<div class="col-md-8"> -->
				<div class="rw-container">
					 
					 <div class="box">
				            <div class="small-box" style="background-color:green; color:yellow;margin:15px 29px;">
				              <div class="inner">				                
								<span class="h5"><i class="fa fa-edit"></i>
				                <p class="">SD Aspect</p></span>
				              </div>				              
				              <a href="" style="color:white!important;" onclick="javascript:showdocs('https://misoanalytics.army.mil:8443/open-view/151546/79b4b260059ceb545e65368f9ccc0dde');" target="_NEW" class="small-box-footer nGloww click-count1" data-screen-id='14'>More info <i class="fa fa-arrow-circle-right"></i></a>
				            </div>
				        </div>
	            				
	            					<div class="col-md-4">
				            <div class="small-box" style="background-color:green; color:yellow;margin:15px 29px;">
				              <div class="inner">				                
								<span class="h5"><i class="fa fa-edit"></i>
				                <p class="">PERS</p></span>
				              </div>				              
				              <a href="" style="color:white!important;" onclick="javascript:showdocs('https://misoanalytics.army.mil:8443/open-view/194418/62bbd944e2eee28a1ecdda60ca7f92bf');" target="_NEW" class="small-box-footer nGloww click-count1" data-screen-id='12'>More info <i class="fa fa-arrow-circle-right"></i></a>
				            </div>
				        </div> 
				        
				          <div class="box">
				            <div class="small-box" style="background-color:green; color:yellow;margin:15px 29px;">
				              <div class="inner">				                
								<span class="h5"><i class="fa fa-truck"></i>
				                <p>Wpn & Eqpt</p></span>
				              </div>				              
				              <a href="" style="color:white!important;" onclick="javascript:showdocs('https://misoanalytics.army.mil:8443/open-view/152532/c19f4fc0ee8d480918f9b7ecdd0e981e');" target="_NEW" class="small-box-footer nGloww click-count1" data-screen-id='11'>More info <i class="fa fa-arrow-circle-right"></i></a>
				            </div>
 				        </div> 
				        
<!-- 				 </div> -->
				 </div>
				 
<!-- 				 <div class="col-md-8" align="right"> -->
				<div class="rw-container">
				 
				     
						 <div class="box"> 
				            <div class="small-box" style="background-color:green; color:yellow;margin:15px 29px;">
				              <div class="inner">				                
								<span class="h5"><i class="fa fa-rocket"></i>
				                <p>IT Asset</p></span>
				              </div>				              
				              <a href="" style="color:white!important;" onclick="javascript:showdocs('https://misoanalytics.army.mil:8443/open-view/165048/7cd9cb3ccf99494dba3b006ac11d04d3');" target="_NEW" class="small-box-footer nGloww click-count1" data-screen-id='13'>More info <i class="fa fa-arrow-circle-right"></i></a>
				            </div>
				        </div>
	
				        <div class="box">
				            <div class="small-box" style="background-color:green; color:yellow;margin:15px 29px;">
				              <div class="inner">				                
								<span class="h5"><i class="fa fa-edit"></i>
				                <p class="">TMS</p></span>
				              </div>				              
<!-- 				              <a href="" onclick="javascript:showdocs('https://misoanalytics.army.mil:8443/open-view/167635/1acd72772c0340a62b2e2103903e73d6');" target="_NEW" class="small-box-footer nGloww">More info <i class="fa fa-arrow-circle-right"></i></a> -->
				            <a href="" style="color:white!important;" onclick="javascript:showdocs('https://misoanalytics.army.mil:8443/open-view/167085/a8038a03486a7b80f15faee06efca56b');" target="_NEW" class="small-box-footer nGloww click-count1" data-screen-id='10'>More info <i class="fa fa-arrow-circle-right"></i></a>
				            </div>
				        </div>
				        
				        <div class="box">
				            <div class="small-box" style="background-color:green; color:yellow;margin:15px 29px;">
				              <div class="inner">				                
								<span class="h5"><i class="fa fa-edit"></i>
				                <p class="">  Medical</p></span>
				              </div>				              
				              <a href="" style="color:white!important;" onclick="javascript:showdocs('https://misoanalytics.army.mil:8443/open-view/194493/d572303e48f6e2a5ad85966dd3e94909');" target="_NEW" class="small-box-footer nGloww click-count1" data-screen-id='16'>More info <i class="fa fa-arrow-circle-right"></i></a>
				            </div>
				        </div> 
				        </div>
				     
				        </c:if>
					
			</div>		


		
	

</body>
</html>
<script>
function showdocs(b){
		
		var a="Analytics";
		var w=screen.width * .90;
		var h=screen.height * .80;
		var popupWindow = null;
		LeftPosition = (screen.width) ? (screen.width - w) / 2 : 0;
		TopPosition = (screen.height) ? (screen.height - h) / 2 : 0;
		settings = 'toolbar=no,height=' + h + ',width=' + w + ',top=' + TopPosition + ',left=' + LeftPosition + ',scrollbars=' + scroll + ',noresizable';
		console.log("-",b);
		if (!b.includes("http")) {
			b=findata(b);
		}
		console.log("=",b);
		popupWindow = window.open(b,"Analytics", settings);
	}
	
function formSubmit() {
	localStorage.clear();
	document.getElementById("logoutForm").submit();
}
	</script>