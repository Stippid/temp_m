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

<form:form action="Update_Awards_MedalUrl" method="post" class="form-horizontal" commandName="Popup_Change_Identity_CardCMD">
<div class="" id="Update_Awards_MedalUrl" style="display: block;"> 	 
			<div  class="watermarked" data-watermark="" id="divwatermark"><span id="ip"></span>
		           <table id="Change_In_Identity_CardUrl" class="table no-margin table-striped  table-hover  table-bordered">
		                 <thead style="text-align: center;">
		                       <tr>
			                        <th style="font-size: 10px ;">Ser No</th>
			                        <th style="font-size: 10px">Category</th>
									<th style="font-size: 10px">Award/Medal</th>
									<th style="font-size: 10px">Date of Award</th>
									<th style="font-size: 10px">Unit</th>
									<th style="font-size: 10px">BDE</th>
									<th style="font-size: 10px">Div/Sub Area</th>
									<th style="font-size: 10px">Corps/Area</th>
								    <th style="font-size: 10px">Command Area</th>
									<th style="font-size: 10px">Authority</th>
		                            <th style="font-size: 10px">Date of Authority</th>
									<th style="font-size: 10px">Created By</th>
			                        <th style="font-size: 10px">Created Date</th>
									<th style="font-size: 10px">Approved By</th>
									<th style="font-size: 10px">Approved Date</th>
									
								 </tr>                                                        
		                  </thead> 
		                  <tbody>
			                  <c:if test="${list.size()==0}">
								<tr>
									<td style="font-size: 15px; text-align: center; color: red;" colspan="15">Data Not Available</td>
								</tr>
							</c:if>
		                       <c:forEach var="item" items="${list}" varStatus="num" >
									<tr>
										<td style="font-size: 10px;text-align: center ;">${num.index+1}</td> 
										<td style="font-size: 10px;">${item.category_8}</td>
										<td style="font-size: 10px;">${item.medal_name}</td>
										<td style="font-size: 10px;">${item.date_of_award}</td>
										<td style="font-size: 10px;">${item.unit}</td>
										<td style="font-size: 10px;">${item.bde_name}</td>
										<td style="font-size: 10px;">${item.div_name}</td>
										<td style="font-size: 10px;">${item.coprs_name}</td>
										<td style="font-size: 10px;">${item.cmd_name}</td>
										<td style="font-size: 10px;">${item.authority}</td>
									    <td style="font-size: 10px;">${item.date_of_authority}</td>
										<td style="font-size: 10px;">${item.created_by}</td>
									    <td style="font-size: 10px;">${item.created_date}</td>
									    <td style="font-size: 10px;">${item.modify_by}</td>
										<td style="font-size: 10px;">${item.modify_on}</td>
										
										
									</tr>
								</c:forEach>
		                     </tbody>
		                 </table>
		            </div>				  
			   </div>
</form:form>

<script>
$(document).ready(function() {
	
	 colAdj("Change_In_Identity_CardUrl");
});
	 
	 </script>
<body>

</body>
</html>