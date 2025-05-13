<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
<link href="js/cue/cueWatermark.css" rel="Stylesheet"></link>

<form:form name="District" id="District" action="DistrictAction" method="post" class="form-horizontal" commandName="DistrictCMD">
	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<h5>ADD/UPDATE DISTRICT</h5>
					<h6 class="enter_by">
						<span style="font-size: 12px; color: red;">(To be entered by MISO)</span>
					</h6>
				</div>
				<div class="card-body card-block">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong> COUNTRY NAME</label>
								</div>
								<div class="col-md-8">
									<select name="country_id" id="country_id" class="form-control-sm form-control" onchange="fn_pre_domicile_Country();">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${country_id}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong> STATE NAME</label>
								</div>
								<div class="col-md-8">
									<select name="state_id" id="state_id" class="form-control-sm form-control">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getMedStateName}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
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
									<label class=" form-control-label"><strong style="color: red;">* </strong> DISTRICT NAME</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="district_name" name="district_name" maxlength="50" class="form-control autocomplete" autocomplete="off" onkeypress="return onlyAlphabets(event);" oninput="this.value = this.value.toUpperCase()" > 
									<input type="hidden" id="id" name="id" class="form-control autocomplete" autocomplete="off">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong>STATUS</label>
								</div>
								<div class="col-md-8">
									<select name="status" id="status" class="form-control">
										<!-- 												<option value="0">--Select--</option> -->
										<c:forEach var="item" items="${getStatusMasterList}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="card-footer" align="center" class="form-control">
					<a href="District" class="btn btn-success btn-sm">Clear</a> 
					<input type="submit" class="btn btn-primary btn-sm" value="Save" onclick="return Validate();">
					<i class="action_icons searchButton"></i><input type="button" class="btn btn-info btn-sm" onclick="Search();" value="Search">
				</div>
			</div>
		</div>
	</div>
	<div align="right" class="container">
		<i class="fa fa-file-excel-o" id="btnExport" style="font-size: x-large; color: green; text-align: right;" aria-hidden="true" title="EXPORT TO EXCEL" onclick="getExcel();"></i>
	</div>
	<br>
	<div class="container" id="District_Search">
		<div class="">
			<div id="divShow" style="display: block;"></div>
			<div class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
				<span id="ip"></span>
				<table id="District_Search" class="table no-margin table-striped  table-hover  table-bordered report_print" width="100%">
					<thead style="color: white; text-align: center;">
						<tr>
							<th style="font-size: 15px; width: 10%;">Ser No</th>
							<th style="font-size: 15px">Country Name</th>
							<th style="font-size: 15px">State Name</th>
							<th style="font-size: 15px">District Name</th>
							<th style="font-size: 15px; width: 20%;">Action</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${list.size()==0}">
							<tr>
								<td style="font-size: 15px; text-align: center; color: red;" colspan="5">Data Not Available</td>
							</tr>
						</c:if>
						<c:forEach var="item" items="${list}" varStatus="num">
							<tr>
								<td style="font-size: 15px; text-align: center; width: 10%;">${num.index+1}</td>
								<td style="font-size: 15px;">${item[0]}</td>
								<td style="font-size: 15px;">${item[1]}</td>
								<td style="font-size: 15px;">${item[2]}</td>
								<td style="font-size: 15px; text-align: center; width: 20%;">${item[3]} ${item[4]}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</form:form>
<c:url value="getSearch_District_Master" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="District_name1">
	<input type="hidden" name="country_id1" id="country_id1" />
	<input type="hidden" name="state_id1" id="state_id1" />
	<input type="hidden" name="District_name1" id="District_name1" />
	<input type="hidden" name="status1" id="status1" value="0" />
</form:form>
<c:url value="Edit_District" var="editUrl" />
<form:form action="${editUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2">
</form:form>
<c:url value="delete_District" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm" name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>
<c:url value="Districtreport" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2" name="search2" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form>
<Script>
	$(document).ready(function() {
		if ('${list.size()}' == "") {
			$("div#District_Search").hide();
		}
		if ('${country_id1}' != "") {
			$("#country_id").val('${country_id1}');
		}
		if ('${state_id1}' != "") {
			$("#state_id").val('${state_id1}');
		}
		if ('${District_name1}' != "") {
			$("#district_name").val('${District_name1}');
		}
		if ('${status1}' != "") {
			$("Select#status").val('${status1}');
		}
	});
	function getExcel() {
		document.getElementById('typeReport1').value = 'excelL';
		document.getElementById('search2').submit();
	}
	function Search() {
		$("#state_id1").val($('#state_id').val());
		$("#country_id1").val($('#country_id').val());
		$("#District_name1").val($('#district_name').val());
		$("#status1").val($('#status').val());
		document.getElementById('searchForm').submit();
	}
	function deleteData(id) {
		$("#id1").val(id);
		document.getElementById('deleteForm').submit();
	}
	function editData(id) {
		$("#id2").val(id);
		$("#updateForm").submit();
	}
	function Validate() {
		if ($("select#country_id").val() == 0) {
			alert("Please Select Country Name");
			$("select#country_id").focus();
			return false;
		}
		if ($("select#state_id").val() == 0) {
			alert("Please Select State Name");
			$("select#state_id").focus();
			return false;
		}
		if ($("input#district_name").val().trim() == "") {
			alert("Please Enter District Name");
			$("input#district_name").focus();
			return false;
		}
		if ($("select#status").val() == "inactive") {
			alert("Only Select Active Status");
			$("select#status").focus();
			return false;
		}
		return true;
	}
	function fn_pre_domicile_Country() {
		var text = $("#country_id option:selected").text();
		var contry_id = $('select#country_id').val();
		$
				.post("getStateListFormcon1?" + key + "=" + value, {
					contry_id : contry_id
				})
				.done(
						function(j) {
							var options = '<option   value="0">' + "--Select--"
									+ '</option>';
							for (var i = 0; i < j.length; i++) {
								options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >'
										+ j[i][1] + '</option>';
							}
							$("select#state_id").html(options);
						}).fail(function(xhr, textStatus, errorThrown) {
				});
	}
</Script>
