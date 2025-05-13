<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<link rel="Stylesheet" href="js/Calender/jquery-ui.css">
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js"></script>
<link rel="stylesheet" href="js/common/nrcss.css">
<script>
var username="${username}";
var curDate = "${curDate}";

</script>
<body class="mmsbg">
	<% int ntotln=0; %>
	<div id="nrWaitLoader" style="display: none;" align="center">
		<span id="">Processing Data.Please Wait ...</span>
	</div>

	<form:form id="uploadWithObsn" action="" method="post" enctype="multipart/form-data" class="form-horizontal" commandName="mms_ro_approver_screen_viewCMD">
		<input type="hidden" id="nrSrcSel">
		<input type="hidden" id="selectedid" name="selectedid" class="form-control" />
		<input type="hidden" id="statushid" class="form-control" />

		<div class="nkpageland" id="printableArea">
			<div class="container" align="center" id="headerView" style="display: block;">
				<div class="card">
					<div class="card-header mms-card-header">
						<b>MONTHLY CENSUS RETURN : ${curMonth}</b><br>
						<span class="mms-card-subheader">(To be used by Unit)</span>
					</div>
					<div class="card-body card-block">
						<div class="col-md-12 row form-group">
							<div class="col-md-2" style="text-align: left;">
								<label class=" form-control-label"><strong style="color: red;">* </strong>SUS No</label>
							</div>
							<div class="col-md-2">
								<input type="text" id="sus_no1" name="sus_no1" maxlength="8" class="form-control-sm form-control" placeholder="Search..." autocomplete="off"  title="Type SUS No or Part of SUS No to Search" />
							</div>

							<div class="col-md-2" style="text-align: left;">
								<label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Unit Name</label>
							</div>
							<div class="col-md-6">
								<input type="text" id="unit_name1" name="unit_name1" maxlength="100" class="form-control-sm form-control" placeholder="Search..." autocomplete="off" title="Type Unit Name or Part of Unit Name to Search">
							</div>
							<c:if test="${fmn != null}">
							<div class="col-md-12">
								<label><b>ORBAT : ${fmn}</b></label>
							</div>
							</c:if>
							<c:if test="${location != null}">
							<div class="col-md-12">
								<label><b>LOCATION (NRS) : ${location}</b></label>
							</div>
							</c:if>
						</div>
					</div>
					<div class="card-footer" align="center" style="margin-top: -10px;padding-right:20px;">
						<input type="button" class="btn btn-success btn-sm" onclick="getUeUhList();" value="UE UH Summary" title="Click to get UE UH Summary">
						<input type="button" class="btn btn-success btn-sm" onclick="UMcrList();" value="Get MCR" title="Click to get MCR">						
						<input type="button" class="btn btn-success btn-sm" onclick="UMcrRegnList();" value="Get MCR with Regn No" title="Click to get MCR">
						<input type="button" class="btn btn-success btn-sm" onclick="openUESummary();" value="UE Summary" title="Click to get UE Summary">
					</div>
				</div>
			</div>
			<div id="divGetMcr">
				<div id="tdheaderView" style="display: none;" colspan='13' align="center" class="nrTableHeading">
					<b>MONTHLY CENSUS RETURN : ${curMonth} - <span id="get_mcr_unit_name"></span></b>
					<span style="float: right;">Last Updated : ${update_date[0].update_date} by ${update_date[0].created_by}</span>
				</div>
			</div>
			
			<div class="card" id="re_tb" style="display: none; background: transparent;">
				<div width="100%">
					<div class="nrTableMain2Search" align="left" id="SearchViewtr">
						<span style="float: left;">Last Updated : ${update_date[0].update_date} by ${update_date[0].created_by}</span>
						<label>Search in Result(<span id="ntotln"></span>)
						</label>&nbsp;:&nbsp; <input id="nrInput" type="text" placeholder="Search..." size="35" style="font-weight: normal; font-size: 14px;">
					</div>
				</div>
				<div class="watermarked" data-watermark="" id="divwatermark">
					<span id="ip"></span> <input type="hidden" id="selectedid" name="selectedid"> <input type="hidden" id="nrSrcSel" name="nrSrcSel">
					<div class="nrTableDataDiv">
						<table border="1" class="report_print" width="100%">
							<thead style="text-align: center;">
								<tr>
									<th width="42%;" colspan='3'>PRF Group</th>
									<th colspan='1' width="5%"></th>
									<th colspan='4' width="20%">Holding</th>
									<th colspan='4' width="20%">Reserve</th>
									<!-- <th rowspan='2' width="13%">Remarks</th> -->
								</tr>
								<tr>
									<th width="7%">Material No</th>
									<th width="7%">Census No</th>
									<th width="28%">Nomenclature</th>
									<th width="5%">UH</th>
									<th width="5%">SS</th>
									<th width="5%">LS</th>
									<th width="5%">ACSFP</th>
									<th width="5%">UN Msn</th>
									<th width="5%">WWR</th>
									<th width="5%">TRSS</th>
									<th width="5%">ETSR</th>
									<th width="5%">Others</th>
								</tr>
							</thead>
							<tbody id="nrTable" style="overflow: scroll;" style="height: 300px;">
								<c:if test="${m_1[0][0] == 'NIL'}">
									<tr class='nrSubHeading'>
										<td colspan='12' style='text-align: center;'>Data Not
											Available...</td>
										<% ntotln=0; %>
									</tr>
								</c:if>
								<% String temp = "";%>
								<c:if test="${m_1[0][0] != 'NIL'}">
									<c:forEach var="item" items="${m_1}" varStatus="num">
										<tr id='NRRDO' name='NRRDO'>
											<c:if test="${item[1] != ''}">
												<c:if test="${temp != item[1]}">
													<tr class='nrGroupHeading'>
														<td colspan='3' width="42%;" style='text-align: left;'>PRF Group&nbsp;:&nbsp;${item[1]}</td>
														<%-- <td colspan='1' width="5%" style='text-align: center;'>UE:&nbsp;<fmt:formatNumber value="${item[2]}" maxFractionDigits="2" minFractionDigits="2" /></td> --%>
														<td colspan='1' width="5%" style='text-align: center;'></td>
														<td colspan='4' width="20%"></td>
														<td colspan='4' width="20%"></td>
														<!-- <td width="13%"></td> -->
														<c:set var="temp" value="${item[1]}"></c:set>

													</tr>
												</c:if>
												<tr class='nrTableLineData'>
													<c:if test="${not empty item[4]}">
														<td style='text-align: left;' width="14%"></td>
														<td style='text-align: left;' width="14%">${item[4]}</td>
														<td style='text-align: left;' width="14%">${item[5]}</td>
														<td style='text-align: center;' width="5%">${item[6]}</td>
														<td style='text-align: center;' width="5%">${item[7]}</td>
														<td style='text-align: center;' width="5%">${item[8]}</td>
														<td style='text-align: center;' width="5%">${item[9]}</td>
														<td style='text-align: center;' width="5%">${item[10]}</td>
														<td style='text-align: center;' width="5%">${item[11]}</td>
														<td style='text-align: center;' width="5%">${item[12]}</td>
														<td style='text-align: center;' width="5%">${item[13]}</td>
														<td style='text-align: center;' width="5%">${item[14]}</td>
														<%-- <td style='text-align: center;' width="13%">${item[15]}</td> --%>
													</c:if>
													<%ntotln++;%>
												</tr>
											</c:if>
										</tr>
									</c:forEach>
								</c:if>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="card-footer" id="pId" style="display: none;">
				<input type="button" class="btn btn-success btn-sm" value="Export" id="btn_e" style="background-color: purple;" data-toggle="tooltip" title="Export Data to Excel" onclick="exportData('.nrTableDataDiv');"> 
				<input type="button" class="btn btn-primary btn-sm" value="Print Page" id="btn_p" onclick="printDiv();"> 
				<input type="button" class="btn btn-primary btn-sm" value="View / Print MCR" id="btn_v" onclick="return viewPrint();" title="Click to get Printable format MCR" style="float: right;">
			</div>
			<div id="divUeUh">
				<div id="tdheaderView1" style="display: none;" colspan='13' align="center" class="nrTableHeading">
					<b>UH UH SUMMARY : ${curMonth} - <span id="get_mcr_ueuh_unit_name"></span></b>
					<span style="float: right;">Last Updated : ${update_date[0].update_date} by ${update_date[0].created_by}</span>
				</div>
			</div>
			<div class="card" id="re_ue_uh"
				style="display: none; background: transparent;">
				<div style="width: 100%;" id="SearchViewtr1" class="nrTableMain2Search">
					<span style="float: left;">Last Updated : ${update_date[0].update_date} by ${update_date[0].created_by}</span>
					<label>Search in Result(<span id="ntotln1"></span>)
					</label>&nbsp;:&nbsp; <input id="nrInput1" type="text" placeholder="Search..." size="35" style="font-weight: normal; font-size: 14px;">
				</div>

				<div class="watermarked" data-watermark="" id="divwatermark">
					<span id="ip"></span> <input type="hidden" id="selectedid"
						name="selectedid"> <input type="hidden" id="nrSrcSel"
						name="nrSrcSel">
					<div class="nrTableDataDiv1">
						<table border="1" class="report_print" width="100%">
							<thead style="text-align: center;">
								<tr>
									<th width="3%">Ser No</th>
									<th width="44%">Nomenclature</th>
									<th width="20%">Type of Holding</th>
									<th width="9%">Entitlement</th>
									<th width="9%">Holding</th>
									<th width="5%">Surplus</th>
									<th width="5%">Defi</th>
									<th width="5%">Update Date</th>
								</tr>
							</thead>
							<tbody id="nrTable" style="height: 300px;">
								<c:if test="${a_1[0][0] == 'NIL'}">
									<tr class='nrSubHeading'>
										<td colspan='13' style='text-align: center;'>Data Not
											Available...</td>
										<% ntotln=0; %>
									</tr>
								</c:if>
								<% String temp1 = "";%>
								<c:if test="${a_1 != 'NIL'}">
									<% int i=1; %>
									<c:forEach var="item" items="${a_1}" varStatus="num">
										<tr id='NRRDO' name='NRRDO'>
											<c:if test="${item[0] != ''}">
												<c:if test="${temp1 != item[0]}">
													<tr class="nrGroupHeading">
														<td colspan='8' style='text-align: left;'>PRF
															Group&nbsp;:&nbsp;${item[0]}</td>
														<c:set var="temp1" value="${item[0]}"></c:set>
													</tr>
												</c:if>
												<tr class='nrTableLineData'>
													<td style='text-align: center;' width="3%;"><%=i%></td>
													<td style='text-align: left;' width="44%;">${item[1]}</td>
													<td style='text-align: left;' width="20%;">${item[2]} (${item[3]})</td>
													<td style='text-align: center;' width="9%;"><fmt:formatNumber value="${item[4]}" maxFractionDigits="2" minFractionDigits="2" /></td>
													<td style='text-align: center;' width="9%;"><fmt:formatNumber value="${item[5]}" maxFractionDigits="2" minFractionDigits="2" /></td>
													<td style='text-align: center;' width="5%;">${item[6]}</td>
													<td style='text-align: center;' width="5%;">${item[7]}</td>
													<td style='text-align: center;' width="5%;">${item[8]}</td>
													<% ntotln++; %>
													<% i=i+1; %>
												</tr>
											</c:if>
										</tr>
									</c:forEach>
								</c:if>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			
			<!--  Reg No-->
			<div id="divRegn">
				<div id="tdheaderView2" style="display: none;" colspan='13' align="center" class="nrTableHeading">
					<b>Unit MCR (with Regn No) : ${curMonth} - <span id="get_mcr_regn_unit_name"></span></b>
					<span style="float: right;">Last Updated : ${update_date[0].update_date} by ${update_date[0].created_by}</span>
				</div>
			</div>
			<div class="card" id="re_regn" style="display: none; background: transparent;">
				<div style="width: 100%;" id="SearchViewtr2" class="nrTableMain2Search">
					<span style="float: left;">Last Updated : ${update_date[0].update_date} by ${update_date[0].created_by}</span>
					<label>Search in Result(<span id="ntotln2"></span>)
					</label>&nbsp;:&nbsp; <input id="nrInput2" type="text" placeholder="Search..." size="35" style="font-weight: normal; font-size: 14px;">
				</div>
				<div class="watermarked" data-watermark="" id="divwatermark">
					<span id="ip"></span> <input type="hidden" id="selectedid"
						name="selectedid"> <input type="hidden" id="nrSrcSel"
						name="nrSrcSel">
					<div class="nrTableDataDiv1">
						<table border="1" class="report_print" width="100%">
							<thead style="text-align: center;">
								<tr>
									<th width="3%">Ser No</th>
									<th width="22%">Nomenclature</th>
									<th width="10%">Type of Holding</th>
									<th width="5%">Holding</th>
									<th width="60%">Registration No(s)</th>
								</tr>
							</thead>
							<tbody id="nrTable" style="height: 300px;">
								<c:if test="${m_r[0][0] == 'NIL'}">
									<tr class='nrSubHeading'>
										<td colspan='13' style='text-align: center;'>Data Not
											Available...</td>
										<% ntotln=0; %>
									</tr>
								</c:if>
								<% String temp2 = "";%>
								<c:if test="${m_r != 'NIL'}">
									<% int i=1; %>
									<c:forEach var="item" items="${m_r}" varStatus="num">
										<tr id='NRRDO' name='NRRDO'>
											<c:if test="${item[1] != ''}">
												<c:if test="${temp2 != item[1]}">
													<tr class="nrGroupHeading">
														<td colspan='8' style='text-align: left;'>PRF
															Group&nbsp;:&nbsp;${item[1]}</td>
														<c:set var="temp2" value="${item[1]}"></c:set>
													</tr>
												</c:if>
												<tr class='nrTableLineData'>
													<td style='text-align: center;' width="3%;"><%=i%></td>
													<td style='text-align: left;' width="22%;">${item[4]}
														(${item[3]})</td>
													<td style='text-align: left;' width="10%;">${item[5]} (${item[6]})</td>													
													<td style='text-align: center;' width="5%;">${item[7]}</td>
													<td width="60%;">${item[8]}</td>
													<% ntotln++; %>
													<% i=i+1; %>
												</tr>
											</c:if>
										</tr>
									</c:forEach>
								</c:if>
							</tbody>
						</table>
					</div>
				</div>
			</div>	
			<!--  -->
			<div class="card-footer" id="pId2" style="display: none;">
				<input type="button" class="btn btn-success btn-sm" value="Export" id="btn_e1" style="background-color: purple;" data-toggle="tooltip" title="Export Data to Excel" onclick="exportData('.nrTableDataDiv1');"> 
				<input type="button" class="btn btn-primary btn-sm" value="Print Page" id="btn_p1" onclick="printDiv();"> 
				<input type="button" class="btn btn-primary btn-sm" value="View / Print UE UH Summary" id="btn_v1" onclick="return getUeUhList1();" style="float: right;" title="Click to get Printable format UE UH Summary">
			</div>
			
			<!-- Get Transaction Unit Wise -->
			<div id="getTransctionHeading" style="display:none;">
				<div align="center" class="nrTableHeading">
					<b>Transaction since last Update</b>
				</div>
			</div>
			<div class="card" id="getTransction" style="display:none;background: transparent;">
				<div width="100%">
					<div class="nrTableMain2Search" align="left" id="SearchViewtr">
						<label>Search in Result(${getTransction.size()}) </label>&nbsp;:&nbsp; 
						<input id="trnInput" type="text" placeholder="Search..." size="35" style="font-weight: normal; font-size: 14px;">
					</div>
				</div>
				<div class="watermarked" data-watermark="" id="divwatermark">
					<span id="ip"></span>
					<div id="" class="nrTableMainDiv">
						<input type="hidden" id="selectedid" name="selectedid"> 
						<input type="hidden" id="nrSrcSel" name="nrSrcSel">
						<div class="nrTableDataDiv">
							<table border="1" class="nrTableDataHead report_print" >
								<thead style="text-align: center;">
									<tr>
										<th width="15%">PRF Group</th>
										<th width="10%">Census No</th>
										<th width="20%">Nomenclature</th>
										<th width="10%">Type of Holdings</th>
										<th width="35%">Activity During Month</th>
										<th width="10%">Trn Date</th>
									</tr>
								</thead>
								<tbody id="nrTable">
									<c:if test="${getTransction.size() == 0}">
										<tr>
											<td colspan='6' align='center'>No transaction made</td>
										</tr>
									</c:if>
									<c:forEach var="item" items="${getTransction}" varStatus="num">
										<tr>
											<td width="15%">${item.prf_group}</td>
											<td width="10%"  style="text-align: center;">${item.census_no}</td>
											<td width="20%">${item.nomen}</td>
											<td width="10%"  style="text-align: center;">${item.type_of_hldg_n}</td>
											<td width="35%">${item.actv}</td>
											<td width="10%" style="text-align: center;">${item.tr_date}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
			<div class="card-footer" id="getTransctionBtn" style="display: none;" align="center">
				<input type="button" class="btn btn-success btn-sm" value="Update" style="background-color: Green;font-weight: bold;" title="Certified that MCR for the month of ${curDate} is correct." onclick="unit_update_mcr()"> 
				<br>
				<span style="color: Green;font-weight: bold;">Certified that MCR for the month of <u>${curMonth}</u> is correct.</span><br><br>
				<input type="button" class="btn btn-sm" value="Update with Observation" title="Update with Observation" style="background-color: Red;color: white;font-weight: bold;" onclick="unit_update_mcr_with_obsn()"><br>
				<span style="color: Red;font-weight: bold;">Certified that i have checked MCR for the month of <u>${curMonth}</u>. Detls of obsn/changes reqd are uploaded.</span> 
			</div>
			<div class="card">
				<div class="modal-open modal" id="getUnitObsnDocument" style="overflow-y: auto;">
					<div class="modal-dialog" style="max-width: 800px;">
						<div class="modal-content">
							<div class="modal-body">
								<div id="tab">
									<div class="col-md-12 row form-group" style="padding-bottom: 10px;">
										<div class="col-md-2" style="text-align: left;">
											<label class=" form-control-label">Upload Document</label>
										</div>
										<div class="col-md-4">
											<input type="file" id="unit_upload_document_byte" name="unit_upload_document_byte"  onchange="checkFileExtImage(this)" class="form-control-sm form-control" title="MVCR Upload .ZIP ,.RAR ,.pdf only">
										</div>
										<div class="col-md-2" style="text-align: left;">
											<label class=" form-control-label">Observation</label>
										</div>
										<div class="col-md-4">
											<textarea id="unit_remarks" name="unit_remarks" rows="2" cols="1" maxlength="150" class="form-control-sm form-control" placeholder="max word limit 150 words"></textarea>
										</div>
										<span style="font-size: 12px;color:red">(Only *.rar, *.zip and *.pdf file extensions and file size max 2MB are allowed.)</span>
									</div>
							    </div>
							    <div class="modal-footer" align="center">
							    	<input type="button" class="btn btn-primary" value="Upload Obsn" onclick="unit_update_mcr_with_obsn_submit()">&ensp;
									<button type="button" class="btn btn-danger" onclick="closeModal1();">Close</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- Get Transaction Unit Wise -->
		</div>
	</form:form>

	<c:url value="UnitMList" var="backUrl" />
	<form:form action="${backUrl}" method="post" id="m2_unit1"
		name="m2_unit1" modelAttribute="m2_sus">
		<input type="hidden" name="m1_sus" id="m1_sus" />
		<input type="hidden" name="m1_unit" id="m1_unit" />
	</form:form>

	<c:url value="mms_print_ue_uh_summ_data" var="backUrl" />
	<form:form action="${backUrl}" method="post" id="m2_unit_ueuh"
		name="m2_unit_ueuh" modelAttribute="m2_sus">
		<input type="hidden" name="m2_sus" id="m2_sus" />
		<input type="hidden" name="m2_unit" id="m2_unit" />
	</form:form>
	
	<c:url value="UnitRList" var="backUrl" />
		<form:form action="${backUrl}" method="post" id="mr_unit1" name="mr_unit1" modelAttribute="mr_sus">
		<input type="hidden" name="mr_sus" id="mr_sus" />
		<input type="hidden" name="mr_unit" id="mr_unit" />
	</form:form>

	<!-- New Add for pop-up print -->
	<c:url value="mms_print_unit_mcr" var="printmcrUrl" />
	<form:form action="${printmcrUrl}" method="post" id="printMCRForm"
		name="printMCRForm" modelAttribute="printMCRId" target="result">
		<input type="hidden" name="printMCRId" id="printMCRId" value="0" />
	</form:form>

	<c:url value="mms_print_ue_uh_summ" var="backUrl" />
	<form:form action="${backUrl}" method="post" id="p_ue" name="p_ue"
		modelAttribute="p_sus" target="result">
		<input type="hidden" name="p_sus" id="p_sus" />
		<input type="hidden" name="p_para" id="p_para" />
	</form:form>

	<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
	<script type="text/javascript" src="js/Calender/jquery-ui.js"></script>
	<script type="text/javascript" src="js/common/commonmethod.js"></script>
	<script type="text/javascript" src="js/cue/cueWatermark.js"></script>

	<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
	<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
	<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script>

var newWin;
function openUESummary(){	
	var x = screen.width/2 - 1100/2;
    var y = screen.height/2 - 900/2;
    newWin = window.open("viewstandardEntitlementForTransport_Weap", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');
	window.onfocus = function () { 
		//newWin.close();
	}
}
function closeWindow()
{
	newWin.close();   
}

function checkFileExtImage(file) {
    var ext = file.value.match(/\.([^\.]+)$/)[1];
  	switch (ext.toLowerCase()) {
            case 'pdf':
            case 'rar':
        	case 'zip':
      break;
            default:
              alert('Only *.zip *.rar and *.pdf  file extensions allowed');
             file.value = "";
  	}
}

function unit_update_mcr(){
	var sus_no = $("#sus_no1").val();
	if(sus_no == ""){
		alert("please select SUS No.");
		return false;
	}else{
		jQuery.ajax({
			type : 'POST',
			url : "unit_update_mcr?"+key+"="+value,
			data : {sus_no : sus_no	},
			success : function(data) {
				if(data.length != 0){
					alert(data[0].error);
					return true;
				}
			}
		});
	}
}

function closeModal1(){
	var modal = document.getElementById('getUnitObsnDocument');
	modal.style.display = "none";
}
function unit_update_mcr_with_obsn(){
	var modal = document.getElementById('getUnitObsnDocument');
	modal.style.display ="block";	
}
function unit_update_mcr_with_obsn_submit(){
	var sus_no = $("#sus_no1").val();
	if(sus_no == ""){
		alert("please select SUS No.");
		return false;
	}
	else if(jQuery("#unit_upload_document_byte").get(0).files.length === 0){
		alert("Please Select Document.");
		jQuery("#unit_upload_document_byte").focus();
		return false;
	}else if(jQuery("#unit_remarks").val() == ""){
		alert("Please enter OBSN.");
		jQuery("#unit_remarks").focus();
		return false;
	}else{
		var form = jQuery('#uploadWithObsn')[0];
		var data = new FormData(form);
		jQuery.ajax({
			type : "POST",
			enctype : 'multipart/form-data',
			url : "unit_upload_doc_mcr?"+key+"="+value,
			data : data,
			processData : false,
			contentType : false,
			cache : false,
			timeout : 600000,
			success : function(data) {
				jQuery("#unit_upload_document_byte").val(null);
				alert(data);
				if(data == "Document Uploaded Successfully."){
					jQuery("#unit_remarks").val("");
					var modal = document.getElementById('getUnitObsnDocument');
					modal.style.display = "none";
					return true;
				}
			}
		});
	}
}

function exportData(b){
	$().tbtoExcel(b);
}

$("#sus_no1").keyup(function(){
	var sus_no = this.value;
	var para = "";
	var paravalue="";
	$().Autocomplete2('POST','getMMSRList',sus_no1,{a:sus_no,b:para,c:"SUS",d:paravalue},'getMMSUnitNameBySUSNo',unit_name1);
});

$("#unit_name1").keyup(function(){
	var unit_name = this.value;
	var para = "";
	var paravalue="";
	$().Autocomplete2('POST','getMMSRList',unit_name1,{a:unit_name,b:para,c:"NAME",d:paravalue},'getMMSSUSNoByUnitName',sus_no1);
});


function printDiv() {
	$("span#get_mcr_unit_name").text($("#unit_name1").val());
	$("span#get_mcr_ueuh_unit_name").text($("#unit_name1").val());
	$("span#get_mcr_regn_unit_name").text($("#unit_name1").val());
	$("#SearchViewtr").hide();
	$("#tdheaderView").show();	
	$("#SearchViewtr1").hide();
	$("#tdheaderView1").show();
	$("#SearchViewtr2").hide();
	$("#tdheaderView2").show();
	$("#headerView").hide();
	  $("#btn_e").hide();
	  $("#btn_p").hide();
	  $("#btn_v").hide();
	  $("#btn_e1").hide();
	  $("#btn_p1").hide();
	  $("#btn_v1").hide();
	  
	  $("#getTransctionHeading").hide();
	  $("#getTransction").hide();
	  $("#getTransctionBtn").hide();
	  
	   
	
//	let popupWinindow
	let innerContents = document.getElementById('printableArea').innerHTML;
	 
	popupWindow = window.open('', '_blank', 'width=850,height=500,scrollbars=yes,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
	popupWindow.document.open();
//popupWindow.document.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/cue/printWatermark.css"></head><body onload="window.print();window.close();">' + innerContents + '</html>');
 popupWindow.document.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/cue/printWatermark.css"><style> table td{font-size:10px; font-weight:normal !important;} table th{font-size:12px !important;} tbody th{font-size:10px; font-weight:normal !important;}</style></head><body onload="window.focus(); window.print(); window.close();" oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false">' +innerContents+  '</html>');

 popupWindow.document.close();
$("#SearchViewtr").show();
$("#tdheaderView").hide();
$("#tdheaderView1").hide();
$("#SearchViewtr1").show();
$("#tdheaderView2").hide();
$("#SearchViewtr2").show();

$("#headerView").show();
$("#btn_e").show();
$("#btn_p").show();
$("#btn_v").show();
$("#btn_e1").show();
$("#btn_p1").show();
$("#btn_v1").show();
$("#getTransctionHeading").show();
$("#getTransction").show();
$("#getTransctionBtn").show();
/* document.location.reload(); */

}
function getUeUhList1(){
	
	if($("#sus_no1").val()==""){
		alert("Please Select SUS No / Unit Name...");
		$("#sus_no1").focus();
	    return false;
	} 
	
	if($("#unit_name1").val()==""){
		alert("Please Select SUS No / Unit Name...");
		$("#unit_name1").focus();
	    return false;
	} 
	
	
	var x = screen.width/2 - 1100/2;
    var y = screen.height/2 - 900/2;
    popupWindow = window.open("", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');
	
	
	var sus = $("#sus_no1").val();
	var para = "ALL";
	
	$("#p_sus").val(sus);
	$("#p_para").val(para);
	
	$("#p_ue").submit();
	/*  $().nrURLPopup("mms_print_ue_uh_summ?"+key+"="+value,"UEUH",800,400,"YES");	 */
}

function setid(a,st){
	$("#selectedid").val(a);
	$("#statushid").val(st);
	$("#nrSrcSel").val(a);
	$("#NRRDOO"+a).attr("background-color","yellow");	
}

function UMcrList(){
	if($("#sus_no1").val()==""){
		alert("Please Select SUS No / Unit Name...");
		$("#sus_no1").focus();
	    return false;
	} 
	
	if($("#unit_name1").val()==""){
		alert("Please Select SUS No / Unit Name...");
		$("#unit_name1").focus();
	    return false;
	} 
	
	$("#m1_sus").val($("#sus_no1").val());
	$("#m1_unit").val($("#unit_name1").val());
	$("#nrWaitLoader").show();
	$("#divUeUh").hide();
	$("#re_regn").hide();
	$("#divGetMcr").show();
	$("#m2_unit1").submit();
}

function getUeUhList(){
	if($("#sus_no1").val()==""){
		alert("Please Select SUS No / Unit Name...");
		$("#sus_no1").focus();
	    return false;
	} 
	
	if($("#unit_name1").val()==""){
		alert("Please Select SUS No / Unit Name...");
		$("#unit_name1").focus();
	    return false;
	} 
	
	$("#m2_sus").val($("#sus_no1").val());
	$("#m2_unit").val($("#unit_name1").val());
	$("#nrWaitLoader").show();
	$("#divGetMcr").hide();
	$("#re_regn").hide();
	$("#divUeUh").show();
	$("#m2_unit_ueuh").submit();
}

function UMcrRegnList(){
	if($("#sus_no1").val()==""){
		alert("Please Select SUS No / Unit Name...");
		$("#sus_no1").focus();
	    return false;
	} 
	if($("#unit_name1").val()==""){
		alert("Please Select SUS No / Unit Name...");
		$("#unit_name1").focus();
	    return false;
	} 
	$("#mr_sus").val($("#sus_no1").val());
	$("#mr_unit").val($("#unit_name1").val());
	$("#nrWaitLoader").show();
	$("#divUeUh").hide();
	$("#divGetMcr").hide();
	$("#re_regn").show();
	$("#mr_unit1").submit();
}

function viewPrint(){
	var a = $("input#sus_no1").val();
	
	if(a!= null && a !="" && a!="null"){	
		var statusid = $("#statushid").val();
		popupWindow = window.open("", 'result', 'height=800,width=1200,left=200, top=100,resizable=yes,scrollbars=yes,toolbar=no,status=yes');
		document.getElementById('printMCRId').value=a;
		document.getElementById('printMCRForm').submit();
	}
}

</script>

<script>

$(document).ready(function() {
	$("div#divwatermark").val('').addClass('watermarked');	
	watermarkreport();
	var y1 = '${m_1[0][0]}';
	var y2 = '${a_1[0][0]}';
	var y3 = '${m_r[0][0]}';
	
	$("#divGetMcr").hide();
	$("#divUeUh").hide();
	$("#divRegn").hide();
	$("#re_regn").hide();
	
	if(y1 != "" || '${m_1[0]}'.length > 0){
		$("#re_tb").show();
		$("#re_ue_uh").hide();
		$("#pId").show();
		$("#sus_no1").val('${m_2}');
		$("#unit_name1").val('${m_3}');
		nrSetWaterMark(<%=ntotln+2.5%>);
		$("#ntotln").text(<%=ntotln%>);
		$("#divGetMcr").show();
		$("#divUeUh").hide();
		
		//fro transaction
		$("#getTransctionHeading").show();
		$("#getTransction").show();
		$("#getTransctionBtn").show();
	}
	
	if(y1 == "NIL"){
		$("#pId").hide();
	}
		
	if(y2 != "" || '${a_1[0]}'.length > 0){
		$("#re_tb").hide();
		$("#re_ue_uh").show();
		$("#pId2").show();
		$("#sus_no1").val('${m_2}');
		$("#unit_name1").val('${m_3}');
		nrSetWaterMark(<%=ntotln+2.5%>);
		$("#ntotln1").text(<%=ntotln%>);
		$("#divGetMcr").hide();
		$("#divUeUh").show();
		
		//fro transaction
		$("#getTransctionHeading").show();
		$("#getTransction").show();
		$("#getTransctionBtn").show();
	}
	
	if(y2 == "NIL"){
		$("#pId2").hide();
	}
	
	if(y3 != "" || '${m_r[0]}'.length > 0){
		$("#re_tb").hide();
		$("#re_ue_uh").hide();
		$("#re_regn").show();
		$("#pId2").show();
		$("#sus_no1").val('${m_2}');
		$("#unit_name1").val('${m_3}');
		nrSetWaterMark(<%=ntotln+2.5%>);
		$("#ntotln1").text(<%=ntotln%>);
		$("#divGetMcr").hide();
		$("#divUeUh").hide();
		$("#divRegn").show();
		
		//fro transaction
		$("#getTransctionHeading").show();
		$("#getTransction").show();
		$("#getTransctionBtn").show();
	}
	
	
		
	
	
	$("#nrInput").on("keyup", function() {
		var value = $(this).val().toLowerCase();
		$("#nrTable tr").filter(function() {
			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
  		});
  	});
	
	$("#nrInput1").on("keyup", function() {
		var value = $(this).val().toLowerCase();
		$("#nrTable tr").filter(function() {
			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
  		});
  	});
	
	$("#trnInput").on("keyup", function() {
		var value = $(this).val().toLowerCase();
		$("#getTransction tbody tr").filter(function() {
			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
  		});
  	});
	
	
	////
		
		$("#nrInput2").on("keyup", function() {
			var value = $(this).val().toLowerCase();
			$("#nrTable tr").filter(function() {
				$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
	  		});
	  	});
	////
	
});	


function fnExcelReport()
{
    var tab_text="<table border='2px'><tr bgcolor='#87AFC6'>";
    var textRange; var j=0;
    tab = document.getElementsByClassName('table'); // id of table

    for(j = 0 ; j < tab.length ; j++) 
    {     
        tab_text=tab_text+tab[j].innerHTML+"</tr>";         
    }      

    tab_text=tab_text+"</table>";      
    var ua = window.navigator.userAgent;
    var msie = ua.indexOf("MSIE "); 

    if (msie > 0 || !!navigator.userAgent.match(/Trident.*rv\:11\./))      
    {
        txtArea1.document.open("txt/html","replace");
        txtArea1.document.write(tab_text);
        txtArea1.document.close();
        txtArea1.focus(); 
        sa=txtArea1.document.execCommand("SaveAs",true,"Say Thanks to Sandeep.xls");
    }  
    else                
    	popupWindow = window.open('data:application/vnd.ms-excel,' + encodeURIComponent(tab_text));  
    return (popupWindow);
}
</script>