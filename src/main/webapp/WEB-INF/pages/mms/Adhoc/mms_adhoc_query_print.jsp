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
function nkprint(nrdiv) {
	var nra=$("#"+nrdiv).html();
	var nrl=$("#nrlinks").html();
	var nrw=window.open('',200,300);
	nrw.document.write(nrl+nra);
	nrw.window.print();
	nrw.document.close();
}
</script>
<form:form action="" method="post" enctype="multipart/form-data" class="form-horizontal" commandName="">
   <div class="nkpageland" id="printableArea">
  <div>		
     <div class="container" align="center"id="headerView" style="display: block;">  
        <div class="card">
				<div class="card-header mms-card-header">
		               <b>WEAPON & EQPT STATUS</b>
		        </div> 
		   </div>
      </div>
  </div>

<div id ="tdheaderView" style="display: none;"  align="center" class="nrTableHeading">WEAPON & EQPT STATUS</div>
<div  class="watermarked" data-watermark="" id="divwatermark" >
						
				<span id="ip"></span>		
				<input type="hidden" id="selectedid" name="selectedid">
						<input type="hidden" id="statushid" name="statushid">
						<input type="hidden" id="nrSrcSel" name="nrSrcSel">
<table id="nrTableDataHead" class="table_collapse nrTableDataHead report_print" border="1" >
	<thead>			
			<tr  style="text-align:center">
			<th  width="3%">Ser No</th>
			<th width="12%">Command Name</th>
			<th  width="12%">Corps Name</th>
			<th  width="12%">Div Name</th>    
			<th  width="12%">BDE Name</th>
			<th  width="12%">Nomenclature</th>
			<th  width="7%">Type Holding</th> 
			<th  width="7%">Type Eqpt</th>
			<th  width="5%">UE</th>
			<th  width="5%">UH</th>
			<th  width="5%">Surplus</th>
			<th  width="5%">Defi</th>
			<th  width="3%">%</th>
	</thead> 
	<tbody id="nrTablee" >
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
						 <c:if test="${temp != item[6]}">
							   <tr class='nrGroupHeading'>
								   <td colspan='6' style='text-align:left;'>PRF Group&nbsp;:&nbsp;${item[7]}</td>
								   <c:set var="temp" value="${item[6]}"></c:set>
							   </tr>
						 </c:if> 
						 <tr class='nrTableLineData'>
		                     <td style="width: 3%"><%=j %></td>
		                     <td style="width: 12%">${item[0]}</td>
		                     <td style="width: 12%">${item[1]}</td>
		                     <td style="width: 12%">${item[2]}</td>
		                     <td style="width: 12%">${item[3]}</td>
		                     <td style="width: 12%">${item[9]}</td>
		                     <td style='text-align:center;width: 7%'>${item[11]}</td>
		                     <td style='text-align:center;width: 7%'>${item[13]}</td>
		                     <td style='text-align:center;width: 5%'>${item[14]}</td>
		                     <td style='text-align:center;width: 5%'>${item[15]}</td>
		                     <td style='text-align:center;width: 5%'>${item[16]}</td>
		                     <td style='text-align:center;width: 5%'>${item[17]}</td> 
		                     <td style="width: 3%"></td>                     			                     
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
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>	 
	 
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
	
	$("#nrInput").on("keyup", function() {
		var value = $(this).val().toLowerCase();
	    $("#nrTable tr").filter(function() {
	    	$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
	    });
    });
});
function printDiv() {
	 $("#SearchViewtr").hide();
	  $("#tdheaderView").show();
	  $("#headerView").hide();	  
	 //let popupWinindow
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