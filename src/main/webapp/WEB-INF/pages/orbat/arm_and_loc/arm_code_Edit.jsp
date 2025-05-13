<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>

<form:form  name="updateArmAction" id="updateArmAction"  action="updateArmAction"  method="POST"  commandName="EditArmCMD"> 
	<div class="animated fadeIn">
		<div class="container" align="center">
	    	<div class="card">
				<div class="card-header"> <h5>UPDATE ARM CODE</h5></div>
					<div class="card-body">
	            		<div class="col-md-12">
							<div class="col-md-6">
              					<div class="row form-group">
                					<div class="col col-md-4">
                  						<label class=" form-control-label"><strong style="color: red;">* </strong>Arm</label>
                					</div>
                					<div class="col-12 col-md-8">
                					 <input type="hidden" id="id" name="id" value="${EditArmCMD.id}"/>
                  					     <input type="text" id="arm_desc" name="arm_desc"  maxlength="100" value="${EditArmCMD.arm_desc}" class="form-control"  onkeypress="return blockSpecialChar(event)">
                					</div>
              					</div>
						   </div>
						   
						   <div class="col-md-6">
              					<div class="row form-group">
                					<div class="col col-md-4">
                  						<label class=" form-control-label"><strong style="color: red;">* </strong>Arm Code</label>
                					</div>
                					<div class="col-12 col-md-8">
                  						<input type="text" id="arm_code" maxlength="4" name="arm_code" value="${EditArmCMD.arm_code}" pattern=".{4,}" class="form-control" readonly="readonly">
                					</div>
              					</div>
   					      	</div>
						</div>
					</div>
					<div class="card-footer" align="center">
						<input type="reset" class="btn btn-success btn-sm" value="Clear">   
	              		<input type="submit" class="btn btn-primary btn-sm" value="Update" onclick="return isLatLonValid();" >
	              		<a href="search_arm_code" type="reset" class="btn btn-danger btn-sm">  Cancel </a>
		            </div> 
	        	</div>
			</div>
		</div>
	
</form:form>

<script>
function blockSpecialChar(e){
	var k;
	document.all ? k = e.keyCode : k = e.which;
	return ((k > 64 && k < 91) || (k > 96 && k < 123) || k == 8 || k == 32 || k == 40 || k== 41 || k== 38 || k == 45 || k == 44);
}
function isLatLonValid(){
	if($("input#arm_desc").val()==""){
		alert("Please Enter Type of Arm")
		$("input#arm_desc").focus();
		return false;
	}
 	if($("input#arm_code").val()==""){
		alert("Please Enter arm_code")
		$("input#arm_code").focus();
		return false;
	}
}
</script>