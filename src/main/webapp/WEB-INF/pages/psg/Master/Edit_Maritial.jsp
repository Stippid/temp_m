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

<form:form name="Edit_Maritial" id="Edit_Maritial" action="Edit_Maritial_Action" method="post" class="form-horizontal" commandName="Edit_MaritialCMD">
	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<h5>UPDATE Marital Status</h5>
					<h6 class="enter_by">
						<span style="font-size: 12px; color: red;">(To be entered by MISO)</span>
					</h6>
				</div>
				<div class="card-body card-block">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong> MARITAL CODE</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="marital_code" name="marital_code" oninput="this.value = this.value.toUpperCase()" maxlength="10" class="form-control autocomplete" autocomplete="off" onkeypress="return onlyAlphabets(event);" />
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong> MARITAL NAME</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="marital_name" name="marital_name" oninput="this.value = this.value.toUpperCase()" maxlength="20" class="form-control autocomplete" Coninput="this.value = this.value.toUpperCase()" maxlength="50"  autocomplete="off" onkeypress="return onlyAlphabets(event);" /> 
									<input type="hidden" id="id" name="id" class="form-control" value="0" autocomplete="off">
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong>STATUS</label>
								</div>
								<div class="col-md-8">
									<select name="status" id="status" class="form-control">
										<!-- 										<option value="0">--Select--</option> -->
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
					<a href="Marital_status" class="btn btn-danger btn-sm">Cancel</a> <input type="submit" class="btn btn-primary btn-sm" value="Update" onclick="return Validate();">
				</div>
			</div>
		</div>
	</div>
</form:form>
<script>
	function Validate() {
		if ($("input#marital_code").val().trim() == "") {
			alert("Please Enter Marital Code ");
			$("input#marital_code").focus();
			return false;
		}
		if ($("input#marital_name").val().trim() == "") {
			alert("Please Enter Marital Name ");
			$("input#marital_name").focus();
			return false;
		}
		/* if ($("select#status").val() == "inactive") {
			alert("Only Select Active Status");
			$("select#status").focus();
			return false;
		}  */
		else {
			var q1 = $("#marital_code").val();
			var q2 = $("#marital_name").val();
			var q3 = $("#id").val();
			if (q3 != "") {
				$("#Edit_Maritial").submit();
			}
		}
		return true;
	}
	$(document).ready(function() {
		$("#marital_code").val('${Edit_MaritialCMD.marital_code}');
		$("#marital_name").val('${Edit_MaritialCMD.marital_name}');
		$("#status").val('${Edit_MaritialCMD.status}');
		$("#id").val('${Edit_MaritialCMD.marital_id}');
	});
</script>
