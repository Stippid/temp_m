<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script src="js/InfiniteScroll/js/jquery-1.11.0.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js"></script>
<script src="js/cue/printAllPages.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="js/InfiniteScroll/js/datatables.min.js"></script>
<script type="text/javascript" src="js/InfiniteScroll/js/jquery.mockjax.min.js"></script>
<script type="text/javascript" src="js/InfiniteScroll/js/datatables.mockjax.js"></script>
<link rel="stylesheet" href="js/common/db_style_new.css">
<script src="js/upload_xls/xlsx.full.min.js"></script>

<style type="text/css">
.multipleSelection {
	width: 100%;
	/*             background-color: #FFFFFF !important; */
	position: relative;
	display: inline-block;
	transition: border-color .15s ease-in-out, box-shadow .15s ease-in-out
		!important;
	border-radius: .2rem !important;
	font-size: .875rem !important;
	line-height: 1.5 !important;
	color: #495057 !important;
	background-color: #fff !important;
	background-clip: padding-box !important;
	border: 1px solid #ced4da !important;
}

.overSelect {
	position: absolute;
	left: 0;
	right: 0;
	top: 0;
	bottom: 0;
	
}


.selectBox {
	position: relative;
	
}

.selectBox select {
	width: 100%;
	font-weight: bold;
}

.checkboxes {
	display: none;
	border: 1px #dadada solid;
            width: 250px;
            display: none;
            border: 0.5px #000000 solid;
            position: absolute;
  			background-color: #FFFFFF;
    		width: 100%;
      		z-index: 1;
            	
}

.checkboxes label {
	margin-left: 10px;
	text-align: left;
	display: block;
}

.checkboxes label:hover {
	background-color: #1e90ff;
}


</style>

<div class="animated fadeIn" id="printableArea">
	<div class="">
    	<div class="col-md-12" align="center">
    		<div class="card">
    			<div class="card-header" align="center"> <h5>WPNS AND EQPTS Detls : Line DTE</h5> </div>
    				<div class="card-body">
    					<div class="col-md-12">
    						<div class="col-md-6">
								<div class="row form-group">
							    	<div class="col col-md-4">
							        	<label class=" form-control-label"><strong style="color: red;">*</strong> WPN CAT</label>
							       	</div>
							        <div class="col-12 col-md-8">
										<select id="type_wep_eqpt"  class="form-control-sm form-control" onchange="getPRFList(this.value)" style="width: 100%">
											 <option value="">--Select--</option>
		                                     <c:forEach var="item" items="${getItemGroupList}" varStatus="num" >
                  								<option value="${item[0]}" >${item[1]}</option>
                  							</c:forEach>
										</select>
									</div>
								</div>		
							</div>
<!-- 							<div class="col-md-6"> -->
<!-- 								<div class="row form-group"> -->
<!-- 							    	<div class="col col-md-4"> -->
<!-- 							        	<label class=" form-control-label"><strong style="color: red;">*</strong> WPN SUB CAT</label> -->
<!-- 							       	</div> -->
<!-- 							        <div class="col-12 col-md-8"> -->
<!-- 										<select id="prf_code"  class="form-control-sm form-control" style="width: 100%"> -->
<!-- 										</select> -->
<!-- 									</div> -->
<!-- 								</div>		 -->
<!-- 							</div> -->


				<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
								<label class=" form-control-label">WPN SUB CAT</label>
							</div>
							
								<div class="col-md-8">

									<div class="selectBox" onclick="showCheckboxes()">
<!-- 										<select name="prf_code_opt" id="prf_code_opt" onclick="showCheckboxes();" -->
<!-- 											class=" form-control"> -->
<!-- 											<option>--Select--</option> -->
<!-- 										</select> -->
											<select id="prf_code"  class="form-control-sm form-control" style="width: 100%">
											<option>--Select--</option>
												</select>
										<div class="overSelect"></div>
									</div>
									<div id="checkboxes" class="checkboxes"
										style="max-height: 200px; overflow: scroll; overflow-x: hidden;">
										
										<div>
											
										</div>
										
									</div>
										<input class="multi form-check-input mr-5" type="hidden"
												id="multiSelect_checkboxes" name="multiSelect_checkboxes" value="">
												 <input class="multi form-check-input mr-5"
												type="hidden" id="multiSelect_count"
												name="multiSelect_count" value="0">

							</div>

						</div>
					</div>
					
					
						</div>
						<div class="col-md-12">
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
							<div class="col-md-6">
								<div class="row form-group">
	                				<div class="col col-md-4">
	                  					<label class=" form-control-label">Line Dte</label>
	                				</div>
	                				<div class="col-12 col-md-8">
	                 					<select id="line_dte_sus" name="line_dte_sus" class="form-control-sm form-control" onchange="sus_nowithUnitName_list()" >
	                 						${selectLine_dte}
	                 						<c:forEach var="item" items="${getLine_DteList}" varStatus="num" >
                  								<option value="${item.line_dte_sus}"  name="${item.line_dte_name}" >${item.line_dte_name}</option>
                  							</c:forEach>
	                 					</select>
	                				</div>
				            	</div>
							</div>
						</div>
						<div class="col-md-12 " style='background-color:mistyrose;padding:7px;'>
							<div class="col-md-5" align="left">
								<b><input type=checkbox id='nSelAll' name='tregn' onclick="callsetall();">&nbsp;Select all (<span id="sregn" style='font-size:14px;'>0</span>)</b>&nbsp;&nbsp;
								<input id="InputSearch" type="text" placeholder="Search unit name .." size="20">
							</div>   
							<div class="col-md-5" align="right">
								<b>Selected Units - <span id="tregn" style='font-size:14px;'>0</span></b>
							</div>
						</div> 
						<div  class="col-md-12">
							<div class="col-md-6" style="height: 200px;overflow:auto;width:99%;border:1px solid #000;text-align: left;" id="srctable"></div>
							<div class="col-md-6" style="height: 200px;overflow:auto;width:99%;border:1px solid #000;text-align: left;" id="tartable"></div>
							<input type="hidden" id="c_val" name="c_val" value="0">
							<input type="hidden" id="sus_no_list" name="sus_no_list" placeholder="" class="form-control-sm nrform-control">
		                    <input type="hidden" id="unit_name_list" name="unit_name_list" placeholder="" class="form-control-sm nrform-control">
						</div>
				</div>
				
    			<div class="form-control card-footer" align="center" id="buttonDiv">
    				<a href="wep_eqpt_status" class="btn btn-success btn-sm" style="border-radius: 5px;">Clear</a>  
					<!-- <button class="btn btn-primary btn-sm" onclick="return Search();" style="border-radius: 5px;">Search</button> -->
					<button id="btn_search" class="btn btn-primary btn-sm" style="border-radius: 5px;">Search</button>
						<i class="fa fa-download"></i><input type="button" id="exportId"
						class="btn btn-sm btn_report"
						style="background-color: #e37c22; color: white;" value="Export"
						onclick="exportToExcel('EqptStatusTbl', 'wpns_and_eqpts_line_dt')">
             	</div> 
             	<div class="card-body">
             		<div class="col-md-12">
             			<div  class="watermarked" data-watermark="" id="divwatermark" >
						<span id="ip"></span>
             			<table id="EqptStatusTbl" style="width: 100%;display: none;" class="table no-margin table-striped">
							<thead>
				        		<tr>
		                            <th rowspan="2" style="text-align: center;width:3%;">Ser No</th>
		                            <th  rowspan="2" style="text-align: center;width:6%;">Comd</th>
		                            <th  rowspan="2" style="text-align: center;width:9%;">Corps</th>
		                            <th  rowspan="2" style="text-align: center;width:9%;">Division</th>
		                            <th  rowspan="2" style="text-align: center;width:9%;">Brigade</th>
		                            <th  rowspan="2" style="text-align: center;width:10%;">Unit Name</th>
							        <th  rowspan="2" style="text-align: center;width:11%;">WPN SUB CAT</th>
							        <th rowspan="2"  style="text-align: center;width:7%;">UE</th>
									<th  colspan="6"  style="text-align: center;width:30%;">UH</th>
									<th rowspan="2"  style="text-align: center;width:6%;">Total UH</th>	
								</tr>
								<tr>
									<th style="text-align: center;">Against UE</th>
									<th style="text-align: center;">On Loan</th>
									<th style="text-align: center;">Sector Store</th>
									<th style="text-align: center;">ACSFP</th>
									<th style="text-align: center;">WWR Unit</th>
									<th style="text-align: center;">WWR Depot</th>
								</tr>  
							</thead>
							<tbody style="font-size: 11px;font-weight:bold;text-align: center;"></tbody>
						</table>
						</div>
             		</div>
             	</div>
			</div>
		</div>
	</div>
	
	<div class="col-md-12" id="reportDiv">
		<div class="col-md-12">
   			<div  class="watermarked" data-watermark="" id="divwatermark" >
					<span id="ip"></span>
				<%-- <table id="getpartBReportMvcr" class="table no-margin table-striped  table-hover  table-bordered report_print" style="margin-bottom: 0rem;">
					<thead>
		        		<tr>
                            <th rowspan="2" style="text-align: center;width:3%;">Ser No</th>
                            <th  rowspan="2" style="text-align: center;width:6%;">Comd</th>
                            <th  rowspan="2" style="text-align: center;width:9%;">Corps</th>
                            <th  rowspan="2" style="text-align: center;width:9%;">Division</th>
                            <th  rowspan="2" style="text-align: center;width:9%;">Brigade</th>
                            <th  rowspan="2" style="text-align: center;width:10%;">Unit Name</th>
					        <th  rowspan="2" style="text-align: center;width:11%;">WPN SUB CAT</th>
					        <th rowspan="2"  style="text-align: center;width:7%;">UE</th>
							<th  colspan="6"  style="text-align: center;width:30%;">UH</th>
							<th rowspan="2"  style="text-align: center;width:6%;">Total UH</th>	
						</tr>
						<tr>
							<th style="text-align: center;">Against UE</th>
							<th style="text-align: center;">On Loan</th>
							<th style="text-align: center;">Sector Store</th>
							<th style="text-align: center;">ACSFP</th>
							<th style="text-align: center;">WWR Unit</th>
							<th style="text-align: center;">WWR Depot</th>
						</tr> 
					</thead>
					<tbody>
						<c:if test="${list.size() == 0}" >
							<tr>
								<td colspan="10" align="center" style="color: red;"> Data Not Available </td>
							</tr>
						</c:if>
						<c:forEach var="item" items="${list}" varStatus="num" >
							<tr>
							     <td style="width:3%;" align="center"><b>${num.index+1}</b></td>
							     <td style="width:6%;font-size: 10px;"><b>${item[2]}</b></td>
							     <td style="width:9%;font-size: 10px;"><b>${item[3]}</b></td>
							     <td style="width:9%;font-size: 10px;"><b>${item[4]}</b></td>
							     <td style="width:9%;font-size: 10px;"><b>${item[5]}</b></td>
							     <td style="width:10%;font-size: 10px;"><b>${item[0]}</b></td>
							     <td style="width:11%;font-size: 10px;"><b>${item[1]}</b></td>
							     <td style="width:7%;text-align: center;">${item[6]}</td>
								 <td style="text-align: center;">${item[7]}</td>
								 <td style="text-align: center;">${item[8]}</td>
								 <td style="text-align: center;">${item[9]}</td> 
								 <td style="text-align: center;">${item[10]}</td>
								 <td style="text-align: center;">${item[11]}</td>
								 <td style="text-align: center;">${item[12]}</td>
								 <td style="width:6%;text-align: center;">${item[13]}</td>
							</tr>
						</c:forEach>
						
				</tbody>
				</table>
				<table class="table no-margin table-striped  table-hover  table-bordered report_print" style="margin-bottom: 0rem;">
					<tbody>
						<c:if test="${list.size() > 0}" >
						<tr>
							<td colspan="7" style="width: 57%;text-align: center;"><B>Total</B></td>
							<td align="left" style="width: 7%;text-align: center;"><B>${sumUE}</B></td>
							<td align='left' style="text-align: center;"><B>${uh}</B></td>
							<td align='left' style="text-align: center;"><B>${loan}</B></td>
							<td align='left' style="text-align: center;"><B>${sector}</B></td>
							<td align='left' style="text-align: center;"><B>${acsfp}</B></td>
							<td align='left' style="text-align: center;"><B>${wwr_unit}</B></td>
							<td align='left' style="text-align: center;"><B>${wwr_depot}</B></td>
							<td align='left' style="width: 6%;text-align: center;"><B>${total_uh}</B></td>
						</tr>
						</c:if>
					</tbody>
				</table> --%>
				
				<!-- <table id="EqptStatusTbl" style="width: 100%;display: none;" class="table no-margin table-striped table-bordered ">
					<thead>
		        		<tr>
                            <th rowspan="2" style="text-align: center;width:3%;">Ser No</th>
                            <th  rowspan="2" style="text-align: center;width:6%;">Comd</th>
                            <th  rowspan="2" style="text-align: center;width:9%;">Corps</th>
                            <th  rowspan="2" style="text-align: center;width:9%;">Division</th>
                            <th  rowspan="2" style="text-align: center;width:9%;">Brigade</th>
                            <th  rowspan="2" style="text-align: center;width:10%;">Unit Name</th>
					        <th  rowspan="2" style="text-align: center;width:11%;">WPN SUB CAT</th>
					        <th rowspan="2"  style="text-align: center;width:7%;">UE</th>
							<th  colspan="6"  style="text-align: center;width:30%;">UH</th>
							<th rowspan="2"  style="text-align: center;width:6%;">Total UH</th>	
						</tr>
						<tr>
							<th style="text-align: center;">Against UE</th>
							<th style="text-align: center;">On Loan</th>
							<th style="text-align: center;">Sector Store</th>
							<th style="text-align: center;">ACSFP</th>
							<th style="text-align: center;">WWR Unit</th>
							<th style="text-align: center;">WWR Depot</th>
						</tr>  
					</thead>
					<tbody style="font-size: 11px;font-weight:bold;"></tbody>
				</table> -->
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

<%-- <c:url value="wep_eqpt_status_details" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="code_value1">
	<input type="hidden" name="cmd1" id="cmd1" value="0"/>
	<input type="hidden" name="type_wep_eqpt1" id="type_wep_eqpt1" value="0"/>
	<input type="hidden" name="prf_code1" id="prf_code1" value="0"/>
	<input type="hidden" name="line_dte_sus1" id="line_dte_sus1" value="0"/>
	<input type="hidden" id="sus_no_list1" name="sus_no_list1">
</form:form> --%>
<script>


function showCheckboxes() {
	var checkboxes = document.getElementById("checkboxes");
	$("#checkboxes").toggle();
	$("#user_role_id").val('');
	$('.prf_code').each(function() {
		$(this).show()
	})
}
$(document).ready(function() {
	$("div#divwatermark").val('').addClass('watermarked');	
	watermarkreport();
	
	/*if('${list.size()}' == ""){
		$("#reportDiv").hide();	
	}else{
		$("#reportDiv").show();
	}
	
	if('${line_dte_sus1}' != ""){
 		$("#line_dte_sus").val('${line_dte_sus1}');
 	}
	
	$("#cmd").val('${cmd}');
	if('${type_wep_eqpt}' != ''){
		$("#type_wep_eqpt").val('${type_wep_eqpt}');
	}
	getPRFList('${type_wep_eqpt}') */
	
	sus_nowithUnitName_list();
	$("#InputSearch").on("keyup", function(){
		var value = $(this).val().toLowerCase();
		$("#srctable tr").filter(function() {
		$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		});
	});
	
	var table = "";
	$('#btn_search').on('click', function(){
		if($("#type_wep_eqpt").val() == ""){
			alert("Please Select WPN CAT");
			$("#type_wep_eqpt").focus();
		} else if($("#prf_code").val() == "0") {
			alert("Please Select WPN SUB CAT");
			$("#prf_code").focus();
		} else {
			if(table == ""){
				//$("#reportDiv").show();
				$('#EqptStatusTbl').show();
				mockjax1('EqptStatusTbl');
				table = dataTable('EqptStatusTbl',[0,7,8,9,10,11,12,13,14],[]);
			}else{
				table.ajax.reload();
			}
		}
    });
});
function data(tableName){
	jQuery("#WaitLoader").show();
	jsondata = [];
	// Default Parameter
	var table = $('#'+tableName).DataTable();
	var info = table.page.info();
	var currentPage = info.page;
	var pageLength = info.length;
	var startPage = info.start;
	var endPage = info.end;
	var Search = table.search();
	var order = table.order();
	var orderColunm = order[0][0] + 1;// for Colunm Id wise Ordering
	//var orderColunm = $(table.column(order[0][0]).header()).html().toLowerCase(); // for Colunm Name wise Ordering
	var orderType = order[0][1];
	// Default Parameter
	// No Change

	// Advanced Search Parameter
	var cmd = $("#cmd").val();
	var type_wep_eqpt = $("#type_wep_eqpt").val();
	var prf_code = $("#prf_code").val();
	var line_dte_sus = $("#line_dte_sus").val();
	var sus_no_list = $("#sus_no_list").val();
	
	var count = $("#multiSelect_count").val();
// 	alert(count);

	var check_list = "";
	for(var i = 1; i <= count; i++){
		if ($('input[name="multisub_'+i+'"]:checked').is(':checked')){
			check_list += $('input[name="multisub_'+i+'"]:checked').val() +",";
			$("#multiSelect_checkboxes").val(check_list);
		}

		
	}
	
	
	var multiSelect = $("#multiSelect_checkboxes").val();
	
	multiSelect = multiSelect.substring(0,multiSelect.length-1 );
	
	prf_code = multiSelect;
	
	// Advanced Search Parameter
	
	$.post("wep_eqpt_statusList?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,orderType:orderType,
		cmd:cmd,type_wep_eqpt:type_wep_eqpt,prf_code:prf_code,line_dte_sus:line_dte_sus,sus_no_list:sus_no_list},function(j) {
		for (var i = 0; i < j.length; i++) {
			jsondata.push([(i+1),j[i].comd,j[i].corps,j[i].div,j[i].bde,j[i].unit_name,j[i].prf_group,j[i].ue,j[i].uh,j[i].ls,j[i].ss,j[i].ac,j[i].wwr_unit,j[i].wwr_depot,j[i].total_uh]);
		}
	});
	$.post("wep_eqpt_statusCount?"+key+"="+value,{Search:Search,cmd:cmd,type_wep_eqpt:type_wep_eqpt,prf_code:prf_code,line_dte_sus:line_dte_sus,sus_no_list:sus_no_list},function(j) {
		FilteredRecords = j;
	});
	jQuery("#WaitLoader").hide();
}

function getPRFList(val)
{
	var options = '';
	if(val !="")
	{
	    $.post("getPRFListbyItemGroup?"+key+"="+value,{type : val}).done(function(j) {
			for ( var i = 0; i < j.length; i++) {
				
					
					options += '<label for="one" class="quali_subjectlist"><input type="checkbox" class="multisub_'+parseInt(i+1)+'" name="multisub_'+parseInt(i+1)+'" value="'+ j[i].prf_group_code+'" /> '+ j[i].prf_group+ ' </label> ' ;
			
			}	
			 $("input#multiSelect_count").val(j.length);
// 			console.log(options);
			$("div#checkboxes").html(options); 
	    }).fail(function(xhr, textStatus, errorThrown) {
		});
	}
	else {
		$("select#prf_group_code").html(options);
	}
}
/* function Search(){
	if($("#type_wep_eqpt").val() == "")
	{
		alert("Please Select WPN CAT");
		$("#type_wep_eqpt").focus();
	}
	else if($("#prf_code").val() == "0")
	{
		alert("Please Select WPN SUB CAT");
		$("#prf_code").focus();
	}
	else
	{
		jQuery("#WaitLoader").show();
		$("#cmd1").val($("#cmd").val()) ;
		$("#type_wep_eqpt1").val($("#type_wep_eqpt").val());
		$("#prf_code1").val($("#prf_code").val());
		$("#line_dte_sus1").val($("#line_dte_sus").val());
		document.getElementById('searchForm').submit();
	}
} */

function sus_nowithUnitName_list(){
	var line_dte_sus = $("#line_dte_sus").val();
	$.post("getMMSUnitNameWITHSUSNo?"+key+"="+value,{line_dte_sus:line_dte_sus}, function(data){ }).done(function(data) {
	    if(data == ""){
			$("#srctable").empty();
			$("#tartable").empty();
			//alert("No Regd Data Found");
		}else{
			$("#d_reg").show();
			$("#d_reg2").show();
			$("#d_reg3").show();
			$("#srctable").val(data);
			drawregn(data);
		}
	}).fail(function(xhr, textStatus, errorThrown) {
	});
}
function drawregn(j) {
	var ii=0;
	$("#srctable").empty();
	$("#tartable").empty();
	for(var i = 0; i < j.length; i++){
		var row="<tr id='SRC"+j[i][1]+"' padding='5px;'>";
		row=row+"<td>&nbsp;<input class='nrCheckBox' type=checkbox id='"+j[i][1]+"' name='"+j[i][0]+"' onclick='findselected();'>&nbsp;";
		row=row+ j[i][1]+" - "+j[i][0] +"</td>";
		$("#srctable").append(row);
		ii=ii+1;
	}
	$("#sregn").text(ii);
}
function callsetall() {
	var chkclk=$('#nSelAll').prop('checked');
	if (chkclk) {
		$('.nrCheckBox').prop('checked',true);
		
	} else {
		$('.nrCheckBox').prop('checked',false);
	}
	findselected();
}
function findselected() {
	$("#srctable tr").css('background-color','white');
	
	var nrSel=$('.nrCheckBox:checkbox:checked').map(function() {
		var bb=$(this).attr('id');
		$("#SRC"+bb).css('background-color','yellow');
		return bb;
	}).get();
	var b=nrSel.join(':');
	
	$("#c_val").val(nrSel.length);

	$("#sus_no_list").val(b);
	
	var nrSel=$('.nrCheckBox:checkbox:checked').map(function() {
		var bb1=$(this).attr('name');
		return bb1;
	}).get();
	var c=nrSel.join(':');
	$("#unit_name_list").val(c);
	
	drawtregn(c);
}
function drawtregn(data) {
	var ii=0;
	$("#tartable").empty();
	var datap=data.split(":");
	for (var i = 0; i <datap.length; i++) {
		var nkrow="<tr id='tarTableTr' padding='5px;'>";
		nkrow=nkrow+"<td>&nbsp;&nbsp;";
		nkrow=nkrow+ datap[i]+"</td>";
		$("#tartable").append(nkrow);
		ii=ii+1;
	}
	$("#tregn").text(ii);
}
//260194
function fnExcelReport()
{
	if($("#type_wep_eqpt").val() == "")
	{
		alert("Please select WPN CAT.");
	}
	/* else if($("#prf_code").val() == "0")
	{
		alert("Please select VEH TYPE.");
	} */
	else
	{
		var tab_text="<table><tr>";
	    var textRange; var j=0;
	    tab = document.getElementById('EqptStatusTbl'); 
		
	    for(j = 0 ; j < tab.rows.length ; j++) 
	    {
			tab_text=tab_text+tab.rows[j].innerHTML+"</tr>";
	    }
	    tab_text=tab_text+"</table>";
	    var ua = window.navigator.userAgent;
	    var msie = ua.indexOf("MSIE "); 
	    if (msie > 0 || !!navigator.userAgent.match(/Trident.*rv\:11\./)) 
	    {
	        txtArea1.document.open("txt/html","replace");
	        txtArea1.document.write(tab_text);
	        txtArea1.document.close();
	        txtArea1.focus(); 
	        sa=txtArea1.document.execCommand("SaveAs",true,"Say Thanks to Sumit.xls");
	    }  
	    else               
	        sa = window.open('data:application/vnd.ms-excel,' + encodeURIComponent(tab_text));  
	    return (sa);
	}
}


function exportToExcel(tableId, fileName) {
	var table = document.getElementById(tableId);
	var ws = XLSX.utils.table_to_sheet(table);
	var wb = XLSX.utils.book_new();
	XLSX.utils.book_append_sheet(wb, ws, "wpns_and_eqpts_line_dt");
	XLSX.writeFile(wb, fileName + ".xlsx");
}


</script>