<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>

<form:form action="type_armAction" method="post" class="form-horizontal" commandName="type_armCMD">
	<div class="animated fadeIn">
			<div class="container" >
	    		<div class="card">
	    				<div class="card-header" align="center"> <h5>CREATE TYPE OF ARM</h5></div>
	          			<div class="card-body" align="center">
            				<div class="col-md-8">
              					<div class="row form-group">
                					<div class="col-md-4">
                  						<label class=" form-control-label"><strong style="color: red;">* </strong>Parent Arm</label>
                					</div>
                					<div class="col-md-8">
                  						<select name="parent_arm" id="parent_arm" class="form-control" >	
                  							<option value="0">--Select--</option>
                  							<c:forEach var="item" items="${getPrantArmList}" varStatus="num" >
                  								<option value="${item.code}">${item.code} - ${item.code_value}</option>
                  							</c:forEach>                  								
							            </select>
                					</div>
              					</div>
              					
              					<div class="row form-group">
                					<div class="col-md-4">
                  						<label class=" form-control-label"><strong style="color: red;">* </strong>Type of Arm</label>
                					</div>
                					<div class="col-md-8"> 
                  						<input type="text" id="code_value" name="code_value" maxlength="400"  class="form-control"  onkeypress="return blockSpecialChar(event)" required="required">
                					</div>
							   </div>
							   <div class="row form-group">
                					<div class="col-md-4">
                  						<label class=" form-control-label"><strong style="color: red;">* </strong>Type of Arm Code</label>
                					</div>
                					<div class="col-md-4">
                						<input type="text" id="parnt_code_id" name="parnt_code_id"  class="form-control" value="" readonly="true"> 
                					</div>
                					<div class="col-md-4">
                  						<input type="text" id="code" name="code"  onkeypress="return validateNumber(event, this)" class="form-control" maxlength="2" required="required">
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
</form:form>

	<c:url value="type_arm1" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="parnt_code_id1">
		<input type="hidden" name="parnt_code_id1" id="parnt_code_id1" value="0"/>
	</form:form> 

<script>

if('${parnt_code_id}' != ""){
	$("#parent_arm").val('${parnt_code_id}');
	$("#parnt_code_id").val('${parnt_code_id}');
	$("#code").val('${code}');
}

var size;
$(document).ready(function() {
	$("select#parent_arm").change(function() {
		if(this.value != "0"){
			$("#parnt_code_id1").val(this.value);
			document.getElementById('searchForm').submit();
		}else{
			$("#parent_arm").val("0");
			$("#parnt_code_id").val("");
			$("#code").val("");
		}
	});
});
       
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
    /*   return ((k > 64 && k < 91) || (k > 96 && k < 123) || k == 8 || k == 32 || k == 40 || k== 41 || k== 38 || k == 45 || k == 44); */
     return ((k > 64 && k < 91) || (k > 96 && k < 123) || k == 8 || k == 32 || k == 40 || k== 41 || k== 95 || k == 45 || k == 44);
}
function isvalidation(){
    if($("select#parent_arm").val() == '0'){
		alert("Please Select Parent Arm")
		$("select#parent_arm").focus();
		return false;
	}
   	if($("input#code_value").val()==""  ){
		alert("Please Enter Type of Arm")
		$("input#code_value").focus();
		return false;
	}
	if($("input#code").val()==""){
		alert("Please Enter 2 Digit Code  ")
		$("input#code").focus();
		return false;
	}
	if($("input#code").val().length != "2"){
		alert("Please Enter 2 Digit Code  ")
		$("input#code").focus();
		return false;
	}
	return true;
}
</script>
