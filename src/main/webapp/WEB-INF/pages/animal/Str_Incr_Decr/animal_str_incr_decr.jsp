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
					<h5>ANIMAL STR INCR/DECR</h5>
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
									<input type="hidden" id="census_id" name="census_id" class="form-control autocomplete" autocomplete="off">	
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
							<h5 id="action_dog">STR DECR</h5>
							<h6 class="enter_by">
								<span style="font-size: 12px; color: red;"></span>
							</h6>
						</div>
					<div class="card-body card-block"><br>
					 <input type="hidden" id="h_id" name="h_id"	class="form-control autocomplete" autocomplete="off" value="0"> 
						
						<div id="Decrdiv">
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
					
					<!--to sus no div -->
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
					
					</div>
					
					
					<div id="Incrdiv">
					
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
					
					</div>
					
					
					<div id="DecrArmyNoDiv" style="display:none">
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
									<label id="name_Decr" name="name_Decr"> </label>
									
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Microchip No </label>
								</div>
								<div class="col-md-8">
									<label id="micro_chip_no_decr" name="micro_chip_no_decr"> </label> 
									<input type="hidden" id="micro_chip_no_decr1" name="micro_chip_no_decr1" class="form-control autocomplete"
										autocomplete="off">
								</div>
							</div>
						</div>				
					</div>	
					</div>
					
					
					<div id="IncrArmyNoDiv" style="display:none">
					
					<div class="col-md-12">
						<div class="col-md-4">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Army No </label>
									<input type="hidden" id="i_id" name="i_id"	class="form-control autocomplete" autocomplete="off" value="0"> 					
								</div>
								<div class="col-md-8">
									<input type="text" id="in_personnel_no" name="in_personnel_no"
													onkeydown="return AvoidSpace(event)"
													onkeypress="return onlyAlphaNumeric(event);" onchange="Inpersonal_number();" maxlength="9"
													min="7" class="form-control autocomplete"
													autocomplete="off">
								</div>
							</div>
						</div>
					 <div class="col-md-4">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Name </label>
								</div>
								<div class="col-md-8">
									<label id="name_Incr" name="name_Incr"> </label>								
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Microchip No </label>
								</div>
								<div class="col-md-8">
									<label id="micro_chip_no_Incr" name="micro_chip_no_Incr"> </label>									
								</div>
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
									<label id="dt_of_tos_col" class=" form-control-label"> Date of SOS </label>
								</div>
								<div class="col-md-8">									
									<input type="text" name="dt_of_sos" id="dt_of_sos" maxlength="10" value="DD/MM/YYYY" onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
										onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
										onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" />
								</div>
							</div>
						</div>									
					</div>
					
					
			
				 </div>
					<div class="card-footer" align="center" class="form-control">
						<a href="anm_str_incr_decrUrl" class="btn btn-success btn-sm">Clear</a> 
						<input type="button" class="btn btn-primary btn-sm" id="save_postout_bt" value="Save" onclick="save_postout();">
					</div>
				</div>
			</form>
		</div>
		 		
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
			   var type= $('#type').val();
			   console.log("VAR OF TYPE " + type);
		      if ( $('#type').val().trim() == '2') {
		    	  $("#action_dog").text("STR DECR");
		    	  $("#dt_of_tos_col").text("Date Of SOS");	
		    	  $("#Decrdiv").show();
		    	  $("#Incrdiv").hide();
		    	  $("#div_post_out").show();
		    	  $("#DecrArmyNoDiv").show();
		    	  $("#IncrArmyNoDiv").hide();
		    	  
		    	   var sus_no = '${roleSusNo}';
		    	  	$.post("getTargetUnitNameList?"+key+"="+value, {sus_no:sus_no}).done(function(data) {
		    	  		var l=data.length-1;
		    	  		var enc = data[l].substring(0,16);    	   	 
		    	  	 	$("#unit_name").val(dec(enc,data[0]));
		    	  			
		    	  		}).fail(function(xhr, textStatus, errorThrown) {
		    	  });
		    	  
		      }
		      if ( $('#type').val().trim() == '1') {
		    	  $("#action_dog").text("STR INCR");
		    	  $("#dt_of_tos_col").text("Date Of TOS");	
		    	  $("#div_post_out").show();
		    	  $("#Incrdiv").show();
		    	  $("#Decrdiv").hide();
		    	  $("#IncrArmyNoDiv").show();
		    	  $("#DecrArmyNoDiv").hide();
		    	 
		    	  
		      }
		      if ( $('#type').val().trim() == '') {
		    	  $("#div_post_out").hide();
		    	  $("#Incrdiv").hide();
		    	  $("#Decrdiv").hide();
		    	  
		      }
		      fn_service_category_change();
		 	});	
		   $('#service_category').on('change', function() {
		      if ( $('#type').val() == '2') {
		    	  $("#div_post_out").show();
		    	  $("#Decrdiv").show();
		    	  $("#Incrdiv").hide();
		    	  
		    	   var sus_no = '${roleSusNo}';
		    	  	$.post("getTargetUnitNameList?"+key+"="+value, {sus_no:sus_no}).done(function(data) {
		    	  		var l=data.length-1;
		    	  		var enc = data[l].substring(0,16);    	   	 
		    	  	 	$("#unit_name").val(dec(enc,data[0]));
		    	  			
		    	  		}).fail(function(xhr, textStatus, errorThrown) {
		    	  });		    	  
		      }
		      if ( $('#type').val() == '1') {
		    	  $("#div_post_out").show();
		    	  $("#Incrdiv").show();
		    	  $("#Decrdiv").hide();
		      }
		      if ( $('#type').val() == '') {
		    	  $("#div_post_out").hide();
		    	  $("#Decrdiv").hide();
		    	  $("#Incrdiv").hide();
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
	fn_getAnimalCensusIncr();	
 }
/* Post In End Save*/
</script>

 <script>
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
		debugger;
	    var scenario = $('#type').val(); 
		var personnel_no = "";
		var name = "";
		var microchip_no = "";
		var to_sus_no = "";
		var from_sus_no = "";
		
		if(scenario == "2"){
			personnel_no = $('#personnel_no').val();
			name = $('#name_Incr').val();
			microchip_no = $('#micro_chip_no_Incr').val();
			to_sus_no = $('#o_to_sus_no').val();
			from_sus_no = $('#from_sus_no').val();
		}else if (scenario == "1"){
			
			personnel_no = $('#in_personnel_no').val();
			name = $('#name_Decr').val();
			microchip_no = $('#micro_chip_no_decr').val();
			to_sus_no = $('#i_to_sus_no').val();
			from_sus_no = $('#i_from_sus_no').val();
			
		}
		
		var out_auth = $('#out_auth').val();
		var out_auth_dt = $('#out_auth_dt').val();
 		var dt_of_sos = $('#dt_of_sos').val();
		var census_id = $('#census_id').val();
		var h_id = $('#h_id').val();
		

		console.log("val of census_id: ", census_id);
		console.log("val of to_sus_no: ", to_sus_no);
		console.log("val of out_auth: ", out_auth);
		console.log("val of out_auth_dt: ", out_auth_dt);
		console.log("val of personnel_no:  ", personnel_no);
		console.log("val of dt_of_sos:  ", dt_of_sos);
		console.log("val of from_sus_no: ", from_sus_no);
		console.log("val of to_sus_no: ", to_sus_no);
		console.log("val of scenario: ", scenario);
		console.log("val of h_id: ", h_id);
	 	 
		 if (personnel_no == "") {
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
		if (to_sus_no == "") {
			alert("Please Select To SUS No");
			$("input#o_to_sus_no").focus();
			return false;
		}
		
		if (from_sus_no == "") {
			alert("Please Select from SUS No");
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
		if(dt_tos!=''){
			if(getformatedDate(dt_of_sos) < getformatedDate(dt_tos)) {
				alert("SOS Should Be Greater Then TOS");
				$("input#dt_of_sos").focus();
				return false;
			}
		}

		 
		var saveUrl;
		
		saveUrl='Animal_Str_Incr_DecrAction?'
				
		$("#save_postout_bt").attr('disabled',true);
			
		$.post(saveUrl + key + "=" + value, {from_sus_no:from_sus_no,personnel_no : personnel_no,scenario : scenario,
										to_sus_no : to_sus_no,out_auth : out_auth,out_auth_dt : out_auth_dt,dt_of_sos : dt_of_sos,
										census_id : census_id,h_id : h_id	},
				function(data) {
											
         
			if (parseInt(data) > 0) {
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
		perurl='getarmy_noListApproved_Animal_Decr?';
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
		fn_getAnimalData_Decr();		
		}

 /* Post Out personnel no End */
	
	var dt_tos='';
	var app_date='';
	var comm_date='';
	
	



function fn_getAnimalData_Decr() {	
    var personnel_no = $("input#personnel_no").val();
    console.log("fn_getAnimalData_Decr personnel_no --" + personnel_no);
    if (personnel_no != "") {		 	
        var perurl = 'GetArmyNoDataAnimal?';							
        $.post(perurl + key + "=" + value, { army_no: personnel_no }, function(j) {
        	
        	 if (!j || j.length === 0 || !Array.isArray(j[0])) {
                 alert("No data found for the given personnel number.");
                 return;
             }
        	
            var census_id_animal = j[0][0];
            $("#census_id").val(census_id_animal);
            $("#name_Decr").text(j[0][3]);
            $("#micro_chip_no_decr").text(j[0][5]);
			console.log("j[0][0] --" + j[0][0] + "j[0][3]" +j[0][3] + "j[0][5]" + j[0][5]);
            $.post('GETPostTenure_Date_Animal?' + key + "=" + value, { census_id_animal: census_id_animal }, function(k) {				
                if (k.length > 0) {
                    $('#date_of_tos').val(k[0][0]);
                    $('#dt_of_sos').val(k[0][0]);			   			
                    setMinDate('date_of_tos', k[0][0]);
                    setMinDate('dt_of_sos', k[0][0]);				   			
                } else {
                    setMinDate('dt_of_sos', '01/01/1890');
                    setMinDate('dt_of_tos', '01/01/1890');
                }		   		 
            });	
        });
    } else {
        $("input#personnel_no").val("");
        $("#name_Decr").text("");
        $("#micro_chip_no_decr").text("");
        $("#app_dt").text("");
        return false;
    }
    $("input#personnel_no").attr('readonly', false);
}

function fn_getAnimalCensusIncr() {		 
    var personnel_no = $("input#in_personnel_no").val();
    console.log(" fn_getAnimalCensusIncr personnel_no "+ personnel_no)
    if (personnel_no != "") {
        var perurl = 'GetArmyNoDataAnimal?';

/*         $.post(perurl + key + "=" + value, { army_no: personnel_no }, function(p) {
            if (p.length == 0) {
                alert("Please Enter Valid Army No.");
                $("input#in_personnel_no").val('');
                return false;
            } */
            
            $.post(perurl + key + "=" + value, { army_no: personnel_no }, function(d) {
            	
           	 if (!d || d.length === 0 || !Array.isArray(d[0])) {
                    alert("No data found for the given personnel number.");
                    $("input#in_personnel_no").val('');
                    return;
                }
            

            var census_id_animal = d[0][0];
            $("#census_id").val(census_id_animal);
            $("#name_Incr").text(d[0][3]);
            $("#micro_chip_no_Incr").text(d[0][5]);
			
            console.log("d[0][0] --" + d[0][0] + "d[0][3]" +d[0][3] + "d[0][5]" + d[0][5]);
            
            $.post('GETPostTenure_Date_Animal?' + key + "=" + value, { census_id_animal: census_id_animal }, function(k) {
                if (k.length > 0) {
                    $('#dt_of_tos').val(k[0][0]);
                    $('#dt_of_sos').val(k[0][0]);

                    setMinDate('dt_of_tos', k[0][0]);
                    setMinDate('dt_of_sos', k[0][0]);
                }
            });

            // Based on personnel number, bind susno
            if ($('#type').val() == 'incr') {
                $("#i_from_sus_no").val(d[0][7]);
                $('#i_from_sus_no').prop('readonly', true);

                var sus_no = d[0][7];
                $.post("getTargetUnitNameList?" + key + "=" + value, { sus_no: sus_no }, function(j) {
                }).done(function(j) {
                    var length = j.length - 1;
                    var enc = j[length].substring(0, 16);
                    $('#unit_nameIn').val(dec(enc, j[0]));
                }).fail(function(xhr, textStatus, errorThrown) {
                });

                $('#unit_nameIn').prop('readonly', true);
            }

            var sus_no = d[0][7];
            if (sus_no != '${roleSusNo}') {
                getunit(sus_no);
                function getunit(val) {	
                    $.post("getTargetUnitNameList?" + key + "=" + value, { sus_no: sus_no }, function(j) {
                    }).done(function(j) {
                        var length = j.length - 1;
                        var enc = j[length].substring(0, 16); 
                        $("#app_unit_name").text(dec(enc, j[0])); 
                    }).fail(function(xhr, textStatus, errorThrown) {
                    });
                } 
            }
        });
    } else {
        $("input#in_personnel_no").val("");
        $("#name_Incr").text("");
        $("#micro_chip_no_Incr").text("");
        return false;
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
		var roleSus=$("#i_from_sus_no").val();
		var personel_no = this.value;
		var susNoAuto = $("#in_personnel_no");
		if(roleSus!=''){
		susNoAuto.autocomplete({
			source: function(request, response) {
				$.ajax({
					type: 'POST',
					url: "getPersonnelNoListAnimal_Incr?" + key + "=" + value,
					data: {
						personel_no: personel_no,roleSus:roleSus
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
					 $('#name_Incr').text("");
					 $('#micro_chip_no_Incr').text("");
					susNoAuto.val("");
					susNoAuto.focus();
					return false;
				}
			},
			select : function(event, ui) {

			}
		});
		}else{
			alert("Please Select From Unit Name");
		}
	});


</script>


