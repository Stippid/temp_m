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
	    			<div class="card-header"><h5>SPECIAL REPRSENTATION IN SERVICES OF SC/ST(CIVILIANS)</h5></div>
	    			<div class="card-header"><h5>STATEMENT SHOWING THE TOTAL NUMBER OF GOVT SERVANT AND</h5></div> 
	    			<div class="card-header"><h5>THE NUMBER OF SC/ST AMONGST THEM IN THE LOWER FORMATIONS <strong
										style="color: red;">* </strong> OF THE ARMY </h5></div> 
	    			<div class="card-header"><h5>(PERMANENT & TEMPORARY WISE)</h5></div> 	    			
	    			<div class="card-header"><h5>DATA AS ON: ${date}  </h5></div>
	    			
	 <div class="col-md-12" id="Services_SC_STtbl" style="display: block;">
		<div class="watermarked" data-watermark="" id="divwatermark">
			<span id="ip"></span>
			<table id="getSearch_Letter"
				class="table no-margin table-striped  table-hover  table-bordered ">
				<thead>
					<c:forEach var="item" items="${list}" varStatus="num">
						<tr>					
							<th rowspan="2" style="vertical-align: middle;">STATUS</th>
							<th rowspan="2" style="vertical-align: middle;">GROUP</th>
						    <th colspan="4" style="text-align: center;">NO. OF EMPLOYEES AS ON 01 JAN ${previousYear} </th> 
						    <th colspan="4" style="text-align: center;">NO. OF EMPLOYEES AS ON 01 JAN ${year}</th>
						</tr>
						 <tr>
						    <th style="text-align: center;">TOTAL GOVT SERVANTS </th> 
						    <th style="text-align: center;">SC</th>
						    <th style="text-align: center;">ST</th>	
						   <th style="text-align: center;">NON EFECTIVE</th>	
						    <th style="text-align: center;">TOTAL GOVT SERVANTS </th> 
						    <th style="text-align: center;">SC</th>
						    <th style="text-align: center;">ST</th>	  	
						  <th style="text-align: center;">NON EFECTIVE</th>			    
				  		</tr>				  		
				  	
				  		<th rowspan="5" style="width: 300px;vertical-align: middle;">PERMANENT</th>
						<tr>
							<th style="font-size: 15px; text-align: left;">GROUP 'A'</th>
							<td style="background-color:#f0f3f5; color:black; text-align: right;" ><span>${item[0]}</span></td>
							<td style="background-color:#f0f3f5; color:black; text-align: right;" ><span>${item[1]}</span></td>
							<td style="background-color:#f0f3f5; color:black; text-align: right;" ><span>${item[2]}</span></td>
							<td style="background-color:#f0f3f5; color:black; text-align: right;" ><span>${item[54]}</span></td>
							
							<td style="background-color:#f0f3f5; color:black; text-align: right;" ><span>${item[27]}</span></td>
							<td style="background-color:#f0f3f5; color:black; text-align: right;" ><span>${item[28]}</span></td>
							<td style="background-color:#f0f3f5; color:black; text-align: right;" ><span>${item[29]}</span></td>
							<td style="background-color:#f0f3f5; color:black; text-align: right;" ><span>${item[63]}</span></td>
							
							
						</tr>
						<tr>
							<th style="font-size: 15px; text-align: left;">GROUP 'B'</th>
							<td style="background-color:#f0f3f5; color:black; text-align: right;"><span>${item[3]}</span></td>
							<td style="background-color:#f0f3f5; color:black; text-align: right;" ><span>${item[4]}</span></td>
							<td style="background-color:#f0f3f5; color:black; text-align: right;" ><span>${item[5]}</span></td>
							<td style="background-color:#f0f3f5; color:black; text-align: right;" ><span>${item[55]}</span></td>
							
							<td style="background-color:#f0f3f5; color:black; text-align: right;"><span>${item[30]}</span></td>
							<td style="background-color:#f0f3f5; color:black; text-align: right;" ><span>${item[31]}</span></td>
							<td style="background-color:#f0f3f5; color:black; text-align: right;" ><span>${item[32]}</span></td>
							<td style="background-color:#f0f3f5; color:black; text-align: right;" ><span>${item[64]}</span></td>
						</tr>
						<tr>
							<th style="font-size: 15px; text-align: left;">GROUP 'C'</th>
							<td style="background-color:#f0f3f5; color:black; text-align: right;"><span>${item[6]}</span></td>
							<td style="background-color:#f0f3f5; color:black; text-align: right;" ><span>${item[7]}</span></td>
							<td style="background-color:#f0f3f5; color:black; text-align: right;" ><span>${item[8]}</span></td>
								<td style="background-color:#f0f3f5; color:black; text-align: right;" ><span>${item[56]}</span></td>
							
							<td style="background-color:#f0f3f5; color:black; text-align: right;"><span>${item[33]}</span></td>
							<td style="background-color:#f0f3f5; color:black; text-align: right;" ><span>${item[34]}</span></td>
							<td style="background-color:#f0f3f5; color:black; text-align: right;" ><span>${item[35]}</span></td>
								<td style="background-color:#f0f3f5; color:black; text-align: right;" ><span>${item[65]}</span></td>
						</tr>
						<tr>
							<th style="font-size: 15px; text-align: center;">TOTAL</th>
							<td style="background-color:#f0f3f5; color:black; text-align: right; font-weight: bold;"><span>${item[9]}</span></td>
							<td style="background-color:#f0f3f5; color:black; text-align: right; font-weight: bold;"><span>${item[10]}</span></td>
							<td style="background-color:#f0f3f5; color:black; text-align: right; font-weight: bold;"><span>${item[11]}</span></td>
							<td style="background-color:#f0f3f5; color:black; text-align: right; font-weight: bold;"><span>${item[57]}</span></td>
							
							<td style="background-color:#f0f3f5; color:black; text-align: right; font-weight: bold;"><span>${item[36]}</span></td>
							<td style="background-color:#f0f3f5; color:black; text-align: right; font-weight: bold;"><span>${item[37]}</span></td>
							<td style="background-color:#f0f3f5; color:black; text-align: right; font-weight: bold;"><span>${item[38]}</span></td>
								<td style="background-color:#f0f3f5; color:black; text-align: right; font-weight: bold;" ><span>${item[66]}</span></td>
						</tr>
				
				  		<th rowspan="5" style="width: 300px;vertical-align: middle;">TEMPORARY</th>
						<tr>
							<th style="font-size: 15px; text-align: left;">GROUP 'A'</th>
							<td style="background-color:#f0f3f5; color:black; text-align: right;"><span>${item[12]}</span></td>
							<td style="background-color:#f0f3f5; color:black; text-align: right;" ><span>${item[13]}</span></td>
							<td style="background-color:#f0f3f5; color:black; text-align: right;" ><span>${item[14]}</span></td>
								<td style="background-color:#f0f3f5; color:black; text-align: right;" ><span>${item[58]}</span></td>
							
							<td style="background-color:#f0f3f5; color:black; text-align: right;"><span>${item[39]}</span></td>
							<td style="background-color:#f0f3f5; color:black; text-align: right;" ><span>${item[40]}</span></td>
							<td style="background-color:#f0f3f5; color:black; text-align: right;" ><span>${item[41]}</span></td>
							<td style="background-color:#f0f3f5; color:black; text-align: right;" ><span>${item[67]}</span></td>
						</tr>
						<tr>
							<th style="font-size: 15px; text-align: left;">GROUP 'B'</th>
							<td style="background-color:#f0f3f5; color:black; text-align: right;"><span>${item[15]}</span></td>
							<td style="background-color:#f0f3f5; color:black; text-align: right;" ><span>${item[16]}</span></td>
							<td style="background-color:#f0f3f5; color:black; text-align: right;" ><span>${item[17]}</span></td>
								<td style="background-color:#f0f3f5; color:black; text-align: right;" ><span>${item[59]}</span></td>
								
							
							<td style="background-color:#f0f3f5; color:black; text-align: right;"><span>${item[42]}</span></td>
							<td style="background-color:#f0f3f5; color:black; text-align: right;" ><span>${item[43]}</span></td>
							<td style="background-color:#f0f3f5; color:black; text-align: right;" ><span>${item[44]}</span></td>
							<td style="background-color:#f0f3f5; color:black; text-align: right;" ><span>${item[68]}</span></td>
						</tr>
						<tr>
							<th style="font-size: 15px; text-align: left;">GROUP 'C'</th>
							<td style="background-color:#f0f3f5; color:black; text-align: right;"><span>${item[18]}</span></td>
							<td style="background-color:#f0f3f5; color:black; text-align: right;" ><span>${item[19]}</span></td>
							<td style="background-color:#f0f3f5; color:black; text-align: right;" ><span>${item[20]}</span></td>
								<td style="background-color:#f0f3f5; color:black; text-align: right;" ><span>${item[60]}</span></td>
							
							<td style="background-color:#f0f3f5; color:black; text-align: right;"><span>${item[45]}</span></td>
							<td style="background-color:#f0f3f5; color:black; text-align: right;" ><span>${item[46]}</span></td>
							<td style="background-color:#f0f3f5; color:black; text-align: right;" ><span>${item[47]}</span></td>
							<td style="background-color:#f0f3f5; color:black; text-align: right;" ><span>${item[69]}</span></td>
						</tr>
						<tr>
							<th style="font-size: 15px; text-align: center;">TOTAL</th>
							<td style="background-color:#f0f3f5; color:black; text-align: right; font-weight: bold;"><span>${item[21]}</span></td>
							<td style="background-color:#f0f3f5; color:black; text-align: right; font-weight: bold;"><span>${item[22]}</span></td>
							<td style="background-color:#f0f3f5; color:black; text-align: right; font-weight: bold;"><span>${item[23]}</span></td>
							<td style="background-color:#f0f3f5; color:black; text-align: right; font-weight: bold;" ><span>${item[61]}</span></td>
							
								
							<td style="background-color:#f0f3f5; color:black; text-align: right; font-weight: bold;"><span>${item[48]}</span></td>
							<td style="background-color:#f0f3f5; color:black; text-align: right; font-weight: bold;"><span>${item[49]}</span></td>
							<td style="background-color:#f0f3f5; color:black; text-align: right; font-weight: bold;"><span>${item[50]}</span></td>
							<td style="background-color:#f0f3f5; color:black; text-align: right; font-weight: bold;" ><span>${item[70]}</span></td>
						</tr>
					
						<tr>
							<th colspan="2" style="width: 300px;vertical-align: middle; text-align: center;">GRAND TOTAL</th>
							<td style="background-color:#f0f3f5; color:black; text-align: right; font-weight: bold;"><span>${grand_total1}</span></td>
							<td style="background-color:#f0f3f5; color:black; text-align: right; font-weight: bold;" ><span>${grand_total2}</span></td>
							<td style="background-color:#f0f3f5; color:black; text-align: right; font-weight: bold;" ><span>${grand_total3}</span></td>
								<td style="background-color:#f0f3f5; color:black; text-align: right; font-weight: bold;" ><span>${item[62]}</span></td>
							
							<td style="background-color:#f0f3f5; color:black; text-align: right; font-weight: bold;"><span>${grand_total4}</span></td>
							<td style="background-color:#f0f3f5; color:black; text-align: right; font-weight: bold;" ><span>${grand_total5}</span></td>
							<td style="background-color:#f0f3f5; color:black; text-align: right; font-weight: bold;" ><span>${grand_total6}</span></td>
							<td style="background-color:#f0f3f5; color:black; text-align: right; font-weight: bold;" ><span>${item[71]}</span></td>
						</tr>						
					</c:forEach>
				</thead>			
			</table>
		</div>
		<div><h6 style="text-align: left;font-size: 15px;"><strong
										style="color: red;">(*) </strong> - All Units Under Comds including Comd HQs excluding Br/Dtes/Units under Ministry of Defence</h6></div>
	</div>
				 <div class="card-footer" align="center">
              		
              		<i class="action_icons action_download"></i><input type="button"  class="btn btn-primary btn-sm" value="Print" onclick="downloaddata()" >
              		<i class="fa fa-file-excel-o" id="btnExport" style="font-size: x-large; color: green; text-align: right;" aria-hidden="true" title="EXPORT TO EXCEL" onclick="getExcel();"></i>
              	</div>	 		      
			</div>
       	</div>
	</div>				
</form>


<c:url value="Download_Services_SC_ST" var="dwonloadUrl"/>
<form:form action="${dwonloadUrl}" method="post" id="downloadForm" name="downloadForm" >
</form:form>
<c:url value="Excel_represc_stService_Regular" var="excelUrl" />
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
</script>
