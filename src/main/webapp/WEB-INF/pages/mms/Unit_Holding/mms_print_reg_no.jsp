<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	HttpSession sess = request.getSession(false);
	if (sess.getAttribute("userId") == null) { response.sendRedirect("~/login"); return; } 
%>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>


<div class="nkpageland" id="printableArea">
<!-- <div class="nrwm">Testing Data</div> -->
<div id="nrlinks"> 
	<link rel="stylesheet" href="js/common/nrcssp.css">
</div> 
<table id="nrTableDataHead" class="nrTableDataHead" width="100%" border="0" style='font-size: 15px;row-spacing:10px;border-width:thin;' >
	<thead>
			<tr style='border: 0px solid white;'>
					<td colspan='6' align="center" class="nrTableHeading"><span id="p_title"></span></td>
			<tr>
			    <td colspan='6'></td> 
			</tr>
			
			<tr>
			    <td colspan='6'></td> 
			</tr>
			
			<tr>
			    <td colspan='6'></td> 
			</tr>
			    
			<tr class="" style="text-align:center">
				<th class="nrTable1" width="10%">Eqpt Reg No</th>
	</thead> 
	<tbody id="nrTablee">
			 <c:forEach var="item" items="${m_1}" varStatus="num">
				  <tr class='nrTableLineData' name='NRRDO'>
					  <td style="text-align: center;">${item}</td>
				  </tr>
			  </c:forEach>
	</tbody>
</table>

</div>
	 
<script >

$(document).ready(function() {
	$("#p_title").text("Eqpt Reg No List");
});
</script>