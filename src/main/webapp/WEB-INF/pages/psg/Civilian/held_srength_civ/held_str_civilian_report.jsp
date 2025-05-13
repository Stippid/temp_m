<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="datatables"
	uri="http://github.com/dandelion/datatables"%>
<%@ taglib prefix="dandelion" uri="http://github.com/dandelion"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<script type="text/javascript" src="js/miso/miso_js/jquery-1.12.3.js"></script>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script src="js/amchart4/core.js"></script>
<script src="js/amchart4/charts.js"></script>
<script src="js/amchart4/animated.js"></script>
<!-- <link rel="stylesheet" href="js/miso/tmsDashboard/tmsDashboardCSS.css"> -->


<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<!--  -->
<script src="js/Calender/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link rel="stylesheet" href="js/InfiniteScroll/css/datatables.min.css">
<script type="text/javascript"
	src="js/InfiniteScroll/js/datatables.min.js"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/jquery.mockjax.min.js"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/datatables.mockjax.js"></script>

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>


<form:form name="Report_Serving" id="Report_Serving"
	action="Report_Serving_Action" method="post" class="form-horizontal"
	commandName="Report_Serving_CMD">
	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<h5 style="text-transform: capitalize">CIVILIAN STR RETURNS</h5>
					<h6 class="enter_by"></h6>
				</div>
				<div class="card-body card-block">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> SUS No: </label>
								</div>
								<div class="col-md-8">
									<label id="unit_sus_no_hid"
										style="display: none; text-transform: uppercase;"><b>${roleSusNo}</b></label>
									<input type="text" id="unit_sus_no" name="unit_sus_no"
										class="form-control autocomplete" autocomplete="off"
										maxlength="8" onkeyup="return specialcharecter(this)"
										onkeypress="return AvoidSpace(event)" style="display: none">
									<input type="hidden" id="personnel_no" name="personnel_no"
										class="form-control autocomplete" autocomplete="off">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Unit Name: </label>
								</div>
								<div class="col-md-8">
									<input type="text" id="unit_name" name="unit_name"
										class="form-control autocomplete" autocomplete="off"
										maxlength="50" onkeyup="return specialcharecter(this)"
										style="display: none"> <label id="unit_name_l"
										style="display: none"><b>${unit_name}</b></label>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Month </label>
								</div>
								<div class="col-md-8">
									<select id="month" name="month" class="required form-control">
										<option value="0">--Select--</option>
										<option value="1">January</option>
										<option value="2">February</option>
										<option value="3">March</option>
										<option value="4">April</option>
										<option value="5">May</option>
										<option value="6">June</option>
										<option value="7">July</option>
										<option value="8">August</option>
										<option value="9">September</option>
										<option value="10">October</option>
										<option value="11">November</option>
										<option value="12">December</option>
									</select>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Year </label>
								</div>
								<div class="col-md-8">
									<input type="text" id="year" name="year"
										class="form-control autocomplete" maxlength="4"
										onkeypress="return isNumber(event)"
										onclick="return AvoidSpace(event)" autocomplete="off"
										onkeyup="return specialcharecter(this)">
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Present Return No </label>
								</div>
								<div class="col-md-8">
									<input type="text" id="present_return_no"
										name="present_return_no" class="form-control autocomplete"
										autocomplete="off" maxlength="10"
										onkeyup="return specialcharecter(this)"
										onkeypress="return AvoidSpace(event)">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong> Date of Present Return </label>
								</div>
								<div class="col-md-8">
									<input type="text" name="present_return_dt"
										id="present_return_dt" maxlength="10"
										onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control"
										style="width: 85%; display: inline;"
										onfocus="this.style.color='#000000'"
										onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);"
										onkeyup="clickclear(this, 'DD/MM/YYYY')"
										onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true"
										autocomplete="off" style="color: rgb(0, 0, 0);">
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Last Return No </label>
								</div>
								<div class="col-md-8">
									<input type="text" id="last_return_no" name="last_return_no"
										class="form-control autocomplete" autocomplete="off"
										maxlength="15" onkeyup="return specialcharecter(this)"
										onkeypress="return AvoidSpace(event)">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong> Date of Last Return </label>
								</div>
								<div class="col-md-8">
									<input type="text" name="last_return_dt" id="last_return_dt"
										maxlength="10" onclick="clickclear(this, 'DD/MM/YYYY')"
										class="form-control" style="width: 85%; display: inline;"
										onfocus="this.style.color='#000000'"
										onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);"
										onkeyup="clickclear(this, 'DD/MM/YYYY')"
										onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true"
										autocomplete="off" style="color: rgb(0, 0, 0);">
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Civilian Status </label>
								</div>
								<div class="col-md-8">
									<select id="civilian_status" name="civilian_status"
										onchange="updateColumnVisibility()"
										class="required form-control">


										<option value="R" selected>Regular</option>
										<option value="NR">NON_Regular</option>

									</select>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Command </label>
								</div>
								<div class="col-md-8">
									<label><b>${unit_name}</b></label>
								</div>
							</div>
						</div>

					</div>


				</div>
				<div class="card-footer" align="center" class="form-control">
					<a href="held_str_civ_Url" class="btn btn-success btn-sm">Clear</a>
					<!-- <input type="button" class="btn btn-info btn-sm" onclick="Search();" value="Search">  -->
					<i class="fa fa-search"></i><input type="button"
						class="btn btn-primary btn-sm" id="btn-reload" value="Search" />
				</div>

			</div>
		</div>

	</div>

	<div class="col-md-12" id="getSearch_held" style="display: block;">
		<div class="card-header">
			<h5>Nominal Roll</h5>

		</div>
	</div>



	<div class="datatablediv">
		<div class="col-md-12" id="getSearch_held_a" style="display: block;">
			<div class="watermarked" data-watermark="" id="divwatermark">
				<span id="ip"></span>
				<table id="getheldstr_a"
					class="table no-margin table-striped  table-hover  table-bordered ">
					<thead>
						<tr>
							<th data-column="ser_no" style="text-align: center;">Ser No</th>
							<th data-column="emp_no" style="text-align: center;">Employee
								No</th>
							<th data-column="emp_name" style="text-align: center;">Employee
								Name</th>
							<th data-column="designation" style="text-align: center;">Designation</th>
							<th data-column="dob" style="text-align: center;">Date of
								Birth</th>
							<th data-column="gazeeted" style="text-align: center;">Gazeeted/Non
								Gazzeted</th>
							<th data-column="group" style="text-align: center;">Group</th>
							<th data-column="industrial" style="text-align: center;">Non
								Industrial/Industrial</th>
							<th data-column="join_date" style="text-align: center;">Date
								of Joining in Govt Service</th>
							<th data-column="tos" style="text-align: center;">Date of
								TOS</th>
							<th data-column="designation_date" style="text-align: center;">Date
								of Designation</th>
							<th data-column="gender" style="text-align: center;">Gender</th>
						</tr>
					</thead>

				</table>
			</div>
		</div>

		<div id="regular_civ_div">

			<div class="col-md-12" id="getSearch_held_b" style="display: block;">
				<div class="card-header">
					<h5>AUTH STR</h5>
				</div>
			</div>



			<div class="col-md-12" id="getSearch_Letter" style="display: block;">
				<div class="watermarked" data-watermark="" id="divwatermark">
					<span id="ip"></span>
					<table id="getauthServingTable"
						class="table no-margin table-striped  table-hover  table-bordered ">
						<thead>
							<tr>
								<th colspan="2" style="text-align: center;" id="parent_arm">
									Gazetted</th>
								<th colspan="4" style="text-align: center;" id="parent_arm">
									NON GAZETTED</th>
								<th rowspan="3" style="text-align: center;" id="med_cat">
									TOTAL</th>
							</tr>
							<tr>
								<th colspan="2" style="text-align: center;"
									id="date_of_seniority"></th>
								<th colspan="2" style="text-align: center;"
									id="date_of_seniority">NON INDUSTRIAL</th>
								<th colspan="2" style="text-align: center;"
									id="date_of_appointment">INDUSTRIAL</th>

							</tr>

							<tr>
								<th style="text-align: center;">GP 'A'</th>
								<th style="text-align: center;">GP 'B'</th>
								<th style="text-align: center;">GP 'B'</th>
								<th style="text-align: center;">GP 'C'</th>
								<th style="text-align: center;">GP 'B'</th>
								<th style="text-align: center;">GP 'C'</th>

							</tr>
						</thead>
					</table>
				</div>
			</div>



			<div class="col-md-12" id="getSearch_held_c" style="display: block;">
				<div class="card-header">
					<h5>HELD STR</h5>
				</div>
			</div>


			<div class="col-md-12" id="getSearch_heldstr_c"
				style="display: block;">
				<div class="watermarked" data-watermark="" id="divwatermark">
					<span id="ip"></span>
					<table id="getheldstr_c"
						class="table no-margin table-striped  table-hover  table-bordered ">
						<thead>
							<tr>
								<th colspan="2" style="text-align: center;" id="parent_arm">
									Gazetted</th>
								<th colspan="4" style="text-align: center;" id="parent_arm">
									NON GAZETTED</th>
								<th rowspan="3" style="text-align: center;" id="med_cat">
									TOTAL</th>
							</tr>
							<tr>
								<th colspan="2" style="text-align: center;"
									id="date_of_seniority"></th>
								<th colspan="2" style="text-align: center;"
									id="date_of_seniority">NON INDUSTRIAL</th>
								<th colspan="2" style="text-align: center;"
									id="date_of_appointment">INDUSTRIAL</th>

							</tr>

							<tr>
								<th style="text-align: center;">GP 'A'</th>
								<th style="text-align: center;">GP 'B'</th>
								<th style="text-align: center;">GP 'B'</th>
								<th style="text-align: center;">GP 'C'</th>
								<th style="text-align: center;">GP 'B'</th>
								<th style="text-align: center;">GP 'C'</th>

							</tr>
						</thead>

					</table>
				</div>
			</div>


			<div class="col-md-12" id="getSearch_Letter" style="display: block;">
				<div class="card-header">
					<h5>SUMMARY</h5>
				</div>
			</div>
			<div class="col-md-12" id="getSearch_Letter" style="display: block;">
				<div class="watermarked" data-watermark="" id="divwatermark">
					<span id="ip"></span>
					<table id="getSearch_Letter"
						class="table no-margin table-striped  table-hover  table-bordered ">
						<thead>
							<tr>
								<th style="text-align: center;">Auth Strength</th>
								<th style="text-align: center;">Holding Strength</th>
								<th style="text-align: center;">Surplus</th>
								<th style="text-align: center;">Deficiency</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td style="font-size: 15px; text-align: center;" id="totalAuth">${totalAuth}</td>
								<td style="font-size: 15px; text-align: center;" id="totalHeld">${totalHeld}</td>
								<td style="font-size: 15px; text-align: center;" id="sur">${sur}</td>
								<td style="font-size: 15px; text-align: center;" id="defi">${defi}</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>

		<div id="non_regular_civ_div" style="display: none;">
			<div class="col-md-12" id="getSearch_held" style="display: block;">
				<div class="card-header">
					<h5>Total Non-Regular Civilian</h5>

				</div>
			</div>



			<div class="datatablediv">
				<div class="col-md-12" id="getSearch_non_regular_civ"
					style="display: block;">
					<div class="watermarked" data-watermark="" id="divwatermark">
						<span id="ip"></span>
						<table id="get_total_non_regular"
							class="table no-margin table-striped  table-hover  table-bordered ">
							<thead>
								<tr>
									<th style="text-align: center;">Industrial</th>
									<th style="text-align: center;">Non-Industrial</th>
									<th style="text-align: center;">Total</th>

								</tr>
							</thead>

						</table>
					</div>
				</div>
			</div>



		</div>
	</div>

	<div class="col-md-12" id="obr">
		<div class="col-md-6">
			<div class="row form-group">
				<div class="col-md-4">
					<label class=" form-control-label"> Please Mention Here,If
						Any Observation </label>
				</div>
				<div class="col-md-8">
					<textarea id="observation" name="observation"
						class="form-control autocomplete" autocomplete="off"></textarea>
				</div>
			</div>
		</div>

	</div>

	<div class="card-footer" align="center" class="form-control"
		id="Savebtn">
		<c:if test="${roleType == 'DEO'}">
			<input type="button" class="btn btn-primary btn-sm"
				value="Submit For Approval" id="submitbtn"
				onclick="Report_Save_fn();">
		</c:if>
	</div>
</form:form>
<c:url value="GetSearch_Report_Serving3009" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="unit_sus_no2">

	<input type="hidden" name="unit_sus_no2" id="unit_sus_no2" value="0">

</form:form>
<script>
	jQuery(function($) { //on document.ready  

		datepicketDate('last_return_dt');
		datepicketDate('present_return_dt');

	});
	$(document).ready(function() {

		 
		$("div#Savebtn").hide();
		var d = new Date();
		var month = d.getMonth() + 1;
		$("#month").val(month);

		var d = new Date();
		var year = d.getFullYear();
		$("#year").val(year);

		//$("#WaitLoader").show();
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

		if ('${unit_sus_no2}' != "") {
			$("#unit_sus_no").val('${unit_sus_no2}');

			var sus_no = '${unit_sus_no2}';
			$.post("getTargetUnitNameList?" + key + "=" + value, {
				sus_no : sus_no
			}, function(j) {
			}).done(function(j) {
				var length = j.length - 1;
				var enc = j[length].substring(0, 16);
				$("#unit_name").val(dec(enc, j[0]));
			}).fail(function(xhr, textStatus, errorThrown) {
			});

			$("#unit_sus_no").attr("disabled", true);
			$("#unit_name").attr("disabled", true);
		}

		if ('${size}' != "" || '${size}' != 0) {
			$("div#getSearch_Letter").show();
			$("div#Note").show();

			var t1 = new Date();
			var Newmonth1 = t1.getMonth() + 1;
			var Newyear1 = t1.getFullYear();
			var mon1 = $("#month").val();
			var yr1 = $("#year").val();

			/*  
			if(mon1 == Newmonth1 && yr1 == Newyear1){
				 $("div#Savebtn").show();		
			}else{
				$("div#Savebtn").hide();	
			}  */
		} else if ('${roleType}' == "DEO") {
			$("div#getSearch_Letter").show();
		} else {
			$("div#getSearch_Letter").hide();
			$("div#Savebtn").hide();
			$("div#obr").hide();
		}

		var selectedValue = $('#civilian_status').val();
		var table, table2, table3, table4;

		if (selectedValue === 'R') {

			mockjax1('getheldstr_a');
			table = dataTable('getheldstr_a');

			mockjax1('getauthServingTable');
			table2 = dataTable('getauthServingTable');

			mockjax1('getheldstr_c');
			table3 = dataTable('getheldstr_c');
		} else {
			mockjax1('getheldstr_a');
			table = dataTable('getheldstr_a');

			mockjax1('get_total_non_regular');
			table2 = dataTable('get_total_non_regular');
		}

		$('#civilian_status').on('change', function() {
		    selectedValue = $(this).val();

		    if (selectedValue === 'R') {
		        mockjax1('getauthServingTable');
		        if (!table2) {
		            table2 = dataTable('getauthServingTable');
		        } else {
		            table2.ajax.url('getauthServingTable').load(); // Update the table data
		        }

		        mockjax1('getheldstr_c');
		        if (!table3) {
		            table3 = dataTable('getheldstr_c');
		        } else {
		            table3.ajax.url('getheldstr_c').load(); // Update the table data
		        }
		        
		        // Destroy any other tables as needed
		        if (table4) {
		            table4.destroy();
		            table4 = null;
		        }
		    } else if (selectedValue === 'NR') {
		        mockjax1('get_total_non_regular');
		        if (!table4) {
		            table4 = dataTable('get_total_non_regular');
		        } else {
		            table4.ajax.url('get_total_non_regular').load(); // Update the table data
		        }
		        
		        // Destroy any other tables as needed
		        if (table2) {
		            table2.destroy();
		            table2 = null;
		        }
		        if (table3) {
		            table3.destroy();
		            table3 = null;
		        }
		    }
		});


		$('#btn-reload').on('click', function() {
			table.ajax.reload();

			
			
			
			
			if (table2) {
				table2.ajax.reload();
			}

			if (table3) {
				table3.ajax.reload();
			}

			if (table4) {
				table4.ajax.reload();
			}
			
			
			
			var civilianStatus = document.getElementById("civilian_status").value;
			if(civilianStatus=='R' )
				{
				 table.column(5).visible(true);
				 table.column(6).visible(true);
				 table.column(9).visible(true);
				 table.column(10).visible(true);
				}
			else{
				 table.column(5).visible(false);
				 table.column(6).visible(false);
				 table.column(9).visible(false);
				 table.column(10).visible(false);
			}
			$("div#Savebtn").show();
		});

	});
</script>



<script type="text/javascript">
	////////////////////data table////////////

	function data(tableName) {
		jsondata = [];
		var table = $('#' + tableName).DataTable();
		var info = table.page.info();
		var currentPage = info.page;
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = order[0][0] + 1;
		var orderType = order[0][1];
		var month = $('#month').val();
		var year = $('#year').val();
		var unit_name = $("#unit_name").val();
		var unit_sus_no = $("#unit_sus_no").val();
		var civilian_status = $("#civilian_status").val();

		 

		if (tableName == "getheldstr_a") {
			$.post("Getsearch_nominrollcount?" + key + "=" + value, {
				Search : Search,
				unit_name : unit_name,
				unit_sus_no : unit_sus_no,
				month : month,
				year : year,
				civilian_status : civilian_status
			}, function(j) {
				FilteredRecords = j;
			});
			$.post("Getsearch_search_nominroll?" + key + "=" + value, {
				startPage : startPage,
				pageLength : pageLength,
				Search : Search,
				orderColunm : orderColunm,
				orderType : orderType,
				unit_name : unit_name,
				unit_sus_no : unit_sus_no,
				month : month,
				year : year,
				civilian_status : civilian_status
			}, function(j) {
				
			 	if (civilian_status === 'R') { 

					for (var i = 0; i < j.length; i++) {
						var sr = i + 1;
						jsondata.push([ sr, j[i].employee_no, j[i].emp_name,
								j[i].description, j[i].dob,
								j[i].classification_services, j[i].civ_group,
								j[i].civ_type, j[i].joining_date_gov_service,
								j[i].date_of_tos, j[i].designation_date,
								j[i].gender_name ]);
				 table.column(5).visible(true);
				 table.column(6).visible(true);
						 table.column(9).visible(true);
						 table.column(10).visible(true);
					}
					
				 } else {
					for (var i = 0; i < j.length; i++) {
						var sr = i + 1;
						jsondata
								.push([ sr, j[i].employee_no, j[i].emp_name,
										j[i].description, j[i].dob," "," ",
										j[i].civ_type,
										j[i].joining_date_gov_service," "," ",
										j[i].gender_name ]);
 						   table.column(5).visible(false);
						   table.column(6).visible(false);
							 table.column(9).visible(false);
							 table.column(10).visible(false);
					}
				 
				} 
			});

		}

		else if (tableName == "getauthServingTable") {

			$.post("Getsearch_authcount?" + key + "=" + value, {
				Search : Search,
				unit_name : unit_name,
				unit_sus_no : unit_sus_no,
				month : month,
				year : year,
				civilian_status : civilian_status
			}, function(j) {
				FilteredRecords = j;
			});
			$.post("Getsearch_search_auth?" + key + "=" + value, {
				startPage : startPage,
				pageLength : pageLength,
				Search : Search,
				orderColunm : orderColunm,
				orderType : orderType,
				unit_name : unit_name,
				unit_sus_no : unit_sus_no,
				month : month,
				year : year,
				civilian_status : civilian_status
			}, function(j) {
				var civ_auth = 0;

				var officer = 0;
				var jco = 0;
				var a_gazetted = 0;
				var b_gazetted = 0;
				var b_non_gazetted_non = 0;
				var c_non_gazetted_non = 0;
				var b_non_gazetted = 0;
				var c_non_gazetted = 0;

				for (var i = 0; i < j.length; i++) {
					var sr = i + 1;
					// alert(j[i].lf + "------ ")
					civ_auth = civ_auth + j[i].total;
					jsondata.push([ j[i].group_a_gaz, j[i].group_b_gaz,
							j[i].group_b_non_gaz_non_ind,
							j[i].group_c_non_gaz_non_ind,
							j[i].group_b_non_gaz_ind, j[i].group_c_non_gaz_ind,
							j[i].total ]);

					document.getElementById("totalAuth").innerHTML = civ_auth;

				}
			});
		}

		else if (tableName == "getheldstr_c") {

			$.post("Getsearch_heldstrcivcount?" + key + "=" + value, {
				Search : Search,
				unit_name : unit_name,
				unit_sus_no : unit_sus_no,
				month : month,
				year : year,
				civilian_status : civilian_status
			}, function(j) {
				FilteredRecords = j;
			});
			$.post("Getsearch_heldstrciv?" + key + "=" + value, {
				startPage : startPage,
				pageLength : pageLength,
				Search : Search,
				orderColunm : orderColunm,
				orderType : orderType,
				unit_name : unit_name,
				unit_sus_no : unit_sus_no,
				month : month,
				year : year,
				civilian_status : civilian_status
			}, function(j) {
				var civ_held = 0;
				for (var i = 0; i < j.length; i++) {
					var sr = i + 1;
					// alert(j[i].lf + "------ ")

					civ_held = civ_held + j[i].total;
					jsondata.push([ j[i].groupa_gaz, j[i].groupb_gaz,
							j[i].groupb_nongaz_nonin, j[i].groupc_nongaz_nonin,
							j[i].groupb_nongaz_in, j[i].groupc_nongaz_in,
							j[i].total ]);

					document.getElementById("totalHeld").innerHTML = civ_held;
				}
			});
		} else if (tableName == "get_total_non_regular") {

			/* 	$.post("Get_total_nonregular_civCount?" + key + "=" + value, {
					startPage : startPage,
					pageLength : pageLength,
					Search : Search,
					orderColunm : orderColunm,
					orderType : orderType,
					unit_name : unit_name,
					unit_sus_no : unit_sus_no,
					month : month,
					year : year,
					civilian_status : civilian_status
				}, function(j) {
					FilteredRecords = j;
				}); */

			$.post("Get_total_nonregular_civ?" + key + "=" + value, {
				startPage : startPage,
				pageLength : pageLength,
				Search : Search,
				orderColunm : orderColunm,
				orderType : orderType,
				unit_name : unit_name,
				unit_sus_no : unit_sus_no,
				month : month,
				year : year,
				civilian_status : civilian_status
			}, function(j) {
				var industrial = 0;
				var non_industrial = 0;
				var total = 0;

				for (var i = 0; i < j.length; i++) {
					var sr = i + 1;
					jsondata.push([ j[i].industrial, j[i].non_industrial,
							j[i].total ]);

				}
			});
		}

		var h_civ = document.getElementById("totalHeld").innerHTML;
		var a_civ = document.getElementById("totalAuth").innerHTML;
		//alert(h_civ);

		var civ_sur = h_civ - a_civ;
		var civdefi = 0;
		if (civ_sur >= 0) {
			civdefi = civ_sur;
			civ_sur = 0;
		} else {
			civ_sur = civ_sur;
			civdefi = 0;
		}
		document.getElementById("defi").innerHTML = civ_sur;
		document.getElementById("sur").innerHTML = civdefi;

	}

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
	/*  function Search(){	
	
	 //	$("#WaitLoader").show();
	
	 var t = new Date();
	
	
	 var r =('${roleAccess}');	
	 if(r == "MISO"){
	 if($('#unit_sus_no').val().trim() == "") {
	 alert("Please Enter Unit Sus No");
	 $('#unit_sus_no').focus();
	 return false;
	 }
	 }
	
	 $("#unit_sus_no2").val($("#unit_sus_no").val()) ;
	
	
	 //	document.getElementById('searchForm').submit();
	 table.ajax.reload();
	 table2.ajax.reload();
	 table3.ajax.reload();
	 table4.ajax.reload();
	 table5.ajax.reload();
	 table6.ajax.reload();
	 } */

	function Report_Save_fn() {

		if ($("select#month").val() == "0") {
			alert("Please Enter month ");
			$("select#month").focus();
			return false;
		}
		if ($("input#year").val() == "") {
			alert("Please Enter year ");
			$("input#year").focus();
			return false;
		}
		if ($("input#present_return_no").val() == "") {
			alert("Please Enter present return no ");
			$("input#present_return_no").focus();
			return false;
		}
		if ($("input#present_return_dt").val() == ""
				|| $("input#present_return_dt").val() == "DD/MM/YYYY") {
			alert("Please Enter present return dt ");
			$("input#gmpresent_return_dtail").focus();
			return false;
		}
		/* 	if ($("textarea#observation").val() == "") {
				alert("Please Enter Observation");
				$("textarea#observation").focus();
				return false;
			} */

		$('#submitbtn').prop('disabled', true)
		var month = $('#month').val();
		var year = $('#year').val();
		var present_return_no = $("#present_return_no").val();
		var present_return_dt = $("#present_return_dt").val();
		var last_return_no = $("#last_return_no").val();
		var last_return_dt = $("#last_return_dt").val();
		var observation = $("#observation").val();
		var unit_sus_no = $("#unit_sus_no").val();
		var civilian_status = $("#civilian_status").val();

		$.post('Insert_civ?' + key + "=" + value, {
			month : month,
			year : year,
			present_return_no : present_return_no,
			present_return_dt : present_return_dt,
			last_return_no : last_return_no,
			last_return_dt : last_return_dt,
			observation : observation,
			unit_sus_no : unit_sus_no,
			civilian_status : civilian_status
		}, function(data) {

			if (data == null) {
				alert("Data Save/Updated Successfully");
				// $('#submitbtn').prop('disabled',false)
			} else {
				alert(data)
				// $('#submitbtn').prop('disabled',false)
			}
		}).fail(function(xhr, textStatus, errorThrown) {
			//$('#submitbtn').prop('disabled',false)
		});

	}

	var civilianStatusDropdown = document.getElementById("civilian_status");

	civilianStatusDropdown.addEventListener("change", function() {
		 
		const selectedValue = civilianStatusDropdown.value;
		if (selectedValue === "R") {
			document.getElementById("regular_civ_div").style.display = "block";
			document.getElementById("non_regular_civ_div").style.display = "none";
			$("div#Savebtn").hide();
		} else if (selectedValue === "NR") {
			document.getElementById("regular_civ_div").style.display = "none";
			document.getElementById("non_regular_civ_div").style.display = "block";
			$("div#Savebtn").hide();
		}
	});

/* 	function updateColumnVisibility() {
		var civilianStatus = document.getElementById("civilian_status").value;
		var columnsToHide = [ "designation", "gazeeted", "group", "tos",
				"designation_date" ];

		if (civilianStatus === "NR") {
			for (var i = 0; i < columnsToHide.length; i++) {
				document.querySelectorAll(
						"th[data-column='" + columnsToHide[i] + "']").forEach(
						function(th) {
							th.style.display = "none";
						});
				document.querySelectorAll(
						"td[data-column='" + columnsToHide[i] + "']").forEach(
						function(td) {
							td.style.display = "none";
						});
			}
		} else {
			for (var i = 0; i < columnsToHide.length; i++) {
				document.querySelectorAll(
						"th[data-column='" + columnsToHide[i] + "']").forEach(
						function(th) {
							th.style.display = "";
						});
				document.querySelectorAll(
						"td[data-column='" + columnsToHide[i] + "']").forEach(
						function(td) {
							td.style.display = "";
						});
			}
		}
	} */
</script>