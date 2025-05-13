<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link rel="stylesheet" href="js/cue/cueWatermark.css">
	<div class="animated fadeIn">
    	<div class="col-md-12" align="center">
    		<div class="card">
   				<div class="card-header"><h5>APPROVE DAILY AVIATION CASUALTY RETURN</h5></div>
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
				<div align="right"><h6>Total Count : ${getDACRReportList.size()}</h6></div>
					<table id="SearchReport" class="table no-margin table-striped  table-hover  table-bordered report_print">
						<thead  align="center">
									<tr>
										<th  rowspan="2" style="width: 4%;">Sr No</th>
										<th  rowspan="2" style="width: 6%;">AC No</th>
										<th  rowspan="2" style="width: 6%;">Status</th>
										<th  rowspan="2" style="width: 8%;">Locations</th>
										<th  colspan="2" style="width: 12%;">Daily Flg Hrs as on</th>
										<th  rowspan="2" style="width: 6%;">G/Run</th>
										<th  rowspan="2" style="width: 6%;">AF Hrs</th>
										<th  rowspan="2" style="width: 6%;">ENG Ser No<br><span>------</span><br>ENG Hrs</th>
										<th  rowspan="2" style="width: 6%;">HRS Left</th>
										<th  rowspan="2" style="width: 6%;">DAYS Left</th>
										<th  rowspan="2" style="width: 6%;">Next INSP</th>
										<th  colspan="4" style="width: 24%;">Prog Hrs Flown</th>
										<th  rowspan="2" style="width: 8%;">Remarks</th>
							<!-- 			<th  rowspan="2" style="width: 6%;">Balance Ah Eng Hrs</th> -->
										<th  rowspan="2" style="width: 8%;">DT Last Flown</th>
											<th  rowspan="2" style="width: 8%;">PDC</th>
		
									</tr>
									<tr>
										<th  style="width: 6%;">Day</th>
										<th  style="width: 6%;">Night</th>
										<th  style="width: 6%;">Monthly</th>
										<th  style="width: 6%;">Qtrly</th>
										<th  style="width: 6%;">H/Yrly</th>
										<th  style="width: 6%;">Yrly</th>
									</tr>
								</thead>
						<tbody>
							<c:forEach items="${getDACRReportList}" var="list"  varStatus="num" >
								<tr>
											<td style="width: 4%;">${num.index + 1}</td>
											<td style="width: 6%;">${list[1]}</td>
											<td style="width: 6%;">${list[2]}</td>
											<td style="width: 8%;">${list[19]}</td>
											<td style="width: 6%;">${list[3]}</td>
											<td style="width: 6%;">${list[4]}</td>
											<td style="width: 6%;">${list[5]}</td>
											<td style="width: 6%;">${list[6]}</td>
											<td style="width: 6%;">${list[16]}<br><span>------</span><br>${list[7]}</td>
											<td style="width: 6%;">${list[8]}</td>
											<td style="width: 6%;">${list[17]}</td>
											<td style="width: 6%;">${list[9]}</td>
											<td style="width: 6%;">${list[10]}</td>
											<td style="width: 6%;">${list[11]}</td>
											<td style="width: 6%;">${list[12]}</td>
											<td style="width: 6%;">${list[13]}</td>
											<td style="width: 8%;">${list[14]}</td>
											<td style="width: 8%;">${list[15]}</td>
											<td style="width: 8%;">${list[18]}</td>
										</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="card-footer" id='aprrove_section' align="center">
			<%-- 	<jsp:include page="../../admin/approve_line.jsp" ></jsp:include> --%>
					<span>I certify that the above data are true to the best of my knowledge.</span><br><br>					
					<a href="#" class="btn btn-danger btn-sm" onclick="Reject();">Reject </a>
					<input type="submit" class="btn btn-success btn-sm" value="Approve" onclick="Approved();">  
				</div>
			</div>
		</div>
	</div>

	<c:url value="Approvedrpasdacr" var="appUrl" />
	<form:form action="${appUrl}" method="post" id="appForm" name="appForm" modelAttribute="sus_no2">
		<input type="hidden" name="sus_no2" id="sus_no2" value="0"/>
		<input type="hidden" name="unit_name1" id="unit_name1" />
		<input type="hidden" name="issue_date1" id="issue_date1" />	
	</form:form> 
	
	<c:url value="rejectrpasdacr" var="rejectUrl" />
	<form:form action="${rejectUrl}" method="post" id="rejectForm" name="rejectForm" modelAttribute="sus_no3">
		<input type="hidden" name="sus_no3" id="sus_no3" value="0"/>
	</form:form> 
<script>

$(document).ready(function() {
	${con}
	document.addEventListener('dragenter', function(event) {
	       event.preventDefault();
	   });

	   document.addEventListener('dragover', function(event) {
	       event.preventDefault();
	   });

	   document.addEventListener('drop', function(event) {
	       event.preventDefault();
	   });
}); 

function Approved(){
   	sus = document.getElementById("sus_no").value;
   	document.getElementById('sus_no2').value=sus;
	document.getElementById('unit_name1').value = '${unit_name}';
	document.getElementById('issue_date1').value = '${asonDate}';
	document.getElementById('appForm').submit();
}
function Reject(){
   	susr = document.getElementById("sus_no").value;
	document.getElementById('sus_no3').value=susr;
	document.getElementById('rejectForm').submit();
}
   
</script>