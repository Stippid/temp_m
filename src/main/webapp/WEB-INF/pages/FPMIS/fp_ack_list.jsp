<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmn" %>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js"></script>
<link rel="stylesheet" href="js/common/nrcss.css">

<script>
	var username="${username}";
	var curDate = "${curDate}";
	var role = "${role}";
</script>
<style>
.nrptt thead, .nrptt tbody tr {
    display:table;
    width:100%;
    table-layout:fixed;
}
</style>
<body onload="setMenu();"  class="mmsbg">
<% int ntotln=0; %>
<c:set var="nsell" value="${n_sel}"/>
<c:set var="nsel" value="${fn:split(nsell,':')}"></c:set>
<form action="" method="post" enctype="multipart/form-data" class="form-horizontal" >
<div id="nMain">
	<div class="nkpageland" id="printableArea">
	   <div>		
     	<div class="container" align="center" id="headerView" style="display: block;">  
	       <div class="container" align="center">
	    	      <div class="card">
	    	           <div class="card-header mms-card-header"
					style="min-height: 60px; padding-top: 10px;">
	    	                 <b>LIST OF FP ACK</b> 
	    	           </div>	    	           
	    	            <div class="card-body card-block">
	    	            </div>
	    	      </div>
	  		</div>  		
		</div>
<div id ="tdheaderView" style="display: none;"  align="center" class="nrTableHeading"> <b></b></div>
<div  class="watermarked" data-watermark="" id="divwatermark"  > 		
		<div class="card" style="background: transparent;" id="unit_hid2">		
				<div  width="100%" class="col-md-12" id = "SearchViewtr" title="">	
				    <div class="nrTableMain2Search col-md-4" align="left" > 
						<input id="nrInput" type="text" placeholder="Search the List ..." size="35" style="font-weight: normal;font-size: 14px;" title="Search by UserID / Users">
				    </div>		    		
				</div>	
				
					<span id="ip"></span>	               
		               <input type="hidden" id="selectedid" name="selectedid">
		               <input type="hidden" id="selectedpid" name="selectedpid"> 
		               <input type="hidden" id="nrSrcSel" name="nrSrcSel">          
						<div class="nrTableDataDiv_1 nPopTable" id="nrTableDataDiv"  style="height:400px;font-size:1.3vw;"> 
							    <table id="nrTable" border="0" class="report_printt" width="96%" >
	                        		<thead style="text-align: center;line-height: 25px;font-size: 1vw;">
	                        				<tr>
	                        					<th width="1%"></th>
				                  				<th width="1%"></th>
				                  				<th width="15%">User ID</th>
				                  				<th width="25%">User</th>
				                  				<th width="15%">No of Attempts</th>
				                  				<th width="15%">Last Attempt</th>
                  			               </tr>
                        		     </thead>
	    							    <tbody id="nrTable" style="font-size: 1vw;text-decoration: none;">
	    							           <c:if test="${n_1[0] == 'NIL'}">
	    							                 <tr class='nrSubHeading'>
	    							                 	<% ntotln++; %>
														 <td colspan='10' style='text-align:center;'>Data Not Available...</td>
													 </tr>
											   </c:if>
											   
											   <% String tmpmjhead = "";%>
											   <% String tmpmjhead1 = "";%>    
											   <% String tmpic = ""; %>
	
											   <c:set var="nsell" value="${n_sel}"/>
											   <c:set var="h1" value=""/>
											   <c:set var="h2" value=""/>
											   <c:set var="h3" value=""/>
											   <c:set var="h4" value=""/>
											   <c:set var="h5" value=""/>
											   <c:set var="idxno" value="0" />
											   <c:set var="nsel" value="${fn:split(nsell,':')}"></c:set>
											   <c:if test="${n_1[0]!= 'NIL'}"> 
											       <c:forEach var="item" items="${n_1}" varStatus="num">
											       	<c:if test="${item[idxno] != tmpmjhead}">
											       		<tr id="ln">
											       		<td style='text-align:left;' colspan='6'>${item[idxno+8]}</td> 
														<c:set var="tmpmjhead" value="${item[idxno]}"></c:set>
												    </c:if>
												    <c:if test="${item[idxno+1] != tmpmjhead1}">
											       		<tr id="ln"><td></td>
											       		<td style='text-align:left;' colspan='5'>${item[idxno+2]}&nbsp;(${item[idxno+1]})</td> 
														<c:set var="tmpmjhead1" value="${item[idxno+1]}"></c:set>
												    </c:if>
											       	<tr id="ln">
											       	<td></td><td></td>							                	    
							                		<td style='text-align:left;' title="User ID - ${item[idxno+3]}">${item[idxno+3]}</td>
							                		<td style='text-align:left;' title="User - ${item[idxno+5]} - ${item[idxno+4]}">${item[idxno+5]} - ${item[idxno+4]}</td>
							                		<td style='text-align:left;' title="No. of Attempts - ${item[idxno+6]}">${item[idxno+6]}</td>
							                		<td style='text-align:left;' title="Last Attempts - ${item[idxno+7]}">${item[idxno+7]}</td>
							                		<c:set var="idxno" value="0"></c:set>
											       </c:forEach>
											   </c:if>
	    							     </tbody>
							    </table>
							       </div>    
						   </div> 
						    <div  class="card-footer">
						        <input type="button" class="btn btn-success btn-sm nGlow" value="Export" id="btn_e" style="background-color: purple;" data-toggle="tooltip" title="Export Data to Excel" onclick="exportData('#nrTableDataDiv');">
				             	<input type="button" class="btn btn-primary btn-sm nGlow" value="Print Page" id="btn_p" onclick="printDiv();" title="Print the Data"> 
				           	</div>
		         		</div> 
		    		</div>
		       </div>            
		</div>
</form>

<c:url value="fp_Budg_status_flt?${_csrf.parameterName}=${_csrf.token}" var="backUrl" />
<form:form action="${backUrl}" method="post" id="m1_Bug_status" name="m1_Bug_status" modelAttribute="m1_nomen">
      <input type="hidden" name="m1_tryear" id="m1_tryear"/>
	  <input type="hidden" name="m1_lvl" id="m1_lvl"/>
</form:form>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>

<script>
function printDiv() {
	  $("#SearchViewtr").hide();
	  $("#tdheaderView").show();
	  $("#headerView").hide();
	  $("#btn_e").hide();
	  $("#btn_p").hide();
	  $("#btn_modify").hide();
	  $('.rdView').css('display','none');
	  let innerContents = document.getElementById('printableArea').innerHTML;	 
	  popupWindow = window.open('', '_blank', 'width=850,height=500,scrollbars=yes,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
	  popupWindow.document.open();
	  popupWindow.document.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/cue/printWatermark.css"><style> table td{font-size:10px; font-weight:normal !important;} table th{font-size:12px !important;} tbody th{font-size:10px; font-weight:normal !important;}</style></head><body onload="window.focus(); window.print(); window.close();" oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false">' +innerContents+  '</html>');
	  popupWindow.document.close();
	  $("#SearchViewtr").show();
	  $("#tdheaderView").hide();
	  $("#headerView").show();
	  $("#btn_e").show();
	  $("#btn_p").show();
	  $("#btn_modify").show();
	  $('.rdView').css('display','block');
}

function exportData(b){
	$().tbtoExcel(b);
	b.preventDefault();
}
</script>

<script>
$(document).ready(function() {
	
    $("#nrInput").on("keyup", function() {
    	var value = $(this).val().toLowerCase();
      	$("#nrTable tbody tr").filter(function() {
      		$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });      
    
    $("div#divwatermark").val('').addClass('watermarked');	
	watermarkreport();
  	try{
  		if(window.location.href.includes("msg=")){
  			var url = window.location.href.split("?msg")[0];
			window.location = url;
		} 	
	}
	catch (e) {
	}
});
</script>