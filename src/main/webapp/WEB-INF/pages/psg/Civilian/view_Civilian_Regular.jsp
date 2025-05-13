<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
  <script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
<style>
.card-header {
	margin-bottom: 0;
	background-color: #cccccc;
	border-bottom: 1px solid #cccccc;
	padding: 12px;
}

/* Full screen modal styles */
        #fullScreenModal {
            display: none;
            position: fixed;
            z-index: 1000;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0,0,0,0.9);
            align-items: center;
            justify-content: center;
        }

        #fullScreenModal img {
            max-width: 90%;
            max-height: 90%;
            object-fit: contain;
        }

        #closeFullScreen {
            position: absolute;
            top: 15px;
            right: 35px;
            color: white;
            font-size: 40px;
            font-weight: bold;
            cursor: pointer;
        }

        /* Image hover effect */
        .image-container {
            position: relative;
            display: inline-block;
        }

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

        .image-container:hover .hover-overlay {
            display: flex;
        }
</style>
 
		<div class="animated fadeIn">
	    	<div class="container-fluid" align="center">
	    		<div class="card">
	    		<div class="card-header"><h5>Approve Regular Employee</h5> <h6 class="enter_by"><!-- <span style="font-size:12px;color:red;">(To be filled by PCTA only)</span> --></h6></div>
				<div class="card-body card-block">
				
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"> </strong> Authority </label>
								</div>
								<div class="col-md-8">
								<label>${listfull[0].authority}</label>
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Date of Authority </label>
								</div>
								<div class="col-md-8">
									<label id = "dt_of_authority"></label>
								</div>
							</div>
						</div>
					</div>
				
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"></strong> Unit Sus No. </label>
								</div>
								<div class="col-md-8">
								<label>${listfull[0].sus_no}</label>
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Unit Posted To </label>
								</div>
								<div class="col-md-8">
								 <label>${listfull[0].unit_name}</label> 
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"> </strong> Employee No. </label>
								</div>
								<div class="col-md-8">
									<label>${listfull[0].employee_no}</label> 
								</div>
							</div>
						</div>
      <div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class="form-control-label"><strong
										style="color: red;"> </strong> Mobile No</label>
								</div>
								<div class="col-md-8">
									<label>${civilian_details_cmd.mobile_no}</label>
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12">
						<div class="col-md-4">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">First Name</label>
								</div>
								<div class="col-md-8">
										<label>${listfull[0].first_name}</label> 
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="row form-group">
								<div class="col-md-5">
									<label class=" form-control-label"><strong
										style="color: red;"> </strong>Middle Name</label>
								</div>
								<div class="col-md-7">
								<label>${listfull[0].middle_name}</label>  
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"> </strong>Last Name</label>
								</div>
								<div class="col-md-8">
								<label>${listfull[0].last_name}</label>  
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">Date of Birth</label>
								</div>
								<div class="col-md-8">
								<label id="dob"></label>  
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">Gender</label>
								</div>
								<div class="col-md-8">
								<label>${listfull[0].gender}</label>
								</div>
							</div>
						</div>
					</div>
					
					
					<div class="col-md-12" id ="gender_other_div">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">Gender Other</label>
								</div>
								<div class="col-md-8">
								<label >${listfull[0].gender_other}</label>
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">Classification of
										Services</label>
								</div>
								<div class="col-md-8">
									<label>${listfull[0].classification_services}</label>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">Group</label>
								</div>
								<div class="col-md-8">
								<label>${listfull[0].civ_group}</label>
								
								</div>
							</div>
						</div>
					</div>
					
					
						<div class="col-md-12">
				

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">Cadre</label>
								</div>
								<div class="col-md-8">
								<label>${listfull[0].cadre}</label>
								</div>
							</div>
						</div>
						
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">Highest Qualification</label>
								</div>
								<div class="col-md-8">
								<label>${listfull[0].qualification}</label>
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12">

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">Category </label>
								</div>
								<div class="col-md-8">
								
								<label>${listfull[0].category_belongs}</label>
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Service Status </label>
								</div>
								<div class="col-md-8">
								<label>${listfull[0].service_status}</label>

								</div>
							</div>
						</div>
					</div>


					<div class="col-md-12">
						<div class="col-md-6" id = "div_classification_of_trade" style="display: none;">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">Classification of
										Trade</label>
								</div>
								<div class="col-md-8">
								<label>${listfull[0].classification_trade}</label>
								</div>
							</div>
						</div>
						
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">Type</label>
								</div>
								<div class="col-md-8">
								<label>${listfull[0].civ_type}</label>
								</div>
							</div>
						</div>
					</div>
					
					<div class="col-md-12" id ="classification_trade_other_div" style="display: block;">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">Classification of
										Trade Other</label>
								</div>
								<div class="col-md-8">
								<label>${listfull[0].classification_trade_other}</label>
								</div>
							</div>
						</div>
					</div>
					
				<div class="col-md-12" id="exservice_man1">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"><strong
									style="color: red;"> </strong> Whether Ex-Serviceman</label>
							</div>
							<div class="col-md-8">
								<label>No</label>
							</div>
						</div>
					</div>
				</div>

				<div class="col-md-12" id="exservice_man">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"> Service's Name</label>
							</div>
							<div class="col-md-4">
								<label>${listfull[0].whether_ex_serviceman}</label>

							</div>
						</div>
					</div>
					<div id="other_service_div" class="col-md-6"
							style="display: none;">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Other</label><strong
										style="color: red;"> </strong>
								</div>
								<div class="col-md-8">
									<label>${listfull[0].service_other}</label>
									
								</div>
							</div>
						</div>
					</div>

				
				<div class="col-md-12" id="person_disability1">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"><strong
									style="color: red;"> </strong>Whether Person With Disability</label>
							</div>
							<div class="col-md-8">
								<label>No</label>
							</div>
						</div>
					</div>


				</div>
					<div class="col-md-12" id="person_disability">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"> </strong>Whether Person With Disability</label>
								</div>
								<div class="col-md-8">
								<label>${listfull[0].whether_person_disability}</label>
								</div>
							</div>
						</div>


					</div>

					<!-- 	              			change -->
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> <strong
										style="color: red;"> </strong>Post in which Initially Appointed
									</label>
								</div>
								<div class="col-md-8">
									<label>${listfull[0].post_initialy_appointed}</label>
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Date of Joining in Govt Service </label>
								</div>
								<div class="col-md-8"> 
								<label id= "dt_of_join_govt_service"></label>
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
                                  <label id = "designation"></label> 
									<label>${listfull[0].designation}</label>
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">Date of Designation</label>
								</div>
								<div class="col-md-8">
								<label id ="dt_of_designation"></label>
								</div>
							</div>
						</div>
					</div>


					<div class="col-md-12">

						<!-- 	              				change -->
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">Pay Level</label>
								</div>
								<div class="col-md-8">
								 <label>${listfull[0].pay_level}</label>
								</div>
							</div>
						</div>

							<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">Date of TOS</label>
								</div>
								<div class="col-md-8">
								<label id ="date_of_tos"></label>
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12" style="">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Father's Name</label>
								</div>
								<div class="col-md-8">
								<label>${listfull[0].father_name}</label>	
								</div>
							</div>
						</div>


						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Mother's Name</label>
								</div>
								<div class="col-md-8">
								<label>${listfull[0].mother_name}</label>
								</div>
							</div>
						</div>
					</div>
					
					<div class="col-md-12" style="">
						<div class="col-md-6">
							
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"> </strong> Religion </label>
								</div>
								<div class="col-md-8">
								 <label>${listfull[0].religion}</label>
								</div>
							</div>
						
						</div>


						<div class="col-md-6">
							
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"> </strong> Mother Tongue </label>
								</div>
								<div class="col-md-8">
									 <label>${listfull[0].mother_tongue}</label>
								</div>
							</div>
						
						</div>
						
						
					</div>
					<div class="col-md-12" >
						<div class="col-md-6" id ="religion_other_div">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">Religion Other</label>
								</div>
								<div class="col-md-8">
								 <label>${listfull[0].religion_other}</label>
								</div>
							</div>
						</div>
						<div class="col-md-6" id ="mother_tongue_other_div">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">Mother Tongue Other</label>
								</div>
								<div class="col-md-8">
								 <label>${listfull[0].mother_tongue_other}</label>
								</div>
							</div>
						</div>
						
					</div>
					<div class="col-md-12" style="">
						<div class="col-md-6">
							
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"> </strong> Aadhaar No. </label>
								</div>
								<div class="col-md-8">
								 <label>${listfull[0].aadhar_card}</label>
								</div>
							</div>
		
						</div>
						
								<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;"></strong> PAN No.</label>
									</div>
									<div class="col-md-8">
                                    <label>${listfull[0].pan_no}</label>
									</div>
								</div>
							</div>


					</div>

					<div class="card-header-inner" id="aa"
						style="margin-left: 20px; margin-bottom: 20px;">
						<strong>ORIGINAL DOMICILE</strong>
					</div>

					<div class="col-md-12">
						<div class="col-md-4">
							<div class="row form-group" >
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"> </strong> Country </label>
								</div>
								<div class="col-md-8">
								 <label>${listfull[0].country_original}</label>
								</div>
								
							</div>
						</div>
						<div class="col-md-4">
							<div class="row form-group">
							
							<div class="col-md-4">
									<label class=" form-control-label">State </label>
								</div>
								<div class="col-md-8">
								<label>${listfull[0].state_original}</label>
									
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="row form-group">
							<div class="col-md-4">
									<label class=" form-control-label"> District </label>
								</div>
								<div class="col-md-8">
									 <label>${listfull[0].district_original}</label>
								</div>
							</div>
						</div>
					</div>
					
					
					<div class="col-md-12">
						<div class="col-md-4" id = "original_country_other_div" style="display: none;">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"> </strong> Country Other</label>
								</div>
								<div class="col-md-8">
								<label>${listfull[0].original_country_other}</label>
								</div>
								
							</div>
						</div>
						<div class="col-md-4" id="original_state_other_div" style="display: none;">
							<div class="row form-group">
							
							<div class="col-md-4">
									<label class=" form-control-label">State Other </label>
								</div>
								<div class="col-md-8">
								<label>${listfull[0].original_state_other}</label>
								</div>
							</div>
						</div>
						<div class="col-md-4" id="original_district_other_div" style="display: none;">
							<div class="row form-group">
							<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"> </strong> District Other </label>
								</div>
								<div class="col-md-8">
								<label>${listfull[0].original_district_other}</label>
								</div>
								
								
							</div>
						</div>
					</div>

<div class="col-md-12">
						
						
						<div class="col-md-4">
							<div class="row form-group">
							
								
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"> </strong> Tehsil </label>
								</div>
								<div class="col-md-8">
									<label>${listfull[0].tehsil_origin}</label>
								</div>
							</div>
						</div>
						
						<div class="col-md-4" id="original_tehshil_other_div" style="display: none;">
							<div class="row form-group">
							
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"> </strong> Tehsil Other</label>
								</div>
								<div class="col-md-8">
								<label>${listfull[0].original_tehshil_other}</label>
								</div>
							</div>
						</div>
						
					</div>

					<div class="card-header-inner" id="aa"
						style="margin-left: 20px; margin-bottom: 20px;">
						<strong>PRESENT DOMICILE</strong>
					</div>

					<div class="col-md-12">
						<div class="col-md-4">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"> </strong> Country </label>
								</div>
								<div class="col-md-8">
									<label>${listfull[0].country_present}</label>
								</div>
								
							</div>
						</div>
						<div class="col-md-4">
							<div class="row form-group">
							<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"> </strong> State </label>
								</div>
								<div class="col-md-8">
									<label>${listfull[0].state_present}</label>
								</div>
								
								
							</div>
						</div>
						<div class="col-md-4">
							<div class="row form-group">
							<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"> </strong> District </label>
								</div>
								<div class="col-md-8">
									 <label>${listfull[0].district_present}</label>
								</div>
								
								
							</div>
						</div>
					</div>
					
					<div class="col-md-12">
						<div class="col-md-4" id = "present_country_other_div" style="display: none;">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"> </strong> Country Other</label>
								</div>
								<div class="col-md-8">
								<label>${listfull[0].present_country_other}</label>
								</div>
								
							</div>
						</div>
						<div class="col-md-4" id="present_state_other_div" style="display: none;">
							<div class="row form-group">
							
							<div class="col-md-4">
									<label class=" form-control-label">State Other </label>
								</div>
								<div class="col-md-8">
								<label>${listfull[0].present_state_other}</label>
								</div>
							</div>
						</div>
						<div class="col-md-4" id="present_district_other_div" style="display: none;">
							<div class="row form-group">
							<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"> </strong> District Other </label>
								</div>
								<div class="col-md-8">
								<label>${listfull[0].present_district_other}</label>
								</div>
								
								
							</div>
						</div>
					</div>
					
					
					<div class="col-md-12">
						<div class="col-md-4">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"> </strong> Tehsil </label>
								</div>
								<div class="col-md-8">
									 <label>${listfull[0].tehsil_present}</label>
								</div>
							</div>
						</div>
							<div class="col-md-4">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"> </strong> Nationality </label>
								</div>
								<div class="col-md-8">
									<label>${listfull[0].nationality}</label>
								</div>
							</div>
						</div>
					</div>
					
					
						<div class="col-md-12">
						<div class="col-md-4" id="present_tehsil_other_div" style="display: none;">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"> </strong> Tehsil Other</label>
								</div>
								<div class="col-md-8">
									 <label>${listfull[0].present_tehshil_other}</label>
								</div>
							</div>
						</div>
							<div class="col-md-4" id="nationality_other_div">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"> </strong> Nationality Other</label>
								</div>
								<div class="col-md-8">
									<label>${listfull[0].nationality_other}</label>
								</div>
							</div>
						</div>
					</div>
					
<!-- 						<div class="col-md-12"> -->
<!-- 						<div class="col-md-4"> -->
<!-- 							<div class="row form-group"> -->
<!-- 								<div class="col-md-4"> -->
<!-- 									<label class=" form-control-label"><strong -->
<!-- 										style="color: red;"> </strong>  Non Effective </label> -->
<!-- 								</div> -->
<!-- 								<div class="col-md-8"> -->
<!-- 									<label id="eff"></label>  -->

<!-- 								</div> -->

<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div> -->


				 <div class="col-md-12">

        <div class="col-md-6">
            <div class="row form-group">
                 <div class="col-md-4">
                    <label class="form-control-label">
                        <strong style="color: red;"></strong> Photograph
                    </label>
                </div>
                <div class="image-container">
                    <img id="identity_image_preview" 
                         alt="Officer Image" 
                         src="js/img/No_Image.jpg" 
                         width="100" 
                         height="100" 
                         onclick="openFullScreen()"
                    />
                    <div class="hover-overlay" onclick="openFullScreen()">
                        Click to View
                    </div>
                </div>
               
                <input type="text" id="pre_identity_id" name="pre_identity_id" value="0" style="display: none">
            </div>
        </div>
    </div>
       <div id="fullScreenModal">
        <span id="closeFullScreen" onclick="closeFullScreen()">&times;</span>
        <img id="fullScreenImage">
    </div>


					<div class="col-md-12" id="none_effective_no">
	              			<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;"> </strong> Non Effective </label>
						                </div>
						                <div class="col-md-8">
						                 <label>No</label>
						       				 						                   

						                </div>
						            </div>
	              				</div>
	              				</div>
					
						<div class="col-md-12" id="none_effective">
						
							<div class="col-md-4" >
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"> </strong> Non Effective</label>
								</div>
								<div class="col-md-8">
								<label>${listfull[0].non_effective}</label>
								</div>
							</div>
						</div>
						
						<div class="col-md-4">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"> </strong> Non Effective Date </label>
								</div>
							   <div class="col-md-8">
								<label id ="date_non_effective2"></label>
								</div>
							</div>
						
						</div>

					</div>
.				</div>

				
	        	</div>
			</div>
	</div>
	
<form:form name="" id="civilian_reg" action="viewcivilianRegularAction" method="post" class="form-horizontal" commandName="civilian_details_cmd">
	    		<div class="panel-group" id="accordion">
				<div class="panel panel-default">
				
				<input type="hidden" id="id" name="id" class="form-control autocomplete" value="${listfull[0].id}" autocomplete="off">
				<input type="hidden" id="civilian_status" name="civilian_status" class="form-control autocomplete" value="${listfull[0].civilian_status}" autocomplete="off">
				<input type="hidden" id="main_id" name="main_id" class="form-control autocomplete" value="${listfull[0].main_id}" autocomplete="off">
				<input type="hidden" id="cancel_status" name="cancel_status" class="form-control autocomplete" value="${listfull[0].cancel_status}" autocomplete="off">						
				   <div class="col-md-12" align="center" >	              					
						    <p><input type="checkbox" id="myCheck" onclick="myFunction();"/> I certify that all the data has been checked and verified by me. </p> 
					</div>
					<div class="col-md-12" id = "submit_a" align="center">	              					
						     <!-- <input type="submit" class="btn btn-primary btn-sm" value="Approve"> -->
						     
						      <input type="submit" class="btn btn-primary btn-sm" value="Approve" onclick="if (confirm('Are You Sure You Want to Approve Data?')){return true;}else{return false;}">
						     
						    <!--  <input type="button" class="btn btn-primary btn-sm" value="Reject" onclick="reject_data()"> -->
						     
						      <input type="button" class="btn btn-primary btn-sm" value="Reject" onclick="if (confirm('Are You Sure You Want to Reject Data?')){addRemarkModel('reject_data',0)}else{return false;}">
						      <a href="Search_civilian_regular" class="btn btn-info btn-sm">Back</a>
						      
						      
						      
				   </div>
			   </div>
			</div>
</form:form>
<a href="#" class="go-top fa fa-arrow-up" style="display:none"></a>

  <script>
  $(document).ready(function() {
	  var id = '${civilian_details_cmd.id}'		
			$('#identity_image_preview').attr("src", "regularCivIdentityConvertpath?i_id="+id+" ");
	  $("#dt_of_authority").text(('${listfull[0].dt_of_authority}'));
	  $("#dob").text(('${listfull[0].dob}'));
	  $("#dt_of_join_govt_service").text(('${listfull[0].joining_date_gov_service}'));
	  $("#dt_of_designation").text(('${listfull[0].designation_date}'));
	  $("#date_non_effective2").text(('${listfull[0].date_non_effective}'));
	  $("#date_of_tos").text(('${listfull[0].date_of_tos}'));
	  $("#service_other").text(('${listfull[0].service_other}'));
	  
      if('${listfull[0].gender_other}' == "")
		 {
		 $("#gender_other_div").hide();
		 }
      
      if('${listfull[0].service_other}' != "")
		 {
		 $("#other_service_div").show();
		 }
      
      
      
      if('${listfull[0].classification_trade_other}' == "")
		 {
		 $("#classification_trade_other_div").hide();
		 }
      
      if('${listfull[0].religion_other}' == "")
		 {
		 $("#religion_other_div").hide();
		 }
      
      if('${listfull[0].mother_tongue_other}' == "")
		 {
		 $("#mother_tongue_other_div").hide();
		 }
      if('${listfull[0].original_country_other}' != "")
		 {
		 $("#original_country_other_div").show();
		 }
      

      if('${listfull[0].original_state_other}' != "")
		 {
		 $("#original_state_other_div").show();
		 }

      if('${listfull[0].original_district_other}' != "")
		 {
		 $("#original_district_other_div").show();
		 }
      
      if('${listfull[0].original_tehshil_other}' != "")
		 {
		 $("#original_tehshil_other_div").show();
		 }

      
      
      if('${listfull[0].present_country_other}' != "")
		 {
		 $("#present_country_other_div").show();
		 }
      
      if('${listfull[0].present_state_other}' != "")
		 {
		 $("#present_state_other_div").show();
		 }

      if('${listfull[0].present_district_other}' != "")
		 {
		 $("#present_district_other_div").show();
		 }
      
      if('${listfull[0].present_tehshil_other}' != "")
		 {
		 $("#present_tehsil_other_div").show();
		 }
      if('${listfull[0].nationality_other}' == "")
		 {
		 $("#nationality_other_div").hide();
		 }
      
      if('${listfull[0].non_effective}' == "")
  	 {
 		 $("#none_effective").hide();
 		 $("#none_effective_no").show();
 	 	}
 		 else
 		{
 		 $("#none_effective").show();
 		 $("#none_effective_no").hide(); 	 
 		}
    
      if('${listfull[0].whether_ex_serviceman}' == "")
      	{
      	  $("#exservice_man").hide();
      	  $("#exservice_man1").show();
      	}
      else
      	{
      	  $("#exservice_man").show();
      	  $("#exservice_man1").hide();
      	}
      if('${listfull[0].whether_person_disability}' == "")
  	{
  	  $("#person_disability").hide();
  	  $("#person_disability1").show();
  	}
  else
  	{
  	  $("#person_disability").show();
  	  $("#person_disability1").hide();
  	}
	 
      $("#submit_a").hide();
      
      ongaz('${listfull[0].classification_services}');
  
  });
  
	 
  function ongaz(val){
	  if(val == "GAZETTED" )
	    {
			 $("div#div_classification_of_trade").hide();
		} 
		else 
		{
			 $("div#div_classification_of_trade").show();
		}
  }

  function classification_trade_other(val){
	  
	  var val = '${listfull[0].classification_trade_other}';
	  alert(val)
	  if(val == "OTHERS" )
	    {
			 $("div#classification_trade_other_div").show();
		} 
		else 
		{
			 $("div#classification_trade_other_div").hide();
		}
  }
	 function myFunction() {
		  var checkBox = document.getElementById("myCheck");
		 
		  if (checkBox.checked == true){
			  $("#submit_a").show();
			 
		  } else {
			  $("#submit_a").hide();
			
		  }
	}
	 
	 function ServiceOtherFn() {
			
			var sx = $("#whether_ex_serviceman option:selected").text();
			 $("#whether_ex_serviceman_hid").val(sx); 
			if(sx == "OTHER"){
				$("#other_service_div").show();
			}
			else{
				$("#other_service_div").hide();
			}
		}
	 

	 
	 function reject_data(){
		  var checkBox = document.getElementById("myCheck");
		if(checkBox.checked == true)
			{
			var id=$("#id").val(); 
		    $("#id4").val(id);
		    $("#reject_remarks4").val($("#reject_remarks").val());
			document.getElementById('rejectForm').submit();
		}
		else
			{
			alert("Please Select checkbox");

			}
			}
	
	 
     function openFullScreen() {
         const modal = document.getElementById('fullScreenModal');
         const fullScreenImg = document.getElementById('fullScreenImage');
         const previewImg = document.getElementById('identity_image_preview');         
     
         if (previewImg.src && previewImg.src.indexOf('No_Image.jpg') === -1) {
             fullScreenImg.src = previewImg.src;
             modal.style.display = 'flex';
         }
     }

     function closeFullScreen() {
         const modal = document.getElementById('fullScreenModal');
         modal.style.display = 'none';
     }
    
     window.onclick = function(event) {
         const modal = document.getElementById('fullScreenModal');
         if (event.target === modal) {
             modal.style.display = 'none';
         }
     }

	 
	 
  </script>

  
  <c:url value="Reject_Civilian" var="rejectUrl" />
		 <form:form action="${rejectUrl}" method="post" id="rejectForm" name="rejectForm" modelAttribute="id4">
		 	<input type="hidden" name="id4" id="id4" value="0"/> 
		 	<input type="hidden" name="reject_remarks4" id="reject_remarks4" value="0"/> 
		 </form:form>

 




