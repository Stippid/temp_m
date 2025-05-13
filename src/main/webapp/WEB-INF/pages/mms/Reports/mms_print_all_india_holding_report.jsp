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
				   			<h5 style="font-size: 22px;">ALL INDIA HOLDING REPORT : WPNs AND EQPT<br>
				   			</h5>
				   		</td>
				   		<td align="right">
				   			<img src="js/miso/images/dgis-logo.png" style="max-width: 155px; height: 50px;"> 
				   		</td>
					</tr>
						
		   			<tr style='font-weight: bold; font-size: 15px; text-align: right;'>
		   			<td colspan='8'>Status as on : <span id="nlastpd"></span></td></tr>
		   
				</table>
			    
			    
			    
<div id="in">
<div  class="watermarked" data-watermark="" id="divwatermark" >
<span id="ip"></span>
<table id="nrTableDataHead" class="table_collapse nrTableDataHead" width="100%" border="1" >
	<thead>
			
			<tr  style="text-align:center;">
				<th  width="4%">Ser No</th>
   			    <th  width="10%">Census No</th>
   			    <th  width="5%">COS Section</th>
   				<th  width="15%">Nomenclature</th>
   				<th  width="7%">Class Of Eqpt</th>
   				<th  width="10%">Cat Part No</th>
   				<th  width="5%">A/U</th>	                    					
		        <th  width="8%">Status</th>
		        <th  width="8%" colspan='2'>Type of Holding</th>
		        <th  width="8%">Holding Qty</th>
	</thead> 
	<tbody id="nrTablee">
	     <c:if test="${m_1[0][0] == 'NIL'}">
			 <tr class='mmsSubHeading'>
				  <td colspan='13' style='text-align:center;'>Data Not Available...</td>
              </tr>
	     </c:if>								 
         <% String oprf = "";%>
		 <% String ocen = "";%>
		 <% int j = 1;
		 	String samePrf="Y";
		 %>		 
		 <c:if test="${m_1[0][0] != 'NIL'}">      
		         	 
		      <c:forEach var="item" items="${m_1}" varStatus="num">
		         <% samePrf="Y"; %>
		          <tr class="" id="NRRDO" name="NRRDO">
		              <c:if test="${oprf != item[1]}">
		              	 <tr>
		                 <td colspan='5' style="text-decoration: underline; font-weight: bold;" width="54%">PRF Group&nbsp;:&nbsp;${item[2]}</td>
		                 <td colspan='2' style="font-weight: bold;" width="15.5%">Total PRF UE:<span id='a'></span></td>
		                 <td colspan='2' style="font-weight: bold;" width="15.5%">Total PRF UH:<span id='a'></span></td>
		                 <tr></tr>
		                 <c:set var="oprf" value="${item[1]}"></c:set>				                 
		              </c:if>
		          
		              <c:if test="${ocen != item[3]}">           
		                 <% samePrf="N"; %>
		              </c:if>
		              <% if (samePrf.equalsIgnoreCase("N")) { %>
		                 <td  style="text-align: center;" width="4%"><%=j %></td> 
						<td  style="text-align: center;" width="10%">${item[3]}</td>
						<td  style="text-align: center;" width="5%">${item[0]}</td>
						<td  style="text-align: left;" width="15%">${item[4]}</td>
						<th  style="text-align: center;" width="7%">${item[11]}</th>										              
						<td  style="text-align: left;" width="10%">${item[5]}</td>
						<td  style="text-align: center;" width="5%">${item[6]}</td>										                 
					    <td  style="text-align: center;" width="8%">${item[7]}</td>
										                 
						<c:set var="ocen" value="${item[3]}"></c:set>
		                 <% j = j+1;%>
		          <% } else { %>    
		              <%-- <c:if test="${ocen == item[3]}"> --%>
		                 <td  width="4%"></td>
						 <td  width="10%"></td>
						 <td  width="5%"></td>
						 <td  width="15%"></td>
						 <td  width="7%"></td>
						 <td  width="10%"></td>
						 <td  width="5%"></td>
						 <td  width="8%"></td>
		              <%-- </c:if> --%>
		          <% } %>
		              <td class="nrTable2" colspan='2' width="8%">&nbsp;&nbsp;${item[8]}</td>
		              <td class="nrTable2" style="text-align: center;" width="8%">${item[10]}</td>
		              
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
	$("div#divwatermark").val('').addClass('watermarked');	
	watermarkreport();
	
	var f_d = new Date('${m_dt[0]}');
	var l_d = f_d.toLocaleDateString('en-GB', {
    	month: 'short', year: 'numeric'
    }).replace(/ /g, '-');
	$("#nlastpd").text(l_d);
	
	//$("#nlastpd").text(l_d);
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
    //popupWindow.document.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/miso/assets/scss/style.css"><link rel="stylesheet" href="js/cue/printWatermark.css"></head><body onload="window.print()">' + innerContents + '</html>');
    popupWindow.document.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/cue/printWatermark.css"><style> table td{font-size:10px; font-weight:normal !important;} table th{font-size:12px !important;} tbody th{font-size:10px; font-weight:normal !important;}</style></head><body onload="window.focus(); window.print(); window.close();" oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false">' +innerContents+  '</html>');
    popupWindow.document.close();
}
</script>