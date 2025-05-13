<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<script src="js/dropDown/select2.min.js"></script>
<link rel="stylesheet" href="js/dropDown/select2.min.css">
<script>
var $select2 = $('.select2').select2({
    containerCssClass: "wrap"
});
</script>

<form:form name="tmsbanumdir" id="tmsbanumdir" action="type_veh_link_Action?${_csrf.parameterName}=${_csrf.token}" method='POST' commandName="type_veh_linkCmd" >
	<div class="animated fadeIn">
		<div class="">
			<div class="container" align="center">
				<div class="card">
					<div class="card-header"><h5>ADD VEHICLE TO DIRECTOR FOR  PROCUREMENT
					<br></h5>
			<!-- 		<h6><span style="font-size: 12px;color:red">(To be entered by Depot)</span></h6> -->
					</div>
			
					<div class="card-header"><strong>Link Directory Report</strong></div>
					<div class="card-body card-block">
					
					
					
					<div class="col-md-12">
							
										<div class="col-md-5">
								<div class="row form-group">
									<div class="col-md-5">
										<label class=" form-control-label"><strong style="color: red;">*</strong> SUS No </label>
									</div>
									<div class="col-md-7">
										<input type="text" id="sus_no" name="sus_no" class="form-control"  autocomplete="off" maxlength="8">
									</div>
								</div>
							</div>
								<div class="col-md-5">
								<div class="row form-group">
									<div class="col-md-5">
										<label class=" form-control-label"><strong style="color: red;">*</strong>UNIT NAME</label>
									</div>
									<div class="col-md-7">
										<input type="text" id="unit_name" name="unit_name" onkeyup="this.value = this.value.toUpperCase();"  class="form-control" autocomplete="off" >
									</div>
								</div>
								</div> 
								
								</div> 
						<div class="col-md-12">
    						<div class="col-md-5">
								<div class="row form-group">
							    	<div class="col-md-5">
							        	<label class=" form-control-label"><strong style="color: red;">*</strong> VEH CAT</label>
							       	</div>
							        <div class="col-md-7">
										<select id="type_veh" name="type_veh" class="form-control-sm form-control" onchange="getPRFList(this.value)" style="width: 100%">
											<option value="">--Select--</option>
											<option value="0">A Vehicles</option>
											<option value="1">B Vehicles</option>
											<option value="2">C Vehicles</option>
										</select>
									</div>
								</div>		
							</div>
							<div class="col-md-5">
								<div class="row form-group">
							    	<div class="col-md-5">
							        	<label class=" form-control-label"><strong style="color: red;">*</strong> VEH TYPE<br>Multiple Selection</label>
							       	</div>
							        <div class=" col-md-7">
										<select id="prf_code" name='prf_code'  class="form-control-sm form-control"  style="width: 100%">
										</select>
									</div>
								</div>		
							</div>
						</div>
						
				
	 			<!-- 		<div class="col-md-12">
						 	<div class="col-md-5">
								<div class="row form-group">
									<div class="col-md-5">
										<label class=" form-control-label"> <span style="color: red;" id="nonArmy">*</span> MCT</label>
									</div>
									<div class="col-md-7">
										<input type="text" id="mct_number" name="mct_number" onkeypress="return isNumber0_9Only(event);" placeholder="Max Ten Digits Integer Value" class="form-control autocomplete" autocomplete="off" maxlength="10">
										<input type="hidden" value =0 id="mct_number" name="mct_number">
									</div>
								</div>
							</div>
							
							 	<div class="col-md-5">
								<div class="row form-group">
									<div class="col-md-5">
										<label class=" form-control-label"> <span style="color: red;" id="nonArmy">*</span> NOMENCLATURE</label>
									</div>
									<div class="col-md-7">
											<textarea id="new_nomencatre" name="new_nomencatre" class="form-control autocomplete"  autocomplete="off" style="font-size: 11px;" maxlength="300"></textarea>
										
									</div>
								</div>
							</div>
						
						</div>  -->
 		
    				<!-- 	<div class="col-md-12">
							<div class="col-md-5">
								<div class="row form-group">
	                				<div class="col col-md-5">
	                  					<label class=" form-control-label">TO KM RUN</label>
	                				</div>
	                				<div class="col-12 col-md-7">
	                 				
	                 					
	                 					<input type="text" id="to_km_run"  name="to_km_run" onkeyup="this.value = this.value.toUpperCase();"  class="form-control" autocomplete="off" >onkeypress="return blockSpecialChar(event)
	                				</div>
				            	</div>
							</div>
							<div class="col-md-5">
								<div class="row form-group">
									<div class="col-md-5">
										<label class=" form-control-label"><strong style="color: red;">*</strong> VINTAGE</label>
									</div>
									<div class="col-md-7">
										<input type="text" id="vintage" name="vintage" onkeyup="this.value = this.value.toUpperCase();"  class="form-control" autocomplete="off" >onkeypress="return blockSpecialChar(event)
									</div>
								</div>
								
								</div> 
								
							</div>  -->
							
					<!-- 		<div class="col-md-12">
							<div class="col-md-5">
								<div class="row form-group">
	                				<div class="col col-md-5">
	                  					<label class=" form-control-label">Turn Type</label>
	                				</div>
	                				<div class="col-12 col-md-7">
	                 				
	                 					
	                 					<input type="text" id="turn_type"  name="turn_type" onkeyup="this.value = this.value.toUpperCase();"  class="form-control" autocomplete="off" >
				            	</div>
							</div>
							
							</div> 
						</div>
							 -->
						
						 <div class="col-md-6">
							<label class=" form-control-label" style="margin-right: 100px;"><h4>Details of MCT/NOMEN </h4></label></div>
							
						
						
						
										<br>
						<div class="col-md-12">
							<table id="reservation_table" class="table-bordered "
								style="margin-top: 10px; width: 100%;">

								<thead style="color: white; text-align: center;">
									<tr>
										<th style="width: 5%;">Sr No</th>
									  <th style="width: 15%;">MCT </th>
									     <th style="width: 15%;">NOMENCLATURE </th> 
										<th style="width: 10%;">Action</th>
									</tr>
								</thead>
								<tbody data-spy="scroll" id="reser_tbody"
									style="min-height: 46px; max-height: 650px; text-align: center;">
									<tr id="tr_reservation1">
										<td class="sib_srno" >1</td>
																
										<!-- <td style="width: 20%;"><input type="date"
											id="sib_date_of_birth1" name="sib_date_of_birth1" class="form-control autocomplete" autocomplete="off">
										</td> -->
										
										
										
										<td style="display: none;"><input type="text"
											id="rese_ch_id1" name="sib_ch_id1" value="0" class="form-control autocomplete" autocomplete="off">
										</td>
										<td style="display: none;"><input type="text"
											id="rese_ch_id1" name="rese_ch_id1" value="0" class="form-control autocomplete" autocomplete="off">
										</td>
					<td>
								
									<div class="col-md-12">
										<input type="text" id="mct_number1" name="mct_number1" onkeyup="MCTAutocomplete(1);return isNumber0_9Only(event);"  class="form-control autocomplete" autocomplete="off" maxlength="10">
										
									</div>
								</td>					
							<td>
								<div class="col-md-12">
							<input type="text" id="new_nomencatre1" name="new_nomencatre1" class="form-control autocomplete"   autocomplete="off" maxlength="300">
										
									</div>
								</td>
								
										
											
											
										<td style="width: 10%;">

											
											<a  class="btn btn-success btn-sm" value="ADD" title="ADD" id="reservation_add1"
											onclick="reservation_add_fn(1);"><i class="fa fa-plus"></i></a> 
											
											<a style="display: none;" class="btn btn-danger btn-sm" value="REMOVE" title="REMOVE" id="reservation_remove1"
											onclick="reservation_remove_fn(1);"><i class="fa fa-trash"></i></a>
										</td>
									</tr>
								</tbody>
							</table>
							<input type=hidden id="reservation_count" name="reservation_count"
									class="required form-control" autocomplete="off" value="1" /> <input
									type="hidden" id="reservationOld_count" name="reservationOld_count"
									class="form-control" maxlength="2" autocomplete="off" value="0">
						</div>
						
						
						
						
					</div>
					
					<input type="hidden" id="count" name="count" >
					<div class="form-control card-footer" align="center">
						<a href="Line_directory_report_url" type="reset" class="btn btn-success btn-sm"> Clear </a> 						
						<input type="submit" class="btn btn-primary btn-sm" value="Submit" ><!--  --> 
					
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>

<script>

function mcthideshow()
{
	var r = $('input:radio[name=ba_no_type]:checked').val();
	if (r == '1')
	{
		$("#mct_number").attr('readonly',true).css("background-color", "#e9ecef"); 
		$("#new_nomencatre").attr('readonly',true).css("background-color", "#e9ecef"); 
		$("span#nonArmy").hide();
		$("#vehicle_clas_code").attr('readonly',false).css("background-color", "white"); 	
	}else{
		$("span#nonArmy").show();
		$("#vehicle_clas_code").attr('readonly',true).css("background-color", "#e9ecef"); 	
		$("#mct_number").attr('readonly',false).css("background-color", "white"); 
		$("#new_nomencatre").attr('readonly',false).css("background-color", "white"); 
	}
}
</script>
<script>
function blockSpecialChar(e){
	var k;
    document.all ? k = e.keyCode : k = e.which;
    return ((k > 64 && k < 91));
}
</script>

<script>
	function isNumber0_9Only(evt) {
		var charCode = (evt.which) ? evt.which : evt.keyCode;
		if (!(charCode >= 48 && charCode <= 57) && charCode != 8) {
			return false;
		}
		return true;
	}
 	
	function validate() {
		var r = $('input:radio[name=ba_no_type]:checked').val();
		if (r == undefined) {
			alert("Please Select Army/Non-Army.");
			$('input:radio[name=ba_no_type]:checked').focus();
			return false;
		}
		if ($("#unit_name").val() == "") {
			alert("Please Enter Unit/Est.");
			$("#unit_name").focus();
			return false;
		}
		if ($("#sus_no").val() == "") {
			alert("Please Enter SUS No.");
			$("#sus_no").focus();
			return false;
		}
		if ($("#dte_of_reqst").val() == "") {
			alert("Please Enter Date of Request.");
			$("#dte_of_reqst").focus();
			return false;
		}
		if ($("select#type_veh").val() == "0") {
			alert("Please Select Type of Vehicle.");
			$("#type_veh").focus();
			return false;
		}
		if(r =='0'){
			if ($("#mct_number").val() == "") {
				alert("Please Enter MCT No.");
				$("#mct_number").focus();
				return false;
			}
		}
		if ($("#vehicle_clas_code").val() == "") {
			alert("Please Enter Vehicle Class Code.");
			$("#vehicle_clas_code").focus();
			$("#vehicle_clas_code").attr('readonly',false).css("background-color", "white");
			return false;
		}
		
		if ($("#year_of_entry").val() == "") {
			alert("Please Enter Yr of Entry into Service.");
			$("#year_of_entry").focus();
			return false;
		}
		if ($("#auth_letter_no").val() == "") {
				alert("Please Enter Supply Order No.");
				$("#auth_letter_no").focus();
				return false;
		}
		if($("#initiating_auth").val() == ""){
			alert("Please Enter Initiating Auth.");
			$("#initiating_auth").focus();
			return false;
		}
		if($("#date_of_auth_letter").val() == ""){
			alert("Please Enter Auth Date.");
			$("#date_of_auth_letter").focus();
			return false;
		}
		if ($("#purchas_cost").val() == "") {
			alert("Please Enter Purchase Cost.");
			$("#purchas_cost").focus();
			return false;
		}
		if($("#front_view_image1").get(0).files.length == 0){
			alert("Please Select Fornt View Image.");
			$("#front_view_image1").focus();
			return false;
		}
		if($("#back_view_image1").get(0).files.length == 0){
			alert("Please Select Rear View Image.");
			$("#back_view_image1").focus();
			return false;
		}
		if($("#side_view_image1").get(0).files.length == 0){
			alert("Please Select Left Side View Image.");
			$("#side_view_image1").focus();
			return false;
		}
		if($("#top_view_image1").get(0).files.length == 0){
			alert("Please Select Right Side View Image.");
			$("#top_view_image1").focus();
			return false;
		} 
		if($("#quantity").val() == ""){
			alert("Please Enter Quantity.");
			$("#quantity").focus();
			return false;
		}
		if ($("#engine_no1").val() == "") {
			alert("Please Enter Engine No.");
			$("#engine_no1").focus();
			return false;
		}
		if ($("#chasis_no1").val() == "") {
			alert("Please Enter Chassis No.");
			$("#chasis_no1").focus();
			return false;
		}
		
		var quantity = $("#quantity").val();
		// Check Engine No
		var EngineNO="";
		var ChasisNO="";
		for(var i = 1; i <= quantity ; i++){
			if($("#engine_no"+i).val() != ""){
				if(i == 1){
					EngineNO = $("#engine_no"+i).val();
				}else{
					EngineNO = EngineNO +","+ $("#engine_no"+i).val();
				}
			}else{
				alert("Please Enter Engine No.");
				$("#engine_no"+i).focus();
				return false;
			}		
			// start check Eng Exist
			var strArrayEng = EngineNO.split(",");
			var alreadySeenEng = [];
			var Engtest ="";
			strArrayEng.forEach(function(str) {
				if (alreadySeenEng[str]){
					Engtest = str;
			  	}else{
			  		alreadySeenEng[str] = true;
			  	}
			});
			if(Engtest != ""){
				alert("Already Exist Engine No." + Engtest);
				return false;
			}
			// end check Eng Exist
			if($("#chasis_no"+i).val() != ""){
				if(i == 1){
					ChasisNO = $("#chasis_no"+i).val();
				}else{
					ChasisNO = ChasisNO +","+ $("#chasis_no"+i).val();
				}
			}else{
				alert("Please Enter Chassis No.");
				$("#chasis_no"+i).focus();
				return false;
			}	
			
			var strArrayCha = ChasisNO.split(",");
			var alreadySeenCha = [];
			var Chatest ="";
			strArrayCha.forEach(function(str) {
				if (alreadySeenCha[str]){
					Chatest = str;
			  	}else{
			  		alreadySeenCha[str] = true;
			  	}
			});
			if(Chatest != ""){
				alert("Already Exist Chassis No." + Chatest);
				return false;
			}
			if($("#engine_image"+i).get(0).files.length == 0){
				alert("Please Select Pencil Rubbing of Engine.");
				$("#engine_image"+i).focus();
				return false;
			}			
			if($("#chasis_image"+i).get(0).files.length == 0){
				alert("Please Select  Pencil Rubbing of Chassis.");
				$("#chasis_image"+i).focus();
				return false;
			}
		}
		
		if(EngineNO != ""){
			var engCheck = checkExistEng(EngineNO);
			if(engCheck != "0"){
				alert("Already Exist Engine No. " + engCheck);
				return false;
		    }
		}		
		if(ChasisNO != ""){
			var chaCheck =  checkExistCha(ChasisNO);
			if(chaCheck != "0"){
				alert("Already Exist Chassis No. " + chaCheck);
				return false;
		    }
		}
		var k = confirm("Please Check the Details filled for Correctness.\nData Once Submitted is not Amendable.");		
		if (k == true) {
			return true;
		} else {
			return false;
		}
	} 
		
	function checkExistEng(EngineNO){
		var test = "";
     	$.ajax({
			url : "checkIfExistEngineNO?"+key+"="+value,
			type : 'POST',
			data : {EngineNO : EngineNO},
			success : function(data) {
				if(data.length != 0){
					test = data;
				}else{
					test ="0";
				}
			},
			async : false,
		});
     	return test;
    }
	
	function checkExistCha(ChasisNO){
		var test = "";
		$.ajax({
			url : "checkIfExistChasisNO?"+key+"="+value,
			type : 'POST',
			data : {ChasisNO : ChasisNO},
			success : function(data) {
				if(data.length != 0){
					test = data;
				}else{
					test ="0";
				}
			},
			async : false,
		});
		return test;
	}
	
	function checkFileExtImage(file) {
	  	var ext = file.value.match(/\.([^\.]+)$/)[1];
		switch (ext) {
		  	case 'jpg':
		  	case 'jpeg':
		  	case 'png':
		  	case 'JPG':
		  	case 'JPEG':
		  	case 'PNG':
		 	//alert('Allowed');
		    break;
		  	default:
		    	alert('Only *.jpg, *.jpeg and *.png file extensions allowed');
		   	file.value = "";
		}
	}
	function typeofveh(obj) {
		if (obj.value == 1) {
			if ($("#type_veh").val() == "A" || $("#type_veh").val() == "C")
			{
				var option = '<option value="2">2</option>';
				$("select#no_of_wheels").html(option);
				$('select#no_of_wheels').attr('readonly', true);
			} else {
				var option = '<option value="0">--Select--</option>'
						+ '<option value="2">2</option>'
						+ '<option value="4">4</option>'
						+ '<option value="6">6</option>'
						+ '<option value="8">8</option>'
						+ '<option value="10">10</option>'
						+ '<option value="12">12</option>';
				$("select#no_of_wheels").html(option);
				$('select#no_of_wheels').attr('readonly', false);
			}
		} else {
			var option = '<option value="0">--Select--</option>'
					+ '<option value="2">2</option>'
					+ '<option value="4">4</option>'
					+ '<option value="6">6</option>'
					+ '<option value="8">8</option>'
					+ '<option value="10">10</option>'
					+ '<option value="12">12</option>';
			$("select#no_of_wheels").html(option);
			$('select#no_of_wheels').attr('readonly', false);

		}
	}

	function ClearField() {
		document.getElementById('addQuantity').style.display = 'block';
		document.getElementById("unit_name").value = "";
		document.getElementById("sus_no").value = "";
		document.getElementById("type_veh").value = "0";
		document.getElementById("mct_number").value = "";
		document.getElementById("new_nomencatre").value = "";
		document.getElementById("vehicle_clas_code").value = "";
		document.getElementById("type_of_fuel").value = "1";
		document.getElementById("no_of_wheels").value = "0";
		document.getElementById("no_of_axles").value = "0";
		document.getElementById("axle_wts").value = "";
		document.getElementById("drive").value = "";
		document.getElementById("tonnage").value = "0";
		document.getElementById("towing_capcty").value = "0";
		document.getElementById("lift_capcty").value = "0";
		document.getElementById("sponsoring_dte").value = "";
		document.getElementById("auth_letter_no").value = "";
		document.getElementById("initiating_auth").value = "";
		document.getElementById("date_of_auth_letter").value = "";
		document.getElementById("spl_eqpmnt_fitd").value = "";
		document.getElementById("purchas_cost").value = "";
		document.getElementById("front_view_image1").value = "";
		document.getElementById("back_view_image1").value = "";
		document.getElementById("side_view_image1").value = "";
		document.getElementById("top_view_image1").value = "";
		document.getElementById("typ_of_spl_eqpt_role").value = "";
		document.getElementById("quantity").value = "";
	}
	
	
	
	
	function getPRFList(val)
	{
		getMCTMainList('');
		var options = '<option value="0">'+ "--Select--" + '</option>';
		if(val !="")
		{
		    $.post("getTptSummaryInPRFList?"+key+"="+value,{type : val}).done(function(j) {
				for ( var i = 0; i < j.length; i++) {
					if(j[i].prf_code == '${prf_code}'){
						options += '<option value="'+j[i].prf_code+'" name="' + j[i].group_name+ '" selected=selected>'+ j[i].group_name+ '</option>';
						getMCTMainList('${prf_code}')
					}else{
						options += '<option value="'+j[i].prf_code+'" name="' + j[i].group_name+ '" >'+ j[i].group_name+ '</option>';
					}
				}	
				$("select#prf_code").html(options); 
		    }).fail(function(xhr, textStatus, errorThrown) {
			});
		}
		else {
			$("select#prf_code").html(options);
		}
	}
	
	
	
	function getMCTMainList(val)
	{
		var options = '<option value="0">'+ "--Select--" + '</option>';
		if(val !="")
		{
		    $.post("getMCtMain_from_prf_code?"+key+"="+value,{prf_code : val}).done(function(j) {
				for ( var i = 0; i < j.length; i++) {
					if(j[i].mct_main_id == '${mct_main}'){
						options += '<option value="'+j[i].mct_main_id+'" name="' + j[i].mct_nomen+ '" selected=selected>'+ j[i].mct_nomen+ '</option>';
					}else{
						options += '<option value="'+j[i].mct_main_id+'" name="' + j[i].mct_nomen+ '" >'+ j[i].mct_nomen+ '</option>';
					}
				}	
				$("select#mct_main").html(options); 
		    }).fail(function(xhr, textStatus, errorThrown) {
			});
		}
		else {
			$("select#mct_main").html(options);
		}
	}
	
</script>



<script type="text/javascript">
	$(function() {    
		$("#front_view_image1").change(function(){	
			checkFileExtImage(this);
		});
		$("#back_view_image1").change(function(){	
			checkFileExtImage(this);
		});
		$("#side_view_image1").change(function(){	
			checkFileExtImage(this);
		});
		$("#top_view_image1").change(function(){	
			checkFileExtImage(this);
		});
	 	$("#unit_name").keypress(function(){
			var unit_name = this.value;
		 	var susNoAuto=$("#unit_name");
		  	susNoAuto.autocomplete({
		  	source: function( request, response ) {
		        $.ajax({
		        	
		        	type: 'POST',
		    	    url: "getReqAgencyList?"+key+"="+value,
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
		        	alert("Please Enter Unit/Est.");
		        	document.getElementById("unit_name").value="";
		        	susNoAuto.val("");	        	  
		        	susNoAuto.focus();
		        	return false;	             
		    	}   	         
		    }, 
		    select: function( event, ui ) {
		      	var ReName = ui.item.value;
		      
		                $.post("getSusNoFromReqAgency?"+key+"="+value, {req_agency : ReName}, function(data) {
		           
		            }).done(function(data) {
						    var length = data.length-1;
				        	var enc = data[length].substring(0,16);
				        	$("#sus_no").val(dec(enc,data[0]));
		            }).fail(function(xhr, textStatus, errorThrown) {
		            });
			}	     
		});
	});	
        
 
		$("input#sus_no").keyup(function(){
			var sus_no = this.value;
			var unitNameAuto=$("#sus_no");
			unitNameAuto.autocomplete({
			      source: function( request, response ) {
			        $.ajax({
			        	type: 'POST',
			    	    url: "getReqAgencySusNoList?"+key+"="+value,
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
			        	  $("#req_agency").val("");
			        	  unitNameAuto.val("");	        	  
			        	  unitNameAuto.focus();
			        	  return false;	             
			          }   	         
			      }, 
			      select: function( event, ui ) {
			      	var susnumber =  ui.item.value;
			    	
			            $.post("getReqAgencyFromSusNo?"+key+"="+value,  {sus_no : susnumber}, function(data) {
			            }).done(function(data) {
			            	var length = data.length-1;
	        				var enc = data[length].substring(0,16);
	        				$("#unit_name").val(dec(enc,data[0]));	
			            }).fail(function(xhr, textStatus, errorThrown) {
			            });
			     }
			});
		});	
			
	    $('#type_veh').change(function() {
	    	$("#mct_number").val("");
	    	$("#new_nomencatre").val("");
	    	var type_of_vehicle = this.value;     
	    });
	
	 
	});
		

 	$("#unit_name").keypress(function(){
		var unit_name = this.value;
	 	var susNoAuto=$("#unit_name");
	  	susNoAuto.autocomplete({
	  	source: function( request, response ) {
	        $.ajax({
	        	
	        	type: 'POST',
	    	    url: "getReqAgencyList?"+key+"="+value,
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
	        	alert("Please Enter Unit/Est.");
	        	document.getElementById("unit_name").value="";
	        	susNoAuto.val("");	        	  
	        	susNoAuto.focus();
	        	return false;	             
	    	}   	         
	    }, 
	    select: function( event, ui ) {
	      	var ReName = ui.item.value;
	      
	                $.post("getSusNoFromReqAgency?"+key+"="+value, {req_agency : ReName}, function(data) {
	           
	            }).done(function(data) {
					    var length = data.length-1;
			        	var enc = data[length].substring(0,16);
			        	$("#sus_no").val(dec(enc,data[0]));
	            }).fail(function(xhr, textStatus, errorThrown) {
	            });
		}	     
	});
});	
	
	
	$("input#sus_no").keyup(function(){
		var sus_no = this.value;
		var unitNameAuto=$("#sus_no");
		unitNameAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        	type: 'POST',
		    	    url: "getReqAgencySusNoList?"+key+"="+value,
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
		        	  $("#req_agency").val("");
		        	  unitNameAuto.val("");	        	  
		        	  unitNameAuto.focus();
		        	  return false;	             
		          }   	         
		      }, 
		      select: function( event, ui ) {
		      	var susnumber =  ui.item.value;
		    	
		            $.post("getReqAgencyFromSusNo?"+key+"="+value,  {sus_no : susnumber}, function(data) {
		            }).done(function(data) {
		            	var length = data.length-1;
        				var enc = data[length].substring(0,16);
        				$("#unit_name").val(dec(enc,data[0]));	
		            }).fail(function(xhr, textStatus, errorThrown) {
		            });
		     }
		});
	});	
</script>


<script>
aller=1;
function reservation_add_fn(q){


	if(q!=0){
	$("#reservation_add"+aller).hide();
	$("#reservation_remove"+aller).hide();
	}
	aller=q+1;

	 $("input#reservation_count").val(aller);
	

	$("table#reservation_table").append('<tr align="center" id="tr_reservation'+aller+'"><td style="width: 2%;">'+aller+'</td>'
	
	+'<td style="width: 20%;"><input type="text"   id="mct_number'+aller+'"  name="mct_number'+aller+'" class="form-control autocomplete" autocomplete="off" onkeypress="return MCTAutocomplete('+aller+');" > </td> '
	
    +'<td style="width: 20%;"><input type="text"   id="new_nomencatre'+aller+'"  name="new_nomencatre'+aller+'" class="form-control autocomplete" autocomplete="off" > </td> '
	
	+'<td style="display: none;"><input type="text" id="rese_ch_id'+aller+'" name="rese_ch_id'+aller+'" value="0" class="form-control autocomplete" autocomplete="off"></td>'
	+'<td style="width: 10%;"><a class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "reservation_add'+aller+'" onclick="reservation_add_fn('+aller+');" ><i class="fa fa-plus"></i></a> <a class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "reservation_remove'+aller+'" onclick="reservation_remove_fn('+aller+');"><i class="fa fa-trash"></i></a></td>' 
    +'</tr>');
	
		

		 if(aller==1){
			 $("#reservation_remove"+aller).hide();
			 
		 }	
		
}

function reservation_remove_fn(R){
	
	 $("tr#tr_reservation"+R).remove();	
	 aller=aller-1
	 $("input#reservation_count").val(aller);
	 $("#reservation_add"+aller).show();
	 if(aller!=1){
	 $("#reservation_remove"+aller).show();
	 }	
}



function MCTAutocomplete(ser){
	
// alert(ser);
// 	$("input#mct_number"+ser).keyup(function(){
		var mct = $("input#mct_number"+ser).val();
		var mct_numberAuto=$("input#mct_number"+ser);
// 		alert(mct_numberAuto);
		var type_of_vehicle = $("#type_veh").val();
		var prf_code = $("#prf_code").val();
		
		 if(type_of_vehicle == ""){
			alert("Please Select Type of Vehicle.");
			$("#type_veh").focus();
			$("#new_nomencatre1").val("");
	    	mct_numberAuto.val("");
			return false;
		} 
		mct_numberAuto.autocomplete({
			source: function( request, response ) {
			$.ajax({
				type: 'POST',
				url: "getMctNo4DigitDetailList?"+key+"="+value,
				data: {mct:mct ,prf_code:prf_code},
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
					alert("Please Enter MCT No.");
			        $("#new_nomencatre"+ser).val("");
			        mct_numberAuto.val("");	        	  
			        mct_numberAuto.focus();
			        return false;
				}
			},
		  	select: function( event, ui ) {
		  		
		      	var mct = ui.item.value;
		        $.post("getMctNo4DigitNomenDetailList?"+key+"="+value,{mct : mct}, function(data) {
	            }).done(function(data) {
	            	
	            	var length = data.length-1;
			        var enc = data[length].substring(0,16);
	      		  	$("#new_nomencatre"+ser).val(dec(enc,data[0]));
	      		 
	      		  
			}).fail(function(xhr, textStatus, errorThrown) { });
		}
	});
// 	});
}


	
$('#type_veh').change(function() {
	$("#mct_number").val("");
	$("#new_nomencatre1").val("");
	var type_of_vehicle = this.value;     
});

$("input#sponsoring_dte").keyup(function(){
	var sponsoring_dte = this.value;
	var sponsoring_dteAuto=$("#sponsoring_dte");
	sponsoring_dteAuto.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
	        	type: 'POST',
	    	    url: "getNodalDteList?"+key+"="+value,
	        data: {sponsoring_dte:sponsoring_dte},
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
	        	  alert("Please Enter Nodal Directorate.");
	        	  sponsoring_dteAuto.val("");	        	  
	        	  sponsoring_dteAuto.focus();
	        	  return false;	             
	    	}   	         
	    }, 
	  	select: function( event, ui ) {
	      	var sponsoring_dte = ui.item.value;
		}
	});
}); 
</script>
 <script type="text/javascript">
	$(function() {    
	
				
	    $('#type_veh').change(function() {
	    	$("#mct_number").val("");
	    	$("#new_nomencatre1").val("");
	    	var type_of_vehicle = this.value;     
	    });
	
	    $("input#sponsoring_dte").keyup(function(){
			var sponsoring_dte = this.value;
			var sponsoring_dteAuto=$("#sponsoring_dte");
			sponsoring_dteAuto.autocomplete({
			      source: function( request, response ) {
			        $.ajax({
			        	type: 'POST',
			    	    url: "getNodalDteList?"+key+"="+value,
			        data: {sponsoring_dte:sponsoring_dte},
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
			        	  alert("Please Enter Nodal Directorate.");
			        	  sponsoring_dteAuto.val("");	        	  
			        	  sponsoring_dteAuto.focus();
			        	  return false;	             
			    	}   	         
			    }, 
			  	select: function( event, ui ) {
			      	var sponsoring_dte = ui.item.value;
				}
			});
		}); 
	});
		
	function getPRFList(val)
	{
		//getMCTMainList('');
		var options = '<option value="XNR">'+ "--- Select All Veh Type ---" + '</option>';
		if(val !="")
		{
		    $.post("getTptSummaryInPRFList?"+key+"="+value,{type : val}).done(function(j) {
		    	var prfcd='${prf_code}';
	    		prfcd = prfcd +",";
	    		var prfcd1=[];
		    	//prfcd=prfcd.split(",");
				for ( var i = 0; i < j.length; i++) {				
					if(prfcd.indexOf(j[i].prf_code+",")>=0){
						options += '<option value="'+j[i].prf_code+'" name="' + j[i].group_name+ '" selected=selected>'+ j[i].group_name+ '</option>';
						//getMCTMainList('${prf_code}')
						prfcd1.push(j[i].prf_code);							
					}else{
						options += '<option value="'+j[i].prf_code+'" name="' + j[i].group_name+ '" >'+ j[i].group_name+ '</option>';
					}
				}	
				
				console.log(prfcd1);
				$("select#prf_code").html(options);
				
		    }).fail(function(xhr, textStatus, errorThrown) {
			});
		}
		else {
			$("select#prf_code").html(options);
		}
		
	}
	$(document).ready(function() {
		
		
		$("#type_veh").val('${type_veh}');
		getPRFList('${type_veh}');	
		
		
	});
			
	
	
	
	
</script> 