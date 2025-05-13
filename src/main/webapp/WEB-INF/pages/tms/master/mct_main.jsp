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
<link href="js/cue/cueWatermark.css" rel="Stylesheet"></link>
<form:form name="mctmainActionForm" id="mctmainActionForm" action="mctMainAction" method="POST" commandName="tms_main_masterCmd"> 
	<div class="animated fadeIn">
	    	<div class="container" align="center">
	    		<div class="card">
	    		<div class="card-header"><span id="lbladd"></span><h5>ALLOTMENT OF MCT</h5> <h6 class="enter_by"><span style="font-size:12px;color:red;">(To be entered by MISO)</span></h6></div>	    			
	          			<div class="card-body card-block">
	          				<div class="col-md-12">
	            				<div class="col-md-6">
	            					<div class="row form-group">
       		                           <div class="col col-md-4">
                  						  <label class=" form-control-label"><strong style="color: red;">* </strong>PRF Nomenclature </label>
                						</div>
	                					<div class="col-12 col-md-8">
           						    		<select class="form-control"  id="mct_slot" onchange="onMctSlotChange()" name="mct_slot">
												<option value="0">--Select--</option>	
												<c:forEach var="item" items="${getGroupNamelist}" varStatus="num" >
													<option value="${item.code_no_from},${item.code_no_to}"  name="${item.prf_code},${item.vehicle_class_code},${item.type_of_veh}">${item.group_name}</option>
												</c:forEach>
           						    		</select>	
           						    		<input type="hidden" id="prf_code" name="prf_code" />
           						    		<input type="hidden" id="vehicle_class_code" name="vehicle_class_code" />
           						    		
       					           		</div>
	              					</div>
	            				</div>
	            				<div class="col-md-6">
	            					<div class="row form-group">
       		                           <div class="col col-md-4">
                  						  <label class=" form-control-label"><strong style="color: red;">* </strong>Type of Vehicle </label>
                						</div>
	                					<div class="col-12 col-md-8">
           						    		  <input  name="type_of_veh" id="type_of_veh" maxlength="1" class=" form-control" readonly="readonly">
       					           		</div>
	              					</div>
	            				</div>
	            			</div>
	            			<div class="col-md-12">
	            				<div class="col-md-6">
	            					<div class="row form-group">
             					        <div class="col col-md-4">
	                  						  <label class=" form-control-label"><strong style="color: red;">* </strong>MCT </label>
	                					</div> 
	                					<div class="col-12 col-md-8">
	                  						<input type="text" id="mct_main_id" name="mct_main_id" onchange="onDiscard()" maxlength="4" onkeypress="return isNumber0_9Only(event);" class="form-control" autocomplete="off" readonly="readonly">
	                					</div>
	              					</div>
	            				</div>
	            				<div class="col-md-6">
	            					<div class="row form-group">
             					        <div class="col col-md-4">
	                  						<label class=" form-control-label"><strong style="color: red;">* </strong>MCT Nomenclature</label>
	                					</div>
	                					<div class="col-12 col-md-8">
	                  						<input type="text" id="mct_nomen" name="mct_nomen" placeholder="" maxlength="100" class="form-control" oninput="this.value = this.value.toUpperCase()"  autocomplete="off">
	                					</div>
									</div>
	            				</div>
	            			</div>
	            			<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong style="color: red;">* </strong>Nodal Dte </label>
									</div>
									<div class="col-12 col-md-8">
										<!-- <input type="text" id="sus_no" name="sus_no"   maxlength="8" class="form-control" autocomplete="off"> -->
										<select id="sus_no" name="sus_no" class="form-control-sm form-control" >
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getLine_DteList}" varStatus="num">
										<option value="${item.line_dte_sus}" >${item.line_dte_name}</option>
										</c:forEach>
									</select>
									</div>
								</div>
							</div>
							
						</div>

	            			<div style="display: none;" class="form-control">
								<input type="text" id="prf_code" name="prf_code" placeholder="" class="form-control" >
								<input type="text" id="vehicle_class_code" name="vehicle_class_code" placeholder="" class="form-control" >
							</div>

<div class="col-md-12" id="getSearch_Letter" style="display: block;">	
	<div class="card-header"> 
		<h5> Discard Condition </h5>				
	</div>	
</div>

					<div id="Discard_Condition" class="tabcontent" style="display: block;">
						<div class="card" style="margin-top: 20px;">
							<div class="card_body">
								<table id="discard_table"
									class="table-bordered watermarked"
									style="margin-top: 10px; width: -webkit-fill-available;">
									<thead style="color: white; text-align: center;">
										<tr>
											<th>Sr No</th>
											<th>Force Type</th>
											<th>Vintage(In Yrs)</th>
											<th>KM</th>
											<th>Action</th>
										</tr>
									</thead>
									<tbody data-spy="scroll" id="discard_condi_tbody"
										style="min-height: 46px; max-height: 650px; text-align: center;">
										<tr id="tr_discard_condi1">
											<td class="dis_con_srno">1</td>
											<td><select id="force_type1" name="force_type1"
												class="form-control autocomplete" ">
													<option value="-1">--Select--</option>
													<c:forEach var="item" items="${getForceTypeList}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
											</select></td>
											<td><input type="text" id="vintage1" name="vintage1" class="form-control autocomplete" autocomplete="off" maxlength="2" onkeypress="return isNumber0_9Only(event)"></td>
											<td><input type="text" id="km1" name="km1" class="form-control autocomplete" autocomplete="off" maxlength="7" onkeypress="return isNumber0_9Only(event)"></td>
											<td style="display:none;"><input type="hidden" id="dis_con_ch_id1" name="dis_con_ch_id1" 
											 value="0" class="form-control autocomplete" autocomplete="off" ></td>

											<td><a class="btn btn-primary btn-sm" value="SAVE" title="SAVE" id="discard_condition_save1" onclick="discard_condition_save_fn(1);"><i
													class="fa fa-save"></i></a> <a style="display: none;"
												class="btn btn-success btn-sm" value="ADD" title="ADD" id="discard_condition_add1" onclick="discard_condition_add_fn(1);"><i
													class="fa fa-plus"></i></a> <a style="display: none;"
												class="btn btn-danger btn-sm" value="REMOVE" title="REMOVE" 	id="discard_condition_remove1" onclick="discard_condition_remove_fn(1);"><i
													class="fa fa-trash"></i></a></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>


						<div class="card-footer" id="bk_id" align="center" class="form-control" style="display: none;">
							<input type="button" class="btn btn-info btn-sm" onclick="Cancel();" value="Back">
						</div>
					</div>
	<div class="col-md-12" id="getSearch_Letter" style="display: block;">	
	<div class="card-header"> 
		<h5> OH/MR </h5>				
	</div>	
</div>				
		<div id="Discard_Condition_oh_mr" class="tabcontent" style="display: block;">
						<div class="card" style="margin-top: 20px;">
							<div class="card_body">
								<table id="discard_table_oh_mr"
									class="table-bordered watermarked"
									style="margin-top: 10px; width: -webkit-fill-available;">
									<thead style="color: white; text-align: center;">
										<tr>
											<th>Sr No</th>
											<th>OH/MR</th>
											<th>Vintage(In Yrs)</th>
											<th>KM</th>
											<th>Action</th>
										</tr>
									</thead>
									<tbody data-spy="scroll" id="discard_condi_tbody_oh_mr"
										style="min-height: 46px; max-height: 650px; text-align: center;">
										<tr id="tr_discard_condi_oh_mr1">
											<td class="dis_con_oh_mr_srno">1</td>
											<td><select id="oh_mr1" name="oh_mr1"
												class="form-control autocomplete" ">
													<option value="0">--Select--</option>
													<option value="1">MR1</option>
													<option value="2">OH1</option>
													<option value="3">MR2</option>
													<option value="4">OH2</option>
													<option value="5">MR3</option>
													<option value="6">OH3</option>
												</select>
											</td>
											<td><input type="text" id="vintage_oh_mr1" name="vintage_oh_mr1" class="form-control autocomplete" autocomplete="off" maxlength="2" onkeypress="return isNumber0_9Only(event)"></td>
											<td><input type="text" id="km_oh_mr1" name="km_oh_mr1" class="form-control autocomplete" autocomplete="off" maxlength="7" onkeypress="return isNumber0_9Only(event)"></td>
											<td style="display:none;"><input type="hidden" id="dis_con_oh_mr_ch_id1" name="dis_con_oh_mr_ch_id1" 
											 value="0" class="form-control autocomplete" autocomplete="off" ></td>

											<td><a class="btn btn-primary btn-sm" value="SAVE" title="SAVE" id="discard_condition_oh_mr_save1" onclick="discard_condition_save_oh_mr_fn(1);"><i
													class="fa fa-save"></i></a> <a style="display: none;"
												class="btn btn-success btn-sm" value="ADD" title="ADD" id="discard_condition_oh_mr_add1" onclick="discard_condition_oh_mr_add_fn(1);"><i
													class="fa fa-plus"></i></a> <a style="display: none;"
												class="btn btn-danger btn-sm" value="REMOVE" title="REMOVE" id="discard_condition_oh_mr_remove1" onclick="discard_condition_oh_mr_remove_fn(1);"><i
													class="fa fa-trash"></i></a></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>


						<div class="card-footer" id="bk_id" align="center" class="form-control" style="display: none;">
							<input type="button" class="btn btn-info btn-sm" onclick="Cancel();" value="Back">
						</div>
					</div>			
					
				</div>
						<div class="card-footer" align="center" class="form-control">
							<input type="submit" class="btn btn-primary btn-sm" value="Save" onclick="return validate();">
								<i class="action_icons searchButton"></i><input type="button"
						class="btn btn-info btn-sm" onclick="Search();" value="Search">
	              			<input type="reset" class="btn btn-success btn-sm" value="Clear">   
	              		</div> 
	              		
	              		
	              		
	              				<div class="card-body">
					<div class="" id="reportDiv">
						<div class="col-md-12">
				
							<div class="watermarked" data-watermark="" id="divwatermark">
								<span id="ip"></span>
								<table id="getVehicleStatusTbl"
									class="table no-margin table-striped  table-hover  table-bordered report_print"
								
									style="margin-bottom: 0rem;">
									<thead>
										<tr>
											<th >Ser No</th>
											<th>Mct Nomen</th>
											<th>PRF Code</th>
											<th>Type Of Vehicle</th>
											<th>Sus No.</th>
											<th>Action</th>
											
										</tr>
										
									</thead>
									<tbody>
										<c:if test="${list.size() == 0}">
											<tr>
												<td colspan="15" align="center" style="color: red;">
													Data Not Available</td>
											</tr>
										</c:if>
										<c:forEach var="item" items="${list}" varStatus="num">
											<tr>
												<td ><b>${num.index+1}</b></td>
												<td><b>${item[0]}</b></td>
												<td><b>${item[1]}</b></td>
												<td ><b>${item[2]}</b></td>
												<td ><b>${item[3]}</b></td>
												<td ><b>${item[5]}</b></td>
											
		
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
</form:form>

<c:url value="getSearch_mctdtl" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="mct_slot1">
	<input type="hidden" name="mct_slot1" id="mct_slot1" />
	<input type="hidden" name="prf_code1" id="prf_code1" />
	<input type="hidden" name="type_of_veh1" id="type_of_veh1" />
	<input type="hidden" name="sus_no1" id="sus_no1" />
</form:form>
<script type="text/javascript">

function Search() {

// 	if($("#mct_slot").val() == "0"){
// 		alert("Please Select PRF Nomenclature.");
// 		$("input#mct_slot").focus();
// 		return false;
// 	}
// 	if($("#mct_nomen").val() == ""){
// 		alert("Please Enter MCT Nomenclature.");
// 		$("#mct_nomen").focus();
// 		return false;
// 	}
// 	if($("#mct_main_id").val() == ""){
// 		alert("Please Enter MCT 3301,3400Main.");
// 		$("#mct_main_id").focus();
// 		return false;
// 	}
// 	if($("#mct_main_id").val().length != 4){
// 		alert("Please Enter 4 Digit MCT Main.");
// 		$("#mct_main_id").focus();
// 		return false;
// 	}
	 if ($("select#sus_no").val() == "0") {
			alert("Please Enter  Sus No");
			$("input#sus_no").focus();
			return false;
		}
	$("#mct_slot1").val($('#mct_slot').val());
	$("#prf_code1").val($('#prf_code').val());
	$("#type_of_veh1").val($('#type_of_veh').val());
	$("#sus_no1").val($('#sus_no').val());
	document.getElementById('searchForm').submit();
	
}

function onDiscard(){
	get_discard_condition_details();
}

function deleteData(id){
	$("#id1").val(id);
	document.getElementById('deleteForm').submit();
	}
function editData(id,mct_main_id,sus_no1,prf_code1,type_of_veh1,prf,mct_nomen){	
	document.getElementById('lbladd').innerHTML = "Update ";
     $("select#mct_slot").val(prf);
	$("input#type_of_veh").val(type_of_veh1);
	$("input#mct_main_id").val(mct_main_id);
	$("input#mct_nomen").val(mct_nomen);
	
	$("select#sus_no").val(sus_no1);
	get_discard_condition_details();
	get_discard_condition_details_oh_mr();
	//document.getElementById('id_org').value=id;
}

$(document).ready(function() {
	
	var q1 = '${mct_slot1}';
	if (q1 != "") {
		$("#mct_slot").val(q1);
	}
	var q2 = '${sus_no1}';
	if (q2 != "") {
		$("#sus_no").val(q2);
	}
	var q3 = '${type_of_veh1}';
	if (q3 != "") {
		$("#type_of_veh").val(q3);
	}
	var q4 = '${mct_nomen1}';
	if (q4 != "") {
		$("#mct_nomen").val(q4);
	}
	
	if('${list.size()}' == 0 ){
		$("#reportDiv").hide();
		
	 } 
 else{
	 $("#reportDiv").show();
	
 }
	
	
	
		
 	$('select#mct_slot').change(function() {
		var mctSlotId = this.value;
	   	if(mctSlotId != 0){
	   		$.post("getmaxMCTMainID?"+key+"="+value, {mctSlotId:mctSlotId }).done(function(j) {
	   			var max1 = "";
	   			if(j.length == 0){
	   				alert("This slot is full.");
	   			}else{
	   				max1 = ('0000' + j).substr(-4);
	   			}
	   			$("#mct_main_id").val(max1);
			}).fail(function(xhr, textStatus, errorThrown) { });   		
	   	}
	   	
	   	var name = $(this).find('option:selected').attr("name");
	   	var res = name.split(",");
	   	$("#prf_code").val(res[0]);
	   	$("#vehicle_class_code").val(res[1]);
	   	$("#type_of_veh").val(res[2]);
	   	
	});	 
});

 function isNumber0_9Only(evt){
		var charCode = (evt.which) ? evt.which : evt.keyCode;
		if( ! ( charCode >= 48 && charCode <= 57 ) && charCode != 8 ){
			 return false;
		}
		 
	    return true;
	}
 function validate() {
	
	if($("#mct_slot").val() == "0"){
		alert("Please Select PRF Nomenclature.");
		$("#mct_slot").focus();
		return false;
	}
	if($("#mct_nomen").val() == ""){
		alert("Please Enter MCT Nomenclature.");
		$("#mct_nomen").focus();
		return false;
	}
	if($("#mct_main_id").val() == ""){
		alert("Please Enter MCT Main.");
		$("#mct_main_id").focus();
		return false;
	}
	if($("#mct_main_id").val().length != 4){
		alert("Please Enter 4 Digit MCT Main.");
		$("#mct_main_id").focus();
		return false;
	}
	 if ($("select#sus_no").val() == "0") {
			alert("Please Enter  Sus No");
			$("#sus_no").focus();
			return false;
		} 
	
	var main_id = $("#mct_main_id").val();
	var slot = $("#mct_slot").val();
	var array = slot.split(",");
	var from = array[0];
	var to = array[1];
	if(from > main_id || to < main_id){
		alert("Please Enter MCT Main Id within " + from +" - " + to);
		$("#mct_main_id").focus();
		return false;
	}
	
	return true;
} 
 
 dis_con=1;
 dis_con_srno=1;
 function discard_condition_add_fn(q){
 	 dis_con=q+1;
 	
 	 if( $('#discard_condition_add'+q).length )         
 	 {
 			$("#discard_condition_add"+q).hide();
 	 }
 	
 	 
 	 if(q!=0)
 		 dis_con_srno+=1;
 	
 	$("table#discard_table").append('<tr id="tr_discard_condi'+dis_con+'">'
 			+'<td class="dis_con_srno">'+dis_con+'</td>'
 			+'<td>'
 			+'<select  id="force_type'+dis_con+'" name="force_type'+dis_con+'"  class="form-control autocomplete"  >'
 			+' <option value="-1">--Select--</option>'
 		   +'<c:forEach var="item" items="${getForceTypeList}" varStatus="num">'
 			+'<option value="${item[0]}" name="${item[1]}">${item[1]}</option>'
 			+'</c:forEach>'
 			+'</select>'
 			+'</td>'	
 			+'<td>'
 			+'<input type="text" id="vintage'+dis_con+'" name="vintage'+dis_con+'" class="form-control autocomplete " autocomplete="off" maxlength="2" onkeypress="return isNumber0_9Only(event)"></td>'
 			+'<td>'
 			+'<input type="text" id="km'+dis_con+'" name="km'+dis_con+'" class="form-control autocomplete " autocomplete="off" maxlength="7" onkeypress="return isNumber0_9Only(event)"></td>'
 			+'<td style="display:none;"><input type="hidden" id="dis_con_ch_id'+dis_con+'" name="dis_con_ch_id'+dis_con+'"  value="0" class="form-control autocomplete" autocomplete="off" ></td>'
 			
 			+'<td><a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "discard_condition_save'+dis_con+'" onclick="discard_condition_save_fn('+dis_con+');" ><i class="fa fa-save"></i></a>'
 			+'<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "discard_condition_add'+dis_con+'" onclick="discard_condition_add_fn('+dis_con+');" ><i class="fa fa-plus"></i></a>'
 			+'<a style="display:none;" class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "discard_condition_remove'+dis_con+'" onclick="discard_condition_remove_fn('+dis_con+');"><i class="fa fa-trash"></i></a>' 
 			+'</td></tr>');
 	
 
 }
 
 
 function discard_condition_save_fn(qs){


			var force_type = $('#force_type' + qs).val();
			var vintage = $('#vintage' + qs).val();
			var km = $('#km' + qs).val();
			var dis_con_ch_id = $('#dis_con_ch_id' + qs).val();
			var mct_main_id = $("#mct_main_id").val();
			
	 var r =('${roleAccess}');
	 if (force_type == "-1") {debugger;
			alert("Please Select Force Type");
			$("#force_type" + qs).focus();
			return false;
		}
		if (vintage == "") {
			alert("Please Enter Vintage");
			$("#vintage" + qs).focus();
			return false;
		}
		if (km == "") {
			alert("Please Enter Km");
			$("#km" + qs).focus();
			return false;
		}
		
		if ($("#mct_nomen").val() == "") {
				alert("Please Enter MCT Nomenclature");
				$("#mct_nomen").focus();
				return false;
			}
		if ($("#sus_no").val() == "0") {
			alert("Please Select SUS No ");
			$("#sus_no").focus();
			return false;
		}
		if ($("#mct_slot").val() == "0") {
			alert("Please Select PRF Nomenclature ");
			$("#mct_slot").focus();
			return false;
		}
		
	


		
		
		
		$.post('discard_conditionAction?' + key + "=" + value, {
			force_type : force_type,
			vintage : vintage,
			km : km,
			dis_con_ch_id : dis_con_ch_id,
			mct_main_id :mct_main_id

		}, function(data) {

			var ids = data.split(",");
			if (data == "update") {
				alert("Data Updated SuccessFully");
// 				getFieldService();
			}

			else if (parseInt(ids[0]) > 0) {

				$("#dis_con_ch_id" + qs).val(ids[0]);
				// 			        	 $('#p_id').val(ids[1]);
				$("#discard_condition_add" + qs).show();
				$("#discard_condition_remove" + qs).show();
				alert("Data Saved SuccessFully");
				// 			        	  getFieldService();
			} else
				alert(data)
		}).fail(function(xhr, textStatus, errorThrown) {
			alert("fail to fetch")
		});
	}
 
 
 function discard_condition_remove_fn(R){
		var rc = confirm("Are You Sure! You Want To Delete");
		 if(rc){
		 var dis_con_ch_id=$('#dis_con_ch_id'+R).val();
		  $.post('discard_condition_delete_action?' + key + "=" + value, {dis_con_ch_id:dis_con_ch_id }, function(data){ 
				   if(data=='1'){
						 $("tr#tr_discard_condi"+R).remove(); 
								 if(R==dis_con){
									 R = R-1;
									 var temp=true;
									 for(i=R;i>=1;i--){
									 if( $('#discard_condition_add'+i).length )         
									 {
										 $("#discard_condition_add"+i).show();
										 temp=false;
										 lang=i;
										 break;
									 }}
							 
								 if(temp){
									 discard_condition_add_fn(0);
									}
				  			 }
								 $('.dis_con_srno').each(function(i, obj) {
									 
										obj.innerHTML=i+1;
										dis_con_srno=i+1;
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
 
 
 function get_discard_condition_details(){
	 var mct_main_id=$('#mct_main_id').val(); 
// 	 var comm_id=$('#comm_id').val(); 
	 var i=0;
	  $.post('discard_condition_getData?' + key + "=" + value, {mct_main_id:mct_main_id}, function(j){
			var v=j.length;
			if(v!=0){
		xpro=0;
		for(i;i<v;i++){
			
			xpro=xpro+1;
				if(xpro==1){
					$('#discard_condi_tbody').empty(); 
				}
// 				var dauth=ParseDateColumncommission(j[i].date_of_authority);
// alert(j[i].force_type);
				$("table#discard_table").append('<tr id="tr_discard_condi'+xpro+'">'
						+'<td class="dis_con_srno">'+xpro+'</td>'
			 			+'<td>'
			 			+'<select  id="force_type'+xpro+'" name="force_type'+xpro+'" value="'+j[i].force_type+'" class="form-control autocomplete"  >'
			 			+' <option value="-1">--Select--</option>'
			 		   +'<c:forEach var="item" items="${getForceTypeList}" varStatus="num">'
			 			+'<option value="${item[0]}" name="${item[1]}">${item[1]}</option>'
			 			+'</c:forEach>'
			 			+'</select>'
			 			+'</td>'	
			 			+'<td>'
			 			+'<input type="text" id="vintage'+xpro+'" name="vintage'+xpro+'" value="'+j[i].vintage+'" class="form-control autocomplete " autocomplete="off" maxlength="2" onkeypress="return isNumber0_9Only(event)"></td>'
			 			+'<td>'
			 			+'<input type="text" id="km'+xpro+'" name="km'+xpro+'" value="'+j[i].km+'" class="form-control autocomplete " autocomplete="off" maxlength="7" onkeypress="return isNumber0_9Only(event)"></td>'
			 			+'<td style="display:none;"><input type="hidden" id="dis_con_ch_id'+xpro+'" name="dis_con_ch_id'+xpro+'" value="'+j[i].id+'" class="form-control autocomplete" autocomplete="off" ></td>'
			 			
			 			+'<td><a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "discard_condition_save'+xpro+'" onclick="discard_condition_save_fn('+xpro+');" ><i class="fa fa-save"></i></a>'
			 			+'<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "discard_condition_add'+xpro+'" onclick="discard_condition_add_fn('+xpro+');" ><i class="fa fa-plus"></i></a>'
			 			+'<a class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "discard_condition_remove'+xpro+'" onclick="discard_condition_remove_fn('+xpro+');"><i class="fa fa-trash"></i></a>' 
			 			+'</td></tr>');
				 $('#force_type'+xpro).val(j[i].force_type );
				
// 				 onPro_exam(xpro);
			
		}
		if(xpro!=0){
			proex=xpro;
			proex_srno=xpro;
			$("#discard_condition_add"+xpro).show();
			
		}
	}
	  });
}
// -----------------------------oh_mr start------------------


dis_con_oh_mr=1;
dis_con_oh_mr_srno=1;
 function discard_condition_oh_mr_add_fn(q){
 dis_con_oh_mr=q+1;
 	
 	 if( $('#discard_condition_oh_mr_add'+q).length )         
 	 {
 			$("#discard_condition_oh_mr_add"+q).hide();
 	 }
 	
 	 
 	 if(q!=0)
 		dis_con_oh_mr_srno+=1;
 	
 	$("table#discard_table_oh_mr").append('<tr id="tr_discard_condi_oh_mr'+dis_con_oh_mr+'">'
 			+'<td class="dis_con_oh_mr_srno">'+dis_con_oh_mr+'</td>'
 			+'<td>'
 			+'<select id="oh_mr'+dis_con_oh_mr+'" name="oh_mr'+dis_con_oh_mr+'"class="form-control autocomplete"> <option value="0">--Select--</option>'
			+		'<option value="1">MR1</option>'
			+		'<option value="2">OH1</option>'
			+		'<option value="3">MR2</option>'
			+		'<option value="4">OH2</option>'
			+		'<option value="5">MR3</option>'
			+		'<option value="6">OH3</option>'
			+	'</select>'
			+'</td>'
			+'<td>'
 			+'<input type="text" id="vintage_oh_mr'+dis_con_oh_mr+'" name="vintage_oh_mr'+dis_con_oh_mr+'" class="form-control autocomplete " autocomplete="off" maxlength="2" onkeypress="return isNumber0_9Only(event)"></td>'
 			+'<td>'
 			+'<input type="text" id="km_oh_mr'+dis_con_oh_mr+'" name="km_oh_mr'+dis_con_oh_mr+'" class="form-control autocomplete " autocomplete="off" maxlength="7" onkeypress="return isNumber0_9Only(event)"></td>'
 			+'<td style="display:none;"><input type="hidden" id="dis_con_oh_mr_ch_id'+dis_con_oh_mr+'" name="dis_con_oh_mr_ch_id'+dis_con_oh_mr+'"  value="0" class="form-control autocomplete" autocomplete="off" ></td>'
 			
 			+'<td><a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "discard_condition_oh_mr_save'+dis_con_oh_mr+'" onclick="discard_condition_save_oh_mr_fn('+dis_con_oh_mr+');" ><i class="fa fa-save"></i></a>'
 			+'<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "discard_condition_oh_mr_add'+dis_con_oh_mr+'" onclick="discard_condition_oh_mr_add_fn('+dis_con_oh_mr+');" ><i class="fa fa-plus"></i></a>'
 			+'<a style="display:none;" class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "discard_condition_oh_mr_remove'+dis_con_oh_mr+'" onclick="discard_condition_oh_mr_remove_fn('+dis_con_oh_mr+');"><i class="fa fa-trash"></i></a>' 
 			+'</td></tr>');
 	
 
 }
 
 
 function discard_condition_save_oh_mr_fn(qs){

var oh_mr = $('#oh_mr' + qs).val();
var vintage_oh_mr = $('#vintage_oh_mr' + qs).val();
var km_oh_mr = $('#km_oh_mr' + qs).val();
var dis_con_oh_mr_ch_id = $('#dis_con_oh_mr_ch_id' + qs).val();
var mct_main_id = $("#mct_main_id").val();

		
	 var r =('${roleAccess}');
	
		if (oh_mr == "0") {
			alert("Please Select OH/MR");
			$("#oh_mr" + qs).focus();
			return false;
		}
		if (vintage_oh_mr == "") {
			alert("Please Enter Vintage");
			$("#vintage_oh_mr" + qs).focus();
			return false;
		}
		if (km_oh_mr == "") {
			alert("Please Enter Km");
			$("#km_oh_mr" + qs).focus();
			return false;
		}

		if ($("#mct_slot").val() == "0") {
			alert("Please Select PRF Nomenclature ");
			$("#mct_slot").focus();
			return false;
		}
		
		if ($("#mct_nomen").val() == "") {
			alert("Please Enter MCT Nomenclature");
			$("#mct_nomen").focus();
			return false;
		}
	if ($("#sus_no").val() == "0") {
		alert("Please Select SUS No ");
		$("#sus_no").focus();
		return false;
	}
	
		$.post('discard_condition_oh_mrAction?' + key + "=" + value, {
			
			oh_mr : oh_mr,
			vintage_oh_mr : vintage_oh_mr,
			km_oh_mr : km_oh_mr,
			dis_con_oh_mr_ch_id : dis_con_oh_mr_ch_id,
			mct_main_id : mct_main_id

		}, function(data) {

			var ids = data.split(",");
			if (data == "update") {
				alert("Data Updated SuccessFully");
				// 				getFieldService();
			}

			else if (parseInt(ids[0]) > 0) {

				$("#dis_con_oh_mr_ch_id" + qs).val(ids[0]);
				// 			        	 $('#p_id').val(ids[1]);
				$("#discard_condition_oh_mr_add" + qs).show();
				$("#discard_condition_oh_mr_remove" + qs).show();
				alert("Data Saved SuccessFully");
				// 			        	  getFieldService();
			} else
				alert(data)
		}).fail(function(xhr, textStatus, errorThrown) {
			alert("fail to fetch")
		});
	}

	function discard_condition_oh_mr_remove_fn(R) {
		
		var rc = confirm("Are You Sure! You Want To Delete");
		if (rc) {
			var dis_con_oh_mr_ch_id = $('#dis_con_oh_mr_ch_id' + R).val();
			$
					.post(
							'discard_condition_delete_oh_mr_action?' + key
									+ "=" + value,
							{
								dis_con_oh_mr_ch_id : dis_con_oh_mr_ch_id
							},
							function(data) {
								if (data == '1') {
									$("tr#tr_discard_condi_oh_mr" + R).remove();
									if (R == dis_con_oh_mr) {
										R = R - 1;
										var temp = true;
										for (i = R; i >= 1; i--) {
											if ($('#discard_condition_oh_mr_add'
													+ i).length) {
												$(
														"#discard_condition_oh_mr_add"
																+ i).show();
												temp = false;
												lang = i;
												break;
											}
										}

										if (temp) {
											discard_condition_oh_mr_add_fn(0);
										}
									}
									$('.dis_con_oh_mr_srno').each(
											function(i, obj) {

												obj.innerHTML = i + 1;
												dis_con_srno = i + 1;
											});
									alert("Data Deleted Successfully");
								} else {
									alert("Data not Deleted ");
								}
							}).fail(function(xhr, textStatus, errorThrown) {
						alert("fail to fetch")
					});
		}
	}
	
	
	
	function get_discard_condition_details_oh_mr(){
	 var mct_main_id=$('#mct_main_id').val(); 
//	 var comm_id=$('#comm_id').val(); 
	 var i=0;
	  $.post('discard_condition_oh_mr_getData?' + key + "=" + value, {mct_main_id:mct_main_id}, function(j){
			var v=j.length;
			if(v!=0){
		xoh_mr=0;
		for(i;i<v;i++){
			
			xoh_mr =xoh_mr+1;
				if(xoh_mr==1){
					$('#discard_condi_tbody_oh_mr').empty(); 
				}

			$("table#discard_table_oh_mr").append('<tr id="tr_discard_condi_oh_mr'+xoh_mr+'">'
						+'<td class="dis_con_oh_mr_srno">'+xoh_mr+'</td>'
			 			+'<td>'
 			+'<select id="oh_mr'+xoh_mr+'" name="oh_mr'+xoh_mr+'" value="'+j[i].oh_mr+'" class="form-control autocomplete"> '
			 +		'<option value="0">--Select--</option>'
			+		'<option value="1">MR1</option>'
			+		'<option value="2">OH1</option>'
			+		'<option value="3">MR2</option>'
			+		'<option value="4">OH2</option>'
			+		'<option value="5">MR3</option>'
			+		'<option value="6">OH3</option>'
			+	'</select>'
			+'</td>'
			+'<td>'
 			+'<input type="text" id="vintage_oh_mr'+xoh_mr+'" name="vintage_oh_mr'+xoh_mr+'"  value="'+j[i].vintage+'" class="form-control autocomplete " autocomplete="off" maxlength="2" onkeypress="return isNumber0_9Only(event)"></td>'
 			+'<td>'
 			+'<input type="text" id="km_oh_mr'+xoh_mr+'" name="km_oh_mr'+xoh_mr+'"  value="'+j[i].km+'" class="form-control autocomplete " autocomplete="off" maxlength="7" onkeypress="return isNumber0_9Only(event)"></td>'
 			+'<td style="display:none;"><input type="hidden" id="dis_con_oh_mr_ch_id'+xoh_mr+'"   value="'+j[i].id+'"  name="dis_con_oh_mr_ch_id'+xoh_mr+'"  value="0" class="form-control autocomplete" autocomplete="off" ></td>'
 			
 			+'<td><a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "discard_condition_oh_mr_save'+xoh_mr+'" onclick="discard_condition_save_oh_mr_fn('+xoh_mr+');" ><i class="fa fa-save"></i></a>'
 			+'<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "discard_condition_oh_mr_add'+xoh_mr+'" onclick="discard_condition_oh_mr_add_fn('+xoh_mr+');" ><i class="fa fa-plus"></i></a>'
 			+'<a class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "discard_condition_oh_mr_remove'+xoh_mr+'" onclick="discard_condition_oh_mr_remove_fn('+xoh_mr+');"><i class="fa fa-trash"></i></a>' 
 			+'</td></tr>');

			 $('#oh_mr'+xoh_mr).val(j[i].oh_mr );
				
//				 onPro_exam(xoh_mr);
			
		}
		if(xoh_mr!=0){
			xoh_mr=xoh_mr;
			xoh_mr_srno=xoh_mr;
			$("#discard_condition_oh_mr_add"+xoh_mr).show();
		}
	}
	  });
}
</script>

