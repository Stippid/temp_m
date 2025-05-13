<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<!-- <link rel="stylesheet" href="js/assets/css/bootstrap.min.css"> -->
<link rel="stylesheet" href="js/layout/css/animation.css">
<!-- <link rel="stylesheet" href="js/assets/scss/style.css">  -->
<link rel="stylesheet" href="js/assets/collapse/collapse.css">

<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

<style>
.tab {
	margin-left: 200px;
}
td {
    text-transform: uppercase;
}



.tablinks {
	outline: 0 !important;
	background-color: #cccccc;
	color: black;
	font-size: 14px;
	padding: 10px 5px;
	/*     border-radius: 5px;  */
	font-family: system-ui;
	border: none;
	margin: 5px 5px;
	border-right: 2px solid black;
	border-left: 2px solid black;
}

button.tablinks.active {
	background-color: #343a40 !important;
	color: white;
	box-shadow: 5px 10px 8px #888888 !important;
	border-right: 2px solid white;
	border-bottom: 2px solid white;
}

.card-header {
	margin-bottom: 0;
	background-color: #cccccc;
	border-bottom: 1px solid #cccccc;
	padding: 8px;
}

.card-body.card-block {
	text-align: left;
	background-color: #cccccc21;
}

.card .card-footer {
	padding: .65rem 1.25rem;
	background-color: #cccccc;
	border-top: 1px solid #c2cfd6;
	position: relative;
}

label {
	word-break: break-word;
	text-transform: uppercase;
	
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
  function divN(obj){
		var id = obj.id;
		 var sib_id = $("#"+id).parent().parent().next().attr('id');
		var hasC=$("#"+sib_id).hasClass("show");
			
			if (hasC) {	
			$("#"+id).addClass( " collapsed");	
			$("#"+sib_id).removeClass("show");
			}  else {				
				$("#"+sib_id).addClass(" show");	
				$("#"+id).removeClass("collapsed");
		    }
  }
  </script>


<form:form name="Edit_Census_Detail" id="Edit_Census_Detail"
	action="Edit_Census_DetailAction" method="post" class="form-horizontal"
	commandName="View_Census_DetailCMD">
	<div class="container-fluid card-header" style="padding-bottom: 10px;"
		align="center">
		<h5>Service Record</h5>
		<label class=" form-control-label"> Last Updated On </label> <label
			id="modified_date" name="modified_date">${list[0].modified_date}</label>

		<label style="right: 5%; position: absolute;" id="serving_status"
			name="serving_status">${serving_status}</label>

		<h6 class="enter_by">
			<span style="font-size: 12px; color: red;"></span>
		</h6>
	</div>
	<div class="animated fadeIn">
		<div class="container-fluid" align="center">
			<div class="card">
				<input type="hidden" id="hid_comm_status" /> <input type="hidden"
					id="hid_sus_no" />
				<div class="panel-group" id="accordion5">
					<div class="panel panel-default" id="b_div1">
						<div class="panel-heading">
							<h4 class="panel-title main_title red" id="b_div5">
								<a class="droparrow collapsed" data-toggle="collapse"
									data-parent="#accordion5" href="#" id="a_final"
									onclick="divN(this)"><b>Personal Details</b></a>
							</h4>
						</div>
						<div id="collapse1a" class="panel-collapse collapse">
							<div class="card-body card-block">

								<div class="row">
									<div class="col-md-12">
										


	                                    <div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"> PERSONAL NO</label>
												</div>
												<div class="col-md-8">
													<label id="personnel_no" name="personnel_no" value="0">${list[0].personnel_no}</label>
													<label class=" form-control-label" id="course"></label>
												</div>
											</div>
										</div>

										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"> NAME</label>
												</div>
												<div class="col-md-4">
													<label id="name" name="name">${list[0].name}</label>
												</div>
												<div class="col-md-4">
													<img id="identity_image_preview" alt="No Image"
														src="js/img/No_Image.jpg" width="75" height="75" />

												</div>
											</div>
										</div>
									</div>

									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"> RANK</label>
												</div>
												<div class="col-md-8">
													<label id="rank" name="rank">${list[0].rank}</label>
												</div>
											</div>
										</div>

										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label">DATE OF RANK</label>
												</div>
												<div class="col-md-8">
													<label id="date_of_rank" name="date_of_rank">${list[0].date_of_rank}</label>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"> REGIMENT/CORPS</label>
												</div>
												<div class="col-md-8">
													<label id="regiment" value="regiment">
														${list[0].regiment}</label>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"> COMMISSION TYPE</label>
												</div>
												<div class="col-md-8">
													<label id="comn_name" name="comn_name">${list[0].comn_name}</label>
												</div>
											</div>
										</div>
									</div>

									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label">DATE OF
														COMMISSION</label>
												</div>
												<div class="col-md-8">
													<label id="date_of_commission" name="date_of_commission">${list[0].date_of_commission}
													</label>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label">DATE OF
														SENIORITY</label>
												</div>
												<div class="col-md-8">
													<label name="date_of_seniority" id="date_of_seniority">${list[0].date_of_seniority}</label>


												</div>
											</div>
										</div>
									</div>

									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label">APPOINTMENT</label>
												</div>
												<div class="col-md-8">
													<label name="appointment" id="appointment">${list[0].appointment}
													</label>


												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"> DATE OF
														APPOINTMENT</label>
												</div>
												<div class="col-md-8">
													<label name="date_of_appointment" id="date_of_appointment">${list[0].date_of_appointment}</label>


												</div>
											</div>
										</div>
									</div>

									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"> UNIT NAME</label>
												</div>
												<div class="col-md-8">
													<label name="unit_name" id="unit_name">${list[0].unit_name}</label>


												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label">DATE OF TOS</label>
												</div>
												<div class="col-md-8">
													<label id="dt_of_tos">${list[0].dt_of_tos}</label>

												</div>
											</div>
										</div>
									</div>

									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"> DATE OF BIRTH</label>
												</div>
												<div class="col-md-8">
													<label name="date_of_birth" id="date_of_birth">${list[0].date_of_birth}</label>

												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"> CDA(O) A/C No</label>
												</div>
												<div class="col-md-8">
													<label id="cda_acc_no">${list[0].cda_acc_no}</label>

												</div>
											</div>
										</div>

									</div>
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"> GENDER</label>
												</div>
												<div class="col-md-8">
													<label name="gender_name" id="gender_name">${list[0].gender_name}</label>

												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"> RELIGION</label>
												</div>
												<div class="col-md-8">
													<label name="religion_name" id="religion_name">${list[0].religion_name}</label>

												</div>
											</div>
										</div>

									</div>
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"> NATIONALITY</label>
												</div>
												<div class="col-md-8">
													<label name="nationality_name" id="nationality_name">${list[0].nationality_name}</label>

												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label">STATE</label>
												</div>
												<div class="col-md-8">
													<label id="state_name">${list[0].state_name} </label>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"> MOTHER TONGUE</label>
												</div>
												<div class="col-md-8">
													<label id="mothertounge">${list[0].mothertounge}</label>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label">AADHAAR NO</label>
												</div>
												<div class="col-md-8">
													<label id="aadhar_no1"></label>
													<label id="aadhar_no2"></label>
													<label id="aadhar_no3"></label>

												</div>
											</div>
										</div>

									</div>
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"> BLOOD GROUP</label>
												</div>
												<div class="col-md-8">
													<label name="blood_desc" id="blood_desc">${list[0].blood_desc}</label>

												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label">HEIGHT</label>
												</div>
												<div class="col-md-8">
													<label name="height" id="height">${list[0].height}</label>

												</div>
											</div>
										</div>
									</div>
									<div class="col-md-12">
									<div class="col-md-4">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label">OLD PERSONAL NO</label>
												</div>
												<div class="col-md-8">
													<label id="personnel_no" name="personnel_no" value="0">${list[0].old_personal_no}</label>
												</div>
											</div>
										</div>
											</div>
									
									<%-- 				
	              				
	              <div class="card-header-inner" id="aa" > <strong>Medical Category</strong></div> 	
	              				
					<table id="" style="margin-top: 10px; width: 100%;">
						<thead style="color: white; text-align: center;">
							<tr>
							<c:forEach var="item" items="${listSH}" varStatus="num" >					
									<th style="color: black; background-color: white; ">  ${item.shape}   ${item.shape_value} </th>		
															
								</c:forEach>
								</tr>
								<tr>
								<c:forEach var="item" items="${listCO}" varStatus="num" >					
									<th style="color: black; background-color: white; ">  ${item.shape}   ${item.shape_value} </th>																	
								</c:forEach>							
							</tr>
						</thead>
					</table> --%>

									<div class="card-header-inner" id="aa"
										style="text-align: center;">
										<strong>Medical Category</strong>
									</div>

									<table id="" class="table-bordered "
										style="margin-top: 10px; width: 100%;">
										<thead style="color: white; text-align: center;">
											<tr>
												<c:forEach var="item" items="${listSH}" varStatus="num">
													<th style="color: black; background-color: white;">
														${item.shape}</th>
												</c:forEach>
											</tr>
											<tr>
												<c:forEach var="item" items="${listCO}" varStatus="num">
													<th style="color: black; background-color: white;">
														${item.shape}</th>
												</c:forEach>
											</tr>
										</thead>
									</table>





									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"> DATE OF MEDICAL
														CATEGORY</label>
												</div>
												<div class="col-md-8">
													<label name="medical" id="medical">${list[0].medical}</label>

												</div>
											</div>
										</div>

										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"> MARITAL STATUS</label>
												</div>
												<div class="col-md-8">
													<label name="marital_name" id="marital_name">${list[0].marital_name}</label>

												</div>
											</div>
										</div>
									</div>
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"> I Card </label>
												</div>
												<div class="col-md-8">
													<label id="id_card_no" name="id_card_no">${list[0].id_card_no}</label>

												</div>
											</div>
										</div>

										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label">DATE OF I Card
														ISSUED</label>
												</div>
												<div class="col-md-8">
													<label id="issue_dt" name="issue_dt">${list[0].issue_dt}
													</label>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"> EMAIL</label>
												</div>
												<div class="col-md-8">
													<label id="gmail" name="gmail">${list[0].gmail}</label>

												</div>
											</div>
										</div>

										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label">MOBILE NO</label>
												</div>
												<div class="col-md-8">
													<label id="mobile_no" name="mobile_no">${list[0].mobile_no}
													</label>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label">NIC MAIL</label>
												</div>
												<div class="col-md-8">
													<label id="remarks" name="remarks">${list[0].nic_mail}</label>

												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label">DATE OF
														PERMANENT COMMISSION</label>
												</div>
												<div class="col-md-8">
													<label id="mobile_no" name="mobile_no">${list[0].date_of_permanent_commission}
													</label>
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

	<div class="animated fadeIn">
		<div class="container-fluid" align="center">
			<div class="card">
				<div class="panel-group" id="accordion5">
					<div class="panel panel-default" id="b_div1">
						<div class="panel-heading">
							<h4 class="panel-title main_title red" id="b_div5">
								<a class="droparrow collapsed" data-toggle="collapse"
									data-parent="#accordion5" href="#" id="b_final"
									onclick="divN(this)"><b>Training Details</b></a>
							</h4>
						</div>
						<div id="collapse1b" class="panel-collapse collapse">
							<div class="card-body card-block">
								<div class="row">
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label">CADET NO</label>
												</div>
												<div class="col-md-8">
													<label id="cadet_no">${listTR[0].cadet_no}</label>
												</div>
											</div>
										</div>


										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label">NAME OF
														INSTITUTION</label>
												</div>
												<div class="col-md-8">
													<label id="institute_name">${listTR[0].institute_name}</label>
												</div>
											</div>
										</div>
									</div>

									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"> BATTALION</label>
												</div>
												<div class="col-md-8">
													<label id="battalion_name">${listTR[0].battalion_name}</label>
												</div>
											</div>
										</div>

										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label">COY</label>
												</div>
												<div class="col-md-8">
													<label id="coy">${listTR[0].company_name} </label>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"> COURSE</label>
												</div>
												<div class="col-md-8">
													<label id="company_name">${listTR[0].course_name}</label>
												</div>
											</div>
										</div>


										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label">COURSE NO</label>
												</div>
												<div class="col-md-8">
													<label id="batch_no">${listTR[0].batch_no}</label>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"> TYPE OF ENTRY</label>
												</div>
												<div class="col-md-8">
													<label id="type_of_entry">${listTR[0].type_of_entry}</label>
												</div>
											</div>
										</div>


										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"> PRE CADET
														STATUS</label>
												</div>
												<div class="col-md-8">
													<label id="pre_cadet_status">
														${listTR[0].pre_cadet_status}</label>
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
	<div class="animated fadeIn">
		<div class="container-fluid" align="center">
			<div class="card">
				<div class="panel-group" id="accordion5">
					<div class="panel panel-default" id="b_div1">
						<div class="panel-heading">
							<h4 class="panel-title main_title red" id="b_div5">
								<a class="droparrow collapsed" data-toggle="collapse"
									data-parent="#accordion5" href="#" id="c_final"
									onclick="divN(this)"><b>FAMILY DETAILS</b></a>
							</h4>
						</div>
						<div id="collapse1c" class="panel-collapse collapse">
							<div class="card-body card-block">

								<div class="row">
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;"> </strong> FATHER'S NAME </label>
												</div>
												<div class="col-md-8">
													<label id=father_name>${listFM[0].father_name}</label>

												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;"> </strong> FATHER'S DATE OF BIRTH</label>
												</div>
												<div class="col-md-8">
													<label id="father_dob">${listFM[0].father_dob}</label>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;"> </strong> MOTHER'S NAME</label>
												</div>
												<div class="col-md-8">
													<label id=mother_name>${listFM[0].mother_name}</label>

												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;"> </strong> MOTHER'S DATE OF BIRTH</label>
												</div>
												<div class="col-md-8">
													<label id="mother_dob">${listFM[0].mother_dob}</label>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="card-header-inner" id="aa">
									<strong>SPOUSE DETAILS</strong>
								</div>
								<div class="row">
									<table id="" class="table-bordered "
										style="margin-top: 10px; width: 100%;">
										<thead style="color: white; text-align: center;">
											<tr>
												<th style="width: 2%;">SR NO</th>
												<th style="width: 10%;">MARITAL STATUS</th>
												<th style="width: 10%;">MAIDEN NAME</th>
												<th style="width: 10%;">DATE OF MARRIAGE</th>
												<th style="width: 10%;">DATE OF BIRTH</th>
												<th style="width: 10%;">PLACE OF BIRTH</th>
												<th style="width: 10%;">NATIONALITY</th>
												<th style="width: 10%;">AADHAAR NO</th>
												<th style="width: 10%;">PAN</th>
												<th style="width: 10%;">DIVORCE DATE</th>
												<th style="width: 10%;">SERVICE/EX-SERVICE</th>
												<th style="width: 10%;">PERSONAL NO</th>
											</tr>

										</thead>
										<tbody data-spy="scroll" id=""
											style="min-height: 46px; max-height: 650px; text-align: center;">
											<c:forEach var="item" items="${listS}" varStatus="num">
												<tr>
													<td>${num.index+1}</td>
													<td>${item.marital_status}</td>
													<td>${item.maiden_name}</td>
													<td>${item.marriage_date}</td>
													<td>${item.date_of_birth}</td>
													<td>${item.place_of_birth}</td>
													<td>${item.nationality_name}</td>
													<td>${adhar}</td>
													<td>${item.pancard_number}</td>
													<td>${item.divorce_date}</td>
													<td>${item.spouse_service}</td>
													<td>${item.spouse_personal_no}</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
								<div class="card-header-inner" id="aa">
									<strong>CHILDREN DETAILS</strong>
								</div>
								<div class="row">
									<table id="" class="table-bordered "
										style="margin-top: 10px; width: 100%;">
										<thead style="color: white; text-align: center;">
											<tr>
												<th style="width: 2%;">SR NO</th>
												<th style="width: 20%;">NAME</th>
												<th style="width: 20%;">DATE OF BIRTH</th>
												<th style="width: 20%;">RELATIONSHIP</th>
											</tr>

										</thead>
										<tbody data-spy="scroll" id=""
											style="min-height: 46px; max-height: 650px; text-align: center;">

											<c:forEach var="item" items="${listCHILD}" varStatus="num">
												<tr>
													<td>${num.index+1}</td>
													<td>${item.name}</td>
													<td>${item.dob}</td>
													<td>${item.gender}</td>
												</tr>
											</c:forEach>


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
	<div class="animated fadeIn">
		<div class="container-fluid" align="center">
			<div class="card">
				<div class="panel-group" id="accordion5">
					<div class="panel panel-default" id="b_div1">
						<div class="panel-heading">
							<h4 class="panel-title main_title red" id="b_div5">
								<a class="droparrow collapsed" data-toggle="collapse"
									data-parent="#accordion5" href="#" id="d_final"
									onclick="divN(this)"><b>AWARDS AND MEDALS</b></a>
							</h4>
						</div>
						<div id="collapse1d" class="panel-collapse collapse">
							<div class="card-body card-block">

								<table id="" class="table-bordered "
									style="margin-top: 10px; width: 100%;">
									<thead style="color: white; text-align: center;">
										<tr>
											<th style="width: 2%;">SR NO</th>
											<th style="width: 20%;">AWARD/MEDAL TYPE</th>
											<th style="width: 20%;">AWARD MEDAL</th>
											<th style="width: 20%;">DATE OF AWARD/MEDAL</th>
											<th style="width: 20%;">UNIT CONCERNED</th>
										</tr>
									</thead>
									<tbody data-spy="scroll" id=""
										style="min-height: 46px; max-height: 650px; text-align: center;">
										<c:forEach var="item" items="${listAM}" varStatus="num">
											<tr>
												<td>${num.index+1}</td>
												<td>${item.medal_type}</td>
												<td>${item.award_medal}</td>
												<td>${item.date_of_award}</td>
												<td>${item.unit}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="animated fadeIn">
		<div class="container-fluid" align="center">
			<div class="card">
				<div class="panel-group" id="accordion5">
					<div class="panel panel-default" id="b_div1">
						<div class="panel-heading">
							<h4 class="panel-title main_title red" id="b_div5">
								<a class="droparrow collapsed" data-toggle="collapse"
									data-parent="#accordion5" href="#" id="e_final"
									onclick="divN(this)"><b>TENURE DETAILS</b></a>
							</h4>
						</div>
						<div id="collapse1e" class="panel-collapse collapse">
							<div class="card-body card-block">

								<table id="" class="table-bordered "
									style="margin-top: 10px; width: 100%;">
									<thead style="color: white; text-align: center;">
										<tr>
											<th style="width: 2%;">SR NO</th>

											<th style="width: 10%;">Unit Name</th>
											<th style="width: 10%;">DATE OF TOS</th>
											<th style="width: 10%;">DATE OF SOS</th>

											<th style="width: 10%;">PLACE</th>
											<th style="width: 10%;">UNIT LOC TYPE</th>
											<th style="width: 10%;">COMMAND</th>

											<th style="width: 10%;">TENURE (MONTHS)</th>
										</tr>

									</thead>
									<tbody data-spy="scroll" id=""
										style="min-height: 46px; max-height: 650px; text-align: center;">
										<c:forEach var="item" items="${listTENU}" varStatus="num">
											<tr>
												<td>${num.index+1}</td>

												<td>${item.unit_name}</td>
												<td>${item.dt_of_tos}</td>
												<td>${item.nextvalue}</td>


												<td>${item.place}</td>
												<td>${item.unit_loc_type}</td>
												<td>${item.command}</td>
												<td>${item.month}</td>

											</tr>
											<tr>
												<c:if test="${num.index+1 == listTENU.size()}">
													<c:forEach var="item1" items="${listTENU_T}"
														varStatus="num">
														<td colspan="7" style="width: 40%; text-align: center;"><B>Total</B></td>
														<td><B>${item1.Total_tenure} </B></td>
													</c:forEach>
												</c:if>

											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
<!-- 	bisag v1 220822 (as per instructed from miso) -->
<!-- 	<div class="animated fadeIn"> -->
<!-- 		<div class="container-fluid" align="center"> -->
<!-- 			<div class="card"> -->
<!-- 				<div class="panel-group" id="accordion5"> -->
<!-- 					<div class="panel panel-default" id="b_div1"> -->
<!-- 						<div class="panel-heading"> -->
<!-- 							<h4 class="panel-title main_title red" id="b_div5"> -->
<!-- 								<a class="droparrow collapsed" data-toggle="collapse" -->
<!-- 									data-parent="#accordion5" href="#" id="f_final" -->
<!-- 									onclick="divN(this)"><b>RANK AND APPOINTMENT</b></a> -->
<!-- 							</h4> -->
<!-- 						</div> -->
<!-- 						<div id="collapse1f" class="panel-collapse collapse"> -->
<!-- 							<div class="card-body card-block"> -->

<!-- 								<table id="" class="table-bordered " -->
<!-- 									style="margin-top: 10px; width: 100%;"> -->
<!-- 									<thead style="color: white; text-align: center;"> -->
<!-- 										<tr> -->
<!-- 											<th style="width: 2%;">SR NO</th> -->
<!-- 											<th style="width: 10%;">UNIT NAME</th> -->
<!-- 											<th style="width: 10%;">RANK</th> -->
<!-- 											<th style="width: 10%;">APPOINTMENT</th> -->
<!-- 											<th style="width: 10%;">DATE</th> -->
<!-- 											<th style="width: 10%;">PLACE</th> -->
<!-- 											<th style="width: 10%;">UNIT LOC TYPE</th> -->
<!-- 											<th style="width: 10%;">COMMAND</th> -->


<!-- 										</tr> -->

<!-- 									</thead> -->
<!-- 									<tbody data-spy="scroll" id="" -->
<!-- 										style="min-height: 46px; max-height: 650px; text-align: center;"> -->
<%-- 										<c:forEach var="item" items="${listRegimental}" --%>
<%-- 											varStatus="num"> --%>
<!-- 											<tr> -->
<%-- 												<td>${num.index+1}</td> --%>
<%-- 												<td>${item.unit_name}</td> --%>
<%-- 												<td>${item.rank}</td> --%>
<%-- 												<td>${item.appt}</td> --%>
<%-- 												<td>${item.dt}</td> --%>

<%-- 												<td>${item.place}</td> --%>
<%-- 												<td>${item.unit_loc_type}</td> --%>
<%-- 												<td>${item.command}</td> --%>

<!-- 											</tr> -->

<%-- 										</c:forEach> --%>
<!-- 									</tbody> -->
<!-- 								</table> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</div> -->
	<div class="animated fadeIn">
		<div class="container-fluid" align="center">
			<div class="card">
				<div class="panel-group" id="accordion5">
					<div class="panel panel-default" id="b_div1">
						<div class="panel-heading">
							<h4 class="panel-title main_title red" id="b_div5">
								<a class="droparrow collapsed" data-toggle="collapse"
									data-parent="#accordion5" href="#" id="g_final"
									onclick="divN(this)"><b>FIELD SERVICE</b></a>
							</h4>
						</div>
						<div id="collapse1g" class="panel-collapse collapse">
							<div class="card-body card-block">
								<div class="row">
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label">PEACE</label>
												</div>
												<div class="col-md-8">
													<label id="cadet_no">${peace_month} months</label>
												</div>
											</div>
										</div>


										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label">FIELD</label>
												</div>
												<div class="col-md-8">
													<label id="institute_name">${listFIS[0].field}
														months</label>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label">HIGH ALT</label>
												</div>
												<div class="col-md-8">
													<label id="cadet_no">${listFIS[0].high_alt} months</label>
												</div>
											</div>
										</div>


										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label">CI OPS</label>
												</div>
												<div class="col-md-8">
													<label id="institute_name">${listFIS[0].ci_ops}
														months</label>
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
	<div class="animated fadeIn">
		<div class="container-fluid" align="center">
			<div class="card">
				<div class="panel-group" id="accordion5">
					<div class="panel panel-default" id="b_div1">
						<div class="panel-heading">
							<h4 class="panel-title main_title red" id="b_div5">
								<a class="droparrow collapsed" data-toggle="collapse"
									data-parent="#accordion5" href="#" id="h_final"
									onclick="divN(this)"><b>ARMY COURSE</b></a>
							</h4>
						</div>
						<div id="collapse1h" class="panel-collapse collapse">
							<div class="card-body card-block">

								<table id="" class="table-bordered "
									style="margin-top: 10px; width: 100%;">
									<thead style="color: white; text-align: center;">
										<tr>
											<th style="width: 2%;">SR NO</th>
											<th style="width: 20%;">COURSE NAME</th>
											<th style="width: 20%;">Grade</th>
											<th style="width: 20%;">NAME OF INSTITUTE</th>
											<th style="width: 20%;">FROM DATE</th>
											<th style="width: 20%;">TO DATE</th>
										</tr>
									</thead>
									<tbody data-spy="scroll" id=""
										style="min-height: 46px; max-height: 650px; text-align: center;">

										<c:forEach var="item" items="${listARM}" varStatus="num">
											<tr>
												<td>${num.index+1}</td>
												<td>${item.course_name}</td>
												<td>${item.grade}</td>
												<td>${item.course_institute}</td>
												<td>${item.from_date}</td>
												<td>${item.to_date}</td>

											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="animated fadeIn">
		<div class="container-fluid" align="center">
			<div class="card">
				<div class="panel-group" id="accordion5">
					<div class="panel panel-default" id="b_div1">
						<div class="panel-heading">
							<h4 class="panel-title main_title red" id="b_div5">
								<a class="droparrow collapsed" data-toggle="collapse"
									data-parent="#accordion5" href="#" id="i_final"
									onclick="divN(this)"><b>PROMOTIONAL EXAM</b></a>
							</h4>
						</div>
						<div id="collapse1i" class="panel-collapse collapse">
							<div class="card-body card-block">

								<table id="" class="table-bordered "
									style="margin-top: 10px; width: 100%;">
									<thead style="color: white; text-align: center;">
										<tr>
											<th style="width: 2%;">SR NO</th>
											<th style="width: 20%;">EXAM</th>
											<th style="width: 20%;">MONTH AND YEAR OF EXAM</th>
										</tr>
									</thead>
									<tbody data-spy="scroll" id=""
										style="min-height: 46px; max-height: 650px; text-align: center;">

										<c:forEach var="item" items="${listPE}" varStatus="num">
											<tr>
												<td>${num.index+1}</td>
												<td>${item.exam}</td>
												<td>${item.date_of_passing}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="animated fadeIn">
		<div class="container-fluid" align="center">
			<div class="card">
				<div class="panel-group" id="accordion5">
					<div class="panel panel-default" id="b_div1">
						<div class="panel-heading">
							<h4 class="panel-title main_title red" id="b_div5">
								<a class="droparrow collapsed" data-toggle="collapse"
									data-parent="#accordion5" href="#" id="j_final"
									onclick="divN(this)"><b>ACADEMIC QUALIFICATIONS</b></a>
							</h4>
						</div>
						<div id="collapse1j" class="panel-collapse collapse">
							<div class="card-body card-block">

								<table id="" class="table-bordered "
									style="margin-top: 10px; width: 100%;">
									<thead style="color: white; text-align: center;">
										<tr>
											<th style="width: 2%;">SR NO</th>
											<th style="width: 10%;">EXAM PASSED</th>
											<th style="width: 10%;">DEGREE ACQUIRED</th>
											<th style="width: 10%;">SPECIALIZATION</th>
											<th style="width: 10%;">YEAR OF PASSING</th>
											<th style="width: 10%;">DIV/CLASS</th>
											<th style="width: 10%;">SUBJECTS</th>
											<th style="width: 10%;">INSTITUTE</th>
										</tr>
									</thead>
									<tbody data-spy="scroll" id=""
										style="min-height: 46px; max-height: 650px; text-align: center;">

										<c:forEach var="item" items="${listAQ}" varStatus="num">
											<tr>
												<td>${num.index+1}</td>
												<td>${item.examination_pass}</td>
												<td>${item.d_name}</td>
												<td>${item.spce_name}</td>
												<td>${item.passing_year}</td>
												<td>${item.div_class}</td>
												<td>${item.description}</td>
												<td>${item.institute}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="animated fadeIn">
		<div class="container-fluid" align="center">
			<div class="card">
				<div class="panel-group" id="accordion5">
					<div class="panel panel-default" id="b_div1">
						<div class="panel-heading">
							<h4 class="panel-title main_title red" id="b_div5">
								<a class="droparrow collapsed" data-toggle="collapse"
									data-parent="#accordion5" href="#" id="k_final"
									onclick="divN(this)"><b>PROFESSIONAL/TECHNICAL
										QUALIFICATIONS</b></a>
							</h4>
						</div>
						<div id="collapse1k" class="panel-collapse collapse">
							<div class="card-body card-block">

								<table id="" class="table-bordered "
									style="margin-top: 10px; width: 100%;">
									<thead style="color: white; text-align: center;">
										<tr>
											<th style="width: 2%;">SR NO</th>
											<th style="width: 10%;">EXAM PASSED</th>
											<th style="width: 10%;">DEGREE ACQUIRED</th>
											<th style="width: 10%;">SPECIALIZATION</th>
											<th style="width: 10%;">YEAR OF PASSING</th>
											<th style="width: 10%;">DIV/CLASS</th>
											<th style="width: 10%;">SUBJECTS</th>
											<th style="width: 10%;">INSTITUTE</th>
										</tr>
									</thead>
									<tbody data-spy="scroll" id=""
										style="min-height: 46px; max-height: 650px; text-align: center;">

										<c:forEach var="item" items="${listPTQ}" varStatus="num">
											<tr>
												<td>${num.index+1}</td>
												<td>${item.examination_pass}</td>
												<td>${item.d_name}</td>
												<td>${item.spce_name}</td>
												<td>${item.passing_year}</td>
												<td>${item.div_class}</td>
												<td>${item.description}</td>
												<td>${item.institute}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="animated fadeIn">
		<div class="container-fluid" align="center">
			<div class="card">
				<div class="panel-group" id="accordion5">
					<div class="panel panel-default" id="b_div1">
						<div class="panel-heading">
							<h4 class="panel-title main_title red" id="b_div5">
								<a class="droparrow collapsed" data-toggle="collapse"
									data-parent="#accordion5" href="#" id="l_final"
									onclick="divN(this)"><b>LANGUAGE DETAILS</b></a>
							</h4>
						</div>
						<div id="collapse1l" class="panel-collapse collapse">
							<div class="card-body card-block">

								<table id="" class="table-bordered "
									style="margin-top: 10px; width: 100%;">
									<thead style="color: white; text-align: center;">
										<tr>
											<th style="width: 2%;">SR NO</th>
											<th style="width: 20%;">INDIAN</th>
											<th style="width: 20%;">LANGUAGE STD</th>

										</tr>
									</thead>
									<tbody data-spy="scroll" id=""
										style="min-height: 46px; max-height: 650px; text-align: center;">

										<c:forEach var="item" items="${listIL}" varStatus="num">
											<tr>
												<td>${num.index+1}</td>
												<td>${item.ind_language}</td>
												<td>${item.lan_std}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>

								<table id="" class="table-bordered "
									style="margin-top: 10px; width: 100%;">
									<thead style="color: white; text-align: center;">
										<tr>
											<th style="width: 2%;">SR NO</th>
											<th style="width: 20%;">FOREIGN</th>
											<th style="width: 20%;">LANGUAGE STD</th>
											<th style="width: 20%;">LANGUAGE PROF</th>
										</tr>

									</thead>
									<tbody data-spy="scroll" id=""
										style="min-height: 46px; max-height: 650px; text-align: center;">
										<c:forEach var="item" items="${listFL}" varStatus="num">
											<tr>
												<td>${num.index+1}</td>
												<td>${item.foreign_language_name}</td>
												<td>${item.lang_std}</td>
												<td>${item.lang_prof}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="animated fadeIn">
		<div class="container-fluid" align="center">
			<div class="card">
				<div class="panel-group" id="accordion5">
					<div class="panel panel-default" id="b_div1">
						<div class="panel-heading">
							<h4 class="panel-title main_title red" id="b_div5">
								<a class="droparrow collapsed" data-toggle="collapse"
									data-parent="#accordion5" href="#" id="m_final"
									onclick="divN(this)"><b>FOREIGN COUNTRIES VISITED</b></a>
							</h4>
						</div>
						<div id="collapse1m" class="panel-collapse collapse">
							<div class="card-body card-block">

								<table id="" class="table-bordered "
									style="margin-top: 10px; width: 100%;">
									<thead style="color: white; text-align: center;">
										<tr>
											<th style="width: 2%;">SR NO</th>
											<th style="width: 20%;">COUNTRY VISITED</th>
											<th style="width: 20%;">PURPOSE OF VISIT</th>
											<th style="width: 20%;">FROM</th>
											<th style="width: 20%;">TO</th>
											<th style="width: 20%;">DURATION</th>
										</tr>
									</thead>
									<tbody data-spy="scroll" id=""
										style="min-height: 46px; max-height: 650px; text-align: center;">
										<c:forEach var="item" items="${listF}" varStatus="num">
											<tr>
												<td>${num.index+1}</td>
												<td>${item.name}</td>
												<td>${item.purpose_visit}</td>
												<td>${item.from_dt}</td>
												<td>${item.to_dt}</td>
												<td>${item.period}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="animated fadeIn" id="mns_bpet">
		<div class="container-fluid" align="center">
			<div class="card">
				<div class="panel-group" id="accordion5">
					<div class="panel panel-default" id="b_div1">
						<div class="panel-heading">
							<h4 class="panel-title main_title red" id="b_div5">
								<a class="droparrow collapsed" data-toggle="collapse"
									data-parent="#accordion5" href="#" id="n_final"
									onclick="divN(this)"><b>BPET</b></a>
							</h4>
						</div>
						<div id="collapse1n" class="panel-collapse collapse">
							<div class="card-body card-block">

								<table id="" class="table-bordered "
									style="margin-top: 10px; width: 100%;">
									<thead style="color: white; text-align: center;">
										<tr>
											<th style="width: 2%;">SR NO</th>
											<th style="width: 20%;">BPET RESULT</th>
											<th style="width: 20%;">BPET QE</th>
											<th style="width: 20%;">YEAR</th>
										</tr>
									</thead>

									<tbody data-spy="scroll" id=""
										style="min-height: 46px; max-height: 650px; text-align: center;">
										<c:forEach var="item" items="${listB}" varStatus="num">
											<tr>
												<td>${num.index+1}</td>
												<td>${item.bpet_result}</td>
												<td>${item.bpet_qt}</td>
												<td>${item.yearb}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="animated fadeIn" id="mns_firing">
		<div class="container-fluid" align="center">
			<div class="card">
				<div class="panel-group" id="accordion5">
					<div class="panel panel-default" id="b_div1">
						<div class="panel-heading">
							<h4 class="panel-title main_title red" id="b_div5">
								<a class="droparrow collapsed" data-toggle="collapse"
									data-parent="#accordion5" href="#" id="o_final"
									onclick="divN(this)"><b>FIRING STANDARD</b></a>
							</h4>
						</div>
						<div id="collapse1o" class="panel-collapse collapse">
							<div class="card-body card-block">

								<table id="" class="table-bordered "
									style="margin-top: 10px; width: 100%;">
									<thead style="color: white; text-align: center;">
										<tr>
											<th style="width: 2%;">SR NO</th>
											<th style="width: 20%;">FIRING GRADE</th>
											<th style="width: 20%;">FIRING EVENT QE</th>
											<th style="width: 20%;">YEAR</th>
										</tr>
									</thead>
									<tbody data-spy="scroll" id=""
										style="min-height: 46px; max-height: 650px; text-align: center;">
										<c:forEach var="item" items="${listFS}" varStatus="num">
											<tr>
												<td>${num.index+1}</td>
												<td>${item.firing_grade}</td>
												<td>${item.firing_event_qe}</td>
												<td>${item.yearf}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="animated fadeIn">
		<div class="container-fluid" align="center">
			<div class="card">
				<div class="panel-group" id="accordion5">
					<div class="panel panel-default" id="b_div1">
						<div class="panel-heading">
							<h4 class="panel-title main_title red" id="b_div5">
								<a class="droparrow collapsed" data-toggle="collapse"
									data-parent="#accordion5" href="#" id="p_final"
									onclick="divN(this)"><b>BATTLE AND PHYSICAL CASULTY</b></a>
							</h4>
						</div>
						<div id="collapse1p" class="panel-collapse collapse">
							<div class="card-body card-block">

								<table id="" class="table-bordered "
									style="margin-top: 10px; width: 100%;">
									<thead style="color: white; text-align: center;">
										<tr>
											<th style="width: 2%;">SR NO</th>
											<th style="width: 20%;">CLASSIFICATION OF CASUALTY</th>
											<th style="width: 20%;">NATURE OF CASUALTY</th>
											<th style="width: 20%;">NAME OF OPERATION</th>
											<th style="width: 20%;">DATE OF CASUALTY</th>
											<th style="width: 20%;">CAUSE OF CASUALTY</th>
											<th style="width: 20%;">EXACT PLACE/AREA/POST</th>
										</tr>
									</thead>
									<tbody data-spy="scroll" id=""
										style="min-height: 46px; max-height: 650px; text-align: center;">

										<c:forEach var="item" items="${listBA}" varStatus="num">
											<tr>
												<td>${num.index+1}</td>
												<td>${item.classification_of_casuality}</td>
												<td>${item.nature_of_casuality}</td>
												<td>${item.name_of_operation}</td>
												<td>${item.date_of_casuality}</td>
												<td>${item.cause_of_casuality}</td>
												<td>${item.exact_place}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="animated fadeIn" id="mns_sec">
		<div class="container-fluid" align="center">
			<div class="card">
				<div class="panel-group" id="accordion5">
					<div class="panel panel-default" id="b_div1">
						<div class="panel-heading">
							<h4 class="panel-title main_title red" id="b_div5">
								<a class="droparrow collapsed" data-toggle="collapse"
									data-parent="#accordion5" href="#" id="q_final"
									onclick="divN(this)"><b>SECONDMENT</b></a>
							</h4>
						</div>
						<div id="collapse1q" class="panel-collapse collapse">
							<div class="card-body card-block">

								<div class="row">
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label">SECONDED TO</label>
												</div>
												<div class="col-md-8">
													<label id="seconded_to">${listSE[0].seconded_to}</label>
												</div>
											</div>
										</div>

										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label">DATE OF
														SECONDMENT</label>
												</div>
												<div class="col-md-8">
													<label id="secondment_effect">${listSE[0].secondment_effect}</label>
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
	<div class="container-fluid" align="center">
		<div class="card">
			<div class="panel-group" id="accordion5">
				<div class="panel panel-default" id="b_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title red" id="b_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion5" href="#" id="r_final"
								onclick="divN(this)"><b>ADDRESS DETAILS</b></a>
						</h4>
					</div>
					<div id="collapse1r" class="panel-collapse collapse">
						<div class="card-body card-block">
							<div class="card-header-inner" id="aa">
								<strong> ORIGINAL DOMICILE OF</strong>
							</div>
							<div class="row">
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label">COUNTRY </label>
											</div>
											<div class="col-md-8">
												<label id="ori_country">${listORM[0].ori_country}</label>
											</div>
										</div>
									</div>

									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label">STATE</label>
											</div>
											<div class="col-md-8">
												<label id="ori_state">${listORM[0].ori_state}</label>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label">DISTRICT</label>
											</div>
											<div class="col-md-8">
												<label id="ori_dis">${listORM[0].ori_dis}</label>
											</div>
										</div>
									</div>

									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label">TEHSIL</label>
											</div>
											<div class="col-md-8">
												<label id="ori_teh">${listORM[0].ori_teh}</label>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="card-header-inner" id="aa">
								<strong>PRESENTLY DOMICILE OF</strong>
							</div>
							<div class="row">
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label">COUNTRY </label>
											</div>
											<div class="col-md-8">
												<label id="pre_country">${listPDO[0].pre_country}</label>
											</div>
										</div>
									</div>

									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label">STATE</label>
											</div>
											<div class="col-md-8">
												<label id="pre_state">${listPDO[0].pre_state}</label>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label">DISTRICT</label>
											</div>
											<div class="col-md-8">
												<label id="pre_dis">${listPDO[0].pre_dis}</label>
											</div>
										</div>
									</div>

									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label">TEHSIL</label>
											</div>
											<div class="col-md-8">
												<label id="pre_teh">${listPDO[0].pre_teh}</label>
											</div>
										</div>
									</div>
								</div>
							</div>

							<div class="card-header-inner" id="aa">
								<strong>PERMANENT ADDRESS</strong>
							</div>
							<div class="row">
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;"> </strong> Country</label>
											</div>
											<div class="col-md-8">
												<label id="pm_country">${listPM[0].pm_country}</label>

											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;"> </strong> State</label>
											</div>
											<div class="col-md-8">
												<label id="pm_state">${listPM[0].pm_state}</label>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;"> </strong> District</label>
											</div>
											<div class="col-md-8">
												<label id="pm_dis">${listPM[0].pm_dis}</label>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;"> </strong> Tehsil</label>
											</div>
											<div class="col-md-8">
												<label id="pm_teh">${listPM[0].pm_teh}</label>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;"> </strong> Village/Town/City</label>
											</div>
											<div class="col-md-8">
												<label id="permanent_village">${listPM[0].permanent_village}</label>

											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;"> </strong> Pin</label>
											</div>
											<div class="col-md-8">
												<label id="permanent_pin_code" name="permanent_pin_code">${listPM[0].permanent_pin_code}</label>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12">

									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;"> </strong> Nearest Railway Station</label>
											</div>
											<div class="col-md-8">
												<label id="permanent_near_railway_station">${listPM[0].permanent_near_railway_station}</label>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;"> </strong> Border Area</label>
											</div>
											<div class="col-md-8">
												<label id="permanent_border_area">${listPM[0].permanent_border_area}</label>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="card-header-inner" id="aa">
								<strong>PRESENT ADDRESS</strong>
							</div>
							<div class="row">
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;"> </strong> Country</label>
											</div>
											<div class="col-md-8">
												<label id="ps_country">${listPS[0].ps_country}</label>

											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;"> </strong> State</label>
											</div>
											<div class="col-md-8">
												<label id="ps_state">${listPS[0].ps_state}</label>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;"> </strong> District</label>
											</div>
											<div class="col-md-8">
												<label id="ps_dis">${listPS[0].ps_dis}</label>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;"> </strong> Tehsil</label>
											</div>
											<div class="col-md-8">
												<label id="ps_teh">${listPS[0].ps_teh}</label>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;"> </strong> Village/Town/City</label>
											</div>
											<div class="col-md-8">
												<label id="permanent_village">${listPS[0].permanent_village}</label>

											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;"> </strong> Pin</label>
											</div>
											<div class="col-md-8">
												<label id="present_pin_code">${listPS[0].present_pin_code}</label>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12">

									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;"> </strong> Nearest Railway Station</label>
											</div>
											<div class="col-md-8">
												<label id="present_near_railway_station">${listPS[0].present_near_railway_station}</label>
											</div>
										</div>
									</div>

								</div>
							</div>
							<div class="card-header-inner" id="aa">
								<strong>NOK DETAILS</strong>
							</div>
							<div class="row">
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;"> </strong> NAME OF NOK</label>
											</div>
											<div class="col-md-8">
												<label id=nok_name>${listNOK[0].nok_name}</label>

											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;"> </strong> Nok Relation</label>
											</div>
											<div class="col-md-8">
												<label id="relation_name">${listNOK[0].relation_name}</label>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="card-header-inner" id="aa">
								<strong>NOK ADDRESS</strong>
							</div>
							<div class="row">
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;"> </strong> COUNTRY </label>
											</div>
											<div class="col-md-8">
												<label id=nok_country>${listNA[0].nok_country}</label>

											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;"> </strong> STATE</label>
											</div>
											<div class="col-md-8">
												<label id="nok_state">${listNA[0].nok_state}</label>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;"> </strong> DISTRICT</label>
											</div>
											<div class="col-md-8">
												<label id=nok_dis>${listNA[0].nok_dis}</label>

											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;"> </strong> TEHSIL</label>
											</div>
											<div class="col-md-8">
												<label id="nok_teh">${listNA[0].nok_teh}</label>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;"> </strong> Village/Town/City</label>
											</div>
											<div class="col-md-8">
												<label id=nok_village>${listNA[0].nok_village}</label>

											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;"></strong> PIN</label>
											</div>
											<div class="col-md-8">
												<label id="nok_pin">${listNA[0].nok_pin}</label>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;"> </strong> Nearest Railway Station</label>
											</div>
											<div class="col-md-8">
												<label id=nok_near_railway_station>${listNA[0].nok_near_railway_station}</label>

											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;"> </strong> NOK's Mobile No</label>
											</div>
											<div class="col-md-8">
												<label id="nok_mobile_no">${listNA[0].nok_mobile_no}</label>
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
		
		<c:if test="${serving_status eq 'NON EFFECTIVE' }">
		<div class="animated fadeIn">
		<div class="container-fluid" align="center">
			<div class="card">
				<div class="panel-group" id="accordion5">
					<div class="panel panel-default" id="b_div1">
						<div class="panel-heading">
							<h4 class="panel-title main_title red" id="b_div5">
								<a class="droparrow collapsed" data-toggle="collapse"
									data-parent="#accordion5" href="#" id="s_final"
									onclick="divN(this)"><b>RETIREMENT DETAILS</b></a>
							</h4>
						</div>
						<div id="collapse1s" class="panel-collapse collapse">
							<div class="card-body card-block">

							
								<div class="card-header-inner" id="aa">
									<strong>ADDRESS</strong>
								</div>
								<div class="row">
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;"> </strong> Country</label>
												</div>
												<div class="col-md-8">
													<label id="ar_country">${listAR[0].ar_country}</label>

												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;"> </strong> STATE</label>
												</div>
												<div class="col-md-8">
													<label id="ar_state">${listAR[0].ar_state}</label>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;"> </strong> DISTRICT</label>
												</div>
												<div class="col-md-8">
													<label id="ar_dis">${listAR[0].ar_dis}</label>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;"> </strong> TEHSIL</label>
												</div>
												<div class="col-md-8">
													<label id="ar_teh">${listAR[0].ar_teh}</label>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;"> </strong> VILLAGE/TOWN/CITY</label>
												</div>
												<div class="col-md-8">
													<label id="permanent_village">${listAR[0].permanent_village}</label>

												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;"> </strong> PIN</label>
												</div>
												<div class="col-md-8">
													<label id="present_pin_code">${listAR[0].permanent_pin_code}</label>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-12">

										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;"> </strong> NEAREST RAILWAY STATION</label>
												</div>
												<div class="col-md-8">
													<label id="present_near_railway_station">${listAR[0].permanent_near_railway_station}</label>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;"> </strong> NOK'S MOBILE NO</label>
												</div>
												<div class="col-md-8">
													<label id="nok_mobile_no">${listAR[0].nok_mobile_no}</label>
												</div>
											</div>
										</div>

									</div>
								</div>
							<!-- 	<div class="card-header-inner" id="aa">
									<strong>RETIREMENT DETAILS</strong>
								</div> -->
								<div class="row">
									<div class="col-md-12">

										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;"> </strong> CAUSE OF RETIREMENT</label>
												</div>
												<div class="col-md-8">
													<label id="causes_name">${listRD[0].causes_name}</label>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;"> </strong> DATE OF RETIREMENT</label>
												</div>
												<div class="col-md-8">
													<label id="date_of_non_effective">${listRD[0].date_of_non_effective}</label>
												</div>
											</div>
										</div>
									</div>

									<div class="col-md-12">

										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;"> </strong> CAUSE OF RELEASE FROM RE-
														EMPLOYMENT</label>
												</div>
												<div class="col-md-8">
													<label id="cause_of_release">${listRD[0].cause_of_release}</label>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong 
														style="color: red;"></strong> DATE OF RELEASE FROM RE-EMPLOYMENT</label>
												</div>
												<div class="col-md-8">
													<label id="date_of_release">${listRD[0].date_of_release}</label>
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
	</c:if>
	
		<c:if test="${serving_status eq 'RE-EMPLOYED' }">
		<div class="animated fadeIn">
		<div class="container-fluid" align="center">
			<div class="card">
				<div class="panel-group" id="accordion5">
					<div class="panel panel-default" id="b_div1">
						<div class="panel-heading">
							<h4 class="panel-title main_title red" id="b_div5">
								<a class="droparrow collapsed" data-toggle="collapse"
									data-parent="#accordion5" href="#" id="s_final"
									onclick="divN(this)"><b>RETIREMENT DETAILS</b></a>
							</h4>
						</div>
						<div id="collapse1s" class="panel-collapse collapse">
							<div class="card-body card-block">

							
								<div class="card-header-inner" id="aa">
									<strong>ADDRESS</strong>
								</div>
								<div class="row">
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;"> </strong> Country</label>
												</div>
												<div class="col-md-8">
													<label id="ar_country">${listAR[0].ar_country}</label>

												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;"> </strong> STATE</label>
												</div>
												<div class="col-md-8">
													<label id="ar_state">${listAR[0].ar_state}</label>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;"> </strong> DISTRICT</label>
												</div>
												<div class="col-md-8">
													<label id="ar_dis">${listAR[0].ar_dis}</label>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;"> </strong> TEHSIL</label>
												</div>
												<div class="col-md-8">
													<label id="ar_teh">${listAR[0].ar_teh}</label>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;"> </strong> VILLAGE/TOWN/CITY</label>
												</div>
												<div class="col-md-8">
													<label id="permanent_village">${listAR[0].permanent_village}</label>

												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;"> </strong> PIN</label>
												</div>
												<div class="col-md-8">
													<label id="present_pin_code">${listAR[0].permanent_pin_code}</label>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-12">

										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;"> </strong> NEAREST RAILWAY STATION</label>
												</div>
												<div class="col-md-8">
													<label id="present_near_railway_station">${listAR[0].permanent_near_railway_station}</label>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;"> </strong> NOK'S MOBILE NO</label>
												</div>
												<div class="col-md-8">
													<label id="nok_mobile_no">${listAR[0].nok_mobile_no}</label>
												</div>
											</div>
										</div>

									</div>
								</div>
							<!-- 	<div class="card-header-inner" id="aa">
									<strong>RETIREMENT DETAILS</strong>
								</div> -->
								<div class="row">
									<div class="col-md-12">

										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;"> </strong> CAUSE OF RETIREMENT</label>
												</div>
												<div class="col-md-8">
													<label id="causes_name">${listRD[0].causes_name}</label>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;"> </strong> DATE OF RETIREMENT</label>
												</div>
												<div class="col-md-8">
													<label id="date_of_non_effective">${listRD[0].date_of_non_effective}</label>
												</div>
											</div>
										</div>
									</div>

									<div class="col-md-12">

										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;"> </strong> CAUSE OF RELEASE FROM RE-
														EMPLOYMENT</label>
												</div>
												<div class="col-md-8">
													<label id="cause_of_release">${listRD[0].cause_of_release}</label>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong 
														style="color: red;"></strong> DATE OF RELEASE FROM RE-EMPLOYMENT</label>
												</div>
												<div class="col-md-8">
													<label id="date_of_release">${listRD[0].date_of_release}</label>
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
	</c:if>
	
	
	<c:if test="${serving_status eq 'RE-CALL FROM RESERVE' }">
		<div class="animated fadeIn">
		<div class="container-fluid" align="center">
			<div class="card">
				<div class="panel-group" id="accordion5">
					<div class="panel panel-default" id="b_div1">
						<div class="panel-heading">
							<h4 class="panel-title main_title red" id="b_div5">
								<a class="droparrow collapsed" data-toggle="collapse"
									data-parent="#accordion5" href="#" id="s_final"
									onclick="divN(this)"><b>RETIREMENT DETAILS</b></a>
							</h4>
						</div>
						<div id="collapse1s" class="panel-collapse collapse">
							<div class="card-body card-block">

							
								<div class="card-header-inner" id="aa">
									<strong>ADDRESS</strong>
								</div>
								<div class="row">
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;"> </strong> Country</label>
												</div>
												<div class="col-md-8">
													<label id="ar_country">${listAR[0].ar_country}</label>

												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;"> </strong> STATE</label>
												</div>
												<div class="col-md-8">
													<label id="ar_state">${listAR[0].ar_state}</label>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;"> </strong> DISTRICT</label>
												</div>
												<div class="col-md-8">
													<label id="ar_dis">${listAR[0].ar_dis}</label>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;"> </strong> TEHSIL</label>
												</div>
												<div class="col-md-8">
													<label id="ar_teh">${listAR[0].ar_teh}</label>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;"> </strong> VILLAGE/TOWN/CITY</label>
												</div>
												<div class="col-md-8">
													<label id="permanent_village">${listAR[0].permanent_village}</label>

												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;"> </strong> PIN</label>
												</div>
												<div class="col-md-8">
													<label id="present_pin_code">${listAR[0].permanent_pin_code}</label>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-12">

										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;"> </strong> NEAREST RAILWAY STATION</label>
												</div>
												<div class="col-md-8">
													<label id="present_near_railway_station">${listAR[0].permanent_near_railway_station}</label>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;"> </strong> NOK'S MOBILE NO</label>
												</div>
												<div class="col-md-8">
													<label id="nok_mobile_no">${listAR[0].nok_mobile_no}</label>
												</div>
											</div>
										</div>

									</div>
								</div>
							<!-- 	<div class="card-header-inner" id="aa">
									<strong>RETIREMENT DETAILS</strong>
								</div> -->
								<div class="row">
									<div class="col-md-12">

										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;"> </strong> CAUSE OF RETIREMENT</label>
												</div>
												<div class="col-md-8">
													<label id="causes_name">${listRD[0].causes_name}</label>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;"> </strong> DATE OF RETIREMENT</label>
												</div>
												<div class="col-md-8">
													<label id="date_of_non_effective">${listRD[0].date_of_non_effective}</label>
												</div>
											</div>
										</div>
									</div>

									<div class="col-md-12">

										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;"> </strong> CAUSE OF RELEASE FROM RE-
														EMPLOYMENT</label>
												</div>
												<div class="col-md-8">
													<label id="cause_of_release">${listRD[0].cause_of_release}</label>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong 
														style="color: red;"></strong> DATE OF RELEASE FROM RE-EMPLOYMENT</label>
												</div>
												<div class="col-md-8">
													<label id="date_of_release">${listRD[0].date_of_release}</label>
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
	</c:if>
		
		<div class="card-footer" align="center" class="form-control">
			<input type="button" class="btn btn-primary btn-sm" value="Print"
				onclick="Print_Pdf();"> <a href="View_UpdateOfficerDataUrl"
				id="backBTN" class="btn btn-info btn-sm">Back</a> <a href="#"
				class="go-top fa fa-arrow-up" style="display: none"></a>
				
<input type="hidden" id="IsMns" name="IsMns"  autocomplete="off">
		</div>
		<input type="hidden" id="comm_id" name="comm_id"
			class="form-control autocomplete" autocomplete="off"> <input
			type="hidden" id="census_id" name="census_id"
			class="form-control autocomplete" autocomplete="off"
			value="${list[0].census_id}">
	




</form:form>




<c:url value="Print_Record_Service" var="Print_Record_Service" />
<form:form action="${Print_Record_Service}" method="post" id="printForm"
	name="printForm">
	<input type="hidden" name="comm_id1" id="comm_id1" value="0" />
	<input type="hidden" name="census_id1" id="census_id1" value="0" />
	<input type="hidden" name="comm_status1" id="comm_status1" value="0" />
	<input type="hidden" name="sus_noV" id="sus_noV" value="0" />
	
</form:form>



<script>


//*************************************START Personel Detail *****************************//


 function Validation()
{
	var first_name = $('#first_name').val();  
	var middle_name = $('#middle_name').val();  
	var last_name = $('#last_name').val();  
	var place_birth = $('#place_birth').val();  
	var district_birth = $('#district_birth').val();  
	var state_birth = $('#state_birth').val();  
	var country_birth = $('#country_birth').val();  
	var nationality = $('#nationality').val();  
	var religion = $('#religion').val();  
	var marital_status = $('#marital_status').val();  
	//var medical_category = $('#medical_category').val(); 
	var blood_desc = $('#blood_desc').val();  
	var rh = $('#rh').val();  
	var height = $('#height').val();  
	var adhar_number = $('#adhar_number').val();  
	var border_area = $('#border_area').val(); 
	var comm_id = $('#comm_id').val(); 
	
	$.post('Personnel_Detailaction?' + key + "=" + value, {first_name:first_name,middle_name:middle_name,last_name:last_name,place_birth:place_birth,district_birth:district_birth,state_birth:state_birth,country_birth:country_birth,nationality:nationality,religion:religion,marital_status:marital_status,blood_group:blood_group,rh:rh,height:height,adhar_number:adhar_number,border_area:border_area,comm_id:comm_id }, function(data){
			      
	       if(parseInt(data)>0)
	       	  {
	    	   
	        	alert("Data Saved Successfully");
	        	$("#tab_id").show();
	          }
	          else
	          {
	        	  alert("Data Not Updated")
	           }
	        	
	 	  		}).fail(function(xhr, textStatus, errorThrown) 
	 	  				{
	 	  					alert("fail to fetch")
	  		});

} 

//*************************************END Personel Detail*****************************//
</script>
<script>
$(document).ready(function() {
	debugger;
	var adhar = '${list[0].adhar_number}';
	var arrTo=adhar.substring(0, 4);
	var arrTo1=adhar.substring(4, 8);
	var arrTo2=adhar.substring(8);

	$("#aadhar_no1").text(arrTo);
	$("#aadhar_no2").text(arrTo1);
	$("#aadhar_no3").text(arrTo2); 
	

	$('#IsMns').val('${IsMns}'); 
	
	if($('#IsMns').val() === 'true'){
		$('#mns_bpet').hide();
		$('#mns_firing').hide();
		$('#mns_sec').hide();
	}
	
	$('#comm_id').val('${comm_id}'); 
	$('#hid_comm_status').val('${comm_status}'); 
	$('#hid_sus_no').val('${sus_no}'); 
	 $('#identity_image_preview').attr("src", "censusIdentityConvertpath?i_id="+'${list[0].imgid}'+" ");
	 if('${whrToOpen}'=='query'){
		 $('#backBTN').attr('href','Record_Service_Query');
	 }
	
	if(sizeSH != 0){
	$("#s_shape").text('${listSH[0].s_shape}');
	$("#h_shape").text('${listSH[0].h_shape}');	
	}
	
		
	if(sizeF !=0) {
	     var from_dt = '${listF[0].from_dt}'
		from_dt = from_dt.substring(0, 10);
		$("#from_dt").text(ParseDateColumncommission(from_dt));
	
		var to_dt = '${listF[0].to_dt}'
		to_dt = to_dt.substring(0, 10);
		$("#to_dt").text(ParseDateColumncommission(to_dt));
		}
	
	if(sizeBA !=0){
			var date_of_casuality = '${listBA[0].date_of_casuality}'
			date_of_casuality = date_of_casuality.substring(0, 10);
			$("#date_of_casuality").text( ParseDateColumncommission(date_of_casuality));
			
	}
	if(sizeS !=0){
			
			var marriage_date = '${listS[0].marriage_date}'
				marriage_date = marriage_date.substring(0, 10);
			$("#marriage_date").text( ParseDateColumncommission(marriage_date));
			
		    var date_of_birth = '${listS[0].date_of_birth}'
		    	date_of_birth = date_of_birth.substring(0, 10);
			$("#date_of_birth1").text( ParseDateColumncommission(date_of_birth));
	}
			
				
			
			
			});
			
function Print_Pdf(){	
	
	$("#sus_noV").val($('#hid_sus_no').val());
	 $("#comm_status1").val($('#hid_comm_status').val());
	 $("#comm_id1").val($('#comm_id').val());
	 $("#census_id1").val($('#census_id').val());
	document.getElementById('printForm').submit(); 
}



$('#backBTN').on('click', function(e) {
    e.preventDefault();
    
    var redirectUrl = $('#IsMns').val() === 'true' 
        ? 'View_UpdateOfficerData_mnsUrl' 
        : 'View_UpdateOfficerDataUrl';
    
    window.location.href = redirectUrl;
});

</script>
