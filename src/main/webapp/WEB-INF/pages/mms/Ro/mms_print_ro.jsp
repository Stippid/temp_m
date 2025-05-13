<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	HttpSession sess = request.getSession(false);
	if (sess.getAttribute("userId") == null) { response.sendRedirect("~/login"); return; } 
%>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css"> 
<link rel="stylesheet" href="js/common/nrcssp.css">
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
<div class="watermarked" data-watermark="" id="divwatermark" >
						<span id="ip"></span>

    <table id="nrTableDataHead" class="table_collapse nrTableDataHead" width="100%" border="0" style='font-size: 15px;row-spacing:10px;border-width:thin;'>
          <tr width="100%" style="text-align:center">
	           <td colspan='3' align='left'>&nbsp;&nbsp;&nbsp;Tele: 34222<br><br></td><td></td><td></td><td></td><td></td><td colspan='2' align='right'></td>
	      <tr> 
	           <td colspan='5'>&nbsp;&nbsp;&nbsp;<B><span id="ro_no"></span></B></td><td colspan='3' align='right'>Dated: <B><span id="ro_date"></span>&nbsp;&nbsp;&nbsp;</td>
	      <tr>
	           <td colspan='8'><br></td>
	      <tr style='border: 0px solid black;'>
		       <td colspan='8'><b><U><center>INTEGRATED HQ OF MINISTRY OF DEFENCE (ARMY)<br>MASTER GENERAL OF ORDANACE BRANCH<br>EM (IM)</U></td>
	      <tr>
	           <td colspan='8'><b><U><center>RELEASE OF CONT STORES<br><br></U></td>
          <!-- 	<tr><td colspan='8'><center><B>VALID UP TO : <span id="valid_upto"></span></B></td> -->	
	      <tr>
	          <td colspan='8'>1. Ref <span id="ro_ref_no"></span></td>
	      <tr>
	          <td colspan='8'>2. The following cont stores are hereby released to the units <U><b><span id="ro_for_n"></span></b></U> as per details given below:-</td>
	      <tr class='nrTable1' style="font-size: 15px; border:1px solid black; text-weight:bold;">
		      <td class='nrBox' colspan='1'>Ser No</td><td colspan='2'>Item Desc</td><td colspan='2'>Unit</td><td colspan='1'><center>Qty</td><td colspan='1'>Issue</td><td colspan='2'>Remarks</td>	     
	     <tbody id="nrTablee" style="border:1px solid black;">
	        <c:if test="${m_1[0][0] == 'NIL'}">
	             <tr class='nrSubHeading'>
	                <td colspan='13' style='text-align:center;'>Data Not Available...</td>
	             </tr>
            </c:if>
            
             <% String tmpprf = "";%>
             <% String tmpcen = "";%>    
             <% int i=1; %> 
             <% int j=0; %> 
             <c:if test="${m_1[0][0] != 'NIL'}"> 
                <c:forEach var="item" items="${m_1}" varStatus="num">
                    <tr style="font-size: 15px; border:1px solid black;">
                        <c:if test="${item[7] != tmpprf || item[8] != tmpcen}">
                            <tr style="font-size: 14px;border-top:1px solid black;">
                               <td width="3%" style="text-align:center;"><%=i%></td>
                               <td colspan="7">${item[7]}</td>
                               <tr style="font-size: 14px">
                                  <td colspan='1'></td><td width="3%"></td>
                                  <td colspan='6'>${item[8]} - ${item[9]}</td>
                               </tr>    
                               <c:set var="tmpprf" value="${item[7]}"></c:set>
                               <c:set var="tmpcen" value="${item[8]}"></c:set>
                               <% i=i+1; %>
                               <% j=j+1; %>
                            </tr>
                        </c:if>
                        
                        <tr style="font-size: 14px;border-top:0px solid black;">      
                           <td colspan='2'></td><td width="3%"></td>
                           <td colspan='2'><%=j%> ${item[11]}</td>
                           <td width="10%" style="text-align:center;">${item[10]} ${item[23]}</td>
                           <c:if test="${item[14] == 'PBD'}">
                           		<td>${item[21]} / ${item[22]}</td>    
                           </c:if>
                           <c:if test="${item[14] != 'PBD'}">
                           		<td>${item[21]} / ${item[14]}</td>
                           </c:if>                           
                           <td>${item[20]}</td>
                        </tr>
                    </tr>
                </c:forEach>
             </c:if>
	     
	     </tbody>
	     
	     <tr>
	          <td colspan='8'><br></td>
	     <tr>
	          <td colspan='8'>3. The wpns/eqpts will be issued along with complete accessories and CES items.</td>
	     <tr>
	          <td colspan='8'>4. Please liaise with Depot before dispatch of collection parties.</td>
	     <tr>
	          <td colspan='8'>5. This RO is valid upto <B><span id="valid_upto"></span></B>. No further extension  will be granted on expiry of the RO.</td>
	     <tr>
	          <td colspan='8'><u><b>Copy to</b></u>:-</td>
	     <tr style='font-size:14px;text-align:left;padding:1px;'>
		      <td colspan='8'>(1) <span id="to_unit_name1"></span>&nbsp;&nbsp;&nbsp;
		                      (2) <span id="depot_name1"></span>&nbsp;&nbsp;&nbsp;   
		                      (3) <span id="to_comd_name1"></span>&nbsp;&nbsp;&nbsp;
		                      (4) <span id="ro_issued_by1"></span>&nbsp;
	     <tr style='font-size:12px;text-align:left;padding:1px;'>
		      <td colspan='8'>(5) &nbsp;&nbsp;&nbsp;
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
	$("div#divwatermark").val('').addClass('watermarked');	
	watermarkreport();
	$("#ro_no").text('${m_1[0][0]}');
	$("#ro_date").text('${m_1[0][1]}');
	$("#valid_upto").text('${m_1[0][2]}');
	$("#ro_for_n").text('${m_1[0][3]}'.toUpperCase());
	$("#ro_ref_no").text('${m_1[0][18]}');
	$("#depot_name1").text('${m_1[0][15]}');
	$("#to_unit_name1").text('${m_1[0][11]}');
	$("#to_comd_name1").text('${m_1[0][13]}');
	$("#ro_issued_by1").text('${m_1[0][19]}');
	$("#ro_issued_by2").text('${m_1[0][19]}');
}); 
</script>

<script>
function nkprint(divName) {
	$("div#divwatermark").val('').addClass('watermarked');	
	watermarkreport();
	//let popupWinindow
    let innerContents = document.getElementById('printableArea').innerHTML;
    popupWindow = window.open('', '_blank', 'width=850,height=500,scrollbars=yes,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
    popupWindow.document.open();
    popupWindow.document.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/cue/printWatermark.css"><link rel="stylesheet" href="js/miso/assets/scss/style.css"></head><body onload="window.print()">' + innerContents + '</html>');
    popupWindow.document.close();
}
</script>