<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %>
<%@ taglib prefix="dandelion" uri="http://github.com/dandelion" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
<!--  <script src="js/common/commonmethod.js" type="text/javascript"></script>  -->

<script type="text/javascript" src="js/miso/miso_js/jquery-1.12.3.js"></script>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script src="js/amchart4/core.js"></script>
<script src="js/amchart4/charts.js"></script>
<script src="js/amchart4/animated.js"></script>
<!-- <link rel="stylesheet" href="js/miso/tmsDashboard/tmsDashboardCSS.css"> -->


<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
  <script src="js/Calender/jquery-2.2.3.min.js"></script>
  <script src="js/Calender/jquery-ui.js"></script>
  <script src="js/Calender/datePicketValidation.js"></script>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link rel="stylesheet" href="js/InfiniteScroll/css/datatables.min.css">
<script type="text/javascript"
	src="js/InfiniteScroll/js/datatables.min.js"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/jquery.mockjax.min.js"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/datatables.mockjax.js"></script>

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/assets/adjestable_Col/jquery.resizableColumns.css">
<script src="js/assets/adjestable_Col/jquery.resizableColumns.js" type="text/javascript"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>

<div class="animated fadeIn">

	<div class="card">
		<div class="card-header" align="center">
			<h5>Tgt Submission & Finalization (OH/ MR)</h5>
		</div>
		<div class="card-body card-block">
					<div class="col-md-12" id="tpt">
						<div class="col-md-6">
							<div class="row form-group">
								<div class=" col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">*</strong> VEH CAT</label>
								</div>
								<div class="col-md-8">
									<select id="type_veh" class="form-control-sm form-control" style="width: 100%" onchange=""><!-- onchange="getPRFList(this.value)" -->
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
								<div class=" col-md-4">
									<label class=" form-control-label">PY</label>
								</div>
								<div class="col-md-8">
									 <select id="ddlYears" name="ddlYears" class="form-control-sm form-control" >
								    <option value='0'>--Select Year--</option>
								 </select>
								</div>
							</div>
						</div>
						
					
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class=" col-md-4">
									<label class=" form-control-label"><strong style="color: red;">*</strong>Nodal</label>
								</div>
								<div class="col-md-8">
									<select id="line_dte_sus" name="line_dte_sus" class="form-control-sm form-control" onchange="getMCTMainList();">
										${selectLine_dte}
										<c:forEach var="item" items="${getLine_DteList}"
											varStatus="num">
											<option value="${item.line_dte_sus}"
												name="${item.line_dte_name}">${item.line_dte_name}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div> 
						<div class="col-md-6">
							<div class="row form-group">
								<div class=" col-md-4">
									<label class=" form-control-label">SUB CAT(MCT 4)</label>
								</div>
								<div class="col-md-8">
									<!-- <input id="mct_main" name="mct_main" class="form-control-sm form-control"
										 onkeyup="MCTAutocomplete();" autocomplete="off"> -->
										 
									<select id="mct_main" name="mct_main" class="form-control-sm form-control">
										<option value="0">--Select--</option>
									</select>
									
								</div>
							</div>
						</div>
					</div>
					
							<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class=" col-md-4">
									<label class=" form-control-label">Type Of Intervention</label>
								</div>
								<div class="col-md-8">
									<select id="type_of_intervention" class="form-control-sm form-control" style="width: 100%" ><!-- onchange="getPRFList(this.value)" -->
										<option value="">--Select--</option>
										<option value="0">OH 1</option>
										<option value="1">OH 2 </option>
										<option value="2">OH 3</option>
										<option value="3">MR 1</option>
										<option value="4">MR 2</option>
										<option value="5">MR 3</option>
									</select>
								</div>
							</div>
						</div> 
			<div class="col-md-6">
						
								<div class="row form-group">
								<div class=" col-md-4">
									<label class=" form-control-label">Line Dte</label>
								</div>
								<div class="col-md-8">
									<select id="line_dte_sus" name="line_dte_sus" class="form-control-sm form-control" onchange="">
										${selectLine_dte}
										<c:forEach var="item" items="${getLine_DteList2}"
											varStatus="num">
											<option value="${item.line_dte_sus}"
												name="${item.line_dte_name}">${item.line_dte_name}</option>
										</c:forEach>
									</select>
								</div>
								
								
								
							</div>
						</div>
					</div>
					
					
					
					
		</div>
			<div class="form-control card-footer" align="center">
					<a href="QH_url" type="reset" class="btn btn-success btn-sm">Clear </a>
					<i class="action_icons searchButton"></i><input type="button"
						class="btn btn-info btn-sm" onclick="return Search();" value="Search">
						
				</div>
	<div id="data_table_div" style="display:none;"> 
			<div class="datatablediv">
				<div class="col-md-12" id="getSearch_Letter_a" style="display: block;">
				
						<span id="ip"></span>
						<table id="getSearch_tbl"
							class="table no-margin table-striped  table-hover  table-bordered  ">
							<thead>
								<tr>
									<th style="text-align: center;" id="ser_no"  rowspan=2>Ser No</th>
									<th style="text-align: center;" id="veh_cat" rowspan=2>Broad Cat(Prf)</th>
							<th style="text-align: center;" id="mct_no" rowspan=2>Veh Type(MCT 4)</th>
								<th style="text-align: center;" id="mct_no" rowspan=2> Nomen (MCT 10)</th>
									<th style="text-align: center;" id="service_mode" colspan=4>Service Mode</th>
									<th style="text-align: center;" id="industry_mode" colspan=4>Industry Mode</th>
									<th style="text-align: center;" id="total_tgt" colspan=4>TotalTgt (Service + Industry )</th>
									<th style="text-align: center;" id="remarks" rowspan=2>Remarks</th>
									<th style="text-align: center;" id="action" rowspan=2>Action</th>
								</tr>
								<tr>
								<th> NODAL</th>
								<th> EME</th>
								<th> OS</th>
								<th> MGS</th>
								<th> NODAL</th>
									<th> EME</th>
								<th> OS</th>
								<th> MGS</th>
								<th> NODAL</th>
									<th> EME</th>
								<th> OS</th>
								<th> MGS</th>
								</tr>
							</thead>
						</table>

				</div>

			</div>
		
		<div class="" align="center">
	 		<input type="button" class="btn btn-primary btn-sm" onclick="Save();" value="Submit All"> 
	 		<a href="QH_url" class="btn btn-success btn-sm" >Clear</a>  
		</div>
		</div>
	</div>
</div>



<script>
$(document).ready(function() {
	$("div#divwatermark").val('').addClass('watermarked');	
// 	watermarkreport();
getyear();

	
});
function getyear() 
{
        var ddlYears = document.getElementById("ddlYears");
        var currentYear = (new Date()).getFullYear();
        for (var i = 0; i <= 20; i++) {
            var option = document.createElement("OPTION");
            option.innerHTML = i+2024;
            option.value = i+2024;
            ddlYears.appendChild(option);
        }
}
var b=true;
function Search(){
	if ($('#type_veh').val() == "") {
		alert("Please Select Veh Cat");
		$('#type_veh').focus();
		return false;
	} 

	if ($('#line_dte_sus').val() == "0") {
		alert("Please Select Nodal Dte");
		$('#line_dte_sus').focus();
		return false;
	} 
	
	$("#data_table_div").show();
// 	colAdj("getSearch_tbl");
$("#WaitLoader").show();
if(b)
	{
	mockjax1('getSearch_tbl');
	table = dataTable11('getSearch_tbl');
	colAdj("getSearch_tbl");
	b=false;
	}
else{
	table.ajax.reload();
}
	
// 	table.ajax.reload();
	$("#WaitLoader").hide();
// 	colAdj("getSearch_tbl");
}
function data(tableName) {
	jsondata = [];
	var table = $('#' + tableName).DataTable();
	var info = table.page.info();
	var currentPage = info.page;
	var pageLength = info.length;
	var startPage = info.start;
	var endPage = info.end;
	var Search = table.search();
	var order = table.order();
	var orderColunm = $(table.column(order[0][0]).header()).attr('id');
	var orderType = order[0][1];
var type_of_veh =$("#type_veh").val();
var line_dte=$('#line_dte_sus').val();
var sub_cat=$('#mct_main').val();
var access='${roleAccess}';
	if (tableName == "getSearch_tbl" ) {

		$.post("get_count_oh?" + key + "=" + value, {
			Search : Search,
			type_of_veh:type_of_veh,
			line_dte:line_dte,
			sub_cat:sub_cat
		}, function(j) {
			FilteredRecords = j;
			
		});
		$.post("get_count_data_oh?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			type_of_veh:type_of_veh,
			line_dte:line_dte,
			orderColunm : orderColunm,
			orderType : orderType			,
			sub_cat:sub_cat
			
		}, function(j) {
			for (var i = 0; i < j.length; i++) {
				var sr = i + 1;
		
			//	var total = '<input type="text" disabled value="0"   id="total' + sr + '" value="'+j[i].total_tgt'"/>';
			if(access=="Line_dte")
				{
				var service_model = '<input type="text" size="1" value="'+j[i].service_mode+'" onkeyup="sum(' + sr + ')" onkeypress="return isNumber0_9Only(event);" style="display:block;" id="service_model' + sr + '"/>';
				var service_model_mgs = '<input type="text" size="1" value="'+j[i].service_mode_mgs+'" disabled onkeyup="sum_mgs(' + sr + ')" onkeypress="return isNumber0_9Only(event);" style="display:block;" id="service_model_mgs' + sr + '"/>';
				var service_model_eme = '<input type="text" size="1" value="'+j[i].service_mode_eme+'" disabled onkeyup="sum_eme(' + sr + ')" onkeypress="return isNumber0_9Only(event);" style="display:block;" id="service_model_eme' + sr + '"/>';
				var service_model_os = '<input type="text" size="1" value="'+j[i].service_mode_os+'" disabled onkeyup="sum_os(' + sr + ')" onkeypress="return isNumber0_9Only(event);" style="display:block;" id="service_model_os' + sr + '"/>';
				
				
				
				var industry_mod = '<input type="text" size="1" value="'+ j[i].industry_mode+'" onkeyup="sum(' + sr + ')" onkeypress="return isNumber0_9Only(event);" style="display:block;" id="industry_mod' + sr + '"/>';
				var industry_mod_mgs = '<input type="text" size="1" value="'+ j[i].industry_mode_mgs+'" disabled onkeyup="sum_mgs(' + sr + ')" onkeypress="return isNumber0_9Only(event);" style="display:block;" id="industry_mod_mgs' + sr + '"/>';
				
				var industry_mod_eme = '<input type="text" size="1" value="'+ j[i].industry_mode_eme+'" onkeyup="sum_eme(' + sr + ')" onkeypress="return isNumber0_9Only(event);" style="display:block;" id="industry_mod_eme' + sr + '"/>';
				var industry_mod_os = '<input type="text" size="1" value="'+ j[i].industry_mode_os+'" disabled onkeyup="sum_os(' + sr + ')" onkeypress="return isNumber0_9Only(event);" style="display:block;" id="industry_mod_os' + sr + '"/>';
				
				
				}
			else{

				var service_model = '<input type="text" size="1" value="'+j[i].service_mode+'"  onkeyup="sum(' + sr + ')" onkeypress="return isNumber0_9Only(event);" style="display:block;" id="service_model' + sr + '"/>';
				var service_model_mgs = '<input type="text" size="1" value="'+j[i].service_mode_mgs+'" onkeyup="sum_mgs(' + sr + ')" onkeypress="return isNumber0_9Only(event);" style="display:block;" id="service_model_mgs' + sr + '"/>';
				var industry_mod = '<input type="text" size="1" value="'+ j[i].industry_mode+'"   onkeyup="sum(' + sr + ')" onkeypress="return isNumber0_9Only(event);" style="display:block;" id="industry_mod' + sr + '"/>';
				var industry_mod_mgs = '<input type="text" size="1" value="'+ j[i].industry_mode_mgs+'" onkeyup="sum_mgs(' + sr + ')" onkeypress="return isNumber0_9Only(event);" style="display:block;" id="industry_mod_mgs' + sr + '"/>';
				var service_model_eme = '<input type="text" size="1" value="'+j[i].service_mode_eme+'"  onkeyup="sum_eme(' + sr + ')" onkeypress="return isNumber0_9Only(event);" style="display:block;" id="service_model_eme' + sr + '"/>';
				var service_model_os = '<input type="text" size="1" value="'+j[i].service_mode_os+'"  onkeyup="sum_os(' + sr + ')" onkeypress="return isNumber0_9Only(event);" style="display:block;" id="service_model_os' + sr + '"/>';
				
				var industry_mod_eme = '<input type="text" size="1" value="'+ j[i].industry_mode_eme+'" onkeyup="sum_eme(' + sr + ')" onkeypress="return isNumber0_9Only(event);" style="display:block;" id="industry_mod_eme' + sr + '"/>';
				var industry_mod_os = '<input type="text" size="1" value="'+ j[i].industry_mode_os+'"  onkeyup="sum_os(' + sr + ')" onkeypress="return isNumber0_9Only(event);" style="display:block;" id="industry_mod_os' + sr + '"/>';
					
				
			}
			
				var total = '<input type="text" size="1" value="'+j[i].total_tgt+'"  id="total' + sr + '" disabled/>';
				var total_mgs = '<input type="text" size="1" value="'+j[i].total_tgt_mgs+'"  id="total_mgs' + sr + '" disabled/>';
				var total_eme = '<input type="text" size="1" value="'+j[i].total_tgt_eme+'"  id="total_eme' + sr + '" disabled/>';
				var total_os = '<input type="text" size="1" value="'+j[i].total_tgt_os+'"  id="total_os' + sr + '" disabled/>';
			
				var remark = '<textarea class="form-control" id="REMARK' + sr + '" value="'+j[i].remark+'"></textarea>';
				var action = '<a class="btn btn-primary btn-sm" value="SAVE" title="SAVE" id="save_oh' + sr + '" onclick="save_fn(' + sr + ',' + j[i].mct_main_id + ',\'' + j[i].broadcat + ',1\');"><i class="fa fa-save"></i></a>';

	
				remark += '<input type="hidden" id="td_mct' + sr + '" value="' + j[i].mct_main_id + '">';
				remark += '<input type="hidden" id="td_broad' + sr + '" value="' + j[i].broadcat + '">';


				jsondata.push([sr, j[i].broadcat, j[i].mct4nomen,j[i].mct10nomen, service_model,service_model_eme,service_model_os,service_model_mgs, industry_mod,industry_mod_eme,industry_mod_os,industry_mod_mgs,total,total_eme,total_os,total_mgs, remark, action]);
				

				
			}
			
		});
	}

}

function save_fn(id,mct_main_id,mct_nomen,k)
{
	

var py =$("#ddlYears").val();
if(py=="0")
	{
	alert("Please Select PY ");
	return false;
	
	}
	var type_of_veh =$("#type_veh").val();
	var line_dte=$('#line_dte_sus').val();
			var totaltgt=  parseInt($("#total" + id).val()) || 0; 
			var totaltgt_mgs=   parseInt($("#total_mgs" + id).val()) || 0; 
			var totaltgt_eme=   parseInt($("#total_eme" + id).val()) || 0; 
			var totaltgt_os=   parseInt($("#total_os" + id).val()) || 0; 
			
		    var service_model = parseInt($("#service_model" + id).val()) || 0; 
		    var industry_mod = parseInt($("#industry_mod" + id).val()) || 0; 
		    
		    var service_model_mgs = parseInt($("#service_model_mgs" + id).val()) || 0; 
		    var industry_mod_mgs = parseInt($("#industry_mod_mgs" + id).val()) || 0; 
		    
		    var service_model_eme = parseInt($("#service_model_eme" + id).val()) || 0; 
		    var industry_mod_eme = parseInt($("#industry_mod_eme" + id).val()) || 0; 
		    
		    var service_model_os = parseInt($("#service_model_os" + id).val()) || 0; 
		    var industry_mod_os = parseInt($("#industry_mod_os" + id).val()) || 0; 
				var remark=$("#REMARK"+id).val();
				
						$.post("Save_Oh_single?" + key + "=" + value, {
							type_of_veh : type_of_veh,
							line_dte:line_dte,
							service_model:service_model,
							service_model_mgs:service_model_mgs,
							service_model_eme:service_model_eme,
							service_model_os:service_model_os,
							
							mct_main_id:mct_main_id,
							
							
							industry_mod:industry_mod,
							industry_mod_mgs:industry_mod_mgs,
							industry_mod_eme:industry_mod_eme,
							industry_mod_os:industry_mod_os,
							
							totaltgt:totaltgt,
							totaltgt_mgs:totaltgt_mgs,
							totaltgt_os:totaltgt_os,
							totaltgt_eme:totaltgt_eme,
							remark:remark,
							mct_nomen:mct_nomen,
							py:py
							
						}).done(function(j) {
							if(k!="0")
								{
								alert(j)
								}

						}).fail(function(xhr, textStatus, errorThrown) {});
	
	}


function Save()
{
	if(py=="0")
	{
	alert("Please Select PY ");
	return false;
	
	}
var k=0;
	 var table = document.getElementById("getSearch_tbl");
	    var data = [];
	    alert(table.rows.length);
		$("#WaitLoader").show();
	    // Iterate through each row in the table
	    for (var i = 1; i < table.rows.length-1; i++) { // Start from index 1 to skip header row
	      var row = table.rows[i];
			var mct_main_id= $("#td_mct" + i).val();
			var mct_nomen= $("#td_broad" + i).val();
	      save_fn(i,mct_main_id,mct_nomen,k)
	    
	    }
	    $("WaitLoader").hide();
	
	}


function isNumber0_9Only(evt) {
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if (!(charCode >= 48 && charCode <= 57) && charCode != 8) {
		return false;
	}
	return true;
}
function sum(id) {
    var service_model = parseInt($("#service_model" + id).val()) || 0; // Parsing input values to integers
    var industry_mod = parseInt($("#industry_mod" + id).val()) || 0; // If parsing fails, default to 0
    var sum = service_model + industry_mod; // Calculating sum
    $("#total" + id).val(sum); // Setting the value of the total field
}
function sum_mgs(id) {
    var service_model = parseInt($("#service_model_mgs" + id).val()) || 0; // Parsing input values to integers
    var industry_mod = parseInt($("#industry_mod_mgs" + id).val()) || 0; // If parsing fails, default to 0
    var sum = service_model + industry_mod; // Calculating sum
    $("#total_mgs" + id).val(sum); // Setting the value of the total field
}
function sum_eme(id) {
    var service_model = parseInt($("#service_model_eme" + id).val()) || 0; // Parsing input values to integers
    var industry_mod = parseInt($("#industry_mod_eme" + id).val()) || 0; // If parsing fails, default to 0
    var sum = service_model + industry_mod; // Calculating sum
    $("#total_eme" + id).val(sum); // Setting the value of the total field
}
function sum_os(id) {
    var service_model = parseInt($("#service_model_os" + id).val()) || 0; // Parsing input values to integers
    var industry_mod = parseInt($("#industry_mod_os" + id).val()) || 0; // If parsing fails, default to 0
    var sum = service_model + industry_mod; // Calculating sum
    $("#total_os" + id).val(sum); // Setting the value of the total field
}
	function getMCTMainList(){
// 		if ($('#type_veh').val() == "") {
// 			alert("Please Select Veh Cat");
// 			$('#type_veh').focus();
// 			return false;
// 		} 

// 		if ($('#line_dte_sus').val() == "0") {
// 			alert("Please Select Nodal Dte");
// 			$('#line_dte_sus').focus();
// 			return false;
// 		} 
		
		
		
		
		$.ajaxSetup({
	        async : false
		});
		
		
		var type_veh = $('#type_veh').val();
		var line_dte_sus = $('#line_dte_sus').val();
		var options = '<option value="0">' + "--Select--" + '</option>';
		if (line_dte_sus != "") {
			$.post("getMctNo4DigitDetailListforqfd?" + key + "=" + value, {line_dte_sus : line_dte_sus,type_veh:type_veh}).done(function(j) {
				for (var i = 0; i < j.length; i++) {
					if (j[i].mct_main_id == '${mct_main1}') {
						options += '<option value="'+j[i].mct_main_id+'" name="' + j[i].mct_nomen+ '" selected=selected>'+j[i].mct_main_id+"-"+j[i].mct_nomen + '</option>';
					} else {
						options += '<option value="'+j[i].mct_main_id+'" name="' + j[i].mct_nomen+ '" >'+j[i].mct_main_id+"-"+j[i].mct_nomen + '</option>';
					}
				}
				$("select#mct_main").html(options);
			}).fail(function(xhr, textStatus, errorThrown) {});
		} else {
			$("select#mct_main").html(options);
		}
	}
</script>