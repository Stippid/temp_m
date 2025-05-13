<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>

<form action="" method="post" enctype="multipart/form-data" class="form-horizontal"> 
	<div class="animated fadeIn">
			<div class="container" align="center">
	    		<div class="card">
	    			<div class="card-header"> <h5>SEARCH ARM CODE</h5> </div>
	          			<div class="card-body">
	            			<div class="col-md-12">
								<div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-4">
	                  						<label class=" form-control-label">Arm</label>
	                					</div>
	                					<div class="col-12 col-md-8">
	                  					     <input type="text" id="arm_desc" name="arm_desc" maxlength="100" class="form-control"  onkeypress="return blockSpecialChar(event)">
	                					</div>
	              					</div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-4">
	                  						<label class=" form-control-label">Arm Code</label>
	                					</div>
	                					<div class="col-12 col-md-8">
	                  						<input type="text" id="arm_code" maxlength="4" name="arm_code" pattern=".{4,}" placeholder="" class="form-control" onkeypress="return validateNumber(event, this)">
	                					</div>
	              					</div>
	   					      	</div>
	              			</div>
              				<div class="col-md-12">
              					<div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-4">
	                  						<label class=" form-control-label">Status</label>
	                					</div>
	                					<div class="col-12 col-md-8">
	                  						<select name="status" id="status" class="form-control-sm form-control">
	                  								 <option value="0">Pending</option>
								                    <option value="1">Approved</option>
								                    <option value="2">Rejected</option>
								            </select>
	                					</div>
	              					</div>
	              				</div>
						   </div>
						</div>
						<div class="card-footer" align="center"  >
							<input type="reset" class="btn btn-success btn-sm" value="Clear">   
		              		<i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm" onclick="Search();" value="Search">
		              	</div>
					</div>
					 
	        	</div>
			</div>
			<div class="container">
				<div class="col-md-12" id="table" style="display: none;">
					<div class="container" align="center">
						<table id="SearchReport" class="table no-margin table-striped  table-hover  table-bordered">
							<thead style="background-color: #9c27b0; color: white;">
								<tr>
									<th style="font-size: 15px"> Arm</th>
									<th style="font-size: 15px">Arm Code</th>
									<th style="font-size: 15px">Action</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="item" items="${list}" varStatus="num" >
									<tr>
										<td style="width: 50%;">${item.arm_desc}</td>
										<td style="width: 20%;">${item.arm_code}</td>
										<td style="width: 30%;">${item.status}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>		
</form>

	<c:url value="search_arm_code1" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="code_value1">
		<input type="hidden" name="arm_desc1" id="arm_desc1" value="0"/>
		<input type="hidden" name="arm_code1" id="arm_code1" value="0"/>
		<input type="hidden" name="status1" id="status1" value="0"/>
	</form:form> 

	<c:url value="ApprovedArmUrl" var="appUrl" />
	<form:form action="${appUrl}" method="post" id="appForm" name="appForm" modelAttribute="appid">
		<input type="hidden" name="appid" id="appid" value="0"/>
	</form:form> 
	
	<c:url value="rejectArmUrl" var="rejectUrl" />
	<form:form action="${rejectUrl}" method="post" id="rejectForm" name="rejectForm" modelAttribute="rejectid">
		<input type="hidden" name="rejectid" id="rejectid" value="0"/>
	</form:form> 
	
	<c:url value="deleteArmUrl" var="deleteUrl" />
	<form:form action="${deleteUrl}" method="post" id="deleteForm" name="deleteForm" modelAttribute="deleteid">
		<input type="hidden" name="deleteid" id="deleteid" value="0"/>
	</form:form> 
	
	<c:url value="updateArmUrl" var="updateUrl" />
	<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid">
		<input type="hidden" name="updateid" id="updateid" value="0"/>
	</form:form>

<script>
	$(document).ready(function() {
		$("#arm_desc").val('${arm_desc1}');
		$("#arm_code").val('${arm_code1}');
		
		if('${status1}' != ""){
			$("#status").val('${status1}');
		}
		
		if('${list.size()}' != "0" && '${list[0].arm_desc}' != "" ){
			$("#table").show();
		}else{
			$("#table").hide();
		}
	});

  	function Search(){
  		var arm_desc = $("#arm_desc").val();
    	var arm_code = $("#arm_code").val();
    	var status = $("#status").val();
  		
		$("#arm_desc1").val($("#arm_desc").val());
      	$("#arm_code1").val($("#arm_code").val());
      	$("#status1").val($("#status").val());
      	$("#WaitLoader").show();
      	document.getElementById('searchForm').submit();
	}
	
  	function Approved(id){
  		document.getElementById('appid').value=id;
   		document.getElementById('appForm').submit();
	}
   	   
	function Reject(id){
   		document.getElementById('rejectid').value=id;
   		document.getElementById('rejectForm').submit();
	}
   	   
	function Delete1(id){
   		document.getElementById('deleteid').value=id;
		document.getElementById('deleteForm').submit();
	}
   	   
	function Update(id){
    	document.getElementById('updateid').value=id;
		document.getElementById('updateForm').submit();
	}
</script>
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
</script>