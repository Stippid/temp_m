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

<form:form name="Edit_Institute" id="Edit_Institute" action="Edit_InstituteAction" method="post" class="form-horizontal" commandName="Edit_InstituteCMD"> 
	<div class="animated fadeIn">
	    	<div class="container" align="center">
	    		<div class="card">
	    		<div class="card-header"><h5>UPDATE Institute</h5> <h6 class="enter_by"><span style="font-size:12px;color:red;">(To be entered by MISO)</span></h6></div>
	          			<div class="card-body card-block">
	            			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Institute Name</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="institute_name" name="institute_name" class="form-control autocomplete" autocomplete="off"
						                   maxlength="100" oninput="this.value = this.value.toUpperCase()"  /> 
						                   <input type="hidden" id="id" name="id" class="form-control" autocomplete="off" value="0">
						                </div>
						               </div>
						            </div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Battalion</label>
						                </div>
						                <div class="col-md-8">
						                   <select name="bn_id" id="bn_id" class="form-control autocomplete"    >
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getBattalionList}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select> 
						                </div>
						            </div>
	              				</div>
	              			 </div>
	              		
            			</div>
            				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Company</label>
						                </div>
						                <div class="col-md-8">
						                   <select name="company_id" id="company_id" class="form-control-sm form-control"   >
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getCompanyList}" varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select> 
						                </div>
						            </div>
	              				</div>
									
						<div class="card-footer" align="center" class="form-control">
							<a href="institute" class="btn btn-danger btn-sm" >Cancel</a>    
		              		<input type="submit" class="btn btn-primary btn-sm" value="Update" onclick="return Validation();">
			            </div> 		
	        	</div>
			</div>
	</div>
</form:form>

<script>
$(document).ready(function() {
	
	$("#institute_name").val('${Edit_InstituteCMD.institute_name}');	
	$("#bn_id").val('${Edit_InstituteCMD.bn_id}');	
	$("#company_id").val('${Edit_InstituteCMD.company_id}');	
	$("#status").val('${Edit_InstituteCMD.status}');	
	$("#id").val('${Edit_InstituteCMD.id}');
	
});

function Validation(){
	  if ($("input#institute_name").val().trim() == "") {
			alert("Please Enter Institute Name.");
			$("input#institute_name").focus();
			return false;
		}  
	  if ($("select#bn_id").val() == 0) {
			alert("Please Select Battalion Name.");
			$("select#bn_id").focus();
			return false;
		} 
	  if ($("select#company_id").val() == 0) {
			alert("Please Select Company Name.");
			$("select#company_id").focus();
			return false;
		} 
	  
		if ($("select#status").val() == "0") {
			alert("Please Select Status");
			$("select#status").focus();
			return false;
		}
	  return true;
}
	  
</script>


