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
	    			<div class="card-header"><h5>COMMAND WISE STR JCO/OR AS ON : ${date}</h5></div>
			</div>	
		</div>	
	</div>						
		<div class="col-md-12">
			<table id="Command_Wise_STR_Jcoreporttbl" class="display table no-margin table-striped  table-hover table-bordered ">
			   <thead>
				 <tr>
					<th>Command</th>
					<th>Auth</th>
					<th>CT-1 Female</th>
					<th>CT-1 Male</th>
					<th>CT-2 Female</th>
					<th>CT-2 Male</th>
					<th>TOTAL</th>
				 </tr>				
			 </thead>
		  </table>
		</div>
			<div class="card-footer" align="center">
				<a href="Command_Wise_STR_JcoUrl" type="reset" class="btn btn-success btn-sm"> Clear </a> 
              	<i class="action_icons action_download"></i><input type="button"  class="btn btn-primary btn-sm" value="Print" onclick="downloaddata()" > 
              	<i class="fa fa-file-excel-o" id="btnExport" style="font-size: x-large; color: green; text-align: right;" aria-hidden="true" title="EXPORT TO EXCEL" onclick="getExcel();"></i> 
           </div>	
</form>


<c:url value="Download_Command_Wise_STR_Jcoquery" var="dwonloadUrl"/>
	<form:form action="${dwonloadUrl}" method="post" id="downloadForm" name="downloadForm" modelAttribute="cont_comd_dn">
</form:form>

<c:url value="Excel_Command_Wise_STR_query_Jco" var="excelUrl" />
<form:form action="${excelUrl}" method="post" id="ExcelForm" name="ExcelForm" modelAttribute="typeReport1">
	   <input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form> 
<script>
$(document).ready(function() {  
	
	mockjax1('Command_Wise_STR_Jcoreporttbl');
	table = dataTable('Command_Wise_STR_Jcoreporttbl');
	$('#btn-reload').on('click', function(){
		table.ajax.reload();
	});
	$.ajaxSetup({
		async : false
	});
	
});
	
function data(tableName){
	if(tableName == "Command_Wise_STR_Jcoreporttbl"){
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
	 	var sus_no='${sus_no}';

	 	var s_auth = 0; var s_male1 = 0; var s_female1 =0; var s_male2 =0; var s_female2 =0; var s_total =0;
	 	
		$.post("getCommand_Wise_STR_Report_JCODataList?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,orderType:orderType,
			cont_comd:cont_comd,cont_corps:cont_corps,cont_div:cont_div,cont_bde:cont_bde,unit_name:unit_name,sus_no:sus_no},function(j) {
			
			for (var i = 0; i < j.length; i++) {
				jsondata.push([j[i].level_c,j[i].auth, j[i].male1,j[i].female1,j[i].male2,j[i].female2,j[i].total]);
				
				s_auth+=j[i].auth; s_male1+=j[i].male1; s_female1+=j[i].female1; s_male2+=j[i].male2; s_female2+=j[i].female2; s_total+=j[i].total;
			}
			
			jsondata.push(["TOTAL",s_auth,s_male1,s_female1,s_male2,s_female2,s_total]);
		});
		$.post("Command_Wise_STR_JCO_TotalCount?"+key+"="+value,{Search:Search,cont_comd:cont_comd,cont_corps:cont_corps,cont_div:cont_div,
			cont_bde:cont_bde,unit_name:unit_name,sus_no:sus_no},function(j) {
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