<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<form:form action="parentsArmAction" commandName="parentsArmCMD" method="post"  class="form-horizontal"> 
	<div class="animated fadeIn">
		<div class="">
	    	<div class="container" align="center">
	    		<div class="card">
	    			<div class="card-header"> <h5>CREATE PARENT ARM</h5></div>
          			<div class="card-body card-block">
            			<div class="col-md-12">
							<div class="col-md-6">
              					<div class="row form-group">
                					<div class="col col-md-6">
                  						<label class=" form-control-label"><strong style="color: red;">* </strong>Parent Arm</label>
                					</div>
                					<div class="col-12 col-md-6">
                  						<input type="text" id="code_value" name="code_value" maxlength="400" class="form-control" onkeypress="return blockSpecialChar(event)" autocomplete="off" required="required">
                					</div>
              					</div>
              				</div>
             					<div class="col-md-6">
              					<div class="row form-group">
                					<div class="col col-md-6">
                  						<label class=" form-control-label"><strong style="color: red;">* </strong>Parent Arm Code</label>
                					</div>
                					<div class="col-12 col-md-6">
                  						<input type="text" id="code" maxlength="2" pattern=".{2,}"  name="code" class="form-control" onkeypress="return validateNumber(event, this)" onblur="numberchck(this)" autocomplete="off" required="required">
                					</div>
  								</div>
							</div>
             				</div>
					</div>
					<div class="card-footer" align="center">
						<input type="reset" class="btn btn-success btn-sm" value="Clear">    	
	              		<input type="submit" class="btn btn-primary btn-sm" value="Save" onclick="return isvalidation();">
	              	</div> 
	        	</div>
			</div>
		</div>
	</div>
</form:form>
<script>

//$('input').on('keypress', function (event) {
/* $(document).on('keypress', function(event) {
    var regex = new RegExp("^[a-zA-Z0-9\\[\\] \\+ \\* \\-.,/ @?#]+$");
    var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
    if (!regex.test(key)) {
       event.preventDefault();
       return false;
    } 
});
 */
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
    return ((k > 64 && k < 91) || (k > 96 && k < 123) || k == 8 || k == 32 || k == 40 || k== 41 || k== 95 || k == 45 || k == 44);
}
	
function isvalidation(){
	if($("input#code_value").val()==""){
		alert("Please Enter Type of Arm")
		$("input#code_value").focus();
		return false;
	}
   	if($("input#code").val()==""){
		alert("Please Enter Code ")
		$("input#code").focus();
		return false;
	}
   	if($("input#code").val().length != 2){
		alert("Please Enter 2 Digit Code")
		$("input#code").focus();
		return false;
	}
	return true;
}


</script>