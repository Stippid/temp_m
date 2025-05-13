<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	HttpSession sess = request.getSession(false);
	if (sess.getAttribute("userId") == null) { response.sendRedirect("~/login"); return; } 
%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css"> 
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js"></script>
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

<div id="nrlinks"> 
	<link rel="stylesheet" href="js/common/nrcssp.css">
</div> 
				<table>
				<tr>
                    <td align="center"><img src="js/miso/images/indianarmy_smrm5aaa.jpg" style="width:30px;height:30px;"></td>
					<td colspan='11' align="center" class="nrTableHeading">DEPOT MONTHLY CENSUS RETURN : <span id="nunitname"></span></td>
					<td align="center"><img src="js/miso/images/dgis-logo.png" style="width:60px;height:30px;"></td>  
				</tr>
				  <tr><td colspan='13'></tr>		
		  <tr style='font-weight: bold;'><td colspan='11'>SUS No: <span id="nsusno"></span></td>
		  <td align='right' colspan='5'>Last Updated: <span id="nlastpd"></span></td>
	      </tr>
				
				
				</table>

<div  class="watermarked" data-watermark="" id="divwatermark" >
						<span id="ip"></span>
<table id="nrTableDataHead" class="nrTableDataHead" width="100%" border="1" style='font-size: 15px;row-spacing:10px;border-width:thin;'>
    <thead>
         
		  <tr style="font-size: 12px;text-align:center;">
	            <th  width="15%" rowspan='2'>PRF Group</th>
			    <th  width="5%" rowspan='2'>Material No</th>
	   			<th  width="5%" rowspan='2'>Census No.</th>
	   			<th  width="12%" rowspan='2'>Nomenclature</th>
	   		    <th  width="3%" rowspan='2'>Free Stk</th>
	   		    <th  width="6%" colspan='2'>Repairable Stock</th>
			    <th  width="12%" colspan='4'>Reserve</th>
			    <th  width="3%" rowspan='2'>BLR /R4</th>
			    <th  width="3%" rowspan='2'>BER /US</th>
			    <th  width="4%" rowspan='2'>Remarks</th>
		   		<tr  style="font-size: 12px;text-align:center;">	    
		        <th  width="3%" rowspan='2'>Depot</th>
			    <th  width="3%" rowspan='2'>Wksp</th>	     
			    <th  width="3%">WWR</th>
			    <th  width="3%">TRSS</th>
			    <th  width="3%">ETSR</th>
			    <th  width="3%">Oth Res</th>
    </thead>
	
	<tbody id="nrTable">
	    <c:if test="${m_1[0][0] == 'NIL'}">
			 <tr class='nrSubHeading'>
				<td colspan='13' style='text-align:center;'>Data Not Available...</td>
			 </tr>
		</c:if>
        
        <c:if test="${m_1[0][0] != 'NIL'}"> 
	        <c:forEach var="item" items="${m_1}" varStatus="num">
			 <tr>
			     <td style="text-align: left;" width="15%">${item[1]}</td>
			     <td width="5%"> </td> 
				 <td style="text-align: center;" width="5%">${item[2]}</td>
				 <td style="text-align: left;" width="12%">${item[3]}</td>
				 <td style="text-align: center;" width="3%">${item[4]}</td>
				 <td style="text-align: center;" width="3%">${item[5]}</td>
				 <td style="text-align: center;" width="3%">${item[6]}</td>
				 <td style="text-align: center;" width="3%">${item[7]}</td>
				 <td style="text-align: center;" width="3%">${item[8]}</td>
				 <td style="text-align: center;" width="3%">${item[9]}</td>
				 <td style="text-align: center;" width="3%">${item[10]}</td>
				 <td style="text-align: center;" width="3%">${item[11]}</td>
				 <td style="text-align: center;" width="3%">${item[12]}</td>
				 <td style="text-align: center;" width="4%">${item[13]}</td>
			</tr>
			</c:forEach>
         </c:if>
	</tbody>
		
</table>
</div>
</div>

<div class="col-md-12"  align="right">
			<input type="hidden" id="count" name="count"> 
			<input type="button" class="btn btn-primary btn-sm" value="Print" onclick="NkprintDiv('printableArea')">
</div>

	 
	 
<script >

$(document).ready(function() {
	$("div#divwatermark").val('').addClass('watermarked');	
	watermarkreport();
	
	var a = '${m_3[0]}'.split(":");
	var sus_no = '${m_2}';
	$("#nsusno").text(sus_no);
	$("#nunitname").text(a[4]);
	
	var f_d = new Date('${m_dt[0]}');
	var l_d = f_d.toLocaleDateString('en-GB', {
    	month: 'short', year: 'numeric'
    }).replace(/ /g, '-');
	$("#nlastpd").text(l_d);
	
});

function printDiv(divName) {
	let innerContents = document.getElementById('printableArea').innerHTML;
	popupWindow = window.open('', '_blank', 'width=850,height=500,scrollbars=yes,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
	popupWindow.document.open();
	popupWindow.document.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/miso/assets/scss/style.css"></head><body onload="window.print()">' + innerContents + '</html>');
	popupWindow.document.close();
}

function NkprintDiv(divName) {
	let innerContents = document.getElementById('printableArea').innerHTML;
	popupWindow = window.open('', '_blank', 'width=850,height=500,scrollbars=yes,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
	popupWindow.document.open();
	popupWindow.document.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/cue/printWatermark.css"><link rel="stylesheet" href="js/miso/assets/scss/style.css"></head><body onload="window.print()">' + innerContents + '</html>');
	popupWindow.document.close();
}
</script>
	 
	 