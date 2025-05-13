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

<form:form name="Edit_Award_Category" id="Edit_Award_Category" action="Edit_Award_CategoryAction" method="post" class="form-horizontal" commandName="Edit_Award_CategoryCMD"> 
	<div class="animated fadeIn">
	    	<div class="container" align="center">
	    		<div class="card">
	    		<div class="card-header"><h5>UPDATE AWARD CATEGORY</h5> <h6 class="enter_by"><span style="font-size:12px;color:red;">(To be entered by MISO)</span></h6></div>
	              			<div class="card-body card-block">
	            			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> AWARD CATEGORY</label>
						                </div>
						                <div class="col-md-8">
						                 <input type="hidden" id="id" name="id" class="form-control" value="0" autocomplete="off">
						                   <input type="text" id="awardCat" name="awardCat" oninput="this.value = this.value.toUpperCase()" maxlength="50" class="form-control autocomplete" autocomplete="off" > 
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
									
						<div class="card-footer" align="center" class="form-control">
							<a href="awardCategoryUrl" class="btn btn-danger btn-sm">Cancel</a>
		              		<input type="submit" class="btn btn-primary btn-sm" value="Update" onclick="return validate();"> 		              		 
			            </div> 		
	        	</div>
			</div>
	</div>

</form:form>


<script>
$(document).ready(function() {
	$("#awardCat").val('${Edit_awardCatCMD.award_cat}');	
	$("#id").val('${Edit_awardCatCMD.id}');
	$("select#status").val('${Edit_awardCatCMD.status}');
});

function validate(){
	if ($("input#awardCat").val().trim() == "") {
		alert("Please Enter Award Category");
		$("input#awardCat").focus();
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

