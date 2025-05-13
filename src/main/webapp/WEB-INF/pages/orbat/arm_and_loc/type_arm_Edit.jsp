<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>

<form:form action="Edittype_armAction" method="post" class="form-horizontal" commandName="Edittype_armCMD">
	<div class="animated fadeIn">
		<div class="">
	    	<div class="container" align="center">
	    		<div class="card">
	    			<div class="card-header"><h5>UPDATE TYPE OF ARM</h5> </div>
	          				<div class="col-md-7 card-body">
              					<div class="row form-group">
                					<div class="col col-md-4">
                  						<label class=" form-control-label"><strong style="color: red;">* </strong>Parent Arm</label>
                					</div>
                					<div class="col-12 col-md-8">
                						<input type="hidden" id="id" name="id" class="form-control"  value="${Edittype_armCMD.id}">
                						<input type="hidden" id="code_hidden" name="code_hidden" value="${Edittype_armCMD.code}">
                  						<select name="parent_arm" id="parent_arm" class="form-control-sm form-control" disabled="disabled">
                  							<option value="${code}">${code} - ${code_value}</option>
	               						</select>
                					</div>
              					</div>
              					<div class="row form-group">
                					<div class="col col-md-4">
                  						<label class=" form-control-label"><strong style="color: red;">* </strong>Type of Arm</label>
                					</div>
                					<div class="col-12 col-md-8"> 
                  						<input type="text" id="code_value" name="code_value" maxlength="400"  class="form-control" value="${Edittype_armCMD.code_value}"  onkeypress="return blockSpecialChar(event)">
                					</div>
							   </div>
							   <div class="row form-group">
                					<div class="col col-md-4">
                  						<label class=" form-control-label"><strong style="color: red;">* </strong>Type of Arm Code</label>
                					</div>
                					<div class="col-6 col-md-4">
                						<input type="text" id="parnt_code_id" name="parnt_code_id" class="form-control" value="${parnt_code_id}" readonly="readonly" > 
                					</div>
                					<div class="col-6 col-md-4">
                  						<input type="text" id="code" name="code" placeholder="" pattern=".{2,}" class="form-control" maxlength="2" value="${code1}" readonly="readonly" >
                  					</div>
								</div>
							</div>
						</div>
						<div class="card-footer" align="center">
							<input type="submit" class="btn btn-primary btn-sm" value="Update" onclick="return isvalidation();">
	              			<a href="search_type_arm" type="reset" class="btn btn-danger btn-sm">  Cancel </a>
		            	</div> 
	        		</div>
				</div>
			</div>
		
</form:form>
<script>
function isvalidation(){
	if($("select#parent_arm").val()== '0'){
   		alert("Please Select Parent Arm")
   		$("select#parent_arm").focus();
   		return false;
   	}
   	if($("input#code_value").val()==""  ){
   		alert("Please Enter Type of Arm")
   		$("input#code_value").focus();
   		return false;
   	}
   	if($("input#code").val()=="" ){
  		alert("Please Enter 2 Digit Code  ")
  		$("input#code").focus();
  		return false;
	}
}
</script>