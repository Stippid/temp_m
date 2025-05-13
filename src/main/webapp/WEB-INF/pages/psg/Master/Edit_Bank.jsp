<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<form:form name="Edit_Bank" id="Edit_Bank" action="Edit_Bank_Action" method="post" class="form-horizontal" commandName="Edit_Bank_CMD"> 
	<div class="animated fadeIn">
	    	<div class="container" align="center">
	    		<div class="card">
	    		<div class="card-header"><h5>Update Bank</h5> <h6 class="enter_by"><span style="font-size:12px;color:red;">(To be entered by MISO)</span></h6></div>
	          			<div class="card-body card-block">
	            			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Bank Name </label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="bank_name" name="bank_name" class="form-control autocomplete" autocomplete="off" > 
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Bank Abbreviation </label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="bank_abb" name="bank_abb" class="form-control autocomplete" autocomplete="off" > 
						                </div>
						            </div>
	              				</div>
	              			</div>	    
	              			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> IFSC </label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="ifsc" name="ifsc" class="form-control autocomplete" autocomplete="off" > 
						                       <input type="hidden" id="id" name="id" class="form-control" value="0" autocomplete="off">		
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Bank Address </label>
						                </div>
						                <div class="col-md-8">
						                   <textarea id="bank_address" name="bank_address" class="form-control autocomplete" autocomplete="off" ></textarea>
						                </div>
						            </div>
	              				</div>
	              			</div>	 
	              			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Bank Phone </label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="bank_phone" name="bank_phone" class="form-control autocomplete" maxlength="10" onkeypress="return validateNumber(event, this)" autocomplete="off" > 
						                </div>
						            </div>
	              				</div>
	              			</div>	           				
            			</div>
									
			            <div class="card-footer" align="center" class="form-control">
							<a href="Bank" class="btn btn-danger btn-sm" >Cancel</a>    
		              		<input type="submit" class="btn btn-primary btn-sm" value="Update" onclick="return validate();">
			            </div>	
	        	</div>
			</div>
	</div>
</form:form>


<script>
$(document).ready(function() {
	
	$("#bank_name").val('${Edit_Bank_CMD.bank_name}');
	$("#bank_abb").val('${Edit_Bank_CMD.bank_abb}');
	$("#ifsc").val('${Edit_Bank_CMD.ifsc}');
	$("#bank_address").val('${Edit_Bank_CMD.bank_address}');	
	$("#bank_phone").val('${Edit_Bank_CMD.bank_phone}');
	$("#id").val('${Edit_Bank_CMD.id}');	
});

function validate(){
	if($("input#bank_name").val() == ""){
		alert("Please Enter Bank Name")
		$("input#bank_name").focus();
		return false;
	}
	if($("input#bank_abb").val() == ""){
		alert("Please Enter Bank Abbreviation")
		$("input#bank_abb").focus();
		return false;
	}
	if($("input#ifsc").val() == ""){
		alert("Please Enter IFSC")
		$("input#ifsc").focus();
		return false;
	}
	if($("#bank_address").val() == ""){
		alert("Please Enter Bank Address")
		$("#bank_address").focus();
		return false;
	}
	if($("input#bank_phone").val() == ""){
		alert("Please Enter Bank Phone No")
		$("input#bank_phone").focus();
		return false;
	}
	return true;
}

function validateNumber(evt) {
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if (charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57)) {
		return false;
	} else {
		if (charCode == 46) {
			return false;
		}
	}
	return true;
} 
</script>


