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
/* function nkprint(nrdiv) {
	
	var nra=$("#"+nrdiv).html();
	var nrl=$("#nrlinks").html();
	popupWindow=window.open('',200,300);
	popupWindow.document.write(nrl+nra);
	popupWindow.window.print();
	popupWindow.document.close();
} */
</script>

<div class="nkpageland" id="printableArea">
	<table style="width:100%;">
		<tr align="center">
			<td></td>
			<td >
				<h5 style="text-decoration: underline;"><b>RESTRICTED</b></h5>
			</td>
			<td></td>
		</tr>
		<tr>
	   		<td align="left" >
	   			<img src="js/miso/images/indianarmy_smrm5aaa.jpg" style="height: 50px;">
	   		</td>
	   		<td align="center">
	   			<h4 style="text-decoration: underline;font-size: 22px;"><b>MISO/MMS</b></h4>
	   			<h5 style="font-size: 22px;"><span style="font-size: 22px; text-align: center;" id="p_title">MONTHLY CENSUS RETURN :</span><br>
	   			<span style="font-size: 22px;" id="nunitname"></span>&nbsp;</h5>
	   		</td>
	   		<td align="right">
	   			<img src="js/miso/images/dgis-logo.png" style="max-width: 155px; height: 50px;"> 
	   		</td>
		</tr>
		<tr style='font-weight: bold; font-size: 15px;'>
			<td >SUS No: <span id="nsusno"></span></td>
  					<td colspan="2" style="text-align: right;">Last Updated: <span id="nlastpd"></span></td>
    		</tr>
    		<tr style='font-weight: bold; font-size: 15px;'>
  					<td colspan='3' style="text-align: left;">ORBAT: <span id="nhqname"></span></td>
  				</tr>
					
			<!-- <tr style='font-weight: bold; font-size: 14px;' align="left">
			    <td colspan='3'></td>
			    <td style="text-align: right;">Last Updated: <span id="nlastpd"></span></td>
			 </tr>  -->
				</table>
		
<div id="in">
		    		<div  class="watermarked" data-watermark="" id="divwatermark" >
						<span id="ip"></span>
<table id="nrTableDataHead" class="table_collapse nrTableDataHead" border="1" style="width: 100%;">
	<thead>
			
			<tr  style="text-align:center; font-size: 12px;">
			<th  width="">Ser No</th>
			<th  width="">Census No.</th>
			<th   width="">Nomenclature</th>
			<th  width="">Unit Holding</th>    
			<th  width="">Sector Store</th>
			<th  width="">Loan Store</th>
			<th  width="">ACSFP Store</th>
			<th  width="">UN Msn</th> 
			<th  width="">WWR</th>
			<th  width="">TRSS</th> 
			<th  width="">ETSR</th>
			<th  width="">Other Res</th>
			<th  width="">Remarks</th>
	</thead> 
	<tbody id="nrTablee">
	     <c:if test="${m_1[0][0] == 'NIL'}">
			  <tr class='mmsSubHeading'>
				  <td colspan='13' style='text-align:center;'>Data Not Available...</td>
              </tr>
	     </c:if>
						                     
		 <% String temp = "";%>
		 <% int j = 1; %>
		 <c:if test="${m_1[0][0] != 'NIL'}"> 
		       <c:forEach var="item" items="${m_1}" varStatus="num">
					<tr style='font-size: 12px' id='NRRDO' name='NRRDO'>
					<c:if test="${item[1] != ''}">
						 <c:if test="${temp != item[1]}">
							   <tr class='nrGroupHeading' style="font-size: 12px;">
								   <td colspan='3' style='text-align:left;'>PRF Group&nbsp;:&nbsp;${item[1]}</td>
								   <td colspan='1' style='text-align:center;'></td>
								   <td colspan='4' style='text-align:center;'></td>
								   <td colspan='4' style='text-align:center;'></td>
								   <td colspan='1'></td>  
								   <c:set var="temp" value="${item[1]}"></c:set>
							   </tr>
						 </c:if> 
						 <tr class='nrTableLineData' style="font-size: 12px;">
		                     <td style='text-align:center;'><%=j %></td>
		                     <td>${item[4]}</td>
		                     <td>${item[5]}</td>
		                     <td style='text-align:center;'>${item[6]}</td>
		                     <td style='text-align:center;'>${item[7]}</td>
		                     <td style='text-align:center;'>${item[8]}</td>
		                     <td style='text-align:center;'>${item[9]}</td>
		                     <td style='text-align:center;'>${item[10]}</td>
		                     <td style='text-align:center;'>${item[11]}</td>
		                     <td style='text-align:center;'>${item[12]}</td>
		                     <td style='text-align:center;'>${item[13]}</td>
		                     <td style='text-align:center;'>${item[14]}</td>
		                     <td style='text-align:center;'>${item[15]}</td>
		                     <% j = j+1;%>
				         </tr>
		            </c:if>			                  
					</tr>  
		     </c:forEach>
		</c:if>	     
	</tbody>
</table>
</div>
</div>
</div>
<div class="col-md-12"  align="right">
		<input type="hidden" id="count" name="count"> 
		<input type="button" class="btn btn-primary btn-sm" value="Print" onclick="nkprint('printableArea')">
</div>

<script>

$(document).ready(function() {
	$("div#divwatermark").val('').addClass('watermarked');	
	watermarkreport();
	var a = '${m_3[0]}'.split(":");
	var sus_no = '${m_2}';
	$("#nsusno").text(sus_no);
	$("#nhqname").text('${m_5}');
	$("#nunitname").text(a[4]);
	
	
	/*	var f_d = new Date('${m_dt[0].update_date}');
		var l_d = f_d.toLocaleDateString('en-GB', {
    	month: 'short', year: 'numeric'
    }).replace(/ /g, '-'); */
	$("#nlastpd").text('${m_dt[0].update_date}');

});

function nkprint(divName) {
	let popupWinindow
    let innerContents = document.getElementById('printableArea').innerHTML;
    popupWinindow = window.open('', '_blank', 'width=850,height=500,scrollbars=yes,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
    popupWinindow.document.open();
    popupWinindow.document.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/cue/printWatermark.css"><style> table td{font-size:10px; font-weight:normal !important;} table th{font-size:12px !important;} tbody th{font-size:10px; font-weight:normal !important;} </style></head><body onload="window.focus(); window.print(); window.close();" oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false">' +innerContents+  '</html>');
    popupWinindow.document.close();
} 
</script>
	 
	 