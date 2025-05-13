	
	
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
<style>
.hover-overlay {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0,0,0,0.5);
    display: none;
    align-items: center;
    justify-content: center;
    color: white;
    font-weight: bold;
    cursor: pointer;
}
</style>

 
	
<form:form name="add_animal_census" id="add_animal_census"  action="add_animal_census_act?${_csrf.parameterName}=${_csrf.token}" method='POST' commandName="add_animal_census_cmnd" enctype="multipart/form-data">
	<div class="container" align="center">
		<div class="card">
			<div class="card-header">
				<strong><h3>ADD ANIMAL CENSUS DETAILS </h3></strong>
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
			                                          <!-- <label for="inline-radio2" class="form-check-label ">
			                                            <input type="radio" id="anml_type2" name="anml_type" value="Army Equines" class="form-check-input" onchange="radioch();" > Army Equines
			                                          </label> -->
			                                        </div>
	                				    </div>
	                			  </div>
	                	   </div> 		  
				</div>
				<div class="card" id="textboxes" style="display: none; margin-bottom: 0; border: 0;">
					<div class="card-header">
						<strong>BASIC DETAILS</strong>
					</div>
					<div class="card-body card-block">
	
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> SUS No</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="sus_no" name="sus_no" class="form-control" autocomplete="off" maxlength="8" style="display: none">
										<label id="unit_sus_no_hid" style="display: none" ><b> ${roleSusNo} </b></label>  
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Unit Name</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="unit_name" name="unit_name" class="form-control" autocomplete="off" maxlength="100" style="display: none">
										<label  id="unit_name_l" style="display: none" ><b>${unit_name}</b></label> 
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
											<input type="text" id="army_num" name="army_num" autocomplete="off" class="form-control"  maxlength="5" oninput="handleInput(this)" onchange="army_no_already_exist();" >
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
											<input type="text" id="name_of_dog" name="name_of_dog" autocomplete="off" class="form-control" oninput="this.value = this.value.toUpperCase()"  maxlength="35">
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
										<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong>Name of Sire</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="details_of_sire" name="details_of_sire" class="form-control" oninput="this.value = this.value.toUpperCase()" autocomplete="off" maxlength="35"> 
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong>Name of Dam</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="details_of_dam" name="details_of_dam" class="form-control" oninput="this.value = this.value.toUpperCase()" autocomplete="off" maxlength="35">
									</div>
								</div>
							</div>
						</div>
						
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong>Army no of Sire</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="armyno_of_sire" name="armyno_of_sire" class="form-control" autocomplete="off" maxlength="5" oninput="handleInput(this)"> 
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong>Army no of Dam</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="armyno_of_dam" name="armyno_of_dam" class="form-control" autocomplete="off" maxlength="5" oninput="handleInput(this)">
									</div>
								</div>
							</div>
						</div>
						
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong>Microchip No</label>
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
									<label for="text-input" class=" form-control-label"><strong
										style="color: red;">*</strong>Front Face Image <br> <span style="font-size: 10px;font-size:12px;color:red">(Only *.jpg, *.jpeg and *.png file extensions and file size max 2MB are allowed.)</span></label>
								</div>

								<div class="col-12 col-md-8">
									<div class="row form-group">
								<div class="image-container">
									<img id="front_image_preview" alt="Officer Image"
										src="js/img/No_Image.jpg" width="100" height="100"/>
								</div>

										<input type="file" id="front_image_anml" autocomplete="off" name="front_image_anml" class="form-control" onchange="previewImage(this,'front_image_preview')">
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
										style="color: red;">*</strong>Left Image <br> <span style="font-size: 10px;font-size:12px;color:red">(Only *.jpg, *.jpeg and *.png file extensions and file size max 2MB are allowed.)</span></label>
								</div>

								<div class="col-12 col-md-8">
									<div class="row form-group">
								<div class="image-container">
									<img id="left_image_preview" alt="Officer Image"
										src="js/img/No_Image.jpg" width="100" height="100"/>
								</div>

								<input type="file" id="left_image_anml" autocomplete="off" name="left_image_anml" class="form-control" onchange="previewImage(this,'left_image_preview')">
							</div>
								</div>

							</div>
						</div>
						
							
							<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label for="text-input" class=" form-control-label"><strong
										style="color: red;">*</strong>Right Image <br> <span style="font-size: 10px;font-size:12px;color:red">(Only *.jpg, *.jpeg and *.png file extensions and file size max 2MB are allowed.)</span></label>
								</div>

								<div class="col-12 col-md-8">
									<div class="row form-group">
								<div class="image-container">
									<img id="right_image_preview" alt="Officer Image"
										src="js/img/No_Image.jpg" width="100" height="100"/>
								</div>

										<input type="file" id="right_image_anml" autocomplete="off" name="right_image_anml" class="form-control" onchange="previewImage(this,'right_image_preview')">
							</div>
								</div>

							</div>
						</div>
							
						</div>
						<div class="col-md-12">			
							
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong>Fitness Status</label>
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
										<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong>Date Admitted</label>
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
						</div>
	
	
						
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong>TOS</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="date" id="tos" name="tos" class="form-control" autocomplete="off" min='1899-01-01' max='${date}' maxlength="10">
									</div>
								</div>
							</div>
							
	
						</div>
						
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong>Date of Authority</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="date" id="date_of_auth" name="date_of_auth" class="form-control" autocomplete="off" min='1899-01-01' max='${date}' maxlength="10">
									</div>
								</div>
							</div>
							
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong>Authority No</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="auth_no" name="auth_no" autocomplete="off" class="form-control" maxlength="50">
									</div>
								</div>
							</div>
							
						</div>
						
				</div>
				
	
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
				
				if ($("input#date_of_auth").val() == "") {                  
					alert("Please Enter Date of Auth");
					$("input#date_of_auth").focus();
					return false;
				}
				
				if ($("input#tos").val() == "") {                  
					alert("Please Enter Date of TOS");
					$("input#tos").focus();
					return false;
				} 
				
				if($("#fitness_status").val() == "3" || $("#fitness_status").val() == "7"){
					if ($("input#date_admitted").val() == "") {                  
						alert("Please Enter Date of Admitted");
						$("input#date_admitted").focus();
						return false;
					} 
					
				}
				
			
				if ($("input#armyno_of_dam").val() == "" ) {
					alert("Please Enter Army no of Dam");
					return false;
				}
				
				if ($("input#armyno_of_sire").val() == "" ) {
					alert("Please Enter Army no of Sire");
					return false;
				}
				if ($("input#details_of_dam").val() == "" ) {
					alert("Please Enter Name of Dam");
					return false;
				}
				if ($("input#details_of_sire").val() == "" ) {
					alert("Please Enter Name of Sire");
					return false;
				}
				/* if ($("input#animal_purchase_cost").val() == "" ) {
					alert("Please Enter Animal Purchase Cost ");
					return false;
				} */
				if ($("input#front_image_anml").val() == "" ) {
					alert("Please Upload Front Face Image ");
					return false;
				}
				if ($("input#left_image_anml").val() == "" ) {
					alert("Please Upload left Face Image ");
					return false;
				}
				if ($("input#right_image_anml").val() == "" ) {
					alert("Please Upload Right Face Image ");
					return false;
				}
				
				
			 return true;
		}    
		 
		
		function chkFitnessStatus(obj){
			
			if(obj.value=='3'){
				$('#date_admitted1').show();
			}
			else if(obj.value=='7'){
				$('#date_admitted1').show();
			}
			else if(obj.value=='2'){
				$('#date_admitted1').hide();
			}
			else if(obj.value=='4'){
				$('#date_admitted1').hide();
			}else{
				$('#date_admitted1').hide();
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
	  
	  
	  	<c:url value="animal_census_dtl1" var="searchUrl" />
		<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="anml_type8">
			<input type="hidden" name="anml_type8" id="anml_type8" value="0"/>
		</form:form> 
		
		
		<script>
		
		$(document).ready(function() {
			
			var r =('${roleAccess}');
			if( r == "Unit"){
				
				 $("#unit_sus_no_hid").show();
				 $("#unit_name_l").show();
				 
			}
			else  {
				
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
			
			$("#front_image_anml").change(function(){	
				checkFileExtImage(this);
			});
			$("#left_image_anml").change(function(){	
				checkFileExtImage(this);
			});
			$("#right_image_anml").change(function(){	
				checkFileExtImage(this);
			});
			
			
			 if('${anml_type8}' == "Army Dog")
				{
				   document.getElementById(anml_type1.id).checked = true;
				   
				    $('#textboxes').show();
					$('#textboxes3').show();
					/* $('#textboxes4').show(); */
					$('#source').show();
					/* $('#textboxes10').show(); */
					/* $('#otherdetails').show(); */
					/* $('#textboxes1').hide(); */
					/* $('#deplmnt').show(); */
					$('#armydog1').show();
					/* $('#armyequines1').hide(); */
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
			 	//alert('Allowed');
			    break;
			  	default:
			    	alert('Only *.jpg, *.jpeg and *.png file extensions allowed');
			   	file.value = "";
			}
		} 
		</script>
		
		<script>
// Function to block special characters and convert text to uppercase
function handleInput(input) {
    // Convert the value to uppercase
    input.value = input.value.toUpperCase();

    // Regular expression to match any special characters
    const regex = /[^A-Z0-9]/g; // Allow only uppercase letters, numbers

    // Replace special characters with an empty string
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
function previewImage(input,id) {
    	
        
         var maxSizeInBytes = 2 * 1024 * 1024; 
         var file = input.files[0];
         var id1 = id;
         
         if (file) {      
             if (file.size > maxSizeInBytes) {
                 alert('File is too large. Maximum file size is 2MB.');
                 input.value = ''; 
                 return;
             }
           
             var validTypes = ['image/jpeg', 'image/png', 'image/jpg'];
             if (!validTypes.includes(file.type)) {
                 alert('Invalid file type. Only JPG, JPEG, and PNG are allowed.');
                 input.value = ''; 
                 return;
             }

         
             var preview = document.getElementById(id1);
             preview.src = window.URL.createObjectURL(file);
       
            
         }
     }
  </script>
