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
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>


<form id="Edit_Anm_Decr" action="Edit_Anm_Decr_Action?${_csrf.parameterName}=${_csrf.token}" method="post"  commandName="Edit_Anm_Decr" >
		<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<h5> Update Str Decr </h5>
					<h6 class="enter_by">
						<span style="font-size: 12px; color: red;">(To be entered by UNIT)</span>
					</h6>
				</div>
				<div class="card-body card-block">
				<div class="col-md-12">
					<div class="col-md-4">
						<div class="row form-group">
							<div class="col-md-5">
								<strong	style="color: red;">* </strong><label class=" form-control-label"> Army No </label>
							</div>
							<div class="col-md-7">
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
								<input type="hidden" id="id" name="id" class="form-control autocomplete" autocomplete="off" value="${updateid}"/>
								<input type="hidden" id="personnel_no" name="personnel_no" class="form-control autocomplete" autocomplete="off" readonly="true" onchange="personal_number()"/>
 								<label id="personnel_nolbl"></label>
 							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="row form-group">
							<div class="col-md-4">
								<strong	style="color: red;">* </strong><label class=" form-control-label"> Name </label>
							</div>
							<div class="col-md-8">	
								<label id="name"></label> 
							</div>
						</div>
					</div>
				 	<div class="col-md-4">
							<div class="row form-group">
								 <div class="col-md-4">
									<strong	style="color: red;">* </strong><label class=" form-control-label"> Microchip No </label>
								</div>
								<div class="col-md-8">
									<label id="rank" name="rank" > </label> 
								</div>
							</div>
						</div>
					</div>
				<div class="col-md-12">
					  <div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<strong	style="color: red;">* </strong><label class=" form-control-label">From Unit SUS No </label>
								</div>
								<div class="col-md-8">
								<label id="from_sus"></label>
									</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<strong	style="color: red;">* </strong><label class=" form-control-label">From Unit Name </label>
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
									<input type="text" id="out_auth" name="out_auth" class="form-control autocomplete" autocomplete="off"
									maxlength="50" onkeyup="return specialcharecter(this)" />
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
									<strong	style="color: red;">* </strong><label class=" form-control-label">To Unit SUS No </label>
								</div>
								<div class="col-md-8">
									<input type="text" id="to_sus_no" name="to_sus_no" class="form-control autocomplete" autocomplete="off"
									maxlength="8" onkeypress="return AvoidSpace(event)" onkeyup="specialcharecter(this)"/>
									<input type="hidden" id="dt_tos_pre" name="dt_tos_pre" value="" class="form-control autocomplete" autocomplete="off"/>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<strong	style="color: red;">* </strong><label class=" form-control-label">To Unit Name </label>
								</div>
								<div class="col-md-8">
									<input type="text" id="unit_name" name="unit_name" class="form-control autocomplete" autocomplete="off"
									maxlength="50" onkeyup="specialcharecter(this)" />
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
					</div>
				</div>

				<div class="card-footer" align="center" class="form-control">					
					<input type="submit" id="update" name="update" value="Update" class="btn btn-primary btn-sm" onclick="return posting_out_edit_fn()"> 
					<a href="Search_Posting_OutUrl" class="btn btn-success btn-sm">Back</a>
				</div>
			</div>
		</div>	
</form>

<script>

var dt_tos='';
$(document).ready(function() {

	if('${size}' != ""){
		
		$("#personnel_no").val('${list[0].army_no}');	
		$("#personnel_nolbl").text('${list[0].army_no}');	
		$("#name").text('${list[0].name}');
		$("#rank").text('${list[0].microchip_no}');	
		$("#out_auth").val('${list[0].out_auth}');	
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
		 
		 sus_no='${list[0].unit_sus_no}';
		 $.post("getTargetUnitNameListpostinout?"+key+"="+value, {
			 sus_no:sus_no
			     }, function(j) {
			            
			     }).done(function(j) {
			    	 var length = j.length-1;
			         var enc = j[length].substring(0,16);
			         $("#unit_name").text(dec(enc,j[0]));   
			             
			     }).fail(function(xhr, textStatus, errorThrown) {
			     });

		var dt_of_sos ='${list[0].dt_of_sos}';
		console.log(" h'${list[0].dt_of_sos}' h" + dt_of_sos);
		if (dt_of_sos != ''){
			$("#dt_of_sos").val(ParseDateColumncommission(dt_of_sos));
		}
		var census_id='${list[0].census_id}';
		$("#census_id").val(census_id);
		console.log("GetArmyNoDataAnimalApproved census_id " + census_id);
		$.post('GetArmyNoDataAnimalApproved?' + key + "=" + value,{ census_id : census_id},function(k) {
   		 if(k.length > 0)
   		 {
   			console.log("GETPostTenure_Date_Animal k[0[]] " + k[0][5]);
   			if(k[0][5] != null)
	    	{
	    		dt_tos=ParseDateColumncommission(k[0][5]);
	    		
	    		$("#dt_tos_pre").val(dt_tos);
	    	}
	    	else{
	    		dt_tos='';
	    	}
   		 }
		});
	}
	console.log("GETPostTenure_Date_Animal census_id " + census_id);
	$.post("GETPostTenure_Date_Animal?" + key + "=" + value,{ census_id_animal : census_id },function(k) {
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

</script>

<script>

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

</script>

<script>

function posting_out_edit_fn() {
	debugger;
	var out_auth_dt = $('#out_auth_dt').val();
	if ($("input#personnel_no").val() == "") {
		alert("Please select Personal No");
		$("input#personnel_no").focus();
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
	console.log("dt_tos  ---> " + dt_tos);
	
	if(dt_tos!=null && dt_tos!=""){
			var newtos = $("input#dt_of_sos").val().split("/");
			var pretos=dt_tos.split("/");			
			var newtosM=Number(newtos[1]);
			var newtosY=newtos[2];			
			var pretosM=Number(pretos[1]);
			var pretosY=pretos[2];
			console.log("newtos --->" + newtos)
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
		
	
	if(comm_date!=''){
		if(getformatedDate(out_auth_dt) < getformatedDate(comm_date)) {
			alert("Date of Auth Should Be Greater Then Date of Commission");
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
	if(comm_date!=''){
		if(getformatedDate($("input#dt_of_sos").val()) < getformatedDate(comm_date)) {
			alert("SOS Should Be Greater Then Date of Commission");
			$("input#dt_of_sos").focus();
			return false;
		}
	}
	
	
}

</script>