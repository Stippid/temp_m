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

<form action="Edit_Posting_In_CivilianAction?${_csrf.parameterName}=${_csrf.token}" method="post" class="form-horizontal" >
	<div class="container" align="center">
		<div class="card">
			<div class="card-header">
				<h5> Update POSTING IN </h5>
					<h6 class="enter_by">
						<span style="font-size: 12px; color: red;">(To be entered by UNIT)</span>
					</h6>
				</div>
			<div class="card-body card-block">
				<div class="col-md-12">
					<div class="col-md-4">
						<div class="row form-group">
							<div class="col-md-5">
								<strong	style="color: red;">*</strong><label class=" form-control-label"> Employee No </label>
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
								<strong	style="color: red;">*</strong><label class=" form-control-label"> Name </label>
							</div>
							<div class="col-md-8">
								<input type="hidden" name="inemployee_no3v" id="inemployee_no3v"/>		
						        <input type="hidden" name="inrank3v" id="inrank3v"/>		
			                	<input type="hidden" name="into_sus_no3v" id="into_sus_no3v"/>		
			                	<input type="hidden" name="infrom_sus_no_out3v" id="infrom_sus_no_out3v"/>		
			                	<input type="hidden" name="intype3v" id="intype3v"/>	
			                	<input type="hidden" name="instatus3v" id="instatus3v" value="0" />	
								<label id="name"></label>
							</div>
						</div>
					</div>
				 	  <div class="col-md-4">
						<div class="row form-group">
							 <div class="col-md-4">
								<strong	style="color: red;">*</strong><label class=" form-control-label"> Designation </label>
							</div>
							<div class="col-md-8">
								<label id="rank"></label>
								<input type="hidden" id="rank1" name="rank1" class="form-control autocomplete" autocomplete="off">
							</div>
						</div>
					</div>
				</div>	 				
				<div class="col-md-12">
					  <div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<strong	style="color: red;">*</strong><label class=" form-control-label"> From SUS No </label>
								</div>
								<div class="col-md-8">
									<label id="from_sus_no" name="from_sus_no"></label> 
									<input type="hidden" id="civ_id" name="civ_id" value="0" class="form-control autocomplete" autocomplete="off"/>
									<input type="hidden" id="dt_tos_pre" name="dt_tos_pre" value="" class="form-control autocomplete" autocomplete="off"/>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
								<strong	style="color: red;">*</strong>	<label class=" form-control-label"> From Unit Name </label>
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
									<strong	style="color: red;">*</strong><label class=" form-control-label"> To SUS No </label>
								</div>
								<div class="col-md-8"> 
									 <label id="to_sus_no"></label> 
 								</div> 
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<strong	style="color: red;">*</strong>	<label class=" form-control-label"> To Unit Name</label>
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
									<strong	style="color: red;">*</strong><label class=" form-control-label"> Appointment </label>
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
									<strong	style="color: red;">*</strong><label class=" form-control-label"> Date of Appointment </label>
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
									<strong	style="color: red;">*</strong><label class=" form-control-label"> Date of TOS </label>
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
									<strong style="color: red;"></strong><label class=" form-control-label"> Unit Description </label>
								</div>
								<div class="col-md-8">
									<label id="unit_description"> </label>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="card-footer" align="center" class="form-control">					
					<input type="submit" id="update" name="update" value="Update" class="btn btn-primary btn-sm" onclick="return posting_in_edit_fn()"> 
					<a href="Search_Posting_In_Out_CivilianUrl" class="btn btn-info btn-sm">Back</a>
					
					
				</div>
			</div>
		</div>
</form>
 
<script>
 var app_dt='';
$(document).ready(function() {
		
	$("#employee_no3").val('${employee_no3}');
	$("#rank").val('${rank3}');
	$("#from_sus_no_in").val('${to_sus_no3}');
	$("#from_unit_name_in").val('${from_sus_no3}');
	$("#type").val('${type3}');
	$("#inemployee_no3v").val('${employee_no3}');
	$("#inrank3v").val('${rank3}');
	$("#into_sus_no3v").val('${to_sus_no3}');
	$("#infrom_sus_no_out3v").val('${from_sus_no3}');
	$("#intype3v").val('${type3}');
	$("#instatus3v").val('${status3}');
	
	if('${size}' != ""){
		
		$("#employee_no").val('${list[0].employee_no}');	
		$("#employee_nolbl").text('${list[0].employee_no}');
		$("#name").text('${list[0].name}');		 
		$("#rank").text('${list[0].rank}');		 
		$("#from_sus_no").text('${list[0].from_sus_no}');	
		$('#to_sus_no').text('${list[0].unit_sus_no}');
		$("#app_name").val('${list[0].app_name}');
		//$("#unit_description").text('${list[0].post_unit_desc}');
		
		/* // bisag v2 240323 (status_sus_no !='INVALID' as per discuss with poonam mam) */
		
		var sus_no='${list[0].from_sus_no}';
		 $.post("getTargetUnitNameListpostinout?"+key+"="+value, {
			 sus_no:sus_no
			     }, function(j) {
			            
			     }).done(function(j) {
			    	 var length = j.length-1;
			         var enc = j[length].substring(0,16);
			         $("#from_unit_name").text(dec(enc,j[0]));   
			             
			     }).fail(function(xhr, textStatus, errorThrown) {
			     });
		  sus_no='${list[0].unit_sus_no}';
		 $.post("getTargetUnitNameList?"+key+"="+value, {
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
		if('${list[0].app_dt}'!=null){
		 app_dt =ParseDateColumncommission('${list[0].app_dt}');
		} 				
		if (app_dt != ''){
			$("#app_dt").val(convertDateFormate(app_dt));
		}
			 
		var dt_of_tos ='${list[0].dt_of_tos}'		 
		if (dt_of_tos != ''){
			$("#dt_of_tos").val(ParseDateColumncommission(dt_of_tos));			
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
	   			if(k[0].post_unit_desc != null){
		    		$('#unit_description').text(k[0].post_unit_desc);
		    	}
	   		 }
		});
	}
	personal_number();
	
	$.post('GETPostTenure_Date_Civilian?' + key + "=" + value,{ civ_id : civ_id },function(k) {
   		 if(k.length > 0){	   			
	   		setMinDate('dt_of_tos', k[0][0]);	   			
   		 }	
   		 else
   		 {
   			setMinDate('dt_of_tos', '01/01/1890');			
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
	 datepicketDate('app_dt');
	 datepicketDate('dt_of_tos');
});


	$("#from_sus_no").keyup(function(){
		var sus_no = this.value;
		var susNoAuto=$("#from_sus_no");

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
						        	$("#from_sus_no").val(dec(enc,data[0]));
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
				setMinDate('dt_of_tos', joining_date_gov_service);
				
				$("#name").text(name);
				$("#rank").text(rank);
				$("#civ_id").val(civ_id);

			 	 /* $.post('GetCensusData?' + key + "=" + value,{ civ_id : civ_id },function(k) {
		    		 if(k.length > 0)
		    		 {
		    			  $('#census_id').val(k[0][0]); 		    			  		    			 
		    		 }
		   		}); */
			});
		}
		$("input#employee_no").attr('readonly', false);
	}

function posting_in_edit_fn() {
	 
	if ($("input#employee_no").val() == "") {
		alert("Please select Employee No");
		$("input#employee_no").focus();
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

	if(joining_date_gov_service!=''){
		if(getformatedDate(joining_date_gov_service) > getformatedDate($("input#dt_of_tos").val())) {
			alert("TOS Should Be Greater Or Equal To Date of Joining Gov Service");
			$("input#dt_of_tos").focus();
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


 