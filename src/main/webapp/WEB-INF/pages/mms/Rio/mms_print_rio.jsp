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
<div  class="watermarked" data-watermark="" id="divwatermark" >
						<span id="ip"></span>

<table id="" width="100%" border="0" style="table-layout:fixed; margin:0px;width:100%;border-collapse: collapse;">
	<tr width="100%" style="text-align:center">
	<td></td><td></td><td></td><td></td><td></td><td></td><td colspan='2' align='right'></td>
	<tr style='border: 0px solid black;'>
		<td colspan="1"><center><img src="js/miso/images/indianarmy_smrm5aaa.jpg" style="width:40px;height:40px;"></td>		
		<td colspan='6'><b><U><center>MANAGEMENT INFORMATION SYSTEMS ORGANISATION (MISO)<br>MATERIAL MANAGEMENT SYSTEM (MMS)</U></td>
		<td colspan="1"><center><img src="js/miso/images/dgis-logo.png" style="width:75px;height:40px;"></td>
	<tr><td colspan='8'><b><U><center>RIO OF WPNS AND EQPT</U></td>
	<tr><td colspan='8'><center><B>VALID UP TO : <span id="valid_upto"></span></B></td>
	<tr><td colspan='8'><br></td>
	<tr><td colspan='5'>RIO No: <B><span id="rio_no"></span></B></td><td colspan='3' style="text-align:right;">RIO Date: <B><span id="rio_date"></span> </td>
	<tr><td colspan='8'><br></td>
	<tr><td colspan='8'>1. Ref Auth <span id="ro_auth"></span> dated <span id="ro_auth_date"></span> issued by <span id="ro_issued_by"></span></td>
	<tr><td colspan='8'>2. The following cont stores are hereby released to the units <B><u><span id="to_unit_name"></span></u></B> (<span id="to_comd_name"></span>) as per details given below:-</td>
	<tr class="nrTable1" style="text-align:center;border:1px solid black;font-weight: bold;">
		<td colspan='1' style="border:1px solid black;">Sr No</td><td colspan='4' style="border:1px solid black;">Item</td><td colspan='1' style="border:1px solid black;">Qty</td><td colspan='1' style="border:1px solid black;">Issue</td><td colspan='2' style="border:1px solid black;">Remarks</td>
	
	<tbody id="nrTablee" style="border:1px solid black;">
	   <c:if test="${m_1[0][0] == 'NIL'}">
	             <tr class='nrSubHeading'>
	                <td colspan='13' style='text-align:center;'>Data Not Available...</td>
	             </tr>
       </c:if>
       
       <% int k=1; %> 
       <% int j=1; %>
       <c:if test="${m_1[0][0] != 'NIL'}"> 
          <c:forEach var="item" items="${m_1}" varStatus="num">
              <tr style="font-size: 14px;border-top:0px solid black;">
                  <td colspan="1" style="text-align:center;border:1px solid black;"><%=j%></td>
                  <td colspan="4" style="border:1px solid black;">${item[12]} - ${item[13]} (${item[20]})</td>
                  <td colspan="1" style="border:1px solid black;text-align:center;">${item[6]}</td>
                  <td colspan="1" style="border:1px solid black;">${item[4]} / ${item[19]}</td>
                  <td colspan="2" style="border:1px solid black;">${item[21]}</td>
                  <% j=j+1; %>
              </tr>
          </c:forEach>
       </c:if>
	
	</tbody>
	
	<tr><td colspan='8'><br></td>
	<tr><td colspan='8'>3. Issuing Depot: <B> <span id="depot_name"></span></td>
	<tr><td colspan='8'>4. The eqpt will be issued along with complete accessories and CES items.</td>
	<tr><td colspan='8'>5. Please liaise with Depot before dispatch of collection parties.</td>
	<tr><td colspan='8'><br></td>
	<tr><td colspan='8'><u><b>Note</b></u>:-</td>
	<tr><td colspan='8'><br></td>
	<tr><td colspan='8'>This is System Generated Authencated Document and has Approval of<b> GSO-1(MISO / MMS)</b></td>
	<tr><td colspan='8'><br></td>
	<tr><td colspan='8'><u><b>Copy to</b></u>:-</td>
	<tr style='font-size:14px;text-align:left;padding:1px;'>
		<td colspan='8'>(1) <span id="to_unit_name1"></span>&nbsp;&nbsp;&nbsp;
		(2) <span id="depot_name1"></span>&nbsp;&nbsp;&nbsp;   
		(3) <span id="to_comd_name1"></span>&nbsp;&nbsp;&nbsp;
		(4) <span id="ro_issued_by1"></span>&nbsp;
	<tr style='text-align:left;padding:1px;'>
		<td colspan='8'>(5) MISO/MMS (office Copy)&nbsp;&nbsp;&nbsp;
</table>

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
	var f = new Date('${m_1[0][15]}');
	var e_date = f.getDate()+"-"+("0" + (f.getMonth()+1)).slice(-2)+"-"+f.getFullYear();
	$("#valid_upto").text(e_date);
	
	$("#rio_no").text('${m_1[0][0]}');	
	
	var f1 = new Date('${m_1[0][18]}') ;
	var e_date1 = f1.getDate()+"-"+("0" + (f1.getMonth()+1)).slice(-2)+"-"+f1.getFullYear();
    $("#rio_date").text(e_date1);
    
    $("#ro_auth_date").text('${m_1[0][9]}');			    	 
	$("#ro_auth").text('${m_1[0][1]}');
	$("#ro_issued_by").text('${m_1[0][10]}');
	$("#rio_type_of_issue").text('${m_1[0][4]}'); //Not used
	$("#depot_name").text('${m_1[0][14]}');
	$("#to_unit_name").text('${m_1[0][3]}');
	$("#to_comd_name").text('${m_1[0][11]}');	
	
	$("#prf_group").text('${m_1[0][5]}');
	$("#depot_name1").text('${m_1[0][14]}');
	$("#to_unit_name1").text('${m_1[0][3]}');
	$("#to_comd_name1").text('${m_1[0][11]}');	    	 
	$("#ro_issued_by1").text('${m_1[0][10]}');
});

function printDiv(divName) {
	let popupWinindow
    let innerContents = document.getElementById('printableArea').innerHTML;
    popupWinindow = window.open('', '_blank', 'width=850,height=500,scrollbars=yes,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
    popupWinindow.document.open();
    popupWinindow.document.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/cue/printWatermark.css"><link rel="stylesheet" href="js/miso/assets/scss/style.css"></head><body onload="window.print()">' + innerContents + '</html>');
    popupWinindow.document.close();
}

function nkprint(divName) {
	let popupWinindow
    let innerContents = document.getElementById('printableArea').innerHTML;
    popupWinindow = window.open('', '_blank', 'width=850,height=500,scrollbars=yes,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
    popupWinindow.document.open();
    popupWinindow.document.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/cue/printWatermark.css"><link rel="stylesheet" href="js/miso/assets/scss/style.css"></head><body onload="window.print()">' + innerContents + '</html>');
    popupWinindow.document.close();
}
</script>