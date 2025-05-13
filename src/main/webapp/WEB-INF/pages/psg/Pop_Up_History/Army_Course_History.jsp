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

<form:form action="Army_CourseUrl" method="post" class="form-horizontal" commandName="Popup_Army_CourseCMD">
<div class="" id="Army_CourseUrl" style="display: block;">
           <div class="watermarked" data-watermark="" id="divwatermark"><span id="ip"></span>
                 <table id="Army_CourseUrl" class="table no-margin table-striped  table-hover  table-bordered">        
                                        <thead style="text-align: center;">
                                                <tr>
                                                    <th style="font-size: 13px ;">Ser No</th>
                                                        <th style="font-size: 13px">Authority</th>
                                                        <th style="font-size: 13px">Date of Authority</th>
                                                        <th style="font-size: 13px">Course Name</th>
                                                        <th style="font-size: 13px">Course Name Abbreviation</th> 
														<th style="font-size: 13px">Course Institute</th> 
                                                        <th style="font-size: 12px">Div/Grade</th>
                                                        <th style="font-size: 13px">Course Type</th>
                                                        <th style="font-size: 13px">Start Date</th>
                                                        <th style="font-size: 13px">Date of Completion</th>
                                                        <th style="font-size: 13px">Created By</th>
                                                        <th style="font-size: 13px">Created Date</th>
                                                        <th style="font-size: 13px">Approved By</th>
                                                        <th style="font-size: 13px">Approved Date</th>
                                                        
                                                </tr> 
                                         </thead>
                                           <tbody >
                                          <c:if test="${list.size()==0}">
								             <tr>
									              <td style="font-size: 13px; text-align: center; color: red;" colspan="14">Data Not Available</td>
								             </tr>
										</c:if>
                                                <c:forEach var="item" items="${list}" varStatus="num" >
                                                        <tr>
                                                        <td style="font-size: 13px;text-align: center;">${num.index+1}</td> 
                                                        <td style="font-size: 13px;">${item[0]}</td>
                                                        <td style="font-size: 13px;">${item[1]}</td>
                                                        <td style="font-size: 13px;">${item[2]}</td>
                                                        <td style="font-size: 12px;">${item[3]}</td>
                                                        <td style="font-size: 13px;">${item[4]}</td>
                                                        <td style="font-size: 13px;">${item[5]}</td>
                                                        <td style="font-size: 13px;">${item[6]}</td>
                                                        <td style="font-size: 13px;">${item[7]}</td>
                                                        <td style="font-size: 13px;">${item[8]}</td>
                                                        <td style="font-size: 13px;">${item[9]}</td>
                                                        <td style="font-size: 13px;">${item[10]}</td>
                                                        <td style="font-size: 13px;">${item[11]}</td>
                                                        <td style="font-size: 13px;">${item[12]}</td>
                                                        </tr>
                                                </c:forEach>
                                        </tbody>
                                </table>
                              </div>
                          </div> 
</form:form>

<script>
$(document).ready(function() {
	
	 colAdj("Army_CourseUrl");
});
	 
	 </script>
<body>

</body>
</html> 