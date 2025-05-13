<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	HttpSession sess = request.getSession(false);
	if (sess.getAttribute("userId") == null) { response.sendRedirect("~/login"); return; } 
%>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js"></script>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css"> 
<link rel="stylesheet" href="js/miso/assets/scss/style.css">
<link rel="stylesheet" href="js/common/nrcssp.css">
<script>
	var username="${username}";
	var curDate = "${curDate}";
</script>
<form:form action="" method="post" enctype="multipart/form-data" class="form-horizontal" commandName="">
   <div class="nkpageland" id="printableArea">
  <div>		
     <div class="container" align="center"id="headerView" style="display: block;">  
        <div class="card">
				<div class="card-header mms-card-header">
		               <b>Unit's Observation Status</b>
		        </div> 
		   </div>
      </div>
  </div>

			<div id ="tdheaderView" style="display: none;"  align="center" class="nrTableHeading">Unit's Observation Status</div>
<div  class="watermarked" data-watermark="" id="divwatermark" >
			<span id="ip"></span>		
<table border="1" class="table_collapse report_print">
	<thead>

			<tr  style="text-align:center">
			<th  width="3%">Ser No</th>
			<th width="10%">Month</th>
			<th  width="10%">Year</th>
			<th  width="12%">Command Name</th>  
			<th  width="12%">Corps Name</th>
			<th  width="12%">Div Name</th>
			<th  width="12%">BDE Name</th>
			<th  width="12%">SUS No</th>
			<th  width="12%">Unit Name</th> 
			<th  width="10%">Observation</th>
			<th  width="10%">Response</th>
			<th  width="10%">Status</th>
			<th  width="12%">Last Updated</th>
			<th  width="12%">Remarks</th>
	</thead> 
	<tbody id="nrTablee">
	     <c:if test="${m_12[0][0] == 'NIL'}">
			  <tr class='mmsSubHeading'>
				  <td colspan='13' style='text-align:center;'>Data Not Available...</td>
              </tr>
	     </c:if>
						                     
		 <% String temp = "";%>
		 <% int j = 1; %>
		 <c:if test="${m_12[0][0] != 'NIL'}"> 
		       <c:forEach var="item" items="${m_12}" varStatus="num">
					<tr style='font-size: 12px' id='NRRDO' name='NRRDO'>
					<c:if test="${item[1] != ''}">
						 <tr class='nrTableLineData'>
		                     <td style='text-align:center;'width="3%"><%=j %></td>
		                     <td style='text-align:center;'width="10%">${item[0]}</td>
		                     <td style='text-align:center;'width="10%">${item[1]}</td>
		                     <td width="12%">${item[2]}</td>
		                     <td width="12%">${item[3]}</td>
		                     <td width="12%">${item[4]}</td>
		                     <td width="12%">${item[5]}</td>
		                     <td style='text-align:center;' width="12%">${item[8]}</td>
		                     <td  width="12%">${item[9]}</td>
		                     <td style='text-align:center;' width="10%">${item[12]}</td>
		                     <td style='text-align:center;' width="10%">${item[13]}</td>
		                     <td style='text-align:center;'width="10%">${item[15]}</td>
		                     <td style='text-align:center;'width="12%">${item[16]}</td>
		                     <td width="12%"></td>
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

<div class="card-footer" style="margin-top: 10px;">
		<input type="hidden" id="count" name="count"> 
		
  <input type="button" class="btn btn-primary btn-sm" value="Print Page" id="btn_p" onclick="printDiv();">  
</div> 
</form:form>
<script>
$(document).ready(function() {
	$("div#divwatermark").val('').addClass('watermarked');	
	watermarkreport();
	var sus_no = '${m_2}';
	$("#nsusno").text(sus_no);
	$("#nhqname").text('${m_5}');
	$("#nunitname").text('${m_3[4]}');

	var f_d = new Date('${m_12[0][16]}');
	var l_d = f_d.toLocaleDateString('en-GB', {
    	month: 'short', year: 'numeric'
    }).replace(/ /g, '-');
	$("#nlastpd").text(l_d);
});

function printDiv() {
	$("#SearchViewtr").hide();
	$("#tdheaderView").show();
	$("#headerView").hide();
	  
	let innerContents = document.getElementById('printableArea').innerHTML;
	popupWindow = window.open('', '_blank', 'width=850,height=500,scrollbars=yes,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
	popupWindow.document.open();
	popupWindow.document.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/cue/printWatermark.css"><style> table td{font-size:10px; font-weight:normal !important;} table th{font-size:12px !important;} tbody th{font-size:10px; font-weight:normal !important;}</style></head><body onload="window.focus(); window.print(); window.close();" oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false">' +innerContents+  '</html>');
	popupWindow.document.close();
	$("#SearchViewtr").show();
	$("#tdheaderView").hide();
	$("#headerView").show();
}
</script>

<c:url value="WpnEqptdatalist" var="backUrl" />
<form:form action="${backUrl}" method="post" id="m4_unit1" name="m4_unit1" modelAttribute="m4_c_code">
      <input type="hidden" name="m4_c_code" id="m4_c_code"/>
	  <input type="hidden" name="m4_q_code" id="m4_q_code"/>
	  <input type="hidden" name="m4_d_code" id="m4_d_code"/>
	  <input type="hidden" name="m4_b_code" id="m4_b_code"/>
	  <input type="hidden" name="m4_p_code" id="m4_p_code"/>
	  <input type="hidden" name="m4_d_from" id="m4_d_from"/>
	  <input type="hidden" name="m4_d_to" id="m4_d_to"/>
	  <input type="hidden" name="m4_hldg" id="m4_hldg"/>
	  <input type="hidden" name="m4_prfs" id="m4_prfs"/>
</form:form>
	 
	 