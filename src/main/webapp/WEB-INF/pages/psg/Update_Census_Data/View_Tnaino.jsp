<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
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

<div class="container" id="Popup_SeniorityUrl" style="display: block;"> 	 
			<div  class="watermarked" data-watermark="" id="divwatermark"><span id="ip"></span>
		           <table id="Change_In_NameUrl" class="table no-margin table-striped  table-hover  table-bordered report_print" style="width:100%;">
		                 <thead style="text-align: center;">
		                        <tr>
									<th >Ser No</th>
                                    <th >Authority</th>
                                    <th >Date of Authority</th>
                                    <th >TNAI No</th>                               
                                    <th >Created By</th>
                                    <th >Created Date</th>
                                    <th >Approved By</th>
                                    <th >Approved Date</th>
								 </tr>                                                        
		                  </thead> 
		                  <tbody>
			                 <c:if test="${list.size()==0}">
								<tr>
									<td style="font-size: 15px; text-align: center; color: red;" colspan="9">Data Not Available</td>
								</tr>
							</c:if>
		                       <c:forEach var="item" items="${list}" varStatus="num" >
									<tr>
										<td >${num.index+1}</td> 
										<td >${item[0]}</td>
										<td >${item[1]}</td>
										<td >${item[2]}</td>
										<td >${item[3]}</td>
										<td >${item[4]}</td>
										<td >${item[5]}</td>
										<td >${item[6]}</td>
										
									</tr>
								</c:forEach>
		                     </tbody>
		                 </table>
		            </div>				  
			   </div>
