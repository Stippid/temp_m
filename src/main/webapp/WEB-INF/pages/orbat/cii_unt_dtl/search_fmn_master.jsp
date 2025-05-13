<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<form action="" method="post" class="form-horizontal"> 
<div class="animated fadeIn">
	<div class="container" align="center">
    	<div class="">
    		<div class="card">
          		<div class="card-header"> <h5>SEARCH Formation</h5> </div>
         		<div class="card-body card-block">
           			<div class="col-md-12">
           			
           				<div class="col-md-6">
            				<div class="row form-group">
             					<div class="col col-md-5">
               						<label class=" form-control-label">Formation Name</label>
             					</div>
             					<div class="col-12 col-md-7">
	               					<input type="text" id="fmn_name" name="fmn_name"  class="form-control" autocomplete="off" value="${fmn_name1}">
    	         				</div>
        	   				</div>
					    </div>
         				
          				<div class="col-md-6">
            				<div class="row form-group">
             					<div class="col col-md-5">
               						<label class=" form-control-label">Formation Code</label>
             					</div>
             					<div class="col-12 col-md-7">
	               					<input type="text" id="fmn_code" name="fmn_code"  pattern=".{2,}" class="form-control" autocomplete="off" value="${fmn_code1}">
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
					<a href="search_fmn_master" class="btn btn-success btn-sm" >Clear</a>   
	        		<i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm" onclick="Search();" value="Search"> 
	        		
	    		</div>
			</div>
		</div>
	</div>
	<div class="container" align="center" >
		<div class="row">
			<div class="col-md-12" id="table" style="display: none;">
				<div class="container" align="center">
				
					<table id="AttributeReport" class=" table-striped  table-hover  table-bordered" >
					
						<thead style="background-color: #9c27b0; color: white;">
							<tr>
								<th style="font-size: 15px;width: 3%;">Ser No</th>
								<th style="font-size: 15px;width: 5%;">FMN CODE</th>
								<th style="font-size: 15px;width: 10%;">FMN NAME</th>
								<th style="font-size: 15px;width: 7%;">LEVEL 1</th>
								<th style="font-size: 15px;width: 7%;">LEVEL 2</th>
								<th style="font-size: 15px;width: 7%;">LEVEL 3</th>
								<th style="font-size: 15px;width: 7%;">LEVEL 4</th>
								<th style="font-size: 15px;width: 7%;">LEVEL 5</th>
								<th style="font-size: 15px;width: 7%;">LEVEL 6</th>
								<th style="font-size: 15px;width: 7%;">LEVEL 7</th>
								<th style="font-size: 15px;width: 7%;">LEVEL 8</th>
								<th style="font-size: 15px;width: 7%;">LEVEL 9</th>
								<th style="font-size: 15px;width: 7%;">LEVEL 10</th>
								<th style="font-size: 15px;width: 12%;">ACTION</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${list}" varStatus="num" >
								<tr>
									<th style="font-size: 15px;width: 3%;">${num.index + 1}</th>
									<td style="font-size: 15px;width: 5%;">${item.fmn_code} </td>
									<td style="font-size: 15px;width: 10%;">${item.fmn_name} </td>
									<td style="font-size: 15px;width: 7%;">${item.level1}</td>
									<td style="font-size: 15px;width: 7%;">${item.level2}</td>
									<td style="font-size: 15px;width: 7%;">${item.level3}</td>
									<td style="font-size: 15px;width: 7%;">${item.level4}</td>
									<td style="font-size: 15px;width: 7%;">${item.level5}</td>
									<td style="font-size: 15px;width: 7%;">${item.level6}</td>
									<td style="font-size: 15px;width: 7%;">${item.level7}</td>
									<td style="font-size: 15px;width: 7%;">${item.level8}</td>
									<td style="font-size: 15px;width: 7%;">${item.level9}</td>
									<td style="font-size: 15px;width: 7%;">${item.level10}</td>
									<td style="font-size: 15px;width: 12%;">${item.status_record}</td>
								</tr>
								
								
							</c:forEach>
						</tbody>
					</table>
				
				</div>
			</div>
		</div>
	</div>
</div>
</form>

	<c:url value="search_fmn1" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="code_value1">
		<input type="hidden" name="fmn_code1" id="fmn_code1" value="0"/>\
		<input type="hidden" name="fmn_name1" id="fmn_name1" value="0"/>
		<input type="hidden" name="status_record1" id="status_record1" value="0"/>
	</form:form> 

	<c:url value="ApprovedFMNUrl" var="appUrl" />
	<form:form action="${appUrl}" method="post" id="appForm" name="appForm" modelAttribute="appid">
		<input type="hidden" name="appid" id="appid" value="0"/>
	</form:form> 
	
	<c:url value="rejectFMNUrl" var="rejectUrl" />
	<form:form action="${rejectUrl}" method="post" id="rejectForm" name="rejectForm" modelAttribute="rejectid">
		<input type="hidden" name="rejectid" id="rejectid" value="0"/>
	</form:form> 
	
	<c:url value="deleteFMNUrl" var="deleteUrl" />
	<form:form action="${deleteUrl}" method="post" id="deleteForm" name="deleteForm" modelAttribute="deleteid">
		<input type="hidden" name="deleteid" id="deleteid" value="0"/>
	</form:form> 
	
	<c:url value="updateFMNUrl" var="updateUrl" />
	<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid">
		<input type="hidden" name="updateid" id="updateid" value="0"/>
	</form:form>

<script>
$(document).ready(function() {
	
	$("#code_value").val('${code_value1}');
	$("#fmn_code").val('${fmn_code1}');
	$("#fmn_name").val('${fmn_name1}');
	
	if('${status_record1}' != ""){
		$("#status_record").val('${status_record1}');
	}
	
	if('${list.size()}' != "0" && '${list[0].fmn_code}' != "" ){
		$("#table").show();
	}else{
		$("#table").hide();
	}
});

function Search(){
	$("#fmn_code1").val($("#fmn_code").val());
	$("#fmn_name1").val($("#fmn_name").val());
    $("#status_record1").val($("#status_record").val());
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
	console.log("Update id + " , id)
	document.getElementById('updateid').value=id;     		   
	document.getElementById('updateForm').submit();
}    
</script>

