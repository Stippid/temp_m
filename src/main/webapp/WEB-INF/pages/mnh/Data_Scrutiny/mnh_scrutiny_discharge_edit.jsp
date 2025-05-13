<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
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

<form:form action="discharge_edit_dsAction" method="post" id="discharge_edit_ds" class="form-horizontal" commandName="discharge_edit_dsCMD">
   <div class="">
      <div class="container" align="center">
          <div class="card">
              <div class="card-header mnh-card-header">
		           <b>EDIT DISCHARGE DETAILS</b>
		      </div> 
		      
		      <div class="card-body card-block">
				   <div class="col-md-12 row form-group">
                        <div class="col-md-3" style="text-align: left;">
							  <label class="form-control-label"><strong style="color: red;">* </strong>Discharge Diagnosis</label>
					    </div>
						<div class="col-md-9">
						      <input type="hidden" id=id_hid name="id_hid">
							  <input type="text" name="diagnosis_code1d" id="diagnosis_code1d" class="form-control-sm form-control" autocomplete="off" 
							  placeholder="Search...">
						</div>		
				   </div>	
				   
				   <div class="col-md-12 row form-group" style="margin-top: -10px;">
				        <div class="col-md-3" style="text-align: left;">
							  <label for="text-input" class="form-control-label" style="margin-left: 13px;">Discharge Cause</label>
						</div>
						<div class="col-md-9">
							<input type="text" name="icd_cause_code1d" id="icd_cause_code1d" class="form-control-sm form-control" autocomplete="off" 
							placeholder="Search..." disabled>			
						</div>
				   </div>
			  </div>		
					
			  <div class="card-footer" align="center" style="margin-top: -20px;">
				   <input type="button" class="btn btn-primary btn-sm" value="Clear"  onclick="btn_clc();" /> 
			       <input type="button" class="btn btn-success btn-sm" id="btn_update" value="Update" onclick="return validate();"> 
			  </div>
	       </div>
	  </div>
   </div>
</form:form>


<script>
function btn_clc(){
	location.reload(true);
}

function validate(){
	if ($("#diagnosis_code1d").val() == "") {
		alert("Please Enter Discharge Diagnosis");
		$("#diagnosis_code1d").focus();
		return false;
	}
	else{
		$("#discharge_edit_ds").submit();	
	}
}

$("#diagnosis_code1d").keyup(function(){
	var code = this.value;
	$().Autocomplete2('GET','getMNHDistinctICDList',diagnosis_code1d,{a:code,b:"ALL"},'','','','','');
});
</script>

<script>
$(document).ready(function() {
	if('${size}' != 0){
		$("#id_hid").val('${id3}');
		
		if('${list[0].diagnosis_code1d}' != ""){
			
				$.post("getMNHICDCodeToCauseVi?"+key+"="+value, {a:'${list[0].diagnosis_code1d}',b:"2"}).done(function(j) {
				var enc = j[j.length-1].substring(0,16);
				var a = dec(enc,j[0]);
				var data = '${list[0].diagnosis_code1d}' + "-" + a;
				$("#diagnosis_code1d").val(data);
			});
		}
		
		if('${list[0].icd_cause_code1d}' != ""){
		
				$.post("getMNHICDCodeToCauseVi?"+key+"="+value, {a:'${list[0].icd_cause_code1d}',b:"1"}).done(function(j) {
				var enc = j[j.length-1].substring(0,16);
				var a = dec(enc,j[0]);
				var data = '${list[0].icd_cause_code1d}' + "-" + a;
				$("#icd_cause_code1d").val(data);
				$("#icd_cause_code1d").attr('disabled',false);
			});
		}
	}
	
	$("#diagnosis_code1d").change(function(){
		var a = this.value.split("-");
		var b = a[0].substring(0,1);
		
		if(b == "S" || b == "T" || b == "V" || b == "W" || b == "X" || b == "Y"){
			$("#icd_cause_code1d").attr('disabled',false);
			
			$("#icd_cause_code1d").keyup(function(){
				var code = this.value;
				/* var para = '${r_1[0][1]}';
				var paravalue="";
				if(para == "MISO"){
					paravalue="ALL";
				} */
				$().Autocomplete2('GET','getMNHDistinctICDList',icd_cause_code1d,{a:code,b:"ALL2"},'','','','','');
			});
		}
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
