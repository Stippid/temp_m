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
<form:form name="Formname" id="Formid" action="office_mstrAction?${_csrf.parameterName}=${_csrf.token}" method="POST" commandName="office_mstrCMD">
<div class="container" align="center">
	<div class="card">
    <div class="card-header"> <h5>OFFICE VERSION : OTHERS</h5></div>
       <div class="card-body card-block cue_text">
<div class="col-md-12" id="divLine" style="display: none;"><span class="line"></span></div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	           <strong style="color: red;">*</strong> <label for="text-input" class="form-control-label">OFFICE VERSION</label>
          </div>
          <div class='col-12 col-md-6'>
	             <form:input type="text" id="office" path="office"  class="form-control" autocomplete='off' maxlength="100" oninput="this.value = this.value.toUpperCase()" ></form:input>
        </div>
    </div>
</div>
<div class="col-md-6">
				<div class="row form-group">
					<div class="col-md-6">
						<label for="text-input" class=" form-control-label"><strong style="color: red;"></strong>Status</label>
					</div>
					<div class="col-md-6">
						<select name="status" id="status" class="form-control">
						<option value="0" >Pending</option>
						<option value="1" >Approved</option>
						</select>
					</div>

				</div>
			</div>
    </div>
       <div class='card-footer' align='center'>
             <a href=Office_mstr_Other_Url class="btn btn-success btn-sm">Clear</a>
<!--            <input type='submit' class='btn btn-primary btn-sm' value='Save'  onclick='return isValidateClientSide()'> -->
          <i class="fa fa-search"></i><input type="button" class="btn btn-info btn-sm" id="btn-reload" value="Search">
       </div>
    </div>
  </div>
</form:form>




<div class="container" align="center">
  <div  class="watermarked" data-watermark="" id="divwatermark"><span id="ip"></span>
				   <input type="hidden" id="CheckVal" name="CheckVal">
				 
				   <b><input type=checkbox id='nSelAll' name='tregn'
						onclick='setselectall();'>Select all [<span id="tregn">0</span><span
							id='nTotRow1'>/</span><span id="tregnn"> ${list.size()}</span>]</b> 
<table id="office_mstr_reporttbl" class="display table no-margin table-striped  table-hover  table-bordered report_print">
	<thead>
        <tr>
<th>OFFICE Version</th>
     <th>Select To Approve/Reject</th>
    </tr>
	</thead>
	</table>
	 <input type="button" class="btn btn-success btn-sm" value="Approve"
							onclick="return setApproveStatus();">
							<input type="button" class="btn btn-success btn-sm" value="Delete"
							onclick="return setDeleteStatus();">
</div>
</div>
 <script>


$(document).ready(function () {

mockjax1('office_mstr_reporttbl');
table = dataTable('office_mstr_reporttbl');
$('#btn-reload').on('click', function(){
	table.ajax.reload();
});


});
function data(tableName){
	jsondata = [];
	var table = $('#office_mstr_reporttbl').DataTable();
	var info = table.page.info();
	var office =$("#office").val();
	var status=$("#status").val();
	
	var currentPage = info.page;
    var pageLength = info.length;
	var startPage = info.start;
	var endPage = info.end;
	var Search = table.search();
	var order = table.order();
	var orderColunm = order[0][0] + 1;
	var orderType = order[0][1];

	$.post("getOffice_mstr_Other_ReportDataList?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,orderType:orderType,office:office,status:status},function(j) {
		for (var i = 0; i < j.length; i++) {
			var data= [j[i].office,
				j[i].chekboxaction ];
            jsondata.push(data);
		}
		$("#nSelAll").prop('checked', false);
		$('#tregn').text("0");
		$('#tregnn').text(j.length);
	});
	$.post("getOffice_mstr_other_TotalCount?"+key+"="+value,{Search:Search,office:office,status:status},function(j) {
		FilteredRecords = j;
	});
}
 function isValidateClientSide()
  {
	
  if($("input#office").val().trim() == "")
   {
	  alert("Please Enter Office Version");
	  $("input#office").focus();
	  return false;
   } 
  return true; 
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

	function setApproveStatus(){
		
		findselected();
		
		var a = $("#CheckVal").val();

		if(a == ""){
			alert("Please Select the Data for Approval"); 
		}else{

				$.post("Approve_OfficeotherData?"+key+"="+value, {a:a,status:"1"}).done(function(j) {
				alert(j);
				location.reload();
				Search();
			}); 
		}	
	}
	
	function setDeleteStatus(){
		
		findselected();
		
		var a = $("#CheckVal").val();

		if(a == ""){
			alert("Please Select the Data for Rejection."); 
		}else{

				$.post("Delete_OfficeotherData?"+key+"="+value, {a:a}).done(function(j) {
				alert(j);
				location.reload();
			}); 
		}	
	}

	function setRejectStatus(){
		
		findselected();
		
		var a = $("#CheckVal").val();

		if(a == ""){
			alert("Please Select the Data for Reject"); 
		}else{

				$.post("Approve_ComputingAssetsData?"+key+"="+value, {a:a,status:"3"}).done(function(j) {
				alert(j);
				location.reload();
			}); 
		}	
	}

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
 </script>
