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

<form:form name="Edit_CopeCode" id="Edit_CopeCode" action="Edit_CopeCodeAction" method="post" class="form-horizontal" commandName="Edit_CopeCodeCMD"> 
	<div class="animated fadeIn">
	    	<div class="container" align="center">
	    		<div class="card">
	    		<div class="card-header"><h5>UPDATE COPE CODE</h5> <h6 class="enter_by"><span style="font-size:12px;color:red;">(To be entered by MISO)</span></h6></div>
	              			<div class="card-body card-block">
	            			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> COPE CODE</label>
						                </div>
						                 <input type="hidden" id="id" name="id" class="form-control" value="0" autocomplete="off">
						                <div class="col-md-8">
										<select name="cope" id="cope" class="form-control">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getCopeCodeList}" varStatus="num">
													<option value="${item[1]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> VALUE</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="value" name="value" class="form-control autocomplete" autocomplete="off" oninput="this.value = this.value.toUpperCase()" maxlength="100"  /> 
						                </div>
						            </div>
	              				</div>
								</div>	
	              				
	              				<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> DESCRIPTION</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="description" name="description" oninput="this.value = this.value.toUpperCase()" class="form-control autocomplete" autocomplete="off" > 
						                </div>
						            </div>
	              				</div>
	              				
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
								</div>
            			</div>
									
						<div class="card-footer" align="center" class="form-control">
							<a href="CopecodeUrl" class="btn btn-danger btn-sm">Cancel</a>
		              		<input type="submit" class="btn btn-primary btn-sm" value="Update" onclick="return validate();"> 	              		 
			            </div> 		
	        	</div>
			</div>
	</div>

</form:form>


<script>
$(document).ready(function() {
	$("#cope").val('${Edit_ccCMD.cope_code}');
	$("#value").val('${Edit_ccCMD.value}');
	$("#description").val('${Edit_ccCMD.description}');
	$("#id").val('${Edit_ccCMD.id}');
	$("select#status").val('${Edit_ccCMD.status}');
});

function validate(){
	if ($("select#cope").val() == "0") {
		alert("Please Select Cope Code");
		$("select#cope").focus();
		return false;
	}
	
	if ($("input#value").val().trim() == "") {
		alert("Please Enter Value");
		$("input#value").focus();
		return false;
	}
	
	if ($("input#description").val().trim() == "") {
		alert("Please Enter Description");
		$("input#description").focus();
		return false;
	}
  
  	/* if ($("select#status").val() == "inactive") {
		alert("Only Select Active Status");
		$("select#status").focus();
		return false;
	} */
	return true;
}

</script>

