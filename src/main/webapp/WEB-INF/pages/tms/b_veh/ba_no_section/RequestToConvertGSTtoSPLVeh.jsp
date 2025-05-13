<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<form:form name="#" id="#" action="ConvertRequestGstosplVehDTLAction?${_csrf.parameterName}=${_csrf.token}" method='POST' commandName="ConvertRequestGstosplVehInsertDTLCMD" enctype="multipart/form-data">
	<div class="animated fadeIn">
		<div class="">
			<div class="container" align="center">
				<div class="card">
				<div class="card-header"><h5>CONVERSION OF GS TO SPL VEH<br></h5><h6><span style="font-size: 10px;font-size:15px;color:red">(To be entered by MISO)</span></h6></div>
					<div class="card-header"><strong>Basic Details</strong></div>
					<div class="card-body card-block" id="mainViewSelection" style="display: block;">
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong style="color: red;">* </strong>SUS No </label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="sus_no" name="sus_no"   maxlength="8" class="form-control" autocomplete="off">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">Unit Name  </label>
									</div>							
									<div class="col-12 col-md-8">
										<textarea id="unit_name" name="unit_name" placeholder="" class="form-control autocomplete" style="font-size: 11px;" autocomplete="off"></textarea>
									     </div>
									
									
								</div>
							</div>
						</div>

						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong style="color: red;">* </strong> Date of Request</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="date" id="dte_of_reqst" name="dte_of_reqst" class="form-control autocomplete" min='1899-01-01' max='${date}' autocomplete="off">
									</div>
								</div>

							</div>
							<div class="col-md-6" style="display: none;">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">* </strong>Recd From</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="date" class="form-control" id="received_from"
											name="received_from" placeholder="" maxlength="50">
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">* </strong>Old BA No </label>
									</div>
									<div class="col-12 col-md-8">

										<select data-placeholder="Choose Ba Number" id="old_ba_no" name="old_ba_no" class="form-control-sm form-control">
											<option value="0">--Select--</option>
										</select>
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="card-header"><strong>Vehicle Details</strong></div>
					<div></div>
					<div class="card-body card-block">
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong style="color: red;"></strong> Old BA No </label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="old_ba_no_1" name="old_ba_no_1" placeholder="" readonly="readonly" class="form-control" autocomplete="off">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label">Chassis No</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="chasis_no" name="chasis_no" readonly="readonly" placeholder="" class="form-control" maxlength="20">
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label"><strong style="color: red;"></strong>Engine No 
										</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" class="form-control" id="engine_no" readonly="readonly" name="engine_no" placeholder="" maxlength="20">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label">Class Code of Veh </label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" class="form-control" id="old_vcode" readonly="readonly" name="old_vcode" placeholder="">
									</div>
								</div>

							</div>
						</div>

						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label">
											MCT </label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" class="form-control" id="old_mct_number"
											readonly="readonly" name="old_mct_number"
											onchange="getstdNomenclature()" placeholder="" maxlength="10">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label">
											Nomenclature </label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="old_std_nomclature"
											name="old_std_nomclature" readonly="readonly" placeholder=""
											class="form-control">
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6" style="display: none;">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label">Justification
											<strong style="color: red;">*</strong>
										</label>
									</div>
									<div class="col-12 col-md-8">
										<textarea  class="form-control" value="" id="justification"
											name="justification" maxlength="50"></textarea>
									</div>
								</div>
							</div>
							<div class="col-md-6" style="display: none;">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label">Financial
											Impact <strong style="color: red;">*</strong>
										</label>
									</div>
									<div class="col-12 col-md-8">
										<textarea class="form-control" value="k" id="financial_impact"
											name="financial_impact" maxlength="50"></textarea>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6" style="display: none;">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" value="k" class=" form-control-label">Conversion/Modification
											Carried out: <strong style="color: red;">*</strong>
										</label>
									</div>
									<div class="col-12 col-md-8">
										<textarea style="dis" class="form-control" id="conv_modCarriedOut"
											name="conv_modCarriedOut" maxlength="10"></textarea>
											
									</div>
								</div>
							</div>
							<div class="col-md-6" style="display: none;">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label">New
											Nomenclature of Veh after Mod as auth in WE : <strong
											style="color: red;">*</strong>
										</label>
									</div>
									<div class="col-12 col-md-8">
										<textarea class="form-control" value="k"
											id="newstdnomencltr_veh_aftrmod_as_auth_we" name="newstdnomencltr_veh_aftrmod_as_auth_we" maxlength="10"></textarea>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label"><strong
											style="color: red;">* </strong>Remarks  </label>
									</div>
									<div class="col-12 col-md-8">
										<textarea class="form-control" id="remarks" name="remarks"></textarea>
									</div>
								</div>
							</div>
							<div class="col-md-6"></div>
						</div>
					</div>
					<div class="card-footer" align="center">
						<input type="hidden" id="count" name="count" value='0'> 
						<a class="btn btn-success" id="addConvTmsDetails" onclick="return validate()">Save</a>
					</div>
					<div class="card-body card-block" id="tblCon" style="overflow: auto;" style="">
						<div class="col s12" >
							<table class="table no-margin table-striped  table-hover  table-bordered" id="add_reports">
								<thead style="text-align: center;">
									<tr>
									<th style="width: 10%;text-align: center;">Ser No</th>
									<th style="width: 10%;text-align: center;">BA No</th>
									<th style="width: 50%;text-align: center;">Nomenclature</th>
									<th style="width: 10%;text-align: center;">MCT No</th>
									<th style="width: 10%;text-align: center;">Remarks</th>							
									</tr>
								</thead>
								<tbody>
									<c:forEach var="item" items="${list}" varStatus="num" >
									<tr>
									   	<th style="width: 10%;text-align: center;">${item[1]}</th>
										<th style="width: 10%;text-align: center;">${item[3]}</th>
										<th style="width: 50%;text-align: center;">${item[4]}</th>
										<th style="width: 10%;text-align: center;">${item[5]}</th>
										<th style="width: 10%;text-align: center;">${item[6]}</th>
									</tr>
								</c:forEach>
								
								</tbody>
							</table>
						</div>
					</div>
					<div class="card-footer" align="center">
						<input type="reset" class="btn btn-success btn-sm" value="Clear" id="clear" onclick="clearAll();">
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>

<script>
var x = 0;
function validate(){
	 if($("#sus_no").val() == ""){
	  alert("Please Enter the SUS No.");
	  $("#sus_no").focus();
	  return false;
	} 
	if($("#unit_name").val() == ""){
	  alert("Please Enter Unit Name.");
	  $("#unit_name").focus();
	  return false;
	} 
	if($("#old_ba_no_1").val() == ""){
	  alert("Please Select  the BA No.");
	  $("#old_ba_no_1").focus();
	  return false;
	} 
	if($("#dte_of_reqst").val() == ""){
	  alert("Please Select Dt of Request.");
	  $("#dte_of_reqst").focus();
	  return false;
	}   
	
	var ba_no = $("#old_ba_no_1").val();
	var cond_ba_no = "" + ba_no[0] + ba_no[1] ;
	var curYear = (new Date()).getFullYear();  
	var sonYear = (new Date().getFullYear().toString().substr(-2));	
	var diff =  sonYear - cond_ba_no;

	/*   NITIN V4 15/11/2022  */
		/* if(diff > 8)
		{
		 	alert("Vintage of the Vehicle Should not be more than 8 years.");
		 	return false;
		} */

		/*   NITIN V4 15/11/2022  */

	
	if(old_ba_no != "")	
	{		
		var max_fields  = 100; //maximum input boxes allowed
		var sus_no = document.getElementById("sus_no").value;
		var old_ba_no = $("#old_ba_no_1").val();
		var chasis_no = $("#chasis_no").val();
		var engine_no = $("#engine_no").val();
		var old_mct_number = $("#old_mct_number").val();
		var newstdnomencltr_veh_aftrmod_as_auth_we = $("#newstdnomencltr_veh_aftrmod_as_auth_we").val();
		var conv_modCarriedOut = $("#conv_modCarriedOut").val();
		var justification = $("#justification").val();
		var financial_impact = $("#financial_impact").val();
		var remarks = $("#remarks").val();
		var stdNomenclature = $("#old_std_nomclature").val();
		var old_vcode = $("#old_vcode").val();
		var dte_of_reqst = $("#dte_of_reqst").val();
		var status = '1';
	
		if(old_ba_no != ""){
			$.ajax({
		        type: 'POST',
		        url: "addConTmsData?"+key+"="+value,
		        data: { sus_no:sus_no, engine_no:engine_no, chasis_no:chasis_no,old_ba_no:old_ba_no,old_mct_number:old_mct_number, remarks:remarks, newstdnomencltr_veh_aftrmod_as_auth_we:newstdnomencltr_veh_aftrmod_as_auth_we, conv_modCarriedOut:conv_modCarriedOut, justification:justification , financial_impact:financial_impact,dte_of_reqst:dte_of_reqst,old_vcode:old_vcode},
		       	success: function(response) {
		        	if(response == "Failure dtl"){
		        		alert("Data Already Exists.");
		        	}
		        	else if(response == "Success")
		        	{
		        		alert("Data Saved Successfully.");
		        		document.getElementById("engine_no").value = "";
		        		document.getElementById("chasis_no").value = "";
		        		document.getElementById("old_ba_no_1").value = "";
		        		document.getElementById("old_mct_number").value = "";
		        		document.getElementById("remarks").value = "";
		        		document.getElementById("old_vcode").value = "";
		        		document.getElementById("old_std_nomclature").value = "";
		        		if(x < max_fields){ //max input box allowed
					        if(old_ba_no != ""){
					        	x++;
					        	$("table#add_reports").append(
						        		'<tr id="'+x+'"><td style="text-align:center;width: 10%;">'+x+'</td>'
						        		+'<td style="text-align:center;width: 10%;">'+old_ba_no+'</td>'
						        		+'<td style="text-align:center;width: 50%;">'+stdNomenclature+'</td>'
						        		+'<td style="text-align:center;width: 10%;">'+old_mct_number+'</td>'	
						        		+'<td style="text-align:center;width: 10%;">'+remarks+'</td>'
					        		+'</tr>');	    	      
						   	}
						   	else{
					        	alert("Please Enter Manadatory Fields.");
					        	return false;
						    }
					    }else {
					    	alert("Add More Ba Number upto 100.")
					    }
			        }
		        	else
		        	{
		        		alert(response);
		        	}
		        	
			 	}
			});
		}
	}
	return true;
}
</script>

<script>
function clearAll()
{
	var tab = $("#add_reports > tbody");
 	tab.empty();
 	var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
  	$("select#old_ba_no").html(options);
 	document.getElementById("engine_no").value = "";
	document.getElementById("chasis_no").value = "";
	document.getElementById("old_ba_no_1").value = "";
	document.getElementById("old_mct_number").value = "";
	document.getElementById("remarks").value = "";
	document.getElementById("newstdnomencltr_veh_aftrmod_as_auth_we").value = "";
	document.getElementById("conv_modCarriedOut").value = "";
	document.getElementById("justification").value = "";
	document.getElementById("financial_impact").value = "";
	document.getElementById("old_vcode").value = "";
	document.getElementById("old_std_nomclature").value = "";
} 
</script>
<script>

$(function() {
	
	 	$("#unit_name").keypress(function(){
			var unit_name = this.value;
			 var susNoAuto=$("#unit_name");
			  susNoAuto.autocomplete({
			      source: function( request, response ) {
			        $.ajax({
			        	type: 'POST',
			    	    url: "getTargetUnitsNameActiveList?"+key+"="+value,
			        data: {unit_name:unit_name},
			          success: function( data ) {
			        	  var susval = [];
			        	  var length = data.length-1;
			        	  if(data.length != 0){
				        		var enc = data[length].substring(0,16);
				        	}
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
			        	  document.getElementById("sus_no").value="";
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
		    				$("#sus_no").val(dec(enc,data[0]));
					        
						  $.post("getBA_NumberList?"+key+"="+value,{sus_no:dec(enc,data[0])}, function(data) {
					            }).done(function(data) {
					           var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';	
					            if(data.length != 0)
					            {
					            	//var length = data.length-1;
									//var enc =  data[length].substring(0,16);
						 			
						 			for ( var i = 0; i < data.length; i++) {
						 				options += '<option value="' + data[i] +'" >'+ data[i] + '</option>';
						 			}	
						 			
					             }
					            $("select#old_ba_no").html(options);
					            }).fail(function(xhr, textStatus, errorThrown) {
					            });
			            }).fail(function(xhr, textStatus, errorThrown) {
			            });
			      
				} 	     
			}); 			
		});
	
	$("input#sus_no").keypress(function(){
		var sus_no = this.value;
		var susNoAuto=$("#sus_no");
		  susNoAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        	type: 'POST',
		    	    url: "getTargetSUSNoList?"+key+"="+value,
		        data: {sus_no:sus_no},
		          success: function( data ) {
		        	  var susval = [];
		        	  var length = data.length-1;
		        	  if(data.length != 0){
			        		var enc = data[length].substring(0,16);
			        	}
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
		        	  alert("Please Enter Approved SUS No.");
		        	  document.getElementById("unit_name").value="";
		        	  susNoAuto.val("");	        	  
		        	  susNoAuto.focus();
		        	  return false;	             
		          }   	         
		      }, 
		      select: function( event, ui ) {
		    	var sus_no = ui.item.value;
		    
		        $.post("getActiveUnitNameFromSusNo?"+key+"="+value,{sus_no:sus_no}, function(data) {
		            }).done(function(data) {
		        		
		  			  	var length =  data.length-1;
			        	var enc =  data[length].substring(0,16);
			        	$("#unit_name").val(dec(enc, data[0]));	
				      
					  $.post("getBA_NumberList?"+key+"="+value,{sus_no:sus_no}, function(data) {
				            }).done(function(data) {
				            	var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
				            	  if(data.length != 0)
						            {
				            			//var length =  data.length-1;
										//var enc =  data[length].substring(0,16);
							 			
							 			for ( var i = 0; i < data.length; i++) {
							 				options += '<option value="' + data[i] +'" >'+ data[i] + '</option>';
							 				
							 			}	
				 					
						            }
				            	  $("select#old_ba_no").html(options);
				            }).fail(function(xhr, textStatus, errorThrown) {
				            });
					  
		            }).fail(function(xhr, textStatus, errorThrown) {
		            }); 	
 			     } 	     
		});	
	});	
	
	
	$('select#old_ba_no').change(function() {
		document.getElementById("remarks").value = "";
		document.getElementById("newstdnomencltr_veh_aftrmod_as_auth_we").value = "";
		document.getElementById("conv_modCarriedOut").value = "";
		document.getElementById("justification").value = "";
		document.getElementById("financial_impact").value = "";
			
		var ba_no = this.value;
            $.post("getEngineChasisFromBANoList?"+key+"="+value, {ba_no : ba_no}, function(data) { }).done(function(data) {
        
              var length = data.length-1;
  	          var enc = data[length][0].substring(0,16);
  	          $("input#old_ba_no_1").val(ba_no);
  	          $("#chasis_no").val(dec(enc,data[0][0]));
  	          $("input#engine_no").val(dec(enc,data[0][1]));
  	          $("#old_mct_number").val(dec(enc,data[0][2]));
  	         
  	          $("#old_std_nomclature").val(dec(enc,data[0][3]));
  	          $("#old_vcode").val(ba_no[2]);
        }).fail(function(xhr, textStatus, errorThrown) {
        });
    });	
});

</script>
