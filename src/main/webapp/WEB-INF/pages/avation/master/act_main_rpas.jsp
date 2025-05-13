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
<form:form name="actmainActionForm" id="actmainActionForm"
	action="actMainActionRPAS" method="POST" commandName="aviation_main_masterCmd">
	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<span id="lbladd"></span>
					<h5>AVIATION ALLOTMENT OF RPAS ACT </h5>
					<h6 class="enter_by">
						<span style="font-size: 12px; color: red;">(To be entered by MISO)</span>
					</h6>
				</div>
				<div class="card-body card-block">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>ABC Nomenclature </label>
								</div>
								<div class="col-12 col-md-8">
									<select class="form-control" id="act_slot" name="act_slot">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getAviationGroupRPASNamelist}"
											varStatus="num">
											<option value="${item.code_no_from},${item.code_no_to}"
												name="${item.abc_code},${item.type_of_aircraft}">${item.group_name}</option>
										</c:forEach>
									</select>
									<input type="hidden" id="abc_code" name="abc_code" />

								</div>
							</div>
						</div>
						
						<div class="col-md-6">
	            					<div class="row form-group">
       		                           <div class="col col-md-4">
                  						  <label class=" form-control-label"><strong style="color: red;">* </strong>Type of AVN Assets</label>
                						</div>
	                					<div class="col-12 col-md-8">
           						    		  <input  name="type_of_aircraft" id="type_of_aircraft" maxlength="1" class=" form-control" readonly="readonly">
       					           		</div>
	              					</div>
	            				</div>

						
					</div>
					<div class="col-md-12">
					<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>ACT </label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="act_main_id" name="act_main_id"
										maxlength="4" onkeypress="return isNumber0_9Only(event);"
										class="form-control" autocomplete="off" readonly="readonly">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>ACT Nomenclature</label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="act_nomen" name="act_nomen"
										placeholder="" maxlength="100" class="form-control"
										oninput="this.value = this.value.toUpperCase()"
										autocomplete="off">
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
					<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>SUS No </label>
								</div>
								<div class="col-12 col-md-8">
									<select id="sus_no" name="sus_no"
										class="form-control-sm form-control">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getLine_DteList}"
											varStatus="num">
											<option value="${item.line_dte_sus}">${item.line_dte_name}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						</div>
					

				<div style="display: none;" class="form-control">
					<input type="text" id="abc_code" name="abc_code" placeholder="" class="form-control">
				</div>


				</div>
				<div class="card-footer" align="center" class="form-control">
					<input type="submit" class="btn btn-primary btn-sm" value="Save"
						onclick="return validate();"> <i
						class="action_icons searchButton"></i>
						<!-- <input type="button"
						class="btn btn-info btn-sm" onclick="Search();" value="Search"> -->
					<input type="reset" class="btn btn-success btn-sm" value="Clear">
				</div>

				<div class="card-body">
					<div class="" id="reportDiv">
						<div class="col-md-12">

							<div class="watermarked" data-watermark="" id="divwatermark">
								<span id="ip"></span>
								<table id="getAviationActTbl"
									class="table no-margin table-striped  table-hover  table-bordered report_print"
									style="margin-bottom: 0rem;">
									<thead>
										<tr>
											<th>Ser No</th>
											<th>Act Nomen</th>
											<th>ABC Code</th>
											<th>Type Of Aircraft</th>
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
												<td><b>${num.index+1}</b></td>
												<td><b>${item[0]}</b></td>
												<td><b>${item[1]}</b></td>
												<td><b>${item[2]}</b></td>
												<td><b>${item[3]}</b></td>
												<td><b>${item[5]}</b></td>

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

<c:url value="getSearch_actdtl" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="act_slot1">
	<input type="hidden" name="act_slot1" id="act_slot1" />
	<input type="hidden" name="abc_code1" id="abc_code1" />
	<input type="hidden" name="type_of_aircraft1" id="type_of_aircraft1" />
	<input type="hidden" name="sus_no1" id="sus_no1" />
</form:form>
<script type="text/javascript">
	function Search() {

		if ($("select#sus_no").val() == "0") {
			alert("Please Enter  Sus No");
			$("input#sus_no").focus();
			return false;
		}
		$("#act_slot1").val($('#act_slot').val());
		$("#abc_code1").val($('#abc_code').val());
		$("#sus_no1").val($('#sus_no').val());
		document.getElementById('searchForm').submit();

	}

	function onDiscard() {
		get_discard_condition_details();
	}

	function deleteData(id) {
		$("#id1").val(id);
		document.getElementById('deleteForm').submit();
	}
	function editData(id, act_main_id, sus_no1, abc_code1, type_of_veh1, abc,
			act_nomen) {
		document.getElementById('lbladd').innerHTML = "Update ";
		$("select#act_slot").val(abc);
		$("input#act_main_id").val(act_main_id);
		$("input#act_nomen").val(act_nomen);

		$("select#sus_no").val(sus_no1);
		get_discard_condition_details();
		get_discard_condition_details_oh_mr();
		//document.getElementById('id_org').value=id;
	}

	$(document).ready(function() {

		var q1 = '${act_slot1}';
		if (q1 != "") {
			$("#act_slot").val(q1);
		}
		var q2 = '${sus_no1}';
		if (q2 != "") {
			$("#sus_no").val(q2);
		}
		var q4 = '${act_nomen1}';
		if (q4 != "") {
			$("#act_nomen").val(q4);
		}

		if ('${list.size()}' == 0) {
			$("#reportDiv").hide();

		} else {
			$("#reportDiv").show();

		}

		$('select#act_slot').change(function() {
			var actSlotId = this.value;
			if (actSlotId != 0) {
				$.post("getmaxACTMainID?" + key + "=" + value, {
					actSlotId : actSlotId
				}).done(function(j) {
					var max1 = "";
					if (j.length == 0) {
						alert("This slot is full.");
					} else {
						max1 = ('0000' + j).substr(-4);
					}
					$("#act_main_id").val(max1);
				}).fail(function(xhr, textStatus, errorThrown) {
				});
			}

			var name = $(this).find('option:selected').attr("name");
			var res = name.split(",");
			$("#abc_code").val(res[0]);
			$("#type_of_aircraft").val(res[1]);

		});
	});

	function isNumber0_9Only(evt) {
		var charCode = (evt.which) ? evt.which : evt.keyCode;
		if (!(charCode >= 48 && charCode <= 57) && charCode != 8) {
			return false;
		}

		return true;
	}
	function validate() {

		if ($("#act_slot").val() == "0") {
			alert("Please Select ABC Nomenclature.");
			$("#act_slot").focus();
			return false;
		}
		if ($("#act_nomen").val() == "") {
			alert("Please Enter ACT Nomenclature.");
			$("#act_nomen").focus();
			return false;
		}
		if ($("#act_main_id").val() == "") {
			alert("Please Enter ACT Main.");
			$("#act_main_id").focus();
			return false;
		}
		if ($("#act_main_id").val().length != 4) {
			alert("Please Enter 4 Digit ACT Main.");
			$("#act_main_id").focus();
			return false;
		}
		if ($("select#sus_no").val() == "0") {
			alert("Please Enter  Sus No");
			$("#sus_no").focus();
			return false;
		}

		var main_id = $("#act_main_id").val();
		var slot = $("#act_slot").val();
		var array = slot.split(",");
		var from = array[0];
		var to = array[1];
		if (from > main_id || to < main_id) {
			alert("Please Enter ACT Main Id within " + from + " - " + to);
			$("#act_main_id").focus();
			return false;
		}

		return true;
	}

</script>

