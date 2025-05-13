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
<form:form name="Formname" id="Formid" action="asset_mstrAction?${_csrf.parameterName}=${_csrf.token}" method="POST" commandName="asset_mstrCMD">
<div class="container" align="center">
	<div class="card">
    <div class="card-header"> <h5>ASSETS CATEGORY MASTER</h5></div>
       <div class="card-body card-block cue_text">
<div class="col-md-12" id="divLine" style="display: none;"><span class="line"></span></div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	           <strong style="color: red;">*</strong> <label for="text-input" class="form-control-label">CATEGORY</label>
          </div>
          <div class='col-12 col-md-6'>
	              <form:select id="category" path="category"  class="form-control">
	              <option value="0">--Select--</option>
										<option value="1">COMPUTING</option>
										<option value="2">PERIPHERAL</option>	
	              </form:select>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	           <strong style="color: red;">*</strong> <label for="text-input" class="form-control-label">ASSET NAME</label>
          </div>
          <div class='col-12 col-md-6'>
	             <form:input type="text" id="assets_name" path="assets_name"  class="form-control" autocomplete='off' maxlength="50" oninput="this.value = this.value.toUpperCase()" ></form:input>
        </div>
    </div>
</div>


    </div>
    
       <div class='card-footer' align='center'>
     <a href=Asset_mstr_Url class="btn btn-success btn-sm">Clear</a>
           <!-- <input type='reset' class='btn btn-success btn-sm' value='Clear' onclick='clearall()'> -->
           <input type='submit' class='btn btn-primary btn-sm' value='Save'  onclick='return isValidateClientSide()'>
         <i class="fa fa-search"></i><input type="button" class="btn btn-info btn-sm" id="btn-reload" value="Search">
       </div>
    </div>
  </div>
</form:form>



<c:url value="EditAsset_mstrUrl" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid">
<input type="hidden" name="updateid" id="updateid" />
</form:form>


<c:url value="deleteasset_mstrUrl" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm" name="deleteForm" modelAttribute="deleteid">
<input type="hidden" name="deleteid" id="deleteid"/>
</form:form>
<c:url value="Assetsmastereport" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
	<input type="hidden" name="assets_name1" id="assets_name1" value="0" />
	<input type="hidden" name="category1" id="category1" value="0" />
</form:form> 


<div align="right" class="container">
		<i class="fa fa-file-excel-o" id="btnExport"
			style="font-size: x-large; color: green; text-align: right;"
			aria-hidden="true" title="EXPORT TO EXCEL" onclick="getExcel();"></i>
	</div>
	<br>
<div class="container" align="center">
<div class="col-md-12">
<table id="asset_mstr_reporttbl" class="display table no-margin table-striped  table-hover  table-bordered report_print">
	<thead>
        <tr>
        <th>Category </th>
        <th>Assets Name </th>
          <th>Action</th>
    </tr>
	</thead>
	</table>
</div>
</div>
 <script>


$(document).ready(function () {

	mockjax1('asset_mstr_reporttbl');
	table = dataTable('asset_mstr_reporttbl');
	$('#btn-reload').on('click', function(){
    	table.ajax.reload();
    });
	



});
function data(asset_mstr_reporttbl){
	jsondata = [];
	var table = $('#'+asset_mstr_reporttbl).DataTable();
	var info = table.page.info();

	var assets_name =$("#assets_name").val();
	var category = $("#category").val();

	var currentPage = info.page;
    var pageLength = info.length;
	var startPage = info.start;
	var endPage = info.end;
	var Search = table.search();
   var order = table.order();
	var orderColunm = order[0][0] + 1;
	var orderType = order[0][1];

	$.post("getAsset_mstrReportDataList?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,orderType:orderType,assets_name: assets_name,category : category},function(j) {
		for (var i = 0; i < j.length; i++) {
			jsondata.push([j[i].category,j[i].assets_name,j[i].id]);
		}
	});
	$.post("getAsset_mstrTotalCount?"+key+"="+value,{Search:Search,	assets_name: assets_name,
		category : category},function(j) {
		FilteredRecords = j;
	});
}

		function isValidateClientSide() {

			if ($("#category").val() == 0) {
				alert("Please Select Category");
				$("#category").focus();
					return false;
				}
			if ($("input#assets_name").val().trim() == "") {
			alert("Please Enter Asset Name");
			$("#assets_name").focus();
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
			var category=$("#category").val();
			var assets_name=$("#assets_name").val();
		
			$("#category1").val(category);
		 	$("#assets_name1").val(assets_name);
		 	
			document.getElementById('typeReport1').value = 'excelL';
			document.getElementById('searchForm').submit();
		} 
		
	</script>
