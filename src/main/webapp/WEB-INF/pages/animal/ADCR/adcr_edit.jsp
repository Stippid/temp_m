
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>

<link href="js/Calender/jquery-ui.css" rel="Stylesheet">
</links>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>

<style>
/* Apply solid light orange color to all header divs */
.card-header1 {
	background-color: #6b5b44; /* Light orange color */
	color: white; /* Ensure text is readable */
	text-align: center; /* Center-align text */
	cursor: pointer; /* Change cursor to indicate it's clickable */
}

.card-header1:hover {
	opacity: 0.9;
	/* Optional: Add a hover effect to slightly fade the header */
}
</style>

<!-- CREATED BY MITESH GOSAI  -->
<form:form name="edit_adcr" id="edit_adcr" action="#" method='POST'
	commandName="edit_animal_census_cmnd" enctype="multipart/form-data">
	<div class="container" align="center">
		<div class="card">
			<div class="card-header">
				<strong><u>ANIMALS</u></strong> <strong><h3>ADCR: VIEW
					</h3></strong>
			</div>
			<div class="card-body card-block" id="basicbox">
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-6">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong> Select Animal Type</label>
							</div>
							<div class="form-check-inline form-check">
								<label for="inline-radio1" class="form-check-label "> <input
									type="radio" id="anml_type1" name="anml_type" value="Army Dog"
									class="form-check-input" onchange="radioch();">Army
									Dogs
								</label>&nbsp;&nbsp;
								<!-- <label for="inline-radio2" class="form-check-label ">
			                                            <input type="radio" id="anml_type2" name="anml_type" value="Army Equines" class="form-check-input" onchange="radioch();" > Army Equines
			                                          </label> -->
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="card-body card-block ">
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>As on Date:</label>
							</div>
							<div class="col-12 col-md-6">
								<input type="date" id="ason_date" name="ason_date"
									class="form-control" min='1899-01-01' max='${date}'
									maxlength="10">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="card" id="textboxes">
			<div class="card-header card-header1" id="basic-details-header">
				<strong>BASIC DETAILS</strong>
			</div>
			<div class="card-body card-block" id="basicbox2"
				style="display: none;">

				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong> SUS No</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="text" id="sus_no" name="sus_no"
									class="form-control" autocomplete="off" maxlength="8">
								<label id="unit_sus_no_hid" style="display: none"><b>
										${roleSusNo} </b></label>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong> Unit Name</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="text" id="unit_name" name="unit_name"
									class="form-control" autocomplete="off" maxlength="100">
								<label id="unit_name_l" style="display: none"><b>${unit_name}</b></label>
							</div>
						</div>
					</div>
				</div>

				<div id="textboxes3">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label for="text-input" class=" form-control-label"><strong
										style="color: red;">*</strong> Army No</label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="army_num" name="army_num"
										autocomplete="off" class="form-control" maxlength="5"
										oninput="handleInput(this)"
										onchange="army_no_already_exist();"> <input
										type="hidden" id="dog_id" name="dog_id"
										class="form-control autocomplete" autocomplete="off">
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label for="text-input" class=" form-control-label"><strong
										style="color: red;">*</strong> Type of Dog</label>
								</div>
								<div class="col-12 col-md-8">
									<select id="type_dog" name="type_dog" class="form-control">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getTypeOfDogList}"
											varStatus="num">
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
									<label for="text-input" class=" form-control-label"><strong
										style="color: red;">*</strong> Name of Dog</label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="name_of_dog" name="name_of_dog"
										autocomplete="off" class="form-control"
										oninput="this.value = this.value.toUpperCase()" maxlength="35">
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label for="text-input" class=" form-control-label"><strong
										style="color: red;">*</strong> Specialisation</label>
								</div>
								<div class="col-12 col-md-8">
									<select id="specialization" name="specialization"
										class="form-control">
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
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong> Breed</label>
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
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong> Color</label>
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
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong> Sex </label>
							</div>

							<div class="col-12 col-md-8" id="armydog1">
								<div class="form-check-inline form-check">
									<label for="inline-radio1" class="form-check-label "> <input
										type="radio" id="male" name="sex" value="MALE"
										class="form-check-input form-control">Male
									</label>&nbsp;&nbsp; <label for="inline-radio2"
										class="form-check-label "> <input type="radio"
										id="female" name="sex" value="FEMALE"
										class="form-check-input form-control">Female
									</label>&nbsp;&nbsp; <label for="inline-radio3"
										class="form-check-label "> <input type="radio"
										id="others" name="sex" value="OTHERS"
										class="form-check-input form-control">Others
									</label>&nbsp;&nbsp;
								</div>
							</div>


						</div>
					</div>

					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong> Source</label>
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
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong> Date of Birth</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="date" id="date_of_birth" name="date_of_birth"
									class="form-control" onchange="calculate_age();"
									min='1899-01-01' max='${date}' maxlength="10"> <input
									type="hidden" id="date_of_death" name="date_of_death"
									class="form-control" min='1899-01-01' max='${date}'
									maxlength="10">
							</div>
						</div>
					</div>

					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label">Age(YY)</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="text" id="age" name="age" value="0"
									class="form-control" readonly="readonly" maxlength="2">
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>Microchip No</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="text" id="microchip_no" name="microchip_no"
									autocomplete="off" class="form-control" maxlength="20">
								<input type="hidden" id="month" name="month" value="0"
									autocomplete="off" class="form-control"> <input
									type="hidden" id="year" name="year" value="0"
									autocomplete="off" class="form-control">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>TOS</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="date" id="tos" name="tos" class="form-control"
									autocomplete="off" min='1899-01-01' max='${date}'
									maxlength="10">
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="card-body"></div>
			<div class="card-header card-header1" id="deployment-details-header">
				<strong>DEPLOYEMENT DETAILS</strong>
			</div>
			<div class="card-body card-block" id="deployment-details"
				style="display: none;">
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>Fitness Status</label>
							</div>
							<div class="col-12 col-md-8">
								<select id="fitness_status" name="fitness_status"
									class="form-control" onclick="chkFitnessStatus(this);">
									<option value="0">--Select--</option>
									<c:forEach var="item" items="${getFitnessStatusList}"
										varStatus="num">
										<option value="${item[1]}">${item[0]}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
					<div class="col-md-6" id="date_admitted1" style="display: none;">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>Date Admitted</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="date" id="date_admitted" name="date_admitted"
									class="form-control" min='1899-01-01' max='${date}'
									maxlength="10">
							</div>
						</div>
					</div>
					<div class="col-md-6" id="date_unfit1" style="display: none;">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>Date of Unfit</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="date" id="date_unfit" name="date_unfit"
									class="form-control" min='1899-01-01' max='${date}'
									maxlength="10">
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12" id="FIT1" style="display: none;">

					<%-- <div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>Employment</label>
							</div>
							<div class="col-12 col-md-8">
								<select id="Employment" name="Employment" class="form-control">
									<option value="0">--Select--</option>
									<c:forEach var="item" items="${getempList}" varStatus="num">
										<option value="${item[1]}">${item[0]}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div> --%>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>Emp Unit</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="text" id="emp_unit" name="emp_unit"
									class="form-control" autocomplete="off" maxlength="100">
								<input type="text" id="emp_sus" name="emp_sus"
									class="form-control" autocomplete="off" maxlength="8"
									Style="display: none;">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>Location</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="text" id="dog_loc" name="dog_loc" placeholder=""
									class="form-control autocomplete" oninput="handleInput2(this)"
									autocomplete="off">
							</div>
						</div>
					</div>
					
				</div>
				<div class="col-md-12" id="FIT2" style="display: none;">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label">Handler Army No </label>
							</div>
							<div class="col-md-8">

								<input type="text" id="personnel_no1" name="personnel_no1"
									class="form-control autocomplete" autocomplete="off"
									onchange="fn_getOfficerData1()"
									onkeyup="getPersonnel_no(this,'emp_sus') ; specialcharecter(this)"
									maxlength="12" onkeypress="return AvoidSpace(event)">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label">Handler Name</label>
							</div>
							<div class="col-md-8">
								<label id="name1" name="name1"> </label>

							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12" id="FIT3" style="display: none;">
				 <div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label for="text-input" class=" form-control-label"><strong
										style="color: red;">*</strong>Remarks</label>
								</div>
								<div class="col-12 col-md-8">
									<textarea class="form-control char-counter1" maxlength="255"
										id="depremarks" name="depremarks"></textarea>
								</div>
							</div>
						</div> 
				</div>
				<div align="center">
					<button type="button" onclick="submitDepData();"
						class="btn btn-primary btn-sm">Update</button>
				</div>
			</div>
			<div class="card-body"></div>
			<div class="card-header card-header1" id="utilization-details-header">
				<strong>UTILIZATION DETAILS</strong>
			</div>
			<form id="utForm" method="POST" action="saveUtData"
				enctype="multipart/form-data">
			<div class="card-body card-block" id="utilization-details"
				style="display: none;">
				<div class="col-md-12" id="ED1" style="display: none;">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong> Total ROP(Kms)</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="number" id="total_ropED" name="total_rop" min="0"
									autocomplete="off" class="form-control">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>Veh Search(No)</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="number" id="no_of_vehED" name="no_of_veh" min="0"
									autocomplete="off" class="form-control">
							</div>
						</div>
					</div>

				</div>
				<div class="col-md-12" id="ED2" style="display: none;">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>Sanitisation Duty(No)</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="number" id="sanitisation_dutyED"
									name="sanitisation_duty" autocomplete="off" min="0"
									class="form-control">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>VA/VP/VOPs Duties(Hrs)</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="number" id="v_dutiesED" name="v_duties"
									autocomplete="off" min="0" class="form-control"> 
							</div>
						</div>
					</div>

				</div>
				<div class="col-md-12" id="ED3" style="display: none;">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>Explosive detected(No)</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="number" id="detected_explosiveED"
									name="detected_explosive" autocomplete="off" min="0"
									class="form-control">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>Article Search(No)</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="number" id="no_of_articleED" name="no_of_article" min="0"
									autocomplete="off" class="form-control">
							</div>
						</div>
					</div>

				</div>
				<div class="col-md-12" id="ED4" style="display: none;">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>Enemy hideout detected(No)</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="number" id="hideout_EnemyED" name="hideout_Enemy"
									autocomplete="off" min="0" class="form-control">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>Arms/Amn Cache(No)</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="number" id="no_of_armsED" name="no_of_arms"
									autocomplete="off" min="0" class="form-control">
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12" id="ED5" style="display: none;">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>SHBO(No)</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="number" id="no_of_shboED" name="no_of_shbo"
									autocomplete="off" min="0" class="form-control">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>Refresher Trg(Days)</label>
							</div>
							<div class="col-12 col-md-8">
							<select id="no_of_reftrgED" name="no_of_reftrg"
									class="form-control">
									<option value="0">--Select--</option>
									<option value="1">Yes</option>
									<option value="2">No</option>
								</select>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12" id="ED6" style="display: none;">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>Patrolling(Km)</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="number" id="ptr_kmED" name="ptr_km"
									autocomplete="off" min="0" class="form-control">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label">Spl
									Achievement/Remarks </label>
							</div>
							<div class="col-md-8">
								<textarea class="form-control char-counter1" maxlength="255"
									id="remarksED" name="remarks"></textarea>
							</div>
						</div>
					</div>

				</div>
				<div class="col-md-12" id="MD1" style="display: none;">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong> Total ROP(Kms)</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="number" id="total_ropMD" name="total_rop"
									autocomplete="off" min="0" class="form-control">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>Veh Search(Nos)</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="number" id="no_of_vehMD" name="no_of_veh"
									autocomplete="off" min="0" class="form-control">
							</div>
						</div>
					</div>

				</div>
				<div class="col-md-12" id="MD2" style="display: none;">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>Sanitisation Duty(No)</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="number" id="sanitisation_dutyMD"
									name="sanitisation_duty" autocomplete="off" min="0"
									class="form-control">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>Patrolling(Km)</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="number" id="ptr_kmMD" name="ptr_km"
									autocomplete="off" min="0" class="form-control">
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12" id="MD3" style="display: none;">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>Explosive detected(No)</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="number" id="detected_explosiveMD"
									name="detected_explosive" autocomplete="off" min="0"
									class="form-control">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>Article Search(No)</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="number" id="no_of_articleMD" name="no_of_article" min="0"
									autocomplete="off" class="form-control">
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12" id="MD4" style="display: none;">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>Enemy hideout detected(No)</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="number" id="hideout_EnemyMD" name="hideout_Enemy" min="0"
									autocomplete="off" class="form-control">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>Arms/Amn Cache(No)</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="number" id="no_of_armsMD" name="no_of_arms" min="0"
									autocomplete="off" class="form-control">
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12" id="MD5" style="display: none;">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>SHBO(No)</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="number" id="no_of_shboMD" name="no_of_shbo" min="0"
									autocomplete="off" class="form-control">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>Refresher Trg(Days)</label>
							</div>
							<div class="col-12 col-md-8">
							<select id="no_of_reftrgMD" name="no_of_reftrg"
									class="form-control">
									<option value="0">--Select--</option>
									<option value="1">Yes</option>
									<option value="2">No</option>
								</select>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12" id="MD6" style="display: none;">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>Mines detected(No)</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="number" id="detected_mine" name="detected_mine" min="0"
									autocomplete="off" class="form-control">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label">Spl
									Achievement/Remarks </label>
							</div>
							<div class="col-md-8">
								<textarea class="form-control char-counter1" maxlength="255"
									id="remarksMD" name="remarks"></textarea>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12" id="GD1" style="display: none;">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>Patrolling(Km)</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="number" id="ptr_kmGD" name="ptr_km" min="0"
									autocomplete="off" class="form-control">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>Guard duty Gate/Perimeter(No)</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="number" id="total_guard" name="total_guard" min="0"
									autocomplete="off" class="form-control">
							</div>
						</div>
					</div>

				</div>
				<div class="col-md-12" id="GD2" style="display: none;">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>SHBO(No)</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="number" id="no_of_shboGD" name="no_of_shbo" min="0"
									autocomplete="off" class="form-control">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>Refresher Trg(Days)</label>
							</div>
							<div class="col-12 col-md-8">
							<select id="no_of_reftrgGD" name="no_of_reftrg"
									class="form-control">
									<option value="0">--Select--</option>
									<option value="1">Yes</option>
									<option value="2">No</option>
								</select>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12" id="GD3" style="display: none;">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>Area Domination Patrolling(Km)</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="number" id="area_kmsGD" name="area_kms" min="0"
									autocomplete="off" class="form-control">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label">Spl
									Achievement/Remarks </label>
							</div>
							<div class="col-md-8">
								<textarea class="form-control char-counter1" maxlength="255"
									id="remarksGD" name="remarks"></textarea>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12" id="IP1" style="display: none;">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>SHBO(No)</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="number" id="no_of_shboIP" name="no_of_shbo"
									autocomplete="off" min="0" class="form-control">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>Refresher Trg(Days)</label>
							</div>
							<div class="col-12 col-md-8">
							<select id="no_of_reftrgIP" name="no_of_reftrg"
									class="form-control">
									<option value="0">--Select--</option>
									<option value="1">Yes</option>
									<option value="2">No</option>
								</select>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12" id="IP2" style="display: none;">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>Patrolling(Km)</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="number" id="ptr_kmIP" name="ptr_km"
									autocomplete="off" min="0" class="form-control">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label">Spl
									Achievement/Remarks </label>
							</div>
							<div class="col-md-8">
								<textarea class="form-control char-counter1" maxlength="255"
									id="remarksIP" name="remarks"></textarea>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12" id="SAR1" style="display: none;">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>SHBO(No)</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="number" id="no_of_shboSAR" name="no_of_shbo"
									autocomplete="off" min="0" class="form-control">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>Refresher Trg(Days)</label>
							</div>
							<div class="col-12 col-md-8">
							<select id="no_of_reftrgSAR" name="no_of_reftrg"
									class="form-control">
									<option value="0">--Select--</option>
									<option value="1">Yes</option>
									<option value="2">No</option>
								</select>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12" id="SAR2" style="display: none;">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>Detected under debris(No)</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="number" id="detected_debris" name="detected_debris"
									autocomplete="off" min="0" class="form-control">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label">Spl
									Achievement/Remarks </label>
							</div>
							<div class="col-md-8">
								<textarea class="form-control char-counter1" maxlength="255"
									id="remarksSAR" name="remarks"></textarea>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12" id="ARO1" style="display: none;">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>SHBO(No)</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="number" id="no_of_shboAR" name="no_of_shbo"
									autocomplete="off" min="0" class="form-control">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>Refresher Trg(Days)</label>
							</div>
							<div class="col-12 col-md-8">
							<select id="no_of_reftrgAR" name="no_of_reftrg"
									class="form-control">
									<option value="0">--Select--</option>
									<option value="1">Yes</option>
									<option value="2">No</option>
								</select>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12" id="ARO2" style="display: none;">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>Detected under avalnches(No)</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="number" min="0" id="detected_avalnches"
									name="detected_avalnches" autocomplete="off"
									class="form-control">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label">Spl
									Achievement/Remarks </label>
							</div>
							<div class="col-md-8">
								<textarea class="form-control char-counter1" maxlength="255"
									id="remarksAR" name="remarks"></textarea>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12" id="ASTL1" style="display: none;">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>Patrolling(Km)</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="number" id="ptr_kmAS" name="ptr_km"
									autocomplete="off" min="0" class="form-control">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>Ops participation Room(No)</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="number" id="ops_room" name="ops_room"
									autocomplete="off" min="0" class="form-control">
							</div>
						</div>
					</div>

				</div>
				<div class="col-md-12" id="ASTL2" style="display: none;">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>Ops terrorist Killed(No)</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="number" id="ops_killed" name="ops_killed"
									autocomplete="off" min="0" class="form-control">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>Ops terrorist Apprehended(No)</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="number" id="ops_apprehended" name="ops_apprehended"
									autocomplete="off" min="0" class="form-control">
							</div>
						</div>
					</div>

				</div>
				<div class="col-md-12" id="ASTL3" style="display: none;">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>SHBO(No)</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="number" id="no_of_shboAS" name="no_of_shbo"
									autocomplete="off" min="0" class="form-control">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>Refresher Trg(Days)</label>
							</div>
							<div class="col-12 col-md-8">
							<select id="no_of_reftrgAS" name="no_of_reftrg"
									class="form-control">
									<option value="0">--Select--</option>
									<option value="1">Yes</option>
									<option value="2">No</option>
								</select>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12" id="ASTL4" style="display: none;">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>Area Domination Patrolling(Km)</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="number" id="area_kmsAS" name="area_kms"
									autocomplete="off" min="0" class="form-control">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label">Spl
									Achievement/Remarks </label>
							</div>
							<div class="col-md-8">
								<textarea class="form-control char-counter1" maxlength="255"
									id="remarksAS" name="remarks"></textarea>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12" id="ND1" style="display: none;">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>Article Search(No)</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="number" id="no_of_articleND" name="no_of_article"
									autocomplete="off" min="0" class="form-control">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>VA/VP/VOPs Duties(Hrs)</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="number" id="v_dutiesND" name="v_duties"
									autocomplete="off" min="0" class="form-control">
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12" id="ND2" style="display: none;">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>SHBO(No)</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="number" id="no_of_shboND" name="no_of_shbo"
									autocomplete="off" min="0" class="form-control">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>Refresher Trg(Days)</label>
							</div>
							<div class="col-12 col-md-8">
							<select id="no_of_reftrgND" name="no_of_reftrg"
									class="form-control">
									<option value="0">--Select--</option>
									<option value="1">Yes</option>
									<option value="2">No</option>
								</select>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12" id="ND3" style="display: none;">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label">Spl
									Achievement/Remarks </label>
							</div>
							<div class="col-md-8">
								<textarea class="form-control char-counter1" maxlength="255"
									id="remarksND" name="remarks"></textarea>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12" id="TR1" style="display: none;">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong> Total ROP(Kms)</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="number" id="total_ropTR" name="total_rop"
									autocomplete="off" min="0" class="form-control">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>Patrolling(Km)</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="number" id="ptr_kmTR" name="ptr_km"
									autocomplete="off" min="0" class="form-control">
							</div>
						</div>
					</div>

				</div>
				<div class="col-md-12" id="TR2" style="display: none;">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>Enemy hideout detected(No)</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="number" id="hideout_EnemyTR" name="hideout_Enemy"
									autocomplete="off" min="0" class="form-control">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>Arms/Amn Cache(No)</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="number" id="no_of_armsTR" name="no_of_arms"
									autocomplete="off" min="0" class="form-control">
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12" id="TR3" style="display: none;">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>SHBO(No)</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="number" id="no_of_shboTR" name="no_of_shbo"
									autocomplete="off" min="0" class="form-control">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>Refresher Trg(Days)</label>
							</div>
							<div class="col-12 col-md-8">
							<select id="no_of_reftrgTR" name="no_of_reftrg"
									class="form-control">
									<option value="0">--Select--</option>
									<option value="1">Yes</option>
									<option value="2">No</option>
								</select>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12" id="TR4" style="display: none;">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>Dog detected direction of
									escape(No)</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="number" id="detected_escape" name="detected_escape"
									autocomplete="off" min="0" class="form-control">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label">Spl
									Achievement/Remarks </label>
							</div>
							<div class="col-md-8">
								<textarea class="form-control char-counter1" maxlength="255"
									id="remarksTR" name="remarks"></textarea>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-6">
								<label for="text-input" class=" form-control-label">Mentioned
									on E-Sitrep</label>
							</div>
							<div class="form-check-inline form-check">
								<label for="inline-radio3" class="form-check-label "> <input
									type="radio" id="esitrep_status1" name="esitrep_status"
									value="1" class="form-check-input">Yes
								</label>&nbsp;&nbsp; <label for="inline-radio4"
									class="form-check-label "> <input type="radio"
									id="esitrep_status2" name="esitrep_status" value="0"
									class="form-check-input">No
								</label>
							</div>
						</div>
					</div>
					<div class="col-md-6" id="esitrep" Style="display: none;">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label">E-Sitrep
									No</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="text" id="esitrep_num" name="esitrep_num"
									placeholder="" class="form-control autocomplete"
									oninput="handleInput2(this)" autocomplete="off">
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									Upload Documents : <span style="color: red;">(*.zip,
										*.rar only, Max size 2 MB)</span>
								</div>

								<div class="col-md-8">
									<input type="file" id="uploadfile" name="uploadfile"
										title="UTDATA Upload .ZIP ,.RAR only " />
								</div>
							</div>
						</div>
					</div>
				<div align="center">
					<button type="button" onclick="submitUtData();"
						class="btn btn-primary btn-sm">Update</button>
				</div>
			</div>
			</form>
			<div class="card-body"></div>
			<div class="card-header card-header1" id="HM-details-header">
				<strong>HEALTH & MEDICAL DETAILS</strong>
			</div>
			<div class="card-body card-block" id="HM-details"
				style="display: none;">
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>Hospital Name</label>
							</div>
							<div class="col-12 col-md-8">
								<select id="hosp_name" name="hosp_name" class="form-control"
									onclick="chkFitnessStatus(this);">
									<option value="0">--Select--</option>
									<c:forEach var="item" items="${getHospList}" varStatus="num">
										<option value="${item[1]}">${item[0]}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>Medical Report As on</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="date" id="med_date" name="med_date"
									class="form-control" min='1899-01-01' max='${date}'
									maxlength="10">
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>Hemoglobin</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="number" id="hemoglobin" name="hemoglobin"
									autocomplete="off" min="0" class="form-control">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>Weight</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="number" id="weight" name="weight"
									autocomplete="off" min="0" class="form-control">
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>TLC</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="number" id="tlc" name="tlc" autocomplete="off" min="0"
									class="form-control">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>DLC</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="number" id="dlc" name="dlc" autocomplete="off" min="0"
									class="form-control">
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>TRBC</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="number" id="trbc" name="trbc" autocomplete="off" min="0"
									class="form-control">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>PCV</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="number" id="pcv" name="pcv" autocomplete="off" min="0"
									class="form-control">
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>Platelet</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="number" id="platelet" name="platelet" min="0"
									autocomplete="off" class="form-control">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>SGOT</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="number" id="sgot" name="sgot" autocomplete="off" min="0"
									class="form-control">
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>Bilirubin</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="number" id="bill" name="bill" autocomplete="off" min="0"
									class="form-control">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>BUN</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="number" id="bun" name="bun" autocomplete="off" min="0"
									class="form-control">
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>Protein</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="number" id="protein" name="protein" min="0"
									autocomplete="off" class="form-control">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>Creatinine</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="number" id="creatinine" name="creatinine" min="0"
									autocomplete="off" class="form-control">
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>Albumin</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="number" id="albun" name="albun" autocomplete="off" min="0"
									class="form-control">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>AGR</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="number" id="agr" name="agr" autocomplete="off" min="0"
									class="form-control">
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>Stool</label>
							</div>
							<div class="form-check-inline form-check">
								<label for="inline-radio3" class="form-check-label "> <input
									type="radio" id="stool_status1" name="stool_status"
									value="1" class="form-check-input">+ve
								</label>&nbsp;&nbsp; <label for="inline-radio4"
									class="form-check-label "> <input type="radio"
									id="stool_status2" name="stool_status" value="0"
									class="form-check-input">-ve
								</label>
							</div>
						</div>
					</div>
					<div class="col-md-6" id="stool1" Style="display: none;">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>Stool Remarks</label>
							</div>
							<div class="col-12 col-md-8">

								<input type="text" id="stool" name="stool" placeholder=""
									class="form-control autocomplete" oninput="handleInput2(this)"
									autocomplete="off">
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>Urine</label>
							</div>
							<div class="form-check-inline form-check">
								<label for="inline-radio3" class="form-check-label "> <input
									type="radio" id="urine_status1" name="urine_status"
									value="1" class="form-check-input">+ve
								</label>&nbsp;&nbsp; <label for="inline-radio4"
									class="form-check-label "> <input type="radio"
									id="urine_status2" name="urine_status" value="0"
									class="form-check-input">-ve
								</label>
							</div>
						</div>
					</div>
					<div class="col-md-6" id="urine1" Style="display: none;">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>Urine Remarks</label>
							</div>
							<div class="col-12 col-md-8">

								<input type="text" id="urine" name="urine" placeholder=""
									class="form-control autocomplete" oninput="handleInput2(this)"
									autocomplete="off">
							</div>
						</div>
					</div>
				</div>
				<div align="center">
					<button type="button" onclick="submitHospData();"
						class="btn btn-primary btn-sm">Update</button>
				</div>
			</div>
			<div class="card-body"></div>
			<div class="card-header card-header1" id="vaccine-details-header">
				<strong>VACCINATION DETAILS</strong>
			</div>
			<div class="card-body card-block" id="vaccine-details"
				style="display: none;">
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>Vaccine</label>
							</div>
							<div class="col-12 col-md-8">
								<select id="vaccine" name="vaccine" class="form-control">
									<option value="0">--Select--</option>
									<c:forEach var="item" items="${getvaccineList}" varStatus="num">
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
								<label for="text-input" class=" form-control-label">Allergy</label>
							</div>
							<div class="form-check-inline form-check">
								<label for="inline-radio3" class="form-check-label "> <input
									type="radio" id="allergy_status1" name="allergy_status"
									value="1" class="form-check-input">Yes
								</label>&nbsp;&nbsp; <label for="inline-radio4"
									class="form-check-label "> <input type="radio"
									id="allergy_status2" name="allergy_status" value="0"
									class="form-check-input">No
								</label>
							</div>
						</div>
					</div>
					<div class="col-md-6" id="allergy1" Style="display: none;">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label">Allergy
									Remarks</label>
							</div>
							<div class="col-12 col-md-8">

								<input type="text" id="allergy" name="allergy" placeholder=""
									class="form-control autocomplete" oninput="handleInput2(this)"
									autocomplete="off">
							</div>
						</div>
					</div>
				</div>
				<div align="center">
					<button type="button" onclick="submitVcData();"
						class="btn btn-primary btn-sm">Update</button>
				</div>
			</div>
			<div class="card-body"></div>
			<div class="card-header card-header1" id="training-details-header">
				<strong>TRAINING RECORD DETAILS</strong>
			</div>
			<div class="card-body card-block" id="training-details"
				style="display: none;">
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label">Training SUS No </label>
							</div>
							<div class="col-md-8">
								<input type="text" id="from_sus_no" name="from_sus_no"
									onkeyup="search_sus1from(this,'traning_place') ; specialcharecter(this)"
									tabindex="-1" class="form-control autocomplete"
									autocomplete="off" maxlength="8"
									onkeypress="return AvoidSpace(event)">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label">Training Unit Name </label>
							</div>
							<div class="col-md-8">
								<input type="text" id="traning_place" name="traning_place"
									onkeyup="search_unit1_from(this,'from_sus_no') ; specialcharecter(this)"
									tabindex="-1" class="form-control autocomplete" maxlength="50"
									autocomplete="off">


							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label">Trainer Army No </label>
							</div>
							<div class="col-md-8">

								<input type="text" id="personnel_no" name="personnel_no"
									class="form-control autocomplete" autocomplete="off"
									onchange="personal_number()"
									onkeyup="getPersonnel_no(this,'from_sus_no') ; specialcharecter(this)"
									maxlength="12" onkeypress="return AvoidSpace(event)">
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label">Trainer Name</label>
							</div>
							<div class="col-md-8">
								<label id="name" name="name"> </label>

							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label">Trainer Rank</label>
							</div>
							<div class="col-md-8">
								<label id="rank" name="rank"> </label>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label">Performance</label>
							</div>
							<div class="col-12 col-md-8">
								<select id="performance_status" name="performance_status"
									class="form-control">
									<option value="0">--Select--</option>
									<option value="1">Satisfactory</option>
									<option value="2">Good</option>
									<option value="3">Excellent</option>
								</select>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label">Trainer
									Mobile No</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="number" id="trainer_mobno" name="trainer_mobno"
									autocomplete="off" class="form-control" maxlength="10">
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label">From
									Date</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="date" id="training_from_date"
									name="training_from_date" autocomplete="off"
									class="form-control">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label">To
									Date</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="date" id="training_to_date" name="training_to_date"
									autocomplete="off" class="form-control">
							</div>
						</div>
					</div>
				</div>
				<div class="card-header" style="text-align: center;">
					<strong>VALIDATOR RECORD DETAILS</strong>
				</div>
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label">Validator SUS No</label>
							</div>
							<div class="col-md-8">
								<input type="text" id="valid_sus_no" name="valid_sus_no"
									onkeyup="search_sus1from(this,'valid_unit_name') ; specialcharecter(this)"
									tabindex="-1" onchange="To_sus_check()"
									class="form-control autocomplete" autocomplete="off"
									maxlength="8" onkeypress="return AvoidSpace(event)">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label">Validator Unit Name </label>
							</div>
							<div class="col-md-8">

								<input type="text" id="valid_unit_name" name="valid_unit_name"
									onkeyup="search_unit1_from(this,'valid_sus_no') ; specialcharecter(this)"
									tabindex="-1" class="form-control autocomplete" maxlength="50"
									autocomplete="off">


							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label">Validator Pers No</label>
							</div>
							<div class="col-md-8">
								<input type="text" id="valid_pers_no" name="valid_pers_no"
									class="form-control autocomplete" autocomplete="off"
									onchange="validpers_number()"
									onkeyup="getPers_no(this,'valid_sus_no') ; specialcharecter(this)"
									maxlength="9" onkeypress="return AvoidSpace(event)">
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label">Validator Name</label>
							</div>
							<div class="col-md-8">
								<label id="v_name" name="v_name"> </label>

							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label">Validator Rank</label>

							</div>
							<div class="col-md-8">
								<label id="v_rank" name="v_rank"> </label>
							</div>
						</div>
					</div>

				</div>
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label">Exam
									Performance</label>
							</div>
							<div class="col-12 col-md-8">
								<select id="exam_status" name="exam_status" class="form-control">
									<option value="0">--Select--</option>
									<option value="1">Satisfactory</option>
									<option value="2">Good</option>
									<option value="3">Excellent</option>
								</select>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>Exam Remarks</label>
							</div>
							<div class="col-12 col-md-8">
								<textarea class="form-control char-counter1" maxlength="255"
									id="exam_remarks" name="exam_remarks"></textarea>
							</div>
						</div>
					</div>
				</div>
				<div align="center">
					<button type="button" onclick="submittrgData();"
						class="btn btn-primary btn-sm">Update</button>
				</div>
			</div>
			<div class="card-body"></div>
			<div class="card-header card-header1" id="dewaorming-details-header">
				<strong>DEWORMING DETAILS</strong>
			</div>
			<div class="card-body card-block" id="dewaorming-details"
				style="display: none;">
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong
									style="color: red;">*</strong>Drug Used</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="text" id="used_drug" name="used_drug"
									placeholder="" class="form-control autocomplete"
									oninput="handleInput2(this)" autocomplete="off">
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label">Allergy</label>
							</div>
							<div class="form-check-inline form-check">
								<label for="inline-radio3" class="form-check-label "> <input
									type="radio" id="allergy_status11" name="allergy_status1"
									value="1" class="form-check-input">Yes
								</label>&nbsp;&nbsp; <label for="inline-radio4"
									class="form-check-label "> <input type="radio"
									id="allergy_status21" name="allergy_status1" value="0"
									class="form-check-input">No
								</label>
							</div>
						</div>
					</div>
					<div class="col-md-6" id="allergy11" Style="display: none;">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label">Allergy
									Remarks</label>
							</div>
							<div class="col-12 col-md-8">

								<input type="text" id="allergy13" name="allergy13"
									placeholder="" class="form-control autocomplete"
									oninput="handleInput2(this)" autocomplete="off">
							</div>
						</div>
					</div>
				</div>
				<div align="center">
					<button type="button" onclick="submitDewData();"
						class="btn btn-primary btn-sm">Update</button>
				</div>
			</div>
			<div class="card-body"></div>
			<div class="card-header card-header1" id="award-details-header">
				<strong>AWARD DETAILS</strong>
			</div>
			<form id="awardForm" method="POST" action="saveAwData"
				enctype="multipart/form-data">
				<div class="card-body card-block" id="award-details"
					style="display: none;">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label for="text-input" class=" form-control-label"><strong
										style="color: red;">*</strong>Award Name</label>
								</div>
								<div class="col-12 col-md-8">
									<select id="award" name="award" class="form-control">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getAwardList}" varStatus="num">
											<option value="${item[1]}">${item[0]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label for="text-input" class=" form-control-label"><strong
										style="color: red;">*</strong>ACT/Remarks</label>
								</div>
								<div class="col-12 col-md-8">
									<textarea class="form-control char-counter1" maxlength="255"
										id="remarks1" name="remarks"></textarea>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									Upload Documents : <span style="color: red;">(*.zip,
										*.rar only, Max size 2 MB)</span>
								</div>

								<div class="col-md-8">
									<input type="file" id="uploadfile" name="uploadfile"
										title="TREE Upload .ZIP ,.RAR only " />
								</div>
							</div>
						</div>
					</div>
					<div align="center">
						<button type="button" onclick="submitAwData();"
							class="btn btn-primary btn-sm">Update</button>
					</div>
				</div>
			</form>

			<div class="card-body"></div>
			<div class="form-control card-footer" align="center">
				<button type="button" onclick="submitDogData();"
					class="btn btn-primary btn-sm">Submit</button>
					<button type="button" class="btn btn-success btn-sm" onclick="goBack()">Back</button>
			</div>


		</div>

	</div>

</form:form>
<script>
    // Disable all form elements inside the div with id 'basicbox'
    document.querySelectorAll('#basicbox input, #basicbox select, #basicbox textarea').forEach(function(element) {
        element.disabled = true;
    });
    document.querySelectorAll('#basicbox2 input, #basicbox2 select, #basicbox2 textarea').forEach(function(element) {
        element.disabled = true;
    });
</script>
<script>
  $(document).ready(function() {
	  
	  $("#basic-details-header").click(function() {
	      $("#basicbox2").toggle(); 
	    });
   
    $("#utilization-details-header").click(function() {
      $("#utilization-details").toggle(); 
    });
    
    $("#deployment-details-header").click(function() {
        $("#deployment-details").toggle(); 
      });
    
    $("#HM-details-header").click(function() {
        $("#HM-details").toggle(); 
      });
    
    $("#vaccine-details-header").click(function() {
        $("#vaccine-details").toggle(); 
      });
    
    $("#training-details-header").click(function() {
        $("#training-details").toggle(); 
      });
    
    $("#dewaorming-details-header").click(function() {
        $("#dewaorming-details").toggle(); 
      });
    
    $("#award-details-header").click(function() {
        $("#award-details").toggle(); 
      });
  });
</script>

<script>
$(document).ready(function() {
    $('#specialization').change(function() {
        var specialization = ${edit_animal_census_cmnd.specialization};
        if (specialization=="64") {
            $('#ED1').show();
            $('#ED2').show();
            $('#ED3').show();
            $('#ED4').show();
            $('#ED5').show();
            $('#ED6').show();
        } else {
            $('#ED1').hide(); 
            $('#ED2').hide(); 
            $('#ED3').hide(); 
            $('#ED4').hide(); 
            $('#ED5').hide(); 
            $('#ED6').hide(); 
        }
        if (specialization=="121") {
            $('#MD1').show();
            $('#MD2').show();
            $('#MD3').show();
            $('#MD4').show();
            $('#MD5').show();
            $('#MD6').show();
        } else {
            $('#MD1').hide(); 
            $('#MD2').hide(); 
            $('#MD3').hide(); 
            $('#MD4').hide(); 
            $('#MD5').hide(); 
            $('#MD6').hide(); 
        }
        if (specialization=="53") {
            $('#GD1').show();
            $('#GD2').show();
            $('#GD3').show();
        } else {
            $('#GD1').hide(); 
            $('#GD2').hide();
            $('#GD3').hide(); 
        }
        if (specialization=="118") {
            $('#IP1').show();
            $('#IP2').show();
        } else {
            $('#IP1').hide(); 
            $('#IP2').hide(); 
        }
        if (specialization=="124") {
            $('#SAR1').show();
            $('#SAR2').show();
        } else {
            $('#SAR1').hide(); 
            $('#SAR2').hide(); 
        }
        if (specialization=="117") {
            $('#ARO1').show();
            $('#ARO2').show();
        } else {
            $('#ARO1').hide(); 
            $('#ARO2').hide(); 
        }
        if (specialization=="52") {
            $('#ASTL1').show();
            $('#ASTL2').show();
            $('#ASTL3').show();
            $('#ASTL4').show();
        } else {
            $('#ASTL1').hide(); 
            $('#ASTL2').hide();
            $('#ASTL3').hide(); 
            $('#ASTL4').hide(); 
        }
        if (specialization=="131") {
            $('#ND1').show();
            $('#ND2').show();
            $('#ND3').show();
        } else {
            $('#ND1').hide(); 
            $('#ND2').hide();
            $('#ND3').hide(); 
        }
        if (specialization=="120") {
            $('#TR1').show();
            $('#TR2').show();
            $('#TR3').show();
            $('#TR4').show();
        } else {
            $('#TR1').hide(); 
            $('#TR2').hide();
            $('#TR3').hide(); 
            $('#TR4').hide(); 
        }
    });
    $('#specialization').trigger('change');
});

</script>
<script>
$(function() {
	$('input[name="esitrep_status"]').on('click', function() {
		
		if ($(this).val() == '1') {
			$('#esitrep').show();
			
		}else{
			$('#esitrep').hide();
		} 
	});
});
</script>
<script>
$(function() {
	$('input[name="allergy_status"]').on('click', function() {
		
		if ($(this).val() == '1') {
			$('#allergy1').show();
			
		}else{
			$('#allergy1').hide();
		} 
	});
});
$(function() {
	$('input[name="allergy_status1"]').on('click', function() {
		
		if ($(this).val() == '1') {
			$('#allergy11').show();
			
		}else{
			$('#allergy11').hide();
		} 
	});
});
$(function() {
	$('input[name="stool_status"]').on('click', function() {
		
		if ($(this).val() == '1') {
			$('#stool1').show();
			
		}else{
			$('#stool1').hide();
		} 
	});
});
$(function() {
	$('input[name="urine_status"]').on('click', function() {
		
		if ($(this).val() == '1') {
			$('#urine1').show();
			
		}else{
			$('#urine1').hide();
		} 
	});
});
</script>
<script>
	

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
	var anml_type;
		$(function() {
			$('input[name="anml_type"]').on('click', function() {
				
				if ($(this).val() == 'Army Dog') {
					anml_type="Army Dog";
					$('#textboxes').show();
					$('#textboxes3').show();
					$('#textboxes4').show();
					$('#source').show();
					$('#textboxes10').show();
					$('#otherdetails').show();
					$('#textboxes1').hide();
					$('#deplmnt').show();
					$('#armydog1').show();
					$('#armyequines1').hide();
					$('#date_admitted1').hide();
			
				} 
			});
		});
	</script>

<script>
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
		}
	
	</script>




<script>
	
 		 function Validate() { 
				
			
			var anml_type = $('input[name=anml_type]:checked').val();
			if(anml_type == undefined){
				alert("Please Select Animal Type");
				return false;
			}
			
			if(anml_type == "Army Dog"){
				
				if ($("input#army_num").val() == "" ) {
					alert("Please Enter Army no.");
					return false;
				}
				
				
				if ($("select#type_dog").val() == "0") {
					alert("Please Select Type of Dog");
					$("select#type_dog").focus();
					return false;
				}
			
				if ($("#name_of_dog").val() == "") {
					alert("Please Enter Name of Dog");
					$("#name_of_dog").focus();
					return false;
				}
				
				if ($("#specialization").val() == "0") {
					alert("Please Select Specialisation");
					$("#specialization").focus();
					return false;
				}
			} 
			

			
			if($("select#breed").val() == "0"){
				alert("Please Select Breed");
				$("input#breed").focus();
				return false;
			}
		
		if($("select#colour").val() == "0"){
			alert("Please Select Color");
			$("input#colour").focus();
			return false;
		}
			
			var sex = $('input[name=sex]:checked').val();
			if(sex == undefined){
				alert("Please Select Sex");
				return false;
			}
			
			
			if($("select#source").val() == "0"){
				alert("Please Select Source");
				$("input#source").focus();
				return false;
			}
				
				if ($("input#date_of_birth").val() == "") {                  
					alert("Please Enter Date of Birth");
					$("input#date_of_birth").focus();
					return false;
				}
			
				
			 return true;
		}    
		 
		
		function chkFitnessStatus(obj){
			
			if(obj.value=='3'){
				$('#date_unfit1').show();
				$('#date_admitted1').hide();
				$('#FIT1').hide();
				$('#FIT2').hide();
				$('#FIT3').show();
				
			}
			else if(obj.value=='7'){
				$('#date_admitted1').show();
				$('#date_unfit1').hide();
				$('#FIT1').hide();
				$('#FIT2').hide();
				$('#FIT3').show();
			}
			else if(obj.value=='2'){
				$('#date_admitted1').hide();
				$('#date_unfit1').hide();
				$('#FIT1').hide();
				$('#FIT2').hide();
				$('#FIT3').hide();
				
			}
			else if(obj.value=='4'){
				$('#date_admitted1').hide();
				$('#date_unfit1').hide();
				$('#FIT1').show();
				$('#FIT2').show();
				$('#FIT3').hide();
			}else{
				$('#date_admitted1').hide();
				$('#date_unfit1').hide();
				$('#FIT1').hide();
				$('#FIT2').hide();
				$('#FIT3').hide();
			}
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
	     	   
	  });
	  </script>

<Script>
		$(document).ready(function() {
			
			 $.ajaxSetup({
		         async : false
		 	});
			 
			$("#dog_id").val('${edit_animal_census_cmnd.id}');
			var selectedValue = '${edit_animal_census_cmnd.animal_type}';
		    $("input[name='anml_type'][value='" + selectedValue + "']").prop("checked", true);
			/* $("#anml_type1").val('${edit_animal_census_cmnd.animal_type}'); */
			$("#army_num").val('${edit_animal_census_cmnd.army_no}');
			$("input#sus_no").val('${edit_animal_census_cmnd.unit_sus_no}');
			
			var sus_no ='${edit_animal_census_cmnd.unit_sus_no}';
			getunit(sus_no);
			console.log("unitname "+ sus_no);
			
			function getunit(val) {	
		        $.post("getTargetUnitNameList?"+key+"="+value, {
		        	sus_no : sus_no
		    }, function(j) {
		            
		            

		    }).done(function(j) {
		            for (var i = 0; i < j.length; i++) {
						   var length = j.length-1;
			                   var enc = j[length].substring(0,16); 
			                   $("#unit_name").val(dec(enc,j[0])); 
					}
		    }).fail(function(xhr, textStatus, errorThrown) {
		    });
		}
			
			var selectedValue = '${edit_animal_census_cmnd.gender}';
		    $("input[name='sex'][value='" + selectedValue + "']").prop("checked", true);
			$("#type_dog").val('${edit_animal_census_cmnd.type_of_dog}');
			$("#name_of_dog").val('${edit_animal_census_cmnd.name}');
			$("#specialization").val('${edit_animal_census_cmnd.specialization}');
			$("#breed").val('${edit_animal_census_cmnd.breed}');
			$("#colour").val('${edit_animal_census_cmnd.color}');
			$("#source").val('${edit_animal_census_cmnd.source}');
			
			 $("#date_of_birth").val(ParseDateColumn('${edit_animal_census_cmnd.date_of_birth}'));
			 console.log("date of birth-----------"+'${edit_animal_census_cmnd.date_of_birth}')
			 calculate_age();
			$("#microchip_no").val('${edit_animal_census_cmnd.microchip_no}');
			$("#tos").val(ParseDateColumn('${edit_animal_census_cmnd.date_of_tos}'));
			$("#date_admitted").val(ParseDateColumn('${edit_animal_census_cmnd.date_of_admitted}'));
		
			var r =('${roleAccess}');
			if( r == "Unit"){
				
				 $("#unit_sus_no_hid").show();
				 $("#unit_name_l").show();
				 
			}
			else if(r == "MISO")  {
				
				 $("input#sus_no").show();		 
				 $("input#unit_name").show();
				
			}
			
		   var sus_nop = '${roleSusNo}';
		  	$.post("getTargetUnitNameList?"+key+"="+value, {sus_nop:sus_nop}).done(function(data) {
		  		var l=data.length-1;
		  		var enc = data[l].substring(0,16);    	   	 
		  	 	$("#unit_name").text(dec(enc,data[0]));
		  		}).fail(function(xhr, textStatus, errorThrown) {
			  });
			
			$("#image_anml").change(function(){	
				checkFileExtImage(this);
			});
			
			
			 if('${anml_type8}' == "Army Dog")
				{
				   document.getElementById(anml_type1.id).checked = true;
				   
				    $('#textboxes').show();
					$('#textboxes3').show();
					$('#textboxes4').show();
					$('#source').show();
					$('#textboxes10').show();
					$('#otherdetails').show();
					$('#textboxes1').hide();
					$('#deplmnt').show();
					$('#armydog1').show();
					$('#armyequines1').hide();
					$('#date_admitted1').hide();
				} 
			 
				
		});
		</script>

<script>

function handleInput(input) {
   
    input.value = input.value.toUpperCase();

    const regex = /[^A-Z0-9]/g; // Allow only uppercase letters, numbers

    input.value = input.value.replace(regex, '');
}
</script>

<script>

function army_no_already_exist(){
	
	var army_num = $("#army_num").val();
	$.post("checkDetailsOfanimaldogarmy?" + key + "=" + value, {
		army_num : army_num
	}).done(function(j) {
					
 		 if(j.length > 0){
 			 
 			 alert("Army No already Exist.");
 			$("input#army_num").focus();
 			$("#army_num").val("");
 		 } 		 
	}).fail(function(xhr, textStatus, errorThrown) {}); 

}

</script>

<script>
function handleInput2(input) {

    input.value = input.value.toUpperCase();

    const regex = /[^A-Z0-9\s-]/g; // Allow only uppercase letters, numbers, and spaces

    input.value = input.value.replace(regex, '');
}
</script>
<script>
function  search_sus1from(sus_obj,unit_id){

	var sus_no = sus_obj.value;
	var susNoAuto = $("#"+sus_obj.id);
	susNoAuto.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
	        type: 'POST',
	        url: "getSUSNoList_Active_or_Inactive?"+key+"="+value,
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
	        	  $('#'+unit_id).val('');
	        	  susNoAuto.val("");	        	  
	        	  susNoAuto.focus();
	        	  return false;	             
	          }   	         
	    }, 
		select: function( event, ui ) {
			var sus_no = ui.item.value;			      	
			 $.post("getUnitNameList_Active_or_Inactive?"+key+"="+value, {
				 sus_no:sus_no
       }, function(j) {
              
       }).done(function(j) {
      	 var length = j.length-1;
           var enc = j[length].substring(0,16);
          
           $('#'+unit_id).val(dec(enc,j[0]));   
               
       }).fail(function(xhr, textStatus, errorThrown) {
       });
		} 	     
	});	
	
	
	}
function search_unit1_from(obj,id){

			var unit_name = obj.value;
				 var susNoAuto=$("#"+obj.id);
				  susNoAuto.autocomplete({
				      source: function( request, response ) {
				        $.ajax({
				        	type: 'POST',
					    	url: "UnitsNameList_active_or_inactive?"+key+"="+value,
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
				        	  $("#"+id).val('');
				        	  susNoAuto.val("");	        	  
				        	  susNoAuto.focus();
				        	
				        	  return false;	             
				          }   	         
				      }, 
				      select: function( event, ui ) {
					    	 var unit_name = ui.item.value;
						            $.post("SUSFromUNITNAMEActive_or_InactiveList?"+key+"="+value,{target_unit_name:unit_name}, function(data) {
						            }).done(function(data) {
						            	var length = data.length-1;
							        	var enc = data[length].substring(0,16);
							        	$("#"+id).val(dec(enc,data[0]));
						            }).fail(function(xhr, textStatus, errorThrown) {
						            });
					      } 	     
				  	     
				}); 			
		
}
</script>


<script>
function getPersonnel_no(obj,id) {
	
	roleSus=$("#"+id).val();		
	if(roleSus!=''){
	var personel_no = obj.value;
	var susNoAuto = $("#"+obj.id);
	var perurl;
	perurl='getpersonnel_noListApproved_JCO?';
	susNoAuto.autocomplete({
		source : function(request, response) {
			$.ajax({
				type : 'POST',
				url : perurl + key + "=" + value,
				data : {
					personel_no : personel_no,army_no:personel_no,roleSus:roleSus
				},
				success : function(data) {
					var susval = [];
					var length = data.length - 1;
					var enc = data[length].substring(0, 16);
					for (var i = 0; i < data.length; i++) {
						susval.push(dec(enc, data[i]));
					}

					response(susval);
				}
			});
		},
		minLength : 1,
		autoFill : true,
		change : function(event, ui) {
			if (ui.item) {
				return true;
			} else {
				alert("Please Enter Approved Army No");
				susNoAuto.val("");
				susNoAuto.focus();
				return false;
			}
		}
	});
	}
	else{
		alert("please Enter From Sus");			
		$("#"+id).focus();
		$("#"+id).val('');
		}
	}
function personal_number() {
		fn_getOfficerData();		
	}
function fn_getOfficerData(){	

	var personnel_no = $("input#personnel_no").val();
	if (personnel_no != "") {		 	
		var	perurl='GetArmyNoData?';							
		$.post(perurl + key + "=" + value, {army_no:personnel_no}, function(j) {
			
			var jco_id = j[0][0];
			var	 cpurl='GetJcoOrCensusDataApprove?';
			$("#jco_id").val(jco_id);
				
			
			$.post('CheckRole_Hdr_Status_Jco?' + key + "=" + value,{ jco_id : jco_id },function(rstatus) {
				if(rstatus.pass=="1"){
			$.post(cpurl + key + "=" + value,{ jco_id : jco_id},function(k) {
				 if(k.length > 0)
	    		 {		
					if(parseInt(k[0].update_jco_status) != parseInt(1))	
						{								
							alert("Individual Record is still in Pending for Approval.Pl Notify the Approval to Approve all Pending Records in Update Offcr Form.");
							$("input#personnel_no").val("");
							$("#name").text("");
							$("#rank").text("");
							
						}
						else
						{								
		    			  	$("#name").text(k[0].full_name);
		    			   	$("#rank").text(k[0].rank);			    			 

		    		    	
		    		    	 
		    		    var sus_no =k[0].unit_sus_no;	    		
						if(sus_no!=null){
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
		    	                   
		    	                   $('#unit_name').text(dec(enc,j[0]));			    		    	                 
		    		        }).fail(function(xhr, textStatus, errorThrown) {
		    		    });
	    		      } 		    		    
					}
				  }	
	    		}		    		
	   		});
		   }
		else{
			alert(rstatus.error);
			$("input#personnel_no").val("");
			$("#name").text("");
			$("#rank").text("");
			
			return false;
			} 
		});
	});
}				
$("input#personnel_no").attr('readonly', false);
}
function fn_getOfficerData1(){	

	var personnel_no = $("input#personnel_no1").val();
	if (personnel_no != "") {		 	
		var	perurl='GetArmyNoData?';							
		$.post(perurl + key + "=" + value, {army_no:personnel_no}, function(j) {
			
			var jco_id = j[0][0];
			var	 cpurl='GetJcoOrCensusDataApprove?';
			$("#jco_id").val(jco_id);
				
			
			$.post('CheckRole_Hdr_Status_Jco?' + key + "=" + value,{ jco_id : jco_id },function(rstatus) {
				if(rstatus.pass=="1"){
			$.post(cpurl + key + "=" + value,{ jco_id : jco_id},function(k) {
				 if(k.length > 0)
	    		 {		
					if(parseInt(k[0].update_jco_status) != parseInt(1))	
						{								
							alert("Individual Record is still in Pending for Approval.Pl Notify the Approval to Approve all Pending Records in Update Offcr Form.");
							$("input#personnel_no").val("");
							$("#name1").text("");
							$("#rank1").text("");
							
						}
						else
						{								
		    			  	$("#name1").text(k[0].full_name);
		    			   	$("#rank1").text(k[0].rank);			    			 

		    		    	
		    		    	 
		    		    var sus_no =k[0].unit_sus_no;	    		
						if(sus_no!=null){
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
		    	                   
		    	                   $('#emp_unit').text(dec(enc,j[0]));			    		    	                 
		    		        }).fail(function(xhr, textStatus, errorThrown) {
		    		    });
	    		      } 		    		    
					}
				  }	
	    		}		    		
	   		});
		   }
		else{
			alert(rstatus.error);
			$("input#personnel_no1").val("");
			$("#name1").text("");
			$("#rank1").text("");
			
			return false;
			} 
		});
	});
}				
$("input#personnel_no").attr('readonly', false);
}
</script>
<script type="text/javascript">
document.getElementById('trainer_mobno').addEventListener('input', function () {
    let mobno = this.value;

   
    mobno = mobno.replace(/\D/g, '');
    if (mobno.length > 10) {
        mobno = mobno.slice(0, 10);
    }

   
    this.value = mobno;
});

document.getElementById('trainer_mobno').addEventListener('blur', function () {
    let mobno = this.value;

    if (mobno.length !== 10) {
        alert('Mobile number must be exactly 10 digits.');

    }
});

</script>
<script type="text/javascript">
function submitDogData() {
	
	 if ($("#ason_date").val() == "") {
	        alert("Please Enter the As on Date.");
	        return false;
	    } else {
	        var ason= $("#ason_date").val();
	    }
  
    if ($("#sus_no").val() == "") {
        alert("Please enter the SUS No.");
        return false;
    } else {
        var sus_no = $("#sus_no").val();
    }

    if ($("#unit_name").val() == "") {
        alert("Please enter the Unit Name.");
        return false;
    } else {
        var unit_name = $("#unit_name").val();
    }

    if ($("#army_num").val() == "") {
        alert("Please enter the Army No.");
        return false;
    } else {
        var army_num = $("#army_num").val();
    }

    if ($("#type_dog").val() == "0") {
        alert("Please select the Type of Dog.");
        return false;
    } else {
        var type_dog = $("#type_dog").val();
    }

    if ($("#name_of_dog").val() == "") {
        alert("Please enter the Name of Dog.");
        return false;
    } else {
        var name_of_dog = $("#name_of_dog").val();
    }

    if ($("#specialization").val() == "0") {
        alert("Please select the Specialization.");
        return false;
    } else {
        var specialization = $("#specialization").val();
    }

    if ($("#breed").val() == "0") {
        alert("Please select the Breed.");
        return false;
    } else {
        var breed = $("#breed").val();
    }

    if ($("#colour").val() == "0") {
        alert("Please select the Color.");
        return false;
    } else {
        var colour = $("#colour").val();
    }

    if ($("input[name='sex']:checked").length == 0) {
        alert("Please select the Sex.");
        return false;
    } else {
        var sex = $("input[name='sex']:checked").val();
    }

    if ($("#source").val() == "0") {
        alert("Please select the Source.");
        return false;
    } else {
        var source = $("#source").val();
    }

    if ($("#date_of_birth").val() == "") {
        alert("Please select the Date of Birth.");
        return false;
    } else {
        var date_of_birth = $("#date_of_birth").val();
    }

    if ($("#microchip_no").val() == "") {
        alert("Please enter the Microchip No.");
        return false;
    } else {
        var microchip_no = $("#microchip_no").val();
    }

    if ($("#tos").val() == "") {
        alert("Please select the TOS.");
        return false;
    } else {
        var tos = $("#tos").val();
    }    

    // Send data via AJAX
  jQuery.ajax({
        type: "POST",
        url: "saveDogData?"+ key + "=" + value, 
        data: data = {
        		ason: ason,
                sus_no: sus_no,
                unit_name: unit_name,
                army_num: army_num,
                type_dog: type_dog,
                name_of_dog: name_of_dog,
                specialization: specialization,
                breed: breed,
                colour: colour,
                sex: sex,
                source: source,
                date_of_birth: date_of_birth,
                microchip_no: microchip_no,
                tos: tos
            },
        success: function(response) {
            if (response === "Data Updated Successfully.") {
                alert("Data Submited Successfully.");
               
            } else {
                alert(response);  
            }
        },
        error: function(xhr, status, error) {
            console.error("AJAX request failed:", status, error);
            alert("An error occurred while saving the data.");
        }
    });

    return false;
}

</script>
<script type="text/javascript">
function submitDepData() {
	
	 if ($("#ason_date").val() == "") {
	        alert("Please Enter the As on Date.");
	        return false;
	    } else {
	        var ason= $("#ason_date").val();
	    }
 
   if ($("#sus_no").val() == "") {
       alert("Please enter the SUS No.");
       return false;
   } else {
       var sus_no = $("#sus_no").val();
   }

   if ($("#unit_name").val() == "") {
       alert("Please enter the Unit Name.");
       return false;
   } else {
       var unit_name = $("#unit_name").val();
   }

   if ($("#army_num").val() == "") {
       alert("Please enter the Army No.");
       return false;
   } else {
       var army_num = $("#army_num").val();
   }
 
 //Employment
   var fitness_status = $("#fitness_status").val();
   if ($("#fitness_status").val() == "FIT") {
       if ($("#date_admitted").val() == "") {
           alert("Please enter the Date Admitted.");
           return false;
       } else {
           var date_admitted = $("#date_admitted").val();
       }
   }
   var employment = $("#Employment").val();
   var empsus = $("#emp_sus").val();
   var empno = $("#personnel_no1").val();
   var empname = $("#name1").text(); 
   var loc = $("#dog_loc").val();
   var remarks = $("#depremarks").val();
   var date_unfit = $("#date_unfit").val();
  
   // Send data via AJAX
 jQuery.ajax({
       type: "POST",
       url: "saveDepData?"+ key + "=" + value, 
       data: data = {
       		ason: ason,
               sus_no: sus_no,
               unit_name: unit_name,
               army_num: army_num,
               fitness_status: fitness_status,
               date_admitted: date_admitted || null,
               employment: employment || 0,
               empsus: empsus,
               empno: empno,
               empname: empname || null,
               loc: loc || null,
               remarks: remarks || null,
               date_unfit: date_unfit || null
           },
       success: function(response) {
           if (response === "Data Updated Successfully.") {
               alert("Deployment Data Updated Successfully.");
              
           } else {
               alert(response);  
           }
       },
       error: function(xhr, status, error) {
           console.error("AJAX request failed:", status, error);
           alert("An error occurred while saving the data.");
       }
   });

   return false;
}
</script>
<script type="text/javascript">
function submitUtData() {
    var formData = new FormData(); 
    
    if ($("#ason_date").val() == "") {
        alert("Please Enter the As on Date.");
        return false;
    } else {
    	formData.append("ason", $("#ason_date").val());
    }
    formData.append("sus_no", $("#sus_no").val());
    formData.append("unit_name", $("#unit_name").val());
    formData.append("army_num", $("#army_num").val());
    formData.append("rop", $("#total_ropED").val() || $("#total_ropMD").val() || $("#total_ropTR").val() || 0);
    formData.append("veh", $("#no_of_vehED").val() || $("#no_of_vehMD").val() || 0);
    formData.append("sanitisation", $("#sanitisation_dutyED").val() || $("#sanitisation_dutyMD").val() || 0);
    formData.append("vduties", $("#v_dutiesED").val() || $("#v_dutiesND").val() || 0);
    formData.append("explosive", $("#detected_explosiveED").val() || $("#detected_explosiveMD").val() || 0);
    formData.append("article", $("#no_of_articleED").val() || $("#no_of_articleMD").val() || $("#no_of_articleND").val() || 0);
    formData.append("hideEnemy", $("#hideout_EnemyED").val() || $("#hideout_EnemyMD").val() || $("#hideout_EnemyTR").val() || 0);
    formData.append("arms", $("#no_of_armsED").val() || $("#no_of_armsMD").val() || $("#no_of_armsTR").val() || 0);
    formData.append("shbo", $("#no_of_shboED").val() || $("#no_of_shboMD").val() || $("#no_of_shboGD").val() || $("#no_of_shboIP").val() ||$("#no_of_shboSAR").val() || $("#no_of_shboAR").val() || $("#no_of_shboAS").val() || $("#no_of_shboND").val() || $("#no_of_shboTR").val() || 0);
    formData.append("reftrg", $("#no_of_reftrgED").val() || $("#no_of_reftrgMD").val() || $("#no_of_reftrgGD").val() || $("#no_of_reftrgIP").val() ||$("#no_of_reftrgSAR").val() || $("#no_of_reftrgAR").val() || $("#no_of_reftrgAS").val() || $("#no_of_reftrgND").val() || $("#no_of_reftrgTR").val() || 0);
    formData.append("ptrkm", $("#ptr_kmED").val() || $("#ptr_kmMD").val() || $("#ptr_kmGD").val() || $("#ptr_kmIP").val() || $("#ptr_kmAS").val() || $("#ptr_kmTR").val() || 0);
    formData.append("remakrs", $("#remarksED").val() || $("#remarksMD").val() || $("#remarksGD").val() || $("#remarksIP").val() ||$("#remarksSAR").val() || $("#remarksAR").val() || $("#remarksAS").val() || $("#remarksND").val() || $("#remarksTR").val() || 0);
    formData.append("mine", $("#detected_mine").val() || 0);
    formData.append("areakms", $("#area_kmsGD").val() || $("#area_kmsAS").val() || 0);
    formData.append("guard", $("#total_guard").val() || 0);
    formData.append("debris", $("#detected_debris").val() || 0);
    formData.append("avalnches", $("#detected_avalnches").val() || 0);
    formData.append("room", $("#ops_room").val() || 0);
    formData.append("killed", $("#ops_killed").val() || 0);
    formData.append("apprehended", $("#ops_apprehended").val() || 0);
    formData.append("escape", $("#detected_escape").val() || 0);
    formData.append("esitrepnum", $("#esitrep_num").val());
    formData.append("esitrepstatus",  $("input[name='esitrep_status']:checked").val());
    formData.append("uploadfile", $("#uploadfile")[0].files[0]); 

    // Send data via AJAX
    jQuery.ajax({
        type: "POST",
        url: "saveUtData?" + key + "=" + value,
        data: formData,
        processData: false, 
        contentType: false, 
        success: function(response) {
            if (response === "Data Updated Successfully.") {
                alert("Utilization Data Updated Successfully.");
            } else {
                alert(response);  
            }
        },
        error: function(xhr, status, error) {
            console.error("AJAX request failed:", status, error);
            alert("An error occurred while saving the data.");
        }
    });

    return false;
}
</script>
<script type="text/javascript">
function submitHospData() {
	
	 if ($("#ason_date").val() == "") {
	        alert("Please Enter the As on Date.");
	        return false;
	    } else {
	        var ason= $("#ason_date").val();
	    }
 
   if ($("#sus_no").val() == "") {
       alert("Please enter the SUS No.");
       return false;
   } else {
       var sus_no = $("#sus_no").val();
   }

   if ($("#unit_name").val() == "") {
       alert("Please enter the Unit Name.");
       return false;
   } else {
       var unit_name = $("#unit_name").val();
   }

   if ($("#army_num").val() == "") {
       alert("Please enter the Army No.");
       return false;
   } else {
       var army_num = $("#army_num").val();
   }
   
   //H&M
   var hospname = $("#hosp_name").val();
   var hemoglobin = $("#hemoglobin").val();
   var weight = $("#weight").val();
   var tlc = $("#tlc").val();
   var dlc = $("#dlc").val();
   var trbc = $("#trbc").val();
   var pcv = $("#pcv").val();
   var platelet = $("#platelet").val();
   var sgot = $("#sgot").val();
   var bill = $("#bill").val();
   var urine = $("#urine").val();
   var protein = $("#protein").val();
   var creatinine = $("#creatinine").val();
   var albun = $("#albun").val();
   var agr = $("#agr").val();
   var bun = $("#bun").val();
   var stool = $("#stool").val();
   var sstatus = $("input[name='stool_status']:checked").val();
   var ustatus = $("input[name='urine_status']:checked").val();
   if ($("#med_date").val() == "") {
       alert("Please Enter the Medical Report As on Date.");
       return false;
   } else {
       var medason= $("#med_date").val();
   }
   
   // Send data via AJAX
 jQuery.ajax({
       type: "POST",
       url: "saveHospData?"+ key + "=" + value, 
       data: data = {
       		ason: ason,
               sus_no: sus_no,
               unit_name: unit_name,
               army_num: army_num,
               hospname: hospname || 0,
               hemoglobin: hemoglobin || 0,
               weight: weight || 0,
               tlc: tlc || 0,
               dlc: dlc || 0,
               trbc: trbc || 0,
               pcv: pcv || 0,
               platelet: platelet || 0,
               sgot: sgot || 0,
               bill: bill || 0,
               urine: urine || null,
               protein: protein || 0,
               creatinine: creatinine || 0,
               albun: albun || 0,
               agr: agr || 0,
               bun: bun || 0,
               stool: stool || null,
               sstatus: sstatus || 0,
               ustatus: ustatus || 0,
               medason: medason
           },
       success: function(response) {
           if (response === "Data Updated Successfully.") {
               alert("Health & Medical Data Updated Successfully.");
              
           } else {
               alert(response);  
           }
       },
       error: function(xhr, status, error) {
           console.error("AJAX request failed:", status, error);
           alert("An error occurred while saving the data.");
       }
   });

   return false;
}
</script>
<script type="text/javascript">
function submitVcData() {
	
	 if ($("#ason_date").val() == "") {
	        alert("Please Enter the As on Date.");
	        return false;
	    } else {
	        var ason= $("#ason_date").val();
	    }
 
   if ($("#sus_no").val() == "") {
       alert("Please enter the SUS No.");
       return false;
   } else {
       var sus_no = $("#sus_no").val();
   }

   if ($("#unit_name").val() == "") {
       alert("Please enter the Unit Name.");
       return false;
   } else {
       var unit_name = $("#unit_name").val();
   }

   if ($("#army_num").val() == "") {
       alert("Please enter the Army No.");
       return false;
   } else {
       var army_num = $("#army_num").val();
   }

   //vaccine
   var vaccine = $("#vaccine").val();
   var astatus = $("input[name='allergy_status']:checked").val();
   var allergy = $("#allergy").val();
   
   // Send data via AJAX
 jQuery.ajax({
       type: "POST",
       url: "saveVcData?"+ key + "=" + value, 
       data: data = {
       		ason: ason,
               sus_no: sus_no,
               unit_name: unit_name,
               army_num: army_num,
               vaccine: vaccine || 0,
               astatus: astatus || 0,
               allergy: allergy || null
           },
       success: function(response) {
           if (response === "Data Updated Successfully.") {
               alert("Vaccine Data Updated Successfully.");
              
           } else {
               alert(response);  
           }
       },
       error: function(xhr, status, error) {
           console.error("AJAX request failed:", status, error);
           alert("An error occurred while saving the data.");
       }
   });

   return false;
}
</script>
<script type="text/javascript">
function submittrgData() {
	
	 if ($("#ason_date").val() == "") {
	        alert("Please Enter the As on Date.");
	        return false;
	    } else {
	        var ason= $("#ason_date").val();
	    }
 
   if ($("#sus_no").val() == "") {
       alert("Please enter the SUS No.");
       return false;
   } else {
       var sus_no = $("#sus_no").val();
   }

   if ($("#unit_name").val() == "") {
       alert("Please enter the Unit Name.");
       return false;
   } else {
       var unit_name = $("#unit_name").val();
   }

   if ($("#army_num").val() == "") {
       alert("Please enter the Army No.");
       return false;
   } else {
       var army_num = $("#army_num").val();
   }

   //Trainer Details
   var trainersus = $("#from_sus_no").val();
   var trainerunit = $("#traning_place").val();
   var trainerno = $("#personnel_no").val();
   var trainername = $("#name").text();
   var trainerrank = $("#rank").text();
   var performance = $("#performance_status").val();
   var trainermobno = $("#trainer_mobno").val();
   var trainingfromdate = $("#training_from_date").val();
   var trainingtodate = $("#training_to_date").val();
   var testersus = $("#valid_sus_no").val();
   var testerunit = $("#valid_unit_name").val();
   var testerno = $("#valid_pers_no").val();
   var testername = $("#v_name").text();
   var testerrank = $("#v_rank").text();
   var examstatus = $("#exam_status").val();
   var exmremarks = $("#exam_remarks").val();

   // Send data via AJAX
 jQuery.ajax({
       type: "POST",
       url: "saveTrgData?"+ key + "=" + value, 
       data: data = {
       		ason: ason,
               sus_no: sus_no,
               unit_name: unit_name,
               army_num: army_num,
               trainersus: trainersus,
               trainerunit: trainerunit || null,
               trainerno: trainerno || null,
               trainername: trainername || null,
               trainerrank: trainerrank || null,
               performance: performance || 0,
               trainermobno: trainermobno || 0,
               trainingfromdate: trainingfromdate || null,
               trainingtodate: trainingtodate || null,
               testersus: testersus,
               testerunit: testerunit || null,
               testerno: testerno || null,
               testername: testername || null,
               testerrank: testerrank || null,
               examstatus: examstatus || 0,
               exmremarks: exmremarks || null
           },
       success: function(response) {
           if (response === "Data Updated Successfully.") {
               alert("Trainer Data Updated Successfully.");
              
           } else {
               alert(response);  
           }
       },
       error: function(xhr, status, error) {
           console.error("AJAX request failed:", status, error);
           alert("An error occurred while saving the data.");
       }
   });

   return false;
}
</script>
<script type="text/javascript">
function submitDewData() {
	
	 if ($("#ason_date").val() == "") {
	        alert("Please Enter the As on Date.");
	        return false;
	    } else {
	        var ason= $("#ason_date").val();
	    }
 
   if ($("#sus_no").val() == "") {
       alert("Please enter the SUS No.");
       return false;
   } else {
       var sus_no = $("#sus_no").val();
   }

   if ($("#unit_name").val() == "") {
       alert("Please enter the Unit Name.");
       return false;
   } else {
       var unit_name = $("#unit_name").val();
   }

   if ($("#army_num").val() == "") {
       alert("Please enter the Army No.");
       return false;
   } else {
       var army_num = $("#army_num").val();
   }

   //Drug
   var drug = $("#used_drug").val();
   var astatus1 = $("input[name='allergy_status1']:checked").val();
   var allergy1 = $("#allergy13").val();

   // Send data via AJAX
 jQuery.ajax({
       type: "POST",
       url: "saveDewData?"+ key + "=" + value, 
       data: data = {
       		ason: ason,
               sus_no: sus_no,
               unit_name: unit_name,
               army_num: army_num,
               drug: drug || null,
               astatus1: astatus1 || 0,
               allergy1: allergy1 || null
           },
       success: function(response) {
           if (response === "Data Updated Successfully.") {
               alert("Drug Data Updated Successfully.");
              
           } else {
               alert(response);  
           }
       },
       error: function(xhr, status, error) {
           console.error("AJAX request failed:", status, error);
           alert("An error occurred while saving the data.");
       }
   });

   return false;
}
</script>
<script type="text/javascript">
function submitAwData() {
    var formData = new FormData(); 

    formData.append("ason", $("#ason_date").val());
    formData.append("sus_no", $("#sus_no").val());
    formData.append("unit_name", $("#unit_name").val());
    formData.append("army_num", $("#army_num").val());
    formData.append("award", $("#award").val() || 0);
    formData.append("remarks1", $("#remarks1").val() || null);
    formData.append("uploadfile", $("#uploadfile")[0].files[0]); 

    // Send data via AJAX
    jQuery.ajax({
        type: "POST",
        url: "saveAwData?" + key + "=" + value,
        data: formData,
        processData: false, 
        contentType: false, 
        success: function(response) {
            if (response === "Data Updated Successfully.") {
                alert("Award Data Updated Successfully.");
            } else {
                alert(response);  
            }
        },
        error: function(xhr, status, error) {
            console.error("AJAX request failed:", status, error);
            alert("An error occurred while saving the data.");
        }
    });

    return false;
}
</script>
<script type="text/javascript">
//emp_sus
$("#emp_sus").keyup(function(){
	var sus_no = this.value;
	var susNoAuto=$("#emp_sus");

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
	        	  document.getElementById("emp_unit").value="";
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
             $("#emp_unit").val(dec(enc,j[0]));   
                 
         }).fail(function(xhr, textStatus, errorThrown) {
         });
		} 	     
	});	
});

// Unit Unit Name
	$("#emp_unit").keyup(function(){
		var unit_name = this.value;
	var unit_nameAuto=$("#emp_unit");

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
	        	  document.getElementById("emp_sus").value="";
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
                 $("#emp_sus").val(dec(enc,j[0]));   
                     
             }).fail(function(xhr, textStatus, errorThrown) {
             });
	      } 	     
	}); 			
});

</script>
<script type="text/javascript">
function getPers_no(obj,id) {
	roleSus=$("#"+id).val();
	
	if(roleSus!=''){
	var personel_no = obj.value;
	var susNoAuto = $("#"+obj.id);
	var perurl;
		perurl='getpersonnel_noListApprovedcomm?';
	
	susNoAuto.autocomplete({
		source : function(request, response) {
			$.ajax({
				type : 'POST',
				url : perurl + key + "=" + value,
				data : {
					personel_no : personel_no,army_no:personel_no,roleSus:roleSus
				},
				success : function(data) {
					var susval = [];
					var length = data.length - 1;
					var enc = data[length].substring(0, 16);
					for (var i = 0; i < data.length; i++) {
						susval.push(dec(enc, data[i]));
					}

					response(susval);
				}
			});
		},
		minLength : 1,
		autoFill : true,
		change : function(event, ui) {
			if (ui.item) {
				return true;
			} else {
				alert("Please Enter Approved Personal No");
				;
				susNoAuto.val("");
				susNoAuto.focus();
				return false;
			}
		},
		select : function(event, ui) {

		}
	});
	}
	else{
		alert("please Enter Validator SUS No.");
		
		$("#"+id).focus();
		$("#"+id).val('');
		}
	}
function validpers_number(){
	 
	var personnel_no = $("input#valid_pers_no").val();
	if (personnel_no != "") {
		 

		var	perurl='GetPersonnelNoData?';
	
		
	
		$.post(perurl + key + "=" + value, {
			personnel_no : personnel_no
		}, function(j) {

			var comm_id = j[0][0];
			var	 cpurl='GetCommDataApprove?';
		
			$.post(cpurl + key + "=" + value,{ comm_id : comm_id},function(k) {
				
	    		 if(k.length > 0 )
	    		 {
					
				
						 if(k[0][18] != "1")	
						{
							
							alert("Individual Record is still in Pending for Approval.Pl Notify the Approval to Approve all Pending Records in Update Offcr Form.");
							$("input#valid_pers_no").val("");
							$("#v_name").text("");
							$("#v_rank").text("");
							
						}
						else
						{ 
							
		    			  	$("#v_name").text(k[0][1]);
		    			   	$("#v_rank").text(k[0][3]);
	
		    		    
		    		    var sus_no =k[0][7];
		    		    

					if(sus_no!=null){
						getunit(sus_no);
					
	    		    	function getunit(val) {	
	    		            $.post("getTargetUnitNameList?"+key+"="+value, {
	    		            	sus_no : sus_no
	    		        }, function(j) {
	    		                

	    		        }).done(function(j) {
	    		    				   var length = j.length-1;
	    		    	                   var enc = j[length].substring(0,16); 
	    		    	                 
	    		    	                   $("#valid_unit_name").text(dec(enc,j[0])); 
	    		    	                   
	    		    	                 
	    		        }).fail(function(xhr, textStatus, errorThrown) {
	    		        });
	    		    } 
	    		    
					}
					 }	
	    		 }

	      		
	   });
				
		});
	}
	
	
	$("input#personnel_no").attr('readonly', false);
}
</script>
<script type="text/javascript">
var file = document.getElementById('uploadfile');
file.onchange = function(e) {
  	var ext = this.value.match(/\.([^\.]+)$/)[1];
  	var FileSize = file.files[0].size /1024/1024; // in MB
  	if (FileSize > 2) {
       alert('File size exceeds 2 MB');
       this.value = '';
    } else {
    	switch (ext) {
		  	case 'rar':
		  	case 'zip':
		 	alert('Allowed');
		    break;
		  	default:
		    	alert('Please Only Allowed *.zip , *.rar File Extensions.');
		   	this.value = '';
		}
    }
};
</script>
<c:url value="getBackADCRReport" var="backUrl" />
	<form:form action="${backUrl}" method="post" id="backForm" name="backForm" modelAttribute="viewStatus1">
	      <input type="hidden" name="sus_no1" id="sus_no1" />
    <input type="hidden" name="unit_name1" id="unit_name1" />
	</form:form>
<script type="text/javascript">
function goBack() {
	var unitname = $("#unit_name").val();
	document.getElementById('sus_no1').value='${edit_animal_census_cmnd.unit_sus_no}';
	document.getElementById('unit_name1').value= unitname ;
	document.getElementById('backForm').submit();
}
</script>
