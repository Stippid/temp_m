<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="Stylesheet" href="js/Calender/jquery-ui.css" >
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>
 
<link href="js/JS_CSS/jquery.dataTables.min.css" rel="stylesheet"> 

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>


<form:form action="" id="" method="post" class="form-horizontal" commandName="">
   <div class="">
      <div class="container" align="center">
          <div class="card">
              <div class="card-header mnh-card-header">
		            <b>HOSPITAL INPUT/DEFAULTER REMINDER</b>
		      </div> 
		       
              <div class="card-body card-block"> 
                    <div class="col-md-12 row form-group">
                         <div class="col-md-2" style="text-align: left;">
		                 		  <label for="text-input" class=" form-control-label"> Command</label>
	               		 </div> 
	               		 
	               		 <div class="col-md-4">
	               		        <select name="command" id="command" class="form-control-sm form-control">
                                         <c:if test="${r_1[0][1] != 'COMMAND'}">
                                                 <option value="ALL">-- All Command --</option>
                                         </c:if>
                                         <c:if test="${not empty ml_1[0]}">
                                                 <c:set var="data" value="${ml_1[0]}" />
                                                 <c:set var="datap" value="${fn:split(data,',')}" />
                                                 <c:forEach var="j" begin="0" end="${fn:length(datap)-1}">
                                                         <c:set var="dataf" value="${fn:split(datap[j],':')}" />
                                                         <option value="${dataf[0]}" name="${dataf[2]}">${dataf[2]}</option>
                                                 </c:forEach>
                                         </c:if>
                                </select>
	               		 </div> 
	               		 
	               		 <div class="col-md-2" style="text-align: left;">
	                 		   <label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Input Type</label>
	               		 </div>	               		 
	               		 <div class="col-md-4">
	             			   <select name="input_type" id="input_type" class="form-control-sm form-control">
										<option value="-1">-- select --</option>
										<option value="A&D">Admission Discharge</option>
										<option value="OPD">Out Patient Details(OPD)</option>
										<option value="BO">Bed Occupancy</option>
										<option value="OSP">OPD Special Procedure</option>
										<option value="Mortality">Mortality</option>
										<option value="IMB">IMB</option>
							   </select>
	               		 </div>
                    </div>
	               	  					
  					<div class="col-md-12 row form-group" style="display: none;margin-top: -10px;" id="d1">
		                 <div class="col-md-2" style="text-align: left;">
	                 		  <label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Date From </label>
	               		 </div>	               		 
	               		 <div class="col-md-4">
	             			  <input type="date" name="from_date" id="from_date" class="form-control-sm form-control">
	               		 </div>	  
	               		  <div class="col-md-2" style="text-align: left;">
	                 		  <label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Date To </label>
	               		 </div>	               		 
	               		 <div class="col-md-4">
	             			  <input type="date" name="to_date" id="to_date" class="form-control-sm form-control">
	               		 </div>	               		 
		             </div>  
		             
		             <div class="col-md-12 row form-group" style="display: none;margin-top: -10px;" id="d2">
		                 <div class="col-md-2" style="text-align: left;">
	                 		  <label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Quarter</label>
	               		 </div>	               		 
	               		 <div class="col-md-4">
	             			   <select name="quater" id="quater" class="form-control-sm form-control">
               					      <option value="-1">--Select--</option>
									  <option value="JAN - MAR">JAN - MAR</option>
						              <option value="APR - JUN">APR - JUN</option>
						              <option value="JUL - SEP">JUL - SEP</option>
					                  <option value="OCT - DEC">OCT - DEC</option>
					                </select>
	               		 </div>	 	 
	               		 
	               		 <div class="col-md-2" style="text-align: left;">
	                 		  <label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Year</label>
	               		 </div>               		 
	               		 <div class="col-md-4">
	               		       <input type="text" id="year" name="year" class="form-control-sm form-control" autocomplete="off" maxlength="4" 
	               		       onkeypress="return isNumberPointKey(event);">
	               		 </div>              		  	               		 
		             </div>          
		       </div>     
		     
		       
               <div class="form-control card-footer" align="center">
				<a	href="search_air_ShoInput" type="reset" class="btn btn-primary btn-sm" onclick="btn_clc();"> Clear </a> 
				<i class="fa fa-search"></i><input type="button" class="btn btn-success btn-sm" id="btn_serach" value="Search"	onclick="SearchData();">
			</div>
               <div class="card-body" id="divPrint" style="display: none;">
                   <div id="divShow" style="display: block;">
			 	        <div style="width: 20%; display: show;" id="divSerachInput"></div>
				        <div class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
				             <span id="ip"></span>					 
				             <table id="SearchReport" class="table table-striped  table-hover  table-bordered">
				                  <thead style="background-color: #9c27b0; color: white;">
							            <tr>
								            <th style="font-size: 14px;text-align: center;" width="9%">Ser No</th>	
                                            <th style="font-size: 14px;text-align: center;">SUS No</th>
			                                <th style="font-size: 14px;text-align: center;">Hospital Name</th>     
							           </tr>
						         </thead> 
						         <tbody>
						               <c:forEach var="item" items="${list}" varStatus="num" >
								            <tr>
									            <th style="font-size: 14px;text-align: center;" width="9%">${num.index+1}</th>
												<th style="font-size: 14px;">${item.sus_no}</th>
										        <th style="font-size: 14px;">${item.unit_name}</th>	
								            </tr>	
					                  </c:forEach>
			 	                 </tbody>
			                 </table>
		                </div>
		           </div>
		      </div>    
		                     
          </div>
      </div>     
   </div>
</form:form>

<c:url value="search_reminder_input" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="cmd1">
     <input type="hidden" name="cmd1" id="cmd1"/>
     <input type="hidden" name="dt_frm" id="dt_frm"/>
     <input type="hidden" name="dt_to" id="dt_to"/>
     <input type="hidden" name="qtr" id="qtr"/>
     <input type="hidden" name="yr1" id="yr1"/>
     <input type="hidden" name="tbl_name" id="tbl_name"/>
</form:form>



<!-- for Functions -->
<script>
function btn_clc(){
	location.reload(true);
	$("#input_type").val('-1');
	$().getCurDt(to_date);    
	$().getFirDt(from_date); 
	$("#quater").val('-1');
}

function isNumberPointKey(evt) {
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if(charCode != 46 && charCode > 31 && (charCode<48 || charCode>57)){
		return false;
	}else{
		if(charCode == 46){
			return false;
		}
	}
	return true;
}

function SearchData(){
	 var a = $("#input_type").val();
	
	if(a == "-1"){
		alert("Please Select the Input Type");
		$("#input_type").focus();
		return false;
	}
	
	if(a == "A&D"){
		if($("#from_date").val() == ""){
			alert("Please Select the From Date");
			$("#from_date").focus();
			return false;
		}
		
		if($("#to_date").val() == ""){
			alert("Please Select the To Date");
			$("#to_date").focus();
			return false;
		}
	}
	
	if(a == "OPD" || a == "BO" || a == "OSP" || a == "Mortality" || a == "IMB"){
		if($("#quater").val() == "-1"){
			alert("Please Select the Quarter");
			$("#quater").focus();
			return false;
		}
		
		if($("#year").val() == ""){
			alert("Please Enter the Year");
			$("#year").focus();
			return false;
		}
	}
	
	$("#cmd1").val($("#command").val());
	$("#dt_frm").val($("#from_date").val());
	$("#dt_to").val($("#to_date").val());
	$("#qtr").val($("#quater").val());
	$("#yr1").val($("#year").val());
	$("#tbl_name").val($("#input_type").val());
	$("#searchForm").submit();
}
</script>

<!-- for On Load Methods -->
<script>
$(document).ready(function() {
	var d = new Date();
	var year = d.getFullYear();
	$("#year").val(year);
	
	$("#input_type").change(function(){
		var a = this.value;
		
		if(a == "A&D"){
			$("#d1").show();
			$("#d2").hide();
		}
		else if(a == "-1"){
			$("#d1").hide();
			$("#d2").hide();
		}else{
			$("#d1").hide();
			$("#d2").show();
		}
	});
	
	if('${size}' != 0){
		$("#divPrint").show();
	} 
	
	if('${size}' == 0 && '${size}' != ""){
		$("#divPrint").show();
	}
	
	
	
	var q = '${dt_frm}';
	var q1 = '${dt_to}';
	var q2 = '${qtr}';
	var q3 = '${tbl_name}';
	var q4 = '${cmd1}';
	var q5 = '${yr1}';
	
	if(q != ""){
		$("#from_date").val(q);
	}else{
		$().getCurDt(to_date);  
	}
	
    if(q1 != ""){
    	$("#to_date").val(q1);
	}else{
		$().getFirDt(from_date); 
	}
    
    if(q2 != ""){
    	$("#quater").val(q2);
    }
    
    if(q3 != ""){
    	$("#input_type").val(q3);
    	
    	if(q3 == "A&D"){
			$("#d1").show();
			$("#d2").hide();
		}
		else if(q3 == "-1"){
			$("#d1").hide();
			$("#d2").hide();
		}else{
			$("#d1").hide();
			$("#d2").show();
		}
    }
    
    if(q4 != ""){
    	$("#command").val(q4);
    }
    
    if(q5 != ""){
    	$("#year").val(q5);
    }

	try{
		if(window.location.href.includes("msg")){
			var url = window.location.href.split("msg")[0];
		    window.location = url;
	    } 
	}catch (e) {
		// TODO: handle exception
	}	
	
});
</script>