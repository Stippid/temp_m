<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" href="js/common/nrcss.css">
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="Stylesheet" href="js/Calender/jquery-ui.css">
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/upload_xls/xlsx.core.min.js"></script>
<script src="js/upload_xls/xls.min.js"></script>
<script src="js/upload_xls/jquery.1.10.js"></script>
<script type="text/javascript" src="js/Calender/jquery-ui.js"></script>
<script type="text/javascript" src="js/common/commonmethod.js"></script>
<script src="js/JS_CSS/jquery.dataTables.js"></script>
<script src="js/cue/cueWatermark.js"></script>

<link rel="stylesheet" href="js/cue/cueWatermark.css">

<script>
	var ususno = "${roleSusNo}";
	var role = "${role}";
</script>
<style>
.scrollit {
	overflow: scroll;
	height: 300px;
	width: 100%;
}

.scrollit table td input {
	border: 0;
	background: transparent;
	font-size: 12px;
	height: 20px;
}

#exceltbl input{
	border : none !important;
	cursor: default;
}

#exceltbl td{
	cursor: default;
}

.row {
	padding-bottom: 10px !important;
}
.thhead {
	background-color: brown!important;;
	color:yellow!important;;
}
</style>
<body onload="setMenu();">
<form:form id="uploadBEAction" class="fp-form"	action="uploadBEAction?${_csrf.parameterName}=${_csrf.token}"	method="POST" enctype="multipart/form-data">
	<c:if test="${not empty errorList}">
		<div class="modal fade show" id="errorModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" style="padding-right: 17px; display: block;" aria-modal="true">
			<div class="modal-dialog modal-lg" role="document">
		    	<div class="modal-content">
		        	<div class="modal-header btn-danger">
		            	<h5 class="modal-title" id="exampleModalLabel">Error</h5>
		                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
		                	<span aria-hidden="true" class="btn-dismiss">×</span>
		                </button>
		         	</div>
		            <div class="modal-body">
		            	<div class="err-list">
		            		<c:forEach var="item" items="${errorList}" varStatus="num">
								<div><span>${num.index+1})</span> ${item}</div>
							</c:forEach>
		            	</div>
		            </div>
		            <div class="modal-footer">
		            	<button class="btn btn-secondary btn-dismiss" type="button" data-dismiss="modal">Close</button>
		            </div>
		       	</div>
		 	</div>
		</div>
	</c:if>
	<div class="" style="width:100%">
		<div class="containerr" align="center">
			<div class="card">
				<div class="card-header mms-card-header"
					style="min-height: 60px; padding-top: 10px;">
					<b>UPLOAD EXCEL SHEET FOR FUND ALLOTMENT (FIRST LEVEL)</b>
				</div>
				<input type="hidden" id="countrow" name="countrow">
				<input type="hidden" id="deno" name="deno">
				<input type="hidden" id="author" name="author">
				<div class="card-body card-block ncard-body-bg" style="width:100%">
					<div class="row">
						<div class="col-md-12">
							<div class="col-md-3" style="text-align: left;">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">* </strong>Upload for </label>
							</div>
							<div class="col-md-4">
								<select id="fp_upldfor" name="fp_upldfor"
									class="form-control-sm" title="Select Upload for">
									<c:forEach var="item" items="${n_rpttype}" varStatus="num">
										<option value="${item[2]}">${item[3]}</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-md-3" style="text-align: left;">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">* </strong>For Financial Year </label>
							</div>
							<div class="col-md-2">
								<select id="fin_year" name="fin_year"
									class="form-control form-control-sm"
									title="Select Financial Year">
									<option value="-1">---- Select FY ----</option>
									<c:forEach var="item" items="${n_finyr}" varStatus="num">
										<c:if test='${item[0] == n_cfinyr}'>
											<option value="${item[0]}">${item[2]}</option>
										</c:if>
										<c:if test='${item[0] != n_cfinyr}'>
											<option value="${item[0]}">${item[2]}</option>
										</c:if>
									</c:forEach>
								</select>
							</div>

						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="col-md-3" style="text-align: left;">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">* </strong>Select Excel File only</label>
							</div>
							<div class="col-md-9">
								<input type="file" id="excelfile" accept="xlsx" class="form-control-sm form-control ignore" title="Choose Excel file to Upload" />
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<span>Please Note: EXCEL File Name should not have any special char.</span>
						</div>
					</div>
					
				</div>
				<div id="exltb" class="card-body card-block" style="display: none; padding: 0px;">
					<div class="col-md-12 row" style="width:99vw!important">
					<div  class="watermarked" data-watermark="" id="divwatermark">
						<div class="nPopTable" align="center" style="height: 300px;width:100%;">
							<table id="exceltable" style="table-layout: fixed;">
								<thead
									style="text-align: center; line-height: 20px; font-size: .9vw;">
									<tr>
										<th width="1%" class="thhead"></th>
										<th width="1%"  class="thhead"></th>
										<th width="1%"  class="thhead"></th>
										<th width="6%"  class="thhead">Code</th>
										<th width="20%"  class="thhead">Head</th>
										<th width="10%"  class="thhead">Fund Recd</th>
										<th width="10%"  class="thhead">Budget Holder</th>
										<th width="10%"  class="thhead">Cur Yr Allocation</th>
										<th width="10%"  class="thhead">Prv Yr Allocation</th>
										<th width="10%"  class="thhead">Cur Yr Projection</th>										
									</tr>
								</thead>
								<tbody id="exceltbl"
									style="font-size: .90vw; text-decoration: none;"></tbody>
							</table>
						</div>	
						</div>
						</div>				
						<div class="col-md-12" style="margin:5px;">
								<span><b>After Clicking this button, Wait for 30 Seconds. Screen may appear Unresponsive. Please wait ...<b></b></span>
								<input type="submit" class="btn btn-success btn-sm  nGlow margin-auto" value="Upload Allotment (First Level)" id="btnSubmit">
						</div>					
				</div>
				<div class="card-footer" align="center">	
				</div>

			</div>
		</div>
	</div>	
</form:form>
<script>
	var key = "${_csrf.parameterName}";
	var value = "${_csrf.token}";
	$(document).ready(function() {
		$("div#divwatermark").val('').addClass('watermarked');	
		watermarkreport();
		
		$("#fin_year").on("change", function() {
			var value = $("#fin_year").val();
			var value1 = parseInt(value)+1;
			alert("Pl Note - You are going to Upload \n\nfor FY - "+value+" - "+value1);			
		});
		
	});
</script>

<script>
	$(".btn-dismiss").click(function(){
		$("#errorModal").hide();
	});
	
	$("input#excelfile").on("change",function() {
		$("#exltb").show();
		$("#nrWaitLoader").show();
		
		var regex = /^([a-zA-Z0-9\s_\\.\-:])+(xlsx)$/;
						
		if (regex.test($("#excelfile").val().toLowerCase())) {
			var xlsxflag = false;
			if ($("#excelfile").val().toLowerCase().indexOf(".xlsx") > 0) {
				xlsxflag = true;
			}
			if (typeof (FileReader) != "undefined") {
				var reader = new FileReader();
				
				reader.onload = function(e) {
					var data = e.target.result;
					
					if (xlsxflag) {
						var workbook = XLSX.read(data, {type : 'binary'});
						xlsxflag = true;
					} else {
						var workbook = XLS.read(data, {type : 'binary'});
						xlsxflag = true;
					}
					
					var sheet_name_list = workbook.SheetNames;
					$("#author").val(workbook.Props.Author);
					var cnt = 0;
					sheet_name_list.forEach(function(y) { 
						if (xlsxflag)
							var exceljson = XLSX.utils.sheet_to_json(workbook.Sheets[y]);
						else
							var exceljson = XLS.utils.sheet_to_row_object_array(workbook.Sheets[y]);
						
						if (exceljson.length > 0 && cnt == 0) {
							
							BindTable(exceljson,'#exceltable');
							cnt++;
						}

					});
					$('#exceltable').show();
				}
				if (xlsxflag)
					reader.readAsArrayBuffer($("#excelfile")[0].files[0]);
				else
					reader.readAsBinaryString($("#excelfile")[0].files[0]);
			} 
			else{
				alert("Sorry! Your browser does not support HTML5!");
				document.getElementById("excelfile").value = "";
				$("#exceltbl").html('');
				$("#exltb").hide();
				$("#nrWaitLoader").hide();
				
			}
		} else {
			alert("Please upload a valid Excel file!\nPlease check file name and remove brackets from file name");
			document.getElementById("excelfile").value = "";
			$("#exceltbl").html('');
			$("#exltb").hide();
			$("#nrWaitLoader").hide();
		}
	});
</script>

<script>
	function BindTable(jsondata, tableid) {
		
		var columns = BindTableHeader(jsondata, tableid);

		var nChkcol = ['FR_SUS_NO', 'DFLAG', 'TR_HEAD', 'MAJOR_HEAD',
				'MINOR_HEAD', 'SUB_HEAD', 'HEAD_CODE', 'HEAD','FUND_RECD', 'BG_SUS',
				'BG_HLDER_FMN', 'BG_HLDER', 'PRV_ALLOT', 'CUR_PROJ',
				'CUR_ALLOT','FMT'];
		var align="left",cellValue,cellVal,colSpan=1,defVal='',td,row,class_name='';
		var nChkcolsz = [ '1%', '1%', '1%', '2%', '2%', '2%', '6%', '25%','1%', '1%', '10%', '15%', '10%', '10%', '10%' ];
		var row1 = validateFileData(jsondata[1]);
		if (!row1.isValid) {
			$("#exceltbl").html('');
			$("#excelfile").val('');
			$("#exltb").hide();
			$("#nrWaitLoader").hide(100);
			alert(row1.msg);
			return false;
		}
		
		var deno = jsondata[0][nChkcol[15]] || 'RS';
		
		$("#deno").val(deno);
		for (var i = 1; i < jsondata.length; i++) {
					
			row = validateData(jsondata[i]);

			if (row.isValid) {
		
				td = "<tr>";
			
				if (columns[0] == nChkcol[0]) {
					cellValue = jsondata[i][columns[0]]||'';
					td += "<td style='display:none;width:"+nChkcolsz[0]+"'><input type='text' name='"+columns[0]+"_"+i+"' id='"+columns[0]+"_"+i+"' value='"+cellValue+"'/></td>";
				}
				if (columns[1] == nChkcol[1]) {
					cellValue = jsondata[i][columns[1]]||'';
					td += "<td style='display:none;width:"+nChkcolsz[1]+"'><input type='text' name='"+columns[1]+"_"+i+"' id='"+columns[1]+"_"+i+"' value='"+cellValue+"'/></td>";
				}
				if (columns[2] == nChkcol[2]) {
					cellValue = jsondata[i][columns[2]]||'';
					td += "<td style='display:none;width:"+nChkcolsz[2]+"'><input type='text' name='"+columns[2]+"_"+i+"' id='"+columns[2]+"_"+i+"' value='"+cellValue+"'/></td>";
				}
				if (columns[3] == nChkcol[3]) {
					if (typeof (jsondata[i][columns[3]]) == 'string'
							&& jsondata[i][columns[3]].length > 0) {
						td += "<td colspan='5' style='width:"+nChkcolsz[3]+"' title='"+jsondata[i][columns[3]]+"'><input type='text' name='"+columns[3]+"_"+i+"' id='"+columns[3]+"_"+i+"' value='"+jsondata[i][columns[3]]+"' style='text-align:left;border:none;width:100%;' readonly/></td>";
					} else {
						td += "<td style='width:"+nChkcolsz[3]+"'></td>";
						if (columns[4] == nChkcol[4]) {
							if (typeof (jsondata[i][columns[4]]) == 'string'
									&& jsondata[i][columns[4]].length > 0) {
								td += "<td colspan='4' style='width:"+nChkcolsz[4]+"' title='"+jsondata[i][columns[4]]+"'><input type='text' name='"+columns[4]+"_"+i+"' id='"+columns[4]+"_"+i+"' value='"+jsondata[i][columns[4]]+"' style='text-align:left;border:none;width:100%;' readonly/></td>";
							} else {
								td += "<td style='width:"+nChkcolsz[4]+"'></td>";
								if (columns[5] == nChkcol[5]) {
									if (typeof (jsondata[i][columns[5]]) == 'string'
											&& jsondata[i][columns[5]].length > 0) {
										td += "<td colspan='3' style='width:"+nChkcolsz[5]+"' title='"+jsondata[i][columns[5]]+"'><input type='text' name='"+columns[5]+"_"+i+"' id='"+columns[5]+"_"+i+"' value='"+jsondata[i][columns[5]]+"' style='text-align:left;border:none;width:100%;' readonly/></td>";
									} else {
										td += "<td style='width:"+nChkcolsz[5]+"'></td>";
									}
								}
							}
						}
					}
				}
				
				if (columns[6] == nChkcol[6]) {
					if (typeof (jsondata[i][columns[6]]) == 'string'
							&& jsondata[i][columns[6]].length > 0) {
						td += "<td style='width:"+nChkcolsz[6]+"' title='"+jsondata[i][columns[6]]+"'><input type='text' name='"+columns[6]+"_"+i+"' id='"+columns[6]+"_"+i+"' value='"+jsondata[i][columns[6]]+"' style='text-align:left;border:none;width:100%;' readonly/></td>";
						td += "<td style='width:"+nChkcolsz[7]+"' title='"+jsondata[i][columns[7]]+"'><input type='text' name='"+columns[7]+"_"+i+"' id='"+columns[7]+"_"+i+"' value='"+jsondata[i][columns[7]]+"' style='text-align:left;border:none;width:100%;' readonly/>";
						td += "</td>";
					} else {
						
						td += "<td style='width:"+nChkcolsz[6]+"'></td>";
							td += "<td style='width:"+nChkcolsz[7]+"'></td>";
					}
				}
				
				if (columns[8] == nChkcol[8]) {
					if (typeof (jsondata[i][columns[8]]) == 'string'
							&& jsondata[i][columns[8]].length > 0) {
						
						isNaN(jsondata[i][columns[8]]) ?  (align="center",colSpan=2,defVal='') : (align="right",colSpan=1,defVal=0);
						
						td += "<td colspan='"+colSpan+"' style='width:"+nChkcolsz[8]+"' title='"+jsondata[i][columns[8]]+"'><input type='text' name='"+columns[8]+"_"+i+"' id='"+columns[8]+"_"+i+"' value='"+jsondata[i][columns[8]]+"' style='text-align:"+align+";border:none;width:100%;' readonly/></td>";
						
					} else {
						if(jsondata[i][columns[11]]){
							td += "<td style='width:"+nChkcolsz[8]+"'></td>";
						}
						
					}
				}
				
				if (columns[10] == nChkcol[10]) {
					if (typeof (jsondata[i][columns[10]]) == 'string'
							&& jsondata[i][columns[10]].length > 0) {
						td += "<td style='display:none;'><input type='text' name='"+columns[9]+"_"+i+"' id='"+columns[9]+"_"+i+"' value='"+jsondata[i][columns[9]]+"' style='text-align:left;border:none;width:100%;' readonly/></td>";
						td += "<td style='display:none;'><input type='text' name='"+columns[10]+"_"+i+"' id='"+columns[10]+"_"+i+"' value='"+jsondata[i][columns[10]]+"' style='text-align:left;border:none;width:100%;' readonly/></td>";
						td += "<td style='text-align:right;width:"+nChkcolsz[11]+"' title='"+jsondata[i][columns[11]]+"'><input type='text' name='"+columns[11]+"_"+i+"' id='"+columns[11]+"_"+i+"' value='"+jsondata[i][columns[11]]+"' style='text-align:left;border:none;width:100%;' readonly/></td>";
					}
					if (columns[14] == nChkcol[14]) {
						cellValue = jsondata[i][columns[14]];
						cellValue = cellValue || defVal;
						cellValue = cellValue.length > 0 ? cellValue.trim() : cellValue;
						cellVal = cellValue;
						if(!isNaN(cellValue)){
							cellVal = Number(cellValue).toINR('',deno,'INR',deno);
						}
						class_name = i == 1 ? "" : "decimal";
						td += "<td style='text-align:right;width:"+nChkcolsz[14]+"'>"+cellVal+"<input class='"+class_name+"' type='hidden' name='"+columns[14]+"_"+i+"' id='"+columns[14]+"_"+i+"' value='"+cellValue+"' style='text-align:right;border:none;width:100%;' readonly/></td>";
					}
					if (columns[12] == nChkcol[12]) {
						cellValue = jsondata[i][columns[12]]||defVal;
						cellVal = cellValue;
						if(!isNaN(cellValue)){
							cellVal = Number(cellValue).toINR('',deno,'INR',deno);
						}
						
						td += "<td style='text-align:right;width:"+nChkcolsz[12]+"'>"+cellVal+"<input class='decimal' type='hidden' name='"+columns[12]+"_"+i+"' id='"+columns[12]+"_"+i+"' value='"+cellValue+"' style='text-align:right;border:none;width:100%;' readonly/></td>";
					}
					if (columns[13] == nChkcol[13]) {
						cellValue = jsondata[i][columns[13]]||defVal;
						
						cellVal = cellValue;
						if(!isNaN(cellValue)){
							cellVal = Number(cellValue).toINR('',deno,'INR',deno);
						}
						//class_name =  i == 1 ? '' : 'decimal';
						
						td += "<td style='text-align:right;width:"+nChkcolsz[13]+"'>"+cellVal+"<input class='decimal' type='hidden' name='"+columns[13]+"_"+i+"' id='"+columns[13]+"_"+i+"' value='"+cellValue+"' style='text-align:right;border:none;width:100%;' readonly/></td>";
					}
											
				}
	
				td += "</tr>";
				$("table#exceltable").append(td);
			}
			else{
				alert(row.msg);
				$("#exceltbl").html('');
				$("#excelfile").val('');
				$("#exltb").hide();
				break;
			}
		}		
		$("#nrWaitLoader").hide(100);
	}
	
	$("#btnSubmit").click(function(e){
		var aa=$("#fin_year").val();
		if (aa=="-1") {
			alert("Please Select Financial Year");
			$('#nrWaitLoader').hide(100);
			e.preventDefault();
			return false;
		}		
		if($("#exceltbl tr").length > 0 && $("#excelfile").val()){
			$('#nrWaitLoader').css({display:"block"});
			return true;
		}
		else{
			alert("Please upload a valid Excel file!");
			$('#nrWaitLoader').hide(100);
			e.preventDefault();
			return false;
		}
	
	});
 
	function BindTableHeader(data, tableid) {
		var columnSet = [];
		var sscnt = 0;	
		for (var i = 0; i < data.length; i++) {
			var rowHash = data[i];
			sscnt += 1;
			for ( var key in rowHash) {
				if (rowHash.hasOwnProperty(key)) {
					if ($.inArray(key, columnSet) == -1) {
						columnSet.push(key);
					}
				}
			}
		}
		$("#countrow").val(sscnt);
		return columnSet;
	}
	
	function validateData(row) {
		if (row.DFLAG == "E") {
			return {
				isValid : false,
				msg : "A Calculation Error Found in Excel File.\nPlease Amend Allocation Indicated in RED in Excel \nand Try Again"
			}
		}
		return {
			isValid : true
		};
	}
	
	function validateFileData(row) {
		if (row.FR_SUS_NO != ususno) {
			return {
				isValid : false,
				msg : "Incorrect Excel File Selected for Upload.\nCheck the File and Try Again"
			}			
		} return {
			isValid : true
		};
	}
	
</script>