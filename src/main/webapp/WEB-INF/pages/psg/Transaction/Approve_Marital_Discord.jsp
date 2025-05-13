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
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>

<form:form name="App_Maritial_Discord" id="App_Maritial_Discord"
	action="App_Maritial_Discord_Action?${_csrf.parameterName}=${_csrf.token}"
	method="post" class="form-horizontal"
	commandName="App_Maritial_Discord_CMD">
	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<h5>APPROVE MARITAL DISCORD</h5>
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
										class="form-control-sm form-control">
										<option value="${getServiceCategoryList.get(0)[0]}"
											name="${getServiceCategoryList.get(0)[1]}">${getServiceCategoryList.get(0)[1]}</option>
									</select>
								</div>
							</div>
						</div>

						<div class="col-md-6" id="per_no_id">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong> Personal No </label>
								</div>
								<div class="col-md-8">
									<input type="text" id="personnel_no" name="personnel_no"
										class="form-control autocomplete" autocomplete="off"
										onchange="personal_number()"> <input type="hidden"
										id="comm_id" name="comm_id" class="form-control"
										autocomplete="off"> <input type="hidden"
										id="census_id" name="census_id" value="0" class="form-control"
										autocomplete="off">
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
										onchange="personal_number_army()"> <input
										type="hidden" id="jco_id" name="jco_id" value="0"
										class="form-control autocomplete" autocomplete="off">
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
									<label class=" form-control-label" id="p_rank"></label> <input
										type="hidden" id="hd_p_rank" name="hd_p_rank"
										class="form-control autocomplete" autocomplete="off">
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
									<label class=" form-control-label" id="cadet_name"></label> <input
										type="hidden" id="cname" name="cname"
										class="form-control autocomplete" autocomplete="off">
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
									<input type="hidden" id="un" name="un"
										class="form-control autocomplete" autocomplete="off">
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
									<input type="text" id="name_lady" name="name_lady"
										class="form-control autocomplete" autocomplete="off">
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
										autocomplete="off" style="color: rgb(0, 0, 0);" />
								</div>
							</div>
						</div>


					</div>
					<div class="col-md-12 form-group">							
						<div class="col-2 col-md-2">
							<label class=" form-control-label"><strong
								style="color: red;">* </strong>Date of Status
							</label>
						</div>
						<div class="col-md-4">
							<input type="text" name="dt_of_status" id="dt_of_status"
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
					
					
					
					<div class="col-md-12">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"><strong
									style="color: red;">* </strong> Complaint/Allegations </label>
							</div>
							<div class="col-md-8">

								<textarea type="text" id="complaint" name="complaint"
									class="form-control autocomplete" autocomplete="off" rows="4"
									cols="50"></textarea>
							</div>
						</div>

					</div>

					<div class="col-md-12">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"><strong
									style="color: red;">* </strong> Status of the Case </label>
							</div>
							<div class="col-md-8">

								<textarea type="text" id="status_of_case" name="status_of_case"
									class="form-control autocomplete" autocomplete="off" rows="4"
									cols="50"></textarea>
							</div>
						</div>

					</div>


				</div>
				<input type="hidden" id="id" name="id"
					class="form-control autocomplete" autocomplete="off">

				<div class="card-footer" align="center" class="form-control">
					<a href="Search_Maritial" class="btn btn-danger btn-sm">Cancel</a>

					<input type="button" class="btn btn-primary btn-sm" value="Reject"
						onclick="if (confirm('Are You Sure You Want to Reject Data?')){addRemarkModel('Reject',0)}else{return false;}">

					<div class="col-md-12" align="center">
						<p>
							<input type="checkbox" id="myCheck" onclick="myFunction();" /> I
							have checked all Details and certify that all the information is
							correct.
						</p>
					</div>

					<div class="col-md-12" id="submit_a" align="center">
						<input type="button" class="btn btn-primary btn-sm"
							value="Approve"
							onclick="if (confirm('Are You Sure You Want to Approve Officer Data?')){return approveData();}else{return false;}">
					</div>

				</div>


			</div>
		</div>
	</div>

</form:form>
<c:url value="approve_Maritial1" var="approveUrl" />
<form:form action="${approveUrl}" method="post" id="approveForm"
	name="approveForm" modelAttribute="id3">
	<input type="hidden" name="id3" id="id3" value="0" />
</form:form>
<c:url value="Reject_Marital_Discord" var="rejectUrl" />
<form:form action="${rejectUrl}" method="post" id="rejectForm"
	name="rejectForm" modelAttribute="id4">
	<input type="hidden" name="id4" id="id4" value="0" />
	<input type="hidden" name="reject_remarks4" id="reject_remarks4" value="0" />
</form:form>
<script>
	function myFunction() {
		var checkBox = document.getElementById("myCheck");

		if (checkBox.checked == true) {
			$("#submit_a").show();

		} else {
			$("#submit_a").hide();

		}
	}
	function approveData() {
		$("#id3").val($("#id").val());
		$("#approveForm").submit();
	}
	function Reject() {
		$("#id4").val($("#id").val());
		$("#reject_remarks4").val($("#reject_remarks").val());
		document.getElementById('rejectForm').submit();
	}
	$(document)
			.ready(
					function() {
						$("#submit_a").hide();
						$("#id").val('${App_Maritial_Discord_CMD.id}');

						$("#service_category").val(
								'${App_Maritial_Discord_CMD.service_category}');
						$("#service_category").attr('readonly', true);
						fn_service_category_change();

						$("#personnel_no").val(
								'${App_Maritial_Discord_CMD.personnel_no}');
						personal_number();
						$("#name_lady").val(
								'${App_Maritial_Discord_CMD.name_lady}');
						$("#name_lady").attr('readonly', true);

						$("#complaint").val(
								'${App_Maritial_Discord_CMD.complaint}');
						$("#complaint").attr('readonly', true);

						$("#dt_of_comp")
								.val(
										ParseDateColumncommission('${App_Maritial_Discord_CMD.dt_of_comp}'));
						$("#dt_of_comp").attr('readonly', true);
						$("#dt_of_status").val(ParseDateColumncommission('${dt_of_status}'));
						$("#dt_of_status").attr('readonly', true);
						$("#status_of_case").val('${Status_case}');
						$("#status_of_case").attr('readonly', true);
					});

	function fn_service_category_change() {
		var text6 = $("#service_category option:selected").text();
		if (text6 == "JCO/OR") {
			$("#army_no_id").show();
			$("#per_no_id").hide();

		} else {
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
		$.post('GetPersonnelNoData?' + key + "=" + value, {
			personnel_no : personnel_no
		}, function(j) {
			if (j != "") {
				$("#comm_id").val(j[0][0]);

				$.ajaxSetup({
					async : false
				});
				var comm_id = j[0][0];
				$.post('GetCensusDataApprove?' + key + "=" + value, {
					comm_id : comm_id
				}, function(k) {
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
					}
				});

			}
		});
		$("input#personnel_no").attr('readonly', true);
	}

	$("input#personnel_no").keyup(function() {
		var personel_no = this.value;
		var susNoAuto = $("#personnel_no");
		susNoAuto.autocomplete({
			source : function(request, response) {
				$.ajax({
					type : 'POST',
					url : "getpersonnel_noListApproved?" + key + "=" + value,
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
		datepicketDate('dt_of_comp');
		datepicketDate('dt_of_status');
	});
</script>