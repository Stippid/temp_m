<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link rel="stylesheet" href="js/cue/cueWatermark.css">
	<div class="animated fadeIn">
    	<div class="col-md-12" align="center">
    		<div class="card">
   				<div class="card-header"><h5>APPROVE MONTHLY VEHICLE CASUALTY RETURN</h5></div>
      				<div class="card-body">
      					<div class="col-md-12">	              					
             				<div class="col-md-6">
            						<div class="col col-md-4">
			                	<label class=" form-control-label">SUS No</label>
			                </div>
			                <div class="col-12 col-md-8">
			                  <input type="text" id="sus_no" name="sus_no" placeholder="" class="form-control autocomplete" autocomplete="off" value="${sus_no}" readonly="readonly"> 
			                </div>
				        </div>
             				<div class="col-md-6">
            						<div class="col col-md-4">
               							<label class=" form-control-label">Unit Name</label>
             						</div>
             						<div class="col-12 col-md-8">
								<textarea id="unit_name" name="unit_name" placeholder="" class="form-control autocomplete" style="font-size: 11px;" autocomplete="off" readonly="readonly">${unit_name}</textarea>
							</div>  
             				</div>
             			</div>
				</div> 
				<div class="card-body">
				<div align="right"><h6>Total Count : ${getMVCRReportList.size()}</h6></div>
					<table id="SearchReport" class="table no-margin table-striped  table-hover  table-bordered report_print">
						<thead align="center">
						 	<tr>
								<th style="width: 5%;">Ser No</th>
								<th style="width: 15%;">BA NO</th>
								<th style="text-align: center;">Previous KM </th>
								<th style="text-align: center;">Present KM </th>
								<th style="text-align: center;">Cl</th>
								<th style="text-align: center;">UNSV</th>
								<th style="width: 33%;">Remarks</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${getMVCRReportList}" var="list"  varStatus="num" >
								<c:if test="${list[5] == '1'}" >
								<tr style="color:red;">
								    <td style="width: 5%;" align="center">${num.index+1}</td> 
								  	<td style="text-align: center;width: 15%;">${list[0]}</td>	
									<td style="text-align: center;">${list[1]}</td>
									<td style="text-align: center;">${list[1]}</td>
									<td style="text-align: center;">${list[2]}</td>
									<td style="text-align: center;">${list[3]}</td>
									<td style="text-align: center;width: 33%;">${list[4]}</td>	
								</tr>
								</c:if>
								<c:if test="${list[5] != '1'}" >
								<tr>
								    <td style="width: 5%;" align="center">${num.index+1}</td> 
								  	<td style="text-align: center;width: 15%;">${list[0]}</td>	
									<td style="text-align: center;">${list[1]}</td>
									<td style="text-align: center;">${list[1]}</td>
									<td style="text-align: center;">${list[2]}</td>
									<td style="text-align: center;">${list[3]}</td>
									<td style="text-align: center;width: 33%;">${list[4]}</td>	
								</tr>
								</c:if>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="card-footer" align="center">
					<jsp:include page="../../admin/approve_line.jsp" ></jsp:include>
					<a href="#" class="btn btn-danger btn-sm" onclick="Reject();">Reject </a>
					<input type="submit" class="btn btn-success btn-sm" value="Approve" onclick="Approved();">  
				</div>
			</div>
		</div>
	</div>

	<c:url value="ApprovedmvcrUrl" var="appUrl" />
	<form:form action="${appUrl}" method="post" id="appForm" name="appForm" modelAttribute="sus_no2">
		<input type="hidden" name="sus_no2" id="sus_no2" value="0"/>
		<input type="hidden" name="unit_name1" id="unit_name1" />
		
	</form:form> 
	
	<c:url value="rejectmvcrUrl" var="rejectUrl" />
	<form:form action="${rejectUrl}" method="post" id="rejectForm" name="rejectForm" modelAttribute="sus_no3">
		<input type="hidden" name="sus_no3" id="sus_no3" value="0"/>
	</form:form> 
	

<script>

$(document).ready(function() {
	${con}
}); 

function Approved(){
   	sus = document.getElementById("sus_no").value;
   	document.getElementById('sus_no2').value=sus;
	document.getElementById('unit_name1').value = '${unit_name}';
	document.getElementById('appForm').submit();
}
function Reject(){
   	susr = document.getElementById("sus_no").value;
	document.getElementById('sus_no3').value=susr;
	document.getElementById('rejectForm').submit();
}
   
</script>