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
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
<link href="js/cue/cueWatermark.css" rel="Stylesheet"></link>
<link rel="stylesheet" href="js/InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="js/InfiniteScroll/js/datatables.min.js"></script>
<script type="text/javascript" src="js/InfiniteScroll/js/jquery.mockjax.min.js"></script>
<script type="text/javascript" src="js/InfiniteScroll/js/datatables.mockjax.js"></script>


<c:if test='${not empty msg}'>
	<input type='hidden' name='msg' id='msg' value='${msg}' />
</c:if>
<form:form name="Formname" id="Formid" action="Management_Layer_Action?${_csrf.parameterName}=${_csrf.token}"
	method="POST" commandName="Management_Layer_CMD">
	<div class="container" align="center">
		<div class="card">
			<div class="card-header">
				<h5>MANAGEMENT LAYER MASTER</h5>
			</div>
			<div class="card-body card-block cue_text">
				<div class="col-md-12" id="divLine" style="display: none;">
					<span class="line"></span>
				</div>
				<div class='col-md-8'>
					<div class='row form-group'>
						<div class='col col-md-4'>
							<strong style="color: red;">*</strong> <label for="text-input" class="form-control-label">MANAGEMENT LAYER
							</label>
						</div>
						<div class='col-md-6'>
							<form:input type="text" id="management_layer"
								path="management_layer" class="form-control" autocomplete='off'
								maxlength="50" oninput="this.value = this.value.toUpperCase()"></form:input>
						</div>
					</div>
				</div>
			</div>
			<div class="card-footer" align="center" class="form-control">
				<a href="MANAGEMENT_LAYER_Url" class="btn btn-success btn-sm">Clear</a>
				<input type='submit' class='btn btn-primary btn-sm' value='Save' >
				 <i class="fa fa-search"></i><input type="button" class="btn btn-info btn-sm" id="btn-reload" value="Search">
			</div>
		</div>
	</div>
</form:form>



<c:url value="EditManagement_Layer_Url" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="updateForm"
	name="updateForm" modelAttribute="updateid">
	<input type="hidden" name="updateid" id="updateid" />
</form:form>


<c:url value="Delete_Management_Layer_Url" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="deleteid">
	<input type="hidden" name="deleteid" id="deleteid" />
</form:form>
<c:url value="MGMTmastereport" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
	<input type="hidden" name="management_layer1" id="management_layer1" value="0" />
	

</form:form> 

 <div align="right" class="container">
		<i class="fa fa-file-excel-o" id="btnExport"
			style="font-size: x-large; color: green; text-align: right;"
			aria-hidden="true" title="EXPORT TO EXCEL" onclick="getExcel();"></i>
	</div>
	<br>
<div class="container" align="center">
	<div class="col-md-12">
		<table id="Management_Layer_mstr_reporttbl"
			class="display table no-margin table-striped  table-hover  table-bordered report_print">
			<thead>
				<tr>
					<th>Management Layer</th>
					<th>Action</th>
				</tr>
			</thead>
		</table>
	</div>
</div>
<script>
	$(document).ready(function() {

		mockjax1('Management_Layer_mstr_reporttbl');
		table = dataTable('Management_Layer_mstr_reporttbl');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});

	});
	function data(tableName) {
		jsondata = [];
		var table = $('#Management_Layer_mstr_reporttbl').DataTable();
		var info = table.page.info();

		var management_layer = $("#management_layer").val();
		
		var currentPage = info.page;
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = order[0][0] + 1;
		var orderType = order[0][1];

		$.post("getManagement_Layer_mstrReportDataList?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			management_layer:management_layer
		}, function(j) {
			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i].management_layer, j[i].id ]);
			}
		});
		$.post("getManagement_Layer_mstrTotalCount?" + key + "=" + value, {
			Search : Search,
			management_layer:management_layer
		}, function(j) {
			FilteredRecords = j;
		});
	}
	function isValidateClientSide() {

		if ($("input#management_layer").val().trim() == "") {
			alert("Please Enter Management Layer");
			$("input#management_layer").focus();
			return false;
		}
		return true;
	}

	function editData(obj) {

		document.getElementById('updateid').value = obj;
		document.getElementById('updateForm').submit();
	}

	function deleteData(obj) {

		document.getElementById('deleteid').value = obj;
		document.getElementById('deleteForm').submit();
	}
	
function getExcel() {
		
		var management_layer=$("#management_layer").val();
		$("#management_layer1").val(management_layer);
	 
		document.getElementById('typeReport1').value = 'excelL';
		document.getElementById('searchForm').submit();
	} 
</script>
