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
<link rel="stylesheet" href="js/assets/adjestable_Col/jquery.resizableColumns.css">
<script src="js/assets/adjestable_Col/jquery.resizableColumns.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/cue/cueWatermark.css">

<form:form name="Actual_str_of_user" id="Actual_str_of_user"
	action="assests_Serviceablity_detailsAction" method="post"
	class="form-horizontal" commandName="">
	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<h5>IT CENSUS REPORT</h5>
					<h6 class="enter_by">Reported On: ${date}</h6>
				</div>
				<div class="card-body card-block">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">Serviceable State</label>
								</div>
								<div class="col-md-8">
									<select name="s_state" id="s_state" class="form-control"
										onchange="unservisable();">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getServiceable_StateList}"
											varStatus="num">
											<option value="${item[1]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
										<option value="4">Discard</option>
									</select>
								</div>
							</div>
						</div>



						<div class="col-md-6" id="un_show" style="display: none;">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">UN-Serviceable State</label>
								</div>
								<div class="col-md-8">
									<select name="unserviceable_state" id="unserviceable_state"
										class="form-control">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${UNServiceableList}"
											varStatus="num"> 
 											<option value="${item[1]}" name="${item[1]}">${item[1]}</option> 
 										</c:forEach> 
									</select>
								</div>
							</div>
						</div>
					</div>
					
					
					<div class="col-md-12">
						
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">Computing Assets Type </label>
								</div>
								<div class="col-md-8">
									<select name="asset_type" id="asset_type"
										class="form-control" >
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${ComputingAssetList}"
											varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
					</div>
					
				</div>

				<div class="card-footer" align="center" class="form-control">
					<a href="Assests_Serviceablity_details_url"
						class="btn btn-success btn-sm">Clear</a> 
						<!-- <i class="fa fa-search"></i> -->
						<input type="button" class="btn btn-info btn-sm" onclick="Search();" value="Search">
					<!-- <i class="fa fa-download"></i> -->
					<input type="button" class="btn btn-primary btn-sm" onclick="downloaddata();" value="Print">
				 <i class="fa fa-file-excel-o" id="btnExport" style="font-size: x-large; color: green; text-align: right;" aria-hidden="true" title="EXPORT TO EXCEL" onclick="getExcel();"></i>
				</div>
			</div>
		</div>
	</div>

	<div id="viewpage" class="container">
		<div class="col-md-12" id="getSearch_Letter" style="display: block;">
			<div class="watermarked" data-watermark="" id="divwatermark">
				<span id="ip"></span>
				<table id="getSearch_Assets_Serviceablity_details"
					class="table no-margin table-striped  table-hover  table-bordered report_print">
					<thead>
						<tr>
							<th>Ser No</th>
							<th>Computing Assets Type</th>
							<th>Make & Model</th>
							<th>Machine No</th>
							<th>MAC Address</th>
							<th>IP Address</th>
							<th>Processor Type</th>
							<th>RAM Capacity</th>
							<th>HDD Capacity</th>
							<th>Operating System</th>
							<th>Office</th>
							<th>Os/Frameware Activation and subsequent patch updation
								Mechanism</th>
							<th>Budget Head</th>
							<th>Serviceable State</th>
							<th>Un-Serviceable State</th>
							<th>Deply Envt as Applicable</th>
							
							  <%-- <c:if test="s_state=2">
								<th>Select To Approve</th>
							</c:if> --%> 
                             
							<%-- <c:if test="${roleType == 'APP'}">
								<th>Select To Approve</th>
							</c:if> --%>
							

						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${list}" varStatus="num">
							<tr>
								<td>${num.index+1}</td>
								<td>${item[0]}</td>
								<td>${item[1]}</td>
								<td>${item[2]}</td>
								<td>${item[3]}</td>
								<td>${item[4]}</td>
								<td>${item[5]}</td>
								<td>${item[6]}</td>
								<td>${item[7]}</td>
								<td>${item[8]}</td>
								<td>${item[9]}</td>
								<td>${item[10]}</td>
								<td>${item[11]}</td>
								<td>${item[12]}</td>
								<td>${item[13]}</td>
								<td>${item[14]}</td>
								
						
								
								<%-- <c:if test="${roleType == 'APP'}">
									<td id="thAction1">${item[13]}${item[14]}</td>
								</c:if> --%>
								<%-- <td>${item[15]}${item[16]}${item[17]}</td> --%>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<div id="viewpage2" class="container">
		<div class="col-md-12" id="getSearch_Letter" style="display: block;">
			<div class="watermarked" data-watermark="" id="divwatermark">
				<span id="ip"></span>
				<table id="getSearch_Assets_Serviceablity_details2"
					class="table no-margin table-striped  table-hover  table-bordered report_print">
					<thead>
						<tr>
							<th style="font-size: 12px; text-align: center;">Total</th>
							<td style="background-color: #f0f3f5; color: black; text-align: center; width: 50%;"><span>${listsize}</span></td>
						</tr>
					</thead>

				</table>
			</div>
		</div>
	</div>
</form:form>

<c:url value="GetSearch_Assests_Serviceable_details" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="command1">
	<input type="hidden" name="s_state1" id="s_state1" value="0">
	<input type="hidden" name="asset_type1" id="asset_type1" value="0">
	<input type="hidden" name="unserviceable_state1" id="unserviceable_state1" value="0">
</form:form>

<c:url value="Download_Computing_Assets_Details" var="dwonloadUrl" />
<form:form action="${dwonloadUrl}" method="post" id="downloadForm" name="downloadForm" modelAttribute="computing_assets_dn">
	<input type="hidden" name="s_state2" id="s_state2" value="0">
	<input type="hidden" name="asset_type2" id="asset_type2" value="0">
	<input type="hidden" name="unserviceable_state2" id="unserviceable_state2" value="0">
</form:form>


<c:url value="Excel_Computing_Assets_Details" var="excelUrl" />
<form:form action="${excelUrl}" method="post" id="ExcelForm" name="ExcelForm" modelAttribute="computing_assets_dn1">
	<input type="hidden" name="s_state3" id="s_state3" value="0">
	<input type="hidden" name="asset_type3" id="asset_type3" value="0">
	<input type="hidden" name="unserviceable_state3" id="unserviceable_state3" value="0">
</form:form>
<Script>
	$(document).ready(function() {
		
		
		if ('${s_state1}' != "") {
			$("select#s_state").val('${s_state1}');
		}
		
		if ('${asset_type1}' != "") {
			$("select#asset_type").val('${asset_type1}');
		}
		
		if ('${unserviceable_state1}' != "") {
			$("select#unserviceable_state").val('${unserviceable_state1}');
		}
		
		
		unservisable();
	});
	function Search() {
		$("#s_state1").val($("#s_state").val());
		$("#unserviceable_state1").val($("#unserviceable_state").val());
		$("#asset_type1").val($("#asset_type").val());
		document.getElementById('searchForm').submit();
	}

	function unservisable() {
		var a = $("select#s_state option:selected").text();

		if (a == "Un-serviceable") {
			$("#un_show").show();

		} else {
			$("#un_show").hide();
			$("#unserviceable_state").val("0");
		}
	}

	function downloaddata() {
		$("#s_state2").val($("#s_state").val());
		$("#unserviceable_state2").val($("#unserviceable_state").val());
		$("#asset_type2").val($("#asset_type").val());
		document.getElementById('downloadForm').submit();
	}
	
	
	
	function getExcel() {	
		$("#s_state3").val($("#s_state").val());
		
		$("#unserviceable_state3").val($("#unserviceable_state").val());
		$("#asset_type3").val($("#asset_type").val());
		
		document.getElementById('ExcelForm').submit();
	} 
</script>
