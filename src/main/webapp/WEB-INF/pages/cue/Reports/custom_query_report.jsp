<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script src="js/InfiniteScroll/js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 

<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js"></script>
<script src="js/cue/printAllPages.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="js/InfiniteScroll/js/datatables.min.js"></script>
<script type="text/javascript" src="js/InfiniteScroll/js/jquery.mockjax.min.js"></script>
<script type="text/javascript" src="js/InfiniteScroll/js/datatables.mockjax.js"></script>


<script>
var username="${username}"; 
var curDate = "${curDate}";
$(document).ready(function (){
	$('#btn_reset').on('click', function(){
    	table.ajax.reload();
    });
	
	var table = "";
	$('#btn_search').on('click', function(){
		if($("#type").val() == "1"){
			if($("#type_veh").val() == ""){
				$("#type_veh").focus();
				alert("Please select VEH CAT.");
			}else if($("#prf_code").val() == "0"){
				$("#prf_code").focus();
				alert("Please select VEH TYPE.");
			}else{
				if(table == ""){
					$('#vehicleStatusTbl').show();
					mockjax1('vehicleStatusTbl');
					table = dataTable('vehicleStatusTbl',[0,2,3],[]);
				}else{
					table.ajax.reload();
				}
			}
		}else if($("#type").val() == "2"){
			
		}else if($("#type").val() == "3"){
			
		}else{
			alert("Please Select Type");
			$("#type").focus();
		}
    });
	//sus_nowithUnitName_list();
	/* $("#InputSearch").on("keyup", function() {
		var value = $(this).val().toLowerCase();
		$("#srctable tr").filter(function() {
		$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		});
	}); */
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
	//alert("currentPage=="+currentPage  + " \npageLength=="+pageLength + "\nstartPage="+ startPage + "\nendPage="+ endPage + "\nSearch==" + Search +"\n OrderColunm ="+orderColunm +"\n OrderType ="+orderType);

	// No Change
	
	// Advanced Search Parameter
	
	var type_veh = $("#type_veh").val();
	var prf_code = $("#prf_code").val();
	var mct_main = $("#mct_main").val();
	var cont_comd = $("#cont_comd").val();
	var cont_corps = $("#cont_corps").val();
	var cont_div = $("#cont_div").val();
	var cont_bde = $("#cont_bde").val();
	var sus_no = $("#sus_no").val();
	var line_dte_sus = $("#line_dte_sus").val();
	// Advanced Search Parameter
	
	$.post("CustomQueryTPTList?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,orderType:orderType,
		type_veh:type_veh,prf_code:prf_code,mct_main:mct_main,cont_comd:cont_comd,cont_corps:cont_corps,cont_div:cont_div,cont_bde:cont_bde,sus_no:sus_no,line_dte_sus:line_dte_sus},function(j) {
		for (var i = 0; i < j.length; i++) {
			jsondata.push([j[i].mct_main_id,j[i].mct_nomen,j[i].ue,j[i].total_uh]);
		}
	});
	$.post("CustomQueryTPTCount?"+key+"="+value,{Search:Search,type_veh:type_veh,prf_code:prf_code,mct_main:mct_main,cont_comd:cont_comd,
		cont_corps:cont_corps,cont_div:cont_div,cont_bde:cont_bde,sus_no:sus_no,line_dte_sus:line_dte_sus},function(j) {
		FilteredRecords = j;
	});
	jQuery("#WaitLoader").hide();
}

/* function sus_nowithUnitName_list(){
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
} */

function exportFn(){
	if($("#type_veh").val() == "") {
		alert("Please select VEH CAT.");
	}else if($("#prf_code").val() == "0"){
		alert("Please select VEH TYPE.");
	}else{
		$("#type_veh1").val($("#type_veh").val());
		$("#prf_code1").val($("#prf_code").val());
		$("#mct_main1").val($("#mct_main").val());
		$("#cont_comd1").val($("#cont_comd").val());
		$("#cont_corps1").val($("#cont_corps").val());
		$("#cont_div1").val($("#cont_div").val());
		$("#cont_bde1").val($("#cont_bde").val());
		$("#sus_no1").val($("#sus_no").val());
		$("#line_dte_sus1").val($("#line_dte_sus").val());
		document.getElementById('exportUserListForm').submit();
	}
}

</script> 
<c:url value="vehicle_status_line_dteExport" var="exportUrl" />
<form:form action="${exportUrl}" method="post" id="exportUserListForm" name="exportUserListForm" modelAttribute="type_veh">
	<input type="hidden" name="type_veh1" id="type_veh1" value="0"/>
	<input type="hidden" name="prf_code1" id="prf_code1" value="0"/>
	<input type="hidden" name="mct_main1" id="mct_main1" value="0"/>
	<input type="hidden" name="cont_comd1" id="cont_comd1" value="0"/>
	<input type="hidden" name="cont_corps1" id="cont_corps1" value="0"/>
	<input type="hidden" name="cont_div1" id="cont_div1" value="0"/>
	<input type="hidden" name="cont_bde1" id="cont_bde1" value="0"/>
	<input type="hidden" name="sus_no1" id="sus_no1" value="0"/>
	<input type="hidden" name="line_dte_sus1" id="line_dte_sus1" value="0"/>
</form:form>

<style>
	.dataTables_filter { display: none; }
</style>

<div class="animated fadeIn" id="printableArea">
	<div class="">
    	<div class="col-md-12" align="center">
    		<div class="card">
    			<div class="card-header" align="center"> <h5>CUSTOM QUERY</h5> </div>
    				<div class="card-body">
    					<div class="col-md-12">
    						<div class="col-md-6">
								<div class="row form-group">
							    	<div class="col col-md-4">
							        	<label class=" form-control-label"><strong style="color: red;">*</strong> TYPE</label>
							       	</div>
							        <div class="col-12 col-md-8">
										<select id="type"  class="form-control-sm form-control" onchange="getTypeList(this.value)" style="width: 100%">
											<option value="">--Select--</option>
											<option value="1">TPT</option>
											<!-- <option value="2">WPN</option>
											<option value="3">PERS</option> -->
										</select>
									</div>
								</div>		
							</div>
						</div>
    					<div class="col-md-12" style="display: none;" id="tpt">
    						<div class="col-md-4">
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
							<div class="col-md-4">
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
							<div class="col-md-4">
								<div class="row form-group">
							    	<div class="col col-md-4">
							        	<label class=" form-control-label">SUB CAT</label>
							       	</div>
							        <div class="col-12 col-md-8">
										<select id="mct_main"  class="form-control-sm form-control" style="width: 100%">
										</select>
									</div>
								</div>		
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
	                				<div class="col col-md-4">
	                  					<label class=" form-control-label">Line Dte</label>
	                				</div>
	                				<div class="col-12 col-md-8">
	                 					<select id="line_dte_sus" name="line_dte_sus" class="form-control-sm form-control">
	                 						${selectLine_dte}
	                 						<c:forEach var="item" items="${getLine_DteList}" varStatus="num" >
                  								<option value="${item.line_dte_sus}"  name="${item.line_dte_name}" >${item.line_dte_name}</option>
                  							</c:forEach>
	                 					</select>
	                				</div>
				            	</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
							    	<div class="col col-md-4">
							        	<label class=" form-control-label"> Command</label>
							       	</div>
							        <div class="col-12 col-md-8">
							           	<select id="cont_comd" name="cont_comd" class="form-control-sm form-control">
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
		                  				<label class="form-control-label"> Corps</label>
		               				</div>
		                			<div class="col-12 col-md-8">
		                 				<select id="cont_corps" name="cont_corps" class="form-control-sm form-control">
		                 					${selectcorps}
		                 					<c:forEach var="item" items="${getCorpsList}" varStatus="num" >
                  								<option value="${item[0]}"  name="${item[1]}" >${item[1]}</option>
                  							</c:forEach>
		                 				</select>
		                			</div>
				              	</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
					                <div class="col col-md-4">
					                  <label class=" form-control-label"> Division</label>
					                </div>
					                <div class="col-12 col-md-8">
					                 	<select id="cont_div" name="cont_div" class="form-control-sm form-control" >
					                 		${selectDiv}
					                 		<c:forEach var="item" items="${getDivList}" varStatus="num" >
                  								<option value="${item[0]}"  name="${item[1]}" >${item[1]}</option>
                  							</c:forEach>
					                 	</select>
					                </div>
					            </div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
	                				<div class="col col-md-4">
	                  					<label class=" form-control-label"> Brigade</label>
	                				</div>
	                				<div class="col-12 col-md-8">
	                 					<select id="cont_bde" name="cont_bde" class="form-control-sm form-control" >
	                 						${selectBde}
	                 						<c:forEach var="item" items="${getBdeList}" varStatus="num" >
                  								<option value="${item[0]}"  name="${item[1]}" >${item[1]}</option>
                  							</c:forEach>
	                 					</select>
	                				</div>
				            	</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
	          					<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">Unit Name </label>
									</div>
									<div class="col-12 col-md-8">			
										<textarea id="unit_name" name="unit_name" class="form-control autocomplete" style="font-size: 12px;" autocomplete="off" maxlength="100" placeholder="select Unit Name"></textarea>
									</div>
								</div>
	          				</div>
	          				<div class="col-md-6">
	          					<div class="row form-group">
                					<div class="col col-md-4">
                  						<label class=" form-control-label">SUS No</label>
						            </div>
						            <div class="col-12 col-md-8">
										<input type="text" id="sus_no" name="sus_no"  class="form-control autocomplete" maxlength="8" autocomplete="off" placeholder="Select SUS No">
									</div>
              					</div>
	          				</div>
	          			</div>
						<!--<div class="col-md-12 " style='background-color:mistyrose;padding:7px;'>
							<div class="col-md-5" align="left">
								<b><input type=checkbox id='nSelAll' name='tregn' onclick="callsetall();">&nbsp;Select all (<span id="sregn" style='font-size:14px;'>0</span>)</b>&nbsp;&nbsp;
								<input id="InputSearch" type="text" placeholder="Search unit name .." size="20">
							</div>   
							<div class="col-md-5" align="right">
								<b>Selected Units - <span id="tregn" style='font-size:14px;'>0</span></b>
							</div>
						</div>-->
						<!-- <div  class="col-md-12">
							<div class="col-md-6" style="height: 200px;overflow:auto;width:99%;border:1px solid #000;text-align: left;" id="srctable"></div>
							<div class="col-md-6" style="height: 200px;overflow:auto;width:99%;border:1px solid #000;text-align: left;" id="tartable"></div>
							<input type="hidden" id="c_val" name="c_val" value="0">
							 <input type="hidden" id="sus_no_list" name="sus_no_list" placeholder="" class="form-control-sm nrform-control">
		                      <input type="hidden" id="unit_name_list" name="unit_name_list" placeholder="" class="form-control-sm nrform-control">
						</div> -->
	          		</div>
	    			<div class="form-control card-footer" align="center" id="buttonDiv">
	    				<a href="vehicle_status_line_dte" class="btn btn-success btn-sm" style="border-radius: 5px;">Clear</a>  
						<button id="btn_search" class="btn btn-primary btn-sm" style="border-radius: 5px;">Search</button>
						<!-- <i class="fa fa-download"></i><input type="button" id="exportId" class="btn btn-sm btn_report" style="background-color: #e37c22;color: white;" value="Export" onclick="exportFn();"> -->
	             	</div>
	             	<div class="card-body">
	             		<div class="" id="reportDiv" >
							<div class="col-md-12">
								<%-- <div align="right"><h6>Total Count : ${list.size()}</h6></div> --%>
					   			<div  class="watermarked" data-watermark="" id="divwatermark">
									<span id="ip"></span>
									<table id="vehicleStatusTbl" style="width: 100%;display: none;" class="table no-margin table-striped"> <!-- class="table no-margin table-striped  table-hover  table-bordered report_print" style="margin-bottom: 0rem;" -->
										<thead>
							        		<tr>
					                            <th style="text-align: center;width:10%;">Code</th>
										        <th style="text-align: center;width:60%;">Nomenclature</th>
										        <th style="text-align: center;width:15%;">UE</th>
												<th style="text-align: center;width:15%;">Total UH</th>
											</tr>
										</thead>
										<tbody> </tbody>
									</table>
								</div>
							</div>
						</div>
	             	</div> 
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

<%-- <c:url value="vehicle_status_details" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="code_value1">
	<input type="hidden" name="type_veh1" id="type_veh1" value="0"/>
	<input type="hidden" name="mct_main1" id="mct_main1" value="0"/>
	<input type="hidden" name="prf_code1" id="prf_code1" value="0"/>
	<input type="hidden" name="sus_no1" id="sus_no1" />
	<input type="hidden" name="unit_name1" id="unit_name1" />
	<input type="hidden" name="cont_comd1" id="cont_comd1"/>
	<input type="hidden" name="cont_corps1" id="cont_corps1" value="0"/>
	<input type="hidden" name="cont_div1" id="cont_div1" value="0"/>
	<input type="hidden" name="cont_bde1" id="cont_bde1" value="0"/>
	<input type="hidden" name="line_dte_sus1" id="line_dte_sus1" value="0"/>
</form:form>  --%>

<script>
$(document).ready(function() {
	$("div#divwatermark").val('').addClass('watermarked');	
	watermarkreport();
    
	var select = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
   	$('select#cont_comd').change(function() {
	   	var fcode = this.value;
	   	if(fcode == "0"){
	   		$("select#cont_corps").html(select);
	   		$("select#cont_div").html(select);
	   		$("select#cont_bde").html(select);
   		}else{	
   			$("select#cont_corps").html(select);
	   		$("select#cont_div").html(select);
	   		$("select#cont_bde").html(select);
	   		
	   		getCONTCorps(fcode);
	    
	    	fcode += "00";	
	   		getCONTDiv(fcode);
	       	
	       	fcode += "000";	
	   		getCONTBde(fcode);
   		}
	});
	$('select#cont_corps').change(function() {
	   	var fcode = this.value;
   	   	if(fcode == "0"){
   	   		$("select#cont_div").html(select);
   	   		$("select#cont_bde").html(select);
	   	}else{
	   		$("select#cont_div").html(select);
   	   		$("select#cont_bde").html(select);
	   	   	getCONTDiv(fcode);
	       	fcode += "000";	
	   		getCONTBde(fcode);
	   	}
	});
	$('select#cont_div').change(function() {
	   		var fcode = this.value;    	   	
	   		if(fcode == "0"){
	 		$("select#cont_bde").html(select)
	   	}else{
	   		$("select#cont_bde").html(select)
		   		getCONTBde(fcode);
	   	}
	});
});
function getTypeList(val){
	if(val == "1"){
		$("div#tpt").show();
	}else if(val == "2"){
		$("div#tpt").hide();
	}else if(val == "3"){
		$("div#tpt").hide();
	}else{
		$("div#tpt").hide();
	}
}
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

/*function Search(){
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
		$("#type_veh1").val($("#type_veh").val());
		$("#prf_code1").val($("#prf_code").val());
		$("#mct_main1").val($("#mct_main").val());
		$("#cont_comd1").val($("#cont_comd").val());
		$("#cont_corps1").val($("#cont_corps").val());
		$("#cont_div1").val($("#cont_div").val());
		$("#cont_bde1").val($("#cont_bde").val());
		$("#unit_name1").val($("#unit_name").val());
		$("#sus_no1").val($("#sus_no").val());
		$("#line_dte_sus1").val($("#line_dte_sus").val());
		
		document.getElementById('searchForm').submit();
	}
} */

function getCONTCorps(fcode){
 	var fcode1 = fcode[0];
		$.post("getCorpsDetailsList?"+key+"="+value,{fcode:fcode1}, function(j) {
			if(j != ""){
				var length = j.length-1;
				var enc = j[length][0].substring(0,16);
				var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
				
				for ( var i = 0; i < length; i++) {
					if('${cont_corps1}' ==  dec(enc,j[i][0])){
						options += '<option value="' + dec(enc,j[i][0]) +'" name="'+dec(enc,j[i][1])+'" selected=selected >'+ dec(enc,j[i][1]) + '</option>';
					}else{
						options += '<option value="' + dec(enc,j[i][0]) +'" >'+ dec(enc,j[i][1]) + '</option>';
					}
				}	
				$("select#cont_corps").html(options);
			}
		});
 } 
 function getCONTDiv(fcode){
 	var fcode1 = fcode[0] + fcode[1] + fcode[2];
	   	$.post("getDivDetailsList?"+key+"="+value,{fcode:fcode1}, function(j) {
	   		if(j != ""){
 		   	var length = j.length-1;
			var enc = j[length][0].substring(0,16);
			var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
			for ( var i = 0; i < length; i++) {
				if('${cont_div1}' ==  dec(enc,j[i][0])){
					options += '<option value="' + dec(enc,j[i][0]) +'" name="'+dec(enc,j[i][1])+'" selected=selected >'+ dec(enc,j[i][1]) + '</option>';
				}else{
					options += '<option value="' + dec(enc,j[i][0]) +'" >'+ dec(enc,j[i][1]) + '</option>';
				}
			}
		   		$("select#cont_div").html(options);
	   		}
		});
 } 
	function getCONTBde(fcode){
		var fcode1 = fcode[0] + fcode[1] + fcode[2] + fcode[3] + fcode[4] + fcode[5];
		$.post("getBdeDetailsList?"+key+"="+value,{fcode:fcode1}, function(j) {
			if(j != ""){
				var length = j.length-1;
			var enc = j[length][0].substring(0,16);
			var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
			jQuery("select#cont_bde").html(options);
			for ( var i = 0; i < length; i++) {
				if('${cont_bde1}' ==  dec(enc,j[i][0])){
					options += '<option value="' +  dec(enc,j[i][0])+ '" name="'+dec(enc,j[i][1])+'" selected=selected>'+  dec(enc,j[i][1]) + '</option>';
					$("#cont_bname").val(dec(enc,j[i][1]));
				}else{
					options += '<option value="' +  dec(enc,j[i][0]) +'" name="'+dec(enc,j[i][1])+'">'+ dec(enc,j[i][1]) + '</option>';
				}
			}	
			$("select#cont_bde").html(options);
			}
		});
	}
	
jQuery(function() {
	// Source SUS No
	jQuery("#sus_no").keypress(function(){
		var sus_no = this.value;
			 var susNoAuto=jQuery("#sus_no");
			  susNoAuto.autocomplete({
			      source: function( request, response ) {
			        jQuery.ajax({
			        type: 'POST',
			        url: "getTargetSUSNoList?"+key+"="+value,
			        data: {sus_no:sus_no},
			          success: function( data ) {
			        	  var susval = [];
			        	  var length = data.length-1;
			        	  if(data.length != 0){
				        		var enc = data[length].substring(0,16);
				        	}
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
			        	  document.getElementById("unit_name").value="";
			        	  susNoAuto.val("");	        	  
			        	  susNoAuto.focus();
			        	  return false;	             
			          }   	         
			      }, 
			      select: function( event, ui ) {
			    	var sus_no = ui.item.value;
			    	$.post("getActiveUnitNameFromSusNo?"+key+"="+value, {sus_no:sus_no}).done(function(j) {				
			    		var length = j.length-1;
					   	var enc = j[length].substring(0,16);
					   	jQuery("#unit_name").val(dec(enc,j[0]));	
			    	}).fail(function(xhr, textStatus, errorThrown) {
			    });
			} 	     
		});	
	});
	// End
	
	// Source Unit Name
   jQuery("#unit_name").keyup(function(){
 			var unit_name = this.value;
				 var susNoAuto=jQuery("#unit_name");
				  susNoAuto.autocomplete({
				      source: function( request, response ) {
				        jQuery.ajax({
				        type: 'POST',
				        url: "getTargetUnitsNameActiveList?"+key+"="+value,
				        data: {unit_name:unit_name},
				          success: function( data ) {
				        	  var susval = [];
				        	  var length = data.length-1;
				        	  if(data.length != 0){
					        		var enc = data[length].substring(0,16);
					        	}
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
				        	  document.getElementById("sus_no").value="";
				        	  susNoAuto.val("");	        	  
				        	  susNoAuto.focus();
				        	  return false;	             
						}   	         
					}, 
					select: function( event, ui ) {
						var target_unit_name = ui.item.value;
					 	$.post("getTargetSUSFromUNITNAME?"+key+"="+value, {target_unit_name:target_unit_name}).done(function(j) {				
					 		 var length = j.length-1;
	 				         var enc = j[length].substring(0,16);
	 				         jQuery("#sus_no").val(dec(enc,j[0]));	
						}).fail(function(xhr, textStatus, errorThrown) {
					});
				}
			}); 			
 		});
	});
</script>