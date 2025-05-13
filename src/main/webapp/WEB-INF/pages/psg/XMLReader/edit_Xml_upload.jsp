<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>


<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>


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
.sticky + .subcontent {
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
								<b>UPDATE COMMISSIONING DETAILS </b>
							</h4>
						</div>
						<div class="col-md-12"
							style="padding-top: 5px; padding-left: 250px;">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong style="color: red;">* </strong>Personal No</label>
									</div>
									<div class="col-md-8">
										<input type="text" id="personnel_no" name="personnel_no" class="form-control autocomplete" autocomplete="off">
										<input type="hidden" id="comm_id" name="comm_id" class="form-control autocomplete" autocomplete="off">
									   <input type="hidden" id="census_id" name="census_id" value="0" class="form-control autocomplete" autocomplete="off">
										<input type="hidden" id="status" name="status" value="0" class="form-control autocomplete" autocomplete="off">
									</div>
								
								</div>
							</div>
						</div>
							<div class="card-footer" align="right" id="back_bt_div" style="">
							<a href="xml_upload_logs" class="btn btn-primary btn-sm" >Back</a> 
<!-- 							<input type="button" class="btn btn-info btn-sm" onclick="Cancel();" value="Back" maxlength="100">    -->
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
							<a class="droparrow collapsed" data-toggle="collapse" data-parent="#accordion4" href="#" id="app_final" onclick="divN(this)"><b>RECORD</b></a>
						</h4>
					</div>
					<div id="collapse1app" class="panel-collapse collapse">
						<div class="card-body card-block"> <br>
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
												<input type="hidden" class=" form-control-label" id="dob_date">
												<input type="hidden" class=" form-control" id="comm_date">
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
												<input type="hidden" id="hd_p_rank" name="hd_p_rank" class="form-control autocomplete" autocomplete="off">
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
												<input type="hidden" class=" form-control-label" id="tos_date">
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
												<label class=" form-control-label"> Arm/Service</label>
											</div>
											<div class="col-md-8">
												<label class=" form-control-label" id="app_parent_arm"></label>
											</div>
										</div>
									</div>
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
									<div class="col-md-12">
									<div class="col-md-3" style="display: none;" id="reg_div">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong style="color: red;"></strong> Regiment</label>
											</div>
											<div class="col-md-8">
												<label id="p_regt" class=" form-control-label"></label> 
													<select name="regt" id="inter_arm_regt_val" class="form-control" style="display: none;">
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
	</div>

<!-- START rank -->
<c:if test="${ChangeOfrank != 0}">
 <form id="form_ChangeOfRank">
		<div class="card">
			<div class="panel-group" id="accordion4">
				<div class="panel panel-default" id="a_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title red" id="a_div5">
								<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion4" href="#" id="r1_final" onclick="divN(this)"
								><b>CHANGE IN RANK</b></a>
						</h4>
					</div>
					<div id="collapse1a" class="panel-collapse collapse">
			                <div class="card-body card-block"><br>
			                <input type="button" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('rank');" />
							<div class="row">
					 <div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> <strong style="color: red;">* </strong>Authority  </label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="r_authority" name="r_authority" class="form-control autocomplete" 
						                   autocomplete="off" onkeyup="return specialcharecter(this)" maxlength="100"> 
						                </div>
						            </div>
	              				</div>	            				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> <strong style="color: red;">* </strong>Date of Authority </label>
						                </div>
						                <div class="col-md-8">
						                    <input type="hidden" id="ch_r_id" name="ch_r_id" value="0" class="form-control" />  
						                  <input type="text" name="rdate_of_authority" id="rdate_of_authority" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
						                </div>
						            </div>
	              				</div>	              				
	              			</div>
	              		 <div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> <strong style="color: red;">* </strong> Rank Type </label>
						                </div>
						                <div class="col-md-8">
										          <select name="rank_type" id="rank_type" class="form-control"   >
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${getOFFTypeofRankList}" varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
														</c:forEach>
											      </select> 
						                </div>
						            </div>
	              				</div>	            				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> <strong style="color: red;">* </strong>Rank </label>
						                </div>
						                <div class="col-md-8">
						                          <select name="rank" id="rank" class="form-control"   >
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${getTypeofRankList}" varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
														</c:forEach>
											      </select> 
						                </div>
						            </div>
	              				</div>	              				
	              			</div>
	              			<div class="col-md-12">
	              			<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> <strong style="color: red;">* </strong> Date of Rank </label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" name="date_of_rank" id="date_of_rank" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
						                </div>
						            </div>
	              				</div>
	              			</div>
							</div>
						  <div class="card-footer" align="center" class="form-control">
								<input type="button" class="btn btn-primary btn-sm" value="Update  Rank" onclick="validate_rank();"> 	
			            	</div> 
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
</c:if>
<!-- END Rank  -->	 


<c:if test="${ChangeOfName!=0 }">
 <form id="form_change_of_name">
				<div class="card">
			<div class="panel-group" id="accordion5">
				<div class="panel panel-default" id="b_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title red" id="b_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion5" href="#" id="b_final" onclick="divN(this)"
								><b>CHANGE IN NAME</b></a>
						</h4>
					</div>
					<div id="collapse1b" class="panel-collapse collapse">
						  <div class="card-body card-block">
			            <br>
			                           
			             <input type="button" name="save" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('Name');" /> 
					<div class="row">
						<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> <strong style="color: red;">* </strong>Authority </label>
						                </div>
						                <div class="col-md-8">
						               <input type="text" id="na_authority" name="authority" class="form-control autocomplete" 
						               autocomplete="off" maxlength="100" onkeyup="return specialcharecter(this)"> 
						               <input type="hidden" id="name_id" name="name_id" value="0" class="form-control autocomplete" autocomplete="off">
						                </div>
						            </div>
	              				</div>	            				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> <strong style="color: red;">* </strong>Date of Authority </label>
						                </div>
						               <div class="col-md-8">
						                <input type="text" name="date_of_authority" id="na_date_of_authority" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 83%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
						               </div>
						            </div>
	              				</div>	              				
	              			</div>
	              			<div class="col-md-12">	              						              				            				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> <strong style="color: red;">* </strong>Name </label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="name" name="name" class="form-control autocomplete" autocomplete="off" 
						                   maxlength="50" onkeypress="return onlyAlphabets(event);"
											oninput="this.value = this.value.toUpperCase()"> 
				                	  </div>
						            </div>
	              				</div>	 
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> <strong style="color: red;">* </strong>Date of Change in Name</label>
						                </div>
						               <div class="col-md-8">
						                <input type="text" name="change_in_name_date" id="change_in_name_date" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 83%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
						               </div>
						            </div>
	              				</div>
	              				
	              				             				
	              			</div>	
							</div>
					    <div class="card-footer" align="center" class="form-control">
		              		<input type="button" class="btn btn-primary btn-sm" value=" Update" onclick="validate_Change_of_name_save_fn();">
			           
			            </div> 	
						</div>
					<!-- 	  <div class="card-footer" align="left" class="form-control">
								<p><u><b>Note:</b></u> Name should be as per Commission Offr Information. </p> 	
			            	</div>  -->
					</div>
				</div>
			</div>
		</div>
		</form>
</c:if>


<c:if test="${ChangeOfAppointment!=0 }">
<form id="form_change_of_appointment">
		<div class="card">
			<div class="panel-group" id="accordion41">
				<div class="panel panel-default" id="c_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title red" id="c_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion41" href="#" id="c_final" onclick="divN(this)"
								><b>CHANGE IN APPOINTMENT</b></a>
						</h4>
					</div>
					<div id="collapse1c" class="panel-collapse collapse">
					     <div class="card-body card-block">
			           
								   <input type="button" name="save" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('Appointment');" /> 
								
							<div class="row">
						<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label for="Authority"> <strong style="color: red;">* </strong>Authority</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="appt_authority" name="appt_authority" class="form-control"  
						                   autocomplete="off" maxlength="100" onkeyup="return specialcharecter(this)">	
								           <input type="hidden" id="appoint_id" name="appoint_id" value="0" class="form-control"  autocomplete="off" >
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label for="Date Autho"><strong style="color: red;">* </strong> Date of Authority </label>
						                </div>
						                <div class="col-md-8">
						                  <input type="text" name="appt_date_of_authority" id="appt_date_of_authority" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 83%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
						                </div>
						            </div>
	              				</div>
	              			</div>
	              				    
	              			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Appointment </label>
						                </div>
						                <div class="col-md-8">
										   <select  id="appointment" name="appointment" class="form-control" onchange="appointment_att();" >
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${getTypeofAppointementList}" varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
														</c:forEach>
											</select> 
						                </div>
						            </div>
	              				</div>
	              			    <div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Date of Appointment </label>
						                </div>
						                <div class="col-md-8">
						                <input type="text" name="date_of_appointment" id="date_of_appointment" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 83%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
						                </div>
						            </div>
	              				</div>
	              			</div>
	              			 <div class="card-body" style="display: none;" id = "Note">
		          		<p align="left" style="color:#ff0730fc; font-weight: bold;" >Note : Please Enter X-List SUS No Or  X-List Unit Name Or  X-List Location.  </p>					            		           
	        		</div>
	              			<div class="col-md-12" style="display: none;" id = "att_dapu">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong
											style="color: red;">* </strong> X-List SUS No</label>
						                </div>
						                <div class="col-md-8">
						                  <input type="text" id="parent_sus_no" name="parent_sus_no" class="form-control autocomplete" autocomplete="off" maxlength="8" onkeypress="return AvoidSpace(event)"  onkeyup="return specialcharecter(this)" >  
						                 
						                </div>
						            </div>
	              				</div>
	              					<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> X-List Unit Name</label>
						                </div>
						                <div class="col-md-8">
						              		  <input type="text" id="parent_unit" name="parent_unit" class="form-control autocomplete" autocomplete="off" maxlength="50" onkeyup="return specialcharecter(this)">
						                </div>
						            </div>
	              				</div>
	              			</div>
	              				<div class="col-md-12" style="display: none;" id = "att_dapu1">	
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> X-List Location</label>
						                </div>
						                <div class="col-md-8">
						              		  <input type="text" id="x_list_loc" name="x_list_loc" class="form-control autocomplete" autocomplete="off" maxlength="50" onkeyup="return specialcharecter(this)">
						                </div>
						            </div>
	              				</div>
	              			</div>
							</div>
						<div class="card-footer" align="center" class="form-control">
		              		 <input type="button" name="save" class="btn btn-primary btn-sm" value="Update" onclick=" validate_appointment_save();" />		              
			            </div>  
						</div>
					</div>
				</div>
			</div>
		</div>
		</form>
</c:if>


<c:if test="${ChangeOfMedCat!=0 }">

<form id="madical_category_form">
		<div class="card">
			<div class="panel-group" id="accordion32">
				<div class="panel panel-default" id="s_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title red" id="s_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion32" href="#" id="s_final" onclick="divN(this)"
								><b>CHANGE IN MEDICAL</b></a>
						</h4>
					</div>
					<div id="collapse1s" class="panel-collapse collapse">
						<div class="card-body card-block">
						<br><input type="button" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('Change_Medicle');" />
							<div class="row">
										<div class="col-md-12">
				<div class="col-md-6">
					<div class="row form-group">
						<div class="col-md-6">
							<label class=" form-control-label"><strong
								style="color: red;">* </strong>Authority</label>
						</div>
						<div class="col-md-6">
<!-- 					 <input type="hidden" id="med_cat_ch_id" name="med_cat_ch_id" value="0" class="form-control autocomplete" autocomplete="off" > 							                      -->
						
							<input type="text" id="madical_authority"  maxlength="100"
								name="madical_authority" class="form-control autocomplete" onkeyup="return specialcharecter(this)"
								autocomplete="off">
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="row form-group">
						<div class="col-md-6">
							<label class=" form-control-label"><strong
								style="color: red;">* </strong>Date of Authority</label>
						</div>
						<div class="col-md-6">

								
			 								 <input type="text" name="madical_date_of_authority" id="madical_date_of_authority" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 83%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY" >
						              

						</div>
					</div>
				</div>
			</div>
			<input type="hidden" id="mad_classification_count" name="mad_classification_count" value="NIL">
				<div class="col-md-12">
					<div class="row form-group">
						<div class="col-md-12" style="font-size: 15px; margin:10px; ">
								<input type="checkbox" id="check_1bx" name="check_1bx"
									onclick="oncheck_1bx()" value="1BX"> <label for="text-input"
									class=" form-control-label"
									style="margin-left: 10px;"><strong> SHAPE 1BX </strong></label>
							</div>
					</div>
						<input type="hidden" id="shape_1bx_id" name="shape_1bx_id"  value="0" class="form-control autocomplete" autocomplete="off" >
				</div>
					<div class="col-md-12" id="shape1bxdiv" style="display:none;">
				
			<div class="col-md-4">
					<div class="row form-group">
						<div class="col-md-4">
							<label class=" form-control-label"><strong
								style="color: red;">* </strong>From Date</label>
						</div>
						<div class="col-md-8">
<%-- 						<input type="date" id="1bx_from_date" name="1bx_from_date" max="${date_without_time}" class="form-control autocomplete" autocomplete="off"> --%>

			 											 <input type="text" name="1bx_from_date" id="1bx_from_date" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 80%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY" >
						              
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="row form-group">
						<div class="col-md-4">
							<label class=" form-control-label"><strong
								style="color: red;">* </strong>To Date</label>
						</div>
						<div class="col-md-8">
<!-- 							<input type="date" id="1bx_to_date" name="1bx_to_date" class="form-control autocomplete" autocomplete="off"> -->
							 <input type="text" name="1bx_to_date" id="1bx_to_date" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 80%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY" >
						              
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="row form-group">
						<div class="col-md-5">
							<label class=" form-control-label"><strong
								style="color: red;">* </strong>Diagnosis</label>
						</div>
						<div class="col-md-7">
							<input type="text" name="1bx_diagnosis_code" id="1bx_diagnosis_code" class="form-control-sm form-control" autocomplete="off"
								placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">
						</div>
					</div>
				</div>
			</div>
			
				<div  id="shapediv" style="width: -webkit-fill-available;">				
							
			<div class="card-header-inner" style="text-align: center;margin-bottom:20px;">
			 <strong style="color: red;">* </strong><b>S - Psychological & Congnitive</b></div>
			<div class="">
	<table id="s_madtable" class="table-bordered " style="margin-top:10px;width:100%;">
											
											<thead style=" color: white; text-align: center;">
												<tr>
													<th style="width: 2%;">Sr No</th>
													<th style="">Status LMC</th> 
													<th style="">Value</th> 
													<th style="">From Date</th> 													
													<th style="">To Date</th> 
													<th style="display:none;" class="diagnosisClass1">Diagnosis</th>	

													<th style="display:none;" class="addbtClass1" >Action</th>
											   </tr>
											</thead>
										   <tbody data-spy="scroll" id="s_madtbody" style="min-height:46px; max-height:650px; text-align: center;">
												 <tr id="tr_sShape1">
													<td  style="width: 2%;">1</td>
													<td style="">
													<select name="s_status1" id="s_status1" 
																class="form-control-sm form-control" onchange="statusChange(1,1,this.options[this.selectedIndex].value)">
																<option value="0">--Select--</option>																
																<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">
																	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>
																</c:forEach>
															</select>
						                               </td>
						                                 <td style="">
														<select name="sShape_value1" id="sShape_value1" 
																class="form-control-sm form-control" onchange="onShapeValueChange(this,'s')">
																<option value="0">--Select--</option>																
															</select>
														</td>
						                             
														<td style="">
			 											 <input type="text" name="s_from_date1" id="s_from_date1" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY" >
						              
			 											 </td>
						                               
							                        <td style="">
						                              <input type="text" name="s_to_date1" id="s_to_date1" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY" >
						              
						                               </td>
						                                <td style="display:none; "  class="diagnosisClass11" >
														 <input type="text" name="_diagnosis_code11" id="_diagnosis_code11" class="form-control-sm form-control" autocomplete="off" 
														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                              
														   </td>

						                             
						                             <td style="display:none;"><input type="text" id="sShape_ch_id1" name="sShape_ch_id1"  value="0" class="form-control autocomplete" autocomplete="off" ></td>
						                               
													<td style="width: 10%;display:none;" class="addbtClass11">
													   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "sShape_add1" onclick="sShape_add_fn(1);" ><i class="fa fa-plus"></i></a>
													</td>
													</tr>
										   </tbody> 
									</table>
					</div>
					
					<br/>
				
		<div class="card-header-inner" style="text-align: center;margin-bottom:20px;">
		 <strong style="color: red;">* </strong><b>H - Hearing</b>
		
		
		 </div>
				<div>
	<table id="h_madtable" class="table-bordered " style="margin-top:10px;width:100%;">
											
											<thead style=" color: white; text-align: center;">
												<tr>
													<th style="width: 2%;">Sr No</th>
													<th style="">Status LMC</th> 
													<th style="">Value</th> 
													<th style="">From Date</th> 													
													<th style="">To Date</th> 
													<th style="display:none;" class="diagnosisClass2">Diagnosis</th>		

													<th style="display:none;" class="addbtClass2">Action</th>
											   </tr>
											</thead>
										   <tbody data-spy="scroll" id="h_madtbody" style="min-height:46px; max-height:650px; text-align: center;">
												 <tr id="tr_hShape1">
													<td  style="width: 2%;">1</td>
													<td style="">
													<select name="h_status1" id="h_status1" 
																class="form-control-sm form-control" onchange="statusChange(2,1,this.options[this.selectedIndex].value)">
																<option value="0">--Select--</option>																
																<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">
																	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>
																</c:forEach>
															</select>
						                               </td>
						                                 <td style="">
														<select name="hShape_value1" id="hShape_value1" 
																class="form-control-sm form-control" onchange="onShapeValueChange(this,'h')">
																<option value="0">--Select--</option>																
															</select>
														</td>
						                             
													<td style="">
			 											 <input type="text" name="h_from_date1" id="h_from_date1" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY" >
						              
			 											 </td>
						                               
							                        <td style="">
						                              <input type="text" name="h_to_date1" id="h_to_date1" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY" >
						              
						                               </td>
						                                <td style=" display:none;"  class="diagnosisClass21">
														 <input type="text" name="_diagnosis_code21" id="_diagnosis_code21" class="form-control-sm form-control" autocomplete="off" 
														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                              
														   </td>

						                             
						                             <td style="display:none;"><input type="text" id="hShape_ch_id1" name="hShape_ch_id1"  value="0" class="form-control autocomplete" autocomplete="off" ></td>
						                               
													<td style="width: 10%;display:none;" class="addbtClass21">
													   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "hShape_add1" onclick="hShape_add_fn(1);" ><i class="fa fa-plus"></i></a>
													</td>
													</tr>
										   </tbody> 
									</table>
					</div>
					
					
					<br/>
				<div class="card-header-inner" style="text-align: center;margin-bottom:20px;">
				
				<strong style="color: red;">* </strong><b>A - Appendages</b>
				 </div>		
					<div>
	<table id="a_madtable" class="table-bordered " style="margin-top:10px;width:100%;">
											
											<thead style=" color: white; text-align: center;">
												<tr>
													<th style="width: 2%;">Sr No</th>
													<th style="">Status LMC</th> 
													<th style="">Value</th> 
													<th style="">From Date</th> 													
													<th style="">To Date</th> 
													<th style="display:none;" class="diagnosisClass3">Diagnosis</th>	

													<th style="display:none;" class="addbtClass3">Action</th>
											   </tr>
											</thead>
										   <tbody data-spy="scroll" id="a_madtbody" style="min-height:46px; max-height:650px; text-align: center;">
												 <tr id="tr_aShape1">
													<td  style="width: 2%;">1</td>
													<td style="">
													<select name="a_status1" id="a_status1" 
																class="form-control-sm form-control" onchange="statusChange(3,1,this.options[this.selectedIndex].value)">
																<option value="0">--Select--</option>																
																<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">
																	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>
																</c:forEach>
															</select>
						                               </td>
						                                 <td style="">
														<select name="aShape_value1" id="aShape_value1" 
																class="form-control-sm form-control" onchange="onShapeValueChange(this,'a')">
																<option value="0">--Select--</option>																
															</select>
														</td>
						                             
														<td style="">
			 											 <input type="text" name="a_from_date1" id="a_from_date1" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY" >
						              
			 											 </td>
						                               
							                        <td style="">
						                              <input type="text" name="a_to_date1" id="a_to_date1" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY" >
						              
						                               </td>
						                                <td style=" display:none; "  class="diagnosisClass31">
														 <input type="text" name="_diagnosis_code31" id="_diagnosis_code31" class="form-control-sm form-control" autocomplete="off" 
														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                              
														   </td>

						                             
						                             <td style="display:none;"><input type="text" id="aShape_ch_id1" name="aShape_ch_id1"  value="0" class="form-control autocomplete" autocomplete="off" ></td>
						                               
													<td style="width: 10%;display:none;" class="addbtClass31">
													   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "aShape_add1" onclick="aShape_add_fn(1);" ><i class="fa fa-plus"></i></a>
													</td>
													</tr>
										   </tbody> 
									</table>
					</div>
					
					
					<br/>
					<div class="card-header-inner" style="text-align: center;margin-bottom:20px;"> 
					<strong style="color: red;">* </strong><b>P - Physical Capacity</b>
					</div>	
					<div>
	<table id="p_madtable" class="table-bordered " style="margin-top:10px;width:100%;">
											
											<thead style=" color: white; text-align: center;">
												<tr>
													<th style="width: 2%;">Sr No</th>
													<th style="">Status LMC</th> 
													<th style="">Value</th> 
													<th style="">From Date</th> 													
													<th style="">To Date</th> 
													<th style="display:none; " class="diagnosisClass4">Diagnosis</th>	

													<th style="display:none;" class="addbtClass4">Action</th>
											   </tr>
											</thead>
										   <tbody data-spy="scroll" id="p_madtbody" style="min-height:46px; max-height:650px; text-align: center;">
												 <tr id="tr_eShape1">
													<td  style="width: 2%;">1</td>
													<td style="">
													<select name="p_status1" id="p_status1" 
																class="form-control-sm form-control" onchange="statusChange(4,1,this.options[this.selectedIndex].value)">
																<option value="0">--Select--</option>																
																<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">
																	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>
																</c:forEach>
															</select>
						                               </td>
						                                 <td style="">
														<select name="pShape_value1" id="pShape_value1" 
																class="form-control-sm form-control" onchange="onShapeValueChange(this,'p')">
																<option value="0">--Select--</option>																
															</select>
														</td>
						                             
													<td style="">
			 											 <input type="text" name="p_from_date1" id="p_from_date1" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY" >
						              
			 											 </td>
						                               
							                        <td style="">
						                              <input type="text" name="p_to_date1" id="p_to_date1" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY" >
						              
						                               </td>
						                                <td style="display:none; "  class="diagnosisClass41">
														 <input type="text" name="_diagnosis_code41" id="_diagnosis_code41" class="form-control-sm form-control" autocomplete="off" 
														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                              
														   </td>

						                             
						                             <td style="display:none;"><input type="text" id="pShape_ch_id1" name="pShape_ch_id1"  value="0" class="form-control autocomplete" autocomplete="off" ></td>
						                               
													<td style="width: 10%;display:none;" class="addbtClass41">
													   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "pShape_add1" onclick="pShape_add_fn(1);" ><i class="fa fa-plus"></i></a>
													</td>
													</tr>
										   </tbody> 
									</table>
					</div>
					
					<br/>
					
	<div class="card-header-inner" style="text-align: center;margin-bottom:20px;">
	<strong style="color: red;">* </strong><b>E - Eye Sight</b>
	
	 </div>			
					<div>
	<table id="e_madtable" class="table-bordered " style="margin-top:10px;width:100%;">
											
											<thead style=" color: white; text-align: center;">
												<tr>
													<th style="width: 2%;">Sr No</th>
													<th style="">Status LMC</th> 
													<th style="">Value</th> 
													<th style="">From Date</th> 													
													<th style="">To Date</th> 
													<th style="display:none;" class="diagnosisClass5">Diagnosis</th>		

													<th style="display:none;" class="addbtClass5">Action</th>
											   </tr>
											</thead>
										   <tbody data-spy="scroll" id="e_madtbody" style="min-height:46px; max-height:650px; text-align: center;">
												 <tr id="tr_eShape1">
													<td  style="width: 2%;">1</td>
													<td style="">
													<select name="e_status1" id="e_status1" 
																class="form-control-sm form-control" onchange="statusChange(5,1,this.options[this.selectedIndex].value)">
																<option value="0">--Select--</option>																
																<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">
																	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>
																</c:forEach>
															</select>
						                               </td>
						                                 <td style="">
														<select name="eShape_value1" id="eShape_value1" 
																class="form-control-sm form-control" onchange="onShapeValueChange(this,'e')">
																<option value="0">--Select--</option>																
															</select>
														</td>
						                             
													<td style="">
			 											 <input type="text" name="e_from_date1" id="e_from_date1" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY" >
						              
			 											 </td>
						                               
							                        <td style="">
						                              <input type="text" name="e_to_date1" id="e_to_date1" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY" >
						              
						                               </td>
						                                <td style="display:none;" class="diagnosisClass51">
														 <input type="text" name="_diagnosis_code51" id="_diagnosis_code51" class="form-control-sm form-control" autocomplete="off" 
														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                              
														   </td>
						                             
						                             <td style="display:none;"><input type="text" id="eShape_ch_id1" name="eShape_ch_id1"  value="0" class="form-control autocomplete" autocomplete="off" ></td>
						                               
													<td style="width: 10%;display:none;" class="addbtClass51">
													   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "eShape_add1" onclick="eShape_add_fn(1);" ><i class="fa fa-plus"></i></a>
													</td>
													</tr>
										   </tbody> 
									</table>
					</div>
					
					
				<br/>
				
				<div class="card-header-inner" style="text-align: center;margin-bottom:20px;"> <strong>C - Climate and Terrain Restrictions</strong></div>
				<div style="width: -webkit-fill-available;">
	<table id="c_cmadtable" class="table-bordered " style="margin-top:10px;width:100%;">
											<thead style=" color: white; text-align: center;">
												<tr>
													<th style="width: 2%;">Sr No</th>
													<th style="">Value & Description</th>
													<th style="display:none;" class="cCopClass">Other</th>  													
<!-- 													<th >Description</th>																					 -->
													<th style="width: 10%; display:none;" class="CopaddbtClass1">Action</th>
											   </tr>
											</thead>
										   <tbody data-spy="scroll" id="c_cmadtbody" style="min-height:46px; max-height:650px; text-align: center;">
												 <tr id="tr_cCope1">
													<td  style="width: 2%;">1</td>
													<td style="">
													<select name="c_cvalue1" id="c_cvalue1" onchange="onCCopeChange(this,1); onCopeChangebt(this,1,1);"
																class="form-control-sm form-control" >
<!-- 																<option value="0">--Select--</option>																 -->
																<c:forEach var="item" items="${getcCopeList}" varStatus="num">
																	<option value="${item[1]}" name="${item[0]}">${item[0]}</option>
																</c:forEach>
															</select>
						                               </td>
						                                
						                             <td style="display:none;" class="cCopClass1" >
														 <input type="text" name="c_cother1" id="c_cother1" class="form-control-sm form-control" autocomplete="off" >						                              
														   </td>

														   
						                             
						                             <td style="display:none;"><input type="text" id="cCope_ch_id1" name="cCope_ch_id1"  value="0" class="form-control autocomplete" autocomplete="off" ></td>
						                               
													<td style="width: 10%; display:none;" class="CopaddbtClass11">
													   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "cCope_add1" onclick="cCope_add_fn(1);" ><i class="fa fa-plus"></i></a>
													</td>
													</tr>
										   </tbody> 
									</table>
					</div>
					<br/>
					
					<div class="card-header-inner" style="text-align: center;margin-bottom:20px;"> <strong>O - Degree of Medical Observation Required</strong></div>
									<div style="width: -webkit-fill-available;">
	<table id="c_omadtable" class="table-bordered " style="margin-top:10px;width:100%;">
											
											<thead style=" color: white; text-align: center;">
												<tr>
													<th style="width: 2%;">Sr No</th>
													<th style="">Value & Description</th> 													
<!-- 													<th >Description</th>																					 -->
													<th style="width: 10%; display:none;" class="CopaddbtClass2">Action</th>
											   </tr>
											</thead>
										   <tbody data-spy="scroll" id="c_omadtbody" style="min-height:46px; max-height:650px; text-align: center;">
												 <tr id="tr_oCope1">
													<td  style="width: 2%;">1</td>
													<td style="">
													<select name="c_ovalue1" id="c_ovalue1" onchange="onCopeChangebt(this,2,1);"
																class="form-control-sm form-control" >
<!-- 																<option value="0">--Select--</option>																 -->
																<c:forEach var="item" items="${getoCopeList}" varStatus="num">
																	<option value="${item[1]}" name="${item[0]}">${item[0]}</option>
																</c:forEach>
															</select>
						                               </td>
						                                
	
						                             
						                             <td style="display:none;"><input type="text" id="oCope_ch_id1" name="oCope_ch_id1"  value="0" class="form-control autocomplete" autocomplete="off" ></td>
						                               
													<td style="width: 10%; display:none;" class="CopaddbtClass21">
													   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "oCope_add1" onclick="oCope_add_fn(1);" ><i class="fa fa-plus"></i></a>
													</td>
													</tr>
										   </tbody> 
									</table>
					</div>
					
					
					<br/>
					<div class="card-header-inner" style="text-align: center;margin-bottom:20px;"> <strong>P - Physical Capability Limitations</strong></div>
										<div style="width: -webkit-fill-available;">
	<table id="c_pmadtable" class="table-bordered " style="margin-top:10px;width:100%;">
											
											<thead style=" color: white; text-align: center;">
												<tr>
													<th style="width: 2%;">Sr No</th>
													<th style="">Value & Description</th> 													
<!-- 													<th >Description</th>																					 -->
													<th style="width: 10%; display:none;" class="CopaddbtClass3">Action</th>
											   </tr>
											</thead>
										   <tbody data-spy="scroll" id="c_pmadtbody" style="min-height:46px; max-height:650px; text-align: center;">
												 <tr id="tr_pCope1">
													<td  style="width: 2%;">1</td>
													<td style="">
													<select name="c_pvalue1" id="c_pvalue1" onchange="onCopeChangebt(this,3,1);"
																class="form-control-sm form-control" >
<!-- 																<option value="0">--Select--</option>																 -->
																<c:forEach var="item" items="${getpCopeList}" varStatus="num">
																	<option value="${item[1]}" name="${item[0]}">${item[0]}</option>
																</c:forEach>
															</select>
						                               </td>
						                                													   
						                             
						                             <td style="display:none;"><input type="text" id="pCope_ch_id1" name="pCope_ch_id1"  value="0" class="form-control autocomplete" autocomplete="off" ></td>
						                               
													<td style="width: 10%; display:none;" class="CopaddbtClass31">
													   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "pCope_add1" onclick="pCope_add_fn(1);" ><i class="fa fa-plus"></i></a>
													</td>
													</tr>
										   </tbody> 
									</table>
					</div>	
					

<br/>
<div class="card-header-inner" style="text-align: center;margin-bottom:20px;"> <strong>E - Exclusive Limitations</strong></div>
			<div style="width: -webkit-fill-available;">
	<table id="c_emadtable" class="table-bordered " style="margin-top:10px;width:100%;">
											
											<thead style=" color: white; text-align: center;">
												<tr>
													<th style="width: 2%;">Sr No</th>
													<th style="">Value & Description</th>
													<th style="display:none;" class="eCopClass">SubValue</th> 
													<th style="display:none;" class="eCop_otherClass">Other</th> 													
<!-- 													<th >Description</th>																					 -->
													<th style="width: 10%; display:none;" class="CopaddbtClass4">Action</th>
											   </tr>
											</thead>
										   <tbody data-spy="scroll" id="c_emadtbody" style="min-height:46px; max-height:650px; text-align: center;">
												 <tr id="tr_eCope1">
													<td  style="width: 2%;">1</td>
													<td style="">
													<select name="c_evalue1" id="c_evalue1" onchange="onECopeChange(this,1); onCopeChangebt(this,4,1);"
																class="form-control-sm form-control" >
<!-- 																<option value="0">--Select--</option>																 -->
																<c:forEach var="item" items="${geteCopeList}" varStatus="num">
																	<option value="${item[1]}" name="${item[0]}">${item[0]}</option>
																</c:forEach>
															</select>
						                               </td>
						                                <td style="display:none;" class="eCopClass1">
													<select name="c_esubvalue1" id="c_esubvalue1" onchange="onECopeSubChange(this,1)"
																class="form-control-sm form-control" >
																<option value="0">--Select--</option>																
																<c:forEach var="item" items="${getesubCopeList}" varStatus="num">
																	<option value="${item[1]}" name="${item[0]}">${item[0]}</option>
																</c:forEach>
															</select>
						                               </td>
						                             <td style="display:none;" class="eCop_otherClass1" >
														 <input type="text" name="c_esubvalueother1" id="c_esubvalueother1" class="form-control-sm form-control" autocomplete="off" >						                              
														   </td>
					                             
						                             <td style="display:none;"><input type="text" id="eCope_ch_id1" name="eCope_ch_id1"  value="0" class="form-control autocomplete" autocomplete="off" ></td>
						                               
													<td style="width: 10%; display:none;" class="CopaddbtClass41">
													   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "eCope_add1" onclick="eCope_add_fn(1);" ><i class="fa fa-plus"></i></a>
													</td>
													</tr>
										   </tbody> 
									</table>
					</div>
					</div>
					
							</div>
	     <div class="card-footer" align="center" class="form-control"> 
		        <input type="button" class="btn btn-primary btn-sm" value="Update" onclick="return medical_cat_save();">		              
		 </div> 
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<input type="hidden" id="sShape_count" name="sShape_count"
				class="required form-control" autocomplete="off"  value="1"/> 
			<input type="hidden" id="sShapeOld_count" name="sShapeOld_count"
				class="form-control" maxlength="2" autocomplete="off" value="0">
				
				<input type="hidden" id="hShape_count" name="hShape_count"
				class="required form-control" autocomplete="off"  value="1"/> 
			<input type="hidden" id="hShapeOld_count" name="hShapeOld_count"
				class="form-control" maxlength="2" autocomplete="off" value="0">
				
				<input type="hidden" id="aShape_count" name="aShape_count"
				class="required form-control" autocomplete="off"  value="1"/> 
			<input type="hidden" id="aShapeOld_count" name="aShapeOld_count"
				class="form-control" maxlength="2" autocomplete="off" value="0">
				
				<input type="hidden" id="pShape_count" name="pShape_count"
				class="required form-control" autocomplete="off"  value="1"/> 
			<input type="hidden" id="pShapeOld_count" name="pShapeOld_count"
				class="form-control" maxlength="2" autocomplete="off" value="0">
				
				<input type="hidden" id="eShape_count" name="eShape_count"
				class="required form-control" autocomplete="off"  value="1"/> 
			<input type="hidden" id="eShapeOld_count" name="eShapeOld_count"
				class="form-control" maxlength="2" autocomplete="off" value="0">
				
				<input type="hidden" id="cCope_count" name="cCope_count"
				class="required form-control" autocomplete="off"  value="1"/> 
			<input type="hidden" id="cCopeOld_count" name="cCopeOld_count"
				class="form-control" maxlength="2" autocomplete="off" value="0">
					<input type="hidden" id="oCope_count" name="oCope_count"
				class="required form-control" autocomplete="off"  value="1"/> 
			<input type="hidden" id="oCopeOld_count" name="oCopeOld_count"
				class="form-control" maxlength="2" autocomplete="off" value="0">
					<input type="hidden" id="pCope_count" name="pCope_count"
				class="required form-control" autocomplete="off"  value="1"/> 
			<input type="hidden" id="pCopeOld_count" name="pCopeOld_count"
				class="form-control" maxlength="2" autocomplete="off" value="0">
					<input type="hidden" id="eCope_count" name="eCope_count"
				class="required form-control" autocomplete="off"  value="1"/> 
			<input type="hidden" id="eCopeOld_count" name="eCopeOld_count"
				class="form-control" maxlength="2" autocomplete="off" value="0">
		
	</form>

</c:if>

<c:if test="${ChangeOfCDA!=0 }">

<form id="form_cda">
		<div class="card">
			<div class="panel-group" id="accordion6">
				<div class="panel panel-default" id="e_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title lightblue" id="e_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion6" href="#" id="e_final" onclick="divN(this)"
								><b>CHANGE IN CDA ACCOUNT NO</b></a>
						</h4>
					</div>
					<div id="collapse1e" class="panel-collapse collapse">
				<div class="card-body card-block"><br>
				  <input type="button" name="save" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('CDA');" /> 
							<div class="row">
					 	<div class="col-md-12">
					 		<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Personal No </label>
						                </div>
						                <div class="col-md-8">
				  							<input type="text" id="personnel_no_c" name="personnel_no_c"class="form-control autocomplete" maxlength="9" autocomplete="off" onchange="personal_number()" onkeyup="return specialcharecter(this)" onkeypress="return AvoidSpace(event)"> 
						                </div>
						            </div>
	              				</div>	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                     <label class=" form-control-label"><strong style="color: red;">* </strong> CDA Account No</label>
						                </div>
						                <div class="col-md-8">
						                  <input type="text" id="cda_acc_no" name="cda_acc_no" class="form-control autocomplete" maxlength="20" autocomplete="off" onkeyup="return specialcharecterCDA(this)" onkeypress="return AvoidSpace(event)">
						                  <input type="hidden" id="statusV" name="statusV" class="form-control autocomplete"  > 
						                  <input type="hidden" id="unit_sus_noV" name="unit_sus_noV" class="form-control autocomplete"  > 
						                  <input type="hidden" id="unit_nameV" name="unit_nameV" class="form-control autocomplete"  > 
						                  <input type=hidden id="rankV" name="rankV" class="form-control autocomplete"  > 
						                  <input type=hidden id="personnel_noV" name="personnel_noV" class="form-control autocomplete"  > 						                 						                  
						                  <input type="hidden" id="id" name="id" class="form-control autocomplete" autocomplete="off" value="0"> 
						                  <input type="hidden" id="comm_id" name="comm_id" class="form-control autocomplete" autocomplete="off"  value="0"> 						                   
						                  <input type="hidden" id="census_id" name="census_id" value="0" class="form-control autocomplete" autocomplete="off">
						                    <input type="hidden" id="cr_byV" name="cr_byV" class="form-control autocomplete"  > 
						                 <input type="hidden" id="cr_dateV" name="cr_dateV" class="form-control autocomplete"  > 
						             
						                </div>
						            </div>
	              				</div>	            				
	              			</div>
	              		
	              		<div class="col-md-12">
						  <div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong> Rank </label>
								</div>
								<div class="col-md-8">
									<label class=" form-control-label" id="cda_rank"></label> 
									<input type="hidden" id="hd_p_rank" name="hd_p_rank" class="form-control autocomplete" autocomplete="off">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong> Name </label>
								</div>
								<div class="col-md-8">
									<label class=" form-control-label" id="cda_cadet_name"></label>
									<input type="hidden" id="cname" name="cname" class="form-control autocomplete" autocomplete="off">
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong> Unit Name </label>
								</div>
								<div class="col-md-8">
									<label class=" form-control-label" id="cda_app_unit_name"></label>
									<input type="hidden" id="un" name="un" class="form-control autocomplete" autocomplete="off">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong> SUS No </label>
								</div>
								<div class="col-md-8">
									<label class=" form-control-label" id="cda_app_sus_no"></label>
								</div>
							</div>
						</div>

					</div>	
							</div>
						  <div class="card-footer" align="center" class="form-control">
							    <a href="cda_acc_no_url" class="btn btn-success btn-sm" id ="clear" >Clear</a> 
			              		<input type="button" class="btn btn-primary btn-sm" value="Update CDA Account" onclick="return validate_insertcda_save();">
<!-- 			              		<input type="button" name="save" class="btn btn-secondary btn-sm" id ="popup"  value="View History " onclick="Pop_Up_History('CDA');" /> -->
<!-- 				            	 <input type="button"  id ="Cancle" class="btn btn-info btn-sm" onclick="Cancel();" style="display:none; " value="Back" > -->
			            	</div> 
						</div>		
					</div>
				</div>
			</div>
		</div>
	</form>
</c:if>

<c:if test="${ChangeOfmedal!=0 }">

	 <!-- START AWARD & MEDAL -->
	<form id="forminspupdate">
                <div class="card">
                        <div class="panel-group" id="accordion14">
                                <div class="panel panel-default" id="u_div1">
                                        <div class="panel-heading">
                                                <h4 class="panel-title main_title lightred" id="u_div5">
                                                        <a class="droparrow collapsed" data-toggle="collapse"
                                                                data-parent="#accordion14" href="#" id="u_final" onclick="divN(this)"
                                                                ><b>UPDATE AWARDS & MEDAL</b></a>
                                                </h4>
                                        </div>
                                        <div id="collapse1u" class="panel-collapse collapse">
                                                <!-- <div class="card-body card-block"> -->
                                                        <div class="card-body card-block" id="total_table"><br>
                                                              <input type="button" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('update_awards_medal');">							
                                                        <table id="awardsNmedal_table" class="table-bordered " style="margin-top:10px;">
                                                                <thead style=" color: white; text-align: center;">
                                                                        <tr>
                                                                                <th style="width: 2%;">Sr No</th>
                                                                                <th style="width: 10%;"><strong style="color: red;">* </strong>Authority</th>                                                
                                                                                <th style="width: 10%;"><strong style="color: red;">* </strong>Date of Authority</th>
                                                                                <th style="width: 10%;"><strong style="color: red;">* </strong>Category</th>
                                                                                <th style="width: 10%;"><strong style="color: red;">* </strong>Award/Medal</th>
                                                                                <th style="width: 10%;"><strong style="color: red;">* </strong>Date of Award</th>
                                                                                <th style="width: 10%;"><strong style="color: red;">* </strong>Unit</th>
                                                                                <th style="width: 10%;"><strong style="color: red;">* </strong>BDE</th>
                                                                                <th style="width: 10%;"><strong style="color: red;">* </strong>DIV/Sub Area</th>
                                                                                <th style="width: 10%;"><strong style="color: red;">* </strong>Corps/Area</th>
                                                                                <th style="width: 10%;"><strong style="color: red;">* </strong>Command</th>                                                                                                                                                       
                                                                                
                                                                                <th style="width: 10%;">Action</th>
                                                                   </tr>
                                                                </thead>
                                                           <tbody data-spy="scroll" id="awardsNmedal_tbody" style="min-height:46px; max-height:650px; text-align: center;">
                                                                        <tr id="tr_awardsNmedal1">
                                                                                <td class="anm_srno" style="width: 2%;">1</td>
                                                                                <td style="width: 10%;">
                                                                                  <input type="text" id="awardsNmedal_authority1" name="awardsNmedal_authority1" class="form-control autocomplete" 
                                                                                  autocomplete="off" 	onkeyup="return specialcharecter(this)" maxlength="100">
                                                                                 </td>
                                                                                  <td style="width: 10%;">
                                                                                    <input type="text" name="awardsNmedal_date_of_authority1" id="awardsNmedal_date_of_authority1" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
																					onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
																					onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
                                                                                   
                                                                                   </td>
                                                                                <td style="width: 10%;">
                                                                                        <select name="Category_81" id="Category_81" onchange="getMedalDescList(this)" class="form-control">
                                                                                                        <option value="0">--Select--</option>
                                                                                                        <c:forEach var="item" items="${getMedalList}" varStatus="num">
                                                                                                                <option value="${item[0]}" name="${item[1]}">${item[1]}</option>
                                                                                                        </c:forEach>
                                                                                        </select>
                                                                                        </td>
                                                                                        <td style="width: 10%;">
                                                                                        <select name="select_desc1" id="select_desc1" class="form-control">
                                                                                                        <option value="0">--Select--</option>
                                                                                        </select>
                                                                                        </td>
                                                                                        <td style="width: 10%;">
                                                                                     <input type="text" name="date_of_award1" id="date_of_award1" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
																					onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
																					onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
                                                                                   </td>
                                                                                   <td style="width: 10%;">
                                                                                  <input type="text" id="awardsNmedal_unit1" name="awardsNmedal_unit1" class="form-control autocomplete" 
                                                                                     onkeypress="unitData(this)" autocomplete="off" maxlength="255">
                                                                                </td>
                                                                                <td style="width: 10%;">
                                                                                <select name="awardsNmedal_bde1" id="awardsNmedal_bde1" class="form-control">
                                                                                                        <option value="0">--Select--</option>
                                                                                                        <c:forEach var="item" items="${getPSG_BdeList}" varStatus="num">
                                                                                                                <option value="${item[0]}" name="${item[1]}">${item[1]}</option>
                                                                                                        </c:forEach>
                                                                                        </select>
                                                                                </td>
                                                                                 <td style="width: 10%;">
                                                                                <select name="awardsNmedal_div_subarea1" id="awardsNmedal_div_subarea1"  class="form-control">
                                                                                                        <option value="0">--Select--</option>
                                                                                                        <c:forEach var="item" items="${getPSG_DivList}" varStatus="num">
                                                                                                                <option value="${item[0]}" name="${item[1]}">${item[1]}</option>
                                                                                                        </c:forEach>
                                                                                        </select>
                                                                                </td>
                                                                                   <td style="width: 10%;">
                                                                                   
                                                                                <select name="awardsNmedal_corps_area1" id="awardsNmedal_corps_area1"  class="form-control">
                                                                                                        <option value="0">--Select--</option>
                                                                                                        <c:forEach var="item" items="${getPSG_CorpsList}" varStatus="num">
                                                                                                                <option value="${item[0]}" name="${item[1]}">${item[1]}</option>
                                                                                                        </c:forEach>
                                                                                        </select>
                                                                                </td>
                                                                                <td style="width: 10%;">
                                                                                <select name="awardsNmedal_command1" id="awardsNmedal_command1"  class="form-control">
                                                                                                        <option value="0">--Select--</option>
                                                                                                        <c:forEach var="item" items="${getPSG_CommandList}" varStatus="num">
                                                                                                                <option value="${item[0]}" name="${item[1]}">${item[1]}</option>
                                                                                                        </c:forEach>
                                                                                        </select>
                                                                                </td>
                                                                                
                                                                                <td style="display:none;"><input type="text" id="awardsNmedal_ch_id1" name="awardsNmedal_ch_id1"  value="0" class="form-control autocomplete" autocomplete="off" ></td>
                                                                                <td style="width: 10%;">
                                                                                <a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "awardsNmedal_save1" onclick="awardsNmedal_save_fn(1);" ><i class="fa fa-save"></i></a>
                                                                                   <a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "awardsNmedal_add1" onclick="awardsNmedal_add_fn(1);" ><i class="fa fa-plus"></i></a>
                                                                                    <a style="display:none;" class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "awardsNmedal_remove1" onclick="awardsNmedal_remove_fn(1);"><i class="fa fa-trash"></i></a> 
                                                                                </td>
                                                                                </tr>
                                                           </tbody> 
                                                                                </table>
                                                        </div>
                                                <!-- </div> -->
                                        </div>
                                </div>
                        </div>
                </div>
        </form>
        <!-- END AWARD & MEDAL -->

</c:if>

<c:if test="${ChangeOfNOK!=0 }">
<!-- START NOK -->
		<form id="form_nok_addr_details_form">
		<div class="card">
			<div class="panel-group" id="accordion10">
				<div class="panel panel-default" id="h_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title lightblue" id="h_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion10" href="#" id="h_final" onclick="divN(this)"
								><b>CHANGE IN NOK</b></a>
						</h4>
					</div>
					<div id="collapse1h" class="panel-collapse collapse">
						<div class="card-body card-block">
						<div align="left">
						<input type="button" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('change_nok');">
						</div>
						<br>
							<div class="row">
							<div class="col-md-12">	
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;"></strong> Authority</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="authority" name="authority" class="form-control autocomplete" 
						                   autocomplete="off" maxlength="100" 	onkeyup="return specialcharecter(this)"  > 						                   
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;"> </strong> Date of Authority</label>
						                </div>
						                <div class="col-md-8">
						                <%--  <input type="date" id="date_authority" name="date_authority"  max="${date}" min='1899-01-01' class="form-control autocomplete" autocomplete="off" maxlength="10"> --%> 						                     
						                  <input type="text" name="date_authority" id="date_authority" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
						                
						                </div>
						            </div>
	              				</div>
	              			</div>
	              				<div class="col-md-12">	
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Name of NOK</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="nok_name" name="nok_name" class="form-control autocomplete"
						                    autocomplete="off" maxlength="50" onkeypress="return onlyAlphabets(event);"
											oninput="this.value = this.value.toUpperCase()"> 						                   
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Relation</label>
						                </div>
						                <div class="col-md-8">
						                 <input type="hidden" id="nok_id" name="nok_id" value="0" class="form-control autocomplete" autocomplete="off" > 							                 
						                 <select name="nok_relation" id="nok_relation" class="form-control"   >
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getRelationList}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>											
											</select> 						                     
						                </div>
						            </div>
	              				</div>
	              			</div>
	              				<div class="col-md-12">
		           			<label class=" form-control-label"  style="margin-left:10px;"><h4> NOK's Address</h4></label>
		          		 </div> 
		          		 		<div class="col-md-12">	
						<div class="col-md-6">
              					<div class="row form-group">
					               <div class="col-md-4">
					                    <label class=" form-control-label"><strong style="color: red;">* </strong>COUNTRY</label>
					                </div>
					                <div class="col-md-8">
					                <select name="nok_country" id="nok_country" class="form-control"   onchange="fn_nok_Country();">
						                         <option value="0">--Select--</option>
												<c:forEach var="item" items="${getMedCountryName}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</SELECT> 						               				               
					                </div>
					            </div>
              				</div>
              				<div class="col-md-6">
              				<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> STATE</label>
						                </div>
						                <div class="col-md-8">
						                	<select name="nok_state" id="nok_state" class="form-control"  onchange="fn_nok_state();" >
						                        <option value="0">--Select--</option>
												<c:forEach var="item" items="${getMedStateName}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select> 
						                </div>
						            </div>
	              			</div>
	              			</div>
	              				<div class="col-md-12">
								<div class="col-md-6" id = "nok_other_id" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">COUNTRY OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="ctry_other" name="ctry_other" class="form-control autocomplete" 
					                	autocomplete="off" onkeypress="return onlyAlphabets(event);" maxlength="50">
					                	 </div>
						            </div>
	              				</div>
	              				<div class="col-md-6" id = "nok_other_st" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">STATE OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="st_other" name="st_other" class="form-control autocomplete" 
					                	autocomplete="off" onkeypress="return onlyAlphabets(event);" maxlength="50">
					                	 </div>
						            </div>
	              				</div>
	              					</div>
	              			 <div class="col-md-12">	
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> DISTRICT</label>
						                </div>
						                <div class="col-md-8">
						                <select name="nok_district" id="nok_district" class="form-control"  onchange="fn_nok_district();" >
					                		<option value="0">--Select--</option>
											<c:forEach var="item" items="${getMedDistrictName}" varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select> 
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> TEHSIL</label>
						                </div>
						                <div class="col-md-8">
						                 <select name="nok_tehsil" id="nok_tehsil" class="form-control"   onchange="fn_nok_tehsil();" >
					                		 <option value="0">--Select--</option>
											<c:forEach var="item" items="${getMedCityName}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select>
						                </div>
						            </div>
	              				</div>
	              			</div>	
	              			<div class="col-md-12">
								<div class="col-md-6" id = "nok_other_dist" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">DISTRICT OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="dist_other" name="dist_other" class="form-control autocomplete" 
					                	autocomplete="off" onkeypress="return onlyAlphabets(event);" maxlength="50">
					                	 </div>
						            </div>
	              				</div>
	              				<div class="col-md-6" id = "nok_other_te" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">TEHSIL OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="nok_tehsil_other" name="nok_tehsil_other" class="form-control autocomplete" 
					                	autocomplete="off" 	onkeypress="return onlyAlphabets(event);" maxlength="50">
					                	 </div>
						            </div>
	              				</div>
	              					</div>
	              				<div class="col-md-12">	
	              			<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Village/Town/City</label>
						                </div>
						                <div class="col-md-8">
											<input type="text" id="nok_village" name="nok_village" class="form-control autocomplete" 
											autocomplete="off"  onkeypress="return onlyAlphabets(event);" maxlength="50">			               
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Pin</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="nok_pin" name="nok_pin" class="form-control autocomplete" autocomplete="off" 
						                   onkeypress="return isNumber0_9Only(event)" maxlength="6"> 						                   
						                </div>
						            </div>
	              				</div>
	              			</div>
	              			  			<div class="col-md-12">	
	              			<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Nearest Railway Station</label>
						                </div>
						                <div class="col-md-8">
						                 <input type="text" id="nok_rail" name="nok_rail" class="form-control autocomplete" autocomplete="off"
						                  maxlength="100" 	onkeypress="return onlyAlphaNumeric(event);"> 						                     
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						             <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Is  Rural /Urban/Semi Urban</label>
						                </div>
						                <div class="col-md-8">
						     <label for="nok_rural">
							 <input type="radio" id="nok_rural" name="nok_rural_urban" value="rural" > Rural</label> 
					    	 <label for="nok_urban" >
							 <input type="radio" id="nok_urban" name="nok_rural_urban" value="urban" > Urban</label> 
					    	 <label for="nok_semi_urban">
							 <input type="radio" id="nok_semi_urban" name="nok_rural_urban" value="semi_urban" >Semi Urban</label> 
						                </div>
						            </div>
	              				</div>	
	              				</div>
	              			<div class="col-md-12">	
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Nok Mobile No</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="nok_mobile_no" name="nok_mobile_no" class="form-control autocomplete" 
						                   autocomplete="off" onkeypress="return isNumber0_9Only(event)" maxlength="10"> 						                   
						                </div>
						            </div>
	              				</div>
	              			</div>
							</div>
					    <div class="card-footer" align="center" class="form-control">
		              		<input type="button" class="btn btn-primary btn-sm" value="Update" onclick="validate_save_nok_details();">		              
			           
			            </div> 
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	<!-- END NOK -->
</c:if>


<c:if test="${ChangeOfChildDetails!=0 }">
		<div class="card">
			<div class="panel-group" id="accordion3">
				<div class="panel panel-default" id="g_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title lightblue" id="g_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion3" href="#" id="g_final" onclick="divN(this)"
								><b>UPDATE CHILD DETAILS</b></a>
						</h4>
					</div>
					<div id="collapse1g" class="panel-collapse collapse">
						<div class="card-body card-block">
						<div align="left">
						<input type="button" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('update_child_dtl');">
						</div>
								<div class="card-body-header">
									<h5></h5>
								</div>
								<table id="m_children_details_table" class="table-bordered" style="margin-top: 10px; width: 100%;">
								<thead style="color: white; text-align: center;">
									<tr>
										<th>Sr No</th>
										<th><strong style="color: red;">* </strong>Name</th>
										<th><strong style="color: red;">* </strong>Date of Birth</th>
										<th>If Specially Abled Child </th>
										<th><strong style="color: red;">* </strong>Relationship</th>
										<th>If Adopted Child </th>
										<th><strong style="color: red;"> </strong>Adoption Date </th>
										<th><strong style="color: red;"> </strong>Aadhaar Card No </th>
										<th>PAN Card No </th>
										<th>Service/Ex-Service</th>
										<th><strong style="color: red;"></strong>Services</th>
										<th><strong style="color: red;"> </strong>Personal No.</th>
										<th>Other Service</th>
										<th>Action</th>
									</tr>
								</thead>
								<tbody data-spy="scroll" id="m_children_detailstbody" style="min-height: 46px; max-height: 650px; text-align: center;">
									<tr id="tr_children1">
										<td class="child_srno" readonly>1</td>
										<td>
										<input type="text" id="sib_name1" name="sib_name1"  class="form-control autocomplete" autocomplete="off" 
										maxlength="50" onkeypress="return onlyAlphabets(event);" oninput="this.value = this.value.toUpperCase()">
										</td>
										<td>
										 <input type="text" name="sib_date_of_birth1" id="sib_date_of_birth1" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY" >
										</td>
										<td>
										<input type="checkbox" id="sib_type1" name="sib_type1" value="Yes">
										</td>
										<td>
										<select name="sib_relationship1" id="sib_relationship1" class="form-control">
											<option value="0">--Select--</option>
											<c:forEach var="item" items="${getChild_RelationshipList}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select>
										</td>
										<td>
										<input type="checkbox" id="sib_adopted1" name="sib_adopted1" value="Yes" onclick="adoption(1);">
										</td>
										<td>
										<input type="text" name="sib_adop_date1" id="sib_adop_date1" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" readonly="readonly" class="form-control" style="width: 85%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY" >
										</td>
										<td>
										<input type="text" id="aadhar_no1" name="aadhar_no1"  class="form-control autocomplete" autocomplete="off" 
										         maxlength="14" onkeypress="return isAadhar(this,event);">
										</td>
										<td>
										<input type="text" id="pan_no1" name="pan_no1"  class="form-control autocomplete" autocomplete="off" 
										          maxlength="10" onchange="isPAN(this);" oninput="this.value = this.value.toUpperCase()">
										</td>
										<td style="display: none;">
										<input type="text" id="sib_ch_id1" name="sib_ch_id1" value="0" class="form-control autocomplete" autocomplete="off">
										</td>
										<td>
										<input type="checkbox" id="child-ex1" name="child-ex1" value="Yes" onclick="childCB(1);">
										</td>
										<td><select name="child_service1" id="child_service1" onchange="otherfunction(1)"
												class="form-control-sm form-control"
												>
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getExservicemenList}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select></td>
											<td><input type="text" id="child_personal_no1"
												name="child_personal_no1" class="form-control autocomplete"
												autocomplete="off" maxlength="9"
												onkeypress="return /[0-9a-zA-Z]/i.test(event.key)"
												oninput="this.value = this.value.toUpperCase()">
											</td>
											<td><input type="text" id="other_child_ser1"
												name="other_child_ser1" class="form-control autocomplete"
												autocomplete="off" maxlength="50"
												onkeypress="return onlyAlphaNumeric(event);"
												oninput="this.value = this.value.toUpperCase()">
											</td>
										<td>
										<a class="btn btn-primary btn-sm" value="SAVE" title="Update" id="m_children_details_save1"
										   onclick="m_children_details_save_fn(1);"><i class="fa fa-save"></i>
										</a> 
										<a style="display: none;" id="m_children_details_add1" class="btn btn-success btn-sm" 
										value="ADD" title="ADD" onclick="m_children_details_add_fn(1);">
										<i class="fa fa-plus"></i>
										</a>
										<a style="display: none;" id="m_children_details_remove1" class="btn btn-danger btn-sm" value="REMOVE" title="REMOVE"
										onclick="m_children_details_remove_fn(1);"><i class="fa fa-trash"></i>
										</a>
										</td>
									</tr>
								</tbody>
							</table>
							</div>
						</div>
						
							  
					</div>
				</div>
			</div>
</c:if>
<!-- END DETAILS OF CHILDRENS -->
<c:if test="${ChangeOfContact!=0}">
	<!-- START CONTACT DETAILS -->
   <form id="form_cda_acnt_no">
		<div class="card">
			<div class="panel-group" id="accordion1">
				<div class="panel panel-default" id="j_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title lightblue" id="j_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion1" href="#" id="j_final" onclick="divN(this)"
								><b>CHANGE IN CONTACT DETAILS</b></a>
						</h4>
					</div>
					<div id="collapse1j" class="panel-collapse collapse">
			            <div class="card-body card-block">
			            <br>
						   <input type="button" name="save" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('contact_details');" /> 
							<div class="row">
	              		<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Gmail/Others</label>
						                </div>
						                <div class="col-md-8">
						                 <input type="hidden" id="cda_id" name="cda_id" value="0" class="form-control autocomplete" autocomplete="off"  >
						                   <input type="text" id="gmail" name="gmail" class="form-control autocomplete" autocomplete="off" 
						                   onchange="validateEmail(this);" maxlength="64"> 
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>NIC Mail</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="nic_mail" name="nic_mail" class="form-control autocomplete" autocomplete="off" 
						                   maxlength="64" onchange="validateEmail(this);"> 
						                </div>
						            </div>
	              				</div>
	              				
	              			</div>
	              			
	              				<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Mobile No</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="mobile_no" name="mobile_no" maxlength="10" class="form-control autocomplete"  
						                   onkeypress="return isNumber(event)" autocomplete="off" > 
						                </div>
						            </div>
	              				</div>
	              			</div>
							</div>
							<div class="card-footer" align="center" class="form-control">
		              		 <input type="button" class="btn btn-primary btn-sm" value="Update" onclick="validate_cda_acnt_no();">		              
			            </div> 
						</div>
					</div>
				</div>
			</div>
		</div>
		</form>
	<!-- END CONTACT DETAILS -->


</c:if>

<c:if test="${ChangeOfNoneffective!=0 }">
	 	  <!-- START NON EFFECTIVE DETAIL -->
	    <form id="form_non_effective">
		<div class="card">
			<div class="panel-group" id="accordion41">
				<div class="panel panel-default" id="bb_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title green" id="bb_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion41" href="#" id="bb_final" onclick="divN(this)"
								><b>NON EFFECTIVE STATUS</b></a>
						</h4>
					</div>
					<div id="collapse1bb" class="panel-collapse collapse">
			           <div class="card-body card-block">
			            <br>
			            
			              <input type="button" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('Non_Effective_Status');" />
								<div class="row">
						<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> <strong style="color: red;">* </strong>Authority</label>
						                </div>
						                <div class="col-md-8">
						                  <input type="text" id="non_ef_authority" name="non_ef_authority" value="" 
						                  class="form-control"  autocomplete="off" maxlength="100" 	onkeyup="return specialcharecter(this)">	
										  <input type="hidden" id="nf_id" name="nf_id" value="0" class="form-control"  autocomplete="off" >
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> <strong style="color: red;">* </strong>Date of Authority</label>
						                </div>
						                <div class="col-md-8">
						                <input type="text" name="date_of_authority_non_ef" id="date_of_authority_non_ef" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
						                </div>
						            </div>
	              				</div>
	              			</div>
	              			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                   <label for="cause"> <strong style="color: red;">* </strong>Cause of Non Effective <span class="star_design"></span></label>
						                </div>
						                <div class="col-md-8">
						                	<select  id="cause_of_non_effective" name="cause_of_non_effective" class="form-control"   >      <!-- onchange="retire_add_fn(this);" -->
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${getOFFR_Non_EffectiveList}" varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
														</c:forEach>
											</select> 
						                </div>
						            </div>
	              				</div>
	              			    <div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label for="Date Non"><strong style="color: red;">* </strong> Date of Non Effective <span class="star_design"></span></label>
						                </div>
						                <div class="col-md-8">
						                <input type="text" name="date_of_non_effective" id="date_of_non_effective" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
						                </div>
						            </div>
	              				</div>
	              			</div>
	              			 	<div class="col-md-12" style="font-size: 15px;">	
					           		<input type="checkbox" id="check_per_address" name="check_per_address" onclick="per_address()">
					               	<label for="text-input" class=" form-control-label" style="color: #dd1a3e; margin-left:10px;">  Same as Permanent Address </label>
					            </div>
					     
	              			<div class="col-md-12">
				           			<label class=" form-control-label"  style="margin-left:10px;"><h4> Address  After Service in Army (Permanent Address)</h4></label>
				          		</div>  
				   
				   <!--  country-->
	              		<div class="col-md-12">	
						<div class="col-md-6">
              					<div class="row form-group">
					               <div class="col-md-4">
					                    <label class=" form-control-label"><strong style="color: red;">* </strong>COUNTRY</label>
					                </div>
					                <div class="col-md-8">
					                <select name="per_home_addr_country" id="perm_home_addr_country" class="form-control"  
					                onchange="fn_perm_home_addr_Country(),onPermHomeAddrCountry()" >
						                        <option value="0">--Select--</option>
												<c:forEach var="item" items="${getMedCountryName}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
									</select> 
					                </div>
					            </div>
              				</div>
              				
              				<div class="col-md-6">
              				<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> STATE</label>
						                </div>
						                <div class="col-md-8">
						                	<select name="per_home_addr_state" id="perm_home_addr_state" class="form-control"   onchange="fn_prem_domicile_state(),onPermHomeAddrState()">
						                        <option value="0">--Select--</option>
												<c:forEach var="item" items="${getMedStateName}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select> 
						                </div>
						            </div>
	              			</div>
	              			</div>
	              			
	              			<!-- //country and STATE OTHERs -->
	              		<div class="col-md-12">	
							  <div class="col-md-6" id = "Ot_perm_hm_addr_con_div" style="display: none;">
              					<div class="row form-group">
					               <div class="col-md-4">
					                    <label class=" form-control-label"><strong style="color: red;">* </strong>OTHER COUNTRY</label>
					                </div>
					                <div class="col-md-8">
					                  <input type="text" id="perm_home_addr_country_other" name="per_home_addr_country_other"  
					                  class="form-control autocomplete" autocomplete="off"  maxlength="50" onkeypress="return onlyAlphabets(event);">				               
					                </div>
					            </div>
              				</div>
              				<div class="col-md-6" id = "Ot_perm_hm_addr_state_div" style="display: none;">
              				<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>OTHER STATE</label>
						                </div>
						               <div class="col-md-8">
					                  <input type="text" id="perm_home_addr_state_other" name="per_home_addr_state_other" 
					                  class="form-control autocomplete" autocomplete="off" maxlength="50" onkeypress="return onlyAlphabets(event);">				               
					                </div>
						            </div>
	              			</div>
	              	</div>
	              	
	              	
	              	<!-- DISTRICT -->
	              				<div class="col-md-12">	
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> DISTRICT</label>
						                </div>
						                <div class="col-md-8">
						               <select name="per_home_addr_district" id="perm_home_addr_district" class="form-control"  
						                onchange="fn_prem_domicile_district(),onPermHomeAddrDis()">
					                		<option value="0">--Select--</option>
											<c:forEach var="item" items="${getMedDistrictName}" varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select>  
										 
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> TEHSIL</label>
						                </div>
						                <div class="col-md-8">
						                 <select name="per_home_addr_tehsil" id="perm_home_addr_tehsil" class="form-control" onchange="onPermHomeAddrTeh();"  >
					                		<option value="0">--Select--</option>
											<c:forEach var="item" items="${getMedCityName}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select>
						                </div>
						            </div>
	              				</div>
	              			</div>
	              			
	              				<!-- //DISTRICT and TEHSIL others -->
	              	<div class="col-md-12">	
						<div class="col-md-6" id = "Ot_perm_hm_addr_dis_div" style="display: none;">
              					<div class="row form-group">
					               <div class="col-md-4">
					                    <label class=" form-control-label"><strong style="color: red;">* </strong>OTHER DISTRICT</label>
					                </div>
					                <div class="col-md-8">
					                  <input type="text" id="perm_home_addr_district_other" name="per_home_addr_district_other"   
					                   class="form-control autocomplete" autocomplete="off" maxlength="50" onkeypress="return onlyAlphabets(event);">				               
					                </div>
					            </div>
              				</div>
              				<div class="col-md-6" id = "Ot_perm_hm_addr_teh_div" style="display: none;">
              				<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>OTHER TEHSIL</label>
						                </div>
						               <div class="col-md-8">
					                  <input type="text" id="perm_home_addr_tehsil_other" name="per_home_addr_tehsil_other" 
					                   class="form-control autocomplete" autocomplete="off"maxlength="50" onkeypress="return onlyAlphabets(event);">				               
					                </div>
						            </div>
	              			</div>
	              	</div>
	              			

	              		
	              				<div class="col-md-12">	
	              			<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Village/Town/City</label>
						                </div>
						                <div class="col-md-8">
											<input type="text" id="perm_home_addr_village" name="per_home_addr_village" 
											onkeypress="return onlyAlphabets(event);" class="form-control autocomplete" autocomplete="off"  maxlength="50">				               
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Pin</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="perm_home_addr_pin" name="per_home_addr_pin" 
						                   class="form-control autocomplete" autocomplete="off"  onkeypress="return isNumber0_9Only(event)" maxlength="6"> 						                   
						                </div>
						            </div>
	              				</div>
	              			</div>
	              					<div class="col-md-12">	
	              			<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Nearest Railway Station</label>
						                </div>
						                <div class="col-md-8">
						                 <input type="text" id="perm_home_addr_rail" name="per_home_addr_rail" onkeypress="return onlyAlphabets(event);"
						                 class="form-control autocomplete" autocomplete="off" maxlength="100"> 						                     
						                <input type="hidden" id="non_addr_ch_id" name="non_addr_ch_id" class="form-control autocomplete" autocomplete="off" value="0">
						               <input type="hidden" id="addr_pending_ch_id" name="addr_pending_ch_id" class="form-control autocomplete" autocomplete="off" value="0">
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						             <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Is  Rural /Urban/Semi Urban</label>
						                </div>
						                <div class="col-md-8">
						     <label for="lbl_per_home_addr_rural">
							 <input type="radio" id="perm_home_addr_rural" name="per_home_addr_rural_urban" value="rural" > Rural</label> 
					    	 <label for="lbl_per_home_addr_urban" >
							 <input type="radio" id="perm_home_addr_urban" name="per_home_addr_rural_urban" value="urban" > Urban</label> 
					    	 <label for="lbl_per_home_addr_semi_urban">
							 <input type="radio" id="perm_home_addr_semi_urban" name="per_home_addr_rural_urban" value="semi_urban" >Semi Urban</label> 
						                </div>
						            </div>
	              				</div> 	
	              				</div>
	              		<div class="col-md-12">	  
	            			<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Border Area</label>
						                </div>
						                <div class="col-md-8">
						                	 <label for="border_area">
							 <input type="radio" id="per_border_area" name="border_area" value="yes"   >Yes</label> 
					    	 <label for="border_area1"> 
				      		 <input type="radio" id="per_border_area1" name="border_area" value="no" > No</label>
						</div>
						                </div>
						            </div>
	              				</div>
							</div>
						<div class="card-footer" align="center" class="form-control">
		              		 <input type="button" name="save" class="btn btn-primary btn-sm" value="Update" onclick=" non_effect_save();" /> 	              
			            </div>  
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>



</c:if>

<c:if test="${ChangeOfPosting!=0}">
	<!-- START CONTACT DETAILS -->
   <form id="form_posting">
		<div class="card">
			<div class="panel-group" id="accordion1">
				<div class="panel panel-default" id="post_in_final_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title lightblue" id="post_in_final_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion1" href="#" id="post_in_final" onclick="divN(this)"
								><b>Update POSTING IN/OUT</b></a>
						</h4>
					</div>
					<div id="collapse1post_in_final" class="panel-collapse collapse">
			            <div class="card-body card-block">
			          
						 
							<div class="row">
	              	
<!-- 				<div class="col-md-12"> -->
<!-- 					<div class="col-md-4"> -->
<!-- 						<div class="row form-group"> -->
<!-- 							<div class="col-md-5"> -->
<!-- 								<strong	style="color: red;">* </strong><label class=" form-control-label"> Personal No </label> -->
<!-- 							</div> -->
<!-- 							<div class="col-md-7"> -->
<%-- 								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />  --%>
<%-- 								<input type="hidden" id="id" name="id" class="form-control autocomplete" autocomplete="off" value="${updateid}"/> --%>
<%-- 								<input type="hidden" id="service_category" name="service_category" class="form-control autocomplete" autocomplete="off" value="${service_category}"/> --%>
<!-- 								<input type="hidden" id="personnel_no" name="personnel_no" class="form-control autocomplete" autocomplete="off" readonly="true" onchange="personal_number()"/> -->
<!--  								<label id="personnel_nolbl"></label> -->
<!--  							</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 					<div class="col-md-4"> -->
<!-- 						<div class="row form-group"> -->
<!-- 							<div class="col-md-4"> -->
<!-- 								<strong	style="color: red;">* </strong><label class=" form-control-label"> Name </label> -->
<!-- 							</div> -->
<!-- 							<div class="col-md-8"> -->
<!-- 								<input type="hidden" name="inpersonnel_no3v" id="inpersonnel_no3v"/>		 -->
<!-- 			                	<input type="hidden" name="inrank3v" id="inrank3v"/>		 -->
<!-- 			                	<input type="hidden" name="into_sus_no3v" id="into_sus_no3v"/>		 -->
<!-- 			                	<input type="hidden" name="infrom_sus_no_out3v" id="infrom_sus_no_out3v"/>		 -->
<!-- 			                	<input type="hidden" name="intype3v" id="intype3v"/>	 -->
<!-- 			                	<input type="hidden" name="instatus3v" id="instatus3v" value="0" /> -->
<!-- 			                	<input type="hidden" name="inservice_category3v" id="inservice_category3v"/>	 -->
<!-- 								<label id="name"></label> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 				 	<div class="col-md-4"> -->
<!-- 							<div class="row form-group"> -->
<!-- 								 <div class="col-md-4"> -->
<!-- 									<strong	style="color: red;">* </strong><label class=" form-control-label"> Rank </label> -->
<!-- 								</div> -->
<!-- 								<div class="col-md-8"> -->
<!-- 									<label id="rank"></label> -->
<!-- 									<input type="hidden" id="rank1" name="rank1" class="form-control autocomplete" autocomplete="off"> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 				</div> -->
		 				
				<div class="col-md-12">
					  <div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<strong	style="color: red;">* </strong><label class=" form-control-label"> From SUS No </label>
								</div>
								<div class="col-md-8">
									<label id="from_sus_no" name="from_sus_no"></label> 
										<input type="hidden" id="update_id" name="update_id" class="form-control autocomplete" autocomplete="off" />
									<input type="hidden" id="comm_id" name="comm_id" value="0" class="form-control autocomplete" autocomplete="off"/>
									<input type="hidden" id="census_id" name="census_id" value="0"  class="form-control autocomplete" autocomplete="off"/>
									<input type="hidden" id="dt_tos_pre" name="dt_tos_pre" value="" class="form-control autocomplete" autocomplete="off"/>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
								<strong	style="color: red;">* </strong>	<label class=" form-control-label"> From Unit Name </label>
 								</div>
 								<div class="col-md-8"> 
									 <label id="from_unit_name"></label> 									 
 								</div> 
							</div>
						</div>
					</div>					
					<div class="col-md-12">
					  <div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<strong	style="color: red;">* </strong><label class=" form-control-label"> To SUS No </label>
								</div>
								<div class="col-md-8"> 
									 <label id="to_sus_no"></label> 
 								</div> 
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
								<strong	style="color: red;">* </strong>	<label class=" form-control-label"> To Unit Name</label>
 								</div>
 								<div class="col-md-8"> 
									 <label id="to_unit_name"></label> 
 								</div> 
							</div>
						</div>
					</div>	
					<div class="col-md-12" style="display: none">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<strong	style="color: red;">* </strong><label class=" form-control-label"> Appointment </label>
								</div>
								<div class="col-md-8">
								 <label id="lbapp_name" name="lbapp_name"> </label>
									 <select name="app_name" id="app_name" onchange="appNamechng();" style="display: none" class="form-control ">  
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getTypeofAppointementList}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach> 
									</select>
								</div>
							</div>
						</div>							 
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<strong	style="color: red;">* </strong><label class=" form-control-label"> Date of Appointment </label>
								</div>
								<div class="col-md-8">
								 	<label id="app_dt"> </label>							
								</div>
							</div>
						</div>
				 		</div> 			
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<strong	style="color: red;">* </strong><label class=" form-control-label"> Date of TOS </label>
								</div>
								<div class="col-md-8">
									<input type="text" name="dt_of_tos" id="dt_of_tos" maxlength="10" value="DD/MM/YYYY" onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
										onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
										onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" />
								</div>
							</div>
						</div>
						 <div class="col-md-6" id=""  >
							<div class="row form-group">
								<div class="col-md-4">
									<strong style="color: red;">  </strong><label class=" form-control-label">Unit Description </label>
								</div>
								<div class="col-md-8">
									<label id="unit_description"> </label>
								</div>
							</div>
						</div>
					</div>
					
					<div class="col-md-12"  id="stats" style="display: none">
						 <div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<strong style="color: red;">  </strong><label class=" form-control-label">T Status </label>
								</div>
								<div class="col-md-8">
									<label id="t_status_lbl"> </label>
								</div>
							</div>
						</div>
					</div>
	              				
							</div>
							<div class="card-footer" align="center" class="form-control">
		              		 <input type="button" class="btn btn-primary btn-sm" value="Update" onclick="posting_in_edit_fn();">		              
			            </div> 
						</div>
					</div>
				</div>
			</div>
		</div>
		</form>
	<!-- END CONTACT DETAILS -->


</c:if>




</div>

	 <!-- /////////// Change_Rank_Form //////////////// -->
	<c:url value="Change_Of_RankUrl" var="Change_Of_RankUrl" />
<form:form action="${Change_Of_RankUrl}" method="post" id="Change_Rank_Form" name="Change_Rank_Form"  target="result">
<input type="hidden" name="rank_comm_id" id="rank_comm_id" value="0"/>
 <input type="hidden" name="rank_census_id" id="rank_census_id" value="0"/> 
</form:form>
	  <!-- /////////// Change_Name_Form //////////////// -->
<c:url value="Change_In_NameUrl" var="Change_In_NameUrl" />
<form:form action="${Change_In_NameUrl}" method="post" id="Change_Name_Form"
	name="Change_Name_Form" modelAttribute="id" target="result">
	<input type="hidden" name="name_comm_id" id="name_comm_id" value="0" />
	<input type="hidden" name="name_census_id" id="name_census_id" value="0" />
</form:form>
<!-- ///////////////////////CHANGE IN APPOINTMENT///////////// -->
<c:url value="Change_In_Appointment_Url_xml" var="Change_In_Appointment_Url_xml" />
<form:form action="${Change_In_Appointment_Url_xml}" method="post" id="Change_Appointment_Form"
	name="Change_Appointment_Form" modelAttribute="id" target="result">
	<input type="hidden" name="appointment_comm_id" id="appointment_comm_id" value="0" />
</form:form>

<!-- ///////////////////////CHANGE IN MEDICLE DETAILS///////////// -->
<c:url value="Change_In_MedicleUrl" var="Change_In_MedicleUrl" />
<form:form action="${Change_In_MedicleUrl}" method="post" id="Change_In_Medicle_Form" name="Change_In_Medicle_Form"  target="result">
<input type="hidden" name="cim_comm_id" id="cim_comm_id" value="0"/>
 <input type="hidden" name="cim_census_id" id="cim_census_id" value="0"/> 
</form:form>
  
  
<!--   ///////////////////////////CDA ACCOUNT////////////////////////////////// -->
	<c:url value="Popup_CDAUrl" var="Popup_CDAURL" />
<form:form action="${Popup_CDAURL}" method="post" id="popup_cda" name="popup_cda" modelAttribute="id" target="result">
	<input type="hidden" name="comm_id1" id="comm_id1" value="0" />
</form:form>

<!-- /////////// Update_Award_Medal_Form //////////////// -->
<c:url value="Update_Awards_MedalUrl_xml" var="Update_Awards_MedalUrl" />
<form:form action="${Update_Awards_MedalUrl}" method="post" id="update_awards_medalForm"
	name="update_awards_medalForm" target="result">
 <input type="hidden" name="am_comm_id" id="am_comm_id" value="0" />  
</form:form>

<!-- //////////////////CHANGE IN NOK//////////////////// -->
<c:url value="Change_NOKUrl_xml" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="nok_updateForm"
	name="nok_updateForm" target="result">
<input type="hidden" name="nok_comm_id" id="nok_comm_id" value="0" /> 
</form:form>

<!-- /////////// Update_Child_Details_Form //////////////// -->
<c:url value="Update_Child_dtlUrl_XML" var="Update_Child_dtlUrl" />
<form:form action="${Update_Child_dtlUrl}" method="post" id="update_child_dtlForm"
	name="update_child_dtlForm" target="result">
 <input type="hidden" name="uc_comm_id" id="uc_comm_id" value="0" />  
<input type="hidden" name="uc_census_id" id="uc_census_id" value="0" />
</form:form>

<!-- /////////// Change_In_Contact_Details //////////////// -->
<c:url value="Change_In_Contact_DetailsUrl_XML" var="Change_In_Contact_DetailsUrl" />
<form:form action="${Change_In_Contact_DetailsUrl}" method="post" id="Change_Contact_Details_Form"
	name="Change_Contact_Details_Form" modelAttribute="id" target="result">
	<input type="hidden" name="cd_comm_id" id="cd_comm_id" value="0" />
</form:form>
<!-- /////////// Non_Effective_Status_DetailsUrl //////////////// -->
<c:url value="Non_Effective_Status_DetailsUrl_xml" var="Non_Effective_Status_DetailsUrl" />
<form:form action="${Non_Effective_Status_DetailsUrl}" method="post" id="Non_Effective_Status_Form" name="Non_Effective_Status_Form"  target="result">
<input type="hidden" name="nes_comm_id" id="nes_comm_id" value="0"/>
</form:form>


<script type="text/javascript">
$(document).ready(function() {
	jQuery(function($){ 
		datepicketDate('r_date_of_authority');
		datepicketDate('date_of_rank');
		 datepicketDate('p_date_of_authority');  		
		 datepicketDate('appt_date_of_authority');  		
		 datepicketDate('na_date_of_authority');  		
		 datepicketDate('date_of_appointment');  		
		 datepicketDate('inter_arm_date_of_authority');  		
		 datepicketDate('inter_arm_with_effect_from');  		
		 datepicketDate('coc_date_of_authority');  		
		 datepicketDate('eff_coc_date_of_seniority');  		
		 datepicketDate('coc_date_of_seniority');  		
		 datepicketDate('coc_date_of_permanent_commission'); 
		 
	     datepicketDate('madical_date_of_authority');		 
		 datepicketDate('s_from_date1');
		 datepicketDate('s_to_date1');		 
		 datepicketDate('h_from_date1');
		 datepicketDate('h_to_date1');		 
		 datepicketDate('a_from_date1');
		 datepicketDate('a_to_date1');		 
		 datepicketDate('p_from_date1');
		 datepicketDate('p_to_date1');		 
		 datepicketDate('e_from_date1');
		 datepicketDate('e_to_date1');
		 datepicketDate('change_in_name_date');
		 datepicketDate('change_in_rel_date');		 
		 datepicketDate('1bx_from_date');
		 datepicketDate('1bx_to_date');
		 datepicketDate('date_authority');
		 
		// datepicketDate('date_of_birth1');//kajal
		 $( "#1bx_to_date" ).datepicker( "option", "maxDate", null);		
		 $( "#s_to_date1" ).datepicker( "option", "maxDate", null);
		 $( "#h_to_date1" ).datepicker( "option", "maxDate", null);
		 $( "#a_to_date1" ).datepicker( "option", "maxDate", null);
		 $( "#p_to_date1" ).datepicker( "option", "maxDate", null);
		 $( "#e_to_date1" ).datepicker( "option", "maxDate", null);
		 // medical ends
		 
		 datepicketDate('r_date_of_authority');            
		 datepicketDate('date_of_rank');
		 datepicketDate('na_date_of_authority');
		 datepicketDate('appt_date_of_authority');
		 datepicketDate('date_of_appointment');
		 datepicketDate('issue_dt'); 
		 datepicketDate('rel_date_of_authority'); 
		 datepicketDate('sib_date_of_birth1');
		 datepicketDate('sib_adop_date1');
		 datepicketDate('date_authority');        
		 datepicketDate('addr_date_authority');  
		 datepicketDate('language_date_of_authority');  
		 datepicketDate('qualification_date_of_authority'); 
		 datepicketDate('promo_exam_date_of_authority'); 
		 datepicketDate('from_dt1'); 
		 datepicketDate('to_dt1'); 
		 datepicketDate('awardsNmedal_date_of_authority1');
		 datepicketDate('date_of_award1');
		 datepicketDate('date_of_casuality');
		 datepicketDate('batnpc_date_of_authority');
		 datepicketDate('date_of_informing');
		 datepicketDate('award_dt');
		 datepicketDate('inter_arm_date_of_authority');
		 datepicketDate('inter_arm_with_effect_from');
		 datepicketDate('coc_date_of_authority');
		 datepicketDate('coc_date_of_permanent_commission');
		 datepicketDate('coc_date_of_seniority');
		 datepicketDate('eff_coc_date_of_seniority');
		 datepicketDate('eoc_authority_date');
		 datepicketDate('eoc_from');
		 datepicketDate('eoc_to');
		 $( "#eoc_to" ).datepicker( "option", "maxDate", null);
		 datepicketDate('sec_date_of_authority');
		 datepicketDate('secondment_effect');
		 datepicketDate('date_of_authority_non_ef');
		 datepicketDate('date_of_non_effective');
		 datepicketDate('marriage_date_of_authority');
		 datepicketDate('marriage_date');
		 datepicketDate('marriage_date_of_birth');
		 datepicketDate('divorce_date');
		 
		 datepicketDate('army_course_date_of_authority');
		 datepicketDate('army_course_start_date1');
		 datepicketDate('army_course_date_of_completion1');
		 datepicketDate('disi_authority_date');
		 
		 
		 datepicketDate('ta_date_of_authority');
		 datepicketDate('ta_date');
		 //medical
		 
	     datepicketDate('madical_date_of_authority');
		 
		 datepicketDate('s_from_date1');
		 datepicketDate('s_to_date1');
		 
		 datepicketDate('h_from_date1');
		 datepicketDate('h_to_date1');
		 
		 datepicketDate('a_from_date1');
		 datepicketDate('a_to_date1');
		 
		 datepicketDate('p_from_date1');
		 datepicketDate('p_to_date1');
		 
		 datepicketDate('e_from_date1');
		 datepicketDate('e_to_date1');
		 datepicketDate('change_in_name_date');
		 datepicketDate('change_in_rel_date');
		 
		 datepicketDate('1bx_from_date');
		 datepicketDate('1bx_to_date');
		 
		 
		 
		// datepicketDate('date_of_birth1');//kajal
		 $( "#1bx_to_date" ).datepicker( "option", "maxDate", null);
		
		 $( "#s_to_date1" ).datepicker( "option", "maxDate", null);
		 $( "#h_to_date1" ).datepicker( "option", "maxDate", null);
		 $( "#a_to_date1" ).datepicker( "option", "maxDate", null);
		 $( "#p_to_date1" ).datepicker( "option", "maxDate", null);
		 $( "#e_to_date1" ).datepicker( "option", "maxDate", null);
		 
		 
		 ///post in 
		 datepicketDate('app_dt');
		 datepicketDate('dt_of_tos');
		 
		 
	});
	
	
	
	
	
	
	
 	$('#comm_id').val('${comm_id_edit}');
  	$('#personnel_no').val('${personnel_no_edit}');
  	$('#census_id').val('${census_id_edit}');
  	 $("input#personnel_no").attr('readonly', true);
  	personal_number();
  	$("#submit_a").hide();
  	
  	<c:if test="${ChangeOfNoneffective != 0}">
  	getNon_effective();
	get_non_eff_address_details();
	get_changeaddress_details();
	</c:if>
  	 <c:if test="${ChangeOfContact != 0}">
 	get_cda_acnt_no();
	</c:if>
	  	 <c:if test="${ChangeOfMarital != 0}">
 		$("#spouse_quali_radioDiv").hide();
				getfamily_marriageDetails();
				getSpouseQualificationData();
	</c:if>

 	<c:if test="${ChangeOfChildDetails != 0}">
 	m_children_Details();
	</c:if>
	
	<c:if test="${ChangeOfNOK != 0}"> 
	get_nokaddress_details();
	</c:if>
	
	<c:if test="${ChangeOfrank != 0}">
	get_rank();
	</c:if>
	
	<c:if test="${ChangeOfmedal != 0}"> 
	getawardsNmedals();
	colAdj("awardsNmedal_table");
	</c:if>
	
	<c:if test="${ChangeOfCDA != 0}"> 
	get_cda();
	</c:if>
	
	<c:if test="${ChangeOfName != 0}"> 
	get_change_of_name();
	</c:if>
	
	<c:if test="${ChangeOfAppointment != 0}"> 
	get_Appointment();
	</c:if>
	
	<c:if test="${ChangeOfMedCat != 0}"> 
	getsShapeDetails();
	gethShapeDetails();
	getaShapeDetails();
	getpShapeDetails();
	geteShapeDetails();
	getcCopeDetails();
	getoCopeDetails();
	getpCopeDetails();
	geteCopeDetails();
	
	</c:if>
	<c:if test="${ChangeOfPosting != 0}"> 
	get_post_in_data();
	</c:if>
	
	
	
});

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
				get_PersonalNo();
			}
		}	
		else if (obj.id == "e_final") {
			if(!hasC){
				get_cda();
			}
		}
		else if (obj.id == "k_final") {
			if(!hasC){
				get_Course();
			}
		}
		else if (obj.id == "p_final") {
			if(!hasC){
				get_Gender();
			}
		}
		else if (obj.id == "r_final") {
			if(!hasC){
				get_Commission();
			}
		}	
		else if (obj.id == "x_final") {
			if(!hasC){
				get_Birth();
			}
		}
		else if (obj.id == "h_final") {
			if(!hasC){
				get_nokaddress_details();
			}
		}
		else if (obj.id == "b_final") {
			if(!hasC){
				get_change_of_name();
			}
		}
		 else if (obj.id == "r1_final") {
				if(!hasC){
					get_rank();
				}
			}
		 else if (obj.id == "u_final") {
				if(!hasC){
 					getawardsNmedals();
					colAdj("awardsNmedal_table");
				}
			}
				else if (obj.id == "j_final") {
			if(!hasC){
				get_cda_acnt_no();
			}
		}
				else if (obj.id == "post_in_final") {
					if(!hasC){
						get_post_in_data();
					}
				}
			else if (obj.id == "f_final") {
			if(!hasC){
				$("#spouse_quali_radioDiv").hide();
				getfamily_marriageDetails();
				getSpouseQualificationData();
			}
		}
			else if (obj.id == "bb_final") {
				if(!hasC){
					getNon_effective();
					get_non_eff_address_details();
					get_changeaddress_details();
				}
			}
		
		}
function SetMin(){
	if ($("input#comm_date").val() != "") {
		var comm_dt = $("input#comm_date").val();
		setMinDate('date_of_seniority', comm_dt);
		setMinDate('date_of_tos', comm_dt);
		setMinDate('date_of_rank', comm_dt);
	}
	if ($("input#date_of_commission").val() != "") {
		var comm_dt = ParseDateColumncommission($("input#date_of_commission").val());
		setMinDate('date_of_seniority', comm_dt);
		setMinDate('date_of_tos', comm_dt);
		setMinDate('date_of_rank', comm_dt);
	}
}
		
</script>


<script>

/* Personal No */

function personal_number()
{
	
	if($("input#personnel_no").val()==""){
		alert("Please select Personal No");
		$("input#personnel_no").focus();
		return false;
	}
	else{
		$("#div_update_data").show();
	}
	 var personnel_no = $("input#personnel_no").val();
	 $.post('GetPersonnelNoDataForComm?' + key + "=" + value,{ personnel_no : personnel_no },function(j) {
		 if(j!=""){
			
	    	$("#comm_id").val(j[0][0]);
	    	var comm_id =j[0][0]; 
	    	 $.post('GetCommissionDataApprove?' + key + "=" + value,{ comm_id : comm_id },function(k) {
	    		 if(k.length > 0)
	    		 {
	    			  $('#marital_event').val('0');
	    			  $('#marriage_div').show();
	    			   $("#cadet_name").text(k[0][1]);	    			 
	    			 	$("#comm_date").val(ParseDateColumncommission(k[0][9]));
	    			 	SetMin();
	    		    	$("#p_rank").text(k[0][2]);
	    		    	$("#hd_p_rank").val(k[0][2]);
	    		    	if(k[0][3]==null){
	    		    		$("#p_app").text('');
	    		    	}else{
	    		    		$("#p_app").text(k[0][3]);
	    		    	}
	    		    	if(k[0][4]==null){
	    		    		$("#p_app_dt").text("");    
	    		    	}
	    		    	else{
	    		    		 $("#p_app_dt").text(convertDateFormate(k[0][4]));  
	    		    	}
	    		    	if(k[0][5]==null){
	    		    		$("#p_tos").text("");    
	    		    	}
	    		    	else{
	    		    		 $("#p_tos").text(convertDateFormate(k[0][5]));  
	    		    	}
	    		    	$("#tos_date").val(ParseDateColumncommission(k[0][5]));
	    		    	$("#app_sus_no").text(k[0][6]);
	    		    	
	    		    
	    		    	$("#app_parent_arm").text(k[0][7]);
	    		    	$("#p_cmd").text(k[0][12]);
	    		    	 if(k[0][6] == "GORKHA" || k[0][6] == "INFANTRY"){
	    		 			$("#reg_div").show();
	    		 		}
	    		 	  else
	    		 		  {
	    		 		 $("#reg_div").hide();
	    		 		  }
	    		    	if(k[0][14]!=0){
	    		    		$('#inter_arm_regt_val').val(k[0][14]);
	    		    		$('#p_regt').text($( "#inter_arm_regt_val option:selected" ).text());
	    		    	}
	    		    	else{
	    		    		$('#p_regt').text("");
	    		    	}
	    		    	var sus_no =k[0][6];
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
	 		
	 		$.post('GetServingStatus?' + key + "=" + value,{ comm_id : comm_id },function(k) {
	    		 if(k.length > 0)
	    		 {
	    		    	$("#p_serving_status").text(k[0][2]);
	    		 }
	   });
	 		$.post('GETtenure_date?' + key + "=" + value,{ comm_id : comm_id },function(k) {
	 			
		   		 if(k.length > 0){
			   			$('#tenure_dt').val(k[0][1]);
// 			   			setMaxDate('date_of_tos', k[0][1]);
		   		 }	 
		   		 
		       });
		 }
	    	
	   });

	 $("input#personnel_no").attr('readonly', true);
}
$("input#personnel_no").keyup(function(){
	var personel_no = this.value;
		 var susNoAuto=$("#personnel_no");
		  susNoAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        	type: 'POST',
			    	url: "getpersonnel_noListFORComm?"+key+"="+value,
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
		        	  alert("Please Enter Approved Personal No");
		        	  document.getElementById("personnel_no").value="";
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
<script type="text/javascript">
<!-- //-->
function getpersonnelNo(){
	 var comm_id = '${id}';
	 $.post('getPersonalNOData?' + key + "=" + value, {comm_id:comm_id}, function(j){
		 if(j!=""){
					 $('#p_authority').val(j[0].authority);
					 $('#p_date_of_authority').val(convertDateFormate(j[0].date_of_authority));
					 $('#persnl_no1').val(j[0].new_personal_no);
					 $('#p_r_id').val(j[0].id);
						
		 }
	 });
	} 
function getcadet(){
	 var comm_id = '${id}';
	 $.post('getcadetData?' + key + "=" + value, {comm_id:comm_id}, function(j){
		 if(j!=""){
					 $('#c_authority').val(j[0].authority);
					 $('#c_date_of_authority').val(convertDateFormate(j[0].date_of_authority));
					 $('#cadet_no').val(j[0].cadet_no);
					 $('#c_id').val(j[0].id);
		 }
	 });
	}
function getcourseno(){
	 var comm_id = '${id}';
	 $.post('getCourseData?' + key + "=" + value, {comm_id:comm_id}, function(j){
		 if(j!=""){
					 $('#co_authority').val(j[0].authority);
					 $('#co_date_of_authority').val(convertDateFormate(j[0].date_of_authority));
					 $('#batch_no').val(j[0].batch_no);
					 $('#course').val(j[0].course);
					 $('#course_val').val($( "#course option:selected" ).text());
					 $('#co_id').val(j[0].id);
		 }
	 });
	}
function getgender(){
	 var comm_id = '${id}';
	 $.post('getGenderData?' + key + "=" + value, {comm_id:comm_id}, function(j){
		 if(j!=""){
					 $('#g_authority').val(j[0].authority);
					 $('#g_date_of_authority').val(convertDateFormate(j[0].date_of_authority));
					 $('#gender').val(j[0].gender);
					 $('#genderlbl').val($( "#gender option:selected" ).text());
					 $('#g_id').val(j[0].id);
		 }
	 });
	}
function getcommison(){
	 var comm_id = '${id}';
	 $.post('getcommisonData?' + key + "=" + value, {comm_id:comm_id}, function(j){
		 if(j!=""){
			 $('#com_authority').val(j[0].authority);
			 $('#com_date_of_authority').val(convertDateFormate(j[0].date_of_authority));
			 $('#type_of_comm_granted').val(j[0].type_of_comm_granted);
			 $('#type_of_comm_grantedlbl').val($( "#type_of_comm_granted option:selected" ).text());
			 $('#date_of_commission').val(convertDateFormate(j[0].date_of_commission));
			 $('#com_id').val(j[0].id);
		 }
	 });
  } 
function getdob(){
	 var comm_id = '${id}';
	 $.post('getdobData?' + key + "=" + value, {comm_id:comm_id}, function(j){
		 if(j!=""){
			 $('#b_authority').val(j[0].authority);
			 $('#b_date_of_authority').val(convertDateFormate(j[0].date_of_authority));
			 $('#date_of_birth').val(convertDateFormate(j[0].date_of_birth));
			 $('#birth_id').val(j[0].id);
		 }
	 });
 }
function getarmservice(){
	 var comm_id = '${id}';
	 $.post('getarmsirviceData?' + key + "=" + value, {comm_id:comm_id}, function(j){
		 if(j!=""){
			 $('#arm_authority').val(j[0].authority);
			 $('#arm_date_of_authority').val(convertDateFormate(j[0].date_of_authority));
			 $('#parent_arm').val(j[0].parent_arm);
			 $('#parent_armlbl').val($( "#parent_arm option:selected" ).text());
			 $('#regiment').val(j[0].regiment)
			 $('#regimentlbl').val($( "#regiment option:selected" ).text());
			 $('#reg_id').val(j[0].id);
		 }
	 });
 } 
function getunit(){
	 var comm_id = '${id}';
	 $.post('getunitData?' + key + "=" + value, {comm_id:comm_id}, function(j){
		 if(j!=""){
			 $('#u_authority').val(j[0].authority);
			 $('#u_date_of_authority').val(convertDateFormate(j[0].date_of_authority));
			 $('#unit_sus_no').val(j[0].unit_sus_no);
			 $('#unit_posted_to').val(j[0].unit_post_to);
			 $('#date_of_tos').val(convertDateFormate(j[0].date_of_tos));
			 $('#unit_id').val(j[0].id);
		 }
	 });
}
</script>

<script>
function validate_personal_no(){

	if ($("select#persnl_no1").val() == "0") {
		alert("Please Select Personal No");
		$("select#persnl_no1").focus();
		return false;
			}
   if ($("input#persnl_no2").val() == "") {
		alert("Please Enter Personal No");
		$("input#persnl_no2").focus();
		return false;
	}
	    var formvalues=$("#form_perssonel").serialize();
		var p_r_id=$("#p_r_id").val();	
		var comm_id=$("#comm_id").val();	
		formvalues+="&p_r_id="+p_r_id+"&comm_id="+comm_id;
		 $.post('Personal_no_Action?' + key + "=" + value,formvalues, function(data){
			      
		          if(data=="update")
		        	  alert("Data Updated Successfully");
		          else if(parseInt(data)>0){
		        	  $("#p_r_id").val(data);	
		        	  alert("Data Saved SuccesFully")
		          }
		          else
		        	  alert(data)
		 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		  		});
}
function get_PersonalNo(){
	 var comm_id=$('#comm_id').val(); 
	  $.post('get_PersonalNo1?' + key + "=" + value, {comm_id:comm_id}, function(j){
			var v=j.length;
			if(j!=""){
				$('#p_authority').val(j[0].authority);
				var l = j[0].new_personal_no.length;
				$('#p_date_of_authority').val(ParseDateColumncommission(j[0].date_of_authority));
				$("#persnl_no1").val(j[0].new_personal_no.substring(0,l-6));
				$("#persnl_no2").val(j[0].new_personal_no.substring(l-6, l-1));
				$("#persnl_no3").val(j[0].new_personal_no.substring(l,l-1));
				
			}
		
	  });
}  
</script>

  
  <script>
/* ----------- Start Unit ---------------*/


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
	             $("#unit_posted_to").val(dec(enc,j[0]));   
	                 
	         }).fail(function(xhr, textStatus, errorThrown) {
	         });
			} 	     
		});	
	});
	
	
	 $("input#unit_posted_to").keypress(function(){
			var unit_name = this.value;
				 var susNoAuto=$("#unit_posted_to");
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
				        	  document.getElementById("unit_posted_to").value="";
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
<script>
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
 </script>
<script>
  

jQuery(function($){
	

	 datepicketDate('p_date_of_authority');  
	 datepicketDate('c_date_of_authority'); 
	 datepicketDate('co_date_of_authority');  
	 datepicketDate('g_date_of_authority'); 
	 
	 datepicketDate('com_date_of_authority'); 
	 datepicketDate('date_of_commission'); 
	 
	 datepicketDate('b_date_of_authority'); 
	 datepicketDate('date_of_birth');
	 
	 datepicketDate('arm_date_of_authority');
	 
	 datepicketDate('u_date_of_authority'); 
	 datepicketDate('date_of_tos');
	 
	 datepicketDate('rdate_of_authority'); 
	 datepicketDate('dt_rk');
	 });
	 
	 
function specialcharecterCadet(a)
{
    var iChars = "@#^&*$,.:;%!+_-[]";   
    var data = a.value;
    for (var i = 0; i < data.length; i++)
    {      
        if (iChars.indexOf(data.charAt(i)) != -1)
        {    
        alert ("This " +data.charAt(i)+" special characters not allowed.");
        a.value = "";
        return false; 
        } 
    }
    return true;
}
function validate_rank(){
	
	
	
	  if ($("#r_authority").val().trim()=="") {
			alert("Please Enter Authority.");
			$("#r_authority").focus();
			return false;
	 }
	 if ($("#rdate_of_authority").val() == "DD/MM/YYYY" || $("#rdate_of_authority").val().trim()=="") {
			alert("Please Enter Date of Authority.");
			$("#rdate_of_authority").focus();
			return false;
	 } 
	 
   if(getformatedDate($("input#comm_date").val()) > getformatedDate($("#rdate_of_authority").val())) {
			alert("Authority Date should be Greater than Commission Date");
			$("#rdate_of_authority").focus();
			return false;
	  }
		
	 if ($("#rank_type").val() == "0") {
			alert("Please Select Rank Type.");
			$("#rank_type").focus();
			return false;
	 } 
	 if ($("#rank").val() == "0") {
			alert("Please Select Rank.");
			$("#rank").focus();
			return false;
	 } 
	 if ($("#date_of_rank").val() == "DD/MM/YYYY" || $("#date_of_rank").val().trim()=="") {
			alert("Please Enter Date of Rank.");
			$("#date_of_rank").focus();
			return false;
	 }  
	 if(getformatedDate($("input#comm_date").val()) > getformatedDate($("#date_of_rank").val())) {
			alert("Date of Rank should be greater Or equal to Commissioning Date");
			$("#date_of_rank").focus();
			return false;
	  }
	var authority=$('#r_authority').val();
	var date_of_authority=$('#rdate_of_authority').val();
	var rank_type=$('#rank_type').val();
	var rank=$('#rank').val();
	var comm_date=$('#comm_date').val();
	var date_of_rank=$('#date_of_rank').val();
	var ch_r_id=$('#ch_r_id').val();
	var census_id_cr=$('#census_id').val();
	var comm_id_cr=$('#comm_id').val();
	
	   $.post('Change_of_Rank_action?' + key + "=" + value, {authority:authority,date_of_authority:date_of_authority,
		   rank_type:rank_type,rank:rank,date_of_rank:date_of_rank,comm_date:comm_date,census_id_cr:census_id_cr,comm_id_cr:comm_id_cr,ch_r_id:ch_r_id}, function(data){
		      
	          if(data=="update")
	        	  alert("Data Updated Successfully");
	          else if(parseInt(data)>0){
	        	 $('#ch_r_id').val(data);
	        	  alert("Data Saved Successfully")
	          } else
	        	  alert(data);
	 	  		}).fail(function(xhr, textStatus, errorThrown) {
	 	  			alert("fail to fetch")
	  		});
}
function get_rank(){

	 var comm_id=$('#comm_id').val();
	 $.post('get_rank1?' + key + "=" + value, {comm_id:comm_id}, function(j){
		 if(j!=""){
		
					 $('#r_authority').val(j[0].authority);
					 $('#rdate_of_authority').val(ParseDateColumncommission(j[0].date_of_authority));
					 $('#rank').val(j[0].rank);
					 $('#rank_type').val( j[0].rank_type );		
					 $('#date_of_rank').val(ParseDateColumncommission(j[0].date_of_rank));
					 $('#ch_r_id').val(j[0].id);
					 
					 
					 
					 
					 
		 }
	 });
	}	 

//************************************* START NON EFFECTIVE DETAIL*****************************//
function getNon_effective(){
	 var p_id=$('#census_id').val(); 
	 var comm_id=$('#comm_id').val(); 
	 var hd_rank=$('#hd_p_rank').val();
	 var i=0;
	 var lengthV=0;
	 $.ajaxSetup({
         async : false
 });
	  $.post('getNon_effective_xml?' + key + "=" + value, {p_id:p_id,comm_id:comm_id }, function(j){
			var v=j.length;
			lengthV=v;
			if(v!=0){
				$('#nf_id').val(j[i].id);
				$("#check_per_address").prop("checked", true);
				 $("#non_ef_authority").val(j[i].non_ef_authority);
				 $("#date_of_authority_non_ef").val(ParseDateColumncommission(j[i].date_of_authority_non_ef));
				 $("#cause_of_non_effective").val(j[i].cause_of_non_effective);
				 $("#date_of_non_effective").val(ParseDateColumncommission(j[i].date_of_non_effective));
			}
	  });
/* 	  if(lengthV==0){
	  $.post('getNon_effective_date?' + key + "=" + value, {comm_id:comm_id,hd_rank:hd_rank }, function(j){
			var v=j.length;
			if(v!=0){
				if(j[0][7]!=null){
					$("#date_of_non_effective").val(ParseDateColumncommission(j[0][7]));
				}
				else{
					$("#date_of_non_effective").val("");
				}
				
				 
			}
	  });
	  } */
}

function get_non_eff_address_details(){
	var census_id=$("#census_id").val();	
	var comm_id=$("#comm_id").val();
	
	if($('#nf_id').val()!="" && $('#nf_id').val()!="0"){
	$.post('address_details_GetData?' + key + "=" + value,{census_id:census_id,comm_id:comm_id }, function(j){
		var v=j.length;
		if(v!=0){
			$("#perm_home_addr_country").val(j[0].permanent_country);
			fn_perm_home_addr_Country();
			$("#perm_home_addr_state").val(j[0].permanent_state);
			fn_prem_domicile_state();
			$("#perm_home_addr_district").val(j[0].permanent_district);
			fn_prem_domicile_district();
			$("#perm_home_addr_village").val(j[0].permanent_village);
			$("#perm_home_addr_tehsil").val(j[0].permanent_tehsil);

			if(j[0].permanent_pin_code=="0")
				{
				$("#perm_home_addr_pin").val("000000");
				}
			else{
				$("#perm_home_addr_pin").val(j[0].permanent_pin_code);
			}
			
			
			
			
			$("#perm_home_addr_rail").val(j[0].permanent_near_railway_station);
			 var permanent= j[0].permanent_rural_urban_semi;
            if(permanent == "rural"){
                    $("#perm_home_addr_rural").prop("checked", true);
             } 
            if(permanent == "urban"){
                    $("#perm_home_addr_urban").prop("checked", true);
             }
            if(permanent == "semi_urban")
            {
                    $("#perm_home_addr_semi_urban").prop("checked", true);
             }  
            var br= j[0].permanent_border_area;
            if(br == "yes"){
                    $("#per_border_area").prop("checked", true);
             } 
            if(br == "no"){
                    $("#per_border_area1").prop("checked", true);
             } 
          
            $("#perm_home_addr_country_other").val(j[0].per_home_addr_country_other);
			$("#perm_home_addr_state_other").val(j[0].per_home_addr_state_other);
			$("#perm_home_addr_district_other").val(j[0].per_home_addr_district_other);
			$("#perm_home_addr_tehsil_other").val(j[0].per_home_addr_tehsil_other);
			
			onPermHomeAddrCountry();
				onPermHomeAddrState();
				onPermHomeAddrDis();
				onPermHomeAddrTeh();
			$("#non_addr_ch_id").val(j[0].id);
		}
	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
	}
}


function non_effect_save(){
	
	var comm_id=$('#comm_id').val();
	var census_id=$('#census_id').val();
	var status=$('#status').val();
	var nf_id=$('#nf_id').val();
	var comm_date=$('#comm_date').val(); 
	 
	var formdata=$('#form_non_effective').serialize();
	 
	formdata+="&census_id="+census_id+"&comm_id="+comm_id+"&comm_date="+comm_date;	
 
	if ($("input#non_ef_authority").val().trim()=="") {
			alert("Please Enter Authority");
			$("input#non_ef_authority").focus();
			return false;
		}
		 if ($("input#date_of_authority_non_ef").val().trim()=="" || $("input#date_of_authority_non_ef").val() == "DD/MM/YYYY") {
			alert("Please Select Date of Authority ");
			$("input#date_of_authority_non_ef").focus();
			return false;
		} 
		 if(getformatedDate($("input#comm_date").val()) > getformatedDate($("#date_of_authority_non_ef").val())) {
				alert("Authority Date should be Greater than Commission Date");
				return false;
		  }

		if ($("select#cause_of_non_effective").val() == 0) {
			alert("Please Select Cause of Non Effective ");
			$("#cause_of_non_effective").focus();
			return false;
		}
		 if ($("input#date_of_non_effective").val().trim()=="" || $("input#date_of_non_effective").val() == "DD/MM/YYYY") {
			alert("Please Select Date of Non Effective ");
			$("input#date_of_non_effective").focus();
			return false;
		} 

		if ($("select#perm_home_addr_country").val() == "0") {
			alert("Please select Country");
			$("select#perm_home_addr_country").focus();
			return false;
		}
		var text9 = $("#perm_home_addr_country option:selected").text();
		if(text9 == "OTHERS" && $("input#perm_home_addr_country_other").val().trim()==""){
			alert("Please Enter Country Other");
			$("input#perm_home_addr_country_other").focus();
			return false;
		}
		if ($("select#perm_home_addr_state").val() == "0") {
			alert("Please Select State");
			$("select#perm_home_addr_state").focus();
			return false;
		}
		var text10 = $("#perm_home_addr_state option:selected").text();
		if(text10 == "OTHERS" && $("input#perm_home_addr_state_other").val().trim()==""){
			alert("Please Enter State Other");
			$("input#perm_home_addr_state_other").focus();
			return false;
		}
		
		if ($("select#perm_home_addr_district").val() == "0") {
			alert("Please Select  District");
			$("select#perm_home_addr_district").focus();
			return false;
		}
		var text11 = $("#perm_home_addr_district option:selected").text();
		if(text11 == "OTHERS" && $("input#perm_home_addr_district_other").val().trim()==""){
			alert("Please Enter District Other");
			$("input#perm_home_addr_district_other").focus();
			return false;
		}
		if ($("select#perm_home_addr_tehsil").val() == "0") {
			alert("Please Enter Tehsil");
			$("select#perm_home_addr_tehsil").focus();
			return false;
		}
		var text12 = $("#perm_home_addr_tehsil option:selected").text();
		if(text12 == "OTHERS" && $("input#perm_home_addr_tehsil_other").val().trim()==""){
			alert("Please Enter Tehsil Other");
			$("input#perm_home_addr_tehsil_other").focus();
			return false;
		}
		if ($("input#perm_home_addr_village").val().trim()=="") {
			alert("Please Enter Village/Town/City");
			$("input#perm_home_addr_village").focus();
			return false;
		}
		if ($("input#perm_home_addr_pin").val().trim()=="") {
			alert("Please Enter Pin");
			$("input#perm_home_addr_pin").focus();
			return false;
		}
		if ($("input#perm_home_addr_pin").val().trim().length!=6) {
			alert("Please Enter Valid Pin");
			$("input#perm_home_addr_pin").focus();
			return false;
		}
		if ($("input#perm_home_addr_rail").val().trim()=="") {
			alert("Please Enter Nearest Railway Station");
			$("input#perm_home_addr_rail").focus();
			return false;
		}
		var a = $('input:radio[name=per_home_addr_rural_urban]:checked').val();
		if (a == undefined) {
			alert("Please select Is Rural /Urban/Semi Urban ");
			return false;
		}
		var b = $('input:radio[name=border_area]:checked').val();
		if (b == undefined) {
			alert("Please select Border Area ");
			return false;
		}

		$.post('non_effectiveAction?' + key + "=" + value, formdata,
				function(data) {
			//alert(data);
					if (data.error==undefined){
					
						$('#nf_id').val(data.nf_id);
						$('#non_addr_ch_id').val(data.add_id);
						$("#addr_ch_id").val(data.add_id);
						
						if(data.msg!=undefined)
							alert("Data Saved/Update Successfully "+data.msg)
						else
							alert("Data Saved/Update Successfully")
					} else
						alert(data.error);

				}).fail(function(xhr, textStatus, errorThrown) {
			alert("fail to fetch")
		});
	}


function per_address() {
	 
	
	var census_id = $("#census_id").val();
	var comm_id = $("#comm_id").val();
	if ($("#check_per_address").prop('checked') == true) {
		//$("#perm_add_div").show();
		$.post('getPerAddressDataStatus1?' + key + "=" + value, {
			census_id : census_id,
			comm_id : comm_id
		}, function(j) {
			if (j != "") {
	 
				$("#perm_home_addr_country").val(j[0].permanent_country);
				fn_perm_home_addr_Country();
				$("#perm_home_addr_state").val(j[0].permanent_state);
				fn_prem_domicile_state();
				$("#perm_home_addr_district").val(j[0].permanent_district);
				fn_prem_domicile_district();
				$("#perm_home_addr_village").val(j[0].permanent_village);
				$("#perm_home_addr_tehsil").val(j[0].permanent_tehsil);
				$("#perm_home_addr_pin").val(j[0].permanent_pin_code);
				$("#perm_home_addr_rail").val(j[0].permanent_near_railway_station);
				
				if(j[0].per_home_addr_country_other!=null )
					$("#perm_home_addr_country_other").val(j[0].per_home_addr_country_other);
					
				if(j[0].per_home_addr_state_other!=null)
					$("#perm_home_addr_state_other").val(j[0].per_home_addr_state_other);
				if(j[0].per_home_addr_district_other!=null)
					$("#perm_home_addr_district_other").val(j[0].per_home_addr_district_other);	
				if(j[0].per_home_addr_tehsil_other!=null)
					$("#perm_home_addr_tehsil_other").val(j[0].per_home_addr_tehsil_other);
			
				var permanent = j[0].permanent_rural_urban_semi;
				if (permanent == "rural") {
					$("#perm_home_addr_rural").prop("checked", true);
				}
				if (permanent == "urban") {
					$("#perm_home_addr_urban").prop("checked", true);
				}
				if (permanent == "semi_urban") {
					$("#perm_home_addr_semi_urban").prop("checked", true);
				}
				var br = j[0].permanent_border_area;
				if (br == "yes") {
					$("#per_border_area").prop("checked", true);
				}
				if (br == "no") {
					$("#per_border_area1").prop("checked", true);
				}
			}
			onPermHomeAddrCountry();
			onPermHomeAddrState();
			onPermHomeAddrDis();
			onPermHomeAddrTeh();
			 
		});
	}
	else{
		$("#perm_home_addr_country").val(0);
		fn_perm_home_addr_Country();
		$("#perm_home_addr_state").val(0);
		fn_prem_domicile_state();
		$("#perm_home_addr_district").val(0);
		fn_prem_domicile_district();
		$("#perm_home_addr_village").val('');
		$("#perm_home_addr_tehsil").val(0);
		$("#perm_home_addr_pin").val('');
		$("#perm_home_addr_rail").val('');
		//26-01-1994
		$("#perm_home_addr_rural").prop("checked", false);
		$("#perm_home_addr_urban").prop("checked", false);
		$("#perm_home_addr_semi_urban").prop("checked", false);
		$("#per_border_area").prop("checked", false);
		$("#per_border_area1").prop("checked", false);
		
		onPermHomeAddrCountry();
		onPermHomeAddrState();
		onPermHomeAddrDis();
		onPermHomeAddrTeh();
	}

}
	
	function fn_prem_domicile_state() {
		var state_id = $('select#perm_home_addr_state').val();

		$
				.post("getDistrictListFormState1?" + key + "=" + value, {
					state_id : state_id
				})
				.done(
						function(j) {

							var options = '<option   value="0">' + "--Select--"
									+ '</option>';
							for (var i = 0; i < j.length; i++) {

								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
										+ j[i][1] + '</option>';

							}

							$("select#perm_home_addr_district").html(options);
							fn_prem_domicile_district();
						}).fail(function(xhr, textStatus, errorThrown) {
				});
	}

	//janki
		function onPermHomeAddrCountry() {
		var perm_home_addr_country = $("#perm_home_addr_country option:selected").text();
		 
		if(perm_home_addr_country == "OTHERS"){
			$("#Ot_perm_hm_addr_con_div").show();
			
		}
		else{
			$("#Ot_perm_hm_addr_con_div").hide();
			$("#perm_home_addr_country_other").val("");
		 	
		}
	}

	function onPermHomeAddrState() {

		var perm_home_addr_state = $("#perm_home_addr_state option:selected").text();
		
		if(perm_home_addr_state == "OTHERS"){
			$("#Ot_perm_hm_addr_state_div").show();
			
		}
		else{
			$("#Ot_perm_hm_addr_state_div").hide();
			$("#perm_home_addr_state_other").val("");
		}
	}	
	function onPermHomeAddrDis() {
		var per_home_addr_district2 = $("#perm_home_addr_district option:selected").text();
		
		if(per_home_addr_district2 == "OTHERS"){
			
			$("#Ot_perm_hm_addr_dis_div").show();
		}
		else{
			$("#Ot_perm_hm_addr_dis_div").hide();
			$("#perm_home_addr_district_other").val("");
		 	
		}
		onPermHomeAddrTeh();
	}	
	function onPermHomeAddrTeh() {

		var perm_home_addr_tehsil = $("#perm_home_addr_tehsil option:selected").text();
		if(perm_home_addr_tehsil == "OTHERS"){
			$("#Ot_perm_hm_addr_teh_div").show();
			
		}
		else{
			$("#Ot_perm_hm_addr_teh_div").hide();
			$("#perm_home_addr_tehsil_other").val("");
		 	
		}
	}
	////////////////////////////////////
	
	
	function fn_prem_domicile_district() {
		var Dist_id = $('select#perm_home_addr_district').val();

		$
				.post("getTehsilListFormDistrict1?" + key + "=" + value, {
					Dist_id : Dist_id
				})
				.done(
						function(j) {

							var options = '<option   value="0">' + "--Select--"
									+ '</option>';
							for (var i = 0; i < j.length; i++) {

								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
										+ j[i][1] + '</option>';

							}

							$("select#perm_home_addr_tehsil").html(options);

						}).fail(function(xhr, textStatus, errorThrown) {
				});
		onPermHomeAddrCountry();
		onPermHomeAddrState();
		onPermHomeAddrDis();
		onPermHomeAddrTeh();
	}

	function fn_perm_home_addr_Country() {
		var contry_id = $('select#perm_home_addr_country').val();

		$
				.post("getStateListFormcon1?" + key + "=" + value, {
					contry_id : contry_id
				})
				.done(
						function(j) {

							var options = '<option   value="0">' + "--Select--"
									+ '</option>';
							for (var i = 0; i < j.length; i++) {

								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
										+ j[i][1] + '</option>';

							}

							$("select#perm_home_addr_state").html(options);
							fn_prem_domicile_state();
							
							onPermHomeAddrCountry();
						}).fail(function(xhr, textStatus, errorThrown) {
				});
	}
	//************************************* End NON EFFECTIVE DETAIL*****************************//




//*************************************START ADDRESS DATA *****************************//

function spouse_addressFn(){
	if($("#check_spouse_address").is(':checked'))
	    $("#spouse_addressInnerdiv").show();  // checked
	else{
	    $("#spouse_addressInnerdiv").hide();
	    $("#spouse_addr_Country").val("0");
	    $("#spouse_addr_state").val("0");
	    $("#spouse_addr_district").val("0");
	    $("#spouse_addr_tehsil").val("0");
	    $("#spouse_addr_village").val("");
	    $("#spouse_addr_pin").val("");
	    $("#spouse_addr_rail").val("");
	    $("#spouse_addr_rural").prop('checked', false);
	    $("#spouse_addr_urban").prop('checked', false);
	    $("#spouse_addr_semi_urban").prop('checked', false);
	    $("#Stn_HQ_sus_no").val("");
	    $("#Stn_HQ_unit_name").val("");
	 	    
	}
}
function validate_changeaddress_details_save(){
// 	 if ($("input#addr_authority").val().trim()=="") {
// 		alert("Please Enter The Authority");
// 		$("input#addr_authority").focus();
// 		return false;
// 	}
// 	 if ($("input#addr_date_authority").val() == "DD/MM/YYYY" || $("input#addr_date_authority").val().trim()=="") {
// 		alert("Please Enter The Date of Authority");
// 		$("input#addr_date_authority").focus();
// 		return false;
// 	}  
	/*  if ($("input#addr_date_authority").val() != "DD/MM/YYYY" && $("input#addr_date_authority").val()!="") {
		
	 if(getformatedDate($("input#comm_date").val()) > getformatedDate($("#addr_date_authority").val())) {
			alert("Authority Date should be Greater than Commission Date");
			return false;
	  }
	 } */

	if ($("select#pre_country").val() == "0") {
		alert("Please Select Present Domicile Country");
		$("select#pre_country").focus();
		return false;
	}
   var text1 = $("#pre_country option:selected").text();
	
	if(text1 == "OTHERS" && $("input#pre_country_other").val().trim()==""){
		alert("Please Enter Present Domicile Country Other");
		$("input#pre_country_other").focus();
		return false;
	}
	
	 if ($("select#pre_domicile_state").val() == "0") {
		alert("Please Select Present Domicile State");
		$("select#pre_domicile_state").focus();
		return false;
	} 
	 var text2 = $("#pre_domicile_state option:selected").text();
		
		if(text2 == "OTHERS" && $("input#pre_domicile_state_other").val().trim()==""){
			alert("Please Enter Present Domicile State Other");
			$("input#pre_domicile_state_other").focus();
			return false;
		}
	if ($("select#pre_domicile_district").val() == "0") {
		alert("Please Select Present Domicile District");
		$("select#pre_domicile_district").focus();
		return false;
	} 
	var text3 = $("#pre_domicile_district option:selected").text();
	
	if(text3 == "OTHERS" && $("input#pre_domicile_district_other").val().trim()==""){
		alert("Please Enter Present Domicile District Other");
		$("input#pre_domicile_district_other").focus();
		return false;
	}
	if ($("select#pre_domicile_tesil").val() == "0") {
		alert("Please Select Present Domicile Tehsil");
		$("select#pre_domicile_tesil").focus();
		return false;
	}
  var text4 = $("#pre_domicile_tesil option:selected").text();
	
	if(text4 == "OTHERS" && $("input#pre_domicile_tesil_other").val().trim()==""){
		alert("Please Enter Present Domicile Tehsil Other");
		$("input#pre_domicile_tesil_other").focus();
		return false;
	}
	
	if ($("select#per_home_addr_country").val() == "0") {
		alert("Please Select The Permanent Address Country");
		$("select#per_home_addr_country").focus();
		return false;
	}
	 var text5 = $("#per_home_addr_country option:selected").text();
		
		if(text5 == "OTHERS" && $("input#per_home_addr_country_others").val().trim()==""){
			alert("Please Enter Permanent Address Country Other");
			$("input#per_home_addr_country_others").focus();
			return false;
		}
		
	if ($("select#per_home_addr_state").val() == "0") {
		alert("Please Select Permanent Address State");
		$("select#per_home_addr_state").focus();
		return false;
	}
	 var text6 = $("#per_home_addr_state option:selected").text();
		
		if(text6 == "OTHERS" && $("input#per_home_addr_state_others").val().trim()==""){
			alert("Please Enter Permanent Address State Other");
			$("input#per_home_addr_state_others").focus();
			return false;
		}
	if ($("select#per_home_addr_district").val() == "0") {
		alert("Please Select Permanent Address District");
		$("select#per_home_addr_district").focus();
		return false;
	}
	 var text7 = $("#per_home_addr_district option:selected").text();
		
		if(text7 == "OTHERS" && $("input#per_home_addr_district_others").val().trim()==""){
			alert("Please Enter Permanent Address District Other");
			$("input#per_home_addr_district_others").focus();
			return false;
		}
	if ($("select#per_home_addr_tehsil").val() == "0") {
		alert("Please Select Permanent Address Tehsil");
		$("select#per_home_addr_tehsil").focus();
		return false;
	}
	var text8 = $("#per_home_addr_tehsil option:selected").text();
	
	if(text8 == "OTHERS" && $("input#per_home_addr_tehsil_others").val().trim()==""){
		alert("Please Enter Permanent Address Tehsil Other");
		$("input#per_home_addr_tehsil_others").focus();
		return false;
	}
	if ($("input#per_home_addr_village").val().trim()=="") {
		alert("Please Enter Permanent Address Village/Town/City");
		$("input#per_home_addr_village").focus();
		return false;
	}
	if ($("input#per_home_addr_pin").val().trim()=="" || $("input#per_home_addr_pin").val().length<6 || $("input#per_home_addr_pin").val().length>6) {
		alert("Please Enter Permanent Address Pin");
		$("input#per_home_addr_pin").focus();
		return false;
	}
	if ($("input#per_home_addr_rail").val().trim()=="") {
		alert("Please Enter Permanent Address Nearest Railway Station");
		$("input#per_home_addr_rail").focus();
		return false;
	}
	 var a = $('input:radio[name=per_home_addr_rural_urban]:checked').val();
		if(a == undefined){	
			alert("Please select The Permanent Address  Rural /Urban/Semi Urban ");
			return false;
			}
		var b = $('input:radio[name=border_area]:checked').val();
		if(b == undefined){	
			alert("Please select The Permanent Address Border area ");
			return false;
			}
		if ($("select#pers_addr_country").val() == "0") {
			alert("Please Select Present Address Country");
			$("select#pers_addr_country").focus();
			return false;
		}
		var text9 = $("#pers_addr_country option:selected").text();
		
		if(text9 == "OTHERS" && $("input#pers_addr_country_other").val().trim()==""){
			alert("Please Enter Present Address Country Other");
			$("input#pers_addr_country_other").focus();
			return false;
		}
		if ($("select#pers_addr_state").val() == "0") {
			alert("Please select Present Address State");
			$("select#pers_addr_state").focus();
			return false;
		}
  var text10 = $("#pers_addr_state option:selected").text();
		
		if(text10 == "OTHERS" && $("input#pers_addr_state_other").val().trim()==""){
			alert("Please Enter Present Address State Other");
			$("input#pers_addr_state_other").focus();
			return false;
		}
		if ($("select#pers_addr_district").val() == "0") {
			alert("Please select Present Address District");
			$("select#pers_addr_district").focus();
			return false;
		}
var text11 = $("#pers_addr_district option:selected").text();
		
		if(text11 == "OTHERS" && $("input#pers_addr_district_other").val().trim()==""){
			alert("Please Enter Present Address District Other");
			$("input#pers_addr_district_other").focus();
			return false;
		}
		if ($("select#pers_addr_tehsil").val() == "0") {
			alert("Please Select Present Address Tehsil");
			$("select#pers_addr_tehsil").focus();
			return false;
		}
var text11 = $("#pers_addr_tehsil option:selected").text();
		
		if(text11 == "OTHERS" && $("input#pers_addr_tehsil_other").val().trim()==""){
			alert("Please Enter Present Address Tehsil Other");
			$("input#pers_addr_tehsil_other").focus();
			return false;
		}
	if ($("input#pers_addr_village").val().trim()=="") {
		alert("Please Enter Present Address Village/Town/City");
		$("input#pers_addr_village").focus();
		return false;
	}
	if ($("input#pers_addr_pin").val().trim()=="" || $("input#pers_addr_pin").val().length<6  || $("input#pers_addr_pin").val().length>6) {
		alert("Please Enter Present Address Pin");
		$("input#pers_addr_pin").focus();
		return false;
	}
	if ($("input#pers_addr_rail").val().trim()=="") {
		alert("Please Enter Present Address Nearest Railway Station");
		$("input#pers_addr_rail").focus();
		return false;
	}
	var c = $('input:radio[name=pers_addr_rural_urban]:checked').val();
	if(c == undefined){	
		alert("Please select The  Present Address Rural /Urban/Semi Urban ");
		return false;
		}
	
	if($("#check_spouse_address").is(':checked')){
		
		if ($("select#spouse_addr_Country").val() == "0") {
			alert("Please select SF ACCN Address Country");
			$("select#spouse_addr_Country").focus();
			return false;
		}	
var text12 = $("#spouse_addr_Country option:selected").text();
		
		if(text12 == "OTHERS" && $("input#spouse_addr_country_other").val().trim()==""){
			alert("Please Enter SF ACCN Address Country Other");
			$("input#spouse_addr_country_other").focus();
			return false;
		}
		if ($("select#spouse_addr_state").val() == "0") {
			alert("Please select SF ACCN Address State");
			$("select#spouse_addr_state").focus();
			return false;
		}
var text13 = $("#spouse_addr_state option:selected").text();
		
		if(text13 == "OTHERS" && $("input#spouse_addr_state_other").val().trim()==""){
			alert("Please Enter SF ACCN Address State Other");
			$("input#spouse_addr_state_other").focus();
			return false;
		}
		if ($("select#spouse_addr_district").val() == "0") {
			alert("Please select SF ACCN Address District");
			$("select#spouse_addr_district").focus();
			return false;
		}
var text14 = $("#spouse_addr_district option:selected").text();
		
		if(text14 == "OTHERS" && $("input#spouse_addr_district_other").val().trim()==""){
			alert("Please Enter SF ACCN Address District Other");
			$("input#spouse_addr_district_other").focus();
			return false;
		}
		if ($("select#spouse_addr_tehsil").val() == "0") {
			alert("Please Select SF ACCN Address Tehsil");
			$("select#spouse_addr_tehsil").focus();
			return false;
		}
var text15 = $("#spouse_addr_tehsil option:selected").text();
		
		if(text15 == "OTHERS" && $("input#spouse_addr_tehsil_other").val().trim()==""){
			alert("Please Enter SF ACCN Address Tehsil Other");
			$("input#spouse_addr_tehsil_other").focus();
			return false;
		}
		if ($("input#spouse_addr_village").val().trim()=="") {
			alert("Please Enter SF ACCN Address Village/Town/City");
			$("input#spouse_addr_village").focus();
			return false;
		}	
		if ($("input#spouse_addr_pin").val().trim()=="" || $("input#spouse_addr_pin").val().length<6 || $("input#spouse_addr_pin").val().length>6)  {
			alert("Please Enter valid SF ACCN Address Pin");
			$("input#spouse_addr_pin").focus();
			return false;
		}	
		if ($("input#spouse_addr_rail").val().trim()=="") {
			alert("Please Enter SF ACCN Address Nearest Railway Station");
			$("input#spouse_addr_rail").focus();
			return false;
		}
		var spouserus = $('input:radio[name=spouse_addr_rural_urban]:checked').val();
		if(spouserus == undefined) {
			alert("Please select SF ACCN Address Is  Rural /Urban/Semi Urban");
			return false;
		}
		
		if ($("input#Stn_HQ_sus_no").val().trim()=="") {
			alert("Please Enter SF ACCN Address Stn HQ SUS No");
			$("input#Stn_HQ_sus_no").focus();
			return false;
		}
	}
	
	/* if ($("select#retire_country").val() == "0") {
		alert("Please Select The  Country");
		$("select#retire_country").focus();
		return false;
	}
	if ($("select#retire_state").val() == "0") {
		alert("Please Select State");
		$("select#retire_state").focus();
		return false;
	}
	if ($("select#retire_district").val() == "0") {
		alert("Please Select District");
		$("select#retire_district").focus();
		return false;
	}
	if ($("select#retire_tehsil").val() == "0") {
		alert("Please Enter Tehsil");
		$("select#retire_tehsil").focus();
		return false;
	}
	if ($("select#retire_village").val() == "0") {
		alert("Please Select  Village/Town/City");
		$("select#retire_village").focus();
		return false;
	}
	if ($("input#retire_pin").val().trim()=="") {
		alert("Please Enter Pin");
		$("input#retire_pin").focus();
		return false;
	}
	if ($("input#retire_rail").val().trim()=="") {
		alert("Please Enter  Nearest Railway Station");
		$("input#retire_rail").focus();
		return false;
	}
	var d = $('input:radio[name=retire_rural_urban]:checked').val();
	if(d == undefined){	
		alert("Please select The  Rural /Urban/Semi Urban ");
		return false;
		} */
	var formvalues=$("#form_addr_details_form").serialize();
	var census_id=$("#census_id").val();	
	var addr_ch_id=$("#addr_ch_id").val();	
		var comm_id=$("#comm_id").val();
		var comm_date=$("#comm_date").val();
		var marital_status = $('#marital_status_p').text();
		formvalues+="&census_id="+census_id+"&addr_ch_id="+addr_ch_id+"&comm_id="+comm_id+"&marital_status="+marital_status+"&comm_date="+comm_date;	

		 $.post('changeaddress_details_action?' + key + "=" + value,formvalues, function(data){
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
function get_changeaddress_details(){
	var census_id=$("#census_id").val();	
	var comm_id=$("#comm_id").val();	
	if($("#marital_status_p").text() == '8'){
		$("#spouse_addressMaindiv").show();
	}
	else{
		$("#spouse_addressMaindiv").hide();
	}
	$.post('address_details_GetData?' + key + "=" + value,{census_id:census_id,comm_id:comm_id }, function(j){
		var v=j.length;
		if(v!=0){
			$("#pre_country").val(j[0].pre_country);
			fn_pre_domicile_Country();
			$("#pre_domicile_state").val(j[0].pre_state);
			fn_pre_domicile_state();
			$("#pre_domicile_district").val(j[0].pre_district);
			fn_pre_domicile_district();
			$("#pre_domicile_tesil").val(j[0].pre_tesil);
			$("#addr_authority").val(j[0].authority);
			$('#addr_date_authority').val(ParseDateColumncommission(j[0].date_authority)); 
			$("#per_home_addr_country").val(j[0].permanent_country);
			fn_per_home_addr_Country();
			$("#per_home_addr_state").val(j[0].permanent_state);
			fn_per_home_addr_state();
			$("#per_home_addr_district").val(j[0].permanent_district);
			fn_per_home_addr_district();
			$("#per_home_addr_village").val(j[0].permanent_village);
			$("#per_home_addr_tehsil").val(j[0].permanent_tehsil);
			$("#per_home_addr_pin").val(j[0].permanent_pin_code);
			$("#per_home_addr_rail").val(j[0].permanent_near_railway_station);
			 var permanent= j[0].permanent_rural_urban_semi;
           if(permanent == "rural"){
                   $("#per_home_addr_rural").prop("checked", true);
            } 
           if(permanent == "urban"){
                   $("#per_home_addr_urban").prop("checked", true);
            }
           if(permanent == "semi_urban")
           {
                   $("#per_home_addr_semi_urban").prop("checked", true);
            }  
           var br= j[0].permanent_border_area;
           if(br == "yes"){
                   $("#border_area").prop("checked", true);
            } 
           if(br == "no"){
                   $("#border_area1").prop("checked", true);
            } 
           $("#pers_addr_country").val(j[0].present_country);
           fn_pers_addr_Country();
           $("#pers_addr_state").val(j[0].present_state);
           fn_pers_addr_state();
           $("#pers_addr_district").val(j[0].present_district);
           fn_pers_addr_district();
			$("#pers_addr_village").val(j[0].present_village);
			$("#pers_addr_tehsil").val(j[0].present_tehsil);
			$("#pers_addr_pin").val(j[0].present_pin_code);
			$("#pers_addr_rail").val(j[0].present_near_railway_station);
			var present= j[0].present_rural_urban_semi;
	            if(present == "rural"){
	                    $("#pers_addr_rural").prop("checked", true);
	            } 
	            if(present == "urban")
	            {
	                    $("#pers_addr_urban").prop("checked", true);
	             }
	            if(present == "semi_urban")
	            {
	                    $("#pers_addr_semi_urban").prop("checked", true);
	             }  
	            var text6 = $("#pre_country option:selected").text();
				if(text6 == "OTHERS"){
					$("#pre_country_other").val(j[0].pre_country_other);
					$("#add_other_id").show();
				}
				else{
					$("#add_other_id").hide();
				}
				
				var text7 = $("#pre_domicile_state option:selected").text();
				if(text7 == "OTHERS"){
					$('#pre_domicile_state_other').val(j[0].pre_domicile_state_other);
					$("#add_other_st").show();
				}
				else{
					$("#add_other_st").hide();
				}
				var text8 = $("#pre_domicile_district option:selected").text();
				if(text8 == "OTHERS"){
					$("#pre_domicile_district_other").val(j[0].pre_domicile_district_other);
					$("#add_other_dt").show();
				}
				else{
					$("#add_other_dt").hide();
				}
				var text9 = $("#pre_domicile_tesil option:selected").text();
				if(text9 == "OTHERS"){
					$("#pre_domicile_tesil_other").val(j[0].pre_domicile_tesil_other);
					$("#add_other_te").show();
				}
				else{
					$("#add_other_te").hide();
				}
				var text10 = $("#per_home_addr_country option:selected").text();
				if(text10 == "OTHERS"){
					$("#per_home_addr_country_others").val(j[0].per_home_addr_country_other);
					$("#per_home_addr_country_other_hid").show();
				}
				else{
					$("#per_home_addr_country_other_hid").hide();
				}
				var text11 = $("#per_home_addr_state option:selected").text();
				if(text11 == "OTHERS"){
					$("#per_home_addr_state_others").val(j[0].per_home_addr_state_other);
					$("#per_home_addr_state_other_hid").show();
				}
				else{
					$("#per_home_addr_state_other_hid").hide();
				}
				var text12 = $("#per_home_addr_district option:selected").text();
				if(text12 == "OTHERS"){
					$("#per_home_addr_district_others").val(j[0].per_home_addr_district_other);
					$("#per_home_addr_district_other_hid").show();
				}
				else{
					$("#per_home_addr_district_other_hid").hide();
				}
				var text13 = $("#per_home_addr_tehsil option:selected").text();
				if(text13 == "OTHERS"){
					$("#per_home_addr_tehsil_others").val(j[0].per_home_addr_tehsil_other);
					$("#per_home_addr_tehsil_other_hid").show();
				}
				else{
					$("#per_home_addr_tehsil_other_hid").hide();
				}
				var text14 = $("#pers_addr_country option:selected").text();
				if(text14 == "OTHERS"){
					$("#pers_addr_country_other").val(j[0].pers_addr_country_other);
					$("#pers_addr_country_other_hid").show();
				}
				else{
					$("#pers_addr_country_other_hid").hide();
				}
				var text15 = $("#pers_addr_state option:selected").text();
				if(text15 == "OTHERS"){
					$("#pers_addr_state_other").val(j[0].pers_addr_state_other);
					$("#pers_addr_state_other_hid").show();
				}
				else{
					$("#pers_addr_state_other_hid").hide();
				}
				var text16 = $("#pers_addr_district option:selected").text();
				if(text16 == "OTHERS"){
					$("#pers_addr_district_other").val(j[0].pers_addr_district_other);
					$("#pers_addr_district_other_hid").show();
				}
				else{
					$("#pers_addr_district_other_hid").hide();
				}
				var text17 = $("#pers_addr_tehsil option:selected").text();
				if(text17 == "OTHERS"){
					$("#pers_addr_tehsil_other").val(j[0].pers_addr_tehsil_other);
					$("#pers_addr_tehsil_other_hid").show();
				}
				else{
					$("#pers_addr_tehsil_other_hid").hide();
				}
				
				
				
				
				
			$("#addr_ch_id").val(j[0].id);
			$("#addr_pending_ch_id").val(j[0].id);
			if($("#marital_status_p").text() == '8' && j[0].spouse_country != 0){
				$("#check_spouse_address").attr("checked", true);
				spouse_addressFn();
				$("#spouse_addr_Country").val(j[0].spouse_country);
				$("#spouse_addr_country_other").val(j[0].spouse_addr_country_other);
				fn_spouse_addr_Country();
				$("#spouse_addr_state").val(j[0].spouse_state);
				$("#spouse_addr_state_other").val(j[0].spouse_addr_state_other);
				fn_spouse_addr_state();
				$("#spouse_addr_district").val(j[0].spouse_district);
				$("#spouse_addr_district_other").val(j[0].spouse_addr_district_other);
				fn_spouse_addr_district();
				$("#spouse_addr_tehsil").val(j[0].spouse_tehsil);
				fn_spouse_addr_tehsil();
				$("#spouse_addr_tehsil_other").val(j[0].spouse_addr_tehsil_other);
				$("#spouse_addr_village").val(j[0].spouse_village);
				$("#spouse_addr_pin").val(j[0].spouse_pin_code);
				$("#spouse_addr_rail").val(j[0].spouse_near_railway_station);
				$("#Stn_HQ_sus_no").val(j[0].stn_hq_sus_no);
				var sus_no = j[0].stn_hq_sus_no;			      	
				 $.post("getTargetUnitNameListStnHQ?"+key+"="+value, {
						 sus_no:sus_no
		         }, function(j) {
		                
		         }).done(function(j) {
		        	 var length = j.length-1;
		             var enc = j[length].substring(0,16);
		             $("#Stn_HQ_unit_name").val(dec(enc,j[0]));   
		                 
		         }).fail(function(xhr, textStatus, errorThrown) {
	        	 });
				 $("input[name=spouse_addr_rural_urban][value=" + j[0].spouse_rural_urban_semi + "]").prop('checked', true);

			}
			
		}
	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
}
function copy_address(){
    if($("#check_address").prop('checked') == true){                
            $("#pers_addr_village").val($("#per_home_addr_village").val());
            $("#pers_addr_country").val($("#per_home_addr_country").val());
            $("#pers_addr_country_other").val($("#per_home_addr_country_others").val());
            fn_pers_addr_Country();
            $("#pers_addr_state").val($("#per_home_addr_state").val());
            $("#pers_addr_state_other").val($("#per_home_addr_state_others").val());
            
            fn_pers_addr_state();
            $("#pers_addr_district").val($("#per_home_addr_district").val());
            $("#pers_addr_district_other").val($("#per_home_addr_district_others").val());
            fn_pers_addr_district();
            $("#pers_addr_tehsil").val($("#per_home_addr_tehsil").val());
            $("#pers_addr_tehsil_other").val($("#per_home_addr_tehsil_others").val());
            fn_pers_addr_tehsil();
            $("#pers_addr_pin").val($("#per_home_addr_pin").val());
            $("#pers_addr_rail").val($("#per_home_addr_rail").val());
            
            $("input[name=pers_addr_rural_urban]").prop('checked', false);
   		 var value= $('input:radio[name=per_home_addr_rural_urban]:checked').val()
   		            $("input[name=pers_addr_rural_urban][value=" + value + "]").prop('checked', true);
    }
    else{
            $("#pers_addr_village").val("");
            $("#pers_addr_tehsil").val("0");
            $("#pers_addr_district").val("0");
            $("#pers_addr_state").val("0");
            $("#pers_addr_district_other").val("");
            $("#pers_addr_country_other").val("");
            $("#pers_addr_state_other").val("");
            $("#pers_addr_tehsil_other").val("");
            $("#pers_addr_pin").val("");
            $("#pers_addr_rail").val("");
            $("#pers_addr_country").val("0");
           // $('input[type="radio"]').prop('checked', false); 
            $("input[name=pers_addr_rural_urban]").attr('checked', false);
    }
}


function fn_pre_domicile_state() {
	var text = $("#pre_domicile_state option:selected").text();
	//alert("hello");
	if(text == "OTHERS"){
		$("#add_other_st").show();
	}
	else{
		$("#add_other_st").hide();
		$("#pre_domicile_state_other").val("");
}
	 var state_id =$('select#pre_domicile_state').val();

	 		 	$.post("getDistrictListFormState1?"+key+"="+value, {state_id:state_id}).done(function(j) {
	 		 				 	
	 		 			 	
	 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
	 				for ( var i = 0; i < j.length; i++) {
	 				
	 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
	 					
	 				}
	 				
	 				$("select#pre_domicile_district").html(options);
	 				fn_pre_domicile_district();
	 			}).fail(function(xhr, textStatus, errorThrown) {
	 	});
}

function fn_pre_domicile_district() {
	
	var text = $("#pre_domicile_district option:selected").text();
	//alert("hello");
	if(text == "OTHERS"){
		$("#add_other_dt").show();
	}
	else{
		$("#add_other_dt").hide();
		$("#pre_domicile_district_other").val("");
}
	 var Dist_id = $('select#pre_domicile_district').val();

	 		 	$.post("getTehsilListFormDistrict1?"+key+"="+value, {Dist_id:Dist_id}).done(function(j) {
	 		 				 	
	 		 			 	
	 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
	 				for ( var i = 0; i < j.length; i++) {
	 				
	 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
	 					
	 				}
	 				
	 				$("select#pre_domicile_tesil").html(options);

	 			}).fail(function(xhr, textStatus, errorThrown) {
	 	});
}
function fn_pre_domicile_tesil() {
	
	var text = $("#pre_domicile_tesil option:selected").text();
	//alert("hello");
	if(text == "OTHERS"){
		$("#add_other_te").show();
	}
	else{
		$("#add_other_te").hide();
		$("#pre_domicile_tesil_other").val("");
}
}
function fn_per_home_addr_Country() {
	var text = $("#per_home_addr_country option:selected").text();
	//alert("hello");
	if(text == "OTHERS"){
		$("#per_home_addr_country_other_hid").show();
	}
	else{
		$("#per_home_addr_country_other_hid").hide();
		$("#per_home_addr_country_others").val("");
	}

	 var contry_id = $('select#per_home_addr_country').val();

	 		 	$.post("getStateListFormcon1?"+key+"="+value, {contry_id:contry_id}).done(function(j) {
	 		 				 	
	 		 			 	
	 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
	 				for ( var i = 0; i < j.length; i++) {
	 				
	 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
	 					
	 				}
	 				
	 				$("select#per_home_addr_state").html(options);
	 				fn_per_home_addr_state();
	 				fn_per_home_addr_district();
	 				fn_per_home_addr_tehsil();
	 			}).fail(function(xhr, textStatus, errorThrown) {
	 	});
}

function fn_per_home_addr_state() {
	var text = $("#per_home_addr_state option:selected").text();
	//alert("hello");
	if(text == "OTHERS"){
		$("#per_home_addr_state_other_hid").show();
	}
	else{
		$("#per_home_addr_state_other_hid").hide();
		$("#per_home_addr_state_others").val("");
}

	 var state_id =$('select#per_home_addr_state').val(); 

	 		 	$.post("getDistrictListFormState1?"+key+"="+value, {state_id:state_id}).done(function(j) {
	 		 				 	
	 		 			 	
	 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
	 				for ( var i = 0; i < j.length; i++) {
	 			
	 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
	 					
	 				}
	 				
	 				$("select#per_home_addr_district").html(options);
	 				fn_per_home_addr_district();
	 				onPermHomeAddrState();
	 			     
	 				
	 			}).fail(function(xhr, textStatus, errorThrown) {
	 	});
}

function fn_pre_domicile_Country() {
	var text = $("#pre_country option:selected").text();
	//alert("hello");
	if(text == "OTHERS"){
		$("#add_other_id").show();
	}
	else{
		$("#add_other_id").hide();
		$("#pre_country_other").val("");
	}
	 var contry_id = $('select#pre_country').val();

	 		 	$.post("getStateListFormcon1?"+key+"="+value, {contry_id:contry_id}).done(function(j) {
	 		 				 	
	 		 			 	
	 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
	 				for ( var i = 0; i < j.length; i++) {
	 				
	 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
	 					
	 				}
	 				
	 				$("select#pre_domicile_state").html(options);
	 				fn_pre_domicile_state();
	 				fn_pre_domicile_district();
	 				fn_pre_domicile_tesil(); 
	 			}).fail(function(xhr, textStatus, errorThrown) {
	 	});
}

function fn_per_home_addr_district() {
	
	var text = $("#per_home_addr_district option:selected").text();
	//alert("hello");
	if(text == "OTHERS"){
		$("#per_home_addr_district_other_hid").show();
	}
	else{
		$("#per_home_addr_district_other_hid").hide();
		$("#per_home_addr_district_others").val("");
}
	 var Dist_id = $('select#per_home_addr_district').val();

	 		 	$.post("getTehsilListFormDistrict1?"+key+"="+value, {Dist_id:Dist_id}).done(function(j) {
	 		 				 	
	 		 			 	
	 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
	 				for ( var i = 0; i < j.length; i++) {
	 				
	 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
	 					
	 				}
	 				
	 				$("select#per_home_addr_tehsil").html(options);

	 			}).fail(function(xhr, textStatus, errorThrown) {
	 	});
}
function fn_per_home_addr_tehsil() {
var text = $("#per_home_addr_tehsil option:selected").text();
//alert("hello");
if(text == "OTHERS"){
	$("#per_home_addr_tehsil_other_hid").show();
}
else{
	$("#per_home_addr_tehsil_other_hid").hide();
	$("#per_home_addr_tehsil_others").val("");
}
}
function fn_pers_addr_Country() {
	var text = $("#pers_addr_country option:selected").text();
	//alert("hello");
	if(text == "OTHERS"){
		$("#pers_addr_country_other_hid").show();
	}
	else{
		$("#pers_addr_country_other_hid").hide();
		$("#pers_addr_country_other").val("");
	}

	 var contry_id = $('select#pers_addr_country').val();

	 		 	$.post("getStateListFormcon1?"+key+"="+value, {contry_id:contry_id}).done(function(j) {
	 		 				 	
	 		 			 	
	 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
	 				for ( var i = 0; i < j.length; i++) {
	 				
	 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
	 					
	 				}
	 				
	 				$("select#pers_addr_state").html(options);
					fn_pers_addr_state();
					fn_pers_addr_district();
					fn_pers_addr_tehsil();
	 			}).fail(function(xhr, textStatus, errorThrown) {
	 	});
}

function fn_pers_addr_state() {
	var text = $("#pers_addr_state option:selected").text();
	
	if(text == "OTHERS"){
		$("#pers_addr_state_other_hid").show();
	}
	else{
		$("#pers_addr_state_other_hid").hide();
		$("#pers_addr_state_other").val("");
	}

	 var state_id = $('select#pers_addr_state').val();

	 		 	$.post("getDistrictListFormState1?"+key+"="+value, {state_id:state_id}).done(function(j) {
	 		 				 	
	 		 			 	
	 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
	 				for ( var i = 0; i < j.length; i++) {
	 					

	 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
	 				
	 				}
	 				
	 				$("select#pers_addr_district").html(options);
					fn_pers_addr_district();
	 			}).fail(function(xhr, textStatus, errorThrown) {
	 	});
}
function fn_pers_addr_district() {
var text = $("#pers_addr_district option:selected").text();
	
	if(text == "OTHERS"){
		$("#pers_addr_district_other_hid").show();
	}
	else{
		$("#pers_addr_district_other_hid").hide();
		$("#pers_addr_district_other").val("");
	}


	 var Dist_id = $('select#pers_addr_district').val();

	 		 	$.post("getTehsilListFormDistrict1?"+key+"="+value, {Dist_id:Dist_id}).done(function(j) {
	 		 				 	
	 		 			 	
	 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
	 				for ( var i = 0; i < j.length; i++) {
	 				
	 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
	 					
	 				}
	 				
	 				$("select#pers_addr_tehsil").html(options);

	 			}).fail(function(xhr, textStatus, errorThrown) {
	 	});
	 		 	
}
function fn_pers_addr_tehsil() {
	var text = $("#pers_addr_tehsil option:selected").text();
		
		if(text == "OTHERS"){
			$("#pers_addr_tehsil_other_hid").show();
		}
		else{
			$("#pers_addr_tehsil_other_hid").hide();
			$("#pers_addr_tehsil_other").val("");
		}
}
function fn_spouse_addr_Country() {
	var text = $("#spouse_addr_Country option:selected").text();
	
	if(text == "OTHERS"){
		$("#spouse_addr_Country_hid").show();
	}
	else{
		$("#spouse_addr_Country_hid").hide();
		$("#spouse_addr_country_other").val("");
	}

	 var contry_id = $('select#spouse_addr_Country').val();

	 		 	$.post("getStateListFormcon1?"+key+"="+value, {contry_id:contry_id}).done(function(j) {
	 		 				 	
	 		 			 	
	 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
	 				for ( var i = 0; i < j.length; i++) {
	 				
	 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
	 					
	 				}
	 				
	 				$("select#spouse_addr_state").html(options);
					fn_spouse_addr_state();
					fn_spouse_addr_district();
					fn_spouse_addr_tehsil()
	 			}).fail(function(xhr, textStatus, errorThrown) {
	 	});
}


function fn_spouse_addr_state() {
var text = $("#spouse_addr_state option:selected").text();
	
	if(text == "OTHERS"){
		$("#spouse_addr_state_hid").show();
	}
	else{
		$("#spouse_addr_state_hid").hide();
		$("#spouse_addr_state_other").val("");
	}

	 var state_id = $('select#spouse_addr_state').val();

	 		 	$.post("getDistrictListFormState1?"+key+"="+value, {state_id:state_id}).done(function(j) {
	 		 				 	
	 		 			 	
	 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
	 				for ( var i = 0; i < j.length; i++) {
	 					

	 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
	 				
	 				}
	 				
	 				$("select#spouse_addr_district").html(options);
					fn_spouse_addr_district();
	 			}).fail(function(xhr, textStatus, errorThrown) {
	 	});
}

function fn_spouse_addr_district() {
var text = $("#spouse_addr_district option:selected").text();
	
	if(text == "OTHERS"){
		$("#spouse_addr_district_hid").show();
	}
	else{
		$("#spouse_addr_district_hid").hide();
		$("#spouse_addr_district_other").val("");
	}

	 var Dist_id = $('select#spouse_addr_district').val();

	 		 	$.post("getTehsilListFormDistrict1?"+key+"="+value, {Dist_id:Dist_id}).done(function(j) {
	 		 				 	
	 		 			 	
	 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
	 				for ( var i = 0; i < j.length; i++) {
	 				
	 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
	 					
	 				}
	 				
	 				$("select#spouse_addr_tehsil").html(options);
	 				fn_spouse_addr_tehsil();
	 			}).fail(function(xhr, textStatus, errorThrown) {
	 	});
	 		 	
}
function fn_spouse_addr_tehsil() {
	var text = $("#spouse_addr_tehsil option:selected").text();
		
		if(text == "OTHERS"){
			$("#spouse_addr_tehsil_hid").show();
		}
		else{
			$("#spouse_addr_tehsil_hid").hide();
			$("#spouse_addr_tehsil_other").val("");
		}
}

$("#Stn_HQ_sus_no").keypress(function(){
	var sus_no = this.value;
	var susNoAuto=$("#Stn_HQ_sus_no");

	susNoAuto.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
	        type: 'POST',
	        url: "HQ_getTargetSUSNoList?"+key+"="+value,
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
	        	  alert("Please Enter Approved Station HQ SUS No.");
	        	  document.getElementById("Stn_HQ_unit_name").value="";
	        	  susNoAuto.val("");	        	  
	        	  susNoAuto.focus();
	        	  return false;	             
	          }   	         
	    }, 
		select: function( event, ui ) {
			var sus_no = ui.item.value;			      	
			 $.post("getTargetUnitNameListStnHQ?"+key+"="+value, {
				 sus_no:sus_no
         }, function(j) {
                
         }).done(function(j) {
        	 var length = j.length-1;
             var enc = j[length].substring(0,16);
             $("#Stn_HQ_unit_name").val(dec(enc,j[0]));   
                 
         }).fail(function(xhr, textStatus, errorThrown) {
         });
		} 	     
	});	
});

$("#Stn_HQ_unit_name").keypress(function(){
	var unit_name = this.value;
		 var susNoAuto=$("#Stn_HQ_unit_name");
		  susNoAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        	type: 'POST',
			    	url: "getallUnitNameListStnHQ?"+key+"="+value,
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
		        	  alert("Please Enter Approved Station HQ Unit Name.");
		        	  document.getElementById("Stn_HQ_sus_no").value="";
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
				        	$("#Stn_HQ_sus_no").val(dec(enc,data[0]));
			            }).fail(function(xhr, textStatus, errorThrown) {
			            });
		      } 	     
		}); 			
});


//*************************************End ADDRESS DATA *****************************//
























//***************** START MARRIED DETAILS************************//
	function checkDivorcedate(obj,marriage_date) {
			
			if(marriage_date.value !="")
			{
				var id = obj.id;
				var myDate = document.getElementById(id).value;
				var Date1 = marriage_date.value;
				if ((Date.parse(myDate) < Date.parse(Date1))) {
					alert('Divorce Date should not be before Marriage Date');
					$("input#divorce_date").focus();
					$("input#divorce_date").val("");
				}
			}
		}
	function marrqualificationFn(){
		
			
			  if($('#marr_quali_radio1').is(':checked')) {
				  $('#marital_eventDiv').show();
				  $('#spouse_Qualifications').hide();
				  $('#mrgbtn_save').show();
				  $('#mrgqualibtn_save').hide();
			  }
			  else if($('#marr_quali_radio2').is(':checked')) { 
				  $('#marital_eventDiv').hide();
				  $('#marital_event').val(0);
				  $('#marriage_remarriageDiv').hide();
				  $('#spouse_Qualifications').show();
				  $('#mrgbtn_save').hide();
				  $('#mrgqualibtn_save').show();
				  
			  }
				
	}

	function married_save_fn(ps){
		 var marital_event=$('#marital_event').val();
		 
	
		if (marital_event!="2" && marital_event!="5" && marital_event!="6" ) {	
		 $("#divorce_date").val("");
		}
    
     var marital_status=$('#marital_status').val();
     var maiden_name=$('#maiden_name').val();
     var marriage_date_of_birth=$('#marriage_date_of_birth').val();
     var marriage_place_of_birth=$('#marriage_place_of_birth').val();
     var marriage_nationality=$('#marriage_nationality').val();
     var marriage_nationality_other=$('#spouseNationality_other').val();
     var marriage_date=$('#marriage_date').val();
     var marriage_adhar_no=$('#marriage_adhar_no').val().split('-').join('');
     var pan_card=$('#pan_card').val();
     var marr_ch_id=$('#marr_ch_id').val();
     var divorce_date=$('#divorce_date').val();
     var marriage_authority=$('#marriage_authority').val();
     var marriage_date_of_authority=$('#marriage_date_of_authority').val();
     var comm_id=$('#comm_id').val();        
     var p_id=$('#census_id').val();
     var ser_radio = $('input:radio[name=spouse_ser_radio]:checked').val();
     var spouse_personal_no=$("#spouse_personal_no").val();
     var spouseSer_other=$("#spouseSer_other").val();
     var spouse_service=$("#spouse_service").val();
     var comm_date=$('#comm_date').val();  
     
     var separated_from_dt=$("#separated_from_dt").val();
     var separated_to_dt=$('#separated_to_dt').val();   
  // bisag v2 03072023(Observation)
     var spouse_profession=$("#spouse_profession").val();
     
     if (marital_event == null || marital_event=="0") {
             alert("Please Select Marital Event");
             $('#marital_event').focus()
             return false;
     }
     
     
     if (marriage_authority == null || marriage_authority.trim()=="") {
         alert("Please Enter Authority");
         $('#marriage_authority').focus()
         return false;
	   }
	   if (marriage_date_of_authority == null || marriage_date_of_authority=="" || marriage_date_of_authority=="DD/MM/YYYY") {
	           alert("Please Enter Date of Authority");
	           $('#marriage_date_of_authority').focus()
	           return false;
	   }
	   if(getformatedDate($("input#comm_date").val()) > getformatedDate($("#marriage_date_of_authority").val())) {
         alert("Authority Date should be Greater than Commission Date");
         return false;
		}
     
     if(marital_event == 1 || marital_event == 3){
			
  	   
    			if(curr_marital_status==13){
				   		if (separated_to_dt != null && separated_to_dt=="" && separated_to_dt=="DD/MM/YYYY") {
				             
				   		if(getformatedDate(separated_from_dt) >= getformatedDate(separated_to_dt)) {
				               alert("Separation To Date should be Greater than Separation From Date");
				               $("input#separated_to_dt").focus();
				               return false;
				       	}
				   		}
				}
    			else{
		               if (maiden_name == null || maiden_name.trim()=="")  {
		                       alert("Please Enter Spouse Name");
		                       $('#maiden_name').focus()
		                       return false;
		               }
		               if (marriage_date == null || marriage_date=="" || marriage_date=="DD/MM/YYYY") {
		                       alert("Please Select Date of Marriage");
		                       $('#marriage_date').focus()
		                       return false;
		               }
		               if (marriage_place_of_birth == null || marriage_place_of_birth.trim()=="") {
		                       alert("Please Enter Place Of Birth");
		                       $('#marriage_place_of_birth').focus()
		                       return false;
		               }
		               if (marriage_nationality == null || marriage_nationality=="0") {
		                       alert("Please Select Nationality");
		                       $('#marriage_nationality').focus()
		                       return false;
		               }
		               if($("#marriage_nationality option:selected").text()==other) {
		                     if(marriage_nationality_other == null || marriage_nationality_other==""){          
		                            alert( "Please Enter Nationality Other");
		                               
		                               $("input#spouseNationality_other").focus();
		                               return false;
		                          }
		             }
		               if (marriage_date_of_birth == null || marriage_date_of_birth=="" || marriage_date_of_birth=="DD/MM/YYYY") {
		                       alert("Please Select Date of Birth");
		                       $('#marriage_date_of_birth').focus()
		                       return false;
		               }
		               
		               if(getformatedDate(marriage_date_of_birth) > getformatedDate(marriage_date)) {
		                       alert("Marriage Date should be Greater than Date of Birth");
		                       $('#marriage_date').focus()
		                       return false;
		               }
		               if(calculate_age(document.getElementById('marriage_date_of_birth'), document.getElementById('marriage_date')) && calculate_age(document.getElementById('dob_date'), document.getElementById('marriage_date'))) {} else {
		                       return false;
		               }
		               if (marriage_adhar_no == null || marriage_adhar_no=="" ||  marriage_adhar_no.length < 12) {
		                       alert("Please Enter Aadhaar Number");
		                       $('#marriage_adhar_no').focus()
		                       return false;
		               }
	
		               if(ser_radio=="Yes"){
		                       if(spouse_service==null || spouse_service=='0'){
		                               alert("Please Select Spouse In Service");
		                               $('#spouse_service').focus()
		                               return false;
		                       }
		                       else{
		                               if(spouse_service=='4'){
		                                       if(spouseSer_other==null || spouseSer_other==""){                        
		                                       alert("Please Enter Other Service");
		                                       $('#spouseSer_other').focus()
		                                       return false;
		                                       }
		                               }
		                                else if(spouse_service=='1'){
		                                        if(spouse_personal_no==null || spouse_personal_no==""){                        
		                                                alert("Please Enter Spouse Personal No.");
		                                                $('#spouse_personal_no').focus()
		                                                return false;
		                                                }
		                                        }
		                               if(spouse_personal_no.trim()!=''){
		                                       if(spouse_personal_no.trim().length < 5 || spouse_personal_no.trim().length >15){
		                                       alert("Please Enter Valid Spouse Personal No");
		                                       $("#spouse_personal_no").focus();
		                                       return false;
		                                       }
		                               }
		                       }
		               }
		       }
     }
   
     if( marital_event=='2' || marital_event=='5' || marital_event=='6'){
             if (divorce_date == null || divorce_date=="" || divorce_date=="DD/MM/YYYY") {
                     alert("Please Select "+$("#divorce_widow_widower_dtlbl").text());
                     $('#divorce_date').focus()
                     return false;
             }
             if(getformatedDate(marriage_date) >= getformatedDate(divorce_date)) {
                     alert($("#divorce_widow_widower_dtlbl").text()+" should be Greater than Marriage Date");
                     $("input#divorce_date").focus();
                     return false;
             }
     }
     
     if(marital_event=='4'){
     	if(curr_marital_status==8){
	        	if (separated_from_dt == null || separated_from_dt=="" || separated_from_dt=="DD/MM/YYYY") {
	                alert("Please Enter Date of Separation");
	                $('#separated_from_dt').focus()
	                return false;
	        		}
	        	if(getformatedDate(marriage_date) >= getformatedDate(separated_from_dt)) {
                 alert("Date of Separation should be Greater than Marriage Date");
                 $("input#separated_from_dt").focus();
                 return false;
         	}
     	}

    }
     
     
     
    
        $.post('update_family_marriage_action?' + key + "=" + value, {separated_to_dt:separated_to_dt,separated_from_dt:separated_from_dt,curr_marital_status:curr_marital_status,maiden_name:maiden_name,marriage_date_of_birth:marriage_date_of_birth,
                marriage_place_of_birth:marriage_place_of_birth,marriage_nationality:marriage_nationality,marriage_date:marriage_date,marriage_adhar_no:marriage_adhar_no,pan_card:pan_card,
                marr_ch_id:marr_ch_id,divorce_date:divorce_date,marriage_authority:marriage_authority,marriage_date_of_authority:marriage_date_of_authority
                ,marriage_nationality_other:marriage_nationality_other,comm_date:comm_date,marital_event:marital_event,p_id:p_id,comm_id:comm_id ,marital_status:marital_status,ser_radio:ser_radio,spouse_service:spouse_service,spouseSer_other:spouseSer_other,spouse_personal_no:spouse_personal_no,spouse_profession:spouse_profession}, function(data){
                        if(data=="update"){
                                
                                if(($('#marital_event').val()=='1' && curr_marital_status==10)  || $('#marital_event').val()=='3'){
                              		var a = $('input:radio[name=spouse_quali_radio]:checked').val();
                              		if(a.toLowerCase() == "yes"){
                              			spouse_qualification_save_fn();
                              		}
                              		else{
                              		  remove_spouse_qualificationFn();	
                              		alert("Spouse Data Updated Successfully");
                              		}
                    			}
                    			else{
                    				alert("Spouse Data Updated Successfully");
                    			}
                        }
                        else if(parseInt(data)>0){
                      $('#marr_ch_id').val(data);
                      
                      if(($('#marital_event').val()=='1' && curr_marital_status==10)  || $('#marital_event').val()=='3'){
                  		var a = $('input:radio[name=spouse_quali_radio]:checked').val();
                  		if(a.toLowerCase() == "yes"){
                  			spouse_qualification_save_fn();
                  		}
                  		else{
                    		  remove_spouse_qualificationFn();	
                    		 alert("Spouse Data Saved Successfully");
                    		}
        			}
        			else{
        				alert("Spouse Data Saved Successfully");
        			}
                      
                       
               }
               else
                       alert(data);
                                }).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
                       });
}
//spouse



function fn_other_spouse_exam() {
        var text = $("#spouse_quali option:selected").text();
        if(text == "OTHERS"){
                $("#other_spouse_exam").show();
        }
        else{
                $("#other_spouse_exam").hide();
        }
}

function fn_other_spouse_div() {
    var text = $("#spouse_div_class option:selected").text();
    if(text == "OTHERS"){
            $("#other_spouse_div").show();
    }
    else{
            $("#other_spouse_div").hide();
    }
}

function getfamily_marriageDetails(){
// 	 var p_id=$('#census_id').val(); 
	 var comm_id=$('#comm_id').val(); 
	 var marital_event=$('#marital_event').val();
	  $.post('update_getfamily_marriageData?' + key + "=" + value, {marital_event:marital_event,comm_id:comm_id }, function(j){
			var v=j.length;
		if(v!=0){
			
if(curr_marital_status==8 && j[0].status==0){
			$("#marr_quali_radio1").prop("checked", true);
			$("#marr_quali_radio2").prop("disabled", true);
			  $('#spouse_Qualifications').hide();
			  $('#mrgbtn_save').show();
			  $('#mrgqualibtn_save').hide();
}
			if($('#marital_event').val()=="0"){
					$('#marital_event').val(j[0].type_of_event);
					$('#marital_status').val(j[0].marital_status);
			}
			$('#maiden_name').val(j[0].maiden_name);
			$('#marriage_date_of_birth').val(ParseDateColumncommission(j[0].date_of_birth));
			$('#marriage_place_of_birth').val(j[0].place_of_birth);
			$('#marriage_nationality').val(j[0].nationality);
			$('#marriage_date').val(ParseDateColumncommission(j[0].marriage_date));
			$('#marriage_adhar_no').val(j[0].adhar_number);
			$('#pan_card').val(j[0].pan_card);
			$('#marr_ch_id').val(j[0].id);	
			if(j[0].status==0 ){
			$('#marriage_date_of_authority').val(ParseDateColumncommission(j[0].date_of_authority));			
			$('#marriage_authority').val(j[0].authority);
			}
			$('#separated_from_dt').val(ParseDateColumncommission(j[0].separated_form_dt));
			$('#separated_to_dt').val(ParseDateColumncommission(j[0].separated_to_dt));
			
			if(j[0].if_spouse_ser=="Yes"){
				$('#spouse_ser_radio1').prop('checked', true);
			}
			else{
				$('#spouse_ser_radio2').prop('checked', true);
				// bisag v2 03072023(Observation)
				$('#spouse_profession').val(j[0].spouse_profession);
			}
			if(j[0].spouse_personal_no!=null){
				$('#spouse_personal_no').val(j[0].spouse_personal_no);
			}
			if(j[0].spouse_service!=null){
				$('#spouse_service').val(j[0].spouse_service);
			}
			if(j[0].other_spouse_ser!=null){
				$('#spouseSer_other').val(j[0].other_spouse_ser);
			}
			if(j[0].other_spouse_ser!=null){
				$('#spouseSer_other').val(j[0].other_spouse_ser);
			}
			if(j[0].other_nationality!=null){
				$('#spouseNationality_other').val(j[0].other_nationality);
			}
			isAadhar(document.getElementById('marriage_adhar_no'));
			spouse_ServiceFn();
			Spouse_ServiceOtherFn('spouseSer_otherDiv','spouse_personalDiv',document.getElementById('spouse_service'))
			fn_otherShowHide(document.getElementById('marriage_nationality'),'Spouse_nationalityDiv','spouseNationality_other');
			if(j[0].divorce_date!=null && j[0].divorce_date!="" && j[0].divorce_date!="DD/MM/YYYY")
				$('#divorce_date').val(ParseDateColumncommission(j[0].divorce_date));
			if(j[0].status==1 || j[0].type_of_event==2 || j[0].type_of_event==4 || j[0].type_of_event==5 || j[0].type_of_event==6 || (j[0].type_of_event==1 && curr_marital_status==13)){
				$('#maiden_name').prop('disabled', true);
				$('#marriage_date_of_birth').prop('disabled', true);
				$("#marriage_date_of_birth").datepicker( "option", "disabled", true );
				$('#marriage_place_of_birth').prop('disabled', true);
				$("#marriage_place_of_birth").datepicker( "option", "disabled", true );
				$('#marriage_nationality').prop('disabled', true);
				$('#spouseNationality_other').prop('disabled', true);
				
				$('#marriage_date').prop('disabled', true);
				$("#marriage_date").datepicker( "option", "disabled", true );
				$('#marriage_adhar_no').prop('disabled', true);
				$('#pan_card').prop('disabled', true);
				$('#marr_ch_id').prop('disabled', true);			
//				$('#marriage_date_of_authority').prop('disabled', true);	
//				$("#marriage_date_of_authority").datepicker( "option", "disabled", true );
//				$('#marriage_authority').prop('disabled', true);
				
				
				$('#spouse_service').prop('disabled', true);
				$('input:radio[name=spouse_ser_radio]').prop('disabled', true);			
				$('#spouseSer_other').prop('disabled', true);		
				$('#spouse_personal_no').prop('disabled', true);
			}
			//$('#marital_event').prop('disabled', true);
			$('#marital_status').prop('disabled', true);
			$("#marriage_remarriageDiv").show();
			if($('#marital_event').val()=="1"){
				if(curr_marital_status==13){
					 $("#seperateDiv").show();
	        		$("#separated_from_dtDiv").show();
	        		$("#separated_to_dtDiv").show();
	        		$('#separated_from_dt').prop('disabled', true);
	        		$("#separated_from_dt").datepicker( "option", "disabled", true );
	        	}
			}
				
			
			if($('#marital_event').val()=="2" || $('#marital_event').val()=="5" || $('#marital_event').val()=="6"){			
			$("#divorceDiv").show();
			$("#spouse_quali_radioDiv").hide();
			 // bisag v2 03072023(Observation)
			$("#spouse_proffDiv").hide();
			
			if($('#marital_event').val()=="2"){
				$("#divorce_widow_widower_dtlbl").text("Date of Divorced")
			}
			if($('#marital_event').val()=="5" || $('#marital_event').val()=="6"){
				$("#divorce_widow_widower_dtlbl").text("Date of Death")
			}
			
			
			
			}
			
			else if($('#marital_event').val()=='4'){
				 $("#seperateDiv").show();
				// bisag v2 03072023(Observation)
				$("#spouse_proffDiv").hide();
			 
		        	if(curr_marital_status==8){
		        		if(j[0].status==1){
		        			$("#separated_from_dt").val('');
		        			$("#separated_to_dt").val('');
		        		}
		        		
		        	
		        		$("#separated_from_dtDiv").show();
		        		$("#separated_to_dtDiv").hide();		        		
		        	}
		        	
			        		
		        	}
		       
		
			else{
				$("#spouse_quali_radioDiv").show();
			}
			
			if(($('#marital_event').val()=='1' && curr_marital_status==10)  || $('#marital_event').val()=='3'){
				$("#spouse_quali_radioDiv").show();
			}
			else{
				$("#spouse_quali_radioDiv").hide();
			}
			
			if(curr_marital_status==0 || curr_marital_status==10){
				$("#marital_event option[value='2']").remove();
				$("#marital_event option[value='3']").remove();
				$("#marital_event option[value='4']").remove();
				$("#marital_event option[value='5']").remove();
				$("#marital_event option[value='6']").remove();
				}
			else if(curr_marital_status==8){
				$("#marital_event option[value='1']").remove();
				$("#marital_event option[value='3']").remove();
				}
			else if(curr_marital_status==13){
//				$("#marital_event option[value='1']").remove();
				$("#marital_event option[value='3']").remove();
				$("#marital_event option[value='4']").remove();
			}
			else {
				$("#marital_event option[value='1']").remove();
				$("#marital_event option[value='2']").remove();
				$("#marital_event option[value='4']").remove();
				$("#marital_event option[value='5']").remove();
				$("#marital_event option[value='6']").remove();
			}
			
			}
		else{
		if(curr_marital_status==0 || curr_marital_status==10){
			$("#marital_event option[value='2']").remove();
			$("#marital_event option[value='3']").remove();
			$("#marital_event option[value='4']").remove();
			$("#marital_event option[value='5']").remove();
			$("#marital_event option[value='6']").remove();
			}
		else if(curr_marital_status==8){
			$("#marital_event option[value='1']").remove();
			$("#marital_event option[value='3']").remove();
			}
		else if(curr_marital_status==13){
//			$("#marital_event option[value='1']").remove();
			$("#marital_event option[value='3']").remove();
			$("#marital_event option[value='4']").remove();
		}
		else {
			$("#marital_event option[value='1']").remove();
			$("#marital_event option[value='2']").remove();
			$("#marital_event option[value='4']").remove();
			$("#marital_event option[value='5']").remove();
			$("#marital_event option[value='6']").remove();
		}
		}
	  });
}
function spouse_ServiceFn() {
	var a = $('input:radio[name=spouse_ser_radio]:checked').val();
	if(a.toLowerCase() == "yes") {
		$("#spouse_inserviceDiv").show();
//		$("#spouseSerDiv").hide();
		// bisag v2 03072023(Observation)
		$("#spouse_proffDiv").hide();
	} else {
//		$("#father_proffDiv").show();
		$("#spouse_inserviceDiv").hide();
		$("#spouse_personal_no").val("");
		$("#spouseSer_other").val("");
		$("#spouse_service").val("0");
		// bisag v2 03072023(Observation)
		$("#spouse_proffDiv").show();
	}
}
function Spouse_ServiceOtherFn(divId, perId, obj) {
	if(obj.options[obj.selectedIndex].text == 'OTHER') {
		$("#" + divId).show();
		$("#" + perId).hide();
		$("#spouse_personal_no").val("");
	} else {
		$("#" + divId).hide();
		$("#" + perId).show();
		$("#spouseSer_other").val("");
	}
}

function spouse_qualification_save_fn() {
	

	var spouse_id = $("#marr_ch_id").val();
	var dateofBirthyear = $("#marriage_date_of_birth").val().split('/')[2];
	var currentdate = new Date();
	var currentyear = currentdate.getFullYear();
	var type = $('#spouse_quali_type').val();
	var examination_pass = $('#spouse_quali').val();
	var exam_other_qua=$('#exam_otherSpouse').val();
 	var degree_other=$('#quali_deg_otherSpouse').val();
	var spec_other=$('#quali_spec_otherSpouse').val();
	var class_other_qua=$('#class_otherSpouse').val();
	var passing_year = $('#spouse_yrOfPass').val();
	var degree = $('#quali_degree_spouse').val();
	var specialization = $('#spouse_specialization').val();
	var div_class = $('#spouse_div_class').val();
	var institute = $('#spouse_institute_place').val();
	var qualification_ch_id = $('#spouse_qualification_ch_id').val();
	var q_id = $('#census_id').val();
	var com_id = $('#comm_id').val();
	var subjectvar = $('input[name="spouse_multisub"]:checkbox:checked').map(function() {
		return this.value;
	}).get();
	var subject = subjectvar.join(",");


		

	if(type == null || type == "0") {
		alert("Please Select Qualification Type");
		$("#spouse_quali_type").focus();
		return false;
	}

	if(examination_pass == null || examination_pass == "0") {
		alert("Please Select Examination Pass");
		$("#spouse_quali").focus();
		return false;
	}

	  if($("#spouse_quali option:selected").text()==other) {
	     	 if(exam_other_qua == null || exam_other_qua.trim()==""){ 	 
	     		alert( "Please Enter Examination Other ");
				
				$("input#exam_otherSpouse").focus();
				return false;
			   }
	      }
	  
	if(degree == null || degree == "0") {
		alert("Please Select The Degree Acquired");
		$("#quali_degree_spouse").focus();
		return false;
	}
	if($("#quali_degree_spouse option:selected").text()==other) {
     	 if(degree_other == null || degree_other.trim()==""){ 	       
     		alert( "Please Enter Degree Other ");
			$("input#quali_deg_otherSpouse").focus();
			return false;
		   }
      }
	if(specialization == null || specialization == "0") {
		alert("Please Select The Specialization");
		$("#spouse_specialization").focus();
		return false;
	}
	





 
 if($("#spouse_specialization option:selected").text()==other) {
  	 if(spec_other == null || spec_other.trim()==""){ 	 
   
			alert( "Please Enter Specialization Other ");
			$("input#quali_spec_otherSpouse").focus();
			return false;
		   }
   }
 

if(passing_year.trim()=="") {
	alert("Please Enter Year of Passing");
	$("input#spouse_yrOfPass").focus();
	return false;
}
if(dateofBirthyear!=null){
if(passing_year.trim() != "") {
	if(parseInt(passing_year) <= parseInt(dateofBirthyear) || parseInt(passing_year) > parseInt(currentyear)) {
		alert("Please Enter Valid Year of Passing");
		$("input#spouse_yrOfPass").focus();
		return false;
	}
}}

if(div_class == "0") {
	alert("Please Select The Div/Grade");
	$("#spouse_div_class").focus();
	return false;
}
if(div_class=="4") {
	 if(class_other_qua == null || class_other_qua.trim()==""){ 	 

		alert( "Please Enter Div/Grade Other ");
		$("input#class_otherSpouse").focus();
		return false;
	   }
}
if(subject.trim()=="") {
	alert("Please Select The Subject");
	$("select#spouse_sub_quali").focus();
	return false;
}
if(institute.trim()=="") {
	alert("Please Enter The Institute & place");
	$("#spouse_institute_place").focus();
	return false;
}
	$.post('updated_spouse_qualification_action?' + key + "=" + value, {
		type: type,
		examination_pass: examination_pass,
		passing_year: passing_year,
		div_class: div_class,
		subject: subject,
		institute: institute,
		qualification_ch_id: qualification_ch_id,
		q_id: q_id,
		spouse_id:spouse_id,
		degree: degree,
		specialization: specialization,
		com_id: com_id,
		dateofBirthyear: dateofBirthyear,
		exam_other_qua:exam_other_qua,class_other_qua:class_other_qua,		
		degree_other:degree_other,spec_other:spec_other
	}, function(data) {
		if(parseInt(data) > 0) {
			
			
			
			if(($('#marital_event').val()=='1' && curr_marital_status==10)  || $('#marital_event').val()=='3'){
				$("#spouse_quali_radioDiv").show();
				$("#spouse_quali_radio1").prop("checked", true);
				$("#spouse_Qualifications").show();
			}
			else{
				$("#spouse_quali_radioDiv").hide();
			}
			
			if(curr_marital_status==8){
				$("#marr_quali_radio2").prop("checked", true);
				$("#marr_quali_radio1").prop("disabled", true);
			}
			
			alert("Data Saved/Updated Successfully")
			$('#spouse_qualification_ch_id').val(data);
		} else alert(data)
	}).fail(function(xhr, textStatus, errorThrown) {
		alert("fail to fetch")
	});
	}

function remove_spouse_qualificationFn(){
	var comm_id=$('#comm_id').val();	
	  $.post('updated_family_marriage_delete_action?' + key + "=" + value, {comm_id:comm_id,qali_status:1}, function(data){ 
	                   if(data=='1'){
	                	   
	                	   $('#spouse_qualification_ch_id').val(0);
	                	  
// 	                       alert("Data Deleted Successfully");
// 	                       $("#marr_save").hide();    
// 	                       $("#marriage_remarriageDiv").hide();
// 	               		$("#divorceDiv").hide();
// 	               		$("#divorce_date").val("");
// 	               		$("#marital_status").val('0');
// //	                		$("#spouse_quali_radioDiv").hide();              		
// 	               		$('#MaritalForm').hide();


	                   }	
	                   
	 }).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  });
}

function getSpouseQualificationData() {
	var q_id = $('#census_id').val();
	var i = 0;
	$.post('getSpouseQualificationData?' + key + "=" + value, {
		q_id: q_id
	}, function(j) {
		var v = j.length;
		if(v != 0) {
			$('#spouse_yrOfPass').val(j[0].passing_year);
			$('#spouse_div_class').val(j[0].div_class);
			$('#spouse_institute_place').val(j[0].institute);
			$('#spouse_qualification_ch_id').val(j[0].id);
			$("input[type=checkbox][name='spouse_multisub']").prop("checked", false);
			var subjectslist = j[0].subject.split(',');
			for(k = 0; k < subjectslist.length; k++) {
				$("input[type=checkbox][name='spouse_multisub'][value='" + subjectslist[k] + "']").prop("checked", true);
				$("#spouse_sub_quali option:first").text('Subjects(' + subjectslist.length + ')');
			}
			$('#spouse_quali_sub_list').empty()
			$("input[type='checkbox'][name='spouse_multisub']").each(function() {
				if(this.checked) {
					$('#spouse_quali_sub_list').append("<span class='subspan'>" + this.parentElement.innerText + "<i class='fa fa-times' style='margin: 5px;  font-size: 15px;' onclick='removeSpouseSubFn(" + this.value + ")'></i><span> <br>");
				}
			});
			$("#spouse_checkboxes").show();
			$('#spouse_quali_type').val(j[0].type);
			var typethisT = document.getElementById('spouse_quali_type');
			fn_qualification_typeChange(typethisT,'spouse_quali','quali_degree_spouse','spouse_specialization');
			
			if(j[0].examination_pass != null) {
				$('#spouse_quali').val(j[0].examination_pass);
				var qualithisT = document.getElementById('spouse_quali');
				fn_ExaminationTypeChange(qualithisT,'quali_degree_spouse','spouse_specialization');
			}
			
			if(j[0].degree != null) {
				$('#quali_degree_spouse').val(j[0].degree);
				fn_otherShowHide(document.getElementById('quali_degree_spouse'),'other_div_qualiDegSpouse','quali_deg_otherSpouse');
				
			}
			if(j[0].specialization != null) {
			$('#spouse_specialization').val(j[0].specialization);
			 fn_otherShowHide(document.getElementById('spouse_specialization'),'other_div_qualiSpecSpouse','quali_spec_otherSpouse');
			}
			
			
			
			
		
			
			
			var examother="";
			var classother="";
			var deg_other="";
			var spec_other="";
			
			if(j[i].exam_other!=null)
	  			examother=j[i].exam_other;
	  	    if(j[i].class_other!=null)
	  	    	classother=j[i].class_other;
	  	    
	  	  if(j[i].degree_other!=null)
	  		deg_other=j[i].degree_other;
	  	   if(j[i].specialization_other!=null)
	  	    	spec_other=j[i].specialization_other;
	  	    
	  	
	 	  	$('#exam_otherSpouse').val(examother);
	 	
	 		 $('#class_otherSpouse').val(classother);
	 	 
	 		 $('#quali_deg_otherSpouse').val(deg_other);
	 	
	 		 $('#quali_spec_otherSpouse').val(spec_other);
	 		 
	 		 fn_otherShowHide(document.getElementById('spouse_div_class'),'other_div_classSpouse','class_otherSpouse');
//	 		 fn_otherShowHide(document.getElementById('spouse_quali'),'other_div_examSpouse','exam_otherSpouse');
//	 		 fn_otherShowHide(document.getElementById('spouse_specialization'),'other_div_qualiSpecSpouse','quali_spec_otherSpouse');
//	 		 fn_otherShowHide(document.getElementById('quali_degree_spouse'),'other_div_qualiDegSpouse','quali_deg_otherSpouse');
	 	 
			 $('#spouse_Qualifications').show();
			  if( curr_marital_status==10  || curr_marital_status==9 || curr_marital_status==11 || curr_marital_status==12 ){
					$("#spouse_quali_radioDiv").show();
					$("#spouse_quali_radio1").prop("checked", true);
					 $('#spouse_quali_labelDiv').hide();
				}
			  else if(curr_marital_status==8){
					$("#marr_quali_radio2").prop("checked", true);
					$("#marr_quali_radio1").prop("disabled", true);
					$('#marriage_remarriageDiv').hide();
					  $('#mrgbtn_save').hide();
						$("#spouse_quali_radioDiv").hide();
					  $('#spouse_quali_labelDiv').show();
					  $('#mrgqualibtn_save').show();
				}
				
		}
	});
}


function fn_qualification_typeChange(obj,set_id,sec_id,th_id) {
	var q_type = obj.value;
	var options = '<option value="0" >--Select--</option>';
	$("select#"+set_id).html(options);
	if(q_type!='0'){
		$.post('GetExaminatonPassed?' + key + "=" + value, {
			q_type: q_type
			
		}, function(j) {
			if(j.length > 0) {
			
				for(var i = 0; i < j.length; i++) {
					options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
				}
				$("select#"+set_id).html(options);
			}
		});
	}
	
	var qualithisT = document.getElementById(set_id);
	fn_ExaminationTypeChange(qualithisT,sec_id,th_id);
	
	
	
}

function fn_ExaminationTypeChange(obj,set_id,sec_id) {
	var e_pass =  obj.value;

// 	var qualification = $("select#quali_type").val();
	var options = '<option value="0" >--Select--</option>';
	$("select#"+set_id).html(options);
	if(e_pass!='0'){
		$.post('GetExaminatonPassedDegree?' + key + "=" + value, {
			e_pass: e_pass
			
		}, function(j) {
			
			if(j.length > 0) {
			
				for(var i = 0; i < j.length; i++) {
					options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
				}
				$("select#"+set_id).html(options);
			}
			
		});
	}
	

	 fn_other_exam();	
	 fn_otherShowHide(document.getElementById('quali_degree'),'other_div_qualiDeg','quali_deg_other');
	 fn_otherShowHide(document.getElementById('specialization'),'other_div_qualiSpec','quali_spec_other');
	 
	 fn_otherShowHide(document.getElementById('spouse_quali'),'other_div_examSpouse','exam_otherSpouse');
	 fn_otherShowHide(document.getElementById('quali_degree_spouse'),'other_div_qualiDegSpouse','quali_deg_otherSpouse');
	 fn_otherShowHide(document.getElementById('spouse_specialization'),'other_div_qualiSpecSpouse','quali_spec_otherSpouse');
}



var other="OTHERS";
function fn_otherShowHide(obj,Divother_id,other_id){
	var thisText=$("#"+obj.id+" option:selected").text();
	if(thisText==other){
		$('#'+Divother_id).show();
	}
	else{
		$('#'+Divother_id).hide();
		$('#'+other_id).val('');
	}
}
function marital_eventchange(){
	

	var marital_eventvalue=$('#marital_event').val();
	if(marital_eventvalue=='1' || marital_eventvalue=='3'){
		$("#marriage_remarriageDiv").show();
		$("#divorceDiv").hide();
 		$("#divorce_date").val("");		
		$("#marital_status").val('8');
		$("#spouse_quali_radioDiv").show();
		if(curr_marital_status==13){
			$("#seperateDiv").show();
 			$("#separated_from_dtDiv").show();
 			$("#separated_to_dtDiv").show();
 			$("#separated_to_dt").val('');
 			getfamily_marriageDetails();
		}
		if(curr_marital_status==8){
			$("#seperateDiv").hide();
 			$("#separated_from_dtDiv").hide();
 			$("#separated_to_dtDiv").hide();
 			$("#separated_to_dt").val('');
 			$("#separated_from_dt").val('');
		}
		
	}
	else if(marital_eventvalue=='2'){
		$("#marital_status").val('9');
		getfamily_marriageDetails();
		$("#divorceDiv").show();
		$("#divorce_widow_widower_dtlbl").text("Date of Divorce")
		$("#spouse_quali_radioDiv").hide();
		if(curr_marital_status==13){
			$("#seperateDiv").hide();
 			$("#separated_from_dtDiv").hide();
 			$("#separated_to_dtDiv").hide();
 			$("#separated_to_dt").val('');
		}
		if(curr_marital_status==8){
			$("#seperateDiv").hide();
 			$("#separated_from_dtDiv").hide();
 			$("#separated_to_dtDiv").hide();
 			$("#separated_to_dt").val('');
 			$("#separated_from_dt").val('');
		}
	}
	else if( marital_eventvalue=='4'){
		$("#marital_status").val('13');
		getfamily_marriageDetails();
		$("#spouse_quali_radioDiv").hide();
		$("#divorceDiv").hide();
		$("#seperateDiv").show();
 		$("#separated_from_dtDiv").show();
	}
	else if( marital_eventvalue=='5'){
		$("#marital_status").val('11');
		getfamily_marriageDetails();
		$("#spouse_quali_radioDiv").hide();
		$("#divorceDiv").show();
		$("#divorce_widow_widower_dtlbl").text("Date of Death")
		if(curr_marital_status==13){
			$("#seperateDiv").hide();
 			$("#separated_from_dtDiv").hide();
 			$("#separated_to_dtDiv").hide();
 			$("#separated_to_dt").val('');
		}
		if(curr_marital_status==8){
			$("#seperateDiv").hide();
 			$("#separated_from_dtDiv").hide();
 			$("#separated_to_dtDiv").hide();
 			$("#separated_to_dt").val('');
 			$("#separated_from_dt").val('');
		}
// 		$("#divorce_date").val("");
	}
	else if( marital_eventvalue=='6'){
		$("#marital_status").val('12');
		getfamily_marriageDetails();
		$("#spouse_quali_radioDiv").hide();
		$("#divorceDiv").show();
		$("#divorce_widow_widower_dtlbl").text("Date of Death")
		if(curr_marital_status==13){
			$("#seperateDiv").hide();
 			$("#separated_from_dtDiv").hide();
 			$("#separated_to_dtDiv").hide();
 			$("#separated_to_dt").val('');
		}
		if(curr_marital_status==8){
			$("#seperateDiv").hide();
 			$("#separated_from_dtDiv").hide();
 			$("#separated_to_dtDiv").hide();
 			$("#separated_to_dt").val('');
 			$("#separated_from_dt").val('');
		}
// 		$("#divorce_date").val("");
	}
	else{
		$("#marriage_remarriageDiv").hide();
		$("#divorceDiv").hide();
 //		$("#divorce_date").val("");
		$("#marital_status").val('0');
		$("#spouse_quali_radioDiv").hide();
	}
	
	if((marital_eventvalue==1 && curr_marital_status==10)  || marital_eventvalue==3){
		$("#spouse_quali_radioDiv").show();
	}
	else{
		$("#spouse_quali_radioDiv").hide();
	}
	
	
	}
	




function fn_setSpecializationSpouse(query){
	
	$.post("getSpecialization?"+key+"="+value, {query:query}).done(function(j) {
		 	
		 	
	 		var options = '<option   value="0">'+ "--Select--" + '</option>';
			for ( var i = 0; i < j.length; i++) {
			
					options += '<option   value="' + j[i] + '" name="'+j[i]+'" >'+ j[i] + '</option>';
				
			}
			
			$("select#spouse_specialization").html(options);
			
		}).fail(function(xhr, textStatus, errorThrown) {
});
}


function spouse_quali_radioFn(){
	
	var a = $('input:radio[name=spouse_quali_radio]:checked').val();
	if(a.toLowerCase() == "yes"){	
		$("#spouse_Qualifications").show();
		}
	else{	
		$("#spouse_Qualifications").hide();
		}
	}
	
function showCheckboxesSpouse() {

	 $("#spouse_checkboxes").toggle();
	$("#spouse_searchSubject").val(''); 
	 
	 $('.spouse_subjectlist').each(function () {       
	 $(this).show()
	})
	}
	
	
$("input[type='checkbox'][name='spouse_multisub']").click(function() {
    // access properties using this keyword
    var num=0;
    $('#spouse_quali_sub_list').empty()
    $("input[type='checkbox'][name='spouse_multisub']").each(function () {  
        if ( this.checked ) {
        	$('#spouse_quali_sub_list').append("<span class='subspan'>"+this.parentElement.innerText+"<i class='fa fa-times' style='margin: 5px;  font-size: 15px;' onclick='removeSpouseSubFn("+this.value+")'></i><span> <br>");
            num=num+1;   
        }
        
    });
    if(num!=0)
        $("#spouse_sub_quali option:first").text('Subjects('+num+')');
    else
        $("#spouse_sub_quali option:first").text("Select Subjects");
});

function removeSpouseSubFn(value){
	$("input[type='checkbox'][name='spouse_multisub'][value='" + value + "']").attr('checked', false);
	
	 var num=0;
	 $('#spouse_quali_sub_list').empty()
	    $("input[type='checkbox'][name='spouse_multisub']").each(function () {  
	        if ( this.checked ) {
	        	$('#spouse_quali_sub_list').append("<span class='subspan'>"+this.parentElement.innerText+"<i class='fa fa-times' style='margin: 5px;  font-size: 15px;' onclick='removeSpouseSubFn("+this.value+")'></i><span> <br>");
	            num=num+1;
	            
	            
	        }
	        
	    });
	    if(num!=0)
	        $("#spouse_sub_quali option:first").text('Subjects('+num+')');
	    else
	        $("#spouse_sub_quali option:first").text("Select Subjects");
	
}
//***************** END MARRIED DETAILS************************//




//*************************************Start CDA ACCOUNT NO & CONTACT DETAIL*****************************//
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
function validate_cda_acnt_no()
{
	 if ($("input#gmail").val().trim()=="") {
			alert("Please Enter Gmail ");
			$("input#gmail").focus();
			return false;
		}
	 if(!validateEmail(document.getElementById('gmail'))){
		 $("input#gmail").focus();
			return false;
	 }
	 if ($("input#nic_mail").val().trim()=="") {
			alert("Please Enter NIC Mail ");
			$("input#nic_mail").focus();
			return false;
		}
	 if(!validateEmail(document.getElementById('nic_mail'))){
		 $("input#nic_mail").focus();
			return false;
	 }
	 if ($("input#mobile_no").val().trim()=="" || $("input#mobile_no").val().trim().length<10 || $("input#mobile_no").val().trim().length>10) {
			alert("Please Enter Mobile No ");
			$("input#mobile_no").focus();
			return false;
		}
	    var formvalues=$("#form_cda_acnt_no").serialize();
		var census_id=$("#census_id").val();	
		var cda_id=$("#cda_id").val();	
		var comm_id=$("#comm_id").val();	
		formvalues+="&census_id="+census_id+"&cda_id="+cda_id+"&comm_id="+comm_id;
		 $.post('cda_acnt_noaction?' + key + "=" + value,formvalues, function(data){
			      
		          if(data=="update")
		        	  alert("Data Updated Successfully");
		          else if(parseInt(data)>0){
		        	  $("#cda_id").val(data);	
		        	  alert("Data Saved Successfully")
		          }
		          else
		        	  alert(data)
		 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		  		});
}

function get_cda_acnt_no(){
	var comm_id=$("#comm_id").val();
	$.post('cda_acnt_no_GetData_xml?' + key + "=" + value,{comm_id:comm_id}, function(j){
		var v=j.length;
		if(v!=0){
			$("#cda_account_no").val(j[0].cda_account_no);
			$("#gmail").val(j[0].gmail);
			$("#nic_mail").val(j[0].nic_mail);
			$("#mobile_no").val(j[0].mobile_no);
			$("#cda_id").val(j[0].id);
		}
	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
}


//*************************************end CDA ACCOUNT NO & CONTACT DETAIL*****************************//



//*************************************Start Children Details*****************************//



		////Children Add
		chi = 1;
		chi_srno = 1;
		function m_children_details_add_fn(q) {
			if ($('#m_children_details_add' + q).length) {
				$("#m_children_details_add" + q).hide();
			}
			if (q != 0)
			chi_srno += 1;
			chi = q + 1;
			$("table#m_children_details_table").append('<tr id="tr_children'+chi+'">'
				+ '<td class="child_srno">'
				+ chi_srno
				+ '</td>'
				+ '<td style="width: 10%;"> <input type="sib_name'+chi+'" id="sib_name'+chi+'" class="form-control"  autocomplete="off" maxlength="50" onkeypress="return onlyAlphabets(event);" oninput="this.value = this.value.toUpperCase()"></td>'
				+ '<td style="width: 10%;">'
				+' <input type="text" name="sib_date_of_birth'+chi+'" id="sib_date_of_birth'+chi+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
				+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
				+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">'
				+ '</td>'
				+ '<td style="width: 10%;"> <input type="checkbox" id="sib_type'+chi+'" name="sib_type'+chi+'" value="Yes">' 
				+ '</td>'
				+ '<td style="width: 10%;"> <select name="sib_relationship'+chi+'" id="sib_relationship'+chi+'" class="form-control"   >'
				+ '<option value="0">--Select--</option>'
				+ '<c:forEach var="item" items="${getChild_RelationshipList}" varStatus="num">'
				+ '<option value="${item[0]}" name="${item[1]}">${item[1]}</option>'
				+ '</c:forEach></select></td>'
				+ '<td style="width: 10%;"> <input type="checkbox" id="sib_adopted'+chi+'" name="sib_adopted'+chi+'" value="Yes" onclick="adoption('+chi+ ');"></td>'
				+ '<td style="width: 10%;">'
				/* <input type="date" id="sib_adop_date'+chi+'" name="sib_adop_date'+chi+'" class="form-control autocomplete" autocomplete="off"  maxlength="10"  max="${date}" min="1899-01-01" readonly="readonly"></td>' */
				+' <input type="text" name="sib_adop_date'+chi+'" id="sib_adop_date'+chi+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" readonly="readonly" style="width: 85%;display: inline;" '
				+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
				+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">'
				+ '</td>'
				+'<td style="width: 10%;">'
				+'<input type="text" id="aadhar_no'+chi+'" name="aadhar_no'+chi+'"  class="form-control autocomplete" autocomplete="off" maxlength="14" onkeypress="return isAadhar(this,event);">'
				+'</td>'
				+'<td style="width: 10%;">'
				+'<input type="text" id="pan_no'+chi+'" name="pan_no'+chi+'"  class="form-control autocomplete" autocomplete="off" maxlength="10" onchange="isPAN(this);" oninput="this.value = this.value.toUpperCase()">'
				+'</td>'
				+ '<td style="display:none;"><input type="text" id="sib_ch_id'+chi+'" name="sib_ch_id'+chi+'"   value="0" class="form-control autocomplete" autocomplete="off" ></td>'
				
				+ '<td style="width: 5%;"><input type="checkbox" ' + '	id="child-ex' + chi + '" name="child-ex' + chi + '" ' + '	value="Yes"  ' + '	onclick="childCB(' + chi + ');"></td> '
				+ '<td style="width: 10%;"> <select name="child_service' + chi + '" id="child_service' + chi + '" class="form-control-sm form-control"  onchange="otherfunction(' + chi + ')"  >' + '<option value="0">--Select--</option>' + '<c:forEach var="item" items="${getExservicemenList}" varStatus="num">' + '<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' + '</c:forEach></select></td>' + '<td style="width: 15%;"><input type="text" ' + '  maxlength="15"	id="child_personal_no' + chi + '" name="child_personal_no' + chi + '" ' + '	class="form-control" autocomplete="off" ></td> '
				+ '<td style="width: 10%;"><input type="text" ' + '	id="other_child_ser' + chi + '" name="other_child_ser' + chi + '" ' + '	class="form-control" autocomplete="off" maxlength="50" onkeypress="return onlyAlphabets(event);"></td>'
				
				+ '<td style="width: 10%;"><a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "m_children_details_save'
				+ chi
				+ '" onclick="m_children_details_save_fn('
				+ chi
				+ ');" ><i class="fa fa-save"></i></a>'
				+ '<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "m_children_details_add'
				+ chi
				+ '" onclick="m_children_details_add_fn('
				+ chi
				+ ');" ><i class="fa fa-plus"></i></a>'
				+ '<a style="display:none;" class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "m_children_details_remove'
				+ chi
				+ '" onclick="m_children_details_remove_fn('
				+ chi
				+ ');"><i class="fa fa-trash"></i></a>'
				+ '</td></tr>');
			datepicketDate('sib_date_of_birth'+chi);
			datepicketDate('sib_adop_date'+chi);
			childCB(chi);
			otherfunction(chi);
		}
		//remove
		function m_children_details_remove_fn(R){
			if (R == '1') {
				alert("Can't Delete From Here!! \nPlease Ask Approver to Reject This Entry");
			}
			else{
			var rc = confirm("Are You Sure! You Want To Delete");
			 if(rc){
			var sib_ch_id=$('#sib_ch_id'+R).val();
			  $.post('m_children_details_delete_action?' + key + "=" + value, {sib_ch_id:sib_ch_id }, function(data){ 
					  
					   if(data=='1'){
						   $("tr#tr_children"+R).remove(); 
							 if(R==chi){
								 R = R-1;
								 var temp=true;
								 for(i=R;i>=1;i--){
								 if( $('#m_children_details_add'+i).length )         
								 {
									 $("#m_children_details_add"+i).show();
									 temp=false;
									 chi=i;
									 break;
								 }}
								 
								 if(temp){
									 m_children_details_add_fn(0);
									}
					  			 }
							 $('.child_srno').each(function(i, obj) {
									obj.innerHTML=i+1;
									chi_srno=i+1;
									 });
									 alert("Data Deleted Successfully");
					   }
						 else{
								 
							 alert("Data not Deleted ");
						 }
					   
			 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
			  		});
			 }
			}
		}
		//save children
		function m_children_details_save_fn(ps) {
			
			var sib_name = $('#sib_name' + ps).val();
			var sib_date_of_birth = $('#sib_date_of_birth' + ps).val();
			//var sib_type = $('#sib_type' + ps).val();
			if ($('#sib_type' + ps).is(":checked")){
				sib_type = "Yes";
			}
			else{
				sib_type = "No";
			}
			
			
			var aadhar_no = $('#aadhar_no' + ps).val().split('-').join('');
			var pan_no = $('#pan_no' + ps).val();
			
			var sib_ch_id = $('#sib_ch_id' + ps).val();
			var comm_id = $('#comm_id').val();
			var census_id = $('#census_id').val();
			
			var child_ser = $("select#child_service"+ps).val();
			var child_pers_no = $("#child_personal_no"+ps).val();
			if($('#child-ex' + ps).is(":checked")){
				var child_ex = "Yes";
			}else{
				var child_ex = "";
			}
			var other_child_ser = $("#other_child_ser"+ps).val();
			
			 if (sib_name.trim()=="") {
					alert("Please Enter Name");
					$('#sib_name' + ps).focus();
					return false;
				}
			  if (sib_date_of_birth == "DD/MM/YYYY" || sib_date_of_birth.trim()=="") {
					alert("Please Enter Date of Birth");
					$('#sib_date_of_birth' + ps).focus();
					return false;
				} 
			  var currentdate=new Date();
				if(getformatedDate(sib_date_of_birth) > currentdate){
					   alert("Enter Valid Date of Birth ");			  
					   $("input#sib_date_of_birth"+ps).focus();
					   return false;
					   
					}
				if(getformatedDate(sib_date_of_birth) <= getformatedDate($('#dob_date').val())){
					alert("Date of Birth of Child Should Not Greater Than Date of Birth His Father or Mother");
					$("input#sib_date_of_birth"+ps).focus();
					return false;
				}
				
				 var sib_relationship = $('#sib_relationship' + ps).val();
			 if (sib_relationship == 0) {
					alert("Please Select Relationship");
					$('#sib_relationship' + ps).focus();
					return false;
				}
			
				var sib_adop_date = $('#sib_adop_date' + ps).val();
				if ($('#sib_adopted' + ps).is(":checked")){
					sib_adopted = "Yes";
					  if (sib_adop_date == "DD/MM/YYYY" || sib_adop_date.trim()=="") {
							alert("Please Enter Adoption Date");
							$('#sib_adop_date' + ps).focus();
							return false;
						} 
					  if(getformatedDate(sib_date_of_birth) > getformatedDate(sib_adop_date)){
						   alert("Adoption Date Should Be Greater Or Equal To Date of Birth ");			  
						   $("input#sib_adop_date"+ps).focus();
						   return false;
						   
						}
				}
				else{
					sib_adopted = "No";
				}
				 if (aadhar_no.trim()!="" && aadhar_no.length < 12) {
						alert("Please Enter Valid Aadhaar Card No");
						$('#aadhar_no' + ps).val("");
						$('#aadhar_no' + ps).focus();
						return false;
					}
			 
				
			
				var child = $( "#child_service"+ps+" option:selected" ).text();
				if($('#child-ex' + ps).is(":checked")){
					if(child_ser == 0){
						alert("Please Select Child Service");
						$("select#child_service"+ps).focus();
						return false;
					}
					if(child_ser == 1){
					if(child_pers_no.trim()==""){
						alert("Please Enter Child Personal No");
						$("#child_personal_no"+ps).focus();
						return false;
					}
					}
					if(child_pers_no.trim()!=''){
						if(child_pers_no.trim().length < 5 || child_pers_no.trim().length >15){
						alert("Please Enter Valid Child Personal No");
						$("#child_personal_no"+ps).focus();
						return false;
						}
					}
					if (child == "OTHER"){
							if($('#other_child_ser' + ps).val().trim()==""){
								alert("Please Enter Other Child Service");
							$('#other_child_ser' + ps).focus();
							return false;
							}
						}
				}	
				else{
					child="";	
				}
	

			$.post('m_children_details_action?' + key + "=" + value, {
				sib_name : sib_name,
				sib_date_of_birth : sib_date_of_birth,sib_type:sib_type,
				sib_relationship : sib_relationship,sib_adopted:sib_adopted,sib_adop_date:sib_adop_date,aadhar_no:aadhar_no,pan_no:pan_no,
				sib_ch_id : sib_ch_id,
				comm_id : comm_id,census_id:census_id,
				child_ser:child_ser,child_pers_no:child_pers_no,child_ex:child_ex,other_child_ser:other_child_ser,child:child
			}, function(data) {

				if (data == "update")
					alert("Data Updated Successfully");
				else if (parseInt(data) > 0) {
					$('#sib_ch_id' + ps).val(data);
					$("#m_children_details_add" + ps).show();
					$("#m_children_details_remove" + ps).show();
					alert("Data Saved Successfully")
				} else
					alert(data);
			}).fail(function(xhr, textStatus, errorThrown) {
				alert("fail to fetch")
			});
		}
		
		//get children
			
	function m_children_Details() {
// 			var p_id = $('#census_id').val();
			var comm_id = $('#comm_id').val();
			var i = 0;
			$.post('getm_children_detailsData_xml?' + key + "=" + value,{comm_id:comm_id},function(j) {
				var v = j.length;
				if (v != 0) {
					$('#m_children_detailstbody').empty();
					for (i; i < v; i++) {
						x = i + 1;
						var dob = ParseDateColumncommission(j[i].date_of_birth);
						if(j[i].date_of_adpoted != null){
							var adob = ParseDateColumncommission(j[i].date_of_adpoted);
							}
							else
								var adob ="";
						$("table#m_children_details_table").append('<tr id="tr_children'+x+'">'
							+ '<td class="child_srno">'
							+ x
							+ '</td>'
							+ '<td style="width: 10%;"> <input type="sib_name'+x+'" id="sib_name'+x+'"  value="'+j[i].name+'" class="form-control"  maxlength="50" onkeypress="return onlyAlphabets(event);" oninput="this.value = this.value.toUpperCase()">'
							+ '<td style="width: 10%;">'
							+' <input type="text" name="sib_date_of_birth'+x+'" id="sib_date_of_birth'+x+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
							+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
							+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="'+dob+'">'
							+ '</td>'
							+ '<td style="width: 10%;"> <input type="checkbox" id="sib_type'+x+'" name="sib_type'+x+'" >'
							+ '</td>'
							+ '<td style="width: 10%;"> <select name="sib_relationship'+x+'" id="sib_relationship'+x+'" class="form-control"   >'
							+ '<option value="0">--Select--</option>'
							+ '<c:forEach var="item" items="${getChild_RelationshipList}" varStatus="num">'
							+ '<option value="${item[0]}" name="${item[1]}">${item[1]}</option>'
							+ '</c:forEach></select></td>'
							+ '<td style="width: 10%;"> <input type="checkbox" id="sib_adopted'+x+'" name="sib_adopted'+x+'" value="Yes" onclick="adoption('+ x+ ');"></td>'
							+ '<td style="width: 10%;">'
							/* <input type="date" id="sib_adop_date'+x+'" name="sib_adop_date'+x+'" value="'+adob+'" class="form-control autocomplete" autocomplete="off"  maxlength="10"  max="${date}" min="1899-01-01" readonly="readonly"></td>' */
							+' <input type="text" name="sib_adop_date'+x+'" id="sib_adop_date'+x+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" readonly="readonly" style="width: 85%;display: inline;" '
							+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
							+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="'+adob+'">'
							+ '</td>'
							+'<td style="width: 10%;">'
							+'<input type="text" id="aadhar_no'+x+'" name="aadhar_no'+x+'" value="'+j[i].aadhar_no+'" class="form-control autocomplete" autocomplete="off" maxlength="14" onkeypress="return isAadhar(this,event);">'
							+'</td>'
							+'<td style="width: 10%;">'
							+'<input type="text" id="pan_no'+x+'" name="pan_no'+x+'"  value="'+j[i].pan_no+'" class="form-control autocomplete" autocomplete="off" maxlength="10" onchange="isPAN(this);" oninput="this.value = this.value.toUpperCase()">'
							+'</td>'
							+ '<td style="display:none;"><input type="text" id="sib_ch_id'+x+'" name="sib_ch_id'+x+'"   value="'+j[i].id+'"  class="form-control autocomplete" autocomplete="off" ></td>'

							+ '<td style="width: 5%;"><input type="checkbox" ' + '	id="child-ex' + x + '" name="child-ex' + x + '" ' + '	value="Yes"  ' + '	onclick="childCB(' + x + ');"></td> '
							+ '<td style="width: 10%;"> <select name="child_service' + x + '" id="child_service' + x + '" class="form-control-sm form-control"  onchange="otherfunction(' + x + ')"  >' + '<option value="0">--Select--</option>' + '<c:forEach var="item" items="${getExservicemenList}" varStatus="num">' + '<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' + '</c:forEach></select></td>' + '<td style="width: 15%;"><input type="text" ' + '	maxlength="15" id="child_personal_no' + x + '" name="child_personal_no' + x + '" ' + '	class="form-control" autocomplete="off" value="' + j[i].child_personal_no + '" ></td> '
							+ '<td style="width: 10%;"><input type="text" ' + '	id="other_child_ser' + x + '" name="other_child_ser' + x + '" ' + '	class="form-control" autocomplete="off" maxlength="50" onkeypress="return onlyAlphabets(event);"></td>'
							
							
							+ '<td style="width: 10%;"><a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "m_children_details_save'+ x + '" onclick="m_children_details_save_fn('+ x+ ');" ><i class="fa fa-save"></i></a>'
							+ '<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "m_children_details_add'+ x+ '" onclick="m_children_details_add_fn('+ x+ ');" ><i class="fa fa-plus"></i></a>'
                          + '<a  class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "m_children_details_remove'+ x+ '" onclick="m_children_details_remove_fn('+ x + ');"><i class="fa fa-trash"></i></a>'
							+ '<td style="display:none;"><input type="text" id="status'+x+'" name="status'+x+'"   value="'+j[i].status+'"  class="form-control autocomplete" autocomplete="off" ></td>'
							+ '</td></tr>');
						$('#sib_relationship' + x).val(j[i].relationship);
						$('#sib_type' + x).val(j[i].type);
						$('#child_service' + x).val(j[i].child_service);
						$('#other_child_ser' + x).val(j[i].other_child_ser);
						if(j[i].if_child_ser =="Yes"){
							$("input[type=checkbox][name='child-ex"+x+"']").prop("checked",true);
						}
						if(j[i].type =="Yes"){
							$("input[type=checkbox][name='sib_type"+x+"']").prop("checked",true);
						}
						
						if(j[i].adoted =="Yes"){
							$("input[type=checkbox][name='sib_adopted"+x+"']").prop("checked",true);
							$('#sib_adop_date' + x).attr('readonly', false);
						}
						datepicketDate('sib_date_of_birth'+x);
						datepicketDate('sib_adop_date'+x);
						isAadhar(document.getElementById('aadhar_no'+x))
						childCB(x);
						otherfunction(x);
						
					}
					chi = v;
					chi_srno = v;
					$('#m_children_details_add' + chi).show();
				}
			});
		}
		function childCB(a) {
			if ($('#child-ex'+a).is(":checked"))
			{
				$('#child_service' + a).attr('readonly', false);
				$('#child_personal_no' + a).attr('readonly', false);
			}
			else{
//		 		$('#ser-ex' + a).attr('readonly', true);
				$('#child_service' + a).attr('readonly', true);
				$('#child_personal_no' + a).attr('readonly', true);
				$('#other_child_ser' + a).attr('readonly', true);
				$('#child_service' + a).val(0);
				$('#child_personal_no' + a).val("");
				$('#other_child_ser' + a).val("");
			}
			
		}
		function otherfunction(k){
			//26-01-1994
			var child = $( "#child_service"+k+" option:selected" ).text();
			if ($( "#child_service"+k ).val() == "0" || !($('#child-ex'+k).is(":checked"))){
				$('#other_child_ser' + k).attr('readonly', true);
				$('#child_personal_no' + k).attr('readonly', true);
				$('#child_personal_no' + k).val('');
			}
			else if (child == "OTHER"){
				$('#other_child_ser' + k).attr('readonly', false);
				$('#child_personal_no' + k).attr('readonly', true);
				$('#child_personal_no' + k).val('');
			}
			else if (child != "OTHER"){
				$('#other_child_ser' + k).attr('readonly', true);
				$('#child_personal_no' + k).attr('readonly', false);
				$('#other_child_ser' + k).val('');
			}
			
		}
		
		function adoption(a) {
// 			var nk=$("input[type=checkbox][name='sib_adopted"+a+"']").val();
// 			//var nk =$('#sib_adopted' + a).val();
// 			if(nk = "Yes"){
// 				//alert("11111");
// 				$('#sib_adop_date' + a).attr('readonly', false);
// 			}
// 			else{
// 				$('#sib_adop_date' + a).attr('readonly', true);
// 			}
			if ($('#sib_adopted'+a).is(":checked"))
			{
				$('#sib_adop_date' + a).attr('readonly', false);
			}
			else{
				$('#sib_adop_date' + a).attr('readonly', true);
				$('#sib_adop_date' + a).val("");
			}
		}
		//*************************************End Children Details*****************************//











	//*************************************Start Nok *****************************//

function fn_nok_Country() {
	
	var text = $("#nok_country option:selected").text();
	//alert("hello");
	if(text == "OTHERS"){
		$("#nok_other_id").show();
	}
	else{
		$("#nok_other_id").hide();
		$("#ctry_other").val("");
		$("#nok_other_te").hide();
	}
	

		 var contry_id =$('select#nok_country').val();

		 		 	$.post("getStateListFormcon1?"+key+"="+value, {contry_id:contry_id}).done(function(j) {
		 		 				 	
		 		 			 	
		 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
		 				for ( var i = 0; i < j.length; i++) {
		 				
		 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
		 					
		 				}
		 				
		 				$("select#nok_state").html(options);
						fn_nok_state();
						fn_nok_district();
		 			}).fail(function(xhr, textStatus, errorThrown) {
		 	});
	}
	
	
	
function fn_nok_state() {
	
	var text = $("#nok_state option:selected").text();
	if(text == "OTHERS"){
		$("#nok_other_st").show();
	}
	else{
		$("#nok_other_st").hide();
		$("#st_other").val("");
	}	

	 var state_id = $('select#nok_state').val();

	 		 	$.post("getDistrictListFormState1?"+key+"="+value, {state_id:state_id}).done(function(j) {
	 		 				 	
	 		 			 	
	 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
	 				for ( var i = 0; i < j.length; i++) {
	 				
	 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
	 					
	 				}
	 				
	 				$("select#nok_district").html(options);
					fn_nok_district();
	 			}).fail(function(xhr, textStatus, errorThrown) {
	 	});
}	

function fn_nok_district() {
	
	var text = $("#nok_district option:selected").text();
	//alert("hello");
	if(text == "OTHERS"){
		$("#nok_other_dist").show();
	}
	else{
		$("#nok_other_dist").hide();
		$("#dist_other").val("");
	}
	
	 var Dist_id = $('select#nok_district').val();

	 		 	$.post("getTehsilListFormDistrict1?"+key+"="+value, {Dist_id:Dist_id}).done(function(j) {
	 		 				 	
	 		 			 	
	 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
	 				for ( var i = 0; i < j.length; i++) {
	 				
	 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
	 					
	 				}
	 				
	 				$("select#nok_tehsil").html(options);

	 			}).fail(function(xhr, textStatus, errorThrown) {
	 	});
}
function fn_nok_tehsil() {
	
	var text = $("#nok_tehsil option:selected").text();
	
	if(text == "OTHERS"){
		$("#nok_other_te").show();
	}
	else{
		$("#nok_other_te").hide();
		$("#nok_tehsil_other").val("");
	}
}	

function validate_save_nok_details(){
//  	if ($("input#authority").val().trim()=="") {
// 		alert("Please Enter Authority");
// 		$("input#authority").focus();
// 		return false;
// 	}
//  	if ($("input#date_authority").val() == "DD/MM/YYYY") {
// 		alert("Please Enter Date of Authority");
// 		$("input#date_authority").focus();
// 		return false;
// 	}  
	
	if($("input#date_authority").val() != "DD/MM/YYYY" && $("input#date_authority").val() != ""){
	if(getformatedDate($("input#comm_date").val()) >= getformatedDate($("#date_authority").val())) {
		alert("Authority Date should be Greater than Commission Date");
		return false;
  }
	}
	if ($("input#nok_name").val().trim()=="") {
		alert("Please Enter NOK Name");
		$("input#nok_name").focus();
		return false;
	}
	if ($("select#nok_relation").val() == "0") {
		alert("Please Enter NOK Relation");
		$("select#nok_relation").focus();
		return false;
	}
	if ($("select#nok_country").val() == "0") {
		alert("Please Select  Country");
		$("select#nok_country").focus();
		return false;
	}
  var text1 = $("#nok_country option:selected").text();
	
	if(text1 == "OTHERS" && $("input#ctry_other").val().trim()==""){
		alert("Please Enter  Country Other");
		$("input#ctry_other").focus();
		return false;
	}
	if ($("select#nok_state").val() == "0") {
		alert("Please Select  Nok State");
		$("select#nok_state").focus();
		return false;
	}
  var text2 = $("#nok_state option:selected").text();
	
	if(text2 == "OTHERS" && $("input#st_other").val().trim()==""){
		alert("Please Enter State Other");
		$("input#st_other").focus();
		return false;
	}
	if ($("select#nok_district").val() == "0") {
		alert("Please Select Nok District");
		$("select#nok_district").focus();
		return false;
	}
	 var text3 = $("#nok_district option:selected").text();
		
		if(text3 == "OTHERS" && $("input#dist_other").val().trim()==""){
			alert("Please Enter District Other");
			$("input#dist_other").focus();
			return false;
		}
	if ($("select#nok_tehsil").val() == "0" ) {
		alert("Please Select Tehsil");
		$("select#nok_tehsil").focus();
		return false;
	}
	var text5 = $("#nok_tehsil option:selected").text();
	
	if(text5 == "OTHERS" && $("input#nok_tehsil_other").val().trim()==""){
		alert("Please Enter Tehsil Other");
		$("input#nok_tehsil_other").focus();
		return false;
	}
	if ($("input#nok_village").val().trim()=="") {
		alert("Please Enter  Village/Town/City");
		$("input#nok_village").focus();
		return false;
	}
	if ($("input#nok_pin").val().trim()==""  ) {
		alert("Please Enter  Nok Pin");
		$("input#nok_pin").focus();
		return false;
	}
	if ($("input#nok_rail").val().trim()=="") {
		alert("Please Enter  Nearest Railway Station");
		$("input#nok_rail").focus();
		return false;
	}
	var n = $('input:radio[name=nok_rural_urban]:checked').val();
	if(n == undefined){	
		alert("Please select Is  Rural /Urban/Semi Urban");
		return false;
		}
	if ($("input#nok_mobile_no").val().trim()=="" || $("input#nok_mobile_no").val().trim().length<10  || $("input#nok_mobile_no").val().trim().length>10) {
		alert("Please Enter  NOK Mobile No");
		$("input#nok_mobile_no").focus();
		return false;
	}
   var formvalues=$("#form_nok_addr_details_form").serialize();
   var comm_date=$('#comm_date').val();
	var census_id=$("#census_id").val();
	var comm_id =$("#comm_id").val();
	var nok_id=$("#nok_id").val();		
		formvalues+="&census_id="+census_id+"&nok_id="+nok_id+"&comm_id="+comm_id+"&comm_date="+comm_date;	
		 $.post('nok_details_action?' + key + "=" + value,formvalues, function(data){
			 if(data=="update")
	        	  alert("Data Updated Successfully");
	          else if(parseInt(data)>0){
	        	  $("#nok_id").val(data);	
	        	  alert("Data Saved Successfully")
	          }
	          else
	        	  alert(data)
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});
}
function get_nokaddress_details(){
	var comm_id=$("#comm_id").val();	
	$.post('nok_details_GetData_xml?' + key + "=" + value,{comm_id:comm_id }, function(j){
		var v=j.length;
			if(v!=0){
				//////nok//////
				$("#authority").val(j[0].authority);
				$('#date_authority').val(ParseDateColumncommission(j[0].date_authority)); 
				$("#nok_name").val(j[0].nok_name);
				$("#nok_relation").val(j[0].nok_relation);
				$("#nok_country").val(j[0].nok_country);
				fn_nok_Country();
				$("#nok_state").val(j[0].nok_state);
				fn_nok_state();
				$("#nok_district").val(j[0].nok_district);
				fn_nok_district();
				$("#nok_tehsil").val(j[0].nok_tehsil);
				$("#nok_village").val(j[0].nok_village);
				$("#nok_pin").val(j[0].nok_pin);
				$("#nok_rail").val(j[0].nok_near_railway_station);
				/* $("#ctry_other").val(j[0].ctry_other);
				$("#st_other").val(j[0].st_other);
				$("#dist_other").val(j[0].dist_other);
				$("#tehsil_other").val(j[0].tehsil_other); */
				$("#nok_mobile_no").val(j[0].nok_mobile_no);
				 var nok= j[0].nok_rural_urban_semi;
		            if(nok == "rural"){
		                    $("#nok_rural").prop("checked", true);
		             } 
		            if(nok == "urban"){
		                    $("#nok_urban").prop("checked", true);
		             }
		            if(nok == "semi_urban")
		            {
		                    $("#nok_semi_urban").prop("checked", true);
		             }  
		            
		            var text6 = $("#nok_country option:selected").text();
					if(text6 == "OTHERS"){
						$("#ctry_other").val(j[0].ctry_other);
						$("#nok_other_id").show();
					}
					else{
						$("#nok_other_id").hide();
					}
					
					var text7 = $("#nok_state option:selected").text();
					if(text7 == "OTHERS"){
						$('#st_other').val(j[0].st_other);
						$("#nok_other_st").show();
					}
					else{
						$("#nok_other_st").hide();
					}
					var text8 = $("#nok_district option:selected").text();
					if(text8 == "OTHERS"){
						$("#dist_other").val(j[0].dist_other);
						$("#nok_other_dist").show();
					}
					else{
						$("#nok_other_dist").hide();
					}
					var text9 = $("#nok_tehsil option:selected").text();
					if(text9 == "OTHERS"){
						$("#nok_tehsil_other").val(j[0].tehsil_other);
						$("#nok_other_te").show();
					}
					else{
						$("#nok_other_te").hide();
					}
				$("#nok_id").val(j[0].id);
			}
		  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
			});
	} 
	////---------------------------------------------------------------------end NOK-----------------------------------------------------------------------//
	///*-----------------------------------------------------------------CDA ACCOUNT -----------------------------------------------------------------------//
	
	
	function get_cda(){
// 		 var p_id=$('#census_id').val(); 
		 var comm_id=$('#comm_id').val(); 
		var status="0";
		 var i=0;
		  $.post('cda_GetData_XML?' + key + "=" + value, {comm_id:comm_id,status:status}, function(j){
				var v=j.length;
				
				if(v!=0){
					
					$('#id').val(j[i].id);
					 $("#cda_acc_no").val(j[i].cda_acc_no);
					 $("#clear").hide();
					 
					 $("#personnel_no_c").val('${personnel_no_edit}');
					 $("input#personnel_no_c").attr('readonly', true);
					 $("#cda_rank").text($("#hd_p_rank").val());
					 $("#cda_cadet_name").text($("#cadet_name").text());
					 $("#cda_app_sus_no").text($("#app_sus_no").text());
					 $("#cda_app_unit_name").text($("#app_unit_name").text());
				}
		  });
	}	
	function validate_insertcda_save(){
		if ($("input#personnel_no_c").val() == "") {
			alert("Please Enter Personnel Number.");
			$("input#personnel_no_c").focus();
			return false;
		}  
		if ($("input#cda_acc_no").val() == "") {
				alert("Please Enter CDA Account Number.");
				$("input#cda_acc_no").focus();
				return false;
		}    
		
		
		var personnel_no=$('#personnel_no_c').val();
		var cda_acc_no=$('#cda_acc_no').val();
		var cda_acc_id=$('#id').val();
		var census_id=$('#census_id').val();
		var comm_id=$('#comm_id').val();
		   $.post('Change_of_cda_action?' + key + "=" + value, {personnel_no:personnel_no,cda_acc_no:cda_acc_no,census_id:census_id,comm_id:comm_id,cda_acc_id:cda_acc_id}, function(data){
			      
		          if(data=="update"){
		        	  alert("Data Updated Successfully");
			          $("#search_cda").submit();
		          }
		          else if(parseInt(data)>0){
		        	 $('#cda_acc_id').val(data);
		        	  alert("Data Saved SuccesFully")
		          } else
		        	  alert(data);
		 	  		}).fail(function(xhr, textStatus, errorThrown) {
		 	  			alert("fail to fetch")
		  		});
	}
	//---------------------------------------------------------------------END CDA ACCOUNT-------------------------------------------------------//
	
	
//************************************* START CHANGE OF NAME DETAIL*****************************//
function get_change_of_name(){
	
//  var p_id=$('#census_id').val(); 
	 var comm_id=$('#comm_id').val();
	 var i=0;
	  $.post('change_of_name_GetData_xml?' + key + "=" + value, {comm_id:comm_id }, function(j){
			var v=j.length;
			if(v!=0){
				$('#name_id').val(j[i].id);
				 $("#na_authority").val(j[i].authority);
				 $("#na_date_of_authority").val(ParseDateColumncommission(j[i].date_of_authority));
				 $("#name").val(j[i].name);
				 $("#change_in_name_date").val(ParseDateColumncommission(j[i].change_in_name_date));      
			}
	  });
}	

function validate_Change_of_name_save_fn(){
	
	 if ($("input#na_authority").val().trim()=="") {
			alert("Please Enter Authority");
			$("input#na_authority").focus();
			return false;
		}
	 	 if ($("input#na_date_of_authority").val() == "DD/MM/YYYY" || $("input#na_date_of_authority").val().trim()=="") {
			alert("Please Enter Date of Authority ");
			$("input#na_date_of_authority").focus();
			return false;
		} 
	 	if(getformatedDate($("input#comm_date").val()) > getformatedDate($("#na_date_of_authority").val())) {
			alert("Authority Date should be Greater than Commission Date");
			return false;
	  }
	 	if ($("input#name").val().trim()=="") {
			alert("Please Enter Name ");
			$("input#name").focus();
			return false;
		}

	 	if ($("input#change_in_name_date").val() == "DD/MM/YYYY" || $("input#change_in_name_date").val().trim()=="") {
	 				alert("Please Select Date of Change in Name ");
	 				$("input#change_in_name_date").focus();
	 				return false;
	 			} 
	 			
	var comm_id=$('#comm_id').val();
	var census_id=$('#census_id').val();
	var name=$('#name').val();
	var name_id=$('#name_id').val();
	var comm_date=$('#comm_date').val();
	var change_in_name_date=$('#change_in_name_date').val();
	var formdata=$('#form_change_of_name').serialize();
	formdata+="&census_id="+census_id+"&comm_id="+comm_id+"&comm_date="+comm_date+"&name_id="+name_id;	
	   $.post('change_of_nameaction?' + key + "=" + value, formdata, function(data){		      
	          if(data=="update")
	        	  alert("Data Updated Successfully");
	          else if(parseInt(data)>0){
	        	
	        	 $('#name_id').val(data);
	        	  alert("Data Saved Successfully")
	          }
	          else
	        	  alert(data);
	    		          window.location.reload();
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});	
}
//*************************************end Change of name*****************************//

	
	/////////////////////////////////CHange OF Appointment////////////////////////////////////
	function validate_appointment_save(){
	var tos_date=$('#tos_date').val();
	var comm_id=$('#comm_id').val();
	var comm_date=$('#comm_date').val();
	var census_id=$('#census_id').val();
	var status=$('#status').val();
	var appoint_id=$('#appoint_id').val();
	var parent_sus_no =$('#parent_sus_no').val();
	var parent_unit=$('#parent_unit').val();
	var x_list_loc=$('#x_list_loc').val();
	
	var formdata=$('#form_change_of_appointment').serialize();
	formdata+="&census_id="+census_id+"&comm_id="+comm_id+"&comm_date="+comm_date+"&tos_date="+tos_date;	
	  if ($("input#appt_authority").val().trim()=="") {
		alert("Please Enter Authority.");
		$("input#appt_authority").focus();
		return false;
	}
	  if ($("input#appt_date_of_authority").val() == "DD/MM/YYYY" || $("input#appt_date_of_authority").val().trim()=="") {
		alert("Please Enter Date Of Authority. ");
		$("input#appt_date_of_authority").focus();
		return false;
	} 
	  if(getformatedDate($("input#comm_date").val()) > getformatedDate($("#appt_date_of_authority").val())) {
			alert("Authority Date should be Greater than Commission Date");
			return false;
	  }
  if ($("select#appointment").val() == "0") {
		alert("Please Select Appointment. ");
		$("select#appointment").focus();
		return false;
	} 
   if ($("input#date_of_appointment").val() == "DD/MM/YYYY" || $("input#date_of_appointment").val().trim()=="") {
		alert("Please Enter Date Of Appointment. ");
		$("input#date_of_appointment").focus();
		return false;
	}  
   if($("input#tos_date").val()!="" && getformatedDate($("input#tos_date").val()) > getformatedDate($("#date_of_appointment").val())) {
		alert("Date of Appointment Date should be Greater than TOS Date");
		return false;
   }

 if($('#appointment').val() == "9562" || $('#appointment').val() == "9565" )
  {
		   if (($("#parent_sus_no").val().trim()== "") && ($("#parent_unit").val().trim()== "") && ($("#x_list_loc").val().trim()== "")) {
				alert("Please Enter X-List SUS No Or  X-List Unit Name Or  X-List Location.");
				$("input#parent_sus_no").focus();
				return false;
			}
  }
 
	   $.post('change_appointmentAction?' + key + "=" + value, formdata, function(data){		      
		   if(data=="update"){
			   alert("Data Updated Successfully");
		   }
		   else if(parseInt(data)>0){
	        	 $('#appoint_id').val(data);
	        	  alert("Data Saved Successfully")
	          }
	          else
	        	  alert(data)
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});	
}
	function get_Appointment(){
// 	 var p_id=$('#census_id').val(); 
	 var comm_id=$('#comm_id').val(); 
	 var i=0;
	  $.post('get_Appointment_xml?' + key + "=" + value, { comm_id:comm_id}, function(j){
			var v=j.length;
			if(v!=0){

				$('#appoint_id').val(j[i].id);
				 $("#appt_authority").val(j[i].appt_authority);
				 $("#appt_date_of_authority").val(ParseDateColumncommission(j[i].appt_date_of_authority));
				 $("#appointment").val(j[i].appointment);
				 $("#date_of_appointment").val(ParseDateColumncommission(j[i].date_of_appointment));
				 $("#parent_sus_no").val(j[i].parent_sus_no);
				 $("#parent_unit").val(j[i].parent_unit);
				 $("#x_list_loc").val(j[i].x_list_loc);
				 if (j[i].appointment == "9562" || j[i].appointment == "9565") {
						$("#att_dapu").show();
						$("#att_dapu1").show();
					} else {
						$("#att_dapu").hide();
						$("#att_dapu1").hide();
					}
				 
			}
	  });
}	
function appointment_att() {

	if ($("#appointment").val() == "9562" || $("#appointment").val() == "9565") {
		$("#att_dapu").show();
		$("#att_dapu1").show();
		 $("div#Note").show();
	} else {
		$("#att_dapu").hide();
		$("#att_dapu1").hide();
		$("div#Note").hide();
	}
}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	////////////////////////////////MEDICAL CATEGORY///////////////////////////////////////////////////
	function oncheck_1bx(){
	
	if ($("#check_1bx").is(':checked')) {
		$("#shape1bxdiv").show();
		$("#shapediv").hide();
		}
	else{
		$("#shape1bxdiv").hide();
		$("#shapediv").show();
	}
}
function AutoCompleteDiagnosis(ele){
	
	var code = ele.value;
	var susNoAuto =$("#"+ele.id);
	susNoAuto.autocomplete({
		source : function(request, response) {
			$.ajax({
				type : 'POST',
				url : "getMNHDistinctICDList?" + key + "=" + value,
				data : {
					a : code,b:"ALL"
				},
				success : function(data) {
					var susval = [];
					var length = data.length - 1;
					var enc = data[length].substring(0, 16);
					for (var i = 0; i < data.length; i++) {
						susval.push(dec(enc, data[i]));
					}
					susval.push("Others");

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
				alert("Please Enter Approved Diagnosis ");			
				susNoAuto.val("");
				susNoAuto.focus();
				return false;
			}
		},
		select : function(event, ui) {

		}
	});
	
}
var classification='1';
$("#mad_classification").change(function(){
	
	classification=this.value;
	setDiagnosis();
	
});

function onShapeValueChange(e,label){
	//26-01-1994
// 	if(e.value=="1"){
		
// 		$("#"+label+"_from_date1").datepicker( "option", "disabled", true );
// 		$("#"+label+"_to_date1").datepicker( "option", "disabled", true );
// 	}
// 	else{
// 		$("#"+label+"_from_date1").datepicker( "option", "disabled", false );
// 		$("#"+label+"_to_date1").datepicker( "option", "disabled", false );
// 	}
}

function oncheck_1bx(){
	
	if ($("#check_1bx").is(':checked')) {
		$("#shape1bxdiv").show();
		$("#shapediv").hide();
		}
	else{
		$("#shape1bxdiv").hide();
		$("#shapediv").show();
	}
}

function onCopeChangebt(e,cope,ind){
	if(ind==1){
		if(e.value == 0 || (cope!= 4 && cope!= 2&& e.value == 1)){
			$('.CopaddbtClass'+cope+ind).hide();
			$('.CopaddbtClass'+cope).hide();
			
		}
		else{
			$('.CopaddbtClass'+cope+ind).show();
			$('.CopaddbtClass'+cope).show();
			
		}
	}
}
function onCCopeChange(e,ind){	
	if(e.value != '2 [c]'){
		$('.cCopClass'+ind).hide();
		$('.cCopClass').hide();
		
	}
	else{
		$('.cCopClass'+ind).show();
		$('.cCopClass').show();
		
	}
	var styleC = $(".cCopClass").css("display");
	for(var i = 1; i<=cCope; i++){
		var	st = $("#c_cvalue"+i).val();
		 
		 if(st == '2 [c]'){
			 $('.cCopClass').show();
				 $('#c_cother'+i).show();
		 }
		 else if(i == cCope && styleC == "none"  ){
			 $('#c_cother'+i).show();
			 $('.cCopClass'+i).hide();
		 }
		 styleC = $(".cCopClass").css("display");
		if(i == cCope && styleC != "none"  ){
			 if(st == '2 [c]'){
				 $('#c_cother'+i).show();
					$('.cCopClass'+i).show();
			 }
			 else {
				 $('#c_cother'+i).hide();
					$('.cCopClass'+i).show();
			}
			 
		 }
	}
	if(styleC == "none"  ){
		for(var i = 1; i<=cCope; i++){
			 $('#c_cother'+i).show();
			 $('.cCopClass'+i).hide();
		 }
	}
	
}

function onECopeChange(e,ind){
	if(e.value == '1'){
		$('.eCopClass'+ind).show();
		$('.eCopClass').show();
	}
	else{
		$('.eCopClass'+ind).hide();
		$('.eCopClass').hide();
		$('.eCop_otherClass'+ind).hide();
		$('.eCop_otherClass').hide();
		$('#c_esubvalue'+ind).val(0);
	}
	
	var styleC = $(".eCopClass").css("display");
	for(var i = 1; i<=eCope; i++){
		var	st = $("#c_evalue"+i).val();
		 if(st == '1'){
			 $('.eCopClass').show();
				 $('#c_esubvalue'+i).show();
		 }
		 else if(i == cCope && styleC == "none"  ){
			 $('#c_esubvalue'+i).show();
			 $('.eCopClass'+i).hide();
		 }
		 styleC = $(".eCopClass").css("display");
		if(i == eCope && styleC != "none"  ){
			 if(st == '1'){
				 $('#c_esubvalue'+i).show();
					$('.eCopClass'+i).show();
			 }
			 else {
				 $('#c_esubvalue'+i).hide();
					$('.eCopClass'+i).show();
			}
			 
		 }
	}
	if(styleC == "none"  ){
		for(var i = 1; i<=eCope; i++){
			 $('#c_esubvalue'+i).show();
			 $('.eCopClass'+i).hide();
		 }
	}
	
	onECopeSubChange(e,ind);
	
}

function onECopeSubChange(e,ind){
	if(e.value == 'k'){
		$('.eCop_otherClass'+ind).show();
		$('.eCop_otherClass').show();
	}
	else{
		$('.eCop_otherClass'+ind).hide();
		$('.eCop_otherClass').hide();
	}
	
	var styleC = $(".eCop_otherClass").css("display");
	for(var i = 1; i<=eCope; i++){
		var	st = $("#c_esubvalue"+i).val();
		 
		 if(st == 'k'){
			 $('.eCop_otherClass').show();
				 $('#c_esubvalueother'+i).show();
		 }
		 else if(i == cCope && styleC == "none"  ){
			 $('#c_esubvalueother'+i).show();
			 $('.eCop_otherClass'+i).hide();
		 }
		 styleC = $(".eCop_otherClass").css("display");
		if(i == eCope && styleC != "none"  ){
			 if(st == 'k'){
				 $('#c_esubvalueother'+i).show();
					$('.eCop_otherClass'+i).show();
			 }
			 else {
				 $('#c_esubvalueother'+i).hide();
					$('.eCop_otherClass'+i).show();
			}
			 
		 }
	}
	if(styleC == "none"  ){
		for(var i = 1; i<=eCope; i++){
			 $('#c_esubvalueother'+i).show();
			 $('.eCop_otherClass'+i).hide();
		 }
	}
}

function setDiagnosis(){
// 	if(classification=='1'){
// 		$('.diagnosisClass1').hide();
// 		$('.diagnosisClass2').hide();
// 		$('.diagnosisClass3').hide();
// 		$('.diagnosisClass4').hide();
// 		$('.diagnosisClass5').hide();
// 		$('.diagnosisClass6').hide();
		
// 	}
// 	if(classification=='2'){
// 		$('.diagnosisClass1').show();
// 		$('.diagnosisClass2').hide();
// 		$('.diagnosisClass3').hide();
// 		$('.diagnosisClass4').hide();
// 		$('.diagnosisClass5').hide();
// 		$('.diagnosisClass6').hide();
// 	}
// 	else if(classification=='3'){
// 		$('.diagnosisClass1').show();
// 		$('.diagnosisClass2').show();
// 		$('.diagnosisClass3').hide();
// 		$('.diagnosisClass4').hide();
// 		$('.diagnosisClass5').hide();
// 		$('.diagnosisClass6').hide();
// 	}
// 	else if(classification=='4'){
// 		$('.diagnosisClass1').show();
// 		$('.diagnosisClass2').show();
// 		$('.diagnosisClass3').show();
// 		$('.diagnosisClass4').show();
// 		$('.diagnosisClass5').show();
// 		$('.diagnosisClass6').show();
// 	}
}

function statusChange(Shape,position,Shape_status){
	
// 	if(classification=='1' && (Shape_status==2  || Shape_status==3)){
// 		 $("select#s_status"+position).val('1');
// 		 alert("Please First Change Medical Classification ");	
// 	}
// 	else{
// 	
	//26-01-1994
// 	if( Shape_status==1 || Shape_status==0){
// 		$('.diagnosisClass'+Shape+position).hide();
// 		$('.diagnosisClass'+Shape).hide();
// 		$('.addbtClass'+Shape+position).hide();
// 		$('.addbtClass'+Shape).hide();
		
// 	}else{
// 		$('.diagnosisClass'+Shape+position).show();
// 		$('.diagnosisClass'+Shape).show();
// 		$('.addbtClass'+Shape+position).show();
// 		$('.addbtClass'+Shape).show();
// 	}
// 	var shapeVal = 0;
// 	var stlab;
// 	if(Shape==1){
// 		shapeVal = sShape;
// 		stlab = "s_status";
// 	 }
// 	 else  if(Shape==2){
// 		 shapeVal = hShape;
// 		 stlab = "h_status";
// 	 }
// 	else  if(Shape==3){
// 		shapeVal = aShape;
// 		stlab = "a_status";
// 	}
// 	else  if(Shape==4){
// 		shapeVal = pShape;
// 		stlab = "p_status";
// 	}
// 	else  if(Shape==5){
// 		shapeVal = eShape;
// 		stlab = "e_status";
// 	}
	
// 	for(var i = 1; i<=shapeVal; i++){
		 
// 		var	st = $("#"+stlab+i).val();
// 		 var styleC = $(".diagnosisClass"+Shape).css("display");
// 		 var styleCval = $(".diagnosisClass"+Shape+i).css("display");
		 
// 		 if(st == 2 || st == 3){
// 			 $('.diagnosisClass'+Shape).show();
// 			 //if(styleCval == "none"){
// 				 $('#_diagnosis_code'+Shape+i).show();
// 			 //}
// 		 }
// 		 else if(i == shapeVal && styleC == "none"  ){
// 			 $('#_diagnosis_code'+Shape+i).show();
// 			 $('.diagnosisClass'+Shape+i).hide();
// 		 }
		 
// 		 if(i == shapeVal && styleC != "none"  ){
// 			 if(st == 2 || st == 3){
// 			 $('.diagnosisClass'+Shape+i).show();
// 			 $('#_diagnosis_code'+Shape+i).show();
// 			 }
// 			 else {
// 				 $('.diagnosisClass'+Shape+i).show();
// 				 $('#_diagnosis_code'+Shape+i).hide();
// 			}
// 		 }
		 
	 
// 	}

var slable ="";
	ShapeCount = 1;
	if(Shape == 1) {
		slable = "s";
		ShapeCount = sShape;
			}
			 else if(Shape == 2) {
				 slable = "h";
				 ShapeCount = hShape;
			} else if(Shape == 3) {
				slable = "a";
				ShapeCount = aShape;
			} else if(Shape == 4) {
				slable = "p";
				ShapeCount = pShape;
			} else if(Shape == 5) {
				slable = "e";
				ShapeCount = eShape;
			}
	
	if((Shape_status == 1 || Shape_status == 0) ) {
		$("#" + slable + "_to_date"+ position).datepicker("option", "disabled", true);
		$("#" + slable + "_to_date"+ position).val("");
		if ( position==1) {
			$('.diagnosisClass' + Shape + position).hide();
			
			if (ShapeCount ==1) {
				$('.addbtClass' + Shape + position).hide();
		 		$('.addbtClass' + Shape).hide();
		 		$('.diagnosisClass' + Shape).hide();
			}
	 		
		}
		else{
			$('.diagnosisClass' + Shape + position).css("visibility","hidden");
	 		$('.addbtClass' + Shape + position).show();
	 		$('.addbtClass' + Shape).show();
		}
		
	}else {
		$("#" + slable + "_to_date"+ position).datepicker("option", "disabled", false);
		if ( position==1) {
			$('.diagnosisClass' + Shape + position).show();
			$('.diagnosisClass' + Shape).show();
			$('.diagnosisClass' + Shape + position).css("visibility","visible");
		}
		else{
			$('.diagnosisClass' + Shape + position).css("visibility","visible");
		}
		$('.addbtClass' + Shape + position).show();
		$('.addbtClass' + Shape).show();
	}
	
	 $.post('getShapevalueUrl?' + key + "=" + value, {Shape_status:Shape_status }, function(j){
		 
		 var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
             var length = j.length;
             if(length != 0){           
		             for ( var i = 0; i < length; i++) {                  
		                             options += '<option value="' + j[i][1]+ '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
		             }  
		             if(Shape == 1) {
						    if(Shape_status == 1){
						    	$("select#sShape_value" + position).html(options);
								$("select#sShape_value" + position).val(1);
							}else{
						        $("select#sShape_value" + position).html(options);
							}
					}
					 else if(Shape == 2) {
						    if(Shape_status == 1){
						        $("select#hShape_value" + position).html(options);
								$("select#hShape_value" + position).val(1);
							}else{
						        $("select#hShape_value" + position).html(options);
							}
					} else if(Shape == 3) {
						   if(Shape_status == 1){
							     $("select#aShape_value" + position).html(options);
								$("select#aShape_value" + position).val(1);
							}else{
						        $("select#aShape_value" + position).html(options);
							}
					} else if(Shape == 4) {
						   if(Shape_status == 1){
							   $("select#pShape_value" + position).html(options);
								$("select#pShape_value" + position).val(1);
							}else{
						        $("select#pShape_value" + position).html(options);
							}
					} else if(Shape == 5) {
						  if(Shape_status == 1){
							  $("select#eShape_value" + position).html(options);
								$("select#eShape_value" + position).val(1);
							}else{
						        $("select#eShape_value" + position).html(options);
							}
					}
		            
             }
		 
		 
	 }).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
	 
// 	}
}

function AutoCompleteDiagnosis(ele){
	
	var code = ele.value;
	var susNoAuto =$("#"+ele.id);
	susNoAuto.autocomplete({
		source : function(request, response) {
			$.ajax({
				type : 'POST',
				url : "getMNHDistinctICDList?" + key + "=" + value,
				data : {
					a : code,b:"ALL"
				},
				success : function(data) {
					var susval = [];
					var length = data.length - 1;
					var enc = data[length].substring(0, 16);
					for (var i = 0; i < data.length; i++) {
						susval.push(dec(enc, data[i]));
					}
					susval.push("Others");

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
				alert("Please Enter Approved Diagnosis ");			
				susNoAuto.val("");
				susNoAuto.focus();
				return false;
			}
		},
		select : function(event, ui) {

		}
	});
	
}





sShape=1;
function sShape_add_fn(q){
	 if( $('#sShape_add'+q).length )        
	 {
			$("#sShape_add"+q).hide();
			 $("#sShape_remove"+q).hide();	

	 }
	 sShape=sShape+1;

	 $("input#sShape_count").val(sShape);
	 
	$("table#s_madtable").append('<tr id="tr_sShape'+sShape+'">'
	+'<td  style="width: 2%;">'+sShape+'</td>'
	+'<td>'
	+'<select name="s_status'+sShape+'" id="s_status'+sShape+'" '
	+'	class="form-control-sm form-control"  onchange="statusChange(1,'+sShape+',this.options[this.selectedIndex].value)">'
// 	+'		<option value="0">--Select--</option>'
	+'	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">'
	+'	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>'
	+'	</c:forEach>'
	+'		</select>'
	+'  </td>'
	+'   <td style="">'
	+'<select name="sShape_value'+sShape+'" id="sShape_value'+sShape+'" '
	+'		class="form-control-sm form-control">'
	+'		</select>	</td>'
// 	+'	<td style="">'
// 	+'		<input type="date" id="s_from_date'+sShape+'" name="s_from_date'+sShape+'" max="${date_without_time}" class="form-control autocomplete" autocomplete="off">	'					                             
// 	+'		 </td>'	   
// 	+'<td style="">'
// 	+'		<input type="date" id="s_to_date'+sShape+'" name="s_to_date'+sShape+'" class="form-control autocomplete" autocomplete="off">		'				                             
// 	+'   </td>'
	+'<td style="""> '
	+' <input type="text" name="s_from_date'+sShape+'" id="s_from_date'+sShape+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
	+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
	+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">'
	+'</td>'
	+'<td style="""> '
	+' <input type="text" name="s_to_date'+sShape+'" id="s_to_date'+sShape+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
	+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
	+'	onchange="clickrecall(this,\'DD/MM/YYYY\')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">'
	+'</td>'

	
	+'  <td style="visibility:hidden; "  class="diagnosisClass1'+sShape+'">'
	+' <input type="text" name="_diagnosis_code1'+sShape+'" id="_diagnosis_code1'+sShape+'" class="form-control-sm form-control" autocomplete="off" '
	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
	+'   </td>'
// 	+'    <td style=" display: none" class="diagnosisClass2">'
// 	+'<input type="text" name="s_diagnosis_code2'+sShape+'" id="s_diagnosis_code2'+sShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+' placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">		'				                              
// 	+' </td>'
// 	+'   <td style=" display: none" class="diagnosisClass3">'
// 	+' <input type="text" name="s_diagnosis_code3'+sShape+'" id="s_diagnosis_code3'+sShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">		'				                              
// 	+'  </td>'
// 	+'    <td style=" display: none" class="diagnosisClass4">'
// 	+' <input type="text" name="s_diagnosis_code4'+sShape+'" id="s_diagnosis_code4'+sShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+' placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
// 	+'  </td>'
// 	+'    <td style=" display: none" class="diagnosisClass5">'
// 	+' <input type="text" name="s_diagnosis_code5'+sShape+'" id="s_diagnosis_code5'+sShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">				'		                              
// 	+'  </td>'
// 	+'   <td style=" display: none" class="diagnosisClass6">'
// 	+' <input type="text" name="s_diagnosis_code6'+sShape+'" id="s_diagnosis_code6'+sShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
// 	+'   </td>'
	+' <td style="display:none;"><input type="text" id="sShape_ch_id'+sShape+'" name="sShape_ch_id'+sShape+'"  value="0" class="form-control autocomplete" autocomplete="off" ></td>'   
	+'<td style="width: 10%;" >'
	+'   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "sShape_add'+sShape+'" onclick="sShape_add_fn('+sShape+');" ><i class="fa fa-plus"></i></a>'
	+'    <a class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "sShape_remove'+sShape+'" onclick="sShaperemove_fn('+sShape+');"><i class="fa fa-trash"></i></a> '
	+'</td>'
	+'</tr>');
	
	 
	 datepicketDate('s_from_date'+sShape);
	 datepicketDate('s_to_date'+sShape);
	 $( "#s_to_date"+sShape ).datepicker( "option", "maxDate", null);
	 statusChange(1, sShape, $("#s_status" + sShape).val());
// 	if(q!=0){
// 		var preShape=sShape-1;
// 			$("#sShape_value"+preShape+" > option").clone().appendTo("#sShape_value"+sShape);
// 			$("#s_status"+sShape).val($("#s_status"+preShape).val());
// 			$("#sShape_value"+sShape).val($("#sShape_value"+preShape).val());
// 			statusChange(1,sShape,$("#s_status"+preShape).val());
// 			$("#s_status"+sShape+" option[value='1']").remove();
// 			$("#s_status"+preShape+" option[value='1']").remove();
// 			$("#s_status"+preShape+" option[value='0']").remove();
// 		 }
	setDiagnosis();
}

function sShaperemove_fn(R){
	var r = confirm("Are You Sure! You Want To Delete This Row");
	 if(r){				   
					 $("tr#tr_sShape"+R).remove(); 
						 R = R-1;
					 $("#sShape_add"+R).show();
					 $("#sShape_remove"+R).show();	
					 $("input#sShape_count").val(R);
					 sShape=sShape-1;
// 						 if(sShape == 1){
// 							var s_val = $("#s_status"+sShape).val();
// 							var lis1 = '<option value="${getShapeStatusList[0][1]}" name="${getShapeStatusList[0][1]}">${getShapeStatusList[0][0]}</option>'
// 							 var lis2 = $("#s_status"+sShape).html();
// 							 $('#s_status'+sShape).empty().append('<option value="0">--Select--</option>'+lis1+lis2);
// 							 $("#s_status"+sShape).val(s_val);
// 							}
			
	 }

	 }
	 

hShape=1;
function hShape_add_fn(q){
	 if( $('#hShape_add'+q).length )        
	 {
			$("#hShape_add"+q).hide();
			 $("#hShape_remove"+q).hide();	

	 }
	 hShape=hShape+1;
	
	
	 $("input#hShape_count").val(hShape);
	 
	$("table#h_madtable").append('<tr id="tr_hShape'+hShape+'">'
	+'<td  style="width: 2%;">'+hShape+'</td>'
	+'<td>'
	+'<select name="h_status'+hShape+'" id="h_status'+hShape+'" '
	+'	class="form-control-sm form-control"  onchange="statusChange(2,'+hShape+',this.options[this.selectedIndex].value)">'
// 	+'		<option value="0">--Select--</option>'
	+'	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">'
	+'	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>'
	+'	</c:forEach>'
	+'		</select>'
	+'  </td>'
	+'   <td style="">'
	+'<select name="hShape_value'+hShape+'" id="hShape_value'+hShape+'" '
	+'		class="form-control-sm form-control">'
	+'		</select>	</td>'
// 	+'	<td style="">'
// 	+'		<input type="date" id="h_from_date'+hShape+'" name="h_from_date'+hShape+'" max="${date_without_time}" class="form-control autocomplete" autocomplete="off">	'					                             
// 	+'		 </td>'	   
// 	+'<td style="">'
// 	+'		<input type="date" id="h_to_date'+hShape+'" name="h_to_date'+hShape+'" class="form-control autocomplete" autocomplete="off">		'				                             
// 	+'   </td>'
	
	+'<td style="""> '
	+' <input type="text" name="h_from_date'+hShape+'" id="h_from_date'+hShape+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
	+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
	+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">'
	+'</td>'
	+'<td style="""> '
	+' <input type="text" name="h_to_date'+hShape+'" id="h_to_date'+hShape+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
	+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
	+'	onchange="clickrecall(this,\'DD/MM/YYYY\')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">'
	+'</td>'
	
	+'  <td style="visibility:hidden; "  class="diagnosisClass2'+hShape+'">'
	+' <input type="text" name="_diagnosis_code2'+hShape+'" id="_diagnosis_code2'+hShape+'" class="form-control-sm form-control" autocomplete="off" '
	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
	+'   </td>'
// 	+'    <td style=" display: none" class="diagnosisClass2">'
// 	+'<input type="text" name="h_diagnosis_code2'+hShape+'" id="h_diagnosis_code2'+hShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+' placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">		'				                              
// 	+' </td>'
// 	+'   <td style=" display: none" class="diagnosisClass3">'
// 	+' <input type="text" name="h_diagnosis_code3'+hShape+'" id="h_diagnosis_code3'+hShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">		'				                              
// 	+'  </td>'
// 	+'    <td style=" display: none" class="diagnosisClass4">'
// 	+' <input type="text" name="h_diagnosis_code4'+hShape+'" id="h_diagnosis_code4'+hShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+' placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
// 	+'  </td>'
// 	+'    <td style=" display: none" class="diagnosisClass5">'
// 	+' <input type="text" name="h_diagnosis_code5'+hShape+'" id="h_diagnosis_code5'+hShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">				'		                              
// 	+'  </td>'
// 	+'   <td style=" display: none" class="diagnosisClass6">'
// 	+' <input type="text" name="h_diagnosis_code6'+hShape+'" id="h_diagnosis_code6'+hShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
// 	+'   </td>'
	+' <td style="display:none;"><input type="text" id="hShape_ch_id'+hShape+'" name="hShape_ch_id'+hShape+'"  value="0" class="form-control autocomplete" autocomplete="off" ></td>'   
	+'<td style="width: 10%;" >'
	+'   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "hShape_add'+hShape+'" onclick="hShape_add_fn('+hShape+');" ><i class="fa fa-plus"></i></a>'
	+'    <a class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "hShape_remove'+hShape+'" onclick="hShaperemove_fn('+hShape+');"><i class="fa fa-trash"></i></a> '
	+'</td>'
	+'</tr>');
	 datepicketDate('h_from_date'+hShape);
	 datepicketDate('h_to_date'+hShape);
	 $( "#h_to_date"+hShape ).datepicker( "option", "maxDate", null);
	 statusChange(2,hShape,$("#h_status"+hShape).val());
// 	if(q!=0){
// 		var preShape=hShape-1;
// 			$("#hShape_value"+preShape+" > option").clone().appendTo("#hShape_value"+hShape);
// 			$("#h_status"+hShape).val($("#h_status"+preShape).val());
// 			$("#hShape_value"+hShape).val($("#hShape_value"+preShape).val());
// 			statusChange(2,hShape,$("#h_status"+preShape).val());
// 			$("#h_status"+hShape+" option[value='1']").remove();
// 			$("#h_status"+preShape+" option[value='1']").remove();
// 			$("#h_status"+preShape+" option[value='0']").remove();
// 		 }
	setDiagnosis();
}

function hShaperemove_fn(R){
	var r = confirm("Are You Sure! You Want To Delete This Row");
	 if(r){				   
					 $("tr#tr_hShape"+R).remove(); 
						 R = R-1;
					 $("#hShape_add"+R).show();
					 $("#hShape_remove"+R).show();
					 $("input#hShape_count").val(R);
					 hShape=hShape-1;
// 					 if(hShape == 1){
// 						var s_val = $("#h_status"+hShape).val();
// 						var lis1 = '<option value="${getShapeStatusList[0][1]}" name="${getShapeStatusList[0][1]}">${getShapeStatusList[0][0]}</option>'
// 						 var lis2 = $("#h_status"+hShape).html();
// 						 $('#h_status'+hShape).empty().append('<option value="0">--Select--</option>'+lis1+lis2);
// 						 $("#h_status"+hShape).val(s_val);
// 						}
	 }

	 }


aShape=1;
function aShape_add_fn(q){
	 if( $('#aShape_add'+q).length )        
	 {
			$("#aShape_add"+q).hide();
			 $("#aShape_remove"+q).hide();	

	 }
	 aShape=aShape+1;
	 $("input#aShape_count").val(aShape);
	 
	$("table#a_madtable").append('<tr id="tr_aShape'+aShape+'">'
	+'<td  style="width: 2%;">'+aShape+'</td>'
	+'<td>'
	+'<select name="a_status'+aShape+'" id="a_status'+aShape+'" '
	+'	class="form-control-sm form-control"  onchange="statusChange(3,'+aShape+',this.options[this.selectedIndex].value)">'
// 	+'		<option value="0">--Select--</option>'
	+'	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">'
	+'	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>'
	+'	</c:forEach>'
	+'		</select>'
	+'  </td>'
	+'   <td style="">'
	+'<select name="aShape_value'+aShape+'" id="aShape_value'+aShape+'" '
	+'		class="form-control-sm form-control">'
	+'		</select>	</td>'
// 	+'	<td style="">'
// 	+'		<input type="date" id="a_from_date'+aShape+'" name="a_from_date'+aShape+'" max="${date_without_time}" class="form-control autocomplete" autocomplete="off">	'					                             
// 	+'		 </td>'	   
// 	+'<td style="">'
// 	+'		<input type="date" id="a_to_date'+aShape+'" name="a_to_date'+aShape+'" class="form-control autocomplete" autocomplete="off">		'				                             
// 	+'   </td>'
	+'<td style="""> '
	+' <input type="text" name="a_from_date'+aShape+'" id="a_from_date'+aShape+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
	+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
	+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">'
	+'</td>'
	+'<td style="""> '
	+' <input type="text" name="a_to_date'+aShape+'" id="a_to_date'+aShape+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
	+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
	+'	onchange="clickrecall(this,\'DD/MM/YYYY\')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">'
	+'</td>'
	+'  <td style="visibility:hidden; "  class="diagnosisClass3'+aShape+'">'
	+' <input type="text" name="_diagnosis_code3'+aShape+'" id="_diagnosis_code3'+aShape+'" class="form-control-sm form-control" autocomplete="off" '
	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
	+'   </td>'
// 	+'    <td style=" display: none" class="diagnosisClass2">'
// 	+'<input type="text" name="a_diagnosis_code2'+aShape+'" id="a_diagnosis_code2'+aShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+' placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">		'				                              
// 	+' </td>'
// 	+'   <td style=" display: none" class="diagnosisClass3">'
// 	+' <input type="text" name="a_diagnosis_code3'+aShape+'" id="a_diagnosis_code3'+aShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">		'				                              
// 	+'  </td>'
// 	+'    <td style=" display: none" class="diagnosisClass4">'
// 	+' <input type="text" name="a_diagnosis_code4'+aShape+'" id="a_diagnosis_code4'+aShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+' placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
// 	+'  </td>'
// 	+'    <td style=" display: none" class="diagnosisClass5">'
// 	+' <input type="text" name="a_diagnosis_code5'+aShape+'" id="a_diagnosis_code5'+aShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">				'		                              
// 	+'  </td>'
// 	+'   <td style=" display: none" class="diagnosisClass6">'
// 	+' <input type="text" name="a_diagnosis_code6'+aShape+'" id="a_diagnosis_code6'+aShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
// 	+'   </td>'
	+' <td style="display:none;"><input type="text" id="aShape_ch_id'+aShape+'" name="aShape_ch_id'+aShape+'"  value="0" class="form-control autocomplete" autocomplete="off" ></td>'   
	+'<td style="width: 10%;" >'
	+'   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "aShape_add'+aShape+'" onclick="aShape_add_fn('+aShape+');" ><i class="fa fa-plus"></i></a>'
	+'    <a class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "aShape_remove'+aShape+'" onclick="aShaperemove_fn('+aShape+');"><i class="fa fa-trash"></i></a> '
	+'</td>'
	+'</tr>');
	
	 datepicketDate('a_from_date'+aShape);
	 datepicketDate('a_to_date'+aShape);
	 $( "#a_to_date"+aShape ).datepicker( "option", "maxDate", null);
	 statusChange(3,aShape,$("#a_status"+aShape).val());
	 
// 	if(q!=0){
// 		var preShape=aShape-1;
// 			$("#aShape_value"+preShape+" > option").clone().appendTo("#aShape_value"+aShape);
// 			$("#a_status"+aShape).val($("#a_status"+preShape).val());
// 			$("#aShape_value"+aShape).val($("#aShape_value"+preShape).val());
// 			statusChange(3,aShape,$("#a_status"+preShape).val());
// 			$("#a_status"+aShape+" option[value='1']").remove();
// 			$("#a_status"+preShape+" option[value='1']").remove();
// 			$("#a_status"+preShape+" option[value='0']").remove();
// 		 }
	setDiagnosis();
}


function aShaperemove_fn(R){
	var r = confirm("Are You Sure! You Want To Delete This Row");
	 if(r){				   
					 $("tr#tr_aShape"+R).remove(); 
						 R = R-1;
					 $("#aShape_add"+R).show();
					 $("#aShape_remove"+R).show();	
					 $("input#aShape_count").val(R);
					 aShape=aShape-1;
// 					 if(aShape == 1){
// 						var s_val = $("#a_status"+aShape).val();
// 						var lis1 = '<option value="${getShapeStatusList[0][1]}" name="${getShapeStatusList[0][1]}">${getShapeStatusList[0][0]}</option>'
// 						 var lis2 = $("#a_status"+aShape).html();
// 						 $('#a_status'+aShape).empty().append('<option value="0">--Select--</option>'+lis1+lis2);
// 						 $("#a_status"+aShape).val(s_val);
// 						}
	 }

	 }
	 


pShape=1;
function pShape_add_fn(q){
	 if( $('#pShape_add'+q).length )        
	 {
			$("#pShape_add"+q).hide();
			 $("#pShape_remove"+q).hide();	

	 }
	 pShape=pShape+1;
	 $("input#pShape_count").val(pShape);
	
	 
	$("table#p_madtable").append('<tr id="tr_pShape'+pShape+'">'
	+'<td  style="width: 2%;">'+pShape+'</td>'
	+'<td>'
	+'<select name="p_status'+pShape+'" id="p_status'+pShape+'" '
	+'	class="form-control-sm form-control"  onchange="statusChange(4,'+pShape+',this.options[this.selectedIndex].value)">'
// 	+'		<option value="0">--Select--</option>'
	+'	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">'
	+'	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>'
	+'	</c:forEach>'
	+'		</select>'
	+'  </td>'
	+'   <td style="">'
	+'<select name="pShape_value'+pShape+'" id="pShape_value'+pShape+'" '
	+'		class="form-control-sm form-control">'
	+'		</select>	</td>'
// 	+'	<td style="">'
// 	+'		<input type="date" id="p_from_date'+pShape+'" name="p_from_date'+pShape+'" max="${date_without_time}" class="form-control autocomplete" autocomplete="off">	'					                             
// 	+'		 </td>'	   
// 	+'<td style="">'
// 	+'		<input type="date" id="p_to_date'+pShape+'" name="p_to_date'+pShape+'" class="form-control autocomplete" autocomplete="off">		'				                             
// 	+'   </td>'
	
	+'<td style="""> '
	+' <input type="text" name="p_from_date'+pShape+'" id="p_from_date'+pShape+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
	+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
	+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">'
	+'</td>'
	+'<td style="""> '
	+' <input type="text" name="p_to_date'+pShape+'" id="p_to_date'+pShape+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
	+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
	+'	onchange="clickrecall(this,\'DD/MM/YYYY\')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">'
	+'</td>'
	+'  <td style="visibility:hidden; "  class="diagnosisClass4'+pShape+'">'
	+' <input type="text" name="_diagnosis_code4'+pShape+'" id="_diagnosis_code4'+pShape+'" class="form-control-sm form-control" autocomplete="off" '
	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
	+'   </td>'
// 	+'    <td style=" display: none" class="diagnosisClass2">'
// 	+'<input type="text" name="p_diagnosis_code2'+pShape+'" id="p_diagnosis_code2'+pShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+' placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">		'				                              
// 	+' </td>'
// 	+'   <td style=" display: none" class="diagnosisClass3">'
// 	+' <input type="text" name="p_diagnosis_code3'+pShape+'" id="p_diagnosis_code3'+pShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">		'				                              
// 	+'  </td>'
// 	+'    <td style=" display: none" class="diagnosisClass4">'
// 	+' <input type="text" name="p_diagnosis_code4'+pShape+'" id="p_diagnosis_code4'+pShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+' placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
// 	+'  </td>'
// 	+'    <td style=" display: none" class="diagnosisClass5">'
// 	+' <input type="text" name="p_diagnosis_code5'+pShape+'" id="p_diagnosis_code5'+pShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">				'		                              
// 	+'  </td>'
// 	+'   <td style=" display: none" class="diagnosisClass6">'
// 	+' <input type="text" name="p_diagnosis_code6'+pShape+'" id="p_diagnosis_code6'+pShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
// 	+'   </td>'
	+' <td style="display:none;"><input type="text" id="pShape_ch_id'+pShape+'" name="pShape_ch_id'+pShape+'"  value="0" class="form-control autocomplete" autocomplete="off" ></td>'   
	+'<td style="width: 10%;" >'
	+'   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "pShape_add'+pShape+'" onclick="pShape_add_fn('+pShape+');" ><i class="fa fa-plus"></i></a>'
	+'    <a class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "pShape_remove'+pShape+'" onclick="pShaperemove_fn('+pShape+');"><i class="fa fa-trash"></i></a> '
	+'</td>'
	+'</tr>');
	
	 datepicketDate('p_from_date'+pShape);
	 datepicketDate('p_to_date'+pShape);
	 $( "#p_to_date"+pShape ).datepicker( "option", "maxDate", null);
	 statusChange(4,pShape,$("#p_status"+pShape).val());
	 
// 	if(q!=0){
// 		var preShape=pShape-1;
// 			$("#pShape_value"+preShape+" > option").clone().appendTo("#pShape_value"+pShape);
// 			$("#p_status"+pShape).val($("#p_status"+preShape).val());
// 			$("#pShape_value"+pShape).val($("#pShape_value"+preShape).val());
// 			statusChange(4,pShape,$("#p_status"+preShape).val());
// 			$("#p_status"+pShape+" option[value='1']").remove();
// 			$("#p_status"+preShape+" option[value='1']").remove();
// 			$("#p_status"+preShape+" option[value='0']").remove();
// 		 }
	setDiagnosis();
}

function pShaperemove_fn(R){
	var r = confirm("Are You Sure! You Want To Delete This Row");
	 if(r){				   
					 $("tr#tr_pShape"+R).remove(); 
						 R = R-1;
					 $("#pShape_add"+R).show();
					 $("#pShape_remove"+R).show();	
					 $("input#pShape_count").val(R);
					 pShape=pShape-1;
// 					 if(pShape == 1){
// 						var s_val = $("#p_status"+pShape).val();
// 						var lis1 = '<option value="${getShapeStatusList[0][1]}" name="${getShapeStatusList[0][1]}">${getShapeStatusList[0][0]}</option>'
// 						 var lis2 = $("#p_status"+pShape).html();
// 						 $('#p_status'+pShape).empty().append('<option value="0">--Select--</option>'+lis1+lis2);
// 						 $("#p_status"+pShape).val(s_val);
// 						}
	 }

	 }

eShape=1;
function eShape_add_fn(q){
	 if( $('#eShape_add'+q).length )        
	 {
			$("#eShape_add"+q).hide();
			 $("#eShape_remove"+q).hide();	
	 }
	 eShape=eShape+1;
	 $("input#eShape_count").val(eShape);
	 
	$("table#e_madtable").append('<tr id="tr_eShape'+eShape+'">'
	+'<td  style="width: 2%;">'+eShape+'</td>'
	+'<td>'
	+'<select name="e_status'+eShape+'" id="e_status'+eShape+'" '
	+'	class="form-control-sm form-control"  onchange="statusChange(5,'+eShape+',this.options[this.selectedIndex].value)">'
// 	+'		<option value="0">--Select--</option>'
	+'	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">'
	+'	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>'
	+'	</c:forEach>'
	+'		</select>'
	+'  </td>'
	+'   <td style="">'
	+'<select name="eShape_value'+eShape+'" id="eShape_value'+eShape+'" '
	+'		class="form-control-sm form-control">'
	+'		</select>	</td>'
	
// 	+'	<td style="">'
// 	+'		<input type="date" id="e_from_date'+eShape+'" name="e_from_date'+eShape+'" max="${date_without_time}" class="form-control autocomplete" autocomplete="off">	'					                             
// 	+'		 </td>'	   
// 	+'<td style="">'
// 	+'		<input type="date" id="e_to_date'+eShape+'" name="e_to_date'+eShape+'" class="form-control autocomplete" autocomplete="off">		'				                             
// 	+'   </td>'
	
	+'<td style="""> '
	+' <input type="text" name="e_from_date'+eShape+'" id="e_from_date'+eShape+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
	+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
	+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">'
	+'</td>'
	+'<td style="""> '
	+' <input type="text" name="e_to_date'+eShape+'" id="e_to_date'+eShape+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
	+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
	+'	onchange="clickrecall(this,\'DD/MM/YYYY\')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">'
	+'</td>'
	+'  <td style="visibility:hidden; "  class="diagnosisClass5'+eShape+'">'
	+' <input type="text" name="_diagnosis_code5'+eShape+'" id="_diagnosis_code5'+eShape+'" class="form-control-sm form-control" autocomplete="off" '
	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
	+'   </td>'
// 	+'    <td style=" display: none" class="diagnosisClass2">'
// 	+'<input type="text" name="e_diagnosis_code2'+eShape+'" id="e_diagnosis_code2'+eShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+' placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">		'				                              
// 	+' </td>'
// 	+'   <td style=" display: none" class="diagnosisClass3">'
// 	+' <input type="text" name="e_diagnosis_code3'+eShape+'" id="e_diagnosis_code3'+eShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">		'				                              
// 	+'  </td>'
// 	+'    <td style=" display: none" class="diagnosisClass4">'
// 	+' <input type="text" name="e_diagnosis_code4'+eShape+'" id="e_diagnosis_code4'+eShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+' placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
// 	+'  </td>'
// 	+'    <td style=" display: none" class="diagnosisClass5">'
// 	+' <input type="text" name="e_diagnosis_code5'+eShape+'" id="e_diagnosis_code5'+eShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">				'		                              
// 	+'  </td>'
// 	+'   <td style=" display: none" class="diagnosisClass6">'
// 	+' <input type="text" name="e_diagnosis_code6'+eShape+'" id="e_diagnosis_code6'+eShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
// 	+'   </td>'
	+' <td style="display:none;"><input type="text" id="eShape_ch_id'+eShape+'" name="eShape_ch_id'+eShape+'"  value="0" class="form-control autocomplete" autocomplete="off" ></td>'   
	+'<td style="width: 10%;" >'
	+'   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "eShape_add'+eShape+'" onclick="eShape_add_fn('+eShape+');" ><i class="fa fa-plus"></i></a>'
	+'    <a class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "eShape_remove'+eShape+'" onclick="eShaperemove_fn('+eShape+');"><i class="fa fa-trash"></i></a> '
	+'</td>'
	+'</tr>');
	
	
	 datepicketDate('e_from_date'+eShape);
	 datepicketDate('e_to_date'+eShape);
	 $( "#e_to_date"+eShape ).datepicker( "option", "maxDate", null);
	 statusChange(5,eShape,$("#e_status"+eShape).val());
	 
	 
// 	if(q!=0){
// 		var preShape=eShape-1;
// 			$("#eShape_value"+preShape+" > option").clone().appendTo("#eShape_value"+eShape);
// 			$("#e_status"+eShape).val($("#e_status"+preShape).val());
// 			$("#eShape_value"+eShape).val($("#eShape_value"+preShape).val());
// 			statusChange(5,eShape,$("#e_status"+preShape).val());
// 			$("#e_status"+eShape+" option[value='1']").remove();
// 			$("#e_status"+preShape+" option[value='1']").remove();
// 			$("#e_status"+preShape+" option[value='0']").remove();
// 		 }
	setDiagnosis();
}

function eShaperemove_fn(R){
	var r = confirm("Are You Sure! You Want To Delete This Row");
	 if(r){				   
					 $("tr#tr_eShape"+R).remove(); 
						 R = R-1;
					 $("#eShape_add"+R).show();
					 $("#eShape_remove"+R).show();
					 $("input#eShape_count").val(R);
					 eShape=eShape-1;
// 					 if(eShape == 1){
// 						var s_val = $("#e_status"+eShape).val();
// 						var lis1 = '<option value="${getShapeStatusList[0][1]}" name="${getShapeStatusList[0][1]}">${getShapeStatusList[0][0]}</option>'
// 						 var lis2 = $("#e_status"+eShape).html();
// 						 $('#e_status'+eShape).empty().append('<option value="0">--Select--</option>'+lis1+lis2);
// 						 $("#e_status"+eShape).val(s_val);
// 						}
	 }
	 
	 
}
 
	 
	 
	 
	 
	 
	 
	 
	 
	 cCope=1;
	 function cCope_add_fn(q){
	 	 if( $('#cCope_add'+q).length )        
	 	 {
	 			$("#cCope_add"+q).hide();
	 			 $("#cCope_remove"+q).hide();	
	 	 }
	 	 cCope=cCope+1;
	 	 $("input#cCope_count").val(cCope);
	 	 
	 	$("table#c_cmadtable").append('<tr id="tr_cCope'+cCope+'">'
	 	+'<td  style="width: 2%;">'+cCope+'</td>'
	 	+'<td>'
	 	+'<select name="c_cvalue'+cCope+'" id="c_cvalue'+cCope+'" '
	 	+'	class="form-control-sm form-control" onchange="onCCopeChange(this,'+cCope+')">'
// 	 	+'		<option value="0">--Select--</option>'
	 	+'	<c:forEach var="item" items="${getcCopeList}" varStatus="num">'
	 	+'	<option value="${item[1]}" name="${item[0]}">${item[0]}</option>'
	 	+'	</c:forEach>'
	 	+'		</select>'
	 	+'  </td>'
	 	+'<td style="display:none;" class="cCopClass'+cCope+'" >'
		+'<input type="text" name="c_cother'+cCope+'" id="c_cother'+cCope+'" class="form-control-sm form-control" autocomplete="off" >	'					                              
		+' </td>'
// 	 	+'  <td style="" >'
// 	 	+' <input type="text" name="c_cdescription'+cCope+'" id="c_cdescription'+cCope+'" class="form-control-sm form-control" autocomplete="off" '
// 	 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
// 	 	+'   </td>'
	 	+' <td style="display:none;"><input type="text" id="cCope_ch_id'+cCope+'" name="cCope_ch_id'+cCope+'"  value="0" class="form-control autocomplete" autocomplete="off" ></td>'   
	 	+'<td style="width: 10%;" >'
	 	+'   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "cCope_add'+cCope+'" onclick="cCope_add_fn('+cCope+');" ><i class="fa fa-plus"></i></a>'
	 	+'    <a class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "cCope_remove'+cCope+'" onclick="cCoperemove_fn('+cCope+');"><i class="fa fa-trash"></i></a> '
	 	+'</td>'
	 	+'</tr>');
	 	
	 	var thisT = document.getElementById('c_cvalue'+cCope)
	 	onCCopeChange(thisT,cCope);
	 	$("#c_cvalue"+cCope+" option[value='0']").remove();
	 	$("#c_cvalue"+cCope+" option[value='1']").remove();
		$("#c_cvalue"+(cCope-1)+" option[value='0']").remove();
		$("#c_cvalue"+(cCope-1)+" option[value='1']").remove();
	 	
	 }

	 function cCoperemove_fn(R){
	 	var r = confirm("Are You Sure! You Want To Delete This Row");
	 	 if(r){				   
	 					 $("tr#tr_cCope"+R).remove(); 
	 						 R = R-1;
	 					 $("#cCope_add"+R).show();
	 					 $("#cCope_remove"+R).show();
	 					 $("input#cCope_count").val(R);
	 					cCope=cCope-1;
	 					if(cCope == 1){
							var s_val = $("#c_cvalue"+cCope).val();
							var lis1 = '<option value="${getcCopeList[0][1]}" name="${getcCopeList[0][0]}">${getcCopeList[0][0]}</option>'
							var lis2 = '<option value="${getcCopeList[1][1]}" name="${getcCopeList[1][0]}">${getcCopeList[1][0]}</option>'
							var lis3 = $("#c_cvalue"+cCope).html();
							 $('#c_cvalue'+cCope).empty().append(""+lis1+lis2+lis3);
							 $("#c_cvalue"+cCope).val(s_val);
							}
	 					var thisT = document.getElementById('c_cvalue'+cCope)
	 				 	onCCopeChange(thisT,cCope);
	 	 }

	 }
	 
	
	 
	 oCope=1;
	 function oCope_add_fn(q){
	 	 if( $('#oCope_add'+q).length )        
	 	 {
	 			$("#oCope_add"+q).hide();
	 			 $("#oCope_remove"+q).hide();	
	 	 }
	 	 oCope=oCope+1;
	 	 $("input#oCope_count").val(oCope);
	 	 
	 	$("table#c_omadtable").append('<tr id="tr_oCope'+oCope+'">'
	 	+'<td  style="width: 2%;">'+oCope+'</td>'
	 	+'<td>'
	 	+'<select name="c_ovalue'+oCope+'" id="c_ovalue'+oCope+'" '
	 	+'	class="form-control-sm form-control"  >'
// 	 	+'		<option value="0">--Select--</option>'
	 	+'	<c:forEach var="item" items="${getoCopeList}" varStatus="num">'
	 	+'	<option value="${item[1]}" name="${item[0]}">${item[0]}</option>'
	 	+'	</c:forEach>'
	 	+'		</select>'
	 	+'  </td>'
	 	
// 	 	+'  <td style="" >'
// 	 	+' <input type="text" name="c_odescription'+oCope+'" id="c_odescription'+oCope+'" class="form-control-sm form-control" autocomplete="off" '
// 	 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
// 	 	+'   </td>'
	 	+' <td style="display:none;"><input type="text" id="oCope_ch_id'+oCope+'" name="oCope_ch_id'+oCope+'"  value="0" class="form-control autocomplete" autocomplete="off" ></td>'   
	 	+'<td style="width: 10%;" >'
	 	+'   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "oCope_add'+oCope+'" onclick="oCope_add_fn('+oCope+');" ><i class="fa fa-plus"></i></a>'
	 	+'    <a class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "oCope_remove'+oCope+'" onclick="oCoperemove_fn('+oCope+');"><i class="fa fa-trash"></i></a> '
	 	+'</td>'
	 	+'</tr>');
	 	$("#c_ovalue"+oCope+" option[value='0']").remove();
		$("#c_ovalue"+(oCope-1)+" option[value='0']").remove();
	 	
	 }

	 function oCoperemove_fn(R){
	 	var r = confirm("Are You Sure! You Want To Delete This Row");
	 	 if(r){				   
	 					 $("tr#tr_oCope"+R).remove(); 
	 						 R = R-1;
	 					 $("#oCope_add"+R).show();
	 					 $("#oCope_remove"+R).show();
	 					 $("input#oCope_count").val(R);
	 					oCope=oCope-1;
	 					if(oCope == 1){
							var s_val = $("#c_ovalue"+oCope).val();
							var lis1 = '<option value="${getoCopeList[0][1]}" name="${getoCopeList[0][0]}">${getoCopeList[0][0]}</option>'
							var lis2 = $("#c_ovalue"+oCope).html();
							 $('#c_ovalue'+oCope).empty().append(""+lis1+lis2);
							 $("#c_ovalue"+oCope).val(s_val);
							}
	 	 }

	 }
	 
	 pCope=1;
	 function pCope_add_fn(q){
	 	 if( $('#pCope_add'+q).length )        
	 	 {
	 			$("#pCope_add"+q).hide();
	 			 $("#pCope_remove"+q).hide();	
	 	 }
	 	 pCope=pCope+1;
	 	 $("input#pCope_count").val(pCope);
	 	 
	 	$("table#c_pmadtable").append('<tr id="tr_pCope'+pCope+'">'
	 	+'<td  style="width: 2%;">'+pCope+'</td>'
	 	+'<td>'
	 	+'<select name="c_pvalue'+pCope+'" id="c_pvalue'+pCope+'" '
	 	+'	class="form-control-sm form-control"  >'
// 	 	+'		<option value="0">--Select--</option>'
	 	+'	<c:forEach var="item" items="${getpCopeList}" varStatus="num">'
	 	+'	<option value="${item[1]}" name="${item[0]}">${item[0]}</option>'
	 	+'	</c:forEach>'
	 	+'		</select>'
	 	+'  </td>'
	 	
// 	 	+'  <td style="" >'
// 	 	+' <input type="text" name="c_pdescription'+pCope+'" id="c_pdescription'+pCope+'" class="form-control-sm form-control" autocomplete="off" '
// 	 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
// 	 	+'   </td>'
	 	+' <td style="display:none;"><input type="text" id="pCope_ch_id'+pCope+'" name="pCope_ch_id'+pCope+'"  value="0" class="form-control autocomplete" autocomplete="off" ></td>'   
	 	+'<td style="width: 10%;" >'
	 	+'   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "pCope_add'+pCope+'" onclick="pCope_add_fn('+pCope+');" ><i class="fa fa-plus"></i></a>'
	 	+'    <a class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "pCope_remove'+pCope+'" onclick="pCoperemove_fn('+pCope+');"><i class="fa fa-trash"></i></a> '
	 	+'</td>'
	 	+'</tr>');
	 	$("#c_pvalue"+pCope+" option[value='0']").remove();
	 	$("#c_pvalue"+pCope+" option[value='1']").remove();
		$("#c_pvalue"+(pCope-1)+" option[value='0']").remove();
		$("#c_pvalue"+(pCope-1)+" option[value='1']").remove();
	 	
	 }

	 function pCoperemove_fn(R){
	 	var r = confirm("Are You Sure! You Want To Delete This Row");
	 	 if(r){				   
	 					 $("tr#tr_pCope"+R).remove(); 
	 						 R = R-1;
	 					 $("#pCope_add"+R).show();
	 					 $("#pCope_remove"+R).show();
	 					 $("input#pCope_count").val(R);
	 					pCope=pCope-1;
	 					if(pCope == 1){
							var s_val = $("#c_pvalue"+pCope).val();
							var lis1 = '<option value="${getpCopeList[0][1]}" name="${getpCopeList[0][0]}">${getpCopeList[0][0]}</option>'
							var lis2 = '<option value="${getpCopeList[1][1]}" name="${getpCopeList[1][0]}">${getpCopeList[1][0]}</option>'
							var lis3 = $("#c_pvalue"+pCope).html();
							 $('#c_pvalue'+pCope).empty().append(""+lis1+lis2+lis3);
							 $("#c_pvalue"+pCope).val(s_val);
							}
	 	 }

	 }
	 
	 
	 eCope=1;
	 function eCope_add_fn(q){
	 	 if( $('#eCope_add'+q).length )        
	 	 {
	 			$("#eCope_add"+q).hide();
	 			 $("#eCope_remove"+q).hide();	
	 	 }
	 	 eCope=eCope+1;
	 	 $("input#eCope_count").val(eCope);
	 	 
	 	$("table#c_emadtable").append('<tr id="tr_eCope'+eCope+'">'
	 	+'<td  style="width: 2%;">'+eCope+'</td>'
	 	+'<td>'
	 	+'<select name="c_evalue'+eCope+'" id="c_evalue'+eCope+'" '
	 	+'	class="form-control-sm form-control" onchange="onECopeChange(this,'+eCope+')" >'
// 	 	+'		<option value="0">--Select--</option>'
	 	+'	<c:forEach var="item" items="${geteCopeList}" varStatus="num">'
	 	+'	<option value="${item[1]}" name="${item[0]}">${item[0]}</option>'
	 	+'	</c:forEach>'
	 	+'		</select>'
	 	+'  </td>'
	 	+'<td style="display:none;" class="eCopClass'+eCope+'">'
	 	+'<select name="c_esubvalue'+eCope+'" id="c_esubvalue'+eCope+'" onchange="onECopeSubChange(this,'+eCope+')"'
	 	+'			class="form-control-sm form-control" >'
	 	+'			<option value="0">--Select--</option>'																
	 	+'			<c:forEach var="item" items="${getesubCopeList}" varStatus="num">'
	 	+'				<option value="${item[1]}" name="${item[0]}">${item[0]}</option>'
	 	+'			</c:forEach></select>   </td>'
	 	+'<td style="display:none;" class="eCop_otherClass'+eCope+'" >'
	 	+'	 <input type="text" name="c_esubvalueother'+eCope+'" id="c_esubvalueother'+eCope+'" class="form-control-sm form-control" autocomplete="off" >'						                              
	 	+'	   </td>'
// 	 	+'  <td style="" >'
// 	 	+' <input type="text" name="c_edescription'+eCope+'" id="c_edescription'+eCope+'" class="form-control-sm form-control" autocomplete="off" '
// 	 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
// 	 	+'   </td>'
	 	+' <td style="display:none;"><input type="text" id="eCope_ch_id'+eCope+'" name="eCope_ch_id'+eCope+'"  value="0" class="form-control autocomplete" autocomplete="off" ></td>'   
	 	+'<td style="width: 10%;" >'
	 	+'   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "eCope_add'+eCope+'" onclick="eCope_add_fn('+eCope+');" ><i class="fa fa-plus"></i></a>'
	 	+'    <a class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "eCope_remove'+eCope+'" onclick="eCoperemove_fn('+eCope+');"><i class="fa fa-trash"></i></a> '
	 	+'</td>'
	 	+'</tr>');
	 	
	 	$("#c_evalue"+eCope+" option[value='0']").remove();
		$("#c_evalue"+(eCope-1)+" option[value='0']").remove();
		var thisT = document.getElementById('c_evalue'+eCope)
	 	onECopeChange(thisT,eCope);
	 	
	 }

	 function eCoperemove_fn(R){
	 	var r = confirm("Are You Sure! You Want To Delete This Row");
	 	 if(r){				   
	 					 $("tr#tr_eCope"+R).remove(); 
	 						 R = R-1;
	 					 $("#eCope_add"+R).show();
	 					 $("#eCope_remove"+R).show();
	 					 $("input#eCope_count").val(R);
	 					eCope=eCope-1;
	 					if(eCope == 1){
							var s_val = $("#c_evalue"+eCope).val();
							var lis1 = '<option value="${geteCopeList[0][1]}" name="${geteCopeList[0][0]}">${geteCopeList[0][0]}</option>'
							var lis2 = $("#c_evalue"+eCope).html();
							 $('#c_evalue'+eCope).empty().append(""+lis1+lis2);
							 $("#c_evalue"+eCope).val(s_val);
							}
	 					var thisT = document.getElementById('c_evalue'+eCope)
	 				 	onECopeChange(thisT,eCope);
	 	 }

	 }
	 
	 
	 function medical_cat_save(){
		 var count_classi = 0;
	
		 
		 if($("#madical_authority").val().trim()==""){
				alert("Please Enter Authority");
				$("#madical_authority").focus();				
				return false;
			}
		 
		 if($("input#madical_date_of_authority").val().trim()=="" || $("#madical_date_of_authority").val().trim()=="DD/MM/YYYY"){
				alert("Please Enter Date of Authority");
				$("#madical_date_of_authority").focus();
				return false;
			}
		

		 	var comm_date=$("#comm_date").val();
			if(getformatedDate($("input#comm_date").val()) > getformatedDate($("#madical_date_of_authority").val())) {
				alert("Authority Date should be Greater than Commission Date");
				$("#madical_date_of_authority").focus();
				return false;
		    }
		 
		 if ($("#check_1bx").prop('checked')){
				if($("input#1bx_from_date").val().trim()=="" || $("input#1bx_from_date").val().trim()=="DD/MM/YYYY"){
					alert("Please Enter From Date");
					$("#1bx_from_date").focus();
					return false;
				}
				if($("input#1bx_to_date").val().trim()=="" || $("input#1bx_to_date").val().trim()=="DD/MM/YYYY"){
					alert("Please Enter To Date");
					$("#1bx_to_date").focus();
					return false;
				}
				if(getformatedDate($("input#1bx_from_date").val()) > getformatedDate($("#1bx_to_date").val())) {
					alert("To Date should be Greater than Or Equal to From Date");
					$("#1bx_to_date").focus();
					
					return false;
			    }
				if($("#1bx_diagnosis_code").val().trim()==""){
						alert("Please Enter Diagnosis");
						$("#1bx_diagnosis_code").focus();				
						return false;
					}
				
			  }
			else{
				
				$("#1bx_from_date").val("");
				$("#1bx_to_date").val("");
				$("#1bx_diagnosis_code").val("");	
				
				for(var j=1; j<=sShape; j++){
					if($("#s_status"+j).val()=='0'){
						alert("Please Select S-Shape Status In "+j+" Row");
						$("#s_status"+j).focus();
						return false;
					}
					
					if($("#sShape_value"+j).val()=='0'){
						alert("Please Select S-Shape Value In "+j+" Row");
						$("#sShape_value"+j).focus();
						return false;
					}
					if($("#s_status"+j).val() == "1" && $("#sShape_value"+j).val()=="1B"){
						if($("input#s_to_date"+j).val().trim()!="" && $("input#s_to_date"+j).val().trim()!="DD/MM/YYYY"){
							if($("input#s_from_date"+j).val().trim()=="" || $("input#s_from_date"+j).val().trim()=="DD/MM/YYYY"){
								alert("Please Enter S-Shape From Date In "+j+" Row");
								$("#s_from_date"+j).focus();
								return false;
							}
						}
						if($("input#s_from_date"+j).val().trim()!="" && $("input#s_from_date"+j).val().trim()!="DD/MM/YYYY"){
							if($("input#s_to_date"+j).val().trim()=="" || $("input#s_to_date"+j).val().trim()=="DD/MM/YYYY"){
								alert("Please Enter S-Shape To Date In "+j+" Row");
								$("#s_to_date"+j).focus();
								return false;
							}
						}
					}
					if($("#s_status"+j).val() != "0" && $("#s_status"+j).val() != "1")
						count_classi++;
					
					if($("#s_status"+j).val() != "1"){
						if($("input#s_from_date"+j).val().trim()=="" || $("input#s_from_date"+j).val().trim()=="DD/MM/YYYY"){
							alert("Please Enter S-Shape From Date In "+j+" Row");
							$("#s_from_date"+j).focus();
							return false;
						}
						if($("input#s_to_date"+j).val().trim()=="" || $("input#s_to_date"+j).val().trim()=="DD/MM/YYYY"){
							alert("Please Enter S-Shape To Date In "+j+" Row");
							$("#s_to_date"+j).focus();
							return false;
						}
					}	
				
					if(($("#s_status"+j).val() == "1" && $("#sShape_value"+j).val()=="1B" && $("input#s_from_date"+j).val().trim()!="" 
							&& $("input#s_from_date"+j).val().trim()!="DD/MM/YYYY" && $("input#s_to_date"+j).val().trim()!="" 
							&& $("input#s_to_date"+j).val().trim()!="DD/MM/YYYY") || $("#s_status"+j).val() != "1"){
						if(getformatedDate($("input#s_from_date"+j).val()) > getformatedDate($("#s_to_date"+j).val())) {
							alert("S-Shape To Date should be Greater than Or Equal to From Date "+j+" Row");
							$("#s_to_date"+j).focus();
							
							return false;
					    }
					}
					
			// 		if($("#s_status"+j).val() != "1"){			
			// 			if($("#_diagnosis_code1"+j).val().trim()==""){
			// 				alert("Please Enter S-Shape Diagnosis "+j+" Row");
			// 				$("#_diagnosis_code1"+j).focus();				
			// 				return false;
			// 			}
			// 		}
				}
				for(var j=1; j<=hShape; j++){
					if($("#h_status"+j).val()=='0'){
						alert("Please Select H-Shape Status In "+j+" Row");
						$("#h_status"+j).focus();
						return false;
					}
					
					if($("#hShape_value"+j).val()=='0'){
						alert("Please Select H-Shape Value In "+j+" Row");
						$("#hShape_value"+j).focus();
						return false;
					}
					if($("#h_status"+j).val() == "1" && $("#hShape_value"+j).val()=="1B"){
						if($("input#h_to_date"+j).val().trim()!="" && $("input#h_to_date"+j).val().trim()!="DD/MM/YYYY"){
							if($("input#h_from_date"+j).val().trim()=="" || $("input#h_from_date"+j).val().trim()=="DD/MM/YYYY"){
								alert("Please Enter H-Shape From Date In "+j+" Row");
								$("#h_from_date"+j).focus();
								return false;
							}
						}
						if($("input#h_from_date"+j).val().trim()!="" && $("input#h_from_date"+j).val().trim()!="DD/MM/YYYY"){
							if($("input#h_to_date"+j).val().trim()=="" || $("input#h_to_date"+j).val().trim()=="DD/MM/YYYY"){
								alert("Please Enter H-Shape To Date In "+j+" Row");
								$("#h_to_date"+j).focus();
								return false;
							}
						}
					}
					if($("#h_status"+j).val() != "0" && $("#h_status"+j).val() != "1")
						count_classi++;
					
					if($("#h_status"+j).val() != "1"){
						if($("input#h_from_date"+j).val().trim()=="" || $("input#h_from_date"+j).val().trim()=="DD/MM/YYYY"){
							alert("Please Enter H-Shape From Date In "+j+" Row");
							$("#h_from_date"+j).focus();
							return false;
						}
						if($("input#h_to_date"+j).val().trim()=="" || $("input#h_to_date"+j).val().trim()=="DD/MM/YYYY"){
							alert("Please Enter H-Shape To Date In "+j+" Row");
							$("#h_to_date"+j).focus();
							return false;
						}
					}
					
					if(($("#h_status"+j).val() == "1" && $("#hShape_value"+j).val()=="1B" && $("input#h_from_date"+j).val().trim()!="" 
						&& $("input#h_from_date"+j).val().trim()!="DD/MM/YYYY" && $("input#h_to_date"+j).val().trim()!="" 
						&& $("input#h_to_date"+j).val().trim()!="DD/MM/YYYY") || $("#h_status"+j).val() != "1"){
						if(getformatedDate($("input#h_from_date"+j).val()) > getformatedDate($("#h_to_date"+j).val())) {
							alert("H-Shape To Date should be Greater than Or Equal to From Date "+j+" Row");
							$("#h_to_date"+j).focus();
							return false;
					    }
					}
					
					
					
				}
				for(var j=1; j<=aShape; j++){
					if($("#a_status"+j).val()=='0'){
						alert("Please Select A-Shape Status In "+j+" Row");
						$("#a_status"+j).focus();
						return false;
					}
					
					if($("#aShape_value"+j).val()=='0'){
						alert("Please Select A-Shape Value In "+j+" Row");
						$("#aShape_value"+j).focus();
						return false;
					}
					if($("#a_status"+j).val() == "1" && $("#aShape_value"+j).val()=="1B"){
						if($("input#a_to_date"+j).val().trim()!="" && $("input#a_to_date"+j).val().trim()!="DD/MM/YYYY"){
							if($("input#a_from_date"+j).val().trim()=="" || $("input#a_from_date"+j).val().trim()=="DD/MM/YYYY"){
								alert("Please Enter A-Shape From Date In "+j+" Row");
								$("#a_from_date"+j).focus();
								return false;
							}
						}
						if($("input#a_from_date"+j).val().trim()!="" && $("input#a_from_date"+j).val().trim()!="DD/MM/YYYY"){
							if($("input#a_to_date"+j).val().trim()=="" || $("input#a_to_date"+j).val().trim()=="DD/MM/YYYY"){
								alert("Please Enter A-Shape To Date In "+j+" Row");
								$("#a_to_date"+j).focus();
								return false;
							}
						}
					}
					if($("#a_status"+j).val() != "0" && $("#a_status"+j).val() != "1")
						count_classi++;
					
					if($("#a_status"+j).val() != "1"){
						if($("input#a_from_date"+j).val().trim()=="" || $("input#a_from_date"+j).val().trim()=="DD/MM/YYYY"){
							alert("Please Enter A-Shape From Date In "+j+" Row");
							$("#a_from_date"+j).focus();
							return false;
						}
						if($("input#a_to_date"+j).val().trim()=="" || $("input#a_to_date"+j).val().trim()=="DD/MM/YYYY"){
							alert("Please Enter A-Shape To Date In "+j+" Row");
							$("#a_to_date"+j).focus();
							return false;
						}
					}
					if(($("#a_status"+j).val() == "1" && $("#aShape_value"+j).val()=="1B" && $("input#a_from_date"+j).val().trim()!="" 
						&& $("input#a_from_date"+j).val().trim()!="DD/MM/YYYY" && $("input#a_to_date"+j).val().trim()!="" 
						&& $("input#a_to_date"+j).val().trim()!="DD/MM/YYYY") || $("#a_status"+j).val() != "1"){
						if(getformatedDate($("input#a_from_date"+j).val()) > getformatedDate($("#a_to_date"+j).val())) {
							alert("A-Shape To Date should be Greater than Or Equal to From Date "+j+" Row");
							$("#a_to_date"+j).focus();
							return false;
					    }
					}	
				}
				for(var j=1; j<=pShape; j++){
					if($("#p_status"+j).val()=='0'){
						alert("Please Select P-Shape Status In "+j+" Row");
						$("#p_status"+j).focus();
						return false;
					}
					
					if($("#pShape_value"+j).val()=='0'){
						alert("Please Select P-Shape Value In "+j+" Row");
						$("#pShape_value"+j).focus();
						return false;
					}
					if($("#p_status"+j).val() == "1" && $("#pShape_value"+j).val()=="1B"){
						if($("input#p_to_date"+j).val().trim()!="" && $("input#p_to_date"+j).val().trim()!="DD/MM/YYYY"){
							if($("input#p_from_date"+j).val().trim()=="" || $("input#p_from_date"+j).val().trim()=="DD/MM/YYYY"){
								alert("Please Enter P-Shape From Date In "+j+" Row");
								$("#p_from_date"+j).focus();
								return false;
							}
						}
						if($("input#p_from_date"+j).val().trim()!="" && $("input#p_from_date"+j).val().trim()!="DD/MM/YYYY"){
							if($("input#p_to_date"+j).val().trim()=="" || $("input#p_to_date"+j).val().trim()=="DD/MM/YYYY"){
								alert("Please Enter P-Shape To Date In "+j+" Row");
								$("#p_to_date"+j).focus();
								return false;
							}
						}
					}
					if($("#p_status"+j).val() != "0" && $("#p_status"+j).val() != "1")
						count_classi++;
					if($("#p_status"+j).val() != "1"){
						if($("input#p_from_date"+j).val().trim()=="" || $("input#p_from_date"+j).val().trim()=="DD/MM/YYYY"){
							alert("Please Enter P-Shape From Date In "+j+" Row");
							$("#p_from_date"+j).focus();
							return false;
						}
						if($("input#p_to_date"+j).val().trim()=="" || $("input#p_to_date"+j).val().trim()=="DD/MM/YYYY"){
							alert("Please Enter P-Shape To Date In "+j+" Row");
							$("#p_to_date"+j).focus();
							return false;
						}
					}
					
					if(($("#p_status"+j).val() == "1" && $("#pShape_value"+j).val()=="1B" && $("input#p_from_date"+j).val().trim()!="" 
						&& $("input#p_from_date"+j).val().trim()!="DD/MM/YYYY" && $("input#p_to_date"+j).val().trim()!="" 
						&& $("input#p_to_date"+j).val().trim()!="DD/MM/YYYY") || $("#p_status"+j).val() != "1"){
						
						if(getformatedDate($("input#p_from_date"+j).val()) > getformatedDate($("#p_to_date"+j).val())) {
							alert("P-Shape To Date should be Greater than Or Equal to From Date "+j+" Row");
							$("#p_to_date"+j).focus();
							return false;
					    }
					}
				}
				for(var j=1; j<=eShape; j++){
					
					if($("#e_status"+j).val()=='0'){
						alert("Please Select E-Shape Status In "+j+" Row");
						$("#e_status"+j).focus();
						return false;
					}
					
					if($("#eShape_value"+j).val()=='0'){
						alert("Please Select E-Shape Value In "+j+" Row");
						$("#eShape_value"+j).focus();
						return false;
					}
					if($("#e_status"+j).val() == "1" && $("#eShape_value"+j).val()=="1B"){
						if($("input#e_to_date"+j).val().trim()!="" && $("input#e_to_date"+j).val().trim()!="DD/MM/YYYY"){
							if($("input#e_from_date"+j).val().trim()=="" || $("input#e_from_date"+j).val().trim()=="DD/MM/YYYY"){
								alert("Please Enter E-Shape From Date In "+j+" Row");
								$("#e_from_date"+j).focus();
								return false;
							}
						}
						
						if($("input#e_from_date"+j).val().trim()!="" && $("input#e_from_date"+j).val().trim()!="DD/MM/YYYY"){
							if($("input#e_to_date"+j).val().trim()=="" || $("input#e_to_date"+j).val().trim()=="DD/MM/YYYY"){
								alert("Please Enter E-Shape To Date In "+j+" Row");
								$("#e_to_date"+j).focus();
								return false;
							}
						}
					}
					
					if($("#e_status"+j).val() != "0" && $("#e_status"+j).val() != "1")
						count_classi++;
					if($("#e_status"+j).val() != "1"){
						if($("input#e_from_date"+j).val().trim()=="" || $("input#e_from_date"+j).val().trim()=="DD/MM/YYYY"){
							alert("Please Enter E-Shape From Date In "+j+" Row");
							$("#e_from_date"+j).focus();
							return false;
						}
						if($("input#e_to_date"+j).val().trim()=="" || $("input#e_to_date"+j).val().trim()=="DD/MM/YYYY"){
							alert("Please Enter E-Shape To Date In "+j+" Row");
							$("#e_to_date"+j).focus();
							return false;
						}
					}
					if(($("#e_status"+j).val() == "1" && $("#eShape_value"+j).val()=="1B" && $("input#e_from_date"+j).val().trim()!="" 
						&& $("input#e_from_date"+j).val().trim()!="DD/MM/YYYY" && $("input#e_to_date"+j).val().trim()!="" 
						&& $("input#e_to_date"+j).val().trim()!="DD/MM/YYYY") || $("#e_status"+j).val() != "1"){
						
					
						if(getformatedDate($("input#e_from_date"+j).val()) > getformatedDate($("#e_to_date"+j).val())) {
							alert("E-Shape To Date should be Greater than Or Equal to From Date "+j+" Row");
							$("#e_to_date"+j).focus();
							return false;
					    }
					}	
				}
				
				for(var j=1; j<=cCope; j++){
						if($("#c_cvalue"+j).val()=="2 [c]"){
							if($("#c_cother"+j).val().trim()==""){
								alert("Please Enter C-Cope Other In "+j+" Row");
								$("#c_cother"+j).focus();
								return false;
							}
						}
				}
				
				for(var j=1; j<=eCope; j++){
					
					if($("#c_evalue"+j).val()=="1"){
						if($("#c_esubvalue"+j).val().trim()=="0"){
							alert("Please Select E-Cope Sub Value In "+j+" Row");
							$("#c_esubvalue"+j).focus();
							return false;
						}
						if($("#c_esubvalueother"+j).val().trim()=="" && $("#c_esubvalue"+j).val().trim()=="k"){
							alert("Please Enter E-Cope Sub Other In "+j+" Row");
							$("#c_esubvalueother"+j).focus();
							return false;
						}
					}
				}
				
				
			}
			if(count_classi >= 3){
				$("#mad_classification_count").val("Z");
			}
			else if(count_classi == 2){
				$("#mad_classification_count").val("Y");
			}
			else if(count_classi == 1){
				$("#mad_classification_count").val("X");
			}
			else
				$("#mad_classification_count").val("NIL");
			
		    var formvalues=$("#madical_category_form").serialize();
		
			var census_id=$("#census_id").val();	
			var comm_id=$('#comm_id').val();			
			formvalues+="&census_id="+census_id+"&comm_id="+comm_id+"&comm_date="+comm_date;
			
		
			
		   $.post('medical_categoryAction?' + key + "=" + value,formvalues, function(data){
			      
		      
		             	 
		        	 if(data=='1'){
		        		 alert('Data Save/Update Successfully');
		        	
// 		        		 get1BXShapeDetails();
		        	  getsShapeDetails();
		        	  gethShapeDetails();
		        	  getaShapeDetails();
		        	  getpShapeDetails();
		        	  geteShapeDetails();
		        	  getcCopeDetails();
		        	  getoCopeDetails();
		        	  getpCopeDetails();
		        	  geteCopeDetails();
		        	 }
		        	 else{
		        		 alert(data);
		        	 }
		         
		 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		  		});
	}
function getsShapeDetails(){
	
	 var p_id=$('#comm_id').val(); 
	 var app_status=0;
	 var Shape='S'
	 var i=0;
	  $.post('madical_cat_GetData_xml?' + key + "=" + value, {p_id:p_id,Shape:Shape,app_status:app_status }, function(j){
			var v=j.length;
			
			
		if(v!=0){	
			 
			$('#s_madtable').show(); 
			$('#s_madtbody').empty(); 
			for(i;i<v;i++){			
				x=i+1;		
				 var fd="DD/MM/YYYY";
				 var td="DD/MM/YYYY";
				 
				 if(j[i][2]!=null && j[i][2]!=""){
					 fd=ParseDateColumncommission(j[i][2]);
// 				  	 td=ParseDateColumncommission(j[i][3]);
				 }
				 if(j[i][3] != null && j[i][3] != "") {
						td = ParseDateColumncommission(j[i][3]);
					}
				 if (j[i][0]==1) {
					 td="";
					 }
					$("table#s_madtable").append('<tr id="tr_sShape'+x+'">'
						+'<td class="sShape_srno" style="width: 2%;">'+x+'</td>'
						+'<td>'
						
						+'<select name="s_status'+x+'" id="s_status'+x+'" '
						+'	class="form-control-sm form-control"  onchange="statusChange(1,'+x+',this.options[this.selectedIndex].value)">'
						+'	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">'
						+'	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>'
						+'	</c:forEach>'
						+'		</select>'
						+'  </td>'
						+'   <td style="">'
						+'<select name="sShape_value'+x+'" id="sShape_value'+x+'" '
						+'		class="form-control-sm form-control" onchange="onShapeValueChange(this,\'s\')">'
						+'		</select>	</td>'
//							+'	<td style="">'
//							+'		<input type="date" id="s_from_date'+x+'" name="s_from_date'+x+'" value="'+fd+'" max="${date_without_time}" class="form-control autocomplete" autocomplete="off">	'					                             
//							+'		 </td>'	   
//							+'<td style="">'
//							+'		<input type="date" id="s_to_date'+x+'" name="s_to_date'+x+'" value="'+td+'" class="form-control autocomplete" autocomplete="off">		'				                             
//							+'   </td>'
						
						+'<td style=""> '
						+' <input type="text" name="s_from_date'+x+'" id="s_from_date'+x+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
						+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
						+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="'+fd+'">'
						+'</td>'
						+'<td style=""> '
						+' <input type="text" name="s_to_date'+x+'" id="s_to_date'+x+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
						+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
						+'	onchange="clickrecall(this,\'DD/MM/YYYY\')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="'+td+'">'
						+'</td>'
						
						+'  <td style="visibility:hidden; "  class="diagnosisClass1'+x+'">'
						+' <input type="text" name="_diagnosis_code1'+x+'" id="_diagnosis_code1'+x+'" value="'+j[i][4]+'" class="form-control-sm form-control" autocomplete="off" '
						+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
						+'   </td>'

						+' <td style="display:none;"><input type="text" id="sShape_ch_id'+x+'" name="sShape_ch_id'+x+'"  value="'+j[i][5]+'" class="form-control autocomplete" autocomplete="off" ></td>'   
						+'<td style="width: 10%;" class="addbtClass1'+x+'">'
						+'   <a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "sShape_add'+x+'" onclick="sShape_add_fn('+x+');" ><i class="fa fa-plus"></i></a>'
						+'</td>'
						

						+'</tr>');
					
					 datepicketDate('s_from_date'+x);
					 datepicketDate('s_to_date'+x);
					 $( "#s_to_date"+x ).datepicker( "option", "maxDate", null);
					 
					$("#s_status"+x).val(j[i][0]);
						$.ajaxSetup({
  							async : false
  						});

						statusChange(1,x,j[i][0]);
						
						$("#sShape_value"+x).val(j[i][1]); 
// 						if(x>1){
// 							$("#s_status"+x+" option[value='1']").remove();
// 							$("#s_status"+(x-1)+" option[value='1']").remove();
							
// 						}
// 						else {
							var thisT = document.getElementById('sShape_value'+x);
							onShapeValueChange(thisT,'s');
// 						}
						 
						
						
					
		}
			sShape=v;
			$("input#sShape_count").val(v);
			$("input#sShapeOld_count").val(v);
			$("#sShape_add"+v).show(); 
			$("#madical_authority").val(j[i-1][6]); 
			$("#madical_date_of_authority").val(ParseDateColumncommission(j[i-1][7])); 
			$('#mad_classification_count').val(j[i-1][8]);
			
			if(j[i-1][11]!=null && j[i-1][11]!="" && j[i-1][12]!=null && j[i-1][12]!="" && j[i-1][13]!=null && j[i-1][13]!=""){
				$("#check_1bx").prop("checked", true);
				oncheck_1bx();
				 fd=ParseDateColumncommission(j[i-1][11]);
			  	 td=ParseDateColumncommission(j[i-1][12]);
				$("#1bx_from_date").val(fd);
				$("#1bx_to_date").val(td);
				$("#1bx_diagnosis_code").val(j[i-1][13]);
			}
//  			$('#mad_classification').attr('readonly', true);
			
			setDiagnosis();
			}
		
		
			
	  });
}


function gethShapeDetails(){
	
	 var p_id=$('#comm_id').val(); 
	 var app_status=0;
	 var Shape='H';
	 var i=0;
	  $.post('madical_cat_GetData_xml?' + key + "=" + value, {p_id:p_id,Shape:Shape,app_status:app_status }, function(j){
			var v=j.length;
			
		if(v!=0){	
			$('#h_madtable').show(); 
				$('#h_madtbody').empty(); 
				for(i;i<v;i++){			
					x=i+1;		
	
					 var fd="DD/MM/YYYY";
					 var td="DD/MM/YYYY";
					 
					 if(j[i][2]!=null && j[i][2]!=""){
						 fd=ParseDateColumncommission(j[i][2]);
// 					  	 td=ParseDateColumncommission(j[i][3]);
					 }
					 if(j[i][3] != null && j[i][3] != "") {
							td = ParseDateColumncommission(j[i][3]);
						}
					 if (j[i][0]==1) {
						 td="";
						 }
					
						$("table#h_madtable").append('<tr id="tr_hShape'+x+'">'
							+'<td style="width: 2%;">'+x+'</td>'
							+'<td>'
							
							+'<select name="h_status'+x+'" id="h_status'+x+'" '
							+'	class="form-control-sm form-control"  onchange="statusChange(2,'+x+',this.options[this.selectedIndex].value)">'
							+'	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">'
							+'	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>'
							+'	</c:forEach>'
							+'		</select>'
							+'  </td>'
							+'   <td style="">'
							+'<select name="hShape_value'+x+'" id="hShape_value'+x+'" '
							+'		class="form-control-sm form-control" onchange="onShapeValueChange(this,\'h\')">'
							+'		</select>	</td>'
// 							+'	<td style="">'
// 							+'		<input type="date" id="h_from_date'+x+'" name="h_from_date'+x+'" value="'+fd+'" max="${date_without_time}" class="form-control autocomplete" autocomplete="off">	'					                             
// 							+'		 </td>'	   
// 							+'<td style="">'
// 							+'		<input type="date" id="h_to_date'+x+'" name="h_to_date'+x+'" value="'+td+'" class="form-control autocomplete" autocomplete="off">		'				                             
// 							+'   </td>'
							
							
							+'<td style=""> '
							+' <input type="text" name="h_from_date'+x+'" id="h_from_date'+x+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
							+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
							+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="'+fd+'">'
							+'</td>'
							+'<td style=""> '
							+' <input type="text" name="h_to_date'+x+'" id="h_to_date'+x+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
							+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
							+'	onchange="clickrecall(this,\'DD/MM/YYYY\')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="'+td+'">'
							+'</td>'
							+'  <td style="visibility:hidden; "  class="diagnosisClass2'+x+'">'
							+' <input type="text" name="_diagnosis_code2'+x+'" id="_diagnosis_code2'+x+'" value="'+j[i][4]+'" class="form-control-sm form-control" autocomplete="off" '
							+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
							+'   </td>'

							+' <td style="display:none;"><input type="text" id="hShape_ch_id'+x+'" name="hShape_ch_id'+x+'" value="'+j[i][5]+'" class="form-control autocomplete" autocomplete="off" ></td>'   
							+'<td style="width: 10%;" class="addbtClass2'+x+'">'
							+'   <a  style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "hShape_add'+x+'" onclick="hShape_add_fn('+x+');" ><i class="fa fa-plus"></i></a>'
							+'</td>'
							+'</tr>');
						
						datepicketDate('h_from_date'+x);
						 datepicketDate('h_to_date'+x);
						 $( "#h_to_date"+x ).datepicker( "option", "maxDate", null);

						 
						$("#h_status"+x).val(j[i][0]);
						$.ajaxSetup({
 							async : false
 						});
						
						statusChange(2,x,j[i][0]);
						

						$("#hShape_value"+x).val(j[i][1]); 
// 						if(x>1){
// 							$("#h_status"+x+" option[value='1']").remove();
// 							$("#h_status"+(x-1)+" option[value='1']").remove();
							
// 						}
// 						else {
							var thisT = document.getElementById('hShape_value'+x);
							onShapeValueChange(thisT,'h');
// 						}
						 
						
						
					
		}
			hShape=v;
			$("input#hShape_count").val(v);
			$("input#hShapeOld_count").val(v);
			$("#hShape_add"+v).show();
			
			setDiagnosis();
			}
			
	  });
}



function getaShapeDetails(){
	
	 var p_id=$('#comm_id').val(); 
	 var app_status=0;
	 var Shape='A';
	 var i=0;
	  $.post('madical_cat_GetData_xml?' + key + "=" + value, {p_id:p_id,Shape:Shape,app_status:app_status }, function(j){
			var v=j.length;
			
		if(v!=0){	
			classification=j[0][13];
			$('#a_madtable').show(); 
				$('#a_madtbody').empty(); 
				for(i;i<v;i++){			
					x=i+1;		
	
					 var fd="DD/MM/YYYY";
					 var td="DD/MM/YYYY";
					 
					 if(j[i][2]!=null && j[i][2]!=""){
						 fd=ParseDateColumncommission(j[i][2]);
// 					  	 td=ParseDateColumncommission(j[i][3]);
					 }
					 if(j[i][3] != null && j[i][3] != "") {
							td = ParseDateColumncommission(j[i][3]);
						}
					 if (j[i][0]==1) {
						 td="";
						 }
					
						$("table#a_madtable").append('<tr id="tr_aShape'+x+'">'
							+'<td style="width: 2%;">'+x+'</td>'
							+'<td>'
							+'<select name="a_status'+x+'" id="a_status'+x+'" '
							+'	class="form-control-sm form-control"  onchange="statusChange(3,'+x+',this.options[this.selectedIndex].value)">'
							+'	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">'
							+'	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>'
							+'	</c:forEach>'
							+'		</select>'
							+'  </td>'
							+'   <td style="">'
							+'<select name="aShape_value'+x+'" id="aShape_value'+x+'" '
							+'		class="form-control-sm form-control" onchange="onShapeValueChange(this,\'a\')">'
							+'		</select>	</td>'
// 							+'	<td style="">'
// 							+'		<input type="date" id="a_from_date'+x+'" name="a_from_date'+x+'" value="'+fd+'" max="${date_without_time}" class="form-control autocomplete" autocomplete="off">	'					                             
// 							+'		 </td>'	   
// 							+'<td style="">'
// 							+'		<input type="date" id="a_to_date'+x+'" name="a_to_date'+x+'" value="'+td+'" class="form-control autocomplete" autocomplete="off">		'				                             
// 							+'   </td>'
							+'<td style=""> '
							+' <input type="text" name="a_from_date'+x+'" id="a_from_date'+x+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
							+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
							+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="'+fd+'">'
							+'</td>'
							+'<td style=""> '
							+' <input type="text" name="a_to_date'+x+'" id="a_to_date'+x+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
							+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
							+'	onchange="clickrecall(this,\'DD/MM/YYYY\')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="'+td+'">'
							+'</td>'
							
							+'  <td style="visibility:hidden; "  class="diagnosisClass3'+x+'">'
							+' <input type="text" name="_diagnosis_code3'+x+'" id="_diagnosis_code3'+x+'" value="'+j[i][4]+'" class="form-control-sm form-control" autocomplete="off" '
							+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
							+'   </td>'
							+' <td style="display:none;"><input type="text" id="aShape_ch_id'+x+'" name="aShape_ch_id'+x+'"  value="'+j[i][5]+'" class="form-control autocomplete" autocomplete="off" ></td>'   
							+'<td style="width: 10%;" class="addbtClass3'+x+'" >'
							+'   <a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "aShape_add'+x+'" onclick="aShape_add_fn('+x+');" ><i class="fa fa-plus"></i></a>'
							+'</td>'
							+'</tr>');
						
						datepicketDate('a_from_date'+x);
						 datepicketDate('a_to_date'+x);
						 $( "#a_to_date"+x ).datepicker( "option", "maxDate", null);

						 
						$("#a_status"+x).val(j[i][0]);
						$.ajaxSetup({
							async : false
						});

						statusChange(3,x,j[i][0]);
						
						$("#aShape_value"+x).val(j[i][1]); 
// 						if(x>1){
// 							$("#a_status"+x+" option[value='1']").remove();
// 							$("#a_status"+(x-1)+" option[value='1']").remove();
							
// 						}
// 						else {
							var thisT = document.getElementById('aShape_value'+x);
							onShapeValueChange(thisT,'a');
// 						}
						 
						
						
					
		}
			aShape=v;
			$("input#aShape_count").val(v);
			$("input#aShapeOld_count").val(v);
			$("#aShape_add"+v).show(); 		
			
			setDiagnosis();
			}
			
	  });
}



function getpShapeDetails(){
	
	 var p_id=$('#comm_id').val(); 
	 var app_status=0;
	 var Shape='P';
	 var i=0;
	  $.post('madical_cat_GetData_xml?' + key + "=" + value, {p_id:p_id,Shape:Shape,app_status:app_status }, function(j){
			var v=j.length;
			
		if(v!=0){	
			$('#p_madtable').show(); 
				$('#p_madtbody').empty(); 
				for(i;i<v;i++){			
					x=i+1;		
	
					 var fd="DD/MM/YYYY";
					 var td="DD/MM/YYYY";
					 
					 if(j[i][2]!=null && j[i][2]!=""){
						 fd=ParseDateColumncommission(j[i][2]);
// 					  	 td=ParseDateColumncommission(j[i][3]);
					 }
					 if(j[i][3] != null && j[i][3] != "") {
							td = ParseDateColumncommission(j[i][3]);
						}
					 if (j[i][0]==1) {
						 td="";
						 }
					
						$("table#p_madtable").append('<tr id="tr_pShape'+x+'">'
							+'<td style="width: 2%;">'+x+'</td>'
							+'<td>'
							+'<select name="p_status'+x+'" id="p_status'+x+'" '
							+'	class="form-control-sm form-control"  onchange="statusChange(4,'+x+',this.options[this.selectedIndex].value)">'
							+'	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">'
							+'	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>'
							+'	</c:forEach>'
							+'		</select>'
							+'  </td>'
							+'   <td style="">'
							+'<select name="pShape_value'+x+'" id="pShape_value'+x+'" '
							+'		class="form-control-sm form-control" onchange="onShapeValueChange(this,\'p\')">'
							+'		</select>	</td>'
// 							+'	<td style="">'
// 							+'		<input type="date" id="p_from_date'+x+'" name="p_from_date'+x+'" value="'+fd+'" max="${date_without_time}" class="form-control autocomplete" autocomplete="off">	'					                             
// 							+'		 </td>'	   
// 							+'<td style="">'
// 							+'		<input type="date" id="p_to_date'+x+'" name="p_to_date'+x+'" value="'+td+'" class="form-control autocomplete" autocomplete="off">		'				                             
// 							+'   </td>'		
							+'<td style=""> '
							+' <input type="text" name="p_from_date'+x+'" id="p_from_date'+x+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
							+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
							+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="'+fd+'">'
							+'</td>'
							+'<td style=""> '
							+' <input type="text" name="p_to_date'+x+'" id="p_to_date'+x+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
							+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
							+'	onchange="clickrecall(this,\'DD/MM/YYYY\')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="'+td+'">'
							+'</td>'

							+'  <td style="visibility:hidden; "  class="diagnosisClass4'+x+'">'
							+' <input type="text" name="_diagnosis_code4'+x+'" id="_diagnosis_code4'+x+'" value="'+j[i][4]+'" class="form-control-sm form-control" autocomplete="off" '
							+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
							+'   </td>'
							+' <td style="display:none;"><input type="text" id="pShape_ch_id'+x+'" name="pShape_ch_id'+x+'"  value="'+j[i][5]+'" class="form-control autocomplete" autocomplete="off" ></td>'   
							+'<td style="width: 10%;" class="addbtClass4'+x+'">'
							+'   <a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "pShape_add'+x+'" onclick="pShape_add_fn('+x+');" ><i class="fa fa-plus"></i></a>'
							+'</td>'
							+'</tr>');
						
						datepicketDate('p_from_date'+x);
						 datepicketDate('p_to_date'+x);
						 $( "#p_to_date"+x ).datepicker( "option", "maxDate", null);

						 
						$("#p_status"+x).val(j[i][0]);
						$.ajaxSetup({
							async : false
						});

						statusChange(4,x,j[i][0]);
						$.ajaxSetup({
							async : false
						});

						$("#pShape_value"+x).val(j[i][1]); 
// 						if(x>1){
// 							$("#p_status"+x+" option[value='1']").remove();
// 							$("#p_status"+(x-1)+" option[value='1']").remove();
							
// 						}
// 						else {
							var thisT = document.getElementById('pShape_value'+x);
							onShapeValueChange(thisT,'p');
// 						}
						 
						
						
					
		}
			pShape=v;
			$("input#pShape_count").val(v);
			$("input#pShapeOld_count").val(v);
			$("#pShape_add"+v).show(); 	
			setDiagnosis();
			}
			
	  });
}


function geteShapeDetails(){
	
	 var p_id=$('#comm_id').val(); 
	 var app_status=0;
	 var Shape='E';
	 var i=0;
	  $.post('madical_cat_GetData_xml?' + key + "=" + value, {p_id:p_id,Shape:Shape ,app_status:app_status}, function(j){
			var v=j.length;
			 
		if(v!=0){	
			$('#e_madtable').show(); 
				$('#e_madtbody').empty(); 
				for(i;i<v;i++){			
					x=i+1;		
	
					 var fd="DD/MM/YYYY";
					 var td="DD/MM/YYYY";
					 
					 if(j[i][2]!=null && j[i][2]!=""){
						 fd=ParseDateColumncommission(j[i][2]);
// 					  	 td=ParseDateColumncommission(j[i][3]);
					 }
					 if(j[i][3] != null && j[i][3] != "") {
							td = ParseDateColumncommission(j[i][3]);
						}
					 if (j[i][0]==1) {
						 td="";
						 }
					
						$("table#e_madtable").append('<tr id="tr_eShape'+x+'">'
							+'<td style="width: 2%;">'+x+'</td>'
							+'<td>'
							+'<select name="e_status'+x+'" id="e_status'+x+'" '
							+'	class="form-control-sm form-control"  onchange="statusChange(5,'+x+',this.options[this.selectedIndex].value)">'
							+'	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">'
							+'	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>'
							+'	</c:forEach>'
							+'		</select>'
							+'  </td>'
							+'   <td style="">'
							+'<select name="eShape_value'+x+'" id="eShape_value'+x+'" '
							+'		class="form-control-sm form-control" onchange="onShapeValueChange(this,\'e\')">'
							+'		</select>	</td>'
// 							+'	<td style="">'
// 							+'		<input type="date" id="e_from_date'+x+'" name="e_from_date'+x+'" max="${date_without_time}" value="'+fd+'" class="form-control autocomplete" autocomplete="off">	'					                             
// 							+'		 </td>'	   
// 							+'<td style="">'
// 							+'		<input type="date" id="e_to_date'+x+'" name="e_to_date'+x+'" value="'+td+'" class="form-control autocomplete" autocomplete="off">		'				                             
// 							+'   </td>'


							+'<td style=""> '
							+' <input type="text" name="e_from_date'+x+'" id="e_from_date'+x+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
							+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
							+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="'+fd+'">'
							+'</td>'
							+'<td style=""> '
							+' <input type="text" name="e_to_date'+x+'" id="e_to_date'+x+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
							+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
							+'	onchange="clickrecall(this,\'DD/MM/YYYY\')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="'+td+'">'
							+'</td>'
							
							+'  <td style="visibility:hidden; "  class="diagnosisClass5'+x+'">'
							+' <input type="text" name="_diagnosis_code5'+x+'" id="_diagnosis_code5'+x+'" value="'+j[i][4]+'" class="form-control-sm form-control" autocomplete="off" '
							+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
							+'   </td>'
							+' <td style="display:none;"><input type="text" id="eShape_ch_id'+x+'" name="eShape_ch_id'+x+'"  value="'+j[i][5]+'" class="form-control autocomplete" autocomplete="off" ></td>'   
							+'<td style="width: 10%;" class="addbtClass5'+x+'">'
							+'   <a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "eShape_add'+x+'" onclick="eShape_add_fn('+x+');" ><i class="fa fa-plus"></i></a>'
							+'</td>'
							+'</tr>');
						
						datepicketDate('e_from_date'+x);
						 datepicketDate('e_to_date'+x);
						 $( "#e_to_date"+x ).datepicker( "option", "maxDate", null);

						 
						$("#e_status"+x).val(j[i][0]);
						$.ajaxSetup({
							async : false
						});

						statusChange(5,x,j[i][0]);
						$.ajaxSetup({
							async : false
						});

						$("#eShape_value"+x).val(j[i][1]); 
// 						if(x>1){
// 							$("#e_status"+x+" option[value='1']").remove();
// 							$("#e_status"+(x-1)+" option[value='1']").remove();
							
// 						}
// 						else {
							var thisT = document.getElementById('eShape_value'+x);
							onShapeValueChange(thisT,'e');
// 						}
						 
						
						
					
		}
			eShape=v;
			$("input#eShape_count").val(v);
			$("input#eShapeOld_count").val(v);
			$("#eShape_add"+v).show(); 
			setDiagnosis();
			}
			
	  });
}



function getcCopeDetails(){
	
	 var p_id=$('#comm_id').val(); 
	 var app_status=0;
	 var Shape='C_C';
	 var i=0;
	  $.post('madical_cat_GetData_xml?' + key + "=" + value, {p_id:p_id,Shape:Shape,app_status:app_status }, function(j){
			var v=j.length;
			 
		if(v!=0){	
			
			$('#c_cmadtable').show(); 
				$('#c_cmadtbody').empty(); 
				for(i;i<v;i++){			
					x=i+1;												
					cCope=x;
					$("table#c_cmadtable").append('<tr id="tr_cCope'+x+'">'
						 	+'<td  style="width: 2%;">'+x+'</td>'
						 	+'<td>'
						 	
						 	+'<select name="c_cvalue'+x+'" id="c_cvalue'+x+'" '
						 	+'	class="form-control-sm form-control" onchange="onCCopeChange(this,'+x+'); onCopeChangebt(this,1,'+x+');">'
						 	+'	<c:forEach var="item" items="${getcCopeList}" varStatus="num">'
						 	+'	<option value="${item[1]}" name="${item[0]}">${item[0]}</option>'
						 	+'	</c:forEach>'
						 	+'		</select>'
						 	+'  </td>'
						 	+'<td style="display:none;" class="cCopClass'+x+'" >'
							+'<input type="text" name="c_cother'+x+'" id="c_cother'+x+'" value="'+j[i][10]+'" class="form-control-sm form-control" autocomplete="off" >	'					                              
							+' </td>'
						 	+' <td style="display:none;"><input type="text" id="cCope_ch_id'+x+'" name="cCope_ch_id'+x+'"  value="'+j[i][5]+'" class="form-control autocomplete" autocomplete="off" ></td>'   
						 	+'<td style="width: 10%;" class="CopaddbtClass11" >'
						 	+'   <a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "cCope_add'+x+'" onclick="cCope_add_fn('+x+');" ><i class="fa fa-plus"></i></a>'
						 	+'</td>'
						 	+'</tr>');
					
						$("#c_cvalue"+x).val(j[i][1]); 
						var thisT = document.getElementById('c_cvalue'+x);
					 	onCCopeChange(thisT,x);
						if(x==1){onCopeChangebt(thisT,1,x);}
						
						if(x>1){
						 	$("#c_cvalue"+x+" option[value='0']").remove();
						 	$("#c_cvalue"+x+" option[value='1']").remove();
							$("#c_cvalue"+(x-1)+" option[value='0']").remove();
							$("#c_cvalue"+(x-1)+" option[value='1']").remove();
						}
		
		}
			cCope=v;
			$("input#cCope_count").val(v);
			$("input#cCopeOld_count").val(v);
			$("#cCope_add"+v).show(); 														
			}
			
	  });
}


function getoCopeDetails(){
	
	 var p_id=$('#comm_id').val(); 
	 var app_status=0;
	 var Shape='C_O';
	 var i=0;
	  $.post('madical_cat_GetData_xml?' + key + "=" + value, {p_id:p_id,Shape:Shape ,app_status:app_status}, function(j){
			var v=j.length;
			 
		if(v!=0){	
			
			$('#c_omadtable').show(); 
				$('#c_omadtbody').empty(); 
				for(i;i<v;i++){			
					x=i+1;												
					oCope=x;
					$("table#c_omadtable").append('<tr id="tr_oCope'+x+'">'
						 	+'<td  style="width: 2%;">'+x+'</td>'
						 	+'<td>'
						 	+'<select name="c_ovalue'+x+'" id="c_ovalue'+x+'" '
						 	+'	class="form-control-sm form-control"  onchange="onCopeChangebt(this,2,'+x+');">'
						 	+'	<c:forEach var="item" items="${getoCopeList}" varStatus="num">'
						 	+'	<option value="${item[1]}" name="${item[0]}">${item[0]}</option>'
						 	+'	</c:forEach>'
						 	+'		</select>'
						 	+'  </td>'	 	
						 	
						 	+' <td style="display:none;"><input type="text" id="oCope_ch_id'+x+'" name="oCope_ch_id'+x+'"  value="'+j[i][5]+'" class="form-control autocomplete" autocomplete="off" ></td>'   
						 	+'<td style="width: 10%;" class="CopaddbtClass21" >'
						 	+'   <a  style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "oCope_add'+x+'" onclick="oCope_add_fn('+x+');" ><i class="fa fa-plus"></i></a>'
						 	+'</td>'
						 	+'</tr>');
					$.ajaxSetup({
						async : false
					});
						$("#c_ovalue"+x).val(j[i][1]); 
						
						var thisT = document.getElementById('c_ovalue'+x)
						if(x==1){onCopeChangebt(thisT,2,x);}
						
						if(x>1){
						$("#c_ovalue"+x+" option[value='0']").remove();
						$("#c_ovalue"+(x-1)+" option[value='0']").remove();
						}
		
		}
			oCope=v;
			$("input#oCope_count").val(v);
			$("input#oCopeOld_count").val(v);
			$("#oCope_add"+v).show(); 														
			}
			
	  });
}

function getpCopeDetails(){
	
	 var p_id=$('#comm_id').val(); 
	 var app_status=0;
	 var Shape='C_P';
	 var i=0;
	  $.post('madical_cat_GetData_xml?' + key + "=" + value, {p_id:p_id,Shape:Shape ,app_status:app_status}, function(j){
			var v=j.length;
			 
		if(v!=0){	
			
			$('#c_pmadtable').show(); 
				$('#c_pmadtbody').empty(); 
				for(i;i<v;i++){			
					x=i+1;												
					pCope=x;
					$("table#c_pmadtable").append('<tr id="tr_pCope'+x+'">'
						 	+'<td  style="width: 2%;">'+x+'</td>'
						 	+'<td>'
						 	+'<select name="c_pvalue'+x+'" id="c_pvalue'+x+'" '
						 	+'	class="form-control-sm form-control"  onchange="onCopeChangebt(this,3,'+x+');">'
						 	+'	<c:forEach var="item" items="${getpCopeList}" varStatus="num">'
						 	+'	<option value="${item[1]}" name="${item[0]}">${item[0]}</option>'
						 	+'	</c:forEach>'
						 	+'		</select>'
						 	+'  </td>'	 	
						 	+' <td style="display:none;"><input type="text" id="pCope_ch_id'+x+'" name="pCope_ch_id'+x+'"  value="'+j[i][5]+'" class="form-control autocomplete" autocomplete="off" ></td>'   
						 	+'<td style="width: 10%;" class="CopaddbtClass31">'
						 	+'   <a  style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "pCope_add'+x+'" onclick="pCope_add_fn('+x+');" ><i class="fa fa-plus"></i></a>'
						 	+'</td>'
						 	+'</tr>');
					$.ajaxSetup({
						async : false
					});
						$("#c_pvalue"+x).val(j[i][1]); 
						
						var thisT = document.getElementById('c_pvalue'+x)
						if(x==1){onCopeChangebt(thisT,3,x);}
						
						if(x>1){
						$("#c_pvalue"+x+" option[value='0']").remove();
						$("#c_pvalue"+x+" option[value='1']").remove();
						$("#c_pvalue"+(x-1)+" option[value='0']").remove();
						$("#c_pvalue"+(x-1)+" option[value='1']").remove();
						}
		
		}
			pCope=v;
			$("input#pCope_count").val(v);
			$("input#pCopeOld_count").val(v);
			$("#pCope_add"+v).show(); 														
			}
			
	  });
}


function geteCopeDetails(){
	
	 var p_id=$('#comm_id').val(); 
	 var app_status=0;
	 var Shape='C_E';
	 var i=0;
	  $.post('madical_cat_GetData_xml?' + key + "=" + value, {p_id:p_id,Shape:Shape,app_status:app_status }, function(j){
			var v=j.length;
			 
		if(v!=0){	
			
			$('#c_emadtable').show(); 
				$('#c_emadtbody').empty(); 
				for(i;i<v;i++){			
					x=i+1;												
					eCope=x;
					$("table#c_emadtable").append('<tr id="tr_eCope'+x+'">'
						 	+'<td  style="width: 2%;">'+x+'</td>'
						 	+'<td>'
						 	+'<select name="c_evalue'+x+'" id="c_evalue'+x+'" '
						 	+'	class="form-control-sm form-control"  onchange="onECopeChange(this,'+x+'); onCopeChangebt(this,4,'+x+');">'
						 	+'	<c:forEach var="item" items="${geteCopeList}" varStatus="num">'
						 	+'	<option value="${item[1]}" name="${item[0]}">${item[0]}</option>'
						 	+'	</c:forEach>'
						 	+'		</select>'
						 	+'  </td>'	 	
						 	+'<td style="display:none;" class="eCopClass'+x+'">'
						 	+'<select name="c_esubvalue'+x+'" id="c_esubvalue'+x+'" onchange="onECopeSubChange(this,'+x+')"'
						 	+'			class="form-control-sm form-control" >'
						 	+'			<option value="0">--Select--</option>'																
						 	+'			<c:forEach var="item" items="${getesubCopeList}" varStatus="num">'
						 	+'				<option value="${item[1]}" name="${item[0]}">${item[0]}</option>'
						 	+'			</c:forEach></select>   </td>'
						 	+'<td style="display:none;" class="eCop_otherClass'+x+'" >'
						 	+'	 <input type="text" name="c_esubvalueother'+x+'" id="c_esubvalueother'+x+'" value="'+j[i][10]+'" class="form-control-sm form-control" autocomplete="off" >'						                              
						 	+'	   </td>'
						 	+' <td style="display:none;"><input type="text" id="eCope_ch_id'+x+'" name="eCope_ch_id'+x+'" value="'+j[i][5]+'"  class="form-control autocomplete" autocomplete="off" ></td>'   
						 	+'<td style="width: 10%;" class="CopaddbtClass41">'
						 	+'   <a  style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "eCope_add'+x+'" onclick="eCope_add_fn('+x+');" ><i class="fa fa-plus"></i></a>'
						 	+'</td>'
						 	+'</tr>');
					$.ajaxSetup({
						async : false
					});
						$("#c_evalue"+x).val(j[i][1]); 
						$("#c_esubvalue"+x).val(j[i][9]);
						var thisT = document.getElementById('c_evalue'+x)
						onECopeChange(thisT,x);
						if(x==1){onCopeChangebt(thisT,4,x);}
						
						if(x>1){
						$("#c_evalue"+x+" option[value='0']").remove();
						$("#c_evalue"+(x-1)+" option[value='0']").remove();
						}
				
		}
			eCope=v;
			$("input#eCope_count").val(v);
			$("input#eCopeOld_count").val(v);
			$("#eCope_add"+v).show(); 														
			}
			
	  });
}
	
	
//************************************* END MEDICAL *****************************//

//*************************************MEDAL*************************//



function getawardsNmedals(){
    var comm_id=$('#comm_id').val();
    var i=0;
     $.post('getawardsNmedalData_xml?' + key + "=" + value, {comm_id:comm_id}, function(j){
                   var v=j.length;
                   if(v!=0){
                           $('#awardsNmedal_tbody').empty(); 
           for(i;i<v;i++){
                   am=i+1;
                   var dauth=ParseDateColumncommission(j[i].date_of_authority);
                   var doa=ParseDateColumncommission(j[i].date_of_award);
             $("table#awardsNmedal_table").append('<tr id="tr_awardsNmedal'+am+'">'
                                   +'<td class="anm_srno">'+am+'</td>'
                                   +'<td style="width: 10%;">'
                                   +'<input type="text" id="awardsNmedal_authority'+am+'" name="awardsNmedal_authority'+am+'" 	onkeyup="return specialcharecter(this)" value="'+j[i].authority+'"  class="form-control autocomplete" autocomplete="off" maxlength="100"></td>'
                                 	+'<td style="width: 10%;">' 
                					+' <input type="text" name="awardsNmedal_date_of_authority'+am+'" id="awardsNmedal_date_of_authority'+am+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')"   class="form-control" style="width: 85%;display: inline;" '
                					+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
                					+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="'+dauth+'">'
                					+ '</td>'
                                   +'<td style="width: 10%;"><select name="Category_8'+am+'" id="Category_8'+am+'" onchange="getMedalDescList(this)" class="form-control"><option value="0">--Select--</option>'
                                   +'<c:forEach var="item" items="${getMedalList}" varStatus="num"><option value="${item[0]}" name="${item[1]}">${item[1]}</option></c:forEach></select>'
                                   +'<td style="width: 10%;"><select name="select_desc'+am+'" id="select_desc'+am+'" class="form-control"><option value="0">--Select--</option></select>'
		                            +'<td style="width: 10%;">' 
		           					+' <input type="text" name="date_of_award'+am+'" id="date_of_award'+am+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')"   class="form-control" style="width: 85%;display: inline;" '
		           					+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
		           					+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="'+doa+'">'
		           					+ '</td>'
                                   +'<td style="width: 10%;"><input type="text" id="awardsNmedal_unit'+am+'" name="awardsNmedal_unit'+am+'" value="'+j[i].unit+'" onkeypress="unitData(this)" class="form-control autocomplete" autocomplete="off" maxlength="255"></td>'
                                   +'<td style="width: 10%;"><select name="awardsNmedal_bde'+am+'" id="awardsNmedal_bde'+am+'"  class="form-control">'
                                   +'<option value="0">--Select--</option><c:forEach var="item" items="${getPSG_BdeList}" varStatus="num">'
                                   +'<option value="${item[0]}" name="${item[1]}">${item[1]}</option></c:forEach></select></td>'
                                   +'<td style="width: 10%;"><select name="awardsNmedal_div_subarea'+am+'" id="awardsNmedal_div_subarea'+am+'"  class="form-control">'
                                   +'<option value="0">--Select--</option><c:forEach var="item" items="${getPSG_DivList}" varStatus="num">'
                                   +'<option value="${item[0]}" name="${item[1]}">${item[1]}</option></c:forEach></select></td>'
                                   +'<td style="width: 10%;"><select name="awardsNmedal_corps_area'+am+'" id="awardsNmedal_corps_area'+am+'"  class="form-control">'
                                   +'<option value="0">--Select--</option><c:forEach var="item" items="${getPSG_CorpsList}" varStatus="num">'
                                   +'<option value="${item[0]}" name="${item[1]}">${item[1]}</option></c:forEach></select></td>'
                                   +'<td style="width: 10%;"><select name="awardsNmedal_command'+am+'" id="awardsNmedal_command'+am+'"  class="form-control">'
                                   +'<option value="0">--Select--</option><c:forEach var="item" items="${getPSG_CommandList}" varStatus="num">'
                                   +'<option value="${item[0]}" name="${item[1]}">${item[1]}</option></c:forEach></select></td>'                
                                   
                                   +'<td style="display:none;"><input type="text" id="awardsNmedal_ch_id'+am+'" name="awardsNmedal_ch_id'+am+'"  value="' + j[i].id + '" class="form-control autocomplete" autocomplete="off" ></td>'
                                   +'<td style="width: 10%;"><a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "awardsNmedal_save'+am+'" onclick="awardsNmedal_save_fn('+am+');" ><i class="fa fa-save"></i></a>'
                                   +'<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "awardsNmedal_add'+am+'" onclick="awardsNmedal_add_fn('+am+');" ><i class="fa fa-plus"></i></a>'
                                   +'<a  class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "awardsNmedal_remove'+am+'" onclick="awardsNmedal_remove_fn('+am+');"><i class="fa fa-trash"></i></a>'
                                   +'</td></tr>');
		             datepicketDate('awardsNmedal_date_of_authority'+am);
		   		     datepicketDate('date_of_award'+am);
              
             $.ajaxSetup({
                           async : false
                   });
             $('#Category_8'+am).val( j[i].category_8 );
             getMedalDescList(document.getElementById('Category_8'+am))
             $('#select_desc'+am).val( j[i].select_desc );
             $('#awardsNmedal_bde'+am).val( j[i].bde );
             $('#awardsNmedal_div_subarea'+am).val( j[i].div_subarea );
             $('#awardsNmedal_corps_area'+am).val( j[i].corps_area );
             $('#awardsNmedal_command'+am).val( j[i].command );
           }
           anm=v;
           anm_srno=v;
           $("#awardsNmedal_add"+anm).show();
           }
     });
} 


function awardsNmedal_save_fn(qs){
    var Category_8=$('#Category_8'+qs).val();
    var select_desc=$('#select_desc'+qs).val();
    var date_of_award=$('#date_of_award'+qs).val();
    var awardsNmedal_unit=$('#awardsNmedal_unit'+qs).val();
    var awardsNmedal_bde=$('#awardsNmedal_bde'+qs).val();
    var awardsNmedal_div_subarea=$('#awardsNmedal_div_subarea'+qs).val();
    var awardsNmedal_corps_area=$('#awardsNmedal_corps_area'+qs).val();
    var awardsNmedal_command=$('#awardsNmedal_command'+qs).val();
    var awardsNmedal_ch_id=$('#awardsNmedal_ch_id'+qs).val();
    var q_id=$('#census_id').val();
    var com_id=$('#comm_id').val();
    var anm_authority=$('#awardsNmedal_authority'+qs).val();
    var anm_doa=$('#awardsNmedal_date_of_authority'+qs).val();
    if (anm_authority.trim()=="") {
        alert("Please Enter Authority");
        $('#awardsNmedal_authority'+qs).focus();
        return false;
   }
    if (anm_doa.trim()=="" || anm_doa == "DD/MM/YYYY") {
            alert("Please Enter Date of Authority");
            $("input#awardsNmedal_date_of_authority"+qs).focus();
            return false;
    } 
    
    if (Category_8 == "0") {
        alert("Please Select Category");
        $("#Category_8"+qs).focus();
        return false;
     }
    if (select_desc == "0") {
        alert("Please Select Award/Medal");
        $("#select_desc"+qs).focus();
        return false;
     }
    if (date_of_award.trim()=="" || date_of_award == "DD/MM/YYYY") {
            alert("Please Enter Date of Award");
            $("#date_of_award"+qs).focus();
            return false;
    } 
  
    if (awardsNmedal_unit.trim()=="") {
            alert("Please Enter Unit");
            $("#awardsNmedal_unit"+qs).focus();
            return false;
    }
    if (awardsNmedal_bde == "0") {
        alert("Please Select BDE");
        $("#awardsNmedal_bde"+qs).focus();
        return false;
    }
    if (awardsNmedal_div_subarea == "0") {
        alert("Please Select DIV/Sub Area");
        $("#awardsNmedal_div_subarea"+qs).focus();
        return false;
     }

    if (awardsNmedal_corps_area == "0") {
        alert("Please Select Corps/Area");
        $("#awardsNmedal_corps_area"+qs).focus();
        return false;
     }
    if (awardsNmedal_command == "0") {
        alert("Please Select Command");
        $("#awardsNmedal_command"+qs).focus();
        return false;
    }


 
    $.post('awardsNmedal_action?' + key + "=" + value, {Category_8:Category_8,select_desc:select_desc,date_of_award:date_of_award,awardsNmedal_unit:awardsNmedal_unit,awardsNmedal_bde:awardsNmedal_bde,awardsNmedal_div_subarea:awardsNmedal_div_subarea,awardsNmedal_corps_area:awardsNmedal_corps_area,awardsNmedal_command:awardsNmedal_command,awardsNmedal_ch_id:awardsNmedal_ch_id,q_id:q_id,com_id:com_id,anm_authority:anm_authority,anm_doa:anm_doa }, function(data){           
              if(data=="update")
                      alert("Data Updated Successfully");
              else if(parseInt(data)>0){
                     $("#awardsNmedal_ch_id"+qs).val(data);
                     $("#awardsNmedal_add"+qs).show();
                     $("#awardsNmedal_remove"+qs).show();
                      alert("Data Saved Successfully")
              }
              else
                      alert(data)
                               }).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
                      });
}


///************************POPUp History/****************************///


function Pop_Up_History(a) {
	var x = screen.width/2 - 1100/2;
   var y = screen.height/2 - 900/2;
 	newWin = window.open("", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');
	window.onfocus = function () { 
	}
	var comm_id = $("#comm_id").val();
	var census_id = $("#census_id").val();

if(a == "rank"){
		$('#rank_comm_id').val(comm_id);
		document.getElementById('Change_Rank_Form').submit();
	}	
if(a == "Name"){
	$('#name_comm_id').val(comm_id);
	document.getElementById('Change_Name_Form').submit();
}
if(a == "Appointment"){
	$('#appointment_comm_id').val(comm_id);
	document.getElementById('Change_Appointment_Form').submit();
}
if(a == "Change_Medicle")
	{
		$("#cim_comm_id").val(comm_id);
		$("#cim_census_id").val(census_id);
		document.getElementById('Change_In_Medicle_Form').submit();
	}
if(a == "CDA"){
	$('#comm_id1').val(comm_id);
	document.getElementById('popup_cda').submit();
}
if(a == "update_awards_medal"){
	$("#am_comm_id").val(comm_id);
	document.getElementById('update_awards_medalForm').submit();
}
if(a == "change_nok"){
	$("#nok_comm_id").val(comm_id);
	document.getElementById('nok_updateForm').submit();
}
	if(a == "update_child_dtl"){
		$("#uc_comm_id").val(comm_id);
		document.getElementById('update_child_dtlForm').submit();
	}
	if(a == "contact_details"){
		$('#cd_comm_id').val(comm_id);
		document.getElementById('Change_Contact_Details_Form').submit();
	}
 	if(a == "Non_Effective_Status"){
   		$("#nes_comm_id").val(comm_id);
   		document.getElementById('Non_Effective_Status_Form').submit();
   	}
   	
	
	
}
///--------------------------------------------------------------------Post In --------------------------------------------------------

function get_post_in_data(){
	
	var comm_id=$("#comm_id").val();
	var status="0";
	$.post('GetPosting_in_data_xml?' + key + "=" + value,{ comm_id : comm_id ,status:status},function(k) {
	
	if(k != ""){
	
		$("#personnel_nolbl").text(k[0].personnel_no);
		$("#name").text(k[0].name);		 
		$("#rank").text(k[0].rank);		 
		$("#from_sus_no").text(k[0].from_sus_no);	
		$('#to_sus_no').text(k[0].unit_sus_no);
 		$("#app_name").val(k[0].app_name);
		$("#unit_description").text(k[0].unit_description);
		$("#update_id").val(k[0].id);
		
		var sus_no=k[0].from_sus_no;
		 $.post("getTargetUnitNameListpostinout?"+key+"="+value, {
			 sus_no:sus_no
			     }, function(j) {
			            
			     }).done(function(j) {
			    	 var length = j.length-1;
			         var enc = j[length].substring(0,16);
			         $("#from_unit_name").text(dec(enc,j[0]));   
			             
			     }).fail(function(xhr, textStatus, errorThrown) {
			     });
		  sus_no=k[0].unit_sus_no;
		 $.post("getTargetUnitNameListpostinout?"+key+"="+value, {
			 sus_no:sus_no
			     }, function(j) {
			            
			     }).done(function(j) {
			    	 var length = j.length-1;
			         var enc = j[length].substring(0,16);
			         $("#to_unit_name").text(dec(enc,j[0]));   
			             
			     }).fail(function(xhr, textStatus, errorThrown) {
			     });
		 
		if($("#app_name").val()!='0')
			$("#lbapp_name").text($( "#app_name option:selected" ).text());
// 		if('${list[0].app_dt}'!=null){
// 		 app_dt =ParseDateColumncommission('${list[0].app_dt}');
// 		} 
		
		
			if (app_dt != ''){
				$("#app_dt").val(convertDateFormate(app_dt));
			}
			
		var dt_of_tos =k[0].dt_of_tos;
		 console.log(k[0][6]);
		if (dt_of_tos != ''){
			$("#dt_of_tos").val(ParseDateColumncommission(dt_of_tos));
			
		}
		
		
		
		
		var comm_id=$("#comm_id").val();
			var cpurl="";
				cpurl="GetCommDataApprove?";
			$.post(cpurl + key + "=" + value,{ comm_id : comm_id},function(i) {
	   		 if(i.length > 0)
	   		 {
	   			if(i[0][6] != null)
		    	{
		    		dt_tos=ParseDateColumncommission(i[0][6]);
		    		$("#dt_tos_pre").val(dt_tos);
		    	}
		    	else{
		    		dt_tos='';
		    	}
	   			if(i[0][19] == '20'){
	   				$("#stats").show();
// 	   				$("#t_status_lbl").text('${list[0].t_status_lbl}');
	   					}
	   			if(i[0][23] != null){
		    		$('#unit_description_IN').text(i[0][23]);
		    	}
	   		 }
			});
		
	$.post('GETPosttenure_date?' + key + "=" + value,{ comm_id : comm_id },function(o) {
   		 if(o.length > 0){   			
	   			setMinDate('dt_of_tos', o[0][0]);	   			
   		 }	
   		 else
   		 {
   			setMinDate('dt_of_tos', '01/01/1890');
  
   		 }   		 
       });
	
	
}
	});
}
function posting_in_edit_fn() {
	
	if ($("input#personnel_no").val() == "") {
		alert("Please select Personal No");
		$("input#personnel_no").focus();
		return false;
	}		 	
	if ($("input#from_sus_no").val() == "") {
		alert("Please Select From SUS No");
		$("input#from_sus_no").focus();
		return false;
	}		
	if($("#dt_of_tos").val()== "DD/MM/YYYY"){
		alert("Please Select Date of TOS");
		$("input#dt_of_tos").focus();
		return false;
	 }
	if ($("input#dt_of_tos").val() == "") {
		alert("Please Select Date of TOS");
		$("input#dt_of_tos").focus();
		return false;
	}
	
	if(dt_tos!=null && dt_tos!=""){
		var newtos = $("input#dt_of_tos").val().split("/");
		var pretos=dt_tos.split("/");		
		var newtosM=newtos[1];
		var newtosY=newtos[2];		
		var pretosM=pretos[1];
		var pretosY=pretos[2];		
		if(newtosY==pretosY){
			if(newtosM<=pretosM){
				alert("Invalid Date of Tos");
				$("input#dt_of_tos").focus();
				return false;
			}
		}
		else if(newtosY<pretosY){
			alert("Invalid Date of Tos");
			$("input#dt_of_tos").focus();
			return false;
		}
	}
    var  comm_date=$('#comm_date').val();  
	if(comm_date!=''){
	
		if(getformatedDate(comm_date) > getformatedDate($("input#dt_of_tos").val())) {
			alert("TOS Should Be Greater Or Equal To Date of Commission");
			$("input#dt_of_tos").focus();
			return false;
		}
	}
var formdata=$('#form_posting').serialize();
	
	$.post('Edit_posting_in_Action_xml?' + key + "=" + value, formdata,
				function(data) {
			alert(data);
				}).fail(function(xhr, textStatus, errorThrown) {
			alert("fail to fetch")
		});
	
	
	
}



</script>