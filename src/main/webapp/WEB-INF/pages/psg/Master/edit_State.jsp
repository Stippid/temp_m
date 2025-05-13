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

<form:form name="Edit_State" id="Edit_State" action="Edit_State_Action" method="post" class="form-horizontal" commandName="Edit_StateCMD"> 
	<div class="animated fadeIn">
	    	<div class="container" align="center">
	    		<div class="card">
	    		<div class="card-header"><h5>Update State</h5> <h6 class="enter_by"><span style="font-size:12px;color:red;">(To be entered by MISO)</span></h6></div>
	          			<div class="card-body card-block">
	            			
	              			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> COUNTRY NAME</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="hidden" id="id" name="id" class="form-control" value="0" autocomplete="off">
						                   <select name="country_id" id="country_id" class="form-control-sm form-control"   >
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${country_id}" varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select> 
						                </div>
						            </div>
	              				</div>
	              				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> STATE NAME</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="state_name" name="state_name" maxlength="30" class="form-control autocomplete" autocomplete="off" oninput="this.value = this.value.toUpperCase()" onkeypress="return onlyAlphabets(event);"> 
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
<!-- 												<option value="0">--Select--</option> -->
												<c:forEach var="item" items="${getStatusMasterList}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>	
								</div>
            			</div>
									
						<div class="card-footer" align="center" class="form-control">
							<a href="State" class="btn btn-danger btn-sm">Cancel</a>
		              		<input type="submit" class="btn btn-primary btn-sm" value="Update" onclick="return validate();"> <!-- onclick="return validate();" -->		              		 
			            </div> 		
	        	</div>
			</div>
	</div>

</form:form>


<script>

function validate() {
	if ($("select#country_id").val() == 0) {
		alert("Please Select Country Name");
		$("select#country_id").focus();
		return false;
	}
	
	if ($("input#state_name").val().trim() == 0) {
		alert("Please Enter State Name");
		$("select#state_name").focus();
		return false;
	}
	
	/* if ($("select#status").val() == "inactive") {
		alert("Only Select Active Status");
		$("select#status").focus();
		return false;
	} */
	return true;
}
$(document).ready(function() {
	$("#state_name").val('${Edit_StateCMD.state_name}');
	$("#country_id").val('${Edit_StateCMD.country_id}');
	$("#status").val('${Edit_StateCMD.status}');
	$("#id").val('${Edit_StateCMD.state_id}');
});
</script>

