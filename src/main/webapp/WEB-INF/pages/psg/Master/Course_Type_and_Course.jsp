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

<form:form name="Course_type" id="Course_type" action="Course_typeAction" method="post" class="form-horizontal" commandName="Course_typeCMD"> 
	<div class="animated fadeIn">
	    	<div class="container" align="center">
	    		<div class="card">
	    		<div class="card-header"><h5>ADD/UPDATE ARMY COURSE</h5> <h6 class="enter_by"><span style="font-size:12px;color:red;">(To be entered by MISO)</span></h6></div>
	          			<div class="card-body card-block">
	            			<div class="col-md-12">	  
	            			<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>CATEGORY</label>
						                </div>
						                <div class="col-md-8">
						                	<select id="rank_type" name="rank_type" class="form-control autocomplete" autocomplete="off">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getServiceCategoryList}" varStatus="num">
													<option value="${item[1]}">${item[1]}</option>
												</c:forEach>
										    </select>
						                 <!--   <input type="text" id="rank_type" name="rank_type" class="form-control autocomplete" autocomplete="off" >  -->
						                </div>
						            </div>
	              				</div>                 					
	              				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> COURSE NAME</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="course_name" name="course_name" class="form-control autocomplete"  maxlength="100" autocomplete="off" onkeyup="return specialcharecter(this)"> 
						                </div>
						            </div>
	              				</div>
	              			</div>
	              			
	              			<div class="col-md-12">	 
	              		 <!-- <div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Course Type</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="course_type" name="course_type" class="form-control autocomplete" autocomplete="off" > 
						                </div>
						            </div>
	              				</div>	 -->		
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>COURSE NAME ABBREVIATION</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="course_gp" name="course_gp" class="form-control autocomplete"  maxlength="50"  autocomplete="off" onkeyup="return specialcharecter(this)"> 
						                </div>
						            </div>
	              				</div>
	              				<%-- <div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Course Type</label>
						                </div>
						                <div class="col-md-8">
						                   <select id="course_type" name="course_type" class="form-control autocomplete" autocomplete="off">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getCourseTypeLists}" varStatus="num">
													<option value="${item[0]}">${item[1]}</option>
												</c:forEach>
										    </select>
						                </div>
						            </div>
	              				</div> --%>
	              					<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong style="color: red;">* </strong>STATUS</label>
										</div>
										<div class="col-md-8">
										<select name="status" id="status" class="form-control">
<!-- 												<option value="0">--Select--</option> -->
												<c:forEach var="item" items="${getStatusMasterList}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>	
	              			</div>
	              		<%-- 	<div class="col-md-12">	 
	              			<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong style="color: red;">* </strong>Status</label>
										</div>
										<div class="col-md-8">
										<select name="status" id="status" class="form-control">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getStatusMasterList}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>	
								</div> --%>
            			</div>
									
						<div class="card-footer" align="center" class="form-control">
								<a href="Course_Type_and_Course" class="btn btn-success btn-sm" >Clear</a> 
		              			<input type="submit" class="btn btn-primary btn-sm" value="Save" onclick="return validate();">
		              		 	<i class="action_icons searchButton"></i><input type="button" class="btn btn-info btn-sm" onclick="Search();" value="Search">  
			            </div> 		
	        	</div>
			</div>
	</div>
<div align="right" class="container">
	<i class="fa fa-file-excel-o" id="btnExport" style="font-size:x-large; color:green;text-align: right;" aria-hidden="true" title="EXPORT TO EXCEL" onclick="getExcel();"></i>
	</div><br>
	<div class="container" id="getcourse_tySearch" style="display: block;"> 	 
			<div  class="watermarked" data-watermark="" id="divwatermark"><span id="ip"></span>
		           <table id="getcourse_tySearch" class="table no-margin table-striped  table-hover  table-bordered report_print">
		                 <thead style="text-align: center;">
		                       <tr>
			                        	<th style="font-size: 15px ;width: 10%;">Ser No</th>
										<th style="font-size: 15px">Rank Type</th>
										<th style="font-size: 15px">Course Name</th>
<!-- 									 	<th style="font-size: 15px">Course Type</th>  -->
										<th style="font-size: 15px">Course Name Abbreviation</th>
										<th style="font-size: 15px ;width: 20%;">Action</th>
		                        </tr>                                                        
		                  </thead> 
		                  <tbody>
			                <c:if test="${list.size()==0}">
								<tr>
									<td style="font-size: 15px; text-align: center; color: red;" colspan="5">Data Not Available</td>
								</tr>
							</c:if>
		                       <c:forEach var="item" items="${list}" varStatus="num" >
									<tr>
										<td style="font-size: 15px;text-align: center ;width: 10%;">${num.index+1}</td> 
										<td style="font-size: 15px;">${item[0]}</td>
										<td style="font-size: 15px;">${item[1]}</td>
										 <td style="font-size: 15px;">${item[2]}</td> 
										<td style="font-size: 15px;text-align: center ;width: 20%;">${item[3]} ${item[4]}</td>
									</tr>
								</c:forEach>
		                     </tbody>
		                 </table>
		            </div>				  
			   </div>
	
</form:form>



<c:url value="getSearchCourse_typeMaster" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="rank_type1">
		<input type="hidden" name="rank_type1" id="rank_type1" />
		<input type="hidden" name="course_name1" id="course_name1" />
		<input type="hidden" name="course_gp1" id="course_gp1" />
		<input type="hidden" name="status1" id="status1" value="0"/>
</form:form> 

<c:url value="Edit_Course_Type_and_Course" var="editUrl"/>
<form:form action="${editUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="id2">
	  <input type="hidden" name="id2" id="id2">
</form:form>

<c:url value="deletecourse_typeMasterURL" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm" name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0"/> 
</form:form>
<c:url value="CourseTypereport" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2" name="search2" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form> 
<Script>
$(document).ready(function() {
	
	  if('${list.size()}' == ""){
		   $("div#getcourse_tySearch").show();
		 } 
	 if('${rank_type1}'== 0 || '${rank_type1}'=='0')
		{}else
		{
			$("#rank_type").val('${rank_type1}') ;
		} 
	 if('${course_name1}'== null || '${course_name1}'=='')
		{
			$("#course_name").val('') ;
		}else
		{
			$("#course_name").val('${course_name1}') ;
		} 
	 /* if('${course_type1}'== null || '${course_type1}'=='')
		{
			$("#course_type").val('') ;
		}else
		{
			$("#course_type").val('${course_type1}') ;
		} */ 
		/* if('${course_type1}'== 0)
		{}else
		{
			$("#course_type").val('${course_type1}') ;
		}  */
	 if('${course_gp1}'== null || '${course_gp1}'=='')
		{
			$("#course_gp").val('') ;
		}else
		{
			$("#course_gp").val('${course_gp1}') ;
		} 
	 
	 if('${status1}' != ""){
		 
		 	$("Select#status").val('${status1}') ;
			}
});

function getExcel() {
	document.getElementById('typeReport1').value = 'excelL';
	document.getElementById('search2').submit();
}
function validate(){
	
	if($("select#rank_type").val() == 0 || $("select#rank_type").val() == "0"){
		alert("Please Select Category")
		$("input#rank_type").focus();
		return false;
	}
	if($("input#course_name").val().trim() == ""){
		alert("Please Enter Course Name")
		$("input#course_name").focus();
		return false;
	}
	if($("input#course_gp").val().trim() == ""){
		alert("Please Enter Course Name Abbreviation")
		$("input#course_gp").focus();
		return false;
	}
	
/* 	if($("select#course_type").val() == 0 || $("select#course_type").val() == "0"){
		alert("Please Enter Course Type")
		$("select#course_type").focus();
		return false;
	} */
	
	 if ($("select#status").val() == "inactive") {
			alert("Only Select Active Status");
			$("select#status").focus();
			return false;
		}
	
	return true;
}
function Search(){

		$("#rank_type1").val($('#rank_type').val());
		$("#course_name1").val($('#course_name').val());
		$("#course_gp1").val($('#course_gp').val());
		$("#status1").val($('#status').val());
		
		document.getElementById('searchForm').submit();

}
function deleteData(id){
	$("#id1").val(id);
	document.getElementById('deleteForm').submit();
	}
	
function editData(id){	
	
	$("#id2").val(id);
	$("#updateForm").submit();
}
</Script>
