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
		<link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css">
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
	</style>	
	</head>
	
	<div class="animated fadeIn">
	<div class="mt-1">
		<div class="col-md-12" >
			<div class="card">		
				<div class="card-header" align="center"> <strong style="text-decoration: underline;"> Comd Wise Auth of Pers</strong> 
	    			</div>	
	    			<div class="mt-1">
	    			<div class="row form-group" >
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-3">
									<label class=" form-control-label"> Command</label>
								</div>
								<div class="col-12 col-md-8">
									<select id="cont_comd" name="cont_comd" class="form-control">
										${selectcomd}
										<c:forEach var="item" items="${getCommandList}"
											varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
							<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-3">
									<label class="form-control-label"> Corps</label>
								</div>
								<div class="col-12 col-md-8">
									<select id="cont_corps" name="cont_corps" class="form-control">
										${selectcorps}
										<c:forEach var="item" items="${getCorpsList}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						</div>
					
				<div class="row form-group" >
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-3">
									<label class=" form-control-label"> Division</label>
								</div>
								<div class="col-12 col-md-8">
									<select id="cont_div" name="cont_div" class="form-control">
										${selectDiv}
										<c:forEach var="item" items="${getDivList}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						
				<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-3">
									<label class=" form-control-label"> Brigade</label>
								</div>
								<div class="col-12 col-md-8">
									<select id="cont_bde" name="cont_bde" class="form-control">
										${selectBde}
										<c:forEach var="item" items="${getBdeList}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						</div>
					<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col col-md-4">
													<label for="text-input" class=" form-control-label">CT Part I/II</label>
												</div>
												<div class="col col-md-8">
													<div class="form-check-inline form-check">
														<label for="inline-radio1" class="form-check-label ">
															<input type="radio" id="radio1" name="ct_part_i_ii" maxlength="8" value="CTPartI" class="form-control form-check-input">CT Part I</label>&nbsp;&nbsp;&nbsp; 
														<label for="inline-radio2" class="form-check-label "> 
															<input type="radio" id="radio1" name="ct_part_i_ii" maxlength="8" value="CTPartII" class="form-control form-check-input">CT Part II</label> &nbsp;&nbsp;&nbsp; 
														<label for="inline-radio3" class="form-check-label "> 
															<input type="radio" id="radio1" name="ct_part_i_ii" maxlength="8" value="Others" class="form-control form-check-input">Others</label>
													</div>
												</div>
											</div>
										</div>
									</div>
	    			<div class="card-body">
	  						<div class="col-md-6">	
	  						<div class="row form-group">           					
             					<div class="col col-md-3">
               						<label class=" form-control-label">Category</label>
             					</div>
             					<div class="col-12 col-md-8">
               					<select id="rank_cat" name="rank_cat" class="form-control" onchange="select_rank_app_trade();select_rank_cat();">
             					<option value="">--Select--</option>
	                                    <c:forEach var="item" items="${getTypeofRankcategoryListdash}" varStatus="num" >
                 							<option value="${item.codevalue}"  name="${item.label}" >${item.label}</option>
                 						</c:forEach>
             					</select>
             					</div>
             				</div>
             				</div>	 
             				
             				<br>
             				<div class="row form-group" >
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-3">
               						<label class=" form-control-label">Common Appt/Trade</label>
             					</div>
             					<div class="col-12 col-md-8">
               						<input id="appt_trade" name="appt_trade" class="form-control" autocomplete="off" style="font-family: 'FontAwesome',Arial;" placeholder="&#128269; Search">
               						<input type="hidden" id="app_trd_code" name="app_trd_code">
             					</div>
             				</div>
             				</div>          					
	  					
		  					<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-3">
	                 					<label for="text-input" class=" form-control-label">Rank</label>
	               					</div>
	               					<div class="col-12 col-md-8">
	                 					<select id="rank" name="rank" class="form-control">
	                 					<option value="">--Select--</option>
	                 					</select>
									</div>	 							
		  						</div>
		  						</div>	
		  						</div>
		  						</div>
		  	<div class="card-footer">
			<div align="center" class="search_btn">
			<a href="DocTypeReportWetPetFet?type=pers" class="btn btn-success btn-sm" >Clear</a>  
<!-- 			<input type="button" class="btn btn-success btn-sm" id="btn1" value="Clear" onclick="return clearAll();">					 -->
			<i class="fa fa-search"></i>
			<input type="button" class="btn btn-primary btn-sm" id="btn-reload" value="Search"/>  
		</div>
		</div>
						</div>
						</div>
					</div>
					</div><br>
					
					
				<div class="card-body">
					<div class="datatablediv" id="reportDiv">
						<div class="">
							<div class="watermarked" data-watermark="" id="divwatermark">
								<span id="ip"></span>
								<table id="getSearch" 
								class="table no-margin table-striped  table-hover  table-bordered" >
									<thead style="font-size: 15px; text-align: center;">
										<tr>
											<th>Name of the comd</th>
											<th>Offr</th>
											<th>JCOs</th>
											<th>ORs</th>
											<th>Civ</th>
											<th>Total</th>
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
			
				

<script>
var key = "${_csrf.parameterName}";
var value = "${_csrf.token}"; 
$(document).ready(function() {
	$.ajaxSetup({
		async : false
	});
	
// 	clearAll();
	var select = '<option value="' + "0" + '">'
			+ "--Select--" + '</option>';
	$('select#cont_comd').change(function() {
		var fcode = this.value;
		if (fcode == "0") {
			$("select#cont_corps").html(select);
			$("select#cont_div").html(select);
			$("select#cont_bde").html(select);
		} else {
			$("select#cont_corps").html(select);
			$("select#cont_div").html(select);
			$("select#cont_bde").html(select);

			$("#hd_cmd_sus").val(fcode);

			getCONTCorps(fcode);

			fcode += "00";
			getCONTDiv(fcode);

			fcode += "000";
			getCONTBde(fcode);
		}
	});
	
	$('select#to_cont_comd').change(function() {
		var fcode = this.value;
		if (fcode == "0") {
			$("select#to_cont_corps").html(select);
			$("select#to_cont_div").html(select);
			$("select#to_cont_bde").html(select);
		} else {
			$("select#to_cont_corps").html(select);
			$("select#to_cont_div").html(select);
			$("select#to_cont_bde").html(select);

			$("#hd_cmd_sus").val(fcode);

			getCONTCorpsFrom(fcode);

			fcode += "00";
			getCONTDiv(fcode);

			fcode += "000";
			getCONTBde(fcode);
		}
	});
	$('select#cont_corps').change(function() {
		var fcode = this.value;
		if (fcode == "0") {
			$("select#cont_div").html(select);
			$("select#cont_bde").html(select);
		} else {
			$("select#cont_div").html(select);
			$("select#cont_bde").html(select);

			$("#hd_corp_sus").val(fcode);
			getCONTDiv(fcode);
			fcode += "000";
			getCONTBde(fcode);
		}
	});
	
	$('select#cont_div').change(function() {
		var fcode = this.value;
		if (fcode == "0") {
			$("select#cont_bde").html(select);
		} else {

			$("select#cont_bde").html(select);
			$("#hd_div_sus").val(fcode);
			getCONTBde(fcode);
		}
	});
	


	$('select#cont_bde').change(function() {
		var fcode = this.value;
		if (fcode == "0") {
			$("select#cont_bde").html(select);
		} else {
			$("#hd_bde_sus").val(fcode);
		}
	});
	
	
});

mockjax1('getSearch');
table = dataTable('getSearch');

$('#btn-reload').on('click', function(){
    table.ajax.reload();
});


function getCONTCorps(fcode) {
	var fcode1 = fcode[0];
	$.post("getCorpsDetailsList?" + key + "=" + value, {
		fcode : fcode1
	}, function(j) {
		if (j != "") {
			var length = j.length - 1;
			var enc = j[length][0].substring(0, 16);
			var options = '<option value="' + "0" + '">' + "--Select--"
					+ '</option>';

			for (var i = 0; i < length; i++) {
				if ('${corp_sus}' == dec(enc, j[i][0])) {
					options += '<option value="' + dec(enc, j[i][0])
							+ '" name="' + dec(enc, j[i][1])
							+ '" selected=selected >' + dec(enc, j[i][1])
							+ '</option>';
				} else {
					options += '<option value="' + dec(enc, j[i][0])
							+ '" >' + dec(enc, j[i][1]) + '</option>';
				}
			}
			$("select#cont_corps").html(options);
		}
	});
}

function getCONTDiv(fcode) {
	var fcode1 = fcode[0] + fcode[1] + fcode[2];
	$.post("getDivDetailsList?" + key + "=" + value, {
		fcode : fcode1
	}, function(j) {
		if (j != "") {
			var length = j.length - 1;
			var enc = j[length][0].substring(0, 16);
			var options = '<option value="' + "0" + '">' + "--Select--"
					+ '</option>';
			for (var i = 0; i < length; i++) {
				if ('${div_sus}' == dec(enc, j[i][0])) {
					options += '<option value="' + dec(enc, j[i][0])
							+ '" name="' + dec(enc, j[i][1])
							+ '" selected=selected >' + dec(enc, j[i][1])
							+ '</option>';
				} else {
					options += '<option value="' + dec(enc, j[i][0])
							+ '" >' + dec(enc, j[i][1]) + '</option>';
				}
			}
			$("select#cont_div").html(options);
		}
	});
}


function getCONTBde(fcode) {
	var fcode1 = fcode[0] + fcode[1] + fcode[2] + fcode[3] + fcode[4]
			+ fcode[5];
	$.post("getBdeDetailsList?" + key + "=" + value, {
		fcode : fcode1
	}, function(j) {
		if (j != "") {
			var length = j.length - 1;
			var enc = j[length][0].substring(0, 16);
			var options = '<option value="' + "0" + '">' + "--Select--"
					+ '</option>';
			jQuery("select#cont_bde").html(options);
			for (var i = 0; i < length; i++) {
				if ('${bde_sus}' == dec(enc, j[i][0])) {
					options += '<option value="' + dec(enc, j[i][0])
							+ '" name="' + dec(enc, j[i][1])
							+ '" selected=selected>' + dec(enc, j[i][1])
							+ '</option>';
					$("#cont_bname").val(dec(enc, j[i][1]));
				} else {
					options += '<option value="' + dec(enc, j[i][0])
							+ '" name="' + dec(enc, j[i][1]) + '">'
							+ dec(enc, j[i][1]) + '</option>';
				}
			}
			$("select#cont_bde").html(options);
		}
	});
}


function select_rank_cat(){
	var rnk = $("select#rank_cat").val();
	 $('select#rank').val("");
	
	 
	 $.post("getTypeofRankList?"+key+"="+value,{rnk : rnk}).done(function(j) {
		 var length = j.length-1;
			var enc = j[length].columnName1.substring(0,16);
			var options = '<option value="">'+ "--Select--" + '</option>';
			for ( var i = 0; i < j.length-1; i++) {
				options += '<option value="' + dec(enc,j[i].columnCode)+ '" >'+ dec(enc,j[i].columnName) + '</option>';					
			}	
			$("select#rank").html(options); 		
		 }).fail(function(xhr, textStatus, errorThrown) { });   

		
}

function select_rank_app_trade(){
	var rnk = $("select#rank_cat").val();
	
	$('#appt_trade').val("");
	$('#app_trd_code').val("");
	
	 var wepetext1=$("#appt_trade");

	  wepetext1.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
        	type: 'POST',
	  	    url: "getTypeappt_tradeList?"+key+"="+value,
	        data: {rnk : rnk,appt_trade : document.getElementById('appt_trade').value },
	          success: function( data ) {
	            //response( data );
	            if(data.length > 1){
	        	  var susval = [];
	        	  var length = data.length-1;
	        		 var enc = data[length].columnName1.substring(0,16);
                   for(var i = 0;i<data.length-1;i++){
                    susval.push(dec(enc,data[i].columnName));
	        	  	}
		        	var dataCountry1 = susval.join("|");
	            var myResponse = []; 
	            var autoTextVal=wepetext1.val();
				$.each(dataCountry1.toString().split("|"), function(i,e){
					var newE = e.substring(0, autoTextVal.length);
					//if (newE.toLowerCase() === autoTextVal.toLowerCase()) {
					if (e.toLowerCase().includes(autoTextVal.toLowerCase())) {
					  myResponse.push(e);
					}
				});      	          
				response( myResponse ); 
				}
	          }
	        });
	      },
	      minLength: 1,
	      autoFill: true,
	      change: function(event, ui) {
	    	 if (ui.item) {   	        	  
	        	  return true;    	            
	          } else {
	        	  alert("Please Enter Approved Common Appt/Trade");
	        	  wepetext1.val("");	
// 	        	  document.getElementById("app_trd_code").value="";
	        	  wepetext1.focus();
	        	  return false;	             
	          }   	         
	      }, 	     
	    });
	
	}


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
	var appt_trade = $("#appt_trade").val();
	var rank = $("#rank").val();  
	var rank_cat = $("#rank_cat").val(); 
	var cont_comd = $("#cont_comd").val();
	var cont_corps = $("#cont_corps").val();
	var cont_div = $("#cont_div").val();
	var cont_bde = $("#cont_bde").val();
// 	var radio1 = $('input[id="radio1"]:checked').val();
	
	var radio1;
	if ($('input[id="radio1"]:checked').length > 0) {
		radio1 = $('input[id="radio1"]:checked').val();
	} else {
	  radio1 = ""; 
	}

	if (tableName == "getSearch") {

	    $.post("getDocTypeDetaisReportPerscount?" + key + "=" + value,{
	    	Search:Search,
	    	appt_trade : appt_trade,
	    	rank : rank,
	    	rank_cat:rank_cat,
	    	cont_comd : cont_comd,
			cont_corps : cont_corps,
			cont_div : cont_div,
			cont_bde : cont_bde,
			radio1 : radio1
		 },  function(j) {
			FilteredRecords = j;
		});
		$.post("getDocTypeDetaisReportPers?" + key + "=" + value, {
		
			startPage : startPage,
			pageLength : pageLength,
			Search:Search,
			orderColunm : orderColunm,
			appt_trade :  appt_trade,
			rank : rank,
			rank_cat:rank_cat,
			cont_comd : cont_comd,
			cont_corps : cont_corps,
			cont_div : cont_div,
			cont_bde : cont_bde,
			radio1 : radio1
		}, function(j) {

			for (var i = 0; i < j.length; i++) {
				var sr = i + 1;
					
				jsondata.push([j[i].cmd_name, j[i].officer, j[i].jco,j[i].or, j[i].civ, j[i].total]);
				
				
			}
		});
	}
}

</script>

