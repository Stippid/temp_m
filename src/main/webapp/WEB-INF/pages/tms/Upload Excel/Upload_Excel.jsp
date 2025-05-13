	
	
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
	<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
	<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
	<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 
	
	<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
	
	<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
	
	<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></links>
	<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
    <script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
	<script src="js/upload_xls/xls.min.js" type="text/javascript"></script>

 
<%-- <form:form name="upload_excel" id="upload_excel" action="upload_excel_act" method='POST' enctype="multipart/form-data" > --%>
<%-- <form:form name="upload_excel" id="upload_excel"  action="upload_excel_act" method='POST' commandName="upload_excel_cmnd" enctype="multipart/form-data"> --%>

<form:form
	action="upload_excel_act?${_csrf.parameterName}=${_csrf.token}"
	method="POST" class="form-horizontal" 
	enctype="multipart/form-data">


	<div class="container" align="center">
		<div class="card">
			<div class="card-header">
				<strong><u>Upload Excel</u></strong>
				<strong><h3>Upload Excel </h3></strong>
			</div>
			<div class="card-body card-block">
				<div class="col-md-12">
					<div class="col-md-6">
              					<div class="row form-group">
	                					<div class="col col-md-6">	                					
						                	<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Download Format </label>
						               
			                                        <div class="form-check-inline form-check">
			                                         
<!-- 			                                           <input type="button" class="btn btn-primary btn-sm" value="Excel" id="btnExport_qtr" onclick="getExcelUtilization();">  -->
			                                          <div class="input-style-2">
													
																<ul class="btn btn-primary btn-sm">
																	<li class="fa fa-file-excel-o">
															
																		<a href="js/upload_excel.xls" download  class="btn btn-primary btn-sm" >Download</a>
														
																	</li>

																</ul>
														</div>
										 			</div>
										 </div>
										 <div class="col-12 col-sm-12 col-md-6 col-lg-8">
												<div class="input-style-2">
														<label for="username">Upload File<span class="mandatory">*</span></label>
																<input type="file" accept=".xls" id="u_file1" name="u_file1" class="form-control" autocomplete="off">
																		<span class="errorClass"
																					id="doc_path_doc1_lbl1"></span>
																		<span class='tikClass' id="doc_path_doc_lbltik1"></span>
												</div>			
								<!-- end select -->
										</div>				   
								</div>
					</div>
				</div>
			</div>
	 </div>                           
				
	
				<!-- end of other details -->
	
				<div class="form-control card-footer" align="center">
					<input type="reset" class="btn btn-success btn-sm" value="Clear">
					<input type="submit" class="btn btn-primary btn-sm" value="Save" onclick="return Validate();"> 
					
				</div>
				
				<!-- end of card-footer -->
	
			</div>
			<!-- end of card -->
		</div>
		<!-- end of container -->
	</form:form>
	
<c:url value="Excel_quarter_Report" var="Excel_quarter_Report" />
<form:form action="${Excel_quarter_Report}" method="post"
	id="search_qtr" name="search_qtr" modelAttribute="comd1">
	<input type="hidden" name="typeReport_qtr" id="typeReport_qtr"value="0" />
	<input type="hidden" id="ba_no2" name="ba_no2" value="0">
	<input type="hidden" id="old_chasis_no2" name="old_chasis_no2" value="0">
	<input type="hidden" id="old_eng_no2" name="old_eng_no2" value="0">
	<input type="hidden" id="new_chasis_no2" name="new_chasis_no2" value="0">
	<input type="hidden" id="new_eng_no2" name="new_eng_no2" value="0">
	
</form:form>

	
	


	
	
	
	
	<script>
	document.addEventListener('DOMContentLoaded', function() {
		
		
		
// 		document.getElementById('downlaod').onclick = function(){
// 			getExcelUtilization();
// 		};
		
	});
	
 	 	 function Validate() { 
				
			
// 			var anml_type = $('input[name=anml_type]:checked').val();
// 			if(anml_type == undefined){
// 				alert("Please Select Animal Type");
// 				return false;
// 			}
			
		
			 return true;
		}    
 		function getExcelUtilization() {   
 			var sub_category_id = 	$("#ba_no").val();
 			var adg = $("#old_chasis_no").val();
 			var project_id = $("#old_eng_no").val();
 			var ba_em_no = $("#new_chasis_no").val();
// 		 	var quarter = $("#quarter").val();
 			var quarter = $("#new_eng_no").val();
 		
 				$("#ba_no2").val(ba_no);
 				$("#old_chasis_no2").val(old_chasis_no);
 				$("#old_eng_no2").val(old_eng_no);
 				$("#new_chasis_no2").val(new_chasis_no);
 				$("#new_eng_no2").val(new_eng_no);
 			
 				document.getElementById('typeReport_qtr').value = 'excelL';
 				document.getElementById('search_qtr').submit();
 			} 
		
		function getExcel() {

			document.getElementById('typeReport1').value = 'excelL';
			document.getElementById('search2').submit();
		}
		
 			
	</script>
	
	

	
