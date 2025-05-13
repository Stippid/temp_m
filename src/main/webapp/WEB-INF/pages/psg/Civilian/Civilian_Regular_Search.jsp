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
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<link href="js/cue/cueWatermark.css" rel="Stylesheet"></link>



<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
  <script src="js/Calender/jquery-2.2.3.min.js"></script>
  <script src="js/Calender/jquery-ui.js"></script>
  <script src="js/Calender/datePicketValidation.js"></script>
   <!-- resizable_col -->
<link rel="stylesheet" href="js/assets/adjestable_Col/jquery.resizableColumns.css">
<script src="js/assets/adjestable_Col/jquery.resizableColumns.js" type="text/javascript"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
<style>

.card-header {
	margin-bottom: 0;
	background-color: #cccccc;
	border-bottom: 1px solid #cccccc;
	padding: 12px;
}
</style>
<div class="animated fadeIn">
	<div class="container" align="center">
		<div class="card">
			<div class="card-header">
				<h5 id="hedar_deo" style="display: none;">Search/Approve/Update Regular Employee</h5>
				<h5 id="hedar_app" style="display: none;">Search/Approve/Update Regular Employee </h5>
				<h6 class="enter_by">
<!-- 					<span style="font-size: 12px; color: red;">(To be entered by -->
<!-- 						MISO)</span> -->
				</h6>
			</div>
			<div class="card-body card-block">
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"><strong
									style="color: red;"> </strong> Unit Sus No </label>
							</div>
							<div class="col-md-8">
								<label id="unit_sus_no_hid" style="display: none"><b>
										${roleSusNo} </b></label> <input type="text" id="unit_sus_no"  maxlength="8" onkeypress="return AvoidSpace(event)"  onkeyup="return specialcharecter(this)"
									name="unit_sus_no" class="form-control autocomplete"
									autocomplete="off" style="display: none">
							</div>
						</div>
					</div>

					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label">Unit Name </label>
							</div>
							<div class="col-md-8">
								<input type="text" id="unit_name" name="unit_name" onkeyup="return specialcharecter(this)"
									class="form-control autocomplete" autocomplete="off"
									maxlength="50" style="display: none"> <label
									id="unit_name_l" style="display: none"><b>${unit_name}</b></label>
							</div>
						</div>
					</div>
				</div>

				<div class="col-md-12">
				
				
						<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> Employee No. </label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="personnel_no" name="personnel_no" class="form-control autocomplete" autocomplete="off" maxlength="9" onkeyup="return specialcharecter(this)" onkeypress="return AvoidSpace(event)"> 
						                </div>
						            </div>
	              				</div>
				
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"><strong
									style="color: red;"> </strong>Name</label>
							</div>
							<div class="col-md-8">
								<input type="text" id="first_name" name="first_name"
									onkeypress="return onlyAlphabets(event);"
									class="form-control autocomplete" autocomplete="off"
									maxlength="50" oninput="this.value = this.value.toUpperCase()">
								<input type="hidden" id="id" name="id"
									class="form-control autocomplete" value="0" autocomplete="off"
									maxlength="50">
							</div>
						</div>
					</div>

				
				</div>

				<div class="col-md-12" >
				
			
				
					<div class="col-md-6" id="status_div">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"><strong
									style="color: red;"> </strong> Status</label>
							</div>
							<div class="col-md-8">
								<select name="status" id="status"
									class="form-control-sm form-control">
									<option value="0">Pending</option>
									<option value="1">Approved / Updated</option>
									<option value="3">Rejected</option>
								</select>
							</div>
						</div>
					</div>
						<div class="col-md-6" >
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"><strong
									style="color: red;"> </strong> Serving Status</label>
							</div>
							<div class="col-md-8">
								<select name="service_status" id="service_status"
									class="form-control-sm form-control"
									onchange="function_status();">
									<option value="1">Serving</option>
									<option value="4">Non Effective</option>
								</select>
							</div>
						</div>
					</div>
				</div>
				
				<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">Created by </label>
						                </div>
						                <div class="col-md-8">
		
						                 
						                 <select name="cr_by" id="cr_by" class="form-control-sm form-control"   >
                                                                                                <option value="">--Select--</option>
                                                                                                <c:forEach var="item" items="${getRoleNameList_dash}" varStatus="num">
                                                                                                <option value="${item.userId}"  name="${item.userName}">${item.userName}</option>
                                                                                                </c:forEach>
                                                                                        </select> 
						                </div>
						            </div>
	              				</div>
	              				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">Created Date </label>
						                </div>
						                <div class="col-md-8">
						               	<input type="text" name="cr_date" id="cr_date" maxlength="10"   onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"  >
											
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

	<div class="container" id="getSearch_civil">
		<div class="">
			<div id="divShow" style="display: block;"></div>
			<div class="watermarked" data-watermark="" id="divwatermark"
				style="display: block;">
				<span id="ip"></span>
				<table id="getSearch_civilTable"
					class="table no-margin table-striped  table-hover  table-bordered report_print_scroll">
					<thead style="color: white; text-align: center;">
						<tr>
							<th style="font-size: 15px">Ser No</th>
							<th style="font-size: 15px">Employee No</th>
							<th style="font-size: 15px">Name</th>
							<th style="font-size: 15px">Date of Birth</th>
							<th style="font-size: 15px">Unit Sus No</th>
							<c:if test="${status == '3'}">
								<th style="text-align: center;">Reject Remarks</th>
							</c:if>
							<th id="th_cancel" style="font-size: 15px; display: none;">
								Cancel Entry</th>
							<th style="font-size: 15px">Action</th>

						</tr>
					</thead>
					<tbody>
						<c:if test="${list.size()==0}">
							<tr>
								<td style="font-size: 15px; text-align: center; color: red;"
									colspan="7">Data Not Available</td>
							</tr>
						</c:if>
						<c:forEach var="item" items="${list}" varStatus="num">
							<tr>
								<td style="font-size: 15px; text-align: center">${num.index+1}</td>
								<td style="font-size: 15px;">${item[1]}</td>
								<td style="font-size: 15px;">${item[2]}</td>
								<td style="font-size: 15px;">${item[3]}</td>
								<td style="font-size: 15px;">${item[4]}</td>
								<c:if test="${status == '3'}">
									<td style="font-size: 15px;">${item[5]}</td>
								</c:if>
								<td id="td_cancel" style="font-size: 15px; display: none;">${item[11]}${item[12]}</td>
								<td style="font-size: 15px; text-align: center;">${item[6]}${item[7]}${item[8]}${item[9]}${item[10]}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>

<c:url value="viewcivilianRegularUrl" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm"
	name="viewForm" modelAttribute="id3">
	<input type="hidden" name="id3" id="id3">
	<input type="hidden" name="unit_sus_no2" id="unit_sus_no2" />
	<input type="hidden" name="unit_name2" id="unit_name2" />
	<input type="hidden" name="first_name2" id="first_name2" />
	<input type="hidden" name="status2" id="status2" />
	
</form:form>

<c:url value="deleteEduUrl_regular" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm">
	<input type="hidden" name="deleteid" id="deleteid" value="0" />

</form:form>
<c:url value="getSearch_regular" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="unit_sus_no1">
	<input type="hidden" name="unit_sus_no1" id="unit_sus_no1" />
	<input type="hidden" name="unit_name1" id="unit_name1" />
	<input type="hidden" name="first_name1" id="first_name1" />
	<input type="hidden" name="personnel_no1" id="personnel_no1" />
	<input type="hidden" name="status1" id="status1" />
	<input type="hidden" name="cr_date1" id="cr_date1"  />
	<input type="hidden" name="cr_by1" id="cr_by1"  />
</form:form>

<c:url value="editcivilianRegularUrl" var="editUrl" />
<form:form action="${editUrl}" method="post" id="updateForm"
	name="updateForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2">
</form:form>

<c:url value="Reject_Civilian" var="rejectUrl" />
<form:form action="${rejectUrl}" method="post" id="rejectForm"
	name="rejectForm" modelAttribute="id4">
	<input type="hidden" name="id4" id="id4" value="0" />
</form:form>

<script>
	$(document).ready(function() {
		 jQuery(function($){ 
		      
		        datepicketDate('cr_date');
		        
		        
			});

		$("#cr_date").val('${cr_date1}');		
		$("#cr_by").val('${cr_by1}');	
		
		function_status($("#service_status").val(1));

		var r = ('${roleAccess}');

		if (r == "Unit") {

			$("#unit_sus_no_hid").show();
			$("#unit_name_l").show();

		} else if (r == "MISO") {

			$("input#unit_sus_no").show();
			$("input#unit_name").show();

		}
		if ('${list.size()}' == "") {
			$("div#getSearch_civil").hide();
		}

		var q5 = '${unit_sus_no}';
		if (q5 != "") {
			$("#unit_sus_no").val(q5);
		}

		var q3 = '${unit_name}';
		if (q3 != "") {
			$("#unit_name").val(q3);
		}

		if ('${first_name}' != "") {
			$("#first_name").val('${first_name}');
		}
		if ('${status}' != "") {
			if ('${status}' == 4) {
				$("#status_div").hide();
				$("select#service_status").val('${status}');
			}
			else {
				$("select#status").val('${status}');
			}
		}

		var q4 = '${emp_no}';
		if (q4 != "") {
			$("#personnel_no").val(q4);
		}
		
		if ('${size}' == 0 && '${size}' != "") {
			$("#divPrint").show();
		}

		colAdj("getSearch_civilTable");

		if ('${roleType}' == "APP") {
			$("#hedar_app").show();
			if ('${status}' == 4) {
				$("td#td_cancel").show();
				$("th#th_cancel").show();
			}
		} else {
			$("#hedar_deo").show();
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
		$("#unit_name1").val($('#unit_name').val());
		$("#first_name1").val($('#first_name').val());
		$("#personnel_no1").val($('#personnel_no').val());
		$("#cr_by1").val($("#cr_by").val()) ;
		$("#cr_date1").val($("#cr_date").val()) ;
	
		if ($('#service_status').val() != 4) {
			$("#status1").val($('#status').val());
			$("#status_div").show();

		} else {
			$("#status1").val($('#service_status').val());
		}
		document.getElementById('searchForm').submit();
	}

	function viewData(id) {

		$("#unit_sus_no2").val($('#unit_sus_no').val());
		$("#unit_name2").val($('#unit_name').val());
		$("#first_name2").val($('#first_name').val());
		$("#status2").val($('#status').val());

		document.getElementById('id3').value = id;
		document.getElementById('viewForm').submit();
	}

	function Reject(id) {
		$("#id4").val(id);
		document.getElementById('rejectForm').submit();
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

	//////////////////////////////////

	$("input#personnel_no").keyup(function() {
		
		
		
		var personnel_no = this.value;
		var susNoAuto = $("input#personnel_no");
		susNoAuto.autocomplete({
			source : function(request, response) {
				$.ajax({
					type : 'POST',
					url : "getemployee_noListApproved?" + key + "=" + value,
					data : {
						personnel_no : personnel_no
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
					alert("Please Enter Approved Employee No");
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
													$("#unit_name").val(
															dec(enc, j[0]));

												}).fail(
												function(xhr, textStatus,
														errorThrown) {
												});
									}
								});
					});

	// unit name
	$("input#unit_name").keypress(function() {
		var unit_name = this.value;
		var susNoAuto = $("#unit_name");
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
					document.getElementById("unit_name").value = "";
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

	function AppViewData(id) {
		document.getElementById('id5').value = id;
		document.getElementById('appForm').submit();
	}

	function AppeditData(id) {
		document.getElementById('id6').value = id;
		document.getElementById('editForm').submit();
	}

	function function_status() {
		var se = $("#service_status").val();
		if (se != 4) {
			$("#status_div").show();
		} else {
			$("#status_div").hide();
		}
	}
	function CancelCivilianData(id) {
		$("#id7").val(id);
		document.getElementById('cancelForm').submit();
	}
	function AppCancelData(id) {
		$("#id8").val(id);
		document.getElementById('AppCancelForm').submit();
	}
	function RejCancelData(id) {
		$("#id9").val(id);
		document.getElementById('RejCancelForm').submit();
	}
	
	
	 
	
</script>

<c:url value="appcivilianRegularUrl" var="appUrl" />
<form:form action="${appUrl}" method="post" id="appForm" name="viewForm"
	modelAttribute="id5">
	<input type="hidden" name="id5" id="id5">
</form:form>


<c:url value="AppEditUrl" var="appeditUrl" />
<form:form action="${appeditUrl}" method="post" id="editForm"
	name="editForm" modelAttribute="id6">
	<input type="hidden" name="id6" id="id6">
</form:form>


<c:url value="CancelUrl" var="cUrl" />
<form:form action="${cUrl}" method="post" id="cancelForm"
	name="cancelForm" modelAttribute="id7">
	<input type="hidden" name="id7" id="id7">
</form:form>


<c:url value="AppCancelUrl" var="appcUrl" />
<form:form action="${appcUrl}" method="post" id="AppCancelForm"
	name="AppCancelForm" modelAttribute="id8">
	<input type="hidden" name="id8" id="id8">
</form:form>


<c:url value="RejCancelUrl" var="rejcUrl" />
<form:form action="${rejcUrl}" method="post" id="RejCancelForm"
	name="RejCancelForm" modelAttribute="id9">
	<input type="hidden" name="id9" id="id9">
</form:form>
