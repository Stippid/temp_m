<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
<script type="text/javascript"
	src="js/exportExcelData/infragistics.core.js"></script>
<script type="text/javascript"
	src="js/exportExcelData/infragistics.lob.js"></script>
<script type="text/javascript"
	src="js/exportExcelData/infragistics.ext_ui.js"></script>
<script type="text/javascript"
	src="js/exportExcelData/infragistics.documents.core_core.js"></script>
<script type="text/javascript"
	src="js/exportExcelData/infragistics.excel_core.js"></script>
<script type="text/javascript"
	src="js/exportExcelData/infragistics.xml.js"></script>
<script type="text/javascript"
	src="js/exportExcelData/infragistics.documents.core_openxml.js"></script>
<script type="text/javascript"
	src="js/exportExcelData/infragistics.excel_serialization_openxml.js"></script>
<link rel="stylesheet" href="js/common/nrcss.css">


<style type="text/css">
.td-img-modal {
	display: none;
	position: fixed;
	top: 0;
	left: 0;
	right: 0;
	bottom: 0;
	background-color: rgba(0, 0, 0, 0.7);
	z-index: 9999;
	text-align: center;
	background: #0000008;
}

.td-img-modal .modal-content {
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	max-width: 70%;
	max-height: 70%;
}

.zoom-icon {
	position: relative;
}

.td-img-modal button.close {
	position: absolute;
	right: 10px;
	top: 10px;
	background: red;
	color: white;
	height: 40px;
	width: 40px;
	display: flex;
	justify-content: center;
	align-items: center;
	border-radius: 50%;
	font-size: 30px;
}
</style>

<div class="animated fadeIn">
	<div class="">
		<div class="container" align="center">
			<div class="card">
				<div class="card-header"
					style="border: 1px solid rgba(0, 0, 0, .125); text-align: center;">
					<h5>Animal Daily Census Return</h5>
				</div>
				<div class="card-body">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
									<label class=" form-control-label">SUS No </label>
								</div>
								<div class="col-12 col-md-6">
									<input type="text" id="sus_no" name="sus_no" placeholder=""
										class="form-control " autocomplete="off" value="${sus_no}"
										readonly="readonly">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-6">
									<label class=" form-control-label">Unit Name </label>
								</div>
								<div class="col-12 col-md-6">
									<input type="text" id="unit_name" name="unit_name"
										class="form-control" autocomplete="off" value="${unit_name}"
										readonly="readonly">
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="card-body"></div>
<!-- <div style="text-align: center;">
    <button class="btn btn-primary btn-sm" id="toggleDivBtn">Raise Ticket Form</button>
</div> -->
<div class="card-body"></div>
<div id="ticketForm" style="display:none;">
    <table class="table no-margin table-striped table-bordered report_print">
        <tbody>
            <tr>
                <td>
                    <br>
                    <h6 style="font-weight: bold; text-align: center;">Raise Ticket</h6>
                    <div>
                        <div>
                            <div style="font-weight: bold; text-align: center;">
                                <label for="text-input" class="form-control-label">Report Obs</label>
                            </div>
                            <div style="font-weight: bold; text-align: center; margin: 0 100px 0 100px;" >
                                <select id="report_obsn" name="report_obsn" class="form-control">
                                    <option value="0" style="text-align: center;">-------------------Select-------------------</option>
                                    <c:forEach var="item" items="${GetHelpTopic}" varStatus="num">
                                        <option value="${item.codevalue}" name="${item.label}">${item.label}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                </td>
                <td>
                    <br>
                    <h6 style="font-weight: bold; text-align: center;">Upload Document</h6>
                    <span style="color: red;">(*.zip or *.rar Only)</span>
                    <form method="POST" enctype="multipart/form-data" id="fileUploadForm">
                        <input type="hidden" id="sus" name="sus" value="${sus_no}" />
                        <input type="file" id="uploadDacr" name="uploadDacr" title="DACR Upload .ZIP only" />
                        <br>
                        <br>
                        <div style="text-align: center;">
                        <button class="btn btn-primary btn-sm" value="Upload" id="btnSubmit" style="text-align: center;">Upload</button>
                        <a href="#" data-target="#rejectModal" data-toggle="modal" onclick="getDownloadImageDACR()" class="btn btn-primary btn-sm" style="text-align: center;">Download</a>
                     </div>
                    </form>
                </td>
            </tr>
        </tbody>
    </table>
</div>
		<div class="" style="padding: 0; text-align: center;">
		<div id="reportshow1" style="width: 100%; display: inline-block;">
				<table id="SearchMCRReport" class="table no-margin table-striped  table-hover  table-bordered report_print">
						<thead>
						<tr>
							<th style="text-align: center;">Ser No</th>
							<th style="text-align: center;">Army No</th>
							<th style="text-align: center;">Name of Dog</th>
							<th style="text-align: center;">DOB</th>
							<th style="text-align: center;">Speciality</th>
							<th style="text-align: center;">Colour</th>
							<th style="text-align: center;">Breed</th>
							<th style="text-align: center;">Fitness</th>
							<th style="text-align: center;">MCNO</th>
							<th style="text-align: center;">Sex</th>
							<th style="text-align: center;">TOS</th>
							<th style="text-align: center;">Age</th>
							<th style="text-align: center;">Action</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${list.size() == 0}">
							<tr>
								<td colspan="7" align="center" style="color: red;">Data Not
									Available</td>
							</tr>
						</c:if>
						<c:forEach items="${list}" var="list" varStatus="num">
							<tr>
								<td style="text-align: center;">${num.index+1}</td>
								<td id="ba_no" style="text-align: center;">${list[0]}</td>
								<td id="des" style="text-align: center;">${list[1]}</td>
								<td id="cl" style="text-align: center;">${list[2]}</td>
								<td id="total_km" style="text-align: center;">${list[3]}</td>
								<td id="engine_type" style="text-align: center;">${list[4]}</td>
								<td id="aux" style="text-align: center;">${list[5]}</td>
								<td id="main_gun" style="text-align: center;">${list[6]}</td>
								<td id="sec_gun" style="text-align: center;">${list[7]}</td>
								<td id="main_radio" style="text-align: center;">${list[8]}</td>
								<td id="con" style="text-align: center;">${list[9]}</td>
								<td id="remarks" style="text-align: center;">${list[10]}</td>
								<td style="text-align: center;">${list[11]}</td>

							</tr>
						</c:forEach>
					</tbody>
				</table>

				<div align="center">
					<a href="adcr" class="btn btn-success btn-sm">Back</a>
				</div>
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
							id="getImgDocMCR">
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
				<button type="button" id="close_div" class="btn btn-danger"
					data-dismiss="modal" onclick="$('#rrr').attr('data-target','#')">Close</button>
			</div>
		</div>
	</div>
</div>

<c:url value="viewadcr" var="viewadcrurl" />
<form:form action="${viewadcrurl}" method="post" id="editForm"
	name="editForm" modelAttribute="editArmy_no">
	<input type="hidden" name="editArmy_no" id="editArmy_no" value="0" />
</form:form>

<c:url value="getExcelFMCR" var="excelUrl" />
<form:form action="${excelUrl}" method="post" id="ExcelForm" name="ExcelForm" modelAttribute="typeReport1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
	<input type="hidden" name="sus_noex" id="sus_noex" />
	<input type="hidden" name="unit_nameex" id="unit_nameex" />
</form:form> 


<script>


function validate(){
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
        if (str == "2024" && str2 == "tb_tms_census_retrn"){
        	return true;
        }else{
        	alert("Please Enter Valid File and Valid Table Name.");
            return false;
        }
    }
}



$(function() {
	$("input#file_browser").on("change",function() {
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
									alert("Total BA No.: " + numberOfRows);

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


function getExcel() {
 	
	var sus_no=$("#sus_no").val();
 	var unit_name=$("#unit_name").val();
 	
	$("#sus_noex").val(sus_no);
	$("#unit_nameex").val(unit_name);
	
	document.getElementById('typeReport1').value = 'excelL';
	document.getElementById('ExcelForm').submit();
}


var count = 0;
jQuery(document).ready(function() {
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
	$("#btnSubmit").click(function(event) {
		event.preventDefault();
		
		var sus_no = $("#sus_no").val();
		if(sus_no == ""){
			alert("Please Select SUS No.");
			$("#sus_no").focus()
			return false;
		}
		var report_obsn = jQuery("#report_obsn").val();
		if(report_obsn == "0"){
			alert("Please Select Report Obsn.");
			jQuery("#report_obsn").focus();
			return false;
		}
		if($("#uploadMvcr").get(0).files.length === 0){
			alert("Please Select Document.");
			$("#uploadMvcr").focus()
			return false;
		}
		openModal();
	});
	
	
	var sus_no = '${sus_no}';
   	var unit_name = '${unit_name}'; 
   
   	if(unit_name == ""){
   		
 	   	$.post("getActiveUnitNameFromSusNo?"+key+"="+value, {sus_no:sus_no}, function(j) {

		}).done(function(j) {
			for ( var i = 0; i < j.length; i++) {
   				var length = j.length-1;
	        	var enc = j[length].substring(0,16);
	        	$("#unit_name").val(dec(enc,j[0]));
   		 	}
		}).fail(function(xhr, textStatus, errorThrown) {
		});	
   	}
    $("#sus_no").val(sus_no);
    $("#unit_name").val(unit_name);
   
     
});
var u = 0;
 

function getDownloadImageMCR(){
	
	
	jQuery("table#getImgDocMCR tbody").empty();
	var sus_no = jQuery("#sus_no").val();

			 	$.post("getDocumentMCR?"+key+"="+value, {sus_no:sus_no}).done(function(j) {				
			 		for (var x = 0; x < j.length; x++) {
						if(j[x].document != null ){
							jQuery("table#getImgDocMCR").append('<tr id="'+x+'"><td>'+j[x].document+'</td><td><a hreF="#" onclick="getDownloadImageMCR1('+ j[x].id +')" class="btn btn-primary btn-sm">Download</a></td>' +'</tr>');
						}
					}	
				}).fail(function(xhr, textStatus, errorThrown) {
			});
}

function getDownloadImageMCR1(id){  
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
			
		var form = $('#fileUploadForm')[0];
		var data = new FormData(form);
		data.append("issue_summary",issue_summary);
		data.append("description",description);
		data.append("report_obsn",report_obsn);
		$.ajax({
			type : "POST",
			enctype : 'multipart/form-data',
			url : "uploadDocMCR?${_csrf.parameterName}=${_csrf.token}",
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
 
 
 
</script>
<script type="text/javascript">
    function open1(army_no) {
        // Ensure that the element with id 'editArmy_no' exists
        var armyInput = document.getElementById("editArmy_no");

        if (armyInput) {
            // Set the value of the input field with id 'editArmy_no' to the passed 'army_no'
            armyInput.value = army_no;
            // Submit the form with id 'editForm'
            document.getElementById("editForm").submit();
        } else {
            // Log an error if the element is not found
            console.error("Element with id 'editArmy_no' not found.");
        }
    }
</script>
>
<script>
    document.getElementById('toggleDivBtn').addEventListener('click', function() {
        var formDiv = document.getElementById('ticketForm');
        if (formDiv.style.display === 'none' || formDiv.style.display === '') {
            formDiv.style.display = 'block';
        } else {
            formDiv.style.display = 'none';
        }
    });
</script>

<c:url value="getDownloadImageMCR_Report" var="imageDownloadUrl1" />
<form:form action="${imageDownloadUrl1}" method="post"
	id="getDownloadImageForm1" name="getDownloadImageForm1"
	modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>