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

<form:form name="Edit_CDA_NO" id="Edit_CDA_NO" action="Edit_CDA_NO_Action?${_csrf.parameterName}=${_csrf.token}" method="post" class="form-horizontal" commandName="Edit_CDANOCMD"> 
	<div class="animated fadeIn">
	    	<div class="container" align="center">
	    		<div class="card">
	    		<div class="card-header"><h5>Update CDA Account No</h5> <h6 class="enter_by"><span style="font-size:12px;color:red;">(To be entered by MISO)</span></h6></div>
	          			<div class="card-body card-block">
	            			<div class="col-md-12">	  
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> CDA Account No</label>
						                </div>
						                <div class="col-md-8">
						                 <input type="hidden" id="id" name="id" class="form-control" value="0" autocomplete="off">		
						                   <input type="text" id="cda_acc_no" name="cda_acc_no" class="form-control autocomplete" autocomplete="off" > 
						                </div>
						            </div>
	              				</div>
	              			</div>
	              			
            			</div>
									
						<div class="card-footer" align="center" class="form-control">
							<a href="cda_acc_nourl" class="btn btn-danger btn-sm">Cancel</a>
		              		<input type="submit" class="btn btn-primary btn-sm" value="Update" onclick="return Validate();"> <!-- onclick="return validate();" -->		              		 
			            </div> 		
	        	</div>
			</div>
	</div>
</form:form>

<script>
function Validate() {
	if ($("input#cda_acc_no").val() == "") {
		alert("Please Enter CDA Account No");
		$("input#cda_acc_no").focus();
		return false;
	}
	return true;
}
$(document).ready(function() {
	$("#cda_acc_no").val('${Edit_CDANOCMD.cda_acc_no}');
	$("#id").val('${Edit_CDANOCMD.id}');
});
</script>

