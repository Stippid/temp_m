
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="Stylesheet" href="js/Calender/jquery-ui.css">
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>
 <link rel="stylesheet" href="js/cue/cueWatermark.css">

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>



 <style>
     
        
       
        .modal {
          display: none; /* Hidden by default */
          position: fixed; /* Stay in place */
          z-index: 1; /* Sit on top */
          padding-top: 100px; /* Location of the box */
          left: 0;
          top: 0;
          width: 100%; /* Full width */
          height: 100%; /* Full height */
          overflow: auto; /* Enable scroll if needed */
          background-color: rgb(0,0,0); /* Fallback color */
          background-color: rgba(0,0,0,0.9); /* Black w/ opacity */
        }
        
        /* Modal Content (image) */
        .modal-content {
          margin: auto;
          display: block;
          width: 90%;
          max-width: 1000px;
        }
        
        /* Caption of Modal Image */
        #caption {
          margin: auto;
          display: block;
          width: 80%;
          max-width: 700px;
          text-align: center;
          color: #ccc;
          padding: 10px 0;
          height: 150px;
        }
        
        /* Add Animation */
        .modal-content, #caption {  
          -webkit-animation-name: zoom;
          -webkit-animation-duration: 0.6s;
          animation-name: zoom;
          animation-duration: 0.6s;
        }
        
        @-webkit-keyframes zoom {
          from {-webkit-transform:scale(0)} 
          to {-webkit-transform:scale(1)}
        }
        
        @keyframes zoom {
          from {transform:scale(0)} 
          to {transform:scale(1)}
        }
        
        /* The Close Button */
        .close {
          position: absolute;
          top: 15px;
          right: 35px;
          color: #f1f1f1;
          font-size: 40px;
          font-weight: bold;
          transition: 0.3s;
        }
        
        .close:hover,
        .close:focus {
          color: #bbb;
          text-decoration: none;
          cursor: pointer;
        }
        
        /* 100% Image Width on Smaller Screens */
        @media only screen and (max-width: 700px){
          .modal-content {
            width: 100%;
          }
        }
        </style>

<div class="container" align="center">
	<div class="card">
		<div class="card-header">
			<h5>
				<span id="lbdad">CAPTURE</span> PREVENTIVE DETAILS
			</h5>
			<h6>
				<span style="font-size: 12px; color: red">(To be entered by
					SHO/FHO)</span>
			</h6>
		</div>
		<div class="card-header"
			style="border: 1px solid rgba(0, 0, 0, .125); text-align: center;"
			id="aa">
			<strong>Preventive Details</strong>
		</div>
		<form:form name="add_prvnt_details" id="add_prvnt_details" action=""
			method='POST' commandName="add_prvnt_details_cmnd"
			enctype="multipart/form-data">


			<div class="card" id="actsho"
				style="display: none; margin-bottom: 0; border: 0;">
				<div class="card-body card-block">
					<div class="row">
						<div class="col-md-12">
							<div class="col-md-8">
								<div class="row form-group">
									<div class="col-md-3">
										<label for="text-input" class=" form-control-label"><strong
											style="color: red;">*</strong> SHO Name </label>
									</div>
									<div class="col-md-9">
										<input type="hidden" id="id" name="id" value="0"
											class="form-control-sm form-control" autocomplete="off">
										<input type="text" id="unit_name1" name="sho_name"
											value="${add_prvnt_details_cmnd.sho_name}"
											readonly="readonly" class="form-control-sm form-control"
											autocomplete="off">
									</div>
								</div>
							</div>

							<div class="col-md-4">
								<div class="row form-group">
									<div class="col-md-4">
										<label for="text-input" class=" form-control-label"><strong
											style="color: red;">*</strong> SUS No </label>
									</div>
									<div class="col-md-8">
										<input type="text" id="sus_no1" name="sus_no"
											value="${add_prvnt_details_cmnd.sus_no}" readonly="readonly"
											class="form-control-sm form-control" autocomplete="off">
									</div>
								</div>
							</div>
						</div>

						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label for="text-input" class=" form-control-label"><strong
											style="color: red;">*</strong> Month </label>
									</div>
									<div class="col-md-8">
										<input type="text" id="month" name="month"
											value="${add_prvnt_details_cmnd.month}" readonly="readonly"
											class="form-control-sm form-control" autocomplete="off">										
									</div>
								</div>
							</div>

							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label for="text-input" class=" form-control-label"><strong
											style="color: red;">*</strong> Year </label>
									</div>
									<div class="col-md-8">
										<input type="text" id="year" name="year"
											value="${add_prvnt_details_cmnd.year}" readonly="readonly"
											class="form-control-sm form-control" autocomplete="off"
											onkeypress="return isNumber0_9Only(event)"
											onload="ParseDateColumn()">
									</div>
								</div>
							</div>
						</div>

						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label for="text-input" class=" form-control-label"><strong
											style="color: red;">*</strong> Location </label>
									</div>
									<div class="col-md-8">
										<input type="text" id="location" name="location"
											value="${add_prvnt_details_cmnd.location}"
											class="form-control-sm form-control" autocomplete="off">
									</div>
								</div>
							</div>
						</div>

					</div>

					<div class="card-header-inner" id="aa">
						<strong>Prevention From Mosquitoes</strong>
					</div>


					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label"><strong
										style="color: red;">* </strong>Date From</label>
								</div>
								<div class="col-md-8">
									<input type="date" id="date_from" name="date_from"
										class="form-control-sm form-control"
										onchange="CheckDate(this.value);" autocomplete="off"
										min="1899-01-01" max="${date}">
								</div>
								
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label"> <strong
										style="color: red;">* </strong>Date To
									</label>
								</div>
								<div class="col-md-8">
									<input type="date" id="date_to" name="date_to" 
										class="form-control-sm form-control" min="1899-01-01"
										max="${date}">
								</div>
								
							</div>
						</div>
					</div>



					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class="form-control-label"><strong
										style="color: red;">*</strong> Antilarval Measure Undertaken</label>
								</div>
								<div class="col-md-8">
									<select id="antilarval_measure_undertaken"
										name="antilarval_measure_undertaken"
										class="form-control-sm form-control">
										<option value="yes">Yes</option>
										<option value="no">No</option>
									</select>
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label">
										Residual Spray Done</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="residual_spray_done"
										name="residual_spray_done"
										value="${add_prvnt_details_cmnd.residual_spray_done}"
										class="form-control-sm form-control" placeholder="0"
										autocomplete="off" onkeypress="return isNumber0_9Only(event);">
								</div>
							</div>
						</div>
					</div>




					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label">
										Entomonological Survey Done </label>
								</div>
								<div class="col-md-8">
									<input type="text" id="entomonological_survey_done"
										name="entomonological_survey_done"
										value="${add_prvnt_details_cmnd.entomonological_survey_done}"
										class="form-control-sm form-control" placeholder="0"
										autocomplete="off" onkeypress="return isNumber0_9Only(event);">
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label">
										Units Inspected</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="units_inspected" name="units_inspected"
										value="${add_prvnt_details_cmnd.units_inspected}"
										class="form-control-sm form-control" autocomplete="off"
										placeholder="0" onkeypress="return isNumber0_9Only(event);">
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label">
										Sanitary Fittings Checked</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="sanitary_fittings_checked"
										name="sanitary_fittings_checked"
										value="${add_prvnt_details_cmnd.sanitary_fittings_checked}"
										class="form-control-sm form-control" autocomplete="off"
										placeholder="0" onkeypress="return isNumber0_9Only(event);">
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="card-header"
					style="border: 1px solid rgba(0, 0, 0, .125); text-align: center;"
					id="aa">
					<strong>Water Quality</strong>
				</div>

				<div class="card-body card-block">
					<div class="row">
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label for="text-input" class=" form-control-label"> <strong
											style="color: red;">* </strong> Number Of Free Chlorine Check
										</label>
									</div>
									<div class="col-md-8">
										<input type="text" id="number_of_free_chlorine_ckeck"
											name="number_of_free_chlorine_ckeck"
											value="${add_prvnt_details_cmnd.number_of_free_chlorine_ckeck}"
											class="form-control-sm form-control" placeholder="0"
											autocomplete="off"
											onkeypress="return isNumber0_9Only(event);">
									</div>
								</div>
							</div>

							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-6">
										<label class=" form-control-label"><strong
											style="color: red;">* </strong> Unsatisfactory %</label>
									</div>
									<div class="col-md-6">
										<input type="text" id="chl_unsatisfactory"
											name="chl_unsatisfactory"
											value="${add_prvnt_details_cmnd.chl_unsatisfactory}"
											class="form-control-sm form-control" autocomplete="off"
											placeholder="0" onkeypress="return isNumber0_9Only(event);">
									</div>
								</div>
							</div>
						</div>

						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-6">
										<label for="text-input" class=" form-control-label"><strong
											style="color: red;">* </strong>Number Of water samples sent
											for Bacteriological Exam </label>
									</div>
									<div class="col-md-6">
										<input type="text"
											id="number_of_water_samples_sent_for_bacteriological_exam"
											name="number_of_water_samples_sent_for_bacteriological_exam"
											class="form-control-sm form-control" autocomplete="off"
											value="${add_prvnt_details_cmnd.number_of_water_samples_sent_for_bacteriological_exam}"
											placeholder="0" onkeypress="return isNumber0_9Only(event);">
									</div>
								</div>
							</div>

							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-6">
										<label class=" form-control-label"><strong
											style="color: red;">* </strong>Unsatisfactory % </label>
									</div>
									<div class="col-md-6">
										<input type="text" id="bact_unsatisfactory"
											name="bact_unsatisfactory"
											value="${add_prvnt_details_cmnd.bact_unsatisfactory}"
											class="form-control-sm form-control" placeholder="0"
											autocomplete="off"
											onkeypress="return isNumber0_9Only(event);">
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
		</form:form>
		<form id="milk_quality_form">

			<div class="card-header"
				style="border: 1px solid rgba(0, 0, 0, .125); text-align: center;"
				id="aa">
				<strong>Milk Quality</strong>
			</div>


			<!-- ----------MILK GRID------- -->
			<div class="card-body card-block" id="milk_total_table">
				<table
					class="table no-margin table-striped  table-hover  table-bordered report_print "
					id="milkaddQuantity">
					<thead style="color: white; text-align: center;">
						<tr>
							<th align="center">Ser No</th>
							<th>Sample Number</th>
							<th>Specific Gravity</th>
							<th>Fat Content</th>
							<th>Total Solids</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody id="milkaddQuantitytbody">

						<c:if test="${listMilkQualityDetails.size() == 0}">
							<tr id="">
								<td align="center">1</td>
								<td align="center"><input type="text"
									name="milk_samplenum1" id="milk_samplenum1" maxlength="40"
									class="form-control" autocomplete="off" /></td>
								<td align="center"><select id="milk_spec_gravity1"
									name="milk_spec_gravity1" class="form-control-sm form-control">
										<option value="0">--Select--</option>
										<option value="1">Satisfactory</option>
										<option value="2">Non Satisfactory</option>
								</select></td>

								<td align="center"><select id="milk_fat_con1"
									name="milk_fat_con1" class="form-control-sm form-control">
										<option value="0">--Select--</option>
										<option value="1">Satisfactory</option>
										<option value="2">Non Satisfactory</option>
								</select></td>

								<td align="center"><select id="milk_tot_solid1"
									name="milk_tot_solid1" class="form-control-sm form-control">
										<option value="0">--Select--</option>
										<option value="1">Satisfactory</option>
										<option value="2">Non Satisfactory</option>
								</select></td>



								<!--  			<td align="center"> -->
								<td id="c_milk_idtd1" style="display: none;"><input
									type="text" id="c_milk_id1" name="c_milk_id1"
									class="form-control-sm form-control" value="0"
									autocomplete="off"></td>
								<td align="center"><i class="fa fa-plus"
									onclick="milkformopen(1);" id="milk_id_add1" value="ADD"></i>
									</a></td>
							</tr>
						</c:if>

						<c:if test="${listMilkQualityDetails.size() != 0}">
							<c:forEach var="item" items="${listMilkQualityDetails}"
								varStatus="num">
								<tr id="malaria_tr_id${num.index+1}">
									<td style="text-align: center;">${num.index+1}</td>
									<td>${item[0]}</td>

									<td>${item[1]}</td>

									<td>${item[2]}</td>

									<td>${item[3]}</td>




									<td style="display: none;">${item[4]}</td>
									<td style="text-align: center;">${item[5]}</td>
								</tr>
							</c:forEach>
						</c:if>

					</tbody>
				</table>
			</div>
			<div>
				<input type="hidden" id="milk_count" name="milk_count"> <input
					type="hidden" id="milk_oldcount" name="milk_oldcount"> <input
					style="float: right; margin: 5px" type="button"
					class="btn btn-success btn-sm" value="UPDATE"
					onclick="milkqualityformsubmit();">
			</div>
		</form>
		<!-- MILK GRID END--->
		<form:form id="factories_inspectedForm">
			<div class="card-header"
				style="border: 1px solid rgba(0, 0, 0, .125); text-align: center;"
				id="aa">
				<strong>Factories Inspected</strong>
			</div>

			<div class="card-body card-block">
				<div class="row">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-6">
									<label for="text-input" class=" form-control-label">
										Initial inspection </label>
								</div>
								<div class="col-md-6">
									<input type="text" id="initial_inspection"
										name="initial_inspection"
										value="${add_prvnt_details_cmnd.initial_inspection}"
										class="form-control-sm form-control" autocomplete="off"
										placeholder="0" onkeypress="return isNumber0_9Only(event);">
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-6">
									<label for="text-input" class=" form-control-label">
										Periodic inspection </label>
								</div>
								<div class="col-md-6">
									<input type="text" id="periodic_inspection"
										name="periodic_inspection"
										value="${add_prvnt_details_cmnd.periodic_inspection}"
										class="form-control-sm form-control" autocomplete="off"
										placeholder="0" onkeypress="return isNumber0_9Only(event);">
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="card-header"
				style="border: 1px solid rgba(0, 0, 0, .125); text-align: center;"
				id="aa">
				<strong>Malaria Surveillance</strong>
			</div>

			<div class="card-body card-block">
				<div class="row">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-6">
									<label for="text-input" class=" form-control-label">
										Number Of Slides Checked</label>
								</div>
								<div class="col-md-6">
									<input type="text" id="malaria_number_of_slides_checked"
										name="malaria_number_of_slides_checked"
										value="${add_prvnt_details_cmnd.malaria_number_of_slides_checked}"
										class="form-control-sm form-control" autocomplete="off"
										placeholder="0" onkeypress="return isNumber0_9Only(event);">
								</div>
							</div>
						</div>
					</div>
				</div>


				<div class="row">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-12">
									<label for="text-input" class=" form-control-label">
										Number Of Slides Found Positive</label>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-12">

						<div class="row form-group">
							<div class="col-md-12">
								<table id=""
									class="table no-margin table-striped  table-hover  table-bordered report_print"
									width="100%">

									<thead>
										<tr>
											<th></th>
											<th>OFFICERS</th>
											<th>JCOs</th>
											<th>ORs</th>
											<th>FAMILY</th>
											<th>TOTAL</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>P Vivax</td>
											<td><input size=2 type='text'
												name='malaria_p_vivax_officers'
												id='malaria_p_vivax_officers'
												value="${add_prvnt_details_cmnd.malaria_p_vivax_officers}"
												class="form-control-sm form-control" autocomplete="off"
												placeholder="0" onkeypress="return isNumber0_9Only(event);"
												onchange="Number_Of_Slides_Found_Positive()"></td>
											<td><input size=2 type='text'
												name='malaria_p_vivax_jcos'
												value="${add_prvnt_details_cmnd.malaria_p_vivax_jcos}"
												id='malaria_p_vivax_jcos'
												class="form-control-sm form-control" autocomplete="off"
												placeholder="0" onkeypress="return isNumber0_9Only(event);"
												onchange="Number_Of_Slides_Found_Positive()"></td>
											<td><input size=2 type='text' name='malaria_p_vivax_ors'
												value="${add_prvnt_details_cmnd.malaria_p_vivax_ors}"
												id='malaria_p_vivax_ors'
												class="form-control-sm form-control" autocomplete="off"
												placeholder="0" onkeypress="return isNumber0_9Only(event);"
												onchange="Number_Of_Slides_Found_Positive()"></td>
											<td><input size=2 type='text'
												value="${add_prvnt_details_cmnd.malaria_p_vivax_family}"
												name='malaria_p_vivax_family' id='malaria_p_vivax_family'
												class="form-control-sm form-control" autocomplete="off"
												placeholder="0" onkeypress="return isNumber0_9Only(event);"
												onchange="Number_Of_Slides_Found_Positive()"></td>
											<td><input size=2 type='text' name=''
												id='slides_vivax_total' class="form-control-sm form-control"
												autocomplete="off" placeholder="0"
												onkeypress="return isNumber0_9Only(event);"
												readonly="readonly"
												onchange="Number_Of_Slides_Found_Positive()"></td>

										</tr>
										<tr>
											<td>P Falciparum</td>
											<td><input size=2 type='text'
												name='malaria_p_falciparum_officers'
												value="${add_prvnt_details_cmnd.malaria_p_falciparum_officers}"
												id='malaria_p_falciparum_officers'
												class="form-control-sm form-control" placeholder="0"
												autocomplete="off"
												onkeypress="return isNumber0_9Only(event);"
												onchange="Number_Of_Slides_Found_Positive()"></td>
											<td><input size=2 type='text'
												value="${add_prvnt_details_cmnd.malaria_p_falciparum_jcos}"
												name='malaria_p_falciparum_jcos'
												id='malaria_p_falciparum_jcos'
												class="form-control-sm form-control" placeholder="0"
												autocomplete="off"
												onkeypress="return isNumber0_9Only(event);"
												onchange="Number_Of_Slides_Found_Positive()"></td>
											<td><input size=2 type='text'
												value="${add_prvnt_details_cmnd.malaria_p_falciparum_ors}"
												name='malaria_p_falciparum_ors'
												id='malaria_p_falciparum_ors'
												class="form-control-sm form-control" placeholder="0"
												autocomplete="off"
												onkeypress="return isNumber0_9Only(event);"
												onchange="Number_Of_Slides_Found_Positive()"></td>
											<td><input size=2 type='text'
												name='malaria_p_falciparum_family'
												value="${add_prvnt_details_cmnd.malaria_p_falciparum_family}"
												id='malaria_p_falciparum_family'
												class="form-control-sm form-control" placeholder="0"
												autocomplete="off"
												onkeypress="return isNumber0_9Only(event);"
												onchange="Number_Of_Slides_Found_Positive()"></td>
											<td><input size=2 type='text' name=''
												id='slides_falciparum_total'
												class="form-control-sm form-control" placeholder="0"
												autocomplete="off"
												onkeypress="return isNumber0_9Only(event);"
												readonly="readonly"
												onchange="Number_Of_Slides_Found_Positive()"></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						
					</div>

				</div>
			</div>
			<div class="card-header"
				style="border: 1px solid rgba(0, 0, 0, .125); text-align: center;"
				id="aa">
				<strong>Filaria Surveillance</strong>
			</div>

			<div class="card-body card-block">
				<div class="row">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-6">
									<label for="text-input" class=" form-control-label">
										Number Of Slides Checked</label>
								</div>
								<div class="col-md-6">
									<input type="hidden" id=id_hid name="id_hid"> <input
										type="text" id="filaria_number_of_slides_checked"
										name="filaria_number_of_slides_checked"
										class="form-control-sm form-control" placeholder="Search..."
										autocomplete="off"
										value="${add_prvnt_details_cmnd.filaria_number_of_slides_checked}"
										title="Type Unit Name or Part of Unit Name to Search"
										maxlength="100">
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-12">
									<label for="text-input" class=" form-control-label">
										Number Of Slides Found Positive</label>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-12">

						<div class="row form-group">
							<div class="col-md-12">
								<table id=""
									class="table no-margin table-striped  table-hover  table-bordered report_print">

									<thead>
										<tr>
											<th>OFFICERS</th>
											<th>JCOs</th>
											<th>ORs</th>
											<th>FAMILY</th>
											<th>TOTAL</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td><input size=2 type='text' name='filaria_officers'
												value="${add_prvnt_details_cmnd.filaria_officers}"
												id='filaria_officers' class="form-control-sm form-control"
												autocomplete="off" placeholder="0"
												onkeypress="return isNumber0_9Only(event);"
												onchange="P_Falciparum()"></td>
											<td><input size=2 type='text' name='filaria_jcos'
												id='filaria_jcos'
												value="${add_prvnt_details_cmnd.filaria_jcos}"
												class="form-control-sm form-control" autocomplete="off"
												placeholder="0" onkeypress="return isNumber0_9Only(event);"
												onchange="P_Falciparum()"></td>
											<td><input size=2 type='text' name='filaria_ors'
												id='filaria_ors'
												value="${add_prvnt_details_cmnd.filaria_ors}"
												class="form-control-sm form-control" autocomplete="off"
												placeholder="0" onkeypress="return isNumber0_9Only(event);"
												onchange="P_Falciparum()"></td>
											<td><input size=2 type='text' name='filaria_family'
												id='filaria_family'
												value="${add_prvnt_details_cmnd.filaria_family}"
												class="form-control-sm form-control" autocomplete="off"
												placeholder="0" onkeypress="return isNumber0_9Only(event);"
												onchange="P_Falciparum()"></td>
											<td><input size=2 type='text' name=''
												id='slides_filaria_total'
												class="form-control-sm form-control" autocomplete="off"
												placeholder="0" onkeypress="return isNumber0_9Only(event);"
												readonly="readonly" onchange="P_Falciparum()"></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>

					</div>

				</div>
			</div>



			<div class="card-header"
				style="border: 1px solid rgba(0, 0, 0, .125); text-align: center;"
				id="aa">
				<strong>Immunisation</strong>
			</div>

			<div class="card-body card-block">
				<div class="row">
					<div class="col-md-12 row form-group">
						<div class="col-md-2" style="text-align: left;">
							<label for="text-input" class=" form-control-label"> BCG</label>
						</div>
						<div class="col-md-2">
							<input type="text" id="immunisation_bcg" name="immunisation_bcg"
								value="${add_prvnt_details_cmnd.immunisation_bcg}"
								class="form-control-sm form-control" autocomplete="off"
								placeholder="0" onkeypress="return isNumber0_9Only(event);">
						</div>
						<div class="col-md-2" style="text-align: left;">
							<label for="text-input" class=" form-control-label">
								Polio</label>
						</div>
						<div class="col-md-2">
							<input type="text" id="immunisation_polio"
								name="immunisation_polio"
								value="${add_prvnt_details_cmnd.immunisation_polio}"
								class="form-control-sm form-control" autocomplete="off"
								placeholder="0" onkeypress="return isNumber0_9Only(event);">
						</div>
						<div class="col-md-2" style="text-align: left;">
							<label for="text-input" class=" form-control-label">
								Measles</label>
						</div>
						<div class="col-md-2">
							<input type="text" id="immunisation_measles"
								name="immunisation_measles"
								value="${add_prvnt_details_cmnd.immunisation_measles}"
								class="form-control-sm form-control" autocomplete="off"
								placeholder="0" onkeypress="return isNumber0_9Only(event);">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12 row form-group">
						<div class="col-md-2" style="text-align: left;">
							<label for="text-input" class=" form-control-label"> DPT</label>
						</div>
						<div class="col-md-2">
							<input type="text" id="immunisation_dpt" name="immunisation_dpt"
								value="${add_prvnt_details_cmnd.immunisation_dpt}"
								class="form-control-sm form-control" autocomplete="off"
								placeholder="0" onkeypress="return isNumber0_9Only(event);">
						</div>
						<div class="col-md-2" style="text-align: left;">
							<label for="text-input" class=" form-control-label">
								Hepatitis B</label>
						</div>
						<div class="col-md-2">
							<input type="text" id="immunisation_hepatitis_b"
								name="immunisation_hepatitis_b"
								value="${add_prvnt_details_cmnd.immunisation_hepatitis_b}"
								class="form-control-sm form-control" autocomplete="off"
								placeholder="0" onkeypress="return isNumber0_9Only(event);">
						</div>
						<div class="col-md-2" style="text-align: left;">
							<label for="text-input" class=" form-control-label"> DT</label>
						</div>
						<div class="col-md-2">
							<input type="text" id="immunisation_dt" name="immunisation_dt"
								value="${add_prvnt_details_cmnd.immunisation_dt}"
								class="form-control-sm form-control" autocomplete="off"
								placeholder="0" onkeypress="return isNumber0_9Only(event);">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12 row form-group">
						<div class="col-md-2" style="text-align: left;">
							<label for="text-input" class=" form-control-label"> TT</label>
						</div>
						<div class="col-md-2">
							<input type="text" id="immunisation_tt" name="immunisation_tt"
								value="${add_prvnt_details_cmnd.immunisation_tt}"
								class="form-control-sm form-control" autocomplete="off"
								placeholder="0" onkeypress="return isNumber0_9Only(event);">
						</div>
					</div>
				</div>
			</div>
			
			<div class="card-header"
				style="border: 1px solid rgba(0, 0, 0, .125); text-align: center;"
				id="aa">
				<strong>Special Campaigns</strong>
			</div>
			<div class="card-body card-block">
				<div class="row">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-6">
									<label for="text-input" class=" form-control-label"><strong
										style="color: red;">*</strong> Pulse Polio</label>
								</div>
								<div class="col-md-6">
									<select id="special_pulse_polio" name="special_pulse_polio"
										class="form-control-sm form-control" autocomplete="off">
										<option value="0">Yes</option>
										<option value="1">No</option>
										<!-- 	<option value="2">2</option> -->
									</select>
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-6">
									<label for="text-input" class=" form-control-label">
										Number Vaccinated</label>
								</div>
								<div class="col-md-6">
									<input type="text" id="special_number_vaccinated"
										name="special_number_vaccinated" placeholder="0"
										value="${add_prvnt_details_cmnd.special_number_vaccinated}"
										class="form-control-sm form-control" autocomplete="off"
										onkeypress="return isNumber0_9Only(event);">
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-6">
									<label for="text-input" class=" form-control-label"><strong
										style="color: red;">*</strong> Other Campaigns</label>
								</div>
								<div class="col-md-6">
									<select id="special_other_campaigns"
										name="special_other_campaigns"
										class="form-control-sm form-control" autocomplete="off">
										<option value="0">Yes</option>
										<option value="1">No</option>
										
									</select>
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-6">
									<label for="text-input" class=" form-control-label">
										Details</label>
								</div>
								<div class="col-md-6">
									<textarea id="special_details" name="special_details"
										value="${add_prvnt_details_cmnd.special_details}"
										maxlength="250" class="form-control-sm form-control">
								</textarea>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="card-header"
				style="border: 1px solid rgba(0, 0, 0, .125); text-align: center;"
				id="aa">
				<strong>Courses</strong>
			</div>

			<div class="card-body card-block">
				<div class="row">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-6">
									<label for="text" class=" form-control-label"> Hygiene
										and Sanitation</label>
								</div>
								<div class="col-md-6">
									<input type="text" id="cources_hygiene_and_sanitation"
										name="cources_hygiene_and_sanitation"
										value="${add_prvnt_details_cmnd.cources_hygiene_and_sanitation}"
										class="form-control-sm form-control" autocomplete="off"
										placeholder="0" onkeypress="return isNumber0_9Only(event);">
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-6">
									<label for="text-input" class=" form-control-label">
										Vector Control</label>
								</div>
								<div class="col-md-6">
									<input type="text" id="cources_vector_control"
										name="cources_vector_control"
										class="form-control-sm form-control" autocomplete="off"
										value="${add_prvnt_details_cmnd.cources_vector_control}"
										placeholder="0" onkeypress="return isNumber0_9Only(event);">
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-6">
									<label for="text-input" class=" form-control-label">
										Water Supply</label>
								</div>
								<div class="col-md-6">
									<input type="text" id="cources_water_supply"
										name="cources_water_supply"
										value="${add_prvnt_details_cmnd.cources_water_supply}"
										class="form-control-sm form-control" autocomplete="off"
										placeholder="0" onkeypress="return isNumber0_9Only(event);">
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>


			<div class="card-header"
				style="border: 1px solid rgba(0, 0, 0, .125); text-align: center;"
				id="aa">
				<strong>Training</strong>
			</div>
			<div class="card-body card-block">
				<div class="row">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-6">
									<label for="text-input" class=" form-control-label">Post
										Graduate Training</label>
								</div>
								<div class="col-md-6">
									<input type="text" id="training_post_graduate_training"
										name="training_post_graduate_training"
										class="form-control-sm form-control" autocomplete="off"
										value="${add_prvnt_details_cmnd.training_post_graduate_training}"
										placeholder="0" onkeypress="return isNumber0_9Only(event);">
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-6">
									<label class=" form-control-label">Internee Officer
										Training</label>
								</div>
								<div class="col-md-6">
									<input type="text" id="training_internee_officer_training"
										value="${add_prvnt_details_cmnd.training_internee_officer_training}"
										name="training_internee_officer_training"
										class="form-control-sm form-control" autocomplete="off"
										placeholder="0" onkeypress="return isNumber0_9Only(event);">
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-6">
									<label for="text-input" class=" form-control-label">Health
										Assistance Training </label>
								</div>
								<div class="col-md-6">
									<input type="text" id="training_health_assistance_training"
										name="training_health_assistance_training"
										value="${add_prvnt_details_cmnd.training_health_assistance_training}"
										class="form-control-sm form-control" autocomplete="off"
										placeholder="0" onkeypress="return isNumber0_9Only(event);">
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-6">
									<label class=" form-control-label">Hygiene & Sanitation
										Squad Training</label>
								</div>
								<div class="col-md-6">
									<input type="text"
										id="training_hygiene_sanitation_squad_training"
										name="training_hygiene_sanitation_squad_training"
										value="${add_prvnt_details_cmnd.training_hygiene_sanitation_squad_training}"
										class="form-control-sm form-control" autocomplete="off"
										placeholder="0" onkeypress="return isNumber0_9Only(event);">
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form:form>
		<form id="health_total_table_form" enctype="multipart/form-data" >
			<div class="card-header"
				style="border: 1px solid rgba(0, 0, 0, .125); text-align: center;"
				id="aa">
				<strong>Health Education</strong>
			</div>




			<!-- ----------HEALTH EDUCATION GRID------- -->
			<div class="card-body card-block" id="health_total_table">
				<table
					class="table no-margin table-striped  table-hover  table-bordered report_print "
					id="healthaddQuantity">
					<thead style="color: white; text-align: center;">
						<tr>
							<th align="center" width="5%">Ser No</th>
							<th align="center" width="10%">Topic</th>
							<th align="center" width="10%">Unit Name</th>
							<th align="center" width="15%">Personal Category</th>
							<th align="center" width="10%"> Number Present</th>
							<th align="center" width="10%">Remarks</th>
							<th  width="20%">Photographs</th>
							<th  width="10%">View Photograph</th>
							<th align="center" width="10%">Action</th>
						</tr>
					</thead>
					<tbody>
					<c:if test="${listhealtheduDetails.size() == 0}">
						<tr id="">
							<td align="center" width="5%">1</td>
							<td align="center" width="10%"><input type="text" name="health_topic1"
								id="health_topic1" maxlength="40"
								class="form-control-sm form-control" autocomplete="off" /></td>
							<td align="center" width="10%"><input type="hidden" id="health_sus_no1"
							name="" class="form-control autocomplete" autocomplete="off"><input type="text"
								name="health_unit_name1" id="health_unit_name1" maxlength="40" onkeypress="healthUnitAuto(this);"
								class="form-control-sm form-control" autocomplete="off" /></td>

							<td align="center" width="15%"><select id="h_personnel_category1"
								name="h_personnel_category1"
								class="form-control-sm form-control">
									<option value="0">--Select--</option>
									<c:forEach var="item" items="${ml_1}" varStatus="num">
										<option value="${item}" name="${item}">${item}</option>
									</c:forEach>
							</select></td>

							<td align="center" width="10%"><input type="text" id="h_number_present1"
								name="h_number_present1" class="form-control-sm form-control"
								autocomplete="off" placeholder="0"
								onkeypress="return isNumber0_9Only(event);"></td>
							<td align="center" width="10%"><input type="text" name="h_remarks1"
								id="h_remarks1" class="form-control-sm form-control"
								maxlength="250" autocomplete="off" /></td>
							<td align="center" width="20%" ><input type="file"
								id="hea_img1" name="hea_img1" class="img" onchange="PreviewImage('1');"> 
								
								<img style="display: none;" id="healthimagepreview1" src="js/img/No_Image.jpg"									
									style="width: 100px; height: 100px;" />
									
									
									
									</td>
							<td id="c_health_idtd1" style="display: none;"><input
								type="text" id="c_health_id1" name="c_health_id1"
								class="form-control-sm form-control" value="0"
								autocomplete="off"></td>
							<td align="center" width="10%"><i class="fa fa-eye"
								onclick="healthimage(1);"></i></td>
							<td align="center" width="10%"><i class="fa fa-plus"
								onclick="healthformopen(1);" id="health_id_add1" value="ADD"></i>
								
							</td>
						</tr>
						</c:if>
							<c:if test="${listhealtheduDetails.size() != 0}">
							<c:forEach var="item" items="${listhealtheduDetails}"
								varStatus="num">
								<tr id="health_tr_id${num.index+1}">
									<td align="center" width="5%">${num.index+1}</td>
									<td align="center" width="10%">${item[0]}</td>

									<td align="center" width="10%">${item[1]}</td>

									<td align="center" width="15%">${item[2]}</td>

									<td align="center" width="10%">${item[3]}</td>
									<td align="center" width="10%">${item[4]}</td>
									<td align="center"width="20%">${item[5]}</td>	
									
									<td align="center"width="10%">${item[9]}</td>
									


									<td style="display: none;">${item[6]}</td>
									<td align="center">${item[7]}</td>
								</tr>
							</c:forEach>
						</c:if>
						
						
					</tbody>
				</table>
			</div>
			<div>
				<input type="hidden" id="health_count" name="health_count">
				<input type="hidden" id="health_oldcount" name="health_oldcount">
				<input style="float: right; margin: 5px" type="button"
					class="btn btn-success btn-sm" value="UPDATE"
					onclick="health_formsubmit();">
			</div>
			 <div id="myModal" class="modal">
          <i class="close" onclick="closemodel();">&times;</i>
        <img class="modal-content" id="img01">
        <div id="caption"></div>
      </div>
		</form>
		<!-- HEALTH EDUCATION GRID END--->

		<form id="Workshops_total_table_form" enctype="multipart/form-data">
			<div class="card-header"
				style="border: 1px solid rgba(0, 0, 0, .125); text-align: center;"
				id="aa">
				<strong>Workshops/Seminars/Symposiums Organized</strong>
			</div>



			<!-- ----------WORKSHOPS  GRID------- -->
			<div class="card-body card-block" id="workshop_total_table">
				<table
					class="table no-margin table-striped  table-hover  table-bordered report_print "
					id="workshopaddQuantity">
					<thead style="color: white; text-align: center;">
						<tr>
							<th align="center" width="5%">Ser No</th>
							<th align="center" width="10%">Topic</th>
							<th align="center" width="10%"> Unit Name</th>
							<th align="center" width="15%">Target Group</th>
							<th align="center" width="10%">Number Present</th>
							<th align="center" width="10%">Remarks</th>
							<th align="center" width="20%">Photographs</th>
							<th  width="10%">View Photograph</th>
							<th align="center" width="10%">Action</th>
						</tr>
					</thead>
					<tbody>
					<c:if test="${listworkshopDetails.size() == 0}">
						<tr id="">
							<td align="center" width="5%">1</td>
							<td align="center" width="10%"><input type="text" name="topic1"
								id="topic1" maxlength="40" class="form-control-sm form-control"
								autocomplete="off" /></td>
							<td align="center" width="10%"><input type="hidden" id="workshop_sus_no1"
							name="" class="form-control autocomplete" autocomplete="off"><input type="text" name="unit_name1"
								id="workshop_unit_name1" maxlength="40" onkeypress="workshopUnitAuto(this);"
								class="form-control-sm form-control" autocomplete="off" /></td>

							<td align="center" width="15%"> <select id="target_group1"
								name="target_group1" class="form-control-sm form-control">
									<option value="0">--Select--</option>
									<option value="1">Pers Group</option>
									<option value="2">Students</option>
									<option value="3">Teachers</option>
									<option value="4">Others</option>
							</select></td>

							<td align="center" width="10%"><input type="text" id="number_present1"
								name="number_present1" class="form-control-sm form-control"
								autocomplete="off" placeholder="0"
								onkeypress="return isNumber0_9Only(event);"></td>
							<td align="center" width="10%"><input type="text" name="remarks1"
								id="remarks1" maxlength="250"
								class="form-control-sm form-control" autocomplete="off" /></td>
							<td align="center"  width="20%"><input type="file"
								id="work_img1" name="work_img1" class="img" onchange="workPreviewImage('1');"> 
								
								<img style="display: none;" id="workimagepreview1" src="js/img/No_Image.jpg"									
									style="width: 100px; height: 100px;" />
								
								</td>
								
								<td align="center" width="10%"> <i class="fa fa-eye"
								onclick="workimage(1);"></i></td>
								
							<td id="c_workshop_idtd1" style="display: none;"><input
								type="text" id="c_workshop_id1" name="c_workshop_id1"
								class="form-control-sm form-control" value="0"
								autocomplete="off"></td>

							<td align="center" width="10%"><i class="fa fa-plus"
								onclick="workshopformopen(1);" id="workshop_id_add1" value="ADD"></i>
								</td>
						</tr>
						
						</c:if>
							<c:if test="${listworkshopDetails.size() != 0}">
							<c:forEach var="item" items="${listworkshopDetails}"
								varStatus="num">
								<tr id="health_tr_id${num.index+1}">
									<td align="center"  width="5%">${num.index+1}</td>
									<td align="center"  width="10%">${item[0]}</td>

									<td align="center"  width="10%">${item[1]}</td>

									<td align="center"  width="15%">${item[2]}</td>

									<td align="center"  width="10%">${item[3]}</td>
									<td align="center"  width="10%">${item[4]}</td>
									<td align="center"  width="20%">${item[5]}</td>	
									
									<td align="center" width="10%">${item[9]}</td>

									<td style="display: none;">${item[6]}</td>
									<td align="center"  width="10%">${item[7]}</td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
			</div>
			<div>
				<input type="hidden" id="workshop_count" name="workshop_count">
				<input type="hidden" id="workshop_oldcount" name="workshop_oldcount">
				<input style="float: right; margin: 5px" type="button"
					class="btn btn-success btn-sm" value="UPDATE"
					onclick="workshop_formsubmit();">
			</div>
		</form>
		<!--WORKSHOPS GRID END--->


		<div id="save1" class="card-footer" align="center">
			<input type="reset" class="btn btn-success btn-sm" value="Clear">
			<input type="button" class="btn btn-success btn-sm" value="Save"
				onclick="return checkprvtdtls()"> <a href="superDashboard"
				type="reset" class="btn btn-danger btn-sm">Cancel </a>
		</div>
		<div class="card-footer" align="center">
			<input type="button" class="btn btn-primary btn-sm"
				value="Save & Next" onclick="Nextpage()">

		</div>


	</div>

	<!-- end of actsho -->

	<!-- add_prvnt_details_act_1 -->
	
	<div class="card" id="montdtls"
		style="display: none; margin-bottom: 0; border: 0;">

		<div class="card-header"
			style="border: 1px solid rgba(0, 0, 0, .125); text-align: center;"
			id="aa">
			<strong>Monthly Morbidity Details</strong>
		</div>
		<form:form id="all_causes_dtl">
			<div class="card-header"
				style="border: 1px solid rgba(0, 0, 0, .125); text-align: center;"
				id="aa">
				<strong>All Causes</strong>
			</div>
			<div class="card-body card-block">
				<div class="row">
					<div class="col-md-12">
						<div class="container" align="center">
							<table id=""
								class="table no-margin table-striped  table-hover  table-bordered"
								width="60%">

								<thead style="text-align: center;">
									<tr>
										<th colspan=""></th>
										<th style="font-size: 15px" colspan="">OFFICERS</th>
										<th style="font-size: 15px" colspan="">JCOs</th>
										<th style="font-size: 15px" colspan="">ORs</th>
										<th style="font-size: 15px" colspan="">TOTAL</th>
									</tr>

								</thead>

								<tbody style="text-align: center;">
									<tr>
										<th style="font-size: 15px">Number</th>
										<!--  <th colspan="3"></th> -->
										<td colspan=""><input size=2 type='text'
											value="${add_prvnt_details_cmnd.causes_number_officers}"
											name='causes_number_officers' id='causes_number_officers'
											class="form-control-sm form-control"
											onkeypress="return isNumber0_9Only(event);" placeholder="0"
											onchange="myFunction()" autocomplete="off"></td>
										<td colspan=""><input size=2 type='text'
											value="${add_prvnt_details_cmnd.causes_number_jcos}"
											name='causes_number_jcos' id='causes_number_jcos'
											class="form-control-sm form-control"
											onkeypress="return isNumber0_9Only(event);" placeholder="0"
											onchange="myFunction()" autocomplete="off"></td>
										<td colspan=""><input size=2 type='text'
											value="${add_prvnt_details_cmnd.causes_number_ors}"
											name='causes_number_ors' id='causes_number_ors'
											class="form-control-sm form-control"
											onkeypress="return isNumber0_9Only(event);" placeholder="0"
											onchange="myFunction()" autocomplete="off"></td>
										<td colspan=""><input size=2 type='text'
											name='number_total' id='number_total'
											class="form-control-sm form-control"
											onkeypress="return isNumber0_9Only(event);" placeholder="0"
											onchange="myFunction()" autocomplete="off"
											readonly="readonly"></td>
									</tr>

									<tr>
										<th style="font-size: 15px">Rate</th>
										<td colspan=""><input size=2 type='text'
											value="${add_prvnt_details_cmnd.causes_rate_officers}"
											name='causes_rate_officers' id='causes_rate_officers'
											class="form-control-sm form-control"
											onkeypress="return isNumber0_9Only(event);" placeholder="0"
											onchange="myFunction()" autocomplete="off"></td>
										<td colspan=""><input size=2 type='text'
											value="${add_prvnt_details_cmnd.causes_rate_jcos}"
											name='causes_rate_jcos' id='causes_rate_jcos'
											class="form-control-sm form-control"
											onkeypress="return isNumber0_9Only(event);" placeholder="0"
											onchange="myFunction()" autocomplete="off"></td>
										<td colspan=""><input size=2 type='text'
											name='causes_rate_ors'
											value="${add_prvnt_details_cmnd.causes_rate_ors}"
											id='causes_rate_ors' class="form-control-sm form-control"
											onkeypress="return isNumber0_9Only(event);" placeholder="0"
											onchange="myFunction()" autocomplete="off"></td>
										<td colspan=""><input size=2 type='text'
											name='rate_total' id='rate_total'
											class="form-control-sm form-control"
											onkeypress="return isNumber0_9Only(event);" placeholder="0"
											onchange="myFunction()" autocomplete="off"
											readonly="readonly"></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</form:form>
		<form ID="malaria_table_form">
			<div class="card-header"
				style="border: 1px solid rgba(0, 0, 0, .125); text-align: center;"
				id="aa">
				<strong>Malaria</strong>
			</div>

			<div class="card-body card-block">
				<div class="row">
					<div class="col-md-12">
						<div class="container" align="center">
							<table id=""
								class="table no-margin table-striped  table-hover  table-bordered"
								width="60%">

								<thead style="text-align: center;">
									<tr>
										<th colspan=""></th>
										<th style="font-size: 15px" colspan="">OFFICERS</th>
										<th style="font-size: 15px" colspan="">JCOs</th>
										<th style="font-size: 15px" colspan="">ORs</th>
										<th style="font-size: 15px" colspan="">TOTAL</th>
									</tr>

								</thead>

								<tbody style="text-align: center;">

									<tr>
										<th style="font-size: 15px">Number</th>
										
										<td colspan=""><input size=2 type='text'
											name='malaria_number_officers' id='malaria_number_officers'
											value="${add_prvnt_details_cmnd.malaria_number_officers}"
											class="form-control-sm form-control" placeholder="0"
											onkeypress="return isNumber0_9Only(event);"
											onchange="myFunction4()" autocomplete="off"></td>
										<td colspan=""><input size=2 type='text'
											name='malaria_number_jcos' id='malaria_number_jcos'
											value="${add_prvnt_details_cmnd.malaria_number_jcos}"
											class="form-control-sm form-control" placeholder="0"
											onkeypress="return isNumber0_9Only(event);"
											onchange="myFunction4()" autocomplete="off"></td>
										<td colspan=""><input size=2 type='text'
											name='malaria_number_ors' id='malaria_number_ors'
											value="${add_prvnt_details_cmnd.malaria_number_ors}"
											class="form-control-sm form-control" placeholder="0"
											onkeypress="return isNumber0_9Only(event);"
											onchange="myFunction4()" autocomplete="off"></td>
										<td colspan=""><input size=2 type='text'
											name='number_total' id='Malaria_number_total'
											class="form-control-sm form-control" placeholder="0"
											onkeypress="return isNumber0_9Only(event);"
											onchange="myFunction4()" autocomplete="off"
											readonly="readonly"></td>
									</tr>

									<tr>
										<th style="font-size: 15px">Rate</th>
										<td colspan=""><input size=2 type='text'
											name='malaria_rate_officers' id='malaria_rate_officers'
											value="${add_prvnt_details_cmnd.malaria_rate_officers}"
											class="form-control-sm form-control" placeholder="0"
											onkeypress="return isNumber0_9Only(event);"
											onchange="myFunction4()" autocomplete="off"></td>
										<td colspan=""><input size=2 type='text'
											name='malaria_rate_jcos' id='malaria_rate_jcos'
											value="${add_prvnt_details_cmnd.malaria_rate_jcos}"
											class="form-control-sm form-control" placeholder="0"
											onkeypress="return isNumber0_9Only(event);"
											onchange="myFunction4()" autocomplete="off"></td>
										<td colspan=""><input size=2 type='text'
											name='malaria_rate_ors'
											value="${add_prvnt_details_cmnd.malaria_rate_ors}"
											id='malaria_rate_ors' class="form-control-sm form-control"
											placeholder="0" onkeypress="return isNumber0_9Only(event);"
											onchange="myFunction4()" autocomplete="off"></td>
										<td colspan=""><input size=2 type='text'
											name='rate_total' id='Malaria_rate_total'
											class="form-control-sm form-control" placeholder="0"
											onkeypress="return isNumber0_9Only(event);"
											onchange="myFunction4()" autocomplete="off"
											readonly="readonly"></td>
									</tr>

								</tbody>
							</table>
						</div>
					</div>
				</div>


				<!-- ----------MALARIA GRID------- -->
				<div class="card-body card-block" id="malaria_total_table">
					<table
						class="table no-margin table-striped  table-hover  table-bordered report_print "
						id="malariaaddQuantity">
						<thead style="color: white; text-align: center;">
							<tr>
								<th align="center">Ser No</th>
								<th>Unit Name</th>
								<th>BT</th>
								<th>MT</th>
								<th>FL</th>
								<th>FI</th>
								<th>TOTAL</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody id="malariaaddQuantitytbody">
							<c:if test="${listMalariyaDetails.size() == 0}">
								<tr id="">
									<td align="center">1</td>
									<td align="center"><input type="hidden" name=""
										id="mal_sus_no1" maxlength="40"
										class="form-control-sm form-control" autocomplete="off" /> <input
										type="text" name="unit_name1" id="mal_unit_name1"
										maxlength="40" onkeypress="malUnitAuto(this);"
										class="form-control-sm form-control" autocomplete="off" /></td>
									<td align="center"><input type="text" id="bt1" name="bt1"
										class="form-control-sm form-control"
										onchange="myFunctionMAl(1)" autocomplete="off" placeholder="0"
										onkeypress="return isNumber0_9Only(event);"></td>
									<td align="center"><input type="text" id="mt1" name="mt1"
										class="form-control-sm form-control"
										onchange="myFunctionMAl(1)" autocomplete="off" placeholder="0"
										onkeypress="return isNumber0_9Only(event);"></td>
									<td align="center"><input type="text" id="fl1" name="fl1"
										class="form-control-sm form-control"
										onchange="myFunctionMAl(1)" autocomplete="off" placeholder="0"
										onkeypress="return isNumber0_9Only(event);"></td>
									<td align="center"><input type="text" id="fi1" name="fi1"
										class="form-control-sm form-control"
										onchange="myFunctionMAl(1)" autocomplete="off" placeholder="0"
										onkeypress="return isNumber0_9Only(event);"></td>
									<td align="center"><input size=2 type='text'
										name='mala_total1' id='mala_total1'
										class="form-control-sm form-control" placeholder="0"
										onkeypress="return isNumber0_9Only(event);" autocomplete="off"
										readonly="readonly"></td>

									<td align="center"><i class="fa fa-plus"
										onclick="malariaformopen(1);" id="malaria_id_add1" value="ADD"></i></td>
								</tr>
							</c:if>

							<c:if test="${listMalariyaDetails.size() != 0}">
								<c:forEach var="item" items="${listMalariyaDetails}"
									varStatus="num">
									<tr id="malaria_tr_id${num.index+1}">
										<td style="text-align: center;">${num.index+1}</td>
										<td>${item[0]}</td>

										<td>${item[1]}</td>

										<td>${item[2]}</td>

										<td>${item[3]}</td>

										<td>${item[4]}</td>

										<td>${item[5]}</td>



										<td style="display: none;">${item[6]}</td>
										<td style="text-align: center;">${item[7]}</td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
					</table>
				</div>

				<!-- MALARIA GRID END--->
			</div>
			<div>
				<input type="hidden" id="malaria_count" name="malaria_count" val="">
				<input type="hidden" id="malariaoldcount" name="malariaoldcount">
				<input style="float: right; margin: 5px" type="button"
					class="btn btn-success btn-sm" value="UPDATE"
					onclick="malariyaformsubmit();">
			</div>
		</form>

		<!-- MALARIA GRID END--->

		<form:form id="dengue_dtl">

			<div class="card-header"
				style="border: 1px solid rgba(0, 0, 0, .125); text-align: center;"
				id="aa">
				<strong>Dengue</strong>
			</div>
			<div class="card-body card-block">
				<div class="row">
					<div class="col-md-12">
						<div class="container" align="center">
							<table id=""
								class="table no-margin table-striped  table-hover  table-bordered"
								width="60%">

								<thead style="text-align: center;">
									<tr>
										<th colspan=""></th>
										<th style="font-size: 15px" colspan="">OFFICERS</th>
										<th style="font-size: 15px" colspan="">JCOs</th>
										<th style="font-size: 15px" colspan="">ORs</th>
										<th style="font-size: 15px" colspan="">TOTAL</th>
									</tr>

								</thead>

								<tbody style="text-align: center;">
									<tr>
										<th style="font-size: 15px">Number</th>
										
										<td colspan=""><input size=2 type='text'
											name='dengue_number_officers' id='dengue_number_officers'
											value="${add_prvnt_details_cmnd.dengue_number_officers}"
											class="form-control-sm form-control" placeholder="0"
											onkeypress="return isNumber0_9Only(event);"
											onchange="myFunction5()" autocomplete="off"></td>
										<td colspan=""><input size=2 type='text'
											name='dengue_number_jcos' id='dengue_number_jcos'
											value="${add_prvnt_details_cmnd.dengue_number_jcos}"
											class="form-control-sm form-control" placeholder="0"
											onkeypress="return isNumber0_9Only(event);"
											onchange="myFunction5()" autocomplete="off"></td>
										<td colspan=""><input size=2 type='text'
											name='dengue_number_ors' id='dengue_number_ors'
											value="${add_prvnt_details_cmnd.dengue_number_ors}"
											class="form-control-sm form-control" placeholder="0"
											onkeypress="return isNumber0_9Only(event);"
											onchange="myFunction5()" autocomplete="off"></td>
										<td colspan=""><input size=2 type='text'
											name='number_total' id='Dengue_number_total'
											class="form-control-sm form-control" placeholder="0"
											onkeypress="return isNumber0_9Only(event);"
											onchange="myFunction5()" autocomplete="off"
											readonly="readonly"></td>
									</tr>
									<tr>
										<th style="font-size: 15px">Rate</th>
										<td colspan=""><input size=2 type='text'
											name='dengue_rate_officers' id='dengue_rate_officers'
											value="${add_prvnt_details_cmnd.dengue_rate_officers}"
											class="form-control-sm form-control" placeholder="0"
											onkeypress="return isNumber0_9Only(event);"
											onchange="myFunction5()" autocomplete="off"></td>
										<td colspan=""><input size=2 type='text'
											name='dengue_rate_jcos' id='dengue_rate_jcos'
											value="${add_prvnt_details_cmnd.dengue_rate_jcos}"
											class="form-control-sm form-control" placeholder="0"
											onkeypress="return isNumber0_9Only(event);"
											onchange="myFunction5()" autocomplete="off"></td>
										<td colspan=""><input size=2 type='text'
											name='dengue_rate_ors'
											value="${add_prvnt_details_cmnd.dengue_rate_ors}"
											id='dengue_rate_ors' class="form-control-sm form-control"
											placeholder="0" onkeypress="return isNumber0_9Only(event);"
											onchange="myFunction5()" autocomplete="off"></td>
										<td colspan=""><input size=2 type='text'
											name='rate_total' id='Dengue_rate_total'
											class="form-control-sm form-control" placeholder="0"
											onkeypress="return isNumber0_9Only(event);"
											onchange="myFunction5()" autocomplete="off"
											readonly="readonly"></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="col-md-12 row form-group">
					<div class="col-md-2" style="text-align: left;">
						<label for="text-input" class=" form-control-label">Unit
							Name</label>
					</div>
					<div class="col-md-4">
						<input type="hidden" id="sus_no12" name=""
							class="form-control autocomplete" autocomplete="off"> <input
							type="text" id="unit_name12" name="dengue_unit_name"
							value="${add_prvnt_details_cmnd.dengue_unit_name}"
							class="form-control-sm form-control" autocomplete="on">
						<!-- <input type="text" id="unit_name" name="unit_name" class="form-control autocomplete" autocomplete="off"> -->
					</div>

				</div>

			</div>
		</form:form>
		<form:form id="chikunguniya_dtl">
			<div class="card-header"
				style="border: 1px solid rgba(0, 0, 0, .125); text-align: center;"
				id="aa">
				<strong>Chikungunya</strong>
			</div>
			<div class="card-body card-block">
				<div class="row">
					<div class="col-md-12">
						<div class="container" align="center">
							<table id=""
								class="table no-margin table-striped  table-hover  table-bordered"
								width="60%">

								<thead style="text-align: center;">
									<tr>
										<th colspan=""></th>
										<th style="font-size: 15px" colspan="">OFFICERS</th>
										<th style="font-size: 15px" colspan="">JCOs</th>
										<th style="font-size: 15px" colspan="">ORs</th>
										<th style="font-size: 15px" colspan="">TOTAL</th>
									</tr>

								</thead>

								<tbody style="text-align: center;">

									<tr>
										<th style="font-size: 15px">Number</th>
										
										<td colspan=""><input size=2 type='text'
											name='chikungunya_number_officers'
											id='chikungunya_number_officers'
											value="${add_prvnt_details_cmnd.chikungunya_number_officers}"
											class="form-control-sm form-control" placeholder="0"
											onkeypress="return isNumber0_9Only(event);"
											onchange="myFunction7()" autocomplete="off"></td>
										<td colspan=""><input size=2 type='text'
											name='chikungunya_number_jcos' id='chikungunya_number_jcos'
											value="${add_prvnt_details_cmnd.chikungunya_number_jcos}"
											class="form-control-sm form-control" placeholder="0"
											onkeypress="return isNumber0_9Only(event);"
											onchange="myFunction7()" autocomplete="off"></td>
										<td colspan=""><input size=2 type='text'
											name='chikungunya_number_ors' id='chikungunya_number_ors'
											value="${add_prvnt_details_cmnd.chikungunya_number_ors}"
											class="form-control-sm form-control" placeholder="0"
											onkeypress="return isNumber0_9Only(event);"
											onchange="myFunction7()" autocomplete="off"></td>
										<td colspan=""><input size=2 type='text'
											name='number_total' id='Chikenguniya_number_total'
											class="form-control-sm form-control" placeholder="0"
											onkeypress="return isNumber0_9Only(event);"
											onchange="myFunction7()" autocomplete="off"
											readonly="readonly"></td>
									</tr>

									<tr>
										<th style="font-size: 15px">Rate</th>
										<td colspan=""><input size=2 type='text'
											name='chikungunya_rate_officers'
											id='chikungunya_rate_officers'
											value="${add_prvnt_details_cmnd.chikungunya_rate_officers}"
											class="form-control-sm form-control" placeholder="0"
											onkeypress="return isNumber0_9Only(event);"
											onchange="myFunction7()" autocomplete="off"></td>
										<td colspan=""><input size=2 type='text'
											name='chikungunya_rate_jcos' id='chikungunya_rate_jcos'
											value="${add_prvnt_details_cmnd.chikungunya_rate_jcos}"
											class="form-control-sm form-control" placeholder="0"
											onkeypress="return isNumber0_9Only(event);"
											onchange="myFunction7()" autocomplete="off"></td>
										<td colspan=""><input size=2 type='text'
											name='chikungunya_rate_ors'
											value="${add_prvnt_details_cmnd.chikungunya_rate_ors}"
											id='chikungunya_rate_ors'
											class="form-control-sm form-control" placeholder="0"
											onkeypress="return isNumber0_9Only(event);"
											onchange="myFunction7()" autocomplete="off"></td>
										<td colspan=""><input size=2 type='text'
											name='rate_total' id='Chikenguniya_rate_total'
											class="form-control-sm form-control" placeholder="0"
											onkeypress="return isNumber0_9Only(event);"
											onchange="myFunction7()" autocomplete="off"
											readonly="readonly"></td>
									</tr>

								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="col-md-12 row form-group">
					<div class="col-md-2" style="text-align: left;">
						<label for="text-input" class=" form-control-label"> Unit
							Name</label>
					</div>
					<div class="col-md-4">
						<input type="hidden" id="sus_no13" name=""
							class="form-control autocomplete" autocomplete="off"> <input
							type="text" id="unit_name13" name="chikungunya_unit_name"
							value="${add_prvnt_details_cmnd.chikungunya_unit_name}"
							class="form-control-sm form-control" autocomplete="off">
						<!-- <input type="text" id="unit_name" name="unit_name" class="form-control autocomplete" autocomplete="off"> -->
					</div>

				</div>

			</div>
		</form:form>
		<%-- </form:form> --%>
		<form ID="STD_table_form">
			<div class="card-header"
				style="border: 1px solid rgba(0, 0, 0, .125); text-align: center;"
				id="aa">
				<strong>STD</strong>
			</div>

			<div class="card-body card-block">
				<div class="row">
					<div class="col-md-12">
						<div class="container" align="center">
							<table id=""
								class="table no-margin table-striped  table-hover  table-bordered"
								width="60%">

								<thead style="text-align: center;">
									<tr>
										<th colspan=""></th>
										<th style="font-size: 15px" colspan="">OFFICERS</th>
										<th style="font-size: 15px" colspan="">JCOs</th>
										<th style="font-size: 15px" colspan="">ORs</th>
										<th style="font-size: 15px" colspan="">TOTAL</th>
									</tr>

								</thead>

								<tbody style="text-align: center;">

									<tr>
										<th style="font-size: 15px">Number</th>
										
										<td colspan=""><input size=2 type='text'
											name='std_number_officers' id='std_number_officers'
											value="${add_prvnt_details_cmnd.std_number_officers}"
											class="form-control-sm form-control" placeholder="0"
											onkeypress="return isNumber0_9Only(event);"
											onchange="myFunction8()" autocomplete="off"></td>
										<td colspan=""><input size=2 type='text'
											name='std_number_jcos' id='std_number_jcos'
											value="${add_prvnt_details_cmnd.std_number_jcos}"
											class="form-control-sm form-control" placeholder="0"
											onkeypress="return isNumber0_9Only(event);"
											onchange="myFunction8()" autocomplete="off"></td>
										<td colspan=""><input size=2 type='text'
											name='std_number_ors' id='std_number_ors'
											value="${add_prvnt_details_cmnd.std_number_ors}"
											class="form-control-sm form-control" placeholder="0"
											onkeypress="return isNumber0_9Only(event);"
											onchange="myFunction8()" autocomplete="off"></td>
										<td colspan=""><input size=2 type='text'
											name='number_total' id='STD_number_total'
											class="form-control-sm form-control" placeholder="0"
											onkeypress="return isNumber0_9Only(event);"
											onchange="myFunction8()" autocomplete="off"
											readonly="readonly"></td>
									</tr>

									<tr>
										<th style="font-size: 15px">Rate</th>
										<td colspan=""><input size=2 type='text'
											name='std_rate_officers' id='std_rate_officers'
											value="${add_prvnt_details_cmnd.std_rate_officers}"
											class="form-control-sm form-control" placeholder="0"
											onkeypress="return isNumber0_9Only(event);"
											onchange="myFunction8()" autocomplete="off"></td>
										<td colspan=""><input size=2 type='text'
											name='std_rate_jcos' id='std_rate_jcos'
											value="${add_prvnt_details_cmnd.std_rate_jcos}"
											class="form-control-sm form-control" placeholder="0"
											onkeypress="return isNumber0_9Only(event);"
											onchange="myFunction8()" autocomplete="off"></td>
										<td colspan=""><input size=2 type='text'
											name='std_rate_ors'
											value="${add_prvnt_details_cmnd.std_rate_ors}"
											id='std_rate_ors' class="form-control-sm form-control"
											placeholder="0" onkeypress="return isNumber0_9Only(event);"
											onchange="myFunction8()" autocomplete="off"></td>
										<td colspan=""><input size=2 type='text'
											name='rate_total' id='STD_rate_total'
											class="form-control-sm form-control" placeholder="0"
											onkeypress="return isNumber0_9Only(event);"
											onchange="myFunction8()" autocomplete="off"
											readonly="readonly"></td>
									</tr>

								</tbody>
							</table>
						</div>
					</div>
				</div>

				<!-- ----------STD GRID------- -->
				<div class="card-body card-block" id="std_total_table">
					<table
						class="table no-margin table-striped  table-hover  table-bordered report_print "
						id="stdaddQuantity">
						<thead style="color: white; text-align: center;">
							<tr>
								<th align="center">Ser No</th>
								<th>Unit Name</th>
								<th>FL</th>
								<th>FI</th>
								<th>TOTAL</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody id="stdaddQuantitytbody">
							<c:if test="${listStdDetails.size() == 0}">
								<tr id="">
									<td align="center">1</td>
									<td align="center"><input type="hidden" name=""
										id="std_sus_no1" maxlength="40"
										class="form-control-sm form-control" autocomplete="off" /> <input
										type="text" name="unit_name1" id="std_unit_name1"
										maxlength="40" onkeypress="stdUnitAuto(this);"
										class="form-control-sm form-control" autocomplete="off" /></td>
									<td align="center"><input type="text" id="s_fl1"
										name="s_fl1" class="form-control-sm form-control"
										autocomplete="off" placeholder="0" onchange="myFunctionSTD(1)"
										onkeypress="return isNumber0_9Only(event);"></td>
									<td align="center"><input type="text" id="s_fi1"
										name="s_fi1" class="form-control-sm form-control"
										autocomplete="off" placeholder="0" onchange="myFunctionSTD(1)"
										onkeypress="return isNumber0_9Only(event);"></td>
									<td align="center"><input size=2 type='text'
										name='std_total1' id='std_total1'
										class="form-control-sm form-control" placeholder="0"
										onkeypress="return isNumber0_9Only(event);" autocomplete="off"
										readonly="readonly"></td>


									<td align="center"><i class="fa fa-plus"
										onclick="stdformopen(1);" id="std_id_add1" value="ADD"></i></td>
								</tr>
							</c:if>



							<c:if test="${listStdDetails.size() != 0}">
								<c:forEach var="item" items="${listStdDetails}" varStatus="num">
									<tr id="std_tr_id${num.index+1}">
										<td style="text-align: center;">${num.index+1}</td>
										<td>${item[0]}</td>

										<td>${item[1]}</td>

										<td>${item[2]}</td>

										<td>${item[3]}</td>

										<td style="display: none;">${item[4]}</td>
										<td style="text-align: center;">${item[5]}</td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
					</table>
				</div>
				<div>
					<input type="hidden" id="std_count" name="std_count"> <input
						type="hidden" id="stdoldcount" name="stdoldcount"> <input
						style="float: right; margin: 5px" type="button"
						class="btn btn-success btn-sm" value="UPDATE"
						onclick="stdformsubmit();">
				</div>
		</form>

		<!-- STD GRID END--->
	</div>
	<form:form id="immune_survellance_dtl">
		<div class="card-header"
			style="border: 1px solid rgba(0, 0, 0, .125); text-align: center;"
			id="aa">
			<strong>Immune Surveillance</strong>
		</div>
		<div class="card-body card-block">
			<div class="row">
				<div class="col-md-12">
					<div class="container" align="center">
						<table id=""
							class="table no-margin table-striped  table-hover  table-bordered"
							width="60%">

							<thead style="text-align: center;">
								<tr>
									<th colspan=""></th>
									<th style="font-size: 15px" colspan="">OFFFICERS</th>
									<th style="font-size: 15px" colspan="">JCOs</th>
									<th style="font-size: 15px" colspan="">ORs</th>
									<th style="font-size: 15px" colspan="">TOTAL</th>
								</tr>

							</thead>

							<tbody style="text-align: center;">

								<tr>
									<th style="font-size: 15px">Number</th>
									
									<td colspan=""><input size=2 type='text'
										name='immune_surveillance_number_officers'
										value="${add_prvnt_details_cmnd.immune_surveillance_number_officers}"
										id='immune_surveillance_number_officers'
										class="form-control-sm form-control" placeholder="0"
										onkeypress="return isNumber0_9Only(event);"
										onchange="myFunction9()" autocomplete="off"></td>
									<td colspan=""><input size=2 type='text'
										name='immune_surveillance_number_jcos'
										id='immune_surveillance_number_jcos'
										value="${add_prvnt_details_cmnd.immune_surveillance_number_jcos}"
										class="form-control-sm form-control" placeholder="0"
										onkeypress="return isNumber0_9Only(event);"
										onchange="myFunction9()" autocomplete="off"></td>
									<td colspan=""><input size=2 type='text'
										name='immune_surveillance_number_ors'
										id='immune_surveillance_number_ors'
										value="${add_prvnt_details_cmnd.immune_surveillance_number_ors}"
										class="form-control-sm form-control" placeholder="0"
										onkeypress="return isNumber0_9Only(event);"
										onchange="myFunction9()" autocomplete="off"></td>
									<td colspan=""><input size=2 type='text'
										name='number_total' id='Imiune_Surveillance_number_total'
										class="form-control-sm form-control" placeholder="0"
										onkeypress="return isNumber0_9Only(event);"
										onchange="myFunction9()" autocomplete="off"
										readonly="readonly"></td>
								</tr>

								<tr>
									<th style="font-size: 15px">Rate</th>
									<td colspan=""><input size=2 type='text'
										name='immune_surveillance_rate_officers'
										id='immune_surveillance_rate_officers'
										value="${add_prvnt_details_cmnd.immune_surveillance_rate_officers}"
										class="form-control-sm form-control" placeholder="0"
										onkeypress="return isNumber0_9Only(event);"
										onchange="myFunction9()" autocomplete="off"></td>
									<td colspan=""><input size=2 type='text'
										name='immune_surveillance_rate_jcos'
										id='immune_surveillance_rate_jcos'
										value="${add_prvnt_details_cmnd.immune_surveillance_rate_jcos}"
										class="form-control-sm form-control" placeholder="0"
										onkeypress="return isNumber0_9Only(event);"
										onchange="myFunction9()" autocomplete="off"></td>
									<td colspan=""><input size=2 type='text'
										name='immune_surveillance_rate_ors'
										value="${add_prvnt_details_cmnd.immune_surveillance_rate_ors}"
										id='immune_surveillance_rate_ors'
										class="form-control-sm form-control" placeholder="0"
										onkeypress="return isNumber0_9Only(event);"
										onchange="myFunction9()" autocomplete="off"></td>
									<td colspan=""><input size=2 type='text' name='rate_total'
										id='Imiune_Surveillance_rate_total'
										class="form-control-sm form-control" placeholder="0"
										onkeypress="return isNumber0_9Only(event);"
										onchange="myFunction9()" autocomplete="off"
										readonly="readonly"></td>
								</tr>

							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</form:form>
	<form ID="Viral_Hepatitis_table_form">
		<div class="card-header"
			style="border: 1px solid rgba(0, 0, 0, .125); text-align: center;"
			id="aa">
			<strong>Viral Hepatitis A&E</strong>
		</div>
		<div class="card-body card-block">
			<div class="row">
				<div class="col-md-12">
					<div class="container" align="center">
						<table id=""
							class="table no-margin table-striped  table-hover  table-bordered"
							width="60%">

							<thead style="text-align: center;">
								<tr>
									<th colspan=""></th>
									<th style="font-size: 15px" colspan="">OFFICERS</th>
									<th style="font-size: 15px" colspan="">JCOs</th>
									<th style="font-size: 15px" colspan="">ORs</th>
									<th style="font-size: 15px" colspan="">TOTAL</th>
								</tr>

							</thead>

							<tbody style="text-align: center;">

								<tr>
									<th style="font-size: 15px">Number</th>
									
									<td colspan=""><input size=2 type='text'
										name='viral_hepatitis_number_officers'
										id='viral_hepatitis_number_officers'
										value="${add_prvnt_details_cmnd.viral_hepatitis_number_officers}"
										class="form-control-sm form-control" placeholder="0"
										onkeypress="return isNumber0_9Only(event);"
										onchange="myFunction10()" autocomplete="off"></td>
									<td colspan=""><input size=2 type='text'
										name='viral_hepatitis_number_jcos'
										id='viral_hepatitis_number_jcos'
										value="${add_prvnt_details_cmnd.viral_hepatitis_number_jcos}"
										class="form-control-sm form-control" placeholder="0"
										onkeypress="return isNumber0_9Only(event);"
										onchange="myFunction10()" autocomplete="off"></td>
									<td colspan=""><input size=2 type='text'
										name='viral_hepatitis_number_ors'
										id='viral_hepatitis_number_ors'
										value="${add_prvnt_details_cmnd.viral_hepatitis_number_ors}"
										class="form-control-sm form-control" placeholder="0"
										onkeypress="return isNumber0_9Only(event);"
										onchange="myFunction10()" autocomplete="off"></td>
									<td colspan=""><input size=2 type='text'
										name='number_total' id='Viral_Hepatitis_number_total'
										class="form-control-sm form-control" placeholder="0"
										onkeypress="return isNumber0_9Only(event);"
										onchange="myFunction10()" autocomplete="off"
										readonly="readonly"></td>
								</tr>

								<tr>
									<th style="font-size: 15px">Rate</th>
									<td colspan=""><input size=2 type='text'
										name='viral_hepatitis_rate_officers'
										id='viral_hepatitis_rate_officers'
										value="${add_prvnt_details_cmnd.viral_hepatitis_rate_officers}"
										class="form-control-sm form-control" placeholder="0"
										onkeypress="return isNumber0_9Only(event);"
										onchange="myFunction10()" autocomplete="off"></td>
									<td colspan=""><input size=2 type='text'
										name='viral_hepatitis_rate_jcos'
										id='viral_hepatitis_rate_jcos'
										value="${add_prvnt_details_cmnd.viral_hepatitis_rate_jcos}"
										class="form-control-sm form-control" placeholder="0"
										onkeypress="return isNumber0_9Only(event);"
										onchange="myFunction10()" autocomplete="off"></td>
									<td colspan=""><input size=2 type='text'
										name='viral_hepatitis_rate_ors'
										value="${add_prvnt_details_cmnd.viral_hepatitis_rate_ors}"
										id='viral_hepatitis_rate_ors'
										class="form-control-sm form-control" placeholder="0"
										onkeypress="return isNumber0_9Only(event);"
										onchange="myFunction10()" autocomplete="off"></td>
									<td colspan=""><input size=2 type='text' name='rate_total'
										id='Viral_Hepatitis_rate_total'
										class="form-control-sm form-control" placeholder="0"
										onkeypress="return isNumber0_9Only(event);"
										onchange="myFunction10()" autocomplete="off"
										readonly="readonly"></td>
								</tr>

							</tbody>
						</table>
					</div>
				</div>
			</div>

			<!-- ----------VIRAL HEPATITIS GRID------- -->
			<div class="card-body card-block" id="hepatits_total_table">
				<table
					class="table no-margin table-striped  table-hover  table-bordered report_print "
					id="hepatitsaddQuantity">
					<thead style="color: white; text-align: center;">
						<tr>
							<th align="center">Ser No</th>
							<th>Unit Name</th>
							<th>FL</th>
							<th>FI</th>
							<th>TOTAL</th>
							<th>Action</th>

						</tr>
					</thead>
					<tbody id="hepatitsaddQuantitytbody">
						<c:if test="${listviral_hepatitisDetails.size() == 0}">
							<tr id="">
								<td align="center">1</td>
								<td align="center"><input type="hidden" name=""
									id="viral_sus_no1" maxlength="40"
									class="form-control-sm form-control" autocomplete="off">
									<input type="text" name="unit_name1" id="viral_unit_name1"
									maxlength="40" onkeypress="viralUnitAuto(this);"
									class="form-control-sm form-control" autocomplete="off" /></td>
								<td align="center"><input type="text" id="h_fl1"
									name="h_fl1" onchange="myFunctionHEPA(1)"
									class="form-control-sm form-control" autocomplete="off"
									placeholder="0" onkeypress="return isNumber0_9Only(event);">
								</td>
								<td align="center"><input type="text" id="h_fi1"
									name="h_fi1" onchange="myFunctionHEPA(1)"
									class="form-control-sm form-control" autocomplete="off"
									placeholder="0" onkeypress="return isNumber0_9Only(event);">
								</td>
								<td align="center"><input size=2 type='text'
									name='hepa_total1' id='hepa_total1'
									class="form-control-sm form-control" placeholder="0"
									onkeypress="return isNumber0_9Only(event);"
									onchange="myFunctionHEPA()" autocomplete="off"
									readonly="readonly"></td>


								<td align="center"><i class="fa fa-plus"
									onclick="hepatitsformopen(1);" id="hepatits_id_add1"
									value="ADD"></i></td>
							</tr>

						</c:if>

						<c:if test="${listviral_hepatitisDetails.size() != 0}">
							<c:forEach var="item" items="${listviral_hepatitisDetails}"
								varStatus="num">
								<tr id="hepatits_tr_id${num.index+1}">
									<td style="text-align: center;">${num.index+1}</td>
									<td>${item[0]}</td>

									<td>${item[1]}</td>

									<td>${item[2]}</td>

									<td>${item[3]}</td>

									<td style="display: none;">${item[4]}</td>
									<td style="text-align: center;">${item[5]}</td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
			</div>
			<div>
				<input type="hidden" id="viral_hepatitis_count"
					name="viral_hepatitis_count"> <input type="hidden"
					id="viral_hepatitis_oldcount" name="viral_hepatitis_oldcount">
				<input style="float: right; margin: 5px" type="button"
					class="btn btn-success btn-sm" value="UPDATE"
					onclick="viral_hepatitisformsubmit();">
			</div>
	</form>

	<!-- VIRAL HEPATITIS  END--->

</div>
<form:form id="Diarrhoea_n_respi_dtl">
	<div class="card-header"
		style="border: 1px solid rgba(0, 0, 0, .125); text-align: center;"
		id="aa">
		<strong>Diarrhoea/Dysentry</strong>
	</div>
	<div class="card-body card-block">
		<div class="row">
			<div class="col-md-12">
				<div class="container" align="center">
					<table id=""
						class="table no-margin table-striped  table-hover  table-bordered"
						width="60%">

						<thead style="text-align: center;">
							<tr>
								<th colspan=""></th>
								<th style="font-size: 15px" colspan="">OFFICERS</th>
								<th style="font-size: 15px" colspan="">JCOs</th>
								<th style="font-size: 15px" colspan="">ORs</th>
								<th style="font-size: 15px" colspan="">TOTAL</th>
							</tr>

						</thead>

						<tbody style="text-align: center;">
							<tr>
								<th style="font-size: 15px">Number</th>
								
								<td colspan=""><input size=2 type='text'
									name='diarrhoea_number_officers' id='diarrhoea_number_officers'
									value="${add_prvnt_details_cmnd.diarrhoea_number_officers}"
									class="form-control-sm form-control" placeholder="0"
									onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction16()" autocomplete="off"></td>
								<td colspan=""><input size=2 type='text'
									name='diarrhoea_number_jcos' id='diarrhoea_number_jcos'
									value="${add_prvnt_details_cmnd.diarrhoea_number_jcos}"
									class="form-control-sm form-control" placeholder="0"
									onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction16()" autocomplete="off"></td>
								<td colspan=""><input size=2 type='text'
									name='diarrhoea_number_ors' id='diarrhoea_number_ors'
									value="${add_prvnt_details_cmnd.diarrhoea_number_ors}"
									class="form-control-sm form-control" placeholder="0"
									onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction16()" autocomplete="off"></td>
								<td colspan=""><input size=2 type='text'
									name='number_total' id='Diarrhoea_number_total'
									class="form-control-sm form-control" placeholder="0"
									onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction16()" autocomplete="off"
									readonly="readonly"></td>
							</tr>

							<tr>
								<th style="font-size: 15px">Rate</th>
								<td colspan=""><input size=2 type='text'
									name='diarrhoea_rate_officers' id='diarrhoea_rate_officers'
									value="${add_prvnt_details_cmnd.diarrhoea_rate_officers}"
									class="form-control-sm form-control" placeholder="0"
									onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction16()" autocomplete="off"></td>
								<td colspan=""><input size=2 type='text'
									name='diarrhoea_rate_jcos' id='diarrhoea_rate_jcos'
									value="${add_prvnt_details_cmnd.diarrhoea_rate_jcos}"
									class="form-control-sm form-control" placeholder="0"
									onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction16()" autocomplete="off"></td>
								<td colspan=""><input size=2 type='text'
									name='diarrhoea_rate_ors'
									value="${add_prvnt_details_cmnd.diarrhoea_rate_ors}"
									id='diarrhoea_rate_ors' class="form-control-sm form-control"
									placeholder="0" onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction16()" autocomplete="off"></td>
								<td colspan=""><input size=2 type='text' name='rate_total'
									id='Diarrhoea_rate_total' class="form-control-sm form-control"
									placeholder="0" onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction16()" autocomplete="off"
									readonly="readonly"></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div class="card-header"
		style="border: 1px solid rgba(0, 0, 0, .125); text-align: center;"
		id="aa">
		<strong>Respiratory Group Of Disease</strong>
	</div>
	<div class="card-body card-block">
		<div class="row">
			<div class="col-md-12">
				<div class="container" align="center">
					<table id=""
						class="table no-margin table-striped  table-hover  table-bordered"
						width="60%">

						<thead style="text-align: center;">
							<tr>
								<th colspan=""></th>
								<th style="font-size: 15px" colspan="">OFFICERS</th>
								<th style="font-size: 15px" colspan="">JCOs</th>
								<th style="font-size: 15px" colspan="">ORs</th>
								<th style="font-size: 15px" colspan="">TOTAL</th>
							</tr>

						</thead>

						<tbody style="text-align: center;">

							<tr>
								<th style="font-size: 15px">Number</th>
								
								<td colspan=""><input size=2 type='text'
									name='respiratory_number_officers'
									value="${add_prvnt_details_cmnd.respiratory_number_officers}"
									id='respiratory_number_officers'
									class="form-control-sm form-control" placeholder="0"
									onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction11()" autocomplete="off"></td>
								<td colspan=""><input size=2 type='text'
									name='respiratory_number_jcos' id='respiratory_number_jcos'
									value="${add_prvnt_details_cmnd.respiratory_number_jcos}"
									class="form-control-sm form-control" placeholder="0"
									onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction11()" autocomplete="off"></td>
								<td colspan=""><input size=2 type='text'
									name='respiratory_number_ors' id='respiratory_number_ors'
									value="${add_prvnt_details_cmnd.respiratory_number_ors}"
									class="form-control-sm form-control" placeholder="0"
									onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction11()" autocomplete="off"></td>
								<td colspan=""><input size=2 type='text'
									name='number_total' id='Respiratory_Group_number_total'
									class="form-control-sm form-control" placeholder="0"
									onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction11()" autocomplete="off"
									readonly="readonly"></td>
							</tr>

							<tr>
								<th style="font-size: 15px">Rate</th>
								<td colspan=""><input size=2 type='text'
									name='respiratory_rate_officers' id='respiratory_rate_officers'
									value="${add_prvnt_details_cmnd.respiratory_rate_officers}"
									class="form-control-sm form-control" placeholder="0"
									onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction11()" autocomplete="off"></td>
								<td colspan=""><input size=2 type='text'
									name='respiratory_rate_jcos' id='respiratory_rate_jcos'
									value="${add_prvnt_details_cmnd.respiratory_rate_jcos}"
									class="form-control-sm form-control" placeholder="0"
									onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction11()" autocomplete="off"></td>
								<td colspan=""><input size=2 type='text'
									name='respiratory_rate_ors'
									value="${add_prvnt_details_cmnd.respiratory_rate_ors}"
									id='respiratory_rate_ors' class="form-control-sm form-control"
									placeholder="0" onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction11()" autocomplete="off"></td>
								<td colspan=""><input size=2 type='text' name='rate_total'
									id='Respiratory_Group_rate_total'
									class="form-control-sm form-control" placeholder="0"
									onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction11()" autocomplete="off"
									readonly="readonly"></td>
							</tr>

						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</form:form>
<form id="h1n1_total_table_form">
	<div class="card-header"
		style="border: 1px solid rgba(0, 0, 0, .125); text-align: center;"
		id="aa">
		<strong>H1N1 Influenza</strong>
	</div>
	<div class="card-body card-block">
		<div class="row">
			<div class="col-md-12">
				<div class="container" align="center">
					<table id=""
						class="table no-margin table-striped  table-hover  table-bordered"
						width="60%">

						<thead style="text-align: center;">
							<tr>
								<th colspan=""></th>
								<th style="font-size: 15px" colspan="">OFFICERS</th>
								<th style="font-size: 15px" colspan="">JCOs</th>
								<th style="font-size: 15px" colspan="">ORs</th>
								<th style="font-size: 15px" colspan="">TOTAL</th>
							</tr>

						</thead>

						<tbody style="text-align: center;">

							<tr>
								<th style="font-size: 15px">Number</th>
								
								<td colspan=""><input size=2 type='text'
									name='h1n1_influenza_number_officers'
									id='h1n1_influenza_number_officers'
									value="${add_prvnt_details_cmnd.h1n1_influenza_number_officers}"
									class="form-control-sm form-control" placeholder="0"
									onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction12()" autocomplete="off"></td>
								<td colspan=""><input size=2 type='text'
									name='h1n1_influenza_number_jcos'
									id='h1n1_influenza_number_jcos'
									value="${add_prvnt_details_cmnd.h1n1_influenza_number_jcos}"
									class="form-control-sm form-control" placeholder="0"
									onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction12()" autocomplete="off"></td>
								<td colspan=""><input size=2 type='text'
									name='h1n1_influenza_number_ors' id='h1n1_influenza_number_ors'
									value="${add_prvnt_details_cmnd.h1n1_influenza_number_ors}"
									class="form-control-sm form-control" placeholder="0"
									onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction12()" autocomplete="off"></td>
								<td colspan=""><input size=2 type='text'
									name='number_total' id='H1N1_number_total'
									class="form-control-sm form-control" placeholder="0"
									onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction12()" autocomplete="off"
									readonly="readonly"></td>
							</tr>

							<tr>
								<th style="font-size: 15px">Rate</th>
								<td colspan=""><input size=2 type='text'
									name='h1n1_influenza_rate_officers'
									id='h1n1_influenza_rate_officers'
									value="${add_prvnt_details_cmnd.h1n1_influenza_rate_officers}"
									class="form-control-sm form-control" placeholder="0"
									onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction12()" autocomplete="off"></td>
								<td colspan=""><input size=2 type='text'
									name='h1n1_influenza_rate_jcos' id='h1n1_influenza_rate_jcos'
									value="${add_prvnt_details_cmnd.h1n1_influenza_rate_jcos}"
									class="form-control-sm form-control" placeholder="0"
									onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction12()" autocomplete="off"></td>
								<td colspan=""><input size=2 type='text'
									name='h1n1_influenza_rate_ors'
									value="${add_prvnt_details_cmnd.h1n1_influenza_rate_ors}"
									id='h1n1_influenza_rate_ors'
									class="form-control-sm form-control" placeholder="0"
									onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction12()" autocomplete="off"></td>
								<td colspan=""><input size=2 type='text' name='rate_total'
									id='H1N1_rate_total' class="form-control-sm form-control"
									placeholder="0" onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction12()" autocomplete="off"
									readonly="readonly"></td>
							</tr>

						</tbody>
					</table>
				</div>
			</div>
		</div>


		<!-- ----------H1N1 GRID------- -->
		<div class="card-body card-block" id="h1n1_total_table">
			<table
				class="table no-margin table-striped  table-hover  table-bordered report_print "
				id="h1n1addQuantity">
				<thead style="color: white; text-align: center;">
					<tr>
						<th align="center">Ser No</th>
						<th>Unit Name</th>
						<th>No. Of Cases</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody id="h1n1addQuantitytbody">
					<c:if test="${listh1n1Details.size() == 0}">
						<tr id="">
							<td align="center">1</td>
							<td align="center"><input type="hidden" name=""
								id="h1n1_sus_no1" maxlength="40"
								class="form-control-sm form-control" autocomplete="off">
								<input type="text" name="unit_name1" id="h1n1_unit_name1"
								maxlength="40" class="form-control-sm form-control"
								autocomplete="off" onkeypress="h1n1UnitAuto(this)" /></td>
							<td align="center"><input type="text" id="no_cases1"
								name="no_cases1" class="form-control-sm form-control"
								autocomplete="off" placeholder="0"
								onkeypress="return isNumber0_9Only(event);"></td>



							<td align="center"><i class="fa fa-plus"
								onclick="h1n1formopen(1);" id="h1n1_id_add1" value="ADD"></i></td>
						</tr>
					</c:if>

					<c:if test="${listh1n1Details.size() != 0}">
						<c:forEach var="item" items="${listh1n1Details}" varStatus="num">
							<tr id="h1n1_tr_id${num.index+1}">
								<td style="text-align: center;">${num.index+1}</td>
								<td>${item[0]}</td>

								<td>${item[1]}</td>



								<td style="display: none;">${item[2]}</td>
								<td style="text-align: center;">${item[3]}</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>

		<div>
			<input type="hidden" id="h1n1_count" name="h1n1_count"> <input
				type="hidden" id="h1n1_oldcount" name="h1n1_oldcount"> <input
				style="float: right; margin: 5px" type="button"
				class="btn btn-success btn-sm" value="UPDATE"
				onclick="h1n1_formsubmit();">
		</div>
	</div>
</form>

<!-- H1N1 GRID END--->


<form:form id="skin_disease_dtl">
	<div class="card-header"
		style="border: 1px solid rgba(0, 0, 0, .125); text-align: center;"
		id="aa">
		<strong>Skin Disease</strong>
	</div>
	<div class="card-body card-block">
		<div class="row">
			<div class="col-md-12">
				<div class="container" align="center">
					<table id=""
						class="table no-margin table-striped  table-hover  table-bordered"
						width="60%">

						<thead style="text-align: center;">
							<tr>
								<th colspan=""></th>
								<th style="font-size: 15px" colspan="">OFFICERS</th>
								<th style="font-size: 15px" colspan="">JCOs</th>
								<th style="font-size: 15px" colspan="">ORs</th>
								<th style="font-size: 15px" colspan="">TOTAL</th>
							</tr>

						</thead>

						<tbody style="text-align: center;">

							<tr>
								<th style="font-size: 15px">Number</th>
								
								<td colspan=""><input size=2 type='text'
									name='skin_disease_number_officers'
									id='skin_disease_number_officers'
									value="${add_prvnt_details_cmnd.skin_disease_number_officers}"
									class="form-control-sm form-control" placeholder="0"
									onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction13()" autocomplete="off"></td>
								<td colspan=""><input size=2 type='text'
									name='skin_disease_number_jcos' id='skin_disease_number_jcos'
									value="${add_prvnt_details_cmnd.skin_disease_number_jcos}"
									class="form-control-sm form-control" placeholder="0"
									onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction13()" autocomplete="off"></td>
								<td colspan=""><input size=2 type='text'
									name='skin_disease_number_ors' id='skin_disease_number_ors'
									value="${add_prvnt_details_cmnd.skin_disease_number_ors}"
									class="form-control-sm form-control" placeholder="0"
									onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction13()" autocomplete="off"></td>
								<td colspan=""><input size=2 type='text'
									name='number_total' id='Skin_Dieseases_number_total'
									class="form-control-sm form-control" placeholder="0"
									onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction13()" autocomplete="off"
									readonly="readonly"></td>
							</tr>

							<tr>
								<th style="font-size: 15px">Rate</th>
								<td colspan=""><input size=2 type='text'
									name='skin_disease_rate_officers'
									id='skin_disease_rate_officers'
									value="${add_prvnt_details_cmnd.skin_disease_rate_officers}"
									class="form-control-sm form-control" placeholder="0"
									onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction13()" autocomplete="off"></td>
								<td colspan=""><input size=2 type='text'
									name='skin_disease_rate_jcos' id='skin_disease_rate_jcos'
									value="${add_prvnt_details_cmnd.skin_disease_rate_jcos}"
									class="form-control-sm form-control" placeholder="0"
									onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction13()" autocomplete="off"></td>
								<td colspan=""><input size=2 type='text'
									name='skin_disease_rate_ors'
									value="${add_prvnt_details_cmnd.skin_disease_rate_ors}"
									id='skin_disease_rate_ors' class="form-control-sm form-control"
									placeholder="0" onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction13()" autocomplete="off"></td>
								<td colspan=""><input size=2 type='text' name='rate_total'
									id='Skin_Dieseases_rate_total'
									class="form-control-sm form-control" placeholder="0"
									onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction13()" autocomplete="off"
									readonly="readonly"></td>
							</tr>

						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</form:form>

<form id="injuries_nea_form">

	<div class="card-header"
		style="border: 1px solid rgba(0, 0, 0, .125); text-align: center;"
		id="aa">
		<strong>Injuries NEA</strong>
	</div>
	<div class="card-body card-block">
		<div class="row">
			<div class="col-md-12">
				<div class="container" align="center">
					<table id=""
						class="table no-margin table-striped  table-hover  table-bordered"
						width="60%">

						<thead style="text-align: center;">
							<tr>
								<th colspan=""></th>
								<th style="font-size: 15px" colspan="">OFFICERS</th>
								<th style="font-size: 15px" colspan="">JCOs</th>
								<th style="font-size: 15px" colspan="">ORs</th>
								<th style="font-size: 15px" colspan="">TOTAL</th>
							</tr>

						</thead>

						<tbody style="text-align: center;">

							<tr>
								<th style="font-size: 15px">Number</th>
								
								<td colspan=""><input size=2 type='text'
									name='injuries_nea_number_officers'
									id='injuries_nea_number_officers'
									value="${add_prvnt_details_cmnd.injuries_nea_number_officers}"
									class="form-control-sm form-control" placeholder="0"
									onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction14()" autocomplete="off"></td>
								<td colspan=""><input size=2 type='text'
									name='injuries_nea_number_jcos' id='injuries_nea_number_jcos'
									value="${add_prvnt_details_cmnd.injuries_nea_number_jcos}"
									class="form-control-sm form-control" placeholder="0"
									onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction14()" autocomplete="off"></td>
								<td colspan=""><input size=2 type='text'
									name='injuries_nea_number_ors' id='injuries_nea_number_ors'
									value="${add_prvnt_details_cmnd.injuries_nea_number_ors}"
									class="form-control-sm form-control" placeholder="0"
									onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction14()" autocomplete="off"></td>
								<td colspan=""><input size=2 type='text'
									name='number_total' id='Injuries_NEA_number_total'
									class="form-control-sm form-control" placeholder="0"
									onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction14()" autocomplete="off"
									readonly="readonly"></td>
							</tr>

							<tr>
								<th style="font-size: 15px">Rate</th>
								<td colspan=""><input size=2 type='text'
									name='injuries_nea_rate_officers'
									id='injuries_nea_rate_officers'
									value="${add_prvnt_details_cmnd.injuries_nea_rate_officers}"
									class="form-control-sm form-control" placeholder="0"
									onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction14()" autocomplete="off"></td>
								<td colspan=""><input size=2 type='text'
									name='injuries_nea_rate_jcos' id='injuries_nea_rate_jcos'
									value="${add_prvnt_details_cmnd.injuries_nea_rate_jcos}"
									class="form-control-sm form-control" placeholder="0"
									onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction14()" autocomplete="off"></td>
								<td colspan=""><input size=2 type='text'
									name='injuries_nea_rate_ors'
									value="${add_prvnt_details_cmnd.injuries_nea_rate_ors}"
									id='injuries_nea_rate_ors' class="form-control-sm form-control"
									placeholder="0" onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction14()" autocomplete="off"></td>
								<td colspan=""><input size=2 type='text' name='rate_total'
									id='Injuries_NEA_rate_total'
									class="form-control-sm form-control" placeholder="0"
									onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction14()" autocomplete="off"
									readonly="readonly"></td>
							</tr>

						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<!-- ----------INJURIES GRID------- -->
	<div class="card-body card-block" id="injuries_total_table">
		<table
			class="table no-margin table-striped  table-hover  table-bordered report_print "
			id="injuriesaddQuantity">
			<thead style="color: white; text-align: center;">
				<tr>
					<th align="center">Ser No</th>
					<th>Unit Name</th>
					<th>Diagnosis</th>
					<th>Mode Of Injuries</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${listneaDetails.size() == 0}">
					<tr id="">

						<td align="center">1</td>
						<td align="center"><input type="hidden" name=""
							id="inj_sus_no1" maxlength="40"
							class="form-control-sm form-control" autocomplete="off">
							<input type="text" name="unit_name1" id="inj_unit_name1"
							maxlength="40" class="form-control-sm form-control"
							autocomplete="off" onkeypress="injUnitAuto(this)" /></td>
						<td align="center"><input type="text" id="diagnosis1"
							name="diagnosis1" class="form-control-sm form-control"
							onchange="split()" autocomplete="off"></td>
						<td align="center"><select id="mode_of_injury1"
							name="mode_of_injury1" class="form-control-sm form-control"
							autocomplete="off">
								<option value="0">--Select--</option>
								<c:forEach var="item" items="${PREVENTION_INJURIESList}"
									varStatus="num">
									<option value="${item[0]}">${item[1]}</option>
								</c:forEach>
						</select></td>

						<td id="c_nea_idtd1" style="display: none;"><input
							type="text" id="c_nea_id1" name="c_nea_id1"
							class="form-control-sm form-control" value="0" autocomplete="off"></td>
						<td align="center"><i class="fa fa-plus"
							onclick="injuriesformopen(1);" id="injuries_id_add1" value="ADD"></i>
							</a></td>
					</tr>
				</c:if>

				<c:if test="${listneaDetails.size() != 0}">
					<c:forEach var="item" items="${listneaDetails}" varStatus="num">
						<tr id="injuries_tr_id${num.index+1}">
							<td style="text-align: center;">${num.index+1}</td>
							<td>${item[0]}</td>
							<td>${item[1]}</td>
							<td>${item[2]}</td>
							<td style="display: none;">${item[3]}</td>
							<td style="text-align: center;">${item[4]}</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
	</div>



	<div>
		<input type="hidden" id="injuries_nea_count" name="injuries_nea_count">
		<input type="hidden" id="injuries_nea_oldcount"
			name="injuries_nea_oldcount"> <input
			style="float: right; margin: 5px" type="button"
			class="btn btn-success btn-sm" value="UPDATE"
			onclick="injuries_nea_formsubmit();">
	</div>

</form>

<!-- INJURIES GRID END--->

<form:form id="heat_cold_attlitude_dtl">
	<div class="card-header"
		style="border: 1px solid rgba(0, 0, 0, .125); text-align: center;"
		id="aa">
		<strong>Effect of Heat</strong>
	</div>
	<div class="card-body card-block">
		<div class="row">
			<div class="col-md-12">
				<div class="container" align="center">
					<table id=""
						class="table no-margin table-striped  table-hover  table-bordered"
						width="60%">

						<thead style="text-align: center;">
							<tr>
								<th colspan=""></th>
								<th style="font-size: 15px" colspan="">OFFICERS</th>
								<th style="font-size: 15px" colspan="">JCOs</th>
								<th style="font-size: 15px" colspan="">ORs</th>
								<th style="font-size: 15px" colspan="">TOTAL</th>
							</tr>
						</thead>

						<tbody style="text-align: center;">
							<tr>
								<th style="font-size: 15px">Number</th>
								
								<td colspan=""><input size=2 type='text'
									name='effect_of_heat_number_officers'
									value="${add_prvnt_details_cmnd.effect_of_heat_number_officers}"
									id='effect_of_heat_number_officers'
									class="form-control-sm form-control" placeholder="0"
									onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction1()" autocomplete="off"></td>
								<td colspan=""><input size=2 type='text'
									name='effect_of_heat_number_jcos'
									id='effect_of_heat_number_jcos'
									value="${add_prvnt_details_cmnd.effect_of_heat_number_jcos}"
									class="form-control-sm form-control" placeholder="0"
									onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction1()" autocomplete="off"></td>
								<td colspan=""><input size=2 type='text'
									name='effect_of_heat_number_ors' id='effect_of_heat_number_ors'
									value="${add_prvnt_details_cmnd.effect_of_heat_number_ors}"
									class="form-control-sm form-control" placeholder="0"
									onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction1()" autocomplete="off"></td>
								<td colspan=""><input size=2 type='text'
									name='effect_heat_number_total' id='effect_heat_number_total'
									class="form-control-sm form-control" placeholder="0"
									onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction1()" autocomplete="off" readonly="readonly"></td>
							</tr>

							<tr>
								<th style="font-size: 15px">Rate</th>
								<td colspan=""><input size=2 type='text'
									name='effect_of_heat_rate_officers'
									value="${add_prvnt_details_cmnd.effect_of_heat_rate_officers}"
									id='effect_of_heat_rate_officers'
									class="form-control-sm form-control" placeholder="0"
									onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction1()" autocomplete="off"></td>
								<td colspan=""><input size=2 type='text'
									name='effect_of_heat_rate_jcos' id='effect_of_heat_rate_jcos'
									value="${add_prvnt_details_cmnd.effect_of_heat_rate_jcos}"
									class="form-control-sm form-control" placeholder="0"
									onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction1()" autocomplete="off"></td>
								<td colspan=""><input size=2 type='text'
									name='effect_of_heat_rate_ors' id='effect_of_heat_rate_ors'
									value="${add_prvnt_details_cmnd.effect_of_heat_rate_ors}"
									class="form-control-sm form-control" placeholder="0"
									onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction1()" autocomplete="off"></td>
								<td colspan=""><input size=2 type='text'
									name='effect_heat_rate_total' id='effect_heat_rate_total'
									class="form-control-sm form-control" placeholder="0"
									onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction1()" autocomplete="off" readonly="readonly"></td>
							</tr>

						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div class="card-header"
		style="border: 1px solid rgba(0, 0, 0, .125); text-align: center;"
		id="aa">
		<strong>Effect of Cold</strong>
	</div>
	<div class="card-body card-block">
		<div class="row">
			<div class="col-md-12">
				<div class="container" align="center">
					<table id=""
						class="table no-margin table-striped  table-hover  table-bordered"
						width="60%">

						<thead style="text-align: center;">
							<tr>
								<th colspan=""></th>
								<th style="font-size: 15px" colspan="">OFFICERS</th>
								<th style="font-size: 15px" colspan="">JCOs</th>
								<th style="font-size: 15px" colspan="">ORs</th>
								<th style="font-size: 15px" colspan="">TOTAL</th>
							</tr>

						</thead>
						<tbody style="text-align: center;">
							<tr>
								<th style="font-size: 15px">Number</th>
								
								<td colspan=""><input size=2 type='text'
									name='effect_of_cold_number_officers'
									value="${add_prvnt_details_cmnd.effect_of_cold_number_officers}"
									id='effect_of_cold_number_officers'
									class="form-control-sm form-control" placeholder="0"
									onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction2()" autocomplete="off"></td>
								<td colspan=""><input size=2 type='text'
									name='effect_of_cold_number_jcos'
									id='effect_of_cold_number_jcos'
									value="${add_prvnt_details_cmnd.effect_of_cold_number_jcos}"
									class="form-control-sm form-control" placeholder="0"
									onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction2()" autocomplete="off"></td>
								<td colspan=""><input size=2 type='text'
									name='effect_of_cold_number_ors' id='effect_of_cold_number_ors'
									value="${add_prvnt_details_cmnd.effect_of_cold_number_ors}"
									class="form-control-sm form-control" placeholder="0"
									onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction2()" autocomplete="off"></td>
								<td colspan=""><input size=2 type='text'
									name='effect_cold_number_total' id='effect_cold_number_total'
									class="form-control-sm form-control" placeholder="0"
									onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction2()" autocomplete="off" readonly="readonly"></td>
							</tr>

							<tr>
								<th style="font-size: 15px">Rate</th>
								<td colspan=""><input size=2 type='text'
									name='effect_of_cold_rate_officers'
									value="${add_prvnt_details_cmnd.effect_of_cold_rate_officers}"
									id='effect_of_cold_rate_officers'
									class="form-control-sm form-control" placeholder="0"
									onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction2()" autocomplete="off"></td>
								<td colspan=""><input size=2 type='text'
									name='effect_of_cold_rate_jcos' id='effect_of_cold_rate_jcos'
									value="${add_prvnt_details_cmnd.effect_of_cold_rate_jcos}"
									class="form-control-sm form-control" placeholder="0"
									onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction2()" autocomplete="off"></td>
								<td colspan=""><input size=2 type='text'
									name='effect_of_cold_rate_ors' id='effect_of_cold_rate_ors'
									value="${add_prvnt_details_cmnd.effect_of_cold_rate_ors}"
									class="form-control-sm form-control" placeholder="0"
									onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction2()" autocomplete="off"></td>
								<td colspan=""><input size=2 type='text'
									name='effect_cold_rate_total' id='effect_cold_rate_total'
									class="form-control-sm form-control" placeholder="0"
									onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction2()" autocomplete="off" readonly="readonly"></td>
							</tr>

						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div class="card-header"
		style="border: 1px solid rgba(0, 0, 0, .125); text-align: center;"
		id="aa">
		<strong>Effect of High Altitude</strong>
	</div>
	<div class="card-body card-block">
		<div class="row">
			<div class="col-md-12">
				<div class="container" align="center">
					<table id=""
						class="table no-margin table-striped  table-hover  table-bordered"
						width="60%">
						<thead style="text-align: center;">
							<tr>
								<th colspan=""></th>
								<th style="font-size: 15px" colspan="">OFFICERS</th>
								<th style="font-size: 15px" colspan="">JCOs</th>
								<th style="font-size: 15px" colspan="">ORs</th>
								<th style="font-size: 15px" colspan="">TOTAL</th>
							</tr>
						</thead>

						<tbody style="text-align: center;">

							<tr>
								<th style="font-size: 15px">Number</th>
								
								<td colspan=""><input size=2 type='text'
									name='effect_of_high_altitude_number_officers'
									value="${add_prvnt_details_cmnd.effect_of_high_altitude_number_officers}"
									id='effect_of_high_altitude_number_officers'
									class="form-control-sm form-control" placeholder="0"
									onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction3()" autocomplete="off"></td>
								<td colspan=""><input size=2 type='text'
									name='effect_of_high_altitude_number_jcos'
									value="${add_prvnt_details_cmnd.effect_of_high_altitude_number_jcos}"
									id='effect_of_high_altitude_number_jcos'
									class="form-control-sm form-control" placeholder="0"
									onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction3()" autocomplete="off"></td>
								<td colspan=""><input size=2 type='text'
									name='effect_of_high_altitude_number_ors'
									value="${add_prvnt_details_cmnd.effect_of_high_altitude_number_ors}"
									id='effect_of_high_altitude_number_ors'
									class="form-control-sm form-control" placeholder="0"
									onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction3()" autocomplete="off"></td>
								<td colspan=""><input size=2 type='text'
									name='effect_altitude_number_total'
									id='effect_altitude_number_total'
									class="form-control-sm form-control" placeholder="0"
									onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction3()" autocomplete="off" readonly="readonly"></td>
							</tr>

							<tr>
								<th style="font-size: 15px">Rate</th>
								<td colspan=""><input size=2 type='text'
									name='effect_of_high_altitude_rate_officers'
									value="${add_prvnt_details_cmnd.effect_of_high_altitude_rate_officers}"
									id='effect_of_high_altitude_rate_officers'
									class="form-control-sm form-control" placeholder="0"
									onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction3()" autocomplete="off"></td>
								<td colspan=""><input size=2 type='text'
									name='effect_of_high_altitude_rate_jcos'
									value="${add_prvnt_details_cmnd.effect_of_high_altitude_rate_jcos}"
									id='effect_of_high_altitude_rate_jcos'
									class="form-control-sm form-control" placeholder="0"
									onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction3()" autocomplete="off"></td>
								<td colspan=""><input size=2 type='text'
									name='effect_of_high_altitude_rate_ors'
									value="${add_prvnt_details_cmnd.effect_of_high_altitude_rate_ors}"
									id='effect_of_high_altitude_rate_ors'
									class="form-control-sm form-control" placeholder="0"
									onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction3()" autocomplete="off"></td>
								<td colspan=""><input size=2 type='text'
									name='effect_altitude_rate_total'
									id='effect_altitude_rate_total'
									class="form-control-sm form-control" placeholder="0"
									onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction3()" autocomplete="off" readonly="readonly"></td>
							</tr>

						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</form:form>

<form id="pulmonary_tuberculosis_form">

	<div class="card-header"
		style="border: 1px solid rgba(0, 0, 0, .125); text-align: center;"
		id="aa">
		<strong>Pulmonary Tuberculosis</strong>
	</div>
	<div class="card-body card-block">
		<div class="row">
			<div class="col-md-12">
				<div class="container" align="center">
					<table id=""
						class="table no-margin table-striped  table-hover  table-bordered"
						width="60%">

						<thead style="text-align: center;">
							<tr>
								<th colspan=""></th>
								<th style="font-size: 15px" colspan="">OFFICERS</th>
								<th style="font-size: 15px" colspan="">JCOs</th>
								<th style="font-size: 15px" colspan="">ORs</th>
								<th style="font-size: 15px" colspan="">TOTAL</th>
							</tr>

						</thead>

						<tbody style="text-align: center;">
							<tr>
								<th style="font-size: 15px">Number</th>
								
								<td colspan=""><input size=2 type='text'
									name='pulmonary_tuberculosis_number_officers'
									value="${add_prvnt_details_cmnd.pulmonary_tuberculosis_number_officers}"
									id='pulmonary_tuberculosis_number_officers'
									class="form-control-sm form-control" placeholder="0"
									onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction15()" autocomplete="off"></td>
								<td colspan=""><input size=2 type='text'
									name='pulmonary_tuberculosis_number_jcos'
									id='pulmonary_tuberculosis_number_jcos'
									value="${add_prvnt_details_cmnd.pulmonary_tuberculosis_number_jcos}"
									class="form-control-sm form-control" placeholder="0"
									onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction15()" autocomplete="off"></td>
								<td colspan=""><input size=2 type='text'
									name='pulmonary_tuberculosis_number_ors'
									id='pulmonary_tuberculosis_number_ors'
									class="form-control-sm form-control" placeholder="0"
									value="${add_prvnt_details_cmnd.pulmonary_tuberculosis_number_ors}"
									onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction15()" autocomplete="off"></td>
								<td colspan=""><input size=2 type='text'
									name='number_total' id='Pulm_Tuber_Culosis_number_total'
									class="form-control-sm form-control" placeholder="0"
									onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction15()" autocomplete="off"
									readonly="readonly"></td>
							</tr>

							<tr>
								<th style="font-size: 15px">Rate</th>
								<td colspan=""><input size=2 type='text'
									name='pulmonary_tuberculosis_rate_officer'
									id='pulmonary_tuberculosis_rate_officer'
									class="form-control-sm form-control" placeholder="0"
									value="${add_prvnt_details_cmnd.pulmonary_tuberculosis_rate_officer}"
									onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction15()" autocomplete="off"></td>
								<td colspan=""><input size=2 type='text'
									name='pulmonary_tuberculosis_rate_jcos'
									id='pulmonary_tuberculosis_rate_jcos'
									value="${add_prvnt_details_cmnd.pulmonary_tuberculosis_rate_jcos}"
									class="form-control-sm form-control" placeholder="0"
									onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction15()" autocomplete="off"></td>
								<td colspan=""><input size=2 type='text'
									name='pulmonary_tuberculosis_rate_ors'
									value="${add_prvnt_details_cmnd.pulmonary_tuberculosis_rate_ors}"
									id='pulmonary_tuberculosis_rate_ors'
									class="form-control-sm form-control" placeholder="0"
									onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction15()" autocomplete="off"></td>
								<td colspan=""><input size=2 type='text' name='rate_total'
									id='Pulm_Tuber_Culosis_rate_total'
									class="form-control-sm form-control" placeholder="0"
									onkeypress="return isNumber0_9Only(event);"
									onchange="myFunction15()" autocomplete="off"
									readonly="readonly"></td>
							</tr>

						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>



	<!-- ---------- GRID------- -->
	<div class="card-body card-block" id="total_table">
		<table
			class="table no-margin table-striped  table-hover  table-bordered report_print "
			id="addQuantity">
			<thead style="color: white; text-align: center;">
				<tr>
					<th align="center">Ser No</th>
					<th width="300px">Personnel No</th>
					<th>Category</th>
					<th>Rank</th>
					<th>Personnel Name</th>
					<th>Unit Name</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${listpultubDetails.size() == 0}">
					<tr id="">
						<td align="center">1</td>
						<td align="center" width="300px">
							<div class="col-md-14 row form-group">
								<div class="col-md-4">
									<select id="personnel_number11" name="personnel_number11"
										class="form-control-sm form-control"
										onchange="per_nochange(1)">
										<option value="-1">--Select--</option>
										<c:forEach var="item" items="${ml_2}" varStatus="num">
											<option value="${item}" name="${item}">${item}</option>
										</c:forEach>
									</select>
								</div>

								<div class="col-md-4">
									<input type="text" id="personnel_number21"
										name="personnel_number21" class="form-control-sm form-control"
										autocomplete="off" placeholder="Enter No..." maxlength="10">
								</div>

								<div class="col-md-4">
									<select id="personnel_number31" name="personnel_number31"
										class="form-control-sm form-control">
										<option value="-1">--Select--</option>
										<c:forEach var="item" items="${ml_3}" varStatus="num">
											<option value="${item}" name="${item}">${item}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</td>
						<td align="center"><select name="category1" id="category1"
							class="form-control-sm form-control">
								<option value="2">--Select--</option>
								<c:forEach var="item" items="${ml_1}" varStatus="num">
									<option value="${item}" name="${item}">${item}</option>
								</c:forEach>
						</select></td>

						<td align="center"><select id="rank1" name="rank1"
							class="form-control-sm form-control" autocomplete="off">
						</select></td>

						<td align="center"><input type="text" id="personnel_name1"
							name="personnel_name1" class="form-control-sm form-control"
							autocomplete="off"></td>
						<td align="center"><input type="hidden" id="pul_sus_no1"
							name="" class="form-control autocomplete" autocomplete="off">
							<input type="text" id="pul_unit_name1" name="unit_name1"
							class="form-control-sm form-control"
							onkeypress="pulUnitAuto(this);" autocomplete="off"></td>
						<td id="c_pultub_idtd1" style="display: none;"><input
							type="text" id="c_pul_tub_id1" name="c_pul_tub_id1"
							class="form-control-sm form-control" value="0" autocomplete="off"></td>

						<td align="center"><i class="fa fa-plus"
							onclick="formopen(1);" id="id_add1" value="ADD"></i> </a></td>
					</tr>
				</c:if>

				<c:if test="${listpultubDetails.size() != 0}">
					<c:forEach var="item" items="${listpultubDetails}" varStatus="num">
						<tr id="tr_id${num.index+1}">
							<td style="text-align: center;">${num.index+1}</td>
							<td align="center" width="300px"><div
									class="col-md-14 row form-group">
									<div class="col-md-4">${item[0]}</div>
									<div class="col-md-4">${item[1]}</div>
									<div class="col-md-4">${item[2]}</div>
								</div></td>

							<td>${item[3]}</td>

							<td>${item[4]}</td>

							<td>${item[5]}</td>
							<td>${item[6]}</td>

							<td style="display: none;">${item[7]}</td>
							<td style="text-align: center;">${item[8]}</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
	</div>
	<div>
		<input type="hidden" id="count" name="count"> <input
			type="hidden" id="oldcount" name="oldcount"> <input
			type="hidden" id="service" name="service"
			class="form-control-sm form-control" autocomplete="off"
			readonly="readonly" value="ARMY"> <input
			style="float: right; margin: 5px" type="button"
			class="btn btn-success btn-sm" value="UPDATE"
			onclick="pultub_formsubmit();">
	</div>

</form>

<!--  GRID END--->
<form:form id="other_dtl">
	<div class="card-header"
		style="border: 1px solid rgba(0, 0, 0, .125); text-align: center;"
		id="aa">
		<strong></strong>
	</div>
	<div class="card-body card-block">
		<div class="col-md-12 row form-group">
			<div class="col-md-2" style="text-align: left;">
				<label for="text-input" class=" form-control-label"> Number
					Of Dog Bites</label>
			</div>
			<div class="col-md-4">
				<input type="text" id="number_of_dog_bites"
					name="number_of_dog_bites"
					value="${add_prvnt_details_cmnd.number_of_dog_bites}"
					class="form-control-sm form-control" autocomplete="off"
					placeholder="0" onkeypress="return isNumber0_9Only(event);">
			</div>

			<div class="col-md-2" style="text-align: left;">
				<label for="text-input" class=" form-control-label"> Number
					Of Rabies Cases</label>
			</div>
			<div class="col-md-4">
				<input type="text" id="number_of_rabies_cases"
					name="number_of_rabies_cases"
					value="${add_prvnt_details_cmnd.number_of_rabies_cases}"
					class="form-control-sm form-control" autocomplete="off"
					placeholder="0" onkeypress="return isNumber0_9Only(event);">
			</div>
		</div>
		<div class="col-md-12 row form-group">
			<div class="col-md-2" style="text-align: left;">
				<label for="text-input" class=" form-control-label"> Number
					Of Snake Bite Cases</label>
			</div>
			<div class="col-md-4">
				<input type="text" id="number_of_snake_bite_cases"
					name="number_of_snake_bite_cases"
					value="${add_prvnt_details_cmnd.number_of_snake_bite_cases}"
					class="form-control-sm form-control" autocomplete="off"
					placeholder="0" onkeypress="return isNumber0_9Only(event);">
			</div>
		</div>

	</div>
</form:form>
<c:url value="search_preventiveurlAction" var="search_preventiveurlAction" />
		<form:form action="${search_preventiveurlAction}"  method="POST" id="searchprevention" name="searchprevention" >
				<input type="hidden" name="unit1" id="unit1" value="${add_prvnt_details_cmnd.sho_name}"/>
				<input type="hidden" name="sus1" id="sus1" value="${add_prvnt_details_cmnd.sus_no}"/>
				<input type="hidden" name="month1" id="month1" value="${add_prvnt_details_cmnd.month}"/>
				<input type="hidden" name="year1" id="year1" value="${add_prvnt_details_cmnd.year}"/>
</form:form>

<!-- end of montdtls -->
</div>

<div id="save2" class="card-footer" align="center">
	<!-- <input type="reset" class="btn btn-success btn-sm" value="Clear"> -->
	<input type="button" class="btn btn-primary btn-sm" value="Back"
		onclick="Backpage()"> <input type="button"
		class="btn btn-success btn-sm" value="Save" onclick="updateDtls()">
	<a href="mnh_input_preventivedetails" type="reset" class="btn btn-danger btn-sm">Cancel</a>
</div>
</div>
<!-- end of card-footer -->

</div>
<!-- end of card -->
<%-- </form:form> --%>
<!-- end of container -->


<script>
// FOR MILK 
		
function milkformopen(x){
	$("#milk_total_table").show();
	$("#milk_id_add"+x).hide();
	$("#milk_id_remove"+x).hide();
	
	 x= x+1;
	if(x < 51){
	 $("input#milk_count").val(x);
	 $("table#milkaddQuantity").append('<tr align="center" id="milk_tr_id'+x+'"><td>'+x+'</td>'
			+'<td><input type="text" name="milk_samplenum'+x+'" id="milk_samplenum'+x+'"  class="form-control"  /></td>'
	 		
			+'<td><select id="milk_spec_gravity'+x+'" name="milk_spec_gravity'+x+'" class="form-control-sm form-control" >'
			+'<option value="">--Select--</option>'
			+'<option value="1">Satisfactory</option>'
			+'<option value="2">Non Satisfactory</option>'
			+'</select></td>'
	 		
	 		+'<td><select id="milk_fat_con'+x+'" name="milk_fat_con'+x+'" class="form-control-sm form-control" >'
			+'<option value="">--Select--</option>'
			+'<option value="1">Satisfactory</option>'
			+'<option value="2">Non Satisfactory</option>'
			+'</select></td>'
	 		
			+'<td><select id="milk_tot_solid'+x+'" name="milk_tot_solid'+x+'" class="form-control-sm form-control" >'
			+'<option value="">--Select--</option>'
			+'<option value="1">Satisfactory</option>'
			+'<option value="2">Non Satisfactory</option>'
			+'</select></td>'
			+'<td id="c_milk_idtd'+x+'" style="display: none;">	<input type="text" id="c_milk_id'+x+'" name="c_milk_id'+x+'" class="form-control-sm form-control" value="0" autocomplete="off"></td>'

	 		+'<td><i class="fa fa-plus" value = "ADD" title = "ADD" id = "milk_id_add'+x+'" onclick="milkformopen('+x+');"></i></a><i class="action_icons action_delete" value="REMOVE" title = "REMOVE" id = "milk_id_remove'+x+'" onclick="milk_formopen_re('+x+');"></i></a></td>' 
	 		+'</tr>');
		 
		 
		}else{
				alert("Please Enter max 50 Quantity");
				 if ( x == 51){
					 x = x-1; 
					 $("#milk_id_remove"+x).show();
				 }	   
		}
}

function milk_formopen_re(R){
	 $("tr#milk_tr_id"+R).remove();
	R=R-1;

	 $("input#milk_count").val(R);
	 $("#milk_id_add"+R).show();
	 $("#milk_id_remove"+R).show();
	 
	
}

// END MILK

// FOR HEALTH EDUCATION 
		
function healthformopen(h){
	$("#health_total_table").show();
	$("#health_id_add"+h).hide();
	$("#health_id_remove"+h).hide();
	
	 h= h+1;
	if(h < 51){
	 $("input#health_count").val(h);
	 $("table#healthaddQuantity").append('<tr align="center" id="health_tr_id'+h+'"><td align="center" width="5%">'+h+'</td>'
			+'<td align="center" width="10%"><input type="text" name="health_topic'+h+'" id="health_topic'+h+'"   class="form-control-sm form-control" /></td>'
			+'<td align="center" width="10%"><input type="hidden" id="health_sus_no'+h+'"	name="" class="form-control autocomplete" autocomplete="off"><input type="text" name="health_unit_name'+h+'" id="health_unit_name'+h+'" onkeypress="healthUnitAuto(this);"  class="form-control-sm form-control" /></td>'
			
			+'<td align="center" width="15%"><select id="h_personnel_category'+h+'" name="h_personnel_category'+h+'" class="form-control-sm form-control">'
			+'<option value="-1">--Select--</option>'
			+'<c:forEach var="item" items="${ml_1}" varStatus="num">'
			+'<option value="${item}" name="${item}">${item}</option>'
			+'</c:forEach>'
			+'</select></td>'
	 		
			+'<td align="center" width="10%"><input type="text" name="h_number_present'+h+'" id="h_number_present'+h+'"   class="form-control-sm form-control" autocomplete="off" placeholder="0" onkeypress="return isNumber0_9Only(event);"/></td>'
			+'<td align="center" width="10%"><input type="text" name="h_remarks'+h+'" id="h_remarks'+h+'"  maxlength="250" class="form-control-sm form-control" autocomplete="off"/></td>'
			+'<td align="center" width="20%"><input type="file" name="hea_img'+h+'" id="hea_img'+h+'"   class="img" onchange="PreviewImage('+h+');"/><img style="display: none;" src="js/img/No_Image.jpg"	 id="healthimagepreview'+h+'" '									
			+'style="width: 100px; height: 100px;" /></td>'
			+'<td align="center" width="10%"><i class="fa fa-eye" onclick="healthimage('+h+');"></i> </td>'
			+'<td id="c_health_idtd'+h+'" style="display: none;">	<input type="text" id="c_health_id'+h+'" name="c_health_id'+h+'" class="form-control-sm form-control" value="0" autocomplete="off"></td>'

	 		+'<td align="center" width="10%"><i class="fa fa-plus" value = "ADD" title = "ADD" id = "health_id_add'+h+'" onclick="healthformopen('+h+');"></i></a><i class="action_icons action_delete" value="REMOVE" title = "REMOVE" id = "health_id_remove'+h+'" onclick="health_formopen_re('+h+');"></i></a>'
	 		+'</td>' 
			
	 		+'</tr>');
		 
		
		}else{
				alert("Please Enter max 50 Quantity");
				 if ( h == 51){
					 h = h-1; 
					 $("#health_id_remove"+h).show();
				 }	   
		}
}

function health_formopen_re(R){
	 $("tr#health_tr_id"+R).remove();
	 R=R-1
	 
	 $("input#health_count").val(R);
	 $("#health_id_add"+R).show();
	 $("#health_id_remove"+R).show();
	 
	
}

// END HEALTH EDUCATION 


// FOR WORKSHOP 
		
function workshopformopen(w){
	$("#workshop_total_table").show();
	$("#workshop_id_add"+w).hide();
	$("#workshop_id_remove"+w).hide();
	w=w+1;
	
	if(w < 51){
	 $("input#workshop_count").val(w);
	 $("table#workshopaddQuantity").append('<tr align="center" id="workshop_tr_id'+w+'"><td align="center"   width="5%">'+w+'</td>'
			+'<td align="center"   width="10%"><input type="text" name="topic'+w+'" id="topic'+w+'"   class="form-control-sm form-control" /></td>'
			+'<td align="center"   width="10%"><input type="hidden" id="workshop_sus_no'+w+'" name="" class="form-control autocomplete" autocomplete="off"><input type="text" name="unit_name'+w+'" id="workshop_unit_name'+w+'"  onkeypress="workshopUnitAuto(this);" class="form-control-sm form-control" /></td>'
			
			+'<td align="center"   width="15%"> <select id="target_group'+w+'" name="target_group'+w+'" class="form-control-sm form-control">'
			+'<option value="0">--Select--</option>'
			+'	<option value="1">Pers Group</option>'
			+'	<option value="2">Students</option>'
			+'	<option value="3">Teachers</option>'
			+'	<option value="4">Others</option>'
			+'</select></td>'			
			+'<td align="center"   width="10%"><input type="text" name="number_present'+w+'" id="number_present'+w+'"   class="form-control-sm form-control" autocomplete="off" placeholder="0" onkeypress="return isNumber0_9Only(event);"/></td>'
			+'<td align="center"   width="10%"><input type="text" name="remarks'+w+'" id="remarks'+w+'"  maxlength="250" class="form-control-sm form-control" autocomplete="off"/></td>'
			+'<td align="center"   width="20%"><input type="file" name="work_img'+w+'" id="work_img'+w+'"    class="img" onchange="workPreviewImage('+w+');" /><img style="display: none;" src="js/img/No_Image.jpg"	 id="workimagepreview'+w+'" '									
			+'style="width: 100px; height: 100px;" /></td>'
			+'<td align="center"   width="10%"><i class="fa fa-eye" onclick="workimage('+w+');"></i></td>'
			+'<td id="c_workshop_idtd'+w+'" style="display: none;">	<input type="text" id="c_workshop_id'+w+'" name="c_workshop_id'+w+'" class="form-control-sm form-control" value="0" autocomplete="off"></td>'
	 		+'<td align="center"   width="10%"><i class="fa fa-plus" value = "ADD" title = "ADD" id = "workshop_id_add'+w+'" onclick="workshopformopen('+w+');"></i></a><i class="action_icons action_delete" value="REMOVE" title = "REMOVE" id = "workshop_id_remove'+w+'" onclick="workshop_formopen_re('+w+');"></i></a></td>' 
	 		+'</tr>');
		 
		
		}else{
				alert("Please Enter max 50 Quantity");
				 if ( w == 51){
					 w = w-1; 
					 $("#workshop_id_remove"+w).show();
				 }	   
		}
}

function workshop_formopen_re(R){
	 $("tr#workshop_tr_id"+R).remove();
	R=R-1;
	 /* $("input#total_noof_items_reqd_to_conv_kits").val(w); */
	 $("input#workshop_count").val(R);
	 $("#workshop_id_add"+R).show();
	 $("#workshop_id_remove"+R).show();
	 
	
}

// END WORKSHOP

// FOR MALARIA 
	function malariaformopen(m){
	$("#malaria_total_table").show();
	$("#malaria_id_add"+m).hide();
	$("#malaria_id_remove"+m).hide();
	m=m+1;

	if(m < 51){
	 $("input#malaria_count").val(m);
	 $("table#malariaaddQuantity").append('<tr align="center" id="malaria_tr_id'+m+'"><td>'+m+'</td>'
			+'<td><input type="hidden" name="" id="mal_sus_no'+m+'"  class="form-control-sm form-control"  /><input type="text" name="unit_name'+m+'" id="mal_unit_name'+m+'" onkeypress="malUnitAuto(this);" class="form-control-sm form-control"  /></td>'
			+'<td><input type="text" name="bt'+m+'" id="bt'+m+'"  class="form-control-sm form-control" onchange="myFunctionMAl('+m+')"  autocomplete="off" placeholder="0" onkeypress="return isNumber0_9Only(event);" /></td>'
			+'<td><input type="text" name="mt'+m+'" id="mt'+m+'"  class="form-control-sm form-control"  onchange="myFunctionMAl('+m+')"  autocomplete="off" placeholder="0" onkeypress="return isNumber0_9Only(event);" /></td>'
			+'<td><input type="text" name="fl'+m+'" id="fl'+m+'"  class="form-control-sm form-control"  onchange="myFunctionMAl('+m+')"  autocomplete="off" placeholder="0" onkeypress="return isNumber0_9Only(event);" /></td>'
			+'<td><input type="text" name="fi'+m+'" id="fi'+m+'"  class="form-control-sm form-control"   onchange="myFunctionMAl('+m+')"  autocomplete="off" placeholder="0" onkeypress="return isNumber0_9Only(event);" /></td>'
			+'<td align="center"><input size=2 type="text" name="mala_total'+m+'" id="mala_total'+m+'" class="form-control-sm form-control" placeholder="0"'
			+'	onkeypress="return isNumber0_9Only(event);" autocomplete="off" readonly="readonly"></td>'
	 
	 		+'<td><i class="fa fa-plus" value = "ADD" title = "ADD" id = "malaria_id_add'+m+'" onclick="malariaformopen('+m+');"></i></a><i class="action_icons action_delete" value="REMOVE" title = "REMOVE" id = "malaria_id_remove'+m+'" onclick="malaria_formopen_re('+m+');"></i></a></td>' 
	 		+'</tr>');
		 
			 
		}else{
				alert("Please Enter max 50 Quantity");
				 if ( m == 51){
					 m = m-1; 
					 $("#malaria_id_remove"+m).show();
				 }	   
		}
}

function malaria_formopen_re(R){
	
	 $("tr#malaria_tr_id"+R).remove();
	 R = R-1;
	 
	 $("input#malaria_count").val(R);
	 $("#malaria_id_add"+R).show();
	 $("#malaria_id_remove"+R).show();
	 
	
}

// END MALARIA


// FOR STD 
		
function stdformopen(s){
	$("#std_total_table").show();
	$("#std_id_add"+s).hide();
	$("#std_id_remove"+s).hide();
	
	 s= s+1;
	if(s < 51){
	 $("input#std_count").val(s);
	 $("table#stdaddQuantity").append('<tr align="center" id="std_tr_id'+s+'"><td>'+s+'</td>'
			+'<td><input type="hidden" name=""id="std_sus_no'+s+'" maxlength="40"class="form-control-sm form-control" autocomplete="off"><input type="text" name="unit_name'+s+'" id="std_unit_name'+s+'" onkeypress="stdUnitAuto(this);" class="form-control-sm form-control"  /></td>'
			+'<td><input type="text" name="s_fl'+s+'" id="s_fl'+s+'"  class="form-control-sm form-control"  autocomplete="off" placeholder="0" onkeypress="return isNumber0_9Only(event);" onchange="myFunctionSTD('+s+')"   /></td>'
			+'<td><input type="text" name="s_fi'+s+'" id="s_fi'+s+'"  class="form-control-sm form-control"  autocomplete="off" placeholder="0" onkeypress="return isNumber0_9Only(event);" onchange="myFunctionSTD('+s+')"  /></td>'
			+'<td align="center">'
			+'<input size=2 type="text"  name="std_total'+s+'" id="std_total'+s+'" class="form-control-sm form-control" placeholder="0" onkeypress="return isNumber0_9Only(event);" autocomplete="off" readonly="readonly">'
			+'</td>'
			
			
			+'<td><i class="fa fa-plus" value = "ADD" title = "ADD" id = "std_id_add'+s+'" onclick="stdformopen('+s+');"></i></a><i class="action_icons action_delete" value="REMOVE" title = "REMOVE" id = "std_id_remove'+s+'" onclick="std_formopen_re('+s+');"></i></a></td>' 
	 		+'</tr>');
		 
	
		}else{
				alert("Please Enter max 50 Quantity");
				 if ( s == 51){
					 s = s-1; 
					 $("#std_id_remove"+s).show();
				 }	   
		}
}

function std_formopen_re(R){
	 $("tr#std_tr_id"+R).remove();
	R=R-1
	
	 $("input#std_count").val(R);
	 $("#std_id_add"+R).show();
	 $("#std_id_remove"+R).show();
	 
	
}

// END STD

// FOR hepatits 
		
function hepatitsformopen(h){
	$("#hepatits_total_table").show();
	$("#hepatits_id_add"+h).hide();
	$("#hepatits_id_remove"+h).hide();
	
	 h= h+1;
	if(h < 51){
	 $("input#viral_hepatitis_count").val(h);
	 $("table#hepatitsaddQuantity").append('<tr align="center" id="hepatits_tr_id'+h+'"><td>'+h+'</td>'
			+'<td><input type="hidden" name=""id="viral_sus_no'+h+'" maxlength="40"class="form-control-sm form-control" autocomplete="off"><input type="text" name="unit_name'+h+'" id="viral_unit_name'+h+'" onkeypress="viralUnitAuto(this);" class="form-control-sm form-control"  /></td>'
			+'<td><input type="text" name="h_fl'+h+'" id="h_fl'+h+'" onchange="myFunctionHEPA('+h+')"  class="form-control-sm form-control"  autocomplete="off" placeholder="0" onkeypress="return isNumber0_9Only(event);" /></td>'
			+'<td><input type="text" name="h_fi'+h+'" id="h_fi'+h+'"  onchange="myFunctionHEPA('+h+')" class="form-control-sm form-control"  autocomplete="off" placeholder="0" onkeypress="return isNumber0_9Only(event);" /></td>'
			+'<td align="center"><input size=2 type="text" name="hepa_total'+h+'" id="hepa_total'+h+'"class="form-control-sm form-control" placeholder="0"'
			+'	onkeypress="return isNumber0_9Only(event);" autocomplete="off" readonly="readonly"></td>'
	 
			
			
			
			+'<td><i class="fa fa-plus" value = "ADD" title = "ADD" id = "hepatits_id_add'+h+'" onclick="hepatitsformopen('+h+');"></i></a><i class="action_icons action_delete" value="REMOVE" title = "REMOVE" id = "hepatits_id_remove'+h+'" onclick="hepatits_formopen_re('+h+');"></i></a></td>' 
	 		+'</tr>');
		 
	 
		}else{
				alert("Please Enter max 50 Quantity");
				 if ( h == 51){
					 h = h-1; 
					 $("#hepatits_id_remove"+h).show();
				 }	   
		}
}

function hepatits_formopen_re(R){
	 $("tr#hepatits_tr_id"+R).remove();
	 R = R-1;
	 
	 $("input#viral_hepatitis_count").val(R);
	 $("#hepatits_id_add"+R).show();
	 $("#hepatits_id_remove"+R).show();
	 
	
}

// END HEPATITS

// FOR H1N1 
		
function h1n1formopen(hn){
	$("#h1n1_total_table").show();
	$("#h1n1_id_add"+hn).hide();
	$("#h1n1_id_remove"+hn).hide();
	
	 hn= hn+1;
	if(hn < 51){
	 $("input#h1n1_count").val(hn);
	 $("table#h1n1addQuantity").append('<tr align="center" id="h1n1_tr_id'+hn+'"><td>'+hn+'</td>'
			+'<td><input type="hidden" name=""id="h1n1_sus_no'+hn+'" maxlength="40"class="form-control-sm form-control" autocomplete="off"><input type="text" name="unit_name'+hn+'" id="h1n1_unit_name'+hn+'" onkeypress="h1n1UnitAuto(this);"  class="form-control-sm form-control"  /></td>'
			+'<td><input type="text" name="no_cases'+hn+'" id="no_cases'+hn+'"  class="form-control-sm form-control"  autocomplete="off" placeholder="0" onkeypress="return isNumber0_9Only(event);" /></td>'
	 		+'<td><i class="fa fa-plus" value = "ADD" title = "ADD" id = "h1n1_id_add'+hn+'" onclick="h1n1formopen('+hn+');"></i></a><i class="action_icons action_delete" value="REMOVE" title = "REMOVE" id = "h1n1_id_remove'+hn+'" onclick="h1n1_formopen_re('+hn+');"></i></a></td>' 
	 		+'</tr>');
		 
		
		}else{
				alert("Please Enter max 50 Quantity");
				 if ( hn == 51){
					 hn = hn-1; 
					 $("#h1n1_id_remove"+hn).show();
				 }	   
		}
}

function h1n1_formopen_re(R){
	 $("tr#h1n1_tr_id"+R).remove();
	 R = R-1;
	 $("input#h1n1_count").val(R);
	 $("#h1n1_id_add"+R).show();
	 $("#h1n1_id_remove"+R).show();
	 
}

// END H1N1
// FOR INJURIES 
		
function injuriesformopen(inj){
	$("#injuries_total_table").show();
	$("#injuries_id_add"+inj).hide();
	$("#injuries_id_remove"+inj).hide();
	
	 inj= inj+1;
	if(inj < 51){
	 $("input#injuries_nea_count").val(inj);
	 $("table#injuriesaddQuantity").append('<tr align="center" id="injuries_tr_id'+inj+'"><td>'+inj+'</td>'
			+'<td><input type="hidden" name=""id="inj_sus_no'+inj+'" maxlength="40"class="form-control-sm form-control" autocomplete="off"><input type="text" name="unit_name'+inj+'" id="inj_unit_name'+inj+'"   onkeypress="injUnitAuto(this);" class="form-control-sm form-control"  /></td>'
			
			+'<td><input type="text" name="diagnosis'+inj+'" id="diagnosis'+inj+'"  class="form-control-sm form-control" onchange="split()" autocomplete="off" /></td>'
			
			+'<td><select id="mode_of_injury'+inj+'" name="mode_of_injury'+inj+'" class="form-control-sm form-control">'
			+'<option value="0">--Select--</option><c:forEach var="item" items="${PREVENTION_INJURIESList}" varStatus="num">'
			+'<option value="${item[0]}" >${item[1]}</option>'
			+'</c:forEach>'
			+'</select></td>'
			
			+'<td id="c_nea_idtd'+inj+'" style="display: none;">	<input type="text" id="c_nea_id'+inj+'" name="c_nea_id'+inj+'" class="form-control-sm form-control" value="0" autocomplete="off"></td>'
			+'<td><i class="fa fa-plus" value = "ADD" title = "ADD" id = "injuries_id_add'+inj+'" onclick="injuriesformopen('+inj+');"></i></a><i class="action_icons action_delete" value="REMOVE" title = "REMOVE" id = "injuries_id_remove'+inj+'" onclick="injuries_formopen_re('+inj+');"></i></a></td>' 
	 		+'</tr>');
		 
		}else{
				alert("Please Enter max 50 Quantity");
				 if ( inj == 51){
					 inj = inj-1; 
					 $("#injuries_id_remove"+inj).show();
				 }	   
		}
}

function injuries_formopen_re(R){
	
	 $("tr#injuries_tr_id"+R).remove();
	 R = R-1;
	 $("input#injuries_nea_count").val(R);
	 $("#injuries_id_add"+R).show();
	 $("#injuries_id_remove"+R).show();
	 
}

// END INJURIES

// FOR GRID 
			
function formopen(g){
	$("#total_table").show();
	$("#id_add"+g).hide();
	$("#id_remove"+g).hide();
	
	 g= g+1;
	if(g < 51){
	 $("input#count").val(g);
	 $("table#addQuantity").append('<tr align="center" id="tr_id'+g+'"><td>'+g+'</td>'
			 
			 
			 +'<td align="center" width="300px">'
			 +'<div class="col-md-14 row form-group" >'
			 +'<div class="col-md-4" >'  
			 +'<select name="personnel_number1'+g+'" id="personnel_number1'+g+'" class="form-control-sm form-control" onchange="per_nochange('+g+')">'
			 +'<option value="-1">--Select--</option>'
			 +'<c:forEach var="item" items="${ml_2}" varStatus="num">'
			 +'<option value="${item}" name="${item}">${item}</option>'
			 +'</c:forEach>'
			 +'</select>'
			 +'</div>'
			 +'<div class="col-md-4" >'
			 +'<input type="text" name="personnel_number2'+g+'" id="personnel_number2'+g+'"  class="form-control-sm form-control" autocomplete="off"'
			 +'placeholder="Enter No..." maxlength="10">'
			 +'</div>'
			 +'<div class="col-md-4" >'
			 +'<select name="personnel_number3'+g+'" id="personnel_number3'+g+'" class="form-control-sm form-control">'
			 +'<option value="-1">--Select--</option>'
			 +'<c:forEach var="item" items="${ml_3}" varStatus="num">'
			 +'<option value="${item}" name="${item}">${item}</option>'
			 +'</c:forEach>'
			 +'</select>'
			 +'</div>'
			 +'</div>'
			 +'</td>'
			 
			 +'<td align="center">' 
			 +'<input type="hidden"  name="service'+g+'" id="service'+g+'" class="form-control-sm form-control" autocomplete="off" readonly="readonly" value="ARMY"> '
			 +'<select  name="category'+g+'" id="category'+g+'" class="form-control-sm form-control">'
			 +'<option value="2">--Select--</option>'
			 +'<c:forEach var="item" items="${ml_1}" varStatus="num">'
			 +'<option value="${item}" name="${item}">${item}</option>'
			 +'</c:forEach>'
			 +'</select>'
			 +'</td>'
			 
			 +'<td align="center">'
			 +'<select  name="rank'+g+'" id="rank'+g+'"class="form-control-sm form-control" autocomplete="off">'
			 +'</select>'
			 +'</td>'
				
			 +'<td align="center">' 
			 +'<input type="text"  name="personnel_name'+g+'" id="personnel_name'+g+'" class="form-control-sm form-control" autocomplete="off">'
			 +'</td>'
			 
			+'<td><input type="hidden" name=""id="pul_sus_no'+g+'" maxlength="40"class="form-control-sm form-control" autocomplete="off"><input type="text" name="unit_name'+g+'" id="pul_unit_name'+g+'" onkeypress="pulUnitAuto(this);" class="form-control-sm form-control"  /></td>'
			+'<td id="c_pultub_idtd'+g+'" style="display: none;">	<input type="text" id="c_pul_tub_id'+g+'" name="c_pul_tub_id'+g+'" class="form-control-sm form-control" value="0" autocomplete="off"></td>'

			+'<td><i class="fa fa-plus" value = "ADD" title = "ADD" id = "id_add'+g+'" onclick="formopen('+g+');"></i></a><i class="action_icons action_delete" value="REMOVE" title = "REMOVE" id = "id_remove'+g+'" onclick="formopen_re('+g+');"></i></a></td>' 
	 		+'</tr>');
		 
		}else{
				alert("Please Enter max 50 Quantity");
				 if ( g == 51){
					 g = g-1; 
					 $("#id_remove"+g).show();$("#injuries_id_remove"+R).show();
				 }	   
		}
}
function formopen_re(R){
	 $("tr#tr_id"+R).remove();
	 R=R-1
	 $("input#count").val(R);
	 $("#id_add"+R).show();
	 $("#id_remove"+R).show();
	 
}

// END GRID


</script>





<script>
	

	$(document).ready(function() {
		$('#actsho').show();
		$('#save1').hide();
		$('#montdtls').hide();
		$('#save2').hide();
		SetValues();

	});
	
	function SetValues(){
		$("#id").val('${add_prvnt_details_cmnd.id}');
		if(${update == "update"})
		{ 
		
		document.getElementById("lbdad").textContent="UPDATE";
		}
		if($("#id").val() != "0"){
			document.getElementById("special_details").value = '${add_prvnt_details_cmnd.special_details}';
			var date_from1 = '${add_prvnt_details_cmnd.date_from}'.substring(0, 10); 
			$("#date_from").val(date_from1);
			var date_to1 = '${add_prvnt_details_cmnd.date_to}'.substring(0, 10); 
			$("#date_to").val(date_to1);
			$("select#antilarval_measure_undertaken").val('${add_prvnt_details_cmnd.antilarval_measure_undertaken}');
			$("select#special_pulse_polio").val('${add_prvnt_details_cmnd.special_pulse_polio}');
			$("select#special_other_campaigns").val('${add_prvnt_details_cmnd.special_other_campaigns}');
			myFunction3();//high_altitude
			myFunction2();//cold
			myFunction1();//heat
			myFunction13();//skin
			myFunction11();//respri
			myFunction16();//diarr
			myFunction9();//immune
			myFunction7();//chikanguniya
			myFunction5();//dengue
			myFunction();//all_causes
			myFunction15();//pulmonary
			myFunction14();//injuries
			myFunction12();//h1n1
			myFunction10();//viral
			myFunction8();//STD
			myFunction4();//malaria
			Number_Of_Slides_Found_Positive();
			P_Falciparum();
			
		}
		
	}
	
	function Nextpage() {
		if(validation1()== true){			
			var formValues=$('#add_prvnt_details').serialize();
			var factories_inspectedForm=$('#factories_inspectedForm').serialize();
			formValues += "&"+factories_inspectedForm;
			console.log(formValues);
			
			 $.post('add_prvnt_details_act?' + key + "=" + value, formValues, function(response){ 
				
		           if(response > 0)
		 	        	{
		 	        		
		 	        		$("#id").val(response);
		 	        		alert("Data Saved Successfully.");
		 		        }
		 	        	else
		 	        	{
		 	        		alert(response);
		 	        	}	           
		        });
			
			$('#montdtls').show();
			$('#save2').show();
			$('#actsho').hide();
			$('#save1').hide();
			
		}
		
	}
	function Backpage() {

		$('#actsho').show();
		$('#save1').hide();
		$('#montdtls').hide();
		$('#save2').hide();
	}
</script>
<script type="text/javascript">
function validation1() {
	
	
	
	var milk_count = $("#milk_count").val(); 
	var milk_oldcount = $("#milk_oldcount").val();
	
	if(milk_count == milk_oldcount){
		
	}else{
		if(milk_count > milk_oldcount){ 
			for(var i=parseInt(milk_oldcount)+1;i<=milk_count;i++){
				if($("#milk_samplenum"+i).val() != "" || $("#milk_spec_gravity"+i).val() != "0" || $("#milk_fat_con"+i).val() != "0" || $("#milk_tot_solid"+i).val() != "0" ){
					alert("Please Update Milk Quality Section");
					return false;
				}
			}
		}
	}
	
	
	var health_count = $("#health_count").val(); 
	var health_oldcount = $("#health_oldcount").val();
	
	if(health_count == health_oldcount){
		
	}else{
		if(health_count > health_oldcount){ 
			for(var i=parseInt(health_oldcount)+1;i<=health_count;i++){
				if($("#health_topic"+i).val() != "" || $("#health_unit_name"+i).val() != "" || $("#h_personnel_category"+i).val() != "0" || $("#h_number_present"+i).val() != ""
					|| $("#h_remarks"+i).val() != "" ){
					alert("Please Update Health Eduction Section");
					return false;
				}
			}
		}
	}
	
	var workshop_count = $("#workshop_count").val(); 
	var workshop_oldcount = $("#workshop_oldcount").val();
	
	if(workshop_count == workshop_oldcount){
		
	}else{
		if(workshop_count > workshop_oldcount){ 
			for(var i=parseInt(workshop_oldcount)+1;i<=workshop_count;i++){
				if($("#topic"+i).val() != "" || $("#workshop_unit_name"+i).val() != "" || $("#target_group"+i).val() != "0" || $("#number_present"+i).val() != ""
					|| $("#remarks"+i).val() != "" ){
					alert("Please Update Workshops/Seminars/Symposiums Organized Section");
					return false;
				}
			}
		}
	}
	
	return true;
	
}


function validation2() {
	
	
	
	var malaria_count = $("#malaria_count").val(); 
	var malariaoldcount = $("#malariaoldcount").val();
	
	if(malaria_count == malariaoldcount){
		
	}else{
		if(malaria_count > malariaoldcount){ 
			for(var i=parseInt(malariaoldcount)+1;i<=malaria_count;i++){
				if($("#mal_unit_name"+i).val() != "" || $("#bt"+i).val() != "" || $("#mt"+i).val() != "" || $("#fl"+i).val() != "" || $("#fi"+i).val() != "" ){
					alert("Please Update Malaria Section");
					return false;
				}
			}
		}
	}
	
	
	var std_count = $("#std_count").val(); 
	var stdoldcount = $("#stdoldcount").val();
	
	if(std_count == stdoldcount){
		
	}else{
		if(std_count > stdoldcount){ 
			for(var i=parseInt(stdoldcount)+1;i<=std_count;i++){
				if($("#std_unit_name"+i).val() != "" || $("#s_fl"+i).val() != "" || $("#s_fi"+i).val() != "" ){
					alert("Please Update STD Section");
					return false;
				}
			}
		}
	}
	
	var viral_hepatitis_count = $("#viral_hepatitis_count").val(); 
	var viral_hepatitis_oldcount = $("#viral_hepatitis_oldcount").val();
	
	if(viral_hepatitis_count == viral_hepatitis_oldcount){
		
	}else{
		if(viral_hepatitis_count > viral_hepatitis_oldcount){ 
			for(var i=parseInt(viral_hepatitis_oldcount)+1;i<=viral_hepatitis_count;i++){
				if($("#viral_unit_name"+i).val() != "" || $("#h_fl"+i).val() != "" || $("#h_fi"+i).val() != "" ){
					alert("Please Update Viral Hepatitis A&E Section");
					return false;
				}
			}
		}
	}
	
	
	var h1n1_count = $("#h1n1_count").val(); 
	var h1n1_oldcount = $("#h1n1_oldcount").val();
	
	if(h1n1_count == h1n1_oldcount){
		
	}else{
		if(h1n1_count > h1n1_oldcount){ 
			for(var i=parseInt(h1n1_oldcount)+1;i<=h1n1_count;i++){
				if($("#h1n1_unit_name"+i).val() != "" || $("#no_cases"+i).val() != "" ){
					alert("Please Update H1N1 Influenza Section");
					return false;
				}
			}
		}
	}
	
	
	var injuries_nea_count = $("#injuries_nea_count").val(); 
	var injuries_nea_oldcount = $("#injuries_nea_oldcount").val();
	
	if(injuries_nea_count == injuries_nea_oldcount){
		
	}else{
		if(injuries_nea_count > injuries_nea_oldcount){ 
			for(var i=parseInt(injuries_nea_oldcount)+1;i<=injuries_nea_count;i++){
				if($("#inj_unit_name"+i).val() != "" || $("#diagnosis"+i).val() != "" || $("#mode_of_injury"+i).val() != "0" ){
					alert("Please Update Injuries NEA Section");
					return false;
				}
			}
		}
	}
	
	var count = $("#count").val(); 
	var oldcount = $("#oldcount").val();
	
	if(count == oldcount){
		
	}else{
		if(count > oldcount){ 
			for(var i=parseInt(oldcount)+1;i<=count;i++){
				if($("#personnel_number1"+i).val() != "-1" || $("#personnel_number2"+i).val() != "" || $("#personnel_number3"+i).val() != "-1" 
						|| $("#category"+i).val() != "2" ||  $("#personnel_name"+i).val() != "" || $("#pul_unit_name"+i).val() != ""){
					alert("Please Update Pulmonary Tuberculosis Section");
					return false;
				}
			}
		}
	}
	
	
	return true;
	
}

</script>


<script>
	function checkprvtdtls() {
		var type_prvnt = $('input[name=type_prvnt]:checked').val();
		if ($("input#sho_name").val() == "") {
			alert("Please select sho name");
			$("input#sho_name").focus();
			return false;
		} else if ($("input#location").val() == "") {
			alert("Please select location");
			$("input#location").focus();
			return false;
		} else if ($("input#month").val() == "") {
			alert("Please select month");
			$("input#month").focus();
			return false;
		} else if ($("input#year").val() == "") {
			alert("Please select year");
			$("input#year").focus();
			return false;
		} else if ($("input#antilarval_measure").val() == "") {
			alert("Please select antilarval_measure");
			$("input#antilarval_measure").focus();
			return false;
		} else if ($("input#campaigns_pulse_polio").val() == "") {
			alert("Please select campaigns_pulse_polio");
			$("input#campaigns_pulse_polio").focus();
			return false;
		} else if ($("input#campaigns_others").val() == "") {
			alert("Please select campaigns_others");
			$("input#campaigns_others").focus();
			return false;
		} else if ($("input#image_actvt").val() == "") {
			alert("Please Enter image_actvt");
			$("input#image_actvt").focus();
			return false;
		}
		if ($("select#category1").val() == "2") {
			alert("Please Select category");
			$("select#category1").focus();
			return false;
		} else {

		}

	}
	
	function updateDtls(){
		if(validation2()== true){		
		var formValues="";
		var all_causes_dtl_form = $("#all_causes_dtl").serialize(); 
		var dengue_dtl_form = $("#dengue_dtl").serialize();
		var chikunguniya_dtl_form = $("#chikunguniya_dtl").serialize();
		var immune_survellance_dtl_form = $("#immune_survellance_dtl").serialize();
		var Diarrhoea_n_respi_dtl_form = $("#Diarrhoea_n_respi_dtl").serialize();
		var skin_disease_dtl_form = $("#skin_disease_dtl").serialize();
		var heat_cold_attlitude_dtl_form = $("#heat_cold_attlitude_dtl").serialize();
		var other_dtl_form = $("#other_dtl").serialize();
		formValues += all_causes_dtl_form;
		formValues += "&"+dengue_dtl_form;
		formValues += "&"+chikunguniya_dtl_form;
		formValues += "&"+immune_survellance_dtl_form;
		formValues += "&"+Diarrhoea_n_respi_dtl_form;
		formValues += "&"+skin_disease_dtl_form;
		formValues += "&"+heat_cold_attlitude_dtl_form;
		formValues += "&"+other_dtl_form;
		var jc_id=$("#id").val();
		formValues += '&id='+jc_id;
		
		
		$.post('add_prvnt_details_act1?' + key + "=" + value, formValues, function(response){ 
	          
 				 if(response == "Data Updated Successfully")
	 	        	{
	 	        		alert(response);
	 	        		$("#searchprevention").submit();
	 	        		
	 		        }
	 	        	else
	 	        	{
	 	        		alert(response);
	 	        	}           
	        });
		
			}
		}
</script>



<Script>
	(function($) {
		"use strict";
		$.fn.charCounter = function(options) {
			if (typeof String.prototype.format == "undefined") {
				String.prototype.format = function() {
					var content = this;
					for (var i = 0; i < arguments.length; i++) {
						var replacement = '{' + i + '}';
						content = content.replace(replacement, arguments[i]);
					}
					return content;
				};
			}
			var options = $.extend({
				backgroundColor : "#FFFFFF",
				position : {
					right : 10,
					top : 10
				},
				font : {
					size : 10,
					color : "#a59c8c"
				},
				limit : 250
			}, options);
			return this.each(function() {
				var el = $(this), wrapper = $("<div/>").addClass(
						'focus-textarea').css({
					"position" : "relative",
					"display" : "inline-block"
				}), label = $("<span/>").css({
					"zIndex" : 999,
					"backgroundColor" : options.backgroundColor,
					"position" : "absolute",
					"font-size" : options.font.size,
					"color" : options.font.color
				}).css(options.position);
				if (options.limit > 0) {
					label
							.text("{0}/{1}".format(el.val().length,
									options.limit));
					el.prop("maxlength", options.limit);
				} else {
					label.text(el.val().length);
				}
				el.wrap(wrapper);
				el.before(label);
				el.on("keyup", updateLabel).on("keypress", updateLabel).on(
						'keydown', updateLabel);
				function updateLabel(e) {
					if (options.limit > 0) {
						label.text("{0}/{1}".format($(this).val().length,
								options.limit));
					} else {
						label.text($(this).val().length);
					}
				}
			});
		}
	})(jQuery);
	$(".char-counter1").charCounter();
</Script>

<script>
	function isNumber0_9Only(evt) {
		var charCode = (evt.which) ? evt.which : evt.keyCode;
		if (!(charCode >= 48 && charCode <= 57) && charCode != 8) {
			return false;
		}
		return true;
	}
</script>

<script>
	$(document).ready(
			function ParseDateColumn() {
				var d = new Date();
				document.getElementById("year").value = d.getFullYear();

				
			});

	function chgCategory(r) {
		if ($("#personnel_number1"+r).val() == "-1") {
			$("#category"+r).val('OR');
		} else if ($("#personnel_number1"+r).val() == "TJ"
				|| $("#personnel_number1"+r).val() == "JC") {
			$("#category"+r).val('JCO');
		} else if ($("#personnel_number1"+r).val() == "NR"
				|| $("#personnel_number1"+r).val() == "NS"
				|| $("#personnel_number1"+r).val() == "NL"
				|| $("#personnel_number1"+r).val() == "PN") {
			$("#category"+r).val('MNS');
		} else if ($("#personnel_number1"+r).val() != "NR"
				|| $("#personnel_number1"+r).val() != "NS"
				|| $("#personnel_number1"+r).val() != "NL"
				|| $("#personnel_number1"+r).val() != "PN"
				|| $("#personnel_number1"+r).val() != "TJ"
				|| $("#personnel_number1"+r).val() != "JC") {
			$("#category"+r).val('OFFICER');
		}
	}
	function getRank(r) {
		var category = $("#category"+r).val();
		var service = $("#service").val();
		$("#rank"+r).attr('disabled', false);
		$.post(
						"getMNHRank?" + key + "=" + value,
						{
							a1 : category,
							a2 : service
						},
						function(j) {
							var options = '<option value="-1">--Select--</option>';

							var a = [];
							var enc = j[j.length - 1].substring(0, 16);
							for (var i = 0; i < j.length; i++) {
								a[i] = dec(enc, j[i]);
							}
							var data = a[0].split(",");
							var datap;

							for (var i = 0; i < data.length - 1; i++) {
								datap = data[i].split(":");
								options += '<option value="'+datap[1]+'" name="' + datap[0]+ '" >'
										+ datap[0] + '</option>';
							}
						
							$("#rank"+r).html(options);
							var q = '${list.rank}';
							if (q != "") {
								$("#rank"+r).val(q);
							}
							
						});
	}
	function myFunction() {

		var a = $("#causes_number_officers").val();
		if(a == "" || a == null){
			a = 0;
		}
		var b = $("#causes_number_jcos").val();
		if(b == "" || b == null){
			b = 0;
		}
		var c = $("#causes_number_ors").val();
		if(c == "" || c == null){
			c = 0;
		}
		var d = $("#causes_rate_officers").val();
		if(d == "" || d == null){
			d = 0;
		}
		var e =$("#causes_rate_jcos").val();
		if(e == "" || e == null){
			e = 0;
		}
		var f = $("#causes_rate_ors").val();
		if(f == "" || f == null){
			f = 0;
		}
		
		var x = parseInt(a) + parseInt(b) + parseInt(c);
		var y = parseInt(d) + parseInt(e) + parseInt(f);
		$("#number_total").val(x);
		$("#rate_total").val(y);
	}

	function myFunction1() {
	
		var a = $("#effect_of_heat_number_officers").val();
		if(a == "" || a == null){
			a = 0;
		}
		var b = $("#effect_of_heat_number_jcos").val();
		if(b == "" || b == null){
			b = 0;
		}
		var c = $("#effect_of_heat_number_ors").val();
		if(c == "" || c == null){
			c = 0;
		}
		var d = $("#effect_of_heat_rate_officers").val();
		if(d == "" || d == null){
			d = 0;
		}
		var e =$("#effect_of_heat_rate_jcos").val();
		if(e == "" || e == null){
			e = 0;
		}
		var f = $("#effect_of_heat_rate_ors").val();
		if(f == "" || f == null){
			f = 0;
		}
		
		var x = parseInt(a) + parseInt(b) + parseInt(c);
		var y = parseInt(d) + parseInt(e) + parseInt(f);
		$("#effect_heat_number_total").val(x);
		$("#effect_heat_rate_total").val(y);
	}

	function myFunction2() {
		
		var a = $("#effect_of_cold_number_officers").val();
		if(a == "" || a == null){
			a = 0;
		}
		var b = $("#effect_of_cold_number_jcos").val();
		if(b == "" || b == null){
			b = 0;
		}
		var c = $("#effect_of_cold_number_ors").val();
		if(c == "" || c == null){
			c = 0;
		}
		var d = $("#effect_of_cold_rate_officers").val();
		if(d == "" || d == null){
			d = 0;
		}
		var e =$("#effect_of_cold_rate_jcos").val();
		if(e == "" || e == null){
			e = 0;
		}
		var f = $("#effect_of_cold_rate_ors").val();
		if(f == "" || f == null){
			f = 0;
		}
		var x = parseInt(a) + parseInt(b) + parseInt(c);
		var y = parseInt(d) + parseInt(e) + parseInt(f);
		$("#effect_cold_number_total").val(x);
		$("#effect_cold_rate_total").val(y);
	}

	function myFunction3() {
		var a = document.getElementById("effect_of_high_altitude_number_officers").value;
		var b = document.getElementById("effect_of_high_altitude_number_jcos").value;
		var c = document.getElementById("effect_of_high_altitude_number_ors").value;
		var d = document.getElementById("effect_of_high_altitude_rate_officers").value;
		var e = document.getElementById("effect_of_high_altitude_rate_jcos").value;
		var f = document.getElementById("effect_of_high_altitude_rate_ors").value;
		

		var a = $("#effect_of_high_altitude_number_officers").val();
		if(a == "" || a == null){
			a = 0;
		}
		var b = $("#effect_of_high_altitude_number_jcos").val();
		if(b == "" || b == null){
			b = 0;
		}
		var c = $("#effect_of_high_altitude_number_ors").val();
		if(c == "" || c == null){
			c = 0;
		}
		var d = $("#effect_of_high_altitude_rate_officers").val();
		if(d == "" || d == null){
			d = 0;
		}
		var e =$("#effect_of_high_altitude_rate_jcos").val();
		if(e == "" || e == null){
			e = 0;
		}
		var f = $("#effect_of_high_altitude_rate_ors").val();
		if(f == "" || f == null){
			f = 0;
		}
		var x = parseInt(a) + parseInt(b) + parseInt(c);
		var y = parseInt(d) + parseInt(e) + parseInt(f);
		$("#effect_altitude_number_total").val(x);
		$("#effect_altitude_rate_total").val(y);
	}

	function myFunction4() {
			
		var a = $("#malaria_number_officers").val();
		if(a == "" || a == null){
			a = 0;
		}
		var b = $("#malaria_number_jcos").val();
		if(b == "" || b == null){
			b = 0;
		}
		var c = $("#malaria_number_ors").val();
		if(c == "" || c == null){
			c = 0;
		}
		var d = $("#malaria_rate_officers").val();
		if(d == "" || d == null){
			d = 0;
		}
		var e =$("#malaria_rate_jcos").val();
		if(e == "" || e == null){
			e = 0;
		}
		var f = $("#malaria_rate_ors").val();
		if(f == "" || f == null){
			f = 0;
		}
		
		var g = $("#fl").val();
		if(g== "" || f == null){
			g = 0;
		}
		var h = $("#fi").val();
		if(h == "" || f == null){
			h = 0;
		}
		var x = parseInt(a) + parseInt(b) + parseInt(c);
		var y = parseInt(d) + parseInt(e) + parseInt(f);
		var z = parseInt(g) + parseInt(h) ;
		$("#Malaria_number_total").val(x);
		$("#Malaria_rate_total").val(y);
	}
	function myFunctionMAl(m) {
		
		var a = $("#bt"+m).val();
		if(a == "" || a == null){
			a = 0;
		}
		var b = $("#mt"+m).val();
		if(b == "" || b == null){
			b = 0;
		}
		var c = $("#fl"+m).val();
		if(c == "" || c == null){
			c = 0;
		}
		var d = $("#fi"+m).val();
		if(d == "" || d == null){
			d = 0;
		}
		
		var x = parseInt(a) + parseInt(b) + parseInt(c) + parseInt(d) ;
		
		
		$("#mala_total"+m).val(x);
		
	}
	
	function myFunction5() {
		
		var a = $("#dengue_number_officers").val();
		if(a == "" || a == null){
			a = 0;
		}
		var b = $("#dengue_number_jcos").val();
		if(b == "" || b == null){
			b = 0;
		}
		var c = $("#dengue_number_ors").val();
		if(c == "" || c == null){
			c = 0;
		}
		var d = $("#dengue_rate_officers").val();
		if(d == "" || d == null){
			d = 0;
		}
		var e =$("#dengue_rate_jcos").val();
		if(e == "" || e == null){
			e = 0;
		}
		var f = $("#dengue_rate_ors").val();
		if(f == "" || f == null){
			f = 0;
		}
		
		var x = parseInt(a) + parseInt(b) + parseInt(c);
		var y = parseInt(d) + parseInt(e) + parseInt(f);
		$("#Dengue_number_total").val(x);
		$("#Dengue_rate_total").val(y);
	}


	function myFunction7() {
		
		var a = $("#chikungunya_number_officers").val();
		if(a == "" || a == null){
			a = 0;
		}
		var b = $("#chikungunya_number_jcos").val();
		if(b == "" || b == null){
			b = 0;
		}
		var c = $("#chikungunya_number_ors").val();
		if(c == "" || c == null){
			c = 0;
		}
		var d = $("#chikungunya_rate_officers").val();
		if(d == "" || d == null){
			d = 0;
		}
		var e =$("#chikungunya_rate_jcos").val();
		if(e == "" || e == null){
			e = 0;
		}
		var f = $("#chikungunya_rate_ors").val();
		if(f == "" || f == null){
			f = 0;
		}
		
		
		var x = parseInt(a) + parseInt(b) + parseInt(c);
		var y = parseInt(d) + parseInt(e) + parseInt(f);
		$("#Chikenguniya_number_total").val(x);
		$("#Chikenguniya_rate_total").val(y);
	}

	function myFunction8() {
		
		var a = $("#std_number_officers").val();
		if(a == "" || a == null){
			a = 0;
		}
		var b = $("#std_number_jcos").val();
		if(b == "" || b == null){
			b = 0;
		}
		var c = $("#std_number_ors").val();
		if(c == "" || c == null){
			c = 0;
		}
		var d = $("#std_rate_officers").val();
		if(d == "" || d == null){
			d = 0;
		}
		var e =$("#std_rate_jcos").val();
		if(e == "" || e == null){
			e = 0;
		}
		var f = $("#std_rate_ors").val();
		if(f == "" || f == null){
			f = 0;
		}
		
		var g = $("#fl").val();
		if(g == "" || g == null){
			g = 0;
		}
		var h = $("#fi").val();
		if(h == "" || h == null){
			h = 0;
		}
		
		var x = parseInt(a) + parseInt(b) + parseInt(c);
		var y = parseInt(d) + parseInt(e) + parseInt(f);
		var z = parseInt(g) + parseInt(h) 
	
		$("#STD_number_total").val(x);
		$("#STD_rate_total").val(y);
		$("#std_total").val(y);
	}

function myFunctionSTD(s) {
		
		var a = $("#s_fl"+s).val();
		if(a == "" || a == null){
			a = 0;
		}
		var b = $("#s_fi"+s).val();
		if(b == "" || b == null){
			b = 0;
		}
		
		var x = parseInt(a) + parseInt(b) ;
		
		$("#std_total"+s).val(x);
		
	}
	
	
	function myFunction9() { 
		
		var a = $("#immune_surveillance_number_officers").val();
		if(a == "" || a == null){
			a = 0;
		}
		var b = $("#immune_surveillance_number_jcos").val();
		if(b == "" || b == null){
			b = 0;
		}
		var c = $("#immune_surveillance_number_ors").val();
		if(c == "" || c == null){
			c = 0;
		}
		var d = $("#immune_surveillance_rate_officers").val();
		if(d == "" || d == null){
			d = 0;
		}
		var e =$("#immune_surveillance_rate_jcos").val();
		if(e == "" || e == null){
			e = 0;
		}
		var f = $("#immune_surveillance_rate_ors").val();
		if(f == "" || f == null){
			f = 0;
		}
		
		
		var x = parseInt(a) + parseInt(b) + parseInt(c);
		var y = parseInt(d) + parseInt(e) + parseInt(f);
		$("#Imiune_Surveillance_number_total").val(x);
		$("#Imiune_Surveillance_rate_total").val(y);
	}
	function myFunction10() {
			
		var a = $("#viral_hepatitis_number_officers").val();
		if(a == "" || a == null){
			a = 0;
		}
		var b = $("#viral_hepatitis_number_jcos").val();
		if(b == "" || b == null){
			b = 0;
		}
		var c = $("#viral_hepatitis_number_ors").val();
		if(c == "" || c == null){
			c = 0;
		}
		var d = $("#viral_hepatitis_rate_officers").val();
		if(d == "" || d == null){
			d = 0;
		}
		var e =$("#viral_hepatitis_rate_jcos").val();
		if(e == "" || e == null){
			e = 0;
		}
		var f = $("#viral_hepatitis_rate_ors").val();
		if(f == "" || f == null){
			f = 0;
		}
		var x = parseInt(a) + parseInt(b) + parseInt(c);
		var y = parseInt(d) + parseInt(e) + parseInt(f);
		$("#Viral_Hepatitis_number_total").val(x);
		$("#Viral_Hepatitis_rate_total").val(y);
	}
	function myFunctionHEPA(h) {
		
		var a = $("#h_fl"+h).val();
		if(a == "" || a == null){
			a = 0;
		}
		var b = $("#h_fi"+h).val();
		if(b == "" || b == null){
			b = 0;
		}
		
		
		var x = parseInt(a) + parseInt(b)  ;
		
		
		$("#hepa_total"+h).val(x);
		
	}
	function myFunction16() {
			
		var a = $("#diarrhoea_number_officers").val();
		if(a == "" || a == null){
			a = 0;
		}
		var b = $("#diarrhoea_number_jcos").val();
		if(b == "" || b == null){
			b = 0;
		}
		var c = $("#diarrhoea_number_ors").val();
		if(c == "" || c == null){
			c = 0;
		}
		var d = $("#diarrhoea_rate_officers").val();
		if(d == "" || d == null){
			d = 0;
		}
		var e =$("#diarrhoea_rate_jcos").val();
		if(e == "" || e == null){
			e = 0;
		}
		var f = $("#diarrhoea_rate_ors").val();
		if(f == "" || f == null){
			f = 0;
		}
	
		
		var x = parseInt(a) + parseInt(b) + parseInt(c);
		var y = parseInt(d) + parseInt(e) + parseInt(f);
		$("#Diarrhoea_number_total").val(x);
		$("#Diarrhoea_rate_total").val(y);
	}

	function myFunction11() {
		
		var a = $("#respiratory_number_officers").val();
		if(a == "" || a == null){
			a = 0;
		}
		var b = $("#respiratory_number_jcos").val();
		if(b == "" || b == null){
			b = 0;
		}
		var c = $("#respiratory_number_ors").val();
		if(c == "" || c == null){
			c = 0;
		}
		var d = $("#respiratory_rate_officers").val();
		if(d == "" || d == null){
			d = 0;
		}
		var e =$("#respiratory_rate_jcos").val();
		if(e == "" || e == null){
			e = 0;
		}
		var f = $("#respiratory_rate_ors").val();
		if(f == "" || f == null){
			f = 0;
		}
		
		var x = parseInt(a) + parseInt(b) + parseInt(c);
		var y = parseInt(d) + parseInt(e) + parseInt(f);
		$("#Respiratory_Group_number_total").val(x);
		$("#Respiratory_Group_rate_total").val(y);
	}

	function myFunction12() {
		
		var a = $("#h1n1_influenza_number_officers").val();
		if(a == "" || a == null){
			a = 0;
		}
		var b = $("#h1n1_influenza_number_jcos").val();
		if(b == "" || b == null){
			b = 0;
		}
		var c = $("#h1n1_influenza_number_ors").val();
		if(c == "" || c == null){
			c = 0;
		}
		var d = $("#h1n1_influenza_rate_officers").val();
		if(d == "" || d == null){
			d = 0;
		}
		var e =$("#h1n1_influenza_rate_jcos").val();
		if(e == "" || e == null){
			e = 0;
		}
		var f = $("#h1n1_influenza_rate_ors").val();
		if(f == "" || f == null){
			f = 0;
		}
		
		var x = parseInt(a) + parseInt(b) + parseInt(c);
		var y = parseInt(d) + parseInt(e) + parseInt(f);
		$("#H1N1_number_total").val(x);
		$("#H1N1_rate_total").val(y);
	}
	function myFunction13() {
		
		var a = $("#skin_disease_number_officers").val();
		if(a == "" || a == null){
			a = 0;
		}
		var b = $("#skin_disease_number_jcos").val();
		if(b == "" || b == null){
			b = 0;
		}
		var c = $("#skin_disease_number_ors").val();
		if(c == "" || c == null){
			c = 0;
		}
		var d = $("#skin_disease_rate_officers").val();
		if(d == "" || d == null){
			d = 0;
		}
		var e =$("#skin_disease_rate_jcos").val();
		if(e == "" || e == null){
			e = 0;
		}
		var f = $("#skin_disease_rate_ors").val();
		if(f == "" || f == null){
			f = 0;
		}
		
		var x = parseInt(a) + parseInt(b) + parseInt(c);
		var y = parseInt(d) + parseInt(e) + parseInt(f);
		$("#Skin_Dieseases_number_total").val(x);
		$("#Skin_Dieseases_rate_total").val(y);
	}

	function myFunction14() {
		
		var a = $("#injuries_nea_number_officers").val();
		if(a == "" || a == null){
			a = 0;
		}
		var b = $("#injuries_nea_number_jcos").val();
		if(b == "" || b == null){
			b = 0;
		}
		var c = $("#injuries_nea_number_ors").val();
		if(c == "" || c == null){
			c = 0;
		}
		var d = $("#injuries_nea_rate_officers").val();
		if(d == "" || d == null){
			d = 0;
		}
		var e =$("#injuries_nea_rate_jcos").val();
		if(e == "" || e == null){
			e = 0;
		}
		var f = $("#injuries_nea_rate_ors").val();
		if(f == "" || f == null){
			f = 0;
		}
	
		
		var x = parseInt(a) + parseInt(b) + parseInt(c);
		var y = parseInt(d) + parseInt(e) + parseInt(f);
		$("#Injuries_NEA_number_total").val(x);
		$("#Injuries_NEA_rate_total").val(y);
	}

	function myFunction15() {
			
		var a = $("#pulmonary_tuberculosis_number_officers").val();
		if(a == "" || a == null){
			a = 0;
		}
		var b = $("#pulmonary_tuberculosis_number_jcos").val();
		if(b == "" || b == null){
			b = 0;
		}
		var c = $("#pulmonary_tuberculosis_number_ors").val();
		if(c == "" || c == null){
			c = 0;
		}
		var d = $("#pulmonary_tuberculosis_rate_officer").val();
		if(d == "" || d == null){
			d = 0;
		}
		var e =$("#pulmonary_tuberculosis_rate_jcos").val();
		if(e == "" || e == null){
			e = 0;
		}
		var f = $("#pulmonary_tuberculosis_rate_ors").val();
		if(f == "" || f == null){
			f = 0;
		}
	
		
		
		var x = parseInt(a) + parseInt(b) + parseInt(c);
		var y = parseInt(d) + parseInt(e) + parseInt(f);
		$("#Pulm_Tuber_Culosis_number_total").val(x);
		$("#Pulm_Tuber_Culosis_rate_total").val(y);
	}

	function P_Falciparum() {
	
		var a = $("#filaria_officers").val();
		if(a == "" || a == null){
			a = 0;
		}
		var b = $("#filaria_jcos").val();
		if(b == "" || b == null){
			b = 0;
		}
		var c = $("#filaria_ors").val();
		if(c == "" || c == null){
			c = 0;
		}
		var d = $("#filaria_family").val();
		if(d == "" || d == null){
			d = 0;
		}
		
		var x = parseInt(a) + parseInt(b) + parseInt(c) + parseInt(d);
		$("#slides_filaria_total").val(x);
	}

	function Number_Of_Slides_Found_Positive() {
		var a = $("#malaria_p_vivax_officers").val();
		if(a == "" || a == null){
			a = 0;
		}
		var b = $("#malaria_p_vivax_jcos").val();
		if(b == "" || b == null){
			b = 0;
		}
		var c = $("#malaria_p_vivax_ors").val();
		if(c == "" || c == null){
			c = 0;
		}
		var d = $("#malaria_p_vivax_family").val();
		if(d == "" || d == null){
			d = 0;
		}
		var e =$("#malaria_p_falciparum_officers").val();
		if(e == "" || e == null){
			e = 0;
		}
		var f = $("#malaria_p_falciparum_jcos").val();
		if(f == "" || f == null){
			f = 0;
		}
		var g =$("#malaria_p_falciparum_ors").val();
		if(g == "" || g == null){
			g = 0;
		}
		var h = $("#malaria_p_falciparum_family").val();
		if(h == "" || h == null){
			h = 0;
		}
		
		var x = parseInt(a) + parseInt(b) + parseInt(c) + parseInt(d)  ;
		
		var y = parseInt(e) + parseInt(f) + parseInt(g) + parseInt(h);
		
		$("#slides_vivax_total").val(x);
		$("#slides_falciparum_total").val(y);
	}

	function split() {

		var a = document.getElementById("diagnosis").value;
		var b = a.split("-");
		var c = b[0]
		document.getElementById("diagnosis").value = c;
	}
</script>


<script type="text/javascript">

$(document).ready(function() {
	 if(${listMilkQualityDetails.size() == 0}){
		 
		 $("#milk_count").val(1);
		 $("#milk_oldcount").val(0);
		
	 } 
	else{
 
 		$("#milk_count").val('${listMilkQualityDetails.size()}');
 		$("#milk_oldcount").val('${listMilkQualityDetails.size()}');
	}
	 
	 
	 ${specific_gravity};
	 ${total_solids};
	 ${fat_content};
	 
	 
	 
 if(${listMalariyaDetails.size() == 0}){
		 
		 $("#malaria_count").val(1);
		 $("#malariaoldcount").val(0);
		
	 } 
	else{
 
 		$("#malaria_count").val('${listMalariyaDetails.size()}');
 		$("#malariaoldcount").val('${listMalariyaDetails.size()}');
	} 
	 
 	if(${listStdDetails.size() == 0}){
		 
		 $("#std_count").val(1);
		 $("#stdoldcount").val(0);
		
	 } 
	else{
 
 		$("#std_count").val('${listStdDetails.size()}');
 		$("#stdoldcount").val('${listStdDetails.size()}');
		}
 
	 if(${listviral_hepatitisDetails.size() == 0}){
	 
	 $("#viral_hepatitis_count").val(1);
	 $("#viral_hepatitis_oldcount").val(0);
	
	 } 
	else{

		$("#viral_hepatitis_count").val('${listviral_hepatitisDetails.size()}');
		$("#viral_hepatitis_oldcount").val('${listviral_hepatitisDetails.size()}');
		}

	if(${listh1n1Details.size() == 0}){
	 
	 $("#h1n1_count").val(1);
	 $("#h1n1_oldcount").val(0);
	
		} 
	else{

		$("#h1n1_count").val('${listh1n1Details.size()}');
		$("#h1n1_oldcount").val('${listh1n1Details.size()}');
		}
	
	if(${listneaDetails.size() == 0}){
		 
		 $("#injuries_nea_count").val(1);
		 $("#injuries_nea_oldcount").val(0);
		
			} 
		else{

			$("#injuries_nea_count").val('${listneaDetails.size()}');
			$("#injuries_nea_oldcount").val('${listneaDetails.size()}');
			}
	
	
	 option = '';
		for(var i=1;i<='${listneaDetails.size()}';i++){
			option = "<c:forEach var='item' items='${PREVENTION_INJURIESList}' varStatus='num'><option value='${item[0]}'  >${item[1]}</option></c:forEach>";
			$("#mode_of_injury"+i).html(option);
		} 
		${mode_of_injuries}
		
		
		
		
		if(${listpultubDetails.size() == 0}){
			 
			 $("#count").val(1);
			 $("#oldcount").val(0);
			
				} 
			else{

				$("#count").val('${listpultubDetails.size()}');
				$("#oldcount").val('${listpultubDetails.size()}');
				}
		
		
		 option = '';
			for(var i=1;i<='${listpultubDetails.size()}';i++){
				option = "<c:forEach var='item' items='${ml_2}' varStatus='num'><option value='${item}'  >${item}</option></c:forEach>";
				$("#personnel_number1"+i).html(option);
			} 
			${pers_no1}
			
			 option = '';
				for(var i=1;i<='${listpultubDetails.size()}';i++){
					option = "<c:forEach var='item' items='${ml_3}' varStatus='num'><option value='${item}'  >${item}</option></c:forEach>";
					$("#personnel_number3"+i).html(option);
				} 
				${pers_no3}
				
				 option = '';
					for(var i=1;i<='${listpultubDetails.size()}';i++){
						option = "<c:forEach var='item' items='${ml_1}' varStatus='num'><option value='${item}'  >${item}</option></c:forEach>";
						$("#category"+i).html(option);
					} 
					${category}
			
					for(var i=1;i<='${listpultubDetails.size()}';i++){
					getRank(i);
					}
					${rank}
					
					
					 if(${listhealtheduDetails.size() == 0}){
						 
						 $("#health_count").val(1);
						 $("#health_oldcount").val(0);
						
					 } 
					else{
				 
				 		$("#health_count").val('${listhealtheduDetails.size()}');
				 		$("#health_oldcount").val('${listhealtheduDetails.size()}');
					} 
					 
					 
					 option = '';
						for(var i=1;i<='${listhealtheduDetails.size()}';i++){
							option = "<c:forEach var='item' items='${ml_1}' varStatus='num'><option value='${item}'  >${item}</option></c:forEach>";
							$("#h_personnel_category"+i).html(option);
						} 
						${per_category}
						
						
						 if(${listworkshopDetails.size() == 0}){
							 
							 $("#workshop_count").val(1);
							 $("#workshop_oldcount").val(0);
							
						 } 
						else{
					 
					 		$("#workshop_count").val('${listworkshopDetails.size()}');
					 		$("#workshop_oldcount").val('${listworkshopDetails.size()}');
						} 
						 
						 option = '';
							for(var i=1;i<='${listworkshopDetails.size()}';i++){
								option = '<option value="1">Pers Group</option><option value="2">Students</option><option value="3">Teachers</option><option value="4">Others</option>'
								$("#target_group"+i).html(option);
							} 
							${total_group}

});


function milkqualityformsubmit(){
	
 	var formvalues=$("#milk_quality_form").serialize();
 	
	var p_id=$("#id").val();
	if(p_id==0){
		alert("data not updated");
		return false;
	}
 	formvalues+="&p_id="+p_id;
	
	   $.post('updatemilk_quality?' + key + "=" + value,formvalues, function(data){
          alert(data);
          $.post('get_milk_quality?' + key + "=" + value,{ p_id : p_id }).done(function(j) {
       	   var i=0;
       	   var v=j.length;
       	   for(i;i<v;i++){
       			x=i+1;
       			$("td#c_milk_idtd"+x).replaceWith(j[i][4]);  	 
       		}
       	   $("#milk_oldcount").val(v);
       	$("#milk_id_remove"+v).remove();
          }).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
      	});
       
       });

}

		
function malariyaformsubmit(){
	
 	var formvalues=$("#malaria_table_form").serialize();
	var p_id=$("#id").val();
	if(p_id==0){
		alert("data not updated");
		return false;
	}
 	formvalues+="&p_id="+p_id;
	   $.post('updateMalaria?' + key + "=" + value,formvalues, function(data){
         
          $.post('get_Malaria_data?' + key + "=" + value,{ p_id : p_id }).done(function(j) {
       	   var i=0;
       	   var v=j.length;
       	   
       	   $("#malariaaddQuantitytbody").empty();
       	   for(i;i<v;i++){
       			x=i+1;
       				 $("table#malariaaddQuantity").append('<tr align="center" id="milk_tr_id'+x+'">'
       			    			+'<td style="text-align: center;">'+x+'</td>'
       			    			+'<td >'+j[i][0]+'</td>'
       			    			+'<td >'+j[i][1]+'</td>'
       			    			+'<td >'+j[i][2]+'</td>'
       			    			+'<td >'+j[i][3]+'</td>'
       			    			+'<td >'+j[i][4]+'</td>'
       			    			+'<td >'+j[i][5]+'</td>'
       			    		   +'<td style="width: 10%; display:none;" >'+j[i][6]+'</td>'
       			    		   +'<td style="text-align: center;">'+j[i][7]+'</td>'
       			    		    +'</tr>');
       		 
       		}
       	   $("#malariaoldcount").val(v);
          }).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
      	});
        
       });

}

function stdformsubmit(){
	
 	var formvalues=$("#STD_table_form").serialize();
	var p_id=$("#id").val();
	if(p_id==0){
		alert("data not updated");
		return false;
	}
 	formvalues+="&p_id="+p_id;
	   $.post('updateSTD?' + key + "=" + value,formvalues, function(data){
          alert(data);
          $.post('get_Std_data?' + key + "=" + value,{ p_id : p_id }).done(function(j) {
       	   var i=0;
       	   var v=j.length;
       	   
        	   $("#stdaddQuantitytbody").empty();
       	   for(i;i<v;i++){
       			x=i+1;
       				 $("table#stdaddQuantity").append('<tr align="center" id="std_tr_id'+x+'">'
       			    			+'<td style="text-align: center;">'+x+'</td>'
       			    			+'<td >'+j[i][0]+'</td>'
       			    			+'<td >'+j[i][1]+'</td>'
       			    			+'<td >'+j[i][2]+'</td>'
       			    			+'<td >'+j[i][3]+'</td>'      			    		
       			    		   +'<td style="width: 10%; display:none;" >'+j[i][4]+'</td>'
       			    		   +'<td style="text-align: center;">'+j[i][5]+'</td>'
       			    		    +'</tr>');
       		}
       	   
       	   
       	   $("#stdoldcount").val(v);
          }).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
      	});
       
       });
}

function viral_hepatitisformsubmit(){
	
 	var formvalues=$("#Viral_Hepatitis_table_form").serialize();
	var p_id=$("#id").val();
	if(p_id==0){
		alert("data not updated");
		return false;
	}
 	formvalues+="&p_id="+p_id;
	
	   $.post('updateviral_hepatitis?' + key + "=" + value,formvalues, function(data){
          alert(data);
          $.post('get_Viral_Hepatitis_data?' + key + "=" + value,{ p_id : p_id }).done(function(j) {
       	   var i=0;
       	   var v=j.length;       	   
        	   $("#hepatitsaddQuantitytbody").empty();
       	   for(i;i<v;i++){
       			x=i+1;
       				 $("table#hepatitsaddQuantity").append('<tr align="center" id="hepatits_tr_id'+x+'">'
       			    			+'<td style="text-align: center;">'+x+'</td>'
       			    			+'<td >'+j[i][0]+'</td>'
       			    			+'<td >'+j[i][1]+'</td>'
       			    			+'<td >'+j[i][2]+'</td>'
       			    			+'<td >'+j[i][3]+'</td>'      			    		
       			    		   +'<td style="width: 10%; display:none;" >'+j[i][4]+'</td>'
       			    		   +'<td style="text-align: center;">'+j[i][5]+'</td>'
       			    		    +'</tr>');
       		}
       	   
       	   
       	   $("#viral_hepatitis_oldcount").val(v);
          }).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
      	});
       
       });
}


function h1n1_formsubmit(){
	
 	var formvalues=$("#h1n1_total_table_form").serialize();
	var p_id=$("#id").val();
	if(p_id==0){
		alert("data not updated");
		return false;
	}
 	formvalues+="&p_id="+p_id;
	
	   $.post('updateh1n1?' + key + "=" + value,formvalues, function(data){
           // Display the returned data in browser
          alert(data);
          $.post('get_h1n1_data?' + key + "=" + value,{ p_id : p_id }).done(function(j) {
       	   var i=0;
       	   var v=j.length;
       	   
        	   $("#h1n1addQuantitytbody").empty();
       	   
       	   for(i;i<v;i++){
       			x=i+1;
       				 $("table#h1n1addQuantity").append('<tr align="center" id="h1n1_tr_id'+x+'">'
       			    			+'<td style="text-align: center;">'+x+'</td>'
       			    			+'<td >'+j[i][0]+'</td>'
       			    			+'<td >'+j[i][1]+'</td>'      			    			  			    		
       			    		   +'<td style="width: 10%; display:none;" >'+j[i][2]+'</td>'
       			    		   +'<td style="text-align: center;">'+j[i][3]+'</td>'
       			    		    +'</tr>');
       				 
       		}
       	   $("#h1n1_oldcount").val(v);       	  
          }).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
      	});       
       
       });
}


function injuries_nea_formsubmit(){
	
 	var formvalues=$("#injuries_nea_form").serialize();
	var p_id=$("#id").val();
	if(p_id==0){
		alert("data not updated");
		return false;
	}
 	formvalues+="&p_id="+p_id;
   $.post('updateinjuries_nea?' + key + "=" + value,formvalues, function(data){
           // Display the returned data in browser
          alert(data);
          $.post('get_injuries_nea_data?' + key + "=" + value,{ p_id : p_id }).done(function(j) {
       	   var i=0;
       	   var v=j.length;
       	   
       	   for(i;i<v;i++){
       			x=i+1;
       		
       			$("td#c_nea_idtd"+x).replaceWith(j[i][3]);  	
       		}
       	   
       	   
       	   $("#injuries_nea_oldcount").val(v);
       	$("#injuries_id_remove"+v).remove();      	   
          
          }).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
      	});        
       
       });

}


function injuries_nea_formsubmit(){
	
 	var formvalues=$("#injuries_nea_form").serialize();
	var p_id=$("#id").val();
	if(p_id==0){
		alert("data not updated");
		return false;
	}
 	formvalues+="&p_id="+p_id;
	   $.post('updateinjuries_nea?' + key + "=" + value,formvalues, function(data){
           // Display the returned data in browser
          alert(data);
          $.post('get_injuries_nea_data?' + key + "=" + value,{ p_id : p_id }).done(function(j) {
       	   var i=0;
       	   var v=j.length;
       	   
       	   for(i;i<v;i++){       			
       			x=i+1;       			
       		
       			$("td#c_nea_idtd"+x).replaceWith(j[i][3]);  
       		 
       		}
       	   
       	   
       	   $("#injuries_nea_oldcount").val(v);
       	$("#injuries_id_remove"+v).remove();
       	   
          
          }).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
      	});
        
       
       });

}



function pultub_formsubmit(){
	
 	var formvalues=$("#pulmonary_tuberculosis_form").serialize();
	var p_id=$("#id").val();
	if(p_id==0){
		alert("data not updated");
		return false;
	}
 	formvalues+="&p_id="+p_id;

	
	   $.post('updatepulmonary_tuberculosis?' + key + "=" + value,formvalues, function(data){
           // Display the returned data in browser
          alert(data);
          $.post('get_pulmonary_tuberculosis_data?' + key + "=" + value,{ p_id : p_id }).done(function(j) {
       	   var i=0;
       	   var v=j.length;
       	   
       	   for(i;i<v;i++){       			
       			x=i+1;       			
       		
       			$("td#c_pultub_idtd"+x).replaceWith(j[i][7]);  	 
       		 
       		}
       	   
       	   
       	   $("#oldcount").val(v);
       	$("#id_remove"+v).remove();
       	   
          
          }).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
      	});
        
       
       });

}




function health_formsubmit(){
	
	var key = "${_csrf.parameterName}";
	var value = "${_csrf.token}";	
	var p_id=$("#id").val();
 	var form_data = new FormData(document.getElementById("health_total_table_form"));
 	form_data.append("p_id",p_id);
 	console.log(form_data);
 	$.ajax({
        url: 'updatehealthquality?_csrf='+value,
        type: "POST",
        data: form_data,
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false
      }).done(function(data) {
         alert(data);
                  
         $.post('get_healthedu?' + key + "=" + value,{ p_id : p_id }).done(function(j) {
         	   var i=0;
         	   var v=j.length;
         	   
         	   for(i;i<v;i++){         			
         			x=i+1;         			
         		
         			$("td#c_health_idtd"+x).replaceWith(j[i][6]);  	          		 
         		}
         	   $("#health_oldcount").val(v);
         	$("#health_id_remove"+v).remove();         	   
            
            }).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
        	});
         
         
      }).fail(function(jqXHR, textStatus) {        
          alert('File upload failed ...');
      });	 
       
}

function workshop_formsubmit(){
	
	var key = "${_csrf.parameterName}";
	var value = "${_csrf.token}";	
	var p_id=$("#id").val();
 	var form_data = new FormData(document.getElementById("Workshops_total_table_form"));
 	form_data.append("p_id",p_id);
 	console.log(form_data);
 	 
 	$.ajax({
        url: 'updateworkshopdetails?_csrf='+value,
        type: "POST",
        data: form_data,
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false
      }).done(function(data) {
         alert(data);
         
         
         $.post('get_workshopdetails?' + key + "=" + value,{ p_id : p_id }).done(function(j) {
         	   var i=0;
         	   var v=j.length;
         	   
         	   for(i;i<v;i++){         			
         			x=i+1;        			
         		
         			$("td#c_workshop_idtd"+x).replaceWith(j[i][6]);  	          		 
         		}
         	   
         	   $("#workshop_oldcount").val(v);
         	$("#workshop_id_remove"+v).remove();
         	   
            
            }).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
        	});
         
         
      }).fail(function(jqXHR, textStatus) {
          alert('File upload failed ...');
      });
	 
       
}
</script>
<script>

function per_nochange(r){
		
			chgCategory(r);
			if ($("#service").val() == "ARMY"
					&& $("#category"+r).val() != "-1") {
				getRank(r);
			}
		}

</script>
<script>
var key = "${_csrf.parameterName}";
var value = "${_csrf.token}";


jQuery(function() {
	// Source SUS No

	jQuery("#sus_no13").keypress(function(){
		var sus_no = this.value;
			 var susNoAuto=jQuery("#sus_no13");
			  susNoAuto.autocomplete({
			      source: function( request, response ) {
			        jQuery.ajax({
			        type: 'POST',
			        url: "getTargetSUSNoList?"+key+"="+value,
			        data: {sus_no:sus_no},
			          success: function( data ) {
			        	  var susval = [];
			        	  var length = data.length-1;
			        	  if(data.length != 0){
				        		var enc = data[length].substring(0,16);
				        	}
				        	for(var i = 0;i<data.length;i++){
				        		susval.push(dec(enc,data[i]));
				        	}
				        	var dataCountry1 = susval.join("|");
			            var myResponse = []; 
			            var autoTextVal=susNoAuto.val();
						jQuery.each(dataCountry1.toString().split("|"), function(i,e){
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
			        	  alert("Please Enter Approved SUS No.");
			        	  document.getElementById("sus_no13").value="";
			        	  susNoAuto.val("");	        	  
			        	  susNoAuto.focus();
			        	  return false;	             
			          }   	         
			      }, 
			      select: function( event, ui ) {
			    	  var unit_sus_no = ui.item.value;
			    	 	
  	 			 	$.post("getActiveUnitNameFromSusNo?"+key+"="+value, {sus_no:unit_sus_no}).done(function(j) {				
  	 			 		if(j == ""){
  	 			      	 	alert("Please Enter Approved Unit SUS No.");
  			        	  	document.getElementById("unit_name13").value="";
  			        	  	susNoAuto.val("");
  			        	  	susNoAuto.focus();
  	 			      	}else{
	    	 	   	        	var length = j.length-1;
	    	    				var enc = j[length].substring(0,16);
	    	    				$("#unit_name13").val(dec(enc,j[0]));	
	    	 	   	        		
  	 	   	        	}
  	 				}).fail(function(xhr, textStatus, errorThrown) {
  	 				});
			      } 	     
			});	
	});
	// End
	
	// Source Unit Name

    jQuery("#unit_name13").keypress(function(){
 			var unit_name = this.value;
				 var susNoAuto=jQuery("#unit_name13");
				  susNoAuto.autocomplete({
				      source: function( request, response ) {
				        jQuery.ajax({
				        type: 'POST',
				        url: "getTargetUnitsNameActiveList?"+key+"="+value,
				        data: {unit_name:unit_name},
				          success: function( data ) {
				        	  var susval = [];
				        	  var length = data.length-1;
				        	  if(data.length != 0){
					        		var enc = data[length].substring(0,16);
					        	}
					        	for(var i = 0;i<data.length;i++){
					        		susval.push(dec(enc,data[i]));
					        	}
					        	var dataCountry1 = susval.join("|");
				            var myResponse = []; 
				            var autoTextVal=susNoAuto.val();
							jQuery.each(dataCountry1.toString().split("|"), function(i,e){
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
				        	  document.getElementById("unit_name13").value="";
				        	  susNoAuto.val("");	        	  
				        	  susNoAuto.focus();
				        	  return false;	             
						}   	         
					}, 
					select: function( event, ui ) {
						 var target_unit_name = ui.item.value;
						
								 	$.post("getTargetSUSFromUNITNAME?"+key+"="+value, {target_unit_name:target_unit_name}).done(function(j) {				
								 		 var length = j.length-1;
				 				         var enc = j[length].substring(0,16);
				 				         jQuery("#sus_no13").val(dec(enc,j[0]));	
									}).fail(function(xhr, textStatus, errorThrown) {
							});
					} 	     
				}); 			
 			});

});


</script>
<script>

var key = "${_csrf.parameterName}";
var value = "${_csrf.token}";


jQuery(function() {
	// Source SUS No

	jQuery("#sus_no12").keypress(function(){
		var sus_no = this.value;
			 var susNoAuto=jQuery("#sus_no12");
			  susNoAuto.autocomplete({
			      source: function( request, response ) {
			        jQuery.ajax({
			        type: 'POST',
			        url: "getTargetSUSNoList?"+key+"="+value,
			        data: {sus_no:sus_no},
			          success: function( data ) {
			        	  var susval = [];
			        	  var length = data.length-1;
			        	  if(data.length != 0){
				        		var enc = data[length].substring(0,16);
				        	}
				        	for(var i = 0;i<data.length;i++){
				        		susval.push(dec(enc,data[i]));
				        	}
				        	var dataCountry1 = susval.join("|");
			            var myResponse = []; 
			            var autoTextVal=susNoAuto.val();
						jQuery.each(dataCountry1.toString().split("|"), function(i,e){
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
			        	  alert("Please Enter Approved SUS No.");
			        	  document.getElementById("sus_no12").value="";
			        	  susNoAuto.val("");	        	  
			        	  susNoAuto.focus();
			        	  return false;	             
			          }   	         
			      }, 
			      select: function( event, ui ) {
			    	  var unit_sus_no = ui.item.value;
			    	 	
  	 			 	$.post("getActiveUnitNameFromSusNo?"+key+"="+value, {sus_no:unit_sus_no}).done(function(j) {				
  	 			 		if(j == ""){
  	 			      	 	alert("Please Enter Approved Unit SUS No.");
  			        	  	document.getElementById("unit_name12").value="";
  			        	  	susNoAuto.val("");
  			        	  	susNoAuto.focus();
  	 			      	}else{
	    	 	   	        	var length = j.length-1;
	    	    				var enc = j[length].substring(0,16);
	    	    				$("#unit_name12").val(dec(enc,j[0]));	
	    	 	   	        		
  	 	   	        	}
  	 				}).fail(function(xhr, textStatus, errorThrown) {
  	 				});
			      } 	     
			});	
	});
	// End
	
	// Source Unit Name

    jQuery("#unit_name12").keypress(function(){
 			var unit_name = this.value;
				 var susNoAuto=jQuery("#unit_name12");
				  susNoAuto.autocomplete({
				      source: function( request, response ) {
				        jQuery.ajax({
				        type: 'POST',
				        url: "getTargetUnitsNameActiveList?"+key+"="+value,
				        data: {unit_name:unit_name},
				          success: function( data ) {
				        	  var susval = [];
				        	  var length = data.length-1;
				        	  if(data.length != 0){
					        		var enc = data[length].substring(0,16);
					        	}
					        	for(var i = 0;i<data.length;i++){
					        		susval.push(dec(enc,data[i]));
					        	}
					        	var dataCountry1 = susval.join("|");
				            var myResponse = []; 
				            var autoTextVal=susNoAuto.val();
							jQuery.each(dataCountry1.toString().split("|"), function(i,e){
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
				        	  document.getElementById("unit_name12").value="";
				        	  susNoAuto.val("");	        	  
				        	  susNoAuto.focus();
				        	  return false;	             
						}   	         
					}, 
					select: function( event, ui ) {
						 var target_unit_name = ui.item.value;
						
								 	$.post("getTargetSUSFromUNITNAME?"+key+"="+value, {target_unit_name:target_unit_name}).done(function(j) {				
								 		 var length = j.length-1;
				 				         var enc = j[length].substring(0,16);
				 				         jQuery("#sus_no12").val(dec(enc,j[0]));	
									}).fail(function(xhr, textStatus, errorThrown) {
							});
					} 	     
				}); 			
 			});

});

</script>

<script type="text/javascript">
function malUnitAuto(obj) {
    
    var check_id = obj.id;
    var k = check_id.replace('mal_unit_name','');
    var t = check_id.replace('mal_sus_no','');
    // Part No AutoComplete
    var wepetext=$("#"+obj.id);
wepetext.autocomplete({
    source: function( request, response ) {
      $.ajax({
      type: 'POST',
      url: "getTargetUnitsNameActiveList?"+key+"="+value,
      data: {unit_name:$("#"+obj.id).val()},
        success: function( data ) { 
                  var codeval = [];
                  var length = data.length-1;
                  var enc = data[length].substring(0,16);
                      for(var i = 0;i<data.length;i++){
                              codeval.push(dec(enc,data[i]));
                      } 
                      var dataCountry1 = codeval.join("|");
                      var myResponse = []; 
                  var autoTextVal=wepetext.val();
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
        } 
           else {
               alert("Please Enter Approved Unit Name."); 
//                document.getElementById("unit_name12").value="";
// 	        	  susNoAuto.val("");	        	  
// 	        	  susNoAuto.focus();
// 	        	  return false;	     
               $("#mal_unit_name"+k).val(""); 
               return false;                     
       }                 
    }, 
    select: function( event, ui ) {
            
            var target_unit_name = ui.item.value;
                  $.post("getTargetSUSFromUNITNAME?"+key+"="+value,{target_unit_name:target_unit_name}, function(j) {
                                  var length = j.length-1;
                var enc = j[length].substring(0,16);
                $("#mal_sus_no"+k).val(dec(enc,j[0]));  
        });
         } 
  });
}   


function stdUnitAuto(obj) {
    
    var check_id = obj.id;
    var k = check_id.replace('std_unit_name','');
    var t = check_id.replace('std_sus_no','');
    // Part No AutoComplete
    var wepetext=$("#"+obj.id);
wepetext.autocomplete({
    source: function( request, response ) {
      $.ajax({
      type: 'POST',
      url: "getTargetUnitsNameActiveList?"+key+"="+value,
      data: {unit_name:$("#"+obj.id).val()},
        success: function( data ) { 
                  var codeval = [];
                  var length = data.length-1;
                  var enc = data[length].substring(0,16);
                      for(var i = 0;i<data.length;i++){
                              codeval.push(dec(enc,data[i]));
                      } 
                      var dataCountry1 = codeval.join("|");
                      var myResponse = []; 
                  var autoTextVal=wepetext.val();
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
        } 
           else {
               alert("Please Enter Approved Unit Name.");    
               $("#std_unit_name"+k).val(""); 
               return false;                     
       }                 
    }, 
    select: function( event, ui ) {
            
            var target_unit_name = ui.item.value;
                  $.post("getTargetSUSFromUNITNAME?"+key+"="+value,{target_unit_name:target_unit_name}, function(j) {
                                  var length = j.length-1;
                var enc = j[length].substring(0,16);
                $("#std_sus_no"+k).val(dec(enc,j[0]));  
        });
         } 
  });
}   


function viralUnitAuto(obj) {
    
    var check_id = obj.id;
    var k = check_id.replace('viral_unit_name','');
    var t = check_id.replace('viral_sus_no','');
    // Part No AutoComplete
    var wepetext=$("#"+obj.id);
wepetext.autocomplete({
    source: function( request, response ) {
      $.ajax({
      type: 'POST',
      url: "getTargetUnitsNameActiveList?"+key+"="+value,
      data: {unit_name:$("#"+obj.id).val()},
        success: function( data ) { 
                  var codeval = [];
                  var length = data.length-1;
                  var enc = data[length].substring(0,16);
                      for(var i = 0;i<data.length;i++){
                              codeval.push(dec(enc,data[i]));
                      } 
                      var dataCountry1 = codeval.join("|");
                      var myResponse = []; 
                  var autoTextVal=wepetext.val();
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
        } 
           else {
               alert("Please Enter Approved Unit Name."); 
                 $("#viral_unit_name"+k).val(""); 
               return false;                     
       }                 
    }, 
    select: function( event, ui ) {
            
            var target_unit_name = ui.item.value;
                  $.post("getTargetSUSFromUNITNAME?"+key+"="+value,{target_unit_name:target_unit_name}, function(j) {
                                  var length = j.length-1;
                var enc = j[length].substring(0,16);
                $("#viral_sus_no"+k).val(dec(enc,j[0]));  
        });
         } 
  });
}



function h1n1UnitAuto(obj) {
    
    var check_id = obj.id;
    var k = check_id.replace('h1n1_unit_name','');
    var t = check_id.replace('h1n1_sus_no','');
    // Part No AutoComplete
    var wepetext=$("#"+obj.id);
wepetext.autocomplete({
    source: function( request, response ) {
      $.ajax({
      type: 'POST',
      url: "getTargetUnitsNameActiveList?"+key+"="+value,
      data: {unit_name:$("#"+obj.id).val()},
        success: function( data ) { 
                  var codeval = [];
                  var length = data.length-1;
                  var enc = data[length].substring(0,16);
                      for(var i = 0;i<data.length;i++){
                              codeval.push(dec(enc,data[i]));
                      } 
                      var dataCountry1 = codeval.join("|");
                      var myResponse = []; 
                  var autoTextVal=wepetext.val();
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
        } 
           else {
               alert("Please Enter Approved Unit Name.");     
               $("#h1n1_unit_name"+k).val(""); 
               return false;                     
       }                 
    }, 
    select: function( event, ui ) {
            
            var target_unit_name = ui.item.value;
                  $.post("getTargetSUSFromUNITNAME?"+key+"="+value,{target_unit_name:target_unit_name}, function(j) {
                                  var length = j.length-1;
                var enc = j[length].substring(0,16);
                $("#h1n1_sus_no"+k).val(dec(enc,j[0]));  
        });
         } 
  });
}


function injUnitAuto(obj) {
    
    var check_id = obj.id;
    var k = check_id.replace('inj_unit_name','');
    var t = check_id.replace('inj_sus_no','');
    // Part No AutoComplete
    var wepetext=$("#"+obj.id);
wepetext.autocomplete({
    source: function( request, response ) {
      $.ajax({
      type: 'POST',
      url: "getTargetUnitsNameActiveList?"+key+"="+value,
      data: {unit_name:$("#"+obj.id).val()},
        success: function( data ) { 
                  var codeval = [];
                  var length = data.length-1;
                  var enc = data[length].substring(0,16);
                      for(var i = 0;i<data.length;i++){
                              codeval.push(dec(enc,data[i]));
                      } 
                      var dataCountry1 = codeval.join("|");
                      var myResponse = []; 
                  var autoTextVal=wepetext.val();
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
        } 
           else {
               alert("Please Enter Approved Unit Name."); 
               $("#inj_unit_name"+k).val(""); 
               return false;                     
       }                 
    }, 
    select: function( event, ui ) {
            
            var target_unit_name = ui.item.value;
                  $.post("getTargetSUSFromUNITNAME?"+key+"="+value,{target_unit_name:target_unit_name}, function(j) {
                                  var length = j.length-1;
                var enc = j[length].substring(0,16);
                $("#inj_sus_no"+k).val(dec(enc,j[0]));  
        });
         } 
  });
}



function pulUnitAuto(obj) {
    
    var check_id = obj.id;
    var k = check_id.replace('pul_unit_name','');
    var t = check_id.replace('pul_sus_no','');
    // Part No AutoComplete
    var wepetext=$("#"+obj.id);
wepetext.autocomplete({
    source: function( request, response ) {
      $.ajax({
      type: 'POST',
      url: "getTargetUnitsNameActiveList?"+key+"="+value,
      data: {unit_name:$("#"+obj.id).val()},
        success: function( data ) { 
                  var codeval = [];
                  var length = data.length-1;
                  var enc = data[length].substring(0,16);
                      for(var i = 0;i<data.length;i++){
                              codeval.push(dec(enc,data[i]));
                      } 
                      var dataCountry1 = codeval.join("|");
                      var myResponse = []; 
                  var autoTextVal=wepetext.val();
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
        } 
           else {
               alert("Please Enter Approved Unit Name.");    
               $("#pul_unit_name"+k).val(""); 
               return false;                     
       }                 
    }, 
    select: function( event, ui ) {
            
            var target_unit_name = ui.item.value;
                  $.post("getTargetSUSFromUNITNAME?"+key+"="+value,{target_unit_name:target_unit_name}, function(j) {
                                  var length = j.length-1;
                var enc = j[length].substring(0,16);
                $("#pul_sus_no"+k).val(dec(enc,j[0]));  
        });
         } 
  });
}



function healthUnitAuto(obj) {
    
    var check_id = obj.id;
    var k = check_id.replace('health_unit_name','');
    var t = check_id.replace('health_sus_no','');
    // Part No AutoComplete
    var wepetext=$("#"+obj.id);
wepetext.autocomplete({
    source: function( request, response ) {
      $.ajax({
      type: 'POST',
      url: "getTargetUnitsNameActiveList?"+key+"="+value,
      data: {unit_name:$("#"+obj.id).val()},
        success: function( data ) { 
                  var codeval = [];
                  var length = data.length-1;
                  var enc = data[length].substring(0,16);
                      for(var i = 0;i<data.length;i++){
                              codeval.push(dec(enc,data[i]));
                      } 
                      var dataCountry1 = codeval.join("|");
                      var myResponse = []; 
                  var autoTextVal=wepetext.val();
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
        } 
           else {
               alert("Please Enter Approved Unit Name.");      
               $("#health_unit_name"+k).val(""); 
               return false;                     
       }                 
    }, 
    select: function( event, ui ) {
            
            var target_unit_name = ui.item.value;
                  $.post("getTargetSUSFromUNITNAME?"+key+"="+value,{target_unit_name:target_unit_name}, function(j) {
                                  var length = j.length-1;
                var enc = j[length].substring(0,16);
                $("#health_sus_no"+k).val(dec(enc,j[0]));  
        });
         } 
  });
}

function workshopUnitAuto(obj) {
    
    var check_id = obj.id;
    var k = check_id.replace('workshop_unit_name','');
    var t = check_id.replace('workshop_sus_no','');
    // Part No AutoComplete
    var wepetext=$("#"+obj.id);
wepetext.autocomplete({
    source: function( request, response ) {
      $.ajax({
      type: 'POST',
      url: "getTargetUnitsNameActiveList?"+key+"="+value,
      data: {unit_name:$("#"+obj.id).val()},
        success: function( data ) { 
                  var codeval = [];
                  var length = data.length-1;
                  var enc = data[length].substring(0,16);
                      for(var i = 0;i<data.length;i++){
                              codeval.push(dec(enc,data[i]));
                      } 
                      var dataCountry1 = codeval.join("|");
                      var myResponse = []; 
                  var autoTextVal=wepetext.val();
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
        } 
           else {
               alert("Please Enter Approved Unit Name.");   
               $("#workshop_unit_name"+k).val(""); 
               return false;                     
       }                 
    }, 
    select: function( event, ui ) {
            
            var target_unit_name = ui.item.value;
                  $.post("getTargetSUSFromUNITNAME?"+key+"="+value,{target_unit_name:target_unit_name}, function(j) {
                                  var length = j.length-1;
                var enc = j[length].substring(0,16);
                $("#workshop_sus_no"+k).val(dec(enc,j[0]));  
        });
         } 
  });
}
</script>

<<script type="text/javascript">
<!--

//-->

function PreviewImage(a) {
    var oFReader = new FileReader();
    oFReader.readAsDataURL(document.getElementById("hea_img"+a).files[0]);

    oFReader.onload = function (oFREvent) {
        document.getElementById("healthimagepreview"+a).src = oFREvent.target.result;
    };
};

function healthimage(a){
var modal = document.getElementById("myModal");

//Get the image and insert it inside the modal - use its "alt" text as a caption
var img = document.getElementById("healthimagepreview"+a);
var modalImg = document.getElementById("img01");
var captionText = document.getElementById("caption");

modal.style.display = "block";
modalImg.src = img.src;
captionText.innerHTML = img.alt;

}
function closemodel(){ 
    document.getElementById("myModal").style.display = "none";
}



function workPreviewImage(a) {
    var oFReader = new FileReader();
    oFReader.readAsDataURL(document.getElementById("work_img"+a).files[0]);

    oFReader.onload = function (oFREvent) {
        document.getElementById("workimagepreview"+a).src = oFREvent.target.result;
    };
};

function workimage(a){
var modal = document.getElementById("myModal");

//Get the image and insert it inside the modal - use its "alt" text as a caption
var img = document.getElementById("workimagepreview"+a);
var modalImg = document.getElementById("img01");
var captionText = document.getElementById("caption");

modal.style.display = "block";
modalImg.src = img.src;
captionText.innerHTML = img.alt;

}
</script>

