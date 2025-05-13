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


<form:form name="med_cat_values" id="med_cat_values"
	action="Edit_med_cat_valuesAction" method="post"
	class="form-horizontal" commandName="Edit_med_cat_valuesCMD">
	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<h5>UPDATE MEDICAL CATEGORY VALUES</h5>
					<h6 class="enter_by">
						<span style="font-size: 12px; color: red;">(To be entered
							by UNIT)</span>
					</h6>
				</div>
				<div class="card-body card-block">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong> SHAPE STATUS</label>
								</div>
								<div class="col-md-8">
									<select name="shape_status" id="shape_status"
										class="form-control-sm form-control">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getShapeStatusList}"
											varStatus="num">
											<option value="${item[1]}" name="${item[1]}">${item[0]}</option>
										</c:forEach>
									</select>
								</div>
								<input type="hidden" id="id" name="id" class="form-control"
									autocomplete="off" value="0">
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong> VALUES </label>
								</div>
								<div class="col-md-8">
									<input type="text" id="values" name="values" oninput="this.value = this.value.toUpperCase()" maxlength="50"
										class="form-control autocomplete" autocomplete="off">
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
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
			</div>
		</div>

		<div class="card-footer" align="center" class="form-control">
			<a href="Med_cat_Url" class="btn btn-danger btn-sm">Cancel</a> <input
				type="submit" class="btn btn-primary btn-sm" value="Update"
				onclick="return Validation();">
		</div>
	</div>

</form:form>

<Script>
$(document).ready(function() {
	
	$("#shape_status").val('${Edit_med_cat_valuesCMD.shape_status}');	
	$("#values").val('${Edit_med_cat_valuesCMD.values}');
	$("#status").val('${Edit_med_cat_valuesCMD.status}');
	$("#id").val('${Edit_med_cat_valuesCMD.id}');
	
});

function Validation() {

	if ($("select#shape_status").val() == "0") {
		alert("Please Select Shape Status");
		$("select#shape_status").focus();
		return false;
	}

	if ($("input#values").val().trim() == "") {
		alert("Please Enter Value");
		$("input#values").focus();
		return false;
	}
	 /*  if ($("select#status").val() == "inactive") {
			alert("Only Select Active Status");
			$("select#status").focus();
			return false;
		} */
	  return true;
}
	  
</script>

