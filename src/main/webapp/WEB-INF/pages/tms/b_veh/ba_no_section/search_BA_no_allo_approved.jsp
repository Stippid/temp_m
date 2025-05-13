<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
.td-zoom-img{
	position: relative;
}

  .blur-image {
  filter: blur(0);
    width: 300px;
    height: auto;
    cursor: pointer;
    transition: filter 0.3s ease;
  }
  .normal-image {
    width: 300px;
    height: auto;
    cursor: pointer;
  }
  .td-zoom-img:hover .blur-image {
  filter: blur(8px);
  }
  .zoom-icon {
    position: absolute !important;
    top: 50%; 
     left: 50%; 
    transform: translate(-50%, -50%);
    font-size: 48px;
    color: white;
    cursor: pointer;
    opacity: 0;
    transition: opacity 0.3s ease;

  }
.td-zoom-img:hover .zoom-icon {
    opacity: 1;
  }

  .td-img-modal{
    display: none;
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background-color: rgba(0, 0, 0, 0.7);
    z-index: 9999;
    text-align: center;
    background: #0000008;
  }
  .td-img-modal .modal-content {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    max-width: 70%;
     max-height: 70%; 
  }
  .zoom-icon{
  position:relative;
  }
  
  .td-img-modal button.close{
position: absolute;
    right: 10px;
    top: 10px;
    background: red;
    color: white;
    height: 40px;
    width: 40px;
    display: flex;
    justify-content: center;
    align-items: center;
    border-radius: 50%;
    font-size: 30px;
  }
  
</style>
<form:form action="ba_no_allo_mstAction" method="post" class="form-horizontal" commandName="ba_no_alloCMD">
	<div class="animated fadeIn">
		<div class="">
			<div class="container" align="center">
				<div class="card">
					<h5><b>Approve Allocation Of BA Number</b></h5>
					<div class="card-header">
						<strong>Basic Details</strong>
					</div>
					<div class="card-body card-block">
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label"><strong
											style="color: red;">*</strong>Army/Non-Army </label>
									</div>
									<div class="col-12 col-md-8">
										<div class="form-check-inline form-check">
											<input type="hidden" id="id" name="id" value="${Tms_Banum_Req_PrntCmd.parent_req_id}">
											<input type="hidden" id="id_child" name="id_child" value="${Tms_Banum_Req_ChildCmd.id}">
									   		<input type="hidden" id="ba_no_type_hide" name="ba_no_type_hide" value="${Tms_Banum_Req_PrntCmd.ba_no_type}" > 
											<label for="inline-radio1" class="form-check-label "><input type="radio" id="ba_no_type1" name="ba_no_type" value="0" class="form-check-input"  >Army</label>&nbsp;&nbsp;&nbsp; 
										    <label for="inline-radio2" class="form-check-label "> <input type="radio" id="ba_no_type2" name="ba_no_type" value="1" class="form-check-input" >Non-Army</label>&nbsp;&nbsp;&nbsp;
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong style="color: red;">*</strong>Requesting Agency Dept </label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="requesting_agency" name="requesting_agency" class="form-control autocomplete" autocomplete="off" value="${Tms_Banum_Req_PrntCmd.requesting_agency}" readonly="readonly">
									</div>
								</div>

								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong style="color: red;">*</strong>Date of Req </label>
									</div>
									<div class="col-12 col-md-8">
										<input type="date" id="dte_of_reqst" name="dte_of_reqst" class="form-control" readonly="readonly">
									</div>
								</div>
							</div>

							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong style="color: red;">*</strong>SUS NO </label>
									</div>
									<div class="col-12 col-md-8">
										
										<input type="hidden" id="veh_cat" name="veh_cat" class="form-control" value="${Tms_Banum_Req_ChildCmd.veh_cat}" readonly="readonly">
										<input type="text" id="sus_no" name="sus_no" maxlength="8" class="form-control" value="${Tms_Banum_Req_PrntCmd.sus_no}" readonly="readonly">
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="card-header">
						<strong>Veh Details</strong>
					</div>
					<div class="card-body card-block">
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">Tech Specification</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="tech_spec" name="tech_spec" class="form-control"  value="${Tms_Banum_Req_ChildCmd.tech_spec}" readonly="readonly">
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong style="color: red;">* </strong>MCT</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="mct_number" name="mct_number" maxlength="10"   class="form-control autocomplete" value="${Tms_Banum_Req_ChildCmd.mct_number}" readonly="readonly" >
									</div>
								</div>
							</div>

							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">Nomenclature</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="new_nomencatre" name="new_nomencatre" class="form-control" value="${Tms_Banum_Req_ChildCmd.new_nomencatre}" readonly="readonly">
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong style="color: red;">* </strong>Vehicle Class Code</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="vehicle_clas_code" maxlength="2"name="vehicle_clas_code" placeholder="" class="form-control" value="${Tms_Banum_Req_ChildCmd.vehicle_clas_code}" readonly="readonly">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong style="color: red;">* </strong>Class of Vehicle</label> 
									</div>
									<div class="col-12 col-md-8">
									<input type="hidden" id="veh_class_hide"  value="${Tms_Banum_Req_ChildCmd.veh_class}">
										<select id="veh_class" name="veh_class" class="form-control" disabled="disabled">
											<option value="1">1</option>
											<option value="2">2</option>
											<option value="3">3</option>
											<option value="4">4</option>
											<option value="5">5</option>
										</select>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">Type of Fuel</label>
									</div>
									<div class="col-12 col-md-8">
									<input type="hidden" id="type_of_fuel_hide"  value="${Tms_Banum_Req_ChildCmd.type_of_fuel}">
										<select id="type_of_fuel" name="type_of_fuel" class="form-control" disabled="disabled"> 
											<option value="1">Petrol</option>
											<option value="2">Diesel</option>
											<option value="3">Electric</option>
											<option value="4">Cng</option>
											<option value="5">Other</option>
										</select>
									</div>
								</div>
							</div>
							
								<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">Wheel/Track</label>
									</div>
									<div class="col-12 col-md-8">
									<input type="hidden" id="wheel_track_hide"  value="${Tms_Banum_Req_ChildCmd.wheel_track}">
										<select id="wheel_track" name="wheel_track" class="form-control" onchange="typeofveh(this);" readonly="readonly" disabled="disabled">
									 		<option selected disabled>--Select--</option>
											<option value="0">Wheel</option>
				                            <option value="1">Track</option>
										</select>
									</div>
								</div>
							</div>
							
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">* </strong>Yr of Entry into Service</label>
									</div>
									<div class="col-12 col-md-8">
									 <!--  <select id="year_of_entry" name="year_of_entry" class="form-control"></select> -->
									<%--  <input type="text" id="year_of_entry" name="year_of_entry" class="form-control" value="${Tms_Banum_Req_ChildCmd.year_of_entry}" readonly="readonly">  
								 --%>	
								 		 <input type="text" id="year_of_entry" name="year_of_entry" class="form-control" readonly="readonly">  
								 	</div>
								</div>
							</div> 
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">Country of Origin</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="country_of_origin" name="country_of_origin" class="form-control"  value="${Tms_Banum_Req_ChildCmd.country_of_origin}" readonly="readonly">
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"> No of Wheels/Tr</label>
									</div>
									<div class="col-12 col-md-8">
									<input type="hidden" id="no_of_wheels_hide"  value="${Tms_Banum_Req_ChildCmd.no_of_wheels}">
			
										<select id="no_of_wheels" name="no_of_wheels" class="form-control" disabled="disabled">
											<option value="">--Select--</option>
											<option value="2">2</option>
											<option value="4">4</option>
											<option value="6">6</option>
											<option value="8">8</option>
											<option value="10">10 & Above</option>

										</select>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"> No of Axles</label>
									</div>
									<div class="col-12 col-md-8">
									<input type="hidden" id="no_of_axles_hide" name="" maxlength="20"
										class="form-control" value="${Tms_Banum_Req_ChildCmd.no_of_axles}">
										<select id="no_of_axles" name="no_of_axles" class="form-control" disabled="disabled">
											<option value="0">--Select--</option>
											<option value="4">4</option>
											<option value="2">8</option>
											<option value="6">12 & Above</option>
										</select>																			
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">Axle Whls</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="axle_wts" name="axle_wts" maxlength="5" class="form-control" placeholder="Max Five Character" value="${Tms_Banum_Req_ChildCmd.axle_wts}" readonly="readonly">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">Drive</label>
									</div>
									<div class="col-12 col-md-8">
										 <input type="hidden" id="drive_hide" name="" maxlength="20" class="form-control" value="${Tms_Banum_Req_ChildCmd.drive}" readonly="readonly"> 
										<select id="drive" name="drive" class="form-control" disabled="disabled">
											<option value="">--Select--</option>
											 <option value="1">4x2</option>
			                                     <option value="2">4x4</option>
			                                     <option value="4">8x8</option>
			                                     <option value="Tracked">Tracked</option>
			                                </select>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">Tonnage</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="tonnage" name="tonnage" maxlength="5" onkeypress="return isNumber0_9Only(event);" class="form-control" placeholder="Max Five Digits Integer Value" value="${Tms_Banum_Req_ChildCmd.tonnage}" readonly="readonly">
									</div>
								</div>
							</div>
							
								<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">No of Bogie wheel</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="hidden" id="no_of_bogie_wheel_hide"  value="${Tms_Banum_Req_ChildCmd.no_of_bogie_wheel}" readonly="readonly">
										 <select name="no_of_bogie_wheel" id="no_of_bogie_wheel" class=" form-control" disabled="disabled"> 
							                          <option selected disabled>--Select--</option>
												      <option value="0">6</option>
										              <option value="1">8</option>
										              <option value="2">12&above</option>
											  </select> 
									</div>
								</div>
							</div>
							
							
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">Towing Capacity</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="towing_capcty" name="towing_capcty" maxlength="5" class="form-control" onkeypress="return isNumber0_9Only(event);" placeholder="Max Five Digits Integer Value" value="${Tms_Banum_Req_ChildCmd.towing_capcty}" readonly="readonly">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">Lift Capacity</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="lift_capcty" name="lift_capcty" maxlength="5" class="form-control" onkeypress="return isNumber0_9Only(event);" placeholder="Max Five Digits Integer Value" value="${Tms_Banum_Req_ChildCmd.lift_capcty}" readonly="readonly">
									</div>
								</div>
							</div>
						</div>

						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">Nodal Directorate</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="sponsoring_dte" name="sponsoring_dte" class="form-control autocomplete" autocomplete="off" value="${Tms_Banum_Req_ChildCmd.sponsoring_dte}" readonly="readonly">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">* </strong>Supply Order No</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="auth_letter_no" name="auth_letter_no" class="form-control" value="${Tms_Banum_Req_ChildCmd.auth_letter_no}" readonly="readonly">
									</div>
								</div>
							</div>
						</div>

						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong style="color: red;">* </strong>Initiating auth</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="initiating_auth" name="initiating_auth" class="form-control" value="${Tms_Banum_Req_ChildCmd.initiating_auth}" readonly="readonly">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">Date of auth Letter</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="date" id="date_of_auth_letter" name="date_of_auth_letter" class="form-control" value="${Tms_Banum_Req_ChildCmd.date_of_auth_letter}" readonly="readonly">
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">Spl Eqpt Fitted</label>
									</div>
									<div class="col-12 col-md-8">
									<input type="hidden" id="spl_eqpmnt_fitd_hide" name="" class="form-control" value="${Tms_Banum_Req_ChildCmd.spl_eqpmnt_fitd}" >
										<textarea raws="2" id="spl_eqpmnt_fitd" name="spl_eqpmnt_fitd" class="form-control" readonly="readonly" ></textarea>
									</div>
								</div>
							</div>

							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong>Vehicle Category </label>
									</div>
									<div class="col-12 col-md-8">
										<div class="form-check-inline form-check">
										<input type="hidden" id="veh_cat_hide" name="" class="form-control" value="${Tms_Banum_Req_ChildCmd.veh_cat}">
											<label for="inline-radio1" class="form-check-label ">
												<input type="radio" id="veh_cat1" name="veh_cat" value="A" class="form-check-input">A Veh</label>&nbsp;&nbsp;&nbsp; 
												<label for="inline-radio2" class="form-check-label "> 
												<input type="radio" id="veh_cat2" name="veh_cat" value="B" class="form-check-input">B Veh</label>&nbsp;&nbsp;&nbsp; 
												<label for="inline-radio2" class="form-check-label "> 
												<input type="radio" id="veh_cat3" name="veh_cat" value="C" class="form-check-input">C Veh</label>&nbsp;&nbsp;&nbsp;
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
					  		<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">Purchase Cost</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="purchas_cost" name="purchas_cost" class="form-control"  value="${Tms_Banum_Req_ChildCmd.purchas_cost}" readonly="readonly">
									</div>
								</div>
							</div> 
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">Fornt View Image</label>
									</div>
									<div class="col-12 col-md-8">
									<input type="hidden" id="" name="" class="form-control" value="${Tms_Banum_Req_ChildCmd.front_view_image}">
									<img  src="ViewImageTMS?kmlFileId4=${Tms_Banum_Req_ChildCmd.id}&fildname=front_view_image"  class="pull-right" style="height: 100px;"/>
										
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">Back View Image</label>
									</div>
									<div class="col-12 col-md-8">
									<input type="hidden" id="" name="" class="form-control" value="${Tms_Banum_Req_ChildCmd.back_view_image}">
										<img  src="ViewImageTMS?kmlFileId4=${Tms_Banum_Req_ChildCmd.id}&fildname=back_view_image"  class="pull-right" style="height: 100px;"/>
									</div>
								</div>
							</div>
						</div>

						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">Side View Image</label>
									</div>
									<div class="col-12 col-md-8">
									<input type="hidden" id="" name="" class="form-control" value="${Tms_Banum_Req_ChildCmd.side_view_image}">
										<img  src="ViewImageTMS?kmlFileId4=${Tms_Banum_Req_ChildCmd.id}&fildname=side_view_image"  class="pull-right" style="height: 100px;"/>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">Top View Image</label>
									</div>
									<div class="col-12 col-md-8">
									<input type="hidden" id="" name="" class="form-control" value="${Tms_Banum_Req_ChildCmd.top_view_image}">
										<img  src="ViewImageTMS?kmlFileId4=${Tms_Banum_Req_ChildCmd.id}&fildname=top_view_image"  class="pull-right" style="height: 100px;"/>
									</div>
								</div>
							</div>
						</div>
						
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">Type of Spl Eqpt/Role for which veh will be used</label>
									</div>
									<div class="col-12 col-md-8">
									<input type="hidden" id="typ_of_spl_eqpt_role_hide" name="" class="form-control" value="${Tms_Banum_Req_ChildCmd.typ_of_spl_eqpt_role}">
										<textarea raws="2" id="typ_of_spl_eqpt_role" name="typ_of_spl_eqpt_role" class="form-control" readonly="readonly"></textarea>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">Qty</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="quantity" maxlength="4" name="quantity" class="form-control" placeholder="Max Four Character"  value="${Tms_Banum_Req_ChildCmd.quantity}"  readonly="readonly">
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="card-body card-block" style="overflow: auto;">
		            	 <div class="col s12" style="">
			              	<table class="table no-margin table-striped  table-hover  table-bordered" id="addQuantity">
				                <thead>
				                  <tr>
				                  	<th width="5%">Ser No</th>
				                  	<th width="15%"> BA No</th>
				                  	<th width="25%"> Engine No</th>
				                    <th width="25%"> Chassis No</th>
				                     <th width="15%"> Pencil Rubbing of Engine</th> 
				                     <th width="15%"> Pencil Rubbing of Chassis</th> 
				                  </tr>
				       			</thead>
				                <tbody>
				                 	 <c:forEach items="${Tms_Banum_Req_engin_chasisCmd}" var = "i" varStatus="c">
				                 	 	<tr>
				                 	 		<td id="${c.count}" width="5%">
				                 	 			${c.count}
				                 	 		</td>
				                 	 		<td width="15%"><input id="ba_no${c.count}" name="ba_no${c.count}" maxlength="20" class="form-control" readonly="readonly"></td>
				                 	 		<td width="25%"><input type="hidden" id="id${c.count}" name="id${c.count}" maxlength="20" class="form-control" value="${i.id}"  readonly="readonly">  <input id="engine_no${c.count}" name="engine_no${c.count}" maxlength="20" class="form-control" value="${i.engine_no}"  readonly="readonly"></td>
				                 	 		<td width="25%"><input id="chasis_no${c.count}" name="chasis_no${c.count}" maxlength="20" class="form-control" value="${i.chasis_no}"  readonly="readonly"></td>
				                 	 			 
				                 	 <td width="15%" class="td-zoom-img">				                 	 
    <img id="myImg"  src="ViewImageTMS?kmlFileId4=${i.id}&fildname=engine_image" class="pull-right blur-image" style="height: 100px;"/>
   <div class="zoom-icon" onclick="openModal(this)">+</div>

</td>
				                 	 		
				                 	 			<td width="15%"  class="td-zoom-img">
				                 	 		<img id="myImg"  src="ViewImageTMS?kmlFileId4=${i.id}&fildname=chasis_img"  class="pull-right blur-image" style="height: 100px;"/>
				                 	 	    <div class="zoom-icon" onclick="openModal(this)">+</div> 
				                 	 	    </td>  
				                 	 		</tr>
				                 	 </c:forEach>
				               </tbody>
				              </table>
			             </div>
			        </div>
		        	<input type="hidden" id="count" name="count" >
					<div class="card-footer" align="center">
						<jsp:include page="../../../admin/approve_line.jsp" ></jsp:include>
						<input type="submit" class="btn btn-primary btn-sm" value="Approve" onclick="return valid();"> 
					</div>
				</div>
			</div>
		</div>
	</div>

</form:form>

<div id="td-myModal" class="modal td-img-modal">
<button type="button" class="close" aria-label="Close">
  <span aria-hidden="true">×</span>
</button>
  <img class="modal-content"  id="modalImg">
</div>
<script type="text/javascript">
	$(function() {
	   var d = new Date();
         for (var i = 0; i <= 10; i++) {
             var option = "<option value=" + parseInt(d.getFullYear() + i) + ">" + parseInt(d.getFullYear() + i) + "</option>"
             $('select#year_of_entry').append(option);
         }   
         
      var str = '${Tms_Banum_Req_PrntCmd.dte_of_reqst}';
   	  var res = str.substring(0, 10);
   	  $("#dte_of_reqst").val(res);
   	  var str = '${Tms_Banum_Req_ChildCmd.year_of_entry}';
   	  var res = str.substring(0, 10);
   	  $("#year_of_entry").val(res);
   	  var str = '${Tms_Banum_Req_ChildCmd.date_of_auth_letter}';
   	  var res = str.substring(0, 10);
   	  $("#date_of_auth_letter").val(res);
         
	});
	
	function valid() {
		return true;
	}
		
</script>

<script>
$(document).ready(function() {
	
	$("select#veh_class").val($("input#veh_class_hide").val());
	$("select#type_of_fuel").val($("input#type_of_fuel_hide").val());
	$("select#drive").val($("input#drive_hide").val());	
	$("select#no_of_wheels").val($("input#no_of_wheels_hide").val());
	$("select#no_of_axles").val($("input#no_of_axles_hide").val());	
	$("textarea#typ_of_spl_eqpt_role").val($("input#typ_of_spl_eqpt_role_hide").val());
	$("textarea#spl_eqpmnt_fitd").val($("input#spl_eqpmnt_fitd_hide").val());
	$("select#wheel_track").val($("input#wheel_track_hide").val());
	$("select#no_of_bogie_wheel").val($("input#no_of_bogie_wheel_hide").val());

	var d = new Date('${Tms_Banum_Req_ChildCmd.year_of_entry}');
	$('#year_of_entry').val(d.getFullYear());
	
	var veh_qty= document.getElementById("quantity").value 
	var veh_cls_code= document.getElementById("vehicle_clas_code").value 
	var a=0;
	
	var b=1;
	var tmp=null;

	var veh_last_code = "";

			 	$.post("getba_nocodeUrl?"+key+"="+value, {veh_code:veh_cls_code,veh_qty:veh_qty}).done(function(j) {				
			 		veh_last_code = j;
					for(var i=0;i<=j.length;i++){
						k = i +1;
						$("#ba_no"+k).val(j[i]);  	
					}  
				}).fail(function(xhr, textStatus, errorThrown) {
			});

	veh_vat();	
	arm_radio();
	
});
	function isNumber0_9Only(evt) {
		var charCode = (evt.which) ? evt.which : evt.keyCode;
		if (!(charCode >= 48 && charCode <= 57) && charCode != 8) {
			return false;
		}
		return true;
	}

	function validate() {
		var r = $('input:radio[name=ba_no_type]:checked').val();
		if (r == undefined) {
			alert("Please Select Army/Non-Army.");
			$('input:radio[name=ba_no_type]:checked').focus();
			return false;
		}

		var r1 = $('input:radio[name=veh_cat]:checked').val();
		 if (r1 == undefined) {
			alert("Please Select Vehicle Category.");
			$('input:radio[name=veh_cat]:checked').focus();
			return false;
		}
		 if ($("input#requesting_agency").val() == "") {
			alert("Please Enter Requesting Agency.");
			$("input#requesting_agency").focus();
			return false;
		}
		 if ($("#dte_of_reqst").val() == "") {
			alert("Please Enter Date of Request.");
			$("#dte_of_reqst").focus();
			return false;
		}
		 if ($("#sus_no").val() == "") {
			alert("Please Enter SUS NO.");
			$("#sus_no").focus();
			return false;
		}
		
		 if ($("#mct_number").val() == "") {
			alert("Please Enter MCT Number.");
			$("#mct_number").focus();
			return false;
		}
		 if ($("#vehicle_clas_code").val() == "") {
			alert("Please Enter Vehicle Class Code.");
			$("#vehicle_clas_code").focus();
			return false;
		}
		 if ($("#veh_class").val() == "") {
			alert("Please Enter Class of Vehicle.");
			$("#veh_class").focus();
			return false;
		}
		 if ($("#year_of_entry").val() == "") {
			alert("Please Enter Yr of Entry into Service.");
			$("#year_of_entry").focus();
			return false;
		}
		 if ($("#engine_no").val() == "") {
			alert("Please Enter Engine Number.");
			$("#engine_no").focus();
			return false;
		}
		 if ($("#chasis_no").val() == "") {
			alert("Please Enter Chasis Number.");
			$("#chasis_no").focus();
			return false;
		}
		 if ($("#auth_letter_no").val() == "") {
			alert("Please Enter Supply Order Number.");
			$("#auth_letter_no").focus();
			return false;
		}
		 if($("#initiating_auth").val() == ""){
			alert("Please Enter Initiating Auth.");
			$("#initiating_auth").focus();
			return false;
		}
		 if($("#vehicle_clas_code").val() == ""){
			alert("Please Enter Vehicle Class Code.");
			$("#vehicle_clas_code").focus();
			return false;
		} 
		return true;
	}
</script>

<script type="text/javascript">
function myFunction() {
  var d = new Date();
  var n = d.getFullYear();
  document.getElementById("year_of_entry").innerHTML = n;
}
</script>

<script >
function arm_radio(){
	var str = document.getElementById("ba_no_type_hide").value;
	
	   if(str == 0){
		  document.getElementById("ba_no_type1").checked = true;
		  document.getElementById("ba_no_type2").disabled= true;
	 } 
	   if(str == 1){
		   document.getElementById("ba_no_type2").checked = true;
		   document.getElementById("ba_no_type1").disabled= true;
	   }
	}
	
function veh_vat(){
	var str = document.getElementById("veh_cat_hide").value;
	
	if(str == 'A'){
		  document.getElementById("veh_cat1").checked = true;
		  document.getElementById("veh_cat2").disabled= true;
		  document.getElementById("veh_cat3").disabled= true;
	 } 
	   if(str == 'B'){
		   document.getElementById("veh_cat2").checked = true;
		   document.getElementById("veh_cat1").disabled= true;
		   document.getElementById("veh_cat3").disabled= true;
	   }
	   if(str == 'C'){
		   document.getElementById("veh_cat3").checked = true;
		   document.getElementById("veh_cat1").disabled= true;
		   document.getElementById("veh_cat2").disabled= true;
	   }
	}

function openModal(element) {
    var modal = document.getElementById("td-myModal");
    var modalImg = document.getElementById("modalImg");
   // var img = document.querySelector(".blur-image");
    var imgSrc = element.previousElementSibling.src;
    modal.style.display = "block";
    modalImg.src = imgSrc;

    modal.onclick = function() {
      modal.style.display = "none";
    };
  }
</script>