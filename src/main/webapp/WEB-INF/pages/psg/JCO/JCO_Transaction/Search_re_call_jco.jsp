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
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<!-- resizable_col -->
<link rel="stylesheet" href="js/assets/adjestable_Col/jquery.resizableColumns.css">
<script src="js/assets/adjestable_Col/jquery.resizableColumns.js" type="text/javascript"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>

	<div class="animated fadeIn">
		<div class="container-fluid" align="center">
			<div class="card">
				<div class="card-header">
					<h5>Search/Approve ReCall JCO/OR</h5>
					<h6 class="enter_by">
						<span style="font-size: 12px; color: red;">(To be entered by Unit)</span>
					</h6>
				</div>
				
				
				<div class="card-body card-block">
				<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Unit SUS No </label>
						                </div>
						                <div class="col-md-8">
						                  <label id="unit_sus_no_hid" style="display: none" ><b> ${roleSusNo} </b></label>   
						                 <input type="text" id="unit_sus_no" name="unit_sus_no" class="form-control autocomplete" autocomplete="off"  maxlength="8" onkeypress="return AvoidSpace(event)"  onkeyup="return specialcharecter(this)" 	  style="display: none"> 
						               
						                </div>
						            </div>
	              				</div>
	              				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Unit Name </label>
						                </div>
						                <div class="col-md-8">
						                  <input type="text" id="unit_name" name="unit_name" class="form-control autocomplete" autocomplete="off" maxlength="50" style="display: none" onkeyup="return specialcharecter(this)">				
										   <label  id="unit_name_l" style="display: none" ><b>${unit_name}</b></label>  
										</div>
						            </div>
	              				</div>
	              			</div>
					<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> Army No </label>
						                </div>
						                <div class="col-md-8">
						               
						                   <input type="text" id="army_no" name="army_no" value="${army_no1}" class="form-control autocomplete" autocomplete="off" maxlength="11" onkeyup="return specialcharecter(this)" onclick="return AvoidSpace(event)" onkeypress="return /[0-9a-zA-Z]/i.test(event.key)"> 
						                </div>
						            </div>
	              				</div>
	             				
	             				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Status </label>
						                </div>						               
						                <div class="col-md-8">				           
												
											<select name="status" id="statusA" class="form-control"  >
													<option value="0">Pending</option>
												    <option value="1">Approved</option>	
													 <option value="3">Rejected</option>
													<option value="4">View History/Cancel</option>
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
					<a href="search_re_callurlJCO" class="btn btn-success btn-sm">Clear</a>
					<input type="button" class="btn btn-info btn-sm"
						onclick="Search();" value="Search">
				</div>
			</div>
		</div>
	</div>

	
<div class="container-fluid" id="divPrint" style="display: block;">
	<div class="watermarked" data-watermark="" id="divwatermark">
		<span id="ip"></span>
		<table id="getSearch_Letter"
			class="table no-margin table-striped  table-hover  table-bordered report_print_scroll">
			<thead>
				<tr>
					<th style="font-size: 15px; text-align: center">Ser No</th>
					<th style="font-size: 15px; text-align: center">Army No</th>
					<th style="font-size: 15px; text-align: center">Name</th>
					<th style="font-size: 15px; text-align: center">Unit SUS No</th>
					<th style="font-size: 15px; text-align: center">Unit Posted To</th>
					<th style="font-size: 15px; text-align: center">Date of TOS</th>
					<th style="font-size: 15px; text-align: center">Grant From Date</th>
					<th style="font-size: 15px; text-align: center">Authority</th>
					<th style="font-size: 15px; text-align: center">Date of Authority</th>
					<th style="font-size: 15px; text-align: center">Reject Remarks</th>
					
					  <c:if test="${ status1 != 1}">
					<th style="font-size: 15px; text-align: center">ACTION</th>
			    </c:if>
				</tr>
			</thead>
			<tbody>
				<c:if test="${list.size()==0}">
					<tr>
					 <c:if test="${ status1 != 1}">
							 <td style="font-size: 15px; text-align: center; color: red;"
							colspan="11">Data Not Available</td>
							</c:if>
							
							 <c:if test="${ status1 == 1}">
							 <td style="font-size: 15px; text-align: center; color: red;"
							colspan="10">Data Not Available</td>
							 </c:if>
							
					</tr>
				</c:if>
				<c:forEach var="item" items="${list}" varStatus="num">
					<tr>
						<td style="font-size: 15px; text-align: center; width: 5%;">${num.index+1}</td>
						<td style="font-size: 15px;">${item[0]}</td>
						<td style="font-size: 15px;">${item[1]}</td>
						<td style="font-size: 15px;">${item[3]}</td>
						<td style="font-size: 15px;">${item[4]}</td>
						 <td style="font-size: 15px;">${item[5]}</td> 
						<td style="font-size: 15px;">${item[8]}</td>
						<td style="font-size: 15px;">${item[6]}</td>
						<td style="font-size: 15px;">${item[7]}</td>
					    <td style="font-size: 15px;">${item[9]}</td>
				  <c:if test="${ status1 != 1}">
				<td style="font-size: 15px;"> ${item[10]} ${item[11]} ${item[12]}</td>
			</c:if>
						
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>

<c:url value="Search_Re_CallJCO" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="army_no1">
	<input type="hidden" name="army_no1" id="army_no1" value="0" />
	
	<input type="hidden" name="status1" id="status1" value="0" />
	<input type="hidden" name="unit_name1" id="unit_name1"  />
		<input type="hidden" name="unit_sus_no1" id="unit_sus_no1"  />
		<input type="hidden" name="cr_date1" id="cr_date1"  />
	<input type="hidden" name="cr_by1" id="cr_by1"  />

</form:form>


<c:url value="Edit_Re_call_UrlJCO" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="updateForm"
	name="updateForm" modelAttribute="updateid">
	<input type="hidden" name="updateid" id="updateid" value="0" />
	<input type="hidden" name="jco_id_e" id="jco_id_e" value="0" />
	<input type="hidden" name="army_no_e" id="army_no_e" value="0" />
	<input type="hidden" name="army_no6" id="army_no6" />
	<input type="hidden" name="unit_sus_no5" id="unit_sus_no5" value="0" />
	<input type="hidden" name="unit_name5" id="unit_name5" value="0" />
	<input type="hidden" name="statusA5" id="statusA5" value="0" />

</form:form>

<c:url value="Approve_re_call_urlJCO" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="approveForm"
	name="approveForm" modelAttribute="appid">
	<input type="hidden" name="appid" id="appid" value="0" />
	<input type="hidden" name="jco_id_a" id="jco_id_a" value="0" />
</form:form>

<c:url value="Reject_re_call_urlJCO" var="rejectUrl" />
<form:form action="${rejectUrl}" method="post" id="rejectForm"
	name="rejectForm" modelAttribute="reid">
	<input type="hidden" name="reid" id="reid" value="0" />
	<input type="hidden" name="rej_remarks_recall" id="rej_remarks_recall" value="0" />
</form:form>


<!--  //////////////////////Cancel History//////////////////////////// -->

<c:url value="CancelRejectHistory_RecallJCO" var="CancelRejectUrl" />
<form:form action="${CancelRejectUrl}" method="post" id="CancelRejectForm" name="CancelRejectForm" modelAttribute="idcrh">
	<input type="hidden" name="idcrh" id="idcrh" value="0"/> 	 
	<input type="hidden" name="jco_idcrh" id="jco_idcrh" value="0"/>  	
	<input type="hidden" name="army_nocrh" id="army_nocrh" value="0" />	
	<input type="hidden" name="statuscrh" id="statuscrh" value="0" />
	<input type="hidden" name="unit_namecrh" id="unit_namecrh"  />
	<input type="hidden" name="unit_sus_nocrh" id="unit_sus_nocrh"  />
</form:form> 

 <c:url value="ApproveCancelHistory_RecallJCO" var="ApproveCancelUrl" />
<form:form action="${ApproveCancelUrl}" method="post" id="ApproveCancelForm" name="ApproveCancelForm" modelAttribute="idach">
	
	<input type="hidden" name="idach" id="idach" value="0"/> 	
	<input type="hidden" name="jco_idach" id="jco_idach" value="0"/>  	
	<input type="hidden" name="army_noach" id="army_noach" value="0" />
	<input type="hidden" name="statusach" id="statusach" value="0" />
	<input type="hidden" name="unit_nameach" id="unit_nameach"  />
	<input type="hidden" name="unit_sus_noach" id="unit_sus_noach"  />
	
	
</form:form> 

<script>

function Search() {

	 $("#army_no1").val($("#army_no").val());
	
	$("#status1").val($("#statusA").val());
	$("#unit_name1").val($("#unit_name").val()) ;	
	$("#unit_sus_no1").val($("#unit_sus_no").val()) ;
	$("#cr_by1").val($("#cr_by").val()) ;
	$("#cr_date1").val($("#cr_date").val()) ;
	document.getElementById('searchForm').submit();

}


$(function() {
	$("#from_dt").change(function() {
		//var selectedText = $(this).find("option:selected").text();
		var f_month = $(this).val();
		//$("#leave_to").attr("value", f_month);
		$("#to_dt").attr("min", f_month);

	});
});
$(function() {
	$("#to_dt").change(function() {
		//var selectedText = $(this).find("option:selected").text();
		var f_month = $(this).val();
				
		//$("#leave_to").attr("value", f_month);
		$("#from_dt").attr("max", f_month);

	});
});


$(document).ready(function() {

	$("#cr_date").val('${cr_date1}');
	$("#cr_by").val('${cr_by1}');
		jQuery(function($) {
				datepicketDate('cr_date');

		});
		
		var r = ('${roleAccess}');

		if (r == "Unit") {

			$("#unit_sus_no_hid").show();
			$("#unit_name_l").show();

		} else if (r == "MISO") {

			$("input#unit_sus_no").show();
			$("input#unit_name").show();

		}

		if ('${roleSusNo}' != "") {
			$('#roleSusNo').val('${roleSusNo}');
		}
		if ('${name}' != "") {
			$('#name').val('${name}');
		}
		if ('${status1}' != "") {
			$('Select#statusA').val('${status1}');
		}
		var q3 = '${unit_name1}';
		if (q3 != "") {
			$("#unit_name").val(q3);
		}

		var q5 = '${unit_sus_no1}';
		if (q5 != "") {
			$("#unit_sus_no").val(q5);
		}
		colAdj("getSearch_Letter");

	});

	//*************************************MAIN Personel Number Onchange*****************************//

	function editData(id, jco_id) {
		$.ajaxSetup({
			async : false
		});
		if (getArmyNoFromJco(jco_id, 'army_no_e', '')) {
			document.getElementById('jco_id_e').value = jco_id;
			document.getElementById('updateid').value = id;
			$("#army_no6").val($("#army_no").val());
			$("#unit_sus_no5").val($("#unit_sus_no").val());
			$("#unit_name5").val($("#unit_name").val());
			$("#statusA5").val($("#statusA").val());

			document.getElementById('updateForm').submit();
		} else {
			return false;
		}
	}

	function Approve(id, jco_id) {

		document.getElementById('jco_id_a').value = jco_id;
		document.getElementById('appid').value = id;
		document.getElementById('approveForm').submit();
	}

	function Reject(id)

	{
		document.getElementById('reid').value = id;
		$("#rej_remarks_recall").val($("#reject_remarks").val());
		document.getElementById('rejectForm').submit();
	}

	$("input#army_no").keypress(
			function() {
				var personel_no = this.value;
				var susNoAuto = $("#army_no");
				susNoAuto.autocomplete({
					source : function(request, response) {
						$.ajax({
							type : 'POST',
							url : "getpersonnel_noListApproved_JCO?" + key
									+ "=" + value,
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
							document.getElementById("army_no").value = "";
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

	//*************************************END Personel Number Onchange*****************************//
	//******************************Start ReEmployment***************************// 
	/////////////////History cancel//////////////

	function RejectCancelHisData(idcan, jco_idcan) {
		//alert("reject--"+idcan)
		$("#idcrh").val(idcan);
		$("#jco_idcrh").val(jco_idcan);
		$("#army_nochr").val($("#army_no").val());

		$("#statuschr").val($("#statusA").val());
		$("#unit_namechr").val($("#unit_name").val());
		$("#unit_sus_nochr").val($("#unit_sus_no").val());

		document.getElementById('CancelRejectForm').submit();

	}

	function ApprovedCancelHisData(idcan, jco_idcan) {
		$("#idach").val(idcan);
		$("#jco_idach").val(jco_idcan);

		$("#army_noach").val($("#army_no").val());
		$("#statusach").val($("#statusA").val());
		$("#unit_nameach").val($("#unit_name").val());
		$("#unit_sus_noach").val($("#unit_sus_no").val());

		document.getElementById('ApproveCancelForm').submit();

	}
</script>



