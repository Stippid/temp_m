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
<style>
.selectBox {
	position: relative;
}

.selectBox select {
	width: 100%;
	font-weight: bold;
}

.overSelect {
	position: absolute;
	left: 0;
	right: 0;
	top: 0;
	bottom: 0;
}

.checkboxes {
	display: none;
	border: 1px #dadada solid;
}

.checkboxes label {
	margin-left: 10px;
	text-align: left;
	display: block;
}

.checkboxes label:hover {
	background-color: #1e90ff;
}
td {
    word-break: break-all;
}
</style>
<script>
// window.onscroll = function() {myFunction()};

// function myFunction() {
// 	navbar = document.getElementById("Personnel_no_form");
//   if (window.pageYOffset >= 110) {
//     navbar.classList.add("sticky");
//   } else {
//     navbar.classList.remove("sticky");
//   }
// }


function Cancel(){
  	
	$("#personnel_no1").val($("#personnel_noV").val()) ;
	$("#status1").val($("#statusV").val()) ;
	$("#rank1").val($("#rankV").val()) ;
	$("#comm_id").val($("#comm_idV").val()) ;
	$("#unit_name1").val($("#unit_nameV").val()) ;
	$("#unit_sus_no1").val($("#unit_sus_noV").val()) ;
	$("#icstatus").val($("#icstatusV").val()) ;
	 
	document.getElementById('searchForm').submit();
}

 


</script>
<c:url value="GetSearch_UpdateOfficerData" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="personnel_no1">
		<input type="hidden" name="personnel_no1" id="personnel_no1" value="0"/>		
		<input type="hidden" name="rank1" id="rank1" value="0"/>
		<input type="hidden" name="status1" id="status1" value="0"/>
		<input type="hidden" name="unit_name1" id="unit_name1"  />
		<input type="hidden" name="unit_sus_no1" id="unit_sus_no1"  />
		<input type="hidden" name="icstatus" id="icstatus"  />
		<input type="hidden" name="cr_by1" id="cr_by1"   />
        <input type="hidden" name="cr_date1" id="cr_date1"   />
	</form:form> 
	
	
<div class="container-fluid" align="center">
<form  id="Personnel_no_form" >
 		<div class="animated fadeIn">
	    	<div class="" align="center">
		<div class="card">	
			<div class="panel-group" id="accordion">
				<div class="panel panel-default" id="insp_div1">
					
					<div class="panel-heading">
						<h4 class="panel-title main_title" id="insp_div5">
						<b>VIEW HISTORY/CANCEL DATA OF UNIT OFFR 
						<!-- <input type="text" pattern="^[a-zA-Z0-9]+$" />
						 <input type="text" name="count"  onkeypress="return /[0-9a-zA-Z]/i.test(event.key)"> --> </b>
						</h4>
					</div>
							<!-- <div class="col-md-12" style="padding-top: 20px;">	              					
	              				<div class="col-md-6">
	              						<div class="row form-group">
						               <div class="col-md-6">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Personal No</label>
						                </div>
						               <div class="col-md-6">
						                    <input type="text" id="personnel_no" name="personnel_no" class="form-control autocomplete" autocomplete="off"  > 						                   
						               		<input type="hidden" id="comm_id" name="comm_id" class="form-control autocomplete" autocomplete="off"  > 						                   
						                    <input type="hidden" id="census_id" name="census_id" value="0" class="form-control autocomplete" autocomplete="off">
						                </div>
						                 <input type="button" class="btn btn-success btn-sm" id="btn1" value="Process" onclick="return personal_number();">
						            </div> 
	              				</div> 
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Name</label>
						                </div>
						                <div class="col-md-8">
						                 <label class=" form-control-label" id="cadet_name"></label>		
						                 </div>
						                 <div class="col-md-4">
						                    <label class=" form-control-label"> Rank</label>
						                </div>
						                <div class="col-md-8">
						                 <label class=" form-control-label" id="p_rank"></label>	
						                 </div>
						                <div class="col-md-4">
						                    <label class=" form-control-label"> Date of Birth</label>
						                </div>
						                <div class="col-md-8">
						                 <label class=" form-control-label" id="dob"></label>		
						                 </div>
						            </div>
	              				</div>
	              			</div>	 -->
	              			
	              					<div class="col-md-12" style="padding-top: 5px; padding-left: 250px;">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong>Personal No</label>
											</div>
											<div class="col-md-8">
												<input type="text" id="personnel_no" name="personnel_no" class="form-control autocomplete" autocomplete="off"  > 						                   
							               		<input type="hidden" id="comm_id" name="comm_id" class="form-control autocomplete" autocomplete="off"  > 						                   
							                    <input type="hidden" id="census_id" name="census_id" value="0" class="form-control autocomplete" autocomplete="off">
							                     <input type="hidden" id="status" name="status" value="0" class="form-control autocomplete" autocomplete="off">
											
											
											
											
											</div>
										</div>
									</div>

									<div class="col-md-6" id="process_btn">
									     <input type="button" class="btn btn-success btn-sm" id="btn1" value="Process" onclick="return personal_number();">
									</div>
								</div> 
	              				<!-- janki -->
										<input type="hidden" id="personnel_noV" name="personnel_noV" class="form-control autocomplete"  > 
						                 <input type="hidden" id="statusV" name="statusV" class="form-control autocomplete"  > 
						                 <input type="hidden" id="rankV" name="rankV" class="form-control autocomplete"  > 
						                 <input type=hidden id="comm_idV" name="comm_idV" class="form-control autocomplete"  > 
						                 <input type="hidden" id="unit_nameV" name="unit_nameV" class="form-control autocomplete"  > 
						                 <input type="hidden" id="unit_sus_noV" name="unit_sus_noV" class="form-control autocomplete"  > 
											<input type="hidden" id="icstatusV" name="icstatusV" class="form-control autocomplete"  >
										
	              	 	
			            
			            <div class="card-footer" align="right" class="form-control" id="back_bt_div" style="display: none;">
							<!-- <a href="Search_UpdateOfficerDataUrl" class="btn btn-primary btn-sm" >Back</a>  -->
							<input type="button"   class="btn btn-info btn-sm" onclick="Cancel();" value="Back">   
			            </div> 
					 			
					</div>
				</div>
		</div>
	</div>
	</div>
</form>

<!-- START CDA ACCOUNT NO -->
<!-- <div id = "div_update_data" style="display: none;" class="subcontent"> -->
<div id="maindetailsdiv" >
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
						                   <input type="hidden" class=" form-control-label" id="comm_date" >
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
						                    <label class=" form-control-label"><strong style="color: red;"></strong> ID Card No</label>
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
						                     <label class=" form-control-label">  Arm/Service</label>
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

<div id="moredetailsdiv">
<!-- Start Change of Rank  -->
<c:if test="${ChangeOfRank != 0}">
	 <form id="form_ChangeOfRank">
		<div class="card">
			<div class="panel-group" id="accordion4">
				<div class="panel panel-default" id="a_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title red" id="a_div5">
								<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion4" href="#" id="a_final" onclick="divN(this)"
								><b>CHANGE IN RANK</b></a>
						</h4>
					</div>
					<div id="collapse1a" class="panel-collapse collapse">
			                <div class="card-body card-block">
							
							
							<table id="changeOfRank_table" class="table-bordered " style="margin-top: 10px; width: 100%;">
								<thead style="color: white; text-align: center;">
									<tr>
										<th style="width: 2%;">Sr No</th>
										<th style="width: 10%;">Authority</th>
										<th style="width: 10%;">Date of Authority</th>
										<th style="width: 10%;">Rank Type</th>
										<th style="width: 10%;">Rank</th>
										<th style="width: 10%;">Date of Rank</th>
										<th style="width: 10%;">Updated By</th>
										<th style="width: 10%;">Updated Date</th>
										<th style="width: 10%;">Action</th>
									</tr>
								</thead>
								<tbody data-spy="scroll" id="changeOfRank_tbody" style="min-height: 46px; max-height: 650px; text-align: center;">
									
								</tbody>
							</table>
							

						  
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	</c:if>
	 <!-- END Change of Rank  -->
	 <!-- START CHANGE OF NAME DETAIL -->
	 <c:if test="${ChangeOfName != 0}">
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
			           
					    <table id="changeOfName_table" class="table-bordered " style="margin-top: 10px; width: 100%;">
								<thead style="color: white; text-align: center;">
									<tr>
										<th style="width: 2%;">Sr No</th>
										<th style="width: 10%;">Authority</th>
										<th style="width: 10%;">Date of Authority</th>
										<th style="width: 10%;">Name</th>
										<th style="width: 10%;">Updated By</th>
										<th style="width: 10%;">Updated Date</th>
										<th style="width: 10%;">Action</th>
									</tr>
								</thead>
								<tbody data-spy="scroll" id="changeOfName_tbody" style="min-height: 46px; max-height: 650px; text-align: center;">
									
								</tbody>
							</table>
							 	
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
	<!-- END CHANGE OF NAME DETAIL -->
	<!-- START CHANGE OF APPOINTMENT DETAIL -->
	 <c:if test="${ChngAppointment != 0}">
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
					     
					     <table id="Appointment_table" class="table-bordered " style="margin-top: 10px; width: 100%;">
								<thead style="color: white; text-align: center;">
									<tr>
										<th style="width: 2%;">Sr No</th>
										<th style="width: 10%;">Authority</th>
										<th style="width: 10%;">Date of Authority</th>
										<th style="width: 10%;">Appointment</th>
										<th style="width: 10%;">Date of Appointment</th>
<!-- 										<th style="width: 10%;">X Unit Name</th> -->
<!-- 										<th style="width: 10%;">X List Location</th> -->
										<th style="width: 10%;">Updated By</th>
										<th style="width: 10%;">Updated Date</th>
										<th style="width: 10%;">Action</th>
									</tr>
								</thead>
								<tbody data-spy="scroll" id="Appointment_tbody" style="min-height: 46px; max-height: 650px; text-align: center;">
									
								</tbody>
							</table>
							
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	</c:if>
	<!-- END CHANGE OF APPOINTEMENT DETAIL -->
	<!-- START IDENTY CARD DETAIL -->
	 <c:if test="${IdentityCard != 0}"> 
	<form id="form_identity_card">
		<div class="card">
			<div class="panel-group" id="accordion43">
				<div class="panel panel-default" id="d_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title red" id="d_div5">
							<a class="droparrow collapsed " data-toggle="collapse"
								data-parent="#accordio43" href="#" id="d_final" onclick="divN(this)"
								><b>CHANGE IN IDENTITY CARD</b></a>
						</h4>
					</div>
					<div id="collapse1d" class="panel-collapse collapse">
					<div class="card-body card-block">
					
					<table id="Identity_card_table" class="table-bordered " style="margin-top: 10px; width: 100%;">
								<thead style="color: white; text-align: center;">
									<tr>
										<th style="width: 2%;">Sr No</th>
										<th style="width: 10%;">ID Card no</th>
										<th style="width: 10%;">Issuing Date</th>
										<th style="width: 10%;">Issuing Authority</th>
										<th style="width: 10%;">Visible Identification Marks</th>
										<th style="width: 10%;">Hair Colour</th>
										<th style="width: 10%;">Eye Colour</th>
										<th style="width: 10%;">Photograph</th>
										<th style="width: 10%;">Updated By</th>
										<th style="width: 10%;">Updated Date</th>
										<th style="width: 10%;">Action</th>
									</tr>
								</thead>
								<tbody data-spy="scroll" id="Identity_card_tbody" style="min-height: 46px; max-height: 650px; text-align: center;">
									
								</tbody>
							</table>
							
					</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	</c:if>
	<!-- END IDENTY CARD DETAIL -->
	<!-- START RELIGION DETAIL -->
	<c:if test="${religion != 0}">
	<form id="form_religion">
		<div class="card">
			<div class="panel-group" id="accordion6">
				<div class="panel panel-default" id="e_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title lightblue" id="e_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion6" href="#" id="e_final" onclick="divN(this)"
								><b>CHANGE IN RELIGION</b></a>
						</h4>
					</div>
					<div id="collapse1e" class="panel-collapse collapse">
					  <div class="card-body card-block">
					  
					  <table id="ChangeOfReligion_table" class="table-bordered " style="margin-top: 10px; width: 100%;">
								<thead style="color: white; text-align: center;">
									<tr>
										<th style="width: 2%;">Sr No</th>
										<th style="width: 10%;">Authority</th>
										<th style="width: 10%;">Date of Authority</th>
										<th style="width: 10%;">Religion</th>
										<th style="width: 10%;">Updated By</th>
										<th style="width: 10%;">Updated Date</th>
										<th style="width: 10%;">Action</th>
									</tr>
								</thead>
								<tbody data-spy="scroll" id="ChangeOfReligion_tbody" style="min-height: 46px; max-height: 650px; text-align: center;">
									
								</tbody>
							</table>
						
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	</c:if>
	<!-- END RELIGION DETAIL -->
	<!-- START MARITAL EVENTS DETAIL -->
	<c:if test="${Marital != 0 || SpouseQualification!=0 }">
	  <form id="forminspupdate">
		<div class="card">
			<div class="panel-group" id="accordion2">
				<div class="panel panel-default" id="f_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title lightblue" id="f_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion2" href="#" id="f_final" onclick="divN(this)"
								><b>CHANGE IN MARITAL STATUS</b></a>
						</h4>
					</div>
					<div id="collapse1f" class="panel-collapse collapse">
						<div class="card-body card-block">
						<div class="card-header"><h5>Marital Details</h5> <h6 class="enter_by"><span style="font-size:12px;color:red;"></span></h6></div>
						<table id="Marital_status_table" class="table-bordered " style="margin-top: 10px; width: 100%;">
								<thead style="color: white; text-align: center;">
									<tr>
										<th style="width: 2%;">Sr No</th>
										<th >Authority</th>
										<th >Date of Authority</th>
										<th >Marital status</th>
										<th >Spouse Name</th>
										<th >Date of Marriage</th>
										<th >Spouse Place of Birth</th>
										<th >Spouse Nationality</th>
										<th >Spouse Date of Birth</th>
										<th >Spouse Aadhaar Card No.</th>
										<th >Spouse Service</th>
										<th >Spouse Personnel No.</th>
										<th >Spouse Pan Card No.</th>	
										<th >Separated From Date</th>	
										<th >Separated To Date</th>	
										<th >Date of Divorced/Death</th>	
										<th >Updated By</th>
										<th >Updated Date</th>
										<th >Action</th>
									</tr>
								</thead>
								<tbody data-spy="scroll" id="Marital_status_tbody" style="min-height: 46px; max-height: 650px; text-align: center;">
									
								</tbody>
							</table>
							
							<br/>
								<div class="card-header"><h5>Spouse Qualification Details</h5> <h6 class="enter_by"><span style="font-size:12px;color:red;"></span></h6></div>
								<table id="spouse_quali_table" class="table-bordered " style="margin-top: 10px; width: 100%;">
								<thead style="color: white; text-align: center;">
									<tr>
										<th style="width: 2%;">Sr No</th>
										<th >Spouse Name</th> 
										<th >Qualification Type</th> 
										<th >Examination Pass</th>
										<th >Degree Acquired</th>
										<th >Specialization</th>
										<th >Year of Passing</th>
										<th >Div/Grade</th>
										<th >Institute & Place</th>
										<th >Subject</th>
										<th >Updated By</th>
										<th >Updated Date</th>
										<th >Action</th>
									</tr>
								</thead>
								<tbody data-spy="scroll" id="spouse_quali_tbody" style="min-height: 46px; max-height: 650px; text-align: center;">
									
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	</c:if>
	<!-- END MARITAL EVENTS DETAIL -->
		<!--  START DETAILS OF CHILDRENS -->
		 <c:if test="${ChildDetails != 0}">
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
								<div class="card-body-header">
									<h5></h5>
								</div>
								<table id="m_children_details_table" class="table-bordered " style="margin-top: 10px; width: 100%;">
								<thead style="color: white; text-align: center;">
									<tr>
										<th style="width: 2%;">Sr No</th>
										<th style="width: 10%;">Name</th>
										<th style="width: 10%;">Date of Birth</th>
										<th style="width: 10%;">If Specially Abled Child </th>
										<th style="width: 10%;">Relationship</th>
										<th style="width: 10%;">If Adopted Child </th>
										<th style="width: 10%;">Adoption Date </th>
										<th style="width: 10%;">Aadhaar Card No </th>
										<th style="width: 10%;">PAN Card No </th>
										<th style="width: 10%;">Updated By</th>
										<th style="width: 10%;">Updated Date</th>
										<th style="width: 10%;">Action</th>
									</tr>
								</thead>
								<tbody data-spy="scroll" id="m_children_detailstbody" style="min-height: 46px; max-height: 650px; text-align: center;">
									
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
			<c:if test="${NOK != 0}">
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
						
						<table id="NOK_address_details_table" class="table-bordered " style="margin-top: 10px; width: 100%;">
								<thead style="color: white; text-align: center;">
									<tr>
										<th style="width: 2%;">Sr No</th>
										<th style="width: 10%;">Authority</th>
										<th style="width: 10%;">Date of Authority</th>
										<th style="width: 10%;">Name of NOK</th>
										<th style="width: 10%;">Relation</th>
										<th style="width: 10%;">Contry</th>
										<th style="width: 10%;">State</th>
										<th style="width: 10%;">District</th>
										<th style="width: 10%;">Tehsil</th>
										<th style="width: 10%;">Village</th>
										<th style="width: 10%;">Pin</th>
										<th style="width: 10%;">Nearest Railway Station</th>
										<th style="width: 10%;">Livelihood</th>
										<th style="width: 10%;">Mobile No</th>
										<th style="width: 10%;">Updated By</th>
										<th style="width: 10%;">Updated Date</th>
										<th style="width: 10%;">Action</th>
									</tr>
								</thead>
								<tbody data-spy="scroll" id="NOK_address_detailstbody" style="min-height: 46px; max-height: 650px; text-align: center;">
									
								</tbody>
							</table>
						
						
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	</c:if>
	<!-- END NOK -->
	<!-- START ADDRESS -->
	<c:if test="${ChangeAdd != 0}">
	<form id="form_addr_details_form">
		<div class="card">
			<div class="panel-group" id="accordion9">
				<div class="panel panel-default" id="i_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title lightblue" id="i_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion9" href="#" id="i_final" onclick="divN(this)"
								><b>CHANGE IN ADDRESS</b></a>
						</h4>
					</div>
					<div id="collapse1i" class="panel-collapse collapse">
						<div class="card-body card-block">
						<div class="card-header"><h5>Present Domicile Address</h5> <h6 class="enter_by"><span style="font-size:12px;color:red;"></span></h6></div>
						<table id="ChangeInAddress_table" class="table-bordered " style="margin-top: 10px; width: 100%;">
								<thead style="color: white; text-align: center;">
									<tr>
										<th style="width: 2%;">Sr No</th>
										<th style="width: 10%;">Authority</th>
										<th style="width: 10%;">Date of Authority</th>
										<th style="width: 10%;">Country</th>
										<th style="width: 10%;">State</th>
										<th style="width: 10%;">District</th>
										<th style="width: 10%;">Tehsil</th>
										<th style="width: 10%;">Updated By</th>
										<th style="width: 10%;">Updated Date</th>
										<th style="width: 10%;">Action</th>
									</tr>
								</thead>
								<tbody data-spy="scroll" id="ChangeInAddress_tbody" style="min-height: 46px; max-height: 650px; text-align: center;">
									
								</tbody>
							</table>
							<div class="card-header"><h5>Permanent Address</h5> <h6 class="enter_by"><span style="font-size:12px;color:red;"></span></h6></div>
							<table id="ChangeInPerAddress_table" class="table-bordered " style="margin-top: 10px; width: 100%;">
								<thead style="color: white; text-align: center;">
									<tr>
										<th style="width: 2%;">Sr No</th>
										<th style="width: 10%;">Authority</th>
										<th style="width: 10%;">Date of Authority</th>
										<th style="width: 10%;">Country</th>
										<th style="width: 10%;">State</th>
										<th style="width: 10%;">District</th>
										<th style="width: 10%;">Tehsil</th>
										<th style="width: 10%;">Village</th>
										<th style="width: 10%;">Pin</th>
										<th style="width: 10%;">Nearest Railway Station</th>
										<th style="width: 10%;">Livelihood</th>
										<th style="width: 10%;">Border Area</th>
										<th style="width: 10%;">Updated By</th>
										<th style="width: 10%;">Updated Date</th>
										<th style="width: 10%;">Action</th>
									</tr>
								</thead>
								<tbody data-spy="scroll" id="ChangeInPerAddress_tbody" style="min-height: 46px; max-height: 650px; text-align: center;">
									
								</tbody>
							</table>
							<div class="card-header"><h5>Present Address</h5> <h6 class="enter_by"><span style="font-size:12px;color:red;"></span></h6></div>
							<table id="ChangeInPreAddress_table" class="table-bordered " style="margin-top: 10px; width: 100%;">
								<thead style="color: white; text-align: center;">
									<tr>
										<th style="width: 2%;">Sr No</th>
										<th style="width: 10%;">Authority</th>
										<th style="width: 10%;">Date of Authority</th>
										<th style="width: 10%;">Country</th>
										<th style="width: 10%;">State</th>
										<th style="width: 10%;">District</th>
										<th style="width: 10%;">Tehsil</th>
										<th style="width: 10%;">Village</th>
										<th style="width: 10%;">Pin</th>
										<th style="width: 10%;">Nearest Railway Station</th>
										<th style="width: 10%;">Livelihood</th>
										<th style="width: 10%;">Updated By</th>
										<th style="width: 10%;">Updated Date</th>
										<th style="width: 10%;">Action</th>
									</tr>
								</thead>
								<tbody data-spy="scroll" id="ChangeInPreAddress_tbody" style="min-height: 46px; max-height: 650px; text-align: center;">
									
								</tbody>
							</table>
							<div id="spouseAddressDiv" style="display: none">
							<div class="card-header"><h5>SF ACCN</h5> <h6 class="enter_by"><span style="font-size:12px;color:red;"></span></h6></div>
							<table id="ChangeInSpouseAddress_table" class="table-bordered " style="margin-top: 10px; width: 100%;">
								<thead style="color: white; text-align: center;">
									<tr>
										<th style="width: 2%;">Sr No</th>
										<th style="width: 10%;">Authority</th>
										<th style="width: 10%;">Date of Authority</th>
										<th style="width: 10%;">Country</th>
										<th style="width: 10%;">State</th>
										<th style="width: 10%;">District</th>
										<th style="width: 10%;">Tehsil</th>
										<th style="width: 10%;">Village</th>
										<th style="width: 10%;">Pin</th>
										<th style="width: 10%;">Nearest Railway Station</th>
										<th style="width: 10%;">Livelihood</th>
										<th style="width: 10%;">Stn HQ SUS No</th>
										<th style="width: 10%;">Stn HQ Name</th>
										<th style="width: 10%;">Updated By</th>
										<th style="width: 10%;">Updated Date</th>
										<th style="width: 10%;">Action</th>
									</tr>
								</thead>
								<tbody data-spy="scroll" id="ChangeInSpouseAddress_tbody" style="min-height: 46px; max-height: 650px; text-align: center;">
									
								</tbody>
							</table>
							
						</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	</c:if>
	<!-- END ADDRESS -->
	<!-- START CONTACT DETAILS -->
	 <c:if test="${CDAaccount != 0}">
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
			            
			             <table id="ContactDetails_table" class="table-bordered " style="margin-top: 10px; width: 100%;">
								<thead style="color: white; text-align: center;">
									<tr>
										<th style="width: 2%;">Sr No</th>
										<th style="width: 10%;">Gmail/Others</th>
										<th style="width: 10%;">NIC Mail</th>
										<th style="width: 10%;">Mobile No</th>
										<th style="width: 10%;">Updated By</th>
										<th style="width: 10%;">Updated Date</th>
										<th style="width: 10%;">Action</th>
									</tr>
								</thead>
								<tbody data-spy="scroll" id="ContactDetails_tbody" style="min-height: 46px; max-height: 650px; text-align: center;">
									
								</tbody>
							</table>
			            
			             
						</div>
					</div>
				</div>
			</div>
		</div>
		</form>
		</c:if>
	<!-- END CONTACT DETAILS -->
	<!-- START LANGUAGE DETAIL -->
	  <c:if test="${Language != 0 ||  FLanguage != 0}">
		<div class="card">
			<div class="panel-group" id="accordion15">
				<div class="panel panel-default" id="k_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title lightgreen" id="k_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion15" href="#" id="k_final" onclick="divN(this)"
								><b>ADD LANGUAGE</b></a>
						</h4>
					</div>
					<div id="collapse1k" class="panel-collapse collapse">
						<!-- <div class="card-body card-block"> -->
							<div class="card-body card-block" id="total_table">
								<div class="card-body-header">
									<h5></h5>
								</div>
									<div class="card-header"><h5>Indian Language</h5> <h6 class="enter_by"><span style="font-size:12px;color:red;"></span></h6></div>
									<table id="language_table" class="table-bordered " style="margin-top:10px;width: -webkit-fill-available;">
					<thead style=" color: white; text-align: center;">
						<tr>
							<th style="width: 2%;">Sr No</th>
							<th style="width: 10%;">Authority</th>
							<th style="width: 10%;">Date of Authority</th>
							<th style="width: 10%;">Indian Language</th> 
							<th style="width: 10%;">Language Standard</th>
							<th style="width: 10%;">Updated By</th>
							<th style="width: 10%;">Updated Date</th>
							<th style="width: 10%;">Action</th>
					   </tr>
					</thead>
				   <tbody data-spy="scroll" id="langtbody" style="min-height:46px; max-height:650px; text-align: center;">
						
				   </tbody> 
						</table>
							<div class="card-header"><h5>Foreign Language</h5> <h6 class="enter_by"><span style="font-size:12px;color:red;"></span></h6></div>
					<table id="foregin_language_table" class="table-bordered " style="margin-top:10px;">
								<thead style=" color: white; text-align: center;">
									<tr>
										<th style="width: 2%;">Sr No</th>
										<th style="width: 10%;">Authority</th>
										<th style="width: 10%;">Date of Authority</th>
										<th style="width: 10%;">Language</th> 
										<th style="width: 10%;">Language Standard</th>
										<th style="width: 10%;">Language Proficiency</th>
										<th style="width: 10%;">Exam Passed</th>
										<th style="width: 10%;">Updated By</th>
										<th style="width: 10%;">Updated Date</th>
										<th style="width: 10%;">Action</th>
								   </tr>
								</thead>
							   <tbody data-spy="scroll" id="flangtbody" style="min-height:46px; max-height:650px; text-align: center;">
									
							   </tbody> 
									</table>
							<!-- 			<div class="card-footer" align="center" class="form-control">
		              		 <input type="button" class="btn btn-primary btn-sm" value="Submit for Approval" onclick="validate_Lang_save();">		              
			            </div>  -->
							</div>
						<!-- </div> -->
					</div>
				</div>
			</div>
		</div>
		</c:if>
	<!-- END LANGUAGE DETAIL -->







		<!-- START QUALIFICATION DETAIL -->
		<c:if test="${Qualification != 0}">
	<form id="forminspupdate">
		<div class="card">
			<div class="panel-group" id="accordion16">
				<div class="panel panel-default" id="l_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title lightgreen" id="l_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion16" href="#" id="l_final" onclick="divN(this)"
								><b>UPDATE QUALIFICATION</b></a>
						</h4>
					</div>
					<div id="collapse1l" class="panel-collapse collapse">
						<div class="card-body card-block">
			   <table id="qualificationtable" class="table no-margin table-striped  table-hover  table-bordered report_print">
		                 <thead>
		                       <tr>
		                       <th style="width: 2%;">Sr No</th>
										<th style="width: 10%;">Authority</th>
										<th style="width: 10%;">Date of Authority</th>
										<th style="width: 10%;">Qualification Type</th> 
										<th style="width: 10%;">Examination Pass</th>
										<th style="width: 10%;">Degree Acquired</th>
										<th style="width: 10%;">Specialization</th>
										<th style="width: 10%;">Year of Passing</th>
										<th style="width: 10%;">Subject</th>
										<th style="width: 10%;">Div/Grade</th>
										<th style="width: 10%;">Institute & Place</th>
										<th style="width: 10%;">Updated By</th>
										<th style="width: 10%;">Updated Date</th>
										<th style="width: 10%;">Action</th>
										
			                       </tr>                                                        
		                  </thead> 
		                  <tbody id="qualificationtbody">
			               
		                     </tbody>
		                 </table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	</c:if>
	<!-- END QUALIFICATION DETAIL -->
	<!-- START Promotional Exam -->
	 <c:if test="${Promotional_Exam != 0}">
	<div class="card">
			<div class="panel-group" id="accordion99">
				<div class="panel panel-default" id="m_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title lightgreen" id="m_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion99" href="#" id="m_final" onclick="divN(this)"
								style="color: #90EE90;"><b>PROMOTIONAL EXAM</b></a>
						</h4>
					</div>
					<div id="collapse1m" class="panel-collapse collapse">

						<!-- <div class="card-body card-block"> -->
							<div class="card-body card-block" id="total_table">
								<div class="card-body-header">
									<h5></h5>
								</div>
								  
	              			
		          		 <table id="promo_exam_table" class="table-bordered " style="margin-top:10px; width: -webkit-fill-available;">
					<thead style=" color: white; text-align: center;">
						<tr>
						<th style="width: 2%;">Sr No</th>
										<th style="width: 10%;">Authority</th>
										<th style="width: 10%;">Date of Authority</th>
										<th style="width: 10%;">Exam</th> 
										<th style="width: 10%;">Date of Passing</th>
										<th style="width: 10%;">Updated By</th>
										<th style="width: 10%;">Updated Date</th>
										<th style="width: 10%;">Action</th>
					   </tr>
					</thead>
				   <tbody data-spy="scroll" id="promo_examtbody" style="min-height:46px; max-height:650px; text-align: center;">
						
				   </tbody> 
						</table>
							</div>
						<!-- </div> -->
					</div>
				</div>
			</div>
		</div>
		</c:if>
		<!-- END Promotional Exam -->		
		<!-- START Army Course -->
		 <c:if test="${Army_Course != 0}">
		<div class="card">

			<div class="panel-group" id="accordion98">
				<div class="panel panel-default" id="cc_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title lightgreen" id="cc_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion98" href="#" id="cc_final" onclick="divN(this)"
								style="color: #90EE90;"><b>ARMY COURSE</b></a>
						</h4>
					</div>
					<div id="collapse1cc" class="panel-collapse collapse">

						<!-- <div class="card-body card-block"> -->
							<div class="card-body card-block" id="total_table">
								<div class="card-body-header">
									<h5></h5>
								</div>
						<table id="army_course_table" class="table-bordered " style="margin-top:10px; width: -webkit-fill-available;">
								<thead style=" color: white; text-align: center;">
									<tr>
										<th style="width: 2%;">Sr No</th>
										<th style="width: 10%;">Authority</th>
										<th style="width: 10%;">Date of Authority</th>
										<th style="width: 10%;">Course Name</th> 
										<th style="width: 10%;">Course Name Abbreviation</th> 
										<th style="width: 10%;">Course Institute</th> 
										<th style="width: 10%;">Div/Grade</th>
										<th style="width: 10%;">Course Type</th>
										<th style="width: 10%;">Start Date</th>
										<th style="width: 10%;">Date of Completion</th>
										<th style="width: 10%;">Updated By</th>
										<th style="width: 10%;">Updated Date</th>
										<th style="width: 10%;">Action</th>
								   </tr>
								</thead>
							   <tbody data-spy="scroll" id="army_coursetbody" style="min-height:46px; max-height:650px; text-align: center;">
									
							   </tbody> 
									</table>
							</div>
						<!-- </div> -->
					</div>
				</div>
			</div>
		</div>
		</c:if>
		<!-- END Army Course -->
	<!-- START BPET DETAIL -->
	 <c:if test="${BEPT != 0}">
	<form id="forminspupdate">
		<div class="card">
			<div class="panel-group" id="accordion42">
				<div class="panel panel-default" id="p_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title yellow" id="p_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion42" href="#" id="p_final" onclick="divN(this)"
								><b>UPDATE BPET DETAILS</b></a>
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
											<th style="width: 10%;">BPET Result</th>
											<th style="width: 10%;">BPET QTR</th>
											<th style="width: 10%;">Year</th>
											<th style="width: 10%;">Conducted at Unit</th>
											<th style="width: 10%;">Updated By</th>
											<th style="width: 10%;">Updated Date</th>
											<th style="width: 10%;">Action</th>
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
	</form>
	</c:if>
	<!-- END BPET DETAIL -->
	<!-- START FIRING STANDERD -->
	 <c:if test="${FiringStan != 0}">
	<form id="forminspupdate">
		<div class="card">
			<div class="panel-group" id="accordion19">
				<div class="panel panel-default" id="q_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title yellow" id="q_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion19" href="#" id="q_final" onclick="divN(this)"
								><b>UPDATE FIRING STANDARD</b></a>
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
										<th style="width: 20%;">Firing Event QTR</th>
										<th style="width: 20%;">Year</th>
										<th style="width: 20%;">Conducted at Unit</th>
										<th style="width: 10%;">Updated By</th>
										<th style="width: 10%;">Updated Date</th>
										<th style="width: 10%;">Action</th>
										
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
	</form>
	</c:if>
	<!-- END FIRING STANDERD -->
	<!-- START Alergy -->
	<c:if test="${Allergy != 0}">
	<form id="forminspupdate">
		<div class="card">
			<div class="panel-group" id="accordion45">
				<div class="panel panel-default" id="r_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title lightred" id="r_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion45" href="#" id="r_final" onclick="divN(this)"
								><b>Known Allergy</b></a>
						</h4>
					</div>
					<div id="collapse1r" class="panel-collapse collapse">
						<!-- <div class="card-body card-block"> -->
							<div class="card-body card-block" id="total_table">
								<div class="card-body-header">
									<h5></h5>
								</div>
								<table id="allergic_table" class="table-bordered " style="margin-top:10px; width: -webkit-fill-available;">
					<thead style=" color: white; text-align: center;">
						<tr>
							<th style="width: 2%;">Sr No</th>
							<th style="width: 10%;">Allergy</th> 
							<th style="width: 10%;">Updated By</th>
							<th style="width: 10%;">Updated Date</th>
							<th style="width: 10%;">Action</th>
					   </tr>
					</thead>
				   		<tbody data-spy="scroll" id="allergictbody" style="min-height: 46px; max-height: 650px; text-align: center;">
							
				   </tbody> 
						</table>
							</div>
							<div class="card-footer" align="right">
							</div>
						<!-- </div> -->
					</div>
				</div>
			</div>
		</div>
	</form>
	</c:if>
	<!-- END Allergy  -->
	   <!-- START MEDICAL -->
	    <c:if test="${MedDetails != 0}">
	<form id="madical_category_form">
		<div class="card">
			<div class="panel-group" id="accordion32">
				<div class="panel panel-default" id="s_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title lightred" id="s_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion32" href="#" id="s_final" onclick="divN(this)"
								><b>CHANGE IN MEDICAL</b></a>
						</h4>
					</div>
					<div id="collapse1s" class="panel-collapse collapse">
						<div class="card-body card-block">
						<table id="medical_table" class="table-bordered " style="margin-top:10px; width: -webkit-fill-available;">
					<thead style=" color: white; text-align: center;">
						<tr>
											<th style="width: 2%;" rowspan="2">Sr No</th>
											<th style="width: 10%;" rowspan="2">Authority</th>

											<th style="width: 10%;" rowspan="2">Date of Authority</th>
											<th style="width: 10%;" colspan="5">SHAPE</th>
											<th style="width: 10%;" colspan="4">COPE</th>
											
											
											<th style="width: 10%;" rowspan="2">Updated by</th>

											<th style="width: 10%;" rowspan="2">Updated Date</th>


											<th style="width: 10%;" rowspan="2">Action</th>
					   </tr>
					   <tr>
					  <th style="width: 10%;">S</th>

											<th style="width: 10%;">H</th>

											<th style="width: 10%;">A</th>
											<th style="width: 10%;">P</th>
											<th style="width: 10%;">E</th>

											<th style="width: 10%;">C</th>
											<th style="width: 10%;">O</th>
											<th style="width: 10%;">P</th>

											<th style="width: 10%;">E</th>
					   </tr>
					</thead>
				   		<tbody data-spy="scroll" id="medicaltbody" style="min-height: 46px; max-height: 650px; text-align: center;">
							
				   </tbody> 
						</table>
					</div>
					</div>
					
							</div>
	    
						</div>
					</div>
				
		
		
	</form>
	</c:if>
	<!-- END MEDICAL -->
	<!-- START FOREIGN COUNTRY -->
	 <c:if test="${ForeignCountry != 0}">   
	<form id="forminspupdate">
		<div class="card">
			<div class="panel-group" id="accordion18">
				<div class="panel panel-default" id="t_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title lightred" id="t_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion18" href="#" id="t_final" onclick="divN(this)"
								><b>UPDATE VISIT TO FOREIGN COUNTRY</b></a>
						</h4>
					</div>
					<div id="collapse1t" class="panel-collapse collapse">
						<!-- <div class="card-body card-block"> -->
							<div class="card-body card-block" id="total_table">
								<div class="card-body-header">
									<h5></h5>
								</div>
							<table id="foreign_country_visit" class="table-bordered " style="margin-top:10px;">
								<thead style=" color: white; text-align: center;">
									<tr>
										<th style="width: 2%;">Sr No</th>
										<th style="width: 10%;">Country Visited</th> 
										<th style="width: 10%;">From</th>
										<th style="width: 10%;">To</th>
										<th style="width: 10%;">Duration</th>
										<th style="width: 10%;">Purpose of Visit</th>
										<th style="width: 10%;">Updated By</th>
										<th style="width: 10%;">Updated Date</th>
										<th style="width: 10%;">Action</th>
								   </tr>
								</thead>
							   <tbody data-spy="scroll" id="foregin_Country_tbody" style="min-height:46px; max-height:650px; text-align: center;">
				   					</tbody> 
								</table>
							</div>
						<!-- </div> -->
					</div>
				</div>
			</div>
		</div>
	</form>
	</c:if>
	<!-- END FOREIGN COUNTRY -->
	 <!-- START AWARD & MEDAL -->
	  <c:if test="${AwardsMedal != 0}">
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
                                                        <div class="card-body card-block" id="total_table">
                                                                <div class="card-body-header">
                                                                        <h5></h5>
                                                                </div>
                                                        <table id="awardsNmedal_table" class="table-bordered " style="margin-top:10px;">
                                                                <thead style=" color: white; text-align: center;">
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
                                                                                <th style="width: 10%;">Updated By</th>
                                                                                <th style="width: 10%;">Updated Date</th>                                                                                                                                                      
                                                                               <th style="width: 10%;">Action</th>
                                                                   </tr>
                                                                </thead>
                                                           <tbody data-spy="scroll" id="awardsNmedal_tbody" style="min-height:46px; max-height:650px; text-align: center;">
                                                                      
                                                           </tbody> 
                                                                                </table>
                                                        </div>
                                                <!-- </div> -->
                                        </div>
                                </div>
                        </div>
                </div>
        </form>
        </c:if>
        <!-- END AWARD & MEDAL -->
        <!-- START BTLE & PHY -->
          <c:if test="${btel_cas != 0}">
	    <form id="form_battle_physical_casuality">
		<div class="card">
			<div class="panel-group" id="accordion13">
				<div class="panel panel-default" id="v_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title lightred" id="v_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion13" href="#" id="v_final" onclick="divN(this)"
								><b>UPDATE BATTLE & PHYSICAL CASUALTY</b></a>
						</h4>
					</div>
					<div id="collapse1v" class="panel-collapse collapse">
						<div class="card-body card-block">
						  <table id="battle_cause_table" class="table-bordered " style="margin-top: 10px; width: 100%;">
								<thead style="color: white; text-align: center;">
									<tr>
										<th style="width: 2%;">Sr No</th>
										<th style="font-size: 10px">Authority</th>
										<th style="font-size: 10px">Date of Authority</th>
										<th style="font-size: 10px">Date of Casualty</th>
										<th style="font-size: 10px">Recommended For</th>
										<th style="font-size: 10px">Nature of Casualty</th>
										<th style="font-size: 10px">Name of Operation</th>
										<th style="font-size: 10px">Cause of Casualty</th>
										<th	 style="font-size: 10px">Unit</th>
										<th style="font-size: 10px">Diagnosis</th>
										<th style="font-size: 10px;">Updated By</th>
										<th style="font-size: 10px;">Updated Date</th>
										
										<th style="width: 10%;">Action</th>
									</tr>
								</thead>
								<tbody data-spy="scroll" id="battle_cause_tbody" style="min-height: 46px; max-height: 650px; text-align: center;">
									
								</tbody>
							</table>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		
	</form>
	</c:if>
	<!-- END BTLE & PHY ---->
	<!-- START DISCIPLINE -->
	<c:if test="${Discipline != 0}">
	<form id="form_disipline">
		<div class="card">
			<div class="panel-group" id="accordion44">
				<div class="panel panel-default" id="w_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title lightred" id="w_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion44" href="#" id="w_final" onclick="divN(this)"
								><b>UPDATE Discipline</b></a>
						</h4>
					</div>
					<div id="collapse1w" class="panel-collapse collapse">
					<div class="card-body card-block">
						   <table id="updateDiscipline_table" class="table-bordered " style="margin-top: 10px; width: 100%;">
								<thead style="color: white; text-align: center;">
									<tr>
										<th style="width: 2%;">Sr No</th>
										<th style="width: 10%;">Authority</th>
										<th style="width: 10%;">Date of Authority</th>
										<th style="width: 10%;">Army Act Sec</th>
										<th style="width: 10%;">Sub Clause</th>
										<th style="width: 10%;">Trialed By</th>
										<th style="width: 10%;">Description(cascode/Punishment Awarded)</th>
										<th style="width: 10%;">Type of Entry</th>
										<th style="width: 10%;">Award Date</th>
										<th style="width: 10%;">Unit Name</th>
										<th style="width: 10%;">Updated By</th>
										<th style="width: 10%;">Updated Date</th>
										<th style="width: 10%;">Action</th>
									</tr>
								</thead>
								<tbody data-spy="scroll" id="updateDiscipline_tbody" style="min-height: 46px; max-height: 650px; text-align: center;">
									
								</tbody>
							</table>
	              		
							</div>
							 
						</div>
					</div>
				</div>
			</div>
		
	</form>
	</c:if>
	<!-- END DISCIPLINE -->
	<!-- 	START INTER ARM -->
	<c:if test="${InterArmTransfer != 0}">
	<form id="forminspupdate">
		<div class="card">
			<div class="panel-group" id="accordion21">
				<div class="panel panel-default" id="x_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title blue" id="x_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion21" href="#" id="x_final" onclick="divN(this)"
								><b>INTER ARM/SERVICE TRANSFER</b></a>
						</h4>
					</div>
					<div id="collapse1x" class="panel-collapse collapse">
								<div class="card-body card-block">
								   <table id="interArm_table" class="table-bordered " style="margin-top: 10px; width: 100%;">
								<thead style="color: white; text-align: center;">
									<tr>
										<th style="width: 2%;">Sr No</th>
										<th style="width: 10%;">Authority</th>
										<th style="width: 10%;">Date of Authority</th>
										<th style="width: 10%;">To Present Arm/Service</th>
										<th style="width: 10%;">New Regt(INF)</th>
										<th style="width: 10%;">With Effect From</th>
										<th style="width: 10%;">Updated By</th>
										<th style="width: 10%;">Updated Date</th>
										<th style="width: 10%;">Action</th>
									</tr>
								</thead>
								<tbody data-spy="scroll" id="interArm_tbody" style="min-height: 46px; max-height: 650px; text-align: center;">
									
								</tbody>
							</table>
	               
	              			</div>	
					</div>
				</div>
			</div>
		</div>
	</form>
	</c:if>
<!-- 	END INTER ARM -->
		<!-- START CHANGE OF COMMISSION DETAIL -->
		<c:if test="${ChangeOfComm != 0}">
	    <form id="forminspupdate">
		<div class="card">
			<div class="panel-group" id="accordion7">
				<div class="panel panel-default" id="y_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title blue" id="y_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion7" href="#" id="y_final" onclick="divN(this)"
								><b>SSC TO PERMT COMMISSION</b></a>
						</h4>
					</div>
					<div id="collapse1y" class="panel-collapse collapse">
						<div class="card-body card-block">
						<table id="sscToper_table" class="table-bordered " style="margin-top: 10px; width: 100%;">
								<thead style="color: white; text-align: center;">
									<tr>
										<th style="width: 2%;">Sr No</th>
										<th style="width: 10%;">Authority</th>
										<th style="width: 10%;">Date of Authority</th>
										<th style="width: 10%;">New Personal No </th>
										<th style="width: 10%;">Type of Commission Granted</th>
										<th style="width: 10%;">Date of Permanent Commission</th>
										<th style="width: 10%;">Date of Seniority</th>
										<th style="width: 10%;">Date From Which Change in Seniority is Effective</th>
										<th style="width: 10%;">Updated By</th>
										<th style="width: 10%;">Updated Date</th>
										<th style="width: 10%;">Action</th>
									</tr>
								</thead>
								<tbody data-spy="scroll" id="sscToper_tbody" style="min-height: 46px; max-height: 650px; text-align: center;">
									
								</tbody>
							</table>
								
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	</c:if>
	<!-- END CHANGE OF COMMISSION DETAIL -->
	<!-- START EXTENSION OF COMMISSION DETAIL -->
	<c:if test="${ExtensionComm != 0}">
	    <form id="forminspupdate">
		<div class="card">
			<div class="panel-group" id="accordion8">
				<div class="panel panel-default" id="z_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title blue" id="z_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion8" href="#" id="z_final" onclick="divN(this)"
								><b>EXTENSION OF SSC</b></a>
						</h4>
					</div>
					<div id="collapse1z" class="panel-collapse collapse">
						<div class="card-body card-block">
						 <table id="Extension_of_ssc_table" class="table-bordered " style="margin-top: 10px; width: 100%;">
								<thead style="color: white; text-align: center;">
									<tr>
										<th style="width: 2%;">Sr No</th>
										<th style="width: 10%;">Authority</th>
										<th style="width: 10%;">Date of Authority</th>
										<th style="width: 10%;">From</th>
										<th style="width: 10%;">To</th>
										<th style="width: 10%;">Updated By</th>
										<th style="width: 10%;">Updated Date</th>
										<th style="width: 10%;">Action</th>
									</tr>
								</thead>
								<tbody data-spy="scroll" id="Extension_of_ssc_tbody" style="min-height: 46px; max-height: 650px; text-align: center;">
									
								</tbody>
							</table>
			            </div>
			            
						</div>
					</div>
				</div>
			</div>
		
	</form>
	</c:if>
	<!-- END EXTENSION OF COMMISSION DETAIL -->
	<!-- START SECONDMENT DETAIL -->
	<c:if test="${Secondment != 0}">
 <form id="form_secondment">
		<div class="card">
			<div class="panel-group" id="accordion17">
				<div class="panel panel-default" id="aa_div16">
					<div class="panel-heading">
						<h4 class="panel-title main_title green" id="aa_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion17" href="#" id="aa_final" onclick="divN(this)"
								><b>SECONDMENT</b></a>
						</h4>
					</div>
					<div id="collapse1aa" class="panel-collapse collapse">
			           <div class="card-body card-block">
			             <table id="secondment_table" class="table-bordered " style="margin-top: 10px; width: 100%;">
								<thead style="color: white; text-align: center;">
									<tr>
										<th style="width: 2%;">Sr No</th>
										<th style="width: 10%;">Authority</th>
										<th style="width: 10%;">Date of Authority</th>
										<th style="width: 10%;">Seconded To</th>
										<th style="width: 10%;">Secondment With Effect From</th>
										<th style="width: 10%;">Updated By</th>
										<th style="width: 10%;">Updated Date</th>
										<th style="width: 10%;">Action</th>
									</tr>
								</thead>
								<tbody data-spy="scroll" id="secondment_tbody" style="min-height: 46px; max-height: 650px; text-align: center;">
									
								</tbody>
							</table>
							</div>
						 
						</div>
					</div>
				</div>
			</div>
		
	</form>
	</c:if>
	<!-- END SECONDMENT DETAIL -->
	 	  <!-- START NON EFFECTIVE DETAIL -->
	 	   <c:if test="${NonEffective != 0}">
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
			           <table id="non_eff_table" class="table-bordered " style="margin-top: 10px; width: 100%;">
								<thead style="color: white; text-align: center;">
									<tr>
										<th style="width: 2%;">Sr No</th>
										<th style="width: 10%;">Authority</th>
										<th style="width: 10%;">Date of Authority</th>
										<th style="width: 10%;">Cause of Non Effective </th>
										
										<th style="width: 10%;">Date of Non Effective</th>
										
										<th style="width: 10%;">Updated By</th>
										<th style="width: 10%;">Updated Date</th>
										<th style="width: 10%;">Action</th>
									</tr>
								</thead>
								<tbody data-spy="scroll" id="non_eff_tbody" style="min-height: 46px; max-height: 650px; text-align: center;">
									
								</tbody>
							</table>
						 
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	</c:if>
	<!-- END NON EFFECTIVE DETAIL -->
	
	
	<!-- START DESERTER -->
	 <c:if test="${deserter1 != 0}">
	    <form id="forminspupdate">
		<div class="card">
			<div class="panel-group" id="accordion8">
				<div class="panel panel-default" id="dd_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title blue" id="dd_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion8" href="#" id="dd_final" onclick="divN(this)"
								><b>DESERTER</b></a>
						</h4>
					</div>
					<div id="collapse1dd" class="panel-collapse collapse">
						<div class="card-body card-block">
						  <table id="deserter_table" class="table-bordered " style="margin-top: 10px; width: 100%;">
								<thead style="color: white; text-align: center;">
									<tr>
										<th style="width: 2%;">Sr No</th>
										<th style="width: 10%;">Arms Type</th>
										<th style="width: 10%;">Weapon</th>
										<th style="width: 10%;">Date of Desertion</th>
										<th style="width: 10%;">Date of Recoverd</th>
										<th style="width: 10%;">Recovered With Arms or Not</th>
										<th style="width: 10%;">Cause of Desertion</th>
										<th style="width: 10%;">Class of Indl</th>
										<th style="width: 10%;">Updated By</th>
										<th style="width: 10%;">Updated Date</th>
										<th style="width: 10%;">Action</th>
									</tr>
								</thead>
								<tbody data-spy="scroll" id="deserter_tbody" style="min-height: 46px; max-height: 650px; text-align: center;">
									
								</tbody>
							</table>
							</div>
						
                       
						</div>
					</div>
				</div>
			</div>
		
	</form>
	</c:if>
	<!-- END DESERTER -->
	<!-- START CSD -->
	 <c:if test="${csddetails !=0}">
	<form id="forminspupdate1">
		<div class="card">
			<div class="panel-group" id="accordion8">
				<div class="panel panel-default" id="ee_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title blue" id="ee_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion8" href="#" id="ee_final" onclick="divN(this)"
								><b>CSD</b></a>
						</h4>
					</div>
					<div id="collapse1ee" class="panel-collapse collapse">
						<div class="card-body card-block">
						  <table id="csd_table" class="table-bordered " style="margin-top: 10px; width: 100%;">
								<thead style="color: white; text-align: center;">
									<tr>
										<th style="width: 2%;">Sr No</th>
										<th style="width: 10%;">Category</th>
										<th style="width: 10%;">Name</th>
										<th style="width: 10%;">Date of Birth</th>
										<th style="width: 10%;">Type of Card</th>
										<th style="width: 10%;">Card No</th>										
										<th style="width: 10%;">Updated By</th>
										<th style="width: 10%;">Updated Date</th>
										<th style="width: 10%;">Action</th>
									</tr>
								</thead>
								<tbody data-spy="scroll" id="csd_tbody" style="min-height: 46px; max-height: 650px; text-align: center;">
									
								</tbody>
							</table>
							</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	</c:if>
	<!-- END CSD -->
<!--     medical Model -->  
 <div class="modal fade" id="medicalModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header" >
        <h4 class="modal-title" align="left">Medical Details</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          
        </div>
        <div class="modal-body">
         <form id="madical_category_form">
		<div class="card">
			
							<div class="row">
										<div class="col-md-12">
				<div class="col-md-6">
					<div class="row form-group">
						<div class="col-md-6">
							<label class=" form-control-label"><strong
								style="color: red;">* </strong>Authority</label>
						</div>
						<div class="col-md-6">
						
							<label  id="madical_authority"
								name="madical_authority" ></label>
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
							<label  id="madical_date_of_authority"
								name="madical_date_of_authority"
							></label>

						</div>
					</div>
				</div>
			</div>
			<input type="hidden" id="mad_classification_count" name="mad_classification_count" value="NIL">
				<div class="col-md-12">
					<div class="row form-group">
						<div class="col-md-12" style="font-size: 15px; margin:10px; ">
								<input type="checkbox" id="check_1bx" name="check_1bx"
									onclick="oncheck_1bx()" value="1BX" disabled="disabled"> <label for="text-input"
									class=" form-control-label"
									style="margin-left: 10px;" > SHAPE 1BX </label>
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
						<label  id="1bx_from_date" name="1bx_from_date" ></label>

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
							<label  id="1bx_to_date" name="1bx_to_date" ></label>
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
							<label  name="1bx_diagnosis_code" id="1bx_diagnosis_code" ></label>
						</div>
					</div>
				</div>
			</div>
			
				<div  id="shapediv" style="width: -webkit-fill-available;">				
							
			<div class="card-header-inner" style="margin-left:20px;margin-bottom:20px;"> <strong>S - Psychological & Cognitive</strong></div>
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
		
								
											   </tr>
											</thead>
										   <tbody data-spy="scroll" id="s_madtbody" style="min-height:46px; max-height:650px; text-align: center;">
												
										   </tbody> 
									</table>
					</div>
					
					<br/>
				
		<div class="card-header-inner" style="margin-left:20px;margin-bottom:20px;"> <strong>H - Hearing</strong></div>
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
							
								
											   </tr>
											</thead>
										   <tbody data-spy="scroll" id="h_madtbody" style="min-height:46px; max-height:650px; text-align: center;">
												
										   </tbody> 
									</table>
					</div>
					
					
					<br/>
				<div class="card-header-inner" style="margin-left:20px;margin-bottom:20px;"> <strong>A - Appendages</strong></div>		
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

											   </tr>
											</thead>
										   <tbody data-spy="scroll" id="a_madtbody" style="min-height:46px; max-height:650px; text-align: center;">
												 
										   </tbody> 
									</table>
					</div>
					
					
					<br/>
					<div class="card-header-inner" style="margin-left:20px;margin-bottom:20px;"> <strong>P - Physical Capacity</strong></div>	
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

											   </tr>
											</thead>
										   <tbody data-spy="scroll" id="p_madtbody" style="min-height:46px; max-height:650px; text-align: center;">
												
										   </tbody> 
									</table>
					</div>
					
					<br/>
					
	<div class="card-header-inner" style="margin-left:20px;margin-bottom:20px;"> <strong>E - Eye Sight</strong></div>			
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

											   </tr>
											</thead>
										   <tbody data-spy="scroll" id="e_madtbody" style="min-height:46px; max-height:650px; text-align: center;">
												
										   </tbody> 
									</table>
					</div>
					
					
				<br/>
				
				<div class="card-header-inner" style="margin-left:20px;margin-bottom:20px;"> <strong>C - Climate and Terrain Restrictions</strong></div>
				<div style="width: -webkit-fill-available;">
	<table id="c_cmadtable" class="table-bordered " style="margin-top:10px;width:100%;">
											<thead style=" color: white; text-align: center;">
												<tr>
													<th style="width: 2%;">Sr No</th>
													<th style="">Value & Description</th>
													<th style="display:none;" class="cCopClass">Other</th>  													

											   </tr>
											</thead>
										   <tbody data-spy="scroll" id="c_cmadtbody" style="min-height:46px; max-height:650px; text-align: center;">
												
										   </tbody> 
									</table>
					</div>
					<br/>
					
					<div class="card-header-inner" style="margin-left:20px;margin-bottom:20px;"> <strong>O - Degree of Medical Observation Required</strong></div>
									<div style="width: -webkit-fill-available;">
	<table id="c_omadtable" class="table-bordered " style="margin-top:10px;width:100%;">
											
											<thead style=" color: white; text-align: center;">
												<tr>
													<th style="width: 2%;">Sr No</th>
													<th style="">Value & Description</th> 													

											   </tr>
											</thead>
										   <tbody data-spy="scroll" id="c_omadtbody" style="min-height:46px; max-height:650px; text-align: center;">
												
										   </tbody> 
									</table>
					</div>
					
					
					<br/>
					<div class="card-header-inner" style="margin-left:20px;margin-bottom:20px;"> <strong>P - Physical Capability Limitations</strong></div>
										<div style="width: -webkit-fill-available;">
	<table id="c_pmadtable" class="table-bordered " style="margin-top:10px;width:100%;">
											
											<thead style=" color: white; text-align: center;">
												<tr>
													<th style="width: 2%;">Sr No</th>
													<th style="">Value & Description</th> 													

											   </tr>
											</thead>
										   <tbody data-spy="scroll" id="c_pmadtbody" style="min-height:46px; max-height:650px; text-align: center;">
												
										   </tbody> 
									</table>
					</div>	
					

<br/>
<div class="card-header-inner" style="margin-left:20px;margin-bottom:20px;"> <strong>E - Exclusive Limitations</strong></div>
			<div style="width: -webkit-fill-available;">
	<table id="c_emadtable" class="table-bordered " style="margin-top:10px;width:100%;">
											
											<thead style=" color: white; text-align: center;">
												<tr>
													<th style="width: 2%;">Sr No</th>
													<th style="">Value & Description</th>
													<th style="display:none;" class="eCopClass">SubValue</th> 
													<th style="display:none;" class="eCop_otherClass">Other</th> 													

											   </tr>
											</thead>
										   <tbody data-spy="scroll" id="c_emadtbody" style="min-height:46px; max-height:650px; text-align: center;">
												
										   </tbody> 
									</table>
					</div>
					</div>
					
							</div>
	  
	                  
						</div>
				
				
		
		
	</form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
  
  <form:form name="Approve_officer_Data" id="Approve_officer_Data" action="Approve_ViewHistoryofficer_DataAction" method="post" class="form-horizontal" >
	<div class="animated fadeIn">
		<div class="card">
		                          	
						            						                   
			               		<input type="hidden" id="comm_idApp" name="comm_idApp" value="0" class="form-control autocomplete" autocomplete="off"  > 						                   
			                 <input type="hidden" id="spouseApp" name="spouseApp" value="0" class="form-control autocomplete" autocomplete="off"  > 						                   
			                
			                 <div class="panel-group" id="accordion">
				<div class="panel panel-default">
				
				   <div class="col-md-12" align="center" >	              					
						    <p><input type="checkbox" id="myCheck" onclick="myFunction();"/> I have checked all the tabs and certify that all the information is correct. </p> 
					</div>
					<div class="col-md-12" id = "submit_a" align="center" style="display:none">	              					
						     <input type="submit" class="btn btn-primary btn-sm" value="Approve">
				   </div>
			   </div>
			</div>
	    </div>
   </div>
</form:form>
<script type="text/javascript">
cancel_status=0;
set_status=2;
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
				getChangeOfRank();
				colAdj("changeOfRank_table");
			}
		}
		else if (obj.id == "b_final") {
			if(!hasC){
				get_change_of_name();
				colAdj("changeOfName_table");
			}
		}
		else if (obj.id == "c_final") {
			if(!hasC){
				get_Appointment();
				colAdj("Appointment_table");
			}
		}
		else if (obj.id == "d_final") {
			if(!hasC){
				getIdentity_Card();
				colAdj("Identity_card_table");
			}
		}
		else if (obj.id == "e_final") {
			if(!hasC){
				get_religion();
				colAdj("ChangeOfReligion_table");
			}
		}
		else if (obj.id == "f_final") {
			if(!hasC){
				getfamily_marriageDetails();
				colAdj("Marital_status_table");
				colAdj("Marital_div_status_table");
				getSpouseQualificationData();
				colAdj("spouse_quali_table");
			}
		}
		else if (obj.id == "g_final") {
			if(!hasC){
				m_children_Details();
				colAdj("m_children_details_table");
			}
		}
		else if (obj.id == "h_final") {
			if(!hasC){
				get_nokaddress_details();
				colAdj("NOK_address_details_table");
			}
		}
		else if (obj.id == "i_final") {
			if(!hasC){
				//get_changeaddress_details();
				get_changeinaddress_details();
				colAdj("ChangeInAddress_table");
				colAdj("ChangeInPerAddress_table");
				colAdj("ChangeInPreAddress_table");
				colAdj("ChangeInSpouseAddress_table");
			}
		}
		else if (obj.id == "j_final") {
			if(!hasC){
				get_cda_acnt_no();
				colAdj("ContactDetails_table"); 
			}
		}
		else if (obj.id == "k_final") {
			if(!hasC){
				get_language_details();
				get_foreign_language_details();
				colAdj("language_table");         
				colAdj("foregin_language_table");
			}
		}
		else if (obj.id == "l_final") {
			if(!hasC){
				getQualifications();
				colAdj("qualificationtable");
			}
		}
		else if (obj.id == "m_final") {
			if(!hasC){
				get_promo_exam_details();         
				colAdj("promo_exam_table");
			}
		}
		
		else if (obj.id == "p_final") {
			if(!hasC){
				bpet_Details();
				colAdj("bpet_table");
				
			}
		}
		else if (obj.id == "q_final") {
			if(!hasC){
				fire_Details();       
				colAdj("fire_std_table");
			}
		}
		else if (obj.id == "r_final") {
			if(!hasC){
				get_allergic_details();        
				colAdj("allergic_table");
			}
		}
		else if (obj.id == "s_final") {
			if(!hasC){
				getMedicalDetails();
				//get1BXShapeDetails();
				colAdj("s_madtable");
				colAdj("h_madtable");
				colAdj("a_madtable");
				colAdj("p_madtable");
				colAdj("e_madtable");
				colAdj("c_cmadtable");         
				colAdj("c_omadtable");
				colAdj("c_pmadtable");
				colAdj("c_emadtable");
				colAdj("medical_table");
				
// 				getsShapeDetails();
// 				gethShapeDetails();
// 				getaShapeDetails();
// 				getpShapeDetails();
// 				geteShapeDetails();
// 				getcCopeDetails();
// 				getoCopeDetails();
// 				getpCopeDetails();
// 				geteCopeDetails();
				
				
			}
		}
		else if (obj.id == "t_final") {
			if(!hasC){
				getUPForeign_CountriesDetails(); 
				colAdj("foreign_country_visit");
			}
		}
		else if (obj.id == "u_final") {
			if(!hasC){
				getawardsNmedals();
				colAdj("awardsNmedal_table");
			}
		}
		else if (obj.id == "v_final") {
			if(!hasC){
				get_Battle_and_Physical_Casuality_details(); 
				colAdj("battle_cause_table");
				
			}
		}
		else if (obj.id == "w_final") {
			if(!hasC){
				get_change_of_Discipline();
				colAdj("updateDiscipline_table");
				
			}
		}
		else if (obj.id == "x_final") {
			if(!hasC){
				getInterArm();
				colAdj("interArm_table"); 
				
			}
		}
		else if (obj.id == "y_final") {
			if(!hasC){
				getcoc();
				colAdj("sscToper_table"); 
				
			}
		}
		else if (obj.id == "z_final") {
			if(!hasC){
				geteoc();
				colAdj("Extension_of_ssc_table"); 
				
			}
		}
		else if (obj.id == "aa_final") {
			if(!hasC){
				get_Secondment();
				colAdj("secondment_table"); 
			}
		}
		else if (obj.id == "bb_final") {
			if(!hasC){
				getNon_effective();
				colAdj("non_eff_table"); 
// 				get_non_eff_address_details();
// 				get_changeaddress_details();
			}
		}
		else if (obj.id == "cc_final") {
			if(!hasC){
				get_army_course_details();  
				colAdj("army_course_table");
			}
		}
		
		else if (obj.id == "dd_final") {
			if(!hasC){
				get_deserter();
				colAdj("deserter_table"); 
			}
		}
		else if (obj.id == "ee_final") {
			if(!hasC){
				get_CSD_details();
				colAdj("csd_table"); 
			}
		}
}
</script>

<script>
//*************************************MAIN Personel Number Onchange*****************************//
var curr_marital_status=0;
function personal_number()
{
	
	$("#submit_data").show();
	if($("input#personnel_no").val()==""){
		alert("Please select Personal No");
		$("input#personnel_no").focus();
		return false;
	}
	else{
		$("#div_update_data").show();
	}
	 var personnel_no = $("input#personnel_no").val();
	//alert("hello---" + personnel_no);
	 $.post('GetPersonnelNoData?' + key + "=" + value,{ personnel_no : personnel_no },function(j) {
		 if(j!=""){
	    	$("#comm_id").val(j[0][0]);
	    	//alert("hii--" + j[0][8]);
	    	$('#inter_arm_old_arm_service_val').val(j[0][8]);
			$('#inter_arm_old_arm_service').text($( "#inter_arm_old_arm_service_val option:selected" ).text());
	    	if(j[0][9] != 0){
	    		$('#inter_arm_old_regt_val').val(j[0][9]);
				$('#inter_arm_old_regt').text($( "#inter_arm_old_regt_val option:selected" ).text());
	    	}
	    	else {
	    		$('#inter_arm_old_regt').text("");
	    	}
	    	
			//$("select#inter_arm_old_regt").val(j[0][9]);
	    	var comm_id =j[0][0]; 
	    	 $.post('GetCensusDataApprove?' + key + "=" + value,{ comm_id : comm_id },function(k) {
	    		 if(k.length > 0)
	    		 {alert(k[0][1]);
	    			  $('#census_id').val(k[0][1]); 
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
	    		    		 $("#dob_date").val(ParseDateColumncommission(k[0][11]));
	    		    	}
	    			 	$("#comm_date").val(ParseDateColumncommission(k[0][12]));
	    			    $("#marital_status_p").text(k[0][13]);
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
	    		    	$("#tos_date").val(ParseDateColumncommission(k[0][6]));
	    		    	$("#app_sus_no").text(k[0][7]);
	    		    	$("#p_id_no").text(k[0][8]);
	    		    	$("#p_religion").text(k[0][9]);
	    		    	$("#app_parent_arm").text(k[0][10]);
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
	 		
	    	 $.post('GetServingStatus?' + key + "=" + value,{ comm_id : comm_id },function(k) {
	    		 if(k.length > 0)
	    		 {
	    		    	$("#p_serving_status").text(k[0][2]);
	    		 }
	   });
	 }
  }); 
	 $("input#personnel_no").attr('readonly', true);
}
$("input#personnel_no").keypress(function(){
	var personel_no = this.value;
		 var susNoAuto=$("#personnel_no");
		  susNoAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        	type: 'POST',
			    	url: "getpersonnel_noListApproved?"+key+"="+value,
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

// function fn_getUnitnamefromSus(sus_no,e){
	
// 	$.post("getTargetUnitNameList?"+key+"="+value, {sus_no:sus_no}).done(function(data) {
// 		var l=data.length-1;
// 		  var enc = data[l].substring(0,16);    	   	 
// 	 		e.value=dec(enc,data[0]);
// 		}).fail(function(xhr, textStatus, errorThrown) {
// });
// }

function fn_getUnitnamefromSusHis(sus_no){
	var un="";
	$.ajaxSetup({
		async : false
	});
	$.post("getTargetUnitNameList?"+key+"="+value, {sus_no:sus_no}).done(function(data) {
		var l=data.length-1;
		  var enc = data[l].substring(0,16);    	   	 
	 		un=dec(enc,data[0]);
		}).fail(function(xhr, textStatus, errorThrown) {
});
	return un;
}

$("input#issue_authority").keypress(function(){
	var unit_name = this.value;
		 var susNoAuto=$("#issue_authority");
		  susNoAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        	type: 'POST',
			    	url: "getIssueingAuthList?"+key+"="+value,
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
		        	  alert("Please Enter Approved Issue Authority");
		        	  document.getElementById("issue_authority").value="";
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
					        	$("#issue_authority_sus").val(dec(enc,data[0]));
				            }).fail(function(xhr, textStatus, errorThrown) {
				            });
			      } 	     
			}); 		     
					
});
//*************************************END Personel Number Onchange*****************************//
</script>
<script>
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
		}
		//remove
		function m_children_details_remove_fn(R){
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
									 alert("Data Deleted SuceessFully");
					   }
						 else{
								 
							 alert("Data not Deleted ");
						 }
					   
			 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
			  		});
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
			var sib_relationship = $('#sib_relationship' + ps).val();
			var sib_adop_date = $('#sib_adop_date' + ps).val();
			if ($('#sib_adopted' + ps).is(":checked")){
				sib_adopted = "Yes";
				  if (sib_adop_date == "DD/MM/YYYY") {
						alert("Please Select Adoption Date");
						sib_adop_date.focus();
						return false;
					} 
			}
			else{
				sib_adopted = "No";
			}
			
			var aadhar_no = $('#aadhar_no' + ps).val().split('-').join('');
			var pan_no = $('#pan_no' + ps).val();
			
			var sib_ch_id = $('#sib_ch_id' + ps).val();
			var comm_id = $('#comm_id').val();
			var census_id = $('#census_id').val();
			
			 if (sib_name == "") {
					alert("Please Enter Name");
					sib_name.focus();
					return false;
				}
			  if (sib_date_of_birth == "DD/MM/YYYY") {
					alert("Please Select Date of Birth");
					sib_date_of_birth.focus();
					return false;
				} 
			 if (sib_relationship == 0) {
					alert("Please Select Relationship");
					sib_relationship.focus();
					return false;
				}
			 
			 if (aadhar_no == "") {
					alert("Please Enter Aadhaar Card No");
					aadhar_no.focus();
					return false;
				}
			 
				var currentdate=new Date();
				if(getformatedDate(sib_date_of_birth) > currentdate){
					   alert("Enter Valid Date of Birth Date");			  
					   $("input#sib_date_of_birth"+ps).focus();
					   return false;
					   
					}
			

			$.post('m_children_details_action?' + key + "=" + value, {
				sib_name : sib_name,
				sib_date_of_birth : sib_date_of_birth,sib_type:sib_type,
				sib_relationship : sib_relationship,sib_adopted:sib_adopted,sib_adop_date:sib_adop_date,aadhar_no:aadhar_no,pan_no:pan_no,
				sib_ch_id : sib_ch_id,
				comm_id : comm_id,census_id:census_id
			}, function(data) {

				if (data == "update")
					alert("Data Updated Successfully");
				else if (parseInt(data) > 0) {
					$('#sib_ch_id' + ps).val(data);
					$("#m_children_details_add" + ps).show();
					$("#m_children_details_remove" + ps).show();
					alert("Data Saved SuccesFully")
				} else
					alert(data);
			}).fail(function(xhr, textStatus, errorThrown) {
				alert("fail to fetch")
			});
		}
		//get children
		function m_children_Details() {
			var cen_id=$('#census_id').val(); 
			var comm_id=$('#comm_idV').val();
			var i=0;
			 $.post('getHisChildData?' + key + "=" + value, {comm_id:comm_id,cen_id:cen_id,cancel_status:cancel_status}, function(j){
				 $('#m_children_detailstbody').empty();
				 var v=j.length;
				 
			     if(v!=0){
			              
					for(i;i<v;i++){
			     		cor=i+1;
			     		var dob=convertDateFormate(j[i].date_of_birth);
			     		var doa=convertDateFormate(j[i].date_of_adpoted);
			     		var dmod=convertDateFormate(j[i].modified_date);
			     		if(j[i].date_of_adpoted==null){
			     			doa="--";
			     		}
						$("table#m_children_details_table").append('<tr id="tr_m_children_details'+cor+'">'
			                     +'<td class="anm_srno">'+cor+'</td>'
			                     +'<td style="width: 10%;">'+j[i].name+'</td>'
			                     +'<td style="width: 10%;">'+dob+'</td>'
			                     +'<td style="width: 10%;">'+j[i].type+'</td>'
			                     +'<td style="width: 10%;">'+j[i].relationship+'</td>'
			                     +'<td style="width: 10%;">'+j[i].adoted+'</td>'
			                     +'<td style="width: 10%;">'+doa+'</td>'
			                     +'<td style="width: 10%;">'+setInvalidToNull(j[i].aadhar_no)+'</td>'
			                     +'<td style="width: 10%;">'+j[i].pan_no+'</td>'
			                     +'<td style="width: 10%;">'+j[i].modified_by+'</td>'
			                     +'<td style="width: 10%;">'+dmod+'</td>'
			                     +'<td style="display:none;"><input type="text" id="Identity_card_ch_id'+cor+'" name="Identity_card_ch_id'+cor+'"  value="' + j[i].id + '" class="form-control autocomplete" autocomplete="off" ></td>'
			                     +'<td style="width: 10%;"><a  class="btn btn-danger btn-sm" value="REMOVE" title = "REJECT" '+j[i].action+'><i class="fa fa-times"></i></a>'
			                     +'</td></tr>');

					}
				}
			 });
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
</script>
<script>
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
	 if ($("input#gmail").val() == "") {
			alert("Please Enter Gamil ");
			$("input#gmail").focus();
			return false;
		}
	 if ($("input#nic_mail").val() == "") {
			alert("Please Enter NIC Mail ");
			$("input#nic_mail").focus();
			return false;
		}
	 if ($("input#mobile_no").val() == "") {
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
		        	  $("#addr_ch_id").val(data);	
		        	  alert("Data Saved SuccesFully")
		          }
		          else
		        	  alert(data)
		 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		  		});
}

function get_cda_acnt_no(){
	var cen_id=$('#census_id').val(); 
	var comm_id=$('#comm_idV').val();
	var i=0;
	 $.post('getHisContactDetailsData?' + key + "=" + value, {comm_id:comm_id,cen_id:cen_id,cancel_status:cancel_status}, function(j){
		 var v=j.length;
		 $('#ContactDetails_tbody').empty();
	     if(v!=0){
	              
			for(i;i<v;i++){
	     		cor=i+1;
	     		var dmod=convertDateFormate(j[i].modified_date);
	     		
				$("table#ContactDetails_table").append('<tr id="tr_ContactDetails'+cor+'">'
	                     +'<td class="anm_srno">'+cor+'</td>'
	                     +'<td style="width: 10%;">'+j[i].gmail+'</td>'
	                     +'<td style="width: 10%;">'+j[i].nic_mail+'</td>'
	                     +'<td style="width: 10%;">'+j[i].mobile_no+'</td>'
	                     +'<td style="width: 10%;">'+j[i].modified_by+'</td>'
	                     +'<td style="width: 10%;">'+dmod+'</td>'
	                     +'<td style="display:none;"><input type="text" id="ContactDetails_ch_id'+cor+'" name="ContactDetails_ch_id'+cor+'"  value="' + j[i].id + '" class="form-control autocomplete" autocomplete="off" ></td>'
	                     +'<td style="width: 10%;"><a  class="btn btn-danger btn-sm" value="REMOVE" title = "REJECT" '+j[i].action+'><i class="fa fa-times"></i></a>'
	                     +'</td></tr>');

			}
		}
	 });
}


//*************************************end CDA ACCOUNT NO & CONTACT DETAIL*****************************//
</script>
<script>	
//************************************* START NON EFFECTIVE DETAIL*****************************//
 function getNon_effective(){
	 var cen_id=$('#census_id').val(); 
	 var comm_id=$('#comm_idV').val(); 
	 var hd_rank=$('#hd_p_rank').val();
	 var i=0;
	
	 $.post('getHisNon_effectiveData?' + key + "=" + value, {comm_id:comm_id,cen_id:cen_id,cancel_status:cancel_status}, function(j){
		 var v=j.length;
		 $('#non_eff_tbody').empty(); 
	     if(v!=0){
	             
			for(i;i<v;i++){
	     		con=i+1;
	     		
	     		var dauth=convertDateFormate(j[i].date_of_authority);
	     		
	     		var dmod=convertDateFormate(j[i].modified_date);
	     		var dpc=convertDateFormate(j[i].date_of_non_effective);
	     		
				$("table#non_eff_table").append('<tr id="tr_changeOfName'+con+'">'
	                     +'<td class="anm_srno">'+con+'</td>'
	                     +'<td style="width: 10%;">'+j[i].authority+'</td>'
	                     +'<td style="width: 10%;">'+dauth+'</td>'
	                     +'<td style="width: 10%;">'+j[i].cause_of_non_effective+'</td>'
	                    
	                     +'<td style="width: 10%;">'+dpc+'</td>'
	                   
	                     +'<td style="width: 10%;">'+j[i].modified_by+'</td>'
	                     +'<td style="width: 10%;">'+dmod+'</td>'
	                     +'<td style="display:none;"><input type="text" id="changeOfRank_ch_id'+con+'" name="changeOfRank_ch_id'+con+'"  value="' + j[i].id + '" class="form-control autocomplete" autocomplete="off" ></td>'
	                     +'<td style="width: 10%;"><a  class="btn btn-danger btn-sm" value="REMOVE" title = "REJECT" '+j[i].action+'><i class="fa fa-times"></i></a>'
	                     +'</td></tr>');

			}
				//$("#awardsNmedal_add"+anm).show();
		}
	 });
}

function get_non_eff_address_details(){
	var census_id=$("#census_id").val();	
	var comm_id=$("#comm_id").val();	
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
			$("#perm_home_addr_pin").val(j[0].permanent_pin_code);
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
            onPermHomeAddrDis();
            onPermHomeAddrTeh();
            $("#per_home_addr_country_other").val(j[0].per_home_addr_country_other);
			$("#per_home_addr_state_other").val(j[0].per_home_addr_state_other);
			$("#per_home_addr_district_other").val(j[0].per_home_addr_district_other);
			$("#per_home_addr_tehsil_other").val(j[0].per_home_addr_tehsil_other);
			//////nok//////
			$("#non_addr_ch_id").val(j[0].id);
		}
	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
}


function non_effect_save(){
	var comm_id=$('#comm_id').val();
	var census_id=$('#census_id').val();
	var status=$('#status').val();
	var nf_id=$('#nf_id').val();
	var comm_date=$('#comm_date').val(); 
	 
	var formdata=$('#form_non_effective').serialize();
	 
	formdata+="&census_id="+census_id+"&comm_id="+comm_id+"&comm_date="+comm_date;	
 
	if ($("input#non_ef_authority").val() == "") {
			alert("Please Enter Authority ");
			$("input#non_ef_authority").focus();
			return false;
		}
		 if ($("input#date_of_authority_non_ef").val() == "" || $("input#date_of_authority_non_ef").val() == "DD/MM/YYYY") {
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
			$("input#cause_of_non_effective").focus();
			return false;
		}
		 if ($("input#date_of_non_effective").val() == "" || $("input#date_of_non_effective").val() == "DD/MM/YYYY") {
			alert("Please Select Date of Non Effective ");
			$("input#date_of_non_effective").focus();
			return false;
		} 

		if ($("select#perm_home_addr_country").val() == "0") {
			alert("Please select The Country");
			$("select#perm_home_addr_country").focus();
			return false;
		}
		if ($("select#perm_home_addr_state").val() == "0") {
			alert("Please Select State");
			$("select#perm_home_addr_state").focus();
			return false;
		}
		if ($("select#perm_home_addr_district").val() == "0") {
			alert("Please Select  District");
			$("select#perm_home_addr_district").focus();
			return false;
		}
		if ($("select#perm_home_addr_tehsil").val() == "0") {
			alert("Please Enter  Tehsil");
			$("select#perm_home_addr_tehsil").focus();
			return false;
		}
		if ($("input#perm_home_addr_village").val() == "") {
			alert("Please Enter  Village/Town/City");
			$("input#perm_home_addr_village").focus();
			return false;
		}
		if ($("input#perm_home_addr_pin").val() == "") {
			alert("Please Enter Pin");
			$("input#perm_home_addr_pin").focus();
			return false;
		}
		if ($("input#perm_home_addr_rail").val() == "") {
			alert("Please Enter Nearest Railway Station");
			$("input#perm_home_addr_rail").focus();
			return false;
		}
		var a = $('input:radio[name=per_home_addr_rural_urban]:checked').val();
		if (a == undefined) {
			alert("Please select The  Rural /Urban/Semi Urban ");
			return false;
		}
		var b = $('input:radio[name=border_area]:checked').val();
		if (b == undefined) {
			alert("Pl ease select The Border area ");
			return false;
		}

		$.post('non_effectiveAction?' + key + "=" + value, formdata,
				function(data) {
			//alert(data);
					if (data.substring(0, 6) == "update")
						alert("Data Updated Successfully" + data.substring(6));
					else if (parseInt(data) > 0) {
						$('#nf_id').val(data);
						alert("Data Saved SuccesFully")
					} else
						alert(data);

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

				 
			});
		}

	}	function fn_prem_domicile_state() {
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
							fn_pre_domicile_district();
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
		 	
		}
	}

	function onPermHomeAddrState() {

		var perm_home_addr_state = $("#perm_home_addr_state option:selected").text();
		
		if(perm_home_addr_state == "OTHERS"){
			$("#Ot_perm_hm_addr_state_div").show();
			
		}
		else{
			$("#Ot_perm_hm_addr_state_div").hide();
		 	
		}
	}	
	function onPermHomeAddrDis() {
		var per_home_addr_district2 = $("#perm_home_addr_district option:selected").text();
		
		if(per_home_addr_district2 == "OTHERS"){
			
			$("#Ot_perm_hm_addr_dis_div").show();
		}
		else{
			$("#Ot_perm_hm_addr_dis_div").hide();
		 	
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
							fn_per_home_addr_state();
							fn_per_home_addr_district();
							onPermHomeAddrCountry();
						}).fail(function(xhr, textStatus, errorThrown) {
				});
	}
	//************************************* End NON EFFECTIVE DETAIL*****************************//
</script>
<script>
//************************************* START RELIGION DETAIL*****************************//
function fn_other() {
	var text = $("#religion option:selected").text();
	if(text == "OTHERS"){
		$("#other_div").show();
	}
	else{
		$("#other_div").hide();
	}
}

function validate_Religion_save_fn(){
	
	if ($("input#rel_authority").val() == "") {
		alert("Please Enter Authority");
		$("input#rel_authority").focus();
		return false;
	}
 if ($("input#rel_date_of_authority").val() == "DD/MM/YYYY") {
		alert("Please Select Date of Authority ");
		$("input#rel_date_of_authority").focus();
		return false;
	}
 if(getformatedDate($("input#comm_date").val()) >= getformatedDate($("#rel_date_of_authority").val())) {
		alert("Authority Date should be Greater than Commission Date");
		return false;
}
 if ($("input#religion").val() == "") {
		alert("Please Select Religion ");
		$("input#religion").focus();
		return false;
	}
	var comm_id=$('#comm_id').val();
	var census_id=$('#census_id').val();
	var religion=$('#religion').val();
	var rel_id=$('#rel_id').val();
	var comm_date=$('#comm_date').val();
	var formdata=$('#form_religion').serialize();
	formdata+="&census_id="+census_id+"&comm_id="+comm_id+"&comm_date="+comm_date;	
	   $.post('religion_action?' + key + "=" + value, formdata, function(data){		      
	          if(data=="update")
	        	  alert("Data Updated Successfully");
	          else if(parseInt(data)>0){
	        	
	        	 $('#name_id').val(data);
	        	  alert("Data Saved SuccesFully")
	          }
	          else
	        	  alert(data);
	    		          
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});	
}

function get_religion(){
	var cen_id=$('#census_id').val(); 
	var comm_id=$('#comm_idV').val();
	var i=0;
	 $.post('getHisChangeOfReligionData?' + key + "=" + value, {comm_id:comm_id,cen_id:cen_id,cancel_status:cancel_status}, function(j){
		 var v=j.length;
		 $('#ChangeOfReligion_tbody').empty();
	     if(v!=0){
	              
			for(i;i<v;i++){
	     		cor=i+1;
	     		var dauth=convertDateFormate(j[i].date_of_authority);
	     		var dmod=convertDateFormate(j[i].modified_date);
				$("table#ChangeOfReligion_table").append('<tr id="tr_ChangeOfReligion'+cor+'">'
	                     +'<td class="anm_srno">'+cor+'</td>'
	                     +'<td style="width: 10%;">'+j[i].authority+'</td>'
	                     +'<td style="width: 10%;">'+dauth+'</td>'
	                     +'<td style="width: 10%;">'+j[i].religion_name+'</td>'
	                     +'<td style="width: 10%;">'+j[i].modified_by+'</td>'
	                     +'<td style="width: 10%;">'+dmod+'</td>'
	                     +'<td style="display:none;"><input type="text" id="ChangeOfReligion_ch_id'+cor+'" name="ChangeOfReligion_ch_id'+cor+'"  value="' + j[i].id + '" class="form-control autocomplete" autocomplete="off" ></td>'
	                     +'<td style="width: 10%;"><a  class="btn btn-danger btn-sm" value="REMOVE" title = "REJECT" '+j[i].action+'><i class="fa fa-times"></i></a>'
	                     +'</td></tr>');

			}
		}
	 });
}	
//************************************* End RELIGION DETAIL*****************************//
</script>

<script>
//************************************* START CHANGE OF NAME DETAIL*****************************//
function get_change_of_name(){
	var cen_id=$('#census_id').val(); 
	var comm_id=$('#comm_idV').val();
	var i=0;
	 $.post('getHisChangeOfNameData?' + key + "=" + value, {comm_id:comm_id,cen_id:cen_id,cancel_status:cancel_status}, function(j){
		 var v=j.length;
		 $('#changeOfName_tbody').empty(); 
	     if(v!=0){
	             
			for(i;i<v;i++){
	     		con=i+1;
	     		var dauth=convertDateFormate(j[i].date_of_authority);
	     		var dmod=convertDateFormate(j[i].modified_date);
				$("table#changeOfName_table").append('<tr id="tr_changeOfName'+con+'">'
	                     +'<td class="anm_srno">'+con+'</td>'
	                     +'<td style="width: 10%;">'+j[i].authority+'</td>'
	                     +'<td style="width: 10%;">'+dauth+'</td>'
	                     +'<td style="width: 10%;">'+j[i].name+'</td>'
	                     +'<td style="width: 10%;">'+j[i].modified_by+'</td>'
	                     +'<td style="width: 10%;">'+dmod+'</td>'
	                     +'<td style="display:none;"><input type="text" id="changeOfRank_ch_id'+con+'" name="changeOfRank_ch_id'+con+'"  value="' + j[i].id + '" class="form-control autocomplete" autocomplete="off" ></td>'
	                     +'<td style="width: 10%;"><a  class="btn btn-danger btn-sm" value="REMOVE" title = "REJECT" '+j[i].action+'><i class="fa fa-times"></i></a>'
	                     +'</td></tr>');

			}
				//$("#awardsNmedal_add"+anm).show();
		}
	 });
}	

function validate_Change_of_name_save_fn(){
	 if ($("input#na_authority").val() == "") {
			alert("Please Enter Authority");
			$("input#na_authority").focus();
			return false;
		}
	 	 if ($("input#na_date_of_authority").val() == "DD/MM/YYYY" || $("input#na_date_of_authority").val() == "") {
			alert("Please Select Date of Authority ");
			$("input#na_date_of_authority").focus();
			return false;
		} 
	 	if(getformatedDate($("input#comm_date").val()) > getformatedDate($("#na_date_of_authority").val())) {
			alert("Authority Date should be Greater than Commission Date");
			return false;
	  }
	 	if ($("input#name").val() == "") {
			alert("Please Enter Name ");
			$("input#name").focus();
			return false;
		}
	var comm_id=$('#comm_id').val();
	var census_id=$('#census_id').val();
	var name=$('#name').val();
	var name_id=$('#name_id').val();
	var comm_date=$('#comm_date').val();
	var formdata=$('#form_change_of_name').serialize();
	formdata+="&census_id="+census_id+"&comm_id="+comm_id+"&comm_date="+comm_date;	
	   $.post('change_of_nameaction?' + key + "=" + value, formdata, function(data){		      
	          if(data=="update")
	        	  alert("Data Updated Successfully");
	          else if(parseInt(data)>0){
	        	
	        	 $('#name_id').val(data);
	        	  alert("Data Saved SuccesFully")
	          }
	          else
	        	  alert(data);
	    		          
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});	
}
//*************************************end Change of name*****************************//
</script>

<script>
//*************************************START Change of RANK*****************************//

function validate_ChngofRank_save(){
	
	
	
	  if ($("#r_authority").val() == "") {
			alert("Please Enter Authority.");
			$("#r_authority").focus();
			return false;
	 }
	 if ($("#r_date_of_authority").val() == "DD/MM/YYYY" || $("#r_date_of_authority").val() == "") {
			alert("Please Select Date of Authority.");
			$("#r_date_of_authority").focus();
			return false;
	 } 
	 
     if(getformatedDate($("input#comm_date").val()) > getformatedDate($("#r_date_of_authority").val())) {
			alert("Authority Date should be Greater than Commission Date");
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
	 if ($("#date_of_rank").val() == "DD/MM/YYYY") {
			alert("Please Select Date of Rank.");
			$("#date_of_rank").focus();
			return false;
	 }  
	var authority=$('#r_authority').val();
	var date_of_authority=$('#r_date_of_authority').val();
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
	        	  alert("Data Saved SuccesFully")
	          } else
	        	  alert(data);
	 	  		}).fail(function(xhr, textStatus, errorThrown) {
	 	  			alert("fail to fetch")
	  		});
}

function getChangeOfRank(){
var cen_id=$('#census_id').val(); 
var comm_id=$('#comm_idV').val();
var i=0;
 $.post('getHisChangeOfRankData?' + key + "=" + value, {comm_id:comm_id,cancel_status:cancel_status}, function(j){
	 var v=j.length;
	 $('#changeOfRank_tbody').empty(); 
     if(v!=0){
             
		for(i;i<v;i++){
     		cor=i+1;
     		var dauth=convertDateFormate(j[i].date_of_authority);
     		var drank=convertDateFormate(j[i].date_of_rank);
     		var dmod=convertDateFormate(j[i].modified_date);
			$("table#changeOfRank_table").append('<tr id="tr_changeOfRank'+cor+'">'
                     +'<td class="anm_srno">'+cor+'</td>'
                     +'<td style="width: 10%;">'+j[i].authority+'</td>'
                     +'<td style="width: 10%;">'+dauth+'</td>'
                     +'<td style="width: 10%;">'+j[i].rank_type+'</td>'
                     +'<td style="width: 10%;">'+j[i].rank+'</td>'
                     +'<td style="width: 10%;">'+drank+'</td>'
                     +'<td style="width: 10%;">'+j[i].modified_by+'</td>'
                     +'<td style="width: 10%;">'+dmod+'</td>'
                     +'<td style="display:none;"><input type="text" id="changeOfRank_ch_id'+cor+'" name="changeOfRank_ch_id'+cor+'"  value="' + j[i].id + '" class="form-control autocomplete" autocomplete="off" ></td>'
                     +'<td style="width: 10%;"><a  class="btn btn-danger btn-sm" value="REMOVE" title = "REJECT"  '+j[i].action+'><i class="fa fa-times"></i></a>'
                     +'</td></tr>');

		}
	}
 });
}  
//*************************************END Change of RANK*****************************//
</script>

<script>
//*************************************START AWARD AND MEDAL *****************//

//awardsNmedal

anm=1;
anm_srno=1;
function awardsNmedal_add_fn(q){
    if( $('#awardsNmedal_add'+q).length )         
    {
                   $("#awardsNmedal_add"+q).hide();
    }
    anm=q+1;
    
    if(q!=0)
            anm_srno+=1;
   
   $("table#awardsNmedal_table").append('<tr id="tr_awardsNmedal'+anm+'">'
   +'<td class="anm_srno">'+anm_srno+'</td>'
   +'<td style="width: 10%;"><select name="Category_8'+anm+'" id="Category_8'+anm+'" onchange="getMedalDescList(this)" class="form-control"><option value="0">--Select--</option>'
   +'<c:forEach var="item" items="${getMedalList}" varStatus="num"><option value="${item[0]}" name="${item[1]}">${item[1]}</option></c:forEach></select>'
   +'<td style="width: 10%;"><select name="select_desc'+anm+'" id="select_desc'+anm+'" class="form-control"><option value="0">--Select--</option></select>'
	+'<td style="width: 10%;">' 
	+' <input type="text" name="date_of_award'+anm+'" id="date_of_award'+anm+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')"   class="form-control" style="width: 85%;display: inline;" '
	+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
	+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);">'
	+ '</td>'
   
   +'<td style="width: 10%;"><input type="text" id="awardsNmedal_unit'+anm+'" name="awardsNmedal_unit'+anm+'" onkeypress="unitData(this)" class="form-control autocomplete" autocomplete="off" maxlength="255"></td>'
   +'<td style="width: 10%;"><select name="awardsNmedal_bde'+anm+'" id="awardsNmedal_bde'+anm+'"  class="form-control">'
   +'<option value="0">--Select--</option><c:forEach var="item" items="${getPSG_BdeList}" varStatus="num">'
   +'<option value="${item[0]}" name="${item[1]}">${item[1]}</option></c:forEach></select></td>'
   +'<td style="width: 10%;"><select name="awardsNmedal_div_subarea'+anm+'" id="awardsNmedal_div_subarea'+anm+'"  class="form-control">'
   +'<option value="0">--Select--</option><c:forEach var="item" items="${getPSG_DivList}" varStatus="num">'
   +'<option value="${item[0]}" name="${item[1]}">${item[1]}</option></c:forEach></select></td>'
   +'<td style="width: 10%;"><select name="awardsNmedal_corps_area'+anm+'" id="awardsNmedal_corps_area'+anm+'"  class="form-control">'
   +'<option value="0">--Select--</option><c:forEach var="item" items="${getPSG_CorpsList}" varStatus="num">'
   +'<option value="${item[0]}" name="${item[1]}">${item[1]}</option></c:forEach></select></td>'
   +'<td style="width: 10%;"><select name="awardsNmedal_command'+anm+'" id="awardsNmedal_command'+anm+'"  class="form-control">'
   +'<option value="0">--Select--</option><c:forEach var="item" items="${getPSG_CommandList}" varStatus="num">'
   +'<option value="${item[0]}" name="${item[1]}">${item[1]}</option></c:forEach></select></td>'                
                                                  
   +'<td style="width: 10%;">'
   +'<input type="text" id="awardsNmedal_authority'+anm+'" name="awardsNmedal_authority'+anm+'" onkeyup="return specialcharecter(this)" class="form-control autocomplete" autocomplete="off" maxlength="50" ></td>'
  /*  +'<td style="width: 10%;"><input type="date" id="awardsNmedal_date_of_authority'+anm+'" name="awardsNmedal_date_of_authority'+anm+'" class="form-control autocomplete" autocomplete="off" maxlength="10" min="1899-01-01" max="${date}">'
   +'</td>' */
	+'<td style="width: 10%;">' 
	+' <input type="text" name="awardsNmedal_date_of_authority'+anm+'" id="awardsNmedal_date_of_authority'+anm+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')"   class="form-control" style="width: 85%;display: inline;" '
	+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
	+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);">'
	+ '</td>'
   +'<td style="display:none;"><input type="text" id="awardsNmedal_ch_id'+anm+'" name="awardsNmedal_ch_id'+anm+'"  value="0" class="form-control autocomplete" autocomplete="off" ></td>'
   +'<td style="width: 10%;"><a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "awardsNmedal_save'+anm+'" onclick="awardsNmedal_save_fn('+anm+');" ><i class="fa fa-save"></i></a>'
   +'<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "awardsNmedal_add'+anm+'" onclick="awardsNmedal_add_fn('+anm+');" ><i class="fa fa-plus"></i></a>'
   +'<a style="display:none;" class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "awardsNmedal_remove'+anm+'" onclick="awardsNmedal_remove_fn('+anm+');"><i class="fa fa-trash"></i></a>' 
   +'</td></tr>');
   
   datepicketDate('awardsNmedal_date_of_authority'+anm);
   datepicketDate('date_of_award'+anm);
}

function awardsNmedal_remove_fn(R){
        var rc = confirm("Are You Sure! You Want To Delete");
         if(rc){
         var awardsNmedal_ch_id=$('#awardsNmedal_ch_id'+R).val();
          $.post('awardsNmedal_delete_action?' + key + "=" + value, {awardsNmedal_ch_id:awardsNmedal_ch_id }, function(data){ 
                           if(data=='1'){
                                         $("tr#tr_awardsNmedal"+R).remove(); 
                                         if(R==anm){
                                                 R = R-1;
                                                 var temp=true;
                                                 for(i=R;i>=1;i--){
                                                 if( $('#awardsNmedal_add'+i).length )         
                                                 {
                                                         $("#awardsNmedal_add"+i).show();
                                                         temp=false;
                                                         anm=i;
                                                         break;
                                                 }}
                                                 if(temp){
                                                         awardsNmedal_add_fn(0);
                                                        }
                                         }
                                         $('.anm_srno').each(function(i, obj) {
                                                 
                                                        obj.innerHTML=i+1;
                                                        anm_srno=i+1;
                                                         });
                                                         alert("Data Deleted SuceessFully");
                           }
                                 else{
                                         alert("Data not Deleted ");
                                 }
                           
                                   }).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
                          });
         }
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
                if (date_of_award == "" || date_of_award == "DD/MM/YYYY") {
                        alert("Please Select Date of Award");
                        $("input#date_of_award"+qs).focus();
                        return false;
                } 
                if (awardsNmedal_command == "0") {
                        alert("Please Enter Command");
                        $("select#awardsNmedal_command"+qs).focus();
                        return false;
                }
                if (awardsNmedal_corps_area == "0") {
                        alert("Please Enter Corps/Area");
                        $("select#awardsNmedal_corps_area"+qs).focus();
                        return false;
                }
                if (awardsNmedal_div_subarea == "0") {
                        alert("Please Enter DIV/Subarea");
                        $("select#awardsNmedal_div_subarea"+qs).focus();
                        return false;
                }
                if (awardsNmedal_bde == "0") {
                        alert("Please Enter BDE");
                        $("select#awardsNmedal_bde"+qs).focus();
                        return false;
                }
                if (awardsNmedal_unit == "") {
                        alert("Please Enter Unit");
                        $("input#awardsNmedal_unit"+qs).focus();
                        return false;
                }
                if (anm_authority == "") {
                    alert("Please Enter Authority");
                    anm_authority.focus();
                    return false;
               }
                if (anm_doa == "" || anm_doa == "DD/MM/YYYY") {
                        alert("Please Select Date of Authority");
                        $("input#awardsNmedal_date_of_authority"+qs).focus();
                        return false;
                } 
                $.post('awardsNmedal_action?' + key + "=" + value, {Category_8:Category_8,select_desc:select_desc,date_of_award:date_of_award,awardsNmedal_unit:awardsNmedal_unit,awardsNmedal_bde:awardsNmedal_bde,awardsNmedal_div_subarea:awardsNmedal_div_subarea,awardsNmedal_corps_area:awardsNmedal_corps_area,awardsNmedal_command:awardsNmedal_command,awardsNmedal_ch_id:awardsNmedal_ch_id,q_id:q_id,com_id:com_id,anm_authority:anm_authority,anm_doa:anm_doa }, function(data){           
                          if(data=="update")
                                  alert("Data Updated Successfully");
                          else if(parseInt(data)>0){
                                 $("#awardsNmedal_ch_id"+qs).val(data);
                                 $("#awardsNmedal_add"+qs).show();
                                 $("#awardsNmedal_remove"+qs).show();
                                  alert("Data Saved SuccesFully")
                          }
                          else
                                  alert(data)
                                           }).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
                                  });
}
function getawardsNmedals(){
    var cen_id=$('#census_id').val(); 
    var comm_id=$('#comm_idV').val();
    var i=0;
    
                           $.post('getHisAwardAndMedalData?' + key + "=" + value, {comm_id:comm_id,cen_id:cen_id,cancel_status:cancel_status}, function(j){
                        		 var v=j.length;
                        		 $('#awardsNmedal_tbody').empty(); 
                        	     if(v!=0){
                        	             
                        			for(i;i<v;i++){
                        	     		cor=i+1;
                        	     		
                        	     		
                        	     		var dauth=convertDateFormate(j[i].date_of_authority);
                        	     		var daward=convertDateFormate(j[i].date_of_award);
                        	     		var dmod=convertDateFormate(j[i].modify_on);
                        				$("table#awardsNmedal_table").append('<tr id="tr_battle_cause'+cor+'">'
                        	                     +'<td class="anm_srno">'+cor+'</td>'
                        	                     +'<td style="width: 10%;">'+j[i].authority+'</td>'
                        	                     +'<td style="width: 10%;">'+dauth+'</td>'
                        	                     +'<td style="width: 10%;">'+j[i].category_8+'</td>'
                        	                     +'<td style="width: 10%;">'+j[i].medal_name+'</td>'
                        	                     +'<td style="width: 10%;">'+daward+'</td>'
                        	                     +'<td style="width: 10%;">'+j[i].unit+'</td>'
                        	                     +'<td style="width: 10%;">'+j[i].bde_name+'</td>'
                        	                     +'<td style="width: 10%;">'+j[i].div_name+'</td>'
                        	                     +'<td style="width: 10%;">'+j[i].coprs_name+'</td>'
                        	                     +'<td style="width: 10%;">'+j[i].cmd_name+'</td>'
                        	                   
                        	                     
                        	                
                        	                     +'<td style="width: 10%;">'+j[i].modify_by+'</td>'
                        	                     +'<td style="width: 10%;">'+dmod+'</td>'                    
                        	                     +'<td style="width: 10%;"><a  class="btn btn-danger btn-sm" value="REMOVE" title = "REJECT"  '+j[i].action+'><i class="fa fa-times"></i></a>'
                        	                     +'</td></tr>');

                        			}
                        		}
                        	 });
                        	}  
//*************************************END AWARD AND MEDAL*********************//
</script>
<script>
//*************************************START LANGUAGE DETAILS*********************//
//language
lang=1;
flang=1;
lang_srno=1;
flang_srno=1;
function language_add_fn(q){
	 if( $('#language_add'+q).length )         
	 {
			$("#language_add"+q).hide();
	 }
	lang=q+1;
	if(q!=0)
		lang_srno+=1;
	$("table#language_table").append('<tr id="tr_lang'+lang+'">'
	+'<td class="lang_srno">'+lang_srno+'</td>'
	+'<td style="width: 10%;">'
	+'<select  id="language'+lang+'" name="language'+lang+'" onchange="onLanguage('+lang+')" class="form-control autocomplete"  >'
	  +' <option value="0">--Select--</option>'
	   +'<c:forEach var="item" items="${getIndianLanguageList}" varStatus="num">'
		+'<option value="${item[0]}" name="${item[1]}">${item[1]}</option>'
		+'</c:forEach>'
		+'</select>'
		+'</td>'	
		+'<td style="width: 10%;">'
		+'<input type="text" id="other_language'+lang+'" name="other_language'+lang+'" class="form-control autocomplete" autocomplete="off"  ></td>'
	+'<td style="width: 10%;"> '
	+'<select name="lang_std'+lang+'" id="lang_std'+lang+'" onchange="onLanguage_std('+lang+')" class="form-control" >'
	+' <option value="0">--Select--</option>'
	+'	<c:forEach var="item" items="${getLanguageSTDList}" varStatus="num">'
	+'	<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' 
	+'	</c:forEach>'
	+'</select>'
	+'</td> '
	+'<td style="width: 10%;">'
	+'<input type="text" id="other_lang_std'+lang+'" name="other_lang_std'+lang+'" class="form-control autocomplete" autocomplete="off"  ></td>'
/* 	+'<td style="width: 10%;">'
	+'<input type="text" id="language_authority'+lang+'" name="language_authority'+lang+'" class="form-control autocomplete" autocomplete="off" maxlength="50" ></td>'
	+'<td style="width: 10%;"><input type="date" id="language_date_of_authority'+lang+'"  min="1899-01-01" max="${date}" name="language_date_of_authority'+lang+'" class="form-control autocomplete" autocomplete="off" maxlength="10">'
	+'</td>' */
	+'<td style="display:none;"><input type="text" id="lang_ch_id'+lang+'" name="lang_ch_id'+lang+'"  value="0" class="form-control autocomplete" autocomplete="off" ></td>'
	+'<td style="width: 10%;"><a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "language_save'+lang+'" onclick="language_save_fn('+lang+');" ><i class="fa fa-save"></i></a>'
	+'<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "language_add'+lang+'" onclick="language_add_fn('+lang+');" ><i class="fa fa-plus"></i></a>'
	+'<a style="display:none;" class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "language_remove'+lang+'" onclick="language_remove_fn('+lang+');"><i class="fa fa-trash"></i></a>' 
	+'</td></tr>');
}

function language_remove_fn(R){
	var rc = confirm("Are You Sure! You Want To Delete");
	 if(rc){
	 var lang_ch_id=$('#lang_ch_id'+R).val();
	  $.post('update_offr_language_delete_action?' + key + "=" + value, {lang_ch_id:lang_ch_id }, function(data){ 
			   if(data=='1'){
					 $("tr#tr_lang"+R).remove(); 
							 if(R==lang){
								 R = R-1;
								 var temp=true;
								 for(i=R;i>=1;i--){
								 if( $('#language_add'+i).length )         
								 {
									 $("#language_add"+i).show();
									 temp=false;
									 lang=i;
									 break;
								 }}
						 
							 if(temp){
								 language_add_fn(0);
								}
			  			 }
							 $('.lang_srno').each(function(i, obj) {
								 
									obj.innerHTML=i+1;
									lang_srno=i+1;
									 });
							 alert("Data Deleted SuceessFully");
			   }
				 else{
					 alert("Data not Deleted ");
				 }
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});
	 }
}
function language_save_fn(ps){
	var language=$('#language'+ps).val();
	var other_lan=$('#other_language'+ps).val();	
	var lang_std=$('#lang_std'+ps).val();
	var other_lan_std=$('#other_lang_std'+ps).val();
	var lang_ch_id=$('#lang_ch_id'+ps).val();
	var p_id=$('#census_id').val();
	var com_id=$('#comm_id').val();
	var lang_authority=$('#language_authority').val();
	var lang_doa=$('#language_date_of_authority').val();
	if (language == "0") {
		alert("Please select Language");
		$("select#language"+ps).focus();
		return false;
	}	
	 var country_sel = $("#language"+ps+" option:selected").text();
		if (country_sel == "OTHERS" && other_lan == "") {
			alert("Please Enter Other Language");
			$('#other_language'+ps).focus();
			return false;
		} 
	if (lang_std == "0") {
		alert("Please select Language Standard");
		$("select#lang_std"+ps).focus();
		return false;
	}
	 var country_sel = $("#lang_std"+ps+" option:selected").text();
		if (country_sel == "OTHERS" && other_lan_std == "") {
			alert("Please Enter Other Language Standard");
			$('#other_lang_std'+ps).focus();
			return false;
		} 
	if (lang_authority == "" || lang_authority == "DD/MM/YYYY") {
		alert("Please Enter Authority");
		lang_authority.focus();
		return false;
	}
	if (lang_doa == "" || lang_doa == "DD/MM/YYYY") {
		alert("Please Select Date of Authority");
		$("input#language_date_of_authority"+ps).focus();
		return false;
	} 
	  $.post('update_offr_language_detail_action?' + key + "=" + value, {language:language,other_lan:other_lan,lang_std:lang_std,other_lan_std:other_lan_std,lang_ch_id:lang_ch_id,p_id:p_id,com_id:com_id,lang_authority:lang_authority,lang_doa:lang_doa }, function(data){
	          if(data=="update")
	        	  alert("Data Updated Successfully");
	          else if(parseInt(data)>0){
	        	 $('#lang_ch_id'+ps).val(data);
	        	 $("#language_add"+ps).show();
	        	 $("#language_remove"+ps).show();
	        	  alert("Data Saved SuccesFully")
	          }
	          else
	        	  alert(data)
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});
}
function get_language_details(){
	var cen_id=$('#census_id').val(); 
	var comm_id=$('#comm_idV').val();
	var i=0;
	 $.post('getHisLanguageDetailsData?' + key + "=" + value, {comm_id:comm_id,cen_id:cen_id,cancel_status:cancel_status}, function(j){
			var v=j.length;
			$('#langtbody').empty(); 
			 if(v!=0){
	              
					for(i;i<v;i++){
			     		cor=i+1;
			     		var dauth=convertDateFormate(j[i].date_of_authority);
			     		var dmod=convertDateFormate(j[i].modify_on);
			     		var ind_language =j[i].ind_language;
			     		var lang_std =j[i].lang_std;
			     		
			     		if(j[i].ind_language=="OTHERS"){
			     			ind_language=j[i].other_language;
			     		}
			     		if(j[i].lang_std=="OTHERS"){
			     			lang_std=j[i].other_lang_std;
			     		}
						$("table#language_table").append('<tr id="tr_lang'+cor+'">'
			                     +'<td class="anm_srno">'+cor+'</td>'
			                     +'<td style="width: 10%;">'+j[i].authority+'</td>'
			                     +'<td style="width: 10%;">'+dauth+'</td>'
			                     +'<td style="width: 10%;">'+ind_language+'</td>'
			                     +'<td style="width: 10%;">'+lang_std+'</td>'
			                     +'<td style="width: 10%;">'+j[i].modify_by+'</td>'
			                     +'<td style="width: 10%;">'+dmod+'</td>'
			                     +'<td style="display:none;"><input type="text" id="lang_ch_id'+cor+'" name="lang_ch_id'+cor+'"  value="' + j[i].id + '" class="form-control autocomplete" autocomplete="off" ></td>'
			                     +'<td style="width: 10%;"><a  class="btn btn-danger btn-sm" value="REMOVE" title = "REJECT" '+j[i].action+'><i class="fa fa-times"></i></a>'
			                     +'</td></tr>');
			}
			 }
	  });
}

function get_foreign_language_details(){
	var cen_id=$('#census_id').val(); 
	var comm_id=$('#comm_idV').val();
	var i=0;
	 $.post('getHisForeignLangData?' + key + "=" + value, {comm_id:comm_id,cen_id:cen_id,cancel_status:cancel_status}, function(j){
			var v=j.length;
			$('#flangtbody').empty(); 
			 if(v!=0){
	              
					for(i;i<v;i++){
			     		cor=i+1;
			     		var dauth=convertDateFormate(j[i].date_of_authority);
			     		var dmod=convertDateFormate(j[i].modify_on);
			     		var foreign_language_name =j[i].foreign_language_name;
			     		var lang_std =j[i].lang_std;
			     		var lang_proff =j[i].lang_proff;
			     		
			     		if(j[i].foreign_language_name=="OTHERS"){
			     			foreign_language_name=j[i].f_other_language;
			     		}
			     		if(j[i].lang_std=="OTHERS"){
			     			lang_std=j[i].f_other_lang_std;
			     		}
			     		if(j[i].lang_proff=="OTHERS"){
			     			lang_proff=j[i].f_other_prof;
			     		}
						$("table#foregin_language_table").append('<tr id="tr_flang'+cor+'">'
			                     +'<td class="anm_srno">'+cor+'</td>'
			                     +'<td style="width: 10%;">'+j[i].authority+'</td>'
			                     +'<td style="width: 10%;">'+dauth+'</td>'
			                     +'<td style="width: 10%;">'+foreign_language_name+'</td>'
			                     +'<td style="width: 10%;">'+lang_std+'</td>'
			                     +'<td style="width: 10%;">'+lang_proff+'</td>'
			                     +'<td style="width: 10%;">'+j[i].f_exam_pass+'</td>'
			                     +'<td style="width: 10%;">'+j[i].modify_by+'</td>'
			                     +'<td style="width: 10%;">'+dmod+'</td>'
			                     +'<td style="display:none;"><input type="text" id="flang_ch_id'+cor+'" name="flang_ch_id'+cor+'"  value="' + j[i].id + '" class="form-control autocomplete" autocomplete="off" ></td>'
			                     +'<td style="width: 10%;"><a  class="btn btn-danger btn-sm" value="REMOVE" title = "REJECT" '+j[i].action+'><i class="fa fa-times"></i></a>'
			                     +'</td></tr>');
			}
			 }	
	  });
}

function onLanguage(ind){
	var country_sel = $("#language"+ind+" option:selected").text();
	if(country_sel != "OTHERS"){
		$('#other_language'+ind).val("");
		$('#other_language'+ind).attr('readonly', true);
	}
	else
		$('#other_language'+ind).attr('readonly', false);
}
function onLanguage_std(indl){
	var country_sel = $("#lang_std"+indl+" option:selected").text();
	if(country_sel != "OTHERS"){
		$('#other_lang_std'+indl).val("");
		$('#other_lang_std'+indl).attr('readonly', true);
	}
	else
		$('#other_lang_std'+indl).attr('readonly', false);
}

function onF_Language(ind){
	var country_sel = $("#flanguage"+ind+" option:selected").text();
	if(country_sel != "OTHERS"){
		$('#f_other_language'+ind).val("");
		$('#f_other_language'+ind).attr('readonly', true);
	}
	else
		$('#f_other_language'+ind).attr('readonly', false);
}
function onF_Language_std(indl){
	var country_sel = $("#flang_std"+indl+" option:selected").text();
	if(country_sel != "OTHERS"){
		$('#f_other_lang_std'+indl).val("");
		$('#f_other_lang_std'+indl).attr('readonly', true);
	}
	else
		$('#f_other_lang_std'+indl).attr('readonly', false);
}
function onF_Language_prof(indl){

	var country_sel = $("#lang_prof"+indl+" option:selected").text();
	
	if(country_sel != "OTHERS"){
		$('#f_other_prof'+indl).val("");
		$('#f_other_prof'+indl).attr('readonly', true);
	}
	else
		$('#f_other_prof'+indl).attr('readonly', false);
}
////foregin_language
function foregin_language_add_fn(q){
	 if( $('#foregin_language_add'+q).length )       
	 {
			$("#foregin_language_add"+q).hide();
	 }
	flang=q+1;
	if(q!=0)
		flang_srno+=1;
	$("table#foregin_language_table").append('<tr id="tr_flang'+flang+'">'
	+'<td class="flang_srno">'+flang_srno+'</td>'
	+'<td style="width: 10%;"> <select id="flanguage'+flang+'" name="flanguage'+flang+'" onchange="onF_Language('+flang+')" class="form-control autocomplete"  >'
	+'   <option value="0">--Select--</option>'
    +'	<c:forEach var="item" items="${getForiegnLangugeList}" varStatus="num">'
	+'	<option value="${item[0]}" name="${item[1]}">${item[1]}</option>'
	+'	</c:forEach>'
	+'	</select></td>'
	+'<td style="width: 10%;">'
	+'<input type="text" id="f_other_language'+flang+'" name="f_other_language'+flang+'" class="form-control autocomplete" autocomplete="off"  ></td>'
	+'<td style="width: 10%;"> '
	+'<select name="flang_std'+flang+'" id="flang_std'+flang+'" onchange="onF_Language_std('+flang+')" class="form-control" >'
	+' <option value="0">--Select--</option>'
	+'	<c:forEach var="item" items="${getLanguageSTDList}" varStatus="num">'
	+'	<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' 
	+'	</c:forEach>'
	+'</select> </td> '
	
	+'<td style="width: 10%;">'
	+'<input type="text" id="f_other_lang_std'+fang+'" name="f_other_lang_std'+fang+'" class="form-control autocomplete" autocomplete="off"  ></td>'

	+'<td style="width: 10%;"> <select name="lang_prof'+flang+'" id="lang_prof'+flang+'" onchange="onF_Language_prof'+flang+')" class="form-control"   >'	
	+' <option value="0">--Select--</option>'
	+'	<c:forEach var="item" items="${getLanguagePROOFList}" varStatus="num">'
	+'	<option value="${item[0]}" name="${item[1]}">${item[1]}</option>'
	+'	</c:forEach>'
	+' </select></td> '
	+'<td style="width: 10%;">'
	+'<input type="text" id="f_other_prof'+fang+'" name="f_other_prof'+fang+'" class="form-control autocomplete" autocomplete="off"  ></td>'
	+'<td style="width: 10%;"> <input type="text" id="exam_pass'+flang+'" name="exam_pass'+flang+'" class="form-control autocomplete" autocomplete="off" maxlength="20"></td>'		
	+'<td style="display:none;"><input type="text" id="flang_ch_id'+flang+'" name="flang_ch_id'+flang+'"  value="0" class="form-control autocomplete" autocomplete="off" ></td>'
	+'<td style="width: 10%;"><a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "foregin_language_save'+flang+'" onclick="foregin_language_save_fn('+flang+');" ><i class="fa fa-save"></i></a>'
	+'<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "foregin_language_add'+flang+'" onclick="foregin_language_add_fn('+flang+');" ><i class="fa fa-plus"></i></a>'
	+'<a style="display:none;" class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "foregin_language_remove'+flang+'" onclick="foregin_language_remove_fn('+flang+');"><i class="fa fa-trash"></i></a>' 
	+'</td></tr>');
}

function foregin_language_remove_fn(R){
	var rc = confirm("Are You Sure! You Want To Delete");
	 if(rc){
	 var lang_ch_id=$('#flang_ch_id'+R).val();
	  $.post('update_offr_language_delete_action?' + key + "=" + value, {lang_ch_id:lang_ch_id }, function(data){ 
			   if(data=='1'){
					 $("tr#tr_flang"+R).remove(); 
							 if(R==flang){
								 R = R-1;
								 var temp=true;
								 for(i=R;i>=1;i--){
								 if( $('#foregin_language_add'+i).length )         
								 {
									 $("#foregin_language_add"+i).show();
									 
									 temp=false;
									 flang=i;
									 break;
								 }}
								 
							 if(temp){
								 foregin_language_add_fn(0);
								}
			  			 }
							 
							 $('.flang_srno').each(function(i, obj) {
									obj.innerHTML=i+1;
									flang_srno=i+1;
									 });
							 alert("Data Deleted SuceessFully");
			   }
				 else{
					 alert("Data not Deleted ");
				 }
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});
	 }
}

function foregin_language_save_fn(ps){
	var language=$('#flanguage'+ps).val();
	var f_ot_language=$('#f_other_language'+ps).val();
	var lang_std=$('#flang_std'+ps).val();
	var f_ot_std=$('#f_other_lang_std'+ps).val();
	var lang_prof=$('#lang_prof'+ps).val();
	var f_ot_prof=$('#f_other_prof'+ps).val();

	var exam_pass=$('#exam_pass'+ps).val();
	var lang_authority=$('#language_authority').val();
	var lang_doa=$('#language_date_of_authority').val();
	var lang_ch_id=$('#flang_ch_id'+ps).val();
	var p_id=$('#census_id').val();
	var com_id=$('#comm_id').val();
	 if (language == "0") {
			alert("Please select Language");
			$("select#flanguage"+ps).focus();
			return false;
		}
		 var country_sel = $("#flanguage"+ps+" option:selected").text();
		if (country_sel == "OTHERS" && f_ot_language == "") {
			alert("Please Enter Other Language");
			$('#f_other_language'+ps).focus();
			return false;
		} 
	 if (lang_std == "0") {
			alert("Please select Lang_std");
			$("select#flang_std"+ps).focus();
			return false;
		}	
	 var country_sel = $("#flang_std"+ps+" option:selected").text();
		if (country_sel == "OTHERS" && f_ot_std == "") {
			alert("Please Enter Other Language Standard");
			$('#f_other_lang_std'+ps).focus();
			return false;
		} 
	 if (lang_prof == "0") {
			alert("Please select Lang_Prof");
			$("select#lang_prof"+ps).focus();
			return false;
		}
	 var country_sel = $("#lang_prof"+ps+" option:selected").text();
		if (country_sel == "OTHERS" && f_ot_prof == "") {
			alert("Please Enter Other Language Proficiency");
			$('#f_other_prof'+ps).focus();
			return false;
		} 
	 if (exam_pass == "") {
			alert("Please Enter The Exam pass");
			$("input#exam_pass"+ps).focus();
			return false;
		}
	  $.post('update_offr_foreginlanguage_detail_action?' + key + "=" + value, {language:language,lang_prof:lang_prof,f_ot_language:f_ot_language,f_ot_std:f_ot_std,f_ot_prof:f_ot_prof,exam_pass:exam_pass,lang_authority:lang_authority,lang_doa:lang_doa,lang_std:lang_std,lang_ch_id:lang_ch_id,p_id:p_id,com_id:com_id}, function(data){
	          if(data=="update")
	        	  alert("Data Updated Successfully");
	          else if(parseInt(data)>0){
	        	 $('#flang_ch_id'+ps).val(data);
	        	 $("#foregin_language_add"+ps).show();
	        	 $("#foregin_language_remove"+ps).show();
	        	  alert("Data Saved SuccesFully")
	          }
	          else
	        	  alert(data)
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});
}
//*************************************END LANGUAGE DETAILS*********************//
</Script>

<script>
//*************************************Start Secondment DETAIL*****************************//
function get_Secondment(){
		 var cen_id=$('#census_id').val(); 
		 var comm_id=$('#comm_idV').val(); 
		 var i=0;
		 $.post('getHisSecondmentData?' + key + "=" + value, {comm_id:comm_id,cen_id:cen_id,cancel_status:cancel_status}, function(j){
			 var v=j.length;
			 $('#secondment_tbody').empty(); 
		     if(v!=0){
		             
				for(i;i<v;i++){
		     		cor=i+1;
		     		
		     	
		     		
		     		
		   
		     		
		     		var dauth=convertDateFormate(j[i].date_of_authority);
		     		var fd=convertDateFormate(j[i].secondment_effect);		     		
		     		var dmod=convertDateFormate(j[i].modified_date);
					$("table#secondment_table").append('<tr id="tr_ssc_cause'+cor+'">'
		                     +'<td class="anm_srno">'+cor+'</td>'
		                     +'<td style="width: 10%;">'+j[i].authority+'</td>'
		                     +'<td style="width: 10%;">'+dauth+'</td>'
		                     +'<td style="width: 10%;">'+j[i].seconded_to+'</td>'
		                     +'<td style="width: 10%;">'+fd+'</td>'
		                    
		                     +'<td style="width: 10%;">'+j[i].modified_by+'</td>'
		                     +'<td style="width: 10%;">'+dmod+'</td>'                    
		                     +'<td style="width: 10%;"><a  class="btn btn-danger btn-sm" value="REMOVE" title = "REJECT"  '+j[i].action+'><i class="fa fa-times"></i></a>'
		                     +'</td></tr>');

				}
			}
		 });
		} 
//************************************* End Secondment DETAIL*****************************//
</script>

 <script>
//***************FOREIGN  COUNTRY************//
function onContryVisited(ind){
	var country_sel = $("#country"+ind+" option:selected").text();
	if(country_sel != "Other"){
		$('#foregin_Country_ot'+ind).val("");
		$('#foregin_Country_ot'+ind).attr('readonly', true);
	}
	else
		$('#foregin_Country_ot'+ind).attr('readonly', false);
}

function onPurposeVisited(pup){
	var purpose_sel = $("#purpose_visit"+pup+" option:selected").text();
	if(purpose_sel != "OTHER"){
		$('#purpose_visit_ot'+pup).val("");
		$('#purpose_visit_ot'+pup).attr('readonly', true);
	}
	else
		$('#purpose_visit_ot'+pup).attr('readonly', false);
}

//GET FOREIGN COUNTRY DETAILS
function getUPForeign_CountriesDetails(){
	var cen_id=$('#census_id').val(); 
	var comm_id=$('#comm_idV').val();
	var i=0;
	 $.post('getHisForeignCountryData?' + key + "=" + value, {comm_id:comm_id,cen_id:cen_id,cancel_status:cancel_status}, function(j){
			var v=j.length;
			$('#foregin_Country_tbody').empty(); 
			 if(v!=0){
	              
					for(i;i<v;i++){
			     		cor=i+1;
			     		
			     		var dfrom=convertDateFormate(j[i].from_dt);
			     		var dto=convertDateFormate(j[i].to_dt);
			     		var dmod=convertDateFormate(j[i].modified_date);
			     		var country =j[i].country;
			     		var purpose_visit =j[i].purpose_visit;
			     		
			     		if(j[i].country=="OTHERS"){
			     			country=j[i].other_country;
			     		}
			     		if(j[i].purpose_visit=="OTHERS"){
			     			purpose_visit=j[i].other_purpose_visit;
			     		}
						$("table#foreign_country_visit").append('<tr id="tr_foregin_country'+cor+'">'
			                     +'<td class="anm_srno">'+cor+'</td>'
			                     +'<td style="width: 10%;">'+country+'</td>'
			                     +'<td style="width: 10%;">'+dfrom+'</td>'
			                     +'<td style="width: 10%;">'+dto+'</td>'
			                     +'<td style="width: 10%;">'+j[i].period+'</td>'
			                     +'<td style="width: 10%;">'+purpose_visit+'</td>'
			                     +'<td style="width: 10%;">'+j[i].modified_by+'</td>'
			                     +'<td style="width: 10%;">'+dmod+'</td>'
			                     +'<td style="display:none;"><input type="text" id="flang_ch_id'+cor+'" name="flang_ch_id'+cor+'"  value="' + j[i].id + '" class="form-control autocomplete" autocomplete="off" ></td>'
			                     +'<td style="width: 10%;"><a  class="btn btn-danger btn-sm" value="REMOVE" title = "REJECT" '+j[i].action+'><i class="fa fa-times"></i></a>'
			                     +'</td></tr>');
			}
			 }	
	  }); 
	
} 
  fcon=1;
  fcon_srno=1;
  //ADD FUNCTION
  function UPforeign_country_add_fn(q){
	  if( $('#country_add'+q).length )       
  	 {
  			$("#country_add"+q).hide();
  	 }
  	 fcon=q+1;
  	if(q!=0)
  		fcon_srno+=1;
  	$("table#foreign_country_visit").append('<tr id="tr_foregin_country'+fcon+'">'
  	+'<td class="fcon_srno">'+fcon_srno+'</td>'
  	+'<td style="width: 10%;"><select name="country'+fcon+'" id="country'+fcon+'" onchange="onContryVisited('+fcon+')" class="form-control"><option value="0">--Select--</option><c:forEach var="item" items="${getForeign_country}" varStatus="num"><option value="${item[0]}" name="${item[1]}">${item[1]}</option></c:forEach></select></td>'
  	+'<td style="width: 10%;"> <input type="text" id="foregin_Country_ot'+fcon+'" name="foregin_Country_ot'+fcon+'" onkeypress="return /[0-9a-zA-Z]/i.test(event.key)" readonly  class="form-control autocomplete" autocomplete="off" ></td>'
  /* 	+'<td style="width: 10%;"> <input type="date" id="from_dt'+fcon+'" min="1899-01-01" max="${date}" name="from_dt'+fcon+'" onchange="calcDate22('+fcon+')" class="form-control autocomplete" autocomplete="off" maxlength="10"></td>' */	
	+'<td style="width: 10%;">' 
	+' <input type="text" name="from_dt'+fcon+'" id="from_dt'+fcon+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')"   class="form-control" style="width: 85%;display: inline;" '
	+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
	+'	onchange="clickrecall(this,\'DD/MM/YYYY\');calcDate22('+fcon+');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >'
	+ '</td>'
  /* 	+'<td style="width: 10%;"> <input type="date" id="to_dt'+fcon+'" min="1899-01-01" max="${date}" name="to_dt'+fcon+'" onchange="calcDate22('+fcon+')" class="form-control autocomplete" autocomplete="off" maxlength="10"></td>' */	
  	+'<td style="width: 10%;">' 
	+' <input type="text" name="to_dt'+fcon+'" id="to_dt'+fcon+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')"   class="form-control" style="width: 85%;display: inline;" '
	+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
	+'	onchange="clickrecall(this,\'DD/MM/YYYY\');calcDate22('+fcon+');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >'
	+ '</td>'
  	+'<td style="width: 10%;"> <input type="text" id="period'+fcon+'" name="period'+fcon+'" class="form-control autocomplete" autocomplete="off" maxlength="4" onkeypress="isNumber0_9Only(event)"></td>'
  	+'<td style="width: 10%;"><select name="purpose_visit'+fcon+'" onchange="onPurposeVisited('+fcon+')" id="purpose_visit'+fcon+'" class="form-control"><option value="0">--Select--</option><c:forEach var="item" items="${getForiegnCountryList}" varStatus="num"><option value="${item[0]}" name="${item[1]}">${item[1]}</option></c:forEach></select></td>'
	+'<td style="width: 10%;"> <input type="text" id="purpose_visit_ot'+fcon+'" onchange="onPurposeVisited('+fcon+')" name="purpose_visit_ot'+fcon+'" onkeypress="return /[0-9a-zA-Z]/i.test(event.key)" readonly  class="form-control autocomplete" autocomplete="off" ></td>'
  	+'<td style="display:none;"><input type="text" id="foregin_country_ch_id'+fcon+'" name="foregin_country_ch_id'+fcon+'"  value="0" class="form-control autocomplete" autocomplete="off" ></td>'
  	+'<td style="width: 10%;"><a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "country_save'+fcon+'" onclick="UPforeign_country_save_fn('+fcon+');" ><i class="fa fa-save"></i></a>'
  	+'<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "country_add'+fcon+'" onclick="UPforeign_country_add_fn('+fcon+');" ><i class="fa fa-plus"></i></a>'
  	+'<a style="display:none;" class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "country_remove'+fcon+'" onclick="UPforeign_country_remove_fn('+fcon+');"><i class="fa fa-trash"></i></a>' 
  	+'</td></tr>');
  	 datepicketDate('to_dt'+fcon); 
  	 datepicketDate('from_dt'+fcon); 
  	
  }
  //SAVE FOREIGN COUNTRY DETAILS
  function UPforeign_country_save_fn(ps){
		 if ($("select#country1").val() == "0") {
			alert("Please select Country Visitede");
			$("select#country1").focus();
			return false;
		}
		if ($("input#period1").val() == "") {
			alert("Please Enter Period");
			$("input#period1").focus();
			return false;
		}
	 	if ($("input#from_dt1").val() == "" || $("input#from_dt1").val() =="DD/MM/YYYY") {
			alert("Please Select From Date");
			$("input#from_dt1").focus();
			return false;
		}
		if ($("input#to_dt1").val() == "" || $("input#to_dt1").val() == "DD/MM/YYYY") {
			alert("Please Select To Date");
			$("input#to_dt1").focus();
			return false;
		} 
		if ($("select#purpose_visit1").val() == 0) {
			alert("Please Enter Purpose To Visit");
			$("select#purpose_visit1").focus();
			return false;
		}  
		
			var country=$('#country'+ps).val();
			var other_ct=$('#foregin_Country_ot'+ps).val();
			var period=$('#period'+ps).val();
			var from_dt=$('#from_dt'+ps).val();
			var to_dt=$('#to_dt'+ps).val();
			var purpose_visit=$('#purpose_visit'+ps).val();
			var other_purpose_visit=$('#purpose_visit_ot'+ps).val();
			var foregin_country_ch_id=$('#foregin_country_ch_id'+ps).val();
			var f_id=$('#census_id').val();
			var comm_id=$('#comm_id').val();
			if (country == "0") {
				alert("Please select Country Visited");
				$("select#country"+ps).focus();
				return false;
			}
			var country_sel = $("#country"+ps+" option:selected").text();
			if (country_sel == "Other" && other_ct == "") {
				alert("Please Enter Other Country");
				$('#foregin_Country_ot'+ps).focus();
				return false;
			}
			
			if (period == "") {
				alert("Please Enter Period");
				$("input#period"+ps).focus();
				return false;
			}
		 	if (from_dt == "" || from_dt == "DD/MM/YYYY") {
				alert("Please Select From Date");
				$("input#from_dt"+ps).focus();
				return false;
			}
		 	if(getformatedDate($("input#dob_date").val()) > getformatedDate(from_dt)) {
				alert("From Date should be Greater than Date of Birth");
				$("input#from_dt" + ps).focus();
				return false;
			}
			if (to_dt == "" || to_dt == "DD/MM/YYYY") {
				alert("Please Select To Date");
				$("input#to_dt"+ps).focus();
				return false;
			} 
			var currentdate = new Date();
			if(getformatedDate(to_dt) > currentdate) {
				alert("Enter Valid To Date");
				$("input#to_dt" + ps).focus();
				return false;
			}
			if(getformatedDate(from_dt) > getformatedDate(to_dt)) {
				alert("To Date should always be greater than From Date");
				$("input#to_dt" + ps).focus();
				return false;
			}
			if (purpose_visit == "") {
				alert("Please Enter Purpose To Visit");
				$("input#purpose_visit"+ps).focus();
				return false;
			}  
			
			var pup_sel = $("#purpose_visit"+ps+" option:selected").text();
			if (pup_sel == "OTHER" && other_purpose_visit == "") {
				alert("Please Enter Other Purpose Visit");
				$('#purpose_visit_ot'+ps).focus();
				return false;
			}
					   $.post('UPforegin_country_action?' + key + "=" + value, {country:country,other_ct:other_ct,period:period,from_dt:from_dt,to_dt:to_dt,purpose_visit:purpose_visit,other_purpose_visit:other_purpose_visit,foregin_country_ch_id:foregin_country_ch_id,f_id:f_id,comm_id:comm_id}, function(data){	 
			          if(data=="update")
			        	  alert("Data Updated Successfully");
			          else if(parseInt(data)>0){
			        	 $("#foregin_country_ch_id"+ps).val(data);
			        	 $("#country_add"+ps).show();
			        	 $("#country_remove"+ps).show();
			        	  alert("Data Saved SuccesFully")
			          }
			          else
			        	  alert(data)
			 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
			  		});
	}

	//DELETE FOREIGN COUNTRY
  function UPforeign_country_remove_fn(R){
  	var rc = confirm("Are You Sure! You Want To Delete");
  	 if(rc){
  	var foregin_country_ch_id=$('#foregin_country_ch_id'+R).val();
  	  $.post('UPforegin_country_delete_action?' + key + "=" + value, {foregin_country_ch_id:foregin_country_ch_id }, function(data){ 
  			   if(data=='1'){
  					 $("tr#tr_foregin_country"+R).remove(); 
  					 if(R==fcon){
  						 R = R-1;
  						 var temp=true;
  						 for(i=R;i>=1;i--){
  						 if( $('#country_add'+i).length )         
  						 {
  							 $("#country_add"+i).show();
  							 temp=false;
  							 fcon=i;
  							 break;
  						 }}
  						 if(temp){
  							 UPforeign_country_add_fn(0);
  							}
  					 }
  					 $('.fcon_srno').each(function(i, obj) {
  							obj.innerHTML=i+1;
  							fcon_srno=i+1;
  							 });
  							 alert("Data Deleted SuceessFully");
  			   }
  				 else{
  					 alert("Data not Deleted ");
  				 }
  	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
  	  		});
  	 }
  	 }
  function calcDate22(f) {
	  
	   	if($('#from_dt'+f).val()!="" && $('#from_dt'+f).val()!="DD/MM/YYYY"  && $('#to_dt'+f).val()!="" && $('#to_dt'+f).val()!="DD/MM/YYYY"){
	   		
	   		if(getformatedDate($('#from_dt'+f).val()) > getformatedDate($('#to_dt'+f).val())){
				   //alert("Invalid Date Range");
				   $('#period'+f).val('');
				   $("input#to_dt"+f).focus();
				   return false;
				   
				}
	   		
	   	 	var date_form=getformatedDate($('#from_dt'+f).val());
	   	   	var date_to=getformatedDate($('#to_dt'+f).val());
	       var diff = Math.floor(date_to.getTime() - date_form.getTime());
	       var day = 1000 * 60 * 60 * 24;

	       var days = Math.floor(diff/day);
//	        var months = Math.floor(days/31);
//	        var years = Math.floor(months/12);
	       
	       
	       var totalYears = Math.floor(days / 365);
	       var totalMonths = Math.floor((days % 365) / 30);
	       var remainingDays = Math.floor((days % 365) % 30);
	       var message;
	    if(totalYears==0 && totalMonths==0 && remainingDays==0){
	    	message="0 years 0 months 1 days";
	    }
	    else{
	       message = totalYears + " years "
	       message += totalMonths + " months "
	       message += remainingDays + " days" 
	    }
	       $('#period'+f).val(message);
	      
	   	}
	       }
  //*****************END FOREIGN COUNTRY DETAILS************************//
  </script>
  <script>
//*****************START INTER ARM  DETAILS************************//

 function chgarm(){
	 /*  if($("#inter_arm_parent_arm_service").val() == "0700" || $("#inter_arm_parent_arm_service").val() == "0800"){ */
		  if($("#inter_arm_parent_arm_service").val() == "0800"){
			
			$("#inter_arm_regt").attr('readonly',false);
		}
	  else
		  {
		  $("#inter_arm_regt").attr('readonly',true);
		  $("#inter_arm_regt").val("0");
		  }
	  
  }
  
function validate_inter_arm_transfer_save(){

	if ($("input#inter_arm_authority").val() == "") {
	    alert("Please Enter Authority");
		$("input#inter_arm_authority").focus();
		return false;
	} 
		 if ($("input#date_of_authority").val() == "" || $("input#date_of_authority").val() == "DD/MM/YYYY") {
		 alert("Please Select Date of Authority");
			$("input#date_of_authority").focus();
			return false;
		} 
	 	 if ($("select#parent_arm_service").val() == "0") {
		 alert("Please Select Parent arm Services");
			$("select#parent_arm_service").focus();
			return false;
		} 
   var inter_arm_regt=$('#inter_arm_regt').val();
 	var inter_arm_parent_arm_serviceV = $("#inter_arm_parent_arm_service"+" option:selected").text();
 	
 	if (inter_arm_parent_arm_serviceV == "INFANTRY" && inter_arm_regt == "0") {
		alert("Please Select Inter Arm Regt");
		$('#inter_arm_regt').focus();
		return false;
	}

 	
 	var authority1=$('#inter_arm_authority').val();
 	var date_of_authority1=$('#inter_arm_date_of_authority').val();
 	var old_arm_service1=$('#inter_arm_old_arm_service').val();
 	var old_regt1=$('#inter_arm_old_regt').val();
 	var parent_arm_service1=$('#inter_arm_parent_arm_service').val();
 	var with_effect_from1=$('#inter_arm_with_effect_from').val();
 	var regt1=$('#inter_arm_regt').val();
 	var pre_ch_id=$('#p_id').val();
 	var census_id=$('#census_id').val();
 	var comm_id=$('#comm_id').val();
 
	   $.post('Inter_arm_action?' + key + "=" + value, {authority1:authority1, date_of_authority1:date_of_authority1,
		   old_arm_service1:old_arm_service1,old_regt1:old_regt1,parent_arm_service1:parent_arm_service1,
		   with_effect_from1:with_effect_from1,regt1:regt1,pre_ch_id:pre_ch_id,census_id:census_id,comm_id:comm_id}, function(data){
		      
	          if(data=="update")
	        	  alert("Data Updated Successfully");
	          else if(parseInt(data)>0){
				$('#p_id').val(data);
	        	  alert("Data Saved SuccesFully")
	          }
	          else
	        	  alert(data);
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch");
 	  		});
}

function getInterArm(){
	 var cen_id=$('#census_id').val(); 
	 var comm_id=$('#comm_idV').val(); 
	 var i=0;
	 $.post('getHisInter_arm_service_transferData?' + key + "=" + value, {comm_id:comm_id,cen_id:cen_id,cancel_status:cancel_status}, function(j){
		 var v=j.length;
		 $('#interArm_tbody').empty(); 
	     if(v!=0){
	             
			for(i;i<v;i++){
	     		con=i+1;
	     		var regt='';
	     		if(j[i].regt!=null && j[i].regt!='')
	     			regt=j[i].regt;
	     		
	     		var dauth=convertDateFormate(j[i].date_of_authority);
	     		var dmod=convertDateFormate(j[i].modified_date);
	     		var wef=convertDateFormate(j[i].with_effect_from);
				$("table#interArm_table").append('<tr id="tr_changeOfName'+con+'">'
	                     +'<td class="anm_srno">'+con+'</td>'
	                     +'<td style="width: 10%;">'+j[i].authority+'</td>'
	                     +'<td style="width: 10%;">'+dauth+'</td>'
	                     +'<td style="width: 10%;">'+j[i].parent_arm_service+'</td>'
	                     +'<td style="width: 10%;">'+regt+'</td>'
	                     +'<td style="width: 10%;">'+wef+'</td>'
	                     
	                     +'<td style="width: 10%;">'+j[i].modified_by+'</td>'
	                     +'<td style="width: 10%;">'+dmod+'</td>'
	                    
	                     +'<td style="width: 10%;"><a  class="btn btn-danger btn-sm" value="REMOVE" title = "REJECT" '+j[i].action+'><i class="fa fa-times"></i></a>'
	                     +'</td></tr>');

			}
				//$("#awardsNmedal_add"+anm).show();
		}
	 });
}	
//*****************END INTER ARM  DETAILS************************//
</script>

<script>
//***************** START CHANGE OF COMMISSION DETAILS************************//
function validate_coc_save_fn(){
		var authority=$('#coc_authority').val();
		var date_of_authority=$('#coc_date_of_authority').val();
		var persnl_no1=$('#persnl_no1').val();
		var persnl_no2=$('#persnl_no2').val();
		var persnl_no3=$('#persnl_no3').val();
		var date_of_permanent_commission=$('#coc_date_of_permanent_commission').val();
		var previous_commission=$('#coc_previous_commission').val();
		var type_of_commission_granted=$('#type_of_commission_granted').val();
		var date_of_seniority=$('#coc_date_of_seniority').val();
		var coc_ch_id=$('#coc_ch_id').val();
		var cen_id=$('#census_id').val();
		var comm_id=$('#comm_id').val();
		
 		if ($("input#coc_authority").val() == "") {
 			 alert("Please Enter Authority");
 				$("input#coc_authority").focus();
 				return false;
 			} 
 			 if ($("input#coc_date_of_authority").val() == "" || $("input#coc_date_of_authority").val() == "DD/MM/YYYY") {
 			 alert("Please Select Date of Authority");
 				$("input#coc_date_of_authority").focus();
 				return false;
 			} 
 			if ($("input#coc_new_personal_no").val() == "") {
 				 alert("Please Enter New Personal No");
 					$("input#coc_new_personal_no").focus();
 					return false;
 				} 
 			if ($("input#coc_date_of_permanent_commission").val() == "" || $("input#coc_date_of_permanent_commission").val() == "DD/MM/YYYY") {
 				 alert("Please Enter Permanent Commission Date");
					$("input#coc_date_of_permanent_commission").focus();
 					return false;
				} 
 			if ($("input#coc_previous_commission").val() == "") {
 				 alert("Please Enter Previous Commission");
					$("input#coc_previous_commission").focus();
 					return false;
				} 
			 
 		 	 if ($("select#type_of_commission_granted").val() == "0") {
 			 alert("Please Select Type of Commission Granted");
 				$("select#type_of_commission_granted").focus();
 				return false;
			} 
		 	 		 	if ($("input#coc_date_of_seniority").val() == "" || $("input#coc_date_of_seniority").val() == "DD/MM/YYYY") {
				 alert("Please Enter Date of Seniority");
 					$("input#coc_date_of_seniority").focus();
					return false;
 			} 
		
		   $.post('coc_action?' + key + "=" + value, {authority:authority,date_of_authority:date_of_authority,persnl_no1:persnl_no1,persnl_no2:persnl_no2,persnl_no3:persnl_no3,
			   date_of_permanent_commission:date_of_permanent_commission,previous_commission:previous_commission,type_of_commission_granted:type_of_commission_granted,
			   date_of_seniority:date_of_seniority,coc_ch_id:coc_ch_id,cen_id:cen_id,comm_id:comm_id}, function(data){
			      
		          if(data=="update")
		        	  alert("Data Updated Successfully");
		          else if(parseInt(data)>0){
		        	 $('#coc_ch_id').val(data);
		        	  alert("Data Saved SuccesFully")
		          }
		          else
		        	  alert(data);
		 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		  		});
}
function getcoc(){
	 var cen_id=$('#census_id').val(); 
	 var comm_id=$('#comm_idV').val(); 
	 var i=0;
	 $.post('getHisSsc_to_permt_commissionData?' + key + "=" + value, {comm_id:comm_id,cen_id:cen_id,cancel_status:cancel_status}, function(j){
		 var v=j.length;
		 $('#sscToper_tbody').empty(); 
	     if(v!=0){
	             
			for(i;i<v;i++){
	     		con=i+1;
	     		
	     		var dauth=convertDateFormate(j[i].date_of_authority);
	     		
	     		var dmod=convertDateFormate(j[i].modified_date);
	     		var dpc=convertDateFormate(j[i].date_of_permanent_commission);
	     		var ds=convertDateFormate(j[i].date_of_seniority);
	     		var eds=convertDateFormate(j[i].eff_date_of_seniority);
				$("table#sscToper_table").append('<tr id="tr_changeOfName'+con+'">'
	                     +'<td class="anm_srno">'+con+'</td>'
	                     +'<td style="width: 10%;">'+j[i].authority+'</td>'
	                     +'<td style="width: 10%;">'+dauth+'</td>'
	                     +'<td style="width: 10%;">'+j[i].new_personal_no+'</td>'
	                     +'<td style="width: 10%;">'+j[i].type_of_commission+'</td>'
	                     +'<td style="width: 10%;">'+dpc+'</td>'
	                     +'<td style="width: 10%;">'+ds+'</td>'
	                     +'<td style="width: 10%;">'+eds+'</td>'
	                     +'<td style="width: 10%;">'+j[i].modified_by+'</td>'
	                     +'<td style="width: 10%;">'+dmod+'</td>'
	                     +'<td style="display:none;"><input type="text" id="changeOfRank_ch_id'+con+'" name="changeOfRank_ch_id'+con+'"  value="' + j[i].id + '" class="form-control autocomplete" autocomplete="off" ></td>'
	                     +'<td style="width: 10%;"><a  class="btn btn-danger btn-sm" value="REMOVE" title = "REJECT" '+j[i].action+'><i class="fa fa-times"></i></a>'
	                     +'</td></tr>');

			}
				//$("#awardsNmedal_add"+anm).show();
		}
	 });
}
function onChangePerNo(obj)
{
	var data = obj.value;

	if(data.length == 5)
	{
		var suffix="";
		var summation = 6*parseInt(data[0])+5*parseInt(data[1])+4*parseInt(data[2])+3*parseInt(data[3])+2*parseInt(data[4]);
		
		summation = parseInt( parseInt(summation) % 11);
	
		if(summation == 0)
		{
			suffix="A";
		}
		if(summation == 1)
		{
			suffix="F";
		}
		if(summation == 2)
		{
			suffix="H";
		}
		if(summation == 3)
		{
			suffix="K";
		}
		if(summation == 4)
		{
			suffix="L";
		}
		if(summation == 5)
		{
			suffix="M";
		}
		if(summation == 6)
		{
			suffix="N";
		}
		if(summation == 7)
		{
			suffix="P";
		}
		if(summation == 8)
		{
			suffix="W";
		}
		if(summation == 9)
		{
			suffix="X";
		}
		if(summation == 10)
		{
			suffix="Y";
		}
		 $.ajaxSetup({
             async : false
    	 });
		$("#persnl_no3").val(suffix);
		 $.ajaxSetup({
             async : false
    	 });
 		Personal_no_already_exist();
		
	}
}

function Personal_no_already_exist()
{
	
	 var persnl_no1 = $("select#persnl_no1").val();
	 if(persnl_no1 == "-1")
	 {
		 alert("Please Select Personal Number Prefix.")
		 return false;
	 }
	 var persnl_no2 = $("input#persnl_no2").val();
	 if(persnl_no2.length != 5 )
	 {
		 alert("Please Enter Personal Number.")
		 return false;
	 }
	 var persnl_no3 = $("input#persnl_no3").val();
	 if(persnl_no3.length != 1 )
	 {
		 alert("Please Enter Personal Number.")
		 return false;
	 }
	 var personnel_no = persnl_no1 + persnl_no2 + persnl_no3;
	 
	 
	 $.post("getPersonnelNoAlreadyExist?"+key+"="+value, {personnel_no : personnel_no}).done(function(j) {
		
		 	if(j == true){
				alert("Personal Number already Exist.")
				$("select#persnl_no1").val("-1");
				$("input#persnl_no2").val("");
				$("input#persnl_no3").val("0");
			} 
			
		}); 
}
//***************** END CHANGE OF COMMISSION DETAILS************************//
</script>

<script>
//***************** START EXTENSION OF COMMISSION DETAILS************************//
function validate_eoc_save_fn(){
			var authority=$('#eoc_authority').val();
			var date_of_authority=$('#eoc_authority_date').val();
			var from=$('#eoc_from').val();
			var to=$('#eoc_to').val();
			var eoc_ch_id=$('#eoc_ch_id').val();
			var cen_id=$('#census_id').val();
			var comm_id=$('#comm_id').val();
			
			if ($("input#eoc_authority").val() == "") {
				 alert("Please Enter Authority");
					$("input#eoc_authority").focus();
					return false;
				} 
				 if ($("input#eoc_authority_date").val() == "") {
				 alert("Please Select Date of Authority");
					$("input#eoc_authority_date").focus();
					return false;
				} 
				 if ($("input#eoc_from").val() == "") {
					 alert("Please Select From");
						$("input#eoc_from").focus();
						return false;
					}  
				 if ($("input#eoc_to").val() == "") {
					 alert("Please Select To");
						$("input#eoc_to").focus();
						return false;
					}  
				 
				 if(getformatedDate(from) > getformatedDate(to)) {
						alert("To Date should always be greater than From Date");
						$("input#eoc_to").focus();
						return false;
					} 
				 
			   $.post('eoc_action?' + key + "=" + value, {authority:authority,date_of_authority:date_of_authority,to:to
				   ,from:from,eoc_ch_id:eoc_ch_id,cen_id:cen_id,comm_id:comm_id}, function(data){
			          if(data=="update")
			        	  alert("Data Updated Successfully");
			          else if(parseInt(data)>0){
			        	 $('#eoc_ch_id').val(data);
			        	 alert("Data Saved SuccesFully")
			          }
			          else
			        	  alert(data);
			 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
			  		});
	}
function geteoc(){
	 var cen_id=$('#census_id').val(); 
	 var comm_id=$('#comm_idV').val();
	 i=0;
	 $.post('getHisExtension_of_sscData?' + key + "=" + value, {comm_id:comm_id,cancel_status:cancel_status}, function(j){
		 var v=j.length;
		 $('#Extension_of_ssc_tbody').empty(); 
	     if(v!=0){
	             
			for(i;i<v;i++){
	     		cor=i+1;
	     		
	     	
	     		
	     		
	   
	     		
	     		var dauth=convertDateFormate(j[i].date_of_authority);
	     		var fd=convertDateFormate(j[i].fromdt);
	     		var td=convertDateFormate(j[i].todt);
	     		var dmod=convertDateFormate(j[i].modified_date);
				$("table#Extension_of_ssc_table").append('<tr id="tr_ssc_cause'+cor+'">'
	                     +'<td class="anm_srno">'+cor+'</td>'
	                     +'<td style="width: 10%;">'+j[i].authority+'</td>'
	                     +'<td style="width: 10%;">'+dauth+'</td>'
	                     +'<td style="width: 10%;">'+fd+'</td>'
	                     +'<td style="width: 10%;">'+td+'</td>'
	                    
	                     +'<td style="width: 10%;">'+j[i].modified_by+'</td>'
	                     +'<td style="width: 10%;">'+dmod+'</td>'                    
	                     +'<td style="width: 10%;"><a  class="btn btn-danger btn-sm" value="REMOVE" title = "REJECT"  '+j[i].action+'><i class="fa fa-times"></i></a>'
	                     +'</td></tr>');

			}
		}
	 });
	} 
	function formateDate(value){
		var date = new Date(value);
		var formattedDate = date.getFullYear() + '-' + ('0' + (date.getMonth() + 1)).slice(-2) + '-' + ('0' + date.getDate()).slice(-2);
		return formattedDate;
	}
	//***************** END EXTENSION OF COMMISSION DETAILS************************//
</script>
  


<script>
//***************** START MARRIED DETAILS************************//
	function checkDivorcedate(obj,marriage_date) {
			
			if(marriage_date.value !="")
			{
				var id = obj.id;
				var myDate = document.getElementById(id).value;
				var Date1 = marriage_date.value;
				if ((Date.parse(myDate) < Date.parse(Date1))) {
					alert('Divorce Date should not be before Marriage Date1');
					$("input#divorce_date").focus();
					$("input#divorce_date").val("");
				}
			}
		}

function married_save_fn(ps){
	var marital_event=$('#marital_event').val();
	var marital_status=$('#marital_status').val();
	var maiden_name=$('#maiden_name').val();
	var marriage_date_of_birth=$('#marriage_date_of_birth').val();
	var marriage_place_of_birth=$('#marriage_place_of_birth').val();
	var marriage_nationality=$('#marriage_nationality').val();
	var marriage_date=$('#marriage_date').val();
	var marriage_adhar_no=$('#marriage_adhar_no').val().split('-').join('');
	var pan_card=$('#pan_card').val();
	var marr_ch_id=$('#marr_ch_id').val();
	var divorce_date=$('#divorce_date').val();
	var marriage_authority=$('#marriage_authority').val();
	var marriage_date_of_authority=$('#marriage_date_of_authority').val();
	var comm_id=$('#comm_id').val();	
	var p_id=$('#census_id').val();
	
	if (marital_event == null || marital_event=="0") {
		alert("Please Select the Marital Event");
		$('#marital_event').focus()
		return false;
	}
	/* if (marital_status == null || marital_status=="0") {
		alert("Please Select the Marital Event");
		$('#marital_status').focus()
		return false;
	} */
	if(marital_event == 1 || marital_event == 3){
		if (marriage_authority == null || marriage_authority=="") {
			alert("Please Enter the Authority");
			$('#marriage_authority').focus()
			return false;
		}
		if (marriage_date_of_authority == null || marriage_date_of_authority=="" || marriage_date_of_authority=="DD/MM/YYYY") {
			alert("Please Enter the Date of Authority");
			$('#marriage_date_of_authority').focus()
			return false;
		}
		if (maiden_name == null || maiden_name=="")  {
			alert("Please Enter The Maiden Name");
			$('#maiden_name').focus()
			return false;
		}
		if (marriage_date_of_birth == null || marriage_date_of_birth=="" || marriage_date_of_birth=="DD/MM/YYYY") {
			alert("Please Select The Date of Birth");
			$('#marriage_date_of_birth').focus()
			return false;
		}
		if (marriage_place_of_birth == null || marriage_place_of_birth=="") {
			alert("Please Enter The Place of Birth");
			$('#marriage_place_of_birth').focus()
			return false;
		}
		if (marriage_nationality == null || marriage_nationality=="0") {
			alert("Please Select The Nationality");
			$('#marriage_nationality').focus()
			return false;
		}
		if (marriage_date == null || marriage_date=="" || marriage_date=="DD/MM/YYYY") {
			alert("Please Select The Date of Marriage");
			$('#marriage_date').focus()
			return false;
		}
		if (marriage_adhar_no == null || marriage_adhar_no=="") {
			alert("Please Enter The Adhar Number");
			$('#marriage_adhar_no').focus()
			return false;
		}
		if (pan_card == null || pan_card=="") {
			alert("Please Enter Spouse PAN Card No");
			$('#pan_card').focus()
			return false;
		}
		
		if(getformatedDate(marriage_date_of_birth) > getformatedDate(marriage_date)) {
			alert("Marriage Date should be Greater than Date of Birth");
			$('#marriage_date').focus()
			return false;
		}
	}
	
	if( marital_event=='2'){
		if (divorce_date == null || divorce_date=="" || divorce_date=="DD/MM/YYYY") {
			alert("Please Select The Divorce Date");
			$('#marital_event').focus()
			return false;
		}
	}
	   $.post('update_family_marriage_action?' + key + "=" + value, {maiden_name:maiden_name,marriage_date_of_birth:marriage_date_of_birth,
		   marriage_place_of_birth:marriage_place_of_birth,marriage_nationality:marriage_nationality,marriage_date:marriage_date,marriage_adhar_no:marriage_adhar_no,pan_card:pan_card,
		   marr_ch_id:marr_ch_id,divorce_date:divorce_date,marriage_authority:marriage_authority,marriage_date_of_authority:marriage_date_of_authority
		   ,marital_event:marital_event,p_id:p_id,comm_id:comm_id ,marital_status:marital_status}, function(data){
			   if(data=="update"){
				   alert("Data Updated Successfully");
				   return spouse_qualification_save_fn();
			   }
			   else if(parseInt(data)>0){
	        	 $('#marr_ch_id').val(data);
	        	  alert("Data Saved SuccesFully");
	        	  return spouse_qualification_save_fn();
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

function spouse_qualification_save_fn(){
	
	 spouse_quali_bt=$('input[name="spouse_quali_radio"]:checked').val();
	 if(spouse_quali_bt=='no'){
		 return false;
	 }
	 else{
		 var spouse_id=$('#marr_ch_id').val();
			var dateofBirthyear=$("#marriage_date_of_birth").val().split('/')[0];
		
			var currentdate=new Date();
			var currentyear=currentdate.getFullYear();
			
			var type=$('#spouse_quali_type').val();
			var examination_pass=$('#spouse_quali').val();
			
			
			var ot_spouse_exam=$("#spouse_exam_other").val();
			var ot_spouse_div=$("#spouse_div_other").val();
			
			
			var stream=$("#spouse_stream12").val();
			var passing_year=$('#spouse_yrOfPass').val();
			var course_name=$('#spouse_quali_course_name').val();
			var specialization=$('#spouse_specialization').val();		
			var div_class=$('#spouse_div_class').val();
			var institute=$('#spouse_institute_place').val();
			var qualification_ch_id=$('#spouse_qualification_ch_id').val();
			var q_id=$('#census_id').val();
			var com_id=$('#comm_id').val();
			var subjectvar= $('input[name="spouse_multisub"]:checkbox:checked').map(function() {
			    return this.value;
			}).get();
			var subject=subjectvar.join(",");
			
			 if (examination_pass == "0") {
				
				}
			 
			 
			 if(type!="6")
				{
					if(examination_pass == null || examination_pass=="0"){ 
						alert("Please Enter Examination Pass");
						$("input#spouse_quali").focus();
						return false;
					}
					if(examination_pass=="12th") {
						if(stream == null || stream=="0"){ 
							alert("Please Select The Stream");
							$("input#spouse_stream12").focus();
							return false;
						}
						if( stream=="Science"){ 
							if(specialization == null || specialization=="0"){ 
								alert( "Please Select The Specialization");
								$("input#spouse_specialization").focus();
								return false;
							}
						}
					}
					if(!examination_pass=="12th" && !examination_pass=="10th" && !examination_pass=="others") {
						if(specialization == null || specialization=="0"){ 
							alert( "Please Select The  Specialization");
							$("input#spouse_specialization").focus();
							return false;
						}
					}	
					
				}
				else {
					if(specialization == null || specialization=="0"){ 
						alert("Please Select The Specialization");
						$("input#spouse_specialization").focus();
						return false;
					}
					if(course_name == null || course_name=="0"){ 
						alert("Please Select The Course Name");
						$("input#spouse_quali_course_name").focus();
						return false;
					}
				}
			 
			 
		  		
		           if(examination_pass=="others") {
		  	        	 if(ot_spouse_exam == null || ot_spouse_exam==""){ 	 
		  	         
		  				alert( "Please Enter Spouse Exam Other ");
		  				$("input#exam_other").focus();
		  				return false;
		  			   }
		  	         }
				if (passing_year == "") {
					alert("Please Enter yr of Passing");
					$("input#spouse_yrOfPass").focus();
					return false;
				}
				
		 		if(passing_year != ""){
				
					if(parseInt(passing_year)<=parseInt(dateofBirthyear) || parseInt(passing_year)>parseInt(currentyear)){
						alert("Please Enter Valid Year of Passing");
						$("input#spouse_yrOfPass").focus();
						return false;
					}
				} 
				if (div_class == "0") {
					alert("Please Enter Div/class");
					$("input#spouse_div_class").focus();
					return false;
				}
				if(div_class=="4") {
		         	 if(ot_spouse_div == null || ot_spouse_div==""){ 	 
		          
		  			alert( "Please Enter Spouse Div/Grade Other ");
		  			$("input#class_other").focus();
		  			return false;
		  		   }
		          }
			 if (subject == "") {
					alert("Please Enter Subject");
					$("input#spouse_sub_quali").focus();
					return false;
				}
			 if (institute == "") {
					alert("Please select Institute of place");
					$("select#spouse_institute_place").focus();
					return false;
				}
			
			
					   $.post('spouse_qualification_action?' + key + "=" + value, {type:type,examination_pass:examination_pass,passing_year:passing_year,
						   div_class:div_class,subject:subject,institute:institute,qualification_ch_id:qualification_ch_id,q_id:q_id ,stream:stream,
						   course_name:course_name,specialization:specialization,com_id:com_id,dateofBirthyear:dateofBirthyear,spouse_id:spouse_id,ot_spouse_exam:ot_spouse_exam,ot_spouse_div:ot_spouse_div}, function(data){	   
			        
			           if(parseInt(data)>0){ 	 		        
			        	  //alert("Data Saved SuccesFully")
			        	  $('#spouse_qualification_ch_id').val(data);
			          }
			          else
			        	  alert(data)
			        	  
			 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")});
		}
}

function getfamily_marriageDetails(){
	
	var cen_id=$('#census_id').val(); 
	var comm_id=$('#comm_idV').val();
	var i=0;
	 $.post('getHisfamily_marriedData?' + key + "=" + value, {comm_id:comm_id,cen_id:cen_id,cancel_status:cancel_status}, function(j){
		 var v=j.length;
		 $('#Marital_status_tbody').empty();
		 $('#Marital_div_status_tbody').empty();
	     if(v!=0){
	              
			for(i;i<v;i++){
	     		cor=i+1;
	     			$("table#Marital_status_table").append('<tr id="tr_Marital_status'+cor+'">'
		                     +'<td class="anm_srno">'+cor+'</td>'
		                     +'<td style="width: 10%;">'+j[i].authority+'</td>'
		                     +'<td style="width: 10%;">'+j[i].date_of_authority+'</td>'
		                     +'<td style="width: 10%;">'+j[i].marital_name+'</td>'
		                     +'<td style="width: 10%;">'+j[i].maiden_name+'</td>'
		                     +'<td style="width: 10%;">'+j[i].marriage_date+'</td>'
		                     +'<td style="width: 10%;">'+j[i].place_of_birth+'</td>'
		                     +'<td style="width: 10%;">'+j[i].nationality_name+'</td>'
		                     +'<td style="width: 10%;">'+j[i].date_of_birth+'</td>'
		                     +'<td style="width: 10%;">'+j[i].adhar_number+'</td>'
		                     +'<td style="width: 10%;">'+j[i].pan_card+'</td>'
		                     +'<td style="width: 10%;">'+j[i].spouse_service+'</td>'
		                     +'<td style="width: 10%;">'+j[i].spouse_personal_no+'</td>'
		                     +'<td style="width: 10%;">'+j[i].separated_form_dt+'</td>'
		                     +'<td style="width: 10%;">'+j[i].separated_to_dt+'</td>'
		                     +'<td style="width: 10%;">'+j[i].divorce_date+'</td>'
		                     +'<td style="width: 10%;">'+j[i].modified_by+'</td>'
		                     +'<td style="width: 10%;">'+j[i].modified_date+'</td>'
		                     +'<td style="display:none;"><input type="text" id="Marital_status_ch_id" name="Marital_status_ch_id'+cor+'"  value="' + j[i].id + '" class="form-control autocomplete" autocomplete="off" ></td>'
		                   +'<td style="width: 10%;"><a  class="btn btn-danger btn-sm" value="REMOVE" title = "REJECT" '+j[i].action+'><i class="fa fa-times"></i></a>'
	                     +'</td></tr>');
// 	     			if(j[i].status==1){
// 						$("#tr_Marital_status"+cor).attr("bgcolor",'#b9deb3');
// 					}
				

			}
		}
	 });	
	
	
	 
}

function getSpouseQualificationData(){
	
	var comm_id=$('#comm_idV').val();
	
	var i=0;
	$.post('getHisSpouseQualificationData?' + key + "=" + value, {comm_id:comm_id,cancel_status:cancel_status}, function(j){
		 var v=j.length;
		 $('#spouse_quali_tbody').empty();
	     if(v!=0){
	              
			for(i;i<v;i++){
	     		cor=i+1;
	     		var dmod=convertDateFormate(j[i].modified_date);
	     		var exp_name =j[i].exp_name;
	     		var d_name =j[i].d_name;
	     		var spce_name =j[i].spce_name;
	     		var div =j[i].div;
	     		
	     		if(j[i].exp_name=="OTHERS"){
	     			exp_name=j[i].exam_other;
	     		}
	     		if(j[i].d_name=="OTHERS"){
	     			d_name=j[i].degree_other;
	     		}
	     		if(j[i].spce_name=="OTHERS"){
	     			spce_name=j[i].specialization_other;
	     		}
	     		if(j[i].div=="OTHERS"){
	     			div=j[i].class_other;
	     		}
	     		$("table#spouse_quali_table").append('<tr id="tr_spouse_quali'+cor+'">'
	                     +'<td class="anm_srno">'+cor+'</td>'
	                     +'<td style="width: 10%;">'+j[i].maiden_name+'</td>'
	                     +'<td style="width: 10%;">'+j[i].type+'</td>'
	                     +'<td style="width: 10%;">'+exp_name+'</td>'
	                     +'<td style="width: 10%;">'+d_name+'</td>'
	                     +'<td style="width: 10%;">'+spce_name+'</td>'
	                     +'<td style="width: 10%;">'+j[i].passing_year+'</td>'
	                     +'<td style="width: 10%;">'+div+'</td>'
	                     +'<td style="width: 10%;">'+j[i].institute+'</td>'
	                     +'<td style="width: 10%;">'+j[i].subject+'</td>'
	                     +'<td style="width: 10%;">'+j[i].modified_by+'</td>'
	                     +'<td style="width: 10%;">'+dmod+'</td>'
	                     +'<td style="width: 10%;"><a  class="btn btn-danger btn-sm" value="REMOVE" title = "REJECT" '+j[i].action+'><i class="fa fa-times"></i></a>'
	                     +'</td></tr>');
// 	     		if(j[i].status==1){
// 					$("#tr_spouse_quali"+cor).attr("bgcolor",'#b9deb3');
// 				}
			}
		}
	 });
	
	
	
}


function fn_qualification_typeChangeSpouse(){
	
	var val=$("#spouse_quali_type").val();
	if(val=="6"){
		$("#spouse_specializationDiv").show();
	    $("#spouse_courseNamediv").show();
	    $("#spouse_stream12Div").hide()
	    $("#spouse_academyQuali").hide();
	    $("#spouse_quali").val("0");
	    $("#spouse_stream12").val("0");
	    fn_setSpecializationSpouse("Tech")
	    
	}
	else {
		 $("#spouse_courseNamediv").hide();
		 $("#spouse_stream12Div").hide()
		 $("#spouse_specializationDiv").hide();
		 $("#spouse_academyQuali").show()
		 $("#spouse_stream12").val("0");
		 $("#spouse_course_name").val("0");
		 $("#spouse_specialization").val("0");
	}
}

function fn_ExaminationTypeChangeSpouse(){
	var val=$("#spouse_quali").val();
	if(val=='12th'){
		 $("#spouse_stream12Div").show()
	}
	else{
		$("#spouse_stream12Div").hide()
	}
	
	if(val!='12th' && val!='10th' && val!='0' && val!='others'){
		fn_setSpecializationSpouse(val);
		$("#spouse_specializationDiv").show();
	}
	else{
		$("#spouse_specializationDiv").hide();
		$("#spouse_specialization").val("0");
	}
}


function fn_streamChangedSpouse(){
	var val=$("#spouse_stream12").val();
	if(val=='Science'){
		fn_setSpecializationSpouse(val);
		$("#spouse_specializationDiv").show();
	}
	else{
		$("#spouse_specializationDiv").hide();
		$("#spouse_specialization").val("0");
	}
}

function marital_eventchange(){
	var marital_eventvalue=$('#marital_event').val();
	if(marital_eventvalue=='1' || marital_eventvalue=='3'){
		$("#marriage_remarriageDiv").show();
		$("#divorceDiv").hide();
		$("#marital_status").val('8');
	}
	else if(marital_eventvalue=='2'){
		$("#marital_status").val('9');
		getfamily_marriageDetails();
		$("#divorceDiv").show();
		$("#spouse_quali_radioDiv").hide();
	}
	else if( marital_eventvalue=='4'){
		$("#marital_status").val('13');
		getfamily_marriageDetails();
		$("#spouse_quali_radioDiv").hide();
	}
	else{
		$("#marriage_remarriageDiv").hide();
		$("#divorceDiv").hide();
		$("#marital_status").val('0');
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
</script>
<script>
//***************** START FIRING STANDARD DETAILS************************//

	// unit name
	/*  $("input#unit_posted_to").keypress(function(){
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
		}); */
		
		
	 function Firing_UnitAuto(obj) {
			
			var check_id = obj.id;
			var k = check_id.replace('firing_unit_name','');
			//var sub_cat_id = $("#sub_cat_id").val();
			// Nomen AutoComplete
			var wepetext=$("#"+obj.id);
		    wepetext.autocomplete({
		        source: function( request, response ) {
		          $.ajax({
		          type: 'POST',
		          url: "PSG_CADET_getTargetUnitsNameActiveList?"+key+"="+value,
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
		   				alert("Please Enter Condacted at Unit ");			
		   				wepetext.val("");
		   				wepetext.focus();
		   				return false;
		   			}
		        }, 
		        select: function( event, ui ) {
			    	 var unit_name = ui.item.value;
			    
				            $.post("getTargetSUSFromUNITNAME?"+key+"="+value,{target_unit_name:unit_name}, function(data) {
				            }).done(function(data) {
				            	var length = data.length-1;
					        	var enc = data[length].substring(0,16);
					        	$("#firing_unit_sus_no"+k).val(dec(enc,data[0]));
				            }).fail(function(xhr, textStatus, errorThrown) {
				            });
			      } 
		      });
		} 


function isNumberPointKey(evt) {
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
function Checkyear(obj){
    var d = new Date();
    var year = d.getFullYear() - 10;
    if(obj.value > year ){
            $("#"+obj.id).focus();
            alert("Please Enter the Valid Year");
            $("#"+obj.id).val("");
    }
}
/////////////////////For data get fire standard
function fire_Details() {
	var cen_id=$('#census_id').val(); 
	var comm_id=$('#comm_idV').val();
	var i=0;
	 $.post('getHisFiringDetailsData?' + key + "=" + value, {comm_id:comm_id,cen_id:cen_id,cancel_status:cancel_status}, function(j){
			var v=j.length;
			$('#fire_stdtbody').empty(); 
			 if(v!=0){
	              
					for(i;i<v;i++){
			     		cor=i+1;
			     		
			     		var dauth=convertDateFormate(j[i].date_of_authority);
			     		var dmod=convertDateFormate(j[i].modified_date);
			     		var firing_grade =j[i].firing_grade;
			     		
			     		if(j[i].firing_grade=="OTHERS"){
			     			firing_grade=j[i].ot_firing_grade;
			     		}
						$("table#fire_std_table").append('<tr id="tr_fire_std'+cor+'">'
			                     +'<td class="anm_srno">'+cor+'</td>'
			                     +'<td style="width: 10%;">'+firing_grade+'</td>'
			                     +'<td style="width: 10%;">'+j[i].firing_event_qe+'</td>'
			                     +'<td style="width: 10%;">'+j[i].year+'</td>'
			                     +'<td style="width: 10%;">'+fn_getUnitnamefromSusHis(j[i].firing_unit_sus_no)+'</td>'
			                     +'<td style="width: 10%;">'+j[i].modified_by+'</td>'
			                     +'<td style="width: 10%;">'+dmod+'</td>'
			                     +'<td style="display:none;"><input type="text" id="flang_ch_id'+cor+'" name="flang_ch_id'+cor+'"  value="' + j[i].id + '" class="form-control autocomplete" autocomplete="off" ></td>'
			                     +'<td style="width: 10%;"><a  class="btn btn-danger btn-sm" value="REMOVE" title = "REJECT" '+j[i].action+'><i class="fa fa-times"></i></a>'
			                     +'</td></tr>');
			}
			 }	
	  });
	 
	
}

function onFiringGrade(ind){
	var firing_grade = $("#firing_grade"+ind+" option:selected").text();
	 
	if(firing_grade != "OTHERS"){
		$('#ot_firing_grade'+ind).val("");
		$('#ot_firing_grade'+ind).attr('readonly', true);
	}
	else
		$('#ot_firing_grade'+ind).attr('readonly', false);
}



//////////////////fire save function ///////////
function fire_std_save_fn(ps){
	var d = new Date();	
	var cyear = d.getFullYear();
	if ($("select#firing_grade").val() == "-1") {
	alert("Please Enter Firing Grade");
	$("input#firing_grade1").focus();
	return false;
	}
	if ($("select#firing_event_qe1").val() == "-1") {
	alert("Please Select Firing Event  Qe");
	$("input#firing_event_qe1").focus();
	return false;
	}
	if($("#year1").val() == ""){
	alert("Please Enter the Year");
	$("#year1").focus();
	return false;
	}	
	if($("#year1").val() > cyear){
	alert("Future Year is not allowed");
	$("#year1").focus();
	 return false;
	}  
	if($("#year1").val() == cyear && fire_validate($("select#firing_event_qe1").val())==0){
	alert("Future Month is not allowed");
	$("#firing_event_qe1").focus();
	 return false;
	} 
	var firing_grade=$('#firing_grade'+ps).val();
	var ot_firing_grade=$('#ot_firing_grade'+ps).val();
	var firing_event_qe=$('#firing_event_qe'+ps).val();
	var year=$('#year'+ps).val();
	var firing_unit_sus_no=$('#firing_unit_sus_no'+ps).val()
	var fire_id=$('#fire_id'+ps).val();
	var census_id=$('#census_id').val();
	var com_id=$('#comm_id').val();

	if (firing_grade == "-1") {
	alert("Please Enter Firing Grade");
	$("input#firing_grade").focus();
	return false;
	}

	var firing_gradeV = $("#firing_grade"+ps+" option:selected").text();
	if (firing_gradeV == "OTHERS" && ot_firing_grade == "") {
		alert("Please Enter Other Firing Grade");
		$('#ot_firing_grade'+ps).focus();
		return false;
	}

	if (firing_event_qe == "-1") {
	alert("Please Select Firing Event  QTR");
	$("input#firing_event_qe").focus();
	return false;
	}
	if(year == ""){
	alert("Please Enter the Year");
	return false;
	}	
	if(firing_unit_sus_no == ""){
	alert("Please Enter Conducted at Unit");
	return false;
	}	
	if($('#year'+ps).val() == cyear && fire_validate($('#firing_event_qe'+ps).val())==0){
		alert("Future Month is not allowed"); 		
		 return false;
	}  	

	if($('#firing_unit_name'+ps).val() == ""){
			alert("Please Enter Conducted at Unit"); 		
			 return false;
	} 

	$.post('fire_std_action?' + key + "=" + value, {firing_grade:firing_grade,ot_firing_grade:ot_firing_grade,firing_event_qe:firing_event_qe,
	   year:year,firing_unit_sus_no:firing_unit_sus_no,fire_id:fire_id,census_id:census_id,com_id:com_id }, function(data){
	      if(data=="update")
	    	  alert("Data Updated Successfully");
	      else if(parseInt(data)>0){
	    	 $('#fire_id'+ps).val(data);
	    	  alert("Data Saved SuccesFully")
	    	  $("#fire_std_add"+ps).show();
	        	 $("#fire_remove"+ps).show();
	      }
	      else
	    	  alert(data);
		  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
			});
	}
////FIRE add//////////
fs=1;
fire_srno=1;
function fire_std_add_fn(q){
if( $('#fire_std_add'+q).length )        
{
	$("#fire_std_add"+q).hide();
}
if(q!=0)
 fire_srno+=1;
fs=q+1;
$("table#fire_std_table").append('<tr id="tr_fire_std'+fs+'">'
+'<td class="fire_srno">'+fire_srno+'</td>'
+'<td style="width: 10%;"> '
+'<select name="firing_grade'+fs+'" id="firing_grade'+fs+'" class="form-control" onchange="onFiringGrade('+fs+')"   >'
+'<option value="-1">--Select--</option>'
+'<c:forEach var="item" items="${getFiring_grade}" varStatus="num">'
+'<option value="${item[0]}" name="${item[1]}">${item[1]}</option>'
+'</c:forEach>'
+'</select></td>'

+'<td style="width: 10%;"> <input type="text" id="ot_firing_grade'+fs+'" name="ot_firing_grade'+fs+'" onkeypress="return /[0-9a-zA-Z]/i.test(event.key)" readonly  class="form-control autocomplete" autocomplete="off" ></td>'


+'<td style="width: 10%;"> '
+'<select name="firing_event_qe'+fs+'" id="firing_event_qe'+fs+'" class="form-control" >'
+'<option value="-1">--Select--</option>'
+'<c:forEach var="item" items="${getFiring_event_qe}" varStatus="num">'
+' <option value="${item[0]}" name="${item[1]}">${item[1]}</option>'
+'</c:forEach>'
+'</select></td>'
+'<td style="width: 10%;">	<input type="text" id="year'+fs+'" name="year'+fs+'" value="0" class="form-control autocomplete"  onchange="Checkyear(this)" autocomplete="off" maxlength="4" onkeypress="return isNumberPointKey(event);">'
+'</td>'
+ '<td style="width: 10%;">'
+'<input type="text" id="firing_unit_name'+fs+'" name="firing_unit_name'+fs+'" class="form-control autocomplete" onkeypress="Firing_UnitAuto(this);" autocomplete="off">'
+'<input type="hidden" id="firing_unit_sus_no'+fs+'" name="firing_unit_sus_no'+fs+'" class="form-control autocomplete" autocomplete="off">'
+ '</td>'
+'<td style="display:none;"><input type="text" id="fire_id'+fs+'" name="fire_id'+fs+'"   value="0" class="form-control autocomplete" autocomplete="off" ></td>'
+'<td style="width: 10%;"><a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "fire_std_save'+fs+'" onclick="fire_std_save_fn('+fs+');" ><i class="fa fa-save"></i></a>'
+'<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "fire_std_add'+fs+'" onclick="fire_std_add_fn('+fs+');" ><i class="fa fa-plus"></i></a>'
+'<a style="display:none;" class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "fire_remove'+fs+'" onclick="fire_remove_fn('+fs+');"><i class="fa fa-trash"></i></a>' 
+'</td></tr>');
}

//////////////////fire remove function ///////
function fire_remove_fn(R){
	var rc = confirm("Are You Sure! You Want To Delete");
	 if(rc){
	var fire_id=$('#fire_id'+R).val();
	  $.post('fire_delete_action?' + key + "=" + value, {fire_id:fire_id }, function(data){ 
			   if(data=='1'){
				   $("tr#tr_fire_std"+R).remove(); 
					 if(R==fs){
						 R = R-1;
						 var temp=true;
						 for(i=R;i>=1;i--){
						 if( $('#fire_std_add'+i).length )         
						 {
							 $("#fire_std_add"+i).show();
							 temp=false;
							 fs=i;
							 break;
						 }}
						 if(temp){
							 fire_std_add_fn(0);
							}
			  			 }
					 $('.fire_srno').each(function(i, obj) {
						 
							obj.innerHTML=i+1;
							fire_srno=i+1;
							 });
							 alert("Data Deleted SuceessFully");
			   }
				 else{
					 alert("Data not Deleted ");
				 }
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});
	 }
}
//***************** END FIRING STANDARD DETAILS************************//
</script>

<script>
//***************** START BPET DETAILS************************//

	 function BPET_UnitAuto(obj) {
			
			var check_id = obj.id;
			var k = check_id.replace('unit_name','');
			//var sub_cat_id = $("#sub_cat_id").val();
			// Nomen AutoComplete
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
			   				alert("Please Enter Condacted at Unit ");			
			   				wepetext.val("");
			   				wepetext.focus();
			   				return false;
			   			}
		        }, 
		        select: function( event, ui ) {
			    	 var unit_name = ui.item.value;
			    
				            $.post("getTargetSUSFromUNITNAME?"+key+"="+value,{target_unit_name:unit_name}, function(data) {
				            }).done(function(data) {
				            	var length = data.length-1;
					        	var enc = data[length].substring(0,16);
					        	$("#unit_sus_no"+k).val(dec(enc,data[0]));
				            }).fail(function(xhr, textStatus, errorThrown) {
				            });
			      } 
		      });
		} 

function isNumberPointKey(evt) {
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
function onBeptResult(ind){
	var BPET_result = $("#BPET_result"+ind+" option:selected").text();
	if(BPET_result != "OTHERS"){
		$('#bept_result_others'+ind).val("");
		$('#bept_result_others'+ind).attr('readonly', true);
	}
	else
		$('#bept_result_others'+ind).attr('readonly', false);
}
function bpet_save_fn(ps){
	var d = new Date();
	var cyear = d.getFullYear(); 
	if ($("select#BPET_result1").val() == "-1") {
		alert("Please Enter BPET Result");
		$("input#BPET_result1").focus();
		return false;
	}
 	if ($("select#BPET_qe1").val() == "-1") {
		alert("Please Select BPET QE");
		$("input#BPET_qe1").focus();
		return false;
	}
 	if($("#bpet_year1").val() == ""){
		alert("Please Enter the Year");
		$("#year1").focus();
		return false;
	}	 
    if($("#bpet_year1").val() > cyear){
		alert("Future Year cannot be allowed");
		$("#bpet_year1").focus();
		return false;
	} 
    if($("#bpet_year1").val() == cyear && fire_validate($("select#BPET_qe1").val())== 0){
		alert("Future Month is not allowed");
		$("#BPET_qe1").focus();
		 return false;
	} 
	var BPET_result=$('#BPET_result'+ps).val();
	var bept_result_others=$('#bept_result_others'+ps).val();
	
	var BPET_qe=$('#BPET_qe'+ps).val();
	var year=$('#bpet_year'+ps).val();
	var unit_sus_no=$('#unit_sus_no'+ps).val();
	var id=$('#id'+ps).val();
	var census_id=$('#census_id').val();
	var com_id=$('#comm_id').val();
	if (BPET_result == "-1") {
		alert("Please Enter BPET Result");
		$("input#BPET_result").focus();
		return false;
	}
	
	var BPET_resultV = $("#BPET_result"+ps+" option:selected").text();
	if (BPET_resultV == "OTHERS" && bept_result_others == "") {
		alert("Please Enter Other BPET Results");
		$('#bept_result_others'+ps).focus();
		return false;
	}
	
	
 	if (BPET_qe == "-1") {
		alert("Please Select BPET QTR");
		$("input#BPET_qe").focus();
		return false;
	}
 	if(year == ""){
		alert("Please Enter the Year");
		return false;
	}	 
    if($('#bpet_year'+ps).val() > cyear){
		alert("Future Year cannot be allowed");
		return false;
	}
    if($('#bpet_year'+ps).val() == cyear && fire_validate($('#BPET_qe'+ps).val())==0){
 		alert("Future Month is not allowed"); 		
 		 return false;
 	}  
    
    if($('#unit_name'+ps).val() == ""){
 		alert("Please Enter Conducted at Unit"); 		
 		 return false;
 	}  
	   $.post('BPET_action?' + key + "=" + value, {BPET_result : BPET_result ,bept_result_others:bept_result_others,BPET_qe : BPET_qe,
		   year:year,unit_sus_no:unit_sus_no,id : id,census_id :census_id,com_id:com_id }, function(data){
	          if(data=="update")
	        	  alert("Data Updated Successfully");
	          else if(parseInt(data)>0){
	        	 $('#id'+ps).val(data);
	        	  alert("Data Saved SuccesFully")
	        	  $("#bpet_add"+ps).show();
		        	 $("#bpet_remove"+ps).show();
	          }
	          else
	        	  alert(data);
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});
}
function bpet_Details() {
	var cen_id=$('#census_id').val(); 
	var comm_id=$('#comm_idV').val();
	var i=0;
	 $.post('getHisBpetDetailsData?' + key + "=" + value, {comm_id:comm_id,cen_id:cen_id,cancel_status:cancel_status}, function(j){
			var v=j.length;
			$('#bpettbody').empty(); 
			 if(v!=0){
	              
					for(i;i<v;i++){
			     		cor=i+1;
			     		
			     		var dauth=convertDateFormate(j[i].date_of_authority);
			     		var dmod=convertDateFormate(j[i].modified_date);
			     		var bpet_result =j[i].bpet_result;
			     		
			     		if(j[i].bpet_result=="OTHERS"){
			     			bpet_result=j[i].bept_result_others;
			     		}
						$("table#bpet_table").append('<tr id="tr_bpet'+cor+'">'
			                     +'<td class="anm_srno">'+cor+'</td>'
			                     +'<td style="width: 10%;">'+bpet_result+'</td>'
			                     +'<td style="width: 10%;">'+j[i].bpet_qe+'</td>'
			                     +'<td style="width: 10%;">'+j[i].year+'</td>'
			                     +'<td style="width: 10%;">'+fn_getUnitnamefromSusHis(j[i].unit_sus_no)+'</td>'
			                     +'<td style="width: 10%;">'+j[i].modified_by+'</td>'
			                     +'<td style="width: 10%;">'+dmod+'</td>'
			                     +'<td style="display:none;"><input type="text" id="flang_ch_id'+cor+'" name="flang_ch_id'+cor+'"  value="' + j[i].id + '" class="form-control autocomplete" autocomplete="off" ></td>'
			                     +'<td style="width: 10%;"><a  class="btn btn-danger btn-sm" value="REMOVE" title = "REJECT" '+j[i].action+'><i class="fa fa-times"></i></a>'
			                     +'</td></tr>');
			}
			 }	
	  });

}
bpet =1;
bpet_srno=1;
function bpet_add_fn(q){
	 if( $('#bpet_add'+q).length )        
	 {
			$("#bpet_add"+q).hide();
	 }
	 if(q!=0)
		 bpet_srno+=1;
	 bpet=q+1;
	$("table#bpet_table").append('<tr id="tr_bpet'+bpet+'">'
	+'<td class="bpet_srno">'+bpet_srno+'</td>'
	+'<td style="width: 10%;"> '
	+'<select name="BPET_result'+bpet+'" id="BPET_result'+bpet+'" class="form-control"  onchange="onBeptResult('+bpet+')" >'
	+'<option value="-1">--Select--</option>'
    +'<c:forEach var="item" items="${getBPET_result}" varStatus="num">'
    +'<option value="${item[0]}" name="${item[1]}">${item[1]}</option>'
    +'</c:forEach>'
	+'</select></td>'
  	+'<td style="width: 10%;"> <input type="text" id="bept_result_others'+bpet+'" name="bept_result_others'+bpet+'" onkeypress="return /[0-9a-zA-Z]/i.test(event.key)" readonly  class="form-control autocomplete" autocomplete="off" ></td>'

	+'<td style="width: 10%;"> '
	+'<select name="BPET_qe'+bpet+'" id="BPET_qe'+bpet+'" class="form-control"   >'
	+' <option value="-1">--Select--</option>'
    +' <c:forEach var="item" items="${getBPET_event_qe}" varStatus="num">'
    +' <option value="${item[0]}" name="${item[1]}">${item[1]}</option>'
    +'</c:forEach>'
	+'</select></td>'
	+'<td style="width: 10%;">	<input type="text" id="bpet_year'+bpet+'" name="year'+bpet+'" value="0" class="form-control autocomplete"  onchange="Checkyear(this)" autocomplete="off" maxlength="4" onkeypress="return isNumberPointKey(event);">'
	+'</td>'
	+'<td style="width: 20%;">'
	+'<input type="text" id="unit_name'+bpet+'"" name="unit_name'+bpet+'"" class="form-control autocomplete" onkeypress="BPET_UnitAuto(this);" autocomplete="off">'
	+'<input type="hidden" id="unit_sus_no'+bpet+'" name="unit_sus_no'+bpet+'" class="form-control autocomplete" autocomplete="off">'
	+'</td>'
	+'<td style="display:none;"><input type="text" id="id'+bpet+'" name="id'+bpet+'"   value="0" class="form-control autocomplete" autocomplete="off" ></td>'
	+'<td style="width: 10%;"><a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "bpet_save'+bpet+'" onclick="bpet_save_fn('+bpet+');" ><i class="fa fa-save"></i></a>'
	+'<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "bpet_add'+bpet+'" onclick="bpet_add_fn('+bpet+');" ><i class="fa fa-plus"></i></a>'
	+'<a style="display:none;" class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "bpet_remove'+bpet+'" onclick="bpet_remove_fn('+bpet+');"><i class="fa fa-trash"></i></a>' 
	+'</td></tr>');
}
//////////////////BPET remove function ///////
function bpet_remove_fn(R){
	var rc = confirm("Are You Sure! You Want To Delete");
	 if(rc){
	var id=$('#id'+R).val();
	  $.post('bpet_delete_action?' + key + "=" + value, {id:id }, function(data){ 
			   if(data=='1'){
				   $("tr#tr_bpet"+R).remove(); 
					 if(R==bpet){
						 R = R-1;
						 var temp=true;
						 for(i=R;i>=1;i--){
						 if( $('#bpet_add'+i).length )         
						 {
							 $("#bpet_add"+i).show();
							 temp=false;
							 bpet=i;
							 break;
						 }}
						 if(temp){
							 bpet_add_fn(0);
							}
			  			 }
					 $('.bpet_srno').each(function(i, obj) {
							obj.innerHTML=i+1;
							bpet_srno=i+1;
							 });
							 alert("Data Deleted SuceessFully");
			   }
				 else{
					 alert("Data not Deleted ");
				 }
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});
	 }
}
//***************** END BPET DETAILS************************//
</script>
<script>
//***************** START IDENTY CARD  DETAILS************************//

function idcard_other() {
	var text = $("#hair_colour option:selected").text();
	//alert("hello");
	if(text == "OTHERS"){
		$("#other_id").show();
	}
	else{
		$("#other_id").hide();
	}
}
function ideys_other() {
	var text = $("#eye_colour option:selected").text();
	if(text == "OTHERS"){
		$("#other_eye").show();
	}
	else{
		$("#other_eye").hide();
	}
}

function getIdentity_Card(){
	var cen_id=$('#census_id').val(); 
	var comm_id=$('#comm_idV').val();
	var i=0;
	 $.post('getHisIdentity_CardData?' + key + "=" + value, {comm_id:comm_id,cen_id:cen_id,cancel_status:cancel_status}, function(j){
		 var v=j.length;
		 $('#Identity_card_tbody').empty();
	     if(v!=0){
	              
			for(i;i<v;i++){
	     		cor=i+1;
	     		var dissu=convertDateFormate(j[i].issue_dt);
	     		var dmod=convertDateFormate(j[i].modified_date);
	     		var hair_cl_name =j[i].hair_cl_name;
	     		var eye_cl_name =j[i].eye_cl_name;
	     		if(j[i].hair_cl_name=="OTHERS"){
	     			hair_cl_name=j[i].hair_other;
	     		}
	     		if(j[i].eye_cl_name=="OTHERS"){
	     			eye_cl_name=j[i].eye_other;
	     		}
				$("table#Identity_card_table").append('<tr id="tr_Identity_card'+cor+'">'
	                     +'<td class="anm_srno">'+cor+'</td>'
	                     +'<td style="width: 10%;">'+j[i].id_card_no+'</td>'
	                     +'<td style="width: 10%;">'+dissu+'</td>'
	                     +'<td style="width: 10%;">'+fn_getUnitnamefromSusHis(j[i].issue_authority)+'</td>'
	                     +'<td style="width: 10%;">'+j[i].id_marks+'</td>'
	                     +'<td style="width: 10%;">'+hair_cl_name+'</td>'
	                     +'<td style="width: 10%;">'+eye_cl_name+'</td>'
	                     +'<td style="width: 10%;"><img alt="Officer Image"  src="censusIdentityConvertpath?i_id='+j[i].id+'" width="100" height="100" /></td>'
	                     +'<td style="width: 10%;">'+j[i].modified_by+'</td>'
	                     +'<td style="width: 10%;">'+dmod+'</td>'
	                     +'<td style="display:none;"><input type="text" id="Identity_card_ch_id'+cor+'" name="Identity_card_ch_id'+cor+'"  value="' + j[i].id + '" class="form-control autocomplete" autocomplete="off" ></td>'
	                     +'<td style="width: 10%;"><a  class="btn btn-danger btn-sm" value="REMOVE" title = "REJECT" '+j[i].action+'><i class="fa fa-times"></i></a>'
	                     +'</td></tr>');

			}
		}
	 });
}	


function validate_identity_card_save(){
	var comm_id=$('#comm_id').val();
	var census_id=$('#census_id').val();
	var status=$('#status').val();
	var card_id=$('#card_id').val();
	var comm_date=$('#comm_date').val();
 	if($("#id_card_no").val() == ""){
		alert("Please Enter ID Card No");
		$("#id_card_no").focus();
		return false;
	}
	
	if($("#issue_dt").val() == "DD/MM/YYYY" || $("#issue_dt").val() == ""){
		alert("Please Select Issue Date");
		$("#issue_dt").focus();
		return false;
	} 
	
	if($("#issue_authority").val() == ""){
		alert("Please Enter Issue Authority");
		$("#issue_authority").focus();
		return false;
	}
	
	if(getformatedDate($("input#comm_date").val()) > getformatedDate($("#issue_dt").val())) {
		alert("Issue Date should be Greater than Commission Date");
		return false;
  }
	
	if($("#id_marks").val() == ""){
		alert("Please Enter  Visible Identification Marks");
		$("#id_marks").focus();
		return false;
	}
	
	if($("#hair_colour").val() == 0){
		alert("Please Select Hair Colour");
		$("#hair_colour").focus();                 
		return false;
	}
	
	if($("#eye_colour").val() == 0){
		alert("Please Select Eye Colour");
		$("#eye_colour").focus();
		return false;
	} 
	
	if($("select#hair_colour").val() == 7 &&  $("#hair_other").val() == "" ){
		alert("Please Enter Others");
		$("#hair_other").focus();
		return false;
	} 
	if($("select#eye_colour").val() == 28 &&  $("#eye_other").val() == "" ){
		alert("Please Enter Others");
		$("#eye_other").focus();
		return false;
	}

	
	 var file = document.getElementById("identity_image"); 
	  var preDate = document.getElementById('image_updated_date').value;
	  // Below one is the single line logic to calculate the no. of years...
	  var years = new Date(new Date() - new Date(preDate)).getFullYear() - 1970;
	  
	  
	if(years>=8){
	  if(file.files.length == 0){
			  alert("Please Upload Current Photograph");			
				return false;        
	  }
	}
	  
	  
	  
	 var form_data = new FormData(document.getElementById("form_identity_card"));		
		form_data.append("comm_id",comm_id);
		form_data.append("census_id",census_id);
		form_data.append("comm_date",comm_date);
   
		   $.ajax({
		        url: 'Identity_CardAction?_csrf='+value,
		        type: "POST",
		        data: form_data,
		        enctype: 'multipart/form-data',
		        processData: false,
		        contentType: false
		      }).done(function(data) {
	          if(data=="update")
	        	  alert("Data Updated Successfully");
	          else if(parseInt(data)>0){
	        	 $('#card_id').val(data);
	        	  alert("Data Saved SuccesFully")
	          }
	          else
	        	  alert(data)
		      }).fail(function(jqXHR, textStatus) 
	 	  				{
	 	  					alert("fail to fetch")
	  		});
}
//***************** END IDENTY CARD  DETAILS************************//
</script>
<script>
//*************************************START ADDRESS DATA *****************************//

function spouse_addressFn(){
	if($("#check_spouse_address").is(':checked'))
	    $("#spouse_addressInnerdiv").show();  // checked
	else
	    $("#spouse_addressInnerdiv").hide();
}

function validate_changeaddress_details_save(){
	 if ($("input#addr_authority").val() == "") {
		alert("Please Enter The Authority");
		$("input#addr_authority").focus();
		return false;
	}
	 if ($("input#addr_date_authority").val() == "DD/MM/YYYY" || $("input#addr_date_authority").val()=="") {
		alert("Please Enter The Date of Authority");
		$("input#addr_date_authority").focus();
		return false;
	}  
	 if(getformatedDate($("input#comm_date").val()) > getformatedDate($("#addr_date_authority").val())) {
			alert("Authority Date should be Greater than Commission Date");
			return false;
	  }

	if ($("select#pre_country").val() == "0") {
		alert("Please Select Country");
		$("select#pre_country").focus();
		return false;
	}
   var text1 = $("#pre_country option:selected").text();
	
	if(text1 == "OTHERS" && $("input#pre_country_other").val() == ""){
		alert("Please Enter  Country Other");
		$("input#pre_country_other").focus();
		return false;
	}
	
	 if ($("select#pre_domicile_state").val() == "0") {
		alert("Please Select State");
		$("select#pre_domicile_state").focus();
		return false;
	} 
	 var text2 = $("#pre_domicile_state option:selected").text();
		
		if(text2 == "OTHERS" && $("input#pre_domicile_state_other").val() == ""){
			alert("Please Enter State Other");
			$("input#pre_domicile_state_other").focus();
			return false;
		}
	if ($("select#pre_domicile_district").val() == "0") {
		alert("Please Select District");
		$("select#pre_domicile_district").focus();
		return false;
	} 
	var text3 = $("#pre_domicile_district option:selected").text();
	
	if(text3 == "OTHERS" && $("input#pre_domicile_district_other").val() == ""){
		alert("Please Enter District Other");
		$("input#pre_domicile_district_other").focus();
		return false;
	}
	if ($("select#pre_domicile_tesil").val() == "0") {
		alert("Please Select Tehsil");
		$("select#pre_domicile_tesil").focus();
		return false;
	}
  var text4 = $("#pre_domicile_tesil option:selected").text();
	
	if(text4 == "OTHERS" && $("input#pre_domicile_tesil_other").val() == ""){
		alert("Please Enter Tehsil Other");
		$("input#pre_domicile_tesil_other").focus();
		return false;
	}
	
	if ($("select#per_home_addr_country").val() == "0") {
		alert("Please select The Country");
		$("select#per_home_addr_country").focus();
		return false;
	}
	 var text5 = $("#per_home_addr_country option:selected").text();
		
		if(text5 == "OTHERS" && $("input#per_home_addr_country_others").val() == ""){
			alert("Please Enter Country Other");
			$("input#per_home_addr_country_others").focus();
			return false;
		}
		
	if ($("select#per_home_addr_state").val() == "0") {
		alert("Please Select State");
		$("select#per_home_addr_state").focus();
		return false;
	}
	 var text6 = $("#per_home_addr_state option:selected").text();
		
		if(text6 == "OTHERS" && $("input#per_home_addr_state_others").val() == ""){
			alert("Please Enter State Other");
			$("input#per_home_addr_state_others").focus();
			return false;
		}
	if ($("select#per_home_addr_district").val() == "0") {
		alert("Please Select  District");
		$("select#per_home_addr_district").focus();
		return false;
	}
	 var text7 = $("#per_home_addr_district option:selected").text();
		
		if(text7 == "OTHERS" && $("input#per_home_addr_district_others").val() == ""){
			alert("Please Enter District Other");
			$("input#per_home_addr_district_others").focus();
			return false;
		}
	if ($("select#per_home_addr_tehsil").val() == "0") {
		alert("Please Enter  Tehsil");
		$("select#per_home_addr_tehsil").focus();
		return false;
	}
	var text8 = $("#per_home_addr_tehsil option:selected").text();
	
	if(text8 == "OTHERS" && $("input#per_home_addr_tehsil_others").val() == ""){
		alert("Please Enter Tehsil Other");
		$("input#per_home_addr_tehsil_others").focus();
		return false;
	}
	if ($("input#per_home_addr_village").val() == "") {
		alert("Please Enter  Village/Town/City");
		$("input#per_home_addr_village").focus();
		return false;
	}
	if ($("input#per_home_addr_pin").val() == "") {
		alert("Please Enter Pin");
		$("input#per_home_addr_pin").focus();
		return false;
	}
	if ($("input#per_home_addr_rail").val() == "") {
		alert("Please Enter Nearest Railway Station");
		$("input#per_home_addr_rail").focus();
		return false;
	}
	 var a = $('input:radio[name=per_home_addr_rural_urban]:checked').val();
		if(a == undefined){	
			alert("Please select The  Rural /Urban/Semi Urban ");
			return false;
			}
		var b = $('input:radio[name=border_area]:checked').val();
		if(b == undefined){	
			alert("Please select The Border area ");
			return false;
			}
		if ($("select#pers_addr_country").val() == "0") {
			alert("Please Select Country");
			$("select#pers_addr_country").focus();
			return false;
		}
		var text9 = $("#pers_addr_country option:selected").text();
		
		if(text9 == "OTHERS" && $("input#pers_addr_country_other").val() == ""){
			alert("Please Enter Country Other");
			$("input#pers_addr_country_other").focus();
			return false;
		}
		if ($("select#pers_addr_state").val() == "0") {
			alert("Please select State");
			$("select#pers_addr_state").focus();
			return false;
		}
  var text10 = $("#pers_addr_state option:selected").text();
		
		if(text10 == "OTHERS" && $("input#pers_addr_state_other").val() == ""){
			alert("Please Enter State Other");
			$("input#pers_addr_state_other").focus();
			return false;
		}
		if ($("select#pers_addr_district").val() == "0") {
			alert("Please select District");
			$("select#pers_addr_district").focus();
			return false;
		}
var text11 = $("#pers_addr_district option:selected").text();
		
		if(text11 == "OTHERS" && $("input#pers_addr_district_other").val() == ""){
			alert("Please Enter District Other");
			$("input#pers_addr_district_other").focus();
			return false;
		}
		if ($("select#pers_addr_tehsil").val() == "0") {
			alert("Please Enter Tehsil");
			$("select#pers_addr_tehsil").focus();
			return false;
		}
var text11 = $("#pers_addr_tehsil option:selected").text();
		
		if(text11 == "OTHERS" && $("input#pers_addr_tehsil_other").val() == ""){
			alert("Please Enter Tehsil Other");
			$("input#pers_addr_tehsil_other").focus();
			return false;
		}
	if ($("input#pers_addr_village").val() == "") {
		alert("Please Enter Village/Town/City");
		$("input#pers_addr_village").focus();
		return false;
	}
	if ($("input#pers_addr_pin").val() =="") {
		alert("Please Enter Pin");
		$("input#pers_addr_pin").focus();
		return false;
	}
	if ($("input#pers_addr_rail").val() == "") {
		alert("Please Enter Nearest Railway Station");
		$("input#pers_addr_rail").focus();
		return false;
	}
	var c = $('input:radio[name=pers_addr_rural_urban]:checked').val();
	if(c == undefined){	
		alert("Please select The  Rural /Urban/Semi Urban ");
		return false;
		}
	
	if($("#check_spouse_address").is(':checked')){
		
		if ($("select#spouse_addr_Country").val() == "0") {
			alert("Please select Present Country");
			$("select#spouse_addr_Country").focus();
			return false;
		}	
var text12 = $("#spouse_addr_Country option:selected").text();
		
		if(text12 == "OTHERS" && $("input#spouse_addr_country_other").val() == ""){
			alert("Please Enter Country Other");
			$("input#spouse_addr_country_other").focus();
			return false;
		}
		if ($("select#spouse_addr_state").val() == "0") {
			alert("Please select Present State");
			$("select#spouse_addr_state").focus();
			return false;
		}
var text13 = $("#spouse_addr_state option:selected").text();
		
		if(text13 == "OTHERS" && $("input#spouse_addr_state_other").val() == ""){
			alert("Please Enter State Other");
			$("input#spouse_addr_state_other").focus();
			return false;
		}
		if ($("select#spouse_addr_district").val() == "0") {
			alert("Please select Present District");
			$("select#spouse_addr_district").focus();
			return false;
		}
var text14 = $("#spouse_addr_district option:selected").text();
		
		if(text14 == "OTHERS" && $("input#spouse_addr_district_other").val() == ""){
			alert("Please Enter District Other");
			$("input#spouse_addr_district_other").focus();
			return false;
		}
		if ($("select#spouse_addr_tehsil").val() == "0") {
			alert("Please Enter Present Tehsil");
			$("select#spouse_addr_tehsil").focus();
			return false;
		}
var text15 = $("#spouse_addr_tehsil option:selected").text();
		
		if(text15 == "OTHERS" && $("input#spouse_addr_tehsil_other").val() == ""){
			alert("Please Enter Tehsil Other");
			$("input#spouse_addr_tehsil").focus();
			return false;
		}
		if ($("input#spouse_addr_village").val() == "") {
			alert("Please Enter Present Village/Town/City");
			$("input#spouse_addr_village").focus();
			return false;
		}	
		if ($("input#spouse_addr_pin").val() =="" || $("input#spouse_addr_pin").val().length<6) {
			alert("Please Enter Present valid Pin");
			$("input#spouse_addr_pin").focus();
			return false;
		}	
		if ($("input#spouse_addr_rail").val() == "") {
			alert("Please Enter Present Nearest Railway Station");
			$("input#spouse_addr_rail").focus();
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
	if ($("input#retire_pin").val() == "") {
		alert("Please Enter Pin");
		$("input#retire_pin").focus();
		return false;
	}
	if ($("input#retire_rail").val() == "") {
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
	        	  alert("Data Saved SuccesFully")
	          }
	          else
	        	  alert(data)
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});
}

function get_changeinaddress_details(){
	var cen_id=$('#census_id').val(); 
	var comm_id=$('#comm_idV').val();
	var i=0;
	 $.post('getHisAddressData?' + key + "=" + value, {comm_id:comm_id,cen_id:cen_id,cancel_status:cancel_status}, function(j){
		 var v=j.length;
		 $('#ChangeInAddress_tbody').empty();
		 $('#ChangeInPerAddress_tbody').empty();
		 $('#ChangeInPreAddress_tbody').empty();
		 $('#ChangeInSpouseAddress_tbody').empty();
	     if(v!=0){
	              
			for(i;i<v;i++){
	     		cor=i+1;
	     		var doa=convertDateFormate(j[i].date_authority);
	     		var dmod=convertDateFormate(j[i].modified_date);
	     		var pre_country =j[i].pre_country;
	     		var pre_state =j[i].pre_state;
	     		var pre_district =j[i].pre_district;
	     		var pre_tehsil =j[i].pre_tehsil;
	     		
	     		if(j[i].pre_country=="OTHERS"){
	     			pre_country=j[i].pre_country_other;
	     		}
	     		if(j[i].pre_state=="OTHERS"){
	     			pre_state=j[i].pre_domicile_state_other;
	     		}
	     		if(j[i].pre_district=="OTHERS"){
	     			pre_district=j[i].pre_domicile_district_other;
	     		}
	     		if(j[i].pre_tehsil=="OTHERS"){
	     			pre_tehsil=j[i].pre_domicile_tesil_other;
	     		}
				$("table#ChangeInAddress_table").append('<tr id="tr_ChangeInAddress'+cor+'">'
	                     +'<td class="anm_srno">'+cor+'</td>'
	                     +'<td style="width: 10%;">'+j[i].authority+'</td>'
	                     +'<td style="width: 10%;">'+doa+'</td>'
	                     +'<td style="width: 10%;">'+pre_country+'</td>'
	                     +'<td style="width: 10%;">'+pre_state+'</td>'
	                     +'<td style="width: 10%;">'+pre_district+'</td>'
	                     +'<td style="width: 10%;">'+pre_tehsil+'</td>'
	                     +'<td style="width: 10%;">'+j[i].modified_by+'</td>'
	                     +'<td style="width: 10%;">'+dmod+'</td>'
	                     +'<td style="display:none;"><input type="text" id="NOK_address_details_ch_id'+cor+'" name="NOK_address_details_ch_id'+cor+'"  value="' + j[i].id + '" class="form-control autocomplete" autocomplete="off" ></td>'
	                     +'<td style="width: 10%;"><a  class="btn btn-danger btn-sm" value="REMOVE" title = "REJECT" '+j[i].action+'><i class="fa fa-times"></i></a>'
	                     +'</td></tr>');
				
				var home_country =j[i].home_country;
	     		var home_state =j[i].home_state;
	     		var home_district =j[i].home_district;
	     		var home_tehsil =j[i].home_tehsil;
	     		
	     		if(j[i].home_country=="OTHERS"){
	     			home_country=j[i].per_home_addr_country_other;
	     		}
	     		if(j[i].home_state=="OTHERS"){
	     			home_state=j[i].per_home_addr_state_other;
	     		}
	     		if(j[i].home_district=="OTHERS"){
	     			home_district=j[i].per_home_addr_district_other;
	     		}
	     		if(j[i].home_tehsil=="OTHERS"){
	     			home_tehsil=j[i].per_home_addr_tehsil_other;
	     		}
				$("table#ChangeInPerAddress_table").append('<tr id="tr_ChangeInPerAddress'+cor+'">'
	                     +'<td class="anm_srno">'+cor+'</td>'
	                     +'<td style="width: 10%;">'+j[i].authority+'</td>'
	                     +'<td style="width: 10%;">'+doa+'</td>'
	                     +'<td style="width: 10%;">'+home_country+'</td>'
	                     +'<td style="width: 10%;">'+home_state+'</td>'
	                     +'<td style="width: 10%;">'+home_district+'</td>'
	                     +'<td style="width: 10%;">'+home_tehsil+'</td>'
	                     +'<td style="width: 10%;">'+j[i].permanent_village+'</td>'
	                     +'<td style="width: 10%;">'+j[i].permanent_pin_code+'</td>'
	                     +'<td style="width: 10%;">'+j[i].permanent_near_railway_station+'</td>'
	                     +'<td style="width: 10%;">'+j[i].permanent_rural_urban_semi+'</td>'
	                     +'<td style="width: 10%;">'+j[i].permanent_border_area+'</td>'
	                     +'<td style="width: 10%;">'+j[i].modified_by+'</td>'
	                     +'<td style="width: 10%;">'+dmod+'</td>'
	                     +'<td style="display:none;"><input type="text" id="NOK_address_details_ch_id'+cor+'" name="NOK_address_details_ch_id'+cor+'"  value="' + j[i].id + '" class="form-control autocomplete" autocomplete="off" ></td>'
	                     +'<td style="width: 10%;"><a  class="btn btn-danger btn-sm" value="REMOVE" title = "REJECT" '+j[i].action+'><i class="fa fa-times"></i></a>'
	                     +'</td></tr>');
				
				
				var present_country =j[i].present_country;
	     		var present_state =j[i].present_state;
	     		var present_district =j[i].present_district;
	     		var present_tehsil =j[i].present_tehsil;
	     		
	     		if(j[i].present_country=="OTHERS"){
	     			present_country=j[i].pers_addr_country_other;
	     		}
	     		if(j[i].present_state=="OTHERS"){
	     			present_state=j[i].pers_addr_state_other;
	     		}
	     		if(j[i].present_district=="OTHERS"){
	     			present_district=j[i].pers_addr_district_other;
	     		}
	     		if(j[i].present_tehsil=="OTHERS"){
	     			present_tehsil=j[i].pers_addr_tehsil_other;
	     		}
				$("table#ChangeInPreAddress_table").append('<tr id="tr_ChangeInPreAddress'+cor+'">'
	                     +'<td class="anm_srno">'+cor+'</td>'
	                     +'<td style="width: 10%;">'+j[i].authority+'</td>'
	                     +'<td style="width: 10%;">'+doa+'</td>'
	                     +'<td style="width: 10%;">'+present_country+'</td>'
	                     +'<td style="width: 10%;">'+present_state+'</td>'
	                     +'<td style="width: 10%;">'+present_district+'</td>'
	                     +'<td style="width: 10%;">'+present_tehsil+'</td>'
	                     +'<td style="width: 10%;">'+j[i].present_village+'</td>'
	                     +'<td style="width: 10%;">'+j[i].present_pin_code+'</td>'
	                     +'<td style="width: 10%;">'+j[i].present_near_railway_station+'</td>'
	                     +'<td style="width: 10%;">'+j[i].present_rural_urban_semi+'</td>'
	                     +'<td style="width: 10%;">'+j[i].modified_by+'</td>'
	                     +'<td style="width: 10%;">'+dmod+'</td>'
	                     +'<td style="display:none;"><input type="text" id="NOK_address_details_ch_id'+cor+'" name="NOK_address_details_ch_id'+cor+'"  value="' + j[i].id + '" class="form-control autocomplete" autocomplete="off" ></td>'
	                     +'<td style="width: 10%;"><a  class="btn btn-danger btn-sm" value="REMOVE" title = "REJECT" '+j[i].action+'><i class="fa fa-times"></i></a>'
	                     +'</td></tr>');
				
				if(j[i].spouse_country!=''){
					var spouse_country =j[i].spouse_country;
		     		var spouse_state =j[i].present_state;
		     		var spouse_district =j[i].present_district;
		     		var spouse_tehsil =j[i].present_tehsil;
		     		
		     		if(j[i].spouse_country=="OTHERS"){
		     			spouse_country=j[i].spouse_addr_country_other;
		     		}
		     		if(j[i].spouse_state=="OTHERS"){
		     			spouse_state=j[i].spouse_addr_state_other;
		     		}
		     		if(j[i].spouse_district=="OTHERS"){
		     			spouse_district=j[i].spouse_addr_district_other;
		     		}
		     		if(j[i].spouse_tehsil=="OTHERS"){
		     			spouse_tehsil=j[i].spouse_addr_tehsil_other;
		     		}
		     		var sus_no = j[i].stn_hq_sus_no;
		     		var Stn_HQ_unit_name ;
					 $.post("getTargetUnitNameListStnHQ?"+key+"="+value, {
							 sus_no:sus_no
			         }, function(j) {
			                
			         }).done(function(j) {
			        	 var length = j.length-1;
			             var enc = j[length].substring(0,16);
			             Stn_HQ_unit_name=dec(enc,j[0]);   
			                 
			         }).fail(function(xhr, textStatus, errorThrown) {
		        	 });
					$("table#ChangeInSpouseAddress_table").append('<tr id="tr_ChangeInPreAddress'+cor+'">'
		                     +'<td class="anm_srno">'+cor+'</td>'
		                     +'<td style="width: 10%;">'+j[i].authority+'</td>'
		                     +'<td style="width: 10%;">'+doa+'</td>'
		                     +'<td style="width: 10%;">'+spouse_country+'</td>'
		                     +'<td style="width: 10%;">'+spouse_state+'</td>'
		                     +'<td style="width: 10%;">'+spouse_district+'</td>'
		                     +'<td style="width: 10%;">'+spouse_tehsil+'</td>'
		                     +'<td style="width: 10%;">'+j[i].spouse_village+'</td>'
		                     +'<td style="width: 10%;">'+j[i].spouse_pin_code+'</td>'
		                     +'<td style="width: 10%;">'+j[i].spouse_near_railway_station+'</td>'
		                     +'<td style="width: 10%;">'+j[i].spouse_rural_urban_semi+'</td>'
		                     +'<td style="width: 10%;">'+j[i].stn_hq_sus_no+'</td>'
		                     +'<td style="width: 10%;">'+Stn_HQ_unit_name+'</td>'
		                     +'<td style="width: 10%;">'+j[i].modified_by+'</td>'
		                     +'<td style="width: 10%;">'+dmod+'</td>'
		                     +'<td style="display:none;"><input type="text" id="NOK_address_details_ch_id'+cor+'" name="NOK_address_details_ch_id'+cor+'"  value="' + j[i].id + '" class="form-control autocomplete" autocomplete="off" ></td>'
		                     +'<td style="width: 10%;"><a  class="btn btn-danger btn-sm" value="REMOVE" title = "REJECT" '+j[i].action+'><i class="fa fa-times"></i></a>'
		                     +'</td></tr>');
					$("#spouseAddressDiv").show();
					}
				

			}
		}
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
            
             var ele = document.getElementsByName('per_home_addr_rural_urban'); 
     for(i = 0; i < ele.length; i++) { 
         if(ele[i].checked) 
                {
                var value = ele[i].value;
            $("input[name=pers_addr_rural_urban][value=" + value + "]").attr('checked', 'checked');
                }
     } 
    }
    else{
            $("#pers_addr_village").val("");
            $("#pers_addr_tehsil").val("");
            $("#pers_addr_district").val("");
            $("#pers_addr_state").val("");
            $("#pers_addr_district_other").val("");
            $("#pers_addr_country_other").val("");
            $("#pers_addr_state_other").val("");
            $("#pers_addr_tehsil_other").val("");
            $("#pers_addr_pin").val("");
            $("#pers_addr_rail").val("");
            $("#pers_addr_country").val("");
            $('input[type="radio"]').prop('checked', false); 
            //$("input[name=pers_addr_rural_urban]").attr('checked', false);
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
		}
}
function fn_spouse_addr_Country() {
	var text = $("#spouse_addr_Country option:selected").text();
	
	if(text == "OTHERS"){
		$("#spouse_addr_Country_hid").show();
	}
	else{
		$("#spouse_addr_Country_hid").hide();
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
		}
}
//*************************************End ADDRESS DATA *****************************//
</script>
<script>
//*************************************Start Nok *****************************//

function fn_nok_Country() {
	
	var text = $("#nok_country option:selected").text();
	//alert("hello");
	if(text == "OTHERS"){
		$("#nok_other_id").show();
	}
	else{
		$("#nok_other_id").hide();
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
	}
}	

function validate_save_nok_details(){
 	if ($("input#authority").val() == "") {
		alert("Please Enter Authority");
		$("input#authority").focus();
		return false;
	}
 	if ($("input#date_authority").val() == "DD/MM/YYYY") {
		alert("Please Enter Date of Authority");
		$("input#date_authority").focus();
		return false;
	}  
	
	if(getformatedDate($("input#comm_date").val()) >= getformatedDate($("#date_authority").val())) {
		alert("Authority Date should be Greater than Commission Date");
		return false;
  }
	if ($("input#nok_name").val() == "") {
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
	
	if(text1 == "OTHERS" && $("input#ctry_other").val() == ""){
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
	
	if(text2 == "OTHERS" && $("input#st_other").val() == ""){
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
		
		if(text3 == "OTHERS" && $("input#dist_other").val() == ""){
			alert("Please Enter District Other");
			$("input#dist_other").focus();
			return false;
		}
	if ($("select#nok_tehsil").val() == "0" ) {
		alert("Please Enter Tehsil");
		$("select#nok_tehsil").focus();
		return false;
	}
	var text5 = $("#nok_tehsil option:selected").text();
	
	if(text5 == "OTHERS" && $("input#nok_tehsil_other").val() == ""){
		alert("Please Enter Tehsil Other");
		$("input#nok_tehsil_other").focus();
		return false;
	}
	if ($("input#nok_village").val() == "") {
		alert("Please Enter  Village/Town/City");
		$("input#nok_village").focus();
		return false;
	}
	if ($("input#nok_pin").val() == "") {
		alert("Please Enter  Nok Pin");
		$("input#nok_pin").focus();
		return false;
	}
	if ($("input#nok_rail").val() == "") {
		alert("Please Enter  Nearest Railway Station");
		$("input#nok_rail").focus();
		return false;
	}
	var n = $('input:radio[name=nok_rural_urban]:checked').val();
	if(n == undefined){	
		alert("Please select Is  Rural /Urban/Semi Urban");
		return false;
		}
	if ($("input#nok_mobile_no").val() == "") {
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
	        	  alert("Data Saved SuccesFully")
	          }
	          else
	        	  alert(data)
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});
}
function get_nokaddress_details(){
	var cen_id=$('#census_id').val(); 
	var comm_id=$('#comm_idV').val();
	var i=0;
	 $.post('getHisNokAddressData?' + key + "=" + value, {comm_id:comm_id,cen_id:cen_id,cancel_status:cancel_status}, function(j){
		 var v=j.length;
		 $('#NOK_address_detailstbody').empty();
	     if(v!=0){
	              
			for(i;i<v;i++){
	     		cor=i+1;
	     		var doa=convertDateFormate(j[i].date_authority);
	     		var dmod=convertDateFormate(j[i].modified_date);
	     		var relation_name =j[i].relation_name;
	     		var country =j[i].country;
	     		var state_name =j[i].state_name;
	     		var district_name =j[i].district_name;
	     		var city_name =j[i].city_name;
	     		
	     		if(j[i].relation_name=="OTHERS"){
	     			relation_name=j[i].relation_other;
	     		}
	     		if(j[i].country=="OTHERS"){
	     			country=j[i].ctry_other;
	     		}
	     		if(j[i].state_name=="OTHERS"){
	     			state_name=j[i].st_other;
	     		}
	     		if(j[i].district_name=="OTHERS"){
	     			district_name=j[i].dist_other;
	     		}
	     		if(j[i].city_name=="OTHERS"){
	     			city_name=j[i].tehsil_other;
	     		}
				$("table#NOK_address_details_table").append('<tr id="tr_NOK_address_details'+cor+'">'
	                     +'<td class="anm_srno">'+cor+'</td>'
	                     +'<td style="width: 10%;">'+j[i].authority+'</td>'
	                     +'<td style="width: 10%;">'+doa+'</td>'
	                     +'<td style="width: 10%;">'+j[i].nok_name+'</td>'
	                     +'<td style="width: 10%;">'+relation_name+'</td>'
	                     +'<td style="width: 10%;">'+country+'</td>'
	                     +'<td style="width: 10%;">'+state_name+'</td>'
	                     +'<td style="width: 10%;">'+district_name+'</td>'
	                     +'<td style="width: 10%;">'+city_name+'</td>'
	                     +'<td style="width: 10%;">'+j[i].nok_village+'</td>'
	                     +'<td style="width: 10%;">'+j[i].nok_pin+'</td>'
	                     +'<td style="width: 10%;">'+j[i].nok_near_railway_station+'</td>'
	                     +'<td style="width: 10%;">'+j[i].nok_rural_urban_semi+'</td>'
	                     +'<td style="width: 10%;">'+j[i].nok_mobile_no+'</td>'
	                     +'<td style="width: 10%;">'+j[i].modified_by+'</td>'
	                     +'<td style="width: 10%;">'+dmod+'</td>'
	                     +'<td style="display:none;"><input type="text" id="NOK_address_details_ch_id'+cor+'" name="NOK_address_details_ch_id'+cor+'"  value="' + j[i].id + '" class="form-control autocomplete" autocomplete="off" ></td>'
	                     +'<td style="width: 10%;"><a  class="btn btn-danger btn-sm" value="REMOVE" title = "REJECT" '+j[i].action+'><i class="fa fa-times"></i></a>'
	                     +'</td></tr>');

			}
		}
	 });
	} 
///state
// 	   $('select#nok_state').change(function() {
//            var state_id = this.value;
//                                     $.post("getDistrictListFormState1?"+key+"="+value, {state_id:state_id}).done(function(j) {
//                                             var options = '<option   value="0">'+ "--Select--" + '</option>';
//                                            for ( var i = 0; i < j.length; i++) {
//                                                            options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
//                                            }
//                                            $("select#nok_district").html(options);
//                                    }).fail(function(xhr, textStatus, errorThrown) {
//                    });
//   });
//   ///country
// 	   $('select#nok_Country').change(function() {
//            var contry_id = this.value;
//                                     $.post("getStateListFormcon1?"+key+"="+value, {contry_id:contry_id}).done(function(j) {
//                                             var options = '<option   value="0">'+ "--Select--" + '</option>';
//                                            for ( var i = 0; i < j.length; i++) {
//                                                            options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
//                                            }
//                                            $("select#nok_state").html(options);
//                                    }).fail(function(xhr, textStatus, errorThrown) {
//                    });
//   });
//   ///district
// 	   $('select#nok_district').change(function() {
// 	         var Dist_id = this.value;
// 	                                  $.post("getTehsilListFormDistrict1?"+key+"="+value, {Dist_id:Dist_id}).done(function(j) {
// 	                                          var options = '<option   value="0">'+ "--Select--" + '</option>';
// 	                                         for ( var i = 0; i < j.length; i++) {
// 	                                                         options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
// 	                                         }
// 	                                         $("select#nok_tehsil").html(options);
// 	                                 }).fail(function(xhr, textStatus, errorThrown) {
// 	                 });
// 	});
  
	   
  //tehsil
	 /*   $('select#nok_tehsil').change(function() {
	         var teh_id = this.value;
	                                  $.post("getvillageListFormteh1?"+key+"="+value, {teh_id:teh_id}).done(function(j) {
	                                          var options = '<option   value="0">'+ "--Select--" + '</option>';
	                                         for ( var i = 0; i < j.length; i++) {
	                                                         options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
	                                         }
	                                         $("select#nok_village").html(options);
	                                 }).fail(function(xhr, textStatus, errorThrown) {
	                 });
	}); */
	 //*************************************END Nok *****************************//
</script>
<script>
//************************************* START BTLE & PHY *****************************//
function validate_batnpc_save(){
		if ($("#batnpc_authority").val() == "") {
			alert("Please Enter Authority");
			$("input#batnpc_authority").focus();
			return false;
		}
	 	if ($("#batnpc_date_of_authority").val() == "" || $("#batnpc_date_of_authority").val() == "DD/MM/YYYY" ) {
			alert("Please Select Date of Authority");
			$("input#batnpc_date_of_authority").focus();
			return false;
		} 
	 	
	 	if(getformatedDate($("input#comm_date").val()) > getformatedDate($("#batnpc_date_of_authority").val())) {
			alert("Authority Date should be Greater than Commission Date");
			return false;
	  }

	 	
		if ($('input[name="classification_of_casuality"]:checked').length == 0) {
			alert("Please Select Classification of Casualty");
			$("input#classification_of_casuality").focus();
			return false;
		}
		if ($('input[name="nature_of_casuality"]:checked').length == 0) {
			alert("Please Select Nature of Casualty");
			$("input#nature_of_casuality").focus();
			return false;
		}
		if ($("#name_of_operation").val() == "" && $('input[name="classification_of_casuality"]:checked').val()!="physical_casuality") {
			alert("Please Enter Name of Operation");
			$("input#name_of_operation").focus();
			return false;
		}
	 	if ($("#date_of_casuality").val() == ""  || $("#date_of_casuality").val() == "DD/MM/YYYY" ) {
			alert("Please Select Date of Casualty");
			$("input#date_of_casuality").focus();
			return false;
		} 
	 	
	 	/* if(getformatedDate($("input#date_of_casuality").val()) > getformatedDate($("#batnpc_date_of_authority").val())) {
			alert("Date of Casuality should be Greater than Commission Date");
			return false;
	  } */
// 		if ($("#cause_of_casuality").val() == "0") {
// 			alert("Please Enter Command");
// 			$("select#cause_of_casuality").focus();
// 			return false;
// 		}
		if ($("#batnpc_diagnosis").val() == "" && $('input[name="classification_of_casuality"]:checked').val()!="physical_casuality") {
			alert("Please Enter Diagnosis");
			$("input#batnpc_diagnosis").focus();
			return false;
		}
		if ($("#batnpc_exact_place").val() == "") {
			alert("Please Enter Place/Area/Post");
			$("input#batnpc_exact_place").focus();
			return false;
		}
		if ($('input[name="whether_on"]:checked').length == 0) {
			alert("Please Select Whether On");
			$("input#whether_on").focus();
			return false;
		}
		
		if ($("#batnpc_command").val() == "0") {
			alert("Please Select Command");
			$("select#batnpc_command").focus();
			return false;
		}
		if ($("#batnpc_corps_area").val() == "0") {
			alert("Please Select Corps/Area");
			$("select#batnpc_corps_area").focus();
			return false;
		}
		if ($("#batnpc_div_subarea").val() == "0") {
			alert("Please Select DIV/Sub Area");
			$("select#batnpc_div_subarea").focus();
			return false;
		}
		if ($("#batnpc_bde").val() == "0") {
			alert("Please Select BDE");
			$("select#batnpc_bde").focus();
			return false;
		}
		if ($("#batnpc_unit").val() == "") {
			alert("Please Enter Unit");
			$("input#batnpc_unit").focus();
			return false;
		}
		if ($("#batnpc_country").val() == "0") {
			alert("Please Select Country");
			$("select#batnpc_country").focus();
			return false;
		}
		var country_other_sel = $("#batnpc_country option:selected").text();
		if(country_other_sel == "OTHERS"){
			if ($("#country_other").val() == "") {
				alert("Please Enter Type of Country Other");
				$("#country_other").focus();
				return false;
		 }
		}
		if ($("#batnpc_state").val() == "0") {
			alert("Please Select State/UT");
			$("select#batnpc_state").focus();
			return false;
		}
		var state_other_sel = $("#batnpc_state option:selected").text();
		if(state_other_sel == "OTHERS"){
			if ($("#state_other").val() == "") {
				alert("Please Enter State Other");
				$("#state_other").focus();
				return false;
		 }
		}
		if ($("#batnpc_district").val() == "0") {
			alert("Please Select District");
			$("select#batnpc_district").focus();
			return false;
		}
		var district_other_sel = $("#batnpc_district option:selected").text();
		if(district_other_sel == "OTHERS"){
			if ($("#district_other").val() == "") {
				alert("Please Enter District Other");
				$("#district_other").focus();
				return false;
		 }
		}
		if ($("#batnpc_tehsil").val() == "0") {
			alert("Please Select Tehsil");
			$("select#batnpc_tehsil").focus();
			return false;
		}
		var tehsil_other_sel = $("#batnpc_tehsil option:selected").text();
		if(tehsil_other_sel == "OTHERS"){
			if ($("#tehsil_other").val() == "") {
				alert("Please Enter Tehsil Other");
				$("#tehsil_other").focus();
				return false;
		 }
		}
		if ($("#batnpc_village").val() == "0") {
			alert("Please Select Village/Town/City");
			$("select#batnpc_village").focus();
			return false;
		}
		if ($("#batnpc_pin").val() == "") {
			alert("Please Enter Pin");
			$("input#batnpc_pin").focus();
			return false;
		}
	//validation Ends
	  var formvalues=$("#form_battle_physical_casuality").serialize();
		var census_id=$("#census_id").val();	
		var comm_id=$('#comm_id').val();	
		var comm_date=$('#comm_date').val();
		formvalues+="&census_id="+census_id+"&comm_id="+comm_id+"&comm_date="+comm_date;	
	   $.post('battle_physical_casualityAction?' + key + "=" + value,formvalues, function(data){
		   if(data=="update"){
			   alert("Data Updated Successfully");
		   }
		   else if(parseInt(data)>0){
	        	 $('#batnpc_ch_id').val(data);	        	 
	        	  alert("Data Saved SuccesFully")
	          }
	          else
	        	  alert(data);
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});
}

function get_Battle_and_Physical_Casuality_details(){
	var cen_id=$('#census_id').val(); 
	var comm_id=$('#comm_idV').val();
	var i=0;
	 $.post('getHisBattle_physical_casualityData?' + key + "=" + value, {comm_id:comm_id,cen_id:cen_id,cancel_status:cancel_status}, function(j){
		 var v=j.length;
		 $('#battle_cause_tbody').empty(); 
	     if(v!=0){
	             
			for(i;i<v;i++){
	     		cor=i+1;
	     		var desc='';
	     		if(j[i].desc_others!=null && j[i].desc_others!=''){
	     			desc=j[i].desc_others;
	     		
	     		}
	     		else if(j[i].description!=null){
	     			desc=j[i].description;
	     		}
	     		else {
	     			desc=j[i].desc_value;
	     		}
	     		var country=j[i].country;
	     		var state=j[i].state;
	     		
	     		var district=j[i].district;
	     		var tehsil=j[i].tehsil
	     		
	     		if(j[i].country_other!=null && j[i].country_other!='')
	     			country=j[i].country_other;
	     		
	     		if(j[i].state_other!=null && j[i].state_other!='')
	     			state=j[i].state_other;
	     		
	     		if(j[i].district_other!=null && j[i].district_other!='')
	     			district=j[i].district_other;
	     		
	     		if(j[i].tehsil_other!=null && j[i].tehsil_other!='')
	     			tehsil=j[i].tehsil_other;
	     		
	     		var dauth=convertDateFormate(j[i].date_of_authority);
	     		var dcause=convertDateFormate(j[i].date_of_casuality);
	     		var dmod=convertDateFormate(j[i].modify_on);
				$("table#battle_cause_table").append('<tr id="tr_battle_cause'+cor+'">'
	                     +'<td class="anm_srno">'+cor+'</td>'
	                     +'<td style="font-size: 10px;">'+j[i].authority+'</td>'
	                     +'<td style="font-size: 10px;">'+dauth+'</td>'
	                     +'<td style="font-size: 10px;">'+dcause+'</td>'
// 	                     +'<td style="font-size: 10px;">'+j[i].cause_of_casuality+'</td>'
	                     +'<td style="font-size: 10px;">'+j[i].classification_of_casuality+'</td>'
	                     +'<td style="font-size: 10px;">'+j[i].nature_of_casuality+'</td>'
	                     +'<td style="font-size: 10px;">'+j[i].name_of_operation+'</td>'
	                     +'<td style="font-size: 10px;">'+j[i].casuality+'</td>'
	                     
	                     
// 	                     +'<td style="width: 10%;">'+desc+'</td>'
	                     +'<td style="font-size: 10px">'+j[i].unit+'</td>'
	                     +'<td style="font-size: 10px;">'+j[i].diagnosis+'</td>'
// 	                     +'<td style="width: 10%;">'+j[i].exact_place+'</td>'
	                     
// 	                     +'<td style="width: 10%;">'+j[i].whether_on+'</td>'
// 	                     +'<td style="width: 10%;">'+j[i].unit+'</td>'
// 	                     +'<td style="width: 10%;">'+j[i].bde_name+'</td>'
// 	                     +'<td style="width: 10%;">'+j[i].div_name+'</td>'
	                     
	                     
// 	                     +'<td style="width: 10%;">'+j[i].coprs_name+'</td>'
// 	                     +'<td style="width: 10%;">'+j[i].cmd_name+'</td>'
// 	                     +'<td style="width: 10%;">'+country+'</td>'
// 	                     +'<td style="width: 10%;">'+state+'</td>'
	                     
// 	                     +'<td style="width: 10%;">'+district+'</td>'
// 	                     +'<td style="width: 10%;">'+tehsil+'</td>'
// 	                     +'<td style="width: 10%;">'+j[i].village+'</td>'
// 	                     +'<td style="width: 10%;">'+j[i].pin+'</td>'
	                     +'<td style="font-size: 10px;">'+j[i].modify_by+'</td>'
	                     +'<td style="font-size: 10px;">'+dmod+'</td>'                    
	                     +'<td style="width: 10%;"><a  class="btn btn-danger btn-sm" value="REMOVE" title = "REJECT"  '+j[i].action+'><i class="fa fa-times"></i></a>'
	                     +'</td></tr>');

			}
		}
	 });
	}						
//************************************* END BTLE & PHY *****************************//
</script>


<script>
var classification='1';
$("#mad_classification").change(function(){
	
	classification=this.value;
	setDiagnosis();
	
});

function onShapeValueChange(e,label){
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

	
	+'  <td style="display:none; "  class="diagnosisClass1'+sShape+'">'
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
	 
	if(q!=0){
		var preShape=sShape-1;
			$("#sShape_value"+preShape+" > option").clone().appendTo("#sShape_value"+sShape);
			$("#s_status"+sShape).val($("#s_status"+preShape).val());
			$("#sShape_value"+sShape).val($("#sShape_value"+preShape).val());
			statusChange(1,sShape,$("#s_status"+preShape).val());
			$("#s_status"+sShape+" option[value='1']").remove();
			$("#s_status"+preShape+" option[value='1']").remove();
			$("#s_status"+preShape+" option[value='0']").remove();
		 }
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
						 if(sShape == 1){
							var s_val = $("#s_status"+sShape).val();
							var lis1 = '<option value="${getShapeStatusList[0][1]}" name="${getShapeStatusList[0][1]}">${getShapeStatusList[0][0]}</option>'
							 var lis2 = $("#s_status"+sShape).html();
							 $('#s_status'+sShape).empty().append('<option value="0">--Select--</option>'+lis1+lis2);
							 $("#s_status"+sShape).val(s_val);
							}
			
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
	
	+'  <td style="display:none; "  class="diagnosisClass2'+hShape+'">'
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
	if(q!=0){
		var preShape=hShape-1;
			$("#hShape_value"+preShape+" > option").clone().appendTo("#hShape_value"+hShape);
			$("#h_status"+hShape).val($("#h_status"+preShape).val());
			$("#hShape_value"+hShape).val($("#hShape_value"+preShape).val());
			statusChange(2,hShape,$("#h_status"+preShape).val());
			$("#h_status"+hShape+" option[value='1']").remove();
			$("#h_status"+preShape+" option[value='1']").remove();
			$("#h_status"+preShape+" option[value='0']").remove();
		 }
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
					 if(hShape == 1){
						var s_val = $("#h_status"+hShape).val();
						var lis1 = '<option value="${getShapeStatusList[0][1]}" name="${getShapeStatusList[0][1]}">${getShapeStatusList[0][0]}</option>'
						 var lis2 = $("#h_status"+hShape).html();
						 $('#h_status'+hShape).empty().append('<option value="0">--Select--</option>'+lis1+lis2);
						 $("#h_status"+hShape).val(s_val);
						}
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
	+'  <td style="display:none; "  class="diagnosisClass3'+aShape+'">'
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
	 
	if(q!=0){
		var preShape=aShape-1;
			$("#aShape_value"+preShape+" > option").clone().appendTo("#aShape_value"+aShape);
			$("#a_status"+aShape).val($("#a_status"+preShape).val());
			$("#aShape_value"+aShape).val($("#aShape_value"+preShape).val());
			statusChange(3,aShape,$("#a_status"+preShape).val());
			$("#a_status"+aShape+" option[value='1']").remove();
			$("#a_status"+preShape+" option[value='1']").remove();
			$("#a_status"+preShape+" option[value='0']").remove();
		 }
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
					 if(aShape == 1){
						var s_val = $("#a_status"+aShape).val();
						var lis1 = '<option value="${getShapeStatusList[0][1]}" name="${getShapeStatusList[0][1]}">${getShapeStatusList[0][0]}</option>'
						 var lis2 = $("#a_status"+aShape).html();
						 $('#a_status'+aShape).empty().append('<option value="0">--Select--</option>'+lis1+lis2);
						 $("#a_status"+aShape).val(s_val);
						}
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
	+'  <td style="display:none; "  class="diagnosisClass4'+pShape+'">'
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
	 
	if(q!=0){
		var preShape=pShape-1;
			$("#pShape_value"+preShape+" > option").clone().appendTo("#pShape_value"+pShape);
			$("#p_status"+pShape).val($("#p_status"+preShape).val());
			$("#pShape_value"+pShape).val($("#pShape_value"+preShape).val());
			statusChange(4,pShape,$("#p_status"+preShape).val());
			$("#p_status"+pShape+" option[value='1']").remove();
			$("#p_status"+preShape+" option[value='1']").remove();
			$("#p_status"+preShape+" option[value='0']").remove();
		 }
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
					 if(pShape == 1){
						var s_val = $("#p_status"+pShape).val();
						var lis1 = '<option value="${getShapeStatusList[0][1]}" name="${getShapeStatusList[0][1]}">${getShapeStatusList[0][0]}</option>'
						 var lis2 = $("#p_status"+pShape).html();
						 $('#p_status'+pShape).empty().append('<option value="0">--Select--</option>'+lis1+lis2);
						 $("#p_status"+pShape).val(s_val);
						}
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
	+'  <td style="display:none; "  class="diagnosisClass5'+eShape+'">'
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
	 
	 
	if(q!=0){
		var preShape=eShape-1;
			$("#eShape_value"+preShape+" > option").clone().appendTo("#eShape_value"+eShape);
			$("#e_status"+eShape).val($("#e_status"+preShape).val());
			$("#eShape_value"+eShape).val($("#eShape_value"+preShape).val());
			statusChange(5,eShape,$("#e_status"+preShape).val());
			$("#e_status"+eShape+" option[value='1']").remove();
			$("#e_status"+preShape+" option[value='1']").remove();
			$("#e_status"+preShape+" option[value='0']").remove();
		 }
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
					 if(eShape == 1){
						var s_val = $("#e_status"+eShape).val();
						var lis1 = '<option value="${getShapeStatusList[0][1]}" name="${getShapeStatusList[0][1]}">${getShapeStatusList[0][0]}</option>'
						 var lis2 = $("#e_status"+eShape).html();
						 $('#e_status'+eShape).empty().append('<option value="0">--Select--</option>'+lis1+lis2);
						 $("#e_status"+eShape).val(s_val);
						}
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
				for(var j=1; j<=sShape; j++){
					if($("#s_status"+j).val() != "0" && $("#s_status"+j).val() != "1")
						count_classi++;
					
					if(getformatedDate($("input#s_from_date"+j).val()) > getformatedDate($("#s_to_date"+j).val())) {
						alert("To Date should be Greater than Or Equal to From Date "+j+" Row");
						return false;
				    }
				}
				for(var j=1; j<=hShape; j++){
					if($("#h_status"+j).val() != "0" && $("#h_status"+j).val() != "1")
						count_classi++;
					if(getformatedDate($("input#h_from_date"+j).val()) > getformatedDate($("#h_from_date"+j).val())) {
						alert("To Date should be Greater than Or Equal to From Date "+j+" Row");
						return false;
				    }
				}
				for(var j=1; j<=aShape; j++){
					if($("#a_status"+j).val() != "0" && $("#a_status"+j).val() != "1")
						count_classi++;
					if(getformatedDate($("input#a_from_date"+j).val()) > getformatedDate($("#a_to_date"+j).val())) {
						alert("To Date should be Greater than Or Equal to From Date "+j+" Row");
						return false;
				    }
				}
				for(var j=1; j<=pShape; j++){
					if($("#p_status"+j).val() != "0" && $("#p_status"+j).val() != "1")
						count_classi++;
					if(getformatedDate($("input#p_from_date"+j).val()) > getformatedDate($("#p_to_date"+j).val())) {
						alert("To Date should be Greater than Or Equal to From Date "+j+" Row");
						return false;
				    }
				}
				for(var j=1; j<=eShape; j++){
					if($("#e_status"+j).val() != "0" && $("#e_status"+j).val() != "1")
						count_classi++;
					if(getformatedDate($("input#e_from_date"+j).val()) > getformatedDate($("#e_to_date"+j).val())) {
						alert("To Date should be Greater than Or Equal to From Date "+j+" Row");
						return false;
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
		    var comm_date=$("#comm_date").val();
			var census_id=$("#census_id").val();	
			var comm_id=$('#comm_id').val();			
			formvalues+="&census_id="+census_id+"&comm_id="+comm_id+"&comm_date="+comm_date;
			
			if(getformatedDate($("input#comm_date").val()) > getformatedDate($("#madical_date_of_authority").val())) {
				alert("Authority Date should be Greater than Commission Date");
				return false;
		    }
			
			if(getformatedDate($("input#1bx_from_date").val()) > getformatedDate($("#1bx_to_date").val())) {
				alert("To Date should be Greater than Or Equal to From Date");
				return false;
		    }

			
		   $.post('medical_categoryAction?' + key + "=" + value,formvalues, function(data){
			      
		      
		             	 
		        	 if(data=='1'){
		        		 alert('Data Save/Update Succesfully');
		        	
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
	 
	 
	 
// function sShape_save_fn(ps){
// 	var Shape="S";
// 	var Shape_status=$('#s_status'+ps).val();
// 	var Shape_value=$('#sShape_value'+ps).val();
// 	var from_date=$('#s_from_date'+ps).val();
// 	var to_date=$('#s_to_date'+ps).val();
// 	var diagnosis_code1=$('#s_diagnosis_code1'+ps).val();
// 	var diagnosis_code2=$('#s_diagnosis_code2'+ps).val();
// 	var diagnosis_code3=$('#s_diagnosis_code3'+ps).val();
// 	var diagnosis_code4=$('#s_diagnosis_code4'+ps).val();
// 	var diagnosis_code5=$('#s_diagnosis_code5'+ps).val();
// 	var diagnosis_code6=$('#s_diagnosis_code6'+ps).val();
// 	var authority=$('#madical_authority').val();
// 	var date_of_authority=$('#madical_date_of_authority').val();
// 	var mad_classification=$('#mad_classification').val();
// 	var comm_id=$('#comm_id').val();	
// 	var p_id=$('#census_id').val();
// 	var sShape_ch=$('#SShape_ch_id'+ps).val();
	
	
// 	if (authority == null || authority=="") {
// 		alert("Please Enter The Authority");
// 		$("#madical_authority").focus()
// 		return false;
// 	}
// 	if (date_of_authority == null || date_of_authority=="") {
// 		alert("Please Enter  Date of Authority");
// 		$("#madical_date_of_authority").focus()
// 		return false;
// 	}
// 	if (mad_classification == null || mad_classification=="0") {
// 		alert("Please Select The Medical Classification");
// 		$("#mad_classification").focus()
// 		return false;
// 	}
// 	if (Shape_status == null || Shape_status=="0") {
// 		alert("Please Select The Shape Status");
// 		$("#s_status"+ps).focus()
// 		return false;
// 	}
// 	if (Shape_value == null || Shape_value=="0") {
// 		alert("Please Select The Shape Value");
// 		$("#sShape_value"+ps).focus()
// 		return false;
// 	}
// 	if (from_date == null || from_date=="") {
// 		alert("Please Enter The From Date");
// 		$("#s_form_date"+ps).focus()
// 		return false;
// 	}
// 	if (to_date == null || to_date=="") {
// 		alert("Please Enter The To Date");
// 		$("#s_to_date"+ps).focus()
// 		return false;
// 	}
// 	if(mad_classification=='1'){
// 		diagnosis_code2="";
// 		diagnosis_code3="";
// 		diagnosis_code4="";
// 		diagnosis_code5="";
// 		diagnosis_code6="";
// 	}
	
// 	else if(mad_classification=='2'){
// 		if(diagnosis_code1=="" || diagnosis_code1 == null){
// 			alert("Please  Enter The Diagnosis-1");
// 			$("#s_diagnosis_code1"+ps).focus()
// 			return false;
// 		}
// 		if(diagnosis_code2=="" || diagnosis_code2 == null){
// 			alert("Please  Enter The Diagnosis-2");
// 			$("#s_diagnosis_code2"+ps).focus()
// 			return false;
// 		}
// 		diagnosis_code3="";
// 		diagnosis_code4="";
// 		diagnosis_code5="";
// 		diagnosis_code6="";
// 	}
// 	else if(mad_classification=='3'){
// 		if(diagnosis_code1=="" || diagnosis_code1 == null){
// 			alert("Please  Enter The Diagnosis-1");
// 			$("#s_diagnosis_code1"+ps).focus()
// 			return false;
// 		}
// 		if(diagnosis_code2=="" || diagnosis_code2 == null){
// 			alert("Please  Enter The Diagnosis-2");
// 			$("#s_diagnosis_code2"+ps).focus()
// 			return false;
// 		}
// 		if(diagnosis_code3=="" || diagnosis_code3 == null){
// 			alert("Please  Enter The Diagnosis-3");
// 			$("#s_diagnosis_code3"+ps).focus()
// 			return false;
// 		}
		
// 		if(diagnosis_code6!="" && diagnosis_code6 != null){
// 			if(diagnosis_code4=="" || diagnosis_code4 == null){
// 				alert("Please First Enter The Diagnosis-4");
// 				$("#s_diagnosis_code4"+ps).focus()
// 				return false;
// 			}
			
// 			if(diagnosis_code5=="" || diagnosis_code5 == null){
// 				alert("Please First Enter The Diagnosis-5");
// 				$("#s_diagnosis_code5"+ps).focus()
// 				return false;	
// 					}
// 		}
		
// 		if(diagnosis_code5!="" && diagnosis_code5 != null){
// 			if(diagnosis_code4=="" || diagnosis_code4 == null){
// 				alert("Please First Enter The Diagnosis-4");
// 				$("#s_diagnosis_code4"+ps).focus();
// 				return false;
// 			}
// 		}
// 	}
	
	
// 	   $.post('medical_categoryAction?' + key + "=" + value, {Shape_status:Shape_status,Shape_value:Shape_value,from_date:from_date,
// 		   to_date:to_date,diagnosis_code1:diagnosis_code1,diagnosis_code2:diagnosis_code2,diagnosis_code3:diagnosis_code3,diagnosis_code4:diagnosis_code4,
// 		   diagnosis_code5:diagnosis_code5,diagnosis_code6:diagnosis_code6,authority:authority,date_of_authority:date_of_authority,mad_classification:mad_classification,
// 		   comm_id:comm_id,p_id:p_id,sShape_ch:sShape_ch,Shape:Shape}, function(data){
		      
	      
// 	          if(parseInt(data)>0){
	        	
// 	        	 $('#SShape_ch_id'+ps).val(data);
//  	        	 $("#sShape_add"+ps).show();
// 	        	 $("#sShape_remove"+ps).show();
// 	        	  alert("Data Saved SuccesFully")
// 	          }
// 	          else
// 	        	  alert(data);
// 	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
// 	  		});
// }

// function get1BXShapeDetails(){
	
// 	 var p_id=$('#census_id').val(); 
	
// 	 var Shape='1BX'
// 	 var i=0;
// 	  $.post('madical_cat_GetData?' + key + "=" + value, {p_id:p_id,Shape:Shape }, function(j){
// 			var v=j.length;
			
// 		if(v!=0){
// 			$("#check_1bx").prop("checked", true);
// 			oncheck_1bx();
			
// 			fd=new Date(j[0][2]).toISOString().split('T')[0];
// 		  	 td=new Date(j[0][3]).toISOString().split('T')[0];
		  	 
// 		  	$("#shape_1bx_id").val(j[0][5]);
// 			$("#1bx_from_date").val(fd);
// 			$("#1bx_to_date").val(td);
// 			$("#1bx_diagnosis_code").val(j[0][4]);
// 			$("#madical_authority").val(j[0][6]); 
// 			$("#madical_date_of_authority").val(new Date(j[0][7]).toISOString().split('T')[0]);
// 			}
			
// 	  });
// }
	 function getsShapeDetails(id){
			
		 var p_id='${census_id}'; 
		
		 var Shape='S'
		 var i=0;
		  $.post('madical_cat_HisGetData?' + key + "=" + value, {id:id}, function(j){
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
// 						  	 td=convertDateFormate(j[i][3]);
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
								
								+'<select style="display:none" name="s_status'+x+'" id="s_status'+x+'" class="select_hide" '
								+'	class="form-control-sm form-control"  onchange="statusChange(1,'+x+',this.options[this.selectedIndex].value)">'
								+'	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">'
								+'	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>'
								+'	</c:forEach>'
								+'		</select>'
								+'  </td>'
								+'   <td style=""><label id="sShape_value_lb'+x+'"></label>'
								+'<select style="display:none" name="sShape_value'+x+'" id="sShape_value'+x+'" class="select_hide" '
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
// 							if(x>1){
// 								$("#s_status"+x+" option[value='1']").remove();
// 								$("#s_status"+(x-1)+" option[value='1']").remove();
								
// 							}
// 							else {
								var thisT = document.getElementById('sShape_value'+x);
								onShapeValueChange(thisT,'s');
// 							}
							 
							
							
						
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
					
					 fd=convertDateFormate(j[i-1][11]);
				  	 td=convertDateFormate(j[i-1][12]);
					$("#1bx_from_date").text(fd);
					$("#1bx_to_date").text(td);
					$("#1bx_diagnosis_code").text(j[i-1][13]);
				}
//	  			$('#mad_classification').attr('readonly', true);
				oncheck_1bx();
				setDiagnosis();
				}
			
			
				
		  });
	}


	function gethShapeDetails(id){
		
		 var p_id='${census_id}'; 
		
		 var Shape='H';
		 var i=0;
		  $.post('madical_cat_HisGetData?' + key + "=" + value, {id:id}, function(j){
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
// 						  	 td=convertDateFormate(j[i][3]);
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
								
								+'<select style="display:none" name="h_status'+x+'" id="h_status'+x+'" class="select_hide" '
								+'	class="form-control-sm form-control"  onchange="statusChange(2,'+x+',this.options[this.selectedIndex].value)">'
								+'	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">'
								+'	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>'
								+'	</c:forEach>'
								+'		</select>'
								+'  </td>'
								+'   <td style=""><label id="hShape_value_lb'+x+'"></label>'
								+'<select style="display:none" name="hShape_value'+x+'" id="hShape_value'+x+'" class="select_hide" '
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
							$("#hShape_value_lb"+x).text($( "#sShape_value"+x+" option:selected" ).text());
// 							if(x>1){
// 								$("#h_status"+x+" option[value='1']").remove();
// 								$("#h_status"+(x-1)+" option[value='1']").remove();
								
// 							}
// 							else {
								var thisT = document.getElementById('hShape_value'+x);
								onShapeValueChange(thisT,'h');
// 							}
							 
							
							
						
			}
				hShape=v;
				$("input#hShape_count").val(v);
				$("input#hShapeOld_count").val(v);
				$("#hShape_add"+v).show();
				
				setDiagnosis();
				}
				
		  });
	}



	function getaShapeDetails(id){
		
		 var p_id='${census_id}'; 
		
		 var Shape='A';
		 var i=0;
		  $.post('madical_cat_HisGetData?' + key + "=" + value, {id:id}, function(j){
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
// 						  	 td=convertDateFormate(j[i][3]);
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
								+'<select style="display:none" name="a_status'+x+'" id="a_status'+x+'" class="select_hide" '
								+'	class="form-control-sm form-control"  onchange="statusChange(3,'+x+',this.options[this.selectedIndex].value)">'
								+'	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">'
								+'	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>'
								+'	</c:forEach>'
								+'		</select>'
								+'  </td>'
								+'   <td style=""><label id="aShape_value_lb'+x+'"></label>'
								+'<select style="display:none" name="aShape_value'+x+'" id="aShape_value'+x+'" class="select_hide" '
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
// 							if(x>1){
// 								$("#a_status"+x+" option[value='1']").remove();
// 								$("#a_status"+(x-1)+" option[value='1']").remove();
								
// 							}
							else {
								var thisT = document.getElementById('aShape_value'+x);
								onShapeValueChange(thisT,'a');
// 							}
							 
							
							
						
			}
				aShape=v;
				$("input#aShape_count").val(v);
				$("input#aShapeOld_count").val(v);
				$("#aShape_add"+v).show(); 		
				
				setDiagnosis();
				}
				
		  });
	}



	function getpShapeDetails(id){
		
		 var p_id='${census_id}'; 
		
		 var Shape='P';
		 var i=0;
		  $.post('madical_cat_HisGetData?' + key + "=" + value, {id:id}, function(j){
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
// 						  	 td=convertDateFormate(j[i][3]);
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
								+'<select style="display:none" name="p_status'+x+'" id="p_status'+x+'" class="select_hide" '
								+'	class="form-control-sm form-control"  onchange="statusChange(4,'+x+',this.options[this.selectedIndex].value)">'
								+'	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">'
								+'	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>'
								+'	</c:forEach>'
								+'		</select>'
								+'  </td>'
								+'   <td style=""><label id="pShape_value_lb'+x+'"></label>'
								+'<select style="display:none" name="pShape_value'+x+'" id="pShape_value'+x+'" class="select_hide" '
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
							$("#pShape_value_lb"+x).text($( "#pShape_value_lb"+x+" option:selected" ).text());
// 							if(x>1){
// 								$("#p_status"+x+" option[value='1']").remove();
// 								$("#p_status"+(x-1)+" option[value='1']").remove();
								
// 							}
// 							else {
								var thisT = document.getElementById('pShape_value'+x);
								onShapeValueChange(thisT,'p');
// 							}
							 
							
							
						
			}
				pShape=v;
				$("input#pShape_count").val(v);
				$("input#pShapeOld_count").val(v);
				$("#pShape_add"+v).show(); 	
				setDiagnosis();
				}
				
		  });
	}


	function geteShapeDetails(id){
		
		 var p_id='${census_id}'; 
		
		 var Shape='E';
		 var i=0;
		  $.post('madical_cat_HisGetData?' + key + "=" + value, {id:id }, function(j){
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
// 						  	 td=convertDateFormate(j[i][3]);
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
								+'<label id="e_status_lb'+x+'"></label><select style="display:none" class="select_hide" name="e_status'+x+'" id="e_status'+x+'" '
								+'	class="form-control-sm form-control"  onchange="statusChange(5,'+x+',this.options[this.selectedIndex].value)">'
								+'	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">'
								+'	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>'
								+'	</c:forEach>'
								+'		</select>'
								+'  </td>'
								+'   <td style=""><label id="eShape_value_lb'+x+'"></label>'
								+'<select style="display:none" class="select_hide" name="eShape_value'+x+'" id="eShape_value'+x+'" '
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
// 							if(x>1){
// 								$("#e_status"+x+" option[value='1']").remove();
// 								$("#e_status"+(x-1)+" option[value='1']").remove();
								
// 							}
// 							else {
								var thisT = document.getElementById('eShape_value'+x);
								onShapeValueChange(thisT,'e');
// 							}
							 
							
							
						
			}
				eShape=v;
				$("input#eShape_count").val(v);
				$("input#eShapeOld_count").val(v);
				$("#eShape_add"+v).show(); 
				setDiagnosis();
				}
				
		  });
	}



	function getcCopeDetails(id){
		
		 var p_id='${census_id}'; 
		
		 var Shape='C_C';
		 var i=0;
		  $.post('madical_cat_HisGetData?' + key + "=" + value, {id:id }, function(j){
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
							 	
							 	+'<label id="c_cvalue_lb'+x+'"></label><select style="display:none" class="select_hide" name="c_cvalue'+x+'" id="c_cvalue'+x+'" '
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


	function getoCopeDetails(id){
		
		 var p_id='${census_id}'; 
		
		 var Shape='C_O';
		 var i=0;
		  $.post('madical_cat_HisGetData?' + key + "=" + value, {id:id }, function(j){
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
							 	+'<label id="c_ovalue_lb'+x+'"><label><select style="display:none" class="select_hide" name="c_ovalue'+x+'" id="c_ovalue'+x+'" '
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

	function getpCopeDetails(id){
		
		 var p_id='${census_id}'; 
		
		 var Shape='C_P';
		 var i=0;
		  $.post('madical_cat_HisGetData?' + key + "=" + value, {id:id}, function(j){
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
							 	+'<label id="c_pvalue_lb'+x+'"></label><select style="display:none" class="select_hide" name="c_pvalue'+x+'" id="c_pvalue'+x+'" '
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


	function geteCopeDetails(id){
		
		 var p_id='${census_id}'; 
		
		 var Shape='C_E';
		 var i=0;
		  $.post('madical_cat_HisGetData?' + key + "=" + value, {id:id}, function(j){
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
 </script> 
  <script>
  //***************** START QUALIFICATION DETAILS************************//
//qualification
  
  function fn_qualification_typeChange(){
  	
  	var val=$("#quali_type").val();
  	if(val=="6"){
  		$("#specializationDiv").show();
  	    $("#courseNamediv").show();
  	    $("#stream12Div").hide()
  	    $("#academyQuali").hide();
  	    $("#quali").val("0");
  	    $("#stream12").val("0");
  	    fn_setSpecialization("Tech")
  	    
  	}
  	else {
  		 $("#courseNamediv").hide();
  		 $("#stream12Div").hide()
  		 $("#specializationDiv").hide();
  		 $("#academyQuali").show()
  		 $("#stream12").val("0");
  		 $("#course_name").val("0");
  		 $("#specialization").val("0");
  	}
  }


  function fn_ExaminationTypeChange(){
  	var val=$("#quali").val();
  	if(val=='12th'){
  		 $("#stream12Div").show()
  	}
  	else{
  		$("#stream12Div").hide()
  	}
  	
  	if(val!='12th' && val!='10th' && val!='0'  && val!='others'){
  		fn_setSpecialization(val);
  		$("#specializationDiv").show();
  	}
  	else{
  		$("#specializationDiv").hide();
  		$("#specialization").val("0");
  	}
  }


  function fn_streamChanged(){
  	var val=$("#stream12").val();
  	if(val=='Science'){
  		fn_setSpecialization(val);
  		$("#specializationDiv").show();
  	}
  	else{
  		$("#specializationDiv").hide();
  		$("#specialization").val("0");
  	}
  }
  function fn_setSpecialization(query){
  	
  	$.post("getSpecialization?"+key+"="+value, {query:query}).done(function(j) {
  		 	
  		 	
  	 		var options = '<option   value="0">'+ "--Select--" + '</option>';
  			for ( var i = 0; i < j.length; i++) {
  			
  					options += '<option   value="' + j[i] + '" name="'+j[i]+'" >'+ j[i] + '</option>';
  				
  			}
  			
  			$("select#specialization").html(options);
  			
  		}).fail(function(xhr, textStatus, errorThrown) {
  });
  }
  function fn_other_exam() {
  	var text = $("#quali option:selected").text();
  	if(text == "OTHERS"){
  		$("#other_div_exam").show();
  	}
  	else{
  		$("#other_div_exam").hide();
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
  	}
  }
  function qualification_save_fn(qs){
  	
  	var dateofBirthyear=$("#dob").text().split('-')[2];
  	var currentdate=new Date();
  	var currentyear=currentdate.getFullYear();
  	
  	var type=$('#quali_type').val();
  	var examination_pass=$('#quali').val();
  	var exam_other_qua=$('#exam_other').val();
  	var class_other_qua=$('#class_other').val();
  	var stream=$("#stream12").val();
  	var passing_year=$('#yrOfPass').val();
  	var course_name=$('#quali_course_name').val();
  	var specialization=$('#specialization').val();		
  	var div_class=$('#div_class').val();
  	var institute=$('#institute_place').val();
  	var qualification_ch_id=$('#qualification_ch_id').val();
  	var q_id=$('#census_id').val();
  	var com_id=$('#comm_id').val();
  	var qual_authority=$('#qualification_authority').val();
  	var qual_doa=$('#qualification_date_of_authority').val();
  	var subjectvar= $('input[name="multisub"]:checkbox:checked').map(function() {
  	    return this.value;
  	}).get();
  	var subject=subjectvar.join(",");
  	

  	 if (examination_pass == "0") {
  		
  		}
  	 
  	 if(qual_authority == null || qual_authority==""){ 
  			alert("Please Enter Authority");
  			$("input#qualification_authority").focus();
  			return false;
  		}
  	 
  	 if(qual_doa == null || qual_doa=="" || qual_doa == "DD/MM/YYYY"){ 
  			alert("Please Select Date of Authority");
  			$("input#qualification_date_of_authority").focus();
  			return false;
  		} 
  	 
  	
  	 
  	 if(type!="6")
  		{
  			if(examination_pass == null || examination_pass=="0"){ 
  				alert("Please Enter Examination Pass");
  				$("input#quali").focus();
  				return false;
  			}
  			if(examination_pass=="12th") {
  				if(stream == null || stream=="0"){ 
  					alert("Please Select The Stream");
  					$("input#stream12").focus();
  					return false;
  				}
  				if( stream=="Science"){ 
  					if(specialization == null || specialization=="0"){ 
  						alert( "Please Select The Specialization");
  						$("input#specialization").focus();
  						return false;
  					}
  				}
  			}
  			if(!examination_pass=="12th" && !examination_pass=="10th" && !examination_pass=="others") {
  				if(specialization == null || specialization=="0"){ 
  					alert( "Please Select The  Specialization");
  					$("input#specialization").focus();
  					return false;
  				}
  			}	
  			
  		}
  		else {
  			if(specialization == null || specialization=="0"){ 
  				alert("Please Select The Specialization");
  				$("input#specialization").focus();
  				return false;
  			}
  			if(course_name == null || course_name=="0"){ 
  				alert("Please Select The Course Name");
  				$("input#quali_course_name").focus();
  				return false;
  			}
  		}
  	 
  	         if(examination_pass=="others") {
  	        	 if(exam_other_qua == null || exam_other_qua==""){ 	 
  	         
  				alert( "Please Enter Exam Other ");
  				$("input#exam_other").focus();
  				return false;
  			   }
  	         }
  		if (passing_year == "") {
  			alert("Please Enter yr of Passing");
  			$("input#yrOfPass").focus();
  			return false;
  		}
  		
  		if(passing_year != ""){
  		
  			if(parseInt(passing_year)<=parseInt(dateofBirthyear) || parseInt(passing_year)>parseInt(currentyear)){
  				alert("Please Enter Valid Year of Passing");
  				$("input#yrOfPass").focus();
  				return false;
  			}
  		}
  		if (div_class == "0") {
  			alert("Please Enter Div/class");
  			$("input#div_class").focus();
  			return false;
  		}
  		
  		if(div_class=="4") {
         	 if(class_other_qua == null || class_other_qua==""){ 	 
          
  			alert( "Please Enter Class Other ");
  			$("input#class_other").focus();
  			return false;
  		   }
          }
  	 if (subject == "") {
  			alert("Please Enter Subject");
  			$("input#sub_quali").focus();
  			return false;
  		}
  	 if (institute == "") {
  			alert("Please select Institute of place");
  			$("select#institute_place").focus();
  			return false;
  		}
  	
  			   $.post('update_offr_qualification_action?' + key + "=" + value, {type:type,examination_pass:examination_pass,passing_year:passing_year,
  				   div_class:div_class,subject:subject,institute:institute,qualification_ch_id:qualification_ch_id,q_id:q_id ,stream:stream,
  				   course_name:course_name,specialization:specialization,com_id:com_id,dateofBirthyear:dateofBirthyear,qual_authority:qual_authority,qual_doa:qual_doa,exam_other_qua:exam_other_qua,class_other_qua:class_other_qua}, function(data){	   
  	        
  	           if(parseInt(data)>0){ 	 		        
  	        	  alert("Data Saved SuccesFully")
  	          }
  	          else
  	        	  alert(data)
  	        	  
  	        		
  	   		$('#quali').val('0');		   		
  	   		$("#stream12").val('0');
  	   		$('#yrOfPass').val('');
  	   		$('#quali_course_name').val('0');
  	   		$('#specialization').val('0');		
  	   		$('#div_class').val('0');
  	   		$('#institute_place').val('');
  	   	    $('#qualification_ch_id').val('0');
  	   		$("input[type=checkbox][name='multisub']").prop("checked",false);
  	   		$('#quali').prop('disabled', false);
  	   		$('#stream12').prop('disabled', false);
  	   		$('#quali_type').prop('disabled', false);
  	    	$("#sub_quali option:first").text('Select Subjects');
  	    	$("#quali_sub_list").empty();
  	   		fn_qualification_typeChange();
  	   		getQualifications();
  	   		fn_other_exam();
  	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
  	  		});
  }
  function getQualifications(){
	  
	  var cen_id=$('#census_id').val(); 
		var comm_id=$('#comm_idV').val();
		var i=0;
		 $.post('getHisQualificationData?' + key + "=" + value, {comm_id:comm_id,cen_id:cen_id,cancel_status:cancel_status}, function(j){
				var v=j.length;
				$('#qualificationtbody').empty(); 
				 if(v!=0){
		              
						for(i;i<v;i++){
				     		cor=i+1;
				     		
				     		var dauth=convertDateFormate(j[i].date_of_authority);
				     		var dmod=convertDateFormate(j[i].modify_on);
				     		var exp_name =j[i].exp_name;
				     		var d_name =j[i].d_name;
				     		var spce_name =j[i].spce_name;
				     		var div =j[i].div;
				     		
				     		if(j[i].exp_name=="OTHERS"){
				     			exp_name=j[i].exam_other;
				     		}
				     		if(j[i].d_name=="OTHERS"){
				     			d_name=j[i].degree_other;
				     		}
				     		if(j[i].spce_name=="OTHERS"){
				     			spce_name=j[i].specialization_other;
				     		}
				     		if(j[i].div=="OTHERS"){
				     			div=j[i].class_other;
				     		}
							$("table#qualificationtable").append('<tr id="tr_quali'+cor+'">'
				                     +'<td class="anm_srno">'+cor+'</td>'
				                     +'<td style="width: 10%;">'+j[i].authority+'</td>'
				                     +'<td style="width: 10%;">'+dauth+'</td>'
				                     +'<td style="width: 10%;">'+j[i].type+'</td>'
				                     +'<td style="width: 10%;">'+exp_name+'</td>'
				                     +'<td style="width: 10%;">'+d_name+'</td>'
				                     +'<td style="width: 10%;">'+spce_name+'</td>'
				                     +'<td style="width: 10%;">'+j[i].passing_year+'</td>'
				                     +'<td style="width: 10%;">'+j[i].subject+'</td>'
				                     +'<td style="width: 10%;">'+div+'</td>'
				                     +'<td style="width: 10%;">'+j[i].institute+'</td>'
				                     +'<td style="width: 10%;">'+j[i].modify_by+'</td>'
				                     +'<td style="width: 10%;">'+dmod+'</td>'
				                     +'<td style="display:none;"><input type="text" id="flang_ch_id'+cor+'" name="flang_ch_id'+cor+'"  value="' + j[i].id + '" class="form-control autocomplete" autocomplete="off" ></td>'
				                     +'<td style="width: 10%;"><a  class="btn btn-danger btn-sm" value="REMOVE" title = "REJECT" '+j[i].action+'><i class="fa fa-times"></i></a>'
				                     +'</td></tr>');
				}
				 }	
		  });  
	  
  
  } 

  function editQualificationData(id,type,exampass,passing_year,course_name,stream,specialization,div_class,subject,institute,authority,date_of_authority,examother,classother){

  $('#yrOfPass').val(passing_year);
  $('#div_class').val(div_class);
  $('#institute_place').val(institute);
  $('#qualification_authority').val(authority);
  var dauth=ParseDateColumncommission(date_of_authority);
  $('#qualification_date_of_authority').val(dauth);
  $('#qualification_ch_id').val(id);
  $("input[type=checkbox][name='multisub']").prop("checked",false);
  var subjectslist=subject.split(',');

  for(k=0;k<subjectslist.length;k++){
  $("input[type=checkbox][name='multisub'][value='"+subjectslist[k]+"']").prop("checked",true);
  $("#sub_quali option:first").text('Subjects('+subjectslist.length+')');
  }	
  $('#quali_sub_list').empty();
  $("input[type='checkbox'][name='multisub']").each(function () {  
         if ( this.checked ) {   
         	
         	$('#quali_sub_list').append("<span class='subspan'>"+this.parentElement.innerText+"<i class='fa fa-times' style='margin: 5px;  font-size: 15px;' onclick='removeSubFn("+this.value+")'></i><span> <br>");
             
         }
    });

  $('#quali_type').val(type);
  $('#quali_type').prop('disabled', true);
  fn_qualification_typeChange();

  if(exampass!='--'){
  	$('#quali').prop('disabled', true);
  	$('#quali').val(exampass);
  	fn_ExaminationTypeChange();

  }
   
  fn_other_exam();
  $('#exam_other').val(examother);
   
    
   fn_other_class();
   	$('#class_other').val(classother);

  if(stream!='--'){	
  	$('#stream12').prop('disabled', true);
   	$("#stream12").val(stream);
   	fn_streamChanged();
  }
  if(course_name!='--'){
  	$('#quali_course_name').val(course_name);
  }
  if(specialization!='--'){
  	$('#specialization').val(specialization);
  }
  }
  function deleteQualificationData(id){
  	
  	var qualification_ch_id=id;
  	  $.post('qualification_delete_action?' + key + "=" + value, {qualification_ch_id:qualification_ch_id }, function(data){ 
  		  
  		   if(data=='1'){
  			   
  			   getQualifications();
  			   alert("Data Deleted SuceessFully");
  		   }
  			 else{
  					 
  				 alert("Data not Deleted ");
  			 }
  		   
  	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
  		});
  	  
  }
function showCheckboxes() {
	  var checkboxes = document.getElementById("checkboxes");
	  $("#checkboxes").toggle();
	  $("#quali_sub_search").val(''); 
	  
	  $('.quali_subjectlist').each(function () {       
	  $(this).show()
	})
	}


proex=1;
armyc=1;
proex_srno=1;
armyc_srno=1;
function promo_exam_add_fn(q){
	 if( $('#promo_exam_add'+q).length )         
	 {
			$("#promo_exam_add"+q).hide();
	 }
	 proex=q+1;
	if(q!=0)
		proex_srno+=1;
	$("table#promo_exam_table").append('<tr id="tr_promo_exam'+proex+'">'
			+'<td class="proex_srno" style="width: 2%;">'+proex_srno+'</td>'
			+'<td style="width: 10%;">'
			+'<select name="promo_exam'+proex+'" id="promo_exam'+proex+'" onchange="onPro_exam('+proex+')" class="form-control-sm form-control">'
			+'<option value="0">--Select--</option>'
			+'<c:forEach var="item" items="${getExamList}" varStatus="num">'
			+'<option value="${item[0]}" name="${item[1]}">${item[1]}</option> '
			+'</c:forEach> '
			+'</select></td>'
			+'<td style="width: 10%;">'
			+'<input type="text" id="exam_other'+proex+'" name="exam_other'+proex+'"  class="form-control autocomplete" autocomplete="off" />'
			+'</td>'
			+'<td style="width: 10%;">'
			+'<input type="month" id="promo_exam_dop'+proex+'" name="promo_exam_dop'+proex+'"  class="form-control autocomplete" autocomplete="off" />'
			+'</td>'
			+'<td style="display:none;"><input type="text" id="promo_exam_ch_id'+proex+'" name="promo_exam_ch_id'+proex+'"  value="0" class="form-control autocomplete" autocomplete="off" ></td>'
			+'<td style="width: 10%;">'
			+'<a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "promo_exam_save'+proex+'" onclick="promo_exam_save_fn('+proex+');" ><i class="fa fa-save"></i></a>'
			+'<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "promo_exam_add'+proex+'" onclick="promo_exam_add_fn('+proex+');" ><i class="fa fa-plus"></i></a>'
			+'<a style="display:none;" class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "promo_exam_remove'+proex+'" onclick="promo_exam_remove_fn('+proex+');"><i class="fa fa-trash"></i></a> '
			+'</td></tr>');
}

function promo_exam_remove_fn(R){
	var rc = confirm("Are You Sure! You Want To Delete");
	 if(rc){
	 var promo_exam_ch_id=$('#promo_exam_ch_id'+R).val();
	  $.post('update_offr_promo_exam_delete_action?' + key + "=" + value, {promo_exam_ch_id:promo_exam_ch_id }, function(data){ 
			   if(data=='1'){
					 $("tr#tr_promo_exam"+R).remove(); 
							 if(R==proex){
								 R = R-1;
								 var temp=true;
								 for(i=R;i>=1;i--){
								 if( $('#promo_exam_add'+i).length )         
								 {
									 $("#promo_exam_add"+i).show();
									 temp=false;
									 proex=i;
									 break;
								 }}
						 
							 if(temp){
								 promo_exam_add_fn(0);
								}
			  			 }
							 $('.proex_srno').each(function(i, obj) {
								 
									obj.innerHTML=i+1;
									proex_srno=i+1;
									 });
							 alert("Data Deleted SuceessFully");
			   }
				 else{
					 alert("Data not Deleted ");
				 }
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});
	 }
}
function promo_exam_save_fn(ps){
	var promo_exam=$('#promo_exam'+ps).val();
	var e_ot=$('#exam_other'+ps).val();
	var promo_exam_dop=$('#promo_exam_dop'+ps).val();
	var promo_exam_ch_id=$('#promo_exam_ch_id'+ps).val();
	var p_id=$('#census_id').val();
	var com_id=$('#comm_id').val();
	var promo_exam_authority=$('#promo_exam_authority').val();
	var promo_exam_doa=$('#promo_exam_date_of_authority').val();
 	if (promo_exam == "0") {
 		alert("Please select Exam");
 		$("select#promo_exam"+ps).focus();
 		return false;
 	}	
 	var country_sel = $("#promo_exam"+ps+" option:selected").text();
	if (country_sel == "OTHERS" && e_ot == "") {
		alert("Please Enter Other Exam");
		$('#exam_other'+ps).focus();
		return false;
	} 

	if (promo_exam_authority == "") {
		alert("Please Enter Authority");
		$("input#promo_exam_authority").focus();
		return false;
	}
	 if (promo_exam_doa == "" || promo_exam_doa == "DD/MM/YYYY") {
		alert("Please Select Date of Authority");
		$("input#promo_exam_date_of_authority").focus();
		return false;
	}
	if (promo_exam_dop == "") {
		alert("Please Enter Date of Passing");
		$("select#promo_exam_dop"+ps).focus();
		return false;
	}
 
	  $.post('update_offr_promo_exam_detail_action?' + key + "=" + value, {promo_exam:promo_exam,e_ot:e_ot,promo_exam_dop:promo_exam_dop,promo_exam_ch_id:promo_exam_ch_id,p_id:p_id,com_id:com_id,promo_exam_authority:promo_exam_authority,promo_exam_doa:promo_exam_doa }, function(data){
	          if(data=="update")
	        	  alert("Data Updated Successfully");
	          else if(parseInt(data)>0){
	        	 $('#promo_exam_ch_id'+ps).val(data);
	        	 $("#promo_exam_add"+ps).show();
	        	 $("#promo_exam_remove"+ps).show();
	        	  alert("Data Saved SuccesFully")
	          }
	          else
	        	  alert(data)
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});
}



function get_promo_exam_details(){
	var cen_id=$('#census_id').val(); 
	var comm_id=$('#comm_idV').val();
	var i=0;
	 $.post('getHisPromotionalExamData?' + key + "=" + value, {comm_id:comm_id,cen_id:cen_id,cancel_status:cancel_status}, function(j){
			var v=j.length;
			$('#promo_examtbody').empty(); 
			 if(v!=0){
	              
					for(i;i<v;i++){
			     		cor=i+1;
			     		
			     		var dauth=convertDateFormate(j[i].date_of_authority);
			     		var dmod=convertDateFormate(j[i].modify_on);
			     		var exam =j[i].exam;
			     		
			     		if(j[i].exam=="OTHERS"){
			     			exam=j[i].exam_other;
			     		}
						$("table#promo_exam_table").append('<tr id="tr_promo_exam'+cor+'">'
			                     +'<td class="anm_srno">'+cor+'</td>'
			                     +'<td style="width: 10%;">'+j[i].authority+'</td>'
			                     +'<td style="width: 10%;">'+dauth+'</td>'
			                     +'<td style="width: 10%;">'+exam+'</td>'
			                     +'<td style="width: 10%;">'+j[i].date_of_passing+'</td>'
			                     +'<td style="width: 10%;">'+j[i].modify_by+'</td>'
			                     +'<td style="width: 10%;">'+dmod+'</td>'
			                     +'<td style="display:none;"><input type="text" id="flang_ch_id'+cor+'" name="flang_ch_id'+cor+'"  value="' + j[i].id + '" class="form-control autocomplete" autocomplete="off" ></td>'
			                     +'<td style="width: 10%;"><a  class="btn btn-danger btn-sm" value="REMOVE" title = "REJECT" '+j[i].action+'><i class="fa fa-times"></i></a>'
			                     +'</td></tr>');
			}
			 }	
	  });
	
	 
}
function onPro_exam(pro){
	
	var p_exam = $("#promo_exam"+pro+" option:selected").text();

	if(p_exam != "OTHERS"){
		
		$('#exam_other'+pro).val("");
		$('#exam_other'+pro).attr('readonly', true);
	}
	else
		$('#exam_other'+pro).attr('readonly', false);
}

function onDivGrade(ind){
	var army_course_div_grade = $("#army_course_div_grade"+ind+" option:selected").text();
	 
	if(army_course_div_grade != "OTHERS"){
		$('#ar_course_div_ot'+ind).val("");
		$('#ar_course_div_ot'+ind).attr('readonly', true);
	}
	else
		$('#ar_course_div_ot'+ind).attr('readonly', false);
}
function onCoursetype(ind){
	var army_course_type = $("#army_course_type"+ind+" option:selected").text();
	 
	if(army_course_type != "OTHERS"){
		$('#course_type_ot'+ind).val("");
		$('#course_type_ot'+ind).attr('readonly', true);
	}
	else
		$('#course_type_ot'+ind).attr('readonly', false);
}
 
function army_course_add_fn(q){
	 if( $('#army_course_add'+q).length )         
	 {
			$("#army_course_add"+q).hide();
	 }
	 armyc=q+1;
	if(q!=0)
		armyc_srno+=1;
	$("table#army_course_table").append('<tr id="tr_army_course'+armyc+'">'
			+'<td class="army_course_srno" style="width: 2%;">'+armyc_srno+'</td>'
			+'<td style="width: 10%;">'
			+'<input type="text" id="army_course_name'+armyc+'" name="army_course_name'+armyc+'" class="form-control autocomplete" onkeypress="AutoCompleteCourse(this);" autocomplete="off" maxlength="20">'
			+'</td>'
			+'<td style="width: 10%;">'
			+'<select  id="army_course_div_grade'+armyc+'" name="army_course_div_grade'+armyc+'" class="form-control autocomplete" onchange="onDivGrade('+armyc+')" >'
			+'<option value="0">--Select--</option>'
			+'<c:forEach var="item" items="${getDivclass}" varStatus="num">'
			+'<option value="${item[0]}" name="${item[1]}">${item[1]}</option> '
			+'</c:forEach></select></td>'
			
			+'<td style="width: 10%;"> <input type="text" id="ar_course_div_ot'+armyc+'" name="ar_course_div_ot'+armyc+'" onkeypress="return /[0-9a-zA-Z]/i.test(event.key)" readonly  class="form-control autocomplete" autocomplete="off" ></td>'
			  
			+'<td style="width: 10%;">'
			+'<select  id="army_course_type'+armyc+'" name="army_course_type'+armyc+'" class="form-control autocomplete" onchange="onCoursetype('+armyc+')" >'
			+'<option value="0">--Select--</option>'
			+'<c:forEach var="item" items="${getCourseTypeList}" varStatus="num">'
			+'<option value="${item[0]}" name="${item[1]}">${item[1]}</option> '
			+'</c:forEach> </select>	</td>'
			
			+'<td style="width: 10%;"> <input type="text" id="course_type_ot'+armyc+'" name="course_type_ot'+armyc+'" onkeypress="return /[0-9a-zA-Z]/i.test(event.key)" readonly  class="form-control autocomplete" autocomplete="off" ></td>'
			
			
			+'<td style="width: 10%;">' 
			+' <input type="text" name="army_course_start_date'+armyc+'" id="army_course_start_date'+armyc+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')"   class="form-control" style="width: 85%;display: inline;" '
			+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
			+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);">'
			+ '</td>'
			+'<td style="width: 10%;">' 
			+' <input type="text" name="army_course_date_of_completion'+armyc+'" id="army_course_date_of_completion'+armyc+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')"   class="form-control" style="width: 85%;display: inline;" '
			+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
			+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);">'
			+ '</td>'
			+'<td style="display:none;"><input type="text" id="army_course_ch_id'+armyc+'" name="army_course_ch_id'+armyc+'"  value="0" class="form-control autocomplete" autocomplete="off" ></td>'
			+'<td style="width: 10%;">'
			+'<a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "army_course_save'+armyc+'" onclick="army_course_save_fn('+armyc+');" ><i class="fa fa-save"></i></a>'
			+'<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "army_course_add'+armyc+'" onclick="army_course_add_fn('+armyc+');" ><i class="fa fa-plus"></i></a>'
			+'<a style="display:none;" class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "army_course_remove'+armyc+'" onclick="army_course_remove_fn('+armyc+');"><i class="fa fa-trash"></i></a> '
			+'</td></tr>');
	 
	 datepicketDate('army_course_start_date'+armyc);
	 datepicketDate('army_course_date_of_completion'+armyc);
}


function army_course_remove_fn(R){
	var rc = confirm("Are You Sure! You Want To Delete");
	 if(rc){
	 var army_course_ch_id=$('#army_course_ch_id'+R).val();
	  $.post('update_offr_army_course_delete_action?' + key + "=" + value, {army_course_ch_id:army_course_ch_id }, function(data){ 
			   if(data=='1'){
					 $("tr#tr_army_course"+R).remove(); 
							 if(R==armyc){
								 R = R-1;
								 var temp=true;
								 for(i=R;i>=1;i--){
								 if( $('#army_course_add'+i).length )         
								 {
									 $("#army_course_add"+i).show();
									 temp=false;
									 armyc=i;
									 break;
								 }}
						 
							 if(temp){
								 army_course_add_fn(0);
								}
			  			 }
							 $('.army_course_srno').each(function(i, obj) {
								 
									obj.innerHTML=i+1;
									armyc_srno=i+1;
									 });
							 alert("Data Deleted SuceessFully");
			   }
				 else{
					 alert("Data not Deleted ");
				 }
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});
	 }
}
function army_course_save_fn(ps){
	var army_course_name=$('#army_course_name'+ps).val();
	
	var army_course_div_grade=$('#army_course_div_grade'+ps).val();
	var ar_course_div_ot=$('#ar_course_div_ot'+ps).val();
	var army_course_type=$('#army_course_type'+ps).val();
	var course_type_ot=$('#course_type_ot'+ps).val();
	
	
	var army_course_start_date=$('#army_course_start_date'+ps).val();
	var army_course_date_of_completion=$('#army_course_date_of_completion'+ps).val();
	var army_course_ch_id=$('#army_course_ch_id'+ps).val();
	var p_id=$('#census_id').val();
	var com_id=$('#comm_id').val();
	var army_course_authority=$('#army_course_authority').val();
	var army_course_doa=$('#army_course_date_of_authority').val();
	
	var comm_date=$('#comm_date').val();
	
	
	if(getformatedDate($("input#comm_date").val()) >= getformatedDate($("#army_course_date_of_authority").val())) {
		alert("Authority Date should be Greater than Commission Date");
		return false;
  }
	
	if (army_course_name == "") {
		alert("Please Enter Course Name");
		$("select#army_course_name"+ps).focus();
		return false;
	}
	if (army_course_div_grade == "0") {
		alert("Please select Div/Grade");
		$("select#army_course_div_grade"+ps).focus();
		return false;
	}	
	var army_course_div_gradeV = $("#army_course_div_grade"+ps+" option:selected").text();
	if (army_course_div_gradeV == "OTHERS" && ar_course_div_ot == "") {
		alert("Please Enter Other Div/Grade");
		$('#ar_course_div_ot'+ps).focus();
		return false;
	}
	var army_course_typeV = $("#army_course_type"+ps+" option:selected").text();
	if (army_course_typeV == "OTHERS" && course_type_ot == "") {
		alert("Please Enter Other Course Type");
		$('#course_type_ot'+ps).focus();
		return false;
	}
	if (army_course_authority == "") {
		alert("Please Enter Authority");
		$("input#army_course_authority").focus();
		return false;
	}
	if (army_course_doa == "" || army_course_doa == "DD/MM/YYY") {
		alert("Please Enter Date of Authority");
		$("input#army_course_date_of_authority").focus();
		return false;
	}
	if (army_course_start_date == "" || army_course_start_date == "DD/MM/YYY") {
		alert("Please Enter Start Date");
		$("input#army_course_start_date"+ps).focus();
		return false;
	}
	if (army_course_date_of_completion == "" || army_course_date_of_completion == "DD/MM/YYY") {
		alert("Please Enter Date of Completion");
		$("input#army_course_date_of_completion"+ps).focus();
		return false;
 	}
      if(getformatedDate(army_course_start_date) > getformatedDate(army_course_date_of_completion)) {
		alert("Completion Date should always be greater than Start Date");
		$("input#army_course_date_of_completion"+ps).focus();
		return false;
	}
	  $.post('update_offr_army_course_detail_action?' + key + "=" + value, 
			  {comm_date:comm_date,army_course_name:army_course_name,army_course_div_grade:army_course_div_grade,ar_course_div_ot:ar_course_div_ot,
		  		army_course_type:army_course_type,course_type_ot:course_type_ot,army_course_start_date:army_course_start_date,
		  		army_course_date_of_completion:army_course_date_of_completion,army_course_ch_id:army_course_ch_id,
		  		p_id:p_id,com_id:com_id,army_course_authority:army_course_authority,army_course_doa:army_course_doa }, function(data){
	          if(data=="update")
	        	  alert("Data Updated Successfully");
	          else if(parseInt(data)>0){
	        	 $('#army_course_ch_id'+ps).val(data);
	        	 $("#army_course_add"+ps).show();
	        	 $("#army_course_remove"+ps).show();
	        	  alert("Data Saved SuccesFully")
	          }
	          else
	        	  alert(data)
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});
}



function get_army_course_details(){
	var cen_id=$('#census_id').val(); 
	var comm_id=$('#comm_idV').val();
	var i=0;
	 $.post('getHisArmyCourseData?' + key + "=" + value, {comm_id:comm_id,cen_id:cen_id,cancel_status:cancel_status}, function(j){
			var v=j.length;
			$('#army_coursetbody').empty(); 
			 if(v!=0){
	              
					for(i;i<v;i++){
			     		cor=i+1;
			     		
			     		var dst=convertDateFormate(j[i].start_date);
			     		var doc=convertDateFormate(j[i].date_of_completion);
			     		var dauth=convertDateFormate(j[i].date_of_authority);
			     		var dmod=convertDateFormate(j[i].modify_on);
			     		var div =j[i].div;
			     		var course_type =j[i].course_type;
			     		
			     		if(j[i].div=="OTHERS"){
			     			div=j[i].ar_course_div_ot;
			     		}
			     		if(j[i].course_type=="OTHERS"){
			     			course_type=j[i].course_type_ot;
			     		}
						$("table#army_course_table").append('<tr id="tr_army_course'+cor+'">'
			                     +'<td class="anm_srno">'+cor+'</td>'
			                     +'<td style="width: 10%;">'+j[i].authority+'</td>'
			                     +'<td style="width: 10%;">'+dauth+'</td>'
			                     +'<td style="width: 10%;">'+j[i].course_name+'</td>'
			                     +'<td style="width: 10%;">'+j[i].course_abbreviation+'</td>'
			                     +'<td style="width: 10%;">'+j[i].institute+'</td>'
			                     +'<td style="width: 10%;">'+div+'</td>'
			                     +'<td style="width: 10%;">'+course_type+'</td>'
			                     +'<td style="width: 10%;">'+dst+'</td>'
			                     +'<td style="width: 10%;">'+doc+'</td>'
			                     +'<td style="width: 10%;">'+j[i].modify_by+'</td>'
			                     +'<td style="width: 10%;">'+dmod+'</td>'
			                     +'<td style="display:none;"><input type="text" id="flang_ch_id'+cor+'" name="flang_ch_id'+cor+'"  value="' + j[i].id + '" class="form-control autocomplete" autocomplete="off" ></td>'
			                     +'<td style="width: 10%;"><a  class="btn btn-danger btn-sm" value="REMOVE" title = "REJECT" '+j[i].action+'><i class="fa fa-times"></i></a>'
			                     +'</td></tr>');
			}
			 }	
	  });
	
	
}


function AutoCompleteCourse(ele){
	
	var code = ele.value;
	var susNoAuto =$("#"+ele.id);
	susNoAuto.autocomplete({
		source : function(request, response) {
			$.ajax({
				type : 'POST',
				url : "getArmyCourseNameList?" + key + "=" + value,
				data : {
					
				},
				success : function(data) {
					var susval = [];
					var length = data.length - 1;
					
					for (var i = 0; i < data.length; i++) {
						susval.push(data[i][1]);
					}

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
				alert("Please Enter Course Name ");			
				susNoAuto.val("");
				susNoAuto.focus();
				return false;
			}
		},
		select : function(event, ui) {

		}
	});
	
}

//***************** END QUALIFICATION DETAILS************************//
</Script>

<script>	
//***************** START CHANGE OF APPOINTEMENT DETAILS************************//
function get_Appointment(){
	var cen_id=$('#census_id').val(); 
	var comm_id=$('#comm_idV').val();
	var i=0;
	 $.post('getHisChangeOfAppointmentData?' + key + "=" + value, {comm_id:comm_id,cen_id:cen_id,cancel_status:cancel_status}, function(j){
		 var v=j.length;
		 $('#Appointment_tbody').empty();
	     if(v!=0){
	              
			for(i;i<v;i++){
	     		cor=i+1;
	     		var dauth=convertDateFormate(j[i].appt_date_of_authority);
	     		var dapp=convertDateFormate(j[i].date_of_appointment);
	     		var dmod=convertDateFormate(j[i].modified_date);
	     		var x_list_loc =j[i].x_list_loc;
	     		var x_list_sus_no =j[i].x_list_sus_no;
	     		if(j[i].x_list_loc==null){
	     			x_list_loc="";
	     		}
	     		if(j[i].x_list_sus_no==null){
	     			x_list_sus_no="";
	     		}
				$("table#Appointment_table").append('<tr id="tr_Appointment'+cor+'">'
	                     +'<td class="anm_srno">'+cor+'</td>'
	                     +'<td style="width: 10%;">'+j[i].appt_authority+'</td>'
	                     +'<td style="width: 10%;">'+dauth+'</td>'
	                     +'<td style="width: 10%;">'+j[i].description+'</td>'
	                     +'<td style="width: 10%;">'+dapp+'</td>'
// 	                     +'<td style="width: 10%;">'+fn_getUnitnamefromSusHis(x_list_sus_no)+'</td>'
// 	                     +'<td style="width: 10%;">'+x_list_loc+'</td>'
	                     +'<td style="width: 10%;">'+j[i].modified_by+'</td>'
	                     +'<td style="width: 10%;">'+dmod+'</td>'
	                     +'<td style="display:none;"><input type="text" id="Appointment_ch_id'+cor+'" name="Appointment_ch_id'+cor+'"  value="' + j[i].id + '" class="form-control autocomplete" autocomplete="off" ></td>'
	                     +'<td style="width: 10%;"><a  class="btn btn-danger btn-sm" value="REMOVE" title = "REJECT" '+j[i].action+'><i class="fa fa-times"></i></a>'
	                     +'</td></tr>');

			}
		}
	 });
}	
validate_coc_save_fn
function appNamechng(){
	var appname = $("#appointment option:selected").text();
	  //alert("--"+appname+"--");
		if(appname == "1" || appname == "ON COURSE ABROAD" || appname == "ON COURSE EXCEEDING 10 WEEKS" 
				|| appname == "ON DEPUTATION" || appname == "ON FURLOUGH LEAVE"
				|| appname == "ON LEAVE PENDING RETIREMENT R/R" || appname == "ON STUDY LEAVE" || appname == "ON ATTACHMENT" || appname == "IN HOSP MORE THAN 60 DAYS" || appname == "MISSING IN OPERATION/WAR" || appname =="ATT FOR DISCIPLINARY ACTION" || appname == "POW (PRISONER OF WAR)"){
			$("#xlist").show();
			}
		else{
			$("#xlist").hide();
			$("#x_sus_no").val("");
			$("#x_list_loc").val("");
		 
	}
}
	

function validate_appointment_save(){
	var tos_date=$('#tos_date').val();
	var comm_id=$('#comm_id').val();
	var comm_date=$('#comm_date').val();
	var census_id=$('#census_id').val();
	var status=$('#status').val();
	var appoint_id=$('#appoint_id').val();
	var formdata=$('#form_change_of_appointment').serialize();
	formdata+="&census_id="+census_id+"&comm_id="+comm_id+"&comm_date="+comm_date+"&tos_date="+tos_date;	
	  if ($("input#appt_authority").val() == "") {
		alert("Please Enter Authority.");
		$("input#appt_authority").focus();
		return false;
	}
	  if ($("input#appt_date_of_authority").val() == "DD/MM/YYYY" || $("input#appt_date_of_authority").val() =="") {
		alert("Please Select Date of Authority. ");
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
   if ($("input#date_of_appointment").val() == "DD/MM/YYYY") {
		alert("Please Select Date of Appointment. ");
		$("input#date_of_appointment").focus();
		return false;
	}  
   if($("input#tos_date").val()!="" && getformatedDate($("input#tos_date").val()) > getformatedDate($("#date_of_appointment").val())) {
		alert("Date of Appointment Date should be Greater than TOS Date");
		return false;
   }

 
	   $.post('change_appointmentAction?' + key + "=" + value, formdata, function(data){		      
		   if(data=="update"){
			   alert("Data Updated Successfully");
		   }
		   else if(parseInt(data)>0){
	        	 $('#appoint_id').val(data);
	        	  alert("Data Saved SuccesFully")
	          }
	          else
	        	  alert(data)
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});	
}
//***************** END QUALIFICATION DETAILS************************//
</Script>

<script>
$(document).ready(function() {
	
	 
	//alert("unit_sus_no6--"+'${unit_sus_no6}');
	//alert("comm_idV--"+'${comm_id}');
	$("#personnel_noV").val('${personnel_no6}');
	$("#icstatusV").val('${icstatus6}');
	$("#statusV").val('${status6}');
	$("#rankV").val('${rank6}');
	$("#comm_idV").val('${comm_id6}');
	$("#comm_idApp").val('${comm_id6}');
	$("#unit_nameV").val('${unit_name6}');
	$("#unit_sus_noV").val('${unit_sus_no6}');
	 	
	
	
		$("#back_bt_div").show();
		$('#status').val('${status}');
		if('${NonEffective}' > 0){
			$("#maindetailsdiv").hide();
		}

	$('#personnel_no').val('${personnel_no_edit}');
	if ('${personnel_no_edit}' != ""){
		$('#census_id').val('${census_idHis}'); 
		var personnel_no = '${personnel_no_edit}';
		 $.post('GetPersonnelNoData?' + key + "=" + value,{ personnel_no : personnel_no },function(j) {
			 if(j!=""){
				 
			
		    	$("#comm_id").val(j[0][0]);
		    	$("select#inter_arm_old_arm_service").val(j[0][8]);
				$("select#inter_arm_old_regt").val(j[0][9]);
		    	var comm_id =j[0][0]; 
		    	 $.post('GetCensusDataApprove?' + key + "=" + value,{ comm_id : comm_id },function(k) {
		    		 if(k.length > 0)
		    		 {
// 		    			 $('#census_id').val(k[0][1]); 
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
		    		    		 $("#dob_date").val(ParseDateColumncommission(k[0][11]));
		    		    	}
		    			 	$("#comm_date").val(ParseDateColumncommission(k[0][12]));
		    			    $("#marital_status_p").text(k[0][13]);
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
		    		    	$("#tos_date").val(ParseDateColumncommission(k[0][6]));
		    		    	$("#app_sus_no").text(k[0][7]);
		    		    	$("#p_id_no").text(k[0][8]);
		    		    	$("#p_religion").text(k[0][9]);
		    		    	$("#app_parent_arm").text(k[0][10]);
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
		 		
		    	 $.post('GetServingStatus?' + key + "=" + value,{ comm_id : comm_id },function(k) {
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
}); 
</script>
<script>

//************************************* START DISCIPLINE *****************************//

function onTypeofEntry(){
	
	var type_of_entry_sel = $("#type_of_entry option:selected").text();
	//alert("hiii---" + type_of_entry_sel);
	if(type_of_entry_sel != "OTHER"){
		
		$("#type_of_entry_other").hide();
		//$('#type_of_entry_other').attr('readonly', true);
	}
	else
		$("#type_of_entry_other").show();
}

function get_change_of_Discipline(){
	var cen_id=$("#census_id").val();	
	var comm_id=$("#comm_id").val();
	
	 var i=0;
	$.post('getHisDisciplineData?' + key + "=" + value, {comm_id:comm_id,cen_id:cen_id,cancel_status:cancel_status}, function(j){
		 var v=j.length;
		 $('#updateDiscipline_tbody').empty(); 
	     if(v!=0){
	             
			for(i;i<v;i++){
	     		cor=i+1;
	     		
	     		var toe=j[i].type_of_entry;
	     		
	     		
	     		if(j[i].type_of_entry_other!=null && j[i].type_of_entry_other!='')
	     			toe=j[i].type_of_entry_other;
	     		
	     		
	   
	     		
	     		var dauth=convertDateFormate(j[i].disi_authority_date);
	     		var daward=convertDateFormate(j[i].award_dt);
	     		var dmod=convertDateFormate(j[i].modified_date);
				$("table#updateDiscipline_table").append('<tr id="tr_battle_cause'+cor+'">'
	                     +'<td class="anm_srno">'+cor+'</td>'
	                     +'<td style="width: 10%;">'+j[i].disi_authority+'</td>'
	                     +'<td style="width: 10%;">'+dauth+'</td>'
	                     +'<td style="width: 10%;">'+j[i].army_act_sec+'</td>'
	                     +'<td style="width: 10%;">'+j[i].sub_clause+'</td>'
	                     +'<td style="width: 10%;">'+j[i].disc_trialed+'</td>'
	                     +'<td style="width: 10%;">'+j[i].description+'</td>'
	                     +'<td style="width: 10%;">'+toe+'</td>'
	                     
	                     
	                     +'<td style="width: 10%;">'+daward+'</td>'
	                     +'<td style="width: 10%;">'+fn_getUnitnamefromSusHis(j[i].unit_name)+'</td>'
	                    
	                     
	                    
	                     +'<td style="width: 10%;">'+j[i].modified_by+'</td>'
	                     +'<td style="width: 10%;">'+dmod+'</td>'                    
	                     +'<td style="width: 10%;"><a  class="btn btn-danger btn-sm" value="REMOVE" title = "REJECT"  '+j[i].action+'><i class="fa fa-times"></i></a>'
	                     +'</td></tr>');

			}
		}
	 });
	} 


function validate_Discipline_save(){
	var formvalues=$("#form_disipline").serialize();
	/*   if ($("#army_no1").val() == "") {
			alert("Please Enter Army No");
			$("#army_no1").focus();
			return false;
	 }
	 if ($("#rank_d").val() == "0") {
			alert("Please Select Rank");
			$("#rank_d").focus();
			return false;
	 }
	 if ($("#name_dis").val() == "") {
			alert("Please Enter Name");
			$("#name_dis").focus();
			return false;
	 }  */
	 
	 if ($("#disi_authority").val() == "") {
			alert("Please Enter Authority");
			$("#disi_authority").focus();
			return false;
	 } 
	 
	 if ($("#disi_authority_date").val() == "" || $("#disi_authority_date").val() =="DD/MM/YYYY") {
			alert("Please Select Authority Date");
			$("#disi_authority_date").focus();
			return false;
	 } 
	 
	 if ($("select#army_act_sec").val() == "0") {
			alert("Please Select Army Act Sec");
			$("select#army_act_sec").focus();
			return false;
	 } 

	 if ($("select#sub_clause").val() == "0") {
			alert("Please Select Sub Clause");
			$("select#sub_clause").focus();
			return false;
	 }

	 if ($("select#trialed_by").val() == "0") {
			alert("Please Select Trialed By");
			$("select#trialed_by").focus();
			return false;
	 }
	  if ($("#description1").val() == "") {
			alert("Please Enter Punishment Awarded ");
			$("#description1").focus();
			return false;
	 } 
	
	 if ($("#type_of_entry").val() == "") {
			alert("Please Enter Type of Entry ");
			$("#type_of_entry").focus();
			return false;
	 }
	  if ($("#award_dt").val() == "" || $("#award_dt").val() == "DD/MM/YYYY") {
			alert("Please Enter  Award Date");
			$("#award_dt").focus();
			return false;
	 } 
	  if(getformatedDate($("input#comm_date").val()) > getformatedDate($("#award_dt").val())) {
			alert("Date Of Award should be Greater Or Equal  Commission Date");
			$("#award_dt").focus();
			return false;
	  }
	 if ($("#unit_name").val() == "") {
			alert("Please Enter Unit Name");
			$("#unit_name").focus();
			return false;
	 }
	 
	 var type_of_entry_sel = $("#type_of_entry option:selected").text();
				if(type_of_entry_sel != "OTHER"){
					if ($("#type_of_entry_other").val() == "") {
						alert("Please Enter Type of Entry Other");
						$("#type_of_entry_other").focus();
						return false;
				 }
				}
	
	 

	
	var disi_id=$('#disi_id').val();
	var census_id=$("#census_id").val();	
	var comm_id=$('#comm_id').val();			
	formvalues+="&census_id="+census_id+"&comm_id="+comm_id;

	   $.post('Change_of_Discipline_action?' + key + "=" + value,formvalues, function(data){
		   if(data=="update"){
			   alert("Data Updated Successfully");
		   }
		   else if(parseInt(data)>0){
	        	 $('#disi_id').val(data);	        	 
	        	  alert("Data Saved SuccesFully")
	          }
	          else
	        	  alert(data);
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});
}
$("input#unit_name").keypress(function() {
	var unit_name = this.value;
	var susNoAuto = $("#unit_name");
	susNoAuto.autocomplete({
		source : function(request, response) {
			$.ajax({
				type : 'POST',
				url : "getTargetUnitsNameActiveList?" + key + "=" + value,
				data : {
					unit_name : unit_name
				},
				success : function(data) {
					var susval = [];
					var length = data.length - 1;
					var enc = data[length].substring(0, 16);
					for (var i = 0; i < data.length; i++) {
						susval.push(dec(enc, data[i]));
					}

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
				alert("Please Enter Approved Unit Name.");
				document.getElementById("unit_name").value = "";
				susNoAuto.val("");
				susNoAuto.focus();
				return false;
			}
		},
		select : function(event, ui) {
			var unit_name = ui.item.value;
			$.post("getTargetSUSFromUNITNAME?" + key + "=" + value, {
				target_unit_name : unit_name
			}, function(data) {
			}).done(function(data) {
				var length = data.length - 1;
				var enc = data[length].substring(0, 16);
				$("#dis_sus").val(dec(enc, data[0]));
			}).fail(function(xhr, textStatus, errorThrown) {
			});
		}
	});
});

//*************************************END DISCIPLINE *****************************//


///////////allergic///////////////
aller=1;
aller_srno=1;
function allergic_add_fn(q){
	 if( $('#allergic_add'+q).length )         
	 {
			$("#allergic_add"+q).hide();
	 }
	 aller=q+1;
	if(q!=0)
		aller_srno+=1;
	$("table#allergic_table").append('<tr id="tr_allergic'+aller+'">'
			+'<td class="proex_srno" style="width: 2%;">'+aller_srno+'</td>'
			+'<td><input type="text" id="allergic'+aller+'" name="allergic'+aller+'"'
			+'class="form-control autocomplete" autocomplete="off">'
			+'</td>'
			+'<td style="display:none;"><input type="text" id="allergic_ch_id'+aller+'" name="allergic_ch_id'+aller+'"  value="0" class="form-control autocomplete" autocomplete="off" ></td>'
			+'<td style="width: 10%;">'
			+'<a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "allergic_save'+aller+'" onclick="allergic_save_fn('+aller+');" ><i class="fa fa-save"></i></a>'
			+'<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "allergic_add'+aller+'" onclick="allergic_add_fn('+aller+');" ><i class="fa fa-plus"></i></a>'
			+'<a style="display:none;" class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "allergic_remove'+aller+'" onclick="allergic_remove_fn('+aller+');"><i class="fa fa-trash"></i></a> '
			+'</td></tr>');
}

function allergic_remove_fn(R){
	var rc = confirm("Are You Sure! You Want To Delete");
	 if(rc){
	 var allergic_ch_id=$('#allergic_ch_id'+R).val();
	  $.post('update_offr_allergic_delete_action?' + key + "=" + value, {allergic_ch_id:allergic_ch_id }, function(data){ 
			   if(data=='1'){
					 $("tr#tr_allergic"+R).remove(); 
							 if(R==aller){
								 R = R-1;
								 var temp=true;
								 for(i=R;i>=1;i--){
								 if( $('#allergic_add'+i).length )         
								 {
									 $("#allergic_add"+i).show();
									 temp=false;
									 aller=i;
									 break;
								 }}
						 
							 if(temp){
								 allergic_add_fn(0);
								}
			  			 }
							 $('.proex_srno').each(function(i, obj) {
								 
									obj.innerHTML=i+1;
									aller_srno=i+1;
									 });
							 alert("Data Deleted SuceessFully");
			   }
				 else{
					 alert("Data not Deleted ");
				 }
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});
	 }
}
function allergic_save_fn(ps){
	var allergic=$('#allergic'+ps).val();
	var allergic_ch_id=$('#allergic_ch_id'+ps).val();
	var p_id=$('#census_id').val();
	var com_id=$('#comm_id').val();

	if (allergic == "") {
		alert("Please Enter Allergy");
		$("input#allergic"+ps).focus();
		return false;
	}
	
	  $.post('update_offr_allergic_detail_action?' + key + "=" + value, {allergic:allergic,allergic_ch_id:allergic_ch_id,p_id:p_id,com_id:com_id }, function(data){
	          if(data=="update")
	        	  alert("Data Updated Successfully");
	          else if(parseInt(data)>0){
	        	 $('#allergic_ch_id'+ps).val(data);
	        	 $("#allergic_add"+ps).show();
	        	 $("#allergic_remove"+ps).show();
	        	  alert("Data Saved SuccesFully")
	          }
	          else
	        	  alert(data)
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});
}

function get_allergic_details(){
	var cen_id=$('#census_id').val(); 
	var comm_id=$('#comm_idV').val();
	var i=0;
	 $.post('getHisAllergyData?' + key + "=" + value, {comm_id:comm_id,cen_id:cen_id,cancel_status:cancel_status}, function(j){
			var v=j.length;
			$('#allergictbody').empty(); 
			 if(v!=0){
	              
					for(i;i<v;i++){
			     		cor=i+1;
			     		
			     		var dmod=convertDateFormate(j[i].modified_date);
			     		
						$("table#allergic_table").append('<tr id="tr_allergic'+cor+'">'
			                     +'<td class="anm_srno">'+cor+'</td>'
			                     +'<td style="width: 10%;">'+j[i].medicine+'</td>'
			                     +'<td style="width: 10%;">'+j[i].modified_by+'</td>'
			                     +'<td style="width: 10%;">'+dmod+'</td>'
			                     +'<td style="display:none;"><input type="text" id="allergic_ch_id'+cor+'" name="allergic_ch_id'+cor+'"  value="' + j[i].id + '" class="form-control autocomplete" autocomplete="off" ></td>'
			                     +'<td style="width: 10%;"><a  class="btn btn-danger btn-sm" value="REMOVE" title = "REJECT" '+j[i].action+'><i class="fa fa-times"></i></a>'
			                     +'</td></tr>');
			}
			 }	
	  });
	
}



</script>
<script>

/////////////search subject////////////////////////



$("input[type='checkbox'][name='multisub']").click(function() {
  // access properties using this keyword
  var num=0;
  $("input[type='checkbox'][name='multisub']").each(function () {  
      if ( this.checked ) {     
          num=num+1
      }
      
  });
  if(num!=0)
      $("#sub_quali option:first").text('Subjects('+num+')');
  else
      $("#sub_quali option:first").text("Select Subjects");
});
$("#quali_sub_search").keyup(function () {
  var re = new RegExp($(this).val(), "i")
  $('.quali_subjectlist').each(function () {
      var text = $(this).text(),
          matches = !! text.match(re);
      $(this).toggle(matches)
  })
});


$("input[type='checkbox'][name='multisub']").click(function() {
    // access properties using this keyword
    var num=0;
    $('#quali_sub_list').empty()
    $("input[type='checkbox'][name='multisub']").each(function () {  
        if ( this.checked ) {   
        	
        	$('#quali_sub_list').append("<span class='subspan'>"+this.parentElement.innerText+"<i class='fa fa-times' style='margin: 5px;  font-size: 15px;' onclick='removeSubFn("+this.value+")'></i><span> <br>");
            num=num+1;
        }
        
    });
    if(num!=0)
        $("#sub_quali option:first").text('Subjects('+num+')');
    else
        $("#sub_quali option:first").text("Select Subjects");
});



function removeSubFn(value){
	
	$("input[type='checkbox'][name='multisub'][value='" + value + "']").attr('checked', false);
	
	var num=0;
    $('#quali_sub_list').empty()
    $("input[type='checkbox'][name='multisub']").each(function () {  
        if ( this.checked ) {   
        	
        	$('#quali_sub_list').append("<span class='subspan'>"+this.parentElement.innerText+"<i class='fa fa-times' style='margin: 5px;  font-size: 15px;' onclick='removeSubFn("+this.value+")'></i><span> <br>");
            num=num+1;
        }
        
    });
    if(num!=0)
        $("#sub_quali option:first").text('Subjects('+num+')');
    else
        $("#sub_quali option:first").text("Select Subjects");
	
    
}


$("input[type='checkbox'][name='spouse_multisub']").click(function() {
  // access properties using this keyword
  var num=0;
  $("input[type='checkbox'][name='spouse_multisub']").each(function () {  
      if ( this.checked ) {     
          num=num+1
      }
      
  });
  if(num!=0)
      $("#spouse_sub_quali option:first").text('Subjects('+num+')');
  else
      $("#spouse_sub_quali option:first").text("Select Subjects");
});
$("#spouse_searchSubject").keyup(function () {
  var re = new RegExp($(this).val(), "i")
  $('.spouse_subjectlist').each(function () {
      var text = $(this).text(),
          matches = !! text.match(re);
      $(this).toggle(matches)
  })
});
</script>

<script>
  
  /*///////////////// Start Deserter ////////////////////  */ 

function ArmType(){
	var arm_type=$("#arm_type").val();
 	if(arm_type =='1' ){
 		  $("#div_weapon").show(); 
	 }
	else{
 		$("#div_weapon").hide(); 
 		$('#arm_type_weapon').val("");
 	}
	
}
function DesertionCause(){
	var desertion_cause=$("#desertion_cause").val();
 	if(desertion_cause =='3' ){
 		  $("#div_cause").show(); 
	 }
	else{
 		$("#div_cause").hide(); 
 		$('#ot_desertion_cause').val("");
 	}
	
}

//save
function validate_Deserter_save_fn(){
	var deserter=$('#deserter').val();
	var arm_type=$('#arm_type').val();
	var arm_type_weapon=$('#arm_type_weapon').val();
	var dt_desertion=$('#dt_desertion').val();
	var dt_recovered=$('#dt_recovered').val();
	var desertion_cause=$('#desertion_cause').val();
	var indl_class=$('#indl_class').val();
	var ot_desertion_cause=$('#ot_desertion_cause').val();
	var des_ch_id=$('#des_ch_id').val();
	
	/* if(deserter == null || deserter=="0"){ 
			alert("Please Select The Deserter");
			$("select#deserter").focus();
			return false;
		}  */
	if(arm_type == null || arm_type=="0"){ 
		alert("Please Select The Arm Type");
		$("select#arm_type").focus();
		return false;
		} 
	if(arm_type=="1") { 
		if(arm_type_weapon == null || arm_type_weapon==""){
			alert( "Please Enter Weapon ");
			$("input#arm_type_weapon").focus();
			return false;
			}
		}
	if (dt_desertion == "" || dt_desertion == "DD/MM/YYYY") {
		alert("Please Select Date of Desertion");
		$("input#dt_desertion").focus();
		return false;
		}
	if (dt_recovered == "" || dt_recovered == "DD/MM/YYYY") {
		alert("Please Select Date of Recovered");
		$("input#dt_recovered").focus();
		return false;
		}
	if(desertion_cause == null || desertion_cause=="0"){ 
		alert("Please Select The Desertaon Cause");
		$("select#desertion_cause").focus();
		return false;
	} 
	if(desertion_cause=="3") {
		if(ot_desertion_cause == null || ot_desertion_cause==""){
			alert( "Please Enter Other ");
			$("input#ot_desertion_cause").focus();
			return false;
			}
		}
	if(indl_class == null || indl_class==""){ 
		alert("Please Enter The Indl Class");
		$("input#indl_class").focus();
		return false;
	} 
	
	    $.post('deserterAction?' + key + "=" + value, {
		   deserter:deserter,
		   arm_type:arm_type,
		   arm_type_weapon:arm_type_weapon,
		   dt_desertion:dt_desertion,
		   dt_recovered:dt_recovered,
		   desertion_cause:desertion_cause,
		   indl_class:indl_class,
		   ot_desertion_cause:ot_desertion_cause,
		   des_ch_id:des_ch_id
		   }, function(data){
	          if(data=="update")
	        	  alert("Data Updated Successfully");
	          else if(parseInt(data)>0){
	        	 $('#des_ch_id').val(data);
	        	 alert("Data Saved SuccesFully")
	          }
	          else
	        	  alert(data);
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		}); 
}

//get
function get_deserter(){
	
	 var cen_id=$('#census_id').val(); 
	 var comm_id=$('#comm_idV').val(); 
	 var i=0;
	 $.post('getHisDeserterData?' + key + "=" + value, {comm_id:comm_id,cen_id:cen_id,cancel_status:cancel_status}, function(j){
		 var v=j.length;
		 $('#deserter_tbody').empty(); 
	     if(v!=0){
	             
			for(i;i<v;i++){
	     		cor=i+1;
	     		
	     	
	     		
	     		var dc=j[i].desertion_cause;
	     		if(j[i].ot_desertion_cause !=null && j[i].ot_desertion_cause!='')
	     				dc=j[i].ot_desertion_cause;
	    		var atw='';
	    	
	    		if(j[i].arm_type_weapon !=null && j[i].arm_type_weapon!='')
					atw=j[i].arm_type_weapon;
	    	
	     		
	     		var dtd=convertDateFormate(j[i].dt_desertion);
	     		var dr=convertDateFormate(j[i].dt_recovered);		     		
	     		var ad=convertDateFormate(j[i].approved_date);
				$("table#deserter_table").append('<tr id="tr_deserter_cause'+cor+'">'
	                     +'<td class="anm_srno">'+cor+'</td>'
	                     +'<td style="width: 10%;">'+j[i].arm_type+'</td>'
	                     +'<td style="width: 10%;">'+atw+'</td>'
	                     +'<td style="width: 10%;">'+dtd+'</td>'
	                     +'<td style="width: 10%;">'+dr+'</td>'
	                     +'<td style="width: 10%;">'+j[i].recovered_arms+'</td>' 
	                     +'<td style="width: 10%;">'+dc+'</td>'
	                     +'<td style="width: 10%;">'+j[i].indl_class+'</td>'
	                    
	                     +'<td style="width: 10%;">'+j[i].approved_by+'</td>'
	                     +'<td style="width: 10%;">'+ad+'</td>'                    
	                     +'<td style="width: 10%;"><a  class="btn btn-danger btn-sm" value="REMOVE" title = "REJECT"  '+j[i].action+'><i class="fa fa-times"></i></a>'
	                     +'</td></tr>');

			}
		}
	 });
	} 


/*/////////////////////////  End Deserter ////////////////// */ 
 
////////CSD/////////////
function get_CSD_details(){
	 var cen_id=$('#census_id').val(); 
	 var comm_id=$('#comm_idV').val(); 
	 var i=0;
	 $.post('getHisCSDData?' + key + "=" + value, {comm_id:comm_id,cen_id:cen_id,cancel_status:cancel_status}, function(j){
		 var v=j.length;
		 $('#csd_tbody').empty(); 
	     if(v!=0){
	             
			for(i;i<v;i++){
	     		cor=i+1;
	     		
	     	
	     		
	     		
	    	
	     		
	     		var mod=convertDateFormate(j[i].modified_date);
	     		var dob=convertDateFormate(j[i].date_of_birth);		     		
	     	
				$("table#csd_table").append('<tr id="tr_csdr_cause'+cor+'">'
	                     +'<td class="anm_srno">'+cor+'</td>'
	                     +'<td style="width: 10%;">'+j[i].relation+'</td>'
	                     +'<td style="width: 10%;">'+j[i].name+'</td>'
	                     +'<td style="width: 10%;">'+dob+'</td>'
	                     +'<td style="width: 10%;">'+j[i].type_of_card+'</td>'
	                     +'<td style="width: 10%;">'+j[i].card_no+'</td>'                                   
	                     +'<td style="width: 10%;">'+j[i].modified_by+'</td>'
	                     +'<td style="width: 10%;">'+mod+'</td>'                    
	                     +'<td style="width: 10%;"><a  class="btn btn-danger btn-sm" value="REMOVE" title = "REJECT"  '+j[i].action+'><i class="fa fa-times"></i></a>'		                    
	                     +'</td></tr>');

			}
		}
	 });
}
</script>

<script>
  
  //////////////////////// Date //////////////////////

jQuery(function($){ //on document.ready  
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
	 datepicketDate('award_dt');
	 datepicketDate('inter_arm_date_of_authority');
	 datepicketDate('inter_arm_with_effect_from');
	 datepicketDate('coc_date_of_authority');
	 datepicketDate('coc_date_of_permanent_commission');
	 datepicketDate('coc_date_of_seniority');
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
	 
	 
	 datepicketDate('1bx_from_date');
	 datepicketDate('1bx_to_date');
	 $( "#1bx_to_date" ).datepicker( "option", "maxDate", null);
	
	 $( "#s_to_date1" ).datepicker( "option", "maxDate", null);
	 $( "#h_to_date1" ).datepicker( "option", "maxDate", null);
	 $( "#a_to_date1" ).datepicker( "option", "maxDate", null);
	 $( "#p_to_date1" ).datepicker( "option", "maxDate", null);
	 $( "#e_to_date1" ).datepicker( "option", "maxDate", null);
	 // medical ends
	 
	 datepicketDate('dt_desertion');
	 datepicketDate('dt_recovered');
	 if(clickrecall )
	 var currentDate = new Date();  
	 $("#dt_recovered").datepicker("setDate",currentDate);
	 
	 
});
  

</script>

<script>

function CancelHisChangeOfRankData(id){
	
	
	$.post('CancelHisChangeOfRankData?' + key + "=" + value,{id:id,set_status:set_status}, function(j){
		
	if(j==0){
		alert("Unable to Reject");
	}
	if(j==1){
		alert("Data Rejected Successfully!");
		getChangeOfRank();
	}
		
	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
}

function CancelHisChangeOfNameData(id){
	
	
	$.post('CancelHisChangeOfNameData?' + key + "=" + value,{id:id,set_status:set_status}, function(j){
		
	if(j==0){
		alert("Unable to Reject");
	}
	if(j==1){
		alert("Data Rejected Successfully!");
		get_change_of_name();
	}
		
	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
}

function CancelHisChangeInAppointment(id){
	
	
	$.post('CancelHisChangeInAppointment?' + key + "=" + value,{id:id,set_status:set_status}, function(j){
		
	if(j==0){
		alert("Unable to Reject");
	}
	if(j==1){
		alert("Data Rejected Successfully!");
		get_Appointment();
	}
		
	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
}

function CancelHisChangeIdentityCard(id){
	
	
	$.post('CancelHisChangeIdentityCard?' + key + "=" + value,{id:id,set_status:set_status}, function(j){
		
	if(j==0){
		alert("Unable to Reject");
	}
	if(j==1){
		alert("Data Rejected Successfully!");
		getIdentity_Card();
	}
		
	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
}

function CancelHisChangeInReligion(id){
	
	
	$.post('CancelHisChangeInReligion?' + key + "=" + value,{id:id,set_status:set_status}, function(j){
		
	if(j==0){
		alert("Unable to Reject");
	}
	if(j==1){
		alert("Data Rejected Successfully!");
		get_religion();
	}
		
	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
}

function CancelHisMarriage(id){
	
	
	$.post('CancelHisMarriage?' + key + "=" + value,{id:id,set_status:set_status}, function(j){
		
	if(j==0){
		alert("Unable to Reject");
	}
	if(j==1){
		alert("Data Rejected Successfully!");
		getfamily_marriageDetails();
	}
		
	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
}

function CancelHisSpouseQualification(id){
	
	
	$.post('CancelHisSpouseQualification?' + key + "=" + value,{id:id,set_status:set_status}, function(j){
		
	if(j==0){
		alert("Unable to Reject");
	}
	if(j==1){
		alert("Data Rejected Successfully!");
		getSpouseQualificationData();
	}
		
	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
}

function CancelHisChildDetails(id){
	
	
	$.post('CancelHisChildDetails?' + key + "=" + value,{id:id,set_status:set_status}, function(j){
		
	if(j==0){
		alert("Unable to Reject");
	}
	if(j==1){
		alert("Data Rejected Successfully!");
		m_children_Details();
	}
		
	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
}

function CancelHisNokDetails(id){
	
	
	$.post('CancelHisNokDetails?' + key + "=" + value,{id:id,set_status:set_status}, function(j){
		
	if(j==0){
		alert("Unable to Reject");
	}
	if(j==1){
		alert("Data Rejected Successfully!");
		get_nokaddress_details();
	}
		
	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
}

function CancelHisAddressDetails(id){
	
	if(confirm("This Will Reject Present Domicile, Permanent Address And Present Address.")){ 
	$.post('CancelHisAddressDetails?' + key + "=" + value,{id:id,set_status:set_status}, function(j){
		
	if(j==0){
		alert("Unable to Reject");
	}
	if(j==1){
		alert("Data Rejected Successfully!");
		get_changeinaddress_details();
	}
		
	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
	}
}

function CancelHisQualification(id){
	
	
	$.post('CancelHisQualification?' + key + "=" + value,{id:id,set_status:set_status}, function(j){
		
	if(j==0){
		alert("Unable to Reject");
	}
	if(j==1){
		alert("Data Rejected Successfully!");
		getQualifications();
	}
		
	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
}

function CancelHisLanguage(id){
	
	
	$.post('CancelHisLanguage?' + key + "=" + value,{id:id,set_status:set_status}, function(j){
		
	if(j==0){
		alert("Unable to Reject");
	}
	if(j==1){
		alert("Data Rejected Successfully!");
		get_language_details();
		get_foreign_language_details();
	}
		
	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
}

function CancelHisContactDetails(id){
	
	
	$.post('CancelHisContactDetails?' + key + "=" + value,{id:id,set_status:set_status}, function(j){
		
	if(j==0){
		alert("Unable to Reject");
	}
	if(j==1){
		alert("Data Rejected Successfully!");
		get_cda_acnt_no();
	}
		
	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
}

function CancelHisPromotionalExam(id){
	
	
	$.post('CancelHisPromotionalExam?' + key + "=" + value,{id:id,set_status:set_status}, function(j){
		
	if(j==0){
		alert("Unable to Reject");
	}
	if(j==1){
		alert("Data Rejected Successfully!");
		get_promo_exam_details();
	}
		
	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
}

function CancelHisArmyCourse(id){
	
	
	$.post('CancelHisArmyCourse?' + key + "=" + value,{id:id,set_status:set_status}, function(j){
		
	if(j==0){
		alert("Unable to Reject");
	}
	if(j==1){
		alert("Data Rejected Successfully!");
		get_army_course_details();
	}
		
	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
}

function CancelHisBPET(id){
	
	
	$.post('CancelHisBPET?' + key + "=" + value,{id:id,set_status:set_status}, function(j){
		
	if(j==0){
		alert("Unable to Reject");
	}
	if(j==1){
		alert("Data Rejected Successfully!");
		bpet_Details();
	}
		
	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
}

function CancelHisFiringStandard(id){
	
	
	$.post('CancelHisFiringStandard?' + key + "=" + value,{id:id,set_status:set_status}, function(j){
		
	if(j==0){
		alert("Unable to Reject");
	}
	if(j==1){
		alert("Data Rejected Successfully!");
		fire_Details();
	}
		
	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
}

function CancelHisAllergy(id){
	
	
	$.post('CancelHisAllergy?' + key + "=" + value,{id:id,set_status:set_status}, function(j){
		
	if(j==0){
		alert("Unable to Reject");
	}
	if(j==1){
		alert("Data Rejected Successfully!");
		get_allergic_details();
	}
		
	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
}

function CancelHisForeignCountry(id){
	
	
	$.post('CancelHisForeignCountry?' + key + "=" + value,{id:id,set_status:set_status}, function(j){
		
	if(j==0){
		alert("Unable to Reject");
	}
	if(j==1){
		alert("Data Rejected Successfully!");
		getUPForeign_CountriesDetails();
	}
		
	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
}


function CancelHisAwardsNmedal(id){
	
	
	$.post('CancelHisAwardsNmedal?' + key + "=" + value,{id:id,set_status:set_status}, function(j){
		
	if(j==0){
		alert("Unable to Reject");
	}
	if(j==1){
		alert("Data Rejected Successfully!");
		getawardsNmedals();
	}
		
	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
}


function CancelHisBatt_phy_casuality(id,con){
	
	var comm_id=$('#comm_idV').val();
	$.post('CancelHisBatt_phy_casuality?' + key + "=" + value,{id:id,set_status:set_status,comm_id:comm_id,con:con}, function(j){
		
	if(j==0){
		alert("Unable to Reject");
	}
	if(j==1){
		alert("Data Rejected Successfully!");
		get_Battle_and_Physical_Casuality_details();
	}
		
	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
}

function CancelHisDiscipline(id){
	
	
	$.post('CancelHisDiscipline?' + key + "=" + value,{id:id,set_status:set_status}, function(j){
		
	if(j==0){
		alert("Unable to Reject");
	}
	if(j==1){
		alert("Data Rejected Successfully!");
		get_change_of_Discipline();
	}
		
	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
}

function CancelHisInter_arm_transfer(id){
	
	
	$.post('CancelHisInter_arm_transfer?' + key + "=" + value,{id:id,set_status:set_status}, function(j){
		
	if(j==0){
		alert("Unable to Reject");
	}
	if(j==1){
		alert("Data Rejected Successfully!");
		getInterArm();
	}
		
	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
}

function CancelHisChange_of_commission(id){
	
	
	$.post('CancelHisChange_of_commission?' + key + "=" + value,{id:id,set_status:set_status}, function(j){
		
	if(j==0){
		alert("Unable to Reject");
	}
	if(j==1){
		alert("Data Rejected Successfully!");
		getcoc();
	}
		
	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
}

function CancelHisExtension_of_comission(id){
	
	
	$.post('CancelHisExtension_of_comission?' + key + "=" + value,{id:id,set_status:set_status}, function(j){
		
	if(j==0){
		alert("Unable to Reject");
	}
	if(j==1){
		alert("Data Rejected Successfully!");
		geteoc();
	}
		
	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
}

function CancelHisSecondment(id){
	
	
	$.post('CancelHisSecondment?' + key + "=" + value,{id:id,set_status:set_status}, function(j){
		
	if(j==0){
		alert("Unable to Reject");
	}
	if(j==1){
		alert("Data Rejected Successfully!");
		get_Secondment();
	}
		
	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
}

function CancelHisNon_effective(id,con){
	
	var comm_id=$('#comm_idV').val();
	$.post('CancelHisNon_effective?' + key + "=" + value,{id:id,set_status:set_status,comm_id:comm_id,con:con}, function(j){
		
	if(j==0){
		alert("Unable to Reject");
	}
	if(j==1){
		alert("Data Rejected Successfully!");
		getNon_effective();
	}
		
	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
}

function CancelHisDeserter(id){
	
	
	$.post('CancelHisDeserter?' + key + "=" + value,{id:id,set_status:set_status}, function(j){
		
	if(j==0){
		alert("Unable to Reject");
	}
	if(j==1){
		alert("Data Rejected Successfully!");
		get_deserter();
	}
		
	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
}

function CancelHisCSD(id){
	var comm_id=$('#comm_idV').val();
	
	$.post('CancelHisCSD?' + key + "=" + value,{id:id,set_status:set_status,comm_id:comm_id}, function(j){
		
	if(j==0){
		alert("Unable to Reject");
	}
	if(j==1){
		alert("Data Rejected Successfully!");
		get_CSD_details();
	}
		
	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
}

</script>







 <script>
 function CancelHisMedicalDetails(id){
		
		
		$.post('CancelHisMedicalDetails?' + key + "=" + value,{id:id,set_status:set_status}, function(j){
			
		if(j==0){
			alert("Unable to Reject");
		}
		if(j==1){
			alert("Data Rejected Successfully!");
			getMedicalDetails();
		}
			
		  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
			});
	}

	function getMedicalDetails(){
	    var cen_id=$('#census_id').val(); 
	    var comm_id=$('#comm_idV').val();
	    var i=0;
	    
	                           $.post('getHisMedicalCategoryData?' + key + "=" + value, {comm_id:comm_id,cen_id:cen_id,cancel_status:cancel_status}, function(j){
	                        		 var v=j.length;
	                        		 $('#medicaltbody').empty(); 
	                        	     if(v!=0){
	                        	             
	                        			for(i;i<v;i++){
	                        	     		cor=i+1;
	                        	     		
	                        	     		var s_id=j[i].s_id;
	                        	     		
	                        	     		var h_id=j[i].h_id;
	                        	     		var a_id=j[i].a_id;
	                        	     		var p_id=j[i].p_id;
	                        	     		var e_id=j[i].e_id;
	                        	     		
	                        	     		var c_c_id=j[i].c_c_id;
	                        	     		var c_o_id=j[i].c_o_id;
	                        	     		var c_p_id=j[i].c_p_id;
	                        	     		var c_e_id=j[i].c_e_id;
	                        	     		var action=s_id+','+h_id+','+a_id+','+p_id+','+e_id+','+c_c_id+','+c_o_id+','+c_p_id+','+c_e_id;
	                        	     		var dauth=convertDateFormate(j[i].date_of_authority);
	                        	     		var daward=convertDateFormate(j[i].date_of_award);
	                        	     		var dmod=convertDateFormate(j[i].modify_on);
	                        				$("table#medical_table").append('<tr id="tr_medical'+cor+'">'
	                        	                     +'<td class="anm_srno">'+cor+'</td>'
	                        	                     +'<td style="width: 10%;">'+j[i].authority+'</td>'
	                        	                     +'<td style="width: 10%;">'+dauth+'</td>'
	                        	                     +'<td style="width: 10%;">'+j[i].s_value+'</td>'
	                        	                     +'<td style="width: 10%;">'+j[i].h_value+'</td>'
	                        	                     +'<td style="width: 10%;">'+j[i].a_value+'</td>'
	                        	                     +'<td style="width: 10%;">'+j[i].p_value+'</td>'
	                        	                     +'<td style="width: 10%;">'+j[i].e_value+'</td>'
	                        	                     
	                        	                     +'<td style="width: 10%;">'+j[i].c_c_value+'</td>'
	                        	                     +'<td style="width: 10%;">'+j[i].c_o_value+'</td>'
	                        	                     +'<td style="width: 10%;">'+j[i].c_p_value+'</td>'
	                        	                     +'<td style="width: 10%;">'+j[i].c_e_value+'</td>'
	                        	                     
	                        	                     
	                        	                
	                        	                     +'<td style="width: 10%;">'+j[i].modify_by+'</td>'
	                        	                     +'<td style="width: 10%;">'+dmod+'</td>'                    
	                        	                     +'<td style="width: 10%;"><i class="fa fa-eye"  onclick="viewMedicaldetailFn(\''+s_id+'\',\''+h_id+'\',\''+a_id+'\',\''+p_id+'\',\''+e_id+'\',\''+c_c_id+'\',\''+c_o_id+'\',\''+c_p_id+'\',\''+c_e_id+'\')" data-toggle="modal" data-target="#medicalModal" title="View Data"></i><a  class="btn btn-danger btn-sm" value="REMOVE" title = "REJECT"  onclick="CancelHisMedicalDetails(\''+action+'\')"><i class="fa fa-times"></i></a>'
	                        	                     +'</td></tr>');

	                        			}
	                        		}
	                        	 });
	                           
	                        	} 
	                        	
	                        	function viewMedicaldetailFn(s_id,h_id,a_id,p_id,e_id,c_c_id,c_o_id,c_p_id,c_e_id){
	                        		
	                        		$('#check_1bx').prop('checked', false);
	                        		
	                        		getsShapeDetails(s_id);
	                        		gethShapeDetails(h_id);
	                        		getaShapeDetails(a_id);
	                        		getpShapeDetails(p_id);
	                        		geteShapeDetails(e_id);
	                        		getcCopeDetails(c_c_id);
	                        		getoCopeDetails(c_o_id);
	                        		getpCopeDetails(c_p_id);
	                        		geteCopeDetails(c_e_id);
	}
	                        	
       	function myFunction() {
       		  var checkBox = document.getElementById("myCheck");
       		 
       		  if (checkBox.checked == true){
       			  $("#submit_a").show();
       			 
       		  } else {
       			  $("#submit_a").hide();
       			
       		  }
       	}
 </script>
 

