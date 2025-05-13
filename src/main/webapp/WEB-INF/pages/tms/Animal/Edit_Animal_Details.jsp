	
	
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
	
	
	<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
	<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
	<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
	
	<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
	<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
	
	<link rel="stylesheet" href="js/cue/cueWatermark.css">
	
	<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
	<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
	<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
	
	<script type="text/javascript" src="js/printWatermark/common.js"></script>
	<link rel="stylesheet" href="js/printWatermark/cueWatermark.css">
	<script src="js/printWatermark/cueWatermark.js" type="text/javascript"></script>
	
	
	
	
	<script>
				$(document).ready(function() {
					$('#animal_purchase_cost').on('keyup', function() {
					    jQuery(this).val(jQuery(this).val().split(',').join(''));
					    jQuery(this).val((jQuery(this).val()).replace(/\B(?=(?:\d{3})+(?!\d))/g, ","));
					});
					$('#animal_present_cost').on('keyup', function() {
					    jQuery(this).val(jQuery(this).val().split(',').join(''));
					    jQuery(this).val((jQuery(this).val()).replace(/\B(?=(?:\d{3})+(?!\d))/g, ","));
					});		
				});
	</script>
	
	
	<form:form id="AnimalEditAction" name="AnimalEditAction" action="AnimalEditActionUpload?${_csrf.parameterName}=${_csrf.token}" commandName="AnimalEditCMDUpload" method="post" enctype="multipart/form-data">
		<div class="animated fadeIn">
				<div class="container" align="center">
					<div class="card">
						<div class="card-header">
							<strong><h3>ANIMAL DETAILS : EDIT</h3></strong>
						</div>
						<div class="card" id="army_animl" style="display: none; margin-bottom: 0; border: 0;">
							<div class="card-header">
								<strong>GENERAL</strong>
							</div>
	
							<div class="card-body card-block">
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col col-md-4">
												<input type="hidden" id="id" name="id" value="${AnimalEditCMDUpload.id}" class="form-control">
												<input type="hidden" id="abc" name="abc" value="${AnimalEditCMDUpload.anml_type}" class="form-control">
												<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> SUS No</label>
											</div>
	
											<div class="col-12 col-md-8">
												<input type="text" id="sus_no" name="sus_no" class="form-control" value="${AnimalEditCMDUpload.sus_no}" autocomplete="off" maxlength="8">
											</div>
										</div>
									</div>
									
									
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col col-md-4">
												<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Unit Name </label>
											</div>
											<div class="col-12 col-md-8">
												<input type="text" id="unit_name" name="" class="form-control" value="${unit_name}" autocomplete="off" maxlength="100">
												<input type="hidden" id="anml_type" name="anml_type" value="${AnimalEditCMDUpload.anml_type}">
											</div>
										</div>
									</div>
								</div>
	
	
								<div id="Army_equin" style="display: none;">
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col col-md-4">
													<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Remount No </label>
												</div>
	
												<div class="col-12 col-md-8">
													<input type="text" id="remount_no" name="remount_no" value="${AnimalEditCMDUpload.remount_no}" class="form-control" autocomplete="off" readonly="readonly" maxlength="8">
												</div>
											</div>
										</div>
	
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col col-md-4">
													<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Type</label>
												</div>
												<div class="col-12 col-md-8" style="padding-bottom: 40px">
													<select id="type_equines" name="type_equines" class="form-control">
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${getAnimalTypeList}" varStatus="num">
															<option value="${item[1]}">${item[0]}</option>
														</c:forEach>
													</select>
												</div>
											</div>
										</div>
									</div>
								</div>
	
								<div id="Army_dog" style="display: none;">
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col col-md-4">
													<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Army Number </label>
												</div>
												<div class="col-12 col-md-8">
													<input type="text" id="army_num" name="army_num" class="form-control" value="${AnimalEditCMDUpload.army_num}" readonly="readonly" autocomplete="off" maxlength="20">
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col col-md-4">
													<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Type of Dog </label>
												</div>
												<div class="col-12 col-md-8">
													<select id="type_dog" name="type_dog" class="form-control">
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${getTypeOfDogList}" varStatus="num">
															<option value="${item[1]}">${item[0]}</option>
														</c:forEach>
													</select>
												</div>
											</div>
										</div>
									</div>
	
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col col-md-4">
													<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Name of Dog </label>
												</div>
												<div class="col-12 col-md-8">
													<input type="text" id="name_of_dog" name="name_of_dog" class="form-control" value="${AnimalEditCMDUpload.name_of_dog}" autocomplete="off" maxlength="35">
												</div>
											</div>
										</div>
	
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col col-md-4">
													<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Specialization</label>
												</div>
												<div class="col-12 col-md-8">
													<select id="specialization" name="specialization" class="form-control">
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${getsplzList}" varStatus="num">
															<option value="${item[1]}">${item[0]}</option>
														</c:forEach>
													</select>
												</div>
											</div>
										</div>
									</div>
								</div>
	
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col col-md-4">
												<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Breed </label>
											</div>
											<div class="col-12 col-md-8">
												<select id="breed" name="breed" class="form-control">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getbreedList}" varStatus="num">
														<option value="${item[1]}">${item[0]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
	
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col col-md-4">
												<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Color</label>
											</div>
											<div class="col-12 col-md-8">
												<select id="colour" name="colour" class="form-control">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getclrList}" varStatus="num">
														<option value="${item[1]}">${item[0]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
								</div>
	
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col col-md-4">
												<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Sex </label>
											</div>
	
											<div class="col-12 col-md-8" id="sex_dog" style="display: none;">
												<div class="form-check-inline form-check">
													<label for="inline-radio1" class="form-check-label ">
													<input type="hidden" id="sex_radio" name="sex_radio" value="${AnimalEditCMDUpload.sex}" class="form-control" readonly="readonly" disabled="disabled"> 
													<input type="radio" id="sex1" name="sex" value="MALE" class="form-check-input" value="${AnimalEditCMDUpload.sex}">Male</label>&nbsp;&nbsp;&nbsp;
														
													 <label for="inline-radio2" class="form-check-label "> 
													 <input type="radio" id="sex2" name="sex" value="FEMALE" class="form-check-input" value="${AnimalEditCMDUpload.sex}">Female</label>&nbsp;&nbsp;&nbsp;
														
														
													 <label for="inline-radio2" class="form-check-label ">
													 <input type="radio" id="sex3" name="sex" value="OTHERS" class="form-check-input" value="${AnimalEditCMDUpload.sex}">Others</label>&nbsp;&nbsp;&nbsp;
												</div>
											</div>
	
											<div class="col-12 col-md-8" id="sex_equ" style="display: none;">
												<div class="form-check-inline form-check">
													<label for="inline-radio1" class="form-check-label ">
														<input type="hidden" id="sex_radio" name="sex_radio" value="${AnimalEditCMDUpload.sex}" class="form-control" readonly="readonly" disabled="disabled">
														
														 <input type="radio" id="sex4" name="sex" value="ENTIRE" class="form-check-input" value="${AnimalEditCMDUpload.sex}">Entire</label>&nbsp;&nbsp;&nbsp; 
														
														
													<label for="inline-radio2" class="form-check-label ">
														 <input type="radio" id="sex5" name="sex" value="MARE" class="form-check-input" value="${AnimalEditCMDUpload.sex}">Mare</label>&nbsp;&nbsp;&nbsp;
														
														
													 <label for="inline-radio2" class="form-check-label ">
														 <input type="radio" id="sex6" name="sex" value="GELD" class="form-check-input" value="${AnimalEditCMDUpload.sex}">Geld</label>&nbsp;&nbsp;&nbsp;
														
														 
													 <label for="inline-radio2" class="form-check-label ">
														 <input type="radio" id="sex7" name="sex" value="RIG" class="form-check-input" value="${AnimalEditCMDUpload.sex}">Rig</label>&nbsp;&nbsp;&nbsp; 
													
													<label for="inline-radio2" class="form-check-label ">
														 <input type="radio" id="sex8" name="sex" value="OTHERS" class="form-check-input" value="${AnimalEditCMDUpload.sex}">Others</label>&nbsp;&nbsp;&nbsp;
												
												</div>
											</div>
										</div>
									</div>
	
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col col-md-4">
												<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Source</label>
											</div>
	
	
											<div class="col-12 col-md-8">
												<select id="source" name="source" class="form-control">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getSourceList}" varStatus="num">
														<option value="${item[1]}">${item[0]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col col-md-4">
												<label for="text-input" class=" form-control-label">Photograph</label>
											</div>
											<div class="col-12 col-md-8">
												<input type="file" id="image_anml" name="image_anml" class="form-control" autocomplete="off">
											</div>
										</div>
									</div>
	
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col col-md-4">
												<label for="text-input" class=" form-control-label">Download Upload Document</label>
											</div>
											<div class="col-12 col-md-8">
												<a hreF="#" onclick="getDownloadImageanml1('${AnimalEditCMDUpload.id}')" class="btn btn-primary btn-sm" >Download</a>
											</div>
										</div>
									</div>
								</div>
	
								<div class="col-md-12">
							
	
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col col-md-4">
												<label for="text-input" class=" form-control-label">Fitness Status </label>
											</div>
											<div class="col-12 col-md-8">
												<select id="fitness_status" name="fitness_status" onclick="chkFitnessStatus(this);" class="form-control">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getFitnessStatusList}" varStatus="num">
														<option value="${item[1]}">${item[0]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
							
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col col-md-4">
												<label for="text-input" class=" form-control-label">Fitness Deployment Details</label>
											</div>
											<div class="col-12 col-md-8">
													<input type="text" id="fitness_deployment" name="fitness_deployment" value="${AnimalEditCMDUpload.fitness_deployment}" class="form-control" autocomplete ="off" maxlength="255">
											</div>
										</div>
									</div>
								</div>
	
								<div class="col-md-12" id="date_admitted1" style="display: none;">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col col-md-4">
												<label for="text-input" class=" form-control-label">Date Admitted</label>
											</div>
											<div class="col-12 col-md-8">
												<input type="date" id="date_admitted" name="date_admitted" value="${AnimalEditCMDUpload.date_admitted}" class="form-control" min='1899-01-01' max='${date}' maxlength="10">
											</div>
										</div>
									</div>
								</div>
	
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col col-md-4">
												<label for="text-input" class=" form-control-label">Details of Sire </label>
											</div>
											<div class="col-12 col-md-8">
												<input type="text" id="details_of_sire" name="details_of_sire" class="form-control" value="${AnimalEditCMDUpload.details_of_sire}" autocomplete="off" maxlength="35">
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col col-md-4">
												<label for="text-input" class=" form-control-label">Details of Dam </label>
											</div>
											<div class="col-12 col-md-8">
												<input type="text" id="details_of_dam" name="details_of_dam" class="form-control" value="${AnimalEditCMDUpload.details_of_dam}" autocomplete="off" maxlength="35">
											</div>
										</div>
									</div>
								</div>
	
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col col-md-4">
												<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Date of Birth </label>
											</div>
											<div class="col-12 col-md-8">
												<input type="date" id="date_of_birth" name="date_of_birth" class="form-control" value="${AnimalEditCMDUpload.date_of_birth}" onchange="return calculate_age();" min='1899-01-01' max='${date}' placeholder="dd-mm-yyyy" autocomplete="off" maxlength="10">
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col col-md-4">
												<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Age (YY) </label>
											</div>
											<div class="col-12 col-md-8">
												<input type="text" id="age" name="age" class="form-control" value="${AnimalEditCMDUpload.age}" autocomplete="off" readonly="readonly" maxlength="2">
											</div>
										</div>
									</div>
								</div>
	
	
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col col-md-4">
												<label for="text-input" class=" form-control-label">Animal Purchase cost (&#8377) </label>
											</div>
											<div class="col-12 col-md-8">
												<input type="text" id="animal_purchase_cost" name="animal_purchase_cost" value="${AnimalEditCMDUpload.animal_purchase_cost}" class="form-control" autocomplete="off" onkeypress="return validateNumber(event, this)" maxlength="10">
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col col-md-4">
												<label for="text-input" class=" form-control-label">Book Value(&#8377) </label>
											</div>
											<div class="col-12 col-md-8"> 
												<input type="text" id="animal_present_cost" name="animal_present_cost" value="${AnimalEditCMDUpload.animal_present_cost}" class="form-control" autocomplete="off" onkeypress="return validateNumber(event, this)" maxlength="10">
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12">
								
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col col-md-4">
												<label for="text-input" class=" form-control-label">Microchip No </label>
											</div>
											
											<div class="col-12 col-md-8">
												<input type="text" id="microchip_no" name="microchip_no" class="form-control" value="${AnimalEditCMDUpload.microchip_no}" autocomplete="off" maxlength="20">
											</div>
										</div>
									</div>
								
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col col-md-4">
												<label for="text-input" class=" form-control-label"> Date of Death</label>
											</div>
											<div class="col-12 col-md-8">
												<input type="date" id="date_of_death" name="date_of_death" value="${AnimalEditCMDUpload.date_of_death}" class="form-control" min='1899-01-01' max='${date}' maxlength="10">
											</div>
										</div>
									</div>
								</div>
	
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col col-md-4">
												<label for="text-input" class=" form-control-label">Disp/Trans</label>
											</div>
											<div class="col-12 col-md-8">
	
												<div class="form-check-inline form-check">
													<label for="inline-radio1" class="form-check-label ">
														<input type="hidden" id="disptrans_radio" name="disptrans_radio" value="${AnimalEditCMDUpload.disptrans}" class="form-control" readonly="readonly" disabled="disabled">
														<input type="radio" id="disptrans1" name="disptrans" value="disp" class="form-check-input" value="${AnimalEditCMDUpload.disptrans}" onclick="chkDisposal();">Disposal</label>&nbsp;&nbsp;&nbsp; 
													
													<label for="inline-radio2" class="form-check-label ">
														 <input type="radio" id="disptrans2" name="disptrans" value="issue" class="form-check-input" onclick="chkDisposal1();" value="${AnimalEditCMDUpload.disptrans}">Issue To Unit</label>&nbsp;&nbsp;&nbsp;
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-6" id="disposal1" style="display: none;">
										<div class="row form-group">
											<div class="col col-md-4">
												<label for="text-input" class=" form-control-label">Disposal</label>
											</div>
											<div class="col-12 col-md-8">
												<select id="disposal" name="disposal" class="form-control">
													<option value="0">--Select--</option>
													<option value="DEAD">DEAD</option>
													<option value="CAST AND BOARDED OUT">CAST AND BOARDED OUT</option>
													<option value="CAST AND DESTROYED">CAST AND DESTROYED</option>
													<option value="LOST">LOST</option>
													<option value="SALE">SALE</option>
													<option value="GIFT">GIFT</option>
													<option value="OTHERS">OTHERS</option>
												</select>
											</div>
										</div>
									</div>
	
									<div class="col-md-6" id="isuue_to_unit1" style="display: none;">
										<div class="row form-group">
											<div class="col col-md-4">
												<label for="text-input" class=" form-control-label">Issue To Unit </label>
											</div>
											<div class="col-12 col-md-8">
												<input type="text" id="issue_to_unit_name" name="issue_to_unit_name" value="${AnimalEditCMDUpload.issue_to_unit_name}" class="form-control" autocomplete="off" maxlength="100">
											</div>
										</div>
									</div>
								</div>
	
	
								<div class="col-md-12" id="disposal_date" style="display: none;">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col col-md-4">
												<label for="text-input" class=" form-control-label">Disposal Date</label>
											</div>
											<div class="col-12 col-md-8">
												<input type="date" id="dis_date" name="dis_date" value="${AnimalEditCMDUpload.dis_date}" class="form-control" min='1899-01-01' max='${date}' maxlength="10">
											</div>
										</div>
									</div>
								</div>
	
	
								<div class="col-md-12" id="issue_date" style="display: none;">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col col-md-4">
												<label for="text-input" class=" form-control-label">Issue Date</label>
											</div>
											<div class="col-12 col-md-8">
												<input type="date" id="issue_date" name="issue_date" value="${AnimalEditCMDUpload.issue_date}" class="form-control" min='1899-01-01' max='${date}' maxlength="10">
											</div>
										</div>
									</div>
								</div>
	
								<div class="col-md-12" id="dispremark" style="display: none;">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col col-md-4">
												<label for="text-input" class=" form-control-label">Disposal Remarks </label>
											</div>
											<div class="col-12 col-md-8">
												<textarea id="disposal_remarks" name="disposal_remarks" class="form-control" maxlength="255">${AnimalEditCMDUpload.disposal_remarks}</textarea>
											</div>
										</div>
									</div>
								</div>
							</div>
							<!-- end of general -->
	
	
							<div class="card-header">
								<strong>MEDALS/DECORATION</strong>
							</div>
							<div class="card-body card-block">
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col col-md-4">
												<label for="text-input" class=" form-control-label">Name of Award </label>
											</div>
											<div class="col-12 col-md-8">
												<select id="medals_desc_details" name="medals_desc_details" class="form-control">
												   	<option value="">--Select--</option>
													<option value="MENTION IN DESPATCHES">Mention-in-despatches</option>
													<option value="COAS COMMENDATION CARD">COAS Commendation Card</option>
													<option value="VCOAS COMMENDATION CARD">VCOAS Commendation Card</option>
													<option value="GOC IN C COMMENDATION CARD">GOC-in-C-Commendation Card</option>
													<option value="OTHERS">OTHERS</option>
												</select>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col col-md-4">
												<label for="text-input" class=" form-control-label">Unit Where Awarded </label>
											</div>
											<div class="col-12 col-md-8">
												<input type="text" id="unit_where_awarded" name="unit_where_awarded" value="${AnimalEditCMDUpload.unit_where_awarded}" class="form-control" autocomplete="off" maxlength="100">
											</div>
										</div>
									</div>
								</div>
	
	
	
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col col-md-4">
												<label for="text-input" class=" form-control-label">Authority
												</label>
											</div>
											<div class="col-12 col-md-8">
												<input type="text" id="authority" name="authority" value="${AnimalEditCMDUpload.authority}" class="form-control" autocomplete="off" maxlength="50">
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col col-md-4">
												<label for="text-input" class=" form-control-label">Award Date </label>
											</div>
											<div class="col-12 col-md-8">
												<input type="date" id="award_date" name="award_date" value="${AnimalEditCMDUpload.award_date}" class="form-control" autocomplete="off" placeholder="dd-mm-yyyy" min='1899-01-01' max='${date}' maxlength="10">
											</div>
										</div>
									</div>
	
								</div>
	
								<div class="col-md-12" id="remarks" style="display: none;">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col col-md-4">
												<label for="text-input" class=" form-control-label">Awarded Remarks </label>
											</div>
											<div class="col-12 col-md-8">
												<textarea id="awared_remarks" name="awared_remarks" class="form-control" maxlength="255">${AnimalEditCMDUpload.awared_remarks}</textarea>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- end of textbox -->
	
	
	
						<!-- starting of textbox1 -->
	
						<!-- <div class="card" id="Army_equin" style="display: none; margin-bottom: 0; border: 0;"> -->
	
						<!-- end of textboox1 -->
	
						<div class="card" id="otherdetails" style="display: none; margin-bottom: 0; border: 0;">
							<div class="card-header">
								<strong>OTHERS DETAILS</strong>
							</div>
							<div class="card-body card-block">
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col col-md-4">
												<label for="text-input" class=" form-control-label">TOS</label>
											</div>
											<div class="col-12 col-md-8">
												<input type="date" id="tos" name="tos" class="form-control" value="${AnimalEditCMDUpload.tos}" placeholder="dd-mm-yyyy" autocomplete="off" min='1899-01-01' max='${date}' maxlength="10">
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col col-md-4">
												<label for="text-input" class=" form-control-label">TORS</label>
											</div>
											<div class="col-12 col-md-8">
												<input type="date" id="tors" name="tors" class="form-control" value="${AnimalEditCMDUpload.tors}" autocomplete="off" placeholder="dd-mm-yyyy" min='1899-01-01' max='${date}' maxlength="10">
											</div>
										</div>
									</div>
								</div>
	
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col col-md-4">
												<label for="text-input" class=" form-control-label">SOS</label>
											</div>
											<div class="col-12 col-md-8">
												<input type="date" id="sos" name="sos" class="form-control" value="${AnimalEditCMDUpload.sos}" autocomplete="off" placeholder="dd-mm-yyyy" min='1899-01-01' max='${date}' maxlength="10">
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col col-md-4">
												<label for="text-input" class=" form-control-label">SORS</label>
											</div>
											<div class="col-12 col-md-8">
												<input type="date" id="sors" name="sors" class="form-control" value="${AnimalEditCMDUpload.sors}" autocomplete="off" placeholder="dd-mm-yyyy" min='1899-01-01' max='${date}' maxlength="10">
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- end of other details -->
	
	
						<div class="form-control card-footer" align="center">
							<input type="submit" class="btn btn-primary btn-sm" value="Update" onclick="return Validate();">
						</div>
						<!-- end of card-footer -->
	
					</div>
					<!-- end of card -->
				</div>
				<!-- end of container -->
		</div>
	</form:form>
	
		<c:url value="getDownloadImageanml" var="imageDownloadUrl1" />
		<form:form action="${imageDownloadUrl1}" method="post" id="getDownloadanmlImageForm" name="getDownloadanmlImageForm" modelAttribute="updateid">
			<input type="hidden" name="updateid" id="updateid" value="0" />
		</form:form>
	
	
	<script>
					$(document).ready(function() {
						var image_anml = document.links.item("image_anml").href;
						$("#image_anml").change(function(){	
							checkFileExtImage(this);
						});
						var animal = '${AnimalEditCMDUpload.anml_type}';
						$("#medals_desc_details").val('${AnimalEditCMDUpload.medals_desc_details}'); 
						$("#disposal").val('${AnimalEditCMDUpload.disposal}'); 
					
						if (animal == "Army Dog") {
							var anml_type = animal ;
							$("div#army_animl").show();
							$("div#Army_dog").show();
							$("div#splz").show();
							$("div#deplmnt").show();
							$("div#otherdetails").show();
							$("div#Army_equin").hide();
							$("div#sex_dog").show();
							$("div#sex_equ").hide();
							
							var spz = '${AnimalEditCMDUpload.specialization}'
							var bre = '${AnimalEditCMDUpload.breed}'
							var col = '${AnimalEditCMDUpload.colour}'
							var sor = '${AnimalEditCMDUpload.source}'
							var fit = '${AnimalEditCMDUpload.fitness_status}'
							var tyequ = '${AnimalEditCMDUpload.type_equines}'
							var dog = '${AnimalEditCMDUpload.type_dog}'
							
							$("select#type_dog").val(dog);
					        $("select#specialization").val(spz);
					        $("select#breed").val(bre);
					        $("select#colour").val(col);
					        $("select#source").val(sor);
					        $("select#fitness_status").val(fit);
						} else {
							var anml_type = animal ;
							$("div#army_animl").show();
							$("div#Army_dog").hide();
							$("div#equin").show();
							$("div#splz").show();
							$("div#deplmnt").hide();
							$("div#otherdetails").show();
							$("div#Army_equin").show();
							$("div#sex_equ").show();
							$("div#sex_dog").hide();
							
							var beq = '${AnimalEditCMDUpload.breed}'
							var ceq = '${AnimalEditCMDUpload.colour}'
							var seq = '${AnimalEditCMDUpload.source}'
							var fieq = '${AnimalEditCMDUpload.fitness_status}'
							var tyequ = '${AnimalEditCMDUpload.type_equines}'
							
						   $("select#type_equines").val(tyequ);
						   $("select#breed").val(beq);
						   $("select#colour").val(ceq);
					       $("select#source").val(seq);
					       $("select#fitness_status").val(fieq);
						}
				
						var sex_hi = $("#sex_radio").val();
						if (sex_hi == "MALE") {
							document.getElementById("sex1").checked = true;
						}
						if (sex_hi == "FEMALE") {
							document.getElementById("sex2").checked = true;
						}
						if (sex_hi == "OTHERS") {
							document.getElementById("sex3").checked = true;
						}
						if (sex_hi == "ENTIRE") {
							document.getElementById("sex4").checked = true;
						}
						if (sex_hi == "MARE") {
							document.getElementById("sex5").checked = true;
						}
						if (sex_hi == "GELD") {
							document.getElementById("sex6").checked = true;
						}
						if (sex_hi == "RIG") {
							document.getElementById("sex7").checked = true;
						}
						if (sex_hi == "OTHERS") {
							document.getElementById("sex8").checked = true;
						}
						
						var disptrans_hi = $("#disptrans_radio").val();
						if (disptrans_hi == "disp") {
							document.getElementById("disptrans1").checked = true;
							$('#isuue_to_unit1').hide();
							$('#disposal1').show();
							$('#dispremark').hide();
							$('#disposal_date').show();
							$('#issue_date').hide();
						}
						if (disptrans_hi == "issue") {
							document.getElementById("disptrans2").checked = true;
							$('#isuue_to_unit1').show();
							$('#disposal1').hide();
							$('#dispremark').hide();
							$('#disposal_date').hide();
							$('#issue_date').show();
						}
						
						$("#medals_desc_details").change(function() {
							var end = this.value;
							if (end == 'OTHERS') {
								$("#remarks").show();
							} else {
								$("#remarks").hide();
							}
						});
						
						var n = '${AnimalEditCMDUpload.medals_desc_details}'
								if (n == 'OTHERS') {
									$("#remarks").show();
								} else {
									$("#remarks").hide();
								}
								
								var d =	'${AnimalEditCMDUpload.disposal}'
								if (d == 'OTHERS') {
									$("#dispremark").show();
								} else {
									$("#dispremark").hide();
								}
								
								var f =	'${AnimalEditCMDUpload.fitness_status}'
									if(f=='5'){
											$('#date_admitted1').show();
											}
											else if(f=='3'){
												$('#date_admitted1').show();
											}
											else if(f=='2'){
												$('#date_admitted1').hide();
											}
											else if(f=='4'){
												$('#date_admitted1').hide();
											}
					});
	</script>
	
	<script>
			function getDownloadImageanml1(id) {
		        document.getElementById("updateid").value = id;
				document.getElementById("getDownloadanmlImageForm").submit(); 
			}
			
			$("#disposal").change(function() {
				var end = this.value;
				if (end == 'OTHERS') {
					$("#dispremark").show();
				} else {
					$("#dispremark").hide();
				}
			});
			
			function chkDisposal(){
					$('#isuue_to_unit1').hide();
					$('#disposal1').show();
					$('#dispremark').hide();
					$('#disposal_date').show();
					$('#issue_date').hide();
			}
				function chkDisposal1(){
					$('#isuue_to_unit1').show();
					$('#disposal1').hide();
					$('#dispremark').hide();
					$('#disposal_date').hide();
					$('#issue_date').show();
				}
			function chkFitnessStatus(obj){
				if(obj.value=='5'){
					$('#date_admitted1').show();
				}
				else if(obj.value=='3'){
					$('#date_admitted1').show();
				}
				else if(obj.value=='2'){
					$('#date_admitted1').hide();
				}
				else if(obj.value=='4'){
					$('#date_admitted1').hide();
				}
			}
			
			    function validateNumber(evt) {
					var charCode = (evt.which) ? evt.which : evt.keyCode;
					if (charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57)) {
						return false;
					} else {
						if (charCode == 46) {
							return false;
						}
					}
					return true;
				}  
			   
	</script>
	<script>
		 	$(function() {
		        $('#animal_purchase_cost').on('click', function () {
		 	            var x = $('#animal_purchase_cost').val();
		 	            $('#animal_purchase_cost').val(addCommas(x));
		 	            function addCommas(x) {
		 	                var parts = x.toString().split(".");
		 	                parts[0] = parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",");
		 	                return parts.join(".");
		 	            }
		       });
		 	
		  
			}); 
	</script>
	<script>
			$(document).ready(function() {
				var sex_hi = $("#sex_radio").val();
				if (sex_hi == "Male") {
					document.getElementById("sex1").checked = true;
				}
				if (sex_hi == "Female") {
					document.getElementById("sex2").checked = true;
				}
				if (sex_hi == "OTHERS") {
					document.getElementById("sex3").checked = true;
				}
			});
		
			function calculate_age(date_of_birth) {
				var today = new Date();
				var date = today.getDate();
				var mn = today.getMonth() + 1;
				var yr = today.getFullYear();
				var s = document.getElementById("date_of_birth").value;
				var y1 = s.substring(0, 4);
				var m1 = s.substring(6, 7);
				var d1 = s.substring(9, 10);
				var c1 = yr - y1;
				var c3 = date - d1;
				var age = c1
				document.getElementById('age').value = age;
				var Date2 = new Date();
				var Dateofbirth = document.getElementById("date_of_birth").value;
				if ((Date.parse(Dateofbirth) > Date.parse(Date2))) {
					alert('You Can Not Enter The Future Date !');
					document.getElementById("date_of_birth").value = "";
				}
			}
			
		  	function checkFileExtImage(file) {
			  	var ext = file.value.match(/\.([^\.]+)$/)[1];
				switch (ext) {
				  	case 'jpg':
				  	case 'jpeg':
				  	case 'png':
				  	case 'JPG':
				  	case 'JPEG':
				  	case 'PNG':
				 	alert('Allowed');
				    break;
				  	default:
				    	alert('Only *.jpg, *.jpeg and *.png file extensions allowed');
				   	file.value = "";
				}
			}  
	</script>
	
	
	<script>
			$(function() {
		        
		        // Unit SUS No
		        $("#sus_no").keyup(function(){
		        	var sus_no = this.value;
		        	var susNoAuto=$("#sus_no");

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
		        			 $.post("getActiveUnitNameFromSusNo?"+key+"="+value, {
		        				 sus_no:sus_no
		                 }, function(j) {
		                        
		                 }).done(function(j) {
		                	 var length = j.length-1;
	                         var enc = j[length].substring(0,16);
	                         $("#unit_name").val(dec(enc,j[0]));   
		                         
		                 }).fail(function(xhr, textStatus, errorThrown) {
		                 });
		        		} 	     
		        	});	
		        });
		   
		
		// Unit Unit Name
    	$("#unit_name").keyup(function(){
    		var unit_name = this.value;
    	var unit_nameAuto=$("#unit_name");

    	unit_nameAuto.autocomplete({
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
                            var dataCountry1 = susval.join("|");
                            var myResponse = []; 
    	                    var autoTextVal=unit_nameAuto.val();
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
    	        	  alert("Please Enter Approved Unit Name.");
    	        	  document.getElementById("sus_no").value="";
    	        	  unit_nameAuto.val("");	        	  
    	        	  unit_nameAuto.focus();
    	        	  return false;	             
    	          }   	         
    	      }, 
    	      select: function( event, ui ) {
    	    	  var target_unit_name = ui.item.value;
    	    		 $.post("getTargetSUSFromUNITNAME?"+key+"="+value, {
    	    			 target_unit_name:target_unit_name
                 }, function(j) {
                        
                 }).done(function(j) {
                	 var length = j.length-1;
                     var enc = j[length].substring(0,16);
                     $("#sus_no").val(dec(enc,j[0]));   
                         
                 }).fail(function(xhr, textStatus, errorThrown) {
                 });
    	      } 	     
    	}); 			
    });
		
    	$("#unit_where_awarded").keyup(function(){
    		var unit_name = this.value;
    	var unit_nameAuto=$("#unit_where_awarded");

    	unit_nameAuto.autocomplete({
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
                            var dataCountry1 = susval.join("|");
                            var myResponse = []; 
	        	            var autoTextVal=unit_nameAuto.val();
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
    	        	  alert("Please Enter Approved Unit Name.");
    	        	  document.getElementById("sus_no").value="";
    	        	  unit_nameAuto.val("");	        	  
    	        	  unit_nameAuto.focus();
    	        	  return false;	             
    	          }   	         
    	      }, 
    	}); 			
    });
 	 
 	 
 	 
 	$("#issue_to_unit_name").keyup(function(){
		var unit_name = this.value;
	var unit_nameAuto=$("#issue_to_unit_name");

	unit_nameAuto.autocomplete({
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
                        var dataCountry1 = susval.join("|");
                        var myResponse = []; 
        	            var autoTextVal=unit_nameAuto.val();
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
	        	  alert("Please Enter Approved Unit Name.");
	        	  document.getElementById("sus_no").value="";
	        	  unit_nameAuto.val("");	        	  
	        	  unit_nameAuto.focus();
	        	  return false;	             
	          }   	         
	      }, 
	}); 			
});
});
	</script> 
	
	
