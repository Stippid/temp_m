<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>

<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<script type="text/javascript" src="js/printWatermark/common.js"></script>
<script src="js/printWatermark/printAllPages.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/printWatermark/cueWatermark.js" type="text/javascript"></script>
<div class="animated fadeIn">
	<div class="">
	    	<div class="container" align="center">
	    		<div class="card">
	    			<div class="card-header"><h5><b>Four Monthly Census Return</b></h5></div>
	       				<div class="card-body card-block">
	       					<div class="col-md-12">
	                             <div class="col-md-6">
	                             	<div class="row form-group">
					            	   <div class="col col-md-4">
					                	   <label class=" form-control-label">SUS No </label>
					                   </div>
					                   <div class="col-12 col-md-8">
					                       <input type="text" id="sus_no" name="sus_no" class="form-control autocomplete" autocomplete="off" value="${sus_no}"> 
					                    </div>
					          	    </div>
	          	                 </div>
	          					 <div class="col-md-6">
	          					      <div class="row form-group">
               						       <div class="col col-md-4">
                 							    <label class=" form-control-label">Unit Name </label>
               						       </div>
               						       <div class="col-12 col-md-8">
               						       <textarea id="unit_name" name="unit_name" class="form-control autocomplete" style="font-size: 11px;" value="${unit_name}" autocomplete="off"></textarea>              							  
               						       </div>
               						  </div>
	                             </div>
                            </div>	       						       					
						</div> 
	        		</div>
				</div>
			</div> 
		   </div>	
	    	<div class="col s12" align="center" id="reportshow1" style="overflow: auto;">
	    			<div class="col-md-12">
						<table id="SearchMCRReport" class="table no-margin table-striped  table-hover  table-bordered report_print" >
							<thead style="text-align: center">
								<tr>
								<th style="width: 5%;">Ser No</th>
								<th style="width: 10%;">Veh BA No</th>
								<th style="width: 10%;">MCT</th> 
								<th style="width: 10%;">Country of Origin</th>
								<th style="width: 10%;">Description</th>
								<th colspan="4" style="width: 40%;" >Vehicle Details</th>	
								<th colspan="3" style="width: 30%;" >Engine Details</th>	
								<th colspan="3" style="width: 30%;">Aux Engine Details</th>		
								<th colspan="3" style="width: 30%;">Main Gun Type</th>	
								<th  style="width: 10%;">Section Gun Type</th>
								<th colspan="3" style="width: 30%;">Main Radio Set</th>	
								<th style="width: 10%;">Remarks</th>
								<th style="width: 10%;">Action</th>
								<tr>
									<th colspan="5" style=" width: 45%;"></th>
									<th style="">Cl</th> 
									<th style="">Km Run During the Period </th> 
									<th style="">Total Km Run</th>
									<th style="">Previous Tr Km Run</th> 
									<th style="">Type </th>
									<th style="">Km Run</th>
									<th style="">Hrs Run</th> 
									<th style="">Type</th>
									<th style="">Cl</th>
									<th style="">Hrs Run</th> 
									<th style="">Type</th>
									<th style="">EFC</th>
									<th style="">QR</th> 
								    <th colspan="1"></th> 
									<th style="">Nomenclature</th>
									<th style="">UH</th>
									<th style="">Condition</th>
								    <th colspan="1" style=""></th> 
									<th  colspan="1" style=""></th> 
									
								</tr> 
								
						  
						 </thead>
							  <tbody>
							 	 <c:if test="${list.size() == 0}" >
										<tr>
											<td colspan="7" align="center" style="color: red;"> Data Not Available </td>
										</tr>
									</c:if>
								<c:forEach items="${getmcrReportList}" var="list"  varStatus="num" >
									<tr>
									    <td style="width: 5%;">${num.index+1}</td>
									   <td style="width: 10%;text-align: center;">${list[0]}</td>
									   <td style="width: 10%;text-align: center;">${list[1]}</td>
										<td style="width: 10%;text-align: center;">${list[3]}</td>
										<td style="width: 10%;text-align: center;">${list[2]}</td>
										<td style="width: 10%;text-align: center;">${list[4]}</td>	
										<td style="text-align: center;width: 10%;">${list[5]}</td>	
										<td style="text-align: center;width: 10%;">${list[6]}</td>
										 <td style="text-align: center;width: 10%;">${list[7]}</td>
										<td style="text-align: center;width: 10%;">${list[8]}</td>
										<td style="text-align: center;width: 10%;">${list[9]}</td>
										<td style="text-align: center;width: 10%;">${list[10]}</td>	
										<td style="text-align: center;width: 10%;">${list[11]}</td>										
										 <td style="text-align: center;width: 10%;">${list[12]}</td>
										<td style="text-align: center;width: 10%;">${list[13]}</td>
										<td style="text-align: center;width: 10%;">${list[14]}</td>
										<td style="text-align: center;width: 10%;">${list[15]}</td>	
										<td style="text-align: center;width: 10%;">${list[16]}</td>										
										 <td style="text-align: center;width: 10%;">${list[17]}</td>
										<td style="text-align: center;width: 10%;">${list[18]}</td>
										<td style="text-align: center;width: 10%;">${list[19]}</td>
										<td style="text-align: center;width: 10%;">${list[20]}</td>	
										<td style="text-align: center;width: 10%;">${list[21]}</td>
										<td style="text-align: center;width: 10%;">${list[24]}</td>
									</tr>
						</c:forEach>
				</tbody>
						</table>
					</div>	
					
			</div>
			
	    		<div class="col-md-12" align="center">
					<div class="form-control card-footer" align="center">
						<jsp:include page="../../admin/approve_line.jsp" ></jsp:include>
						<a href="#" class="btn btn-danger btn-sm" onclick="Reject();">Reject</a>
						<input type="submit" class="btn btn-success btn-sm" value="Approve" onclick="Approved();">  
					</div>
				</div>     
			
	    
	    
	    
	 <c:url value="mcredit" var="editurlmcr123" />
	<form:form action="${editurlmcr123}"  method="post" id="editForm" name="editForm" modelAttribute="editId">
		<input type="hidden" name="editId" id="editId" value="0"/>
	</form:form> 
	

	
	<c:url value="rejectmcrUrl" var="rejectUrl" />
	<form:form action="${rejectUrl}" method="post" id="rejectForm" name="rejectForm" modelAttribute="sus">
		<input type="hidden" name="sus" id="sus"/>
	</form:form> 
	
	<c:url value="ApprovedmcrUrl" var="appUrl" />
	<form:form action="${appUrl}" method="post" id="appForm" name="appForm" modelAttribute="sus3">
		<input type="hidden" name="sus3" id="sus3" />
		<input type="hidden" name="unit_name1" id="unit_name1" />
	</form:form> 

<script>
$(document).ready(function() {
	
	var sus_no = '${sus_no}';
   	var unit_name = '${unit_name}';  

   	$("#unit_name").val(unit_name);
  
});

	function open1(id)
	{  
		document.getElementById("editId").value=id;	
		document.getElementById("editForm").submit();
	}  
	
	function Reject(){
		susno = document.getElementById("sus_no").value;
	    document.getElementById('sus').value=susno;
		document.getElementById('rejectForm').submit();
   }
	
	function Approved(){
		s1 = document.getElementById("sus_no").value;
	    document.getElementById('sus3').value=s1;
	    document.getElementById('unit_name1').value = $("#unit_name").val();
		document.getElementById('appForm').submit();
  }

</script>

