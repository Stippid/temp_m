<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>

<form:form name="Edit_Course_type" id="Edit_Course_type"
	action="Edit_Course_typeAction" method="post" class="form-horizontal"
	commandName="Edit_Course_typeCMD">
	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<h5>UPDATE ARMY COURSE</h5>
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
										style="color: red;">* </strong>CATEGORY</label>
								</div>
								<div class="col-md-8">
									<select id="rank_type" name="rank_type"
										class="form-control autocomplete" >
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getServiceCategoryList}"
											varStatus="num">
											<option value="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong> COURSE NAME</label>
								</div>
								<div class="col-md-8">
									<input type="hidden" id="id" name="id" class="form-control"
										value="0" autocomplete="off"> <input type="text"
										id="course_name" name="course_name"
										maxlength="100" class="form-control autocomplete"
										autocomplete="off" onkeyup="return specialcharecter(this)">
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>COURSE NAME ABBRIVIATION</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="course_gp" name="course_gp"
										 maxlength="50"
										class="form-control autocomplete" autocomplete="off"
										onkeyup="return specialcharecter(this)">
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
				</div>

				<div class="card-footer" align="center" class="form-control">
					<a href="Course_Type_and_Course" class="btn btn-danger btn-sm">Cancel</a>
					<input type="submit" class="btn btn-primary btn-sm" value="Update"
						onclick="return validate();">
				</div>
			</div>
		</div>
	</div>

</form:form>

<script>

$(document).ready(function() {
	var r = '${Edit_Course_typeCMD.rank_type}';
	$("#rank_type").val(r);
	$("#course_name").val('${Edit_Course_typeCMD.course_name}');
	$("#course_gp").val('${Edit_Course_typeCMD.course_gp}');
	$("#id").val('${Edit_Course_typeCMD.id}');
	$("#status").val('${Edit_Course_typeCMD.status}');
});

function validate() {

	if ($("select#rank_type").val() == 0
			|| $("select#rank_type").val() == "0") {
		alert("Please Select Category")
		$("input#rank_type").focus();
		return false;
	}
	if ($("input#course_name").val().trim() == "") {
		alert("Please Enter Course Name")
		$("input#course_name").focus();
		return false;
	}
	if ($("input#course_gp").val().trim() == "") {
		alert("Please Enter Course Name Abbreviation")
		$("input#bank_phone").focus();
		return false;
	}

	/* if ($("select#status").val() == "inactive") {
		alert("Only Select Active Status");
		$("select#status").focus();
		return false;
	} */
	return true;
}
</script>

