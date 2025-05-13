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
	<title>PRF WISE HOLDING</title>
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
	    			<div class="card-header" align="center"> <strong style="text-decoration: underline;">PRF WISE HOLDING</strong> 
	    			</div> 
		            <div class="col-md-12" id="printableArea" >
		           	 	<div id="divShow" style="display: block;">
						</div> 
						 <div class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
					<span id="ip"></span>
         				 
  						<datatables:table id="applicanttbl" url="getprfwiseueuhrpt" serverSide="true" pipelining="true" pipeSize="3"	row="latlon" rowIdBase="applicant_id" rowIdPrefix="latlon_"  displayLength="10" lengthMenu="10,20,100,500,5000" jqueryUI="true" 
	    					bDestroy="true" filterable="false" sortable="true" processing="true" border="1"  autoWidth="true" pageable="true" paginationType="full_numbers" stateSave="false" deferRender="true" scrollX="100%" scrollY="100%" scrollCollapse="true" >  
							    <datatables:column title="PRF NO" property="prf_group" searchable="true" data-halign="left" data-valign="left" />
							     <datatables:column title="PRF NAME" property="group_name" searchable="true" data-halign="left" data-valign="left" />
							    <datatables:column title="UE" property="ue" searchable="true" data-halign="left" data-valign="left"/>
							    <datatables:column title="UH" property="uh" searchable="true" data-halign="left" data-valign="left" />
								<%-- <datatables:column title="Actions" renderFunction="actionsUnitDetails" sortable="false" searchable="false" display="HTML" /> --%>
						</datatables:table>    
                        <br/>
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
				
				
			});
		
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
				row +='<div class="col-6 col-sm-6 col-md-6"><h3>PRF WISE HOLDING</h3> </div> ';
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
	
	



	
  