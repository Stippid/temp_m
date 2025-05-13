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
<input type='hidden' name='msg' id='msg' value='${msg}'/>
</c:if>
<form:form name="frmBudget" id="frmBudget" action="budget_mstrAction?${_csrf.parameterName}=${_csrf.token}" method="POST" commandName="budget_mstrCMD">
<div class="container" align="center">
	<div class="card">
    <div class="card-header"> <h5>BUDGET MASTER</h5></div>
       <div class="card-body card-block cue_text">
<div class="col-md-12" id="divLine" style="display: none;"><span class="line"></span></div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	           <strong style="color: red;">*</strong> <label for="text-input" class="form-control-label">BUDGET HEAD</label>
          </div>
          <div class='col-12 col-md-6'>
	              <form:input type="text" id="budget_head" path="budget_head"  class="form-control" autocomplete='off' maxlength="50" oninput="this.value = this.value.toUpperCase()" ></form:input>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	           <strong style="color: red;">*</strong> <label for="text-input" class="form-control-label">BUDGET CODE</label>
          </div>
          <div class='col-12 col-md-6'>
	             <form:input type="text" id="budget_code" path="budget_code"  class="form-control" autocomplete='off' maxlength="50" oninput="this.value = this.value.toUpperCase()" ></form:input>
        </div>
    </div>
</div>


    </div>
       <div class='card-footer' align='center'>
            <a href=Budget_mstr_Url class="btn btn-success btn-sm">Clear</a>
          				  <input type='submit' class='btn btn-primary btn-sm' value='Save'  onclick='return isValidateClientSide()' />
         				   <i class="fa fa-search"></i><input type="button" class="btn btn-info btn-sm" id="btn-reload" value="Search">
       </div>
    </div>
  </div>
</form:form>



<c:url value="EditBudget_mstrUrl" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid">
<input type="hidden" name="updateid" id="updateid" />
</form:form>


<c:url value="deletebudget_mstrUrl" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm" name="deleteForm" modelAttribute="deleteid">
<input type="hidden" name="deleteid" id="deleteid"/>
</form:form>

<c:url value="Budgetmastereport" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
	<input type="hidden" name="budget_head1" id="budget_head1" value="0" />
	<input type="hidden" name="budget_code1" id="budget_code1" value="" />

</form:form> 

 <div align="right" class="container">
		<i class="fa fa-file-excel-o" id="btnExport"
			style="font-size: x-large; color: green; text-align: right;"
			aria-hidden="true" title="EXPORT TO EXCEL" onclick="getExcel();"></i>
	</div>
	<br>
<div class="container" align="center">
<div class="col-md-12">
<table id="budget_mstr_reporttbl" class="display table no-margin table-striped  table-hover  table-bordered report_print">
	<thead>
        <tr>
        <th>Budget Head </th>
        <th>Budget Code </th>
          <th>Action</th>
    </tr>
	</thead>
	</table>
</div>
</div>
 <script>


$(document).ready(function () {

mockjax1('budget_mstr_reporttbl');
table = dataTable('budget_mstr_reporttbl');
$('#btn-reload').on('click', function(){
	table.ajax.reload();
});


});
function data(tableName){
	jsondata = [];
	var table = $('#budget_mstr_reporttbl').DataTable();
	var info = table.page.info();

	var budget_head = $("#budget_head").val();
	var budget_code = $("#budget_code").val();
	
	var currentPage = info.page;
    var pageLength = info.length;
	var startPage = info.start;
	var endPage = info.end;
	var Search = table.search();
	var order = table.order();
	var orderColunm = order[0][0] + 1;
	var orderType = order[0][1];

	$.post("getBudget_mstrReportDataList?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,orderType:orderType,
		budget_head:budget_head,budget_code:budget_code},function(j) {
		for (var i = 0; i < j.length; i++) {
			jsondata.push([j[i].budget_head,j[i].budget_code,j[i].id]);
		}
	});
	$.post("getBudget_mstrTotalCount?"+key+"="+value,{Search:Search,budget_head:budget_head,budget_code:budget_code},function(j) {
		FilteredRecords = j;
	});
}

		function isValidateClientSide() {

			if ($("input#budget_head").val().trim() == "") {
				alert("Please Enter Budget Head");
				 $("#budget_head").focus();
				return false;
			}
		if ($("input#budget_cost").val().trim() == "") {
			alert("Please Enter Budget Cost");
			 $("#budget_cost").focus();
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
		
			var budget_head=$("#budget_head").val();
			var budget_cost=$("#budget_cost").val();

			$("#budget_head1").val(budget_head);
		 	$("#budget_cost1").val(budget_cost);
		 	
			document.getElementById('typeReport1').value = 'excelL';
			document.getElementById('searchForm').submit();
		} 
	</script>
