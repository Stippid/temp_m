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

<form:form action="Update_QualificationCIV" method="post" class="form-horizontal" commandName="Popup_Update_QualificationCIVCMD">
<div class="" id="Update_QualificationcivUrl" style="display: block;">
           <div class="watermarked" data-watermark="" id="divwatermark"><span id="ip"></span>
                 <table id="Update_Qualificationtable" class="table no-margin table-striped  table-hover  table-bordered ">        
                                        <thead style="text-align: center;">
                                                <tr>
                                        <th style="font-size: 10px ;">Ser No</th>
                                        <th style="font-size: 10px ;">Authority</th>
										<th style="font-size: 10px ;">Date of Authority</th>
										<th style="font-size: 10px ;">Qualification Type</th> 
										<th style="font-size: 10px ;">Examination Pass</th>
										<th style="font-size: 10px ;">Degree Acquired</th>
										<th style="font-size: 10px ;">Specialization</th>
										<th style="font-size: 10px ;">Year of Passing</th>
										<th style="font-size: 10px ;">Subject</th>
										<th style="font-size: 10px ;">Div/Grade</th>
										<th style="font-size: 10px ;">Institute & Place</th>
                                        <th style="font-size: 10px">Created By</th>
                                        <th style="font-size: 10px">Created Date</th>
                                        <th style="font-size: 10px">Approved By</th>
                                        <th style="font-size: 10px">Approved Date</th>
                                        
                                                </tr> 
                                         </thead>
                                           <tbody id="Update_Qualificationtbody">
                                          <c:if test="${list.size()==0}">
								             <tr>
									              <td style="font-size: 15px; text-align: center; color: red;" colspan="15">Data Not Available</td>
								             </tr>
										</c:if>
                                                
                                        </tbody>
                                </table>
                              </div>
                          </div> 
</form:form>

<script type="text/javascript">

$(document).ready(function() {
	
	 colAdj("Update_Qualificationtable");
	
	 <c:if test="${list.size()!=0}">
        
	 <c:forEach var="item" items="${list}" varStatus="num" >

	     		var dauth=convertDateFormate('${item.date_of_authority}');
	     		var dmod=convertDateFormate('${item.modify_on}');
	     		var dcd=convertDateFormate('${item.created_on}');
	     		var exp_name ='${item.exp_name}';
	     		var d_name ='${item.d_name}';
	     		var spce_name ='${item.spce_name}';
	     		var div ='${item.div_class}';
	     		
	     		if('${item.exp_name}'=="OTHERS"){
	     			exp_name='${item.exam_other}';
	     		}
	     		if('${item.d_name}'=="OTHERS"){
	     			d_name='${item.degree_other}';
	     		}
	     		if('${item.spce_name}'=="OTHERS"){
	     			spce_name='${item.specialization_other}';
	     		}
	     		if('${item.div_class}'=="OTHERS"){
	     			div='${item.class_other}';
	     		}
				$("table#Update_Qualificationtable").append('<tr id="tr_quali'+'${num.index+1}'+'">'
	                     +'<td style="font-size: 10px ;" class="anm_srno">'+'${num.index+1}'+'</td>'
	                     +'<td style="font-size: 10px ;">'+'${item.authority}'+'</td>'
	                     +'<td style="font-size: 10px ;">'+dauth+'</td>'
	                     +'<td style="font-size: 10px ;">'+'${item.type}'+'</td>'
	                     +'<td style="font-size: 10px ;">'+exp_name+'</td>'
	                     +'<td style="font-size: 10px ;">'+d_name+'</td>'
	                     +'<td style="font-size: 10px ;">'+spce_name+'</td>'
	                     +'<td style="font-size: 10px ;">'+'${item.passing_year}'+'</td>'
	                     +'<td style="font-size: 10px ;">'+'${item.subject}'+'</td>'
	                     +'<td style="font-size: 10px ;">'+div+'</td>'
	                     +'<td style="font-size: 10px ;">'+'${item.institute}'+'</td>'
	                     
	                     +'<td style="font-size: 10px ;">'+'${item.created_by}'+'</td>' 
	                     +'<td style="font-size: 10px ;">'+dcd+'</td>'
	               
	                     +'<td style="font-size: 10px ;">'+'${item.modify_by}'+'</td>'
	                     +'<td style="font-size: 10px ;">'+dmod+'</td>'
		                   
	                   
	                     +'</td></tr>');
				 </c:forEach>
	 </c:if>
});
<!--

//-->
</script>
    