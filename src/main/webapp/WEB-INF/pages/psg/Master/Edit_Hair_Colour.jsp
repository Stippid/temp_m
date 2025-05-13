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

<form:form name="Edit_Hair_Colour" id="Edit_Hair_Colour" action="Edit_Hair_ColourAction" method="post" class="form-horizontal" commandName="Edit_Hair_ColourCMD"> 
	<div class="animated fadeIn">
	    	<div class="container" align="center">
	    		<div class="card">
	    		<div class="card-header"><h5>UPDATE HAIR COLOUR</h5> <h6 class="enter_by"><span style="font-size:12px;color:red;">(To be entered by MISO)</span></h6></div>
	          			<div class="card-body card-block">
	            			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> COLOUR NAME</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="hair_cl_name" name="hair_cl_name" class="form-control autocomplete" oninput="this.value = this.value.toUpperCase()" maxlength="50" autocomplete="off" onkeypress="return onlyAlphabets(event);"> 
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
							<a href="Hair_Colour" class="btn btn-danger btn-sm" >Cancel</a>    
		              		<input type="submit" class="btn btn-primary btn-sm" value="Update" onclick="return Validation();">
			            </div> 		
	        	</div>
			</div>
	</div>
</form:form>

<script>
$(document).ready(function() {
	
	$("#hair_cl_name").val('${Edit_Hair_ColourCMD.hair_cl_name}');	
	$("#status").val('${Edit_Hair_ColourCMD.status}');	
	$("#id").val('${Edit_Hair_ColourCMD.id}');
	
});

function Validation(){
	  if ($("input#hair_cl_name").val().trim() == "") {
			alert("Please Enter Colour Name");
			$("input#hair_cl_name").focus();
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


