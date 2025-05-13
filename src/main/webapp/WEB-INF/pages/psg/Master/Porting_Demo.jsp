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

<form:form name="Agency" id="Agency" action="AgencyAction" method="post" class="form-horizontal" commandName="AgencyCMD"> 
	<div class="animated fadeIn">
	    	<div class="container" align="center">
	    		<div class="card">
	    		<div class="card-header"><h5>ADD/UPDATE AGENCY</h5> <h6 class="enter_by"><span style="font-size:12px;color:red;">(To be entered by MISO)</span></h6></div>
	          			<div class="card-body card-block">
	            			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> AGENCY NAME</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="agency_name" name="agency_name" maxlength="50"  class="form-control autocomplete" autocomplete="off" oninput="this.value = this.value.toUpperCase()"  /> 

						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong style="color: red;">* </strong>Status</label>
										</div>
										<div class="col-md-8">
										<select name="status" id="status" class="form-control">
<!-- 										<option value="0">--Select--</option> -->
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
							
							<a href="agency_url" class="btn btn-success btn-sm">Clear</a> 
		              		<input type="submit" class="btn btn-primary btn-sm" value="Save"  onclick="return Validate();">
		              		<i class="action_icons searchButton"></i><input type="button" class="btn btn-info btn-sm" onclick="Search();" value="Search">
			            </div> 		
	        	</div>
			</div>
	</div>
<!-- 		<div align="right" class="container"> -->
<!-- 	<i class="fa fa-file-excel-o" id="btnExport" style="font-size:x-large; color:green;text-align: right;" aria-hidden="true" title="EXPORT TO EXCEL" onclick="getExcel();"></i> -->
<!-- 	</div><br> -->

</form:form>

<c:url value="getSearch_Agency_Master" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="Agency_name1">
			<input type="hidden" name="Agency_name1" id="Agency_name1" />
			<input type="hidden" name="status1" id="status1" value="0"/>
			
	</form:form> 
	
	  <c:url value="Edit_Agency" var="editUrl"/>
	<form:form action="${editUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="id2">
		  <input type="hidden" name="id2" id="id2">
	</form:form>
	
   <c:url value="delete_Agency" var="deleteUrl" />
	<form:form action="${deleteUrl}" method="post" id="deleteForm" name="deleteForm" modelAttribute="id1">
		<input type="hidden" name="id1" id="id1" value="0"/> 
	</form:form> 
	
<%-- 	 <c:url value="Countryreport" var="searchUrl" /> --%>
<%-- <form:form action="${searchUrl}" method="post" id="search2" name="search2" modelAttribute="comd1"> --%>
<!-- 	<input type="hidden" name="typeReport1" id="typeReport1" value="0" /> -->
<%-- </form:form>     --%>

<Script>
	$(document).ready(function() {
		
		
		//alert("helloooo");
		$.ajaxSetup({
    	async: false
		});
		var comm_id;
		$.post("get_posting_list_census?" + key + "=" + value, {
		}).done(function(j) {
			for(var i = 0; i < j.length; i++) {
				comm_id = j[i];
			
				$.ajaxSetup({
				    async: false
				});
		
						$.ajaxSetup({
    					async: false
						});
						$.post("convert_status?" + key + "=" + value, { comm_id : comm_id
						}).done(function(j1) {
							
						}).fail(function(xhr, textStatus, errorThrown) {});
			}
		}).fail(function(xhr, textStatus, errorThrown) {});
		
		
	});
	if('${status1}' != ""){
		 
	 	$("Select#status").val('${status1}') ;
		}
	function Search(){
			
			$("#Agency_name1").val($('#agency_name').val());
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
		if ($("input#agency_name").val().trim() == "") {
			alert("Please Enter Agency Name");
			$("input#agency_name").focus();
			return false;
		}
		if ($("select#status").val() == "inactive") {
			alert("Only Select Active Status");
			$("select#status").focus();
			return false;
		}
		return true;
	}

// 	function getExcel() {
// 		document.getElementById('typeReport1').value = 'excelL';
// 		document.getElementById('search2').submit();
// 	}
</Script>


