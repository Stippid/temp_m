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

<form:form action="Change_In_Marital_StatusCivUrl" method="post" class="form-horizontal" commandName="">
<div class="" id="Change_In_Marital_StatusCivUrl" style="display: block;"> 	 
			<div  class="watermarked" data-watermark="" id="divwatermark"><span id="ip"></span>
			<div class="card-header"><h5>Marital Details</h5> <h6 class="enter_by"><span style="font-size:12px;color:red;"></span></h6></div>
		           <table id="Change_In_Marital_StatusCivUrl" class="table no-margin table-striped  table-hover  table-bordered">
		                 <thead style="text-align: center;">
		                       <tr>
			                        <th style="font-size: 10px ;">Ser No</th>
			                        <th style="font-size: 10px">Authority</th>
									<th style="font-size: 10px">Date of Authority</th>
									<th style="font-size: 10px">Marital Status</th>
			                        <th style="font-size: 10px">Maiden Name</th>
			                        <th style="font-size: 10px">Date of Birth</th>
			                        <th style="font-size: 10px">Place of Birth</th>
			                        <th style="font-size: 10px">Nationality</th>
			                        <th style="font-size: 10px">Adhar Number</th>
			                        <th style="font-size: 10px">Pan Card</th>
									<th style="font-size: 10px">Spouse Service</th>
									<th style="font-size: 10px">Spouse Personal No</th>
									<th style="font-size: 10px">Marriage Date</th>
									<th style="font-size: 10px">Separated From Date</th>	
									<th style="font-size: 10px">Separated To Date</th>	
									<th style="font-size: 10px">Divorce Date</th>
									<th style="font-size: 10px">Created By</th>
			                        <th style="font-size: 10px">Created Date</th>
									<th style="font-size: 10px">Approved By</th>
									<th style="font-size: 10px">Approved Date</th>
									
									
									
								 </tr>                                                        
		                  </thead> 
		                  <tbody>
		                 
			                  <c:if test="${list.size()==0}">
								<tr>
									<td style="font-size: 15px; text-align: center; color: red;" colspan="22">Data Not Available</td>
								</tr>
							</c:if>
		                       <c:forEach var="item" items="${list}" varStatus="num" >
									<tr>
										<td style="font-size: 10px;text-align: center ;">${num.index+1}</td> 
										<td style="font-size: 10px;">${item[0]}</td>
										<td style="font-size: 10px;">${item[1]}</td>
									    <td style="font-size: 10px;">${item[2]}</td>
									    <td style="font-size: 10px;">${item[3]}</td>
										<td style="font-size: 10px;">${item[4]}</td>
										<td style="font-size: 10px;">${item[5]}</td>
										<td style="font-size: 10px;">${item[6]}</td>
										<td style="font-size: 10px;">${item[7]}</td>
										<td style="font-size: 10px;">${item[8]}</td>
										<td style="font-size: 10px;">${item[9]}</td>
										<td style="font-size: 10px;">${item[10]}</td>
										<td style="font-size: 10px;">${item[11]}</td>
									    <td style="font-size: 10px;">${item[12]}</td>
									    <td style="font-size: 10px;">${item[13]}</td>
										<td style="font-size: 10px;">${item[14]}</td>
										<td style="font-size: 10px;">${item[15]}</td>
										<td style="font-size: 10px;">${item[16]}</td>
										<td style="font-size: 10px;">${item[17]}</td>
										<td style="font-size: 10px;">${item[18]}</td>
										
									</tr>
								</c:forEach> 
		                     </tbody>
		                 </table>
		                 <br/>
		           <div class="card-header"><h5>Spouse Qualification Details</h5> <h6 class="enter_by"><span style="font-size:12px;color:red;"></span></h6></div>
							<table id="spouse_quali_table" class="table-bordered " style="margin-top: 10px; width: 100%;">
								<thead style="color: white; text-align: center;">
									<tr>
										<th style="width: 2%;">Sr No</th>
										<th>Spouse Name</th> 
										<th>Qualification Type</th> 
										<th>Examination Pass</th>
										<th>Degree Acquired</th>
										<th>Specialization</th>
										<th>Year of Passing</th>
										<th>Div/Grade</th>
										<th>Institute & Place</th>
										<th>Subject</th>
										<th>Created By</th>
			                        	<th>Created Date</th>
										<th>Approved By</th>
										<th>Approved Date</th>
										
									</tr>
								</thead>
								<tbody data-spy="scroll" id="spouse_quali_tbody" style="min-height: 46px; max-height: 650px; text-align: center;">
									 <c:if test="${qualilist.size()==0}">
								<tr>
									<td style="font-size: 15px; text-align: center; color: red;" colspan="14">Data Not Available</td>
								</tr>
							</c:if>
		                       <c:forEach var="item" items="${qualilist}" varStatus="num" >
									<tr>
										<td style="font-size: 10px;text-align: center ;">${num.index+1}</td> 
										<td style="font-size: 10px;">${item.maiden_name}</td>
										<td style="font-size: 10px;">${item.type}</td>
									    <td style="font-size: 10px;">${item.exp_name}</td>
									    <td style="font-size: 10px;">${item.d_name}</td>
										<td style="font-size: 10px;">${item.spce_name}</td>
										<td style="font-size: 10px;">${item.passing_year}</td>
										<td style="font-size: 10px;">${item.div_class}</td>
										<td style="font-size: 10px;">${item.institute}</td>
										<td style="font-size: 10px;">${item.subject}</td>
										<td style="font-size: 10px;">${item.created_by}</td>
										<td style="font-size: 10px;">${item.created_date}</td>
										<td style="font-size: 10px;">${item.modified_by}</td>
									    <td style="font-size: 10px;">${item.modified_date}</td>
									  
										
									</tr>
								</c:forEach> 
								</tbody>
							</table>
		            </div>				  
			   </div>
</form:form>
<script>
$(document).ready(function() {
	
	 colAdj("Change_In_Marital_StatusCivUrl");
	 colAdj("spouse_quali_table");
});
	 
	 </script>
<body>

</body>
</html>