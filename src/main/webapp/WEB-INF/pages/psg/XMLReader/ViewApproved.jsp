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
<script src="js/Calender/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

<!-- <link rel="stylesheet" href="js/assets/css/bootstrap.min.css"> -->
<link rel="stylesheet" href="js/layout/css/animation.css">
<!-- <link rel="stylesheet" href="js/assets/scss/style.css">  -->
<link rel="stylesheet" href="js/assets/collapse/collapse.css">
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>


<!-- resizable_col -->
<link rel="stylesheet"
	href="js/assets/adjestable_Col/jquery.resizableColumns.css">
<script src="js/assets/adjestable_Col/jquery.resizableColumns.js"
	type="text/javascript"></script>

<style>
label {
	word-break: break-word;
}

.go-top {
	right: 1em;
	bottom: 2em;
	color: #FAFAFA;
	text-decoration: none;
	background: #F44336;
	padding: 5px;
	border-radius: 5px;
	border: 1px solid #e0e0e0;
	position: fixed;
	font-size: 180%;
}
</style>
<script>

$(window).scroll(function(){
    if($(this).scrollTop() > 100){
      $('.go-top').fadeIn(200);      
    } else {
      $('.go-top').fadeOut(300);
    }
  });
  $('.go-top').click(function() {
    event.preventDefault();
    
    $('html , body').animate({scrollTop: 0}, 1000);
  });
  </script>

<script>
function Cancel(){
  	
	$("#personnel_no1").val($("#personnel_noV").val()) ;
	$("#status1").val($("#statusV").val()) ;
	$("#rank1").val($("#rankV").val()) ;
	$("#comm_id").val($("#comm_idV").val()) ;
	$("#unit_name1").val($("#unit_nameV").val()) ;
	$("#unit_sus_no1").val($("#unit_sus_noV").val()) ;
	 
	document.getElementById('searchForm').submit();
}
</script>

<%-- <c:url value="xml_upload_logs" var="searchUrl" /> --%>
<%-- <form:form action="${searchUrl}" method="get" id="searchForm" --%>
<%-- 	name="searchForm" >  --%>
<%--  </form:form>  --%>

<form id="Personnel_no_form">
	<div class="animated fadeIn">
		<div class="" align="center">
			<div class="card">
				<div class="panel-group" id="accordion">
					<div class="panel panel-default" id="insp_div1">
						<div class="panel-heading">
							<div class="panel-heading">
								<h4 class="panel-title main_title" id="insp_div5">
									<b>APPROVED DATA OF UPDATED OFFR XML</b>
								</h4>
							</div>
							<div class="col-md-12"
								style="padding-top: 20px; padding-left: 250px;">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong>Personal No</label>
										</div>
										<div class="col-md-8">
											<input type="text" id="personnel_no" name="personnel_no"
												class="form-control autocomplete" autocomplete="off">
										</div>

										<!-- janki -->
										<input type="hidden" id="personnel_noV" name="personnel_noV"
											class="form-control autocomplete"> <input
											type="hidden" id="statusV" name="statusV"
											class="form-control autocomplete"> <input
											type="hidden" id="rankV" name="rankV"
											class="form-control autocomplete"> <input
											type=hidden id="comm_idV" name="comm_idV"
											class="form-control autocomplete"> <input
											type="hidden" id="unit_nameV" name="unit_nameV"
											class="form-control autocomplete"> <input
											type="hidden" id="unit_sus_noV" name="unit_sus_noV"
											class="form-control autocomplete">
											<input
											type=hidden id="comm_id" name="comm_id"
											class="form-control autocomplete">

									</div>
								</div>

								<!-- <div class="col-md-6" id="process_btn">
									     <input type="button" class="btn btn-success btn-sm" id="btn1" value="Process" onclick="return personal_number();">
									</div> -->
								<div class="card-footer" align="right" class="form-control"
									id="back_bt_div">
									<!--  style="display: none;"-->
									<a class="btn btn-primary btn-sm"  href="xml_upload_logs">Back</a> 
<!-- 									<input type="button" class="btn btn-info btn-sm" -->
<!-- 										onclick="Cancel();" value="Back"> -->
								</div>
							</div>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</form>

<div id="maindetailsdiv">
	<div class="card">
		<div class="panel-group" id="accordion4">
			<div class="panel panel-default" id="app_div1">
				<div class="panel-heading">
					<h4 class="panel-title main_title red" id="a_div5">
						<a class="droparrow collapsed" data-toggle="collapse"
							data-parent="#accordion4" href="#" id="app_final"
							onclick="divN(this)"><b>RECORD</b></a>
					</h4>
				</div>
				<div id="collapse1app" class="panel-collapse collapse">
					<div class="card-body card-block">
						<br>
						<div class="row">

							<div class="col-md-12">
								<div class="col-md-3">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;"></strong> Name</label>
										</div>
										<div class="col-md-8">
											<label class=" form-control-label" id="cadet_name"></label> <label
												class=" form-control-label" id="dob" style="display: none;"></label>
											<label class=" form-control-label" id="marital_status_p"
												style="display: none;"></label>
										</div>
									</div>
								</div>
								<div class="col-md-3">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"> Rank</label>
										</div>
										<div class="col-md-8">
											<label class=" form-control-label" id="p_rank"></label> <input
												type="hidden" id="hd_p_rank" name="hd_p_rank"
												class="form-control autocomplete" autocomplete="off">
										</div>
									</div>
								</div>
								<div class="col-md-3">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;"></strong> Appointment</label>
										</div>
										<div class="col-md-8">
											<label class=" form-control-label" id="p_app"></label>
										</div>
									</div>
								</div>
								<div class="col-md-3">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"> Date of
												Appointment</label>
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
										</div>
									</div>
								</div>
								<div class="col-md-3">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;"></strong> SUS No</label>
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
											<label class=" form-control-label"><strong
												style="color: red;"></strong> Command</label>
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
											<label class=" form-control-label"><strong
												style="color: red;"></strong> ID Card No</label>
										</div>
										<div class="col-md-8">
											<label class=" form-control-label" id="p_id_no"></label>
										</div>
									</div>
								</div>
								<div class="col-md-3">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"> Religion</label>
										</div>
										<div class="col-md-8">
											<label class=" form-control-label" id="p_religion"></label>
										</div>
									</div>
								</div>
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
											<label class=" form-control-label"><strong
												style="color: red;"></strong> Serving Status</label>
										</div>
										<div class="col-md-8">
											<label class=" form-control-label" id="p_serving_status"></label>
										</div>
									</div>
								</div>

							</div>
							<div class="col-md-12">
								<div class="col-md-3" style="display: none;" id="reg_div">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;"></strong> Regiment</label>
										</div>
										<div class="col-md-8">
											<label id="p_regt" class=" form-control-label"></label> <select
												name="regt" id="inter_arm_regt_val" class="form-control"
												style="display: none;">
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

<!-- Start Change of Rank  -->
<c:if test="${ChangeOfRank.size() != 0}">
	<form id="form_ChangeOfRank">
		<div class="card">
			<div class="panel-group" id="accordion4">
				<div class="panel panel-default" id="a_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title red" id="a_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion4" href="#" id="a_final"
								onclick="divN(this)"><b>CHANGE IN RANK</b></a>
						</h4>
					</div>
					<div id="collapse1a" class="panel-collapse collapse">
						<div class="card-body card-block">
							<br>
							<div class="row">
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"> Authority </label>
											</div>
											<div class="col-md-8">
												<label id="r_authority" class=" form-control-label"></label>
												<input type="hidden" id="r_authority" name="authority"
													class="form-control autocomplete" autocomplete="off">
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"> Date of
													Authority </label>
											</div>
											<div class="col-md-8">
												<label id="r_date_of_authority" class=" form-control-label"></label>
												<input type="hidden" id="ch_r_id" name="ch_r_id"
													class="form-control" /> <input type="hidden"
													id="r_date_of_authority" name="date_of_authority"
													class="form-control autocomplete" autocomplete="off">
											</div>
										</div>
									</div>
								</div>

								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"> Rank Type </label>
											</div>
											<div class="col-md-8">
												<label id="rank_type" class=" form-control-label"></label> <select
													id="rank_typeSelect" name="rank_typeSelect"
													class="form-control " style="display: none">

													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getOFFTypeofRankList}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"> Rank </label>
											</div>
											<div class="col-md-8">
												<label class=" form-control-label" id="rank"></label> <select
													name="rank_val" id="rank_val" class="form-control"
													style="display: none">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getTypeofRankList}"
														varStatus="num">
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
												<label class=" form-control-label"> Date of Rank </label>
											</div>
											<div class="col-md-8">
												<label class=" form-control-label" id="date_of_rank"></label>
												<input type="hidden" name="date_of_rank" id="date_of_rank"
													class="form-control">
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
	</form>
</c:if>
<!-- END Change of Rank  -->

<!-- START CHANGE OF NAME DETAIL -->
<c:if test="${ChangeOfName.size() != 0}">
	<form id="form_change_of_name">
		<div class="card">
			<div class="panel-group" id="accordion5">
				<div class="panel panel-default" id="b_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title red" id="b_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion5" href="#" id="b_final"
								onclick="divN(this)"><b>CHANGE IN NAME</b></a>
						</h4>
					</div>
					<div id="collapse1b" class="panel-collapse collapse">
						<div class="card-body card-block">
							<br>
							<div class="row">
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"> Authority </label>
											</div>
											<div class="col-md-8">
												<label class=" form-control-label" id="na_authority"></label>
												<input type="hidden" id="na_authority" name="authority"
													class="form-control autocomplete" autocomplete="off">
												<input type="hidden" id="name_id" name="name_id"
													class="form-control autocomplete" autocomplete="off">

											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"> Date of
													Authority </label>
											</div>
											<div class="col-md-8">
												<label class=" form-control-label" id="na_date_of_authority"></label>
												<input type="hidden" id="na_date_of_authority"
													name="date_of_authority" class="form-control autocomplete"
													autocomplete="off">
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"> Name </label>
											</div>
											<div class="col-md-8">
												<label class=" form-control-label" id="name"></label> <input
													type="hidden" id="name" name="name"
													class="form-control autocomplete" autocomplete="off">
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"> Date of Change in Name</label>
											</div>
											<div class="col-md-8">
												<label class=" form-control-label" id="na_change_in_name_date"></label>
												<input type="hidden" id="na_change_in_name_date"
													name="change_in_name_date" class="form-control autocomplete"
													autocomplete="off">
											</div>
										</div>
									</div>

								
							</div>



						</div>

					</div>
				</div>
			</div>
		</div>
	</form>
</c:if>
<!-- END CHANGE OF NAME DETAIL -->
<!-- START CHANGE OF APPOINTEMENT DETAIL -->
<c:if test="${ChngAppointment.size() != 0}">

	<form id="form_change_of_appointment">
		<div class="card">
			<div class="panel-group" id="accordion41">
				<div class="panel panel-default" id="c_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title red" id="c_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion41" href="#" id="c_final"
								onclick="divN(this)"><b>CHANGE IN APPOINTMENT</b></a>
						</h4>
					</div>
					<div id="collapse1c" class="panel-collapse collapse">
						<div class="card-body card-block">
							<br>
							<div class="row">
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label for="Authority"> <strong style="color: red;">*
												</strong>Authority
												</label>
											</div>
											<div class="col-md-8">
												<label id="appt_authority" class=" form-control-label"></label>
												<input type="hidden" id="appt_authority"
													name="appt_authority" class="form-control"
													autocomplete="off"> <input type="hidden"
													id="appoint_id" name="appoint_id" class="form-control"
													autocomplete="off">
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label for="Date Autho"><strong style="color: red;">*
												</strong> Date of Authority </label>
											</div>
											<div class="col-md-8">
												<label id="appt_date_of_authority"
													class=" form-control-label"></label> <input type="hidden"
													id="appt_date_of_authority" name="appt_date_of_authority"
													autocomplete="off" class="form-control" maxlength="10">
											</div>
										</div>
									</div>

								</div>
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong>Appointment </label>
											</div>
											<div class="col-md-8">
												<label id="appointment" class=" form-control-label"></label>
												<select id="appointment_val" name="appointment_val"
													class="form-control" style="display: none">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getTypeofAppointementList}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>


											</div>
										</div>
									</div>

									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong> Date of Appointment </label>
											</div>
											<div class="col-md-8">
												<label id="date_of_appointment" class=" form-control-label"></label>
												<input type="hidden" id="date_of_appointment"
													name="date_of_appointment" class="form-control"
													autocomplete="off" maxlength="10">
											</div>
										</div>
									</div>

								</div>
 <div class="col-md-12" id= "att_dapu">	              					
						<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">X-List SUS No</label>
						                </div>
						               <div class="col-md-8">
						                   <label class=" form-control-label" id="at_parent_sus_no"></label>
						                    <input type="hidden" id="at_parent_sus_no" name="parent_sus_no" class="form-control autocomplete" autocomplete="off" > 
						               </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					 <div class="col-md-4">
						                    <label class=" form-control-label">X-List Unit Name</label>
						                </div>
						               <div class="col-md-8">
						                   <label class=" form-control-label" id="at_parent_unit"></label>
						                    <input type="hidden" id="at_parent_unit" name="parent_unit" class="form-control autocomplete" autocomplete="off" > 
						               </div>
	              				</div>
	              			</div>	
	              		<div class="col-md-12" id= "att_dapu1">	
								<div class="col-md-6">
	              					 <div class="col-md-4">
						                    <label class=" form-control-label">X-List Location</label>
						                </div>
						               <div class="col-md-8">
						                   <label class=" form-control-label" id="at_attachment_location"></label>
						                    <input type="hidden" id="at_attachment_location" name="x_list_loc" class="form-control autocomplete" autocomplete="off" > 
						               </div>
	              				</div>

	              				
							</div>

							</div>

						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
</c:if>
<!-- END CHANGE OF APPOINTEMENT DETAIL -->

<!-- START identity_card -->
<c:if test="${IdentityCard.size() != 0}">
	<form id="form_identity_card">
		<div class="card">
			<div class="panel-group" id="accordion43">
				<div class="panel panel-default" id="d_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title red" id="d_div5">
							<a class="droparrow collapsed " data-toggle="collapse"
								data-parent="#accordio43" href="#" id="d_final"
								onclick="divN(this)"><b>CHANGE IN IDENTITY
									CARD</b></a>
						</h4>
					</div>
					<div id="collapse1d" class="panel-collapse collapse">
						<div class="card-body card-block">
							<br>
							<div class="row">
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"> ID Card no</label>
											</div>
											<div class="col-md-8">
												<label id="id_card_no" class=" form-control-label"></label>
												<input type="hidden" id="id_card_no" name="id_card_no"
													class="form-control autocomplete" autocomplete="off"
													maxlength="10">
											</div>
										</div>
									</div>

									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"> Issuing Date</label>
											</div>
											<div class="col-md-8">
												<label id="issue_dt" class=" form-control-label"></label> <input
													type="hidden" id="issue_dt" name="issue_dt"
													class="form-control autocomplete" autocomplete="off"
													maxlength="10">
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"> Issuing
													Authority </label>
											</div>
											<div class="col-md-8">
												<label id="issue_authority" class=" form-control-label"></label>
												<input type="hidden" id="issue_authority"
													name="issue_authority" class="form-control autocomplete"
													autocomplete="off" maxlength="10"> <input
													type="hidden" id="card_id" name="card_id"
													class="form-control" autocomplete="off">
											</div>
										</div>
									</div>

									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"> Visible
													Identification Marks</label>
											</div>
											<div class="col-md-8">
												<label id="id_marks" class=" form-control-label"></label> <input
													type="hidden" id="id_marks" name="id_marks"
													class="form-control autocomplete" autocomplete="off"
													maxlength="10">
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"> Hair Colour</label>
											</div>
											<div class="col-md-8">
												<label id="hair_colour" class=" form-control-label"></label>
												<select name="hair_colourselect" id="hair_colourselect"
													class="form-control" style="display: none;">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getHair_ColourList}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>;
												</c:forEach>
												</select>

											</div>
										</div>
									</div>

									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"> Eye Colour</label>
											</div>
											<div class="col-md-8">

												<label id="eye_colour" class=" form-control-label"></label>
												<select name="eye_colourselect" id="eye_colourselect"
													class="form-control" style="display: none;">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getEye_ColourList}"
														varStatus="num">
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
												<label class=" form-control-label"><strong
													style="color: red;"> </strong> Photograph </label>
											</div>
											<div class="col-md-8">
												<img id="identity_image_preview" alt="Officer Image"
													src="js/img/No_Image.jpg" width="100" height="100" />
											</div>
										</div>
									</div>
									<!-- 	<div class="col-md-6">
									<div class="row form-group">
										<img id="identity_image_preview" alt="Officer Image"  src="js/img/No_Image.jpg" width="100" height="100" />
									</div>
								</div> -->


								</div>


							</div>

						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
</c:if>
<!-- END identity_card -->




<!-- Start RELIGION-------------- -->
<c:if test="${religion.size() != 0}">
	<form id="form_religion">
		<div class="card">
			<div class="panel-group" id="accordion6">
				<div class="panel panel-default" id="e_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title lightblue" id="e_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion6" href="#" id="e_final"
								onclick="divN(this)"><b>CHANGE IN RELIGION</b></a>
						</h4>
					</div>
					<div id="collapse1e" class="panel-collapse collapse">
						<div class="card-body card-block">
							<br>
							<div class="row">
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"> Authority </label>
											</div>
											<div class="col-md-8">
												<label id="rel_authority" class=" form-control-label"></label>
												<input type="hidden" id="rel_authority" name="authority"
													class="form-control autocomplete" autocomplete="off">
												<input type="hidden" id="rel_id" name="rel_id"
													class="form-control autocomplete" autocomplete="off">

											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"> Date of
													Authority </label>
											</div>
											<div class="col-md-8">
												<label class=" form-control-label"
													id="rel_date_of_authority"></label> <input type="hidden"
													id="rel_date_of_authority" name="date_of_authority"
													class="form-control autocomplete" autocomplete="off">

											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"> Religion </label>
											</div>
											<div class="col-md-8">
												<label class=" form-control-label" id="religion"></label> <select
													name="religionSelect" id="religionSelect"
													class="form-control" style="display: none;">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getReligionList}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>

									<div class="col-md-6" id="other_div" style="display: none;">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"> </label>
											</div>
											<div class="col-md-8">
												<label class=" form-control-label" id="religion_other"></label>
												<!-- <input type="text" id="religion_other" name="religion_other" class="form-control autocomplete" autocomplete="off" > -->
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12">
								<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"> Date of Change in Religion</label>
											</div>
											<div class="col-md-8">
												<label class=" form-control-label"
													id="rel_change_in_rel_date"></label> <input type="hidden"
													id="rel_change_in_rel_date" name="change_in_rel_date"
													class="form-control autocomplete" autocomplete="off">

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
	</form>
</c:if>
<!-- END RELIGION-------------- -->
<!-- START MARITAL EVENTS -->


<!-- END MARITAL EVENTS -->
<!--  START DETAILS OF CHILDRENS -->

<c:if test="${ChildDetails.size() != 0}">
	<!--  START DETAILS OF CHILDRENS -->
	<div class="card">
		<div class="panel-group" id="accordion3">
			<div class="panel panel-default" id="g_div1">
				<div class="panel-heading">
					<h4 class="panel-title main_title lightblue" id="g_div5">
						<a class="droparrow collapsed" data-toggle="collapse"
							data-parent="#accordion3" href="#" id="g_final"
							onclick="divN(this)"><b>UPDATE CHILD DETAILS</b></a>
					</h4>
				</div>
				<div id="collapse1g" class="panel-collapse collapse">
					<div class="card-body card-block">
						<div class="card-body-header">
							<h5></h5>
						</div>
						<table id="m_children_details_table"
							class="table-bordered watermarked"
							style="margin-top: 10px; width: 100%;">
							<thead style="color: white; text-align: center;">
								<tr>
									<!--exa  -->
									<th style="width: 2%;">Sr No</th>
									<th style="width: 10%;">Name</th>
									<th style="width: 10%;">Date of Birth</th>
									<th style="width: 5%;">If Specially Abled Child</th>
									<th style="width: 10%;">Relationship</th>
									<th style="width: 5%;">If Adopted Child</th>
									<th style="width: 10%;">Adoption Date</th>
									<th style="width: 10%;">Aadhaar Card No</th>
									<th style="width: 10%;">PAN Card No</th>
									<th style="width: 5%;">SERVICE/EX-SERVICE</th>
									<th style="width: 10%;">PERSONAL NO</th>
									<th style="width: 15%;">OTHER SERVICE</th>

								</tr>
							</thead>
							<tbody data-spy="scroll" id="m_children_detailstbody"
								style="min-height: 46px; max-height: 650px; text-align: center;">

							</tbody>
						</table>
					</div>

				</div>
			</div>
		</div>
	</div>


</c:if>
<!-- END DETAILS OF CHILDRENS -->

<!-- START NOK -->
<c:if test="${NOK.size() != 0}">
	<form id="form_nok_addr_details_form">
		<div class="card">
			<div class="panel-group" id="accordion10">
				<div class="panel panel-default" id="h_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title lightblue" id="h_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion10" href="#" id="h_final"
								onclick="divN(this)"><b>CHANGE IN NOK</b></a>
						</h4>
					</div>
					<div id="collapse1h" class="panel-collapse collapse">
						<div class="card-body card-block">
							<br>
							<div class="row">

								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong> AUTHORITY</label>
											</div>
											<div class="col-md-8">
												<label id="nok_authority" class=" form-control-label"></label>
												<input type="hidden" id="nok_authority" name="authority"
													class="form-control autocomplete" autocomplete="off">
											</div>
										</div>
									</div>

									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong> DATE OF AUTHORITY</label>
											</div>
											<div class="col-md-8">
												<label id="nok_date_authority" class=" form-control-label"></label>
												<input type="hidden" id="nok_date_authority"
													name="date_authority" class="form-control autocomplete"
													autocomplete="off">
											</div>
										</div>
									</div>
								</div>

								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong> Name of NOK</label>
											</div>
											<div class="col-md-8">
												<label id="nok_name" class=" form-control-label"></label> <input
													type="hidden" id="nok_name" name="nok_name"
													class="form-control autocomplete" autocomplete="off">
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong> Relation</label>
											</div>
											<div class="col-md-8">
												<input type="hidden" id="nok_id" name="nok_id" value="0"
													class="form-control autocomplete" autocomplete="off">
												<label id="nok_relation" class=" form-control-label"></label>
												<select name="nok_relationSelect" id="nok_relationSelect"
													class="form-control" style="display: none">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getRelationList}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
								</div>

								<div class="col-md-12">
									<label class=" form-control-label" style="margin-left: 10px;"><h4>
											NOK's Address</h4></label>
								</div>

								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong>COUNTRY</label>
											</div>
											<div class="col-md-8">
												<label id="nok_country" class=" form-control-label"></label>
												<select name="nok_countrySelect" id="nok_countrySelect"
													class="form-control" style="display: none">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getMedCountryName}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>

												</SELECT>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong> STATE</label>
											</div>
											<div class="col-md-8">
												<label id="nok_state" class=" form-control-label"></label> <select
													name="nok_stateSelect" id="nok_stateSelect"
													class="form-control-sm form-control" style="display: none">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getMedStateName}"
														varStatus="num">
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
												<label class=" form-control-label"><strong
													style="color: red;">* </strong> DISTRICT</label>
											</div>
											<div class="col-md-8">
												<label id="nok_district" class=" form-control-label"></label>
												<select name="nok_districtSelect" id="nok_districtSelect"
													class="form-control" style="display: none">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getMedDistrictName}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong> TEHSIL</label>
											</div>
											<div class="col-md-8">
												<label id="nok_tehsil" class=" form-control-label"></label>
												<select name="nok_tehsilSelect" id="nok_tehsilSelect"
													class="form-control" style="display: none">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getMedCityName}"
														varStatus="num">
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
												<label class=" form-control-label"><strong
													style="color: red;">* </strong> Village/Town/City</label>
											</div>
											<div class="col-md-8">
												<label id="nok_village" class=" form-control-label"></label>
												<%-- <select name="nok_villageSelect" id="nok_villageSelect" class="form-control" style="display:none">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getVillageList}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>		 --%>
											</div>
										</div>
									</div>

									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong> Pin</label>
											</div>
											<div class="col-md-8">
												<label id="nok_pin" class=" form-control-label"></label> <input
													type="hidden" id="nok_pin" name="nok_pin"
													class="form-control autocomplete" autocomplete="off"
													onkeypress="return isNumber0_9Only(event)">
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong> Nearest Railway Station</label>
											</div>
											<div class="col-md-8">
												<label id="nok_rail" class=" form-control-label"></label> <input
													type="hidden" id="nok_rail" name="nok_rail"
													class="form-control autocomplete" autocomplete="off">
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong>Is Rural /Urban/Semi Urban</label>
											</div>
											<div class="col-md-8">
												<label for="nok_rural" id="nok_rural_urban"></label>
												<!-- <input type="radio" id="nok_rural" name="nok_rural_urban" value="rural" > Rural</label> 
					    	 <label for="nok_urban" >
							 <input type="radio" id="nok_urban" name="nok_rural_urban" value="urban" > Urban</label> 
					    	 <label for="nok_semi_urban">
							 <input type="radio" id="nok_semi_urban" name="nok_rural_urban" value="semi_urban" >Semi Urban</label>  -->
											</div>
										</div>
									</div>
								</div>

								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong>Nok Mobile No</label>
											</div>
											<div class="col-md-8">
												<label id="nok_mobile_no" class=" form-control-label"></label>
												<input type="hidden" id="nok_mobile_no" name="nok_mobile_no"
													class="form-control autocomplete" autocomplete="off"
													onkeypress="return isNumber0_9Only(event)">
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
	</form>
</c:if>
<!-- END NOK -->
<!-- START CHANGE OF ADD -->

<c:if test="${ChangeAdd.size() != 0}">
	<form id="form_addr_details_form">
		<div class="card">
			<div class="panel-group" id="accordion9">
				<div class="panel panel-default" id="i_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title lightblue" id="i_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion9" href="#" id="i_final"
								onclick="divN(this)"><b>CHANGE IN ADDRESS</b></a>
						</h4>
					</div>
					<div id="collapse1i" class="panel-collapse collapse">
						<div class="card-body card-block">
							<br>
							<div class="row">
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong>Authority</label>
											</div>
											<div class="col-md-8">
												<label id="addr_authority"></label> <input type="hidden"
													id="addr_authority" class="form-control "
													autocomplete="off">
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong>Date of Authority</label>
											</div>
											<div class="col-md-8">
												<label id="addr_date_authority"></label> <input
													type="hidden" id="addr_date_authority"
													class="form-control " autocomplete="off">
											</div>
										</div>
									</div>
								</div>
								<label class=" form-control-label" style="margin-left: 10px;"><h4>
										Present Domicile</h4></label>

								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong>COUNTRY</label>
											</div>
											<div class="col-md-8">
												<label id="pre_country" class=" form-control-label"></label>
												<select name="pre_country_val" id="pre_country_val"
													class="form-control" style="display: none;">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getMedCountryName}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>

											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong> STATE</label>
											</div>
											<div class="col-md-8">
												<input type="hidden" id="addr_ch_id" name="addr_ch_id"
													value="0" class="form-control autocomplete"
													autocomplete="off"> <label id="pre_domicile_state"
													class=" form-control-label"></label> <select
													name="pre_domicile_state_val" id="pre_domicile_state_val"
													class="form-control" style="display: none;">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getMedStateName}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</SELECT>
											</div>
										</div>
									</div>

								</div>

								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong> DISTRICT</label>
											</div>
											<div class="col-md-8">
												<label id="pre_domicile_district"
													class=" form-control-label"></label> <select
													name="pre_domicile_district_val"
													id="pre_domicile_district_val" class="form-control"
													style="display: none;">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getMedDistrictName}"
														varStatus="num">
														<option value="${item[0]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong> TEHSIL*</label>
											</div>
											<div class="col-md-8">
												<label id="pre_domicile_tesil" class=" form-control-label"></label>
												<select name="pre_domicile_tesil_val"
													id="pre_domicile_tesil_val" class="form-control"
													style="display: none;">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getMedCityName}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>

												</SELECT>
											</div>
										</div>
									</div>

								</div>



								<label class=" form-control-label" style="margin-left: 10px;"><h4>
										Permanent Address</h4></label>
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong>COUNTRY</label>
											</div>
											<div class="col-md-8">

												<label id="per_home_addr_country"
													class=" form-control-label"></label> <select
													name="per_home_addr_country_val"
													id="per_home_addr_country_val" class="form-control"
													style="display: none;">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getMedCountryName}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>

											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong> STATE</label>
											</div>
											<div class="col-md-8">
												<label id="per_home_addr_state" class=" form-control-label"></label>
												<select name="per_home_addr_state_val"
													id="per_home_addr_state_val" class="form-control"
													style="display: none;">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getMedStateName}"
														varStatus="num">
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
												<label class=" form-control-label"><strong
													style="color: red;">* </strong> DISTRICT</label>
											</div>
											<div class="col-md-8">
												<label id="per_home_addr_district"
													class=" form-control-label"></label> <select
													name="per_home_addr_district_val"
													id="per_home_addr_district_val" class="form-control"
													style="display: none;">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getMedDistrictName}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong> TEHSIL</label>
											</div>
											<div class="col-md-8">
												<label id="per_home_addr_tehsil" class=" form-control-label"></label>
												<select name="per_home_addr_tehsil_val"
													id="per_home_addr_tehsil_val" class="form-control"
													style="display: none;">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getMedCityName}"
														varStatus="num">
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
												<label class=" form-control-label"><strong
													style="color: red;">* </strong> Village/Town/City</label>
											</div>
											<div class="col-md-8">
												<label id="per_home_addr_village"
													class=" form-control-label"></label>
												<%-- <select name="per_home_addr_village_val" id="per_home_addr_village_val" class="form-control" style="display: none;">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getVillageList}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>	 --%>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong> Pin</label>
											</div>
											<div class="col-md-8">
												<label id="per_home_addr_pin" class=" form-control-label"></label>
												<input type="hidden" id="per_home_addr_pin"
													name="per_home_addr_pin" class="form-control autocomplete"
													autocomplete="off"
													onkeypress="return isNumber0_9Only(event)">
											</div>
										</div>
									</div>

								</div>

								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong> Nearest Railway Station</label>
											</div>
											<div class="col-md-8">
												<label id="per_home_addr_rail" class=" form-control-label"></label>
												<input type="hidden" id="per_home_addr_rail"
													name="per_home_addr_rail" class="form-control autocomplete"
													autocomplete="off">
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">

												<label class=" form-control-label"><strong
													style="color: red;">* </strong>Is Rural /Urban/Semi Urban</label>
											</div>
											<div class="col-md-8">

												<label for="lbl_perm_home_addr_rural" id="perm_home_addr"></label>
												<!-- <input type="radio" id="perm_home_addr_rural" name="perm_home_addr_rural_urban" value="rural" > Rural</label> 
					    	 <label for="lbl_perm_home_addr_urban" >
							 <input type="radio" id="perm_home_addr_urban" name="perm_home_addr_rural_urban" value="urban" > Urban</label> 
					    	 <label for="lbl_perm_home_addr_semi_urban">
							 <input type="radio" id="perm_home_addr_semi_urban" name="perm_home_addr_rural_urban" value="semi_urban" >Semi Urban</label>  -->

											</div>
										</div>
									</div>
								</div>

								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong>Border area</label>
											</div>
											<div class="col-md-8">
												<!--  <label for="border_area">
							 <input type="radio" id="border_area" name="border_area" value="yes"   >yes</label> 
					    	 <label for="border_area1"> 
				      		 <input type="radio" id="border_area1" name="border_area" value="no" > no</label> -->

												<label for="border_area" id="per_border_area"></label>
												<!-- <input type="radio" id="per_border_area" name="per_border_area" value="yes"   >yes</label> 
					    	 <label for="per_border_area1"> 
				      		 <input type="radio" id="per_border_area1" name="per_border_area" value="no" > no -->

											</div>
										</div>
									</div>
								</div>

								<div class="col-md-12"></div>
								<!-- 	<div class="col-md-12" style="font-size: 15px;">	
           		<input type="checkbox" id="check_address" name="check_address" onclick="copy_address()">
               	<label for="text-input" class=" form-control-label" style="color: #dd1a3e; margin-left:10px;">  Same as Permanent Address </label>
            </div> -->
								<div class="col-md-12">
									<label class=" form-control-label" style="margin-left: 10px;"><h4>
											Present Address</h4></label>
								</div>

								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong>COUNTRY</label>
											</div>
											<div class="col-md-8">
												<label id="pers_addr_country" class=" form-control-label"></label>
												<select name="pers_addr_country_val"
													id="pers_addr_country_val" class="form-control"
													style="display: none;">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getMedCountryName}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>

											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong> STATE</label>
											</div>
											<div class="col-md-8">
												<label id="pers_addr_state" class=" form-control-label"></label>
												<select name="pers_addr_state_val" id="pers_addr_state_val"
													class="form-control" style="display: none;">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getMedStateName}"
														varStatus="num">
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
												<label class=" form-control-label"><strong
													style="color: red;">* </strong> DISTRICT</label>
											</div>
											<div class="col-md-8">
												<label id="pers_addr_district" class=" form-control-label"></label>
												<select name="pers_addr_district_val"
													id="pers_addr_district_val" class="form-control"
													style="display: none;">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getMedDistrictName}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong> TEHSIL</label>
											</div>
											<div class="col-md-8">
												<label id="pers_addr_tehsil" class=" form-control-label"></label>
												<select name="pers_addr_tehsil_val"
													id="pers_addr_tehsil_val" class="form-control"
													style="display: none;">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getMedCityName}"
														varStatus="num">
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
												<label class=" form-control-label"><strong
													style="color: red;">* </strong> Village/Town/City</label>
											</div>
											<div class="col-md-8">
												<label id="pers_addr_village" class=" form-control-label"></label>
												<%--  <select name="pers_addr_village_val" id="pers_addr_village_val" class="form-control" style="display: none;">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getVillageList}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>	 --%>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong> Pin</label>
											</div>
											<div class="col-md-8">
												<label id="pers_addr_pin" class=" form-control-label"></label>
												<input type="hidden" id="pers_addr_pin" name="pers_addr_pin"
													class="form-control autocomplete" autocomplete="off"
													onkeypress="return isNumber0_9Only(event)">
											</div>
										</div>
									</div>

								</div>

								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong> Nearest Railway Station</label>
											</div>
											<div class="col-md-8">
												<label id="pers_addr_rail" class=" form-control-label"></label>
												<input type="hidden" id="pers_addr_rail"
													name="pers_addr_rail" class="form-control autocomplete"
													autocomplete="off">
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong>Is Rural /Urban/Semi Urban</label>
											</div>
											<div class="col-md-8">
												<label for="pers_addr_rural" id="pers_addr_rural_urban"></label>
												<!-- 	 <input type="radio" id="pers_addr_rural" name="pers_addr_rural_urban" value="rural" > Rural</label> 
					    	 <label for="pers_addr_urban" >
							 <input type="radio" id="pers_addr_urban" name="pers_addr_rural_urban" value="urban" > Urban</label> 
					    	 <label for="pers_addr_semi_urban">
							 <input type="radio" id="pers_addr_semi_urban" name="pers_addr_rural_urban" value="semi_urban" >Semi Urban</label>  -->




											</div>
										</div>
									</div>
								</div>
								<div id="spouse_addressInnerdiv" style="display: none">
									<label for="text-input" class=" form-control-label"
										style="color: #dd1a3e; margin-left: 10px;"> Family
										Staying SF ACCN </label>
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;">* </strong> COUNTRY</label>
												</div>
												<div class="col-md-8">
													<label id="spouse_addr_Countrylb"
														name="spouse_addr_Countrylb"></label> <select
														style="display: none" name="spouse_addr_Country"
														id="spouse_addr_Country"
														onchange="fn_spouse_addr_Country();"
														class="form-control-sm form-control">
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${getMedCountryName}"
															varStatus="num">
															<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
														</c:forEach>
													</select>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;">* </strong> STATE</label>
												</div>
												<div class="col-md-8">
													<label id="spouse_addr_statelb" name="spouse_addr_statelb"></label>
													<select style="display: none" name="spouse_addr_state"
														id="spouse_addr_state" onchange="fn_spouse_addr_state();"
														class="form-control-sm form-control">
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${getMedStateName}"
															varStatus="num">
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
													<label class=" form-control-label"><strong
														style="color: red;">* </strong> DISTRICT</label>
												</div>
												<div class="col-md-8">
													<label id="spouse_addr_districtlb"
														name="spouse_addr_districtlb"></label> <select
														style="display: none" name="spouse_addr_district"
														id="spouse_addr_district"
														onchange="fn_spouse_addr_district();"
														class="form-control-sm form-control">
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${getMedDistrictName}"
															varStatus="num">
															<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
														</c:forEach>
													</select>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row foInteger.parseInt(rm-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;">* </strong> TEHSIL</label>
												</div>
												<div class="col-md-8">
													<label id="spouse_addr_tehsillb"
														name="spouse_addr_tehsillb"></label> <select
														style="display: none" name="spouse_addr_tehsil"
														id="spouse_addr_tehsil"
														class="form-control-sm form-control">
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${getMedCityName}"
															varStatus="num">
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
													<label class=" form-control-label"><strong
														style="color: red;">* </strong> Village/Town/City</label>
												</div>
												<div class="col-md-8">
													<%--  <select name="pers_addr_village" id="pers_addr_village" class="form-control-sm form-control">
											<option value="0">--Select--</option>
											<c:forEach var="item" items="${getVillageList}" varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select> --%>
													<label id="spouse_addr_village" name="spouse_addr_village"
														class=" autocomplete"></label>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;">* </strong> Pin</label>
												</div>
												<div class="col-md-8">
													<label id="spouse_addr_pin" name="spouse_addr_pin"
														class=" autocomplete"></label>
												</div>
											</div>
										</div>
									</div>


									<div class="col-md-12">

										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;">* </strong> Nearest Railway Station</label>
												</div>
												<div class="col-md-8">
													<label id="spouse_addr_rail" name="spouse_addr_rail"
														class=" autocomplete"></label>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;">* </strong>Is Rural /Urban/Semi Urban</label>
												</div>
												<div class="col-md-8">
													<label for="spouse_addr_rural" id="spouse_addr_rural_urban"></label>
													<!-- 	 <input type="radio" id="pers_addr_rural" name="pers_addr_rural_urban" value="rural" > Rural</label> 
					    	 <label for="pers_addr_urban" >
							 <input type="radio" id="pers_addr_urban" name="pers_addr_rural_urban" value="urban" > Urban</label> 
					    	 <label for="pers_addr_semi_urban">
							 <input type="radio" id="pers_addr_semi_urban" name="pers_addr_rural_urban" value="semi_urban" >Semi Urban</label>  -->




												</div>
											</div>
										</div>
									</div>
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;">* </strong>Stn HQ SUS No</label>
												</div>
												<div class="col-md-8">
													<label class=" form-control-label" id="Stn_HQ_sus_no"></label>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;">* </strong>Stn HQ Name</label>
												</div>
												<div class="col-md-8">
													<label class=" form-control-label" id="Stn_HQ_unit_name"></label>
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
	</form>
</c:if>
<!-- END CHANGE OF ADD -->

<!-- START CDA ACCOUNT NO -->

<c:if test="${CDAaccount.size() != 0}">
	<div class="card">
		<div class="panel-group" id="accordion1">
			<div class="panel panel-default" id="j_div1">
				<div class="panel-heading">
					<h4 class="panel-title main_title lightblue" id="j_div5">
						<a class="droparrow collapsed" data-toggle="collapse"
							data-parent="#accordion1" href="#" id="j_final"
							onclick="divN(this)"><b>CHANGE IN CONTACT
								DETAILS</b></a>
					</h4>
				</div>
				<div id="collapse1j" class="panel-collapse collapse">
					<div class="card-body card-block">
						<br>
						<div class="row">
							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong>Gmail/Others</label>
										</div>
										<div class="col-md-8">
											<label id="gmail" class=" form-control-label"></label> <input
												type="hidden" id="gmail" name="gmail"
												class="form-control autocomplete" autocomplete="off">
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong>NIC Mail</label>
										</div>
										<div class="col-md-8">
											<label id="nic_mail" class=" form-control-label"></label> <input
												type="hidden" id="nic_mail" name="nic_mail"
												class="form-control autocomplete" autocomplete="off">
										</div>
									</div>
								</div>

							</div>

							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong>Mobile No</label>
										</div>
										<div class="col-md-8">
											<label id="mobile_no" class=" form-control-label"></label> <input
												type="hidden" id="cda_id" name="cda_id"
												class="form-control autocomplete" autocomplete="off"
												maxlength="64"> <input type="hidden" id="mobile_no"
												name="mobile_no" class="form-control autocomplete"
												autocomplete="off">
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
</c:if>
<!-- START CDA ACCOUNT NO -->

<!-- START LANGUAGE DETAIL -->
<c:if test="${Language.size() != 0}">
	<div class="card">
		<div class="panel-group" id="accordion15">
			<div class="panel panel-default" id="k_div1">
				<div class="panel-heading">
					<h4 class="panel-title main_title lightgreen" id="k_div5">
						<a class="droparrow collapsed" data-toggle="collapse"
							data-parent="#accordion15" href="#" id="k_final"
							onclick="divN(this)"><b>ADD LANGUAGE</b></a>
					</h4>
				</div>
				<div id="collapse1k" class="panel-collapse collapse">
					<!-- <div class="card-body card-block"> -->
					<div class="card-body card-block" id="total_table">
						<div class="card-body-header">
							<h5></h5>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">* </strong>Authority</label>
									</div>
									<div class="col-md-8">
										<label id="language_authority" class=" form-control-label"></label>
										<!-- <input type="text" id="language_authority" name="language_authority" class="form-control autocomplete" autocomplete="off" maxlength="50"> -->
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">* </strong>Date of Authority</label>
									</div>
									<div class="col-md-8">
										<label id="language_date_of_authority"
											class=" form-control-label"></label>
										<%-- <input type="date" id="language_date_of_authority" name="language_date_of_authority"  max="${date}" min='1899-01-01' class="form-control autocomplete" autocomplete="off" maxlength="10"> --%>
									</div>
								</div>
							</div>
						</div>
						<div class="card-header">
							<h5>Indian Language</h5>
							<h6 class="enter_by">
								<span style="font-size: 12px; color: red;"></span>
							</h6>
						</div>
						<table id="language_table" class="table-bordered "
							style="margin-top: 10px; width: -webkit-fill-available;">
							<thead style="color: white; text-align: center;">
								<tr>
									<th style="width: 2%;">Sr No</th>
									<th style="width: 10%;">Indian Language</th>
									<th style="width: 10%;">Language Standard</th>
									<!-- <th style="width: 10%;">Authority</th>
							<th style="width: 10%;">Date of Authority</th> -->

								</tr>
							</thead>
							<tbody data-spy="scroll" id="langtbody"
								style="min-height: 46px; max-height: 650px; text-align: center;">
								<tr id="tr_lang1">
									<td class="lang_srno" style="width: 2%;">1</td>

									<%-- <td style="width: 10%;">
							 <select  id="language1" name="language1" class="form-control autocomplete"  >
							   <option value="0">--Select--</option>
								<c:forEach var="item" items="${getIndianLanguageList}" varStatus="num">
								<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
								</c:forEach>
								</select>
							</td>
							
							<td style="width: 10%;">
							  <select  id="lang_std1" name="lang_std1" class="form-control autocomplete"  >
							   <option value="0">--Select--</option>
								<c:forEach var="item" items="${getLanguageSTDList}" varStatus="num">
								<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
								</c:forEach>
								</select>
							</td>
							<td style="width: 10%;">
							  <input type="text" id="language_authority1" name="language_authority1" class="form-control autocomplete" autocomplete="off" maxlength="50">
							 </td>
							  <td style="width: 10%;">
							   <input type="date" id="language_date_of_authority1" name="language_date_of_authority1"  max="${date}" class="form-control autocomplete" autocomplete="off" maxlength="10">
							   </td>
						<td style="display:none;"><input type="text" id="lang_ch_id1" name="lang_ch_id1"  value="0" class="form-control autocomplete" autocomplete="off" ></td>
						 --%>
								</tr>
							</tbody>
						</table>
						<div class="card-header">
							<h5>Foregin Language</h5>
							<h6 class="enter_by">
								<span style="font-size: 12px; color: red;"></span>
							</h6>
						</div>
						<table id="foregin_language_table" class="table-bordered"
							style="margin-top: 10px;">
							<thead style="color: white; text-align: center;">
								<tr>
									<th style="width: 2%;">Sr No</th>
									<th style="width: 10%;">Language</th>
									<th style="width: 10%;">Language Standard</th>
									<th style="width: 10%;">Language Profession</th>
									<th style="width: 10%;">Exam Passed</th>

								</tr>
							</thead>
							<tbody data-spy="scroll" id="flangtbody"
								style="min-height: 46px; max-height: 650px; text-align: center;">
								<tr id="tr_flang1">
									<td class="flang_srno" style="width: 2%;">1</td>

									<%-- <td style="width: 10%;">
									 <select  id="flanguage1" name="flanguage1" class="form-control autocomplete"  >
										   <option value="0">--Select--</option>
											<c:forEach var="item" items="${getForiegnLangugeList}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
											</select>
										</td> --%>
									<%-- 	<td style="width: 10%;">
										  <select  id="flang_std1" name="flang_std1" class="form-control autocomplete"  >
										   <option value="0">--Select--</option>
											<c:forEach var="item" items="${getLanguageSTDList}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
											</select>
										</td> --%>
									<%-- 		<td style="width: 10%;">
										   <select  id="lang_prof1" name="lang_prof1" class="form-control autocomplete"  >
										  <option value="0">--Select--</option>
											<c:forEach var="item" items="${getLanguagePROOFList}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										 </select>
										</td> --%>
									<!-- 	<td style="width: 10%;">
										  <input type="text" id="exam_pass1" name="exam_pass1" class="form-control autocomplete" autocomplete="off" maxlength="20">
										</td> -->
									<!-- <td style="display:none;"><input type="text" id="flang_ch_id1" name="flang_ch_id1"  value="0" class="form-control autocomplete" autocomplete="off" ></td> -->
								</tr>
							</tbody>
						</table>
					</div>

					<!-- </div> -->
				</div>
			</div>
		</div>
	</div>
</c:if>
<!-- END LANGUAGE DETAIL -->
<!-- START QUALIFICATION -->
<c:if test="${Qualification.size() != 0}">
	<div class="card">
		<div class="panel-group" id="accordion16">
			<div class="panel panel-default" id="l_div1">
				<div class="panel-heading">
					<h4 class="panel-title main_title lightgreen" id="l_div5">
						<a class="droparrow collapsed" data-toggle="collapse"
							data-parent="#accordion16" href="#" id="l_final"
							onclick="divN(this)"><b>UPDATE QUALIFICATION</b></a>
					</h4>
				</div>
				<div id="collapse1l" class="panel-collapse collapse">
					<!-- 	<div class="card-body card-block"><br> -->
					<div class="card-body card-block" id="total_table">
						<div class="card-body-header">
							<h5></h5>
						</div>
						<div class="row" style="display: none">
							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong>Authority</label>
										</div>
										<div class="col-md-8">
											<input type="text" id="qualification_authority"
												name="qualification_authority"
												class="form-control autocomplete" autocomplete="off"
												maxlength="50" onkeyup="return specialcharecter(this)">
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong>Date of Authority</label>
										</div>
										<div class="col-md-8">
											<input type="text" name="qualification_date_of_authority"
												id="qualification_date_of_authority" maxlength="10"
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
										<div class="col-md-4" align="left">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong>Qualification Type</label>
										</div>
										<div class="col-md-8">
											<select name="quali_type" id="quali_type"
												class=" form-control"
												onchange="fn_qualification_typeChange(this,'quali','quali_degree','specialization')">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getQualificationTypeList}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>

											</select> <input type="hidden" id="qualification_ch_id"
												name="qualification_ch_id" value="0"
												class="form-control autocomplete" autocomplete="off">
										</div>
									</div>

								</div>

								<div class="col-md-6" id="academyQuali">
									<div class="row form-group">
										<div class="col-md-4" align="left">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong>Examination Passed</label>
										</div>
										<div class="col-md-8">
											<select name="quali" id="quali" class=" form-control"
												onchange="fn_ExaminationTypeChange(this,'quali_degree','specialization');fn_other_exam();">
												<option value="0">--Select--</option>

											</select>
										</div>
									</div>

								</div>
							</div>
							<div class="col-md-12">
								<div class="col-md-6" id="other_div_exam" style="display: none;">
									<div class="row form-group">
										<div class="col-md-4" align="left">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong>Examination Other </label>
										</div>
										<div class="col-md-8">
											<input type="text" id="exam_other" name="exam_other"
												class="form-control autocomplete" autocomplete="off">
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-12">


								<div class="col-md-6" id="courseNamediv">
									<div class="row form-group">
										<div class="col-md-4" align="left">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong>Degree Acquired</label>
										</div>
										<div class="col-md-8">
											<select name="quali_degree" id="quali_degree"
												class=" form-control"
												onchange="fn_otherShowHide(this,'other_div_qualiDeg','quali_deg_other')">

												<option value="0">--Select--</option>


											</select>
										</div>
									</div>

								</div>
								<div class="col-md-6" id="specializationDiv">
									<div class="row form-group">
										<div class="col-md-4" align="left">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong>Specialization</label>
										</div>
										<div class="col-md-8">
											<select name="specialization" id="specialization"
												class="form-control"
												onchange="fn_otherShowHide(this,'other_div_qualiSpec','quali_spec_other')">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getSpecializationList}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
									</div>

								</div>

							</div>
							<div class="col-md-12">
								<div class="col-md-6" id="other_div_qualiDeg"
									style="display: none;">
									<div class="row form-group">
										<div class="col-md-4" align="left">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong>Degree Other </label>
										</div>
										<div class="col-md-8">
											<input type="text" id="quali_deg_other"
												name="quali_deg_other" class="form-control autocomplete"
												autocomplete="off">
										</div>
									</div>
								</div>

								<div class="col-md-6" id="other_div_qualiSpec"
									style="display: none;">
									<div class="row form-group">
										<div class="col-md-4" align="left">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong>Specialization Other </label>
										</div>
										<div class="col-md-8">
											<input type="text" id="quali_spec_other"
												name="quali_spec_other" class="form-control autocomplete"
												autocomplete="off">
										</div>
									</div>
								</div>
							</div>

							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4" align="left">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong>Year of Passing</label>
										</div>
										<div class="col-md-8">

											<input type="text" id="yrOfPass" name="yrOfPass"
												class="form-control autocomplete" autocomplete="off"
												onkeypress="return isNumber(event)" maxlength="4" />
										</div>
									</div>

								</div>

								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4" align="left">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong>Div/Grade</label>
										</div>
										<div class="col-md-8">
											<select name="div_class" id="div_class" class=" form-control"
												onchange="fn_other_class();">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getDivclass}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
									</div>

								</div>
							</div>

							<div class="col-md-12" id="other_div_class"
								style="display: none;">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4" align="left">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong>Div/Grade Other </label>
										</div>
										<div class="col-md-8">
											<input type="text" id="class_other" name="class_other"
												class="form-control autocomplete" autocomplete="off">
										</div>
									</div>
								</div>

							</div>

							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4" align="left">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong>Subjects</label>
										</div>
										<div class="col-md-8">
											<div class="multiselect">

												<div class="selectBox" onclick="showCheckboxes()">
													<select name="sub_quali" id="sub_quali"
														class=" form-control">
														<option>Select Subjects</option>
													</select>
													<div class="overSelect"></div>
												</div>
												<div id="checkboxes" class="checkboxes"
													style="max-height: 200px; overflow: auto;">
													<div style="">
														<input type="text" id="quali_sub_search"
															class="form-control autocomplete" autocomplete="off"
															placeholder="Search Subjects">
													</div>
													<div>
														<c:forEach var="item" items="${getSubject}"
															varStatus="num">
															<label for="one" class="quali_subjectlist"> <input
																type="checkbox" name="multisub" value="${item[0]}" />
																${item[1]}
															</label>
														</c:forEach>
													</div>
												</div>

											</div>
										</div>

									</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4" align="left">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong>Institute & Place</label>
										</div>
										<div class="col-md-8">
											<div class="row">
												<input type="text" id="institute_place"
													name="institute_place" class="form-control autocomplete"
													autocomplete="off"
													onkeypress="return onlyAlphabets(event);"
													oninput="this.value = this.value.toUpperCase()">
											</div>

										</div>
									</div>

									<div class="row form-group">
										<div class="col-md-4" align="left">
											<label class=" form-control-label"><strong
												style="color: red;"></strong>Selected Subject</label>
										</div>
										<div class="col-md-8">
											<div class="row">

												<div id="quali_sub_list"
													style="max-height: 200px; overflow: auto; width: 100%; border: 1px solid;">

												</div>

											</div>

										</div>
									</div>





								</div>


							</div>



						</div>
						<!-- 			<div class="card-footer" align="center" class="form-control"> -->
						<!-- 					 <input type="button" class="btn btn-primary btn-sm" value="Submit for Approval" onclick="qualification_save_fn();"> -->
						<!-- 			</div> -->
						<table id="qualificationtable"
							class="table no-margin table-striped  table-hover  table-bordered report_print">
							<thead>
								<tr>
									<th style="text-align: center; width: 10%;">Ser No</th>
									<th style="text-align: center;">Authority</th>
									<th style="text-align: center;">Date of Authority</th>
									<th style="text-align: center;">Qualification Type</th>
									<th style="text-align: center;">Examination Passed</th>
									<th style="text-align: center;">Degree Acquired</th>
									<th style="text-align: center;">Specialization</th>
									<th style="text-align: center;">Year of Passing</th>
									<th style="text-align: center;">Subjects</th>
									<th style="text-align: center;">Div/Grade</th>
									<th style="text-align: center;">Institute & Place</th>

								</tr>
							</thead>
							<tbody id="qualificationtbody">

							</tbody>
						</table>
					</div>

					<!-- </div> -->
				</div>
			</div>
		</div>
	</div>
</c:if>
<!-- END QUALIFICATION -->

<!-- START Promotional Exam -->


<!-- START Army Course -->

<!-- END Army Course -->
<!-- START BPET -->
<c:if test="${BEPT.size() != 0}">
	<div class="card">
		<div class="panel-group" id="accordion42">
			<div class="panel panel-default" id="p_div1">
				<div class="panel-heading">
					<h4 class="panel-title main_title yellow" id="p_div5">
						<a class="droparrow collapsed" data-toggle="collapse"
							data-parent="#accordion42" href="#" id="p_final"
							onclick="divN(this)"><b>UPDATE BPET DETAILS</b></a>
					</h4>
				</div>
				<div id="collapse1p" class="panel-collapse collapse">
					<!-- <div class="card-body card-block"> -->
					<div class="card-body card-block" id="total_table">
						<div class="card-body-header">
							<h5></h5>
						</div>
						<table id="bpet_table" class="table-bordered "
							style="margin-top: 10px; width: 100%;">

							<thead style="color: white; text-align: center;">
								<tr>
									<th style="width: 2%;">Sr No</th>
									<th style="width: 20%;">BPET RESULT</th>
									<th style="width: 20%;">BPET QE</th>
									<th style="width: 20%;">Year</th>
									<th style="width: 20%;">Conducted at Unit</th>

								</tr>
							</thead>
							<tbody data-spy="scroll" id="bpettbody"
								style="min-height: 46px; max-height: 650px; text-align: center;">

							</tbody>
						</table>
					</div>

					<!-- </div> -->

				</div>
			</div>
		</div>
	</div>
</c:if>
<!-- END BPET -->
<!-- STRAT FIRING STANDARD -->
<c:if test="${FiringStan.size() != 0}">
	<div class="card">
		<div class="panel-group" id="accordion19">
			<div class="panel panel-default" id="q_div1">
				<div class="panel-heading">
					<h4 class="panel-title main_title yellow" id="q_div5">
						<a class="droparrow collapsed" data-toggle="collapse"
							data-parent="#accordion19" href="#" id="q_final"
							onclick="divN(this)"><b>UPDATE FIRING STANDARD</b></a>
					</h4>
				</div>
				<div id="collapse1q" class="panel-collapse collapse">
					<!-- <div class="card-body card-block"> -->
					<div class="card-body card-block" id="total_table">
						<div class="card-body-header">
							<h5></h5>
						</div>
						<table id="fire_std_table" class="table-bordered "
							style="margin-top: 10px; width: 100%;">

							<thead style="color: white; text-align: center;">
								<tr>
									<th style="width: 2%;">Sr No</th>
									<th style="width: 20%;">Firing Grade</th>
									<th style="width: 20%;">FIRING EVENT QE</th>
									<th style="width: 20%;">Year</th>
									<th style="width: 20%;">Condaucted at Unit</th>
								</tr>
							</thead>
							<tbody data-spy="scroll" id="fire_stdtbody"
								style="min-height: 46px; max-height: 650px; text-align: center;">

							</tbody>
						</table>
					</div>
					<!-- </div> -->



				</div>
			</div>
		</div>
	</div>
</c:if>
<!-- END FIRING STANDARD -->

<!-- START Medical -->
<c:if test="${MedDetails.size() != 0}">
	<form id="madical_category_form">
		<div class="card">
			<div class="panel-group" id="accordion32">
				<div class="panel panel-default" id="s_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title lightred" id="s_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion32" href="#" id="s_final"
								onclick="divN(this)"><b>CHANGE IN MEDICAL</b></a>
						</h4>
					</div>
					<div id="collapse1s" class="panel-collapse collapse">
						<div class="card-body card-block">
							<br>
							<div class="row">
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-6">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong>Authority</label>
											</div>
											<div class="col-md-6">

												<label id="madical_authority" name="madical_authority"></label>
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
												<label id="madical_date_of_authority"
													name="madical_date_of_authority"></label>

											</div>
										</div>
									</div>
								</div>
								<input type="hidden" id="mad_classification_count"
									name="mad_classification_count" value="NIL">
								<div class="col-md-12">
									<div class="row form-group">
										<div class="col-md-12" style="font-size: 15px; margin: 10px;">
											<input type="checkbox" id="check_1bx" name="check_1bx"
												onclick="oncheck_1bx()" value="1BX" disabled="disabled">
											<label for="text-input" class=" form-control-label"
												style="margin-left: 10px;"> SHAPE 1BX </label>
										</div>
									</div>
									<input type="hidden" id="shape_1bx_id" name="shape_1bx_id"
										value="0" class="form-control autocomplete" autocomplete="off">
								</div>
								<div class="col-md-12" id="shape1bxdiv" style="display: none;">

									<div class="col-md-4">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong>From Date</label>
											</div>
											<div class="col-md-8">
												<label id="1bx_from_date" name="1bx_from_date"></label>

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
												<label id="1bx_to_date" name="1bx_to_date"></label>
											</div>
										</div>
									</div>
									<div class="col-md-4">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong>Diagnosis</label>
											</div>
											<div class="col-md-8">
												<label name="1bx_diagnosis_code" id="1bx_diagnosis_code"></label>
											</div>
										</div>
									</div>
								</div>

								<div id="shapediv" class="watermarked"
									style="width: -webkit-fill-available;">

									<div class="card-header-inner"
										style="margin-left: 20px; margin-bottom: 20px;">
										<strong>S - Psychological & Congnitive</strong>
									</div>
									<div class="">
										<table id="s_madtable" class="table-bordered"
											style="margin-top: 10px; width: 100%;">

											<thead style="color: white; text-align: center;">
												<tr>
													<th style="width: 2%;">Sr No</th>
													<th style="">Status LMC</th>
													<th style="">Value</th>
													<th style="">From Date</th>
													<th style="">To Date</th>
													<th style="display: none;" class="diagnosisClass1">Diagnosis</th>


												</tr>
											</thead>
											<tbody data-spy="scroll" id="s_madtbody"
												style="min-height: 46px; max-height: 650px; text-align: center;">

											</tbody>
										</table>
									</div>

									<br />

									<div class="card-header-inner"
										style="margin-left: 20px; margin-bottom: 20px;">
										<strong>H - Hearing</strong>
									</div>
									<div>
										<table id="h_madtable" class="table-bordered "
											style="margin-top: 10px; width: 100%;">

											<thead style="color: white; text-align: center;">
												<tr>
													<th style="width: 2%;">Sr No</th>
													<th style="">Status LMC</th>
													<th style="">Value</th>
													<th style="">From Date</th>
													<th style="">To Date</th>
													<th style="display: none;" class="diagnosisClass2">Diagnosis</th>


												</tr>
											</thead>
											<tbody data-spy="scroll" id="h_madtbody"
												style="min-height: 46px; max-height: 650px; text-align: center;">

											</tbody>
										</table>
									</div>


									<br />
									<div class="card-header-inner"
										style="margin-left: 20px; margin-bottom: 20px;">
										<strong>A - Appendages</strong>
									</div>
									<div>
										<table id="a_madtable" class="table-bordered "
											style="margin-top: 10px; width: 100%;">

											<thead style="color: white; text-align: center;">
												<tr>
													<th style="width: 2%;">Sr No</th>
													<th style="">Status LMC</th>
													<th style="">Value</th>
													<th style="">From Date</th>
													<th style="">To Date</th>
													<th style="display: none;" class="diagnosisClass3">Diagnosis</th>

												</tr>
											</thead>
											<tbody data-spy="scroll" id="a_madtbody"
												style="min-height: 46px; max-height: 650px; text-align: center;">

											</tbody>
										</table>
									</div>


									<br />
									<div class="card-header-inner"
										style="margin-left: 20px; margin-bottom: 20px;">
										<strong>P - Physical Capacity</strong>
									</div>
									<div>
										<table id="p_madtable" class="table-bordered "
											style="margin-top: 10px; width: 100%;">

											<thead style="color: white; text-align: center;">
												<tr>
													<th style="width: 2%;">Sr No</th>
													<th style="">Status LMC</th>
													<th style="">Value</th>
													<th style="">From Date</th>
													<th style="">To Date</th>
													<th style="display: none;" class="diagnosisClass4">Diagnosis</th>

												</tr>
											</thead>
											<tbody data-spy="scroll" id="p_madtbody"
												style="min-height: 46px; max-height: 650px; text-align: center;">

											</tbody>
										</table>
									</div>

									<br />

									<div class="card-header-inner"
										style="margin-left: 20px; margin-bottom: 20px;">
										<strong>E - Eye Sight</strong>
									</div>
									<div>
										<table id="e_madtable" class="table-bordered "
											style="margin-top: 10px; width: 100%;">

											<thead style="color: white; text-align: center;">
												<tr>
													<th style="width: 2%;">Sr No</th>
													<th style="">Status LMC</th>
													<th style="">Value</th>
													<th style="">From Date</th>
													<th style="">To Date</th>
													<th style="display: none;" class="diagnosisClass5">Diagnosis</th>

												</tr>
											</thead>
											<tbody data-spy="scroll" id="e_madtbody"
												style="min-height: 46px; max-height: 650px; text-align: center;">

											</tbody>
										</table>
									</div>


									<br />

									<div class="card-header-inner"
										style="margin-left: 20px; margin-bottom: 20px;">
										<strong>C - Climate and Terrain Restrictions</strong>
									</div>
									<div style="width: -webkit-fill-available;">
										<table id="c_cmadtable" class="table-bordered "
											style="margin-top: 10px; width: 100%;">
											<thead style="color: white; text-align: center;">
												<tr>
													<th style="width: 2%;">Sr No</th>
													<th style="">Value & Description</th>
													<th style="display: none;" class="cCopClass">Other</th>

												</tr>
											</thead>
											<tbody data-spy="scroll" id="c_cmadtbody"
												style="min-height: 46px; max-height: 650px; text-align: center;">

											</tbody>
										</table>
									</div>
									<br />

									<div class="card-header-inner"
										style="margin-left: 20px; margin-bottom: 20px;">
										<strong>O - Degree of Medical Observation Required</strong>
									</div>
									<div style="width: -webkit-fill-available;">
										<table id="c_omadtable" class="table-bordered "
											style="margin-top: 10px; width: 100%;">

											<thead style="color: white; text-align: center;">
												<tr>
													<th style="width: 2%;">Sr No</th>
													<th style="">Value & Description</th>

												</tr>
											</thead>
											<tbody data-spy="scroll" id="c_omadtbody"
												style="min-height: 46px; max-height: 650px; text-align: center;">

											</tbody>
										</table>
									</div>


									<br />
									<div class="card-header-inner"
										style="margin-left: 20px; margin-bottom: 20px;">
										<strong>P - Physical Capability Limitations</strong>
									</div>
									<div style="width: -webkit-fill-available;">
										<table id="c_pmadtable" class="table-bordered "
											style="margin-top: 10px; width: 100%;">

											<thead style="color: white; text-align: center;">
												<tr>
													<th style="width: 2%;">Sr No</th>
													<th style="">Value & Description</th>

												</tr>
											</thead>
											<tbody data-spy="scroll" id="c_pmadtbody"
												style="min-height: 46px; max-height: 650px; text-align: center;">

											</tbody>
										</table>
									</div>


									<br />
									<div class="card-header-inner"
										style="margin-left: 20px; margin-bottom: 20px;">
										<strong>E - Exclusive Limitations</strong>
									</div>
									<div style="width: -webkit-fill-available;">
										<table id="c_emadtable" class="table-bordered "
											style="margin-top: 10px; width: 100%;">

											<thead style="color: white; text-align: center;">
												<tr>
													<th style="width: 2%;">Sr No</th>
													<th style="">Value & Description</th>
													<th style="display: none;" class="eCopClass">SubValue</th>
													<th style="display: none;" class="eCop_otherClass">Other</th>

												</tr>
											</thead>
											<tbody data-spy="scroll" id="c_emadtbody"
												style="min-height: 46px; max-height: 650px; text-align: center;">

											</tbody>
										</table>
									</div>
								</div>

							</div>


						</div>
					</div>
				</div>
			</div>
		</div>



	</form>
</c:if>
<!-- END Medical -->




<!-- START FOREIGN COUNTRY -->
<c:if test="${ForeignCountry.size() != 0}">
	<div class="card">
		<div class="panel-group" id="accordion18">
			<div class="panel panel-default" id="t_div1">
				<div class="panel-heading">
					<h4 class="panel-title main_title lightred" id="t_div5">
						<a class="droparrow collapsed" data-toggle="collapse"
							data-parent="#accordion18" href="#" id="t_final"
							onclick="divN(this)"><b>UPDATE VISIT TO FOREIGN
								COUNTRY</b></a>
					</h4>
				</div>
				<div id="collapse1t" class="panel-collapse collapse">
					<!-- 	<div class="card-body card-block"> -->
					<div class="card-body card-block" id="total_table">
						<div class="card-body-header">
							<h5></h5>
						</div>
						<table id="foreign_country_visit" class="table-bordered "
							style="margin-top: 10px;">
							<thead style="color: white; text-align: center;">
								<tr>
									<th style="width: 2%;">Sr No</th>
									<th style="width: 10%;">Country Visited</th>
									<th style="width: 10%;">Country Visited Other</th>
									<th style="width: 10%;">From</th>
									<th style="width: 10%;">To</th>
									<th style="width: 10%;">Duration</th>
									<th style="width: 10%;">Purpose of Visit</th>
									<th style="width: 10%;">Purpose of Visit Other</th>

								</tr>
							</thead>
							<tbody data-spy="scroll" id="foregin_Country_tbody"
								style="min-height: 46px; max-height: 650px; text-align: center;">
								<tr id="tr_foregin_country1">
									<td class="fcon_srno" style="width: 2%;">1</td>

									<%-- 	<td style="width: 10%;">
										    <select name="country1" id="country1" class="form-control-sm form-control"   >
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${getForeign_country}" varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
														</c:forEach>
											</select> 
										</td>
									     <td style="width: 10%;">
										 <input type="date" id="from_dt1" name="from_dt1" class="form-control autocomplete" autocomplete="off" >
										</td>
										
										<td style="width: 5%;">
										 <input type="date" id="to_dt1" name="to_dt1" class="form-control autocomplete" autocomplete="off" > 
										</td>
										
										<td style="width: 10%;">
										  <input type="text" id="period1" name="period1" class="form-control autocomplete" autocomplete="off" >
										</td>
										
									
										
										<td style="width: 5%;">
										<!-- <input type="text" id="purpose_visit1" name="purpose_visit1" class="form-control autocomplete" autocomplete="off" > --> 
											<select name="purpose_visit1" id="purpose_visit1" class="form-control-sm form-control"   >
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${getForiegnCountryList}" varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
														</c:forEach>
											</select> 
										</td>
									
									<td style="display:none;"><input type="text" id="foregin_country_ch_id1" name="foregin_country_ch_id1"  value="0" class="form-control autocomplete" autocomplete="off" ></td> --%>
								</tr>
							</tbody>
						</table>
					</div>


					<!-- </div> -->

				</div>
			</div>
		</div>
	</div>
</c:if>
<!-- END FOREIGN COUNTRY -->
<!-- START AWARD & MEDAL -->
<c:if test="${AwardsMedal.size() != 0}">
	<div class="card">
		<div class="panel-group" id="accordion14">
			<div class="panel panel-default" id="u_div1">
				<div class="panel-heading">
					<h4 class="panel-title main_title lightred" id="u_div5">
						<a class="droparrow collapsed" data-toggle="collapse"
							data-parent="#accordion14" href="#" id="u_final"
							onclick="divN(this)"><b>UPDATE
								AWARDS & MEDAL</b></a>
					</h4>
				</div>
				<div id="collapse1u" class="panel-collapse collapse">
					<!-- <div class="card-body card-block"> -->
					<div class="card-body card-block" id="total_table">
						<div class="card-body-header">
							<h5></h5>
						</div>
						<table id="awardsNmedal_table" class="table-bordered "
							style="margin-top: 10px;">
							<thead style="color: white; text-align: center;">
								<tr>
									<th style="width: 2%;">Sr No</th>
									<th style="width: 10%;">Authority</th>
									<th style="width: 10%;">Date of Authority</th>
									<th style="width: 10%;">Category</th>
									<th style="width: 10%;">Award/Medal</th>
									<th style="width: 10%;">Date of Award</th>
									<th style="width: 10%;">Unit</th>
									<th style="width: 10%;">BDE</th>
									<th style="width: 10%;">DIV/Sub Area</th>
									<th style="width: 10%;">Corps/Area</th>
									<th style="width: 10%;">Command</th>


								</tr>
							</thead>
							<tbody data-spy="scroll" id="awardsNmedal_tbody"
								style="min-height: 46px; max-height: 650px; text-align: center;">
								<tr id="tr_awardsNmedal">
									<td class="anm_srno" style="width: 2%;">1</td>
									<%--   <td style="width: 10%;">
                                                                   <select name="Category_81" id="Category_81" onchange="getMedalDescList(this)" class="form-control-sm form-control">
                                                                                   <option value="0">--Select--</option>
                                                                                   <c:forEach var="item" items="${getMedalList}" varStatus="num">
                                                                                           <option value="${item[0]}" name="${item[1]}">${item[1]}</option>
                                                                                   </c:forEach>
                                                                   </select>
                                                                   </td>
                                                                   <td style="width: 10%;">
                                                                   <select name="select_desc1" id="select_desc1" class="form-control-sm form-control">
                                                                                   <option value="0">--Select--</option>
                                                                   </select>
                                                                   </td>
                                                                   <td style="width: 10%;">
                                                              <input type="date" id="date_of_award1" name="date_of_award1" class="form-control autocomplete" autocomplete="off" maxlength="10"  max="${date}">
                                                              </td>
                                                              <td style="width: 10%;">
                                                             <input type="text" id="awardsNmedal_unit1" name="awardsNmedal_unit1" class="form-control autocomplete" onkeypress="unitData(this)" autocomplete="off" maxlength="255">
                                                           </td>
                                                           <td style="width: 10%;">
                                                           <select name="awardsNmedal_bde1" id="awardsNmedal_bde1" class="form-control-sm form-control">
                                                                                   <option value="0">--Select--</option>
                                                                                   <c:forEach var="item" items="${getPSG_BdeList}" varStatus="num">
                                                                                           <option value="${item[0]}" name="${item[1]}">${item[1]}</option>
                                                                                   </c:forEach>
                                                                   </select>
                                                           </td>
                                                            <td style="width: 10%;">
                                                           <select name="awardsNmedal_div_subarea1" id="awardsNmedal_div_subarea1"  class="form-control-sm form-control">
                                                                                   <option value="0">--Select--</option>
                                                                                   <c:forEach var="item" items="${getPSG_DivList}" varStatus="num">
                                                                                           <option value="${item[0]}" name="${item[1]}">${item[1]}</option>
                                                                                   </c:forEach>
                                                                   </select>
                                                           </td>
                                                              <td style="width: 10%;">
                                                              
                                                           <select name="awardsNmedal_corps_area1" id="awardsNmedal_corps_area1"  class="form-control-sm form-control">
                                                                                   <option value="0">--Select--</option>
                                                                                   <c:forEach var="item" items="${getPSG_CorpsList}" varStatus="num">
                                                                                           <option value="${item[0]}" name="${item[1]}">${item[1]}</option>
                                                                                   </c:forEach>
                                                                   </select>
                                                           </td>
                                                           <td style="width: 10%;">
                                                           <select name="awardsNmedal_command1" id="awardsNmedal_command1"  class="form-control-sm form-control">
                                                                                   <option value="0">--Select--</option>
                                                                                   <c:forEach var="item" items="${getPSG_CommandList}" varStatus="num">
                                                                                           <option value="${item[0]}" name="${item[1]}">${item[1]}</option>
                                                                                   </c:forEach>
                                                                   </select>
                                                           </td>
                                                           <td style="width: 10%;">
                                                             <input type="text" id="awardsNmedal_authority1" name="awardsNmedal_authority1" class="form-control autocomplete" autocomplete="off" maxlength="50">
                                                            </td>
                                                             <td style="width: 10%;">
                                                              <input type="date" id="awardsNmedal_date_of_authority1" name="awardsNmedal_date_of_authority1" class="form-control autocomplete" autocomplete="off"  max="${date}" maxlength="10">
                                                              </td>
                                                           <td style="display:none;"><input type="text" id="awardsNmedal_ch_id1" name="awardsNmedal_ch_id1"  value="0" class="form-control autocomplete" autocomplete="off" ></td> --%>

								</tr>
							</tbody>
						</table>
					</div>

					<!-- </div> -->
				</div>
			</div>
		</div>
	</div>
</c:if>

<!-- END AWARD & MEDAL -->
<!-- START BTLE & PHY -->
<c:if test="${btel_cas.size() != 0}">
	<form id="form_battle_physical_casuality">
		<div class="card">
			<div class="panel-group" id="accordion13">
				<div class="panel panel-default" id="v_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title lightred" id="v_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion13" href="#" id="v_final"
								onclick="divN(this)"><b>UPDATE BATTLE &
									PHYSICAL CASUALTY</b></a>
						</h4>
					</div>
					<div id="collapse1v" class="panel-collapse collapse">
						<div class="card-body card-block">

							<br>

							<div class="row">

								<div class="col-md-12">

									<div class="col-md-6">

										<div class="row form-group">

											<div class="col-md-4">

												<label class=" form-control-label">Authority(Unit
													Letter/Medical Auth)</label>

											</div>

											<div class="col-md-8">

												<input type="hidden" id="batnpc_ch_id" name="batnpc_ch_id"
													value="0" class="form-control autocomplete"
													autocomplete="off"> <label id="batnpc_authority"
													class=" form-control-label"></label> <input type="hidden"
													id="batnpc_authority" name="batnpc_authority"
													class="form-control autocomplete" autocomplete="off">



											</div>

										</div>

									</div>

									<div class="col-md-6">

										<div class="row form-group">

											<div class="col-md-4">

												<label class=" form-control-label">Date of Authority</label>

											</div>

											<div class="col-md-8">

												<label id="batnpc_date_of_authority"
													class=" form-control-label"></label> <input type="hidden"
													id="batnpc_date_of_authority" max="${date}"
													name="batnpc_date_of_authority"
													class="form-control autocomplete" autocomplete="off">

											</div>

										</div>

									</div>

								</div>

								<div class="card-header-inner"
									style="margin-left: 20px; margin-bottom: 20px;">
									<strong>Casualty Details</strong>
								</div>

								<div class="col-md-12">

									<div class="col-md-6">

										<div class="row form-group">

											<div class="col-md-4">

												<label class=" form-control-label">Date of Casualty</label>

											</div>

											<div class="col-md-8">

												<label id="date_of_casuality" class=" form-control-label"></label>

												<input type="hidden" id="date_of_casuality" max="${date}"
													name="date_of_casuality" class="form-control autocomplete"
													autocomplete="off">

											</div>

										</div>

									</div>

									<div class="col-md-6">

										<div class="row form-group">

											<div class="col-md-4">

												<label class=" form-control-label">Time of Casualty</label>

											</div>

											<div class="col-md-8">

												<label id="time_of_casuality" class=" form-control-label"></label>

											</div>

										</div>

									</div>

								</div>

								<div class="col-md-12">

									<div class="col-md-6">

										<div class="row form-group">

											<div class="col-md-4">

												<label class=" form-control-label">Recommended For</label>

											</div>

											<div class="col-md-8">

												<label id="classification_of_casuality"></label> <input
													type="hidden" id="classification_of_casuality_hid"
													name="classification_of_casuality_hid"
													onchange="Casuality_radioApproval();">

												<!--  <input type="radio" id="classification_of_casuality1" name="classification_of_casuality" value="physical_casuality" onchange="Casuality_radio();">

								  <label for="physical_casuality">Physical Casualty</label>

									<input type="radio" id="classification_of_casuality2" name="classification_of_casuality" value="battle_casuality" onchange="Casuality_radio();">

									<label for="battle_casuality">Battle Casualty</label><br>  -->

											</div>

										</div>

									</div>

									<div class="col-md-6">

										<div class="row form-group">

											<div class="col-md-4">

												<label class=" form-control-label">Nature of
													Casualty</label>

											</div>

											<div class="col-md-8">

												<label id="nature_of_casuality"></label>



												<!-- <input type="radio" id="nature_of_casuality1" name="nature_of_casuality" value="Killed">

								  <label for="Killed">Killed</label>

									<input type="radio" id="nature_of_casuality2" name="nature_of_casuality" value="Died" >

									<label for="Died">Died</label>

									<input type="radio" id="nature_of_casuality3" name="nature_of_casuality" value="Wounded" >

									<label for="Wounded">Wounded</label>

									<input type="radio" id="nature_of_casuality4" name="nature_of_casuality" value="Missing" >

									<label for="Missing">Missing</label> -->

											</div>

										</div>

									</div>

								</div>

								<div class="col-md-12">

									<div class="col-md-6"></div>

									<div class="col-md-6" id="missingdiv" style="display: none">

										<div class="row form-group">

											<div class="col-md-4">

												<label class=" form-control-label">If Missing</label>

											</div>

											<div class="col-md-8">

												<label id="missing_desclb" class=" form-control-label"></label>

												<select name="missing_desc" id="missing_desc"
													style="display: none" class="form-control">

													<option value="0">--Select--</option>

													<c:forEach var="item" items="${getMissingList}"
														varStatus="num">

														<option value="${item[1]}" name="${item[0]}">${item[0]}</option>

													</c:forEach>

												</select>

											</div>

										</div>

									</div>

								</div>

								<div class="card-header-inner"
									style="margin-left: 20px; margin-bottom: 20px;">
									<strong>Location Where The Casualty Occurred</strong>
								</div>

								<div class="col-md-12">

									<div class="col-md-6">

										<div class="row form-group">

											<div class="col-md-4">

												<label class=" form-control-label">On duty or
													otherwise (Specify)</label>

											</div>

											<div class="col-md-8">

												<label id="onduty" class=" form-control-label"></label>

											</div>

										</div>

									</div>

									<div class="col-md-6" id="onduityotherdiv"
										style="display: none;">

										<div class="row form-group">

											<div class="col-md-4">

												<label class=" form-control-label">Others</label>

											</div>

											<div class="col-md-8">

												<label id="onduityother" class=" form-control-label"></label>

											</div>

										</div>

									</div>

								</div>

								<div class="col-md-12">

									<div class="col-md-6">

										<div class="row form-group">

											<div class="col-md-4">

												<label class=" form-control-label">COUNTRY</label>

											</div>

											<div class="col-md-8">

												<label id="batnpc_country" class=" form-control-label"></label>

												<select name="batnpc_country_val" id="batnpc_country_val"
													style="display: none;" class="form-control"
													onchange="onCountryChangeApprove(this)">

													<option value="0">--Select--</option>

													<c:forEach var="item" items="${getMedCountryName}"
														varStatus="num">

														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>

													</c:forEach>

												</select>

											</div>

										</div>

									</div>

									<div class="col-md-6" id="btnpc_statediv">

										<div class="row form-group">

											<div class="col-md-4">

												<label class=" form-control-label">STATE/UT</label>

											</div>

											<div class="col-md-8">

												<label id="batnpc_state" class=" form-control-label"></label>

												<select name="batnpc_state" id="batnpc_state_val"
													style="display: none;" class="form-control"
													onchange="onStateChangeApprove(this)">

													<option value="0">--Select--</option>



												</select>

											</div>

										</div>

									</div>



									<div class="col-md-6" id="otherindcontydiv1"
										style="display: none;">

										<div class="row form-group">

											<div class="col-md-4">

												<label class=" form-control-label">Name of Peace
													Keeping Mission / Trg Team / Expedition</label>

											</div>

											<div class="col-md-8">

												<label id="mission_expedition" class=" form-control-label"></label>

											</div>

										</div>

									</div>

								</div>

								<div class="col-md-12" id="btnpc_dist_tehsildiv">

									<div class="col-md-6">

										<div class="row form-group">

											<div class="col-md-4">

												<label class=" form-control-label">DISTRICT</label>

											</div>

											<div class="col-md-8">

												<label id="batnpc_district" class=" form-control-label"></label>

												<select name="batnpc_district" id="batnpc_district_val"
													style="display: none;" class="form-control"
													onchange="onDistrictChangeApprove(this)">

													<option value="0">--Select--</option>



												</select>

											</div>

										</div>

									</div>

									<div class="col-md-6">

										<div class="row form-group">

											<div class="col-md-4">

												<label class=" form-control-label">TEHSIL</label>

											</div>

											<div class="col-md-8">

												<label id="batnpc_tehsil" class=" form-control-label"></label>

												<select name="batnpc_tehsil" id="batnpc_tehsil_val"
													style="display: none;" class="form-control"
													onchange="onTehsilChangeApprove(this)">

													<option value="0">--Select--</option>



												</select>

											</div>

										</div>

									</div>

								</div>

								<div class="col-md-12" id="btnpc_vill_pindiv">

									<div class="col-md-6">

										<div class="row form-group">

											<div class="col-md-4">

												<label class=" form-control-label">Village/Town/City</label>

											</div>

											<div class="col-md-8">

												<label id="batnpc_village" class=" form-control-label"></label>

												<!-- <select name="batnpc_village" id="batnpc_village_val"

														class="form-control" style="display: none;">

														<option value="0">--Select--</option>

													</select> -->

											</div>

										</div>

									</div>

									<div class="col-md-6">

										<div class="row form-group">

											<div class="col-md-4">

												<label class=" form-control-label">Pin</label>

											</div>

											<div class="col-md-8">

												<label id="batnpc_pin" class=" form-control-label"></label>

												<input type="hidden" id="batnpc_pin" pattern="^[0-9]*$"
													name="batnpc_pin" class="form-control autocomplete"
													autocomplete="off" maxlength="6"
													onkeypress="return isNumber(event)">

											</div>

										</div>

									</div>

								</div>

								<div class="col-md-12" id="btnpc_placediv">

									<div class="col-md-6">

										<div class="row form-group">

											<div class="col-md-4">

												<label class=" form-control-label">Exact
													Place/Area/Post</label>

											</div>

											<div class="col-md-8">

												<label id="batnpc_exact_place" class=" form-control-label"></label>

												<input type="hidden" id="batnpc_exact_place"
													autocomplete="off" name="batnpc_exact_place"
													class="form-control autocomplete">

											</div>

										</div>

									</div>

								</div>

								<div class="col-md-12" id="otherindcontydiv2"
									style="display: none;">

									<div class="col-md-6">

										<div class="row form-group">

											<div class="col-md-4">

												<label class=" form-control-label">Name of Area
													/Post /Village /Town /Hospital </label>

											</div>

											<div class="col-md-8">

												<label id="area_post_town" class=" form-control-label"></label>

											</div>

										</div>

									</div>

									<div class="col-md-6">

										<div class="row form-group">

											<div class="col-md-4">

												<label id="batnpc_exact_place" class=" form-control-label"></label>

												<label class=" form-control-label">Sector / Bde</label>

											</div>

											<div class="col-md-8">

												<label id="Sector_bde" class=" form-control-label"></label>

											</div>

										</div>

									</div>

								</div>



								<div class="col-md-12">

									<div class="col-md-6">

										<div class="row form-group">

											<div class="col-md-4">

												<label class=" form-control-label">Name of Operation</label>

											</div>

											<div class="col-md-8">

												<label id="name_of_operation" class=" form-control-label"></label>

												<input type="hidden" id="name_of_operation"
													name="name_of_operation" class="form-control autocomplete"
													onkeypress="getNameOfOperation(this);" autocomplete="off">

											</div>

										</div>

									</div>

									<div class="col-md-6">

										<div class="row form-group">

											<div class="col-md-4">

												<label class=" form-control-label">Sector</label>

											</div>

											<div class="col-md-8">

												<label id="btnpc_sector" class=" form-control-label"></label>

											</div>

										</div>

									</div>

								</div>



								<div class="col-md-12">

									<div class="col-md-6">

										<div class="row form-group">

											<div class="col-md-4">

												<label class=" form-control-label">Field Area</label>

											</div>

											<div class="col-md-8">

												<label id="field_services"></label>

											</div>

										</div>

									</div>

									<div class="col-md-6">

										<div class="row form-group">

											<div class="col-md-4">

												<label class=" form-control-label">Whether on</label>

											</div>

											<div class="col-md-8">

												<label id="whether_on"></label>

											</div>

										</div>

									</div>

								</div>

								<div class="col-md-12">

									<div class="col-md-6">

										<div class="row form-group">

											<div class="col-md-4">

												<label class=" form-control-label">BDE</label>

											</div>

											<div class="col-md-8">

												<label id="batnpc_bde" class=" form-control-label"></label>

												<select name="batnpc_bde" id="batnpc_bde_val"
													style="display: none;" class="form-control">

													<option value="0">--Select--</option>

													<c:forEach var="item" items="${getPSG_BdeList}"
														varStatus="num">

														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>

													</c:forEach>

												</select>

											</div>

										</div>

									</div>

									<div class="col-md-6">

										<div class="row form-group">

											<div class="col-md-4">

												<label class=" form-control-label">DIV/Sub Area</label>

											</div>

											<div class="col-md-8">

												<label id="batnpc_div_subarea" class=" form-control-label"></label>

												<select name="batnpc_div_subarea"
													id="batnpc_div_subarea_val" class="form-control"
													style="display: none;">

													<option value="0">--Select--</option>

													<c:forEach var="item" items="${getPSG_DivList}"
														varStatus="num">

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

												<label class=" form-control-label">Corps/Area</label>

											</div>

											<div class="col-md-8">

												<label id="batnpc_corps_area" class=" form-control-label"></label>

												<select name="batnpc_corps_area" id="batnpc_corps_area_val"
													class="form-control" style="display: none;">

													<option value="0">--Select--</option>

													<c:forEach var="item" items="${getPSG_CorpsList}"
														varStatus="num">

														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>

													</c:forEach>

												</select>

											</div>

										</div>

									</div>



									<div class="col-md-6">

										<div class="row form-group">

											<div class="col-md-4">

												<label class=" form-control-label">Command</label>

											</div>

											<div class="col-md-8">

												<label id="batnpc_command" class=" form-control-label"></label>

												<select name="batnpc_command" id="batnpc_command_val"
													class="form-control" style="display: none;">

													<option value="0">--Select--</option>

													<c:forEach var="item" items="${getPSG_CommandList}"
														varStatus="num">

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

												<label class=" form-control-label">Hospital Name</label>

											</div>

											<div class="col-md-8">

												<label id="hospital_name" class=" form-control-label"></label>

											</div>

										</div>

									</div>

									<div class="col-md-6">

										<div class="row form-group">

											<div class="col-md-4">

												<label class=" form-control-label">Hospital Location</label>

											</div>

											<div class="col-md-8">

												<label id="hospital_location" class=" form-control-label"></label>

											</div>

										</div>

									</div>

								</div>

								<div class="col-md-12">

									<div class="col-md-6">

										<div class="row form-group">

											<div class="col-md-4">

												<label class=" form-control-label">Cause of Casualty</label>

											</div>

											<div class="col-md-7">

												<label id="cause_of_casuality_1lb"
													class=" form-control-label"></label> <select
													name="cause_of_casuality_1" id="cause_of_casuality_1"
													class="form-control" onchange="onCauses1()"
													style="display: none">

													<option value="0">--Select--</option>

													<c:forEach var="item" items="${getCausesOfCasuality}"
														varStatus="num">

														<option value="${item[1]}" name="${item[0]}">${item[0]}</option>

													</c:forEach>

												</select>

											</div>

											<div class="col-md-1" align="center" style="display: none"
												id="cause_of_casuality_2arrow">==></div>

										</div>

									</div>

									<div class="col-md-6">

										<div class="row form-group">



											<div class="col-md-4">

												<label id="cause_of_casuality_2lb"
													class=" form-control-label"></label> <select
													name="cause_of_casuality_2" id="cause_of_casuality_2"
													class="form-control" onchange="onCauses2()"
													style="display: none">

													<option value="0">--Select--</option>

												</select>

											</div>

											<div class="col-md-1" align="center" style="display: none"
												id="cause_of_casuality_3arrow">==></div>

											<div class="col-md-4">

												<label id="cause_of_casuality_3lb"
													class=" form-control-label"></label> <select
													name="cause_of_casuality_3" id="cause_of_casuality_3"
													class="form-control" style="display: none">

													<option value="0">--Select--</option>

												</select>

											</div>

										</div>

									</div>



								</div>



								<div class="col-md-12">

									<div class="col-md-6">

										<div class="row form-group">

											<div class="col-md-4">

												<label class=" form-control-label">Brief decription
													of circumstances leading to casualty</label>

											</div>

											<div class="col-md-8">

												<label id="circumstances" class=" form-control-label"></label>

											</div>

										</div>

									</div>

									<div class="col-md-6">

										<div class="row form-group">

											<div class="col-md-4">

												<label class=" form-control-label"><strong>Diagnosis</strong>
													(As specified in Medical Report)</label>

											</div>

											<div class="col-md-8">

												<label id="batnpc_diagnosis" class=" form-control-label"></label>

												<input type="hidden" id="batnpc_diagnosis"
													name="batnpc_diagnosis" class="form-control autocomplete"
													autocomplete="off" onkeypress="AutoCompleteDiagnosis(this)">

											</div>

										</div>

									</div>

								</div>



								<div class="col-md-12">

									<div class="col-md-6">

										<div class="row form-group">

											<div class="col-md-4">

												<label class=" form-control-label">In Aid to Civ
													Auth/Power</label>

											</div>

											<div class="col-md-8">

												<label id="aid_to_civ" class=" form-control-label"></label>

											</div>

										</div>

									</div>

								</div>



								<div class="col-md-12">

									<div class="col-md-6">

										<div class="row form-group">

											<div class="col-md-4">

												<label class=" form-control-label">Whether NOK
													Informed or Not</label>

											</div>

											<div class="col-md-8" onclick="isInformed();">

												<label id="nok_informed" class=" form-control-label"></label>

											</div>

										</div>

									</div>

									<div class="col-md-6" id="dt_infodiv" style="display: none">

										<div class="row form-group">

											<div class="col-md-4">

												<label class=" form-control-label">Date of Informing</label>

											</div>

											<div class="col-md-8">

												<label id="date_of_informing" class=" form-control-label"></label>

											</div>

										</div>

									</div>



								</div>

								<div class="col-md-12" id="timeandmd_infodiv"
									style="display: none">



									<div class="col-md-6">

										<div class="row form-group">

											<div class="col-md-4">

												<label class=" form-control-label">Time of Informing</label>

											</div>

											<div class="col-md-8">

												<label id="time_of_informing" class=" form-control-label"></label>

											</div>

										</div>

									</div>

									<div class="col-md-6">

										<div class="row form-group">

											<div class="col-md-4">

												<label class=" form-control-label">Method of
													Informing</label>

											</div>

											<div class="col-md-8">

												<label id="methodofinforming" class=" form-control-label"></label>

											</div>

										</div>

									</div>

								</div>



								<div class="col-md-12">

									<div class="col-md-6">

										<div class="row form-group">

											<div class="col-md-4">

												<label class=" form-control-label">Category of
													Casualty</label>

											</div>

											<div class="col-md-8">

												<label id="cause_of_casuality" class=" form-control-label"></label>

												<select name="cause_of_casuality"
													id="cause_of_casuality_val" style="display: none;"
													class="form-control"
													onchange="getDescListApprove(this); onCauseOfCasualityApprove(this)">

													<option value="0">--Select--</option>

													<c:forEach var="item" items="${getCauseOfCasualityList}"
														varStatus="num">

														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>

													</c:forEach>

												</select>

											</div>

										</div>

									</div>

								</div>

								<div id="batnpc_battle_desc"
									style="display: block; width: -webkit-fill-available;">

									<div class="col-md-12" id="battle_descdiv">

										<div class="col-md-6">

											<div class="row form-group">

												<div class="col-md-4">

													<label class=" form-control-label">Description</label>

												</div>

												<div class="col-md-8">

													<label id="battle_desc" class=" form-control-label"></label>

													<select name="battle_desc" id="battle_desc_val"
														class="form-control" style="display: none;"
														onchange="onBattleDescApprove(this)">

														<option value="0">--Select--</option>

													<c:forEach var="item" items="${getBattleDescList}"
														varStatus="num">

														<option value="${item[1]}" name="${item[0]}">${item[0]}</option>

													</c:forEach>

													</select>

												</div>

											</div>

										</div>

										<div class="col-md-6" id="battle_desc_othersdiv"
											style="display: block;">

											<div class="row form-group">

												<div class="col-md-4">

													<label class=" form-control-label">Others</label>

												</div>

												<div class="col-md-8">

													<label id="battle_desc_others" class=" form-control-label"></label>

													<input type="hidden" id="battle_desc_others"
														name="battle_desc_others" class="form-control"
														autocomplete="off">

												</div>

											</div>

										</div>

									</div>

									<div class="col-md-12" id="battleothersdiv"
										style="display: block;">

										<div class="col-md-6">

											<div class="row form-group">

												<div class="col-md-4">

													<label class=" form-control-label">Others</label>

												</div>

												<div class="col-md-8">

													<label id="others_battle" class=" form-control-label"></label>

													<input type="hidden" id="others_battle"
														name="others_battle" class="form-control "
														autocomplete="off">

												</div>

											</div>

										</div>

									</div>

								</div>

								<div id="batnpc_physical_desc"
									style="display: block; width: -webkit-fill-available;">

									<div class="col-md-12" id="physical_descdiv">

										<div class="col-md-6">

											<div class="row form-group">

												<div class="col-md-4">

													<label class=" form-control-label">Description</label>

												</div>

												<div class="col-md-8">

													<label id="physical_desc" class=" form-control-label"></label>

													<select name="physical_desc" id="physical_desc_val"
														style="display: none;" class="form-control"
														onchange="onphysicalDescApprove(this)">

														<option value="0">--Select--</option>



													</select>

												</div>

											</div>

										</div>

										<div class="col-md-6" id="physical_desc_othersdiv"
											style="display: block;">

											<div class="row form-group">

												<div class="col-md-4">

													<label class=" form-control-label">Others</label>

												</div>

												<div class="col-md-8">

													<label id="physical_desc_others"
														class=" form-control-label"></label> <input type="hidden"
														id="physical_desc_others" name="physical_desc_others"
														class="form-control" autocomplete="off">

												</div>

											</div>

										</div>

									</div>

									<div class="col-md-12" id="physicalothersdiv"
										style="display: block;">

										<div class="col-md-6">

											<div class="row form-group">

												<div class="col-md-4">

													<label class=" form-control-label">Others</label>

												</div>

												<div class="col-md-8">

													<label id="others_physical" class=" form-control-label"></label>

													<input type="hidden" id="others_physical"
														name="others_physical" class="form-control "
														autocomplete="off">

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
	</form>
</c:if>
<!-- END BTLE & PHY ---->
<!-- START DISCIPLINE -->
<c:if test="${Discipline.size() != 0}">
	<form id="form_disipline">
		<div class="card">
			<div class="panel-group" id="accordion44">
				<div class="panel panel-default" id="w_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title lightred" id="w_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion44" href="#" id="w_final"
								onclick="divN(this)"><b>UPDATE Discipline</b></a>
						</h4>
					</div>
					<div id="collapse1w" class="panel-collapse collapse">
						<div class="card-body card-block">
							<br>
							<div class="row">
								<%-- 	 <div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> Army No </label>
						                </div>
						                <div class="col-md-8">
 						                    <label  id="army_no1" name="army_no" class=" form-control-label">   </label>
						                
						                </div>
						            </div>
	              				</div>	            				
	              			<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">Rank</label>
						                </div>
						                <div class="col-md-8">
						                  <label  id="rank_d" name="rank" class=" form-control-label">   </label>
						               <select name="rank" id="rank_d_val" class="form-control"  style="display: none;" >
														<option value="-1">--Select--</option>
														<c:forEach var="item" items="${getTypeofRankList}" varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
														</c:forEach>
											      </select>  
						                </div>
						            </div>
	              				</div>	          				
	              			</div> --%>

								<!-- 		<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">  Name </label>
						                </div>
						                <div class="col-md-8">
						                  <label  id="name_dis" name="name_dis" class=" form-control-label">   </label>
						                      <input type="text" id="name_dis" name="name" class="form-control autocomplete"  autocomplete="off" maxlength="10"> 
						                </div>
						            </div>
	              				</div>	 -->
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label">Army Act Sec</label>
											</div>
											<div class="col-md-8">
												<label id="army_act_sec" name="army_act_sec"
													class=" form-control-label"> </label> <select
													name="army_act_sec_select" id="army_act_sec_select"
													class="form-control" style="display: none;">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getArmyAct_Sec}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select> <input type="hidden" id="disi_id" name="disi_id"
													class="form-control" />
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label">Sub Clause</label>
											</div>
											<div class="col-md-8">
												<label id="sub_clause" name="sub_clause"
													class=" form-control-label"> </label> <select
													name="sub_clause_select" id="sub_clause_select"
													class="form-control" style="display: none;">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getSub_Clause}"
														varStatus="num">
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
												<label class=" form-control-label">Trialed By </label>
											</div>
											<div class="col-md-8">
												<label id="trialed_by" name="trialed_by"
													class=" form-control-label"> </label> <select
													name="trialed_byselect" id="trialed_byselect"
													class="form-control" style="display: none;">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getDisc_Trialed}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label">Punishment
													Awarded </label>
											</div>
											<div class="col-md-8">
												<label id="description1" name="description"
													class=" form-control-label"> </label>
												<!--  <input type="text" name="description" id="description1"   class="form-control" maxlength="10" /> -->
											</div>
										</div>
									</div>
								</div>

								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label">Type of Entry</label>
											</div>
											<div class="col-md-8">
												<label id="type_of_entry" name="type_of_entry"
													class=" form-control-label"> </label> <select
													name="type_of_entry" id="type_of_entry_val"
													class="form-control" style="display: none;">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getdisciplinetypeentry}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select> </br> <label id="type_of_entry_other" name="type_of_entry_other"
													class=" form-control-label"> </label>

											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"> Award Date </label>
											</div>
											<div class="col-md-8">
												<label id="award_dt" name="award_dt"
													class=" form-control-label"> </label>
												<!--  <input type="date" id="award_dt" name="award_dt" class="form-control autocomplete"  autocomplete="off" maxlength="10"> -->
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label">Unit Name</label>
											</div>
											<div class="col-md-8">
												<label id="unit_name" name="unit_name"
													class=" form-control-label"> </label>
												<!--  <input type="text" id="dis_sus" name="dis_sus" class="form-control autocomplete" autocomplete="off" maxlength="10"> -->
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
	</form>
</c:if>
<!-- END DISCIPLINE -->


<!-- START CHANGE OF COMM -->
<c:if test="${ChangeOfComm.size() != 0}">
	<div class="card">
		<div class="panel-group" id="accordion7">
			<div class="panel panel-default" id="y_div1">
				<div class="panel-heading">
					<h4 class="panel-title main_title blue" id="y_div5">
						<a class="droparrow collapsed" data-toggle="collapse"
							data-parent="#accordion7" href="#" id="y_final"
							onclick="divN(this)"><b>SSC TO PERMT COMMISSION</b></a>
					</h4>
				</div>
				<div id="collapse1y" class="panel-collapse collapse">
					<div class="card-body card-block">
						<br>
						<div class="row">
							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;"> </strong> Authority</label>
										</div>
										<div class="col-md-8">
											<label id="coc_authority" class=" form-control-label"></label>
											<input type="hidden" id="coc_ch_id" name="coc_ch_id"
												class="form-control autocomplete" autocomplete="off">
											<input type="hidden" id="coc_authority" name="authority"
												class="form-control autocomplete" autocomplete="off">
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;"> </strong> Date of Authority</label>
										</div>
										<div class="col-md-8">
											<label id="coc_date_of_authority" class=" form-control-label"></label>
											<input type="hidden" id="coc_date_of_authority"
												name="date_of_authority" class="form-control autocomplete"
												autocomplete="off">
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-12 form-group">
								<div class="col-md-2">
									<label for="text-input" class=" form-control-label"><strong
										style="color: red;"> </strong>New Personal No</label>
								</div>
								<div class="col-md-10">
									<div class="row form-group">
										<div class="col-md-4">
											<label id="persnl_no1" class=" form-control-label"></label> <select
												id="persnl_no1_val" name="persnl_no1" class="form-control"
												style="display: none;">
												<option value="-1">--Select--</option>
												<c:forEach var="item" items="${getPersonalType}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
										<div class="col-md-4">
											<label id="persnl_no2" class=" form-control-label"></label> <input
												type="hidden" id="persnl_no2" name="persnl_no2"
												onkeyup="onChangePerNo(this);"
												class="form-control-sm form-control" autocomplete="off"
												placeholder="Enter No..." maxlength="5">
										</div>
										<div class="col-md-4">
											<label id="persnl_no3" class=" form-control-label"></label> <input
												type="hidden" id="persnl_no3" name="persnl_no3"
												class="form-control-sm form-control" autocomplete="off"
												maxlength="10" readonly="readonly">
										</div>
									</div>
								</div>
							</div>

							<div class="col-md-12">
<!-- 							26-01-1994 -->
									<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;"> </strong> Previous Commission (Type)</label>
										</div>
										<div class="col-md-8">
											<label id="coc_previous_commission"
												class=" form-control-label"></label> <!-- <input type="text"
												id="coc_previous_commission" name="previous_commission"
												class="form-control autocomplete" autocomplete="off"> -->
												<select
												name="coc_previous_commissionselect"
												id="coc_previous_commissionselect" class="form-control" style="display: none;"
												>
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getTypeOfCommissionList}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>
<!-- 								e 26-01-1994 -->
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;"> </strong> Type of Commission Granted</label>
										</div>
										<div class="col-md-8">
											<label id="type_of_commission_granted"
												class=" form-control-label"></label> <select
												name="type_of_commission_grantedselect"
												id="type_of_commission_grantedselect" class="form-control"
												style="display: none">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getTypeOfCommissionList}"
													varStatus="num">
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
											<label class=" form-control-label"><strong
												style="color: red;"> </strong> Date of Permanent Commission</label>
										</div>
										<div class="col-md-8">
											<label id="coc_date_of_permanent_commission"
												class=" form-control-label"></label> <input type="hidden"
												id="coc_date_of_permanent_commission"
												name="date_of_permanent_commission"
												class="form-control autocomplete" autocomplete="off">
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;"> </strong> Date of Seniority</label>
										</div>
										<div class="col-md-8">
											<label id="coc_date_of_seniority" class=" form-control-label"></label>
											<input type="hidden" id="coc_date_of_seniority"
												name="date_of_seniority" class="form-control autocomplete"
												autocomplete="off">
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-12">

								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;"> </strong>Date From Which Change in
												Seniority is Effective</label>
										</div>
										<div class="col-md-8">
											<label id="eff_coc_date_of_seniority"
												class=" form-control-label"></label> <input type="hidden"
												id="eff_coc_date_of_seniority" name="eff_date_of_seniority"
												class="form-control autocomplete" autocomplete="off">
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

</c:if>

<!-- END CHANGE OF COMM -->
<!-- START EXTENSION OF COMM -->
<c:if test="${ExtensionComm.size() != 0}">
	<div class="card">
		<div class="panel-group" id="accordion8">
			<div class="panel panel-default" id="z_div1">
				<div class="panel-heading">
					<h4 class="panel-title main_title blue" id="z_div5">
						<a class="droparrow collapsed" data-toggle="collapse"
							data-parent="#accordion8" href="#" id="z_final"
							onclick="divN(this)"><b>EXTENSION OF SSC</b></a>
					</h4>
				</div>
				<div id="collapse1z" class="panel-collapse collapse">
					<div class="card-body card-block">
						<br>
						<div class="row">

							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;"> </strong> Authority</label>
										</div>
										<div class="col-md-8">
											<input type="hidden" id="eoc_ch_id" name="eoc_ch_id"
												class="form-control autocomplete" autocomplete="off">
											<label id="eoc_authority" class=" form-control-label"></label>
											<input type="hidden" id="eoc_authority" name="authority"
												class="form-control autocomplete" autocomplete="off">
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;"> </strong> Date of Authority</label>
										</div>
										<div class="col-md-8">
											<label id="eoc_authority_date" class=" form-control-label"></label>
											<input type="hidden" id="eoc_authority_date"
												name="date_of_authority" class="form-control autocomplete"
												autocomplete="off">
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong> From</label>
										</div>
										<div class="col-md-8">
											<label id="eoc_from" class=" form-control-label"></label> <input
												type="hidden" id="eoc_from" name="fromdt"
												class="form-control autocomplete" autocomplete="off">
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong> To</label>
										</div>
										<div class="col-md-8">
											<label id="eoc_to" class=" form-control-label"></label> <input
												type="hidden" id="eoc_to" name="todt"
												class="form-control autocomplete" autocomplete="off">
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
</c:if>
<!-- END EXTENSION OF COMM -->
<!-- START SECONDMENT DETAIL -->
<c:if test="${Secondment.size() != 0}">
	<form id="form_secondment">
		<div class="card">
			<div class="panel-group" id="accordion17">
				<div class="panel panel-default" id="aa_div16">
					<div class="panel-heading">
						<h4 class="panel-title main_title green" id="aa_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion17" href="#" id="aa_final"
								onclick="divN(this)"><b>SECONDMENT</b></a>
						</h4>
					</div>
					<div id="collapse1aa" class="panel-collapse collapse">
						<div class="card-body card-block">
							<br>
							<div class="row">
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"> Authority</label>
											</div>
											<div class="col-md-8">
												<label id="sec_authority" class=" form-control-label"></label>
												<input type="hidden" id="sec_authority" name="authority"
													class="form-control autocomplete" autocomplete="off">
												<input type="hidden" id="sec_id" name="sec_id"
													class="form-control" autocomplete="off">
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"> Date of
													Authority</label>
											</div>
											<div class="col-md-8">
												<label id="sec_date_of_authority"
													class=" form-control-label"></label> <input type="hidden"
													id="sec_date_of_authority" name="date_of_authority"
													class="form-control autocomplete" autocomplete="off">
											</div>
										</div>
									</div>

								</div>
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong>Seconded To</label>
											</div>
											<div class="col-md-8">
												<label id="seconded_to" class=" form-control-label"></label>
												<select name="seconded_to_val" id="seconded_to_val"
													class="form-control" style="display: none;">
													<option value="-1">--Select--</option>
													<c:forEach var="item" items="${getSeconded_To}"
														varStatus="num">
														<option value="${item[1]}" name="${item[0]}">${item[0]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>

									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"> Secondment with
													effect from</label>
											</div>
											<div class="col-md-8">
												<label id="secondment_effect" class=" form-control-label"></label>
												<input type="hidden" id="secondment_effect"
													name="secondment_effect" class="form-control autocomplete"
													autocomplete="off">
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
	</form>
</c:if>
<!-- END SECONDMENT DETAIL -->

<!-- START NON EFFECTIVE  -->

<c:if test="${NonEffective.size() != 0}">
	<form id="form_non_effective">
		<div class="card">
			<div class="panel-group" id="accordion41">
				<div class="panel panel-default" id="bb_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title green" id="bb_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion41" href="#" id="bb_final"
								onclick="divN(this)"><b>NON EFFECTIVE STATUS</b></a>
						</h4>
					</div>
					<div id="collapse1bb" class="panel-collapse collapse">
						<div class="card-body card-block">
							<br>
							<div class="row">
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"> Authority</label>
											</div>
											<div class="col-md-8">
												<label id="non_ef_authority" class=" form-control-label"></label>
												<input type="hidden" id="non_ef_authority"
													name="non_ef_authority" class="form-control"
													autocomplete="off"> <input type="hidden"
													id="nf_id" name="nf_id" value="0" class="form-control"
													autocomplete="off">
											</div>
										</div>


									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"> Date of
													Authority</label>
											</div>
											<div class="col-md-8">
												<label id="date_of_authority_non_ef"
													class=" form-control-label"></label> <input type="hidden"
													id="date_of_authority_non_ef"
													name="date_of_authority_non_ef" autocomplete="off"
													class="form-control" maxlength="10">
											</div>
										</div>

									</div>

								</div>
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label for="cause"> Cause of Non Effective <span
													class="star_design"></span></label>
											</div>
											<div class="col-md-8">
												<label id="cause_of_non_effective"
													class=" form-control-label"></label> <select
													id="cause_of_non_effectiveselect"
													name="cause_of_non_effective" class="form-control"
													style="display: none">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getOFFR_Non_EffectiveList}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>

									</div>

									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">

												<label for="Date Non"> Date of Non Effective <span
													class="star_design"></span></label>
											</div>
											<div class="col-md-8">
												<label id="date_of_non_effective"
													class=" form-control-label"></label> <input type="hidden"
													id="date_of_non_effective" name="date_of_non_effective"
													value="" class="form-control" autocomplete="off"
													maxlength="10">
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12" style="font-size: 15px;">
									<input type="checkbox" id="check_per_address"
										name="check_per_address" disabled="disabled"> <label
										for="text-input" class=" form-control-label"
										style="color: #dd1a3e; margin-left: 10px;"> Same as
										Permanent Address </label>
								</div>
								<div class="col-md-12">
									<label class=" form-control-label" style="margin-left: 10px;"><h4>
											Address After Service in Army (Permanent Address)</h4></label>
								</div>

								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong>COUNTRY</label>
											</div>
											<div class="col-md-8">
												<label id="perm_home_addr_country"
													class=" form-control-label"></label> <select
													name="per_home_addr_country_val"
													id="perm_home_addr_country_val" class="form-control"
													style="display: none;">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getMedCountryName}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong> STATE</label>
											</div>
											<div class="col-md-8">
												<label id="perm_home_addr_state" class=" form-control-label"></label>
												<select name="per_home_addr_state_val"
													id="perm_home_addr_state_val" class="form-control"
													style="display: none;">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getMedStateName}"
														varStatus="num">
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
												<label class=" form-control-label"><strong
													style="color: red;">* </strong> DISTRICT</label>
											</div>
											<div class="col-md-8">
												<label id="perm_home_addr_district"
													class=" form-control-label"></label> <select
													name="per_home_addr_district"
													id="perm_home_addr_district_val" class="form-control"
													style="display: none;">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getMedDistrictName}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong> TEHSIL</label>
											</div>
											<div class="col-md-8">
												<label id="perm_home_addr_tehsil"
													class=" form-control-label"></label> <select
													name="per_home_addr_tehsil" id="perm_home_addr_tehsil_val"
													class="form-control" style="display: none;">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getMedCityName}"
														varStatus="num">
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
												<label class=" form-control-label"><strong
													style="color: red;">* </strong> Village/Town/City</label>
											</div>
											<div class="col-md-8">
												<label id="perm_home_addr_village"
													class=" form-control-label"></label>
												<!-- <input type="text" id="perm_home_addr_village" name="per_home_addr_village" class="form-control autocomplete" autocomplete="off"  maxlength="50"> -->
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong> Pin</label>
											</div>
											<div class="col-md-8">
												<label id="perm_home_addr_pin" class=" form-control-label"></label>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong> Nearest Railway Station</label>
											</div>
											<div class="col-md-8">
												<label id="perm_home_addr_rail" class=" form-control-label"></label>
												<input type="hidden" id="non_addr_ch_id"
													name="non_addr_ch_id" class="form-control autocomplete"
													autocomplete="off" value="0"> <input type="hidden"
													id="addr_pending_ch_id" name="addr_pending_ch_id"
													class="form-control autocomplete" autocomplete="off"
													value="0">
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong>Is Rural /Urban/Semi Urban</label>
											</div>
											<div class="col-md-8">
												<label for="lbl_perm_home_addr_rural" id="perm_home_addr1"></label>
												<!--  <input type="radio" id="perm_home_addr_rural" name="per_home_addr_rural_urban" value="rural" > Rural</label> 
					    	 <label for="lbl_per_home_addr_urban" >
							 <input type="radio" id="perm_home_addr_urban" name="per_home_addr_rural_urban" value="urban" > Urban</label> 
					    	 <label for="lbl_per_home_addr_semi_urban">
							 <input type="radio" id="perm_home_addr_semi_urban" name="per_home_addr_rural_urban" value="semi_urban" >Semi Urban</label>  -->
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong>Border Area</label>
											</div>
											<div class="col-md-8">
												<label for="border_area" id="perm_border_area"></label>
												<!--  <label for="border_area">
						 <input type="radio" id="per_border_area" name="border_area" value="yes"   >Yes</label> 
					    	 <label for="border_area1"> 
				      		 <input type="radio" id="per_border_area1" name="border_area" value="no" > No</label> -->

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
	</form>
</c:if>
<c:if test="${deserterV.size() != 0}">
	<form id="forminspupdate">
		<div class="card">
			<div class="panel-group" id="accordion8">
				<div class="panel panel-default" id="dd_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title blue" id="dd_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion8" href="#" id="dd_final"
								onclick="divN(this)"><b>DESERTER</b></a>
						</h4>
					</div>
					<div id="collapse1dd" class="panel-collapse collapse">
						<div class="card-body card-block">
							<br>
							<div class="row">
								<div class="col-md-12">
									<%-- 	<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong>Declared / Recovered Deserter</label>
								</div>
								<div class="col-md-8">
								
									<select name="deserter" id="deserter" class="form-control">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getDclredRcvrdList}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div> --%>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong>Arms Type</label>
											</div>
											<div class="col-md-8">
												<input type="hidden" id="des_ch_id" name="des_ch_id"
													value="0" class="form-control autocomplete"
													autocomplete="off"> <label id="arm_type"
													class=" form-control-label"></label> <select
													name="arm_type_val" id="arm_type_val" class="form-control"
													onchange="ArmType();" style="display: none;">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getARM_TYPE}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12" id="div_weapon" style="display: none;">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong> Weapon</label>
											</div>
											<div class="col-md-8">
												<label id="arm_type_weapon" class=" form-control-label"></label>
											</div>
										</div>
									</div>
								</div>

								<div class="col-md-12">

									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong> Date of Desertion</label>
											</div>
											<div class="col-md-8">
												<label id="dt_desertion" class=" form-control-label"></label>
											</div>
										</div>
									</div>

									<div class="col-md-6" id="div_dt_rec" style="display: none;">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong>Date of Recovered</label>
											</div>
											<div class="col-md-8">
												<label id="dt_recovered" class=" form-control-label"></label>
											</div>
										</div>
									</div>
								</div>

								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong>Cause of Desertion</label>
											</div>
											<div class="col-md-8">
												<label id="desertion_cause" class=" form-control-label"></label>
												<select name="desertion_cause_val" id="desertion_cause_val"
													class="form-control" onchange="DesertionCause();"
													style="display: none;">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getCauseOfDeserter}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="col-md-6" id="div_arms_rec" style="display: none;">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong>Recovered With Arms or Not</label>
											</div>
											<div class="col-md-8">
												<label id="recovered_arms" class=" form-control-label"></label>
												<select name="recovered_arms_val" id="recovered_arms_val"
													class="form-control" style="display: none;">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getARM_TYPE}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<!-- 		<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong>Class of Indl</label>
								</div>
								<div class="col-md-8">
								    <label id="indl_class" class=" form-control-label"></label>
								</div>
							</div>
						</div> -->
								</div>

								<div id="div_cause" class="col-md-12" style="display: none;">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong> Others</label>
											</div>
											<div class="col-md-8">
												<label id="ot_desertion_cause" class=" form-control-label"></label>
											</div>
										</div>
									</div>
								</div>

							</div>
							<!-- 		
                        <div class="card-footer" align="center" class="form-control">
							 <input id="btnsave" type="button" class="btn btn-primary btn-sm" value="Reject" onclick="Deserter_Reject();">		              
			            </div> -->
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
</c:if>
<!-- END DESERTER -->
<c:if test="${ChangeOfCDA!=0 }">

<form id="form_cda_acc">
		<div class="card">
			<div class="panel-group" id="accordion6">
				<div class="panel panel-default" id="cda_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title lightblue" id="cda_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion6" href="#" id="cda_final" onclick="divN(this)"
								><b>CHANGE IN CDA ACCOUNT NO</b></a>
						</h4>
					</div>
					<div id="collapse1cda" class="panel-collapse collapse">
				<div class="card-body card-block"><br>
<!-- 				  <input type="button" name="save" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('CDA');" />  -->
							<div class="row">
					 	<div class="col-md-12">
					 		<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Personal No </label>
						                </div>
						                <div class="col-md-8">
						                    <label class=" form-control-label" id="personnel_no_c" ></label> 
<!-- 				  							<input type="text" id="personnel_no_c" name="personnel_no_c"class="form-control autocomplete" maxlength="9" autocomplete="off" onchange="personal_number()" onkeyup="return specialcharecter(this)" onkeypress="return AvoidSpace(event)">  -->
						                </div>
						            </div>
	              				</div>	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                     <label class=" form-control-label"><strong style="color: red;">* </strong> CDA Account No</label>
						                </div>
						                <div class="col-md-8">
						                <label class=" form-control-label" id="cda_acc_no" ></label> 
<!-- 						                  <input type="text" id="cda_acc_no" name="cda_acc_no" class="form-control autocomplete" maxlength="20" autocomplete="off" onkeyup="return specialcharecterCDA(this)" onkeypress="return AvoidSpace(event)"> -->
						                  <input type="hidden" id="statusV" name="statusV" class="form-control autocomplete"  > 
						                  <input type="hidden" id="unit_sus_noV" name="unit_sus_noV" class="form-control autocomplete"  > 
						                  <input type="hidden" id="unit_nameV" name="unit_nameV" class="form-control autocomplete"  > 
						                  <input type=hidden id="rankV" name="rankV" class="form-control autocomplete"  > 
						                  <input type=hidden id="personnel_noV" name="personnel_noV" class="form-control autocomplete"  > 						                 						                  
						                  <input type="hidden" id="idr" name="idr" class="form-control autocomplete" autocomplete="off" value="0"> 
<!-- 						                  <input type="hidden" id="comm_id" name="comm_id" class="form-control autocomplete" autocomplete="off"  value="0"> 						                    -->
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
<!-- 									<input type="hidden" id="hd_p_rank" name="hd_p_rank" class="form-control autocomplete" autocomplete="off"> -->
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
				<div class="col-md-12">
					  <div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<strong	style="color: red;">* </strong><label class=" form-control-label"> From SUS No </label>
								</div>
								<div class="col-md-8">
									<label id="from_sus_no" name="from_sus_no"></label> 
										<input type="hidden" id="update_id" name="update_id" class="form-control autocomplete" autocomplete="off" />
<!-- 									<input type="hidden" id="comm_id" name="comm_id" value="0" class="form-control autocomplete" autocomplete="off"/> -->
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
<!-- 									<input type="text" name="dt_of_tos" id="dt_of_tos" maxlength="10" value="DD/MM/YYYY" onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;" -->
<!-- 										onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')"  -->
<!-- 										onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" /> -->
										
											<label id="dt_of_tos"> </label>							
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
<!-- 							<div class="card-footer" align="center" class="form-control"> -->
<!-- <!-- 		              		 <input type="button" class="btn btn-primary btn-sm" value="Submit for Approval" onclick="posting_in_edit_fn();">		               -->
<!-- 			          <input type="button" class="btn btn-primary btn-sm" value="Reject" onclick="if (confirm('Are You Sure You Want to Reject Officer Data?')){addRemarkModel('get_post_in_data_reject',0)}else{return false;}">	               -->
<!-- 			            </div>  -->
						</div>
					</div>
				</div>
			</div>
		</div>
		</form>
	<!-- END CONTACT DETAILS -->
</c:if>


<a href="#" class="go-top fa fa-arrow-up" style="display: none"></a>


<script type="text/javascript">
var app_status=1;
function myFunction() {
	  var checkBox = document.getElementById("myCheck");
	 
	  if (checkBox.checked == true){
		  $("#submit_a").show();
		 
	  } else {
		  $("#submit_a").hide();
		
	  }
}


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
		} 

</script>


<script>
$(document).ready(function() {
	alert
	$("#personnel_noV").val('${personnel_no5}');
// 	$("#statusV").val('${status5}');
// 	$("#rankV").val('${rank5}');
	$("#comm_idV").val('${comm_id}');
	$("#comm_id").val('${comm_id}');
// 	$("#unit_nameV").val('${unit_name5}');
// 	$("#unit_sus_noV").val('${unit_sus_no5}');
	 	
	$.ajaxSetup({
			async : false
		});
// 	$('#event').val('${event}');
 	$('#comm_id').val('${comm_id}');
 	$('#census_id').val('${census_id}');
  	$('#personnel_no').val('${personnel_no2}');
  	 $("input#personnel_no").attr('readonly', true);
  	
  	 personal_number();
  	  $("#submit_a").hide();
  	

  	<c:if test="${ChangeOfRank.size() != 0}">
		getChangeOfRank();
  	</c:if>
  	
  	<c:if test="${ChangeOfName.size() != 0}">
  	get_change_of_name();
	</c:if>
	
  	<c:if test="${ChngAppointment.size() != 0}">
  	get_Appointment();
	</c:if>
// 	<c:if test="${ChangeOfTA.size() != 0}">
// 	get_ta_status();
// 	</c:if>
  	<c:if test="${IdentityCard.size() != 0}">
  	getIdentity_Card();
	</c:if>
	
  	<c:if test="${religion.size() != 0}">
  	get_religion();
	</c:if>
	
//   	<c:if test="${Marital.size() != 0}">  	
//   	$("#marital_eventDiv").show();
// 	getfamily_marriageDetails();	
// 	</c:if>
// 	<c:if test="${Spouse_Quali.size() != 0}">  	
// 	getSpouseQualificationData();
// 	</c:if>
  	<c:if test="${ChildDetails.size() != 0}">
  	m_children_Details();
  	colAdj("m_children_details_table");
	</c:if>
	
  	<c:if test="${NOK.size() != 0}">
  	get_nokaddress_details();
	</c:if>
	
  	<c:if test="${ChangeAdd.size() != 0}">
  	get_changeaddress_details();
	</c:if>
	
  	<c:if test="${CDAaccount.size() != 0}">
  	get_cda_acnt_no();
	</c:if>
	
  	<c:if test="${Language.size() != 0}">
  	get_language_details();
  	colAdj("language_table");
  	colAdj("foregin_language_table");
	</c:if>
	
  	<c:if test="${Qualification.size() != 0}">
  	getQualifications();
  	colAdj("qualificationtable");
	</c:if>
	
//   	<c:if test="${Promotional_Exam.size() != 0}">
//   	get_promo_exam_details();
//   	colAdj("promo_exam_table");
// 	</c:if>
	
	<c:if test="${ChangeOfPosting != 0}">
	get_post_in_data();
		</c:if>
		<c:if test="${ChangeOfCDA != 0}">
		get_cda();
			</c:if>
  	<c:if test="${BEPT.size() != 0}">
  	bpet_Details();
  	colAdj("bpet_table");
	</c:if>
	
  	<c:if test="${FiringStan.size() != 0}">
  	fire_Details();
  	colAdj("fire_std_table");
	</c:if>
	
  	
	
  	<c:if test="${MedDetails.size() != 0}">
  	getsShapeDetails();
	gethShapeDetails();
	getaShapeDetails();
	getpShapeDetails();
	geteShapeDetails();
	getcCopeDetails();
	getoCopeDetails();
	getpCopeDetails();
	geteCopeDetails();
		colAdj("s_madtable");
		colAdj("h_madtable");
		colAdj("a_madtable");
		colAdj("p_madtable");
		colAdj("e_madtable");
		colAdj("c_cmadtable");         
		colAdj("c_omadtable");
		colAdj("c_pmadtable");
		colAdj("c_emadtable");
	</c:if>
	
  	<c:if test="${ForeignCountry.size() != 0}">
  	getUPForeign_CountriesDetails(); 
  	colAdj("foreign_country_visit");
	</c:if>
	
  	<c:if test="${AwardsMedal.size() != 0}">
  	getawardsNmedals();
	colAdj("awardsNmedal_table");
	</c:if>
	
  	<c:if test="${btel_cas.size() != 0}">
  	get_Battle_and_Physical_Casuality_details();
	</c:if>
	
  	<c:if test="${Discipline.size() != 0}">
  	get_change_of_Discipline();
	</c:if>
	
  	<c:if test="${InterArmTransfer.size() != 0}">
  	getInterArm();
	</c:if>
	
  	<c:if test="${ChangeOfComm.size() != 0}">
  	getcoc();
	</c:if>
	
  	<c:if test="${ExtensionComm.size() != 0}">
  	geteoc();
	</c:if>
	
  	<c:if test="${Secondment.size() != 0}">
  	get_Secondment();
	</c:if>
	
  	<c:if test="${NonEffective.size() != 0}">
  	getNon_effective();
	get_non_eff_address_details();
// 	get_changeaddress_details();
	</c:if>
  
	 <c:if test="${deserterV.size() != 0}">
 
	    get_deserter();
	 </c:if>
	 

		
		
});

var curr_marital_status=0;
var serving_status="SERVING";

function personal_number(){
	
	var personnel_no = '${personnel_no2}';
	 $.post('GetPersonnelNoData?' + key + "=" + value,{ personnel_no : personnel_no },function(j) {
	
		 if(j!=""){
			 
	    	$("#comm_id").val(j[0][0]);
	    	$('#inter_arm_old_arm_service_val').val(j[0][8]);
			$('#inter_arm_old_arm_service').text($( "#inter_arm_old_arm_service_val option:selected" ).text());
	    	if(j[0][9] != 0){
	    		$('#inter_arm_old_regt_val').val(j[0][9]);
				$('#inter_arm_old_regt').text($( "#inter_arm_old_regt_val option:selected" ).text());
	    	}
	    	else {
	    		$('#inter_arm_old_regt').text("");
	    	}
	    	var comm_id =j[0][0]; 
	    	 $.post('GetCensusDataApprove_xml?' + key + "=" + value,{ comm_id : comm_id },function(k) {
	    		 if(k.length > 0)
	    		 {
	    			 $("#type_of_comm_granted").val(k[0][24]);
// 	    			 $('#census_id').val(k[0][1]); 
	    			  $('#div_cda_acnt_no').show(); 
	    			  curr_marital_status=k[0][13];  
	    			  $('#marital_event').val('0');
	    			  $('#marriage_div').show();
	    			  
	    			   $("#cadet_name").text(k[0][2]);
	    			 	if(k[0][11]==null){
	    		    		$("#dob").text("");    
	    		    	}
	    		    	else{
	    		    		 $("#dob").text(convertDateFormate(k[0][11]));  
	    		    	}
	    			 	//alert("nmk---" + k[0][13]);
	    			    $("#marital_status_p").text(k[0][13]);
	    			    if(k[0][13] == '8'){
	    					//alert("ok");
	    					$("#spouse_addressInnerdiv").show();
	    				}
	    				else{
	    					$("#spouse_addressInnerdiv").hide();
	    				}
	    			    
	    			    $("#marital_status_hp").val(k[0][13]);
	    		    	$("#p_rank").text(k[0][3]);
	    		    	
	    		    	$("#hd_p_rank").val(k[0][3]);
	    		    	$("#p_app").text(k[0][4]);
	    		    	if(k[0][5]==null){
	    		    		$("#p_app_dt").text("");    
	    		    	}
	    		    	else{
	    		    		 $("#p_app_dt").text(convertDateFormate(k[0][5]));  
	    		    	}
	    		    	if(k[0][6]==null){
	    		    		$("#p_tos").text("");    
	    		    	}
	    		    	else{
	    		    		 $("#p_tos").text(convertDateFormate(k[0][6]));  
	    		    	}
	    		    
	    		    	$("#app_sus_no").text(k[0][7]);
	    		    	$("#p_id_no").text(k[0][8]);
	    		    	$("#p_religion").text(k[0][9]);
	    		    	$("#app_parent_arm").text(k[0][10]);
	    		    	if(k[0][24] == '20'){
	    		    		$("#form_ta_status").show();
	    		    		}
	    		    	$("#p_cmd").text(k[0][16]);
	    		    	
	    		    	if(k[0][10] == "GORKHA" || k[0][10] == "INFANTRY"){
	    		 			$("#reg_div").show();
	    		 		}
	    		 	  else
	    		 		  {
	    		 		 $("#reg_div").hide();
	    		 		  }
	    		    	
	    		    	/* $('#inter_arm_regt_val').val(k[0][17]);
	    				$('#p_regt').text($( "#inter_arm_regt_val option:selected" ).text()); */
	    		    	if(k[0][17]!=0){
	    		    		$('#inter_arm_regt_val').val(k[0][17]);
	    		    		$('#p_regt').text($( "#inter_arm_regt_val option:selected" ).text());
	    		    	}
	    		    	else{
	    		    		$('#p_regt').text("");
	    		    	}
	    				
	    		    	var sus_no =k[0][7];
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
	    		    	                   //alert("unit---" + dec(enc,j[0]));
	    		    	                   $("#app_unit_name").text(dec(enc,j[0])); 
	    		        }).fail(function(xhr, textStatus, errorThrown) {
	    		        });
	    		    } 
	    		 }
	   });
	 		$.ajaxSetup({
					async : false
				});
	 		
	    	 $.post('GetServingStatus_xml?' + key + "=" + value,{ comm_id : comm_id },function(k) {
	    	
	    		 if(k.length > 0)
	    		 {
	    		    	$("#p_serving_status").text(k[0][2]);
	    		    	if(k[0][2]!=null && k[0][2]!="" && k[0][2]!=undefined)
	    		    		serving_status=k[0][2];
	    		    
	    		    	$.post("getOFFR_Non_EffectiveList?" + key + "=" + value, {
	    		    		serving_status : serving_status
	    				})
	    				.done(
	    						function(j) {

	    							var options = '<option   value="0">' + "--Select--"
	    									+ '</option>';
	    							for (var i = 0; i < j.length; i++) {

	    								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
	    										+ j[i][1] + '</option>';

	    							}

	    							$("select#cause_of_non_effectiveselect").html(options);
	    							
	    						}).fail(function(xhr, textStatus, errorThrown) {
	    				});
	    		 }
	   });
		 }
});

}




// function personal_number(){
// 	var personnel_no = '${personnel_no2}';
	
// 	 $.post('GetPersonnelNoData?' + key + "=" + value,{ personnel_no : personnel_no },function(j) {
// 		 if(j!=""){
// 	    	$("#comm_id").val(j[0][0]);
// 	    	//KAJAL
// 	    	/* $('#inter_arm_old_arm_service_val').val(j[0][8]);
// 			$('#inter_arm_old_arm_service').text($( "#inter_arm_old_arm_service_val option:selected" ).text()); */
// 	    	/* if(j[0][9] != 0){
// 	    		$('#inter_arm_old_regt_val').val(j[0][9]);
// 				$('#inter_arm_old_regt').text($( "#inter_arm_old_regt_val option:selected" ).text());
// 	    	} 
// 	    	else {
// 	    		$('#inter_arm_old_regt').text("");
// 	    	}*/
// 	    	var comm_id =j[0][0]; 
// 	    	 $.post('GetCensusDataApprove?' + key + "=" + value,{ comm_id : comm_id },function(k) {
// 	    		 if(k.length > 0)
// 	    		 {
// 	    			 $('#census_id').val(k[0][1]); 
// 	    			  $('#div_cda_acnt_no').show(); 
// 	    			  curr_marital_status=k[0][13];  
// 	    			  $('#marital_event_val').val('0');
// 	    			  $('#marriage_div').show();
	    			  
// 	    			   $("#cadet_name").text(k[0][2]);
// 	    			 	if(k[0][11]==null){
// 	    		    		$("#dob").text("");    
// 	    		    	}
// 	    		    	else{
// 	    		    		 $("#dob").text(convertDateFormate(k[0][11]));  
// 	    		    	}
// 	    			    $("#marital_status_p").text(k[0][13]);
// 	    		    	$("#p_rank").text(k[0][3]);
// 	    		    	$("#p_app").text(k[0][4]);
// 	    		    	if(k[0][5]==null){
// 	    		    		$("#p_app_dt").text("");    
// 	    		    	}
// 	    		    	else{
// 	    		    		 $("#p_app_dt").text(convertDateFormate(k[0][5]));  
// 	    		    	}
// 	    		    	if(k[0][6]==null){
// 	    		    		$("#p_tos").text("");    
// 	    		    	}
// 	    		    	else{
// 	    		    		 $("#p_tos").text(convertDateFormate(k[0][6]));  
// 	    		    	}
	    		    
// 	    		    	$("#app_sus_no").text(k[0][7]);
// 	    		    	$("#p_id_no").text(k[0][8]);
// 	    		    	$("#p_religion").text(k[0][9]);
// 	    		    	$("#app_parent_arm").text(k[0][10]);
// 	    		    	$("#p_cmd").text(k[0][16]);
	    		    	
// 	    		    	if(k[0][10] == "GORKHA" || k[0][10] == "INFANTRY"){
// 	    		 			$("#reg_div").show();
// 	    		 		}
// 	    		 	  else
// 	    		 		  {
// 	    		 		 $("#reg_div").hide();
// 	    		 		  }
	    		    	
// 	    		    	/* $('#inter_arm_regt_val').val(k[0][17]);
// 	    				$('#p_regt').text($( "#inter_arm_regt_val option:selected" ).text()); */
// 	    		    	if(k[0][17]!=0){
// 	    		    		$('#inter_arm_regt_val').val(k[0][17]);
// 	    		    		$('#p_regt').text($( "#inter_arm_regt_val option:selected" ).text());
// 	    		    	}
// 	    		    	else{
// 	    		    		$('#p_regt').text("");
// 	    		    	}
	    				
// 	    		    	var sus_no =k[0][7];
// 	    		    	getunit(sus_no);
// 	    		    	function getunit(val) {	
// 	    		            $.post("getTargetUnitNameList?"+key+"="+value, {
// 	    		            	sus_no : sus_no
// 	    		        }, function(j) {
// 	    		                //var json = JSON.parse(data);
// 	    		                //...

// 	    		        }).done(function(j) {
// 	    		    				   var length = j.length-1;
// 	    		    	                   var enc = j[length].substring(0,16); 
// 	    		    	                   //alert("unit---" + dec(enc,j[0]));
// 	    		    	                   $("#app_unit_name").text(dec(enc,j[0])); 
// 	    		        }).fail(function(xhr, textStatus, errorThrown) {
// 	    		        });
// 	    		    } 
// 	    		 }
// 	   });
// 	 		$.ajaxSetup({
// 					async : false
// 				});
	 		
// 	    	 $.post('GetServingStatus?' + key + "=" + value,{ comm_id : comm_id },function(k) {
// 	    		 if(k.length > 0)
// 	    		 {
// 	    		    	$("#p_serving_status").text(k[0][2]);
// 	    		    	if(k[0][2]!=null && k[0][2]!="" && k[0][2]!=undefined)
// 	    		    		serving_status=k[0][2];
	    		    
// 	    		    	$.post("getOFFR_Non_EffectiveList?" + key + "=" + value, {
// 	    		    		serving_status : serving_status
// 	    				})
// 	    				.done(
// 	    						function(j) {

// 	    							var options = '<option   value="0">' + "--Select--"
// 	    									+ '</option>';
// 	    							for (var i = 0; i < j.length; i++) {

// 	    								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
// 	    										+ j[i][1] + '</option>';

// 	    							}

// 	    							$("select#cause_of_non_effectiveselect").html(options);
	    							
// 	    						}).fail(function(xhr, textStatus, errorThrown) {
// 	    				});
// 	    		 }
// 	   });
// 		 }
// });

// }
function formateDate(value){
	var date = new Date(value);
	var formattedDate = date.getFullYear() + '-' + ('0' + (date.getMonth() + 1)).slice(-2) + '-' + ('0' + date.getDate()).slice(-2);
	return formattedDate;
}

function getChangeOfRank(){

	
	 
	<c:if test="${ChangeOfRank.size() != 0}">
				 $('#ch_r_id').val('${ChangeOfRank.get(0).id}');
					 $('#r_authority').text('${ChangeOfRank.get(0).authority}');
					 $('#r_date_of_authority').text(convertDateFormate('${ChangeOfRank.get(0).date_of_authority}'));	
					 $('#rank_typeSelect').val('${ChangeOfRank.get(0).rank_type}');
					 $('#rank_type').text($( "#rank_typeSelect option:selected" ).text());
					 $('#rank_val').val('${ChangeOfRank.get(0).rank}' );	
					 $('#rank').text($( "#rank_val option:selected" ).text());
					 $('#date_of_rank').text(convertDateFormate('${ChangeOfRank.get(0).date_of_rank}')); 
					
					$('#rank_v').val('${ChangeOfRank.get(0).rank}');
					$('#dt_rank').val(formateDate('${ChangeOfRank.get(0).date_of_rank}'));
			</c:if>
	 
	} 

function get_change_of_name(){
	
	<c:if test="${ChangeOfName.size() != 0}">
			     $('#name_id').val('${ChangeOfName.get(0).id}' );
				 $("#na_authority").text('${ChangeOfName.get(0).authority}');
				 $("#na_date_of_authority").text(convertDateFormate('${ChangeOfName.get(0).date_of_authority}' ));
				 $("#name").text('${ChangeOfName.get(0).name}' );
				 $("#na_change_in_name_date").text(convertDateFormate('${ChangeOfName.get(0).change_in_name_date}' ));
				 $("#name_v").val('${ChangeOfName.get(0).name}');
					</c:if>
	  
}	

function get_Appointment(){
	

	
	<c:if test="${ChngAppointment.size() != 0}">

				$('#appoint_id').val('${ChngAppointment.get(0).id}');
				 $("#appt_authority").text('${ChngAppointment.get(0).appt_authority}');
				 $("#appt_date_of_authority").text(convertDateFormate('${ChngAppointment.get(0).appt_date_of_authority}' ));
				 $("#appointment_val").val('${ChngAppointment.get(0).appointment}');
				 $('#appointment').text($( "#appointment_val option:selected" ).text());
				 $("#date_of_appointment").text(convertDateFormate('${ChngAppointment.get(0).date_of_appointment}' ));
                 ///bisag 010723 v2 (new requirement)
                 
               if ('${ChngAppointment.get(0).appointment}' == "9562" ||'${ChngAppointment.get(0).appointment}' == "9565") {
							
            	   $("#att_dapu").show();
							$("#att_dapu1").show();
							 $("#at_parent_sus_no").text('${ChngAppointment.get(0).parent_sus_no}');
							 $("#at_parent_unit").text('${ChngAppointment.get(0).parent_unit}');
							 $("#at_attachment_location").text('${ChngAppointment.get(0).x_list_loc}');
						} else {
							
							$("#att_dapu").hide();
							$("#att_dapu1").hide();
						}
				
				 </c:if>
	
	}
 

function getIdentity_Card(){
	
	<c:if test="${IdentityCard.size() != 0}">
				 $('#card_id').val( '${IdentityCard.get(0).id}');
				 $("#id_card_no").text('${IdentityCard.get(0).id_card_no}');
				 $("#issue_authority").text(fn_getUnitnamefromSusHis('${IdentityCard.get(0).issue_authority}'));
				 $("#id_marks").text('${IdentityCard.get(0).id_marks}' );			
				 $('#issue_dt').text(convertDateFormate('${IdentityCard.get(0).issue_dt}' ));
				 $('#hair_colourselect').val('${IdentityCard.get(0).hair_colour}' );

				 $('#hair_colour').text($( "#hair_colourselect option:selected" ).text());
				 if('${IdentityCard.get(0).hair_other}'!=null && '${IdentityCard.get(0).hair_other}'!='' && ($('#hair_colour').text().toUpperCase()==other || $('#hair_colour').text().toUpperCase()==others)){
						$('#hair_colour').text('${IdentityCard.get(0).hair_other}');
					}
				 $('#eye_colourselect').val('${IdentityCard.get(0).eye_colour}');
				 $('#eye_colour').text($( "#eye_colourselect option:selected" ).text());
				 if('${IdentityCard.get(0).eye_other}'!=null && '${IdentityCard.get(0).eye_other}'!='' && ($('#eye_colour').text().toUpperCase()==other || $('#eye_colour').text().toUpperCase()==others)){
						$('#eye_colour').text('${IdentityCard.get(0).eye_other}');
					}
				 
				if('${IdentityCard.get(0).id}' != "0"){
					$('#identity_image_preview').attr("src", "censusIdentityConvertpath?i_id="+'${IdentityCard.get(0).id}'+" ");
				}
				 
				 </c:if> 
			
}

function get_religion(){

	
	 
	<c:if test="${religion.size() != 0}">
				     $('#rel_id').val( '${religion.get(0).id}' );
					 $("#rel_authority").text( '${religion.get(0).authority}' );
					 $("#rel_date_of_authority").text(convertDateFormate( '${religion.get(0).date_of_authority}'));
					 $('#religionSelect').val('${religion.get(0).religion}');
					 $('#religion').text($( "#religionSelect option:selected" ).text());
					 $('#relign').val( '${religion.get(0).religion}');
					    var text = $("#religionSelect option:selected").text();
						if(text == "OTHERS"){
							$('#religion_other').text( '${religion.get(0).other}' );
							$("#other_div").show();
						}
						else{
							$("#other_div").hide();
						}
						 $("#rel_change_in_rel_date").text(convertDateFormate( '${religion.get(0).change_in_rel_date}'));
			</c:if> 		
					 

		
}	


	
	
//spouse qualification


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
	

// 	 fn_other_exam();	
// 	 fn_otherShowHide(document.getElementById('quali_degree'),'other_div_qualiDeg','quali_deg_other');
// 	 fn_otherShowHide(document.getElementById('specialization'),'other_div_qualiSpec','quali_spec_other');
	 	<c:if test="${Qualification.size() == 0}">
	 fn_otherShowHide(document.getElementById('spouse_quali'),'other_div_examSpouse','exam_otherSpouse');
	 fn_otherShowHide(document.getElementById('quali_degree_spouse'),'other_div_qualiDegSpouse','quali_deg_otherSpouse');
	 fn_otherShowHide(document.getElementById('spouse_specialization'),'other_div_qualiSpecSpouse','quali_spec_otherSpouse');
 </c:if>
 }
function fn_other_exam() {
  	var text = $("#quali option:selected").text();
  	if(text == "OTHERS"){
  		$("#other_div_exam").show();
  	}
  	else{
  		$("#other_div_exam").hide();
  		$("#exam_other").val("");
  	}
  }


function fn_other_class() {
	var text = $("#div_class option:selected").text();
	if(text == "OTHERS"){
	
		$("#other_div_class").show();
		
	}
	else
	{
		$("#other_div_class").hide();
		$("#class_other").val("");
		
	}
}


function fn_otherShowHide(obj,Divother_id,other_id){
	var thisText=$("#"+obj.id+" option:selected").text();
	
	
	if(thisText=="OTHERS"){
		$('#'+Divother_id).show();
	}
	else{
		$('#'+Divother_id).hide();
		$('#'+other_id).val('');
	}
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

function m_children_Details() {


	var x = 0;
	
	
	
	
//  			$('#m_children_detailstbody').empty();
	
		<c:forEach var="j" items="${ChildDetails}" varStatus="num">

		x = x + 1;
		var dob = convertDateFormate('${j.date_of_birth}');
		if('${j.date_of_adpoted}' != ''){
			 adob = convertDateFormate('${j.date_of_adpoted}');
			}
		else{
			adob = '';
		}
			
		$("#m_children_detailstbody").append('<tr id="tr_children'+x+'">'
			+ '<td class="child_srno">'
			+ x
			+ '</td>'
			+ '<td style="width: 10%;"><label id="sib_name_lb'+x+'">'+"${j.name }"+ '</label> '
			+ '<td style="width: 10%;"> <label id="sib_date_of_birth_lb'+x+'">'+dob+'</label></td>'
			+ '<td style="width: 10%;"><label id="sib_type_lb'+x+'"></label> '
			+ '</td>'
			+ '<td style="width: 10%;"><label id="sib_relationship_lb'+x+'"></label> <select style="display:none" name="sib_relationship'+x+'" id="sib_relationship'+x+'" class="form-control"   >'
			+ '<option value="0">--Select--</option>'
			+ '<c:forEach var="item" items="${getChild_RelationshipList}" varStatus="num">'
			+ '<option value="${item[0]}" name="${item[1]}">${item[1]}</option>'
			+ '</c:forEach></select></td>'
			+ '<td style="width: 10%;"><label id="sib_adopted_lb'+x+'"></label></td>'
			+ '<td style="width: 10%;"> <label id="sib_adop_lb'+x+'">'+adob+'</label></td>'
			
			+ '<td style="width: 10%;"><label id="aadhar_no_lb'+x+'">'+setInvalidToNull("${j.aadhar_no }")+'</label></td>'
			+ '<td style="width: 10%;"> <label id="pan_no_lb'+x+'">'+"${j.pan_no }"+'</label></td>'
			
			+ '<td style="display:none;"><input type="text" id="sib_ch_id'+x+'" name="sib_ch_id'+x+'"   value="'+"${j.id }"+'"  class="form-control autocomplete" autocomplete="off" ></td>'
			
			+ '<td style="width: 5%;"> ' + '	<label class=" form-control-label" id="child_service' + x + '"> <select name="chi_service' + x + '" id="chi_service' + x + '"   >' + '<option value="0">--Select--</option>' + '<c:forEach var="item" items="${getExservicemenList}" varStatus="num">' + '<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' + '</c:forEach></select></td>'
			+ '<td style="width: 10%;"> ' + '	<label class=" form-control-label"  id="child_personal_no' + x + '" name="child_personal_no' + x + '" ' + '	>' +  + '</label> ' + '	</td>'
			+ '<td style="width: 15%;"> ' + '	<label class=" form-control-label"  id="other_child_ser' + x + '" name="other_child_ser' + x + '" ' + '	>' + + '</label> ' + '	</td>'
			
			+'</tr>');
		$('#sib_relationship' + x).val('${j.relationship }');
		$('#sib_relationship_lb' + x).text($('#sib_relationship'+ x+' option:selected').text());
		 if('${j.child_service}' == 0){
				$('#chi_service' + x).val("");
			}else{
				$('#chi_service' + x).val('${j.child_service}');
			}
			$('#child_personal_no' + x).text('${j.child_personal_no}');
			$('#other_child_ser' + x).text('${j.other_child_ser}');
			$('#child_service' + x).text($("#chi_service" + x +" option:selected").text());
		//$('#sib_adopted' + x).val(j.adoted);
		if('${j.type }' =="Yes"){
			$('#sib_type_lb' + x).text("Yes");
		}
		else
			$('#sib_type_lb' + x).text("No");
		
		if('${j.adoted }' =="Yes"){
			$('#sib_adopted_lb' + x).text("Yes");
		
		}
		else
			$('#sib_adopted_lb' + x).text("No");
		
		
			
		</c:forEach>	
			
			
		
		
}



function get_nokaddress_details(){
	$.ajaxSetup({
		async : false
	});

	<c:if test="${NOK.size() != 0}">
				$("#nok_authority").text('${NOK.get(0).authority}') ;
				$('#nok_date_authority').text(convertDateFormate('${NOK.get(0).date_authority}') ); 
				$("#nok_name").text('${NOK.get(0).nok_name}' );
				
				$('#nok_relationSelect').val('${NOK.get(0).nok_relation}' );
				$('#nok_relation').text($( "#nok_relationSelect option:selected" ).text());
				if('${NOK.get(0).relation_other}'!=null && '${NOK.get(0).relation_other}'!='' && ($('#nok_relation').text().toUpperCase()==other || $('#nok_relation').text().toUpperCase()==others)){
					$('#nok_relation').text('${NOK.get(0).relation_other}');
				}
				$('#nok_countrySelect').val('${NOK.get(0).nok_country}');
				$('#nok_country').text($( "#nok_countrySelect option:selected" ).text());
				if('${NOK.get(0).ctry_other}'!=null && '${NOK.get(0).ctry_other}'!='' && ($('#nok_country').text().toUpperCase()==other || $('#nok_country').text().toUpperCase()==others)){
					$('#nok_country').text('${NOK.get(0).ctry_other}');
				}
				fn_nok_Country();
				$('#nok_stateSelect').val('${NOK.get(0).nok_state}');
				$('#nok_state').text($( "#nok_stateSelect option:selected" ).text());
				if('${NOK.get(0).st_other}'!=null && '${NOK.get(0).st_other}'!='' && ($('#nok_state').text().toUpperCase()==other || $('#nok_state').text().toUpperCase()==others)){
					$('#nok_state').text('${NOK.get(0).st_other}');
				}
				fn_nok_state();
				$('#nok_districtSelect').val('${NOK.get(0).nok_district}');
				$('#nok_district').text($( "#nok_districtSelect option:selected" ).text());
				if('${NOK.get(0).dist_other}'!=null && '${NOK.get(0).dist_other}'!='' && ($('#nok_district').text().toUpperCase()==other || $('#nok_district').text().toUpperCase()==others)){
					$('#nok_district').text('${NOK.get(0).dist_other}');
				}
				fn_nok_district();
				$('#nok_tehsilSelect').val('${NOK.get(0).nok_tehsil}') ;
				$('#nok_tehsil').text($( "#nok_tehsilSelect option:selected" ).text());
				if('${NOK.get(0).tehsil_other}'!=null && '${NOK.get(0).tehsil_other}'!='' && ($('#nok_tehsil').text().toUpperCase()==other || $('#nok_tehsil').text().toUpperCase()==others)){
					$('#nok_tehsil').text('${NOK.get(0).tehsil_other}');
				}
				$('#nok_village').text('${NOK.get(0).nok_village}') ;
				
				
				
				$("#nok_pin").text('${NOK.get(0).nok_pin}') ;
				$("#nok_rail").text('${NOK.get(0).nok_near_railway_station}' );
				$("#nok_mobile_no").text('${NOK.get(0).nok_mobile_no}');
				 var nok= '${NOK.get(0).nok_rural_urban_semi}';
		            if(nok == "rural"){
		                   $("#nok_rural_urban").text("Rural");
			            } 
			           if(nok == "urban"){
			           	 $("#nok_rural_urban").text("Urban");
			            }
			           if(nok == "semi_urban")
			           {
			           	 $("#nok_rural_urban").text("Semi Urban");
			            } 
				$("#nok_id").val('${NOK.get(0).id}');
				</c:if>
			
	}
	
function fn_nok_Country() {
	 var contry_id =$('select#nok_countrySelect').val();

	 		 	$.post("getStateListFormcon1?"+key+"="+value, {contry_id:contry_id}).done(function(j) {
	 		 				 	
	 		 			 	
	 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
	 				for ( var i = 0; i < j.length; i++) {
	 				
	 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
	 					
	 				}
	 				
	 				$("select#nok_stateSelect").html(options);
					/* fn_nok_state();
					fn_nok_district(); */
	 			}).fail(function(xhr, textStatus, errorThrown) {
	 	});
}



function fn_nok_state() {
var state_id = $('select#nok_stateSelect').val();

		 	$.post("getDistrictListFormState1?"+key+"="+value, {state_id:state_id}).done(function(j) {
		 				 	
		 			 	
		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
				for ( var i = 0; i < j.length; i++) {
				
						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
					
				}
				
				$("select#nok_districtSelect").html(options);
				//fn_nok_district();
			}).fail(function(xhr, textStatus, errorThrown) {
	});
}	

function fn_nok_district() {
var Dist_id = $('select#nok_districtSelect').val();

		 	$.post("getTehsilListFormDistrict1?"+key+"="+value, {Dist_id:Dist_id}).done(function(j) {
		 				 	
		 			 	
		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
				for ( var i = 0; i < j.length; i++) {
				
						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
					
				}
				
				$("select#nok_tehsilSelect").html(options);

			}).fail(function(xhr, textStatus, errorThrown) {
	});
}

function get_changeaddress_details(){
	$.ajaxSetup({
			async : false
		});

	<c:if test="${ChangeAdd.size() != 0}">
	
	
			$("#pre_country_val").val('${ChangeAdd.get(0).pre_country}');
			$('#pre_country').text($( "#pre_country_val option:selected" ).text());
			if('${ChangeAdd.get(0).pre_country_other}'!=null && '${ChangeAdd.get(0).pre_country_other}'!='' && ($('#pre_country').text().toUpperCase()==other || $('#pre_country').text().toUpperCase()==others)){
				$('#pre_country').text('${ChangeAdd.get(0).pre_country_other}');
			}
			fn_pre_domicile_Country();
			$("#pre_domicile_state_val").val('${ChangeAdd.get(0).pre_state}' );
			$('#pre_domicile_state').text($( "#pre_domicile_state_val option:selected" ).text());
			if('${ChangeAdd.get(0).pre_domicile_state_other}'!=null && '${ChangeAdd.get(0).pre_domicile_state_other}'!='' && ($('#pre_domicile_state').text().toUpperCase()==other || $('#pre_domicile_state').text().toUpperCase()==others)){
				$('#pre_domicile_state').text('${ChangeAdd.get(0).pre_domicile_state_other}');
			}
			fn_pre_domicile_state();
			$("#pre_domicile_district_val").val( '${ChangeAdd.get(0).pre_district}' );
			$('#pre_domicile_district').text($( "#pre_domicile_district_val option:selected" ).text());
			if('${ChangeAdd.get(0).pre_domicile_district_other}'!=null && '${ChangeAdd.get(0).pre_domicile_district_other}'!='' && ($('#pre_domicile_district').text().toUpperCase()==other || $('#pre_domicile_district').text().toUpperCase()==others) ){
				$('#pre_domicile_district').text('${ChangeAdd.get(0).pre_domicile_district_other}');
			}
			fn_pre_domicile_district();
			$("#pre_domicile_tesil_val").val('${ChangeAdd.get(0).pre_tesil}' );
			$('#pre_domicile_tesil').text($( "#pre_domicile_tesil_val option:selected" ).text());
			if('${ChangeAdd.get(0).pre_domicile_tesil_other}'!=null && '${ChangeAdd.get(0).pre_domicile_tesil_other}'!='' && ($('#pre_domicile_tesil').text().toUpperCase()==other || $('#pre_domicile_tesil').text().toUpperCase()==others)){
				$('#pre_domicile_tesil').text('${ChangeAdd.get(0).pre_domicile_tesil_other}');
			}
			
			$("#addr_authority").text('${ChangeAdd.get(0).authority}');
			$('#addr_date_authority').text(convertDateFormate('${ChangeAdd.get(0).date_authority}')); 
			
			$("#per_home_addr_country_val").val('${ChangeAdd.get(0).permanent_country}');
			$('#per_home_addr_country').text($( "#per_home_addr_country_val option:selected" ).text());
			fn_per_home_addr_Country();
			if('${ChangeAdd.get(0).per_home_addr_country_other}'!=null && '${ChangeAdd.get(0).per_home_addr_country_other}'!='' && ($('#per_home_addr_country').text().toUpperCase()==other || $('#per_home_addr_country').text().toUpperCase()==others)){
				$('#per_home_addr_country').text('${ChangeAdd.get(0).per_home_addr_country_other}');
			}
			
			$("#per_home_addr_state_val").val('${ChangeAdd.get(0).permanent_state}' );
			$('#per_home_addr_state').text($( "#per_home_addr_state_val option:selected" ).text());
			fn_per_home_addr_state();
			if('${ChangeAdd.get(0).per_home_addr_state_other}'!=null && '${ChangeAdd.get(0).per_home_addr_state_other}'!='' && ($('#per_home_addr_state').text().toUpperCase()==other || $('#per_home_addr_state').text().toUpperCase()==others)){
				$('#per_home_addr_state').text('${ChangeAdd.get(0).per_home_addr_state_other}');
			}
			
			$("#per_home_addr_district_val").val('${ChangeAdd.get(0).permanent_district}');
			$('#per_home_addr_district').text($( "#per_home_addr_district_val option:selected" ).text());
			fn_per_home_addr_district();
			if('${ChangeAdd.get(0).per_home_addr_district_other}'!=null && '${ChangeAdd.get(0).per_home_addr_district_other}'!='' && ($('#per_home_addr_district').text().toUpperCase()==other || $('#per_home_addr_district').text().toUpperCase()==others)){
				$('#per_home_addr_district').text('${ChangeAdd.get(0).per_home_addr_district_other}');
			}
			$("#per_home_addr_tehsil_val").val('${ChangeAdd.get(0).permanent_tehsil}' );
			$('#per_home_addr_tehsil').text($( "#per_home_addr_tehsil_val option:selected" ).text());
			if('${ChangeAdd.get(0).per_home_addr_tehsil_other}'!=null && '${ChangeAdd.get(0).per_home_addr_tehsil_other}'!='' && ($('#per_home_addr_tehsil').text().toUpperCase()==other || $('#per_home_addr_tehsil').text().toUpperCase()==others)){
				$('#per_home_addr_tehsil').text('${ChangeAdd.get(0).per_home_addr_tehsil_other}');
			}
			
			$("#per_home_addr_village").text('${ChangeAdd.get(0).permanent_village}');
			
			$("#per_home_addr_pin").text('${ChangeAdd.get(0).permanent_pin_code}' );
			$("#per_home_addr_rail").text('${ChangeAdd.get(0).permanent_near_railway_station}' );
			
            var permanent= '${ChangeAdd.get(0).permanent_rural_urban_semi}';
            if(permanent == "rural"){
                    $("#perm_home_addr").text("Rural");
             } 
            if(permanent == "urban"){
            	 $("#perm_home_addr").text("Urban");
             }
            if(permanent == "semi_urban")
            {
            	 $("#perm_home_addr").text("Semi Urban");
             }  
            var br= '${ChangeAdd.get(0).permanent_border_area}';
            if(br == "yes"){
                    $("#per_border_area").text("Yes");
             } 
            if(br == "no"){
            	$("#per_border_area").text("No");
             } 
	 
            $("#pers_addr_country_val").val('${ChangeAdd.get(0).present_country}' );
        	$('#pers_addr_country').text($( "#pers_addr_country_val option:selected" ).text());
        	if('${ChangeAdd.get(0).pers_addr_country_other}'!=null && '${ChangeAdd.get(0).pers_addr_country_other}'!='' && ($('#pers_addr_country').text().toUpperCase()==other || $('#pers_addr_country').text().toUpperCase()==others)){
				$('#pers_addr_country').text('${ChangeAdd.get(0).pers_addr_country_other}');
			}
        	fn_pers_addr_Country();
        	
			$("#pers_addr_state_val").val('${ChangeAdd.get(0).present_state}' );
			$('#pers_addr_state').text($("#pers_addr_state_val option:selected").text());
			if('${ChangeAdd.get(0).pers_addr_state_other}'!=null && '${ChangeAdd.get(0).pers_addr_state_other}'!='' && ($('#pers_addr_state').text().toUpperCase()==other || $('#pers_addr_state').text().toUpperCase()==others)){
				$('#pers_addr_state').text('${ChangeAdd.get(0).pers_addr_state_other}');
			}
			fn_pers_addr_state();
			$("#pers_addr_district_val").val('${ChangeAdd.get(0).present_district}' );
			$('#pers_addr_district').text($( "#pers_addr_district_val option:selected" ).text());
			if('${ChangeAdd.get(0).pers_addr_district_other}'!=null && '${ChangeAdd.get(0).pers_addr_district_other}'!='' && ($('#pers_addr_district').text().toUpperCase()==other || $('#pers_addr_district').text().toUpperCase()==others)){
				$('#pers_addr_district').text('${ChangeAdd.get(0).pers_addr_district_other}');
			}
			fn_pers_addr_district();
			$("#pers_addr_tehsil_val").val('${ChangeAdd.get(0).present_tehsil}');
			$('#pers_addr_tehsil').text($( "#pers_addr_tehsil_val option:selected" ).text());
			if('${ChangeAdd.get(0).pers_addr_tehsil_other}'!=null && '${ChangeAdd.get(0).pers_addr_tehsil_other}'!='' && ($('#pers_addr_tehsil').text().toUpperCase()==other || $('#pers_addr_tehsil').text().toUpperCase()==others)){
				$('#pers_addr_tehsil').text('${ChangeAdd.get(0).pers_addr_tehsil_other}');
			}
			
			$("#pers_addr_village").text('${ChangeAdd.get(0).present_village}' );
			
			$("#pers_addr_pin").text('${ChangeAdd.get(0).present_pin_code}' );
			$("#pers_addr_rail").text('${ChangeAdd.get(0).present_near_railway_station}' );
			var present='${ChangeAdd.get(0).present_rural_urban_semi}'  ;
			
			   if(present == "rural"){
                   $("#pers_addr_rural_urban").text("Rural");
	            } 
	           if(present == "urban"){
	           	 $("#pers_addr_rural_urban").text("Urban");
	            }
	           if(present == "semi_urban")
	           {
	           	 $("#pers_addr_rural_urban").text("Semi Urban");
	            } 


			$("#addr_ch_id").val('${ChangeAdd.get(0).id}' );
			if( '${ChangeAdd.get(0).spouse_country}' != 0){
				$("#spouse_addressInnerdiv").show();
				$("#spouse_addr_Country").val('${ChangeAdd.get(0).spouse_country}' );
				$('#spouse_addr_Countrylb').text($( "#spouse_addr_Country option:selected" ).text());
				fn_spouse_addr_Country();
				if('${ChangeAdd.get(0).spouse_addr_country_other}'!=null && '${ChangeAdd.get(0).spouse_addr_country_other}'!='' && ($('#spouse_addr_Countrylb').text().toUpperCase()==other || $('#spouse_addr_Countrylb').text().toUpperCase()==others)){
					$('#spouse_addr_Countrylb').text('${ChangeAdd.get(0).spouse_addr_country_other}');
				}
				$("#spouse_addr_state").val('${ChangeAdd.get(0).spouse_state}' );
				
				$('#spouse_addr_statelb').text($( "#spouse_addr_state option:selected" ).text());
				fn_spouse_addr_state();
				if('${ChangeAdd.get(0).spouse_addr_state_other}'!=null && '${ChangeAdd.get(0).spouse_addr_state_other}'!='' && ($('#spouse_addr_statelb').text().toUpperCase()==other || $('#spouse_addr_statelb').text().toUpperCase()==others)){
					$('#spouse_addr_statelb').text('${ChangeAdd.get(0).spouse_addr_state_other}');
				}
				$("#spouse_addr_district").val('${ChangeAdd.get(0).spouse_district}' );
				$('#spouse_addr_districtlb').text($( "#spouse_addr_district option:selected" ).text());
				fn_spouse_addr_district();
				if('${ChangeAdd.get(0).spouse_addr_district_other}'!=null && '${ChangeAdd.get(0).spouse_addr_district_other}'!='' && ($('#spouse_addr_districtlb').text().toUpperCase()==other || $('#spouse_addr_districtlb').text().toUpperCase()==others)){
					$('#spouse_addr_districtlb').text('${ChangeAdd.get(0).spouse_addr_district_other}');
				}
				$("#spouse_addr_tehsil").val('${ChangeAdd.get(0).spouse_tehsil}' );
				$('#spouse_addr_tehsillb').text($( "#spouse_addr_tehsil option:selected" ).text());
				if('${ChangeAdd.get(0).spouse_addr_tehsil_other}'!=null && '${ChangeAdd.get(0).spouse_addr_tehsil_other}'!='' && ($('#spouse_addr_tehsillb').text().toUpperCase()==other || $('#spouse_addr_tehsillb').text().toUpperCase()==others)){
					$('#spouse_addr_tehsillb').text('${ChangeAdd.get(0).spouse_addr_tehsil_other}');
				}
				$("#spouse_addr_village").text('${ChangeAdd.get(0).spouse_village}');
				$("#spouse_addr_pin").text('${ChangeAdd.get(0).spouse_pin_code}' );
				$("#spouse_addr_rail").text('${ChangeAdd.get(0).spouse_near_railway_station}' );
				$("#Stn_HQ_sus_no").text('${ChangeAdd.get(0).stn_hq_sus_no}');
				var sus_no = '${ChangeAdd.get(0).stn_hq_sus_no}' ;			      	
				 $.post("getTargetUnitNameListStnHQ?"+key+"="+value, {
						 sus_no:sus_no
		         }, function(j) {
		                
		         }).done(function(j) {
		        	 var length = j.length-1;
		             var enc = j[length].substring(0,16);
		             $("#Stn_HQ_unit_name").text(dec(enc,j[0]));   
		                 
		         }).fail(function(xhr, textStatus, errorThrown) {
	        	 });
				
				var spouse_rus='${ChangeAdd.get(0).spouse_rural_urban_semi}'  ;
				
				   if(spouse_rus == "rural"){
	                   $("#spouse_addr_rural_urban").text("Rural");
		            } 
		           if(spouse_rus == "urban"){
		           	 $("#spouse_addr_rural_urban").text("Urban");
		            }
		           if(spouse_rus == "semi_urban")
		           {
		           	 $("#spouse_addr_rural_urban").text("Semi Urban");
		            } 
			}
			
			</c:if>
		
}
function fn_spouse_addr_Country() {
	 var contry_id = $('select#spouse_addr_Country').val();

	 		 	$.post("getStateListFormcon1?"+key+"="+value, {contry_id:contry_id}).done(function(j) {
	 		 				 	
	 		 			 	
	 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
	 				for ( var i = 0; i < j.length; i++) {
	 				
	 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
	 					
	 				}
	 				
	 				$("select#spouse_addr_state").html(options);
					/* fn_spouse_addr_state();
					fn_spouse_addr_district(); */
	 			}).fail(function(xhr, textStatus, errorThrown) {
	 	});
}

function fn_spouse_addr_state() {
	 var state_id = $('select#spouse_addr_state').val();

	 		 	$.post("getDistrictListFormState1?"+key+"="+value, {state_id:state_id}).done(function(j) {
	 		 				 	
	 		 			 	
	 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
	 				for ( var i = 0; i < j.length; i++) {
	 					

	 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
	 				
	 				}
	 				
	 				$("select#spouse_addr_district").html(options);
					//fn_spouse_addr_district();
	 			}).fail(function(xhr, textStatus, errorThrown) {
	 	});
}

function fn_spouse_addr_district() {
	 var Dist_id = $('select#spouse_addr_district').val();

	 		 	$.post("getTehsilListFormDistrict1?"+key+"="+value, {Dist_id:Dist_id}).done(function(j) {
	 		 				 	
	 		 			 	
	 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
	 				for ( var i = 0; i < j.length; i++) {
	 				
	 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
	 					
	 				}
	 				
	 				$("select#spouse_addr_tehsil").html(options);

	 			}).fail(function(xhr, textStatus, errorThrown) {
	 	});
	 		 	
}

function fn_pre_domicile_Country() {
	 var contry_id = $('select#pre_country_val').val();

	 		 	$.post("getStateListFormcon1?"+key+"="+value, {contry_id:contry_id}).done(function(j) {
	 		 				 	
	 		 			 	
	 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
	 				for ( var i = 0; i < j.length; i++) {
	 				
	 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
	 					
	 				}
	 				
	 				$("select#pre_domicile_state_val").html(options);
	 				/* fn_pre_domicile_state();
	 				fn_pre_domicile_district(); */
	 			}).fail(function(xhr, textStatus, errorThrown) {
	 	});
}

function fn_pre_domicile_state() {
	 var state_id =$('select#pre_domicile_state_val').val();

	 		 	$.post("getDistrictListFormState1?"+key+"="+value, {state_id:state_id}).done(function(j) {
	 		 				 	
	 		 			 	
	 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
	 				for ( var i = 0; i < j.length; i++) {
	 				
	 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
	 					
	 				}
	 				
	 				$("select#pre_domicile_district_val").html(options);
	 				/* fn_pre_domicile_district(); */
	 			}).fail(function(xhr, textStatus, errorThrown) {
	 	});
}

function fn_pre_domicile_district() {
	 var Dist_id = $('select#pre_domicile_district_val').val();

	 		 	$.post("getTehsilListFormDistrict1?"+key+"="+value, {Dist_id:Dist_id}).done(function(j) {
	 		 				 	
	 		 			 	
	 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
	 				for ( var i = 0; i < j.length; i++) {
	 				
	 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
	 					
	 				}
	 				
	 				$("select#pre_domicile_tesil_val").html(options);

	 			}).fail(function(xhr, textStatus, errorThrown) {
	 	});
}

function fn_per_home_addr_Country() {
	 var contry_id = $('select#per_home_addr_country_val').val();

	 		 	$.post("getStateListFormcon1?"+key+"="+value, {contry_id:contry_id}).done(function(j) {
	 		 				 	
	 		 			 	
	 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
	 				for ( var i = 0; i < j.length; i++) {
	 				
	 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
	 					
	 				}
	 				
	 				$("select#per_home_addr_state_val").html(options);
	 				/* fn_per_home_addr_state();
	 				fn_per_home_addr_district(); */
	 			}).fail(function(xhr, textStatus, errorThrown) {
	 	});
}

function fn_per_home_addr_state() {
	 var state_id =$('select#per_home_addr_state_val').val(); 

	 		 	$.post("getDistrictListFormState1?"+key+"="+value, {state_id:state_id}).done(function(j) {
	 		 				 	
	 		 			 	
	 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
	 				for ( var i = 0; i < j.length; i++) {
	 			
	 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
	 					
	 				}
	 				
	 				$("select#per_home_addr_district_val").html(options);
	 				/* fn_per_home_addr_district(); */
	 			
	 			}).fail(function(xhr, textStatus, errorThrown) {
	 	});
}

function fn_per_home_addr_district() {
	 var Dist_id = $('select#per_home_addr_district_val').val();

	 		 	$.post("getTehsilListFormDistrict1?"+key+"="+value, {Dist_id:Dist_id}).done(function(j) {
	 		 				 	
	 		 			 	
	 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
	 				for ( var i = 0; i < j.length; i++) {
	 				
	 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
	 					
	 				}
	 				
	 				$("select#per_home_addr_tehsil_val").html(options);

	 			}).fail(function(xhr, textStatus, errorThrown) {
	 	});
}

function fn_pers_addr_Country() {
	 var contry_id = $('select#pers_addr_country_val').val();

	 		 	$.post("getStateListFormcon1?"+key+"="+value, {contry_id:contry_id}).done(function(j) {
	 		 				 	
	 		 			 	
	 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
	 				for ( var i = 0; i < j.length; i++) {
	 				
	 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
	 					
	 				}
	 				
	 				$("select#pers_addr_state_val").html(options);
					/* fn_pers_addr_state();
					fn_pers_addr_district(); */
	 			}).fail(function(xhr, textStatus, errorThrown) {
	 	});
}

function fn_pers_addr_state() {
	 var state_id = $('select#pers_addr_state_val').val();

	 		 	$.post("getDistrictListFormState1?"+key+"="+value, {state_id:state_id}).done(function(j) {
	 		 				 	
	 		 			 	
	 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
	 				for ( var i = 0; i < j.length; i++) {
	 					

	 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
	 				
	 				}
	 				
	 				$("select#pers_addr_district_val").html(options);
					/* fn_pers_addr_district(); */
	 			}).fail(function(xhr, textStatus, errorThrown) {
	 	});
}
function fn_pers_addr_district() {
	 var Dist_id = $('select#pers_addr_district_val').val();

	 		 	$.post("getTehsilListFormDistrict1?"+key+"="+value, {Dist_id:Dist_id}).done(function(j) {
	 		 				 	
	 		 			 	
	 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
	 				for ( var i = 0; i < j.length; i++) {
	 				
	 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
	 					
	 				}
	 				
	 				$("select#pers_addr_tehsil_val").html(options);

	 			}).fail(function(xhr, textStatus, errorThrown) {
	 	});
	 		 	
}

function get_cda_acnt_no(){

	<c:if test="${CDAaccount.size() != 0}">
			$("#gmail").text('${CDAaccount.get(0).gmail}');
			$("#nic_mail").text('${CDAaccount.get(0).nic_mail}' );
			$("#mobile_no").text('${CDAaccount.get(0).mobile_no}');
			$("#cda_id").val('${CDAaccount.get(0).id}' );
		</c:if>
}


function get_language_details(){
	
	
	xl=0;
	xf=0;

		
		
		<c:forEach var="j" items="${Language}" varStatus="num">
		
		if('${j.language}'!="0"){
			xl=xl+1;
			if(xl==1){
				$('#langtbody').empty(); 
			}
		
			$("table#language_table").append('<tr id="tr_lang'+xl+'">'
			+'<td class="lang_srno">'+xl+'</td>'
			+'<td style="width: 10%;"> <label id="language'+xl+'"></label>'
			+'<select  id="language_val'+xl+'" name="language'+xl+'" value="'+"${j.language}"+'"  class="form-control"  style="display:none;">'
			  +' <option value="0">--Select--</option>'
			   +'<c:forEach var="item" items="${getIndianLanguageList}" varStatus="num">'
				+'<option value="${item[0]}" name="${item[1]}">${item[1]}</option>'
				+'</c:forEach>'
				+'</select>'
				+'</td>'
			+'<td style="width: 10%;"> <label id="lang_std'+xl+'"></label> '
			+'<select name="lang_std'+xl+'" id="lang_std_val'+xl+'" class="form-control" style="display:none;">'
			+' <option value="0">--Select--</option>'
			+'	<c:forEach var="item" items="${getLanguageSTDList}" varStatus="num">'
			+'	<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' 
			+'	</c:forEach>'
			+'</select> </td> '
			/* +'<td style="width: 10%;">'
			+'<label id="language_authority'+xl+'"></label></td> ' */
			
			+'</tr>');
			 $('#lang_std_val'+xl).val('${j.lang_std}' );
			 $('#lang_std'+xl).text($( "#lang_std_val"+xl+" option:selected" ).text());
			 if('${j.other_lang_std}'!=null && '${j.other_lang_std}'!='' && ( $('#lang_std'+xl).text().toUpperCase()==other ||  $('#lang_std'+xl).text().toUpperCase()==others)){
				 $('#lang_std'+xl).text('${j.other_lang_std}');
				}
			 $('#language_val'+xl).val('${j.language}' );
			 
			 $('#language'+xl).text($( "#language_val"+xl+" option:selected" ).text());
			 if('${j.other_language}'!=null && '${j.other_language}'!='' && ( $('#language'+xl).text().toUpperCase()==other ||  $('#language'+xl).text().toUpperCase()==others)){
				 $('#language'+xl).text('${j.other_language}');
				}
			 $('#language_authority').text('${j.authority}' );
			 $('#language_date_of_authority').text(convertDateFormate('${j.date_of_authority}'));
		}
		else{
			xf=xf+1;
			if(xf==1){
				$('#flangtbody').empty(); 
			}
			$("table#foregin_language_table").append('<tr id="tr_flang'+xf+'">'
					+'<td class="flang_srno">'+xf+'</td>'
					+'<td style="width: 10%;"> <label id="flanguage'+xf+'"></label>'
					+'<select  id="flanguage_val'+xf+'" name="flanguage'+xf+'" class="form-control" style="display:none;" >'
					+'   <option value="0">--Select--</option>'
				    +'	<c:forEach var="item" items="${getForiegnLangugeList}" varStatus="num">'
					+'	<option value="${item[0]}" name="${item[1]}">${item[1]}</option>'
					+'	</c:forEach>'
					+'	</select></td>'
					+'<td style="width: 10%;"> <label id="flang_std'+xf+'"></label>'
					+'<select name="flang_std'+xf+'" id="flang_std_val'+xf+'" class="form-control" style="display:none;">'
					+' <option value="0">--Select--</option>'
					+'	<c:forEach var="item" items="${getLanguageSTDList}" varStatus="num">'
					+'	<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' 
					+'	</c:forEach>'
					+'</select> </td> '
					+'<td style="width: 10%;"> <label id="lang_prof'+xf+'"></label>'
					+'<select name="lang_prof'+xf+'" id="lang_prof_val'+xf+'" class="form-control"  style="display:none;" >'
					+' <option value="0">--Select--</option>'
					+'	<c:forEach var="item" items="${getLanguagePROOFList}" varStatus="num">'
					+'	<option value="${item[0]}" name="${item[1]}">${item[1]}</option>'
					+'	</c:forEach>'
					+' </select></td> '
					+'<td style="width: 10%;"> <label id="exam_pass'+xf+'"></label></td>'		
					+'</tr>');
			$('#flang_std_val'+xf).val('${j.lang_std}' );
			 $('#flang_std'+xf).text($( "#flang_std_val"+xf+" option:selected" ).text());
			 
			 if('${j.f_other_lang_std}'!=null && '${j.f_other_lang_std}'!='' && ($('#flang_std'+xf).text().toUpperCase()==other || $('#flang_std'+xf).text().toUpperCase()==others)){
				 $('#flang_std'+xf).text('${j.f_other_lang_std}');
				}
			 
			 $('#lang_prof_val'+xf).val('${j.f_lang_prof}'  );  
			 $('#lang_prof'+xf).text($( "#lang_prof_val"+xf+" option:selected" ).text());
			 if('${j.f_other_prof}'!=null && '${j.f_other_prof}'!='' && ($('#lang_prof'+xf).text().toUpperCase()==other || $('#lang_prof'+xf).text().toUpperCase()==others)){
				 $('#lang_prof'+xf).text('${j.f_other_prof}');
				}
			 
			 $('#flanguage_val'+xf).val('${j.foreign_language}' );
			 $('#flanguage'+xf).text($( "#flanguage_val"+xf+" option:selected" ).text());
			 if('${j.f_other_language}'!=null && '${j.f_other_language}'!='' && ($('#flanguage'+xf).text().toUpperCase()==other || $('#flanguage'+xf).text().toUpperCase()==others)){
				 $('#flanguage'+xf).text('${j.f_other_language}');
				}
			 
			 $('#exam_pass'+xf).text('${j.f_exam_pass}' );
		}
	
		</c:forEach>
}

function getQualification_Reject(){
	var ch_id='${census_id}'; 
	 var comm_id = '${comm_id}';
	 
	 $.post('getQualification_Reject?' + key + "=" + value, {ch_id:ch_id,comm_id:comm_id}, function(j){
		 
		 if(j!=""){
			 alert(j);
		 }
	 });
	} 
	
function getQualifications(){
	 var qu=0;
		$.ajaxSetup({
			async : false
		});
	 $('#qualificationtbody').empty(); 
	<c:forEach var="j" items="${Qualification}" varStatus="num"> 
			qu=qu+1;
			
				
			var examother="--";
			var classother="--";
			var deg_other="--";
			var spec_other="--";
			var exampass = "--";				
			var deg_name = "--";
			var specialization = "--";
			
			$('#quali_type').val("${j.type}");
			
			var typethisT = document.getElementById('quali_type');
			fn_qualification_typeChange(typethisT,'quali','quali_degree','specialization');
		
			$('#quali').val("${j.examination_pass}");
			var qualithisT = document.getElementById('quali');
			fn_ExaminationTypeChange(qualithisT,'quali_degree','specialization');
			$('#quali_degree').val("${j.degree}");
			
			if('${j.examination_pass}' != null) exampass = $('#quali option[value="'+"${j.examination_pass}" +'"]').text();
		
			if('${j.degree}' != null) deg_name =$('#quali_degree option[value="'+"${j.degree}" +'"]').text();;
			if('${j.specialization}' != null) specialization = $('#specialization option[value="'+"${j.specialization}" +'"]').text();
			
			if('${j.exam_other}' !=null && '${j.exam_other}' !=''){
				examother='${j.exam_other}';
				exampass='${j.exam_other}';
			}
	  	    
	  	  if('${j.degree_other}' !=null && '${j.degree_other}' !=''){
	  		deg_name='${j.degree_other}';
	  		deg_other='${j.degree_other}';
	  	  }
	  	   if('${j.specialization_other}'!=null && '${j.specialization_other}'!='')
	  		   {
	  		 specialization='${j.specialization_other}';
	  		spec_other='${j.specialization_other}';
	  		   }
	  	    
	  	   
			var qulitype=$('#quali_type option[value="'+"${j.type}" +'"]').text();
			var divclass=$('#div_class option[value="'+"${j.div_class}"+'"]').text();
			 if('${j.class_other}' !=null && '${j.class_other}' !=''){
		  	    	classother='${j.class_other}';
		  	    	divclass='${j.class_other}';
			 }
			var  date_of_authority="";
			if('${j.date_of_authority}'!= null){
				var  date_of_authority = convertDateFormate('${j.date_of_authority}' );
				}
			var subjectslist='${j.subject}'.split(',');
			var subjects="";
			for(k=0;k<subjectslist.length;k++){
				if(k!=0)
					subjects+=",";

				subjects+=$("input[type=checkbox][name='multisub'][value='"+subjectslist[k]+"']").parent().text();
				
			}		
		
		
		
			 
		  $("table#qualificationtable").append('<tr>'
					+'<td style="font-size: 15px;text-align: center ;width: 10%;">'+qu+'</td> '
					+'<td style="font-size: 15px;text-align: center">'+"${j.authority}"+'</td>	'	
					+'<td style="font-size: 15px;text-align: center">'+date_of_authority+'</td>	'	
					+'<td style="font-size: 15px;text-align: center">'+qulitype+'</td>	'									
					+'<td style="font-size: 15px;text-align: center">'+exampass+'</td> '
					+'<td style="font-size: 15px;text-align: center">'+deg_name +'</td>'		
							
					+'<td style="font-size: 15px;text-align: center">'+specialization+'</td>'		
					+'<td style="font-size: 15px;text-align: center">'+'${j.passing_year}' +'</td>'
					+'<td style="font-size: 15px;text-align: center">'+subjects +'</td>'		
					+'<td style="font-size: 15px;text-align: center">'+divclass+'</td>'		
					+'<td style="font-size: 15px;text-align: center">'+'${j.institute}'+'</td>'
					
				+'</tr>');
		  
		  </c:forEach> 
	 
		}
		
		
function bpet_Details() {
	
	var bt = 0;

		

	
			$('#bpettbody').empty();
			<c:forEach var="j" items="${BEPT}" varStatus="num"> 
				bt = bt + 1;
				$("table#bpet_table").append('<tr id="tr_bpet'+bt+'">'
					+'<td class="bpet_srno">'+bt+'</td>'
					+'<td style="width: 10%;"> <label id="BPET_result'+bt+'"></label><select style="display:none" name="BPET_resultSelect'+bt+'" id="BPET_resultSelect'+bt+'" class="form-control"><option value="0">--Select--</option><c:forEach var="item" items="${getBPET_result}" varStatus="num"><option value="${item[0]}" name="${item[1]}">${item[1]}</option></c:forEach></select></td>'
					+'<td style="width: 10%;"> <label id="BPET_qe'+bt+'"></label><select style="display:none" name="BPET_qeSelect'+bt+'" id="BPET_qeSelect'+bt+'" class="form-control"><option value="0">--Select--</option><c:forEach var="item" items="${getBPET_event_qe}" varStatus="num"><option value="${item[0]}" name="${item[1]}">${item[1]}</option></c:forEach></select></td>'
					+'<td style="width: 10%;"> <label id="year'+bt+'"></label></td>'
					+'<td style="width: 10%;">'
					+'<label id="bpet_unit_name_lb'+bt+'"></label>'
					+'</td>'
				+ '</tr>');
				 $('#BPET_resultSelect'+bt).val('${j.BPET_result}');
     			 $('#BPET_result'+bt).text($( "#BPET_resultSelect"+bt+" option:selected" ).text());
     			 if('${j.bept_result_others}'!=null && '${j.bept_result_others}'!='' && ($('#BPET_result'+bt).text().toUpperCase()==other || $('#BPET_result'+bt).text().toUpperCase()==others)){
    				 $('#BPET_result'+bt).text('${j.bept_result_others}');
    				}
    			 
				 $('#BPET_qeSelect'+bt).val('${j.BPET_qe}');
	     		 $('#BPET_qe'+bt).text($( "#BPET_qeSelect"+bt+" option:selected" ).text());
	     		 $('#year'+bt).text('${j.year}');
	     		var sus_no ='${j.unit_sus_no}';
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
		    	                   $('#bpet_unit_name_lb'+bt).text(dec(enc,j[0])); 
		        }).fail(function(xhr, textStatus, errorThrown) {
		        });
		    } 
		    	</c:forEach >
			
}
 
function fire_Details() {

    var fir = 0;
    $('#fire_stdtbody').empty();
                  	<c:forEach var="j" items="${FiringStan}" varStatus="num"> 
                            fir = fir + 1;
                            $("table#fire_std_table").append('<tr id="tr_fire_std'+fir+'">'
                            +'<td class="fire_srno">'+fir+'</td>'
                            +'<td style="width: 10%;"> <label id="firing_grade'+fir+'"></label><select style="display:none" name="firing_gradeSelect'+fir+'" id="firing_gradeSelect'+fir+'" class="form-control"><option value="0">--Select--</option><c:forEach var="item" items="${getFiring_grade}" varStatus="num"><option value="${item[0]}" name="${item[1]}">${item[1]}</option></c:forEach></select></td>'                                            
                            +'<td style="width: 10%;"> <label id="firing_event_qe'+fir+'"></label><select style="display:none" name="firing_event_qeSelect'+fir+'" id="firing_event_qeSelect'+fir+'" class="form-control"><option value="0">--Select--</option><c:forEach var="item" items="${getFiring_event_qe}" varStatus="num"><option value="${item[0]}" name="${item[1]}">${item[1]}</option></c:forEach></select></td>'
                            +'<td style="width: 10%;"> <label id="fir_year'+fir+'" ></label></td>'
                            
                            + '<td style="width: 10%;">'
                            +'<label id="firing_unit_name_lb'+fir+'"></label>'
							+ '</td>'
							
                       
                            +'</tr>');
                            $('#firing_event_qeSelect'+fir).val('${j.firing_event_qe}');
                			$('#firing_event_qe'+fir).text($( "#firing_event_qeSelect"+fir+" option:selected" ).text());
                            $('#firing_gradeSelect'+fir).val('${j.firing_grade}');
                			$('#firing_grade'+fir).text($( "#firing_gradeSelect"+fir+" option:selected" ).text());
                			if('${j.ot_firing_grade}'!=null && '${j.ot_firing_grade}'!='' && ($('#firing_grade'+fir).text().toUpperCase()==other || $('#firing_grade'+fir).text().toUpperCase()==others)){
               				 $('#firing_grade'+fir).text('${j.ot_firing_grade}');
               				}
                            $('#fir_year'+fir).text('${j.year}');
                            
                            
                          	var sus_no ='${j.firing_unit_sus_no}' ;
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
    	    		    	                   $('#firing_unit_name_lb'+fir).text(dec(enc,j[0])); 
    	    		        }).fail(function(xhr, textStatus, errorThrown) {
    	    		        });
    	    		    } 
    	    		    	
    	    		    	</c:forEach>
                   
}
function getUPForeign_CountriesDetails(){

	 
	 var f=0;
	
		$('#foregin_Country_tbody').empty(); 
				
		<c:forEach var="j" items="${ForeignCountry}" varStatus="num"> 
		
					f=f+1;
						  $("table#foreign_country_visit").append('<tr id="tr_foregin_country'+f+'">'
									+'<td class="fcon_srno">'+f+'</td>'
									+'<td style="width: 10%;"> <label id="country'+f+'"></label><select style="display:none;" name="countrySelect'+f+'" id="countrySelect'+f+'" class="form-control"><option value="0">--Select--</option><c:forEach var="item" items="${getForeign_country}" varStatus="num"><option value="${item[0]}" name="${item[1]}">${item[1]}</option></c:forEach></select></td>'
									+'<td style="width: 10%;"> <label id="foregin_Country_ot'+f+'"></label></td>'
									+'<td style="width: 10%;"> <label id="from_dt'+f+'"></label></td>'
									+'<td style="width: 10%;"> <label id="to_dt'+f+'"></label></td>'
									+'<td style="width: 10%;"> <label id="period'+f+'"></label></td>'
									+'<td style="width: 10%;"> <label id="purpose_visit'+f+'"></label><select style="display:none" name="purpose_visitSelect'+f+'" id="purpose_visitSelect'+f+'" class="form-control"><option value="0">--Select--</option><c:forEach var="item" items="${getForiegnCountryList}" varStatus="num"><option value="${item[0]}" name="${item[1]}">${item[1]}</option></c:forEach></select></td>'
									+'<td style="width: 10%;"> <label id="purpose_visit_ot'+f+'"></label></td>'
									+'</td></tr>');
					  
					  $('#countrySelect'+f).val('${j.country}');
					  $('#country'+f).text($( "#countrySelect"+f+" option:selected" ).text());
					  $("#period"+f).text('${j.period}' );
					  $("#from_dt"+f).text(convertDateFormate('${j.from_dt}'));
					  $("#to_dt"+f).text(convertDateFormate('${j.to_dt}' ));
					  $('#purpose_visitSelect'+f).val('${j.purpose_visit}' );
					  $('#purpose_visit'+f).text($( "#purpose_visitSelect"+f+" option:selected" ).text());
					  $('#foregin_Country_ot'+f).text('${j.other_country}' );
					  $('#purpose_visit_ot'+f).text('${j.other_purpose_visit}' );
		 </c:forEach>
} 

function Award_Reject(){
	var ch_id1 = '${census_id}'; 
	 var comm_id1='${comm_id}';
	 
	  $.post('getaward_Reject?' + key + "=" + value, {ch_id:ch_id1,comm_id:comm_id1}, function(j){
		 if(j!=""){
			 alert(j);
		 }
	 });
	}
function getawardsNmedals(){

   var am=0;
   
                          $('#awardsNmedal_tbody').empty(); 
	<c:forEach var="j" items="${AwardsMedal}" varStatus="num"> 
                  am=am+1;
                
            $("table#awardsNmedal_table").append('<tr id="tr_awardsNmedal'+am+'">'
                                  +'<td class="anm_srno">'+am+'</td>'
                                  +'<td style="width: 10%;">'
                                  +'<label id="awardsNmedal_authority'+am+'" value="'+"${j.authority}"+'"></label</td>'
                                  +'<td style="width: 10%;"><label id="awardsNmedal_date_of_authority'+am+'"></label></td>'
                                  +'<td style="width: 10%;"><label id="Category_8'+am+'"></label>'
                                  +'<select name="Category_8'+am+'" id="Category_8_val'+am+'" onchange="getMedalDescList(this)" class="form-control" style="display:none;"><option value="0">--Select--</option>'
                                  +'<c:forEach var="item" items="${getMedalList}" varStatus="num"><option value="${item[0]}" name="${item[1]}">${item[1]}</option></c:forEach></select>'
                                 
                                  +'<td style="width: 10%;"><label id="select_desc'+am+'"></label>'
                                  +'<select name="select_desc'+am+'" id="select_desc_val'+am+'" class="form-control" style="display:none;"><option value="0">--Select--</option></select>'
                                  
                                  +'<td style="width: 10%;"><label id="date_of_award'+am+'"></label></td>'
                                  +'<td style="width: 10%;"><label id="awardsNmedal_unit'+am+'"></label></td>'
                                  
                                  +'<td style="width: 10%;"><label id="awardsNmedal_bde'+am+'"></label>'
                                  +'<select name="awardsNmedal_bde'+am+'" id="awardsNmedal_bde_val'+am+'"  class="form-control" style="display:none;">'
                                  +'<option value="0">--Select--</option><c:forEach var="item" items="${getPSG_BdeList}" varStatus="num">'
                                  +'<option value="${item[0]}" name="${item[1]}">${item[1]}</option></c:forEach></select></td>'
                                  
                                  +'<td style="width: 10%;"><label id="awardsNmedal_div_subarea'+am+'"></label>'
                                  +'<select name="awardsNmedal_div_subarea'+am+'" id="awardsNmedal_div_subarea_val'+am+'" style="display:none;" class="form-control">'
                                  +'<option value="0">--Select--</option><c:forEach var="item" items="${getPSG_DivList}" varStatus="num">'
                                  +'<option value="${item[0]}" name="${item[1]}">${item[1]}</option></c:forEach></select></td>'
                                  
                                  +'<td style="width: 10%;"><label id="awardsNmedal_corps_area'+am+'"></label>'
                                  +'<select name="awardsNmedal_corps_area'+am+'" id="awardsNmedal_corps_area_val'+am+'"  class="form-control" style="display:none;">'
                                  +'<option value="0">--Select--</option><c:forEach var="item" items="${getPSG_CorpsList}" varStatus="num">'
                                  +'<option value="${item[0]}" name="${item[1]}">${item[1]}</option></c:forEach></select></td>'
                                  
                                  +'<td style="width: 10%;"><label id="awardsNmedal_command'+am+'"></label>'
                                  +'<select name="awardsNmedal_command'+am+'" id="awardsNmedal_command_val'+am+'"  class="form-control" style="display:none;">'
                                  +'<option value="0">--Select--</option><c:forEach var="item" items="${getPSG_CommandList}" varStatus="num">'
                                  +'<option value="${item[0]}" name="${item[1]}">${item[1]}</option></c:forEach></select></td>'                
                                  
                                  +'</tr>');
            $.ajaxSetup({
                          async : false
                  });
            $('#Category_8_val'+am).val( '${j.category_8}');
    		$('#Category_8'+am).text($( "#Category_8_val"+am+" option:selected" ).text());
             getMedalDescList(document.getElementById('Category_8_val'+am));
            $('#select_desc_val'+am).val( '${j.select_desc}' );
            $('#select_desc'+am).text($( "#select_desc_val"+am+" option:selected" ).text());
            $('#awardsNmedal_bde_val'+am).val('${j.bde}' );
            $('#awardsNmedal_bde'+am).text($( "#awardsNmedal_bde_val"+am+" option:selected" ).text());
            $('#awardsNmedal_div_subarea_val'+am).val('${j.div_subarea}'  );
            $('#awardsNmedal_div_subarea'+am).text($( "#awardsNmedal_div_subarea_val"+am+" option:selected" ).text());
            $('#awardsNmedal_corps_area_val'+am).val( '${j.corps_area}' );
            $('#awardsNmedal_corps_area'+am).text($( "#awardsNmedal_corps_area_val"+am+" option:selected" ).text());
            $('#awardsNmedal_command_val'+am).val( '${j.command}' );
            $('#awardsNmedal_command'+am).text($( "#awardsNmedal_command_val"+am+" option:selected" ).text());
            $('#awardsNmedal_authority'+am).text('${j.authority}' );
            $('#awardsNmedal_date_of_authority'+am).text(convertDateFormate('${j.date_of_authority}'));
            $('#date_of_award'+am).text(convertDateFormate('${j.date_of_award}'));
            $('#awardsNmedal_unit'+am).text('${j.unit}' );
            
          </c:forEach>
}
function BattleCasulity_Reject(){
	var ch_id1 = '${census_id}'; 
	 var comm_id1='${comm_id}';
	 
	  $.post('getBattleCasulity_Reject?' + key + "=" + value, {ch_id:ch_id1,comm_id:comm_id1}, function(j){
		 if(j!=""){
			 alert(j);
		 }
	 });
	}

function get_Battle_and_Physical_Casuality_details(){


	<c:if test="${btel_cas.size() != 0}">
	
		$.ajaxSetup({
			async : false
		});
		
		 $("#batnpc_authority").text('${btel_cas.get(0).authority}');
		
		 $("#batnpc_date_of_authority").text(convertDateFormate('${btel_cas.get(0).date_of_authority}'));
		
		 	$('input#classification_of_casuality_hid').val('${btel_cas.get(0).classification_of_casuality}');
			if('${btel_cas.get(0).classification_of_casuality}'  != null){
			
			 var Class_casuality= '${btel_cas.get(0).classification_of_casuality}';
	            if(Class_casuality == "physical_casuality"){
	                   $("#classification_of_casuality").text("Physical Casualty");
		        } 
		        if(Class_casuality == "battle_casuality"){
		           	 $("#classification_of_casuality").text("Battle Casualty");
		        }
		        Casuality_radioApproval();
			}
			
			$('#btnpc_sector').text('${btel_cas.get(0).sector}' );
				$('input#nature_of_casuality').val('${btel_cas.get(0).nature_of_casuality}' );
			if('${btel_cas.get(0).nature_of_casuality}'  != null){
					 var nature_casuality= ['${btel_cas.get(0).nature_of_casuality}' ];
					    if(nature_casuality == "1"){
			                   $("#nature_of_casuality").text("Killed");
				        } 
					    if(nature_casuality == "2"){
				           	 $("#nature_of_casuality").text("Died");
				        } 
					    if(nature_casuality == "3"){
				           	 $("#nature_of_casuality").text("Wounded");
				        } 
					    if(nature_casuality == "4"){
				           	 $("#nature_of_casuality").text("Missing");
				        } 
			} 
		
		 onCauseOfCasualityApprove('${btel_cas.get(0).cause_of_casuality}' );
		
		
		 if('${btel_cas.get(0).classification_of_casuality}'  == 'physical_casuality' && '${btel_cas.get(0).cause_of_casuality}' != "0"){
			if('${btel_cas.get(0).cause_of_casuality}'  != 'OTHERS'){
			
				$("#physical_desc_val").val('${btel_cas.get(0).description}' );
				$('#physical_desc').text($( "#physical_desc_val option:selected" ).text());
				onphysicalDescApprove(document.getElementById('physical_desc_val'));
				if('${btel_cas.get(0).description}'  == 'OTHERS'){
					$("#physical_desc_othersdiv").show();
					$("#physical_desc_others").text('${btel_cas.get(0).desc_others}' );
					}
				}
			else {
				$("#others_physical").text('${btel_cas.get(0).description}' );
				}
			}
		 if('${btel_cas.get(0).classification_of_casuality}'  == 'battle_casuality' && '${btel_cas.get(0).cause_of_casuality}'  != "0"){
			
			if('${btel_cas.get(0).cause_of_casuality}'  != 'OTHERS'){
			
				$("#battle_desc_val").val('${btel_cas.get(0).description}' );
				$('#battle_desc').text($( "#battle_desc_val option:selected" ).text());
				onBattleDescApprove(document.getElementById('battle_desc_val'));
				if( '${btel_cas.get(0).description}'  == 'OTHERS'){
					$("#battle_desc_othersdiv").show();
					$("#battle_desc_others").text('${btel_cas.get(0).desc_others}' );
					}
				}
			else {
				$("#others_battle").text('${btel_cas.get(0).description}' );
				}
			}
		
		
		 $("#batnpc_diagnosis").text('${btel_cas.get(0).diagnosis}' );
		$("#batnpc_exact_place").text('${btel_cas.get(0).exact_place}' );
		if('${btel_cas.get(0).whether_on}'  != null){
			var whether = ['${btel_cas.get(0).whether_on}' ];
			if(whether == "IB"){
	           	 $("#whether_on").text("IB");
	        } 
			if(whether == "LC"){
	           	 $("#whether_on").text("LC");
	        } 
			if(whether == "LAC"){
	           	 $("#whether_on").text("LAC");
	        } 
			if(whether == "AGPL"){
	           	 $("#whether_on").text("AGPL");
	        } 
			if(whether == "OTHERS"){
	           	 $("#whether_on").text("OTHERS");
	        } 
		}
		
		$("#batnpc_unit").text('${btel_cas.get(0).unit}' );
		
		$("#batnpc_bde_val").val('${btel_cas.get(0).bde}' );
		$('#batnpc_bde').text($( "#batnpc_bde_val option:selected" ).text());
		$("#batnpc_div_subarea_val").val('${btel_cas.get(0).div_subarea}' );
		$('#batnpc_div_subarea').text($( "#batnpc_div_subarea_val option:selected" ).text());
		$("#batnpc_corps_area_val").val('${btel_cas.get(0).corps_area}' );
		$('#batnpc_corps_area').text($( "#batnpc_corps_area_val option:selected" ).text());
		$("#batnpc_command_val").val('${btel_cas.get(0).command}' );
		$('#batnpc_command').text($( "#batnpc_command_val option:selected" ).text()); 
		
		$("#batnpc_country_val").val('${btel_cas.get(0).country}' );
		$('#batnpc_country').text($( "#batnpc_country_val option:selected" ).text());
 		
		if('${btel_cas.get(0).country_other}'!=null && '${btel_cas.get(0).country_other}'!='' && ($('#batnpc_country').text().toUpperCase()==other || $('#batnpc_country').text().toUpperCase()==others)){
				 $('#batnpc_country').text('${btel_cas.get(0).country_other}');
				}
		
		
		onCountryChangeApprove(document.getElementById('batnpc_country_val'));
		
		$("#batnpc_state_val").val('${btel_cas.get(0).state}' );
		$('#batnpc_state').text($( "#batnpc_state_val option:selected" ).text());
		
		if('${btel_cas.get(0).state_other}'!=null && '${btel_cas.get(0).state_other}'!='' && ($('#batnpc_state').text().toUpperCase()==other || $('#batnpc_state').text().toUpperCase()==others)){
			 $('#batnpc_state').text('${btel_cas.get(0).state_other}');
			}
		
		 onStateChangeApprove(document.getElementById('batnpc_state_val'));
		
		$("#batnpc_district_val").val('${btel_cas.get(0).district}');
		$('#batnpc_district').text($( "#batnpc_district_val option:selected" ).text());
		
		if('${btel_cas.get(0).district_other}'!=null && '${btel_cas.get(0).district_other}'!='' && ($('#batnpc_district').text().toUpperCase()==other || $('#batnpc_district').text().toUpperCase()==others)){
			 $('#batnpc_district').text('${btel_cas.get(0).district_other}');
			}
		
		onDistrictChangeApprove(document.getElementById('batnpc_district_val'));
		
		$("#batnpc_tehsil_val").val('${btel_cas.get(0).tehsil}' );
		$('#batnpc_tehsil').text($( "#batnpc_tehsil_val option:selected" ).text());
		
		if('${btel_cas.get(0).tehsil_other}'!=null && '${btel_cas.get(0).tehsil_other}'!='' && ($('#batnpc_tehsil').text().toUpperCase()==other || $('#batnpc_tehsil').text().toUpperCase()==others)){
			 $('#batnpc_tehsil').text('${btel_cas.get(0).tehsil_other}');
			}
		
		onTehsilChangeApprove(document.getElementById('batnpc_tehsil_val'));
		
		//$("#batnpc_village_val").val(village);
		$('#batnpc_village').text('${btel_cas.get(0).village}' );
		$("#batnpc_pin").text('${btel_cas.get(0).pin}' );
		
		
		
		$("#name_of_operation").text('${btel_cas.get(0).name_of_operation}');
		if('${btel_cas.get(0).date_of_casuality}'  != null){
		   $("#date_of_casuality").text(convertDateFormate('${btel_cas.get(0).date_of_casuality}' ));
		}
		
		$("#circumstances").text('${btel_cas.get(0).circumstances}' );
		$("#time_of_casuality").text('${btel_cas.get(0).time_of_casuality}' );
		$("#onduty").text('${btel_cas.get(0).onduty}' );
		$("#field_services").text('${btel_cas.get(0).field_services}' );
		$("#hospital_name").text('${btel_cas.get(0).hospital_name}');
		$("#hospital_location").text('${btel_cas.get(0).hospital_location}' );
		$("#aid_to_civ").text('${btel_cas.get(0).aid_to_civ}' );
		$("#nok_informed").text('${btel_cas.get(0).nok_informed}' );
		
		$("#cause_of_casuality_1").val('${btel_cas.get(0).cause_of_casuality_1}' );
		$('#cause_of_casuality_1lb').text($( "#cause_of_casuality_1 option:selected" ).text());
		
		
		$('#battle_desc_val').val('${btel_cas.get(0).description}');
		$('#battle_desc').text($("#battle_desc_val option:selected" ).text());
		
		/*
		if('${btel_cas.get(0).cause_of_casuality}' !=0){
		$('#cause_of_casuality_1lb').text('${btel_cas.get(0).cause_of_casuality}' );
		
		$('#cause_of_casuality_1lb').val( '${btel_cas.get(0).cause_of_casuality_1}');
		$('#cause_of_casuality_1').text($("#cause_of_casuality_1lb option:selected" ).text());
		
		$('#battle_desc_val').val('${btel_cas.get(0).description}');
		$('#battle_desc').text($("#battle_desc_val option:selected" ).text());
		*/
		$('#cause_of_casuality_val').val('${btel_cas.get(0).cause_of_casuality}');
		getDescListApprove(document.getElementById('cause_of_casuality_val'));
		$('#cause_of_casuality').text('${btel_cas.get(0).cause_of_casuality}' ); 
		
		$('#battle_desc_val').val('${btel_cas.get(0).description}');
		$('#battle_desc').text($("#battle_desc_val option:selected" ).text());
		
		
		</c:if>
}
function getInterArm(){
	<c:if test="${InterArmTransfer.size() != 0}">
				$('#inter_arm_parent_arm_service_val').val('${InterArmTransfer.get(0).parent_arm_service}' );	
				$('#inter_arm_parent_arm_service').text($( "#inter_arm_parent_arm_service_val option:selected" ).text());
				
				$('#inter_arm_authority').text('${InterArmTransfer.get(0).authority}' );
				$('#inter_arm_date_of_authority').text(convertDateFormate('${InterArmTransfer.get(0).date_of_authority}' ));
				
				$('#inter_arm_with_effect_from').text(convertDateFormate('${InterArmTransfer.get(0).with_effect_from}'));
				if('${InterArmTransfer.get(0).regt}' == 0){
					$('#inter_arm_regt').text("");
				}
				else{
					$('#inter_arm_regt_val').val('${InterArmTransfer.get(0).regt}' );
					$('#inter_arm_regt').text($( "#inter_arm_regt_val option:selected" ).text());
				}
				
				<c:if test="${InterArmTransfer_old.size() != 0}">
			
		    	 
		    		 $('#inter_arm_old_arm_service_val').val('${InterArmTransfer_old.get(0).parent_arm_service}' );	
					$('#inter_arm_old_arm_service').text($( "#inter_arm_old_arm_service_val option:selected" ).text());
					$('#inter_arm_old_regt_val').val('${InterArmTransfer_old.get(0).regt}' );
					if ('${InterArmTransfer_old.get(0).regt}' =='0') {
						$('#inter_arm_old_regt').text("");
					}
					else
					$('#inter_arm_old_regt').text($( "#inter_arm_old_regt_val option:selected" ).text());
		    	 
		    	 </c:if>
				
				$('#regn').val('${InterArmTransfer.get(0).regt}' );
				$('#p_arm').val('${InterArmTransfer.get(0).parent_arm_service}' );
	</c:if>
				
}
function getInterArm_Reject(){
	 var ch_id='${census_id}';
	 var comm_id = '${comm_id}';
	 var ch_r_id = $("#p_id").val();
	 
	 $.post('get_InterArmReject?' + key + "=" + value, {ch_id:ch_id,comm_id:comm_id,ch_r_id:ch_r_id}, function(j){
		 if(j!=""){
			 alert(j);
		 }
	 });
	}
function getcoc(){
	 var n_id='${census_id}'; 
	 var comm_id='${comm_id}';
	
	 
	 <c:if test="${ChangeOfComm.size() != 0}">
		
		$('#coc_authority').text('${ChangeOfComm.get(0).authority}');
		
				
				$('#coc_date_of_authority').text(convertDateFormate('${ChangeOfComm.get(0).date_of_authority}'));
				
			//$("#persnl_no1_val").val(j[0].new_personal_no.substring(0, 2));
			$('#persnl_no1').text('${ChangeOfComm.get(0).new_personal_no}');
// 			$("#persnl_no2").text(j[0].new_personal_no.substring(2, 7));
// 			$("#persnl_no3").text(j[0].new_personal_no.substring(7, 8));
			$('#coc_date_of_permanent_commission').text(convertDateFormate('${ChangeOfComm.get(0).date_of_permanent_commission}'));
			///$('#coc_previous_commission').text(j[0].previous_commission);
			$('#coc_previous_commissionselect').val('${ChangeOfComm.get(0).previous_commission}');
			$('#coc_previous_commission').text($( "#coc_previous_commissionselect option:selected" ).text());
			$('#type_of_commission_grantedselect').val('${ChangeOfComm.get(0).type_of_commission_granted}');
			$('#type_of_commission_granted').text($( "#type_of_commission_grantedselect option:selected" ).text());
			$('#coc_date_of_seniority').text(convertDateFormate('${ChangeOfComm.get(0).date_of_seniority}'));
			$('#eff_coc_date_of_seniority').text(convertDateFormate('${ChangeOfComm.get(0).eff_date_of_seniority}'))
			$('#coc_ch_id').val('${ChangeOfComm.get(0).id}');
			
			$('#per_no').val('${ChangeOfComm.get(0).new_personal_no}');
			$('#type_of_comm_granted').val('${ChangeOfComm.get(0).type_of_commission_granted}');
			$('#dt_seniority').val(convertDateFormate('${ChangeOfComm.get(0).date_of_seniority}'));
			$('#dt_comm').val(convertDateFormate('${ChangeOfComm.get(0).date_of_permanent_commission}'));
			
	 
	  $.post('getcocDataStatus1?' + key + "=" + value, {comm_id:comm_id}, function(j){
		  if(j!=""){
			  $('#coc_previous_commission').text(j[0][0]);
			 /// $('#coc_date_of_seniority').val(formateDate(j[0][1]));
		  }
			
	  });
	  </c:if>
}
function get_Coc_Reject(){
	 var ch_id='${census_id}';
	 var comm_id = '${comm_id}';
	 var ch_r_id = $("#coc_ch_id").val();
	
	 $.post('get_CocReject?' + key + "=" + value, {ch_id:ch_id,comm_id:comm_id,ch_r_id:ch_r_id}, function(j){
		 if(j!=""){
			 alert(j);
		 }
	 });
	}
function geteoc(){
	 var n_id='${census_id}';  
	 var comm_id='${comm_id}';
	 
	 <c:if test="${ExtensionComm.size() != 0}">
				
				$('#eoc_authority').text('${ExtensionComm.get(0).authority}');
			    $('#eoc_authority_date').text(convertDateFormate('${ExtensionComm.get(0).date_of_authority}'));
				$('#eoc_from').text(convertDateFormate('${ExtensionComm.get(0).fromdt}'));
				$('#eoc_to').text(convertDateFormate('${ExtensionComm.get(0).todt}'));
				$('#eoc_ch_id').val('${ExtensionComm.get(0).id}');
				
				 </c:if>
}
function get_Eoc_Reject(){
	 var ch_id='${census_id}';
	 var comm_id = '${comm_id}';
	 var ch_r_id = $("#eoc_ch_id").val();
	 $.post('get_EocReject?' + key + "=" + value, {ch_id:ch_id,comm_id:comm_id,ch_r_id:ch_r_id}, function(j){
		 if(j!=""){
			 alert(j);
		 }
	 });
	}
function get_Secondment(){
	<c:if test="${Secondment.size() != 0}">
	
				
				 $("#sec_authority").text('${Secondment.get(0).authority}' );
				 $("#sec_date_of_authority").text(convertDateFormate('${Secondment.get(0).date_of_authority}' ));
				 $("#seconded_to_val").val('${Secondment.get(0).seconded_to}' );
				 $('#seconded_to').text($( "#seconded_to_val option:selected" ).text());
				 $("#secondment_effect").text(convertDateFormate('${Secondment.get(0).secondment_effect}' ));
		  </c:if>
}
function get_Secondment_Reject(){
	 var ch_id='${census_id}';
	 var comm_id = '${comm_id}';
	 var ch_r_id = $("#sec_id").val();
	 $.post('get_SecondmentReject?' + key + "=" + value, {ch_id:ch_id,comm_id:comm_id,ch_r_id:ch_r_id}, function(j){
		 if(j!=""){
			 alert(j);
		 }
	 });
	}
function getNon_effective(){
 
	 <c:if test="${NonEffective.size() != 0}">		
	 

				 $("#non_ef_authority").text( ' ${NonEffective.get(0).non_ef_authority}');
				 $("#date_of_authority_non_ef").text(convertDateFormate( ' ${NonEffective.get(0).date_of_authority_non_ef}' ));
				 $('#cause_of_non_effectiveselect').val( '${NonEffective.get(0).cause_of_non_effective}' );
				
				 $('#cause_of_non_effective').text($( "#cause_of_non_effectiveselect option:selected" ).text());
				 $("#date_of_non_effective").text(convertDateFormate( ' ${NonEffective.get(0).date_of_non_effective}' ));
		</c:if>
}

function get_non_eff_address_details(){
	
	$.ajaxSetup({
		async : false
	});
	
	var p_id1='${census_id}';	
	 var comm_id = '${comm_id}';
	 
	$.post('address_details_GetData_census?' + key + "=" + value,{p_id1:p_id1,comm_id:comm_id,app_status:app_status }, function(j){
		var v=j.length;
		if(v!=0){
	
            
             $("#check_per_address").prop("checked", true);
				
            $("#perm_home_addr_country_val").val(j[0].permanent_country);
			$('#perm_home_addr_country').text($( "#perm_home_addr_country_val option:selected" ).text());
			
			fn_perm_home_addr_Country();
			if(j[0].per_home_addr_country_other!=null && j[0].per_home_addr_country_other && ($('#perm_home_addr_country').text().toUpperCase()==other || $('#perm_home_addr_country').text().toUpperCase()==others)){
				$('#perm_home_addr_country').text(j[0].per_home_addr_country_other);
			}
			$("#perm_home_addr_state_val").val(j[0].permanent_state);
			$('#perm_home_addr_state').text($( "#perm_home_addr_state_val option:selected" ).text());
			fn_prem_domicile_state();
			if(j[0].per_home_addr_state_other!=null && j[0].per_home_addr_state_other && ($('#perm_home_addr_state').text().toUpperCase()==other || $('#perm_home_addr_state').text().toUpperCase()==others)){
				$('#perm_home_addr_state').text(j[0].per_home_addr_state_other);
			}
			$("#perm_home_addr_district_val").val(j[0].permanent_district);
			$('#perm_home_addr_district').text($( "#perm_home_addr_district_val option:selected" ).text());
			fn_prem_domicile_district();
			if(j[0].per_home_addr_district_other!=null && j[0].per_home_addr_district_other && ($('#perm_home_addr_district').text().toUpperCase()==other || $('#perm_home_addr_district').text().toUpperCase()==others)){
				$('#perm_home_addr_district').text(j[0].per_home_addr_district_other);
			}
			$("#perm_home_addr_tehsil_val").val(j[0].permanent_tehsil);
			$('#perm_home_addr_tehsil').text($( "#perm_home_addr_tehsil_val option:selected" ).text());
			if(j[0].per_home_addr_tehsil_other!=null && j[0].per_home_addr_tehsil_other && ($('#perm_home_addr_tehsil').text().toUpperCase()==other || $('#perm_home_addr_tehsil').text().toUpperCase()==others)){
				$('#perm_home_addr_tehsil').text(j[0].per_home_addr_tehsil_other);
			}
			$("#perm_home_addr_village").text(j[0].permanent_village);
			
			$("#perm_home_addr_pin").text(j[0].permanent_pin_code);
			$("#perm_home_addr_rail").text(j[0].permanent_near_railway_station);
			
            var permanent= j[0].permanent_rural_urban_semi;
            if(permanent == "rural"){
                    $("#perm_home_addr1").text("Rural");
             } 
            if(permanent == "urban"){
            	 $("#perm_home_addr1").text("Urban");
             }
            if(permanent == "semi_urban")
            {
            	 $("#perm_home_addr1").text("Semi Urban");
             }  
            var br= j[0].permanent_border_area;
            if(br == "yes"){
                    $("#perm_border_area").text("Yes");
             } 
            if(br == "no"){
            	$("#perm_border_area").text("No");
             } 
          
			//////nok//////
			$("#non_addr_ch_id").val(j[0].id);
		}
	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
}

function fn_perm_home_addr_Country() {
	
	var contry_id = $('select#perm_home_addr_country_val').val();
	

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
						
						$("select#perm_home_addr_state_val").html(options);
						/* fn_prem_domicile_state();
						fn_prem_domicile_district(); */
					}).fail(function(xhr, textStatus, errorThrown) {
			});
}

function fn_prem_domicile_state() {
	var state_id = $('select#perm_home_addr_state_val').val();
	//alert("c---" + state_id);
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

						$("select#perm_home_addr_district_val").html(options);
						//fn_prem_domicile_district();
					}).fail(function(xhr, textStatus, errorThrown) {
			});
}

function fn_prem_domicile_district() {
	
	var Dist_id = $('select#perm_home_addr_district_val').val();
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

						$("select#perm_home_addr_tehsil_val").html(options);

					}).fail(function(xhr, textStatus, errorThrown) {
			});
}





function per_address(){
	 var census_id='${census_id}'; 
	 var comm_id = '${comm_id}';

  /// if($("#check_per_address").prop('checked') == true){  
   	$.post('getPerAddressDataStatus1?' + key + "=" + value, {census_id:census_id,comm_id:comm_id}, function(j){
   	
   		if(j!=""){
   			$("#non_per_home_addr_country_val").val(j[0].permanent_country);
   			$('#non_per_home_addr_country').text($( "#non_per_home_addr_country_val option:selected" ).text());
   			
   			$("#non_perm_home_addr_state_val").val(j[0].permanent_state);
   			$('#non_perm_home_addr_state').text($( "#non_perm_home_addr_state_val option:selected" ).text());
   			
   			$("#non_perm_home_addr_district_val").val(j[0].permanent_district);
   			$('#non_perm_home_addr_district').text($( "#non_perm_home_addr_district_val option:selected" ).text());
   			
   			$("#non_perm_home_addr_tehsil_val").val(j[0].permanent_tehsil);
   			$('#non_perm_home_addr_tehsil').text($( "#non_perm_home_addr_tehsil_val option:selected" ).text());
   			
   			$("#non_perm_home_addr_village").text(j[0].permanent_village);
   			$("#non_perm_home_addr_pin").text(j[0].permanent_pin_code);
   			
   			$("#non_perm_home_addr_rail").text(j[0].permanent_near_railway_station);
   			 var permanent= j[0].permanent_rural_urban_semi;
               if(permanent == "rural"){
                       $("#non_perm_home_addr_rural").prop("checked", true);
                } 
               if(permanent == "urban"){
                       $("#non_perm_home_addr_urban").prop("checked", true);
                }
               if(permanent == "semi_urban")
               {
                       $("#non_perm_home_addr_semi_urban").prop("checked", true);
                }  
               var br= j[0].permanent_border_area;
               if(br == "yes"){
                       $("#non_per_border_area").prop("checked", true);
                } 
               if(br == "no"){
                       $("#non_per_border_area1").prop("checked", true);
                } 
   		}
	  });
   ///}
  
}


//************************************* START DISCIPLINE *****************************//
var others="OTHERS";
var other="OTHER";
function get_change_of_Discipline(){
	
		<c:if test="${Discipline.size() != 0}">
		
		$('#army_act_sec_select').val('${Discipline.get(0).army_act_sec}' );
		$('#army_act_sec').text($( "#army_act_sec_select option:selected" ).text());
		
		$('#sub_clause_select').val('${Discipline.get(0).sub_clause}' );
		$('#sub_clause').text($( "#sub_clause_select option:selected" ).text());

				$("#description1").text('${Discipline.get(0).description}' );
			
				$('#trialed_byselect').val('${Discipline.get(0).trialed_by}' );
				$('#trialed_by').text($( "#trialed_byselect option:selected" ).text());

			
				$('#type_of_entry_val').val('${Discipline.get(0).type_of_entry}' );
				$('#type_of_entry').text($( "#type_of_entry_val option:selected" ).text());
				if('${Discipline.get(0).type_of_entry_other}'!=null && '${Discipline.get(0).type_of_entry_other}'!='' && ($('#type_of_entry').text().toUpperCase()==other || $('#type_of_entry').text().toUpperCase()==others)  ){
					 $('#type_of_entry').text('${Discipline.get(0).type_of_entry_other}');
					}
				
				var sus_no ='${Discipline.get(0).unit_name}' ;
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
		    	                   //alert("unit---" + dec(enc,j[0]));
		    	                   $("#unit_name").text(dec(enc,j[0])); 
		        }).fail(function(xhr, textStatus, errorThrown) {
		        });
		    	}
				//$("#name_dis").text(name);
				$("#award_dt").text(convertDateFormate('${Discipline.get(0).award_dt}' ));
		</c:if>
} 

function getDiscipline_Reject(){
	 var ch_id='${census_id}';
	 var comm_id = '${comm_id}';
	 var ch_r_id = $("#disi_id").val();
	 
	 $.post('get_DisciplineReject?' + key + "=" + value, {ch_id:ch_id,comm_id:comm_id,ch_r_id:ch_r_id}, function(j){
		 if(j!=""){
			 alert(j);
		 }
	 });
	}
	

//*************************************END DISCIPLINE *****************************//

//promotional exam











</script>

<script>
////////////////////Start Medical/////////////////
function get_Medical_Reject(){
	var ch_id='${census_id}'; 
	 var comm_id = '${comm_id}';
	 
	 $.post('getMedical_Reject?' + key + "=" + value, {ch_id:ch_id,comm_id:comm_id}, function(j){
		 
		 if(j!=""){
			 alert(j);
		 }
	 });
	} 

function onShapeValueChange(e,label){
// 	if(e.value=="1A"){
// 		$("#"+label+"_from_date1").prop('readonly', true);
// 		$("#"+label+"_to_date1").prop('readonly', true);
// 	}
// 	else{
// 		$("#"+label+"_from_date1").prop('readonly', false);
// 		$("#"+label+"_to_date1").prop('readonly', false);
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
		if(e.value == 0 || (cope!= 4 && cope!= 2 && e.value == 1)){
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


function statusChange(Shape,position,Shape_status){
	

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
		             
		             if(Shape==1){
		            	   $("select#sShape_value"+position).html(options);
		    			 
		    		 }
		    		 else  if(Shape==2){
		    			   $("select#hShape_value"+position).html(options);
		    		 }
		    		else  if(Shape==3){
		    			   $("select#aShape_value"+position).html(options);
		    				 }
		    		else  if(Shape==4){
		    			   $("select#pShape_value"+position).html(options);
		    		}
		    		else  if(Shape==5){
		    			   $("select#eShape_value"+position).html(options);
		    		}
		             
		            
             }
		 
		 
	 }).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
	 
// 	}
}
sShape = 1;
function getsShapeDetails(){
	 var p_id=$('#comm_id').val(); 
	
	var app_status=1;
	 var Shape='S'
	 var i=0;
	$.post('madical_cat_GetData_xml?' + key + "=" + value, {p_id:p_id,Shape:Shape, app_status:app_status}, function(j){
			var v=j.length;
				
		if(v!=0){	
			 
			
			$('#s_madtable').show(); 
				$('#s_madtbody').empty(); 
				for(i;i<v;i++){			
					x=i+1;		
					 var fd="";
					 var td="";
					 
					 if(j[i][2]!=null && j[i][2]!=""){
						 fd=convertDateFormate(j[i][2]);
// 					  	 td=convertDateFormate(j[i][3]);
					 }
					 if(j[i][3] != null && j[i][3] != "") {
							td = convertDateFormate(j[i][3]);
						}
					 if (j[i][0]==1) {
						 td="";
						 }
						$("table#s_madtable").append('<tr id="tr_sShape'+x+'">'
							+'<td class="sShape_srno" style="width: 2%;">'+x+'</td>'
							+'<td><label id="s_status_lb'+x+'"></label>'
							
							+'<select name="s_status'+x+'" id="s_status'+x+'" class="select_hide" '
							+'	class="form-control-sm form-control"  onchange="statusChange(1,'+x+',this.options[this.selectedIndex].value)">'
							+'	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">'
							+'	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>'
							+'	</c:forEach>'
							+'		</select>'
							+'  </td>'
							+'   <td style=""><label id="sShape_value_lb'+x+'"></label>'
							+'<select name="sShape_value'+x+'" id="sShape_value'+x+'" class="select_hide" '
							+'		class="form-control-sm form-control" onchange="onShapeValueChange(this,\'s\')">'
							+'		</select>	</td>'
							+'	<td style="">'
							+'		<label >'+fd+'<label>	'					                             
							+'		 </td>'	   
							+'<td style="">'
							+'			<label >'+td+'<label>	'				                             
							+'   </td>'
							+'  <td style="visibility:hidden; "  class="diagnosisClass1'+x+'">'
							+' <label name="_diagnosis_code1'+x+'" id="_diagnosis_code1'+x+'"  '
							+'  >'+j[i][4]+'<label>	'					                              
							+'   </td>'

							+' <td style="display:none;"><input type="text" id="sShape_ch_id'+x+'" name="sShape_ch_id'+x+'"  value="'+j[i][5]+'" class="form-control autocomplete" autocomplete="off" ></td>'   
							
							

							+'</tr>');
						$("#s_status"+x).val(j[i][0]);
						$("#s_status_lb"+x).text($( "#s_status"+x+" option:selected" ).text());
						$.ajaxSetup({
  							async : false
  						});

						statusChange(1,x,j[i][0]);
						
						$("#sShape_value"+x).val(j[i][1]);
						$("#sShape_value_lb"+x).text($( "#sShape_value"+x+" option:selected" ).text());
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
			$("#madical_authority").text(j[i-1][6]); 
			$("#madical_date_of_authority").text(convertDateFormate(j[i-1][7])); 
			$('#mad_classification_count').val(j[i-1][8]);
	
			if(j[i-1][11]!=null && j[i-1][11]!="" && j[i-1][12]!=null && j[i-1][12]!="" && j[i-1][13]!=null && j[i-1][13]!=""){
				$("#check_1bx").prop("checked", true);
				oncheck_1bx();
				 fd=convertDateFormate(j[i-1][11]);
			  	 td=convertDateFormate(j[i-1][12]);
				$("#1bx_from_date").text(fd);
				$("#1bx_to_date").text(td);
				$("#1bx_diagnosis_code").text(j[i-1][13]);
			}
//  			$('#mad_classification').attr('readonly', true);
			
// 			setDiagnosis();
			}
		
			
	  });
}

hShape = 1;
function gethShapeDetails(){
	
	  var p_id=$('#comm_id').val(); 
	  var app_status=1;
	 var Shape='H';
	 var i=0;
	  $.post('madical_cat_GetData_xml?' + key + "=" + value, {p_id:p_id,Shape:Shape,app_status:app_status }, function(j){
			var v=j.length;
			
		if(v!=0){	
			$('#h_madtable').show(); 
				$('#h_madtbody').empty(); 
				for(i;i<v;i++){			
					x=i+1;		
	
					 var fd="";
					 var td="";
					 if(j[i][2]!=null && j[i][2]!=""){
						 fd=convertDateFormate(j[i][2]);
// 					  	 td=convertDateFormate(j[i][3]);
					 }
					 if(j[i][3] != null && j[i][3] != "") {
							td = convertDateFormate(j[i][3]);
						}
					 if (j[i][0]==1) {
						 td="";
						 }
					
						$("table#h_madtable").append('<tr id="tr_hShape'+x+'">'
							+'<td style="width: 2%;">'+x+'</td>'
							+'<td><label id="h_status_lb'+x+'"></label>'
							
							+'<select name="h_status'+x+'" id="h_status'+x+'" class="select_hide" '
							+'	class="form-control-sm form-control"  onchange="statusChange(2,'+x+',this.options[this.selectedIndex].value)">'
							+'	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">'
							+'	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>'
							+'	</c:forEach>'
							+'		</select>'
							+'  </td>'
							+'   <td style=""><label id="hShape_value_lb'+x+'"></label>'
							+'<select name="hShape_value'+x+'" id="hShape_value'+x+'" class="select_hide" '
							+'		class="form-control-sm form-control" onchange="onShapeValueChange(this,\'h\')">'
							+'		</select>	</td>'
							+'	<td style="">'
							+'		<label >'+fd+'<label>	'					                             
							+'		 </td>'	   
							+'<td style="">'
							+'			<label >'+td+'<label>	'				                             
							+'   </td>'
							+'  <td style="visibility:hidden; "  class="diagnosisClass2'+x+'">'
							+' <label name="_diagnosis_code2'+x+'" id="_diagnosis_code2'+x+'"  '
							+'  >'+j[i][4]+'<label>	'						                              
							+'   </td>'

							+' <td style="display:none;"><input type="text" id="hShape_ch_id'+x+'" name="hShape_ch_id'+x+'" value="'+j[i][5]+'" class="form-control autocomplete" autocomplete="off" ></td>'   
							
							+'</tr>');
						$("#h_status"+x).val(j[i][0]);
						$("#h_status_lb"+x).text($( "#h_status"+x+" option:selected" ).text());
						$.ajaxSetup({
 							async : false
 						});
						
						statusChange(2,x,j[i][0]);
						

						$("#hShape_value"+x).val(j[i][1]); 
						$("#hShape_value_lb"+x).text($( "#hShape_value"+x+" option:selected" ).text());
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
			
// 			setDiagnosis();
			}
			
	  });
}


aShape = 1;
function getaShapeDetails(){
	
	  var p_id=$('#comm_id').val(); 
	  var app_status=1;
	 var Shape='A';
	 var i=0;
	  $.post('madical_cat_GetData_xml?' + key + "=" + value, {p_id:p_id,Shape:Shape ,app_status:app_status}, function(j){
			var v=j.length;
			
		if(v!=0){	
			classification=j[0][13];
			$('#a_madtable').show(); 
				$('#a_madtbody').empty(); 
				for(i;i<v;i++){			
					x=i+1;		
	
					 var fd="";
					 var td="";
					 if(j[i][2]!=null && j[i][2]!=""){
						 fd=convertDateFormate(j[i][2]);
// 					  	 td=convertDateFormate(j[i][3]);
					 }
					 if(j[i][3] != null && j[i][3] != "") {
							td = convertDateFormate(j[i][3]);
						}
					 if (j[i][0]==1) {
						 td="";
						 }
					
						$("table#a_madtable").append('<tr id="tr_aShape'+x+'">'
							+'<td style="width: 2%;">'+x+'</td>'
							+'<td><label id="a_status_lb'+x+'"></label>'
							+'<select name="a_status'+x+'" id="a_status'+x+'" class="select_hide" '
							+'	class="form-control-sm form-control"  onchange="statusChange(3,'+x+',this.options[this.selectedIndex].value)">'
							+'	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">'
							+'	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>'
							+'	</c:forEach>'
							+'		</select>'
							+'  </td>'
							+'   <td style=""><label id="aShape_value_lb'+x+'"></label>'
							+'<select name="aShape_value'+x+'" id="aShape_value'+x+'" class="select_hide" '
							+'		class="form-control-sm form-control" onchange="onShapeValueChange(this,\'a\')">'
							+'		</select>	</td>'
							+'	<td style="">'
							+'		<label >'+fd+'<label>	'					                             
							+'		 </td>'	   
							+'<td style="">'
							+'			<label >'+td+'<label>	'				                             
							+'   </td>'
							
							+'  <td style="visibility:hidden; "  class="diagnosisClass3'+x+'">'
							+' <label name="_diagnosis_code3'+x+'" id="_diagnosis_code3'+x+'"  '
							+'  >'+j[i][4]+'<label>	'						                              
							+'   </td>'
							+' <td style="display:none;"><input type="text" id="aShape_ch_id'+x+'" name="aShape_ch_id'+x+'"  value="'+j[i][5]+'" class="form-control autocomplete" autocomplete="off" ></td>'   
						
							+'</tr>');
						$("#a_status"+x).val(j[i][0]);
						$("#a_status_lb"+x).text($( "#a_status"+x+" option:selected" ).text());
						$.ajaxSetup({
							async : false
						});

						statusChange(3,x,j[i][0]);
						
						$("#aShape_value"+x).val(j[i][1]); 
						$("#aShape_value_lb"+x).text($( "#aShape_value"+x+" option:selected" ).text());
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
			
// 			setDiagnosis();
			}
			
	  });
}


pShape = 1;
function getpShapeDetails(){
	
	  var p_id=$('#comm_id').val(); 
	  var app_status=1;
	 var Shape='P';
	 var i=0;
	  $.post('madical_cat_GetData_xml?' + key + "=" + value, {p_id:p_id,Shape:Shape,app_status:app_status }, function(j){
			var v=j.length;
			
		if(v!=0){	
			$('#p_madtable').show(); 
				$('#p_madtbody').empty(); 
				for(i;i<v;i++){			
					x=i+1;		
	
					 var fd="";
					 var td="";
					 if(j[i][2]!=null && j[i][2]!=""){
						 fd=convertDateFormate(j[i][2]);
// 					  	 td=convertDateFormate(j[i][3]);
					 }
					 if(j[i][3] != null && j[i][3] != "") {
							td = convertDateFormate(j[i][3]);
						}
					 if (j[i][0]==1) {
						 td="";
						 }
					
						$("table#p_madtable").append('<tr id="tr_pShape'+x+'">'
							+'<td style="width: 2%;">'+x+'</td>'
							+'<td><label id="p_status_lb'+x+'"></label>'
							+'<select name="p_status'+x+'" id="p_status'+x+'" class="select_hide" '
							+'	class="form-control-sm form-control"  onchange="statusChange(4,'+x+',this.options[this.selectedIndex].value)">'
							+'	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">'
							+'	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>'
							+'	</c:forEach>'
							+'		</select>'
							+'  </td>'
							+'   <td style=""><label id="pShape_value_lb'+x+'"></label>'
							+'<select name="pShape_value'+x+'" id="pShape_value'+x+'" class="select_hide" '
							+'		class="form-control-sm form-control" onchange="onShapeValueChange(this,\'p\')">'
							+'		</select>	</td>'
							+'	<td style="">'
							+'		<label >'+fd+'<label>	'					                             
							+'		 </td>'	   
							+'<td style="">'
							+'			<label >'+td+'<label>	'				                             
							+'   </td>'												
							+'  <td style="visibility:hidden; "  class="diagnosisClass4'+x+'">'
							+' <label name="_diagnosis_code4'+x+'" id="_diagnosis_code4'+x+'"  '
							+'  >'+j[i][4]+'<label>	'						                              
							+'   </td>'
							+' <td style="display:none;"><input type="text" id="pShape_ch_id'+x+'" name="pShape_ch_id'+x+'"  value="'+j[i][5]+'" class="form-control autocomplete" autocomplete="off" ></td>'   
							
							+'</tr>');
						$("#p_status"+x).val(j[i][0]);
						$("#p_status_lb"+x).text($( "#p_status"+x+" option:selected" ).text());
						$.ajaxSetup({
							async : false
						});

						statusChange(4,x,j[i][0]);
						$.ajaxSetup({
							async : false
						});

						$("#pShape_value"+x).val(j[i][1]); 
						$("#pShape_value_lb"+x).text($( "#pShape_value"+x+" option:selected" ).text());
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
// 			setDiagnosis();
			}
			
	  });
}

eShape = 1;
function geteShapeDetails(){
	
	  var p_id=$('#comm_id').val(); 
	  var app_status=1;
	 var Shape='E';
	 var i=0;
	  $.post('madical_cat_GetData_xml?' + key + "=" + value, {p_id:p_id,Shape:Shape,app_status:app_status }, function(j){
			var v=j.length;
			 
		if(v!=0){	
			$('#e_madtable').show(); 
				$('#e_madtbody').empty(); 
				for(i;i<v;i++){			
					x=i+1;		
	
					 var fd="";
					 var td="";
					 if(j[i][2]!=null && j[i][2]!=""){
					 	 fd=convertDateFormate(j[i][2]);
// 					  	 td=convertDateFormate(j[i][3]);
					 }
					 if(j[i][3] != null && j[i][3] != "") {
							td = convertDateFormate(j[i][3]);
						}
					 if (j[i][0]==1) {
						 td="";
						 }
					
						$("table#e_madtable").append('<tr id="tr_eShape'+x+'">'
							+'<td style="width: 2%;">'+x+'</td>'
							+'<td>'
							+'<label id="e_status_lb'+x+'"></label><select class="select_hide" name="e_status'+x+'" id="e_status'+x+'" '
							+'	class="form-control-sm form-control"  onchange="statusChange(5,'+x+',this.options[this.selectedIndex].value)">'
							+'	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">'
							+'	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>'
							+'	</c:forEach>'
							+'		</select>'
							+'  </td>'
							+'   <td style=""><label id="eShape_value_lb'+x+'"></label>'
							+'<select class="select_hide" name="eShape_value'+x+'" id="eShape_value'+x+'" '
							+'		class="form-control-sm form-control" onchange="onShapeValueChange(this,\'e\')">'
							+'		</select>	</td>'
							+'	<td style="">'
							+'		<label >'+fd+'<label>	'					                             
							+'		 </td>'	   
							+'<td style="">'
							+'			<label >'+td+'<label>	'				                             
							+'   </td>'
							+'  <td style="visibility:hidden; "  class="diagnosisClass5'+x+'">'
							+' <label name="_diagnosis_code5'+x+'" id="_diagnosis_code5'+x+'"  '
							+'  >'+j[i][4]+'<label>	'						                              
							+'   </td>'
							+' <td style="display:none;"><input type="text" id="eShape_ch_id'+x+'" name="eShape_ch_id'+x+'"  value="'+j[i][5]+'" class="form-control autocomplete" autocomplete="off" ></td>'   
						
							+'</tr>');
						$("#e_status"+x).val(j[i][0]);
						$("#e_status_lb"+x).text($( "#e_status"+x+" option:selected" ).text());
						$.ajaxSetup({
							async : false
						});

						statusChange(5,x,j[i][0]);
						$.ajaxSetup({
							async : false
						});

						$("#eShape_value"+x).val(j[i][1]); 
						$("#eShape_value_lb"+x).text($( "#eShape_value"+x+" option:selected" ).text());
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
// 			setDiagnosis();
			}
			
	  });
}



function getcCopeDetails(){
	
	  var p_id=$('#comm_id').val(); 
	  var app_status=1;
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
						 	
						 	+'<label id="c_cvalue_lb'+x+'"></label><select class="select_hide" name="c_cvalue'+x+'" id="c_cvalue'+x+'" '
						 	+'	class="form-control-sm form-control" onchange="onCCopeChange(this,'+x+'); onCopeChangebt(this,1,'+x+');">'
						 	+'	<c:forEach var="item" items="${getcCopeList}" varStatus="num">'
						 	+'	<option value="${item[1]}" name="${item[0]}">${item[0]}</option>'
						 	+'	</c:forEach>'
						 	+'		</select>'
						 	+'  </td>'
						 	+'<td style="display:none;" class="cCopClass'+x+'" >'
							+'<label name="c_cother'+x+'" id="c_cother'+x+'"   >'+j[i][10]+'</label>	'					                              
							+' </td>'
						 	+' <td style="display:none;"><input type="text" id="cCope_ch_id'+x+'" name="cCope_ch_id'+x+'"  value="'+j[i][5]+'" class="form-control autocomplete" autocomplete="off" ></td>'   
						 
						 	+'</tr>');
					
						$("#c_cvalue"+x).val(j[i][1]); 
						$("#c_cvalue_lb"+x).text($( "#c_cvalue"+x+" option:selected" ).text());
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
			$(".select_hide").hide();
			cCope=v;
			$("input#cCope_count").val(v);
			$("input#cCopeOld_count").val(v);
			$("#cCope_add"+v).show(); 														
			}
			
	  });
}


function getoCopeDetails(){
	
	  var p_id=$('#comm_id').val(); 
	  var app_status=1;
	 var Shape='C_O';
	 var i=0;
	  $.post('madical_cat_GetData_xml?' + key + "=" + value, {p_id:p_id,Shape:Shape,app_status:app_status }, function(j){
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
						 	+'<label id="c_ovalue_lb'+x+'"></label><select class="select_hide" name="c_ovalue'+x+'" id="c_ovalue'+x+'" '
						 	+'	class="form-control-sm form-control"  onchange="onCopeChangebt(this,2,'+x+');">'
						 	+'	<c:forEach var="item" items="${getoCopeList}" varStatus="num">'
						 	+'	<option value="${item[1]}" name="${item[0]}">${item[0]}</option>'
						 	+'	</c:forEach>'
						 	+'		</select>'
						 	+'  </td>'	 	
						 	
						 	+' <td style="display:none;"><input type="text" id="oCope_ch_id'+x+'" name="oCope_ch_id'+x+'"  value="'+j[i][5]+'" class="form-control autocomplete" autocomplete="off" ></td>'   
						 	
						 	+'</tr>');
					$.ajaxSetup({
						async : false
					});
						$("#c_ovalue"+x).val(j[i][1]);
						
						$("#c_ovalue_lb"+x).text($( "#c_ovalue"+x+" option:selected" ).text());
						var thisT = document.getElementById('c_ovalue'+x)
					
						if(x==1){onCopeChangebt(thisT,2,x);}
						
						if(x>1){
						$("#c_ovalue"+x+" option[value='0']").remove();
						$("#c_ovalue"+(x-1)+" option[value='0']").remove();
						}
		
		}
			$(".select_hide").hide();
			oCope=v;
			$("input#oCope_count").val(v);
			$("input#oCopeOld_count").val(v);
			$("#oCope_add"+v).show(); 														
			}
			
	  });
}

function getpCopeDetails(){
	
	  var p_id=$('#comm_id').val(); 
	  var app_status=1;
	 var Shape='C_P';
	 var i=0;
	  $.post('madical_cat_GetData_xml?' + key + "=" + value, {p_id:p_id,Shape:Shape,app_status:app_status }, function(j){
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
						 	+'<label id="c_pvalue_lb'+x+'"></label><select class="select_hide" name="c_pvalue'+x+'" id="c_pvalue'+x+'" '
						 	+'	class="form-control-sm form-control"  onchange="onCopeChangebt(this,3,'+x+');">'
						 	+'	<c:forEach var="item" items="${getpCopeList}" varStatus="num">'
						 	+'	<option value="${item[1]}" name="${item[0]}">${item[0]}</option>'
						 	+'	</c:forEach>'
						 	+'		</select>'
						 	+'  </td>'	 	
						 	+' <td style="display:none;"><input type="text" id="pCope_ch_id'+x+'" name="pCope_ch_id'+x+'"  value="'+j[i][5]+'" class="form-control autocomplete" autocomplete="off" ></td>'   
						 
						 	+'</tr>');
					$.ajaxSetup({
						async : false
					});
						$("#c_pvalue"+x).val(j[i][1]); 
						$("#c_pvalue_lb"+x).text($( "#c_pvalue"+x+" option:selected" ).text());
						
						var thisT = document.getElementById('c_pvalue'+x)
						if(x==1){onCopeChangebt(thisT,3,x);}
						
						if(x>1){
						$("#c_pvalue"+x+" option[value='0']").remove();
						$("#c_pvalue"+x+" option[value='1']").remove();
						$("#c_pvalue"+(x-1)+" option[value='0']").remove();
						$("#c_pvalue"+(x-1)+" option[value='1']").remove();
						}
		
		}
				$(".select_hide").hide();
			pCope=v;
			$("input#pCope_count").val(v);
			$("input#pCopeOld_count").val(v);
			$("#pCope_add"+v).show(); 														
			}
			
	  });
}


function geteCopeDetails(){
	
	  var p_id=$('#comm_id').val(); 
	  var app_status=1;
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
						 	+'<label id="c_evalue_lb'+x+'"></label>'
						 	+'<select style="display:none" name="c_evalue'+x+'" id="c_evalue'+x+'" '
						 	+'	class="form-control-sm form-control"  onchange="onECopeChange(this,'+x+'); onCopeChangebt(this,4,'+x+');">'
						 	+'	<c:forEach var="item" items="${geteCopeList}" varStatus="num">'
						 	+'	<option value="${item[1]}" name="${item[0]}">${item[0]}</option>'
						 	+'	</c:forEach>'
						 	+'		</select>'
						 	+'  </td>'	 	
						 	+'<td style="display:none;" class="eCopClass'+x+'">'
						 	+'<label id="c_esubvalue_lb'+x+'"></label>'
						 	+'<select class="select_hide" style="display:none" name="c_esubvalue'+x+'" id="c_esubvalue'+x+'" onchange="onECopeSubChange(this,'+x+')"'
						 	+'			class="form-control-sm form-control" >'
						 	+'			<option value="0">--Select--</option>'																
						 	+'			<c:forEach var="item" items="${getesubCopeList}" varStatus="num">'
						 	+'				<option value="${item[1]}" name="${item[0]}">${item[0]}</option>'
						 	+'			</c:forEach></select>   </td>'
						 	+'<td style="display:none;" class="eCop_otherClass'+x+'" >'
						 	+'	 <label name="c_esubvalueother'+x+'" id="c_esubvalueother'+x+'">'+j[i][10]+'</label>'						                              
						 	+'	   </td>'
						 	+' <td style="display:none;"><input type="text" id="eCope_ch_id'+x+'" name="eCope_ch_id'+x+'" value="'+j[i][5]+'"  class="form-control autocomplete" autocomplete="off" ></td>'   						 
						 	+'</tr>');
					$.ajaxSetup({
						async : false
					});
						$("#c_evalue"+x).val(j[i][1]); 
						$("#c_esubvalue"+x).val(j[i][9]);
						 $('#c_evalue_lb'+x).text($( "#c_evalue"+x+" option:selected" ).text());
						 if(j[i][9]!=null && j[i][9]!=0)
							$('#c_esubvalue_lb'+x).text($( "#c_esubvalue"+x+" option:selected" ).text());
						var thisT = document.getElementById('c_evalue'+x)
						onECopeChange(thisT,x);
						
						if(x==1){onCopeChangebt(thisT,4,x);}
						
						if(x>1){
						$("#c_evalue"+x+" option[value='0']").remove();
						$("#c_evalue"+(x-1)+" option[value='0']").remove();
						
						}
				
		}
				
				$(".select_hide").hide();
			eCope=v;
			$("input#eCope_count").val(v);
			$("input#eCopeOld_count").val(v);
			$("#eCope_add"+v).show(); 														
			}
			
	  });
}

//************************************* END MEDICAL *****************************//
function get_deserter(){
	
	<c:if test="${deserterV.size() != 0}">
	if ('${deserterV.get(0).dt_recovered}' == "" || '${deserterV.get(0).dt_recovered}' == null || '${deserterV.get(0).dt_recovered}' == "null"){
		
		$("#div_dt_rec").hide();
		$("#des_ch_id").val('${deserterV.get(0).id}');
		
		$("#arm_type_val").val('${deserterV.get(0).arm_type}');
		$("#arm_type").text($( "#arm_type_val option:selected" ).text());
		
		if('${deserterV.get(0).arm_type}' == 1){
			   $("#div_weapon").show(); 
			   $("#arm_type_weapon").text('${deserterV.get(0).arm_type_weapon}');
		}else {
			    $("#div_weapon").hide(); 
				$("#arm_type_weapon").val("");
		}
		
		$("#dt_desertion").text(ParseDateColumncommission('${deserterV.get(0).dt_desertion}'));
		
		 $("#desertion_cause_val").val('${deserterV.get(0).desertion_cause}');
		 $("#desertion_cause").text($( "#desertion_cause_val option:selected" ).text());
		
		if ('${deserterV.get(0).desertion_cause}' == 3){
			$("#div_cause").show(); 
			$("#ot_desertion_cause").text('${deserterV.get(0).ot_desertion_cause}');
		}else{
			$("#div_cause").hide(); 
	 		$('#ot_desertion_cause').val("");
		}
		$("#indl_class").text('${deserterV.get(0).indl_class}');
		
	}else{
		
		if('${deserterV.get(0).arm_type}' == 1){
			$("#div_arms_rec").show();
			if('${deserterV.get(0).recovered_arms}'!=0){
			$("#recovered_arms_val").val('${deserterV.get(0).recovered_arms}');
			$("#recovered_arms").text($( "#recovered_arms_val option:selected" ).text());
			}
		}
		$("#div_dt_rec").show();
		$("#des_ch_id").val('${deserterV.get(0).id}');
		
		$("#arm_type_val").val('${deserterV.get(0).arm_type}');
		$("#arm_type").text($( "#arm_type_val option:selected" ).text());
		 
		if('${deserterV.get(0).arm_type}' == 1){
			   $("#div_weapon").show(); 
			   $("#arm_type_weapon").text('${deserterV.get(0).arm_type_weapon}');
		}else {
			    $("#div_weapon").hide(); 
				$("#arm_type_weapon").val("");
		}
		
		$("#dt_desertion").text(ParseDateColumncommission('${deserterV.get(0).dt_desertion}'));
		
		 $("#dt_recovered").text(ParseDateColumncommission('${deserterV.get(0).dt_recovered}'));
		 
		 $("#desertion_cause_val").val('${deserterV.get(0).desertion_cause}');
		 $("#desertion_cause").text($( "#desertion_cause_val option:selected" ).text());
		 
		 
		if ('${deserterV.get(0).desertion_cause}' == 3){
			 $("#div_cause").show(); 
			 $("#ot_desertion_cause").text('${deserterV.get(0).ot_desertion_cause}');
		}else{
			$("#div_cause").hide(); 
	 		$('#ot_desertion_cause').val("");
		}
		
		$("#indl_class").text('${deserterV.get(0).indl_class}');
		
	}
	
	</c:if>
	var census_id=$('#census_id').val();
	var comm_id=$('#comm_id').val();
	$.post('deserter_GetDataA?' + key + "=" + value,{census_id:census_id,comm_id:comm_id}, function(j){
		var v=j.length;
		if(v!=0){
			
			if(j[0].arm_type == 1){
				$("#div_arms_rec").show();
				if(j[0].recovered_arms!=0){
				$("#recovered_arms_val").val(j[0].recovered_arms);
				$("#recovered_arms").text($( "#recovered_arms_val option:selected" ).text());
				}
			}
			
			$("#div_dt_rec").show();
			$("#des_ch_id").val(j[0].id);
			$("#deserter").val(j[0].deserter);
			$("#arm_type_val").val(j[0].arm_type);
			$("#arm_type").text($( "#arm_type_val option:selected" ).text());
			if(j[0].arm_type == 1){
				   $("#div_weapon").show(); 
				   $("#arm_type_weapon").text(j[0].arm_type_weapon);
			}else {
				    $("#div_weapon").hide(); 
					$("#arm_type_weapon").text("");
			}
			
			$("#dt_desertion").text(ParseDateColumncommission(j[0].dt_desertion));
			$("#dt_recovered").text(ParseDateColumncommission(j[0].dt_recovered));
			
			$("#desertion_cause_val").val(j[0].desertion_cause);
			 $("#desertion_cause").text($( "#desertion_cause_val option:selected" ).text());
			 $("select#desertion_cause").attr('readonly', true);
			if (j[0].desertion_cause == 3){
				$("#div_cause").show(); 
				$("#ot_desertion_cause").text(j[0].ot_desertion_cause);
			}else{
				$("#div_cause").hide(); 
		 		$('#ot_desertion_cause').text("");
			}
			$("#indl_class").text(j[0].indl_class);
		}
	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
} 
function get_ta_status(){
	
	<c:if test="${ChangeOfTA.size() != 0}">
		$('#ta_id').val('${ChangeOfTA.get(0).id}');
	 	$("#ta_authority").text('${ChangeOfTA.get(0).ta_status_authority}');
	 	$("#ta_date_of_authority").text(convertDateFormate('${ChangeOfTA.get(0).ta_authority_date}' ));
	 	$("#ta_val").val('${ChangeOfTA.get(0).ta_status}');
	 	$('#ta_status').text($( "#ta_val option:selected" ).text());
	 	$("#ta_date").text(convertDateFormate('${ChangeOfTA.get(0).date_of_ta_status}' ));
					</c:if>
	  
}

function get_post_in_data(){
	
	var comm_id=$("#comm_id").val();
	var status="1";
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
			$("#dt_of_tos").text(ParseDateColumncommission(dt_of_tos));
			
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
function get_cda(){
	 var comm_id=$('#comm_id').val(); 
	 var status="1";
	 var i=0;
	  $.post('cda_GetData_XML?' + key + "=" + value, {comm_id:comm_id,status:status}, function(j){
			var v=j.length;
			
			if(v!=0){
				
				$('#idr').val(j[i].id);
				 $("#cda_acc_no").text(j[i].cda_acc_no);
		
				 $("#clear").hide();
				 
				 $("#personnel_no_c").text('${personnel_no2}');
//				 $("input#personnel_no_c").attr('readonly', true);
				 $("#cda_rank").text($("#hd_p_rank").val());
				 $("#cda_cadet_name").text($("#cadet_name").text());
				 $("#cda_app_sus_no").text($("#app_sus_no").text());
				 $("#cda_app_unit_name").text($("#app_unit_name").text());
			}
	  });
}	

</script>
