package com.controller.ComputingImport;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dao.dropdown.DropdownDao;


@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class import1 {
	@Autowired
	private  DropdownDao cd;
	
	
	
	 List<String> filesListInDir = new ArrayList<String>();

	 
		@RequestMapping(value = "/getDownloadUtility", method = RequestMethod.POST)
		public @ResponseBody int getDownloadUtility(HttpServletRequest request, String msg,HttpSession session , HttpServletResponse response)
				throws IOException {
			
			String itAssetsFilePath = session.getAttribute("itAssetsFilePath").toString();
		

			String jsp = "";
			String Pp_jsp = "";
		    
			
		     jsp+=	"<html><head>\r\n" + 
		     		"    <title>IT ASSET</title>\r\n" + 

		     		"  	<link rel=\"stylesheet\" href=\"js/assets/css/bootstrap.min.css\">\r\n" + 
		     		"  	<link rel=\"stylesheet\" href=\"js/assets/css/font-awesome.min.css\">\r\n" + 
		     		"	<link rel=\"stylesheet\" href=\"js/assets/scss/style.css\">\r\n" + 
		     		"	\r\n" + 
		     		"	<script type=\"text/javascript\" src=\"js/assets/js/vendor/jquery-2.1.4.min.js\"></script> \r\n" + 
		     		"	<script src=\"js/assets/js/plugins.js\"></script> \r\n" + 
		     		"	<script src=\"js/assets/js/main.js\"></script> \r\n" + 
		     		"	\r\n" + 
		     		"\r\n" + 
		     		"<script type=\"text/javascript\" src=\"js/AES_ENC_DEC/lib/aes.js\"></script>\r\n" + 
		     		"<script type=\"text/javascript\" src=\"js/AES_ENC_DEC/lib/pbkdf2.js\"></script>\r\n" + 
		     		"<script type=\"text/javascript\" src=\"js/AES_ENC_DEC/AesUtil.js\"></script>\r\n" + 
		     		"<script src=\"js/miso/miso_js/jquery-2.2.3.min.js\"></script>\r\n" + 
		     		"<link rel=\"stylesheet\" href=\"js/miso/autoComplate/autoComplate.css\">\r\n" + 
		     		"<link href=\"js/Calender/jquery-ui.css\" rel=\"Stylesheet\">\r\n" + 
		     		"<script src=\"js/Calender/jquery-ui.js\" type=\"text/javascript\"></script>\r\n" + 
		     		"<script src=\"js/miso/commonJS/commonmethod.js\" type=\"text/javascript\"></script>\r\n" + 
		     		"<link rel=\"stylesheet\" href=\"js/InfiniteScroll/css/datatables.min.css\">\r\n" + 
		     		"<script src=\"js/InfiniteScroll/js/jquery-1.11.0.js\"></script>\r\n" + 
		     		"<script type=\"text/javascript\" src=\"js/InfiniteScroll/js/datatables.min.js\"></script>\r\n" + 
		     		"<script type=\"text/javascript\" src=\"js/InfiniteScroll/js/jquery.mockjax.min.js\"></script>\r\n" + 
		     		"<script type=\"text/javascript\" src=\"js/InfiniteScroll/js/datatables.mockjax.js\"></script>\r\n" + 
		     		"\r\n" + 
		     		"<link rel=\"stylesheet\" href=\"js/assets/css/bootstrap.min.css\">\r\n" + 
		     		"<link rel=\"stylesheet\" href=\"js/layout/css/animation.css\">\r\n" + 
		     		"<link rel=\"stylesheet\" href=\"js/assets/scss/style.css\">\r\n" + 
		     		"<link rel=\"stylesheet\" href=\"js/assets/collapse/collapse.css\">\r\n" + 
		     		"\r\n" + 
		     		"<script src=\"js/Calender/jquery-2.2.3.min.js\"></script>\r\n" + 
		     		"<script src=\"js/Calender/jquery-ui.js\"></script>\r\n" + 
		     		"<script src=\"js/Calender/datePicketValidation.js\"></script>\r\n" + 
		     		"<script src=\"js/assets/adjestable_Col/jquery.resizableColumns.js\" type=\"text/javascript\"></script>\r\n" + 
		     		"<link rel=\"stylesheet\" href=\"js/assets/adjestable_Col/jquery.resizableColumns.css\">\r\n" + 
		     		"\r\n" + 
		     		"\r\n" + 
		     		"	<script type=\"text/javascript\">\r\n" + 
		     		"	\r\n" + 
		     		"	\r\n" + 
		     		"		var roleAccess = 'Unit';\r\n" + 
		     		"		var role = 'IT_DEO';\r\n" + 
		     		"		var user_agent = 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.82 Safari/537.36';\r\n" + 
		     		"		var army_no = 'IC123456';\r\n" + 
		     		"		var otpKey = '';\r\n" + 
		     		"		\r\n" + 
		     		"		\r\n" + 
		     		"		\r\n" + 
		     		"		var tbl, div;\r\n" + 
		     		"     	function resetTimer() {\r\n" + 
		     		"        	if (jQuery('#div_timeout').length) {  jQuery('#div_timeout').html(timeout());  }\r\n" + 
		     		"     	}\r\n" + 
		     		"     	function timeout() { return '600'; }\r\n" + 
		     		"     	function getsubmodule(id){ localStorage.setItem(\"subModule\", id); }\r\n" + 
		     		"     	\r\n" + 
		     		"     	var key = \"_csrf\";\r\n" + 
		     		"     	var value = \"3b596ad3-3205-4709-835e-4867dcda32af\";\r\n" + 
		     		"     	\r\n" + 
		     		"     	jQuery(document).on('keypress', function(event) {\r\n" + 
		     		"     		localStorage.setItem(\"army_no\", army_no);\r\n" + 
		     		"     		\r\n" + 
		     		"     		var regex = new RegExp(\"^[a-zA-Z0-9\\\\[\\\\] \\\\+ \\\\* \\\\-.,/ ~!@#$^&%_]+$\");\r\n" + 
		     		"     	    var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);\r\n" + 
		     		"     	    if (!regex.test(key)) {\r\n" + 
		     		"     	       event.preventDefault();\r\n" + 
		     		"     	       return false;\r\n" + 
		     		"     	    } \r\n" + 
		     		"     	});\r\n" + 
		     		"     	\r\n" + 
		     		"   		jQuery(document).ready(function() {\r\n" + 
		     		"   			jQuery('body').bind('cut copy paste', function (e) {\r\n" + 
		     		"	   	        e.preventDefault();\r\n" + 
		     		"					\r\n" + 
		     		"				\r\n" + 
		     		"	   	    });\r\n" + 
		     		"   			\r\n" + 
		     		"   			// set current sub module\r\n" + 
		     		"   			jQuery('ul#Dropdown_'+localStorage.getItem(\"subModule\")).attr(\"class\",\"dropdown-menu scrollbar show\");\r\n" + 
		     		"   			setInterval(function() {\r\n" + 
		     		"				var today = new Date();\r\n" + 
		     		"				var date =(\"0\" + today.getDate()).slice(-2)+'-'+ (\"0\" + (today.getMonth()+1)).slice(-2)+'-'+today.getFullYear();\r\n" + 
		     		"				var time = (\"0\" + today.getHours()).slice(-2) + \":\" + (\"0\" + today.getMinutes()).slice(-2);// + \":\" + (\"0\" + today.getSeconds()).slice(-2);\r\n" + 
		     		"				var dateTime = date+' '+time;\r\n" + 
		     		"				jQuery(\"#datetime\").text(dateTime);\r\n" + 
		     		"				\r\n" + 
		     		"				if (jQuery('#div_timeout').length) {\r\n" + 
		     		"	            	 var tt = jQuery('#div_timeout').html();\r\n" + 
		     		"	                 if (tt === undefined) {\r\n" + 
		     		"	                     tt = timeout();\r\n" + 
		     		"	                 }\r\n" + 
		     		"	                 var ct = parseInt(tt, 10) - 1;\r\n" + 
		     		"	                 jQuery('#div_timeout').html(ct.toString().padStart(3, '0'));\r\n" + 
		     		"	                 if (ct === 0) {\r\n" + 
		     		"	                	 formSubmit();\r\n" + 
		     		"	                 }\r\n" + 
		     		"	             } else {\r\n" + 
		     		"	            	 formSubmit();\r\n" + 
		     		"	             }\r\n" + 
		     		"			}, 1000);\r\n" + 
		     		"			try\r\n" + 
		     		"			{\r\n" + 
		     		"				var msg = document.getElementById(\"msg\").value;\r\n" + 
		     		"				if(msg != null )\r\n" + 
		     		"				{\r\n" + 
		     		"					alert(msg);\r\n" + 
		     		"				}\r\n" + 
		     		"			}\r\n" + 
		     		"			catch (e) {\r\n" + 
		     		"			}\r\n" + 
		     		"		});\r\n" + 
		     		"		function formSubmit() {\r\n" + 
		     		"			document.getElementById(\"logoutForm\").submit();\r\n" + 
		     		"		}\r\n" + 
		     		"		popupWindow = null;\r\n" + 
		     		"		function parent_disable() {\r\n" + 
		     		"			if(popupWindow && !popupWindow.closed)\r\n" + 
		     		"				popupWindow.focus();\r\n" + 
		     		"		}\r\n" + 
		     		"	</script>\r\n" + 
		     		"	<script>\r\n" + 
		     		"		document.onkeydown = function(e) {\r\n" + 
		     		"			if(e.keyCode == 123) { return false; }\r\n" + 
		     		"			if(e.keyCode == 44) {  return false; }\r\n" + 
		     		"			if(e.ctrlKey && e.keyCode == 'E'.charCodeAt(0)){ return false; } \r\n" + 
		     		"			if(e.ctrlKey && e.shiftKey && e.keyCode == 'I'.charCodeAt(0)){ return false; }\r\n" + 
		     		"			if(e.ctrlKey && e.shiftKey && e.keyCode == 'J'.charCodeAt(0)){ return false; }\r\n" + 
		     		"			if(e.ctrlKey && e.keyCode == 'U'.charCodeAt(0)){ return false; }\r\n" + 
		     		"			if(e.ctrlKey && e.keyCode == 'S'.charCodeAt(0)){ return false; }\r\n" + 
		     		"			if(e.ctrlKey && e.keyCode == 'H'.charCodeAt(0)){ return false; }\r\n" + 
		     		"			if(e.ctrlKey && e.keyCode == 'A'.charCodeAt(0)){ return false; }\r\n" + 
		     		"			if(e.ctrlKey && e.keyCode == 'E'.charCodeAt(0)){ return false; }\r\n" + 
		     		"		}\r\n" + 
		     		"	</script>\r\n" + 
		     		"</head>\r\n" + 
		     		"<body onfocus=\"parent_disable();\" onclick=\"parent_disable();resetTimer();\" oncontextmenu=\"return false\" style=\"\">\r\n" + 
		     		"		\r\n" + 
		     		"		\r\n" + 
		     		"<script>\r\n" + 
		     		"$(document).ready(function() {\r\n" + 
		     		"	\r\n" + 
		     		"	 colAdj(\"show\");\r\n" + 
		     		"});\r\n" + 
		     		"	 \r\n" + 
		     		"	 </script>\r\n" + 
		     		"<script>\r\n" + 
		     		"	var username=\"PARTH_DEO\";\r\n" + 
		     		"	var curDate = \"20-09-2021 14:29:22\";\r\n" + 
		     		"	\r\n" + 
		     		"	//var addiphostna ='0:0:0:0:0:0:0:1';\r\n" + 
		     		"</script>\r\n" + 
		     		"	\r\n" + 
		     		"	<form action=\"/It_Asset_01_09_2021_v2/j_spring_security_logout\" method=\"post\" id=\"logoutForm\">\r\n" + 
		     		"		<input type=\"hidden\" name=\"_csrf\" value=\"3b596ad3-3205-4709-835e-4867dcda32af\">\r\n" + 
		     		"	</form>\r\n" + 
		     		"	<aside id=\"left-panel\" class=\"left-panel\">\r\n" + 
		     		"		<nav class=\"navbar navbar-expand-sm navbar-default\">\r\n" + 
		     		"	      	<div align=\"center\">\r\n" + 
		     		" 				<span style=\"position: relative; color: white;border-radius: 2px;\"> Session timeout in &nbsp; <i style=\"font-size: 10px;\" class=\"fa fa-hourglass fa-spin\"></i>  :  <b style=\"color: orangered; min-width: 20px\" id=\"div_timeout\">213</b></span>\r\n" + 
		     		" 			</div>\r\n" + 
		     		"	  	</nav>\r\n" + 
		     		"</aside>\r\n" + 
		     		"		<div id=\"right-panel\" class=\"right-panel\"> \r\n" + 
		     		"			<header id=\"header\" class=\"header\">\r\n" + 
		     		"		    	<div class=\"header-menu\">\r\n" + 
		     		"		    		<div class=\"col-md-2 col-sm-2\"> <a id=\"menuToggle\" class=\"menutoggle pull-left\"><i class=\"fa fa fa-tasks\"></i></a>\r\n" + 
		     		"		        		<div class=\"header-left\">\r\n" + 
		     		"		          			<div class=\"dropdown for-notification\"> <img src=\"js/miso/images/indianarmy_smrm5aaa.jpg\" class=\"img-fluid\" style=\"height: 55px;\"> </div>\r\n" + 
		     		"		        		</div>\r\n" + 
		     		"		      		</div>\r\n" + 
		     		"		      		<div class=\"col-md-8 col-sm-8\">\r\n" + 
		     		"		      			<div align=\"center\"> <strong style=\"font-size: 22px;color: #800000;\">IT ASSET</strong> <br> <strong style=\"font-size: 15px;color: #030080;\">VERSION 1.0</strong></div>\r\n" + 
		     		"		      		</div>\r\n" + 
		     		"		      		<div class=\"col-md-2 col-sm-2\">\r\n" + 
		     		"		        	<div class=\"language-select dropdown\" id=\"language-select\" align=\"right\"> <img src=\"js/miso/images/dgis-logo.png\" class=\"img-fluid\" style=\"max-width:130px; height: 50px;\"></div>\r\n" + 
		     		"		      		</div>\r\n" + 
		     		"		    	</div>\r\n" + 
		     		"		  	</header>\r\n" + 
		     		"		  	<div class=\"ticker dash-tic\" align=\"right\">\r\n" + 
		     		"				<h3></h3>\r\n" + 
		     		"				<label id=\"datetime\" style=\"position: relative; color: white;border-radius: 5px;background: #672a2a;\">20-09-2021 14:45</label>\r\n" + 
		     		"				<a href=\"javascript:formSubmit();\" class=\"btn btn-danger\" type=\"submit\" style=\"border-radius: 5px; position: relative; float: right !important;\" onclick=\"localStorage.clear();\">Logout</a>\r\n" + 
		     		" 			</div>\r\n" + 
		     		"		  	<p></p>\r\n" + 
		     		"		  	<div class=\"content mt-3\" style=\"margin-top:-13px !important;\"> \r\n" + 
		     		"		  		<div id=\"WaitLoader\" style=\"display:none;\" align=\"center\">\r\n" + 
		     		"					<span id=\"\">Processing Data.Please Wait ...<i style=\"font-size: 18px;\" class=\"fa fa-hourglass fa-spin\"></i></span>\r\n" + 
		     		"				</div>\r\n" + 
		     		"				\r\n" + 
		     		"\r\n" + 
		     		"\r\n" + 
		     		"\r\n" + 
		     		"\r\n" + 
		     		"<style>\r\n" + 
		     		".ui-tooltip {\r\n" + 
		     		"	position: absolute;\r\n" + 
		     		"	top: 110px;\r\n" + 
		     		"	left: 100px;\r\n" + 
		     		"	\r\n" + 
		     		"	\r\n" + 
		     		"}\r\n" + 
		     		"\r\n" + 
		     		"\r\n" + 
		     		"</style>\r\n" + 
		     		"<div class=\"third_2\" id=\"third_2\">\r\n" + 
		     		"<div id=\"data_1\">\r\n" + 
		     		"  <div id=\"data\" style=\"overflow-x: auto; max-width: 1600px; border:solid black;\">\r\n" + 
		     		"\r\n" + 
		     		"\r\n" + 
		     		"     \r\n" + 
		     		"\r\n" + 
		     		"     <table border=\"1\" style=\"border-color: black;    width: 4000px;\" id=\"show\" >\r\n" + 
		     		"\r\n" + 
		     		"    \r\n" + 
		     		"        <thead>\r\n" + 
		     		"         <!---  <tr>\r\n" + 
		     		"            <th colspan=\"6\" style=\"text-align: center; background-color: #ffcc00\">PERSONAL DETAILS</th>\r\n" + 
		     		"             <th colspan=\"4\" style=\"text-align: center; background-color: #99cc00\">PRIMARY SKILLS</th>\r\n" + 
		     		"            <th colspan=\"10\" style=\"text-align: center;background-color: #ffa366\">SECONDARY SKILLS</th> \r\n" + 
		     		"            <th colspan=\"4\" style=\"text-align: center; background-color: #b3d1ff\">BODY WEIGHT DETAILS</th>\r\n" + 
		     		"             <th rowspan=\"3\"  style=\"text-align: center; background-color: #00cc00\">ACTION</th> \r\n" + 
		     		"              \r\n" + 
		     		"          </tr>  --->\r\n" + 
		     		"\r\n" + 
		     		"\r\n" + 
		     		"       <tr>\r\n" + 
		     		"                <th align=\"center\" rowspan=\"2\" style=\"background-color: #264e58;\">Ser No</th>\r\n" + 
		     		"                <th rowspan=\"2\" style=\"background-color: #264e58\"><strong style=\"color: red;\">* </strong>Computing Assets Type</th>\r\n" + 
		     		"                <th rowspan=\"2\" style=\"background-color: #264e58\"><strong style=\"color: red;\">* </strong>Make/Brand Name</th>\r\n" + 
		     		"                <th rowspan=\"2\" style=\"background-color: #264e58\"><strong style=\"color: red;\">* </strong>Model Name</th>\r\n" + 
		     		"                <th rowspan=\"2\" style=\"background-color: #264e58\"><strong style=\"color: red;\">* </strong>Processor Type</th>\r\n" + 
		     		"                <th rowspan=\"2\" style=\"background-color: #264e58\"><strong style=\"color: red;\">* </strong>RAM Capacity</th>\r\n" + 
		     		"               <th style=\"background-color: #264e58\"><strong style=\"color: red;\">* </strong>HDD Capacity</th>\r\n" + 
		     		"                <th style=\"background-color: #264e58\"><strong style=\"color: red;\">* </strong>Operating System</th>\r\n" + 
		     		"                <th style=\"background-color: #264e58\">Office</th>\r\n" + 
		     		"                <th style=\"background-color: #264e58\"><strong style=\"color: red;\">* </strong>AntiVirus Installed</th>\r\n" + 
		     		"                <th style=\"background-color: #264e58\"><strong style=\"color: red;\">* </strong>AntiVirus</th>\r\n" + 
		     		"                <th style=\"background-color: #264e58\"><strong style=\"color: red;\">* </strong>OS/Firmware Activation and subsequent Patch Updation Mechanism</th>\r\n" + 
		     		"                <th style=\"background-color: #264e58\"><strong style=\"color: red;\">* </strong>Dply Envt as Applicable</th>\r\n" + 
		     		"                <th style=\"background-color: #264e58\">RAM Slot Quantity</th>\r\n" + 
		     		"                <th style=\"background-color: #264e58\">CD Drive</th>\r\n" + 
		     		"                <th style=\"background-color: #264e58\">Warranty</th>\r\n" + 
		     		"                <th style=\"background-color: #264e58\">Warranty Upto</th>\r\n" + 
		     		"                <th style=\"background-color: #264e58\"><strong style=\"color: red;\">* </strong>Model Number</th>\r\n" + 
		     		"                <th style=\"background-color: #264e58\"><strong style=\"color: red;\">* </strong>Machine No.</th>\r\n" + 
		     		"                <th style=\"background-color: #264e58\">MAC Address</th>\r\n" + 
		     		"                <th style=\"background-color: #264e58\">IP Address</th>\r\n" + 
		     		"                <th style=\"background-color: #264e58\"><strong style=\"color: red;\">* </strong>Serviceable State</th>\r\n" + 
		     		"                <th style=\"background-color: #264e58\"><strong style=\"color: red;\">* </strong>Un-serviceable State</th> \r\n" + 
		     		"				<th style=\"background-color: #264e58\"><strong style=\"color: red;\">* </strong>Un-servicable from</th>\r\n" + 
		     		"				<th style=\"background-color: #264e58\">Proc Cost</th>\r\n" + 
		     		"				<th style=\"background-color: #264e58\">Proc Date</th>\r\n" + 
		     		"				<th style=\"background-color: #264e58\"><strong style=\"color: red;\">* </strong>Budget Head</th>\r\n" + 
		     		"				<th style=\"background-color: #264e58\"><strong style=\"color: red;\">* </strong>Budget Code</th>\r\n" + 
		     		"				<th style=\"background-color: #264e58\">Action</th>\r\n" + 
		     		"            </tr>"+
		     		"       \r\n" + 
		     		"       \r\n" + 
		     		"       \r\n" + 
		     		"          <!-- <th colspan=\"6\" ></th> \r\n" + 
		     		"           <th style=\"text-align: center; background-color: #99cc00;width: 2%\">120</th>\r\n" + 
		     		"          <th style=\"text-align: center; background-color: #99cc00\">65</th>\r\n" + 
		     		"          <th style=\"text-align: center; background-color: #99cc00\">35</th>\r\n" + 
		     		"          <th style=\"text-align: center; background-color: #99cc00\">10</th>\r\n" + 
		     		"          <th style=\"text-align: center; background-color: #ffa366\">100</th>\r\n" + 
		     		"          <th style=\"text-align: center; background-color: #ffa366\">30</th>\r\n" + 
		     		"          <th style=\"text-align: center; background-color: #ffa366\">30</th>\r\n" + 
		     		"          <th style=\"text-align: center; background-color: #ffa366\">30</th>\r\n" + 
		     		"          <th style=\"text-align: center; background-color: #ffa366\">30</th>\r\n" + 
		     		"          <th style=\"text-align: center; background-color: #ffa366\">30</th>\r\n" + 
		     		"          <th style=\"text-align: center; background-color: #ffa366\">30</th>\r\n" + 
		     		"          <th style=\"text-align: center; background-color: #ffa366\">30</th>\r\n" + 
		     		"          <th style=\"text-align: center; background-color: #ffa366\">30</th>\r\n" + 
		     		"          <th style=\"text-align: center; background-color: #ffa366\">30</th>\r\n" + 
		     		"          <th colspan=\"3\" style=\"text-align: center; background-color: #b3d1ff\"></th>  \r\n" + 
		     		"    <th style=\"text-align: center; background-color: #0099ff\"></th>\r\n" + 
		     		"          <th style=\"text-align: center; background-color: #0099ff\"></th>\r\n" + 
		     		"          <th style=\"text-align: center; background-color: #b3d1ff\">30</th>  -->\r\n" + 
		     		"          \r\n" + 
		     		"         \r\n" + 
		     		"          <!-- <th colspan=\"3\" ></th>\r\n" + 
		     		"          <th style=\"text-align: center;\">30</th> -->\r\n" + 
		     		"      </thead> \r\n" + 
		     		"        <tbody id=\"tb1\">  \r\n" + 
		     	
		     		"            <tr id=\"tr_id1\">\r\n" + 
		     		"              <td align=\"center\" width='1%;'>1</td>\r\n" + 
		     		"                <td><select id=\"asset_type1\" name=\"asset_type1\" class=\"form-control\" >\r\n" ;
		     	List<Map<String, Object>> getasset_type1 = cd.ComputingAssetListNew();
		    	List<Map<String, Object>> getmake = cd.ComputingMakeListNew();
		    	List<Map<String, Object>> getmodel = cd.ComputingModelListNew();
			    List<Map<String, Object>> getprocessor_type = cd.processor_typeListNew();
			    List<Map<String, Object>> getram = cd.ramListNew();
			    List<Map<String, Object>> gethdd = cd.hddListNew();
			    List<Map<String, Object>> getos = cd.OperatingsystemListNew();
			    List<Map<String, Object>> getoffice = cd.OfficeListNew();
			    List<Map<String, Object>> getantivirus = cd.AntiVirusListNew();
			    List<Map<String, Object>> getosfirmware = cd.OSFirmwareListNew();
			    List<Map<String, Object>> getdplyenv = cd.DplyenvListNew();
			    List<Map<String, Object>> getbudget = cd.BudgetListNew();
			    List<Map<String, Object>> getpheral_type = cd.peripheral_typeListNew();
				List<Map<String, Object>> gethwype = cd.hwListNew();
				List<Map<String, Object>> gettype = cd.typeListNew();
				List<Map<String, Object>> ups_capacityNew = cd.ups_capacityNew();
				List<Map<String, Object>> paper_sizeNew = cd.paper_sizeNew();
				List<Map<String, Object>> paper_connetivity = cd.paper_connetivity();
				List<Map<String, Object>> hardware_interfaceNew = cd.hardware_interfaceNew();
				List<Map<String, Object>> type_connetivity = cd.type_connetivity();
				List<Map<String, Object>> getnetwork_features = cd.getnetwork_features();
				List<Map<String, Object>> getethernet_portnew = cd.getethernet_portnew();
				List<Map<String, Object>> getmanagement_layernew = cd.getmanagement_layernew();
			    
			    
			    jsp+= "	<option value=\"0\">--Select--</option>\r\n" ; 
			    for(int i=0;i<getasset_type1.size();i++) {
				    jsp+= "<option value='"+getasset_type1.get(i).get("id")+"' name='"+getasset_type1.get(i).get("assets_name")+"'>"+getasset_type1.get(i).get("assets_name")+"</option>\r\n" ; 
				         
				     }
				    jsp+= "</select>\r\n" ;
				    jsp+=" </td> " ; 
				    jsp+=" <td ><select id=\"make_name1\" name=\"make_name1\" class=\"form-control\" >";
		     		 jsp+= "	<option value=\"0\">--Select--</option>\r\n" ; 
				     for(int i=0;i<getmake.size();i++) {
				    jsp+= "<option value='"+getmake.get(i).get("id")+"' name='"+getmake.get(i).get("make_name")+"'>"+getmake.get(i).get("make_name")+"</option>\r\n" ; 
				         
				     }
				    jsp+= "</select>\r\n" ;
				    jsp+= "</td>\r\n" ; 
				    		jsp+= "<td ><select id=\"model_name1\" name=\"model_name1\" class=\"form-control\">";
		     		 jsp+= "	<option value=\"0\">--Select--</option>\r\n" ; 
				     for(int i=0;i<getmodel.size();i++) {
				    jsp+= "<option value='"+getmodel.get(i).get("id")+"' name='"+getmodel.get(i).get("model_name")+"'>"+getmodel.get(i).get("model_name")+"</option>\r\n" ; 
				         
				     }
				    jsp+= "</select> </td>\r\n"+ 
		     		"                <td width='15%;'><select id=\"processor_type1\" name=\"processor_type1\" class=\"form-control\">\r\n" + 
		     		"										<option value=\"0\" selected=\"selected\">--Select--</option>\r\n" ; 
		     		 for(int i=0;i<getprocessor_type.size();i++) {
						    jsp+= "<option value='"+getprocessor_type.get(i).get("id")+"' name='"+getprocessor_type.get(i).get("processor_type")+"'>"+getprocessor_type.get(i).get("processor_type")+"</option>\r\n" ; 
						         
						     }
		     		 jsp+= "</select></td>\r\n" + 
		     		"                <td ><select id=\"ram_capi1\" name=\"ram_capi1\" class=\"form-control\">\r\n" + 
		     		"<option value=\"0\" selected=\"selected\">--Select--</option>\r\n" ; 
		     		 for(int i=0;i<getram.size();i++) {
						    jsp+= "<option value='"+getram.get(i).get("id")+"' name='"+getram.get(i).get("ram")+"'>"+getram.get(i).get("ram")+"</option>\r\n" ; 
						         
						     }
		     		jsp+= "</select>\r\n" + 
		     		"                </td>\r\n" + 
		     		"             \r\n" + 
		     		"                <td ><select id=\"hdd_capi1\" name=\"hdd_capi1\" class=\"form-control\">\r\n" +
		     		"<option value=\"0\" selected=\"selected\">--Select--</option>\r\n" ;
		     		for(int i=0;i<gethdd.size();i++) {
					    jsp+= "<option value='"+gethdd.get(i).get("id")+"' name='"+gethdd.get(i).get("hdd")+"'>"+gethdd.get(i).get("hdd")+"</option>\r\n" ; 
					         
					     }
		     		jsp+= "</select></td>\r\n" + 
		     		"									\r\n" + 
		     		"                <td  ><select id=\"operating_system1\" name=\"operating_system1\" class=\"form-control\">\r\n"+
		     		"<option value=\"0\" selected=\"selected\">--Select--</option>\r\n" ;
		     		
		     		for(int i=0;i<getos.size();i++) {
					    jsp+= "<option value='"+getos.get(i).get("id")+"' name='"+getos.get(i).get("os")+"'>"+getos.get(i).get("os")+"</option>\r\n" ; 
					         
					     }
		     		
		     		jsp+= "</select></td>\r\n" + 
				     		"							\r\n" + 
				     		 "<td  ><select id=\"office1\" name=\"office1\" class=\"form-control\">\r\n"+
				     		"<option value=\"0\" selected=\"selected\">--Select--</option>\r\n" ;
		     		
		     		for(int i=0;i<getoffice.size();i++) {
					    jsp+= "<option value='"+getoffice.get(i).get("id")+"' name='"+getoffice.get(i).get("office")+"'>"+getoffice.get(i).get("office")+"</option>\r\n" ; 
					         
					     }
		     		jsp+= "</select></td>\r\n" ;
		     		
		     		jsp+= " <td ><div class=\"col-md-8\" >\r\n" +
		     		"									<input id=\"antiviruscheckyes1\" name=\"antiviruscheck1\"  type=\"radio\" value=\"Yes\" checked=\"checked\"  onclick=\"anti_show(this,1);\">\r\n" + 
		     		"									&nbsp; <label for=\"yes\">Yes</label>&nbsp;\r\n" + 
		     		"									<input id=\"antiviruscheckno1\" name=\"antiviruscheck1\"  type=\"radio\" value=\"No\" onclick=\"anti_show(this,1);\">\r\n" + 
		     		"									&nbsp; <label for=\"no\">No</label>\r\n" + 
		     		"								</div></td>\r\n" ; 
		     		
				 
					 		
		     		jsp+= "<td ><select id=\"antivirus1\" name=\"antivirus1\" class=\"form-control\">\r\n"+
					 		"<option value=\"0\" selected=\"selected\">--Select--</option>\r\n" ;
		     		
					for(int i=0;i<getantivirus.size();i++) {
					    jsp+= "<option value='"+getantivirus.get(i).get("id")+"' name='"+getantivirus.get(i).get("antivirus")+"'>"+getantivirus.get(i).get("antivirus")+"</option>\r\n" ; 
					         
					     }
					
					
					jsp+= "</select></td>\r\n" + 
					 		"							\r\n" + 
					 		"<td ><select id=\"os_patch1\" name=\"os_patch1\" class=\"form-control\">\r\n"+
					 		"<option value=\"0\" selected=\"selected\">--Select--</option>\r\n" ;
					
					for(int i=0;i<getosfirmware.size();i++) {
					    jsp+= "<option value='"+getosfirmware.get(i).get("id")+"' name='"+getosfirmware.get(i).get("os_firmware")+"'>"+getosfirmware.get(i).get("os_firmware")+"</option>\r\n" ; 
					         
					     }
					
					
					
					jsp+= "</select></td>\r\n" + 
					 		"							\r\n" + 
					 		"<td ><select id=\"dply_envt1\" name=\"dply_envt1\" class=\"form-control\">\r\n"+
					 		"<option value=\"0\" selected=\"selected\">--Select--</option>\r\n" ;
					
					
					for(int i=0;i<getdplyenv.size();i++) {
					    jsp+= "<option value='"+getdplyenv.get(i).get("id")+"' name='"+getdplyenv.get(i).get("dply_env")+"'>"+getdplyenv.get(i).get("dply_env")+"</option>\r\n" ; 
					         
					     }
					jsp+= "</select></td>\r\n" ;
		     		
					jsp+="<td ><input id=\"ram_slot_qty1\" name=\"ram_slot_qty1\" min=\"1\" max=\"1000\" type=\"number\" class=\"form-control\" value=\"0\" autocomplete=\"off\"></td>\r\n" ; 
							
					jsp+=" <td ><div class=\"col-md-8\">\r\n" + 
		     		"										<input id=\"scan1\" name=\"cd_drive1\" type=\"radio\" value=\"Yes\">\r\n" + 
		     		"										&nbsp; <label for=\"yes\">Yes</label>&nbsp;\r\n" + 
		     		"										<input id=\"scan1\" name=\"cd_drive1\" checked=\"checked\" type=\"radio\" value=\"No\">\r\n" + 
		     		"										&nbsp; <label for=\"no\">No</label>\r\n" + 
		     		"									</div></td>\r\n";
				
					jsp+="<td ><div class=\"col-md-8\">\r\n" + 
		     		"									<input id=\"warrantycheckyes1\" name=\"warrantycheck\"  type=\"radio\" value=\"Yes\" checked=\"checked\" onclick=\"warrenty_show(this,1);\">\r\n" + 
		     		"									&nbsp; <label for=\"yes\">Yes</label>&nbsp;\r\n" + 
		     		"									<input id=\"warrantycheckno1\" name=\"warrantycheck\" type=\"radio\" value=\"No\" onclick=\"warrenty_show(this,1);\">\r\n" + 
		     		"									&nbsp; <label for=\"no\">No</label>\r\n" + 
		     		"								</div></td>\r\n";
		     		
					jsp+="<td ><input type=\"date\" name=\"warranty_dt1\" id=\"warranty1\" maxlength=\"10\"  class=\"form-control\"></td>\r\n";
		     		 
		     	 
					jsp+="<td >	<input type=\"text\" id=\"model_number1\" name=\"model_number1\" class=\"form-control \" autocomplete=\"off\" onkeypress=\"return isNumber(event);\"></td>\r\n"; 
 
					jsp+="<td >	<input type=\"text\" id=\"machine_number1\" name=\"machine_number1\" class=\"form-control\" autocomplete=\"off\" onkeypress=\"return isNumber(event);\"></td>\r\n" ;
		     
					jsp+="<td ><input  type=\"text\" id=\"mac_address1\" name=\"mac_address1\" class=\"form-control \" maxlength=\"17\" autocomplete=\"off\" onkeyup=\"makeMacAddress(this);\"></td>\r\n"; 
		     	
					jsp+="<td ><input  type=\"text\" id=\"ip_address1\" name=\"ip_address1\" maxlength=\"15\" class=\"form-control \" autocomplete=\"off\" onchange=\"ValidateIPaddress(this,1);\"></td>\r\n" ; 
		      
					
					
					
					jsp+="<td ><select id=\"s_state1\" name=\"s_state1\" class=\"form-control\" onchange=\"serviceStChange(this,1);\">\r\n" + 
		     		"														<option value=\"0\">--Select--</option>\r\n" + 
		     		"														\r\n" + 
		     		"															<option name=\"Serviceable\" value=\"1\">Serviceable</option>\r\n" + 
		     		"														\r\n" + 
		     		"															<option name=\"Un-serviceable\" value=\"2\">Un-serviceable</option>\r\n" + 
		     		"														\r\n" + 
		     		"													</select></td> \r\n";
					
					jsp+=" <td ><select id=\"unserviceable_state1\" name=\"unserviceable_state1\" class=\"form-control\">\r\n" + 
		     		"														<option value=\"0\" selected=\"selected\">--Select--</option>\r\n" + 
		     		"														\r\n" + 
		     		"															<option name=\"BER\" value=\"1\">BER</option>\r\n" + 
		     		"														\r\n" + 
		     		"															<option name=\"Minor Repair\" value=\"2\">Minor Repair</option>\r\n" + 
		     		"														\r\n" + 
		     		"															<option name=\"DOWNGRADED\" value=\"3\">DOWNGRADED</option>\r\n" + 
		     		"														\r\n" + 
		     		"													</select></td>\r\n" ;
					
					jsp+="<td ><input type=\"date\" name=\"unsv_from_dt1\" id=\"unsv_from_dt1\" maxlength=\"10\"  class=\"form-control\"></td> \r\n" ;
		     	
					
					
					jsp+="<td ><input type=\"text\" class=\"form-control\" id=\"b_cost1\" name=\"b_cost1\" onkeypress=\"return isNumber(event);\"></td>\r\n"; 
		     	
					jsp+="<td ><input type=\"date\" name=\"proc_dt1\" id=\"proc_date1\" maxlength=\"10\"></td>\r\n"; 
		     		
					jsp+= "<td ><select id=\"b_head1\" name=\"b_head1\" class=\"form-control b_head\" >\r\n"+
					 		"<option value=\"0\" selected=\"selected\">--Select--</option>\r\n" ;
					
					
					for(int i=0;i<getbudget.size();i++) {
					    jsp+= "<option value='"+getbudget.get(i).get("id")+"' name='"+getbudget.get(i).get("budget_head")+"' data-id='"+getbudget.get(i).get("id")+"' >"+getbudget.get(i).get("budget_head")+"</option>\r\n" ; 
					         
					     }
					
					
					jsp+= "</select></td>\r\n" + 
					 		"							\r\n" + 
					 		"<td ><select id=\"b_code1\" name=\"b_code1\" class=\"form-control b_code\">"+
					 		"<option value=\"0\" selected=\"selected\">--Select--</option>\r\n" ;
					
					
					for(int i=0;i<getbudget.size();i++) {
					    jsp+= "<option value='"+getbudget.get(i).get("id")+"' name='"+getbudget.get(i).get("budget_code")+"' class='\"+option "+getbudget.get(i).get("id")+"'>"+getbudget.get(i).get("budget_code")+"</option>\r\n" ; 
					         
					     }
					jsp+= "</select></td>\r\n" ;
		     
					jsp+="<td width='6%' onclick=\"formopen(1)\" id=\"id_add1\"  align=\"center\" /><i class=\"fa fa-plus\" aria-hidden=\"true\" style=\"color:#17a2b8;font-size:25px;\"></i></td>\r\n" ;
		     		
					jsp+="</tr>\r\n" + 
		     		"<!-- onkeypress=\"return validateInt(event)\"  -->\r\n" + 
		     		"\r\n" + 
		     		"\r\n" + 
		     		"\r\n" + 
		     		"\r\n" + 
		     		"         </tbody>\r\n" + 
		     		"  \r\n" + 
		     		"    </table>\r\n" + 
		     		"<div id=\"bottom_anchor\"></div>\r\n" + 
		     		"      <td class=\"center\" class=\"avgs\"><span id=\"avg\" hidden=\"true\">0</span></td>\r\n" + 
		     		"            <td class=\"center\" class=\"avgs\"><span id=\"avg1\" hidden=\"true\">0</span></td>\r\n" + 
		     		"\r\n" + 
		     		"</div>\r\n" + 
		     		"   <input type=\"hidden\" id=\"count\" name=\"count\" value=\"1\">\r\n" + 
		     		"</div> \r\n" + 
		     		"<!-- export table start -->\r\n" + 
		     		"<!--\r\n" + 
		     		"<div class=\"first_1\" id=\"first_1\" style=\"display: none;\" > \r\n" + 
		     		"     <table border=\"4\" id=\"table_show_1\" >\r\n" + 
		     		"   \r\n" + 
		     		"      <thead>\r\n" + 
		     		"        <tr>\r\n" + 
		     		"          <th>Month Validation</th>\r\n" + 
		     		"          <th>Sus No</th>\r\n" + 
		     		"          <th>Unit Name</th>\r\n" + 
		     		"          <th>Sub Unit Tested</th>\r\n" + 
		     		"          <th>Camp Layout</th>\r\n" + 
		     		"          <th>Briefing</th>\r\n" + 
		     		"          <th>Snap Sit</th>\r\n" + 
		     		"          <th>Op Role</th>\r\n" + 
		     		"          <th>Debriefing</th>\r\n" + 
		     		"          <th>Tug Of War</th>\r\n" + 
		     		"          <th>Social Aspects</th>\r\n" + 
		     		"          <th>Adm Task</th>\r\n" + 
		     		"           <!-- <th>Total</th> -->\r\n" + 
		     		"\r\n" + 
		     		"         \r\n" + 
		     		"        </tr>\r\n" + 
		     		"      </thead>\r\n" + 
		     		"      <tbody>  \r\n" + 
		     		"      </tbody>\r\n" + 
		     		"  \r\n" + 
		     		"    </table> \r\n" + 
		     		"  </div>\r\n" + 
		     		"\r\n" + 
		     		"  <div class=\"second_2\" id=\"second_2\"> \r\n" + 
		     		"\r\n" + 
		     		"\r\n" + 
		     		"<!-- style=\"display: none;\" -->\r\n" + 
		     		"<table border=\"4\" id=\"table_show_2\" style=\"display: none;\" > \r\n" + 
		     		"  <thead>\r\n" + 
		     		"    <tr>\r\n" + 
		     		"				\r\n" + 
		     		"                <th >Computing Assets Type</th>\r\n" + 
		     		"                <th >Make/Brand Name</th>\r\n" + 
		     		"                <th >Model Name</th>\r\n" + 
		     		"                <th >Processor Type</th>\r\n" + 
		     		"                <th >RAM Capacity</th>\r\n" + 
		     		"                <th >HDD Capacity</th>\r\n" + 
		     		"                <th >Operating System</th>\r\n" + 
		     		"                <th >Office</th>\r\n" + 
		     		"                <th >AntiVirus Installed</th>\r\n" + 
		     		"                <th >AntiVirus</th>\r\n" + 
		     		"                <th >OS/Firmware Activation and subsequent Patch Updation Mechanism</th>\r\n" + 
		     		"                <th >Dply Envt as Applicable</th>\r\n" + 
		     		"                <th >RAM Slot Quantity</th>\r\n" + 
		     		"                <th >CD Drive</th>\r\n" + 
		     		"                <th >Warranty</th>\r\n" + 
		     		"                <th >Warranty Upto</th>\r\n" + 
		     		"                <th >Model Number</th>\r\n" + 
		     		"                <th >Machine No</th>\r\n" + 
		     		"                <th >MAC Address</th>\r\n" + 
		     		"                <th >IP Address</th>\r\n" + 
		     		"                <th >Serviceable State</th>\r\n" + 
		     		"                <th >Un-serviceable State</th>\r\n" + 
		     		"				<th >Un-servicable from</th>\r\n" + 
		     		
		     		"				<th >Proc Cost</th>\r\n" + 
		     		"				<th >Proc Date</th>\r\n" + 
		     		"				<th >Budget Head</th>\r\n" + 
		     		"				<th >Budget Code</th>\r\n" + 
		     		"    </tr>\r\n" + 
		     		"  </thead>\r\n" + 
		     		"  <tbody>\r\n" + 
		     		"  </tbody> \r\n" + 
		     		"</table>\r\n" + 
		     		"   </div>\r\n" + 
		     		"<br/>\r\n" + 
		     		"<center><input type=\"btnExport\" class=\"btn btn-primary btn-sm\" onclick=\"return validate();return AddData_second()\" value=\"Export To Excel\" ></center>\r\n" + 
		     		"\r\n" + 
		     		" <br><br><br>\r\n" + 
		     		"<!-- export table end -->\r\n" + 
		     		"  </div>\r\n" + 
		     		"  \r\n" + 
		     		"\r\n" + 
		     		"\r\n" + 
		     		"</div>\r\n" + 
		     		"\r\n" + 
		     		"\r\n" + 
		     		"  </div>\r\n" + 
		     		"</div>\r\n" + 
		     		"<div class=\"digital_india\" style=\"position: fixed;bottom: 20px;\">\r\n" + 
		     		
		     		"  </div>\r\n" + 
		     		" \r\n" + 
		     		"</body>\r\n" + 
		     		"\r\n" + 
		     		"<!-- <script type=\"text/javascript\">\r\n" + 
		     		"        \r\n" + 
		     		"    </script> -->\r\n" + 
		     		"    \r\n" + 
		     		"<script>\r\n" + 
		     		"\r\n" + 
		     		"/*function moveScroll(){\r\n" + 
		     		"    var scroll = $(window).scrollTop();\r\n" + 
		     		"    var anchor_top = $(\"#show\").offset().top;\r\n" + 
		     		"    var anchor_bottom = $(\"#bottom_anchor\").offset().top;\r\n" + 
		     		"    if (scroll>anchor_top && scroll<anchor_bottom) {\r\n" + 
		     		"    clone_table = $(\"#clone\");\r\n" + 
		     		"    if(clone_table.length == 0){\r\n" + 
		     		"        clone_table = $(\"#show\").clone();\r\n" + 
		     		"        clone_table.attr('id', 'clone');\r\n" + 
		     		"        clone_table.css({position:'fixed', \r\n" + 
		     		"                  'border' : '4',\r\n" + 
		     		"                 'pointer-events': 'none',\r\n" + 
		     		"                 top:0});\r\n" + 
		     		"        clone_table.width($(\"#show\").width());\r\n" + 
		     		"        $(\"#addQuantity\").append(clone_table);\r\n" + 
		     		"        $(\"#clone\").css({visibility:'hidden'});\r\n" + 
		     		"        $(\"#clone thead\").css({visibility:'visible'});\r\n" + 
		     		"    }\r\n" + 
		     		"    } else {\r\n" + 
		     		"    $(\"#clone\").remove();\r\n" + 
		     		"    }\r\n" + 
		     		"}\r\n" + 
		     		"$(window).scroll(moveScroll);*/\r\n" + 
		     		" function moveScroll(){\r\n" + 
		     		"    var scroll = $(window).scrollTop();\r\n" + 
		     		"    var anchor_top = $(\"#show\").offset().top;\r\n" + 
		     		"    var anchor_bottom = $(\"#bottom_anchor\").offset().top;\r\n" + 
		     		"    if (scroll > anchor_top && scroll < anchor_bottom) {\r\n" + 
		     		"        clone_table = $(\"#clone\");\r\n" + 
		     		"        if(clone_table.length === 0) {          \r\n" + 
		     		"            clone_table = $(\"#show\").clone();\r\n" + 
		     		"            clone_table.attr({id: \"clone\"})\r\n" + 
		     		"            .css({\r\n" + 
		     		"                position: \"fixed\",\r\n" + 
		     		"                \"pointer-events\": \"none\",\r\n" + 
		     		"                 top:0\r\n" + 
		     		"            })\r\n" + 
		     		"            .width($(\"#show\").width());\r\n" + 
		     		"\r\n" + 
		     		"            $(\"#addQuantity\").append(clone_table);\r\n" + 
		     		"            // dont hide the whole table or you lose border style & \r\n" + 
		     		"            // actively match the inline width to the #maintable width if the \r\n" + 
		     		"            // container holding the table (window, iframe, div) changes width          \r\n" + 
		     		"            $(\"#clone\").width($(\"#show\").width());\r\n" + 
		     		"            // only the clone thead remains visible\r\n" + 
		     		"            $(\"#clone thead\").css({\r\n" + 
		     		"                visibility:\"visible\"\r\n" + 
		     		"            });\r\n" + 
		     		"            // clone tbody is hidden\r\n" + 
		     		"            $(\"#clone tbody\").css({\r\n" + 
		     		"                visibility:\"hidden\"\r\n" + 
		     		"            });\r\n" + 
		     		"            // add support for a tfoot element\r\n" + 
		     		"            // and hide its cloned version too\r\n" + 
		     		"            /*var footEl = $(\"#clone tfoot\");\r\n" + 
		     		"            if(footEl.length){\r\n" + 
		     		"                footEl.css({\r\n" + 
		     		"                    visibility:\"hidden\"\r\n" + 
		     		"                });\r\n" + 
		     		"            }*/\r\n" + 
		     		"        }\r\n" + 
		     		"    } \r\n" + 
		     		"    else {\r\n" + 
		     		"        $(\"#clone\").remove();\r\n" + 
		     		"    }\r\n" + 
		     		"}\r\n" + 
		     		"function validate()\r\n" + 
		     		"{\r\n" + 
		     		"\r\n" + 
		     		"\r\n" + 
		     		" var q = $(\"#count\").val();\r\n" + 
		     		"  for(var x = 1; x <= q ; x++){\r\n" + 
		     		" if($(\"#asset_type\"+x).val()==0 || $(\"#asset_type\"+x).val()==\"\"){\r\n" + 
		     		"		alert(\"Please Select Computing Assets Type \");\r\n" + 
		     		"		$(\"#asset_type\"+x).focus();\r\n" + 
		     		"		return false;\r\n" + 
		     		"		}\r\n" + 
		     		"		\r\n" + 
		     		"		if($(\"#make_name\"+x).val()==0 || $(\"#make_name\"+x).val()==\"\"){\r\n" + 
		     		"		alert(\"Please Select Make/Brand Name\");\r\n" + 
		     		"		$(\"#make_name\"+x).focus();\r\n" + 
		     		"		return false;\r\n" + 
		     		"		}\r\n" + 
		     		"		\r\n" + 
		     		"		\r\n" + 
		     		"		if($(\"#model_name\"+x).val()==0 || $(\"#model_name\"+x).val()==\"\"){\r\n" + 
		     		"		alert(\"Please Select Model Name\");\r\n" + 
		     		"		$(\"#model_name\"+x).focus();\r\n" + 
		     		"		return false;\r\n" + 
		     		"		}\r\n" + 
		     		"		\r\n" + 
		     		"		if($(\"#processor_type\"+x).val()==0 || $(\"#processor_type\"+x).val()==\"\"){\r\n" + 
		     		"		alert(\"Please Select Processor Type\");\r\n" + 
		     		"		$(\"#processor_type\"+x).focus();\r\n" + 
		     		"		return false;\r\n" + 
		     		"		}\r\n" + 
		     		"		\r\n" + 
		     		"		\r\n" + 
		     		"		if($(\"#ram_capi\"+x).val()==0 || $(\"#ram_capi\"+x).val()==\"\"){\r\n" + 
		     		"		alert(\"Please Select Ram Capacity\");\r\n" + 
		     		"		$(\"#ram_capi\"+x).focus();\r\n" + 
		     		"		return false;\r\n" + 
		     		"		}\r\n" + 
		     		"		\r\n" + 
		     		"		if($(\"#hdd_capi\"+x).val()==0 || $(\"#hdd_capi\"+x).val()==\"\"){\r\n" + 
		     		"		alert(\"Please Select HDD Capacity\");\r\n" + 
		     		"		$(\"#hdd_capi\"+x).focus();\r\n" + 
		     		"		return false;\r\n" + 
		     		"		}\r\n" + 
		     		"		\r\n" + 
		     		"		if($(\"#operating_system\"+x).val()==0 || $(\"#operating_system\"+x).val()==\"\"){\r\n" + 
		     		"		alert(\"Please Select Operating System\");\r\n" + 
		     		"		$(\"#operating_system\"+x).focus();\r\n" + 
		     		"		return false;\r\n" + 
		     		"		}\r\n" + 
		     		"		\r\n" + 
		     		"		\r\n" + 
		     		"		\r\n" + 
		     		"var antivirusChecked=$('input[name=\"antiviruscheck'+x+'\"]:checked').val();"+
		     		"\r\n" + 
		     		"	if(antivirusChecked.toUpperCase()==\"YES\"){\r\n" + 
		     		"		if($(\"#antivirus\"+x).val()==0 || $(\"#antivirus\"+x).val()==\"\"){\r\n" + 
		     		"			alert(\"Please Select AntiVirus  \");\r\n" + 
		     		"			$(\"#antivirus\"+x).focus();\r\n" + 
		     		"			return false;\r\n" + 
		     		"		}\r\n" + 
		     		"	}\r\n" + 
		     		"	else{\r\n" + 
		     		"		$(\"#antivirus\"+x).val('0');\r\n" + 
		     		"	}		\r\n" + 
		     		"		if($(\"#os_patch\"+x).val()==0 || $(\"#os_patch\"+x).val()==\"\"){\r\n" + 
		     		"		alert(\"Please Select OS/Firmware Activation and subsequent Patch Updation Mechanism\");\r\n" + 
		     		"		$(\"#os_patch\"+x).focus();\r\n" + 
		     		"		return false;\r\n" + 
		     		"		}\r\n" + 
		     		"		\r\n" + 
		     		"		\r\n" + 
		     		"		if($(\"#dply_envt\"+x).val()==0 || $(\"#dply_envt\"+x).val()==\"\"){\r\n" + 
		     		"		alert(\"Please Select Dply Envt as Applicable\");\r\n" + 
		     		"		$(\"#dply_envt\"+x).focus();\r\n" + 
		     		"		return false;\r\n" + 
		     		"		}\r\n" + 
		     		"		\r\n" + 
		     		"		if($(\"#model_number\"+x).val()==0 || $(\"#model_number\"+x).val()==\"\"){\r\n" + 
		     		"		alert(\"Please Enter Model Number\");\r\n" + 
		     		"		$(\"#model_number\"+x).focus();\r\n" + 
		     		"		return false;\r\n" + 
		     		"		}\r\n" + 
		     		"		\r\n" + 
		     		"		if($(\"#machine_number\"+x).val()==0 || $(\"#machine_number\"+x).val()==\"\"){\r\n" + 
		     		"		alert(\"Please Enter Machine Number\");\r\n" + 
		     		"		$(\"#machine_number\"+x).focus();\r\n" + 
		     		"		return false;\r\n" + 
		     		"		}\r\n" + 
		     		"		\r\n" + 
		     		"		\r\n" + 
		     		"		\r\n" + 
		     		"		if( $(\"#b_cost\"+x).val()==\"0\" ){\r\n" + 
		     		"		alert(\"Proc Cost Must be Greater Than Zero\");\r\n" + 
		     		"		$(\"#b_cost\"+x).focus();\r\n" + 
		     		"		return false;\r\n" + 
		     		"		} else if ($(\"#b_cost\"+x).val() > 1000000000) {\r\n" + 
		     		"		alert(\"Proc Cost cannot be greater Than 100 Crores\");\r\n" + 
		     		"		$(\"#b_cost\"+x).focus();\r\n" + 
		     		"		return false;\r\n" + 
		     		"		}\r\n" + 
		     		"		\r\n" + 
		     		"		\r\n" + 
		     		"		if( $(\"#s_state\"+x).val()==\"\" || $(\"#s_state\"+x).val()==\"0\"){\r\n" + 
		     		"		alert(\"Please Select Serviceable State\");\r\n" + 
		     		"		$(\"#s_state\"+x).focus();\r\n" + 
		     		"		return false;\r\n" + 
		     		"	}\r\n" + 
		     		"	\r\n" + 
		     		"	\r\n" + 
		     		"	if($(\"#s_state\"+x).val()==2){\r\n" + 
		     		"		if( $(\"#unserviceable_state\"+x).val()==\"\" || $(\"#unserviceable_state\"+x).val()==\"0\"){\r\n" + 
		     		"			alert(\"Please Select UN-Serviceable State\");\r\n" + 
		     		"			$(\"#unserviceable_state\"+x).focus();\r\n" + 
		     		"			return false;\r\n" + 
		     		"		}\r\n" + 
		     		"	\r\n" + 
		     		"		if( $(\"#unsv_from_dt\"+x).val()==\"\" || $(\"#unsv_from_dt\"+x).val()==\"0\"){\r\n" + 
		     		"			alert(\"Please Select UN-Serviceable From Date\");\r\n" + 
		     		"			$(\"#unsv_from_dt\"+x).focus();\r\n" + 
		     		"			return false;\r\n" + 
		     		"		}\r\n" + 
		     		"	\r\n" + 
		     		"}	if( $(\"#b_head\"+x).val()==\"0\"){\r\n" + 
		     		"		alert(\"Please Select Budget Head\");\r\n" + 
		     		"		$(\"#b_head\"+x).focus();\r\n" + 
		     		"		return false;\r\n" + 
		     		"	}\r\n" + 
		     		"	\r\n" + 
		     		"	if( $(\"#b_code\"+x).val()==\"0\"){\r\n" + 
		     		"		alert(\"Please Select Budget Code\");\r\n" + 
		     		"		$(\"#b_code\"+x).focus();\r\n" + 
		     		"		return false;\r\n" + 
		     		"	}\r\n" + 
		     		" }\r\n" + 
		     		" return AddData_second();\r\n" + 
		     		"}"+
		     		"\r\n" + 
		     		"\r\n" + 
		     		"$(window).scroll(moveScroll);\r\n" + 
		     		"\r\n" + 
		     		"\r\n" + 
		     		"\r\n" + 
		     		"    $(\".b_code option\").hide();\r\n" + 
			     	"		     				$(document).on(\"change\", \".b_head\", function(){\r\n" + 
			     	"		     					\r\n" + 
			     	"		     						var el = $(this);\r\n" + 
			     	"		     		\r\n" + 
			     	"		     			var id = el.find(\"option:selected\").data(\"id\");\r\n" + 
			     	"		     							\r\n" + 
			     	"		     							el.closest(\"tr\").find(\".b_code\").find(\"option\").hide();\r\n" + 
			     	"		     							el.closest(\"tr\").find(\".b_code\").find(\".\"+id).show();\r\n" + 
			     	"		     					}); "+
		     		" x=1;//declare export variable \r\n" +
		     		"function formopen_re(R){\r\n" + 
					"   $(\"tr#tr_id\"+R).remove();\r\n" + 
					"   R = R-1;\r\n" + 
					"// x = x-1;\r\n" + 
					"   $(\"input#count\").val(R);\r\n" + 
					"   $(\"#id_add\"+R).show();\r\n" + 
					"   $(\"#id_remove\"+R).show();\r\n" + 
					"   getPersCount();\r\n" +  
					"}\r\n" ;
		     		jsp+="function formopen(x){\r\n" + 
		     		"\r\n" + 
		     		"//document.getElementById('show').style.width=\"100%\";\r\n" + 
		     		"\r\n" + 
		     		"//endurun(x);\r\n" + 
		     		"\r\n" + 
		     		"\r\n" + 
		     		
		     		"if($(\"#asset_type\"+x).val()==0 || $(\"#asset_type\"+x).val()==\"\"){\r\n" + 
		     		"		alert(\"Please Select Computing Assets Type \");\r\n" + 
		     		"		$(\"#asset_type\"+x).focus();\r\n" + 
		     		"		return false;\r\n" + 
		     		"		}\r\n" + 
		     		"		\r\n" + 
		     		"		if($(\"#make_name\"+x).val()==0 || $(\"#make_name\"+x).val()==\"\"){\r\n" + 
		     		"		alert(\"Please Select Make/Brand Name\");\r\n" + 
		     		"		$(\"#make_name\"+x).focus();\r\n" + 
		     		"		return false;\r\n" + 
		     		"		}\r\n" + 
		     		"		\r\n" + 
		     		"		\r\n" + 
		     		"		if($(\"#model_name\"+x).val()==0 || $(\"#model_name\"+x).val()==\"\"){\r\n" + 
		     		"		alert(\"Please Select Model Name\");\r\n" + 
		     		"		$(\"#model_name\"+x).focus();\r\n" + 
		     		"		return false;\r\n" + 
		     		"		}\r\n" + 
		     		"		\r\n" + 
		     		"		if($(\"#processor_type\"+x).val()==0 || $(\"#processor_type\"+x).val()==\"\"){\r\n" + 
		     		"		alert(\"Please Select Processor Type\");\r\n" + 
		     		"		$(\"#processor_type\"+x).focus();\r\n" + 
		     		"		return false;\r\n" + 
		     		"		}\r\n" + 
		     		"		\r\n" + 
		     		"		\r\n" + 
		     		"		if($(\"#ram_capi\"+x).val()==0 || $(\"#ram_capi\"+x).val()==\"\"){\r\n" + 
		     		"		alert(\"Please Select Ram Capacity\");\r\n" + 
		     		"		$(\"#ram_capi\"+x).focus();\r\n" + 
		     		"		return false;\r\n" + 
		     		"		}\r\n" + 
		     		"		\r\n" + 
		     		"		if($(\"#hdd_capi\"+x).val()==0 || $(\"#hdd_capi\"+x).val()==\"\"){\r\n" + 
		     		"		alert(\"Please Select HDD Capacity\");\r\n" + 
		     		"		$(\"#hdd_capi\"+x).focus();\r\n" + 
		     		"		return false;\r\n" + 
		     		"		}\r\n" + 
		     		"		\r\n" + 
		     		"		if($(\"#operating_system\"+x).val()==0 || $(\"#operating_system\"+x).val()==\"\"){\r\n" + 
		     		"		alert(\"Please Select Operating System\");\r\n" + 
		     		"		$(\"#operating_system\"+x).focus();\r\n" + 
		     		"		return false;\r\n" + 
		     		"		}\r\n" + 
		     		"		\r\n" + 
		     		"		\r\n" + 
		     		"		\r\n" + 
		     	"var antivirusChecked=$('input[name=\"antiviruscheck'+x+'\"]:checked').val();"+
		     		"	\r\n" + 
		     		"	if(antivirusChecked.toUpperCase()==\"YES\"){\r\n" + 
		     		"		if($(\"#antivirus\"+x).val()==0 || $(\"#antivirus\"+x).val()==\"\"){\r\n" + 
		     		"			alert(\"Please Select AntiVirus  \");\r\n" + 
		     		"			$(\"#antivirus\"+x).focus();\r\n" + 
		     		"			return false;\r\n" + 
		     		"		}\r\n" + 
		     		"	}\r\n" + 
		     		"	else{\r\n" + 
		     		"		$(\"#antivirus\"+x).val('0');\r\n" + 
		     		"	}"+
		     		"		\r\n" + 
		     		"		if($(\"#os_patch\"+x).val()==0 || $(\"#os_patch\"+x).val()==\"\"){\r\n" + 
		     		"		alert(\"Please Select OS/Firmware Activation and subsequent Patch Updation Mechanism\");\r\n" + 
		     		"		$(\"#os_patch\"+x).focus();\r\n" + 
		     		"		return false;\r\n" + 
		     		"		}\r\n" + 
		     		"		\r\n" + 
		     		"		\r\n" + 
		     		"		if($(\"#dply_envt\"+x).val()==0 || $(\"#dply_envt\"+x).val()==\"\"){\r\n" + 
		     		"		alert(\"Please Select Dply Envt as Applicable\");\r\n" + 
		     		"		$(\"#dply_envt\"+x).focus();\r\n" + 
		     		"		return false;\r\n" + 
		     		"		}\r\n" + 
		     		"		\r\n" + 
		     		"		if($(\"#model_number\"+x).val()==0 || $(\"#model_number\"+x).val()==\"\"){\r\n" + 
		     		"		alert(\"Please Enter Model Number\");\r\n" + 
		     		"		$(\"#model_number\"+x).focus();\r\n" + 
		     		"		return false;\r\n" + 
		     		"		}\r\n" + 
		     		"		\r\n" + 
		     		"		if($(\"#machine_number\"+x).val()==0 || $(\"#machine_number\"+x).val()==\"\"){\r\n" + 
		     		"		alert(\"Please Enter Machine Number\");\r\n" + 
		     		"		$(\"#machine_number\"+x).focus();\r\n" + 
		     		"		return false;\r\n" + 
		     		"		}\r\n" + 
		     		"		\r\n" + 
		     		"		\r\n" + 
		     		"		\r\n" + 
		     		"		if( $(\"#b_cost\"+x).val()==\"0\" ){\r\n" + 
		     		"		alert(\"Proc Cost Must be Greater Than Zero\");\r\n" + 
		     		"		$(\"#b_cost\"+x).focus();\r\n" + 
		     		"		return false;\r\n" + 
		     		"		} else if ($(\"#b_cost\"+x).val() > 1000000000) {\r\n" + 
		     		"		alert(\"Proc Cost cannot be greater Than 100 Crores\");\r\n" + 
		     		"		$(\"#b_cost\"+x).focus();\r\n" + 
		     		"		return false;\r\n" + 
		     		"		}\r\n" + 
		     		"		\r\n" + 
		     		"		\r\n" +
		     		
		     		"		if( $(\"#s_state\"+x).val()==\"\" || $(\"#s_state\"+x).val()==\"0\"){\r\n" + 
		     		"		alert(\"Please Select Serviceable State\");\r\n" + 
		     		"		$(\"#s_state\"+x).focus();\r\n" + 
		     		"		return false;\r\n" + 
		     		"	}\r\n" + 
		     		"	\r\n" + 
		     		"	\r\n" + 
		     		"	if($(\"#s_state\"+x).val()==2){\r\n" + 
		     		"		if( $(\"#unserviceable_state\"+x).val()==\"\" || $(\"#unserviceable_state\"+x).val()==\"0\"){\r\n" + 
		     		"			alert(\"Please Select UN-Serviceable State\");\r\n" + 
		     		"			$(\"#unserviceable_state\"+x).focus();\r\n" + 
		     		"			return false;\r\n" + 
		     		"		}\r\n" + 
		     		"	\r\n" + 
		     		"		if( $(\"#unsv_from_dt\"+x).val()==\"\" || $(\"#unsv_from_dt\"+x).val()==\"0\"){\r\n" + 
		     		"			alert(\"Please Select UN-Serviceable From Date\");\r\n" + 
		     		"			$(\"#unsv_from_dt\"+x).focus();\r\n" + 
		     		"			return false;\r\n" + 
		     		"		}\r\n" + 
		     		"	\r\n" + 
		     		"}"+
		     		
		     		"	if( $(\"#b_head\"+x).val()==\"0\"){\r\n" + 
		     		"		alert(\"Please Select Budget Head\");\r\n" + 
		     		"		$(\"#b_head\"+x).focus();\r\n" + 
		     		"		return false;\r\n" + 
		     		"	}\r\n" + 
		     		"	\r\n" + 
		     		"	if( $(\"#b_code\"+x).val()==\"0\"){\r\n" + 
		     		"		alert(\"Please Select Budget Code\");\r\n" + 
		     		"		$(\"#b_code\"+x).focus();\r\n" + 
		     		"		return false;\r\n" + 
		     		"	}"+
		     		
		     		"\r\n" + 
		     		"   $(\"#id_add\"+x).hide();\r\n" + 
		     		"   $(\"#id_remove\"+x).hide();\r\n" + 
		     		"  \r\n" + 
		     		"   x= x+1;\r\n" + 
		     		"\r\n" + 
		     		"   \r\n" + 
		     		"\r\n" + 
		     		"     $(\"input#count\").val(x);\r\n" + 
		     		"     $(\"table#show\").append('<tr id=\"tr_id'+x+'\"><td align=\"center\" width=\"1%;\">'+x+'</td>'\r\n" + 
		     		"	\r\n" + 
		     		"	 \r\n" + 
		     		"        +'<td><select id=\"asset_type'+x+'\" name=\"asset_type'+x+'\" class=\"form-control\" >'"+
		     		
					  "+'<option value=\"0\">--Select--</option>'\r\n" ; 
									    for(int i=0;i<getasset_type1.size();i++) {
										    jsp+= "+'<option value=\""+getasset_type1.get(i).get("id")+"\" name=\""+getasset_type1.get(i).get("assets_name")+"\">"+getasset_type1.get(i).get("assets_name")+"</option>'\r\n" ; 
										         
										     }
										    jsp+= "+'</select>'\r\n"+
										     "+'</td> '";
										    
							 jsp+="+' <td ><select id=\"make_name'+x+'\" name=\"make_name'+x+'\" class=\"form-control\" >'";
								   jsp+= "+'<option value=\"0\">--Select--</option>'\r\n" ; 
										     for(int i=0;i<getmake.size();i++) {
										    jsp+= "+'<option value=\""+getmake.get(i).get("id")+"\" name=\""+getmake.get(i).get("make_name")+"\">"+getmake.get(i).get("make_name")+"</option>'\r\n" ; 
										         
										     }
										    jsp+= "+'</select>'\r\n" ;
										    jsp+= "+'</td>'\r\n" ;
		     		
										    		
										    jsp+= "+'<td ><select id=\"model_name'+x+'\" name=\"model_name'+x+'\" class=\"form-control\">'";
								     		 jsp+= "+'<option value=\"0\">--Select--</option>'\r\n" ; 
										     for(int i=0;i<getmodel.size();i++) {
										    jsp+= "+'<option value=\""+getmodel.get(i).get("id")+"\" name=\""+getmodel.get(i).get("model_name")+"\">"+getmodel.get(i).get("model_name")+"</option>'\r\n" ; 
										         
										     }
										    jsp+= "+'</select> </td>'\r\n";
										     
										    		
		     		
										    		
				jsp+="+'<td><select id=\"processor_type'+x+'\" name=\"processor_type'+x+'\" class=\"form-control\">'\r\n" + 
		     		"	+'<option value=\"0\" selected=\"selected\">--Select--</option>'\r\n" ; 
		     		 for(int i=0;i<getprocessor_type.size();i++) {
						    jsp+= "+'<option value=\""+getprocessor_type.get(i).get("id")+"\" name=\""+getprocessor_type.get(i).get("processor_type")+"\">"+getprocessor_type.get(i).get("processor_type")+"</option>'\r\n" ; 
						         
						     }
		     		 jsp+= "+'</select></td>'\r\n" ; 
		     		
		     				 
		     		jsp+="  +'<td ><select id=\"ram_capi'+x+'\" name=\"ram_capi'+x+'\" class=\"form-control\">'\r\n" + 
					"+'<option value=\"0\" selected=\"selected\">--Select--</option>'\r\n" ; 
					 for(int i=0;i<getram.size();i++) {
					    jsp+= "+'<option value=\""+getram.get(i).get("id")+"\" name=\""+getram.get(i).get("ram")+"\">"+getram.get(i).get("ram")+"</option>'\r\n" ; 
					         
					     }
					jsp+= "+'</select></td>'\r\n";
				               
				
					jsp+=" +'<td ><select id=\"hdd_capi'+x+'\" name=\"hdd_capi'+x+'\" class=\"form-control\">'\r\n" +
						"+'<option value=\"0\" selected=\"selected\">--Select--</option>'\r\n" ;
						for(int i=0;i<gethdd.size();i++) {
					    jsp+= "+'<option value=\""+gethdd.get(i).get("id")+"\" name=\""+gethdd.get(i).get("hdd")+"\">"+gethdd.get(i).get("hdd")+"</option>'\r\n" ; 
					         
					     }
						jsp+= "+'</select></td>'\r\n" ;
		     		
		     		
		     		
						jsp+="+'<td  ><select id=\"operating_system'+x+'\" name=\"operating_system'+x+'\" class=\"form-control\">'\r\n"+
					     		"+'<option value=\"0\" selected=\"selected\">--Select--</option>'\r\n" ;
			     		
			     		for(int i=0;i<getos.size();i++) {
						    jsp+= "+'<option value=\""+getos.get(i).get("id")+"\" name=\""+getos.get(i).get("os")+"\">"+getos.get(i).get("os")+"</option>'\r\n" ; 
						         
						     }
			     		
			     		jsp+= "+'</select></td>'\r\n" ;
								
								
			     		jsp+= "+'<td  ><select id=\"office'+x+'\" name=\"office'+x+'\" class=\"form-control\">'\r\n"+
							"+'<option value=\"0\" selected=\"selected\">--Select--</option>'\r\n" ;
						
						for(int i=0;i<getoffice.size();i++) {
						jsp+= "+'<option value=\""+getoffice.get(i).get("id")+"\" name=\""+getoffice.get(i).get("office")+"\">"+getoffice.get(i).get("office")+"</option>'\r\n" ; 
						 
						}
						jsp+= "+'</select></td>'\r\n" ;
		     		
					jsp+= " +'<td ><div class=\"col-md-8\">'\r\n" +
					 		"+'<input id=\"antiviruscheckyes'+x+'\" name=\"antiviruscheck'+x+'\" type=\"radio\" value=\"Yes\"  checked=\"checked\" onclick=\"anti_show(this,'+x+');\">'\r\n" + 
					 		"+'&nbsp; <label for=\"yes\">Yes</label>&nbsp'"+
					 		"+'<input id=\"antiviruscheckno'+x+'\" name=\"antiviruscheck'+x+'\"  type=\"radio\" value=\"No\"  onclick=\"anti_show(this,'+x+');\">'\r\n" + 
					 		"+'&nbsp; <label for=\"no\">No</label>'\r\n" + 
					 		"+'</div></td>'\r\n" ;
								
								
					jsp+= "+'<td ><select id=\"antivirus'+x+'\" name=\"antivirus'+x+'\" class=\"form-control\">'\r\n"+
					 		"+'<option value=\"0\" selected=\"selected\">--Select--</option>'\r\n" ;
						
					for(int i=0;i<getantivirus.size();i++) {
					    jsp+= "+'<option value=\""+getantivirus.get(i).get("id")+"\" name=\""+getantivirus.get(i).get("antivirus")+"\">"+getantivirus.get(i).get("antivirus")+"</option>'\r\n" ; 
					         
					     }
					
					
					jsp+= "+'</select></td>'\r\n" ;
					
					
					
					jsp+= "+'<td ><select id=\"os_patch'+x+'\" name=\"os_patch'+x+'\" class=\"form-control\">'\r\n"+
			 		"+'<option value=\"0\" selected=\"selected\">--Select--</option>'\r\n" ;
			
						for(int i=0;i<getosfirmware.size();i++) {
						    jsp+= "+'<option value=\""+getosfirmware.get(i).get("id")+"\" name=\""+getosfirmware.get(i).get("os_firmware")+"\">"+getosfirmware.get(i).get("os_firmware")+"</option>'\r\n" ; 
						         
						     }
			
								jsp+= "+'</select></td>'\r\n" ;
		     		
		     		
		     		
					jsp+="+'<td ><select id=\"dply_envt'+x+'\" name=\"dply_envt'+x+'\" class=\"form-control\">'\r\n"+
						"+'<option value=\"0\" selected=\"selected\">--Select--</option>'\r\n" ;
					
					
					for(int i=0;i<getdplyenv.size();i++) {
					jsp+= "+'<option value=\""+getdplyenv.get(i).get("id")+"\" name=\""+getdplyenv.get(i).get("dply_env")+"\">"+getdplyenv.get(i).get("dply_env")+"</option>'\r\n" ; 
					 
					}
					jsp+= "+'</select></td>'\r\n" ;
		     		
		     		
		     		
							jsp+="+'<td ><input id=\"ram_slot_qty'+x+'\" name=\"ram_slot_qty'+x+'\" min=\"1\" max=\"1000\" type=\"number\" class=\"form-control\" value=\"0\" autocomplete=\"off\"></td>'\r\n" ; 
		     		
							
							jsp+="+' <td ><div class=\"col-md-8\">'\r\n" + 
						     		"+'<input id=\"scan'+x+'\" name=\"cd_drive'+x+'\" type=\"radio\" value=\"Yes\">'\r\n" + 
						     		"+'&nbsp; <label for=\"yes\">Yes</label>&nbsp;'\r\n" + 
						     		"+'<input id=\"scan'+x+'\" name=\"cd_drive'+x+'\" checked=\"checked\" type=\"radio\" value=\"No\">'\r\n" + 
						     		"+'&nbsp; <label for=\"no\">No</label>'\r\n" + 
						     		"+'</div></td>'\r\n";
								
									jsp+="+'<td ><div class=\"col-md-8\" >'\r\n" + 
						     		"+'<input id=\"warrantycheckyes'+x+'\" name=\"warrantycheck'+x+'\"  type=\"radio\" value=\"Yes\" checked=\"checked\" onclick=\"warrenty_show(this,'+x+');\">'\r\n" + 
						     		"+'&nbsp; <label for=\"yes\">Yes</label>&nbsp;'\r\n" + 
						     		"+'<input id=\"warrantycheckno'+x+'\" name=\"warrantycheck'+x+'\" type=\"radio\" value=\"No\" onclick=\"warrenty_show(this,'+x+');\">'\r\n" + 
						     		"+'&nbsp; <label for=\"no\">No</label>'\r\n" + 
						     		"+'</div></td>'\r\n";
							
							
							
		     		
									jsp+="+'<td ><input type=\"date\" name=\"warranty_dt'+x+'\" id=\"warranty'+x+'\" maxlength=\"10\"  class=\"form-control\"></td>'\r\n";
						     		 
							     	 
									jsp+="+'<td ><input type=\"text\" id=\"model_number'+x+'\" name=\"model_number'+x+'\" class=\"form-control \" autocomplete=\"off\" onkeypress=\"return isNumber(event);\"></td>'\r\n"; 
				 
									jsp+="+'<td ><input type=\"text\" id=\"machine_number'+x+'\" name=\"machine_number'+x+'\" class=\"form-control\" autocomplete=\"off\" onkeypress=\"return isNumber(event);\"></td>'\r\n" ;
						     
									jsp+="+'<td ><input  type=\"text\" id=\"mac_address'+x+'\" name=\"mac_address'+x+'\" class=\"form-control \" maxlength=\"17\" autocomplete=\"off\" onkeyup=\"makeMacAddress(this);\"></td>'\r\n"; 
						     	
									jsp+="+'<td ><input  type=\"text\" id=\"ip_address'+x+'\" name=\"ip_address'+x+'\" maxlength=\"15\" class=\"form-control \" autocomplete=\"off\" onchange=\"ValidateIPaddress(this,'+x+');\"></td>'\r\n" ;
		     		
											jsp+="+'<td ><select id=\"s_state'+x+'\" name=\"s_state'+x+'\" class=\"form-control\" onchange=\"serviceStChange(this,'+x+');\">'\r\n" + 
										     		"+'<option value=\"0\">--Select--</option>'\r\n" + 
										  
										     		"+'<option name=\"Serviceable\" value=\"1\">Serviceable</option>'\r\n" + 
										     	
										     		"+'<option name=\"Un-serviceable\" value=\"2\">Un-serviceable</option>'\r\n" + 
										 
										     		"+'</select></td>' \r\n";
													
													jsp+="+' <td ><select id=\"unserviceable_state'+x+'\" name=\"unserviceable_state'+x+'\" class=\"form-control\">'\r\n" + 
										     		"+'<option value=\"0\" selected=\"selected\">--Select--</option>'\r\n" + 
										     		 
										     		"+'<option name=\"BER\" value=\"1\">BER</option>'\r\n" + 
								
										     		"+'<option name=\"Minor Repair\" value=\"2\">Minor Repair</option>'\r\n" + 
										    
										     		"+'<option name=\"DOWNGRADED\" value=\"3\">DOWNGRADED</option>'\r\n" + 
										     
										     		"+'</select></td>'\r\n" ;									
											
										
		     		
						jsp+="+'<td ><input type=\"date\" name=\"unsv_from_dt'+x+'\" id=\"unsv_from_dt'+x+'\" maxlength=\"10\"  class=\"form-control\"></td> '\r\n" ;
													     	
						
															
						jsp+="+'<td ><input type=\"text\" class=\"form-control-sm\" id=\"b_cost'+x+'\" name=\"b_cost'+x+'\" onkeypress=\"return isNumber(event);\"></td>'\r\n"; 
												     	
						jsp+="+'<td ><input type=\"date\" name=\"proc_dt'+x+'\" id=\"proc_date'+x+'\" maxlength=\"10\"></td>'\r\n";
							
								
		     		
					jsp+= "+'<td ><select id=\"b_head'+x+'\" name=\"b_head'+x+'\" class=\"form-control b_head\" >'\r\n"+
					 		"+'<option value=\"0\" selected=\"selected\">--Select--</option>'\r\n" ;
					
					//add comp map1
					for(int i=0;i<getbudget.size();i++) {
					    jsp+= "+'<option value=\""+getbudget.get(i).get("id")+"\" name=\""+getbudget.get(i).get("budget_head")+"\" data-id=\""+getbudget.get(i).get("id")+"\">"+getbudget.get(i).get("budget_head")+"</option>'\r\n" ; 
					}
					
					
					jsp+= "+'</select></td>'\r\n" + 
					 		"+'<td ><select id=\"b_code'+x+'\" name=\"b_code'+x+'\" class=\"form-control b_code\">'"+
					 		"+'<option value=\"0\" selected=\"selected\">--Select--</option>'\r\n" ;
					
					//add comp map2

					for(int i=0;i<getbudget.size();i++) {
						
					    jsp+= "+'<option value=\""+getbudget.get(i).get("id")+"\" name=\""+getbudget.get(i).get("budget_code")+"\" class=\""+"option "+getbudget.get(i).get("id")+"\">"+getbudget.get(i).get("budget_code")+"</option>'\r\n" ; 
					         
					     }
					jsp+= "+'</select></td>'\r\n" +
		     		
		     		
		     		"+'<td  align=\"center\" ><i class=\"fa fa-plus\" style=\"color:#17a2b8;font-size:25px;\"  id = \"id_add'+x+'\" onclick=\"formopen('+x+');\"></i>&nbsp;&nbsp;&nbsp;<i class=\"fa fa-minus\"  style=\"color:red;font-size:25px;\" id = \"id_remove'+x+'\" onclick=\"formopen_re('+x+');\"></i></td>'+'</tr>');\r\n" + 
		     		"    \r\n" + 
		     		"\r\n" + 
		     		"    }\r\n" + 
		     		"\r\n" + 
		     		"\r\n" + 
		     		"\r\n" + 
		     		"/*function validateInt(key) {\r\n" + 
		     		"  \r\n" + 
		     		"            var keycode = (key.which) ? key.which : key.keyCode;\r\n" + 
		     		"           \r\n" + 
		     		"            if (!(keycode == 8 || keycode == 46) && (keycode < 48 || keycode > 57)) {\r\n" + 
		     		"                return false;\r\n" + 
		     		"            }\r\n" + 
		     		"            else {\r\n" + 
		     		"                var parts = key.srcElement.value.split('.');\r\n" + 
		     		"                if (parts.length > 1 && keycode == 46)\r\n" + 
		     		"                    return false;\r\n" + 
		     		"                return true;\r\n" + 
		     		"            }\r\n" + 
		     		"}*/\r\n" + 
		     		"\r\n" + 
		     		"function validateFloatKeyPress(el, evt) {\r\n" + 
		     		"    var charCode = (evt.which) ? evt.which : event.keyCode;\r\n" + 
		     		"    var number = el.value.split('.');\r\n" + 
		     		"    if (charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57)) {\r\n" + 
		     		"        return false;\r\n" + 
		     		"    }\r\n" + 
		     		"    //just one dot\r\n" + 
		     		"    if(number.length>1 && charCode == 46){\r\n" + 
		     		"         return false;\r\n" + 
		     		"    }\r\n" + 
		     		"    //get the carat position\r\n" + 
		     		"    var caratPos = getSelectionStart(el);\r\n" + 
		     		"    var dotPos = el.value.indexOf(\".\");\r\n" + 
		     		"    if( caratPos > dotPos && dotPos>-1 && (number[1].length > 1)){\r\n" + 
		     		"        return false;\r\n" + 
		     		"    }\r\n" + 
		     		"    return true;\r\n" + 
		     		"}\r\n" + 
		     		"\r\n" + 
		     		"\r\n" + 
		     		"\r\n" + 
		     		"\r\n" + 
		     		"//thanks: http://javascript.nwbox.com/cursor_position/\r\n" + 
		     		"function getSelectionStart(o) {\r\n" + 
		     		"  if (o.createTextRange) {\r\n" + 
		     		"    var r = document.selection.createRange().duplicate()\r\n" + 
		     		"    r.moveEnd('character', o.value.length)\r\n" + 
		     		"    if (r.text == '') return o.value.length\r\n" + 
		     		"    return o.value.lastIndexOf(r.text)\r\n" + 
		     		"  } else return o.selectionStart\r\n" + 
		     		"}\r\n" + 
		     		"\r\n" + 
		     		"\r\n" + 
		     		"\r\n" + 
		     		"\r\n" + 
		     		"\r\n" + 
		     		"  \r\n" + 
		     		"function CheckColors(val,rowid){\r\n" + 
		     		"/*if(rowid == \"0\"){\r\n" + 
		     		"rowid =\"\";\r\n" + 
		     		"}*/\r\n" + 
		     		"\r\n" + 
		     		"document.getElementById(\"medical_category_remarks\"+rowid).style.width='200px';\r\n" + 
		     		"//alert(document.getElementById(\"medical_category_remarks\"+rowid));\r\n" + 
		     		" if(val=='--SELECT--'||val=='LMC'){\r\n" + 
		     		"  document.getElementById(\"medical_category_remarks\"+rowid).style.display='block';\r\n" + 
		     		"\r\n" + 
		     		" }else  {\r\n" + 
		     		"   document.getElementById(\"medical_category_remarks\"+rowid).style.display='none';\r\n" + 
		     		"   }\r\n" + 
		     		"}\r\n" + 
		     		"\r\n" + 
		     		"\r\n" + 
		     		"\r\n" + 
		     		" ///////////////////////////////////////////////////////////////////////////////////////export code start\r\n" + 
		     		"\r\n" + 
		     		"\r\n" + 
		     		"\r\n" + 
		     		" function AddData_second() {\r\n" + 
		     		"\r\n" + 
		     		"\r\n" + 
		     		"  $(\"table#table_show_2  tbody\").empty();\r\n" + 
		     		"\r\n" + 
		     		"\r\n" + 
		     		"\r\n" + 
		     		"\r\n" + 
		     		"  rc=$(\"#count\").val();\r\n" + 
		     		"\r\n" + 
		     		"\r\n" + 
		     		"  var asset_type ;\r\n" + 
		     		"  var make_name;\r\n" + 
		     		"  var model_name;\r\n" + 
		     		"  var processor_type;\r\n" + 
		     		"  var ram_capi;\r\n" + 
		     		"  var hdd_capi;\r\n" + 
		     		"  var operating_system;\r\n" + 
		     		"  var office;\r\n" + 
		     		"  var antiviruscheck;\r\n" + 
		     		"  var antivirus;\r\n" + 
		     		"  var os_patch;\r\n" + 
		     		"  var dply_envt;\r\n" + 
		     		"  var ram_slot_qty;\r\n" + 
		     		"  var scan;\r\n" + 
		     		"  var warrantycheck;\r\n" + 
		     		"  var warranty;\r\n" + 
		     		"  var model_number;\r\n" + 
		     		"  var machine_number;\r\n" + 
		     		"  var mac_address;\r\n" + 
		     		"  var ip_address;\r\n" + 
		     		"  var s_state;\r\n" + 
		     		"  var unserviceable_state;\r\n" + 
		     		"  var unsv_from_dt;\r\n" + 
		     		
		     		"   var b_cost;\r\n" + 
		     		"    var proc_date;\r\n" + 
		     		"	 var b_head;\r\n" + 
		     		"	  var b_code;\r\n" + 
		     		"\r\n" + 
		     		"\r\n" + 
		     		"  for (var colIndex1 = 1; colIndex1 <=parseInt(rc); colIndex1++) {\r\n" + 
		     		"   \r\n" + 
		     		"\r\n" + 
		     		"  var rows = \"\";\r\n" + 
		     		" \r\n" + 
		     		"   asset_type =  document.getElementById(\"asset_type\"+colIndex1).value;\r\n" + 
		     		"   make_name=  document.getElementById(\"make_name\"+colIndex1).value;\r\n" + 
		     		"   model_name=  document.getElementById(\"model_name\"+colIndex1).value;\r\n" + 
		     		"   processor_type =  document.getElementById(\"processor_type\"+colIndex1).value;\r\n" + 
		     		"   ram_capi=  document.getElementById(\"ram_capi\"+colIndex1).value;\r\n" + 
		     		"   hdd_capi=  document.getElementById(\"hdd_capi\"+colIndex1).value;\r\n" + 
		     		"   operating_system=  document.getElementById(\"operating_system\"+colIndex1).value;\r\n" + 
		     		"   office=  document.getElementById(\"office\"+colIndex1).value;\r\n" + 
		     		"if(document.getElementById('antiviruscheckyes'+colIndex1).checked){\r\n" + 
		     		"			antiviruscheck=$('#antiviruscheckyes'+colIndex1).val()\r\n" + 
		     		"			}\r\n" + 
		     		"			else if(document.getElementById('antiviruscheckno'+colIndex1).checked){\r\n" + 
		     		"			antiviruscheck=$('#antiviruscheckno'+colIndex1).val()\r\n" + 
		     		"			}"+
		     		"   antivirus=  document.getElementById(\"antivirus\"+colIndex1).value;\r\n" + 
		     		"   os_patch=  document.getElementById(\"os_patch\"+colIndex1).value;\r\n" + 
		     		"   dply_envt=  document.getElementById(\"dply_envt\"+colIndex1).value;\r\n" + 
		     		"   ram_slot_qty=  document.getElementById(\"ram_slot_qty\"+colIndex1).value;\r\n" + 
		     		"   scan=  document.getElementById(\"scan\"+colIndex1).value;\r\n" + 
		     		"  if(document.getElementById('warrantycheckyes'+colIndex1).checked){\r\n" + 
		     		"			warrantycheck=$('#warrantycheckyes'+colIndex1).val()\r\n" + 
		     		"			}\r\n" + 
		     		"			else if(document.getElementById('warrantycheckno'+colIndex1).checked){\r\n" + 
		     		"			warrantycheck=$('#warrantycheckno'+colIndex1).val()\r\n" + 
		     		"			}"+ 
		     		"   warranty=  document.getElementById(\"warranty\"+colIndex1).value;\r\n" + 
		     		"   model_number=  document.getElementById(\"model_number\"+colIndex1).value;\r\n" + 
		     		"   machine_number=  document.getElementById(\"machine_number\"+colIndex1).value;\r\n" + 
		     		"   mac_address=  document.getElementById(\"mac_address\"+colIndex1).value;\r\n" + 
		     		"   ip_address=  document.getElementById(\"ip_address\"+colIndex1).value;\r\n" + 
		     		"   s_state=  document.getElementById(\"s_state\"+colIndex1).value;\r\n" + 
		     		"   unserviceable_state=  document.getElementById(\"unserviceable_state\"+colIndex1).value;\r\n" + 
		     		"   unsv_from_dt=  document.getElementById(\"unsv_from_dt\"+colIndex1).value;\r\n" + 
		     		
		     		"     b_cost=  document.getElementById(\"b_cost\"+colIndex1).value;\r\n" + 
		     		"	   proc_date=  document.getElementById(\"proc_date\"+colIndex1).value;\r\n" + 
		     		"	     b_head=  document.getElementById(\"b_head\"+colIndex1).value;\r\n" + 
		     		"		   b_code=  document.getElementById(\"b_code\"+colIndex1).value;\r\n" + 
		     		"\r\n" + 
		     		" rows = \"<tr><td>\" + asset_type + \"</td><td>\" + make_name + \"</td><td>\" + model_name + \"</td><td>\" + processor_type + \"</td><td>\" + ram_capi + \"</td><td>\" \r\n" + 
		     		" + hdd_capi + \"</td><td>\" + operating_system + \"</td><td>\" + office + \"</td><td>\" + antiviruscheck + \"</td><td>\" + antivirus + \"</td><td>\" + os_patch + \r\n" + 
		     		" \"</td><td>\" + dply_envt + \"</td><td>\" + ram_slot_qty + \"</td><td>\" + scan + \"</td><td>\" + warrantycheck + \"</td><td>\" + warranty + \r\n" + 
		     		"\"</td><td>\" + model_number + \"</td><td>\" + machine_number + \"</td><td>\" + mac_address + \"</td><td>\" + ip_address + \"</td><td>\" + s_state + \"</td><td>\" + unserviceable_state + \r\n" + 
		     		" \"</td><td>\" + unsv_from_dt + \"</td><td>\" + b_cost + \"</td><td>\" + proc_date + \"</td><td>\" + b_head + \"</td><td>\" + b_code + \"</td></tr> \";\r\n" + 
		     		"  $(rows).appendTo(\"#table_show_2 tbody\");\r\n" + 
		     		" \r\n" + 
		     		"  \r\n" + 
		     		"\r\n" + 
		     		"    \r\n" + 
		     		"}\r\n" + 
		     		"\r\n" + 
		     		"btnExport();\r\n" + 
		     		"window.location.reload();\r\n" + 
		     		"}\r\n" + 
		     		"\r\n" + 
		     		"\r\n" + 
		     		"\r\n" + 
		     		"var btnExport = (function() {\r\n"
		     		+ "var currentdate = new Date(); \r\n" + 
		     		"var datetime = \"Computing: \" + currentdate.getDate() + \"/\"\r\n" + 
		     		"                + (currentdate.getMonth()+1)  + \"/\" \r\n" + 
		     		"                + currentdate.getFullYear() + \" Time \"  \r\n" + 
		     		"                + currentdate.getHours() + \":\"  \r\n" + 
		     		"                + currentdate.getMinutes() + \":\" \r\n" + 
		     		"                + currentdate.getSeconds();\r\n" + 
		     		"var blobURL = tablesToExcel(['table_show_2'],['UnitDtl'],datetime+'.xls','Excel');\r\n" + 
		     		" $(this).attr('href',blobURL);" + 
		     		
		     		
		     		"});\r\n" + 
		     		"\r\n" + 
		     		"\r\n" + 
		     		"\r\n" + 
		     		"var tablesToExcel = (function() {\r\n" + 
		     		"var uri = 'data:application/vnd.ms-excel;base64,'\r\n" + 
		     		", tmplWorkbookXML = '<?xml version=\"1.0\"?><?mso-application progid=\"Excel.Sheet\"?><Workbook xmlns=\"urn:schemas-microsoft-com:office:spreadsheet\" xmlns:ss=\"urn:schemas-microsoft-com:office:spreadsheet\">'\r\n" + 
		     		"  + '<DocumentProperties xmlns=\"urn:schemas-microsoft-com:office:office\"><Author>Axel Richter</Author><Created>{created}</Created></DocumentProperties>'\r\n" + 
		     		"  + '<Styles>'\r\n" + 
		     		"  + '<Style ss:ID=\"Currency\"><NumberFormat ss:Format=\"Currency\"></NumberFormat></Style>'\r\n" + 
		     		"  + '<Style ss:ID=\"Date\"><NumberFormat ss:Format=\"Medium Date\"></NumberFormat></Style>'\r\n" + 
		     		"  + '</Styles>' \r\n" + 
		     		"  + '{worksheets}</Workbook>'\r\n" + 
		     		", tmplWorksheetXML = '<Worksheet ss:Name=\"{nameWS}\"><Table>{rows}</Table></Worksheet>'\r\n" + 
		     		", tmplCellXML = '<Cell{attributeStyleID}{attributeFormula}><Data ss:Type=\"{nameType}\">{data}</Data></Cell>'\r\n" + 
		     		", base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) }\r\n" + 
		     		", format = function(s, c) { return s.replace(/{(\\w+)}/g, function(m, p) { return c[p]; }) }\r\n" + 
		     		"return function(tables, wsnames, wbname, appname) {\r\n" + 
		     		"  var ctx = \"\";\r\n" + 
		     		"  var workbookXML = \"\";\r\n" + 
		     		"  var worksheetsXML = \"\";\r\n" + 
		     		"  var rowsXML = \"\";\r\n" + 
		     		"\r\n" + 
		     		"\r\n" + 
		     		"  for (var i = 0; i < tables.length; i++) {\r\n" + 
		     		"\r\n" + 
		     		"    if (!tables[i].nodeType) \r\n" + 
		     		"       tables[i] = document.getElementById(tables[i]);\r\n" + 
		     		"\r\n" + 
		     		"\r\n" + 
		     		"    for (var j = 0; j < tables[i].rows.length; j++) {\r\n" + 
		     		"      rowsXML += '<Row>'\r\n" + 
		     		"      for (var k = 0; k < tables[i].rows[j].cells.length; k++) {\r\n" + 
		     		"        var dataType = tables[i].rows[j].cells[k].getAttribute(\"data-type\");\r\n" + 
		     		"        var dataStyle = tables[i].rows[j].cells[k].getAttribute(\"data-style\");\r\n" + 
		     		"        var dataValue = tables[i].rows[j].cells[k].getAttribute(\"data-value\");\r\n" + 
		     		"        dataValue = (dataValue)?dataValue:tables[i].rows[j].cells[k].innerHTML;\r\n" + 
		     		"        var dataFormula = tables[i].rows[j].cells[k].getAttribute(\"data-formula\");\r\n" + 
		     		"        dataFormula = (dataFormula)?dataFormula:(appname=='Calc' && dataType=='DateTime')?dataValue:null;\r\n" + 
		     		"        ctx = {  attributeStyleID: (dataStyle=='Currency' || dataStyle=='Date')?' ss:StyleID=\"'+dataStyle+'\"':''\r\n" + 
		     		"               , nameType: (dataType=='Number' || dataType=='DateTime' || dataType=='Boolean' || dataType=='Error')?dataType:'String'\r\n" + 
		     		"               , data: (dataFormula)?'':dataValue\r\n" + 
		     		"               , attributeFormula: (dataFormula)?' ss:Formula=\"'+dataFormula+'\"':''\r\n" + 
		     		"              };\r\n" + 
		     		"        rowsXML += format(tmplCellXML, ctx);\r\n" + 
		     		"      }\r\n" + 
		     		"      rowsXML += '</Row>'\r\n" + 
		     		"    }\r\n" + 
		     		"    ctx = {rows: rowsXML, nameWS: wsnames[i] || 'Sheet' + i};\r\n" + 
		     		"    worksheetsXML += format(tmplWorksheetXML, ctx);\r\n" + 
		     		"    rowsXML = \"\";\r\n" + 
		     		"  }\r\n" + 
		     		"\r\n" + 
		     		"  ctx = {created: (new Date()).getTime(), worksheets: worksheetsXML};\r\n" + 
		     		"  workbookXML = format(tmplWorkbookXML, ctx);\r\n" + 
		     		"  \r\n" + 
		     		"\r\n" + 
		     		"\r\n" + 
		     		"  var link = document.createElement(\"A\");\r\n" + 
		     		"  link.href = uri + base64(workbookXML);\r\n" + 
		     		"  console.log(typeof ctx);\r\n" + 
		     		"  link.download = wbname || 'Workbook.xls';\r\n" + 
		     		"  link.target = '_blank';\r\n" + 
		     		"  document.body.appendChild(link);\r\n" + 
		     		"  link.click();\r\n" + 
		     		"  document.body.removeChild(link);\r\n" + 
		     		"}\r\n" + 
		     		"\r\n" + 
		     		" })();\r\n" + 
		     		"\r\n" + 
		     		"function serviceStChange(obj,x)\r\n" + 
		     		" {\r\n" + 
		     		"	 var a =$(\"select#s_state\"+x).val();\r\n" + 
		     		"	 if(a == '1')\r\n" + 
		     		"		 {\r\n" + 
		     		"	\r\n" + 
		     		"			 $('select#unserviceable_state'+x).attr('readonly', true);"
		     		+ " $('#unsv_from_dt'+x).attr('readonly', true);"
		     		+ "$('#unserviceable_state'+x).val('0');"
		     		+ " $('#unsv_from_dt'+x).val('0');\r\n" + 
		     		"		   \r\n" + 
		     		"		 }\r\n" + 
		     		"	 else\r\n" + 
		     		"		 {\r\n" + 
		     		"		 $('select#unserviceable_state'+x).attr('readonly', false);"
		     		+ " $('#unsv_from_dt'+x).attr('readonly', false);\r\n" + 
		     		"		 \r\n" + 
		     		"		 }\r\n" + 
		     		" }"+
		     		" function anti_show(obj,x)\r\n" + 
		     		" {\r\n" + 
		     	
		     		"	 if ($(\"#antiviruscheckyes\"+x).prop(\"checked\"))\r\n" + 
		     		"	 {\r\n" + 
		     		"	     \r\n" + 
		     		"		  $('select#antivirus'+x).attr('readonly', false);\r\n" + 
		     		"		\r\n" + 
		     		"	}\r\n" + 
		     		"		else{\r\n" + 
		     		"			 $('select#antivirus'+x).attr('readonly', true);\r\n"
		     		+ " $('select#antivirus'+x).val('0');" + 
		     		"			\r\n" + 
		     		
		     		"	 }"+
		     		" }"+
		     		
		     		"function warrenty_show(obj,x)\r\n" + 
		     		"	{\r\n" + 
		     		"	\r\n" + 
		     		"		 if ($(\"#warrantycheckyes\"+x).prop(\"checked\")) {\r\n" + 
		     		"		 \r\n" + 
		     		"			\r\n" + 
		     		"		  $('#warranty'+x).attr('readonly', false);\r\n" + 
		     		"				}\r\n" + 
		     		"			else{\r\n" + 
		     		"			 $('#warranty'+x).attr('readonly', true);\r\n"
		     		+ " $('#warranty'+x).val('0');" + 
		     		"			\r\n" + 
		     		"	 }\r\n" + 
		     		"		\r\n" + 
		     		"	}"+
		     		"function makeMacAddress(obj){\r\n" + 
		     		"	\r\n" + 
		     		"	 if(obj.value!=''){\r\n" + 
		     		"	 var val=obj.value.split('-').join('');\r\n" + 
		     		"	 var v = val.match(/.{1,2}/g).join(\"-\");\r\n" + 
		     		"	 obj.value=v;\r\n" + 
		     		"	 }\r\n" + 
		     		"}\r\n" + 
		     		"\r\n" + 
		     		"\r\n" + 
		     		"function ValidateIPaddress(ipaddress,x) {  \r\n" + 
		     		"	var ip = new RegExp(/^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/)\r\n" + 
		     		"	  if (ip.test(ipaddress.value)) {  \r\n" + 
		     		"	    return true;  \r\n" + 
		     		"	   \r\n" + 
		     		"	  }  \r\n" + 
		     		"	  else{\r\n" + 
		     		"	 	alert(\"You have entered an invalid IP Address!\")  \r\n" + 
		     		"		$(\"#ip_address\"+x).focus();\r\n" + 
		     		"	\r\n" + 
		     		"		  return false;\r\n" + 
		     		"	  }\r\n" + 
		     		"	} \r\n" + 
		     		"	\r\n" + 
		     		"	\r\n" + 
		     		"	function isNumber(evt) {\r\n" + 
		     		"		evt = (evt) ? evt : window.event;\r\n" + 
		     		"		var charCode = (evt.which) ? evt.which : evt.keyCode;\r\n" + 
		     		"		if (charCode > 31 && (charCode < 48 || charCode > 57)) {\r\n" + 
		     		"		return false;\r\n" + 
		     		"		}\r\n" + 
		     		"		return true;\r\n" + 
		     		"		}"+
		     		
		     		"	\r\n" + 
		     		"///////////////////////////////////////////////////////////////////////////////////////export code end\r\n" + 
		     		"</script>\r\n" + 
		     		"\r\n" + 
		     		"</html>\r\n" + 
		     		"\r\n" + 
		     		"	\r\n" + 
		     		"\r\n" + 
		     		"<div id=\"ui-datepicker-div\" class=\"ui-datepicker ui-widget ui-widget-content ui-helper-clearfix ui-corner-all\"></div></body></html>";
					Pp_jsp += "<html><head>\r\n" + 
							"    <title>IT ASSET</title>\r\n" + 
							"  	<link rel=\"stylesheet\" href=\"js/assets/css/bootstrap.min.css\">\r\n" + 
							"  	<link rel=\"stylesheet\" href=\"js/assets/css/font-awesome.min.css\">\r\n" + 
							"	<link rel=\"stylesheet\" href=\"js/assets/scss/style.css\">\r\n" + 
							"		<script type=\"text/javascript\" src=\"js/assets/js/vendor/jquery-2.1.4.min.js\"></script> \r\n" + 
							"	<script src=\"js/assets/js/plugins.js\"></script> \r\n" + 
							"	<script src=\"js/assets/js/main.js\"></script> \r\n" + 
							"	\r\n" + 
							"<script type=\"text/javascript\" src=\"js/AES_ENC_DEC/lib/aes.js\"></script>\r\n" + 
							"<script type=\"text/javascript\" src=\"js/AES_ENC_DEC/lib/pbkdf2.js\"></script>\r\n" + 
							"<script type=\"text/javascript\" src=\"js/AES_ENC_DEC/AesUtil.js\"></script>\r\n" + 
							"<script src=\"js/miso/miso_js/jquery-2.2.3.min.js\"></script>\r\n" + 
							"<link rel=\"stylesheet\" href=\"js/miso/autoComplate/autoComplate.css\">\r\n" + 
							"<link href=\"js/Calender/jquery-ui.css\" rel=\"Stylesheet\">\r\n" + 
							"<script src=\"js/Calender/jquery-ui.js\" type=\"text/javascript\"></script>\r\n" + 
							"<script src=\"js/miso/commonJS/commonmethod.js\" type=\"text/javascript\"></script>\r\n" + 
							"<link rel=\"stylesheet\" href=\"js/InfiniteScroll/css/datatables.min.css\">\r\n" + 
							"<script src=\"js/InfiniteScroll/js/jquery-1.11.0.js\"></script>\r\n" + 
							"<script type=\"text/javascript\" src=\"js/InfiniteScroll/js/datatables.min.js\"></script>\r\n" + 
							"<script type=\"text/javascript\" src=\"js/InfiniteScroll/js/jquery.mockjax.min.js\"></script>\r\n" + 
							"<script type=\"text/javascript\" src=\"js/InfiniteScroll/js/datatables.mockjax.js\"></script>\r\n" + 
							"\r\n" + 
							"<link rel=\"stylesheet\" href=\"js/assets/css/bootstrap.min.css\">\r\n" + 
							"<link rel=\"stylesheet\" href=\"js/layout/css/animation.css\">\r\n" + 
							"<link rel=\"stylesheet\" href=\"js/assets/scss/style.css\">\r\n" + 
							"<link rel=\"stylesheet\" href=\"js/assets/collapse/collapse.css\">\r\n" + 
							"\r\n" + 
							"<script src=\"js/Calender/jquery-2.2.3.min.js\"></script>\r\n" + 
							"<script src=\"js/Calender/jquery-ui.js\"></script>\r\n" + 
							"<script src=\"js/Calender/datePicketValidation.js\"></script>\r\n" + 
							"<script src=\"js/assets/adjestable_Col/jquery.resizableColumns.js\" type=\"text/javascript\"></script>\r\n" + 
							"<link rel=\"stylesheet\" href=\"js/assets/adjestable_Col/jquery.resizableColumns.css\">\r\n" + 
							"\r\n" + 
							"\r\n" + 
							"	<script type=\"text/javascript\">\r\n" + 
							"	\r\n" + 
							"	\r\n" + 
							"		var roleAccess = 'Unit';\r\n" + 
							"		var role = 'IT_DEO';\r\n" + 
							"		var user_agent = 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.82 Safari/537.36';\r\n" + 
							"		var army_no = 'IC123456';\r\n" + 
							"		var otpKey = '';\r\n" + 
							"		\r\n" + 
							"		\r\n" + 
							"		\r\n" + 
							"		var tbl, div;\r\n" + 
							"     	function resetTimer() {\r\n" + 
							"        	if (jQuery('#div_timeout').length) {  jQuery('#div_timeout').html(timeout());  }\r\n" + 
							"     	}\r\n" + 
							"     	function timeout() { return '600'; }\r\n" + 
							"     	function getsubmodule(id){ localStorage.setItem(\"subModule\", id); }\r\n" + 
							"     	\r\n" + 
							"     	var key = \"_csrf\";\r\n" + 
							"     	var value = \"3b596ad3-3205-4709-835e-4867dcda32af\";\r\n" + 
							"     	\r\n" + 
							"     	jQuery(document).on('keypress', function(event) {\r\n" + 
							"     		localStorage.setItem(\"army_no\", army_no);\r\n" + 
							"     		\r\n" + 
							"     		var regex = new RegExp(\"^[a-zA-Z0-9\\\\[\\\\] \\\\+ \\\\* \\\\-.,/ ~!@#$^&%_]+$\");\r\n" + 
							"     	    var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);\r\n" + 
							"     	    if (!regex.test(key)) {\r\n" + 
							"     	       event.preventDefault();\r\n" + 
							"     	       return false;\r\n" + 
							"     	    } \r\n" + 
							"     	});\r\n" + 
							"     	\r\n" + 
							"   		jQuery(document).ready(function() {	\r\n" + 
							"   			jQuery('body').bind('cut copy paste', function (e) {\r\n" + 
							"	   	        e.preventDefault();\r\n" + 
							"				\r\n" + 
							"	   	    });\r\n" + 
							"   			\r\n" + 
							"   			// set current sub module\r\n" + 
							"   			jQuery('ul#Dropdown_'+localStorage.getItem(\"subModule\")).attr(\"class\",\"dropdown-menu scrollbar show\");\r\n" + 
							"   			setInterval(function() {\r\n" + 
							"				var today = new Date();\r\n" + 
							"				var date =(\"0\" + today.getDate()).slice(-2)+'-'+ (\"0\" + (today.getMonth()+1)).slice(-2)+'-'+today.getFullYear();\r\n" + 
							"				var time = (\"0\" + today.getHours()).slice(-2) + \":\" + (\"0\" + today.getMinutes()).slice(-2);// + \":\" + (\"0\" + today.getSeconds()).slice(-2);\r\n" + 
							"				var dateTime = date+' '+time;\r\n" + 
							"				jQuery(\"#datetime\").text(dateTime);\r\n" + 
							"				\r\n" + 
							"				if (jQuery('#div_timeout').length) {\r\n" + 
							"	            	 var tt = jQuery('#div_timeout').html();\r\n" + 
							"	                 if (tt === undefined) {\r\n" + 
							"	                     tt = timeout();\r\n" + 
							"	                 }\r\n" + 
							"	                 var ct = parseInt(tt, 10) - 1;\r\n" + 
							"	                 jQuery('#div_timeout').html(ct.toString().padStart(3, '0'));\r\n" + 
							"	                 if (ct === 0) {\r\n" + 
							"	                	 formSubmit();\r\n" + 
							"	                 }\r\n" + 
							"	             } else {\r\n" + 
							"	            	 formSubmit();\r\n" + 
							"	             }\r\n" + 
							"			}, 1000);\r\n" + 
							"			try\r\n" + 
							"			{\r\n" + 
							"				var msg = document.getElementById(\"msg\").value;\r\n" + 
							"				if(msg != null )\r\n" + 
							"				{\r\n" + 
							"					alert(msg);\r\n" + 
							"				}\r\n" + 
							"			}\r\n" + 
							"			catch (e) {\r\n" + 
							"			}\r\n" + 
							"		});\r\n" + 
							"		function formSubmit() {\r\n" + 
							"			document.getElementById(\"logoutForm\").submit();\r\n" + 
							"		}\r\n" + 
							"		popupWindow = null;\r\n" + 
							"		function parent_disable() {\r\n" + 
							"			if(popupWindow && !popupWindow.closed)\r\n" + 
							"				popupWindow.focus();\r\n" + 
							"		}\r\n" + 
							"	</script>\r\n" + 
							"	<script>\r\n" + 
							"		document.onkeydown = function(e) {\r\n" + 
							"			if(e.keyCode == 123) { return false; }\r\n" + 
							"			if(e.keyCode == 44) {  return false; }\r\n" + 
							"			if(e.ctrlKey && e.keyCode == 'E'.charCodeAt(0)){ return false; } \r\n" + 
							"			if(e.ctrlKey && e.shiftKey && e.keyCode == 'I'.charCodeAt(0)){ return false; }\r\n" + 
							"			if(e.ctrlKey && e.shiftKey && e.keyCode == 'J'.charCodeAt(0)){ return false; }\r\n" + 
							"			if(e.ctrlKey && e.keyCode == 'U'.charCodeAt(0)){ return false; }\r\n" + 
							"			if(e.ctrlKey && e.keyCode == 'S'.charCodeAt(0)){ return false; }\r\n" + 
							"			if(e.ctrlKey && e.keyCode == 'H'.charCodeAt(0)){ return false; }\r\n" + 
							"			if(e.ctrlKey && e.keyCode == 'A'.charCodeAt(0)){ return false; }\r\n" + 
							"			if(e.ctrlKey && e.keyCode == 'E'.charCodeAt(0)){ return false; }\r\n" + 
							"		}\r\n" + 
							"	</script>\r\n" + 
							"</head>\r\n" + 
							"<body onfocus=\"parent_disable();\" onclick=\"parent_disable();resetTimer();\" oncontextmenu=\"return false\" style=\"\">\r\n" + 
							"		\r\n" + 
							"		\r\n" + 
							"<script>\r\n" + 
							"$(document).ready(function() {\r\n" + 
							"	\r\n" + 
							"	colAdj(\"show\");\r\n" + 
							"});\r\n" + 
							"	 \r\n" + 
							"	 </script>\r\n" + 
							"<script>\r\n" + 
							"	var username=\"PARTH_DEO\";\r\n" + 
							"	var curDate = \"20-09-2021 14:29:22\";\r\n" + 
							"	\r\n" + 
							"	//var addiphostna ='0:0:0:0:0:0:0:1';\r\n" + 
							"</script>\r\n" + 
							"	\r\n" + 
							"	<form action=\"/It_Asset_01_09_2021_v2/j_spring_security_logout\" method=\"post\" id=\"logoutForm\">\r\n" + 
							"		<input type=\"hidden\" name=\"_csrf\" value=\"3b596ad3-3205-4709-835e-4867dcda32af\">\r\n" + 
							"	</form>\r\n" + 
							"	<aside id=\"left-panel\" class=\"left-panel\">\r\n" + 
							"		<nav class=\"navbar navbar-expand-sm navbar-default\">\r\n" + 
							"	    	<div class=\"navbar-header\">\r\n" + 
							"	      		<button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#main-menu\" aria-controls=\"main-menu\" aria-expanded=\"false\" aria-label=\"Toggle navigation\"> <i class=\"fa fa-bars\"></i> </button>\r\n" + 
							"	      		<a class=\"navbar-brand\" href=\"#\"><i class=\"menu-icon fa fa-user\"></i>PARTH_DEO</a> \r\n" + 
							"	      	</div>\r\n" + 
							"	      	<div align=\"center\">\r\n" + 
							" 				<span style=\"position: relative; color: white;border-radius: 2px;\"> Session timeout in &nbsp; <i style=\"font-size: 10px;\" class=\"fa fa-hourglass fa-spin\"></i>  :  <b style=\"color: orangered; min-width: 20px\" id=\"div_timeout\">213</b></span>\r\n" + 
							" 			</div>\r\n" + 
							"	    	\r\n" + 
							"	  	</nav>\r\n" + 
							"</aside>\r\n" + 
							"		<div id=\"right-panel\" class=\"right-panel\"> \r\n" + 
							"			<header id=\"header\" class=\"header\">\r\n" + 
							"		    	<div class=\"header-menu\">\r\n" + 
							"		    		<div class=\"col-md-2 col-sm-2\"> <a id=\"menuToggle\" class=\"menutoggle pull-left\"><i class=\"fa fa fa-tasks\"></i></a>\r\n" + 
							"		        		<div class=\"header-left\">\r\n" + 
							"		          			<div class=\"dropdown for-notification\"> <img src=\"js/miso/images/indianarmy_smrm5aaa.jpg\" class=\"img-fluid\" style=\"height: 55px;\"> </div>\r\n" + 
							"		        		</div>\r\n" + 
							"		      		</div>\r\n" + 
							"		      		<div class=\"col-md-8 col-sm-8\">\r\n" + 
							"		      			<div align=\"center\"> <strong style=\"font-size: 22px;color: #800000;\">IT ASSET</strong> <br> <strong style=\"font-size: 15px;color: #030080;\">VERSION 1.0</strong></div>\r\n" + 
							"		      		</div>\r\n" + 
							"		      		<div class=\"col-md-2 col-sm-2\">\r\n" + 
							"		        	<div class=\"language-select dropdown\" id=\"language-select\" align=\"right\"> <img src=\"js/miso/images/dgis-logo.png\" class=\"img-fluid\" style=\"max-width:130px; height: 50px;\"></div>\r\n" + 
							"		      		</div>\r\n" + 
							"		    	</div>\r\n" + 
							"		  	</header>\r\n" + 
							"		  	<div class=\"ticker dash-tic\" align=\"right\">\r\n" + 
							"				<h3></h3>\r\n" + 
							"				<label id=\"datetime\" style=\"position: relative; color: white;border-radius: 5px;background: #672a2a;\">20-09-2021 14:45</label>\r\n" + 
							"				<a href=\"javascript:formSubmit();\" class=\"btn btn-danger\" type=\"submit\" style=\"border-radius: 5px; position: relative; float: right !important;\" onclick=\"localStorage.clear();\">Logout</a>\r\n" + 
							" 			</div>\r\n" + 
							"		  	<p></p>\r\n" + 
							"		  	<div class=\"content mt-3\" style=\"margin-top:-13px !important;\"> \r\n" + 
							"		  		<div id=\"WaitLoader\" style=\"display:none;\" align=\"center\">\r\n" + 
							"					<span id=\"\">Processing Data.Please Wait ...<i style=\"font-size: 18px;\" class=\"fa fa-hourglass fa-spin\"></i></span>\r\n" + 
							"				</div>\r\n" + 
							"				\r\n" + 
							"\r\n" + 
							"\r\n" + 
							"\r\n" + 
							"\r\n" + 
							"\r\n" + 
							"<style>\r\n" + 
							".ui-tooltip {\r\n" + 
							"	position: absolute;\r\n" + 
							"	top: 110px;\r\n" + 
							"	left: 100px;\r\n" + 
							"}\r\n" + 
							"</style>\r\n" + 
							"<div class=\"third_2\" id=\"third_2\">\r\n" + 
							"<div id=\"data_1\">\r\n" + 
							"  <div id=\"data\" style=\"overflow-x: auto; max-width: 1600px; border:solid black;\">\r\n" + 
							"\r\n" + 
							"\r\n" + 
							"      <br>\r\n" + 
							"\r\n" + 
							"\r\n" + 
							"     <table border=\"1\" style=\"border-color: black;    width: 4000px;\" id=\"show\">\r\n" + 
							"\r\n" + 
							"    \r\n" + 
							"        <thead>\r\n" + 
							"         <!---  <tr>\r\n" + 
							"            <th colspan=\"6\" style=\"text-align: center; background-color: #ffcc00\">PERSONAL DETAILS</th>\r\n" + 
							"             <th colspan=\"4\" style=\"text-align: center; background-color: #99cc00\">PRIMARY SKILLS</th>\r\n" + 
							"            <th colspan=\"10\" style=\"text-align: center;background-color: #ffa366\">SECONDARY SKILLS</th> \r\n" + 
							"            <th colspan=\"4\" style=\"text-align: center; background-color: #b3d1ff\">BODY WEIGHT DETAILS</th>\r\n" + 
							"             <th rowspan=\"3\"  style=\"text-align: center; background-color: #00cc00\">ACTION</th> \r\n" + 
							"              \r\n" + 
							"          </tr>  --->\r\n" + 
							"\r\n" + 
							"\r\n" + 
							" <tr>\r\n" + 
							"                <th align=\"center\" rowspan=\"2\" style=\"background-color: #264e58;\">Ser No</th>\r\n" + 
							"                <th rowspan=\"2\" style=\"background-color: #264e58\"><strong style=\"color: red;\">* </strong>Peripheral Type</th>\r\n" + 
							"                <th rowspan=\"2\" style=\"background-color: #264e58\"><strong style=\"color: red;\">* </strong>Make/Brand Name</th>\r\n" + 
							"                <th rowspan=\"2\" style=\"background-color: #264e58\"><strong style=\"color: red;\">* </strong>Model Name</th>\r\n" + 
							"                <th rowspan=\"2\" style=\"background-color: #264e58\">Warranty</th>\r\n" + 
							"                <th rowspan=\"2\" style=\"background-color: #264e58\">Warranty Upto</th>\r\n" + 
							"               <th style=\"background-color: #264e58\"><strong style=\"color: red;\">* </strong>Type of Peripheral HW</th>\r\n" + 
							"                <th style=\"background-color: #264e58\"><strong style=\"color: red;\">* </strong>Model Type</th>\r\n" + 
							"                <th style=\"background-color: #264e58\">Year of Proc</th>\r\n" + 
							"                <th style=\"background-color: #264e58\">Year of Manufacturing</th>\r\n" + 
							"                <th style=\"background-color: #264e58\">Remarks</th>\r\n" + 
							"                <th style=\"background-color: #264e58\"><strong style=\"color: red;\">* </strong>Model Number</th>\r\n" + 
							"                <th style=\"background-color: #264e58\"><strong style=\"color: red;\">* </strong>Machine No.</th>\r\n" + 
							"                <th style=\"background-color: #264e58\"><strong style=\"color: red;\">* </strong>IS Networked</th>\r\n" + 
							"                <th style=\"background-color: #264e58\"><strong style=\"color: red;\">* </strong>IP Address(For Networked Device)</th>\r\n" + 
							"               <th style=\"background-color: #264e58\"><strong style=\"color: red;\">* </strong>UPS Capacity</th>\r\n" + 
							"                <th style=\"background-color: #264e58\"><strong style=\"color: red;\">* </strong>Monochrome/Color</th>\r\n" + 
							"				<th style=\"background-color: #264e58\"><strong style=\"color: red;\">* </strong>Max Paper Size</th>\r\n" + 
							"				<th style=\"background-color: #264e58\"><strong style=\"color: red;\">* </strong>Connectivity Type</th>\r\n" + 
							"              <th style=\"background-color: #264e58\"><strong style=\"color: red;\">* </strong>Print</th>\r\n" + 
							"				<th style=\"background-color: #264e58\"><strong style=\"color: red;\">* </strong>Scan</th>\r\n" + 
							"				<th style=\"background-color: #264e58\"><strong style=\"color: red;\">* </strong>Photography</th>\r\n" + 
							"				<th style=\"background-color: #264e58\"><strong style=\"color: red;\">* </strong>Colour</th>\r\n" + 
							"                 <th style=\"background-color: #264e58\"><strong style=\"color: red;\">* </strong>Capacity(Lumens)</th>\r\n" + 
							"               <th style=\"background-color: #264e58\"><strong style=\"color: red;\">* </strong>Hardware Interface</th> \r\n" + 
							"			   <th style=\"background-color: #264e58\"><strong style=\"color: red;\">* </strong>Resolution</th>\r\n" + 
							"                <th style=\"background-color: #264e58\"><strong style=\"color: red;\">* </strong>Display Size</th>\r\n" + 
							"                <th style=\"background-color: #264e58\"><strong style=\"color: red;\">* </strong>Display Connector</th>\r\n" + 
							"				 <th style=\"background-color: #264e58\"><strong style=\"color: red;\">* </strong>No Of Ports</th>\r\n" + 
							"				 <th style=\"background-color: #264e58\"><strong style=\"color: red;\">* </strong>Network Features</th>\r\n" + 
							"				   <th style=\"background-color: #264e58\"><strong style=\"color: red;\">* </strong>Ethernet Port</th>\r\n" + 
							"				   <th style=\"background-color: #264e58\">Management Layer</th>\r\n" + 
							"				<th style=\"background-color: #264e58\"><strong style=\"color: red;\">* </strong>Display Type</th>\r\n" + 
							"				<th style=\"background-color: #264e58\"><strong style=\"color: red;\">* </strong>Display Size</th>\r\n" + 
							"				<th style=\"background-color: #264e58\"><strong style=\"color: red;\">* </strong>Display Connector</th>\r\n" + 
							"				<th style=\"background-color: #264e58\"><strong style=\"color: red;\">* </strong>Serviceable State</th>\r\n" + 
							"				<th style=\"background-color: #264e58\"><strong style=\"color: red;\">* </strong>UN-Serviceable State</th>\r\n" + 
							"				<th style=\"background-color: #264e58\"><strong style=\"color: red;\">* </strong>Un-servicable from</th>\r\n" + 
							"				<th style=\"background-color: #264e58\">Proc Cost</th>\r\n" + 
							"				<th style=\"background-color: #264e58\">Proc Date</th>\r\n" + 
							"				<th style=\"background-color: #264e58\"><strong style=\"color: red;\">* </strong>Budget Head</th>\r\n" + 
							"				<th style=\"background-color: #264e58\"><strong style=\"color: red;\">* </strong>Budget Code</th>\r\n" + 
							"				<th style=\"background-color: #264e58\">Action</th>\r\n" + 
							"            </tr>"+
							"       \r\n" + 
							"       \r\n" + 
							"       \r\n" + 
							"          <!-- <th colspan=\"6\" ></th> \r\n" + 
							"           <th style=\"text-align: center; background-color: #99cc00;width: 2%\">120</th>\r\n" + 
							"          <th style=\"text-align: center; background-color: #99cc00\">65</th>\r\n" + 
							"          <th style=\"text-align: center; background-color: #99cc00\">35</th>\r\n" + 
							"          <th style=\"text-align: center; background-color: #99cc00\">10</th>\r\n" + 
							"          <th style=\"text-align: center; background-color: #ffa366\">100</th>\r\n" + 
							"          <th style=\"text-align: center; background-color: #ffa366\">30</th>\r\n" + 
							"          <th style=\"text-align: center; background-color: #ffa366\">30</th>\r\n" + 
							"          <th style=\"text-align: center; background-color: #ffa366\">30</th>\r\n" + 
							"          <th style=\"text-align: center; background-color: #ffa366\">30</th>\r\n" + 
							"          <th style=\"text-align: center; background-color: #ffa366\">30</th>\r\n" + 
							"          <th style=\"text-align: center; background-color: #ffa366\">30</th>\r\n" + 
							"          <th style=\"text-align: center; background-color: #ffa366\">30</th>\r\n" + 
							"          <th style=\"text-align: center; background-color: #ffa366\">30</th>\r\n" + 
							"          <th style=\"text-align: center; background-color: #ffa366\">30</th>\r\n" + 
							"          <th colspan=\"3\" style=\"text-align: center; background-color: #b3d1ff\"></th>  \r\n" + 
							"    <th style=\"text-align: center; background-color: #0099ff\"></th>\r\n" + 
							"          <th style=\"text-align: center; background-color: #0099ff\"></th>\r\n" + 
							"          <th style=\"text-align: center; background-color: #b3d1ff\">30</th>  -->\r\n" + 
							"          \r\n" + 
							"         \r\n" + 
							"          <!-- <th colspan=\"3\" ></th>\r\n" + 
							"          <th style=\"text-align: center;\">30</th> -->\r\n" + 
							"      </thead> \r\n" + 
							"        <tbody id=\"tb1\">  \r\n" + 
							"       \r\n" + 
							"        \r\n" + 
							"            <tr id=\"tr_id1\">\r\n" + 
							"              <td align=\"center\" >1</td>\r\n" + 
							"                <td ><select id=\"assets_type1\" name=\"assets_type1\" class=\"form-control\" onchange=\"fn_hide_show(this,1);\">\r\n" + 
							"									<option value=\"0\" selected=\"selected\">--Select--</option>\r\n" ; 
							for (int i = 0; i < getpheral_type.size(); i++) {
								Pp_jsp +="<option value='" + getpheral_type.get(i).get("id") + "' name='"
										+ getpheral_type.get(i).get("assets_name") + "'>" + getpheral_type.get(i).get("assets_name")
										+ "</option>\r\n";

							}
							Pp_jsp +="</select>\r\n" + 
							"                </td>\r\n" + 
							"                 <td ><select id=\"make_name1\" name=\"make_name1\" class=\"form-control\">\r\n" + 
							"					<option value=\"0\">--Select--</option>\r\n" ; 
							for (int i = 0; i < getmake.size(); i++) {
								Pp_jsp += "<option value='" + getmake.get(i).get("id") + "' name='"
										+ getmake.get(i).get("make_name") + "'>" + getmake.get(i).get("make_name")
										+ "</option>\r\n";

							}
							Pp_jsp+="</select>\r\n" +
							"                </td>\r\n" + 
							"                <td ><select id=\"model_name1\" name=\"model_name1\" class=\"form-control\">\r\n" + 
							"				<option value=\"0\">--Select--</option>\r\n" ; 
							for (int i = 0; i < getmodel.size(); i++) {
								Pp_jsp+=" <option value='" + getmodel.get(i).get("id") + "' name='"
										+ getmodel.get(i).get("model_name") + "'>" + getmodel.get(i).get("model_name")
										+ "</option>\r\n";

							}
							Pp_jsp+="</select></td>\r\n" + 
							"                <td >\r\n" + 
							"									<input id=\"warrantycheckyes1\" name=\"warrantycheckyes\" checked=\"checked\" type=\"radio\" value=\"Yes\" onclick=\"warrenty_show(this,1);\">&nbsp; <label for=\"yes\">Yes</label>&nbsp;   \r\n" + 
							"  									<input id=\"warrantycheckno1\" name=\"warrantycheckyes\" type=\"radio\" value=\"No\" onclick=\"warrenty_show(this,1);\">&nbsp;  \r\n" + 
							"									<label for=\"no\">No</label>\r\n" + 
							"								</td>\r\n" + 
							"                <td ><input type=\"date\" name=\"warranty_dt1\" id=\"warranty_dt1\" maxlength=\"10\"  class=\"form-control\"></td>\r\n" + 
							"                <td ><select id=\"type_of_hw1\" name=\"type_of_hw1\" class=\"form-control\" >\r\n" + 
							"				<option value=\"0\">--Select--</option>\r\n" ; 
							for (int i = 0; i < gethwype.size(); i++) {
								Pp_jsp+="<option value='" + gethwype.get(i).get("id") + "' name='" + gethwype.get(i).get("type_of_hw") + "'>"
										+ gethwype.get(i).get("type_of_hw") + "</option>\r\n";

							}
							Pp_jsp+="</select></td>\r\n" + 
							"                <td  ><select id=\"type1\" name=\"type1\" class=\"form-control\">\r\n" + 
							"				<option value=\"0\">--Select--</option>\r\n" ;
							for (int i = 0; i < gettype.size(); i++) {
								Pp_jsp+="<option value='" + gettype.get(i).get("id") + "' name='" + gettype.get(i).get("type") + "'>"
										+ gettype.get(i).get("type") + "</option>\r\n";

							}
							Pp_jsp+="</select></td>\r\n" + 
							"                <td  ><input id=\"year_of_proc1\" name=\"year_of_proc1\"  type=\"text\" class=\"form-control\"   maxlength=\"4\" onkeypress=\"return isNumber(event);\"   onkeyup=\"validateYear(this);\" onblur=\"validateYearLn(this);\"  autocomplete=\"off\"></td>\r\n" + 
							"                <td ><input id=\"year_of_manufacturing1\" name=\"year_of_manufacturing1\"  type=\"text\" class=\"form-control\" onkeypress=\"return isNumber(event);\"  onkeyup=\"validateYear(this);\" onblur=\"validateYearLn(this);\"  maxlength=\"4\" autocomplete=\"off\"></td>\r\n" + 
							"				<td ><textarea id=\"remarks1\" name=\"remarks1\" autocomplete=\"off\" class=\"form-control \"></textarea></td>\r\n" + 
						    "      <td ><input type=\"text\" id=\"model_no1\" name=\"model_no1\" class=\"form-control\" autocomplete=\"off\" onkeypress=\"return isNumber(event);\"></td>\r\n" + 
						    "          <td ><input type=\"text\" id=\"machine_no1\" name=\"machine_no1\" class=\"form-control \" autocomplete=\"off\" onkeypress=\"return isNumber(event);\"></td>\r\n" + 
							"                <td ><input type=\"radio\" id=\"is_networkedyes1\" value=\"Yes\"  name=\"is_networked1\" checked=\"checked\" onclick=\"networked_show(this,1);\">&nbsp; <label for=\"yes\">Yes</label>&nbsp;    \r\n" + 
							"  									<input type=\"radio\" id=\"is_networkedno1\" name=\"is_networked1\" value=\"No\"   onclick=\"networked_show(this,1);\">&nbsp;   \r\n" + 
							"									<label for=\"no\">No</label>\r\n" + 
							"							</td>\r\n" + 
							"                <td ><input type=\"text\" id=\"ip_address1\" name=\"ip_address1\" maxlength=\"15\" class=\"form-control \" autocomplete=\"off\" onchange=\"ValidateIPaddress(this,1);\"></td>\r\n" + 
							"                <td ><select id=\"ups_capacity1\" name=\"ups_capacity1\" class=\"form-control\">\r\n" + 
							"				<option value=\"0\">--Select--</option>\r\n" ;
							for (int i = 0; i < ups_capacityNew.size(); i++) {
								Pp_jsp+="<option value='" + ups_capacityNew.get(i).get("id") + "' name='" + ups_capacityNew.get(i).get("ups_capacity") + "'>"
										+ ups_capacityNew.get(i).get("ups_capacity") + "</option>\r\n";

							}
							Pp_jsp+="</select></td>\r\n" + 
							"                <td ><div class=\"col-md-8\">\r\n" + 
							"								<input type=\"radio\" id=\"monochrome1\" name=\"monochrome_color_radio1\" value=\"1\">&nbsp; <label for=\"monochrome\">Monochrome</label>&nbsp; <input type=\"radio\" id=\"monochrome_color1\" name=\"monochrome_color_radio1\" value=\"2\" checked=\"checked\">&nbsp; <label for=\"color\">Color</label><br>\r\n" + 
							"								</div></td>\r\n" + 
							"			   <td >	<select id=\"paper_size1\" name=\"paper_size1\" class=\"form-control\">\r\n" + 
							"				<option value=\"0\">--Select--</option>\r\n" ;
							for (int i = 0; i < paper_sizeNew.size(); i++) {
								Pp_jsp+="<option value='" + paper_sizeNew.get(i).get("codevalue") + "' name='" + paper_sizeNew.get(i).get("label") + "'>"
										+ paper_sizeNew.get(i).get("label") + "</option>\r\n";

							}
							Pp_jsp+="</select></td>\r\n" +
						   "                <td >	<select id=\"connectivity_type1\" name=\"connectivity_type1\" class=\"form-control\">\r\n" + 
						   "				<option value=\"0\">--Select--</option>\r\n" ;
							for (int i = 0; i < paper_connetivity.size(); i++) {
								Pp_jsp+="<option value='" + paper_connetivity.get(i).get("codevalue") + "' name='" + paper_connetivity.get(i).get("label") + "'>"
										+ paper_connetivity.get(i).get("label") + "</option>\r\n";

							}
							Pp_jsp+="</select></td>\r\n" +
							"			   <td >\r\n" + 
							"								 <input id=\"printyes1\" name=\"print1\" type=\"radio\" value=\"Yes\">&nbsp; <label for=\"yes\">Yes</label>&nbsp;   \r\n" + 
							"  									<input id=\"printno1\" name=\"print1\"  type=\"radio\" value=\"No\"  checked=\"checked\">&nbsp;  \r\n" + 
							"									<label for=\"no\">No</label>\r\n" + 
							"							</td>\r\n" + 
							"               \r\n" + 
							"			   <td >\r\n" + 
							"								 <input id=\"scanyes1\" name=\"scan1\" type=\"radio\"  value=\"Yes\">&nbsp; <label for=\"yes\">Yes</label>&nbsp;   \r\n" + 
							"  									<input id=\"scanno1\" name=\"scan1\"  type=\"radio\" value=\"No\"  checked=\"checked\">&nbsp;  \r\n" + 
							"									<label for=\"no\">No</label>\r\n" + 
							"							</td>\r\n" + 
							"              \r\n" + 
							"			  <td ><div class=\"col-md-2\">\r\n" + 
							"								 <input id=\"photographyyes1\" name=\"photography1\"  type=\"radio\"  value=\"Yes\">&nbsp; <label for=\"yes\">Yes</label>&nbsp;   \r\n" + 
							"  									<input id=\"photographyno1\" name=\"photography1\"  type=\"radio\" value=\"No\"  checked=\"checked\">&nbsp;  \r\n" + 
							"									<label for=\"no\">No</label>\r\n" + 
							"								</div></td> \r\n" + 
							"                <td ><div class=\"col-md-2\">\r\n" + 
							"								 <input id=\"colouryes1\" name=\"colour1\" type=\"radio\" value=\"Yes\" >&nbsp; <label for=\"yes\">Yes</label>&nbsp;   \r\n" + 
							"  									<input id=\"colourno1\" name=\"colour1\" c type=\"radio\" value=\"No\"  checked=\"checked\">&nbsp;  \r\n" + 
							"									<label for=\"no\">No</label>\r\n" + 
							"								</div></td>\r\n" + 
						 
							"					<td ><input id=\"capacity1\" name=\"capacity1\" type=\"text\" class=\"form-control \" value=\"\" autocomplete=\"off\"></td> \r\n" + 
						     "					<td ><select id=\"hw_interface1\" name=\"hw_interface1\" class=\"form-control\">\r\n" + 
							  "				<option value=\"0\">--Select--</option>\r\n" ;
							for (int i = 0; i < hardware_interfaceNew.size(); i++) {
								Pp_jsp+="<option value='" + hardware_interfaceNew.get(i).get("id") + "' name='" + hardware_interfaceNew.get(i).get("hardware_interface") + "'>"
										+ hardware_interfaceNew.get(i).get("hardware_interface") + "</option>\r\n";

							}
							Pp_jsp+="</select></td>\r\n" +
							"	 <td >	<input id=\"resolution1\" name=\"resolution1\" type=\"text\" class=\"form-control \" value=\"\" autocomplete=\"off\"></td>\r\n" + 
							"	 <td ><input id=\"v_display_size1\" name=\"v_display_size1\" type=\"text\" class=\"form-control \" value=\"\" autocomplete=\"off\"></td>\r\n" + 
							"	<td ><select id=\"v_display_connector1\" name=\"v_display_connector1\" class=\"form-control\">\r\n" + 
							  "				<option value=\"0\">--Select--</option>\r\n" ;
							for (int i = 0; i < type_connetivity.size(); i++) {
								Pp_jsp+="<option value='" + type_connetivity.get(i).get("codevalue") + "' name='" + type_connetivity.get(i).get("label") + "'>"
										+ type_connetivity.get(i).get("label") + "</option>\r\n";

							}
							Pp_jsp+="</select></td>\r\n" +
							"<td >  <input id=\"no_of_ports1\" name=\"no_of_ports1\" onkeypress=\"return isNumber(event);\" type=\"text\" class=\"form-control \" value=\"\" autocomplete=\"off\"></td>\r\n" + 
							"<td ><select id=\"network_features1\" name=\"network_features1\" class=\"form-control\">\r\n" + 
							"	<option value=\"0\" selected=\"selected\">--Select--</option>\r\n" ; 
							for (int i = 0; i < getnetwork_features.size(); i++) {
								Pp_jsp+="<option value='" + getnetwork_features.get(i).get("id") + "' name='" + getnetwork_features.get(i).get("network_features") + "'>"
										+ getnetwork_features.get(i).get("network_features") + "</option>\r\n";

							}
							Pp_jsp+="</select></td>\r\n" +
							"									    <td ><select id=\"ethernet_port1\" name=\"ethernet_port1\" class=\"form-control\">\r\n" + 
							  "				<option value=\"0\">--Select--</option>\r\n" ;
							for (int i = 0; i < getethernet_portnew.size(); i++) {
								Pp_jsp+="<option value='" + getethernet_portnew.get(i).get("id") + "' name='" + getethernet_portnew.get(i).get("ethernet_port") + "'>"
										+ getethernet_portnew.get(i).get("ethernet_port") + "</option>\r\n";

							}
							Pp_jsp+="</select></td>\r\n" +
							"	<td ><select id=\"management_layer1\" name=\"management_layer1\" class=\"form-control\">\r\n" + 
							"				<option value=\"0\">--Select--</option>\r\n" ;
							for (int i = 0; i < getmanagement_layernew.size(); i++) {
								Pp_jsp+="<option value='" + getmanagement_layernew.get(i).get("id") + "' name='" + getmanagement_layernew.get(i).get("management_layer") + "'>"
										+ getmanagement_layernew.get(i).get("management_layer") + "</option>\r\n";

							}
							Pp_jsp+="</select></td>\r\n" +
							"<td ><input id=\"display_type1\" name=\"display_type1\" type=\"text\" class=\"form-control \" value=\"\" autocomplete=\"off\"></td>\r\n" + 
							"	 <td ><input id=\"display_size1\" name=\"display_size1\" type=\"text\" class=\"form-control \" value=\"\" autocomplete=\"off\"></td> \r\n" + 
							"	 <td ><select id=\"display_connector1\" name=\"display_connector1\" class=\"form-control\">\r\n" + 
							 "				<option value=\"0\">--Select--</option>\r\n" ;
							for (int i = 0; i < type_connetivity.size(); i++) {
								Pp_jsp+="<option value='" + type_connetivity.get(i).get("codevalue") + "' name='" + type_connetivity.get(i).get("label") + "'>"
										+ type_connetivity.get(i).get("label") + "</option>\r\n";

							}
							Pp_jsp+="</select></td>\r\n" +
							"										   <td ><select id=\"s_state1\" name=\"s_state1\" class=\"form-control\" onchange=\"serviceStChange(this,1);\">\r\n" + 
							"									  	<option value=\"0\">--Select--</option>\r\n" + 
							"										 \r\n" + 
							"										    <option name=\"Serviceable\" value=\"1\">Serviceable</option>\r\n" + 
							"									     \r\n" + 
							"										    <option name=\"Un-serviceable\" value=\"2\">Un-serviceable</option>\r\n" + 
							"									     \r\n" + 
							"									  </select></td>\r\n" + 
							"										    <td ><select id=\"unserviceable_state1\" name=\"unserviceable_state1\" class=\"form-control\">\r\n" + 
							"												  <option value=\"0\" selected=\"selected\">--Select--</option>\r\n" + 
							"													 \r\n" + 
							"													    <option name=\"BER\" value=\"1\">BER</option>\r\n" + 
							"												     \r\n" + 
							"													    <option name=\"Minor Repair\" value=\"3\">Minor Repair</option>\r\n" + 
							"												     \r\n" + 
							"													    <option name=\"DOWNGRADED\" value=\"2\">DOWNGRADED</option>\r\n" + 
							"												     \r\n" + 
							"												 </select></td>\r\n" + 
							"											<td ><input type=\"date\" name=\"unsv_from_dt1\" id=\"unsv_from_dt1\" maxlength=\"10\"  class=\"form-control\"></td> \r\n" + 
							
							"											<td ><input id=\"b_cost1\" name=\"b_cost1\"  type=\"text\" class=\"form-control-sm\" value=\"\" onkeypress=\"return isNumber(event);\" autocomplete=\"off\"></td>\r\n" + 
							"											  <td >	<input type=\"date\" name=\"proc_date1\" id=\"proc_date1\" maxlength=\"10\"></td>\r\n" + 
							"	<td ><select id=\"b_head1\" name=\"b_head1\" class=\"form-control b_head\" >\r\n" + 
							 "				<option value=\"0\">--Select--</option>\r\n" ;
							
							//peri first row
							for (int i = 0; i < getbudget.size(); i++) {
								Pp_jsp+="<option value='"+getbudget.get(i).get("id")+"' name='"+getbudget.get(i).get("budget_head")+"' data-id='"+getbudget.get(i).get("id")+"' >"+getbudget.get(i).get("budget_head")+"</option>\r\n" ;

							}
							Pp_jsp+="</select></td>\r\n" +
							"<td ><select id=\"b_code1\" name=\"b_code1\" class=\"form-control b_code\">\r\n" + 
							"<option value=\"0\">--Select--</option>\r\n" ;
							for (int i = 0; i < getbudget.size(); i++) {
								Pp_jsp+="<option value='"+getbudget.get(i).get("id")+"' name='"+getbudget.get(i).get("budget_code")+"' class='\"+option "+getbudget.get(i).get("id")+"'>"+getbudget.get(i).get("budget_code")+"</option>\r\n" ; 

							}
							Pp_jsp+="</select></td>\r\n" +
							"                <td  onclick=\"formopen(1)\" id=\"id_add1\"  align=\"center\" /><i class=\"fa fa-plus\" aria-hidden=\"true\" style=\"color:#17a2b8;font-size:25px;\"></i></td>\r\n" + 
							"            </tr>\r\n" + 
							"\r\n" + 
							"\r\n" + 
							"\r\n" + 
							"<!-- onkeypress=\"return validateInt(event)\"  -->\r\n" + 
							"\r\n" + 
							"\r\n" + 
							"\r\n" + 
							"\r\n" + 
							"         </tbody>\r\n" + 
							"   <tbody>\r\n" + 
							"\r\n" + 
							"      </tbody>\r\n" + 
							"    </table>\r\n" + 
							"<div id=\"bottom_anchor\"></div>\r\n" + 
							"      <td class=\"center\" class=\"avgs\"><span id=\"avg\" hidden=\"true\">0</span></td>\r\n" + 
							"            <td class=\"center\" class=\"avgs\"><span id=\"avg1\" hidden=\"true\">0</span></td>\r\n" + 
							"\r\n" + 
							"</div>\r\n" + 
							"   <input type=\"hidden\" id=\"count\" name=\"count\" value=\"1\">\r\n" + 
							"</div> \r\n" + 
							"<!-- export table start -->\r\n" + 
							"<!--\r\n" + 
							"<div class=\"first_1\" id=\"first_1\" style=\"display: none;\" > \r\n" + 
							"     <table border=\"4\" id=\"table_show_1\" >\r\n" + 
							"   \r\n" + 
							"      <thead>\r\n" + 
							"        <tr>\r\n" + 
							"          <th>Month Validation</th>\r\n" + 
							"          <th>Sus No</th>\r\n" + 
							"          <th>Unit Name</th>\r\n" + 
							"          <th>Sub Unit Tested</th>\r\n" + 
							"          <th>Camp Layout</th>\r\n" + 
							"          <th>Briefing</th>\r\n" + 
							"          <th>Snap Sit</th>\r\n" + 
							"          <th>Op Role</th>\r\n" + 
							"          <th>Debriefing</th>\r\n" + 
							"          <th>Tug Of War</th>\r\n" + 
							"          <th>Social Aspects</th>\r\n" + 
							"          <th>Adm Task</th>\r\n" + 
							"           <!-- <th>Total</th> -->\r\n" + 
							"\r\n" + 
							"         \r\n" + 
							"        </tr>\r\n" + 
							"      </thead>\r\n" + 
							"      <tbody>  \r\n" + 
							"      </tbody>\r\n" + 
							"  \r\n" + 
							"    </table> \r\n" + 
							"  </div>\r\n" + 
							"\r\n" + 
							"  <div class=\"second_2\" id=\"second_2\"> \r\n" + 
							"\r\n" + 
							"\r\n" + 
							"<!-- style=\"display: none;\" -->\r\n" + 
							"<table border=\"4\" id=\"table_show_2\" style=\"display: none;\" > \r\n" + 
							"  <thead>\r\n" + 
							"    <tr>\r\n" + 
							"				\r\n" + 
							"                <th >Peripheral Type</th>\r\n" + 
							"                <th >Make/Brand Name</th>\r\n" + 
							"                <th >Model Name</th>\r\n" + 
							"                <th >Warranty</th>\r\n" + 
							"                <th >Warranty Upto</th>\r\n" + 
							"                <th >Type of Peripheral HW</th>\r\n" + 
							"                <th >Model Type</th>\r\n" + 
							"                <th >Year of Proc</th>\r\n" + 
							"                <th >Year of Manufacturing</th>\r\n" + 
							"                <th >Remarks</th>\r\n" + 
							"                <th >Model Number</th>\r\n" + 
							"                <th >Machine No</th>\r\n" + 
							"                <th >IS Networked</th>\r\n" + 
							"                <th >IP Address(For Networked Device)</th>\r\n" + 
							"				 <th >UPS Capacity</th>\r\n" + 
							"                <th >Monochrome/Color</th>\r\n" + 
							"                <th >Max Paper Size</th>\r\n" + 
							"                <th >Connectivity Type</th>\r\n" + 
							"                <th >Print</th>\r\n" + 
							"                <th >Scan</th>\r\n" + 
							"                <th >Photography</th>\r\n" + 
							"                <th >Colour</th>\r\n" + 
							"				<th >Capacity(Lumens)</th> \r\n" + 
							"				<th >Hardware Interface</th>\r\n" + 
							"				<th >Resolution</th>\r\n" + 
							"				<th >Display Size</th>\r\n" + 
							"				<th >Display Connector</th>\r\n" + 
							"				<th >No Of Ports</th>\r\n" + 
							"				<th >Network Features</th>\r\n" + 
							"				<th >Ethernet Port</th>\r\n" + 
							"				<th >Management Layer</th>\r\n" + 
							"				<th >Display Type</th>\r\n" + 
							"				<th >Display Size</th>\r\n" + 
							"				<th >Display Connector</th>\r\n" + 
							"				<th >Serviceable State</th>\r\n" + 
							"				<th >UN-Serviceable State</th>\r\n" + 
							"				<th >Un-servicable from</th>\r\n" + 
							
							"				<th >Proc Cost</th>\r\n" + 
							"				<th >Proc Date</th>\r\n" + 
							"				<th >Budget Head</th>\r\n" + 
							"				<th >Budget Code</th>"+
							"    </tr>\r\n" + 
							"  </thead>\r\n" + 
							"  <tbody>\r\n" + 
							"  </tbody> \r\n" + 
							"</table>\r\n" + 
							"   </div>\r\n" + 
							"\r\n" + 
							"\r\n" + 
							"\r\n" + 
							"<br/>\r\n" + 
							"\r\n" + 
							"\r\n" + 
							"\r\n" + 
							"\r\n" + 
							"<center><input type=\"btnExport\" class=\"btn btn-primary btn-sm\" onclick=\"return validate();return AddData_second();\" value=\"Export To Excel\" ></center>\r\n" + 
							"\r\n" + 
							" <br><br><br>\r\n" + 
							"<!-- export table end -->\r\n" + 
							"  </div>\r\n" + 
							"  \r\n" + 
							"\r\n" + 
							"\r\n" + 
							"</div>\r\n" + 
							"\r\n" + 
							"\r\n" + 
							"  </div>\r\n" + 
							"</div>\r\n" + 
							"<div class=\"digital_india\" style=\"position: fixed;bottom: 20px;\">\r\n" + 
							
							"  </div>\r\n" + 
							" \r\n" + 
							"</body>\r\n" + 
							"\r\n" + 
							"<!-- <script type=\"text/javascript\">\r\n" + 
							"        \r\n" + 
							"    </script> -->\r\n" + 
							"    \r\n" + 
							"<script>\r\n" + 
							"\r\n" + 
							"/*function moveScroll(){\r\n" + 
							"    var scroll = $(window).scrollTop();\r\n" + 
							"    var anchor_top = $(\"#show\").offset().top;\r\n" + 
							"    var anchor_bottom = $(\"#bottom_anchor\").offset().top;\r\n" + 
							"    if (scroll>anchor_top && scroll<anchor_bottom) {\r\n" + 
							"    clone_table = $(\"#clone\");\r\n" + 
							"    if(clone_table.length == 0){\r\n" + 
							"        clone_table = $(\"#show\").clone();\r\n" + 
							"        clone_table.attr('id', 'clone');\r\n" + 
							"        clone_table.css({position:'fixed', \r\n" + 
							"                  'border' : '4',\r\n" + 
							"                 'pointer-events': 'none',\r\n" + 
							"                 top:0});\r\n" + 
							"        clone_table.width($(\"#show\").width());\r\n" + 
							"        $(\"#addQuantity\").append(clone_table);\r\n" + 
							"        $(\"#clone\").css({visibility:'hidden'});\r\n" + 
							"        $(\"#clone thead\").css({visibility:'visible'});\r\n" + 
							"    }\r\n" + 
							"    } else {\r\n" + 
							"    $(\"#clone\").remove();\r\n" + 
							"    }\r\n" + 
							"}\r\n" + 
							"$(window).scroll(moveScroll);*/\r\n" +
                          
							" function moveScroll(){\r\n" + 
							"    var scroll = $(window).scrollTop();\r\n" + 
							"    var anchor_top = $(\"#show\").offset().top;\r\n" + 
							"    var anchor_bottom = $(\"#bottom_anchor\").offset().top;\r\n" + 
							"    if (scroll > anchor_top && scroll < anchor_bottom) {\r\n" + 
							"        clone_table = $(\"#clone\");\r\n" + 
							"        if(clone_table.length === 0) {          \r\n" + 
							"            clone_table = $(\"#show\").clone();\r\n" + 
							"            clone_table.attr({id: \"clone\"})\r\n" + 
							"            .css({\r\n" + 
							"                position: \"fixed\",\r\n" + 
							"                \"pointer-events\": \"none\",\r\n" + 
							"                 top:0\r\n" + 
							"            })\r\n" + 
							"            .width($(\"#show\").width());\r\n" + 
							"\r\n" + 
							"            $(\"#addQuantity\").append(clone_table);\r\n" + 
							"            // dont hide the whole table or you lose border style & \r\n" + 
							"            // actively match the inline width to the #maintable width if the \r\n" + 
							"            // container holding the table (window, iframe, div) changes width          \r\n" + 
							"            $(\"#clone\").width($(\"#show\").width());\r\n" + 
							"            // only the clone thead remains visible\r\n" + 
							"            $(\"#clone thead\").css({\r\n" + 
							"                visibility:\"visible\"\r\n" + 
							"            });\r\n" + 
							"            // clone tbody is hidden\r\n" + 
							"            $(\"#clone tbody\").css({\r\n" + 
							"                visibility:\"hidden\"\r\n" + 
							"            });\r\n" + 
							"            // add support for a tfoot element\r\n" + 
							"            // and hide its cloned version too\r\n" + 
							"            /*var footEl = $(\"#clone tfoot\");\r\n" + 
							"            if(footEl.length){\r\n" + 
							"                footEl.css({\r\n" + 
							"                    visibility:\"hidden\"\r\n" + 
							"                });\r\n" + 
							"            }*/\r\n" + 
							"        }\r\n" + 
							"    } \r\n" + 
							"    else {\r\n" + 
							"        $(\"#clone\").remove();\r\n" + 
							"    }\r\n" + 
							"}\r\n" + 
							"$(window).scroll(moveScroll);\r\n" + 
							"\r\n" + 
							"\r\n" + 
							"\r\n" + 
							    "                              $(\".b_code option\").hide();\r\n" + 
	                            "		     				$(document).on(\"change\", \".b_head\", function(){\r\n" + 
	                            "		     					\r\n" + 
	                            "		     						var el = $(this);\r\n" + 
								"		     		\r\n" + 
								"		     			var id = el.find(\"option:selected\").data(\"id\");\r\n" + 
								"		     							\r\n" + 
								"		     							el.closest(\"tr\").find(\".b_code\").find(\"option\").hide();\r\n" + 
								"		     							el.closest(\"tr\").find(\".b_code\").find(\".\"+id).show();\r\n" + 
								"		     						\r\n" + 
								"		     					}); "+
							" x=1;//declare export variable \r\n" + 
							"function formopen(x){\r\n" + 
							"\r\n" + 
							"//document.getElementById('show').style.width=\"100%\";\r\n" + 
							"\r\n" + 
							"//endurun(x);\r\n" + 
							"\r\n" + 
							"if($(\"#assets_type\"+x).val()==0 || $(\"#assets_type\"+x).val()==\"\"){\r\n" + 
							"		alert(\"Please Select Computing Assets Type \");\r\n" + 
							"		$(\"#assets_type\"+x).focus();\r\n" + 
							"		return false;\r\n" + 
							"		}\r\n" + 
							"		\r\n" + 
							"		if($(\"#make_name\"+x).val()==0 || $(\"#make_name\"+x).val()==\"\"){\r\n" + 
							"		alert(\"Please Select Make/Brand Name\");\r\n" + 
							"		$(\"#make_name\"+x).focus();\r\n" + 
							"		return false;\r\n" + 
							"		}\r\n" + 
							"		\r\n" + 
							"		\r\n" + 
							"		if($(\"#model_name\"+x).val()==0 || $(\"#model_name\"+x).val()==\"\"){\r\n" + 
							"		alert(\"Please Select Model Name\");\r\n" + 
							"		$(\"#model_name\"+x).focus();\r\n" + 
							"		return false;\r\n" + 
							"		}\r\n" + 
							"		\r\n" + 
							"		if($(\"#type_of_hw\"+x).val()==0 || $(\"#type_of_hw\"+x).val()==\"\"){\r\n" + 
							"		alert(\"Please Select Type Of Peripheral HW\");\r\n" + 
							"		$(\"#type_of_hw\"+x).focus();\r\n" + 
							"		return false;\r\n" + 
							"		}\r\n" + 
							"		\r\n" + 
							"		\r\n" + 
							"		if($(\"#type\"+x).val()==0 || $(\"#type\"+x).val()==\"\"){\r\n" + 
							"		alert(\"Please Select Model Type\");\r\n" + 
							"		$(\"#type\"+x).focus();\r\n" + 
							"		return false;\r\n" + 
							"		}\r\n" + 
							"		\r\n" + 
							"		if($(\"#model_no\"+x).val()==0 || $(\"#model_no\"+x).val()==\"\"){\r\n" + 
							"		alert(\"Please Enter Model Number\");\r\n" + 
							"		$(\"#model_no\"+x).focus();\r\n" + 
							"		return false;\r\n" + 
							"		}\r\n" + 
							"		\r\n" + 
							"		if($(\"#machine_no\"+x).val()==0 || $(\"#machine_no\"+x).val()==\"\"){\r\n" + 
							"		alert(\"Please Enter Machine Number\");\r\n" + 
							"		$(\"#machine_no\"+x).focus();\r\n" + 
							"		return false;\r\n" + 
							"		}"+
							"var b =$(\"select#assets_type\"+x).val();\r\n" + 
							"	 if(b=='8')\r\n" + 
							"		 {\r\n" + 
							"		 if( $(\"#ups_capacity\"+x).val()==\"\" || $(\"#ups_capacity\"+x).val()==\"0\"){\r\n" + 
							"				alert(\"Please Select UPS Capacity \");\r\n" + 
							"				$(\"#ups_capacity\"+x).focus();\r\n" + 
							"				return false;\r\n" + 
							"			}\r\n" + 
							"		 \r\n" + 
							"		 }	\r\n" +
							"\r\n" + 
							"	 else if(b=='12')\r\n" + 
							"		{\r\n" + 
							"		\r\n" + 
							"		 if( $(\"#paper_size\"+x).val()==\"\" || $(\"#paper_size\"+x).val()==\"0\"){\r\n" + 
							"				alert(\"Please Select Max Paper Size \");\r\n" + 
							"				$(\"#paper_size\"+x).focus();\r\n" + 
							"				return false;\r\n" + 
							"			}\r\n" + 
							"		 if( $(\"#connectivity_type\"+x).val()==\"\" || $(\"#connectivity_type\"+x).val()==\"0\"){\r\n" + 
							"				alert(\"Please Select Connectivity Type \");\r\n" + 
							"				$(\"#connectivity_type\"+x).focus();\r\n" + 
							"				return false;\r\n" + 
							"			}\r\n" + 
							"		 \r\n" + 
							"		}		 \r\n" + 
							"\r\n" + 
							"\r\n" + 
							"	else if(b=='14')\r\n" + 
							"		{\r\n" + 
							"		 if( $(\"#capacity\"+x).val()==\"\"){\r\n" + 
							"			 	alert(\"Please Enter Capacity(Lumens)\")  \r\n" + 
							"				$(\"#capacity\"+x).focus();\r\n" + 
							"				return false;\r\n" + 
							"		}\r\n" + 
							"		 if( $(\"#hw_interface\"+x).val()==\"\" || $(\"#hw_interface\"+x).val()==\"0\" ){\r\n" + 
							"			 	alert(\"Please Select Hardware Interface\")  \r\n" + 
							"				$(\"#hw_interface\"+x).focus();\r\n" + 
							"				return false;\r\n" + 
							"		}\r\n" + 
							"		 \r\n" + 
							"		}\r\n" + 
							"		\r\n" + 
							"	 else if(b=='16')\r\n" + 
							"		{\r\n" + 
							"		 if( $(\"#no_of_ports\"+x).val()==\"\"){\r\n" + 
							"			 	alert(\"Please Enter No Of Ports\")  \r\n" + 
							"				$(\"#no_of_ports\"+x).focus();\r\n" + 
							"				return false;\r\n" + 
							"		}\r\n" + 
							"		 if( $(\"#network_features\"+x).val()==\"\" || $(\"#network_features\"+x).val()==\"0\" ){\r\n" + 
							"			 	alert(\"Please Select Network Features\")  \r\n" + 
							"				$(\"#network_features\"+x).focus();\r\n" + 
							"				return false;\r\n" + 
							"		}\r\n" + 
							"		 if( $(\"#ethernet_port\"+x).val()==\"\" || $(\"#ethernet_port\"+x).val()==\"0\" ){\r\n" + 
							"			 	alert(\"Please Select Ethernet Port\")  \r\n" + 
							"				$(\"#ethernet_port\"+x).focus();\r\n" + 
							"				return false;\r\n" + 
							"		}\r\n" + 
							"\r\n" + 
							"		}"+
							
							"	else if(b=='15')\r\n" + 
							"		{\r\n" + 
							"		 if( $(\"#resolution\"+x).val()==\"\"){\r\n" + 
							"			 	alert(\"Please Enter Resolution\")  \r\n" + 
							"				$(\"#resolution\"+x).focus();\r\n" + 
							"				return false;\r\n" + 
							"		}\r\n" + 
							"		 if( $(\"#v_display_size\"+x).val()==\"\"){\r\n" + 
							"			 	alert(\"Please Enter Display Size\")  \r\n" + 
							"				$(\"#v_display_size\"+x).focus();\r\n" + 
							"				return false;\r\n" + 
							"		}\r\n" + 
							"		 if( $(\"#v_display_connector\"+x).val()==\"\" || $(\"#v_display_connector\").val()==\"0\" ){\r\n" + 
							"			 	alert(\"Please Select Display Connector\")  \r\n" + 
							"				$(\"#v_display_connector\"+x).focus();\r\n" + 
							"				return false;\r\n" + 
							"		}\r\n" + 
							"		}"+
							
							"else if (b == '2') {\r\n" + 
							"			if ($(\"#display_type\"+x).val() == \"\") {\r\n" + 
							"				alert(\"Please Enter Display Type\")\r\n" + 
							"				$(\"#display_type\"+x).focus();\r\n" + 
							"				return false;\r\n" + 
							"			}\r\n" + 
							"			if ($(\"#display_size\"+x).val() == \"\") {\r\n" + 
							"				alert(\"Please Enter Display Size\")\r\n" + 
							"				$(\"#display_size\"+x).focus();\r\n" + 
							"				return false;\r\n" + 
							"			}\r\n" + 
							"			if ($(\"#display_connector\"+x).val() == \"\"\r\n" + 
							"					|| $(\"#display_connector\"+x).val() == \"0\") {\r\n" + 
							"				alert(\"Please Select Display Connector\")\r\n" + 
							"				$(\"#display_connector\"+x).focus();\r\n" + 
							"				return false;\r\n" + 
							"			}\r\n" + 
							"\r\n" + 
							"		}"+
							
							"		if ($(\"#s_state\"+x).val() == \"\" || $(\"#s_state\"+x).val() == \"0\") {\r\n" + 
							"			alert(\"Please Select Serviceable State\");\r\n" + 
							"			$(\"#s_state\"+x).focus();\r\n" + 
							"			return false;\r\n" + 
							"		}\r\n" + 
							"		if ($(\"#s_state\"+x).val() == 2) {\r\n" + 
							"			if ($(\"#unserviceable_state\"+x).val() == \"\" || $(\"#unserviceable_state\"+x).val() == \"0\") {\r\n" + 
							"				alert(\"Please Select UN-Serviceable State\");\r\n" + 
							"				$(\"#unserviceable_state\"+x).focus();\r\n" + 
							"				return false;\r\n" + 
							"			}\r\n" + 
							"			if( $(\"#unsv_from_dt\"+x).val()==\"\" || $(\"#unsv_from_dt\"+x).val()==\"0\"){\r\n" + 
							"				alert(\"Please Select UN-Serviceable From Date\");\r\n" + 
							"				$(\"#unsv_from_dt\"+x).focus();\r\n" + 
							"				return false;\r\n" + 
							"			}\r\n" + 
							"		}"+
							"if ($(\"#b_head\"+x).val() == \"\" || $(\"#b_head\"+x).val() == \"0\") {\r\n" + 
							"			alert(\"Please Select Budget Head\");\r\n" + 
							"			$(\"#b_head\"+x).focus();\r\n" + 
							"			return false;\r\n" + 
							"		}\r\n" + 
							"\r\n" + 
							"		if ($(\"#b_code\"+x).val() == \"\" || $(\"#b_code\"+x).val() == \"0\") {\r\n" + 
							"			alert(\"Please Select Budget Code\");\r\n" + 
							"			$(\"#b_code\"+x).focus();\r\n" + 
							"			return false;\r\n" + 
							"		}"+
							"\r\n" + 
							"\r\n" + 
							"\r\n" + 
							"   $(\"#id_add\"+x).hide();\r\n" + 
							"   $(\"#id_remove\"+x).hide();\r\n" + 
							"  \r\n" + 
							"   x= x+1;\r\n" + 
							"\r\n" + 
							"   \r\n" + 
							"\r\n" + 
							"     $(\"input#count\").val(x);\r\n" + 
							"     $(\"table#show\").append('<tr id=\"tr_id'+x+'\"><td align=\"center\" width=\"1%;\">'+x+'</td>'\r\n" + 
							"	\r\n" + 
							"	 \r\n" + 
							
						" +'<td><select id=\"assets_type'+x+'\" name=\"assets_type'+x+'\" class=\"form-control\" onchange=\"fn_hide_show(this,'+x+');\">'"+
	
					  "+'<option value=\"0\">--Select--</option>'\r\n" ; 
									    for(int i=0;i<getpheral_type.size();i++) {
									    	Pp_jsp+= "+'<option value=\""+getpheral_type.get(i).get("id")+"\" name=\""+getpheral_type.get(i).get("assets_name")+"\">"+getpheral_type.get(i).get("assets_name")+"</option>'\r\n" ; 
										         
										     }
									    Pp_jsp+= "+'</select>'\r\n"+
										     "+'</td> '";
							    
							
				 
							Pp_jsp+="+'<td ><select id=\"make_name'+x+'\" name=\"make_name'+x+'\" class=\"form-control\" onchange=\"fn_modelName();\">'"+
							 "+'<option value=\"0\">--Select--</option>'";
							 for(int i=0;i<getmake.size();i++) {
								 Pp_jsp+= "+'<option value=\""+getmake.get(i).get("id")+"\" name=\""+getmake.get(i).get("make_name")+"\">"+getmake.get(i).get("make_name")+"</option>'\r\n" ; 
								         
								     }
							 Pp_jsp+= "+'</select>'\r\n"+
								     "+'</td> '";
							
							 Pp_jsp+="+'<td ><select id=\"model_name'+x+'\" name=\"model_name'+x+'\" class=\"form-control\">'"
							 		+ "+'<option value=\"0\">--Select--</option>'";
							 		 for(int i=0;i<getmodel.size();i++) {
										 Pp_jsp+= "+'<option value=\""+getmodel.get(i).get("id")+"\" name=\""+getmodel.get(i).get("model_name")+"\">"+getmodel.get(i).get("model_name")+"</option>'\r\n" ; 
										         
										     }
								Pp_jsp+= "+'</select>'\r\n"+
										     "+'</td> '";
							 		
				
								
								
								
							Pp_jsp+="+' <td ><input id=\"warrantycheckyes'+x+'\" name=\"warrantycheck'+x+'\" checked=\"checked\" type=\"radio\" value=\"Yes\" onclick=\"warrenty_show(this,'+x+');\">&nbsp; <label for=\"yes\">Yes</label>&nbsp;   <input id=\"warrantycheckno'+x+'\" name=\"warrantycheck'+x+'\" type=\"radio\" value=\"No\" onclick=\"warrenty_show(this,'+x+');\">&nbsp;  <label for=\"no\">No</label></td>'\r\n" + 
							"		\r\n" ;
							Pp_jsp+="+' <td ><input type=\"date\" name=\"warranty_dt'+x+'\" id=\"warranty_dt'+x+'\" maxlength=\"10\"  class=\"form-control\"></td>'\r\n" + 
							"											\r\n" ;
							
							Pp_jsp+="+'<td ><select id=\"type_of_hw'+x+'\" name=\"type_of_hw'+x+'\" class=\"form-control\">'"
					 		+ "+'<option value=\"0\">--Select--</option>'";
					 		 for(int i=0;i<gethwype.size();i++) {
								 Pp_jsp+= "+'<option value=\""+gethwype.get(i).get("id")+"\" name=\""+gethwype.get(i).get("type_of_hw")+"\">"+gethwype.get(i).get("type_of_hw")+"</option>'\r\n" ; 
								         
								     }
						Pp_jsp+= "+'</select>'\r\n"+
								     "+'</td> '";
							
							
					Pp_jsp+="+'<td ><select id=\"type'+x+'\" name=\"type'+x+'\" class=\"form-control\">'"
					 		+ "+'<option value=\"0\">--Select--</option>'";
					 		 for(int i=0;i<gettype.size();i++) {
								 Pp_jsp+= "+'<option value=\""+gettype.get(i).get("id")+"\" name=\""+gettype.get(i).get("type")+"\">"+gettype.get(i).get("type")+"</option>'\r\n" ; 
								         
								     }
						Pp_jsp+= "+'</select>'\r\n"+
								     "+'</td> '";
							
							
							
						Pp_jsp+="+'<td  ><input id=\"year_of_proc'+x+'\" name=\"year_of_proc'+x+'\"  type=\"text\" class=\"form-control\"   onkeypress=\"return isNumber(event);\" onkeyup=\"validateYear(this);\" onblur=\"validateYearLn(this);\"  maxlength=\"4\" autocomplete=\"off\"></td>' \r\n" + 
							"			\r\n" ;
						Pp_jsp+="		+'<td ><input id=\"year_of_manufacturing'+x+'\" name=\"year_of_manufacturing'+x+'\"  type=\"text\" class=\"form-control\"  onkeyup=\"validateYear(this);\" onblur=\"validateYearLn(this);\"  onkeypress=\"return isNumber(event);\" maxlength=\"4\" autocomplete=\"off\"></td>'	\r\n" + 
							"			\r\n" ;
						Pp_jsp+="		+'<td ><textarea id=\"remarks'+x+'\" name=\"remarks'+x+'\" autocomplete=\"off\" class=\"form-control \"></textarea></td>'\r\n" + 
							"			\r\n" ; 
						Pp_jsp+="		+'<td ><input type=\"text\" id=\"model_no'+x+'\" name=\"model_no'+x+'\" class=\"form-control \" autocomplete=\"off\" onkeypress=\"return isNumber(event);\"></td>'	\r\n" + 
							"			\r\n" ;
						Pp_jsp+="		+'<td ><input type=\"text\" id=\"machine_no'+x+'\" name=\"machine_no'+x+'\" class=\"form-control \" autocomplete=\"off\" onkeypress=\"return isNumber(event);\"></td>'	\r\n" + 
							"			\r\n" ;
						Pp_jsp+="		+' <td ><input type=\"radio\" id=\"is_networkedyes'+x+'\" checked=\"checked\" value=\"Yes\"  name=\"is_networked'+x+'\" onclick=\"networked_show(this,'+x+');\">&nbsp; <label for=\"yes\">Yes</label>&nbsp;    <input type=\"radio\" id=\"is_networkedno'+x+'\" name=\"is_networked'+x+'\" value=\"No\" onclick=\"networked_show(this,'+x+');\" >&nbsp;   <label for=\"no\">No</label></td>'\r\n" + 
							"		\r\n" ;
						Pp_jsp+="		+'<td ><input type=\"text\" id=\"ip_address'+x+'\" name=\"ip_address'+x+'\" maxlength=\"15\" class=\"form-control \" autocomplete=\"off\" onchange=\"ValidateIPaddress(this,'+x+');\"></td>'\r\n" + 
							"                \r\n" ;
						
						
						
						Pp_jsp+="+'<td ><select id=\"ups_capacity'+x+'\" name=\"ups_capacity'+x+'\" class=\"form-control\">'"
						 		+ "+'<option value=\"0\">--Select--</option>'";
						 		 for(int i=0;i<ups_capacityNew.size();i++) {
									 Pp_jsp+= "+'<option value=\""+ups_capacityNew.get(i).get("id")+"\" name=\""+ups_capacityNew.get(i).get("ups_capacity")+"\">"+ups_capacityNew.get(i).get("ups_capacity")+"</option>'\r\n" ; 
									         
									     }
							Pp_jsp+= "+'</select>'\r\n"+
									     "+'</td> '";
						
						
						
						 
							Pp_jsp+="		+'<td ><input type=\"radio\" id=\"monochrome'+x+'\" name=\"monochrome_color_radio'+x+'\" value=\"1\" >&nbsp;<label for=\"monochrome\">Monochrome</label>&nbsp; <input type=\"radio\" id=\"monochrome_color'+x+'\" name=\"monochrome_color_radio'+x+'\" value=\"2\" checked=\"checked\">&nbsp; <label for=\"color\">Color</label><br></td>'\r\n" + 
							"		\r\n" ;
							
							Pp_jsp+="+'<td ><select id=\"paper_size'+x+'\" name=\"paper_size'+x+'\" class=\"form-control\">'"
							 		+ "+'<option value=\"0\">--Select--</option>'";
							 		 for(int i=0;i<paper_sizeNew.size();i++) {
										 Pp_jsp+= "+'<option value=\""+paper_sizeNew.get(i).get("id")+"\" name=\""+paper_sizeNew.get(i).get("label")+"\">"+paper_sizeNew.get(i).get("label")+"</option>'\r\n" ; 
										         
										     }
								Pp_jsp+= "+'</select>'\r\n"+
										     "+'</td> '";
							
										     
							
					Pp_jsp+="+'<td ><select id=\"connectivity_type'+x+'\" name=\"connectivity_type'+x+'\" class=\"form-control\">'"
					 		+ "+'<option value=\"0\">--Select--</option>'";
					 		 for(int i=0;i<paper_connetivity.size();i++) {
								 Pp_jsp+= "+'<option value=\""+paper_connetivity.get(i).get("id")+"\" name=\""+paper_connetivity.get(i).get("codevalue")+"\">"+paper_connetivity.get(i).get("codevalue")+"</option>'\r\n" ; 
								         
								     }
						Pp_jsp+= "+'</select>'\r\n"+
								     "+'</td> '";
							
						 
							 
					Pp_jsp+= "		+' <td > <input id=\"printyes'+x+'\" name=\"print'+x+'\" type=\"radio\"  value=\"Yes\">&nbsp; <label for=\"yes\">Yes</label>&nbsp;<input id=\"printno'+x+'\" name=\"print'+x+'\"  type=\"radio\" value=\"No\" checked=\"checked\">&nbsp;<label for=\"no\">No</label></td>'\r\n" + 
							"               \r\n" ;
					Pp_jsp+="		+' <td ><input id=\"scanyes'+x+'\"name=\"scan'+x+'\" type=\"radio\"  value=\"Yes\">&nbsp; <label for=\"yes\">Yes</label>&nbsp;<input id=\"scanno'+x+'\" name=\"scan'+x+'\"  type=\"radio\" value=\"No\" checked=\"checked\">&nbsp;<label for=\"no\">No</label></td>'\r\n" + 
							"              \r\n" ;
					Pp_jsp+="		+'  <td ><div class=\"col-md-2\"><input id=\"photographyyes'+x+'\"  name=\"photography'+x+'\"  type=\"radio\" value=\"Yes\">&nbsp; <label for=\"yes\">Yes</label>&nbsp;<input id=\"photographyno'+x+'\" name=\"photography'+x+'\"  type=\"radio\" value=\"No\" checked=\"checked\">&nbsp;<label for=\"no\">No</label></div></td> '\r\n" + 
							"		\r\n" ;
					Pp_jsp+="		+' <td ><div class=\"col-md-2\"><input id=\"colouryes'+x+'\" name=\"colour'+x+'\"  type=\"radio\" value=\"Yes\">&nbsp; <label for=\"yes\">Yes</label>&nbsp;<input id=\"colourno'+x+'\" name=\"colour'+x+'\"  type=\"radio\" value=\"No\" checked=\"checked\">&nbsp;<label for=\"no\">No</label></div></td>'\r\n" + 
							"													\r\n" ;
					Pp_jsp+="		+'<td ><input id=\"capacity'+x+'\" name=\"capacity'+x+'\" type=\"text\" class=\"form-control \" value=\"\" autocomplete=\"off\"></td> ' \r\n" + 
							"		\r\n" ;
					
					
					Pp_jsp+="+'<td ><select id=\"hw_interface'+x+'\" name=\"hw_interface'+x+'\" class=\"form-control\">'"
					 		+ "+'<option value=\"0\">--Select--</option>'";
					 		 for(int i=0;i<hardware_interfaceNew.size();i++) {
								 Pp_jsp+= "+'<option value=\""+hardware_interfaceNew.get(i).get("id")+"\" name=\""+hardware_interfaceNew.get(i).get("hardware_interface")+"\">"+hardware_interfaceNew.get(i).get("hardware_interface")+"</option>'\r\n" ; 
								         
								     }
						Pp_jsp+= "+'</select>'\r\n"+
								     "+'</td> '";
					
					
						Pp_jsp+= "		+'<td >	<input id=\"resolution'+x+'\" name=\"resolution'+x+'\" type=\"text\" class=\"form-control \" value=\"\" autocomplete=\"off\"></td>'\r\n" + 
							"													   \r\n" ;
						Pp_jsp+= 	"		+'<td ><input id=\"v_display_size'+x+'\" name=\"v_display_size'+x+'\" type=\"text\" class=\"form-control \" value=\"\" autocomplete=\"off\"></td>'\r\n" + 
							"		\r\n" ;
						
						
						Pp_jsp+="+'<td ><select id=\"v_display_connector'+x+'\" name=\"v_display_connector'+x+'\" class=\"form-control\">'"
						 		+ "+'<option value=\"0\">--Select--</option>'";
						 		 for(int i=0;i<type_connetivity.size();i++) {
									 Pp_jsp+= "+'<option value=\""+type_connetivity.get(i).get("id")+"\" name=\""+type_connetivity.get(i).get("label")+"\">"+type_connetivity.get(i).get("label")+"</option>'\r\n" ; 
									         
									     }
							Pp_jsp+= "+'</select>'\r\n"+
									     "+'</td> '";
						
						
							Pp_jsp+= 	"+'<td >  <input id=\"no_of_ports'+x+'\" name=\"no_of_ports'+x+'\" onkeypress=\"return isNumber(event);\" type=\"text\" class=\"form-control \" value=\"\" autocomplete=\"off\"></td>'\r\n" + 
							"		\r\n" ;
							
							
							Pp_jsp+="+'<td ><select id=\"network_features'+x+'\" name=\"network_features'+x+'\" class=\"form-control\">'"
							 		+ "+'<option value=\"0\">--Select--</option>'";
							 		 for(int i=0;i<getnetwork_features.size();i++) {
										 Pp_jsp+= "+'<option value=\""+getnetwork_features.get(i).get("id")+"\" name=\""+getnetwork_features.get(i).get("network_features")+"\">"+getnetwork_features.get(i).get("network_features")+"</option>'\r\n" ; 
										         
										     }
								Pp_jsp+= "+'</select>'\r\n"+
										     "+'</td> '";
								
								Pp_jsp+="+'<td ><select id=\"ethernet_port'+x+'\" name=\"ethernet_port'+x+'\" class=\"form-control\">'"
								 		+ "+'<option value=\"0\">--Select--</option>'";
								 		 for(int i=0;i<getethernet_portnew.size();i++) {
											 Pp_jsp+= "+'<option value=\""+getethernet_portnew.get(i).get("id")+"\" name=\""+getethernet_portnew.get(i).get("ethernet_port")+"\">"+getethernet_portnew.get(i).get("ethernet_port")+"</option>'\r\n" ; 
											         
											     }
									Pp_jsp+= "+'</select>'\r\n"+
											     "+'</td> '";
							
							
							 
									Pp_jsp+="+'<td ><select id=\"management_layer'+x+'\" name=\"management_layer'+x+'\" class=\"form-control\">'"
									 		+ "+'<option value=\"0\">--Select--</option>'";
									 		 for(int i=0;i<getmanagement_layernew.size();i++) {
												 Pp_jsp+= "+'<option value=\""+getmanagement_layernew.get(i).get("id")+"\" name=\""+getmanagement_layernew.get(i).get("management_layer")+"\">"+getmanagement_layernew.get(i).get("management_layer")+"</option>'\r\n" ; 
												         
												     }
										Pp_jsp+= "+'</select>'\r\n"+
												     "+'</td> '";
							
							Pp_jsp+= "		+'<td ><input id=\"display_type'+x+'\" name=\"display_type'+x+'\" type=\"text\" class=\"form-control \" value=\"\" autocomplete=\"off\"></td>'\r\n" + 
							"		\r\n" ; 
							Pp_jsp+= "		+'<td ><input id=\"display_size'+x+'\" name=\"display_size'+x+'\" type=\"text\" class=\"form-control \" value=\"\" autocomplete=\"off\"></td> '\r\n" + 
							"		\r\n" ;
							
							
							Pp_jsp+="+'<td ><select id=\"display_connector'+x+'\" name=\"display_connector'+x+'\" class=\"form-control\">'"
							 		+ "+'<option value=\"0\">--Select--</option>'";
							 		 for(int i=0;i<type_connetivity.size();i++) {
										 Pp_jsp+= "+'<option value=\""+type_connetivity.get(i).get("id")+"\" name=\""+type_connetivity.get(i).get("label")+"\">"+type_connetivity.get(i).get("label")+"</option>'\r\n" ; 
										         
										     }
								Pp_jsp+= "+'</select>'\r\n"+
										     "+'</td> '";
							
						
								Pp_jsp+="+'<td ><select id=\"s_state'+x+'\" name=\"s_state'+x+'\" class=\"form-control\" onchange=\"serviceStChange(this,'+x+');\"><option value=\"0\">--Select--</option><option name=\"Serviceable\" value=\"1\">Serviceable</option><option name=\"Un-serviceable\" value=\"2\">Un-serviceable</option></select></td>'\r\n" + 
							"		\r\n" + 
							"		+' <td ><select id=\"unserviceable_state'+x+'\" name=\"unserviceable_state'+x+'\" class=\"form-control\"><option value=\"0\" selected=\"selected\">--Select--</option><option name=\"BER\" value=\"1\">BER</option><option name=\"Minor Repair\" value=\"3\">Minor Repair</option><option name=\"DOWNGRADED\" value=\"2\">DOWNGRADED</option></select></td>'\r\n" + 
							"		\r\n" + 
							"		+'<td ><input type=\"date\" name=\"unsv_from_dt'+x+'\" id=\"unsv_from_dt'+x+'\" maxlength=\"10\"  class=\"form-control\"></td> '\r\n" + 
							"		\r\n" + 
							
							"		+'<td ><input id=\"b_cost'+x+'\" name=\"b_cost'+x+'\" onkeypress=\"return isNumber(event);\" type=\"text\" class=\"form-control\" value=\"\" autocomplete=\"off\"></td>'\r\n" + 
							"		\r\n" + 
							"		+'<td ><input type=\"date\" name=\"proc_date'+x+'\" id=\"proc_date'+x+'\"maxlength=\"10\"></td>'\r\n" + 
							"		\r\n" ;
								
								//peri add more
								Pp_jsp+="+'<td ><select id=\"b_head'+x+'\" name=\"b_head'+x+'\" class=\"form-control b_head\">'"
								 		+ "+'<option value=\"0\">--Select--</option>'";
								 		 for(int i=0;i<getbudget.size();i++) {
											 Pp_jsp+= "+'<option value=\""+getbudget.get(i).get("id")+"\" name=\""+getbudget.get(i).get("budget_head")+"\" data-id=\""+getbudget.get(i).get("id")+"\">"+getbudget.get(i).get("budget_head")+"</option>'\r\n" ;  
											         
											     }
									Pp_jsp+= "+'</select>'\r\n"+
											     "+'</td> '";
									
									Pp_jsp+="+'<td ><select id=\"b_code'+x+'\" name=\"b_code'+x+'\" class=\"form-control b_code\">'"
									 		+ "+'<option value=\"0\">--Select--</option>'";
									 		 for(int i=0;i<getbudget.size();i++) {
												 Pp_jsp+= "+'<option value=\""+getbudget.get(i).get("id")+"\" name=\""+getbudget.get(i).get("budget_code")+"\" class=\""+"option "+getbudget.get(i).get("id")+"\">"+getbudget.get(i).get("budget_code")+"</option>'\r\n" ;  
												         
												     }
										Pp_jsp+= "+'</select>'\r\n"+
												     "+'</td> '";
								
							
										Pp_jsp+="\r\n" + 
							"			+'<td align=\"center\" ><i class=\"fa fa-plus\" style=\"color:#17a2b8;font-size:25px;\"  id = \"id_add'+x+'\" onclick=\"formopen('+x+');\"></i>&nbsp;&nbsp;&nbsp;<i class=\"fa fa-minus\"  style=\"color:red;font-size:25px;\" id = \"id_remove'+x+'\" onclick=\"formopen_re('+x+');\"></i></td>'+'</tr>');\r\n" + 
							"    \r\n" + 
							"\r\n" + 
							"    }\r\n" + 
							"\r\n" + 
							"\r\n" + 
							"\r\n" + 
							"/*function validateInt(key) {\r\n" + 
							"  \r\n" + 
							"            var keycode = (key.which) ? key.which : key.keyCode;\r\n" + 
							"           \r\n" + 
							"            if (!(keycode == 8 || keycode == 46) && (keycode < 48 || keycode > 57)) {\r\n" + 
							"                return false;\r\n" + 
							"            }\r\n" + 
							"            else {\r\n" + 
							"                var parts = key.srcElement.value.split('.');\r\n" + 
							"                if (parts.length > 1 && keycode == 46)\r\n" + 
							"                    return false;\r\n" + 
							"                return true;\r\n" + 
							"            }\r\n" + 
							"}*/\r\n" + 
							"\r\n" + 
							"function validateFloatKeyPress(el, evt) {\r\n" + 
							"    var charCode = (evt.which) ? evt.which : event.keyCode;\r\n" + 
							"    var number = el.value.split('.');\r\n" + 
							"    if (charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57)) {\r\n" + 
							"        return false;\r\n" + 
							"    }\r\n" + 
							"    //just one dot\r\n" + 
							"    if(number.length>1 && charCode == 46){\r\n" + 
							"         return false;\r\n" + 
							"    }\r\n" + 
							"    //get the carat position\r\n" + 
							"    var caratPos = getSelectionStart(el);\r\n" + 
							"    var dotPos = el.value.indexOf(\".\");\r\n" + 
							"    if( caratPos > dotPos && dotPos>-1 && (number[1].length > 1)){\r\n" + 
							"        return false;\r\n" + 
							"    }\r\n" + 
							"    return true;\r\n" + 
							"}\r\n" + 
							"\r\n" + 
							"\r\n" + 	
							"function validate()\r\n" + 
							"{\r\n" + 
							"\r\n" + 
							" var q = $(\"#count\").val();\r\n" + 
							"  for(var y = 1; y <= q ; y++){\r\n" + 
							"  \r\n" + 
							"  if($(\"#assets_type\"+y).val()==0 || $(\"#assets_type\"+y).val()==\"\"){\r\n" + 
							"		alert(\"Please Select Computing Assets Type \");\r\n" + 
							"		$(\"#assets_type\"+y).focus();\r\n" + 
							"		return false;\r\n" + 
							"		}\r\n" + 
							"		\r\n" + 
							"		if($(\"#make_name\"+y).val()==0 || $(\"#make_name\"+y).val()==\"\"){\r\n" + 
							"		alert(\"Please Select Make/Brand Name\");\r\n" + 
							"		$(\"#make_name\"+y).focus();\r\n" + 
							"		return false;\r\n" + 
							"		}\r\n" + 
							"		\r\n" + 
							"		\r\n" + 
							"		if($(\"#model_name\"+y).val()==0 || $(\"#model_name\"+y).val()==\"\"){\r\n" + 
							"		alert(\"Please Select Model Name\");\r\n" + 
							"		$(\"#model_name\"+y).focus();\r\n" + 
							"		return false;\r\n" + 
							"		}\r\n" + 
							"		\r\n" + 
							"		if($(\"#type_of_hw\"+y).val()==0 || $(\"#type_of_hw\"+y).val()==\"\"){\r\n" + 
							"		alert(\"Please Select Type Of Peripheral HW\");\r\n" + 
							"		$(\"#type_of_hw\"+y).focus();\r\n" + 
							"		return false;\r\n" + 
							"		}\r\n" + 
							"		\r\n" + 
							"		\r\n" + 
							"		if($(\"#type\"+y).val()==0 || $(\"#type\"+y).val()==\"\"){\r\n" + 
							"		alert(\"Please Select Model Type\");\r\n" + 
							"		$(\"#type\"+y).focus();\r\n" + 
							"		return false;\r\n" + 
							"		}\r\n" + 
							"		\r\n" + 
							"		if($(\"#model_no\"+y).val()==0 || $(\"#model_no\"+y).val()==\"\"){\r\n" + 
							"		alert(\"Please Enter Model Number\");\r\n" + 
							"		$(\"#model_no\"+y).focus();\r\n" + 
							"		return false;\r\n" + 
							"		}\r\n" + 
							"		\r\n" + 
							"		if($(\"#machine_no\"+y).val()==0 || $(\"#machine_no\"+y).val()==\"\"){\r\n" + 
							"		alert(\"Please Enter Machine Number\");\r\n" + 
							"		$(\"#machine_no\"+y).focus();\r\n" + 
							"		return false;\r\n" + 
							"		}var b =$(\"select#assets_type\"+y).val();\r\n" + 
							"	 if(b=='8')\r\n" + 
							"		 {\r\n" + 
							"		 if( $(\"#ups_capacity\"+y).val()==\"\" || $(\"#ups_capacity\"+y).val()==\"0\"){\r\n" + 
							"				alert(\"Please Select UPS Capacity \");\r\n" + 
							"				$(\"#ups_capacity\"+y).focus();\r\n" + 
							"				return false;\r\n" + 
							"			}\r\n" + 
							"		 \r\n" + 
							"		 }	\r\n" + 
							"\r\n" + 
							"	 else if(b=='12')\r\n" + 
							"		{\r\n" + 
							"		\r\n" + 
							"		 if( $(\"#paper_size\"+y).val()==\"\" || $(\"#paper_size\"+y).val()==\"0\"){\r\n" + 
							"				alert(\"Please Select May Paper Size \");\r\n" + 
							"				$(\"#paper_size\"+y).focus();\r\n" + 
							"				return false;\r\n" + 
							"			}\r\n" + 
							"		 if( $(\"#connectivity_type\"+y).val()==\"\" || $(\"#connectivity_type\"+y).val()==\"0\"){\r\n" + 
							"				alert(\"Please Select Connectivity Type \");\r\n" + 
							"				$(\"#connectivity_type\"+y).focus();\r\n" + 
							"				return false;\r\n" + 
							"			}\r\n" + 
							"		 \r\n" + 
							"		}		 \r\n" + 
							"\r\n" + 
							"\r\n" + 
							"	else if(b=='14')\r\n" + 
							"		{\r\n" + 
							"		 if( $(\"#capacity\"+y).val()==\"\"){\r\n" + 
							"			 	alert(\"Please Enter Capacity(Lumens)\")  \r\n" + 
							"				$(\"#capacity\"+y).focus();\r\n" + 
							"				return false;\r\n" + 
							"		}\r\n" + 
							"		 if( $(\"#hw_interface\"+y).val()==\"\" || $(\"#hw_interface\"+y).val()==\"0\" ){\r\n" + 
							"			 	alert(\"Please Select Hardware Interface\")  \r\n" + 
							"				$(\"#hw_interface\"+y).focus();\r\n" + 
							"				return false;\r\n" + 
							"		}\r\n" + 
							"		 \r\n" + 
							"		}\r\n" + 
							"		\r\n" + 
							"	 else if(b=='16')\r\n" + 
							"		{\r\n" + 
							"		 if( $(\"#no_of_ports\"+y).val()==\"\"){\r\n" + 
							"			 	alert(\"Please Enter No Of Ports\")  \r\n" + 
							"				$(\"#no_of_ports\"+y).focus();\r\n" + 
							"				return false;\r\n" + 
							"		}\r\n" + 
							"		 if( $(\"#network_features\"+y).val()==\"\" || $(\"#network_features\"+y).val()==\"0\" ){\r\n" + 
							"			 	alert(\"Please Select Network Features\")  \r\n" + 
							"				$(\"#network_features\"+y).focus();\r\n" + 
							"				return false;\r\n" + 
							"		}\r\n" + 
							"		 if( $(\"#ethernet_port\"+y).val()==\"\" || $(\"#ethernet_port\"+y).val()==\"0\" ){\r\n" + 
							"			 	alert(\"Please Select Ethernet Port\")  \r\n" + 
							"				$(\"#ethernet_port\"+y).focus();\r\n" + 
							"				return false;\r\n" + 
							"		}\r\n" + 
							"\r\n" + 
							"		}	else if(b=='15')\r\n" + 
							"		{\r\n" + 
							"		 if( $(\"#resolution\"+y).val()==\"\"){\r\n" + 
							"			 	alert(\"Please Enter Resolution\")  \r\n" + 
							"				$(\"#resolution\"+y).focus();\r\n" + 
							"				return false;\r\n" + 
							"		}\r\n" + 
							"		 if( $(\"#v_display_size\"+y).val()==\"\"){\r\n" + 
							"			 	alert(\"Please Enter Display Size\")  \r\n" + 
							"				$(\"#v_display_size\"+y).focus();\r\n" + 
							"				return false;\r\n" + 
							"		}\r\n" + 
							"		 if( $(\"#v_display_connector\"+y).val()==\"\" || $(\"#v_display_connector\").val()==\"0\" ){\r\n" + 
							"			 	alert(\"Please Select Display Connector\")  \r\n" + 
							"				$(\"#v_display_connector\"+y).focus();\r\n" + 
							"				return false;\r\n" + 
							"		}\r\n" + 
							"		}else if (b == '2') {\r\n" + 
							"			if ($(\"#display_type\"+y).val() == \"\") {\r\n" + 
							"				alert(\"Please Enter Display Type\")\r\n" + 
							"				$(\"#display_type\"+y).focus();\r\n" + 
							"				return false;\r\n" + 
							"			}\r\n" + 
							"			if ($(\"#display_size\"+y).val() == \"\") {\r\n" + 
							"				alert(\"Please Enter Display Size\")\r\n" + 
							"				$(\"#display_size\"+y).focus();\r\n" + 
							"				return false;\r\n" + 
							"			}\r\n" + 
							"			if ($(\"#display_connector\"+y).val() == \"\"\r\n" + 
							"					|| $(\"#display_connector\"+y).val() == \"0\") {\r\n" + 
							"				alert(\"Please Select Display Connector\")\r\n" + 
							"				$(\"#display_connector\"+y).focus();\r\n" + 
							"				return false;\r\n" + 
							"			}\r\n" + 
							"\r\n" + 
							"		}		if ($(\"#s_state\"+y).val() == \"\" || $(\"#s_state\"+y).val() == \"0\") {\r\n" + 
							"			alert(\"Please Select Serviceable State\");\r\n" + 
							"			$(\"#s_state\"+y).focus();\r\n" + 
							"			return false;\r\n" + 
							"		}\r\n" + 
							"		if ($(\"#s_state\"+y).val() == 2) {\r\n" + 
							"			if ($(\"#unserviceable_state\"+y).val() == \"\" || $(\"#unserviceable_state\"+y).val() == \"0\") {\r\n" + 
							"				alert(\"Please Select UN-Serviceable State\");\r\n" + 
							"				$(\"#unserviceable_state\"+y).focus();\r\n" + 
							"				return false;\r\n" + 
							"			}\r\n" + 
							"			if( $(\"#unsv_from_dt\"+y).val()==\"\" || $(\"#unsv_from_dt\"+y).val()==\"0\"){\r\n" + 
							"				alert(\"Please Select UN-Serviceable From Date\");\r\n" + 
							"				$(\"#unsv_from_dt\"+y).focus();\r\n" + 
							"				return false;\r\n" + 
							"			}\r\n" + 
							"		}if ($(\"#b_head\"+y).val() == \"\" || $(\"#b_head\"+y).val() == \"0\") {\r\n" + 
							"			alert(\"Please Select Budget Head\");\r\n" + 
							"			$(\"#b_head\"+y).focus();\r\n" + 
							"			return false;\r\n" + 
							"		}\r\n" + 
							"\r\n" + 
							"		if ($(\"#b_code\"+y).val() == \"\" || $(\"#b_code\"+y).val() == \"0\") {\r\n" + 
							"			alert(\"Please Select Budget Code\");\r\n" + 
							"			$(\"#b_code\"+y).focus();\r\n" + 
							"			return false;\r\n" + 
							"		}\r\n" + 
							"	}\r\n" + 
							"	\r\n" + 
							"	 return AddData_second();\r\n" + 
							"  }"+
							"\r\n" + 
							"\r\n" + 
							"//thanks: http://javascript.nwbox.com/cursor_position/\r\n" + 
							"function getSelectionStart(o) {\r\n" + 
							"  if (o.createTextRange) {\r\n" + 
							"    var r = document.selection.createRange().duplicate()\r\n" + 
							"    r.moveEnd('character', o.value.length)\r\n" + 
							"    if (r.text == '') return o.value.length\r\n" + 
							"    return o.value.lastIndexOf(r.text)\r\n" + 
							"  } else return o.selectionStart\r\n" + 
							"}\r\n" + 
							"\r\n" + 
							"\r\n" + 
							"function formopen_re(R){\r\n" + 
							"   $(\"tr#tr_id\"+R).remove();\r\n" + 
							"   R = R-1;\r\n" + 
							"// x = x-1;\r\n" + 
							"   $(\"input#count\").val(R);\r\n" + 
							"   $(\"#id_add\"+R).show();\r\n" + 
							"   $(\"#id_remove\"+R).show();\r\n" + 
							"   getPersCount();\r\n" +  
							"}\r\n" + 
							"\r\n" + 
							"\r\n" + 
							"  \r\n" + 
							"function CheckColors(val,rowid){\r\n" + 
							"/*if(rowid == \"0\"){\r\n" + 
							"rowid =\"\";\r\n" + 
							"}*/\r\n" + 
							"\r\n" + 
							"document.getElementById(\"medical_category_remarks\"+rowid).style.width='200px';\r\n" + 
							"//alert(document.getElementById(\"medical_category_remarks\"+rowid));\r\n" + 
							" if(val=='--SELECT--'||val=='LMC'){\r\n" + 
							"  document.getElementById(\"medical_category_remarks\"+rowid).style.display='block';\r\n" + 
							"\r\n" + 
							" }else  {\r\n" + 
							"   document.getElementById(\"medical_category_remarks\"+rowid).style.display='none';\r\n" + 
							"   }\r\n" + 
							"}\r\n" + 
							"function getPersCount(){\r\n" + 
							"  //alert(\"hii\");\r\n" + 
							"\r\n" + 
							"\r\n" + 
							"    rowCount=$(\"#count\").val();\r\n" + 
							"      //document.getElementById(\"pers_type\"+rowCount);\r\n" + 
							"      var count1=0,count2=0,count3=0;\r\n" + 
							"\r\n" + 
							"     //alert(document.getElementById(\"pers_type\"+rowCount));\r\n" + 
							"    for(i=1;i<=rowCount;i++){\r\n" + 
							"\r\n" + 
							"           if( $(\"#pers_type\"+i).val() == 'OFFICER'){\r\n" + 
							"                count1++;      \r\n" + 
							"                 \r\n" + 
							"               \r\n" + 
							"              }\r\n" + 
							"            \r\n" + 
							"         else   if( $(\"#pers_type\"+i).val() == 'JCO'){\r\n" + 
							"                count2++;\r\n" + 
							"                 \r\n" + 
							"              \r\n" + 
							"              }\r\n" + 
							"             \r\n" + 
							"          else  if( $(\"#pers_type\"+i).val() == 'OR'){\r\n" + 
							"                count3++; \r\n" + 
							"                \r\n" + 
							"                \r\n" + 
							"              }\r\n" + 
							"              \r\n" + 
							"    }\r\n" + 
							"\r\n" + 
							"                $(\"#officer_spm\").html(count1);\r\n" + 
							"                $(\"#jco_spm\").html(count2);\r\n" + 
							"                $(\"#or_spm\").html(count3);\r\n" + 
							"}\r\n" + 
							"function getsum(){\r\n" + 
							" \r\n" + 
							"      var sum = 0;\r\n" + 
							"      var sum1 = 0;\r\n" + 
							"      var sum2 = 0;\r\n" + 
							"      var sum3 = 0;\r\n" + 
							"      var sum4 = 0;\r\n" + 
							"      var sum5 = 0;\r\n" + 
							"      var sum6 = 0;\r\n" + 
							"      var sum7 = 0;\r\n" + 
							"\r\n" + 
							"       sum += parseInt($(\"#camp_layout\").val());\r\n" + 
							"       sum1 += parseInt($(\"#briefing\").val());\r\n" + 
							"       sum2 += parseInt($(\"#snap_sit\").val());\r\n" + 
							"       sum3 += parseInt($(\"#op_role\").val());\r\n" + 
							"       sum4 += parseInt($(\"#dbriefing\").val());\r\n" + 
							"       sum5 += parseInt($(\"#tug_of_war\").val());\r\n" + 
							"       sum6 += parseInt($(\"#social_aspect\").val());\r\n" + 
							"       sum7 += parseInt($(\"#adm_task\").val());\r\n" + 
							"\r\n" + 
							"\r\n" + 
							"           Tsum = parseFloat(sum + sum1 + sum2 + sum3 + sum4 + sum5 + sum6 + sum7);\r\n" + 
							"      var Tsum_txt = $(Tsum).text();\r\n" + 
							"\r\n" + 
							"      $(avg).html(Tsum);\r\n" + 
							"      getTotal();\r\n" + 
							"\r\n" + 
							"      \r\n" + 
							"\r\n" + 
							"}\r\n" + 
							"\r\n" + 
							"function geteravg() {\r\n" + 
							"\r\n" + 
							"      \r\n" + 
							"         rc=$(\"#count\").val();\r\n" + 
							"      var sum = 0;\r\n" + 
							"      var average = 0;\r\n" + 
							"     \r\n" + 
							"      \r\n" + 
							"      for(i=1;i<=rc;i++)\r\n" + 
							"      {\r\n" + 
							"        sum += parseInt($(\"#endurance_run\"+i).val());\r\n" + 
							"        if(!Number.isNaN(sum)){\r\n" + 
							"          average = parseFloat(sum / rc);\r\n" + 
							"        }\r\n" + 
							"      } \r\n" + 
							"      if(average!=0){\r\n" + 
							"      $(avgV).html(average.toFixed(2));\r\n" + 
							"      getTotal();\r\n" + 
							"    }\r\n" + 
							"    else{$(\"#avgV\").text(\"\")}\r\n" + 
							"}\r\n" + 
							"\r\n" + 
							"function geteravg1() {\r\n" + 
							"\r\n" + 
							"      \r\n" + 
							"         rc=$(\"#count\").val();\r\n" + 
							"      var sum = 0;\r\n" + 
							"      var average = 0;\r\n" + 
							"     \r\n" + 
							"\r\n" + 
							"      for(i=1;i<=rc;i++)\r\n" + 
							"      {\r\n" + 
							"        sum += parseInt($(\"#day_fg\"+i).val());\r\n" + 
							"        if(!Number.isNaN(sum)){\r\n" + 
							"          average = parseFloat(sum / rc);\r\n" + 
							"        }\r\n" + 
							"      }\r\n" + 
							"      if(average!=0){\r\n" + 
							"        $(avgV1).html(average.toFixed(2));\r\n" + 
							"        getTotal();\r\n" + 
							"      }\r\n" + 
							"    else{$(\"#avgV1\").text(\"\")}\r\n" + 
							"}\r\n" + 
							"function geteravg2() {\r\n" + 
							"\r\n" + 
							"      \r\n" + 
							"         rc=$(\"#count\").val();\r\n" + 
							"      var sum = 0;\r\n" + 
							"      var average = 0;\r\n" + 
							"     \r\n" + 
							"\r\n" + 
							"      for(i=1;i<=rc;i++)\r\n" + 
							"      {\r\n" + 
							"        sum += parseInt($(\"#ni_fg\"+i).val());\r\n" + 
							"        if(!Number.isNaN(sum)){\r\n" + 
							"          average = parseFloat(sum / rc);\r\n" + 
							"        }\r\n" + 
							"      }\r\n" + 
							"      if(average!=0){\r\n" + 
							"        $(avgV2).html(average.toFixed(2));\r\n" + 
							"        getTotal();\r\n" + 
							"      }\r\n" + 
							"    else{$(\"#avgV2\").text(\"\")}\r\n" + 
							"}\r\n" + 
							"function geteravg3() {\r\n" + 
							"\r\n" + 
							"      \r\n" + 
							"         rc=$(\"#count\").val();\r\n" + 
							"      var sum = 0;\r\n" + 
							"      var average = 0;\r\n" + 
							"     \r\n" + 
							"\r\n" + 
							"      for(i=1;i<=rc;i++)\r\n" + 
							"      {\r\n" + 
							"        sum += parseInt($(\"#tsoet\"+i).val());\r\n" + 
							"        if(!Number.isNaN(sum)){\r\n" + 
							"          average = parseFloat(sum / rc);\r\n" + 
							"        }\r\n" + 
							"      }\r\n" + 
							"      if(average!=0){\r\n" + 
							"        $(avgV3).html(average.toFixed(2));\r\n" + 
							"        getTotal();\r\n" + 
							"      }\r\n" + 
							"    else{$(\"#avgV3\").text(\"\")}\r\n" + 
							"}\r\n" + 
							"function geteravg4() {\r\n" + 
							"\r\n" + 
							"      \r\n" + 
							"         rc=$(\"#count\").val();\r\n" + 
							"      var sum = 0;\r\n" + 
							"      var average = 0;\r\n" + 
							"     \r\n" + 
							"\r\n" + 
							"      for(i=1;i<=rc;i++)\r\n" + 
							"      {\r\n" + 
							"        sum += parseInt($(\"#quiz\"+i).val());\r\n" + 
							"        if(!Number.isNaN(sum)){\r\n" + 
							"         average = parseFloat(sum / rc);\r\n" + 
							"        }\r\n" + 
							"      }\r\n" + 
							"      if(average!=0){\r\n" + 
							"        $(avgV4).html(average.toFixed(2));\r\n" + 
							"        getTotal();\r\n" + 
							"       }\r\n" + 
							"    else{$(\"#avgV4\").text(\"\")}\r\n" + 
							"}\r\n" + 
							"function geteravg5() {\r\n" + 
							"\r\n" + 
							"      \r\n" + 
							"         rc=$(\"#count\").val();\r\n" + 
							"      var sum = 0;\r\n" + 
							"      var average = 0;\r\n" + 
							"     \r\n" + 
							"\r\n" + 
							"      for(i=1;i<=rc;i++)\r\n" + 
							"      {\r\n" + 
							"        sum += parseInt($(\"#comb_first_aid_and_eqpt\"+i).val());\r\n" + 
							"        if(!Number.isNaN(sum)){\r\n" + 
							"          average = parseFloat(sum / rc);\r\n" + 
							"        }\r\n" + 
							"      }\r\n" + 
							"      if(average!=0){\r\n" + 
							"        $(avgV5).html(average.toFixed(2));\r\n" + 
							"        getTotal();\r\n" + 
							"       }\r\n" + 
							"    else{$(\"#avgV5\").text(\"\")}\r\n" + 
							"}\r\n" + 
							"\r\n" + 
							"function geteravg6() {\r\n" + 
							"\r\n" + 
							"      \r\n" + 
							"         rc=$(\"#count\").val();\r\n" + 
							"      var sum = 0;\r\n" + 
							"      var average = 0;\r\n" + 
							"     \r\n" + 
							"\r\n" + 
							"      for(i=1;i<=rc;i++)\r\n" + 
							"      {\r\n" + 
							"        sum += parseInt($(\"#new_genr_wpn_and_eqpt\"+i).val());\r\n" + 
							"         if(!Number.isNaN(sum)){\r\n" + 
							"          average = parseFloat(sum / rc);\r\n" + 
							"        }\r\n" + 
							"      }\r\n" + 
							"      if(average!=0){\r\n" + 
							"        $(avgV6).html(average.toFixed(2));\r\n" + 
							"        getTotal();\r\n" + 
							"      }\r\n" + 
							"    else{$(\"#avgV6\").text(\"\")}\r\n" + 
							"}\r\n" + 
							"\r\n" + 
							"function geteravg7() {\r\n" + 
							"\r\n" + 
							"      \r\n" + 
							"         rc=$(\"#count\").val();\r\n" + 
							"      var sum = 0;\r\n" + 
							"      var average = 0;\r\n" + 
							"     \r\n" + 
							"\r\n" + 
							"      for(i=1;i<=rc;i++)\r\n" + 
							"      {\r\n" + 
							"        sum += parseInt($(\"#dem\"+i).val());\r\n" + 
							"        if(!Number.isNaN(sum)){\r\n" + 
							"          average = parseFloat(sum / rc);\r\n" + 
							"        }\r\n" + 
							"      }\r\n" + 
							"      if(average!=0){\r\n" + 
							"        $(avgV7).html(average.toFixed(2));\r\n" + 
							"        getTotal();\r\n" + 
							"       }\r\n" + 
							"    else{$(\"#avgV7\").text(\"\")}\r\n" + 
							"}\r\n" + 
							"\r\n" + 
							"function geteravg8() {\r\n" + 
							"\r\n" + 
							"      \r\n" + 
							"         rc=$(\"#count\").val();\r\n" + 
							"      var sum = 0;\r\n" + 
							"      var average = 0;\r\n" + 
							"     \r\n" + 
							"\r\n" + 
							"      for(i=1;i<=rc;i++)\r\n" + 
							"      {\r\n" + 
							"        sum += parseInt($(\"#rt_procedure\"+i).val());\r\n" + 
							"        if(!Number.isNaN(sum)){\r\n" + 
							"          average = parseFloat(sum / rc);\r\n" + 
							"        }\r\n" + 
							"      }\r\n" + 
							"      if(average!=0){\r\n" + 
							"        $(avgV8).html(average.toFixed(2));\r\n" + 
							"        getTotal();\r\n" + 
							"       }\r\n" + 
							"    else{$(\"#avgV8\").text(\"\")}\r\n" + 
							"}\r\n" + 
							"\r\n" + 
							"function geteravg9() {\r\n" + 
							"\r\n" + 
							"      \r\n" + 
							"         rc=$(\"#count\").val();\r\n" + 
							"      var sum = 0;\r\n" + 
							"      var average = 0;\r\n" + 
							"     \r\n" + 
							"\r\n" + 
							"      for(i=1;i<=rc;i++)\r\n" + 
							"      {\r\n" + 
							"        sum += parseInt($(\"#mor_shoot\"+i).val());\r\n" + 
							"         if(!Number.isNaN(sum)){\r\n" + 
							"          average = parseFloat(sum / rc);\r\n" + 
							"        }\r\n" + 
							"      }\r\n" + 
							"       if(average!=0){\r\n" + 
							"        $(avgV9).html(average.toFixed(2));\r\n" + 
							"        getTotal();\r\n" + 
							"       }\r\n" + 
							"    else{$(\"#avgV9\").text(\"\")}\r\n" + 
							"}\r\n" + 
							"\r\n" + 
							"function geteravg10() {\r\n" + 
							"\r\n" + 
							"      \r\n" + 
							"         rc=$(\"#count\").val();\r\n" + 
							"      var sum = 0;\r\n" + 
							"      var average = 0;\r\n" + 
							"     \r\n" + 
							"\r\n" + 
							"      for(i=1;i<=rc;i++)\r\n" + 
							"      {\r\n" + 
							"        sum += parseInt($(\"#swimming\"+i).val());\r\n" + 
							"        if(!Number.isNaN(sum)){\r\n" + 
							"          average = parseFloat(sum / rc);\r\n" + 
							"        }\r\n" + 
							"      }\r\n" + 
							"      if(average!=0){\r\n" + 
							"        $(avgV10).html(average.toFixed(2));\r\n" + 
							"        getTotal();\r\n" + 
							"      }\r\n" + 
							"    else{$(\"#avgV10\").text(\"\")}\r\n" + 
							"}\r\n" + 
							"\r\n" + 
							"\r\n" + 
							"function geteravg11() {\r\n" + 
							"\r\n" + 
							"      \r\n" + 
							"         rc=$(\"#count\").val();\r\n" + 
							"      var sum = 0;\r\n" + 
							"      var average = 0;\r\n" + 
							"     \r\n" + 
							"\r\n" + 
							"      for(i=1;i<=rc;i++)\r\n" + 
							"      {\r\n" + 
							"        sum += parseInt($(\"#navig\"+i).val());\r\n" + 
							"        if(!Number.isNaN(sum)){\r\n" + 
							"          average = parseFloat(sum / rc);\r\n" + 
							"        }\r\n" + 
							"      }\r\n" + 
							"      if(average!=0){\r\n" + 
							"        $(avgV11).html(average.toFixed(2));\r\n" + 
							"        getTotal();\r\n" + 
							"       }\r\n" + 
							"    else{$(\"#avgV11\").text(\"\")}\r\n" + 
							"}\r\n" + 
							"function geteravg12() {\r\n" + 
							"\r\n" + 
							"      \r\n" + 
							"         rc=$(\"#count\").val();\r\n" + 
							"      var sum = 0;\r\n" + 
							"      var average = 0;\r\n" + 
							"     \r\n" + 
							"\r\n" + 
							"      for(i=1;i<=rc;i++)\r\n" + 
							"      {\r\n" + 
							"        sum += parseInt($(\"#driving\"+i).val());\r\n" + 
							"        if(!Number.isNaN(sum)){\r\n" + 
							"          average = parseFloat(sum / rc);\r\n" + 
							"        }\r\n" + 
							"      }\r\n" + 
							"      if(average!=0){\r\n" + 
							"        $(avgV12).html(average.toFixed(2));\r\n" + 
							"        getTotal();\r\n" + 
							"       }\r\n" + 
							"    else{$(\"#avgV12\").text(\"\")}\r\n" + 
							"}\r\n" + 
							"\r\n" + 
							"function geteravg13() {\r\n" + 
							"\r\n" + 
							"      \r\n" + 
							"         rc=$(\"#count\").val();\r\n" + 
							"      var sum = 0;\r\n" + 
							"      var average = 0;\r\n" + 
							"     \r\n" + 
							"\r\n" + 
							"      for(i=1;i<=rc;i++)\r\n" + 
							"      {\r\n" + 
							"        sum += parseInt($(\"#any_spl_skills\"+i).val());\r\n" + 
							"        if(!Number.isNaN(sum)){\r\n" + 
							"          average = parseFloat(sum / rc);\r\n" + 
							"        }\r\n" + 
							"      }\r\n" + 
							"      if(average!=0){\r\n" + 
							"        $(avgV13).html(average.toFixed(2));\r\n" + 
							"        getTotal();\r\n" + 
							"       }\r\n" + 
							"    else{$(\"#avgV13\").text(\"\")}\r\n" + 
							"\r\n" + 
							"}\r\n" + 
							"\r\n" + 
							"\r\n" + 
							"\r\n" + 
							" function getTotal(){\r\n" + 
							"\r\n" + 
							"              /*var overwt_txt = $(\"#overwt\").text();avgV*/\r\n" + 
							"//alert(avgV13_txt);\r\n" + 
							"\r\n" + 
							"       // var Tsum_txt = $(Tsum).text();\r\n" + 
							"        // alert(\"wei   \"+weighingin_txt);\r\n" + 
							"\r\n" + 
							"   //alert(\"hi\");\r\n" + 
							"              var avgV_txt = parseFloat($(\"#avgV\").text());\r\n" + 
							"              var avgV1_txt = parseFloat($(\"#avgV1\").text());\r\n" + 
							"              var avgV2_txt = parseFloat($(\"#avgV2\").text());\r\n" + 
							"              var avgV3_txt = parseFloat($(\"#avgV3\").text());\r\n" + 
							"              var avgV4_txt = parseFloat($(\"#avgV4\").text());\r\n" + 
							"              var avgV5_txt = parseFloat($(\"#avgV5\").text());\r\n" + 
							"              var avgV6_txt = parseFloat($(\"#avgV6\").text());\r\n" + 
							"              var avgV7_txt = parseFloat($(\"#avgV7\").text());\r\n" + 
							"              var avgV8_txt = parseFloat($(\"#avgV8\").text());\r\n" + 
							"              var avgV9_txt = parseFloat($(\"#avgV9\").text());\r\n" + 
							"              var avgV10_txt = parseFloat($(\"#avgV10\").text());\r\n" + 
							"              var avgV11_txt = parseFloat($(\"#avgV11\").text());\r\n" + 
							"              var avgV12_txt = parseFloat($(\"#avgV12\").text());\r\n" + 
							"              var avgV13_txt = parseFloat($(\"#avgV13\").text());\r\n" + 
							"              var avgV14_txt = parseFloat($(\"#avgV14\").text());\r\n" + 
							"              var unit_txt_total = parseFloat($(\"#avg\").text());\r\n" + 
							"        \r\n" + 
							"        var total= parseFloat(avgV_txt + avgV1_txt + avgV2_txt + avgV3_txt +avgV4_txt + avgV5_txt + avgV6_txt + avgV7_txt + avgV8_txt + avgV9_txt + avgV10_txt + avgV11_txt+ avgV12_txt + avgV13_txt + avgV14_txt);\r\n" + 
							"\r\n" + 
							"       // alert(\"total\"+total);\r\n" + 
							"        $('#avg1').html(total);\r\n" + 
							"\r\n" + 
							"if(Number.isNaN(unit_txt_total)){unit_txt_total= 0}\r\n" + 
							"        var unit_total = parseFloat(unit_txt_total + total);\r\n" + 
							"        $('#UnitTotal').html(unit_total.toFixed(2));\r\n" + 
							"\r\n" + 
							"  }\r\n" + 
							"\r\n" + 
							"\r\n" + 
							"\r\n" + 
							"function parent_btn1(){\r\n" + 
							"  \r\n" + 
							"        var unit_name =document.getElementById('unit_name').value;\r\n" + 
							"        var sus_no =document.getElementById('sus_no').value;\r\n" + 
							"        var month =document.getElementById('month').value;\r\n" + 
							"        var sub_unit_tested =document.getElementById('sub_unit_tested').value;\r\n" + 
							"        var camp_layout =document.getElementById('camp_layout').value;\r\n" + 
							"        var briefing =document.getElementById('briefing').value;\r\n" + 
							"        var snap_sit =document.getElementById('snap_sit').value;\r\n" + 
							"        var op_role =document.getElementById('op_role').value;\r\n" + 
							"        var dbriefing =document.getElementById('dbriefing').value;\r\n" + 
							"        var tug_of_war =document.getElementById('tug_of_war').value;\r\n" + 
							"        var social_aspect =document.getElementById('social_aspect').value;\r\n" + 
							"        var adm_task =document.getElementById('adm_task').value;\r\n" + 
							"\r\n" + 
							"\r\n" + 
							"\r\n" + 
							" var b_1 = /^(?=.*[a-zA-Z ])(?=.*[0-9])[a-zA-Z0-9_.-]+$/;\r\n" + 
							"\r\n" + 
							"\r\n" + 
							"\r\n" + 
							"      if(unit_name == \"\"){\r\n" + 
							"         \r\n" + 
							"         alert(\"Unit Name must be filled out\");\r\n" + 
							"         document.getElementById('unit_name').focus();\r\n" + 
							"         return false;\r\n" + 
							"          \r\n" + 
							"        }\r\n" + 
							"         if(!unit_name.match(b_1)){\r\n" + 
							"        alert(\"only Alphanumerics are allowed    ==    \"+document.getElementById(\"unit_name\").value);\r\n" + 
							"        document.getElementById('unit_name').focus();\r\n" + 
							"        return false;\r\n" + 
							"        }\r\n" + 
							"\r\n" + 
							"        if (unit_name.length>25){\r\n" + 
							"        alert(\"The field cannot contain more than 25 characters    ==  \"+document.getElementById(\"unit_name\").value);\r\n" + 
							"        document.getElementById('unit_name').focus();\r\n" + 
							"        return false\r\n" + 
							"      }\r\n" + 
							"\r\n" + 
							"      if(sus_no == \"\"){\r\n" + 
							"         \r\n" + 
							"         alert(\"SUS No must be filled out\");\r\n" + 
							"         document.getElementById('sus_no').focus();\r\n" + 
							"         return false;\r\n" + 
							"          \r\n" + 
							"        }\r\n" + 
							"        if(!sus_no.match(b_1)){\r\n" + 
							"        alert(\"only Alphanumerics are allowed    ==    \"+document.getElementById(\"sus_no\").value);\r\n" + 
							"        document.getElementById('sus_no').focus();\r\n" + 
							"        return false;\r\n" + 
							"        }\r\n" + 
							"        \r\n" + 
							"        if (sus_no.length<0){\r\n" + 
							"        alert(\"The field cannot have 0 characters    ==  \"+document.getElementById(\"sus_no\").value);\r\n" + 
							"         document.getElementById('sus_no').focus();\r\n" + 
							"        return false\r\n" + 
							"      }\r\n" + 
							"      if (sus_no.length<8){\r\n" + 
							"        alert(\"The field cannot accept less than 8 characters    ==  \"+document.getElementById(\"sus_no\").value);\r\n" + 
							"         document.getElementById('sus_no').focus();\r\n" + 
							"        return false\r\n" + 
							"      }\r\n" + 
							"      if (sus_no.length>8){\r\n" + 
							"        alert(\"The field cannot accept more than 8 characters     ==  \"+document.getElementById(\"sus_no\").value);\r\n" + 
							"         document.getElementById('sus_no').focus();\r\n" + 
							"        return false\r\n" + 
							"      }\r\n" + 
							"      if(month == \"\"){\r\n" + 
							"         \r\n" + 
							"         alert(\"Month must be filled out\"+document.getElementById(\"month\").value);\r\n" + 
							"         document.getElementById('month').focus();\r\n" + 
							"         return false;\r\n" + 
							"          \r\n" + 
							"        }\r\n" + 
							"     if(sub_unit_tested == \"\"){\r\n" + 
							"         \r\n" + 
							"         alert(\"Sub Unit Tested must be filled out\");\r\n" + 
							"         document.getElementById('sub_unit_tested').focus();\r\n" + 
							"         return false;\r\n" + 
							"          \r\n" + 
							"        }\r\n" + 
							"        if(!sub_unit_tested.match(b_1)){\r\n" + 
							"        alert(\"only Alphanumerics are allowed   ==    \"+document.getElementById(\"sub_unit_tested\").value);\r\n" + 
							"        document.getElementById('sub_unit_tested').focus();\r\n" + 
							"        return false;\r\n" + 
							"        }\r\n" + 
							"        if (sub_unit_tested.length>50){\r\n" + 
							"        alert(\"The field cannot contain more than 50 characters    ==  \"+document.getElementById(\"sub_unit_tested\").value);\r\n" + 
							"        return false\r\n" + 
							"      }\r\n" + 
							"\r\n" + 
							"     if(camp_layout == \"\"){\r\n" + 
							"         \r\n" + 
							"         alert(\"Camp Layout must be filled out\");\r\n" + 
							"         document.getElementById('camp_layout').focus();\r\n" + 
							"         return false;\r\n" + 
							"          \r\n" + 
							"        }\r\n" + 
							"        if(camp_layout>120){\r\n" + 
							"         \r\n" + 
							"         alert(\"Camp Layout should be between 0 to 120  \"+document.getElementById(\"camp_layout\").value);\r\n" + 
							"         document.getElementById('camp_layout').focus();\r\n" + 
							"         return false;\r\n" + 
							"          \r\n" + 
							"        }\r\n" + 
							"      \r\n" + 
							"\r\n" + 
							"       if(briefing == \"\"){\r\n" + 
							"         \r\n" + 
							"         alert(\"Briefing must be filled out\");\r\n" + 
							"         document.getElementById('briefing').focus();\r\n" + 
							"         return false;\r\n" + 
							"          \r\n" + 
							"        }\r\n" + 
							"        if(briefing>65){\r\n" + 
							"         \r\n" + 
							"         alert(\"Briefing should be between 0 to 65  \"+document.getElementById(\"briefing\").value);\r\n" + 
							"         document.getElementById('briefing').focus();\r\n" + 
							"         return false;\r\n" + 
							"          \r\n" + 
							"        }\r\n" + 
							"      \r\n" + 
							"        if(snap_sit == \"\"){\r\n" + 
							"         \r\n" + 
							"         alert(\"Snap Sit must be filled out\");\r\n" + 
							"         document.getElementById('snap_sit').focus();\r\n" + 
							"         return false;\r\n" + 
							"          \r\n" + 
							"        }\r\n" + 
							"        if(snap_sit>35){\r\n" + 
							"         \r\n" + 
							"         alert(\"Snap Sit should be between 0 to 35 \"+document.getElementById(\"snap_sit\").value);\r\n" + 
							"         document.getElementById('snap_sit').focus();\r\n" + 
							"         return false;\r\n" + 
							"          \r\n" + 
							"        }\r\n" + 
							"       \r\n" + 
							"\r\n" + 
							"      if(op_role == \"\"){\r\n" + 
							"         \r\n" + 
							"         alert(\"Op Role must be filled out\");\r\n" + 
							"         document.getElementById('op_role').focus();\r\n" + 
							"         return false;\r\n" + 
							"          \r\n" + 
							"        }\r\n" + 
							"        if(op_role>10){\r\n" + 
							"         \r\n" + 
							"         alert(\"Op Role should be between 0 to 10  \"+document.getElementById(\"op_role\").value);\r\n" + 
							"         document.getElementById('op_role').focus();\r\n" + 
							"         return false;\r\n" + 
							"          \r\n" + 
							"        }\r\n" + 
							"       \r\n" + 
							"      if(dbriefing == \"\"){\r\n" + 
							"         \r\n" + 
							"         alert(\"Debriefing must be filled out\");\r\n" + 
							"         document.getElementById('dbriefing').focus();\r\n" + 
							"         return false;\r\n" + 
							"          \r\n" + 
							"        }\r\n" + 
							"        if(dbriefing>100){\r\n" + 
							"         \r\n" + 
							"         alert(\"Debriefing should be between 0 to 100  \"+document.getElementById(\"dbriefing\").value);\r\n" + 
							"         document.getElementById('dbriefing').focus();\r\n" + 
							"         return false;\r\n" + 
							"          \r\n" + 
							"        }\r\n" + 
							"       \r\n" + 
							"       if(tug_of_war == \"\"){\r\n" + 
							"         \r\n" + 
							"         alert(\"Tug Of War must be filled out\");\r\n" + 
							"         document.getElementById('tug_of_war').focus();\r\n" + 
							"         return false;\r\n" + 
							"          \r\n" + 
							"        }\r\n" + 
							"        if(tug_of_war>30){\r\n" + 
							"         \r\n" + 
							"         alert(\"Tug Of War should be between 0 to 30  \"+document.getElementById(\"tug_of_war\").value);\r\n" + 
							"         document.getElementById('tug_of_war').focus();\r\n" + 
							"         return false;\r\n" + 
							"          \r\n" + 
							"        }\r\n" + 
							"       \r\n" + 
							"        if(social_aspect == \"\"){\r\n" + 
							"         \r\n" + 
							"         alert(\"Social Aspects  must be filled out\");\r\n" + 
							"         document.getElementById('social_aspect').focus();\r\n" + 
							"         return false;\r\n" + 
							"          \r\n" + 
							"        }\r\n" + 
							"        if(social_aspect>30){\r\n" + 
							"         \r\n" + 
							"         alert(\"Social Aspects should be between 0 to 30  \"+document.getElementById(\"social_aspect\").value);\r\n" + 
							"         document.getElementById('social_aspect').focus();\r\n" + 
							"         return false;\r\n" + 
							"          \r\n" + 
							"        }\r\n" + 
							"        \r\n" + 
							"        if(adm_task == \"\"){\r\n" + 
							"         \r\n" + 
							"         alert(\"Adm Task  must be filled out\");\r\n" + 
							"         document.getElementById('adm_task').focus();\r\n" + 
							"         return false;\r\n" + 
							"          \r\n" + 
							"        }\r\n" + 
							"        if(adm_task>30){\r\n" + 
							"         \r\n" + 
							"         alert(\"Adm Task should be between 0 to 30  \"+document.getElementById(\"adm_task\").value);\r\n" + 
							"         document.getElementById('adm_task').focus();\r\n" + 
							"         return false;\r\n" + 
							"          \r\n" + 
							"        }\r\n" + 
							"       \r\n" + 
							"\r\n" + 
							"      if(child(x)){\r\n" + 
							"        return true;\r\n" + 
							"      }\r\n" + 
							"      else{\r\n" + 
							"        return false;\r\n" + 
							"      }\r\n" + 
							"\r\n" + 
							"  }   \r\n" + 
							"\r\n" + 
							"function child() {\r\n" + 
							"\r\n" + 
							"    \r\n" + 
							"        rc=$(\"#count\").val();\r\n" + 
							"     \r\n" + 
							"\r\n" + 
							"      var b_2 = /^(?=.*[a-zA-Z ])(?=.*[0-9])[a-zA-Z0-9_.-]+$/;\r\n" + 
							"      //var a_2 = /^[a-zA-Z ]+$/; \r\n" + 
							"      var a_2=/^([\\s\\.]?[a-z\\s\\.A-Z]+)+$/;\r\n" + 
							"       //var a_2_a=/^[0-9]+$/; \r\n" + 
							"      \r\n" + 
							"    for(x=1;x<=rc;x++)\r\n" + 
							"    {\r\n" + 
							"         if($(\"#pers_type\"+x).val() == \"0\"){\r\n" + 
							"         \r\n" + 
							"         alert(\"Pers Type must be filled out\");\r\n" + 
							"         document.getElementById('pers_type'+x).focus();\r\n" + 
							"         return false;\r\n" + 
							"          \r\n" + 
							"        }\r\n" + 
							"     if($(\"#rank\"+x).val() == \"0\"){\r\n" + 
							"        \r\n" + 
							"         alert(\"Rank must be filled out\");\r\n" + 
							"         document.getElementById('rank'+x).focus();\r\n" + 
							"         return false;        \r\n" + 
							"       }\r\n" + 
							"\r\n" + 
							"      if($(\"#number\"+x).val() == \"\"){\r\n" + 
							"         \r\n" + 
							"         alert(\"Number must be filled out\"+document.getElementById(\"number\"+x).value);\r\n" + 
							"         document.getElementById('number'+x).focus();\r\n" + 
							"         return false;\r\n" + 
							"          \r\n" + 
							"        }\r\n" + 
							"\r\n" + 
							"      if(!($(\"#number\"+x).val()).match(b_2)){\r\n" + 
							"        alert(\"only Alphanumerics are allowed   ==    \"+document.getElementById(\"number\"+x).value);\r\n" + 
							"        document.getElementById('number'+x).focus();\r\n" + 
							"        return false;\r\n" + 
							"      }\r\n" + 
							"      if(($(\"#number\"+x).val()).length>10){\r\n" + 
							"        alert(\"The field cannot contain more than 10 characters    ==  \"+document.getElementById(\"number\"+x).value);\r\n" + 
							"        document.getElementById('number'+x).focus();\r\n" + 
							"        return false\r\n" + 
							"      }\r\n" + 
							"\r\n" + 
							"      if($(\"#name\"+x).val() == \"\"){\r\n" + 
							"         \r\n" + 
							"         alert(\"Name must be filled out\");\r\n" + 
							"         document.getElementById('name'+x).focus();\r\n" + 
							"         return false;\r\n" + 
							"          \r\n" + 
							"        }\r\n" + 
							"\r\n" + 
							"      if(!($(\"#name\"+x).val()).match(a_2)){\r\n" + 
							"        alert(\"only Characters are allowed    ==    \"+document.getElementById(\"name\"+x).value);\r\n" + 
							"        document.getElementById('name'+x).focus();\r\n" + 
							"        return false;\r\n" + 
							"      }\r\n" + 
							"      \r\n" + 
							"      if (($(\"#name\"+x).val()).length>25){\r\n" + 
							"        alert(\"The field cannot contain more than 25 characters    ==  \"+document.getElementById(\"name\"+x).value);\r\n" + 
							"        document.getElementById('name'+x).focus();\r\n" + 
							"        return false\r\n" + 
							"      }\r\n" + 
							"       if($(\"#medical_category\"+x).val() == \"0\"){\r\n" + 
							"         \r\n" + 
							"         alert(\"Medical Category must be filled out\");\r\n" + 
							"         document.getElementById('medical_category'+x).focus();\r\n" + 
							"         return false;\r\n" + 
							"          \r\n" + 
							"      }\r\n" + 
							"     if($(\"#medical_category\"+x).val() == 'LMC')\r\n" + 
							"        {\r\n" + 
							"  if($(\"#medical_category_remarks\"+x).val() == \"\"){\r\n" + 
							"         \r\n" + 
							"         alert(\"Remarks  must be filled out\");\r\n" + 
							"         document.getElementById('medical_category_remarks'+x).focus();\r\n" + 
							"         return false;\r\n" + 
							"      }\r\n" + 
							"    }\r\n" + 
							"         if($(\"#endurance_run\"+x).val() == \"\"){\r\n" + 
							"         \r\n" + 
							"         alert(\"Endurance Run must be filled out\");\r\n" + 
							"         document.getElementById('endurance_run'+x).focus();\r\n" + 
							"         return false;\r\n" + 
							"          \r\n" + 
							"        }\r\n" + 
							"        if($(\"#endurance_run\"+x).val()>120){\r\n" + 
							"         \r\n" + 
							"         alert(\"Endurance Run should be between 0 to 120  \"+document.getElementById(\"endurance_run\"+x).value);\r\n" + 
							"         document.getElementById('endurance_run'+x).focus();\r\n" + 
							"         return false;\r\n" + 
							"          \r\n" + 
							"        }\r\n" + 
							"       \r\n" + 
							"      if($(\"#day_fg\"+x).val() == \"\"){\r\n" + 
							"         \r\n" + 
							"         alert(\"Day Fg must be filled out\");\r\n" + 
							"         document.getElementById('day_fg'+x).focus();\r\n" + 
							"         return false;\r\n" + 
							"          \r\n" + 
							"        }\r\n" + 
							"        if($(\"#day_fg\"+x).val()>65){\r\n" + 
							"         \r\n" + 
							"         alert(\"Day Fg should be between 0 to 65  \"+document.getElementById(\"day_fg\"+x).value);\r\n" + 
							"         document.getElementById('day_fg'+x).focus();\r\n" + 
							"         return false;\r\n" + 
							"          \r\n" + 
							"        }\r\n" + 
							"        \r\n" + 
							"        \r\n" + 
							"        if($(\"#ni_fg\"+x).val() == \"\"){\r\n" + 
							"         \r\n" + 
							"         alert(\"Ni Fg must be filled out\");\r\n" + 
							"         document.getElementById('ni_fg'+x).focus();\r\n" + 
							"         return false;\r\n" + 
							"          \r\n" + 
							"        }\r\n" + 
							"        if($(\"#ni_fg\"+x).val()>35){\r\n" + 
							"         \r\n" + 
							"         alert(\"Ni Fg should be between 0 to 35 \"+document.getElementById(\"ni_fg\"+x).value);\r\n" + 
							"         document.getElementById('ni_fg'+x).focus();\r\n" + 
							"         return false;\r\n" + 
							"          \r\n" + 
							"        }\r\n" + 
							"         \r\n" + 
							"        \r\n" + 
							"        if($(\"#tsoet\"+x).val() == \"\"){\r\n" + 
							"         \r\n" + 
							"         alert(\"TsOET must be filled out\");\r\n" + 
							"         document.getElementById('tsoet'+x).focus();\r\n" + 
							"         return false;\r\n" + 
							"          \r\n" + 
							"        }\r\n" + 
							"        if($(\"#tsoet\"+x).val()>10){\r\n" + 
							"         \r\n" + 
							"         alert(\"TsOET should be between 0 to 10  \"+document.getElementById(\"tsoet\"+x).value);\r\n" + 
							"         document.getElementById('tsoet'+x).focus();\r\n" + 
							"         return false;\r\n" + 
							"          \r\n" + 
							"        }\r\n" + 
							"        \r\n" + 
							"        \r\n" + 
							"        if($(\"#quiz\"+x).val() == \"\"){\r\n" + 
							"         \r\n" + 
							"         alert(\"Quiz must be filled out\");\r\n" + 
							"         document.getElementById('quiz'+x).focus();\r\n" + 
							"         return false;\r\n" + 
							"          \r\n" + 
							"        }\r\n" + 
							"        if($(\"#quiz\"+x).val()>100){\r\n" + 
							"         \r\n" + 
							"         alert(\"Quiz should be between 0 to 100  \"+document.getElementById(\"quiz\"+x).value);\r\n" + 
							"         document.getElementById('quiz'+x).focus();\r\n" + 
							"         return false;\r\n" + 
							"          \r\n" + 
							"        }\r\n" + 
							"        \r\n" + 
							"\r\n" + 
							"       if($(\"#comb_first_aid_and_eqpt\"+x).val() == \"\"){\r\n" + 
							"         \r\n" + 
							"         alert(\"Comb First Aid and Cas Evac must be filled out\");\r\n" + 
							"         document.getElementById('comb_first_aid_and_eqpt'+x).focus();\r\n" + 
							"         return false;\r\n" + 
							"          \r\n" + 
							"        }\r\n" + 
							"        if($(\"#comb_first_aid_and_eqpt\"+x).val()>30){\r\n" + 
							"         \r\n" + 
							"         alert(\"Comb First Aid and Cas Evac should be between 0 to 30  \"+document.getElementById(\"comb_first_aid_and_eqpt\"+x).value);\r\n" + 
							"         document.getElementById('comb_first_aid_and_eqpt'+x).focus();\r\n" + 
							"         return false;\r\n" + 
							"          \r\n" + 
							"        }\r\n" + 
							"        \r\n" + 
							"       \r\n" + 
							"        if($(\"#new_genr_wpn_and_eqpt\"+x).val() == \"\"){\r\n" + 
							"         \r\n" + 
							"         alert(\"New Genr Wpn And Eqpt must be filled out\");\r\n" + 
							"         document.getElementById('new_genr_wpn_and_eqpt'+x).focus();\r\n" + 
							"         return false;\r\n" + 
							"          \r\n" + 
							"        }\r\n" + 
							"        if($(\"#new_genr_wpn_and_eqpt\"+x).val()>30){\r\n" + 
							"         \r\n" + 
							"         alert(\"New Genr Wpn And Eqpt should be between 0 to 30  \"+document.getElementById(\"new_genr_wpn_and_eqpt\"+x).value);\r\n" + 
							"         document.getElementById('new_genr_wpn_and_eqpt'+x).focus();\r\n" + 
							"         return false;\r\n" + 
							"          \r\n" + 
							"        }\r\n" + 
							"        \r\n" + 
							"       \r\n" + 
							"        if($(\"#dem\"+x).val() == \"\"){\r\n" + 
							"         \r\n" + 
							"         alert(\"Dem must be filled out\");\r\n" + 
							"         document.getElementById('dem'+x).focus();\r\n" + 
							"         return false;\r\n" + 
							"          \r\n" + 
							"        }\r\n" + 
							"        if($(\"#dem\"+x).val()>30){\r\n" + 
							"         \r\n" + 
							"         alert(\"Dem should be between 0 to 30  \"+document.getElementById(\"dem\"+x).value);\r\n" + 
							"         document.getElementById('dem'+x).focus();\r\n" + 
							"         return false;\r\n" + 
							"          \r\n" + 
							"        }\r\n" + 
							"        \r\n" + 
							"        \r\n" + 
							"        if($(\"#rt_procedure\"+x).val() == \"\"){\r\n" + 
							"         \r\n" + 
							"         alert(\"Rt Procedure must be filled out\");\r\n" + 
							"         document.getElementById('rt_procedure'+x).focus();\r\n" + 
							"         return false;\r\n" + 
							"          \r\n" + 
							"        }\r\n" + 
							"        if($(\"#rt_procedure\"+x).val()>30){\r\n" + 
							"         \r\n" + 
							"         alert(\"Rt Procedure should be between 0 to 30  \"+document.getElementById(\"rt_procedure\"+x).value);\r\n" + 
							"         document.getElementById('rt_procedure'+x).focus();\r\n" + 
							"         return false;\r\n" + 
							"          \r\n" + 
							"        }\r\n" + 
							"        \r\n" + 
							"        \r\n" + 
							"        if($(\"#mor_shoot\"+x).val() == \"\"){\r\n" + 
							"         \r\n" + 
							"         alert(\"Mor Shoot must be filled out\");\r\n" + 
							"         document.getElementById('mor_shoot'+x).focus();\r\n" + 
							"         return false;\r\n" + 
							"          \r\n" + 
							"        }\r\n" + 
							"        if($(\"#mor_shoot\"+x).val()>30){\r\n" + 
							"         \r\n" + 
							"         alert(\"Mor Shoot should be between 0 to 30  \"+document.getElementById(\"mor_shoot\"+x).value);\r\n" + 
							"         document.getElementById('mor_shoot'+x).focus();\r\n" + 
							"         return false;\r\n" + 
							"          \r\n" + 
							"        }\r\n" + 
							"        \r\n" + 
							"        \r\n" + 
							"        if($(\"#swimming\"+x).val() == \"\"){\r\n" + 
							"         \r\n" + 
							"         alert(\"Swimming must be filled out\");\r\n" + 
							"         document.getElementById('swimming'+x).focus();\r\n" + 
							"         return false;\r\n" + 
							"          \r\n" + 
							"        }\r\n" + 
							"        if($(\"#swimming\"+x).val()>30){\r\n" + 
							"         \r\n" + 
							"         alert(\"Swimming should be between 0 to 30  \"+document.getElementById(\"swimming\"+x).value);\r\n" + 
							"         document.getElementById('swimming'+x).focus();\r\n" + 
							"         return false;\r\n" + 
							"          \r\n" + 
							"        }\r\n" + 
							"        \r\n" + 
							"        \r\n" + 
							"\r\n" + 
							"       if($(\"#navig\"+x).val() == \"\"){\r\n" + 
							"         \r\n" + 
							"         alert(\"Navig must be filled out\");\r\n" + 
							"         document.getElementById('navig'+x).focus();\r\n" + 
							"         return false;\r\n" + 
							"          \r\n" + 
							"        }\r\n" + 
							"        if($(\"#navig\"+x).val()>30){\r\n" + 
							"         \r\n" + 
							"         alert(\"Navig should be between 0 to 30  \"+document.getElementById(\"navig\"+x).value);\r\n" + 
							"         document.getElementById('navig'+x).focus();\r\n" + 
							"         return false;\r\n" + 
							"          \r\n" + 
							"        }\r\n" + 
							"          \r\n" + 
							"        \r\n" + 
							"       if($(\"#driving\"+x).val() == \"\"){\r\n" + 
							"         \r\n" + 
							"         alert(\"Driving must be filled out\");\r\n" + 
							"         document.getElementById('driving'+x).focus();\r\n" + 
							"         return false;\r\n" + 
							"          \r\n" + 
							"        }\r\n" + 
							"        if($(\"#driving\"+x).val()>30){\r\n" + 
							"         \r\n" + 
							"         alert(\"Driving should be between 0 to 30  \"+document.getElementById(\"driving\"+x).value);\r\n" + 
							"         document.getElementById('driving'+x).focus();\r\n" + 
							"         return false;\r\n" + 
							"          \r\n" + 
							"        }\r\n" + 
							"        \r\n" + 
							"        \r\n" + 
							"          if($(\"#any_spl_skills\"+x).val()== \"\"){\r\n" + 
							"         \r\n" + 
							"         alert(\"Any Spl Skills must be filled out\");\r\n" + 
							"         document.getElementById('any_spl_skills'+x).focus();\r\n" + 
							"         return false;\r\n" + 
							"          \r\n" + 
							"        }\r\n" + 
							"        if($(\"#any_spl_skills\"+x).val()>30){\r\n" + 
							"         \r\n" + 
							"         alert(\"Any Spl Skills should be between 0 to 30  \"+document.getElementById(\"any_spl_skills\"+x).value);\r\n" + 
							"         document.getElementById('any_spl_skills'+x).focus();\r\n" + 
							"         return false;\r\n" + 
							"          \r\n" + 
							"        }\r\n" + 
							"        \r\n" + 
							"        \r\n" + 
							"         if($(\"#autenticate_wt\"+x).val()== \"\"){\r\n" + 
							"         \r\n" + 
							"         alert(\"Authenticate  Weight must be filled out\");\r\n" + 
							"         document.getElementById('autenticate_wt'+x).focus();\r\n" + 
							"         return false;\r\n" + 
							"          \r\n" + 
							"        }\r\n" + 
							"        if($(\"#autenticate_wt\"+x).val()<0){\r\n" + 
							"         \r\n" + 
							"         alert(\"Authenticate  Weight should be greater than 0 \"+\" \"+document.getElementById(\"autenticate_wt\"+x).value);\r\n" + 
							"         document.getElementById('autenticate_wt'+x).focus();\r\n" + 
							"         return false;\r\n" + 
							"          \r\n" + 
							"        }\r\n" + 
							"         \r\n" + 
							"         if($(\"#actual_wt\"+x).val()== \"\"){\r\n" + 
							"         \r\n" + 
							"         alert(\"Actual  Weight must be filled out\");\r\n" + 
							"         document.getElementById('actual_wt'+x).focus();\r\n" + 
							"         return false;\r\n" + 
							"          \r\n" + 
							"        }\r\n" + 
							"        if($(\"#actual_wt\"+x).val()<0){\r\n" + 
							"         \r\n" + 
							"         alert(\"Actual  Weight should be greater than 0 \"+\" \"+document.getElementById(\"actual_wt\"+x).value);\r\n" + 
							"         document.getElementById('actual_wt'+x).focus();\r\n" + 
							"         return false;\r\n" + 
							"          \r\n" + 
							"        }\r\n" + 
							"        \r\n" + 
							"      }\r\n" + 
							"\r\n" + 
							"return true;\r\n" + 
							"}\r\n" + 
							"\r\n" + 
							"\r\n" + 
							"\r\n" + 
							"\r\n" + 
							"\r\n" + 
							" ///////////////////////////////////////////////////////////////////////////////////////export code start\r\n" + 
							"\r\n" + 
							"\r\n" + 
							"\r\n" + 
							" function AddData_second() {\r\n" + 
							"  $(\"table#table_show_2  tbody\").empty();\r\n" + 
							"\r\n" + 
							"\r\n" + 
							"\r\n" + 
							"\r\n" + 
							"  rc=$(\"#count\").val();\r\n" + 
							"\r\n" + 
							"\r\n" + 
							"  var assets_type;\r\n" + 
							"  var make_name;\r\n" + 
							"  var model_name;\r\n" + 
							"  var warrantycheck;\r\n" + 
							"  var warranty_dt;\r\n" + 
							"  var type_of_hw;\r\n" + 
							"  var type;\r\n" + 
							"  var year_of_proc;\r\n" + 
							"  var year_of_manufacturing;\r\n" + 
							"  var remarks;\r\n" + 
							"  var model_no;\r\n" + 
							"  var machine_no;\r\n" + 
							"  var is_networked;\r\n" + 
							"  var ip_address;\r\n" + 
							"  var ups_capacity;\r\n" + 
							"  var monochrome_color_radio;\r\n" + 
							"  var paper_size;\r\n" + 
							"  var connectivity_type;\r\n" + 
							"  var print;\r\n" + 
							"  var scan;\r\n" + 
							"  var photography;\r\n" + 
							"  var colour;\r\n" + 
							"  var capacity;\r\n" + 
							"  var hw_interface;\r\n" + 
							"  var resolution;\r\n" + 
							"  var v_display_size;\r\n" + 
							"  var v_display_connector;\r\n" + 
							"  var no_of_ports;\r\n" + 
							"	var network_features;\r\n" + 
							"	var ethernet_port;\r\n" + 
							"	var management_layer;\r\n" + 
							"	var display_type;\r\n" + 
							"	var display_size;\r\n" + 
							"	var display_connector;\r\n" + 
							"	var s_state;\r\n" + 
							"	var unserviceable_state;\r\n" + 
							"	var unsv_from_dt; \r\n" + 
							"	var b_cost;\r\n" + 
							
							"	var proc_date;\r\n" + 
							"	var b_head;\r\n" + 
							"	var b_code;\r\n" + 
							"\r\n" + 
							"  for (var colIndex1 = 1; colIndex1 <=parseInt(rc); colIndex1++) {\r\n" + 
							"   \r\n" + 
							"\r\n" + 
							"  var rows = \"\";\r\n" + 
							" \r\n" + 
							"   assets_type =  document.getElementById(\"assets_type\"+colIndex1).value;\r\n" + 
							"   make_name=  document.getElementById(\"make_name\"+colIndex1).value;\r\n" + 
							"   model_name=  document.getElementById(\"model_name\"+colIndex1).value;\r\n" + 
							"if(document.getElementById('warrantycheckyes'+colIndex1).checked){\r\n" + 
							"			warrantycheck=$('#warrantycheckyes'+colIndex1).val();\r\n" + 
							"			}\r\n" + 
							"			else if(document.getElementById('warrantycheckno'+colIndex1).checked){\r\n" + 
							"			warrantycheck=$('#warrantycheckno'+colIndex1).val();\r\n" + 
							"			} "+
							"   warranty_dt=  document.getElementById(\"warranty_dt\"+colIndex1).value;\r\n" + 
							"   type_of_hw=  document.getElementById(\"type_of_hw\"+colIndex1).value;\r\n" + 
							"   type=  document.getElementById(\"type\"+colIndex1).value;\r\n" + 
							"   year_of_proc=  document.getElementById(\"year_of_proc\"+colIndex1).value;\r\n" + 
							"   year_of_manufacturing=  document.getElementById(\"year_of_manufacturing\"+colIndex1).value;\r\n" + 
							"   remarks=  document.getElementById(\"remarks\"+colIndex1).value;\r\n" + 
							"   model_no=  document.getElementById(\"model_no\"+colIndex1).value;\r\n" + 
							"   machine_no=  document.getElementById(\"machine_no\"+colIndex1).value;\r\n" + 
							" if(document.getElementById('is_networkedyes'+colIndex1).checked){\r\n" + 
							"			is_networked=$('#is_networkedyes'+colIndex1).val();\r\n" + 
							"			}\r\n" + 
							"			else if(document.getElementById('is_networkedno'+colIndex1).checked){\r\n" + 
							"			is_networked=$('#is_networkedno'+colIndex1).val();\r\n" + 
							"			}"+
							"   ip_address=  document.getElementById(\"ip_address\"+colIndex1).value;\r\n" + 
							"   ups_capacity=  document.getElementById(\"ups_capacity\"+colIndex1).value;\r\n" + 
							"if(document.getElementById('monochrome'+colIndex1).checked){\r\n" + 
							"			monochrome_color_radio=$('#monochrome'+colIndex1).val();\r\n" + 
							"			}\r\n" + 
							"			else if(document.getElementById('monochrome_color'+colIndex1).checked){\r\n" + 
							"			monochrome_color_radio=$('#monochrome_color'+colIndex1).val();\r\n" + 
							"			}"+
							"   paper_size=  document.getElementById(\"paper_size\"+colIndex1).value;\r\n" + 
							"   connectivity_type=  document.getElementById(\"connectivity_type\"+colIndex1).value;\r\n" + 
							
							"if(document.getElementById('printyes'+colIndex1).checked){\r\n" + 
							"			print=$('#printyes'+colIndex1).val();\r\n" + 
							"			}\r\n" + 
							"			else if(document.getElementById('printno'+colIndex1).checked){\r\n" + 
							"			print=$('#printno'+colIndex1).val();\r\n" + 
							"			}\r\n" + 
							"			\r\n" + 
							"			if(document.getElementById('scanyes'+colIndex1).checked){\r\n" + 
							"			scan=$('#scanyes'+colIndex1).val();\r\n" + 
							"			}\r\n" + 
							"			else if(document.getElementById('scanno'+colIndex1).checked){\r\n" + 
							"			scan=$('#scanno'+colIndex1).val();\r\n" + 
							"			}\r\n" + 
							"			\r\n" + 
							"			if(document.getElementById('photographyyes'+colIndex1).checked){\r\n" + 
							"			photography=$('#photographyyes'+colIndex1).val();\r\n" + 
							"			}\r\n" + 
							"			else if(document.getElementById('photographyno'+colIndex1).checked){\r\n" + 
							"			photography=$('#photographyno'+colIndex1).val();\r\n" + 
							"			}\r\n" + 
							"			\r\n" + 
							"			if(document.getElementById('colouryes'+colIndex1).checked){\r\n" + 
							"			colour=$('#colouryes'+colIndex1).val();\r\n" + 
							"			}\r\n" + 
							"			else if(document.getElementById('colourno'+colIndex1).checked){\r\n" + 
							"			colour=$('#colourno'+colIndex1).val();\r\n" + 
							"			}"+
							
							"   capacity=  document.getElementById(\"capacity\"+colIndex1).value;\r\n" + 
							"   hw_interface=  document.getElementById(\"hw_interface\"+colIndex1).value;\r\n" + 
							"   resolution=  document.getElementById(\"resolution\"+colIndex1).value;\r\n" + 
							"   v_display_size=  document.getElementById(\"v_display_size\"+colIndex1).value;\r\n" + 
							"   v_display_connector=  document.getElementById(\"v_display_connector\"+colIndex1).value;\r\n" + 
							"	no_of_ports=  document.getElementById(\"no_of_ports\"+colIndex1).value;\r\n" + 
							"	network_features=  document.getElementById(\"network_features\"+colIndex1).value;\r\n" + 
							"	ethernet_port=  document.getElementById(\"ethernet_port\"+colIndex1).value;\r\n" + 
							"	management_layer=  document.getElementById(\"management_layer\"+colIndex1).value;\r\n" + 
							"	display_type=  document.getElementById(\"display_type\"+colIndex1).value;\r\n" + 
							"	display_size=  document.getElementById(\"display_size\"+colIndex1).value;\r\n" + 
							"	display_connector=  document.getElementById(\"display_connector\"+colIndex1).value;\r\n" + 
							"	s_state=  document.getElementById(\"s_state\"+colIndex1).value;\r\n" + 
							"	unserviceable_state=  document.getElementById(\"unserviceable_state\"+colIndex1).value;\r\n" + 
							"	unsv_from_dt=  document.getElementById(\"unsv_from_dt\"+colIndex1).value;\r\n" + 
							
							"	b_cost=  document.getElementById(\"b_cost\"+colIndex1).value;\r\n" + 
							"	proc_date=  document.getElementById(\"proc_date\"+colIndex1).value;  \r\n" + 
							"	b_head=  document.getElementById(\"b_head\"+colIndex1).value;\r\n" + 
							"	b_code=  document.getElementById(\"b_code\"+colIndex1).value;\r\n" + 
							"\r\n" + 
							" rows = \"<tr><td>\" + assets_type + \"</td><td>\" + make_name + \"</td><td>\" + model_name + \"</td><td>\" + warrantycheck + \"</td><td>\" + warranty_dt + \"</td><td>\" \r\n" + 
							" + type_of_hw + \"</td><td>\" + type + \"</td><td>\" + year_of_proc + \"</td><td>\" + year_of_manufacturing + \"</td><td>\" + remarks + \"</td><td>\" + model_no + \r\n" + 
							" \"</td><td>\" + machine_no + \"</td><td>\" + is_networked + \"</td><td>\" + ip_address + \"</td><td>\" + ups_capacity + \"</td><td>\" + monochrome_color_radio + \r\n" + 
							"\"</td><td>\" + paper_size + \"</td><td>\" + connectivity_type + \"</td><td>\" + print + \"</td><td>\" + scan + \"</td><td>\" + photography + \"</td><td>\" + colour + \r\n" + 
							" \"</td><td>\" + capacity + \"</td><td>\" + hw_interface + \"</td><td>\" + resolution + \"</td><td>\" + v_display_size + \"</td><td>\" + v_display_connector +\r\n" + 
							" \"</td><td>\" + no_of_ports + \"</td><td>\" + network_features + \"</td><td>\" + ethernet_port + \"</td><td>\" + management_layer + \"</td><td>\" + display_type + \"</td><td>\" + display_size + \r\n" + 
							" \"</td><td>\" + display_connector + \"</td><td>\" + s_state + \"</td><td>\" + unserviceable_state + \"</td><td>\" + unsv_from_dt + \"</td><td>\" + b_cost + \"</td><td>\" + proc_date + \"</td><td>\" + b_head + \"</td><td>\" + b_code + \"</td></tr> \";\r\n" + 
							"  $(rows).appendTo(\"#table_show_2 tbody\");\r\n" + 
							" \r\n" + 
							"  \r\n" + 
							"\r\n" + 
							"    \r\n" + 
							"}\r\n" + 
							"\r\n" + 
							"btnExport();\r\n" + 
							"window.location.reload();\r\n" + 
							"}\r\n" + 
							"\r\n" + 
							"\r\n" + 
							"\r\n" + 
							"var btnExport = (function() {\r\n"
							+ "var currentdate = new Date(); \r\n" + 
							"var datetime = \"Peripheral: \" + currentdate.getDate() + \"/\"\r\n" + 
							"                + (currentdate.getMonth()+1)  + \"/\" \r\n" + 
							"                + currentdate.getFullYear() + \" Time \"  \r\n" + 
							"                + currentdate.getHours() + \":\"  \r\n" + 
							"                + currentdate.getMinutes() + \":\" \r\n" + 
							"                + currentdate.getSeconds();\r\n" + 
							"var blobURL = tablesToExcel(['table_show_2'],['UnitDtl'],datetime+'.xls','Excel');\r\n" + 
							" $(this).attr('href',blobURL);" + 
							
							"});\r\n" + 
							"\r\n" + 
							"\r\n" + 
							"\r\n" + 
							"var tablesToExcel = (function() {\r\n" + 
							"var uri = 'data:application/vnd.ms-excel;base64,'\r\n" + 
							", tmplWorkbookXML = '<?xml version=\"1.0\"?><?mso-application progid=\"Excel.Sheet\"?><Workbook xmlns=\"urn:schemas-microsoft-com:office:spreadsheet\" xmlns:ss=\"urn:schemas-microsoft-com:office:spreadsheet\">'\r\n" + 
							"  + '<DocumentProperties xmlns=\"urn:schemas-microsoft-com:office:office\"><Author>Axel Richter</Author><Created>{created}</Created></DocumentProperties>'\r\n" + 
							"  + '<Styles>'\r\n" + 
							"  + '<Style ss:ID=\"Currency\"><NumberFormat ss:Format=\"Currency\"></NumberFormat></Style>'\r\n" + 
							"  + '<Style ss:ID=\"Date\"><NumberFormat ss:Format=\"Medium Date\"></NumberFormat></Style>'\r\n" + 
							"  + '</Styles>' \r\n" + 
							"  + '{worksheets}</Workbook>'\r\n" + 
							", tmplWorksheetXML = '<Worksheet ss:Name=\"{nameWS}\"><Table>{rows}</Table></Worksheet>'\r\n" + 
							", tmplCellXML = '<Cell{attributeStyleID}{attributeFormula}><Data ss:Type=\"{nameType}\">{data}</Data></Cell>'\r\n" + 
							", base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) }\r\n" + 
							", format = function(s, c) { return s.replace(/{(\\w+)}/g, function(m, p) { return c[p]; }) }\r\n" + 
							"return function(tables, wsnames, wbname, appname) {\r\n" + 
							"  var ctx = \"\";\r\n" + 
							"  var workbookXML = \"\";\r\n" + 
							"  var worksheetsXML = \"\";\r\n" + 
							"  var rowsXML = \"\";\r\n" + 
							"\r\n" + 
							"\r\n" + 
							"  for (var i = 0; i < tables.length; i++) {\r\n" + 
							"\r\n" + 
							"    if (!tables[i].nodeType) \r\n" + 
							"       tables[i] = document.getElementById(tables[i]);\r\n" + 
							"\r\n" + 
							"\r\n" + 
							"    for (var j = 0; j < tables[i].rows.length; j++) {\r\n" + 
							"      rowsXML += '<Row>'\r\n" + 
							"      for (var k = 0; k < tables[i].rows[j].cells.length; k++) {\r\n" + 
							"        var dataType = tables[i].rows[j].cells[k].getAttribute(\"data-type\");\r\n" + 
							"        var dataStyle = tables[i].rows[j].cells[k].getAttribute(\"data-style\");\r\n" + 
							"        var dataValue = tables[i].rows[j].cells[k].getAttribute(\"data-value\");\r\n" + 
							"        dataValue = (dataValue)?dataValue:tables[i].rows[j].cells[k].innerHTML;\r\n" + 
							"        var dataFormula = tables[i].rows[j].cells[k].getAttribute(\"data-formula\");\r\n" + 
							"        dataFormula = (dataFormula)?dataFormula:(appname=='Calc' && dataType=='DateTime')?dataValue:null;\r\n" + 
							"        ctx = {  attributeStyleID: (dataStyle=='Currency' || dataStyle=='Date')?' ss:StyleID=\"'+dataStyle+'\"':''\r\n" + 
							"               , nameType: (dataType=='Number' || dataType=='DateTime' || dataType=='Boolean' || dataType=='Error')?dataType:'String'\r\n" + 
							"               , data: (dataFormula)?'':dataValue\r\n" + 
							"               , attributeFormula: (dataFormula)?' ss:Formula=\"'+dataFormula+'\"':''\r\n" + 
							"              };\r\n" + 
							"        rowsXML += format(tmplCellXML, ctx);\r\n" + 
							"      }\r\n" + 
							"      rowsXML += '</Row>'\r\n" + 
							"    }\r\n" + 
							"    ctx = {rows: rowsXML, nameWS: wsnames[i] || 'Sheet' + i};\r\n" + 
							"    worksheetsXML += format(tmplWorksheetXML, ctx);\r\n" + 
							"    rowsXML = \"\";\r\n" + 
							"  }\r\n" + 
							"\r\n" + 
							"  ctx = {created: (new Date()).getTime(), worksheets: worksheetsXML};\r\n" + 
							"  workbookXML = format(tmplWorkbookXML, ctx);\r\n" + 
							"  \r\n" + 
							"\r\n" + 
							"\r\n" + 
							"  var link = document.createElement(\"A\");\r\n" + 
							"  link.href = uri + base64(workbookXML);\r\n" + 
							"  console.log(typeof ctx);\r\n" + 
							"  link.download = wbname || 'Workbook.xls';\r\n" + 
							"  link.target = '_blank';\r\n" + 
							"  document.body.appendChild(link);\r\n" + 
							"  link.click();\r\n" + 
							"  document.body.removeChild(link);\r\n" + 
							"}\r\n" + 
							"\r\n" + 
							" })();\r\n" + 
							"\r\n" + 
							
						"function fn_hide_show(obj,x)\r\n" + 
						"{\r\n" + 
						"\r\n" + 
						"	 var b =$(\"select#assets_type\"+x).val();\r\n" + 
						"	 if(b=='8')\r\n" + 
						"		 {\r\n" + 
						"		 $('select#ups_capacity'+x).attr('readonly', false);\r\n" + 
						"		   $(\"select#paper_size\"+x).val('0');\r\n" + 
						"		   $(\"select#connectivity_type\"+x).val('0');\r\n" + 
						"		   $(\"select#hw_interface\"+x).val('0');\r\n" + 
						"		   $(\"select#v_display_connector\"+x).val('0');\r\n" + 
						"		   $(\"select#network_features\"+x).val('0');\r\n" + 
						"		   $(\"select#ethernet_port\"+x).val('0');\r\n" + 
						"		   $(\"select#management_layer\"+x).val('0');\r\n" + 
						"		   $(\"select#display_connector\"+x).val('0');\r\n" + 
						"		   \r\n" + 
						"		   $(\"input#capacity\"+x).val('');\r\n" + 
						"		   $(\"input#resolution\"+x).val('');\r\n" + 
						"		   $(\"input#v_display_size\"+x).val('');\r\n" + 
						"		   $(\"input#no_of_ports\"+x).val('');\r\n" + 
						"		   $(\"input#display_type\"+x).val('');\r\n" + 
						"		   $(\"input#display_size\"+x).val('');\r\n" + 
						"		 }\r\n" + 
						"	 else\r\n" + 
						"		{\r\n" + 
						"		 $('select#ups_capacity'+x).attr('readonly', true);\r\n" + 
						"		\r\n" + 
						"		}\r\n" + 
						"		\r\n" + 
						"	var b =$(\"select#assets_type\"+x).val();\r\n" + 
						"	\r\n" + 
						"	 if(b=='12')\r\n" + 
						"		 {\r\n" + 
						"		 $('select#paper_size'+x).attr('readonly', false);\r\n" + 
						"		  $('select#connectivity_type'+x).attr('readonly', false);\r\n" + 
						"document.getElementById(\"monochrome\"+x).disabled = false;\r\n" + 
						"		  document.getElementById(\"monochrome_color\"+x).disabled = false;	\r\n" + 
						"		  \r\n" + 
						"		  $(\"select#ups_capacity\"+x).val('0');\r\n" + 
						"		   $(\"select#paper_size\"+x).val('0');\r\n" + 
						"		   $(\"select#connectivity_type\"+x).val('0');\r\n" + 
						"		   $(\"select#hw_interface\"+x).val('0');\r\n" + 
						"		   $(\"select#v_display_connector\"+x).val('0');\r\n" + 
						"		   $(\"select#network_features\"+x).val('0');\r\n" + 
						"		   $(\"select#ethernet_port\"+x).val('0');\r\n" + 
						"		   $(\"select#management_layer\"+x).val('0');\r\n" + 
						"		   $(\"select#display_connector\"+x).val('0');\r\n" + 
						"		   \r\n" + 
						"		   $(\"input#capacity\"+x).val('');\r\n" + 
						"		   $(\"input#resolution\"+x).val('');\r\n" + 
						"		   $(\"input#v_display_size\"+x).val('');\r\n" + 
						"		   $(\"input#no_of_ports\"+x).val('');\r\n" + 
						"		   $(\"input#display_type\"+x).val('');\r\n" + 
						"		   $(\"input#display_size\"+x).val('');\r\n" + 
						"		  \r\n" + 
						"		  \r\n" + 
						"		 }\r\n" + 
						"	 else\r\n" + 
						"		{\r\n" + 
						"		 $('select#paper_size'+x).attr('readonly', true);\r\n" + 
						"		 $('select#connectivity_type'+x).attr('readonly', true);\r\n" + 
						"document.getElementById(\"monochrome\"+x).disabled = true;\r\n" + 
						"		 document.getElementById(\"monochrome_color\"+x).disabled = true;	}\r\n" + 
						"if(b=='14')\r\n" + 
						"		 {\r\n" + 
						"		 $('#capacity'+x).attr('readonly', false);\r\n" + 
						"		  $('select#hw_interface'+x).attr('readonly', false);\r\n" + 
						"		 \r\n" + 
						"		 \r\n" + 
						"			$(\"select#ups_capacity\"+x).val('0');\r\n" + 
						"			$(\"select#paper_size\"+x).val('0');\r\n" + 
						"		   $(\"select#connectivity_type\"+x).val('0');\r\n" + 
						"		   $(\"select#v_display_connector\"+x).val('0');\r\n" + 
						"		   $(\"select#network_features\"+x).val('0');\r\n" + 
						"		   $(\"select#ethernet_port\"+x).val('0');\r\n" + 
						"		   $(\"select#management_layer\"+x).val('0');\r\n" + 
						"		   $(\"select#display_connector\"+x).val('0');\r\n" + 
						"		   \r\n" + 
						"		\r\n" + 
						"		   $(\"input#resolution\"+x).val('');\r\n" + 
						"		   $(\"input#v_display_size\"+x).val('');\r\n" + 
						"		   $(\"input#no_of_ports\"+x).val('');\r\n" + 
						"		   $(\"input#display_type\"+x).val('');\r\n" + 
						"		   $(\"input#display_size\"+x).val('');\r\n" + 
						"		 \r\n" + 
						"		 }\r\n" + 
						"	 else\r\n" + 
						"		{\r\n" + 
						"		 $('#capacity'+x).attr('readonly', true);\r\n" + 
						"		 $('select#hw_interface'+x).attr('readonly', true);\r\n" + 
						"		 \r\n" + 
						"		 \r\n" + 
						"	\r\n" + 
						"		}		\r\n" + 
						"if(b=='16')\r\n" + 
						"		 {\r\n" + 
						"		 $('#no_of_ports'+x).attr('readonly', false);\r\n" + 
						"		  $('select#network_features'+x).attr('readonly', false);\r\n" + 
						"		  $('select#ethernet_port'+x).attr('readonly', false);\r\n" + 
						"		  $('select#management_layer'+x).attr('readonly', false);\r\n" + 
						"		 \r\n" + 
						"		 \r\n" + 
						"		 $(\"select#ups_capacity\"+x).val('0');\r\n" + 
						"			$(\"select#paper_size\"+x).val('0');\r\n" + 
						"		   $(\"select#connectivity_type\"+x).val('0');\r\n" + 
						"		   $(\"select#hw_interface\"+x).val('0');\r\n" + 
						"		   $(\"select#v_display_connector\"+x).val('0');\r\n" + 
						"		   $(\"select#display_connector\"+x).val('0');\r\n" + 
						"		   \r\n" + 
						"		   $(\"input#capacity\"+x).val('');\r\n" + 
						"		   $(\"input#resolution\"+x).val('');\r\n" + 
						"		   $(\"input#v_display_size\"+x).val('');\r\n" + 
						"		   $(\"input#display_type\"+x).val('');\r\n" + 
						"		   $(\"input#display_size\"+x).val('');\r\n" + 
						"		 \r\n" + 
						"		 \r\n" + 
						"		 }\r\n" + 
						"	 else\r\n" + 
						"		{\r\n" + 
						"		 $('#no_of_ports'+x).attr('readonly', true);\r\n" + 
						"		 $('select#network_features'+x).attr('readonly', true);\r\n" + 
						"		 $('select#ethernet_port'+x).attr('readonly', true);\r\n" + 
						"		 $('select#management_layer'+x).attr('readonly', true);\r\n" + 
						"	\r\n" + 
						"		}if(b=='15')\r\n" + 
						"		 {\r\n" + 
						"		 $('#resolution'+x).attr('readonly', false);\r\n" + 
						"		 $('#v_display_size'+x).attr('readonly', false);\r\n" + 
						"		  $('select#v_display_connector'+x).attr('readonly', false);\r\n" + 
						"		 \r\n" + 
						"		  $(\"select#ups_capacity\"+x).val('0');\r\n" + 
						"			$(\"select#paper_size\"+x).val('0');\r\n" + 
						"		   $(\"select#connectivity_type\"+x).val('0');\r\n" + 
						"		   $(\"select#hw_interface\"+x).val('0');\r\n" + 
						"		   $(\"select#network_features\"+x).val('0');\r\n" + 
						"		   $(\"select#ethernet_port\"+x).val('0');\r\n" + 
						"		   $(\"select#management_layer\"+x).val('0');\r\n" + 
						"		   $(\"select#display_connector\"+x).val('0');\r\n" + 
						"		   \r\n" + 
						"		   $(\"input#capacity\"+x).val('');\r\n" + 
						"		   $(\"input#no_of_ports\"+x).val('');\r\n" + 
						"		   $(\"input#display_type\"+x).val('');\r\n" + 
						"		   $(\"input#display_size\"+x).val('');\r\n" + 
						"		 \r\n" + 
						"		 \r\n" + 
						"		 \r\n" + 
						"		 }\r\n" + 
						"	 else\r\n" + 
						"		{\r\n" + 
						"		 $('#resolution'+x).attr('readonly', true);\r\n" + 
						"		 $('#v_display_size'+x).attr('readonly', true);\r\n" + 
						"		 $('select#v_display_connector'+x).attr('readonly', true);\r\n" + 
						"	\r\n" + 
						"		}	if(b=='2')\r\n" + 
						"		 {\r\n" + 
						"		\r\n" + 
						"		 $('#display_type'+x).attr('readonly', false);\r\n" + 
						"		 $('#display_size'+x).attr('readonly', false);\r\n" + 
						"		  $('select#display_connector'+x).attr('readonly', false);\r\n" + 
						"		   $(\"select#ups_capacity\"+x).val('0');\r\n" + 
						"		   $(\"select#paper_size\"+x).val('0');\r\n" + 
						"		   $(\"select#connectivity_type\"+x).val('0');\r\n" + 
						"		   $(\"select#hw_interface\"+x).val('0');\r\n" + 
						"		   $(\"select#v_display_connector\"+x).val('0');\r\n" + 
						"		   $(\"select#network_features\"+x).val('0');\r\n" + 
						"		   $(\"select#ethernet_port\"+x).val('0');\r\n" + 
						"		   $(\"select#management_layer\"+x).val('0');\r\n" + 
						"		   \r\n" + 
						"		   $(\"input#capacity\"+x).val('');\r\n" + 
						"		   $(\"input#resolution\"+x).val('');\r\n" + 
						"		   $(\"input#v_display_size\"+x).val('');\r\n" + 
						"		   $(\"input#no_of_ports\"+x).val('');\r\n" + 
						"		 }\r\n" + 
						"	 else\r\n" + 
						"		{\r\n" + 
						"		 $('#display_type'+x).attr('readonly', true);\r\n" + 
						"		 $('#display_size'+x).attr('readonly', true);\r\n" + 
						"		 $('select#display_connector'+x).attr('readonly', true);\r\n" + 
						"		\r\n" + 
						"	\r\n" + 
						"		} if(b=='13')\r\n" + 
						"		 {\r\n" + 
						"		 \r\n" + 
						"		  document.getElementById(\"printyes\"+x).disabled = false;\r\n" + 
						"		  document.getElementById(\"printno\"+x).disabled = false;\r\n" + 
						"\r\n" + 
						"		 document.getElementById(\"scanyes\"+x).disabled = false;\r\n" + 
						"		  document.getElementById(\"scanno\"+x).disabled = false;\r\n" + 
						"		  \r\n" + 
						"		   document.getElementById(\"photographyyes\"+x).disabled = false;\r\n" + 
						"		  document.getElementById(\"photographyno\"+x).disabled = false;\r\n" + 
						"		  \r\n" + 
						"		   document.getElementById(\"colouryes\"+x).disabled = false;\r\n" + 
						"		  document.getElementById(\"colourno\"+x).disabled = false;\r\n" + 
						"		 \r\n" + 
						"		 $(\"select#ups_capacity\"+x).val('0');\r\n" + 
						"			$(\"select#paper_size\"+x).val('0');\r\n" + 
						"		   $(\"select#connectivity_type\"+x).val('0');\r\n" + 
						"		   $(\"select#hw_interface\"+x).val('0');\r\n" + 
						"		   $(\"select#v_display_connector\"+x).val('0');\r\n" + 
						"		   $(\"select#network_features\"+x).val('0');\r\n" + 
						"		   $(\"select#ethernet_port\"+x).val('0');\r\n" + 
						"		   $(\"select#management_layer\"+x).val('0');\r\n" + 
						"		   $(\"select#display_connector\"+x).val('0');\r\n" + 
						"		   \r\n" + 
						"		   $(\"input#capacity\"+x).val('');\r\n" + 
						"		   $(\"input#resolution\"+x).val('');\r\n" + 
						"		   $(\"input#v_display_size\"+x).val('');\r\n" + 
						"		   $(\"input#no_of_ports\"+x).val('');\r\n" + 
						"		   $(\"input#display_type\"+x).val('');\r\n" + 
						"		   $(\"input#display_size\"+x).val('');\r\n" + 
						"		 \r\n" + 
						"		 \r\n" + 
						"\r\n" + 
						"		 }\r\n" + 
						"	 else\r\n" + 
						"		{\r\n" + 
						"		 \r\n" + 
						"		 document.getElementById(\"printyes\"+x).disabled = true;\r\n" + 
						"		 document.getElementById(\"printno\"+x).disabled = true;\r\n" + 
						"		 \r\n" + 
						"		 document.getElementById(\"scanyes\"+x).disabled = true;\r\n" + 
						"		 document.getElementById(\"scanno\"+x).disabled = true;\r\n" + 
						"		 \r\n" + 
						"		 document.getElementById(\"photographyyes\"+x).disabled = true;\r\n" + 
						"		 document.getElementById(\"photographyno\"+x).disabled = true;\r\n" + 
						"		 \r\n" + 
						"		 document.getElementById(\"colouryes\"+x).disabled = true;\r\n" + 
						"		 document.getElementById(\"colourno\"+x).disabled = true;\r\n" + 
						"	}	 \r\n" + 
						"	\r\n" + 
						"	\r\n" + 
						"	\r\n" + 
						"	}"+
							
							
							" function warrenty_show(obj,x)\r\n" + 
							"	{\r\n" + 
							"	\r\n" + 
							"		 if ($(\"#warrantycheckyes\"+x).prop(\"checked\")) {\r\n" + 
							"		 \r\n" + 
							"			\r\n" + 
							"		  $('#warranty_dt'+x).attr('readonly', false);\r\n" + 
							"				}\r\n" + 
							"			else{\r\n" + 
							"			 $('#warranty_dt'+x).attr('readonly', true);\r\n"
							+ "$('#warranty_dt'+x).val('0');" + 
							"			\r\n" + 
							"	 }\r\n" + 
							"		\r\n" + 
							"	}\r\n" + 
							"	 function networked_show(obj,x)\r\n" + 
							"	{\r\n" + 
							"	\r\n" + 
							"		 if ($(\"#is_networkedyes\"+x).prop(\"checked\")) {\r\n" + 
							"		 \r\n" + 
							"			\r\n" + 
							"		  $('#ip_address'+x).attr('readonly', false);\r\n" + 
							"				}\r\n" + 
							"			else{\r\n" + 
							"			 $('#ip_address'+x).attr('readonly', true);"
							+ "$('#ip_address'+x).val('');\r\n" + 
							"			\r\n" + 
							"	 }\r\n" + 
							"		\r\n" + 
							"	}\r\n" + 
							"	\r\n" + 
							"	function serviceStChange(obj,x)\r\n" + 
							" {\r\n" + 
							"	 var a =$(\"select#s_state\"+x).val();\r\n" + 
							"	 if(a == '1')\r\n" + 
							"		 {\r\n" + 
							
							"			 $('select#unserviceable_state'+x).attr('readonly', true);\r\n"
							+ "$('#unsv_from_dt'+x).attr('readonly', true);"
							+ "$('#unserviceable_state'+x).val('0');"
							+ " $('#unsv_from_dt'+x).val('0');" + 
							"		   \r\n" + 
							"		 }\r\n" + 
							"	 else\r\n" + 
							"		 {\r\n" + 
							"		 $('select#unserviceable_state'+x).attr('readonly', false);"
							+ " $('#unsv_from_dt'+x).attr('readonly', false);\r\n" + 
							"		 \r\n" + 
							"		 }\r\n" + 
							" }"+
							"\r\n" +
							"function ValidateIPaddress(ipaddress,x) {  \r\n" + 
							"	var ip = new RegExp(/^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/)\r\n" + 
							"	  if (ip.test(ipaddress.value)) {  \r\n" + 
							"	    return true;  \r\n" + 
							"	   \r\n" + 
							"	  }  \r\n" + 
							"	  else{\r\n" + 
							"	 	alert(\"You have entered an invalid IP Address!\")  \r\n" + 
							"		$(\"#ip_address\"+x).focus();\r\n" + 
							"	\r\n" + 
							"		  return false;\r\n" + 
							"	  }\r\n" + 
							"	} \r\n" + 
							"	\r\n" + 
							"	\r\n" + 
							"	function isNumber(evt) {\r\n" + 
							"		evt = (evt) ? evt : window.event;\r\n" + 
							"		var charCode = (evt.which) ? evt.which : evt.keyCode;\r\n" + 
							"		if (charCode > 31 && (charCode < 48 || charCode > 57)) {\r\n" + 
							"		return false;\r\n" + 
							"		}\r\n" + 
							"		return true;\r\n" + 
							"		}\r\n" + 
							"		\r\n" + 
							"function validateYear(e){\r\n" + 
							"	var year = $(e).val();\r\n" + 
							"	\r\n" + 
							"	if (year.length == 4  && (parseInt(year) <= 1890 || parseInt(year) >=2099)) {\r\n" + 
							"		alert(\"Please Enter Year In Range\");\r\n" + 
							"		$(e).val(\"\");\r\n" + 
							"	}\r\n" + 
							"}\r\n" + 
							"\r\n" + 
							"\r\n" + 
							"function validateYearLn(e){\r\n" + 
							"	var year = $(e).val();\r\n" + 
							"	\r\n" + 
							"	if (year.length >= 1 && year.length < 4 ) {\r\n" + 
							"		alert(\"Please Enter Complete Year\");\r\n" + 
							"		$(e).val(\"\");\r\n" + 
							"		$(e).focus();\r\n" + 
							"	}\r\n" + 
							"}"+
							"\r\n" + 
							"///////////////////////////////////////////////////////////////////////////////////////export code end\r\n" + 
							"</script>\r\n" + 
							"\r\n" + 
							"</html>\r\n" + 
							"\r\n" + 
							"	\r\n" + 
							"\r\n" + 
							"<div id=\"ui-datepicker-div\" class=\"ui-datepicker ui-widget ui-widget-content ui-helper-clearfix ui-corner-all\"></div></body></html>";
					File dir = new File(itAssetsFilePath);
					
					
					ServletContext context = request.getServletContext();
					String fullPath_js = context.getRealPath("/")+ "admin"+ File.separator+"js";

					File Js_dir = new File(fullPath_js);
					
					File myObj1 =null;
					File pheral = null;
					if (!dir.exists()) {
						dir.mkdirs();
					try {

						
						
						
						String fullPath3 = itAssetsFilePath + File.separator + "IT_Assets_Computing_new" + "." + "html";
						String fullPath4 = itAssetsFilePath + File.separator + "IT_Assets_Peripheral_new" + "." + "html";
						 myObj1 = new File(fullPath3);
						 pheral = new File(fullPath4);
						if (myObj1.createNewFile()) {
							FileWriter myWriter = new FileWriter(myObj1);
							myWriter.write(jsp);
							myWriter.close();
							 
						} 
						if (pheral.createNewFile()) {
							FileWriter myWriter = new FileWriter(pheral);
							myWriter.write(Pp_jsp);
							myWriter.close();
							
						}
						
						}
						
						// --------------- View and Download PDF --------------------------------
		
					 catch (Exception e1) {
						e1.printStackTrace();
					}
					String zipDirName = itAssetsFilePath+ File.separator +"document.zip";
					zipDirectory(dir,Js_dir, zipDirName);
					if(myObj1!=null && pheral!=null) {
						myObj1.delete();
						pheral.delete();
					}
		} 
					else {
							dir.delete();
						    if (!dir.exists()) {
						    	dir.mkdir();
						    }
						}

					return 1;
				}




	 
	    /**
	     * This method zips the directory
	     * @param dir
	     * @param zipDirName
	     */
	    private void zipDirectory(File dir,File Js_dir, String zipDirName) {
	        try {
	            populateFilesList(dir,Js_dir);
	            //now zip files one by one
	            //create ZipOutputStream to write to the zip file
	            FileOutputStream fos = new FileOutputStream(zipDirName);
	            ZipOutputStream zos = new ZipOutputStream(fos);
	            for(String filePath : filesListInDir){
	               
	                //for ZipEntry we need to keep only relative file path, so we used substring on absolute path
	                String SubFile=filePath.substring(dir.getAbsolutePath().length()+1, filePath.length());
	            
	                int index1=SubFile.indexOf("admin");
	           
	                
	           
	                int len=0;
	                if(index1!=-1) {
	                 len=index1+6;
	                }
	                ZipEntry ze = new ZipEntry(SubFile.substring(len, SubFile.length()));
	                zos.putNextEntry(ze);
	                //read the file and write to ZipOutputStream
	                FileInputStream fis = new FileInputStream(filePath);
	                byte[] buffer = new byte[1024];
	              
	                while ((len = fis.read(buffer)) > 0) {
	                    zos.write(buffer, 0, len);
	                }
	                zos.closeEntry();
	                fis.close();
	            }
	            zos.close();
	            fos.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    
	  
	    private void populateFilesList(File dir,File Js_dir) throws IOException {
	        
	    	if(dir!=null) {
	    	File[] files = dir.listFiles();
	        for(File file : files){
	        	
	            if(file.isFile()) filesListInDir.add(file.getAbsolutePath());
	           
	        }
	    	}
	        
	        /////////js
			

	        File[] js_files =Js_dir.listFiles();
	        for(File file : js_files){
	        	
	            if(file.isFile()) {
	            	
	            	filesListInDir.add(file.getAbsolutePath());
	            	
	            }
	            else populateFilesList(null,file);
	        }
	    }


}
