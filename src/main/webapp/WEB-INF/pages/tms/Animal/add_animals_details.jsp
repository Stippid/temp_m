	
	
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
	<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
	<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
	<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 
	
	<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
	
	<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
	
	<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></links>
	<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
    <script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>


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
	
<form:form name="add_animal_details" id="add_animal_details"  action="add_animal_details_act?${_csrf.parameterName}=${_csrf.token}" method='POST' commandName="add_animal_details_cmnd" enctype="multipart/form-data">
	<div class="container" align="center">
		<div class="card">
			<div class="card-header">
				<strong><u>ANIMALS</u></strong>
				<strong><h3>ADD ANIMAL DETAILS </h3></strong>
			</div>
			<div class="card-body card-block">
				<div class="col-md-12">
					<div class="col-md-6">
              					<div class="row form-group">
	                					<div class="col col-md-6">	                					
						                	<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Select Animal Type</label>
						                </div>
			                                        <div class="form-check-inline form-check">
			                                          <label for="inline-radio1" class="form-check-label ">
			                                            <input type="radio" id="anml_type1" name="anml_type" value="Army Dog" class="form-check-input" onchange="radioch();">Army Dogs
			                                          </label>&nbsp;&nbsp;
			                                          <label for="inline-radio2" class="form-check-label ">
			                                            <input type="radio" id="anml_type2" name="anml_type" value="Army Equines" class="form-check-input" onchange="radioch();" > Army Equines
			                                          </label>
			                                        </div>
	                				    </div>
	                			  </div>
	                	   </div> 		  
				</div>
				<div class="card" id="textboxes" style="display: none; margin-bottom: 0; border: 0;">
					<div class="card-header">
						<strong>GENERAL</strong>
					</div>
					<div class="card-body card-block">
	
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> SUS No</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="sus_no" name="sus_no" class="form-control" autocomplete="off" maxlength="8">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Unit Name</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="unit_name" name="unit_name" class="form-control" autocomplete="off" maxlength="100">
									</div>
								</div>
							</div>
						</div>
	
						<div id="textboxes3">
							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col col-md-4">
											<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Army No</label>
										</div>
										<div class="col-12 col-md-8">
											<input type="text" id="army_num" name="army_num" autocomplete="off" class="form-control" onchange="DetailsOFSire();" maxlength="20">
											<input type="hidden" id="hidearmynumno">
										</div>
									</div>
								</div>
	
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col col-md-4">
											<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Type of Dog</label>
										</div>
										<div class="col-12 col-md-8">
											<select id="type_dog" name="type_dog" class="form-control">
											<option value="0">--Select--</option>
											<c:forEach var="item" items="${getTypeOfDogList}" varStatus="num" >
												<option value="${item[1]}" >${item[0]}</option>
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
											<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Name of Dog</label>
										</div>
										<div class="col-12 col-md-8">
											<input type="text" id="name_of_dog" name="name_of_dog" autocomplete="off" class="form-control" onchange="DetailsOFSire();" maxlength="35">
										</div>
									</div>
								</div>
								
								<div class="col-md-6">
										<div class="row form-group">
											<div class="col col-md-4">
												<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Specialisation</label>
											</div>
											<div class="col-12 col-md-8">
												<select id="specialization" name="specialization" class="form-control">
												       <option value="0">--Select--</option>	
															<c:forEach var="item" items="${getsplzList}" varStatus="num" >
																<option value="${item[1]}" >${item[0]}</option>
															</c:forEach>
												</select> 
											</div>	
										</div>
									</div>
							</div>
						</div>
						<div id="textboxes1" style="display: none;">
							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col col-md-4">
											<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Remount/Neck No </label>
										</div>
										<div class="col-12 col-md-8">
											<input type="text" id="remount_no" name="remount_no" autocomplete="off" class="form-control" maxlength="8">
											<input type="hidden" id="hideremountno">
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col col-md-4">
											<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Type</label>
										</div>
										<div class="col-12 col-md-8">
											<select id="type_equines1" name="type_equines1"  class="form-control">
											<option value="0">--Select--</option>
											<c:forEach var="item" items="${getAnimalTypeList}" varStatus="num" >
												<option value="${item[1]}" >${item[0]}</option>
											</c:forEach>
											</select> 
											
										</div>
									</div>
								</div>
							</div>
						</div>
					<!-- end of textboox1 -->
					
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Breed</label>
									</div>
									<div class="col-12 col-md-8">
										<select id="breed" name="breed" class="form-control">
										<option value="0">--Select--</option>
											<c:forEach var="item" items="${getbreedList}" varStatus="num" >
												<option value="${item[1]}" >${item[0]}</option>
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
											<c:forEach var="item" items="${getclrList}" varStatus="num" >
												<option value="${item[1]}" >${item[0]}</option>
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
									
									<div class="col-12 col-md-8" id="armydog1" style="display: none;">
										<div class="form-check-inline form-check">
											<label for="inline-radio1"  class="form-check-label ">
												<input type="radio" id="male" name="sex" value="MALE" class="form-check-input form-control">Male</label>&nbsp;&nbsp; 
											<label for="inline-radio2" class="form-check-label ">
												 <input type="radio" id="female" name="sex" value="FEMALE" class="form-check-input form-control">Female</label>&nbsp;&nbsp;										
											<label for="inline-radio3" class="form-check-label ">
												<input type="radio" id="others" name="sex" value="OTHERS" class="form-check-input form-control">Others</label>&nbsp;&nbsp;											
										</div>
									</div>
									
									<div class="col-12 col-md-8" id="armyequines1" style="display: none;">
										<div class="form-check-inline form-check">											
											<label for="inline-radio1" class="form-check-label ">
												<input type="radio" id="entire" name="sex" value="ENTIRE" class="form-check-input form-control">Entire</label>&nbsp;&nbsp;
											<label for="inline-radio2" class="form-check-label ">
												<input type="radio" id="mare" name="sex" value="MARE" class="form-check-input form-control">Mare</label>&nbsp;&nbsp;
											 <label for="inline-radio3" class="form-check-label ">
												<input type="radio" id="geld" name="sex" value="GELD" class="form-check-input form-control">Geld</label>&nbsp;&nbsp;
											<label for="inline-radio4" class="form-check-label ">
												<input type="radio" id="geld" name="sex" value="RIG" class="form-check-input form-control">Rig</label>&nbsp;&nbsp;																				
											<label for="inline-radio5" class="form-check-label ">
												<input type="radio" id="others" name="sex" value="OTHERS" class="form-check-input form-control">Others</label>
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
											<c:forEach var="item" items="${getSourceList}" varStatus="num" >
												<option value="${item[1]}" >${item[0]}</option>
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
										<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Date of Birth</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="date" id="date_of_birth" name="date_of_birth" class="form-control" onchange="calculate_age();"  min='1899-01-01' max='${date}' maxlength="10">
									    <input type="hidden" id="date_of_death" name="date_of_death" class="form-control" min='1899-01-01' max='${date}' maxlength="10">
									</div>
								</div>
							</div>
							
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label">Age(YY)</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="age" name="age" value="0" class="form-control" readonly="readonly" maxlength="2">
									</div>
								</div>
							</div>
						</div>
	
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label">Details of Sire</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="details_of_sire" name="details_of_sire" class="form-control" autocomplete="off" maxlength="35"> 
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label">Details of Dam</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="details_of_dam" name="details_of_dam" class="form-control" autocomplete="off" maxlength="35">
									</div>
								</div>
							</div>
						</div>
						
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label">Microchip No</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="microchip_no" name="microchip_no" autocomplete="off" class="form-control" maxlength="20">
										<input type="hidden" id="month" name="month" value = "0" autocomplete="off" class="form-control" >
										<input type="hidden" id="year" name="year"  value = "0" autocomplete="off" class="form-control" >
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label">Photograph</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="file" id="image_anml" autocomplete="off" name="image_anml" class="form-control">
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
										<select id="fitness_status" name="fitness_status" class="form-control" onclick="chkFitnessStatus(this);">
										<option value="0">--Select--</option>
											<c:forEach var="item" items="${getFitnessStatusList}" varStatus="num" >
												<option value="${item[1]}" >${item[0]}</option>
											</c:forEach>
										</select>
									</div>
								</div>
							</div>
							
							<div class="col-md-6" id="date_admitted1" style="display: none;">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label">Date Admitted</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="date" id="date_admitted" name="date_admitted" class="form-control" min='1899-01-01' max='${date}' maxlength="10">
									</div>
								</div>
							</div>
							
						</div>
						
						<div class="col-md-12">			
							
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label">Fitness Deployment Details</label>
									</div>
									<div class="col-12 col-md-8">
											<input type="text" id="fitness_deployment" name="fitness_deployment" class="form-control" autocomplete ="off" maxlength="255">
									</div>
								</div>
							</div>
						</div>
	
	
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label"> Animal Purchase Cost(&#8377)</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="animal_purchase_cost"  name="animal_purchase_cost"  class="form-control" autocomplete ="off" placeholder="NUMERIC" onkeypress="return validateNumber(event, this)" maxlength="10"> 
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label">Book Value(&#8377)</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="animal_present_cost" name="animal_present_cost" class="form-control" autocomplete ="off" placeholder="NUMERIC" onkeypress="return validateNumber(event, this)" maxlength="10">
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
											<label for="inline-radio1"  class="form-check-label ">
												<input type="radio" id="disp" name="disptrans" value="disp" class="form-check-input form-control"  onclick="chkDisposal(this);">Disposal</label>&nbsp;&nbsp; 
											<label for="inline-radio2" class="form-check-label ">
												 <input type="radio" id="issue" name="disptrans" value="issue" class="form-check-input form-control" onclick="chkDisposal(this);">Issue To Unit</label>&nbsp;&nbsp;										
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
												<label for="text-input" class=" form-control-label">Issue To Unit  </label>
											</div>
											<div class="col-12 col-md-8">
												<input type="text" id="issue_to_unit_name" name="issue_to_unit_name" autocomplete="off" class="form-control" maxlength="100">
											</div>
										</div>
									</div>
								</div>	
									
					<div class="col-md-12" id = "disposal_date" style="display: none;">			
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label">Disposal Date</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="date" id="dis_date" name="dis_date" class="form-control" min='1899-01-01' max='${date}' maxlength="10">
									</div>
								</div>
							</div>
						</div>	 
						
										
						<div class="col-md-12" id = "issue_date" style="display: none;">			
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label">Issue Date</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="date" id="issue_date" name="issue_date" class="form-control" min='1899-01-01' max='${date}' maxlength="10">
									</div>
								</div>
							</div>
						</div>	  
									
									<div class="col-md-12" id="dispremark" style="display: none;">
									<div class="col-md-6" >
										<div class="row form-group">
											<div class="col col-md-4">
												<label for="text-input" class=" form-control-label">Disposal Remarks</label>
											</div>
											<div class="col-12 col-md-8">
												<textarea raws="2" id="disposal_remarks" name="disposal_remarks" class="form-control" maxlength="255"></textarea>
											</div>
										</div>
									</div>
								</div>
							
					</div>
					<!-- end of general -->			
				
					<div class="card-header">
						<strong>MEDALS/DECORATIONS</strong>
					</div>
					<div class="card-body card-block">
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label">Name of Award</label>
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
										<label for="text-input" class=" form-control-label">Unit Where Awarded</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="unit_where_awarded" autocomplete="off" name="unit_where_awarded" class="form-control" maxlength="100">
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
										<input type="text" id="authority" name="authority" autocomplete="off" class="form-control" maxlength="50">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label">Award Date</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="date" id="award_date" name="award_date" class="form-control" min='1899-01-01' max='${date}' maxlength="10">
									</div>
								</div>
							</div>
						</div>
						
						  <div class="col-md-12" id="remarks" style="display: none;">
							<div class="col-md-6" >
										<div class="row form-group">
											<div class="col col-md-4">
												<label for="text-input" class=" form-control-label">Awarded Remarks</label>
											</div>
											<div class="col-12 col-md-8">
												<textarea raws="2" id="awared_remarks" name="awared_remarks" class="form-control" maxlength="255"></textarea>
											</div>
										</div>
									</div>
							  </div>	
					</div>
				</div>
				<!-- end of textbox -->
	
	
				<div class="card" id="otherdetails"
					style="display: none; margin-bottom: 0; border: 0;">
					<div class="card-header">
						<strong>OTHER DETAILS</strong>
					</div>
					<div class="card-body card-block">
	
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label">TOS</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="date" id="tos" name="tos" class="form-control" autocomplete="off" min='1899-01-01' max='${date}' maxlength="10">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label">TORS</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="date" id="tors" name="tors" class="form-control" autocomplete="off" min='1899-01-01' max='${date}' maxlength="10">
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
										<input type="date" id="sos" name="sos" class="form-control" autocomplete="off" min='1899-01-01' max='${date}' maxlength="10">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label">SORS</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="date" id="sors" name="sors" class="form-control" autocomplete="off" min='1899-01-01' max='${date}' maxlength="10">
									</div>
								</div>
							</div>
	
						</div>
					</div>
				</div>
	
				<!-- end of other details -->
	
				<div class="form-control card-footer" align="center">
					<input type="reset" class="btn btn-success btn-sm" value="Clear">
					<input type="submit" class="btn btn-primary btn-sm" value="Save" onclick="return Validate();"> 
				</div>
				<!-- end of card-footer -->
	
			</div>
			<!-- end of card -->
		</div>
		<!-- end of container -->
	</form:form>
	
	<script>
	

		$("#medals_desc_details").change(function() {
			var end = this.value;
			if (end == 'OTHERS') {
	
				$("#remarks").show();
	
			} else {
	
				$("#remarks").hide();
			}
		});
		
		$("#disposal").change(function() {
			var end = this.value;
			
			
			if (end == 'OTHERS') {
	
				$("#dispremark").show();
	
			} else {
				$("#dispremark").hide();
			}
		});
		
		
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
			
				} else {
					anml_type="Army Equines";
					$('#textboxes').show();
					$('#textboxes1').show();
					$('#source').show();
					$('#textboxes3').hide();
					$('#textboxes4').hide();
					$('#textboxes10').show();
					$('#otherdetails').show();
					$('#deplmnt').hide();
					$('#armydog1').hide();
					$('#armyequines1').show();
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
			
			if ($("input#sus_no").val() == "") {
				alert("Please Select SUS No");
				$("input#sus_no").focus();
				return false;
			}
			if ($("input#unit_name").val() == "") {
				alert("Please Select Unit Name");
				$("input#unit_name").focus();
				return false;
			}
			if(anml_type == "Army Dog"){
				
				var army_num = document.getElementById("army_num").value;
				
				if(army_num == ""){
					alert("Please Select Army No");
					$("input#army_num").focus();
					return false;
				}
			$.ajaxSetup({
					async : false
				});
			
	                    $.post("checkDetailsOfanmlarmy?"+key+"="+value, {
	                    	army_num : army_num
	                }, function(j) {
	                        //        alert(data);
	                        //var json = JSON.parse(data);
	                        //...

	                }).done(function(j) {
	                       // alert(j);
	                
	                        document.getElementById("hidearmynumno").value = j;
	                        
	                }).fail(function(xhr, textStatus, errorThrown) {
	                });
		
				if (document.getElementById("hidearmynumno").value != "") {
					alert("Army Number is Already Exist");
					$("input#army_num").focus();
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
			
			if(anml_type == "Army Equines"){
				var remount_no = document.getElementById("remount_no").value;
						
				if(remount_no == ""){
					alert("Please Enter Remount No");
					$("input#remount_no").focus();
					return false;
				}
				
				$.ajaxSetup({
					async : false
				});
			
	                    $.post("checkDetailsOfanmlremount?"+key+"="+value, {
	                    	remount_no : remount_no
	                }, function(j) {
	                        //        alert(data);
	                        //var json = JSON.parse(data);
	                        //...

	                }).done(function(j) {
	                       // alert(j);
	                
	                        document.getElementById("hideremountno").value = j;
	                        
	                }).fail(function(xhr, textStatus, errorThrown) {
	                });
		
				if (document.getElementById("hideremountno").value != "") {
					alert("Remount Number is Already Exist");
					$("input#remount_no").focus();
					return false;
				}
				
				if ($("select#type_equines1").val() == "0") {
					alert("Please Select Type of Equines");
					$("select#type_equines1").focus();
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
			
				if ($("input#animal_purchase_cost").val() == "" && $("input#animal_present_cost").val() == "") {
					alert("Please Enter Either Animal Purchase Cost or Book Value ");
					return false;
				}
				
				if ($("input#animal_purchase_cost").val()!== "" && $("input#animal_present_cost").val()!== "") {
					alert("Please Enter Either Animal Purchase Cost or Book Value ");
					return false;
				}
			 return true;
		}    
		 
		
		function chkFitnessStatus(obj){
			
			if(obj.value=='3'){
				$('#date_admitted1').show();
			}
			else if(obj.value=='5'){
				$('#date_admitted1').show();
			}
			else if(obj.value=='2'){
				$('#date_admitted1').hide();
			}
			else if(obj.value=='4'){
				$('#date_admitted1').hide();
			}
		}
		
		function DetailsOFSire(){
			var army_num = $("input#army_num").val();
			var name_of_dog = $("input#name_of_dog").val();
			
			if(army_num != "" && name_of_dog != ""){
				var dtofsire = army_num +' '+ name_of_dog+' ';
				$("input#details_of_sire").val(dtofsire);
				$("input#details_of_dam").val(dtofsire);
			}
		}
		
		function chkDisposal(obj){
			if(document.getElementById(obj.id).checked == true){
				
				if(obj.id == "issue")
					{
						$('#isuue_to_unit1').show();
						$('#disposal1').hide();
						$('#dispremark').hide();
						$('#disposal_date').hide();
						$('#issue_date').show();
					}
				else
					{
						$('#disposal1').show();
						$('#isuue_to_unit1').hide();
						$('#disposal_date').show();
						$('#issue_date').hide();
					}
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
	  
	 <Script>
	  

	
function radioch(){
		var anml_type1 = $('input[name=anml_type]:checked').val(); 
		$("#anml_type8").val(anml_type1);
        document.getElementById('searchForm').submit();
	}
	  </Script>
	  
	  
	  	<c:url value="add_animal_details1" var="searchUrl" />
		<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="anml_type8">
			<input type="hidden" name="anml_type8" id="anml_type8" value="0"/>
		</form:form> 
		
		
		<script>
		
		$(document).ready(function() {
			
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
			 
			 if('${anml_type8}' == "Army Equines")
				{
				 
				 document.getElementById(anml_type2.id).checked = true;
				 
					$('#textboxes').show();
					$('#textboxes1').show();
					$('#source').show();
					$('#textboxes3').hide();
					$('#textboxes4').hide();
					$('#textboxes10').show();
					$('#otherdetails').show();
					$('#deplmnt').hide();
					$('#armydog1').hide();
					$('#armyequines1').show();
					$('#date_admitted1').hide();
				}
				
		});
		
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
