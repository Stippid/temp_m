
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

<form:form name="Edit_Medal_Type" id="Edit_Medal_Type" action="Edit_Medal_TypeAction" method="post" class="form-horizontal" commandName="Edit_Medal_TypeCMD">
	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<h5>UPDATE MEDAL</h5>
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
									<label class=" form-control-label"><strong style="color: red;">* </strong>MEDAL TYPE </label>
								</div>
								<div class="col-md-8">
									<select name="medal_type" id="medal_type"
										class="form-control-sm form-control">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getMedalTypeList1}"
											varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong> MEDAL NAME </label>
								</div>
								<div class="col-md-8">
									<input type="text" id="medal_name" name="medal_name" oninput="this.value = this.value.toUpperCase()" maxlength="50" class="form-control autocomplete" autocomplete="off" onkeyup="return specialcharecter(this)">
									 <input type="hidden" id="id" name="id" class="form-control autocomplete" autocomplete="off" > 
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong> MEDAL ABBREVIATION </label>
								</div>
								<div class="col-md-8">
									<input type="text" id="medal_abberivation" oninput="this.value = this.value.toUpperCase()" maxlength="50"
										name="medal_abberivation" class="form-control autocomplete"
										autocomplete="off" onkeyup="return specialcharecter(this)">
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
					<a href="Medal_TypeUrl" class="btn btn-danger btn-sm">Cancel</a> <input
						type="submit" class="btn btn-primary btn-sm" value="Update"
						onclick="return Validation();">
				</div>
			</div>
		</div>
	</div>
</form:form>

<script>
$(document).ready(function() {
	$("#medal_type").val('${Edit_Medal_TypeCMD.medal_type}');	
	$("#medal_name").val('${Edit_Medal_TypeCMD.medal_name}');	
	$("#medal_abberivation").val('${Edit_Medal_TypeCMD.medal_abberivation}');
	$("#id").val('${Edit_Medal_TypeCMD.id}');
	$("#status").val('${Edit_Medal_TypeCMD.status}');
	
});


function Validation(){
	
	  if ($("Select#medal_type").val() == 0) {
			alert("Please Select Medal Type");
			$("Select#medal_type").focus();
			return false;
		}  
	  if ($("input#medal_name").val().trim() == "") {
			alert("Please Enter Medal Name");
			$("input#medal_name").focus();
			return false;
		}  
	  if ($("input#medal_abberivation").val().trim() == "") {
			alert("Please Enter Medal Abbreviation");
			$("input#medal_abberivation").focus();
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




