
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
	    			<div class="card-header"><h5>HELD STR OF OFFRs ARM/ SERVICE WISE</h5></div>
	    			<div class="card-header"><h5>Report No. MISO/OFFR/55         Report Generated On: ${date}  </h5></div>
	          			
		    			
		<div class="col-md-12">
			<table id="ArmServices_Wise_reporttbl" class="display table no-margin table-striped  table-hover table-bordered ">
				<thead>
			        <tr>
			        
			        	<th rowspan="2">ARM CODE</th>
						<th rowspan="2">PARENT ARM</th>
						<th colspan="12" style="text-align: center;">MONTH</th>
						<th rowspan="2" >TOTAl</th>
					</tr>
					<tr>
						<th>JAN</th>
						<th>FEB</th>
						<th>MAR</th>
						<th>APR</th>
						<th>MAY</th>
						<th>JUN</th>
						<th>JUL</th>
						<th>AUG</th>
						<th>SEP</th>
						<th>OCT</th>
						<th>NOV</th>
						<th>DEC</th>					
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


<c:url value="Download_ArmServices_Wise_query" var="dwonloadUrl"/>
	<form:form action="${dwonloadUrl}" method="post" id="downloadForm" name="downloadForm" >	
</form:form>

<c:url value="Excel_ArmServices_Wise_query" var="excelUrl" />
<form:form action="${excelUrl}" method="post" id="ExcelForm" name="ExcelForm" modelAttribute="typeReport1">
	   <input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form> 

<script>
$(document).ready(function() {  
	
	mockjax1('ArmServices_Wise_reporttbl');
	table = dataTable('ArmServices_Wise_reporttbl');
	$('#btn-reload').on('click', function(){
		table.ajax.reload();
	});
	$.ajaxSetup({
		async : false
	});
	
});

	
function data(tableName){
	if(tableName == "ArmServices_Wise_reporttbl"){
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
		
		var s_jan = 0; var s_feb =0; var s_mar = 0; var s_apr = 0; var s_may =0; var s_jun = 0; var s_jul =0;
		var s_aug = 0; var s_sep = 0; var s_oct = 0; var s_nov =0; var s_dec =0; var s_total =0;
		$.post("getArmServices_Wise_ReportDataList?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,orderType:orderType,
			},function(j) {
				
			for (var i = 0; i < j.length; i++) {
				jsondata.push([j[i].parent_arm,j[i].arm_desc,j[i].jan,j[i].feb,j[i].mar,j[i].apr,
					j[i].may,j[i].jun,j[i].jul,j[i].aug,j[i].sep,j[i].oct,j[i].nov,j[i].dec,j[i].total]);
				
				s_jan+= j[i].jan; s_feb+= j[i].feb;s_mar+= j[i].mar; s_apr+= j[i].apr; s_may+= j[i].may; s_jun+= j[i].jun;
				s_jul+= j[i].jul; s_aug+= j[i].aug;s_sep+= j[i].sep; s_oct+= j[i].oct; s_nov+= j[i].nov; s_dec+= j[i].dec; s_total += j[i].total;
				
			}
			
			jsondata.push(["","TOTAl",s_jan,s_feb,s_mar,s_apr,
				s_may,s_jun,s_jul,s_aug,s_sep,s_oct,s_nov,s_dec,s_total]);
			
		});
		$.post("getArmServices_Wise_TotalCount?"+key+"="+value,{Search:Search},function(j) {
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