
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css">
<link rel="stylesheet" href="js/miso/assets/scss/style.css">

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
	
	<div class="container" align="center">
		<div class="card">
			<div class="card-header">
						<strong><h3>SHOW ANIMAL DETAILS</h3></strong>
			</div>
			<div class="card" id="army_animl" style="display: none; margin-bottom: 0; border: 0;">
				
				<div class="card-header">
					<strong>General</strong>
				</div>
				<div class="card-body card-block">
	
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<input type="hidden" id="id" name="id" value="${show_animal_details_loc_cmnd.id}" class="form-control"> <input type="hidden" id="abc" name="abc" value="${show_animal_details_loc_cmnd.anml_type}" class="form-control"> 
										
									<label for="text-input"	 class=" form-control-label"><strong style="color: red;">*</strong> SUS No</label>
								</div>
								
								<div class="col-12 col-md-8">
									<input type="text" id="sus_no" name="sus_no" class="form-control" value="${show_animal_details_loc_cmnd.sus_no}" readonly="readonly" autocomplete="off">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Unit Name </label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="unit_name" name="" class="form-control" value="${unit_name}" readonly="readonly" autocomplete="off">
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
										<input type="text" id="remount_no" name="remount_no" value="${show_animal_details_loc_cmnd.remount_no}" class="form-control" readonly="readonly" autocomplete="off">
									</div>
								</div>
							</div>
	
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Type</label>
									</div>
									<div class="col-12 col-md-8" style="padding-bottom: 40px">
	
										<select id="type_equines" name="type_equines" class="form-control" disabled="disabled">
											<option value="0">--Select--</option>
											<c:forEach var="item" items="${getAnimalTypeList}"
												varStatus="num">
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
										<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Army Number</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="army_num" name="army_num" class="form-control" value="${show_animal_details_loc_cmnd.army_num}" readonly="readonly" autocomplete="off">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Type of Dog </label>
									</div>
									<div class="col-12 col-md-8">
										<select id="type_dog" name="type_dog" class="form-control" disabled="disabled">
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
										<input type="text" id="name_of_dog" name="name_of_dog" class="form-control" value="${show_animal_details_loc_cmnd.name_of_dog}" readonly="readonly">
									</div>
								</div>
							</div>
	
	
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Specialization</label>
									</div>
									<div class="col-12 col-md-8">
										<select id="specialization" name="specialization"
											class="form-control" disabled="disabled">
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
									<select id="breed" name="breed" class="form-control" disabled="disabled">
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
									<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Color </label>
								</div>
								<div class="col-12 col-md-8">
									<select id="colour" name="colour" class="form-control" disabled="disabled">
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
										<input type="hidden" id="sex_radio" name="sex_radio" value="${show_animal_details_loc_cmnd.sex}" class="form-control" readonly="readonly" disabled="disabled">
										<input type="radio" id="sex1" name="sex" value="MALE" class="form-check-input" value="${show_animal_details_loc_cmnd.sex}" readonly="readonly" disabled="disabled">Male</label>&nbsp;&nbsp;&nbsp; 
										
										<label for="inline-radio2" class="form-check-label ">
										<input type="radio" id="sex2" name="sex" value="FEMALE" class="form-check-input" value="${show_animal_details_loc_cmnd.sex}" readonly="readonly" disabled="disabled">Female</label>&nbsp;&nbsp;&nbsp; 
									
										<label for="inline-radio2" class="form-check-label ">
										<input type="radio" id="sex3" name="sex" value="OTHERS" class="form-check-input" value="${show_animal_details_loc_cmnd.sex}" readonly="readonly" disabled="disabled">Others</label>&nbsp;&nbsp;&nbsp;
									</div>
								</div>
	
								<div class="col-12 col-md-8" id="sex_equ" style="display: none;">
									<div class="form-check-inline form-check">
										<label for="inline-radio1" class="form-check-label ">
										<input type="hidden" id="sex_radio" name="sex_radio" value="${show_animal_details_loc_cmnd.sex}" class="form-control" readonly="readonly" disabled="disabled">
										<input type="radio" id="sex4" name="sex" value="ENTIRE" class="form-check-input" value="${show_animal_details_loc_cmnd.sex}" readonly="readonly" disabled="disabled">Entire</label>&nbsp;&nbsp;&nbsp; 
									
										<label for="inline-radio2" class="form-check-label ">
										<input type="radio" id="sex5" name="sex" value="MARE" class="form-check-input" value="${show_animal_details_loc_cmnd.sex}" readonly="readonly" disabled="disabled">Mare</label>&nbsp;&nbsp;&nbsp;
										
										 <label for="inline-radio2" class="form-check-label ">
										 <input type="radio" id="sex6" name="sex" value="GELD" class="form-check-input" value="${show_animal_details_loc_cmnd.sex}" readonly="readonly" disabled="disabled">Geld</label>&nbsp;&nbsp;&nbsp;
										
										 <label for="inline-radio2" class="form-check-label ">
											 <input type="radio" id="sex7" name="sex" value="RIG" class="form-check-input" value="${show_animal_details_loc_cmnd.sex}" readonly="readonly" disabled="disabled">Rig</label>&nbsp;&nbsp;&nbsp; 
										
										<label for="inline-radio2" class="form-check-label "> 
											<input type="radio" id="sex8" name="sex" value="OTHERS" class="form-check-input" value="${show_animal_details_loc_cmnd.sex}" readonly="readonly" disabled="disabled">Others</label>&nbsp;&nbsp;&nbsp;
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
									<select id="source" name="source" class="form-control" disabled="disabled">
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
									<label for="text-input" class=" form-control-label">Fitness Status</label>
								</div>
								<div class="col-12 col-md-8">
									<select id="fitness_status" name="fitness_status" class="form-control" disabled="disabled">
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
												<input type="text" id="fitness_deployment" name="fitness_deployment" value="${show_animal_details_loc_cmnd.fitness_deployment}" class="form-control" autocomplete ="off" readonly="readonly">
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
									<input type="text" id="details_of_sire" name="details_of_sire" class="form-control" value="${show_animal_details_loc_cmnd.details_of_sire}" readonly="readonly" autocomplete="off">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4"> 
									<label for="text-input" class=" form-control-label">Details of Dam </label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="details_of_dam" name="details_of_dam" class="form-control" value="${show_animal_details_loc_cmnd.details_of_dam}" readonly="readonly" autocomplete="off">
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
									<input type="date" id="date_of_birth" name="date_of_birth" class="form-control" value="${show_animal_details_loc_cmnd.date_of_birth}" onchange="return calculate_age();" readonly="readonly">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Age (YY) </label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="age" name="age" class="form-control" value="${show_animal_details_loc_cmnd.age}" readonly="readonly">
								</div>
							</div>
						</div>
					</div>
	
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label for="text-input" class=" form-control-label">Animal Purchase Cost (&#8377) </label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="animal_purchase_cost" name="animal_purchase_cost" value="${show_animal_details_loc_cmnd.animal_purchase_cost}" class="form-control" autocomplete="off" onkeypress="return validateNumber(event, this)" readonly="readonly">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label for="text-input" class=" form-control-label">Book Value(&#8377) </label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="animal_present_cost" name="animal_present_cost" value="${show_animal_details_loc_cmnd.animal_present_cost}" class="form-control" autocomplete="off" onkeypress="return validateNumber(event, this)" readonly="readonly">
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
									<input type="text" id="microchip_no" name="microchip_no" class="form-control" value="${show_animal_details_loc_cmnd.microchip_no}" autocomplete="off" readonly="readonly">
								</div>
							</div>
						</div>
					
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label for="text-input" class=" form-control-label"> Date of Death </label>
								</div>
								<div class="col-12 col-md-8">
									<input type="date" id="date_of_death" name="date_of_death" value="${show_animal_details_loc_cmnd.date_of_death}" class="form-control" readonly="readonly" placeholder="dd-mm-yyyy">
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
										<input type="hidden" id="disptrans_radio" name="disptrans_radio" value="${show_animal_details_loc_cmnd.disptrans}" class="form-control" readonly="readonly" disabled="disabled">
											
										<input type="radio" id="disptrans1" name="disptrans" value="disp" class="form-check-input" value="${show_animal_details_loc_cmnd.disptrans}" readonly="readonly" disabled="disabled">Disposal</label>&nbsp;&nbsp;&nbsp;
										 
										 <label for="inline-radio2" class="form-check-label ">
										 <input type="radio" id="disptrans2" name="disptrans" value="issue" class="form-check-input" value="${show_animal_details_loc_cmnd.disptrans}" readonly="readonly" disabled="disabled">Issue To Unit</label>&nbsp;&nbsp;&nbsp;
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
									<input type="text" id="disposal" name="disposal" value="${show_animal_details_loc_cmnd.disposal}" class="form-control" autocomplete="off" readonly="readonly">
								</div>
							</div>
						</div>
	
	
						<div class="col-md-6" id="isuue_to_unit1" style="display: none;">
							<div class="row form-group">
								<div class="col col-md-4">
									<label for="text-input" class=" form-control-label">Issue To Unit</label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="issue_to_unit_name" name="issue_to_unit_name" value="${show_animal_details_loc_cmnd.issue_to_unit_name}" class="form-control" autocomplete="off" readonly="readonly">
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
									<input type="date" id="dis_date" name="dis_date" value="${show_animal_details_loc_cmnd.dis_date}" class="form-control" min='1899-01-01' max='${date}' readonly="readonly">
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
									<input type="date" id="issue_date" name="issue_date" value="${show_animal_details_loc_cmnd.issue_date}" class="form-control" min='1899-01-01' max='${date}' readonly="readonly">
								</div>
							</div>
						</div>
					</div>
	
					<div class="col-md-12" id="dispremark" style="display: none;">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label for="text-input" class=" form-control-label">Disposal Remarks</label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="disposal_remarks" name="disposal_remarks" value="${show_animal_details_loc_cmnd.disposal_remarks}" class="form-control" autocomplete="off" readonly="readonly">
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- end of general -->
	
				<div class="card-header">
					<strong>Medals/Decorations</strong>
				</div>
				<div class="card-body card-block">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label for="text-input" class=" form-control-label">Name of Award </label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="medals_desc_details" name="medals_desc_details" value="${show_animal_details_loc_cmnd.medals_desc_details}" class="form-control" readonly="readonly">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label for="text-input" class=" form-control-label">Unit Where Awarded </label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="unit_where_awarded" name="unit_where_awarded" value="${show_animal_details_loc_cmnd.unit_where_awarded}" class="form-control" autocomplete="off" readonly="readonly">
								</div>
							</div>
						</div>
					</div>
	
					<div class="col-md-12" id="remarks" style="display: none;">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label for="text-input" class=" form-control-label">Awarded Remarks</label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="awared_remarks" name="awared_remarks" value="${show_animal_details_loc_cmnd.awared_remarks}" class="form-control" autocomplete="off" readonly="readonly">
								</div>
							</div>
						</div>
					</div>
	
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label for="text-input" class=" form-control-label">Authority</label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="authority" name="authority" value="${show_animal_details_loc_cmnd.authority}" class="form-control" autocomplete="off" readonly="readonly">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label for="text-input" class=" form-control-label">Award Date </label>
								</div>
								<div class="col-12 col-md-8">
									<input type="date" id="award_date" name="award_date" value="${show_animal_details_loc_cmnd.award_date}" class="form-control" readonly="readonly" placeholder="dd-mm-yyyy">
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
					<strong>Other Details</strong>
				</div>
				<div class="card-body card-block">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label for="text-input" class=" form-control-label">TOS</label>
								</div>
								<div class="col-12 col-md-8">
									<input type="date" id="tos" name="tos" class="form-control" value="${show_animal_details_loc_cmnd.tos}" readonly="readonly" placeholder="dd-mm-yyyy">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label for="text-input" class=" form-control-label">TORS</label>
								</div>
								<div class="col-12 col-md-8">
									<input type="date" id="tors" name="tors" class="form-control" value="${show_animal_details_loc_cmnd.tors}" readonly="readonly" placeholder="dd-mm-yyyy">
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
									<input type="date" id="sos" name="sos" class="form-control" value="${show_animal_details_loc_cmnd.sos}" readonly="readonly" placeholder="dd-mm-yyyy">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label for="text-input" class=" form-control-label">SORS</label>
								</div>
								<div class="col-12 col-md-8">
									<input type="date" id="sors" name="sors" class="form-control" value="${show_animal_details_loc_cmnd.sors}" readonly="readonly" placeholder="dd-mm-yyyy">
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- end of other details -->

			<!-- end of card-footer -->
		</div>
		<!-- end of card -->
	</div>
	<!-- end of container -->

	<script>
			$(document).ready(function() {
				var animal = '${show_animal_details_loc_cmnd.anml_type}';
				$("#medals_desc_details").val('${show_animal_details_loc_cmnd.medals_desc_details}'); 
				$("#disposal").val('${show_animal_details_loc_cmnd.disposal}'); 
			
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
					
					var spz = '${show_animal_details_loc_cmnd.specialization}'
					var bre = '${show_animal_details_loc_cmnd.breed}'
					var col = '${show_animal_details_loc_cmnd.colour}'
					var sor = '${show_animal_details_loc_cmnd.source}'
					var fit = '${show_animal_details_loc_cmnd.fitness_status}'
					var tyequ = '${show_animal_details_loc_cmnd.type_equines}'
					var dog = '${show_animal_details_loc_cmnd.type_dog}'
					
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
					
					var beq = '${show_animal_details_loc_cmnd.breed}'
					var ceq = '${show_animal_details_loc_cmnd.colour}'
					var seq = '${show_animal_details_loc_cmnd.source}'
					var fieq = '${show_animal_details_loc_cmnd.fitness_status}'
					var tyequ = '${show_animal_details_loc_cmnd.type_equines}'
					
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
				var n = '${show_animal_details_loc_cmnd.medals_desc_details}'
						if (n == 'OTHERS') {
							$("#remarks").show();
						} else {
							$("#remarks").hide();
						}
						
					    var d =	'${show_animal_details_loc_cmnd.disposal}'
						if (d == 'OTHERS') {
							$("#dispremark").show();
						} else {
							$("#dispremark").hide();
						}
			});
</script>

<script>
			
			
		
			
			function chkDisposal(obj){
				if(document.getElementById(obj.id).checked == true){
					if(obj.id == "isuue_to_unit")
						{
							$('#isuue_to_unit1').show();
							$('#disposal1').hide();
							$('#dispremark').hide();
							$('#disposal_date').show();
							$('#issue_date').hide();
						}
					else
						{
							$('#disposal1').show();
							$('#isuue_to_unit1').hide();
							$('#disposal_date').hide();
							$('#issue_date').show();
						}
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
</script>

	
