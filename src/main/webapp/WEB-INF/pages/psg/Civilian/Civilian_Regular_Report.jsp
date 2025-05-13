<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<!-- <link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script> -->
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>


<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<div class="animated fadeIn">
	<div class="container" align="center">
		<div class="card">
			<div class="card-header">
				<h5>Regular Employee Report</h5>
				<h6 class="enter_by">
					<span style="font-size: 12px; color: red;"></span>
				</h6>
			</div>
			<div class="card-body card-block">
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"><strong
									style="color: red;">* </strong> Unit Sus No </label>
							</div>
							<div class="col-md-8">
								<input type="text" id="unit_sus_no" name="unit_sus_no"
									class="form-control autocomplete" autocomplete="off"
									maxlength="50">

							</div>
						</div>
					</div>

					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"> Unit Posted To </label>
							</div>
							<div class="col-md-8">
								<input type="text" id="unit_posted_to" name="sus_no"
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
									style="color: red;">* </strong> First Name</label>
							</div>
							<div class="col-md-8">
								<input type="text" id="first_name" name="first_name"
									class="form-control autocomplete" autocomplete="off"
									maxlength="50"> <input type="hidden" id="id" name="id"
									class="form-control autocomplete" value="0" autocomplete="off"
									maxlength="50">

							</div>
						</div>
					</div>


				</div>
			</div>
			<div class="card-footer" align="center" class="form-control">
				<a href="Search_civilian_regular" class="btn btn-success btn-sm">Clear</a>

				<i class="action_icons searchButton"></i><input type="button"
					class="btn btn-info btn-sm" onclick="Search1();" value="Search">
				
			</div>
		</div>
	</div>


	<div class="container" id="village_search">
		<div class="">
			<div id="divShow" style="display: block;"></div>
			<div class="watermarked" data-watermark="" id="divwatermark"
				style="display: block;">
				<span id="ip"></span>
				<table style="width: 100%"
					class="table no-margin table-striped  table-hover  table-bordered report_print"
					width="100%">
					<thead style="color: white; text-align: center;">
						<tr>
							<th rowspan="2" style="width:100px; font-size: 15px; text-align: center;">Category</th>
							<th rowspan="2" style="width:170px; font-size: 15px; text-align: center;">Group</th>
							<th rowspan="2" style="font-size: 15px">Auth (Industrial)</th>
							<th colspan="3" style="font-size: 15px">Held (Industrial)</th>
							<th rowspan="2" style="font-size: 15px">Auth (Non Industrial)</th>
							<th colspan="3" style="font-size: 15px">Held (Non Industrial)</th>
						</tr>
						<tr>
							<th style="font-size: 15px">Male</th>
							<th style="font-size: 15px">Female</th>
							<th style="font-size: 15px">Total</th>
							<th style="font-size: 15px">Male</th>
							<th style="font-size: 15px">Female</th>
							<th style="font-size: 15px">Total</th>

						</tr>

					</thead>

					<tr >
						<td rowspan="2">Gazetted</td>
						<td>GROUP A</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
					</tr>
					<tr>

						<td>GROUP B</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
					</tr>
					<tr>
						<td rowspan="12"style="vertical-align: middle;">Non Gazetted</td>
						<td>GROUP B <br> (MINISTERIAL)</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>

					</tr>
					<tr>
						<td>GROUP C <br> (MINISTERIAL)</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>

					</tr>
					<tr>
						<td>GROUP B <br> (EXECUTIVE)</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>

					</tr>
					<tr>
						<td>GROUP C <br> (EXECUTIVE)</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>

					</tr>
					<tr>
						<td>GROUP B <br> (TECHNICAL)</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>

					</tr>
					<tr>
						<td>GROUP C <br> (TECHNICAL)</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>

					</tr>
					<tr>
						<td>GROUP C <br> (OFFICE WORKER)</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>

					</tr>
					<tr>
						<td>GROUP C <br> (SEMI SKILLED)</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>

					</tr>
					<tr>
						<td>OTHERS <br> (SPECIFY IN NOTES)</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>

					</tr>
					<!-- <tr>
						<td>Doe</td>
						<td>80</td>
						<td>Jill</td>
						<td>Smith</td>
						<td>50</td>
						<td>Jill</td>
						<td>Smith</td>
						<td>50</td>
						<td>50</td>

					</tr>
					<tr>
						<td>Doe</td>
						<td>80</td>
						<td>Jill</td>
						<td>Smith</td>
						<td>50</td>
						<td>Jill</td>
						<td>Smith</td>
						<td>50</td>
						<td>50</td>

					</tr>
					<tr>
						<td>Doe</td>
						<td>80</td>
						<td>Jill</td>
						<td>Smith</td>
						<td>50</td>
						<td>Jill</td>
						<td>Smith</td>
						<td>50</td>
						<td>50</td>

					</tr> -->


				</table>
			</div>
		</div>
	</div>


</div>


<c:url value="deleteEduUrl_regular" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm">
	<input type="hidden" name="deleteid" id="deleteid" value="0" />

</form:form>
<c:url value="getSearch_regular" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="country_id1">
	<input type="hidden" name="unit_sus_no1" id="unit_sus_no1" />
	<input type="hidden" name="unit_posted_to1" id="unit_posted_to1" />
	<input type="hidden" name="first_name1" id="first_name1" />
</form:form>

<c:url value="editcivilianRegularUrl" var="editUrl" />
<form:form action="${editUrl}" method="post" id="updateForm"
	name="updateForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2">
</form:form>
<script>
	$(document).ready(function() {

		if ('${unit_sus_no}' != "") {
			$("#unit_sus_no").val('${unit_sus_no}');
		}
		if ('${unit_posted_to}' != "") {
			$("#unit_posted_to").val('${unit_posted_to}');
		}
		if ('${first_name}' != "") {
			$("#first_name").val('${first_name}');
		}

	});
	function deleteData(id) {

		document.getElementById('deleteid').value = id;
		document.getElementById('deleteForm').submit();
	}

	function editData(id) {

		document.getElementById('id2').value = id;
		document.getElementById('updateForm').submit();
	}
	function Search1() {

		$("#unit_sus_no1").val($('#unit_sus_no').val());
		$("#unit_posted_to1").val($('#unit_posted_to').val());
		$("#first_name1").val($('#first_name').val());
		document.getElementById('searchForm').submit();
	}
</script>
<script>
	function validate() {

		if ($("input#unit_sus_no").val() == "") {
			alert("Please Enter Unit Sus No");
			$("input#unit_sus_no").focus();
			return false;
		}
	}
	// Unit SUS No

	$("#unit_sus_no")
			.keyup(
					function() {
						var sus_no = this.value;
						var susNoAuto = $("#unit_sus_no");

						susNoAuto
								.autocomplete({
									source : function(request, response) {
										$
												.ajax({
													type : 'POST',
													url : "getTargetSUSNoList?"
															+ key + "=" + value,
													data : {
														sus_no : sus_no
													},
													success : function(data) {
														var susval = [];
														var length = data.length - 1;
														var enc = data[length]
																.substring(0,
																		16);
														for (var i = 0; i < data.length; i++) {
															susval.push(dec(
																	enc,
																	data[i]));
														}
														var dataCountry1 = susval
																.join("|");
														var myResponse = [];
														var autoTextVal = susNoAuto
																.val();
														$
																.each(
																		dataCountry1
																				.toString()
																				.split(
																						"|"),
																		function(
																				i,
																				e) {
																			var newE = e
																					.substring(
																							0,
																							autoTextVal.length);
																			if (e
																					.toLowerCase()
																					.includes(
																							autoTextVal
																									.toLowerCase())) {
																				myResponse
																						.push(e);
																			}
																		});
														response(myResponse);
													}
												});
									},
									minLength : 1,
									autoFill : true,
									change : function(event, ui) {
										if (ui.item) {
											return true;
										} else {
											alert("Please Enter Approved Unit SUS No.");
											document
													.getElementById("unit_name").value = "";
											susNoAuto.val("");
											susNoAuto.focus();
											return false;
										}
									},
									select : function(event, ui) {
										var sus_no = ui.item.value;
										$.post(
												"getTargetUnitNameList?" + key
														+ "=" + value, {
													sus_no : sus_no
												}, function(j) {

												}).done(
												function(j) {
													var length = j.length - 1;
													var enc = j[length]
															.substring(0, 16);
													$("#unit_posted_to").val(
															dec(enc, j[0]));

												}).fail(
												function(xhr, textStatus,
														errorThrown) {
												});
									}
								});
					});

	// unit name
	$("input#unit_posted_to").keypress(function() {
		var unit_name = this.value;
		var susNoAuto = $("#unit_posted_to");
		susNoAuto.autocomplete({
			source : function(request, response) {
				$.ajax({
					type : 'POST',
					url : "getTargetUnitsNameActiveList?" + key + "=" + value,
					data : {
						unit_name : unit_name
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
					alert("Please Enter Approved Unit Name.");
					document.getElementById("unit_posted_to").value = "";
					susNoAuto.val("");
					susNoAuto.focus();
					return false;
				}
			},
			select : function(event, ui) {
				var unit_name = ui.item.value;

				$.post("getTargetSUSFromUNITNAME?" + key + "=" + value, {
					target_unit_name : unit_name
				}, function(data) {
				}).done(function(data) {
					var length = data.length - 1;
					var enc = data[length].substring(0, 16);
					$("#unit_sus_no").val(dec(enc, data[0]));
				}).fail(function(xhr, textStatus, errorThrown) {
				});
			}
		});
	});
</script>
