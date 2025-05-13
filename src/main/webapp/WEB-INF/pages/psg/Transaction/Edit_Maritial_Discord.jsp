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
<form:form name="Edit_Maritial_Discord" id="Edit_Maritial_Discord" action="Edit_Maritial_Discord_action?${_csrf.parameterName}=${_csrf.token}" method="post" class="form-horizontal" commandName="Edit_Maritial_Discord_CMD"> 
	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<h5>UPDATE MARITAL DISCORD</h5>
					<h6 class="enter_by">
						<span style="font-size: 12px; color: red;">(To be entered
							by Unit)</span>
					</h6>
				</div>
				<div class="card-body card-block">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong> Category</label>
								</div>
								<div class="col-md-8">
									<select name="service_category" id="service_category"
										onchange="fn_service_category_change();"
										class="form-control" readonly>
										<option value="${getServiceCategoryList.get(0)[0]}" name="${getServiceCategoryList.get(0)[1]}">${getServiceCategoryList.get(0)[1]}</option>
									</select>
								</div>
							</div>
						</div>

						<div class="col-md-6" id="per_no_id" >
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong> Personal No </label>
								</div>
								<div class="col-md-8">
									<input type="text" id="personnel_no" name="personnel_no" class="form-control autocomplete" autocomplete="off" onkeyup="return specialcharecter(this)" onkeypress="return AvoidSpace(event)"
										onchange="personal_number()" maxlength="9"> 
									<input type="hidden" id="comm_id" name="comm_id" class="form-control" autocomplete="off"> 
									<input type="hidden" id="census_id" name="census_id" value="0" class="form-control" autocomplete="off">
								</div>
							</div>
						</div>
						<div class="col-md-6" id="army_no_id" style="display: none;">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong> Army No </label>
								</div>
								<div class="col-md-8">
									<input type="text" id="army_no" name="army_no"
										class="form-control autocomplete" autocomplete="off"
										onchange="personal_number_army()">
										<input type="hidden" id="jco_id" name="jco_id" value="0" class="form-control autocomplete"  autocomplete="off"  >
								</div>
							</div>
						</div>

					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong> Rank </label>
								</div>
								<div class="col-md-8">
									<label class=" form-control-label" id="p_rank"></label> 
									<input type="hidden" id="hd_p_rank" name="hd_p_rank" class="form-control autocomplete" autocomplete="off">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong> Name </label>
								</div>
								<div class="col-md-8">
									<label class=" form-control-label" id="cadet_name"></label>
									<input type="hidden" id="cname" name="cname" class="form-control autocomplete" autocomplete="off">
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12">

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong> Unit Name </label>
								</div>
								<div class="col-md-8">
									<label class=" form-control-label" id="app_unit_name"></label>
									<input type="hidden" id="un" name="un" class="form-control autocomplete" autocomplete="off">
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong> SUS No </label>
								</div>
								<div class="col-md-8">
									<label class=" form-control-label" id="app_sus_no"></label>
								</div>
							</div>
						</div>

					</div>

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong> Name of Complainant </label>
								</div>
								<div class="col-md-8">

									<!-- 		<select name="" id="" class="form-control-sm form-control"  >
											<option value="0">--Select--</option>
													<option value="0">Pending</option>
												    <option value="1">Approved</option>	
													 <option value="2">Rejected</option>
											</select> -->
									<input type="text" id="name_lady" name="name_lady"
										class="form-control autocomplete" autocomplete="off" onkeypress="return onlyAlphabets(event);" oninput="this.value = this.value.toUpperCase()" maxlength="50" readonly="readonly">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong> Date of Complaint/Allegations
									</label>
								</div>
								<div class="col-md-8">

									<input type="text" name="dt_of_comp" id="dt_of_comp"
										maxlength="10" value="DD/MM/YYYY"
										onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control"
										style="width: 85%; display: inline;"
										onfocus="this.style.color='#000000'"
										onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);"
										onkeyup="clickclear(this, 'DD/MM/YYYY')"
										onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true"
										autocomplete="off" style="color: rgb(0, 0, 0);" readonly="readonly" />
								</div>
							</div>
						</div>

					</div>
					
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong> Date of Status </label>
								</div>
								<div class="col-md-8">
								
									<input type="text" name="dt_of_status" id="dt_of_status"
								maxlength="10" value="DD/MM/YYYY"
								onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control"
								style="width: 85%; display: inline;"
								onfocus="this.style.color='#000000'"
								onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);"
								onkeyup="clickclear(this, 'DD/MM/YYYY')"
								onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true"
								autocomplete="off" style="color: rgb(0, 0, 0);"  />
								</div>
							</div>
						</div>
					

					</div>
					
					
					
					<div class="col-md-12">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong> Complaint/Allegations
									</label>
								</div>
								<div class="col-md-8">
								
								<textarea type="text" id="complaint" name="complaint" class="form-control autocomplete" autocomplete="off" rows="4" cols="50" maxlength="255" onkeypress="return /[0-9a-zA-Z\s]/i.test(event.key)" readonly="readonly"></textarea>
								</div>
							</div>
					
					</div>
					
					<div class="col-md-12">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong> Status of the Case
									</label>
								</div>
								<div class="col-md-8">
								
								<textarea type="text" id="status_of_case" name="status_of_case" class="form-control autocomplete" autocomplete="off" rows="4" cols="50" maxlength="255" onkeypress="return /[0-9a-zA-Z\s]/i.test(event.key)" ></textarea>
								</div>
							</div>

					</div>
					

				</div>
				<input type="hidden" id="id" name="id" class="form-control autocomplete" autocomplete="off" >
				<input type="hidden" id="Child_Hid" name="Child_Hid" value = "0" class="form-control autocomplete" autocomplete="off" >
				<div class="card-footer" align="center" class="form-control">
			      	<a href="Search_Maritial" class="btn btn-danger btn-sm" >Cancel</a> 
					<input type="submit" class="btn btn-primary btn-sm" value="Update" onclick=" return validate();">
					<input type="button" name="save" class="btn btn-primary btn-sm" id ="popup" style=" display: none;" value="View History " onclick="Pop_Up_History('Marital_Discord');" />
				</div>
			</div>
		</div>
	</div>

</form:form>
<c:url value="Popup_Marital_DiscordUrl" var="Popup_Marital_DiscordUrl" />
<form:form action="${Popup_Marital_DiscordUrl}" method="post" id="Marital_Discord_Form" name="Marital_Discord_Form" modelAttribute="id" target="result">
	<input type="hidden" name="m_comm_id" id="m_comm_id" value="0" />
	<input type="hidden" name="m_census_id" id="m_census_id" value="0" />
</form:form>





<script>	

$(document).ready(function() {
	
	
	
	$("#id").val('${Edit_Maritial_Discord_CMD.id}');
 	$("#service_category").val('${Edit_Maritial_Discord_CMD.service_category}');
 	fn_service_category_change();
 	$("#personnel_no").val('${Edit_Maritial_Discord_CMD.personnel_no}');
 	personal_number();
 	$("#name_lady").val('${Edit_Maritial_Discord_CMD.name_lady}');
 	$("#complaint").val('${Edit_Maritial_Discord_CMD.complaint}');  
 	$("#dt_of_comp").val(ParseDateColumncommission('${Edit_Maritial_Discord_CMD.dt_of_comp}'));
 	
 	
 	$("#status_of_case").val('${Status_case}');
 	$("#dt_of_status").val(ParseDateColumncommission('${dt_of_status}'));
 	$("#Child_Hid").val('${Child_Hid}');
});

function fn_service_category_change() {
	var text6 = $("#service_category option:selected").text();
	if(text6 == "JCO/OR"){
		$("#army_no_id").show();
		$("#per_no_id").hide();
		
	}
	else{
		$("#army_no_id").hide();
		$("#per_no_id").show();
	}
}
function personal_number() {

	if ($("input#personnel_no").val() == "") {
		alert("Please select Personal No");
		$("input#personnel_no").focus();
		return false;
	} 
	
	 var personnel_no = $("input#personnel_no").val();
		//alert("hello---" + personnel_no);
		 $.post('GetPersonnelNoData?' + key + "=" + value,{ personnel_no : personnel_no },function(j) {
			 if(j!=""){
							$("#comm_id").val(j[0][0]);
							
							$.ajaxSetup({
								async : false
							});
							var comm_id = j[0][0];
							$.post('GetCensusDataApprove?' + key+ "=" + value,{comm_id : comm_id},function(k) {
								if (k.length > 0) {
									$('#census_id').val(k[0][1]);
								
									curr_marital_status = k[0][13];
									$("#cadet_name").text(k[0][2]);
									$("#p_rank").text(k[0][3]);
									$("#hd_p_rank").val(k[0][3]);
									$("#app_sus_no").text(k[0][7]);
									
									$.ajaxSetup({
										async : false
									});
									var sus_no = k[0][7];
									getunit(sus_no);
									function getunit(val) {
							                $.post("getTargetUnitNameList?"+ key+ "="+ value,{sus_no : sus_no},function(j) {}).done(function(j) {
												var length = j.length - 1;
												var enc = j[length].substring(0,16);
												//alert("unit---" + dec(enc,j[0]));
												$("#app_unit_name").text(dec(enc,j[0]));
											}).fail(function(xhr,textStatus,errorThrown) {
									        });
									}
							}
						});

						}
					});
		 $("input#personnel_no").attr('readonly', true);
			$("#popup").show();
}

$("input#personnel_no").keyup(function(){
	var personel_no = this.value;
		 var susNoAuto=$("#personnel_no");
		  susNoAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        	type: 'POST',
			    	url: "getpersonnel_noListApproved?"+key+"="+value,
		        data: {personel_no:personel_no},
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
		        	  alert("Please Enter Approved Personal No");
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

function Pop_Up_History(a) {

	var x = screen.width/2 - 1100/2;
    var y = screen.height/2 - 900/2;
    popupWindow = window.open("", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');
	window.onfocus = function () { 
	}
	comm_id = $("#comm_id").val();
	census_id = $("#census_id").val();

	if(a == "Marital_Discord"){
		$('#m_comm_id').val(comm_id);
		$('#m_census_id').val(census_id);
		document.getElementById('Marital_Discord_Form').submit();
	}
}

function validate() {
	 
	 if ($("#service_category").val() == "0") {
			alert("Please Select Category");
			$("#service_category").focus();
			return false;
		}
		 if ($("#service_category").val() == "1") {
			 if ($("input#personnel_no").val() == "") {
				alert("Please Enter Personal No");
				$("input#personnel_no").focus();
				return false;
			 }
		 }
		 if ($("input#name_lady").val().trim() == "") {
				alert("Please Enter Name of Complainant.");
				$("input#name_lady").focus();
				return false;
		 }
		 if ($("input#dt_of_comp").val().trim() == "" || $("input#dt_of_comp").val().trim() == "DD/MM/YYYY") {
				alert("Please Enter Date of Complaint/Allegations.");
				$("input#dt_of_comp").focus();
				return false;
		 }
		if ($("textarea#complaint").val().trim() == "") {
			alert("Please Enter Complaint/Allegations.");
			$("textarea#complaint").focus();
			return false;
		}
		if ($("textarea#status_of_case").val().trim() == "") {
			alert("Please Enter Status of the Case.");
			$("textarea#status_of_case").focus();
			return false;
		}
		 return true;

}
jQuery(function($){ //on document.ready 
 	datepicketDate('dt_of_comp');
 	datepicketDate('dt_of_status');
}); 

</script>