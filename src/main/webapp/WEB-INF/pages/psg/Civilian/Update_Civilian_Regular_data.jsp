<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>

<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

<!-- <link rel="stylesheet" href="js/assets/css/bootstrap.min.css"> -->
<link rel="stylesheet" href="js/layout/css/animation.css">
<!-- <link rel="stylesheet" href="js/assets/scss/style.css">  -->
<link rel="stylesheet" href="js/assets/collapse/collapse.css">

<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
<!-- <script src="js/common/commonmethod.js" type="text/javascript"></script> -->

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
.go-top {
  right:1em;
  bottom:2em;
  color:#FAFAFA;
  text-decoration:none;
  background:#F44336;
  padding:5px;
  border-radius:5px;
  border:1px solid #e0e0e0;
  position:fixed;
  font-size: 180%;
  

}

label {
    word-break: break-word;
}

textarea {
    text-transform: none!important;
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


function Cancel(){
  	
	$("#personnel_no1").val($("#personnel_noV").val()) ;
	$("#status1").val($("#statusV").val()) ;
	$("#rank1").val($("#rankV").val()) ;
	$("#civ_id").val($("#civ_idV").val()) ;
	$("#unit_name1").val($("#unit_nameV").val()) ;
	$("#unit_sus_no1").val($("#unit_sus_noV").val()) ;
	 
	document.getElementById('searchForm').submit();
}

 


</script>
<c:url value="GetSearch_UpdateJCOData" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="personnel_no1">
		<input type="hidden" name="personnel_no1" id="personnel_no1" value="0"/>		
		<input type="hidden" name="rank1" id="rank1" value="0"/>
		<input type="hidden" name="status1" id="status1" value="0"/>
		<input type="hidden" name="unit_name1" id="unit_name1"  />
		<input type="hidden" name="unit_sus_no1" id="unit_sus_no1"  />
		<input type="hidden" name="cr_date1" id="cr_date1"   />
        <input type="hidden" name="cr_by1" id="cr_by1"   />
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
						<b>UPDATE DATA CIVILIAN REGULAR EMPLOYEE </b>
						
						</h4>
					</div>
							
	              			
	              					<div class="col-md-12" style="padding-top: 5px; padding-left: 250px;">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong>Employee No</label>
											</div>
											<div class="col-md-8">
												<input type="text" id="personnel_no" name="personnel_no" class="form-control autocomplete" autocomplete="off"  > 						                   
							               		<input type="hidden" id="civ_id" name="civ_id" class="form-control autocomplete" autocomplete="off"  > 	
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
						                 <input type=hidden id="civ_idV" name="civ_idV" class="form-control autocomplete"  > 
						                 <input type="hidden" id="unit_nameV" name="unit_nameV" class="form-control autocomplete"  > 
						                 <input type="hidden" id="unit_sus_noV" name="unit_sus_noV" class="form-control autocomplete"  > 
											
										
	              		<div class="card-footer" align="right" class="form-control" id="back_bt" style="display: none;">
							<a href="Search_Report_3008Url" class="btn btn-info btn-sm" >Back</a> 
			            </div> 	
			            
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
<div id = "div_update_data" style="display: none;" class="subcontent">
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
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;"></strong> Name	:</label>
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
	              				  <div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                     <label class=" form-control-label"> Date of TOS	:</label>
						                </div>
						                <div class="col-md-8">
						                     <label class=" form-control-label" id="p_tos"></label>		
						                      <input type="hidden" class=" form-control-label" id="tos_date" >
						                </div>
						            </div>
	              				</div>
	              				
	              			</div>          
	              		  <div class="col-md-12">	              					
	              	
	              			 
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;"></strong> SUS No	:</label>
						                </div>
						                <div class="col-md-8">
						                  <label class=" form-control-label" id="app_sus_no"></label>
						                </div>
						            </div>
	              				</div>
	              				  	<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                     <label class=" form-control-label"> Unit Name	:</label>
						                </div>
						                <div class="col-md-8">
						                     <label class=" form-control-label" id="app_unit_name"></label>		
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
<!-- <div id="moredetailsdiv"> -->

		<!-- START MARITAL EVENTS DETAIL -->
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
						<div align="left">
						<input type="button" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('marital_status');">
						</div>
						
							<div class="row" >

                            <div class="col-md-12" id="marr_quali_radioDiv" style="margin-top: 10px; width: 100%; margin-bottom: 16px;">
									<label class=" form-control-label" style="margin-right: 70px;"><h4>Event</h4></label> &nbsp
									<input type="radio" style="margin-right: 5px; transform: scale(1.3);"
    
										id="marr_quali_radio1" name="marr_quali_radio" value="yes"
										onchange="marrqualificationFn();">&nbsp <label for="yes">Change Marital Status</label>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
									<input type="radio" id="marr_quali_radio2" name="marr_quali_radio" style="margin-right: 5px; transform: scale(1.3);"
										value="no" onchange="marrqualificationFn();" >&nbsp
									<label for="no">Add/Update Spouse Qualification</label><br>

								</div>
                                
						<div class="col-md-12" id="marital_eventDiv">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">* </strong>Marital Event</label>
									</div>
									<div class="col-md-8">
									<select name="marital_event" id="marital_event" class="form-control"  onchange="marital_eventchange();" >
									                        <option value="0">--Select--</option>
															<c:forEach var="item" items="${getMarital_eventList}" varStatus="num">
															<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
															</c:forEach>
										</select>  			
									</div>
								</div>
							</div>
							<div class="col-md-6" style="display: none;" >
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">* </strong>Marital Status</label>
									</div>
									<div class="col-md-8">
								<select name="marital_status" id="marital_status" class="form-control"   >
									                        <option value="0">--Select--</option>
															<c:forEach var="item" items="${getMarital_statusList}" varStatus="num">
															<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
															</c:forEach>
															</select> 
									</div>
								</div>
							</div> 
						</div>
<div id="marriage_remarriageDiv" style="display:none">		
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong>Authority</label>
											</div>
											<div class="col-md-8">
										<input type="hidden" id="marr_ch_id" name="marr_ch_id"  value="0" class="form-control autocomplete" autocomplete="off" >											
										 <input type="text" id="marriage_authority" name="marriage_authority" class="form-control autocomplete" autocomplete="off" 
										  onkeyup="return specialcharecter(this)" maxlength="100">
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
											 <input type="text" name="marriage_date_of_authority" id="marriage_date_of_authority" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
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
							<label class=" form-control-label"><strong
								style="color: red;">* </strong>Spouse Name</label>
						</div>
						<div class="col-md-8">
						<input type="text" id="maiden_name" name="maiden_name" class="form-control autocomplete" autocomplete="off" 
						        maxlength="50" onkeypress="return onlyAlphabets(event);" oninput="this.value = this.value.toUpperCase()">
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="row form-group">
						<div class="col-md-4">
							<label class=" form-control-label"><strong
								style="color: red;">* </strong>Date of Marriage</label>
						</div>
						<div class="col-md-8">
						<input type="text" name="marriage_date" id="marriage_date" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
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
								<label class=" form-control-label"><strong
									style="color: red;">* </strong>Spouse Place of Birth</label>
							</div>
							<div class="col-md-8">
								<input type="text" id="marriage_place_of_birth" name="marriage_place_of_birth" 
								class="form-control autocomplete" autocomplete="off" maxlength="50" onkeypress="return onlyAlphabets(event);">				         
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"><strong
									style="color: red;">* </strong>Spouse Nationality</label>
							</div>
							<div class="col-md-8">
					<select name="marriage_nationality" id="marriage_nationality" 
					onchange="fn_otherShowHide(this,'Spouse_nationalityDiv','spouseNationality_other')"
					class="form-control"   >
							                        <option value="0">--Select--</option>
													<c:forEach var="item" items="${getNationalityList}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
													</select> 
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12" id="Spouse_nationalityDiv" style="display:none">
					 
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"><strong
									style="color: red;">* </strong>Other Nationality</label>
							</div>
							<div class="col-md-8">
				       	<input type="text" id="spouseNationality_other" name="spouseNationality_other"
												class="form-control autocomplete" autocomplete="off"
											 onkeypress="return onlyAlphabets(event);" maxlength="50"
												oninput="this.value = this.value.toUpperCase()">
					
							</div>
						</div>
					</div>
				</div>
				
							<div class="col-md-12">
								<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong>Spouse Date of Birth</label>
											</div>
											<div class="col-md-8">
											<input type="text" name="marriage_date_of_birth" id="marriage_date_of_birth" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong>Spouse Aadhar Card No</label>
											</div>
											<div class="col-md-8">
									  <input type="text" id="marriage_adhar_no" name="marriage_adhar_no" class="form-control autocomplete" 
									      autocomplete="off" maxlength="14" onkeypress="return isAadhar(this,event);" >
											</div>
										</div>
									</div>
								</div>
								
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;"> </strong>Spouse PAN Card No</label>
											</div>
											<div class="col-md-8">
									  <input type="text" id="pan_card" name="pan_card" class="form-control autocomplete" 
									  autocomplete="off" maxlength="10" onchange="isPAN(this);" oninput="this.value = this.value.toUpperCase()" >
											</div>
										</div>
									</div>
									<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">* </strong>In Service/Ex-Service</label>
									</div>
									<div class="col-md-8">
										<input type="radio" id="spouse_ser_radio1"
											name="spouse_ser_radio" value="Yes"
											onchange="spouse_ServiceFn();">&nbsp <label
											for="yes">Yes</label>&nbsp <input type="radio"
											id="spouse_ser_radio2" name="spouse_ser_radio" value="No"
											onchange="spouse_ServiceFn();" checked="checked">&nbsp
										<label for="no">No</label><br>


									</div>
								</div>
							</div>
									
								</div>
								<div id="spouse_inserviceDiv" class="col-md-12"
								style="display: none">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong>Services</label>
										</div>
										<div class="col-md-8">
											<select name="spouse_service" id="spouse_service"
												class="form-cont rol-sm form-control"
												onchange="Spouse_ServiceOtherFn('spouseSer_otherDiv','spouse_personalDiv',this)">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getExservicemenList}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>


										</div>
									</div>
								</div>
								<div class="col-md-6" id="spouseSer_otherDiv" style="display: none">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">*</strong>Other</label>
										</div>
										<div class="col-md-8">
											<input type="text" id="spouseSer_other" name="spouseSer_other"
                                                 class="form-control autocomplete" autocomplete="off"
                                                 onkeypress="return onlyAlphaNumeric(event);" maxlength="50"
                                                 oninput="this.value = this.value.toUpperCase()">


										</div>
									</div>
								</div>
								<div class="col-md-6" id="spouse_personalDiv">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;"></strong> Personal No.</label>
										</div>
										<div class="col-md-8">
											<input type="text" id="spouse_personal_no"
												name="spouse_personal_no" class="form-control autocomplete" maxlength="9"
												autocomplete="off"
												onkeypress="return /[0-9a-zA-Z]/i.test(event.key)"
												oninput="this.value = this.value.toUpperCase()">


										</div>
									</div>
								</div>

							</div>
							<div class="col-md-12" id="divorceDiv" style="display:none">				
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label" id="divorce_widow_widower_dtlbl"><strong
														style="color: red;">* </strong>Date of Divorce</label>
												</div>
												<div class="col-md-8">
												<input type="text" name="divorce_date" id="divorce_date" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
												</div>
											</div>
										</div>
									</div>
									
									<div class="col-md-12" id="seperateDiv" style="display:none">				
										<div class="col-md-6" id="separated_from_dtDiv" style="display:none">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label" ><strong
														style="color: red;">* </strong>Date of Separation</label>
												</div>
												<div class="col-md-8">
												<input type="text" name="separated_from_dt" id="separated_from_dt" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
												</div>
											</div>
										</div>
										<div class="col-md-6" id="separated_to_dtDiv" style="display:none">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label" ><strong
														style="color: red;"> </strong>Separation To Date</label>
												</div>
												<div class="col-md-8">
												<input type="text" name="separated_to_dt" id="separated_to_dt" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
												</div>
											</div>
										</div>
									</div>
							
						</div>								
								
						      
								<div class="col-md-12" id="spouse_quali_radioDiv" style=" width: 100%;">
									<label class=" form-control-label" style="margin-right: 100px;"><h4> Qualification Details of Spouse</h4><strong
														style="color: red;">Note:-Only Highest qualification should be fill.</strong></label> 
								<input type="radio"
										id="spouse_quali_radio1" name="spouse_quali_radio" value="yes"
										onchange="spouse_quali_radioFn();">&nbsp <label for="yes">Yes</label>&nbsp
									<input type="radio" id="spouse_quali_radio2" name="spouse_quali_radio"
										value="no" onchange="spouse_quali_radioFn();" checked="checked">&nbsp
									<label for="no">No</label><br>
								</div>
								
							<div id="spouse_Qualifications" style="margin-top: 20px; display:none">
							
							
								
								<div class="col-md-12" id="spouse_quali_labelDiv" style=" width: 100%; display: none">
									<label class=" form-control-label" style="margin-right: 100px;"><h4> Qualification Details of Spouse</h4><strong
														style="color: red;">Note:-Only Highest qualification should be fill.</strong></label> 							
								</div>
								
											<div class="col-md-12">
											<div class="col-md-6">
												<div class="row form-group">
													<div class="col-md-4" align="left">
														<label class=" form-control-label"><strong
															style="color: red;">* </strong>Qualification Type</label>
													</div>
													<div class="col-md-8">
														<select name="spouse_quali_type" id="spouse_quali_type"
															class=" form-control"
															onchange="fn_qualification_typeChange(this,'spouse_quali','quali_degree_spouse','spouse_specialization')">
															<option value="0">--Select--</option>
															<c:forEach var="item" items="${getQualificationTypeList}"
																varStatus="num">
																<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
															</c:forEach>

														</select> <input type="hidden" id="spouse_qualification_ch_id"
															name="spouse_qualification_ch_id" value="0"
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
														<select name="spouse_quali" id="spouse_quali"
															class=" form-control"
															onchange="fn_ExaminationTypeChange(this,'quali_degree_spouse','spouse_specialization');fn_otherShowHide(this,'other_div_examSpouse','exam_otherSpouse')">
															<option value="0">--Select--</option>

														</select>
													</div>
												</div>

											</div>

										</div>

										<div class="col-md-12">
											<div class="col-md-6" id="other_div_examSpouse"
												style="display: none;">
												<div class="row form-group">
													<div class="col-md-4" align="left">
														<label class=" form-control-label"><strong
															style="color: red;">* </strong>Examination Other </label>
													</div>
													<div class="col-md-8">
														<input type="text" id="exam_otherSpouse" onkeypress="return onlyAlphabets(event);" maxlength="50"
															name="exam_otherSpouse" class="form-control autocomplete"
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
															style="color: red;">* </strong>Degree Acquired</label>
													</div>
													<div class="col-md-8">
														<select name="quali_degree_spouse" id="quali_degree_spouse"
															class=" form-control"
															onchange="fn_otherShowHide(this,'other_div_qualiDegSpouse','quali_deg_otherSpouse')">

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
														<select name="spouse_specialization"
															id="spouse_specialization" class="form-control"
															onchange="fn_otherShowHide(this,'other_div_qualiSpecSpouse','quali_spec_otherSpouse')">
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
											<div class="col-md-6" id="other_div_qualiDegSpouse"
												style="display: none;">
												<div class="row form-group">
													<div class="col-md-4" align="left">
														<label class=" form-control-label"><strong
															style="color: red;">* </strong>Degree Other </label>
													</div>
													<div class="col-md-8">
														<input type="text" id="quali_deg_otherSpouse"
															name="quali_deg_otherSpuse" onkeypress="return onlyAlphabets(event);" maxlength="50"
															class="form-control autocomplete" autocomplete="off">
													</div>
												</div>
											</div>

											<div class="col-md-6" id="other_div_qualiSpecSpouse"
												style="display: none;">
												<div class="row form-group">
													<div class="col-md-4" align="left">
														<label class=" form-control-label"><strong
															style="color: red;">* </strong>Specialization Other </label>
													</div>
													<div class="col-md-8">
														<input type="text" id="quali_spec_otherSpouse"
															name="quali_spec_otherSpouse" onkeypress="return onlyAlphabets(event);" maxlength="50"
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
															style="color: red;">* </strong>Year of Passing</label>
													</div>
													<div class="col-md-8">

														<input type="text" id="spouse_yrOfPass"
															name="spouse_yrOfPass" class="form-control autocomplete"
															autocomplete="off" onkeypress="return isNumber(event)"
															maxlength="4" />
													</div>
												</div>

											</div>

											<div class="col-md-6">
												<div class="row form-group">
													<div class="col-md-4">
														<label class=" form-control-label"><strong
															style="color: red;">* </strong>Div/Grade/PCT(%)</label>
													</div>
													<div class="col-md-8">
														<select name="spouse_div_class" id="spouse_div_class"
															class="form-control-sm form-control" onchange="fn_otherShowHide(this,'other_div_classSpouse','class_otherSpouse')">
															<option value="0">--Select--</option>
															<c:forEach var="item" items="${getDivclass}"
																varStatus="num">
																<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
															</c:forEach>
														</select>
													</div>
												</div>

											</div>


										</div>

										<div class="col-md-12" id="other_div_classSpouse"
											style="display: none;">
											<div class="col-md-6">
												<div class="row form-group">
													<div class="col-md-4" align="left">
														<label class=" form-control-label"><strong
															style="color: red;">* </strong>Div/Grade Other </label>
													</div>
													<div class="col-md-8">
														<input type="text" id="class_otherSpouse" name="class_otherSpouse"
															class="form-control autocomplete" autocomplete="off" 
															onkeypress="return onlyAlphabets(event);" maxlength="50">
													</div>
												</div>
											</div>

										</div>

										<div class="col-md-12">
											<div class="col-md-6">
												<div class="row form-group">
													<div class="col-md-4">
														<label class=" form-control-label"><strong
															style="color: red;">* </strong>Subjects</label>
													</div>
													<div class="col-md-8">
														<div class="multiselect">

															<div class="selectBox" onclick="showCheckboxesSpouse()">
																<select name="spouse_sub_quali" id="spouse_sub_quali"
																	class="form-control-sm form-control">
																	<option>Select Subjects</option>
																</select>
																<div class="overSelect"></div>
															</div>
															<div id="spouse_checkboxes" class="checkboxes"
																style="max-height: 200px; overflow: auto;">
																<input type="text" id="spouse_searchSubject"
																	name="spouse_searchSubject"
																	placeholder="Search Subjects"
																	class="form-control autocomplete" autocomplete="off">
																<c:forEach var="item" items="${getSubject}"
																	varStatus="num">
																	<label for="one" class="spouse_subjectlist"> <input
																		type="checkbox" name="spouse_multisub"
																		value="${item[0]}" /> ${item[1]}
																	</label>
																</c:forEach>
															</div>

														</div>
													</div>

												</div>
											</div>
											<div class="col-md-6">
												<div class="row form-group">
													<div class="col-md-4">
														<label class=" form-control-label"><strong
															style="color: red;">* </strong>Institute & Place</label>
													</div>
													<div class="col-md-8">

														<input type="text" id="spouse_institute_place"
															name="spouse_institute_place"
															class="form-control autocomplete" autocomplete="off"
															onkeypress="return onlyAlphabets(event);" maxlength="50"
															oninput="this.value = this.value.toUpperCase()">
													</div>
												</div>
												<div class="row form-group">
													<div class="col-md-4">
														<label class=" form-control-label"><strong
															style="color: red;"></strong>Selected Subject</label>
													</div>
													<div class="col-md-8">
														<div class="row">

															<div id="spouse_quali_sub_list"
																style="max-height: 200px; overflow: auto; width: 100%; border: 1px solid;">

															</div>

														</div>

													</div>
												</div>

											</div>


										</div>


							</div>
								</div>
									
							</div>
								<div class="card-footer" align="center" class="form-control"> 
										<input type="button" id="mrgbtn_save" class="btn btn-primary btn-sm" value="Submit for Approval" onclick="return married_save_fn();" >
										<input type="button" id="mrgqualibtn_save" class="btn btn-primary btn-sm" value="Submit for Approval" onclick="return spouse_qualification_save_fn();" style="display: none">		              
							   </div>
						</div>
					</div>
				</div>
			</div>
<!-- 		</div> -->
	</form>
	<!-- END MARITAL EVENTS DETAIL -->
		<!--  START DETAILS OF CHILDRENS -->
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
										<th>Name</th>
										<th>Date of Birth</th>
										<th>If Specially Abled Child </th>
										<th>Relationship</th>
										<th>If Adopted Child </th>
										<th>Adoption Date </th>
										<th>Aadhar Card No </th>
										<th>PAN Card No </th>
										<th>Service/Ex-Service</th>
										<th>Services</th>
										<th>Personal No.</th>
										<th>Other Service</th>
										<th>Action</th>
									</tr>
								</thead>
								<tbody data-spy="scroll" id="m_children_detailstbody" style="min-height: 46px; max-height: 650px; text-align: center;">
									<tr id="tr_children1">
										<td class="child_srno" readonly>1</td>
										<td>
										<input type="text" id="sib_name1" name="sib_name1"  class="form-control autocomplete" autocomplete="off" maxlength="50" onkeypress="return onlyAlphabets(event);" oninput="this.value = this.value.toUpperCase()">
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
										<input type="text" id="aadhar_no1" name="aadhar_no1"  class="form-control autocomplete" autocomplete="off" maxlength="14" onkeypress="return isAadhar(this,event);">
										</td>
										<td>
										<input type="text" id="pan_no1" name="pan_no1"  class="form-control autocomplete" autocomplete="off" maxlength="10" onchange="isPAN(this);" oninput="this.value = this.value.toUpperCase()">
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
												autocomplete="off" maxlength="15"
												onkeypress="return /[0-9a-zA-Z]/i.test(event.key)"
												oninput="this.value = this.value.toUpperCase()">
											</td>
											<td><input type="text" id="other_child_ser1"
												name="other_child_ser1" class="form-control autocomplete"
												autocomplete="off"
												onkeypress="return onlyAlphabets(event);"
												oninput="this.value = this.value.toUpperCase()">
											</td>
										<td>
										<a class="btn btn-primary btn-sm" value="SAVE" title="Submit for Approval" id="m_children_details_save1"
										   onclick="m_children_details_save_fn(1);"><i class="fa fa-save"></i>
										</a> 
										<a style="display: none;" id="m_children_details_add1" class="btn btn-success btn-sm" 
										value="ADD" title="ADD" onclick="m_children_details_add_fn(1);">
										<i class="fa fa-plus"></i>
										</a>
										<a style="display: none;" id="m_children_details_remove1" class="btn btn-danger btn-sm" value="REMOVE" title="REMOVE"
										onclick="m_children_details_remove(1);"><i class="fa fa-trash"></i>
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
<!-- 		</div> -->
		<!-- END DETAILS OF CHILDRENS -->
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
						                   <input type="text" id="authority" name="authority" class="form-control autocomplete" maxlength="100" autocomplete="off" maxlength="50" onkeyup="return specialcharecter(this)"> 						                   
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
						                   <input type="text" id="nok_name" name="nok_name" class="form-control autocomplete" autocomplete="off" maxlength="50" onkeypress="return onlyAlphabets(event);"
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
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> STATE/PROVINCE</label>
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
					                	<input type="text" id="ctry_other" name="ctry_other" maxlength="100" class="form-control autocomplete" autocomplete="off" onkeypress="return onlyAlphabets(event);">
					                	 </div>
						            </div>
	              				</div>
	              				<div class="col-md-6" id = "nok_other_st" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">STATE/PROVINCE OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="st_other" name="st_other" maxlength="100" class="form-control autocomplete" autocomplete="off" onkeypress="return onlyAlphabets(event);">
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
	              				<div class="col-md-6" >
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> TEHSIL/MUNICIPALITY</label>
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
					                	<input type="text" id="dist_other" name="dist_other" maxlength="100" class="form-control autocomplete" autocomplete="off" onkeypress="return onlyAlphabets(event);">
					                	 </div>
						            </div>
	              				</div>
	              				<div class="col-md-6" id = "nok_other_te" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">TEHSIL/MUNICIPALITY OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="nok_tehsil_other" name="nok_tehsil_other" maxlength="100" class="form-control autocomplete" autocomplete="off" 	onkeypress="return onlyAlphaNumeric(event);">
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
						                   <input type="text" id="nok_pin" name="nok_pin" class="form-control autocomplete" autocomplete="off" onkeypress="return isNumber0_9Only(event)" maxlength="6"> 						                   
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
						                 <input type="text" id="nok_rail" name="nok_rail" class="form-control autocomplete" autocomplete="off" maxlength="100" 	onkeypress="return onlyAlphaNumeric(event);"> 						                     
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
						                   <input type="text" id="nok_mobile_no" name="nok_mobile_no" class="form-control autocomplete" autocomplete="off" onkeypress="return isNumber0_9Only(event)" maxlength="10"> 						                   
						                </div>
						            </div>
	              				</div>
	              			</div>
							</div>
					    <div class="card-footer" align="center" class="form-control">
		              		<input type="button" class="btn btn-primary btn-sm" value="Submit for Approval" onclick="validate_save_nok_details();">		              
			           
			            </div> 
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	<!-- END NOK -->
	<!-- START DESIGNATION -->
	<form id="form_designation_details_form">
		<div class="card">
			<div class="panel-group" id="accordion5">
				<div class="panel panel-default" id="d_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title lightgreen" id="d_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion5" href="#" id="d_final" onclick="divN(this)"
								><b>CHANGE IN DESIGNATION</b></a>
						</h4>
					</div>
					<div id="collapse1d" class="panel-collapse collapse">
						<div class="card-body card-block">
						<div align="left">
						<input type="button" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('change_des');">
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
						               <input type="hidden" id="des_id" name="des_id" value="0" class="form-control autocomplete" autocomplete="off" > 							                 
						                   
						                   <input type="text" id="des_authority" name="des_authority" class="form-control autocomplete" maxlength="100" autocomplete="off" maxlength="50" onkeyup="return specialcharecter(this)"> 						                   
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
						                  <input type="text" name="date_authority_des" id="date_authority_des" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
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
									<label class=" form-control-label"> Designation</label>
								</div>
								<div class="col-md-8">
									<select name="designation" id="designation"
										class="form-control-sm form-control">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getDesignationList}"
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
										style="color: red;"> *</strong> Date of Designation</label>
								</div>
								<div class="col-md-8">
									<input type="text" name="designation_date"
										id="dt_of_designation" maxlength="10"
										onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control"
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
		              		<input type="button" class="btn btn-primary btn-sm" value="Submit for Approval" onclick="validate_save_designation_details();">		              
			           
			            </div> 
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	<!-- END DESIGNATION -->
	
	<!-- START TENURE -->
	
	<form id="form_tenure_details_form">
		<div class="card">
			<div class="panel-group" id="accordion8">
				<div class="panel panel-default" id="j_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title lightgreen" id="j_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion8" href="#" id="j_final" onclick="divN(this)"
								><b>CHANGE IN TENURE DATA</b></a>
						</h4>
					</div>
					<div id="collapse1j" class="panel-collapse collapse">
						<div class="card-body card-block">
						<div align="left">
						<input type="button" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('change_tenure');">
						</div>
						<br>
							<div class="row">
							<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Unit SUS No </label>
						                </div>
						                <div class="col-md-8">
						                <input type="hidden" id="ten_id" name="ten_id" value="0" class="form-control autocomplete" autocomplete="off" > 							                 
						                
						                   <input type="text" id="unit_sus_no" name="unit_sus_no" class="form-control autocomplete" autocomplete="off" maxlength="8" onkeypress="return AvoidSpace(event)"  onkeyup="return specialcharecter(this)" > 
						                </div>
						            </div>
	              				</div>
	              				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Unit Posted To </label>
						                </div>
						                <div class="col-md-8">
						                  <input type="text" id="unit_posted_to" name="unit_posted_to" class="form-control autocomplete" autocomplete="off" maxlength="50" onkeyup="return specialcharecter(this)">
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
											<input type="text" name="ten_from" id="ten_from" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
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
										 <input type="text" name="ten_to" id="ten_to" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY" >
										</div>
									</div>
								</div>
							</div>
	              				
	              			
							</div>
					    <div class="card-footer" align="center" class="form-control">
		              		<input type="button" class="btn btn-primary btn-sm" value="Submit for Approval" onclick="validate_save_tenure_details();">		              
			           
			            </div> 
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	
	<!-- END TENURE -->
	
	<!-- START ADDRESS -->
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
						<br>
						 <input type="button" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('change_in_address');">
							<div class="row" >
									<div class="col-md-12">	
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;"> </strong>Authority</label>
						                </div>
						                <div class="col-md-8">
						                 <input type="text" name="authority" id="addr_authority" maxlength="100" class="form-control autocomplete" autocomplete="off" maxlength="50" onkeyup="return specialcharecter(this)">					               
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;"> </strong>Date of Authority</label>
						                </div>
						                <div class="col-md-8">
						                  <input type="text" name="date_authority" id="addr_date_authority" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
						                </div>
						            </div>
	              				</div>
	              			</div>	
							
							<label class=" form-control-label" style="margin-left:10px;"><h4> Present Domicile</h4></label>
							<div class="col-md-12">	
		 				<div class="col-md-6">
              					<div class="row form-group">
					               <div class="col-md-4">
					                    <label class=" form-control-label"><strong style="color: red;">* </strong>COUNTRY</label>
					                </div>
					                <div class="col-md-8">
					               						                   
					                <select name="pre_country" id="pre_country" class="form-control"   onchange="fn_pre_domicile_Country()">
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
					                    <label class=" form-control-label"><strong style="color: red;">* </strong> STATE/PROVINCE</label>
					                </div>
					                <div class="col-md-8">
						                 <input type="hidden" id="addr_ch_id" name="addr_ch_id" value="0" class="form-control autocomplete" autocomplete="off" > 							                     
					               			<select name="pre_domicile_state" id="pre_domicile_state" class="form-control" onchange="fn_pre_domicile_state();" >
						                        <option value="0">--Select--</option>
												<c:forEach var="item" items="${getMedStateName}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</SELECT> 						               				               
					                </div>
					            </div>
              				</div>
	              			</div>	
	              			<div class="col-md-12">
								<div class="col-md-6" id = "add_other_id" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">COUNTRY OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="pre_country_other" name="pre_country_other" maxlength="100" class="form-control autocomplete" autocomplete="off" onkeypress="return onlyAlphabets(event);">
					                	 </div>
						            </div>
	              				</div>
	              				<div class="col-md-6" id = "add_other_st" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">STATE/PROVINCE OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="pre_domicile_state_other" name="pre_domicile_state_other" maxlength="100" class="form-control autocomplete" autocomplete="off" onkeypress="return onlyAlphabets(event);">
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
						                 <select name="pre_domicile_district" id="pre_domicile_district" class="form-control"  onchange="fn_pre_domicile_district();" >
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
					                    <label class=" form-control-label"><strong style="color: red;">* </strong> TEHSIL/MUNICIPALITY</label>
					                </div>
					                <div class="col-md-8">
					               			<select name="pre_domicile_tesil" id="pre_domicile_tesil" class="form-control"  onchange="fn_pre_domicile_tesil();" >
						                        <option value="0">--Select--</option>
											<c:forEach var="item" items="${getMedCityName}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
											</SELECT> 						               				               
					                </div>
					            </div>
              				</div>
	              			</div>	
	              			<div class="col-md-12">
								<div class="col-md-6" id = "add_other_dt" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">DISTRICT OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="pre_domicile_district_other" name="pre_domicile_district_other" maxlength="100" class="form-control autocomplete" autocomplete="off" onkeypress="return onlyAlphabets(event);">
					                	 </div>
						            </div>
	              				</div>
	              				<div class="col-md-6" id = "add_other_te" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">TEHSIL/MUNICIPALITY OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="pre_domicile_tesil_other" name="pre_domicile_tesil_other"  maxlength="100" class="form-control autocomplete" autocomplete="off" onkeypress="return onlyAlphabets(event);">
					                	 </div>
						            </div>
	              				</div>
	              					</div>
	              		
	              			<label class=" form-control-label" style="margin-left:10px;"><h4> Permanent Address</h4></label>
	              					<div class="col-md-12">	
						<div class="col-md-6">
              					<div class="row form-group">
					               <div class="col-md-4">
					                    <label class=" form-control-label"><strong style="color: red;">* </strong>COUNTRY</label>
					                </div>
					                <div class="col-md-8">
					                <select name="per_home_addr_country" id="per_home_addr_country" class="form-control"  onchange="fn_per_home_addr_Country();" >
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
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> STATE/PROVINCE</label>
						                </div>
						                <div class="col-md-8">
						                	<select name="per_home_addr_state" id="per_home_addr_state" class="form-control"  onchange="fn_per_home_addr_state();" >
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
								<div class="col-md-6" id = "per_home_addr_country_other_hid" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">COUNTRY OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="per_home_addr_country_others" name="per_home_addr_country_others" maxlength="100" class="form-control autocomplete" autocomplete="off" onkeypress="return onlyAlphabets(event);">
					                	 </div>
						            </div>
	              				</div>
	              				<div class="col-md-6" id = "per_home_addr_state_other_hid" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">STATE/PROVINCE OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="per_home_addr_state_others" name="per_home_addr_state_others" maxlength="100" class="form-control autocomplete" autocomplete="off" onkeypress="return onlyAlphabets(event);">
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
						                <select name="per_home_addr_district" id="per_home_addr_district" class="form-control"   onchange="fn_per_home_addr_district();">
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
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> TEHSIL/MUNICIPALITY</label>
						                </div>
						                <div class="col-md-8">
						                 <select name="per_home_addr_tehsil" id="per_home_addr_tehsil" class="form-control" onchange="fn_per_home_addr_tehsil();"  >
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
								<div class="col-md-6" id = "per_home_addr_district_other_hid" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">DISTRICT OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="per_home_addr_district_others" name="per_home_addr_district_others" maxlength="100" class="form-control autocomplete" autocomplete="off" onkeypress="return onlyAlphabets(event);">
					                	 </div>
						            </div>
	              				</div>
	              				<div class="col-md-6" id = "per_home_addr_tehsil_other_hid" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">TEHSIL/MUNICIPALITY OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="per_home_addr_tehsil_others" name="per_home_addr_tehsil_others" maxlength="100" class="form-control autocomplete" autocomplete="off" onkeypress="return onlyAlphabets(event);">
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
										<input type="text" id="per_home_addr_village" name="per_home_addr_village" 	onkeypress="return onlyAlphabets(event);" 
											class="form-control autocomplete" autocomplete="off"  maxlength="50">
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Pin</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="per_home_addr_pin" name="per_home_addr_pin" class="form-control autocomplete" autocomplete="off"  onkeypress="return isNumber0_9Only(event)" maxlength="6"> 						                   
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
						                 <input type="text" id="per_home_addr_rail" name="per_home_addr_rail" 		onkeypress="return onlyAlphaNumeric(event);"  class="form-control autocomplete" autocomplete="off" maxlength="100"> 						                     
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
							 <input type="radio" id="per_home_addr_rural" name="per_home_addr_rural_urban" value="rural" > Rural</label> 
					    	 <label for="lbl_per_home_addr_urban" >
							 <input type="radio" id="per_home_addr_urban" name="per_home_addr_rural_urban" value="urban" > Urban</label> 
					    	 <label for="lbl_per_home_addr_semi_urban">
							 <input type="radio" id="per_home_addr_semi_urban" name="per_home_addr_rural_urban" value="semi_urban" >Semi Urban</label> 
						                </div>
						            </div>
	              				</div> 	
	              				</div>
	              		<div class="col-md-12">	  
	            			<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Border area</label>
						                </div>
						                <div class="col-md-8">
						                	 <label for="border_area">
							 <input type="radio" id="border_area" name="border_area" value="yes"   >Yes</label> 
					    	 <label for="border_area1"> 
				      		 <input type="radio" id="border_area1" name="border_area" value="no" > No</label>
						</div>
						                </div>
						            </div>
	              				</div>
	              			
						           		<div class="col-md-12"></div>
		 
			  <div class="col-md-12">
			 	<label class=" form-control-label"  style="margin-left:10px;"><h4> Present Address</h4></label>
			 </div> 
			 	<div class="col-md-12" style="font-size: 15px;">	
           		<input type="checkbox" id="check_address" name="check_address" onclick="copy_address()">
               	<label for="text-input" class=" form-control-label" style="color: #dd1a3e; margin-left:10px;">  Same as Permanent Address </label>
            </div>
			 	 <div class="col-md-12">	
						<div class="col-md-6">
              					<div class="row form-group">
					               <div class="col-md-4">
					                    <label class=" form-control-label"><strong style="color: red;">* </strong>COUNTRY</label>
					                </div>
					                <div class="col-md-8">
					               						                   
					                <select name="pers_addr_country" id="pers_addr_country" class="form-control" onchange="fn_pers_addr_Country();"  >
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
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> STATE/PROVINCE</label>
						                </div>
						                <div class="col-md-8">
						                	<select name="pers_addr_state" id="pers_addr_state" class="form-control"  onchange="fn_pers_addr_state();" >
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
								<div class="col-md-6" id = "pers_addr_country_other_hid" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">COUNTRY OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="pers_addr_country_other" name="pers_addr_country_other" maxlength="100" class="form-control autocomplete" autocomplete="off" onkeypress="return onlyAlphabets(event);">
					                	 </div>
						            </div>
	              				</div>
	              				<div class="col-md-6" id = "pers_addr_state_other_hid" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">STATE/PROVINCE OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="pers_addr_state_other" name="pers_addr_state_other" maxlength="100" class="form-control autocomplete" autocomplete="off" onkeypress="return onlyAlphabets(event);">
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
						                <select name="pers_addr_district" id="pers_addr_district" class="form-control"  onchange="fn_pers_addr_district();" >
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
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> TEHSIL/MUNICIPALITY</label>
						                </div>
						                <div class="col-md-8">
						                 <select name="pers_addr_tehsil" id="pers_addr_tehsil" class="form-control"   onchange="fn_pers_addr_tehsil();">
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
							<div class="col-md-6" id = "pers_addr_district_other_hid" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">DISTRICT OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="pers_addr_district_other" name="pers_addr_district_other" maxlength="100" class="form-control autocomplete" autocomplete="off" onkeypress="return onlyAlphabets(event);">
					                	 </div>
						            </div>
	              				</div>
	              				<div class="col-md-6" id = "pers_addr_tehsil_other_hid" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">TEHSIL/MUNICIPALITY OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="pers_addr_tehsil_other" name="pers_addr_tehsil_other" maxlength="100" class="form-control autocomplete" autocomplete="off" onkeypress="return onlyAlphabets(event);">
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
											<input type="text" id="pers_addr_village" name="pers_addr_village" 	onkeypress="return onlyAlphabets(event);"  class="form-control autocomplete" autocomplete="off"  maxlength="50">				               
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Pin</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="pers_addr_pin" name="pers_addr_pin" class="form-control autocomplete" autocomplete="off" onkeypress="return isNumber0_9Only(event)" maxlength="6"> 						                   
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
						                 <input type="text" id="pers_addr_rail" name="pers_addr_rail" 		onkeypress="return onlyAlphaNumeric(event);"  class="form-control autocomplete" autocomplete="off"  maxlength="100"> 						                     
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						             <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Is  Rural /Urban/Semi Urban</label>
						                </div>
						                <div class="col-md-8">
						     <label for="pers_addr_rural">
							 <input type="radio" id="pers_addr_rural" name="pers_addr_rural_urban" value="rural" > Rural</label> 
					    	 <label for="pers_addr_urban" >
							 <input type="radio" id="pers_addr_urban" name="pers_addr_rural_urban" value="urban" > Urban</label> 
					    	 <label for="pers_addr_semi_urban">
							 <input type="radio" id="pers_addr_semi_urban" name="pers_addr_rural_urban" value="semi_urban" >Semi Urban</label> 
						                </div>
						            </div>
	              				</div>	
	              				</div>
	              				
	              				<div id="spouse_addressMaindiv" style="display: none">
							
							<div id="spouse_addressInnerdiv" style="display: none">
							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong> COUNTRY</label>
										</div>
										<div class="col-md-8">
											<select name="spouse_addr_Country" id="spouse_addr_Country"
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
												style="color: red;">* </strong> STATE/PROVINCE</label>
										</div>
										<div class="col-md-8">
											<select name="spouse_addr_state" id="spouse_addr_state"
												onchange="fn_spouse_addr_state();"
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
								<div class="col-md-6" id = "spouse_addr_Country_hid" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">COUNTRY OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="spouse_addr_country_other" name="spouse_addr_country_other" maxlength="100" class="form-control autocomplete" autocomplete="off" onkeypress="return onlyAlphabets(event);">
					                	 </div>
						            </div>
	              				</div>
	              				<div class="col-md-6" id = "spouse_addr_state_hid" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">STATE/PROVINCE OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="spouse_addr_state_other" name="spouse_addr_state_other" maxlength="100" class="form-control autocomplete" autocomplete="off" onkeypress="return onlyAlphabets(event);">
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
											<select name="spouse_addr_district" id="spouse_addr_district"
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
												style="color: red;">* </strong> TEHSIL/MUNICIPALITY</label>
										</div>
										<div class="col-md-8">
											<select name="spouse_addr_tehsil" id="spouse_addr_tehsil"
												class="form-control-sm form-control" onchange="fn_spouse_addr_tehsil();">
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
								<div class="col-md-6" id = "spouse_addr_district_hid" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">DISTRICT OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="spouse_addr_district_other" name="spouse_addr_district_other" maxlength="100" class="form-control autocomplete" autocomplete="off" onkeypress="return onlyAlphabets(event);">
					                	 </div>
						            </div>
	              				</div>
	              				<div class="col-md-6" id = "spouse_addr_tehsil_hid" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">TEHSIL/MUNICIPALITY OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="spouse_addr_tehsil_other" name="spouse_addr_tehsil_other" maxlength="100" class="form-control autocomplete" autocomplete="off" onkeypress="return onlyAlphabets(event);">
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
											<input type="text" id="spouse_addr_village"
												name="spouse_addr_village" class="form-control autocomplete" 	onkeypress="return onlyAlphabets(event);"
											
												>
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
											<input type="text" id="spouse_addr_pin" name="spouse_addr_pin"
												class="form-control autocomplete" autocomplete="off"
												onkeypress="return isNumber(event)" maxlength="6">
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
											<input type="text" id="spouse_addr_rail" name="spouse_addr_rail"
												class="form-control autocomplete" autocomplete="off"
													onkeypress="return onlyAlphaNumeric(event);"
												oninput="this.value = this.value.toUpperCase()">
										</div>
									</div>
								</div>
								<div class="col-md-6">
	              					<div class="row form-group">
						             <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Is  Rural /Urban/Semi Urban</label>
						                </div>
						                <div class="col-md-6">
						     <label for="spouse_addr_rural">
							 <input type="radio" id="spouse_addr_rural" name="spouse_addr_rural_urban" value="rural" > Rural</label> 
					    	 <label for="spouse_addr_urban" >
							 <input type="radio" id="spouse_addr_urban" name="spouse_addr_rural_urban" value="urban" > Urban</label> 
					    	 <label for="spouse_addr_semi_urban">
							 <input type="radio" id="spouse_addr_semi_urban" name="spouse_addr_rural_urban" value="semi_urban" >Semi Urban</label> 
						                </div>
<!-- 						                 <div class="popup" onclick="myFunction()"> -->
<!-- 												<i class="fa fa-question-circle" -->
<!-- 													style="font-size: 28px; color: black"></i> <span -->
<!-- 													class="popuptext" id="myPopup">Urban :An urban area -->
<!-- 													is The region surrounding a city. Rural :Rural areas are -->
<!-- 													The opposite of urban areas, Rural areas Rural areas often -->
<!-- 													called The COUNTRY. Semi Urban : Semi Urban are large -->
<!-- 													residential areas that surround main cities. </span> -->
<!-- 											</div> -->
						            </div>
	              				</div>
							</div>
							<div class="col-md-12">
								<div class="col-md-6" >
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Stn HQ SUS No</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="Stn_HQ_sus_no" name="Stn_HQ_sus_no" class="form-control autocomplete" autocomplete="off"   >
					                	 </div>
						            </div>
	              				</div>
	              				<div class="col-md-6" >
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Stn HQ Name</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="Stn_HQ_unit_name" name="Stn_HQ_unit_name" class="form-control autocomplete" autocomplete="off"   >
					                	 </div>
						            </div>
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


	
	
</div>
</div>
</div>
<a href="#" class="go-top fa fa-arrow-up" style="display:none"></a>




<script>
$(document).ready(function() {
	$.ajaxSetup({
		async : false
	});
	childCB(1);
	 
	
	$("#personnel_noV").val('${personnel_no6}');
	$("#statusV").val('${status6}');
	$("#rankV").val('${rank6}');
	$("#civ_idV").val('${civ_id6}');
	$("#unit_nameV").val('${unit_name6}');
	$("#unit_sus_noV").val('${unit_sus_no6}');
	 	
	
	if ('${status}' == 1){
		$("#back_bt").show();
		$('#status').val('${status}');
	}
	
	if ('${status}' == 2){
		$("#back_bt_div").show();
		$('#status').val('${status}');
	}
	
	$('#personnel_no').val('${emp_no_edit}');
	if ('${emp_no_edit}' != ""){
		
		var personnel_no = '${emp_no_edit}';
		 $.post('GetEmpNoData12?' + key + "=" + value,{ personnel_no : personnel_no },function(j) {
			 if(j!=""){
				 
			
		    	
		    	var civ_id =j[0][0]; 
		    	
		    	 $.post('GetCivDataApprove_civdata?' + key + "=" + value,{ civ_id : civ_id },function(k) {
		    		 if(k.length > 0)
		    		 {
		    			 
		    			  $('#div_cda_acnt_no').show(); 
		    			  curr_marital_status=k[0][13];  

                    if(curr_marital_status!=8)
	    			  {
	    				  $("#marr_quali_radioDiv").remove();
	    			  }else{
	    				  $('#marital_eventDiv').hide();
	    			  }
	    			  

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
	addMaxLengthToInput(auth_length);
}); 
$("a").click(function(){
	addMaxLengthToInput(auth_length);

});

</script>
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
		
		
		 if (obj.id == "f_final") {
			if(!hasC){
				$("#spouse_quali_radioDiv").hide();
				getfamily_marriageDetails();
				getSpouseQualificationData();
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
			}
		}
		else if (obj.id == "d_final") {
			if(!hasC){
				get_changedesignation_details();
			}
		}
		else if (obj.id == "i_final") {
			if(!hasC){
				get_changeaddress_details();
			}
		}
		else if (obj.id == "j_final") {
			if(!hasC){
				get_changetenure_details();
			}
		}
		
		
		
		addMaxLengthToInput(auth_length);
}
</script>

<script>
//*************************************MAIN Personel Number Onchange*****************************//
var curr_marital_status=0;
var serving_status="SERVING";
var dateOfAttestaion='';
function personal_number()
{
	
	$("#submit_data").show();
	if($("input#personnel_no").val().trim()==""){
		alert("Please select Personal No");
		$("input#personnel_no").focus();
		return false;
	}
	else{
		$("#div_update_data").show();
	}
	
	 var personnel_no = $("input#personnel_no").val();

	 $.post('GetEmpNoData12?' + key + "=" + value,{ personnel_no : personnel_no },function(j) { 
		 if(j!=""){
			$("#civ_id").val(j[0][0]);
	    	var civ_id = j[0][0]; 
	    	
	         $.post('GetCivDataApprove_civdata?' + key + "=" + value,{ civ_id : civ_id },function(k) {
	    		
	    		 if(k.length > 0)
	    		 {
	    			 
	    			  curr_marital_status=k[0].marital_status;
	    			  
	    			  if(curr_marital_status!=8)
	    			  {
	    				  $("#marr_quali_radioDiv").remove();
	    			  }else{
	    				  $('#marital_eventDiv').hide();
	    			  }
	    			  
	    			  $('#marital_event').val('0');
	    			  $('#marriage_div').show();
	    			  
	    			   $("#cadet_name").text(k[0].full_name);
	    			 	if(k[0].dob==null){
	    		    		$("#dob").text("");    
	    		    	}
	    		    	else{
	    		    		 $("#dob").text(convertDateFormate(k[0].dob));  
	    		    		 $("#dob_date").val(ParseDateColumncommission(k[0].dob));
	    		    	}
	    			    $("#marital_status_p").text(k[0].marital_status);
	    		    	
	    		    	if(k[0].date_of_tos==null){
	    		    		$("#p_tos").text("");    
	    		    	}
	    		    	else{
	    		    		 $("#p_tos").text(convertDateFormate(k[0].date_of_tos));  
	    		    	}
	    		    	$("#tos_date").val(ParseDateColumncommission(k[0].date_of_tos));
	    		    	$("#app_sus_no").text(k[0].sus_no);
	    		    	$("#personnel_no").text(k[0].employee_no);
	    		    	$("#auth").text(k[0].authority);
	    		    	
	    		    	
	    			    
	    				
	    		    	
	    		    	
	    		    	
	    		    	$("#old_att_sus_no").text(k[0].sus_no);
	    		    	var att_sus_no = $("#old_att_sus_no").text(k[0].sus_no);
	    				
	    		    	var sus_no =k[0].sus_no;
	    		    	if(sus_no!=""){
	    		    		getunit(sus_no);
	    		    	}
	    		    	if(att_sus_no!=""){
	    		    		getunit(att_sus_no);
	    		    	}
	    		    	
	    		    	function getunit(val) {	
	    		            $.post("getTargetUnitNameList?"+key+"="+value, {
	    		            	sus_no : sus_no
	    		        }, function(j) {
	    		                
	    		                

	    		        }).done(function(j) {
	    		    				   var length = j.length-1;
	    		    	                   var enc = j[length].substring(0,16); 
	    		    	                   
	    		    	                   $("#app_unit_name").text(dec(enc,j[0])); 
	    		    	                   $("#old_att_unit_name").text(dec(enc,j[0])); 
	    		        }).fail(function(xhr, textStatus, errorThrown) {
	    		        });
	    		    } 
	    		 }
	   });
	    	
	 		$.ajaxSetup({
					async : false
				});
	 		
	    	
	 } 
  }); 
	 $("input#personnel_no").attr('readonly', true);
}
$("input#personnel_no").keyup(function(){
	var employee_no = this.value;
		 var susNoAuto=$("#personnel_no");
		  susNoAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        	type: 'POST',
			    	url: "getemp_noListApproved_CIV?"+key+"="+value,
		        data: {employee_no:employee_no},
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
		        	  alert("Please Enter Approved Employee No");
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

function fn_getUnitnamefromSus(sus_no,e){
	
	$.post("getTargetUnitNameList?"+key+"="+value, {sus_no:sus_no}).done(function(data) {
		var l=data.length-1;
		  var enc = data[l].substring(0,16);    	   	 
	 		e.value=dec(enc,data[0]);
		}).fail(function(xhr, textStatus, errorThrown) {
});
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
		+ '<td style="width: 10%;"><input type="text" ' + '	id="other_child_ser' + chi + '" name="other_child_ser' + chi + '" ' + '	class="form-control" autocomplete="off" onkeypress="return onlyAlphabets(event);"></td>'
		
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
	//datepicketDate('sib_adop_date'+chi);
	childCB(chi);
	otherfunction(chi);
}

function m_children_details_remove_fn(R){
	var rc = confirm("Are You Sure! You Want To Delete");
	 if(rc){
	var sib_ch_id=$('#sib_ch_id'+R).val();
	  $.post('m_children_details_delete_action_civ?' + key + "=" + value, {sib_ch_id:sib_ch_id }, function(data){ 
			  
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
//save children
function m_children_details_save_fn(ps) {
	
	var sib_name = $('#sib_name' + ps).val().trim();
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
	var civ_id = $('#civ_id').val();
	
	
	var child_ser = $("select#child_service"+ps).val();
	var child_pers_no = $("#child_personal_no"+ps).val().trim();
	if($('#child-ex' + ps).is(":checked")){
		var child_ex = "Yes";
	}else{
		var child_ex = "";
	}
	var other_child_ser = $("#other_child_ser"+ps).val().trim();
	
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
			  if (sib_adop_date == "DD/MM/YYYY" || sib_adop_date.trim() == "") {
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
			if ((sib_adop_date != "DD/MM/YYYY" && sib_adop_date.trim()!="")) {
				alert("Please Check the field 'If Adopted Child' Yes Otherwise Pl,Clear Adoption Date.");
				$('#sib_adop_date' + ps).focus();
				return false;
			} 
		}
		 if (aadhar_no.trim()!="" && aadhar_no.length < 12) {
				alert("Please Enter Valid Aadhar Card No");
				$('#aadhar_no' + ps).val("");
				$('#aadhar_no' + ps).focus();
				return false;
			}
	 
		
	
		var child = $( "#child_service"+ps+" option:selected" ).text().toUpperCase();
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
			if (child == "OTHER" || child == "OTHERS"){
					if($('#other_child_ser' + ps).val().trim()==""){
						alert("Please Enter Other Child Service");
					$('#other_child_ser' + ps).focus();
					return false;
					}
					
					if(lengthValidation($("input#other_child_ser"+ps).val().trim(),auth_length)){
						alert("Other Child Service Length Should be Less or Equal To 100")
						$("#other_child_ser"+ps).focus();
						return false;
					}
				}
		}	
		else{
			child="";
		}


	$.post('m_children_details_action_civ?' + key + "=" + value, {
		sib_name : sib_name,
		sib_date_of_birth : sib_date_of_birth,sib_type:sib_type,
		sib_relationship : sib_relationship,sib_adopted:sib_adopted,sib_adop_date:sib_adop_date,aadhar_no:aadhar_no,pan_no:pan_no,
		sib_ch_id : sib_ch_id,
		civ_id : civ_id,
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
	
	var civ_id = $('#civ_id').val();
	var i = 0;
	$.post('getm_children_detailsData_Civ?' + key + "=" + value,{civ_id:civ_id},function(j) {
		var v = j.length;
		if (v != 0) {
			
			$('#m_children_detailstbody').empty();
			for (i; i < v; i++) {
				x = i + 1;
				var dob = ParseDateColumncommission(j[i].dob);
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
					+ '<td style="width: 10%;"><input type="text" ' + '	id="other_child_ser' + x + '" name="other_child_ser' + x + '" ' + '	class="form-control" autocomplete="off" onkeypress="return onlyAlphabets(event);"></td>'
					
					
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

		$('#child_service' + a).attr('readonly', true);
		$('#child_personal_no' + a).attr('readonly', true);
		$('#other_child_ser' + a).attr('readonly', true);
		$('#child_service' + a).val(0);
		$('#child_personal_no' + a).val("");
		$('#other_child_ser' + a).val("");
	}
	
}
function otherfunction(k){
	var child = $( "#child_service"+k+" option:selected" ).text();
	
	if (child == "OTHER"){
		$('#other_child_ser' + k).attr('readonly', false);
		$('#child_personal_no' + k).attr('readonly', true);
		$('#child_personal_no' + k).val('');
	}
	if (child != "OTHER"){
		$('#other_child_ser' + k).attr('readonly', true);
		$('#child_personal_no' + k).attr('readonly', false);
		$('#other_child_ser' + k).val('');
	}
}

function adoption(a) {
	if ($('#sib_adopted'+a).is(":checked"))
	{
		$('#sib_adop_date' + a).attr('readonly', false);
		datepicketDate('sib_adop_date'+chi);
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
	 if ($("input#gmail").val().trim()=="") {
			alert("Please Enter Gmail ");
			$("input#gmail").focus();
			return false;
		}
	 if(!validateEmail(document.getElementById('gmail'))){
		 $("input#gmail").focus();
			return false;
	 }
	 /* if ($("input#nic_mail").val().trim()=="") {
			alert("Please Enter NIC Mail ");
			$("input#nic_mail").focus();
			return false;
		} */
		if($("input#nic_mail").val()!=""){
			 if(!validateEmail(document.getElementById('nic_mail'))){
				 $("input#nic_mail").focus();
					return false;
			 }
		}
	
	 if ($("input#mobile_no").val().trim()=="" || $("input#mobile_no").val().trim().length<10 || $("input#mobile_no").val().trim().length>10) {
			alert("Please Enter Mobile No ");
			$("input#mobile_no").focus();
			return false;
		}
	    var formvalues=$("#form_cda_acnt_no").serialize();
		var cda_id=$("#cda_id").val();	
		var civ_id=$("#civ_id").val();	
		formvalues+="&cda_id="+cda_id+"&civ_id="+civ_id;
		 $.post('cda_acnt_noaction_jco?' + key + "=" + value,formvalues, function(data){
			      
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

	var civ_id=$("#civ_id").val();
	$.post('cda_acnt_no_GetData_jco?' + key + "=" + value,{ civ_id:civ_id}, function(j){
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
</script>
<script>	
//************************************* START NON EFFECTIVE DETAIL*****************************//
function getNon_effective(){
 
	 var civ_id=$('#civ_id').val(); 
	
	 var i=0;
	 $.ajaxSetup({
         async : false
 });
	  $.post('getNon_JCOeffective?' + key + "=" + value, {civ_id:civ_id }, function(j){
			var v=j.length;		
			if(v!=0){
				$('#nf_id').val(j[i].id);
				$("#check_per_address").prop("checked", true);
				 $("#non_ef_authority").val(j[i].non_ef_authority);
				 $("#date_of_authority_non_ef").val(ParseDateColumncommission(j[i].date_of_authority_non_ef));
				 $("#cause_of_non_effective").val(j[i].cause_of_non_effective);
				 $("#date_of_non_effective").val(ParseDateColumncommission(j[i].date_of_non_effective));
			}
	  });
	
}

function get_non_eff_address_details(){
	
	var civ_id=$("#civ_id").val();
	if($('#nf_id').val()!="" && $('#nf_id').val()!="0"){
	$.post('address_details_GetJCOData?' + key + "=" + value,{civ_id:civ_id }, function(j){
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
	var civ_id=$('#civ_id').val();

	var status=$('#status').val();
	var nf_id=$('#nf_id').val();
	var enroll_date=$('#enroll_date').val(); 
	var perm_home_addr_country_text = $("#perm_home_addr_country option:selected").text().toUpperCase();
	
	var formdata=$('#form_non_effective').serialize();
	 
	formdata+="&civ_id="+civ_id+"&enroll_date="+enroll_date;	
	formdata+="&perm_home_addr_country_text="+perm_home_addr_country_text;
	if ($("input#non_ef_authority").val().trim()=="") {
			alert("Please Enter Authority ");
			$("input#non_ef_authority").focus();
			return false;
		}
	if(lengthValidation($("input#non_ef_authority").val().trim(),auth_length)){
		alert("Authority Length Should be Less or Equal To 100")
		$("#non_ef_authority").focus();
		return false;
	}
		 if ($("input#date_of_authority_non_ef").val().trim()=="" || $("input#date_of_authority_non_ef").val() == "DD/MM/YYYY") {
			alert("Please Select Date of Authority ");
			$("input#date_of_authority_non_ef").focus();
			return false;
		} 
		 if(getformatedDate($("input#enroll_date").val()) > getformatedDate($("#date_of_authority_non_ef").val())) {
				alert("Authority Date should be Greater than Enrollment Date");
				$("input#date_of_authority_non_ef").focus();
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

		 if(getformatedDate($("input#enroll_date").val()) > getformatedDate($("#date_of_non_effective").val())) {
				alert("Date of Non Effective  should be Greater than Enrollment Date");
				$("input#date_of_non_effective").focus();
				return false;
		  }
		 
		if ($("select#perm_home_addr_country").val() == "0") {
			alert("Please select The Country");
			$("select#perm_home_addr_country").focus();
			return false;
		}
		var text9 = $("#perm_home_addr_country option:selected").text();
		if(text9 == "OTHERS" && $("input#perm_home_addr_country_other").val().trim()==""){
			alert("Please Enter Country Other");
			$("input#perm_home_addr_country_other").focus();
			return false;
		}
		if(text9 == "OTHERS" ){
			if(lengthValidation($("input#perm_home_addr_country_other").val().trim(),auth_length)){
				alert("Country Other Length Should be Less or Equal To 100")
				$("#perm_home_addr_country_other").focus();
				return false;
			}
		}
		if ($("select#perm_home_addr_state").val() == "0") {
			alert("Please Select State/Province");
			$("select#perm_home_addr_state").focus();
			return false;
		}
		var text10 = $("#perm_home_addr_state option:selected").text();
		if(text10 == "OTHERS" && $("input#perm_home_addr_state_other").val().trim()==""){
			alert("Please Enter State/Province Other");
			$("input#perm_home_addr_state_other").focus();
			return false;
		}
		
		if(text10 == "OTHERS" ){
			if(lengthValidation($("input#perm_home_addr_state_other").val().trim(),auth_length)){
				alert("State/Province Other Length Should be Less or Equal To 100")
				$("#perm_home_addr_state_other").focus();
				return false;
			}
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
		
		if(text11 == "OTHERS" ){
			if(lengthValidation($("input#perm_home_addr_district_other").val().trim(),auth_length)){
				alert("District Other Length Should be Less or Equal To 100")
				$("#perm_home_addr_state_other").focus();
				return false;
			}
		}
		
		if(perm_home_addr_country_text!="BHUTAN"){
			if ($("select#perm_home_addr_tehsil").val() == "0") {
				alert("Please Select Tehsil/Municipality");
				$("select#perm_home_addr_tehsil").focus();
				return false;
			}
			var text12 = $("#perm_home_addr_tehsil option:selected").text();
			if(text12 == "OTHERS" && $("input#perm_home_addr_tehsil_other").val().trim()==""){
				alert("Please Enter Tehsil/Municipality Other");
				$("input#perm_home_addr_tehsil_other").focus();
				return false;
			}
			
			if(text12 == "OTHERS" ){
				if(lengthValidation($("input#perm_home_addr_tehsil_other").val().trim(),auth_length)){
					alert("Tehsil/Municipality Other Length Should be Less or Equal To 100")
					$("#perm_home_addr_tehsil_other").focus();
					return false;
				}
			}
		}
		if ($("input#perm_home_addr_village").val().trim()=="") {
			alert("Please Enter  Village/Town/City");
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
			alert("Please select The  Rural /Urban/Semi Urban ");
			return false;
		}
		var b = $('input:radio[name=border_area]:checked').val();
		if (b == undefined) {
			alert("Pl ease select The Border area ");
			return false;
		}

		$.post('non_effectiveJCOAction?' + key + "=" + value, formdata,
				function(data) {
			
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
		 
		
		var civ_id = $("#civ_id").val();
		if ($("#check_per_address").prop('checked') == true) {
			
			$.post('getPerAddressDataStatusJCO1?' + key + "=" + value, {
			
				civ_id : civ_id
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
		var obj=document.getElementById('perm_home_addr_country');
		FnTehsilDisableEnable(obj,'perm_home_addr_tehsil','Ot_perm_hm_addr_teh_div','perm_home_addr_tehsil_other');
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
		$("#religion_other").val("");
	}
}

function validate_Religion_save_fn(){
	
	if ($("input#rel_authority").val().trim()=="") {
		alert("Please Enter Authority");
		$("input#rel_authority").focus();
		return false;
	}
	if(lengthValidation($("input#rel_authority").val().trim(),auth_length)){
		alert("Authority Length Should be Less or Equal To 100")
		$("#rel_authority").focus();
		return false;
	}
 if ($("input#rel_date_of_authority").val() == "DD/MM/YYYY" || $("input#rel_date_of_authority").val().trim()=="") {
		alert("Please Enter Date of Authority ");
		$("input#rel_date_of_authority").focus();
		return false;
	}
 
 	if(getformatedDate($("input#enroll_date").val()) >= getformatedDate($("#rel_date_of_authority").val())) {
		alert("Authority Date should be Greater than Enrollment Date");
		$("input#rel_date_of_authority").focus();
		return false;
	}
 if ($("select#religion").val() == "0") {
		alert("Please Select Religion ");
		$("#religion").focus();
		return false;
	}
 
 
 
  var rel_ot=$('#religion_other').val();
  var rel_o = $("#religion option:selected").text();
	if (rel_o == "OTHERS" && rel_ot.trim()=="") {
		alert("Please Enter Other Religion");
		$("#religion_other").focus();
		return false;
	} 
	var civ_id=$('#civ_id').val();
	
	var religion=$('#religion').val();
	var rel_id=$('#rel_id').val();
	var enroll_date=$('#enroll_date').val();
	var formdata=$('#form_religion').serialize();
	formdata+="&civ_id="+civ_id+"&enroll_date="+enroll_date;	
	   $.post('religion_action_jco?' + key + "=" + value, formdata, function(data){		      
	          if(data=="update")
	        	  alert("Data Updated Successfully");
	          else if(parseInt(data)>0){
	        	
	        	 $('#rel_id').val(data);
	        	  alert("Data Saved Successfully")
	          }
	          else
	        	  alert(data);
	    		          
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});	
}

function get_religion(){
	  
	 var civ_id=$('#civ_id').val(); 
	 var i=0;
	  $.post('religion_GetData_jco?' + key + "=" + value, {civ_id:civ_id}, function(j){
			var v=j.length;
			
			if(v!=0){
				$('#rel_id').val(j[i].id);
				 $("#rel_authority").val(j[i].authority);
				 $("#rel_date_of_authority").val(ParseDateColumncommission(j[i].date_of_authority));
				 $("#religion").val(j[i].religion);
				
					var text = $("#religion option:selected").text();
					if(text == "OTHERS"){
						$('#religion_other').val(j[0].other);
						$("#other_div").show();
					}
					else{
						$("#other_div").hide();
					}
			}
	  });
}	
//************************************* End RELIGION DETAIL*****************************//
</script>

<script>
//************************************* START CHANGE OF NAME DETAIL*****************************//
function get_change_of_name(){
	  
	 var civ_id=$('#civ_id').val();
	 var i=0;
	  $.post('change_of_name_JCOGetData?' + key + "=" + value, {civ_id:civ_id }, function(j){
			var v=j.length;
			if(v!=0){
				$('#name_id').val(j[i].id);
				 $("#na_authority").val(j[i].authority);
				 $("#na_date_of_authority").val(ParseDateColumncommission(j[i].date_of_authority));
				 $("#first_name").val(j[i].first_name);
				 $("#middle_name").val(j[i].middle_name);
				 $("#last_name").val(j[i].last_name);
			}
	  });
}	

function validate_Change_of_name_save_fn(){
	
	 if ($("input#na_authority").val().trim()=="") {
			alert("Please Enter Authority");
			$("input#na_authority").focus();
			return false;
		}
	 if(lengthValidation($("input#na_authority").val().trim(),auth_length)){
			alert("Authority Length Should be Less or Equal To 100")
			$("#na_authority").focus();
			return false;
		}
	 	 if ($("input#na_date_of_authority").val() == "DD/MM/YYYY" || $("input#na_date_of_authority").val().trim()=="") {
			alert("Please Enter Date of Authority ");
			$("input#na_date_of_authority").focus();
			return false;
		} 
	 	if(getformatedDate($("input#enroll_date").val()) > getformatedDate($("#na_date_of_authority").val())) {
			alert("Authority Date should Be Greater Than Enrollment Date");
			return false;
	  }
	 	if ($("input#first_name").val().trim()=="") {
			alert("Please Enter First Name ");
			$("input#first_name").focus();
			return false;
		}
	 	$("input#middle_name").val($("input#middle_name").val().trim())
	 	$("input#last_name").val($("input#last_name").val().trim())
	var civ_id=$('#civ_id').val();
	
	
	var enroll_date=$('#enroll_date').val();
	var formdata=$('#form_change_of_name').serialize();
	formdata+="&civ_id="+civ_id+"&enroll_date="+enroll_date;	
	   $.post('change_of_name_JcoOr_action?' + key + "=" + value, formdata, function(data){		      
	          if(data=="update")
	        	  alert("Data Updated Successfully");
	          else if(parseInt(data)>0){
	        	
	        	 $('#name_id').val(data);
	        	  alert("Data Saved Successfully")
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

var change_category="";
function change_rankFN(obj){
	var id=obj.value;
$.post("getCategoryFromrankJCO?"+key+"="+value, {id : id}).done(function(j) {
	
	change_category=j[0].toUpperCase();		
	 var preCategory=$("#whetherJCOOR").val().toUpperCase();
	 //260194
	 if(preCategory=="OR"&& change_category=="JCO")
		{
		 $("#army_no1Div").show();
		 $("#army_no1Div_label").show();
		 
		 
		}
	 else
		 {
		 	$("#army_no1Div").hide();
		 	 $("#army_no1Div_label").hide();
		 }
	/* if(change_category=="JCO"){
		$("#army_no1Div").hide();
		$("#army_no1").val("0");
			}
	else{
		$("#army_no1Div").show();
	} */
	
}); 
}

function validate_ChngofRank_save(){
	
	
	
	  if ($("#r_authority").val().trim()=="") {
			alert("Please Enter Authority");
			$("#r_authority").focus();
			return false;
	 }
	  if(lengthValidation($("input#r_authority").val().trim(),auth_length)){
			alert("Authority Length Should be Less or Equal To 100")
			$("#r_authority").focus();
			return false;
		}
	 if ($("#r_date_of_authority").val() == "DD/MM/YYYY" || $("#r_date_of_authority").val().trim()=="") {
			alert("Please Enter Date of Authority");
			$("#r_date_of_authority").focus();
			return false;
	 } 
	 
     if(getformatedDate($("input#enroll_date").val()) > getformatedDate($("#r_date_of_authority").val())) {
			alert("Authority Date should be Greater than Enrollment Date");
			$("#r_date_of_authority").focus();
			return false;
	  }
		
	
	 if ($("#rank").val() == "0") {
			alert("Please Select Rank");
			$("#rank").focus();
			return false;
	 } 
	 if($("#hd_p_rank").val()=='RECTS' && dateOfAttestaion==''){
		 if ($("#date_of_attestation").val() == "DD/MM/YYYY" || $("#date_of_rank").val().trim()=="") {
				alert("Please Enter Date of Attestation");
				$("#date_of_attestation").focus();
				return false;
		 }  
		 if(getformatedDate($("input#enroll_date").val()) > getformatedDate($("#date_of_attestation").val())) {
				alert("Attestation Date should be Greater than Enrollment Date");
				$("#r_date_of_authority").focus();
				return false;
		  }
	 }
	 if ($("#date_of_rank").val() == "DD/MM/YYYY" || $("#date_of_rank").val().trim()=="") {
			alert("Please Enter Date of Rank");
			$("#date_of_rank").focus();
			return false;
	 }  
	 if(getformatedDate($("input#enroll_date").val()) > getformatedDate($("#date_of_rank").val())) {
			alert("Date of Rank should be greater Or equal to Enrollment Date");
			$("#date_of_rank").focus();
			return false;
	  }
	 var preCategory=$("#whetherJCOOR").val().toUpperCase();
	 var army_no1=$("#army_no1").val();
	 var army_no2=$("#army_no2").val().trim();
	 var army_no3=$("#army_no3").val();
	 
	
	 //260194 
	 if(preCategory=="OR"&& change_category=="JCO")
	 {
		 //kajal
		 $("#army_no1Div").show();
		 if(change_category=="OR"){
				if(army_no2!=""){
					 if(army_no2=="" || army_no2.length<1 || army_no2.length>9){
						 alert("Please Enter Valid Army No ");
							$("#army_no2").focus();
							return false;
					 }
					 if(army_no3=="" || army_no3=="0"){
						 alert("Please Enter Valid Army No ");
							$("#army_no2").focus();
							return false;
					 }
					 if(Army_no_already_exist()){
						 $("#army_no2").focus();
							return false;
					 }
					 
				}
			}
			
			if(change_category=="JCO"){
				if(army_no1!="0" || army_no2!=""){
					 if(army_no1=="" || army_no1=="0"){
						 alert("Please Select Army No Prefix");
							$("#army_no1").focus();
							return false;
					 }
					 if(army_no2=="" || army_no2.length<1 || army_no2.length>9){
						 alert("Please Enter Valid Army No ");
							$("#army_no2").focus();
							return false;
					 }
					 if(army_no3=="" || army_no3=="0"){
						 alert("Please Enter Valid Army No ");
							$("#army_no2").focus();
							return false;
					 }
					 if(Army_no_already_exist()){
						 $("#army_no2").focus();
							return false;
					 }
					 
				}
			}
	 }
	 
	 if(preCategory==change_category)
	 {
		
		 $("#army_no1Div").hide();
		 
	 }
	 
	
	
	 
	 if($("#hd_p_rank").val()!='RECTS'){
		 
		 if(preCategory!=change_category){
			 if(preCategory=="OR" && change_category=="JCO"){
				 if(army_no1=="" || army_no1=="0"){
					 alert("Please Select Army No Prefix");
						$("#army_no1").focus();
						return false;
				 }
				 if(army_no2=="" || army_no2.length<1 || army_no2.length>9){
					 alert("Please Enter Valid Army No ");
						$("#army_no2").focus();
						return false;
				 }
				 if(army_no3=="" || army_no3=="0"){
					 alert("Please Enter Valid Army No ");
						$("#army_no2").focus();
						return false;
				 }
				 if(Army_no_already_exist()){
					 $("#army_no2").focus();
						return false;
				 }
				 
			 }
		 }
		 
	 }
	var authority=$('#r_authority').val();
	var date_of_authority=$('#r_date_of_authority').val();
	var date_of_attestation=$('#date_of_attestation').val();
	var prerank=$("#hd_p_rank").val();
	var rank=$('#rank').val();
	var enroll_date=$('#enroll_date').val();
	var date_of_rank=$('#date_of_rank').val();
	var ch_r_id=$('#ch_r_id').val();	
	var civ_id=$('#civ_id').val();
	var army_no_id=$('#army_no_id').val();
	
	   $.post('Change_of_Rank_JCOaction?' + key + "=" + value, {dateOfAttestaion:dateOfAttestaion,army_no_id:army_no_id,preCategory:preCategory,change_category:change_category,army_no1:army_no1,army_no2:army_no2,army_no3:army_no3,prerank:prerank,date_of_attestation:date_of_attestation,authority:authority,date_of_authority:date_of_authority,
		  rank:rank,date_of_rank:date_of_rank,enroll_date:enroll_date,civ_id:civ_id,ch_r_id:ch_r_id}, function(data){
			  
	          if(data.error == null) {
	  			$('#ch_r_id').val(data.rank_id);
	  			$("#army_no_id").val(data.army_no_id);
	  			alert("Data Save/Updated Successfully");
	  		} else {
	  			alert(data.error)
	  		}
	          
	 	  		}).fail(function(xhr, textStatus, errorThrown) {
	 	  			alert("fail to fetch")
	  		});
}

function getChangeOfRank(){
	
	var civ_id=$('#civ_id').val();
	var i=0;
	 $.post('getChangeOfRankJCOData?' + key + "=" + value, {civ_id:civ_id}, function(j){
		 if(j!=""){
			 
			 $('#ch_r_id').val(j[0].id);
				$('#r_authority').val(j[0].authority);
				var dtA =ParseDateColumncommission(j[0].date_of_authority);		
				$('#r_date_of_authority').val(dtA);	
				
				 $('#rank').val( j[0].rank );	
				var dtR =ParseDateColumncommission(j[0].date_of_rank);
				$('#date_of_rank').val(dtR);
				$('#date_of_attestation').val(ParseDateColumncommission(j[0].date_of_attestation));
				change_category=j[0].category;
				var rank_id=j[0].id;
		
				$.post('getChangeOfArmy_NO_JCOData?' + key + "=" + value, {rank_id:rank_id,civ_id:civ_id}, function(k){
					 if(k.length!=0){
						 
						 $('#army_no_id').val(k[0].id);
							
						 var l1=k[0].employee_no.length;
						if(k[0].employee_no != '' && k[0].employee_no != null)
						{
							 $("#army_no1Div").show();
							 $("#army_no1Div_label").show();
						}
						 
						
						 
						 if(change_category=="JCO"){
						var l2=k[0].employee_no.split(/\d/)[0].length;
						
						 $('#army_no1').val(k[0].employee_no.split(/\d/)[0]);
						 $('#army_no2').val(k[0].employee_no.slice(l2, l1-1));
						 $('#army_no3').val(k[0].employee_no.substr(-1));
						 }
						 else{
								$("#army_no1Div").hide();
								$("#army_no1").val("0");
							 $('#army_no2').val(k[0].employee_no.slice(0, l1-1));
							 $('#army_no3').val(k[0].employee_no.substr(-1));
						 }
					 }
				 });
				
		 }
	 });
	}





//*************************************END Change of RANK*****************************//
</script>

<script>
//*************************************START AWARD AND MEDAL *****************//



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
   +'<td style="width: 10%;">'
   +'<input type="text" id="awardsNmedal_authority'+anm+'" name="awardsNmedal_authority'+anm+'" onkeyup="return specialcharecter(this)" class="form-control autocomplete" autocomplete="off" maxlength="50" ></td>'
  /*  +'<td style="width: 10%;"><input type="date" id="awardsNmedal_date_of_authority'+anm+'" name="awardsNmedal_date_of_authority'+anm+'" class="form-control autocomplete" autocomplete="off" maxlength="10" min="1899-01-01" max="${date}">'
   +'</td>' */
	+'<td style="width: 10%;">' 
	+' <input type="text" name="awardsNmedal_date_of_authority'+anm+'" id="awardsNmedal_date_of_authority'+anm+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')"   class="form-control" style="width: 85%;display: inline;" '
	+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
	+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);">'
	+ '</td>'
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
          $.post('awardsNmedal_delete_action_jco?' + key + "=" + value, {awardsNmedal_ch_id:awardsNmedal_ch_id }, function(data){ 
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
                                                         alert("Data Deleted Successfully");
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
    
    var civ_id=$('#civ_id').val();
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
    
    if(getformatedDate($("input#enroll_date").val()) > getformatedDate(anm_doa)) {
		alert("Authority Date should be Greater than Enrollment Date");
		$("#awardsNmedal_date_of_authority"+qs).focus();
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
    if( getformatedDate($("input#dob_date").val())  > getformatedDate(date_of_award)) {
		alert("Date of Award should be Greater than Date of Birth");
		$("#date_of_award"+qs).focus();
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
    
    if (awardsNmedal_unit.trim()=="") {
            alert("Please Enter Unit");
            $("#awardsNmedal_unit"+qs).focus();
            return false;
    }

 
    
    $.post('awardsNmedal_action_jco?' + key + "=" + value, {Category_8:Category_8,select_desc:select_desc,date_of_award:date_of_award,awardsNmedal_unit:awardsNmedal_unit,awardsNmedal_bde:awardsNmedal_bde,awardsNmedal_div_subarea:awardsNmedal_div_subarea,awardsNmedal_corps_area:awardsNmedal_corps_area,awardsNmedal_command:awardsNmedal_command,awardsNmedal_ch_id:awardsNmedal_ch_id,civ_id:civ_id,anm_authority:anm_authority,anm_doa:anm_doa }, function(data){           
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
function getawardsNmedals(){
     
    var civ_id=$('#civ_id').val();
    var i=0;
     $.post('getawardsNmedalData_jco?' + key + "=" + value, {civ_id:civ_id}, function(j){
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
                                   +'<input type="text" id="awardsNmedal_authority'+am+'" name="awardsNmedal_authority'+am+'" onkeyup="return specialcharecter(this)" value="'+j[i].authority+'"  class="form-control autocomplete" autocomplete="off" maxlength="50"></td>'
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
		+'<input type="text" id="other_language'+lang+'" maxlength="100" name="other_language'+lang+'" class="form-control autocomplete" autocomplete="off"  onkeypress="return onlyAlphaNumeric(event);" ></td>'
	+'<td style="width: 10%;"> '
	+'<select name="lang_std'+lang+'" id="lang_std'+lang+'" onchange="onLanguage_std('+lang+')" class="form-control" >'
	+' <option value="0">--Select--</option>'
	+'	<c:forEach var="item" items="${getLanguageSTDList}" varStatus="num">'
	+'	<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' 
	+'	</c:forEach>'
	+'</select>'
	+'</td> '
	+'<td style="width: 10%;">'
	+'<input type="text" id="other_lang_std'+lang+'" name="other_lang_std'+lang+'" maxlength="100" class="form-control autocomplete" onkeypress="return onlyAlphaNumeric(event);" autocomplete="off"  ></td>'
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
	  $.post('update_offr_language_delete_action_jco?' + key + "=" + value, {lang_ch_id:lang_ch_id }, function(data){ 
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
							 alert("Data Deleted Successfully");
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
  
  var civ_id=$('#civ_id').val();
  var lang_authority=$('#language_authority').val();
  var lang_doa=$('#language_date_of_authority').val();
  var enroll_date=$('#enroll_date').val();
  
  if (lang_authority.trim()=="" || lang_authority == "DD/MM/YYYY") {
          alert("Please Enter Authority");
          $('#language_authority').focus();
          return false;
  }
  if(lengthValidation($("input#language_authority").val().trim(),auth_length)){
		alert("Authority Length Should be Less or Equal To 100")
		$("#language_authority").focus();
		return false;
	}
  if (lang_doa.trim()=="" || lang_doa == "DD/MM/YYYY") {
          alert("Please Enter Date of Authority");
          $("input#language_date_of_authority").focus();
          return false;
  } 
   if(getformatedDate($("input#enroll_date").val()) > getformatedDate($("#language_date_of_authority").val())) {
                  alert("Authority Date should be Greater than Enrollment Date");
                  return false;
    }
  if (language == "0") {
          alert("Please select Language");
          $("select#language"+ps).focus();
          return false;
  }        
   var country_sel = $("#language"+ps+" option:selected").text();
          if (country_sel == "OTHERS" && other_lan.trim()=="") {
                  alert("Please Enter Other Language");
                  $('#other_language'+ps).focus();
                  return false;
          } 
          
          if(country_sel == "OTHERS" ){
  			if(lengthValidation($("input#other_language"+ps).val().trim(),auth_length)){
  				alert(" Other Language Length Should be Less or Equal To 100")
  				$("#other_language"+ps).focus();
  				return false;
  			}
  		}
          
  if (lang_std == "0") {
          alert("Please select Language Standard");
          $("select#lang_std"+ps).focus();
          return false;
  }
   var country_sel1 = $("#lang_std"+ps+" option:selected").text();
          if (country_sel1 == "OTHERS" && other_lan_std.trim()=="") {
                  alert("Please Enter Other Language Standard");
                  $('#other_lang_std'+ps).focus();
                  return false;
          } 
          if(country_sel1 == "OTHERS" ){
    			if(lengthValidation($("input#other_lang_std"+ps).val().trim(),auth_length)){
    				alert(" Other  Language Standard Length Should be Less or Equal To 100")
    				$("#other_lang_std"+ps).focus();
    				return false;
    			}
    		}
    $.post('update_offr_language_detail_action_jco?' + key + "=" + value, {language:language,other_lan:other_lan,lang_std:lang_std,other_lan_std:other_lan_std,lang_ch_id:lang_ch_id,civ_id:civ_id,lang_authority:lang_authority,lang_doa:lang_doa,enroll_date:enroll_date }, function(data){
            if(data=="update")
                    alert("Data Updated Successfully");
            else if(parseInt(data)>0){
                   $('#lang_ch_id'+ps).val(data);
                   $("#language_add"+ps).show();
                   $("#language_remove"+ps).show();
                    alert("Data Saved Successfully")
            }
            else
                    alert(data)
                             }).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
                    });
}

function get_language_details(){
	  
	 var civ_id=$('#civ_id').val(); 
	 var i=0;
	  $.post('update_offr_getlanguagedetailsData_jco?' + key + "=" + value, {civ_id:civ_id}, function(j){
			var v=j.length;
			if(v!=0){
				
		xl=0;
		xf=0;
		for(i;i<v;i++){
			if(j[i].language!="0"){
				xl=xl+1;
				if(xl==1){
					$('#langtbody').empty(); 
				}
				var dauth=ParseDateColumncommission(j[i].date_of_authority);
				$("table#language_table").append('<tr id="tr_lang'+xl+'">'
				+'<td class="lang_srno">'+xl+'</td>'
				+'<td style="width: 10%;"> '
				+'<select  id="language'+xl+'" name="language'+xl+'" value="'+j[i].language+'"  onchange="onLanguage('+xl+')" class="form-control autocomplete"  >'
				  +' <option value="0">--Select--</option>'
				   +'<c:forEach var="item" items="${getIndianLanguageList}" varStatus="num">'
					+'<option value="${item[0]}" name="${item[1]}">${item[1]}</option>'
					+'</c:forEach>'
					+'</select>'
					+'</td>'
					+'<td style="width: 10%;"> <input type="text" id="other_language'+xl+'" maxlength="100" name="other_language'+xl+'" value="'+j[i].other_language+'" class="form-control autocomplete" onkeypress="return onlyAlphaNumeric(event);" autocomplete="off" ></td>'		
				+'<td style="width: 10%;"> '
				+'<select name="lang_std'+xl+'" id="lang_std'+xl+'"  onchange="onLanguage_std('+xl+')" class="form-control" >'
				+' <option value="0">--Select--</option>'
				+'	<c:forEach var="item" items="${getLanguageSTDList}" varStatus="num">'
				+'	<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' 
				+'	</c:forEach>'
				+'</select> </td> '
				+'<td style="width: 10%;"> <input type="text" id="other_lang_std'+xl+'" maxlength="100" name="other_lang_std'+xl+'" value="'+j[i].other_lang_std+'" class="form-control autocomplete" onkeypress="return onlyAlphaNumeric(event);" autocomplete="off" ></td>'		
			/* 	+'<td style="width: 10%;">'
				+'<input type="text" id="language_authority'+xl+'" name="language_authority'+xl+'" value="'+j[i].authority+'" class="form-control autocomplete" autocomplete="off" maxlength="50"></td>'
				+'<td style="width: 10%;"><input type="date" id="language_date_of_authority'+xl+'"  min="1899-01-01" max="${date}" value="'+dauth+'" name="language_date_of_authority'+xl+'" class="form-control autocomplete" autocomplete="off" maxlength="10">'
				+'</td>' */
				+'<td style="display:none;"><input type="text" id="lang_ch_id'+xl+'" name="lang_ch_id'+xl+'"  value="'+j[i].id+'" class="form-control autocomplete" autocomplete="off" ></td>'
				+'<td style="width: 10%;"><a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "language_save'+xl+'" onclick="language_save_fn('+xl+');" ><i class="fa fa-save"></i></a>'
				+'<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "language_add'+xl+'" onclick="language_add_fn('+xl+');" ><i class="fa fa-plus"></i></a>'
				+'<a  class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "language_remove'+xl+'" onclick="language_remove_fn('+xl+');"><i class="fa fa-trash"></i></a>' 
				+'</td></tr>');
				 $('#lang_std'+xl).val(j[i].lang_std );
				 $('#language'+xl).val(j[i].language );
				 onLanguage(xl);
				 onLanguage_std(xl);
				 $('#language_authority').val(j[i].authority );
				 $('#language_date_of_authority').val(dauth );
			}
			else{
				xf=xf+1;
				if(xf==1){
					$('#flangtbody').empty(); 
				}
				$("table#foregin_language_table").append('<tr id="tr_flang'+xf+'">'
						+'<td class="flang_srno">'+xf+'</td>'
						+'<td style="width: 10%;"> <select  id="flanguage'+xf+'" name="flanguage'+xf+'" value="'+j[i].foreign_language+'" onchange="onF_Language('+xf+')" class="form-control autocomplete"  >'
						+'   <option value="0">--Select--</option>'
					    +'	<c:forEach var="item" items="${getForiegnLangugeList}" varStatus="num">'
						+'	<option value="${item[0]}" name="${item[1]}">${item[1]}</option>'
						+'	</c:forEach>'
						+'	</select></td>'
						+'<td style="width: 10%;"> <input type="text" id="f_other_language'+xf+'" maxlength="100" name="f_other_language'+xf+'" value="'+j[i].f_other_language+'" class="form-control autocomplete" onkeypress="return onlyAlphaNumeric(event);" autocomplete="off" ></td>'		
						+'<td style="width: 10%;"> '
						+'<select name="flang_std'+xf+'" id="flang_std'+xf+'"  onchange="onF_Language_std('+xf+')"  class="form-control" >'
						+' <option value="0">--Select--</option>'
						+'	<c:forEach var="item" items="${getLanguageSTDList}" varStatus="num">'
						+'	<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' 
						+'	</c:forEach>'
						+'</select> </td> '
						+'<td style="width: 10%;"> <input type="text" id="f_other_lang_std'+xf+'" maxlength="100" name="f_other_lang_std'+xf+'" value="'+j[i].f_other_lang_std+'" class="form-control autocomplete" onkeypress="return onlyAlphaNumeric(event);" autocomplete="off" ></td>'		
						+'<td style="width: 10%;"> <select name="lang_prof'+xf+'" id="lang_prof'+xf+'" onchange="onF_Language_prof('+xf+')"  class="form-control"   >'
						+' <option value="0">--Select--</option>'
						+'	<c:forEach var="item" items="${getLanguagePROOFList}" varStatus="num">'
						+'	<option value="${item[0]}" name="${item[1]}">${item[1]}</option>'
						+'	</c:forEach>'
						+' </select></td> '
						+'<td style="width: 10%;"> <input type="text" id="f_other_prof'+xf+'" maxlength="100"  name="f_other_prof'+xf+'" value="'+j[i].f_other_prof+'" class="form-control autocomplete" onkeypress="return onlyAlphaNumeric(event);" autocomplete="off" ></td>'			
						+'<td style="width: 10%;"> <input type="text" id="exam_pass'+xf+'" name="exam_pass'+xf+'" value="'+j[i].f_exam_pass+'" class="form-control autocomplete" autocomplete="off" maxlength="20"></td>'		
						+'<td style="display:none;"><input type="text" id="flang_ch_id'+xf+'" name="flang_ch_id'+xf+'"  value="'+j[i].id+'" class="form-control autocomplete" autocomplete="off" ></td>'
						+'<td style="width: 10%;"><a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "foregin_language_save'+xf+'" onclick="foregin_language_save_fn('+xf+');" ><i class="fa fa-save"></i></a>'
						+'<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "foregin_language_add'+xf+'" onclick="foregin_language_add_fn('+xf+');" ><i class="fa fa-plus"></i></a>'
						+'<a  class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "foregin_language_remove'+xf+'" onclick="foregin_language_remove_fn('+xf+');"><i class="fa fa-trash"></i></a>' 
						+'</td></tr>');
				
				$('#flang_std'+xf).val(j[i].lang_std );
				 $('#lang_prof'+xf).val(j[i].f_lang_prof );    
				 $('#flanguage'+xf).val(j[i].foreign_language );
				 onF_Language(xf);
				 onF_Language_std(xf);
				 onF_Language_prof(xf);
			}
		}
		if(xl!=0){
			lang=xl;
			lang_srno=xl;
			$("#language_add"+xl).show();
		}
		if(xf!=0)
			flang=xf;
			flang_srno=xf;
		$("#foregin_language_add"+xf).show();
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
	+'<input type="text" id="f_other_language'+flang+'" name="f_other_language'+flang+'" maxlength="100" class="form-control autocomplete" onkeypress="return onlyAlphaNumeric(event);"  autocomplete="off"  ></td>'
	+'<td style="width: 10%;"> '
	+'<select name="flang_std'+flang+'" id="flang_std'+flang+'" onchange="onF_Language_std('+flang+')" class="form-control" >'
	+' <option value="0">--Select--</option>'
	+'	<c:forEach var="item" items="${getLanguageSTDList}" varStatus="num">'
	+'	<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' 
	+'	</c:forEach>'
	+'</select> </td> '
	
	+'<td style="width: 10%;">'
	+'<input type="text" id="f_other_lang_std'+flang+'" name="f_other_lang_std'+flang+'" maxlength="100" class="form-control autocomplete" onkeypress="return onlyAlphaNumeric(event);"  autocomplete="off"  ></td>'

	+'<td style="width: 10%;"> <select name="lang_prof'+flang+'" id="lang_prof'+flang+'" onchange="onF_Language_prof('+flang+')" class="form-control"   >'	
	+' <option value="0">--Select--</option>'
	+'	<c:forEach var="item" items="${getLanguagePROOFList}" varStatus="num">'
	+'	<option value="${item[0]}" name="${item[1]}">${item[1]}</option>'
	+'	</c:forEach>'
	+' </select></td> '
	+'<td style="width: 10%;">'
	+'<input type="text" id="f_other_prof'+flang+'" name="f_other_prof'+flang+'" maxlength="100"  class="form-control autocomplete" onkeypress="return onlyAlphaNumeric(event);"  autocomplete="off"  ></td>'
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
	  $.post('update_offr_language_delete_action_jco?' + key + "=" + value, {lang_ch_id:lang_ch_id }, function(data){ 
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
							 alert("Data Deleted Successfully");
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
	
	var civ_id=$('#civ_id').val();
	if (lang_authority.trim()=="" || lang_authority == "DD/MM/YYYY") {
		alert("Please Enter Authority");
		$('#language_authority').focus();
		return false;
	}
	 if(lengthValidation($("input#language_authority").val().trim(),auth_length)){
			alert("Authority Length Should be Less or Equal To 100")
			$("#language_authority").focus();
			return false;
		}
	if (lang_doa.trim()=="" || lang_doa == "DD/MM/YYYY") {
		alert("Please Enter Date of Authority");
		$("input#language_date_of_authority").focus();
		return false;
	} 
	 if (language == "0") {
			alert("Please select Language");
			$("select#flanguage"+ps).focus();
			return false;
		}
		 var country_sel = $("#flanguage"+ps+" option:selected").text();
		if (country_sel == "OTHERS" && f_ot_language.trim()=="") {
			alert("Please Enter Other Language");
			$('#f_other_language'+ps).focus();
			return false;
		} 
		
		  if(country_sel == "OTHERS" ){
				if(lengthValidation($("input#f_other_language"+ps).val().trim(),auth_length)){
					alert(" Other Language Length Should be Less or Equal To 100")
					$("#f_other_language"+ps).focus();
					return false;
				}
			}
		  
	 if (lang_std == "0") {
			alert("Please select Language standard");
			$("select#flang_std"+ps).focus();
			return false;
		}	
	 var country_sel1 = $("#flang_std"+ps+" option:selected").text();
		if (country_sel1 == "OTHERS" && f_ot_std.trim()=="") {
			alert("Please Enter Other Language Standard");
			$('#f_other_lang_std'+ps).focus();
			return false;
		} 
		
		  if(country_sel1 == "OTHERS" ){
				if(lengthValidation($("input#f_other_lang_std"+ps).val().trim(),auth_length)){
					alert(" Other Language Standard Length Should be Less or Equal To 100")
					$("#f_other_lang_std"+ps).focus();
					return false;
				}
			}
		  
		  
	 if (lang_prof == "0") {
			alert("Please select Language Proficiency");
			$("select#lang_prof"+ps).focus();
			return false;
		}
	 var country_sel2 = $("#lang_prof"+ps+" option:selected").text();
		if (country_sel2 == "OTHERS" && f_ot_prof.trim()=="") {
			alert("Please Enter Other Language Proficiency");
			$('#f_other_prof'+ps).focus();
			return false;
		} 
		
		  if(country_sel2 == "OTHERS" ){
				if(lengthValidation($("input#f_other_prof"+ps).val().trim(),auth_length)){
					alert(" Other  Language Proficiency Length Should be Less or Equal To 100")
					$("#f_other_prof"+ps).focus();
					return false;
				}
			}
		  
	 if (exam_pass.trim()=="") {
			alert("Please Enter The Exam pass");
			$("input#exam_pass"+ps).focus();
			return false;
		}
	  $.post('update_offr_foreginlanguage_detail_action_jco?' + key + "=" + value, {language:language,lang_prof:lang_prof,f_ot_language:f_ot_language,f_ot_std:f_ot_std,f_ot_prof:f_ot_prof,exam_pass:exam_pass,lang_authority:lang_authority,lang_doa:lang_doa,lang_std:lang_std,lang_ch_id:lang_ch_id,civ_id:civ_id}, function(data){
	          if(data=="update")
	        	  alert("Data Updated Successfully");
	          else if(parseInt(data)>0){
	        	 $('#flang_ch_id'+ps).val(data);
	        	 $("#foregin_language_add"+ps).show();
	        	 $("#foregin_language_remove"+ps).show();
	        	  alert("Data Saved Successfully")
	          }
	          else
	        	  alert(data)
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});
}
//*************************************END LANGUAGE DETAILS*********************//
</Script>



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
 	 var civ_id=$('#civ_id').val(); 
 	 var i=0;
 	  $.post('getUPForeginCountryData_jco?' + key + "=" + value, {civ_id:civ_id }, function(j){
 			var v=j.length;
 			if(v!=0){
 				$('#foregin_Country_tbody').empty(); 
 		for(i;i<v;i++){
 			f=i+1;
 			  var fd=ParseDateColumncommission(j[i].from_dt);				
 			  var td=ParseDateColumncommission(j[i].to_dt);
 		  $("table#foreign_country_visit").append('<tr id="tr_foregin_country'+f+'">'
 					+'<td class="fcon_srno">'+f+'</td>'
 					+'<td style="width: 10%;"><select name="country'+f+'" id="country'+f+'" onchange="onContryVisited('+f+')" class="form-control"><option value="0">--Select--</option><c:forEach var="item" items="${getForeign_country}" varStatus="num"><option value="${item[0]}" name="${item[1]}">${item[1]}</option></c:forEach></select></td>'
 					+'<td style="width: 10%;"> <input type="text" id="foregin_Country_ot'+f+'" name="foregin_Country_ot'+f+'" onkeypress="return onlyAlphaNumeric(event);" value="' + j[i].other_country + '"  class="form-control autocomplete" autocomplete="off" ></td>'
 					+'<td style="width: 10%;">' 
 					+' <input type="text" name="from_dt'+f+'" id="from_dt'+f+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')"   class="form-control" style="width: 85%;display: inline;" '
 					+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
 					+'	onchange="clickrecall(this,\'DD/MM/YYYY\');calcDate22('+fcon+');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="'+fd+'">'
 					+ '</td>'	
 					/* +'<td style="width: 10%;"> <input type="date" id="to_dt'+f+'" name="to_dt'+f+'" min="1899-01-01" max="${date}" value="' + td+ '" onchange="calcDate22('+f+')" class="form-control autocomplete" autocomplete="off" maxlength="10"></td>' */	
 					+'<td style="width: 10%;">' 
 					+' <input type="text" name="to_dt'+f+'" id="to_dt'+f+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')"   class="form-control" style="width: 85%;display: inline;" '
 					+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
 					+'	onchange="clickrecall(this,\'DD/MM/YYYY\');calcDate22('+fcon+');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="'+td+'">'
 					+ '</td>'
 					+'<td style="width: 10%;"> <input type="text" id="period'+f+'" name="period'+f+'" value="' + j[i].period + '" readonly="readonly" class="form-control autocomplete" autocomplete="off" maxlength="4" onkeypress="isNumber0_9Only(event)"></td>'
 					+'<td style="width: 10%;"><select name="purpose_visit'+f+'" onchange="onPurposeVisited('+f+')" id="purpose_visit'+f+'" class="form-control"><option value="0">--Select--</option><c:forEach var="item" items="${getForiegnCountryList}" varStatus="num"><option value="${item[0]}" name="${item[1]}">${item[1]}</option></c:forEach></select></td>'
 					+'<td style="width: 10%;"> <input type="text" id="purpose_visit_ot'+f+'" name="purpose_visit_ot'+f+'" onkeypress="return onlyAlphaNumeric(event);" value="' + j[i].other_purpose_visit + '"  class="form-control autocomplete" autocomplete="off" ></td>'
 					+'<td style="display:none;"><input type="text" id="foregin_country_ch_id'+f+'" name="foregin_country_ch_id'+f+'"  value="' + j[i].id + '" value="0" class="form-control autocomplete" autocomplete="off" ></td>'
 					+'<td style="width: 10%;"><a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "country_save'+f+'" onclick="UPforeign_country_save_fn('+f+');" ><i class="fa fa-save"></i></a>'
 					+'<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "country_add'+f+'" onclick="UPforeign_country_add_fn('+f+');" ><i class="fa fa-plus"></i></a>'
 					+'<a  class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "country_remove'+f+'" onclick="UPforeign_country_remove_fn('+f+');"><i class="fa fa-trash"></i></a>' 
 					+'</td></tr>');
 		  $("#country"+f).val(j[i].country)
 		  onContryVisited(f);
 		  $("#purpose_visit"+f).val(j[i].purpose_visit)
 		  onPurposeVisited(f);
 		  datepicketDate('from_dt'+f);
 		  datepicketDate('to_dt'+f);
 		}
 		fcon=v;
 		fcon_srno=v;
 		$("#country_add"+fcon).show();
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
   	+'<td style="width: 10%;"> <input type="text" id="foregin_Country_ot'+fcon+'" name="foregin_Country_ot'+fcon+'" onkeypress="return onlyAlphaNumeric(event);" readonly  class="form-control autocomplete" autocomplete="off" ></td>'
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
   	+'<td style="width: 10%;"> <input type="text" id="period'+fcon+'" name="period'+fcon+'" class="form-control autocomplete" readonly="readonly" autocomplete="off" maxlength="4" onkeypress="isNumber0_9Only(event)"></td>'
   	+'<td style="width: 10%;"><select name="purpose_visit'+fcon+'" onchange="onPurposeVisited('+fcon+')" id="purpose_visit'+fcon+'" class="form-control"><option value="0">--Select--</option><c:forEach var="item" items="${getForiegnCountryList}" varStatus="num"><option value="${item[0]}" name="${item[1]}">${item[1]}</option></c:forEach></select></td>'
 	+'<td style="width: 10%;"> <input type="text" id="purpose_visit_ot'+fcon+'" onchange="onPurposeVisited('+fcon+')" name="purpose_visit_ot'+fcon+'" onkeypress="return onlyAlphaNumeric(event);" readonly  class="form-control autocomplete" autocomplete="off" ></td>'
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
 		 
 		
 			var country=$('#country'+ps).val();
 			var other_ct=$('#foregin_Country_ot'+ps).val();
 			var period=$('#period'+ps).val();
 			var from_dt=$('#from_dt'+ps).val();
 			var to_dt=$('#to_dt'+ps).val();
 			var purpose_visit=$('#purpose_visit'+ps).val();
 			var other_purpose_visit=$('#purpose_visit_ot'+ps).val();
 			var foregin_country_ch_id=$('#foregin_country_ch_id'+ps).val();
 			var civ_id=$('#civ_id').val();
 			if(country == "0") {
 				alert("Please Select Country Visited");
 				$("select#country" + ps).focus();
 				return false;
 			}
 			
 			var country_sel = $("#country"+ps+" option:selected").text().toUpperCase();
 			if (country_sel == "Other" && other_ct.trim()=="") {
 				alert("Please Enter Other Country");
 				$('#foregin_Country_ot'+ps).focus();
 				return false;
 			}
 			
 			if(country_sel == "OTHERS" || country_sel=="OTHER"){
 				if(lengthValidation($("input#foregin_Country_ot"+ps).val().trim(),auth_length)){
 					alert("Country Other Length Should be Less or Equal To 100")
 					$("#foregin_Country_ot"+ps).focus();
 					return false;
 				}
 			}
 			
 			if(from_dt.trim()=="" || from_dt == "DD/MM/YYYY") {
 				alert("Please Enter From Date");
 				$("input#from_dt" + ps).focus();
 				return false;
 			}
 			if(getformatedDate($("input#dob_date").val()) >= getformatedDate(from_dt)) {
 				alert("From Date should be Greater than Date of Birth");
 				$("input#from_dt" + ps).focus();
 				return false;
 			}
 			if(to_dt.trim()=="" || to_dt == "DD/MM/YYYY") {
 				alert("Please Enter To Date");
 				$("input#to_dt" + ps).focus();
 				return false;
 			}
 			var currentdate = new Date();
 			if(getformatedDate(to_dt) > currentdate) {
 				alert("Enter Valid To Date");
 				$("input#to_dt" + ps).focus();
 				return false;
 			}
 			if(getformatedDate(from_dt) > getformatedDate(to_dt)) {
 				alert("Invalid Date Range");
 				$("input#to_dt" + ps).focus();
 				return false;
 			}
 			if(period.trim()=="") {
 				alert("Please Enter Valid From And To Date");
 				$("input#period" + ps).focus();
 				return false;
 			}
 			if(purpose_visit.trim()=="0") {
 				alert("Please Select Purpose To Visit");
 				$("#purpose_visit" + ps).focus();
 				return false;
 			}
 			
 			var pup_sel = $("#purpose_visit"+ps+" option:selected").text().toUpperCase();
 			if (pup_sel == "OTHER" && other_purpose_visit.trim()=="") {
 				alert("Please Enter Other Purpose Visit");
 				$('#purpose_visit_ot'+ps).focus();
 				return false;
 			}
 			
 			if(pup_sel == "OTHERS" ||  pup_sel == "OTHER"){
 				if(lengthValidation($("input#purpose_visit_ot"+ps).val().trim(),auth_length)){
 					alert("Purpose Visit Other Length Should be Less or Equal To 100")
 					$("#purpose_visit_ot"+ps).focus();
 					return false;
 				}
 			}
 			
 					   $.post('UPforegin_country_action_jco?' + key + "=" + value, {country:country,other_ct:other_ct,period:period,from_dt:from_dt,to_dt:to_dt,purpose_visit:purpose_visit,other_purpose_visit:other_purpose_visit,foregin_country_ch_id:foregin_country_ch_id,civ_id:civ_id}, function(data){	 
 			          if(data=="update")
 			        	  alert("Data Updated Successfully");
 			          else if(parseInt(data)>0){
 			        	 $("#foregin_country_ch_id"+ps).val(data);
 			        	 $("#country_add"+ps).show();
 			        	 $("#country_remove"+ps).show();
 			        	  alert("Data Saved Successfully")
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
   	  $.post('UPforegin_country_delete_action_jco?' + key + "=" + value, {foregin_country_ch_id:foregin_country_ch_id }, function(data){ 
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
   							 alert("Data Deleted Successfully");
   			   }
   				 else{
   					 alert("Data not Deleted ");
   				 }
   	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
   	  		});
   	 }
   	 }
   function calcDate22(f) {
 		if($('#from_dt' + f).val().trim() != "" && $('#from_dt' + f).val() != "DD/MM/YYYY" && $('#to_dt' + f).val().trim() != "" && $('#to_dt' + f).val() != "DD/MM/YYYY") {
 			if(getformatedDate($('#from_dt' + f).val()) > getformatedDate($('#to_dt' + f).val())) {
 				alert("Invalid Date Range");
 				$('#period' + f).val('');
 				$("input#to_dt" + f).focus();
 				return false;
 			}
 			var startDate = getformatedDate($('#from_dt' + f).val());
 			var endDate = getformatedDate($('#to_dt' + f).val());
 			
 			
 			 var startYear = startDate.getFullYear();
 			    var february = (startYear % 4 === 0 && startYear % 100 !== 0) || startYear % 400 === 0 ? 29 : 28;
 			    var daysInMonth = [31, february, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];

 			    var yearDiff = endDate.getFullYear() - startYear;
 			    var monthDiff = endDate.getMonth() - startDate.getMonth();
 			    if (monthDiff < 0) {
 			        yearDiff--;
 			        monthDiff += 12;
 			    }
 			    var dayDiff = endDate.getDate() - startDate.getDate();
 			    if (dayDiff < 0) {
 			        if (monthDiff > 0) {
 			            monthDiff--;
 			        } else {
 			            yearDiff--;
 			            monthDiff = 11;
 			        }
 			        dayDiff += daysInMonth[startDate.getMonth()];
 			    }
 		    
 			var message;
 			   if ( (yearDiff == 0) && (monthDiff == 0) && (dayDiff == 0) )  {
 				message = "0 years 0 months 1 days";
 			} else {
 				message = yearDiff + " years "
 				message += monthDiff + " months "
 				message += dayDiff + " days"
 			}
 			$('#period' + f).val(message);
 		}
 	}
   //*****************END FOREIGN COUNTRY DETAILS************************//
  </script>
  <script>
//*****************START INTER ARM  DETAILS************************//

//  function chgarm(){
// 	 /*  if($("#inter_arm_parent_arm_service").val() == "0700" || $("#inter_arm_parent_arm_service").val() == "0800"){ */
// 		  if($("#inter_arm_parent_arm_service").val() == "0800"){
			
// 			$("#inter_arm_regt").attr('disabled',false);
// 		}
// 	  else
// 		  {
// 		  $("#inter_arm_regt").attr('disabled',true);
// 		  $("#inter_arm_regt").val("0");
// 		  }
	  
//   }
  
function validate_inter_arm_transfer_save(){

	if ($("input#inter_arm_authority").val().trim()=="") {
	    alert("Please Enter Authority");
		$("input#inter_arm_authority").focus();
		return false;
	} 
	 if(lengthValidation($("input#inter_arm_authority").val().trim(),auth_length)){
			alert("Authority Length Should be Less or Equal To 100")
			$("#inter_arm_authority").focus();
			return false;
		}
		 if ($("input#inter_arm_date_of_authority").val().trim()=="" || $("input#inter_arm_date_of_authority").val() == "DD/MM/YYYY") {
		 alert("Please Enter Date of Authority");
			$("input#inter_arm_date_of_authority").focus();
			return false;
		} 
			if(getformatedDate($("input#enroll_date").val()) > getformatedDate($("#inter_arm_date_of_authority").val())) {
				alert("Authority Date should be Greater than Enrollment Date");
				$("input#inter_arm_date_of_authority").focus();
				return false;
		  }

	 	 if ($("select#inter_arm_parent_arm_service").val() == "0") {
		 alert("Please Select To Present Arm/Services");
			$("select#inter_arm_parent_arm_service").focus();
			return false;
		} 
	 	 

   var inter_arm_regt=$('#inter_arm_regt').val();
 	var inter_arm_parent_arm_serviceV = $("#inter_arm_parent_arm_service"+" option:selected").text();
 	
 	if(($("#inter_arm_parent_arm_service").val() == "0700" || $("#inter_arm_parent_arm_service").val() == "0800") && inter_arm_regt == "0") {
		alert("Please Select Inter Arm Regt");
		$('#inter_arm_regt').focus();
		return false;
	}
 	

	if ($("input#record_office_sus").val().trim()=="") {
	    alert("Please Enter Record Office SUS");
		$("input#record_office_sus").focus();
		return false;
	} 
	

	if ($("input#record_office_unit").val().trim()=="") {
	    alert("Please Enter Record Office Unit");
		$("input#record_office_unit").focus();
		return false;
	} 
 	
 	if ($("input#inter_arm_with_effect_from").val().trim()=="" || $("input#inter_arm_with_effect_from").val() == "DD/MM/YYYY") {
		 alert("Please Enter With Effect From");
			$("input#inter_arm_with_effect_from").focus();
			return false;
		} 
 	 if(getformatedDate($("input#enroll_date").val()) > getformatedDate($("#inter_arm_with_effect_from").val())) {
			alert("With Effect From Date should be Greater than Enrollment Date");
			$("#inter_arm_with_effect_from").focus();
			return false;
	  }
 
 	
 	var authority1=$('#inter_arm_authority').val();
 	var date_of_authority1=$('#inter_arm_date_of_authority').val();
 	var record_office_sus=$('#record_office_sus').val();
 	var record_office_unit=$('#record_office_unit').val();
 	var parent_arm_service1=$('#inter_arm_parent_arm_service').val();
 	var with_effect_from1=$('#inter_arm_with_effect_from').val();
 	var regt1=$('#inter_arm_regt').val();
 	var pre_ch_id=$('#p_id').val();
 	
 	var civ_id=$('#civ_id').val();
 
	   $.post('Inter_arm_JCOaction?' + key + "=" + value, {authority1:authority1, date_of_authority1:date_of_authority1,
		   record_office_unit:record_office_unit,record_office_sus:record_office_sus,parent_arm_service1:parent_arm_service1,
		   with_effect_from1:with_effect_from1,regt1:regt1,pre_ch_id:pre_ch_id,civ_id:civ_id}, function(data){
		      
	          if(data=="update")
	        	  alert("Data Updated Successfully");
	          else if(parseInt(data)>0){
				$('#p_id').val(data);
	        	  alert("Data Saved Successfully")
	          }
	          else
	        	  alert(data);
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch");
 	  		});
}

function getInterArm(){
	  
	 var civ_id=$('#civ_id').val(); 
	 var i=0;
	  $.post('getInterArmJCO?' + key + "=" + value, {civ_id:civ_id }, function(j){
			var v=j.length;
			if(v!=0){
				$('#pre_cadeytbody').empty(); 
		for(i;i<v;i++){
			x=i+1;
 			var fd=ParseDateColumncommission(j[i].date_of_authority);				
			var td=ParseDateColumncommission(j[i].with_effect_from);
			$('#inter_arm_parent_arm_service').val(j[i].parent_arm_service);
		
			
			chgarm(document.getElementById("inter_arm_parent_arm_service"),'inter_arm_regt');
			$('#inter_arm_authority').val(j[i].authority);
			$('#inter_arm_date_of_authority').val(fd);
			$('#record_office_unit').val(j[i].record_office_unit);
			$('#record_office_sus').val(j[i].record_office_sus);
			$('#inter_arm_with_effect_from').val(td);
 			$('#inter_arm_regt').val(j[i].regt);
			$('#p_id').val(j[i].id);
		}
	}
});
}


$("#record_office_sus")
.keyup(
		function() {
			var sus_no = this.value;
			var susNoAuto = $("#record_office_sus");
      
			susNoAuto
					.autocomplete({
						source : function(request, response) {
							$
									.ajax({
										type : 'POST',
										url : "getRecordofficeSUSNoList?"
												+ key + "=" + value,
										data : {
											sus_no : sus_no
										},
										success : function(data) {
											var susval = [];
											var length = data.length - 1;
											var enc = data[length]
													.substring(0,
															16);
											for (var i = 0; i < data.length; i++) {
												susval.push(dec(
														enc,
														data[i]));
											}
											var dataCountry1 = susval
													.join("|");
											var myResponse = [];
											var autoTextVal = susNoAuto
													.val();
											$
													.each(
															dataCountry1
																	.toString()
																	.split(
																			"|"),
															function(
																	i,
																	e) {
																var newE = e
																		.substring(
																				0,
																				autoTextVal.length);
																if (e
																		.toLowerCase()
																		.includes(
																				autoTextVal
																						.toLowerCase())) {
																	myResponse
																			.push(e);
																}
															});
											response(myResponse);
										}
									});
						},
						minLength : 1,
						autoFill : true,
						change : function(event, ui) {
							if (ui.item) {
								return true;
							} else {
								alert("Please Enter Approved Unit SUS No");
								document
										.getElementById("record_office_unit").value = "";
								susNoAuto.val("");
								susNoAuto.focus();
								return false;
							}
						},
						select : function(event, ui) {
							var sus_no = ui.item.value;
							$.post(
									"getRecordofficeUnitNameList?" + key
											+ "=" + value, {
										sus_no : sus_no
									}, function(j) {

									}).done(
									function(j) {
										var length = j.length - 1;
										var enc = j[length]
												.substring(0, 16);
										$("#record_office_unit").val(
												dec(enc, j[0]));
										
										$("#record_office_unit").attr('readonly', true);

									}).fail(
									function(xhr, textStatus,
											errorThrown) {
									});
						}
					});
		});




$("#record_office_unit").keyup(
		function(){
			
			var record_office = this.value;
			var susNoAuto = $("#record_office_unit");
			susNoAuto.autocomplete({
						source : function(request, response) {
							$.ajax({
										type : 'POST',
										url : "getRecordoffice_UnitNameList?"
												+ key + "=" + value,
										data : {
											record_office : record_office
										},
										success : function(data) {
											var susval = [];
											var length = data.length - 1;
											var enc = data[length]
													.substring(0,
															16);
											for (var i = 0; i < data.length; i++) {
												susval.push(dec(
														enc,
														data[i]));
											}
											var dataCountry1 = susval
													.join("|");
											var myResponse = [];
											var autoTextVal = susNoAuto
													.val();
											$
													.each(
															dataCountry1
																	.toString()
																	.split(
																			"|"),
															function(
																	i,
																	e) {
																var newE = e
																		.substring(
																				0,
																				autoTextVal.length);
																if (e
																		.toLowerCase()
																		.includes(
																				autoTextVal
																						.toLowerCase())) {
																	myResponse
																			.push(e);
																}
															});
											response(myResponse);
										}
									});
						},
						minLength : 1,
						autoFill : true,
						change : function(event, ui) {
							if (ui.item) {
								return true;
							} else {
								alert("Please Enter Approved Unit Name");
								document
										.getElementById("record_office_sus").value = "";
								susNoAuto.val("");
								susNoAuto.focus();
								return false;
							}
						},
						select : function(event, ui) {
							var record_office = ui.item.value;
							$.post("getRecordofficeSUSNoFromUnitName?" + key
											+ "=" + value, {
										record_office : record_office
									}, function(j) {

									}).done(
									function(j) {
										var length = j.length - 1;
										var enc = j[length]
												.substring(0, 16);
										$("#record_office_sus").val(
												dec(enc, j[0]));
										$("#record_office_unit").attr('readonly', true);
									}).fail(
									function(xhr, textStatus,
											errorThrown) {
									});
						}
					});
		});


//*****************END INTER ARM  DETAILS************************//
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
var civ_id=$('#civ_id').val();        
//var p_id=$('#census_id').val();
var ser_radio = $('input:radio[name=spouse_ser_radio]:checked').val();
var spouse_personal_no=$("#spouse_personal_no").val();
var spouseSer_other=$("#spouseSer_other").val();
var spouse_service=$("#spouse_service").val();
var enroll_date=$('#enroll_date').val();  

var separated_from_dt=$("#separated_from_dt").val();
var separated_to_dt=$('#separated_to_dt').val();   

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
  
  if(getformatedDate($("input#enroll_date").val()) > getformatedDate($("#marriage_date_of_authority").val())) {
    alert("Authority Date should be Greater than Enrollment Date");
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
	               
	               
	               if(calculate_age_marr(document.getElementById('marriage_date_of_birth'), document.getElementById('marriage_date')) && calculate_age_marr(document.getElementById('dob_date'), document.getElementById('marriage_date'))) {} else {
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




   $.post('update_family_marriage_civ_action?' + key + "=" + value, {separated_to_dt:separated_to_dt,separated_from_dt:separated_from_dt,curr_marital_status:curr_marital_status,maiden_name:maiden_name,marriage_date_of_birth:marriage_date_of_birth,
           marriage_place_of_birth:marriage_place_of_birth,marriage_nationality:marriage_nationality,marriage_date:marriage_date,marriage_adhar_no:marriage_adhar_no,pan_card:pan_card,
           marr_ch_id:marr_ch_id,divorce_date:divorce_date,marriage_authority:marriage_authority,marriage_date_of_authority:marriage_date_of_authority
           ,marriage_nationality_other:marriage_nationality_other,enroll_date:enroll_date,marital_event:marital_event,civ_id:civ_id ,marital_status:marital_status,ser_radio:ser_radio,spouse_service:spouse_service,spouseSer_other:spouseSer_other,spouse_personal_no:spouse_personal_no}, function(data){
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

	 var civ_id=$('#civ_id').val(); 
	 var marital_event=$('#marital_event').val();
	
	  $.post('update_getfamily_marriageCivData?' + key + "=" + value, {marital_event:marital_event,civ_id:civ_id }, function(j){
		
		 
			var v=j.length;
		if(v!=0){
			
		
			
			if(curr_marital_status==8 && j[0].status==0){
						$("#marr_quali_radio1").prop("checked", true);
						$("#marr_quali_radio2").prop("disabled", true);
						  $('#spouse_Qualifications').hide();
						  $('#mrgbtn_save').show();
						  $('#mrgqualibtn_save').hide();
						  $('#marital_eventDiv').show();
			}
						if($('#marital_event').val()=="0"){
								$('#marital_event').val(j[0].type_of_event);
								$('#marital_status').val(j[0].marital_status);
						}
						$('#maiden_name').val(j[0].maiden_name);
						$('#marriage_date_of_birth').val(ParseDateColumncommission(j[0].dob));
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
						$('#separated_from_dt').val(ParseDateColumncommission(j[0].separated_from_dt));
						$('#separated_to_dt').val(ParseDateColumncommission(j[0].separated_to_dt));
						
						if(j[0].if_spouse_ser=="Yes"){
							$('#spouse_ser_radio1').prop('checked', true);
						}
						else{
							$('#spouse_ser_radio2').prop('checked', true);
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
//							$('#marriage_date_of_authority').prop('disabled', true);	
//							$("#marriage_date_of_authority").datepicker( "option", "disabled", true );
//							$('#marriage_authority').prop('disabled', true);
							
							
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
						
						if($('#marital_event').val()=="2"){
							$("#divorce_widow_widower_dtlbl").text("Date of Divorced")
						}
						if($('#marital_event').val()=="5" || $('#marital_event').val()=="6"){
							$("#divorce_widow_widower_dtlbl").text("Date of Death")
						}
						
						
						
						}
						
						else if($('#marital_event').val()=='4'){
							 $("#seperateDiv").show();
							
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
//							$("#marital_event option[value='1']").remove();
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
//						$("#marital_event option[value='1']").remove();
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

	} else {

		$("#spouse_inserviceDiv").hide();
		$("#spouse_personal_no").val("");
		$("#spouseSer_other").val("");
		$("#spouse_service").val("0");
	}
}
function Spouse_ServiceOtherFn(divId, perId, obj) {
	if(obj.value == 9) {
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
	spouse_id = $("#marr_ch_id").val();
	var a = $('input:radio[name=spouse_quali_radio]:checked').val();
	
	
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
	
	var civ_id = $('#civ_id').val();
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

	  if($("#spouse_quali option:selected").text().toUpperCase()==other || $("#spouse_quali option:selected").text().toUpperCase()=="OTHER") {
	     	 if(exam_other_qua == null || exam_other_qua.trim()==""){ 	 
	     		alert( "Please Enter Examination Other ");
				
				$("input#exam_otherSpouse").focus();
				return false;
			   }
	      }
	  
	  if($("#spouse_quali option:selected").text().toUpperCase()==other || $("#spouse_quali option:selected").text().toUpperCase()=="OTHER") {
			if(lengthValidation($("input#exam_otherSpouse").val().trim(),auth_length)){
				alert("Examination Other Length Should be Less or Equal To 100")
				$("#exam_otherSpouse").focus();
				return false;
			}
	      }
	  
	if(degree == null || degree == "0") {
		alert("Please Select The Degree Acquired");
		$("#quali_degree_spouse").focus();
		return false;
	}
	if($("#quali_degree_spouse option:selected").text().toUpperCase()==other || $("#quali_degree_spouse option:selected").text().toUpperCase()=="OTHER") {
      	 if(degree_other == null || degree_other.trim()==""){ 	       
      		alert( "Please Enter Degree Other ");
 			$("input#quali_deg_otherSpouse").focus();
 			return false;
 		   }
       }
	
	if($("#quali_degree_spouse option:selected").text().toUpperCase()==other || $("#quali_degree_spouse option:selected").text().toUpperCase()=="OTHER") {
		if(lengthValidation($("input#quali_deg_otherSpouse").val().trim(),auth_length)){
			alert("Degree Other Length Should be Less or Equal To 100")
			$("#quali_deg_otherSpouse").focus();
			return false;
		}
      }
	
	if(specialization == null || specialization == "0") {
		alert("Please Select The Specialization");
		$("#spouse_specialization").focus();
		return false;
	}
	





  
  if($("#spouse_specialization option:selected").text().toUpperCase()==other || $("#spouse_specialization option:selected").text().toUpperCase()=="OTHER") {
   	 if(spec_other == null || spec_other.trim()==""){ 	 
    
			alert( "Please Enter Specialization Other ");
			$("input#quali_spec_otherSpouse").focus();
			return false;
		   }
    }
  
  if($("#spouse_specialization option:selected").text().toUpperCase()==other || $("#spouse_specialization option:selected").text().toUpperCase()=="OTHER") {
	  if(lengthValidation($("input#quali_spec_otherSpouse").val().trim(),auth_length)){
			alert("Specialization Other Length Should be Less or Equal To 100")
			$("#quali_spec_otherSpouse").focus();
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
	alert("Please Select The Div/Grade/PCT(%)");
	$("#spouse_div_class").focus();
	return false;
}
if($("#spouse_div_class option:selected").text().toUpperCase()==other || $("#spouse_div_class option:selected").text().toUpperCase()=="OTHER") {
	 if(class_other_qua == null || class_other_qua.trim()==""){ 	 

		alert( "Please Enter Div/Grade/PCT(%) Other ");
		$("input#class_otherSpouse").focus();
		return false;
	   }
}


if($("#spouse_div_class option:selected").text().toUpperCase()==other || $("#spouse_div_class option:selected").text().toUpperCase()=="OTHER") {
	  if(lengthValidation($("input#class_otherSpouse").val().trim(),auth_length)){
			alert("Div/Grade/PCT(%) Other Length Should be Less or Equal To 100")
			$("#class_otherSpouse").focus();
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
	$.post('spouse_qualification_Civaction?' + key + "=" + value, {
		type: type,
		examination_pass: examination_pass,
		passing_year: passing_year,
		div_class: div_class,
		subject: subject,
		institute: institute,
		qualification_ch_id: qualification_ch_id,		
		degree: degree,
		specialization: specialization,
		civ_id: civ_id,
		dateofBirthyear: dateofBirthyear,
		exam_other_qua:exam_other_qua,class_other_qua:class_other_qua,		
		degree_other:degree_other,spec_other:spec_other,
		spouse_id: spouse_id
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
	var civ_id=$('#civ_id').val();	
	  $.post('updated_family_marriage_delete_Civ_action?' + key + "=" + value, {civ_id:civ_id,qali_status:1}, function(data){ 
	                   if(data=='1'){
	                	   $('#spouse_qualification_ch_id').val(0);
	                   }	
	                   
	 }).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  });
}

function getSpouseQualificationData() {
	 var civ_id=$('#civ_id').val(); 
	var i = 0;
	$.post('getSpouseQualificationCivData?' + key + "=" + value, {
		civ_id: civ_id,app_status:0
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
	 
	
	 
	 fn_otherShowHide(document.getElementById('spouse_quali'),'other_div_examSpouse','exam_otherSpouse');
	 fn_otherShowHide(document.getElementById('quali_degree_spouse'),'other_div_qualiDegSpouse','quali_deg_otherSpouse');
	 fn_otherShowHide(document.getElementById('spouse_specialization'),'other_div_qualiSpecSpouse','quali_spec_otherSpouse');
}



var other="OTHERS";
function fn_otherShowHide(obj,Divother_id,other_id){
	var thisText=$("#"+obj.id+" option:selected").text().toUpperCase();
	if(thisText==other || thisText=="OTHER"){
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




function Firing_UnitAuto(obj) {
		
		var check_id = obj.id;
		var k = check_id.replace('firing_unit_name','');
		
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
       alert("Please Enter The Valid Year");
       $("#"+obj.id).val("");
}
}
/////////////////////For data get fire standard
function fire_Details() {
var census_id = $('#census_id').val();
var civ_id = $('#civ_id').val();
var i = 0;
$.post('getfire_detailsData_jco?' + key + "=" + value,{ civ_id:civ_id},function(j) {
	
	var v = j.length;
	if (v != 0) {
		$('#fire_stdtbody').empty();
		for (i; i < v; i++) {
			x = i + 1;
			$("table#fire_std_table").append('<tr id="tr_fire_std'+x+'">'
				+'<td class="fire_srno">'+x+'</td>'
				+ '<td style="width: 10%;">'
				+ '<select name="firing_grade'+x+'" id="firing_grade'+x+'" class="form-control" onchange="onFiringGrade('+x+')">'
				+'<option value="-1">--Select--</option>'
				+'<c:forEach var="item" items="${getFiring_grade}" varStatus="num">'
               +' <option value="${item[0]}" name="${item[1]}">${item[1]}</option>'
               +' </c:forEach>'
				+'</select> </td>'
				
				+'<td style="width: 10%;"> <input type="text" id="ot_firing_grade'+x+'" name="ot_firing_grade'+x+'" value="'+j[i].ot_firing_grade+'" onkeypress="return onlyAlphabets(event);" readonly  class="form-control autocomplete" autocomplete="off" ></td>'
				
				
				+ '<td style="width: 10%;">'
				+ '<select name="firing_event_qe'+x+'" id="firing_event_qe'+x+'" class="form-control"   >'
				+'<option value="-1">--Select--</option>'
				+'<c:forEach var="item" items="${getFiring_event_qe}" varStatus="num">'
               +'<option value="${item[0]}" name="${item[1]}">${item[1]}</option>'
               +'</c:forEach>'
				+ '</select> </td>'
				+ '<td style="width: 10%;">'
				+ '<input type="text" id="year'+x+'" name="year'+x+'" value="'+j[i].year+'" class="form-control" autocomplete="off"  maxlength="4"  onkeypress="return isNumberPointKey(event);">'
				+ '</td>'
				+ '<td style="width: 10%;">'
				+'<input type="text" id="firing_unit_name'+x+'" name="firing_unit_name'+x+'" class="form-control autocomplete" onkeypress="Firing_UnitAuto(this);" autocomplete="off" maxlength="50">'
				+'<input type="hidden" id="firing_unit_sus_no'+x+'" name="firing_unit_sus_no'+x+'" value="'+j[i].firing_unit_sus_no+'" class="form-control autocomplete" autocomplete="off" maxlength="50">'
				+ '</td>'
				+ '<td style="display:none;"><input type="text" id="fire_id'+x+'" name="fire_id'+x+'"   value="'+j[i].id+'"  class="form-control autocomplete" autocomplete="off" ></td>'
				+ '<td style="width: 10%;"><a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "fire_std_save'+ x + '" onclick="fire_std_save_fn('+ x+ ');" ><i class="fa fa-save"></i></a>'
				+ '<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "fire_std_add'+ x+ '" onclick="fire_std_add_fn('+ x+ ');" ><i class="fa fa-plus"></i></a>'
               + '<a  class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "fire_remove'+ x+ '" onclick="fire_remove_fn('+ x + ');"><i class="fa fa-trash"></i></a>'
				+ '<td style="display:none;"><input type="text" id="status'+x+'" name="status'+x+'"   value="'+j[i].status+'"  class="form-control autocomplete" autocomplete="off" ></td>'
				+ '</td></tr>');
			$('#firing_event_qe'+x).val(j[i].firing_event_qe);
			$('#firing_grade'+x).val(j[i].firing_grade);
			onFiringGrade(x);
			var sus_no =j[i].firing_unit_sus_no;
			fn_getUnitnamefromSus(sus_no, document.getElementById("firing_unit_name"+x));
			
		
		}
		fs = v;
		fire_srno = v;
		$('#fire_std_add' + fs).show();
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
var dateofBirthyear =$("#dob").text().split('-')[2];
var cyear = d.getFullYear();
if ($("select#firing_grade"+ps).val() == "-1") {
alert("Please Select Firing Grade");
$("#firing_grade"+ps).focus();
return false;
}
var ot_firing_grade=$('#ot_firing_grade'+ps).val();
var firing_gradeV = $("#firing_grade"+ps+" option:selected").text().toUpperCase();
if (firing_gradeV == "OTHERS" && ot_firing_grade.trim()=="") {
	alert("Please Enter Other Firing Grade");
	$('#ot_firing_grade'+ps).focus();
	return false;
}

if(firing_gradeV == "OTHERS" || firing_gradeV == "OTHER"){
	if(lengthValidation($("input#ot_firing_grade"+ps).val().trim(),auth_length)){
		alert("Firing Grade Other Length Should be Less or Equal To 100")
		$("#ot_firing_grade"+ps).focus();
		return false;
	}
}

if ($("select#firing_event_qe"+ps).val() == "-1") {
alert("Please Select Firing Event QTR");
$("#firing_event_qe"+ps).focus();
return false;
}
if($("#year"+ps).val().trim()=="" || $("#year"+ps).val().trim()=="0"){
alert("Please Enter The Year");
$("#year"+ps).focus();
return false;
}	
if($("#year"+ps).val() > cyear){
alert("Future Year is not allowed");
$("#year"+ps).focus();
return false;
} 
if($("#year"+ps).val() < dateofBirthyear){
	alert("Please Enter The Valid Year");
	$("#year"+ps).focus();
	return false;
	}  
if($("#year"+ps).val() == cyear && fire_validate($("select#firing_event_qe"+ps).val())==0){
alert("Future Month is not allowed");
$("#firing_event_qe"+ps).focus();
return false;
} 

var firing_unit_sus_no=$('#firing_unit_sus_no'+ps).val()
   
	if($("#firing_unit_name"+ps).val().trim()==""){
		alert("Please Enter Conducted At Unit");
		$("#firing_unit_name"+ps).focus();
		return false;
	}
	if(firing_unit_sus_no.trim()==""){
		alert("Please Enter Valid Conducted At Unit");
		$("#firing_unit_name"+ps).focus();
		return false;
	}
	
var firing_grade=$('#firing_grade'+ps).val();

var firing_event_qe=$('#firing_event_qe'+ps).val();
var year=$('#year'+ps).val();

var fire_id=$('#fire_id'+ps).val();

var civ_id=$('#civ_id').val();



$.post('fire_std_action_jco?' + key + "=" + value, {firing_grade:firing_grade,ot_firing_grade:ot_firing_grade,firing_event_qe:firing_event_qe,
  year:year,firing_unit_sus_no:firing_unit_sus_no,fire_id:fire_id,civ_id:civ_id }, function(data){
     if(data=="update")
   	  alert("Data Updated Successfully");
     else if(parseInt(data)>0){
   	 $('#fire_id'+ps).val(data);
   	  alert("Data Saved Successfully")
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

+'<td style="width: 10%;"> <input type="text" id="ot_firing_grade'+fs+'" name="ot_firing_grade'+fs+'" onkeypress="return onlyAlphabets(event);" readonly  class="form-control autocomplete" autocomplete="off" ></td>'


+'<td style="width: 10%;"> '
+'<select name="firing_event_qe'+fs+'" id="firing_event_qe'+fs+'" class="form-control" >'
+'<option value="-1">--Select--</option>'
+'<c:forEach var="item" items="${getFiring_event_qe}" varStatus="num">'
+' <option value="${item[0]}" name="${item[1]}">${item[1]}</option>'
+'</c:forEach>'
+'</select></td>'
+'<td style="width: 10%;">	<input type="text" id="year'+fs+'" name="year'+fs+'" value="0" class="form-control autocomplete"   autocomplete="off" maxlength="4" onkeypress="return isNumberPointKey(event);">'
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
 $.post('fire_delete_action_jco?' + key + "=" + value, {fire_id:fire_id }, function(data){ 
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
						 alert("Data Deleted Successfully");
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
			var k = check_id.replace('b_unit_name','');
			
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
					        	$("#be_unit_sus_no"+k).val(dec(enc,data[0]));
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
	var dateofBirthyear =$("#dob").text().split('-')[2];
	if ($("select#BPET_result"+ps).val() == "-1") {
		alert("Please Select BPET Result");
		$("#BPET_result"+ps).focus();
		return false;
	}
	var bept_result_others=$('#bept_result_others'+ps).val().toUpperCase();
	var BPET_resultV = $("#BPET_result"+ps+" option:selected").text();
	if ((BPET_resultV == "OTHERS" || BPET_resultV == "OTHER" ) && bept_result_others.trim()=="") {
		alert("Please Enter Other BPET Results");
		$('#bept_result_others'+ps).focus();
		return false;
	}
	
	if(BPET_resultV == "OTHERS" || BPET_resultV == "OTHER"){
		if(lengthValidation($("input#bept_result_others"+ps).val().trim(),auth_length)){
			alert("BPET Results Other Length Should be Less or Equal To 100")
			$("#bept_result_others"+ps).focus();
			return false;
		}
	}
	
 	if ($("select#BPET_qe"+ps).val() == "-1") {
		alert("Please Select BPET QTR");
		$("#BPET_qe"+ps).focus();
		return false;
	}
 	if($("#bpet_year"+ps).val().trim()=="" || $("#bpet_year"+ps).val().trim()=="0"){
		alert("Please Enter The Year");
		$("#bpet_year"+ps).focus();
		return false;
	}	 
    if($("#bpet_year"+ps).val() > cyear){
		alert("Future Year cannot be allowed");
		$("#bpet_year"+ps).focus();
		return false;
	} 
    if($("#bpet_year"+ps).val() < dateofBirthyear){
    	alert("Please Enter The Valid Year");
    	$("#bpet_year"+ps).focus();
    	return false;
    	}
    if($("#bpet_year"+ps).val() == cyear && fire_validate($("select#BPET_qe"+ps).val())== 0){
		alert("Future Month is not allowed");
		$("#BPET_qe"+ps).focus();
		 return false;
	} 
    var sus_no=$('#be_unit_sus_no'+ps).val();
    
	if($("#b_unit_name"+ps).val().trim()==""){
		alert("Please Enter Conducted At Unit");
		$("#b_unit_name"+ps).focus();
		return false;
	}
	
	if(sus_no.trim()==""){
		alert("Please Enter Valid Conducted At Unit");
		$("#b_unit_name"+ps).focus();
		return false;
	}
	
	var BPET_result=$('#BPET_result'+ps).val();
	
	
	var BPET_qe=$('#BPET_qe'+ps).val();
	var year=$('#bpet_year'+ps).val();
	
	var id=$('#id'+ps).val();
	
	var civ_id=$('#civ_id').val();
	if (BPET_result == "-1") {
		alert("Please Enter BPET Result");
		$("input#BPET_result").focus();
		return false;
	}
	

	
	
 
	   $.post('BPET_JCOaction?' + key + "=" + value, {BPET_result : BPET_result ,bept_result_others:bept_result_others,BPET_qe : BPET_qe,
		   year:year,sus_no:sus_no,id : id,civ_id:civ_id }, function(data){
	          if(data=="update")
	        	  alert("Data Updated Successfully");
	          else if(parseInt(data)>0){
	        	 $('#id'+ps).val(data);
	        	  alert("Data Saved Successfully")
	        	  $("#bpet_add"+ps).show();
		        	 $("#bpet_remove"+ps).show();
	          }
	          else
	        	  alert(data);
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});
}
function bpet_Details() {
	
	var civ_id = $('#civ_id').val();
	var i = 0;
	$.post('getbpet_JCOData?' + key + "=" + value,{civ_id:civ_id},function(j) {
		var v = j.length;
		if (v != 0) {
			$('#bpettbody').empty();
			for (i; i < v; i++) {
				x = i + 1;
				$("table#bpet_table").append('<tr id="tr_bpet'+x+'">'
					+'<td class="bpet_srno">'+x+'</td>'
					+ '<td style="width: 10%;">'
					+ '<select name="BPET_result'+x+'" id="BPET_result'+x+'" onchange="onBeptResult('+x+')" class="form-control">'
					+'<option value="-1">--Select--</option>'
                    +'<c:forEach var="item" items="${getBPET_result}" varStatus="num">'
                    +'<option value="${item[0]}" name="${item[1]}">${item[1]}</option>'
                   +' </c:forEach>'
					+'</select> </td>'
					+'<td style="width: 10%;">' 
					+'<input type="text" id="bept_result_others'+x+'" name="bept_result_others'+x+'" onkeypress="return onlyAlphabets(event);"  value="' + j[i].bept_result_others + '"  class="form-control autocomplete" autocomplete="off" >'
					+'</td>'
					
					
					+ '<td style="width: 10%;">'
					+ '<select name="BPET_qe'+x+'" id="BPET_qe'+x+'" class="form-control">'
					+' <option value="-1">--Select--</option>'
                    +'<c:forEach var="item" items="${getBPET_event_qe}" varStatus="num">'
                    +'<option value="${item[0]}" name="${item[1]}">${item[1]}</option>'
                    +' </c:forEach>'
					+ '</select> </td>'
					+ '<td style="width: 10%;">'
					+ '<input type="text" id="bpet_year'+x+'" name="year'+x+'" value="'+j[i].year+'" class="form-control" autocomplete="off" maxlength="4" onkeypress="return isNumberPointKey(event);">'
					+ '</td>'
					+'<td style="width: 20%;">'
					+'<input type="text" id="b_unit_name'+x+'"" name="unit_name'+x+'"" class="form-control autocomplete" onkeypress="BPET_UnitAuto(this);" autocomplete="off" maxlength="50">'
					+'<input type="hidden" id="be_unit_sus_no'+x+'" name="be_unit_sus_no'+x+'" value="'+j[i].sus_no+'"  class="form-control autocomplete" autocomplete="off" maxlength="50">'
					+'</td>'
					+ '<td style="display:none;"><input type="text" id="id'+x+'" name="id'+x+'"   value="'+j[i].id+'"  class="form-control autocomplete" autocomplete="off" ></td>'
					+ '<td style="width: 10%;"><a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "bpet_save'+ x + '" onclick="bpet_save_fn('+ x+ ');" ><i class="fa fa-save"></i></a>'
					+ '<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "bpet_add'+ x+ '" onclick="bpet_add_fn('+ x+ ');" ><i class="fa fa-plus"></i></a>'
                    + '<a  class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "bpet_remove'+ x+ '" onclick="bpet_remove_fn('+ x + ');"><i class="fa fa-trash"></i></a>'
					+ '<td style="display:none;"><input type="text" id="status'+x+'" name="status'+x+'"   value="'+j[i].status+'"  class="form-control autocomplete" autocomplete="off" ></td>'
					+ '</td></tr>');
				$('#BPET_result'+x).val(j[i].bpet_result);
				$('#BPET_qe'+x).val(j[i].bpet_qe);
				onBeptResult(x);
				var sus_no =j[i].sus_no;
				fn_getUnitnamefromSus(sus_no, document.getElementById("b_unit_name"+x));
				
			
			}
			bpet = v;
			bpet_srno = v;
			$('#bpet_add' + bpet).show();
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
  	+'<td style="width: 10%;"> <input type="text" id="bept_result_others'+bpet+'" name="bept_result_others'+bpet+'" onkeypress="return onlyAlphabets(event);" readonly  class="form-control autocomplete" autocomplete="off" ></td>'

	+'<td style="width: 10%;"> '
	+'<select name="BPET_qe'+bpet+'" id="BPET_qe'+bpet+'" class="form-control"   >'
	+' <option value="-1">--Select--</option>'
    +' <c:forEach var="item" items="${getBPET_event_qe}" varStatus="num">'
    +' <option value="${item[0]}" name="${item[1]}">${item[1]}</option>'
    +'</c:forEach>'
	+'</select></td>'
	+'<td style="width: 10%;">	<input type="text" id="bpet_year'+bpet+'" name="year'+bpet+'" value="0" class="form-control autocomplete"   autocomplete="off" maxlength="4" onkeypress="return isNumberPointKey(event);">'
	+'</td>'
	+'<td style="width: 20%;">'
	+'<input type="text" id="b_unit_name'+bpet+'"" name="unit_name'+bpet+'"" class="form-control autocomplete" onkeypress="BPET_UnitAuto(this);" autocomplete="off">'
	+'<input type="hidden" id="be_unit_sus_no'+bpet+'" name="be_unit_sus_no'+bpet+'" class="form-control autocomplete" autocomplete="off">'
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
	  $.post('bpet_delete_JCOaction?' + key + "=" + value, {id:id }, function(data){ 
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
							 alert("Data Deleted Successfully");
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
	
	if(text == "OTHERS"){
		$("#other_id").show();
	}
	else{
		$("#other_id").hide();
		$("#hair_other").val("");
	}
}
function ideys_other() {
	var text = $("#eye_colour option:selected").text();
	if(text == "OTHERS"){
		$("#other_eye").show();
	}
	else{
		$("#other_eye").hide();
		$("#eye_other").val("");
	}
}

function getIdentity_Card(){
	 
	 var civ_id=$('#civ_id').val(); 
	 var i=0;
	  $.post('getIdentity_CardJCO?' + key + "=" + value, {civ_id:civ_id}, function(j){
			var v=j.length;
			if(v!=0){
				if(j[i].status==0){
				$('#card_id').val(j[i].id);
				 $("#id_card_no").val(j[i].id_card_no);
				 $("#issue_dt").val(ParseDateColumncommission(j[i].issue_dt));
				 $("#issue_authority_sus").val(j[i].issue_authority);
				 fn_getUnitnamefromSus(j[i].issue_authority, document.getElementById("issue_authority"));
				 $("#id_marks").val(j[i].id_marks);
				 $("#hair_colour").val(j[i].hair_colour);
				 $("#eye_colour").val(j[i].eye_colour);
				  $('#identity_image_preview').attr("src", "censusIdentityJCOConvertpath?i_id="+j[i].id+"");
				  $("#image_updated_date").val(ParseDateColumn(j[i].image_updated_date));
				}
				else{
					  $('#identity_image_preview').attr("src", "censusIdentityJCOConvertpath?i_id="+j[i].id+"");
					  $("#image_updated_date").val(ParseDateColumn(j[i].image_updated_date));
					  $("#pre_identity_id").val(j[i].id);
				}
				
				var text = $("#hair_colour option:selected").text();
				if(text == "OTHERS"){
					$('#hair_other').val(j[i].hair_other);
					$("#other_id").show();
				}
				else{
					$("#other_id").hide();
				}
				
				var text = $("#eye_colour option:selected").text();
				if(text == "OTHERS"){
					$('#eye_other').val(j[i].eye_other);
					$("#other_eye").show();
				}
				else{
					$("#other_eye").hide();
				}
			}
	  });
}	


function validate_identity_card_save(){
	var civ_id=$('#civ_id').val();
	
	var status=$('#status').val();
	var card_id=$('#card_id').val();
	var enroll_date=$('#enroll_date').val();
 	if($("#id_card_no").val().trim()==""){
		alert("Please Enter ID Card No");
		$("#id_card_no").focus();
		return false;
	}
	
	if($("#issue_dt").val() == "DD/MM/YYYY" || $("#issue_dt").val().trim()==""){
		alert("Please Enter Issue Date");
		$("#issue_dt").focus();
		return false;
	} 
	if(getformatedDate($("input#enroll_date").val()) > getformatedDate($("#issue_dt").val())) {
		alert("Issue Date should be Greater than Enrollment Date");
		$("#issue_dt").focus();
		return false;
  }
	
	if($("#issue_authority").val().trim()==""){
		alert("Please Enter Issuing Authority");
		$("#issue_authority").focus();
		return false;
	}
	
	
	
	if($("#id_marks").val().trim()==""){
		alert("Please Enter  Visible Identification Marks");
		$("#id_marks").focus();
		return false;
	}
	
	if($("#hair_colour").val() == 0){
		alert("Please Select Hair Colour");
		$("#hair_colour").focus();                 
		return false;
	}
	var hair_colourV = $("#hair_colour option:selected").text().toUpperCase();
	if((hair_colourV == "OTHERS" || hair_colourV == "OTHER") &&  $("#hair_other").val().trim()=="" ){
		alert("Please Enter Hair Colour Other");
		$("#hair_other").focus();
		return false;
	} 
	if(hair_colourV == "OTHERS" || hair_colourV == "OTHER"){
		if(lengthValidation($("input#hair_other").val().trim(),auth_length)){
			alert("Hair Colour Other Length Should be Less or Equal To 100")
			$("#hair_other").focus();
			return false;
		}
	}
	
	if($("#eye_colour").val() == 0){
		alert("Please Select Eye Colour");
		$("#eye_colour").focus();
		return false;
	} 
	
	var eye_colourV = $("#eye_colour option:selected").text().toUpperCase();
	if((eye_colourV == "OTHERS" || eye_colourV == "OTHER") &&  $("#eye_other").val().trim()=="" ){
		alert("Please Enter Eye Colour Other");
		$("#eye_other").focus();
		return false;
	}
	if(eye_colourV == "OTHERS" || eye_colourV == "OTHER"){
		if(lengthValidation($("#eye_other").val().trim(),auth_length)){
			alert("Eye Colour Other Length Should be Less or Equal To 100")
			$("#eye_other").focus();
			return false;
		}
	}
	
	 var file = document.getElementById("identity_image"); 
	  var preDate = document.getElementById('image_updated_date').value;
	  
	  var years = new Date(new Date() - new Date(preDate)).getFullYear() - 1970;
	  
	  
	if(years>=8){
	  if(file.files.length == 0){
			  alert("Please Upload Current Photograph");			
				return false;        
	  }
	}
	  
	  
	  
	 var form_data = new FormData(document.getElementById("form_identity_card"));		
		form_data.append("civ_id",civ_id);
		form_data.append("enroll_date",enroll_date);
   
		   $.ajax({
		        url: 'Identity_Card_JcoOr_Action?_csrf='+value,
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
	        	  alert("Data Saved Successfully")
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
	$("input#addr_authority").val($("input#addr_authority").val().trim());
	 var pre_country_text = $("#pre_country option:selected").text().toUpperCase();
	if ($("select#pre_country").val() == "0") {
		alert("Please Select Present Domicile Country");
		$("select#pre_country").focus();
		return false;
	}
   var text1 = $("#pre_country option:selected").text().toUpperCase();
	
	if((text1 == "OTHERS" || text1 == "OTHER") && $("input#pre_country_other").val().trim()==""){
		alert("Please Enter Present Domicile Country Other");
		$("input#pre_country_other").focus();
		return false;
	}
	
	if(text1 == "OTHERS" || text1 == "OTHER"){
		if(lengthValidation($("input#pre_country_other").val().trim(),auth_length)){
			alert("Domicile Country Other Length Should be Less or Equal To 100")
			$("#pre_country_other").focus();
			return false;
		}
	}
	
	 if ($("select#pre_domicile_state").val() == "0") {
		alert("Please Select Present Domicile State/Province");
		$("select#pre_domicile_state").focus();
		return false;
	} 
	 var text2 = $("#pre_domicile_state option:selected").text();
		
		if((text2 == "OTHERS" || text2 == "OTHER") && $("input#pre_domicile_state_other").val().trim()==""){
			alert("Please Enter Present Domicile State/Province Other");
			$("input#pre_domicile_state_other").focus();
			return false;
		}
		
		if(text2 == "OTHERS" || text2 == "OTHER"){
			if(lengthValidation($("input#pre_domicile_state_other").val().trim(),auth_length)){
				alert("Domicile State/Province Other Length Should be Less or Equal To 100")
				$("#pre_domicile_state_other").focus();
				return false;
			}
		}
		
	if ($("select#pre_domicile_district").val() == "0") {
		alert("Please Select Present Domicile District");
		$("select#pre_domicile_district").focus();
		return false;
	} 
	var text3 = $("#pre_domicile_district option:selected").text();
	
	if((text3 == "OTHERS" || text3 == "OTHER") && $("input#pre_domicile_district_other").val().trim()==""){
		alert("Please Enter Present Domicile District Other");
		$("input#pre_domicile_district_other").focus();
		return false;
	}
	
	if(text3 == "OTHERS" || text3 == "OTHER"){
		if(lengthValidation($("input#pre_domicile_district_other").val().trim(),auth_length)){
			alert("Domicile District Other Length Should be Less or Equal To 100")
			$("#pre_domicile_district_other").focus();
			return false;
		}
	}
	
	if(pre_country_text!="BHUTAN"){
		if ($("select#pre_domicile_tesil").val() == "0") {
			alert("Please Select Present Domicile Tehsil/Municipality");
			$("select#pre_domicile_tesil").focus();
			return false;
		}
	  var text4 = $("#pre_domicile_tesil option:selected").text().toUpperCase();
		
		if((text4 == "OTHERS" || text4 == "OTHER") && $("input#pre_domicile_tesil_other").val().trim()==""){
			alert("Please Enter Present Domicile Tehsil/Municipality Other");
			$("input#pre_domicile_tesil_other").focus();
			return false;
		}
		
		if(text4 == "OTHERS" || text4 == "OTHER"){
			if(lengthValidation($("input#pre_domicile_tesil_other").val().trim(),auth_length)){
				alert("Domicile Tehsil/Municipality Other Length Should be Less or Equal To 100")
				$("#pre_domicile_tesil_other").focus();
				return false;
			}
		}
	}
	if ($("select#per_home_addr_country").val() == "0") {
		alert("Please Select The Permanent Address Country");
		$("select#per_home_addr_country").focus();
		return false;
	}
	 var text5 = $("#per_home_addr_country option:selected").text().toUpperCase();
		
		if((text5 == "OTHERS" || text5 == "OTHER" ) && $("input#per_home_addr_country_others").val().trim()==""){
			alert("Please Enter Permanent Address Country Other");
			$("input#per_home_addr_country_others").focus();
			return false;
		}
		
		

		if(text5 == "OTHERS" || text5 == "OTHER"){
				if(lengthValidation($("input#per_home_addr_country_others").val().trim(),auth_length)){
					alert("Permanent Address Country Other Length Should be Less or Equal To 100")
					$("#per_home_addr_country_others").focus();
					return false;
				}
			}
		
	if ($("select#per_home_addr_state").val() == "0") {
		alert("Please Select Permanent Address State/Province");
		$("select#per_home_addr_state").focus();
		return false;
	}
	 var text6 = $("#per_home_addr_state option:selected").text().toUpperCase();
		
		if((text6 == "OTHERS" || text6 == "OTHER") && $("input#per_home_addr_state_others").val().trim()==""){
			alert("Please Enter Permanent Address State/Province Other");
			$("input#per_home_addr_state_others").focus();
			return false;
		}
		if(text6 == "OTHERS" || text6 == "OTHER"){
			if(lengthValidation($("input#per_home_addr_state_others").val().trim(),auth_length)){
				alert("Permanent Address State/Province Other Length Should be Less or Equal To 100")
				$("#per_home_addr_state_others").focus();
				return false;
			}
		}
		
		
	if ($("select#per_home_addr_district").val() == "0") {
		alert("Please Select Permanent Address District");
		$("select#per_home_addr_district").focus();
		return false;
	}
	 var text7 = $("#per_home_addr_district option:selected").text().toUpperCase();
		
		if((text7 == "OTHERS" || text7 == "OTHER") && $("input#per_home_addr_district_others").val().trim()==""){
			alert("Please Enter Permanent Address District Other");
			$("input#per_home_addr_district_others").focus();
			return false;
		}
		if(text7 == "OTHERS" || text7 == "OTHER"){
			if(lengthValidation($("input#per_home_addr_district_others").val().trim(),auth_length)){
				alert("Permanent Address District Other Length Should be Less or Equal To 100")
				$("#per_home_addr_district_others").focus();
				return false;
			}
		}
		
		var per_home_addr_country_text = $("#per_home_addr_country option:selected").text().toUpperCase();
		if(per_home_addr_country_text!="BHUTAN"){
			
	
			if ($("select#per_home_addr_tehsil").val() == "0") {
				alert("Please Select Permanent Address Tehsil/Municipality");
				$("select#per_home_addr_tehsil").focus();
				return false;
			}
			var text8 = $("#per_home_addr_tehsil option:selected").text().toUpperCase();
			
			if((text8 == "OTHERS"|| text8 == "OTHER") && $("input#per_home_addr_tehsil_others").val().trim()==""){
				alert("Please Enter Permanent Address Tehsil/Municipality Other");
				$("input#per_home_addr_tehsil_others").focus();
				return false;
				}
			
			if(text8 == "OTHERS" || text8 == "OTHER"){
				if(lengthValidation($("input#per_home_addr_tehsil_others").val().trim(),auth_length)){
					alert("Permanent Address Tehsil/Municipality Other Length Should be Less or Equal To 100")
					$("#per_home_addr_tehsil_others").focus();
					return false;
				}
			}
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
		var text9 = $("#pers_addr_country option:selected").text().toUpperCase();
		
		if((text9 == "OTHERS"|| text9 == "OTHER") && $("input#pers_addr_country_other").val().trim()==""){
			alert("Please Enter Present Address Country Other");
			$("input#pers_addr_country_other").focus();
			return false;
		}
		
		if(text9 == "OTHERS" || text9 == "OTHER"){
			if(lengthValidation($("input#pers_addr_country_other").val().trim(),auth_length)){
				alert("Present Address Country Other Length Should be Less or Equal To 100")
				$("#pers_addr_country_other").focus();
				return false;
			}
		}

		
		if ($("select#pers_addr_state").val() == "0") {
			alert("Please select Present Address State/Province");
			$("select#pers_addr_state").focus();
			return false;
		}
  var text10 = $("#pers_addr_state option:selected").text().toUpperCase();
		
		if((text10 == "OTHERS"|| text10 == "OTHER") && $("input#pers_addr_state_other").val().trim()==""){
			alert("Please Enter Present Address State/Province Other");
			$("input#pers_addr_state_other").focus();
			return false;
		}
		
		if(text10 == "OTHERS" || text10 == "OTHER"){
			if(lengthValidation($("input#pers_addr_state_other").val().trim(),auth_length)){
				alert("Present Address State/Province Other Length Should be Less or Equal To 100")
				$("#pers_addr_state_other").focus();
				return false;
			}
		}
		
		if ($("select#pers_addr_district").val() == "0") {
			alert("Please select Present Address District");
			$("select#pers_addr_district").focus();
			return false;
		}
var text11 = $("#pers_addr_district option:selected").text().toUpperCase();
		
		if((text11 == "OTHERS"|| text11 == "OTHER") && $("input#pers_addr_district_other").val().trim()==""){
			alert("Please Enter Present Address District Other");
			$("input#pers_addr_district_other").focus();
			return false;
		}
		
		if(text11 == "OTHERS" || text11 == "OTHER"){
			if(lengthValidation($("input#pers_addr_district_other").val().trim(),auth_length)){
				alert("Present Address District Other Length Should be Less or Equal To 100")
				$("#pers_addr_district_other").focus();
				return false;
			}
		}
		
		var pers_addr_country_text = $("#pers_addr_country option:selected").text().toUpperCase();
		if(pers_addr_country_text!="BHUTAN"){
			
		
			if ($("select#pers_addr_tehsil").val() == "0") {
				alert("Please Select Present Address Tehsil/Municipality");
				$("select#pers_addr_tehsil").focus();
				return false;
			}
		var text11 = $("#pers_addr_tehsil option:selected").text().toUpperCase();
			
			if((text11 == "OTHERS"|| text11 == "OTHER") && $("input#pers_addr_tehsil_other").val().trim()==""){
				alert("Please Enter Present Address Tehsil/Municipality Other");
				$("input#pers_addr_tehsil_other").focus();
				return false;
			}
			
			if(text11 == "OTHERS" || text11 == "OTHER"){
				if(lengthValidation($("input#pers_addr_tehsil_other").val().trim(),auth_length)){
					alert("Present Address Tehsil/Municipality Other Length Should be Less or Equal To 100")
					$("#pers_addr_tehsil_other").focus();
					return false;
				}
			}
			
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
var text12 = $("#spouse_addr_Country option:selected").text().toUpperCase();
		
		if((text12 == "OTHERS"|| text12 == "OTHER") && $("input#spouse_addr_country_other").val().trim()==""){
			alert("Please Enter SF ACCN Address Country Other");
			$("input#spouse_addr_country_other").focus();
			return false;
		}
		
		
		if(text12 == "OTHERS" || text12 == "OTHER"){
			if(lengthValidation($("input#spouse_addr_country_other").val().trim(),auth_length)){
				alert("SF ACCN Address Country Other Length Should be Less or Equal To 100")
				$("#spouse_addr_country_other").focus();
				return false;
			}
		}
		
		
		if ($("select#spouse_addr_state").val() == "0") {
			alert("Please select SF ACCN Address State/Province");
			$("select#spouse_addr_state").focus();
			return false;
		}
var text13 = $("#spouse_addr_state option:selected").text().toUpperCase();
		
		if((text13 == "OTHERS"|| text13 == "OTHER") && $("input#spouse_addr_state_other").val().trim()==""){
			alert("Please Enter SF ACCN Address State/Province Other");
			$("input#spouse_addr_state_other").focus();
			return false;
		}
		
		if(text13 == "OTHERS" || text13 == "OTHER"){
			if(lengthValidation($("input#spouse_addr_state_other").val().trim(),auth_length)){
				alert("SF ACCN Address State/Province Other Length Should be Less or Equal To 100")
				$("#spouse_addr_state_other").focus();
				return false;
			}
		}
		
		if ($("select#spouse_addr_district").val() == "0") {
			alert("Please select SF ACCN Address District");
			$("select#spouse_addr_district").focus();
			return false;
		}
var text14 = $("#spouse_addr_district option:selected").text().toUpperCase();
		
		if((text14 == "OTHERS"|| text14 == "OTHER") && $("input#spouse_addr_district_other").val().trim()==""){
			alert("Please Enter SF ACCN Address District Other");
			$("input#spouse_addr_district_other").focus();
			return false;
		}
		
		if(text14 == "OTHERS" || text14 == "OTHER"){
			if(lengthValidation($("input#spouse_addr_district_other").val().trim(),auth_length)){
				alert("SF ACCN Address District Other Length Should be Less or Equal To 100")
				$("#spouse_addr_district_other").focus();
				return false;
			}
		}
		
		var spouse_addr_Country_text = $("#spouse_addr_Country option:selected").text().toUpperCase();
		if(spouse_addr_Country_text!="BHUTAN"){
			if ($("select#spouse_addr_tehsil").val() == "0") {
				alert("Please Select SF ACCN Address Tehsil/Municipality");
				$("select#spouse_addr_tehsil").focus();
				return false;
			}
		var text15 = $("#spouse_addr_tehsil option:selected").text().toUpperCase();
			
			if((text15 == "OTHERS"|| text15 == "OTHER") && $("input#spouse_addr_tehsil_other").val().trim()==""){
				alert("Please Enter SF ACCN Address Tehsil/Municipality Other");
				$("input#spouse_addr_tehsil_other").focus();
				return false;
			}
			
			if(text15 == "OTHERS" || text15 == "OTHER"){
				if(lengthValidation($("input#spouse_addr_district_other").val().trim(),auth_length)){
					alert("SF ACCN Address Tehsil/Municipality Other Length Should be Less or Equal To 100")
					$("#spouse_addr_district_other").focus();
					return false;
				}
			}
			
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
		if ($("input#Stn_HQ_unit_name").val().trim()=="") {
			alert("Please Enter SF ACCN Address Stn HQ SUS Name");
			$("input#Stn_HQ_unit_name").focus();
			return false;
		}
	}
	
	
	var formvalues=$("#form_addr_details_form").serialize();
	
	var addr_ch_id=$("#addr_ch_id").val();	
		var civ_id=$("#civ_id").val();
		var enroll_date=$("#enroll_date").val();
		var marital_status = $('#marital_status_p').text();
		formvalues+="&addr_ch_id="+addr_ch_id+"&civ_id="+civ_id+"&marital_status="+marital_status+"&enroll_date="+enroll_date;	
		formvalues+="&pre_country_text="+pre_country_text+"&spouse_addr_Country_text="+spouse_addr_Country_text+"&pers_addr_country_text="+pers_addr_country_text;
		formvalues+="&per_home_addr_country_text="+per_home_addr_country_text;
		 $.post('changeaddress_details_CIVaction?' + key + "=" + value,formvalues, function(data){
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
		
	var civ_id=$("#civ_id").val();	
	if($("#marital_status_p").text() == '8'){
		$("#spouse_addressMaindiv").show();
	}
	else{
		$("#spouse_addressMaindiv").hide();
	}
	$.post('address_details_GetCivData?' + key + "=" + value,{civ_id:civ_id }, function(j){
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
			$("#retire_country").val(j[0].retire_country);
			$("#retire_village").val(j[0].retire_village);
			$("#retire_tehsil").val(j[0].retire_tehsil);
			$("#retire_district").val(j[0].retire_district);
			$("#retire_state").val(j[0].retire_state);
			$("#retire_pin").val(j[0].retire_pin);
			$("#retire_rail").val(j[0].retire_near_railway_station);
			var retire= j[0].retire_rural_urban_semi;
	            if(retire == "rural"){
	                    $("#retire_rural").prop("checked", true);
	        } 
	            if(retire == "urban")
	            {
	                    $("#retire_urban").prop("checked", true);
	             }
	            if(retire == "semi_urban")
	            {
	                    $("#retire_semi_urban").prop("checked", true);
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
         fn_pers_addr_Country();
           
            $("input[name=pers_addr_rural_urban]").attr('checked', false);
    }
}


function fn_pre_domicile_state() {
	var text = $("#pre_domicile_state option:selected").text();
	
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
	
	if(text == "OTHERS"){
		$("#per_home_addr_country_other_hid").show();
	}
	else{
		$("#per_home_addr_country_other_hid").hide();
		$("#per_home_addr_country_others").val("");
	}
	var obj=document.getElementById('per_home_addr_country');
	FnTehsilDisableEnable(obj,'per_home_addr_tehsil','per_home_addr_tehsil_other_hid','per_home_addr_tehsil_others');
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
	
	if(text == "OTHERS"){
		$("#add_other_id").show();
	}
	else{
		$("#add_other_id").hide();
		$("#pre_country_other").val("");
	}
	
	var obj=document.getElementById('pre_country');
	FnTehsilDisableEnable(obj,'pre_domicile_tesil','add_other_te','pre_domicile_tesil_other');
	
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
	
	if(text == "OTHERS"){
		$("#pers_addr_country_other_hid").show();
	}
	else{
		$("#pers_addr_country_other_hid").hide();
		$("#pers_addr_country_other").val("");
	}
	var obj=document.getElementById('pers_addr_country');
	FnTehsilDisableEnable(obj,'pers_addr_tehsil','pers_addr_tehsil_other_hid','pers_addr_tehsil_other');
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
	var obj=document.getElementById('spouse_addr_Country');
	FnTehsilDisableEnable(obj,'spouse_addr_tehsil','spouse_addr_tehsil_hid','spouse_addr_tehsil_other');
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
	        	  alert("Please Enter Approved Station HQ SUS No");
	        	  document.getElementById("Stn_HQ_sus_no").value="";
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
</script>
<script>
//*************************************Start Nok *****************************//

function fn_nok_Country() {
	
	var text = $("#nok_country option:selected").text();
	
	if(text == "OTHERS"){
		$("#nok_other_id").show();
	}
	else{
		$("#nok_other_id").hide();
		$("#ctry_other").val("");
	}	

	var obj=document.getElementById('nok_country');
	FnTehsilDisableEnable(obj,'nok_tehsil','nok_other_te','nok_tehsil_other');
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

	$("input#authority").val($("input#authority").val().trim());
	if($("input#date_authority").val() != "DD/MM/YYYY" && $("input#date_authority").val() != ""){
	if(getformatedDate($("input#enroll_date").val()) >= getformatedDate($("#date_authority").val())) {
		alert("Authority Date should be Greater than Enrollment Date");
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
  var text1 = $("#nok_country option:selected").text().toUpperCase();
	
	if((text1 == "OTHERS" || text1 == "OTHER") && $("input#ctry_other").val().trim()==""){
		alert("Please Enter  Country Other");
		$("input#ctry_other").focus();
		return false;
	}
	
	if(text1 == "OTHERS" || text1 == "OTHER"){
		if(lengthValidation($("input#ctry_other").val().trim(),auth_length)){
			alert("Country Other Length Should be Less or Equal To 100")
			$("#ctry_other").focus();
			return false;
		}
	}

	
	if ($("select#nok_state").val() == "0") {
		alert("Please Select  Nok State/Province");
		$("select#nok_state").focus();
		return false;
	}
  var text2 = $("#nok_state option:selected").text().toUpperCase();
	
	if((text2 == "OTHERS"|| text2 == "OTHER") && $("input#st_other").val().trim()==""){
		alert("Please Enter State/Province Other");
		$("input#st_other").focus();
		return false;
	}
	
	if(text2 == "OTHERS" || text2 == "OTHER"){
		if(lengthValidation($("input#st_other").val().trim(),auth_length)){
			alert("State/Province Other Length Should be Less or Equal To 100")
			$("#st_other").focus();
			return false;
		}
	}
	
	if ($("select#nok_district").val() == "0") {
		alert("Please Select Nok District");
		$("select#nok_district").focus();
		return false;
	}
	 var text3 = $("#nok_district option:selected").text().toUpperCase();
		
		if((text3 == "OTHERS"|| text3 == "OTHER") && $("input#dist_other").val().trim()==""){
			alert("Please Enter District Other");
			$("input#dist_other").focus();
			return false;
		}
		
		if(text3 == "OTHERS" || text3 == "OTHER"){
			if(lengthValidation($("input#dist_other").val().trim(),auth_length)){
				alert("District Other Length Should be Less or Equal To 100")
				$("#dist_other").focus();
				return false;
			}
		}
		
		
		var nok_country_text = $("#nok_country option:selected").text().toUpperCase();
		if(nok_country_text!="BHUTAN"){
			if ($("select#nok_tehsil").val() == "0" ) {
				alert("Please Select Tehsil/Municipality");
				$("select#nok_tehsil").focus();
				return false;
			}
			var text5 = $("#nok_tehsil option:selected").text().toUpperCase();
			
			if((text5 == "OTHERS"|| text5 == "OTHER") && $("input#nok_tehsil_other").val().trim()==""){
				alert("Please Enter Tehsil/Municipality Other");
				$("input#nok_tehsil_other").focus();
				return false;
			}
			
			if(text5 == "OTHERS" || text5 == "OTHER"){
				if(lengthValidation($("input#nok_tehsil_other").val().trim(),auth_length)){
					alert("Tehsil/Municipality Other Length Should be Less or Equal To 100")
					$("#nok_tehsil_other").focus();
					return false;
				}
			}
		}
	if ($("input#nok_village").val().trim()=="") {
		alert("Please Enter  Village/Town/City");
		$("input#nok_village").focus();
		return false;
	}
	if ($("input#nok_pin").val().trim()==""  || $("input#nok_pin").val().trim().length<6 || $("input#nok_pin").val().trim().length>6) {
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
   
	var civ_id =$("#civ_id").val();
	var nok_id=$("#nok_id").val();		
		formvalues+="&nok_id="+nok_id+"&civ_id="+civ_id;	
		formvalues+="&nok_country_text="+nok_country_text;
		$.post('nok_details_action_civ?' + key + "=" + value,formvalues, function(data){
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
	var civ_id=$("#civ_id").val();	
	$.post('nok_details_GetData_Civ?' + key + "=" + value,{civ_id:civ_id }, function(j){
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

	 //*************************************END Nok *****************************//
</script>
<script>
<!-- START DESIGNATION  -->
function validate_save_designation_details(){

	 if ($("#des_authority").val().trim() == "") {
			alert("Please Enter designation Authority");
			$("#des_authority").focus();
			return false;
		}
	 
		if ($("#date_authority_des").val() == "DD/MM/YYYY" || $("#date_authority_des").val().trim()=="") {
			alert("Please Select Date of Authority");
			$("#date_authority_des").focus();
			return false;
		} 
	
  if ($("#dt_of_designation").val() == "DD/MM/YYYY" || $("#dt_of_designation").val().trim()=="") {
		alert("Please Select Date of Designation");
		$("#dt_of_designation").focus();
		return false;
	} 
		
   var formvalues=$("#form_designation_details_form").serialize();
   
	var civ_id =$("#civ_id").val();
	var des_id=$("#des_id").val();		
		formvalues+="&des_id="+des_id+"&civ_id="+civ_id;	
		$.post('designation_details_action_civ?' + key + "=" + value,formvalues, function(data){

			 if(data=="update")
	        	  alert("Data Updated Successfully");
	          else if(parseInt(data)>0)
	          {
	        	  $("#des_id").val(data);	
	        	  alert("Data Saved Successfully")
	          }
	          else
	        	  alert(data)
	 	  		}).fail(function(xhr, textStatus, errorThrown) 
	 	  			{
	 	  			alert("fail to fetch")
	 	  		
	  		});
}
function get_changedesignation_details(){
	var civ_id=$("#civ_id").val();	
	$.post('designation_details_GetData_Civ?' + key + "=" + value,{civ_id:civ_id }, function(j){
		var v=j.length;
			if(v!=0){
				
				$("#des_authority").val(j[0].authority);
				$('#date_authority_des').val(ParseDateColumncommission(j[0].date_authority)); 
				$('#designation').val(j[0].designation); 
				$('#dt_of_designation').val(ParseDateColumncommission(j[0].designation_date)); 
		       
					
					
				$("#des_id").val(j[0].id);
			}
		  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
			});
	} 

</script>
  <script>
//***************** START QUALIFICATION DETAILS************************//
//qualification
  
 
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
  		$("#class_other").val('');
  	}
  }
 
	 function qualification_save_fn(qs) {
			var dateofBirthyear =$("#dob").text().split('-')[2];
			var currentdate = new Date();
			var currentyear = currentdate.getFullYear();
			var type = $('#quali_type').val();
			var examination_pass = $('#quali').val();
			var exam_other_qua=$('#exam_other').val();
		  	var degree_other=$('#quali_deg_other').val();
			var spec_other=$('#quali_spec_other').val();
			var class_other_qua=$('#class_other').val();
			
			var passing_year = $('#yrOfPass').val();
			var degree = $('#quali_degree').val();
			var specialization = $('#specialization').val();
			var div_class = $('#div_class').val();
			var institute = $('#institute_place').val();
			var qualification_ch_id = $('#qualification_ch_id').val();
			
			var civ_id = $('#civ_id').val();
			 var qual_authority=$('#qualification_authority').val();
				var qual_doa=$('#qualification_date_of_authority').val();
				 if(qual_authority == null || qual_authority.trim()==""){ 
						alert("Please Enter Authority");
						$("input#qualification_authority").focus();
						return false;
					}
				 if(lengthValidation($("input#qualification_authority").val().trim(),auth_length)){
						alert("Authority Length Should be Less or Equal To 100");
						$("#qualification_authority").focus();
						return false;

					}
				 
				 if(qual_doa == null || qual_doa.trim()=="" || qual_doa == "DD/MM/YYYY"){ 
						alert("Please Enter Date of Authority");
						$("input#qualification_date_of_authority").focus();
						return false;
					} 
				  if(getformatedDate($("input#enroll_date").val()) > getformatedDate($("#qualification_date_of_authority").val())) {
	                    alert("Authority Date should be Greater than Enrollment Date");
	                	$("input#qualification_date_of_authority").focus();
	                    return false;
	               }
				  
			var subjectvar = $('input[name="multisub"]:checkbox:checked').map(function() {
				return this.value;
			}).get();
			var subject = subjectvar.join(",");
			

			if(type == null || type == "0") {
				alert("Please Select Qualification Type");
				$("#quali_type").focus();
				return false;
			}
		
			if(examination_pass == null || examination_pass == "0") {
				alert("Please Select Examination Pass");
				$("#quali").focus();
				return false;
			}
			var exam_other=$("#quali option:selected").text().toUpperCase();
			  if(exam_other==other || exam_other=="OTHER") {
		     	 if(exam_other_qua == null || exam_other_qua.trim()==""){ 	 
		     		alert( "Please Enter Examination Other ");
					
					$("input#exam_other").focus();
					return false;
				   }
		     	if(lengthValidation($("input#exam_other").val().trim(),auth_length)){
					alert("Examination Other Length Should be Less or Equal To 100")
					$("#exam_other").focus();
					return false;
				}
		      }
			
			if(degree == null || degree == "0") {
				alert("Please Select The Degree Acquired");
				$("#quali_degree").focus();
				return false;
			}
			var deg_other=$("#quali_degree option:selected").text().toUpperCase();
			  if(deg_other==other || deg_other=="OTHER") {
			      	 if(degree_other == null || degree_other.trim()==""){ 	       
			      		alert( "Please Enter Degree Other ");
			 			$("input#quali_deg_other").focus();
			 			return false;
			 		   }
			     	if(lengthValidation($("input#quali_deg_other").val().trim(),auth_length)){
						alert("Degree Other Length Should be Less or Equal To 100")
						$("#quali_deg_other").focus();
						return false;
					}
			       }
			if(specialization == null || specialization == "0") {
				alert("Please Select The Specialization");
				$("#specialization").focus();
				return false;
			}
			
		

		
		
	      var spe_other=$("#specialization option:selected").text().toUpperCase();
	    
	      if(spe_other==other || spe_other=="OTHER") {
	       	 if(spec_other == null || spec_other.trim()==""){ 	 
	        
	  			alert( "Please Enter Specialization Other ");
	  			$("input#quali_spec_other").focus();
	  			return false;
	  		   }
	       	if(lengthValidation($("input#quali_spec_other").val().trim(),auth_length)){
				alert("Specialization Other Length Should be Less or Equal To 100")
				$("#quali_spec_other").focus();
				return false;
			}
	        }
	      
	      
	      
	      
		if(passing_year.trim()=="") {
			alert("Please Enter Year of Passing");
			$("input#yrOfPass").focus();
			return false;
		}
		if(passing_year.trim() != "") {
			if(parseInt(passing_year) <= parseInt(dateofBirthyear) || parseInt(passing_year) > parseInt(currentyear)) {
				alert("Please Enter Valid Year of Passing");
				$("input#yrOfPass").focus();
				return false;
			}
		}
		if(div_class == "0") {
			alert("Please Select Div/Grade/PCT(%)");
			$("#div_class").focus();
			return false;
		}
		var div_other=$("#div_class option:selected").text().toUpperCase();
		  if(div_other==other || div_other=="OTHER") {
	    	 if(class_other_qua == null || class_other_qua.trim()==""){ 	 
	     
				alert( "Please Enter Div/Grade/PCT(%) Other ");
				$("input#class_other").focus();
				return false;
			   }
	    	 
	    		if(lengthValidation($("input#class_other").val().trim(),auth_length)){
					alert("Div/Grade/PCT(%) Other Length Should be Less or Equal To 100")
					$("#class_other").focus();
					return false;
				}
	     }
		if(subject.trim()=="") {
			alert("Please Select The Subject");
			$("select#sub_quali").focus();
			return false;
		}
		if(institute.trim()=="") {
			alert("Please Enter Institute & place");
			$("#institute_place").focus();
			return false;
		}
			$.post('update_qualification_Civ_action?' + key + "=" + value, {
				type: type,
				examination_pass: examination_pass,
				passing_year: passing_year,
				div_class: div_class,
				subject: subject,
				institute: institute,
				qualification_ch_id: qualification_ch_id,				
				degree: degree,
				specialization: specialization,
				civ_id: civ_id,exam_other_qua:exam_other_qua,class_other_qua:class_other_qua,
				degree_other:degree_other,spec_other:spec_other,
				dateofBirthyear: dateofBirthyear,
				qual_authority:qual_authority,
				qual_doa:qual_doa
			}, function(data) {
				if(parseInt(data) > 0) {
					alert("Data Saved Successfully")
				} else alert(data)
				$('#quali').val('0');		
				$('#yrOfPass').val('');
				$('#quali_degree').val('0');
				$('#specialization').val('0');
				$('#div_class').val('0');
				$('#institute_place').val('');
				$('#qualification_ch_id').val('0');
				$("input[type=checkbox][name='multisub']").prop("checked", false);
				
				$("#sub_quali option:first").text('Select Subjects');
				$("#quali_sub_list").empty();
				var typethisT = document.getElementById('quali_type');
				fn_qualification_typeChange(typethisT,'quali','quali_degree','specialization');
				var qualithisT = document.getElementById('quali');
				fn_ExaminationTypeChange(qualithisT,'quali_degree','specialization');
			
				fn_other_class();
				getQualifications();
			}).fail(function(xhr, textStatus, errorThrown) {
				alert("fail to fetch")
			});
		}
	 function getQualifications() {
			var civ_id = $('#civ_id').val();
			var i = 0;
			$.post('getQualificationCivData?' + key + "=" + value, {
				civ_id: civ_id
			}, function(j) {
				var v = j.length;
				$('#qualificationtbody').empty();
				if(v != 0) {
					
					for(i; i < v; i++) {
						qu = i + 1;
						var examother="--";
						var classother="--";
						var deg_other="--";
						var spec_other="--";
						var exampass = "--";				
						var deg_name = "--";
						var specialization = "--";
						if(j[i].exp_name != null) exampass = j[i].exp_name;
					
						if(j[i].d_name != null) deg_name = j[i].d_name;
						if(j[i].spce_name != null) specialization = j[i].spce_name;
						
						if(j[i].exam_other!=null){
							examother=j[i].exam_other;
							exampass=j[i].exam_other;
						}
				  	    
				  	  if(j[i].degree_other!=null){
				  		deg_name=j[i].degree_other;
				  		deg_other=j[i].degree_other;
				  	  }
				  	   if(j[i].specialization_other!=null)
				  		   {
				  		 specialization=j[i].specialization_other;
				  		spec_other=j[i].specialization_other;
				  		   }
				  	    

						var divclass = $('#div_class option[value="' + j[i].div_class + '"]').text();
						 if(j[i].class_other!=null){
					  	    	classother=j[i].class_other;
					  	    	divclass=j[i].class_other;
						 }
						var subjectslist = j[i].subject.split(',');
						var subjects = "";
						for(k = 0; k < subjectslist.length; k++) {
							if(k != 0) subjects += ",";
							subjects += $("input[type=checkbox][name='multisub'][value='" + subjectslist[k] + "']").parent().text();
						}
						var Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Qualification ?') ){editQualificationData(" + j[i].id + "," + j[i].type + ",'" +  j[i].examination_pass + "'," + j[i].passing_year + ",'" + j[i].degree + "','" + j[i].specialization + "'," + j[i].div_class + ",'" + j[i].subject + "','" + j[i].institute + "','" + examother + "','" + classother + "','" + deg_other + "','" + spec_other + "','" + j[i].authority + "','" + ParseDateColumncommission(j[i].date_of_authority) + "')}else{ return false;}\"";
						f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
						var Delete = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Qualification ?') ){deleteQualificationData(" + j[i].id + ")}else{ return false;}\"";
						f1 = "<i class='action_icons action_delete' " + Delete + " title='Delete Data'></i>";
						var action = f + f1;
						$("table#qualificationtable").append('<tr>' + '<td style="font-size: 15px;text-align: center ;width: 10%;">' + qu + '</td> ' +  '<td style="font-size: 15px;text-align: center ;width: 10%;">' + j[i].authority + '</td> ' +  '<td style="font-size: 15px;text-align: center ;width: 10%;">' + convertDateFormate(j[i].date_of_authority) + '</td> ' + '<td style="font-size: 15px;text-align: center">' + j[i].label + '</td>	' + '<td style="font-size: 15px;text-align: center">' + exampass + '</td> ' 
						 + '<td style="font-size: 15px;text-align: center">' + deg_name + '</td>' +   '<td style="font-size: 15px;text-align: center">' + specialization + '</td>' +
						'<td style="font-size: 15px;text-align: center">' + j[i].passing_year + '</td>' + '<td style="font-size: 15px;text-align: center">' + subjects + '</td>' + '<td style="font-size: 15px;text-align: center">' 
						+ divclass + '</td>'  + '<td style="font-size: 15px;text-align: center">' + j[i].institute + '</td>' 
						 + '<td class="hide-action" style="font-size: 15px; text-align: center;">' + action + '</td>' + '</tr>');
					}
				} else {
					$("table#qualificationtable").append('<tr>' + '<td style="font-size: 15px; text-align: center; color: red;" colspan="12">Data Not Available</td>' + '</tr>');
				}
			});
		}

		function editQualificationData(id, type, exampass, passing_year, degree,  specialization, div_class, subject, institute,examother,classother,deg_other,spec_other,authority,doa) {
			console.log(exampass+" "+degree+" "+specialization);
			$("#qualification_authority").val(authority);
			$("#qualification_date_of_authority").val(doa);
			$('#yrOfPass').val(passing_year);
			$('#div_class').val(div_class);
			$('#institute_place').val(institute);
			$('#qualification_ch_id').val(id);
			$("input[type=checkbox][name='multisub']").prop("checked", false);
			var subjectslist = subject.split(',');
			for(k = 0; k < subjectslist.length; k++) {
				$("input[type=checkbox][name='multisub'][value='" + subjectslist[k] + "']").prop("checked", true);
				$("#sub_quali option:first").text('Subjects(' + subjectslist.length + ')');
			}
			$('#quali_sub_list').empty();
			$("input[type='checkbox'][name='multisub']").each(function() {
				if(this.checked) {
					$('#quali_sub_list').append("<span class='subspan'>" + this.parentElement.innerText + "<i class='fa fa-times' style='margin: 5px;  font-size: 15px;' onclick='removeSubFn(" + this.value + ")'></i><span> <br>");
				}
			});
			$('#quali_type').val(type);
			var typethisT = document.getElementById('quali_type');
			fn_qualification_typeChange(typethisT,'quali','quali_degree','specialization');
			if(exampass != '--') {
				
				$('#quali').val(exampass);
				var qualithisT = document.getElementById('quali');
				fn_ExaminationTypeChange(qualithisT,'quali_degree','specialization');
			}
			
			 if(examother!='--')
			  	$('#exam_other').val(examother);
			 if(classother!='--')
				 $('#class_other').val(classother);
			 if(deg_other!='--')
				 $('#quali_deg_other').val(deg_other);
			
			 
			
			  
			
			if(degree != '--') {
				$('#quali_degree').val(degree);
				
			}
			if(specialization != '--') $('#specialization').val(specialization);
			 if(spec_other!='--')
				 $('#quali_spec_other').val(spec_other);
			 fn_other_exam();
			 fn_other_class();
			 fn_otherShowHide(document.getElementById('quali_degree'),'other_div_qualiDeg','quali_deg_other');
			 fn_otherShowHide(document.getElementById('specialization'),'other_div_qualiSpec','quali_spec_other');
		}

		function deleteQualificationData(id) {
			var qualification_ch_id = id;
			$.post('qualification_delete_CIVaction?' + key + "=" + value, {
				qualification_ch_id: qualification_ch_id
			}, function(data) {
				if(data == '1') {
					getQualifications();
					alert("Data Deleted Successfully");
				} else {
					alert("Data not Deleted ");
				}
			}).fail(function(xhr, textStatus, errorThrown) {
				alert("fail to fetch")
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
	  $.post('update_offr_promo_exam_delete_action_jco?' + key + "=" + value, {promo_exam_ch_id:promo_exam_ch_id }, function(data){ 
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
							 alert("Data Deleted Successfully");
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
	var dateofEnrollyear = $("#enroll_date").val().split('/')[2];
	var dateofEnrollMonth = $("#enroll_date").val().split('/')[1];
	var dateofEnrollMonthY=dateofEnrollyear+"-"+dateofEnrollMonth+"-01";
	
	
	var civ_id=$('#civ_id').val();
	var promo_exam_authority=$('#promo_exam_authority').val();
	var promo_exam_doa=$('#promo_exam_date_of_authority').val();
	if (promo_exam_authority.trim()=="") {
		alert("Please Enter Authority");
		$("input#promo_exam_authority").focus();
		return false;
	}
	if(lengthValidation($("input#promo_exam_authority").val().trim(),auth_length)){
		alert("Authority Length Should be Less or Equal To 100");
		$("#promo_exam_authority").focus();
		return false;

	}
	 if (promo_exam_doa.trim()=="" || promo_exam_doa == "DD/MM/YYYY") {
		alert("Please Enter Date of Authority");
		$("input#promo_exam_date_of_authority").focus();
		return false;
	}
	 
	 if(getformatedDate($("input#enroll_date").val()) > getformatedDate($("#promo_exam_date_of_authority").val())) {
         alert("Authority Date should be Greater than Enrollment Date");
         $("input#promo_exam_date_of_authority").focus();
         return false;
    }
	 
 	if (promo_exam == "0") {
 		alert("Please select Exam");
 		$("select#promo_exam"+ps).focus();
 		return false;
 	}	
 	var country_sel = $("#promo_exam"+ps+" option:selected").text().toUpperCase();
	if ((country_sel == "OTHERS"|| country_sel == "OTHER") && e_ot.trim()=="") {
		alert("Please Enter Other Exam");
		$('#exam_other'+ps).focus();
		return false;
	} 

	if(country_sel == "OTHERS" || country_sel == "OTHER"){
		if(lengthValidation($("input#exam_other").val().trim(),auth_length)){
			alert("Exam Other Length Should be Less or Equal To 100")
			$("#exam_other").focus();
			return false;
		}
	}
	
	if (promo_exam_dop.trim()=="") {
		alert("Please Enter Date of Passing");
		$("#promo_exam_dop"+ps).focus();
		return false;
	}
	
	if( Date.parse(dateofEnrollMonthY)> Date.parse((promo_exam_dop+"-01"))){
		  alert("Date of Passing should be Greater Or Equal To Enrollment Date");
		  $("#promo_exam_dop"+ps).focus();
			return false;
	}
 
	  $.post('update_offr_promo_exam_detail_action_jco?' + key + "=" + value, {exam_other_select:country_sel,promo_exam:promo_exam,e_ot:e_ot,promo_exam_dop:promo_exam_dop,promo_exam_ch_id:promo_exam_ch_id,civ_id:civ_id,promo_exam_authority:promo_exam_authority,promo_exam_doa:promo_exam_doa }, function(data){
	          if(data=="update")
	        	  alert("Data Updated Successfully");
	          else if(parseInt(data)>0){
	        	 $('#promo_exam_ch_id'+ps).val(data);
	        	 $("#promo_exam_add"+ps).show();
	        	 $("#promo_exam_remove"+ps).show();
	        	  alert("Data Saved Successfully")
	          }
	          else
	        	  alert(data)
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});
}



function get_promo_exam_details(){
	  
	 var civ_id=$('#civ_id').val(); 
	 var i=0;
	  $.post('update_offr_promo_exam_getData_jco?' + key + "=" + value, {civ_id:civ_id}, function(j){
			var v=j.length;
			if(v!=0){
		xpro=0;
		for(i;i<v;i++){
			
			xpro=xpro+1;
				if(xpro==1){
					$('#promo_examtbody').empty(); 
				}
				var dauth=ParseDateColumncommission(j[i].date_of_authority);
				$("table#promo_exam_table").append('<tr id="tr_promo_exam'+xpro+'">'
						+'<td class="proex_srno" style="width: 2%;">'+xpro+'</td>'
						+'<td style="width: 10%;">'
						+'<select name="promo_exam'+xpro+'" id="promo_exam'+xpro+'" onchange="onPro_exam('+xpro+')" class="form-control-sm form-control">'
						+'<option value="0">--Select--</option>'
						+'<c:forEach var="item" items="${getExamList}" varStatus="num">'
						+'<option value="${item[0]}" name="${item[1]}">${item[1]}</option> '
						+'</c:forEach> '
						+'</select></td>'
						+'<td style="width: 10%;">'
						+'<input type="text" id="exam_other'+xpro+'" name="exam_other'+xpro+'" value="'+j[i].exam_other+'" class="form-control autocomplete" autocomplete="off" />'
						+'</td>'
						+'<td style="width: 10%;">'
						+'<input type="month" id="promo_exam_dop'+xpro+'" name="promo_exam_dop'+xpro+'" value="'+j[i].date_of_passing+'" class="form-control autocomplete" autocomplete="off" />'
						+'</td>'
						+'<td style="display:none;"><input type="text" id="promo_exam_ch_id'+xpro+'" name="promo_exam_ch_id'+xpro+'"  value="'+j[i].id+'" class="form-control autocomplete" autocomplete="off" ></td>'
						+'<td style="width: 10%;">'
						+'<a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "promo_exam_save'+xpro+'" onclick="promo_exam_save_fn('+xpro+');" ><i class="fa fa-save"></i></a>'
						+'<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "promo_exam_add'+xpro+'" onclick="promo_exam_add_fn('+xpro+');" ><i class="fa fa-plus"></i></a>'
						+'<a  class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "promo_exam_remove'+xpro+'" onclick="promo_exam_remove_fn('+xpro+');"><i class="fa fa-trash"></i></a> ' 
						+'</td></tr>');
				 $('#promo_exam'+xpro).val(j[i].exam );
				 $('#promo_exam_authority').val(j[i].authority );
				 $('#promo_exam_date_of_authority').val(dauth);
				 onPro_exam(xpro);
			
		}
		if(xpro!=0){
			proex=xpro;
			proex_srno=xpro;
			$("#promo_exam_add"+xpro).show();
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
			+'<input type="text" id="army_course_name'+armyc+'" name="army_course_name'+armyc+'" class="form-control autocomplete" onkeyup="AutoCompleteCourse(this,'+armyc+',0);" autocomplete="off" maxlength="50">'
			+'</td>'
			+'<td style="width: 10%;">'
			+'<input type="text" id="army_course_abbreviation'+armyc+'" name="army_course_abbreviation'+armyc+'" class="form-control autocomplete" onkeyup="AutoCompleteCourse(this,'+armyc+',1);" autocomplete="off" maxlength="50">'
			+'</td>'
			
			+'<td style="width: 10%;">'	
			+'<select  id="army_course_institute'+armyc+'" name="army_course_institute'+armyc+'" class="form-control autocomplete" onchange="onArmyCourseInstiChange('+armyc+')" >'
			+'<option value="0">--Select--</option>'
			+'<c:forEach var="item" items="${getArmyCourseInstitute}" varStatus="num">'
			+'<option value="${item[0]}" name="${item[1]}">${item[1]}</option> '
			+'</c:forEach></select></td>'
			+'<td style="width: 10%; " > <input type="text" id="army_course_institute_ot'+armyc+'" name="army_course_institute_ot'+armyc+'" onkeypress="return onlyAlphabets(event);"  readonly  class="form-control autocomplete" autocomplete="off" ></td>'
			  
			+'<td style="width: 10%;">'	
			+'<select  id="army_course_div_grade'+armyc+'" name="army_course_div_grade'+armyc+'" class="form-control autocomplete" onchange="onDivGrade('+armyc+')" >'
			+'<option value="0">--Select--</option>'
			+'<c:forEach var="item" items="${getDivclass}" varStatus="num">'
			+'<option value="${item[0]}" name="${item[1]}">${item[1]}</option> '
			+'</c:forEach></select></td>'
			
			+'<td style="width: 10%; " > <input type="text" id="ar_course_div_ot'+armyc+'" name="ar_course_div_ot'+armyc+'" onkeypress="return /[0-9a-zA-Z]/i.test(event.key)" readonly  class="form-control autocomplete" autocomplete="off" ></td>'
			  
			+'<td style="width: 10%;">'
			+'<select  id="army_course_type'+armyc+'" name="army_course_type'+armyc+'" class="form-control autocomplete"  >'
			+'<option value="0">--Select--</option>'
			+'<c:forEach var="item" items="${getCourseTypeList}" varStatus="num">'
			+'<option value="${item[0]}" name="${item[1]}">${item[1]}</option> '
			+'</c:forEach> </select>	</td>'
			
			+'<td style="width: 10%;display: none"> <input type="text" id="course_type_ot'+armyc+'" name="course_type_ot'+armyc+'" onkeypress="return /[0-9a-zA-Z]/i.test(event.key)" readonly  class="form-control autocomplete" autocomplete="off" ></td>'
			
			
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
	  $.post('update_JCO_army_course_delete_action?' + key + "=" + value, {army_course_ch_id:army_course_ch_id }, function(data){ 
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
							 alert("Data Deleted Successfully");
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
	var army_course_abbr=$('#army_course_abbreviation'+ps).val();
	var army_course_institue=$('#army_course_institute'+ps).val();
	var army_course_institue_ot=$('#army_course_institute_ot'+ps).val();
	
	var army_course_div_grade=$('#army_course_div_grade'+ps).val();
	var ar_course_div_ot=$('#ar_course_div_ot'+ps).val();
	var army_course_type=$('#army_course_type'+ps).val();
 	var course_type_ot=$('#course_type_ot'+ps).val();
	
	
	var army_course_start_date=$('#army_course_start_date'+ps).val();
	var army_course_date_of_completion=$('#army_course_date_of_completion'+ps).val();
	var army_course_ch_id=$('#army_course_ch_id'+ps).val();
	
	var civ_id=$('#civ_id').val();
	var army_course_authority=$('#army_course_authority').val();
	var army_course_doa=$('#army_course_date_of_authority').val();
	
	var enroll_date=$('#enroll_date').val();
	
	if (army_course_authority.trim()=="") {
		alert("Please Enter Authority");
		$("input#army_course_authority").focus();
		return false;
	}
	if(lengthValidation($("input#army_course_authority").val().trim(),auth_length)){
		alert("Authority Length Should be Less or Equal To 100");
		$("#army_course_authority").focus();
		return false;

	}
	if (army_course_doa.trim()=="" || army_course_doa == "DD/MM/YYYY") {
		alert("Please Enter Date of Authority");
		$("input#army_course_date_of_authority").focus();
		return false;
	}
	if(getformatedDate($("input#enroll_date").val()) >= getformatedDate($("#army_course_date_of_authority").val())) {
		alert("Authority Date should be Greater than Enrollment Date");
		$("input#army_course_date_of_authority").focus();
		return false;
  }
	
	if (army_course_name.trim()=="") {
		alert("Please Enter Course Name");
		$("#army_course_name"+ps).focus();
		return false;
	}
	if (army_course_abbr.trim()=="") {
		alert("Please Enter Course Abbreviation");
		$("#army_course_abbreviation"+ps).focus();
		return false;
	}
	if (army_course_institue == "0") {
		alert("Please Select Course Institute");
		$("#army_course_institute"+ps).focus();
		return false;
	}
	
	var army_course_institueV = $("#army_course_institute"+ps+" option:selected").text().toUpperCase();
	if (army_course_institueV == "OTHERS" && army_course_institue_ot.trim()=="") {
		alert("Please Enter Other Course Institute");
		$('#army_course_institute_ot'+ps).focus();
		return false;
	}
	
	if(army_course_institueV == "OTHERS" || army_course_institueV == "OTHER"){
		if(lengthValidation($("input#army_course_institute_ot"+ps).val().trim(),auth_length)){
			alert("Course Institute Other Length Should be Less or Equal To 100")
			$("#army_course_institute_ot"+ps).focus();
			return false;
		}
	}
	
	if (army_course_div_grade == "0") {
		alert("Please select Div/Grade");
		$("#army_course_div_grade"+ps).focus();
		return false;
	}	
	var army_course_div_gradeV = $("#army_course_div_grade"+ps+" option:selected").text().toUpperCase();
	if ((army_course_div_gradeV == "OTHERS" || army_course_div_gradeV == "OTHER") && ar_course_div_ot.trim()=="") {
		alert("Please Enter Other Div/Grade");
		$('#ar_course_div_ot'+ps).focus();
		return false;
	}
	if(army_course_div_gradeV == "OTHERS" || army_course_div_gradeV == "OTHER"){
		if(lengthValidation($("input#ar_course_div_ot"+ps).val().trim(),auth_length)){
			alert("Div/Grade Other Length Should be Less or Equal To 100")
			$("#ar_course_div_ot"+ps).focus();
			return false;
		}
	}
	
	if (army_course_type == "0") {
		alert("Please select Course Type");
		$("#army_course_type"+ps).focus();
		return false;
	}	






	if (army_course_authority.trim()=="") {
		alert("Please Enter Authority");
		$("#army_course_authority").focus();
		return false;
	}
	
	if (army_course_start_date.trim()=="" || army_course_start_date == "DD/MM/YYYY") {
		alert("Please Enter Start Date");
		$("#army_course_start_date"+ps).focus();
		return false;
	}
	if (army_course_date_of_completion.trim()=="" || army_course_date_of_completion == "DD/MM/YYYY") {
		alert("Please Enter Date of Completion");
		$("#army_course_date_of_completion"+ps).focus();
		return false;
 	}
      if(getformatedDate(army_course_start_date) > getformatedDate(army_course_date_of_completion)) {
		alert("Completion Date should always be greater than Start Date");
		$("#army_course_date_of_completion"+ps).focus();
		return false;
	}
	  $.post('update_JCO_army_course_detail_action?' + key + "=" + value, 
			  {enroll_date:enroll_date,army_course_name:army_course_name,army_course_div_grade:army_course_div_grade,ar_course_div_ot:ar_course_div_ot,
		  		army_course_type:army_course_type,course_type_ot:course_type_ot,army_course_start_date:army_course_start_date,
		  		army_course_date_of_completion:army_course_date_of_completion,army_course_ch_id:army_course_ch_id,
		  		civ_id:civ_id,army_course_authority:army_course_authority,army_course_doa:army_course_doa,army_course_institueV:army_course_institueV,army_course_abbr:army_course_abbr,army_course_institue:army_course_institue,army_course_institue_ot:army_course_institue_ot }, function(data){
	          if(data=="update")
	        	  alert("Data Updated Successfully");
	          else if(parseInt(data)>0){
	        	 $('#army_course_ch_id'+ps).val(data);
	        	 $("#army_course_add"+ps).show();
	        	 $("#army_course_remove"+ps).show();
	        	  alert("Data Saved Successfully")
	          }
	          else
	        	  alert(data)
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});
}



function get_army_course_details(){
	  
	 var civ_id=$('#civ_id').val(); 
	 var i=0;
	  $.post('update_JCO_army_course_getData?' + key + "=" + value, {civ_id:civ_id}, function(j){
			var v=j.length;
			if(v!=0){
		xaco=0;
		for(i;i<v;i++){
			
			xaco=xaco+1;
				if(xaco==1){
					$('#army_coursetbody').empty(); 
				}
				var dauth=ParseDateColumncommission(j[i].date_of_authority);
				var start_date=ParseDateColumncommission(j[i].start_date);
				var date_of_completion=ParseDateColumncommission(j[i].date_of_completion);
				$("table#army_course_table").append('<tr id="tr_army_course'+xaco+'">'
						+'<td class="army_course_srno" style="width: 2%;">'+xaco+'</td>'
						+'<td style="width: 10%;">'
						+'<input type="text" id="army_course_name'+xaco+'" name="army_course_name'+xaco+'" value="'+j[i].course_name+'" onkeyup="AutoCompleteCourse(this,'+xaco+',0);" class="form-control autocomplete" autocomplete="off" maxlength="20">'
						+'</td>'
						+'<td style="width: 10%;">'
						+'<input type="text" id="army_course_abbreviation'+xaco+'" name="army_course_abbreviation'+xaco+'" value="'+j[i].course_abbreviation+'" class="form-control autocomplete" onkeyup="AutoCompleteCourse(this,'+xaco+',1);" autocomplete="off" maxlength="20">'
						+'</td>'
						+'<td style="width: 10%;">'	
						+'<select  id="army_course_institute'+xaco+'" name="army_course_institute'+xaco+'" class="form-control autocomplete" onchange="onArmyCourseInstiChange('+xaco+')" >'
						+'<option value="0">--Select--</option>'
						+'<c:forEach var="item" items="${getArmyCourseInstitute}" varStatus="num">'
						+'<option value="${item[0]}" name="${item[1]}">${item[1]}</option> '
						+'</c:forEach></select></td>'
						+'<td style="width: 10%; " > <input type="text" id="army_course_institute_ot'+xaco+'" value="'+j[i].course_institute_other+'" name="army_course_institute_ot'+xaco+'" onkeypress="return onlyAlphabets(event);"  readonly  class="form-control autocomplete" autocomplete="off" ></td>'
						+'<td style="width: 10%;">'
						+'<select  id="army_course_div_grade'+xaco+'" name="army_course_div_grade'+xaco+'" onchange="onDivGrade('+xaco+')" class="form-control autocomplete"  >'
						+'<option value="0">--Select--</option>'
						+'<c:forEach var="item" items="${getDivclass}" varStatus="num">'
						+'<option value="${item[0]}" name="${item[1]}">${item[1]}</option> '
						+'</c:forEach></select></td>'
						+'<td style="width: 10%;"> <input type="text" id="ar_course_div_ot'+xaco+'" name="ar_course_div_ot'+xaco+'" value="'+j[i].ar_course_div_ot+'" onkeypress="return /[0-9a-zA-Z]/i.test(event.key)" readonly  class="form-control autocomplete" autocomplete="off" ></td>'
										
						+'<td style="width: 10%;">'
						+'<select  id="army_course_type'+xaco+'" name="army_course_type'+xaco+'"   class="form-control autocomplete"  >'
						+'<option value="0">--Select--</option>'
						+'<c:forEach var="item" items="${getCourseTypeList}" varStatus="num">'
						+'<option value="${item[0]}" name="${item[1]}">${item[1]}</option> '
						+'</c:forEach> </select>	</td>'
						+'<td style="width: 10%; display: none"> <input type="text" id="course_type_ot'+xaco+'" name="course_type_ot'+xaco+'" value="'+j[i].course_type_ot+'" onkeypress="return /[0-9a-zA-Z]/i.test(event.key)" readonly  class="form-control autocomplete" autocomplete="off" ></td>'
						
						+'<td style="width: 10%;">' 
						+' <input type="text" name="army_course_start_date'+xaco+'" id="army_course_start_date'+xaco+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')"   class="form-control" style="width: 85%;display: inline;" '
						+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
						+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="'+start_date+'">'
						+ '</td>'
						+'<td style="width: 10%;">' 
						+' <input type="text" name="army_course_date_of_completion'+xaco+'" id="army_course_date_of_completion'+xaco+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')"   class="form-control" style="width: 85%;display: inline;" '
						+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
						+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="'+date_of_completion+'">'
						+ '</td>'
						+'<td style="display:none;"><input type="text" id="army_course_ch_id'+xaco+'" name="army_course_ch_id'+xaco+'"  value="'+j[i].id+'" class="form-control autocomplete" autocomplete="off" ></td>'
						+'<td style="width: 10%;">'
						+'<a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "army_course_save'+xaco+'" onclick="army_course_save_fn('+xaco+');" ><i class="fa fa-save"></i></a>'
						+'<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "army_course_add'+xaco+'" onclick="army_course_add_fn('+xaco+');" ><i class="fa fa-plus"></i></a>'
						+'<a  class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "army_course_remove'+xaco+'" onclick="army_course_remove_fn('+xaco+');"><i class="fa fa-trash"></i></a> ' 
						+'</td></tr>');
				 $('#army_course_div_grade'+xaco).val(j[i].div_grade );
				 $('#army_course_institute'+xaco).val(j[i].course_institute );
				 $('#army_course_type'+xaco).val(j[i].course_type );
				 $('#army_course_authority').val(j[i].authority );
				 $('#army_course_date_of_authority').val(dauth );
				 datepicketDate('army_course_start_date'+xaco);
				 datepicketDate('army_course_date_of_completion'+xaco);
					onDivGrade(xaco);
					onCoursetype(xaco);
					onArmyCourseInstiChange(xaco);
		}
		if(xaco!=0){
			armyc=xaco;
			armyc_srno=xaco;
			$("#army_course_add"+xaco).show();
		}
	}
	  });
}


function AutoCompleteCourse(ele,index,val){
	type="";
	var category="JCO/OR";
	var abbr=[];
	var susval = [];
	if(val=='1'){
		type="abbr";
	}
	
	var code = ele.value;
	var susNoAuto =$("#"+ele.id);
	susNoAuto.autocomplete({
		source : function(request, response) {
			$.ajax({
				type : 'POST',
				url : "getArmyCourseNameList?" + key + "=" + value,
				data : {
					Course:code,
					type:type,
					category:category
				},
				success : function(data) {
					
					
					var length = data.length - 1;
					
					for (var i = 0; i < data.length; i++) {
						
						if(val=='1'){
							susval.push(data[i][2]);
							abbr.push(data[i][1]);
						}

						else{
							susval.push(data[i][1]);
							abbr.push(data[i][2]);
						}
					
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
				
				if(val=='1'){
					alert("Please Enter Valid Abbreviation ");				
					$("#army_course_name"+index).val("");
				}

				else{
					
					alert("Please Enter Valid Course Name ");
					$("#army_course_abbreviation"+index).val("");
				}
							
				susNoAuto.val("");
				susNoAuto.focus();
				return false;
			}
		},
		select : function(event, ui) {
			var arrindex=susval.indexOf(ui.item.value);
			var second=abbr[arrindex];
			if(val=='1'){
				$("#army_course_name"+index).val(second);
			}

			else{
				$("#army_course_abbreviation"+index).val(second);
			}
			
			
		}
	});
	
}
function onArmyCourseInstiChange(index){
	var val = $("#army_course_institute"+index+" option:selected").text();
	
	if(val != "OTHERS"){
		
		$("#army_course_institute_ot"+index).prop("readonly",true);
		$('#army_course_institute_ot'+index).val('');
	}
	else
		$("#army_course_institute_ot"+index).prop("readonly",false);

	
}
//***************** END QUALIFICATION DETAILS************************//
</Script>

<script>	
//***************** START CHANGE OF APPOINTEMENT DETAILS************************//
function get_Appointment(){
	  
	 var civ_id=$('#civ_id').val(); 
	 var i=0;
	  $.post('get_AppointmentJCO?' + key + "=" + value, { civ_id:civ_id}, function(j){
			var v=j.length;
			if(v!=0){

				 $('#appoint_id').val(j[i].id);
				 $("#appt_authority").val(j[i].appt_authority);
				 $("#appt_date_of_authority").val(ParseDateColumncommission(j[i].appt_date_of_authority));
				 $("#appointment").val(j[i].appointment);
				 $("#date_of_appointment").val(ParseDateColumncommission(j[i].date_of_appointment));
// 				 appNamechng();
// 				 $("#x_sus_no").val(j[i].x_list_sus_no);
// 				 $("#x_list_loc").val(j[i].x_list_loc);
			}
	  });
}	

// function appNamechng(){
// 	var appname = $("#appointment option:selected").text();
// 	  $.post('GetAppointmentFromMaster?' + key + "=" + value, {appname:appname}, function(j){
// 			if(j > 0){
// 				$("#xlist").show();
// 			}else{
// 				$("#xlist").hide();
// 				$("#x_sus_no").val("");
// 				$("#x_list_loc").val("");
// 		     }
// 	     });
// }
function  search_sus(sus_obj,unit_id){

	var sus_no = sus_obj.value;
	var susNoAuto = $("#"+sus_obj.id);

	
	
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
	        	  alert("Please Enter Approved Unit SUS No");
	        	  $('#'+unit_id).val('');
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
          
           $('#'+unit_id).val(dec(enc,j[0]));   
               
       }).fail(function(xhr, textStatus, errorThrown) {
       });
		} 	     
	});	
	
	
	}

function validate_appointment_save(){
	var tos_date=$('#tos_date').val();
	var civ_id=$('#civ_id').val();
	var enroll_date=$('#enroll_date').val();
	
	var status=$('#status').val();
	var appoint_id=$('#appoint_id').val();
	var formdata=$('#form_change_of_appointment').serialize();
	formdata+="&civ_id="+civ_id+"&enroll_date="+enroll_date+"&tos_date="+tos_date;	
	  if ($("input#appt_authority").val().trim()=="") {
		alert("Please Enter Authority");
		$("input#appt_authority").focus();
		return false;
	}
	  if(lengthValidation($("input#appt_authority").val().trim(),auth_length)){
			alert("Authority Length Should be Less or Equal To 100");
			$("#appt_authority").focus();
			return false;

		}
	  if ($("input#appt_date_of_authority").val() == "DD/MM/YYYY" || $("input#appt_date_of_authority").val().trim()=="") {
		alert("Please Enter Date of Authority ");
		$("input#appt_date_of_authority").focus();
		return false;
	} 
	  if(getformatedDate($("input#enroll_date").val()) > getformatedDate($("#appt_date_of_authority").val())) {
			alert("Authority Date should be Greater than Enrollment Date");
			return false;
	  }
  if ($("select#appointment").val() == "0") {
		alert("Please Select Appointment");
		$("select#appointment").focus();
		return false;
	} 
   if ($("input#date_of_appointment").val() == "DD/MM/YYYY" || $("input#date_of_appointment").val().trim()=="") {
		alert("Please Enter Date of Appointment");
		$("input#date_of_appointment").focus();
		return false;
	}  
   if($("input#tos_date").val()!="" && getformatedDate($("input#tos_date").val()) > getformatedDate($("#date_of_appointment").val())) {
		alert("Date of Appointment Date should be Greater than TOS Date");
		return false;
   }

 
	   $.post('change_appointmentJCOAction?' + key + "=" + value, formdata, function(data){		      
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
//***************** END QUALIFICATION DETAILS************************//
</Script>


<script>

//************************************* START DISCIPLINE *****************************//

function onTypeofEntry(){
	
	var type_of_entry_sel = $("#type_of_entry option:selected").text();
	
	if(type_of_entry_sel != "OTHER"){
		
		$("#type_of_entry_otherDiv").hide();
		$("#type_of_entry_other").hide();
		$('#type_of_entry_other').val('');
	}
	else{
		$("#type_of_entry_other").show();
		$("#type_of_entry_otherDiv").show();
	}
}

 function get_change_of_Discipline(){
var civ_id=$("#civ_id").val();
	 
	 var i=0;
	  $.post('getdisciplineData_jco?' + key + "=" + value, {civ_id:civ_id }, function(j){
			var v=j.length;
			if(v!=0){
				$('#disi_id').val(j[i].id);
				
				
				$("#army_act_sec").val(j[i].army_act_sec);
				$("#sub_clause").val(j[i].sub_clause);
				$("#description1").val(j[i].description);
				$("#type_of_entry").val(j[i].type_of_entry);
				
				 $("#dis_sus").val(j[i].unit_name);
				 fn_getUnitnamefromSus(j[i].unit_name, document.getElementById("unit_name"));
				 $("#trialed_by").val(j[i].trialed_by);
				 
				 $("#award_dt").val(ParseDateColumncommission(j[i].award_dt));
				 $("#disi_authority").val(j[i].disi_authority);
				 $("#disi_authority_date").val(ParseDateColumncommission(j[i].disi_authority_date));
				 $("#type_of_entry_other").val(j[i].type_of_entry_other);
				 if(j[i].type_of_entry_other!=""){
					 onTypeofEntry();
				 }
				 
				
				
			}
	  });
} 

 function validate_Discipline_save(){
     var formvalues=$("#form_disipline").serialize();
     
      
      if ($("#disi_authority").val().trim()=="") {
                     alert("Please Enter Authority");
                     $("#disi_authority").focus();
                     return false;
      } 
      if(lengthValidation($("input#disi_authority").val().trim(),auth_length)){
  		alert("Authority Length Should be Less or Equal To 100");
  		$("#disi_authority").focus();
  		return false;

  	}
      if ($("#disi_authority_date").val().trim()=="" || $("#disi_authority_date").val() =="DD/MM/YYYY") {
                     alert("Please Enter Authority Date");
                     $("#disi_authority_date").focus();
                     return false;
      } 
      if(getformatedDate($("input#enroll_date").val()) > getformatedDate($("#disi_authority_date").val())) {
                     alert("Authority Date should be Greater than Enrollment Date");
                     $("#disi_authority_date").focus();
                     return false;
       }
      if ($("select#army_act_sec").val()=="0") {
          alert("Please Select Army Act Sec");
          $("select#army_act_sec").focus();
          return false;
		} 
      if ($("select#sub_clause").val()=="0") {
          alert("Please Select Sub Clause");
          $("select#sub_clause").focus();
          return false;
		} 
		if ($("select#trialed_by").val()=="0") {
			alert("Please Select Trialed By");
			$("select#trialed_by").focus();
			return false;
		} 
       if ($("#description1").val().trim()=="") {
                     alert("Please Enter Punishment Awarded ");
                     $("#description1").focus();
                     return false;
      } 
     
      if ($("#type_of_entry").val() == "0") {
                     alert("Please Select Type of Entry ");
                     $("#type_of_entry").focus();
                     return false;
      }
      
       var type_of_entry_sel = $("#type_of_entry option:selected").text().toUpperCase();
             if(type_of_entry_sel == "OTHER" || type_of_entry_sel == "OTHER"){
                     if ($("#type_of_entry_other").val().trim()=="") {
                             alert("Please Enter Type of Entry Other");
                             $("#type_of_entry_other").focus();
                             return false;
              }
             }
             
             
         	if(type_of_entry_sel == "OTHERS" || type_of_entry_sel == "OTHER"){
    			if(lengthValidation($("input#type_of_entry_other").val().trim(),auth_length)){
    				alert("Type of Entry Other Length Should be Less or Equal To 100")
    				$("#type_of_entry_other").focus();
    				return false;
    			}
    		}
         	
             
       if ($("#award_dt").val().trim()=="" || $("#award_dt").val() == "DD/MM/YYYY") {
                     alert("Please Enter Award Date");
                     $("#award_dt").focus();
                     return false;
      } 
       if(getformatedDate($("input#enroll_date").val()) > getformatedDate($("#award_dt").val())) {
			alert("Date of Award should be Greater Or Equal  Enrollment Date");
			$("#award_dt").focus();
			return false;
	  }
      if ($("#unit_name").val().trim()=="") {
                     alert("Please Enter Unit Name");
                     $("#unit_name").focus();
                     return false;
      }
      

      var disi_id=$('#disi_id').val();
      var civ_id=$('#civ_id').val();                
      var enroll_date=$('#enroll_date').val();
      formvalues+="&civ_id="+civ_id+"&enroll_date="+enroll_date;

         $.post('Change_of_Discipline_action_jco?' + key + "=" + value,formvalues, function(data){
                if(data=="update"){
                        alert("Data Updated Successfully");
                }
                else if(parseInt(data)>0){
                      $('#disi_id').val(data);                         
                       alert("Data Saved Successfully")
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
				url : "PSG_CADET_getTargetUnitsNameActiveList?" + key + "=" + value,
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
				alert("Please Enter Approved Unit Name");
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
			+'<td class="aller_srno" style="width: 2%;">'+aller_srno+'</td>'
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
	  $.post('update_offr_allergic_delete_action_jco?' + key + "=" + value, {allergic_ch_id:allergic_ch_id }, function(data){ 
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
							 $('.aller_srno').each(function(i, obj) {
								 
									obj.innerHTML=i+1;
									aller_srno=i+1;
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
function allergic_save_fn(ps){
	var allergic=$('#allergic'+ps).val();
	var allergic_ch_id=$('#allergic_ch_id'+ps).val();
	
	var civ_id=$('#civ_id').val();

	if (allergic.trim()=="") {
		alert("Please Enter Allergy");
		$("input#allergic"+ps).focus();
		return false;
	}
	
	  $.post('update_offr_allergic_detail_action_jco?' + key + "=" + value, {allergic:allergic,allergic_ch_id:allergic_ch_id,civ_id:civ_id }, function(data){
	          if(data=="update")
	        	  alert("Data Updated Successfully");
	          else if(parseInt(data)>0){
	        	 $('#allergic_ch_id'+ps).val(data);
	        	 $("#allergic_add"+ps).show();
	        	 $("#allergic_remove"+ps).show();
	        	  alert("Data Saved Successfully")
	          }
	          else
	        	  alert(data)
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});
}

function get_allergic_details(){
	  
	 var civ_id=$('#civ_id').val(); 
	 var i=0;
	  $.post('update_offr_allergic_getData_jco?' + key + "=" + value, {civ_id:civ_id}, function(j){
			var v=j.length;
			if(v!=0){
		xaller=0;
		for(i;i<v;i++){
			
			xaller=xaller+1;
				if(xaller==1){
					$('#allergictbody').empty(); 
				}
				var dauth=ParseDateColumncommission(j[i].date_of_authority);
				$("table#allergic_table").append('<tr id="tr_allergic'+xaller+'">'
						+'<td class="aller_srno" style="width: 2%;">'+xaller+'</td>'
						+'<td><input type="text" id="allergic'+xaller+'" maxlength="100" name="allergic'+xaller+'" value="'+j[i].medicine+'"'
						+'class="form-control autocomplete" autocomplete="off">'
						+'</td>'
						+'<td style="display:none;"><input type="text" id="allergic_ch_id'+xaller+'" name="allergic_ch_id'+xaller+'"  value="'+j[i].id+'" class="form-control autocomplete" autocomplete="off" ></td>'
						+'<td style="width: 10%;">'
						+'<a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "allergic_save'+xaller+'" onclick="allergic_save_fn('+xaller+');" ><i class="fa fa-save"></i></a>'
						+'<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "allergic_add'+xaller+'" onclick="allergic_add_fn('+xaller+');" ><i class="fa fa-plus"></i></a>'
						+'<a  class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "allergic_remove'+xaller+'" onclick="allergic_remove_fn('+xaller+');"><i class="fa fa-trash"></i></a> ' 
						+'</td></tr>');
				 
			
			
		}
		if(xaller!=0){
			aller=xaller;
			aller_srno=xaller;
			$("#allergic_add"+xaller).show();
		}
	}
	  });
}



</script>
<script>

/////////////search subject////////////////////////



$("input[type='checkbox'][name='multisub']").click(function() {
  
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
	var recovered_arms=$('#recovered_arms').val();
	var des_ch_id=$('#des_ch_id').val();
	
	var civ_id=$('#civ_id').val();
	var enroll_date=$('#enroll_date').val(); 
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
	
	
	if (dt_desertion.trim()=="" || dt_desertion == "DD/MM/YYYY") {
		alert("Please Select Date of Desertion");
		$("input#dt_desertion").focus();
		return false;
	}
	 if(getformatedDate($("input#enroll_date").val()) >= getformatedDate($("#dt_desertion").val())) {
			alert("Desertion Date should be Greater than Enrollment Date");
			return false;
	 }
	 if(getformatedDate($("input#dt_desertion").val()) >= getformatedDate($("#dt_recovered").val())) {
			alert("Recovered Date should be Greater than Desertion Date");
			return false;
	 }
	/* if (dt_recovered.trim()=="" || dt_recovered == "DD/MM/YYYY") {
			alert("Please Select Date of Recovered");
			$("input#dt_recovered").focus();
			return false;
		} */
	if(desertion_cause == null || desertion_cause=="0"){ 
		alert("Please Select The Desertion Cause");
		$("select#desertion_cause").focus();
		return false;
	} 
		
	var deserter_ot = $("#desertion_cause option:selected").text().toUpperCase();
	if(deserter_ot == "OTHERS" || deserter_ot == "OTHER"){
		if(ot_desertion_cause == null || ot_desertion_cause==""){
			alert( "Please Enter Other ");
			$("input#ot_desertion_cause").focus();
			return false;
			}
		
		
			if(lengthValidation($("input#ot_desertion_cause").val().trim(),auth_length)){
				alert(" Other Length Should be Less or Equal To 100")
				$("#ot_desertion_cause").focus();
				return false;
			}
		
		}
	/* if(indl_class == null || indl_class==""){ 
		alert("Please Enter The Indl Class");
		$("input#indl_class").focus();
		return false;
	}  */
	
	    $.post('deserterAction_jco?' + key + "=" + value, {
		   deserter:deserter,
		   arm_type:arm_type,
		   arm_type_weapon:arm_type_weapon,
		   dt_desertion:dt_desertion,
		   dt_recovered:dt_recovered,
		   desertion_cause:desertion_cause,
		   indl_class:indl_class,
		   ot_desertion_cause:ot_desertion_cause,
		   recovered_arms:recovered_arms,
		   des_ch_id:des_ch_id,civ_id:civ_id,enroll_date:enroll_date
		   }, function(data){
	          if(data=="update")
	        	  alert("Data Updated Successfully");
	          else if(parseInt(data)>0){
	        	 $('#des_ch_id').val(data);
	        	 alert("Data Saved Successfully")
	          }
	          else
	        	  alert(data);
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		}); 
}

//get
function get_deserter(){
	
	var civ_id=$('#civ_id').val();
	$.post('deserter_GetData_jco?' + key + "=" + value,{civ_id:civ_id}, function(j){
		var v=j.length;
		if(v!=0){
			
			if (j[0].dt_recovered == "" || j[0].dt_recovered == null || j[0].dt_recovered == "null"){
				
		
				$("#div_dt_rec").hide();
				$("#des_ch_id").val(j[0].id);
				$("#deserter").val(j[0].deserter);
				$("#arm_type").val(j[0].arm_type);
				if(j[0].arm_type == 1){
					   $("#div_weapon").show(); 
					   $("#arm_type_weapon").val(j[0].arm_type_weapon);
				}else {
					    $("#div_weapon").hide(); 
						$("#arm_type_weapon").val("");
				}
				
				$("#dt_desertion").val(ParseDateColumncommission(j[0].dt_desertion));
				
				$("#desertion_cause").val(j[0].desertion_cause);
				
				if (j[0].desertion_cause == 3){
					$("#div_cause").show(); 
					$("#ot_desertion_cause").val(j[0].ot_desertion_cause);
				}else{
					$("#div_cause").hide(); 
			 		$('#ot_desertion_cause').val("");
				}
				$("#indl_class").val(j[0].indl_class);
				
				
				
			}else{
		
				if(j[0].arm_type == 1){
					$("#div_arms_rec").show();
					$("#recovered_arms").val(j[0].recovered_arms);
				}
				$("#div_dt_rec").show();
				$("#des_ch_id").val(j[0].id);
				$("#deserter").val(j[0].deserter);
				$("#arm_type").val(j[0].arm_type);
				 $("select#arm_type").attr('readonly', true);
				if(j[0].arm_type == 1){
					   $("#div_weapon").show(); 
					   $("#arm_type_weapon").val(j[0].arm_type_weapon);
					   $("input#arm_type_weapon").attr('readonly', true);
				}else {
					    $("#div_weapon").hide(); 
						$("#arm_type_weapon").val("");
				}
				
				$("#dt_desertion").val(ParseDateColumncommission(j[0].dt_desertion));
				 $("input#dt_desertion").attr('readonly', true);
				$("#dt_recovered").val(ParseDateColumncommission(j[0].dt_recovered));
				$("#desertion_cause").val(j[0].desertion_cause);
				 $("select#desertion_cause").attr('readonly', true);
				if (j[0].desertion_cause == 3){
					$("#div_cause").show(); 
					$("#ot_desertion_cause").val(j[0].ot_desertion_cause);
					 $("input#ot_desertion_cause").attr('readonly', true);
				}else{
					$("#div_cause").hide(); 
			 		$('#ot_desertion_cause').val("");
				}
				$("#indl_class").val(j[0].indl_class);
				 $("input#indl_class").attr('readonly', true);
				 
				 
				
				 
			}
			
		}
	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
	
	
	
	 $.post('deserter_GetDataA_jco?' + key + "=" + value,{civ_id:civ_id}, function(j){
			var v=j.length;
			if(v!=0){
				
				if(j[0].arm_type == 1){
					$("#div_arms_rec").show();
					$("#recovered_arms").val(j[0].recovered_arms);
				}
				
				$("#div_dt_rec").show();
				$("#des_ch_id").val(j[0].id);
				$("#deserter").val(j[0].deserter);
				$("#arm_type").val(j[0].arm_type);
				 $("select#arm_type").attr('readonly', true);
				if(j[0].arm_type == 1){
					   $("#div_weapon").show(); 
					   $("#arm_type_weapon").val(j[0].arm_type_weapon);
					   $("input#arm_type_weapon").attr('readonly', true);
				}else {
					    $("#div_weapon").hide(); 
						$("#arm_type_weapon").val("");
				}
				
				$("#dt_desertion").val(ParseDateColumncommission(j[0].dt_desertion));
				 $("input#dt_desertion").attr('readonly', true);
				$("#dt_recovered").val(ParseDateColumncommission(j[0].dt_recovered));
				
				$("#desertion_cause").val(j[0].desertion_cause);
				 $("select#desertion_cause").attr('readonly', true);
				if (j[0].desertion_cause == 3){
					$("#div_cause").show(); 
					$("#ot_desertion_cause").val(j[0].ot_desertion_cause);
					 $("input#ot_desertion_cause").attr('readonly', true);
				}else{
					$("#div_cause").hide(); 
			 		$('#ot_desertion_cause').val("");
				}
				$("#indl_class").val(j[0].indl_class);
				 $("input#indl_class").attr('readonly', true);
			}
		  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
			});
	
	
}


/*/////////////////////////  End Deserter ////////////////// */ 
</script>

<script>
  
  //////////////////////// Date //////////////////////

jQuery(function($){ 
	 datepicketDate('r_date_of_authority'); 
	 datepicketDate('dt_of_designation');
	 datepicketDate('date_of_rank');
	 datepicketDate('na_date_of_authority');
	 datepicketDate('appt_date_of_authority');
	 datepicketDate('date_of_appointment');
	 datepicketDate('issue_dt'); 
	 datepicketDate('rel_date_of_authority'); 
	 datepicketDate('sib_date_of_birth1');
	 datepicketDate('sib_adop_date1');
	 datepicketDate('date_authority');
	 datepicketDate('date_authority_des');
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
	 datepicketDate('ten_from');
	 datepicketDate('ten_to');
	 $( "#ten_to" ).datepicker( "option", "maxDate", null);
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
	 
	 
	 datepicketDate('dt_desertion');
	 datepicketDate('dt_recovered');
	 datepicketDate('date_of_attestation');
	 datepicketDate('td_date_authority');
	 datepicketDate('date_of_trade');
	 
	 datepicketDate('cla_date_authority');
	 datepicketDate('date_of_class');
	 
	 datepicketDate('gp_date_authority');
	 datepicketDate('date_of_group');
	 
	 
	 datepicketDate('se_date_authority');
	 datepicketDate('date_of_seniority');
	 
	 datepicketDate('att_date_authority');
	 datepicketDate('att_date_of_move');
	 datepicketDate('att_from');
	 datepicketDate('att_to');
	 datepicketDate('separated_from_dt');
	 datepicketDate('separated_to_dt');
	 
	 $( "#att_to" ).datepicker( "option", "maxDate", null);
	 
	/*  if(clickrecall )
	 var currentDate = new Date();  
	 $("#dt_recovered").datepicker("setDate",currentDate);
	  */
	 $("#circumstances").click(function(){
		 $('body').unbind();
	  });
	  
	 $("#circumstances").focusout(function(){
		 
	 
		  $('body').bind('cut copy paste', function (e) {
		        e.preventDefault();
		    });
		  });
});
  

</script>

<!-- /********  CSD DETAIL  keval*********/ -->

<script>


csdno=1;
CSD_srno=1;
function CSD_Card_Add_fn(q){
	 if( $('#CSD_Card_Add'+q).length )         
	 {
			$("#CSD_Card_Add"+q).hide();
	 }
	 
	 if(q!=0)
		 CSD_srno+=1;
	
		 csdno=q+1;

	$("table#CSD_table").append('<tr id="tr_csd'+csdno+'">'
			+'<td class="CSD_srno" style="width: 2%;">'+CSD_srno+'</td>'
			+'<td style="width: 10%;">'
			+'<select  id="relation'+csdno+'" name="relation'+csdno+'" onchange="getNameAndDob('+csdno+');" class="form-control autocomplete" >'
			+' <option value="0">--Select--</option>'
			+'<c:forEach var="item" items="${getCSDCategoryList}" varStatus="num">'
			+'<option value="${item[0]}" name="${item[1]}">${item[1]}</option>'
			+'</c:forEach>'
			+'</select>'
			+'</td>'
			+'<td id="iname'+csdno+'"><label id="namelb'+csdno+'"></label> <input type="hidden" id="name'+csdno+'" name="name'+csdno+'"  class="form-control autocomplete" autocomplete="off"></td>'
			
			
			+'<td id="sname'+csdno+'" style="width: 10%;display:none;">'
			+'<select  id="Dname'+csdno+'" name="name'+csdno+'" class="form-control autocomplete"onchange="getDob('+csdno+');" >'
			+' <option value="0">--Select--</option>'
			+'</select>'
			+'</td>'
			
			
			+'<td>' 
			+' <label id="date_of_birthlb'+csdno+'"></label> <input type="hidden" name="dob'+csdno+'" id="dob'+csdno+'" maxlength="10"  > '
			+ '</td>'
			+'<td>'
			+'<label for="type_of_card_grocery'+csdno+'"><input type="radio" class="from-control" id="type_of_card_grocery'+csdno+'" name= "type_of_card'+csdno+'" value="grocery"> Grocery </label>'
			+'<label for="type_of_card_liquor'+csdno+'"><input type="radio" class="from-control" id="type_of_card_liquor'+csdno+'" name="type_of_card'+csdno+'" value="liquor"> Liquor </label>'
			+'</td>'
			+'<td><input type="text" id="card_no'+csdno+'" name="card_no'+csdno+'" class="form-control autocomplete" maxlength="25"  onkeypress="return /[0-9a-zA-Z]/i.test(event.key)"  autocomplete="off"></td>'
			+'<td style="display:none;"><input type="text" id="CSD_Card_ch_id'+csdno+'" name="CSD_Card_ch_id'+csdno+'"  value="0" class="form-control autocomplete" autocomplete="off" ></td>'
			+'<td style="width: 10%;">'
			+'<a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "CSD_save'+csdno+'" onclick="CSD_save_fn('+csdno+');" ><i class="fa fa-save"></i></a>'
			+'<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "CSD_Card_Add'+csdno+'" onclick="CSD_Card_Add_fn('+csdno+');" ><i class="fa fa-plus"></i></a>'
			+'<a style="display:none;" class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "CSD_Card_Remove'+csdno+'" onclick="CSD_Card_Remove_fn('+csdno+');"><i class="fa fa-trash"></i></a> '
			+'</td></tr>');
	
}


function CSD_Card_Remove_fn(R){
	var rc = confirm("Are You Sure! You Want To Delete");
	 if(rc){
		 
	 var CSD_Card_ch_id=$('#CSD_Card_ch_id'+R).val();
	  $.post('CSDCard_delete_action_jco?' + key + "=" + value, {CSD_Card_ch_id:CSD_Card_ch_id }, function(data){ 
			   if(data=='1'){
					 $("tr#tr_csd"+R).remove(); 
							 if(R==csdno){
								 R = R-1;
								 var temp=true;
								 for(i=R;i>=1;i--){
								 if( $('#CSD_Card_Add'+i).length )         
								 {
									 $("#CSD_Card_Add"+i).show();
									 temp=false;
									 csdno=i;
									 break;
								 }}
						 
							 if(temp){
								 CSD_Card_Add_fn(0);
								}
			  			 }
							 $('.CSD_srno').each(function(i, obj) {
								 
									obj.innerHTML=i+1;
									CSD_srno=i+1;
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
function CSD_save_fn(ps){
	
	
	var civ_id=$('#civ_id').val();
	var name=$('#name'+ps).val();
	var Sname = $("#Dname"+ps).val(); 
	var dob=$('#dob'+ps).val();
	var relation=$('#relation'+ps).val();
	var card_no=$('#card_no'+ps).val();
	var type_of_card="";
	if(document.getElementById('type_of_card_grocery'+ps).checked){
		type_of_card=$('#type_of_card_grocery'+ps).val()
	}else{
		type_of_card=$('#type_of_card_liquor'+ps).val()	
	}
	
	var CSD_Card_ch_id=$('#CSD_Card_ch_id'+ps).val();
	
	if (relation == "0") {
		alert("Please Select Category");
		$("select#relation"+ps).focus();
		return false;
	}
	if (name.trim()=="" && Sname == "0") {
		alert("Please Enter Name");
		$("input#name"+ps).focus();
		return false;
	}
	if (dob.trim()=="" || dob.trim()=="DD/MM/YYYY") {
		alert("Please Select Date of Birth");
		$("input#dob"+ps).focus();
		return false;
	}
	
	 var r =  $('input[name="type_of_card'+ps+'"]:checked').val();
	  if(r == undefined)
	  {		 
		    alert("Please Select Type of Card");
			return false;
	  } 
	
	if (card_no.trim()=="" || card_no.trim().length<10) {
		alert("Please Enter Card No");
		$("input#card_no"+ps).focus();
		return false;
	}
	  $.post('CSD_action_jco?' + key + "=" + value, {civ_id:civ_id,name:name,Sname:Sname,dob:dob,relation:relation,card_no:card_no,type_of_card:type_of_card,CSD_Card_ch_id:CSD_Card_ch_id }, function(data){
	          if(data=="update")
	        	  alert("Data Updated Successfully");
	          else if(parseInt(data)>0){
	        	 $('#CSD_Card_ch_id'+ps).val(data);
	        	 $("#CSD_Card_Add"+ps).show();
	        	 $("#CSD_Card_Remove"+ps).show();
	        	  alert("Data Saved Successfully")
	          }
	          else
	        	  alert(data)
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});
}

function get_CSD_details(){
	  
	 var civ_id=$('#civ_id').val(); 
	 var i=0;
	  $.post('updateCSD_getData_jco?' + key + "=" + value, {civ_id:civ_id}, function(j){
		  var v=j.length;
			if(v!=0){
				$('#CSDtbody').empty(); 
			
		x=0;
		for(i;i<v;i++){
			
			
			x=x+1;
			
		
				var td=ParseDateColumncommission(j[i].dob);
				$("table#CSD_table").append('<tr id="tr_csd'+x+'">'
						+'<td class="CSD_srno" style="width: 2%;">'+x+'</td>'
						
						+'<td><select  id="relation'+x+'" name="relation'+x+'" value='+j[i].relation+' onchange="getNameAndDob('+x+');" class="form-control autocomplete" >'
						+' <option value="0">--Select--</option>'
						+'<c:forEach var="item" items="${getCSDCategoryList}" varStatus="num">'
						+'<option value="${item[0]}" name="${item[1]}">${item[1]}</option>'
						+'</c:forEach>'
						+'</select>'
						+'</td>'
						
						+'<td id="iname'+x+'"><label id="namelb'+x+'">'+j[i].name+'</label> <input type="hidden" id="name'+x+'" name="name'+x+'" value='+j[i].name+' class="form-control autocomplete" autocomplete="off"></td>'
						
						+'<td id="sname'+x+'" style="width: 10%;display:none;">'
						+'<select  id="Dname'+x+'" name="name'+x+'" class="form-control autocomplete"onchange="getDob('+x+');" >'
						+' <option value="0">--Select--</option>'
						+'</select>'
						+'</td>'
						
						
						+'<td>' 
						+' <label id="date_of_birthlb'+x+'">'+td+'</label> <input type="hidden" name="dob'+x+'" id="dob'+x+'" maxlength="10"  '
						+'	 value="'+td+'">'
						+ '</td>'
						+'<td>'
						+'<label for="type_of_card_grocery'+x+'"><input type="radio" class="from-control" id="type_of_card_grocery'+x+'" name= "type_of_card'+x+'" value="grocery"> Grocery </label>'
						+'<label for="type_of_card_liquor'+x+'"><input type="radio" class="from-control" id="type_of_card_liquor'+x+'" name="type_of_card'+x+'" value="liquor"> Liquor </label>'
						+'</td>'
						+'<td><input type="text" id="card_no'+x+'" name="card_no'+x+'" value='+j[i].card_no+' class="form-control autocomplete"  onkeypress="return /[0-9a-zA-Z]/i.test(event.key)"  maxlength="25" autocomplete="off"></td>'
						+'<td style="display:none;"><input type="text" id="CSD_Card_ch_id'+x+'" name="CSD_Card_ch_id'+x+'"  value="'+j[i].id+'" class="form-control autocomplete" autocomplete="off" ></td>'
						+'<td style="width: 10%;">'
						+'<a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "CSD_save'+x+'" onclick="CSD_save_fn('+x+');" ><i class="fa fa-save"></i></a>'
						+'<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "CSD_Card_Add'+x+'" onclick="CSD_Card_Add_fn('+x+');" ><i class="fa fa-plus"></i></a>'
						+'<a  class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "CSD_Card_Remove'+x+'" onclick="CSD_Card_Remove_fn('+x+');"><i class="fa fa-trash"></i></a> ' 
						+'</td></tr>');
				$('#relation'+x).val(j[i].relation); 
				//$('#dob'+x).val(td);
			
				
				if($('#relation'+x).val() == 5 || $('#relation'+x).val() == 6){
					$.ajaxSetup({
						async : false
					});
					getNameAndDob(x);
					if($('#Dname'+x).is(":visible")){
						$('#Dname'+x).val(j[i].name); 
						$("#dob"+x).val(td)
						$("#date_of_birthlb"+x).text(td)
					}
				}
				if(j[i].type_of_card =="grocery"){
					$('#type_of_card_grocery'+x).attr("checked",true); 
				}else if(j[i].type_of_card =="liquor"){
					$('#type_of_card_liquor'+x).attr("checked",true); 
				}
				
				
		}
		csdno = v;
		CSD_srno = v;
		$('#CSD_Card_Add' + csdno).show();
			}
	  });
}

function getDob(val){
	
	 $("#dob"+val).val("")
	 $("#date_of_birthlb"+val).text("")
	var id = $("select#Dname"+val).val();
	 var civ_id=$('#civ_id').val(); 
	$.post('getChilddob_jco?' + key + "=" + value, {id:id,civ_id:civ_id}, function(j){
		 $("#dob"+val).val(ParseDateColumncommission(j[0][0]))
		 $("#date_of_birthlb"+val).text(ParseDateColumncommission(j[0][0]))
	 });
	
}

function getNameAndDob(val){
	 $("#dob"+val).val("")
	 $("#name"+val).val("");
	 $("#namelb"+val).text("");
	 $("#Dname"+val).val("0");
	 $("#dob"+val).val("")
	  $("#date_of_birthlb"+val).text("");
	 
	 var options = '<option   value="0">' + "--Select--"
		+ '</option>';
	 $("select#Dname"+val).html(options);
	 var rela = $("#relation"+val).val();
	 var civ_id=$('#civ_id').val(); 
		if(rela=='1' || rela=='3' || rela=='4' || rela=='2'){ 
			
			 $.post('getSelfMotFatName_jco?' + key + "=" + value, {civ_id:civ_id}, function(j){				 
				
				 $("#iname"+val).show();
				 $("#sname"+val).hide();
				
				 $("#namelb"+val).show();
				 $("#Dname"+val).hide();
				 $("#Dname"+val).val('0');
			 if(rela =='1' || rela=='2'){
				 $("#dob"+val).val(ParseDateColumncommission(j[0][2]))
				 $("#name"+val).val(j[0][1]); 
				 $("#namelb"+val).text(j[0][1]);
				 $("#date_of_birthlb"+val).text(ParseDateColumncommission(j[0][2]));
				 
			 }else if (rela =='3'){
				 $("#dob"+val).val(ParseDateColumncommission(j[0][6]))
				 $("#name"+val).val(j[0][5]);
				 $("#namelb"+val).text(j[0][5]);
				 $("#date_of_birthlb"+val).text(ParseDateColumncommission(j[0][6]));
			 }else if (rela =='4'){
				 $("#dob"+val).val(ParseDateColumncommission(j[0][4]))
				 $("#name"+val).val(j[0][3]);
				 $("#namelb"+val).text(j[0][3]);
				 $("#date_of_birthlb"+val).text(ParseDateColumncommission(j[0][4]));
			 }						
				 
			 });
			
		}/* else if(rela=='2'){
			 $.post('getSpouseName_jco?' + key + "=" + value, {civ_id:civ_id}, function(j){
				 
			 if(j.length <=0){
				 alert("Please Fill Marital Details");
				 $("select#relation"+val).val('0');
			 }
					 if(rela =='2'){
						 $("#iname"+val).show();
						 $("#sname"+val).hide();
						 $("#namelb"+val).show();
						 $("#Dname"+val).hide();
						 $("#Dname"+val).val('0');
						 $("#dob"+val).val(ParseDateColumncommission(j[0][2]))
						 $("#name"+val).val(j[0][1]); 
						 $("#namelb"+val).text(j[0][1]);
						 $("#date_of_birthlb"+val).text(ParseDateColumncommission(j[0][2]));
					 }
					
					 
				 });
		} */else if(rela=='5' || rela=='6'){
			$.post('getChildName_jco?' + key + "=" + value, {civ_id:civ_id,rela:rela}, function(j){
				
				 if(j.length <=0){
					 alert("Please Fill Child Details");
					 $("select#relation"+val).val('0');
				 }
				if(j.length > 1){
					 $("#iname"+val).hide();
					 $("#sname"+val).show();
					 $("#namelb"+val).hide();
					 $("#name"+val).val('');
					 $("#namelb"+val).text('');
					 $("#Dname"+val).show();
				
				for (var i = 0; i < j.length; i++) {

					options += '<option   value="' + j[i][1] + '" name="'+j[i][1]+'" >'
							+ j[i][1] + '</option>';

				}
					 $("select#Dname"+val).html(options);
				}
				
				if(j.length == 1){
					 $("#dob"+val).val(ParseDateColumncommission(j[0][2]))
					 $("#name"+val).val(j[0][1]); 
					 $("#namelb"+val).text(j[0][1]);
					 $("#date_of_birthlb"+val).text(ParseDateColumncommission(j[0][2]));
					 $("#iname"+val).show();
					 $("#sname"+val).hide();
					 $("#namelb"+val).show();
					 $("#Dname"+val).hide();
					
				}
				 
			 });
		
		
		}
		 
		 
}
</script>
<!-- /**********END CSD DETAIL *********/ -->


<script>
//*************************************Start Trade DETAIL*****************************//
function get_trade_details(){
	  
	 var civ_id=$('#civ_id').val();
	 var i=0;
	  $.post('change_of_trade_JCOGetData?' + key + "=" + value, {civ_id:civ_id }, function(j){
			var v=j.length;
			if(v!=0){
				$('#td_id').val(j[i].id);
				 $("#td_authority").val(j[i].td_authority);
				 $("#td_date_authority").val(ParseDateColumncommission(j[i].td_date_authority));
				 $("#date_of_trade").val(ParseDateColumncommission(j[i].date_of_trade));
				 $("#trade").val(j[i].trade);
				
			}
	  });
}	

function validate_trade(){
	
	  if ($("input#td_authority").val().trim()=="") {
			alert("Please Enter Authority");
			$("input#td_authority").focus();
			return false;
		}
	  if(lengthValidation($("input#td_authority").val().trim(),auth_length)){
			alert("Authority Length Should be Less or Equal To 100")
			$("#td_authority").focus();
			return false;
		}
	 if ($("input#td_date_authority").val() == "DD/MM/YYYY" || $("input#td_date_authority").val().trim()=="") {
			alert("Please Enter Date of Authority ");
			$("input#td_date_authority").focus();
			return false;
		} 
	 	if(getformatedDate($("input#enroll_date").val()) > getformatedDate($("#td_date_authority").val())) {
			alert("Authority Date should Be Greater Than Enrollment Date");
			return false;
	  }
	 	if ($("select#trade").val() == "-1") {
			alert("Please Select Trade");
			$("select#trade").focus();
			return false;
		}
	 	
	 	 if ($("input#date_of_trade").val() == "DD/MM/YYYY" || $("input#date_of_trade").val().trim()=="") {
				alert("Please Enter Date of Change of Trade ");
				$("input#date_of_trade").focus();
				return false;
			} 
		 	if(getformatedDate($("input#enroll_date").val()) > getformatedDate($("#date_of_trade").val())) {
				alert("Date of Change of Trade should Be Greater Than Enrollment Date");
				return false;
		  }
	var civ_id=$('#civ_id').val();
	
	
	var enroll_date=$('#enroll_date').val();
	var formdata=$('#form_trade').serialize();
	formdata+="&civ_id="+civ_id+"&enroll_date="+enroll_date;	
	   $.post('change_in_tarde_JcoOr_action?' + key + "=" + value, formdata, function(data){		      
	          if(data=="update")
	        	  alert("Data Updated Successfully");
	          else if(parseInt(data)>0){
	        	
	        	 $('#td_id').val(data);
	        	  alert("Data Saved Successfully")
	          }
	          else
	        	  alert(data);
	    		          
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});	
}
//************************************* End Tarde DETAIL*****************************//

function get_postingtype(){
	  
	 var civ_id=$('#civ_id').val(); 
	 var i=0;
	  $.post('change_of_posting_JCOGetData?' + key + "=" + value, {civ_id:civ_id}, function(j){
			var v=j.length;
			
			if(v!=0){
				$('#ch_pp_id').val(j[i].id);
				 $("#type_of_posting").val(j[i].type_of_post);
				
				
				
			}
	  });
}
 function validate_Chngofposting_save(){
	 
		var civ_id=$('#civ_id').val();
	 var ch_pp_id=$('#ch_pp_id').val();
		var formdata=$('#form_Changetype_of_posting').serialize();
		formdata+="&civ_id="+civ_id+"&ch_pp_id="+ch_pp_id;	
		
		
		  if ($("select#type_of_posting").val() == "-1") {
			alert("Please Select Type of Posting");
			$("select#type_of_posting").focus();
			return false;
		}
		
	 

	 
		   $.post('change_postingJCOAction?' + key + "=" + value, formdata, function(data){		      
			   if(data=="update"){
				   alert("Data Updated Successfully");
			   }
			   else if(parseInt(data)>0){
		        	 $('#ch_pp_id').val(data);
		        	  alert("Data Saved Successfully")
		          }
		          else
		        	  alert(data)
		 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		  		});	
	}
 
</script>


<script>
//*************************************Start Class Pay DETAIL*****************************//
function get_class_pay_details(){
	  
	 var civ_id=$('#civ_id').val();
	 var i=0;
	  $.post('change_class_pay_JCOGetData?' + key + "=" + value, {civ_id:civ_id }, function(j){
			var v=j.length;
			if(v!=0){
				$('#cla_id').val(j[i].id);
				 $("#cla_authority").val(j[i].cla_authority);
				 $("#cla_date_authority").val(ParseDateColumncommission(j[i].cla_date_authority));
				 $("#cla_class").val(j[i].cla_class);
				 $("#date_of_class").val(ParseDateColumncommission(j[i].date_of_class));
			}
	  });
}	

function validate_class_pay(){
	
	  if ($("input#cla_authority").val().trim()=="") {
			alert("Please Enter Authority");
			$("input#cla_authority").focus();
			return false;
		}
	  
	  if(lengthValidation($("input#cla_authority").val().trim(),auth_length)){
			alert("Authority Length Should be Less or Equal To 100")
			$("#cla_authority").focus();
			return false;
		}
	 if ($("input#cla_date_authority").val() == "DD/MM/YYYY" || $("input#cla_date_authority").val().trim()=="") {
			alert("Please Enter Date of Authority ");
			$("input#cla_date_authority").focus();
			return false;
		} 
	 	if(getformatedDate($("input#enroll_date").val()) > getformatedDate($("#cla_date_authority").val())) {
			alert("Authority Date should Be Greater Than Enrollment Date");
			return false;
	  }
	 	if ($("select#cla_class").val() == "-1") {
			alert("Please Select New Class(PAY)");
			$("select#cla_class").focus();
			return false;
		}
	 	
	 	 if ($("input#date_of_class").val() == "DD/MM/YYYY" || $("input#date_of_class").val().trim()=="") {
				alert("Please Enter Date of New Class(PAY) ");
				$("input#date_of_class").focus();
				return false;
			} 
		 	if(getformatedDate($("input#enroll_date").val()) > getformatedDate($("#date_of_class").val())) {
				alert("Date of New Class(PAY) should Be Greater Than Enrollment Date");
				return false;
		  }
	var civ_id=$('#civ_id').val();
	
	
	var enroll_date=$('#enroll_date').val();
	var formdata=$('#form_class_pay').serialize();
	formdata+="&civ_id="+civ_id+"&enroll_date="+enroll_date;	
	   $.post('change_in_class_pay_JcoOr_action?' + key + "=" + value, formdata, function(data){		      
	          if(data=="update")
	        	  alert("Data Updated Successfully");
	          else if(parseInt(data)>0){
	        	
	        	  $('#cla_id').val(data);
	        	  alert("Data Saved Successfully")
	          }
	          else
	        	  alert(data);
	    		          
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});	
}
//************************************* End Class Pay DETAIL*****************************//
</script>



<script>
//*************************************Start Pay Group DETAIL*****************************//
function get_pay_group_details(){
	  
	 var civ_id=$('#civ_id').val();
	 var i=0;
	  $.post('change_pay_group_JCOGetData?' + key + "=" + value, {civ_id:civ_id }, function(j){
			var v=j.length;
			if(v!=0){
				$('#gp_id').val(j[i].id);
				 $("#gp_authority").val(j[i].gp_authority);
				 $("#gp_date_authority").val(ParseDateColumncommission(j[i].gp_date_authority));
				 $("#gp_group").val(j[i].gp_group);
				 $("#date_of_group").val(ParseDateColumncommission(j[i].date_of_group));
			}
	  });
}	

function validate_pay_group(){
	
	  if ($("input#gp_authority").val().trim()=="") {
			alert("Please Enter Authority");
			$("input#gp_authority").focus();
			return false;
		}
	  
	  if(lengthValidation($("input#gp_authority").val().trim(),auth_length)){
			alert("Authority Length Should be Less or Equal To 100")
			$("#gp_authority").focus();
			return false;
		}
	  
	 if ($("input#gp_date_authority").val() == "DD/MM/YYYY" || $("input#gp_date_authority").val().trim()=="") {
			alert("Please Enter Date of Authority ");
			$("input#gp_date_authority").focus();
			return false;
		} 
	 	if(getformatedDate($("input#enroll_date").val()) > getformatedDate($("#gp_date_authority").val())) {
			alert("Authority Date should Be Greater Than Enrollment Date");
			return false;
	  }
	 	if ($("select#gp_group").val() == "-1") {
			alert("Please Select New Pay Group");
			$("select#gp_group").focus();
			return false;
		}
	 	
	 	 if ($("input#date_of_group").val() == "DD/MM/YYYY" || $("input#date_of_group").val().trim()=="") {
				alert("Please Enter Date of New Pay Group ");
				$("input#date_of_group").focus();
				return false;
			} 
		 	if(getformatedDate($("input#enroll_date").val()) > getformatedDate($("#date_of_group").val())) {
				alert("Date of New Pay Group should Be Greater Than Enrollment Date");
				return false;
		  }
		 	
	var civ_id=$('#civ_id').val();
	
	var enroll_date=$('#enroll_date').val();
	var formdata=$('#form_pay_group').serialize();
	formdata+="&civ_id="+civ_id+"&enroll_date="+enroll_date;	
	   $.post('change_in_pay_group_JcoOr_action?' + key + "=" + value, formdata, function(data){		      
	          if(data=="update")
	        	  alert("Data Updated Successfully");
	          else if(parseInt(data)>0){
	        	
	        	 $('#gp_id').val(data);
	        	  alert("Data Saved Successfully")
	          }
	          else
	        	  alert(data);
	    		          
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});	
}
//************************************* End Class Pay DETAIL*****************************//
</script>


<script>
//*************************************Start Seniority DETAIL*****************************//
function get_seniority_details(){
	  
	 var civ_id=$('#civ_id').val();
	 var i=0;
	  $.post('change_seniority_JCOGetData?' + key + "=" + value, {civ_id:civ_id }, function(j){
			var v=j.length;
			if(v!=0){
				$('#se_id').val(j[i].id);
				 $("#se_authority").val(j[i].se_authority);
				 $("#se_date_authority").val(ParseDateColumncommission(j[i].se_date_authority));
				 $("#reason_for_change").val(j[i].reason_for_change);
				 $("#date_of_seniority").val(ParseDateColumncommission(j[i].date_of_seniority));
			}
	  });
	    
}	

function validate_seniority(){
	
	  if ($("input#se_authority").val().trim()=="") {
			alert("Please Enter Authority");
			$("input#se_authority").focus();
			return false;
		}
	  
	  if(lengthValidation($("input#se_authority").val().trim(),auth_length)){
			alert("Authority Length Should be Less or Equal To 100")
			$("#se_authority").focus();
			return false;
		}
	  
	 if ($("input#se_date_authority").val() == "DD/MM/YYYY" || $("input#se_date_authority").val().trim()=="") {
			alert("Please Enter Date of Authority ");
			$("input#se_date_authority").focus();
			return false;
		} 
	 
	 if(getformatedDate($("input#enroll_date").val()) > getformatedDate($("#se_date_authority").val())) {
			alert("Authority Date should Be Greater Than Enrollment Date");
			return false;
	  }
	 	
	 	 if ($("input#date_of_seniority").val() == "DD/MM/YYYY" || $("input#date_of_seniority").val().trim()=="") {
				alert("Please Enter Date of Seniority ");
				$("input#date_of_seniority").focus();
				return false;
			} 
	 	 
	 	if(getformatedDate($("input#dob_date").val()) > getformatedDate($("#date_of_seniority").val())) {
			alert("Date of Seniority should Be Greater Than Date of Birth");
			return false;
	  }
	 	 
	 	if ($("input#reason_for_change").val().trim()=="") {
			alert("Please Enter  Reason for the Change");
			$("input#reason_for_change").focus();
			return false;
		}
		 	
		 	
	var civ_id=$('#civ_id').val();

	var dob_date=$('#dob_date').val();
	var enroll_date=$('#enroll_date').val();
	var formdata=$('#form_seniority').serialize();
	formdata+="&civ_id="+civ_id+"&enroll_date="+enroll_date+"&dob_date="+dob_date;	
	   $.post('change_in_seniority_JcoOr_action?' + key + "=" + value, formdata, function(data){		      
	          if(data=="update")
	        	  alert("Data Updated Successfully");
	          else if(parseInt(data)>0){
	        	
	        	 $('#se_id').val(data);
	        	  alert("Data Saved Successfully")
	          }
	          else
	        	  alert(data);
	    		          
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});	
}
//************************************* End Seniority DETAIL*****************************//
</script>

<script>
//*************************************Start Attachment DETAIL*****************************//
 function get_attachment_details(){
	  
	 var civ_id=$('#civ_id').val();
	 var i=0;
	  $.post('attachment_JCOGetData?' + key + "=" + value, {civ_id:civ_id }, function(j){
			var v=j.length;
			if(v!=0){
				$('#att_id').val(j[i].id);
				 $("#att_authority").val(j[i].att_authority);
				 $("#att_date_authority").val(ParseDateColumncommission(j[i].att_date_authority));
				 $("#att_movement_number").val(j[i].att_movement_number);
				 $("#att_date_of_move").val(ParseDateColumncommission(j[i].att_date_of_move)); 
				 $("#att_sus_no").val(j[i].att_sus_no);
				 $("#att_place").val(j[i].att_place);
				 $("#att_from").val(ParseDateColumncommission(j[i].att_from));
				 $("#att_to").val(ParseDateColumncommission(j[i].att_to));
				 $("#att_duration").val(j[i].att_duration);
				 $("#att_reasons").val(j[i].att_reasons);
				 
				 var sus_no = j[i].att_sus_no;
					if(sus_no!=""){
    		    		getunit(sus_no);
    		    	}
    		    	
    		    	function getunit(val) {	
    		            $.post("getTargetUnitNameList?"+key+"="+value, {
    		            	sus_no : sus_no
    		        }, function(j) {
    		                
    		                

    		        }).done(function(j) {
    		    				   var length = j.length-1;
    		    	                   var enc = j[length].substring(0,16); 
    		    	                   $("#att_unit_name").val(dec(enc,j[0])); 
    		        }).fail(function(xhr, textStatus, errorThrown) {
    		        });
    		    }
				 
				 
			}
	  });
	    
}	 

function validate_attachment(){
	
	  if ($("input#att_authority").val().trim()=="") {
			alert("Please Enter Authority");
			$("input#att_authority").focus();
			return false;
		}
	  
	  if(lengthValidation($("input#att_authority").val().trim(),auth_length)){
			alert("Authority Length Should be Less or Equal To 100")
			$("#att_authority").focus();
			return false;
		}
	  
	 if ($("input#att_date_authority").val() == "DD/MM/YYYY" || $("input#att_date_authority").val().trim()=="") {
			alert("Please Enter Date of Authority ");
			$("input#att_date_authority").focus();
			return false;
		} 
	 
	 if(getformatedDate($("input#enroll_date").val()) > getformatedDate($("#att_date_authority").val())) {
			alert("Authority Date should Be Greater Than Enrollment Date");
			return false;
	  }
	 
	 if ($("input#att_movement_number").val().trim()=="") {
			alert("Please Enter Movement Order Number");
			$("input#att_movement_number").focus();
			return false;
		}
	 
	 	
	 	 if ($("input#att_date_of_move").val() == "DD/MM/YYYY" || $("input#att_date_of_move").val().trim()=="") {
				alert("Please Enter Date of Movement Order");
				$("input#att_date_of_move").focus();
				return false;
			} 
	 	
	 	if(getformatedDate($("input#tos_date").val()) > getformatedDate($("#att_date_of_move").val())) {
			alert("Date of Movement Order should Be Greater Than Date of TOS");
			return false;
	  }
	 	
	 	if ($("input#att_sus_no").val().trim()=="") {
			alert("Please Enter Attached to (Unit SUS No)");
			$("input#att_sus_no").focus();
			return false;
		}
	 	
	 	if ($("input#att_unit_name").val().trim()=="") {
			alert("Please Enter Unit Name");
			$("input#att_unit_name").focus();
			return false;
		}
	 	 
	 	if ($("input#att_place").val().trim()=="") {
			alert("Please Enter  Place");
			$("input#att_place").focus();
			return false;
		}
	 	
	 	if ($("input#att_from").val() == "DD/MM/YYYY" || $("input#att_from").val().trim()=="") {
			alert("Please Enter From Date");
			$("input#att_from").focus();
			return false;
		} 
 	
 	if(getformatedDate($("input#tos_date").val()) > getformatedDate($("#att_from").val())) {
		alert("From Date should Be Greater Than Date of TOS");
		return false;
  }
 	
 	if ($("input#att_to").val() == "DD/MM/YYYY" || $("input#att_to").val().trim()=="") {
		alert("Please Enter To Date");
		$("input#att_to").focus();
		return false;
	} 
	
	if(getformatedDate($("input#tos_date").val()) > getformatedDate($("#att_to").val())) {
	alert("To Date should Be Greater Than Date of TOS");
	return false;
}
	
	if ($("input#att_duration").val().trim()=="") {
		alert("Please Enter Duration");
		$("input#att_duration").focus();
		return false;
	}
	
	if ($("input#att_reasons").val().trim()=="") {
		alert("Please Enter Reasons");
		$("input#att_reasons").focus();
		return false;
	}
		 	
	var civ_id=$('#civ_id').val();

	var tos_date=$('#tos_date').val();
	var enroll_date=$('#enroll_date').val();
	var formdata=$('#form_attachment').serialize();
	formdata+="&civ_id="+civ_id+"&enroll_date="+enroll_date+"&tos_date="+tos_date;	
	   $.post('attachment_details_JcoOr_action?' + key + "=" + value, formdata, function(data){		      
	          if(data=="update")
	        	  alert("Data Updated Successfully");
	          else if(parseInt(data)>0){
	        	
	        	 $('#att_id').val(data);
	        	  alert("Data Saved Successfully")
	          }
	          else
	        	  alert(data);
	    		          
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});	
}


$("#att_sus_no")
.keyup(
		function() {
			var sus_no = this.value;
			var susNoAuto = $("#att_sus_no");
      
			susNoAuto
					.autocomplete({
						source : function(request, response) {
							$
									.ajax({
										type : 'POST',
										url : "getTargetSUSNoList?"
												+ key + "=" + value,
										data : {
											sus_no : sus_no
										},
										success : function(data) {
											var susval = [];
											var length = data.length - 1;
											var enc = data[length]
													.substring(0,
															16);
											for (var i = 0; i < data.length; i++) {
												susval.push(dec(
														enc,
														data[i]));
											}
											var dataCountry1 = susval
													.join("|");
											var myResponse = [];
											var autoTextVal = susNoAuto
													.val();
											$
													.each(
															dataCountry1
																	.toString()
																	.split(
																			"|"),
															function(
																	i,
																	e) {
																var newE = e
																		.substring(
																				0,
																				autoTextVal.length);
																if (e
																		.toLowerCase()
																		.includes(
																				autoTextVal
																						.toLowerCase())) {
																	myResponse
																			.push(e);
																}
															});
											response(myResponse);
										}
									});
						},
						minLength : 1,
						autoFill : true,
						change : function(event, ui) {
							if (ui.item) {
								return true;
							} else {
								alert("Please Enter Approved Unit SUS No");
								document
										.getElementById("att_unit_name").value = "";
								susNoAuto.val("");
								susNoAuto.focus();
								return false;
							}
						},
						select : function(event, ui) {
							var sus_no = ui.item.value;
							$.post(
									"getTargetUnitsNameActiveList?" + key
											+ "=" + value, {
										sus_no : sus_no
									}, function(j) {

									}).done(
									function(j) {
										var length = j.length - 1;
										var enc = j[length]
												.substring(0, 16);
										$("#att_unit_name").val(
												dec(enc, j[0]));

									}).fail(
									function(xhr, textStatus,
											errorThrown) {
									});
						}
					});
		});
		
function search_unit(obj,id){
// 	removeMinDate();
	var unit_name = obj.value;
	 var susNoAuto=$("#"+obj.id);
	  susNoAuto.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
	        	type: 'POST',
		    	url: "getTargetUnitsNameActiveList_postin?"+key+"="+value,
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
	        	  $("#"+id).val('');
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
			        	$("#att_sus_no").val(dec(enc,data[0]));
		            }).fail(function(xhr, textStatus, errorThrown) {
		            });
	      } 	     
	}); 			
}
		
/* function calc_duration() {
		if($('#att_from').val().trim() != "" && $('#att_from').val() != "DD/MM/YYYY" && $('#att_to').val().trim() != "" && $('#att_to').val() != "DD/MM/YYYY") {
			if(getformatedDate($('#att_from').val()) > getformatedDate($('#att_to').val())) {
				alert("Invalid Date Range");
				$('#att_duration').val('');
				$("input#att_to" + f).focus();
				return false;
			}
			var startDate = getformatedDate($('#att_from').val());
			var endDate = getformatedDate($('#att_to').val());
			
			
			 var startYear = startDate.getFullYear();
			    var february = (startYear % 4 === 0 && startYear % 100 !== 0) || startYear % 400 === 0 ? 29 : 28;
			    var daysInMonth = [31, february, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];

			    var yearDiff = endDate.getFullYear() - startYear;
			    var monthDiff = endDate.getMonth() - startDate.getMonth();
			    if (monthDiff < 0) {
			        yearDiff--;
			        monthDiff += 12;
			    }
			    var dayDiff = endDate.getDate() - startDate.getDate();
			    if (dayDiff < 0) {
			        if (monthDiff > 0) {
			            monthDiff--;
			        } else {
			            yearDiff--;
			            monthDiff = 11;
			        }
			        dayDiff += daysInMonth[startDate.getMonth()];
			    }
		    
			var message;
			   if ( (yearDiff == 0) && (monthDiff == 0) && (dayDiff == 0) )  {
				message = "0 years 0 months 1 days";
			} else {
				message = yearDiff + " years "
				message += monthDiff + " months "
				message += dayDiff + " days"
			}
			$('#att_duration').val(message);
		}
	} */

	
	function calc_duration() {
		if($('#att_from').val().trim() != "" && $('#att_from').val() != "DD/MM/YYYY" && $('#att_to').val().trim() != "" && $('#att_to').val() != "DD/MM/YYYY") {
			if(getformatedDate($('#att_from').val()) > getformatedDate($('#att_to').val())) {
				alert("Invalid Date Range");
				$('#att_duration').val('');
				$("input#att_to" + f).focus();
				return false;
			}
			var startDate = getformatedDate($('#att_from').val());
			var endDate = getformatedDate($('#att_to').val());
			
			 var timeDiff = Math.abs(endDate.getTime() - startDate.getTime());
		        var diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24)); 
		    

		        diffDays +=  " days"
			
			$('#att_duration').val(diffDays);
		}
	}


//************************************* End Attachment DETAIL*****************************//
</script>
<script>
function validate_save_tenure_details(){

var ten_from=$('#ten_from').val();
var ten_to=$('#ten_to').val();
		if ($("input#unit_sus_no").val() == "") {
			alert("Please Enter Unit Sus No");
			$("input#unit_sus_no").focus();
			return false;
		} 
 		if ($("input#unit_posted_to").val() == "") {
			alert("Please Enter Unit name");
			$("input#unit_posted_to").focus();
			return false;
		}
	 
		if ($("#ten_to").val() == "DD/MM/YYYY" || $("#ten_to").val().trim()=="") {
			alert("Please Select From Date");
			$("#ten_to").focus();
			return false;
		} 
	
 		if ($("#ten_from").val() == "DD/MM/YYYY" || $("#ten_from").val().trim()=="") {
			alert("Please Select To Date");
			$("#ten_from").focus();
			return false;
		} 
		
  var formvalues=$("#form_tenure_details_form").serialize();
  
	var civ_id =$("#civ_id").val();
	var ten_id=$("#ten_id").val();	
	
	formvalues+="&ten_id="+ten_id+"&civ_id="+civ_id;	
	$.post('tenure_details_action_civ?' + key + "=" + value,formvalues, function(data){
		
			 if(data=="update")
	        	  alert("Data Updated Successfully");
	          else if(parseInt(data)>0)
	          {
	        	  $("#ten_id").val(data);	
	        	  alert("Data Saved Successfully")
	          }
	          else
	        	  alert(data)
	 	  		}).fail(function(xhr, textStatus, errorThrown) 
	 	  			{
	 	  			alert("fail to fetch")
	 	  		
	  		});
}
function get_changetenure_details(){
	var civ_id=$("#civ_id").val();	
	$.post('tenure_details_GetData_Civ?' + key + "=" + value,{civ_id:civ_id }, function(j){
		var v=j.length;
			if(v!=0){
				
				$("#unit_sus_no").val(j[0].sus_no);
				$('#unit_posted_to').val(j[0].unit_name); 
				
				$('#ten_to').val(ParseDateColumncommission(j[0].to_date)); 
				$('#ten_from').val(ParseDateColumncommission(j[0].from_date)); 
		       
					
					
				$("#ten_id").val(j[0].id);
			}
		  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
			});
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
             $("#unit_posted_to").val(dec(enc,j[0]));   
                 
         }).fail(function(xhr, textStatus, errorThrown) {
         });
		} 	     
	});	
});

// unit name
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
///////////////// Start  Pop UP History //////////

function Pop_Up_History(a) {

	var x = screen.width/2 - 1100/2;
    var y = screen.height/2 - 900/2;
    popupWindow = window.open("", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');
	window.onfocus = function () { 
	}
	civ_id = $("#civ_id").val();
	census_id = $("#census_id").val();

	
	
	if(a == "update_child_dtl"){
		$("#updatechild_civ_id").val(civ_id);
		document.getElementById('update_child_dtlForm').submit();
	}
	
	if(a == "change_nok"){
		$("#nok_civ_id").val(civ_id);
		document.getElementById('nok_updateForm').submit();
	}
	if(a == "change_des"){
		$("#des_civ_id").val(civ_id);
		document.getElementById('des_updateForm').submit();
	}
	
	
	if(a == "change_tenure"){
		$("#ten_civ_id").val(civ_id);
		document.getElementById('ten_updateForm').submit();
	}

	if(a == "marital_status"){
		$("#maritalstatus_civ_id").val(civ_id);
		$("#Change_In_Marital_Status_Form").submit();
	}
	
	
	if(a == "Update_Qualification"){
		$("#update_qualification_civ_id").val(civ_id);
		document.getElementById('Update_Qualification_Form').submit();
	}
	
	
	if(a == "change_in_address"){
		$("#address_civ_id").val(civ_id);
		document.getElementById('change_in_addressForm').submit();
	}


	

		
}



///////////////// End  Pop UP History //////////

</script>
 	


<!-- CURL -->

<!-- /////////// Update_Child_Details_Form //////////////// -->
<c:url value="Update_Child_DetailsCivUrl" var="Update_Child_DetailsCivUrl" />
<form:form action="${Update_Child_DetailsCivUrl}" method="post" id="update_child_dtlForm"
	name="update_child_dtlForm" target="result">
 <input type="hidden" name="updatechild_civ_id" id="updatechild_civ_id" value="0" />  

</form:form>

<!-- /////////// Change In Nok //////////////// -->
<c:url value="Nok_Popup_CIVTiles" var="Nok_Popup_CIVTiles" />
<form:form action="${Nok_Popup_CIVTiles}" method="post" id="nok_updateForm"
	name="nok_updateForm" target="result">
<input type="hidden" name="nok_civ_id" id="nok_civ_id" value="0" /> 
</form:form>

<c:url value="Des_Popup_CIVTiles" var="Des_Popup_CIVTiles" />
<form:form action="${Des_Popup_CIVTiles}" method="post" id="des_updateForm"
	name="des_updateForm" target="result">
<input type="hidden" name="des_civ_id" id="des_civ_id" value="0" /> 
</form:form>

<c:url value="Ten_Popup_CIVTiles" var="Ten_Popup_CIVTiles" />
<form:form action="${Ten_Popup_CIVTiles}" method="post" id="ten_updateForm"
	name="ten_updateForm" target="result">
<input type="hidden" name="ten_civ_id" id="ten_civ_id" value="0" /> 
</form:form>


<!--  ///////// MaritaL Status//////////////// -->
<c:url value="Change_in_Marital_StatusCivUrl" var="Change_in_Marital_StatusCivUrl" />
<form:form action="${Change_in_Marital_StatusCivUrl}" method="post" id="Change_In_Marital_Status_Form" name="Change_In_Marital_Status_Form" modelAttribute="id" target="result">
<input type="hidden" name="maritalstatus_civ_id" id="maritalstatus_civ_id" value="0" />
</form:form>

<!-- /////////////////// Update Qualification//////////////// -->
<c:url value="Update_Qualification_Popup_CIVTiles" var="Update_Qualification_Popup_CIVTiles" />
<form:form action="${Update_Qualification_Popup_CIVTiles}" method="post" id="Update_Qualification_Form" name="Update_Qualification_Form"  target="result">
<input type="hidden" name="update_qualification_civ_id" id="update_qualification_civ_id" value="0"/>
</form:form>


<!-- /////////// Change_In_Address_Form //////////////// -->
<c:url value="Address_Popup_CivTiles" var="Address_Popup_CivTiles" />
<form:form action="${Address_Popup_CivTiles}" method="post" id="change_in_addressForm"
	name="change_in_addressForm" target="result">
 <input type="hidden" name="address_civ_id" id="address_civ_id" value="0" />  
</form:form>


	
