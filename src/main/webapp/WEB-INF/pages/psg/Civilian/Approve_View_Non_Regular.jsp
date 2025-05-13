<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>



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
			<div class="card-header">
				<h5>Approve Non Regular Employee</h5>
				<h6 class="enter_by">
				</h6>
			</div>
			<div class="card-body card-block">
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"> Authority </label>
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
								<label>${listfull[0].dt_of_authority}</label>
							</div>
						</div>
					</div>
				</div>
				
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label">Unit Sus No. </label>
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
								<label class=" form-control-label"> Employee No. </label>
							</div>
							<div class="col-md-8">
								<label>${listfull[0].employee_no}</label>
							</div>
						</div>
					</div>
					
						
						<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"> Mobile NO. </label>
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
								<label class=" form-control-label"><strong
									style="color: red;"> </strong> First Name</label>
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
									style="color: red;"> </strong> Middle Name</label>
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
									style="color: red;"> </strong> Last Name</label>
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
								<label>${listfull[0].dob}</label>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"> Gender</label>
							</div>
							<div class="col-md-8">
								<label>${listfull[0].gender}</label>
							</div>
						</div>
					</div>
				</div>
				
				<div class="col-md-12" id="gender_other">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label">Gender Other</label>
							</div>
							<div class="col-md-8">
								<label>${listfull[0].gender_other}</label>
							</div>
						</div>
					</div>
				</div>

				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label">Category</label>
							</div>
							<div class="col-md-8">
								<label>${listfull[0].category_belongs}</label>
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

				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label">Pay Head</label>
							</div>
							<div class="col-md-8">
								<label>${listfull[0].pay_level}</label>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label">Date of Joining </label>
							</div>
							<div class="col-md-8">
								<label>${listfull[0].joining_date_gov_service}</label> <input
									class=" form-control" type="hidden" id="date_of_joining"
									name="joining_date_gov_service" onChange="">
							</div>
						</div>
					</div>
				</div>

				<div class="col-md-12" id="pay_head_other">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label">Pay Head Other</label>
							</div>
							<div class="col-md-8">
								<label>${listfull[0].pay_level_other}</label>
							</div>
						</div>
					</div>
				</div>

				<div class="col-md-12">
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
				
				<div class="col-md-12" id="religion_other">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label">Religion Other</label>
							</div>
							<div class="col-md-8">
								<label>${listfull[0].religion_other}</label>
							</div>
						</div>
					</div>
				</div>
				
				<div class="col-md-12" id="mother_tongue_other">
					<div class="col-md-6">
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
				
				<div class="col-md-12">
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
									style="color: red;"> </strong> PAN No. </label>
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
						<div class="row form-group">
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
								<label class=" form-control-label"><strong
									style="color: red;"> </strong> State </label>
							</div>
							<div class="col-md-8">
								<label>${listfull[0].state_original}</label>
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
								<label>${listfull[0].district_original}</label>
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
				</div>
				<!-- other original domicile -->
				<div class="col-md-12">
					<div class="col-md-4" id="country_other">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"><strong
									style="color: red;"> </strong> Country Other </label>
							</div>
							<div class="col-md-8">
								<label>${listfull[0].original_country_other}</label>

							</div>

						</div>
					</div>
					<div class="col-md-4" id="state_other">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"><strong
									style="color: red;"> </strong> State Other </label>
							</div>
							<div class="col-md-8">
								<label>${listfull[0].original_state_other}</label>

							</div>

						</div>
					</div>
					<div class="col-md-4" id="district_other">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"><strong
									style="color: red;"> </strong> District Other</label>
							</div>
							<div class="col-md-8">
								<label>${listfull[0].original_district_other}</label>

							</div>


						</div>
					</div>

				</div>
				<div class="col-md-12" id="tehshil_other">
					<div class="col-md-4">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"><strong
									style="color: red;"> </strong> Tehisl Other</label>
							</div>
							<div class="col-md-8">
								<label>${listfull[0].original_tehshil_other}</label>
							</div>
						</div>
					</div>
				</div>
				<!-- end otehr domicile -->

				<div class="card-header-inner" id="aa"
					style="margin-left: 20px; margin-bottom: 20px;">
					<strong>PRESENT DOMICILE</strong>
				</div>

				<div class="col-md-12">
					<div class="col-md-4">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"><strong
									style="color: red;"> </strong> Country</label>
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
				<!-- present domicile other-->

				<div class="col-md-12">
					<div class="col-md-4" id="pre_other_country">
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
					<div class="col-md-4" id="pre_other_state">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"><strong
									style="color: red;"> </strong> State Other</label>
							</div>
							<div class="col-md-8">
								<label>${listfull[0].present_state_other}</label>

							</div>


						</div>
					</div>
					<div class="col-md-4" id="pre_other_district">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"><strong
									style="color: red;"> </strong> District Other</label>
							</div>
							<div class="col-md-8">
								<label>${listfull[0].present_district_other}</label>

							</div>


						</div>
					</div>
				</div>


				<!-- end present domicile -->
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

				<!-- pre tehshil other-->
				<div class="col-md-12">

					<div class="col-md-4" id="pre_other_tehsil">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"><strong
									style="color: red;"> </strong> Tehsil Other </label>
							</div>
							<div class="col-md-8">
								<label>${listfull[0].present_tehshil_other}</label>

							</div>
						</div>
					</div>
					<div class="col-md-4" id="pre_nationality_other">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"><strong
									style="color: red;"> </strong> Nationality Other </label>
							</div>
							<div class="col-md-8">
								<label>${listfull[0].nationality_other}</label>
							</div>
						</div>
					</div>
				</div>

				<!-- end -->
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
                <input type="date" id="image_updated_date" name="image_updated_date" style="display: none">
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
								<label class=" form-control-label"><strong
									style="color: red;"> </strong> Non Effective </label>
							</div>
							<div class="col-md-8">
								<label>No</label>
							</div>
						</div>
					</div>
				</div>

				<div class="col-md-12" id="none_effective">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"><strong
									style="color: red;"> </strong> Non Effective </label>
							</div>
							<div class="col-md-8">
								<label>${listfull[0].non_effective}</label>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"><strong
									style="color: red;"> </strong> Non Effective Date </label>
							</div>
							<div class="col-md-8">
								<label>${listfull[0].date_non_effective}</label>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="card-footer" align="center" class="form-control">
				<a href="Search_civilian_non_regular" class="btn btn-info btn-sm">Back</a>
				
			<input type="hidden" id="id" name="id" class="form-control autocomplete" autocomplete="off"
			value="${listfull[0].id}">
			<input type="button" class="btn btn-primary btn-sm" value="Print" onclick="Print_Pdf();"> 
			
		</div>
	</div>
</div>

<script>

 $(document).ready(function() {
	 var id = '${civilian_details_cmd.id}'		
			$('#identity_image_preview').attr("src", "regularCivIdentityConvertpath?i_id="+id+" ");
	
		if ('${listfull[0].gender_other}' == "") {
			$("#gender_other").hide();
		}
		if ('${listfull[0].religion_other}' == "") {
			$("#religion_other").hide();
		}
		if ('${listfull[0].mother_tongue_other}' == "") {
			$("#mother_tongue_other").hide();
		}
		if ('${listfull[0].original_country_other}' == "") {
			$("#country_other").hide();
		}
		if ('${listfull[0].original_state_other}' == "") {
			$("#state_other").hide();
		}

		if ('${listfull[0].original_district_other}' == "") {
			$("#district_other").hide();
		}
		if ('${listfull[0].original_tehshil_other}' == "") {
			$("#tehshil_other").hide();
		}
		if ('${listfull[0].present_country_other}' == "") {
			$("#pre_other_country").hide();
		}
		if ('${listfull[0].present_state_other}' == "") {
			$("#pre_other_state").hide();
		}
		if ('${listfull[0].present_district_other}' == "") {
			$("#pre_other_district").hide();
		}
		if ('${listfull[0].present_tehshil_other}' == "") {
			$("#pre_other_tehsil").hide();
		}
		if ('${listfull[0].nationality_other}' == "") {
			$("#pre_nationality_other").hide();
		}
		if ('${listfull[0].non_effective}' == "") {
			$("#none_effective").hide();
			$("#none_effective_no").show();
		} else {
			$("#none_effective").show();
			$("#none_effective_no").hide();
		}
		if ('${listfull[0].pay_level_other}' == "") {
			$("#pay_head_other").hide();

		}
	});
 
 
 function Print_Pdf(){	

		
	 $("#id1").val($('#id').val());
	document.getElementById('printForm').submit(); 
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


<c:url value="Print_non_regular" var="Print_non_regular" />
<form:form action="${Print_non_regular}" method="post" id="printForm"
	name="printForm">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>



