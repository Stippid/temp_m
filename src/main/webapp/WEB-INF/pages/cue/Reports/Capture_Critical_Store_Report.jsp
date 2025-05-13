<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script src="js/InfiniteScroll/js/jquery-1.11.0.js"></script>
<link rel="stylesheet" href="js/InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="js/InfiniteScroll/js/datatables.min.js"></script>
<script type="text/javascript" src="js/InfiniteScroll/js/jquery.mockjax.min.js"></script>
<script type="text/javascript" src="js/InfiniteScroll/js/datatables.mockjax.js"></script>

<head>
<style>
.bg-red {
	background-color: #902018 !important;
	color: #fff;
}

.bg-indigo {
	background-color: #3F51B5 !important;
	color: #fff;
}

.bg-green {
	background-color: #1d8088 !important;
	color: #fff;
}


.bg-yellow {
	background-color: #927215 !important;
	color: #fff;
}


.info-boxcharts {
	box-shadow: 0 2px 10px rgba(0, 0, 0, 0.2);
	height: 150px;
	/* width :500px; */
	display: flex;
	cursor: default;
	background-color: #fff;
	position: relative;
	overflow: hidden;
	margin-bottom: 10px;
	box-shadow: 5px 2px 8px 2px #888888;
}

.info-boxcharts .icon {
	display: inline-block;
	text-align: center;
	background-color: rgba(0, 0, 0, 0.12);
	width: 80px;
}

.info-boxcharts .content {
	display: inline-block;
	padding: 18% 0px 0px;
}

.info-boxcharts .icon i {
	color: #fff;
	font-size: 50px;
	line-height: 80px;
}

.info-tables {
	box-shadow: 0 2px 10px rgba(0, 0, 0, 0.2);
	height: 150px;
	display: flex;
	cursor: default;
	background-color: #fff;
	position: relative;
	overflow: hidden;
	margin-bottom: 10px;
	box-shadow: 5px 2px 8px 2px #888888;
}

.info-tables .icon {
	display: inline-block;
	text-align: center;
	background-color: rgba(0, 0, 0, 0.12);
	width: 80px;
}

.info-tables .content {
	display: inline-block;
	padding: 18% 0px 0px;
}

.info-tables .icon i {
	color: #fff;
	font-size: 50px;
	line-height: 80px;
}

.content h5 {
	text-transform: uppercase;
	font-size: 15px;
	text-align: center;
	color: white;
	font-weight: bold;
}
table.dataTable {
    width: 100% !important;
    overflow-x: auto;
}
.dataTables_scrollHeadInner {
    width: 100% !important;
}
.dataTables_scrollBody {
    overflow: inherit !important;
    width: 100%;
}
</style>
</head>

<script>

</script>
<div class="container">
			<div class="card">
				<div class="card-header" align="center">
					<h5 style="color:black;">CAPTURE CRITICAL REPORT (VEH/EQPT)</h5>
				</div>
				<div class="card-body">

	<div class="col-md-12">
		<div class="col-md-3">
			<div class="info-boxcharts bg-red">
				<div class="content" onclick="call_datatable('A');">

					<h5 style="font-weight: bold; font-size: 28px;">A Vehicle</h5>
					</a> <label id="totalactiveUnits"></label>
				</div>
			</div>
		</div>

		<div class="col-md-3">
			<div class="info-tables bg-green">
				<div class="content" onclick="call_datatable('B');">

					<h5 style="font-weight: bold; font-size: 28px;">B Vehicle</h5>
					</a> <label id="totalactiveUnits"></label>
				</div>
			</div>
		</div>

		<div class="col-md-3">
			<div class="info-boxcharts bg-indigo">
				<div class="content" onclick="call_datatable('C');">

					<h5 style="font-weight: bold; font-size: 28px;">C Vehicle</h5>
					</a> <label id="totalactiveUnits"></label>
				</div>
			</div>
		</div>

		<div class="col-md-3">
			<div class="info-tables bg-yellow">
				<div class="content" onclick="call_datatable('W_E');">

					<h5 style="font-weight: bold; font-size: 28px;">WEP/EQPT</h5>
					</a> <label id="totalactiveUnits"></label>
				</div>
			</div>
		</div>
	</div>
	<div class="col-md-12" id="ccsr_div">
		<table id="capture_critical_store_reporttbl"
			class="display table no-margin table-striped  table-hover  table-bordered">
			<thead>
				<tr>
					<th width="10%">CODE</th>
					<th>NOMEN</th>
					<th width="10%">CATEGORY</th>
					<th width="10%">UE</th>
					<th width="10%">UH</th>
				</tr>
			</thead>
		</table>
	</div>
</div>
</div>
</div>
<script>
var c_type_l='';
$(document).ready(function(){
	$("#ccsr_div").hide();
	mockjax1('capture_critical_store_reporttbl');
	table = dataTable('capture_critical_store_reporttbl',[0,3,4],[]);
	$('#btn-reload').on('click', function(){
		table.ajax.reload();
	});
	});
	
	
	function call_datatable(val){
		c_type_l=val;
		table.ajax.reload();
		$("#ccsr_div").show();
	}
	
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
		var c_type =c_type_l;
		
		$.post("getCaptureReportBYtype?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,orderType:orderType,c_type:c_type},function(j) {
			for (var i = 0; i < j.length; i++) {
				jsondata.push([j[i].code, j[i].nomenc, j[i].category, j[i].ue,j[i].total_uh]);
			}
		});
		$.post("getCaptureby_typeTotalCount?"+key+"="+value,{Search:Search,c_type:c_type},function(j) {
			FilteredRecords = j;
		});
	}
</script>