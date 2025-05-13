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

<form:form name="Edit_Link_Degree" id="Edit_Link_Degree" action="Edit_Link_Degree_Action" method="post" class="form-horizontal" commandName="Edit_LinkDegreeCMD"> 
	<div class="animated fadeIn">
	    	<div class="container" align="center">
	    		<div class="card">
	    		<div class="card-header"><h5>Update Link Degree</h5> <h6 class="enter_by"><span style="font-size:12px;color:red;">(To be entered by MISO)</span></h6></div>
	          			<div class="card-body card-block">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong> Degree </label>
								</div>
								<div class="col-md-8">
								 <input type="hidden" id="id" name="id" class="form-control" value="0" autocomplete="off">
									<select name="degree" id="degree"
										class="form-control-sm form-control">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getDegreeList}"
											varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>

							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">
									
									<strong
										style="color: red;">* </strong> Specialization</label>
								</div>
								<div class="col-md-8">
								<select name="specialization" id="specialization"
										class="form-control-sm form-control">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getSpecializationList}"
											varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
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
											<label class=" form-control-label"><strong style="color: red;">* </strong>Status</label>
										</div>
										<div class="col-md-8">
										<select name="status" id="status" class="form-control">
												<option value="0">--Select--</option>
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
							<a href="LinkUrl" class="btn btn-danger btn-sm">Cancel</a>
		              		<input type="submit" class="btn btn-primary btn-sm" value="Update" onclick="return validate();" > <!-- onclick="return validate();" -->		              		 
			            </div> 		
	        	</div>
			</div>
	</div>

</form:form>


<script>

 function validate() {
	if ($("select#degree").val() == "0") {
		alert("Please Enter Degree");
		$("input#degree").focus();
		return false;
	}
	if ($("select#specialization").val() == "0") {
		alert("Please Enter specialization");
		$("input#specialization").focus();
		return false;
	}
	if ($("select#status").val() == "0") {
		alert("Please Select Status");
		$("select#status").focus();
		return false;
	}

	
	return true;
} 
$(document).ready(function() {
	$("#degree").val('${Edit_LinkDegreeCMD.degree}');
	$("#specialization").val('${Edit_LinkDegreeCMD.specialization}');
	$("#status").val('${Edit_LinkDegreeCMD.status}');
	$("#id").val('${Edit_LinkDegreeCMD.id}');
	
});
</script>

