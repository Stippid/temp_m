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
	    			<div class="card-header"><h5>HELD STRENGTH OF CIVILIANS IN THE ARMY - ARM / SERVICE WISE</h5></div>
	    			<div class="card-header"><h5>REGULAR ESTABLISHMENT </h5></div>
	    			<div class="card-header"><h5>DATA AS ON : ${date}</h5></div>
	    			
	 <div class="col-md-12" id="Age_Wise_reporttbl" style="display: block;">
		<div class="watermarked" data-watermark="" id="divwatermark">
			<span id="ip"></span>
			<table id="getSearch_Letter"
				class="table no-margin table-striped  table-hover  table-bordered ">
				<thead>
						<tr>
							<th style="font-size: 15px; text-align: center;" rowspan = "4">SER NO </th>
							<th style="font-size: 15px; text-align: left;" rowspan = "4" >ARM/SERVICE</th>
							<th style="font-size: 15px; text-align: center;" colspan="6" >REGULAR ESTABLISHMENT </th>
							<th style="font-size: 15px; text-align: center;" rowspan = "4" >TOTAL</th>
							
							
						</tr>
						<tr>
						   <th style="font-size: 15px; text-align: center;" colspan="2" rowspan = "2">GAZETTED </th>
						   <th style="font-size: 15px; text-align: center;" colspan="4"  >NON GAZETTED </th>
						</tr>
						<tr>
						 <th style="font-size: 15px; text-align: center;"  colspan="2">NON INDUSTRIAL </th>
						  <th style="font-size: 15px; text-align: center;"  colspan="2">INDUSTRIAL</th>
						  
						</tr>
						<tr>
						 <th style="font-size: 15px; text-align: center;"  >GROUP - A </th>
						  <th style="font-size: 15px; text-align: center;"  >GROUP - B</th>
						  
						  <th style="font-size: 15px; text-align: center;"  >GROUP - B </th>
						  <th style="font-size: 15px; text-align: center;"  >GROUP - C</th>
						  
						  <th style="font-size: 15px; text-align: center;"  >GROUP - B </th>
						  <th style="font-size: 15px; text-align: center;"  >GROUP - C</th>
						</tr>
					</thead>	
					<tbody>
						<c:if test="${list.size()==0}">
							<tr>
								<td style="font-size: 15px; text-align: center; color: red;" colspan="7">Data Not Available</td>
							</tr>
						</c:if>
						<c:forEach var="item" items="${list}" varStatus="num">
							<tr>
							
								<td style="font-size: 15px;  text-align: center;">${item[0]}</td>
								<td style="font-size: 15px; text-align: left;">${item[1]}</td>
								<td style="font-size: 15px; text-align: right;" class="col1">${item[2]}</td>
								<td style="font-size: 15px; text-align: right;" class="col2">${item[3]}</td>
								<td style="font-size: 15px; text-align: right;" class="col3">${item[4]}</td>
								<td style="font-size: 15px; text-align: right;" class="col4">${item[5]}</td>
								<td style="font-size: 15px; text-align: right;" class="col5">${item[6]}</td>
								<td style="font-size: 15px; text-align: right;" class="col6">${item[7]}</td>
								<td style="font-size: 15px; text-align: right; font-weight: Bold;"  class="col7">${item[8]}</td>
							</tr>
						</c:forEach>
						<tr>
						
							<th colspan="2" style="font-size: 15px; text-align: center;">TOTAL</th>
							<th style="font-size: 15px; text-align: right;" id="col1_th"></th>
								<th style="font-size: 15px; text-align: right;" id="col2_th"></th>
								<th style="font-size: 15px; text-align: right;" id="col3_th"></th>
								<th style="font-size: 15px; text-align: right;" id="col4_th"></th>
								<th style="font-size: 15px; text-align: right;" id="col5_th"></th>
								<th style="font-size: 15px; text-align: right;" id="col6_th"></th>
								<th style="font-size: 15px; text-align: right;" id="col7_th"></th>
						</tr>
						<tr>
						
<!-- 							<th colspan="8" style="font-size: 15px; text-align: center;">GRAND TOTAL</th> -->
<%-- 							<th style="font-size: 15px; text-align: right;">${grand_total}</th> --%>
						</tr>
					</tbody>		
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

<c:url value="Download_Arm_Service_Regular" var="dwonloadUrl"/>
	<form:form action="${dwonloadUrl}" method="post" id="downloadForm" name="downloadForm" >
</form:form>

<c:url value="Excel_Arm_Service_Regular" var="excelUrl" />
<form:form action="${excelUrl}" method="post" id="ExcelForm" name="ExcelForm" modelAttribute="cont_comd_ex">
	   <input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form> 

<script>
function downloaddata(){
	document.getElementById('downloadForm').submit();		 
}

function getExcel() {		
	document.getElementById('typeReport1').value = 'excelL';
	document.getElementById('ExcelForm').submit();
} 

$(document).ready(function(){
	
	var col1=0;
	var col2=0;
	var col3=0;
	var col4=0;
	var col5=0;
	var col6=0;
	var col7=0;
$(".col1").each(function(){
	col1+=parseInt($(this).text());
	
  });
  
$(".col2").each(function(){
	col2+=parseInt($(this).text());
  });
  
$(".col3").each(function(){
	col3+=parseInt($(this).text());
  });
  
$(".col4").each(function(){
	col4+=parseInt($(this).text());
  });
  
$(".col5").each(function(){
	col5+=parseInt($(this).text());
  });
  
$(".col6").each(function(){
	col6+=parseInt($(this).text());
  });
  
$(".col7").each(function(){
	col7+=parseInt($(this).text());
  });
  
  
$("#col1_th").text(col1);

$("#col2_th").text(col2);

$("#col3_th").text(col3);

$("#col4_th").text(col4);

$("#col5_th").text(col5);

$("#col6_th").text(col6);

$("#col7_th").text(col7);

});
</script>