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
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

<!-- <link rel="stylesheet" href="js/assets/css/bootstrap.min.css"> -->
<link rel="stylesheet" href="js/layout/css/animation.css">
<!-- <link rel="stylesheet" href="js/assets/scss/style.css">  -->
<link rel="stylesheet" href="js/assets/collapse/collapse.css">

<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>

<!-- resizable_col -->
<link rel="stylesheet" href="js/assets/adjestable_Col/jquery.resizableColumns.css">
<script src="js/assets/adjestable_Col/jquery.resizableColumns.js" type="text/javascript"></script>


<style>
.sticky {
    position: fixed;
    top: 0;
    z-index: 1000;
    padding-right: 35px;
}
.sticky + .subcontent {
  padding-top: 330px;
}
</style>

<div class="container-fluid" align="center">

	<form id="Comm_form">
		<div class="animated fadeIn">
			<div class="card" align="center">
				<div class="panel-group" id="accordion">
					<div class="panel panel-default" id="insp_div1">
						<div class="panel-heading">
							<h4 class="panel-title main_title" id="insp_div5">
								<b>UPDATE CIVILIAN DETAILS </b>
							</h4>
						</div>
						<div class="col-md-12"
							style="padding-top: 5px; padding-left: 250px;">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong style="color: red;">* </strong>Employee No</label>
									</div>
									<div class="col-md-8">
										<input type="text" id="personnel_no" name="personnel_no" class="form-control autocomplete" autocomplete="off">
										<input type="hidden" id="civ_id" name="civ_id" class="form-control autocomplete" autocomplete="off">
										<input type="hidden" id="status" name="status" value="0" class="form-control autocomplete" autocomplete="off">
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
</div>
<div id="div_update_data" style="display: none;" class="subcontent">
	<div id="maindetailsdiv">
		<div class="card">
			<div class="panel-group" id="accordion4">
				<div class="panel panel-default" id="app_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title red" id="a_div5">
							<a class="droparrow collapsed" data-toggle="collapse" data-parent="#accordion4" href="#" id="app_final" onclick="divN(this)"><b>RECORD</b></a>
						</h4>
					</div>
					<div id="collapse1app" class="panel-collapse collapse">
						<div class="card-body card-block"> <br>
							<div class="row">
								<div class="col-md-12">
									<div class="col-md-3">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong style="color: red;"></strong> Name</label>
											</div>
											<div class="col-md-8">
												<label class=" form-control-label" id="cadet_name"></label>
												<label class=" form-control-label" id="dob" style="display: none;"></label> 
												<input type="hidden" class=" form-control-label" id="dob_date">
												<input type="hidden" class=" form-control" id="comm_date">
												<label class=" form-control-label" id="marital_status_p" style="display: none;"></label>
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
												<input type="hidden" class=" form-control-label" id="tos_date">
											</div>
										</div>
									</div>
									<div class="col-md-3">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong style="color: red;"></strong> SUS No</label>
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
	<!-- Start PERSONNEL  -->
<form id="forminspupdate">
		<div class="card">
			<div class="panel-group" id="accordion2">
				<div class="panel panel-default" id="f_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title lightblue" id="f_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion2" href="#" id="f_final" onclick="divN(this)"
								><b>CHANGE IN MARITAL STATUS</b></a>
						</h4>
					</div>
					<div id="collapse1f" class="panel-collapse collapse">
						<div class="card-body card-block">
						<div align="left">
						<input type="button" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('marital_status');">
						</div>
						
							<div class="row" >

                            <div class="col-md-12" id="marr_quali_radioDiv" style="margin-top: 10px; width: 100%; margin-bottom: 16px;">
									<label class=" form-control-label" style="margin-right: 70px;"><h4>Event</h4></label> &nbsp
									<input type="radio" style="margin-right: 5px; transform: scale(1.3);"
    
										id="marr_quali_radio1" name="marr_quali_radio" value="yes"
										onchange="marrqualificationFn();">&nbsp <label for="yes">Change Marital Status</label>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
									<input type="radio" id="marr_quali_radio2" name="marr_quali_radio" style="margin-right: 5px; transform: scale(1.3);"
										value="no" onchange="marrqualificationFn();" >&nbsp
									<label for="no">Add/Update Spouse Qualification</label><br>

								</div>
                                
						<div class="col-md-12" id="marital_eventDiv">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">* </strong>Marital Event</label>
									</div>
									<div class="col-md-8">
									<select name="marital_event" id="marital_event" class="form-control"  onchange="marital_eventchange();" >
									                        <option value="0">--Select--</option>
															<c:forEach var="item" items="${getMarital_eventList}" varStatus="num">
															<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
															</c:forEach>
										</select>  			
									</div>
								</div>
							</div>
							<div class="col-md-6" style="display: none;" >
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">* </strong>Marital Status</label>
									</div>
									<div class="col-md-8">
								<select name="marital_status" id="marital_status" class="form-control"   >
									                        <option value="0">--Select--</option>
															<c:forEach var="item" items="${getMarital_statusList}" varStatus="num">
															<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
															</c:forEach>
															</select> 
									</div>
								</div>
							</div> 
						</div>
<div id="marriage_remarriageDiv" style="display:none">		
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong>Authority</label>
											</div>
											<div class="col-md-8">
										<input type="hidden" id="marr_ch_id" name="marr_ch_id"  value="0" class="form-control autocomplete" autocomplete="off" >											
										 <input type="text" id="marriage_authority" name="marriage_authority" class="form-control autocomplete" autocomplete="off" 
										  onkeyup="return specialcharecter(this)" maxlength="100">
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
											 <input type="text" name="marriage_date_of_authority" id="marriage_date_of_authority" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
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
						<input type="text" id="maiden_name" name="maiden_name" class="form-control autocomplete" autocomplete="off" 
						        maxlength="50" onkeypress="return onlyAlphabets(event);" oninput="this.value = this.value.toUpperCase()">
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
						<input type="text" name="marriage_date" id="marriage_date" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
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
								<input type="text" id="marriage_place_of_birth" name="marriage_place_of_birth" 
								class="form-control autocomplete" autocomplete="off" maxlength="50" onkeypress="return onlyAlphabets(event);">				         
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
					<select name="marriage_nationality" id="marriage_nationality" 
					onchange="fn_otherShowHide(this,'Spouse_nationalityDiv','spouseNationality_other')"
					class="form-control"   >
							                        <option value="0">--Select--</option>
													<c:forEach var="item" items="${getNationalityList}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
													</select> 
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12" id="Spouse_nationalityDiv" style="display:none">
					 
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"><strong
									style="color: red;">* </strong>Other Nationality</label>
							</div>
							<div class="col-md-8">
				       	<input type="text" id="spouseNationality_other" name="spouseNationality_other"
												class="form-control autocomplete" autocomplete="off"
											 onkeypress="return onlyAlphabets(event);" maxlength="50"
												oninput="this.value = this.value.toUpperCase()">
					
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
											<input type="text" name="marriage_date_of_birth" id="marriage_date_of_birth" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
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
									  <input type="text" id="marriage_adhar_no" name="marriage_adhar_no" class="form-control autocomplete" 
									      autocomplete="off" maxlength="14" onkeypress="return isAadhar(this,event);" >
											</div>
										</div>
									</div>
								</div>
								
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;"> </strong>Spouse PAN Card No</label>
											</div>
											<div class="col-md-8">
									  <input type="text" id="pan_card" name="pan_card" class="form-control autocomplete" 
									  autocomplete="off" maxlength="10" onchange="isPAN(this);" oninput="this.value = this.value.toUpperCase()" >
											</div>
										</div>
									</div>
									<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">* </strong>In Service/Ex-Service</label>
									</div>
									<div class="col-md-8">
										<input type="radio" id="spouse_ser_radio1"
											name="spouse_ser_radio" value="Yes"
											onchange="spouse_ServiceFn();">&nbsp <label
											for="yes">Yes</label>&nbsp <input type="radio"
											id="spouse_ser_radio2" name="spouse_ser_radio" value="No"
											onchange="spouse_ServiceFn();" checked="checked">&nbsp
										<label for="no">No</label><br>


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
											<select name="spouse_service" id="spouse_service"
												class="form-cont rol-sm form-control"
												onchange="Spouse_ServiceOtherFn('spouseSer_otherDiv','spouse_personalDiv',this)">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getExservicemenList}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>


										</div>
									</div>
								</div>
								<div class="col-md-6" id="spouseSer_otherDiv" style="display: none">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">*</strong>Other</label>
										</div>
										<div class="col-md-8">
											<input type="text" id="spouseSer_other" name="spouseSer_other"
                                                 class="form-control autocomplete" autocomplete="off"
                                                 onkeypress="return onlyAlphaNumeric(event);" maxlength="50"
                                                 oninput="this.value = this.value.toUpperCase()">


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
											<input type="text" id="spouse_personal_no"
												name="spouse_personal_no" class="form-control autocomplete" maxlength="9"
												autocomplete="off"
												onkeypress="return /[0-9a-zA-Z]/i.test(event.key)"
												oninput="this.value = this.value.toUpperCase()">


										</div>
									</div>
								</div>

							</div>
							<div class="col-md-12" id="divorceDiv" style="display:none">				
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label" id="divorce_widow_widower_dtlbl"><strong
														style="color: red;">* </strong>Date of Divorce</label>
												</div>
												<div class="col-md-8">
												<input type="text" name="divorce_date" id="divorce_date" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
												</div>
											</div>
										</div>
									</div>
									
									<div class="col-md-12" id="seperateDiv" style="display:none">				
										<div class="col-md-6" id="separated_from_dtDiv" style="display:none">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label" >Date of Separation</label>
												</div>
												<div class="col-md-8">
												<input type="text" name="separated_from_dt" id="separated_from_dt" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
												</div>
											</div>
										</div>
										<div class="col-md-6" id="separated_to_dtDiv" style="display:none">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label" ><strong
														style="color: red;"> </strong>Separation To Date</label>
												</div>
												<div class="col-md-8">
												<input type="text" name="separated_to_dt" id="separated_to_dt" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
												</div>
											</div>
										</div>
									</div>
							
						</div>								
								
						      
								<div class="col-md-12" id="spouse_quali_radioDiv" style=" width: 100%;">
									<label class=" form-control-label" style="margin-right: 100px;"><h4> Qualification Details of Spouse</h4><strong
														style="color: red;">Note:-Only Highest qualification should be fill.</strong></label> 
								<input type="radio"
										id="spouse_quali_radio1" name="spouse_quali_radio" value="yes"
										onchange="spouse_quali_radioFn();">&nbsp <label for="yes">Yes</label>&nbsp
									<input type="radio" id="spouse_quali_radio2" name="spouse_quali_radio"
										value="no" onchange="spouse_quali_radioFn();" checked="checked">&nbsp
									<label for="no">No</label><br>
								</div>
								
							<div id="spouse_Qualifications" style="margin-top: 20px; display:none">
							
							
								
								<div class="col-md-12" id="spouse_quali_labelDiv" style=" width: 100%; display: none">
									<label class=" form-control-label" style="margin-right: 100px;"><h4> Qualification Details of Spouse</h4><strong
														style="color: red;">Note:-Only Highest qualification should be fill.</strong></label> 							
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
															class=" form-control"
															onchange="fn_qualification_typeChange(this,'spouse_quali','quali_degree_spouse','spouse_specialization')">
															<option value="0">--Select--</option>
															<c:forEach var="item" items="${getQualificationTypeList}"
																varStatus="num">
																<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
															</c:forEach>

														</select> <input type="hidden" id="spouse_qualification_ch_id"
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
															class=" form-control"
															onchange="fn_ExaminationTypeChange(this,'quali_degree_spouse','spouse_specialization');fn_otherShowHide(this,'other_div_examSpouse','exam_otherSpouse')">
															<option value="0">--Select--</option>

														</select>
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
														<input type="text" id="exam_otherSpouse" onkeypress="return onlyAlphabets(event);" maxlength="50"
															name="exam_otherSpouse" class="form-control autocomplete"
															autocomplete="off">
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
															class=" form-control"
															onchange="fn_otherShowHide(this,'other_div_qualiDegSpouse','quali_deg_otherSpouse')">

															<option value="0">--Select--</option>

														</select>
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
														<select name="spouse_specialization"
															id="spouse_specialization" class="form-control"
															onchange="fn_otherShowHide(this,'other_div_qualiSpecSpouse','quali_spec_otherSpouse')">
															<option value="0">--Select--</option>
												<c:forEach var="item" items="${getSpecializationList}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
														</select>
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
														<input type="text" id="quali_deg_otherSpouse"
															name="quali_deg_otherSpuse" onkeypress="return onlyAlphabets(event);" maxlength="50"
															class="form-control autocomplete" autocomplete="off">
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
														<input type="text" id="quali_spec_otherSpouse"
															name="quali_spec_otherSpouse" onkeypress="return onlyAlphabets(event);" maxlength="50"
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
															style="color: red;">* </strong>Year of Passing</label>
													</div>
													<div class="col-md-8">

														<input type="text" id="spouse_yrOfPass"
															name="spouse_yrOfPass" class="form-control autocomplete"
															autocomplete="off" onkeypress="return isNumber(event)"
															maxlength="4" />
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
															class="form-control-sm form-control" onchange="fn_otherShowHide(this,'other_div_classSpouse','class_otherSpouse')">
															<option value="0">--Select--</option>
															<c:forEach var="item" items="${getDivclass}"
																varStatus="num">
																<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
															</c:forEach>
														</select>
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
														<input type="text" id="class_otherSpouse" name="class_otherSpouse"
															class="form-control autocomplete" autocomplete="off" 
															onkeypress="return onlyAlphabets(event);" maxlength="50">
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
														<div class="multiselect">

															<div class="selectBox" onclick="showCheckboxesSpouse()">
																<select name="spouse_sub_quali" id="spouse_sub_quali"
																	class="form-control-sm form-control">
																	<option>Select Subjects</option>
																</select>
																<div class="overSelect"></div>
															</div>
															<div id="spouse_checkboxes" class="checkboxes"
																style="max-height: 200px; overflow: auto;">
																<input type="text" id="spouse_searchSubject"
																	name="spouse_searchSubject"
																	placeholder="Search Subjects"
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

														<input type="text" id="spouse_institute_place"
															name="spouse_institute_place"
															class="form-control autocomplete" autocomplete="off"
															onkeypress="return onlyAlphabets(event);" maxlength="50"
															oninput="this.value = this.value.toUpperCase()">
													</div>
												</div>
												<div class="row form-group">
													<div class="col-md-4">
														<label class=" form-control-label"><strong
															style="color: red;"></strong>Selected Subject</label>
													</div>
													<div class="col-md-8">
														<div class="row">

															<div id="spouse_quali_sub_list"
																style="max-height: 200px; overflow: auto; width: 100%; border: 1px solid;">

															</div>

														</div>

													</div>
												</div>

											</div>


										</div>
										


							</div>
								</div>
									
							</div>
								<div class="card-footer" align="center" class="form-control"> 
										<input type="button" id="mrgbtn_save" class="btn btn-primary btn-sm" value="Submit for Approval" onclick="return married_save_fn();" >
										<input type="button" id="mrgqualibtn_save" class="btn btn-primary btn-sm" value="Submit for Approval" onclick="return spouse_qualification_save_fn();" style="display: none">		              
							   </div>
						</div>
					</div>
				</div>
			</div>
<!-- 		</div> -->
	</form>
	<!-- END PERSONNEL  -->

	<!-- START CADET -->
	<div class="card">
			<div class="panel-group" id="accordion3">
				<div class="panel panel-default" id="g_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title lightblue" id="g_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion3" href="#" id="g_final" onclick="divN(this)"
								><b>UPDATE CHILD DETAILS</b></a>
						</h4>
					</div>
					<div id="collapse1g" class="panel-collapse collapse">
						<div class="card-body card-block">
						<div align="left">
						<input type="button" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('update_child_dtl');">
						</div>
								<div class="card-body-header">
									<h5></h5>
								</div>
								<table id="m_children_details_table" class="table-bordered" style="margin-top: 10px; width: 100%;">
								<thead style="color: white; text-align: center;">
									<tr>
										<th>Sr No</th>
										<th><strong style="color: red;">* </strong>Name</th>
										<th><strong style="color: red;">* </strong>Date of Birth</th>
										<th>If Specially Abled Child </th>
										<th><strong style="color: red;">* </strong>Relationship</th>
										<th>If Adopted Child </th>
										<th><strong style="color: red;"> </strong>Adoption Date </th>
										<th><strong style="color: red;"> </strong>Aadhaar Card No </th>
										<th>PAN Card No </th>
										<th>Service/Ex-Service</th>
										<th><strong style="color: red;"></strong>Services</th>
										<th><strong style="color: red;"> </strong>Personal No.</th>
										<th>Other Service</th>
										<th>Action</th>
									</tr>
								</thead>
								<tbody data-spy="scroll" id="m_children_detailstbody" style="min-height: 46px; max-height: 650px; text-align: center;">
									<tr id="tr_children1">
										<td class="child_srno" readonly>1</td>
										<td>
										<input type="text" id="sib_name1" name="sib_name1"  class="form-control autocomplete" autocomplete="off" 
										maxlength="50" onkeypress="return onlyAlphabets(event);" oninput="this.value = this.value.toUpperCase()">
										</td>
										<td>
										 <input type="text" name="sib_date_of_birth1" id="sib_date_of_birth1" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY" >
										</td>
										<td>
										<input type="checkbox" id="sib_type1" name="sib_type1" value="Yes">
										</td>
										<td>
										<select name="sib_relationship1" id="sib_relationship1" class="form-control">
											<option value="0">--Select--</option>
											<c:forEach var="item" items="${getChild_RelationshipList}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select>
										</td>
										<td>
										<input type="checkbox" id="sib_adopted1" name="sib_adopted1" value="Yes" onclick="adoption(1);">
										</td>
										<td>
										<input type="text" name="sib_adop_date1" id="sib_adop_date1" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" readonly="readonly" class="form-control" style="width: 85%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY" >
										</td>
										<td>
										<input type="text" id="aadhar_no1" name="aadhar_no1"  class="form-control autocomplete" autocomplete="off" 
										         maxlength="14" onkeypress="return isAadhar(this,event);">
										</td>
										<td>
										<input type="text" id="pan_no1" name="pan_no1"  class="form-control autocomplete" autocomplete="off" 
										          maxlength="10" onchange="isPAN(this);" oninput="this.value = this.value.toUpperCase()">
										</td>
										<td style="display: none;">
										<input type="text" id="sib_ch_id1" name="sib_ch_id1" value="0" class="form-control autocomplete" autocomplete="off">
										</td>
										<td>
										<input type="checkbox" id="child-ex1" name="child-ex1" value="Yes" onclick="childCB(1);">
										</td>
										<td><select name="child_service1" id="child_service1" onchange="otherfunction(1)"
												class="form-control-sm form-control"
												>
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getExservicemenList}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select></td>
											<td><input type="text" id="child_personal_no1"
												name="child_personal_no1" class="form-control autocomplete"
												autocomplete="off" maxlength="9"
												onkeypress="return /[0-9a-zA-Z]/i.test(event.key)"
												oninput="this.value = this.value.toUpperCase()">
											</td>
											<td><input type="text" id="other_child_ser1"
												name="other_child_ser1" class="form-control autocomplete"
												autocomplete="off" maxlength="50"
												onkeypress="return onlyAlphaNumeric(event);"
												oninput="this.value = this.value.toUpperCase()">
											</td>
										<td>
										<a class="btn btn-primary btn-sm" value="SAVE" title="Submit for Approval" id="m_children_details_save1"
										   onclick="m_children_details_save_fn(1);"><i class="fa fa-save"></i>
										</a> 
										<a style="display: none;" id="m_children_details_add1" class="btn btn-success btn-sm" 
										value="ADD" title="ADD" onclick="m_children_details_add_fn(1);">
										<i class="fa fa-plus"></i>
										</a>
										<a style="display: none;" id="m_children_details_remove1" class="btn btn-danger btn-sm" value="REMOVE" title="REMOVE"
										onclick="m_children_details_remove_fn(1);"><i class="fa fa-trash"></i>
										</a>
										</td>
									</tr>
								</tbody>
							</table>
							</div>
						</div>
						
							  
					</div>
				</div>
			</div>
	<!-- END CADET -->
	<!-- START COURSE -->
<form id="form_nok_addr_details_form">
		<div class="card">
			<div class="panel-group" id="accordion10">
				<div class="panel panel-default" id="h_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title lightblue" id="h_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion10" href="#" id="h_final" onclick="divN(this)"
								><b>CHANGE IN NOK</b></a>
						</h4>
					</div>
					<div id="collapse1h" class="panel-collapse collapse">
						<div class="card-body card-block">
						<div align="left">
						<input type="button" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('change_nok');">
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
						                   <input type="text" id="authority" name="authority" class="form-control autocomplete" 
						                   autocomplete="off" maxlength="100" 	onkeyup="return specialcharecter(this)"  > 						                   
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;"> </strong> Date of Authority</label>
						                </div>
						                <div class="col-md-8">
						                <%--  <input type="date" id="date_authority" name="date_authority"  max="${date}" min='1899-01-01' class="form-control autocomplete" autocomplete="off" maxlength="10"> --%> 						                     
						                  <input type="text" name="date_authority" id="date_authority" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
						                
						                </div>
						            </div>
	              				</div>
	              			</div>
	              				<div class="col-md-12">	
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Name of NOK</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="nok_name" name="nok_name" class="form-control autocomplete"
						                    autocomplete="off" maxlength="50" onkeypress="return onlyAlphabets(event);"
											oninput="this.value = this.value.toUpperCase()"> 						                   
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Relation</label>
						                </div>
						                <div class="col-md-8">
						                 <input type="hidden" id="nok_id" name="nok_id" value="0" class="form-control autocomplete" autocomplete="off" > 							                 
						                 <select name="nok_relation" id="nok_relation" class="form-control"   >
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getRelationList}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>											
											</select> 						                     
						                </div>
						            </div>
	              				</div>
	              			</div>
	              				<div class="col-md-12">
		           			<label class=" form-control-label"  style="margin-left:10px;"><h4> NOK's Address</h4></label>
		          		 </div> 
		          		 		<div class="col-md-12">	
						<div class="col-md-6">
              					<div class="row form-group">
					               <div class="col-md-4">
					                    <label class=" form-control-label"><strong style="color: red;">* </strong>COUNTRY</label>
					                </div>
					                <div class="col-md-8">
					                <select name="nok_country" id="nok_country" class="form-control"   onchange="fn_nok_Country();">
						                         <option value="0">--Select--</option>
												<c:forEach var="item" items="${getMedCountryName}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</SELECT> 						               				               
					                </div>
					            </div>
              				</div>
              				<div class="col-md-6">
              				<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> STATE</label>
						                </div>
						                <div class="col-md-8">
						                	<select name="nok_state" id="nok_state" class="form-control"  onchange="fn_nok_state();" >
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
								<div class="col-md-6" id = "nok_other_id" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">COUNTRY OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="ctry_other" name="ctry_other" class="form-control autocomplete" 
					                	autocomplete="off" onkeypress="return onlyAlphabets(event);" maxlength="50">
					                	 </div>
						            </div>
	              				</div>
	              				<div class="col-md-6" id = "nok_other_st" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">STATE OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="st_other" name="st_other" class="form-control autocomplete" 
					                	autocomplete="off" onkeypress="return onlyAlphabets(event);" maxlength="50">
					                	 </div>
						            </div>
	              				</div>
	              					</div>
	              			 <div class="col-md-12">	
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> DISTRICT</label>
						                </div>
						                <div class="col-md-8">
						                <select name="nok_district" id="nok_district" class="form-control"  onchange="fn_nok_district();" >
					                		<option value="0">--Select--</option>
											<c:forEach var="item" items="${getMedDistrictName}" varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select> 
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> TEHSIL</label>
						                </div>
						                <div class="col-md-8">
						                 <select name="nok_tehsil" id="nok_tehsil" class="form-control"   onchange="fn_nok_tehsil();" >
					                		 <option value="0">--Select--</option>
											<c:forEach var="item" items="${getMedCityName}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select>
						                </div>
						            </div>
	              				</div>
	              			</div>	
	              			<div class="col-md-12">
								<div class="col-md-6" id = "nok_other_dist" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">DISTRICT OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="dist_other" name="dist_other" class="form-control autocomplete" 
					                	autocomplete="off" onkeypress="return onlyAlphabets(event);" maxlength="50">
					                	 </div>
						            </div>
	              				</div>
	              				<div class="col-md-6" id = "nok_other_te" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">TEHSIL OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="nok_tehsil_other" name="nok_tehsil_other" class="form-control autocomplete" 
					                	autocomplete="off" 	onkeypress="return onlyAlphabets(event);" maxlength="50">
					                	 </div>
						            </div>
	              				</div>
	              					</div>
	              				<div class="col-md-12">	
	              			<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Village/Town/City</label>
						                </div>
						                <div class="col-md-8">
											<input type="text" id="nok_village" name="nok_village" class="form-control autocomplete" 
											autocomplete="off"  onkeypress="return onlyAlphabets(event);" maxlength="50">			               
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Pin</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="nok_pin" name="nok_pin" class="form-control autocomplete" autocomplete="off" 
						                   onkeypress="return isNumber0_9Only(event)" maxlength="6"> 						                   
						                </div>
						            </div>
	              				</div>
	              			</div>
	              			  			<div class="col-md-12">	
	              			<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Nearest Railway Station</label>
						                </div>
						                <div class="col-md-8">
						                 <input type="text" id="nok_rail" name="nok_rail" class="form-control autocomplete" autocomplete="off"
						                  maxlength="100" 	onkeypress="return onlyAlphaNumeric(event);"> 						                     
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						             <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Is  Rural /Urban/Semi Urban</label>
						                </div>
						                <div class="col-md-8">
						     <label for="nok_rural">
							 <input type="radio" id="nok_rural" name="nok_rural_urban" value="rural" > Rural</label> 
					    	 <label for="nok_urban" >
							 <input type="radio" id="nok_urban" name="nok_rural_urban" value="urban" > Urban</label> 
					    	 <label for="nok_semi_urban">
							 <input type="radio" id="nok_semi_urban" name="nok_rural_urban" value="semi_urban" >Semi Urban</label> 
						                </div>
						            </div>
	              				</div>	
	              				</div>
	              			<div class="col-md-12">	
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Nok Mobile No</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="nok_mobile_no" name="nok_mobile_no" class="form-control autocomplete" 
						                   autocomplete="off" onkeypress="return isNumber0_9Only(event)" maxlength="10"> 						                   
						                </div>
						            </div>
	              				</div>
	              			</div>
							</div>
					    <div class="card-footer" align="center" class="form-control">
		              		<input type="button" class="btn btn-primary btn-sm" value="Submit for Approval" onclick="validate_save_nok_details();">		              
			           
			            </div> 
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
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
						<input type="button" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('change_des');">
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
						               <input type="hidden" id="des_id" name="des_id" value="0" class="form-control autocomplete" autocomplete="off" > 							                 
						                   
						                   <input type="text" id="des_authority" name="des_authority" class="form-control autocomplete" maxlength="100" autocomplete="off" maxlength="50" onkeyup="return specialcharecter(this)"> 						                   
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;"> </strong> Date of Authority</label>
						                </div>
						                <div class="col-md-8">
						                <%--  <input type="date" id="date_authority" name="date_authority"  max="${date}" min='1899-01-01' class="form-control autocomplete" autocomplete="off" maxlength="10"> --%> 						                     
						                  <input type="text" name="date_authority_des" id="date_authority_des" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
						                
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
									<select name="designation" id="designation"
										class="form-control-sm form-control">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getDesignationList}"
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
										style="color: red;"> *</strong> Date of Designation</label>
								</div>
								<div class="col-md-8">
									<input type="text" name="designation_date"
										id="dt_of_designation" maxlength="10"
										onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control"
										style="width: 93%; display: inline;"
										onfocus="this.style.color='#000000'"
										onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
										onkeyup="clickclear(this, 'DD/MM/YYYY')"
										onchange="clickrecall(this,'DD/MM/YYYY');"
										aria-required="true" autocomplete="off"
										style="color: rgb(0, 0, 0);">
								</div>
							</div>
						</div>
					</div>	
	              				
	              			
							</div>
					    <div class="card-footer" align="center" class="form-control">
		              		<input type="button" class="btn btn-primary btn-sm" value="Submit for Approval" onclick="validate_save_designation_details();">		              
			           
			            </div> 
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>	
	
	
	<!-- START TENURE -->
	
	<form id="form_tenure_details_form">
		<div class="card">
			<div class="panel-group" id="accordion8">
				<div class="panel panel-default" id="j_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title lightgreen" id="j_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion8" href="#" id="j_final" onclick="divN(this)"
								><b>CHANGE IN TENURE DATA</b></a>
						</h4>
					</div>
					<div id="collapse1j" class="panel-collapse collapse">
						<div class="card-body card-block">
						<div align="left">
						<input type="button" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('change_tenure');">
						</div>
						<br>
							<div class="row">
							<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Unit SUS No </label>
						                </div>
						                <div class="col-md-8">
						                <input type="hidden" id="ten_id" name="ten_id" value="0" class="form-control autocomplete" autocomplete="off" > 							                 
						                
						                   <input type="text" id="unit_sus_no" name="unit_sus_no" class="form-control autocomplete" autocomplete="off" maxlength="8" onkeypress="return AvoidSpace(event)"  onkeyup="return specialcharecter(this)" > 
						                </div>
						            </div>
	              				</div>
	              				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Unit Posted To </label>
						                </div>
						                <div class="col-md-8">
						                  <input type="text" id="unit_posted_to" name="unit_posted_to" class="form-control autocomplete" autocomplete="off" maxlength="50" onkeyup="return specialcharecter(this)">
						                </div>
						            </div>
	              				</div>
	              			</div>
	              			<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong> From</label>
										</div>
										<div class="col-md-8">
											<input type="text" name="ten_from" id="ten_from" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong> To</label>
										</div>
										<div class="col-md-8">
										 <input type="text" name="ten_to" id="ten_to" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY" >
										</div>
									</div>
								</div>
							</div>
	              				
	              			
							</div>
					    <div class="card-footer" align="center" class="form-control">
		              		<input type="button" class="btn btn-primary btn-sm" value="Submit for Approval" onclick="validate_save_tenure_details();">		              
			           
			            </div> 
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	
	<!-- END TENURE -->
	
<form id="form_addr_details_form">
		<div class="card">
			<div class="panel-group" id="accordion9">
				<div class="panel panel-default" id="i_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title lightblue" id="i_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion9" href="#" id="i_final" onclick="divN(this)"
								><b>CHANGE IN ADDRESS</b></a>
						</h4>
					</div>
					<div id="collapse1i" class="panel-collapse collapse">
						<div class="card-body card-block">
						<br>
						 <input type="button" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('change_in_address');">
							<div class="row" >
									<div class="col-md-12">	
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;"> </strong>Authority</label>
						                </div>
						                <div class="col-md-8">
						                 <input type="text" name="authority" id="addr_authority" class="form-control autocomplete" 
						                 autocomplete="off" maxlength="100" onkeyup="return specialcharecter(this)" >					               
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;"> </strong>Date of Authority</label>
						                </div>
						                <div class="col-md-8">
						                  <input type="text" name="date_authority" id="addr_date_authority" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
						                </div>
						            </div>
	              				</div>
	              			</div>	
							
							<label class=" form-control-label" style="margin-left:10px;"><h4> Present Domicile</h4></label>
							<div class="col-md-12">	
		 				<div class="col-md-6">
              					<div class="row form-group">
					               <div class="col-md-4">
					                    <label class=" form-control-label"><strong style="color: red;">* </strong>COUNTRY</label>
					                </div>
					                <div class="col-md-8">
					               						                   
					                <select name="pre_country" id="pre_country" class="form-control"   onchange="fn_pre_domicile_Country()">
						                          <option value="0">--Select--</option>
												<c:forEach var="item" items="${getMedCountryName}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select> 
					                </div>
					            </div>
              				</div>
              				<div class="col-md-6">
              					<div class="row form-group">
					               <div class="col-md-4">
					                    <label class=" form-control-label"><strong style="color: red;">* </strong> STATE</label>
					                </div>
					                <div class="col-md-8">
						                 <input type="hidden" id="addr_ch_id" name="addr_ch_id" value="0" class="form-control autocomplete" autocomplete="off" > 							                     
					               			<select name="pre_domicile_state" id="pre_domicile_state" class="form-control" onchange="fn_pre_domicile_state();" >
						                        <option value="0">--Select--</option>
												<c:forEach var="item" items="${getMedStateName}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</SELECT> 						               				               
					                </div>
					            </div>
              				</div>
	              			</div>	
	              			<div class="col-md-12">
								<div class="col-md-6" id = "add_other_id" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">COUNTRY OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="pre_country_other" name="pre_country_other" class="form-control autocomplete" 
					                	autocomplete="off" onkeypress="return onlyAlphabets(event);" maxlength="50">
					                	 </div>
						            </div>
	              				</div>
	              				<div class="col-md-6" id = "add_other_st" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">STATE OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="pre_domicile_state_other" name="pre_domicile_state_other" 
					                	class="form-control autocomplete" autocomplete="off" onkeypress="return onlyAlphabets(event);" maxlength="50">
					                	 </div>
						            </div>
	              				</div>
	              					</div>
	              			<div class="col-md-12">	
              			 <div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> DISTRICT</label>
						                </div>
						                <div class="col-md-8">
						                 <select name="pre_domicile_district" id="pre_domicile_district" class="form-control"  onchange="fn_pre_domicile_district();" >
					                		<option value="0">--Select--</option>
											<c:forEach var="item" items="${getMedDistrictName}" varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select>						               
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
              					<div class="row form-group">
					               <div class="col-md-4">
					                    <label class=" form-control-label"><strong style="color: red;">* </strong> TEHSIL</label>
					                </div>
					                <div class="col-md-8">
					               			<select name="pre_domicile_tesil" id="pre_domicile_tesil" class="form-control"  onchange="fn_pre_domicile_tesil();" >
						                        <option value="0">--Select--</option>
											<c:forEach var="item" items="${getMedCityName}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
											</SELECT> 						               				               
					                </div>
					            </div>
              				</div>
	              			</div>	
	              			<div class="col-md-12">
								<div class="col-md-6" id = "add_other_dt" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">DISTRICT OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="pre_domicile_district_other" name="pre_domicile_district_other"
					                	 class="form-control autocomplete" autocomplete="off" onkeypress="return onlyAlphabets(event);" maxlength="50">
					                	 </div>
						            </div>
	              				</div>
	              				<div class="col-md-6" id = "add_other_te" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">TEHSIL OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="pre_domicile_tesil_other" name="pre_domicile_tesil_other" class="form-control autocomplete" 
					                	autocomplete="off" onkeypress="return onlyAlphabets(event);" maxlength="50">
					                	 </div>
						            </div>
	              				</div>
	              					</div>
	              		
	              			<label class=" form-control-label" style="margin-left:10px;"><h4> Permanent Address</h4></label>
	              					<div class="col-md-12">	
						<div class="col-md-6">
              					<div class="row form-group">
					               <div class="col-md-4">
					                    <label class=" form-control-label"><strong style="color: red;">* </strong>COUNTRY</label>
					                </div>
					                <div class="col-md-8">
					                <select name="per_home_addr_country" id="per_home_addr_country" class="form-control"  onchange="fn_per_home_addr_Country();" >
						                        <option value="0">--Select--</option>
												<c:forEach var="item" items="${getMedCountryName}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select> 
					                </div>
					            </div>
              				</div>
              				<div class="col-md-6">
              				<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> STATE</label>
						                </div>
						                <div class="col-md-8">
						                	<select name="per_home_addr_state" id="per_home_addr_state" class="form-control"  onchange="fn_per_home_addr_state();" >
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
								<div class="col-md-6" id = "per_home_addr_country_other_hid" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">COUNTRY OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="per_home_addr_country_others" name="per_home_addr_country_others"
					                	 class="form-control autocomplete" autocomplete="off" onkeypress="return onlyAlphabets(event);" maxlength="50">
					                	 </div>
						            </div>
	              				</div>
	              				<div class="col-md-6" id = "per_home_addr_state_other_hid" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">STATE OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="per_home_addr_state_others" name="per_home_addr_state_others" class="form-control autocomplete"
					                	 autocomplete="off" onkeypress="return onlyAlphabets(event);" maxlength="50">
					                	 </div>
						            </div>
	              				</div>
	              					</div>
	              				<div class="col-md-12">	
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> DISTRICT</label>
						                </div>
						                <div class="col-md-8">
						                <select name="per_home_addr_district" id="per_home_addr_district" class="form-control"   onchange="fn_per_home_addr_district();">
					                		<option value="0">--Select--</option>
											<c:forEach var="item" items="${getMedDistrictName}" varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select> 
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> TEHSIL</label>
						                </div>
						                <div class="col-md-8">
						                 <select name="per_home_addr_tehsil" id="per_home_addr_tehsil" class="form-control" onchange="fn_per_home_addr_tehsil();"  >
					                		<option value="0">--Select--</option>
											<c:forEach var="item" items="${getMedCityName}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select>
						                </div>
						            </div>
	              				</div>
	              			</div>	
	              			<div class="col-md-12">
								<div class="col-md-6" id = "per_home_addr_district_other_hid" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">DISTRICT OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="per_home_addr_district_others" name="per_home_addr_district_others" 
					                	class="form-control autocomplete" autocomplete="off" onkeypress="return onlyAlphabets(event);" maxlength="50">
					                	 </div>
						            </div>
	              				</div>
	              				<div class="col-md-6" id = "per_home_addr_tehsil_other_hid" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">TEHSIL OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="per_home_addr_tehsil_others" name="per_home_addr_tehsil_others" class="form-control autocomplete"
					                	 autocomplete="off" onkeypress="return onlyAlphabets(event);" maxlength="50">
					                	 </div>
						            </div>
	              				</div>
	              					</div>
	              				<div class="col-md-12">	
	              			<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Village/Town/City</label>
						                </div>
						                <div class="col-md-8">
											<input type="text" id="per_home_addr_village" name="per_home_addr_village" 	onkeypress="return onlyAlphabets(event);" 
											class="form-control autocomplete" autocomplete="off"  maxlength="50">				               
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Pin</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="per_home_addr_pin" name="per_home_addr_pin" class="form-control autocomplete" 
						                   autocomplete="off"  onkeypress="return isNumber0_9Only(event)" maxlength="6"> 						                   
						                </div>
						            </div>
	              				</div>
	              			</div>
	              					<div class="col-md-12">	
	              			<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Nearest Railway Station</label>
						                </div>
						                <div class="col-md-8">
						                 <input type="text" id="per_home_addr_rail" name="per_home_addr_rail" 	onkeypress="return onlyAlphaNumeric(event);" 
						                 class="form-control autocomplete" autocomplete="off" maxlength="100"> 						                     
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						             <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Is  Rural /Urban/Semi Urban</label>
						                </div>
						                <div class="col-md-8">
						     <label for="lbl_per_home_addr_rural">
							 <input type="radio" id="per_home_addr_rural" name="per_home_addr_rural_urban" value="rural" > Rural</label> 
					    	 <label for="lbl_per_home_addr_urban" >
							 <input type="radio" id="per_home_addr_urban" name="per_home_addr_rural_urban" value="urban" > Urban</label> 
					    	 <label for="lbl_per_home_addr_semi_urban">
							 <input type="radio" id="per_home_addr_semi_urban" name="per_home_addr_rural_urban" value="semi_urban" >Semi Urban</label> 
						                </div>
						            </div>
	              				</div> 	
	              				</div>
	              		<div class="col-md-12">	  
	            			<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Border area</label>
						                </div>
						                <div class="col-md-8">
						                	 <label for="border_area">
							 <input type="radio" id="border_area" name="border_area" value="yes"   >Yes</label> 
					    	 <label for="border_area1"> 
				      		 <input type="radio" id="border_area1" name="border_area" value="no" > No</label>
						</div>
						                </div>
						            </div>
	              				</div>
	              			
						           		<div class="col-md-12"></div>
		 
			  <div class="col-md-12">
			 	<label class=" form-control-label"  style="margin-left:10px;"><h4> Present Address</h4></label>
			 </div> 
			 	<div class="col-md-12" style="font-size: 15px;">	
           		<input type="checkbox" id="check_address" name="check_address" onclick="copy_address()">
               	<label for="text-input" class=" form-control-label" style="color: #dd1a3e; margin-left:10px;">  Same as Permanent Address </label>
            </div>
			 	 <div class="col-md-12">	
						<div class="col-md-6">
              					<div class="row form-group">
					               <div class="col-md-4">
					                    <label class=" form-control-label"><strong style="color: red;">* </strong>COUNTRY</label>
					                </div>
					                <div class="col-md-8">
					               						                   
					                <select name="pers_addr_country" id="pers_addr_country" class="form-control" onchange="fn_pers_addr_Country();"  >
						                          <option value="0">--Select--</option>
												<c:forEach var="item" items="${getMedCountryName}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select> 
					                </div>
					            </div>
              				</div>
              				<div class="col-md-6">
              				<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> STATE</label>
						                </div>
						                <div class="col-md-8">
						                	<select name="pers_addr_state" id="pers_addr_state" class="form-control"  onchange="fn_pers_addr_state();" >
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
								<div class="col-md-6" id = "pers_addr_country_other_hid" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">COUNTRY OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="pers_addr_country_other" name="pers_addr_country_other" class="form-control autocomplete" 
					                	autocomplete="off" onkeypress="return onlyAlphabets(event);" maxlength="50">
					                	 </div>
						            </div>
	              				</div>
	              				<div class="col-md-6" id = "pers_addr_state_other_hid" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">STATE OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="pers_addr_state_other" name="pers_addr_state_other" class="form-control autocomplete" 
					                	autocomplete="off" onkeypress="return onlyAlphabets(event);" maxlength="50">
					                	 </div>
						            </div>
	              				</div>
	              					</div>
	              			 <div class="col-md-12">	
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> DISTRICT</label>
						                </div>
						                <div class="col-md-8">
						                <select name="pers_addr_district" id="pers_addr_district" class="form-control"  onchange="fn_pers_addr_district();" >
					                		<option value="0">--Select--</option>
											<c:forEach var="item" items="${getMedDistrictName}" varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select> 
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> TEHSIL</label>
						                </div>
						                <div class="col-md-8">
						                 <select name="pers_addr_tehsil" id="pers_addr_tehsil" class="form-control"   onchange="fn_pers_addr_tehsil();">
					                		<option value="0">--Select--</option>
											<c:forEach var="item" items="${getMedCityName}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select>
						                </div>
						            </div>
	              				</div>
	              			</div>	
	              			<div class="col-md-12">
							<div class="col-md-6" id = "pers_addr_district_other_hid" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">DISTRICT OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="pers_addr_district_other" name="pers_addr_district_other" class="form-control autocomplete"
					                	 autocomplete="off" onkeypress="return onlyAlphabets(event);" maxlength="50">
					                	 </div>
						            </div>
	              				</div>
	              				<div class="col-md-6" id = "pers_addr_tehsil_other_hid" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">TEHSIL OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="pers_addr_tehsil_other" name="pers_addr_tehsil_other" class="form-control autocomplete"
					                	 autocomplete="off" onkeypress="return onlyAlphabets(event);" maxlength="50">
					                	 </div>
						            </div>
	              				</div>
	              					</div>
	              					<div class="col-md-12">	
	              			<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Village/Town/City</label>
						                </div>
						                <div class="col-md-8">
											<input type="text" id="pers_addr_village" name="pers_addr_village" 	onkeypress="return onlyAlphabets(event);" 
											class="form-control autocomplete" autocomplete="off"  maxlength="50">
				               
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Pin</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="pers_addr_pin" name="pers_addr_pin" class="form-control autocomplete" 
						                   autocomplete="off" onkeypress="return isNumber0_9Only(event)" maxlength="6"> 						                   
						                </div>
						            </div>
	              				</div>
	              			</div>
	              				<div class="col-md-12">	
	              			<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Nearest Railway Station</label>
						                </div>
						                <div class="col-md-8">
						                 <input type="text" id="pers_addr_rail" name="pers_addr_rail" 	onkeypress="return onlyAlphaNumeric(event);"
						                 class="form-control autocomplete" autocomplete="off"  maxlength="100"> 						                     
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						             <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Is  Rural /Urban/Semi Urban</label>
						                </div>
						                <div class="col-md-8">
						     <label for="pers_addr_rural">
							 <input type="radio" id="pers_addr_rural" name="pers_addr_rural_urban" value="rural" > Rural</label> 
					    	 <label for="pers_addr_urban" >
							 <input type="radio" id="pers_addr_urban" name="pers_addr_rural_urban" value="urban" > Urban</label> 
					    	 <label for="pers_addr_semi_urban">
							 <input type="radio" id="pers_addr_semi_urban" name="pers_addr_rural_urban" value="semi_urban" >Semi Urban</label> 
						                </div>
						            </div>
	              				</div>	
	              				</div>
	              				
	              				
	              					
							</div>
						<div class="card-footer" align="center" class="form-control">
		              		<input type="button" class="btn btn-primary btn-sm" value="Submit for Approval" onclick="validate_changeaddress_details_save();">		              
			            </div> 
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	<!-- END Gender -->

</div>
<script type="text/javascript">
$(document).ready(function() {
	
 	$('#civ_id').val('${id}');
  	$('#personnel_no').val('${emp_no2}');
  	 $("input#personnel_no").attr('readonly', true);
  	personal_number();
  	$("#submit_a").hide();
  	 
	
});


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
		
		
		if (obj.id == "g_final") {
			if(!hasC){
				m_children_Details();
				colAdj("m_children_details_table");
			}
		}
		else if (obj.id == "i_final") {
			if(!hasC){
				get_changeaddress_details();
			}
		}
		else if (obj.id == "l_final") {
			if(!hasC){
				getQualifications();
			}
		}
		else if (obj.id == "f_final") {
			if(!hasC){
				$("#spouse_quali_radioDiv").hide();
				getfamily_marriageDetails();
				getSpouseQualificationData();
			}
		}
		else if (obj.id == "d_final") {
			if(!hasC){
				get_changedesignation_details();
			}
		}
		else if (obj.id == "h_final") {
			if(!hasC){
				get_nokaddress_details();
			}
		}
		else if (obj.id == "j_final") {
			if(!hasC){
				get_changetenure_details();
			}
		}
		
}

function Pop_Up_History(a) {

	var x = screen.width/2 - 1100/2;
    var y = screen.height/2 - 900/2;
    popupWindow = window.open("", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');
	window.onfocus = function () { 
	}
	civ_id = $("#civ_id").val();
	

	
	
	if(a == "update_child_dtl"){
		$("#updatechild_civ_id").val(civ_id);
		document.getElementById('update_child_dtlForm').submit();
	}
	
	if(a == "change_nok"){
		$("#nok_civ_id").val(civ_id);
		document.getElementById('nok_updateForm').submit();
	}
	if(a == "change_des"){
		$("#des_civ_id").val(civ_id);
		document.getElementById('des_updateForm').submit();
	}
	
	
	

	if(a == "marital_status"){
		$("#maritalstatus_civ_id").val(civ_id);
		$("#Change_In_Marital_Status_Form").submit();
	}
	
	
	if(a == "Update_Qualification"){
		$("#update_qualification_civ_id").val(civ_id);
		document.getElementById('Update_Qualification_Form').submit();
	}
	
	
	if(a == "change_in_address"){
		$("#address_civ_id").val(civ_id);
		document.getElementById('change_in_addressForm').submit();
	}

	if(a == "change_tenure"){
		$("#ten_civ_id").val(civ_id);
		document.getElementById('ten_updateForm').submit();
	}
	

		
}


function SetMin(){
	if ($("input#comm_date").val() != "") {
		var comm_dt = $("input#comm_date").val();
		setMinDate('date_of_seniority', comm_dt);
		setMinDate('date_of_tos', comm_dt);
		setMinDate('date_of_rank', comm_dt);
	}
	if ($("input#date_of_commission").val() != "") {
		var comm_dt = ParseDateColumncommission($("input#date_of_commission").val());
		setMinDate('date_of_seniority', comm_dt);
		setMinDate('date_of_tos', comm_dt);
		setMinDate('date_of_rank', comm_dt);
	}
}
</script>


<script>

/* Personal No */
var curr_marital_status=0;
function personal_number()
{

	if($("input#personnel_no").val()==""){
		alert("Please select Employee No");
		$("input#personnel_no").focus();
		return false;
	}
	
	else{
		$("#div_update_data").show();
	}
	 var personnel_no = $("#personnel_no").val();
	
 $.post('GetEmpNoData12?' + key + "=" + value,{ personnel_no : personnel_no },function(j) {
		 
		 if(j!=""){
			 
	    	$("#civ_id").val(j[0][0]);
	    	
	    	
	    	var civ_id =j[0][0]; 
	    	
	    	 $.post('GetCivDataApprove_civdata?' + key + "=" + value,{ civ_id : civ_id },function(k) {
	    		 
	    		 if(k.length > 0)
	    		 {
	    			
	    			  curr_marital_status=k[0].marital_status; 
	    			  
	    			  $('#marital_event').val('0');
	    			  $('#marriage_div').show();
	    			  
	    			   $("#cadet_name").text(k[0].full_name);
	    			 	
	    			 	//alert("nmk---" + k[0][13]);
	    			    $("#marital_status_p").text(k[0].marital_status);
	    			    
	    			    
	    			    $("#marital_status_hp").val(k[0].marital_status);
	    		    	
	    		    	if(k[0].date_of_tos==null){
	    		    		$("#p_tos").text("");    
	    		    	}
	    		    	else{
	    		    		 $("#p_tos").text(convertDateFormate(k[0].date_of_tos));  
	    		    	}
	    		    
	    		    	$("#app_sus_no").text(k[0].sus_no);
	    		    	
	    		    	var sus_no =k[0].sus_no;
	    		    	getunit(sus_no);
	    		    	function getunit(val) {	
	    		            $.post("getTargetUnitNameList?"+key+"="+value, {
	    		            	sus_no : sus_no
	    		        }, function(j) {
	    		                //var json = JSON.parse(data);
	    		                //...

	    		        }).done(function(j) {
	    		    				   var length = j.length-1;
	    		    	                   var enc = j[length].substring(0,16); 
	    		    	                  
	    		    	                   $("#app_unit_name").text(dec(enc,j[0])); 
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

	 $("input#personnel_no").attr('readonly', true);
}
$("input#personnel_no").keyup(function(){
	var personel_no = this.value;
		 var susNoAuto=$("#personnel_no");
		  susNoAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        	type: 'POST',
			    	url: "getemp_noListApproved_CIV?"+key+"="+value,
		        data: {personel_no:personel_no},
		          success: function( data ) {
		        	 var susval = [];
		        	  var length = data.length-1;
		        	  var enc = data[length].substring(0,16);
			        	for(var i = 0;i<data.length;i++){
			        		susval.push(dec(enc,data[i]));
			        	}
					response( susval ); 
		          }
		        });
		      },
		      minLength: 1,
		      autoFill: true,
		      change: function(event, ui) {
		    	 if (ui.item) {   	        	  
		        	  return true;    	            
		          } else {
		        	  alert("Please Enter Approved Personal No");
		        	  document.getElementById("personnel_no").value="";
		        	  susNoAuto.val("");	        	  
		        	  susNoAuto.focus();
		        	  return false;	             
		          }   	         
		      }, 
		      select: function( event, ui ) {
		    	
		      } 	     
		}); 			
});
</script>
<script type="text/javascript">
<!-- //-->

	
function getmaritalNo(){
	 var civ_id = '${id}';
	 $.post('update_getfamily_marriageCivData1?' + key + "=" + value, {civ_id:civ_id}, function(j){
		 if(j!=""){
					 $('#marr_ch_id').val(j[0].id);
		 }
	 });
	} 

	
function getchild(){
	 var civ_id = '${id}';
	 $.post('getm_children_detailsCivData1?' + key + "=" + value, {civ_id:civ_id}, function(j){
		 if(j!=""){
					 $('#child_id').val(j[0].id);
		 }
	 });
	}

	

function getnok(){
	 var civ_id = '${id}';
	 $.post('nok_details_GetCivData1?' + key + "=" + value, {civ_id:civ_id}, function(j){
		 if(j!=""){
// 					 $('#co_authority').text(j[0].authority);
// 					 $('#co_date_of_authority').text(convertDateFormate(j[0].date_of_authority));
// 					 $('#batch_no').text(j[0].batch_no);
// 					 $('#course').val(j[0].course);
// 					 $('#course_val').text($( "#course option:selected" ).text());
					 $('#nok_id').val(j[0].id);
		 }
	 });
	}

</script>

<script>
function validate_personal_no(){

	if ($("select#persnl_no1").val() == "0") {
		alert("Please Select Personal No123");
		$("select#persnl_no1").focus();
		return false;
			}
   if ($("input#persnl_no2").val() == "") {
		alert("Please Enter Personal No123333");
		$("input#persnl_no2").focus();
		return false;
	}
	    var formvalues=$("#form_perssonel").serialize();
		var p_r_id=$("#p_r_id").val();	
		var civ_id=$("#civ_id").val();	
		formvalues+="&p_r_id="+p_r_id+"&civ_id="+civ_id;
		 $.post('Personal_no_Action?' + key + "=" + value,formvalues, function(data){
			      
		          if(data=="update")
		        	  alert("Data Updated Successfully");
		          else if(parseInt(data)>0){
		        	  $("#p_r_id").val(data);	
		        	  alert("Data Saved SuccesFully")
		          }
		          else
		        	  alert(data)
		 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		  		});
}
function get_PersonalNo(){
	 var civ_id=$('#civ_id').val(); 
	  $.post('get_PersonalNo1?' + key + "=" + value, {civ_id:civ_id}, function(j){
			var v=j.length;
			if(j!=""){
				$('#p_authority').val(j[0].authority);
				var l = j[0].new_personal_no.length;
				$('#p_date_of_authority').val(ParseDateColumncommission(j[0].date_of_authority));
				$("#persnl_no1").val(j[0].new_personal_no.substring(0,l-6));
				$("#persnl_no2").val(j[0].new_personal_no.substring(l-6, l-1));
				$("#persnl_no3").val(j[0].new_personal_no.substring(l,l-1));
				
			}
		
	  });
}  
</script>
<script>
/* ----------- Start cadet no ---------------*/

/* ----------- Start Birth ---------------*/


	$("#unit_sus_no").keyup(function(){
		var sus_no = this.value;
		var susNoAuto=$("#unit_sus_no");

		susNoAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        type: 'POST',
		        url: "getTargetSUSNoList?"+key+"="+value,
		        data: {sus_no:sus_no},
		          success: function( data ) {
		        	  var susval = [];
	                  var length = data.length-1;
	                  var enc = data[length].substring(0,16);
	                        for(var i = 0;i<data.length;i++){
	                                susval.push(dec(enc,data[i]));
	                        }
	                        var dataCountry1 = susval.join("|");
	                        var myResponse = []; 
					        var autoTextVal=susNoAuto.val();
					$.each(dataCountry1.toString().split("|"), function(i,e){
						var newE = e.substring(0, autoTextVal.length);
						if (e.toLowerCase().includes(autoTextVal.toLowerCase())) {
						  myResponse.push(e);
						}
					});      	          
					response( myResponse ); 
		          }
		        });
		      },
			  minLength: 1,
		      autoFill: true,
		      change: function(event, ui) {
		    	 if (ui.item) {   	        	  
		        	  return true;    	            
		          } else {
		        	  alert("Please Enter Approved Unit SUS No.");
		        	  document.getElementById("unit_name").value="";
		        	  susNoAuto.val("");	        	  
		        	  susNoAuto.focus();
		        	  return false;	             
		          }   	         
		    }, 
			select: function( event, ui ) {
				var sus_no = ui.item.value;			      	
				 $.post("getTargetUnitNameList?"+key+"="+value, {
					 sus_no:sus_no
	         }, function(j) {
	                
	         }).done(function(j) {
	        	 var length = j.length-1;
	             var enc = j[length].substring(0,16);
	             $("#unit_posted_to").val(dec(enc,j[0]));   
	                 
	         }).fail(function(xhr, textStatus, errorThrown) {
	         });
			} 	     
		});	
	});
	
	
	 $("input#unit_posted_to").keypress(function(){
			var unit_name = this.value;
				 var susNoAuto=$("#unit_posted_to");
				  susNoAuto.autocomplete({
				      source: function( request, response ) {
				        $.ajax({
				        	type: 'POST',
					    	url: "getTargetUnitsNameActiveList?"+key+"="+value,
				        data: {unit_name:unit_name},
				          success: function( data ) {
				        	 var susval = [];
				        	  var length = data.length-1;
				        	  var enc = data[length].substring(0,16);
					        	for(var i = 0;i<data.length;i++){
					        		susval.push(dec(enc,data[i]));
					        	}
					        	   	          
							response( susval ); 
				          }
				        });
				      },
				      minLength: 1,
				      autoFill: true,
				      change: function(event, ui) {
				    	 if (ui.item) {   	        	  
				        	  return true;    	            
				          } else {
				        	  alert("Please Enter Approved Unit Name.");
				        	  document.getElementById("unit_posted_to").value="";
				        	  susNoAuto.val("");	        	  
				        	  susNoAuto.focus();
				        	  return false;	             
				          }   	         
				      }, 
				      select: function( event, ui ) {
				    	 var unit_name = ui.item.value;
				    
					            $.post("getTargetSUSFromUNITNAME?"+key+"="+value,{target_unit_name:unit_name}, function(data) {
					            }).done(function(data) {
					            	var length = data.length-1;
						        	var enc = data[length].substring(0,16);
						        	$("#unit_sus_no").val(dec(enc,data[0]));
					            }).fail(function(xhr, textStatus, errorThrown) {
					            });
				      } 	     
				}); 			
		});
	
	 </script>
<script>
	 function calculate_age(obj){    
			
			
	     var from_d=$("#"+obj.id).val();
		 
		 var from_d=$("#"+obj.id).val();
	     from_d = from_d.replaceAll("/", "-");
	  
		 
	     var from_d1=from_d.substring(6,10);
	     var from_d2=from_d.substring(3,5);
	     var from_d3=from_d.substring(0,2);
	     var frm_d = from_d3+"-"+from_d2+"-"+from_d1;         
	   
	     var today=new Date();
	     var to_d3 = today.getDate();
	     var to_d2 = today.getMonth() + 1;
	     var to_d1 = today.getFullYear();        
	     var to_d0 = to_d3+"-"+to_d2+"-"+to_d1;
	     if(to_d2 > from_d2 && to_d3 > from_d3 || to_d3 == from_d3){
	     var year = to_d1 - from_d1        
	     var month = to_d2 - from_d2
	     }
	     if(to_d2 > from_d2 && to_d3 < from_d3){
	             var year = to_d1 - from_d1        
	             var month = to_d2 - from_d2 - 1
	             }
	     if(from_d2 > to_d2){
	             var year = to_d1 - from_d1 - 1
	             var month1 = from_d2 - to_d2
	             var month = 12 - month1
	     }
	     if(from_d2 == to_d2 && from_d3 > to_d3){
	             var year = to_d1 - from_d1 - 1
	             var days = from_d3 - to_d3
	     }
	     if(from_d2 == to_d2 && to_d3 > from_d3){
	             var year = to_d1 - from_d1 
	             var days =  to_d3 - from_d3 
	             
	     }
	     if(from_d2 == to_d2 && to_d3 == from_d3){
	             var year = to_d1 - from_d1 
	             var days = 0
	     }
	     
	     if(from_d2 > to_d2 && from_d3 > to_d3){
	             var m = from_d2 - to_d2 
	             var n = m * 30
	             var m1 = from_d3 - to_d3 
	             var m2 = n+m1
	             var days =  m2
	     }
	     if(from_d2 > to_d2 && to_d3 > from_d3){
	             var m = from_d2 - to_d2 
	             var n = m * 30
	             var m1 =  to_d3 - from_d3 
	             var m2 = n+m1
	             var days =  m2
	     }
	     if(to_d2 > from_d2   && to_d3 > from_d3){
	             var m =  to_d2 - from_d2 
	             var n = m * 30
	             var m1 =  to_d3 - from_d3 
	             var m2 = n+m1        
	             var days =  m2 
	     
	     }
	     if(to_d2 >  from_d2 && from_d3 > to_d3){
	             var m = to_d2 - from_d2   
	             var n = m * 30
	             var m1 = from_d3 - to_d3 
	             var m2 = n+m1
	             var days =  m2
	             
	     }
	    

	     
	     
	     if (month == undefined)
	      {
	             month = 0;
	      }
	    
	     if(year < 17)
	     {
	    	 alert("Please enter age above 17");
	    	 $("#"+obj.id).val("");
	     }
	 }

	 
	 function onChangePerNo(obj)
	 {
	 	var data = obj.value;

	 	
	 	if(data.length == 5)
	 	{
	 		
		 
	 		var suffix="";
	 		var summation = 6*parseInt(data[0])+5*parseInt(data[1])+4*parseInt(data[2])+3*parseInt(data[3])+2*parseInt(data[4]);
	 		
	 		summation = parseInt( parseInt(summation) % 11);
	 	
	 		if(summation == 0)
	 		{
	 			suffix="A";
	 		}
	 		if(summation == 1)
	 		{
	 			suffix="F";
	 		}
	 		if(summation == 2)
	 		{
	 			suffix="H";
	 		}
	 		if(summation == 3)
	 		{
	 			suffix="K";
	 		}
	 		if(summation == 4)
	 		{
	 			suffix="L";
	 		}
	 		if(summation == 5)
	 		{
	 			suffix="M";
	 		}
	 		if(summation == 6)
	 		{
	 			suffix="N";
	 		}
	 		if(summation == 7)
	 		{
	 			suffix="P";
	 		}
	 		if(summation == 8)
	 		{
	 			suffix="W";
	 		}
	 		if(summation == 9)
	 		{
	 			suffix="X";
	 		}
	 		if(summation == 10)
	 		{
	 			suffix="Y";
	 		}
	 		 $.ajaxSetup({
                 async : false
         	});
	 		
	 		$("#persnl_no3").val(suffix);
	 		
	 		 $.ajaxSetup({
                 async : false
        	 });
	 		Personal_no_already_exist();
	 	}
	 	
	 	
	 }
	

	 function Personal_no_already_exist()
	 {
		
		 var persnl_no1 = $("select#persnl_no1").val();
		 if(persnl_no1 == "-1")
		 {
			 alert("Please Select Personal Number Prefix.")
			 return false;
		 }
		 var persnl_no2 = $("input#persnl_no2").val();
		 if(persnl_no2.length != 5 )
		 {
			 alert("Please Enter Personal Number.")
			 return false;
		 }
		 var persnl_no3 = $("input#persnl_no3").val();
		 if(persnl_no3.length != 1 )
		 {
			 alert("Please Enter Personal Number.")
			 return false;
		 }
		 var personnel_no = persnl_no1 + persnl_no2 + persnl_no3;
		 
		 $.post("getPersonnelNoAlreadyExistInCommission?"+key+"="+value, {personnel_no : personnel_no}).done(function(j) {
			
			 	if(j == true){
					alert("Personal Number already Approved.")
					$("select#persnl_no1").val("-1");
					$("input#persnl_no2").val("");
					$("input#persnl_no3").val("0");
				} 
				
			}); 
	 }
	 
	
	 
	 function date_of_com() {
		  $( "#date_of_commission" ).datepicker({  maxDate: new Date() });
		  alert("Future date are not allowed ");
	 }
	 
	 function isNumber0_9Only(evt) {
			var charCode = (evt.which) ? evt.which : evt.keyCode;
			if(charCode != 46 && charCode > 31 && (charCode<48 || charCode>57)){
				return false;
			}else{
				if(charCode == 46){
					return false;
				}
			}
			return true;
		}
 </script>
<script>
  

jQuery(function($){
	datepicketDate('date_authority');
	datepicketDate('dt_of_designation');
	 datepicketDate('date_authority_des');
	 datepicketDate('r_date_of_authority');            
	 datepicketDate('date_of_rank');
	 datepicketDate('na_date_of_authority');
	 datepicketDate('appt_date_of_authority');
	 datepicketDate('date_of_appointment');
	 datepicketDate('issue_dt'); 
	 datepicketDate('rel_date_of_authority'); 
	 datepicketDate('sib_date_of_birth1');
	 datepicketDate('sib_adop_date1');
	 datepicketDate('ten_from');
	 datepicketDate('ten_to');
	 $( "#ten_to" ).datepicker( "option", "maxDate", null);
	 datepicketDate('date_authority');        
	 datepicketDate('addr_date_authority');  
	 datepicketDate('language_date_of_authority');  
	 datepicketDate('qualification_date_of_authority'); 
	 datepicketDate('promo_exam_date_of_authority'); 
	 datepicketDate('from_dt1'); 
	 datepicketDate('to_dt1'); 
	 datepicketDate('awardsNmedal_date_of_authority1');
	 datepicketDate('date_of_award1');
	 datepicketDate('date_of_casuality');
	 datepicketDate('batnpc_date_of_authority');
	 datepicketDate('date_of_informing');
	 datepicketDate('award_dt');
	 datepicketDate('inter_arm_date_of_authority');
	 datepicketDate('inter_arm_with_effect_from');
	 datepicketDate('coc_date_of_authority');
	 datepicketDate('coc_date_of_permanent_commission');
	 datepicketDate('coc_date_of_seniority');
	 datepicketDate('eff_coc_date_of_seniority');
	 datepicketDate('eoc_authority_date');
	 datepicketDate('eoc_from');
	 datepicketDate('eoc_to');
	 $( "#eoc_to" ).datepicker( "option", "maxDate", null);
	 datepicketDate('sec_date_of_authority');
	 datepicketDate('secondment_effect');
	 datepicketDate('date_of_authority_non_ef');
	 datepicketDate('date_of_non_effective');
	 datepicketDate('marriage_date_of_authority');
	 datepicketDate('marriage_date');
	 datepicketDate('marriage_date_of_birth');
	 datepicketDate('divorce_date');
	 
	 datepicketDate('army_course_date_of_authority');
	 datepicketDate('army_course_start_date1');
	 datepicketDate('army_course_date_of_completion1');
	 datepicketDate('disi_authority_date');
	 
	 //medical
	 
     datepicketDate('madical_date_of_authority');
	 
	 datepicketDate('s_from_date1');
	 datepicketDate('s_to_date1');
	 
	 datepicketDate('h_from_date1');
	 datepicketDate('h_to_date1');
	 
	 datepicketDate('a_from_date1');
	 datepicketDate('a_to_date1');
	 
	 datepicketDate('p_from_date1');
	 datepicketDate('p_to_date1');
	 
	 datepicketDate('e_from_date1');
	 datepicketDate('e_to_date1');
	 
	 
	 datepicketDate('1bx_from_date');
	 datepicketDate('1bx_to_date');
	// datepicketDate('date_of_birth1');//kajal
	 $( "#1bx_to_date" ).datepicker( "option", "maxDate", null);
	
	 $( "#s_to_date1" ).datepicker( "option", "maxDate", null);
	 $( "#h_to_date1" ).datepicker( "option", "maxDate", null);
	 $( "#a_to_date1" ).datepicker( "option", "maxDate", null);
	 $( "#p_to_date1" ).datepicker( "option", "maxDate", null);
	 $( "#e_to_date1" ).datepicker( "option", "maxDate", null);
	 // medical ends
	 
	 datepicketDate('dt_desertion');
	 datepicketDate('dt_recovered');
	 
	 datepicketDate('separated_from_dt');
	 datepicketDate('separated_to_dt');
	 datepicketDate('p_date_of_authority');  
	 datepicketDate('c_date_of_authority'); 
	 datepicketDate('co_date_of_authority');  
	 datepicketDate('g_date_of_authority'); 
	 
	 datepicketDate('com_date_of_authority'); 
	 datepicketDate('date_of_commission'); 
	 
	 datepicketDate('b_date_of_authority'); 
	 datepicketDate('date_of_birth');
	 
	 datepicketDate('arm_date_of_authority');
	 
	 datepicketDate('u_date_of_authority'); 
	 datepicketDate('date_of_tos');
	 
	 

	 datepicketDate('rdate_of_authority'); 
	 datepicketDate('dt_rk');
	 
	 });
	 
	 
function specialcharecterCadet(a)
{
    var iChars = "@#^&*$,.:;%!+_-[]";   
    var data = a.value;
    for (var i = 0; i < data.length; i++)
    {      
        if (iChars.indexOf(data.charAt(i)) != -1)
        {    
        alert ("This " +data.charAt(i)+" special characters not allowed.");
        a.value = "";
        return false; 
        } 
    }
    return true;
}
  
function validate_rank(){
	
	
	 if ($("#rdate_of_authority").val().trim()=="" || $("#rdate_of_authority").val() == "DD/MM/YYYY" ) {
			alert("Please Enter Date of Authority");
			$("input#rdate_of_authority").focus();
			return false;
		} 
	 
	 if ($("select#rank").val()== "0" || $("select#rank").val()== "") {
			alert("Please Enter Rank");
			$("select#rank").focus();
	 		return false;
		}
	 if ($("#dt_rk").val() == "") {
			alert("Please Enter Date of Rank");
			$("#dt_rk").focus();
	 		return false;
		}
	 
	 
	 var formvalues=$("#form_rnk").serialize();
	
		var civ_id=$("#civ_id").val();	
		
		formvalues+="&civ_id="+civ_id;
		
	
		 $.post('RANK_Action?' + key + "=" + value,formvalues, function(data){
		          if(data=="update")
		        	  alert("Rank Updated Successfully");
		          else if(parseInt(data)>0){
		        	  $("#com_id").val(data);	
		        	  alert("Rank Saved SuccessFully")
		          }
		          else
		        	  alert(data)
		 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		  		}); 
	 
}
function get_rank(){

	 var civ_id = '${id}';
	 $.post('get_rank1?' + key + "=" + value, {civ_id:civ_id}, function(j){
		 if(j!=""){
					 $('#r_authority').val(j[0].authority);
					 $('#rdate_of_authority').val(ParseDateColumncommission(j[0].date_of_authority));
					 $('#rank').val(j[0].rank);
					 $('#dt_rk').val(ParseDateColumncommission(j[0].date_of_rank));
					 $('#rank_id').val(j[0].id);
		 }
	 });
	}	 
	
	
function validate_save_nok_details(){
	
		if ($("input#nok_name").val().trim()=="") {
			alert("Please Enter NOK Name");
			$("input#nok_name").focus();
			return false;
		}
		if ($("select#nok_relation").val() == "0") {
			alert("Please Enter NOK Relation");
			$("select#nok_relation").focus();
			return false;
		}
		if ($("select#nok_country").val() == "0") {
			alert("Please Select  Country");
			$("select#nok_country").focus();
			return false;
		}
	  var text1 = $("#nok_country option:selected").text();
		
		if(text1 == "OTHERS" && $("input#ctry_other").val().trim()==""){
			alert("Please Enter  Country Other");
			$("input#ctry_other").focus();
			return false;
		}
		if ($("select#nok_state").val() == "0") {
			alert("Please Select  Nok State");
			$("select#nok_state").focus();
			return false;
		}
	  var text2 = $("#nok_state option:selected").text();
		
		if(text2 == "OTHERS" && $("input#st_other").val().trim()==""){
			alert("Please Enter State Other");
			$("input#st_other").focus();
			return false;
		}
		if ($("select#nok_district").val() == "0") {
			alert("Please Select Nok District");
			$("select#nok_district").focus();
			return false;
		}
		 var text3 = $("#nok_district option:selected").text();
			
			if(text3 == "OTHERS" && $("input#dist_other").val().trim()==""){
				alert("Please Enter District Other");
				$("input#dist_other").focus();
				return false;
			}
		if ($("select#nok_tehsil").val() == "0" ) {
			alert("Please Select Tehsil");
			$("select#nok_tehsil").focus();
			return false;
		}
		var text5 = $("#nok_tehsil option:selected").text();
		
		if(text5 == "OTHERS" && $("input#nok_tehsil_other").val().trim()==""){
			alert("Please Enter Tehsil Other");
			$("input#nok_tehsil_other").focus();
			return false;
		}
		if ($("input#nok_village").val().trim()=="") {
			alert("Please Enter  Village/Town/City");
			$("input#nok_village").focus();
			return false;
		}
		if ($("input#nok_pin").val().trim()==""  || $("input#nok_pin").val().trim().length<6 || $("input#nok_pin").val().trim().length>6) {
			alert("Please Enter  Nok Pin");
			$("input#nok_pin").focus();
			return false;
		}
		if ($("input#nok_rail").val().trim()=="") {
			alert("Please Enter  Nearest Railway Station");
			$("input#nok_rail").focus();
			return false;
		}
		var n = $('input:radio[name=nok_rural_urban]:checked').val();
		if(n == undefined){	
			alert("Please select Is  Rural /Urban/Semi Urban");
			return false;
			}
		if ($("input#nok_mobile_no").val().trim()=="" || $("input#nok_mobile_no").val().trim().length<10  || $("input#nok_mobile_no").val().trim().length>10) {
			alert("Please Enter  NOK Mobile No");
			$("input#nok_mobile_no").focus();
			return false;
		}
		
		
	   var formvalues=$("#form_nok_addr_details_form").serialize();
	   
		var civ_id =$("#civ_id").val();
		var nok_id=$("#nok_id").val();		
		var nok_country_text=$("#nok_tehsil").val();
			formvalues+="&nok_id="+nok_id+"&civ_id="+civ_id;	
			formvalues+="&nok_country_text="+nok_country_text;
			$.post('nok_details_action_civ?' + key + "=" + value,formvalues, function(data){
				if(data=="update")
		        	  alert("Data Updated Successfully");
		          else if(parseInt(data)>0){
		        	  $("#nok_id").val(data);	
		        	  alert("Data Saved Successfully")
		          }
		          else
		        	  alert(data)
		 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		  		});
	}
	function get_nokaddress_details(){
		var civ_id=$("#civ_id").val();	
		$.post('nok_details_GetData_Civ?' + key + "=" + value,{civ_id:civ_id }, function(j){
			var v=j.length;
			if(v!=0){
				//////nok//////
				$("#authority").val(j[0].authority);
				$('#date_authority').val(ParseDateColumncommission(j[0].date_authority)); 
				$("#nok_name").val(j[0].nok_name);
				$("#nok_relation").val(j[0].nok_relation);
				$("#nok_country").val(j[0].nok_country);
				fn_nok_Country();
				$("#nok_state").val(j[0].nok_state);
				fn_nok_state();
				$("#nok_district").val(j[0].nok_district);
				fn_nok_district();
				$("#nok_tehsil").val(j[0].nok_tehsil);
				$("#nok_village").val(j[0].nok_village);
				$("#nok_pin").val(j[0].nok_pin);
				$("#nok_rail").val(j[0].nok_near_railway_station);
				/* $("#ctry_other").val(j[0].ctry_other);
				$("#st_other").val(j[0].st_other);
				$("#dist_other").val(j[0].dist_other);
				$("#tehsil_other").val(j[0].tehsil_other); */
				$("#nok_mobile_no").val(j[0].nok_mobile_no);
				 var nok= j[0].nok_rural_urban_semi;
		            if(nok == "rural"){
		                    $("#nok_rural").prop("checked", true);
		             } 
		            if(nok == "urban"){
		                    $("#nok_urban").prop("checked", true);
		             }
		            if(nok == "semi_urban")
		            {
		                    $("#nok_semi_urban").prop("checked", true);
		             }  
		            
		            var text6 = $("#nok_country option:selected").text();
					if(text6 == "OTHERS"){
						$("#ctry_other").val(j[0].ctry_other);
						$("#nok_other_id").show();
					}
					else{
						$("#nok_other_id").hide();
					}
					
					var text7 = $("#nok_state option:selected").text();
					if(text7 == "OTHERS"){
						$('#st_other').val(j[0].st_other);
						$("#nok_other_st").show();
					}
					else{
						$("#nok_other_st").hide();
					}
					var text8 = $("#nok_district option:selected").text();
					if(text8 == "OTHERS"){
						$("#dist_other").val(j[0].dist_other);
						$("#nok_other_dist").show();
					}
					else{
						$("#nok_other_dist").hide();
					}
					var text9 = $("#nok_tehsil option:selected").text();
					if(text9 == "OTHERS"){
						$("#nok_tehsil_other").val(j[0].tehsil_other);
						$("#nok_other_te").show();
					}
					else{
						$("#nok_other_te").hide();
					}
				$("#nok_id").val(j[0].id);
			}
		  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
			});
	} 
	

	
//Designation

function validate_save_designation_details(){

	 if ($("#des_authority").val().trim() == "") {
			alert("Please Enter designation Authority");
			$("#des_authority").focus();
			return false;
		}
	 
		if ($("#date_authority_des").val() == "DD/MM/YYYY" || $("#date_authority_des").val().trim()=="") {
			alert("Please Select Date of Authority");
			$("#date_authority_des").focus();
			return false;
		} 
	
  if ($("#dt_of_designation").val() == "DD/MM/YYYY" || $("#dt_of_designation").val().trim()=="") {
		alert("Please Select Date of Designation");
		$("#dt_of_designation").focus();
		return false;
	} 
		
   var formvalues=$("#form_designation_details_form").serialize();
   
	var civ_id =$("#civ_id").val();
	var des_id=$("#des_id").val();		
		formvalues+="&des_id="+des_id+"&civ_id="+civ_id;	
		$.post('designation_details_action_civ?' + key + "=" + value,formvalues, function(data){
			
			 if(data=="update")
	        	  alert("Data Updated Successfully");
	          else if(parseInt(data)>0)
	          {
	        	  $("#des_id").val(data);	
	        	  alert("Data Saved Successfully")
	          }
	          else
	        	  alert(data)
	 	  		}).fail(function(xhr, textStatus, errorThrown) 
	 	  			{
	 	  			alert("fail to fetch")
	 	  		
	  		});
}
function get_changedesignation_details(){
	var civ_id=$("#civ_id").val();	
	$.post('designation_details_GetData_Civ?' + key + "=" + value,{civ_id:civ_id }, function(j){
		var v=j.length;
			if(v!=0){
				
				$("#des_authority").val(j[0].authority);
				$('#date_authority_des').val(ParseDateColumncommission(j[0].date_authority)); 
				$('#designation').val(j[0].designation); 
				$('#dt_of_designation').val(ParseDateColumncommission(j[0].designation_date)); 
		       
					
					
				$("#des_id").val(j[0].id);
			}
		  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
			});
	} 
	
	
function married_save_fn(ps){
	

	 var marital_event=$('#marital_event').val();
	 
		
		if (marital_event!="2" && marital_event!="5" && marital_event!="6" ) {	
		 $("#divorce_date").val("");
		}
 
		var marital_status=$('#marital_status').val();
	     var maiden_name=$('#maiden_name').val();
	     var marriage_date_of_birth=$('#marriage_date_of_birth').val();
	     var marriage_place_of_birth=$('#marriage_place_of_birth').val();
	     var marriage_nationality=$('#marriage_nationality').val();
	     var marriage_nationality_other=$('#spouseNationality_other').val();
	     var marriage_date=$('#marriage_date').val();
	     var marriage_adhar_no=$('#marriage_adhar_no').val().split('-').join('');
	     var pan_card=$('#pan_card').val();
	     var marr_ch_id=$('#marr_ch_id').val();
	     var divorce_date=$('#divorce_date').val();
	     var marriage_authority=$('#marriage_authority').val();
	     var marriage_date_of_authority=$('#marriage_date_of_authority').val();
	var civ_id=$('#civ_id').val();        
	//var p_id=$('#census_id').val();
	 var ser_radio = $('input:radio[name=spouse_ser_radio]:checked').val();
     var spouse_personal_no=$("#spouse_personal_no").val();
     var spouseSer_other=$("#spouseSer_other").val();
     var spouse_service=$("#spouse_service").val();
	//var enroll_date=$('#enroll_date').val();  

	var separated_from_dt=$("#separated_from_dt").val();
	var separated_to_dt=$('#separated_to_dt').val();   

	 
    var separated_from_dt=$("#separated_from_dt").val();
    var separated_to_dt=$('#separated_to_dt').val();   
    
    if (marital_event == null || marital_event=="0") {
            alert("Please Select Marital Event");
            $('#marital_event').focus()
            return false;
    }
    
    
    if (marriage_authority == null || marriage_authority.trim()=="") {
        alert("Please Enter Authority");
        $('#marriage_authority').focus()
        return false;
	   }
	   if (marriage_date_of_authority == null || marriage_date_of_authority=="" || marriage_date_of_authority=="DD/MM/YYYY") {
	           alert("Please Enter Date of Authority");
	           $('#marriage_date_of_authority').focus()
	           return false;
	   }
// 	   if(getformatedDate($("input#comm_date").val()) > getformatedDate($("#marriage_date_of_authority").val())) {
//         alert("Authority Date should be Greater than Commission Date");
//         return false;
// 		}
    
    if(marital_event == 1 || marital_event == 3){
			
 	   
   			if(curr_marital_status==13){
				   		if (separated_to_dt != null && separated_to_dt=="" && separated_to_dt=="DD/MM/YYYY") {
				             
				   		if(getformatedDate(separated_from_dt) >= getformatedDate(separated_to_dt)) {
				               alert("Separation To Date should be Greater than Separation From Date");
				               $("input#separated_to_dt").focus();
				               return false;
				       	}
				   		}
				}
   			else{
		               if (maiden_name == null || maiden_name.trim()=="")  {
		                       alert("Please Enter Spouse Name");
		                       $('#maiden_name').focus()
		                       return false;
		               }
		               if (marriage_date == null || marriage_date=="" || marriage_date=="DD/MM/YYYY") {
		                       alert("Please Select Date of Marriage");
		                       $('#marriage_date').focus()
		                       return false;
		               }
		               if (marriage_place_of_birth == null || marriage_place_of_birth.trim()=="") {
		                       alert("Please Enter Place Of Birth");
		                       $('#marriage_place_of_birth').focus()
		                       return false;
		               }
		               if (marriage_nationality == null || marriage_nationality=="0") {
		                       alert("Please Select Nationality");
		                       $('#marriage_nationality').focus()
		                       return false;
		               }
		               if($("#marriage_nationality option:selected").text()==other) {
		                     if(marriage_nationality_other == null || marriage_nationality_other==""){          
		                            alert( "Please Enter Nationality Other");
		                               
		                               $("input#spouseNationality_other").focus();
		                               return false;
		                          }
		             }
		               if (marriage_date_of_birth == null || marriage_date_of_birth=="" || marriage_date_of_birth=="DD/MM/YYYY") {
		                       alert("Please Select Date of Birth");
		                       $('#marriage_date_of_birth').focus()
		                       return false;
		               }
		               
		               if(getformatedDate(marriage_date_of_birth) > getformatedDate(marriage_date)) {
		                       alert("Marriage Date should be Greater than Date of Birth");
		                       $('#marriage_date').focus()
		                       return false;
		               }
// 		               if(calculate_age(document.getElementById('marriage_date_of_birth'), document.getElementById('marriage_date')) && calculate_age(document.getElementById('dob_date'), document.getElementById('marriage_date'))) {} else {
// 		                       return false;
// 		               }
		               if (marriage_adhar_no == null || marriage_adhar_no=="" ||  marriage_adhar_no.length < 12) {
		                       alert("Please Enter Aadhaar Number");
		                       $('#marriage_adhar_no').focus()
		                       return false;
		               }
	
		               if(ser_radio=="Yes"){
		                       if(spouse_service==null || spouse_service=='0'){
		                               alert("Please Select Spouse In Service");
		                               $('#spouse_service').focus()
		                               return false;
		                       }
		                       else{
		                               if(spouse_service=='4'){
		                                       if(spouseSer_other==null || spouseSer_other==""){                        
		                                       alert("Please Enter Other Service");
		                                       $('#spouseSer_other').focus()
		                                       return false;
		                                       }
		                               }
		                                else if(spouse_service=='1'){
		                                        if(spouse_personal_no==null || spouse_personal_no==""){                        
		                                                alert("Please Enter Spouse Personal No.");
		                                                $('#spouse_personal_no').focus()
		                                                return false;
		                                                }
		                                        }
		                               if(spouse_personal_no.trim()!=''){
		                                       if(spouse_personal_no.trim().length < 5 || spouse_personal_no.trim().length >15){
		                                       alert("Please Enter Valid Spouse Personal No");
		                                       $("#spouse_personal_no").focus();
		                                       return false;
		                                       }
		                               }
		                       }
		               }
		       }
    }
  
    if( marital_event=='2' || marital_event=='5' || marital_event=='6'){
            if (divorce_date == null || divorce_date=="" || divorce_date=="DD/MM/YYYY") {
                    alert("Please Select "+$("#divorce_widow_widower_dtlbl").text());
                    $('#divorce_date').focus()
                    return false;
            }
            if(getformatedDate(marriage_date) >= getformatedDate(divorce_date)) {
                    alert($("#divorce_widow_widower_dtlbl").text()+" should be Greater than Marriage Date");
                    $("input#divorce_date").focus();
                    return false;
            }
    }
    
    if(marital_event=='4'){
    	if(curr_marital_status==8){
	        	if (separated_from_dt == null || separated_from_dt=="" || separated_from_dt=="DD/MM/YYYY") {
	                alert("Please Enter Date of Separation");
	                $('#separated_from_dt').focus()
	                return false;
	        		}
	        	if(getformatedDate(marriage_date) >= getformatedDate(separated_from_dt)) {
                alert("Date of Separation should be Greater than Marriage Date");
                $("input#separated_from_dt").focus();
                return false;
        	}
    	}

   }
    


	   $.post('update_family_marriage_civ_action?' + key + "=" + value, {separated_to_dt:separated_to_dt,separated_from_dt:separated_from_dt,curr_marital_status:curr_marital_status,maiden_name:maiden_name,marriage_date_of_birth:marriage_date_of_birth,
	           marriage_place_of_birth:marriage_place_of_birth,marriage_nationality:marriage_nationality,marriage_date:marriage_date,marriage_adhar_no:marriage_adhar_no,pan_card:pan_card,
	           marr_ch_id:marr_ch_id,divorce_date:divorce_date,marriage_authority:marriage_authority,marriage_date_of_authority:marriage_date_of_authority
	           ,marriage_nationality_other:marriage_nationality_other,marital_event:marital_event,civ_id:civ_id ,marital_status:marital_status,ser_radio:ser_radio,spouse_service:spouse_service,spouseSer_other:spouseSer_other,spouse_personal_no:spouse_personal_no}, function(data){
	                   if(data=="update"){
						
                           if(($('#marital_event').val()=='1' && curr_marital_status==10)  || $('#marital_event').val()=='3'){
                         		var a = $('input:radio[name=spouse_quali_radio]:checked').val();
                         		if(a.toLowerCase() == "yes"){
                         			spouse_qualification_save_fn();
                         		}
                         		else{
                         		  remove_spouse_qualificationFn();	
                         		alert("Spouse Data Updated Successfully");
                         		}
               			}
               			else{
               				alert("Spouse Data Updated Successfully");
               			}
                   }
                   else if(parseInt(data)>0){
                 $('#marr_ch_id').val(data);
                 
                 if(($('#marital_event').val()=='1' && curr_marital_status==10)  || $('#marital_event').val()=='3'){
             		var a = $('input:radio[name=spouse_quali_radio]:checked').val();
             		if(a.toLowerCase() == "yes"){
             			spouse_qualification_save_fn();
             		}
             		else{
               		  remove_spouse_qualificationFn();	
               		 alert("Spouse Data Saved Successfully");
               		}
   			}
   			else{
   				alert("Spouse Data Saved Successfully");
   			}
                 
                  
          }
          else
                  alert(data);
                           }).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
                  });
}

function fn_other_spouse_exam() {
	        var text = $("#spouse_quali option:selected").text();
	        if(text == "OTHERS"){
	                $("#other_spouse_exam").show();
	        }
	        else{
	                $("#other_spouse_exam").hide();
	        }
}

function fn_other_spouse_div() {
	    var text = $("#spouse_div_class option:selected").text();
	    if(text == "OTHERS"){
	            $("#other_spouse_div").show();
	    }
	    else{
	            $("#other_spouse_div").hide();
	    }
}


	function getfamily_marriageDetails(){
		
		 var civ_id=$('#civ_id').val(); 
		 var marital_event=$('#marital_event').val();
		  $.post('update_getfamily_marriageCivData?' + key + "=" + value, {marital_event:marital_event,civ_id:civ_id }, function(j){
				var v=j.length;
			if(v!=0){
				
				if(curr_marital_status==8 && j[0].status==0){
							$("#marr_quali_radio1").prop("checked", true);
							$("#marr_quali_radio2").prop("disabled", true);
							  $('#spouse_Qualifications').hide();
							  $('#mrgbtn_save').show();
							  $('#mrgqualibtn_save').hide();
				}
							if($('#marital_event').val()=="0"){
									$('#marital_event').val(j[0].type_of_event);
									$('#marital_status').val(j[0].marital_status);
							}
							$('#maiden_name').val(j[0].maiden_name);
							$('#marriage_date_of_birth').val(ParseDateColumncommission(j[0].date_of_birth));
							$('#marriage_place_of_birth').val(j[0].place_of_birth);
							$('#marriage_nationality').val(j[0].nationality);
							$('#marriage_date').val(ParseDateColumncommission(j[0].marriage_date));
							$('#marriage_adhar_no').val(j[0].adhar_number);
							$('#pan_card').val(j[0].pan_card);
							$('#marr_ch_id').val(j[0].id);	
							if(j[0].status==0 ){
							$('#marriage_date_of_authority').val(ParseDateColumncommission(j[0].date_of_authority));			
							$('#marriage_authority').val(j[0].authority);
							}
							$('#separated_from_dt').val(ParseDateColumncommission(j[0].separated_form_dt));
							$('#separated_to_dt').val(ParseDateColumncommission(j[0].separated_to_dt));
							
							if(j[0].if_spouse_ser=="Yes"){
								$('#spouse_ser_radio1').prop('checked', true);
							}
							else{
								$('#spouse_ser_radio2').prop('checked', true);
							}
							if(j[0].spouse_personal_no!=null){
								$('#spouse_personal_no').val(j[0].spouse_personal_no);
							}
							if(j[0].spouse_service!=null){
								$('#spouse_service').val(j[0].spouse_service);
							}
							if(j[0].other_spouse_ser!=null){
								$('#spouseSer_other').val(j[0].other_spouse_ser);
							}
							if(j[0].other_spouse_ser!=null){
								$('#spouseSer_other').val(j[0].other_spouse_ser);
							}
							if(j[0].other_nationality!=null){
								$('#spouseNationality_other').val(j[0].other_nationality);
							}
							isAadhar(document.getElementById('marriage_adhar_no'));
							spouse_ServiceFn();
							Spouse_ServiceOtherFn('spouseSer_otherDiv','spouse_personalDiv',document.getElementById('spouse_service'))
							fn_otherShowHide(document.getElementById('marriage_nationality'),'Spouse_nationalityDiv','spouseNationality_other');
							if(j[0].divorce_date!=null && j[0].divorce_date!="" && j[0].divorce_date!="DD/MM/YYYY")
								$('#divorce_date').val(ParseDateColumncommission(j[0].divorce_date));
							if(j[0].status==1 || j[0].type_of_event==2 || j[0].type_of_event==4 || j[0].type_of_event==5 || j[0].type_of_event==6 || (j[0].type_of_event==1 && curr_marital_status==13)){
								$('#maiden_name').prop('disabled', true);
								$('#marriage_date_of_birth').prop('disabled', true);
								$("#marriage_date_of_birth").datepicker( "option", "disabled", true );
								$('#marriage_place_of_birth').prop('disabled', true);
								$("#marriage_place_of_birth").datepicker( "option", "disabled", true );
								$('#marriage_nationality').prop('disabled', true);
								$('#spouseNationality_other').prop('disabled', true);
								
								$('#marriage_date').prop('disabled', true);
								$("#marriage_date").datepicker( "option", "disabled", true );
								$('#marriage_adhar_no').prop('disabled', true);
								$('#pan_card').prop('disabled', true);
								$('#marr_ch_id').prop('disabled', true);			
//								$('#marriage_date_of_authority').prop('disabled', true);	
//								$("#marriage_date_of_authority").datepicker( "option", "disabled", true );
//								$('#marriage_authority').prop('disabled', true);
								
								
								$('#spouse_service').prop('disabled', true);
								$('input:radio[name=spouse_ser_radio]').prop('disabled', true);			
								$('#spouseSer_other').prop('disabled', true);		
								$('#spouse_personal_no').prop('disabled', true);
							}
							//$('#marital_event').prop('disabled', true);
							$('#marital_status').prop('disabled', true);
							$("#marriage_remarriageDiv").show();
							if($('#marital_event').val()=="1"){
								if(curr_marital_status==13){
									 $("#seperateDiv").show();
					        		$("#separated_from_dtDiv").show();
					        		$("#separated_to_dtDiv").show();
					        		$('#separated_from_dt').prop('disabled', true);
					        		$("#separated_from_dt").datepicker( "option", "disabled", true );
					        	}
							}
								
							
							if($('#marital_event').val()=="2" || $('#marital_event').val()=="5" || $('#marital_event').val()=="6"){			
							$("#divorceDiv").show();
							$("#spouse_quali_radioDiv").hide();
							
							if($('#marital_event').val()=="2"){
								$("#divorce_widow_widower_dtlbl").text("Date of Divorced")
							}
							if($('#marital_event').val()=="5" || $('#marital_event').val()=="6"){
								$("#divorce_widow_widower_dtlbl").text("Date of Death")
							}
							
							
							
							}
							
							else if($('#marital_event').val()=='4'){
								 $("#seperateDiv").show();
							 
						        	if(curr_marital_status==8){
						        		if(j[0].status==1){
						        			$("#separated_from_dt").val('');
						        			$("#separated_to_dt").val('');
						        		}
						        		
						        	
						        		$("#separated_from_dtDiv").show();
						        		$("#separated_to_dtDiv").hide();		        		
						        	}
						        	
							        		
						        	}
						       
						
							else{
								$("#spouse_quali_radioDiv").show();
							}
							
							if(($('#marital_event').val()=='1' && curr_marital_status==10)  || $('#marital_event').val()=='3'){
								$("#spouse_quali_radioDiv").show();
							}
							else{
								$("#spouse_quali_radioDiv").hide();
							}
							
							if(curr_marital_status==0 || curr_marital_status==10){
								$("#marital_event option[value='2']").remove();
								$("#marital_event option[value='3']").remove();
								$("#marital_event option[value='4']").remove();
								$("#marital_event option[value='5']").remove();
								$("#marital_event option[value='6']").remove();
								}
							else if(curr_marital_status==8){
								$("#marital_event option[value='1']").remove();
								$("#marital_event option[value='3']").remove();
								}
							else if(curr_marital_status==13){
//								$("#marital_event option[value='1']").remove();
								$("#marital_event option[value='3']").remove();
								$("#marital_event option[value='4']").remove();
							}
							else {
								$("#marital_event option[value='1']").remove();
								$("#marital_event option[value='2']").remove();
								$("#marital_event option[value='4']").remove();
								$("#marital_event option[value='5']").remove();
								$("#marital_event option[value='6']").remove();
							}
							
							}
						else{
						if(curr_marital_status==0 || curr_marital_status==10){
							$("#marital_event option[value='2']").remove();
							$("#marital_event option[value='3']").remove();
							$("#marital_event option[value='4']").remove();
							$("#marital_event option[value='5']").remove();
							$("#marital_event option[value='6']").remove();
							}
						else if(curr_marital_status==8){
							$("#marital_event option[value='1']").remove();
							$("#marital_event option[value='3']").remove();
							}
						else if(curr_marital_status==13){
//							$("#marital_event option[value='1']").remove();
							$("#marital_event option[value='3']").remove();
							$("#marital_event option[value='4']").remove();
						}
						else {
							$("#marital_event option[value='1']").remove();
							$("#marital_event option[value='2']").remove();
							$("#marital_event option[value='4']").remove();
							$("#marital_event option[value='5']").remove();
							$("#marital_event option[value='6']").remove();
						}
						}
					  });
				}
function spouse_ServiceFn() {
		var a = $('input:radio[name=spouse_ser_radio]:checked').val();
			if(a.toLowerCase() == "yes") {
				$("#spouse_inserviceDiv").show();
//						$("#spouseSerDiv").hide();
			} else {
//						$("#father_proffDiv").show();
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


	function spouse_qualification_save_fn() {
		spouse_id = $("#marr_ch_id").val();
		var a = $('input:radio[name=spouse_quali_radio]:checked').val();
		
		
		var dateofBirthyear = $("#marriage_date_of_birth").val().split('/')[2];
		var currentdate = new Date();
		var currentyear = currentdate.getFullYear();
		var type = $('#spouse_quali_type').val();
		var examination_pass = $('#spouse_quali').val();
		var exam_other_qua=$('#exam_otherSpouse').val();
	  	var degree_other=$('#quali_deg_otherSpouse').val();
		var spec_other=$('#quali_spec_otherSpouse').val();
		var class_other_qua=$('#class_otherSpouse').val();
		var passing_year = $('#spouse_yrOfPass').val();
		var degree = $('#quali_degree_spouse').val();
		var specialization = $('#spouse_specialization').val();
		var div_class = $('#spouse_div_class').val();
		var institute = $('#spouse_institute_place').val();
		var qualification_ch_id = $('#spouse_qualification_ch_id').val();
		
		var civ_id = $('#civ_id').val();
		var subjectvar = $('input[name="spouse_multisub"]:checkbox:checked').map(function() {
			return this.value;
		}).get();
		var subject = subjectvar.join(",");


			

		if(type == null || type == "0") {
			alert("Please Select Qualification Type");
			$("#spouse_quali_type").focus();
			return false;
		}

		if(examination_pass == null || examination_pass == "0") {
			alert("Please Select Examination Pass");
			$("#spouse_quali").focus();
			return false;
		}

		  if($("#spouse_quali option:selected").text().toUpperCase()==other || $("#spouse_quali option:selected").text().toUpperCase()=="OTHER") {
		     	 if(exam_other_qua == null || exam_other_qua.trim()==""){ 	 
		     		alert( "Please Enter Examination Other ");
					
					$("input#exam_otherSpouse").focus();
					return false;
				   }
		      }
		  
		  if($("#spouse_quali option:selected").text().toUpperCase()==other || $("#spouse_quali option:selected").text().toUpperCase()=="OTHER") {
				if(lengthValidation($("input#exam_otherSpouse").val().trim(),auth_length)){
					alert("Examination Other Length Should be Less or Equal To 100")
					$("#exam_otherSpouse").focus();
					return false;
				}
		      }
		  
		if(degree == null || degree == "0") {
			alert("Please Select The Degree Acquired");
			$("#quali_degree_spouse").focus();
			return false;
		}
		if($("#quali_degree_spouse option:selected").text().toUpperCase()==other || $("#quali_degree_spouse option:selected").text().toUpperCase()=="OTHER") {
	      	 if(degree_other == null || degree_other.trim()==""){ 	       
	      		alert( "Please Enter Degree Other ");
	 			$("input#quali_deg_otherSpouse").focus();
	 			return false;
	 		   }
	       }
		
		if($("#quali_degree_spouse option:selected").text().toUpperCase()==other || $("#quali_degree_spouse option:selected").text().toUpperCase()=="OTHER") {
			if(lengthValidation($("input#quali_deg_otherSpouse").val().trim(),auth_length)){
				alert("Degree Other Length Should be Less or Equal To 100")
				$("#quali_deg_otherSpouse").focus();
				return false;
			}
	      }
		
		if(specialization == null || specialization == "0") {
			alert("Please Select The Specialization");
			$("#spouse_specialization").focus();
			return false;
		}
		





	  
	  if($("#spouse_specialization option:selected").text().toUpperCase()==other || $("#spouse_specialization option:selected").text().toUpperCase()=="OTHER") {
	   	 if(spec_other == null || spec_other.trim()==""){ 	 
	    
				alert( "Please Enter Specialization Other ");
				$("input#quali_spec_otherSpouse").focus();
				return false;
			   }
	    }
	  
	  if($("#spouse_specialization option:selected").text().toUpperCase()==other || $("#spouse_specialization option:selected").text().toUpperCase()=="OTHER") {
		  if(lengthValidation($("input#quali_spec_otherSpouse").val().trim(),auth_length)){
				alert("Specialization Other Length Should be Less or Equal To 100")
				$("#quali_spec_otherSpouse").focus();
				return false;
			}
		  }
	  

	if(passing_year.trim()=="") {
		alert("Please Enter Year of Passing");
		$("input#spouse_yrOfPass").focus();
		return false;
	}
	if(dateofBirthyear!=null){
		if(passing_year.trim() != "") {
			if(parseInt(passing_year) <= parseInt(dateofBirthyear) || parseInt(passing_year) > parseInt(currentyear)) {
				alert("Please Enter Valid Year of Passing");
				$("input#spouse_yrOfPass").focus();
				return false;
			}
		}}

	if(div_class == "0") {
		alert("Please Select The Div/Grade/PCT(%)");
		$("#spouse_div_class").focus();
		return false;
	}
	if($("#spouse_div_class option:selected").text().toUpperCase()==other || $("#spouse_div_class option:selected").text().toUpperCase()=="OTHER") {
		 if(class_other_qua == null || class_other_qua.trim()==""){ 	 

			alert( "Please Enter Div/Grade/PCT(%) Other ");
			$("input#class_otherSpouse").focus();
			return false;
		   }
	}


	if($("#spouse_div_class option:selected").text().toUpperCase()==other || $("#spouse_div_class option:selected").text().toUpperCase()=="OTHER") {
		  if(lengthValidation($("input#class_otherSpouse").val().trim(),auth_length)){
				alert("Div/Grade/PCT(%) Other Length Should be Less or Equal To 100")
				$("#class_otherSpouse").focus();
				return false;
			}
	}

	if(subject.trim()=="") {
		alert("Please Select The Subject");
		$("select#spouse_sub_quali").focus();
		return false;
	}
	if(institute.trim()=="") {
		alert("Please Enter The Institute & place");
		$("#spouse_institute_place").focus();
		return false;
	}
		$.post('spouse_qualification_Civaction?' + key + "=" + value, {
			type: type,
			examination_pass: examination_pass,
			passing_year: passing_year,
			div_class: div_class,
			subject: subject,
			institute: institute,
			qualification_ch_id: qualification_ch_id,		
			degree: degree,
			specialization: specialization,
			civ_id: civ_id,
			dateofBirthyear: dateofBirthyear,
			exam_other_qua:exam_other_qua,class_other_qua:class_other_qua,		
			degree_other:degree_other,spec_other:spec_other,
			spouse_id: spouse_id
		}, function(data) {
			if(parseInt(data) > 0) {
			if(($('#marital_event').val()=='1' && curr_marital_status==10)  || $('#marital_event').val()=='3'){
				$("#spouse_quali_radioDiv").show();
				$("#spouse_quali_radio1").prop("checked", true);
				$("#spouse_Qualifications").show();
			}
			else{
				$("#spouse_quali_radioDiv").hide();
			}
			
			if(curr_marital_status==8){
				$("#marr_quali_radio2").prop("checked", true);
				$("#marr_quali_radio1").prop("disabled", true);
			}
			
			alert("Data Saved/Updated Successfully")
			$('#spouse_qualification_ch_id').val(data);
			} else alert(data)
		}).fail(function(xhr, textStatus, errorThrown) {
			alert("fail to fetch")
		});
		
		
	}

	function remove_spouse_qualificationFn(){
		var civ_id=$('#civ_id').val();	
		  $.post('updated_family_marriage_delete_Civ_action?' + key + "=" + value, {civ_id:civ_id,qali_status:1}, function(data){ 
		                   if(data=='1'){
		                	   $('#spouse_qualification_ch_id').val(0);
		                   }	
		                   
		 }).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		  });
	}

	function getSpouseQualificationData() {
		 var civ_id=$('#civ_id').val(); 
		var i = 0;
		$.post('getSpouseQualificationCivData?' + key + "=" + value, {
			civ_id: civ_id,app_status:0
		}, function(j) {
			var v = j.length;
			if(v != 0) {
				$('#spouse_yrOfPass').val(j[0].passing_year);
				$('#spouse_div_class').val(j[0].div_class);
				$('#spouse_institute_place').val(j[0].institute);
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
						$('#spouse_quali_sub_list').append("<span class='subspan'>" + this.parentElement.innerText + "<i class='fa fa-times' style='margin: 5px;  font-size: 15px;' onclick='removeSpouseSubFn(" + this.value + ")'></i><span> <br>");
					}
				});
				$("#spouse_checkboxes").show();
				$('#spouse_quali_type').val(j[0].type);
				var typethisT = document.getElementById('spouse_quali_type');
				fn_qualification_typeChange(typethisT,'spouse_quali','quali_degree_spouse','spouse_specialization');
				
				if(j[0].examination_pass != null) {
					$('#spouse_quali').val(j[0].examination_pass);
					var qualithisT = document.getElementById('spouse_quali');
					fn_ExaminationTypeChange(qualithisT,'quali_degree_spouse','spouse_specialization');
				}
				
				if(j[0].degree != null) {
					$('#quali_degree_spouse').val(j[0].degree);
					fn_otherShowHide(document.getElementById('quali_degree_spouse'),'other_div_qualiDegSpouse','quali_deg_otherSpouse');
					
				}
				if(j[0].specialization != null) {
				$('#spouse_specialization').val(j[0].specialization);
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
		  	    
		  	
		 	  	$('#exam_otherSpouse').val(examother);
		 	
		 		 $('#class_otherSpouse').val(classother);
		 	 
		 		 $('#quali_deg_otherSpouse').val(deg_other);
		 	
		 		 $('#quali_spec_otherSpouse').val(spec_other);
		 		 
		 		 fn_otherShowHide(document.getElementById('spouse_div_class'),'other_div_classSpouse','class_otherSpouse');
//		 		 fn_otherShowHide(document.getElementById('spouse_quali'),'other_div_examSpouse','exam_otherSpouse');
//		 		 fn_otherShowHide(document.getElementById('spouse_specialization'),'other_div_qualiSpecSpouse','quali_spec_otherSpouse');
//		 		 fn_otherShowHide(document.getElementById('quali_degree_spouse'),'other_div_qualiDegSpouse','quali_deg_otherSpouse');
		 	 
				 $('#spouse_Qualifications').show();
				  if( curr_marital_status==10  || curr_marital_status==9 || curr_marital_status==11 || curr_marital_status==12 ){
						$("#spouse_quali_radioDiv").show();
						$("#spouse_quali_radio1").prop("checked", true);
						 $('#spouse_quali_labelDiv').hide();
					}
				  else if(curr_marital_status==8){
						$("#marr_quali_radio2").prop("checked", true);
						$("#marr_quali_radio1").prop("disabled", true);
						$('#marriage_remarriageDiv').hide();
						  $('#mrgbtn_save').hide();
							$("#spouse_quali_radioDiv").hide();
						  $('#spouse_quali_labelDiv').show();
						  $('#mrgqualibtn_save').show();
					}
					
			}
		});
	}


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
		

		 fn_other_exam();	
		 fn_otherShowHide(document.getElementById('quali_degree'),'other_div_qualiDeg','quali_deg_other');
		 fn_otherShowHide(document.getElementById('specialization'),'other_div_qualiSpec','quali_spec_other');
		 
		 fn_otherShowHide(document.getElementById('spouse_quali'),'other_div_examSpouse','exam_otherSpouse');
		 fn_otherShowHide(document.getElementById('quali_degree_spouse'),'other_div_qualiDegSpouse','quali_deg_otherSpouse');
		 fn_otherShowHide(document.getElementById('spouse_specialization'),'other_div_qualiSpecSpouse','quali_spec_otherSpouse');
	}



	var other="OTHERS";
	function fn_otherShowHide(obj,Divother_id,other_id){
		var thisText=$("#"+obj.id+" option:selected").text().toUpperCase();
		if(thisText==other || thisText=="OTHER"){
			$('#'+Divother_id).show();
		}
		else{
			$('#'+Divother_id).hide();
			$('#'+other_id).val('');
		}
	}
	function marital_eventchange(){

		var marital_eventvalue=$('#marital_event').val();
		if(marital_eventvalue=='1' || marital_eventvalue=='3'){
			$("#marriage_remarriageDiv").show();
			$("#divorceDiv").hide();
	 		$("#divorce_date").val("");		
			$("#marital_status").val('8');
			$("#spouse_quali_radioDiv").show();
			if(curr_marital_status==13){
				$("#seperateDiv").show();
	 			$("#separated_from_dtDiv").show();
	 			$("#separated_to_dtDiv").show();
	 			$("#separated_to_dt").val('');
	 			getfamily_marriageDetails();
			}
			if(curr_marital_status==8){
				$("#seperateDiv").hide();
	 			$("#separated_from_dtDiv").hide();
	 			$("#separated_to_dtDiv").hide();
	 			$("#separated_to_dt").val('');
	 			$("#separated_from_dt").val('');
			}
			
		}
		else if(marital_eventvalue=='2'){
			$("#marital_status").val('9');
			getfamily_marriageDetails();
			$("#divorceDiv").show();
			$("#divorce_widow_widower_dtlbl").text("Date of Divorce")
			$("#spouse_quali_radioDiv").hide();
			if(curr_marital_status==13){
				$("#seperateDiv").hide();
	 			$("#separated_from_dtDiv").hide();
	 			$("#separated_to_dtDiv").hide();
	 			$("#separated_to_dt").val('');
			}
			if(curr_marital_status==8){
				$("#seperateDiv").hide();
	 			$("#separated_from_dtDiv").hide();
	 			$("#separated_to_dtDiv").hide();
	 			$("#separated_to_dt").val('');
	 			$("#separated_from_dt").val('');
			}
		}
		else if( marital_eventvalue=='4'){
			$("#marital_status").val('13');
			getfamily_marriageDetails();
			$("#spouse_quali_radioDiv").hide();
			$("#divorceDiv").hide();
			$("#seperateDiv").show();
	 		$("#separated_from_dtDiv").show();
		}
		else if( marital_eventvalue=='5'){
			$("#marital_status").val('11');
			getfamily_marriageDetails();
			$("#spouse_quali_radioDiv").hide();
			$("#divorceDiv").show();
			$("#divorce_widow_widower_dtlbl").text("Date of Death")
			if(curr_marital_status==13){
				$("#seperateDiv").hide();
	 			$("#separated_from_dtDiv").hide();
	 			$("#separated_to_dtDiv").hide();
	 			$("#separated_to_dt").val('');
			}
			if(curr_marital_status==8){
				$("#seperateDiv").hide();
	 			$("#separated_from_dtDiv").hide();
	 			$("#separated_to_dtDiv").hide();
	 			$("#separated_to_dt").val('');
	 			$("#separated_from_dt").val('');
			}
//	 		$("#divorce_date").val("");
		}
		else if( marital_eventvalue=='6'){
			$("#marital_status").val('12');
			getfamily_marriageDetails();
			$("#spouse_quali_radioDiv").hide();
			$("#divorceDiv").show();
			$("#divorce_widow_widower_dtlbl").text("Date of Death")
			if(curr_marital_status==13){
				$("#seperateDiv").hide();
	 			$("#separated_from_dtDiv").hide();
	 			$("#separated_to_dtDiv").hide();
	 			$("#separated_to_dt").val('');
			}
			if(curr_marital_status==8){
				$("#seperateDiv").hide();
	 			$("#separated_from_dtDiv").hide();
	 			$("#separated_to_dtDiv").hide();
	 			$("#separated_to_dt").val('');
	 			$("#separated_from_dt").val('');
			}
//	 		$("#divorce_date").val("");
		}
		else{
			$("#marriage_remarriageDiv").hide();
			$("#divorceDiv").hide();
	 //		$("#divorce_date").val("");
			$("#marital_status").val('0');
			$("#spouse_quali_radioDiv").hide();
		}
		
		if((marital_eventvalue==1 && curr_marital_status==10)  || marital_eventvalue==3){
			$("#spouse_quali_radioDiv").show();
		}
		else{
			$("#spouse_quali_radioDiv").hide();
		}
		
		
		}
		






	function fn_setSpecializationSpouse(query){
		
		$.post("getSpecialization?"+key+"="+value, {query:query}).done(function(j) {
			 	
			 	
		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
				for ( var i = 0; i < j.length; i++) {
				
						options += '<option   value="' + j[i] + '" name="'+j[i]+'" >'+ j[i] + '</option>';
					
				}
				
				$("select#spouse_specialization").html(options);
				
			}).fail(function(xhr, textStatus, errorThrown) {
	});
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
		$("#spouse_searchSubject").val(''); 
		 
		 $('.spouse_subjectlist').each(function () {       
		 $(this).show()
		})
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

	function removeSpouseSubFn(value){
		$("input[type='checkbox'][name='spouse_multisub'][value='" + value + "']").attr('checked', false);
		
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
		
	}
	
	
// child details
	chi = 1;
	chi_srno = 1;
	function m_children_details_add_fn(q) {
		if ($('#m_children_details_add' + q).length) {
			$("#m_children_details_add" + q).hide();
		}
		if (q != 0)
		chi_srno += 1;
		chi = q + 1;
		$("table#m_children_details_table").append('<tr id="tr_children'+chi+'">'
			+ '<td class="child_srno">'
			+ chi_srno
			+ '</td>'
			+ '<td style="width: 10%;"> <input type="sib_name'+chi+'" id="sib_name'+chi+'" class="form-control"  autocomplete="off" maxlength="50" onkeypress="return onlyAlphabets(event);" oninput="this.value = this.value.toUpperCase()"></td>'
			+ '<td style="width: 10%;">'
			+' <input type="text" name="sib_date_of_birth'+chi+'" id="sib_date_of_birth'+chi+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
			+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
			+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">'
			+ '</td>'
			+ '<td style="width: 10%;"> <input type="checkbox" id="sib_type'+chi+'" name="sib_type'+chi+'" value="Yes">' 
			+ '</td>'
			+ '<td style="width: 10%;"> <select name="sib_relationship'+chi+'" id="sib_relationship'+chi+'" class="form-control"   >'
			+ '<option value="0">--Select--</option>'
			+ '<c:forEach var="item" items="${getChild_RelationshipList}" varStatus="num">'
			+ '<option value="${item[0]}" name="${item[1]}">${item[1]}</option>'
			+ '</c:forEach></select></td>'
			+ '<td style="width: 10%;"> <input type="checkbox" id="sib_adopted'+chi+'" name="sib_adopted'+chi+'" value="Yes" onclick="adoption('+chi+ ');"></td>'
			+ '<td style="width: 10%;">'
			/* <input type="date" id="sib_adop_date'+chi+'" name="sib_adop_date'+chi+'" class="form-control autocomplete" autocomplete="off"  maxlength="10"  max="${date}" min="1899-01-01" readonly="readonly"></td>' */
			+' <input type="text" name="sib_adop_date'+chi+'" id="sib_adop_date'+chi+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" readonly="readonly" style="width: 85%;display: inline;" '
			+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
			+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">'
			+ '</td>'
			+'<td style="width: 10%;">'
			+'<input type="text" id="aadhar_no'+chi+'" name="aadhar_no'+chi+'"  class="form-control autocomplete" autocomplete="off" maxlength="14" onkeypress="return isAadhar(this,event);">'
			+'</td>'
			+'<td style="width: 10%;">'
			+'<input type="text" id="pan_no'+chi+'" name="pan_no'+chi+'"  class="form-control autocomplete" autocomplete="off" maxlength="10" onchange="isPAN(this);" oninput="this.value = this.value.toUpperCase()">'
			+'</td>'
			+ '<td style="display:none;"><input type="text" id="sib_ch_id'+chi+'" name="sib_ch_id'+chi+'"   value="0" class="form-control autocomplete" autocomplete="off" ></td>'
			
			+ '<td style="width: 5%;"><input type="checkbox" ' + '	id="child-ex' + chi + '" name="child-ex' + chi + '" ' + '	value="Yes"  ' + '	onclick="childCB(' + chi + ');"></td> '
			+ '<td style="width: 10%;"> <select name="child_service' + chi + '" id="child_service' + chi + '" class="form-control-sm form-control"  onchange="otherfunction(' + chi + ')"  >' + '<option value="0">--Select--</option>' + '<c:forEach var="item" items="${getExservicemenList}" varStatus="num">' + '<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' + '</c:forEach></select></td>' + '<td style="width: 15%;"><input type="text" ' + '  maxlength="15"	id="child_personal_no' + chi + '" name="child_personal_no' + chi + '" ' + '	class="form-control" autocomplete="off" ></td> '
			+ '<td style="width: 10%;"><input type="text" ' + '	id="other_child_ser' + chi + '" name="other_child_ser' + chi + '" ' + '	class="form-control" autocomplete="off" maxlength="50" onkeypress="return onlyAlphabets(event);"></td>'
			
			+ '<td style="width: 10%;"><a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "m_children_details_save'
			+ chi
			+ '" onclick="m_children_details_save_fn('
			+ chi
			+ ');" ><i class="fa fa-save"></i></a>'
			+ '<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "m_children_details_add'
			+ chi
			+ '" onclick="m_children_details_add_fn('
			+ chi
			+ ');" ><i class="fa fa-plus"></i></a>'
			+ '<a style="display:none;" class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "m_children_details_remove'
			+ chi
			+ '" onclick="m_children_details_remove_fn('
			+ chi
			+ ');"><i class="fa fa-trash"></i></a>'
			+ '</td></tr>');
		datepicketDate('sib_date_of_birth'+chi);
		datepicketDate('sib_adop_date'+chi);
		childCB(chi);
		otherfunction(chi);
	}
	function m_children_details_remove_fn(R){
		if (R == '1') {
			alert("Can't Delete From Here!! \nPlease Ask Approver to Reject This Entry");
		}
		else{
		var rc = confirm("Are You Sure! You Want To Delete");
		 if(rc){
		var sib_ch_id=$('#sib_ch_id'+R).val();
		  $.post('m_children_details_delete_action_civ?' + key + "=" + value, {sib_ch_id:sib_ch_id }, function(data){ 
				  
				   if(data=='1'){
					   $("tr#tr_children"+R).remove(); 
					   if(R==chi){
							 R = R-1;
							 var temp=true;
							 for(i=R;i>=1;i--){
							 if( $('#m_children_details_add'+i).length )         
							 {
								 $("#m_children_details_add"+i).show();
								 temp=false;
								 chi=i;
								 break;
							 }}
							 
							 if(temp){
								 m_children_details_add_fn(0);
								}
				  			 }
						 $('.child_srno').each(function(i, obj) {
								obj.innerHTML=i+1;
								chi_srno=i+1;
								 });
								 alert("Data Deleted Successfully");
				   }
					 else{
							 
						 alert("Data not Deleted ");
					 }
				   
		 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		  		});
		 }
		}
	}
	//save children
	function m_children_details_save_fn(ps) {
		

		var sib_name = $('#sib_name' + ps).val();
		var sib_date_of_birth = $('#sib_date_of_birth' + ps).val();
		//var sib_type = $('#sib_type' + ps).val();
		if ($('#sib_type' + ps).is(":checked")){
			sib_type = "Yes";
		}
		else{
			sib_type = "No";
		}
		
		var aadhar_no = $('#aadhar_no' + ps).val().split('-').join('');
		var pan_no = $('#pan_no' + ps).val();
		
		var sib_ch_id = $('#sib_ch_id' + ps).val();
		var civ_id = $('#civ_id').val();
		
		

		var child_ser = $("select#child_service"+ps).val();
		var child_pers_no = $("#child_personal_no"+ps).val();
		if($('#child-ex' + ps).is(":checked")){
			var child_ex = "Yes";
		}else{
			var child_ex = "";
		}
		var other_child_ser = $("#other_child_ser"+ps).val();

		 if (sib_name.trim()=="") {
				alert("Please Enter Name");
				$('#sib_name' + ps).focus();
				return false;
			}
		  if (sib_date_of_birth == "DD/MM/YYYY" || sib_date_of_birth.trim()=="") {
				alert("Please Enter Date of Birth");
				$('#sib_date_of_birth' + ps).focus();
				return false;
			} 
		  var currentdate=new Date();
			if(getformatedDate(sib_date_of_birth) > currentdate){
				   alert("Enter Valid Date of Birth ");			  
				   $("input#sib_date_of_birth"+ps).focus();
				   return false;
				   
				}
			if(getformatedDate(sib_date_of_birth) <= getformatedDate($('#dob_date').val())){
				alert("Date of Birth of Child Should Not Greater Than Date of Birth His Father or Mother");
				$("input#sib_date_of_birth"+ps).focus();
				return false;
			}
			
			 var sib_relationship = $('#sib_relationship' + ps).val();
		 if (sib_relationship == 0) {
				alert("Please Select Relationship");
				$('#sib_relationship' + ps).focus();
				return false;
			}
		
			var sib_adop_date = $('#sib_adop_date' + ps).val();
			if ($('#sib_adopted' + ps).is(":checked")){
				sib_adopted = "Yes";
				  if (sib_adop_date == "DD/MM/YYYY" || sib_adop_date.trim()=="") {
						alert("Please Enter Adoption Date");
						$('#sib_adop_date' + ps).focus();
						return false;
					} 
				  if(getformatedDate(sib_date_of_birth) > getformatedDate(sib_adop_date)){
					   alert("Adoption Date Should Be Greater Or Equal To Date of Birth ");			  
					   $("input#sib_adop_date"+ps).focus();
					   return false;
					   
					}
			}
			else{
				sib_adopted = "No";
			}
			 if (aadhar_no.trim()!="" && aadhar_no.length < 12) {
					alert("Please Enter Valid Aadhaar Card No");
					$('#aadhar_no' + ps).val("");
					$('#aadhar_no' + ps).focus();
					return false;
				}
		 
			
		
			var child = $( "#child_service"+ps+" option:selected" ).text();
			if($('#child-ex' + ps).is(":checked")){
				if(child_ser == 0){
					alert("Please Select Child Service");
					$("select#child_service"+ps).focus();
					return false;
				}
				if(child_ser == 1){
				if(child_pers_no.trim()==""){
					alert("Please Enter Child Personal No");
					$("#child_personal_no"+ps).focus();
					return false;
				}
				}
				if(child_pers_no.trim()!=''){
					if(child_pers_no.trim().length < 5 || child_pers_no.trim().length >15){
					alert("Please Enter Valid Child Personal No");
					$("#child_personal_no"+ps).focus();
					return false;
					}
				}
				if (child == "OTHER"){
						if($('#other_child_ser' + ps).val().trim()==""){
							alert("Please Enter Other Child Service");
						$('#other_child_ser' + ps).focus();
						return false;
						}
					}
			}	
			else{
				child="";	
			}


		$.post('m_children_details_action_civ?' + key + "=" + value, {
			sib_name : sib_name,
			sib_date_of_birth : sib_date_of_birth,sib_type:sib_type,
			sib_relationship : sib_relationship,sib_adopted:sib_adopted,sib_adop_date:sib_adop_date,aadhar_no:aadhar_no,pan_no:pan_no,
			sib_ch_id : sib_ch_id,
			civ_id : civ_id,
			child_ser:child_ser,child_pers_no:child_pers_no,child_ex:child_ex,other_child_ser:other_child_ser,child:child
		}, function(data) {

			if (data == "update")
				alert("Data Updated Successfully");
			else if (parseInt(data) > 0) {
				$('#sib_ch_id' + ps).val(data);
				$("#m_children_details_add" + ps).show();
				$("#m_children_details_remove" + ps).show();
				alert("Data Saved Successfully")
			} else
				alert(data);
		}).fail(function(xhr, textStatus, errorThrown) {
			alert("fail to fetch")
		});
	}
	

	//get children
		
	function m_children_Details() {
		
		var civ_id = $('#civ_id').val();
		var i = 0;
		$.post('getm_children_detailsData_Civ?' + key + "=" + value,{civ_id:civ_id},function(j) {
			var v = j.length;
			if (v != 0) {
				
				$('#m_children_detailstbody').empty();
				for (i; i < v; i++) {
					x = i + 1;
					var dob = ParseDateColumncommission(j[i].date_of_birth);
					if(j[i].date_of_adpoted != null){
						var adob = ParseDateColumncommission(j[i].date_of_adpoted);
						}
						else
							var adob ="";
					$("table#m_children_details_table").append('<tr id="tr_children'+x+'">'
						+ '<td class="child_srno">'
						+ x
						+ '</td>'
						+ '<td style="width: 10%;"> <input type="sib_name'+x+'" id="sib_name'+x+'"  value="'+j[i].name+'" class="form-control"  maxlength="50" onkeypress="return onlyAlphabets(event);" oninput="this.value = this.value.toUpperCase()">'
						+ '<td style="width: 10%;">'
						+' <input type="text" name="sib_date_of_birth'+x+'" id="sib_date_of_birth'+x+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
						+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
						+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="'+dob+'">'
						+ '</td>'
						+ '<td style="width: 10%;"> <input type="checkbox" id="sib_type'+x+'" name="sib_type'+x+'" >'
						+ '</td>'
						+ '<td style="width: 10%;"> <select name="sib_relationship'+x+'" id="sib_relationship'+x+'" class="form-control"   >'
						+ '<option value="0">--Select--</option>'
						+ '<c:forEach var="item" items="${getChild_RelationshipList}" varStatus="num">'
						+ '<option value="${item[0]}" name="${item[1]}">${item[1]}</option>'
						+ '</c:forEach></select></td>'
						+ '<td style="width: 10%;"> <input type="checkbox" id="sib_adopted'+x+'" name="sib_adopted'+x+'" value="Yes" onclick="adoption('+ x+ ');"></td>'
						+ '<td style="width: 10%;">'
						/* <input type="date" id="sib_adop_date'+x+'" name="sib_adop_date'+x+'" value="'+adob+'" class="form-control autocomplete" autocomplete="off"  maxlength="10"  max="${date}" min="1899-01-01" readonly="readonly"></td>' */
						+' <input type="text" name="sib_adop_date'+x+'" id="sib_adop_date'+x+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" readonly="readonly" style="width: 85%;display: inline;" '
						+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
						+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="'+adob+'">'
						+ '</td>'
						+'<td style="width: 10%;">'
						+'<input type="text" id="aadhar_no'+x+'" name="aadhar_no'+x+'" value="'+j[i].aadhar_no+'" class="form-control autocomplete" autocomplete="off" maxlength="14" onkeypress="return isAadhar(this,event);">'
						+'</td>'
						+'<td style="width: 10%;">'
						+'<input type="text" id="pan_no'+x+'" name="pan_no'+x+'"  value="'+j[i].pan_no+'" class="form-control autocomplete" autocomplete="off" maxlength="10" onchange="isPAN(this);" oninput="this.value = this.value.toUpperCase()">'
						+'</td>'
						+ '<td style="display:none;"><input type="text" id="sib_ch_id'+x+'" name="sib_ch_id'+x+'"   value="'+j[i].id+'"  class="form-control autocomplete" autocomplete="off" ></td>'

						+ '<td style="width: 5%;"><input type="checkbox" ' + '	id="child-ex' + x + '" name="child-ex' + x + '" ' + '	value="Yes"  ' + '	onclick="childCB(' + x + ');"></td> '
						+ '<td style="width: 10%;"> <select name="child_service' + x + '" id="child_service' + x + '" class="form-control-sm form-control"  onchange="otherfunction(' + x + ')"  >' + '<option value="0">--Select--</option>' + '<c:forEach var="item" items="${getExservicemenList}" varStatus="num">' + '<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' + '</c:forEach></select></td>' + '<td style="width: 15%;"><input type="text" ' + '	maxlength="15" id="child_personal_no' + x + '" name="child_personal_no' + x + '" ' + '	class="form-control" autocomplete="off" value="' + j[i].child_personal_no + '" ></td> '
						+ '<td style="width: 10%;"><input type="text" ' + '	id="other_child_ser' + x + '" name="other_child_ser' + x + '" ' + '	class="form-control" autocomplete="off" maxlength="50" onkeypress="return onlyAlphabets(event);"></td>'
						
						
						+ '<td style="width: 10%;"><a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "m_children_details_save'+ x + '" onclick="m_children_details_save_fn('+ x+ ');" ><i class="fa fa-save"></i></a>'
						+ '<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "m_children_details_add'+ x+ '" onclick="m_children_details_add_fn('+ x+ ');" ><i class="fa fa-plus"></i></a>'
                      + '<a  class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "m_children_details_remove'+ x+ '" onclick="m_children_details_remove_fn('+ x + ');"><i class="fa fa-trash"></i></a>'
						+ '<td style="display:none;"><input type="text" id="status'+x+'" name="status'+x+'"   value="'+j[i].status+'"  class="form-control autocomplete" autocomplete="off" ></td>'
						+ '</td></tr>');
					$('#sib_relationship' + x).val(j[i].relationship);
					$('#sib_type' + x).val(j[i].type);
					$('#child_service' + x).val(j[i].child_service);
					$('#other_child_ser' + x).val(j[i].other_child_ser);
					if(j[i].if_child_ser =="Yes"){
						$("input[type=checkbox][name='child-ex"+x+"']").prop("checked",true);
					}
					if(j[i].type =="Yes"){
						$("input[type=checkbox][name='sib_type"+x+"']").prop("checked",true);
					}
					
					if(j[i].adoted =="Yes"){
						$("input[type=checkbox][name='sib_adopted"+x+"']").prop("checked",true);
						$('#sib_adop_date' + x).attr('readonly', false);
					}
					datepicketDate('sib_date_of_birth'+x);
					datepicketDate('sib_adop_date'+x);
					isAadhar(document.getElementById('aadhar_no'+x))
					childCB(x);
					otherfunction(x);
					
				}
				chi = v;
				chi_srno = v;
				$('#m_children_details_add' + chi).show();
			}
		});
	}
	function childCB(a) {
		if ($('#child-ex'+a).is(":checked"))
		{
			$('#child_service' + a).attr('readonly', false);
			$('#child_personal_no' + a).attr('readonly', false);
		}
		else{
//	 		$('#ser-ex' + a).attr('readonly', true);
			$('#child_service' + a).attr('readonly', true);
			$('#child_personal_no' + a).attr('readonly', true);
			$('#other_child_ser' + a).attr('readonly', true);
			$('#child_service' + a).val(0);
			$('#child_personal_no' + a).val("");
			$('#other_child_ser' + a).val("");
		}
		
	}
	function otherfunction(k){
		//26-01-1994
		var child = $( "#child_service"+k+" option:selected" ).text();
		if ($( "#child_service"+k ).val() == "0" || !($('#child-ex'+k).is(":checked"))){
			$('#other_child_ser' + k).attr('readonly', true);
			$('#child_personal_no' + k).attr('readonly', true);
			$('#child_personal_no' + k).val('');
		}
		else if (child == "OTHER"){
			$('#other_child_ser' + k).attr('readonly', false);
			$('#child_personal_no' + k).attr('readonly', true);
			$('#child_personal_no' + k).val('');
		}
		else if (child != "OTHER"){
			$('#other_child_ser' + k).attr('readonly', true);
			$('#child_personal_no' + k).attr('readonly', false);
			$('#other_child_ser' + k).val('');
		}
		
	}
	function adoption(a) {
//			var nk=$("input[type=checkbox][name='sib_adopted"+a+"']").val();
//			//var nk =$('#sib_adopted' + a).val();
//			if(nk = "Yes"){
//				//alert("11111");
//				$('#sib_adop_date' + a).attr('readonly', false);
//			}
//			else{
//				$('#sib_adop_date' + a).attr('readonly', true);
//			}
		if ($('#sib_adopted'+a).is(":checked"))
		{
			$('#sib_adop_date' + a).attr('readonly', false);
		}
		else{
			$('#sib_adop_date' + a).attr('readonly', true);
			$('#sib_adop_date' + a).val("");
		}
	}	
	
	
	

	function spouse_addressFn(){
		if($("#check_spouse_address").is(':checked'))
		    $("#spouse_addressInnerdiv").show();  // checked
		else
		    $("#spouse_addressInnerdiv").hide();
	}

	function validate_changeaddress_details_save(){
		$("input#addr_authority").val($("input#addr_authority").val().trim());
		 var pre_country_text = $("#pre_country option:selected").text().toUpperCase();
		if ($("select#pre_country").val() == "0") {
			alert("Please Select Present Domicile Country");
			$("select#pre_country").focus();
			return false;
		}
	   var text1 = $("#pre_country option:selected").text().toUpperCase();
		
		if((text1 == "OTHERS" || text1 == "OTHER") && $("input#pre_country_other").val().trim()==""){
			alert("Please Enter Present Domicile Country Other");
			$("input#pre_country_other").focus();
			return false;
		}
		
		if(text1 == "OTHERS" || text1 == "OTHER"){
			if(lengthValidation($("input#pre_country_other").val().trim(),auth_length)){
				alert("Domicile Country Other Length Should be Less or Equal To 100")
				$("#pre_country_other").focus();
				return false;
			}
		}
		
		 if ($("select#pre_domicile_state").val() == "0") {
			alert("Please Select Present Domicile State/Province");
			$("select#pre_domicile_state").focus();
			return false;
		} 
		 var text2 = $("#pre_domicile_state option:selected").text();
			
			if((text2 == "OTHERS" || text2 == "OTHER") && $("input#pre_domicile_state_other").val().trim()==""){
				alert("Please Enter Present Domicile State/Province Other");
				$("input#pre_domicile_state_other").focus();
				return false;
			}
			
			if(text2 == "OTHERS" || text2 == "OTHER"){
				if(lengthValidation($("input#pre_domicile_state_other").val().trim(),auth_length)){
					alert("Domicile State/Province Other Length Should be Less or Equal To 100")
					$("#pre_domicile_state_other").focus();
					return false;
				}
			}
			
		if ($("select#pre_domicile_district").val() == "0") {
			alert("Please Select Present Domicile District");
			$("select#pre_domicile_district").focus();
			return false;
		} 
		var text3 = $("#pre_domicile_district option:selected").text();
		
		if((text3 == "OTHERS" || text3 == "OTHER") && $("input#pre_domicile_district_other").val().trim()==""){
			alert("Please Enter Present Domicile District Other");
			$("input#pre_domicile_district_other").focus();
			return false;
		}
		
		if(text3 == "OTHERS" || text3 == "OTHER"){
			if(lengthValidation($("input#pre_domicile_district_other").val().trim(),auth_length)){
				alert("Domicile District Other Length Should be Less or Equal To 100")
				$("#pre_domicile_district_other").focus();
				return false;
			}
		}
		
		if(pre_country_text!="BHUTAN"){
			if ($("select#pre_domicile_tesil").val() == "0") {
				alert("Please Select Present Domicile Tehsil/Municipality");
				$("select#pre_domicile_tesil").focus();
				return false;
			}
		  var text4 = $("#pre_domicile_tesil option:selected").text().toUpperCase();
			
			if((text4 == "OTHERS" || text4 == "OTHER") && $("input#pre_domicile_tesil_other").val().trim()==""){
				alert("Please Enter Present Domicile Tehsil/Municipality Other");
				$("input#pre_domicile_tesil_other").focus();
				return false;
			}
			
			if(text4 == "OTHERS" || text4 == "OTHER"){
				if(lengthValidation($("input#pre_domicile_tesil_other").val().trim(),auth_length)){
					alert("Domicile Tehsil/Municipality Other Length Should be Less or Equal To 100")
					$("#pre_domicile_tesil_other").focus();
					return false;
				}
			}
		}
		if ($("select#per_home_addr_country").val() == "0") {
			alert("Please Select The Permanent Address Country");
			$("select#per_home_addr_country").focus();
			return false;
		}
		 var text5 = $("#per_home_addr_country option:selected").text().toUpperCase();
			
			if((text5 == "OTHERS" || text5 == "OTHER" ) && $("input#per_home_addr_country_others").val().trim()==""){
				alert("Please Enter Permanent Address Country Other");
				$("input#per_home_addr_country_others").focus();
				return false;
			}
			
			

			if(text5 == "OTHERS" || text5 == "OTHER"){
					if(lengthValidation($("input#per_home_addr_country_others").val().trim(),auth_length)){
						alert("Permanent Address Country Other Length Should be Less or Equal To 100")
						$("#per_home_addr_country_others").focus();
						return false;
					}
				}
			
		if ($("select#per_home_addr_state").val() == "0") {
			alert("Please Select Permanent Address State/Province");
			$("select#per_home_addr_state").focus();
			return false;
		}
		 var text6 = $("#per_home_addr_state option:selected").text().toUpperCase();
			
			if((text6 == "OTHERS" || text6 == "OTHER") && $("input#per_home_addr_state_others").val().trim()==""){
				alert("Please Enter Permanent Address State/Province Other");
				$("input#per_home_addr_state_others").focus();
				return false;
			}
			if(text6 == "OTHERS" || text6 == "OTHER"){
				if(lengthValidation($("input#per_home_addr_state_others").val().trim(),auth_length)){
					alert("Permanent Address State/Province Other Length Should be Less or Equal To 100")
					$("#per_home_addr_state_others").focus();
					return false;
				}
			}
			
			
		if ($("select#per_home_addr_district").val() == "0") {
			alert("Please Select Permanent Address District");
			$("select#per_home_addr_district").focus();
			return false;
		}
		 var text7 = $("#per_home_addr_district option:selected").text().toUpperCase();
			
			if((text7 == "OTHERS" || text7 == "OTHER") && $("input#per_home_addr_district_others").val().trim()==""){
				alert("Please Enter Permanent Address District Other");
				$("input#per_home_addr_district_others").focus();
				return false;
			}
			if(text7 == "OTHERS" || text7 == "OTHER"){
				if(lengthValidation($("input#per_home_addr_district_others").val().trim(),auth_length)){
					alert("Permanent Address District Other Length Should be Less or Equal To 100")
					$("#per_home_addr_district_others").focus();
					return false;
				}
			}
			
			var per_home_addr_country_text = $("#per_home_addr_country option:selected").text().toUpperCase();
			if(per_home_addr_country_text!="BHUTAN"){
				
		
				if ($("select#per_home_addr_tehsil").val() == "0") {
					alert("Please Select Permanent Address Tehsil/Municipality");
					$("select#per_home_addr_tehsil").focus();
					return false;
				}
				var text8 = $("#per_home_addr_tehsil option:selected").text().toUpperCase();
				
				if((text8 == "OTHERS"|| text8 == "OTHER") && $("input#per_home_addr_tehsil_others").val().trim()==""){
					alert("Please Enter Permanent Address Tehsil/Municipality Other");
					$("input#per_home_addr_tehsil_others").focus();
					return false;
					}
				
				if(text8 == "OTHERS" || text8 == "OTHER"){
					if(lengthValidation($("input#per_home_addr_tehsil_others").val().trim(),auth_length)){
						alert("Permanent Address Tehsil/Municipality Other Length Should be Less or Equal To 100")
						$("#per_home_addr_tehsil_others").focus();
						return false;
					}
				}
			}
		if ($("input#per_home_addr_village").val().trim()=="") {
			alert("Please Enter Permanent Address Village/Town/City");
			$("input#per_home_addr_village").focus();
			return false;
		}
		if ($("input#per_home_addr_pin").val().trim()=="" || $("input#per_home_addr_pin").val().length<6 || $("input#per_home_addr_pin").val().length>6) {
			alert("Please Enter Permanent Address Pin");
			$("input#per_home_addr_pin").focus();
			return false;
		}
		if ($("input#per_home_addr_rail").val().trim()=="") {
			alert("Please Enter Permanent Address Nearest Railway Station");
			$("input#per_home_addr_rail").focus();
			return false;
		}
		 var a = $('input:radio[name=per_home_addr_rural_urban]:checked').val();
			if(a == undefined){	
				alert("Please select The Permanent Address  Rural /Urban/Semi Urban ");
				return false;
				}
			var b = $('input:radio[name=border_area]:checked').val();
			if(b == undefined){	
				alert("Please select The Permanent Address Border area ");
				return false;
				}
			if ($("select#pers_addr_country").val() == "0") {
				alert("Please Select Present Address Country");
				$("select#pers_addr_country").focus();
				return false;
			}
			var text9 = $("#pers_addr_country option:selected").text().toUpperCase();
			
			if((text9 == "OTHERS"|| text9 == "OTHER") && $("input#pers_addr_country_other").val().trim()==""){
				alert("Please Enter Present Address Country Other");
				$("input#pers_addr_country_other").focus();
				return false;
			}
			
			if(text9 == "OTHERS" || text9 == "OTHER"){
				if(lengthValidation($("input#pers_addr_country_other").val().trim(),auth_length)){
					alert("Present Address Country Other Length Should be Less or Equal To 100")
					$("#pers_addr_country_other").focus();
					return false;
				}
			}

			
			if ($("select#pers_addr_state").val() == "0") {
				alert("Please select Present Address State/Province");
				$("select#pers_addr_state").focus();
				return false;
			}
	  var text10 = $("#pers_addr_state option:selected").text().toUpperCase();
			
			if((text10 == "OTHERS"|| text10 == "OTHER") && $("input#pers_addr_state_other").val().trim()==""){
				alert("Please Enter Present Address State/Province Other");
				$("input#pers_addr_state_other").focus();
				return false;
			}
			
			if(text10 == "OTHERS" || text10 == "OTHER"){
				if(lengthValidation($("input#pers_addr_state_other").val().trim(),auth_length)){
					alert("Present Address State/Province Other Length Should be Less or Equal To 100")
					$("#pers_addr_state_other").focus();
					return false;
				}
			}
			
			if ($("select#pers_addr_district").val() == "0") {
				alert("Please select Present Address District");
				$("select#pers_addr_district").focus();
				return false;
			}
	var text11 = $("#pers_addr_district option:selected").text().toUpperCase();
			
			if((text11 == "OTHERS"|| text11 == "OTHER") && $("input#pers_addr_district_other").val().trim()==""){
				alert("Please Enter Present Address District Other");
				$("input#pers_addr_district_other").focus();
				return false;
			}
			
			if(text11 == "OTHERS" || text11 == "OTHER"){
				if(lengthValidation($("input#pers_addr_district_other").val().trim(),auth_length)){
					alert("Present Address District Other Length Should be Less or Equal To 100")
					$("#pers_addr_district_other").focus();
					return false;
				}
			}
			
			var pers_addr_country_text = $("#pers_addr_country option:selected").text().toUpperCase();
			if(pers_addr_country_text!="BHUTAN"){
				
			
				if ($("select#pers_addr_tehsil").val() == "0") {
					alert("Please Select Present Address Tehsil/Municipality");
					$("select#pers_addr_tehsil").focus();
					return false;
				}
			var text11 = $("#pers_addr_tehsil option:selected").text().toUpperCase();
				
				if((text11 == "OTHERS"|| text11 == "OTHER") && $("input#pers_addr_tehsil_other").val().trim()==""){
					alert("Please Enter Present Address Tehsil/Municipality Other");
					$("input#pers_addr_tehsil_other").focus();
					return false;
				}
				
				if(text11 == "OTHERS" || text11 == "OTHER"){
					if(lengthValidation($("input#pers_addr_tehsil_other").val().trim(),auth_length)){
						alert("Present Address Tehsil/Municipality Other Length Should be Less or Equal To 100")
						$("#pers_addr_tehsil_other").focus();
						return false;
					}
				}
				
			}
		if ($("input#pers_addr_village").val().trim()=="") {
			alert("Please Enter Present Address Village/Town/City");
			$("input#pers_addr_village").focus();
			return false;
		}
		if ($("input#pers_addr_pin").val().trim()=="" || $("input#pers_addr_pin").val().length<6  || $("input#pers_addr_pin").val().length>6) {
			alert("Please Enter Present Address Pin");
			$("input#pers_addr_pin").focus();
			return false;
		}
		if ($("input#pers_addr_rail").val().trim()=="") {
			alert("Please Enter Present Address Nearest Railway Station");
			$("input#pers_addr_rail").focus();
			return false;
		}
		var c = $('input:radio[name=pers_addr_rural_urban]:checked').val();
		if(c == undefined){	
			alert("Please select The  Present Address Rural /Urban/Semi Urban ");
			return false;
			}
		
		if($("#check_spouse_address").is(':checked')){
			
			if ($("select#spouse_addr_Country").val() == "0") {
				alert("Please select SF ACCN Address Country");
				$("select#spouse_addr_Country").focus();
				return false;
			}	
	var text12 = $("#spouse_addr_Country option:selected").text().toUpperCase();
			
			if((text12 == "OTHERS"|| text12 == "OTHER") && $("input#spouse_addr_country_other").val().trim()==""){
				alert("Please Enter SF ACCN Address Country Other");
				$("input#spouse_addr_country_other").focus();
				return false;
			}
			
			
			if(text12 == "OTHERS" || text12 == "OTHER"){
				if(lengthValidation($("input#spouse_addr_country_other").val().trim(),auth_length)){
					alert("SF ACCN Address Country Other Length Should be Less or Equal To 100")
					$("#spouse_addr_country_other").focus();
					return false;
				}
			}
			
			
			if ($("select#spouse_addr_state").val() == "0") {
				alert("Please select SF ACCN Address State/Province");
				$("select#spouse_addr_state").focus();
				return false;
			}
	var text13 = $("#spouse_addr_state option:selected").text().toUpperCase();
			
			if((text13 == "OTHERS"|| text13 == "OTHER") && $("input#spouse_addr_state_other").val().trim()==""){
				alert("Please Enter SF ACCN Address State/Province Other");
				$("input#spouse_addr_state_other").focus();
				return false;
			}
			
			if(text13 == "OTHERS" || text13 == "OTHER"){
				if(lengthValidation($("input#spouse_addr_state_other").val().trim(),auth_length)){
					alert("SF ACCN Address State/Province Other Length Should be Less or Equal To 100")
					$("#spouse_addr_state_other").focus();
					return false;
				}
			}
			
			if ($("select#spouse_addr_district").val() == "0") {
				alert("Please select SF ACCN Address District");
				$("select#spouse_addr_district").focus();
				return false;
			}
	var text14 = $("#spouse_addr_district option:selected").text().toUpperCase();
			
			if((text14 == "OTHERS"|| text14 == "OTHER") && $("input#spouse_addr_district_other").val().trim()==""){
				alert("Please Enter SF ACCN Address District Other");
				$("input#spouse_addr_district_other").focus();
				return false;
			}
			
			if(text14 == "OTHERS" || text14 == "OTHER"){
				if(lengthValidation($("input#spouse_addr_district_other").val().trim(),auth_length)){
					alert("SF ACCN Address District Other Length Should be Less or Equal To 100")
					$("#spouse_addr_district_other").focus();
					return false;
				}
			}
			
			var spouse_addr_Country_text = $("#spouse_addr_Country option:selected").text().toUpperCase();
			if(spouse_addr_Country_text!="BHUTAN"){
				if ($("select#spouse_addr_tehsil").val() == "0") {
					alert("Please Select SF ACCN Address Tehsil/Municipality");
					$("select#spouse_addr_tehsil").focus();
					return false;
				}
			var text15 = $("#spouse_addr_tehsil option:selected").text().toUpperCase();
				
				if((text15 == "OTHERS"|| text15 == "OTHER") && $("input#spouse_addr_tehsil_other").val().trim()==""){
					alert("Please Enter SF ACCN Address Tehsil/Municipality Other");
					$("input#spouse_addr_tehsil_other").focus();
					return false;
				}
				
				if(text15 == "OTHERS" || text15 == "OTHER"){
					if(lengthValidation($("input#spouse_addr_district_other").val().trim(),auth_length)){
						alert("SF ACCN Address Tehsil/Municipality Other Length Should be Less or Equal To 100")
						$("#spouse_addr_district_other").focus();
						return false;
					}
				}
				
			}
			if ($("input#spouse_addr_village").val().trim()=="") {
				alert("Please Enter SF ACCN Address Village/Town/City");
				$("input#spouse_addr_village").focus();
				return false;
			}	
			if ($("input#spouse_addr_pin").val().trim()=="" || $("input#spouse_addr_pin").val().length<6 || $("input#spouse_addr_pin").val().length>6)  {
				alert("Please Enter valid SF ACCN Address Pin");
				$("input#spouse_addr_pin").focus();
				return false;
			}	
			if ($("input#spouse_addr_rail").val().trim()=="") {
				alert("Please Enter SF ACCN Address Nearest Railway Station");
				$("input#spouse_addr_rail").focus();
				return false;
			}
			var spouserus = $('input:radio[name=spouse_addr_rural_urban]:checked').val();
			if(spouserus == undefined) {
				alert("Please select SF ACCN Address Is  Rural /Urban/Semi Urban");
				return false;
			}
			
			if ($("input#Stn_HQ_sus_no").val().trim()=="") {
				alert("Please Enter SF ACCN Address Stn HQ SUS No");
				$("input#Stn_HQ_sus_no").focus();
				return false;
			}
			if ($("input#Stn_HQ_unit_name").val().trim()=="") {
				alert("Please Enter SF ACCN Address Stn HQ SUS Name");
				$("input#Stn_HQ_unit_name").focus();
				return false;
			}
		}
		
		
		var formvalues=$("#form_addr_details_form").serialize();
		
		var addr_ch_id=$("#addr_ch_id").val();	
			var civ_id=$("#civ_id").val();
			var enroll_date=$("#enroll_date").val();
			var marital_status = $('#marital_status_p').text();
			formvalues+="&addr_ch_id="+addr_ch_id+"&civ_id="+civ_id+"&marital_status="+marital_status+"&enroll_date="+enroll_date;	
			formvalues+="&pre_country_text="+pre_country_text+"&spouse_addr_Country_text="+spouse_addr_Country_text+"&pers_addr_country_text="+pers_addr_country_text;
			formvalues+="&per_home_addr_country_text="+per_home_addr_country_text;
			 $.post('changeaddress_details_CIVaction?' + key + "=" + value,formvalues, function(data){
				 if(data=="update")
		        	  alert("Data Updated Successfully");
		          else if(parseInt(data)>0){
		        	  $("#addr_ch_id").val(data);	
		        	  alert("Data Saved Successfully")
		          }
		          else
		        	  alert(data)
		 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		  		});
	}
	function get_changeaddress_details(){
			
		var civ_id=$("#civ_id").val();	
		if($("#marital_status_p").text() == '8'){
			$("#spouse_addressMaindiv").show();
		}
		else{
			$("#spouse_addressMaindiv").hide();
		}
		$.post('address_details_GetCivData?' + key + "=" + value,{civ_id:civ_id }, function(j){
			var v=j.length;
			if(v!=0){
				$("#pre_country").val(j[0].pre_country);
				fn_pre_domicile_Country();
				$("#pre_domicile_state").val(j[0].pre_state);
				fn_pre_domicile_state();
				$("#pre_domicile_district").val(j[0].pre_district);
				fn_pre_domicile_district();
				$("#pre_domicile_tesil").val(j[0].pre_tesil);
				$("#addr_authority").val(j[0].authority);
				$('#addr_date_authority').val(ParseDateColumncommission(j[0].date_authority)); 
				$("#per_home_addr_country").val(j[0].permanent_country);
				fn_per_home_addr_Country();
				$("#per_home_addr_state").val(j[0].permanent_state);
				fn_per_home_addr_state();
				$("#per_home_addr_district").val(j[0].permanent_district);
				fn_per_home_addr_district();
				$("#per_home_addr_village").val(j[0].permanent_village);
				$("#per_home_addr_tehsil").val(j[0].permanent_tehsil);
				$("#per_home_addr_pin").val(j[0].permanent_pin_code);
				$("#per_home_addr_rail").val(j[0].permanent_near_railway_station);
				 var permanent= j[0].permanent_rural_urban_semi;
	           if(permanent == "rural"){
	                   $("#per_home_addr_rural").prop("checked", true);
	            } 
	           if(permanent == "urban"){
	                   $("#per_home_addr_urban").prop("checked", true);
	            }
	           if(permanent == "semi_urban")
	           {
	                   $("#per_home_addr_semi_urban").prop("checked", true);
	            }  
	           var br= j[0].permanent_border_area;
	           if(br == "yes"){
	                   $("#border_area").prop("checked", true);
	            } 
	           if(br == "no"){
	                   $("#border_area1").prop("checked", true);
	            } 
	           $("#pers_addr_country").val(j[0].present_country);
	           fn_pers_addr_Country();
	           $("#pers_addr_state").val(j[0].present_state);
	           fn_pers_addr_state();
	           $("#pers_addr_district").val(j[0].present_district);
	           fn_pers_addr_district();
				$("#pers_addr_village").val(j[0].present_village);
				$("#pers_addr_tehsil").val(j[0].present_tehsil);
				$("#pers_addr_pin").val(j[0].present_pin_code);
				$("#pers_addr_rail").val(j[0].present_near_railway_station);
				var present= j[0].present_rural_urban_semi;
		            if(present == "rural"){
		                    $("#pers_addr_rural").prop("checked", true);
		            } 
		            if(present == "urban")
		            {
		                    $("#pers_addr_urban").prop("checked", true);
		             }
		            if(present == "semi_urban")
		            {
		                    $("#pers_addr_semi_urban").prop("checked", true);
		             }  
				$("#retire_country").val(j[0].retire_country);
				$("#retire_village").val(j[0].retire_village);
				$("#retire_tehsil").val(j[0].retire_tehsil);
				$("#retire_district").val(j[0].retire_district);
				$("#retire_state").val(j[0].retire_state);
				$("#retire_pin").val(j[0].retire_pin);
				$("#retire_rail").val(j[0].retire_near_railway_station);
				var retire= j[0].retire_rural_urban_semi;
		            if(retire == "rural"){
		                    $("#retire_rural").prop("checked", true);
		        } 
		            if(retire == "urban")
		            {
		                    $("#retire_urban").prop("checked", true);
		             }
		            if(retire == "semi_urban")
		            {
		                    $("#retire_semi_urban").prop("checked", true);
		             }  
		            var text6 = $("#pre_country option:selected").text();
					if(text6 == "OTHERS"){
						$("#pre_country_other").val(j[0].pre_country_other);
						$("#add_other_id").show();
					}
					else{
						$("#add_other_id").hide();
					}
					
					var text7 = $("#pre_domicile_state option:selected").text();
					if(text7 == "OTHERS"){
						$('#pre_domicile_state_other').val(j[0].pre_domicile_state_other);
						$("#add_other_st").show();
					}
					else{
						$("#add_other_st").hide();
					}
					var text8 = $("#pre_domicile_district option:selected").text();
					if(text8 == "OTHERS"){
						$("#pre_domicile_district_other").val(j[0].pre_domicile_district_other);
						$("#add_other_dt").show();
					}
					else{
						$("#add_other_dt").hide();
					}
					var text9 = $("#pre_domicile_tesil option:selected").text();
					if(text9 == "OTHERS"){
						$("#pre_domicile_tesil_other").val(j[0].pre_domicile_tesil_other);
						$("#add_other_te").show();
					}
					else{
						$("#add_other_te").hide();
					}
					var text10 = $("#per_home_addr_country option:selected").text();
					if(text10 == "OTHERS"){
						$("#per_home_addr_country_others").val(j[0].per_home_addr_country_other);
						$("#per_home_addr_country_other_hid").show();
					}
					else{
						$("#per_home_addr_country_other_hid").hide();
					}
					var text11 = $("#per_home_addr_state option:selected").text();
					if(text11 == "OTHERS"){
						$("#per_home_addr_state_others").val(j[0].per_home_addr_state_other);
						$("#per_home_addr_state_other_hid").show();
					}
					else{
						$("#per_home_addr_state_other_hid").hide();
					}
					var text12 = $("#per_home_addr_district option:selected").text();
					if(text12 == "OTHERS"){
						$("#per_home_addr_district_others").val(j[0].per_home_addr_district_other);
						$("#per_home_addr_district_other_hid").show();
					}
					else{
						$("#per_home_addr_district_other_hid").hide();
					}
					var text13 = $("#per_home_addr_tehsil option:selected").text();
					if(text13 == "OTHERS"){
						$("#per_home_addr_tehsil_others").val(j[0].per_home_addr_tehsil_other);
						$("#per_home_addr_tehsil_other_hid").show();
					}
					else{
						$("#per_home_addr_tehsil_other_hid").hide();
					}
					var text14 = $("#pers_addr_country option:selected").text();
					if(text14 == "OTHERS"){
						$("#pers_addr_country_other").val(j[0].pers_addr_country_other);
						$("#pers_addr_country_other_hid").show();
					}
					else{
						$("#pers_addr_country_other_hid").hide();
					}
					var text15 = $("#pers_addr_state option:selected").text();
					if(text15 == "OTHERS"){
						$("#pers_addr_state_other").val(j[0].pers_addr_state_other);
						$("#pers_addr_state_other_hid").show();
					}
					else{
						$("#pers_addr_state_other_hid").hide();
					}
					var text16 = $("#pers_addr_district option:selected").text();
					if(text16 == "OTHERS"){
						$("#pers_addr_district_other").val(j[0].pers_addr_district_other);
						$("#pers_addr_district_other_hid").show();
					}
					else{
						$("#pers_addr_district_other_hid").hide();
					}
					var text17 = $("#pers_addr_tehsil option:selected").text();
					if(text17 == "OTHERS"){
						$("#pers_addr_tehsil_other").val(j[0].pers_addr_tehsil_other);
						$("#pers_addr_tehsil_other_hid").show();
					}
					else{
						$("#pers_addr_tehsil_other_hid").hide();
					}
					
					
					
					
					
				$("#addr_ch_id").val(j[0].id);
				$("#addr_pending_ch_id").val(j[0].id);
				if($("#marital_status_p").text() == '8' && j[0].spouse_country != 0){
					$("#check_spouse_address").attr("checked", true);
					spouse_addressFn();
					$("#spouse_addr_Country").val(j[0].spouse_country);
					$("#spouse_addr_country_other").val(j[0].spouse_addr_country_other);
					fn_spouse_addr_Country();
					$("#spouse_addr_state").val(j[0].spouse_state);
					$("#spouse_addr_state_other").val(j[0].spouse_addr_state_other);
					fn_spouse_addr_state();
					$("#spouse_addr_district").val(j[0].spouse_district);
					$("#spouse_addr_district_other").val(j[0].spouse_addr_district_other);
					fn_spouse_addr_district();
					$("#spouse_addr_tehsil").val(j[0].spouse_tehsil);
					fn_spouse_addr_tehsil();
					$("#spouse_addr_tehsil_other").val(j[0].spouse_addr_tehsil_other);
					$("#spouse_addr_village").val(j[0].spouse_village);
					$("#spouse_addr_pin").val(j[0].spouse_pin_code);
					$("#spouse_addr_rail").val(j[0].spouse_near_railway_station);
					$("#Stn_HQ_sus_no").val(j[0].stn_hq_sus_no);
					var sus_no = j[0].stn_hq_sus_no;			      	
					 $.post("getTargetUnitNameListStnHQ?"+key+"="+value, {
							 sus_no:sus_no
			         }, function(j) {
			                
			         }).done(function(j) {
			        	 var length = j.length-1;
			             var enc = j[length].substring(0,16);
			             $("#Stn_HQ_unit_name").val(dec(enc,j[0]));   
			                 
			         }).fail(function(xhr, textStatus, errorThrown) {
		        	 });
					 $("input[name=spouse_addr_rural_urban][value=" + j[0].spouse_rural_urban_semi + "]").prop('checked', true);

				}
				
			}
		  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
			});
	}
	function copy_address(){
	    if($("#check_address").prop('checked') == true){                
	            $("#pers_addr_village").val($("#per_home_addr_village").val());
	            $("#pers_addr_country").val($("#per_home_addr_country").val());
	            $("#pers_addr_country_other").val($("#per_home_addr_country_others").val());
	            fn_pers_addr_Country();
	            $("#pers_addr_state").val($("#per_home_addr_state").val());
	            $("#pers_addr_state_other").val($("#per_home_addr_state_others").val());
	            
	            fn_pers_addr_state();
	            $("#pers_addr_district").val($("#per_home_addr_district").val());
	            $("#pers_addr_district_other").val($("#per_home_addr_district_others").val());
	            fn_pers_addr_district();
	            $("#pers_addr_tehsil").val($("#per_home_addr_tehsil").val());
	            $("#pers_addr_tehsil_other").val($("#per_home_addr_tehsil_others").val());
	            fn_pers_addr_tehsil();
	            $("#pers_addr_pin").val($("#per_home_addr_pin").val());
	            $("#pers_addr_rail").val($("#per_home_addr_rail").val());
	            
	            $("input[name=pers_addr_rural_urban]").prop('checked', false);
	   		 var value= $('input:radio[name=per_home_addr_rural_urban]:checked').val()
	   		            $("input[name=pers_addr_rural_urban][value=" + value + "]").prop('checked', true);
	    }
	    else{
	    	 $("#pers_addr_village").val("");
	         $("#pers_addr_tehsil").val("0");
	         $("#pers_addr_district").val("0");
	         $("#pers_addr_state").val("0");
	         $("#pers_addr_district_other").val("");
	         $("#pers_addr_country_other").val("");
	         $("#pers_addr_state_other").val("");
	         $("#pers_addr_tehsil_other").val("");
	         $("#pers_addr_pin").val("");
	         $("#pers_addr_rail").val("");
	         $("#pers_addr_country").val("0");
	         fn_pers_addr_Country();
	           
	            $("input[name=pers_addr_rural_urban]").attr('checked', false);
	    }
	}


	function fn_pre_domicile_state() {
		var text = $("#pre_domicile_state option:selected").text();
		
		if(text == "OTHERS"){
			$("#add_other_st").show();
		}
		else{
			$("#add_other_st").hide();
			$("#pre_domicile_state_other").val("");
	}
		 var state_id =$('select#pre_domicile_state').val();

		 		 	$.post("getDistrictListFormState1?"+key+"="+value, {state_id:state_id}).done(function(j) {
		 		 				 	
		 		 			 	
		 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
		 				for ( var i = 0; i < j.length; i++) {
		 				
		 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
		 					
		 				}
		 				
		 				$("select#pre_domicile_district").html(options);
		 				fn_pre_domicile_district();
		 			}).fail(function(xhr, textStatus, errorThrown) {
		 	});
	}

	function fn_pre_domicile_district() {
		
		var text = $("#pre_domicile_district option:selected").text();
		
		if(text == "OTHERS"){
			$("#add_other_dt").show();
		}
		else{
			$("#add_other_dt").hide();
			$("#pre_domicile_district_other").val("");
	}
		 var Dist_id = $('select#pre_domicile_district').val();

		 		 	$.post("getTehsilListFormDistrict1?"+key+"="+value, {Dist_id:Dist_id}).done(function(j) {
		 		 				 	
		 		 			 	
		 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
		 				for ( var i = 0; i < j.length; i++) {
		 				
		 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
		 					
		 				}
		 				
		 				$("select#pre_domicile_tesil").html(options);

		 			}).fail(function(xhr, textStatus, errorThrown) {
		 	});
	}
	function fn_pre_domicile_tesil() {
		
		var text = $("#pre_domicile_tesil option:selected").text();
		
		if(text == "OTHERS"){
			$("#add_other_te").show();
		}
		else{
			$("#add_other_te").hide();
			$("#pre_domicile_tesil_other").val("");
	}
	}
	function fn_per_home_addr_Country() {
		var text = $("#per_home_addr_country option:selected").text();
		
		if(text == "OTHERS"){
			$("#per_home_addr_country_other_hid").show();
		}
		else{
			$("#per_home_addr_country_other_hid").hide();
			$("#per_home_addr_country_others").val("");
		}
		var obj=document.getElementById('per_home_addr_country');
		FnTehsilDisableEnable(obj,'per_home_addr_tehsil','per_home_addr_tehsil_other_hid','per_home_addr_tehsil_others');
		 var contry_id = $('select#per_home_addr_country').val();

		 		 	$.post("getStateListFormcon1?"+key+"="+value, {contry_id:contry_id}).done(function(j) {
		 		 				 	
		 		 			 	
		 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
		 				for ( var i = 0; i < j.length; i++) {
		 				
		 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
		 					
		 				}
		 				
		 				$("select#per_home_addr_state").html(options);
		 				fn_per_home_addr_state();
		 				fn_per_home_addr_district();
		 				fn_per_home_addr_tehsil();
		 			}).fail(function(xhr, textStatus, errorThrown) {
		 	});
	}

	function fn_per_home_addr_state() {
		var text = $("#per_home_addr_state option:selected").text();
		
		if(text == "OTHERS"){
			$("#per_home_addr_state_other_hid").show();
		}
		else{
			$("#per_home_addr_state_other_hid").hide();
			$("#per_home_addr_state_others").val("");
	}

		 var state_id =$('select#per_home_addr_state').val(); 

		 		 	$.post("getDistrictListFormState1?"+key+"="+value, {state_id:state_id}).done(function(j) {
		 		 				 	
		 		 			 	
		 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
		 				for ( var i = 0; i < j.length; i++) {
		 			
		 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
		 					
		 				}
		 				
		 				$("select#per_home_addr_district").html(options);
		 				fn_per_home_addr_district();
		 				//onPermHomeAddrState();
		 			     
		 				
		 			}).fail(function(xhr, textStatus, errorThrown) {
		 	});
	}

	function fn_pre_domicile_Country() {
		var text = $("#pre_country option:selected").text();
		
		if(text == "OTHERS"){
			$("#add_other_id").show();
		}
		else{
			$("#add_other_id").hide();
			$("#pre_country_other").val("");
		}
		
		var obj=document.getElementById('pre_country');
		FnTehsilDisableEnable(obj,'pre_domicile_tesil','add_other_te','pre_domicile_tesil_other');
		
		 var contry_id = $('select#pre_country').val();

		 		 	$.post("getStateListFormcon1?"+key+"="+value, {contry_id:contry_id}).done(function(j) {
		 		 				 	
		 		 			 	
		 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
		 				for ( var i = 0; i < j.length; i++) {
		 				
		 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
		 					
		 				}
		 				
		 				$("select#pre_domicile_state").html(options);
		 				fn_pre_domicile_state();
		 				fn_pre_domicile_district();
		 				fn_pre_domicile_tesil(); 
		 			}).fail(function(xhr, textStatus, errorThrown) {
		 	});
	}

	function fn_per_home_addr_district() {
		
		var text = $("#per_home_addr_district option:selected").text();
		
		if(text == "OTHERS"){
			$("#per_home_addr_district_other_hid").show();
		}
		else{
			$("#per_home_addr_district_other_hid").hide();
			$("#per_home_addr_district_others").val("");
	}
		 var Dist_id = $('select#per_home_addr_district').val();

		 		 	$.post("getTehsilListFormDistrict1?"+key+"="+value, {Dist_id:Dist_id}).done(function(j) {
		 		 				 	
		 		 			 	
		 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
		 				for ( var i = 0; i < j.length; i++) {
		 				
		 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
		 					
		 				}
		 				
		 				$("select#per_home_addr_tehsil").html(options);

		 			}).fail(function(xhr, textStatus, errorThrown) {
		 	});
	}
	function fn_per_home_addr_tehsil() {
	var text = $("#per_home_addr_tehsil option:selected").text();

	if(text == "OTHERS"){
		$("#per_home_addr_tehsil_other_hid").show();
	}
	else{
		$("#per_home_addr_tehsil_other_hid").hide();
		$("#per_home_addr_tehsil_others").val("");
	}
	}
	function fn_pers_addr_Country() {
		var text = $("#pers_addr_country option:selected").text();
		
		if(text == "OTHERS"){
			$("#pers_addr_country_other_hid").show();
		}
		else{
			$("#pers_addr_country_other_hid").hide();
			$("#pers_addr_country_other").val("");
		}
		var obj=document.getElementById('pers_addr_country');
		FnTehsilDisableEnable(obj,'pers_addr_tehsil','pers_addr_tehsil_other_hid','pers_addr_tehsil_other');
		 var contry_id = $('select#pers_addr_country').val();

		 		 	$.post("getStateListFormcon1?"+key+"="+value, {contry_id:contry_id}).done(function(j) {
		 		 				 	
		 		 			 	
		 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
		 				for ( var i = 0; i < j.length; i++) {
		 				
		 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
		 					
		 				}
		 				
		 				$("select#pers_addr_state").html(options);
						fn_pers_addr_state();
						fn_pers_addr_district();
						fn_pers_addr_tehsil();
		 			}).fail(function(xhr, textStatus, errorThrown) {
		 	});
	}

	function fn_pers_addr_state() {
		var text = $("#pers_addr_state option:selected").text();
		
		if(text == "OTHERS"){
			$("#pers_addr_state_other_hid").show();
		}
		else{
			$("#pers_addr_state_other_hid").hide();
			$("#pers_addr_state_other").val("");
		}

		 var state_id = $('select#pers_addr_state').val();

		 		 	$.post("getDistrictListFormState1?"+key+"="+value, {state_id:state_id}).done(function(j) {
		 		 				 	
		 		 			 	
		 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
		 				for ( var i = 0; i < j.length; i++) {
		 					

		 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
		 				
		 				}
		 				
		 				$("select#pers_addr_district").html(options);
						fn_pers_addr_district();
		 			}).fail(function(xhr, textStatus, errorThrown) {
		 	});
	}
	function fn_pers_addr_district() {
	var text = $("#pers_addr_district option:selected").text();
		
		if(text == "OTHERS"){
			$("#pers_addr_district_other_hid").show();
		}
		else{
			$("#pers_addr_district_other_hid").hide();
			$("#pers_addr_district_other").val("");
		}


		 var Dist_id = $('select#pers_addr_district').val();

		 		 	$.post("getTehsilListFormDistrict1?"+key+"="+value, {Dist_id:Dist_id}).done(function(j) {
		 		 				 	
		 		 			 	
		 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
		 				for ( var i = 0; i < j.length; i++) {
		 				
		 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
		 					
		 				}
		 				
		 				$("select#pers_addr_tehsil").html(options);

		 			}).fail(function(xhr, textStatus, errorThrown) {
		 	});
		 		 	
	}
	function fn_pers_addr_tehsil() {
		var text = $("#pers_addr_tehsil option:selected").text();
			
			if(text == "OTHERS"){
				$("#pers_addr_tehsil_other_hid").show();
			}
			else{
				$("#pers_addr_tehsil_other_hid").hide();
				$("#pers_addr_tehsil_other").val("");
			}
	}
	function fn_spouse_addr_Country() {
		var text = $("#spouse_addr_Country option:selected").text();
		
		if(text == "OTHERS"){
			$("#spouse_addr_Country_hid").show();
		}
		else{
			$("#spouse_addr_Country_hid").hide();
			$("#spouse_addr_country_other").val("");
		}
		var obj=document.getElementById('spouse_addr_Country');
		FnTehsilDisableEnable(obj,'spouse_addr_tehsil','spouse_addr_tehsil_hid','spouse_addr_tehsil_other');
		 var contry_id = $('select#spouse_addr_Country').val();

		 		 	$.post("getStateListFormcon1?"+key+"="+value, {contry_id:contry_id}).done(function(j) {
		 		 				 	
		 		 			 	
		 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
		 				for ( var i = 0; i < j.length; i++) {
		 				
		 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
		 					
		 				}
		 				
		 				$("select#spouse_addr_state").html(options);
						fn_spouse_addr_state();
						fn_spouse_addr_district();
						fn_spouse_addr_tehsil()
		 			}).fail(function(xhr, textStatus, errorThrown) {
		 	});
	}
	
	
	
	
	

	function fn_spouse_addr_state() {
	var text = $("#spouse_addr_state option:selected").text();
		
		if(text == "OTHERS"){
			$("#spouse_addr_state_hid").show();
		}
		else{
			$("#spouse_addr_state_hid").hide();
			$("#spouse_addr_state_other").val("");
		}

		 var state_id = $('select#spouse_addr_state').val();

		 		 	$.post("getDistrictListFormState1?"+key+"="+value, {state_id:state_id}).done(function(j) {
		 		 				 	
		 		 			 	
		 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
		 				for ( var i = 0; i < j.length; i++) {
		 					

		 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
		 				
		 				}
		 				
		 				$("select#spouse_addr_district").html(options);
						fn_spouse_addr_district();
		 			}).fail(function(xhr, textStatus, errorThrown) {
		 	});
	}

	function fn_spouse_addr_district() {
	var text = $("#spouse_addr_district option:selected").text();
		
		if(text == "OTHERS"){
			$("#spouse_addr_district_hid").show();
		}
		else{
			$("#spouse_addr_district_hid").hide();
			$("#spouse_addr_district_other").val("");
		}

		 var Dist_id = $('select#spouse_addr_district').val();

		 		 	$.post("getTehsilListFormDistrict1?"+key+"="+value, {Dist_id:Dist_id}).done(function(j) {
		 		 				 	
		 		 			 	
		 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
		 				for ( var i = 0; i < j.length; i++) {
		 				
		 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
		 					
		 				}
		 				
		 				$("select#spouse_addr_tehsil").html(options);
		 				fn_spouse_addr_tehsil();
		 			}).fail(function(xhr, textStatus, errorThrown) {
		 	});
		 		 	
	}
	function fn_spouse_addr_tehsil() {
		var text = $("#spouse_addr_tehsil option:selected").text();
			
			if(text == "OTHERS"){
				$("#spouse_addr_tehsil_hid").show();
			}
			else{
				$("#spouse_addr_tehsil_hid").hide();
				$("#spouse_addr_tehsil_other").val("");
			}
	}

	function spouse_addressFn(){
		if($("#check_spouse_address").is(':checked'))
		    $("#spouse_addressInnerdiv").show();  // checked
		else{
		    $("#spouse_addressInnerdiv").hide();
		    $("#spouse_addr_Country").val("0");
		    $("#spouse_addr_state").val("0");
		    $("#spouse_addr_district").val("0");
		    $("#spouse_addr_tehsil").val("0");
		    $("#spouse_addr_village").val("");
		    $("#spouse_addr_pin").val("");
		    $("#spouse_addr_rail").val("");
		    $("#spouse_addr_rural").prop('checked', false);
		    $("#spouse_addr_urban").prop('checked', false);
		    $("#spouse_addr_semi_urban").prop('checked', false);
		    $("#Stn_HQ_sus_no").val("");
		    $("#Stn_HQ_unit_name").val("");
		 	    
		}
	}
	
	
	
	

	function fn_nok_Country() {
		
		var text = $("#nok_country option:selected").text();
		
		if(text == "OTHERS"){
			$("#nok_other_id").show();
		}
		else{
			$("#nok_other_id").hide();
			$("#ctry_other").val("");
		}	

		var obj=document.getElementById('nok_country');
		FnTehsilDisableEnable(obj,'nok_tehsil','nok_other_te','nok_tehsil_other');
			 var contry_id =$('select#nok_country').val();

			 		 	$.post("getStateListFormcon1?"+key+"="+value, {contry_id:contry_id}).done(function(j) {
			 		 				 	
			 		 			 	
			 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
			 				for ( var i = 0; i < j.length; i++) {
			 				
			 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
			 					
			 				}
			 				
			 				$("select#nok_state").html(options);
							fn_nok_state();
							fn_nok_district();
			 			}).fail(function(xhr, textStatus, errorThrown) {
			 	});
		}
		
		
		
	function fn_nok_state() {
		
		var text = $("#nok_state option:selected").text();
		if(text == "OTHERS"){
			$("#nok_other_st").show();
		}
		else{
			$("#nok_other_st").hide();
			$("#st_other").val("");
		}	

		 var state_id = $('select#nok_state').val();

		 		 	$.post("getDistrictListFormState1?"+key+"="+value, {state_id:state_id}).done(function(j) {
		 		 				 	
		 		 			 	
		 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
		 				for ( var i = 0; i < j.length; i++) {
		 				
		 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
		 					
		 				}
		 				
		 				$("select#nok_district").html(options);
						fn_nok_district();
		 			}).fail(function(xhr, textStatus, errorThrown) {
		 	});
	}	

	function fn_nok_district() {
		
		var text = $("#nok_district option:selected").text();
		
		if(text == "OTHERS"){
			$("#nok_other_dist").show();
		}
		else{
			$("#nok_other_dist").hide();
			$("#dist_other").val("");
		}
		
		 var Dist_id = $('select#nok_district').val();

		 		 	$.post("getTehsilListFormDistrict1?"+key+"="+value, {Dist_id:Dist_id}).done(function(j) {
		 		 				 	
		 		 			 	
		 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
		 				for ( var i = 0; i < j.length; i++) {
		 				
		 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
		 					
		 				}
		 				
		 				$("select#nok_tehsil").html(options);

		 			}).fail(function(xhr, textStatus, errorThrown) {
		 	});
	}
	function fn_nok_tehsil() {
		
		var text = $("#nok_tehsil option:selected").text();
		
		if(text == "OTHERS"){
			$("#nok_other_te").show();
		}
		else{
			$("#nok_other_te").hide();
			$("#nok_tehsil_other").val("");
		}
	}	

	
	function get_nokaddress_details(){
		var civ_id=$("#civ_id").val();	
		$.post('nok_details_GetData_Civ?' + key + "=" + value,{civ_id:civ_id }, function(j){
			var v=j.length;
				if(v!=0){
					//////nok//////
					$("#authority").val(j[0].authority);
					$('#date_authority').val(ParseDateColumncommission(j[0].date_authority)); 
					$("#nok_name").val(j[0].nok_name);
					$("#nok_relation").val(j[0].nok_relation);
					$("#nok_country").val(j[0].nok_country);
					fn_nok_Country();
					$("#nok_state").val(j[0].nok_state);
					fn_nok_state();
					$("#nok_district").val(j[0].nok_district);
					fn_nok_district();
					$("#nok_tehsil").val(j[0].nok_tehsil);
					$("#nok_village").val(j[0].nok_village);
					$("#nok_pin").val(j[0].nok_pin);
					$("#nok_rail").val(j[0].nok_near_railway_station);
					/* $("#ctry_other").val(j[0].ctry_other);
					$("#st_other").val(j[0].st_other);
					$("#dist_other").val(j[0].dist_other);
					$("#tehsil_other").val(j[0].tehsil_other); */
					$("#nok_mobile_no").val(j[0].nok_mobile_no);
					 var nok= j[0].nok_rural_urban_semi;
			            if(nok == "rural"){
			                    $("#nok_rural").prop("checked", true);
			             } 
			            if(nok == "urban"){
			                    $("#nok_urban").prop("checked", true);
			             }
			            if(nok == "semi_urban")
			            {
			                    $("#nok_semi_urban").prop("checked", true);
			             }  
			            
			            var text6 = $("#nok_country option:selected").text();
						if(text6 == "OTHERS"){
							$("#ctry_other").val(j[0].ctry_other);
							$("#nok_other_id").show();
						}
						else{
							$("#nok_other_id").hide();
						}
						
						var text7 = $("#nok_state option:selected").text();
						if(text7 == "OTHERS"){
							$('#st_other').val(j[0].st_other);
							$("#nok_other_st").show();
						}
						else{
							$("#nok_other_st").hide();
						}
						var text8 = $("#nok_district option:selected").text();
						if(text8 == "OTHERS"){
							$("#dist_other").val(j[0].dist_other);
							$("#nok_other_dist").show();
						}
						else{
							$("#nok_other_dist").hide();
						}
						var text9 = $("#nok_tehsil option:selected").text();
						if(text9 == "OTHERS"){
							$("#nok_tehsil_other").val(j[0].tehsil_other);
							$("#nok_other_te").show();
						}
						else{
							$("#nok_other_te").hide();
						}
					$("#nok_id").val(j[0].id);
				}
			  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
				});
		} 
	
	function qualification_save_fn(qs) {

		
		var dateofBirthyear =$("#dob").text().split('-')[2];
		var currentdate = new Date();
		var currentyear = currentdate.getFullYear();
		var type = $('#quali_type').val();
		var examination_pass = $('#quali').val();
		var exam_other_qua=$('#exam_other').val();
	  	var degree_other=$('#quali_deg_other').val();
		var spec_other=$('#quali_spec_other').val();
		var class_other_qua=$('#class_other').val();
		
		var passing_year = $('#yrOfPass').val();
		var degree = $('#quali_degree').val();
		var specialization = $('#specialization').val();
		var div_class = $('#div_class').val();
		var institute = $('#institute_place').val();
		var qualification_ch_id = $('#qualification_ch_id').val();
		
		var civ_id = $('#civ_id').val();
		 var qual_authority=$('#qualification_authority').val();
			var qual_doa=$('#qualification_date_of_authority').val();
			 if(qual_authority == null || qual_authority.trim()==""){ 
					alert("Please Enter Authority");
					$("input#qualification_authority").focus();
					return false;
				}
			 if(lengthValidation($("input#qualification_authority").val().trim(),auth_length)){
					alert("Authority Length Should be Less or Equal To 100");
					$("#qualification_authority").focus();
					return false;

				}
			 
			 if(qual_doa == null || qual_doa.trim()=="" || qual_doa == "DD/MM/YYYY"){ 
					alert("Please Enter Date of Authority");
					$("input#qualification_date_of_authority").focus();
					return false;
				} 
// 			  if(getformatedDate($("input#enroll_date").val()) > getformatedDate($("#qualification_date_of_authority").val())) {
//                     alert("Authority Date should be Greater than Enrollment Date");
//                 	$("input#qualification_date_of_authority").focus();
//                     return false;
//                }
			  
		var subjectvar = $('input[name="multisub"]:checkbox:checked').map(function() {
			return this.value;
		}).get();
		var subject = subjectvar.join(",");
		

		if(type == null || type == "0") {
			alert("Please Select Qualification Type");
			$("#quali_type").focus();
			return false;
		}
	
		if(examination_pass == null || examination_pass == "0") {
			alert("Please Select Examination Pass");
			$("#quali").focus();
			return false;
		}
		var exam_other=$("#quali option:selected").text().toUpperCase();
		  if(exam_other==other || exam_other=="OTHER") {
	     	 if(exam_other_qua == null || exam_other_qua.trim()==""){ 	 
	     		alert( "Please Enter Examination Other ");
				
				$("input#exam_other").focus();
				return false;
			   }
	     	if(lengthValidation($("input#exam_other").val().trim(),auth_length)){
				alert("Examination Other Length Should be Less or Equal To 100")
				$("#exam_other").focus();
				return false;
			}
	      }
		
		if(degree == null || degree == "0") {
			alert("Please Select The Degree Acquired");
			$("#quali_degree").focus();
			return false;
		}
		var deg_other=$("#quali_degree option:selected").text().toUpperCase();
		  if(deg_other==other || deg_other=="OTHER") {
		      	 if(degree_other == null || degree_other.trim()==""){ 	       
		      		alert( "Please Enter Degree Other ");
		 			$("input#quali_deg_other").focus();
		 			return false;
		 		   }
		     	if(lengthValidation($("input#quali_deg_other").val().trim(),auth_length)){
					alert("Degree Other Length Should be Less or Equal To 100")
					$("#quali_deg_other").focus();
					return false;
				}
		       }
		if(specialization == null || specialization == "0") {
			alert("Please Select The Specialization");
			$("#specialization").focus();
			return false;
		}
		
	

	
	
      var spe_other=$("#specialization option:selected").text().toUpperCase();
    
      if(spe_other==other || spe_other=="OTHER") {
       	 if(spec_other == null || spec_other.trim()==""){ 	 
        
  			alert( "Please Enter Specialization Other ");
  			$("input#quali_spec_other").focus();
  			return false;
  		   }
       	if(lengthValidation($("input#quali_spec_other").val().trim(),auth_length)){
			alert("Specialization Other Length Should be Less or Equal To 100")
			$("#quali_spec_other").focus();
			return false;
		}
        }
      
      
      
      
	if(passing_year.trim()=="") {
		alert("Please Enter Year of Passing");
		$("input#yrOfPass").focus();
		return false;
	}
	if(passing_year.trim() != "") {
		if(parseInt(passing_year) <= parseInt(dateofBirthyear) || parseInt(passing_year) > parseInt(currentyear)) {
			alert("Please Enter Valid Year of Passing");
			$("input#yrOfPass").focus();
			return false;
		}
	}
	if(div_class == "0") {
		alert("Please Select Div/Grade/PCT(%)");
		$("#div_class").focus();
		return false;
	}
	var div_other=$("#div_class option:selected").text().toUpperCase();
	  if(div_other==other || div_other=="OTHER") {
    	 if(class_other_qua == null || class_other_qua.trim()==""){ 	 
     
			alert( "Please Enter Div/Grade/PCT(%) Other ");
			$("input#class_other").focus();
			return false;
		   }
    	 
    		if(lengthValidation($("input#class_other").val().trim(),auth_length)){
				alert("Div/Grade/PCT(%) Other Length Should be Less or Equal To 100")
				$("#class_other").focus();
				return false;
			}
     }
	if(subject.trim()=="") {
		alert("Please Select The Subject");
		$("select#sub_quali").focus();
		return false;
	}
	if(institute.trim()=="") {
		alert("Please Enter Institute & place");
		$("#institute_place").focus();
		return false;
	}
	
		$.post('update_qualification_Civ_action?' + key + "=" + value, {
		
			type: type,
			examination_pass: examination_pass,
			passing_year: passing_year,
			div_class: div_class,
			subject: subject,
			institute: institute,
			qualification_ch_id: qualification_ch_id,				
			degree: degree,
			specialization: specialization,
			civ_id: civ_id,exam_other_qua:exam_other_qua,class_other_qua:class_other_qua,
			degree_other:degree_other,spec_other:spec_other,
			dateofBirthyear: dateofBirthyear,
			qual_authority:qual_authority,
			qual_doa:qual_doa
		}, function(data) {
			if(parseInt(data) > 0) {
				alert("Data Saved Successfully")
			} else alert(data)
			$('#quali').val('0');		
			$('#yrOfPass').val('');
			$('#quali_degree').val('0');
			$('#specialization').val('0');
			$('#div_class').val('0');
			$('#institute_place').val('');
			$('#qualification_ch_id').val('0');
			$("input[type=checkbox][name='multisub']").prop("checked", false);
			
			$("#sub_quali option:first").text('Select Subjects');
			$("#quali_sub_list").empty();
			var typethisT = document.getElementById('quali_type');
			fn_qualification_typeChange(typethisT,'quali','quali_degree','specialization');
			var qualithisT = document.getElementById('quali');
			fn_ExaminationTypeChange(qualithisT,'quali_degree','specialization');
		
			fn_other_class();
			getQualifications();
		}).fail(function(xhr, textStatus, errorThrown) {
			alert("fail to fetch")
		});
	}
 function getQualifications() {
		var civ_id = $('#civ_id').val();
		var i = 0;
		$.post('getQualificationCivData?' + key + "=" + value, {
			civ_id: civ_id
		}, function(j) {
			var v = j.length;
			$('#qualificationtbody').empty();
			if(v != 0) {
				
				for(i; i < v; i++) {
					qu = i + 1;
					var examother="--";
					var classother="--";
					var deg_other="--";
					var spec_other="--";
					var exampass = "--";				
					var deg_name = "--";
					var specialization = "--";
					if(j[i].exp_name != null) exampass = j[i].exp_name;
				
					if(j[i].d_name != null) deg_name = j[i].d_name;
					if(j[i].spce_name != null) specialization = j[i].spce_name;
					
					if(j[i].exam_other!=null){
						examother=j[i].exam_other;
						exampass=j[i].exam_other;
					}
			  	    
			  	  if(j[i].degree_other!=null){
			  		deg_name=j[i].degree_other;
			  		deg_other=j[i].degree_other;
			  	  }
			  	   if(j[i].specialization_other!=null)
			  		   {
			  		 specialization=j[i].specialization_other;
			  		spec_other=j[i].specialization_other;
			  		   }
			  	    

					var divclass = $('#div_class option[value="' + j[i].div_class + '"]').text();
					 if(j[i].class_other!=null){
				  	    	classother=j[i].class_other;
				  	    	divclass=j[i].class_other;
					 }
					var subjectslist = j[i].subject.split(',');
					var subjects = "";
					for(k = 0; k < subjectslist.length; k++) {
						if(k != 0) subjects += ",";
						subjects += $("input[type=checkbox][name='multisub'][value='" + subjectslist[k] + "']").parent().text();
					}
					var Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Qualification ?') ){editQualificationData(" + j[i].id + "," + j[i].type + ",'" +  j[i].examination_pass + "'," + j[i].passing_year + ",'" + j[i].degree + "','" + j[i].specialization + "'," + j[i].div_class + ",'" + j[i].subject + "','" + j[i].institute + "','" + examother + "','" + classother + "','" + deg_other + "','" + spec_other + "','" + j[i].authority + "','" + ParseDateColumncommission(j[i].date_of_authority) + "')}else{ return false;}\"";
					f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
					var Delete = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Qualification ?') ){deleteQualificationData(" + j[i].id + ")}else{ return false;}\"";
					f1 = "<i class='action_icons action_delete' " + Delete + " title='Delete Data'></i>";
					var action = f + f1;
					$("table#qualificationtable").append('<tr>' + '<td style="font-size: 15px;text-align: center ;width: 10%;">' + qu + '</td> ' +  '<td style="font-size: 15px;text-align: center ;width: 10%;">' + j[i].authority + '</td> ' +  '<td style="font-size: 15px;text-align: center ;width: 10%;">' + convertDateFormate(j[i].date_of_authority) + '</td> ' + '<td style="font-size: 15px;text-align: center">' + j[i].label + '</td>	' + '<td style="font-size: 15px;text-align: center">' + exampass + '</td> ' 
					 + '<td style="font-size: 15px;text-align: center">' + deg_name + '</td>' +   '<td style="font-size: 15px;text-align: center">' + specialization + '</td>' +
					'<td style="font-size: 15px;text-align: center">' + j[i].passing_year + '</td>' + '<td style="font-size: 15px;text-align: center">' + subjects + '</td>' + '<td style="font-size: 15px;text-align: center">' 
					+ divclass + '</td>'  + '<td style="font-size: 15px;text-align: center">' + j[i].institute + '</td>' 
					 + '<td class="hide-action" style="font-size: 15px; text-align: center;">' + action + '</td>' + '</tr>');
				}
			} else {
				$("table#qualificationtable").append('<tr>' + '<td style="font-size: 15px; text-align: center; color: red;" colspan="12">Data Not Available</td>' + '</tr>');
			}
		});
	}

	function editQualificationData(id, type, exampass, passing_year, degree,  specialization, div_class, subject, institute,examother,classother,deg_other,spec_other,authority,doa) {
		console.log(exampass+" "+degree+" "+specialization);
		$("#qualification_authority").val(authority);
		$("#qualification_date_of_authority").val(doa);
		$('#yrOfPass').val(passing_year);
		$('#div_class').val(div_class);
		$('#institute_place').val(institute);
		$('#qualification_ch_id').val(id);
		$("input[type=checkbox][name='multisub']").prop("checked", false);
		var subjectslist = subject.split(',');
		for(k = 0; k < subjectslist.length; k++) {
			$("input[type=checkbox][name='multisub'][value='" + subjectslist[k] + "']").prop("checked", true);
			$("#sub_quali option:first").text('Subjects(' + subjectslist.length + ')');
		}
		$('#quali_sub_list').empty();
		$("input[type='checkbox'][name='multisub']").each(function() {
			if(this.checked) {
				$('#quali_sub_list').append("<span class='subspan'>" + this.parentElement.innerText + "<i class='fa fa-times' style='margin: 5px;  font-size: 15px;' onclick='removeSubFn(" + this.value + ")'></i><span> <br>");
			}
		});
		$('#quali_type').val(type);
		var typethisT = document.getElementById('quali_type');
		fn_qualification_typeChange(typethisT,'quali','quali_degree','specialization');
		if(exampass != '--') {
			
			$('#quali').val(exampass);
			var qualithisT = document.getElementById('quali');
			fn_ExaminationTypeChange(qualithisT,'quali_degree','specialization');
		}
		
		 if(examother!='--')
		  	$('#exam_other').val(examother);
		 if(classother!='--')
			 $('#class_other').val(classother);
		 if(deg_other!='--')
			 $('#quali_deg_other').val(deg_other);
		
		 
		
		  
		
		if(degree != '--') {
			$('#quali_degree').val(degree);
			
		}
		if(specialization != '--') $('#specialization').val(specialization);
		 if(spec_other!='--')
			 $('#quali_spec_other').val(spec_other);
		 fn_other_exam();
		 fn_other_class();
		 fn_otherShowHide(document.getElementById('quali_degree'),'other_div_qualiDeg','quali_deg_other');
		 fn_otherShowHide(document.getElementById('specialization'),'other_div_qualiSpec','quali_spec_other');
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
	  		$("#class_other").val('');
	  	}
	  }

	function deleteQualificationData(id) {
		var qualification_ch_id = id;
		$.post('qualification_delete_CIVaction?' + key + "=" + value, {
			qualification_ch_id: qualification_ch_id
		}, function(data) {
			if(data == '1') {
				getQualifications();
				alert("Data Deleted Successfully");
			} else {
				alert("Data not Deleted ");
			}
		}).fail(function(xhr, textStatus, errorThrown) {
			alert("fail to fetch")
		});
	}


</script>
<script>
function validate_save_tenure_details(){


var ten_from=$('#ten_from').val();

var ten_to=$('#ten_to').val();
		if ($("input#unit_sus_no").val() == "") {
			alert("Please Enter Unit Sus No");
			$("input#unit_sus_no").focus();
			return false;
		} 
 		if ($("input#unit_posted_to").val() == "") {
			alert("Please Enter Unit name");
			$("input#unit_posted_to").focus();
			return false;
		}
	 
		if ($("#ten_to").val() == "DD/MM/YYYY" || $("#ten_to").val().trim()=="") {
			alert("Please Select From Date");
			$("#ten_to").focus();
			return false;
		} 
	
 		if ($("#ten_from").val() == "DD/MM/YYYY" || $("#ten_from").val().trim()=="") {
			alert("Please Select To Date");
			$("#ten_from").focus();
			return false;
		} 
		
  var formvalues=$("#form_tenure_details_form").serialize();
  
	var civ_id =$("#civ_id").val();
	var ten_id=$("#ten_id").val();	
	
	formvalues+="&ten_id="+ten_id+"&civ_id="+civ_id;	
	$.post('tenure_details_action_civ?' + key + "=" + value,formvalues, function(data){
		

			 if(data=="update")
	        	  alert("Data Updated Successfully");
	          else if(parseInt(data)>0)
	          {
	        	  $("#ten_id").val(data);	
	        	  alert("Data Saved Successfully")
	          }
	          else
	        	  alert(data)
	 	  		}).fail(function(xhr, textStatus, errorThrown) 
	 	  			{
	 	  			alert("fail to fetch")
	 	  		
	  		});
}
function get_changetenure_details(){
	var civ_id=$("#civ_id").val();	
	$.post('tenure_details_GetData_Civ?' + key + "=" + value,{civ_id:civ_id }, function(j){
		var v=j.length;
			if(v!=0){
				
				$("#unit_sus_no").val(j[0].sus_no);
				$('#unit_posted_to').val(j[0].unit_name); 
				
				$('#ten_to').val(ParseDateColumncommission(j[0].to_date)); 
				$('#ten_from').val(ParseDateColumncommission(j[0].from_date)); 
		       
					
					
				$("#ten_id").val(j[0].id);
			}
		  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
			});
	} 
$("#unit_sus_no").keyup(function(){
	var sus_no = this.value;
	var susNoAuto=$("#unit_sus_no");

	susNoAuto.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
	        type: 'POST',
	        url: "getTargetSUSNoList?"+key+"="+value,
	        data: {sus_no:sus_no},
	          success: function( data ) {
	        	  var susval = [];
                  var length = data.length-1;
                  var enc = data[length].substring(0,16);
                        for(var i = 0;i<data.length;i++){
                                susval.push(dec(enc,data[i]));
                        }
                        var dataCountry1 = susval.join("|");
                        var myResponse = []; 
				        var autoTextVal=susNoAuto.val();
				$.each(dataCountry1.toString().split("|"), function(i,e){
					var newE = e.substring(0, autoTextVal.length);
					if (e.toLowerCase().includes(autoTextVal.toLowerCase())) {
					  myResponse.push(e);
					}
				});      	          
				response( myResponse ); 
	          }
	        });
	      },
		  minLength: 1,
	      autoFill: true,
	      change: function(event, ui) {
	    	 if (ui.item) {   	        	  
	        	  return true;    	            
	          } else {
	        	  alert("Please Enter Approved Unit SUS No.");
	        	  document.getElementById("unit_name").value="";
	        	  susNoAuto.val("");	        	  
	        	  susNoAuto.focus();
	        	  return false;	             
	          }   	         
	    }, 
		select: function( event, ui ) {
			var sus_no = ui.item.value;			      	
			 $.post("getTargetUnitNameList?"+key+"="+value, {
				 sus_no:sus_no
         }, function(j) {
                
         }).done(function(j) {
        	 var length = j.length-1;
             var enc = j[length].substring(0,16);
             $("#unit_posted_to").val(dec(enc,j[0]));   
                 
         }).fail(function(xhr, textStatus, errorThrown) {
         });
		} 	     
	});	
});

// unit name
 $("input#unit_posted_to").keypress(function(){
		var unit_name = this.value;
			 var susNoAuto=$("#unit_posted_to");
			  susNoAuto.autocomplete({
			      source: function( request, response ) {
			        $.ajax({
			        	type: 'POST',
				    	url: "getTargetUnitsNameActiveList?"+key+"="+value,
			        data: {unit_name:unit_name},
			          success: function( data ) {
			        	 var susval = [];
			        	  var length = data.length-1;
			        	  var enc = data[length].substring(0,16);
				        	for(var i = 0;i<data.length;i++){
				        		susval.push(dec(enc,data[i]));
				        	}
				        	   	          
						response( susval ); 
			          }
			        });
			      },
			      minLength: 1,
			      autoFill: true,
			      change: function(event, ui) {
			    	 if (ui.item) {   	        	  
			        	  return true;    	            
			          } else {
			        	  alert("Please Enter Approved Unit Name.");
			        	  document.getElementById("unit_posted_to").value="";
			        	  susNoAuto.val("");	        	  
			        	  susNoAuto.focus();
			        	  return false;	             
			          }   	         
			      }, 
			      select: function( event, ui ) {
			    	 var unit_name = ui.item.value;
			    
				            $.post("getTargetSUSFromUNITNAME?"+key+"="+value,{target_unit_name:unit_name}, function(data) {
				            }).done(function(data) {
				            	var length = data.length-1;
					        	var enc = data[length].substring(0,16);
					        	$("#unit_sus_no").val(dec(enc,data[0]));
				            }).fail(function(xhr, textStatus, errorThrown) {
				            });
			      } 	     
			}); 			
	});



</script>



<c:url value="Update_Child_DetailsCivUrl" var="Update_Child_DetailsCivUrl" />
<form:form action="${Update_Child_DetailsCivUrl}" method="post" id="update_child_dtlForm"
	name="update_child_dtlForm" target="result">
 <input type="hidden" name="updatechild_civ_id" id="updatechild_civ_id" value="0" />  

</form:form>

<c:url value="Ten_Popup_CIVTiles" var="Ten_Popup_CIVTiles" />
<form:form action="${Ten_Popup_CIVTiles}" method="post" id="ten_updateForm"
	name="ten_updateForm" target="result">
<input type="hidden" name="ten_civ_id" id="ten_civ_id" value="0" /> 
</form:form>
<!-- /////////// Change In Nok //////////////// -->
<c:url value="Nok_Popup_CIVTiles" var="Nok_Popup_CIVTiles" />
<form:form action="${Nok_Popup_CIVTiles}" method="post" id="nok_updateForm"
	name="nok_updateForm" target="result">
<input type="hidden" name="nok_civ_id" id="nok_civ_id" value="0" /> 
</form:form>

<c:url value="Des_Popup_CIVTiles" var="Des_Popup_CIVTiles" />
<form:form action="${Des_Popup_CIVTiles}" method="post" id="des_updateForm"
	name="des_updateForm" target="result">
<input type="hidden" name="des_civ_id" id="des_civ_id" value="0" /> 
</form:form>


<!--  ///////// MaritaL Status//////////////// -->
<c:url value="Change_in_Marital_StatusCivUrl" var="Change_in_Marital_StatusCivUrl" />
<form:form action="${Change_in_Marital_StatusCivUrl}" method="post" id="Change_In_Marital_Status_Form" name="Change_In_Marital_Status_Form" modelAttribute="id" target="result">
<input type="hidden" name="maritalstatus_civ_id" id="maritalstatus_civ_id" value="0" />
</form:form>

<!-- /////////////////// Update Qualification//////////////// -->
<c:url value="Update_Qualification_Popup_CIVTiles" var="Update_Qualification_Popup_CIVTiles" />
<form:form action="${Update_Qualification_Popup_CIVTiles}" method="post" id="Update_Qualification_Form" name="Update_Qualification_Form"  target="result">
<input type="hidden" name="update_qualification_civ_id" id="update_qualification_civ_id" value="0"/>
</form:form>


<!-- /////////// Change_In_Address_Form //////////////// -->
<c:url value="Address_Popup_CivTiles" var="Address_Popup_CivTiles" />
<form:form action="${Address_Popup_CivTiles}" method="post" id="change_in_addressForm"
	name="change_in_addressForm" target="result">
 <input type="hidden" name="address_civ_id" id="address_civ_id" value="0" />  
</form:form>


	
