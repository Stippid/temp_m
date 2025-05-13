<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
	<div class="animated fadeIn">
		<div>
	    	<div class="container" align="center">
	    		<div class="card">
	    			<div class="card-header" style="border: 1px solid rgba(0,0,0,.125);"> <h5> B - Vehicle Daily Receipt / Issue Report </h5></div>  
	    			<div class="card-body card-block">
	    				<div class="col-md-12">
                        	<div class="col-md-6">
                            	<div class="row form-group">	
    			         			<div class="col col-md-6">
									   <label for="text-input" class="form-control-label"><strong>Depot SUS No </strong></label>
									</div>
									<div class="col-12 col-md-6">
                  						<label id="susSlotId"></label>
                  						<%-- <input type="text" id ="sus" value='${view_daily_ReceiptIssue_ReportCMD[0][3]}'> --%>
                		        	</div>
                		    	</div>
							</div>
                            <div class="col-md-6">
								<div class="row form-group">	
	    			            	<div class="col col-md-6">
									   <label for="text-input" class="form-control-label"><strong>DRR/DIR Ser No </strong></label>
									</div>
									<div class="col-12 col-md-6">
                  						<label id="serNo"></label>
                		        	</div>
                		     	</div> 
							</div>
						</div>
	    				<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">	
    			         			<div class="col col-md-6">
										<label for="text-input" class="form-control-label"><strong>Depot Name </strong></label>
									</div>
									<div class="col-12 col-md-6">
                  						<label id="susNameId"> ${unit_name} </label>
                		        	</div>
                		    	</div>  
							</div>
                            <div class="col-md-6">
                        		<div class="row form-group">	
	    			         		<div class="col col-md-6">
										   <label for="text-input" class="form-control-label"><strong>Status </strong></label>
										   
									</div>
									<div class="col-12 col-md-6">
	                  					  <label id="statusId"></label>
	                		        </div>
	                		    </div>  
							</div>
						</div>	    			
	        	</div>
	        	<div class="form-control card-footer" align="center"> 
	        		<c:if test="${roleType == 'ALL' || roleType == 'APP'}"> 
		        		 <c:if test="${view_daily_ReceiptIssue_ReportCMD[0][7] == 0}">
		        		 	<jsp:include page="../../admin/approve_line.jsp" ></jsp:include>
		        		 	<button class="btn btn-success btn-sm" onclick="Approved('${sus_no}','${viewSerNo}','${type_of_recv}','${type_of_issue}','${ba_no}')" >Approve All</button>   
						 </c:if>
					</c:if>
					<button type="button" class="btn btn-success btn-sm" onclick="goBack()">Back</button>
			    </div> 	
			</div>
		</div>
    </div>
  </div>  
<div class="container" align="center">
	<div id="tableshow">
		<table id="dtBasicExample" class="table no-margin table-striped table-bordered" style="border-color:black;">
	   		<thead>
				<tr style="text-align:center;">
					<th class="th-sm" style="text-align:center;">BA No</th> 
					<th class="th-sm" style="text-align:center;">SUS No</th>
					<th class="th-sm" style="text-align:center;">Unit Name</th>
					<c:if test="${roleType == 'ALL' || roleType == 'DEO'}"> 
						<th class="th-sm" style="text-align:center;">Action</th>
					</c:if>
				</tr>
			</thead>
			<tbody>
			    <c:forEach items="${view_daily_ReceiptIssue_ReportCMD}" var="i">
			    	<tr>
			    		<td style="text-align:center;">${i[1]}</td>
			    		<td style="text-align:center;">${i[3]}</td>
			    		<td style="text-align:center;">${i[8]}</td>
			    		<c:if test="${roleType == 'ALL' || roleType == 'DEO'}"> 
				    		<c:if test="${i[7] == 0}">
				    		   	<td style="text-align:center;">
				    		   		<%-- <a hreF="#" class="btn btn-default btn-xs" title="Edit Data" onclick="Update(${i[0]})"> Edit</a> --%>
				   		   			<%-- 	<a hreF="#" class="btn btn-default btn-xs" title="Delete Data" onclick="Delete1('${i[0]}','${i[5]}','${i[6]}','${i[1]}','${i[2]}')">Delete</a> --%>
				   		   			<i class="action_icons action_delete" onclick="Delete1('${i[0]}','${i[5]}','${i[6]}','${i[1]}','${i[2]}')" title="Delete data" ></i>
				   		   		</td>
				    		</c:if>
				    		<c:if test="${i[7] == 1}">
				    		   <td style="text-align:center;"> No Action For Approved </td>
				    		</c:if>
			    		</c:if>
			    	</tr>
			    </c:forEach>
			</tbody>
		</table>
	</div>	
</div> 
<c:url value="ApprovedReceiveIssue" var="appUrl" />
	<form:form action="${appUrl}" method="post" id="appForm" name="appForm" modelAttribute="sus_no_aprove">
		<input type="hidden" name="sus_no_aprove" id="sus_no_aprove"/> 
		
		<input type="hidden" name="ser_no_approve" id="ser_no_approve" />
		<input type="hidden" name="type_of_rec" id="type_of_rec"/>
		<input type="hidden" name="type_of_issue" id="type_of_issue"/>
		<input type="hidden" name="bano" id="bano"/>
		 <input type="hidden" name="viewStatus11" id="viewStatus11"/>
	  <input type="hidden" name="viewSus1" id="viewSus1"/>
	  <input type="hidden" name="viewfrom_dt1" id="viewfrom_dt1"/>
	  <input type="hidden" name="viewto_dt1" id="viewto_dt1"/>
	    <input type="hidden" name="sus1" id="sus1"/>
		
	</form:form> 	
<c:url value="UpdateReceiveIssue" var="updateUrl" />
	<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid">
		<input type="hidden" name="updateid" id="updateid" value="0"/>
    </form:form>

<c:url value="DeleteReceiveIssue" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm" name="deleteForm" modelAttribute="deleteid">
      <input type="hidden" name="deleteid" id="deleteid" value="0"/>
	  <input type="hidden" name="type_of_rec_delete" id="type_of_rec_delete"/>
	  <input type="hidden" name="type_of_issue_delete" id="type_of_issue_delete"/>
	  <input type="hidden" name="bano_delete" id="bano_delete"/>
	  <input type="hidden" name="sus_no_delete" id="sus_no_delete"/>
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
	document.getElementById('nPara').value='BVeh';
	document.getElementById('backForm').submit();
}
	
function Approved(sus,ser,rec,iss,ba){
	
	
	document.getElementById('sus_no_aprove').value=sus;
	document.getElementById('ser_no_approve').value=ser;
	document.getElementById('type_of_rec').value=rec;
	document.getElementById('type_of_issue').value=iss;
	document.getElementById('bano').value=ba;
	
	document.getElementById('viewStatus11').value='${s_viewStatus1}';
	document.getElementById('viewSus1').value='${search_sus_no}';
	document.getElementById('viewfrom_dt1').value='${s_viewfrom_dt}';
	document.getElementById('viewto_dt1').value='${s_viewto_dt}';
	document.getElementById('sus1').value='${view_daily_ReceiptIssue_ReportCMD[0][3]}';
	
	document.getElementById('appForm').submit();
}

function Update(id){
	   document.getElementById('updateid').value=id;
	   document.getElementById('updateForm').submit();
}

function Delete1(id,rec,iss,ba,sus){
	document.getElementById('deleteid').value=id;
	document.getElementById('type_of_rec_delete').value=rec;
	document.getElementById('type_of_issue_delete').value=iss;
	document.getElementById('bano_delete').value=ba;
	document.getElementById('sus_no_delete').value=sus;
	document.getElementById('deleteForm').submit();   
} 
</script>
<!-- KAJAL V4 12/11/2022 -->
<script>
    $(document).ready(function() {
 	   $("label#susSlotId").text('${sus_no}');  
 	   $("label#serNo").text('${viewSerNo}');
 	   var a = '${type_of_recv}';
 	   var b = '${type_of_issue}';
 

 	   if(a == 0 && b == 1){
 		   $("label#statusId").text('Free Stock');
		   }else if(a == 1 && b == 0){
			   $("label#statusId").text('Issue To Unit');
		   }else if(a == 0 && b == 0){
			   $("label#statusId").text('Received From Unit');
		   }else if(a == 1 && b == 1){
			   $("label#statusId").text('Auction Out');
	  	   }
    });
</script>