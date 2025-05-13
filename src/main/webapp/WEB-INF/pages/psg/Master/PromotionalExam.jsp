<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
<link href="js/cue/cueWatermark.css" rel="Stylesheet"></link>

<form:form name="promoExamName" id="promoExamId" action="promoExamAction" method="post" class="form-horizontal" commandName="promoExamCMD"> 
	<div class="animated fadeIn">
	    	<div class="container" align="center">
	    		<div class="card">
	    		<div class="card-header"><h5>ADD/UPDATE PROMOTIONAL EXAM</h5> <h6 class="enter_by"><span style="font-size:12px;color:red;">(To be entered by MISO)</span></h6></div>
	          			<div class="card-body card-block">
	            			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> PROMOTIONAL EXAM</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="promoExam" name="promoExam" oninput="this.value = this.value.toUpperCase()" maxlength="50" class="form-control autocomplete" autocomplete="off" > 
						                </div>
						            </div>
	              				</div>
	              				
	              			
	              				<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong style="color: red;">* </strong>STATUS</label>
										</div>
										<div class="col-md-8">
										<select name="status" id="status" class="form-control">
												<c:forEach var="item" items="${getStatusMasterList}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>	
	              				
	              				
	              			</div>	           				              			
            			</div>
									
						<div class="card-footer" align="center" class="form-control">
							<a href="promoExamUrl" class="btn btn-success btn-sm" >Clear</a> 
		              		<input type="submit" class="btn btn-primary btn-sm" value="Save" onclick="return validate();">
		              		<i class="action_icons searchButton"></i><input type="button" class="btn btn-info btn-sm" onclick="Search();" value="Search">  
			            </div> 		
	        	</div>
			</div>
	</div>
<div align="right" class="container">
	<i class="fa fa-file-excel-o" id="btnExport" style="font-size:x-large; color:green;text-align: right;" aria-hidden="true" title="EXPORT TO EXCEL" onclick="getExcel();"></i>
	</div><br>
<div class="container" id="getbankSearch" style="display: block;"> 	 
			<div  class="watermarked" data-watermark="" id="divwatermark"><span id="ip"></span>
		           <table id="getbankSearch" class="table no-margin table-striped  table-hover  table-bordered report_print">
		                 <thead style="text-align: center;">
		                       <tr>
			                         <th style="font-size: 15px ;width: 10%;">Ser No</th>
									<th style="font-size: 15px">Promotional Exam</th>
<!-- 									<th style="font-size: 15px">Status</th> -->
									<th style="font-size: 15px ;">Action</th>
		                        </tr>                                                        
		                  </thead> 
		                  <tbody>
			                 <c:if test="${list.size()==0}">
								<tr>
									<td style="font-size: 15px; text-align: center; color: red;" colspan="3">Data Not Available</td>
								</tr>
							</c:if>
		                       <c:forEach var="item" items="${list}" varStatus="num" >
									<tr>
										<td style="font-size: 15px;text-align: center ;width: 10%;">${num.index+1}</td> 
										<td style="font-size: 15px;">${item[0]}</td>
<%-- 										<td style="font-size: 15px;">${item[1]}</td> --%>
										<td style="font-size: 15px;text-align: center ;">${item[2]} ${item[3]}</td> 
									</tr>
								</c:forEach>
		                     </tbody>
		                 </table>
		            </div>				  
			   </div>
</form:form>

<c:url value="getpromoExamSearch" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="promoExam1">
		<input type="hidden" name="promoExam1" id="promoExam1" />
		<input type="hidden" name="status1" id="status1" />
</form:form> 

<c:url value="Edit_promoExam" var="editUrl"/>
<form:form action="${editUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="id2">
	  <input type="hidden" name="id2" id="id2">
</form:form>

<c:url value="deletePExamURL" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm" name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0"/> 
</form:form>
<c:url value="promoExamreport" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2" name="search2" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form>

<Script>
$(document).ready(function() {
	$("#promoExam").val('${promoExam1}');
	if('${status1}' != '')
	$("#status").val('${status1}');
});
function getExcel() {
	document.getElementById('typeReport1').value = 'excelL';
	document.getElementById('search2').submit();
}


function validate(){
	if ($("input#promoExam").val().trim() == "") {
		alert("Please Enter Promotinal Exam");
		$("input#promoExam").focus();
		return false;
	}
  
  if ($("select#status").val() == "inactive") {
		alert("Only Select Active Status");
		$("select#status").focus();
		return false;
	}
	return true;
}

function Search(){
	
	if($('#promoExam').val() == "" && $('#status').val() == "0"){
		alert("Please Enter Something to Search");
		$('#promoExam').focus();
		return  false;
	}
	
	    $("#promoExam1").val($('#promoExam').val());
	    $("#status1").val($('#status').val());
		document.getElementById('searchForm').submit();

}
function deleteData(id){
	$("#id1").val(id);
	document.getElementById('deleteForm').submit();
	}
	
function editData(id){	
	
	$("#id2").val(id);
	document.getElementById('updateForm').submit();
}

</Script>
