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

<form:form name="Edit_Combination" id="Edit_Combination" action="Edit_Combination_Action" method="post" class="form-horizontal" commandName="Edit_CombinationCMD"> 
	<div class="animated fadeIn">
	    	<div class="container" align="center">
	    		<div class="card">
	    		<div class="card-header"><h5>UPDATE COMBINATION OF ALLCEA</h5> <h6 class="enter_by"><span style="font-size:12px;color:red;">(To be entered by MISO)</span></h6></div>
	              			<div class="card-body card-block">
	            			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> FD 1</label>
						                </div>
						                <div class="col-md-8">
						                 <input type="hidden" id="id" name="id" class="form-control" value="0" autocomplete="off">
						                   <select name="fd1" id="fd1" class="form-control">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getField_areaList}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong style="color: red;">* </strong>FD 2</label>
										</div>
										<div class="col-md-8">
											<select name="fd2" id="fd2" class="form-control">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getField_areaList}" varStatus="num">
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
							<a href="Combination_of_allceaUrl" class="btn btn-danger btn-sm">Cancel</a>
		              		<input type="submit" class="btn btn-primary btn-sm" value="Update" onclick="return Validate();">               		 
			            </div> 		
	        	</div>
			</div>
	</div>

</form:form>


<script>
$(document).ready(function() {
	$("#fd1").val('${Edit_CombinationCMD.fd1}');	
	$("#fd2").val('${Edit_CombinationCMD.fd2}');	
	$("#id").val('${Edit_CombinationCMD.id}');
	$("#status").val('${Edit_CombinationCMD.status}');
	
	
});


function Validate() {
	if ($("select#fd1").val() == "0") {
		alert("Please Select FD 1");
		$("select#fd1").focus();
		return false;
	}
	if ($("select#fd2").val() == "0") {
		alert("Please Select FD 2");
		$("select#fd2").focus();
		return false;
	}
	/* if ($("select#status").val() == "inactive") {
		alert("Only  Select Active Status");
		$("select#status").focus();
		return false;
	} */
	return true;
}

</script>

