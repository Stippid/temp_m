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
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

<style>
textarea {
    text-transform: none !important;
}
</style>
<form:form name="Maritial_Discord_JCO" id="Maritial_Discord_JCO"
	action="Maritial_Discord_JCO_action?${_csrf.parameterName}=${_csrf.token}" method="post" class="form-horizontal"
	commandName="Maritial_Discord_JCO_CMD">
	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<h5>MARITAL DISCORD JCO/OR</h5>
					<h6 class="enter_by">
						<span style="font-size: 12px; color: red;">(To be entered by Unit)</span>
					</h6>
				</div>
				<div class="card-body card-block">
					<div class="col-md-12 form-group">
						
							
								<div class="col-2 col-md-2">	
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>Army No</label>
								</div>
								<div class="col-md-4">
									<input type="text" id="personnel_no" name="personnel_no" class="form-control autocomplete" autocomplete="off" maxlength="12"  onchange="personal_number()" onkeyup="return specialcharecter(this)" onkeypress="return AvoidSpace(event)">
									<input type="hidden" id="jco_id" name="jco_id" class="form-control autocomplete" autocomplete="off"> 
								</div>
						
					</div>
					
					<div class="col-md-12 form-group">
						
							
								<div class="col-2 col-md-2">	
									<label class=" form-control-label"><strong
										style="color: red;">* </strong> Rank </label>
								</div>
								<div class="col-md-4">
									<label class=" form-control-label" id="p_rank"></label> 
									<input type="hidden" id="hd_p_rank" name="hd_p_rank" class="form-control autocomplete" autocomplete="off">
								</div>
							
								<div class="col-2 col-md-2">	
									<label class=" form-control-label"><strong
										style="color: red;">* </strong> Name </label>
								</div>
								<div class="col-md-4">
									<label class=" form-control-label" id="cadet_name"></label>
									<input type="hidden" id="cname" name="cname" class="form-control autocomplete" autocomplete="off">
								</div>
							
						
					</div>

					<div class="col-md-12 form-group">

						
							
								<div class="col-2 col-md-2">	
									<label class=" form-control-label"><strong
										style="color: red;">* </strong> Unit Name </label>
								</div>
								<div class="col-md-4">
									<label class=" form-control-label" id="app_unit_name"></label>
									<input type="hidden" id="un" name="un" class="form-control autocomplete" autocomplete="off">
								</div>
							
						

						
							
								<div class="col-2 col-md-2">	
									<label class=" form-control-label"><strong
										style="color: red;">* </strong> SUS No </label>
								</div>
								<div class="col-md-4">
									<label class=" form-control-label" id="app_sus_no"></label>
								</div>
							
						

					</div>

					<div class="col-md-12 form-group">
						
							
								<div class="col-2 col-md-2">	   
									<label class=" form-control-label"><strong
										style="color: red;">* </strong> Name of Complainant </label>
								</div>
								<div class="col-md-4">
															
									<input type="text" id="name_lady" name="name_lady" class="form-control autocomplete" maxlength="50" autocomplete="off" onkeypress="return onlyAlphabets(event);" oninput="this.value = this.value.toUpperCase()">						
							</div>
						
						
							
								<div class="col-2 col-md-2">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>Date of Complaint/Allegations
									</label>
								</div>
								<div class="col-md-4">

									<input type="text" name="dt_of_comp" id="dt_of_comp"
										maxlength="10" value="DD/MM/YYYY"
										onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control"
										style="width: 85%; display: inline;"
										onfocus="this.style.color='#000000'"
										onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);"
										onkeyup="clickclear(this, 'DD/MM/YYYY')"
										onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true"
										autocomplete="off" style="color: rgb(0, 0, 0);" />
								</div>
							
						

					</div>
                         <div class="col-md-12 form-group">				  
            		<div class="col-2 col-md-2">	             		
                	<label for="text-input"  class=" form-control-label"><strong style="color: red;" >*</strong>Complaint/Allegations</label>
                </div>
                <div class="col-10 col-md-10">
       					<textarea type="text" id="complaint" name="complaint" class="form-control autocomplete" autocomplete="off" rows="4" cols="50" maxlength="255" onkeypress="return /[0-9a-zA-Z\s]/i.test(event.key)"></textarea>
              </div>
    </div>
      <div class="col-md-12 form-group">				  
            		<div class="col-2 col-md-2">	             		
                	<label class=" form-control-label"><strong
										style="color: red;">* </strong> Status of the Case
									</label>
                </div>
                <div class="col-10 col-md-10">
       				<textarea type="text" id="status_of_case" name="status_of_case" class="form-control autocomplete" autocomplete="off" rows="4" cols="50" maxlength="255" onkeypress="return /[0-9a-zA-Z\s]/i.test(event.key)"></textarea>
              </div>
    </div>

				</div>
     	<input type="hidden" id="Hid" name="Hid" value = "0" class="form-control autocomplete" autocomplete="off" >
				<input type="hidden" id="Child_Hid" name="Child_Hid" value = "0" class="form-control autocomplete" autocomplete="off" >
				
				<div class="card-footer" align="center" class="form-control">
					<input type="submit" class="btn btn-primary btn-sm" value="Save" onclick=" return validate();" >
					 <input type="button" name="save" class="btn btn-primary btn-sm" id ="popup" style=" display: none;" value="View History " onclick="Pop_Up_History('Marital_Discord');" />
				</div>
			</div>
		</div>
	</div>

</form:form>


<c:url value="Popup_Marital_DiscordJCOUrl" var="Popup_Marital_DiscordUrl" />
<form:form action="${Popup_Marital_DiscordUrl}" method="post" id="Marital_Discord_Form" name="Marital_Discord_Form" modelAttribute="id" target="result">
	<input type="hidden" name="m_jco_id" id="m_jco_id" value="0" />
</form:form>


<script>



	jQuery(function($) { //on document.ready 
		datepicketDate('dt_of_comp');
		datepicketDate('dt_of_app');

	});

	function personal_number() {

		if ($("input#personnel_no").val() == "") {
			alert("Please Select Army No");
			$("input#personnel_no").focus();
			return false;
		} 
		var army_no = $("input#personnel_no").val();
// 		alert("hello---" + personnel_no);
		$.post('GetArmyNoData?' + key + "=" + value,{army_no : army_no},function(j) {
							if (j != "") {
								$("#jco_id").val(j[0][0]);
								var jco_id = j[0][0];
								$.post('GetCensusDataApproveJCO?' + key+ "=" + value,{jco_id : jco_id},function(k) {
									if (k.length > 0) {
										curr_marital_status = k[0][13];
										$('#marital_event').val('0');
										$("#cadet_name").text(k[0].full_name);
										$("#p_rank").text(k[0].rank);
										$("#hd_p_rank").val(k[0].rank);
										$("#app_sus_no").text(k[0].unit_sus_no);
										
										$.ajaxSetup({
											async : false
										});
										
										var sus_no = k[0].unit_sus_no;
										getunit(sus_no);
										function getunit(val) {
								                $.post("getTargetUnitNameList?"+ key+ "="+ value,{sus_no : sus_no},function(j) {}).done(function(j) {
													var length = j.length - 1;
													var enc = j[length].substring(0,16);
													$("#app_unit_name").text(dec(enc,j[0]));
												}).fail(function(xhr,textStatus,errorThrown) {
										        });
										}
										$.ajaxSetup({
											async : false
										});
										 var cen_id = k[0][1];
									     $.post("CheckJCOStatus?"+ key+ "="+ value,{jco_id:jco_id},function(h) {}).done(function(h) {
									    	 if (h.length > 0) {
									    		 if (h[0].status == "0"){
									    			 $('#Hid').val(h[0].id);
													 $("#name_lady").val(h[0].name_lady);
													 $("#dt_of_comp").val(ParseDateColumncommission(h[0].dt_of_complaint));
													 $("#complaint").val(h[0].complaint);
													 $.ajaxSetup({
															async : false
														});
													 var id = h[0].id;
													 $.post('GetJCOStatusCase?' + key + "=" + value,{id:id}, function(data){
														if(data!=0){
															 $('#Child_Hid').val(data[0].id);
															$("#status_of_case").val(data[0].status_of_case);
														}
												  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
													});
													 
									    		 } else {
										    		 $('#Hid').val(h[0].id);
													 $("#name_lady").val(h[0].name_lady);
													 $("#name_lady").attr('readonly', true);
													 $("#dt_of_comp").val(ParseDateColumncommission(h[0].dt_of_complaint));
													 $("#dt_of_comp").attr('readonly', true);
													 $("#complaint").val(h[0].complaint);
										    		 $("#complaint").attr('readonly', true);
									    		 }
									    	 }
											}).fail(function(xhr,textStatus,errorThrown) {
									        }); 
								}
							});
								
								$.post("Marital_discord_JCO?"+key+"="+value, {jco_id : jco_id}).done(function(j) {
								 	if(j == 1){
								 		$("#name_lady").attr('readonly', true);
								 		$("#dt_of_comp").attr('readonly', true);
								 		$("#complaint").attr('readonly', true);
									} 
									
								}); 
							     
							}
						});
		
		
		
	
// 		$("input#personnel_no").attr('readonly', true);
		
	}

	$("input#personnel_no").keypress(function() {
		var personel_no = this.value;
		var susNoAuto = $("#personnel_no");
		susNoAuto.autocomplete({
			source : function(request, response) {
				$.ajax({
					type : 'POST',
					url : "getpersonnel_noListApproved_JCO?" + key + "=" + value,
					data : {
						personel_no : personel_no
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
					document.getElementById("personnel_no").value = "";
					setReadonly();
					susNoAuto.val("");
					susNoAuto.focus();
					return false;
				}
			},
			select : function(event, ui) {
				setReadonly();
			}
		});
	});
	
	
	function setReadonly(){
		if($("input#personnel_no").val() == ""){
			$("input#personnel_no").attr('readonly', false);
		}
		if(document.getElementById("personnel_no").value != ""){
			$("input#personnel_no").attr('readonly', true);
			$("#popup").show();
		}
		
	}
	
	function Pop_Up_History(a) {

		var x = screen.width/2 - 1100/2;
	    var y = screen.height/2 - 900/2;
	    popupWindow = window.open("", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');
		window.onfocus = function () { 
		}
		jco_id = $("#jco_id").val();

		if(a == "Marital_Discord"){
			$('#m_jco_id').val(jco_id);
			document.getElementById('Marital_Discord_Form').submit();
		}
	}
	
	function validate() {
		 
			  if ($("input#personnel_no").val().trim() == "") {
				alert("Please Enter Army No");
				$("input#personnel_no").focus();
				return false;
			 }
		 if ($("input#name_lady").val().trim() == "") {
				alert("Please Enter Name of Complainant.");
				$("input#name_lady").focus();
				return false;
		 }
		 if ($("input#dt_of_comp").val().trim() == "" || $("input#dt_of_comp").val().trim() == "DD/MM/YYYY") {
				alert("Please Enter Date of Complainant/Allegations.");
				$("input#dt_of_comp").focus();
				return false;
		 }
		if ($("textarea#complaint").val().trim() == "") {
			alert("Please Enter Complaint/Allegations.");
			$("textarea#complaint").focus();
			return false;
		} 
		if ($("textarea#status_of_case").val().trim() == "") {
			alert("Please Enter Status of the Caseqqqq.");
			$("textarea#status_of_case").focus();
			return false;
		}
		 return true;
	}
	</script>	
