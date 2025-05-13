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

<form:form name="editqualification" id="editqualification"
	action="editqualificationAction" method="post" class="form-horizontal"
	commandName="editqualificationCMD">
	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<h5>Update Qualification</h5>
					<h6 class="enter_by">
						<span style="font-size: 12px; color: red;">(To be entered
							by MISO)</span>
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
									<form:select path="qualification_type" id="qualification_type"
										class="form-control" onchange="fn_qualification_typeChange()">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getQualificationTypeList}"
											varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</form:select>
									<form:hidden id="id" path="id" value="0"
										class="form-control autocomplete" autocomplete="off" />
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
									<form:select path="status" id="status" class="form-control">
										<!-- 												<option value="0">--Select--</option> -->
										<c:forEach var="item" items="${getStatusMasterList}"
											varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</form:select>
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
									<form:select path="examination_passed" id="examination_passed"
										class="form-control">
										<option value="0">--Select--</option>
									</form:select>
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
									<form:select path="degree" id="degree" class=" form-control">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getDegreeList}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</form:select>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="card-footer" align="center" class="form-control">
					<a href="Qualification" class="btn btn-danger btn-sm">Cancel</a> <input
						type="submit" class="btn btn-primary btn-sm" value="Update"
						onclick="return validate();">
				</div>
			</div>
		</div>
	</div>
</form:form>
<script>
	$(document).ready(
			function() {
				$.ajaxSetup({
					async : false
				});
				$("#id").val('${editqualificationCMD.id}');
				$("#qualification_type").val(
						'${editqualificationCMD.qualification_type}');
				fn_qualification_typeChange();
				$("#examination_passed").val(
						'${editqualificationCMD.examination_passed}');
				$("#status").val('${editqualificationCMD.status}');
				$("#degree").val('${editqualificationCMD.degree}');
			});
	function validate() {
		if ($('#qualification_type').val() == '0') {
			alert("Please Select Qualification Type");
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
		/* if ($("select#status").val() == "inactive") {
			alert("Only Select Active Status");
			$("select#status").focus();
			return false;
		} */
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
