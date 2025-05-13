<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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

<!-- <link rel="stylesheet" href="js/assets/css/bootstrap.min.css"> -->
<link rel="stylesheet" href="js/layout/css/animation.css">
<!-- <link rel="stylesheet" href="js/assets/scss/style.css">  -->
<link rel="stylesheet" href="js/assets/collapse/collapse.css">

<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>

<form name="Approve_army_course_Data" id="Approve_army_course_Data"
	action="Approve_army_course_DataAction?${_csrf.parameterName}=${_csrf.token}"
	method="post" class="form-horizontal"
	commandName="Approve_army_course_DataCMD">
	<div class="animated fadeIn">
		<div class="container-fluid" align="center">
			<div class="card">
				<div class="card-header">
						<h5>
							<b>ARMY COURSE</b><br>
						</h5>
					</div>
					<div class="card-body card-block">
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong>Personal No</label>
										</div>
										<div class="col-md-8">
											<input type="text" id="personnel_no" name="personnel_no"
												class="form-control autocomplete" autocomplete="off" readonly>
												
												<input type="hidden" id="comm_id" name="comm_id"
												value="0">
										</div>
									</div>
								</div>
							</div>
							
							<!-- pranay 24.09------------------------------------------- -->
						
						<div class="col-md-12" >	              					
						<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"> Name </label>
									</div>
									<div class="col-md-8">
										<label id="name" name="name"> </label>
									</div>
								</div>
							</div>												
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"> Rank </label>
									</div>
									<div class="col-md-8">
									 <label id="rank" name="rank"> </label>
									</div>
								</div>
							</div>								
						</div>
						
						<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Unit Name </label>
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
									<label class=" form-control-label"> SUS No </label>
								</div>
								<div class="col-md-8">
									<label class=" form-control-label" id="app_sus_no"></label>
								</div>
							</div>
						</div>

					</div>	
						
						<!-- end ------------------------------------------- -->
							
							
							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong>Authority</label>
										</div>
										<div class="col-md-8">
											<label id="army_course_authority"
												name="army_course_authority" class=" autocomplete"></label>
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong>Date of Authority</label>
										</div>
										<div class="col-md-8">
											<label id="army_course_date_of_authority"
												name="army_course_date_of_authority" class=" autocomplete">
											</label>
										</div>
									</div>
								</div>
							</div>
							<table id="army_course_table" class="table-bordered "
								style="margin-top: 10px; width: -webkit-fill-available;">
								<thead style="color: white; text-align: center;">
									<tr>
										<th style="width: 2%;">Sr No</th>
										<th style="width: 10%;">Course Name</th>
										<th style="width: 10%;">Course Name Abbreviation</th>
										<th style="width: 10%;">Course Institute</th>
										<th style="width: 10%;">Course Institute Other</th>
										<th style="width: 10%;">Div/Grade</th>
										<th style="width: 10%;">Div/Grade Other</th>
										<th style="width: 10%;">Course Type</th>
										<th style="width: 10%;">Start Date</th>
										<th style="width: 10%;">Date of Completion</th>

									</tr>
								</thead>
								<tbody data-spy="scroll" id="army_coursetbody"
									style="min-height: 46px; max-height: 650px; text-align: center;">
									<tr id="tr_army_course1">
										<td class="army_course_srno" style="width: 2%;">1</td>
									</tr>
								</tbody>
							</table>
						</div>
						
							
				
						<div class="card-footer" align="center" class="form-control">
							<input type="submit" class="btn btn-primary btn-sm"
								value="Approve"
								onclick="if (confirm('Are You Sure You Want to Approve Officer Data?')){return true;}else{return false;}">
							<input type="button" class="btn btn-primary btn-sm"
								value="Reject"
								onclick="if (confirm('Are You Sure You Want to Reject Officer Data?')){addRemarkModel('get_ArmyCourse_Reject',0)}else{return false;}">
						</div>
				
			</div>
						
		</div>
		</div>
<!-- 	</div> -->
</form>


<script>
	$(document).ready(function() {
		$('#comm_id').val('${comm_id}');
		$('#personnel_no').val('${personnel_no2}');
		
		
		$("#app_unit_name").text('${unit_name5}');
		$("#app_sus_no").text('${unit_sus_no5}');
		personal_number();
		get_army_course_details();
		
	});
	
	
	function personal_number() {

		if ($("input#personnel_no").val() == "") {
			alert("Please select Personal No");
			$("input#personnel_no").focus();
			return false;
		}
		var personnel_no = $("input#personnel_no").val();

		$.post('GetPersonnelNoData?' + key + "=" + value, {
			personnel_no : personnel_no
		}, function(j) {
			if (j != "") {

				$("#comm_id").val(j[0][0]);
				$("#comm_date").val(j[0][11]);
				
				$("#name").text(j[0][5]);
				$("#rank").text(j[0][6]);	  
			    
				var comm_id = j[0][0];

				$.post('GetCensusDataApprove?' + key + "=" + value, {
					comm_id : comm_id
				}, function(k) {
					if (k.length > 0) {
						$('#census_id').val(k[0][1]);
						$("#app_sus_no").text(k[0][7]);
						$.ajaxSetup({
							async : false
						});

						var sus_no = k[0][7];
						getunit(sus_no);
						function getunit(val) {
							$.post(
									"getTargetUnitNameList?" + key + "="
											+ value, {
										sus_no : sus_no
									}, function(j) {
									}).done(function(j) {
								var length = j.length - 1;
								var enc = j[length].substring(0, 16);
								//alert("unit---" + dec(enc,j[0]));
								$("#app_unit_name").text(dec(enc, j[0]));
							}).fail(function(xhr, textStatus, errorThrown) {
							});
						}
						$.ajaxSetup({
							async : false
						});
						var cen_id = k[0][1];
					}
				});
			}
			get_army_course_details();
		});

		/* $("input#personnel_no").attr('readonly', false); */
		$("#popup").show();
	}
	
	
	function get_army_course_details() {
		debugger;
		var comm_id = $('#comm_id').val();
		var i = 0;
		$
				.post(
						'update_offr_army_course_getData?' + key + "=" + value,
						{
							comm_id : comm_id
						},
						function(j) {
							var v = j.length;
							if (v != 0) {
								xaco = 0;
								for (i; i < v; i++) {

									xaco = xaco + 1;
									if (xaco == 1) {
										$('#army_coursetbody').empty();
									}
									var dauth = convertDateFormate(j[i].date_of_authority);
									var start_date = convertDateFormate(j[i].start_date);
									var date_of_completion = convertDateFormate(j[i].date_of_completion);
									$("table#army_course_table")
											.append(
													'<tr id="tr_lang'+xaco+'">'
															+ '<td class="army_course_srno" style="width: 2%;">'
															+ xaco
															+ '</td>'
															+ '<td style="width: 10%;">'
															+ '<label  id="army_course_name'+xaco+'" name="army_course_name'+xaco+'" value="'+j[i].course_name+'"  class=" autocomplete" >'
															+ j[i].course_name
															+ '</label>'
															+ '</td>'
															+ '<td style="width: 10%;">'
															+ '<label  id="army_course_abbreviation'+xaco+'" name="army_course_abbreviation'+xaco+'"   class=" autocomplete" >'
															+ j[i].course_abbreviation
															+ '</label>'
															+ '</td>'
															+ '<td style="width: 10%;">'
															+ '<label  id="army_course_institutelb'+xaco+'" name="army_course_institutelb'+xaco+'" class=" autocomplete" > </label>'
															+ '<select style="display:none;"  id="army_course_institute'+xaco+'" name="army_course_institute'+xaco+'" class="form-control autocomplete"  >'
															+ '<option value="0">--Select--</option>'
															+ '<c:forEach var="item" items="${getArmyCourseInstitute}" varStatus="num">'
															+ '<option value="${item[0]}" name="${item[1]}">${item[1]}</option> '
															+ '</c:forEach></select></td>'
															+ '<td style="width: 10%;"> '
															+ '<label  id="army_course_institute_ot'+xaco+'" name="army_course_institute_ot'+xaco+'" class=" autocomplete" > </label></td>'
															+ '<td style="width: 10%;">'
															+ '<label  id="army_course_div_gradelb'+xaco+'" name="army_course_div_gradelb'+xaco+'" class=" autocomplete" > </label>'
															+ '<select style="display:none;" id="army_course_div_grade'+xaco+'" name="army_course_div_grade'+xaco+'" class="form-control autocomplete"  >'
															+ '<option value="0">--Select--</option>'
															+ '<c:forEach var="item" items="${getDivclass}" varStatus="num">'
															+ '<option value="${item[0]}" name="${item[1]}">${item[1]}</option> '
															+ '</c:forEach></select></td>'

															+ '<td style="width: 10%;">'
															+ '<label  id="ar_course_div_ot'+xaco+'" name="ar_course_div_ot'+xaco+'" class=" autocomplete" > </label>'
															+ '<td style="width: 10%;">'
															+ '<label  id="army_course_typelb'+xaco+'" name="army_course_typelb'+xaco+'" class=" autocomplete" > </label>'
															+ '<select style="display:none;" id="army_course_type'+xaco+'" name="army_course_type'+xaco+'" class="form-control autocomplete"  >'
															+ '<option value="0">--Select--</option>'
															+ '<c:forEach var="item" items="${getCourseTypeList}" varStatus="num">'
															+ '<option value="${item[0]}" name="${item[1]}">${item[1]}</option> '
															+ '</c:forEach> </select>	</td>'
															+ '<td style="width: 10%; display:none"> '
															+ '<label  id="course_type_ot'+xaco+'" name="course_type_ot'+xaco+'" class=" autocomplete" > </label></td>'
															+ '<td style="width: 10%;">'
															+ '<label  id="army_course_start_date'+xaco+'" name="army_course_start_date'+xaco+'"   class=" autocomplete"  >'
															+ start_date
															+ '</label>'
															+ '</td>'
															+ '<td style="width: 10%;">'
															+ '<label  id="army_course_date_of_completion'+xaco+'" name="army_course_date_of_completion'+xaco+'"  class=" autocomplete" >'
															+ date_of_completion
															+ '</label>'
															+ '</td>'
															+ '<td style="display:none;"><input type="text" id="army_course_ch_id'+xaco+'" name="army_course_ch_id'+xaco+'"  value="'+j[i].id+'" class="form-control autocomplete" autocomplete="off" ></td>'

															+ '</tr>');
									$('#army_course_div_grade' + xaco).val(
											j[i].div_grade);
									$('#army_course_div_gradelb' + xaco)
											.text(
													$(
															'#army_course_div_grade'
																	+ xaco
																	+ " option:selected")
															.text());
									$('#army_course_type' + xaco).val(
											j[i].course_type);
									$('#army_course_institute' + xaco).val(
											j[i].course_institute);
									$('#army_course_institutelb' + xaco)
											.text(
													$(
															'#army_course_institute'
																	+ xaco
																	+ " option:selected")
															.text());
									$('#army_course_institute_ot' + xaco).text(
											j[i].course_institute_other);
									$('#course_type_ot' + xaco).text(
											j[i].course_type_ot);
									$('#army_course_typelb' + xaco)
											.text(
													$(
															'#army_course_type'
																	+ xaco
																	+ " option:selected")
															.text());
									$('#army_course_authority').text(
											j[i].authority);
									$('#army_course_date_of_authority').text(
											dauth);

									$('#ar_course_div_ot' + xaco).text(
											j[i].ar_course_div_ot);

								}
								//		if(xaco!=0){
								//			armyc=xaco;
								//			armyc_srno=xaco;
								//			$("#army_course_add"+xaco).show();
								//		}
							}
						});
	}
	
	
	function get_ArmyCourse_Reject(){
		
		 var comm_id = '${comm_id}';
		 var reject_remarks = $("#reject_remarks").val();
		 $.post('getArmyCourse_Reject?' + key + "=" + value, {comm_id:comm_id,reject_remarks:reject_remarks}, function(j){
			 if(j == "1"){
				 alert("Data Rejected Successfully.");
				 $("#army_course").hide();
			 }else{
				 alert("Data Not Rejected.");
			 }
			 
		 });
		 
		} 
</script>
