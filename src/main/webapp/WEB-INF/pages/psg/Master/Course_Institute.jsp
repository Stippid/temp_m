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

<form:form name="Course_Institute" id="Course_Institute" action="Course_InstituteAction" method="post" class="form-horizontal" commandName="Course_InstituteCMD"> 
	<div class="animated fadeIn">
	    	<div class="container" align="center">
	    		<div class="card">
	    		<div class="card-header"><h5>ADD COURSE INSTITUTE</h5> <h6 class="enter_by"><span style="font-size:12px;color:red;">(To be entered by MISO)</span></h6></div>
	          			<div class="card-body card-block">
	            			<div class="col-md-12">	     
	            			<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>CATEGORY</label>
						                </div>
						                <div class="col-md-8">
						                	<select id="category" name="category" class="form-control autocomplete" autocomplete="off">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getServiceCategoryList}" varStatus="num">
													<option value="${item[0]}">${item[1]}</option>
												</c:forEach>
										    </select>
						                 <!--   <input type="text" id="rank_type" name="rank_type" class="form-control autocomplete" autocomplete="off" >  -->
						                </div>
						            </div>
	              				</div>                					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> COURSE INSTITUTE</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="course_institute" name="course_institute" oninput="this.value = this.value.toUpperCase()" maxlength="100" class="form-control autocomplete" autocomplete="off" onkeyup="return specialcharecter(this)"> 
						                </div>
						            </div>
	              				</div>	              				
	              			</div>
	              			<div class="col-md-12">	     	            			
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
							
							<a href="Course_InstituteUrl" class="btn btn-success btn-sm">Clear</a> 
		              		<input type="submit" class="btn btn-primary btn-sm" value="Save"  onclick="return Validate();" />
		              		<i class="action_icons searchButton"></i><input type="button" class="btn btn-info btn-sm" onclick="Search();" value="Search">
			            </div> 		
	        	</div>
			</div>
	</div>
		<div align="right" class="container">
	<i class="fa fa-file-excel-o" id="btnExport" style="font-size:x-large; color:green;text-align: right;" aria-hidden="true" title="EXPORT TO EXCEL" onclick="getExcel();"></i>
	</div><br>
	<div class="container" id="Course_Institute_Search">
		<div class="">
			 <div id="divShow" style="display: block;"></div>
			<div class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
				<span id="ip"></span>
				<table id="Course_Institute_Search" class="table no-margin table-striped  table-hover  table-bordered report_print" width = "100%">
						<thead style="color: white;text-align: center;">
							<tr>
								<th style="font-size: 15px ;width: 10%;">Ser No</th>
								<th style="font-size: 15px ;">Category</th>
								<th style="font-size: 15px">Course Institute</th>
								<th style="font-size: 15px ;width: 20%;">Action</th>
							</tr>
						</thead>
						<tbody >
						<c:if test="${list.size()==0}">
								<tr>
									<td style="font-size: 15px; text-align: center; color: red;" colspan="3">Data Not Available</td>
								</tr>
							</c:if>
							<c:forEach var="item" items="${list}" varStatus="num" >
								<tr>
									<td style="font-size: 15px;text-align: center ;width: 10%;">${num.index+1}</td> 
									<td style="font-size: 15px;">${item[0]}</td>
									<td style="font-size: 15px;">${item[1]}</td>
									<td style="font-size: 15px;text-align: center ;width: 20%;">${item[2]} ${item[3]}</td> 
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div> 
		</div>
</form:form>

<c:url value="Search_Course_Institute_Master" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="category1">
			<input type="hidden" name="category1" id="category1" value="0"/>
			<input type="hidden" name="course_institute1" id="course_institute1" />
			<input type="hidden" name="status1" id="status1" value="0"/>
			
	</form:form> 
	
	  <c:url value="Edit_course_institute" var="editUrl"/>
	<form:form action="${editUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="id2">
		  <input type="hidden" name="id2" id="id2">
	</form:form>
	
   <c:url value="delete_course_institute" var="deleteUrl" />
	<form:form action="${deleteUrl}" method="post" id="deleteForm" name="deleteForm" modelAttribute="id1">
		<input type="hidden" name="id1" id="id1" value="0"/> 
	</form:form> 
	
	 <c:url value="course_institutereport" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2" name="search2" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form>    

<Script>
	$(document).ready(function() {
		
		 if('${list.size()}' == ""){
			   $("div#Course_Institute_Search").show();
			 }
		 if('${course_institute1}' == '')
			{}else
			{
				$("#course_institute").val('${course_institute1}') ;
			} 
		 
		 if('${category1}'== 0 || '${category1}'=='0')
			{}else
			{
				$("#category").val('${category1}') ;
			} 
	});
	if('${status2}' != ""){
		 
	 	$("Select#status").val('${status2}') ;
		}
	function Search(){
			$("#category1").val($('#category').val());
			$("#course_institute1").val($('#course_institute').val());
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
	
	function Validate() {
		if($("select#category").val() == "0"){
			alert("Please Select Category");
			$("select#category").focus();
			return false;
		}
		if ($("input#course_institute").val().trim() == "") {
			alert("Please Enter Course Institute");
			$("input#course_institute").focus();
			return false;
		}
		if ($("select#status").val() == "inactive") {
			alert("Only Select Active Status");
			$("select#status").focus();
			return false;
		}
		return true;
	}
	
	function getExcel() {
		document.getElementById('typeReport1').value = 'excelL';
		document.getElementById('search2').submit();
	} 
	</Script>


