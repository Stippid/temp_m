<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


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

<div class="animated fadeIn">
	<div class="">
		<div class="container" align="center">
			<div class="card">
				<h3> <div class="card-header">
						<strong>View Search FMCR</strong>
					</div>
				</h3>
				<div class="card-body card-block">

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>Veh BA No</label>
								</div>
								<div class="col-12 col-md-8">
									<input type="hidden" id="id" name="id"
										value="${MCRSearchCMD.id}" class="form-control"> <input
										type="text" id="ba_no" name="ba_no" placeholder=""
										class="form-control" value="${MCRSearchCMD.ba_no}" readonly>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class="form-control-label"><strong
										style="color: red;">* </strong>Country of Origin </label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="country" name="country"
										class="form-control" readonly>
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class="form-control-label"><strong
										style="color: red;">* </strong>MCT </label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="mct1" name="mct1" class="form-control"
										readonly>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>Description </label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="mct_nomen1" name="mct_nomen1"
										placeholder="" class="form-control" value="" readonly>
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class="form-control-label"><strong
										style="color: red;">* </strong>SUS No </label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="sus_no" name="sus_no"
										class="form-control" value="${MCRSearchCMD.sus_no}" readonly>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class="form-control-label"><strong
										style="color: red;">* </strong>Date of Submission </label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="dt_of_submsn" name="dt_of_submsn"
										class="form-control" readonly>
								</div>
							</div>
						</div>
					</div>


					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
									<label class="form-control-label"><strong
										style="color: red;">* </strong>Date of Census Return </label>
								</div>
								<div class="col-12 col-md-6">
									<input type="text" id="date_of_cens_retrn"
										name="date_of_cens_retrn" class="form-control" readonly>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
									<label class="form-control-label"><strong
										style="color: red;">* </strong>Document Download </label>
								</div>
								<div class="col-12 col-md-6">
									<a hreF="#"
										onclick="getDownloadImagemcr('${MCRSearchCMD.sus_no}')"
										class="btn btn-primary btn-sm">Download</a>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="card-header"
					style="border: 1px solid rgba(0, 0, 0, .125);">
					<strong>Vehicle Details</strong>
				</div>
				<div class="card-body card-block">

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
									<label class=" form-control-label">Classification </label>
								</div>
								<div class="col-12 col-md-6">

									<select data-placeholder="Select the value..."
										class="form-control-sm form-control" tabindex="1"
										id="vehcl_classfctn" name="vehcl_classfctn" readonly>
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
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
									<label class=" form-control-label">Total Km Run </label>
								</div>
								<div class="col-12 col-md-6">
									<input type="text" id="vehcl_kms_run" name="vehcl_kms_run"
										placeholder="" class="form-control"
										value="${MCRSearchCMD.vehcl_kms_run}" readonly>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>Km Run during the Period </label>
								</div>
								<div class="col-12 col-md-6">
									<input type="text" id="veh_km_run_period"
										name="veh_km_run_period" placeholder="" class="form-control"
										value="${MCRSearchCMD.veh_km_run_period}" readonly>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
									<label class=" form-control-label"> Previous Tr Km Run
									</label>
								</div>
								<div class="col-12 col-md-6">
									<input type="text" id="track_kms" name="track_kms"
										placeholder="" class="form-control"
										value="${MCRSearchCMD.track_kms}" readonly>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-6">
										<label class=" form-control-label">Issue Type</label>
									</div>
									<div class="col-12 col-md-6">
										<select id="issued_type" name="issued_type" class="form-control" readonly>
											<option value="OP">OP</option>
											<option value="TRG">TRG</option>
											<option value="WWR">WWR</option>
										</select>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-6">
										<label class=" form-control-label">Servicebality State</label>
									</div>
									<div class="col-12 col-md-6">
										SERV : <input type="radio" id="ser_status" name="ser_status" value="0" style="height:20px;width:15%;" disabled="disabled">
										UNSV : <input type="radio" id="ser_status" name="ser_status" value="1" style="height:20px;width:15%;" disabled="disabled">
									</div>
								</div>
							</div>
							<div class="col-md-6" id="divUNSV_Reason" style="display:none;">
								<div class="row form-group">
									<div class="col col-md-6">
										<label class=" form-control-label">UNSV Reason</label>
									</div>
									<div class="col-12 col-md-6">
										<select id="ser_reason" name="ser_reason" class="form-control" readonly>
											<option value="0">--Select--</option>
											<option value="1">In Wksp for repair</option>
											<option value="2">Tyre/Tube/Bty Demand</option>
											<option value="3">MUA Demand</option>
											<option value="4">Accident</option>
											<option value="5">BOH</option>
											<option value="6">Under Discard</option>
											<option value="7">Defect/Follow Up Report</option>
										</select>
									</div>
								</div>
							</div>
						</div>
				</div>

				<div class="card-header"
					style="border: 1px solid rgba(0, 0, 0, .125);">
					<strong>Engine Details</strong>
				</div>
				<div class="card-body card-block">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label">Type </label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="engine_type" name="engine_type"
										placeholder="" class="form-control"
										value="${MCRSearchCMD.engine_type}" readonly>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label">Hrs Run </label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="engine_hrs_run" name="engine_hrs_run"
										placeholder="" class="form-control"
										value="${MCRSearchCMD.engine_hrs_run}" readonly>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label">Km Run </label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="engine_kms_run" name="engine_kms_run"
										placeholder="" class="form-control"
										value="${MCRSearchCMD.engine_kms_run}" readonly>
								</div>
							</div>
						</div>
						<div class="col-md-6"></div>
					</div>
				</div>


				<div class="card-header"
					style="border: 1px solid rgba(0, 0, 0, .125);">
					<strong>Aux Engine Details</strong>
				</div>
				<div class="card-body card-block">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label">Type </label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="aux_type" name="aux_type" placeholder=""
										class="form-control" value="${MCRSearchCMD.aux_type}" readonly>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label">Hrs Run </label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="aux_engn_hrs_run"
										name="aux_engn_hrs_run" placeholder="" class="form-control"
										value="${MCRSearchCMD.aux_engn_hrs_run}" readonly>
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
									<select data-placeholder="Select the value..."
										class="form-control-sm form-control" tabindex="1"
										id="aux_engn_clasfctn" name="aux_engn_clasfctn" readonly>
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


				<div class="card-header"
					style="border: 1px solid rgba(0, 0, 0, .125);">
					<strong>Main Gun Details</strong>
				</div>
				<div class="card-body card-block">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label">Type </label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="main_gun_type" name="main_gun_type"
										placeholder="" class="form-control"
										value="${MCRSearchCMD.main_gun_type}" readonly>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class="form-control-label"><strong
										style="color: red;">* </strong>Section Gun Type </label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="sec_gun_type" name="sec_gun_type"
										class="form-control" value="${MCRSearchCMD.sec_gun_type}"
										readonly>
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label">EFC </label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="main_gun_efc" name="main_gun_efc"
										placeholder="" class="form-control"
										value="${MCRSearchCMD.main_gun_efc}" readonly>
								</div>
							</div>
						</div>
						<div class="col-md-6"></div>
					</div>

				</div>

				<div class="card-header"
					style="border: 1px solid rgba(0, 0, 0, .125);">
					<strong>Main Radio Set Details</strong>
				</div>
				<div class="card-body card-block">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label">Nomenclature </label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="main_radio_set_nomcltr"
										name="main_radio_set_nomcltr" placeholder=""
										class="form-control"
										value="${MCRSearchCMD.main_radio_set_nomcltr}" readonly>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label">Condition </label>
								</div>
								<div class="col-12 col-md-8">
									<select data-placeholder="Select the value..."
										class="form-control-sm form-control" tabindex="1"
										id="main_radio_set_condn" name="main_radio_set_condn" readonly>
										<option selected disabled>--Select the Value--</option>
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
									<input type="text" id="main_radio_set" name="main_radio_set"
										placeholder="" class="form-control"
										value="${MCRSearchCMD.main_radio_set}" readonly>
								</div>
							</div>
						</div>
						<div class="col-md-6"></div>
					</div>
				</div>

				<div class="card-header"
					style="border: 1px solid rgba(0, 0, 0, .125);">
					<strong>MR/OH Details</strong>
				</div>
				<div class="card-header"
					style="border: 1px solid rgba(0, 0, 0, .125);">
					<strong>MR-1</strong>
				</div>
				<div class="card-body card-block">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label">DUE </label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="mr1_due" name="mr1_due" placeholder=""
										class="form-control" value="${MCRSearchCMD.mr1_due}" readonly>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label">DONE </label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="mr1_done" name="mr1_done" placeholder=""
										class="form-control" value="${MCRSearchCMD.mr1_done}" readonly>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="card-header"
					style="border: 1px solid rgba(0, 0, 0, .125);">
					<strong>OH-1</strong>
				</div>
				<div class="card-body card-block">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label">DUE </label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="oh1_due" name="oh1_due" placeholder=""
										class="form-control" value="${MCRSearchCMD.oh1_due}" readonly>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label">DONE </label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="oh1_done" name="oh1_done" placeholder=""
										class="form-control" value="${MCRSearchCMD.oh1_done}" readonly>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label">DETL </label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="oh1_detl" name="oh1_detl" placeholder=""
										class="form-control" value="${MCRSearchCMD.oh1_detl}" readonly>
								</div>
							</div>
						</div>
						<div class="col-md-6"></div>
					</div>
				</div>
				<div class="card-header"
					style="border: 1px solid rgba(0, 0, 0, .125);">
					<strong>MR-2</strong>
				</div>
				<div class="card-body card-block">

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label">DUE </label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="mr2_due" name="mr2_due" placeholder=""
										class="form-control" value="${MCRSearchCMD.mr2_due}" readonly>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label">DONE </label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="mr2_done" name="mr2_done" placeholder=""
										class="form-control" value="${MCRSearchCMD.mr2_done}" readonly>
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
									<input type="text" id="oh2_due" name="oh2_due" placeholder=""
										class="form-control" value="${MCRSearchCMD.oh2_due}" readonly>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label">DONE </label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="oh2_done" name="oh2_done" placeholder=""
										class="form-control" value="${MCRSearchCMD.oh2_done}" readonly>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label">DETL </label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="oh2_detl" name="oh2_detl" placeholder=""
										class="form-control" value="${MCRSearchCMD.oh2_detl}" readonly>
								</div>
							</div>
						</div>
						<div class="col-md-6"></div>
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
									<input type="text" id="mr3_due" name="mr3_due" placeholder=""
										class="form-control" value="${MCRSearchCMD.mr3_due}" readonly>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label">DONE </label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="mr3_done" name="mr3_done" placeholder=""
										class="form-control" value="${MCRSearchCMD.mr3_done}" readonly>
								</div>
							</div>
						</div>
					</div>
				</div>



				<div class="card-header"
					style="border: 1px solid rgba(0, 0, 0, .125);"></div>
				<div class="card-body card-block">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label">Unit Remarks </label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="unit_remarks" name="unit_remarks"
										placeholder="" class="form-control"
										value="${MCRSearchCMD.unit_remarks}" readonly>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="form-control card-footer" align="center">
					<button class="btn btn-primary btn-sm"
						onclick="View('${MCRSearchCMD.sus_no}')">BACK</button>
				</div>
			</div>
		</div>
	</div>
</div>

<c:url value="getDownloadImagemcr" var="imageDownloadUrl" />
<form:form action="${imageDownloadUrl}" method="post"
	id="getDownloadImageForm" name="getDownloadImageForm"
	modelAttribute="sus_no3">
	<input type="hidden" name="sus_no3" id="sus_no3" />
</form:form>

<c:url value="ViewSerachmcr" var="viewSearchMCRUrl" />
<form:form action="${viewSearchMCRUrl}" method="post" id="viewForm"
	name="viewForm" modelAttribute="sus_no2">
	<input type="hidden" name="sus_no2" id="sus_no2" />
	<input type="hidden" name="unit_name2" id="unit_name2" />
</form:form>

<script>
	$(document).ready(function() {
		$("#main_radio_set_condn").val('${MCRSearchCMD.main_radio_set_condn}');
		$("#vehcl_classfctn").val('${MCRSearchCMD.vehcl_classfctn}');
		$("#issued_type").val('${MCRSearchCMD.issued_type}');
		
		$("input[name='ser_status'][value='${MCRSearchCMD.ser_status}']").attr('checked', 'checked');
		
		if('${MCRSearchCMD.ser_status}' == "1"){
			$("div#divUNSV_Reason").show();
			$("select#ser_reason").val('${MCRSearchCMD.ser_reason}');
		}else{
			$("div#divUNSV_Reason").hide();
		}
		
		$("#aux_engn_clasfctn").val('${MCRSearchCMD.aux_engn_clasfctn}');

		$('#vehcl_classfctn').attr('disabled', true);

		$('#main_radio_set_condn').attr('disabled', true);

		$('#aux_engn_clasfctn').attr('disabled', true);

		var date = '${MCRSearchCMD.dt_of_submsn}';
		var from_d = '${MCRSearchCMD.dt_of_submsn}';
		var from_d1 = from_d.substring(0, 4);
		var from_d2 = from_d.substring(7, 5);
		var from_d3 = from_d.substring(10, 8);
		var frm_d = from_d3 + "-" + from_d2 + "-" + from_d1;
		var today = new Date();
		var dd = String(today.getDate()).padStart(2, '0');
		var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
		var yyyy = today.getFullYear();
		var t = dd + "-" + mm + "-" + yyyy;
		var censsu = t;

		$("#dt_of_submsn").val(frm_d.substring(0, 10));
		$("#date_of_cens_retrn").val(censsu);

	});
</script>

<script>
	function View(sus_no) {

		document.getElementById('sus_no2').value = sus_no;
		document.getElementById('viewForm').submit();
	}

	function getDownloadImagemcr(sus_no) {
		document.getElementById("sus_no3").value = sus_no;
		document.getElementById("getDownloadImageForm").submit();
	}
</script>

<script>
	$(document).ready(function() {
		var ba_no = '${MCRSearchCMD.ba_no}';
		$.post("getcountryList?" + key + "=" + value, {
			ba_no : ba_no
		}, function(h) {
			var length = h.length - 1;
			var enc = h[length].substring(0, 16);
			$("#country").val(dec(enc, h[0]));
		});

		$.post("getmctList?" + key + "=" + value, {
			ba_no : ba_no
		}, function(j) {
			var length = j.length - 1;
			var enc = j[length].substring(0, 16);
			$("#mct1").val(dec(enc, j[0]));

			$.post("getStdNomenList?" + key + "=" + value, {
				mct : dec(enc, j[0])
			}, function(j) {

				var length = j.length - 1;
				var enc = j[length].substring(0, 16);
				$("#mct_nomen1").val(dec(enc, j[0]));

			});

		});

	});
</script>



