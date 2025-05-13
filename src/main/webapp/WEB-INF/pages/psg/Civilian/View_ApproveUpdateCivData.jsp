<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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

<!-- <link rel="stylesheet" href="js/assets/css/bootstrap.min.css"> -->
<link rel="stylesheet" href="js/layout/css/animation.css">
<!-- <link rel="stylesheet" href="js/assets/scss/style.css">  -->
<link rel="stylesheet" href="js/assets/collapse/collapse.css">
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>


<!-- resizable_col -->
<link rel="stylesheet"
	href="js/assets/adjestable_Col/jquery.resizableColumns.css">
<script src="js/assets/adjestable_Col/jquery.resizableColumns.js"
	type="text/javascript"></script>

<style>
label {
	word-break: break-word;
}

.go-top {
	right: 1em;
	bottom: 2em;
	color: #FAFAFA;
	text-decoration: none;
	background: #F44336;
	padding: 5px;
	border-radius: 5px;
	border: 1px solid #e0e0e0;
	position: fixed;
	font-size: 180%;
}
</style>
<script>

$(window).scroll(function(){
    if($(this).scrollTop() > 100){
      $('.go-top').fadeIn(200);      
    } else {
      $('.go-top').fadeOut(300);
    }
  });
  $('.go-top').click(function() {
    event.preventDefault();
    
    $('html , body').animate({scrollTop: 0}, 1000);
  });
  </script>

<script>
function Cancel(){
  	
	$("#personnel_no1").val($("#personnel_noV").val()) ;
	$("#status1").val($("#statusV").val()) ;
	$("#rank1").val($("#rankV").val()) ;
	$("#comm_id").val($("#comm_idV").val()) ;
	$("#unit_name1").val($("#unit_nameV").val()) ;
	$("#unit_sus_no1").val($("#unit_sus_noV").val()) ;
	 
	document.getElementById('searchForm').submit();
}
</script>

<c:url value="GetSearch_UpdateOfficerData" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="personnel_no1">
	<input type="hidden" name="personnel_no1" id="personnel_no1" value="0" />
	<input type="hidden" name="rank1" id="rank1" value="0" />
	<input type="hidden" name="status1" id="status1" value="0" />
	<input type="hidden" name="unit_name1" id="unit_name1" />
	<input type="hidden" name="unit_sus_no1" id="unit_sus_no1" />
	<input type="hidden" name="cr_by1" id="cr_by1"   />
	<input type="hidden" name="cr_date1" id="cr_date1"   />
</form:form>

<form id="Personnel_no_form">
	<div class="animated fadeIn">
		<div class="" align="center">
			<div class="card">
				<div class="panel-group" id="accordion">
					<div class="panel panel-default" id="insp_div1">
						<div class="panel-heading">
							<div class="panel-heading">
								<h4 class="panel-title main_title" id="insp_div5">
									<b>APPROVED DATA OF UPDATED REGULAR EMPLOYEE</b>
								</h4>
							</div>
							<div class="col-md-12"
								style="padding-top: 20px; padding-left: 250px;">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong>Employee No</label>
										</div>
										<div class="col-md-8">
											<input type="text" id="personnel_no" name="personnel_no"
												class="form-control autocomplete" autocomplete="off">
										</div>

										<!-- janki -->
										<input type="hidden" id="personnel_noV" name="personnel_noV"
											class="form-control autocomplete"> <input
											type="hidden" id="statusV" name="statusV"
											class="form-control autocomplete"> <input
											type="hidden" id="rankV" name="rankV"
											class="form-control autocomplete"> <input
											type=hidden id="comm_idV" name="comm_idV"
											class="form-control autocomplete"> <input
											type="hidden" id="unit_nameV" name="unit_nameV"
											class="form-control autocomplete"> <input
											type="hidden" id="unit_sus_noV" name="unit_sus_noV"
											class="form-control autocomplete">
											<input
											type=hidden id="comm_id" name="comm_id"
											class="form-control autocomplete">

									</div>
								</div>

								<!-- <div class="col-md-6" id="process_btn">
									     <input type="button" class="btn btn-success btn-sm" id="btn1" value="Process" onclick="return personal_number();">
									</div> -->
								<div class="card-footer" align="right" class="form-control"
									id="back_bt_div">
									<!--  style="display: none;"-->
									<!-- <a href="Search_UpdateOfficerDataUrl" class="btn btn-primary btn-sm" >Back</a>  -->
									<input type="button" class="btn btn-info btn-sm"
										onclick="Cancel();" value="Back">
								</div>
							</div>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</form>

<div id="maindetailsdiv">
	<div class="card">
		<div class="panel-group" id="accordion4">
			<div class="panel panel-default" id="app_div1">
				<div class="panel-heading">
					<h4 class="panel-title main_title red" id="a_div5">
						<a class="droparrow collapsed" data-toggle="collapse"
							data-parent="#accordion4" href="#" id="app_final"
							onclick="divN(this)"><b>RECORD</b></a>
					</h4>
				</div>
				<div id="collapse1app" class="panel-collapse collapse">
					<div class="card-body card-block">
						<br>
						<div class="row">

							<div class="col-md-12">
								<div class="col-md-3">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;"></strong> Name</label>
										</div>
										<div class="col-md-8">
											<label class=" form-control-label" id="cadet_name"></label> <label
												class=" form-control-label" id="dob" style="display: none;"></label>
											<label class=" form-control-label" id="marital_status_p"
												style="display: none;"></label>
										</div>
									</div>
								</div>
								
								
							</div>
							<div class="col-md-12">

								<div class="col-md-3">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"> Date of TOS</label>
										</div>
										<div class="col-md-8">
											<label class=" form-control-label" id="p_tos"></label>
										</div>
									</div>
								</div>
								<div class="col-md-3">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;"></strong> SUS No</label>
										</div>
										<div class="col-md-8">
											<label class=" form-control-label" id="app_sus_no"></label>
										</div>
									</div>
								</div>
								<div class="col-md-3">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"> Unit Name</label>
										</div>
										<div class="col-md-8">
											<label class=" form-control-label" id="app_unit_name"></label>
										</div>
									</div>
								</div>
							</div>

							
							

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>



<!-- START MARITAL EVENTS -->
<c:if test="${Marital.size() != 0 || Spouse_Quali.size()!=0}">

	<div class="card">
		<div class="panel-group" id="accordion2">
			<div class="panel panel-default" id="f_div1">
				<div class="panel-heading">
					<h4 class="panel-title main_title lightblue" id="f_div5">
						<a class="droparrow collapsed" data-toggle="collapse"
							data-parent="#accordion2" href="#" id="f_final"
							onclick="divN(this)"><b>CHANGE IN MARITAL
								STATUS</b></a>
					</h4>
				</div>
				<div id="collapse1f" class="panel-collapse collapse">
					<div class="card-body card-block">
						<br>
						<div class="row">

							<div class="col-md-12" id="marital_eventDiv"
								style="display: none;">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong>Marital Event</label>
										</div>
										<div class="col-md-8">
											<label id="marital_event" class=" form-control-label"></label>
											<select name="marital_event" id="marital_event_val"
												class="form-control" onchange="marital_eventchange();"
												style="display: none;">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getMarital_eventList}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>

											</select>


										</div>
									</div>
								</div>

								<div class="col-md-6" style="display: none">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong>Marital Status</label>
										</div>
										<div class="col-md-8">
											<label id="marital_status" class=" form-control-label"></label>
											<select name="marital_status" id="marital_status_val"
												class="form-control" style="display: none;">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getMarital_statusList}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>

							</div>
							<div id="marriage_remarriageDiv" style="display: none">
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong>Authority</label>
											</div>
											<div class="col-md-8">
												<input type="hidden" id="marr_ch_id" name="marr_ch_id"
													value="0" class="form-control autocomplete"
													autocomplete="off"> <label id="marriage_authority"
													class=" form-control-label"></label> <input type="hidden"
													id="marriage_authority" name="marriage_authority"
													class="form-control autocomplete" autocomplete="off">
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong>Date of Authority</label>
											</div>
											<div class="col-md-8">
												<label id="marriage_date_of_authority"
													class=" form-control-label"></label> <input type="hidden"
													id="marriage_date_of_authority"
													name="marriage_date_of_authority"
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
													style="color: red;">* </strong>Spouse Name</label>
											</div>
											<div class="col-md-8">
												<label id="maiden_name" class=" form-control-label"></label>
												<input type="hidden" id="maiden_name" name="maiden_name"
													class="form-control autocomplete" autocomplete="off">

											</div>
										</div>
									</div>

									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong>Date of Marriage</label>
											</div>
											<div class="col-md-8">
												<label id="marriage_date" class=" form-control-label"></label>
												<input type="hidden" id="marriage_date" name="marriage_date"
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
													style="color: red;">* </strong>Spouse Place of Birth</label>
											</div>
											<div class="col-md-8">
												<label id="marriage_place_of_birth"
													class=" form-control-label"></label>
												<%-- <select name="marriage_place_of_birth" id="marriage_place_of_birth_val"class="form-control" style="display: none;" >
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getVillageList}"varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>           --%>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong>Spouse Nationality</label>
											</div>
											<div class="col-md-8">
												<label id="marriage_nationality" class=" form-control-label"></label>
												<select name="marriage_nationality"
													id="marriage_nationality_val" class="form-control"
													style="display: none;">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getNationalityList}"
														varStatus="num">
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
												<label class=" form-control-label"><strong
													style="color: red;">* </strong>Spouse Date of Birth</label>
											</div>
											<div class="col-md-8">
												<label id="marriage_date_of_birth"
													class=" form-control-label"></label> <input type="hidden"
													id="marriage_date_of_birth" name="marriage_date_of_birth"
													class="form-control autocomplete" autocomplete="off">


											</div>
										</div>
									</div>

									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong>Spouse Aadhaar Card No</label>
											</div>
											<div class="col-md-8">
												<label id="marriage_adhar_no" class=" form-control-label"></label>
												<input type="hidden" id="marriage_adhar_no"
													name="marriage_adhar_no" class="form-control autocomplete"
													autocomplete="off">
											</div>
										</div>
									</div>
								</div>

								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong>Spouse PAN Card No</label>
											</div>
											<div class="col-md-8">
												<label id="pan_card" class=" form-control-label"></label> <input
													type="hidden" id="pan_card" name="pan_card"
													class="form-control autocomplete" autocomplete="off">
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong>In Service/Ex-Service</label>
											</div>
											<div class="col-md-8" style="display: none">
												<input type="radio" id="spouse_ser_radio1"
													name="spouse_ser_radio" value="Yes"
													onchange="spouse_ServiceFn();">&nbsp <label
													for="yes">Yes</label>&nbsp <input type="radio"
													id="spouse_ser_radio2" name="spouse_ser_radio" value="No"
													onchange="spouse_ServiceFn();" checked="checked">&nbsp
												<label for="no">No</label><br>


											</div>
											<div class="col-md-8">
												<label id="isSpouseInSerlbl"></label>
											</div>
										</div>
									</div>
								</div>
								<div id="spouse_inserviceDiv" class="col-md-12"
									style="display: none">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong>Services</label>
											</div>
											<div class="col-md-8">
												<select style="display: none" name="spouse_service"
													id="spouse_service" class="form-cont rol-sm form-control"
													onchange="Spouse_ServiceOtherFn('spouseSer_otherDiv','spouse_personalDiv',this)">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getExservicemenList}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select> <label id="spouse_servicelbl"></label>

											</div>
										</div>
									</div>
									<div class="col-md-6" id="spouseSer_otherDiv"
										style="display: none">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">*</strong>Other</label>
											</div>
											<div class="col-md-8">
												<input type="hidden" id="spouseSer_other"
													name="spouseSer_other" class="form-control autocomplete"
													autocomplete="off"
													onkeypress="return /[0-9a-zA-Z]/i.test(event.key)"
													oninput="this.value = this.value.toUpperCase()"> <label
													id="spouseSer_otherlbl"></label>
											</div>
										</div>
									</div>
									<div class="col-md-6" id="spouse_personalDiv">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;"></strong> Personal No.</label>
											</div>
											<div class="col-md-8">
												<input type="hidden" id="spouse_personal_no"
													name="spouse_personal_no" class="form-control autocomplete"
													autocomplete="off"
													onkeypress="return /[0-9a-zA-Z]/i.test(event.key)"
													oninput="this.value = this.value.toUpperCase()"> <label
													id="spouse_personal_nolbl"></label>

											</div>
										</div>
									</div>

								</div>

								<div class="col-md-12" id="divorceDiv" style="display: none">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"
													id="divorce_widow_widower_dtlbl"><strong
													style="color: red;">* </strong>Date of Divorce</label>
											</div>
											<div class="col-md-8">
												<label id="divorce_date" class=" form-control-label"></label>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12" id="seperateDiv" style="display: none">
									<div class="col-md-6" id="separated_from_dtDiv"
										style="display: none">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong>Date of Separation</label>
											</div>
											<div class="col-md-8">
												<label id="separated_from_dt" class=" form-control-label"></label>
											</div>
										</div>
									</div>
									<div class="col-md-6" id="separated_to_dtDiv"
										style="display: none">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;"> </strong>Separation To Date</label>
											</div>
											<div class="col-md-8">
												<label id="separated_to_dt" class=" form-control-label"></label>
											</div>
										</div>
									</div>
								</div>
							</div>





							<div id="spouse_Qualifications"
								style="margin-top: 20px; display: none">

								<div class="col-md-12" id="spouse_quali_radioDiv"
									style="margin-top: 10px; width: 100%;">
									<label class=" form-control-label" style="margin-right: 100px;"><h4>
											Qualification Details of Spouse</h4></label> <br>

								</div>
								<div class="col-md-12">

									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4" align="left">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong>Qualification Type</label>
											</div>
											<div class="col-md-8">
												<select name="spouse_quali_type" id="spouse_quali_type"
													style="display: none"
													onchange="fn_qualification_typeChange(this,'spouse_quali','quali_degree_spouse','spouse_specialization')">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getQualificationTypeList}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>

												</select> <label class=" form-control-label"
													id="spouse_quali_typelbl"></label> <input type="hidden"
													id="spouse_qualification_ch_id"
													name="spouse_qualification_ch_id" value="0"
													class="form-control autocomplete" autocomplete="off">
											</div>
										</div>

									</div>
									<div class="col-md-6" id="academyQuali">
										<div class="row form-group">
											<div class="col-md-4" align="left">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong>Examination Passed</label>
											</div>
											<div class="col-md-8">
												<select name="spouse_quali" id="spouse_quali"
													style="display: none"
													onchange="fn_ExaminationTypeChange(this,'quali_degree_spouse','spouse_specialization');fn_otherShowHide(this,'other_div_examSpouse','exam_otherSpouse')">
													<option value="0">--Select--</option>

												</select> <label class=" form-control-label" id="spouse_qualilbl"></label>
											</div>
										</div>

									</div>

								</div>

								<div class="col-md-12">
									<div class="col-md-6" id="other_div_examSpouse"
										style="display: none;">
										<div class="row form-group">
											<div class="col-md-4" align="left">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong>Examination Other </label>
											</div>
											<div class="col-md-8">
												<label class=" form-control-label" id="exam_otherSpouse"></label>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4" align="left">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong>Degree Acquired</label>
											</div>
											<div class="col-md-8">
												<select name="quali_degree_spouse" id="quali_degree_spouse"
													style="display: none"
													onchange="fn_otherShowHide(this,'other_div_qualiDegSpouse','quali_deg_otherSpouse')">

													<option value="0">--Select--</option>

												</select> <label class=" form-control-label"
													id="quali_degree_spouselbl"></label>
											</div>
										</div>

									</div>
									<div class="col-md-6" id="specializationDiv">
										<div class="row form-group">
											<div class="col-md-4" align="left">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong>Specialization</label>
											</div>
											<div class="col-md-8">
												<select name="spouse_specialization" style="display: none"
													id="spouse_specialization"
													onchange="fn_otherShowHide(this,'other_div_qualiSpecSpouse','quali_spec_otherSpouse')">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getSpecializationList}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select> <label class=" form-control-label"
													id="spouse_specializationlbl"></label>
											</div>
										</div>

									</div>


								</div>
								<div class="col-md-12">
									<div class="col-md-6" id="other_div_qualiDegSpouse"
										style="display: none;">
										<div class="row form-group">
											<div class="col-md-4" align="left">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong>Degree Other </label>
											</div>
											<div class="col-md-8">

												<label class=" form-control-label"
													id="quali_deg_otherSpouse"></label>
											</div>
										</div>
									</div>

									<div class="col-md-6" id="other_div_qualiSpecSpouse"
										style="display: none;">
										<div class="row form-group">
											<div class="col-md-4" align="left">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong>Specialization Other </label>
											</div>
											<div class="col-md-8">

												<label class=" form-control-label"
													id="quali_spec_otherSpouse"></label>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong>Year of Passing</label>
											</div>
											<div class="col-md-8">
												<label class=" form-control-label" id="spouse_yrOfPass"></label>
											</div>
										</div>

									</div>

									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong>Div/Grade</label>
											</div>
											<div class="col-md-8">
												<select name="spouse_div_class" id="spouse_div_class"
													style="display: none"
													onchange="fn_otherShowHide(this,'other_div_classSpouse','class_otherSpouse')">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getDivclass}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select> <label class=" form-control-label" id="spouse_div_classlbl"></label>
											</div>
										</div>

									</div>


								</div>

								<div class="col-md-12" id="other_div_classSpouse"
									style="display: none;">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4" align="left">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong>Div/Grade Other </label>
											</div>
											<div class="col-md-8">

												<label class=" form-control-label" id="class_otherSpouse"></label>
											</div>
										</div>
									</div>

								</div>

								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong>Subjects</label>
											</div>
											<div class="col-md-8">
												<div class="multiselect" style="display: none;">

													<div class="selectBox" onclick="showCheckboxesSpouse()">
														<select name="spouse_sub_quali" id="spouse_sub_quali">
															<option>Select Subjects</option>
														</select>
														<div class="overSelect"></div>
													</div>
													<div id="spouse_checkboxes" class="checkboxes"
														style="max-height: 200px; overflow: auto;">
														<input type="text" id="spouse_searchSubject"
															name="spouse_searchSubject" placeholder="Search Subjects"
															class="form-control autocomplete" autocomplete="off">
														<c:forEach var="item" items="${getSubject}"
															varStatus="num">
															<label for="one" class="spouse_subjectlist"> <input
																type="checkbox" name="spouse_multisub"
																value="${item[0]}" /> ${item[1]}
															</label>
														</c:forEach>
													</div>

												</div>
												<div id="spouse_quali_sub_list"
													style="max-height: 200px; overflow: auto; width: 100%; border: 1px solid;">

												</div>

											</div>

										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong>Institute & Place</label>
											</div>
											<div class="col-md-8">
												<label class=" form-control-label"
													id="spouse_institute_place"></label>
											</div>
										</div>


									</div>


								</div>
							</div>


						</div>

					</div>
				</div>
			</div>
		</div>
	</div>

</c:if>
<!-- END MARITAL EVENTS -->
<!--  START DETAILS OF CHILDRENS -->

<c:if test="${ChildDetails.size() != 0}">
	<!--  START DETAILS OF CHILDRENS -->
	<div class="card">
		<div class="panel-group" id="accordion3">
			<div class="panel panel-default" id="g_div1">
				<div class="panel-heading">
					<h4 class="panel-title main_title lightblue" id="g_div5">
						<a class="droparrow collapsed" data-toggle="collapse"
							data-parent="#accordion3" href="#" id="g_final"
							onclick="divN(this)"><b>UPDATE CHILD DETAILS</b></a>
					</h4>
				</div>
				<div id="collapse1g" class="panel-collapse collapse">
					<div class="card-body card-block">
						<div class="card-body-header">
							<h5></h5>
						</div>
						<table id="m_children_details_table"
							class="table-bordered watermarked"
							style="margin-top: 10px; width: 100%;">
							<thead style="color: white; text-align: center;">
								<tr>
									<!--exa  -->
									<th style="width: 2%;">Sr No</th>
									<th style="width: 10%;">Name</th>
									<th style="width: 10%;">Date of Birth</th>
									<th style="width: 5%;">If Specially Abled Child</th>
									<th style="width: 10%;">Relationship</th>
									<th style="width: 5%;">If Adopted Child</th>
									<th style="width: 10%;">Adoption Date</th>
									<th style="width: 10%;">Aadhaar Card No</th>
									<th style="width: 10%;">PAN Card No</th>
									<th style="width: 5%;">SERVICE/EX-SERVICE</th>
									<th style="width: 10%;">PERSONAL NO</th>
									<th style="width: 15%;">OTHER SERVICE</th>

								</tr>
							</thead>
							<tbody data-spy="scroll" id="m_children_detailstbody"
								style="min-height: 46px; max-height: 650px; text-align: center;">

							</tbody>
						</table>
					</div>

				</div>
			</div>
		</div>
	</div>


</c:if>
<!-- END DETAILS OF CHILDRENS -->

<!-- START NOK -->
<c:if test="${NOK.size() != 0}">
	<form id="form_nok_addr_details_form">
		<div class="card">
			<div class="panel-group" id="accordion10">
				<div class="panel panel-default" id="h_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title lightblue" id="h_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion10" href="#" id="h_final"
								onclick="divN(this)"><b>CHANGE IN NOK</b></a>
						</h4>
					</div>
					<div id="collapse1h" class="panel-collapse collapse">
						<div class="card-body card-block">
							<br>
							<div class="row">

								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong> AUTHORITY</label>
											</div>
											<div class="col-md-8">
												<label id="nok_authority" class=" form-control-label"></label>
												<input type="hidden" id="nok_authority" name="authority"
													class="form-control autocomplete" autocomplete="off">
											</div>
										</div>
									</div>

									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong> DATE OF AUTHORITY</label>
											</div>
											<div class="col-md-8">
												<label id="nok_date_authority" class=" form-control-label"></label>
												<input type="hidden" id="nok_date_authority"
													name="date_authority" class="form-control autocomplete"
													autocomplete="off">
											</div>
										</div>
									</div>
								</div>

								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong> Name of NOK</label>
											</div>
											<div class="col-md-8">
												<label id="nok_name" class=" form-control-label"></label> <input
													type="hidden" id="nok_name" name="nok_name"
													class="form-control autocomplete" autocomplete="off">
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong> Relation</label>
											</div>
											<div class="col-md-8">
												<input type="hidden" id="nok_id" name="nok_id" value="0"
													class="form-control autocomplete" autocomplete="off">
												<label id="nok_relation" class=" form-control-label"></label>
												<select name="nok_relationSelect" id="nok_relationSelect"
													class="form-control" style="display: none">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getRelationList}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
								</div>

								<div class="col-md-12">
									<label class=" form-control-label" style="margin-left: 10px;"><h4>
											NOK's Address</h4></label>
								</div>

								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong>COUNTRY</label>
											</div>
											<div class="col-md-8">
												<label id="nok_country" class=" form-control-label"></label>
												<select name="nok_countrySelect" id="nok_countrySelect"
													class="form-control" style="display: none">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getMedCountryName}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>

												</SELECT>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong> STATE</label>
											</div>
											<div class="col-md-8">
												<label id="nok_state" class=" form-control-label"></label> <select
													name="nok_stateSelect" id="nok_stateSelect"
													class="form-control-sm form-control" style="display: none">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getMedStateName}"
														varStatus="num">
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
												<label class=" form-control-label"><strong
													style="color: red;">* </strong> DISTRICT</label>
											</div>
											<div class="col-md-8">
												<label id="nok_district" class=" form-control-label"></label>
												<select name="nok_districtSelect" id="nok_districtSelect"
													class="form-control" style="display: none">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getMedDistrictName}"
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
													style="color: red;">* </strong> TEHSIL</label>
											</div>
											<div class="col-md-8">
												<label id="nok_tehsil" class=" form-control-label"></label>
												<select name="nok_tehsilSelect" id="nok_tehsilSelect"
													class="form-control" style="display: none">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getMedCityName}"
														varStatus="num">
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
												<label class=" form-control-label"><strong
													style="color: red;">* </strong> Village/Town/City</label>
											</div>
											<div class="col-md-8">
												<label id="nok_village" class=" form-control-label"></label>
												<%-- <select name="nok_villageSelect" id="nok_villageSelect" class="form-control" style="display:none">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getVillageList}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>		 --%>
											</div>
										</div>
									</div>

									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong> Pin</label>
											</div>
											<div class="col-md-8">
												<label id="nok_pin" class=" form-control-label"></label> <input
													type="hidden" id="nok_pin" name="nok_pin"
													class="form-control autocomplete" autocomplete="off"
													onkeypress="return isNumber0_9Only(event)">
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong> Nearest Railway Station</label>
											</div>
											<div class="col-md-8">
												<label id="nok_rail" class=" form-control-label"></label> <input
													type="hidden" id="nok_rail" name="nok_rail"
													class="form-control autocomplete" autocomplete="off">
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong>Is Rural /Urban/Semi Urban</label>
											</div>
											<div class="col-md-8">
												<label for="nok_rural" id="nok_rural_urban"></label>
												<!-- <input type="radio" id="nok_rural" name="nok_rural_urban" value="rural" > Rural</label> 
					    	 <label for="nok_urban" >
							 <input type="radio" id="nok_urban" name="nok_rural_urban" value="urban" > Urban</label> 
					    	 <label for="nok_semi_urban">
							 <input type="radio" id="nok_semi_urban" name="nok_rural_urban" value="semi_urban" >Semi Urban</label>  -->
											</div>
										</div>
									</div>
								</div>

								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong>Nok Mobile No</label>
											</div>
											<div class="col-md-8">
												<label id="nok_mobile_no" class=" form-control-label"></label>
												<input type="hidden" id="nok_mobile_no" name="nok_mobile_no"
													class="form-control autocomplete" autocomplete="off"
													onkeypress="return isNumber0_9Only(event)">
											</div>
										</div>
									</div>
								</div>
							</div>

						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
</c:if>
<!-- END NOK -->

<c:if test="${DESIGNATION.size() != 0}">

<form id="form_designation_details_form">
		<div class="card">
			<div class="panel-group" id="accordion5">
				<div class="panel panel-default" id="d_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title lightgreen" id="d_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion5" href="#" id="d_final" onclick="divN(this)"
								><b>CHANGE IN DESIGNATION</b></a>
						</h4>
					</div>
					<div id="collapse1d" class="panel-collapse collapse">
						<div class="card-body card-block">
						<div align="left">
						<input type="button" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('change_designation');">
						</div>
						<br>
							<div class="row">
							<div class="col-md-12">	
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;"></strong> Authority</label>
						                </div>
						                <div class="col-md-8">
						                <label id="des_authority" class=" form-control-label"></label>
												<input type="hidden" id="des_authority" name="des_authority"
													class="form-control autocomplete" autocomplete="off">
						                </div>
						            </div>
	              				</div>
	              				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;"> </strong> Date of Authority</label>
						                </div>
						                <div class="col-md-8">
						                <label id="date_authority_des" class=" form-control-label"></label>
												<input type="hidden" id="date_authority_des"
													name="date_authority_des" class="form-control autocomplete"
													autocomplete="off">
						                </div>
						            </div>
	              				</div>
	              				
	              			</div>
	              			<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Designation</label>
								</div>
								<div class="col-md-8">
								<input type="hidden" id="des_id" name="des_id" value="0"
													class="form-control autocomplete" autocomplete="off">
								<label id="designation" class=" form-control-label"></label>
									<select name="desSelect" id="desSelect"
										class="form-control-sm form-control" style="display: none">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getDesignationList}"
											varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
								<%-- <div class="col-md-8">
												<label id="nok_country" class=" form-control-label"></label>
												<select name="nok_countrySelect" id="nok_countrySelect"
													class="form-control" style="display: none">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getMedCountryName}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>

												</SELECT>
											</div> --%>
							</div>
						</div>
						

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"> *</strong> Date of Designation</label>
								</div>
								<div class="col-md-8">
									
										 <label id="dt_of_designation" class=" form-control-label"></label>
												<input type="hidden" id="dt_of_designation"
													name="dt_of_designation" class="form-control autocomplete"
													autocomplete="off">
								</div>
							</div>
						</div>
					</div>	
	              				
	              			
							</div>
					   
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>	

</c:if>

<!-- START CHANGE OF ADD -->

<c:if test="${ChangeAdd.size() != 0}">
	<form id="form_addr_details_form">
		<div class="card">
			<div class="panel-group" id="accordion9">
				<div class="panel panel-default" id="i_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title lightblue" id="i_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion9" href="#" id="i_final"
								onclick="divN(this)"><b>CHANGE IN ADDRESS</b></a>
						</h4>
					</div>
					<div id="collapse1i" class="panel-collapse collapse">
						<div class="card-body card-block">
							<br>
							<div class="row">
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong>Authority</label>
											</div>
											<div class="col-md-8">
												<label id="addr_authority"></label> <input type="hidden"
													id="addr_authority" class="form-control "
													autocomplete="off">
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong>Date of Authority</label>
											</div>
											<div class="col-md-8">
												<label id="addr_date_authority"></label> <input
													type="hidden" id="addr_date_authority"
													class="form-control " autocomplete="off">
											</div>
										</div>
									</div>
								</div>
								<label class=" form-control-label" style="margin-left: 10px;"><h4>
										Present Domicile</h4></label>

								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong>COUNTRY</label>
											</div>
											<div class="col-md-8">
												<label id="pre_country" class=" form-control-label"></label>
												<select name="pre_country_val" id="pre_country_val"
													class="form-control" style="display: none;">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getMedCountryName}"
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
													style="color: red;">* </strong> STATE</label>
											</div>
											<div class="col-md-8">
												<input type="hidden" id="addr_ch_id" name="addr_ch_id"
													value="0" class="form-control autocomplete"
													autocomplete="off"> <label id="pre_domicile_state"
													class=" form-control-label"></label> <select
													name="pre_domicile_state_val" id="pre_domicile_state_val"
													class="form-control" style="display: none;">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getMedStateName}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</SELECT>
											</div>
										</div>
									</div>

								</div>

								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong> DISTRICT</label>
											</div>
											<div class="col-md-8">
												<label id="pre_domicile_district"
													class=" form-control-label"></label> <select
													name="pre_domicile_district_val"
													id="pre_domicile_district_val" class="form-control"
													style="display: none;">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getMedDistrictName}"
														varStatus="num">
														<option value="${item[0]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong> TEHSIL*</label>
											</div>
											<div class="col-md-8">
												<label id="pre_domicile_tesil" class=" form-control-label"></label>
												<select name="pre_domicile_tesil_val"
													id="pre_domicile_tesil_val" class="form-control"
													style="display: none;">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getMedCityName}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>

												</SELECT>
											</div>
										</div>
									</div>

								</div>



								<label class=" form-control-label" style="margin-left: 10px;"><h4>
										Permanent Address</h4></label>
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong>COUNTRY</label>
											</div>
											<div class="col-md-8">

												<label id="per_home_addr_country"
													class=" form-control-label"></label> <select
													name="per_home_addr_country_val"
													id="per_home_addr_country_val" class="form-control"
													style="display: none;">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getMedCountryName}"
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
													style="color: red;">* </strong> STATE</label>
											</div>
											<div class="col-md-8">
												<label id="per_home_addr_state" class=" form-control-label"></label>
												<select name="per_home_addr_state_val"
													id="per_home_addr_state_val" class="form-control"
													style="display: none;">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getMedStateName}"
														varStatus="num">
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
												<label class=" form-control-label"><strong
													style="color: red;">* </strong> DISTRICT</label>
											</div>
											<div class="col-md-8">
												<label id="per_home_addr_district"
													class=" form-control-label"></label> <select
													name="per_home_addr_district_val"
													id="per_home_addr_district_val" class="form-control"
													style="display: none;">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getMedDistrictName}"
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
													style="color: red;">* </strong> TEHSIL</label>
											</div>
											<div class="col-md-8">
												<label id="per_home_addr_tehsil" class=" form-control-label"></label>
												<select name="per_home_addr_tehsil_val"
													id="per_home_addr_tehsil_val" class="form-control"
													style="display: none;">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getMedCityName}"
														varStatus="num">
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
												<label class=" form-control-label"><strong
													style="color: red;">* </strong> Village/Town/City</label>
											</div>
											<div class="col-md-8">
												<label id="per_home_addr_village"
													class=" form-control-label"></label>
												<%-- <select name="per_home_addr_village_val" id="per_home_addr_village_val" class="form-control" style="display: none;">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getVillageList}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>	 --%>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong> Pin</label>
											</div>
											<div class="col-md-8">
												<label id="per_home_addr_pin" class=" form-control-label"></label>
												<input type="hidden" id="per_home_addr_pin"
													name="per_home_addr_pin" class="form-control autocomplete"
													autocomplete="off"
													onkeypress="return isNumber0_9Only(event)">
											</div>
										</div>
									</div>

								</div>

								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong> Nearest Railway Station</label>
											</div>
											<div class="col-md-8">
												<label id="per_home_addr_rail" class=" form-control-label"></label>
												<input type="hidden" id="per_home_addr_rail"
													name="per_home_addr_rail" class="form-control autocomplete"
													autocomplete="off">
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">

												<label class=" form-control-label"><strong
													style="color: red;">* </strong>Is Rural /Urban/Semi Urban</label>
											</div>
											<div class="col-md-8">

												<label for="lbl_perm_home_addr_rural" id="perm_home_addr"></label>
												<!-- <input type="radio" id="perm_home_addr_rural" name="perm_home_addr_rural_urban" value="rural" > Rural</label> 
					    	 <label for="lbl_perm_home_addr_urban" >
							 <input type="radio" id="perm_home_addr_urban" name="perm_home_addr_rural_urban" value="urban" > Urban</label> 
					    	 <label for="lbl_perm_home_addr_semi_urban">
							 <input type="radio" id="perm_home_addr_semi_urban" name="perm_home_addr_rural_urban" value="semi_urban" >Semi Urban</label>  -->

											</div>
										</div>
									</div>
								</div>

								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong>Border area</label>
											</div>
											<div class="col-md-8">
												<!--  <label for="border_area">
							 <input type="radio" id="border_area" name="border_area" value="yes"   >yes</label> 
					    	 <label for="border_area1"> 
				      		 <input type="radio" id="border_area1" name="border_area" value="no" > no</label> -->

												<label for="border_area" id="per_border_area"></label>
												<!-- <input type="radio" id="per_border_area" name="per_border_area" value="yes"   >yes</label> 
					    	 <label for="per_border_area1"> 
				      		 <input type="radio" id="per_border_area1" name="per_border_area" value="no" > no -->

											</div>
										</div>
									</div>
								</div>

								<div class="col-md-12"></div>
								<!-- 	<div class="col-md-12" style="font-size: 15px;">	
           		<input type="checkbox" id="check_address" name="check_address" onclick="copy_address()">
               	<label for="text-input" class=" form-control-label" style="color: #dd1a3e; margin-left:10px;">  Same as Permanent Address </label>
            </div> -->
								<div class="col-md-12">
									<label class=" form-control-label" style="margin-left: 10px;"><h4>
											Present Address</h4></label>
								</div>

								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong>COUNTRY</label>
											</div>
											<div class="col-md-8">
												<label id="pers_addr_country" class=" form-control-label"></label>
												<select name="pers_addr_country_val"
													id="pers_addr_country_val" class="form-control"
													style="display: none;">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getMedCountryName}"
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
													style="color: red;">* </strong> STATE</label>
											</div>
											<div class="col-md-8">
												<label id="pers_addr_state" class=" form-control-label"></label>
												<select name="pers_addr_state_val" id="pers_addr_state_val"
													class="form-control" style="display: none;">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getMedStateName}"
														varStatus="num">
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
												<label class=" form-control-label"><strong
													style="color: red;">* </strong> DISTRICT</label>
											</div>
											<div class="col-md-8">
												<label id="pers_addr_district" class=" form-control-label"></label>
												<select name="pers_addr_district_val"
													id="pers_addr_district_val" class="form-control"
													style="display: none;">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getMedDistrictName}"
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
													style="color: red;">* </strong> TEHSIL</label>
											</div>
											<div class="col-md-8">
												<label id="pers_addr_tehsil" class=" form-control-label"></label>
												<select name="pers_addr_tehsil_val"
													id="pers_addr_tehsil_val" class="form-control"
													style="display: none;">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getMedCityName}"
														varStatus="num">
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
												<label class=" form-control-label"><strong
													style="color: red;">* </strong> Village/Town/City</label>
											</div>
											<div class="col-md-8">
												<label id="pers_addr_village" class=" form-control-label"></label>
												<%--  <select name="pers_addr_village_val" id="pers_addr_village_val" class="form-control" style="display: none;">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getVillageList}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>	 --%>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong> Pin</label>
											</div>
											<div class="col-md-8">
												<label id="pers_addr_pin" class=" form-control-label"></label>
												<input type="hidden" id="pers_addr_pin" name="pers_addr_pin"
													class="form-control autocomplete" autocomplete="off"
													onkeypress="return isNumber0_9Only(event)">
											</div>
										</div>
									</div>

								</div>

								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong> Nearest Railway Station</label>
											</div>
											<div class="col-md-8">
												<label id="pers_addr_rail" class=" form-control-label"></label>
												<input type="hidden" id="pers_addr_rail"
													name="pers_addr_rail" class="form-control autocomplete"
													autocomplete="off">
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong>Is Rural /Urban/Semi Urban</label>
											</div>
											<div class="col-md-8">
												<label for="pers_addr_rural" id="pers_addr_rural_urban"></label>
												<!-- 	 <input type="radio" id="pers_addr_rural" name="pers_addr_rural_urban" value="rural" > Rural</label> 
					    	 <label for="pers_addr_urban" >
							 <input type="radio" id="pers_addr_urban" name="pers_addr_rural_urban" value="urban" > Urban</label> 
					    	 <label for="pers_addr_semi_urban">
							 <input type="radio" id="pers_addr_semi_urban" name="pers_addr_rural_urban" value="semi_urban" >Semi Urban</label>  -->




											</div>
										</div>
									</div>
								</div>
								<div id="spouse_addressInnerdiv" style="display: none">
									<label for="text-input" class=" form-control-label"
										style="color: #dd1a3e; margin-left: 10px;"> Family
										Staying SF ACCN </label>
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;">* </strong> COUNTRY</label>
												</div>
												<div class="col-md-8">
													<label id="spouse_addr_Countrylb"
														name="spouse_addr_Countrylb"></label> <select
														style="display: none" name="spouse_addr_Country"
														id="spouse_addr_Country"
														onchange="fn_spouse_addr_Country();"
														class="form-control-sm form-control">
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${getMedCountryName}"
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
														style="color: red;">* </strong> STATE</label>
												</div>
												<div class="col-md-8">
													<label id="spouse_addr_statelb" name="spouse_addr_statelb"></label>
													<select style="display: none" name="spouse_addr_state"
														id="spouse_addr_state" onchange="fn_spouse_addr_state();"
														class="form-control-sm form-control">
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${getMedStateName}"
															varStatus="num">
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
													<label class=" form-control-label"><strong
														style="color: red;">* </strong> DISTRICT</label>
												</div>
												<div class="col-md-8">
													<label id="spouse_addr_districtlb"
														name="spouse_addr_districtlb"></label> <select
														style="display: none" name="spouse_addr_district"
														id="spouse_addr_district"
														onchange="fn_spouse_addr_district();"
														class="form-control-sm form-control">
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${getMedDistrictName}"
															varStatus="num">
															<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
														</c:forEach>
													</select>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row foInteger.parseInt(rm-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;">* </strong> TEHSIL</label>
												</div>
												<div class="col-md-8">
													<label id="spouse_addr_tehsillb"
														name="spouse_addr_tehsillb"></label> <select
														style="display: none" name="spouse_addr_tehsil"
														id="spouse_addr_tehsil"
														class="form-control-sm form-control">
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${getMedCityName}"
															varStatus="num">
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
													<label class=" form-control-label"><strong
														style="color: red;">* </strong> Village/Town/City</label>
												</div>
												<div class="col-md-8">
													<%--  <select name="pers_addr_village" id="pers_addr_village" class="form-control-sm form-control">
											<option value="0">--Select--</option>
											<c:forEach var="item" items="${getVillageList}" varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select> --%>
													<label id="spouse_addr_village" name="spouse_addr_village"
														class=" autocomplete"></label>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;">* </strong> Pin</label>
												</div>
												<div class="col-md-8">
													<label id="spouse_addr_pin" name="spouse_addr_pin"
														class=" autocomplete"></label>
												</div>
											</div>
										</div>
									</div>


									<div class="col-md-12">

										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;">* </strong> Nearest Railway Station</label>
												</div>
												<div class="col-md-8">
													<label id="spouse_addr_rail" name="spouse_addr_rail"
														class=" autocomplete"></label>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;">* </strong>Is Rural /Urban/Semi Urban</label>
												</div>
												<div class="col-md-8">
													<label for="spouse_addr_rural" id="spouse_addr_rural_urban"></label>
													<!-- 	 <input type="radio" id="pers_addr_rural" name="pers_addr_rural_urban" value="rural" > Rural</label> 
					    	 <label for="pers_addr_urban" >
							 <input type="radio" id="pers_addr_urban" name="pers_addr_rural_urban" value="urban" > Urban</label> 
					    	 <label for="pers_addr_semi_urban">
							 <input type="radio" id="pers_addr_semi_urban" name="pers_addr_rural_urban" value="semi_urban" >Semi Urban</label>  -->




												</div>
											</div>
										</div>
									</div>
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;">* </strong>Stn HQ SUS No</label>
												</div>
												<div class="col-md-8">
													<label class=" form-control-label" id="Stn_HQ_sus_no"></label>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;">* </strong>Stn HQ Name</label>
												</div>
												<div class="col-md-8">
													<label class=" form-control-label" id="Stn_HQ_unit_name"></label>
												</div>
											</div>
										</div>
									</div>

								</div>
							</div>


						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
</c:if>
<!-- END CHANGE OF ADD -->


<a href="#" class="go-top fa fa-arrow-up" style="display: none"></a>


<script type="text/javascript">
var app_status=1;
function myFunction() {
	  var checkBox = document.getElementById("myCheck");
	 
	  if (checkBox.checked == true){
		  $("#submit_a").show();
		 
	  } else {
		  $("#submit_a").hide();
		
	  }
}


 function divN(obj){
	var id = obj.id;
	 var sib_id = $("#"+id).parent().parent().next().attr('id');
	var hasC=$("#"+sib_id).hasClass("show");
		$(".panel-collapse").removeClass("show");
		 $('.droparrow').each(function(i, obj) {
			 $("#"+obj.id).attr("class", "droparrow collapsed");
			 });
	
		 if (hasC) {	
				$("#"+id).addClass( " collapsed");		 
				}  else {				
					$("#"+sib_id).addClass("show");	
					$("#"+id).removeClass("collapsed");
			    }
		} 

</script>


<script>
$(document).ready(function() {
	
	$("#personnel_noV").val('${emp_no5}');
	$("#statusV").val('${status5}');
	
	$("#comm_idV").val('${civ_id5}');
	$("#civ_id").val('${civ_id}');
	$("#unit_nameV").val('${unit_name5}');
	$("#unit_sus_noV").val('${unit_sus_no5}');
	 	
	$.ajaxSetup({
			async : false
		});
	$('#event').val('${event}');
 	$('#civ_id').val('${civ_id}');
 	
  	$('#personnel_no').val('${emp_no2}');
  	 $("input#personnel_no").attr('readonly', true);
  	
  	 personal_number();
  	  $("#submit_a").hide();
  	

  
  	<c:if test="${Marital.size() != 0}">  	
  	$("#marital_eventDiv").show();
	getfamily_marriageDetails();	
	</c:if>
	<c:if test="${Spouse_Quali.size() != 0}">  	
	getSpouseQualificationData();
	</c:if>
  	<c:if test="${ChildDetails.size() != 0}">
  	m_children_Details();
  	colAdj("m_children_details_table");
	</c:if>
	
  	<c:if test="${NOK.size() != 0}">
  	get_nokaddress_details();
	</c:if>
	<c:if test="${DESIGNATION.size() != 0}">
	get_designation_details();
	</c:if>
	
  	<c:if test="${ChangeAdd.size() != 0}">
  	get_changeaddress_details();
	</c:if>
	
});

var curr_marital_status=0;
var serving_status="SERVING";
function personal_number(){

	var personnel_no = '${emp_no2}';
	
	 $.post('GetEmpNoData12?' + key + "=" + value,{ personnel_no : personnel_no },function(j) {
		
		 if(j!=""){
			 
	    	var civ_id =j[0][0]; 
	    	

	         $.post('GetCivDataApprove_civdata?' + key + "=" + value,{ civ_id : civ_id },function(k) {
	    		
	    		 if(k.length > 0)
	    		 {
	    			 
	    			  curr_marital_status=k[0].marital_status;
	    			  
	    			  if(curr_marital_status!=8)
	    			  {
	    				  $("#marr_quali_radioDiv").remove();
	    			  }else{
	    				  $('#marital_eventDiv').hide();
	    			  }
	    			  
	    			  $('#marital_event').val('0');
	    			  $('#marriage_div').show();
	    			  
	    			   $("#cadet_name").text(k[0].full_name);
	    			 	if(k[0].dob==null){
	    		    		$("#dob").text("");    
	    		    	}
	    		    	else{
	    		    		 $("#dob").text(convertDateFormate(k[0].dob));  
	    		    		 $("#dob_date").val(ParseDateColumncommission(k[0].dob));
	    		    	}
	    			    $("#marital_status_p").text(k[0].marital_status);
	    		    	
	    		    	if(k[0].date_of_tos==null){
	    		    		$("#p_tos").text("");    
	    		    	}
	    		    	else{
	    		    		 $("#p_tos").text(convertDateFormate(k[0].date_of_tos));  
	    		    	}
	    		    	$("#tos_date").val(ParseDateColumncommission(k[0].date_of_tos));
	    		    	$("#app_sus_no").text(k[0].sus_no);
	    		    	$("#personnel_no").text(k[0].employee_no);
	    		    	$("#auth").text(k[0].authority);
	    		    	
	    		    	
	    			    
	    				
	    		    	
	    		    	
	    		    	
	    		    	$("#old_att_sus_no").text(k[0].sus_no);
	    		    	var att_sus_no = $("#old_att_sus_no").text(k[0].sus_no);
	    				
	    		    	var sus_no =k[0].sus_no;
	    		    	if(sus_no!=""){
	    		    		getunit(sus_no);
	    		    	}
	    		    	if(att_sus_no!=""){
	    		    		getunit(att_sus_no);
	    		    	}
	    		    	
	    		    	function getunit(val) {	
	    		            $.post("getTargetUnitNameList?"+key+"="+value, {
	    		            	sus_no : sus_no
	    		        }, function(j) {
	    		                
	    		                

	    		        }).done(function(j) {
	    		    				   var length = j.length-1;
	    		    	                   var enc = j[length].substring(0,16); 
	    		    	                   
	    		    	                   $("#app_unit_name").text(dec(enc,j[0])); 
	    		    	                   $("#old_att_unit_name").text(dec(enc,j[0])); 
	    		        }).fail(function(xhr, textStatus, errorThrown) {
	    		        });
	    		    } 
	    		 }
	   });
	 		$.ajaxSetup({
					async : false
				});
	 		
	    	
		 }
});

}
function formateDate(value){
	var date = new Date(value);
	var formattedDate = date.getFullYear() + '-' + ('0' + (date.getMonth() + 1)).slice(-2) + '-' + ('0' + date.getDate()).slice(-2);
	return formattedDate;
}

function getfamily_marriageDetails(){
	
	 var civ_id='${civ_id}';
	 var marital_event='${event}';
	
	 
	
		<c:if test="${Marital.size() != 0}">
					$('#marital_event_val').val( '${Marital.get(0).type_of_event}'  );
					$('#marital_event').text($( "#marital_event_val option:selected" ).text());
					$('#marital_status_val').val( '${Marital.get(0).marital_status}'  );
					$('#marital_status').text($( "#marital_status_val option:selected" ).text());
		
			$('#maiden_name').text( '${Marital.get(0).maiden_name}'  );
			$('#marriage_date_of_birth').text(convertDateFormate( '${Marital.get(0).date_of_birth}'));
			
			$('#marriage_place_of_birth').text( '${Marital.get(0).place_of_birth}' );
		
			$('#marriage_nationality_val').val( '${Marital.get(0).nationality}');
			$('#marriage_nationality').text($("#marriage_nationality_val option:selected" ).text());
			if('${Marital.get(0).other_nationality}'!=null && '${Marital.get(0).other_nationality}'!='' && ($('#marriage_nationality').text().toUpperCase()==other || $('#marriage_nationality').text().toUpperCase()==others)){
				$('#marriage_nationality').text('${Marital.get(0).other_nationality}');
			}
			$('#marriage_date').text(convertDateFormate( '${Marital.get(0).marriage_date}'));
			$('#marriage_adhar_no').text( '${Marital.get(0).adhar_number}' );
			$('#pan_card').text( '${Marital.get(0).pan_card}'  );
			$('#marr_ch_id').val( '${Marital.get(0).id}'  );					
			$('#marriage_date_of_authority').text(convertDateFormate( '${Marital.get(0).date_of_authority}'));			
			$('#marriage_authority').text( '${Marital.get(0).authority}');
			
			$('#separated_from_dt').text(convertDateFormate( '${Marital.get(0).separated_from_dt}'));	
			
			$('#separated_to_dt').text(convertDateFormate( '${Marital.get(0).separated_to_dt}'));	
			
			if('${Marital.get(0).if_spouse_ser}'=="Yes"){
				$('#spouse_ser_radio1').prop('checked', true);
				$('#isSpouseInSerlbl').text("Yes")
				
				
			}
			else{
				$('#spouse_ser_radio2').prop('checked', true);
				$('#isSpouseInSerlbl').text("No")
			}
			if('${Marital.get(0).spouse_personal_no}'!=null){
				$('#spouse_personal_no').val('${Marital.get(0).spouse_personal_no}');
				$('#spouse_personal_nolbl').text('${Marital.get(0).spouse_personal_no}');
			}
			if('${Marital.get(0).other_spouse_ser}'!=null){
				$('#spouseSer_other').val('${Marital.get(0).other_spouse_ser}');
				$('#spouseSer_otherlbl').text('${Marital.get(0).other_spouse_ser}');
			}
			if('${Marital.get(0).spouse_service}'!=null){
				$('#spouse_service').val('${Marital.get(0).spouse_service}');
				$('#spouse_servicelbl').text($( "#spouse_service option:selected" ).text());
			}
			spouse_ServiceFn();
			Spouse_ServiceOtherFn('spouseSer_otherDiv','spouse_personalDiv',document.getElementById('spouse_service'))
			
			if( '${Marital.get(0).divorce_date}'!=null)
				$('#divorce_date').text(convertDateFormate( '${Marital.get(0).divorce_date}'));
			if( '${Marital.get(0).status}'==1 ||  '${Marital.get(0).type_of_event}'==2 ||  '${Marital.get(0).type_of_event}'==4 ){
				
				$('#maiden_name').prop('disabled', true);
				$('#marriage_date_of_birth').prop('disabled', true);
				$('#marriage_place_of_birth').prop('disabled', true);
				$('#marriage_nationality').prop('disabled', true);
				$('#marriage_date').prop('disabled', true);
				$('#marriage_adhar_no').prop('disabled', true);
				$('#pan_card').prop('disabled', true);
				$('#marr_ch_id').prop('disabled', true);			
				$('#marriage_date_of_authority').prop('disabled', true);		
				$('#marriage_authority').prop('disabled', true);
			}
			///$('#marital_event').prop('disabled', true);
			////$('#marital_status').prop('disabled', true);
			$("#marriage_remarriageDiv").show();
			
			
			if($('#marital_event_val').val()=="1"){
				if(convertDateFormate( '${Marital.get(0).separated_to_dt}')!='' ){
					 $("#seperateDiv").show();
	        		$("#separated_from_dtDiv").show();
	        		$("#separated_to_dtDiv").show();
	        	}
			}
			
			else if($('#marital_event_val').val()=="2" || $('#marital_event_val').val()=="5" || $('#marital_event_val').val()=="6"){			
				$("#divorceDiv").show();
// 				$("#spouse_quali_radioDiv").hide();
				
				if($('#marital_event_val').val()=="2"){
					$("#divorce_widow_widower_dtlbl").text("Date of Divorced")
				}
				if($('#marital_event_val').val()=="5" || $('#marital_event_val').val()=="6"){
					$("#divorce_widow_widower_dtlbl").text("Date of Death")
				}
				
				
				
				}
				
				else if($('#marital_event_val').val()=='4'){					
					 $("#seperateDiv").show();
			        		$("#separated_from_dtDiv").show();
			        		$("#separated_to_dtDiv").hide();		        		
			        	
			        	
				        		
			        	}
			
			$('#m_status').val( '${Marital.get(0).marital_status}');
			</c:if>

		
} 

function spouse_ServiceFn() {
	var a = $('input:radio[name=spouse_ser_radio]:checked').val();
	if(a.toLowerCase() == "yes") {
		$("#spouse_inserviceDiv").show();
//		$("#spouseSerDiv").hide();
	} else {
//		$("#father_proffDiv").show();
		$("#spouse_inserviceDiv").hide();
		$("#spouse_personal_no").val("");
		$("#spouseSer_other").val("");
		$("#spouse_service").val("0");
	}
}
function Spouse_ServiceOtherFn(divId, perId, obj) {
	if(obj.options[obj.selectedIndex].text == 'OTHER') {
		$("#" + divId).show();
		$("#" + perId).hide();
		$("#spouse_personal_no").val("");
	} else {
		$("#" + divId).hide();
		$("#" + perId).show();
		$("#spouseSer_other").val("");
	}
}





function getSpouseQualificationData(){
	
	
	 var q_id=$('#marr_ch_id').val();
	 var civ_id = '${civ_id}';
	 var i=0;
	var  app_status=1;
	  $.post('getSpouseQualificationCivData?' + key + "=" + value, {civ_id:civ_id,app_status:app_status }, function(j){
		  var v = j.length;
			if(v != 0) {
				$('#spouse_yrOfPass').text(j[0].passing_year);
				$('#spouse_div_class').val(j[0].div_class);
				$('#spouse_div_classlbl').text($("#spouse_div_class option:selected").text());
				$('#spouse_institute_place').text(j[0].institute);
				$('#spouse_qualification_ch_id').val(j[0].id);
				$("input[type=checkbox][name='spouse_multisub']").prop("checked", false);
				var subjectslist = j[0].subject.split(',');
				for(k = 0; k < subjectslist.length; k++) {
					$("input[type=checkbox][name='spouse_multisub'][value='" + subjectslist[k] + "']").prop("checked", true);
					$("#spouse_sub_quali option:first").text('Subjects(' + subjectslist.length + ')');
				}
				$('#spouse_quali_sub_list').empty()
				$("input[type='checkbox'][name='spouse_multisub']").each(function() {
					if(this.checked) {
						$('#spouse_quali_sub_list').append("<span class='subspan'>" + this.parentElement.innerText + "<span> <br>");
					}
				});
				$("#spouse_checkboxes").show();
				$('#spouse_quali_type').val(j[0].type);
				$('#spouse_quali_typelbl').text($("#spouse_quali_type option:selected").text());
				var typethisT = document.getElementById('spouse_quali_type');
				fn_qualification_typeChange(typethisT,'spouse_quali','quali_degree_spouse','spouse_specialization');
				
				if(j[0].examination_pass != null) {
					$('#spouse_quali').val(j[0].examination_pass);
					$('#spouse_qualilbl').text($("#spouse_quali option:selected").text());
					var qualithisT = document.getElementById('spouse_quali');
					fn_ExaminationTypeChange(qualithisT,'quali_degree_spouse','spouse_specialization');
				}
				
				if(j[0].degree != null) {
					$('#quali_degree_spouse').val(j[0].degree);
					$('#quali_degree_spouselbl').text($("#quali_degree_spouse option:selected").text());
					var degreethisT = document.getElementById('quali_degree_spouse');
					fn_otherShowHide(document.getElementById('quali_degree_spouse'),'other_div_qualiDegSpouse','quali_deg_otherSpouse');
				}
				if(j[0].specialization != null) {
					$('#spouse_specialization').val(j[0].specialization);
					$('#spouse_specializationlbl').text($("#spouse_specialization option:selected").text());
					 fn_otherShowHide(document.getElementById('spouse_specialization'),'other_div_qualiSpecSpouse','quali_spec_otherSpouse');
				}
				
				
			
				
			
				
				
				var examother="";
				var classother="";
				var deg_other="";
				var spec_other="";
				
				if(j[i].exam_other!=null)
		  			examother=j[i].exam_other;
		  	    if(j[i].class_other!=null)
		  	    	classother=j[i].class_other;
		  	    
		  	  if(j[i].degree_other!=null)
		  		deg_other=j[i].degree_other;
		  	   if(j[i].specialization_other!=null)
		  	    	spec_other=j[i].specialization_other;
		  	    
		  	
		 	  	$('#exam_otherSpouse').text(examother);
		 	
		 		 $('#class_otherSpouse').text(classother);
		 	 
		 		 $('#quali_deg_otherSpouse').text(deg_other);
		 	
		 		 $('#quali_spec_otherSpouse').text(spec_other);
		 		 
		 		 fn_otherShowHide(document.getElementById('spouse_div_class'),'other_div_classSpouse','class_otherSpouse');
		 	
		 	 
				$("#spouse_Qualifications").show();			
			}
			
			
			
	  });
	  
	
	
}

function marital_eventchange(){
	var marital_eventvalue=$('#marital_event_val').val();;
	if(marital_eventvalue=='1' || marital_eventvalue=='3'){
		$("#marriage_remarriageDiv").show();
		$("#divorceDiv").hide();
		$("#marital_status").val('8');
	}
	else if(marital_eventvalue=='2'){
		$("#marital_status").val('9');
		getfamily_marriageDetails();
		$("#divorceDiv").show();
	}
	else if( marital_eventvalue=='4'){
		$("#marital_status").val('13');
		getfamily_marriageDetails();
	}
	else{
		$("#marriage_remarriageDiv").hide();
		$("#divorceDiv").hide();
		$("#marital_status").val('0');
	}
	}
	
	
//spouse qualification


function fn_qualification_typeChange(obj,set_id,sec_id,th_id) {
	var q_type = obj.value;
	var options = '<option value="0" >--Select--</option>';
	$("select#"+set_id).html(options);
	if(q_type!='0'){
		$.post('GetExaminatonPassed?' + key + "=" + value, {
			q_type: q_type
			
		}, function(j) {
			if(j.length > 0) {
			
				for(var i = 0; i < j.length; i++) {
					options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
				}
				$("select#"+set_id).html(options);
			}
		});
	}
	
	var qualithisT = document.getElementById(set_id);
	fn_ExaminationTypeChange(qualithisT,sec_id,th_id);
	
	
	
}

function fn_ExaminationTypeChange(obj,set_id,sec_id) {
	var e_pass =  obj.value;

	var options = '<option value="0" >--Select--</option>';
	$("select#"+set_id).html(options);
	if(e_pass!='0'){
		$.post('GetExaminatonPassedDegree?' + key + "=" + value, {
			e_pass: e_pass
			
		}, function(j) {
			
			if(j.length > 0) {
			
				for(var i = 0; i < j.length; i++) {
					options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
				}
				$("select#"+set_id).html(options);
			}
			
		});
	}
	

 }
function fn_other_exam() {
  	var text = $("#quali option:selected").text();
  	if(text == "OTHERS"){
  		$("#other_div_exam").show();
  	}
  	else{
  		$("#other_div_exam").hide();
  		$("#exam_other").val("");
  	}
  }


function fn_other_class() {
	var text = $("#div_class option:selected").text();
	if(text == "OTHERS"){
	
		$("#other_div_class").show();
		
	}
	else
	{
		$("#other_div_class").hide();
		$("#class_other").val("");
		
	}
}


function fn_otherShowHide(obj,Divother_id,other_id){
	var thisText=$("#"+obj.id+" option:selected").text();
	
	
	if(thisText=="OTHERS"){
		$('#'+Divother_id).show();
	}
	else{
		$('#'+Divother_id).hide();
		$('#'+other_id).val('');
	}
}


function spouse_quali_radioFn(){
	
	var a = $('input:radio[name=spouse_quali_radio]:checked').val();
	if(a.toLowerCase() == "yes"){	
		$("#spouse_Qualifications").show();
		}
	else{	
		$("#spouse_Qualifications").hide();
		}
	}
	
function showCheckboxesSpouse() {
	  $("#spouse_checkboxes").toggle();
	}
	
	
$("input[type='checkbox'][name='spouse_multisub']").click(function() {
    // access properties using this keyword
    var num=0;
    $('#spouse_quali_sub_list').empty()
    $("input[type='checkbox'][name='spouse_multisub']").each(function () {  
        if ( this.checked ) {
        	$('#spouse_quali_sub_list').append("<span class='subspan'>"+this.parentElement.innerText+"<i class='fa fa-times' style='margin: 5px;  font-size: 15px;' onclick='removeSpouseSubFn("+this.value+")'></i><span> <br>");
            num=num+1;
            
            
        }
        
    });
    if(num!=0)
        $("#spouse_sub_quali option:first").text('Subjects('+num+')');
    else
        $("#spouse_sub_quali option:first").text("Select Subjects");
});

function m_children_Details() {


	var x = 0;

	
		<c:forEach var="j" items="${ChildDetails}" varStatus="num">

		x = x + 1;
		var dob = convertDateFormate('${j.date_of_birth}');
		if('${j.date_of_adpoted}' != ''){
			 adob = convertDateFormate('${j.date_of_adpoted}');
			}
		else{
			adob = '';
		}
			
		$("#m_children_detailstbody").append('<tr id="tr_children'+x+'">'
			+ '<td class="child_srno">'
			+ x
			+ '</td>'
			+ '<td style="width: 10%;"><label id="sib_name_lb'+x+'">'+"${j.name }"+ '</label> '
			+ '<td style="width: 10%;"> <label id="sib_date_of_birth_lb'+x+'">'+dob+'</label></td>'
			+ '<td style="width: 10%;"><label id="sib_type_lb'+x+'"></label> '
			+ '</td>'
			+ '<td style="width: 10%;"><label id="sib_relationship_lb'+x+'"></label> <select style="display:none" name="sib_relationship'+x+'" id="sib_relationship'+x+'" class="form-control"   >'
			+ '<option value="0">--Select--</option>'
			+ '<c:forEach var="item" items="${getChild_RelationshipList}" varStatus="num">'
			+ '<option value="${item[0]}" name="${item[1]}">${item[1]}</option>'
			+ '</c:forEach></select></td>'
			+ '<td style="width: 10%;"><label id="sib_adopted_lb'+x+'"></label></td>'
			+ '<td style="width: 10%;"> <label id="sib_adop_lb'+x+'">'+adob+'</label></td>'
			
			+ '<td style="width: 10%;"><label id="aadhar_no_lb'+x+'">'+setInvalidToNull("${j.aadhar_no }")+'</label></td>'
			+ '<td style="width: 10%;"> <label id="pan_no_lb'+x+'">'+"${j.pan_no }"+'</label></td>'
			
			+ '<td style="display:none;"><input type="text" id="sib_ch_id'+x+'" name="sib_ch_id'+x+'"   value="'+"${j.id }"+'"  class="form-control autocomplete" autocomplete="off" ></td>'
			
			+ '<td style="width: 5%;"> ' + '	<label class=" form-control-label" id="child_service' + x + '"> <select name="chi_service' + x + '" id="chi_service' + x + '"   >' + '<option value="0">--Select--</option>' + '<c:forEach var="item" items="${getExservicemenList}" varStatus="num">' + '<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' + '</c:forEach></select></td>'
			+ '<td style="width: 10%;"> ' + '	<label class=" form-control-label"  id="child_personal_no' + x + '" name="child_personal_no' + x + '" ' + '	>' +  + '</label> ' + '	</td>'
			+ '<td style="width: 15%;"> ' + '	<label class=" form-control-label"  id="other_child_ser' + x + '" name="other_child_ser' + x + '" ' + '	>' + + '</label> ' + '	</td>'
			
			+'</tr>');
		$('#sib_relationship' + x).val('${j.relationship }');
		$('#sib_relationship_lb' + x).text($('#sib_relationship'+ x+' option:selected').text());
		 if('${j.child_service}' == 0){
				$('#chi_service' + x).val("");
			}else{
				$('#chi_service' + x).val('${j.child_service}');
			}
			$('#child_personal_no' + x).text('${j.child_personal_no}');
			$('#other_child_ser' + x).text('${j.other_child_ser}');
			$('#child_service' + x).text($("#chi_service" + x +" option:selected").text());
		//$('#sib_adopted' + x).val(j.adoted);
		if('${j.type }' =="Yes"){
			$('#sib_type_lb' + x).text("Yes");
		}
		else
			$('#sib_type_lb' + x).text("No");
		
		if('${j.adoted }' =="Yes"){
			$('#sib_adopted_lb' + x).text("Yes");
		
		}
		else
			$('#sib_adopted_lb' + x).text("No");
		
		
			
		</c:forEach>	
			
			
		
		
}

function get_nokaddress_details(){
	$.ajaxSetup({
		async : false
	});

	<c:if test="${NOK.size() != 0}">
				$("#nok_authority").text('${NOK.get(0).authority}') ;
				$('#nok_date_authority').text(convertDateFormate('${NOK.get(0).date_authority}') ); 
				$("#nok_name").text('${NOK.get(0).nok_name}' );
				
				$('#nok_relationSelect').val('${NOK.get(0).nok_relation}' );
				$('#nok_relation').text($( "#nok_relationSelect option:selected" ).text());
				if('${NOK.get(0).relation_other}'!=null && '${NOK.get(0).relation_other}'!='' && ($('#nok_relation').text().toUpperCase()==other || $('#nok_relation').text().toUpperCase()==others)){
					$('#nok_relation').text('${NOK.get(0).relation_other}');
				}
				$('#nok_countrySelect').val('${NOK.get(0).nok_country}');
				$('#nok_country').text($( "#nok_countrySelect option:selected" ).text());
				if('${NOK.get(0).ctry_other}'!=null && '${NOK.get(0).ctry_other}'!='' && ($('#nok_country').text().toUpperCase()==other || $('#nok_country').text().toUpperCase()==others)){
					$('#nok_country').text('${NOK.get(0).ctry_other}');
				}
				fn_nok_Country();
				$('#nok_stateSelect').val('${NOK.get(0).nok_state}');
				$('#nok_state').text($( "#nok_stateSelect option:selected" ).text());
				if('${NOK.get(0).st_other}'!=null && '${NOK.get(0).st_other}'!='' && ($('#nok_state').text().toUpperCase()==other || $('#nok_state').text().toUpperCase()==others)){
					$('#nok_state').text('${NOK.get(0).st_other}');
				}
				fn_nok_state();
				$('#nok_districtSelect').val('${NOK.get(0).nok_district}');
				$('#nok_district').text($( "#nok_districtSelect option:selected" ).text());
				if('${NOK.get(0).dist_other}'!=null && '${NOK.get(0).dist_other}'!='' && ($('#nok_district').text().toUpperCase()==other || $('#nok_district').text().toUpperCase()==others)){
					$('#nok_district').text('${NOK.get(0).dist_other}');
				}
				fn_nok_district();
				$('#nok_tehsilSelect').val('${NOK.get(0).nok_tehsil}') ;
				$('#nok_tehsil').text($( "#nok_tehsilSelect option:selected" ).text());
				if('${NOK.get(0).tehsil_other}'!=null && '${NOK.get(0).tehsil_other}'!='' && ($('#nok_tehsil').text().toUpperCase()==other || $('#nok_tehsil').text().toUpperCase()==others)){
					$('#nok_tehsil').text('${NOK.get(0).tehsil_other}');
				}
				$('#nok_village').text('${NOK.get(0).nok_village}') ;
				
				
				
				$("#nok_pin").text('${NOK.get(0).nok_pin}') ;
				$("#nok_rail").text('${NOK.get(0).nok_near_railway_station}' );
				$("#nok_mobile_no").text('${NOK.get(0).nok_mobile_no}');
				 var nok= '${NOK.get(0).nok_rural_urban_semi}';
		            if(nok == "rural"){
		                   $("#nok_rural_urban").text("Rural");
			            } 
			           if(nok == "urban"){
			           	 $("#nok_rural_urban").text("Urban");
			            }
			           if(nok == "semi_urban")
			           {
			           	 $("#nok_rural_urban").text("Semi Urban");
			            } 
				$("#nok_id").val('${NOK.get(0).id}');
				</c:if>
			
	}
	
	

function get_designation_details(){
	$.ajaxSetup({
		async : false
	});

	<c:if test="${DESIGNATION.size() != 0}">
				$("#des_authority").text('${DESIGNATION.get(0).authority}') ;
				$('#date_authority_des').text(convertDateFormate('${DESIGNATION.get(0).date_authority}') ); 
				
				$('#dt_of_designation').text(convertDateFormate('${DESIGNATION.get(0).designation_date}') );
				
				$('#desSelect').val('${DESIGNATION.get(0).designation}' );
				$('#designation').text($( "#desSelect option:selected" ).text());
				
				
				
				$("#des_id").val('${DESIGNATION.get(0).id}');
				
				
				</c:if>
			
	}
	
function fn_nok_Country() {
	 var contry_id =$('select#nok_countrySelect').val();

	 		 	$.post("getStateListFormcon1?"+key+"="+value, {contry_id:contry_id}).done(function(j) {
	 		 				 	
	 		 			 	
	 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
	 				for ( var i = 0; i < j.length; i++) {
	 				
	 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
	 					
	 				}
	 				
	 				$("select#nok_stateSelect").html(options);
					/* fn_nok_state();
					fn_nok_district(); */
	 			}).fail(function(xhr, textStatus, errorThrown) {
	 	});
}



function fn_nok_state() {
var state_id = $('select#nok_stateSelect').val();

		 	$.post("getDistrictListFormState1?"+key+"="+value, {state_id:state_id}).done(function(j) {
		 				 	
		 			 	
		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
				for ( var i = 0; i < j.length; i++) {
				
						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
					
				}
				
				$("select#nok_districtSelect").html(options);
				//fn_nok_district();
			}).fail(function(xhr, textStatus, errorThrown) {
	});
}	

function fn_nok_district() {
var Dist_id = $('select#nok_districtSelect').val();

		 	$.post("getTehsilListFormDistrict1?"+key+"="+value, {Dist_id:Dist_id}).done(function(j) {
		 				 	
		 			 	
		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
				for ( var i = 0; i < j.length; i++) {
				
						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
					
				}
				
				$("select#nok_tehsilSelect").html(options);

			}).fail(function(xhr, textStatus, errorThrown) {
	});
}

function get_changeaddress_details(){
	$.ajaxSetup({
			async : false
		});

	<c:if test="${ChangeAdd.size() != 0}">
	
	
			$("#pre_country_val").val('${ChangeAdd.get(0).pre_country}');
			$('#pre_country').text($( "#pre_country_val option:selected" ).text());
			if('${ChangeAdd.get(0).pre_country_other}'!=null && '${ChangeAdd.get(0).pre_country_other}'!='' && ($('#pre_country').text().toUpperCase()==other || $('#pre_country').text().toUpperCase()==others)){
				$('#pre_country').text('${ChangeAdd.get(0).pre_country_other}');
			}
			fn_pre_domicile_Country();
			$("#pre_domicile_state_val").val('${ChangeAdd.get(0).pre_state}' );
			$('#pre_domicile_state').text($( "#pre_domicile_state_val option:selected" ).text());
			if('${ChangeAdd.get(0).pre_domicile_state_other}'!=null && '${ChangeAdd.get(0).pre_domicile_state_other}'!='' && ($('#pre_domicile_state').text().toUpperCase()==other || $('#pre_domicile_state').text().toUpperCase()==others)){
				$('#pre_domicile_state').text('${ChangeAdd.get(0).pre_domicile_state_other}');
			}
			fn_pre_domicile_state();
			$("#pre_domicile_district_val").val( '${ChangeAdd.get(0).pre_district}' );
			$('#pre_domicile_district').text($( "#pre_domicile_district_val option:selected" ).text());
			if('${ChangeAdd.get(0).pre_domicile_district_other}'!=null && '${ChangeAdd.get(0).pre_domicile_district_other}'!='' && ($('#pre_domicile_district').text().toUpperCase()==other || $('#pre_domicile_district').text().toUpperCase()==others) ){
				$('#pre_domicile_district').text('${ChangeAdd.get(0).pre_domicile_district_other}');
			}
			fn_pre_domicile_district();
			$("#pre_domicile_tesil_val").val('${ChangeAdd.get(0).pre_tesil}' );
			$('#pre_domicile_tesil').text($( "#pre_domicile_tesil_val option:selected" ).text());
			if('${ChangeAdd.get(0).pre_domicile_tesil_other}'!=null && '${ChangeAdd.get(0).pre_domicile_tesil_other}'!='' && ($('#pre_domicile_tesil').text().toUpperCase()==other || $('#pre_domicile_tesil').text().toUpperCase()==others)){
				$('#pre_domicile_tesil').text('${ChangeAdd.get(0).pre_domicile_tesil_other}');
			}
			
			$("#addr_authority").text('${ChangeAdd.get(0).authority}');
			$('#addr_date_authority').text(convertDateFormate('${ChangeAdd.get(0).date_authority}')); 
			
			$("#per_home_addr_country_val").val('${ChangeAdd.get(0).permanent_country}');
			$('#per_home_addr_country').text($( "#per_home_addr_country_val option:selected" ).text());
			fn_per_home_addr_Country();
			if('${ChangeAdd.get(0).per_home_addr_country_other}'!=null && '${ChangeAdd.get(0).per_home_addr_country_other}'!='' && ($('#per_home_addr_country').text().toUpperCase()==other || $('#per_home_addr_country').text().toUpperCase()==others)){
				$('#per_home_addr_country').text('${ChangeAdd.get(0).per_home_addr_country_other}');
			}
			
			$("#per_home_addr_state_val").val('${ChangeAdd.get(0).permanent_state}' );
			$('#per_home_addr_state').text($( "#per_home_addr_state_val option:selected" ).text());
			fn_per_home_addr_state();
			if('${ChangeAdd.get(0).per_home_addr_state_other}'!=null && '${ChangeAdd.get(0).per_home_addr_state_other}'!='' && ($('#per_home_addr_state').text().toUpperCase()==other || $('#per_home_addr_state').text().toUpperCase()==others)){
				$('#per_home_addr_state').text('${ChangeAdd.get(0).per_home_addr_state_other}');
			}
			
			$("#per_home_addr_district_val").val('${ChangeAdd.get(0).permanent_district}');
			$('#per_home_addr_district').text($( "#per_home_addr_district_val option:selected" ).text());
			fn_per_home_addr_district();
			if('${ChangeAdd.get(0).per_home_addr_district_other}'!=null && '${ChangeAdd.get(0).per_home_addr_district_other}'!='' && ($('#per_home_addr_district').text().toUpperCase()==other || $('#per_home_addr_district').text().toUpperCase()==others)){
				$('#per_home_addr_district').text('${ChangeAdd.get(0).per_home_addr_district_other}');
			}
			$("#per_home_addr_tehsil_val").val('${ChangeAdd.get(0).permanent_tehsil}' );
			$('#per_home_addr_tehsil').text($( "#per_home_addr_tehsil_val option:selected" ).text());
			if('${ChangeAdd.get(0).per_home_addr_tehsil_other}'!=null && '${ChangeAdd.get(0).per_home_addr_tehsil_other}'!='' && ($('#per_home_addr_tehsil').text().toUpperCase()==other || $('#per_home_addr_tehsil').text().toUpperCase()==others)){
				$('#per_home_addr_tehsil').text('${ChangeAdd.get(0).per_home_addr_tehsil_other}');
			}
			
			$("#per_home_addr_village").text('${ChangeAdd.get(0).permanent_village}');
			
			$("#per_home_addr_pin").text('${ChangeAdd.get(0).permanent_pin_code}' );
			$("#per_home_addr_rail").text('${ChangeAdd.get(0).permanent_near_railway_station}' );
			
            var permanent= '${ChangeAdd.get(0).permanent_rural_urban_semi}';
            if(permanent == "rural"){
                    $("#perm_home_addr").text("Rural");
             } 
            if(permanent == "urban"){
            	 $("#perm_home_addr").text("Urban");
             }
            if(permanent == "semi_urban")
            {
            	 $("#perm_home_addr").text("Semi Urban");
             }  
            var br= '${ChangeAdd.get(0).permanent_border_area}';
            if(br == "yes"){
                    $("#per_border_area").text("Yes");
             } 
            if(br == "no"){
            	$("#per_border_area").text("No");
             } 
	 
            $("#pers_addr_country_val").val('${ChangeAdd.get(0).present_country}' );
        	$('#pers_addr_country').text($( "#pers_addr_country_val option:selected" ).text());
        	if('${ChangeAdd.get(0).pers_addr_country_other}'!=null && '${ChangeAdd.get(0).pers_addr_country_other}'!='' && ($('#pers_addr_country').text().toUpperCase()==other || $('#pers_addr_country').text().toUpperCase()==others)){
				$('#pers_addr_country').text('${ChangeAdd.get(0).pers_addr_country_other}');
			}
        	fn_pers_addr_Country();
        	
			$("#pers_addr_state_val").val('${ChangeAdd.get(0).present_state}' );
			$('#pers_addr_state').text($("#pers_addr_state_val option:selected").text());
			if('${ChangeAdd.get(0).pers_addr_state_other}'!=null && '${ChangeAdd.get(0).pers_addr_state_other}'!='' && ($('#pers_addr_state').text().toUpperCase()==other || $('#pers_addr_state').text().toUpperCase()==others)){
				$('#pers_addr_state').text('${ChangeAdd.get(0).pers_addr_state_other}');
			}
			fn_pers_addr_state();
			$("#pers_addr_district_val").val('${ChangeAdd.get(0).present_district}' );
			$('#pers_addr_district').text($( "#pers_addr_district_val option:selected" ).text());
			if('${ChangeAdd.get(0).pers_addr_district_other}'!=null && '${ChangeAdd.get(0).pers_addr_district_other}'!='' && ($('#pers_addr_district').text().toUpperCase()==other || $('#pers_addr_district').text().toUpperCase()==others)){
				$('#pers_addr_district').text('${ChangeAdd.get(0).pers_addr_district_other}');
			}
			fn_pers_addr_district();
			$("#pers_addr_tehsil_val").val('${ChangeAdd.get(0).present_tehsil}');
			$('#pers_addr_tehsil').text($( "#pers_addr_tehsil_val option:selected" ).text());
			if('${ChangeAdd.get(0).pers_addr_tehsil_other}'!=null && '${ChangeAdd.get(0).pers_addr_tehsil_other}'!='' && ($('#pers_addr_tehsil').text().toUpperCase()==other || $('#pers_addr_tehsil').text().toUpperCase()==others)){
				$('#pers_addr_tehsil').text('${ChangeAdd.get(0).pers_addr_tehsil_other}');
			}
			
			$("#pers_addr_village").text('${ChangeAdd.get(0).present_village}' );
			
			$("#pers_addr_pin").text('${ChangeAdd.get(0).present_pin_code}' );
			$("#pers_addr_rail").text('${ChangeAdd.get(0).present_near_railway_station}' );
			var present='${ChangeAdd.get(0).present_rural_urban_semi}'  ;
			
			   if(present == "rural"){
                   $("#pers_addr_rural_urban").text("Rural");
	            } 
	           if(present == "urban"){
	           	 $("#pers_addr_rural_urban").text("Urban");
	            }
	           if(present == "semi_urban")
	           {
	           	 $("#pers_addr_rural_urban").text("Semi Urban");
	            } 


			$("#addr_ch_id").val('${ChangeAdd.get(0).id}' );
			if( '${ChangeAdd.get(0).spouse_country}' != 0){
				$("#spouse_addressInnerdiv").show();
				$("#spouse_addr_Country").val('${ChangeAdd.get(0).spouse_country}' );
				$('#spouse_addr_Countrylb').text($( "#spouse_addr_Country option:selected" ).text());
				fn_spouse_addr_Country();
				if('${ChangeAdd.get(0).spouse_addr_country_other}'!=null && '${ChangeAdd.get(0).spouse_addr_country_other}'!='' && ($('#spouse_addr_Countrylb').text().toUpperCase()==other || $('#spouse_addr_Countrylb').text().toUpperCase()==others)){
					$('#spouse_addr_Countrylb').text('${ChangeAdd.get(0).spouse_addr_country_other}');
				}
				$("#spouse_addr_state").val('${ChangeAdd.get(0).spouse_state}' );
				
				$('#spouse_addr_statelb').text($( "#spouse_addr_state option:selected" ).text());
				fn_spouse_addr_state();
				if('${ChangeAdd.get(0).spouse_addr_state_other}'!=null && '${ChangeAdd.get(0).spouse_addr_state_other}'!='' && ($('#spouse_addr_statelb').text().toUpperCase()==other || $('#spouse_addr_statelb').text().toUpperCase()==others)){
					$('#spouse_addr_statelb').text('${ChangeAdd.get(0).spouse_addr_state_other}');
				}
				$("#spouse_addr_district").val('${ChangeAdd.get(0).spouse_district}' );
				$('#spouse_addr_districtlb').text($( "#spouse_addr_district option:selected" ).text());
				fn_spouse_addr_district();
				if('${ChangeAdd.get(0).spouse_addr_district_other}'!=null && '${ChangeAdd.get(0).spouse_addr_district_other}'!='' && ($('#spouse_addr_districtlb').text().toUpperCase()==other || $('#spouse_addr_districtlb').text().toUpperCase()==others)){
					$('#spouse_addr_districtlb').text('${ChangeAdd.get(0).spouse_addr_district_other}');
				}
				$("#spouse_addr_tehsil").val('${ChangeAdd.get(0).spouse_tehsil}' );
				$('#spouse_addr_tehsillb').text($( "#spouse_addr_tehsil option:selected" ).text());
				if('${ChangeAdd.get(0).spouse_addr_tehsil_other}'!=null && '${ChangeAdd.get(0).spouse_addr_tehsil_other}'!='' && ($('#spouse_addr_tehsillb').text().toUpperCase()==other || $('#spouse_addr_tehsillb').text().toUpperCase()==others)){
					$('#spouse_addr_tehsillb').text('${ChangeAdd.get(0).spouse_addr_tehsil_other}');
				}
				$("#spouse_addr_village").text('${ChangeAdd.get(0).spouse_village}');
				$("#spouse_addr_pin").text('${ChangeAdd.get(0).spouse_pin_code}' );
				$("#spouse_addr_rail").text('${ChangeAdd.get(0).spouse_near_railway_station}' );
				$("#Stn_HQ_sus_no").text('${ChangeAdd.get(0).stn_hq_sus_no}');
				var sus_no = '${ChangeAdd.get(0).stn_hq_sus_no}' ;			      	
				 $.post("getTargetUnitNameListStnHQ?"+key+"="+value, {
						 sus_no:sus_no
		         }, function(j) {
		                
		         }).done(function(j) {
		        	 var length = j.length-1;
		             var enc = j[length].substring(0,16);
		             $("#Stn_HQ_unit_name").text(dec(enc,j[0]));   
		                 
		         }).fail(function(xhr, textStatus, errorThrown) {
	        	 });
				
				var spouse_rus='${ChangeAdd.get(0).spouse_rural_urban_semi}'  ;
				
				   if(spouse_rus == "rural"){
	                   $("#spouse_addr_rural_urban").text("Rural");
		            } 
		           if(spouse_rus == "urban"){
		           	 $("#spouse_addr_rural_urban").text("Urban");
		            }
		           if(spouse_rus == "semi_urban")
		           {
		           	 $("#spouse_addr_rural_urban").text("Semi Urban");
		            } 
			}
			
			</c:if>
		
}
function fn_spouse_addr_Country() {
	 var contry_id = $('select#spouse_addr_Country').val();

	 		 	$.post("getStateListFormcon1?"+key+"="+value, {contry_id:contry_id}).done(function(j) {
	 		 				 	
	 		 			 	
	 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
	 				for ( var i = 0; i < j.length; i++) {
	 				
	 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
	 					
	 				}
	 				
	 				$("select#spouse_addr_state").html(options);
					/* fn_spouse_addr_state();
					fn_spouse_addr_district(); */
	 			}).fail(function(xhr, textStatus, errorThrown) {
	 	});
}

function fn_spouse_addr_state() {
	 var state_id = $('select#spouse_addr_state').val();

	 		 	$.post("getDistrictListFormState1?"+key+"="+value, {state_id:state_id}).done(function(j) {
	 		 				 	
	 		 			 	
	 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
	 				for ( var i = 0; i < j.length; i++) {
	 					

	 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
	 				
	 				}
	 				
	 				$("select#spouse_addr_district").html(options);
					//fn_spouse_addr_district();
	 			}).fail(function(xhr, textStatus, errorThrown) {
	 	});
}

function fn_spouse_addr_district() {
	 var Dist_id = $('select#spouse_addr_district').val();

	 		 	$.post("getTehsilListFormDistrict1?"+key+"="+value, {Dist_id:Dist_id}).done(function(j) {
	 		 				 	
	 		 			 	
	 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
	 				for ( var i = 0; i < j.length; i++) {
	 				
	 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
	 					
	 				}
	 				
	 				$("select#spouse_addr_tehsil").html(options);

	 			}).fail(function(xhr, textStatus, errorThrown) {
	 	});
	 		 	
}

function fn_pre_domicile_Country() {
	 var contry_id = $('select#pre_country_val').val();

	 		 	$.post("getStateListFormcon1?"+key+"="+value, {contry_id:contry_id}).done(function(j) {
	 		 				 	
	 		 			 	
	 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
	 				for ( var i = 0; i < j.length; i++) {
	 				
	 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
	 					
	 				}
	 				
	 				$("select#pre_domicile_state_val").html(options);
	 				/* fn_pre_domicile_state();
	 				fn_pre_domicile_district(); */
	 			}).fail(function(xhr, textStatus, errorThrown) {
	 	});
}

function fn_pre_domicile_state() {
	 var state_id =$('select#pre_domicile_state_val').val();

	 		 	$.post("getDistrictListFormState1?"+key+"="+value, {state_id:state_id}).done(function(j) {
	 		 				 	
	 		 			 	
	 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
	 				for ( var i = 0; i < j.length; i++) {
	 				
	 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
	 					
	 				}
	 				
	 				$("select#pre_domicile_district_val").html(options);
	 				/* fn_pre_domicile_district(); */
	 			}).fail(function(xhr, textStatus, errorThrown) {
	 	});
}

function fn_pre_domicile_district() {
	 var Dist_id = $('select#pre_domicile_district_val').val();

	 		 	$.post("getTehsilListFormDistrict1?"+key+"="+value, {Dist_id:Dist_id}).done(function(j) {
	 		 				 	
	 		 			 	
	 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
	 				for ( var i = 0; i < j.length; i++) {
	 				
	 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
	 					
	 				}
	 				
	 				$("select#pre_domicile_tesil_val").html(options);

	 			}).fail(function(xhr, textStatus, errorThrown) {
	 	});
}

function fn_per_home_addr_Country() {
	 var contry_id = $('select#per_home_addr_country_val').val();

	 		 	$.post("getStateListFormcon1?"+key+"="+value, {contry_id:contry_id}).done(function(j) {
	 		 				 	
	 		 			 	
	 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
	 				for ( var i = 0; i < j.length; i++) {
	 				
	 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
	 					
	 				}
	 				
	 				$("select#per_home_addr_state_val").html(options);
	 				/* fn_per_home_addr_state();
	 				fn_per_home_addr_district(); */
	 			}).fail(function(xhr, textStatus, errorThrown) {
	 	});
}

function fn_per_home_addr_state() {
	 var state_id =$('select#per_home_addr_state_val').val(); 

	 		 	$.post("getDistrictListFormState1?"+key+"="+value, {state_id:state_id}).done(function(j) {
	 		 				 	
	 		 			 	
	 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
	 				for ( var i = 0; i < j.length; i++) {
	 			
	 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
	 					
	 				}
	 				
	 				$("select#per_home_addr_district_val").html(options);
	 				/* fn_per_home_addr_district(); */
	 			
	 			}).fail(function(xhr, textStatus, errorThrown) {
	 	});
}

function fn_per_home_addr_district() {
	 var Dist_id = $('select#per_home_addr_district_val').val();

	 		 	$.post("getTehsilListFormDistrict1?"+key+"="+value, {Dist_id:Dist_id}).done(function(j) {
	 		 				 	
	 		 			 	
	 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
	 				for ( var i = 0; i < j.length; i++) {
	 				
	 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
	 					
	 				}
	 				
	 				$("select#per_home_addr_tehsil_val").html(options);

	 			}).fail(function(xhr, textStatus, errorThrown) {
	 	});
}

function fn_pers_addr_Country() {
	 var contry_id = $('select#pers_addr_country_val').val();

	 		 	$.post("getStateListFormcon1?"+key+"="+value, {contry_id:contry_id}).done(function(j) {
	 		 				 	
	 		 			 	
	 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
	 				for ( var i = 0; i < j.length; i++) {
	 				
	 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
	 					
	 				}
	 				
	 				$("select#pers_addr_state_val").html(options);
					/* fn_pers_addr_state();
					fn_pers_addr_district(); */
	 			}).fail(function(xhr, textStatus, errorThrown) {
	 	});
}

function fn_pers_addr_state() {
	 var state_id = $('select#pers_addr_state_val').val();

	 		 	$.post("getDistrictListFormState1?"+key+"="+value, {state_id:state_id}).done(function(j) {
	 		 				 	
	 		 			 	
	 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
	 				for ( var i = 0; i < j.length; i++) {
	 					

	 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
	 				
	 				}
	 				
	 				$("select#pers_addr_district_val").html(options);
					/* fn_pers_addr_district(); */
	 			}).fail(function(xhr, textStatus, errorThrown) {
	 	});
}
function fn_pers_addr_district() {
	 var Dist_id = $('select#pers_addr_district_val').val();

	 		 	$.post("getTehsilListFormDistrict1?"+key+"="+value, {Dist_id:Dist_id}).done(function(j) {
	 		 				 	
	 		 			 	
	 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
	 				for ( var i = 0; i < j.length; i++) {
	 				
	 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
	 					
	 				}
	 				
	 				$("select#pers_addr_tehsil_val").html(options);

	 			}).fail(function(xhr, textStatus, errorThrown) {
	 	});
	 		 	
}





function fn_perm_home_addr_Country() {
	
	var contry_id = $('select#perm_home_addr_country_val').val();
	

	$
			.post("getStateListFormcon1?" + key + "=" + value, {
				contry_id : contry_id
			})
			.done(
					function(j) {
						var options = '<option   value="0">' + "--Select--"
								+ '</option>';
						for (var i = 0; i < j.length; i++) {

							options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
									+ j[i][1] + '</option>';

						}
						
						$("select#perm_home_addr_state_val").html(options);
						/* fn_prem_domicile_state();
						fn_prem_domicile_district(); */
					}).fail(function(xhr, textStatus, errorThrown) {
			});
}

function fn_prem_domicile_state() {
	var state_id = $('select#perm_home_addr_state_val').val();
	//alert("c---" + state_id);
	$
			.post("getDistrictListFormState1?" + key + "=" + value, {
				state_id : state_id
			})
			.done(
					function(j) {

						var options = '<option   value="0">' + "--Select--"
								+ '</option>';
						for (var i = 0; i < j.length; i++) {

							options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
									+ j[i][1] + '</option>';

						}

						$("select#perm_home_addr_district_val").html(options);
						//fn_prem_domicile_district();
					}).fail(function(xhr, textStatus, errorThrown) {
			});
}

function fn_prem_domicile_district() {
	
	var Dist_id = $('select#perm_home_addr_district_val').val();
	$
			.post("getTehsilListFormDistrict1?" + key + "=" + value, {
				Dist_id : Dist_id
			})
			.done(
					function(j) {

						var options = '<option   value="0">' + "--Select--"
								+ '</option>';
						for (var i = 0; i < j.length; i++) {

							options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
									+ j[i][1] + '</option>';

						}

						$("select#perm_home_addr_tehsil_val").html(options);

					}).fail(function(xhr, textStatus, errorThrown) {
			});
}





function per_address(){
	 var census_id='${census_id}'; 
	 var comm_id = '${comm_id}';

  /// if($("#check_per_address").prop('checked') == true){  
   	$.post('getPerAddressDataStatus1?' + key + "=" + value, {census_id:census_id,comm_id:comm_id}, function(j){
   	
   		if(j!=""){
   			$("#non_per_home_addr_country_val").val(j[0].permanent_country);
   			$('#non_per_home_addr_country').text($( "#non_per_home_addr_country_val option:selected" ).text());
   			
   			$("#non_perm_home_addr_state_val").val(j[0].permanent_state);
   			$('#non_perm_home_addr_state').text($( "#non_perm_home_addr_state_val option:selected" ).text());
   			
   			$("#non_perm_home_addr_district_val").val(j[0].permanent_district);
   			$('#non_perm_home_addr_district').text($( "#non_perm_home_addr_district_val option:selected" ).text());
   			
   			$("#non_perm_home_addr_tehsil_val").val(j[0].permanent_tehsil);
   			$('#non_perm_home_addr_tehsil').text($( "#non_perm_home_addr_tehsil_val option:selected" ).text());
   			
   			$("#non_perm_home_addr_village").text(j[0].permanent_village);
   			$("#non_perm_home_addr_pin").text(j[0].permanent_pin_code);
   			
   			$("#non_perm_home_addr_rail").text(j[0].permanent_near_railway_station);
   			 var permanent= j[0].permanent_rural_urban_semi;
               if(permanent == "rural"){
                       $("#non_perm_home_addr_rural").prop("checked", true);
                } 
               if(permanent == "urban"){
                       $("#non_perm_home_addr_urban").prop("checked", true);
                }
               if(permanent == "semi_urban")
               {
                       $("#non_perm_home_addr_semi_urban").prop("checked", true);
                }  
               var br= j[0].permanent_border_area;
               if(br == "yes"){
                       $("#non_per_border_area").prop("checked", true);
                } 
               if(br == "no"){
                       $("#non_per_border_area1").prop("checked", true);
                } 
   		}
	  });
   ///}
  
}

</script>
