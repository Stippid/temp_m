<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<link href="js/cue/cueWatermark.css" rel="Stylesheet"></link>

<form:form name="qualification" id="qualification"
	action="qualificationAction" method="post" class="form-horizontal"
	commandName="qualificationCMD">
	<div class="container" align="center">
		<div class="card">
			<div class="card-header">
				<h5>ADD QUALIFICATION</h5>
				<h6 class="enter_by">
					<span style="font-size: 12px; color: red;">(To be entered by
						MISO)</span>
				</h6>
			</div>
			<div class="card-body card-block">
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"><strong
									style="color: red;">* </strong>QUALIFICATION TYPE</label>
							</div>
							<div class="col-md-8">
								<select name="qualification_type" id="qualification_type"
									class="form-control" onchange="fn_qualification_typeChange()">
									<option value="0">--Select--</option>
									<c:forEach var="item" items="${getQualificationTypeList}"
										varStatus="num">
										<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
									</c:forEach>
								</select> <input type="hidden" id="qualification_ch_id"
									name="qualification_ch_id" value="0"
									class="form-control autocomplete" autocomplete="off">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"><strong
									style="color: red;">* </strong>STATUS</label>
							</div>
							<div class="col-md-8">
								<select name="status" id="status" class="form-control">
									<!-- 												<option value="0">--Select--</option> -->
									<c:forEach var="item" items="${getStatusMasterList}"
										varStatus="num">
										<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12">
					<div class="col-md-6" id="specializationDiv">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"><strong
									style="color: red;">* </strong>EXAMINATION PASSED</label>
							</div>
							<div class="col-md-8">
								<select name="examination_passed" id="examination_passed" class="form-control">
									<option value="0">--Select--</option>
								</select>
							</div>
						</div>
					</div>
					<div class="col-md-6" id="courseNamediv">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"><strong
									style="color: red;">* </strong>DEGREE ACQUIRED</label>
							</div>
							<div class="col-md-8">
								<select name="degree" id="degree" class=" form-control">
									<option value="0">--Select--</option>
									<c:forEach var="item" items="${getDegreeList}" varStatus="num">
										<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
				</div>
				<input type="hidden" id="qualification_hid" name="qualification_hid"
					class="form-control" autocomplete="off" />
			</div>
			<div class="card-footer" align="center" class="form-control">
				<a href="Qualification" class="btn btn-success btn-sm">Clear</a> 
				<input type="submit" class="btn btn-primary btn-sm" value="Save" onclick="return validate();"> 
				<i class="action_icons searchButton"></i><input type="button" class="btn btn-info btn-sm" onclick="Search();" value="Search">
			</div>
		</div>
	</div>
	<div align="right" class="container">
		<i class="fa fa-file-excel-o" id="btnExport"
			style="font-size: x-large; color: green; text-align: right;"
			aria-hidden="true" title="EXPORT TO EXCEL" onclick="getExcel();"></i>
	</div>
	<br>
	<div class="container" id="getquali" style="display: block;">
		<div class="watermarked" data-watermark="" id="divwatermark">
			<span id="ip"></span>
			<table id="getquali"
				class="table no-margin table-striped  table-hover  table-bordered report_print">
				<thead>
					<tr>
						<th style="text-align: center; width: 10%;">Ser No</th>
						<th style="text-align: center;">Qualification Type</th>
						<th style="text-align: center;">Examination Passed</th>
						<th style="text-align: center;">Degree Acquired</th>
						<!--  <th style="text-align: center; ">Status</th>		 -->
						<th style="text-align: center; width: 20%;">Action</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${list.size()==0}">
						<tr>
							<td style="font-size: 15px; text-align: center; color: red;"
								colspan="6">Data Not Available</td>
						</tr>
					</c:if>
					<c:forEach var="item" items="${list}" varStatus="num">
						<tr>
							<td style="font-size: 15px; text-align: center; width: 10%;">${num.index+1}</td>
							<td style="font-size: 15px;">${item[0]}</td>
							<td style="font-size: 15px;">${item[1]}</td>
							<td style="font-size: 15px;">${item[2]}</td>
							<%-- <td style="font-size: 15px;">${item[3]}</td>		 --%>
							<td style="font-size: 15px; width: 20%; text-align: center;">${item[4]}
								${item[5]}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</form:form>
<c:url value="GetSearch_Qualification" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="hidqualification_type">
	<input type="hidden" name="hidqualification_type"
		id="hidqualification_type" value="0" />
	<input type="hidden" name="hidexamination_passed"
		id="hidexamination_passed" value="0" />
	<input type="hidden" name="hiddegree" id="hiddegree" value="0" />
	<input type="hidden" name="status1" id="status1" value="0" />
</form:form>
<c:url value="Delete_Qualification" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>
<c:url value="Edit_qualification" var="editUrl" />
<form:form action="${editUrl}" method="post" id="updateForm"
	name="updateForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2" value="0">
</form:form>
<c:url value="Qualificationreport" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2"
	name="search2" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form>
<script>
	$(document).ready(function() {
		$.ajaxSetup({
			async : false
		});
		if ('${list.size()}' == "") {
			$("div#getquali").hide();
		}
		if ('${status1}' != "0" && '${status1}' != "") {
			$("Select#status").val('${status1}');
		}
		if ('${qualification_type}' != "0" && '${qualification_type}' != "") {
			$("Select#qualification_type").val('${qualification_type}');
			fn_qualification_typeChange();
		}
		if ('${degree}' != "0" && '${degree}' != "") {
			$("Select#degree").val('${degree}');
		}
		if ('${examination_passed}' != "0" && '${examination_passed}' != "") {
			$("Select#examination_passed").val('${examination_passed}');
		}
	});
	function getExcel() {
		document.getElementById('typeReport1').value = 'excelL';
		document.getElementById('search2').submit();
	}
	function Search() {
		$("#hidqualification_type").val($("#qualification_type").val());
		$("#hidexamination_passed").val($("#examination_passed").val());
		$("#hiddegree").val($("#degree").val());
		$("#status1").val($("#status").val());
		document.getElementById('searchForm').submit();
	}
	function deleteData(id) {
		$("#id1").val(id);
		document.getElementById('deleteForm').submit();
	}
	function editData(id) {
		$("#id2").val(id);
		document.getElementById('updateForm').submit();
	}
	function validate() {
		if ($('#qualification_type').val() == '0') {
			alert("Please Select Qualification Type ");
			return false;
		}
		if ($("select#status").val() == "0") {
			alert("Please Select Status");
			$("select#status").focus();
			return false;
		}
		if ($("select#examination_passed").val() == "0") {
			alert("Please Select Examination Passed");
			$("select#examination_passed").focus();
			return false;
		}
		if ($("select#degree").val() == "0") {
			alert("Please Select Degree");
			$("select#degree").focus();
			return false;
		}
		if ($("select#status").val() == "inactive") {
			alert("Only Select Active Status");
			$("select#status").focus();
			return false;
		}
		return true;
	}
	function fn_qualification_typeChange() {
		var q_type = $("#qualification_type").val()
		var options = '<option value="0" >--Select--</option>';
		if (q_type != '0') {
			$
					.post(
							'GetExaminatonPassed?' + key + "=" + value,
							{
								q_type : q_type
							},
							function(j) {
								if (j.length > 0) {
									for (var i = 0; i < j.length; i++) {
										options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >'
												+ j[i][1] + '</option>';
									}
									$("select#examination_passed")
											.html(options);
								}
							});
		} else {
			$("select#examination_passed").html(options);
		}
	}
</script>
