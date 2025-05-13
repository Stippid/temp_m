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
				   			<h5 style="font-size: 20px;">WEAPON AND EQPT STATUS<br>
				   			</h5>
				   		</td>
				   		<td align="right">
				   			<img src="js/miso/images/dgis-logo.png" style="max-width: 155px; height: 50px;"> 
				   		</td>
					</tr>
					<tr style="font-size: 15px; font-weight: bold; text-align: right;">		
		   			<td colspan='4'>Last Updated: <span id="nlastpd"></span></td></tr>
					</table>
			
<div id="in">
<div  class="watermarked" data-watermark="" id="divwatermark" >
<span id="ip"></span>
<table id="nrTableDataHead" class="table_collapse nrTableDataHead report_print" width="100%" border="1">
	<thead>
			
			<tr class="" style="text-align:center;">
				<th  width="12%">Fmn HQ</th>
                <th  width="12%">Unit Name</th>
				<th  width="12%">PRF Group</th>
				<th  width="15%">Item Name</th>
				<th  width="8%">Census No</th>
				<th  width="12%">Nomenclature</th>
				<th  width="12%">Type Holding</th>
				<th  width="5%">UE</th>
				<th  width="5%">UH</th>
				<th  width="5%">Defi</th>
				<th  width="5%">Remarks</th>
				</tr>
	</thead> 
	<tbody id="nrTablee">
	     <c:if test="${m_1[0][0] == 'NIL'}">
			 <tr class='mmsSubHeading'>
				  <td colspan='13' style='text-align:center;'>Data Not Available...</td>
              </tr>
	     </c:if>
								 
         <c:if test="${m_1[0][0] != 'NIL'}"> 
			    <c:forEach var="item" items="${m_1}" varStatus="num">
			        <tr style="font-size: 12px" id="NRRDO" name="NRRDO">
			            <td width="12%">${item[11]}</td>
			            <td width="12%">${item[9]}</td>
			            <td width="12%">${item[0]}</td>
			            <td width="15%">${item[1]}</td>
				        <td width="8%" style="text-align: center;">${item[2]}</td>
				        <td width="12%">${item[3]}</td>
				        <td width="12%">${item[4]}</td>
				        <td width="5%" style="text-align: center;">${item[6]}</td>
				        <td width="5%" style="text-align: center;">${item[7]}</td>
				                
				        <c:if test="${item[8] == 'NIL'}">
				            <td width="5%" style="color:red;text-align: center;">0</td>
				        </c:if>
				        
				        <c:if test="${item[8] != 'NIL'}">
				            <td  width="5%" style="color:red;text-align: center;">${item[8]}</td>
				        </c:if>
				     
				        <td width="5%"></td>
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

	 
	 
<script >

$(document).ready(function() {
	$("div#divwatermark").val('').addClass('watermarked');	
	watermarkreport();
	
	/* var f_d = new Date('${m_1[0][8]}');
	
	var l_d = f_d.toLocaleDateString('en-GB', {
    	month: 'short', year: 'numeric'
    }).replace(/ /g, '-'); */
    
    var f_d = new Date('${m_dt[0]}');
	var l_d = f_d.toLocaleDateString('en-GB', {
    	month: 'short', year: 'numeric'
    }).replace(/ /g, '-');
	$("#nlastpd").text(l_d);

	
	//$("#nlastpd").text('${m_1[0][8]}');
    
    var f_d = new Date('${m_dt[0]}');
	var l_d = f_d.toLocaleDateString('en-GB', {
    	month: 'short', year: 'numeric'
    }).replace(/ /g, '-');
	$("#nlastpd").text(l_d);
	
	var para = '${m_12}';
	if(para == "VLS"){
		$("#p_title").text("UNIT WISE LOAN STORES");
	}
	if(para == "VSS"){
		$("#p_title").text("UNIT WISE SECTOR STORES");
	}
	if(para == "VAS"){
		$("#p_title").text("UNIT WISE ACSFP STORES");
	}
	if(para == "UWHD"){
		$("#p_title").text("UNIT WISE HOLDING DATA");
	}
	if(para == "ENG"){
		$("#p_title").text("UNIT WISE ENGR STORES");
	}
	
	 var f_d = new Date('${m_dt[0]}');
	var l_d = f_d.toLocaleDateString('en-GB', {
    	month: 'short', year: 'numeric'
    }).replace(/ /g, '-');
	$("#nlastpd").text(l_d); 
});

function printDiv(divName) {
	//let popupWinindow
    let innerContents = document.getElementById('printableArea').innerHTML;
    popupWindow = window.open('', '_blank', 'width=850,height=500,scrollbars=yes,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
    popupWindow.document.open();
    popupWindow.document.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/cue/printWatermark.css"><style> table td{font-size:10px; font-weight:normal !important;} table th{font-size:12px !important;} tbody th{font-size:10px; font-weight:normal !important;}</style></head><body onload="window.focus(); window.print(); window.close();" oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false">' +innerContents+  '</html>');
    popupWindow.document.close();
}

function nkprint(divName) {
	//let popupWinindow
    let innerContents = document.getElementById('printableArea').innerHTML;
    popupWindow = window.open('', '_blank', 'width=850,height=500,scrollbars=yes,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
    popupWindow.document.open();
    popupWindow.document.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/cue/printWatermark.css"><style> table td{font-size:10px; font-weight:normal !important;} table th{font-size:12px !important;} tbody th{font-size:10px; font-weight:normal !important;}</style></head><body onload="window.focus(); window.print(); window.close();" oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false">' +innerContents+  '</html>');
    popupWindow.document.close();
}
</script>