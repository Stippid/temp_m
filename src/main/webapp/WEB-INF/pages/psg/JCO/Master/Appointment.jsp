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
<link href="js/cue/cueWatermark.css" rel="Stylesheet"></link>

<form:form name="Appointment" id="Appointment" action="ApptAction?${_csrf.parameterName}=${_csrf.token}" method="post" class="form-horizontal" commandName="ApptCmd">
	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<h5>ADD/UPDATE APPOINTMENT</h5>
					<h6 class="enter_by">
						<span style="font-size: 12px; color: red;">(To be entered by MISO)</span>
					</h6>
				</div>
				<div class="card-body card-block">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong> APPOINTMENT</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="appointment" name="appointment" oninput="this.value = this.value.toUpperCase()" maxlength="100" class="form-control autocomplete" autocomplete="off">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong> CATEGORY</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="category" name="category" oninput="this.value = this.value.toUpperCase()" maxlength="50" class="form-control autocomplete" autocomplete="off">
								</div>
							</div>
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
										<c:forEach var="item" items="${getStatusMasterList}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="card-footer" align="center" class="form-control">
					<a href="Appointment_Url" class="btn btn-success btn-sm">Clear</a>
					<input type="submit" class="btn btn-primary btn-sm" value="Save" onclick="return Validate();"> 
					<i class="action_icons searchButton"></i><input type="button" class="btn btn-info btn-sm" onclick="Search();" value="Search">
				</div>
			</div>
		</div>
	</div>
	<div align="right" class="container">
		<i class="fa fa-file-excel-o" id="btnExport" style="font-size: x-large; color: green; text-align: right;" aria-hidden="true" title="EXPORT TO EXCEL" onclick="getExcel();"></i>
	</div>
	<br>
	<div class="container" id="appt_search">
		<div class="">
			 <div id="divShow" style="display: block;"></div>
			<div class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
				<span id="ip"></span>
				<table id="appt_Search" class="table no-margin table-striped  table-hover  table-bordered report_print" width = "100%">
						<thead style="color: white;text-align: center;">
							<tr>
								<th style="font-size: 15px ;width: 10%;">Ser No</th>
								<th style="font-size: 15px">Appointment</th>
								<th style="font-size: 15px">Category</th>
								<th style="font-size: 15px ;width: 20%;">Action</th>
							</tr>
						</thead>
						<tbody >
						<c:if test="${list.size()==0}">
								<tr>
									<td style="font-size: 15px; text-align: center; color: red;" colspan="3">Data Not Available</td>
								</tr>
							</c:if>
							<c:forEach var="item" items="${list}" varStatus="num" >
								<tr>
									<td style="font-size: 15px;text-align: center ;width: 10%;">${num.index+1}</td> 
									<td style="font-size: 15px;">${item[0]}</td>
									<td style="font-size: 15px;">${item[1]}</td>
									<td style="font-size: 15px;text-align: center ;width: 20%;">${item[2]} ${item[3]}</td> 
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div> 
		</div>
</form:form>
 <c:url value="search_Appt" var="search_Appt" />
<form:form action="${search_Appt}" method="post" id="searchForm" name="searchForm" modelAttribute="appointment1">
	<input type="hidden" name="appointment1" id="appointment1" />
	<input type="hidden" name="category1" id="category1" />
	<input type="hidden" name="status1" id="status1" value="0" />
</form:form>
<c:url value="edit_Appt" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="id">
	<input type="hidden" name="updateid" id="updateid" value="0" />
</form:form>
<c:url value="Appt_delete" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm" name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>
<c:url value="Apptreport" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2" name="search2" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form> 
<script>
	function Validate() {
		if ($("input#appointment").val().trim() == "") {
			alert("Please Enter Appointment");
			$("input#appointment").focus();
			return false;
		}
		if ($("input#category").val().trim() == "") {
			alert("Please Enter Category");
			$("input#category").focus();
			return false;
		}
	 if ($("select#status").val() == "inactive") {
			alert("Only Select Active Status");
			$("select#status").focus();
			return false;
		}
		return true;
	}
	function Search() {
		$("#appointment1").val($('#appointment').val());
		$("#category1").val($('#category').val());
		$("#status1").val($('#status').val());
		document.getElementById('searchForm').submit();
	}
	function editData(id) {
		document.getElementById('updateid').value = id;
		document.getElementById('updateForm').submit();
	}
	function deleteData(id) {
		$("#id1").val(id);
		document.getElementById('deleteForm').submit();
	}
	$(document).ready(function() {
		if ('${list.size()}' == "") {
			$("div#appt_search").hide();
		}
		if ('${appointment1}' != "") {
			$("#appointment").val('${appointment1}');
		}
		if ('${category1}' != "") {
			$("#category").val('${category1}');
		}
		if ('${status1}' != "") {
			$("Select#status").val('${status1}');
		}
	});
	function getExcel() {
		document.getElementById('typeReport1').value = 'excelL';
		document.getElementById('search2').submit();
	}
</script>
