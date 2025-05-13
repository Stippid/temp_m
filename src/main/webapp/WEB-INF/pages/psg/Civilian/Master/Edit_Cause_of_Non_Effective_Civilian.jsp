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
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
<link href="js/cue/cueWatermark.css" rel="Stylesheet"></link>

<form:form name="Edit_Cause_of_non_effective_Civilian" id="Edit_Cause_of_non_effective_Civilian" action="Edit_Cause_of_non_effective_CivilianAction" method="post" class="form-horizontal" commandName="Edit_Cause_of_non_effective_CivilianCMD"> 
	<div class="animated fadeIn">
	    	<div class="container" align="center">
	    		<div class="card">
	    		<div class="card-header"><h5>UPDATE CAUSE OF NON EFFECTIVE CIVILIAN</h5> <h6 class="enter_by"><span style="font-size:12px;color:red;">(To be entered by MISO)</span></h6></div>
	          			<div class="card-body card-block">
	            			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> CAUSES NAME</label>
						                </div>
						                <div class="col-md-8">
											<input type="text" id="causes_name" name="causes_name" class="form-control autocomplete" autocomplete="off" maxlength="50" oninput="this.value = this.value.toUpperCase()" />
											<input type="hidden" id="id" name="id" class="form-control autocomplete" autocomplete="off" > 
										</div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong style="color: red;">* </strong>TYPE OF CIVILIAN</label>
										</div>
										<div class="col-md-8">
										<select name="type_of_civilian" id="type_of_civilian" class="form-control">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getCauseOfnoneffList}" varStatus="num">
													<option value="${item[1]}" name="${item[0]}">${item[0]}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>	
	              			</div>
	              			<div class="col-md-12">	              					
	              				<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong style="color: red;">* </strong>STATUS</label>
										</div>
										<div class="col-md-8">
										<select name="status" id="status" class="form-control">
												<c:forEach var="item" items="${getStatusMasterList}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>	
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong style="color: red;">* </strong>TYPE OF REGULAR/NON-REGULAR</label>
										</div>
										<div class="col-md-8">
										<select name="type_of_reg" id="type_of_reg" class="form-control">
												<option value="0">--Select--</option>
												<option value="REGULAR">REGULAR</option>
												<option value="NON-REGULAR">NON-REGUALAR</option>
											</select>
										</div>
									</div>
								</div>	
	              			</div>
            			</div>
									
						<div class="card-footer" align="center" class="form-control">
							<a href="Cause_of_non_eff_civilianUrl" class="btn btn-danger btn-sm">Cancel</a>
		              		<input type="submit" class="btn btn-primary btn-sm" value="Update"  onclick="return Validate();">
			            </div> 		
	        	</div>
			</div>
	</div>
	
</form:form>


<Script>
$(document).ready(function() {
	
	$("#causes_name").val('${Edit_Cause_of_non_effective_CivilianCMD.causes_name}');	
	$("#type_of_civilian").val('${Edit_Cause_of_non_effective_CivilianCMD.type_of_civilian}');
	$("#id").val('${Edit_Cause_of_non_effective_CivilianCMD.id}');
	$("#type_of_reg").val('${Edit_Cause_of_non_effective_CivilianCMD.type_of_regular_or_nonregular}');
	$("#status").val('${Edit_Cause_of_non_effective_CivilianCMD.status}');
	
});


function Validate() {
	if ($("input#causes_name").val() == "") {
		alert("Please Enter Causes Name");
		$("#causes_name").focus();
		return false;
	}
	if ($("select#type_of_civilian").val() == "0") {
		alert("Please Select Type Of Civilian");
		$("select#type_of_officer").focus();
		return false;
	}
	/* if ($("select#status").val() == "inactive") {
		alert("Only  Select Active Status");
		$("select#status").focus();
		return false;
	} */
	if ($("select#type_of_reg").val() == "0") {
		alert("Please Select Type Of Regular/Non-Regular");
		$("select#type_of_reg").focus();
		return false;
	}
	return true;
}
	
</Script>

