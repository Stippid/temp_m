<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%> 

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js"></script>
<script src="js/cue/printAllPages.js" type="text/javascript"></script>


<form:form name="view_avn_daily_Receipt_Report" id="view_avn_daily_Receipt_Report" action="" method="post" class="form-horizontal" commandName="view_avn_daily_Receipt_ReportCMD"> 
	<div class="animated fadeIn">
	    	<div class="container" align="center">
	    		<div class="card">
	    			<div class="card-header" style="border: 1px solid rgba(0,0,0,.125); text-align: center;"><h5>AVN DAILY RECEIPT REPORT DETAILS</h5></div>  
	    			<div class="card-body card-block">
	    			    <div class="col-md-12">
	         					 <div class="col-md-6">
	         					 		<div class="row form-group">	
    			         					<div class="col col-md-4">
									   			<label for="text-input" class="form-control-label"><strong>Agency name</strong></label>									   
											</div>
										<div class="col-12 col-md-8">
                  					  		<label>${agency_name}</label>
                		        		</div>
                		  			  </div>
	          				     </div>
	                             <div class="col-md-6">
	                             	  <div class="row form-group">	
	    			                      <div class="col col-md-4">
									         <label for="text-input" class="form-control-label"><strong>DRR/DIR Ser No</strong></label>									   
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
									         <label for="text-input" class="form-control-label"><strong>Unit Name</strong></label>
									      </div>
								         <div class="col-12 col-md-8">
                  					        <label> ${unit_name} </label>
                		                 </div>
                		              </div>    
	          				     </div> 
	                             <div class="col-md-6">
	                             		 <div class="row form-group">	
    			         		           <div class="col col-md-4">
									           <label for="text-input" class="form-control-label"><strong>Status</strong></label>
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
			        				<jsp:include page="../../admin/approve_line.jsp" ></jsp:include>
							   		<button type="button" class="btn btn-success btn-sm" onclick="Approved('${sus_no}','${viewSerNo}','${agency_name}','${tail_no}')" >Approve All</button>
								</c:if>
							</c:if> 							 
							<a href="search_avn_daily_receipt_report" type="reset"
						class="btn btn-success">  Back  </a>
					    </div> 	   
					</div>
				</div>
    		
  		</div> 
  </form:form>
  
  <div class="container" align="center">
	<div id="tableshow">
			<table id="dtBasicExample" class="table no-margin table-striped  table-hover  table-bordered report_print">
				
				<thead>
					<tr style="text-align:center;">
						<th class="th-sm" style="text-align:center;">Tail No</th> 
						<th class="th-sm" style="text-align:center;">SUS No</th>
						<th class="th-sm" style="text-align:center;">Unit Name</th>
						<th class="th-sm" style="text-align:center;">Action</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${list.size() == 0}" >
						<tr>
							<td colspan="7" align="center" style="color: red;"> Data Not Available </td>
						</tr>
					</c:if>
			    <c:forEach items="${view_avn_daily_Receipt_ReportCMD}" var="i">
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

<c:url value="AVNApprovedReceiveIssue" var="appUrl" />
	<form:form action="${appUrl}" method="post" id="appForm" name="appForm" modelAttribute="sus_no_aprove">
		<input type="hidden" name="sus_no_aprove" id="sus_no_aprove"/> 
		<input type="hidden" name="ser_no_approve" id="ser_no_approve" />
		<input type="hidden" name="agency_name" id="agency_name"/>
		<input type="hidden" name="tail_no" id="tail_no"/>
		 <input type="hidden" name="viewStatus11" id="viewStatus11"/>
	 	 <input type="hidden" name="viewSus1" id="viewSus1"/>
	  	<input type="hidden" name="viewfrom_dt1" id="viewfrom_dt1"/>
	  	<input type="hidden" name="viewto_dt1" id="viewto_dt1"/>
		<input type="hidden" name="search_status" id="1"/>
	</form:form> 
	
<c:url value="AVNUpdateReceiveIssue" var="updateUrl" />
	<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid">
		<input type="hidden" name="updateid" id="updateid" value="0"/>
</form:form>
 
<c:url value="AVNDeleteReceiveReport" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm" name="deleteForm" modelAttribute="deleteid">
      <input type="hidden" name="deleteid" id="deleteid" value="0"/>
	  <input type="hidden" name="drr_ser_no" id="drr_ser_no"/>
	  <input type="hidden" name="tail_no" id="tail_no"/>
	  <input type="hidden" name="unit_sus_no" id="unit_sus_no"/>
</form:form>  
<script>

function Approved(sus,ser,rec,ba){

	document.getElementById('sus_no_aprove').value=sus;
	document.getElementById('ser_no_approve').value=ser;
	document.getElementById('agency_name').value=rec;
	document.getElementById('tail_no').value=ba;  
	
	document.getElementById('viewStatus11').value='${s_viewStatus1}';
	document.getElementById('viewSus1').value='${search_sus_no}';
	document.getElementById('viewfrom_dt1').value='${s_viewfrom_dt}';
	document.getElementById('viewto_dt1').value='${s_viewto_dt}';
	
	document.getElementById('appForm').submit();
}

function Update(id){
	   document.getElementById('updateid').value=id;
	   document.getElementById('updateForm').submit();
}

 function Delete1(id,rec,iss,ba,sus){
	
	document.getElementById('deleteid').value=id;
	document.getElementById('drr_ser_no').value=rec;
	document.getElementById('tail_no').value=ba;
	document.getElementById('unit_sus_no').value=sus;
	document.getElementById('deleteForm').submit();   
} 

 
</script>
 
<script>
       $(document).ready(function() {
    	
    	   $("label#susSlotId").text('${sus_no}');  
    	   $("label#serNo").text('${viewSerNo}');
    	  
    	   var a = '${classification}';
    	   console.log("aclassification val -->" + a);
    	   
    	   if(a == 3){
    		   $("label#statusId").text('Receive from Unit');
   		   }
   	  	   else{
   			   $("label#statusId").text('');
   	  	   }
    	   try{
    		   if(window.location.href.includes("?")){
    			   var url = window.location.href.split("?")[0];
    			   window.location = url;
    		   }
    	   }catch (e) {
    	   } 
    	      	     	   
       });
   </script>
