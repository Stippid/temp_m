<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>

<form:form name="Edit_Disability_Master" id="Edit_Disability_Master" action="Edit_DisabilityAction" method="post" class="form-horizontal" commandName="Edit_DisabilityCMD"> 
	<div class="animated fadeIn">
	    	<div class="container" align="center">
	    		<div class="card">
	    		<div class="card-header"><h5>UPDATE DISABILITY</h5> <h6 class="enter_by"><span style="font-size:12px;color:red;">(To be entered by ADMIN)</span></h6></div>
	          			<div class="card-body card-block">
	            			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">*</strong> DISABILITY</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="disability" name="disability" oninput="this.value = this.value.toUpperCase()" class="form-control autocomplete" onkeypress="return onlyAlphabets(event);" maxlength="50" autocomplete="off" > 
						                   <input type="hidden" id="id" name="id" class="form-control" autocomplete="off" value="0">
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
							<a href="category_url" class="btn btn-danger btn-sm" >Cancel</a>    
		              		<input type="submit" class="btn btn-primary btn-sm" value="Update" onclick="return Validation();">
			            </div> 		
	        	</div>
			</div>
	</div>
</form:form>

<script>
$(document).ready(function() {
	
	$("#disability").val('${Edit_DisabilityCMD.disability}');	
	$("#status").val('${Edit_DisabilityCMD.status}');
	$("#id").val('${Edit_DisabilityCMD.id}');
	
});

function Validation(){
	 
	if ($("input#disability").val().trim() == "") {
			alert("Please Enter Disability");
			$("input#disability").focus();
			return false;
		}
		/* if ($("select#status").val() == "inactive") {
			alert("Only Select Active Status");
			$("select#status").focus();
			return false;
		} */
		 else{
				var q1 = $("#disability").val();
				var q2 = $("#id").val();
			
				
				if(q2 != ""){
					
					$("#Edit_Disability_Master").submit();
				}
				
			}
			return true;
	}
</script>


