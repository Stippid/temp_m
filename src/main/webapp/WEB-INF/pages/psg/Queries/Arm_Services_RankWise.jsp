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
	    		<div class="card-header"><h5>HELD STR OF OFFRS BY ARM/SERVICES AND RANK WISE</h5></div>
	    		 <div class="card-header"><h5>DATA AS ON: ${date} </h5></div>
	    			<div class="card-header"><h5>REPORT NO. MISO/OFFRS/04</h5></div>
	    			
	     <div class="col-md-12">
			<table id="ArmServices_RankWise_reporttbl" class="display table no-margin table-striped  table-hover table-bordered ">
				<thead>
			        <tr>
						<th>PARENT ARM</th>
						<th>ARM DESC</th>
						<th>FM</th>
						<th>CDS</th>
						<th>GEN</th>
						<th>LT GEN</th>
						<th>MAJ GEN</th>
						<th>BRIG</th>
						<th>COL</th>
						<th>COL(TS)</th>
						<th>LT COL</th>
						<th>MAJ</th>
						<th>CAPT</th>
						<th>LT</th>
						<th>TOTAL</th>
						<th>PERCENTAGE</th>
			    </tr>
				</thead>
			</table>
		</div>
				<div class="card-footer" align="center">
              		<i class="action_icons action_download"></i><input type="button"  class="btn btn-primary btn-sm" value="Print" onclick="downloaddata()" >
              		<i class="fa fa-file-excel-o" id="btnExport" style="font-size: x-large; color: green; text-align: right;" aria-hidden="true" title="EXPORT TO EXCEL" onclick="getExcel();"></i> 
              	</div>			      
			</div>
	   </div>
</div>					
	
</form>


<c:url value="Download_ArmServices_RankWise_query" var="dwonloadUrl"/>
	<form:form action="${dwonloadUrl}" method="post" id="downloadForm" name="downloadForm" >	  
</form:form>

<c:url value="Excel_ArmServices_RankWise_query" var="excelUrl" />
<form:form action="${excelUrl}" method="post" id="ExcelForm" name="ExcelForm" modelAttribute="typeReport1">
	   <input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form> 

<script>
$(document).ready(function() {  
	
	mockjax1('ArmServices_RankWise_reporttbl');
	table = dataTable('ArmServices_RankWise_reporttbl');
	$('#btn-reload').on('click', function(){
		table.ajax.reload();
	});
	$.ajaxSetup({
		async : false
	});
	
});
	
function data(tableName){
	if(tableName == "ArmServices_RankWise_reporttbl"){
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
	
		 var s_fm = 0; var s_cds = 0; var s_gen =0; var s_ltgen = 0; var s_majgen = 0; var s_brig = 0; var s_col = 0;
	     var s_colts = 0; var s_ltcol = 0; var s_maj = 0; var s_capt =0; var s_lt = 0; var s_total = 0;
		$.post("getArmServices_RankWise_ReportDataList?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,
			orderColunm:orderColunm,orderType:orderType},function(j) {
			
			for (var i = 0; i < j.length; i++) {
				jsondata.push([j[i].parent_arm,j[i].arm_desc,j[i].fm,
					j[i].cds,j[i].gen,j[i].ltgen,j[i].majgen,j[i].brig,j[i].col,j[i].colts,
					j[i].ltcol,j[i].maj,j[i].capt,j[i].lt,j[i].total,j[i].percentage]);
				
				
				s_fm += j[i].fm; s_cds += j[i].cds; s_gen += j[i].gen; s_ltgen += j[i].ltgen; s_majgen += j[i].majgen;
				s_brig += j[i].brig; s_col += j[i].col; s_colts +=  j[i].colts; s_ltcol += j[i].ltcol; s_maj += j[i].maj;
				s_capt += j[i].capt; s_lt += j[i].lt; s_total +=  j[i].total;
			}
			
			
			jsondata.push(["","TOTAL",s_fm,
				s_cds,s_gen,s_ltgen,s_majgen,s_brig,s_col,s_colts,
				s_ltcol,s_maj,s_capt,s_lt,s_total,""]);
			
			
		});
		$.post("getArmServices_RankWise_TotalCount?"+key+"="+value,{Search:Search},function(j) {
			FilteredRecords = j;
		});
	}
}	
		
</script>
<script>
function downloaddata(){
	document.getElementById('downloadForm').submit();		 
}

function getExcel() {	
	document.getElementById('typeReport1').value = 'excelL';
	document.getElementById('ExcelForm').submit();
} 
</script>