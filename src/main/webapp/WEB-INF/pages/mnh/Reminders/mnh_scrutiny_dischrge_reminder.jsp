<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

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
 



<form:form action="Scrutiny_DischargeAction" id="Scrutiny_DR" method="post" class="form-horizontal" commandName="Scrutiny_DischargeCMD">
   <div class="">
      <div class="container" align="center">
          <div class="card">
              <div class="card-header mnh-card-header">
		            <b>DISCHARGE REMINDER UPADATE</b>
		      </div> 
               <div class="card-body card-block">    
                   <div class="col-md-12" >
		                 <div class="col-md-2" style="text-align: left;">
	                 		  <label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Discharge Date </label>
	               		 </div>	               		 
	               		 <div class="col-md-4">
	               		  <input type="hidden" id="id_hid" name="id_hid">
	               		   <input type="hidden" id="dschrg_datehid" name="dschrg_datehid">		
		                    <input type="hidden" id="disposalhid" name="disposalhid">
		                    <input type="hidden" id="diagnosis_code1ahid" name="diagnosis_code1ahid">
				            <input type="hidden" id="icd_cause_code1ahid" name="icd_cause_code1ahid">
				            <input type="hidden" id="discharge_remarkshid" name="discharge_remarkshid">
				            
	               		  <input type="date" name="dschrg_dt" id="dschrg_dt" class="form-control-sm form-control" >
	               		 </div>	  
	               		  <div class="col-md-2" style="text-align: left;">
	                 		  <label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Disposal </label>
	               		 </div>	               		 
	               		 <div class="col-md-4">
	               		
	             			  <input type="text" name="disposal" id="disposal"  class="form-control-sm form-control" placeholder="Please Enter Disposal" autocomplete="off">
	               		 </div>	               		 
		            </div> 
		            <div class="col-md-12">
		                 <div class="col-md-2" style="text-align: left;">
	                 		  <label for="text-input" class=" form-control-label">Diagnosis Code1a </label>
	               		 </div>	               		 
	               		 <div class="col-md-4">
	             			  <input type="text" name="diagnosis_code1a" id="diagnosis_code1a" class="form-control-sm form-control" autocomplete="off" placeholder="Please Enter Diagnosis Codela">
	               		 </div>	  
	               		  <div class="col-md-2" style="text-align: left;">
	                 		  <label for="text-input" class=" form-control-label">Icd Cause Code1a </label>
	               		 </div>	               		 
	               		 <div class="col-md-4">
	             			  <input type="text" name="icd_cause_code1a" id="icd_cause_code1a" class="form-control-sm form-control" autocomplete="off" placeholder="Please Enter Icd Cause Codela">
	               		 </div>	               		 
		            </div> 		
  					<div class="col-md-12">
		                 <div class="col-md-2" style="text-align: left;">
	                 		  <label for="text-input" class=" form-control-label">Discharge Remarks</label>
	               		 </div>	               		 
	               		 <div class="col-md-4">
	             			  <input type="text" name="discharge_remarks" id="discharge_remarks" class="form-control-sm form-control" placeholder="Please Enter Discharge Remark" autocomplete="off">
	               		 </div>	  
	               		                 		 
		            </div>            
		      </div>     
		      
              <div class="form-control card-footer" align="center">
				<a	href="mnh_scrutiny_dischrge_reminder" type="reset" class="btn btn-primary btn-sm" onclick="btn_clc();"> Clear </a> 
				<i class="fa fa-search"></i><input type="button" class="btn btn-success btn-sm" id="btn_serach" value="Search"	onclick="search()" >
				<input type="submit" id="update_btn" class="btn btn-success btn-sm" value="Update" style="display: none"> 
			</div>
			
               </div>
              <div class="card-body"  id="divPrint" style="display: none;">
                   <div id="divShow" style="display: block;">	
                       <div class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
                            <span id="ip"></span>
                            <table id="SearchReport" class="table table-striped  table-hover  table-bordered">
                                  <thead style="background-color: #9c27b0; color: white;">
                                        <tr>
                                            <th style="font-size: 14px;text-align: center;">Sr No</th>
								            <th style="font-size: 14px;text-align: center;">Discharge Date</th>
								            <th style="font-size: 14px;text-align: center;">Disposal</th>
								            <th style="font-size: 14px;text-align: center;">Diagnosis code1a</th>
								            <th style="font-size: 14px;text-align: center;">Icd cause code1a</th>								           
								            <th style="font-size: 14px;text-align: center;">Discharge_remarks</th>
								            <th style="font-size: 14px;text-align: center;" width="10%">Action</th>							            
                                        </tr>
                                  </thead>
                                        <c:forEach var="item" items="${list}" varStatus="num">
                                           <tr>
                                              <th style="font-size: 14px;text-align: center;">${num.index+1}</th>
                                              <th style="font-size: 14px;text-align: center;" >${item.dschrg_date}</th>
									          <th style="font-size: 14px;text-align: center;" >${item.disposal}</th>
									          <th style="font-size: 14px;text-align: center;" >${item.diagnosis_code1a}</th>
									          <th style="font-size: 14px;text-align: center;" >${item.icd_cause_code1a}</th>									          
									          <th style="font-size: 14px;text-align: center;" >${item.discharge_remarks}</th>
									          <th id="thAction1" style="font-size: 14px;text-align: center;" width="10%">${item.id}</th>	
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
</form:form>

<c:url value="search_scrutiny_dischargeList" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="dschrg_date1">
      <input type="hidden" name="dschrg_date1" id="dschrg_date1"/>
	  <input type="hidden" name="disposal1" id="disposal1"/>
	  <input type="hidden" name="diagnosis_code1a1" id="diagnosis_code1a1"/>
      <input type="hidden" name="icd_cause_code1a1" id="icd_cause_code1a1"/>
	  <input type="hidden" name="discharge_remarks1" id="discharge_remarks1"/>  
</form:form> 


<script>
function btn_clc(){
	$("#dschrg_dt").val('');
	$("#disposal").val('');
	$("#diagnosis_code1a").val('');
	$("#icd_cause_code1a").val('');
	$("#discharge_remarks").val('');
}

var dateControler = {
        currentDate : null
    }
         $(document).on( "change", "#dschrg_dt",function(event, ui ) {
             var now = new Date();
             var selectedDate = new Date($(this).val());
             if(selectedDate > now) {
                 $(this).val(dateControler.currentDate)
                 alert("Please Not Select Future Discharge Date");
             } else {
                 dateControler.currentDate = $(this).val();                 
             }
         }); 
function search(){
	
	$("#dschrg_date1").val($("#dschrg_dt").val());		
	$("#disposal1").val($("#disposal").val());	
	$("#diagnosis_code1a1").val($("#diagnosis_code1a").val());
	$("#icd_cause_code1a1").val($("#icd_cause_code1a").val());		
	$("#discharge_remarks1").val($("#discharge_remarks").val());	
	$("#searchForm").submit();
	
}



function editData(id,dschrg_date,disposal,diagnosis_code1a,icd_cause_code1a,discharge_remarks){

	
$("#disposalhid").val('');
$("#diagnosis_code1ahid").val('');
$("#icd_cause_code1ahid").val('');
$("#discharge_remarkshid").val('');
$("#id").val('');
$("#dschrg_datehid").val($("#dschrg_dt").val());
$("#disposalhid").val($("#disposal").val());
$("#diagnosis_code1ahid").val($("#diagnosis_code1a").val());
$("#icd_cause_code1ahid").val($("#icd_cause_code1a").val());
$("#discharge_remarkshid").val($("#discharge_remarks").val());

$("#id_hid").val($("#id").val());


$("#dschrg_dt").val($("#dschrg_dt").val());
$("#disposal").val(disposal);
$("#diagnosis_code1a").val(diagnosis_code1a);
$("#icd_cause_code1a").val(icd_cause_code1a);
$("#discharge_remarks").val(discharge_remarks);
$("#id_hid").val(id);


if($("#icd_cause_code1a").val() == null){
	$("#icd_cause_code1a").val(icd_cause_code1a);
}
if($("#discharge_remarks").val() ==null){
	$("#discharge_remarks").val(discharge_remarks);
} 	
$("#update_btn").show();	
$("#btn_serach").hide();
}
</script>

<script>
$(document).ready(function() {	
	
	
	if ('${disposal1}' != ""  || '${dschrg_date1}' != ""){	
		$("#divPrint").show();
		$("#disposal").val('${disposal1}');		
		$("#dschrg_dt").val('${dschrg_date1}');	
		$("#diagnosis_code1a").val('${diagnosis_code1a1}');		
		$("#icd_cause_code1a").val('${icd_cause_code1a1}');	
		$("#discharge_remarks").val('${discharge_remarks1}');
		

	} 	
	if('${size}' == 0 && '${size}' != ""){
		$("#divPrint").show();
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