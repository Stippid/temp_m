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
	    			<div class="card-header"><h5>Wastage of JCO/OR by Cause for the</h5></div>
	    				<div class="card-header"><h5>  REPORT NO.   MISO/JCO/OR/56  </h5></div>
	    			<div class="card-header"><h5> Report Generated on:${date}  </h5></div>
					</div>
        		</div>
			</div>						
<div id="viewpage">	
	<div class="col-md-12" id="getCause_of_Wastage" style="display: block;">
		<div class="watermarked" data-watermark="" id="divwatermark">
			<span id="ip"></span>
			<table id="getSearch_Letter"
				class="table no-margin table-striped  table-hover  table-bordered ">
				<thead>
					<c:forEach var="item" items="${list}" varStatus="num">
						<tr>
							<th style="font-size: 15px; text-align: center;">Cause of Wastage</th>
							<th style="font-size: 15px; width:50%; text-align: center;">No of JCO</th>
						</tr>
						<tr>
							<th style="font-size: 15px; text-align: center;">Died</th>
							<td style="background-color:#f0f3f5; color:black; text-align: center;" ><span>${item[0]}</span></td>
						</tr>
						<tr>
							<th style="font-size: 15px; text-align: center;">Medical Invalidment</th>
							<td style="background-color:#f0f3f5; color:black; text-align: center;"><span>${item[1]}</span></td>
						</tr>
						<tr>
							<th style="font-size: 15px; text-align: center;">Premature Retirement</th>
							<td style="background-color:#f0f3f5; color:black;text-align: center;" ><span>${item[2]}</span></td>
						</tr>	<tr>
							<th style="font-size: 15px; text-align: center;">Relinquished Commission/Release SSC</th>
							<td style="background-color:#f0f3f5; color:black; text-align: center;"><span>${item[3]}</span></td>
						</tr>
						<tr>
							<th style="font-size: 15px; text-align: center;">Retired</th>
							<td style="background-color:#f0f3f5; color:black; text-align: center;"><span>${item[4]}</span></td>
						</tr>
						<tr>
							<th style="font-size: 15px; text-align: center;">Total</th>
							<td style="background-color:#f0f3f5; color:black; text-align: center;"><span>${item[5]}</span></td>
						</tr>
						</c:forEach>
					</thead>			
			</table>
		</div>
	</div>
</div>
			<div class="card-footer" align="center">
              	<i class="action_icons action_download"></i><input type="button"  class="btn btn-primary btn-sm" value="Print" onclick="downloaddata()" >
            	<i class="fa fa-file-excel-o" id="btnExport" style="font-size: x-large; color: green; text-align: right;" aria-hidden="true" title="EXPORT TO EXCEL" onclick="getExcel();"></i>
            </div>	
</form>

<c:url value="Download_Cause_of_wastage_query_jco" var="dwonloadUrl"/>
<form:form action="${dwonloadUrl}" method="post" id="downloadForm" name="downloadForm" >
</form:form>

<c:url value="Excel_Cause_of_wastage_query_jco" var="excelUrl" />
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