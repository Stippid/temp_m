<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>

<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

<!-- <link rel="stylesheet" href="js/assets/css/bootstrap.min.css"> -->
<link rel="stylesheet" href="js/layout/css/animation.css">
<!-- <link rel="stylesheet" href="js/assets/scss/style.css">  -->
<!-- <link rel="stylesheet" href="js/assets/collapse/collapse.css"> -->

<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
<!-- <script src="js/common/commonmethod.js" type="text/javascript"></script> -->

<!-- resizable_col -->
<link rel="stylesheet"
	href="js/assets/adjestable_Col/jquery.resizableColumns.css">
<script src="js/assets/adjestable_Col/jquery.resizableColumns.js"
	type="text/javascript"></script>


<form:form name="army_CourseId" id="army_CourseId"
	action="army_Course_Action" method='POST'
	commandName="misoConverUntDtlCMD" >
	<div class="animated fadeIn">
		<div class="container-fluid" align="center">
			<div class="row">
				<div class="card">
					<!-- 					<div class="panel-group" id="accordion98"> -->
					<!-- 						<div class="panel panel-default" id="cc_div1"> -->
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
											style="color: red;">* </strong> Personal No </label>
									</div>
									<div class="col-md-8">
										<input type="text" id="personnel_no" name="personnel_no"
											class="form-control autocomplete" maxlength="9"
											autocomplete="off" onkeyup="return specialcharecter(this)"
											onkeypress="return AvoidSpace(event)">
											 <input
											type="hidden" id="comm_id" name="comm_id"
											class="form-control autocomplete" autocomplete="off"
											value="0"> 
											<input type="hidden" id="comm_date"
											name="comm_date" class="form-control autocomplete"
											autocomplete="off" value="0"> 
											<input type="hidden"
											id="census_id" name="census_id" value="0"
											class="form-control autocomplete" autocomplete="off">
										  <input type="hidden" id="editorapprove" name="editorapprove"
											class="form-control autocomplete" autocomplete="off"
											value="0">
											 <input type="hidden" id="id" name="id" class="form-control autocomplete" autocomplete="off" value="0"> 
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
										<input type="text" id="army_course_authority"
											name="army_course_authority"
											class="form-control autocomplete" autocomplete="off"
											maxlength="100" onkeyup="return specialcharecter(this)">
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
										<input type="text" name="army_course_date_of_authority"
											id="army_course_date_of_authority" maxlength="10"
											onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control"
											style="width: 93%; display: inline;"
											onfocus="this.style.color='#000000'"
											onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
											onkeyup="clickclear(this, 'DD/MM/YYYY')"
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);"
											aria-required="true" autocomplete="off"
											style="color: rgb(0, 0, 0);">
									</div>
								</div>
							</div>
						</div>
						<table id="army_course_table" class="table-bordered "
							style="margin-top: 10px; width: -webkit-fill-available;">
							<thead style="color: white; text-align: center;">
								<tr>
									<th>Sr No</th>
									<th><strong style="color: red;">* </strong>Course Name</th>
									<th><strong style="color: red;">* </strong>Course Name
										Abbreviation</th>
									<th><strong style="color: red;">* </strong>Course
										Institute</th>
									<th>Course Institute Other</th>
									<th><strong style="color: red;">* </strong>Div/Grade</th>
									<th>Div/Grade Other</th>
									<th><strong style="color: red;">* </strong>Course Type</th>
									<th><strong style="color: red;">* </strong>Start Date</th>
									<th><strong style="color: red;">* </strong>Date of
										Completion</th>
									<th>Action</th>
								</tr>
							</thead>
							<tbody data-spy="scroll" id="army_coursetbody"
								style="min-height: 46px; max-height: 650px; text-align: center;">
								<tr id="tr_army_course1">
									<td class="army_course_srno">1</td>

									<td><input type="text" id="army_course_name1"
										name="army_course_name1"
										onkeypress="AutoCompleteCourse(this,1,0);"
										class="form-control autocomplete" autocomplete="off"
										maxlength="20"></td>
									<td><input type="text" id="army_course_abbreviation1"
										name="army_course_abbreviation1"
										onkeypress="AutoCompleteCourse(this,1,1);"
										class="form-control autocomplete" autocomplete="off"
										maxlength="20"></td>

									<td><select id="army_course_institute1"
										name="army_course_institute1"
										class="form-control autocomplete"
										onchange="onArmyCourseInstiChange(1)">
											<option value="0">--Select--</option>
											<c:forEach var="item" items="${getArmyCourseInstituteList}"
												varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
									</select></td>
									<td><input type="text" id="army_course_institute_ot1"
										name="army_course_institute_ot1" maxlength="50"
										class="form-control autocomplete" readonly autocomplete="off"
										onkeypress="return onlyAlphabets(event);"></td>
									<td><select id="army_course_div_grade1"
										name="army_course_div_grade1"
										class="form-control autocomplete" onchange="onDivGrade(1)">
											<option value="0">--Select--</option>
											<c:forEach var="item" items="${getDivclassList}"
												varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
									</select></td>
									<td><input type="text" id="ar_course_div_ot1"
										name="ar_course_div_ot1" class="form-control autocomplete"
										readonly autocomplete="off"
										onkeypress="return /[0-9a-zA-Z]/i.test(event.key)"
										maxlength="50"></td>
									<td><select id="army_course_type1"
										name="army_course_type1" class="form-control autocomplete">
											<option value="0">--Select--</option>
											<c:forEach var="item" items="${getCourseType}"
												varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
									</select></td>
									<td style="display: none"><input type="text"
										id="course_type_ot1" name="course_type_ot1"
										class="form-control autocomplete" maxlength="50" readonly
										autocomplete="off"
										onkeypress="return /[0-9a-zA-Z]/i.test(event.key)"></td>
									<td><input type="text" name="army_course_start_date1"
										id="army_course_start_date1" maxlength="10"
										onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control"
										style="width: 93%; display: inline;"
										onfocus="this.style.color='#000000'"
										onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
										onkeyup="clickclear(this, 'DD/MM/YYYY')"
										onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);"
										aria-required="true" autocomplete="off"
										style="color: rgb(0, 0, 0);"></td>
									<td><input type="text"
										name="army_course_date_of_completion1"
										id="army_course_date_of_completion1" maxlength="10"
										onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control"
										style="width: 93%; display: inline;"
										onfocus="this.style.color='#000000'"
										onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
										onkeyup="clickclear(this, 'DD/MM/YYYY')"
										onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);"
										aria-required="true" autocomplete="off"
										style="color: rgb(0, 0, 0);"></td>
									<td style="display: none;"><input type="text"
										id="army_course_ch_id1" name="army_course_ch_id1" value="0"
										class="form-control autocomplete" autocomplete="off"></td>
									<td><a class="btn btn-primary btn-sm" value="SAVE"
										title="SAVE" id="army_course_save1"
										onclick="army_course_save_fn(1);"><i class="fa fa-save"></i></a>
										<a style="display: none;" class="btn btn-success btn-sm"
										value="ADD" title="ADD" id="army_course_add1"
										onclick="army_course_add_fn(1);"><i class="fa fa-plus"></i></a>
										<a style="display: none;" class="btn btn-danger btn-sm"
										value="REMOVE" title="REMOVE" id="army_course_remove1"
										onclick="army_course_remove_fn(1);"><i class="fa fa-trash"></i></a></td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="card-footer" align="center" id="ButtonId"
						class="form-control" style="display: none;">
						<a href="searchArmyCourseUrl" class="btn btn-danger btn-sm">Cancel</a>
						
					</div>
					<div class="card-footer" align="center" class="form-control">
					<a href="armyCourseUrl" class="btn btn-success btn-sm" id ="clear" >Clear</a> 
					<input type="button" name="save" class="btn btn-secondary btn-sm" id ="popup"  value="View History " onclick="Pop_Up_History('AC');" />
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 		</div> -->
	<!-- 	</div> -->
	
	</form:form>
	
	<c:url value="Popup_ArmycourseUrl" var="Popup_ArmycourseUrl" />
<form:form action="${Popup_ArmycourseUrl}" method="post" id="popup_ac" name="popup_ac" modelAttribute="id" target="result">
	<input type="hidden" name="comm_id1" id="comm_id1" value="0" />
</form:form>
	
	


<script>
	function onArmyCourseInstiChange(index) {
		var val = $("#army_course_institute" + index + " option:selected")
				.text();
		//alert("hiii---" + type_of_entry_sel);
		if (val != "OTHERS") {

			$("#army_course_institute_ot" + index).prop("readonly", true);
			$('#army_course_institute_ot' + index).val('');
		} else
			$("#army_course_institute_ot" + index).prop("readonly", false);

	}

	function onDivGrade(ind) {
		var army_course_div_grade = $(
				"#army_course_div_grade" + ind + " option:selected").text();

		if (army_course_div_grade != "OTHERS") {
			$('#ar_course_div_ot' + ind).val("");
			$('#ar_course_div_ot' + ind).attr('readonly', true);
		} else
			$('#ar_course_div_ot' + ind).attr('readonly', false);
	}
	function onCoursetype(ind) {
		var army_course_type = $("#army_course_type" + ind + " option:selected")
				.text();

		if (army_course_type != "OTHERS") {
			$('#course_type_ot' + ind).val("");
			$('#course_type_ot' + ind).attr('readonly', true);
		} else
			$('#course_type_ot' + ind).attr('readonly', false);
	}

	$(document).ready(function() {
		if ('${personnel_no_e}' != "") {

			$("#personnel_no").val('${personnel_no_e}');
			$("#app_unit_name").text('${unit_name5}');
			$("#app_sus_no").text('${unit_sus_no5}');
			personal_number();
			$("#personnel_no").attr("disabled", true);
			
		}

		if ('${misoConverUntDtlCMD}' != "") {
			$("#ButtonId").show();

		}
		
		if ('${name}' != "") {
			$('#name').val('${name}');
		}
		if ('${rank}' != "") {
			$('#rank').val('${rank}');
		}

	});

	$("input#personnel_no").keyup(function() {
		var personel_no = this.value;
		var susNoAuto = $("#personnel_no");
		susNoAuto.autocomplete({
			source : function(request, response) {
				$.ajax({
					type : 'POST',
					url : "getpersonnel_noListFORComm?" + key + "=" + value,
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
					personal_number();
					return true;
				} else {
					alert("Please Enter Approved Personal No");
					document.getElementById("personnel_no").value = "";
					susNoAuto.val("");
					susNoAuto.focus();
					return false;
				}
			},
			select : function(event, ui) {

			}
		});

	});

	jQuery(function($) { //on document.ready  
		datepicketDate('army_course_date_of_authority');
		datepicketDate('army_course_start_date1');
		datepicketDate('army_course_date_of_completion1');
	});

	function get_army_course_details() {

		var p_id = $('#census_id').val();
		var comm_id = $('#comm_id').val();
		var i = 0;
		$
				.post(
						'update_offr_army_course_getData?' + key + "=" + value,
						{
							p_id : p_id,
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
									var dauth = ParseDateColumncommission(j[i].date_of_authority);
									var start_date = ParseDateColumncommission(j[i].start_date);
									var date_of_completion = ParseDateColumncommission(j[i].date_of_completion);
									$("table#army_course_table")
											.append(
													'<tr id="tr_army_course'+xaco+'">'
															+ '<td class="army_course_srno" style="width: 2%;">'
															+ xaco
															+ '</td>'
															+ '<td style="width: 10%;">'
															+ '<input type="text" id="army_course_name'
															+ xaco
															+ '" name="army_course_name'
															+ xaco
															+ '" value="'
															+ j[i].course_name
															+ '" onkeypress="AutoCompleteCourse(this,'
															+ xaco
															+ ',0);" class="form-control autocomplete" autocomplete="off" maxlength="20">'
															+ '</td>'
															+ '<td style="width: 10%;">'
															+ '<input type="text" id="army_course_abbreviation'
															+ xaco
															+ '" name="army_course_abbreviation'
															+ xaco
															+ '" value="'
															+ j[i].course_abbreviation
															+ '" class="form-control autocomplete" onkeypress="AutoCompleteCourse(this,'
															+ xaco
															+ ',1);" autocomplete="off" maxlength="20">'
															+ '</td>'
															+ '<td style="width: 10%;">'
															+ '<select  id="army_course_institute'
															+ xaco
															+ '" name="army_course_institute'
															+ xaco
															+ '" class="form-control autocomplete" onchange="onArmyCourseInstiChange('
															+ xaco
															+ ')" >'
															+ '<option value="0">--Select--</option>'
															+ '<c:forEach var="item" items="${getArmyCourseInstituteList}" varStatus="num">'
															+ '<option value="${item[0]}" name="${item[1]}">${item[1]}</option> '
															+ '</c:forEach></select></td>'
															+ '<td style="width: 10%; " > <input type="text" id="army_course_institute_ot'
															+ xaco
															+ '" value="'
															+ j[i].course_institute_other
															+ '" name="army_course_institute_ot'
															+ xaco
															+ '" maxlength="50" onkeypress="return onlyAlphabets(event);" readonly  class="form-control autocomplete" autocomplete="off" ></td>'
															+ '<td style="width: 10%;">'
															+ '<select  id="army_course_div_grade'
															+ xaco
															+ '" name="army_course_div_grade'
															+ xaco
															+ '" onchange="onDivGrade('
															+ xaco
															+ ')" class="form-control autocomplete"  >'
															+ '<option value="0">--Select--</option>'
															+ '<c:forEach var="item" items="${getDivclassList}" varStatus="num">'
															+ '<option value="${item[0]}" name="${item[1]}">${item[1]}</option> '
															+ '</c:forEach></select></td>'
															+ '<td style="width: 10%;"> <input type="text" id="ar_course_div_ot'
															+ xaco
															+ '" name="ar_course_div_ot'
															+ xaco
															+ '" value="'
															+ j[i].ar_course_div_ot
															+ '" maxlength="50" onkeypress="return onlyAlphabets(event);" readonly  class="form-control autocomplete" autocomplete="off" ></td>'

															+ '<td style="width: 10%;">'
															+ '<select  id="army_course_type'+xaco+'" name="army_course_type'+xaco+'"   class="form-control autocomplete"  >'
															+ '<option value="0">--Select--</option>'
															+ '<c:forEach var="item" items="${getCourseType}" varStatus="num">'
															+ '<option value="${item[0]}" name="${item[1]}">${item[1]}</option> '
															+ '</c:forEach> </select>	</td>'
															+ '<td style="width: 10%; display: none"> <input type="text" id="course_type_ot'
															+ xaco
															+ '" name="course_type_ot'
															+ xaco
															+ '" value="'
															+ j[i].course_type_ot
															+ '" maxlength="50" onkeypress="return onlyAlphabets(event);" readonly  class="form-control autocomplete" autocomplete="off" ></td>'

															+ '<td style="width: 10%;">'
															+ ' <input type="text" name="army_course_start_date'
															+ xaco
															+ '" id="army_course_start_date'
															+ xaco
															+ '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')"   class="form-control" style="width: 85%;display: inline;" '
															+ '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
															+ '	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="'
															+ start_date
															+ '">'
															+ '</td>'
															+ '<td style="width: 10%;">'
															+ ' <input type="text" name="army_course_date_of_completion'
															+ xaco
															+ '" id="army_course_date_of_completion'
															+ xaco
															+ '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')"   class="form-control" style="width: 85%;display: inline;" '
															+ '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
															+ '	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="'
															+ date_of_completion
															+ '">'
															+ '</td>'
															+ '<td style="display:none;"><input type="text" id="army_course_ch_id'+xaco+'" name="army_course_ch_id'+xaco+'"  value="'+j[i].id+'" class="form-control autocomplete" autocomplete="off" ></td>'
															+ '<td style="width: 10%;">'
															+ '<a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "army_course_save'
															+ xaco
															+ '" onclick="army_course_save_fn('
															+ xaco
															+ ');" ><i class="fa fa-save"></i></a>'
															+ '<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "army_course_add'
															+ xaco
															+ '" onclick="army_course_add_fn('
															+ xaco
															+ ');" ><i class="fa fa-plus"></i></a>'
															+ '<a  class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "army_course_remove'
															+ xaco
															+ '" onclick="army_course_remove_fn('
															+ xaco
															+ ');"><i class="fa fa-trash"></i></a> '
															+ '</td></tr>');
									$('#army_course_div_grade' + xaco).val(
											j[i].div_grade);
									$('#army_course_institute' + xaco).val(
											j[i].course_institute);
									$('#army_course_type' + xaco).val(
											j[i].course_type);
									$('#army_course_authority').val(
											j[i].authority);
									$('#army_course_date_of_authority').val(
											dauth);
									datepicketDate('army_course_start_date'
											+ xaco);
									datepicketDate('army_course_date_of_completion'
											+ xaco);
									onDivGrade(xaco);
									onCoursetype(xaco);
									onArmyCourseInstiChange(xaco);
								}
								if (xaco != 0) {
									armyc = xaco;
									armyc_srno = xaco;
									$("#army_course_add" + xaco).show();
								}
							}
						});
	}
var armyc ="";
var armyc_srno ="";
	function army_course_add_fn(q) {
		if ($('#army_course_add' + q).length) {
			$("#army_course_add" + q).hide();
		}
		armyc = q + 1;
		if (q != 0)
			armyc_srno += 1;
		
		$("table#army_course_table")
				.append(
						'<tr id="tr_army_course'+armyc+'">'
								+ '<td class="army_course_srno" style="width: 2%;">'
								+ armyc
								+ '</td>'
								+ '<td style="width: 10%;">'
								+ '<input type="text" id="army_course_name'
								+ armyc
								+ '" name="army_course_name'
								+ armyc
								+ '" class="form-control autocomplete" onkeypress="AutoCompleteCourse(this,'
								+ armyc
								+ ',0);" autocomplete="off" maxlength="20">'
								+ '</td>'
								+ '<td style="width: 10%;">'
								+ '<input type="text" id="army_course_abbreviation'
								+ armyc
								+ '" name="army_course_abbreviation'
								+ armyc
								+ '" class="form-control autocomplete" onkeypress="AutoCompleteCourse(this,'
								+ armyc
								+ ',1);" autocomplete="off" maxlength="20">'
								+ '</td>'

								+ '<td style="width: 10%;">'
								+ '<select  id="army_course_institute'
								+ armyc
								+ '" name="army_course_institute'
								+ armyc
								+ '" class="form-control autocomplete" onchange="onArmyCourseInstiChange('
								+ armyc
								+ ')" >'
								+ '<option value="0">--Select--</option>'
								+ '<c:forEach var="item" items="${getArmyCourseInstituteList}" varStatus="num">'
								+ '<option value="${item[0]}" name="${item[1]}">${item[1]}</option> '
								+ '</c:forEach></select></td>'
								+ '<td style="width: 10%; " > <input type="text" id="army_course_institute_ot'
								+ armyc
								+ '" name="army_course_institute_ot'
								+ armyc
								+ '" maxlength="50" onkeypress="return onlyAlphabets(event);" readonly  class="form-control autocomplete" autocomplete="off" ></td>'

								+ '<td style="width: 10%;">'
								+ '<select  id="army_course_div_grade'
								+ armyc
								+ '" name="army_course_div_grade'
								+ armyc
								+ '" class="form-control autocomplete" onchange="onDivGrade('
								+ armyc
								+ ')" >'
								+ '<option value="0">--Select--</option>'
								+ '<c:forEach var="item" items="${getDivclassList}" varStatus="num">'
								+ '<option value="${item[0]}" name="${item[1]}">${item[1]}</option> '
								+ '</c:forEach></select></td>'

								+ '<td style="width: 10%; " > <input type="text" id="ar_course_div_ot'
								+ armyc
								+ '" name="ar_course_div_ot'
								+ armyc
								+ '" maxlength="50" onkeypress="return onlyAlphabets(event);" readonly  class="form-control autocomplete" autocomplete="off" ></td>'

								+ '<td style="width: 10%;">'
								+ '<select  id="army_course_type'+armyc+'" name="army_course_type'+armyc+'" class="form-control autocomplete"  >'
								+ '<option value="0">--Select--</option>'
								+ '<c:forEach var="item" items="${getCourseType}" varStatus="num">'
								+ '<option value="${item[0]}" name="${item[1]}">${item[1]}</option> '
								+ '</c:forEach> </select>	</td>'

								+ '<td style="width: 10%;display: none"> <input type="text" id="course_type_ot'
								+ armyc
								+ '" name="course_type_ot'
								+ armyc
								+ '" maxlength="50" onkeypress="return onlyAlphabets(event);" readonly  class="form-control autocomplete" autocomplete="off" ></td>'

								+ '<td style="width: 10%;">'
								+ ' <input type="text" name="army_course_start_date'
								+ armyc
								+ '" id="army_course_start_date'
								+ armyc
								+ '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')"   class="form-control" style="width: 85%;display: inline;" '
								+ '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
								+ '	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);">'
								+ '</td>'
								+ '<td style="width: 10%;">'
								+ ' <input type="text" name="army_course_date_of_completion'
								+ armyc
								+ '" id="army_course_date_of_completion'
								+ armyc
								+ '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')"   class="form-control" style="width: 85%;display: inline;" '
								+ '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
								+ '	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);">'
								+ '</td>'
								+ '<td style="display:none;"><input type="text" id="army_course_ch_id'+armyc+'" name="army_course_ch_id'+armyc+'"  value="0" class="form-control autocomplete" autocomplete="off" ></td>'
								+ '<td style="width: 10%;">'
								+ '<a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "army_course_save'
								+ armyc
								+ '" onclick="army_course_save_fn('
								+ armyc
								+ ');" ><i class="fa fa-save"></i></a>'
								+ '<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "army_course_add'
								+ armyc
								+ '" onclick="army_course_add_fn('
								+ armyc
								+ ');" ><i class="fa fa-plus"></i></a>'
								+ '<a style="display:none;" class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "army_course_remove'
								+ armyc
								+ '" onclick="army_course_remove_fn('
								+ armyc
								+ ');"><i class="fa fa-trash"></i></a> '
								+ '</td></tr>');

		datepicketDate('army_course_start_date' + armyc);
		datepicketDate('army_course_date_of_completion' + armyc);
	}

	function army_course_save_fn(ps) {

		var army_course_name = $('#army_course_name' + ps).val();
		var army_course_abbr = $('#army_course_abbreviation' + ps).val();
		var army_course_institue = $('#army_course_institute' + ps).val();
		var army_course_institue_ot = $('#army_course_institute_ot' + ps).val();

		var army_course_div_grade = $('#army_course_div_grade' + ps).val();
		var ar_course_div_ot = $('#ar_course_div_ot' + ps).val();
		var army_course_type = $('#army_course_type' + ps).val();
		var course_type_ot = $('#course_type_ot' + ps).val();

		var army_course_start_date = $('#army_course_start_date' + ps).val();
		var army_course_date_of_completion = $(
				'#army_course_date_of_completion' + ps).val();
		var army_course_ch_id = $('#army_course_ch_id' + ps).val();
		var p_id = $('#census_id').val();
		var com_id = $('#comm_id').val();
		var army_course_authority = $('#army_course_authority').val();
		var army_course_doa = $('#army_course_date_of_authority').val();
		var personnel_no = $('#personnel_no').val();

		var comm_date = $('#comm_date').val();

		if ($('#personnel_no').val() == "") {
			alert("Please Enter Personal No");
			$("input#personnel_no").focus();
			return false;
		}

		if (army_course_authority.trim() == "") {
			alert("Please Enter Authority");
			$("input#army_course_authority").focus();
			return false;
		}
		if (army_course_doa.trim() == "" || army_course_doa == "DD/MM/YYYY") {
			alert("Please Enter Date Of Authority");
			$("input#army_course_date_of_authority").focus();
			return false;
		}
		if (ParseDateColumncommission($("input#comm_date").val()) >= getformatedDate($(
				"#army_course_date_of_authority").val())) {
			alert("Authority Date should be Greater than Commission Date");
			return false;
		}

		if (army_course_name.trim() == "") {
			alert("Please Enter Course Name");
			$("#army_course_name" + ps).focus();
			return false;
		}
		if (army_course_abbr.trim() == "") {
			alert("Please Enter Course Abbreviation");
			$("#army_course_abbreviation" + ps).focus();
			return false;
		}
		if (army_course_institue == "0") {
			alert("Please Select Course Institute");
			$("#army_course_institute" + ps).focus();
			return false;
		}

		var army_course_institueV = $(
				"#army_course_institute" + ps + " option:selected").text();
		if (army_course_institueV == "OTHERS"
				&& army_course_institue_ot.trim() == "") {
			alert("Please Enter Other Course Institute");
			$('#army_course_institute_ot' + ps).focus();
			return false;
		}
		if (army_course_div_grade == "0") {
			alert("Please select Div/Grade");
			$("#army_course_div_grade" + ps).focus();
			return false;
		}
		var army_course_div_gradeV = $(
				"#army_course_div_grade" + ps + " option:selected").text();
		if (army_course_div_gradeV == "OTHERS" && ar_course_div_ot.trim() == "") {
			alert("Please Enter Other Div/Grade");
			$('#ar_course_div_ot' + ps).focus();
			return false;
		}
		if (army_course_type == "0") {
			alert("Please select Course Type");
			$("#army_course_type" + ps).focus();
			return false;
		}
		//	 	var army_course_typeV = $("#army_course_type"+ps+" option:selected").text();
		//	 	if (army_course_typeV == "OTHERS" && course_type_ot.trim()=="") {
		//	 		alert("Please Enter Other Course Type");
		//	 		$('#course_type_ot'+ps).focus();
		//	 		return false;
		//	 	}
		if (army_course_authority.trim() == "") {
			alert("Please Enter Authority");
			$("#army_course_authority").focus();
			return false;
		}

		if (army_course_start_date.trim() == ""
				|| army_course_start_date == "DD/MM/YYYY") {
			alert("Please Enter Start Date");
			$("#army_course_start_date" + ps).focus();
			return false;
		}
		if (army_course_date_of_completion.trim() == ""
				|| army_course_date_of_completion == "DD/MM/YYYY") {
			alert("Please Enter Date Of Completion");
			$("#army_course_date_of_completion" + ps).focus();
			return false;
		}
		if (getformatedDate(army_course_start_date) > getformatedDate(army_course_date_of_completion)) {
			alert("Completion Date should always be greater than Start Date");
			$("#army_course_date_of_completion" + ps).focus();
			return false;
		}
		$.post('armyCourseActionOut?' + key + "=" + value, {
			comm_date : comm_date,
			army_course_name : army_course_name,
			army_course_div_grade : army_course_div_grade,
			ar_course_div_ot : ar_course_div_ot,
			army_course_type : army_course_type,
			course_type_ot : course_type_ot,
			army_course_start_date : army_course_start_date,
			army_course_date_of_completion : army_course_date_of_completion,
			army_course_ch_id : army_course_ch_id,
			p_id : p_id,
			com_id : com_id,
			army_course_authority : army_course_authority,
			army_course_doa : army_course_doa,
			army_course_institueV : army_course_institueV,
			army_course_abbr : army_course_abbr,
			army_course_institue : army_course_institue,
			army_course_institue_ot : army_course_institue_ot,
			personnel_no : personnel_no
		}, function(data) {
			if (data == "update")
				alert("Data Updated Successfully");
			else if (parseInt(data) > 0) {
				$('#army_course_ch_id' + ps).val(data);
				$("#army_course_add" + ps).show();
				$("#army_course_remove" + ps).show();
				alert("Data Saved Successfully")
			} else
				alert("Data already Exist.")
		}).fail(function(xhr, textStatus, errorThrown) {
			alert("fail to fetch")
		});
	}

	function AutoCompleteCourse(ele, index, val) {
		type = "";
		var category = "OFFICER";
		//var category="1";
		var abbr = [];
		var susval = [];
		if (val == '1') {
			type = "abbr";
		}

		var code = ele.value;
		var susNoAuto = $("#" + ele.id);
		susNoAuto.autocomplete({
			source : function(request, response) {
				$.ajax({
					type : 'POST',
					url : "getArmyCourseNameList?" + key + "=" + value,
					data : {
						Course : code,
						type : type,
						category : category
					},
					success : function(data) {

						var length = data.length - 1;

						for (var i = 0; i < data.length; i++) {

							if (val == '1') {
								susval.push(data[i][2]);
								abbr.push(data[i][1]);
							}

							else {
								susval.push(data[i][1]);
								abbr.push(data[i][2]);
							}

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

					if (val == '1') {
						//alert("Please Enter Valid Abbreviation ");				
						$("#army_course_name" + index).val("");
					}

					else {

						//alert("Please Enter Valid Course Name ");
						$("#army_course_abbreviation" + index).val("");
					}

					susNoAuto.val("");
					susNoAuto.focus();
					return false;
				}
			},
			select : function(event, ui) {
				var arrindex = susval.indexOf(ui.item.value);
				var second = abbr[arrindex];
				if (val == '1') {
					$("#army_course_name" + index).val(second);
				}

				else {
					$("#army_course_abbreviation" + index).val(second);
				}

				
				$(this).val(ui.item.value);
				armyCoureseCheck($(this).val(),index);
			}
		});

	}

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

		$("input#personnel_no").attr('readonly', false);
		$("#popup").show();
	}

	function army_course_remove_fn(R) {
		 if (R == '1') {
			alert("Can't Delete From Here!! \nPlease Ask Approver to Reject This Entry");
		} else  {
			var rc = confirm("Are You Sure! You Want To Delete");
			if (rc) {
				var army_course_ch_id = $('#army_course_ch_id' + R).val();
				$.post(
						'update_offr_army_course_delete_action?' + key + "="
								+ value, {
							army_course_ch_id : army_course_ch_id
						}, function(data) {
							if (data == '1') {
								$("tr#tr_army_course" + R).remove();
								if (R == armyc) {
									R = R - 1;
									var temp = true;
									for (i = R; i >= 1; i--) {
										if ($('#army_course_add' + i).length) {
											$("#army_course_add" + i).show();
											temp = false;
											armyc = i;
											break;
										}
									}

									if (temp) {
										army_course_add_fn(0);
									}
								}
								$('.army_course_srno').each(function(i, obj) {

									obj.innerHTML = i + 1;
									armyc_srno = i + 1;
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
	}
	
	function Pop_Up_History(a) {

		var x = screen.width/2 - 1100/2;
	    var y = screen.height/2 - 900/2;
	    popupWindow = window.open("", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');
		window.onfocus = function () { 
		}
		comm_id = $("#comm_id").val();

		if(a == "AC"){
			$('#comm_id1').val(comm_id);
			document.getElementById('popup_ac').submit();
		}
	}
	
	function armyCoureseCheck(army_course,index){
		
		 army_course;
		
		var personnel_no = $('#personnel_no').val();
		var comm_id = $('#comm_id').val();
		
		$.post("army_courese_check?" + key + "=" + value, {
			army_course: army_course,
			personnel_no:personnel_no,
			comm_id:comm_id
			
		}).done(function(j) {
						
	 		 if(j.length > 0){
	 			 
	 			 alert("This Course Alredy Taken By Personal.");
	 			
	 			$("#army_course_name" + index).autocomplete("close");
	 			
	 			$("#army_course_abbreviation" + index).autocomplete("close");
	 		 } 		 
		}).fail(function(xhr, textStatus, errorThrown) {}); 

	}
</script>
