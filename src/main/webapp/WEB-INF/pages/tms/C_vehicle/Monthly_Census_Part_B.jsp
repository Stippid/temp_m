<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js"></script>
<script src="js/cue/printAllPages.js" type="text/javascript"></script>

<form:form encrypt="multipart/form-data" id="monthly_census_partB"
	action="monthly_census_partBAction?${_csrf.parameterName}=${_csrf.token}"
	method='POST' class="form-horizontal"
	commandName="monthly_census_partBCMD">
	<div class="animated fadeIn">
		<div class="">
			<div class="container" align="center">
				<div class="card">
					<div class="card-header"
						style="border: 1px solid rgba(0, 0, 0, .125); text-align: center;">
						<h5>ENGINEER MACHINARY ACTIVITY REPORT</h5>
					</div>
					<div class="card-header">
						<strong>Basic Details</strong>
					</div>
					<div class="card-body card-block">
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">* </strong>SUS No</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="sus_no" name="sus_no" 
											class="form-control autocomplete" autocomplete="off"
											maxlength="8" />
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">Unit Name </label>
									</div>
									<div class="col-md-8">
										<textarea id="unit_name" name="unit_name" 
											class="form-control autocomplete" style="font-size: 11px;"
											autocomplete="off" maxlength="100"></textarea>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">* </strong>Status</label>
									</div>
									<div class="col-12 col-md-8">
										<select id="status" name="status" class="form-control" >
											<option value="0">Pending</option>
											<option value="1">Approved</option>
										</select>
									</div>
								</div>
							</div>
						</div>
					</div>
					<input type="hidden" id="catpartdis" name="catpartdis" autocomplete="off" />
					<div class="form-control card-footer" align="center">
						<a href="cvehReportActivityForm" type="reset" class="btn btn-success btn-sm"> Clear </a>
						<button type="button" class="btn btn-success btn-sm" onclick="return Search();">Search</button>
						<button type="button" id="btnApp" class="btn btn-success btn-sm" onclick="return Approved();">Approve</button>
						<button type="button" id="printId" class="btn btn-primary btn-sm" onclick="return printDiv();" disabled>Print</button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="col s12" id="divPrint">
		<div align="right"><h6>Total Count : ${list.size()}</h6></div>
		<div id="divShow" style="display: block;"></div>

		<div class="watermarked" data-watermark="" id="divwatermark"
			style="display: block;">
			<span id="ip"></span>
			<table id="getSearchReport"
				class="table no-margin table-striped  table-hover  table-bordered report_print"
				border="1">
				<thead style="text-align: center;">
					<tr>
						<th style="text-align: center; width: 2%;" rowspan="2">Ser No</th>
						<th style="text-align: center; width: 10%;" rowspan="2">Type of Engr origin plant Eqpt</th>
						<!-- <th style="text-align: center; width: 3%;" rowspan="2">Auth</th>
						<th style="text-align: center; width: 3%;" rowspan="2">Held</th> -->
						<th style="text-align: center; width: 30%;" colspan=6>Vintage</th>
						<th style="text-align: center; width: 5%;" rowspan="2">Proc
							From</th>
						<th style="text-align: center; width: 5%;" rowspan="2">Classification</th>
						<th style="text-align: center; width: 5%;" rowspan="2">SER/UNSV</th>
						<th style="text-align: center; width: 15%;" colspan=3>Activity
							Details</th>
						<th style="text-align: center; width: 10%;" colspan=2>Main</th>
						<th style="text-align: center; width: 5%;" rowspan="2">Updated
							On</th>
					</tr>
					<tr>
						<td style="text-align: center;">EM No</td>
						<td style="text-align: center;">Old EM No</td>
						<td style="text-align: center;">Engine No</td>
						<td style="text-align: center;">Chassis No</td>
						<td style="text-align: center;">Dt of Induc -tion</td>
						<td style="text-align: center;">Mother Deport</td>
						<td style="text-align: center;">Total Hrs</td>
						<td style="text-align: center;">Previous Month</td>
						<td style="text-align: center;">Hr Run in current</td>
						<td style="width: 5%;">R1</td>
						<td style="width: 5%;">Progress</td>
					</tr>
				</thead>
				<tbody style="text-align: center;">
					<c:if test="${list.size() == 0}">
						<tr>
							<td colspan="7" align="center" style="color: red;">Data Not
								Available</td>
						</tr>
					</c:if>
					<c:forEach var="item" items="${list}" varStatus="num">
						<tr>
							<td style="width: 2%;">${num.index+1}</td>
							<td style="width: 10%;">${item.std_nomclature}</td>
							<%-- <td style="width: 3%;">${item.ue}</td>
							<td style="width: 3%;">${item.uh}</td> --%>
							<td style="width: 5%;">${item.em_no}</td>
							<td style="width: 5%;">${item.old_ba_no}</td>
							<td style="width: 5%;">${item.engine_no}</td>
							<td style="width: 5%;">${item.chasis_no}</td>
							<td style="width: 5%;">${item.date_of_induction}</td>
							<td style="width: 5%;">${item.unit_name}</td>
							<td style="width: 5%;">${item.proc_from}</td>
							<td style="width: 5%;">${item.classification}</td>
							<td style="width: 5%;">${item.seviceable}</td>
							<td style="width: 5%;">${item.balh_for}</td>
							<td style="width: 5%;">${item.prev_km_run}</td>
							<td style="width: 5%;">${item.current_km_run}</td>
							<td style="width: 5%;">${item.rispares}</td>
							<td style="width: 5%;">${item.pers}</td>
							<td style="width: 5%;">${item.modify_date}</td>
						</tr>
						<input type="hidden" id="hdnStatus" value="hdnStatus"
							value='${item.main_status}' />
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</form:form>

<%-- <c:url value="UpdateEARreport" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="updateForm"
	name="updateForm" modelAttribute="updateid">
	<input type="hidden" name="updateid" id="updateid" value="0" />
	<input type="hidden" name="catid" id="catid" value="0" />
	<input type="hidden" name="ProcFrom" id="ProcFrom" value="0" />
	<input type="hidden" name="current" id="current" value="0" />
	<input type="hidden" name="Balh_for" id="Balh_for" value="0" />
	<input type="hidden" name="state" id="state" value="0" />
	<input type="hidden" name="date_of_oh" id="date_of_oh" value="0" />
	<input type="hidden" name="Rispares" id="Rispares" value="0" />
	<input type="hidden" name="pers" id="pers" value="0" />
	<input type="hidden" name="unsv" id="unsv" value="0" />
	<input type="hidden" name="prev" id="prev" value="0" />
</form:form> --%>

<c:url value="ApproveEARreport" var="appUrl" />
<form:form action="${appUrl}" method="post" id="appForm" name="appForm"
	modelAttribute="appid">
	<input type="hidden" name="appsus_no" id="appsus_no" value="0" />
</form:form>

<script>

function Approved(){

	document.getElementById('appsus_no').value=$("#sus_no").val();
	document.getElementById('appForm').submit();
}
$(function() {
	
	$("#sus_no").keypress(function(){
		var sus_no = this.value;
			 var susNoAuto=$("#sus_no");
			  susNoAuto.autocomplete({
			      source: function( request, response ) {
			        $.ajax({
			        	type: 'POST',
				    	url: "getTargetSUSNoList?"+key+"="+value,
			        data: {sus_no:sus_no},
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
			        	  alert("Please Enter Approved SUS No.");
			        	  document.getElementById("sus_no").value="";
			        	  susNoAuto.val("");	        	  
			        	  susNoAuto.focus();
			        	  return false;	             
			          }   	         
			      }, 
			      select: function( event, ui ) {
			    	  var sus_no = ui.item.value;	
			    	
				            $.post("getActiveUnitNameFromSusNo?"+key+"="+value,{sus_no:sus_no}, function(data) {
				            }).done(function(data) {
				            	var length = data.length-1;
					        	var enc = data[length].substring(0,16);
					        	$("#unit_name").val(dec(enc,data[0]));	 	
				            }).fail(function(xhr, textStatus, errorThrown) {
				            });
			      } 	     
			});	
	});
	
	
	 $("#unit_name").keypress(function(){
			var unit_name = this.value;
				 var susNoAuto=$("#unit_name");
				  susNoAuto.autocomplete({
				      source: function( request, response ) {
				        $.ajax({
				        	type: 'POST',
					    	url: "getTargetUnitsNameActiveList?"+key+"="+value,
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
				        	  alert("Please Enter Approved Unit Name.");
				        	  document.getElementById("unit_name").value="";
				        	  susNoAuto.val("");	        	  
				        	  susNoAuto.focus();
				        	  return false;	             
				          }   	         
				      }, 
				      select: function( event, ui ) {
				    	 var target_unit_name = ui.item.value;
				    
					            $.post("getTargetSUSFromUNITNAME?"+key+"="+value,{target_unit_name:target_unit_name}, function(data) {
					            }).done(function(data) {
					            	var length = data.length-1;
						        	var enc = data[length].substring(0,16);
						        	$("#sus_no").val(dec(enc,data[0]));
					            }).fail(function(xhr, textStatus, errorThrown) {
					            });
				      } 	     
				}); 			
		});
});		
			

/* function validate(){	
	if($("#sus_no").val() == ""){
		alert("Please Enter the SUS No.");
		return false;
	}
	if($("#unit_name").val() == ""){
		alert("Please Enter the Unit Name.");
		return false;
	}	   	
	if($("#sus_no").val() != "" && $("#unit_name").val() != "") {
		 document.getElementById('tableshow').style.display='block'; 
	}
	return true;
}  */
</script>

<script>
function printDiv() 
{
	if('${list.size()}' == 0){
		alert("Data Not Available..");
		return false;
	}
 	var printLbl = ["SUS No :","Unit Name :"];
 	var printVal = ['${sus_no}',document.getElementById('unit_name').value]; 	
 	printDivOptimize('divPrint','ENGINEER MACHINARY ACTIVITY REPORT',printLbl,printVal,"");
}
</script>

<script>
function Search(){
	if( $("#sus_no").val() == "")
	{
		alert("Please Enter SUS No.");
	}
   	else
   	{
		$("#sus_no1").val($("#sus_no").val());
		$("#status1").val($("#status").val());
		document.getElementById('searchForm').submit();
   	}
}

</script>
<c:url value="ReportSearchCVehicleActivityReport" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="code_value1">
	<input type="hidden" name="sus_no1" id="sus_no1" value="0" />
	<input type="hidden" name="status1" id="status1" value="0" />
</form:form>
<script>
 $(document).ready(function() {
	 $("#btnApp").hide();
	$("div#divwatermark").val('').addClass('watermarked'); 
	watermarkreport();
	
	if('${status}' != ''){
		$('#status').val('${status}');
	}
	
	if('${sus_no}' == "")
	{
		$("#divPrint").hide();
	}
	else
	{
		$("#divPrint").show();
		$("#sus_no").val('${sus_no}');
		var sus_no = '${sus_no}';
		//26-01-1994
 	   	$.post("getTargetUnitNameList?"+key+"="+value, {sus_no:sus_no}, function(j) {}).done(function(j) {
			var length = j.length-1;
	        var enc = j[length].substring(0,16);
	        $("#unit_name").val(dec(enc,j[0]));
				
		}).fail(function(xhr, textStatus, errorThrown) {});
		
		$("div#divwatermark").val('').addClass('watermarked');					
		watermarkreport(); 				
		document.getElementById("printId").disabled = false;
	}
	var RoleType = '${roleType}';
	if(RoleType == "APP" || RoleType == "ALL")
	{
		if('${main_status}'=='0')
		{
			$("#btnApp").show();
			
		}	
	}
}); 
</script>