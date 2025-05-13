<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="Stylesheet" href="js/Calender/jquery-ui.css">
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>

<link href="js/amin_module/rbac/datatables/jquery.dataTables.min.css"
	rel="stylesheet">
<script src="js/amin_module/rbac/datatables/jquery.dataTables.js"></script>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<style>
.table {
	display: grid;
	width: 100%;
	table-layout: none;
	overflow-x: scroll;
	margin: auto;
}

.table thead {
	display: table;
	width: calc(100% - 16px);
}

.table tbody {
	display: block;
}

.table td {
	padding: .2rem;
	vertical-align: top;
	border-top: 1px solid #dee2e6;
	width: 90px;
	min-width: 52px;
	text-align: center;
}

.table th {
	padding: .2rem;
	vertical-align: top;
	border-top: 1px solid #dee2e6;
	width: 90px;
	min-width: 52px;
}

.table tr {
	disaplay: table;
	width: 100%;
}

.table td, .table th {
	min-width: 52px;
	word-break: break-all words;
	min-width: 135px;
}
</style>
<style>
table tbody {
	display: block;
	max-height: 500px;
	overflow-y: scroll;
	width: 100%;
	scrollbar-width: thin;
	font-size: 10px;
}

table thead, table tbody tr {
	display: table;
	width: 100%;
	table-layout: fixed;
}
</style>

<%
   String nPara=request.getParameter("Para");
   
%>


<form:form action="Scrutiny_approval_action" id="Scrutiny_approval"
	method="post" class="form-horizontal"
	commandName="Scrutiny_approvalcmd">

	<div class="container" align="center">
		<div class="card">
			<div class="card-header">
				<% if (nPara.equalsIgnoreCase("UNIT")) { %>
				<h5>UNIT LEVEL APPROVAL</h5>
				<h6>
					<span style="font-size: 12px; color: red">(To be Approved by
						Medical Unit)</span>
				</h6>
				<% } %>

				<% if (nPara.equalsIgnoreCase("L1")) { %>
				<h5>APPROVAL BY MISO STAFF</h5>
				<h6>
					<span style="font-size: 12px; color: red">(To be Approved by
						MISO)</span>
				</h6>
				<% } %>

				<% if (nPara.equalsIgnoreCase("L2")) { %>
				<h5>APPROVAL BY GSO-1 M&H</h5>
				<h6>
					<span style="font-size: 12px; color: red">(To be Approved by
						MISO)</span>
				</h6>
				<% } %>

			</div>


			<div class="card-body card-block">
				<div class="row">
					<div class="col-md-12">
						<input type="hidden" id="CheckVal" name="CheckVal">
						<div class="col-md-8">
							<div class="row form-group">
								<div class="col-md-3" style="text-align: left;">
									<label for="text-input" class=" form-control-label"
										<% if (nPara.equalsIgnoreCase("L2")) { %>
										style="margin-left: 11px;" <% } %>> <% if (nPara.equalsIgnoreCase("UNIT") ) { %><strong
										style="color: red;">* </strong> <% } %>Hospital Name
									</label>
								</div>
								<div class="col-md-9">
									<input type="text" id="unit_name1" name="unit_name1"
										class="form-control-sm form-control" placeholder="Search..."
										autocomplete="off" maxlength="100"
										title="Type Unit Name or Part of Unit Name to Search">

									<label id="unit" style="display: none"></label>
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> <% if (nPara.equalsIgnoreCase("UNIT") ) { %><strong
										style="color: red;">* </strong> <% } %>SUS No
									</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="sus_no1" name="sus_no1"
										class="form-control-sm form-control" placeholder="Search..."
										autocomplete="off" maxlength="8"
										title="Type SUS No or Part of SUS No to Search"> <label
										id="sus" style="display: none"></label>
								</div>
							</div>
						</div>
					</div>


					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label"><strong
										style="color: red;">* </strong>From Date</label>
								</div>
								<div class="col-md-8">
									<input type="date" id="frm_dt" name="frm_dt"
										class="form-control-sm form-control" autocomplete="off">
								</div>
							</div>
						</div>



						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>To Date</label>
								</div>
								<div class="col-md-8">
									<input type="date" id="to_dt" name="to_dt"
										class="form-control-sm form-control" autocomplete="off">
								</div>
							</div>
						</div>
					</div>
					<% if (nPara.equalsIgnoreCase("L1") || nPara.equalsIgnoreCase("L2")) { %>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label">ICD
										Remarks a</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="icd_remarks_a" name="icd_remarks_a"
										class="form-control-sm form-control" autocomplete="off">
								</div>
							</div>
						</div>



						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">ICD Remarks d</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="icd_remarks_d" name="icd_remarks_d"
										class="form-control-sm form-control" autocomplete="off">
								</div>
							</div>
						</div>
					</div>
					<% } %>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label"><strong
										style="color: red;">* </strong>Status</label>
								</div>
								<div class="col-md-8">
									<select name="patient_status" id="patient_status"
										class="form-control-sm form-control">
										<option value="PNDUNIT">Pending</option>
										<option value="APPROVE">Approved</option>
										<% if (nPara.equalsIgnoreCase("L2")) { %>
										<option value="PASS">Pass</option>
										<% } %>
									</select>
								</div>
							</div>
						</div>
                          <% if (nPara.equalsIgnoreCase("L1")|| nPara.equalsIgnoreCase("L2")) { %>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label"><strong
										style="color: red;"> </strong>Relation</label>
								</div>
								<div class="col-md-8">
									<select id="relation" name="relation"
										class="form-control-sm form-control">
										<option value="-1">--Select--</option>
										<c:forEach var="item" items="${ml_6}" varStatus="num">
											<option value="${item}" name="${item}">${item}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>

                     <% } %>
					</div>
					<% if (nPara.equalsIgnoreCase("L2")) { %>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label for="text-input" class=" form-control-label">Diagnosis</label>
							</div>
							<div class="col-md-8">
								<select id="p_diag" name="p_diag"
									class="form-control-sm form-control">
									<option value="-1">--Select--</option>
									<c:forEach var="item" items="${ml_5}" varStatus="num">
										<option value="${item}" name="${item}">${item}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
					<% } %>
				</div>
			</div>

			<div class="form-control card-footer" align="center">
				<% if (nPara.equalsIgnoreCase("UNIT")) { %>
				<a href="mnh_scrutiny_unit_approval" type="reset"
					class="btn btn-primary btn-sm" onclick="btn_clc();"> Clear </a>
				<% } %>

				<% if (nPara.equalsIgnoreCase("L1")) { %>
				<a href="mnh_level1_approval" type="reset"
					class="btn btn-primary btn-sm" onclick="btn_clc();"> Clear </a>
				<% } %>
				<% if (nPara.equalsIgnoreCase("L2")) { %>
				<a href="mnh_level2_approval" type="reset"
					class="btn btn-primary btn-sm" onclick="btn_clc();"> Clear </a>
				<% } %>

				<i class="fa fa-search"></i><input type="button" id="btdog"
					class="btn btn-success btn-sm" value="Search"
					onclick="return isvalidData()">
			</div>


		</div>
	</div>

	<div class="container" id="divPrint" style="display: none;">



		<div id="divSerachInput" class="col-md-12">

			<div class="row form-group">
				<div class="col-md-6">
					<input id="searchInput" type="text"
						style="font-family: 'fontello', Arial;" placeholder="Search Word"
						size="35" class="form-control">
				</div>
				<%-- 	  <% if (nPara.equalsIgnoreCase("L1")) { %> 
						 	  
						 <div class="col-md-6" style="padding-left: 290px;">
							<label id="unit_name22" name ="unit_name22" > <h6 >Total Records :  ${list.size()}</h6></label>
						 </div>
						
					    <% } %> --%>
				<% if (nPara.equalsIgnoreCase("UNIT") || nPara.equalsIgnoreCase("L1") || nPara.equalsIgnoreCase("L2")) { %>
				<c:if test="${stat1 == 'APPROVE' || stat1 == 'PASS'}">
					<div class="col-md-6" style="padding-left: 290px;">
						<label id="unit_name22" name="unit_name22">
							<h6>Total Records : ${list.size()}</h6>
						</label>
					</div>
				</c:if>
				<% } %>
			</div>

		</div>


		<div id="SearchViewtr" align="right">
			<% if (nPara.equalsIgnoreCase("UNIT") || nPara.equalsIgnoreCase("L1")||  nPara.equalsIgnoreCase("L2")) { %>
			<c:if test="${stat1 == 'PNDUNIT'}">
				<b><input type=checkbox id='nSelAll' name='tregn'
					onclick='setselectall();'>Select all [<span id="tregn"></span><span
					id='nTotRow1'>/</span><span id="tregn"> ${list.size()}</span>]</b>
			</c:if>
			<% } %>
		</div>

		<div class="tbl_scroll">
			<table id="SearchReport"
				class="table no-margin table-striped  table-hover  table-bordered report_print">
				<thead>
					<tr>
						<th>Ser No</th>
						<th>A&D No</th>
						<th>Patient Name</th>
						<% if (nPara.equalsIgnoreCase("L2")) { %>
						<th>Gender</th>
						<th>Relation</th>
						<th>Personnel No</th>
						<th>Rank</th>
						<th>Category</th>
						<% } %>
						<th>Admission Diagnosis</th>
						<th>Discharge Diagnosis</th>
						<th>Discharge ICD Code</th>
						<th>Discharge ICD Cause</th>


						<% if (nPara.equalsIgnoreCase("UNIT") || nPara.equalsIgnoreCase("L1") || nPara.equalsIgnoreCase("L2")) { %>
						<c:if test="${stat1 == 'PNDUNIT' || stat1 == 'PASS'}">
							<th>Select To Approve</th>
						</c:if>

						<% } %>

						<% if (nPara.equalsIgnoreCase("UNIT") || nPara.equalsIgnoreCase("L1") || nPara.equalsIgnoreCase("L2")) { %>
						<c:if test="${stat1 == 'PNDUNIT' || stat1 == 'PASS'}">
							<th style="width: 104px;">Action</th>
						</c:if>
						<% } %>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${list}" varStatus="num">
						<tr>
							<td>${num.index+1}</td>
							<td style="text-align: left;">${item.and_no}</td>
							<td style="text-align: left;">${item.name}</td>
							<% if (nPara.equalsIgnoreCase("L2")) { %>
							<td style="text-align: left;">${item.sex}</td>
							<td style="text-align: left;">${item.relationship}</td>
							<td style="text-align: left;">${item.persnl_no}</td>
							<td style="text-align: left;">${item.rank}</td>
							<td style="text-align: left;">${item.category}</td>
							<% } %>
							<td style="text-align: left;">${item.icd_remarks_a}</td>
							<td style="text-align: left;">${item.icd_remarks_d}</td>

							<% if (nPara.equalsIgnoreCase("UNIT") 
									) { %>

							<td style="text-align: left;">${item.diagnosis_code1d}</td>
							<td style="text-align: left;">${item.icd_cause_code1d}</td>

							<% } %>

							<% if (nPara.equalsIgnoreCase("L2") || nPara.equalsIgnoreCase("L1")) { %>
							<c:if test="${(stat1 == 'PNDUNIT' || stat1 == 'PASS')}">
								<td><input type="text" id="a_rem${item.id}"
									name="a_rem${item.id}" onkeypress="a_remAuto(this);"
									autocomplete="off" value="${item.diagnosis_code1d}"></td>
								<td><input type="text" id="d_rem${item.id}"
									name="d_rem${item.id}" autocomplete="off"
									onkeypress="d_remAuto(this);" value="${item.icd_cause_code1d}">
								</td>
							</c:if>

							<c:if test="${(stat1 == 'APPROVE')}">

								<td style="text-align: left;">${item.diagnosis_code1d}</td>
								<td style="text-align: left;">${item.icd_cause_code1d}</td>

							</c:if>
							<% } %>


							<% if (nPara.equalsIgnoreCase("UNIT")  || nPara.equalsIgnoreCase("L1") || nPara.equalsIgnoreCase("L2")) { %>

							<c:if test="${(stat1 == 'PNDUNIT' || stat1 == 'PASS')}">
								<td id="thAction1">${item.checkbox}${item.hid}</td>
							</c:if>

							<% } %>

							<% if (nPara.equalsIgnoreCase("UNIT") || nPara.equalsIgnoreCase("L1") || nPara.equalsIgnoreCase("L2")) { %>
							<c:if test="${(stat1 == 'PNDUNIT' || stat1 == 'PASS')}">
								<td>${item.edit_l1}</td>
							</c:if>
							<% } %>
						</tr>
					</c:forEach>
				</tbody>
			</table>

		</div>

		<% if (nPara.equalsIgnoreCase("UNIT") || nPara.equalsIgnoreCase("L1") || nPara.equalsIgnoreCase("L2")) { %>
		<div align="right" style="display: none;" id="ft_btn">
			<c:if test="${stat1 == 'PNDUNIT' || stat1 == 'PASS' }">
				<input type="button" class="btn btn-success btn-sm" value="Approve"
					onclick="return setUpdateStatus();">

				<% if (nPara.equalsIgnoreCase("L2") || nPara.equalsIgnoreCase("L1")) { %>

				<input type="hidden" id="update_icd" name="update_icd"
					class="form-control-sm form-control" autocomplete="off">
				<input type="submit" class="btn btn-danger btn-sm"
					value="Update ICD" onclick="return setUpdateStatus1();">
				<% } %>
			</c:if>
		</div>
		<% } %>
	</div>

</form:form>



<c:url value="DSunitList" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="sus1">
	<input type="hidden" name="sus1" id="sus1">
	<input type="hidden" name="unit1" id="unit1">
	<input type="hidden" name="cmd1" id="cmd1">
	<input type="hidden" name="frm_dt1" id="frm_dt1">
	<input type="hidden" name="to_dt1" id="to_dt1">
	<input type="hidden" name="stat1" id="stat1">
	<input type="hidden" name="para1" id="para1">
	<input type="hidden" name="p_diag1" id="p_diag1">
	<input type="hidden" name="icd_remarks_a1" id="icd_remarks_a1">
	<input type="hidden" name="icd_remarks_d1" id="icd_remarks_d1">
	<input type="hidden" name="relation1" id="relation1">
</form:form>

<c:url value="edit_patientDetails_datascrutiny" var="editUrl" />
<form:form action="${editUrl}" method="post" id="updateForm"
	name="updateForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2">
	<input type="hidden" name="para2" id="para2">
	<input type="hidden" name="sus2" id="sus2">
	<input type="hidden" name="unit2" id="unit2">
	<input type="hidden" name="cmd2" id="cmd2">
	<input type="hidden" name="frm_dt2" id="frm_dt2">
	<input type="hidden" name="to_dt2" id="to_dt2">
	<input type="hidden" name="stat2" id="stat2">
	<input type="hidden" name="p_diag2" id="p_diag2">
	<input type="hidden" name="diagnosis_code1d1" id="diagnosis_code1d1">
	<input type="hidden" name="icd_remarks_a2" id="icd_remarks_a2">
	<input type="hidden" name="icd_remarks_d2" id="icd_remarks_d2">
	<input type="hidden" name="check2" id="check2">
</form:form>

<c:url value="edit_dischargeDetails_datascrutiny" var="edit2Url" />
<form:form action="${edit2Url}" method="post" id="updateForm2"
	name="updateForm2" modelAttribute="id3">
	<input type="hidden" name="id3" id="id3">
	<input type="hidden" name="a_rem" id="a_rem">
	<input type="hidden" name="d_rem" id="d_rem">
</form:form>

<script>
var check_count = 0;
function checkbox_count(obj,id)
{
	if(document.getElementById(obj.id).checked == true)
	{ 
		check_count++;
		
	}
	if(document.getElementById(obj.id).checked == false)
	{
		check_count--;
		
	}
	
	document.getElementById('tregn').innerHTML =check_count;
	
}
function btn_clc(){
	$("#unit_name1").val('');
	$("#sus_no1").val('');	
	$().getFirDt(frm_dt);
	$().getCurDt(to_dt);
	$("#patient_status").val('PNDUNIT');
}

function isvalidData(){
	
	 var tab = $("#Acc_TypeReport > tbody");
	 
	<% if (nPara.equalsIgnoreCase("UNIT")) { %>
	   var para = "UNIT"; 
	<% } %>

	<% if (nPara.equalsIgnoreCase("L1")) { %>
	   var para = "L1"
	<% } %>
	<% if (nPara.equalsIgnoreCase("L2")) { %>
	   var para = "L2"
	<% } %>
	
	 if (($("#unit_name1").val() == "" || $("#sus_no1").val() == "") && (para == "UNIT")) {
		alert("Please Enter the Unit Name or SUS No");
		$("#unit_name1").focus();
		return false;
	}
	 

	if ($("#frm_dt").val() == "") {
		alert("Please Select the From Date");
		$("#frm_dt").focus();
		return false;
	}
	if ($("#to_dt").val() == "") {
		alert("Please Select the To Date");
		$("#to_dt").focus();
		return false;
	}
	if ($("#patient_status").val() == "") {
		alert("Please Select the Status");
		$("#patient_status").focus();
		return false;
	}
	
	$("#sus1").val($("#sus_no1").val());
	$("#unit1").val($("#unit_name1").val());

	$("#frm_dt1").val($("#frm_dt").val());
	$("#to_dt1").val($("#to_dt").val());
	$("#stat1").val($("#patient_status").val());
	$("#para1").val(para);
	$("#p_diag1").val($("#p_diag").val());
	$("#icd_remarks_a1").val($("#icd_remarks_a").val());
	$("#icd_remarks_d1").val($("#icd_remarks_d").val());	
	$("#relation1").val($("#relation").val());
	
	$("#searchForm").submit();
}

function findselected(){
	var nrSel=$('.nrCheckBox:checkbox:checked').map(function() {
		return $(this).attr('id');
	}).get();
		
	var b=nrSel.join(':');
	$("#CheckVal").val(b);
	$('#tregn').text(nrSel.length);
}


function setselectall(){

	if ($("#nSelAll").prop("checked")) {
		$(".nrCheckBox").prop('checked', true);
	} else {
		$(".nrCheckBox").prop('checked', false);
	}
	
	var l = $('[name="cbox"]:checked').length;
	 $("#tregn").val(l);
	document.getElementById('tregn').innerHTML = l;
	
}



function drawtregn(data) {
	var ii=0;
	$("#nrTable").empty();

	for (var i = 0; i <data.length; i++) {
		 var nkrow="<tr id='nrTable' padding='5px;'>";
		 nkrow=nkrow+"<td>&nbsp;&nbsp;";
		 nkrow=nkrow+ data[i] + "("+data[i]+")</td>";
		 $("#nrTable").append(nkrow);
		 ii=ii+1;
	}
	$("#tregn").text(ii);
}

function setUpdateStatus(){
	<% if (nPara.equalsIgnoreCase("UNIT")) { %>
	   var para = "UNIT"; 
	 
	<% } %>
	<% if (nPara.equalsIgnoreCase("L1")) { %>
	   var para = "L1"; 
	<% } %>
	<% if (nPara.equalsIgnoreCase("L2")) { %>
	   var para = "L2"; 
	<% } %>
	findselected();
	
	var a = $("#CheckVal").val();

	if(a == ""){
		alert("Please Select the Data for Approval"); 
	}else{

			$.post("DSunitApproved?"+key+"="+value, {a:a,para:para}).done(function(j) {
			alert(j);
			isvalidData();
		}); 
	}	
}

function setUpdateStatus1(){
	<% if (nPara.equalsIgnoreCase("L1")) { %>
	   var para = "L1"; 
	   $("#update_icd").val("1");
	<% } %>
	<% if (nPara.equalsIgnoreCase("L2")) { %>
	   var para = "L2"; 
	   $("#update_icd").val("2");
	<% } %>
	findselected();
	
	var a = $("#CheckVal").val();	 
	 var id_a = a.split(":");
	  var icdcode = [];
	  var icdcause = [];
	  for (var i = 0; i < id_a.length; i++) {
	    var ida = id_a[i];
	    
	    let icd_code =  $("#a_rem" + ida).val();
	    if(icd_code == ""){
	    	icdcode.push("$icdcode/#");

			} else {
				icdcode.push($("#a_rem" + ida).val())
			}

			let icd_cause = $("#d_rem" + ida).val();
			if (icd_cause == "") {
				icdcause.push("$icdcause/#");
			} else {
				icdcause.push($("#d_rem" + ida).val());
			}

		}

		//alert("icdcode"+icdcode);
		//alert("icdcause"+icdcause);

		if (a == "") {
			alert("Please Select the Data for Update");
			return false;

		} else {

			$.post("DSunitUpdate?" + key + "=" + value, {
				a : id_a.join(":"),
				icdcode : icdcode.join(":"),
				icdcause : icdcause.join(":"),
				para : para
			}).done(function(j) {
				alert(j);
				isvalidData();
			});
		}
	}
function setReject(){
	var nrSel=$('.nrCheckBox:checkbox:checked').map(function() {
		return $(this).attr('id');
	}).get();
		
	var b=nrSel.join(':');

	if(b != ""){
		var ar = b.split(':');
		if(ar.length > 1){
			alert("You Can Cancel Only One Entry At a time...");
		}else{
			var r_remarks = $("#reason"+ar).val();
			if(r_remarks == ""){
				alert("Please Enter the Rejection Remarks");
				$("#reason"+ar).focus();
				return false;
			}
			$.post("DSunitRejected?"+key+"="+value, {a:b,r:r_remarks}).done(function(j) {
	
				alert(j);
				isvalidData();
			}); 
		}
	}else{
		alert("Please Select the Data for Rejection"); 
	}
}

function editData(id){
	<% if (nPara.equalsIgnoreCase("UNIT")) { %>
	   var para = "UNIT"; 
	<% } %>
	
	<% if (nPara.equalsIgnoreCase("L1")) { %>
	   var para = "L1"
	<% } %>
	<% if (nPara.equalsIgnoreCase("L2")) { %>
	   var para = "L2"
	<% } %>
	<% if (nPara.equalsIgnoreCase("AC")) { %>
	   var para = "AC"
	<% } %>
	
	$("#id2").val(id);
	$("#para2").val(para);
	
	$("#sus2").val($("#sus_no1").val());
	$("#unit2").val($("#unit_name1").val());
	
	$("#frm_dt2").val($("#frm_dt").val());
	$("#to_dt2").val($("#to_dt").val());
	$("#stat2").val($("#patient_status").val());
	$("#p_diag2").val($("#p_diag").val());
	$("#icd_remarks_a2").val($("#icd_remarks_a").val());
	$("#icd_remarks_d2").val($("#icd_remarks_d").val());
	$("#check2").val($("#check").val());
	$("#updateForm").submit();
}

function editDischarge(id){
	$("#id3").val(id);
	$("#a_rem").val($("input#a_rem"+id).val());
	$("#d_rem").val($("input#d_rem"+id).val());
	$("#updateForm2").submit();
}
function getCommand(y){
	$("#command").attr('disabled',true);
	var FindWhat = "COMMAND";

		$.post("getMNHHirarNameBySUS?"+key+"="+value, {FindWhat:FindWhat,a:y},function(j) {
	
		var a = [];
		var enc = j[j.length-1].substring(0,16);
		for(var i = 0; i < j.length; i++){
			a[i] = dec(enc,j[i]);
		}
		var data=a[0].split(",");
		var datap;
		
		for(var i = 0; i < data.length-1; i++) {
			datap=data[i].split(":");
			$("#command").val(datap[0]);  
	
		}	
	}); 
}

var key = "${_csrf.parameterName}";
var value = "${_csrf.token}";


jQuery(function() {
	// Source SUS No

	jQuery("#sus_no1").keypress(function(){
		var sus_no = this.value;
			 var susNoAuto=jQuery("#sus_no1");
			  susNoAuto.autocomplete({
			      source: function( request, response ) {
			        jQuery.ajax({
			        type: 'POST',
			        url: "getTargetSUSNoList?"+key+"="+value,
			        data: {sus_no:sus_no},
			          success: function( data ) {
			        	  var susval = [];
			        	  var length = data.length-1;
			        	  if(data.length != 0){
				        		var enc = data[length].substring(0,16);
				        	}
				        	for(var i = 0;i<data.length;i++){
				        		susval.push(dec(enc,data[i]));
				        	}
				        	var dataCountry1 = susval.join("|");
			            var myResponse = []; 
			            var autoTextVal=susNoAuto.val();
						jQuery.each(dataCountry1.toString().split("|"), function(i,e){
							var newE = e.substring(0, autoTextVal.length);
							if (e.toLowerCase().includes(autoTextVal.toLowerCase())) {
							  myResponse.push(e);
							}
						});      	          
						response( myResponse ); 
			          }
			        });
			      },
			      minLength: 1,
			      autoFill: true,
			      change: function(event, ui) {
			    	 if (ui.item) {   	        	  
			        	  return true;    	            
			          } else {
			        	  alert("Please Enter Approved SUS No.");
			        	  document.getElementById("sus_no1").value="";
			        	  susNoAuto.val("");	        	  
			        	  susNoAuto.focus();
			        	  return false;	             
			          }   	         
			      }, 
			      select: function( event, ui ) {
			    	  var unit_sus_no = ui.item.value;
			    	 	
  	 			 	$.post("getActiveUnitNameFromSusNo?"+key+"="+value, {sus_no:unit_sus_no}).done(function(j) {				
  	 			 		if(j == ""){
  	 			      	 	alert("Please Enter Approved Unit SUS No.");
  			        	  	document.getElementById("unit_name1").value="";
  			        	  	susNoAuto.val("");
  			        	  	susNoAuto.focus();
  	 			      	}else{
	    	 	   	        	var length = j.length-1;
	    	    				var enc = j[length].substring(0,16);
	    	    				$("#unit_name1").val(dec(enc,j[0]));	
	    	 	   	        		
  	 	   	        	}
  	 				}).fail(function(xhr, textStatus, errorThrown) {
  	 				});
			      } 	     
			});	
	});
	// End
	
	// Source Unit Name

    jQuery("#unit_name1").keypress(function(){
 			var unit_name = this.value;
				 var susNoAuto=jQuery("#unit_name1");
				  susNoAuto.autocomplete({
				      source: function( request, response ) {
				        jQuery.ajax({
				        type: 'POST',
				        url: "getTargetUnitsNameActiveList?"+key+"="+value,
				        data: {unit_name:unit_name},
				          success: function( data ) {
				    
				        	  var susval = [];
				        	  var length = data.length-1;
				        
				        	  if(data.length != 0){
					        		var enc = data[length].substring(0,16);
					        	}
					        	for(var i = 0;i<data.length;i++){
					        		susval.push(dec(enc,data[i]));
					        	}
					        	var dataCountry1 = susval.join("|");
				            var myResponse = []; 
				            var autoTextVal=susNoAuto.val();
							jQuery.each(dataCountry1.toString().split("|"), function(i,e){
								var newE = e.substring(0, autoTextVal.length);
								if (e.toLowerCase().includes(autoTextVal.toLowerCase())) {
								  myResponse.push(e);
								}
							});      	
						
							response( myResponse ); 
							
				          }
				        });
				      },
				      minLength: 1,
				      autoFill: true,
				      change: function(event, ui) {
				    	 if (ui.item) {   	        	  
				        	  return true;    	            
				          } else {
				        	  alert("Please Enter Approved Unit Name.");
				        	  document.getElementById("unit_name1").value="";
				        	  susNoAuto.val("");	        	  
				        	  susNoAuto.focus();
				        	  return false;	             
						}   	         
					}, 
					select: function( event, ui ) {
						 var target_unit_name = ui.item.value;
						
								 	$.post("getTargetSUSFromUNITNAME?"+key+"="+value, {target_unit_name:target_unit_name}).done(function(j) {				
								 		 var length = j.length-1;
				 				         var enc = j[length].substring(0,16);
				 				         jQuery("#sus_no1").val(dec(enc,j[0]));	
									}).fail(function(xhr, textStatus, errorThrown) {
							});
					} 	     
				}); 			
 			});

});


</script>
<script>


function a_remAuto(obj) {
	
	var check_id = obj.id;
	var k = check_id.replace('a_rem','');
	
	// Part No AutoComplete
	var wepetext=$("#"+obj.id);
    wepetext.autocomplete({
        source: function( request, response ) {
          $.ajax({
          type: 'POST',
          url: "getMNHDistinctICDList?"+key+"="+value,
          data: {a:$("#"+obj.id).val(),b:"ALL"},
            success: function( data ) { 
                      var codeval = [];
                      var length = data.length-1;
                      var enc = data[length].substring(0,16);
                          for(var i = 0;i<data.length;i++){
                                  codeval.push(dec(enc,data[i]));
                          } 
                          var dataCountry1 = codeval.join("|");
                          var myResponse = []; 
                      var autoTextVal=wepetext.val();
                                  $.each(dataCountry1.toString().split("|"), function(i,e){
                                          var newE = e.substring(0, autoTextVal.length);
                                  if (e.toLowerCase().includes(autoTextVal.toLowerCase())) {
                                            myResponse.push(e);
                                          } 
                          });                        
                          response( myResponse );  
            }
          });
      	},
        minLength: 1,
        autoFill: true,
        change: function(event, ui) {
               if (ui.item) { 
                    return true;                        
            } else {
                    alert("Please Enter Approved Diagnosis code 1d"); 
                    return false;                     
            }                    
        }, 
        select: function( event, ui ) {
        	var nom = ui.item.value;
        	
             
             } 
      });
}



function d_remAuto(obj) {
	
	var check_id = obj.id;
	var k = check_id.replace('d_rem','');
	var wepetext=$("#"+obj.id);
	
	var b_1 = $("#a_rem"+k).val().split("-");
	var b_2 = b_1[0].substring(0,1).toUpperCase();

if (b_2 == "S" || b_2 == "T" || b_2 == "X" || b_2 == "V" || b_2 == "W" || b_2 == "Y") 
	{	
	 wepetext.autocomplete({
        source: function( request, response ) {
          $.ajax({
          type: 'POST',
          url: "getMNHDistinctICDList?"+key+"="+value,
          data: {a:$("#"+obj.id).val(),b:"ALL"},
            success: function( data ) { 
                      var codeval = [];
                      var length = data.length-1;
                      var enc = data[length].substring(0,16);
                          for(var i = 0;i<data.length;i++){
                                  codeval.push(dec(enc,data[i]));
                          } 
                          var dataCountry1 = codeval.join("|");
                          var myResponse = []; 
                      var autoTextVal=wepetext.val();
                                  $.each(dataCountry1.toString().split("|"), function(i,e){
                                          var newE = e.substring(0, autoTextVal.length);
                                  if (e.toLowerCase().includes(autoTextVal.toLowerCase())) {
                                            myResponse.push(e);
                                          } 
                          });                        
                          response( myResponse );  
            }
          });
      	},
        minLength: 1,
        autoFill: true,
        change: function(event, ui) {
               if (ui.item) { 
                    return true;                        
            } else {
                    alert("Please Enter Approved Diagnosis code 1d"); 
                    return false;                     
            }                    
        }, 
        select: function( event, ui ) {
        	var nom = ui.item.value;
        	
             
             } 
      });
	}  

}


$(document).ready(function() {
	
	
	var l = '${size123}';

	
	/* if (l==2) {
	
		$('#sus_no1').hide();
		$('#unit_name1').hide();
		$('#sus').show();
		$('#unit').show();
	
		
	 }
	else {

		$('#sus_no1').show();
		$('#unit_name1').show();
		$('#sus').hide();
		$('#unit').hide();
	 } */
	
	$("#searchInput").on("keyup", function() {
		var value = $(this).val().toLowerCase();
		$("#SearchReport tbody tr").filter(function() { 
		$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		});
	});
	
 	  $('#SearchReport').DataTable( {
	       columnDefs: [ {
	           targets: [ 0 ],
	           orderData: [ 0, 1 ]
	       }, {
	           targets: [ 1 ],
	           orderData: [ 1, 0 ]
	       }, {
	           targets: [ 4 ],
	           orderData: [ 4, 0 ]
	       } ],
	       bPaginate: false,
	       bInfo : false,
	       bFilter: false,               
	       bLengthChange: false,
	       sDom: 'lrtip',
	       paging:   false,
	       ordering: false,
	       info:     false 
	       
	       
	      
	   } ); 	 
 	
 
	$().getFirDt(frm_dt);
	$().getCurDt(to_dt);
	
	if('${size}' != 0){
		$("#ft_btn").show();
		$("#divPrint").show();
	} 
	
	
	if('${size}' == 0 && '${size}' != ""){
		$("#ft_btn").hide();
		$("#divPrint").show();
		
	}
	
	
		<% if (nPara.equalsIgnoreCase("UNIT") ||  nPara.equalsIgnoreCase("L1") || nPara.equalsIgnoreCase("L2")) { %>
	          <c:if test="${stat1 == 'PNDUNIT'}">
	         document.getElementById('tregn').innerHTML =0;
            </c:if>
	        <% } %> 
	
	$('#unit_name1').change(function(){
		var y = this.value;
		
		<% if (nPara.equalsIgnoreCase("FMN")) { %>
		  
			   $.post("getMNHSUSNoByUnitName?"+key+"="+value, {y:y}).done(function(j) {
		   
			   var enc = j[j.length-1].substring(0,16);
			   var a = dec(enc,j[0]);
			   getCommand(a);		
		   });
		<% } %>
	});

						$('#sus_no1').change(function() {
							var y = this.value;

						});

						var p = '${sus3}';

						var p1 = '${unit3}';
						var p2 = '${cmd3}';
						var p3 = '${frm_dt3}';
						var p4 = '${to_dt3}';
						var p5 = '${stat3}';
						var p6 = '${p_diag3}';
						var p7 = '${icd_remarks_a3}';
						var p8 = '${icd_remarks_d3}';

						if (p != "") {
							$("#sus_no1").val(p);
						}
						if (p1 != "") {
							$("#unit_name1").val(p1);
						}

						if (p3 != "") {
							$("#frm_dt").val(p3);
						}

						if (p4 != "") {
							$("#to_dt").val(p4);
						}

						if (p5 != "") {
							$("#patient_status").val(p5);
						}

						if (p2 != "") {
							$("#command").val(p2);
						}

						if (p6 != "") {
							$("#p_diag").val(p6);
						}
						if (p7 != "") {
							$("#icd_remarks_a").val(p7);
						}
						if (p8 != "") {

							$("#icd_remarks_d").val(p8);
						}

						if ('${para3}' != "") {

							$("#sus1").val($("#sus_no1").val());
							$("#unit1").val($("#unit_name1").val());
							$("#frm_dt1").val($("#frm_dt").val());
							$("#to_dt1").val($("#to_dt").val());
							$("#stat1").val($("#patient_status").val());
							$("#para1").val('${para3}');
							$("#p_diag1").val($("#p_diag").val());
							$("#icd_remarks_a1").val($("#icd_remarks_a").val());
							$("#icd_remarks_d1").val($("#icd_remarks_d").val());
							$("#relation1").val($("#relation").val());
							$("#searchForm").submit();
						}

						var q = '${sus1}';
						var q1 = '${unit1}';
						var q2 = '${frm_dt1}';
						var q3 = '${to_dt1}';
						var q4 = '${stat1}';
						var q6 = '${p_diag1}';
						var q7 = '${icd_remarks_a1}';
						var q8 = '${icd_remarks_d1}';
						var q99 = '${relation1}';

						if (q != "") {

							$("#sus_no1").val(q);
						}

						if (q1 != "") {

							$("#unit_name1").val(q1);
						}

						if (q2 != "") {

							$("#frm_dt").val(q2);
						}

						if (q3 != "") {
							$("#to_dt").val(q3);
						}

						if (q4 != "") {
							$("#patient_status").val(q4);
						}

						if (q6 != "") {
							$("#p_diag").val(q6);
						}
						if (q7 != "") {
							$("#icd_remarks_a").val(q7);
						}
						if (q8 != "") {

							$("#icd_remarks_d").val(q8);
						}
						if (q99 != "") {

							$("#relation").val(q99);
						}

						if ('${roleAccess}' == "Unit") {
							$("#sus_no1").val('${sus_no}');
							$("#unit_name1").val('${unit_name}');
							$("#sus_no1").attr('readonly', 'readonly');
							$("#unit_name1").attr('readonly', 'readonly');
						}

						try {
							if (window.location.href.includes("msg=")) {
								var url = window.location.href.split("?")[0];
								window.location = url;
							}
						} catch (e) {
							// TODO: handle exception
						}

					});
</script>