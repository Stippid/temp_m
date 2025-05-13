<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<form:form action="createArmAction" commandName="createArmCMD" method="post"  class="form-horizontal"> 
	<div class="animated fadeIn">
		<div class="container" align="center">
    		<div class="card">
          		<div class="card-header"> <h5>CREATE ARM CODE</h5> </div>
      			<div class="card-body">
      				<div class="col-md-12">
						<div class="col-md-6">
	            			<div class="row form-group">
	              				<div class="col col-md-4">
	                				<label class=" form-control-label"><strong style="color: red;">* </strong>Arm</label>
	              				</div>
	              				<div class="col-12 col-md-8">
	                			     <input type="text" id="arm_desc" maxlength="100" name="arm_desc" class="form-control"  onkeypress="return blockSpecialChar(event)" required="required">
	              				</div>
	            			</div>
					   </div>
					   <div class="col-md-6">
	            			<div class="row form-group">
	              				<div class="col col-md-4">
	                					<label class=" form-control-label"><strong style="color: red;">* </strong>Arm Code</label>
	              				</div>
	              				<div class="col-12 col-md-8">
	                					<input type="text" id="arm_code" maxlength="4" name="arm_code" pattern=".{4,}" class="form-control" onkeypress="return validateNumber(event, this)" onblur="numberchck(this)" required="required">
	                					<form:errors path="arm_code"></form:errors> <label for="arm_code" class="error"></label>
	              				</div>
	            			</div>
						</div>
					</div>
				</div>
				<div class="card-footer" align="center">
					<input type="reset" class="btn btn-success btn-sm" value="Clear">   
	             	<input type="submit" class="btn btn-primary btn-sm" onclick="return isLatLonValid();" value="Save">
	             </div>
			</div>
		</div>
	</div>
</form:form>
<script>
function validateNumber(evt) {
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if (charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57)) {
		return false;
	} else {
		if (charCode == 46) {
			return false;
		}
	}
	return true;
}
   
   
function blockSpecialChar(e){
   var k;
   document.all ? k = e.keyCode : k = e.which;
   return ((k > 64 && k < 91) || (k > 96 && k < 123) || k == 8 || k == 32 || k == 40 || k== 41 || k== 38 || k == 45 || k == 44);
}
   
function numberchck(b){
	size=b.value.length;
    if(size < 4){
     	alert("enter 4 digit code");
     	document.getElementById('code').value = "";
   		$("input#arm_code").focus();
    }
}
function isLatLonValid(){
	if($("input#arm_desc").val()==""){
		alert("Please Enter Type of Arm");
		$("input#arm_desc").focus();
		return false;
	}
 	if($("input#arm_code").val()==""){
		alert("Please Enter arm_code");
		$("input#arm_code").focus();
		return false;
	}
}
</script>