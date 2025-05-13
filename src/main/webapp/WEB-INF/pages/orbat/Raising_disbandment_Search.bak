<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %>
<%@ taglib prefix="dandelion" uri="http://github.com/dandelion" %>  <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="dandelion" uri="http://github.com/dandelion" %>
<dandelion:asset cssExcludes="datatables"/> 
<dandelion:asset jsExcludes="datatables"/> 
<dandelion:asset jsExcludes="jquery"/> 

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script> <script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script> <script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
<link href="js/JS_CSS/jquery.dataTables.min.css" rel="stylesheet">
<script src="js/JS_CSS/jquery.dataTables.js"></script>
<script src="js/miso/orbatJs/ChkDateLessThan.js" type="text/javascript"></script>
<script>

var username="${username}";
console.log(username);
</script>

<style type="text/css">
	table.dataTable, table.dataTable th, table.dataTable td {
		-webkit-box-sizing: content-box;
		-moz-box-sizing: content-box;
		box-sizing: content-box;
		text-align: center;
	}
	.dataTables_scrollHead {
		overflow-y:scroll !important;
		overflow-x:hidden !important;
	}
	.DataTables_sort_wrapper{
		//width : 80px; 
	}
	table.dataTable tr.odd {
  			background-color: #f0e2f3;
	} 
	table.dataTable thead {
  			background-color: #9c27b0;
  			color: white;
	}
	.dataTables_paginate.paging_full_numbers{
		margin-top: 0.755em;
	} 
	.dataTables_paginate.paging_full_numbers a{
		background-color: #9c27b0;
		border: 1px solid #9c27b0;
		color: #fff;
		border-radius: 5px;
		padding: 3px 10px;
		margin-right: 5px;
	}
	.dataTables_paginate.paging_full_numbers a:hover{
		background-color: #cb5adf;
		border-color: #cb5adf;
	}
	.dataTables_info{
		color:#9c27b0 !important;
		font-weight: bold;
	}
	.print_btn input{
	  background-color: #9c27b0;
         border-color: #9c27b0;
	} 
</style>
	
<form:form action="" method="post"  class="form-horizontal"> 
	<div class="animated fadeIn">
		<div class="container" align="center">
	    	<div class="row">
	    		<div class="card">
	          		<div class="card-header"><h5><b>SEARCH SCHEDULE</b><br></h5><h6><span style="font-size:12px;color:red;">(To be entered by SD Dte / MISO)</span></h6><strong>Schedule Details</strong> </div>
	          			<div class="card-body">
            				<div class="col-md-12">
	            				<div class="col-md-6">
									<div class="row form-group">
	                					<div class="col col-md-4">
	                  						<label class=" form-control-label"><strong style="color: red;">*</strong> Schedule</label>
	                					</div>
	                					<div class="col-12 col-md-8">
	                 						<select name="scenario" id="scenario" class="form-control-sm form-control" onchange="hideReport();">
	                 							<option value="">--Select--</option>
 												<c:if test="${username == 'app_sd4'}">
			                                    <c:forEach var="item" items="${getType_Of_LetterList_final}" varStatus="num" >
	                  								<option value="${item.codevalue}" name="${item.label}" >${item.label}</option>
	                  							</c:forEach>
	                  							</c:if>
	                  							<c:if test="${username !='app_sd4'}">
			                                    <c:forEach var="item" items="${getType_Of_LetterList}" varStatus="num" >
	                  								<option value="${item.codevalue}" name="${item.label}" >${item.label}</option>
	                  							</c:forEach>
												</c:if>
	                 						</select>
	                					</div>
	              					</div>
	              				</div>
	              				<div class="col-md-6">
		              				<div class="row form-group">
	                					<div class="col-md-4">
	                  						<label class=" form-control-label"> Unit Name</label>
	                					</div>
	                					<div class="col-md-8">
	                  						<input type="text" id="unit_name" name="unit_name" maxlength="100" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search Unit Name" class="form-control autocomplete" autocomplete="off">
	                					</div>
	              					</div>
	              				</div>
	              			</div>
	              			<div class="col-md-12">
	              				<div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-5">
	                  						<label for="text-input" class=" form-control-label"> Date (From) </label>
	                					</div>
	                					<div class="col-12 col-md-7">
	                  						<input type="date" id="comm_depart_date" name="comm_depart_date" class="form-control" max="${date}">
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
	                					<div class="col col-md-5">
	                  						<label for="text-input" class=" form-control-label"> Date (To) </label>
	                					</div>
	                					<div class="col-12 col-md-7">
	                  						<input type="date" id="compltn_arrv_date" name="compltn_arrv_date" onchange="return checkdate(this,comm_depart_date)" class="form-control" max="${date}">
										</div>
	  								</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
	                					<div class="col col-md-4">
	                  						<label for="text-input" class=" form-control-label"> Status </label>
	                					</div>
	                					<div class="col-12 col-md-8">
	                						<select name="status_sus_no" id="status_sus_no" class="form-control-sm form-control">
	                 							<option value="Pending">Pending</option>
							                    <option value="Active">Approved</option>
							                    <option value="Reject">Rejected</option>
								            </select>
	                					</div>
									</div>
								</div>
							</div>
						</div>
						<div class="card-footer" align="center">
							<a href="SearchRaising_disbandment" class="btn btn-success btn-sm" >Clear</a>   
		              		<i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm" onclick="return getUnitReport();" value="Search" /> 
		              		
			            </div> 
			            <div class="col-md-12" id="unitrisindisReport">
			           			<datatables:table id="applicanttbl" url="getraisingdisbadmentRpt" serverSide="true" pipelining="true" pipeSize="3"	row="latlon" rowIdBase="applicant_id" rowIdPrefix="latlon_" displayLength="10"  jqueryUI="true"
		    						bDestroy="true" filterable="true" sortable="true" processing="true" border="1"  autoWidth="true" pageable="true" paginationType="full_numbers" stateSave="false" deferRender="true"  scrollX="100%" scrollY="500px" scrollCollapse="true">   
								    <datatables:column title="Ser No" property="id" searchable="false" data-halign="left" data-valign="left" visible="false"/>
								    <datatables:column title="Unit Name" property="unit_name" searchable="true" data-halign="left" data-valign="left" />
									<datatables:column title="SUS No" property="sus_no" searchable="true" data-halign="left" data-valign="left"/>
									<datatables:column title="Actions" property="action" searchable="false" data-halign="left" data-valign="left" sortable="false"  />
									<%-- <datatables:column title="Actions" renderFunction="actionsSearchDisbandment" sortable="false" searchable="false" display="HTML" />    --%>
								</datatables:table>    
	                        <br/>
			        	</div>
		        	</div>
				</div>
			</div>
		</div>
</form:form>

	<c:url value="modifyDetailsUrl" var="mUrl" />
	<form:form action="${mUrl}" method="post" id="modifyDetailsForm" name="modifyDetailsForm" modelAttribute="mid">
		<input type="hidden" name="mid" id="mid" value="0"/>
		<input type="hidden" name="scenarioM" id="scenarioM" />
	</form:form> 
	
	<c:url value="rejectDetailsUrl" var="rUrl" />
	<form:form action="${rUrl}" method="post" id="rejectDetailsForm" name="rejectDetailsForm" modelAttribute="rid">
		<input type="hidden" name="rid" id="rid" value="0"/>
	</form:form> 
	
	<c:url value="approvedDetailsUrl" var="aUrl" />
	<form:form action="${aUrl}" method="post" id="approvedDetailsForm" name="approvedDetailsForm" modelAttribute="aid">
		<input type="hidden" name="aid" id="aid" value="0"/>
	</form:form> 
		
	<c:url value="deletedDetailsUrl" var="dUrl" />
	<form:form action="${dUrl}" method="post" id="deleteDetailsForm" name="deleteDetailsForm" modelAttribute="did">
		<input type="hidden" name="did" id="did" value="0"/>
		<input type="hidden" name="scenarioD" id="scenarioD" />
		<input type="hidden" name="sus_noD" id="sus_noD" />
	</form:form> 
	
	<c:url value="viewUnitRaisingDetailsUrl" var="vUrl" />
	<form:form action="${vUrl}" method="post" id="viewDetailsForm" name="viewDetailsForm" modelAttribute="vid">
		<input type="hidden" name="vid" id="vid" value="0"/>
		<input type="hidden" name="scenarioV" id="scenarioV" />
	</form:form>

	<c:url value="search_rising_dis_profileSetSession" var="reloadUrl" />
	<form:form action="${reloadUrl}" method="post" id="reloadReport" name="reloadReport">
		<input type="hidden" name="scenario1" id="scenario1" />
		<input type="hidden" name="status_sus_no1" id="status_sus_no1" />
		<input type="hidden" name="unit_name1" id="unit_name1" />
		<input type="hidden" name="comm_depart_date1" id="comm_depart_date1" />
		<input type="hidden" name="compltn_arrv_date1" id="compltn_arrv_date1" /> 
	</form:form>
<script>
	$(document).ready(function() {
		$("#unit_name").val('${unit_name1}');
    	
    	if('${status_sus_no1}' != ""){
    		if('${status_sus_no1}' == "Inactive"){
    			$("#status_sus_no").val('Active');
    		}else{
    			$("#status_sus_no").val('${status_sus_no1}');
    		}
    	}
        $("#comm_depart_date").val('${comm_depart_date1}');
    	$("#compltn_arrv_date").val('${compltn_arrv_date1}'); 
    	
    	
		if('${scenario1}' != ""){
       		$("select#scenario").val('${scenario1}');
       	}else{
       		$("select#scenario").val("");
       	}
    	    	
    	if('${scenario1}' == ""){
    		$("#unitrisindisReport").hide();
    	}
	});
    
	function getUnitReport() {
		if($("#scenario").val() == ""){
			alert("please Select Scenario");
			$("#scenario").focus();
			return false;
		}
		
		document.getElementById('scenario1').value = $("#scenario").val();
        document.getElementById('unit_name1').value = $("#unit_name").val();
        
        if($("#scenario").val() == '4' & $("#status_sus_no").val()== "Active"){
        	document.getElementById('status_sus_no1').value = "Inactive";
        }else{
        	document.getElementById('status_sus_no1').value = $("#status_sus_no").val();
        }
        document.getElementById('comm_depart_date1').value = $("#comm_depart_date").val();
        document.getElementById('compltn_arrv_date1').value = $("#compltn_arrv_date").val(); 
    	$("#WaitLoader").show();
        document.getElementById('reloadReport').submit();
  		$("div#unitrisindisReport").show();
  		return false;
   	}
	function hideReport(){
		$("div#unitrisindisReport").hide();
	}
	${buttons}
</script>


   
<script>
	$(function() {
		$("#unit_name").keypress(function(){
			var unit_name = this.value;
			var susNoAuto=$("#unit_name");
			susNoAuto.autocomplete({
				source: function( request, response ) {
			        $.ajax({
			        type: 'POST',
			        url: "getUnitsNameAllList?"+key+"="+value,
			        data: {unit_name:unit_name},
			       	success: function( data ) {
			       		var susval = [];
 				        	var length = data.length-1;
 				        	var enc = data[length].substring(0,16);
 					        for(var i = 0;i<data.length;i++){
 					        	susval.push(dec(enc,data[i]));
 					        }
 					         	          
						response( susval ); 
			      	}
			  		});
				},
			  	minLength: 1,
			    autoFill: true,
			    change: function(event, ui) {
			    	if (ui.item) {   	        	  
			       		return true;    	            
			      	} else {
			        	alert("Please Enter All Unit Name");
			        	document.getElementById("sus_no").value="";
			        	susNoAuto.val("");	        	  
			        	susNoAuto.focus();
			        	return false;	             
			     	}   	         
			  	}, 
			    select: function( event, ui ) {
			    	$(this).val(ui.item.value);
			 	} 	     
			});
		}); 
	});
</script>