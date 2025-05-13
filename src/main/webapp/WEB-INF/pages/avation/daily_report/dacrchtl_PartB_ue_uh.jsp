<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js"></script>
<script src="js/cue/printAllPages.js" type="text/javascript"></script>
<div class="animated fadeIn">
	<div class="row">
		<div class="col-md-12" align="right">
			 <button class="btn btn-default btn-xs" onclick="Seen('${sus_no}');"
				style="background-color: #9c27b0; color: white;" title="DACR PART A">DACR
				PART A</button> 
			<button class="btn btn-default btn-xs" onclick="ViewD('${sus_no}');"
				style="background-color: #9c27b0; color: white;" title="DACR PART B">DACR
				PART B</button>
		</div>
	</div>
	<br>
</div>

<div class="animated fadeIn" id="printableArea">
	<div class="row">
		<div class="col-md-12" align="center">
			<div class="card">
				<div class="card-header" align="center">
					<strong style="text-decoration: underline;"> RESTRICTED</strong>
				</div>
				<div class="card-header">
					<table class="col-md-12">
						<tbody style="overflow: hidden;">
							<tr>
								<td align="left"><img
									src="js/miso/images/indianarmy_smrm5aaa.jpg"
									style="height: 50px;"></td>
								<td align="center">
									<h5>DAILY AVIATION SERVICEABILITY STATE : PART B</h5>
								</td>
								<td align="right"><img src="js/miso/images/dgis-logo.png"
									style="max-width: 155px; height: 50px;"></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="card-body">
					<table class="col-md-12">
						<tbody style="overflow: hidden;">
							<tr style="font-size: 14px;">
								<td style="width: 25%; padding-left: 5%;"><label
									class="form-control-label">SUS No </label></td>
								<td style="width: 25%;" align="left">${sus_no}</td>
								<td style="width: 25%; padding-left: 5%;"><label
									class="form-control-label">Unit Name </label></td>
								<td style="width: 25%;" align="left"><label id="unit_name"></label></td>
							</tr>
							<tr style="font-size: 14px;">
								<td style="width: 25%; padding-left: 5%;"><label
									class=" form-control-label">DACR as on</label></td>
								<td style="width: 25%;" align="left"><label
									id="approve_date">${issue_date12}</label></td>
								<td style="width: 25%; padding-left: 5%;"><label
									class=" form-control-label">Date of Approved</label></td>
								<td style="width: 25%;" align="left"><label
									id="modify_date">${ap_date12}</label></td>
							</tr>
							<tr style="font-size: 14px;">
								<td style="width: 25%; padding-left: 5%;"><label
									for="text-input" class=" form-control-label">Status </label></td>
								<td style="width: 25%;" align="left"><label id="status">Approved</label></td>
								<td style="width: 25%; padding-left: 5%;"><label
									class=" form-control-label">WE/PE No </label></td>
								<td style="width: 25%;" align="left"><label id="we_pe_no">${we_pe_no}
								</label></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12" align="center">
			<div class="watermarked" data-watermark="" id="divwatermark">
				<span id="ip"></span>
				<table id="getpartBReportMcr"
					class="table no-margin table-striped  table-hover  table-bordered report_print">
					<thead>
						<tr>
							<th rowspan="2" style="text-align: center; width: 5%;">Ser
								No</th>
								<th rowspan="2" style="text-align: center; width: 10%;">Item Code</th>
							<th rowspan="2" style="text-align: center; width: 10%;">ACT</th>
							<th rowspan="2" style="text-align: center; width: 40%;">Nomenclature</th>
							<th rowspan="2" style="text-align: center; width: 5%;">UE</th>
							<th colspan="7" style="text-align: center; width: 35%;">UH</th>
							<th rowspan="2" style="text-align: center; width: 5%;">TOTAL
								UH</th>
							<th rowspan="2" style="text-align: center; width: 5%;">Surplus</th>
							<th rowspan="2" style="text-align: center; width: 5%;">Defi</th>
						</tr>
						<tr>
							<th style="text-align: center; width: 5%;">S</th>
							<th style="text-align: center; width: 5%;">I</th>
							<th style="text-align: center; width: 5%;">RI</th>
							<th style="text-align: center; width: 5%;">RE</th>
							<th style="text-align: center; width: 5%;">AOG</th>
							<th style="text-align: center; width: 5%;">ACCI</th>
							<th style="text-align: center; width: 5%;">O/H</th>
						</tr>
					</thead>

					<tbody>
						<c:if test="${getdetails_ue_uhchtldacr.size() != 0}">
							<c:forEach items="${getdetails_ue_uhchtldacr}" var="list"
								varStatus="num">
								<tr>
									<td style="text-align: center;; width: 5%;">${num.index+1}</td>
										<td style="text-align: center; width: 10%;">${list[14]}</td>
									<td style="text-align: center; width: 10%;">${list[0]}</td>
									<td style="text-align: left; width: 40%;">${list[13]}</td>
									<td style="text-align: center; width: 5%;">${list[2]}</td>
									<td style="text-align: center; width: 5%;"><a href="#" data-target="#rejectModal" data-toggle="modal" onclick="Reject('${issue_date12}','0')"><b>${list[3]}</b></a></td>
									<td style="text-align: center; width: 5%;"><a href="#" data-target="#rejectModal" data-toggle="modal" onclick="Reject('${issue_date12}','1')"><b>${list[4]}</b></a></td>
									<td style="text-align: center; width: 5%;"><a href="#" data-target="#rejectModal" data-toggle="modal" onclick="Reject('${issue_date12}','2')"><b>${list[5]}</b></a></td>
									<td style="text-align: center; width: 5%;"><a href="#" data-target="#rejectModal" data-toggle="modal" onclick="Reject('${issue_date12}','3')"><b>${list[6]}</b></a></td>
									<td style="text-align: center; width: 5%;"><a href="#" data-target="#rejectModal" data-toggle="modal" onclick="Reject('${issue_date12}','4')"><b>${list[7]}</b></a></td>
									<td style="text-align: center; width: 5%;"><a href="#" data-target="#rejectModal" data-toggle="modal" onclick="Reject('${issue_date12}','5')"><b>${list[8]}</b></a></td>
									<td style="text-align: center; width: 5%;"><a href="#" data-target="#rejectModal" data-toggle="modal" onclick="Reject('${issue_date12}','6')"><b>${list[9]}</b></a></td>
									<td style="text-align: center; width: 5%;"><a href="#" data-target="#rejectModal" data-toggle="modal" onclick="Reject('${issue_date12}','-1')"><b>${list[10]}</b></a></td>
									<td style="text-align: center; width: 5%;">${list[11]}</td>
									<td style="text-align: center; width: 5%;">${list[12]}</td>
								</tr>
							</c:forEach>
						</c:if>
						
						<tr>
							<td colspan="4" style="width: 65%; text-align: center;"><B>TOTAL</B></td>
							<td align="center" style="width: 5%;"><B>${totalUE}</B></td>
							<td align="center" style="width: 5%;"><B>${totalSUH}</B></td>
							<td align="center" style="width: 5%;"><B>${totalIUH}</B></td>
							<td align="center" style="width: 5%;"><B>${totalRIUH}</B></td>
							<td align="center" style="width: 5%;"><B>${totalREUH}</B></td>
							<td align="center" style="width: 5%;"><B>${totalAOGUH}</B></td>
							<td align="center" style="width: 5%;"><B>${totalACCIUH}</B></td>
							<td align="center" style="width: 5%;"><B>${totalOHUH}</B></td>
							<td align="center" style="width: 5%;"><B>${totalUH}</B></td>
							<td align="center" style="width: 5%;"><B></B></td>
							<td align="center" style="width: 5%;"><B></B></td>

						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12" align="center">
			<div class="card">
				<div class="card-body">
					<table class="col-md-12">
						<tbody style="overflow: hidden;">
							<tr>
								<td align="left" style="font-size: 15px"><label>
										Prepared By<br> Station<br> Date
								</label></td>
								<td align="center" style="font-size: 15px"><label>Checked
										By </label></td>
								<td align="center" style="font-size: 15px"><label>Approved
										By </label><br></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>

<div class="col-md-12">
	<div align="center" class="print_btn">
		<input type="button" value="Print"
			onclick="return printDiv('printableArea')"
			style="color: black; padding: 5px 10px 5px 10px; font-size: 14px; font-weight: bold;">
		<!-- <i class="fa fa-download"></i> -->
		<input type="button" value="Back" onclick="goBack()"
			style="color: black; padding: 5px 10px 5px 10px; font-size: 14px; font-weight: bold;">
		<!-- <i class="fa fa-download"></i>class="btn btn-success btn-sm"<input type="button" id="exportId" style="color:black; padding: 5px 10px 5px 10px; font-size: 14px; font-weight: bold;" value="Export" onclick="exportFn();"> -->
	</div>
</div>
<div class="modal fade" id="rejectModal" tabindex="-1" role="dialog" aria-labelledby="rejectModalLabel" aria-hidden="true" data-backdrop="static">
	    <div class="modal-dialog">
	      <div class="modal-content">
	        <div class="modal-header">
	          <h4 class="modal-title">TAIL NUMBER</h4>
	          <button type="button" class="close" data-dismiss="modal">&times;</button>
	        </div>
	             <div class="modal-body">
	        	<div class="form-group">	 
					<div class="col-md-12">			
					<div class="row" style="color: maroon; font-size: 16px; font-weight: bold;">
						<div class="col-sm-12">				 
					  		<input id="rejectid_model" name="rejectid_model" placeholder="" class="form-control" type ="hidden" >
							<table class="table no-margin table-striped  table-hover  table-bordered report_print" id="addQuantity">
				                <thead>
				                  <tr>
				                  	<th>SR. No</th>
				                   	<th>TAIL No</th>
				                   	<th>Nomenclature</th>
				                  </tr>
				                 </thead>
				                 <tbody>
				               </tbody>
				              </table>
							</div>
						</div>
			    	</div>
			    </div>		 
	        	<div class="modal-footer">
	          		<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
	        	</div>
	    	</div>
		</div>
	</div>
</div>

<c:url value="SearchChtlReportDacr" var="backUrl" />
<form:form action="${backUrl}" method="post" id="backForm"
	name="backForm" modelAttribute="sus_noSer">
	<input type="hidden" name="sus_noSer" id="sus_noSer" value="${sus_no}" />
	<input type="hidden" name="unit_nameSer" id="unit_nameSer"
		value="${unit_name}" />
	<input type="hidden" name="statusSer" id="statusSer" value="1" />
	<input type="hidden" name="issue_dateSer" id="issue_dateSer"
		value="${issue_date12}" />
</form:form>
<c:url value="SeenchtlDACRUrl" var="seenUrl" />
<form:form action="${seenUrl}" method="post" id="seenForm"
	name="seenForm" modelAttribute="sus_noM">
	<input type="hidden" name="sus_noM" id="sus_noM" value="0" />
	<input type="hidden" name="unit_nameM" id="unit_nameM" value="" />
	<input type="hidden" name="issue_dateM" id="issue_dateM" value="" />
	<input type="hidden" name="ason_dateM" id="ason_dateM" value="" />
</form:form>
<c:url value="getdetails_ue_uhdacr" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewFormD_B"
	name="viewFormD_B" modelAttribute="sus_nob">
	<input type="hidden" name="sus_nob" id="sus_nob" value="${sus_no}" />
	<input type="hidden" name="unit_nameb" id="unit_nameb"
		value="${unit_name}" />
	<input type="hidden" name="issue_date2" id="issue_date2" value="" />
	<input type="hidden" name="ason_date2" id="ason_date2" value="" />
</form:form>
<script>
	$(document).ready(function() {

		var unit_name = '${unit_name}';
		var sus_no = '${sus_no}';

		$("#sus_no").text(sus_no);
		$("#unit_name").text(unit_name);

		$("div#divwatermark").val('').addClass('watermarked');
		watermarkreport();

	});

	function ParseDateColumn(s, id) {
		var d = new Date(s);
		var date = d.getFullYear() + "-" + ("0" + (d.getMonth() + 1)).slice(-2)
				+ "-" + ("0" + d.getDate()).slice(-2);
		if (date == "NaN-aN-aN")
			date = "";
		else if (date == "1900-01-01")
			date = "";
		$("#" + id).text(date);
	}

	function printDiv(divName) {

		let popupWinindow
		let innerContents = document.getElementById('printableArea').innerHTML;
		popupWinindow = window
				.open(
						'',
						'_blank',
						'width=600,height=700,scrollbars=yes,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
		popupWinindow.document.open();
		popupWinindow.document
				.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/cue/printWatermark.css"><style> table td{font-size:10px; font-weight:normal !important;} table th{font-size:12px !important;} tbody th{font-size:10px; font-weight:normal !important;}</style></head><body onload="window.focus(); window.print(); window.close();" oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false">'
						+ innerContents + '</html>');
		//popupWinindow.document.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/miso/assets/scss/style.css"><link rel="stylesheet" href="js/cue/printWatermark.css"><style>.table_print{height:100% !important;} table td{font-size:12px;} table th{font-size:13px !important;} </style></head><body onload="window.focus(); window.print(); window.close();" oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false">' +innerContents+  '</html>');
		watermarkreport();
		popupWinindow.document.close();
	}
</script>
<script>
function Reject(asonDate,issue_type){
	   var sus =  $("#sus_noSer").val();
	   document.getElementById('rejectid_model').value=sus;
	   var asonDate1 = convertToISO8601(asonDate);
	   generate_tail_no(asonDate1,issue_type);

}
function generate_tail_no(asonDate1,issue_type){
	var sus_no = $("#sus_noSer").val();
	$("table#addQuantity tbody").empty();
	$.post("generate_chtltail_no?"+key+"="+value, {sus_no:sus_no,asonDate:asonDate1,issue_type:issue_type}).done(function(k) {
		var i =1;
		for ( var x = 0; x < k.length; x++) {
 		$("table#addQuantity").append('<tr><td>'+i+'</td>'+'<td>'+k[x]+'</td>'+'<td>'+k[x+1]+'</td>'+'</tr>');
 		x=x+1;
 		i=i+1;
    	}
	}).fail(function(xhr, textStatus, errorThrown) { });
}

function convertToISO8601(dateString) {
    // Split the input date string (dd-mm-yyyy)
    var dateParts = dateString.split('-'); 
    // Reconstruct it as yyyy-mm-dd
    var isoDate = dateParts[2] + '-' + dateParts[1] + '-' + dateParts[0];
    // Add time and timezone information (you can adjust the time zone as needed)
    return isoDate + 'T00:00:00+00:00';  // Assuming time is midnight and UTC time zone
}
</script>

<script>
	function goBack() {
		$("#sus_noSer").val($("#sus_no").val());
		$("#unit_nameSer").val($("#unit_name").val());
		$("#statusSer").val($("#status").val());
		$("#issue_date2").val($("#issue_date").val());
		if ($("#sus_no").val() == "") {
			alert("Please Enter the SUS No.");
		} else {
			document.getElementById('backForm').submit();
		}
	}
	function Seen(sus_no) {
		document.getElementById('sus_noM').value = sus_no;
		document.getElementById('unit_nameM').value = '${unit_name12}';
		document.getElementById('issue_dateM').value = '${issue_date12}';
		document.getElementById('ason_dateM').value = '${ap_date12}';
		document.getElementById('seenForm').submit();
	}
	function ViewD(sus_no) {
		document.getElementById('sus_nob').value = sus_no
		document.getElementById('unit_nameb').value = $("#unit_name").val();
		document.getElementById('issue_date2').value = '${issue_date12}';
		document.getElementById('ason_date2').value = '${ap_date12}';
		document.getElementById('viewFormD_B').submit();
	}
</script>