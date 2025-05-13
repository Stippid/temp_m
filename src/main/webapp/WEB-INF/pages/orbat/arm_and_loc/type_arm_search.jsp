<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>

<form action="" method="post" class="form-horizontal"> 
<div class="animated fadeIn">
	<!-- <div class="row"> -->
    	<div class="container" align="center">
    		<div class="card">
          		<div class="card-header"> <h5>SEARCH TYPE OF ARM</h5> </div>
         		<div class="card-body card-block">
           			<div class="col-md-12">
						<div class="col-md-6">
             				<div class="row form-group">
               					<div class="col col-md-5">
               						<label class=" form-control-label">Parent Arm</label>
               					</div>
               					<div class="col-12 col-md-7">
               						<select name="parent_arm" id="parent_arm" class="form-control-sm form-control">
               							<option value="0">--Select--</option>
               							<c:forEach var="item" items="${getPrantArmList}" varStatus="num" >
               								<option value="${item.code}">${item.code} - ${item.code_value}</option>
               							</c:forEach> 
               						</select>
               					</div>
             				</div>
             			</div>
             		</div>
         			<div class="col-md-12">
         				<div class="col-md-6">
          					<div class="row form-group">
            					<div class="col col-md-5">
              						<label class=" form-control-label">Type of Arm</label>
            					</div>
            					<div class="col-12 col-md-7">
              						<input type="text" id="code_value" name="code_value" maxlength="400" class="form-control" autocomplete="off">
            					</div>
          					</div>
          				</div>
          				<div class="col-md-6">
            				<div class="row form-group">
             					<div class="col col-md-5">
               						<label class=" form-control-label">Type of Arm Code</label>
             					</div>
             					<div class="col-12 col-md-7">
	               					<input type="text" id="code" name="code" maxlength="2" class="form-control" autocomplete="off">
    	         				</div>
        	   				</div>
					    </div>
         			</div>
            		<div class="col-md-12">
         				<div class="col-md-6">	
            				<div class="row form-group">
              					<div class="col col-md-5">
                						<label class=" form-control-label">Status</label>
              					</div>
              					<div class="col-12 col-md-7">
                						<select name="status_record" id="status_record" class="form-control-sm form-control">
					                    <option value="0">Pending</option>
					                    <option value="1">Approved</option>
					                    <option value="2">Rejected</option>
					            	</select>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="card-footer" align="center">
					<input type="reset" class="btn btn-success btn-sm" value="Clear">   
	        		<i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm" onclick="Search();" value="Search"> 
	        		
	    		</div>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="col-md-12" id="table" style="display: none;">
			<div class="container" align="center">
				<table id="AttributeReport report_print" class="table no-margin table-striped  table-hover  table-bordered" >
					<thead style="background-color: #9c27b0; color: white;">
						<tr>
							<th style="width: 20%;">Type of Arm Code</th>
							<th style="width: 50%;">Type of Arm</th>
							<th style="width: 30%;">ACTION</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${list}" varStatus="num" >
							<tr>
								<td style="width: 20%;">${item.code}</td>
								<td style="width: 50%;">${item.code_value}</td>
								<td style="width: 30%;">${item.status_record}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	<!-- </div> -->
</div>
</form>
	<c:url value="search_type_arm1" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="code_value1">
		<input type="hidden" name="code_value1" id="code_value1" value="0"/>
		<input type="hidden" name="code1" id="code1" value="0"/>
		<input type="hidden" name="status_record1" id="status_record1" value="0"/>
		<input type="hidden" name="parent_arm1" id="parent_arm1" value="0"/>
	</form:form> 

	<c:url value="ApprovedTypeOfArmUrl" var="appUrl" />
	<form:form action="${appUrl}" method="post" id="appForm" name="appForm" modelAttribute="appid">
		<input type="hidden" name="appid" id="appid" value="0"/>
	</form:form> 
	
	<c:url value="rejectTypeOfArmUrl" var="rejectUrl" />
	<form:form action="${rejectUrl}" method="post" id="rejectForm" name="rejectForm" modelAttribute="rejectid">
		<input type="hidden" name="rejectid" id="rejectid" value="0"/>
	</form:form> 
	
	<c:url value="deleteTypeOfArmUrl" var="deleteUrl" />
	<form:form action="${deleteUrl}" method="post" id="deleteForm" name="deleteForm" modelAttribute="deleteid">
		<input type="hidden" name="deleteid" id="deleteid" value="0"/>
	</form:form> 
	
	<c:url value="updateTypeOfArmUrl" var="updateUrl" />
	<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid">
		<input type="hidden" name="updateid" id="updateid" value="0"/>
	</form:form>

<script>
$(document).ready(function() {
	$("#code_value").val('${code_value1}');
	$("#code").val('${code1}');


	if('${parent_arm1}' != ""){
		$("#parent_arm").val('${parent_arm1}');
	}
	if('${status_record1}' != ""){
		$("#status_record").val('${status_record1}');
	}
	
	if('${list.size()}' != "0" && '${list[0].code}' != "" ){
		$("#table").show();
	}else{
		$("#table").hide();
	}
});

function Search(){
	$("#code_value1").val($("#code_value").val());
	$("#code1").val($("#code").val());
	$("#status_record1").val($("#status_record").val());
	$("#parent_arm1").val($("#parent_arm").val()); 
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

