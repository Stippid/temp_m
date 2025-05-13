<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script src="js/InfiniteScroll/js/jquery-1.11.0.js"></script>
<link rel="stylesheet" href="js/InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="js/InfiniteScroll/js/datatables.min.js"></script>
<script type="text/javascript" src="js/InfiniteScroll/js/jquery.mockjax.min.js"></script>
<script type="text/javascript" src="js/InfiniteScroll/js/datatables.mockjax.js"></script>

<form:form id="CCSForm">
	<div class="animated fadeIn" id="printableArea">
		<div class="container">
			<div class="card">
				<div class="card-header" align="center">
					<h5>SECOND/THIRD LINE TPT REPORT</h5>
				</div>
				<div class="card-body">
					<div class="col-md-12">
						<table id="capture_critical_store_reporttbl" class="display table no-margin table-striped  table-hover  table-bordered">
							<thead>
								<tr>
									<th width="10%" style="text-align:center;">Type</th>
									<th width="10%" style="text-align:center;">Code</th>
									<th>Nomenclature</th>
									<th width="10%" style="text-align:center;">UE</th>
									<th width="10%" style="text-align:center;">UH</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>
<script>
var table = null;
$(document).ready(function(){
	mockjax1('capture_critical_store_reporttbl');
	table = dataTable('capture_critical_store_reporttbl',[],[]);
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

	$.post("second_third_line_tpt_reportData?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,orderType:orderType},function(j){
		for (var i = 0; i < j.length; i++) {
			jsondata.push([j[i].c_type, j[i].code, j[i].nomenc,j[i].ue,j[i].total_uh]);
		}
	});
	$.post("second_third_line_tpt_reportCount?"+key+"="+value,{Search:Search},function(j){
		FilteredRecords = j;
	});
}
</script>