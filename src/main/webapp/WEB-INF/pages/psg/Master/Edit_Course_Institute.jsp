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

<form:form name="Edit_course_institute" id="Edit_course_institute" action="Edit_course_institute_Action" method="post" class="form-horizontal" commandName="Edit_course_instituteCMD"> 
	<div class="animated fadeIn">
	    	<div class="container" align="center">
	    		<div class="card">
	    		<div class="card-header"><h5>UPDATE COURSE INSTITUTE</h5> <h6 class="enter_by"><span style="font-size:12px;color:red;">(To be entered by MISO)</span></h6></div>
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
						                 <input type="hidden" id="id" name="id" class="form-control" value="0" autocomplete="off">
						                   <input type="text" id="course_institute" name="course_institute" oninput="this.value = this.value.toUpperCase()" maxlength="100" class="form-control autocomplete" autocomplete="off" onkeyup="return specialcharecter(this)" > 
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
							<a href="Course_InstituteUrl" class="btn btn-danger btn-sm">Cancel</a>
		              		<input type="submit" class="btn btn-primary btn-sm" value="Update" onclick="return Validate();"> 		              		 
			            </div> 		
	        	</div>
			</div>
	</div>

</form:form>


<script>
$(document).ready(function() {
	$("#category").val('${Edit_course_instituteCMD.category}');	
	$("#course_institute").val('${Edit_course_instituteCMD.course_institute}');	
	$("#id").val('${Edit_course_instituteCMD.id}');
	$("#status").val('${Edit_course_instituteCMD.status}');
	
});

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
	/* if ($("select#status").val() == "inactive") {
		alert("Only Select Active Status");
		$("select#status").focus();
		return false;
	} */
	return true;
}
</script>

