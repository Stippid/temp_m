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

<form:form name="Edit_Casuality2" id="Edit_Casuality2" action="Edit_Casuality2_Action" method="post" class="form-horizontal" commandName="Edit_Casuality2_CMD"> 
	<div class="animated fadeIn">
	    	<div class="container" align="center">
	    		<div class="card">
	    		<div class="card-header"><h5>UPDATE CASUALTY2</h5> <h6 class="enter_by"><span style="font-size:12px;color:red;">(To be entered by MISO)</span></h6></div>
	          			<div class="card-body card-block">
	            			
	              			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Casualty1</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="hidden" id="id" name="id" class="form-control" value="0" autocomplete="off">
						                   <select name="casuality1_id" id="casuality1_id" class="form-control"   >
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getCasuality1}" varStatus="num">
												   <option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
						                </div>
						            </div>
	              				</div>
	              				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Casualty2</label>
						                </div>
						                <div class="col-md-8">
						                  <input type="text" id="casuality2" name="casuality2" class="form-control autocomplete" autocomplete="off" onkeypress="return onlyAlphabets(event);"> 
						                </div>
						            </div>
	              				</div>
	              			</div>
	              			<div class="col-md-12">	 
	              			<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong style="color: red;">* </strong>Status</label>
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
							<a href="Casuality2_Url" class="btn btn-danger btn-sm">Cancel</a>
		              		<input type="submit" class="btn btn-primary btn-sm" value="Update" onclick="return validate();"> 		              		 
			            </div> 		
	        	</div>
			</div>
	</div>

</form:form>


<script>

function validate() {
	if ($("select#casuality1_id").val() == 0) {
		alert("Please Select Casualty1.");
		$("select#casuality1_id").focus();
		return false;
	}
	
	if ($("input#casuality2").val().trim() == 0) {
		alert("Please Select Casualty2.");
		$("input#casuality2").focus();
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
	$("#casuality2").val('${Edit_Casuality2_CMD.casuality2}');
	$("#casuality1_id").val('${Edit_Casuality2_CMD.casuality1_id}');
	$("#status").val('${Edit_Casuality2_CMD.status}');
	$("#id").val('${Edit_Casuality2_CMD.id}');
});
</script>

