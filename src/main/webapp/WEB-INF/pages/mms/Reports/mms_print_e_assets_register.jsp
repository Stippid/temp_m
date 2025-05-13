<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	HttpSession sess = request.getSession(false);
	if (sess.getAttribute("userId") == null) { response.sendRedirect("~/login"); return; } 
%>


<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css"> 
<link rel="stylesheet" href="js/miso/assets/scss/style.css">
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js"></script>
<script src="js/cue/print_OLAPReport.js"></script>
<link rel="stylesheet" href="js/common/nrcssp.css">

<script>
var username="${username}";
var curDate = "${curDate}";
function nkprint(nrdiv) {
	
	var nra=$("#"+nrdiv).html();
	var nrl=$("#nrlinks").html();
	popupWindow=window.open('',200,300);
	popupWindow.document.write(nrl+nra);
	popupWindow.window.print();
	popupWindow.document.close();
}
</script>
<div class="nkpageland" id="printableArea">
				<table style="width:100%;">
				<tr align="center">
					<td></td>
					<td>
					<h5 style="text-decoration: underline;"><b>RESTRICTED</b></h5>
					</td>
					<td></td>
					</tr>
					<tr>
				   		<td align="left" style="padding-right:50px;">
				   			<img src="js/miso/images/indianarmy_smrm5aaa.jpg" style="height: 50px;">
				   		</td>
				   		<td align="center">
				   			<h4 style="text-decoration: underline;font-size: 22px;"><b>MISO/MMS</b></h4>
				   			<h5 style="font-size: 22px;">e-Asset Register :<br>&nbsp;</h5>
				   		</td>
				   		<td align="right">
				   			<img src="js/miso/images/dgis-logo.png" style="max-width: 155px; height: 50px;"> 
				   		</td>
					</tr>
							
		   				<tr style='font-weight: bold; font-size: 15px;'>
		   				<td colspan='8'>SUS No: <span id="nsusno">${sus_no}</span></td></tr>
		   				<tr style='font-weight: bold; font-size: 15px;'>
			    		<td colspan='3'>UNIT NAME: <span id="nunitname">${unit_name}</span></td></tr>
			    		<tr style='font-weight: bold; font-size: 15px;'>
		   				<td colspan='5' style="text-align: right;">Last Updated: <span id="nlastpd">${nlastpd}</span></td></tr>
		   
			</table>
<div id="in">
<div  class="watermarked" data-watermark="" id="divwatermark" >
<span id="ip"></span>
<table id="nrTableDataHead" class="table_collapse nrTableDataHead" width="100%" border="1">
	<thead>
			
			
			<tr class="" style="text-align:center;">
				<th  width="2%" style="text-align: center;">Sr No</th> 
				<th  width="5%">Material No</th>
				<th  width="7%">Census No</th>
     		    <th  width="12%">Nomenclature</th>
     			<th  width="6%">Induction Year</th>
     			<th  width="6%">Batch No</th>
     			<th  width="5%">Cost</th>
				<th  width="5%">Total Qty</th>
				<th  width="7%">Total Cost</th>
				<th  width="7%">Appre / Depreciation</th>
				<th  width="7%">Book Value</th>
				<th  width="9%">Remarks</th>
				</tr>
	</thead> 
	<tbody id="nrTablee">
	     <c:if test="${m_1[0][0] == 'NIL'}">
			 <tr class='mmsSubHeading'>
				  <td colspan='13' style='text-align:center;'>Data Not Available...</td>
              </tr>
	     </c:if>
								 
         <% int j = 1; %>
	     <c:if test="${m_1[0][0] != 'NIL'}"> 
	       <c:forEach var="item" items="${m_1}" varStatus="num">
			   <tr>
				   <td width="2%" style="text-align: center;"><%=j %></td>
				   <td width="5%" style="text-align: center;">${item[0]}</td>
				   <td width="7%">${item[1]}</td>
				   <td width="12%">${item[2]}</td>
				   <td width="6%" style="text-align: center;">${item[3]}</td>
				   <td width="6%" style="text-align: center;">${item[4]}</td>
				   <td width="5%" style="text-align: center;">${item[5]}</td>
				   <td width="5%" style="text-align: center;">${item[6]}</td>
				   <td width="7%" style="text-align: center;">${item[7]}</td>
				   <td width="7%" style="text-align: center;">${item[8]}</td>
				   <td width="7%" style="text-align: center;">${item[9]}</td>
				   <td width="9%" style="text-align: center;">${item[10]}</td>
				   <% j = j+1;%>
			   </tr>
		   </c:forEach>
        </c:if>
	</tbody>
</table>
</div>
</div>
</div>
<div class="col-md-6"  align="center">
		<input type="hidden" id="count" name="count"> 
		<input type="button" class="btn btn-primary btn-sm" value="Print" onclick="nkprint('printableArea')">
</div>

	 
<script>
$(document).ready(function() {
	
	
	//var last_date = '${m_dt}';
	//last_date = last_date.substring(1,11);
	
	var f_d = new Date('${m_dt[0]}');
	var l_d = f_d.toLocaleDateString('en-GB', {
    	month: 'short', year: 'numeric'
    }).replace(/ /g, '-');
	$("#nlastpd").text(l_d);
	
	//$("#nlastpd").text(last_date);
	$("div#divwatermark").val('').addClass('watermarked');	
	watermarkreport();	

	

	
});

function printDiv(divName) {
	//let popupWinindow
    let innerContents = document.getElementById('printableArea').innerHTML;
    popupWindow = window.open('', '_blank', 'width=850,height=500,scrollbars=yes,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
    popupWindow.document.open();
    //popupWinindow.document.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/cue/printWatermark.css"><link rel="stylesheet" href="js/miso/assets/scss/style.css"></head><body onload="window.print()">' + innerContents + '</html>');
    popupWindow.document.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/cue/printWatermark.css"><style> table td{font-size:10px; font-weight:normal !important;} table th{font-size:12px !important;} tbody th{font-size:10px; font-weight:normal !important;}</style></head><body onload="window.focus(); window.print(); window.close();" oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false">' +innerContents+  '</html>');
    popupWindow.document.close();
}

function nkprint(divName) {
	//let popupWinindow
    let innerContents = document.getElementById('printableArea').innerHTML;
    popupWindow = window.open('', '_blank', 'width=850,height=500,scrollbars=yes,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
    popupWindow.document.open();
    //popupWindow.document.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/cue/printWatermark.css"><link rel="stylesheet" href="js/miso/assets/scss/style.css"></head><body onload="window.print()">' + innerContents + '</html>');
    popupWindow.document.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/cue/printWatermark.css"><style> table td{font-size:10px; font-weight:normal !important;} table th{font-size:12px !important;} tbody th{font-size:10px; font-weight:normal !important;}</style></head><body onload="window.focus(); window.print(); window.close();" oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false">' +innerContents+  '</html>');
    popupWindow.document.close();
}
</script>