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

<link rel="stylesheet" href="js/cue/cueWatermark.css">

<form:form action="" id="" method="post" class="form-horizontal" commandName="">
   <div class="">
      <div class="container" align="center">
          <div class="card">
              <div class="card-header mnh-card-header">
		            <b>DAILY DATA STATUS</b>
		      </div> 
		    
              <div class="card-body card-block">    			
                     <div class="col-md-12 row form-group" style="margin-top: -10px;">							
	               		 <div class="col-md-2" style="text-align: left;">
		                 		   <label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Username</label>
		               	 </div>
		               		 
		               	 <div class="col-md-4">		
		               			   <select name="username" id="username" class="form-control-sm form-control">	
	           						       <option value="-1">--Select the Value--</option>
	           						       <c:forEach var="j" begin="0" end="${fn:length(ml_2)-1}">
	           						             <c:set var="datap" value="${fn:split(ml_2[j],':')}"/>
	           						             <c:if test="${empty datap[1]}">
	           						             </c:if> 
	           						   
	           						             <c:if test="${not empty datap[1]}">
	           						                   <option value="${datap[0]}" name="${datap[1]}">${datap[0]}</option>
	           						             </c:if>
								           </c:forEach>          						
							       </select>
						  </div>	 
					</div> 
		      </div>     
		      
	
		      <div class="card-footer" align="center" style="margin-top: -20px;">
			 <a href="mnh_stillin_hospital_datastatus" type="reset" class="btn btn-primary btn-sm" onclick="btn_clc();" > Clear </a> 
		        <i class="fa fa-search"></i> <input type="button" id="btn_serach" class="btn btn-success btn-sm" value="Search" onclick="return search()" />      		         
              </div>
              
              <div class="card-body"  id="divPrint" style="display: none;">
                   <div id="divShow" style="display: block;">	
                       <div class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
                            <span id="ip"></span>
                            <table id="SearchReport" class="table table-striped  table-hover  table-bordered">
                                  <thead style="background-color: #9c27b0; color: white;">
                                        <tr>
                                            <th style="font-size: 14px;text-align: center;">Ser No</th>
								            <th style="font-size: 14px;text-align: center;">A&D Number</th>
								            <th style="font-size: 14px;text-align: center;">Patient Name</th>
								            <th style="font-size: 14px;text-align: center;">Admission Date</th>
								            <th style="font-size: 14px;text-align: center;">Personnel No</th>
								            <th style="font-size: 14px;text-align: center;">Personnel Name</th>
								            <th style="font-size: 14px;text-align: center;">Rank</th>	
								            <th style="font-size: 14px;text-align: center;">Discharge Remarks</th>
								            <th style="font-size: 14px;text-align: center;">Diagnoses Code</th>
								            <th style="font-size: 14px;text-align: center;">Cause Code</th>
								            <th style="font-size: 14px;text-align: center;">Discharge Date</th>							            
								            <th style="font-size: 14px;text-align: center;">Disposal</th>
                                        </tr>
                                  </thead>
                                        <c:forEach var="item" items="${list}" varStatus="num">
                                           <tr>
                                              <th style="font-size: 14px;text-align: center;">${num.index+1}</th>
                                              <th style="font-size: 14px;text-align: center;" >${item.and_no}</th>
									          <th style="font-size: 14px;text-align: center;" >${item.name}</th>
									          <th style="font-size: 14px;text-align: center;" >${item.admsn_date}</th>
									          <th style="font-size: 14px;text-align: center;" >${item.persnl_no}</th>
									          <th style="font-size: 14px;text-align: center;" >${item.persnl_name}</th>
									          <th style="font-size: 14px;text-align: center;" >${item.rank}</th>									          
									          <th style="font-size: 14px;text-align: center;" >${item.discharge_remarks}</th>
									          <th style="font-size: 14px;text-align: center;" >${item.diagnosis_code1d}</th>
									          <th style="font-size: 14px;text-align: center;" >${item.icd_cause_code1d}</th>
									          <th style="font-size: 14px;text-align: center;" >${item.dschrg_date}</th>
									          <th style="font-size: 14px;text-align: center;" >${item.disposal}</th>	
                                           </tr>
                                        </c:forEach>
                                  <tbody>
                                  </tbody>
                            </table>		
                       </div>
                   </div>
              </div>                  
                              
          </div>
      </div>     
   </div>
</form:form>

<c:url value="search_reminder_discharge" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="unit1">
      <input type="hidden" name="unit1" id="unit1"/>
	  <input type="hidden" name="sus1" id="sus1"/>
	  <input type="hidden" name="cmd1" id="cmd1"/>
      <input type="hidden" name="dt_frm" id="dt_frm"/>
	  <input type="hidden" name="dt_to" id="dt_to"/>  
</form:form> 
									

<!-- for Functions -->
<script>
function btn_clc(){
	location.reload(true);
	$("#unit_name1").val('');
	$("#sus_no1").val('');
	$("#command").val('-1');
	$().getCurDt(to_date);    
	$().getFirDt(from_date); 
}

function search(){
	if($("#from_date").val() == ""){
		alert("Please Select the From Date");
		$("#from_date").focus();
		return false;
	}
	
	if($("#to_date").val() == ""){
		alert("Please Select the From Date");
		$("#to_date").focus();
		return false;
	}
	
	$("#unit1").val($("#unit_name1").val());	
	$("#sus1").val($("#sus_no1").val());	
	$("#cmd1").val($("#command").val());
	$("#dt_frm").val($("#from_date").val());		
	$("#dt_to").val($("#to_date").val());	
	$("#searchForm").submit();
}

function getCommand(y){
	$("#command").attr('disabled',true);
	var FindWhat = "COMMAND";
	
		$.post("getMNHHirarNameBySUS?"+key+"="+value, {FindWhat:FindWhat,a:y}).done(function(j) {
		var a = [];
		var enc = j[j.length-1].substring(0,16);
		for(var i = 0; i < j.length; i++){
			a[i] = dec(enc,j[i]);
		}
		var data=a[0].split(",");
		var datap;
		
		for(var i = 0; i < data.length-1; i++) {
			datap=data[i].split(":");
			$("#command").val(datap[1]);  
			//$("#command_sus").val(datap[5]); 
		}	
	}); 
}

$("#sus_no1").keyup(function(){
	var sus_no = this.value;
	var para = '${r_1[0][1]}';
	var paravalue="";
	if(para == "MISO"){
		paravalue="ALL";
	}
	
	$().Autocomplete2('GET','getMNHAutoList',sus_no1,{a:sus_no,b:para,c:"SUS",d:paravalue},'getMNHUnitNameBySUSNo',unit_name1);
});

$("#unit_name1").keyup(function(){
	var unit_name = this.value;
	var para = '${r_1[0][1]}';
	var paravalue="";
	if(para == "MISO"){
		paravalue="ALL";
	}
	
	$().Autocomplete2('GET','getMNHAutoList',unit_name1,{a:unit_name,b:para,c:"NAME",d:paravalue},'getMNHSUSNoByUnitName',sus_no1);
});	
</script>

<!-- for On Load Methods -->
<script>
$(document).ready(function() {
	if('${size}' != 0){
		$("#divPrint").show();
	} 
	
	if('${size}' == 0 && '${size}' != ""){
		$("#divPrint").show();
		//$("table#SearchReport").append("<tr><td colspan='9' style='text-align: center;font-weight:bold;'>Data Not Available</td></tr>");
	}
	

	var q = '${unit1}';
	var q1 = '${sus1}';
	var q2 = '${cmd1}';
	var q3 = '${dt_frm}';
	var q4 = '${dt_to}';
	
	if(q != ""){
    	$("#unit_name1").val(q);
    }
	
	if(q1 != ""){
    	$("#sus_no1").val(q1);
    }
	
	if(q2 != ""){
    	$("#command").val(q2);
    }
	
	if(q3 != ""){
		$("#from_date").val(q3);
	}else{
		$().getFirDt(from_date); 
	}
	
    if(q4 != ""){
    	$("#to_date").val(q4);
	}else{
		$().getCurDt(to_date);  
	}
    
    $('#unit_name1').change(function(){
		var y = this.value;
		
		
			$.post("getMNHSUSNoByUnitName?"+key+"="+value, {y:y}).done(function(j) {
			var enc = j[j.length-1].substring(0,16);
			var a = dec(enc,j[0]);
			getCommand(a);		
		});
		
    }); 
	
	$('#sus_no1').change(function(){
		var y = this.value;
		getCommand(y);
    });  	
    
	try{
		if(window.location.href.includes("msg=")){
			var url = window.location.href.split("?msg")[0];
		    window.location = url;
	    } 
	}catch (e) {
		// TODO: handle exception
	}	
	
});
</script>