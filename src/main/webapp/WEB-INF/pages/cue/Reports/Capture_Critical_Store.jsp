<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

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

<form:form id="CCSForm">
	<div class="animated fadeIn" id="printableArea">
		<div class="container">
			<div class="card">
				<div class="card-header" align="center">
					<h5>CAPTURE CRITICAL STORE (Veh/EQPT)</h5>
				</div>
				<div class="card-body">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label"><strong style="color: red;">*</strong> Type</label>
								</div>
								<div class="col-12 col-md-8">
									<input type="hidden" id="id" name="id"> 
									<select id="c_type" name="c_type" class="form-control-sm form-control" onchange="sus_nowithUnitName_list()" style="width: 100%">
										<option value="0">--Select--</option>
										<option value="A">A</option>
										<option value="B">B</option>
										<option value="C">C</option>
										<option value="W_E">WEP/EQPT</option>
									</select>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label"><strong style="color: red;">*</strong> Category</label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="category" name="category" class="form-control-sm form-control" autocomplete="off" />
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12 "
						style='background-color: mistyrose; padding: 7px;'>
						<div class="col-md-5" align="left">
							<b><input type=checkbox id='nSelAll' name='tregn' onclick="callsetall();">&nbsp;Select all (<span id="sregn" style='font-size: 14px;'>0</span>)</b>&nbsp;&nbsp; 
							   <input id="InputSearch" type="text" placeholder="Search Vehicle name .." size="20">
						</div>
						<div class="col-md-5" align="right">
							<b>Selected Vehicles - <span id="tregn" style='font-size: 14px;'>0</span></b>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6" style="height: 200px; overflow: auto; width: 99%; border: 1px solid #000; text-align: left;" id="srctable"></div>
						<div class="col-md-6" style="height: 200px; overflow: auto; width: 99%; border: 1px solid #000; text-align: left;" id="tartable"></div>
						<input type="hidden" id="c_val" name="c_val" value="0">
					</div>
				</div>
				<div class="form-control card-footer" align="center">
					<a href="capture_critical_strore" class="btn btn-success btn-sm" style="border-radius: 5px;">Clear</a> 
					<a href="#" class="btn btn-primary btn-sm" style="border-radius: 5px;" onclick="saveFun();">Save</a>
				</div>
			</div>
		</div>
	</div>
</form:form>

<div class="container" align="center">
		<div class="col-md-12">
			<table id="capture_critical_store_reporttbl"
				class="display table no-margin table-striped table-hover table-bordered">
				<thead>
					<tr>
						<th width="10%" style="text-align:center;">C Type</th>
						<th width="15%"  style="text-align:center;">Category</th>
						<th width="10%"  style="text-align:center;">Code</th>
						<th>Nomenclature</th>
						<th width="10%"  style="text-align:center;">Action</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	
<c:url value="deletecapture_critical_storeUrl" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm" name="deleteForm" modelAttribute="deleteid">
<input type="hidden" name="deleteid" id="deleteid"/>
</form:form>

<script>

var table = null;
$(document).ready(function () {
	mockjax1('capture_critical_store_reporttbl');
	table = dataTable('capture_critical_store_reporttbl',[0,1,2,4],[]);
	$('#btn-reload').on('click', function(){
		table.ajax.reload();
	});
});

function data(tableName){
	jsondata = [];
	var info = table.page.info();
	var currentPage = info.page;
    var pageLength = info.length;
	var startPage = info.start;
	var endPage = info.end;
	var Search = table.search();
	var order = table.order();
	var orderColunm = order[0][0] + 1;
	var orderType = order[0][1];

	$.post("getCapture_critical_storeReportDataList?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,orderType:orderType},function(j) {
		for (var i = 0; i < j.length; i++) {
			jsondata.push([j[i].c_type, j[i].category, j[i].code, j[i].nomenc,j[i].id]);
		}
	});
	$.post("getCapture_critical_storeTotalCount?"+key+"="+value,{Search:Search},function(j) {
		FilteredRecords = j;
	});
}

function sus_nowithUnitName_list() {
	var type_of_veh = $("#c_type").val();
	if(type_of_veh == "W_E"){
		$.post("getWepone_Eqp?" + key + "=" + value, {
		type_of_veh : type_of_veh
		}, function(data) {
		}).done(function(data) {
			if (data == "") {
				$("#srctable").empty();
				$("#tartable").empty();
				//alert("No Regd Data Found");
			} else {
				$("#d_reg").show();
				$("#d_reg2").show();
				$("#d_reg3").show();
				$("#srctable").val(data);
				drawregn(data);
			}
		}).fail(function(xhr, textStatus, errorThrown) {
		});
		
	}else{
		
		$.post("getVEHICLELISTbyType?" + key + "=" + value, {
			type_of_veh : type_of_veh
		}, function(data) {
		}).done(function(data) {
			if (data == "") {
				$("#srctable").empty();
				$("#tartable").empty();
				//alert("No Regd Data Found");
			} else {
				$("#d_reg").show();
				$("#d_reg2").show();
				$("#d_reg3").show();
				$("#srctable").val(data);
				drawregn(data);
			}
		}).fail(function(xhr, textStatus, errorThrown) {
		});
	}
}
function drawregn(j) {
	var ii = 0;
	$("#srctable").empty();
	$("#tartable").empty();
	for (var i = 0; i < j.length; i++) {
		var row = "<tr id='SRC"+j[i][1]+"' padding='5px;'>";
		row = row
				+ "<td>&nbsp;<input class='nrCheckBox' type=checkbox id='"
				+ j[i][1] + "' name='" + j[i][0]
				+ "' onclick='findselected();'>&nbsp;";
		row = row + j[i][1] + " - " + j[i][0] + "</td>";
		$("#srctable").append(row);
		ii = ii + 1;
	}
	$("#sregn").text(ii);
}
function callsetall() {
	var chkclk = $('#nSelAll').prop('checked');
	if (chkclk) {
		$('.nrCheckBox').prop('checked', true);

	} else {
		$('.nrCheckBox').prop('checked', false);
	}
	findselected();
}

	var code = '';
	var nomenc = '';

	function findselected() {
		$("#srctable tr").css('background-color', 'white');
		var nrSel = $('.nrCheckBox:checkbox:checked').map(function() {
			var bb = $(this).attr('id');
			$("#SRC" + bb).css('background-color', 'yellow');
			return bb;
		}).get();
		var b = nrSel.join(':');
		$("#c_val").val(nrSel.length);
		//$("#sus_no_list").val(b);
		code = b;
		var nrSel = $('.nrCheckBox:checkbox:checked').map(function() {
			var bb1 = $(this).attr('name');
			return bb1;
		}).get();
		var c = nrSel.join(':');
		//$("#unit_name_list").val(c);
		nomenc = c;
		drawtregn(c);
	}
	function drawtregn(data) {
		var ii = 0;
		$("#tartable").empty();
		var datap = data.split(":");
		for (var i = 0; i < datap.length; i++) {
			var nkrow = "<tr id='tarTableTr' padding='5px;'>";
			nkrow = nkrow + "<td>&nbsp;&nbsp;";
			nkrow = nkrow + datap[i] + "</td>";
			$("#tartable").append(nkrow);
			ii = ii + 1;
		}
		$("#tregn").text(ii);
	}
	$("#InputSearch").on("keyup", function() {
		var value = $(this).val().toLowerCase();
		$("#srctable tr").filter(function() {
		$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		});
	});

function saveFun() {
	if ($("select#c_type").val() == "0") {
		alert("Please Select Type");
		$("select#c_type").focus();
		return false;
	}
	if ($("input#category").val().trim() == "") {
		alert("Please Enter Category.");
		$("input#category").focus();
		return false;
	}
	if (code == "") {
		alert("Please Select Item")
		return false;
	}
	var id = $('#id').val();
	var formdata = $('#CCSForm').serialize();
	formdata += "&id=" + id;
	formdata += "&code=" + code;
	formdata += "&nomenc=" + nomenc;
	$.post('C_C_Saction?' + key + "=" + value, formdata, function(data) {
		if (parseInt(data) > 0) {
			alert("Data Saved SuccessFully");
			table.ajax.reload();
			return false;
		} else {
			alert(data);
		}
	}).fail(function(xhr, textStatus, errorThrown) {
		alert("fail to fetch")
	});
}

function deleteData(obj){
    document.getElementById('deleteid').value=obj;
    document.getElementById('deleteForm').submit();
}
</script>