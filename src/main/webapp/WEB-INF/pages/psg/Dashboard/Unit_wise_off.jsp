<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %>
<%@ taglib prefix="dandelion" uri="http://github.com/dandelion" %>
<%
	HttpSession sess = request.getSession(false);
	if (sess.getAttribute("userId") == null) { response.sendRedirect("/login"); return; } 

	
%>
<dandelion:asset cssExcludes="datatables"/>
<dandelion:asset jsExcludes="datatables"/>
<dandelion:asset jsExcludes="jquery"/>
<html>
  <head>
	<title>FMN WISE HOLDING</title>
			     		<link rel="stylesheet" href="js/cue/cueWatermark.css">
        <script src="js/cue/cueWatermark.js"></script>
		<script src="js/cue/printAllPages.js" type="text/javascript"></script>
		<link href="js/JS_CSS/jquery.dataTables.min.css" rel="stylesheet"> 
		<script src="js/JS_CSS/jquery-3.3.1.js"></script>
		<script src="js/JS_CSS/jquery.dataTables.js"></script>
		<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
		
		<link rel="stylesheet" href="js/cue/cueWatermark.css">

<link rel="stylesheet" href="js/datatable/css/datatables.min.css">
<script type="text/javascript" src="js/datatable/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/datatable/js/jquery.mockjax.js"></script>

   <!-- resizable_col -->
<link rel="stylesheet" href="js/assets/adjestable_Col/jquery.resizableColumns.css">
<script src="js/assets/adjestable_Col/jquery.resizableColumns.js" type="text/javascript"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
     
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
		<script>
			var username="${username}";
			var curDate = "${curDate}";
		</script>
		
	<style type="text/css">
	table{
	      width:100% !important;
	   }
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
		
		.print_btn{
		  margin:0 auto;
		}
		.print_btn input{
		  background-color: #9c27b0;
          border-color: #9c27b0;
		}
		.print_btn input:hover{
			background-color: #cb5adf;
			border-color: #cb5adf;
		}
	</style>
	
	<script type="text/javascript">
		$(document).ready(function () {
			
		    $('body').bind('cut copy paste', function (e) {
		        e.preventDefault();
		    });
		   
		    $("body").on("contextmenu",function(e){
		        return false;
		    });
		});
	</script>
</head>
<body style="height: 400px">
	<div class="container" id="printableArea">
			<div class="row">
	    		<div class="card">
	    			<div class="card-header" align="center"> <strong style="text-decoration: underline;">UNIT </strong> 
	    			</div> 
		            <div class="col-md-12" id="printableArea" >
		           	 	<div id="divShow" style="display: block;">
						</div> 
						 <div class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
					<span id="ip"></span>
         				 	<table id="getSearchtable_unit"
					class="table no-margin table-striped  table-hover  table-bordered 	 ">
					<thead>
						<tr>
							<th style="text-align: center;" id="cadet_no">Ser No</th>
							<th style="text-align: center;" id="command">COMMAND</th>
							<th style="text-align: center;" id="corps"> CORPS</th>
							<th style="text-align: center;" id="brigade">BRIGADE</th>
							<th style="text-align: center;" id="division"> DIVISION</th>
							
							<th style="text-align: center;" id="unit_name"> UNIT NAME</th>
						</tr>
					</thead>

				</table>
<%--   						<datatables:table id="applicanttbl" url="getunitwiseoff" serverSide="true" pipelining="true" pipeSize="3"	row="latlon" rowIdBase="applicant_id" rowIdPrefix="latlon_"  displayLength="10" lengthMenu="10,20,100,500,5000" jqueryUI="true"  --%>
<%-- 	    					bDestroy="true" filterable="false" sortable="true" processing="true" border="1"  autoWidth="true" pageable="true" paginationType="full_numbers" stateSave="false" deferRender="true" scrollX="100%" scrollY="100%" scrollCollapse="true" >   --%>
<%-- 						   <datatables:column title="COMMAND" property="command" searchable="true" data-halign="left" data-valign="left" /> --%>
<%-- 						    <datatables:column title="CORPS" property="corps" searchable="true" data-halign="left" data-valign="left" /> --%>
<%-- 						    <datatables:column title="BRIGADE" property="brigade" searchable="true" data-halign="left" data-valign="left"/> --%>
<%-- 						    <datatables:column title="DIVISION" property="division" searchable="true" data-halign="left" data-valign="left" /> --%>
<%-- 						    <datatables:column title="UNIT NAME" property="unit_name"  searchable="true" data-halign="left" data-valign="left" /> --%>

<%-- 					</datatables:table>     --%>
                        
                        </div>
		        	</div>
	        	</div>
			</div>
		</div>
		<div class="col-md-12">
			<div align="center" class="print_btn">
			<input type="button" value="Print" onclick="return printDiv('printableArea')" style="color:white; padding: 5px 10px 5px 10px; font-size: 14px; font-weight: bold;"></div>
		</div>

		<script>	
		
			$(document).ready(function() {
				 $("div#divwatermark").val('').addClass('watermarked');	
				watermarkreport(); 
				mockjax1('getSearchtable_unit');
				table = dataTable11('getSearchtable_unit');
				
			});
			 var key = "${_csrf.parameterName}";
			 var value = "${_csrf.token}";
	
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
var orderColunm = $(table.column(order[0][0]).header()).attr('id');
var orderType = order[0][1];

				var cont_comd4 = '${cont_comd50}';
			    var cont_corps4 = '${cont_corps50}';
			    var cont_div4 = '${cont_div50}';
			    var cont_bde4 = '${cont_bde50}';
			    var rank4 = '${rank50}';
			    var arm4 = '${arm50}';
			    var parm4 = '${parm50}';
			    var cmd4 = '${cmd50}';
			    var bdes4 = '${bdes50}';
			    var div4 = '${div50}';
			    var corps4 = '${corps50}';
			    var regs4 = '${regs50}';
			    var unit4 = '${unit50}';
			    var parent_arm4 = '${parent_arm50}';
			    var unit_name4 = '${unit_name50}';


				if (tableName == "getSearchtable_unit" ) {

					$.post("getSearchtabledata_unitcount?" + key + "=" + value, {
						Search : Search,
						cont_comd4 : cont_comd4,
						cont_corps4 : cont_corps4,
						cont_div4:cont_div4,
						cont_bde4:cont_bde4,
						rank4:rank4,
						arm4:arm4,
						parm4:parm4,
						cmd4:cmd4,
						bdes4:bdes4,
						div4:div4,
						corps4:corps4,
						regs4:regs4,
						unit4:unit4,
						parent_arm4:parent_arm4,
						unit_name4:unit_name4
					}, function(j) {
						FilteredRecords = j;
						
					});
					$.post("getSearchtabledataunit?" + key + "=" + value, {
						startPage : startPage,
						pageLength : pageLength,
						Search : Search,
						orderColunm : orderColunm,
						orderType : orderType,				
						cont_comd4 : cont_comd4,
						cont_corps4 : cont_corps4,
						cont_bde4:cont_bde4,
						cont_div4:cont_div4,
						rank4:rank4,
						arm4:arm4,
						parm4:parm4,
						cmd4:cmd4,
						bdes4:bdes4,
						div4:div4,
						corps4:corps4,
						regs4:regs4,
						unit4:unit4,
						parent_arm4:parent_arm4,
						unit_name4:unit_name4
					}, function(j) {
					
			
						for (var i = 0; i < j.length; i++) {
							var sr = i + 1;
							jsondata.push([ sr, j[i][0], j[i][1],j[i][2], j[i][3], j[i][4],
									 j[i][5],j[i][6]
									
			
								]);

						}
						
					});
				}
			
			} 
			
			function printDiv(divName) {
				$("div#divwatermark").val('').addClass('watermarked');	
				watermarkreport(); 
				$("div#divShow").empty();
				$("div#divShow").show();
				
				var row="";
				row +='<div class="print_content"> ';
			 	row +='<div class="print_logo"> ';
				row +='<div class="row"> ';
				row +='<div class="col-3 col-sm-3 col-md-3"><img src="js/miso/images/indianarmy_smrm5aaa.jpg"></div> ';
				row +='<div class="col-6 col-sm-6 col-md-6"><h3>FMN WISE HOLDING</h3> </div> ';
				row +='<div class="col-3 col-sm-3 col-md-3" align="right"><img src="js/miso/images/dgis-logo.png"></div> ';
				row +='</div> ';
				row +='</div> ';
				 $("div#divShow").append(row); 
				
				let popupWinindow;
				let innerContents = document.getElementById(divName).innerHTML;
				popupWinindow = window.open('', '_blank', 'width=1400,height=700,scrollbars=yes,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
				popupWinindow.document.open();
				popupWinindow.oncontextmenu = function () {
					return false;
				}
				
			   popupWinindow.document.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/miso/assets/scss/style.css"><link rel="stylesheet" href="js/cue/printWatermark.css"><style> table td{font-size:12px;font-weight:normal;} table th{font-size:13px !important;} </style></head><body onload="window.focus(); window.print(); window.close();" oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false">' +innerContents+  '</html>');
	   
				watermarkreport();
			    popupWinindow.document.close(); 
			    document.location.reload();
			    $("div#divShow").hide();
			}
			</script>

    </body>
</html>
	
	



	
  