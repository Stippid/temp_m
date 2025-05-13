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

<form:form name="Edit_height" id="Edit_height" action="Edit_height_Action" method="post" class="form-horizontal" commandName="Edit_heightCMD"> 
	<div class="animated fadeIn">
	    	<div class="container" align="center">
	    		<div class="card">
	    		<div class="card-header"><h5>Update Height</h5> <h6 class="enter_by"><span style="font-size:12px;color:red;">(To be entered by MISO)</span></h6></div>
	          			<div class="card-body card-block">
	            			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>CENTIMETER</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="centimeter" name="centimeter" maxlength="50" class="form-control autocomplete" autocomplete="off" onkeypress="return isNumber1(event)"> 
						                </div>
						            </div>
	              				</div>
	              				  <input type="hidden" id="id" name="id" class="form-control" value="0" autocomplete="off">	
	              			<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> INCH</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="inch" name="inch" class="form-control autocomplete" maxlength="50" autocomplete="off" onkeypress="return isNumber1(event)"> 
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
							<a href="Height" class="btn btn-danger btn-sm">Cancel</a>
		              		<input type="submit" class="btn btn-primary btn-sm" value="Update"  onclick="return Validate();">              		 
			           </div> 		
	        	</div>
			</div>
	</div>

</form:form>


<script>
function isNumber1(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if ((event.which != 46) && (event.which < 47 || event.which > 59))
    {
        event.preventDefault();
        if ((event.which == 46) && ($(this).indexOf('.') != -1)) {
            event.preventDefault();
        }
    }
    return true;
}
function Validate() {
	if ($("input#centimeter").val().trim() == "") {
		alert("Please Enter centimeter ");
		$("input#centimeter").focus();
		return false;
	}
	if ($("input#inch").val().trim() == "") {
		alert("Please Enter Inch ");
		$("input#inch").focus();
		return false;
	}
	/* if ($("select#status").val() == "inactive") {
		   alert("Only Select Active Status");
			$("select#status").focus();
			return false;
		} */
	 else{
			var q1 = $("#centimeter").val();
			var q2 = $("#inch").val();
			var q3 = $("#id").val();
			
			if(q3 != ""){
				
				$("#Edit_height").submit();
			}
			
		}
	
	 /*  if ($("select#status").val() == "inactive") {
			alert("Only Select Active Status");
			$("select#status").focus();
			return false;
		} */
	  
	  return true;
}

$(document).ready(function() {
	$("#centimeter").val('${Edit_heightCMD.centimeter}');
	$("#inch").val('${Edit_heightCMD.inch}');
	$("#status").val('${Edit_heightCMD.status}');
	$("#id").val('${Edit_heightCMD.height_id}');
});
</script>

