<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="Stylesheet" href="js/Calender/jquery-ui.css" >
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<form:form action="EditOperationAction" id="EditOperation" method="post" class="form-horizontal" commandName="EditOperationCMD">
	<div class="container" align="center">
		<div class="card">
			<div class="card-header ">
				<h5>UPDATE OPERATION NAME</h5>
				<h6>
					<span style="font-size: 12px; color: red">(To be entered by MISO)</span>
				</h6>
			</div>

			<div class="card-body card-block">
				<div class="row">
					<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Operation Name</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="operation_name" name="operation_name" class="form-control autocomplete" autocomplete="off" > 
						                    <input type="hidden" id="id" name="id" class="form-control autocomplete" autocomplete="off" > 
						                </div>
						            </div>
	              				</div>
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
			<div class="card-footer" align="center">
			    
				<a href="Operation" class="btn btn-danger btn-sm">Cancel</a>
				<input type="submit" class="btn btn-success btn-sm" value="Update"  onclick="return validate1();"> 
			</div>
		</div>
	</div>
	</div>
</form:form>
<script>
$(document).ready(function() {
 
		
		$("#operation_name").val('${EditOperationCMD.operation_name}');	
		$("#id").val('${EditOperationCMD.id}');
		$("#status").val('${EditOperationCMD.status}');	
	
});
</script>



<!-- for Functions -->
<script>


function validate1(){
	

	
	
	 if ($("input#operation_name").val().trim() == "") {
			alert("Please Enter Operation Name.");
			$("input#operation_name").focus();
			return false;
		}  
	 /*  if ($("select#status").val() == "inactive") {
			alert("Only Select Active Status");
			$("select#status").focus();
			return false;
		} */
	  return true;
	
	
}	
	

</script>


