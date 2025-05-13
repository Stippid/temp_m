<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>

<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<script type="text/javascript" src="js/printWatermark/common.js"></script>

<link rel="stylesheet" href="js/cue/cueWatermark.css"> 
<script src="js/printWatermark/printAllPages.js" type="text/javascript"></script>
<script src="js/printWatermark/cueWatermark.js" type="text/javascript"></script>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/miso/miso_js/jquery-1.12.3.js"></script>

<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>

<script src="js/cue/update.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>

<script src="js/cue/printAllPages.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>



<script src="js/exportExcelData/modernizr-2.8.3.js"></script>
<script src="js/exportExcelData/jquery-1.11.3.min.js"></script>
<script src="js/exportExcelData/jquery-ui.min.js"></script>
<script src="js/excel/xlsx-0.7.7.core.min.js"></script>  
<script src="js/excel/xls-0.7.4.core.min.js"></script> 

<script type="text/javascript" src="js/exportExcelData/infragistics.core.js"></script>
<script type="text/javascript" src="js/exportExcelData/infragistics.lob.js"></script>
<script type="text/javascript" src="js/exportExcelData/infragistics.ext_ui.js"></script>
<script type="text/javascript" src="js/exportExcelData/infragistics.documents.core_core.js"></script>
<script type="text/javascript" src="js/exportExcelData/infragistics.excel_core.js"></script>
<script type="text/javascript" src="js/exportExcelData/infragistics.xml.js"></script>
<script type="text/javascript" src="js/exportExcelData/infragistics.documents.core_openxml.js"></script>
<script type="text/javascript" src="js/exportExcelData/infragistics.excel_serialization_openxml.js"></script>

<script type="text/javascript" src="js/printWatermark/common.js"></script>
<script src="js/printWatermark/printAllPages.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/printWatermark/cueWatermark.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/assets/adjestable_Col/jquery.resizableColumns.css">
<script src="js/assets/adjestable_Col/jquery.resizableColumns.js" type="text/javascript"></script>
<style>
  .column {
    display: inline-block;
    width: 50%; /* Adjust width as needed */
  }

  .column > div {
    margin-bottom: 5px; /* Adjust spacing between rows as needed */
  }
  
  .report_print table thead {
    width: 100% !important;
}
.select_option
{
 width: 100% !important;
}
.set-margin{
margin-top:50%;
}


#getSearchReport td, #getSearchReport th {
    vertical-align: middle;
}

</style>

<%-- <form:form  encrypt="multipart/form-data" id="unit_details"  action="unit_detailsAction?${_csrf.parameterName}=${_csrf.token}"   method='POST' class="form-horizontal" commandName="unit_detailsCMD"> 
 --%>	<div class="animated fadeIn" id="printableArea2" style="display: block;">
		<div class="">
	    	<div class="container" align="center">
	    		<div class="card">
	    		 <div class="card-header" style="border: 1px solid rgba(0,0,0,.125); text-align: center;"><h5>ENGINEER MACHINARY ACTIVITY REPORT</h5></div>  
	   		      <div class="card-header"><strong>Basic Details</strong></div>
	          			<div class="card-body card-block">	            			
							<div class="col-md-12">
	          					<div class="col-md-6">
								    <div class="row form-group">
	                					<div class="col col-md-4">
	                  						<label class=" form-control-label"><strong style="color: red;">* </strong>SUS No</label>
	                					</div>   
	                					<div class="col-12 col-md-8">
	              							<input type="text" id="sus_no" name="sus_no" class="form-control autocomplete" autocomplete="off" maxlength="8" value="${sus_no}" />
	                  					</div>
	              					</div>
								</div>									 	          					          								 
								<div class="col-md-6">
									<div class="row form-group">
	                					<div class="col col-md-4">
	                						<label class=" form-control-label">Unit Name </label>
	                					</div>
	                					<div class="col-md-8">
											<textarea id="unit_name" name="unit_name" class="form-control autocomplete" style="font-size: 11px;" autocomplete="off" maxlength="100">${unit_name}</textarea>
									    </div>
	                				</div> 
	                			</div> 
							</div> 
						</div>	
						<input type="hidden" id="catpartdis" name="catpartdis" autocomplete="off"/> 
						<div class="form-control card-footer" align="center">
							<a href="unit_details" type="reset" class="btn btn-success btn-sm"> Clear </a> 
                           	<button type="button"  id="searchBTN"  class="btn btn-success btn-sm" onclick="return Search();">Search</button>
                      	</div> 	
					</div>
				</div>
			</div>
		</div>
		
		<div class="animated fadeIn" id="printableArea1" >
			<div class="row" id="printableArea" style="display: none;">
				<div class="col-md-12" align="center">
					<div class="card">
						<div class="card-header">
							<strong style="text-decoration: underline;"> RESTRICTED</strong>
						</div>
						<div class="card-header">
							<div class="col-md-2">
								<img src="js/miso/images/indianarmy_smrm5aaa.jpg"
									style="height: 85px;">
							</div>
							<div class="col-md-8">
								<h3>FOUR MONTHLY CENSUS RETURN - PART 'A'</h3>
							</div>
							<div class="col-md-2">
								<img src="js/miso/images/dgis-logo.png"
									style="max-width: 155px; height: 85px;">
							</div>
						</div>
						<div class="card-body card-block">
							<div class="col-md-5">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">SUS NO :</label>
									</div>
									<div class="col-12 col-md-8">
								            <label id="lb_sus_no"></label>  
									</div>
								</div>
							</div>
							<div class="col-md-5 col-md-offset-1">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">UNIT Name :</label>
									</div>
									<div class="col-12 col-md-8">
										<label id="lb_unit_name" ></label>
									</div>
								</div>
							</div>		
							<div class="col-md-5">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">MCR as on:</label>
									</div>
									<div class="col-12 col-md-8">
										<label id="approve_date" ></label>
									</div>
								</div>
							</div>
							<div class="col-md-5 col-md-offset-1">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">Date of Last Return :</label>
									</div>
									<div class="col-12 col-md-8">
										<label id="modify_date" ></label>
									</div>
								</div>
							</div>		
						</div>
					</div>
				</div>
			</div>		
			
				<div class="card-body card-block"  style="">
					<div class="col-md-12" id="EMAR" style="background-color: #f0f3f5;">

						<div class="col-md-6">
				<form method="POST" id="formID" enctype="multipart/form-data" id="exportExcelData" name="exportExcelData" action="exportFMVCRExcelAction?${_csrf.parameterName}=${_csrf.token}">
			<label style="color: #000000;">Download data in Excel:</label>
			<a href="#" title="Download Uploaded Documents" onclick="getExcel();">
			<i class="action_icons action_download"></i></a>
			<br>
			<label style="color: #000000;">Upload Excel:</label>
			<div style="display: flex; align-items: center;">
			<input type="file" style="width: 50%;" id="file_browser" name="file_browser" accept="application/vnd.ms-excel, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" class="form-control-sm form-control"/>
			<input type="submit" class="btn btn-success btn-sm" value="Submit" onclick="return tableUpload()">
			</div>
			<input type="hidden"  id="count" name="count">
	    	<input type="hidden"  id="countrow" name="countrow">
	    	<!-- Added By Mitesh -->
	    	<input type="hidden"  id="numberOfColumns" name="numberOfColumns">
			<input type="hidden"  id="ColumnNameExists" name="ColumnNameExists">
			<input type="hidden"  id="sus_no3" name="sus_no3" value="${sus_no}">
	    	<input type="hidden"  id="table_name" name="table_name" value="tb_tms_emar_report">
			<div class="card-body card-block">
	    		<div id="result"></div>
		    	<div>
		    		<table id="Report" style="display:none; width: 100%;font-size: 12px;"></table> 
		    	</div>
	    	</div>
		</form>

						
						</div>
						<div class="col-md-4">
						<div>
						<h5 style="text-align: center;">Raise Ticket</h5>
						<h6 style="color:red" align="center">(In Case of Observation)</h6>
						</div>
						<c:if test="${roleAccess == 'Unit'}">
							<div class="row form-group">
								<div class="col col-md-4">
									<label for="text-input" class=" form-control-label">Report Obsn </label>
								</div>
								<div class="col-12 col-md-8">
								
								 <select  id="report_obsn" name="report_obsn" class="form-control"> 
               				<option value="0">--Select--</option>
       							<c:forEach var="item" items="${GetHelpTopic}" varStatus="num" >
       								<option value="${item.codevalue}" name="${item.label}">${item.label}</option>
       							</c:forEach>
						    </select>
								
								</div>
							</div>
							</c:if>
							
							<c:if test="${roleAccess == 'Unit'}">
							
								Document Upload : <span style="color: red;">(*.zip, *.rar
									only)</span>
							</c:if>
							<div>
								<form method="POST" enctype="multipart/form-data" id="fileUploadForm">
									<input type="hidden" id="sus" name="sus" value="${sus_no}" />
									<input type="hidden" value="0" id="hdcount" name="hdcount" />
									<c:if test="${roleAccess == 'Unit'}">
										<input type="file" id="uploadMvcr" name="uploadMvcr"
											title="MVCR Upload .ZIP ,.RAR only" />
										<br>
										<br>
										<input type="submit" class="btn btn-primary btn-sm"
											value="Upload" id="btnSubmit" data-toggle='modal'>
										&emsp;
									</c:if>
									<a hreF="#" data-target='#rejectModal' data-toggle='modal'
										onclick="return getDownloadImageEMAR()"
										class="btn btn-primary btn-sm">View Uploaded Doc</a>
								</form>
							</div>
						</div>





						<div class="col-md-4">
							<div align="left">
								Total Vehicles : <label id="totalVeh">${TOTAL}</label>
							</div>
							<div align="left">
								Updated : <label id="totalUp">${UPDATE}</label>
							</div>
							<div align="left">
								Not Updated : <label id="totalNUp">${NotUpdated}</label>
							</div>
						</div>
					</div>
			
			<div id="tableshow"class="col-md-12" >
					<div id="divShow" style="display: block;">
						</div> 	
						
						
												                  
					<div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
								<span id="ip"></span>
				              	<table id="getSearchReport" class="table no-margin table-striped  table-hover  table-bordered report_print">
								<thead style="text-align: center;">
					                  <tr >
					                    <th style="width:2%;" rowspan="2">Ser No</th>
					                    <th rowspan="2" style="width:10%;">Type of Engr Origin Plant Eqpt</th>					                   
					                   	<th rowspan="2">Veh-Particulars</th>
									 	<th rowspan="2" style="width:7%;">Proc From</th>
					                    <th rowspan="2" style="width:5%;">Cl</th>
					                    <th colspan="2" style="width:15%;">Activity Details</th>
					                    <th rowspan="2" style="width:5%;">OH Due</th>
					                    <th rowspan="2" style="width:10%;">Date of OH (MM/YY)</th>
					                    <th colspan="2" style="width:15%;">Main</th>
					                    <!-- <th id="thRemarks" style="display:none; width:5%;" rowspan="2">Remarks</th> -->
					                    <th rowspan="2" style="width:10%;">Off Rd</th>
					                  
					                    <th rowspan="2"  style="width:5%;"> Action</th>  					                    
					                 </tr>
					                 <tr>					                  
						               <td > Hrs Run in current</td>
						               <td > Total Hrs</td>
						               <td > R1 </td>
		                               <td > Progress on</td>	
		                             </tr>
					                </thead>
					               <tbody style="text-align: center;">
									<c:if test="${list.size() == 0}" >
										<tr>
											<td colspan="20" align="center" style="color: red;"> Data Not Available </td>
										</tr>
									</c:if>
									<c:forEach var="item" items="${list}" varStatus="num" >
									<tr>
									 <td class="set-margin"  style="width:2%;">${num.index+1}</td> 
									 <td class="set-margin"   style="width:10%;" >${item[5]}</td> 
		

 <td >
<div class="watermarked" data-watermark="" id="divwatermark" style="display: block; ">
<span id="ip"></span>
				              	<table id="getSearchReport" class="table no-margin table-striped  table-hover  table-bordered ">
								<thead style="text-align: center;">
					                  <tr >
					                    <th > EM No</th>
					                   	<td style="background-color:#f0f3f5; color:black; text-align: center; "><span>${item[8]}</span></td>
					                    </tr>
					                        <tr >
					                    <th > Old EM No</th>
					                  	<td style="background-color:#f0f3f5; color:black; text-align: center;"><span>${item[31]}</span></td>
					                    </tr>
					                     <tr >
					                    <th >Engine No</th>
					                  	<td style="background-color:#f0f3f5; color:black; text-align: center;"><span>${item[22]}</span></td>
					                    </tr>
					                     <tr >
					                    <th > Chassis No</th>
					                  	<td style="background-color:#f0f3f5; color:black; text-align: center;"><span>${item[23]}</span></td>
					                    </tr>
					                     <tr >
					                    <th >Dt of Induction</th>
					                  	<td style="background-color:#f0f3f5; color:black; text-align: center;"><span>${item[3]}</span></td>
					                    </tr>
					                      <tr >
					                    <th > Mother Depot</th>
					                  	<td style="background-color:#f0f3f5; color:black; text-align: center;"><span>${item[2]}</span></td>
					                    </tr>
					                    
					                    
					                    </thead>
					                    </table>
					                    


					                    
  </div>

  </td>

									 <td class="set-margin"  style="width:7%;" >${item[33]}</td>
									 <td  class="set-margin"  style="width:5%;">${item[42]}</td>
									 <td class="set-margin"  style="width:7%;">${item[35]}</td>
									 <td class="set-margin"  style="width:8%;">${item[34]}</td> 
									 <td  class="set-margin"  style="width:5%;">${item[46]}</td>
									 <td class="set-margin"  style="width:10%;">${item[41]}</td> 
									 <td class="set-margin"  style="width:8%;">${item[24]}</td>
									 <td class="set-margin"  style="width:7%;">${item[25]}</td>
									<%--  <td class="set-margin"   style="display:none;">${item[37]}</td> --%>
									  <td  class="set-margin"  style="width:10%;">${item[36]}<br><span id="tdRemarks" >${item[49]}</span></td> 
									 <%-- <td  class="set-margin"  style="width:10%;">${item[49]}</td> --%>
									 <td class="set-margin"   style="width:5%;">${item[39]}</td>
									</tr>
								</c:forEach>
						</tbody>
					                </table>					          
				             </div>	 
				</div>	            
			</div>
			</div>
			<!-- model open -->
			<div class="modal fade" id="rejectModal" tabindex="-1" role="dialog"
				aria-labelledby="rejectModalLabel" aria-hidden="true"
				data-backdrop="static">
				<div class="modal-dialog">
					<div class="modal-content">
			
						<!-- Modal Header -->
						<div class="modal-header">
							<h4 class="modal-title">SUS NO wise Documents</h4>
							<button type="button" class="close" data-dismiss="modal">&times;</button>
						</div>
						<!-- Modal body -->
						<div class="modal-body">
							<div class="form-group">
								<div class="row"
									style="color: maroon; font-size: 16px; font-weight: bold;">
									<table
										class="table no-margin table-striped  table-hover  table-bordered report_print"
										id="getImgDocEMAR">
										<thead>
											<tr>
												<th>Document Name</th>
												<th>Action</th>
											</tr>
										</thead>
										<tbody>
										</tbody>
									</table>
								</div>
							</div>
							<!-- Modal footer -->
							<div class="modal-footer">
								<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
							</div>
			
						</div>
					</div>
				</div>
			</div>
			
<div class="modal" id="td-myModal" tabindex="-1" role="dialog"
	aria-labelledby="rejectModalLabel" aria-hidden="true"
	data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">New Ticket Remark</h4>
<!-- 				<button type="button" id ="close_div" class="close" data-dismiss="modal">&times;</button> -->
			</div>

			<div class="modal-body">
				<div class="form-group">
					<div class="col-md-12">
						<div class="row"
							style="color: maroon; font-size: 16px; font-weight: bold;">
							<div class="col-md-12 form-group">
								<div class="col-2 col-md-2">
									<label for="text-input" class=" form-control-label"><strong
										style="color: red;">*</strong> Issue summary </label>
								</div>
								<div class="col-10 col-md-10">
									<input type="text" style="width: 680;" id="issue_summary"
										name="issue_summary" placeholder="Maximum 100 characters"
										class="form-control char-counter" autocomplete="off"
										maxlength="100"></input>
								</div>
							</div>
							<div class="col-md-12 form-group">
								<div class="col-2 col-md-2">
									<label for="text-input" class=" form-control-label"><strong
										style="color: red;">*</strong> Description<br> <span
										style="font-size: 10px; font-size: 12px; color: red">(Max
											1000 words)</span></label>
								</div>
								<div class="col-10 col-md-10">
									<textarea rows="2" cols="250" id="description"
										style="height: 150px;" name="description"
										class="form-control char-counter1" autocomplete="off"
										maxlength="1000"></textarea>
								</div>
							</div>

						</div>
					</div>

				</div>

				<div align="center">
					<button type="button" name="submit"
						class="btn btn-primary login-btn" onclick="return updatedata();">Save</button>
					<button type="reset" name="reset" class="btn btn-primary login-btn"
						onclick="cleardata();">Reset</button>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" id="close_div" class="btn btn-danger" data-dismiss="modal"
					onclick="$('#rrr').attr('data-target','#')">Close</button>
			</div>
		</div>
	</div>
</div>
		
<%-- </form:form> --%>


<c:url value="UpdateEARreport" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid">
		<input type="hidden" name="updateid" id="updateid" value="0"/>
		<input type="hidden" name="ProcFrom" id="ProcFrom" value="0"/>
		<input type="hidden" name="current" id="current" value="0"/>
		<input type="hidden" name="Balh_for" id="Balh_for" value="0"/>
		<input type="hidden" name="oh_state" id="oh_state" value="0"/>
		<input type="hidden" name="date_of_oh" id="date_of_oh" value="0"/>
		<input type="hidden" name="Rispares" id="Rispares" value="0"/>
		<input type="hidden" name="pers" id="pers" value="0"/>
		<input type="hidden" name="unsv" id="unsv" value="0"/>
		<input type="hidden" name="prev" id="prev" value="0"/>	
		<input type="hidden" name="state" id="state" value="0"/>
		<input type="hidden" name="Depo" id="Depo" value="0"/>
		<input type="hidden" name="em_no" id="em_no" value="0"/>
		<input type="hidden" name="remarks" id="remarks" value="0"/>
		<input type="hidden" name="sus_no2" id="sus_no2" value="0"/>
		<input type="hidden" name="classi" id="classi" value="0"/>
		<input type="hidden" name="seviceable" id="seviceable" value="0"/>
		<input type="hidden" name="ser_reason" id="ser_reason" value="0"/>
		<input type="hidden" name="spareDemand1" id="spareDemand1" value="0"/>
</form:form>

<script>


function getExcel() {
 	
	var sus_no=$("#sus_no").val();
 	var unit_name=$("#unit_name").val();
 	
	$("#sus_noex").val(sus_no);
	$("#unit_nameex").val(unit_name);
	
	document.getElementById('typeReport1').value = 'excelL';
	document.getElementById('ExcelForm').submit();
}

function tableUpload(){
	if($("#file_browser").val() == ""){
		alert("Please Upload the File.");
		$("#file_browser").focus();
		return false;
	}
	if($("#table_name").val() == "0"){
		alert("Please Select the table.");
		$("#table_name").focus();
		return false;
	}
	var filename = $("#file_browser").val();
	var lastIndex = filename.lastIndexOf("\\");
    if (lastIndex >= 0) {
        filename = filename.substring(lastIndex + 1);           	        
        fl = filename.split('.');
        var str1 = fl[0];
        var str =  str1.substring(0, 4);
        var str2 = $("#table_name").val();
        if (str == "2024" && str2 == "tb_tms_emar_report"){
        	return true;
        }else{
        	alert("Please Enter Valid File and Valid Table Name.");
            return false;
        }
    }
}



$(function() {
	$("input#file_browser").on("change",function() {debugger;
		$("#Report").find("tbody").empty();

		var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.xlsx|.xls)$/;
		var regex_low = $("#file_browser").val().toLowerCase();
		if (regex.test(regex_low)) {
	
			var xlsxflag = false; /*Flag for checking whether excel is .xls format or .xlsx format*/
			if ($("#file_browser").val().toLowerCase()
					.indexOf(".xlsx|.xls") > 0) {
				xlsxflag = true;
			}

			/*Checks whether the browser supports HTML5*/
			if (typeof (FileReader) != "undefined") {

				var reader = new FileReader();

				reader.onload = function(e) {

					var data = e.target.result;
					
					/*Converts the excel data in to object*/
					if (xlsxflag) {
						var workbook = XLSX.read(data, 
						{
							type : 'binary'
						});
					}
					else {
						var workbook = XLS.read(data, {
							type : 'binary'
						});
					}
					/*Gets all the sheetnames of excel in to a variable*/
					var sheet_name_list = workbook.SheetNames;

					var cnt = 0; /*This is used for restricting the script to consider only first sheet of excel*/

					const sheetName = workbook.SheetNames[0];
					const worksheet = workbook.Sheets[sheetName];
					const range = XLSX.utils.decode_range(worksheet['!ref']);
					const numberOfColumns = range.e.c + 1;
					$("#numberOfColumns").val(numberOfColumns);
					const numberOfRows = range.e.r;
					alert("Total EM No.: " + numberOfRows);

					validateColumnName(workbook,'EM No');

					function validateColumnName(workbook, targetColumnName) {
					const sheetName = workbook.SheetNames[0];
					const worksheet = workbook.Sheets[sheetName];
					const headerRow = XLSX.utils.sheet_to_json(worksheet, { header: 1 })[0];
					$("#ColumnNameExists").val(headerRow.includes(targetColumnName));
					}
					
					sheet_name_list
							.forEach(function(y) { /*Iterate through all sheets*/
								/*Convert the cell value to Json*/

								if (xlsxflag) {
									var exceljson = XLSX.utils.sheet_to_json(workbook.Sheets[y]);

								} 
								else {
									var exceljson = XLS.utils.sheet_to_row_object_array(workbook.Sheets[y]);

								}

								if (exceljson.length > 0 && cnt == 0) {
									BindTable(exceljson,'#Report');
									$("#countrow").val(exceljson.length);
									cnt++;
								}

							});

					$("div#importshow").hide();

				}
				if (xlsxflag) {/*If excel file is .xlsx extension than creates a Array Buffer from excel*/
					reader
							.readAsArrayBuffer($("#file_browser")[0].files[0]);

				} else {
					reader
							.readAsBinaryString($("#file_browser")[0].files[0]);

				}
			} else {
				alert("Sorry! Your browser does not support HTML5!");
			}
		} else {
			alert("Please upload a valid Excel file!");
		}

	})

});



function BindTable(jsondata, tableid) {/*Function used to convert the JSON array to Html Table*/
	var columns = BindTableHeader(jsondata, tableid); /*Gets all the column headings of Excel*/

	for (var i = 0; i < jsondata.length; i++) {

		var td = "<tbody><tr>";
		for (var colIndex = 0; colIndex < columns.length; colIndex++) {
			var cellValue = jsondata[i][columns[colIndex]];

			var TH1 = columns[colIndex];

			td += "<td style='text-align:center;width: 10%'><input name='"+TH1+"_"+i+"' id='"+TH1+"_"+i+"' value='"+cellValue+"' style='text-align:center;' readonly='readonly' /></td>";

			if (cellValue == null)
				cellValue = "";

		}


		td += "</tbody>";
		$(tableid).append(td);

	}


}

function BindTableHeader(jsondata, tableid) {/*Function used to get all column names from JSON and bind the html table header*/
	var columnSet = [];
	var headerTr$ = $('<tr/>');
	for (var i = 0; i < jsondata.length; i++) {
		var rowHash = jsondata[i];
		for ( var key in rowHash) {
			if (rowHash.hasOwnProperty(key)) {
				if ($.inArray(key, columnSet) == -1) {/*Adding each unique column names to a variable array*/
					columnSet.push(key);
					//  alert(key + "key");
					headerTr$.append($('<th/>').html(key));
				}
			}
		}
	}
	$(tableid).append(headerTr$);
	return columnSet;
}

function validate(){	
	if($("#sus_no").val() == ""){
		alert("Please Enter the SUS No.");
		return false;
	}
	if($("#unit_name").val() == ""){
		alert("Please Enter the Unit Name.");
		return false;
	}	 	
	if($("#sus_no").val() != "" && $("#unit_name").val() != "") {
		 document.getElementById('tableshow').style.display='block'; 
	}
	return true;
}

function printDiv(divName) {
	if('${list.size()}' == 0){
		alert("Data Not Available..");
		return false;
	}
	 document.getElementById('printableArea').style.display='block'; 
	 document.getElementById('printableArea2').style.display='none'; 
		var unitname =$("#unit_name").val();
		var susno =$("#sus_no").val();
		$("#lb_unit_name").val(unitname) ;
	    $("#lb_sus_no").val(susno);
	    let popupWinindow
	    let innerContents = document.getElementById('printableArea1').innerHTML;
	    popupWinindow = window.open('', '_blank', 'width=600,height=700,scrollbars=yes,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
	    popupWinindow.document.open();
	    popupWinindow.document.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/miso/assets/scss/style.css"></head><body onload="window.print()">' + innerContents + '</html>');
	    popupWinindow.document.close();  
     document.getElementById('printableArea').style.display='none'; 
	 document.getElementById('printableArea2').style.display='block'; 
} 
</script>

 <script>
$(function() {
	
	$("#sus_no").keypress(function(){
		var sus_no = this.value;
			 var susNoAuto=$("#sus_no");
			  susNoAuto.autocomplete({
			      source: function( request, response ) {
			        $.ajax({
			        	type: 'POST',
					    	url: "getTargetSUSNoList?"+key+"="+value,
			        data: {sus_no:sus_no},
			          success: function( data ) {
			        	  var susval = [];
			        	  var length = data.length-1;
			        	  var enc = data[length].substring(0,16);
				        	for(var i = 0;i<data.length;i++){
				        		susval.push(dec(enc,data[i]));
				        	}
				        	response(susval); 
			          }
			        });
			      },
				      minLength: 1,
			      autoFill: true,
			      change: function(event, ui) {
			    	 if (ui.item) {   	        	  
			        	  return true;    	            
			          } else {
			        	  alert("Please Enter Approved SUS No.");
			        	  document.getElementById("sus_no").value="";
			        	  susNoAuto.val("");	        	  
			        	  susNoAuto.focus();
			        	  return false;	             
			          }   	         
			      }, 
			      select: function( event, ui ) {
			    	  var sus_no = ui.item.value;
			    	 
			    	     $.post("getActiveUnitNameFromSusNo?"+key+"="+value,{sus_no:sus_no}, function(j) {
				            }).done(function(j) {

			  					 var length = j.length-1;
						        	var enc = j[length].substring(0,16);
						        	$("#unit_name").val(dec(enc,j[0]));
				            }).fail(function(xhr, textStatus, errorThrown) {
				            });
			      } 	     
			});	
	});
	
    $("#unit_name").keypress(function(){
 			var unit_name = this.value;
 				 var susNoAuto=$("#unit_name");
 				  susNoAuto.autocomplete({
 				      source: function( request, response ) {
 				        $.ajax({
 				       	type: 'POST',
					    	url: "getTargetUnitsNameActiveList?"+key+"="+value,
 				        data: {unit_name:unit_name},
 				          success: function( data ) {
 				        	 var susval = [];
 				        	  var length = data.length-1;
 				        	  var enc = data[length].substring(0,16);
 					        	for(var i = 0;i<data.length;i++){
 					        		susval.push(dec(enc,data[i]));
 					        	}
 					        	var dataCountry1 = susval.join("|");
 				            var myResponse = []; 
 				            var autoTextVal=susNoAuto.val();
 							$.each(dataCountry1.toString().split("|"), function(i,e){
 								var newE = e.substring(0, autoTextVal.length);
 								if (e.toLowerCase().includes(autoTextVal.toLowerCase())) {
 								  myResponse.push(e);
 								}
 							});      	          
 							response( myResponse ); 
 				          }
 				        });
 				      },
 				      minLength: 1,
 				      autoFill: true,
 				      change: function(event, ui) {
 				    	 if (ui.item) {   	        	  
 				        	  return true;    	            
 				          } else {
 				        	  alert("Please Enter Approved Unit Name.");
 				        	  document.getElementById("unit_name").value="";
 				        	  susNoAuto.val("");	        	  
 				        	  susNoAuto.focus();
 				        	  return false;	             
 				          }   	         
 				      }, 
 				      select: function( event, ui ) {
 				    	 var target_unit_name = ui.item.value;
 				    	
 				       $.post("getTargetSUSFromUNITNAME?"+key+"="+value,{target_unit_name:target_unit_name}, function(j) {
			            }).done(function(j) {
			            	 var length = j.length-1;
					        	var enc = j[length].substring(0,16);
					        	$("#sus_no").val(dec(enc,j[0]));
			            }).fail(function(xhr, textStatus, errorThrown) {
			            });
 				      } 	     
 				}); 			
 		});	      
});						
</script> 

<script>
function Search(){
	
    $("#sus_no1").val($("#sus_no").val()) ;
   	if( $("#sus_no1").val() == "")
	 {
	   alert("Please Enter SUS No.");
	 }
   	else
   	{
   		document.getElementById('searchForm').submit();
   	}	
}

</script>
<c:url value="UpdateReportSearchCVehicleActivityReport" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="code_value1">
		<input type="hidden" name="sus_no1" id="sus_no1" value="0"/>
	</form:form> 

<c:url value="getExcelFMVCR" var="excelUrl" />
	<form:form action="${excelUrl}" method="post" id="ExcelForm" name="ExcelForm" modelAttribute="typeReport1">
		<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
		<input type="hidden" name="sus_noex" id="sus_noex" />
		<input type="hidden" name="unit_nameex" id="unit_nameex" />
	</form:form> 
<script>

$(document).ready(function() {
	colAdj("getSearchReport");
	$("div#divwatermark").val('').addClass('watermarked'); 
	watermarkreport();
	
	if('${sus_no}' == "")
	{
		$("#formID").hide();
		$("#tableshow").hide();
	}
	else
	{
		${con}
		${unsv}
		${classi}
		${oh_stateJS}
		$("#tableshow").show();
		$("#sus_no").val('${sus_no}');
		var sus_no = '${sus_no}';
	//26-01-1994
 	   	$.post("getTargetUnitNameList?"+key+"="+value, {sus_no:sus_no}, function(j) {

		}).done(function(j) {
			 var length = j.length-1;
	        	var enc = j[length].substring(0,16);
	        	$("#unit_name").val(dec(enc,j[0]));
			
		}).fail(function(xhr, textStatus, errorThrown) {
		});
	}
	if('${roleAccess}' == 'Unit'){
		var file = document.getElementById('uploadMvcr');
		file.onchange = function(e) {
		  	var ext = this.value.match(/\.([^\.]+)$/)[1];
			var FileSize = file.files[0].size / 1024 / 1024; // in MB
		  	if (FileSize > 2) {
	           alert('File size exceeds 2 MB');
	           this.value = '';
	        } else {
				switch (ext) {
					case 'rar':
				  	case 'zip':
				  	alert('Allowed.');
					break;
				  	default:
				    	alert('Please Only Allowed *.zip or *.rar File Extensions.');
				    this.value = '';
				}
	        }
		};
		}
	jQuery("#btnSubmit").click(function(event) {
		event.preventDefault();
		var sus_no = jQuery("#sus_no").val();
		if(sus_no == ""){
			alert("Please Enter SUS No.");
			jQuery("#sus_no").focus();
			return false;
		}
		var report_obsn = jQuery("#report_obsn").val();
		if(report_obsn == "0"){
			alert("Please Select Report Obsn.");
			jQuery("#report_obsn").focus();
			return false;
		}
		if(jQuery("#uploadMvcr").get(0).files.length === 0){
			alert("Please Select Document.");
			jQuery("#uploadMvcr").focus()
			return false;
		}
		
		openModal();

	});
	
	
	
}); 
function stateChange(id, catid) {
	var ser = "state" + id;
	var unsv = "unsv" + id;
	var data = document.getElementById(ser).value;
	
	
		if (data == "Yes") {
			$("#ser_reason"+id).show();
		
		} else {
			$("#ser_reason" + id).hide();
			
		}
}

function isNumber0_9Only(evt) {
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if (!(charCode >= 48 && charCode <= 57) && charCode != 8) {
		return false;
	}
	return true;
}
function Update(id,dt_oh,em_no){
		   var unsv = "unsv" + id
		   var ProcFrom= "ProcFrom" + id;
		   var current = "current" + id;
		   var Balh_for = "Balh_for" + id;
		   var state = "state" + id;
		   var date_of_oh = "date_of_oh" + id;
		   var Rispares = "Rispares" + id;
		   var pers = "pers" + id;
		   var prev = "prev" + id;
		   var oh_state = "oh_state" + id;
		   var ser = "state" + id;
		   var Depo = "Depo" + id;
		   var remarks = "remarks" + id;
		   var classification = "classification" + id;
		   var spareDemand = "spare_demand" + id;
		   var kj = $("select#"+ProcFrom).val();	
		   var servalue = $("select#"+unsv).val();
		   var state = $("select#"+state).val();
		   var classi = $("select#"+classification).val();
		   var Depovalue = $("select#"+Depo).val();
		   var seviceable = jQuery("input[name='seviceable"+id+"']:checked").val();//jQuery("#ser_status" + j).val();;
		   var ser_reason = jQuery("#ser_reason" + id).val();
		  
			
			
			
	var ids = "current" + id;
		var s1 = document.getElementById(ids).value;
		if (s1 == "") {
			alert("Please Enter Hrs Run In Current");
			return false;
		}
		var checked = $('input[id="km_2type' + id + '"]:checked').length;
		var checked2 = $('input[id="km_type' + id + '"]:checked').length;

		if (checked == 0 && checked2 == 0 && s1 != "0" && s1 != "") {
			alert("Please Select Inc/Dec.");
			return false;
		}

		document.getElementById('updateid').value = id;
		document.getElementById('ProcFrom').value = $("select#" + ProcFrom)
				.val();
		document.getElementById('current').value = $("input#" + current).val();
		document.getElementById('Balh_for').value = $("input#" + Balh_for)
				.val();
		document.getElementById('state').value = servalue;
		document.getElementById('Depo').value = Depovalue;
		document.getElementById('em_no').value = em_no;
		document.getElementById('sus_no2').value = $("#sus_no").val();

		document.getElementById('remarks').value = $("#" + remarks).val();
		if ($("input#" + date_of_oh).val() == "") {
			document.getElementById('date_of_oh').value = dt_oh;
		} else {
			document.getElementById('date_of_oh').value = $(
					"input#" + date_of_oh).val();
		}

		document.getElementById('Rispares').value = $("input#" + Rispares)
				.val();
		document.getElementById('pers').value = $("input#" + pers).val();
		document.getElementById('prev').value = $("input#" + prev).val();
		document.getElementById('unsv').value = servalue;
		document.getElementById('oh_state').value = state;
		document.getElementById('updateid').value = id;
		document.getElementById('classi').value = classi;
		document.getElementById('spareDemand1').value = $(
				"input#" + spareDemand).val();
		document.getElementById('ser_reason').value = ser_reason;
		document.getElementById('seviceable').value = seviceable;
		document.getElementById('updateForm').submit();

	}

	function Current(id, obj) {
		var data = obj.value;
		var Balh_for1 = "Balh_for" + id;
		var prev = "prev" + id;
		var lblcurr = "lblcurr" + id;
		var Balh_for = document.getElementById(Balh_for1).value;
		var sum = parseFloat(Balh_for) + parseFloat(data);
		$("input#" + Balh_for1).val(sum);
		var lblcurr_value = $("label#" + lblcurr).text();
		var prev_value = sum - lblcurr_value;
		$("label#" + prev).val(prev_value);
	}

	function uncheck(j) {
		var radios = document.getElementsByName(j);
		for (var i = 0; i < radios.length; i++) {
			radios[i].checked = false;
		}
	}

	function sum(sum_type, id) {
		
		var s = $("#Balh_tot" + id).val();
		var ids = "current" + id;
		var s1 = document.getElementById(ids).value;
		//	var sum_type=document.getElementById("km_type").value;
		var vehcl_kms_run = "";
		if (sum_type == "0") {
			vehcl_kms_run = parseInt(s) + parseInt(s1);
			if (vehcl_kms_run > 300000) {
				
				alert("Exceeding Ceiling Hrs Run");
				var k = 0;
				document.getElementById(ids).value = k;
				document.getElementById('Balh_for' + id).value = s;
				uncheck("km_type" + id);
				$("#veh_km_run_period").focus();
			}
			if (vehcl_kms_run <= 300000) {
				document.getElementById('Balh_for' + id).value = vehcl_kms_run;
			}
		}
		if (sum_type == "1") {
			vehcl_kms_run = parseInt(s) - parseInt(s1);
			if (vehcl_kms_run < 0) {
				vehcl_kms_run = 0;
				document.getElementById('Balh_for' + id).value = vehcl_kms_run;
			}
			if (vehcl_kms_run > 300000) {
				alert("Exceeding Ceiling Hrs Run");
				var k = 0;
				document.getElementById(ids).value = k;
				document.getElementById('Balh_for' + id).value = s;
				uncheck("km_type" + id);
				$("#veh_km_run_period").focus();
			}
			if (vehcl_kms_run <= 300000) {
				document.getElementById('Balh_for' + id).value = vehcl_kms_run;
			}
		}
	}


	
	function getDownloadImageEMAR(){
		
		
		jQuery("table#getImgDocEMAR tbody").empty();
		var sus_no = jQuery("#sus_no").val();

				 	$.post("getDocumentEMAR?"+key+"="+value, {sus_no:sus_no}).done(function(j) {				
				 		for (var x = 0; x < j.length; x++) {
							if(j[x].document != null ){
								jQuery("table#getImgDocEMAR").append('<tr id="'+x+'"><td>'+j[x].document+'</td><td><a hreF="#" onclick="getDownloadImageEMAR1('+ j[x].id +')" class="btn btn-primary btn-sm">Download</a></td>' +'</tr>');
							}
						}	
					}).fail(function(xhr, textStatus, errorThrown) {
				});
	}

	function getDownloadImageEMAR1(id){  
		document.getElementById("id1").value = id;
		document.getElementById("getDownloadImageForm1").submit();
	} 
	function openModal(element) {debugger;
	var modal = document.getElementById("td-myModal");

	modal.style.display = "block";


	var closeDiv = document.getElementById("close_div");
	closeDiv.onclick = function () {
	    modal.style.display = "none";
	};
	}


	function cleardata()
	{
	  	document.getElementById("issue_summary").value ="";
	  	document.getElementById("description").value ="";
	}
	function updatedata()
	{
	var issue_summary = $("input#issue_summary").val(), description = $("textarea#description").val();
	var report_obsn =$("select#report_obsn").val();
	if(issue_summary == "")
	{
		alert("Please Enter issue summary");
		$("input#issue_summary").focus();
		return false;
	}
	if(description == "")
	{
		alert("Please Enter description");
		$("textarea#description").focus();
		return false;
	}
	else if((issue_summary != "" && issue_summary != null) || (description != "" && description != null))
	{
//			var id =document.getElementById('rejectid_model').value; 
		debugger;
		var form = $('#fileUploadForm')[0];
		var data = new FormData(form);
		data.append("issue_summary",issue_summary);
		data.append("description",description);
		data.append("report_obsn",report_obsn);
		$.ajax({
			type : "POST",
			enctype : 'multipart/form-data',
			url : "uploadDocEMAR?${_csrf.parameterName}=${_csrf.token}",
			data : data,
			processData : false,
			contentType : false,
			cache : false,
			timeout : 600000,
			success : function(data) {
				alert(data);
				$("#uploadMvcr").val(null);
				
				if(data == "Document Uploaded Successfully")
				{
					 document.getElementById('issue_summary').value ="";
					 document.getElementById('description').value ="";
					
					 
					 //////////////////// close pop up
					 $('.modal').removeClass('in');
					 $('.modal').attr("aria-hidden","true");
					 $('.modal').css("display", "none");
					 $('.modal-backdrop').remove();
					 $('body').removeClass('modal-open');
					 //////////////////// end close pop up
					
					 
				}
				
				
			}
		});
		
		

		return true;
	}

	return true;
	}
	function ChangeSer_Status(em_no,id,value)
	{debugger;
		if(value == 'Yes'){
			jQuery("div#ser_reason"+em_no).show();
		}else{
			jQuery("div#ser_reason"+em_no).hide();
			jQuery("select#ser_reason"+id).val("0");
		}
	}

</script>



<c:url value="getDownloadImageEMAR_Report" var="imageDownloadUrl1" />
	<form:form action="${imageDownloadUrl1}" method="post" id="getDownloadImageForm1" name="getDownloadImageForm1" modelAttribute="id1">
		<input type="hidden" name="id1" id="id1" value="0"/>
	</form:form>