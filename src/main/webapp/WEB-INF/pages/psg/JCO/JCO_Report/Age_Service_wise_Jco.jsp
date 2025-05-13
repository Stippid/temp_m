<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<!-- resizable_col -->
<link rel="stylesheet" href="js/assets/adjestable_Col/jquery.resizableColumns.css">
<script src="js/assets/adjestable_Col/jquery.resizableColumns.js" type="text/javascript"></script>



<form action="" method="post" enctype="multipart/form-data" class="form-horizontal"> 
	<div class="animated fadeIn">
		
	    	<div class="col-md-12" align="center">
	    		<div class="card">
	    			<div class="card-header"><h5>HELD STR OF JCO/OR AGE WISE</h5></div>
	    			<div class="card-header"><h5>DATA AS ON: ${date}  </h5></div>
	    			
	 <div class="col-md-12" id="Age_Wise_reporttbl" style="display: block;">
		<div class="watermarked" data-watermark="" id="divwatermark">
			<span id="ip"></span>
			<table id="getSearch_Letter"
				class="table no-margin table-striped  table-hover  table-bordered ">
				<thead>
					<c:forEach var="item" items="${list}" varStatus="num">
						<tr>
							<th style="font-size: 15px; text-align: center;">Age Group </th>
							<th style="font-size: 15px; width:50%; text-align: center;">No of JCO/OR</th>
						</tr>
						<tr>
							<th style="font-size: 15px; text-align: center;">YRS 20 ONWARDS LESS THAN 25YRS</th>
							<td style="background-color:#f0f3f5; color:black; text-align: center;" ><span>${item[0]}</span></td>
						</tr>
						<tr>
							<th style="font-size: 15px; text-align: center;">YRS 25 ONWARDS LESS THAN 30RS</th>
							<td style="background-color:#f0f3f5; color:black; text-align: center;"><span>${item[1]}</span></td>
						</tr>
						<tr>
							<th style="font-size: 15px; text-align: center;">YRS 30 ONWARDS LESS THAN 35RS</th>
							<td style="background-color:#f0f3f5; color:black;text-align: center;" ><span>${item[2]}</span></td>
						</tr>	<tr>
							<th style="font-size: 15px; text-align: center;">YRS 35 ONWARDS LESS THAN 40RS</th>
							<td style="background-color:#f0f3f5; color:black; text-align: center;"><span>${item[3]}</span></td>
						</tr>
						<tr>
							<th style="font-size: 15px; text-align: center;">YRS 40 ONWARDS LESS THAN 45RS</th>
							<td style="background-color:#f0f3f5; color:black; text-align: center;"><span>${item[4]}</span></td>
						</tr>
						<tr>
							<th style="font-size: 15px; text-align: center;">YRS 45 ONWARDS LESS THAN 50RS</th>
							<td style="background-color:#f0f3f5; color:black; text-align: center;"><span>${item[5]}</span></td>
						</tr>
						<tr>
							<th style="font-size: 15px; text-align: center;">YRS 50 ONWARDS LESS THAN 55RS</th>
							<td style="background-color:#f0f3f5; color:black; text-align: center;"><span>${item[6]}</span></td>
						</tr>
						<tr>
							<th style="font-size: 15px; text-align: center;">YRS 55 ONWARDS LESS THAN 60RS</th>
							<td style="background-color:#f0f3f5; color:black; text-align: center;"><span>${item[7]}</span></td>
						</tr>
						<tr>
							<th style="font-size: 15px; text-align: center;">YEARS 60 ONWARDS</th>
							<td style="background-color:#f0f3f5; color:black; text-align: center;"><span>${item[8]}</span></td>
						</tr>
						<tr>
							<th style="font-size: 15px; text-align: center;">TOTAL</th>
							<td style="background-color:#f0f3f5; color:black; text-align: center;"><span>${item[9]}</span></td>
						</tr>
						</c:forEach>
					</thead>			
			</table>
		</div>
	</div>
	
	<div class="col-md-12" id="Service_Wise_reporttbl" style="display: block;">
		<div class="watermarked" data-watermark="" id="divwatermark">
			<span id="ip"></span>
			<table id="getSearch_Letter"
				class="table no-margin table-striped  table-hover  table-bordered ">
				<thead>
					<c:forEach var="item1" items="${list1}" varStatus="num">
						<tr>
							<th style="font-size: 15px; text-align: center;">Service Group</th>
							<th style="font-size: 15px; width:50%; text-align: center;">No of JCO/OR</th>
						</tr>
						<tr>
							<th style="font-size: 15px; text-align: center;">LESS THAN 1YRS</th>
							<td style="background-color:#f0f3f5; color:black; text-align: center;" ><span>${item1[0]}</span></td>
						</tr>
						<tr>
							<th style="font-size: 15px; text-align: center;">YRS 1 ONWARDS LESS THAN 3YRS</th>
							<td style="background-color:#f0f3f5; color:black; text-align: center;"><span>${item1[1]}</span></td>
						</tr>
						<tr>
							<th style="font-size: 15px; text-align: center;">YRS 3 ONWARDS LESS THAN 5YRS</th>
							<td style="background-color:#f0f3f5; color:black;text-align: center;" ><span>${item1[2]}</span></td>
						</tr>	<tr>
							<th style="font-size: 15px; text-align: center;">YRS 5 ONWARDS LESS THAN 7YRS</th>
							<td style="background-color:#f0f3f5; color:black; text-align: center;"><span>${item1[3]}</span></td>
						</tr>
						<tr>
							<th style="font-size: 15px; text-align: center;">YRS 7 ONWARDS LESS THAN 9YRS</th>
							<td style="background-color:#f0f3f5; color:black; text-align: center;"><span>${item1[4]}</span></td>
						</tr>
						<tr>
							<th style="font-size: 15px; text-align: center;">YRS 9 ONWARDS LESS THAN 11YRSS</th>
							<td style="background-color:#f0f3f5; color:black; text-align: center;"><span>${item1[5]}</span></td>
						</tr>
						<tr>
							<th style="font-size: 15px; text-align: center;">YRS 11 ONWARDS LESS THAN 13YRS</th>
							<td style="background-color:#f0f3f5; color:black; text-align: center;"><span>${item1[6]}</span></td>
						</tr>
						<tr>
							<th style="font-size: 15px; text-align: center;">YRS 13 ONWARDS LESS THAN 15YRS</th>
							<td style="background-color:#f0f3f5; color:black; text-align: center;"><span>${item1[7]}</span></td>
						</tr>
						<tr>
							<th style="font-size: 15px; text-align: center;">YRS 15 ONWARDS LESS THAN 20YRS</th>
							<td style="background-color:#f0f3f5; color:black; text-align: center;"><span>${item1[8]}</span></td>
						</tr>
						<tr>
							<th style="font-size: 15px; text-align: center;">YRS 20 ONWARDS LESS THAN 25YRS</th>
							<td style="background-color:#f0f3f5; color:black; text-align: center;"><span>${item1[9]}</span></td>
						</tr>
						<tr>
							<th style="font-size: 15px; text-align: center;">YRS 25 ONWARDS LESS THAN 30YRS</th>
							<td style="background-color:#f0f3f5; color:black; text-align: center;"><span>${item1[10]}</span></td>
						</tr>
						<tr>
							<th style="font-size: 15px; text-align: center;">YRS 30 ONWARDS</th>
							<td style="background-color:#f0f3f5; color:black; text-align: center;"><span>${item1[11]}</span></td>
						</tr>
						<tr>
							<th style="font-size: 15px; text-align: center;">TOTAL</th>
							<td style="background-color:#f0f3f5; color:black; text-align: center;"><span>${item1[12]}</span></td>
						</tr>
				    </c:forEach>
					</thead>			
			</table>
		</div>
	</div>
						<div class="card-footer" align="center">
		              		<i class="action_icons action_download"></i><input type="button"  class="btn btn-primary btn-sm" value="Print" onclick="downloaddata()" >
		              		<i class="fa fa-file-excel-o" id="btnExport" style="font-size: x-large; color: green; text-align: right;" aria-hidden="true" title="EXPORT TO EXCEL" onclick="getExcel();"></i>
		              	</div>			      
						</div>
	        		</div>
			</div>			
	
</form>


<c:url value="Download_Age_Service_query_Jco" var="dwonloadUrl"/>
<form:form action="${dwonloadUrl}" method="post" id="downloadForm" name="downloadForm" >
</form:form>

<c:url value="Excel_Age_Service_wise_query_Jco" var="excelUrl" />
<form:form action="${excelUrl}" method="post" id="ExcelForm" name="ExcelForm" modelAttribute="cont_comd_ex">
	   <input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form> 
<script>
$(document).ready(function() {  
		
});

function downloaddata(){
	document.getElementById('downloadForm').submit();		 
}
function getExcel() {	
	document.getElementById('typeReport1').value = 'excelL';
	document.getElementById('ExcelForm').submit();
} 
</script>
