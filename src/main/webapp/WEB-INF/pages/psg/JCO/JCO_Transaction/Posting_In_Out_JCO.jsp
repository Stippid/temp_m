<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>

<div class="animated fadeIn">
	<div class="container" align="center">
		<div class="card">
			<form>				
				<div class="card-header">
					<h5>POSTING IN/OUT INDL</h5>
					<h6 class="enter_by">
						<span style="font-size: 12px; color: red;">(To be entered by UNIT)</span>
					</h6>
				</div>
			<div class="card-body card-block">	   
				<div class="col-md-12">
						 <div class="col-md-6" id=""  >
							<div class="row form-group">
								<div class="col-md-4">
									<strong style="color: red;">  </strong><label class=" form-control-label"> Action </label>
								</div>
								<div class="col-md-8">									
									<select name="type" id="type" class="form-control"   >
										<option value="">--Select--</option>
										<c:forEach var="item" items="${getPostINOutstatusList}" varStatus="num">
										<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>											      
									<input type="hidden" id="jco_id" name="jco_id" class="form-control autocomplete" autocomplete="off">	
								</div>						
							</div>
						</div>					
					</div>
			 </div>					 
		</form>

	<!-- POST OUT FORM START  -->
			
	<div id="div_post_out" class="tabcontent" style="display:none ;">
		<form id="post_out_form">
					<div class="card">
						<div class="card-header">
							<h5>POST OUT</h5>
							<h6 class="enter_by">
								<span style="font-size: 12px; color: red;"></span>
							</h6>
						</div>
					<div class="card-body card-block"><br>
					 <input type="hidden" id="h_id" name="h_id"	class="form-control autocomplete" autocomplete="off" value="0"> 
						<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> From SUS No </label>
								</div>
								<div class="col-md-8">							
										 <input type="text" id="from_sus_no" name="from_sus_no" onkeyup="search_sus1from(this,'unit_name') ; specialcharecter(this)" tabindex="-1" onchange="To_sus_check()"
										class="form-control autocomplete" autocomplete="off" maxlength="8" onkeypress="return AvoidSpace(event)" >									 
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> From Unit Name </label>
								</div>
								<div class="col-md-8">												
										 <input type="text" id="unit_name" name="unit_name" onkeyup="search_unit1_from(this,'from_sus_no') ; specialcharecter(this)" tabindex="-1" 
										class="form-control autocomplete" maxlength="50" autocomplete="off" onchange="To_sus_check();">
									 
							 									 							 	
								</div>
							</div>
						</div> 
					</div>
					<div class="col-md-12">
						<div class="col-md-4">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Army No </label>
								</div>
								<div class="col-md-8">
								
									<input type="text" id="personnel_no" name="personnel_no" class="form-control autocomplete" autocomplete="off"
										onchange="personal_number()" onkeyup="getPersonnel_no(this,'from_sus_no') ; specialcharecter(this)"
										maxlength="12" onkeypress="return AvoidSpace(event)">
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Name </label>
								</div>
								<div class="col-md-8">
									<label id="name" name="name"> </label>
									
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Rank </label>
								</div>
								<div class="col-md-8">
									<label id="rank" name="rank"> </label> 
									<input type="hidden" id="rank1" name="rank1" class="form-control autocomplete"
										autocomplete="off">
								</div>
							</div>
						</div>				
					</div>					
					<div class="col-md-12" style="display:none">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Appointment </label>

								</div>
								<div class="col-md-8">
									<label id="out_app_name" name="out_app_name"> </label> 
								
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Date of Appointment</label>
								</div>
								<div class="col-md-8">
									<label id="out_app_dt" name="out_app_dt"> </label> 
									
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Auth No </label>
								</div>
								<div class="col-md-8">
										<input type="text" id="out_auth" name="out_auth" class="form-control autocomplete" maxlength="100" onkeyup="return specialcharecter(this)" autocomplete="off">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Auth Date </label>
								</div>
									<div class="col-md-8">										
										<input type="text" name="out_auth_dt" id="out_auth_dt" maxlength="10" value="DD/MM/YYYY" onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
										onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
										onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" />
								</div>
							</div>
						</div>
					</div>									
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> To SUS No </label>
								</div>
								<div class="col-md-8">								
								
								<input type="text" id="o_to_sus_no" name="to_sus_no" onkeyup="search_sus(this,'unit_name1') ; specialcharecter(this)"
										class="form-control autocomplete" autocomplete="off" onchange="To_sus_check();" maxlength="8" onkeypress="return AvoidSpace(event)" >
								
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> To Unit Name </label>
								</div>
								<div class="col-md-8">
				
								<input type="text" id="unit_name1" name="unit_name1" onkeyup="search_unit(this,'o_to_sus_no') ; specialcharecter(this)"
										class="form-control autocomplete" autocomplete="off" maxlength="50" onchange="To_sus_check();" >
									
								
								</div>
							</div>
						</div>
					</div>					
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Date of SOS </label>
								</div>
								<div class="col-md-8">									
									<input type="text" name="dt_of_sos" id="dt_of_sos" maxlength="10" value="DD/MM/YYYY" onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
										onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
										onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" />
								</div>
							</div>
						</div>						
						 <!-- <div class="col-md-6" id="" >
							<div class="row form-group">
								<div class="col-md-4">
									<strong style="color: red;"></strong><label class=" form-control-label"> Remarks </label>
								</div>
								<div class="col-md-8">
                                   <textarea id="unit_description_out" name="unit_description_out" class="form-control autocomplete" autocomplete="off" maxlength="250" onkeyup="return specialcharecter(this)"></textarea>
								</div>
							</div>
						</div>	 -->				
					</div>
					
					
				<div class="col-md-12">						 
						 <div class="col-md-6" id="stats_out" style="display: none;">
							<div class="row form-group">
								<div class="col-md-4">
									<strong style="color: red;"> </strong><label class=" form-control-label">T Status </label>
								</div>
								<div class="col-md-8">
									<select name="t_status" id="t_status_out" class="form-control"   >
										<option value= "0">--Select--</option>
										<c:forEach var="item" items="${getstatusList}" varStatus="num">
										<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						
					</div>
				 </div>
					<div class="card-footer" align="center" class="form-control">
						<a href="Posting_In_Out_JCOUrl" class="btn btn-success btn-sm">Clear</a> 
						<input type="button" class="btn btn-primary btn-sm" id="save_postout_bt" value="Save" onclick="save_postout();">
					</div>
				</div>
			</form>
		</div>
<!-- POST OUT FORM END  -->
			
<!-- POST IN FORM START  -->			
	<div id="div_post_in" class="tabcontent" style="display:none ;">
		<form id="post_in_form">
					<div class="card">
						<div class="card-header">
							<h5>POST IN</h5>
							<h6 class="enter_by">
								<span style="font-size: 12px; color: red;"></span>
							</h6>
						</div>
					<div class="card-body card-block"><br>
					
								<div class="col-md-12">
						<div class="col-md-4">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Army No </label>
									<input type="hidden" id="i_id" name="i_id"	class="form-control autocomplete" autocomplete="off" value="0"> 					
								</div>
								<div class="col-md-8">
										
										<input type="text" id="in_personnel_no" name="personnel_no"
										class="form-control autocomplete" autocomplete="off"
										
										onchange="Inpersonal_number();" maxlength="12" onkeypress="return AvoidSpace(event)">
								</div>
							</div>
						</div>
					 	<div class="col-md-4">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Name </label>
								</div>
								<div class="col-md-8">
									<label id="iname" name="name"> </label>
									
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Rank </label>
								</div>
								<div class="col-md-8">
									<label id="rank2" name="rank"> </label>								
								</div>
							</div>
						</div>					
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> From SUS No </label>
								</div>
								<div class="col-md-8">													
								<input type="text" id="i_from_sus_no" name="from_sus_no" onkeyup="search_sus1from(this,'unit_nameIn') ; specialcharecter(this)" class="form-control autocomplete" autocomplete="off" 
									maxlength="8" onchange="from_sus_check()" onkeypress="return AvoidSpace(event)">
								
								</div>
							</div>
						</div>
						 <div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> From Unit Name </label>
								</div>
								<div class="col-md-8">									
								 <input type="text" id="unit_nameIn" name="unit_nameIn" onkeyup="search_unit1_from(this,'i_from_sus_no') ; specialcharecter(this)"
										class="form-control autocomplete" maxlength="50" autocomplete="off" onchange="from_sus_check()">						 										 						 	
								</div>
							</div>
						</div> 
					</div>
		
					<div class="col-md-12" style="display:none">
					 	<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Appointment </label>
								</div>
								<div class="col-md-8">								
 									<label id="app_name" name="app_name"> </label>								
 								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Date of Appointment </label>
 								</div>
 								<div class="col-md-8"> 
									<label id="app_dt" name="app_dt"> </label>
									 <input type="hidden" id="app_dt" name="app_dt" class="form-control autocomplete" autocomplete="off">
 								</div> 
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Auth No </label>
								</div>
								<div class="col-md-8">
									<input type="text" id="in_auth" name="in_auth" class="form-control autocomplete" 
									maxlength="100" onkeyup="return specialcharecter(this)" autocomplete="off">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Auth Date </label>
								</div>
								<div class="col-md-8">															
								<input type="text" name="in_auth_dt" id="in_auth_dt" maxlength="10" value="DD/MM/YYYY" onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
										onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
										onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" />										
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> To SUS No </label>
								</div>
								<div class="col-md-8">
								<input type="text" id="i_to_sus_no" name="to_sus_no"  onkeyup="search_sus(this,'unit_nameIn1') ; specialcharecter(this)" tabindex="-1" class="form-control autocomplete" autocomplete="off" 
									maxlength="8" onchange="from_sus_check()" onkeypress="return AvoidSpace(event)">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> To Unit Name </label>
								</div>
								<div class="col-md-8">																																		
								<input type="text" id="unit_nameIn1" name="unit_nameIn1" onkeyup="search_unit(this,'i_to_sus_no') ; specialcharecter(this)" tabindex="-1" 
									class="form-control autocomplete" autocomplete="off" maxlength="50" onchange="from_sus_check()" >														
								
								</div>
							</div>
						</div>
					</div>					 
				  <div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Date of TOS </label>
								</div>
								<div class="col-md-8">
										<input type="text" name="dt_of_tos" id="dt_of_tos" maxlength="10" value="DD/MM/YYYY" onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
										onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
										onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" />
								</div>
							</div>
						</div>
					  <!-- <div class="col-md-6" id=""  >
							<div class="row form-group">
								<div class="col-md-4">
									<strong style="color: red;">  </strong><label class=" form-control-label"> Remarks </label>
								</div>
								<div class="col-md-8">
									<label id="unit_description_IN"> </label>
								</div>
							 </div>
						</div> -->
					</div> 
				</div>  	
				<div class="col-md-12"> 
					 <div class="col-md-6" id="stats" style="display: none">
						<div class="row form-group">
							<div class="col-md-4">
								<strong style="color: red;"> </strong><label class=" form-control-label"> T Status </label>
							</div>
							<div class="col-md-8">
									<select name="t_status" id="t_status" class="form-control"   >
										<option value= "0">--Select--</option>
										<c:forEach var="item" items="${getstatusList}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
								</select>
							</div>
						</div>
					</div>	
				</div>					 
				 <div class="col-md-12" id="xlist" style="display: none" >
					<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<strong style="color: red;"></strong><label class=" form-control-label"> X List SUS No </label>
								</div>
								<div class="col-md-8">
									 <input type="text"   id="x_sus_no" name="x_sus_no" value=""
										class="form-control autocomplete" autocomplete="off">   				  			 
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<strong style="color: red;">  </strong><label class=" form-control-label"> X List Loc </label>
								</div>
								<div class="col-md-8">
									 <textarea    id="x_list_loc" name="x_list_loc" value=""
										class="form-control autocomplete" autocomplete="off">   </textarea>
								</div>
							</div>
						</div>
					</div>				 				  			 
						<div class="card-footer" align="center" class="form-control">
							<a href="Posting_In_Out_JCOUrl" class="btn btn-success btn-sm">Clear</a> 
							<input type="button" class="btn btn-primary btn-sm" value="Save" onclick="save_postin();">
						</div>
					</div>
				</form>
			</div>
		<!-- POST IN FORM END  -->			 		
			</div>
		</div>
	</div>
	 	 				 
<script>
	$(document).ready(function() {
		$.ajaxSetup({
			async : false
		});
				
		var roleSusNo='${roleSusNo}';	
		if(roleSusNo!='' && roleSusNo!=null){
			$.post("getTargetUnitNameList?"+key+"="+value, {sus_no:roleSusNo}).done(function(data) {
    	  		var l=data.length-1;
    	  		var enc = data[l].substring(0,16);    	   	 
    	  	 	$("#unit_nameIn1").val(dec(enc,data[0]));
    	  	 	$("#unit_name").val(dec(enc,data[0]));
    	  			
    	  		}).fail(function(xhr, textStatus, errorThrown) {
    	  });

			$('#i_to_sus_no').val(roleSusNo);
			$('#i_to_sus_no').attr('readonly',true);
			$('#from_sus_no').val(roleSusNo);
			$('#from_sus_no').attr('readonly',true);
			$('#unit_name').attr('readonly',true);
			$('#unit_nameIn1').attr('readonly',true);
		}
	
		   $('#type').on('change', function() {
		      if ( $('#type').val() == 'postout'  && $('#service_category').val() != '0') {
		    	  $("#div_post_out").show();
		    	  $("#div_post_in").hide();
		    	  
		    	   var sus_no = '${roleSusNo}';
		    	  	$.post("getTargetUnitNameList?"+key+"="+value, {sus_no:sus_no}).done(function(data) {
		    	  		var l=data.length-1;
		    	  		var enc = data[l].substring(0,16);    	   	 
		    	  	 	$("#unit_name").val(dec(enc,data[0]));
		    	  			
		    	  		}).fail(function(xhr, textStatus, errorThrown) {
		    	  });
		    	  
		      }
		      if ( $('#type').val() == 'postin' && $('#service_category').val() != '0') {
		    	  $("#div_post_in").show();
		    	  $("#div_post_out").hide();
		      }
		      if ( $('#type').val() == '' || $('#service_category').val() == '0') {
		    	  $("#div_post_in").hide();
		    	  $("#div_post_out").hide();
		      }
		      fn_service_category_change();
		 	});	
		   $('#service_category').on('change', function() {
		      if ( $('#type').val() == 'postout'  && $('#service_category').val() != '0') {
		    	  $("#div_post_out").show();
		    	  $("#div_post_in").hide();
		    	  
		    	   var sus_no = '${roleSusNo}';
		    	  	$.post("getTargetUnitNameList?"+key+"="+value, {sus_no:sus_no}).done(function(data) {
		    	  		var l=data.length-1;
		    	  		var enc = data[l].substring(0,16);    	   	 
		    	  	 	$("#unit_name").val(dec(enc,data[0]));
		    	  			
		    	  		}).fail(function(xhr, textStatus, errorThrown) {
		    	  });		    	  
		      }
		      if ( $('#type').val() == 'postin' && $('#service_category').val() != '0') {
		    	  $("#div_post_in").show();
		    	  $("#div_post_out").hide();
		      }
		      if ( $('#type').val() == '' || $('#service_category').val() == '0') {
		    	  $("#div_post_in").hide();
		    	  $("#div_post_out").hide();
		      }
		      fn_service_category_change();
		 });	
		   
		   jQuery(function($){ //on document.ready 
		  	 datepicketDate('out_auth_dt1');
		   	 datepicketDate('out_auth_dt');
			 datepicketDate('dt_of_sos');
			 datepicketDate('dt_of_tos');
			 datepicketDate('in_auth_dt');			
			 datepicketDate('app_dt');							 
			});   
		try{
			if(window.location.href.includes("msg=")){
				var url = window.location.href.split("?")[0];
			    window.location = url;
		    } 
		}catch (e) {
			// TODO: handle exception
		}							   
	});
</script>

<script>
/* Post In Start Save*/
	function save_postin() {
		var personnel_no = $('#in_personnel_no').val();
		var rank = $('#rank_in').val();
		var from_sus_no = $('#i_from_sus_no').val();
		var to_sus_no = $('#i_to_sus_no').val();
		var app_name = $('#app_in').val();
		var dt_of_tos = $('#dt_of_tos').val();
		var t_status = $('#t_status').val();
		var x_sus_no = $('#x_sus_no').val();
		var x_list_loc = $('#x_list_loc').val();
		var jco_id = $('#jco_id').val();
		var i_id = $('#i_id').val();
		var in_auth = $('#in_auth').val();
		var in_auth_dt = $('#in_auth_dt').val();
		
		if ($("input#in_personnel_no").val() == "") {
			alert("Please Select Army No");
			$("input#in_personnel_no").focus();
			return false;
		}	
		
		if ($("input#i_from_sus_no").val() == "") {
			alert("Please Enter From Sus No");
			$("input#i_from_sus_no").focus();
			return false;
		}			
			 		
		if ($("input#in_auth").val().trim() == "") {
			alert("Please Enter Auth No");
			$("input#in_auth").focus();
			return false;
		}	
		if ($("input#in_auth_dt").val() == "") {
			alert("Please Enter Auth Date");
			$("input#in_auth_dt").focus();
			return false;
		}
		if($("#in_auth_dt").val()== "DD/MM/YYYY"){
			alert("Please Select Auth Date");
			$("input#in_auth_dt").focus();
			return false;
		}
		if ($("input#i_to_sus_no").val() == "") {
			alert("Please Enter  To SUS No ");
			$("input#i_to_sus_no").focus();
			return false;
		}
		if ($("input#unit_nameIn1").val() == "") {
			alert("Please Enter  To Unit Name ");
			$("input#unit_nameIn1").focus();
			return false;
		}
		if($("#dt_of_tos").val()== "DD/MM/YYYY"){
			alert("Please Enter TOS Date");
			$("input#dt_of_tos").focus();
			return false;
		}
		if ($("input#dt_of_tos").val() == "") {
			alert("Please Enter Date of TOS");
			$("input#dt_of_tos").focus();
			return false;
		}		
		if(dt_tos!=null && dt_tos!=""){					 
			var newtos = dt_of_tos.split("/");
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
					
		if(comm_date!=''){
			if(getformatedDate(in_auth_dt) < getformatedDate(comm_date)) {
				alert("Date of Auth Should Be Greater Then Date of Commission");
				$("input#in_auth_dt").focus();
				return false;
			}
		}
 
		if(comm_date!=''){
			if(getformatedDate(dt_of_tos) <= getformatedDate(comm_date)) {
				alert("TOS Should Be Greater Or Equal To Date of Commission");
				$("input#dt_of_tos").focus();
				return false;
			}
		}
		
		var prefix_per = $("#in_personnel_no").text().substring(0,2);
		var appname = $("#app_name").val();
		 if (prefix_per == "NT" && $("#t_status").val() == "" ) {
	 		 alert("Please Select The Status");
	 		 $("#t_status").focus();
	 		 return false;
	 		 }
		
		if( $("#x_sus_no").val() == "" &&  appname == "1" || appname == "ON COURSE ABROAD" || appname == "ON COURSE EXCEEDING 10 WEEKS" 
				|| appname == "ON DEPUTATION" || appname == "ON FURLOUGH LEAVE" || appname == "ON LEAVE PENDING RETIREMENT R/R" 
				|| appname == "ON STUDY LEAVE"  ){
			 alert("Please Enter The X Sus No");
	 		 $("#x_sus_no").focus();
	 		 return false;	
		} 
			if($("#x_list_loc").val() == "" && appname == "1" || appname == "ON COURSE ABROAD" || appname == "ON COURSE EXCEEDING 10 WEEKS" 
				|| appname == "ON DEPUTATION" || appname == "ON FURLOUGH LEAVE" || appname == "ON LEAVE PENDING RETIREMENT R/R" 
				|| appname == "ON STUDY LEAVE" ){
			 alert("Please Enter The X Location");
				 $("#x_list_loc").focus();
				 return false;	
		}
		 
		var saveUrl;
		
			saveUrl='Posting_In_JcoAction?'					
		
		$.post(saveUrl + key + "=" + value, {personnel_no : personnel_no,	rank : rank, from_sus_no : from_sus_no , to_sus_no : to_sus_no, 
			app_name : app_name,dt_of_tos : dt_of_tos,t_status : t_status,x_sus_no :x_sus_no,x_list_loc :x_list_loc,
			jco_id : jco_id,i_id : i_id	,in_auth : in_auth,in_auth_dt : in_auth_dt},
				function(data) {
         
			if (data == "update")
				alert("Data Updated Successfully");

			else if (parseInt(data) > 0) {
				alert("Data saved successfully.");
				fn_service_category_change();
			} else
				alert(data);						
		}).fail(function(xhr, textStatus, errorThrown) {
			alert("fail to fetch")
		});

		return true;
	}

//X SUS No
	$("#x_sus_no").keyup(function(){
		var sus_no = this.value;
		var susNoAuto=$("#x_sus_no");

		susNoAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        type: 'POST',
		        url: "getTargetSUSNoList_postin?"+key+"="+value,
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
	 
function Inpersonal_number() {
	fn_getOfficerCensusIn();	
 }
/* Post In End Save*/
</script>

 <script>
	function appNamechng(){		 
		var appname = $("#app_name").val();
		  
			if(appname == "1" || appname == "ON COURSE ABROAD" || appname == "ON COURSE EXCEEDING 10 WEEKS" 
					|| appname == "ON DEPUTATION" || appname == "ON FURLOUGH LEAVE"
					|| appname == "ON LEAVE PENDING RETIREMENT R/R" || appname == "ON STUDY LEAVE"){
				$("#xlist").show();
				}
			else{
				$("#xlist").hide();
				$("#x_sus_no").val("");
				$("#x_list_loc").val("");		 
		}
	}
	function statusPER(){		 
		var prefix_per = $("#in_personnel_no").val().substring(0,2);		 
			if(prefix_per == "NT"){
				 $("#stats").show();
				}
			else{
				 $("#stats").hide();
				 $("#t_status").val("");
			}	 	
	}
	function removeMinDate(){		
		setMinDate('out_auth_dt', '01/01/1890');
		setMinDate('in_auth_dt', '01/01/1890');
		setMinDate('dt_of_sos', '01/01/1890');
		setMinDate('dt_of_tos', '01/01/1890');	
	}
	function To_sus_check() {
		 var to_sus_no = $('#o_to_sus_no').val(); 
		 var from_sus_no = $('#from_sus_no').val(); 				
		if(to_sus_no==from_sus_no && to_sus_no!='' && from_sus_no!=''){	
			alert("From And To Sus Can't Be Same");
			$('#o_to_sus_no').val('');
			$('#unit_name1').val('');
		}		
		if(from_sus_no=='' ||  $('#unit_name').val()==''){
			 $("#in_personnel_no").val('');
			 $("#personnel_no").val('');
		}		
	}
	function from_sus_check() {		
		var from_sus_no = $('#i_from_sus_no').val(); 
		var to_sus_no = $('#i_to_sus_no').val(); 
		
	if(from_sus_no==to_sus_no && to_sus_no!='' && from_sus_no!='')	 {		
		alert("From And To Sus Can't Be Same");
		if('${roleSusNo}'!='' && '${roleSusNo}'!=null){
			 $('#i_from_sus_no').val(''); 
			 $('#unit_nameIn').val(''); 
		}
		else{
		$('#i_to_sus_no').val('');
		$('#unit_nameIn1').val('');
		}
	}
	if(from_sus_no=='' ||  $('#unit_nameIn').val()==''){
		 $("#in_personnel_no").val('');
		 $("#personnel_no").val('');
	}
}
</script>
 
<script>
/* Post Out Start Save*/
	function save_postout() {		
		var personnel_no = $('#personnel_no').val();
		var name = '${name}';
		var rank = $('#rank1').val();		 
		var out_auth = $('#out_auth').val();
		var out_auth_dt = $('#out_auth_dt').val();
		var to_sus_no = $('#o_to_sus_no').val();
		var from_sus_no = $('#from_sus_no').val();
 		var dt_of_sos = $('#dt_of_sos').val();
		var jco_id = $('#jco_id').val();
		var unit_description = $('#unit_description_out').val();
		var t_status_out = $('#t_status_out').val(); 
		 var h_id = $('#h_id').val();
	 	 
		 if ($("input#personnel_no").val() == "") {
			 alert("Please select Army No");
			 $("input#personnel_no").focus();
			 return false;
		 }	
		 var out_auth_dt = $('#out_auth_dt').val();			
		 if ($("input#out_auth").val().trim() == "") {
			alert("Please Enter Auth No");
			$("input#out_auth").focus();
			return false;
		 }
		 if($("#out_auth_dt").val().trim() == "DD/MM/YYYY"){
			 alert("Please Enter Auth Date");
			 $("input#out_auth_dt").focus();
			 return false;
		 }
		if ($("input#out_auth_dt").val().trim()  == "") {
			alert("Please Select Auth Date");
			$("input#out_auth_dt").focus();
			return false;
		}
		if ($("input#o_to_sus_no").val() == "") {
			alert("Please Select To SUS No");
			$("input#o_to_sus_no").focus();
			return false;
		}			 
		if($("#dt_of_sos").val()== "DD/MM/YYYY"){
			alert("Please Select Date of SOS");
			$("input#dt_of_sos").focus();
			return false;
		 }
		if ($("input#dt_of_sos").val() == "") {
			alert("Please Select Date of SOS");
			$("input#dt_of_sos").focus();
			return false;
		}
		
		if(dt_tos!=null && dt_tos!=""){
			var newtos = dt_of_sos.split("/");
			var pretos=dt_tos.split("/");			
			var newtosM=Number(newtos[1]);
			var newtosY=newtos[2];			
			var pretosM=Number(pretos[1]);
			var pretosY=pretos[2];
			
			if(newtosY==pretosY){
				if(newtosM<=pretosM){
					alert("Invalid Date of SOS");
					$("input#dt_of_sos").focus();
					return false;
				}
			}
			else if(newtosY<pretosY){
				alert("Invalid Date of SOS");
				$("input#dt_of_sos").focus();
				return false;
			}
		}
						
		if ($("input#out_auth_dt").val() == "") {
			alert("Please Select Date of Auth");
			$("input#out_auth_dt").focus();
			return false;
		}
		if(comm_date!=''){
			if(getformatedDate(out_auth_dt) < getformatedDate(comm_date)) {
				alert("Date of Auth Should Be Greater Then Date of Commission");
				$("input#out_auth_dt").focus();
				return false;
			}
		}
		if(dt_tos!=''){
			if(getformatedDate(dt_of_sos) < getformatedDate(dt_tos)) {
				alert("SOS Should Be Greater Then TOS");
				$("input#dt_of_sos").focus();
				return false;
			}
		}
		if(comm_date!=''){
			if(getformatedDate(dt_of_sos) < getformatedDate(comm_date)) {
				alert("SOS Should Be Greater Then Date of Enrollment Date");
				$("input#dt_of_sos").focus();
				return false;
			}
		}
		 
		var saveUrl;
		
		saveUrl='Posting_Out_JcoAction?'
				
		$("#save_postout_bt").attr('disabled',true);
			
		$.post(saveUrl + key + "=" + value, {unit_description:unit_description,from_sus_no:from_sus_no,personnel_no : personnel_no,
										rank:rank, to_sus_no : to_sus_no,out_auth : out_auth,out_auth_dt : out_auth_dt,dt_of_sos : dt_of_sos,
										jco_id : jco_id,t_status:t_status_out,h_id : h_id	},
				function(data) {
											
         
			if (data == "update")
				alert("Data Updated Successfully");

			else if (parseInt(data) > 0) {
				alert("Data saved successfully.");
				fn_service_category_change();
			} else
				alert(data);
			
			$("#save_postout_bt").attr('disabled',false);

		}).fail(function(xhr, textStatus, errorThrown) {
			alert("fail to fetch");
			$("#save_postout_bt").attr('disabled',false);
		});
		$("#save_postout_bt").attr('disabled',false);
		return true;
	}
	
/* Post Out Personnel No */
	function getPersonnel_no(obj,id) {
		removeMinDate();
		roleSus=$("#"+id).val();		
		if(roleSus!=''){
		var personel_no = obj.value;
		var susNoAuto = $("#"+obj.id);
		var perurl;
		perurl='getpersonnel_noListApproved_JCO?';
		susNoAuto.autocomplete({
			source : function(request, response) {
				$.ajax({
					type : 'POST',
					url : perurl + key + "=" + value,
					data : {
						personel_no : personel_no,army_no:personel_no,roleSus:roleSus
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
					fn_getJcoData();
					return true;
				} else {
					alert("Please Enter Approved Army No");
					susNoAuto.val("");
					susNoAuto.focus();
					return false;
				}
			},
			select : function(event, ui) {

			}
		});
		}
		else{
			alert("please Enter From Sus");			
			$("#"+id).focus();
			$("#"+id).val('');
			}
		}
	var jco_id = null;
	function personal_number() {
			fn_getOfficerData();		
		}

 /* Post Out End */
	
	var dt_tos='';
	var app_date='';
	var comm_date='';
	function fn_getJcoData(){
		 
		var personnel_no = $("input#personnel_no").val();
		if (personnel_no != "") {		
			var	perurl='GetArmyNoData?';							
			$.post(perurl + key + "=" + value, {army_no:personnel_no}, function(j) {
				var jco_id = j[0][0];
				var	 cpurl='GetJCODataApprove?'
	
				$("#jco_id").val(jco_id);
				$.post(cpurl + key + "=" + value,{ id : jco_id },function(k) {
		    		 if(k.length > 0)
		    		 {						
							if(k[0][17] != "1")	
							{								
								alert("Individual Record is still in Pending for Approval.Pl Notify the Oc/Co of Unit to Approve all Pending Records in Update JCO/OR Form.");
								$("input#personnel_no").val("");
								$("#name").text("");
								$("#rank").text("");
								$("#out_app_name").text("");
								$("#app_dt").text("");0
							}
							else
							{
								$("#census_id").val(k[0][1]);
			    			  	$("#name").text(k[0][2]);
			    			   	$("#rank").text(k[0][3]);
			    			 
			    		    	$("#out_app_name").text(k[0][4]);
			    		    
			    		    	if(k[0][5] != null)
			    		    	{
			    		    		$("#app_dt").text(convertDateFormate(k[0][5]));
			    		    	}			    		    
			    		    	var sus_no =k[0][7];
		    		
						if(sus_no!=null){
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
					}	 
		    	 }		    		
		     });
		});
	}
	$("input#personnel_no").attr('readonly', false);
}
	
	function fn_getOfficerData(){	

		var personnel_no = $("input#personnel_no").val();
		if (personnel_no != "") {		 	
			var	perurl='GetArmyNoData?';							
			$.post(perurl + key + "=" + value, {army_no:personnel_no}, function(j) {
				
				var jco_id = j[0][0];
				var	 cpurl='GetJcoOrCensusDataApprove?';
				$("#jco_id").val(jco_id);
				
				$.post('GETPostTenure_Date_Jco?' + key + "=" + value,{ jco_id : jco_id },function(k) {				
			   		 if(k.length > 0){
			   			$('#date_of_tos').val(k[0][0]);
			   			$('#dt_of_sos').val(k[0][0]);			   			
			   			setMinDate('date_of_tos',k[0][0]);
			   			setMinDate('dt_of_sos', k[0][0]);				   			
			   		 }	
			   		 else
			   		 {
			   			setMinDate('dt_of_sos', '01/01/1890');
			   			setMinDate('dt_of_tos', '01/01/1890');
			   		 }		   		 
			       });	
				
				$.post('CheckRole_Hdr_Status_Jco?' + key + "=" + value,{ jco_id : jco_id },function(rstatus) {
					if(rstatus.pass=="1"){
				$.post(cpurl + key + "=" + value,{ jco_id : jco_id},function(k) {
					 if(k.length > 0)
		    		 {		
						if(parseInt(k[0].update_jco_status) != parseInt(1))	
							{								
								alert("Individual Record is still in Pending for Approval.Pl Notify the Approval to Approve all Pending Records in Update Offcr Form.");
								$("input#personnel_no").val("");
								$("#name").text("");
								$("#rank").text("");
								$("#out_app_name").text("");
								$("#app_dt").text("");
							}
							else
							{								
			    			  	$("#name").text(k[0].full_name);
			    			   	$("#rank").text(k[0].rank);			    			 

			    		    	if(k[0].dt_of_tos != null)
			    		    	{
			    		    		dt_tos=ParseDateColumncommission(k[0].dt_of_tos);
			    		    	}
			    		    	else{
			    		    		dt_tos='';
			    		    	}			    		    	
			    		    	 if(k[0].enroll_dt != null)
			    		    	{
			    		    		comm_date=ParseDateColumncommission(k[0].enroll_dt);			    		    					    		    		
			    		    		setMinDate('out_auth_dt', comm_date);
			    		    		setMinDate('in_auth_dt', '01/01/1890');			    		    		
			    		    	}
			    		    	else{
			    		    		comm_date='';
			    		    	}
			    		    	 

			    		    	 if(k[0].parent_arm == "TERRITORIAL ARMY")
 	 			    		    	{
 	 			    		    		$("#stats_out").show();
 	 			    		    	}else{
 	 			    		    		$("#stats_out").hide();
 	 			    		    	}
			    		    	 
			    		    var sus_no =k[0].unit_sus_no;	    		
							if(sus_no!=null){
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
    		    	                   $('#unit_name').text(dec(enc,j[0]));			    		    	                 
			    		        }).fail(function(xhr, textStatus, errorThrown) {
			    		    });
		    		      } 		    		    
						}
					  }	
		    		}		    		
		   		});
			   }
			else{
				alert(rstatus.error);
				$("input#personnel_no").val("");
				$("#name").text("");
				$("#rank").text("");
				$("#out_app_name").text("");
				$("#app_dt").text("");
				return false;
				} 
			});
		});
	}				
	$("input#personnel_no").attr('readonly', false);
}
	
	function fn_getOfficerCensusIn(){		 
		var personnel_no = $("input#in_personnel_no").val();
		if (personnel_no != "") {
					
			var	perurl='CheckArmyNoData?';							
			$.post(perurl + key + "=" + value, {army_no:personnel_no}, function(j) {
				if(j.length==0){
					alert("Please Enter Valid Army No.");
					$("input#in_personnel_no").val('');
					return false;
				}
				else{
					if(j[0][1]=='4'){
						alert("Offcr In Non-Effective State");
						$("input#in_personnel_no").val('');
						return false;
					}
					if(j[0][1] !='1' && j[0][1] !='5'){
						alert("Individual Record is still in Pending for Approval.Pl Notify the Approval to Approve all Pending Records in Update Offcr Form.");
						$("input#in_personnel_no").val('');
						return false;
					}
				}
								
				var jco_id = j[0][0];
				$("#jco_id").val(jco_id);
				
				$.post('CheckRole_Hdr_Status_Jco?' + key + "=" + value,{ jco_id : jco_id },function(rstatus) {
				if(rstatus.pass=="1"){
				$.post('GetJcoOrCensusDataApprove?' + key + "=" + value,{ jco_id : jco_id},function(k) {
					
		    		 if(k.length > 0)
		    		 {
		    			 if(parseInt(k[0].update_jco_status) != parseInt(1))		
							{						
								alert("Individual Record is still in Pending for Approval.Pl Notify the Approval to Approve all Pending Records in Update Offcr Form.");
								$("input#in_personnel_no").val("");
								$("#iname").text("");
								$("#rank2").text("");
								//$("#unit_description_IN").text("");
							}
							else
							{
			    			  	$("#iname").text(k[0].full_name);
			    			   	$("#rank2").text(k[0].rank);
			    			   	//$("#unit_description_IN").text(k[0].unit_description);

		    		    	if(k[0].enroll_dt != null)
		    		    	{
		    		    		comm_date=ParseDateColumncommission(k[0].enroll_dt);	    		    		
		    		    		setMinDate('dt_of_sos', '01/01/1890');
		    		    		setMinDate('out_auth_dt', '01/01/1890');
		    		    		setMinDate('in_auth_dt', comm_date);
		    		    		setMinDate('dt_of_tos', comm_date);
		    		    	}
		    		    	else{
		    		    		comm_date='';
		    		    	}	    		    	
		    		    	if(k[0].post_unit_desc != null){
		    		    		$('#unit_description_IN').text(k[0].post_unit_desc);
		    		    	}
		    		    	if(k[0].dt_of_tos != null)
		    		    	{
		    		    		dt_tos=ParseDateColumncommission(k[0].dt_of_tos);
		    		    	}
		    		    	else{
		    		    		dt_tos='';
		    		    	}
		    		    	
		    		    	if(k[0].parent_arm == "TERRITORIAL ARMY")
		    		    	{
		    		    		$("#stats").show();
		    		    	}else{
		    		    		$("#stats").hide();
		    		    	}

		    		    	
		    		    	
		    		    	// base on personnel number susno bind 
		    		    	  if ( $('#type').val() == 'postin' && $('#service_category').val() != '0') {
		    		    	     $("#i_from_sus_no").val(k[0].unit_sus_no);
		    		    	     $('#i_from_sus_no').prop('readonly',true);
		    		    	     
		    		 			var sus_no = k[0].unit_sus_no;			      	
		    					   $.post("getTargetUnitNameList?"+key+"="+value, {
		    						 sus_no:sus_no
		    		                }, function(j) {		    		              
		    		                 }).done(function(j) {
		    		      	     var length = j.length-1;
		    		           var enc = j[length].substring(0,16);		    		          
		    		           $('#unit_nameIn').val(dec(enc,j[0]));   
		    		               
		    		             }).fail(function(xhr, textStatus, errorThrown) {
		    		          });
		    					   $('#unit_nameIn').prop('readonly',true); 
		    		    	  }
		    		    	
		    		    	
		    		    	var sus_no =k[0].unit_sus_no;
		    		    	if(sus_no!='${roleSusNo}'){
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
	    			 else{
	    				 alert("Please check To SUS NO,it can't be same as like FROM SUS NO");
							fn_service_category_change();	 
	    			 }
				   }
		    	}
		    		 $.post('GETPostTenure_Date_Jco?' + key + "=" + value,{ jco_id : jco_id },function(k) {
			   		 if(k.length > 0){
				   			$('#dt_of_tos').val(k[0][0]);
				   			$('#dt_of_sos').val(k[0][0]);
				   			
				   			setMinDate('dt_of_tos',k[0][0]);
				   			setMinDate('dt_of_sos',k[0][0]);					   			
			   		 }					   							   		 
				   });
		     	});
			}
			else{
				alert(rstatus.error);
				$("input#in_personnel_no").val("");
				$("#iname").text("");
				$("#rank2").text("");
				return false;
			}
		});				
	});
  }
	$("input#in_personnel_no").attr('readonly', false);
}
	
function fn_getjcoCensusDataIn(){
	census_id = $('#census_id').val();
	
	var personnel_no = $("input#in_personnel_no").val();
	if (personnel_no != "") {

		$.post('GetArmyNoDataNoData?' + key + "=" + value, {
			army_no:personnel_no
		}, function(j) {

			if(j.length==0){
				alert("Individual Record is still in Pending for Approval.Pl Notify the Approval to Approve all Pending Records in Update JCO/OR Form.");
				$("input#in_personnel_no").val('');
				return false;
			}
			var comm_id = j[0][0];
				 var	 cpurl='GetJCODataApprove?'

			$("#comm_id").val(comm_id);

			$.post(cpurl + key + "=" + value,{ id : comm_id },function(k) {
				
	    		 if(k.length > 0)
	    		 { 	    			 					
						if(k[0][17] != "1")	
						{							
							alert("Individual Record is still in Pending for Approval.Pl Notify the Approval to Approve all Pending Records in Update JCO/OR Form.");
							$("input#in_personnel_no").val("");
							$("#iname").text("");
							$("#rank2").text("");
							$("#app_name").text("");
							$("#app_dt").text("");
						}
						else
						{
							$("#census_id").val(k[0][1]);
		    			  	$("#iname").text(k[0][2]);
		    			   	$("#rank2").text(k[0][3]);		    			 
		    		    	$("#app_name").text(k[0][4]);
		    		    
		    		    	if(k[0][5] != null)
		    		    	{
		    		    		$("#app_dt").text(convertDateFormate(k[0][5]));
		    		    	}
		    		    
		    		    	var sus_no =k[0][7];
		    		    	
							if(sus_no!='${roleSusNo}'){
								$('#i_from_sus_no').val(sus_no);
			    		    	$('#i_from_sus_no').prop('readonly',true);
	    		
					if(sus_no!=null){
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
				  }
					else{
						
						alert("Please check To SUS NO,it can't be same as like FROM SUS NO");
						fn_service_category_change();
					}
				}	 
	    	}    		
	   });
	});
  }
	$("input#in_personnel_no").attr('readonly', false);
}
	
function fn_service_category_change(){
		$("input#in_personnel_no").val("");
		$("#iname").text("");
		$("#rank2").text("");
		$("#app_name").text("");
		$("#app_dt").text("");
		$("#census_id").val(0);
		$("#comm_id").val(0);
		$("#in_auth").val('');				
		$("#in_auth_dt").val('DD/MM/YYYY');
		$("#i_from_sus_no").val('');
		$("#i_from_sus_no").prop("readonly",false);
		$("#dt_of_tos").val('DD/MM/YYYY');		
		$("#out_auth_dt").val('DD/MM/YYYY');
		$("#unit_name1").val('');
		$("#unit_nameIn").val('');		
		$("#o_to_sus_no").val('');
		$("#out_auth").val('');
		$("#dt_of_sos").val('DD/MM/YYYY');
		$("#unit_description_out").val('');
		$("#unit_description_IN").text('');		
		$("input#personnel_no").val("");
		$("#name").text("");
		$("#rank").text("");
		$("#out_app_name").text("");
		$("#app_dt").text("");
	}

//bisag 14_03_2023 v2(change invalid status)
function  search_sus1from(sus_obj,unit_id){
// 	removeMinDate();
	var sus_no = sus_obj.value;
	var susNoAuto = $("#"+sus_obj.id);

	
	
	susNoAuto.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
	        type: 'POST',
	        url: "getSUSNoList_Active_or_Inactive?"+key+"="+value,
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
	        	  if( $('#type').val() == 'postout'){
	        	  alert("Please Enter Approved Unit SUS No.");
	        	  $('#'+unit_id).val('');
	        	  susNoAuto.val("");	        	  
	        	  susNoAuto.focus();
	        	  }
	        	  return false;	             
	          }   	         
	    }, 
		select: function( event, ui ) {
			var sus_no = ui.item.value;			      	
			 $.post("getUnitNameList_Active_or_Inactive?"+key+"="+value, {
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

function search_unit1_from(obj,id){
// 	removeMinDate();
			var unit_name = obj.value;
				 var susNoAuto=$("#"+obj.id);
				  susNoAuto.autocomplete({
				      source: function( request, response ) {
				        $.ajax({
				        	type: 'POST',
					    	url: "UnitsNameList_active_or_inactive?"+key+"="+value,
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
				        	  if( $('#type').val() == 'postout'){
				        	  alert("Please Enter Approved Unit Name.");
				        	  $("#"+id).val('');
				        	  susNoAuto.val("");	        	  
				        	  susNoAuto.focus();
				        	  }
				        	  return false;	             
				          }   	         
				      }, 
				      select: function( event, ui ) {
				    	 var unit_name = ui.item.value;
					            $.post("SUSFromUNITNAMEActive_or_InactiveList?"+key+"="+value,{target_unit_name:unit_name}, function(data) {
					            }).done(function(data) {
					            	var length = data.length-1;
						        	var enc = data[length].substring(0,16);
						        	$("#"+id).val(dec(enc,data[0]));
					            }).fail(function(xhr, textStatus, errorThrown) {
					            });
				      } 	     
				}); 			
		
}
	
	
function  search_sus(sus_obj,unit_id){
	var sus_no = sus_obj.value;
	var susNoAuto = $("#"+sus_obj.id);
	susNoAuto.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
	        type: 'POST',
	        url: "getTargetSUSNoList_postin?"+key+"="+value,
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
			        	$("#"+id).val(dec(enc,data[0]));
		            }).fail(function(xhr, textStatus, errorThrown) {
		            });
	      } 	     
	}); 			
}
function validateDate(inputDate, inputElement) {
    var currentDate = new Date();
    var parts = inputDate.split('/');
    var selectedDate = new Date(parts[2], parts[1] - 1, parts[0]);
    
    if (selectedDate > currentDate) {
        alert("Future dates are not allowed.");
        inputElement.value = "DD/MM/YYYY";
        inputElement.style.color = "#000000";
    }
}	


</script>


<script>
$("input#in_personnel_no").keyup(function() {
		var personel_no = this.value;
		var susNoAuto = $("#in_personnel_no");
		susNoAuto.autocomplete({
			source: function(request, response) {
				$.ajax({
					type: 'POST',
					url: "getPersonnelNoListJco?" + key + "=" + value,
					data: {
						personel_no: personel_no
					},
					success: function(data) {
						var susval = [];
						var length = data.length - 1;
						var enc = data[length].substring(0, 16);
						for(var i = 0; i < data.length; i++) {
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
					alert("Please Enter Approved Army No");
					 $('#i_from_sus_no').val("");
					 $('#unit_nameIn').val("");
					 $('#iname').text("");
					 $('#rank2').text("");
					susNoAuto.val("");
					susNoAuto.focus();
					return false;
				}
			},
			select : function(event, ui) {

			}
		});
	});


</script>


