<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<!-- <link rel="stylesheet" href="js/assets/css/bootstrap.min.css"> -->
<link rel="stylesheet" href="js/layout/css/animation.css">
<!-- <link rel="stylesheet" href="js/assets/scss/style.css">  -->
<link rel="stylesheet" href="js/assets/collapse/collapse.css">
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>
<!-- resizable_col -->
<link rel="stylesheet" href="js/assets/adjestable_Col/jquery.resizableColumns.css">
<script src="js/assets/adjestable_Col/jquery.resizableColumns.js" type="text/javascript"></script>

<style>
.sticky {
	position: fixed;
	top: 0;
	z-index: 1000;
	padding-right: 35px;
}

.sticky+.subcontent {
	padding-top: 330px;
}
</style>

<div class="container-fluid" align="center">
	<form id="Comm_form">
		<div class="animated fadeIn">
			<div class="card" align="center">
				<div class="panel-group" id="accordion">
					<div class="panel panel-default" id="insp_div1">
						<div class="panel-heading">
							<h4 class="panel-title main_title" id="insp_div5">
								<b>UPDATE CENSUS DETAILS JCO/OR</b>
							</h4>
						</div>
						<div class="col-md-12" style="padding-top: 5px; padding-left: 250px;">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong style="color: red;">* </strong>Army No</label>
									</div>
									<div class="col-md-8">
										<input type="text" id="army_no" name="army_no" class="form-control-sm form-control autocomplete" autocomplete="off">
										<input type="hidden" id="jco_id" name="jco_id" class="form-control-sm form-control autocomplete" autocomplete="off">
										<input type="hidden" id="status" name="status" value="0" class="form-control-sm form-control autocomplete" autocomplete="off">
									</div>
								</div>
							</div>
							<div class="col-md-6" id="process_btn">
								<input type="button" class="btn btn-success btn-sm" id="btn1" value="Process" onclick="return personal_number();">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
</div>
<div id="div_update_data" style="display: none;" class="subcontent">
	<div id="maindetailsdiv">
		<div class="card">
			<div class="panel-group" id="accordion4">
				<div class="panel panel-default" id="app_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title red" id="a_div5">
								<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion4" href="#" id="app_final" onclick="divN(this)"
								><b>RECORD</b></a>
						</h4>
					</div>
					<div id="collapse1app" class="panel-collapse collapse">
			                <div class="card-body card-block"><br>
			               
							  
							 
							<div class="row">

								<div class="col-md-12">	              					
	              				<div class="col-md-3">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;"></strong> Name</label>
						                </div>
						                <div class="col-md-8">
						                  <label class=" form-control-label" id="cadet_name"></label>
						                   <label class=" form-control-label" id="dob" style="display: none;"></label>
						                   <input type="hidden" class=" form-control-label" id="dob_date" >
						                   <input type="hidden" class=" form-control-label" id="enroll_date" >
						                   <label class=" form-control-label" id="marital_status_p" style="display: none;"></label>
						                </div>
						            </div>
	              				</div>	            				
	              				<div class="col-md-3">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                     <label class=" form-control-label"> Rank</label>
						                </div>
						                <div class="col-md-8">
						                     <label class=" form-control-label" id="p_rank"></label>
						                     <input type="hidden" id="hd_p_rank" name="hd_p_rank" class="form-control autocomplete" autocomplete="off" >		
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-3">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;"></strong> Appointment</label>
						                </div>
						                <div class="col-md-8">
						                  <label class=" form-control-label" id="p_app"></label>
						                </div>
						            </div>
	              				</div>	            				
	              				<div class="col-md-3">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                     <label class=" form-control-label"> Date of Appointment</label>
						                </div>
						                <div class="col-md-8">
						                     <label class=" form-control-label" id="p_app_dt"></label>		
						                </div>
						            </div>
	              				</div> 
	              			</div>          
	              		  <div class="col-md-12">	              					
	              	
	              			   <div class="col-md-3">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                     <label class=" form-control-label"> Date of TOS</label>
						                </div>
						                <div class="col-md-8">
						                     <label class=" form-control-label" id="p_tos"></label>		
						                      <input type="hidden" class=" form-control-label" id="tos_date" >
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-3">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;"></strong> SUS No</label>
						                </div>
						                <div class="col-md-8">
						                  <label class=" form-control-label" id="app_sus_no"></label>
						                </div>
						            </div>
	              				</div>
	              				  	<div class="col-md-3">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                     <label class=" form-control-label"> Unit Name</label>
						                </div>
						                <div class="col-md-8">
						                     <label class=" form-control-label" id="app_unit_name"></label>		
						                </div>
						            </div>
	              				</div>	              					
	              				<div class="col-md-3">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;"></strong> Command</label>
						                </div>
						                <div class="col-md-8">
						                  <label class=" form-control-label" id="p_cmd"></label>
						                </div>
						            </div>
	              				</div>	
	              			</div>     	

	              				  <div class="col-md-12">
	              						
	              				<div class="col-md-3">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;"></strong> Serving Status</label>
						                </div>
						                <div class="col-md-8">
						                  <label class=" form-control-label" id="p_serving_status"></label>
						                </div>
						            </div>
	              				</div>  
	              				             				
	              			</div>
	              					  <div class="col-md-12">
	              				<div class="col-md-3" style="display: none;" id = "reg_div">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;"></strong> Regiment</label>
						                </div>
						                <div class="col-md-8">
						                  <label id="p_regt" class=" form-control-label"></label>
						                   <select name="regt" id="inter_arm_regt_val" class="form-control"  style="display: none;" >
						                        <option value="0">--Select--</option>
												<c:forEach var="item" items="${getRegtList}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select> 		
						                </div>
						            </div>
	              				</div>	
	              				</div>	
	              			
	              			 	</div> 
						</div>
					</div>
				</div>
			</div>
			</div>
</div>
	
	<!-- START GENDER -->
	<form id="form_gender">
		<div class="card">
			<div class="panel-group" id="accordionaa">
				<div class="panel panel-default" id="a_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title yellow" id="a_div5">
							<a class="droparrow collapsed" data-toggle="collapse" data-parent="#accordionaa" href="#" id="a_final" onclick="divN(this)"><b>Gender</b></a>
						</h4>
					</div>
					<div id="collapse1p" class="panel-collapse collapse">
						<div class="card-body card-block">
							<br> <input type="button" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('gender');" />
							<div class="row">
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong style="color: red;">* </strong>Authority</label>
											</div>
											<div class="col-md-8">
												<input type="text" id="g_authority" name="g_authority" class="form-control-sm form-control autocomplete" autocomplete="off" maxlength="100" onkeyup="return specialcharecter(this)">
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong style="color: red;">* </strong>Date of Authority</label>
											</div>
											<div class="col-md-8">
												<input type="text" name="g_date_of_authority"
													id="g_date_of_authority" maxlength="10"
													onclick="clickclear(this, 'DD/MM/YYYY')"
													class="form-control-sm form-control"
													style="width: 93%; display: inline;"
													onfocus="this.style.color='#000000'"
													onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
													onkeyup="clickclear(this, 'DD/MM/YYYY')"
													onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);"
													aria-required="true" autocomplete="off"
													style="color: rgb(0, 0, 0);">
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"> <strong style="color: red;">* </strong>Gender </label>
											</div>
											<div class="col-md-8">
												<input type="hidden" id="g_id" name="g_id" value="0" class="form-control-sm form-control autocomplete" autocomplete="off">
												<select name="gender" id="gender" class="form-control-sm form-control">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getGenderList}" varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="card-footer" align="center" class="form-control">
								<input type="button" class="btn btn-primary btn-sm" value="Submit for Approval" onclick="validate_Gender();">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	<!-- END Gender -->
	<!-- 	START Birth-->
	<form id="form_Birth">
		<div class="card">
			<div class="panel-group" id="accordion21">
				<div class="panel panel-default" id="b_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title lightgreen" id="b_div5">
							<a class="droparrow collapsed" data-toggle="collapse" data-parent="#accordionbb" href="#" id="b_final" onclick="divN(this)"><b>DATE OF BIRTH</b></a>
						</h4>
					</div>
					<div id="collapse1x" class="panel-collapse collapse">
						<div class="card-body card-block">
							<br> <input type="button" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('date_of_birth');" />
							<div class="row">
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong style="color: red;">* </strong>Authority</label>
											</div>
											<div class="col-md-8">
												<input type="text" id="b_authority" name="authority" class="form-control-sm form-control autocomplete" autocomplete="off" maxlength="100" onkeyup="return specialcharecter(this)">
												<input type="hidden" id="birth_id" name="birth_id" value="0" class="form-control" autocomplete="off">
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong style="color: red;">* </strong>Date of Authority</label>
											</div>
											<div class="col-md-8">
												<input type="text" name="b_date_of_authority"
													id="b_date_of_authority" maxlength="10"
													onclick="clickclear(this, 'DD/MM/YYYY')"
													class="form-control-sm form-control"
													style="width: 93%; display: inline;"
													onfocus="this.style.color='#000000'"
													onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
													onkeyup="clickclear(this, 'DD/MM/YYYY')"
													onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);"
													aria-required="true" autocomplete="off"
													style="color: rgb(0, 0, 0);">
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"> <strong style="color: red;">* </strong>Date of Birth </label>
											</div>
											<div class="col-md-8">
												<input type="text" name="date_of_birth" id="date_of_birth"
													maxlength="10" onclick="clickclear(this, 'DD/MM/YYYY')"
													class="form-control-sm form-control"
													style="width: 93%; display: inline;"
													onfocus="this.style.color='#000000'"
													onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
													onkeyup="clickclear(this, 'DD/MM/YYYY')"
													onchange="clickrecall(this,'DD/MM/YYYY');calculate_age(this);"
													aria-required="true" autocomplete="off"
													style="color: rgb(0, 0, 0);">
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="card-footer" align="center" class="form-control">
								<input type="button" class="btn btn-primary btn-sm" value="Submit for Approval" onclick="validate_Birth();">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	<!-- 	END Birth -->
	<!-- START ADDRESS -->
	<form id="form_addr_details_form">
		<div class="card">
			<div class="panel-group" id="accordion9">
				<div class="panel panel-default" id="c_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title blue" id="c_div5">
							<a class="droparrow collapsed" data-toggle="collapse" data-parent="#accordion9" href="#" id="c_final" onclick="divN(this)"><b>Address of Birth</b></a>
						</h4>
					</div>
					<div id="collapse1c" class="panel-collapse collapse">
						<div class="card-body card-block">
							<br> <input type="button" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('address_of_birth');" />
							<div class="row">
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong style="color: red;">* </strong>Authority</label>
											</div>
											<div class="col-md-8">
												<input type="text" name="addr_authority" id="addr_authority" class="form-control-sm form-control autocomplete" autocomplete="off" maxlength="100" onkeyup="return specialcharecter(this)">
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;"> *</strong>Date of Authority</label>
											</div>
											<div class="col-md-8">
												<input type="text" name="addr_date_authority"
													id="addr_date_authority" maxlength="10"
													onclick="clickclear(this, 'DD/MM/YYYY')"
													class="form-control" style="width: 93%; display: inline;"
													onfocus="this.style.color='#000000'"
													onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
													onkeyup="clickclear(this, 'DD/MM/YYYY')"
													onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);"
													aria-required="true" autocomplete="off"
													style="color: rgb(0, 0, 0);">
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong> Place of Birth</label>
											</div>
											<div class="col-md-8">
												<input type="text" id="place_of_birth" name="place_of_birth"
													onkeypress="return onlyAlphabets(event);" class="form-control-sm form-control autocomplete" autocomplete="off" maxlength="50">
											</div>
										</div>
									</div>
								<div class="col-md-6">
              					<div class="row form-group">
					               <div class="col-md-4">
					                    <label class=" form-control-label"><strong style="color: red;">* </strong>Country</label>
					                </div>
					                <div class="col-md-8">
					                <select name="country_of_birth" id="country_of_birth" class="form-control"
					               onchange="fn_country_birth();"> <!-- onchange="fn_otherShowHide(this,'con_other_id','country_other');fn_country_of_birth();" -->
						                         <option value="0">--Select--</option>
												<c:forEach var="item" items="${getMedCountryName}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select> 						               				               
					               	 </div>
					            </div>
              					</div>
							</div>
								
						<div class="col-md-12">	
						<div class="col-md-6" id = "con_other_id" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Country Other</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="country_other" name="country_other" class="form-control autocomplete" autocomplete="off" onkeypress="return onlyAlphabets(event);" maxlength="50" onkeyup="return specialcharecter(this)">
					                	 </div>
						            </div>
	              				</div>
	              			<div class="col-md-6">
              				<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> State</label>
						                </div>
						                <div class="col-md-8">
						                	<select name="state_of_birth" id="state_of_birth" class="form-control" 
						                	onchange="fn_state_birth();"><!-- onchange="fn_otherShowHide(this,'sta_other_st','state_other');fn_state_of_birth();" -->
						                        <option value="0">--Select--</option>
												<%-- <c:forEach var="item" items="${getMedStateName}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach> --%>
											</select> 
						                </div>
						            </div>
	              			</div>
	              			</div>
	              			<div class="col-md-12">
								<div class="col-md-6" id = "sta_other_st" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>State Other</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="state_other" name="state_other" class="form-control autocomplete" autocomplete="off" onkeypress="return onlyAlphabets(event);" maxlength="50" onkeyup="return specialcharecter(this)">
					                	<input type="hidden" id="addr_ch_id" name="addr_ch_id" class="form-control autocomplete" autocomplete="off" value="0">
					                	 </div>
						            </div>
	              				</div>
              			
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> District</label>
						                </div>
						                <div class="col-md-8">
						                <select name="district_of_birth" id="district_of_birth" class="form-control"  onchange="fn_district_birth();" ><!-- onchange="fn_otherShowHide(this,'other_dist','district_other');fn_district_birth();" -->
					                		<option value="0">--Select--</option>
											<%-- <c:forEach var="item" items="${getMedDistrictName}" varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach> --%>
										</select> 
						                </div>
						            </div>
	              				</div>
	              			</div>
	              				<div class="col-md-12">
	              					<div class="col-md-6" id = "other_dist" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>District Other</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="district_other" name="district_other" class="form-control autocomplete" autocomplete="off" onkeypress="return onlyAlphabets(event);" maxlength="50" onkeyup="return specialcharecter(this)">
					                	 </div>
						            </div>
	              				</div>
	              				</div>
							</div>
							<div class="card-footer" align="center" class="form-control">
								<input type="button" class="btn btn-primary btn-sm" value="Submit for Approval" onclick="validate_changeaddress_details_save();">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	<!-- END ADDRESS -->
	<!-- START Nationality -->
	<form id="form_nationality">
		<div class="card">
			<div class="panel-group" id="accordionaa">
				<div class="panel panel-default" id="d_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title green" id="d_div5">
							<a class="droparrow collapsed" data-toggle="collapse" data-parent="#accordionaa" href="#" id="d_final" onclick="divN(this)"><b>Nationality</b></a>
						</h4>
					</div>
					<div id="collapse1d" class="panel-collapse collapse">
						<div class="card-body card-block">
							<br> <input type="button" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('nationality');" />
							<div class="row">
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong style="color: red;">* </strong>Authority</label>
											</div>
											<div class="col-md-8">
												<input type="text" id="n_authority" name="n_authority" class="form-control-sm form-control autocomplete" autocomplete="off" maxlength="100" onkeyup="return specialcharecter(this)">
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong style="color: red;">* </strong>Date of Authority</label>
											</div>
											<div class="col-md-8">
												<input type="text" name="n_date_of_authority"
													id="n_date_of_authority" maxlength="10"
													onclick="clickclear(this, 'DD/MM/YYYY')"
													class="form-control-sm form-control"
													style="width: 93%; display: inline;"
													onfocus="this.style.color='#000000'"
													onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
													onkeyup="clickclear(this, 'DD/MM/YYYY')"
													onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);"
													aria-required="true" autocomplete="off"
													style="color: rgb(0, 0, 0);">
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"> <strong style="color: red;">* </strong>Nationality </label>
											</div>
											<div class="col-md-8">
												<input type="hidden" id="n_id" name="n_id" value="0" class="form-control-sm form-control autocomplete" autocomplete="off">
												 <select name="nationality" id="nationality" class="form-control" 
												 onchange="fn_otherShowHide(this,'NA_other_nati','nationality_other')" >
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getNationalityList}" varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="col-md-6" id = "NA_other_nati" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Nationality Other</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="nationality_other" name="nationality_other" class="form-control autocomplete" autocomplete="off" onkeypress="return onlyAlphabets(event);" maxlength="50" onkeyup="return specialcharecter(this)">
					                	 </div>
						            </div>
	              				</div>
								</div>
							</div>
							<div class="card-footer" align="center" class="form-control">
								<input type="button" class="btn btn-primary btn-sm" value="Submit for Approval" onclick="validate_Nationality();">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	<!-- END Nationality -->
<!-- START Nationality -->
	<form id="form_Mother_Tongue">
		<div class="card">
			<div class="panel-group" id="accordionaa">
				<div class="panel panel-default" id="e_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title lightred" id="e_div5">
							<a class="droparrow collapsed" data-toggle="collapse" data-parent="#accordionaa" href="#" id="e_final" onclick="divN(this)"><b>Mother Tongue</b></a>
						</h4>
					</div>
					<div id="collapse1e" class="panel-collapse collapse">
						<div class="card-body card-block">
							<br> <input type="button" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('mother_tounge');" />
							<div class="row">
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong style="color: red;">* </strong>Authority</label>
											</div>
											<div class="col-md-8">
												<input type="text" id="m_authority" name="m_authority" class="form-control-sm form-control autocomplete" autocomplete="off" maxlength="100" onkeyup="return specialcharecter(this)">
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong style="color: red;">* </strong>Date of Authority</label>
											</div>
											<div class="col-md-8">
												<input type="text" name="m_date_of_authority"
													id="m_date_of_authority" maxlength="10"
													onclick="clickclear(this, 'DD/MM/YYYY')"
													class="form-control-sm form-control"
													style="width: 93%; display: inline;"
													onfocus="this.style.color='#000000'"
													onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
													onkeyup="clickclear(this, 'DD/MM/YYYY')"
													onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);"
													aria-required="true" autocomplete="off"
													style="color: rgb(0, 0, 0);">
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"> <strong style="color: red;">* </strong>Mother Tongue</label>
											</div>
											<div class="col-md-8">
												<input type="hidden" id="m_id" name="m_id" value="0" class="form-control-sm form-control autocomplete" autocomplete="off"> 
												<select name="mother_tounge" id="mother_tounge" class="form-control" onchange="fn_otherShowHide(this,'motherton_other','mother_tongue_other')">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getMothertoungeList}" varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="col-md-6" id = "motherton_other" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Mother Tongue Other</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="mother_tongue_other" name="mother_tongue_other" class="form-control autocomplete" autocomplete="off" onkeypress="return onlyAlphabets(event);" maxlength="50" onkeyup="return specialcharecter(this)">
					                	 </div>
						            </div>
	              				</div>
								</div>
							</div>
							<div class="card-footer" align="center" class="form-control">
								<input type="button" class="btn btn-primary btn-sm" value="Submit for Approval" onclick="validate_Mother_Tongue();">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	<!-- END Mother Tongue -->
	<!-- START Blood Group -->
	<form id="form_Blood_Group">
		<div class="card">
			<div class="panel-group" id="accordionaa">
				<div class="panel panel-default" id="f_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title red" id="f_div5">
							<a class="droparrow collapsed" data-toggle="collapse" data-parent="#accordionaa" href="#" id="f_final" onclick="divN(this)"><b>Blood Group</b></a>
						</h4>
					</div>
					<div id="collapse1f" class="panel-collapse collapse">
						<div class="card-body card-block">
							<br> <input type="button" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('blood_group');" />
							<div class="row">
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong style="color: red;">* </strong>Authority</label>
											</div>
											<div class="col-md-8">
												<input type="text" id="bg_authority" name="bg_authority" class="form-control-sm form-control autocomplete" autocomplete="off" maxlength="100" onkeyup="return specialcharecter(this)">
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong style="color: red;">* </strong>Date of Authority</label>
											</div>
											<div class="col-md-8">
												<input type="text" name="bg_date_of_authority"
													id="bg_date_of_authority" maxlength="10"
													onclick="clickclear(this, 'DD/MM/YYYY')"
													class="form-control-sm form-control"
													style="width: 93%; display: inline;"
													onfocus="this.style.color='#000000'"
													onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
													onkeyup="clickclear(this, 'DD/MM/YYYY')"
													onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);"
													aria-required="true" autocomplete="off"
													style="color: rgb(0, 0, 0);">
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"> <strong style="color: red;">* </strong>Blood Group</label>
											</div>
											<div class="col-md-8">
												<input type="hidden" id="bg_id" name="bg_id" value="0" class="form-control-sm form-control autocomplete" autocomplete="off"> 
												<select name="blood_group" id="blood_group" class="form-control">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getBloodList}" varStatus="num">
														<option value="${item[0]}" name="${item[0]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="card-footer" align="center" class="form-control">
								<input type="button" class="btn btn-primary btn-sm" value="Submit for Approval" onclick="validate_Blood_Group();">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	<!-- END Blood Group -->
	<!-- START Height -->
	<form id="form_Height">
		<div class="card">
			<div class="panel-group" id="accordionaa">
				<div class="panel panel-default" id="g_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title blue" id="g_div5">
							<a class="droparrow collapsed" data-toggle="collapse" data-parent="#accordionaa" href="#" id="g_final" onclick="divN(this)"><b>Height</b></a>
						</h4>
					</div>
					<div id="collapse1g" class="panel-collapse collapse">
						<div class="card-body card-block">
							<br> <input type="button" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('height');" />
							<div class="row">
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong style="color: red;">* </strong>Authority</label>
											</div>
											<div class="col-md-8">
												<input type="text" id="h_authority" name="h_authority" class="form-control-sm form-control autocomplete" autocomplete="off" maxlength="100" onkeyup="return specialcharecter(this)">
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong style="color: red;">* </strong>Date of Authority</label>
											</div>
											<div class="col-md-8">
												<input type="text" name="h_date_of_authority"
													id="h_date_of_authority" maxlength="10"
													onclick="clickclear(this, 'DD/MM/YYYY')"
													class="form-control-sm form-control"
													style="width: 93%; display: inline;"
													onfocus="this.style.color='#000000'"
													onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
													onkeyup="clickclear(this, 'DD/MM/YYYY')"
													onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);"
													aria-required="true" autocomplete="off"
													style="color: rgb(0, 0, 0);">
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"> <strong style="color: red;">* </strong>Height</label>
											</div>
											<div class="col-md-8">
												<input type="hidden" id="h_id" name="h_id" value="0" class="form-control-sm form-control autocomplete" autocomplete="off"> 
												<select name="height" id="height" class="form-control">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getHeight}" varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="card-footer" align="center" class="form-control">
								<input type="button" class="btn btn-primary btn-sm" value="Submit for Approval" onclick="validate_Height();">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	<!-- END Height -->
	<!-- START Aadhar No -->
	<form id="form_aadhar_no">
		<div class="card">
			<div class="panel-group" id="accordionaa">
				<div class="panel panel-default" id="h_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title lightgreen" id="h_div5">
							<a class="droparrow collapsed" data-toggle="collapse" data-parent="#accordionaa" href="#" id="h_final" onclick="divN(this)"><b>Aadhaar No</b></a>
						</h4>
					</div>
					<div id="collapse1h" class="panel-collapse collapse">
						<div class="card-body card-block">
							<br> <input type="button" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('aadhar_no');" />
							<div class="row">
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong style="color: red;">* </strong>Authority</label>
											</div>
											<div class="col-md-8">
												<input type="text" id="aa_authority" name="aa_authority" class="form-control-sm form-control autocomplete" autocomplete="off" maxlength="100" onkeyup="return specialcharecter(this)">
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong style="color: red;">* </strong>Date of Authority</label>
											</div>
											<div class="col-md-8">
												<input type="text" name="aa_date_of_authority"
													id="aa_date_of_authority" maxlength="10"
													onclick="clickclear(this, 'DD/MM/YYYY')"
													class="form-control-sm form-control"
													style="width: 93%; display: inline;"
													onfocus="this.style.color='#000000'"
													onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
													onkeyup="clickclear(this, 'DD/MM/YYYY')"
													onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);"
													aria-required="true" autocomplete="off"
													style="color: rgb(0, 0, 0);">
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12">
								 <div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong style="color: red;">* </strong> Aadhaar No</label>
										</div>
								 		<div class="col-md-2">
								 			<input type="hidden" id="aa_id" name="aa_id" value="0" class="form-control-sm form-control autocomplete" autocomplete="off">
											<input type="text" id="adhar_number1" name="adhar_number1"
												class="form-control-sm form-control autocomplete" autocomplete="off" maxlength="4" onkeypress="return isNumber0_9Only(event)" onkeyup="movetoNext(this, 'adhar_number2'),lengthadhar()">
										</div>
										<div class="col-md-2">
											<input type="text" id="adhar_number2" name="adhar_number2"
												class="form-control-sm form-control autocomplete" autocomplete="off" maxlength="4" onkeypress="return isNumber0_9Only(event)" onkeyup="movetoNext(this, 'adhar_number3'),lengthadhar()">
										</div>
										<div class="col-md-2">
											<input type="text" id="adhar_number3" name="adhar_number3"
												class="form-control-sm form-control autocomplete" autocomplete="off" maxlength="4" onkeypress="return isNumber0_9Only(event)" onkeyup="lengthadhar()">
										</div> 
									</div>
								</div> 
								</div>
							</div>
							<div class="card-footer" align="center" class="form-control">
								<input type="button" class="btn btn-primary btn-sm" value="Submit for Approval" onclick="validate_aadhar_no();">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	<!-- END Aadhar No -->
	<!-- START Pan No -->
	<form id="form_pan_no">
		<div class="card">
			<div class="panel-group" id="accordionaa">
				<div class="panel panel-default" id="i_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title blue" id="i_div5">
							<a class="droparrow collapsed" data-toggle="collapse" data-parent="#accordionaa" href="#" id="i_final" onclick="divN(this)"><b>Pan No</b></a>
						</h4>
					</div>
					<div id="collapse1i" class="panel-collapse collapse">
						<div class="card-body card-block">
							<br> <input type="button" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('pan_no');" />
							<div class="row">
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong style="color: red;">* </strong>Authority</label>
											</div>
											<div class="col-md-8">
												<input type="hidden" id="pan_id" name="pan_id" value="0" class="form-control-sm form-control autocomplete" autocomplete="off">
												<input type="text" id="pan_authority" name="pan_authority" class="form-control-sm form-control autocomplete" autocomplete="off" maxlength="100" onkeyup="return specialcharecter(this)">
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong style="color: red;">* </strong>Date of Authority</label>
											</div>
											<div class="col-md-8">
												<input type="text" name="pan_date_of_authority"
													id="pan_date_of_authority" maxlength="10"
													onclick="clickclear(this, 'DD/MM/YYYY')"
													class="form-control-sm form-control"
													style="width: 93%; display: inline;"
													onfocus="this.style.color='#000000'"
													onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
													onkeyup="clickclear(this, 'DD/MM/YYYY')"
													onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);"
													aria-required="true" autocomplete="off"
													style="color: rgb(0, 0, 0);">
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12">
								 <div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong style="color: red;">* </strong> Pan No</label>
										</div>
								 	<div class="col-md-8">
										<input type="text" id="pan_no" name="pan_no" class="form-control-sm form-control autocomplete" autocomplete="off" onchange=" isPAN(this); " oninput="this.value = this.value.toUpperCase()"maxlength="10">
									</div>
									</div>
								</div> 
								</div>
							</div>
							<div class="card-footer" align="center" class="form-control">
								<input type="button" class="btn btn-primary btn-sm" value="Submit for Approval" onclick="validate_pan_no();">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	<!-- END Pan No -->
	<!-- START Class Enrolment -->
	<form id="form_class_enrolment">
		<div class="card">
			<div class="panel-group" id="accordionaa">
				<div class="panel panel-default" id="j_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title red" id="j_div5">
							<a class="droparrow collapsed" data-toggle="collapse" data-parent="#accordionaa" href="#" id="j_final" onclick="divN(this)"><b>Class for Enrollment</b></a>
						</h4>
					</div>
					<div id="collapse1j" class="panel-collapse collapse">
						<div class="card-body card-block">
							<br> <input type="button" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('class_enroll');" />
							<div class="row">
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong style="color: red;">* </strong>Authority</label>
											</div>
											<div class="col-md-8">
												<input type="text" id="ce_authority" name="ce_authority" class="form-control-sm form-control autocomplete" autocomplete="off" maxlength="100" onkeyup="return specialcharecter(this)">
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong style="color: red;">* </strong>Date of Authority</label>
											</div>
											<div class="col-md-8">
												<input type="text" name="ce_date_of_authority"
													id="ce_date_of_authority" maxlength="10"
													onclick="clickclear(this, 'DD/MM/YYYY')"
													class="form-control-sm form-control"
													style="width: 93%; display: inline;"
													onfocus="this.style.color='#000000'"
													onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
													onkeyup="clickclear(this, 'DD/MM/YYYY')"
													onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);"
													aria-required="true" autocomplete="off"
													style="color: rgb(0, 0, 0);">
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"> <strong style="color: red;">* </strong>Class for Enrollment </label>
											</div>
											<div class="col-md-8">
												<input type="hidden" id="ce_id" name="ce_id" value="0" class="form-control-sm form-control autocomplete" autocomplete="off">
												<select name="class_enroll" id="class_enroll" class="form-control" onchange="fn_domicile();">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getClass_enrollList}" varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="col-md-6" id="gorkha_domicile" style="display:none;">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong>Domicile</label>
										</div>
										<div class="col-md-8">
								 	<select name="domicile" id="domicile"
										class="form-control">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getclass_domicileList}"
											varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
										</div>
									</div>
								</div>
								</div>
							</div>
							<div class="card-footer" align="center" class="form-control">
								<input type="button" class="btn btn-primary btn-sm" value="Submit for Approval" onclick="validate_Class_Enrolment();">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	<!-- END Class Enrolment -->
	
<!--START Date of Enrollment-->
	<form id="form_dt_of_enrollment">
		<div class="card">
			<div class="panel-group" id="accordion21">
				<div class="panel panel-default" id="l_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title blue" id="l_div5">
							<a class="droparrow collapsed" data-toggle="collapse" data-parent="#accordionbb" href="#" id="l_final" onclick="divN(this)"><b> Date of Enrollment</b></a>
						</h4>
					</div>
					<div id="collapse1l" class="panel-collapse collapse">
						<div class="card-body card-block">
							<br> <input type="button" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('dt_of_enrollment');" />
							<div class="row">
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong style="color: red;">* </strong>Authority</label>
											</div>
											<div class="col-md-8">
												<input type="text" id="doe_authority" name="doe_authority" class="form-control-sm form-control autocomplete" autocomplete="off" maxlength="100" onkeyup="return specialcharecter(this)">
												<input type="hidden" id="doe_id" name="doe_id" value="0" class="form-control" autocomplete="off">
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong style="color: red;">* </strong>Date of Authority</label>
											</div>
											<div class="col-md-8">
												<input type="text" name="doe_date_of_authority"
													id="doe_date_of_authority" maxlength="10"
													onclick="clickclear(this, 'DD/MM/YYYY')"
													class="form-control-sm form-control"
													style="width: 93%; display: inline;"
													onfocus="this.style.color='#000000'"
													onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
													onkeyup="clickclear(this, 'DD/MM/YYYY')"
													onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);"
													aria-required="true" autocomplete="off"
													style="color: rgb(0, 0, 0);">
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"> <strong style="color: red;">* </strong> Date of Enrollment </label>
											</div>
											<div class="col-md-8">
												<input type="text" name="enroll_dt" id="enroll_dt"
													maxlength="10" onclick="clickclear(this, 'DD/MM/YYYY')"
													class="form-control-sm form-control"
													style="width: 93%; display: inline;"
													onfocus="this.style.color='#000000'"
													onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
													onkeyup="clickclear(this, 'DD/MM/YYYY')"
													onchange="clickrecall(this,'DD/MM/YYYY');"
													aria-required="true" autocomplete="off"
													style="color: rgb(0, 0, 0);">
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="card-footer" align="center" class="form-control">
								<input type="button" class="btn btn-primary btn-sm" value="Submit for Approval" onclick="validate_enroll_dt();">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
								           		<input type="hidden" id="min_date_enroll" name="min_date_enroll" class="form-control autocomplete"  > 
		
	</form>
	<!--END  Date of Enrollment-->
	<!--START Date of Attestation-->
	<form id="form_dt_of_Attestation">
		<div class="card">
			<div class="panel-group" id="accordion21">
				<div class="panel panel-default" id="m_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title red" id="m_div5">
							<a class="droparrow collapsed" data-toggle="collapse" data-parent="#accordionbb" href="#" id="m_final" onclick="divN(this)"><b>  Date of Attestation</b></a>
						</h4>
					</div>
					<div id="collapse1m" class="panel-collapse collapse">
						<div class="card-body card-block">
							<br> <input type="button" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('dt_of_attest');" />
							<div class="row">
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong style="color: red;">* </strong>Authority</label>
											</div>
											<div class="col-md-8">
												<input type="text" id="doa_authority" name="doa_authority" class="form-control-sm form-control autocomplete" autocomplete="off" maxlength="100" onkeyup="return specialcharecter(this)">
												<input type="hidden" id="doa_id" name="doa_id" value="0" class="form-control" autocomplete="off">
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong style="color: red;">* </strong>Date of Authority</label>
											</div>
											<div class="col-md-8">
												<input type="text" name="doa_date_of_authority"
													id="doa_date_of_authority" maxlength="10"
													onclick="clickclear(this, 'DD/MM/YYYY')"
													class="form-control-sm form-control"
													style="width: 93%; display: inline;"
													onfocus="this.style.color='#000000'"
													onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
													onkeyup="clickclear(this, 'DD/MM/YYYY')"
													onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);"
													aria-required="true" autocomplete="off"
													style="color: rgb(0, 0, 0);">
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"> <strong style="color: red;">* </strong>  Date of Attestation </label>
											</div>
											<div class="col-md-8">
												<input type="text" name="date_of_attestation" id="date_of_attestation"
													maxlength="10" onclick="clickclear(this, 'DD/MM/YYYY')"
													class="form-control-sm form-control"
													style="width: 93%; display: inline;"
													onfocus="this.style.color='#000000'"
													onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
													onkeyup="clickclear(this, 'DD/MM/YYYY')"
													onchange="clickrecall(this,'DD/MM/YYYY');"
													aria-required="true" autocomplete="off"
													style="color: rgb(0, 0, 0);">
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="card-footer" align="center" class="form-control">
								<input type="button" class="btn btn-primary btn-sm" value="Submit for Approval" onclick="validate_date_of_attestation();">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	<!--END   Date of Attestation-->
	<!--START Family details-->
	<form id="form_family_details">
		<div class="card">
			<div class="panel-group" id="accordion21">
				<div class="panel panel-default" id="n_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title blue" id="n_div5">
							<a class="droparrow collapsed" data-toggle="collapse" data-parent="#accordionbb" href="#" id="n_final" onclick="divN(this)"><b> Family Details</b></a>
						</h4>
					</div>
					<div id="collapse1n" class="panel-collapse collapse">
						<div class="card-body card-block">
							<br> <input type="button" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('family_details');" />
							<div class="row">
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong style="color: red;">* </strong>Authority</label>
											</div>
											<div class="col-md-8">
												<input type="text" id="fd_authority" name="fd_authority" class="form-control-sm form-control autocomplete" autocomplete="off" maxlength="100" onkeyup="return specialcharecter(this)">
												<input type="hidden" id="fd_id" name="fd_id" value="0" class="form-control" autocomplete="off">
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong style="color: red;">* </strong>Date of Authority</label>
											</div>
											<div class="col-md-8">
												<input type="text" name="fd_date_of_authority"
													id="fd_date_of_authority" maxlength="10"
													onclick="clickclear(this, 'DD/MM/YYYY')"
													class="form-control-sm form-control"
													style="width: 93%; display: inline;"
													onfocus="this.style.color='#000000'"
													onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
													onkeyup="clickclear(this, 'DD/MM/YYYY')"
													onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);"
													aria-required="true" autocomplete="off"
													style="color: rgb(0, 0, 0);">
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong style="color: red;">* </strong>Father's Name</label>
											</div>
											<div class="col-md-8">
												<input type="text" id="father_name" name="father_name" class="form-control-sm form-control autocomplete" autocomplete="off" onkeypress="return onlyAlphabets(event);" oninput="this.value = this.value.toUpperCase()" maxlength="50" onkeyup="return specialcharecter(this)">

											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong style="color: red;">* </strong>Father's Date of Birth</label>
											</div>
											<div class="col-md-8">
												<input type="text" name="father_dob" id="father_dob"
													maxlength="10" onclick="clickclear(this, 'DD/MM/YYYY')"
													class="form-control" style="width: 85%; display: inline;"
													onfocus="this.style.color='#000000'"
													onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
													onkeyup="clickclear(this, 'DD/MM/YYYY')"
													onchange="clickrecall(this,'DD/MM/YYYY');calculate_age(this);"
													aria-required="true" autocomplete="off"
													style="color: rgb(0, 0, 0);">
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong style="color: red;">* </strong>Father's Place of Birth</label>
											</div>
											<div class="col-md-8">
												<input type="text" id="father_place_birth" name="father_place_birth" class="form-control-sm form-control autocomplete" autocomplete="off" oninput="this.value = this.value.toUpperCase()" maxlength="50" onkeyup="return specialcharecter(this)">
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong style="color: red;">* </strong>Father In Service/Ex-Service</label>
											</div>
											<div class="col-md-8">
												<input type="radio" id="father_proff_radio1" name="father_proff_radio" value="yes" onchange="father_proffFn();">&nbsp <label for="yes">Yes</label>&nbsp
												<input type="radio" id="father_proff_radio2" name="father_proff_radio" value="no" onchange="father_proffFn();" checked="checked">&nbsp
												<label for="no">No</label><br>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12">
									<div id="father_inserviceDiv" class="col-md-12" style="display: none">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong style="color: red;">* </strong>Father's Services</label>
												</div>
												<div class="col-md-8">
													<select name="father_service" id="father_service" class="form-control-sm form-control" onchange="ServiceOtherFn('father_otherDiv','father_personalDiv',this)">
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${getExservicemenList}" varStatus="num">
															<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
														</c:forEach>
													</select>
												</div>
											</div>
										</div>
										<div class="col-md-6" id="father_otherDiv" style="display: none">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong style="color: red;">*</strong>Other</label>
												</div>
												<div class="col-md-8">
													<input type="text" id="father_other" name="father_other" class="form-control-sm form-control autocomplete" autocomplete="off"
														oninput="this.value = this.value.toUpperCase()" maxlength="50" onkeyup="return specialcharecter(this)">
												</div>
											</div>
										</div>
										<div class="col-md-6" id="father_personalDiv">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong style="color: red;">*</strong>Father's Personal No.</label>
												</div>
												<div class="col-md-8">
													<input type="text" id="father_personal_no" name="father_personal_no"
														class="form-control-sm form-control autocomplete" autocomplete="off" 
														onkeypress="return /[0-9a-zA-Z]/i.test(event.key)"
														oninput="this.value = this.value.toUpperCase()" maxlength="9" min="7">
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-6" id="father_proffDiv">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong style="color: red;">* </strong>Father's Profession</label>
											</div>
											<div class="col-md-8">
												<select name="father_profession" id="father_profession" class="form-control" onchange="fn_father_other();">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getProfession}" varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12">
								<div class="col-md-6" id="father_proffotherDiv" style="display: none">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">*</strong>Other</label>
										</div>
										<div class="col-md-8">
											<input type="text" id="father_proffother" name="father_proffother"
												class="form-control autocomplete" autocomplete="off"
												oninput="this.value = this.value.toUpperCase()" maxlength="50" onkeyup="return specialcharecter(this)">


										</div>
									</div>
								</div>
								</div>
								
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong style="color: red;">* </strong>Mother's Name</label>
											</div>
											<div class="col-md-8">
												<input type="text" id="mother_name" name="mother_name" class="form-control-sm form-control autocomplete" autocomplete="off" onkeypress="return onlyAlphabets(event);" oninput="this.value = this.value.toUpperCase()" maxlength="50" onkeyup="return specialcharecter(this)">
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong style="color: red;">* </strong>Mother's Date of Birth</label>
											</div>
											<div class="col-md-8">
												<input type="text" name="mother_dob" id="mother_dob"
													maxlength="10" onclick="clickclear(this, 'DD/MM/YYYY')"
													class="form-control" style="width: 85%; display: inline;"
													onfocus="this.style.color='#000000'"
													onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
													onkeyup="clickclear(this, 'DD/MM/YYYY')"
													onchange="clickrecall(this,'DD/MM/YYYY');calculate_age(this);"
													aria-required="true" autocomplete="off"
													style="color: rgb(0, 0, 0);">
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong style="color: red;">* </strong>Mother's Place of Birth</label>
											</div>
											<div class="col-md-8">
												<input type="text" id="mother_place_birth" name="mother_place_birth" class="form-control-sm form-control autocomplete" autocomplete="off" oninput="this.value = this.value.toUpperCase()" maxlength="50" onkeyup="return specialcharecter(this)">
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong style="color: red;">* </strong>Mother In Service/Ex-Service</label>
											</div>
											<div class="col-md-8">
												<input type="radio" id="mother_proff_radio1" name="mother_proff_radio" value="yes" onchange="mother_proffFn();">&nbsp <label for="yes">Yes</label>&nbsp
												<input type="radio" id="mother_proff_radio2" name="mother_proff_radio" value="no" onchange="mother_proffFn();" checked="checked">&nbsp
												<label for="no">No</label><br>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12">
									<div id="mother_inserviceDiv" class="col-md-12" style="display: none">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong style="color: red;">* </strong>Mother's Services</label>
												</div>
												<div class="col-md-8">
													<select name="mother_service" id="mother_service" class="form-control-sm form-control"
														onchange="ServiceOtherFn('mother_otherDiv','mother_personalDiv',this)">
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${getExservicemenList}" varStatus="num">
															<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
														</c:forEach>
													</select>
												</div>
											</div>
										</div>
										<div class="col-md-6" id="mother_otherDiv" style="display: none">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong style="color: red;">*</strong>Other</label>
												</div>
												<div class="col-md-8">
													<input type="text" id="mother_other" name="mother_other" class="form-control-sm form-control autocomplete" autocomplete="off"
														oninput="this.value = this.value.toUpperCase()" maxlength="50" onkeyup="return specialcharecter(this)">
												</div>
											</div>
										</div>
										<div class="col-md-6" id="mother_personalDiv">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong style="color: red;">*</strong>Mother's Personal No.</label>
												</div>
												<div class="col-md-8">
													<input type="text" id="mother_personal_no" name="mother_personal_no"
														class="form-control-sm form-control autocomplete" autocomplete="off"
														onkeypress="return /[0-9a-zA-Z]/i.test(event.key)"
														oninput="this.value = this.value.toUpperCase()"maxlength="9" min="7">
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-6" id="mother_proffDiv">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong style="color: red;">* </strong>Mother's Profession</label>
											</div>
											<div class="col-md-8">
												<select name="mother_profession" id="mother_profession" class="form-control" onchange="fn_mother_other();">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getProfession}" varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="col-md-6" id="mother_otherproffDiv" style="display: none">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">*</strong>Other</label>
										</div>
										<div class="col-md-8">
											<input type="text" id="mother_proffother" name="mother_proffother"
												class="form-control autocomplete" autocomplete="off"
												oninput="this.value = this.value.toUpperCase()" maxlength="50" onkeyup="return specialcharecter(this)">
										</div>
									</div>
								</div>
								</div>
							</div>
							<div class="card-footer" align="center" class="form-control">
								<input type="button" class="btn btn-primary btn-sm" value="Submit for Approval" onclick="validate_family_details();">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	<!--END Family Details-->
<!--START Details of Sibilings-->
	<form id="form_sibilings">
		<div class="card">
			<div class="panel-group" id="accordion21">
				<div class="panel panel-default" id="o_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title lightblue" id="o_div5">
							<a class="droparrow collapsed" data-toggle="collapse" data-parent="#accordionbb" href="#" id="o_final" onclick="divN(this)"><b>Details of Sibling</b></a>
						</h4>
					</div>
					<div id="collapse1o" class="panel-collapse collapse">
						<div class="card-body card-block">
							<br> <input type="button" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('sibling_details');" />
							<div class="row">
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong style="color: red;">* </strong>Authority</label>
											</div>
											<div class="col-md-8">
												<input type="text" id="ds_authority" name="ds_authority" class="form-control-sm form-control autocomplete" autocomplete="off" maxlength="100" onkeyup="return specialcharecter(this)">
												<input type="hidden" id="ds_id" name="ds_id" value="0" class="form-control" autocomplete="off">
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong style="color: red;">* </strong>Date Of Authority</label>
											</div>
											<div class="col-md-8">
												<input type="text" name="ds_date_of_authority"
													id="ds_date_of_authority" maxlength="10"
													onclick="clickclear(this, 'DD/MM/YYYY')"
													class="form-control-sm form-control"
													style="width: 93%; display: inline;"
													onfocus="this.style.color='#000000'"
													onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
													onkeyup="clickclear(this, 'DD/MM/YYYY')"
													onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);"
													aria-required="true" autocomplete="off"
													style="color: rgb(0, 0, 0);">
											</div>
										</div>
									</div>
								</div>
							<div class="col-md-12 watermarked" style="display: block;">
							<table id="family_table" class="table-bordered "
								style="margin-top: 10px; width: 100%;">

								<thead style="color: white; text-align: center;">
									<tr>
										<th>Sr No</th>
										<th>Name</th>
										<th>Date of Birth</th>
										<th>Relationship</th>
										<th>Aadhar No</th>
										<th>PAN No</th>
										<th>Service/Ex-Service</th>
										<th>Services</th>
										<th>Personal No.</th>
										<th>Other Service</th>
										<th class="hide-action">Action</th>
									</tr>
								</thead>
								<tbody data-spy="scroll" id="family_sibtbody"
									style="min-height: 46px; max-height: 650px; text-align: center;">
									<tr id="tr_sibling1">
										<td class="sib_srno">1</td>
										<td><input type="text" id="sib_name1"
											name="sib_name1" class="form-control autocomplete"
											onkeypress="return onlyAlphabets(event);"
											oninput="this.value = this.value.toUpperCase()" maxlength="50"
											autocomplete="off"></td>
										<td>
											<input
											type="text" name="sib_date_of_birth1" id="sib_date_of_birth1"
											maxlength="10" onclick="clickclear(this, 'DD/MM/YYYY')"
											class="form-control" style="width: 85%; display: inline;"
											onfocus="this.style.color='#000000'"
											onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
											onkeyup="clickclear(this, 'DD/MM/YYYY')"
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);"
											aria-required="true" autocomplete="off"
											style="color: rgb(0, 0, 0);" value="DD/MM/YYYY">

										</td>
										<td><select name="sib_relationship1"
											id="sib_relationship1" class="form-control-sm form-control">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getFamily_siblings}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
										</select></td>

										<td><input type="text"
											id="sib_adhar_number1" name="sib_adhar_number1"
											class="form-control autocomplete" maxlength="14"
											onkeypress="return isAadhar(this,event); " autocomplete="off"></td>

										<td><input type="text"
											id="sib_pan_no1" name="sib_pan_no1"
											class="form-control autocomplete" autocomplete="off"
											onchange=" isPAN(this); "
											oninput="this.value = this.value.toUpperCase()"
											maxlength="10"></td>

										<td style="display: none;"><input type="text"
											id="sib_ch_id1" name="sib_ch_id1" value="0"
											class="form-control autocomplete" autocomplete="off"></td>
										<td>
										<input type="checkbox" id="ser-ex1" name="ser-ex1" value="Yes" onclick="siblingCB(1);">
										</td>
										<td><select name="sibling_service1" id="sibling_service1" onchange = "otherfunction(1)"
												class="form-control-sm form-control"
												>
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getExservicemenList}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select></td>
											<td><input type="text" id="sibling_personal_no1"
												name="sibling_personal_no1" class="form-control autocomplete"
												autocomplete="off"
												maxlength="15"
												onkeypress="return /[0-9a-zA-Z]/i.test(event.key)"
												oninput="this.value = this.value.toUpperCase()">
											</td>
											<td><input type="text" id="other_sibling_ser1"
												name="other_sibling_ser1" class="form-control autocomplete"
												autocomplete="off"
												onkeypress="return onlyAlphaNumeric(event);"
												oninput="this.value = this.value.toUpperCase()">
											</td>
										<td class="hide-action"><a
											class="btn btn-primary btn-sm" value="SAVE" title="SAVE"
											id="family_save1" onclick="family_save_fn(1);"><i
												class="fa fa-save"></i></a> <a style="display: none;"
											class="btn btn-success btn-sm" value="ADD" title="ADD"
											id="family_add1" onclick="family_add_fn(1);"><i
												class="fa fa-plus"></i></a> <a style="display: none;"
											class="btn btn-danger btn-sm" value="REMOVE" title="REMOVE"
											id="family_remove1" onclick="family_remove_fn(1);"><i
												class="fa fa-trash"></i></a></td>
									</tr>
								</tbody>
							</table>
						</div>
							</div>
							
										 <input type="hidden" id="army_noV" name="army_noV" class="form-control autocomplete"  > 
						                 <input type="hidden" id="statusV" name="statusV" class="form-control autocomplete"  > 
						                 <input type="hidden" id="rankV" name="rankV" class="form-control autocomplete"  > 
						                 <input type=hidden id="jco_idV" name="jco_idV" class="form-control autocomplete"  > 
						                 <input type="hidden" id="unit_nameV" name="unit_nameV" class="form-control autocomplete"  > 
						                 <input type="hidden" id="unit_sus_noV" name="unit_sus_noV" class="form-control autocomplete"  > 
						                 
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
<!--END Details of Sibilings-->
	</div>
	
<c:url value="Preview_Gender_JCO_Url" var="Preview_Gender_JCO_Url" />
<form:form action="${Preview_Gender_JCO_Url}" method="post" id="Preview_Gender_JCO_Form" name="Preview_Gender_JCO_Form" modelAttribute="id" target="result">
	<input type="hidden" name="gen_jco_id" id="gen_jco_id" value="0" />
</form:form>

<c:url value="Preview_Date_Of_Birth_Url" var="Preview_Date_Of_Birth_Url" />
<form:form action="${Preview_Date_Of_Birth_Url}" method="post" id="Preview_Date_Of_Birth_Url_JCO_Form" name="Preview_Date_Of_Birth_Url_JCO_Form" modelAttribute="id" target="result">
	<input type="hidden" name="dob_jco_id" id="dob_jco_id" value="0" />
</form:form>
	
<c:url value="Preview_Address_Of_Birth_Url" var="Preview_Address_Of_Birth_Url" />
<form:form action="${Preview_Address_Of_Birth_Url}" method="post" id="Preview_Address_Of_Birth_Url_JCO_Form" name="Preview_Address_Of_Birth_Url_JCO_Form" modelAttribute="id" target="result">
	<input type="hidden" name="add_jco_id" id="add_jco_id" value="0" />
</form:form>
	
<c:url value="Preview_Nationality_Url" var="Preview_Nationality_Url" />
<form:form action="${Preview_Nationality_Url}" method="post" id="Preview_Nationality_Url_JCO_Form" name="Preview_Nationality_Url_JCO_Form" modelAttribute="id" target="result">
	<input type="hidden" name="n_jco_id" id="n_jco_id" value="0" />
</form:form>
	
<c:url value="Preview_Mother_Tounge_Url" var="Preview_Mother_Tounge_Url" />
<form:form action="${Preview_Mother_Tounge_Url}" method="post" id="Preview_Mother_tounge_Url_JCO_Form" name="Preview_Mother_tounge_Url_JCO_Form" modelAttribute="id" target="result">
	<input type="hidden" name="mt_jco_id" id="mt_jco_id" value="0" />
</form:form>
	
<c:url value="Preview_Blood_Group_Url" var="Preview_Blood_Group_Url" />
<form:form action="${Preview_Blood_Group_Url}" method="post" id="Preview_Blood_Group_Url_JCO_Form" name="Preview_Blood_Group_Url_JCO_Form" modelAttribute="id" target="result">
	<input type="hidden" name="bg_jco_id" id="bg_jco_id" value="0" />
</form:form>
	
<c:url value="Preview_Height_Url" var="Preview_Height_Url" />
<form:form action="${Preview_Height_Url}" method="post" id="Preview_Height_Url_JCO_Form" name="Preview_Height_Url_JCO_Form" modelAttribute="id" target="result">
	<input type="hidden" name="h_jco_id" id="h_jco_id" value="0" />
</form:form>
	
<c:url value="Preview_Mother_Tounge_Url" var="Preview_Mother_Tounge_Url" />
<form:form action="${Preview_Mother_Tounge_Url}" method="post" id="Preview_Mother_tounge_Url_JCO_Form" name="Preview_Mother_tounge_Url_JCO_Form" modelAttribute="id" target="result">
	<input type="hidden" name="mt_jco_id" id="mt_jco_id" value="0" />
</form:form>
	
<c:url value="Preview_Aadhar_No_Url" var="Preview_Aadhar_No_Url" />
<form:form action="${Preview_Aadhar_No_Url}" method="post" id="Preview_Aadhar_No_Url_JCO_Form" name="Preview_Aadhar_No_Url_JCO_Form" modelAttribute="id" target="result">
	<input type="hidden" name="aa_jco_id" id="aa_jco_id" value="0" />
</form:form>

<c:url value="Preview_Pan_No_Url" var="Preview_Pan_No_Url" />
<form:form action="${Preview_Pan_No_Url}" method="post" id="Preview_Pan_No_Url_JCO_Form" name="Preview_Pan_No_Url_JCO_Form" modelAttribute="id" target="result">
	<input type="hidden" name="p_jco_id" id="p_jco_id" value="0" />
</form:form>
	
<c:url value="Preview_Class_Enroll_Url" var="Preview_Class_Enroll_Url" />
<form:form action="${Preview_Class_Enroll_Url}" method="post" id="Preview_Class_Enroll_Url_JCO_Form" name="Preview_Class_Enroll_Url_JCO_Form" modelAttribute="id" target="result">
	<input type="hidden" name="ce_jco_id" id="ce_jco_id" value="0" />
</form:form>

<c:url value="Preview_Rank_Url" var="Preview_Rank_Url" />
<form:form action="${Preview_Rank_Url}" method="post" id="Preview_Rank_Url_JCO_Form" name="Preview_Rank_Url_JCO_Form" modelAttribute="id" target="result">
	<input type="hidden" name="r_jco_id" id="r_jco_id" value="0" />
</form:form>

<c:url value="Preview_Date_Of_Enrollment_Url" var="Preview_Date_Of_Enrollment_Url" />
<form:form action="${Preview_Date_Of_Enrollment_Url}" method="post" id="Preview_Date_Of_Enrollment_Url_JCO_Form" name="Preview_Date_Of_Enrollment_Url_JCO_Form" modelAttribute="id" target="result">
	<input type="hidden" name="de_jco_id" id="de_jco_id" value="0" />
</form:form>

<c:url value="Preview_Date_Of_Attestation_Url" var="Preview_Date_Of_Attestation_Url" />
<form:form action="${Preview_Date_Of_Attestation_Url}" method="post" id="Preview_Date_Of_Attestation_Url_JCO_Form" name="Preview_Date_Of_Attestation_Url_JCO_Form" modelAttribute="id" target="result">
	<input type="hidden" name="da_jco_id" id="da_jco_id" value="0" />
</form:form>

<c:url value="Preview_Family_Details_Url" var="Preview_Family_Details_Url" />
<form:form action="${Preview_Family_Details_Url}" method="post" id="Preview_Family_Details_Url_JCO_Form" name="Preview_Family_Details_Url_JCO_Form" modelAttribute="id" target="result">
	<input type="hidden" name="fd_jco_id" id="fd_jco_id" value="0" />
</form:form>

<c:url value="Preview_Sibling_Details_Url" var="Preview_Sibling_Details_Url" />
<form:form action="${Preview_Sibling_Details_Url}" method="post" id="Preview_Sibling_Details_Url_JCO_Form" name="Preview_Sibling_Details_Url_JCO_Form" modelAttribute="id" target="result">
	<input type="hidden" name="sd_jco_id" id="sd_jco_id" value="0" />
</form:form>
	

<script type="text/javascript">
function divN(obj){
	var id = obj.id;
	var sib_id = $("#"+id).parent().parent().next().attr('id');
	var hasC=$("#"+sib_id).hasClass("show");
		$(".panel-collapse").removeClass("show");
		$('.droparrow').each(function(i, obj) {
			 $("#"+obj.id).attr("class", "droparrow collapsed");
			 });
		if (hasC) {	
		$("#"+id).addClass( " collapsed");		 
		}  else {				
			$("#"+sib_id).addClass("show");	
			$("#"+id).removeClass("collapsed");
	    }
		
		if (obj.id == "a_final") {
			if(!hasC){
				get_Gender();
			}
		}
		else if (obj.id == "b_final") {
			if(!hasC){
				get_Birth();
			}
		}
		else if (obj.id == "c_final") {
			if(!hasC){
				get_Birth_Address();
			}
		}
		else if (obj.id == "d_final") {
			if(!hasC){
				get_nationality();
			}
		}
		else if (obj.id == "e_final") {
			if(!hasC){
				get_Mother_Tongue();
			}
		}
		else if (obj.id == "f_final") {
			if(!hasC){
				get_Blood_Group();
			}
		}
		else if (obj.id == "g_final") {
			if(!hasC){
				get_height();
			}
		}
		else if (obj.id == "h_final") {
			if(!hasC){
				get_aadhar_no();
			}
		}
		else if (obj.id == "i_final") {
			if(!hasC){
				get_pan();
			}
		}
		else if (obj.id == "j_final") {
			if(!hasC){
				get_class_enrollment();
			}
		}
// 		else if (obj.id == "k_final") {
// 			if(!hasC){
// 				get_rank();
// 			}
// 		}
		else if (obj.id == "l_final") {
			if(!hasC){
				get_EnrollDate();
			}
		}
		else if (obj.id == "m_final") {
			if(!hasC){
				get_AttestationDate();
			}
		}
		else if (obj.id == "n_final") {
			if(!hasC){
				get_family_details();
			}
		}
		else if (obj.id == "o_final") {
			if(!hasC){
				getfamily_siblingDetails();
			}
		}
		
		}
</script>
<script>
function Pop_Up_History(a) {
	var x = screen.width/2 - 1100/2;
    var y = screen.height/2 - 900/2;
  	newWin = window.open("", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');
	window.onfocus = function () { 
	}
	jco_id = $("#jco_id").val();
	//army_no = $("#army_no").val();
	
			if(a == "gender"){
				$('#gen_jco_id').val(jco_id);
				//$('#gen_army_no').val(army_no);
				document.getElementById('Preview_Gender_JCO_Form').submit();
			}
		if(a == "date_of_birth"){
				$('#dob_jco_id').val(jco_id);
				//$('#gen_army_no').val(army_no);
				document.getElementById('Preview_Date_Of_Birth_Url_JCO_Form').submit();
			}
		if(a == "address_of_birth"){
				$('#add_jco_id').val(jco_id);
				//$('#gen_army_no').val(army_no);
				document.getElementById('Preview_Address_Of_Birth_Url_JCO_Form').submit();
			}
		
		if(a == "nationality"){
				$('#n_jco_id').val(jco_id);
				document.getElementById('Preview_Nationality_Url_JCO_Form').submit();
			}
		if(a == "blood_group"){
				$('#bg_jco_id').val(jco_id);
				document.getElementById('Preview_Blood_Group_Url_JCO_Form').submit();
			}
			if(a == "mother_tounge"){
				$('#mt_jco_id').val(jco_id);
				document.getElementById('Preview_Mother_tounge_Url_JCO_Form').submit();
			}
		if(a == "height"){
				$('#h_jco_id').val(jco_id);
				document.getElementById('Preview_Height_Url_JCO_Form').submit();
			}
		if(a == "aadhar_no"){
			$('#aa_jco_id').val(jco_id);
			document.getElementById('Preview_Aadhar_No_Url_JCO_Form').submit();
		}
		
		if(a == "pan_no"){
			$('#p_jco_id').val(jco_id);
			document.getElementById('Preview_Pan_No_Url_JCO_Form').submit();
		}
		
		if(a == "class_enroll"){
			$('#ce_jco_id').val(jco_id);
			document.getElementById('Preview_Class_Enroll_Url_JCO_Form').submit();
		}
		if(a == "rank"){
			$('#r_jco_id').val(jco_id);
			document.getElementById('Preview_Rank_Url_JCO_Form').submit();
		}
		
		if(a == "dt_of_enrollment"){
			$('#de_jco_id').val(jco_id);
			document.getElementById('Preview_Date_Of_Enrollment_Url_JCO_Form').submit();
		}
		
		if(a == "dt_of_attest"){
			$('#da_jco_id').val(jco_id);
			document.getElementById('Preview_Date_Of_Attestation_Url_JCO_Form').submit();
		}
		if(a == "family_details"){
			$('#fd_jco_id').val(jco_id);
			document.getElementById('Preview_Family_Details_Url_JCO_Form').submit();
		}
		if(a == "sibling_details"){
			$('#sd_jco_id').val(jco_id);
			document.getElementById('Preview_Sibling_Details_Url_JCO_Form').submit();
		}
		
}

function SetMin(){
	
	if ($("input#dob_date").val() != "") {
			
			var birth_dt = $("input#dob_date").val();
			
			setMinDate('enroll_dt', birth_dt);
			setMinDate('date_of_seniority', birth_dt);
			setMinDate('date_of_tos', birth_dt);
			setMinDate('date_of_attestation', birth_dt);
			setMaxDate('father_dob', birth_dt);
			setMaxDate('mother_dob', birth_dt);
		}

  }


function parent_disable1() {
    if(newWin && !newWin.closed)
            newWin.focus();
}

function personal_number()
{	
	if($("input#army_no").val()==""){
		alert("Please Enter Army No");
		$("input#army_no").focus();
		return false;
	}
	else{
		$("#div_update_data").show();
	}
	 var army_no = $("input#army_no").val();
	 $.post('GetArmyNoData?' + key + "=" + value,{ army_no : army_no },function(j) {
		 if(j!=""){
	    	$("#jco_id").val(j[0][0]);
	    	var jco_id =j[0][0]; 
	    	$.post('GetJcoUpdateCensusDataApprove?' + key + "=" + value,{ jco_id : jco_id },function(k) {
	    		 if(k.length > 0)
	    		 {
	    			 
	    			  $('#div_cda_acnt_no').show(); 
	    			  curr_marital_status=k[0].marital_status;  
	    			  $('#marital_event').val('0');
	    			  $('#marriage_div').show();
	    			  
	    			   $("#cadet_name").text(k[0].full_name);
	    			   if(k[0].date_of_birth==null){
	    		    		$("#dob").text("");    
	    		    	}
	    		    	else{
	    		    		 $("#dob").text(convertDateFormate(k[0].date_of_birth));
	    		    		 $("#dob_date").val(ParseDateColumncommission(k[0].date_of_birth));
	    		    		 SetMin();
	    		    	}
	    			 	
	    			 	$("#enroll_date").val(ParseDateColumncommission(k[0].enroll_dt));
	    			 	var e_date = ParseDateColumncommission(k[0].enroll_dt);
	    			 	if (e_date != "") {
							setMinDate('date_of_attestation', e_date);
							setMinDate('date_of_tos', e_date);
						}
	    			 	
	    		    	$("#p_rank").text(k[0].rank);
	    		    	if(k[0].rank=='RECTS')
	    		    		$("#attestationDiv").show();
	    		    	else
	    		    		$("#attestationDiv").hide();
	    		    	$("#hd_p_rank").val(k[0].rank);
	    		    	
	    		    	if(k[0].appointment==null){
	    		    		$("#p_app").text("");
	    		    	}
	    		    	else{
	    		    		$("#p_app").text(k[0].appointment);
	    		    	}
	    		    	if(k[0].date_appointment==null){
	    		    		$("#p_app_dt").text("");    
	    		    	}
	    		    	else{
	    		    		 $("#p_app_dt").text(convertDateFormate(k[0].date_appointment));  
	    		    	}
	    		    	if(k[0].dt_of_tos==null){
	    		    		$("#p_tos").text("");    
	    		    	}
	    		    	else{
	    		    		 $("#p_tos").text(convertDateFormate(k[0].dt_of_tos));  
	    		    	}
	    		    	$("#tos_date").val(ParseDateColumncommission(k[0].dt_of_tos));
	    		    	$("#app_sus_no").text(k[0].unit_sus_no);
	    		    	$("#p_id_no").text(k[0].id_card_no);
	    		    	$("#p_religion").text(k[0].religion_name);
	    		    	$("#app_parent_arm").text(k[0].parent_arm);
	    		    	$("#p_cmd").text(k[0].command);
	    		    	$('#inter_arm_record_office_suslb').text(k[0].record_office_sus );
	    		    	$('#inter_arm_record_office_unitlb').text(k[0].record_office );
	    		    	$('#inter_arm_old_arm_service').text(k[0].parent_arm );
	    		    	
	    		    	$("#old_trade").text(k[0].trade); 
	    		    	$("#old_class").text(k[0].class_pay);
	    		    	$("#old_group").text(k[0].pay_group);
	    		    	$("#old_dt_seniority").text(convertDateFormate(k[0].date_of_seniority));
	    				
	    		    	var dtTos = new Date(k[0].dt_of_tos2);
	    		    	var dtrank = new Date(k[0].date_of_rank);

	    		    	if(dtrank > dtTos){
		    		    	setMaxDate('enroll_dt', k[0].dt_of_tos2);
		    		    	$("#min_date_enroll").val(k[0].dt_of_tos2);
	    		    	}else{
	    		    		setMaxDate('enroll_dt', k[0].date_of_rank);
		    		    	$("#min_date_enroll").val(k[0].date_of_rank);

	    		    	}

	    			    
	    				
	    		    	if(k[0].parent_arm == "GORKHA" || k[0].parent_arm == "INFANTRY"){
	    		 			$("#reg_div").show();
	    		 		}
	    		 	  else
	    		 		  {
	    		 		 $("#reg_div").hide();
	    		 		  }
	    		    	if(k[0].regiment!=0){
	    		    		$('#inter_arm_regt_val').val(k[0].regiment);
	    		    		$('#p_regt').text($( "#inter_arm_regt_val option:selected" ).text());
	    		    		$('#inter_arm_old_regt').text($( "#inter_arm_regt_val option:selected" ).text());
	    		    	}
	    		    	else{
	    		    		$('#p_regt').text("");
	    		    	}
	    				
	    		    	var sus_no =k[0].unit_sus_no;
	    		    	getunit(sus_no);
	    		    	function getunit(val) {	
	    		            $.post("getTargetUnitNameList?"+key+"="+value, {
	    		            	sus_no : sus_no
	    		        }, function(j) {
	    		                
	    		                

	    		        }).done(function(j) {
	    		    				   var length = j.length-1;
	    		    	                   var enc = j[length].substring(0,16); 
	    		    	                   
	    		    	                   $("#app_unit_name").text(dec(enc,j[0])); 
	    		        }).fail(function(xhr, textStatus, errorThrown) {
	    		        });
	    		    } 
	    		 }
	   });
	 		$.ajaxSetup({
					async : false
				});
	 		
	    	 $.post('GetServingStatusJCO?' + key + "=" + value,{ jco_id : jco_id },function(k) {
	    		 if(k.length > 0)
	    		 {
	    		    	$("#p_serving_status").text(k[0][2]);
	    		 }
	   });
		 }
});
	 		
	 $("input#army_no").attr('readonly', true);
}



$("input#army_no").keypress(function(){
	var personel_no = this.value;
		 var susNoAuto=$("#army_no");
		  susNoAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        	type: 'POST',
			    	url: "getpersonnel_noListApproved_JCO?"+key+"="+value,
		        data: {personel_no:personel_no},
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
		        	  alert("Please Enter Approved Army No");
		        	  document.getElementById("army_no").value="";
		        	  susNoAuto.val("");	        	  
		        	  susNoAuto.focus();
		        	  return false;	             
		          }   	         
		      }, 
		      select: function( event, ui ) {
		      } 	     
		}); 			
});
</script>

<script>



/* ----------- Start Gender ---------------*/
function validate_Gender(){
	 if ($("input#g_authority").val().trim() == "") {
			alert("Please Enter Authority");
			$("input#g_authority").focus();
	 		return false;
		}
	 if ($("input#g_date_of_authority").val().trim() == "" || $("input#g_date_of_authority").val().trim() == "DD/MM/YYYY") {
			alert("Please Enter Date of Authority");
			$("input#g_date_of_authority").focus();
	 		return false;
		}
	 if ($("select#gender").val() == "0") {
			alert("Please Select Gender");
			$("select#gender").focus();
	 		return false;
		}
	 
	    var formvalues=$("#form_gender").serialize();
		var g_id=$("#g_id").val();	
		var jco_id=$("#jco_id").val();	
		formvalues+="&g_id="+g_id+"&jco_id="+jco_id;
		 $.post('Census_Gender_Action_JCO?' + key + "=" + value,formvalues, function(data){
		          if(data=="update")
		        	  alert("Data Updated Successfully");
		          else if(parseInt(data)>0){
		        	  $("#g_id").val(data);	
		        	  alert("Data Saved Successfully")
		          }
		          else
		        	  alert(data)
		 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		  		});
}
function get_Gender(){
	 var jco_id=$('#jco_id').val(); 
	  $.post('get_Gender1_JCO?' + key + "=" + value, {jco_id:jco_id}, function(j){
			var v=j.length;
			if(j!=""){
				$('#g_authority').val(j[0].authority);
				$('#g_date_of_authority').val(ParseDateColumncommission(j[0].date_of_authority));
				$('#gender').val(j[0].gender);
				$("#g_id").val(j[0].id);
			}
	  });
} 

/* ----------- Start Birth ---------------*/
function validate_Birth(){
	
	 if ($("input#b_authority").val().trim() == "") {
			alert("Please Enter Authority");
			$("input#b_authority").focus();
	 		return false;
		}
	 if ($("input#b_date_of_authority").val().trim() == "" || $("input#b_date_of_authority").val().trim() == "DD/MM/YYYY"){
			alert("Please Enter Date of Authority");
			$("input#b_date_of_authority").focus();
	 		return false;
		}
	 if($("input#date_of_birth").val().trim() == "" || $("input#date_of_birth").val().trim() == "DD/MM/YYYY"){
			alert("Please Select Date of Birth");
			$("input#date_of_birth").focus();
			return false;
		}
	    var formvalues=$("#form_Birth").serialize();
		var birth_id=$("#birth_id").val();	
		var jco_id=$("#jco_id").val();	
		formvalues+="&birth_id="+birth_id+"&jco_id="+jco_id;
		 $.post('Birth_Action_JCO?' + key + "=" + value,formvalues, function(data){
		          if(data=="update")
		        	  alert("Data Updated Successfully");
		          else if(parseInt(data)>0){
		        	  $("#birth_id").val(data);	
		        	  alert("Data Saved Successfully")
		          }
		          else
		        	  alert(data)
		 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		  		});
}
function get_Birth(){
	 var jco_id=$('#jco_id').val(); 
	  $.post('get_Birth1_JCO?' + key + "=" + value, {jco_id:jco_id}, function(j){
			var v=j.length;
			if(j!=""){
				$('#b_authority').val(j[0].authority);
				$('#b_date_of_authority').val(ParseDateColumncommission(j[0].date_of_authority));
				$('#date_of_birth').val(ParseDateColumncommission(j[0].date_of_birth));
				$("#birth_id").val(j[0].id);
			}
	  });
}

	

	 function calculate_age(obj){    
	     var from_d=$("#"+obj.id).val();
		 var from_d=$("#"+obj.id).val();
	     from_d = from_d.replaceAll("/", "-");
	     var from_d1=from_d.substring(6,10);
	     var from_d2=from_d.substring(3,5);
	     var from_d3=from_d.substring(0,2);
	     var frm_d = from_d3+"-"+from_d2+"-"+from_d1;         
	     var today=new Date();
	     var to_d3 = today.getDate();
	     var to_d2 = today.getMonth() + 1;
	     var to_d1 = today.getFullYear();        
	     var to_d0 = to_d3+"-"+to_d2+"-"+to_d1;
	     if(to_d2 > from_d2 && to_d3 > from_d3 || to_d3 == from_d3){
	     var year = to_d1 - from_d1        
	     var month = to_d2 - from_d2
	     }
	     if(to_d2 > from_d2 && to_d3 < from_d3){
	             var year = to_d1 - from_d1        
	             var month = to_d2 - from_d2 - 1
	             }
	     if(from_d2 > to_d2){
	             var year = to_d1 - from_d1 - 1
	             var month1 = from_d2 - to_d2
	             var month = 12 - month1
	     }
	     if(from_d2 == to_d2 && from_d3 > to_d3){
	             var year = to_d1 - from_d1 - 1
	             var days = from_d3 - to_d3
	     }
	     if(from_d2 == to_d2 && to_d3 > from_d3){
	             var year = to_d1 - from_d1 
	             var days =  to_d3 - from_d3 
	     }
	     if(from_d2 == to_d2 && to_d3 == from_d3){
	             var year = to_d1 - from_d1 
	             var days = 0
	     }
	     
	     if(from_d2 > to_d2 && from_d3 > to_d3){
	             var m = from_d2 - to_d2 
	             var n = m * 30
	             var m1 = from_d3 - to_d3 
	             var m2 = n+m1
	             var days =  m2
	     }
	     if(from_d2 > to_d2 && to_d3 > from_d3){
	             var m = from_d2 - to_d2 
	             var n = m * 30
	             var m1 =  to_d3 - from_d3 
	             var m2 = n+m1
	             var days =  m2
	     }
	     if(to_d2 > from_d2   && to_d3 > from_d3){
	             var m =  to_d2 - from_d2 
	             var n = m * 30
	             var m1 =  to_d3 - from_d3 
	             var m2 = n+m1        
	             var days =  m2 
	     }
	     if(to_d2 >  from_d2 && from_d3 > to_d3){
	             var m = to_d2 - from_d2   
	             var n = m * 30
	             var m1 = from_d3 - to_d3 
	             var m2 = n+m1
	             var days =  m2
	     }
	     if (month == undefined)
	      {
	             month = 0;
	      }
	     if(year < 17)
	     {
	    	 alert("Please enter age above 17");
	    	 $("#"+obj.id).val("");
	     }
	 }
	 
	
	 function date_of_com() {
		  $( "#date_of_commission" ).datepicker({  maxDate: new Date() });
		  alert("Future date are not allowed ");
	 }
	 function isNumber0_9Only(evt) {
			var charCode = (evt.which) ? evt.which : evt.keyCode;
			if(charCode != 46 && charCode > 31 && (charCode<48 || charCode>57)){
				return false;
			}else{
				if(charCode == 46){
					return false;
				}
			}
			return true;
		}
	 
	 /* ----------- Start Birth Address ---------------*/
	  function validate_changeaddress_details_save(){
	 	
		 if ($("input#addr_authority").val().trim() == "") {
	 		alert("Please Enter Authority");
	 		$("input#addr_authority").focus();
	 		return false;
	 	}  
	 	if ($("input#addr_date_authority").val().trim() == ""|| $("input#addr_date_authority").val().trim() == "DD/MM/YYYY") {
	 		alert("Please Enter Date of Authority");
	 		$("input#addr_date_authority").focus();
	 		return false;
	 	} 
	 	 if($("input#place_of_birth").val().trim() == "") {
				alert("Please Enter Place of Birth ");
				$("input#place_of_birth").focus();
				return false;
			}
			if ($("select#country_of_birth").val() == "0") {
				alert("Please Select Country of Birth");
				$("select#country_of_birth").focus();
				return false;
			}
			var text9 = $("#country_of_birth option:selected").text();
			
			if(text9.toUpperCase() == "OTHERS" && $("input#country_other").val().trim() == ""){
				alert("Please Enter Country Birth Other");
				$("input#country_other").focus();
				return false;
			}
			if ($("select#state_of_birth").val() == "0") {
				alert("Please Select State of Birth ");
				$("select#state_of_birth").focus();
				return false;
			}
			var text10 = $("#state_of_birth option:selected").text();
			
			if(text10.toUpperCase() == "OTHERS" && $("input#state_other").val().trim() == ""){
				alert("Please Enter State Birth Other");
				$("input#state_other").focus();
				return false;
			}
			if ($("select#district_of_birth").val() == "0") {
				alert("Please Select District of Birth ");
				$("select#district_of_birth").focus();
				return false;
			}
			var text11 = $("#district_of_birth option:selected").text();
			
			if(text11.toUpperCase() == "OTHERS" && $("input#district_other").val().trim() == ""){
				alert("Please Enter District Birth Other");
				$("input#district_other").focus();
				return false;
			}
	 	    var formvalues=$("#form_addr_details_form").serialize();
	 		var addr_ch_id=$("#addr_ch_id").val();	
	 		var jco_id=$("#jco_id").val();	
	 		formvalues+="&addr_ch_id="+addr_ch_id+"&jco_id="+jco_id;
	 		 $.post('Address_Birth_Action_JCO?' + key + "=" + value,formvalues, function(data){
	 		          if(data=="update")
	 		        	  alert("Data Updated Successfully");
	 		          else if(parseInt(data)>0){
	 		        	  $("#addr_ch_id").val(data);	
	 		        	  alert("Data Saved Successfully")
	 		          }
	 		          else
	 		        	  alert(data)
	 		 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	 		  		});
	 }
	  function get_Birth_Address(){
		 	 var jco_id=$('#jco_id').val(); 
		 	  $.post('get_Birth_address1_JCO?' + key + "=" + value, {jco_id:jco_id}, function(j){
		 			var v=j.length;
		 			if(j!=""){
		 				$('#addr_authority').val(j[0].authority);
		 				$('#addr_date_authority').val(ParseDateColumncommission(j[0].date_of_authority));
		 				$('#place_of_birth').val(j[0].place_of_birth);
		 				$('#country_of_birth').val(j[0].country_of_birth);
		 				fn_country_birth();
		 				$('#state_of_birth').val(j[0].state_of_birth);
		 				fn_state_birth();
		 				$('#district_of_birth').val(j[0].district_of_birth);
		 				fn_district_birth();
		 				var text6 = $("#country_of_birth option:selected").text();
						if(text6 == "OTHERS"){
							$("#country_other").val(j[0].country_other);
							$("#con_other_id").show();
						}
						else{
							$("#con_other_id").hide();
						}
						
						var text7 = $("#state_of_birth option:selected").text();
						if(text7 == "OTHERS"){
							$('#state_other').val(j[0].state_other);
							$("#sta_other_st").show();
						}
						else{
							$("#sta_other_st").hide();
						}
						
						var text8 = $("#district_of_birth option:selected").text();
						if(text8 == "OTHERS"){
							$("#district_other").val(j[0].district_other);
							$("#other_dist").show();
						}
						else{
							$("#other_dist").hide();
						}
						
		 				$("#addr_ch_id").val(j[0].id);
		 			}
		 	  });
		 }
	 /* ----------- End Birth Address ---------------*/
	 
	  /* ----------- Start Nationality ---------------*/
	  function validate_Nationality(){
		 
		 if ($("input#n_authority").val().trim() == "") {
		 		alert("Please Enter Authority");
		 		$("input#n_authority").focus();
		 		return false;
		 	}  
		 	if ($("input#n_date_of_authority").val().trim() == "" || $("input#n_date_of_authority").val().trim() == "DD/MM/YYYY") {
		 		alert("Please Enter Date of Authority");
		 		$("input#n_date_of_authority").focus();
		 		return false;
		 	} 
		 	if ($("select#nationality").val() == "0") {
				alert("Please Select Nationality");
				$("select#nationality").focus();
				return false;
			}
			var text12 = $("#nationality option:selected").text();
			
			if(text12.toUpperCase() == "OTHERS" && $("input#nationality_other").val().trim() == ""){
				alert("Please Enter Nationality Other");
				$("input#nationality_other").focus();
				return false;
			}
			var ny = $("#nationality option:selected").text();
	 	    var formvalues=$("#form_nationality").serialize();
	 		var n_id=$("#n_id").val();	
	 		var jco_id=$("#jco_id").val();	
	 		formvalues+="&n_id="+n_id+"&jco_id="+jco_id+"&ny="+ny;
	 		 $.post('Nationality_Action_JCO?' + key + "=" + value,formvalues, function(data){
	 		          if(data=="update")
	 		        	  alert("Data Updated Successfully");
	 		          else if(parseInt(data)>0){
	 		        	  $("#n_id").val(data);	
	 		        	  alert("Data Saved Successfully")
	 		          }
	 		          else
	 		        	  alert(data)
	 		 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	 		  		});
	 }
	 function get_nationality(){
	 	 var jco_id=$('#jco_id').val(); 
	 	  $.post('get_nationality1_JCO?' + key + "=" + value, {jco_id:jco_id}, function(j){
	 			var v=j.length;
	 			if(j!=""){
	 				$('#n_authority').val(j[0].authority);
	 				$('#n_date_of_authority').val(ParseDateColumncommission(j[0].date_of_authority));
	 				$('#nationality').val(j[0].nationality);
	 				
	 				var text6 = $("#nationality option:selected").text();
					if(text6 == "OTHERS"){
						$("#nationality_other").val(j[0].nationality_other);
						$("#NA_other_nati").show();
					}
					else{
						$("#NA_other_nati").hide();
					}
					
	 				$("#n_id").val(j[0].id);
	 			}
	 	  });
	 }
	 /* ----------- End Nationality ---------------*/
	 
	/* ----------- Start validate_Mother_Tongue ---------------*/
	 function validate_Mother_Tongue(){
		 
		   if ($("input#m_authority").val().trim() == "") {
		 		alert("Please Enter Authority");
		 		$("input#m_authority").focus();
		 		return false;
		 	}  
		 	if ($("input#m_date_of_authority").val().trim() == "" || $("input#m_date_of_authority").val().trim() == "DD/MM/YYYY") {
		 		alert("Please Enter Date of Authority");
		 		$("input#m_date_of_authority").focus();
		 		return false;
		 	} 
		 	if ($("select#mother_tounge").val() == "0") {
				alert("Please Select Mother Tongue");
				$("select#mother_tounge").focus();
				return false;
			}
			var text13 = $("#mother_tounge option:selected").text();
			
			if(text13.toUpperCase() == "OTHERS" && $("input#mother_tongue_other").val().trim() == ""){
				alert("Please Enter Mother Tongue Other");
				$("input#mother_tongue_other").focus();
				return false;
			}

      	  	var mt = $("#mother_tounge option:selected").text();
	 	    var formvalues=$("#form_Mother_Tongue").serialize();
	 	  
	 		var m_id=$("#m_id").val();	
	 		var jco_id=$("#jco_id").val();	
	 		formvalues+="&m_id="+m_id+"&jco_id="+jco_id+"&mt="+mt;
	 		 $.post('Mother_Tongue_Action_JCO?' + key + "=" + value,formvalues, function(data){
	 		          if(data=="update")
	 		        	  alert("Data Updated Successfully");
	 		          else if(parseInt(data)>0){
	 		        	  $("#m_id").val(data);	
	 		        	  alert("Data Saved Successfully")
	 		          }
	 		          else
	 		        	  alert(data)
	 		 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	 		  		});
	 }
	 function get_Mother_Tongue(){
	 	 var jco_id=$('#jco_id').val(); 
	 	  $.post('get_Mother_Tongue1_JCO?' + key + "=" + value, {jco_id:jco_id}, function(j){
	 			var v=j.length;
	 			if(j!=""){
	 				$('#m_authority').val(j[0].authority);
	 				$('#m_date_of_authority').val(ParseDateColumncommission(j[0].date_of_authority));
	 				$('#mother_tounge').val(j[0].mother_tounge);
	 				
	 				var text7 = $("#mother_tounge option:selected").text();
					if(text7 == "OTHERS"){
						$('#mother_tongue_other').val(j[0].mother_tongue_other);
						$("#motherton_other").show();
					}
					else{
						$("#motherton_other").hide();
					}
					
	 				$("#m_id").val(j[0].id);
	 			}
	 	  });
	 }
	 /* ----------- End validate_Mother_Tongue ---------------*/
	 
	 /* ----------- Start validate_Blood_Group ---------------*/
	 function validate_Blood_Group(){
		 
		 if ($("input#bg_authority").val().trim() == "") {
		 		alert("Please Enter Authority");
		 		$("input#bg_authority").focus();
		 		return false;
		 	}  
		 	if ($("input#bg_date_of_authority").val().trim() == "" || $("input#bg_date_of_authority").val().trim() == "DD/MM/YYYY") {
		 		alert("Please Enter Date of Authority");
		 		$("input#bg_date_of_authority").focus();
		 		return false;
		 	} 
		 	if ($("select#blood_group").val() == "0") {
				alert("Please Select Blood Group");
				$("select#blood_group").focus();
				return false;
			}
	 	    var formvalues=$("#form_Blood_Group").serialize();
	 		var bg_id=$("#bg_id").val();	
	 		var jco_id=$("#jco_id").val();	
	 		formvalues+="&bg_id="+bg_id+"&jco_id="+jco_id;
	 		 $.post('Blood_Group_Action_JCO?' + key + "=" + value,formvalues, function(data){
	 		          if(data=="update")
	 		        	  alert("Data Updated Successfully");
	 		          else if(parseInt(data)>0){
	 		        	  $("#bg_id").val(data);	
	 		        	  alert("Data Saved Successfully")
	 		          }
	 		          else
	 		        	  alert(data)
	 		 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	 		  		});
	 }
	 function get_Blood_Group(){
	 	 var jco_id=$('#jco_id').val(); 
	 	  $.post('get_Blood_Group1_JCO?' + key + "=" + value, {jco_id:jco_id}, function(j){
	 			var v=j.length;
	 			if(j!=""){
	 				$('#bg_authority').val(j[0].authority);
	 				$('#bg_date_of_authority').val(ParseDateColumncommission(j[0].date_of_authority));
	 				$('#blood_group').val(j[0].blood_group);
	 				$("#bg_id").val(j[0].id);
	 			}
	 	  });
	 }
	 /* ----------- End validate_Blood_Group ---------------*/
	 
	 
	 /* ----------- Start validate_Height ---------------*/
	 function validate_Height(){
		 
		 if ($("input#h_authority").val().trim() == "") {
		 		alert("Please Enter Authority");
		 		$("input#h_authority").focus();
		 		return false;
		 	}  
		 	if ($("input#h_date_of_authority").val().trim() == "" || $("input#h_date_of_authority").val().trim() == "DD/MM/YYYY") {
		 		alert("Please Enter Date of Authority");
		 		$("input#h_date_of_authority").focus();
		 		return false;
		 	} 
		 	if ($("select#height").val() == "0") {
				alert("Please Select Height");
				$("select#height").focus();
				return false;
	 		}
	 	    var formvalues=$("#form_Height").serialize();
	 		var h_id=$("#h_id").val();	
	 		var jco_id=$("#jco_id").val();	
	 		formvalues+="&h_id="+h_id+"&jco_id="+jco_id;
	 		 $.post('Height_Action_JCO?' + key + "=" + value,formvalues, function(data){
	 		          if(data=="update")
	 		        	  alert("Data Updated Successfully");
	 		          else if(parseInt(data)>0){
	 		        	  $("#h_id").val(data);	
	 		        	  alert("Data Saved Successfully")
	 		          }
	 		          else
	 		        	  alert(data)
	 		 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	 		  		});
	 }
	 function get_height(){
	 	 var jco_id=$('#jco_id').val(); 
	 	  $.post('get_Height1_JCO?' + key + "=" + value, {jco_id:jco_id}, function(j){
	 			var v=j.length;
	 			if(j!=""){
	 				$('#h_authority').val(j[0].authority);
	 				$('#h_date_of_authority').val(ParseDateColumncommission(j[0].date_of_authority));
	 				$('#height').val(j[0].height);
	 				$("#h_id").val(j[0].id);
	 			}
	 	  });
	 }
	 /* ----------- End validate_Height ---------------*/
	 
	 	 //janki
	 /* ----------- Start Class_Enrolment ---------------*/
	
	   function validate_Class_Enrolment(){
		 
		   if ($("input#ce_authority").val().trim() == "") {
				alert("Please Enter Authority");
				$("input#ce_authority").focus();
		 		return false;
			}
		  if ($("input#ce_date_of_authority").val().trim() == "" || $("input#ce_date_of_authority").val().trim() == "DD/MM/YYYY") {
				alert("Please Enter Date of Authority");
				$("input#ce_date_of_authority").focus();
		 		return false;
			}
		  if ($("select#class_enroll").val().trim() == "0") {
				alert("Please Select Class For Enrolment");
				$("select#class_enroll").focus();
		 		return false;
			}
		  if($("#class_enroll").val() == "10"){
				if ($("select#domicile").val() == "0") {
					alert("Please Select Domicile ");
					$("select#domicile").focus();
					return false;
			}
		  } 
	 	    var formvalues=$("#form_class_enrolment").serialize();
	 		var ce_id=$("#ce_id").val();	
	 		var jco_id=$("#jco_id").val();	
	 		formvalues+="&ce_id="+ce_id+"&jco_id="+jco_id;
	 		 $.post('Enroll_Class_Action_JCO?' + key + "=" + value,formvalues, function(data){
	 		          if(data=="update")
	 		        	  alert("Data Updated Successfully");
	 		          else if(parseInt(data)>0){
	 		        	  $("#ce_id").val(data);	
	 		        	  alert("Data Saved Successfully")
	 		          }
	 		          else
	 		        	  alert(data)
	 		 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	 		  		});
	 }
		 function get_class_enrollment(){
		 	 var jco_id=$('#jco_id').val(); 
		 	  $.post('get_Enroll_Class1_JCO?' + key + "=" + value, {jco_id:jco_id}, function(j){
		 			var v=j.length;
		 			if(j!=""){
		 				$('#ce_authority').val(j[0].authority);
		 				$('#ce_date_of_authority').val(ParseDateColumncommission(j[0].date_of_authority));
		 				$('#class_enroll').val(j[0].class_enroll);
		 				fn_domicile()
		 				$('#domicile').val(j[0].domicile);
		 				$("#ce_id").val(j[0].id);
		 			}
		 	  });
		 }
	  
		 function fn_domicile()
		 {
		 	var a = $("#class_enroll option:selected").val();
		 	if(a == "10") {

		 		  $("#gorkha_domicile").show();

		 	} 
		 	else {
		 		  $("#gorkha_domicile").hide();
		 		  $("#domicile").val("0");
		 	}
		 }
	 /* ----------- End Class_Enrolment ---------------*/
	  /* ----------- Start rank ---------------*/
// 	    function validate_rank(){
		 
// 		   if ($("input#rank_authority").val().trim() == "") {
// 				alert("Please Enter Authority");
// 				$("input#rank_authority").focus();
// 		 		return false;
// 			}
// 		  if ($("input#rank_date_of_authority").val().trim() == "" || $("input#rank_date_of_authority").val().trim() == "DD/MM/YYYY") {
// 				alert("Please Enter Date of Authority");
// 				$("input#rank_date_of_authority").focus();
// 		 		return false;
// 			}
// 		  if ($("select#rank").val() == "0") {
// 				alert("Please Select Rank ");
// 				$("select#rank").focus();
// 				return false;
// 			}
// 		  var rnk = $("#rank option:selected").val();
			
// 			if(rnk == "17"){
// 				if($("select#rank_intake").val()== "0") {
// 				alert("Please Select Rank Intake");
// 				$("select#rank_intake").focus();
// 				return false;
// 			  }
// 			}
// 	 	    var formvalues=$("#form_rank").serialize();
// 	 		var r_id=$("#r_id").val();	
// 	 		var jco_id=$("#jco_id").val();	
// 	 		formvalues+="&r_id="+r_id+"&jco_id="+jco_id;
// 	 		 $.post('Rank_Action_JCO?' + key + "=" + value,formvalues, function(data){
// 	 		          if(data=="update")
// 	 		        	  alert("Data Updated Successfully");
// 	 		          else if(parseInt(data)>0){
// 	 		        	  $("#r_id").val(data);	
// 	 		        	  alert("Data Saved Successfully")
// 	 		          }
// 	 		          else
// 	 		        	  alert(data)
// 	 		 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
// 	 		  		});
// 	 }
// 		 function get_rank(){
// 		 	 var jco_id=$('#jco_id').val(); 
// 		 	  $.post('get_Rank1_JCO?' + key + "=" + value, {jco_id:jco_id}, function(j){
// 		 			var v=j.length;
// 		 			if(j!=""){
// 		 				$('#rank_authority').val(j[0].authority);
// 		 				$('#rank_date_of_authority').val(ParseDateColumncommission(j[0].date_of_authority));
// 		 				$('#rank').val(j[0].rank);
// 		 				$("#r_id").val(j[0].id);
// 		 			}
// 		 	  });
// 		 }
		 /* ----------- End rank ---------------*/
		  /* ----------- Start Date of Enrollment ---------------*/
	   function validate_enroll_dt(){
			 
		   if ($("input#doe_authority").val().trim() == "") {
		 		alert("Please Enter Authority");
		 		$("input#doe_authority").focus();
		 		return false;
		 	}  
		 	if ($("input#doe_date_of_authority").val().trim() == "" || $("input#doe_date_of_authority").val().trim() == "DD/MM/YYYY") {
		 		alert("Please Enter Date of Authority");
		 		$("input#doe_date_of_authority").focus();
		 		return false;
		 	} 
// 		 	 if ($("input#enroll_dt").val() == "" || $("#enroll_dt").val() == "DD/MM/YYYY") {
// 					alert("Please Select Date of Enrollment");
// 					$("input#enroll_dt").focus();
// 					return false;
// 			 }else if ($("input#enroll_dt").val() == "" || $("#enroll_dt").val() == "DD/MM/YYYY" || ){
// 				 alert("Please Select Date of Enrollment Grater than Date of Rank and Date of TOS");
// 					$("input#enroll_dt").focus();
// 					return false;
// 			 }

		 	 
// 		 	 alert(new Date($("#min_date_enroll").val()) <= new Date($("#enroll_dt").val()));
	 	    var formvalues=$("#form_dt_of_enrollment").serialize();
	 		var doe_id=$("#doe_id").val();	
	 		var jco_id=$("#jco_id").val();	
	 		formvalues+="&doe_id="+doe_id+"&jco_id="+jco_id;
	 		 $.post('Enroll_Date_Action_JCO?' + key + "=" + value,formvalues, function(data){
	 			
	 			 if(data=="update")
	 		        	  alert("Data Updated Successfully");
	 		          else if(parseInt(data)>0){
	 		        	  $("#doe_id").val(data);	
	 		        	  alert("Data Saved Successfully")
	 		          }
	 		          else
	 		        	  alert(data)
	 		 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	 		  		});
	 }
		 function get_EnrollDate(){
		 	 var jco_id=$('#jco_id').val(); 
		 	  $.post('get_Enroll_Date1_JCO?' + key + "=" + value, {jco_id:jco_id}, function(j){
		 			var v=j.length;
		 			if(j!=""){
		 				$('#doe_authority').val(j[0].authority);
		 				$('#doe_date_of_authority').val(ParseDateColumncommission(j[0].date_of_authority));
		 				$('#enroll_dt').val(ParseDateColumncommission(j[0].date_of_enrollment));
		 				$("#doe_id").val(j[0].id);
		 			}
		 	  });
		 }
		 /* ----------- End Date of Enrollment ---------------*/
		   /* ----------- Start Date of Attestation ---------------*/
	   function validate_date_of_attestation(){
			 
		   if ($("input#doa_authority").val().trim() == "") {
		 		alert("Please Enter Authority");
		 		$("input#doa_authority").focus();
		 		return false;
		 	}  
		 	if ($("input#doa_date_of_authority").val().trim() == "" || $("input#doa_date_of_authority").val().trim() == "DD/MM/YYYY") {
		 		alert("Please Enter Date of Authority");
		 		$("input#doa_date_of_authority").focus();
		 		return false;
		 	} 
		 	 if ($("input#date_of_attestation").val() == "" || $("#date_of_attestation").val() == "DD/MM/YYYY") {
					alert("Please Select Date of Attestation");
					$("input#date_of_attestation").focus();
					return false;
		         }
	 	    var formvalues=$("#form_dt_of_Attestation").serialize();
	 		var doa_id=$("#doa_id").val();	
	 		var jco_id=$("#jco_id").val();	
	 		formvalues+="&doa_id="+doa_id+"&jco_id="+jco_id;
	 		 $.post('Attestation_Date_Action_JCO?' + key + "=" + value,formvalues, function(data){
	 		          if(data=="update")
	 		        	  alert("Data Updated Successfully");
	 		          else if(parseInt(data)>0){
	 		        	  $("#doa_id").val(data);	
	 		        	  alert("Data Saved Successfully")
	 		          }
	 		          else
	 		        	  alert(data)
	 		 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	 		  		});
	 }
		 function get_AttestationDate(){
		 	 var jco_id=$('#jco_id').val(); 
		 	  $.post('get_Attestation_Date1_JCO?' + key + "=" + value, {jco_id:jco_id}, function(j){
		 			var v=j.length;
		 			if(j!=""){
		 				$('#doa_authority').val(j[0].authority);
		 				$('#doa_date_of_authority').val(ParseDateColumncommission(j[0].date_of_authority));
		 				$('#date_of_attestation').val(ParseDateColumncommission(j[0].date_of_attestation));
		 				$("#doa_id").val(j[0].id);
		 			}
		 	  });
		 }
		 /* ----------- End Date of Attestation ---------------*/
		
	 //janki end
	 
	  /* ----------- Start validate_aadhar_no ---------------*/
	  function validate_aadhar_no(){
		 
		  if ($("input#aa_authority").val().trim() == "") {
		 		alert("Please Enter Authority");
		 		$("input#aa_authority").focus();
		 		return false;
		 	}  
		 	if ($("input#aa_date_of_authority").val().trim() == "" || $("input#aa_date_of_authority").val().trim() == "DD/MM/YYYY") {
		 		alert("Please Enter Date of Authority");
		 		$("input#aa_date_of_authority").focus();
		 		return false;
		 	} 
		 	var aadhar1 = $("input#adhar_number1").val();
		    var aadhar2 = $("input#adhar_number2").val();
		    var aadhar3 = $("input#adhar_number3").val();
			 
				if(aadhar1.trim()=="" || aadhar1.length < 4) {
					alert("Please Enter Aadhar Number");
					$("input#adhar_number1").focus();
					return false;
				}
				if(aadhar2.trim()=="" || aadhar2.length < 4) {
					alert("Please Enter Aadhar Number");
					$("input#adhar_number2").focus();
					return false;
				}
				if(aadhar3.trim()=="" || aadhar3.length < 4) {
					alert("Please Enter Aadhar Number");
					$("input#adhar_number3").focus();
					return false;
				}

	 	    var formvalues=$("#form_aadhar_no").serialize();
	 		var aa_id=$("#aa_id").val();	
	 		var jco_id=$("#jco_id").val();	
	 		formvalues+="&aa_id="+aa_id+"&jco_id="+jco_id;
	 		 $.post('Aadhar_Action_JCO?' + key + "=" + value,formvalues, function(data){
	 		          if(data=="update")
	 		        	  alert("Data Updated Successfully");
	 		          else if(parseInt(data)>0){
	 		        	  $("#aa_id").val(data);	
	 		        	  alert("Data Saved Successfully")
	 		          }
	 		          else
	 		        	  alert(data)
	 		 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	 		  		});
	 }
	 function get_aadhar_no(){
	 	 var jco_id=$('#jco_id').val(); 
	 	  $.post('get_AadharNo1_JCO?' + key + "=" + value, {jco_id:jco_id}, function(j){
	 			var v=j.length;
	 			if(j!=""){
	 				$('#aa_authority').val(j[0].authority);
	 				$('#aa_date_of_authority').val(ParseDateColumncommission(j[0].date_of_authority));
	 				var aadhar = j[0].aadhar_no +"";
	 				
	 				$('#adhar_number1').val(aadhar.substring(0, 4));
	 				$('#adhar_number2').val(aadhar.substring(4, 8));
	 				$('#adhar_number3').val(aadhar.substring(8, 12));
	 				$("#aa_id").val(j[0].id);
	 			}
	 	  });
	 }
	 /* ----------- End validate_aadhar_no ---------------*/
	 
	  /* ----------- Start validate_pan_no ---------------*/
	function validate_pan_no(){
		 
		 if ($("input#pan_authority").val().trim() == "") {
		 		alert("Please Enter Authority");
		 		$("input#pan_authority").focus();
		 		return false;
		 	}  
		 	if ($("input#pan_date_of_authority").val().trim() == "" || $("input#pan_date_of_authority").val().trim() == "DD/MM/YYYY") {
		 		alert("Please Enter Date of Authority");
		 		$("input#pan_date_of_authority").focus();
		 		return false;
		 	} 
		 	var pan_no=$('#pan_no').val();		
			if(pan_no.trim()=="") {
			
				alert("Please Enter PAN Number");
				$('#pan_no').focus();
				return false
			}
	 	    var formvalues=$("#form_pan_no").serialize();
	 		var pan_id=$("#pan_id").val();	
	 		var jco_id=$("#jco_id").val();	
	 		formvalues+="&pan_id="+pan_id+"&jco_id="+jco_id;
	 		 $.post('Pan_no_Action_JCO?' + key + "=" + value,formvalues, function(data){
	 		          if(data=="update")
	 		        	  alert("Data Updated Successfully");
	 		          else if(parseInt(data)>0){
	 		        	  $("#pan_id").val(data);	
	 		        	  alert("Data Saved Successfully")
	 		          }
	 		          else
	 		        	  alert(data)
	 		 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	 		  		});
	 }
	 function get_pan(){
	 	 var jco_id=$('#jco_id').val(); 
	 	  $.post('get_pan_no1_JCO?' + key + "=" + value, {jco_id:jco_id}, function(j){
	 			var v=j.length;
	 			if(j!=""){
	 				$('#pan_authority').val(j[0].authority);
	 				$('#pan_date_of_authority').val(ParseDateColumncommission(j[0].date_of_authority));
	 				$('#pan_no').val(j[0].pan_no);
	 				$("#pan_id").val(j[0].id);
	 			}
	 	  });
	 }
	  /* ----------- End validate_pan_no ---------------*/
	  /* ----------- Start validate_family_details ---------------*/
	 function validate_family_details(){
		  
		 var dt_of_birth = $("#date_of_birth").val();
		 dt_of_birth1 =dt_of_birth.substring(6,10);
	    

		
		var fa_dob  = $("#father_dob").val();
		father_dob1 =fa_dob.substring(6,10);
		
		var mo_dob  = $("#mother_dob").val();
		mother_dob1 =mo_dob.substring(6,10);
		
	  
	 if ($("input#fd_authority").val().trim() == "") {
	 		alert("Please Enter Authority");
	 		$("input#fd_authority").focus();
	 		return false;
	 	}  
	 	if ($("input#fd_date_of_authority").val().trim() == "" || $("input#fd_date_of_authority").val().trim() == "DD/MM/YYYY") {
	 		alert("Please Enter Date of Authority");
	 		$("input#fd_date_of_authority").focus();
	 		return false;
	 	}
	  
	 	if($("input#father_name").val().trim() == "") {
			alert("Please Enter Father's Name ");
			$("input#father_name").focus();
			return false;
		}
	
	      if ($("input#father_dob").val() == "" || $("#father_dob").val() == "DD/MM/YYYY") {
			alert("Please Select Father's Date of Birth");
			$("input#father_dob").focus();
			return false;
		}
           
	  		if ($("input#father_place_birth").val() == "") {
	  			alert("Please Enter Father's Place of Birth");
	  			$("input#father_place_birth").focus();
	  			return false;
	  		}
		
	  		var fservice_radio = $("input[name='father_proff_radio']:checked").val();
	  		if(fservice_radio == "yes") {
	  			if($("#father_service").val()=="0"){
	  				alert("Please Select Father Service");
	  				$("#father_service").focus();
	  				return false;
	  			}
	  			if($("#father_service").val()=="9"){
	  				if($("#father_other").val().trim()==''){
	  					alert("Please Enter Other Service");
	  					$("#father_other").focus();
	  					return false;
	  				}
	  			}
	  			if($("#father_service").val()=="6" || $("#father_service").val()=="7" || $("#father_service").val()=="8"){
	  				if($("#father_personal_no").val().trim()==''){
	  					alert("Please Enter Father Personal No");
	  					$("#father_personal_no").focus();
	  					return false;
	  				}
	  			}
	  			if($("#father_personal_no").val()!=''){
	  				if($("#father_personal_no").val().length < 7 || $("#father_personal_no").val().length > 9){
	  				alert("Please Enter Valid Father Personal No");
	  				$("#father_personal_no").focus();
	  				return false;
	  				}
	  			}
	  		}
			else{
				if($("#father_profession").val() == "0") {
					alert("Please Select Father's Profession ");
					$("#father_profession").focus();
					return false;
				}
				
		 		var f_proff = $("#father_profession  option:selected").text();
				if(f_proff == "OTHERS"){
					if($("#father_proffother").val().trim()==""){
						alert("Please Enter Father's Other Profession ");
						$("#father_proffother").focus();
						return false;
					}
				} 
			}
if($("input#mother_name").val().trim() == "") {
alert("Please Enter Mother's Name");
$("input#mother_name").focus();
return false;
}

if ($("input#mother_dob").val() == "" || $("#mother_dob").val() == "DD/MM/YYYY") {
	alert("Please Select Mother's Date of Birth");
	$("input#mother_dob").focus();
	return false;
}
   
if ($("input#mother_place_birth").val().trim() == "") {
	alert("Please Enter Mother's Place of Birth");
	$("input#mother_place_birth").focus();
	return false;
}

var mservice_radio = $("input[name='mother_proff_radio']:checked").val();
if(mservice_radio == "yes") {
	if($("#mother_service").val()=="0"){
		alert("please Select Mother Service");
		$("#mother_service").focus();
		return false;
	}
	if($("#mother_service").val()=="4"){
		if($("#mother_other").val()==""){
			alert("please Enter Other Service");
			$("#mother_other").focus();
			return false;
		}
	}

	if($("#mother_service option:selected").text() == "OTHER"){
		if($("#mother_other").val().trim() ==""){
			alert("please Enter Mother Other");
			$("#mother_other").focus();
			return false;
		}
	
}
/* 	if($("#mother_service option:selected").text() != "OTHER"){
		if($("#mother_personal_no").val().trim() ==""){
			alert("please Enter Mother Personal No");
			$("#mother_personal_no").focus();
			return false;
		}
	
} */
	if($("#mother_service").val()=="6" || $("#mother_service").val()=="7" || $("#mother_service").val()=="8"){
		if($("#mother_personal_no").val().trim()==""){
			alert("Please Enter Mother Personal No");
			$("#mother_personal_no").focus();
			return false;
		}
		if($("#mother_personal_no").val().trim()!=''){
			if($("#mother_personal_no").val().length < 7 || $("#mother_personal_no").val().length > 9){
			alert("Please Enter Valid Mother Personal No");
			$("#mother_personal_no").focus();
			return false;
			}
		}
	}
	
}
else{
	if($("#mother_profession").val() == "0") {
		alert("Please Select Mother's Profession ");
		$("#mother_profession").focus();
		return false;
	}
	var m_proff = $("#mother_profession option:selected").text();
	if(m_proff == "OTHERS"){
		if($("#mother_proffother").val().trim() ==""){
			alert("Please Enter Mother's Profession Other ");
			$("#mother_proffother").focus();
			return false;
		}
	}
} 

   if($("input#mother_name").val().trim() == "") {
	alert("Please Enter Mother's Name");
	$("input#mother_name").focus();
	return false;
   }

   if ($("input#mother_dob").val() == "" || $("#mother_dob").val() == "DD/MM/YYYY") {
		alert("Please Select Mother's Date of Birth");
		$("input#mother_dob").focus();
		return false;
	}
	   
	if ($("input#mother_place_birth").val().trim() == "") {
		alert("Please Enter Mother's Place of Birth");
		$("input#mother_place_birth").focus();
		return false;
	}
	
	var mservice_radio = $("input[name='mother_proff_radio']:checked").val();
	if(mservice_radio == "yes") {
		if($("#mother_service").val()=="0"){
			alert("please Select Mother Service");
			$("#mother_service").focus();
			return false;
		}
		if($("#mother_service").val()=="4"){
			if($("#mother_other").val()==""){
				alert("please Enter Other Service");
				$("#mother_other").focus();
				return false;
			}
		}
	
		if($("#mother_service").val()=="6" || $("#mother_service").val()=="7" || $("#mother_service").val()=="8"){
			
			if($("#mother_personal_no").val().trim() ==""){
				alert("please Enter Mother Personal No");
				$("#mother_personal_no").focus();
				return false;
			}
		
	}
	}
	else{
		if($("#mother_profession").val() == "0") {
			alert("Please Select Mother's Profession ");
			$("#mother_profession").focus();
			return false;
		}
		var m_proff = $("#mother_profession option:selected").text();
		if(m_proff == "OTHERS"){
			if($("#mother_proffother").val().trim() ==""){
				alert("Please Enter Mother's Other Profession ");
				$("#mother_proffother").focus();
				return false;
			}
		}
	} 
	 	    var formvalues=$("#form_family_details").serialize();
	 		var fd_id=$("#fd_id").val();	
	 		var jco_id=$("#jco_id").val();	
	 		formvalues+="&fd_id="+fd_id+"&jco_id="+jco_id;
	 		 $.post('Family_Details_Action_JCO?' + key + "=" + value,formvalues, function(data){
	 		          if(data=="update")
	 		        	  alert("Data Updated Successfully");
	 		          else if(parseInt(data)>0){
	 		        	  $("#fd_id").val(data);	
	 		        	  alert("Data Saved Successfully")
	 		          }
	 		          else
	 		        	  alert(data)
	 		 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	 		  		});
	 }
	 function get_family_details(){
	 	 var jco_id=$('#jco_id').val(); 
	 	  $.post('get_Family_details1_JCO?' + key + "=" + value, {jco_id:jco_id}, function(j){
	 			var v=j.length;
	 			if(j!=""){
	 				$('#fd_authority').val(j[0].authority);
	 				$('#fd_date_of_authority').val(ParseDateColumncommission(j[0].date_of_authority));
	 				$('#father_name').val(j[0].father_name);
	 				$('#father_dob').val(ParseDateColumncommission(j[0].father_dob));
	 				$('#father_place_birth').val(j[0].father_place_birth);
	 				$('#father_profession').val(j[0].father_profession);
	 				$('#father_service').val(j[0].father_service);
	 				$('#father_personal_no').val(j[0].father_personal_no);
	 				$('#mother_name').val(j[0].mother_name);
	 				$('#mother_dob').val(ParseDateColumncommission(j[0].mother_dob));
	 				$('#mother_place_birth').val(j[0].mother_place_birth);
	 				$('#mother_profession').val(j[0].mother_profession);
	 				$('#mother_service').val(j[0].mother_service);
	 				$('#mother_personal_no').val(j[0].mother_personal_no);
	 				
	 				if(j[0].father_profession == "0"){
						$('#father_proff_radio1').prop('checked', true);
						$('#father_proff_radio1').text("Yes")
						$('#father_inserviceDiv').show();
						$("#father_proffDiv").hide();
					}
					else{
						$('#father_proff_radio2').prop('checked', true);
						$('#father_proff_radio2').text("No")
						$('#father_inserviceDiv').hide();
						$("#father_proffDiv").show();
					}
	 				
	 				
	 				if(j[0].mother_profession == "0"){
						$('#mother_proff_radio1').prop('checked', true);
						$('#mother_proff_radio1').text("Yes")
						$('#mother_inserviceDiv').show();
						$("#mother_proffDiv").hide();
						
					}
					else{
						$('#mother_proff_radio2').prop('checked', true);
						$('#mother_proff_radio2').text("No")
						$('#mother_inserviceDiv').hide();
						$("#mother_proffDiv").show();
					}
	 				
	 				var text4 = $("#father_service option:selected").text();
					if(text4 == "OTHER"){
						
						$("#father_other").val(j[0].father_other);
						$("#father_otherDiv").show();
						$("#father_personalDiv").hide();
					}
					else{
						$("#father_otherDiv").hide();
						$("#father_personalDiv").show();
					} 
					
					var text5 = $("#mother_service option:selected").text();
					
					if(text5 == "OTHER"){
						$("#mother_other").val(j[0].mother_other);
						$("#mother_otherDiv").show();
						$("#mother_personalDiv").hide();
					}
					else{
						$("#mother_otherDiv").hide();
						$("#mother_personalDiv").show();
					} 
					ServiceOtherFn('father_otherDiv','father_personalDiv',document.getElementById('father_service'));
					ServiceOtherFn('mother_otherDiv','mother_personalDiv',document.getElementById('mother_service'));
				
	 				
	 				var text6 = $("#father_profession option:selected").text();
					if(text6 == "OTHERS"){
						$("#father_proffother").val(j[0].father_proffother);
						$("#father_proffotherDiv").show();
					}
					else{
						$("#father_proffotherDiv").hide();
					}
					
					var text8 = $("#mother_profession option:selected").text();
					if(text8 == "OTHERS"){
						$("#mother_proffother").val(j[0].mother_proffother);
						//$("#mother_otherproffDiv").show();
					}
					else{
						$("#mother_otherproffDiv").hide();
					}
					
					
	 				$("#fd_id").val(j[0].id);
	 			}
	 	  });
	 }
	  /* ----------- End validate_family_details ---------------*/
</script>
<script>
  
jQuery(function($){
	 datepicketDate('g_date_of_authority');  
	 datepicketDate('b_date_of_authority'); 
	 datepicketDate('date_of_birth');  
	 datepicketDate('addr_date_authority'); 
	 datepicketDate('n_date_of_authority'); 
	 datepicketDate('m_date_of_authority'); 
	 datepicketDate('bg_date_of_authority'); 
	 datepicketDate('h_date_of_authority');
	 datepicketDate('aa_date_of_authority');
	 datepicketDate('pan_date_of_authority'); 
	 datepicketDate('ce_date_of_authority');
	 datepicketDate('rank_date_of_authority');
	 datepicketDate('doe_date_of_authority');
	 datepicketDate('enroll_dt');
	 datepicketDate('doa_date_of_authority');
	 datepicketDate('date_of_attestation');
	 datepicketDate('fd_date_of_authority');
	 datepicketDate('father_dob');
	 datepicketDate('mother_dob');
	 datepicketDate('ds_date_of_authority');
	 datepicketDate('sib_date_of_birth1');
	 });
</script>

<script>
/* copy from jcosor */
 function fn_country_birth() {
	var text = $("#country_of_birth option:selected").text();
	$("#country_of_birth_hid").val(text);
		if(text.toUpperCase() == "OTHERS"){
			$("#country_birth_other_hid").show();
		}
		else{
			$("#country_birth_other_hid").hide();
		}
		var contry_id = $('select#country_of_birth').val();
		$.post("getStateListFormcon1?" + key + "=" + value, {
			contry_id: contry_id
		}).done(function(j) {
			var options = '<option   value="0">' + "--Select--" + '</option>';
			for(var i = 0; i < j.length; i++) {
				options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
			}
			$("select#state_of_birth").html(options);
			fn_state_birth();
		}).fail(function(xhr, textStatus, errorThrown) {});
	}
 function fn_state_birth() {
		var text = $("#state_of_birth option:selected").text();
		$("#state_of_birth_hid").val(text);
			if(text.toUpperCase() == "OTHERS"){
				$("#state_birth_other_hid").show();
			}
			else{
				$("#state_birth_other_hid").hide();
			}
			var state_id = $('select#state_of_birth').val();
			$.post("getDistrictListFormState1?" + key + "=" + value, {
				state_id: state_id
			}).done(function(j) {
				var options = '<option   value="0">' + "--Select--" + '</option>';
				for(var i = 0; i < j.length; i++) {
					options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
				}
				$("select#district_of_birth").html(options);
				fn_district_birth();
			}).fail(function(xhr, textStatus, errorThrown) {});
		}
		 function fn_district_birth() {
			 var text = $("#district_of_birth option:selected").text();
			 $("#district_of_birth_hid").val(text);
				if(text.toUpperCase() == "OTHERS"){
					$("#district_birth_other_hid").show();
				}
				else{
					$("#district_birth_other_hid").hide();
				}
				
		 	}
	function father_proffFn() {
		var a = $('input:radio[name=father_proff_radio]:checked').val();
		if(a.toLowerCase() == "yes") {
			$("#father_inserviceDiv").show();
			$("#father_proffDiv").hide();
		} else {
			$("#father_proffDiv").show();
			$("#father_inserviceDiv").hide();
		}
	}

	function mother_proffFn() {
		var a = $('input:radio[name=mother_proff_radio]:checked').val();
		if(a.toLowerCase() == "yes") {
			$("#mother_inserviceDiv").show();
			$("#mother_proffDiv").hide();
		} else {
			$("#mother_proffDiv").show();
			$("#mother_inserviceDiv").hide();
		}
	}
	function ServiceOtherFn(divId, perId, obj) {
		if(obj.value == 9) {
			$("#" + divId).show();
			$("#" + perId).hide();
		} else {
			$("#" + divId).hide();
			$("#" + perId).show();
		}
	}
	function movetoNext(current, nextFieldID) {  
		if (current.value.length >= current.maxLength) {  
			document.getElementById(nextFieldID).focus();  
		}  
	}

</script>
<script>
/* ----------- Start Sib ---------------*/

sib = 1;
sib_srno = 1;

function family_add_fn(q) {
	if($('#family_add' + q).length) {
		$("#family_add" + q).hide();
	}
	if(q != 0) sib_srno += 1;
	sib = q + 1;
	$("table#family_table").append('<tr id="tr_sibling' + sib + '">' + '<td class="sib_srno">' + sib_srno + '</td>' + '<td> <input type="sib_name' + sib + '" id="sib_name' + sib + '" onkeypress="return onlyAlphabets(event);" oninput="this.value = this.value.toUpperCase()" maxlength="50" class="form-control-sm form-control"   >'
		
		
		+ '<td>'
		
		+ ' <input type="text" name="sib_date_of_birth' + sib + '" id="sib_date_of_birth' + sib + '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" ' + '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" ' + '	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">' + '</td>'
		+ '<td> <select name="sib_relationship' + sib + '" id="sib_relationship' + sib + '" class="form-control-sm form-control"   >' + '<option value="0">--Select--</option>' + '<c:forEach var="item" items="${getFamily_siblings}" varStatus="num">' + '<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' + '</c:forEach></select></td>'
		+ '<td><input type="text" ' + '	id="sib_adhar_number' + sib + '" name="sib_adhar_number' + sib + '" ' + '	class="form-control autocomplete" maxlength="14"  ' + '	onkeypress="return isAadhar(this,event); " autocomplete="off"></td> ' 
		+ '<td> ' + '	<input type="text" id="sib_pan_no' + sib + '" name="sib_pan_no' + sib + '" ' + '	class="form-control autocomplete" autocomplete="off" ' + '	onchange=" isPAN(this); " ' + '	oninput="this.value = this.value.toUpperCase()" ' + '	maxlength="10"> ' + '	</td> ' 
		+ '<td style="display:none;"><input type="text" id="sib_ch_id' + sib + '" name="sib_ch_id' + sib + '"   value="0" class="form-control autocomplete" autocomplete="off" ></td>' 

		+ '<td><input type="checkbox" ' + '	id="ser-ex' + sib + '" name="ser-ex' + sib + '" ' + '	value="Yes"  ' + '	onclick="siblingCB(' + sib + ');"></td> '
		+ '<td> <select name="sibling_service' + sib + '" id="sibling_service' + sib + '" class="form-control-sm form-control"  onchange = "otherfunction(' + sib + ')" >' + '<option value="0">--Select--</option>' + '<c:forEach var="item" items="${getExservicemenList}" varStatus="num">' + '<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' + '</c:forEach></select></td>' 
		+ '<td><input type="text" ' + '	 maxlength="15" id="sibling_personal_no' + sib + '" name="sibling_personal_no' + sib + '" ' + '	class="form-control" autocomplete="off" oninput="this.value = this.value.toUpperCase()" ></td> '
		+ '<td><input type="text" ' + '	id="other_sibling_ser' + sib + '" name="other_sibling_ser' + sib + '" ' + '	class="form-control" onkeypress="return onlyAlphaNumeric(event);" oninput="this.value = this.value.toUpperCase()" autocomplete="off" ></td>'
		+ '<td class="hide-action"><a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "family_save' + sib + '" onclick="family_save_fn(' + sib + ');" ><i class="fa fa-save"></i></a>' + '<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "family_add' + sib + '" onclick="family_add_fn(' + sib + ');" ><i class="fa fa-plus"></i></a>' 
		+ '<a style="display:none;" class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "family_remove' + sib + '" onclick="family_remove_fn(' + sib + ');"><i class="fa fa-trash"></i></a>' + '</td></tr>');
	datepicketDate('sib_date_of_birth' + sib);  
		
	siblingCB(sib);
	otherfunction(sib);
}

function family_remove_fn(R) {
	var rc = confirm("Are You Sure! You Want To Delete");
	if(rc) {
		var sib_ch_id = $('#sib_ch_id' + R).val();
		$.post('Sibling_JCOdelete_action?' + key + "=" + value, {
			sib_ch_id: sib_ch_id
		}, function(data) {
			if(data == '1') {
				$("tr#tr_sibling" + R).remove();
				if(R == sib) {
					R = R - 1;
					var temp = true;
					for(i = R; i >= 1; i--) {
						if($('#family_add' + i).length) {
							$("#family_add" + i).show();
							temp = false;
							sib = i;
							break;
						}
					}
					if(temp) {
						family_add_fn(0);
					}
				}
				$('.sib_srno').each(function(i, obj) {
					obj.innerHTML = i + 1;
					sib_srno = i + 1;
				});
				alert("Data Deleted Successfully");
			} else {
				alert("Data not Deleted ");
			}
		}).fail(function(xhr, textStatus, errorThrown) {
			alert("fail to fetch")
		});
	}
}

function family_save_fn(ps) {
	
	var sib_name = $('#sib_name' + ps).val();
	var sib_date_of_birth = $('#sib_date_of_birth' + ps).val();
	var sib_relationship = $('#sib_relationship' + ps).val();
	var sib_ch_id = $('#sib_ch_id' + ps).val();
	var ds_authority = $('#ds_authority').val();
	var date_of_authority = $('#ds_date_of_authority').val();
	
	var jco_id = $('#jco_id').val();
	//var com_id = $("#comm_id").val();
	var sib_ser = $("select#sibling_service"+ps).val();
	var sib_pers_no = $("#sibling_personal_no"+ps).val();
	if($('#ser-ex' + ps).is(":checked")){
		var ser_ex = "Yes";
	}else{
		var ser_ex = "";
	}
	var other_sib_ser =  $("#other_sibling_ser"+ps).val();
	
	if(sib_name.trim() == "") {
		alert("Please Enter Sibling Name");
		$("input#sib_name" + ps).focus();
		return false;
	}
	if(sib_date_of_birth == "DD/MM/YYYY") {
		alert("Please Enter Sibling Date of Birth");
		$("input#sib_date_of_birth" + ps).focus();
		return false;
	}
	if(sib_date_of_birth.trim() == "") {
		alert("Please Enter Sibling Date of Birth");
		$("input#sib_date_of_birth" + ps).focus();
		return false;
	}
	if(sib_relationship == "0") {
		alert("Please Select Sibling Relationship");
		$("select#sib_relationship" + ps).focus();
		return false;
	}
	if($('#sib_adhar_number' + ps).val().trim() == "" || $('#sib_adhar_number' + ps).val().length < 14) {
		alert("Please Enter Aadhar No");
		$("input#sib_adhar_number"+ps).focus();
		return false;
	}
	var sibling = $( "#sibling_service"+ps+" option:selected" ).text();
	if($('#ser-ex' + ps).is(":checked")){
		if(sib_ser == 0){
			alert("Please Select Sibling Service");
			$("select#sibling_service"+ps).focus();
			return false;
		}
		if(sib_ser == 1){
			if(sib_pers_no.trim() == ""){
				alert("Please Enter Sibling Personal No");
				$("#sibling_personal_no"+ps).focus();
				return false;
			}
		}
		if(sib_pers_no.trim()!=''){
			if(sib_pers_no.trim().length < 5 || sib_pers_no.trim().length > 15){
			alert("Please Enter Valid Sibling Personal No");
			$("#sibling_personal_no").focus();
			return false;
			}
		}
			
		if (sibling.toUpperCase() == "OTHER"){
			if($('#other_sibling_ser' + ps).val().trim() == ""){
				alert("Please Enter Other Sibling Service");
			$('#other_sibling_ser' + ps).focus();
			return false;
			}
			}
		
	}
	else{
		sibling="";
	}
	var adhar_number = $('#sib_adhar_number' + ps).val().split('-').join('');
	var pan_no = $('#sib_pan_no' + ps).val();
	var currentdate = new Date();
	if(getformatedDate(sib_date_of_birth) > currentdate) {
		alert("Enter Valid Date of Birth Date");
		$("input#sib_date_of_birth" + ps).focus();
		return false;
	}
	$.post('Sibling_Action_JCO?' + key + "=" + value, {
		sib_name: sib_name,
		sib_date_of_birth: sib_date_of_birth,
		adhar_number: adhar_number,
		pan_no: pan_no,
		sib_relationship: sib_relationship,
		sib_ch_id: sib_ch_id,
		jco_id: jco_id,
		ds_authority:ds_authority,
		date_of_authority:date_of_authority,
		//com_id: com_id,
		sib_ser:sib_ser,
		sib_pers_no:sib_pers_no,
		ser_ex:ser_ex,
		other_sib_ser:other_sib_ser,sibling:sibling
	}, function(data) {
		if(data.error == null) {
			if(data.sibId != null) {
				$('#sib_ch_id' + ps).val(data.sibId);
				$("#family_add" + ps).show();
				$("#family_remove" + ps).show();
				alert(data.saved);
			} else {
				alert(data.updated);
			}
		} else alert(data.error);
	}).fail(function(xhr, textStatus, errorThrown) {
		alert("fail to fetch")
	});
}

function getfamily_siblingDetails() {
	var jco_id = $('#jco_id').val();
	var i = 0;
	$.post('get_sib1_JCO?' + key + "=" + value, {
		jco_id: jco_id
	}, function(j) {
		var v = j.length;
		if(v != 0) {
			$('#family_sibtbody').empty();
			for(i; i < v; i++) {
				x = i + 1;
				var pan = "";
				if(j[i].pan_no != null) {
					pan = j[i].pan_no;
				}
				$("table#family_table").append('<tr id="tr_sibling' + x + '">' + '<td class="sib_srno">' + x + '</td>' + '<td> <input type="sib_name' + x + '" id="sib_name' + x + '"  value="' + j[i].name + '"  onkeypress="return onlyAlphabets(event);" oninput="this.value = this.value.toUpperCase()" class="form-control-sm form-control"   >' + '<td> '
					
					+ ' <input type="text" name="sib_date_of_birth' + x + '" id="sib_date_of_birth' + x + '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" ' + '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" ' + '	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="' + ParseDateColumncommission(j[i].date_of_birth) + '">' + '</td>' 
					+ '<td> <select name="sib_relationship' + x + '" id="sib_relationship' + x + '" class="form-control-sm form-control"   >' + '<option value="0">--Select--</option>' + '<c:forEach var="item" items="${getFamily_siblings}" varStatus="num">' + '<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' + '</c:forEach></select></td>' + '<td><input type="text" ' + '	id="sib_adhar_number' + x + '" name="sib_adhar_number' + x + '" ' + '	class="form-control autocomplete" maxlength="14"  ' + '	onkeypress="return isAadhar(this,event); " autocomplete="off" value="' + j[i].aadhar_no + '" ></td> ' 
					+ '<td> ' + '	<input type="text" id="sib_pan_no' + x + '" name="sib_pan_no' + x + '" ' + '	class="form-control autocomplete" autocomplete="off" ' + '	onchange=" isPAN(this); " ' + '	oninput="this.value = this.value.toUpperCase()" ' + '	maxlength="10" value="' + pan + '" > ' + '	</td> ' 
					+ '<td style="display:none;"><input type="text" id="sib_ch_id' + x + '" name="sib_ch_id' + x + '"   value="' + j[i].id + '"  class="form-control autocomplete" autocomplete="off" ></td>'
					+ '<td><input type="checkbox" ' + '	id="ser-ex' + x + '" name="ser-ex' + x + '" ' + '	value="Yes"  ' + '	onclick="siblingCB(' + x + ');"></td> '
					+ '<td> <select name="sibling_service' + x + '" id="sibling_service' + x + '" class="form-control-sm form-control" onchange = "otherfunction(' + x + ')"   >' + '<option value="0">--Select--</option>' + '<c:forEach var="item" items="${getExservicemenList}" varStatus="num">' + '<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' + '</c:forEach></select></td>' + '<td><input type="text" ' + '  maxlength="15" oninput="this.value = this.value.toUpperCase()"	id="sibling_personal_no' + x + '" name="sibling_personal_no' + x + '" ' + '	class="form-control" autocomplete="off" value="' + j[i].sibling_personal_no + '" ></td> '
					+ '<td><input type="text" ' + '	id="other_sibling_ser' + x + '" name="other_sibling_ser' + x + '" ' + '	class="form-control" onkeypress="return onlyAlphaNumeric(event);" oninput="this.value = this.value.toUpperCase()" autocomplete="off" ></td>'
					+ '<td class="hide-action"><a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "family_save' + x + '" onclick="family_save_fn(' + x + ');" ><i class="fa fa-save"></i></a>' + '<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "family_add' + x + '" onclick="family_add_fn(' + x + ');" ><i class="fa fa-plus"></i></a>' + '<a  class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "family_remove' + x + '" onclick="family_remove_fn(' + x + ');"><i class="fa fa-trash"></i></a>' + '</td></tr>');
				$('#sib_relationship' + x).val(j[i].relationship);
				$('#sibling_service' + x).val(j[i].sibling_service);
				$('#other_sibling_ser' + x).val(j[i].other_sibling_ser);
				if(j[i].if_sibling_ser =="Yes"){
					$("input[type=checkbox][name='ser-ex"+x+"']").prop("checked",true);

				}
				siblingCB(x);
				
				otherfunction(x);
				datepicketDate('sib_date_of_birth' + x);
				isAadhar(document.getElementById('sib_adhar_number' + x))
				$('#ds_authority').val(j[0].authority);
				$('#ds_date_of_authority').val(ParseDateColumncommission(j[0].date_of_authority));
					
			}
			sib = v;
			sib_srno = v;
			$('#family_add' + sib).show();
		}
	});
}
function siblingCB(a) {
	if ($('#ser-ex'+a).is(":checked"))
	{
		$('#sibling_service' + a).attr('readonly', false);
		$('#sibling_personal_no' + a).attr('readonly', false);
	}
	else{

		$('#sibling_service' + a).attr('readonly', true);
		$('#sibling_personal_no' + a).attr('readonly', true);
		$('#other_sibling_ser' + a).attr('readonly', true);
		$('select#sibling_service' + a).val(0);
		$('#sibling_personal_no' + a).val("");
		$('#other_sibling_ser' + a).val("");
	}
}

function otherfunction(k){
	var spouse = $( "#spouse_service"+k+" option:selected" ).text();
	var sibling = $( "#sibling_service"+k+" option:selected" ).text();
	
	if (spouse.toUpperCase() == "OTHER"){
		$('#other_spouse_ser' + k).attr('readonly', false);
		$('#spouse_personal_no' + k).attr('readonly', true);
		$('#spouse_personal_no' + k).val('');
	}
	if (spouse.toUpperCase() != "OTHER" && spouse != "--Select--"){
		$('#other_spouse_ser' + k).attr('readonly', true);
		$('#spouse_personal_no' + k).attr('readonly', false);
		$('#other_spouse_ser' + k).val('');
	}
	if(sibling.toUpperCase() == "OTHER"){
		$('#other_sibling_ser' + k).attr('readonly', false);	
		$('#sibling_personal_no' + k).attr('readonly', true);
		$('#sibling_personal_no' + k).val('');
	}
	if(sibling.toUpperCase() != "OTHER" && sibling != "--Select--"){
		$('#other_sibling_ser' + k).attr('readonly', true);
		$('#sibling_personal_no' + k).attr('readonly', false);
		$('#other_sibling_ser' + k).val('');
	}
}
/* ----------- End Sib ---------------*/
</script>
<script>
$(document).ready(function() {
	
	$("#army_noV").val('${army_no6}');
	$("#statusV").val('${status6}');
	$("#rankV").val('${rank6}');
	$("#jco_idV").val('${jco_id6}');
	$("#unit_nameV").val('${unit_name6}');
	$("#unit_sus_noV").val('${unit_sus_no6}');
	 	
	siblingCB(1);

	$('#army_no').val('${personnel_no_edit_JCO}');
	
	if ('${personnel_no_edit_JCO}' != ""){
		
		var army_no = '${personnel_no_edit_JCO}';
		 $.post('GetArmyNoData?' + key + "=" + value,{ army_no : army_no },function(j) {
			 if(j != ""){
		    	$("#jco_id").val(j[0][0]);
		    	var jco_id =j[0][0]; 
		    	$.post('GetJcoUpdateCensusDataApprove?' + key + "=" + value,{ jco_id : jco_id },function(k) {
		    		 if(k.length > 0)
		    		 {
		    			
		    			  $('#div_cda_acnt_no').show(); 
		    			  curr_marital_status=k[0].marital_status;  
		    			  $('#marital_event').val('0');
		    			  $('#marriage_div').show();
		    			  
		    			   $("#cadet_name").text(k[0].full_name);
		    			   if(k[0].date_of_birth==null){
		    		    		$("#dob").text("");    
		    		    	}
		    		    	else{
		    		    		 $("#dob").text(convertDateFormate(k[0].date_of_birth));
		    		    		 $("#dob_date").val(ParseDateColumncommission(k[0].date_of_birth));
		    		    		 SetMin();
		    		    	}
		    			 	
		    			 	$("#enroll_date").val(ParseDateColumncommission(k[0].enroll_dt));
		    			 	var e_date = ParseDateColumncommission(k[0].enroll_dt);
		    			 	if (e_date != "") {
								setMinDate('date_of_attestation', e_date);
								setMinDate('date_of_tos', e_date);
							}

		    		    	$("#p_rank").text(k[0].rank);
		    		    	if(k[0].rank=='RECTS')
		    		    		$("#attestationDiv").show();
		    		    	else
		    		    		$("#attestationDiv").hide();
		    		    	$("#hd_p_rank").val(k[0].rank);
		    		    	$("#p_app").text(k[0].appointment);
		    		    	if(k[0].appointment==null){
		    		    		$("#p_app").text("");
		    		    	}
		    		    	else{
		    		    		$("#p_app").text(k[0].appointment);
		    		    	}
		    		    	if(k[0].date_appointment==null){
		    		    		$("#p_app_dt").text("");    
		    		    	}
		    		    	else{
		    		    		 $("#p_app_dt").text(convertDateFormate(k[0].date_appointment));  
		    		    	}
		    		    	if(k[0].dt_of_tos==null){
		    		    		$("#p_tos").text("");    
		    		    	}
		    		    	else{
		    		    		 $("#p_tos").text(convertDateFormate(k[0].dt_of_tos));  
		    		    	}
		    		    	$("#tos_date").val(ParseDateColumncommission(k[0].dt_of_tos));
		    		    	$("#app_sus_no").text(k[0].unit_sus_no);
		    		    	$("#p_id_no").text(k[0].id_card_no);
		    		    	$("#p_religion").text(k[0].religion_name);
		    		    	$("#app_parent_arm").text(k[0].parent_arm);
		    		    	$("#p_cmd").text(k[0].command);
		    		    	$('#inter_arm_record_office_suslb').text(k[0].record_office_sus );
		    		    	$('#inter_arm_record_office_unitlb').text(k[0].record_office );
		    		    	$('#inter_arm_old_arm_service').text(k[0].parent_arm );
		    		    	
		    		    	$("#old_trade").text(k[0].trade); 
		    		    	$("#old_class").text(k[0].class_pay);
		    		    	$("#old_group").text(k[0].pay_group);
		    		    	$("#old_dt_seniority").text(convertDateFormate(k[0].date_of_seniority));

		    		    	var dtTos = new Date(k[0].dt_of_tos2);
		    		    	var dtrank = new Date(k[0].date_of_rank);

		    		    	if(dtrank > dtTos){
			    		    	setMaxDate('enroll_dt', k[0].dt_of_tos2);
			    		    	$("#min_date_enroll").val(k[0].dt_of_tos2);
		    		    	}else{
		    		    		setMaxDate('enroll_dt', k[0].date_of_rank);
			    		    	$("#min_date_enroll").val(k[0].date_of_rank);

		    		    	}
		    				
		    		    	if(k[0].parent_arm == "GORKHA" || k[0].parent_arm == "INFANTRY"){
		    		 			$("#reg_div").show();
		    		 		}
		    		 	  else
		    		 		  {
		    		 		 $("#reg_div").hide();
		    		 		  }
		    		    	if(k[0].regiment!=0){
		    		    		$('#inter_arm_regt_val').val(k[0].regiment);
		    		    		$('#p_regt').text($( "#inter_arm_regt_val option:selected" ).text());
		    		    		$('#inter_arm_old_regt').text($( "#inter_arm_regt_val option:selected" ).text());
		    		    	}
		    		    	else{
		    		    		$('#p_regt').text("");
		    		    	}
		    				
		    		    	var sus_no =k[0].unit_sus_no;
		    		    	getunit(sus_no);
		    		    	function getunit(val) {	
		    		            $.post("getTargetUnitNameList?"+key+"="+value, {
		    		            	sus_no : sus_no
		    		        }, function(j) {
		    		                
		    		                

		    		        }).done(function(j) {
		    		    				   var length = j.length-1;
		    		    	                   var enc = j[length].substring(0,16); 
		    		    	                   
		    		    	                   $("#app_unit_name").text(dec(enc,j[0])); 
		    		        }).fail(function(xhr, textStatus, errorThrown) {
		    		        });
		    		    } 
		    		 }
		   });
		 		$.ajaxSetup({
						async : false
					});
		 		
		    	 $.post('GetServingStatusJCO?' + key + "=" + value,{ jco_id : jco_id },function(k) {
		    		 if(k.length > 0)
		    		 {
		    		    	$("#p_serving_status").text(k[0][2]);
		    		 }
		   });
			 }
	});
		 $("#div_update_data").show();          
		 $("#process_btn").hide();
		 
	}
	else{
		$("#div_update_data").hide();
		$("#process_btn").show();
		
	}
	 try{
	        if(window.location.href.includes("?"))
	         {
	             var url = window.location.href.split("?")[0];
	            window.history.pushState({}, document.title, url);
	        }
	    }
	    catch (e) {
	        // TODO: handle exception
	    }
}); 



function fn_father_other() {
	 var text = $("#father_profession option:selected").text();
	
		if(text.toUpperCase() == "OTHERS"){
			$("#father_proffotherDiv").show();
		}
		else{
			$("#father_proffotherDiv").hide();
		}
		 
	}
function fn_mother_other() {
	 var text = $("#mother_profession option:selected").text();
	
		if(text.toUpperCase() == "OTHERS"){
			$("#mother_otherproffDiv").show();
		}
		else{
			$("#mother_otherproffDiv").hide();
		}
		 
	}
	
	
function rank_intake_jco() 
{
	var a = $("select#rank option:selected").val();

	if(a == "17") {
		$("#intake").show();
		
	}
	else
		{
		$("#intake").hide();
		}
	
}


$("#unit_sus_no").keyup(function(){
	var sus_no = this.value;
	var susNoAuto=$("#unit_sus_no");

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
			 $.post("getTargetUnitNameList?"+key+"="+value, {
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

// unit name
 $("input#unit_name").keypress(function(){
		var unit_name = this.value;
			 var susNoAuto=$("#unit_name");
			  susNoAuto.autocomplete({
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
			        	  document.getElementById("unit_name").value="";
			        	  susNoAuto.val("");	        	  
			        	  susNoAuto.focus();
			        	  return false;	             
			          }   	         
			      }, 
			      select: function( event, ui ) {
			    	 var unit_name = ui.item.value;
			    
				            $.post("getTargetSUSFromUNITNAME?"+key+"="+value,{target_unit_name:unit_name}, function(data) {
				            }).done(function(data) {
				            	var length = data.length-1;
					        	var enc = data[length].substring(0,16);
					        	$("#unit_sus_no").val(dec(enc,data[0]));
				            }).fail(function(xhr, textStatus, errorThrown) {
				            });
			      } 	     
			}); 			
	});

	
</script>