<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<!-- <link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css"> -->
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
<form:form name="Formname" id="Formid" action="ram_mstrAction?${_csrf.parameterName}=${_csrf.token}" method="POST" commandName="ram_mstrCMD">
<div class="container" align="center">
	<div class="card">
    <div class="card-header"> <h5>RAM CAPACITY MASTER</h5></div>
       <div class="card-body card-block cue_text">
<div class="col-md-12" id="divLine" style="display: none;"><span class="line"></span></div>
 <div class='col-md-12'>
	 
	 <div class='col-md-2'>
	           <strong style="color: red;">*</strong> <label for="text-input" class="form-control-label">RAM CAPACITY</label>
          </div>
          <div class='col-md-3'>
	             <input type="text" id="ram" name="ram"  class="form-control number" autocomplete='off' maxlength="10"
	              oninput="this.value = this.value.toUpperCase()"  onkeypress="return digitKeyOnly(event)"  />
        </div>
        <div class='col-md-2'>
        <select name="size" id="size" class="form-control">
											<option value="0" name="MB" >--Select--</option>
											   <option value="MB" value="MB">MB</option>
											    <option value="GB" value="GB">GB</option>
											    <option value="TB" value="TB">TB</option>
										 </select>
         </div>
	 
	     <%--  <div class='col col-md-4'>
	            <strong style="color: red;">*</strong> <label for="text-input" class="form-control-label">RAM CAPACITY</label>
          </div>
          <div class='col-12 col-md-6'>
	             <form:input type="text" id="ram" path="ram"  class="form-control" autocomplete='off' maxlength="50" oninput="this.value = this.value.toUpperCase()"></form:input>
        </div> --%>
  
</div>
    </div>
       <div class='card-footer' align='center'>
       <a href=Ram_mstr_Url class="btn btn-success btn-sm">Clear</a>
           <input type='submit' class='btn btn-primary btn-sm' value='Save'  onclick='return isValidateClientSide()'>
        <i class="fa fa-search"></i><input type="button" class="btn btn-info btn-sm" id="btn-reload" value="Search">
       </div>
    </div>
  </div>
</form:form>



<c:url value="EditRam_mstrUrl" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid">
<input type="hidden" name="updateid" id="updateid" />
</form:form>


<c:url value="deleteram_mstrUrl" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm" name="deleteForm" modelAttribute="deleteid">
<input type="hidden" name="deleteid" id="deleteid"/>
</form:form>

<c:url value="RAMmastereport" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
	<input type="hidden" name="ram1" id="ram1" value="0" />
	<input type="hidden" name="size1" id="size1" value="0" />
</form:form> 

<div align="right" class="container">
		<i class="fa fa-file-excel-o" id="btnExport"
			style="font-size: x-large; color: green; text-align: right;"
			aria-hidden="true" title="EXPORT TO EXCEL" onclick="getExcel()"></i>
	</div>
	<br>
<div class="container" align="center">
<div class="col-md-12">
<table id="ram_mstr_reporttbl" class="display table no-margin table-striped  table-hover  table-bordered report_print">
	<thead>
        <tr>
<th>RAM Capacity</th>
          <th>Action</th>
    </tr>
	</thead>
	</table>
</div>
</div>
 <script>


$(document).ready(function () {

mockjax1('ram_mstr_reporttbl');
table = dataTable('ram_mstr_reporttbl');
$('#btn-reload').on('click', function(){
	table.ajax.reload();
});

$(".number").on('keypress',function(e){
	return !isNaN(e.key);
})

});
function data(ram_mstr_reporttbl){
	jsondata = [];
	var table = $('#ram_mstr_reporttbl').DataTable();
	var info = table.page.info();
	
		//var e = document.getElementById("size");
	  
		var ram_capacity = $("#size").val() ;
	
	//alert(size);
	var ram =$("#ram").val(); // +" "+ size ;
	//var ram_capacity = ram + size;
	var currentPage = info.page;
    var pageLength = info.length;
	var startPage = info.start;
	var endPage = info.end;
	var Search = table.search();
	var order = table.order();
	//var orderColunm = $(table.column(order[0][0]).header()).attr('id');
	var orderColunm = order[0][0] + 1;
	var orderType = order[0][1];

	$.post("getRam_mstrReportDataList?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,orderType:orderType,ram:ram,ram_capacity:ram_capacity},function(j) {
		for (var i = 0; i < j.length; i++) {
			jsondata.push([j[i].ram,j[i].id]);
		}
	});
	$.post("getRam_mstrTotalCount?"+key+"="+value,{Search:Search,ram:ram,ram_capacity:ram_capacity},function(j) {
		FilteredRecords = j;
	});
}
 function isValidateClientSide()
  {
	
  if($("input#ram").val().trim() == "")
   {
	  alert("Please Enter RAM Capacity");
	  $("input#ram").focus();
	  
	  return false;
	   }
  if($("#size").val() == 0)
  {
	  alert("Please Select Size");
	  $("input#size").focus();
	  
	  return false;
	   }
  return true;
  }
    

 function editData(obj){

	    document.getElementById('updateid').value=obj;
	    document.getElementById('updateForm').submit();
}


 function deleteData(obj){

	    document.getElementById('deleteid').value=obj;
	    document.getElementById('deleteForm').submit();
}
 
 function digitKeyOnly(e) {
	  var keyCode = e.keyCode == 0 ? e.charCode : e.keyCode;
	  var value = Number(e.target.value + e.key) || 0;
	  
	  /* var charCode = e.which || e.keyCode;
	    var charStr = String.fromCharCode(charCode);
	    || charStr != "&" || charStr != "%" */

	  if ((keyCode >= 37 && keyCode <= 40) || (keyCode == 8 || keyCode == 9 || keyCode == 13) || (keyCode >= 48 && keyCode <= 57 || keyCode == 46) ) {
		  //console.log(charStr);
	    return true;
	  }
	  return false;
	}
 
	function getExcel() {
		
		var ram = $("#ram").val() ;
		
		$("#ram1").val(ram);
	 	
	 	document.getElementById('typeReport1').value = 'excelL';
	 	document.getElementById('searchForm').submit();
	} 
 </script>
