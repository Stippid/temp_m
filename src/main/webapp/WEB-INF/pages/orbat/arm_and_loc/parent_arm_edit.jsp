<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>

<form:form action="parentsArmEditAction"  commandName="parentsArmEditCMD" method="post"  class="form-horizontal"> 
	<div class="animated fadeIn">
		<div class="">
		<input type="hidden" id="code" name="code" class="form-control" value="${parentsArmEditCMD.code}">
	    	<div class="container" align="center">
	    		<div class="card">
	    			
	          		<div class="card-header"> <h5>UPDATE PARENT ARM</h5> </div>
	          			<div class="card-body">
	            			<div class="col-md-12">
								<div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-6">
	                  						<label class=" form-control-label"><strong style="color: red;">* </strong>Parent Arm</label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                					<input type="hidden" id="id" name="id" class="form-control" value="${parentsArmEditCMD.id}">
	                  						<input type="text" id="code_value" name="code_value" maxlength="400" class="form-control" value="${parentsArmEditCMD.code_value}" onkeypress="return blockSpecialChar(event)" autocomplete="off">
	                					</div>
	              					</div>
								</div>
								<div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-6">
	                  						<label class=" form-control-label"><strong style="color: red;">* </strong>Parent Arm Code</label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                  						<label id="code1" class="form-control" >${parentsArmEditCMD.code}</label>
	                  						
	                					</div>
	  								</div>
								</div>
							</div>
						</div>
						<div class="card-footer" align="center">
							<input type="submit" class="btn btn-primary btn-sm" value="Update" onclick="return isvalidation();">
		              		
			            </div> 
	        	</div>
			</div>
		</div>
	</div>
</form:form>

<script>
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
}
</script>