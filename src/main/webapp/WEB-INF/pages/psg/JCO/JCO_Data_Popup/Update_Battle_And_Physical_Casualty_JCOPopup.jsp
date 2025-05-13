<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	HttpSession sess = request.getSession(false);
	if (sess.getAttribute("userId") == null){
		sess.invalidate();
		response.sendRedirect("/login"); return; 
	} 
%>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>

<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<link href="js/cue/cueWatermark.css" rel="Stylesheet"></link>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

<link rel="stylesheet" href="js/assets/css/bootstrap.min.css">
<link rel="stylesheet" href="js/layout/css/animation.css">
<link rel="stylesheet" href="js/assets/scss/style.css">
<link rel="stylesheet" href="js/assets/collapse/collapse.css">

<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>

<!-- resizable_col -->
<link rel="stylesheet" href="js/assets/adjestable_Col/jquery.resizableColumns.css">
<script src="js/assets/adjestable_Col/jquery.resizableColumns.js" type="text/javascript"></script>

<form:form action="Update_Battel_Physical_CasualtyJCO" method="post" class="form-horizontal" commandName="Update_Battel_Physical_CasualtyJCOCMD">
<div class="" id="Update_Battel_Physical_CasualtyUrl" style="display: block;">
          <div  class="watermarked" data-watermark="" id="divwatermark"><span id="ip"></span>
		           <table id="Update_Battel_Physical_CasualtyUrl" class="table no-margin table-striped  table-hover  table-bordered">
		                 <thead style="text-align: center;">
		                       <tr>
			                        <th style="font-size: 10px ;">Ser No</th>
			                        <th style="font-size: 10px">Authority</th>
									<th style="font-size: 10px">Date of Authority</th>
									<th style="font-size: 10px">Date of Casualty</th>
									<th style="font-size: 10px">Recommended For</th>
									<th style="font-size: 10px">Nature of Casualty</th>
									<th style="font-size: 10px">Name of Operation</th>
									<th style="font-size: 10px">Cause of Casualty</th>
									<th style="font-size: 10px">Unit</th>
									<th style="font-size: 10px">Diagnosis</th>
									<th style="font-size: 10px">Created By</th>
			                        <th style="font-size: 10px">Created Date</th>
									<th style="font-size: 10px">Approved By</th>
									<th style="font-size: 10px">Approved Date</th>
								 </tr>                                                        
		                  </thead> 
		                  <tbody>
			                  <c:if test="${list.size()==0}">
								<tr>
									<td style="font-size: 15px; text-align: center; color: red;" colspan="14">Data Not Available</td>
								</tr>
							</c:if>
		                       <c:forEach var="item" items="${list}" varStatus="num" >
									<tr>
										<td style="font-size: 10px;text-align: center;">${num.index+1}</td> 
										<td style="font-size: 10px;">${item[0]}</td>
										<td style="font-size: 10px;">${item[1]}</td>
										<td style="font-size: 10px;">${item[4]}</td>
									    <td style="font-size: 10px;">${item[2]}</td>
									    <td style="font-size: 10px;">${item[25]}</td>
									    <td style="font-size: 10px;">${item[3]}</td>
										<td style="font-size: 10px;">${item[26]}</td>
<%-- 										<td style="font-size: 10px;">${item[5]}</td> --%>
<%-- 										<td style="font-size: 10px;">${item[6]}</td> --%>
										<td style="font-size: 10px;">${item[7]}</td>
<%-- 										<td style="font-size: 10px;">${item[8]}</td> --%>
<%-- 										<td style="font-size: 10px;">${item[9]}</td> --%>
										<td style="font-size: 10px;">${item[10]}</td>
<%-- 										<td style="font-size: 10px;">${item[11]}</td> --%>
<%-- 									    <td style="font-size: 10px;">${item[12]}</td> --%>
<%-- 									    <td style="font-size: 10px;">${item[13]}</td> --%>
<%-- 										<td style="font-size: 10px;">${item[14]}</td> --%>
<%-- 										<td style="font-size: 10px;">${item[15]}</td> --%>
<%-- 										<td style="font-size: 10px;">${item[16]}</td> --%>
<%-- 										<td style="font-size: 10px;">${item[17]}</td> --%>
<%-- 										<td style="font-size: 10px;">${item[18]}</td> --%>
<%-- 										<td style="font-size: 10px;">${item[19]}</td> --%>
<%-- 										<td style="font-size: 10px;">${item[20]}</td> --%>
										<td style="font-size: 10px;">${item[21]}</td>
										<td style="font-size: 10px;">${item[22]}</td>
										<td style="font-size: 10px;">${item[23]}</td>
										<td style="font-size: 10px;">${item[24]}</td>
									</tr>
								</c:forEach> 
		                     </tbody>
		                 </table>
		            </div>				  
			   </div>
</form:form>
<script>
$(document).ready(function() {
	
	 colAdj("Update_Battel_Physical_CasualtyUrl");
});
	 
	 </script>
<body>

</body>
</html>       
