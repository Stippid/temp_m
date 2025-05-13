<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<!--last changes by Mitesh 05-12-24  -->
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/printAllPages.js" type="text/javascript"></script>
<script src="js/cue/cueWatermark.js"></script>
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
<div class="animated fadeIn">
	<div class="col-md-12" align="center">
		<div class="card">
			<div class="card-header">
				<h5>VIEW DAILY AVIATION CASUALTY RETURN</h5>
			</div>

			<div class="card-body">
				<table class="col-md-12">
					<tbody style="overflow: hidden;">
						<tr style="font-size: 14px;">
							<td style="width: 25%; padding-left: 10%;"><label
								class="form-control-label">SUS No : </label></td>
							<td style="width: 25%;" align="left">${sus_no}</td>
							<td style="width: 20%; padding-left: 5%;"><label
								class="form-control-label">Unit Name : </label></td>
							<td style="width: 30%;" align="left"><label id="unit_name">${unit_name}
							</label></td>
						</tr>
						<tr style="font-size: 14px;">
							<td style="width: 25%; padding-left: 10%;"><label
								class=" form-control-label">DACR Approved dt: </label></td>
							<td style="width: 25%;" align="left"><label>${ap_date12}</label></td>

							<td style="width: 20%; padding-left: 5%;"><label
								class=" form-control-label">Orbat : </label></td>
							<td style="width: 30%;" align="left"><label>${fmn}</label></td>


						</tr>
						<tr style="font-size: 14px;">
							<%-- <td style="width: 25%;padding-left: 10%;"><label class=" form-control-label">Date of Last Return </label></td>
							<td style="width: 25%;" align="left"><label>${modi}</label></td> --%>
							<td style="width: 25%; padding-left: 10%;"><label
								class=" form-control-label">Modification : </label></td>
							<td style="width: 30%;" align="left"><label>${modification}</label></td>
							<td style="width: 20%; padding-left: 5%;"><label
								class=" form-control-label">Location(NRS) : </label></td>
							<td style="width: 30%;" align="left"><label>${loc_nrs}</label></td>
						</tr>
						<tr style="font-size: 14px;">
							<td style="width: 25%; padding-left: 10%;"><label
								for="text-input" class=" form-control-label">Status : </label></td>
							<td style="width: 25%;" align="left"><label>Approved</label></td>
							<td style="width: 20%; padding-left: 5%;"><label
								class=" form-control-label">WE / PE No : </label></td>
							<td style="width: 30%;" align="left"><label>${wep} </label></td>
						</tr>

					</tbody>

				</table>
			</div>
			<div id="printableArea">
			<div class="card-body">
				<div id="divShow" style="display: block;"></div>
				<div class="watermarked" data-watermark="" id="divwatermark"
					style="display: block;">
					<span id="ip"></span>
					<div align="right">
						<h6>Unit Held : ${getDACRReportList.size()}</h6>
					</div>
					<table id="SearchReport"
						class="table no-margin table-striped  table-hover  table-bordered report_print">
						<thead align="center">
							<tr>
								<th rowspan="2" style="width: 4%;">Sr No</th>
								<th rowspan="2" style="width: 6%;">AC No</th>
								<th rowspan="2" style="width: 6%;">Status</th>
								<th rowspan="2" style="width: 8%;">Current Location</th>
								<th colspan="2" style="width: 12%;">Daily Flg Hrs as on
									${issue_date12}</th>
								<th rowspan="2" style="width: 6%;">G/Run</th>
								<th rowspan="2" style="width: 6%;">AF Hrs</th>
								<th rowspan="2" style="width: 6%;">ENG Ser No<br> <span>------</span><br>ENG
									Hrs
								</th>
								<th rowspan="2" style="width: 6%;">HRS Left</th>
								<th rowspan="2" style="width: 6%;">DAYS Left</th>
								<th rowspan="2" style="width: 6%;">Next INSP</th>
								<th colspan="4" style="width: 25%;">Prog Hrs Flown</th>
								<th rowspan="2" style="width: 8%;">Remarks</th>
								<!-- 			<th  rowspan="2" style="width: 6%;">Balance Ah Eng Hrs</th> -->
								<th rowspan="2" style="width: 6%;">DT Last Flown</th>
								<th rowspan="2" style="width: 6%;">PDC</th>

							</tr>
							<tr>
								<th style="width: 6%;">Day</th>
								<th style="width: 6%;">Night</th>
								<th style="width: 7%;">Monthly</th>
								<th style="width: 6%;">Qtrly</th>
								<th style="width: 6%;">H/Yrly</th>
								<th style="width: 6%;">Yrly</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${getDACRReportList}" var="list"
								varStatus="num">
								<tr>
									<td style="width: 4%;">${num.index + 1}</td>
									<td style="width: 6%;">${list[1]}</td>
									<td style="width: 6%;">${list[2]}</td>
									<td style="width: 8%;">${list[20]}</td>
									<td style="width: 6%;">${list[3]}</td>
									<td style="width: 6%;">${list[4]}</td>
									<td style="width: 6%;">${list[5]}</td>
									<td style="width: 6%;">${list[6]}</td>
									<td style="width: 6%;">${list[18]}<br> <span>------</span><br>${list[7]}</td>
									<td style="width: 6%;">${list[9]}</td>
									<td style="width: 6%;">${list[8]}</td>
									<td style="width: 6%;">${list[10]}</td>
									<td style="width: 6%;">${list[11]}</td>
									<td style="width: 7%;">${list[12]}</td>
									<td style="width: 6%;">${list[13]}</td>
									<td style="width: 6%;">${list[14]}</td>
									<td style="width: 8%;">${list[15]}</td>
									<%-- <td>${list[16]}</td> --%>
									<td style="width: 6%;">${list[17]}</td>
									<td style="width: 6%;">${list[19]}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<table
						class="table no-margin table-striped  table-hover  table-bordered report_print"
						style="margin-bottom: 0rem;">
						<tbody>
							<tr>
								<td colspan="4" style="width: 20.5%; text-align: center;"><B>Total</B></td>
								<td align="left" style="width: 5%; text-align: center;">${sumDay}</td>
								<td align="left" style="width: 5%; text-align: center;">${sumNight}</td>
								<td align="left" style="width: 31%; text-align: center;"><B></B></td>
								<td align="left" style="width: 6%; text-align: center;">${sumM}</td>
								<td align="left" style="width: 5%; text-align: center;">${sumQ}</td>
								<td align="left" style="width: 5%; text-align: center;">${sumHY}</td>
								<td align="left" style="width: 5%; text-align: center;">${sumY}</td>
								<td align="left" style="text-align: center;"><B></B></td>
							</tr>
						</tbody>
					</table>
					<table
						class="table no-margin table-striped  table-hover  table-bordered report_print"
						style="margin-bottom: 0rem;">
						<tbody>
							<tr>
								<td colspan="4" style="width: 20.5%; text-align: center;"></td>
								<td align="left" style="width: 10%; text-align: center;">${sumDN}</td>
								<td align="left" style="text-align: center;"><B></B></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div>
				<div class="col-md-12" align="center">
				
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


<c:url value="SearchRpasReportDacr" var="backUrl" />
<form:form action="${backUrl}" method="post" id="backForm"
	name="backForm" modelAttribute="sus_noSer">
	<input type="hidden" name="sus_noSer" id="sus_noSer" value="0" />
	<input type="hidden" name="unit_nameSer" id="unit_nameSer" value="0" />
	<input type="hidden" name="statusSer" id="statusSer" value="0" />
	<input type="hidden" name="issue_dateSer" id="issue_dateSer" value="0" />
</form:form>
<c:url value="SeenrpasDACRUrl" var="seenUrl" />
<form:form action="${seenUrl}" method="post" id="seenForm"
	name="seenForm" modelAttribute="sus_noM">
	<input type="hidden" name="sus_noM" id="sus_noM" value="0" />
	<input type="hidden" name="unit_nameM" id="unit_nameM" value="" />
	<input type="hidden" name="issue_dateM" id="issue_dateM" value="" />
	<input type="hidden" name="ason_dateM" id="ason_dateM" value="" />

</form:form>
<c:url value="getdetails_ue_uhrpas" var="viewUrl" />
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
		$("div#divwatermark").val('').addClass('watermarked');
		watermarkreport();

		document.addEventListener('dragenter', function(event) {
			event.preventDefault();
		});

		document.addEventListener('dragover', function(event) {
			event.preventDefault();
		});

		document.addEventListener('drop', function(event) {
			event.preventDefault();
		});
	});

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

	function printDiv(divName) {

		$("div#divShow").empty();
		$("div#divShow").show();
		printDivOptimize(
				divName,
				'DAILY AVIATION SERVICEABILITY STATE FOR ${unit_name} AS ON: ${ap_date}',
				"", "", "");
		$("div#divShow").hide();
	}
</script>
<script>
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