<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<script type="text/javascript" src="js/miso/assets/js/vendor/jquery-2.1.4.min.js"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/common/nrcss.css">
<link rel="stylesheet" href="js/common/xLte.css">
<script src="js/amchart4/core.js"></script>
<script src="js/amchart4/charts.js"></script>
<script src="js/amchart4/animated.js"></script>
<style type="text/css">
.blink {
  animation: blinker 1s step-start infinite;
  color:yellow;
  text-shadow:5px red;
}

 @keyframes blinker {
  50% {
    opacity: 0;
  }
} 
</style>
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

.small-box p {
	font-size:1.5em;
	color: aliceblue;
}
</style>
<body onload="nGetAlert();" >


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
	
		<div style="${panelstyle};background-color: transparent;display:inline-block;margin-top:10px;border-radius:10px;border:1px solid lightgray;"><center>
				<div class="nMsgHeader" style="width:96%!important;text-align:center;font-size: x-large;">
					<c:if test="${nfplay == 'Y'}">
						<c:if test="${nfplay1 == 'W'}">	
							&nbsp;&nbsp;OverAll Status for Cdr : ${roleloginName}
						</c:if>
						<c:if test="${nfplay1 != 'W'}">	
							&nbsp;&nbsp;OverAll Status for HQ : ${roleloginName}
						</c:if>
					</c:if>
					<c:if test="${(role=='aad') or (role=='linedte') or (role=='corp') or (role=='div')}">	
							&nbsp;&nbsp;&nbsp;<b>Cdr's Strategic Dashboard: MISO</b>
					</c:if>	
					<c:if test="${fn:contains(role,'dgis')}">	
							&nbsp;&nbsp;&nbsp;<b>Cdr's Strategic Dashboard: MISO</b>
					</c:if>	

				 	<c:if test="${fn:contains(role,'command')}">
					 Cdr's Strategic Dashboard: MISO
					</c:if> 
					
					<c:if test="${fn:contains(role,'armd')}">	
							&nbsp;&nbsp;&nbsp;Cdr's Strategic Dashboard: MISO   
					</c:if>	
							
				</div> 
<%-- 					 <c:if test="${nfplay == 'Y'}"> --%>
				<c:if test="${(role=='aad') or (role=='linedte')}"> 
				<div><img src="js/miso/indian_army.png" style="width: 15%;height: 20%"> </div>
<%-- 				<div><img src="js/miso/corps/<%=susno1%>.PNG" style="width: 15%;height: 20%"> </div> --%>
<%-- 							<div><img src="js/miso/slogan/<%=susno1%>.JPG" style="width: 30%;height: 20%"></div> --%>
								<div class="status_card_container" style=";margin:5px 10px;display:inline-block;width:96%!important;">
						<div class="col-md-4">
				            <div class="small-box" style="background-color:green; color:yellow;margin:15px 10px;">
				              <div class="inner">				                
								<span class="h5"><i class="fa fa-truck"></i>
				                <p>Transport</p></span>
				              </div>				              
				              <a href="" onclick="javascript:showdocs('TMS');" target="_NEW" class="small-box-footer nGloww click-count1" data-screen-id='10'>More info <i class="fa fa-arrow-circle-right"></i></a>
				            </div>
				        </div>
				        <div class="col-md-4">
				            <div class="small-box" style="background-color:green; color:yellow;margin:15px 10px;">
				              <div class="inner">				                
								<span class="h5"><i class="fa fa-rocket"></i>
				                <p>Wpn/Eqpt</p></span>
				              </div>				              
				              <a href="" onclick="javascript:showdocs('WPN');" target="_NEW" class="small-box-footer nGloww click-count1" data-screen-id='11'>More info <i class="fa fa-arrow-circle-right"></i></a>
				            </div>
				        </div>
				        <div class="col-md-4">
				            <div class="small-box" style="background-color:green; color:yellow;margin:15px 10px;">
				              <div class="inner">				                
								<span class="h5"><i class="fa fa-rocket"></i>
				                <p>IT Asset</p></span>
				              </div>				              
				              <a href="" onclick="javascript:showdocs('IT');" target="_NEW" class="small-box-footer nGloww click-count1" data-screen-id='13'>More info <i class="fa fa-arrow-circle-right"></i></a>
				            </div>
				        </div>
				        <div class="col-md-4">
				            <div class="small-box" style="background-color:green; color:yellow;margin:15px 10px;">
				              <div class="inner">				                
								<span class="h5"><i class="fa fa-rocket"></i>
				                <p>Pers</p></span>
				              </div>				              
				              <a href="" onclick="javascript:showdocs('PERS');" target="_NEW" class="small-box-footer nGloww click-count1" data-screen-id='12'>More info <i class="fa fa-arrow-circle-right"></i></a>
				            </div>
				        </div>
				         <div class="col-md-4">
				            <div class="small-box" style="background-color:green; color:yellow;margin:15px 10px;">
				              <div class="inner">				                
								<span class="h5"><i class="fa fa-rocket"></i>
				                <p>Update Status</p></span>
				              </div>				              
				              <a href="" onclick="javascript:showdocs('LOGIN');" target="_NEW" class="small-box-footer nGloww click-count1" data-screen-id='17'>More info <i class="fa fa-arrow-circle-right"></i></a>
				            </div>
				        </div> 
				        <div class="col-md-4">
				            <div class="small-box" style="background-color:green; color:yellow;margin:15px 10px;">
				              <div class="inner">				                
								<span class="h5"><i class="fa fa-edit"></i>
				                <p class="">SD Aspect</p></span>
				              </div>				              
				              <a href="" onclick="javascript:showdocs('SD');" target="_NEW" class="small-box-footer nGloww click-count1" data-screen-id='14'>More info <i class="fa fa-arrow-circle-right"></i></a>
				            </div>
				        </div>
				        <div class="col-md-4">
				            <div class="small-box" style="background-color:green; color:yellow;margin:15px 10px;">
				              <div class="inner">				                
								<span class="h5"><i class="fa fa-rocket"></i>
				                <p>Reg No Update Status</p></span>
				              </div>				              
				              <a href="" onclick="javascript:showdocs('Reg');" target="_NEW" class="small-box-footer nGloww click-count1" data-screen-id='18'>More info <i class="fa fa-arrow-circle-right"></i></a>
				            </div>
				        </div>
				       </div>
				        </c:if>
				       				        
				        <c:if test="${fn:contains(role,'div') }"> 
				
				<div><img src="js/miso/corps/<%=susno1%>.PNG" style="width: 15%;height: 20%"> </div>
							<div><img src="js/miso/slogan/<%=susno1%>.JPG" style="width: 30%;height: 20%"></div>
								<div class="status_card_container" style=";margin:5px 10px;display:inline-block;width:96%!important;">
						<div class="col-md-4">
				            <div class="small-box" style="background-color:green; color:yellow;margin:15px 10px;">
				              <div class="inner">				                
								<span class="h5"><i class="fa fa-truck"></i>
				                <p>Transport</p></span>
				              </div>				              
				              <a href="" onclick="javascript:showdocs('https://misoanalytics.army.mil:8443/open-view/167085/a8038a03486a7b80f15faee06efca56b');" target="_NEW" class="small-box-footer nGloww click-count1" data-screen-id='10'>More info <i class="fa fa-arrow-circle-right"></i></a>
				            </div>
				        </div>
				        <div class="col-md-4">
				            <div class="small-box" style="background-color:green; color:yellow;margin:15px 10px;">
				              <div class="inner">				                
								<span class="h5"><i class="fa fa-rocket"></i>
				                <p>Wpn/Eqpt</p></span>
				              </div>				              
				              <a href="" onclick="javascript:showdocs('WPN');" target="_NEW" class="small-box-footer nGloww click-count1" data-screen-id='11'>More info <i class="fa fa-arrow-circle-right"></i></a>
				            </div>
				        </div>
				        <div class="col-md-4">
				            <div class="small-box" style="background-color:green; color:yellow;margin:15px 10px;">
				              <div class="inner">				                
								<span class="h5"><i class="fa fa-rocket"></i>
				                <p>IT Asset</p></span>
				              </div>				              
				              <a href="" onclick="javascript:showdocs('IT');" target="_NEW" class="small-box-footer nGloww click-count1" data-screen-id='13'>More info <i class="fa fa-arrow-circle-right"></i></a>
				            </div>
				        </div>
				        <div class="col-md-4">
				            <div class="small-box" style="background-color:green; color:yellow;margin:15px 10px;">
				              <div class="inner">				                
								<span class="h5"><i class="fa fa-rocket"></i>
				                <p>Pers</p></span>
				              </div>				              
				              <a href="" onclick="javascript:showdocs('PERS');" target="_NEW" class="small-box-footer nGloww click-count1" data-screen-id='12'>More info <i class="fa fa-arrow-circle-right"></i></a>
				            </div>
				        </div>
				         <div class="col-md-4">
				            <div class="small-box" style="background-color:green; color:yellow;margin:15px 10px;">
				              <div class="inner">				                
								<span class="h5"><i class="fa fa-rocket"></i>
				                <p>Update Status</p></span>
				              </div>				              
				              <a href="" onclick="javascript:showdocs('LOGIN');" target="_NEW" class="small-box-footer nGloww click-count1" data-screen-id='17'>More info <i class="fa fa-arrow-circle-right"></i></a>
				            </div>
				        </div> 
				        <div class="col-md-4">
				            <div class="small-box" style="background-color:green; color:yellow;margin:15px 10px;">
				              <div class="inner">				                
								<span class="h5"><i class="fa fa-edit"></i>
				                <p class="">SD Aspect</p></span>
				              </div>				              
				              <a href="" onclick="javascript:showdocs('SD');" target="_NEW" class="small-box-footer nGloww click-count1" data-screen-id='14'>More info <i class="fa fa-arrow-circle-right"></i></a>
				            </div>
				        </div>
				        <div class="col-md-4">
				            <div class="small-box" style="background-color:green; color:yellow;margin:15px 10px;">
				              <div class="inner">				                
								<span class="h5"><i class="fa fa-rocket"></i>
				                <p>Reg No Update Status</p></span>
				              </div>				              
				              <a href="" onclick="javascript:showdocs('Reg');" target="_NEW" class="small-box-footer nGloww click-count1" data-screen-id='18'>More info <i class="fa fa-arrow-circle-right"></i></a>
				            </div>
				        </div>
				       </div>
				        </c:if>
				   
				   <c:if test="${fn:contains(role,'command') or fn:contains(role,'corps')}"> 	        					         
								<div><img src="js/miso/command/<%=susno1%>.jpg" style="height: 50px;width: 15%;height: 20%"> 
			<div><img src="js/miso/slogan_command/<%=susno1%>.JPG" style="width: 30%;height: 20%"></div>
								<div class="status_card_container" style=";margin:5px 10px;display:inline-block;width:96%!important;">
						<div class="col-md-4">
				            <div class="small-box" style="background-color:green; color:yellow;margin:15px 10px;">
				              <div class="inner">				                
								<span class="h6"><i class="fa fa-truck"></i>
				                <p>Transport</p></span>
				              </div>				              
				           <!-- <a href="" onclick="javascript:showdocs('https://misoanalytics.army.mil:8443/open-view/220783/921d1069dae8e3ab4dc06b2fa85773f4');"target="_NEW" class="small-box-footer nGloww click-count1" data-screen-id='10'>More info <i class="fa fa-arrow-circle-right"></i></a>
 -->				            
 							<a href="" onclick="javascript:showdocs('TMS');"target="_NEW" class="small-box-footer nGloww click-count1" data-screen-id='10'>More info <i class="fa fa-arrow-circle-right"></i></a>
				            </div>
				        </div>
				         <div class="col-md-4">
				            <div class="small-box" style="background-color:green; color:yellow;margin:15px 10px;">
				              <div class="inner">				                
								<span class="h6"><i class="fa fa-rocket"></i>
				                <p>Wpn/Eqpt</p></span>
				              </div>				              
				              <a href="" onclick="javascript:showdocs('WPN');" target="_NEW" class="small-box-footer nGloww click-count1" data-screen-id='11'>More info <i class="fa fa-arrow-circle-right"></i></a>
				            </div>
				        </div>
				        <div class="col-md-4">
				            <div class="small-box" style="background-color:green; color:yellow;margin:15px 10px;">
				              <div class="inner">				                
								<span class="h6"><i class="fa fa-rocket"></i>
				                <p>IT Asset</p></span>
				              </div>				              
				              <a href="" onclick="javascript:showdocs('IT');" target="_NEW" class="small-box-footer nGloww click-count1" data-screen-id='13'>More info <i class="fa fa-arrow-circle-right"></i></a>
				            </div>
				        </div>
				        <div class="col-md-4">
				            <div class="small-box" style="background-color:green; color:yellow;margin:15px 10px;">
				              <div class="inner">				                
								<span class="h6"><i class="fa fa-edit"></i>
				                <p class="">SD Aspect</p></span>
				              </div>				              
				              <a href="" onclick="javascript:showdocs('SD');" target="_NEW" class="small-box-footer nGloww click-count1" data-screen-id='14'>More info <i class="fa fa-arrow-circle-right"></i></a>
				            </div>
				        </div>
				         <div class="col-md-4">
				            <div class="small-box" style="background-color:green; color:yellow;margin:15px 10px;">
				              <div class="inner">				                
								<span class="h5"><i class="fa fa-rocket"></i>
				                <p>Pers</p></span>
				              </div>				              
				              <a href="" onclick="javascript:showdocs('PERS');" target="_NEW" class="small-box-footer nGloww click-count1" data-screen-id='12'>More info <i class="fa fa-arrow-circle-right"></i></a>
				            </div>
				        </div>
				         <div class="col-md-4">
				            <div class="small-box" style="background-color:green; color:yellow;margin:15px 10px;">
				              <div class="inner">				                
								<span class="h5"><i class="fa fa-rocket"></i>
				                <p>Update Status</p></span>
				              </div>				              
				              <a href="" onclick="javascript:showdocs('LOGIN');" target="_NEW" class="small-box-footer nGloww click-count1" data-screen-id='17'>More info <i class="fa fa-arrow-circle-right"></i></a>
				            </div>
				        </div> 
				        <div class="col-md-4">
				            <div class="small-box" style="background-color:green; color:yellow;margin:10px 10px;">
				              <div class="inner">				                
								<span class="h5"><i class="fa fa-rocket"></i>
				                <p>Reg No Update Status</p></span>
				              </div>				              
				              <a href="" onclick="javascript:showdocs('Reg');" target="_NEW" class="small-box-footer nGloww click-count1" data-screen-id='18'>More info <i class="fa fa-arrow-circle-right"></i></a>
				            </div>
				        </div>
				      </div>
				        </c:if>   
		   		        				
				        				
				        				<c:if test="${fn:contains(role,'dgis' )}"> 
									
				<div><img src="js/miso/indian_army.png" style="width: 15%;height: 20%"> </div>
							
								
								<div class="status_card_container" style=";margin:5px 10px;display:inline-block;width:96%!important;">
						<div class="col-md-4">
				            <div class="small-box" style="background-color:green; color:yellow;margin:15px 10px;">
				              <div class="inner">				                
								<span class="h5"><i class="fa fa-truck"></i>
				                <p>Wpn</p></span>
				              </div>				              
				              <a href="" onclick="javascript:showdocs('https://misoanalytics.army.mil:8443/open-view/152532/c19f4fc0ee8d480918f9b7ecdd0e981e');" target="_NEW" class="small-box-footer nGloww click-count1" data-screen-id='11'>More info <i class="fa fa-arrow-circle-right"></i></a>
				            </div>
				        </div>
				        <div class="col-md-4">
				            <div class="small-box" style="background-color:green; color:yellow;margin:15px 10px;">
				              <div class="inner">				                
								<span class="h5"><i class="fa fa-rocket"></i>
				                <p>IT Asset</p></span>
				              </div>				              
				              <a href="" onclick="javascript:showdocs('https://misoanalytics.army.mil:8443/open-view/165048/7cd9cb3ccf99494dba3b006ac11d04d3');" target="_NEW" class="small-box-footer nGloww click-count1" data-screen-id='13'>More info <i class="fa fa-arrow-circle-right"></i></a>
				            </div>
				        </div>
				        <div class="col-md-4">
				            <div class="small-box" style="background-color:green; color:yellow;margin:15px 10px;">
				              <div class="inner">				                
								<span class="h5"><i class="fa fa-edit"></i>
				                <p class="">SD Aspect</p></span>
				              </div>				              
				              <a href="" onclick="javascript:showdocs('https://misoanalytics.army.mil:8443/open-view/151546/79b4b260059ceb545e65368f9ccc0dde');" target="_NEW" class="small-box-footer nGloww click-count1" data-screen-id='14'>More info <i class="fa fa-arrow-circle-right"></i></a>
				            </div>
				        </div>
				        <div class="col-md-4">
				            <div class="small-box" style="background-color:green; color:yellow;margin:15px 10px;">
				              <div class="inner">				                
								<span class="h5"><i class="fa fa-edit"></i>
				                <p class="">TMS</p></span>
				              </div>				              
<!-- 				              <a href="" onclick="javascript:showdocs('https://misoanalytics.army.mil:8443/open-view/167635/1acd72772c0340a62b2e2103903e73d6');" target="_NEW" class="small-box-footer nGloww">More info <i class="fa fa-arrow-circle-right"></i></a> -->
				            <a href="" onclick="javascript:showdocs('https://misoanalytics.army.mil:8443/open-view/167085/a8038a03486a7b80f15faee06efca56b');" target="_NEW" class="small-box-footer nGloww click-count1" data-screen-id='10'>More info <i class="fa fa-arrow-circle-right"></i></a>
				            </div>
				        </div>
				        <div class="col-md-4">
				            <div class="small-box" style="background-color:green; color:yellow;margin:15px 10px;">
				              <div class="inner">				                
								<span class="h5"><i class="fa fa-edit"></i>
				                <p class="">PERS</p></span>
				              </div>				              
				              <a href="" onclick="javascript:showdocs('https://misoanalytics.army.mil:8443/open-view/156399/76cdc4748bbccf67939431f7c7b471fd');" target="_NEW" class="small-box-footer nGloww click-count1" data-screen-id='12'>More info <i class="fa fa-arrow-circle-right"></i></a>
				            </div>
				        </div> 
				        <div class="col-md-4">
				            <div class="small-box" style="background-color:green; color:yellow;margin:15px 10px;">
				              <div class="inner">				                
								<span class="h5"><i class="fa fa-edit"></i>
				                <p class="">Update Status</p></span>
				              </div>				              
				              <a href="" onclick="javascript:showdocs('https://misoanalytics.army.mil:8443/open-view/191748/f133e765b776e8def8cbcdf4c7f79555');" target="_NEW" class="small-box-footer nGloww click-count1" data-screen-id='16'>More info <i class="fa fa-arrow-circle-right"></i></a>
				            </div>
				        </div> 
				        </c:if>
	<!-- COAS DEMO END -->
				        				
	<!-- FOR BRIGMISO ROLE -->			        				
	<c:if test="${fn:contains(role,'brigmiso' )}"> 
									
				<div><img src="js/miso/indian_army.png" style="width: 15%;height: 20%"> </div>
							
								
								<div class="status_card_container" style=";margin:5px 10px;display:inline-block;width:96%!important;">
						<div class="col-md-4">
				            <div class="small-box" style="background-color:green; color:yellow;margin:15px 10px;">
				              <div class="inner">				                
								<span class="h5"><i class="fa fa-truck"></i>
				                <p>Wpn</p></span>
				              </div>				              
				              <a href="" onclick="javascript:showdocs('https://misoanalytics.army.mil:8443/open-view/152532/c19f4fc0ee8d480918f9b7ecdd0e981e');" target="_NEW" class="small-box-footer nGloww click-count1" data-screen-id='11'>More info <i class="fa fa-arrow-circle-right"></i></a>
				            </div>
				        </div>
				        <div class="col-md-4">
				            <div class="small-box" style="background-color:green; color:yellow;margin:15px 10px;">
				              <div class="inner">				                
								<span class="h5"><i class="fa fa-rocket"></i>
				                <p>IT Asset</p></span>
				              </div>				              
				              <a href="" onclick="javascript:showdocs('https://misoanalytics.army.mil:8443/open-view/165048/7cd9cb3ccf99494dba3b006ac11d04d3');" target="_NEW" class="small-box-footer nGloww click-count1" data-screen-id='13'>More info <i class="fa fa-arrow-circle-right"></i></a>
				            </div>
				        </div>
				        <div class="col-md-4">
				            <div class="small-box" style="background-color:green; color:yellow;margin:15px 10px;">
				              <div class="inner">				                
								<span class="h5"><i class="fa fa-edit"></i>
				                <p class="">SD Aspect</p></span>
				              </div>				              
				              <a href="" onclick="javascript:showdocs('https://misoanalytics.army.mil:8443/open-view/151546/79b4b260059ceb545e65368f9ccc0dde');" target="_NEW" class="small-box-footer nGloww click-count1" data-screen-id='14'>More info <i class="fa fa-arrow-circle-right"></i></a>
				            </div>
				        </div>
				        <div class="col-md-4">
				            <div class="small-box" style="background-color:green; color:yellow;margin:15px 10px;">
				              <div class="inner">				                
								<span class="h5"><i class="fa fa-edit"></i>
				                <p class="">TMS</p></span>
				              </div>				              
				            <a href="" onclick="javascript:showdocs('https://misoanalytics.army.mil:8443/open-view/167085/a8038a03486a7b80f15faee06efca56b');" target="_NEW" class="small-box-footer nGloww click-count1" data-screen-id='10'>More info <i class="fa fa-arrow-circle-right"></i></a>
				            </div>
				        </div>
				        <div class="col-md-4">
				            <div class="small-box" style="background-color:green; color:yellow;margin:15px 10px;">
				              <div class="inner">				                
								<span class="h5"><i class="fa fa-edit"></i>
				                <p class="">PERS</p></span>
				              </div>				              
				              <a href="" onclick="javascript:showdocs('https://misoanalytics.army.mil:8443/open-view/156399/76cdc4748bbccf67939431f7c7b471fd');" target="_NEW" class="small-box-footer nGloww click-count1" data-screen-id='12'>More info <i class="fa fa-arrow-circle-right"></i></a>
				            </div>
				        </div> 
				        <div class="col-md-4">
				            <div class="small-box" style="background-color:green; color:yellow;margin:15px 10px;">
				              <div class="inner">				                
								<span class="h5"><i class="fa fa-edit"></i>
				                <p class="">Role based Update Status</p></span>
				              </div>				              
				              <a href="" onclick="javascript:showdocs('https://misoanalytics.army.mil:8443/open-view/172460/29758e24d5c36c14fede6dbf3808feab');" target="_NEW" class="small-box-footer nGloww click-count1" data-screen-id='16'>More info <i class="fa fa-arrow-circle-right"></i></a>
				            </div>
				        </div> 
				        <div class="col-md-4">
				            <div class="small-box" style="background-color:green; color:yellow;margin:15px 10px;">
				              <div class="inner">				                
								<span class="h5"><i class="fa fa-edit"></i>
				                <p class="">Update Status</p></span>
				              </div>				              
				              <a href="" onclick="javascript:showdocs('https://misoanalytics.army.mil:8443/open-view/191748/f133e765b776e8def8cbcdf4c7f79555');" target="_NEW" class="small-box-footer nGloww click-count1" data-screen-id='16'>More info <i class="fa fa-arrow-circle-right"></i></a>
				            </div>
				        </div>
				        <div class="col-md-4">
				            <div class="small-box" style="background-color:green; color:yellow;margin:15px 10px;">
				              <div class="inner">				                
								<span class="h5"><i class="fa fa-rocket"></i>
				                <p>Reg No Update Status</p></span>
				              </div>				              
				              <a href="" onclick="javascript:showdocs('https://misoanalytics.army.mil:8443/open-view/222739/fcbff5447d6dcf97139b0a337256e89f');" target="_NEW" class="small-box-footer nGloww click-count1" data-screen-id='18'>More info <i class="fa fa-arrow-circle-right"></i></a>
				            </div>
				        </div>
				        </c:if>			        				        			  
	<!-- BRIGMISO ROLE END -->			        
 <!-- For miso login status-->
   <c:if test="${fn:contains(role,'dgmi')}"> 	        					         
							
								<div class="status_card_container" style=";margin:5px 10px;display:inline-block;width:96%!important;">
						<div class="col-md-4">
				            <div class="small-box" style="background-color:green; color:yellow;margin:15px 10px;">
				              <div class="inner">				                
								<span class="h6"><i class="fa fa-truck"></i>
				                <p>SD Aspect</p></span>
				              </div>				              
				           <a href="" onclick="javascript:showdocs('https://misoanalytics.army.mil:8443/open-view/208016/aaccc05a97fdea5ae67b69c8e8284ce6');" target="_NEW" class="small-box-footer nGloww click-count1" data-screen-id='10'>More info <i class="fa fa-arrow-circle-right"></i></a>
				            
				            </div>
				        </div>
				  	        
				      </div>
				        </c:if> 
				<c:if test="${nfplay == 'Y'}">
<%-- 				<h3>Welcome ${roleloginName}, </h3>	 --%>		
				<div class="nPopContainer" id="nPopContainer" style="width:100%;height:100%;display1:none;">										
					<div class="" style="margin-top: 0px; position: inherit;height:410px;width:inherit;border:2px solid gray!important;background-color: #F5F5DC!important;border-radius: 10px;">		
						<div class="row" style="align-items: flex-start !important;">
							<div class="col-md-12" style="margin-top:-6px;background-color:transparent;">
								<!-- <span class="nRibbinTitle" style="float:left;text-align:left;">[ OverAll Status ]</span> -->
								<span class="nRibbinData2" id="nAlertMsg" style="font-size:1em!important;text-align:left;float:left;padding:5px!important;color:navy;"></span>
								<span class="nRibbinData2" style="font-size:0.8em;text-align:right;float:right;padding:5px!important">* Last Updated</span>							
							</div>
							<div class="col-md-12" style="margin-top:20px;">
								<c:set var="ln" value="0" />
								 <c:set var="ln" value="${ln+1}" />
						         <div class="col-md-${nfplay1col} form-control-sm" >		         	
						         	<div class="nRibbinShape form-control nGlowB" style="height:110px;width:80%;padding:0px">
						         		<div class="nRibbinHead">Fund Recieved</div>
						         		<div class="nRibbinDataDiv">
								 			<span class="nRibbinData" id="ndb1_frecd"><i style="font-size:18px;" class="fa fa-hourglass fa-spin"></i></span>
							 				<br><span class="nRibbinData2" id="ndb1_frecd2"></span>
								 		</div>
								 	</div>
								 </div>
								 <c:if test="${nfplay1 != 'W'}">
									 <div class="col-md-${nfplay1col} form-control-sm" id="div_fallot">		         	
							         	<div class="nRibbinShape form-control nGlowB" style="height:110px;width:80%;;padding:0px">
							         		<div class="nRibbinHead">Fund Allocation</div>
							         		<div class="nRibbinDataDiv">
									 			<span class="nRibbinData" id="ndb1_fallot"><i style="font-size:18px;" class="fa fa-hourglass fa-spin"></i></span>								 			
									 			<br><span class="nRibbinData2" id="ndb1_fallot2a"></span>
									 			<br><a href="javascript:Findfundallot();" title="Show Fund Allocation Details"><u><span class="nRibbinData2" id="ndb1_fallot2"></span></u></a>
									 		</div>
									 	</div>
									 </div>
								 </c:if>
							<div class="col-md-${nfplay1col} form-control-sm" >		         	
						         	<div class="nRibbinShape form-control nGlowB" style="height:110px;width:80%;;padding:0px">
						         		<div class="nRibbinHead">Expenditure</div>
						         		<div class="nRibbinDataDiv">
								 			<span class="nRibbinData" id="ndb1_fexp"><i style="font-size:18px;" class="fa fa-hourglass fa-spin"></i></span>
							 				<br><span class="nRibbinData2" id="ndb1_fexp2"></span>
								 		</div>
								 	</div>
								 </div>
								  <div class="col-md-${nfplay1col} form-control-sm">		         	
						         	<div class="nRibbinShape form-control nGlowB" style="height:110px;width:80%;;padding:0px">
						         		<div class="nRibbinHead">Booked By CDA</div>
						         		<div class="nRibbinDataDiv" >
								 			<span class="nRibbinData" id="ndb1_fbkd"><i style="font-size:18px;" class="fa fa-hourglass fa-spin"></i></span>
							 				<br><span class="nRibbinData2" id="ndb1_fbkd2"></span>
								 		</div>
								 	</div>
								 </div>								 
							</div>
							<c:if test="${nfplay1 != 'W'}">	
							<div class="col-md-12" style="margin-top:20px;">
								<c:set var="ln" value="0" />	
														
								         <div class="col-md-4 dbfndalt" onclick="dbbreak('fndalt');" id="spedo_fallot" style="">
								         	<span data-head='Fund vs Allotment' class="form-control spedoBox">Fund vs Allocation</span>		         	
								         	<div id="chartgauge_1" style="width: 100%; height: 150px;box-shadow: 0 10px 8px 0 rgba(0,0,0,.8);" titlee="Fund Received vs Fund Allocation. Click for Breakup ..."><i style="font-size:18px;" class="fa fa-hourglass fa-spin"></i></div>
											<a class="nViewMore nGlowB" href="fpmyDashboard?nPara=0" onclick="localStorage.Abandon();" title="Minor Head Wise BreakUp ...">Details ...</a>						         	
										 </div>
								 	
								 <div class="col-md-4 dbaltexp" id="spedo_fexp">
								 	<span data-head='Allotment vs Expdt' class="form-control spedoBox">Fund vs Expdr</span>		         	
						         	<div id="chartgauge_2" style="width: 100%; height: 150px;box-shadow: 0 10px 8px 0 rgba(0,0,0,.8);" titlee="Fund allocation vs Expenditure. Click for Breakup ..."><i style="font-size:18px;" class="fa fa-hourglass fa-spin"></i></div>						         	
						         	<a id="call1_fallot" class="nViewMore nGlowB" href="fpmyDashboard?nPara=1" onclick="localStorage.Abandon();" title="Minor Head Wise BreakUp ...">Details ...</a>
								 </div>
								 <div class="col-md-4 dbexpbkd" id="spedo_fbkd">		         	
								 	<span data-head='Expdt vs Booking' class="form-control spedoBox">Expdr vs Booking</span>
						         	<div id="chartgauge_3" style="width: 100%; height: 150px;box-shadow: 0 10px 8px 0 rgba(0,0,0,.8);" titlee="Expenditure vs Booking by CDA. Click for Breakup ..."><i style="font-size:18px;" class="fa fa-hourglass fa-spin"></i></div>
						         	<a id="call2_fallot"  class="nViewMore nGlowB" href="fpmyDashboard?nPara=5" onclick="localStorage.Abandon();" title="Minor Head Wise BreakUp ...">Details ...</a>
								 </div>
							</div>
							</c:if>
							<div id="" style="width:94%;height:100%;">	
								<div id="" style="width:90%;height:100%;">
									<a class="nViewMoreButton nGlow" style="float:left;background-color:lightgray;padding:5px 10px;color:blue;box-shadow: 0 10px 8px 0 rgba(0,0,0,.8);" href="fp_fund_status" onclick="localStorage.Abandon();" title="View Fund Status">View Fund Status</a>
								</div>								
								
								<div id="call_fallot" style="width:90%;height:100%;">
									<a class="nViewMoreButton nGlow" style="float:right;background-color:lightgray;padding:5px 10px;color:blue;box-shadow: 0 10px 8px 0 rgba(0,0,0,.8);" href="fp_info_board" onclick="localStorage.Abandon();" title="View Fund Performance">View Fund Performance</a>
								</div>
							</div>
						</div>
						</div>
								
				</div>
			</c:if>		
		</div>	
		
		<%-- <c:if test="${nfplay1 != 'W'}">	 --%>	
			<%-- <div class="container" style="width:200px;margin:0px;">
				<div class="nMsgContainer" id="nMsgContainer" style="border:0px solid blue;margin-top:10px;height:55vh;">
					<div class="nMsgHeader"><i class="fa fa fa-bell"></i>&nbsp;&nbsp;Message</div>
						<div class="nPopBody" style="background-color:#F5F5DC;color:blue;padding-left:10px;font-size:14px!important;">												
							<div id="nMsgBoard" style="width:94%;height:95%;">							
								No Message
							</div>
						</div>	
						<c:if test="${fn:contains(role,'fp')}">		
							<div class="nMsgHeader" title="Click to Find New Advisories" style="cursor:pointer;margin-top:15px;border-radius:10px 10px 10px 10px;color:white;">
							<i class="fa fa fa-book"></i><a href="fp_advisory_upd" onclick="localStorage.Abandon();">&nbsp;Advisory</a></div>
						</c:if>
						<c:if test="${fn:contains(role,'fp')}">		
							<div class="nMsgHeader" title="Click to Find New Updates" style="font-size:1.2em;cursor:pointer;margin-top:15px;border-radius:0px 0px 10px 10px" onclick="javascript:$('#divNewUpd').show();">
							<i class="fa fa fa-bullhorn blink"></i>&nbsp;&nbsp;New Updates in Appl</div>
						</c:if>
				</div>
			</div> --%>
			<div id="divNewUpd" class="nPopModelContainer" style="display:none;">
				<div id="divNewUpdfirst" class="nPopCContainer" style="border:20px groove!important;top:25vh!important;width:60%;max-height:55%;min-height:30vh;displayy:none;z-index:100;background:lightgray">
						<div class="nPopHeader">
							<span style="float:center;text-align:center;width:100%;">New Updates in Application</span>	
							<span class="nPopClose" onclick="$('#divNewUpd').hide();" title="Close Window">&#10006;</span>
						</div>
						<div class="nPopBody">
							<input type="hidden" id="nrDetlInput" value="ALL">
							<div id="nMsgBoard card" style="width:100%;">																
								<div class="nPopTable" id="nrTableDataDivDtl" style="max-height:50vh;"> 
									<table style="width:100%;color:white!important;">
						               	<tbody style="font-size: 1vw;">  
						               		<tr><td width="5%"></td><td width="5%"></td><td width="70%"></td><td width="20%"></td>
						               		<tr style="font-size: 1.2vw;font-weight: bold;background-color: azure;"><td colspan='3'>Functionalities</td><td>21 Feb 2022</td>							  			
								  			<tr><Td></td><Td><i class="fa fa fa-book"></i>&nbsp;</td><Td colspan='2'><b>Fund Performance Report for CDRs</b> : Fund Performance Indicator for CDRs as per their Orbat.</td>
								  			<tr><Td></td><Td><i class="fa fa fa-book"></i>&nbsp;</td><Td colspan='2'><b>Expenditure / CDA Report</b> : Expenditure / CDA Status Report.</td>
								  			<tr><Td></td><Td><i class="fa fa fa-book"></i>&nbsp;</td><Td colspan='2'><b>Capture CDA Info</b> : Capturing of CDA Unit Code and PCDA in 'Fwd to CDA' .</td>
						               		<tr style="font-size: 1.2vw;font-weight: bold;background-color: azure;"><td colspan='3'></td><td>11 Oct 2021</td>							  			
								  			<tr><Td></td><Td><i class="fa fa fa-book"></i>&nbsp;</td><Td colspan='2'><b>Expenditure Delete</b> : Deletion of Expenditure is with CDA Status Screen.</td>
								  			<tr style="font-size: 1.2vw;font-weight: bold;background-color: azure;"><td colspan='3'>New Utilities</td><td>22 Jul 2021</td>							  			
								  			<tr><Td></td><Td><i class="fa fa fa-book"></i>&nbsp;</td><Td colspan='2'><b>Fund Allocation Reports</b> : List of Fund Allocation with Amount.</td>
								  			<tr><Td></td><Td><i class="fa fa fa-book"></i>&nbsp;</td><Td colspan='2'><b>Fund Flow </b>: Shows the Flow of Funds ie. from where the Fund Received and to whom the Fund has been allocated.</td>
								  			<tr><Td></td><Td><i class="fa fa fa-book"></i>&nbsp;</td><Td colspan='2'><b>Fund Allocation Tree </b>: Complete End Line flow of Budget Holders</td>
								  			<tr><Td></td><Td><i class="fa fa fa-book"></i>&nbsp;</td><Td colspan='2'><b>Fund Received from Higher BH </b>: Details of Fund Received.</td>							  										  			
						    		  	</tbody>								   
								    </table>
								</div>
						</div>
					</div>
				</div>
			</div>
			
			
			<%-- <div id="framediv" class="col-md-8" style="margin-top:2px;display:none'">
						<div id="ifrmDiv" class="col-md-12 form-control-sm nChipDiv">	
					  		<div class="nPopClose nPopClosePOS" style="" onclick="javascript:nExpdBtn('ifrmDiv','12');"><span id="dbrdExpdBtn"  style="color:yellow;font-size:3.5em;" title="Close Window"><i class="fa fa-close"></i></span></div>								  
		           						<c:set var="dataf" value='${nBase[0][6]}'/>
							<iframe id="framedata" frameborder=0 src="${dataf}" style="width:100%;height:100%;top:0px;left:0px;border:1px solid gray"></iframe>
							
						</div>
			</div> --%>
			
			
			
			
		<%-- </c:if> --%>
</div>
<c:url value="fp_rpt_fund_allot?${_csrf.parameterName}=${_csrf.token}" var="backUrl" />
<form:form action="${backUrl}" method="post" id="m1_fund_allot" name="m1_fund_allot" modelAttribute="m1_nomen">
      <input type="hidden" name="m1_tryear" id="m3_tryear"/>
	  <input type="hidden" name="m1_lvl" id="m3_lvl"/>
	  <input type="hidden" name="m1_rsfmt" id="m3_rsfmt"/>
</form:form>
<c:url value="fpmyDashboard" var="backUrl" />
<form:form action="${backUrl}" id="m1_break" name="m1_break" modelAttribute="m1_nomen">
	<input type="hidden" name="m2_para" id="m2_para"/>
</form:form>
<style>
	
	.spedoBox {
		height:35px;
		text-overflow:none;
		width:100%;
		text-align: center;
		font-size:1.6vw;
		color:blue!important;
		padding:0px;
		background-color: #43C6DB;
	}
	
	.ddbfndalt:hover, .ddbaltexp:hover, .ddbexpbkd:hover {
		border: 1px solid red!important;
		border-radius:7px;
		cursor:pointer;
	}
</style>
<script>
	var t="RS";
	var t2="CR";
	var ts="Cr";
	var nYry="2021";
	$(document).ready(function() {
		
		$(".click-count1").click(function(e){
			 	e.preventDefault();
				var screenid=$(this).data("screen-id");
				//var onclickvalue=$(this).attr("onclick");
				//alert(screenid);
				//alert("check");
				$.ajax({			
					type: 'POST',
					url: 'getClickCount?'+key+"="+value,
			        data: {screen_id:screenid},//key:value//key is the value in controller
			        success: function(response) {
			         if(response){
			        	 window.location.href='commonDashboard';
			        	 //$(this).attr("onclick");
			         }
			        }
			       
			        });
			});
		
		
		nGetAlert();
		if (role=='fp_viewww'){
			frmsrc="https://131.3.54.99:8443/open-view/8141/7bc827ea71cc31ad949ce6e8a7d76df5?ZOHO_CRITERIA=%22zoho_data_04102021%22.%22hlbh_gp%22%3D'"+sus+"'"
			frmsrc="https://131.3.54.99:8443/open-view/8719/f8836bc596c77bf1e7011169790d4b80?ZOHO_CRITERIA=%22zoho_data_21%22.%22hlbh_gp%22%3D'"+sus+"'";
			//openGph(frmsrc)
			$("#cdrfrm1").attr("src",frmsrc);
		} else {
			nGetDBData1();
			nGetDBData2();
		}
		$("#nrWaitLoader").hide();
	});
	
	function nGetAlert11() {
		//alert("test");		
		var b ="${roleSusNo}";
		alert(b);
		if (b !='') {
			var nHed="ln_1_X00000000_"+b;
			$.post("nGetAlertMsg?"+key+"="+value, {nPara:nHed}, function(j) {
		 	}).done(function(j) {
		 		alert(j);
		 		var itxts="";
				var itxt="";
				if (j[0][0] !='NIL') {
			 		for (var i=0;i<j.length;i++){
						itxt += '<span class="nMsgClass" id="nMsg_'+j[i][0]+'"><span>'+j[i][5]+'</spna>'+(i+1)+'. '+j[i][2]+'</span><br>';
			 		}
			 		$("#nMsgBoard").html(itxt);
				}
		 	}).fail(function(xhr, textStatus, errorThrown) { });
			$('#nPopContainerMsg').show();	
		} else {
			alert("Your SUS No is Not Assinged.\nSome Functionalities may give an ERROR.\n\nPl Contact ASCON-34617 / 39747 to resolve it.");
			return false;
		}
	}	
	function nGetAlert() {		
		var b ="${roleSusNo}";
		/* alert('sus '+b); */
		var nHed="ln_1_X00000000_"+b;
		$.post("nGetAlertMsg?"+key+"="+value, {nPara:nHed}, function(j) {
	 	}).done(function(j) { 		
	 		var itxts="";
			var itxt="";
			if (j.length>0) {
				if (j[0][0] !='NIL') {
					itxt='<marquee direction="up" scrollamount="2.5" style="height:100%;" onmouseover="stop();" onmouseout="start()">';
			 		for (var i=0;i<j.length;i++){
						/* itxt += '<span class="nMsgClass" style="padding:10px;" id="nMsg_'+j[i][0]+'">'+(i+1)+'. '+j[i][2]+'<span class="nMsgClassDt">'+j[i][4]+'</span></span>'; */
						itxt += '<span class="nMsgClass nMsgClassD" style="padding:10px;" id="nMsg_'+j[i][0]+'">'+j[i][2]+'<span class="nMsgClassDt">'+j[i][4]+'</span></span>';
			 		}
			 		itxt += '<br><br><br><br><br>';
			 		itxt+="</marquee>";
			 		$("#nMsgBoard").html(itxt);
				}
			}
	 	}).fail(function(xhr, textStatus, errorThrown) { });
		$('#nPopContainerMsg').show();		
	}
	
	function nGetDBData1() {		
		var b ="${roleSusNo}";
		//alert("Alert-1-"+b+"-role");
		if (role.indexOf("fp")>=0) {
			//alert("Alert-1");
			t="RS";
			t2="CR";
			ts="Cr";
			var nHed="ln_1_X00000000_"+b;
			$.post("fpTopDashbdData?"+key+"="+value, {nPara:nHed}, function(j) {
		 	}).done(function(j) {
		 		if (j[0] !="NIL") {
					if (parseInt(j[0][11])>=3) {
						t="CR";
						t2="RS";
						ts="Rs";				
					}
						$("#ndb1_frecd").text(Number(j[0][2]).toINR('',t,'INR',t2) +" "+ts);
						if (parseInt(j[0][11]) !=5) {
							$("#ndb1_fallot").text(Number(j[0][3]).toINR('',t,'INR',t2) + " "+ts);
						}
						$("#ndb1_fexp").text(Number(j[0][4]).toINR('',t,'INR',t2) + " "+ts);
						$("#ndb1_fbkd").text(Number(j[0][10]).toINR('',t,'INR',t2) + " "+ts);
						
						if (parseInt(j[0][11]) !=5) {
							var gval1=(j[0][3] * 100)/j[0][2];					
							if (isNaN(gval1)) {
								nPlotfpExp(1,0);
								
							} else {
								nPlotfpExp(1,gval1);
								
							}
						} else {
							$("#div_fallot").hide();
			 				$("#spedo_fallot").hide();
			 				$("#call_fallot").hide();
			 				$("#call1_fallot").hide();
			 				$("#call2_fallot").hide();
			 			}				
						
						var gval2=(j[0][4] * 100)/j[0][2];
						if (isNaN(gval2)) {
							nPlotfpExp(2,0);
						} else {
							nPlotfpExp(2,gval2);
						}
						var gval3=(j[0][10] * 100)/j[0][4];
						if (isNaN(gval3)) {
							nPlotfpExp(3,0);
						} else {
							nPlotfpExp(3,gval3);
						}
		 		} else {
		 			$("#ndb1_frecd").text(Number(0).toINR('',t,'INR',t2) +" "+ts);
		 			if (parseInt(j[0][11]) !=5) {
						$("#ndb1_fallot").text(Number(0).toINR('',t,'INR',t2) + " "+ts);
		 			} else {
		 				$("#div_fallot").hide();
		 				$("#spedo_fallot").hide();
		 				$("#call_fallot").hide();
		 				$("#call1_fallot").hide();
		 				$("#call2_fallot").hide();
		 			}
					$("#ndb1_fexp").text(Number(0).toINR('',t,'INR',t2) + " "+ts);
					$("#ndb1_fbkd").text(Number(0).toINR('',t,'INR',t2) + " "+ts);
					if (parseInt(j[0][11]) !=5) {
						nPlotfpExp(1,0);
					}
					nPlotfpExp(2,0);
					nPlotfpExp(3,0);			
		 		}
		 		
		 	}).fail(function(xhr, textStatus, errorThrown) {
		 		$("#ndb1_frecd").text("-No Data-");
		 		$("#ndb1_fallot").text("-No Data-");
 				$("#div_fallot").hide();
 				$("#spedo_fallot").hide();
 				$("#spedo_fexp").hide();
 				$("#spedo_fbkd").hide(); 				
 				$("#call_fallot").hide();
 				$("#call1_fallot").hide();
 				$("#call2_fallot").hide();
	 			$("#ndb1_fexp").text("-No Data-");
	 			$("#ndb1_fbkd").text("-No Data-");
	 			$("#spedo_fallot").hide();
				nPlotfpExp(1,0);
				nPlotfpExp(2,0);
				nPlotfpExp(3,0);
		 	});
		}
	}
	function nGetDBData2() {		
		var b ="${roleSusNo}";
		if (role.indexOf("fp")>=0) {
			//alert("Alert-2");
			var nHed="ln_1_X00000000_"+b;
			$.post("fpTopDashbdData2?"+key+"="+value, {nPara:nHed}, function(j) {
		 	}).done(function(j) { 	
		 		//alert(j[0]);
				if (j[0] !='NIL') {
					var frecd2 = j[0][0];
					
					$("#ndb1_frecd2").text( frecd2+" *");
					
					if (j[0][1] !='NIL') {
						var fallot2a=j[0][4];
						$("#ndb1_fallot2").text(j[0][1]+" BH / Est ");

						$("#ndb1_fallot2a").text(fallot2a +" *");
						
						if (new Date(frecd2)>new Date(fallot2a)) {
							$("#ndb1_fallot2a, #ndb1_frecd2, #nAlertMsg").addClass(" blink");
							//$("#ndb1_frecd2").addClass(" blink");
							$("#nAlertMsg").text("Your Fund has been Revised. Fresh Fund Allocation to Allottees may be carried out.");
						} else {
							$("#ndb1_fallot2a, #ndb1_frecd2, #nAlertMsg").removeClass(" blink");
							$("#nAlertMsg").text("");
						}
						
						
					}
					if (j[0][2] !='NIL') {
						$("#ndb1_fexp2").text(j[0][2]+" *");
					}
					if (j[0][3] !='NIL') {
						$("#ndb1_fbkd2").text(j[0][3]+" *");
					}
					nYry=j[0][5];
				}
				if (frecd2 >= fallot2a) {
					//alert(frecd2+","+fallot2a);
				}
				    
		 	}).fail(function(xhr, textStatus, errorThrown) { 
		 		
		 		
		 		
		 	});
		}
	}
	function nPlotfpExp(a,b){
		if (b>100) {
			b=100;
		}
		var chartMin = 0;
		var chartMax = 100;
		var data = {
		  score: b,
		  gradingData: [
		    {
		      title: "Poor",
		      color: "#ED6E6E",
		      lowScore: -1,
		      highScore: 20
		    },
		    {
		      title: "Good",
		      color: "#F9CF48",
		      lowScore: 20,
		      highScore: 40
		    },
		    {
		      title: "Very Good",
		      color: "#84BD4C",
		      lowScore: 40,
		      highScore: 70
		    },
		    {
		      title: "Excellent",
		      color: "#509EE3",
		      lowScore: 70,
		      highScore: 100
		    }
		  ]
		};

		function lookUpGrade(lookupScore, grades) {
		  for (var i = 0; i < grades.length; i++) {
		    if (
		      grades[i].lowScore < lookupScore &&   
		      grades[i].highScore >= lookupScore 
		    ) {
		      return grades[i];
		    }
		  }
		  return null;
		}

		am4core.useTheme(am4themes_animated);

		var chart = am4core.create("chartgauge_"+a, am4charts.GaugeChart);
		chart.hiddenState.properties.opacity = 0;
		chart.fontSize = "12pt";
		chart.innerRadius = am4core.percent(60);
		chart.resizable = true;

		var axis = chart.xAxes.push(new am4charts.ValueAxis());
		axis.min = chartMin;
		axis.max = chartMax;
		axis.strictMinMax = true;
		axis.renderer.radius = am4core.percent(60);
		axis.renderer.inside = false;
		axis.renderer.line.strokeOpacity = 0.1;
		axis.renderer.ticks.template.disabled = false;
		axis.renderer.ticks.template.strokeOpacity = 1;
		axis.renderer.ticks.template.length = 5;
		axis.renderer.grid.template.disabled = true;
		axis.renderer.labels.template.radius = am4core.percent(70);
		axis.renderer.labels.template.fontSize = "0.7em";

		var axis2 = chart.xAxes.push(new am4charts.ValueAxis());
		axis2.min = chartMin;
		axis2.max = chartMax;
		axis2.strictMinMax = true;
		axis2.renderer.labels.template.disabled = true;
		axis2.renderer.ticks.template.disabled = true;
		axis2.renderer.grid.template.disabled = false;
		axis2.renderer.grid.template.opacity = 0.5;
		axis2.renderer.labels.template.bent = true;

		for (let grading of data.gradingData) {
		  var range = axis2.axisRanges.create();
		  range.axisFill.fill = am4core.color(grading.color);
		  range.axisFill.fillOpacity = 1;
		  range.axisFill.zIndex = -1;
		  range.value = grading.lowScore > chartMin ? grading.lowScore : chartMin;
		  range.endValue = grading.highScore < chartMax ? grading.highScore : chartMax;
		  range.grid.strokeOpacity = 0;
		  range.stroke = am4core.color(grading.color).lighten(-0.1);
		}

		var matchingGrade = lookUpGrade(data.score, data.gradingData);

		var label = chart.radarContainer.createChild(am4core.Label);
		label.isMeasured = false;
		label.fontSize = ".9em";
		label.x = am4core.percent(50);
		label.paddingBottom = 15;
		label.horizontalCenter = "middle";
		label.verticalCenter = "bottom";
		label.text = data.score.toFixed(1) + " % ";

		var label2 = chart.radarContainer.createChild(am4core.Label);
		label2.isMeasured = false;
		label2.fontSize = "1.1em";
		label2.horizontalCenter = "middle";
		label2.verticalCenter = "top";	
		var lb2=matchingGrade.title || "";
		label2.text = lb2.toUpperCase();
		label2.fill = am4core.color(matchingGrade.color);

		var hand = chart.hands.push(new am4charts.ClockHand());
		hand.axis = axis2;
		hand.pin.disabled = false;
		hand.value = data.score;
		hand.fill = am4core.color(matchingGrade.color);
		hand.stroke = am4core.color(matchingGrade.color);
	}

	function Findfundallot() {
		var nYr=nYry;
		var nrsfmt=t2;
		$("#m3_tryear").val(nYr);
		$("#m3_lvl").val("RPTALT1");
		$("#m3_rsfmt").val(nrsfmt);
		$("#m1_fund_allot").submit();
	}

	function dbbreak(a) {
		$("#m1_break").submit();		
	}
	
	function nExpdBtn(a,b){
		if ($("#"+a).hasClass("fullscr")) {
			$("#"+a).removeClass("fullscr");
			$("#"+a).addClass("col-md-"+b);			
		} else {
			$("#"+a).removeClass("col-md-"+b);
			$("#"+a).addClass("fullscr");
		}
	}
	
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
	
	function  findata(a) {
		var n="";
		if (a=="TMS") {
			n="https://misoanalytics.army.mil:8443/open-view/220783/921d1069dae8e3ab4dc06b2fa85773f4?ZOHO_CRITERIA=%22bano_wise_bveh_detls_nodal_new${nZBase}";
		} else if (a=="PERS") {
			n="https://misoanalytics.army.mil:8443/open-view/173141/16a0bef8e88661103f7bf152888ac866?ZOHO_CRITERIA=%22UE_Pers_Auth${nZBase}";
			
		} else if (a=="SD") {
			n="https://misoanalytics.army.mil:8443/open-view/166088/6b6adcbab49d479ec7a810da0290a24a?ZOHO_CRITERIA=%22orbat_all_units_view${nZBase}";
		} else if (a=="IT") {
			n="https://misoanalytics.army.mil:8443/open-view/166725/9f841e15a88234413a50fea933147dfa?ZOHO_CRITERIA=%22aih_it_asset_report_all_status${nZBase}";
		} else if (a=="WPN") {
			n="https://misoanalytics.army.mil:8443/open-view/166752/b747186a4d0ca6753969be33ba32cc63?ZOHO_CRITERIA=%22mms_report${nZBase}";
		} else if (a=="LOGIN") {
			n="https://misoanalytics.army.mil:8443/open-view/172460/29758e24d5c36c14fede6dbf3808feab?ZOHO_CRITERIA=%22tb_orbat_modulestatus${nZBase}";
		} 
		else if (a=="Reg") {
			n="https://misoanalytics.army.mil:8443/open-view/222739/fcbff5447d6dcf97139b0a337256e89f?ZOHO_CRITERIA=%22REGN_UPDATE_STATUS${nZBase}";
		} 
		//else if (a=="LOGINS") {
		//	n="https://misoanalytics.army.mil:8443/open-view/168516/2e2a69a4572a166737922bf981a0265d?ZOHO_CRITERIA=%22tb_orbat_modulestatus${nZBase}";
		//} 
		return n;
	}

		//alert(url);
		if(popupWindow != null && !popupWindow.closed){
			popupWindow.close();
			popupWindow = window.open(url, "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=700,height=600,fullscreen=no");
		} else {
			popupWindow = window.open(url, "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=700,height=600,fullscreen=no");
		}
	
</script>
  
