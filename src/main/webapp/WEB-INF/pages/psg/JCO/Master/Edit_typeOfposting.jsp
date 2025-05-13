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
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>

<form:form action="EditType_Of_PostingAction" id="Edittypeofposting" method="post" class="form-horizontal" commandName="EditType_Of_PostingCMD">
	<div class="container" align="center">
		<div class="card">
			<div class="card-header ">
				<h5>UPDATE TYPE OF POSTING</h5>
				<h6>
					<span style="font-size: 12px; color: red">(To be entered by Medical Unit)</span>
				</h6>
			</div>

			<div class="card-body card-block">
				<div class="row">
					<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>TYPE OF POSTING</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="type_of_post" name="type_of_post" oninput="this.value = this.value.toUpperCase()" maxlength="50"  class="form-control autocomplete" autocomplete="off" 
						                    onkeypress="return onlyAlphabets(event);" /> 
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
			</div>
			<div class="card-footer" align="center">
			    <input type="hidden" id="id" name="id" value="${EditType_Of_PostingCMD.id}" class="form-control" autocomplete="off">				
				<a href="type_of_postURl" class="btn btn-danger btn-sm">Cancel</a>
				<input type="submit" class="btn btn-success btn-sm" value="Update"  /> 
			</div>
		</div>
	</div>
</form:form>
<script>
$(document).ready(function() {
 
		$("#type_of_post").val('${EditType_Of_PostingCMD.type_of_post}');
		$("#id").val('${EditType_Of_PostingCMD.id}');
		$("#status").val('${EditType_Of_PostingCMD.status}');

	 
	
});
</script>



<!-- for Functions -->
<script>


function validate1(){
	
	if($("#type_of_post").val() == ""){
		alert("Please Enter the Type of Post");
		$("#type_of_post").focus();
		return false;
	}
 
/*  else{
		var q1 = $("#language_name").val();
		var q2 = $("#id").val();
	
		
		if(q2 != ""){
			
			$("#EditLanguage").submit();
		}
		
	} */
	/*  if ($("select#status").val() == "inactive") {
			alert("Only Select Active Status");
			$("select#status").focus();
			return false;
		} */
	return true;
}	
	

</script>


