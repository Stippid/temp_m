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

<form:form name="upload_psg_doc" id="upload_psg_doc"
	action="upload_psg_docAction?${_csrf.parameterName}=${_csrf.token}"
	method='POST' commandName="upload_psg_docCMD"
	enctype="multipart/form-data">
	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<h5>UPLOAD PSG DOCUMENT</h5>
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
										style="color: red;">* </strong>SUS No</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="sus_no" name="sus_no" placeholder=""
										class="form-control autocomplete" autocomplete="off"
										maxlength="8">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">Unit Name </label>
								</div>
								<div class="col-md-8">
									<input type="text" id="unit_name" name="unit_name"
										maxlength="100" placeholder=""
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
										style="color: red;">* </strong>Date</label>
								</div>
								<div class="col-md-8">
									<input type="month" id="date" name="date" class="form-control"
										autocomplete="off" min="1899-01-01" max="${date}">
								</div>
							</div>
						</div>		
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">OFFRS DO-II
										[IAFF-3010]</label>
								</div>
								<div class="col-md-8">
									<input type="file" id="up_offrs_do_2_iaff_30101"
										name="up_offrs_do_2_iaff_30101">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">STR Return
										[IAFF-3008]</label>
								</div>
								<div class="col-md-8">
									<input type="file" id="up_str_return_iaff_30081"
										name="up_str_return_iaff_30081">
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">OFFRS ARRIVAL REPORT</label>
								</div>
								<div class="col-md-8">
									<input type="file" id="up_offrs__arrival_rp1"
										name="up_offrs__arrival_rp1">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">JCO/OR DO-II
										[IAFF-3010]</label>
								</div>
								<div class="col-md-8">
									<input type="file" id="up_jco_do_21" name="up_jco_do_21">
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">STR Return
										[IAFF-3009]</label>
								</div>
								<div class="col-md-8">
									<input type="file" id="up_str_return_iaff_3009_a_b1"
										name="up_str_return_iaff_3009_a_b1">
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="card-footer" align="center" class="form-control">
					<a href="psgUploadurl" type="reset" class="btn btn-success btn-sm">
						Clear </a> <input type="submit" class="btn btn-primary btn-sm"
						value="Save" onclick="validate();">
				</div>
			</div>
		</div>
	</div>
</form:form>
<script>
jQuery(document).ready(function() {
	if('${roleAccess}' == "Unit"){
		$("#sus_no").attr('readonly','readonly');
		$("#unit_name").attr('readonly','readonly');
		if('${sus_no}'!= ""){
			$("#sus_no").val('${sus_no}');
		}
		if('${unit_name}'!= ""){
			$("#unit_name").val('${unit_name}');
		}
	}
});
</script>
<script>
	function validate() {			
		if($("#sus_no").val() == ""){
			  alert("Please Enter SUS No.");
			  return false;
		} 
		if($("#date").val() == ""){
			  alert("Please Select Date.");
			  return false;
		} 
		return true;
	}
</script>