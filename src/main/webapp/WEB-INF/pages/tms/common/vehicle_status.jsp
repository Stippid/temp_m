<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>

<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js"></script>
<script src="js/cue/printAllPages.js" type="text/javascript"></script>

<div class="animated fadeIn" id="printableArea">
	<div class="">
    	<div class="container" align="center">
    		<div class="card">
    			<div class="card-header" align="center"> <h5>Vehicle Status:Line Dte</h5> </div>
    				<div class="card-body">
    					<div class="col-md-12">
    						
							<div class="col-md-6">
								<div class="row form-group">
							    	<div class="col col-md-4">
							        	<label class=" form-control-label"><strong style="color: red;">*</strong> VEH CAT</label>
							       	</div>
							        <div class="col-12 col-md-8">
										<select id="type_veh"  class="form-control-sm form-control" onchange="getPRFList(this.value)" style="width: 100%">
											<option value="">--Select--</option>
											<option value="0">A Vehicles</option>
											<option value="1">B Vehicles</option>
											<option value="2">C Vehicles</option>
										</select>
									</div>
								</div>		
							</div>
							<div class="col-md-6">
								<div class="row form-group">
							    	<div class="col col-md-4">
							        	<label class=" form-control-label"><strong style="color: red;">*</strong> VEH TYPE</label>
							       	</div>
							        <div class="col-12 col-md-8">
										<select id="prf_code"  class="form-control-sm form-control" onchange="getMCTMainList(this.value)" style="width: 100%">
										</select>
									</div>
								</div>		
							</div>
						</div>
						<div class="col-md-12">
							
							<div class="col-md-6">
								<div class="row form-group">
							    	<div class="col col-md-4">
							        	<label class=" form-control-label"> SUB CAT</label>
							       	</div>
							        <div class="col-12 col-md-8">
										<select id="mct_main"  class="form-control-sm form-control" style="width: 100%">
										</select>
									</div>
								</div>		
							</div>
							<div class="col-md-6">
								<div class="row form-group">
							    	<div class="col col-md-4">
							        	<label class=" form-control-label"> COMMAND</label>
							       	</div>
							        <div class="col-12 col-md-8">
							            <select id="cmd" name="cmd" class="form-control-sm form-control" >
								            <option value="">-- Select All --</option>
		                                    <c:forEach var="item" items="${getCommandList}" varStatus="num" >
                  								<option value="${item[0]}"  name="${item[1]}" >${item[1]}</option>
                  							</c:forEach>
                  						</select>
							    	</div>
							  	</div>
							</div>
						</div>
						
				</div>
    			<div class="form-control card-footer" align="center" id="buttonDiv">
    				<a href="vehicle_status" class="btn btn-success btn-sm" style="border-radius: 5px;">Clear</a>  
					<button class="btn btn-primary btn-sm" onclick="return Search();" style="border-radius: 5px;">Search</button>
             	</div> 
			</div>
		</div>
	</div>
	
	<div class="" id="reportDiv" >
		<div class="col-md-12" >
   			<div  class="watermarked" data-watermark="" id="divwatermark" >
					<span id="ip"></span>
				<table id="getpartBReportMvcr" class="table no-margin table-striped  table-hover  table-bordered report_print" style="margin-bottom: 0rem;">
					<thead>
		        		<tr>
                            <th rowspan="2" style="text-align: center;width:3%;">Ser No</th>
					        <th  rowspan="2" style="text-align: center;width:6%;">Comd</th>
					        <th  rowspan="2" style="text-align: center;width:9%;">Corps</th>
					        <th  rowspan="2" style="text-align: center;width:9%;">Division</th>
					        <th  rowspan="2" style="text-align: center;width:9%;">Brigade</th>
					        <th  rowspan="2" style="text-align: center;width:12%;">Unit Name</th>
					        <th  rowspan="2" style="text-align: center;width:12%;">Nomenclature</th>
					        <th rowspan="2"  style="text-align: center;width:5%;">UE</th>
							<th  colspan="6"  style="text-align: center;width:30%;" >UH</th>	
							<th rowspan="2" style="width:5%;">Total UH</th>
						</tr>
						<tr>
							<th style="text-align: center;">Against UE</th>
							<th style="text-align: center;">Over UE</th>
							<th style="text-align: center;">Loan</th>
							<th style="text-align: center;">Sector Store</th>
							<th style="text-align: center;">ACSFP</th>
							<th style="text-align: center;">Gift</th>
						</tr> 
					</thead>
					<tbody>
						<c:if test="${list.size() == 0}" >
							<tr>
								<td colspan="15" align="center" style="color: red;"> Data Not Available </td>
							</tr>
						</c:if>
						<c:forEach var="item" items="${list}" varStatus="num" >
							<tr>
							     <td style="width:3%;" align="center"><b>${num.index+1}</b></td>
							     <td style="width:6%;font-size: 10px;"><b>${item[1]}</b></td>
							     <td style="width:9%;font-size: 10px;"><b>${item[2]}</b></td>
							     <td style="width:9%;font-size: 10px;"><b>${item[3]}</b></td>
							     <td style="width:9%;font-size: 10px;"><b>${item[4]}</b></td>
							     <td style="width:12%;font-size: 10px;"><b>${item[5]}</b></td>
							     <td style="width:12%;font-size: 10px;"><b>${item[0]}</b></td>
							     <td style="width:5%;text-align: center;">${item[6]}</td>
								 <td style="width:5%;text-align: center;">${item[7]}</td>
								 <td style="width:5%;text-align: center;">${item[8]}</td>
								 <td style="width:5%;text-align: center;">${item[9]}</td> 
								 <td style="width:5%;text-align: center;">${item[10]}</td>
								 <td style="width:5%;text-align: center;">${item[11]}</td>
								 <td style="width:5%;text-align: center;">${item[12]}</td>
								 <td style="width:5%;text-align: center;"><b>${item[13]}</b></td> 
							</tr>
						</c:forEach>
				</tbody>
				</table>
				<table class="table no-margin table-striped  table-hover  table-bordered report_print" style="margin-bottom: 0rem;">
					<tbody>
						<c:if test="${list.size() > 0}" >
						<tr>
							<td colspan="7" style="width: 60%;text-align: center;"><B>Total</B></td>
							<td align="left" style="width: 5%;text-align: center;"><B>${sumUE}</B></td>
							<td align='left' style="text-align: center;"><B>${againUE}</B></td>
							<td align='left' style="text-align: center;"><B>${overUE}</B></td>
							<td align='left' style="text-align: center;"><B>${loan}</B></td>
							<td align='left' style="text-align: center;"><B>${sector}</B></td>
							<td align='left' style="text-align: center;"><B>${acsfp}</B></td>
							<td align='left' style="text-align: center;"><B>${gift}</B></td>
							<td align='left' style="width: 5%;text-align: center;"><B>${totalUH}</B></td>
						</tr>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<div class="animated fadeIn" id="printDiv" style="display: none;">
	<div class="" >
		<div class="container" align="center">
		<div class="col-md-12"  align="center">
			<input type="button" class="btn btn-primary btn-sm" value="Print" onclick="printDiv('printableArea')" >
		</div>
		</div>
	</div>
</div>

<c:url value="vehicle_status_details" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="code_value1">
	<input type="hidden" name="cmd1" id="cmd1" value="0"/>
	<input type="hidden" name="type_veh1" id="type_veh1" value="0"/>
	<input type="hidden" name="mct_main1" id="mct_main1" value="0"/>
	<input type="hidden" name="prf_code1" id="prf_code1" value="0"/>
</form:form> 

<script>
$(document).ready(function() {
	$("div#divwatermark").val('').addClass('watermarked');	
	watermarkreport();
	
	if('${list.size()}' == ""){
		$("#reportDiv").hide();	
	}else{
		$("#reportDiv").show();
	}
	
	$("#cmd").val('${cmd}');
	$("#type_veh").val('${type_veh}');
	
	getPRFList('${type_veh}')
	
});
function getPRFList(val)
{
	getMCTMainList('');
	var options = '<option value="0">'+ "--Select--" + '</option>';
	if(val !="")
	{
	    $.post("getTptSummaryInPRFList?"+key+"="+value,{type : val}).done(function(j) {
			for ( var i = 0; i < j.length; i++) {
				if(j[i].prf_code == '${prf_code}'){
					options += '<option value="'+j[i].prf_code+'" name="' + j[i].group_name+ '" selected=selected>'+ j[i].group_name+ '</option>';
					getMCTMainList('${prf_code}')
				}else{
					options += '<option value="'+j[i].prf_code+'" name="' + j[i].group_name+ '" >'+ j[i].group_name+ '</option>';
				}
			}	
			$("select#prf_code").html(options); 
	    }).fail(function(xhr, textStatus, errorThrown) {
		});
	}
	else {
		$("select#prf_code").html(options);
	}
}
function getMCTMainList(val)
{
	var options = '<option value="0">'+ "--Select--" + '</option>';
	if(val !="")
	{
	    $.post("getMCtMain_from_prf_code?"+key+"="+value,{prf_code : val}).done(function(j) {
			for ( var i = 0; i < j.length; i++) {
				if(j[i].mct_main_id == '${mct_main}'){
					options += '<option value="'+j[i].mct_main_id+'" name="' + j[i].mct_nomen+ '" selected=selected>'+ j[i].mct_nomen+ '</option>';
				}else{
					options += '<option value="'+j[i].mct_main_id+'" name="' + j[i].mct_nomen+ '" >'+ j[i].mct_nomen+ '</option>';
				}
			}	
			$("select#mct_main").html(options); 
	    }).fail(function(xhr, textStatus, errorThrown) {
		});
	}
	else {
		$("select#mct_main").html(options);
	}
}

function Search(){
	if($("#type_veh").val() == "")
	{
		alert("Please select VEH CAT.");
	}
	else if($("#prf_code").val() == "0")
	{
		alert("Please select VEH TYPE.");
	}
	else
	{
		jQuery("#WaitLoader").show();
		$("#cmd1").val($("#cmd").val()) ;
		$("#type_veh1").val($("#type_veh").val());
		$("#prf_code1").val($("#prf_code").val());
		$("#mct_main1").val($("#mct_main").val());
		document.getElementById('searchForm').submit();
	}
}	 
</script>