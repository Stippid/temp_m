<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link rel="stylesheet" href="js/InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="js/InfiniteScroll/js/datatables.min.js"></script>
<script type="text/javascript" src="js/InfiniteScroll/js/jquery.mockjax.min.js"></script>
<script type="text/javascript" src="js/InfiniteScroll/js/datatables.mockjax.js"></script>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<style type="text/css">
	 table.dataTable, table.dataTable th, table.dataTable td {
		-webkit-box-sizing: content-box;
		-moz-box-sizing: content-box;
		box-sizing: content-box;
		width: 90px;
		text-align: center;
		font-size: 10px;
	}
	
</style>

<form action="" method="post" enctype="multipart/form-data" class="form-horizontal"> 
	<div class="animated fadeIn">	
	    	<div class="col-md-12" align="center">
	    		<div class="card">
	    			<div class="card-header"><h5>STR OF REGULAR ARMY PERSONNEL</h5></div>
	    			<div class="card-header"><h5>INF REGTs </h5></div>
	    			<div class="card-header"><h5>DATA AS ON: ${date}</h5></div>
				</div>
	        </div>
	  </div>			
		<!-- <div class="col-md-12">
	    	<div class="card">
	    		<div class="card-header"><h5>GORKHA</h5></div>
			</div>
	    </div> -->	
	   <!--  / bisag 121222 v2 (perentarm changed report1 one) -->
		<!-- <div class="col-md-12 container">
			<table id="Regular_ArmyGorkha_reporttbl" class="display table no-margin table-striped  table-hover table-bordered ">
				<thead>
					<tr>
						<th>ARM_DESCRIPTION</th>
						<th>Auth</th>
						<th>TOTAL</th>
			    	</tr>
				</thead>
			</table>
		</div> -->
			<div class="col-md-12">
	    		<div class="card">
	    			<div class="card-header"><h5>INFANTRY INCLUDING GORKHA</h5></div>
				</div>
	        </div>
		<div class="col-md-12 container">
			<table id="Regular_ArmyInfantary_reporttbl" class="display table no-margin table-striped  table-hover table-bordered ">
				<thead>
					<tr >
						<th>ARM_DESCRIPTION</th>
						<th>Auth</th>
						<th>TOTAL</th>
			    	</tr>
				</thead>
			</table>
		</div>
		<div class="card-footer" align="center">
           	<i class="action_icons action_download"></i><input type="button"  class="btn btn-primary btn-sm" value="Print" onclick="downloaddata()" >
        	<i class="fa fa-file-excel-o" id="btnExport" style="font-size: x-large; color: green; text-align: right;" aria-hidden="true" title="EXPORT TO EXCEL" onclick="getExcel();"></i> 
        </div>	
</form>

<c:url value="Download_Regular_ArmyPersonnel_query" var="dwonloadUrl"/>
	<form:form action="${dwonloadUrl}" method="post" id="downloadForm" name="downloadForm" modelAttribute="">
</form:form>

<c:url value="Excel_Regular_ArmyPersonnel_query" var="excelUrl" />
<form:form action="${excelUrl}" method="post" id="ExcelForm" name="ExcelForm" modelAttribute="typeReport1">
	   <input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form> 

<script>
$(document).ready(function() {  
	// bisag 121222 v2 (perentarm changed report1 one)
	/* mockjax1('Regular_ArmyGorkha_reporttbl');
	table = dataTable('Regular_ArmyGorkha_reporttbl');
	$('#btn-reload').on('click', function(){
		table.ajax.reload();
	}); */
	$.ajaxSetup({
		async : false
	});
	mockjax1('Regular_ArmyInfantary_reporttbl');
	table = dataTable('Regular_ArmyInfantary_reporttbl');
	$('#btn-reload').on('click', function(){
		table.ajax.reload();
	});
});	
	
function data(tableName){
	// bisag 121222 v2 (perentarm changed report1 one)
/* 	if(tableName == "Regular_ArmyGorkha_reporttbl"){
		jsondata = [];
		var table = $('#'+tableName).DataTable();
		var info = table.page.info();
	
		var currentPage = info.page;
	    var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = order[0][0] + 1;
		var orderType = order[0][1];
				
		var cont_comd=$("#cont_comd").val();
	 	var cont_corps=$("#cont_corps").val();
	 	var cont_div=$("#cont_div").val();
	 	var cont_bde=$("#cont_bde").val();
	 	var unit_name=$("#unit_name").val();
	 	var sus_no=$("#sus_no").val();
	 
	 	var s_auth = 0;var s_total = 0;
		$.post("getRegular_ArmyGorkha_ReportDataList?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,orderType:orderType,
			cont_comd:cont_comd,cont_corps:cont_corps,cont_div:cont_div,cont_bde:cont_bde,unit_name:unit_name,sus_no:sus_no},function(j) {
			
			for (var i = 0; i < j.length; i++) {
				jsondata.push([j[i].arm_desc,j[i].auth, j[i].total]);
				s_auth += j[i].auth; s_total += j[i].total;
			}
			jsondata.push(["TOTAL",s_auth,s_total]);		
		});
		$.post("getRegular_ArmyGorkha_TotalCount?"+key+"="+value,{Search:Search,cont_comd:cont_comd,cont_corps:cont_corps,cont_div:cont_div,
			cont_bde:cont_bde,unit_name:unit_name,sus_no:sus_no},function(j) {
			FilteredRecords = j;
		});
	} */
	if(tableName == "Regular_ArmyInfantary_reporttbl"){
		jsondata = [];
		var table = $('#'+tableName).DataTable();
		var info = table.page.info();
	
		var currentPage = info.page;
	    var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = order[0][0] + 1;
		var orderType = order[0][1];
			
		var cont_comd=$("#cont_comd").val();
	 	var cont_corps=$("#cont_corps").val();
	 	var cont_div=$("#cont_div").val();
	 	var cont_bde=$("#cont_bde").val();
	 	var unit_name=$("#unit_name").val();
	 	var sus_no=$("#sus_no").val();

	 	var s_auth = 0;var s_total = 0;
		$.post("getRegular_ArmyInfantary_ReportDataList?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,orderType:orderType,
			cont_comd:cont_comd,cont_corps:cont_corps,cont_div:cont_div,cont_bde:cont_bde,unit_name:unit_name,sus_no:sus_no},function(j) {
			
			for (var i = 0; i < j.length; i++) {
				jsondata.push([j[i].arm_desc,j[i].auth,j[i].total]);
				s_auth += j[i].auth; s_total += j[i].total;
			}			
			jsondata.push(["TOTAL",s_auth,s_total]);
		});
		$.post("getRegular_ArmyInfantary_TotalCount?"+key+"="+value,{Search:Search,cont_comd:cont_comd,cont_corps:cont_corps,cont_div:cont_div,
			cont_bde:cont_bde,unit_name:unit_name,sus_no:sus_no},function(j) {
			FilteredRecords = j;
		});
	}
}	
		
function downloaddata(){
	document.getElementById('downloadForm').submit();		 
}

function getExcel() {	
	
	document.getElementById('typeReport1').value = 'excelL';
	document.getElementById('ExcelForm').submit();
}

</script>