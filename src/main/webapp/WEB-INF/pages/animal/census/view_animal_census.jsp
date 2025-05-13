	
	
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
  <script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>

<style>
        .image-container {
            position: relative;
            display: inline-block;
            cursor: pointer;
        }
        .image-container img {
            width: 100px;
            height: 100px;
            border: 1px solid #ccc;
        }
        .hover-overlay {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: rgba(0, 0, 0, 0.5);
            color: white;
            display: flex;
            justify-content: center;
            align-items: center;
            opacity: 0;
            transition: opacity 0.3s ease-in-out;
        }
        .image-container:hover .hover-overlay {
            opacity: 1;
        }
    </style>
 
	
<form:form name="add_animal_census" id="add_animal_census"  action="add_animal_census_act?${_csrf.parameterName}=${_csrf.token}" method='POST' commandName="add_animal_census_cmnd" enctype="multipart/form-data">
	<div class="container" align="center">
		<div class="card">
			<div class="card-header">
				
				<strong><h3>VIEW ANIMAL CENSUS DETAILS </h3></strong>
			</div>
			<div class="card-body card-block">
				
					<div class="col-md-12">
					<div class="col-md-6">
              					<div class="row form-group">
	                					<div class="col col-md-6">	                					
						                	<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Animal Type</label>
						                </div>
			                                        <div class="form-check-inline form-check">
			                                          
			                                           <label id="animal_type" name="animal_type">
			                                          </label> 
			                                        </div>
	                				    </div>
	                			  </div>
	                	   </div> 
	
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> SUS No</label>
									</div>
									<div class="col-12 col-md-8">
										
										<label id="unit_sus_no_hid"  ></label>  
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Unit Name</label>
									</div>
									<div class="col-12 col-md-8">
										<label  id="unit_name_l"  ></label> 
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
											<label type="text" id="army_no" name="army_no" ></label> 
											
										</div>
									</div>
								</div>
	
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col col-md-4">
											<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Type of Dog</label>
										</div>
										<div class="col-12 col-md-8">
											<label  id="type_dog" name="type_dog" ></label> 
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
											<label  id="name_of_dog" name="name_of_dog" ></label> 								
										</div>
									</div>
								</div>
								
								<div class="col-md-6">
										<div class="row form-group">
											<div class="col col-md-4">
												<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Specialisation</label>
											</div>
											<div class="col-12 col-md-8">
												
												<label  id="specialization" name="specialization" ></label> 
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
										<label  id="breed" name="breed" ></label>
									</div>
								</div>
							</div>
	
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Color</label>
									</div>
									<div class="col-12 col-md-8">
										<label  id="colour" name="colour" ></label>
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
									
									<div class="col-12 col-md-8" id="armydog1" >
										<label  id="gender" name="gender" ></label>
									</div>
									
					
								</div>
							</div>
							
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Source</label>
									</div>
									<div class="col-12 col-md-8">
										
										<label  id="source" name="source" ></label>
										
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
										<label  id="date_of_birth" name="date_of_birth" onchange="calculate_age();"></label>
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
										<label id="details_of_sire" name="details_of_sire" > </label>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong>Name of Dam</label>
									</div>
									<div class="col-12 col-md-8">
										<label id="details_of_dam" name="details_of_dam" > </label>
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
										<label id="armyno_of_sire" name="armyno_of_sire" > </label>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong>Army no of Dam</label>
									</div>
									<div class="col-12 col-md-8">
										<label id="armyno_of_dam" name="armyno_of_dam" > </label>
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
								<label id="microchip_no" name="microchip_no"> </label>

							</div>
						</div>
					</div>
					<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label for="text-input" class=" form-control-label"><strong
										style="color: red;">*</strong>Front Face Image </label>
								</div>

								<div class="col-12 col-md-8">
									<div class="row form-group">
										<div class="image-container">
										
												<img  id="front"  class="pull-right" style="height: 100px;"/>
											
										</div>

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
										style="color: red;">*</strong>Left Face Image </label>
								</div>

								<div class="col-12 col-md-8">
									<div class="row form-group">
										<div class="image-container">
											<img  id="left"  class="pull-right" style="height: 100px;"/>
										</div>

											
									</div>
								</div>

							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label for="text-input" class=" form-control-label"><strong
										style="color: red;">*</strong>Right Face Image </label>
								</div>

								<div class="col-12 col-md-8">
									<div class="row form-group">
										<div class="image-container">
											<img  id="right"  class="pull-right" style="height: 100px;"/>
										</div>

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
										<label id="fitness_status" name="fitness_status" > </label>
										
									</div>
								</div>
							</div>
							
							<div class="col-md-6" id="date_admitted1" style="display: none;">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong>Date Admitted</label>
									</div>
									<div class="col-12 col-md-8">
										<label id="date_admitted" name="date_admitted" > </label>
										
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
										<label id="fitness_deployment" name="fitness_deployment" > </label>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label"> <strong style="color: red;">*</strong> Animal Purchase Cost(&#8377)</label>
									</div>
									<div class="col-12 col-md-8">
										<label id="animal_purchase_cost" name="animal_purchase_cost" > </label>
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
										<label id="date_of_tos" name="date_of_tos" > </label>
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
										<label id="date_of_auth" name="date_of_auth" > </label>
									</div>
								</div>
							</div>
							
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong>Authority No</label>
									</div>
									<div class="col-12 col-md-8">
									<label id="auth_letter_no" name="auth_letter_no" > </label>

									</div>
								</div>
							</div>
							
						</div>
						
				</div>
				
	
				<div id="submit_data" class="card-footer" align="center"
					class="form-control" style="display: none;">

					<div id="approve_checkdiv" style="display: none">
						<p>
							<input type="checkbox" class="btn btn-success btn-sm"
								id="approve_check"> I certify that all the data has been
							checked and verified by me.
						</p>
					</div>

					<input type="button" class="btn btn-primary btn-sm" value="Approve"
						id="approve_btn" style="display: none"
						onclick="if (confirm('Are You Sure You Want to Approve Animal Census Data?')){return Approved();}else{return false;}">

					
					<input type="button" class="btn btn-primary btn-sm" value="Reject" id="reject_btn" style="display: none"
						onclick="if (confirm('Are You Sure You Want to Reject Animal Census Data?')){addRemarkModel('Reject',0)}else{return false;}">
					
					<a href="Search_Animal_Census" class="btn btn-info btn-sm">Back</a>

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
				if ($("input#animal_purchase_cost").val() == "" ) {
					alert("Please Enter Animal Purchase Cost ");
					return false;
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
	  
	  });
	  </script>
	 
	  
	  
	  	<c:url value="animal_census_dtl1" var="searchUrl" />
		<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="anml_type8">
			<input type="hidden" name="anml_type8" id="anml_type8" value="0"/>
		</form:form>

<c:url value="Approve_animal_census" var="approveUrl" />
<form:form action="${approveUrl}" method="post" id="approveForm"
	name="approveForm" modelAttribute="id3">
	<input type="hidden" name="id3" id="id3" value="0" />
	<input type="hidden" name="status" id="status" value="0" />
	<input type="hidden" name="modified_by" id="modified_by" />
	<input type="hidden" name="modified_date" id="modified_date" value="0" />
</form:form>

<c:url value="Reject_animal_census" var="rejectUrl" />
<form:form action="${rejectUrl}" method="post" id="rejectForm"
	name="rejectForm" modelAttribute="id4">
	<input type="hidden" name="id4" id="id4" value="0" />
	<input type="hidden" name="reject_remarks_jc" id="reject_remarks_jc" value="0" />
</form:form>


<script>
	function Approved() {
		var id = '${list[0][26]}';
		console.log("-------"+id3);
		$("#id3").val(id);
		document.getElementById('approveForm').submit();
	}

	function Reject() {

		var id = '${list[0][26]}';
		$("#id4").val(id);
		$("#reject_remarks_jc").val($("#reject_remarks").val());
		document.getElementById('rejectForm').submit();
	}
</script>


<script>
		
		
		
		$(document).ready(function() {
			
			
			$("#submit_data").show();
			$("#approve_btn").hide();
			$("#reject_btn").hide();
			
			var status='${list[0][28]}';
			if(status==0){
				
			$("#submit_data").show();
			$("#approve_btn").hide();
			$("#reject_btn").hide();
			$("#approve_checkdiv").show();
			$('#approve_check').click(function() {
				if ($(this).prop("checked") == true) {
					$("#approve_btn").show();
					$("#reject_btn").show();
				} else if ($(this).prop("checked") == false) {
					$("#approve_btn").hide();
					$("#reject_btn").hide();
				}
			});
		};
			
			$("#animal_purchase_cost").text('${list[0][0]}');
			$("#animal_type").text('${list[0][1]}');
			$("#army_no").text('${list[0][2]}');
			$("#auth_letter_no").text('${list[0][3]}');
			$("#unit_sus_no_hid").text('${list[0][4]}');
			$("#colour").text('${list[0][5]}');
			$("#breed").text('${list[0][6]}');
			$("#details_of_dam").text('${list[0][7]}');
			$("#armyno_of_dam").text('${list[0][8]}');
			$("#date_of_auth").text('${list[0][9]}');
			
			var fit='${list[0][14]}';
			
			
			if (fit == "HOSPITALIZED" || fit == "UNFIT FOR DEPLOYMENT" ) {
				$('#date_admitted1').show();
				$("#date_admitted").text('${list[0][10]}');
				}
			
			$("#date_of_tos").text('${list[0][11]}');
			$("#date_of_birth").text('${list[0][12]}');
			var id = '${list[0][26]}'
			
			/* $('#identity_image_preview').attr("src", "animalimgConvertpath?i_id="+id+" "); */
			$('#front').attr("src", "animalimgConvertpath?i_id="+id+"&fildname=front_img_path");
			$('#left').attr("src", "animalimgConvertpath?i_id="+id+"&fildname=left_img_path");
			$('#right').attr("src", "animalimgConvertpath?i_id="+id+"&fildname=right_img_path");
			$("#fitness_deployment").text('${list[0][13]}');
			$("#fitness_status").text('${list[0][14]}');
			$("#gender").text('${list[0][15]}');
			$("#microchip_no").text('${list[0][16]}');
			$("#name_of_dog").text('${list[0][17]}');
			$("#armyno_of_sire").text('${list[0][18]}');
			$("#details_of_sire").text('${list[0][19]}');
			$("#source").text('${list[0][20]}');
			$("#specialization").text('${list[0][21]}');
			$("#type_dog").text('${list[0][22]}');
			$("#upload_img_path").text('${list[0][23]}');
			$("#id").text('${list[0][26]}');
			$("#unit_name_l").text('${list[0][27]}');
			
			
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
        function openFullScreen() {
            var imgSrc = document.getElementById("identity_image_preview").src;
            var newWindow = window.open("", "_blank");
            newWindow.document.write('<img src="' + imgSrc + '" style="width:100%; height:auto;" />');
        }
    </script>