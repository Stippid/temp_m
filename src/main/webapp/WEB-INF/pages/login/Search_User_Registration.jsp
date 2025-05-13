<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="js/common/jquery-3.4.1.js" ></script>
<link rel="stylesheet" href="js/InfiniteScroll/css/datatables.min.css">
<script src="js/InfiniteScroll/js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="js/InfiniteScroll/js/datatables.min.js"></script>
<script type="text/javascript" src="js/InfiniteScroll/js/jquery.mockjax.min.js"></script>
<script type="text/javascript" src="js/InfiniteScroll/js/datatables.mockjax.js"></script>

<div class="container" align="center">
	<div class="card">			
		<div class="card-header"><h5>Search User Registration</h5></div>
		<div class="card-body card-block">
			<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-6">
								<label for="text-input" class=" form-control-label"> IC No. </label>
							</div>
							<div class="col-md-6">
								<input type="text" id="ic_no" name="ic_no" class="form-control" autocomplete="off" >
			                </div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-6">
								<label for="text-input" class=" form-control-label"> Status </label>
							</div>
							<div class="col-md-6">
							<select name="status" id="status" class="form-control-sm form-control">
								<option value="0">Pending</option>
								<option value="1">Approved</option>

							</select>
						</div>
						</div>
					</div>
				</div>
		</div> 
		<div class="card-footer" align="center">
           	<a href="Search_User_regUrl" class="btn btn-primary btn-sm" >Clear</a> 
          	<i class="fa fa-search"></i><input type="button" class="btn btn-info btn-sm" onclick="search();" id="srch" value="Search"> 
          	<a href="commonDashboard" class="btn btn-danger btn-sm">Cancel</a>		 
		</div> 
	</div>
</div>
<div class="col-md-12" id="userist" align="center">
	<table id="User_Regi" class="display">
		<thead>
			<tr>
				<th width="5%" style="POINTER-EVENTS: none;background-image: none;text-align:center;">Ser No</th>
				<th width="15%">User Name</th>
				<th width="15%">Unit name</th>
				<th width="8%">Sus No</th>
				<th width="8%">IC No</th>
				<th width="10%">Rank</th>
				<th width="10%">Appointment</th>
				<th width="10%" style="text-align:center;">Created On</th>
				<th width="10%">User Id</th>
				<th width="10%" style="text-align:center;">Action</th>
			</tr>
		</thead>
	</table>
</div>




<c:url value="view_user_registration" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>

<script>
$(document).ready(function() {
	mockjax1('User_Regi');
	table = dataTable('User_Regi',[0,7,9],[]);
	$('#srch').on('click', function(){
    	table.ajax.reload();
    });
});
function viewData(id){
	$("#id1").val(id);
    document.getElementById('updateForm').submit();
}
function data(){
	var table = $('#User_Regi').DataTable();
	var info = table.page.info();
	var currentPage = info.page;
	var pageLength = info.length;
	var startPage = info.start;
	var endPage = info.end;
	var Search = table.search();
	var order = table.order();
	console.log(order);
	var orderColunm ='id';
	var orderType = order[0][1];
	jsondata = [];
	var ic_no = $("#ic_no").val();
	var status = $("#status").val();
	$.post("getFilteredUserList_SQL?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,orderType:orderType,
		ic_no:ic_no,status:status},function(j) {
		for (var i = 0; i < j.length; i++) {	
			jsondata.push([j[i].ser,j[i].user_name,j[i].unit_name,j[i].sus_no,j[i].ic_no,j[i].rank,j[i].appointment,j[i].creat,j[i].user_id,j[i].action]);
		}
	});
	$.post("getUserTotalCount_SQL?"+key+"="+value,{Search:Search,ic_no:ic_no,status:status},function(j) {
		FilteredRecords = j;
	});
}
function editData(id){	
	$("input#id1").val(id);
	document.getElementById('updateuser_reg_form').submit();
}
</script>