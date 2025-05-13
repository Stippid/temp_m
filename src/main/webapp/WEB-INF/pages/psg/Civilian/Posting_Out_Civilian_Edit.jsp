<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<form action="Edit_Posting_Out_CivilianAction?${_csrf.parameterName}=${_csrf.token}" method="post" class="form-horizontal" >
		<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<h5> Update POSTING OUT </h5>
					<h6 class="enter_by">
						<span style="font-size: 12px; color: red;">(To be entered by UNIT)</span>
					</h6>
				</div>
			<div class="card-body card-block">
				<div class="col-md-12">
					<div class="col-md-4">
						<div class="row form-group">
							<div class="col-md-5">
								<strong	style="color: red;">* </strong><label class=" form-control-label"> Employee No </label>
							</div>
							<div class="col-md-7">
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
								<input type="hidden" id="id" name="id" class="form-control autocomplete" autocomplete="off" value="${updateid}"/>							
								<input type="hidden" id="employee_no" name="employee_no" class="form-control autocomplete" autocomplete="off" readonly="true" onchange="personal_number()"/>
 								<label id="employee_nolbl"></label>
 							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="row form-group">
							<div class="col-md-4">
								<strong	style="color: red;">* </strong><label class=" form-control-label"> Name </label>
							</div>
							<div class="col-md-8">
								<input type="hidden" name="inemployee_no3v" id="inemployee_no3v"/>		
			                	<input type="hidden" name="inrank3v" id="inrank3v"/>		
			                	<input type="hidden" name="into_sus_no3v" id="into_sus_no3v"/>		
			                	<input type="hidden" name="infrom_sus_no_out3v" id="infrom_sus_no_out3v"/>		
			                	<input type="hidden" name="intype3v" id="intype3v"/>
			                	<input type="hidden" name="instatus3v" id="instatus3v"/>	
								<label id="name"></label> 
							</div>
						</div>
					</div>
				 	<div class="col-md-4">
							<div class="row form-group">
								 <div class="col-md-4">
									<strong	style="color: red;">* </strong><label class=" form-control-label"> Designation </label>
								</div>
								<div class="col-md-8">
									<label id="rank" name="rank"></label> 
								</div>
							</div>
						</div>
				</div>
				<div class="col-md-12">
					  <div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<strong	style="color: red;">* </strong><label class=" form-control-label"> From Unit SUS No </label>
								</div>
								<div class="col-md-8">
									<label id="from_sus"></label>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<strong	style="color: red;">* </strong><label class=" form-control-label"> From Unit Name </label>
								</div>
								<div class="col-md-8">
									<label id="from_unitname"></label>
								</div>
							</div>
						</div>
					</div>				
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<strong	style="color: red;">* </strong><label class=" form-control-label"> Auth No </label>
								</div>
								<div class="col-md-8">
									<input type="text" id="out_auth" name="out_auth" class="form-control autocomplete" autocomplete="off" onkeyup="return specialcharecter(this);" />
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<strong	style="color: red;">* </strong><label class=" form-control-label"> Auth Date </label>
								</div>
								<div class="col-md-8">
							 		<input type="text" name="out_auth_dt" id="out_auth_dt" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >						              									
								</div>
							</div>
					   </div>
				</div>								
				<div class="col-md-12">
					  <div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<strong	style="color: red;">* </strong><label class=" form-control-label"> To Unit SUS No </label>
								</div>
								<div class="col-md-8">
									<input type="text" id="to_sus_no" name="to_sus_no" class="form-control autocomplete" autocomplete="off"/>
									<input type="hidden" id="from_sus_no" name="from_sus_no" class="form-control autocomplete" autocomplete="off"/>
									<input type="hidden" id="civ_id" name="civ_id" value="0" class="form-control autocomplete" autocomplete="off"/>
									<input type="hidden" id="dt_tos_pre" name="dt_tos_pre" value="" class="form-control autocomplete" autocomplete="off"/>								
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<strong	style="color: red;">* </strong><label class=" form-control-label"> To Unit Name </label>
								</div>
								<div class="col-md-8">
									<input type="text" id="unit_name" name="unit_name" class="form-control autocomplete" autocomplete="off"/>
								</div>
							</div>
						</div>
					</div>				
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<strong	style="color: red;">* </strong><label class=" form-control-label"> Date of SOS </label>
								</div>
								<div class="col-md-8">
									<input type="text" name="dt_of_sos" id="dt_of_sos" maxlength="10" value="DD/MM/YYYY" onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
										onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
										onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" />
								</div>
							</div>
						</div>
						 <div class="col-md-6" id="" >
							<div class="row form-group">
								<div class="col-md-4">
									<strong style="color: red;">  </strong><label class=" form-control-label"> Unit Description </label>
								</div>
								<div class="col-md-8">
										<textarea id="unit_description_out" name="unit_description_out" class="form-control autocomplete" autocomplete="off" maxlength="1000" onkeyup="return specialcharecter(this);"></textarea>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="card-footer" align="center" class="form-control">					
					<input type="submit" id="update" name="update" value="Update" class="btn btn-primary btn-sm" onclick="return posting_out_edit_fn()"> 
					<!-- <input type="button"  class="btn btn-info btn-sm" onclick="Cancel();" value="Back">  -->
					<a href="Search_Posting_In_Out_CivilianUrl" class="btn btn-info btn-sm">Back</a>
				</div>
			</div>
		</div>
</form>
 
<script>
var dt_tos='';
$(document).ready(function() {

	$("#employee_no6").val('${employee_no6}');
	$("#rank").val('${rank6}');
	$("#to_sus_no").val('${to_sus_no6}');
	$("#to_sus_no_out").val('${from_sus_no6}');
	$("#type").val('${type6}');		
	$("#inemployee_no3v").val('${employee_no6}');
	$("#inrank3v").val('${rank6}');
	$("#into_sus_no3v").val('${to_sus_no6}');
	$("#infrom_sus_no_out3v").val('${from_sus_no6}');
	$("#intype3v").val('${type6}');
	$("#instatus3v").val('${status6}');
	
	if('${size}' != ""){
		
		$("#employee_no").val('${list[0].employee_no}');	
		$("#employee_nolbl").text('${list[0].employee_no}');	
		$("#name").text('${list[0].name}');
		$("#rank").text('${list[0].rank}');	
		$("#out_auth").val('${list[0].out_auth}');	
		$("#unit_description_out").val('${list[0].unit_description}');	
		
		var out_auth_dt ='${list[0].out_auth_dt}'		
		if (out_auth_dt != ''){
			$("#out_auth_dt").val(ParseDateColumncommission(out_auth_dt));
		}
		
		$("#to_sus_no").val('${list[0].unit_sus_no}');	
		$("#unit_name").val('${list[0].unit_name}');	
		$("#from_sus_no").val('${list[0].from_sus_no}');
		$("#from_sus").text('${list[0].from_sus_no}');
		var sus_no='${list[0].from_sus_no}';
		 $.post("getTargetUnitNameList?"+key+"="+value, {
			 sus_no:sus_no
			     }, function(j) {
			            
			     }).done(function(j) {
			    	 var length = j.length-1;
			         var enc = j[length].substring(0,16);
			         $("#from_unitname").text(dec(enc,j[0]));   
			             
			     }).fail(function(xhr, textStatus, errorThrown) {
			  });
		 
		var dt_of_sos ='${list[0].dt_of_sos}';		 
		if (dt_of_sos != ''){
			$("#dt_of_sos").val(ParseDateColumncommission(dt_of_sos));
		}
		var civ_id='${list[0].civ_id}';
		$("#civ_id").val(civ_id);
		
		var cpurl='GetCivilianOrCensusDataApprove?';
		$.post(cpurl + key + "=" + value,{civ_id:civ_id},function(k) {
   		 if(k.length > 0)
   		 {
   			if(k[0].dt_of_tos != null)
	    	{
	    		dt_tos=ParseDateColumncommission(k[0].dt_of_tos);
	    		$("#dt_tos_pre").val(dt_tos);
	    	}
	    	else{
	    		dt_tos='';
	    	}
   		 }
	  });
	}
	$.post('GETPostTenure_Date_Civilian?' + key + "=" + value,{ civ_id : civ_id },function(k) {
   		 if(k.length > 0){   			
	   		setMinDate('dt_of_sos', k[0][0]);	   			
   		 }	
   		 else
   		 {
   			setMinDate('dt_of_sos', '01/01/1890'); 			
   		 }  		 
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
 
jQuery(function($){ //on document.ready  
	 datepicketDate('out_auth_dt');
	 datepicketDate('dt_of_sos');
});

	$("#to_sus_no").keyup(function(){
		var sus_no = this.value;
		var susNoAuto=$("#to_sus_no");
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
					        	$("#to_sus_no").val(dec(enc,data[0]));
				            }).fail(function(xhr, textStatus, errorThrown) {
				            });
				      } 	     
				}); 			
		});
	

	////Personal No

	$("input#employee_no").keypress(function() {

		var employee_no = this.value;
		var susNoAuto = $("#employee_no");
		susNoAuto.autocomplete({
			source : function(request, response) {
				$.ajax({
					type : 'POST',
					url : "getEmployee_NoListApproved_Civilian?" + key + "=" + value,
					data : {employee_no : employee_no},
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
					alert("Please Enter Approved Employee No");
					document.getElementById("employee_no").value = "";
					susNoAuto.val("");
					susNoAuto.focus();
					return false;
				}
			},
			select : function(event, ui) {

			}
		});
	});
	
	var census_id = null;
	var comm_date='';
	function personal_number() {
		census_id = $('#census_id').val();
		if (parseInt(census_id) > 0) {
			$("#tab_id").show();
			$("#submit_data").show();
		} else {

			$("#1").show();
			$("#submit_data").show();
		}
		var employee_no = $("input#employee_no").val();
		if (employee_no != "") {
			$.post('GetEmployeeNoData?' + key + "=" + value, {employee_no:employee_no}, function(j) {
				
				var civ_id = j[0][0];
				var name = j[0][2];
				var rank = j[0][4];
				joining_date_gov_service = ParseDateColumncommission(j[0][6]);
				setMinDate('dt_of_sos', joining_date_gov_service);
				setMinDate('out_auth_dt', joining_date_gov_service);
				$("#civ_id").val(civ_id);

			 	/*  $.post('GetCensusData?' + key + "=" + value,{ civ_id : civ_id },function(k) {
		    		 if(k.length > 0)
		    		 {
		    			  $('#census_id').val(k[0][0]); 		    			  		    			 
		    		 }
		   		}); */
			});
		}
		$("input#employee_no").attr('readonly', false);
	}

function posting_out_edit_fn() {
	var out_auth_dt = $('#out_auth_dt').val();
	if ($("input#employee_no").val() == "") {
		alert("Please select Employee No");
		$("input#employee_no").focus();
		return false;
	}		
	if ($("input#out_auth").val() == "") {
		alert("Please Enter Auth No");
		$("input#out_auth").focus();
		return false;
	}
	 if($("#out_auth_dt").val()== "DD/MM/YYYY"){
		alert("Please Enter Auth Date");
		$("input#out_auth_dt").focus();
		return false;
	 }
	if ($("input#out_auth_dt").val() == "") {
		alert("Please Select Date of Auth");
		$("input#out_auth_dt").focus();
		return false;
	}
	if ($("input#to_sus_no").val() == "") {
		alert("Please Select Unit SUS No");
		$("input#to_sus_no").focus();
		return false;
	}	
var fsus=$("input#from_sus_no").val();
var tsus=$("input#to_sus_no").val();

	if ( fsus == tsus) {
		alert("From And To SUS Can't Be Same");
		$("input#to_sus_no").focus();
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
			var newtos = $("input#dt_of_sos").val().split("/");
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
		
	
	if(joining_date_gov_service!=''){
		if(getformatedDate(out_auth_dt) < getformatedDate(joining_date_gov_service)) {
			alert("Date of Auth Should Be Greater Then Date of Joining Gov Service");
			$("input#out_auth_dt").focus();
			return false;
		}
	}
	if(dt_tos!=''){
		if(getformatedDate($("input#dt_of_sos").val()) < getformatedDate(dt_tos)) {
			alert("SOS Should Be Greater Then TOS");
			$("input#dt_of_sos").focus();
			return false;
		}
	}
	if(joining_date_gov_service!=''){
		if(getformatedDate($("input#dt_of_sos").val()) < getformatedDate(joining_date_gov_service)) {
			alert("SOS Should Be Greater Then Date of Joining Gov Service");
			$("input#dt_of_sos").focus();
			return false;
		}
	}		
}
function Cancel(){
	
	$("#employee_no4").val($("#inemployee_no3v").val());
	$("#rank4").val($("#inrank3v").val());
	$("#to_sus_no4").val($("#into_sus_no3v").val());
	$("#from_sus_no4").val($("#infrom_sus_no_out3v").val());
	$("#type4").val($("#intype3v").val());
	$("#status4").val($("#instatus3v").val());
	document.getElementById('searchForm').submit();
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

<c:url value="Search_Posting_In_Out_CivilianUrl" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="updateid">	
		<input type="hidden" name="employee_no4" id="employee_no4" value="0" />
		<input type="hidden" name="rank4" id="rank4" />
		<input type="hidden" name="to_sus_no4" id="to_sus_no4" value="0" />
		<input type="hidden" name="from_sus_no4" id="from_sus_no4" value="0" />
		<input type="hidden" name="type4" id="type4" value="0" />
		<input type="hidden" name="status4" id="status4" value="0" />
	</form:form>
