
<%@ taglib prefix="dandelion" uri="http://github.com/dandelion" %>
<%
	HttpSession sess = request.getSession(false);
	if (sess.getAttribute("userId") == null) { response.sendRedirect("/login"); return; } 
%>
<dandelion:asset cssExcludes="datatables"/>
<dandelion:asset jsExcludes="datatables"/>
<dandelion:asset jsExcludes="jquery"/>

  <head>
	<title>Document Type Details</title>
		<link href="js/JS_CSS/jquery.dataTables.min.css" rel="stylesheet"> 
		<script src="js/JS_CSS/jquery-3.3.1.js"></script>
		<script src="js/JS_CSS/jquery.dataTables.js"></script>
		<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
		
		<link rel="stylesheet" href="js/cue/cueWatermark.css">
        <script src="js/cue/cueWatermark.js"></script>
		<script src="js/cue/printAllPages.js" type="text/javascript"></script>
		<script>
			var username="${username}";
			var curDate = "${curDate}";
		</script>




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
<link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css">

<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>

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

 				
	<style type="text/css">
	.dataTable{
	    position: relative;
	    z-index: -2;	
	}
		table.dataTable, table.dataTable th, table.dataTable td {
			-webkit-box-sizing: content-box;
			-moz-box-sizing: content-box;
			box-sizing: content-box;
			width: auto; 
			text-align: left;
			font-size: 12px;
			padding: 0px;
			font-weight: bold;
		}
		.dataTables_scrollHead {
			/* overflow-y:scroll !important; */
			overflow-x:hidden !important;
		}
		.DataTables_sort_wrapper{
			 width : auto;  
		}

		table.dataTable tr.odd {
	  			background-color: #f0e2f3;
		}
		table.dataTable thead {
  			background-color: #9c27b0;
  			color: white;
  			font-weight: bold;
		}
		
		.dataTables_paginate.paging_full_numbers{
			margin-top: 0.755em;
		}
		.dataTables_paginate.paging_full_numbers a{
			background-color: #9c27b0;
			border: 1px solid #9c27b0;
			color: #fff;
			border-radius: 5px;
			padding: 3px 10px;
			margin-right: 5px;
		}
		.dataTables_paginate.paging_full_numbers a:hover{
			background-color: #cb5adf;
			border-color: #cb5adf;
		}
		.dataTables_info{
			color:#9c27b0 !important;
			font-weight: bold;
		}
		
		.search_btn{
		  margin:0 auto;
		}
		#btn-reload input{
		  background-color: #007bff;
          border-color: #007bff;
		}

		#btn-reload input:hover{
		background-color: #0069d9;
			border-color: #0069d9;
		}
		
		.watermarked::before{
		  opacity: 0.4;
		}
		.mt-1 {
			margin-top: 1rem;
		}
		.card-footer, .card-header {
		    padding: 0.35rem 1.25rem;
		 }
		 .card-header{
		 display:none;
		 }
	</style>
	</head>	
	<div class="animated fadeIn">
	<div class="mt-1">
		<div class="col-md-12" >
			<div class="card">		
				<div class="card-header" id="heading1" align="center"> <strong style="text-decoration: underline;"> DETAILS OF WE/PE NO</strong> 
	    			</div>	
	    			<div class="card-header" id="heading2" align="center"> <strong style="text-decoration: underline;"> DETAILS OF WET/PET NO</strong> 
	    			</div>
	    			
	    			
	    			<div class="mt-1">
	    			<div class="row form-group" >
						 <div class="col-md-6">
	             		 <div class="row form-group">	
		                 <div class="col-md-4">
	                 		  <label for="text-input" class=" form-control-label">Created By </label>
	               		 </div>	               		 
	               		 <div class="col-md-8">
	             			  <select name="cr_by" id="cr_by" class="form-control-sm form-control"   >
												<option value="">--Select--</option>
												<c:forEach var="item" items="${getRoleNameList_dash}" varStatus="num">
												<option value="${item.userId}"  name="${item.userName}">${item.userName}</option>
												</c:forEach>
											</select> 
	             			
	             			
	             			
	               		 </div>	
	               		 </div>
	               		 </div>
							<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col col-md-3 ">
						                    <label class=" form-control-label">Created Date: </label>
						                </div>
						                <div class="col-md-8">
						               <input type="text" name="crDate" id="crDate" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  max="${date}">
						                </div>
						            </div>
	              				</div>
						</div>
		
		
				
		  	<div class="card-footer">
			<div align="center" class="search_btn">
			<a href="DocTypeReportWetPetFet?type=WEWET" class="btn btn-success btn-sm" >Clear</a>  
<!-- 			<input type="button" class="btn btn-success btn-sm" id="btn1" value="Clear" onclick="return clearAll();">					 -->
								
			<i class="fa fa-search"></i>
			<input type="button" class="btn btn-primary btn-sm" id="btn-reload" value="Search"/>  
		</div>
		</div>
						</div>
						</div>
					</div>
					</div>
					<br>
					
				<br>
				<div class="card-body" id="table1" style="display:none">
					<div class="datatablediv" id="reportDiv">
					
						<div class="">
							<div class="watermarked" data-watermark="" id="divwatermark">
								<span id="ip"></span>
								<table id="getSearchWEPE" 
							class="table no-margin table-striped  table-hover  table-bordered" >
									<thead style="font-size: 15px; text-align: center;">
										<tr>
											<th>WE/PE NO</th>
											<th>CREATED BY</th>
											<th>CREATED DATE</th>
											<th>MODIFIED BY</th>
											<th>MODIFIED DATE</th>
<!-- 											<th>APPROVEED BY</th> -->
<!-- 											<th>APPROVEED DATE</th> -->
										</tr>
									</thead>
									<tbody>
										<c:if test="${list.size() == 0}">
											<tr>
												<td colspan="12" align="center" style="color: red;">Data
													Not Available</td>
											</tr>
										</c:if>
									</tbody>
								</table>

							</div>
						</div>
					</div>
				</div>
			
			
			<div class="card-body" id="table2" style="display:none">
					<div class="datatablediv" id="reportDiv">
					
						<div class="">
							<div class="watermarked" data-watermark="" id="divwatermark">
								<span id="ip"></span>
								<table id="getSearchWETPET" 
								class="table no-margin table-striped  table-hover  table-bordered" >
									<thead style="font-size: 15px; text-align: center;">
										<tr>
											<th>WET/PET NO</th>
											<th>CREATED BY</th>
											<th>CREATED DATE</th>
											<th>MODIFIED BY</th>
											<th>MODIFIED DATE</th>
<!-- 											<th>APPROVEED BY</th> -->
<!-- 											<th>APPROVEED DATE</th> -->
										</tr>
									</thead>
									<tbody>
										<c:if test="${list.size() == 0}">
											<tr>
												<td colspan="12" align="center" style="color: red;">Data
													Not Available</td>
											</tr>
										</c:if>
									</tbody>
								</table>

							</div>
						</div>
					</div>
				</div>
					<input type="hidden" id="CheckValNC" name="CheckValbde">
				

<script>
var key = "${_csrf.parameterName}";
var value = "${_csrf.token}"; 
$(document).ready(function() {
	$.ajaxSetup({
		async : false
	});
	jQuery(function($){ 
        
      
        datepicketDate('crDate');
   
	});	
	
	var type ='${pageType}';
	if(type=="WE"){
		$("#heading1").show();
		$("#table1").show();
		mockjax1('getSearchWEPE');
		table = dataTable11('getSearchWEPE');
		$('#btn-reload').on('click', function(){
		    table.ajax.reload();
		   
		});
	}
	if(type=="WET"){
		$("#heading2").show();
		$("#table2").show();

		mockjax1('getSearchWETPET');
		table1 = dataTable11('getSearchWETPET');
		$('#btn-reload').on('click', function(){
		    table1.ajax.reload();
		});
	}

});






function data(tableName) {
	jsondata = [];

	var table = $('#'+tableName).DataTable();
	var info = table.page.info();
	var currentPage = info.page;
	var pageLength = info.length;
	var startPage = info.start;
	var endPage = info.end;
	var Search = table.search();
	var order = table.order();
	var orderColunm = order[0][0] + 1;
	var orderType = order[0][1];
	var cr_by = $("#cr_by").val();
	var crDate = $("#crDate").val();


	if (tableName == "getSearchWEPE") {
	
	
	    $.post("getDocTypeDetaisWEPEcount?" + key + "=" + value,{
	    	Search:Search,
	    	cr_by:cr_by,
	    	crDate:crDate
	    
			
		 },  function(j) {
			FilteredRecords = j;
		});
		$.post("getDocTypeDetaisWEPElist?" + key + "=" + value, {
		
			startPage : startPage,
			pageLength : pageLength,
			Search:Search,
			orderColunm : orderColunm,
			cr_by:cr_by,
	    	crDate:crDate
		
			
		}, function(j) {

			for (var i = 0; i < j.length; i++) {
				var sr = i + 1;
					
				jsondata.push([j[i].we_pe_no, j[i].created_by, j[i].created_on, j[i].modified_by, j[i].modified_on]);
				
				
			}
		});
	}
	
	if (tableName == "getSearchWETPET") {
		
		
	    $.post("getDocTypeDetaisWETPETcount?" + key + "=" + value,{
	    	Search:Search,
	    	cr_by:cr_by,
	    	crDate:crDate
			
		 },  function(j) {
			FilteredRecords = j;
		});
		$.post("getDocTypeDetaisWETPETlist?" + key + "=" + value, {
		
			startPage : startPage,
			pageLength : pageLength,
			Search:Search,
			orderColunm : orderColunm,
			cr_by:cr_by,
	    	crDate:crDate
		
			
		}, function(j) {

			for (var i = 0; i < j.length; i++) {
				var sr = i + 1;
					
				jsondata.push([j[i].wet_pet_no, j[i].created_by, j[i].created_on, j[i].modified_by, j[i].modified_on]);
				
				
			}
		});
	}
}




</script>

