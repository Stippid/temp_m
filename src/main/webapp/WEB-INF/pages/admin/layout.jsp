<%-- <%@page import="com.itextpdf.text.log.SysoLogger"%> --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%> 
<%@ taglib prefix="dandelion" uri="http://github.com/dandelion" %>
<dandelion:asset cssExcludes="datatables"/> 
<dandelion:asset jsExcludes="datatables"/> 
<dandelion:asset jsExcludes="jquery"/>  
<%
	HttpSession sess = request.getSession(false);
	if (sess.getAttribute("userId") == null){
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
	user_agentWithIp = user_agentWithIp.replace(";","");
	currentuser_agentWithIp = currentuser_agentWithIp.replace(";","");
	//out.print("<BR>1. "+currentuser_agentWithIp+"<=c = s=>"+user_agentWithIp);
	if(!user_agentWithIp.equals(currentuser_agentWithIp)){
		//out.print("<BR><BR><BR><BR><BR><BR><BR>--2.-"+currentuser_agentWithIp+"<=c = s=>"+user_agentWithIp);
		sess.invalidate();
		response.sendRedirect("/login"); return; 
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title><spring:message code="myapp.name" /></title>
  	<link rel="shortcut icon" href="js/miso/images/dgis-logo_favicon.png" > 
  	<link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"> 
  	<link rel="stylesheet" href="js/miso/assets/css/font-awesome.min.css">
	<link rel="stylesheet" href="js/miso/assets/scss/style.css">
	<link rel="stylesheet" href="js/common/nrappcss.css">
	<link rel="stylesheet" href="js/common/nrcss.css">	
	<script type="text/javascript" src="js/miso/assets/js/vendor/jquery-2.1.4.min.js"></script> 
	<script src="js/miso/assets/js/plugins.js"></script> 
	<script src="js/miso/assets/js/main.js"></script> 

<style type="text/css">
.dropdown-item.dropdown.create_search.show .dropdown-toggle{
         color: #f2da52 !important
      } 
       a.nav-link.dropdown-toggle.active{
         color: #f2da52 !important;
      }  
      .dropdown-item.dropdown.create_search.show:after{
        color: #f2da52 !important

      }
      .dropdown-item.dropdown.create_search.show .dropdown-toggle i {
            color: #f2da52 !important
       }
      .dropdown-item.dropdown.create_search.show a:active {
         color: #f2da52 !important
      
      }
     .dropdown-item.active i , .dropdown-item.active a.link {
           color: #f2da52 !important
     }
     .nav-item.dropdown.dropdown-item.show .nav-link.dropdown-toggle , .left-panel .navbar .nav-item.dropdown.show:before , .nav-item.dropdown.dropdown-item.show   {
           color: #f2da52 !important
     }
     .left-panel .navbar i {
       margin-right: 3px;
       color: inherit;
     }
      .left-panel .navbar .dropdown-item:focus, .navbar .dropdown-item:hover {
	  color: #fff;
       }
/*        bisag v2 250822  (notification) */
            /*   #notifications {
        display:none;
        width:430px;
        position:absolute;
        top:120px;
        right:10px;
        background:#FFF;
        border:solid 1px rgba(100, 100, 100, .20);
        -webkit-box-shadow:0 3px 8px rgba(0, 0, 0, .20);
        z-index: 1;
    } */
   
    
/* #notifications:before {         
        content: '';
        display:block;
        width:0;
        height:0;
        color:transparent;
        border:10px solid #CCC;
        border-color:transparent transparent #FFF;
        margin-top:-20px;
        margin-left:230px;
    } */
</style>

<style type="text/css">
.notifications{
		/* height: 100%;
		position: relative;
		color: #000;
		padding-left: 3em;
		padding-right: 2em;
		overflow: hidden; */
		
		
		display:none;
        width:430px;
        position:absolute;
        top:120px;
        right:10px;
        background:#FFF;
        border:solid 1px rgba(100, 100, 100, .20);
        -webkit-box-shadow:0 3px 8px rgba(0, 0, 0, .20);
        z-index: 1;
}
		.horizontal-line{
			position: absolute;
			left: 2em;
			height: 22.5em;
			width: .2em;
			background: #EBEBEB;
		}

		.notification{
		padding-left: 3em;
			margin-top: 1.5em;
			opacity: 1;
			animation: FadeInTop .4s ease .2s forwards;
/* 			background-color: #e6e09b; */
			}
			.notification:hover span, .notification:hover p {
    		color: #5F98CD;
		}

		/* 	&:hover{
				span, p{
					color: $blue;
				}			
			} */

			.circle{
				position: relative;
				left: -1.38em;
				    top: 1em;
				margin-top: .3em;
				height: 1em;
				width: 1em;
				border-radius: 50%;
				cursor: pointer;
				background: #fff;
				border: 2px solid #3f52b5;
			}
			
			.fill-circle{
			background: #459cd5;
			}

			.notification span{
				margin: 0;
				padding: 0;
				font-size: .785em;
				cursor: pointer;
				color: #666666;
			}

			.notification p{
				margin: 0;
				margin-top: .2em;
				font-size: 1rem;
				cursor: pointer;
				color: #666666;
				  max-height: 75px;
   				 overflow: hidden;
			}
			
			#notification_counts{
    font-size: 15px;
    color: #fff;
    background-color: #dc3545;
    position: absolute;
    right: 25px;
    width: fit-content;
    border-radius: 20px;
    padding: 2px;
    font-weight: bold;
}

.notification_cross{
    color: #716a6a;
    position: relative;
    left: 340px;
    top: 30px;
        height: 0px;
        text-shadow: 0 0 8px #7696ab;
    
}
		
	
</style>

	<script type="text/javascript">
		var roleAccess = '${roleAccess}';
		var role = '${role}';
		var user_agent = '${user_agent}';
		var army_no = '${army_no}';
		var otpKey = '${otpKey}';
		var nSpara ='${nSPara}';
		//alert(nSpara);		
		var tbl, div;
     	function resetTimer() {
        	if (jQuery('#div_timeout').length) {     
        		jQuery('#div_timeout').html(timeout());  }
     	}
     	function timeout() { return '600'; }
     	function getsubmodule(id){localStorage.setItem("subModule", id); }
     	function getmodule(id){localStorage.setItem("Module", id); }
     	function getpagelink(id){localStorage.setItem("pagelink", id); }
     	
     	var key = "${_csrf.parameterName}";
     	var value = "${_csrf.token}";
     	
     	jQuery(document).on('keypress', function(event) {
     		localStorage.setItem("army_no", army_no);
     		var regex = new RegExp("${regScript}");
     	    var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
     	    if (!regex.test(key)) {
     	       event.preventDefault();
     	       return false;
     	    } 
     	});
     	
   		jQuery(document).ready(function() {	
   			jQuery('body').bind('cut copy paste', function (e) {
	   	        e.preventDefault();
	   	    });
   			/// A.I menu selection @PSP
   			jQuery('ul#Dropdown_'+localStorage.getItem("subModule")).children().each(function () {
   				console.log(this.id.split("Dropdown_scr")[1]); 
   				if (window.location.href == this.children[1].href) {
   					getpagelink(this.id.split("Dropdown_scr")[1])
				}
   			});
   			
   			jQuery(".click-count").click(function(e){

   			 	e.preventDefault();

   				var screenid=$(this).data("screen-id");

   				var url=$(this).find("a").attr("href");

   				//alert(url);

   				//alert(user);

   				$.ajax({

   					type: 'POST',

   					url: 'getClickCount?'+key+"="+value,

   			        data: {screen_id:screenid},//key:value//key is the value in controller

   			        success: function(response) {

   			         if(response){

   			        	 window.location.href=url;

   			         }

   			        }

   			       

   			        });

   			});
   			
   			// set current sub module
   			//jQuery('ul#Dropdown_'+localStorage.getItem("subModule")).attr("class","dropdown-menu scrollbar show");
   			
   			// set current sub module
   			jQuery('ul#Dropdown_'+localStorage.getItem("Module")).parent().attr("class","nav-item dropdown dropdown-item show");
   			jQuery('ul#Dropdown_'+localStorage.getItem("subModule")).parent().attr("class","dropdown-item dropdown create_search  show");
   			jQuery('ul#Dropdown_'+localStorage.getItem("subModule")).attr("class","dropdown-menu scrollbar show");
   			jQuery('ul#Dropdown_'+localStorage.getItem("Module")).attr("class","dropdown-menu show");
   			jQuery('li#Dropdown_scr'+localStorage.getItem("pagelink")).attr("class","dropdown-item active");
   			
   			
   			setInterval(function() {
				var today = new Date();
				var date =("0" + today.getDate()).slice(-2)+'-'+ ("0" + (today.getMonth()+1)).slice(-2)+'-'+today.getFullYear();
				var time = ("0" + today.getHours()).slice(-2) + ":" + ("0" + today.getMinutes()).slice(-2);// + ":" + ("0" + today.getSeconds()).slice(-2);
				var dateTime = date+' '+time;
				jQuery("#datetime").text(dateTime);
				if (jQuery('#div_timeout').length) {
	            	 var tt = jQuery('#div_timeout').html();
	                 if (tt === undefined) {
	                     tt = timeout();
	                 }
	                 var ct = parseInt(tt, 10) - 1;
	                 jQuery('#div_timeout').html(ct.toString().padStart(3, '0'));
	                 if (ct === 0) {
	                	 formSubmit();
	                 }
	             } else {
	            	 formSubmit();
	             }
			}, 1000);
			try
			{
				var msg = document.getElementById("msg").value;
				if(msg != null && msg.length > 0)
				{
					alert(msg);
				}
			}
			catch (e) {
			}
			
			
			getNotifications();
		});
		function formSubmit() {
			localStorage.clear();
			document.getElementById("logoutForm").submit();
		}
		popupWindow = null;
		function parent_disable() {
			if(popupWindow && !popupWindow.closed)
				popupWindow.focus();
		}
		
		
		///bisag v2 250822  (notification)
		function dropnoti() {	 
			
			   // TOGGLE (SHOW OR HIDE) NOTIFICATION WINDOW.
			    jQuery('#notifications').fadeToggle('fast', 'linear', function () {
			       if (jQuery('#notifications').is(':hidden')) {
			    	   //alert("hiiii")
			           jQuery('#notiBellSpanId').css('background-color', '#672a2a');
			       }
			       // CHANGE BACKGROUND COLOR OF THE BUTTON.
			       else {
			    	
			    	getNotifications();
			    	
			    	   jQuery('#notiBellSpanId').css('background-color', '#672a2a');
			       }
			    	  
			   }); 
			   jQuery('#notiSpanId').fadeOut('slow');     // HIDE THE COUNTER.
			  
			 
			  
			   return false;

			}
		var noti_counts = 0;
		function getNotifications(){
			jQuery.post('getnotificationList?' + key + "=" + value, function(j){
		 		jQuery("#appnoti").empty();
		 		noti_counts = 0;
					var v=j.length;
					if(v!=0){
						for(i=0;i<v;i++){			
// 							jQuery("#appnoti").append("<div>"+j[i][0] +"-----------------"+ j[i][1]+"</div>"+"</br>");
							var seen = "";
							var newtxt = "";
								if (j[i][3] !="" && j[i][3] !=null && j[i][3].split(",").includes('${userId}')) {
									seen = "";
								}
								else{
									seen = "fill-circle";
									newtxt = '<i style=" position: absolute; top: -22px;color: #dc3545;"><b>NEW</b></i>';
									noti_counts += 1;
								}
									
							jQuery("#appnoti").append('<div class="notification" data-toggle="modal" data-target="#myModal" onclick="getNotiDetails('+j[i][5]+',\''+seen+'\')">'
							+'<i class="fa fa-times notification_cross" onclick="removeNoti(event,'+j[i][5]+')"></i>'
							+'<div class="circle '+seen+'">'+newtxt+'</div>'
							+'<span class="time">'+(j[i][4]).substring(0,16) +'</span><p><b>'+j[i][0] +'</b></br>'+j[i][1] +'</p></div>');
						}
					}
					jQuery("#notification_counts").text(noti_counts);
					
			  }); 
			
		}
		
		function getNotiDetails(id,seen){
			dropnoti();
			jQuery.post('getnotificationDetails?' + key + "=" + value,{id:id,seen:seen}, function(j){
					var v=j.length;
					if(v!=0){
						 var parsedate="";
						 var dataT;
						  if(j[0].created_date!=null && j[0].created_date!=""){
							  dataT = new Date(j[0].created_date);
// 							  parsedate= dataT.getHours()+":"+dataT.getMinutes()+" "+dataT.getDay()+"/"+(dataT.getMonth()+1)+"/"+dataT.getFullYear()+""
							  parsedate= dataT.getFullYear()+"-"+(dataT.getMonth()+1)+"-"+dataT.getDay()+" "+(dataT.getHours()+1)+":"+dataT.getMinutes()+""
						  }
						jQuery("#noti_detailsdiv").html(' <span class="time" style="text-align: right;color: #666666;"><b>'+parsedate+'</b></span>'
								+'<p><b>'+j[0].title+'</b></p>'
								+'<p style="max-height: 250px; overflow: auto;">'+j[0].description+'</p>');
					}
					else{
						jQuery("#noti_detailsdiv").html(' <p><b>Unable to fetch data</b></p>');
					}
					
					getNotifications();
					
			  }); 
			
			
		}
		
		function removeNoti(event,id){
			 event.stopPropagation();
			 jQuery.post('removeNotification?' + key + "=" + value,{id:id}, function(j){
					if(j!=0){
						getNotifications() 
					}
					else{
					}
			  }); 
		}
		
		///bisag v2 250822  (notification) end
	</script>
	<script>
		document.onkeydown = function(e) {
			if(e.keyCode == 123) { return false; }
			if(e.keyCode == 44) {  return false; }
			if(e.ctrlKey && e.keyCode == 'E'.charCodeAt(0)){ return false; } 
			if(e.ctrlKey && e.shiftKey && e.keyCode == 'I'.charCodeAt(0)){ return false; }
			if(e.ctrlKey && e.shiftKey && e.keyCode == 'J'.charCodeAt(0)){ return false; }
			if(e.ctrlKey && e.keyCode == 'U'.charCodeAt(0)){ return false; }
			if(e.ctrlKey && e.keyCode == 'S'.charCodeAt(0)){ return false; }
			if(e.ctrlKey && e.keyCode == 'H'.charCodeAt(0)){ return false; }
			if(e.ctrlKey && e.keyCode == 'A'.charCodeAt(0)){ return false; }
			if(e.ctrlKey && e.keyCode == 'E'.charCodeAt(0)){ return false; }
		}
		
		function setMenu() {
			if (role.toUpperCase().indexOf("FP")>=0) {
				if (!$("body").hasClass("open")) {
					if (nSpara=='1') {
						document.querySelector("#menuToggle").click();
					} 
				}
			}			
		}
		
	
	</script>
	<!-- <style>
		div {border:0px solid lightgray!important}
		.nBg1 {
			background-image: linear-graient(to right,#6253C1,#852D91,#A3A1FF,#F24645)!important;
			box-shadow: 0 4px 15px 0 rgba(126,52,161,0.75)!important;
		}
	</style> -->
</head>
<body  onFocus="parent_disable();" onclick="parent_disable();resetTimer();" oncontextmenu="return false" onload="setMenu();">
		<c:set var="nfplay" value="N"/>
		<c:set var="panelstyle" value=""/>
		<c:if test="${fn:contains(role,'fp') or role=='cor'}">
			<c:set var="nfplay" value="Y"/>
			<c:set var="panelstyle" value="background:#FBF6D9;!important;"/>
		</c:if>
		<c:if test="${not empty msg}">
			<input type="hidden" name="msg" id="msg" value="${msg}" disabled="disabled"/>
		</c:if>
		<tiles:insertAttribute name="menu" />
		<div id="right-panel" class="right-panel">
			<!-- Header-->
		  	<header id="header" class="header" style="${panelstyle}">
		    	<div class="header-menu">
			    	<c:if test="${nfplay == 'Y'}">
			      		<div class="col-md-12 col-sm-12 nBg1" style="box-shadow: 0 10px 10px 0 rgba(0,0,0,.6);">		      		
				    		<div class="col-md-1 col-sm-1">
				        		<div class="header-left">
				          			<div class="dropdown for-notification"> <img src="js/miso/images/indianarmy_smrm5aaa.jpg" style="height: 50px!important;border-radius:50%;"> </div>			        
				        		</div>
				        		<div align="center" style="position:fixed;left:10px;top:60px;z-index:1001;width:50px;height:50px;">
					 				<!-- <a id="menuToggle" class="btn btn-danger" style="border-radius: 5px; position: relative; float: left !important;margin-top: 10px;">Menu</a> -->
					 				<a id="menuToggle" class="menutoggle pull-right" title="Menu"><i class="fa fa fa-hand-o-left"></i></a>					 				 
					 			</div>
				        		<div align="center" style="position:fixed;left:50px;top:60px;z-index:1001;width:50px;height:50px;border-radius: 5px;">
					 				<a class="btn-primary nBtn nGlow" style="color:yellow;border-radius:5px;" href="commonDashboard" onclick="localStorage.Abandon();" title="Home Page">Home</a>
					 			</div>
				      		</div>
				      		<div class="col-md-10 col-sm-10">
				      			<div align="center"> <strong style="font-size: 2.3vw;color: #800000;text-shadow: 2px 2px 2px #B1FB17;">MANAGEMENT INFORMATION SYSTEM ORGANISATION (MISO)</strong><strong style="font-size: 15px;color: #030080;">&nbsp;&nbsp;<sup>Ver 4.0</sup></strong></div>
				      			<div align="center" class="nticker dash-tic dash-tic-fp" style="background-color:#FFF5EE;color:navy"><marquee scrollamount="3">${layout}</marquee></div>
				      			<div class="col-md-8 col-sm-8">
					      			<div class="navbar-header" style="color:blue;font-weight: bold;margin-left: 30px;">
							      		<!-- <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#main-menu" aria-controls="main-menu" aria-expanded="false" aria-label="Toggle navigation"> <i class="fa fa-bars"></i> </button> -->
							      		${roleloginName} ( ${army_no} )<%--  ( ${roleSusNo} )  --%>
							      	</div>					   
							    </div>
							    <div class="col-md-4 col-sm-4">   	
							      	<div align="right">
						 				<span style="position: relative; color: blue;border-radius: 2px;"> 
							 				Timeout in &nbsp; 
							 				<i style="font-size: 10px;" class="fa fa-hourglass fa-spin"></i>  :  
							 				<b style="color: orangered; min-width: 20px" id="div_timeout">600</b>
						 				</span>
						 			</div>
						 		</div>
				      		</div>
				      		<div class="col-md-1 col-sm-1">
				      			<div class="header-left">
					        		<!-- <div class="language-select dropdown" id="language-select"> <img src="js/miso/images/dgis-logo.png" style="max-width:130px; height: 50px;"> </div> -->				        						        	
					        		<div class="dropdown for-notification"> <img src="js/miso/images/dgislogo.png" style="max-width:130px; height: 50px!important;background-color:transparent;"> </div>
					        		<div style="text-align: center;"><center>
					        			<a href="javascript:formSubmit();" class="btn-danger nBtn nGlow" type="submit" style="background-color:red;color:yellow;border-radius: 5px;margin-top: 10px;" onclick="localStorage.clear();" title="Logout from Application">Logout</a>
					        		</div>				        		
					        	</div>			        		
				      		</div>
				      	</div>
			      	</c:if>	
			      	<c:if test="${nfplay != 'Y'}">
		      			<div class="col-md-1 col-sm-1"> <a id="menuToggle" class="menutoggle pull-left"><i class="fa fa fa-tasks"></i></a>
	        				<div class="header-left">
	          					<div class="dropdown for-notification"> <img src="js/miso/images/indianarmy_smrm5aaa.jpg" style="height: 50px;"> </div>
	        				</div>
	      				</div>
	      				<div class="col-md-10 col-sm-10">
	      					<div align="center"> <strong style="font-size: 22px;color: #800000;">MANAGEMENT INFORMATION SYSTEM ORGANISATION (MISO)</strong>&nbsp;<sup><strong style="font-size: 15px;color: #030080;">Ver 4.0</strong></sup></div>
	      				</div>
	      				<div class="col-md-1 col-sm-1">
	        				<div class="language-select dropdown" id="language-select" align="right"> <img src="js/miso/images/dgislogo.png" style="max-width:130px; height: 50px;"> </div>
	      				</div>		      		
			      	</c:if>		      		
		    	</div>
		  	</header>
		  	<c:if test="${nfplay != 'Y'}">
			  	<%-- <div class="ticker dash-tic" align="right">
					<h3>${layout}</h3>
					<label id="datetime" style="position: relative; color: white;border-radius: 5px;background: #672a2a;"></label>
					<a href="javascript:formSubmit();" class="btn btn-danger" type="submit" style="border-radius: 5px; position: relative; float: right !important;" onclick="localStorage.clear();">Logout</a>
	 			</div> --%>
	 			
	 			<div class="ticker dash-tic" align="right">
				<h3>${layout}</h3>
<!-- 				bisag v2 250822  (notification) -->
				<span id="notiBellSpanId" style="position: relative; margin-right: -5px;"  onclick="dropnoti();">
 				 <i class="fa fa-bell" style="font-size: 30px; color: #fff;">
				<i id="notification_counts" style="">55</i></i>	
				</span>
				<label id="datetime" style="position: relative; color: white;border-radius: 5px;background: #672a2a;"></label>
				<a href="javascript:formSubmit();" class="btn btn-danger" type="submit" style="border-radius: 5px; position: relative; float: right !important;" onclick="localStorage.clear();">Logout</a>
 			</div>
 			<!-- 				bisag v2 250822  (notification) -->
 			
<!--  			<div id="notifications"> -->
<!--                     <h4>Notifications</h4> -->
<!--              <div id="appnoti" style="overflow: scroll; height : 50%; max-height:200px" >  -->
<!--                     </div> -->
<!--                     <div class="seeAll"><a href="#" >See All</a></div> -->
<!--                 </div> -->
<div id="notifications" class="notifications" >
			<div class="horizontal-line"></div>
			<div id="appnoti" style=" overflow: auto;    height: 320px;">
			<div class="notification">
				<div class="circle"></div>
				<span class="time">9:24 AM</span>
				<p><b>John Walker</b></br> posted a photo on your wall.</p>
			</div>
				<div class="notification">
				<div class="circle"></div>
				<span class="time">9:24 AM</span>
				<p><b>John Walker</b> posted a photo on your wall.</p>
			</div>
				<div class="notification">
				<div class="circle"></div>
				<span class="time">9:24 AM</span>
				<p><b>John Walker</b> posted a photo on your wall.</p>
			</div>
			<div class="notification">
				<div class="circle"></div>
				<span class="time">8:19 AM</span>
				<p><b>Alice Parker</b> commented your last post.</p>
			</div>
			<div class="notification">
				<div class="circle"></div>
				<span class="time">Yesterday</span>
				<p><b>Luke Wayne</b> added you as friend.</p>
			</div>
			</div>
		</div>
		
		<!-- Trigger the modal with a button -->
<!--   <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Open Modal</button> -->
		  <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
         <h4 class="modal-title">Notification</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
         
        </div>
        <div class="modal-body">
          <div style="  display: inline-grid; width: -webkit-fill-available;" id = "noti_detailsdiv">
          <span class="time" style="text-align: right;">8:19 AM</span>
				<p><b>Alice Parker</b></p>
				<p>commented your last post.</p>
				</div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
	 			
	 			
	 			
	 			
	 			
	 		</c:if>
		  	<p></p>
		  	<div class="content mt-3" style="margin-top:-13px !important;">		  	 
		  		<div id="WaitLoader" style="display:none;z-index:1000;box-shadow: 0 10px 8px 0 rgba(0,0,0,.8);" align="center">
					<span id="">Processing Data.Please Wait ...<i style="font-size:18px;" class="fa fa-hourglass fa-spin"></i></span>
				</div>
				<div id="nrWaitLoader" class="nPopContainer" style="display:none;z-index:1000;box-shadow: 0 10px 8px 0 rgba(0,0,0,.8);" align="center">
					<span>Processing Data. Please Wait ...  <i style="font-size: 18px;" class="fa fa-hourglass fa-spin"></i></span>
				</div>
				<div id="Loader" style="display: none;z-index:1000;box-shadow: 0 10px 8px 0 rgba(0,0,0,.8);" align="center">
					<span id="" style="font-size: 28px;"><img alt="" src="../login_file/loader1.gif">  <br>Please wait while loading...</span>
				</div>
				<tiles:insertAttribute name="body" />
		  	</div>	
		  	 <c:if test="${nfplay == 'Y'}">
			  	<footer id="footer" class="footer">
			  		<div class="col-md-12 col-sm-12">
			  			<div class="col-md-6 col-sm-6">    
			  				FPMIS Devp by : MISO In-house Devp Team.
        				</div>
        				<div class="col-md-6 col-sm-6" style="text-align: right;padding-right:15px;">
			  				Appl Last Updated : 28 Feb 2022
        				</div>
	      			</div>			 		
			 	</footer>
			 </c:if>
		</div>
	</body>
</html>
