<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
<link href="js/cue/cueWatermark.css" rel="Stylesheet"></link>
<style>
.multiselect {
	width: 200px;
}
.selectBox {
	position: relative;
}
.selectBox select {
	width: 100%;
}
.overSelect {
	position: absolute;
	left: 0;
	right: 0;
	top: 0;
	bottom: 0;
}
#checkboxes {
	display: none;
	border: 1px #dadada solid;
}
#checkboxes label {
	display: block;
	text-align: left;
	padding-left: 30px;
}
#checkboxes label:hover {
	background-color: #1e90ff;
}
#checkboxes label input[type="checkbox"] {
	margin-right: 10px;
}
#checkboxes label, #checkboxesbattalion label, #checkboxescompany label,
	#checkboxesplatoon label {
	margin-bottom: 0;
}
#checkboxesbattalion {
	display: none;
	border: 1px #dadada solid;
}
#checkboxesbattalion label {
	display: block;
	text-align: left;
	padding-left: 30px;
}
#checkboxesbattalion label:hover {
	background-color: #1e90ff;
}
#checkboxesbattalion label input[type="checkbox"] {
	margin-right: 10px;
}
#checkboxescompany {
	display: none;
	border: 1px #dadada solid;
}
#checkboxescompany label {
	display: block;
	text-align: left;
	padding-left: 30px;
}
#checkboxescompany label:hover {
	background-color: #1e90ff;
}
#checkboxescompany label input[type="checkbox"] {
	margin-right: 10px;
}
#checkboxesplatoon {
	display: none;
	border: 1px #dadada solid;
}
#checkboxesplatoon label {
	display: block;
	text-align: left;
	padding-left: 30px;
}
#checkboxesplatoon label:hover {
	background-color: #1e90ff;
}
#checkboxesplatoon label input[type="checkbox"] {
	margin-right: 10px;
}
</style>
<form:form name="Institute" id="Institute" action="InstituteAction" method="post" class="form-horizontal" commandName="InstituteCMD">
	<div class="animated fadeIn">
		<div class="container-fluid" align="center">
			<div class="card">
				<div class="card-header">
					<h5>Institute</h5>
					<h6 class="enter_by">
						<span style="font-size: 12px; color: red;">(To be entered by MISO)</span>
					</h6>
				</div>
				<div class="card-body card-block">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong> INSTITUTE NAME</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="institute_name" name="institute_name" maxlength="100" oninput="this.value = this.value.toUpperCase()" class="form-control autocomplete" autocomplete="off" onkeyup="return specialcharecter(this)">
									<input type="hidden" id="id" name="id" value="0" autocomplete="off">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong> INSTITUTE TYPE</label>
								</div>
								<div class="col-md-8">
									<select name="institute_type" id="institute_type" class="form-control-sm form-control">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getInstituteTypeList}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-4">
							<div class="row form-group">
								<div class="col-md-1">
									<input type="checkbox" id="bn_id" name="bn_id" class="form-control autocomplete" autocomplete="off" onclick="battalion_list(); checkboxesbattalion();">
								</div>
								<div class="col-md-3">
									<label class=" form-control-label" style="width: 100px"><strong style="color: red;">* </strong>BATTALION</label>
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="row form-group">
								<div class="col-md-1">
									<input type="checkbox" id="company_id" name="company_id" class="form-control autocomplete" autocomplete="off" onclick="company_list(); checkboxescompany();">
								</div>
								<div class="col-md-4">
									<label class=" form-control-label" style="width: 200px"><strong style="color: red;">* </strong>COMPANY / SQN</label>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-4">
							<div id="checkboxesbattalion"></div>
						</div>
						<div class="col-md-4">
							<div id="checkboxescompany"></div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong>STATUS</label>
								</div>
								<div class="col-md-8">
									<select name="status" id="status" class="form-control">
<!-- 										<option value="0">--Select--</option> -->
										<c:forEach var="item" items="${getStatusMasterList}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
					</div>
				</div>
				<input type="hidden" name="btn_arry_id" id="btn_arry_id" autocomplete="off" /> 
				<input type="hidden" name="company_arr_id" id="company_arr_id" autocomplete="off" />

				<div class="card-footer" align="center" class="form-control">
					<a href="institute" class="btn btn-success btn-sm">Clear</a> 
					<input type="submit" class="btn btn-primary btn-sm" value="Save" onclick="return Validation();"> 
					<input type="button" class="btn btn-info btn-sm" onclick="Search();" value="Search">
				</div>
				
			</div>
		</div>
	</div>
	<div align="right" class="">
		<i class="fa fa-file-excel-o" id="btnExport" style="font-size: x-large; color: green; text-align: right;" aria-hidden="true" title="EXPORT TO EXCEL" onclick="getExcel();"></i>
	</div>
	<br>
	<div class="container-fluid" id="getInstitute" style="display: block;">
		<div class="watermarked" data-watermark="" id="divwatermark">
			<span id="ip"></span>
			<table id="getInstitute" class="table no-margin table-striped  table-hover  table-bordered report_print">
				<thead>
					<tr>
						<th style="text-align: center; width: 10%;">Ser No</th>
						<th style="text-align: center;">Institute Name</th>
						<th style="text-align: center;">Battalion</th>
						<th style="text-align: center;">Company</th>
						<th style="text-align: center; width: 20%;">Action</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${list.size()==0}">
						<tr>
							<td style="font-size: 15px; text-align: center; color: red;" colspan="5">Data Not Available</td>
						</tr>
					</c:if>
					<c:forEach var="item" items="${list}" varStatus="num">
						<tr>
							<td style="font-size: 15px; text-align: center; width: 10%;">${num.index+1}</td>
							<td style="font-size: 15px; text-align: center;">${item[0]}</td>
							<td style="font-size: 15px; text-align: center;">${item[1]}</td>
							<td style="font-size: 15px; text-align: center;">${item[2]}</td>
							<td style="font-size: 15px; text-align: center; width: 20%;">${item[4]} ${item[5]}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</form:form>

<c:url value="GetSearch_Institute" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="Institute_name2">
	<input type="hidden" name="institute_name2" id="institute_name2" value="0" />
	<input type="hidden" name="bn_id2" id="bn_id2" value="0" />
	<input type="hidden" name="company_id2" id="company_id2" value="0" />
	<input type="hidden" name="status2" id="status2" value="0" />
</form:form>

<c:url value="Delete_Institute" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm" name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>

<c:url value="Institutereport" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2" name="search2" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form>

<script>
	function getExcel() {
		document.getElementById('typeReport1').value = 'excelL';
		document.getElementById('search2').submit();
	}
	$(document).ready(function() {
		
		
		if ('${list.size()}' == "") {
			$("div#getInstitute").hide();
		} else {
		}
		$("#institute_name").val('${institute_name2}');
		var b = '${bn_id2}';
		if (b == '') {
			$("select#bn_id").val("0");
		}
		if (b != '') {
			$("select#bn_id").val(b);
		}
		var com = '${company_id2}';
		if (com == '') {
			$("#company_id").val("0");
		}
		if (com != '') {
			$("#company_id").val(com);
		}
		if ('${status1}' != "") {
			$("Select#status").val('${status1}');
		}
	});
	function Search() {
	
		$("#institute_name2").val($("#institute_name").val());
		$("#bn_id2").val($("#bn_id").val());
		$("#company_id2").val($("#company_id").val());
		$("#status2").val($("#status").val());
		document.getElementById('searchForm').submit();
	}
	function deleteData(id) {
		$("#id1").val(id);
		document.getElementById('deleteForm').submit();
	}
</script>

<script>
	function checkboxesbattalion() {
		var checkboxes = document.getElementById("checkboxesbattalion");
		if ($("#bn_id").prop('checked') == true) {
			checkboxes.style.display = "block";
			checkboxes.style.overflow = "auto";
			checkboxes.style.height = "200px";
			checkboxes.style.width = "200px";
		} else {
			$("input[type=checkbox][name='b1']").prop("checked", false);
			checkboxes.style.display = "none";
			chkBtnString();
			$("input[type=checkbox][name='c1']").prop("checked", false);
			$("#checkboxescompany").hide();
			$("#company_id").prop("checked", false);
			chkCompanyString();
		}
	}
	function checkboxescompany() {
		var checkboxes = document.getElementById("checkboxescompany");
		if ($("#company_id").prop('checked') == true) {
			checkboxes.style.display = "block";
			checkboxes.style.overflow = "auto";
			checkboxes.style.height = "200px";
			checkboxes.style.width = "200px";
		} else {
			$("input[type=checkbox][name='c1']").prop("checked", false);
			checkboxes.style.display = "none";
			chkCompanyString();
		}
	}
	function checkboxesplatoon() {
		var checkboxes = document.getElementById("checkboxesplatoon");
		if ($("#platoon_id").prop('checked') == true) {
			checkboxes.style.display = "block";
			checkboxes.style.overflow = "auto";
			checkboxes.style.height = "200px";
			checkboxes.style.width = "200px";
		} else {
			$("input[type=checkbox][name='p1']").prop("checked", false);
			checkboxes.style.display = "none";
			chkString();
		}
	}
	function Validation() {
		if ($("input#institute_name").val().trim() == "") {
			alert("Please Enter Institute Name");
			$("input#institute_name").focus();
			return false;
		}
		if ($("select#institute_type").val() == "0") {
			alert("Please Select Institute Type");
			$("select#institute_type").focus();
			return false;
		}
		if ($("input#btn_arry_id").val() == "") {
			alert("Please Choose Battalion");
			return false;
		}
		if ($("input#company_arr_id").val() == "") {
			alert("Please Choose Company");
			return false;
		}
		if ($("select#status").val() == "inactive") {
			alert("Only Select Active Status");
			$("select#status").focus();
			return false;
		}
		return true;
	}
	document.getElementById("h_id").value = "";
	function battalion_list() {
		var chk_battalion = $("input[name='bn_id']:checked").val();
		$('#checkboxesbattalion').find('input:checkbox[name=b1]').remove();
		$('#checkboxesbattalion').find('label[id=battalion_id_lable]').remove();
		if (chk_battalion != undefined) {
			$
					.post(
							"battalionurl?" + key + "=" + value,
							{},
							function(j) {
								for (var i = 0; i < j.length; i++) {
									$("div#checkboxesbattalion")
											.append(
													'<label for="bat_'+j[i][0]+'" id="battalion_id_lable"  > <input type="checkbox"  name ="b1" id="'
															+ j[i][0]
															+ '"  onclick="chkBtnString()"  value ="'
															+ j[i][0]
															+ '" />'
															+ j[i][1]
															+ '</label>');
								}
							});
		}
	}
	function chkBtnString() {
		var ruleName = new Array();
		$("input:checkbox[name=b1]:checked").each(function() {
			ruleName.push($(this).val());
		});
		document.getElementById('btn_arry_id').value = ruleName;
		company_list();
	}
	function chkCompanyString() {
		var ruleName = new Array();
		$("input:checkbox[name=c1]:checked").each(function() {
			ruleName.push($(this).val());
		});
		document.getElementById('company_arr_id').value = ruleName;
	}
	function company_list() {
		var chk_company = $("input[name='company_id']:checked").val();
		$('#checkboxescompany').find('input:checkbox[name=c1]').remove();
		$('#checkboxescompany').find('label[id=company_id_lable]').remove();
		var btn_arr = $('#btn_arry_id').val().split(',');
		var i = 0;
		$.ajaxSetup({
			async : false
		});
		for (i; i < btn_arr.length; i++) {
			var btn_id = btn_arr[i];
			$
					.post(
							"companyurl?" + key + "=" + value,
							{
								btn_id : btn_id
							},
							function(j) {
								for (var i = 0; i < j.length; i++) {
									$("div#checkboxescompany")
											.append(
													'<label for="com_'+j[i][0]+'"  id="company_id_lable"> <input type="checkbox"  name ="c1" id="'
															+ j[i][0]
															+ '" onclick="chkCompanyString()" value ="'
															+ btn_id
															+ '_'
															+ j[i][0]
															+ '" />'
															+ j[i][1]
															+ '</label>');
								}
								var comlist = $('#company_arr_id').val().split(
										',');
								for (k = 0; k < comlist.length; k++) {
									$(
											"input[type=checkbox][name='c1'][value='"
													+ comlist[k] + "']").prop(
											"checked", true);
								}
							});
		}
		chkCompanyString();
	}
	function editData(id, name, bn_id, com_id, pla_id, type) {
		$.ajaxSetup({
			async : false
		});
		$("#id").val(id);
		$("#institute_name").val(name);
		$("#institute_type").val(type);
		// 	var b_c_p_id="";
		if (bn_id != "") {
			$('#bn_id').prop('checked', true);
			battalion_list();
			$('#btn_arry_id').val(bn_id);
			checkboxesbattalion();
			// 			b_c_p_id+=bn_id;
		}
		if (com_id != "") {
			$('#company_id').prop('checked', true);
			company_list();
			$('#company_arr_id').val(com_id);
			checkboxescompany();
		}
		$("input[type=checkbox][name='b1']").prop("checked", false);
		var bnlist = bn_id.split(',');
		for (k = 0; k < bnlist.length; k++) {
			$("input[type=checkbox][name='b1'][value='" + bnlist[k] + "']")
					.prop("checked", true);
		}
		$("input[type=checkbox][name='c1']").prop("checked", false);
		var comlist = com_id.split(',');
		for (k = 0; k < comlist.length; k++) {
			$("input[type=checkbox][name='c1'][value='" + comlist[k] + "']")
					.prop("checked", true);
		}

	}
</script>