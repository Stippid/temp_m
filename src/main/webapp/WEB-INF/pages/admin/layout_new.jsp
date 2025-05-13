<%-- <%@page import="com.itextpdf.text.log.SysoLogger"%> --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%> 
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
	
	//out.print(currentuser_agentWithIp+"<=c = s=>"+user_agentWithIp);
	if(!user_agentWithIp.equals(currentuser_agentWithIp)){
		sess.invalidate();
		response.sendRedirect("/login"); return; 
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta charset="UTF-8">
  <meta name="" content="">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><spring:message code="myapp.name" /></title>
  	<link rel="shortcut icon" href="js/miso/images/dgis-logo_favicon.png" > 
  	<link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"> 
  	<link rel="stylesheet" href="js/miso/assets/css/font-awesome.min.css">
	<link rel="stylesheet" href="js/miso/assets/scss/style.css">
	<link href="js/assets/css/chat_bot.css" rel="Stylesheet"></link>
	<script src="https://cdn.plot.ly/plotly-latest.min.js"></script>
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
      ::placeholder {
            color: #b4b8bc !important;
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
        z-index: 9999;
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
		
		var tbl, div;
     	function resetTimer() {
        	if (jQuery('#div_timeout').length) {  jQuery('#div_timeout').html(timeout());  }
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
   				 
   				if (window.location.href == this.children[1].href) {
   					getpagelink(this.id.split("Dropdown_scr")[1])
				}
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
				if(msg != null )
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
							
								var onclick = 'getNotiDetails(' + j[i][5] + ',\'' + seen + '\')';
								var toggle='data-toggle="modal" data-target="#myModal" ';
							
								if (j[i][6] != "" && j[i][6] != 0) {
						
									if(j[i][1]=="Please Check the Ticket in Manage Ticket Screen")
										{
										onclick = 'print_ticket(' + j[i][6] + ', ' + j[i][5] + ', \'' + seen + '\')';
										}
								
									else if(j[i][0]=="Tikect Status")
									{
									onclick = 'print_ticket(' + j[i][6] + ', ' + j[i][5] + ', \'' + seen + '\')';
									}
								
									/* else if(j[i][0]=="DRR Approved" || j[i][0]=="DRR Received")
									{
									onclick = 'Search()';
									} */
									else if(j[i][0]=="Unit Raised")
									{
									onclick = 'view(' + j[i][6] + ', ' + j[i][5] + ', \'' + seen + '\')';
									}
									else{
										onclick = 'print(' + j[i][6] + ', ' + j[i][5] + ', \'' + seen + '\')';
									}
								  //  onclick = 'print(' + j[i][6] + ', "'+ j[i][5] +'", \'' + seen + '\')';
								
								toggle='';
								}
								if (  j[i][6] == 0 && j[i][0]=="B Veh DRR Approved" || j[i][0]=="B Veh DRR Received") {
									onclick = 'Search_notification()';	
								}
								

								jQuery("#appnoti").append(
								    '<div class="notification" '+toggle+' onclick="' + onclick + '">' +
								    '<i class="fa fa-times notification_cross" onclick="removeNoti(event,' + j[i][5] + ')"></i>' +
								    '<div class="circle ' + seen + '">' + newtxt + '</div>' +
								    '<span class="time">' + j[i][4].substring(0, 16) + '</span><p><b>' + j[i][0] + '</b></br>' + j[i][1] + '</p></div>'
								);
								
					}
					
					}
					jQuery("#notification_counts").text(noti_counts);
			  }); 
			
		}
		
			
			function print(common_id,id,seen)
			{ 
				getNotiDetails_seen(id,seen)	;	
				document.getElementById("pId").value=common_id;	
				document.getElementById("printForm").submit();
				
			}
			function Search_notification()
			{ 
				
				document.getElementById("sus_no2").value='${roleSusNo}';	
				var sus_no = '${roleSusNo}';
		    	$.post("getActiveUnitNameFromSusNo?"+key+"="+value, {sus_no:sus_no}).done(function(j) {				
		    		var length = j.length-1;
				   	var enc = j[length].substring(0,16);
				   	jQuery("#unit_name2").val(dec(enc,j[0]));	
		    	}).fail(function(xhr, textStatus, errorThrown) {
		    });
				document.getElementById("searchFormnotification").submit();
				
			}
			function view(id)
			{ 
				
				document.getElementById("vid").value=id;	
				document.getElementById("viewDetailsForm").submit();
				
			}
			
			function print_ticket(common_id,id,seen)
			{
				getNotiDetails_seen(id,seen)	;	
				document.getElementById("updateid").value=common_id;	
				document.getElementById('label3').value="Manage User Tickets";
				document.getElementById("updateForm").submit();
				
			}
			
			function getNotiDetails_seen(id,seen){
// 				dropnoti();
				jQuery.post('getnotificationDetails?' + key + "=" + value,{id:id,seen:seen}, function(j){
						var v=j.length;
						if(v!=0){
							 var parsedate="";
							 var dataT;
							  if(j[0].created_date!=null && j[0].created_date!=""){
								  dataT = new Date(j[0].created_date);
//	 							  parsedate= dataT.getHours()+":"+dataT.getMinutes()+" "+dataT.getDay()+"/"+(dataT.getMonth()+1)+"/"+dataT.getFullYear()+""
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
	</script>
</head>
<body  onFocus="parent_disable();" onclick="parent_disable();resetTimer();" oncontextmenu="return false" >
		<c:if test="${not empty msg}">
			<input type="hidden" name="msg" id="msg" value="${msg}" disabled="disabled"/>
		</c:if>
		<tiles:insertAttribute name="menu" />
		<div id="right-panel" class="right-panel"> 
			<!-- Header-->
		  	<header id="header" class="header">
		    	<div class="header-menu">
		    		<div class="col-md-2 col-sm-2"> <a id="menuToggle" class="menutoggle pull-left"><i class="fa fa fa-tasks"></i></a>
		        		<div class="header-left">
		          			<div class="dropdown for-notification"> <img src="js/miso/images/indianarmy_smrm5aaa.jpg" style="height: 50px;"> </div>
		        		</div>
		      		</div>
		      		<div class="col-md-8 col-sm-8">
		      			<div align="center"> <strong style="font-size: 22px;color: #800000;">MANAGEMENT INFORMATION SYSTEM ORGANISATION</strong> <br> <strong style="font-size: 15px;color: #030080;">VERSION 3.1</strong></div>
		      		</div>
		      		<div class="col-md-2 col-sm-2">
		        		<div class="language-select dropdown" id="language-select" align="right"> <img src="js/miso/images/dgis-logo.png" style="max-width:130px; height: 50px;"> </div>
		      		</div>
		    	</div>
		  	</header>
		  	
		  	<div class="ticker dash-tic" align="right">
				<h3>${layout}</h3>
<!-- 				<span style="/* display: flex; */margin-right: 34.5%;color: white;font-size: large;">AI Databot</span> -->
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
<!--           <span class="time" style="text-align: right;">8:19 AM</span> -->
<!-- 				<p><b>Alice Parker</b></p> -->
<!-- 				<p>commented your last post.</p> -->
				</div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>





		  	<p></p>
		  	<div class="content mt-3" style="margin-top:-13px !important;"> 
		  		<div id="WaitLoader" style="display:none;" align="center">
					<span id="">Processing Data.Please Wait ...<i style="font-size: 18px;" class="fa fa-hourglass fa-spin"></i></span>
				</div>
				<div id="Loader" style="display: none;" align="center">
					<span id="" style="font-size: 28px;"><img alt="" src="../login_file/loader1.gif">  <br>Please wait while loading...</span>
				</div>
				<tiles:insertAttribute name="body" />
		  	</div>
		</div>
			<div class="chatbot">
				<a >  <!-- href="chat_url" -->
					<img src="js/chatbot5.gif" alt="chatbot">
				</a>
			</div>
			
			<div class="chat_div">
				<div class="chat_head">
					<h4>AI Databot</h4>
					<button class="btn_close btn btn-danger"><i class="fa fa-times"></i></button>
				</div>
				<div class="chat_main">
				
					<div class="default_div">
						<img class="" src="js/img/chatbot.png">
						<p>How can I help you?</p>
					</div>
				
			    	<div id="question_history" class="">    	    
				    </div>	    	    
				</div>
	<div class="custom-container">
	<div id="classificationDiv" style="display:none">
	        <p class="m-0">Please select any one classification from below.</p>
	        <label><input class="form-check-input" type="radio" name="option" value="vehicle_category A"> Vehicle Type A</label>
	        <label><input class="form-check-input" type="radio" name="option" value="Vehicle Category B"> Vehicle Type B</label>
	        <label><input class="form-check-input" type="radio" name="option" value="Vehicle Category C"> Vehicle Type C</label>
        </div>
	        <input class="form-control inpt_cls" type="text" id="inputText" placeholder="Write your question here ..."><button class="btn btn-primary send_btn"><i class="fa fa-send"></i></button>
	        <div id="radioButtons"></div>
	        <div id="responseTable">
	    </div>
	    </div>
	</div>
	
	<script type="text/javascript">
	var key = "${_csrf.parameterName}";
	var value = "${_csrf.token}";
	var que_cnt = 1;
	var que_cnt_arr = [];
	
	var timeoutDuration = 5000; 
	var timeoutID = setTimeout(function() {
	    console.error("Server did not responded , Either Server is not running or Configuration Problem !!");
	}, timeoutDuration);
	
	document.getElementById("inputText").addEventListener("keyup", function(event) {
		  if (event.key === "Enter") {
			var input_text = $("#inputText").val();
			  var myarr = ["vehicle_unit A", "vehicle_unit B", "vehicle_unit C"];
			  var flag = true;
			  console.log("here");
			  if (flag){
				  document.getElementById("classificationDiv").style.display = 'none';
				  $(".default_div").hide();
				  $("#question_history").append('<div class="question_div"><p><strong class="question"> ' +que_cnt+". "+ input_text + '</strong></p></div>');
				  que_cnt_arr.push(que_cnt);
				  que_cnt+=1;
				  var spinHTML = '<div class="ans_main"><div class="spinner2" id="spinnerDiv"><div class="sp_circle one"></div><div class="sp_circle two"></div><div class="sp_circle three"></div></div></div>';
			        $("#question_history").append(spinHTML);
			        document.getElementById("question_history").scrollTop = document.getElementById("question_history").scrollHeight;
						$("#inputText").val("");
						$.post("http://192.168.14.77:5000/chatbot?" + key + "=" + value, {
							question : input_text,
						})
						.done(function(data, status) {
							clearTimeout(timeoutID);
							 if (status === "success") {
								console.log("Data",data);
								var numberOfKeys = Object.keys(data).length;
								var numberOfValues = 0;
								var dataplot = data.plot;
								data = data.df_dict;
								for (var key in data) {
								  if (data.hasOwnProperty(key)) {
								    numberOfValues++;
								  }
								}
								console.log("Total keys--->",numberOfKeys,"Total values--->",numberOfValues)
								var elementToRemove = document.getElementById("spinnerDiv");
						 		  if (elementToRemove) {
						 		    elementToRemove.parentNode.removeChild(elementToRemove);
						 		    console.log("Element Remove with id"+elementToRemove);
						 		  } else {
						 		    console.log("Element with ID " + id + " not found.");
						 		  }
								var table_str = '<div class="ans_main"><div class="ans_div tbl-scroll">';
								if(numberOfKeys == 1 && numberOfValues==1){
									console.log("Here----");
									var cur_dict = data[0];
									Object.entries(cur_dict).forEach(([key,value])=>{
										table_str +=('<ul><li><span>'+value+'</span></li></ul>')
									});
								}
								else{
									table_str += '<div class="tbl-respons"><table class="table no-margin table-striped table-hover table-bordered">';
									for(var i=0;i<data.length;i++){
										var cur_dict = data[i];
										if(i==0){
											table_str+='<thead><tr>';
											Object.entries(cur_dict).forEach(([key,value])=>{
												table_str+=('<th>'+key.replaceAll("_"," ")+'</th>');
											});
											table_str+='</tr></thead>';
											table_str+='<tbody><tr>';
											Object.entries(cur_dict).forEach(([key,value])=>{
												table_str+=('<td>'+value+'</td>');
											});
											table_str+='</tr>';
										}
										else{
											table_str+='<tr>';
											Object.entries(cur_dict).forEach(([key,value])=>{
												table_str+=('<td>'+value+'</td>');
											});
											table_str+='</tr>';
										}
									}
									table_str+='<tbody></table></div></div></div>';
									table_str+='<div class="ans_main"><button class="btn btn-info btn-chart" id="btn-chart-'+que_cnt_arr[que_cnt_arr.length-1]+'"><i class="fa fa-area-chart"></i> View Chart</button></div>';
								}
								$("#question_history").append(table_str);
								$("#question_history").append('<div id="plotlyChart'+que_cnt_arr[que_cnt_arr.length-1]+'" class="ans_div none">'+dataplot+'</div>');
							 }
							 else{
								console.log("Error",status);
							}
								$("#btn-chart-"+que_cnt_arr[que_cnt_arr.length-1]).click(function(){
										console.log(this.textContent);
										var parentDiv = this.parentNode; // Get the parent element of the button
									    var nextDiv = parentDiv.nextElementSibling;
										console.log(parentDiv,'---',nextDiv);
										 	if (this.textContent.trim() === "View Chart") {
										 		this.textContent = "Hide Chart";
										    } else {
										    	this.textContent = "View Chart";
										    }
										$(nextDiv).toggle();
									});
						}).fail(function(jqXHR, textStatus, errorThrown) {
						    clearTimeout(timeoutID);
						    console.log(textStatus);
						    var table_str = '<div class="ans_main"><div class="ans_div tbl-scroll">';
							var elementToRemove = document.getElementById("spinnerDiv");
					 		  if (elementToRemove) {
					 		    elementToRemove.parentNode.removeChild(elementToRemove);
					 		    console.log("Element Remove with id"+elementToRemove);
					 		  } else {
					 		    console.log("Element with ID " + id + " not found.");
					 		  }
					 		 table_str +=('<ul><li><span> Unknown Error, Please ask again !! </span></li></ul>')
					 		 $("#question_history").append(table_str);
						});
			  }
			  else{
				  document.getElementById("classificationDiv").style.display = 'block';
			  }
		  }
	});
		$(".chatbot").click(function(){
			  $(".chat_div").show();
			});
	$(".btn_close").click(function(){
		  $(".chat_div").hide();
		});
	
	</script>
	</body>
	<c:url value="printRIOUrl" var="printUrl" />
	<form:form action="${printUrl}" method="post" id="printForm" name="printForm" modelAttribute="pId">
		<input type="hidden" name="pId" id="pId" value="0"/>
	</form:form> 
	
<!-- 	///manage_tickect -->
	<c:url value="viewticketDetails" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid"  > <%--  target="result" --%>
	<input type="hidden" name="updateid" id="updateid" value="0"/>
	<input type="hidden" name="label3" id="label3" value="${label}"/>
</form:form> 
<c:url value="viewUnitRaisingDetailsUrl" var="vUrl" />
	<form:form action="${vUrl}" method="post" id="viewDetailsForm" name="viewDetailsForm" modelAttribute="vid">
		<input type="hidden" name="vid" id="vid" value="0"/>
		<input type="hidden" name="scenarioV" id="scenarioV" />
	</form:form>
	<c:url value="UpdategetMVCRReportList" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchFormnotification" name="searchFormnotification" modelAttribute="code_value1">
	<input type="hidden" name="sus_no2" id="sus_no2" value="0"/>
	<input type="hidden" name="unit_name2" id="unit_name2" value="0"/>
</form:form> 
</html>

