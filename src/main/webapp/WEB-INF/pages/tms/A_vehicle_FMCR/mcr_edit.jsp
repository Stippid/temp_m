<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>

<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<script type="text/javascript" src="js/printWatermark/common.js"></script>
<script src="js/printWatermark/printAllPages.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/printWatermark/cueWatermark.css">
<script src="js/printWatermark/cueWatermark.js" type="text/javascript"></script>

<form:form name="updateMCR" action="updateMCR" method="post" commandName="MCRCMD">
	<div class="animated fadeIn">
		<div class="row">
			<div class="container" align="center">
				<div class="card">
					<h3>
						<div class="card-header"><strong>FMCR : view</strong></div>
					</h3>
					<div class="card-body card-block">
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong style="color: red;">* </strong>Veh BA No </label>
									</div>
									<div class="col-12 col-md-8">
										<input type="hidden" id="id" name="id" value="${MCRCMD.id}" class="form-control"> <input type="text" id="ba_no" name="ba_no" maxlength="10" class="form-control" value="${MCRCMD.ba_no}" readonly>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Country of Origin </label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="cntry_of_orgn" name="cntry_of_orgn" class="form-control" readonly>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class="form-control-label"><strong style="color: red;">* </strong>MCT </label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="mct1" maxlength="10" name="mct1" class="form-control" readonly>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong style="color: red;">* </strong>Description </label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="des_of_tanks" name="des_of_tanks" maxlength="100" class="form-control" readonly>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class="form-control-label"><strong style="color: red;">* </strong>SUS No </label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="sus_no" name="sus_no" maxlength="8" class="form-control" value="${MCRCMD.sus_no}" readonly>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class="form-control-label"><strong style="color: red;">* </strong>Date of Census Return </label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="date_of_cens_retrn1" name="date_of_cens_retrn1" class="form-control" readonly>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="card-header" style="border: 1px solid rgba(0, 0, 0, .125);"><strong>Vehicle Details</strong></div>
					<div class="card-body card-block">
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">Classification</label>
									</div>
									<div class="col-12 col-md-8"> <lable id="lablclass"></lable>
										<select data-placeholder="Select the value..." class="form-control-sm form-control" tabindex="1" id="vehcl_classfctn" name="vehcl_classfctn">
											<option selected disabled>--Select--</option>
											<option value="1">I</option>
											<option value="2">II</option>
											<option value="3">III</option>
											<option value="4">IV</option>
											<option value="5">V</option>
											<option value="6">VI</option>
											<option value="7">VII</option>
										</select>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">Total Km Run </label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="vehcl_kms_run" name="vehcl_kms_run" class="form-control" value="${MCRCMD.vehcl_kms_run}" onkeyup="return sum1()" readonly="readonly" onkeypress="return isNumber0_9Only(event);">
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong style="color: red;">* </strong>Km Run during the Period </label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="veh_km_run_period" name="veh_km_run_period" maxlength="6" class="form-control"   onkeyup="return uncheck()"autocomplete="off" max="300000"  value="0"  onkeypress="return isNumber0_9Only(event);">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">Previous Tr Km Run</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="track_kms" name="track_kms" class="form-control" value="${MCRCMD.vehcl_kms_run}" readonly="readonly" onkeypress="return isNumber0_9Only(event);">
										
<%-- 										<input type="text" id="track_kms" name="track_kms" class="form-control" value="${MCRCMD.vehcl_kms_run}" readonly="readonly" onkeypress="return isNumber0_9Only(event);" onblur="showRadioButtons()"> --%>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
						<div class="col-md-6"  id="radio_buttons" >
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong style="color: red;">* </strong>Km Run Type</label>
									</div>
									<div class="col-12 col-md-8">
								INCREASE : <input type="radio" id="km_type1" name="km_type"  onchange="return sum(0)"  value="0" style="height:20px;width:15%;" >
										DECREASE : <input type="radio" id="km_type" name="km_type" onchange="return sum(1)"  value="1" style="height:20px;width:15%;">
									
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">Issue Type</label>
									</div>
									<div class="col-12 col-md-8">
										<select id="issued_type" name="issued_type" class="form-control">
											<option value="OP">OP</option>
											<option value="TRG">TRG</option>
											<option value="WWR">WWR</option>
											<!-- KAJAL DATA 1 -->
											<option value="LOAN">ON LOAN</option>
											<option value="SECTOR">SECTOR</option>
										</select>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">Servicebality State</label>
									</div>
									<div class="col-12 col-md-8">
										SERV : <input type="radio" id="ser_status" name="ser_status" onchange="ChangeSer_Status(0)" value="0" style="height:20px;width:15%;" checked="checked">
										UNSV : <input type="radio" id="ser_status" name="ser_status" onchange="ChangeSer_Status(1)" value="1" style="height:20px;width:15%;">
									</div>
								</div>
							</div>
							<div class="col-md-6" id="divUNSV_Reason" style="display:none;">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">UNSV Reason</label>
									</div>
									<div class="col-12 col-md-8">
										
										<select id="ser_reason" name="ser_reason" class="form-control">
											<option value="0">--Select--</option>
											<c:forEach var="item" items="${ml_2}">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>	
											</c:forEach>   
										</select>
									</div>
								</div>
							</div>
						</div>
						
					</div>
					<div class="card-header" style="border: 1px solid rgba(0, 0, 0, .125);"><strong>Engine Details</strong></div>
					<div class="card-body card-block">
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">Type</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="engine_type" name="engine_type" class="form-control" value="${MCRCMD.engine_type}">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">Hrs Run</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="engine_hrs_run" name="engine_hrs_run" maxlength="5" class="form-control" value="${MCRCMD.engine_hrs_run}" onkeypress="return isNumber0_9Only(event);">
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">Km Run</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="engine_kms_run" name="engine_kms_run" class="form-control" maxlength="5" value="${MCRCMD.engine_kms_run}" onkeypress="return isNumber0_9Only(event);">
									</div>
								</div>
							</div>
							<div class="col-md-6"></div>
						</div>
					</div>
					<div class="card-header" style="border: 1px solid rgba(0, 0, 0, .125);"><strong>Aux Engine Details</strong></div>
					<div class="card-body card-block">
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">Type</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="aux_type" name="aux_type" class="form-control" value="${MCRCMD.aux_type}">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">Hrs Run</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="aux_engn_hrs_run" name="aux_engn_hrs_run" class="form-control" value="${MCRCMD.aux_engn_hrs_run}" onkeypress="return isNumber0_9Only(event);">
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">Classification </label>
									</div>
									<div class="col-12 col-md-8">
										<select data-placeholder="Select the value..." class="form-control-sm form-control" tabindex="1" id="aux_engn_clasfctn" name="aux_engn_clasfctn"> 
											<option selected disabled>--Select the Value--</option>
											<option value="1">I</option>
											<option value="2">II</option>
											<option value="3">III</option>
											<option value="4">IV</option>
											<option value="5">V</option>
											<option value="6">VI</option>
											<option value="7">VII</option>
										</select>
									</div>
								</div>
							</div>
							<div class="col-md-6"></div>
						</div>
					</div>
					<div class="card-header" style="border: 1px solid rgba(0, 0, 0, .125);"><strong>Main Gun Details</strong></div>
					<div class="card-body card-block">
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">Type</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="main_gun_type" name="main_gun_type" class="form-control" value="${MCRCMD.main_gun_type}">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">QR</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="main_gun_qr" name="main_gun_qr" class="form-control" value="${MCRCMD.main_gun_qr}">
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">EFC</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="main_gun_efc" name="main_gun_efc" class="form-control" value="${MCRCMD.main_gun_efc}">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class="form-control-label"><strong style="color: red;">* </strong>Section Gun Type</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="sec_gun_type" name="sec_gun_type" class="form-control" value="${MCRCMD.sec_gun_type}">
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="card-header" style="border: 1px solid rgba(0, 0, 0, .125);">
						<strong>Main Radio Set Details</strong>
					</div>
					<div class="card-body card-block">
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">Nomenclature</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="main_radio_set_nomcltr" name="main_radio_set_nomcltr" class="form-control" value="${MCRCMD.main_radio_set_nomcltr}" maxlength="300">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">Condition </label>
									</div>
									<div class="col-12 col-md-8">
										<select data-placeholder="Select the value..." class="form-control-sm form-control" tabindex="1" id="main_radio_set_condn" name="main_radio_set_condn">
											<option selected disabled>--Select--</option>
											<option value="S">Serviceable</option>
											<option value="US">Un Serviceable</option>
										</select>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">UH </label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="main_radio_set" name="main_radio_set" class="form-control" value="${MCRCMD.main_radio_set}">
									</div>
								</div>
							</div>
							<div class="col-md-6"></div>
						</div>
					</div>
					<div class="card-header" style="border: 1px solid rgba(0, 0, 0, .125);"><strong>MR/OH Details</strong></div>
					<div class="card-header" style="border: 1px solid rgba(0, 0, 0, .125);"><strong>MR-1</strong></div>
					<div class="card-body card-block">
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">DUE</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="date" id="mr1_due" name="mr1_due" class="form-control"> 
										<input type="hidden" id="hdmr1_due" name="hdmr1_due" class="form-control" value="${MCRCMD.mr1_due}">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">DONE</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="date" id="mr1_done" name="mr1_done" class="form-control"> <input type="hidden" id="hdmr1_done" name="hdmr1_done" class="form-control" value="${MCRCMD.mr1_done}">
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="card-header" style="border: 1px solid rgba(0, 0, 0, .125);"><strong>OH-1</strong></div>
					<div class="card-body card-block">
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">DUE</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="date" id="oh1_due" name="oh1_due" class="form-control"> <input type="hidden" id="hdoh1_due" name="hdoh1_due" class="form-control" value="${MCRCMD.oh1_due}">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">DONE</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="date" id="oh1_done" name="oh1_done" class="form-control">
										<input type="hidden" id="hdoh1_done" name="hdoh1_done" class="form-control" value="${MCRCMD.oh1_done}">
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">DETL</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="date" id="oh1_detl" name="oh1_detl" class="form-control">
										<input type="hidden" id="hdoh1_detl" name="hdoh1_detl" class="form-control" value="${MCRCMD.main_radio_set}">
									</div>
								</div>
							</div>
							<div class="col-md-6"></div>
						</div>
					</div>
					<div class="card-header" style="border: 1px solid rgba(0, 0, 0, .125);"><strong>MR-2</strong></div>
					<div class="card-body card-block">
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">DUE</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="date" id="mr2_due" name="mr2_due" class="form-control"> <input type="hidden" id="hdmr2_due" name="hdmr2_due" class="form-control" value="${MCRCMD.mr2_due}">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">DONE</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="date" id="mr2_done" name="mr2_done" class="form-control"> <input type="hidden" id="hdmr2_done" name="hdmr2_done" class="form-control" value="${MCRCMD.mr2_done}">
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="card-header"
						style="border: 1px solid rgba(0, 0, 0, .125);">
						<strong>OH-2</strong>
					</div>
					<div class="card-body card-block">
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">DUE </label>
									</div>
									<div class="col-12 col-md-8">
										<input type="date" id="oh2_due" name="oh2_due"
											class="form-control"> <input type="hidden"
											id="hdoh2_due" name="hdoh2_due"
											class="form-control" value="${MCRCMD.oh2_due}">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">DONE</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="date" id="oh2_done" name="oh2_done" class="form-control">
										<input type="hidden" id="hdoh2_done" name="hdoh2_done" class="form-control" value="${MCRCMD.oh2_done}">
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="card-header"
						style="border: 1px solid rgba(0, 0, 0, .125);">
						<strong>MR-3</strong>
					</div>
					<div class="card-body card-block">

						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">DUE </label>
									</div>
									<div class="col-12 col-md-8">
										<input type="date" id="mr3_due" name="mr3_due"
											class="form-control"> <input type="hidden"
											id="hdmr3_due" name="hdmr3_due"
											class="form-control" value="${MCRCMD.mr3_due}">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">DONE</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="date" id="mr3_done" name="mr3_done" class="form-control">
										<input type="hidden" id="hdmr3_done" name="hdmr3_done" class="form-control" value="${MCRCMD.main_radio_set}">
									</div>
								</div>
							</div>
						</div>
					</div>
					
					
					<div class="card-header"
						style="border: 1px solid rgba(0, 0, 0, .125);">
						<strong>OH-3</strong>
					</div>
					<div class="card-body card-block">

						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">DUE </label>
									</div>
									<div class="col-12 col-md-8">
										<input type="date" id="oh3_due" name="oh3_due"
											class="form-control"> <input type="hidden"
											id="hdoh3_due" name="hdoh3_due"
											class="form-control" value="${MCRCMD.oh3_due}">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">DONE</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="date" id="oh3_done" name="oh3_done" class="form-control">
										<input type="hidden" id="hdoh3_done" name="hdoh3_done" class="form-control" value="${MCRCMD.oh3_done}">
									</div>
								</div>
							</div>
						</div>
					</div>


					<div class="card-header"
						style="border: 3px solid rgba(0, 0, 0, .125);"></div>
					<div class="card-body card-block">
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">Unit Remarks </label>
									</div>
									<div class="col-12 col-md-8">
										<textarea id="unit_remarks" name="unit_remarks"
											class="form-control autocomplete" style="font-size: 11px;"
											autocomplete="off" maxlength="255"></textarea>
									</div>
								</div>
							</div>
						</div>
						<!--  <div class="col-md-6"> -->
						<div class="row form-group" style="display: none;">
							<div class="col col-md-4">
								<label class=" form-control-label">MISO Remarks </label>
							</div>
							<div class="col-12 col-md-8">
								<input type="hidden" id="miso_remarks" name="miso_remarks" class="form-control" value="${MCRCMD.unit_remarks}">
							</div>
						</div>
					</div>
					<div class="form-control card-footer" align="center">
						<input type="submit" class="btn btn-primary btn-sm" value="Update" onclick="return validate();">
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>

<c:url value="ViewSerachmcr" var="getMCRReportUrl" />
<form:form action="${getMCRReportUrl}" method="post" id="viewForm" name="viewForm" modelAttribute="sus_no">
	<input type="hidden" name="sus_no2" id="sus_no2" />
	<input type="hidden" name="unit_name1" id="unit_name1" />
</form:form>

<script>
	$(document).ready(function() {
		$("#main_radio_set_condn").val('${MCRCMD.main_radio_set_condn}');
		$("#vehcl_classfctn").val('${MCRCMD.vehcl_classfctn}');
		$("#aux_engn_clasfctn").val('${MCRCMD.aux_engn_clasfctn}');
		$("#mr1_due").val('${MCRCMD.mr1_due}');
		$("#mr1_done").val('${MCRCMD.mr1_done}');
		$("#oh1_due").val('${MCRCMD.oh1_due}');
		$("#oh1_done").val('${MCRCMD.oh1_done}');
		$("#oh1_detl").val('${MCRCMD.oh1_detl}');
		$("#mr2_due").val('${MCRCMD.mr2_due}');
		$("#mr2_done").val('${MCRCMD.mr2_done}');
		$("#oh2_due").val('${MCRCMD.oh2_due}');
		$("#oh2_done").val('${MCRCMD.oh2_done}');
		$("#mr3_due").val('${MCRCMD.mr3_due}');
		$("#mr3_done").val('${MCRCMD.mr3_done}');
		$("#oh3_due").val('${MCRCMD.oh3_due}');
		$("#oh3_done").val('${MCRCMD.oh3_done}');
	});
</script>
<script>
	function ChangeSer_Status(type){
		if(type == "0"){
			$("select#ser_reason").val("0");
			$("div#divUNSV_Reason").hide();
		}else{
			$("div#divUNSV_Reason").show();
		}
	}


	
	function uncheck(){
		 var radios = document.getElementsByName("km_type");
		    for (var i = 0; i < radios.length; i++) {
		        radios[i].checked = false;
		    }
	}

	
	function sum(sum_type) {
	
		var s = document.getElementById("veh_km_run_period").value;
		var s1 = document.getElementById("track_kms").value;
// 		var sum_type=document.getElementById("km_type").value;
		var vehcl_kms_run="";
		if(sum_type=="0")
			{
			 vehcl_kms_run = parseInt(s) + parseInt(s1);
				if(vehcl_kms_run>300000)
				{	
					
				alert("Exceeding ceiling Kms Run");
				var  k = 0; 
				document.getElementById('veh_km_run_period').value = k;
				document.getElementById('vehcl_kms_run').value = s1;
				uncheck();
				$("#veh_km_run_period").focus();
				}
				if(vehcl_kms_run<=300000)
					{
					document.getElementById('vehcl_kms_run').value = vehcl_kms_run;
					}
			}
		if(sum_type=="1")
		{
	vehcl_kms_run = parseInt(s1) - parseInt(s);
	if(vehcl_kms_run<0)
	{
		 vehcl_kms_run = 0; 
		document.getElementById('vehcl_kms_run').value =vehcl_kms_run;
		//document.getElementById('track_kms').value = "0";
	}
	if(vehcl_kms_run>300000)
	{
	alert("Exceeding ceiling Kms Run");
	var  k = 0; 
	document.getElementById('veh_km_run_period').value = k;
	document.getElementById('vehcl_kms_run').value = s1;
	uncheck();
	$("#veh_km_run_period").focus();
	}
	if(vehcl_kms_run<=300000)
		{
		document.getElementById('vehcl_kms_run').value = vehcl_kms_run;
		}
		}
	}
			
	function View(sus_no) {
		
		document.getElementById('sus_no2').value = sus_no;
		document.getElementById('viewForm').submit();
	}
</script>
<script>
	$(document).ready(function() {
		var today = new Date();
		var dd = String(today.getDate()).padStart(2, '0');
		var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
		var yyyy = today.getFullYear();
		var t = dd + "-" + mm + "-" + yyyy;
		$('#date_of_cens_retrn1').val(t);
	});
	function isNumber0_9Only(evt) {
		var charCode = (evt.which) ? evt.which : evt.keyCode;
		if (!(charCode >= 48 && charCode <= 57) && charCode != 8) {
			return false;
		}
		return true;
	}
</script>
<script>
$(document).ready(function() {
	var ba_no = '${MCRCMD.ba_no}';
	$("#unit_remarks").text('${MCRCMD.unit_remarks}');
   	$.post("getcountryList?"+key+"="+value, {ba_no:ba_no}, function(h) {
	}).done(function(h) {
		var length = h.length-1;
        var enc = h[length].substring(0,16);
        $("#cntry_of_orgn").val(dec(enc,h[0]));
	}).fail(function(xhr, textStatus, errorThrown) {
	});

   	$.post("getmctList?"+key+"="+value, {ba_no:ba_no}, function(j) { }).done(function(j) {
		var length = j.length-1;
    	var enc = j[length].substring(0,16);
    	$("#mct1").val(dec(enc,j[0]));
    	$.post("getStdNomenList?"+key+"="+value, {mct : dec(enc,j[0])}, function(j) { }).done(function(j) {
    		var length = j.length-1;
	        var enc = j[length].substring(0,16);
	        $("#des_of_tanks").val(dec(enc,j[0]));
	   }).fail(function(xhr, textStatus, errorThrown) {});
	}).fail(function(xhr, textStatus, errorThrown) { });
});
function validate()
{
	var prev_km = $("#track_kms").val();
	var total_km = $("#vehcl_kms_run").val();
	
	if ($('input[id="km_type"]:checked').length === 0 && $('input[id="km_type1"]:checked').length === 0 && $("#veh_km_run_period").val() != "0") {
	    alert("Please Select Km Run Type.");
	    return false;
	}

			
			
	if($("#veh_km_run_period").val() == "")
	{
		alert("Please Enter Km Run during The Peroid.");
		$("#veh_km_run_period").focus();
		return false ;
	}
	
	if($("#veh_km_run_period").val() == "")
	{
		alert("Please Enter Km Run during The Peroid.");
		$("#veh_km_run_period").focus();
		return false ;
	}
	if(parseInt(total_km)>300000)
	{
		alert("Exceeding ceiling Kms Run.");
		$("#veh_km_run_period").focus();
		uncheck();
		return false ;
	}
	
	
// 	if(parseInt(prev_km) > parseInt(total_km))
// 	{
// 		alert("Previous Tr Km Run Should be less than Total Km Run.")	
// 		return false;
// 	}	
	return true;	
}
</script>