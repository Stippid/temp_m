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
	    			<div class="card-header"><h5>Wastage of Officers by Cause for the</h5></div>
	    				<div class="card-header"><h5>  REPORT NO.   MISO/OFFR/56  </h5></div>
	    			<div class="card-header"><h5> Report Generated on: ${date} </h5></div>
					</div>
        		</div>
			</div>						

	<%-- <div class="col-md-12" id="getCause_of_Wastage" style="display: block;">
		<div class="watermarked" data-watermark="" id="divwatermark">
			<span id="ip"></span>
			<table id="getSearch_Letter"
				class="table no-margin table-striped  table-hover  table-bordered ">
				<thead>
					
						<tr>
							<th >Cause of Wastage</th>
							<th >No of Offers</th>
						</tr>
						<c:forEach var="item" items="${list}" varStatus="num">
						<tr>
							<td> ${item[0]}</td>
							 <td  style="background-color:#f0f3f5; color:black; text-align: center;"><span>${item[1]} </span></td> 
						</tr>
						
						</c:forEach>
					</thead>			
			</table>
		</div> --%>
	<div class="col-md-12">
			<table id="westege_reporttbl" class="display table no-margin table-striped  table-hover table-bordered ">
			<thead>
				<tr>
					<th >Cause of Wastage</th>
					<th >No of Offers</th>
				</tr>
			</thead>
		</table>
		</div>

			<div class="card-footer" align="center">
              	<i class="action_icons action_download"></i><input type="button"  class="btn btn-primary btn-sm" value="Print" onclick="downloaddata()" >
              	<i class="fa fa-file-excel-o" id="btnExport" style="font-size: x-large; color: green; text-align: right;" aria-hidden="true" title="EXPORT TO EXCEL" onclick="getExcel();"></i>
            </div>	
            <!-- </div> -->
</form>

<c:url value="Download_Cause_of_wastage_query" var="dwonloadUrl"/>
<form:form action="${dwonloadUrl}" method="post" id="downloadForm" name="downloadForm" >
</form:form>

<c:url value="Excel_Cause_of_wastage_query" var="excelUrl" />
<form:form action="${excelUrl}" method="post" id="ExcelForm" name="ExcelForm" modelAttribute="cont_comd_ex">
	   <input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form>

<script>
$(document).ready(function() {  
	
	mockjax1('westege_reporttbl');
	table = dataTable('westege_reporttbl');
	$('#btn-reload').on('click', function(){
		table.ajax.reload();
	});
	$.ajaxSetup({
		async : false
	});
	
});

function data(tableName){
	if(tableName == "westege_reporttbl"){
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
		
		var s_auth = 0;
		$.post("getwestegeReportDataList?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,orderType:orderType
			},function(j) {
			
			for (var i = 0; i < j.length; i++) {
				
				jsondata.push([j[i].cause_of_wastage,j[i].no_of_offrs]);
				
				s_auth+=j[i].no_of_offrs;
			}
			
			jsondata.push(["TOTAL",s_auth]);
		});
		$.post("getwestegeReportDataListcount?"+key+"="+value,{Search:Search},function(j) {
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