<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<link rel="Stylesheet" href="js/Calender/jquery-ui.css" >

<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js"></script>
<link rel="stylesheet" href="js/common/nrcss.css">
<body class="mmsbg">
<% int ntotln=0; %>
<div id="nrWaitLoader" style="display:none;" align="center">
	<span id="">Processing Data.Please Wait ...</span>
</div>

 <div class="nkpageland" id="printableArea">
  <div onFocus="parent_disable1();" onclick="parent_disable1();">		
     <div class="container" align="center" id="headerView" style="display: block;">  
		    <div class="card">  
      
           <div class="card-header mms-card-header">
		          <b>BULK RO : COMD</b>
		   </div> 
		   
      </div>
   </div>
</div>

<div id ="tdheaderView" style="display: none;" colspan='13' align="center" class="nrTableHeading">  <b>BULK RO : COMD</b></div>
<div class="card" id="unit_hid2" style="background: transparent;">

					<div width="100%"> 	
						<div class="nrTableMain2Search" align="left" id = "SearchViewtr"> 
									<label>Search in Result(<span id="ntotln"></span>)</label>&nbsp;:&nbsp;
									<input id="nrInput" type="text" placeholder="Search..." size="35" style="font-weight: normal;font-size: 14px;">
							    </div>
			    		
					</div>	
  
	<div  class="watermarked" data-watermark="" id="divwatermark" >
						<span id="ip"></span>
			<div id="" class="nrTableMainDiv">
       
            <input type="hidden" id="selectedid" name="selectedid">
            <input type="hidden" id="statushid" name="statushid">
            <input type="hidden" id="nrSrcSel" name="nrSrcSel">
            <input type="hidden" id="c_qty" name="c_qty">
            				 <div class="nrTableDataDiv">
							    <table border="1" class="report_print" width="100%">
	                        			<thead style="text-align: center;">
	                          				<tr >
	                        					<th width="19%">RO No with Date</th>
		            							<th width="6%">RO Type</th>
		                    					<th width="18%">To Unit</th>
		                    					<th width="14%">Issue Against</th>
		                    					<th width="16%">PRF Group</th>
		            							<th width="6%">Qty</th>
		            							<th width="6%">Qty Issued</th>
		            							<th width="6%">Qty Hold</th>
		            							<th width="10%">Status</th>
	                          				</tr>
                        			</thead>
    								<tbody id="nrTable">
    								    <c:if test="${m_1[0][0] == 'NIL'}">
											 <tr class='nrSubHeading'>
												<td colspan='13' style='text-align:center;'>Data Not Available...</td>
											    <% ntotln=0; %>
											 </tr>
										</c:if>
										
										<c:if test="${m_1[0][0] != 'NIL'}"> 
										    <c:forEach var="item" items="${m_1}" varStatus="num">
										        <tr id="NRRDO" name="NRRDO">
										            <td width="19%" style='text-align:left;'><input class ="nrCheckBox"type="radio" id="NRRDOO${item[0]}" name="n" onclick="setid('${item[0]}','${item[7]}','${item[8]}')">${item[1]}</td>
										            <td width="6%" style='text-align:left;'>${item[2]}</td>
										            <td width="18%" style='text-align: left;'>${item[3]}</td>
										            <td width="14%" style='text-align:center;'>${item[4]}</td>
										            <td width="16%" style='text-align:left;'>${item[5]}</td>
										            <td width="6%" style='text-align:center;'>${item[6]}</td>
										            <c:if test="${empty item[8]}">
										                <td style='text-align:center;'>0</td>
										            </c:if>
										            <c:if test="${not empty item[8]}">
										                <td width="6%" style='text-align:center;'>${item[8]}</td>
										            </c:if>
										            <td width="6%" style='text-align:center;'>${item[9]}</td>
										            <td width="10%" style='text-align:center;'>AWAITING SANCTION OF BRIG EM</td>
										            <% ntotln++; %>
										        </tr>
										    </c:forEach>
										</c:if>
    								</tbody>
								</table>
								</div>
						</div>
				     </div>
     
     <div class="card-footer">
           <input type="button" class="btn btn-success btn-sm" value="Export" id="btn_e" style="background-color: purple;" onclick="exportData('.nrTableDataDiv');">
             <input type="button" class="btn btn-primary btn-sm" value="Print Page" id="btn_p" onclick="printDiv();"> 
      	   <input type="button" class="btn btn-success btn-sm" value="Allocate Units" onclick="return todo();" id = "wDiv" style ="display:none;float: right;"> 	
     </div> 
</div>
</div>
<c:url value="mms_bulk_rio_allocation" var="roUrl" />
<form:form action="${roUrl}"  method="post" id="editForm" name="editForm" modelAttribute="editId">
<input type="hidden" name="editId" id="editId" value="0"/>
<input type="hidden" name="cqty" id="cqty"/>
</form:form>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>
		
<script>
function printDiv() {
	  $("#SearchViewtr").hide();
	  $("#tdheaderView").show();
	  $("#headerView").hide();
	  $("#btn_e").hide();
	  $("#btn_p").hide();
	 $('.nrCheckBox').css('display','none');
	  //let popupWinindow
let innerContents = document.getElementById('printableArea').innerHTML;
	 
popupWindow = window.open('', '_blank', 'width=850,height=500,scrollbars=yes,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
popupWindow.document.open();
//popupWinindow.document.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/miso/assets/scss/style.css"><link rel="stylesheet" href="js/cue/printWatermark.css"></head><body onload="window.print();window.close();">' + innerContents + '</html>');
popupWindow.document.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/cue/printWatermark.css"><style> table td{font-size:10px; font-weight:normal !important;} table th{font-size:12px !important;} tbody th{font-size:10px; font-weight:normal !important;}</style></head><body onload="window.focus(); window.print(); window.close();" oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false">' +innerContents+  '</html>');
popupWindow.document.close();
$("#SearchViewtr").show();
$("#tdheaderView").hide();
$("#headerView").show();
$("#btn_e").show();
$("#btn_p").show();
$('.nrCheckBox').css('display','block');
 
}
function exportData(b){
	
	$().tbtoExcel(b);
	b.preventDefault();
}

function todo(){
	var a = $('#nrSrcSel').val();
	var qt = $('#c_qty').val();
	$('#editId').val(a);
	$('#cqty').val(qt);
	$('#editForm').submit();
}
</script>

<script>
$(document).ready(function() {
	$("div#divwatermark").val('').addClass('watermarked');        
    watermarkreport();
	var y1 = '${m_1[0][0]}';
	
	if(y1 != "NIL"){
		$("#btn_p").show();
	}
		
	if(y1 != "" || '${m_1[0]}'.length > 0){
		nrSetWaterMark(<%=ntotln+2%>);
		$("#ntotln").text(<%=ntotln%>);	
	}
			
	try{
		if(window.location.href.includes("msg=")){
			var url = window.location.href.split("?msg")[0];
			window.location = url;
		} 	
	}catch (e){
		
	}
	  		    
	try{
		if(window.location.href.includes("id=")){
			var url = window.location.href.split("?id")[0];
	      	window.location = url;
	    } 	
	}catch (e){
	}  		
	      		
	$("#nrInput").on("keyup", function() {
		var value = $(this).val().toLowerCase();
		$("#nrTable tr").filter(function() {
		$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		});
	});
});

function setid(a,st,qt){
	$("#selectedid").val(a);
	$("#statushid").val(st);
	$("#nrSrcSel").val(a);
	$("#c_qty").val(qt);
	$("#wDiv").show();
	$("#NRRDOO"+a).attr("background-color","yellow");
	
}
</script>