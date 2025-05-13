<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="Stylesheet" href="js/Calender/jquery-ui.css" >
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>
 
<link href="js/amin_module/rbac/datatables/jquery.dataTables.min.css" rel="stylesheet"> 
<script src="js/amin_module/rbac/datatables/jquery.dataTables.js"></script>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>



<%
   String nPara=request.getParameter("Para");
String p_para =request.getParameter("para2");
  
%>
<form:form action="scrutiny_edit_dsAction" method="post" class="form-horizontal" id="scrutiny_edit_ds" commandName="scrutiny_edit_dsCMD">
   <div class="">
      <div class="container" align="center">
          <div class="card">
              <div class="card-header mnh-card-header">
               <% if (p_para.equalsIgnoreCase("UNIT")) { %>
		             <b>DATA SCRUTINY AT LEVEL-1 (UNIT)</b>
		             
		             <% } %>
		              <% if (p_para.equalsIgnoreCase("FMN")) { %>
		             <b>DATA SCRUTINY AT LEVEL-2 (FMN)</b>
		             
		             <% } %>
              <% if (p_para.equalsIgnoreCase("L1")) { %>
		             <b>DATA SCRUTINY LEVEL-3 (MISO)</b>
		             
		             <% } %>
		              <% if (p_para.equalsIgnoreCase("L2")) { %>
		             <b>DATA SCRUTINY LEVEL-4 (MISO)</b>
		             
		             <% } %>
		      </div> 
		      
		      <div class="card-body card-block">
					<div class="col-md-12 ">							
	               		 <div class="col-md-2" style="text-align: left;">
	                 		  	<label for="text-input" class=" form-control-label">A&D No</label>
	               		 </div>
	               		 <div class="col-md-3">
	               		    <input type="hidden" id=check2 name="check2"> 
	               		        <input type="hidden" id=id_hid name="id_hid">
	               		        <input type="hidden" id=sus2 name="sus2">
	               		        <input type="hidden" id=unit2 name="unit2">
	               		        <input type="hidden" id=cmd2 name="cmd2">
	               		        <input type="hidden" id=frm_dt2 name="frm_dt2">
	               		        <input type="hidden" id=to_dt2 name="to_dt2">
	               		        <input type="hidden" id=stat2 name="stat2">
	               		        <input type="hidden" id=p_diag2 name="p_diag2">
	               		         <input type="hidden" id=icd_remarks_a2 name="icd_remarks_a2">
	               		          <input type="hidden" id=icd_remarks_d2 name="icd_remarks_d2">
	               		        <input type="hidden" id=p_para name="p_para">
	               		        <input type="hidden" id="hd_pass" name="hd_pass"  value="">
	               			    <input type="text" id="and_no" name ="and_no" class="form-control-sm form-control" autocomplete="off" readonly>
						 </div>	 
						 
						 <div class="col-md-2" style="text-align: left;"> 
	               			  <label class=" form-control-label">Patient Name</label>
	             		 </div>
	             		 <div class="col-md-5">
	             			  <input type="text" id="name" name="name" class="form-control-sm form-control" autocomplete="off" readonly>
	               		 </div>
  					 </div>
  					 <div class="col-md-12">
						<div class="col-md-6">
						<div class="row form-group">
						<div class="col-md-4">
						 	<label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Service</label>
							 </div>
						<div class="col-md-8">
							<select id="service" name="service" class="form-control-sm form-control" disabled>
									<option value="-1">--Select--</option>
									<c:forEach var="item" items="${GETSYSSERVICECODE}" varStatus="num">
										<option value="${item}" name="${item}">${item}</option>
									</c:forEach>
								</select>
						</div>
						</div>
						</div>
						</div>
  					 <div class="col-md-12 ">	
  					     <div class="col-md-2" style="text-align: left;"> 
	               			  <label class=" form-control-label">Admission Date</label>
	             		 </div>
	             		 <div class="col-md-4">
	             			  <input type="date" id="ad_dt" name="ad_dt" class="form-control-sm form-control">
	               		 </div>  
	               		 
	               		 <div class="col-md-2" style="text-align: left;"> 
	               			  <label class=" form-control-label">Discharge Date</label>
	             		 </div>
	             		 <div class="col-md-4">
	             			  <input type="date" id="di_dt" name="di_dt" class="form-control-sm form-control">
	               		 </div>
  					 </div>
  					 
  					 <div class="col-md-12">	
  					     <div class="col-md-2" style="text-align: left;"> 
	               			  <label class=" form-control-label">Ward</label>
	             		 </div>
	             		 <div class="col-md-4">
	             			  <input type="text" id="ward" name="ward" class="form-control-sm form-control" autocomplete="off" readonly>
	               		 </div>
	               		 
	               		 <div class="col-md-2" style="text-align: left;"> 
	               			  <label class=" form-control-label">Relation</label>
	             		 </div>
	             		 <div class="col-md-4">
	             			  <select id="relation" name="relation" class="form-control-sm form-control" >
									 <option value="-1">--Select--</option>
									 <c:forEach var="item" items="${ml_1}" varStatus="num" >
									     <option value="${item}" name = "${item}">${item}</option>
									 </c:forEach>
							  </select>
	               		 </div>
  					 </div>
  					 
  					 <div class="col-md-12 ">
  					     <div class="col-md-2" style="text-align: left;"> 
	               			  <label class=" form-control-label">Gender</label>
	             		 </div>
	             		 <div class="col-md-2">
	             			  <select id="gender" name="gender" class="form-control-sm form-control" >
									 <option value="-1">--Select--</option>
									 <c:forEach var="item" items="${ml_2}" varStatus="num" >
									     <option value="${item}" name = "${item}">${item}</option>
									 </c:forEach>
							  </select>
	               		 </div>	
	               		 
	               		 <div class="col-md-2" style="text-align: right;"> 
	               			  <label class=" form-control-label">NBB</label>
	             		 </div>
	             		 <div class="col-md-2">
	             			  <select id="nbb" name="nbb" class="form-control-sm form-control" >
									 <option value="-1">--Select--</option>
									 <c:forEach var="item" items="${ml_3}" varStatus="num" >
									     <option value="${item}" name = "${item}">${item}</option>
									 </c:forEach>
							  </select>
	               		 </div>
	               		 
	               		 <div class="col-md-2" style="text-align: right;"> 
	               			  <label class=" form-control-label">NBB Weight</label>
	             		 </div>
	             		 <div class="col-md-2">
	             			  <input type="text" id="nbb_weight" name="nbb_weight" class="form-control-sm form-control" autocomplete="off" 
	             			  onkeypress="return isNumberPointKey(event);" readonly maxlength="4">
	               		 </div>
  					 </div>
  					 
  					 <div class="col-md-12 ">
  					     <div class="col-md-2" style="text-align: left;"> 
	               			  <label class=" form-control-label">Patient Age Year</label>
	             		 </div>
	             		 <div class="col-md-2">
	             			  <input type="text" id="age_year" name="age_year" class="form-control-sm form-control" autocomplete="off"
	             			  onkeypress="return isNumberPointKey(event);" maxlength="2">
	               		 </div>	
	               		 
	               		 <div class="col-md-2" style="text-align: right;"> 
	               			  <label class=" form-control-label">Age Month</label>
	             		 </div>
	             	  <div class="col-md-2">
	             			  <input type="number" id="age_month" name="age_month" class="form-control-sm form-control" autocomplete="off"
	             			  min="0" max="12" onKeyPress="if(this.value.length==2) return false;" onkeydown="return isNumberPointKey(event);">
	               		 </div> 
	              
	               		 <div class="col-md-2" style="text-align: right;"> 
	               			  <label class=" form-control-label">Age Days</label>
	             		 </div>
	             		 <div class="col-md-2">
	             			  <input type="number" id="age_days" name="age_days" class="form-control-sm form-control" autocomplete="off"
	             			  min="0" max="29" onKeyPress="if(this.value.length==2) return false;" onkeydown="return isNumberPointKey(event);">
	               		 </div> 
	               		
  					 </div>
  					 
  					 <div class="col-md-12 ">
  					     <div class="col-md-2" style="text-align: left;"> 
	               			  <label class=" form-control-label">Personnel No</label>
	             		 </div>
	             		 <div class="col-md-4">
	             			  <input type="text" id="persnl_no" name="persnl_no" class="form-control-sm form-control" autocomplete="off" >
	               		 </div>
	               		 
	               		 <div class="col-md-2" style="text-align: left;"> 
	               			  <label class=" form-control-label">Personnel Name</label>
	             		 </div>
	             		 <div class="col-md-4">
	             			  <input type="text" id="persnl_name" name="persnl_name" class="form-control-sm form-control" autocomplete="off" readonly>
	               		 </div>
  					 </div>
  					 
  					 <div class="col-md-12 ">
  					     <div class="col-md-2" style="text-align: left;"> 
	               			  <label class=" form-control-label">Personnel Age Year</label>
	             		 </div>
	             		 <div class="col-md-2">
	             			  <input type="text" id="persnl_age_year" name="persnl_age_year" class="form-control-sm form-control" autocomplete="off" 
	             			  onkeypress="return isNumberPointKey(event);" maxlength="2">
	               		 </div>	
	               		 
	               		 <div class="col-md-2" style="text-align:right;"> 
	               			  <label class=" form-control-label">Age Month</label>
	             		 </div>
	             		 <div class="col-md-2">
	             			  <input type="text" id="persnl_age_month" name="persnl_age_month" class="form-control-sm form-control" autocomplete="off" 
	             			>
	               		 </div>
  					 </div>
  					 
  					 <div class="col-md-12 ">
				        <div class="col-md-2" >
						     <label for="text-input" class=" form-control-label">Category</label>
					    </div>
					    <div class="col-md-4">
							 <select name="category" id="category" class="form-control-sm form-control">
								 <option value="-1">--Select--</option>
								 <c:forEach var="item" items="${ml_4}" varStatus="num">
									 <option value="${item}" name="${item}">${item}</option>
								 </c:forEach>
							 </select>
					    </div>
					    
					    <div class="col-md-2" >
						     <label for="text-input" class=" form-control-label">Rank</label>
					    </div>
					    
					    <div class="col-md-4">
							 <select id="rank" name="rank" class="form-control-sm form-control" >
									 <option value="-1">--Select--</option>
									 <c:forEach var="item" items="${ml_8}" varStatus="num" >
									     <option value="${item}" name = "${item}">${item}</option>
									 </c:forEach>
							  </select>
					    </div>
				     </div>
				     
				     <div class="col-md-12 ">
  					     <div class="col-md-2" style="text-align: left;"> 
	               			  <label class=" form-control-label">Service Years</label>
	             		 </div>
	             		 <div class="col-md-2">
	             			  <input type="text" id="service_years" name="service_years" class="form-control-sm form-control" autocomplete="off" >
	               		 </div>	
	               		 
	               		 <div class="col-md-2" style="text-align: right;"> 
	               			  <label class=" form-control-label">Service Months</label>
	             		 </div>
	             		 <div class="col-md-2">
	             			  <input type="text" id="service_months" name="service_months" class="form-control-sm form-control" autocomplete="off" >
	               		 </div>
	               		 
	               		 <div class="col-md-2" style="text-align: right;"> 
	               			  <label class=" form-control-label">Personnel Gender</label>
	             		 </div>
	             		 <div class="col-md-2">
	             			  <select id="persnl_sex" name="persnl_sex" class="form-control-sm form-control" >
									 <option value="-1">--Select--</option>
									 <c:forEach var="item" items="${ml_2}" varStatus="num" >
									     <option value="${item}" name = "${item}">${item}</option>
									 </c:forEach>
							  </select>
	               		 </div>
  					 </div>
  					 
  					 <div class="col-md-12 ">
  					    <div class="col-md-2" style="text-align: left;">
					         <label for="text-input" class=" form-control-label">Marital Status</label>
					    </div> 
					    <div class="col-md-4">
					          <select id="marital_status" name="marital_status" class="form-control-sm form-control" >
									 <option value="-1">--Select--</option>
									 <c:forEach var="item" items="${ml_5}" varStatus="num" >
									     <option value="${item}" name = "${item}">${item}</option>
									 </c:forEach>
							  </select>
					    </div>
					    
					    <div class="col-md-2" style="text-align: left;">
					         <label for="text-input" class=" form-control-label">Personnel Marital Status</label>
					    </div> 
					    <div class="col-md-4">
					          <select id="persnl_marital_status" name="persnl_marital_status" class="form-control-sm form-control" >
									 <option value="-1">--Select--</option>
									 <c:forEach var="item" items="${ml_5}" varStatus="num" >
									     <option value="${item}" name = "${item}">${item}</option>
									 </c:forEach>
							  </select>
					    </div>
  					 </div>
  					 <div class="col-md-12"  style="display: none;" id="nok">
  					    <div class="col-md-2" >
					         <label for="text-input" class=" form-control-label">NOK Name</label>
					    </div> 
					    <div class="col-md-4">
					          <input type="text" id="nok_name" name="nok_name" class="form-control-sm form-control" autocomplete="off" >
					    </div>
					    
					    <div class="col-md-2" style="text-align: left;">
					         <label for="text-input" class=" form-control-label">NOK Relation</label>
					    </div> 
					    <div class="col-md-4">
					         <input type="text" id="nok_relation" name="nok_relation" class="form-control-sm form-control" autocomplete="off" >
					    </div>
  					 </div>
  					 <div class="col-md-12 ">
  					    <div class="col-md-2" style="text-align: left;">
					         <label for="text-input" class=" form-control-label">Adm ICD Code</label>
					    </div> 
					    <div class="col-md-4">
					          <input type="text" id="diagnosis_code1a" name="diagnosis_code1a" class="form-control-sm form-control" autocomplete="off">
					    </div>
					    
					    <div class="col-md-2" style="text-align: left;">
					         <label for="text-input" class=" form-control-label">Adm ICD Cause</label>
					    </div> 
					    <div class="col-md-4">
					          <input type="text" id="icd_cause_code1a" name="icd_cause_code1a" class="form-control-sm form-control" autocomplete="off" 
					          placeholder="Search..." readonly>
					    </div> 
  					 </div>
  					 
  					 <div class="col-md-12 ">
  					    <div class="col-md-2" style="text-align: left;">
					         <label for="text-input" class=" form-control-label">Adm Diagnosis</label>
					    </div> 
					    <div class="col-md-10">
					          <textarea  id="icd_remarks_a" name="icd_remarks_a" class="form-control-sm form-control" readonly autocomplete="off"></textarea>
					    </div>
  					 </div>
  					 
  					 <div class="col-md-12 ">
  					     <div class="col-md-2" style="text-align: left;">
					         <label for="text-input" class=" form-control-label">Discharge Diagnosis</label>
					     </div> 
					     <div class="col-md-10">
					          <textarea  id="icd_remarks_d" name="icd_remarks_d" class="form-control-sm form-control" readonly autocomplete="off"></textarea>
					     </div>
  					 </div>
  					  
  					 <div class="col-md-12 ">
  					     <div class="col-md-2" style="text-align: left;">
					         <label for="text-input" class=" form-control-label">Discharge Remarks</label>
					     </div> 
					     <div class="col-md-10">
					          <textarea  id="discharge_remarks" name="discharge_remarks" class="form-control-sm form-control" readonly autocomplete="off"></textarea>
					     </div>
  					 </div>
  					
  					 <div class="col-md-12 ">
  					    <div class="col-md-2" style="text-align: left;">
					         <label for="text-input" class=" form-control-label">Admission Type</label>
					    </div> 
					    <div class="col-md-4">
					          <select id="admsn_type" name="admsn_type" class="form-control-sm form-control" >
									 <option value="-1">--Select--</option>
									 <c:forEach var="item" items="${ml_6}" varStatus="num" >
									     <option value="${item}" name = "${item}">${item}</option>
									 </c:forEach>
							  </select>
					    </div>
					    
					    <div class="col-md-2" style="text-align: left;">
					         <label for="text-input" class=" form-control-label">Disposal</label>
					    </div> 
					    <div class="col-md-4">
					          <select id="disposal" name="disposal" class="form-control-sm form-control" >
									 <option value="-1">--Select--</option>
									 <c:forEach var="item" items="${ml_7}" varStatus="num" >
									     <option value="${item}" name = "${item}">${item}</option>
									 </c:forEach>
							  </select>
					    </div>
  					 </div>
  					 
  					 <div class="col-md-12 ">
                        <div class="col-md-2" style="text-align: left;">
							  <label class="form-control-label">Discharge ICD Code</label>
					    </div>
						<div class="col-md-4">
							  <input type="text" name="diagnosis_code1d" id="diagnosis_code1d" class="form-control-sm form-control" autocomplete="off" 
							  placeholder="Search...">
						</div>
                          
						<div class="col-md-2" style="text-align: left;">
							  <label for="text-input" class="form-control-label">Discharge ICD Cause</label>
						</div>
						<div class="col-md-4">
							<input type="text" name="icd_cause_code1d" id="icd_cause_code1d" class="form-control-sm form-control" autocomplete="off" 
							placeholder="Search..." readonly>			
						</div>		
					 </div>		
  				
				</div>		
				
				
             	<div class="card-footer" align="center" >				

			    	  <c:if test="${para2 == 'UNIT'}">
			<!-- <a href="mnh_scrutiny_unit_approval" class="btn btn-danger btn-sm" id="btn_cancl" >Cancel</a> -->
			    		
			    		<input type="button"  class="btn btn-danger btn-sm"  id="btn_cancl" onclick="Cancel();" value="Cancel"> 
			    	  </c:if>
			    		 <c:if test="${para2 == 'L1'}">
			    		<!-- <a href="mnh_level1_approval" class="btn btn-danger btn-sm" id="btn_cancl" >Cancel</a> -->
			    	
			    			<input type="button"  class="btn btn-danger btn-sm" id="btn_cancl"  onclick="Cancel();" value="Cancel" > 
			    		 </c:if>
			    		 <c:if test="${para2 == 'L2'}">
			    	<!-- 	<a href="mnh_level2_approval" class="btn btn-danger btn-sm" id="btn_cancl" >Cancel</a> -->
			    		<input type="button"  class="btn btn-danger btn-sm" id="btn_cancl" onclick="Cancel();" value="Cancel"> 
			    		 </c:if>
			    <input type="submit" class="btn btn-success btn-sm" id="btn_update" value="Update"    onclick="return validate();"  >
                 <c:if test="${para2 == 'L1'}">
                 <button type="button" class="btn btn-primary btn-sm" value="Approve Later" onclick="validatepass();" style="position: absolute; right: 0;margin-right: 5px; ">Approve Later</button>
                 
                 </c:if>
                  
             	</div>	
			</div>
		  </div>
	 </div>
</form:form>

<c:url value="DSunitList" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="sus1">
	<input type="hidden" name="sus1" id="sus1">
	<input type="hidden" name="unit1" id="unit1">
	<input type="hidden" name="cmd1" id="cmd1"> 
	<input type="hidden" name="frm_dt1" id="frm_dt1">
	<input type="hidden" name="to_dt1" id="to_dt1">
	<input type="hidden" name="stat1" id="stat1">
	<input type="hidden" name="para1" id="para1">
	<input type="hidden" name="p_diag1" id="p_diag1">
	<input type="hidden" name="icd_remarks_a1" id="icd_remarks_a1">
	<input type="hidden" name="icd_remarks_d1" id="icd_remarks_d1">
</form:form>

<script>

function Cancel(){
  	
	$("#sus1").val($("#sus2").val()) ;
	$("#unit1").val($("#unit2").val()) ;
	$("#cmd1").val($("#cmd2").val()) ;
	$("#frm_dt1").val($("#frm_dt2").val()) ;
	$("#to_dt1").val($("#to_dt2").val()) ;
	$("#stat1").val($("#stat2").val()) ;
	$("#para1").val($("#p_para").val()) ;
	$("#p_diag1").val($("#p_diag2").val()) ;
	$("#icd_remarks_a1").val($("#icd_remarks_a2").val()) ;
	$("#icd_remarks_d1").val($("#icd_remarks_d2").val()) ;
	
	
	
	document.getElementById('searchForm').submit();
}
function btn_clc(){
	location.reload(true);
}

function isNumberPointKey(evt){
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

function twoLenghtNumberKey(evt,a){
	
	if(parseInt(a.length) == 2){
		return false;
	}
	
}

function validatepass(){

	if(validate())
	{
		$("#hd_pass").val("test");
		$("#scrutiny_edit_ds").submit();
		
	}
	
}


function validate(){
	
	 if(confirm("Are you sure you want to update?")){
		}
		else{
			return false;
		}
	var d = new Date();
	var c_d = d.getFullYear()+"-"+("0" + (d.getMonth()+1)).slice(-2)+"-"+("0" + d.getDate()).slice(-2);
	var rel = $("#relation").val();
	var an = '${list[0].and_no}'.split("/");
	var b = an[0].substring(0,2);
	var an = '${list[0].and_no}'.split("/");
	var c = an[0].substring(0,2);
	var and_no = $("#and_no").val();
	var cat = $("#category").val();
	var age = $("#persnl_age_year").val();
	var serv = $("#service_years").val();
	var disposal = $("#disposal").val();
	var b_1 = $("#diagnosis_code1d").val().split("-");
	var b_2 = b_1[0].substring(0,1).toUpperCase();
	var d_code = $("#diagnosis_code1d").val().split("-");
	var d_code_p = $("#diagnosis_code1d").val().substring(0,3).toUpperCase();
	var a_code_p = $("#diagnosis_code1a").val().substring(0,3);
	$("#hd_pass").val("");
	
	 if ($("#ad_dt").val() == "") {
		alert("Please Select Admission Date");
		$("#ad_dt").focus();
		return false;
	}
	 if($("#ad_dt").val() > c_d){	
		alert("Can't select the Future Admission Date");
		$("#ad_dt").focus();
		return false;
	}
	 if ($("#di_dt").val() == "") {
		alert("Please Select Discharge Date");
		$("#di_dt").focus();
		return false;
	
	}
	 if($("#di_dt").val() > c_d){
		alert("Can't select the Future Discharge Date");
		$("#di_dt").focus();
		return false;
	}

/* 	 if(parseInt($("#ad_dt").val()) > parseInt($("#di_dt").val())){
		
		alert("Discharge Date is greater than or equal Admission Date Require");
		$("#di_dt").focus();
		return false;
	} */
	 
	 if(($("#ad_dt").val()) > ($("#di_dt").val())){
			
			alert("Discharge Date is greater than or equal Admission Date Require");
			$("#di_dt").focus();
			return false;
		}
	if ((rel == "WIFEOF" || rel == "MOTHEROF" || rel == "DAUGHTEROF" || rel == "SISTEROF") && ($("#gender").val() != "FEMALE")) {
		alert("Gender should be Female Here");
		$("#gender").focus();
		return false;
	}
	if ((rel == "BROTHEROF" || rel == "HUSBANDOF" || rel == "FATHEROF" || rel == "SONOF") && ($("#gender").val() != "MALE")) {
		alert("Gender should be Male Here");
		$("#gender").focus();
		return false;
	}
	if((rel != "SONOF" && rel != "DAUGHTEROF") && ($("#nbb").val() == "Y")){
		alert("NBB Value is Y here. So Relation Should be SONOF or DAUGHTEROF Allowed Here");
		$("#relation").focus();
		return false;
	} 
	 if($("#nbb").val() == "Y" && $("#nbb_weight").val().substring(0,1) == "0"){
		alert("NBB Weight Zero Not Allowed");
		$("#nbb_weight").focus();
		return false;
	}
	 
	 if($("#nbb").val() == "Y" && $("#nbb_weight").val() == ""){
			alert("Please Enter NBB Weight");
			$("#nbb_weight").focus();
			return false;
		}
	 if ($("#age_year").val() == "0" &&  $("#age_month").val() == "0" &&  $("#age_days").val() == "0") {
		alert("Patient Age Year,Age Month and Age Days 0 Not Allowed");
		$("#age_year").focus();
		return false;
	} 
   if($("#nbb").val() == "Y" && $("#age_year").val()!= "0")
  {
			alert("NBB Age Only in Days Allowed");
			$("#age_year").focus();
			return false;
		
   }
	 if ($("#persnl_age_year").val() == "0" && $("#persnl_age_month").val() == "0") {
		alert("Personnel Age Year and Personnel Age month  0 Not Allowed");
		$("#persnl_age_year").focus();
		return false;
	} 
	 if ($("#persnl_no").val() == "" ) {
			alert("Please Enter Personnel No");
			$("#persnl_no").focus();
			return false;
		} 
	 
	//Added on 24-09-20 according to thier requirement 
	  
	
   if(c == "NE" && rel == "SELF")
	{
	
	}
	else
	{
  
		if($("#persnl_age_year").val() != "" && $("#persnl_age_year").val() < 17 ) 
		{
		alert("Personnel Age Year Not Allowed Below 17");
		$("#persnl_age_year").focus();
		return false;
				
		}
	} 
	
  
	 if(parseInt(age) <= parseInt(serv)){
		alert("Personnel Age Should be greater than Service");
		$("#persnl_age_year").focus();
		return false;	
	} 
	 if(cat == "-1"){
			alert("Please Select Category");
			$("#category").focus();
			return false;
		}
	 if(rel == "SELF" && ($("#age_year").val() != $("#persnl_age_year").val())){
		alert("Patient & Personnel Age Should be Same in Relation Self");
		$("#persnl_age_year").focus();
		return false;	
	}
	
	 if((rel == "MOTHEROF" || rel == "FATHEROF") && ($("#age_year").val() <= $("#persnl_age_year").val())){
		alert("In Relation MOTHEROF or FATHEROF, Patient age should be greater than Personnel Age Require");
		$("#age_year").focus();
		return false;
	}
	 if(rel == "SELF" && $("#gender").val() == "FEMALE" && (cat != "MNS" && cat != "OFFICER") && b == "AR"){
		alert("Category should be MNS or OFFICER Here");
		$("#category").focus();
		return false;
	}
	 
	 if(rel == "SELF" && $("#persnl_age_year").val() > "60" && b == "AR"){
			alert("Personnel Age Not Above 60");
			$("#persnl_age_year").focus();
			return false;
		}
	 if(cat != "" && $("#rank").val() == "-1" && (b == "AR" || b == "XR" || b == "DS")){
	    	alert("Mention A&D No  Enter Rank Here ");
	  		$("#rank").focus();
	  		return false;
		}
	 if(rel == "SELF" && $("#gender").val() == "MALE" && (cat == "MNS")){
		alert("Category should not be MNS Here");
		$("#category").focus();
		return false;
	}
	if((cat == "JCO" || cat == "OR") && $("#persnl_sex").val() != "MALE"){
		alert("Personnel Gender Should be MALE Here");
		$("#persnl_sex").focus();
		return false;
	}
	if(rel == "SELF" && ($("#marital_status").val() != $("#persnl_marital_status").val())){
		alert("In SELF Relation, Marital & Personnel Marital Status Should be Same Require");
		$("#marital_status").focus();
		return false;
	}
	 if(rel != "SELF" && $("#disposal").val() == "DSCHRGUNIT"){
		alert("Other than SELF Relation, Disposal should Not be Accepted DSCHRGUNIT");
		$("#disposal").focus();
		return false;
	}
	 if(rel != "SELF" && $("#disposal").val() == "INVALIDMNT"){
			alert("Other than SELF Relation, Disposal should Not be Accepted INVALIDMNT");
			$("#disposal").focus();
			return false;
		}
	 if ($("#diagnosis_code1a").val() == "") {
		alert("Please Enter Adm ICD Code");
		$("#diagnosis_code1a").focus();
		return false;
	}
	 if ($("#disposal").val() == "-1") {
		alert("Please Select Disposal");
		$("#disposal").focus();
		return false;
	}
	 if((disposal == "DEATH" || disposal == "FOUNDDEAD" || disposal == "S/BIRTH") && (d_code_p == "Z09")){
		alert("Discharge ICD Z09 is not Allowed for DEATH or FOUNDDEAD or S/BIRTH");
		$("#diagnosis_code1d").focus();
		return false;
	}
	
	 if (d_code_p == "AAA") {
		alert("Entered ICD Code value not Allowed");
		$("#diagnosis_code1d").focus();
		return false;
	}

	 if ((d_code_p == "O80" || d_code_p == "O82") && (rel != "WIFEOF" && rel != "SELF") && (cat != "MNS" && cat != "OFFICER")){
		alert("Mentioned ICD Code Allowed For Relation WIFEOF or SELF Only and category should be MNS or OFFICER");
		$("#diagnosis_code1d").focus();
		return false;
	}

	 if ((d_code_p == "Z37" || d_code_p == "Z38") && (rel != "SONOF" && rel != "DAUGHTEROF" && $("#nbb").val() != "Y")) {
		alert("Mentioned ICD Code Allowed For Relation SONOF or DAUGHTEROF Only and NBB Value is Y here ");
		$("#diagnosis_code1d").focus();
		return false;
	}
	 if ((d_code_p.substring(0,1) == "O") && (rel != "WIFEOF" && rel != "MOTHEROF" && rel != "DAUGHTEROF" && rel != "SISTEROF" && rel != "SELF")) {
		alert("Mentioned ICD Code Allowed For Relation WIFEOF or MOTHEROF or DAUGHTEROF or SISTEROF or SELF Only");
		$("#diagnosis_code1d").focus();
		return false;
	}
	 if ((d_code_p.substring(0,1) == "O") && (rel == "SELF") && (cat == "JCO" || cat == "OR")) {
		alert("Category JCO or OR is not Allowed under Mentioned ICD Code");
		$("#diagnosis_code1d").focus();
		return false;
	}
	 if ((d_code_p.substring(0,1) == "P") && (rel != "SONOF" && rel != "DAUGHTEROF")) {
		alert("Mentioned ICD Code Allowed For Relation SONOF or DAUGHTEROF Only");
		$("#diagnosis_code1d").focus();
		return false;
	}  
	 //070395
	
/* 	
	 if ($("#diagnosis_code1d").val() == "") {
			alert("Please Enter Diagnosis ICD Code");
			$("#diagnosis_code1d").focus();
			return false;
		}
	 	  */
	 	  
	  if ((b_2 == "S" || b_2 == "T" || b_2 == "X" || b_2 == "V" || b_2 == "W" || b_2 == "Y")  
      && ($("#icd_cause_code1d").val() == "")) {
		alert("Please Enter ICD Cause");
		$("#icd_cause_code1d").focus();
		return false;
	} 
	 
 	 return true;  
	
}

 $("#age_month").keyup(function(){
	var a = this.value;
	var min = 0; 
    var max = 12;
    if(a < min && a != "")
    	$("#age_month").val(min);
    if(a > max)
    	$("#age_month").val(max);
});

$("#age_days").keyup(function(){
	var a = this.value;
	var min = 0; 
    var max = 29;
    if(a == 0 && a != ""){
    	$("#age_days").val(min);
    }
    if(a > max){
    	$("#age_days").val(max);
    }   	
}); 

 $("#persnl_age_month").keyup(function(){
	var a = this.value;
	var min = 0; 
    var max = 12;
    if(a < min && a != "")
    	$("#persnl_age_month").val(min);
    if(a > max)
    	$("#persnl_age_month").val(max);
}); 

$("#diagnosis_code1d").keyup(function(){
	var code = this.value;
	$().Autocomplete2('GET','getMNHDistinctICDList',diagnosis_code1d,{a:code,b:"ALL"},'','','','','');
});

$("#diagnosis_code1a").keyup(function(){
	var code = this.value;
	$().Autocomplete2('GET','getMNHDistinctICDList',diagnosis_code1a,{a:code,b:"ALL"},'','','','','');
});
</script>

<!-- for On Load Methods -->
<script>
$(document).ready(function() {
	if('${size}' != 0){
		
		var an = '${list[0].and_no}'.split("/");
		var b = an[0].substring(0,2);
	
		if(b == "AR" || b == "XR" || b == "XE" || b == "DA" || b == "RR" ||
				b == "DS" || b == "MC" || b == "MA"){

			$("#service").val("ARMY");
	
		}
		if(b == "AF"|| b == "XF" || b == "XH"){

			$("#service").val("AIRFORCE");
	
		}
		if(b == "IN" || b == "XN" || b == "XS" ){

			$("#service").val("NAVY");
	
		}
		if(b == "M1" || b == "M2" || b == "M3" || b == "C4" || b == "UE" || b == "FN" ||
				b == "CR" ||  b == "CI" || b == "IT" || b == "NC" || b == "MS" || b == "N4"){

			$("#service").val("OTHERS");
		}
	
		if('${para2}' == "UNIT" || '${para2}' == "UNIT"){
			$("#btn_update").val('Approve');
		}else{
			$("#btn_update").val('Update');
		}
		$("#id_hid").val('${id2}');
		$("#p_para").val('${para2}');
		$("#check2").val('${check2}');
		
		if('${para2}' == "AC" && '${check2}'== "A68"){

			$("#and_no").attr('readonly',false);
		}
		if('${para2}' == "AC" && '${check2}'== "A10"){

			$("#ward").attr('readonly',false);
		}
		if('${para2}' == "AC" && '${check2}'== "A11"){

			$("#ward").attr('readonly',false);
		}
		if('${para2}' == "AC" && '${check2}'== "A12"){

			$("#nok").show();
		} 
		$("#sus2").val('${sus2}');
		$("#unit2").val('${unit2}');
		$("#cmd2").val('${cmd2}');
		$("#frm_dt2").val('${frm_dt2}');
		$("#to_dt2").val('${to_dt2}');
		$("#stat2").val('${stat2}');
		$("#p_diag2").val('${p_diag2}');
		$("#icd_remarks_a2").val('${icd_remarks_a2}');
		$("#icd_remarks_d2").val('${icd_remarks_d2}');
		
		$("#nok_name").val('${list[0].nok_name}');
		$("#nok_relation").val('${list[0].nok_relation}');
		$("#and_no").val('${list[0].and_no}');
		
		var admsn_date = '${list[0].admsn_date}';
		admsn_date =admsn_date.substring(0, 10);
		$("#ad_dt").val(admsn_date);
		var dschrg_date = '${list[0].dschrg_date}';
		dschrg_date =dschrg_date.substring(0, 10);
		$("#di_dt").val(dschrg_date);
		$("#ward").val('${list[0].ward}/${list[0].discharge_ward}');
		$("#name").val('${list[0].name}');
		
		if('${list[0].relationship}' == ""){
			$("#relation").val('-1');
		}else{
			$("#relation").val('${list[0].relationship}');
		}
		
		if('${list[0].sex}' == "M"){
			$("#gender").val('MALE');
		}
		if('${list[0].sex}' == "F"){
			$("#gender").val('FEMALE');
		}
		if('${list[0].sex}' == "T"){
			$("#gender").val('TRANSGENDER');
		}
		
		if('${list[0].nbb}' == ""){
			$("#nbb").val('-1');
			$("#nbb_weight").val('2750');
		}else{
			if('${list[0].nbb}' == "Y"){
				$("#nbb_weight").attr('readonly',false);
				$("#nbb_weight").val('${list[0].nbb_weight}');
			}
			if('${list[0].nbb}' == "N"){
				$("#nbb_weight").val('0');
			}
			$("#nbb").val('${list[0].nbb}');
		}
		
		if('${list[0].age_year}' == ""){
			$("#age_year").val('0');
		}else{
			$("#age_year").val('${list[0].age_year}');
		}
		
	 	if('${list[0].age_month}' == ""){
			$("#age_month").val('0');
		}else{
			$("#age_month").val('${list[0].age_month}');
		} 
		
		$("#age_days").val('${list[0].age_days}');
		$("#persnl_no").val('${list[0].persnl_no}');
		$("#persnl_name").val('${list[0].persnl_name}');
		$("#persnl_age_year").val('${list[0].persnl_age_year}');
		
	 	if('${list[0].persnl_age_month}' == ""){
			$("#persnl_age_month").val('0');
		}else{
			$("#persnl_age_month").val('${list[0].persnl_age_month}');
		} 
		
		if('${list[0].category}' == ""){
			$("#category").val('-1');
		}else{
			$("#category").val('${list[0].category}');
		}
		
		$("#rank").val('${list[0].rank}');
		
		if('${list[0].service_years}' == ""){
			$("#service_years").val('0');
		}else{
			$("#service_years").val('${list[0].service_years}');
		}
		
		if('${list[0].service_months}' == ""){
			$("#service_months").val('0');
		}else{
			$("#service_months").val('${list[0].service_months}');
		}
		
		if('${list[0].persnl_sex}' == "M"){
			$("#persnl_sex").val('MALE');
		}
		if('${list[0].persnl_sex}' == "F"){
			$("#persnl_sex").val('FEMALE');
		}
		if('${list[0].persnl_sex}' == "T"){
			$("#persnl_sex").val('TRANSGENDER');
		}
		
		if('${list[0].marital_status}' == ""){
			$("#marital_status").val('-1');
		}else{
			$("#marital_status").val('${list[0].marital_status}');
		}
		
		if('${list[0].persnl_marital_status}' == ""){
			$("#persnl_marital_status").val('-1');
		}else{
			$("#persnl_marital_status").val('${list[0].persnl_marital_status}');
		}
	
	if('${list[0].diagnosis_code1a}' == 'Z37.0')
	{
		$("#diagnosis_code1a").val('Z38.0');
		
	}
	else
	{
		if('${list[0].diagnosis_code1a}' != ""){
			$.post("getMNHICDCodeToCauseVi?"+key+"="+value,{a:'${list[0].diagnosis_code1a}',b:"2"},function(j) {
			
				if(j != ""){
					var enc = j[j.length-1].substring(0,16);
					var a = dec(enc,j[0]);
					var data = '${list[0].diagnosis_code1a}' + "-" + a;
				
					$("#diagnosis_code1a").val(data);
					
				}else{
					
					$("#diagnosis_code1a").val('');
				}	
			});
		}
	}
		
		
		
		var a_1 = '${list[0].diagnosis_code1a}'.split("-");
		var a_2 = a_1[0].substring(0,1).toUpperCase();
		
		if(a_2 == "S" || a_2 == "T" || a_2 == "V" || a_2 == "W" || a_2 == "X" || a_2 == "Y"){
			$("#icd_cause_code1a").attr('readonly',false);
			if('${list[0].icd_cause_code1a}' != ""){
				$.post("getMNHICDCodeToCauseVi?"+key+"="+value,{a:'${list[0].icd_cause_code1a}',b:"2"},function(j) {
					if(j != ""){
						
						var enc = j[j.length-1].substring(0,16);
						var a = dec(enc,j[0]);
						var data = '${list[0].icd_cause_code1a}' + "-" + a;
						$("#icd_cause_code1a").val(data);
					}else{
	
						$("#icd_cause_code1a").val('');
					}
				});
			}
			
			
			$("#icd_cause_code1a").keyup(function(){
				var code = this.value;
				$().Autocomplete2('GET','getMNHDistinctICDList',icd_cause_code1a,{a:code,b:"ALL2"},'','','','','');
			});	
		}
		
	    if('${list[0].diagnosis_code1d}' == 'Z37.0')
	    {
		
	    	$("#diagnosis_code1d").val('Z38.0');
		
	    }
		else
		{
			if('${list[0].diagnosis_code1d}' != ""){
				$.post("getMNHICDCodeToCauseVi?"+key+"="+value,{a:'${list[0].diagnosis_code1d}',b:"2"},function(j) {
				
					if(j != ""){
					
						var enc = j[j.length-1].substring(0,16);
						var a = dec(enc,j[0]);
						var data = '${list[0].diagnosis_code1d}' + "-" + a;
						
						$("#diagnosis_code1d").val(data);
						
					}else{
						$("#diagnosis_code1d").val('');
					}	
				});
			}
	}
		
		var b_1 = '${list[0].diagnosis_code1d}'.split("-");
		var b_2 = b_1[0].substring(0,1).toUpperCase();
		
		if(b_2 == "S" || b_2 == "T" || b_2 == "V" || b_2 == "W" || b_2 == "X" || b_2 == "Y"){
			$("#icd_cause_code1d").attr('readonly',false);
	
			if('${list[0].icd_cause_code1d}' != ""){
				$.post("getMNHICDCodeToCauseVi?"+key+"="+value,{a:'${list[0].icd_cause_code1d}',b:"2"},function(j) {
					if(j != ""){
						var enc = j[j.length-1].substring(0,16);
						var a = dec(enc,j[0]);
						var data = '${list[0].icd_cause_code1d}' + "-" + a;
						$("#icd_cause_code1d").val(data);
					}else{
					
						$("#icd_cause_code1d").val('');
					}
				});
			}
			
			
			$("#icd_cause_code1d").keyup(function(){
				var code = this.value;
				$().Autocomplete2('GET','getMNHDistinctICDList',icd_cause_code1d,{a:code,b:"ALL2"},'','','','','');
			});	
		}
		
		$("#icd_remarks_a").val('${list[0].icd_remarks_a}');
		$("#icd_remarks_a").css('color','red');
		$("#icd_remarks_d").val('${list[0].icd_remarks_d}');
		$("#icd_remarks_d").css('color','red');
		$("#discharge_remarks").val('${list[0].discharge_remarks}');
		$("#discharge_remarks").css('color','red');
		
		if('${list[0].admsn_type}' == ""){
			$("#admsn_type").val('-1');
		}else{
			$("#admsn_type").val('${list[0].admsn_type}');
		}
		
		if('${list[0].disposal}' == ""){
			$("#disposal").val('-1');
		}else{
			$("#disposal").val('${list[0].disposal}');
		}
	
	} 
	
	$("#relation").change(function(){
		var a = this.value; 
		
		if(a == "WIFEOF" || a == "MOTHEROF" || a == "DAUGHTEROF" || a == "SISTEROF"){
			$("#gender").val('FEMALE');
		}
		
		if(a == "BROTHEROF" || a == "HUSBANDOF" || a == "FATHEROF" || a == "SONOF" || a == "PSO"){
			$("#gender").val('MALE');
		}	
	});
	
	
	$("#nbb").change(function(){
		var a = this.value; 
		
		if(a == "N" || a == "-1"){
			$("#nbb_weight").attr('readonly',true);
			$("#nbb_weight").val('0');
			
			 $("#diagnosis_code1d").attr('readonly',false);
			 $("#diagnosis_code1d").val('');
			 
			  if('${list[0].diagnosis_code1d}' != ""){
					$.post("getMNHICDCodeToCauseVi?"+key+"="+value,{a:'${list[0].diagnosis_code1d}',b:"2"},function(j) {
					
						if(j != ""){
						
							var enc = j[j.length-1].substring(0,16);
							var a = dec(enc,j[0]);
							var data = '${list[0].diagnosis_code1d}' + "-" + a;
							
							$("#diagnosis_code1d").val(data);
							
						}else{
							$("#diagnosis_code1d").val('');
						}	
					});
				} 
			  
		}
		if(a == "Y"){
			$("#nbb_weight").attr('readonly',false);
			$("#nbb_weight").val('2750');
			
			$("#diagnosis_code1d").val('Z38.0');
			 $("#diagnosis_code1d").attr('readonly',true);

		}
	});
		
	$("#admsn_type").change(function(){
		var a = this.value; 
		
		if(a == "FOUNDDEAD"){
			$("#disposal").val('FOUNDDEAD');
		}
			
	});
	$("#disposal").change(function(){
		var a = this.value; 
		
		if(a == "FOUNDDEAD"){
			$("#admsn_type").val('FOUNDDEAD');
			$("#admsn_type").attr('readonly',true);
		}
			
	});
	

	$("#diagnosis_code1d").change(function(){
		var a = this.value.split("-");
		var b = a[0].substring(0,1).toUpperCase();
		
		if(b == "S" || b == "T" || b == "V" || b == "W" || b == "X" || b == "Y"){
			$("#icd_cause_code1d").attr('readonly',false);
			
			$("#icd_cause_code1d").keyup(function(){
				var code = this.value;
		
				$().Autocomplete2('GET','getMNHDistinctICDList',icd_cause_code1d,{a:code,b:"ALL2"},'','','','','');
			});
		}else{
			$("#icd_cause_code1d").attr('readonly',true);
			$("#icd_cause_code1d").val('');
		}
	});
	
	$("#diagnosis_code1a").change(function(){
		var a = this.value.split("-");
		var b = a[0].substring(0,1).toUpperCase();
		
		if(b == "S" || b == "T" || b == "V" || b == "W" || b == "X" || b == "Y"){
			$("#icd_cause_code1a").attr('readonly',false);
			
			$("#icd_cause_code1a").keyup(function(){
				var code = this.value;
				$().Autocomplete2('GET','getMNHDistinctICDList',icd_cause_code1a,{a:code,b:"ALL2"},'','','','','');
			});
		}else{
			$("#icd_cause_code1a").attr('readonly',true);
			$("#icd_cause_code1a").val('');
		}
	});
	
	$("#category").change(function(){
		var b = this.value;
		$.post("getMedDistinctRankList?"+key+"="+value,{enc:"1",a:'${ser}',b:b},function(j) {
			if(j.length <= 0 || j == null || j == ''){
	 			alert("No Search Result Found");
	 			$("#category").focus();
	 		}else{
	 			var options = '<option value="' + "-1" + '">'+ "--Select--" + '</option>';
	 			
	 			var a = [];
				var enc = j[j.length-1].substring(0,16);
	 			for(var i = 0; i < j.length; i++){
					a[i] = dec(enc,j[i]);
	            }
	 			
				for(var i = 0; i < a.length-1; i++){
					options += '<option value="'+a[i]+'" name="' + a[i]+ '" >'+ a[i]+ '</option>';
				}  
				
				$("select#rank").html(options);
				if(b == '${list[0].category}'){
					$("select#rank").val('${list[0].rank}'); 
				}
	 		}
			
		});
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