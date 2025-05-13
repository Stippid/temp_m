<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/cue/cueWatermark.css">

<form:form name="cveh_daily_receipt_issue_report_view" id="cveh_daily_receipt_issue_report_view" action="" method="post" class="form-horizontal" commandName="cveh_daily_receipt_issue_report_viewCMD"> 
	<div class="animated fadeIn">
	    	<div class="container" align="center">
	    		<div class="card">
	    		<div class="card-header" style="border: 1px solid rgba(0,0,0,.125); text-align: center;"><h5>View C - Vehicle Daily Receipt / Issue Report</h5></div>  
	   		      <div class="card-header"><strong>Basic Details</strong></div>
	    			<div class="card-body card-block">
	    				<div class="col-md-12">
		          				<div class="col-md-6">
		          				<div class="row form-group">	
	    			         		<div class="col col-md-4">
										   <label for="text-input" class="form-control-label"><strong>Mother Depot SUS No </strong></label>										   
									</div>
									<div class="col-12 col-md-8">
	                  					  <label id="susSlotId"></label>
	                		        </div>
	                		    </div>
		          				</div>
		          				<div class="col-md-6">
		          				<div class="row form-group">	
		    			            <div class="col col-md-4">
										   <label for="text-input" class="form-control-label"><strong>DRR/DIR Ser No </strong></label>										   
									</div>
									<div class="col-12 col-md-8">
	                  					  <label id="serNo"></label>
	                		        </div>
	                		     </div> 
		          				</div>
	          			 </div>	    			    
	    			       <div class="col-md-12">
		          				<div class="col-md-6">
		          				 <div class="row form-group">	
	    			         		<div class="col col-md-4">
										   <label for="text-input" class="form-control-label"><strong>Mother Depot Name </strong></label>
										   
									</div>
									<div class="col-12 col-md-8">
	                  					  <label id="susNameId"></label>
	                		        </div>
	                		    </div>
		          				</div>
		          				<div class="col-md-6">
		          				<div class="row form-group">	
	    			         		<div class="col col-md-4">
										   <label for="text-input" class="form-control-label"><strong>Status </strong></label>										   
									</div>
									<div class="col-12 col-md-8">
	                  					  <label id="statusId"></label>
	                		        </div>
	                		    </div>
	          				</div>
	          			</div>    		       	    			    
	        	</div>
	        		        	
	        	
	        	<div class="form-control card-footer" align="center"> 
	        			<c:if test="${roleType == 'ALL' || roleType == 'APP'}"> 
			        	<c:if test="${status == 0}">         
					    <button type="button" class="btn btn-success btn-sm" onclick="Approved('${sus_no}','${cviewSerNo}','${issue_condition}','${em_no}')">Approve All</button> 
					    </c:if>
					 </c:if>
					<button type="button" class="btn btn-success btn-sm" onclick="return goBack();">Back</button>
			    </div> 	
			</div>
		</div>
  </div>  
</form:form>

<div class="container" align="center">
	<div id="tableshow">
			<table id="dtBasicExample" class="table no-margin table-striped  table-hover  table-bordered report_print">
				   <thead style="background-color: #9c27b0; color: white;">
							<th class="th-sm" style="text-align:center;">BA No</th> 
							<th class="th-sm" style="text-align:center;">SUS No</th>
							<th class="th-sm" style="text-align:center;">Unit Name</th>
							<th class="th-sm" style="text-align:center;">Action</th>
					</thead>
				    <tbody>
					    <c:if test="${list.size() == 0}" >
								<tr>
									<td colspan="7" align="center" style="color: red;"> Data Not Available </td>
								</tr>
						</c:if>
				    	<c:forEach items="${cveh_daily_receipt_issue_report_viewCMD}" var="i">
				    		<tr>
				    			<td style="text-align:center;">${i[1]}</td>
						    		<td style="text-align:center;">${i[3]}</td>
						    		<td style="text-align:left;">${i[7]} </td>
						    	<td >${i[8]} </td>
				    			
				    		</tr>
				    	</c:forEach>
					</tbody>
			</table>
	</div>	
</div> 

	<c:url value="CVehApprovedReceiveIssue" var="appUrl" />
	<form:form action="${appUrl}" method="post" id="cappForm" name="cappForm" modelAttribute="csus_no_aprove">
		<input type="hidden" name="csus_no_aprove" id="csus_no_aprove"/> 
		<input type="hidden" name="cser_no_approve" id="cser_no_approve" />
		<input type="hidden" name="cissue_condition" id="cissue_condition"/>
		<input type="hidden" name="emno" id="emno"/>
		
	</form:form> 
	
<c:url value="CVehUpdateReceiveIssue" var="updateUrl" />
	<form:form action="${updateUrl}" method="post" id="cupdateForm" name="cupdateForm" modelAttribute="cupdateid">
		<input type="hidden" name="cupdateid" id="cupdateid" value="0"/>
	</form:form>

<c:url value="CVehDeleteReceiveIssue" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="cdeleteForm" name="cdeleteForm" modelAttribute="cdeleteid">
      <input type="hidden" name="cdeleteid" id="cdeleteid" value="0"/>
	  <input type="hidden" name="cissue_condition_delete" id="cissue_condition_delete"/>
	  <input type="hidden" name="cemno_delete" id="cemno_delete"/>
	  <input type="hidden" name="csus_no_delete" id="csus_no_delete"/>
</form:form>  

<c:url value="BackReceiveIssue" var="backUrl" />
<form:form action="${backUrl}" method="post" id="backForm" name="backForm" modelAttribute="viewStatus1">
      <input type="hidden" name="viewStatus1" id="viewStatus1"/>
	  <input type="hidden" name="viewSus" id="viewSus"/>
	  <input type="hidden" name="viewfrom_dt" id="viewfrom_dt"/>
	  <input type="hidden" name="viewto_dt" id="viewto_dt"/>
	  <input type="hidden" name="nPara" id="nPara"/>
</form:form>  
	
<script>

function goBack() {
	document.getElementById('viewStatus1').value='${s_viewStatus1}';
	document.getElementById('viewSus').value='${s_viewSus}';
	document.getElementById('viewfrom_dt').value='${s_viewfrom_dt}';
	document.getElementById('viewto_dt').value='${s_viewto_dt}';
	document.getElementById('nPara').value='CVeh';
	document.getElementById('backForm').submit();
}
	
function Approved(sus,ser,rec,ba){
	
	
	document.getElementById('csus_no_aprove').value=sus;
	document.getElementById('cser_no_approve').value=ser;
	document.getElementById('cissue_condition').value=rec;
	document.getElementById('emno').value=ba;
	document.getElementById('cappForm').submit();
}

function Update(id){
	   document.getElementById('cupdateid').value=id;
	   document.getElementById('cupdateForm').submit();
}

function Delete1(id,rec,iss,ba,sus){
	document.getElementById('cdeleteid').value=id;
	document.getElementById('cissue_condition_delete').value=iss;
	document.getElementById('cemno_delete').value=ba;
	document.getElementById('csus_no_delete').value=sus;
	document.getElementById('cdeleteForm').submit();   
} 
</script>

<script>
       $(document).ready(function() {
    	 
    	   $("label#susSlotId").text('${sus_no}');  
    	   $("label#serNo").text('${cviewSerNo}');
    	   $("label#susNameId").text('${unit_name}');
    	 
    	   var a = '${issue_condition}';
    	   
    	   if(a == 1){
    		   $("label#statusId").text('Free Stock');
   		   }else if(a == 2){
   			   $("label#statusId").text('Issued To Unit');
   		   }else if(a == 3){
   			   $("label#statusId").text('Received From Unit');
   		   }else if(a == 4){
   			   $("label#statusId").text('Auctioned');
   	  	   }
    	   
    	   var sus_no = '${sus_no}'   	   
       });
   </script>
