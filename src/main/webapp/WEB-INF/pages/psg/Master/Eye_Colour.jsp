<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
<link href="js/cue/cueWatermark.css" rel="Stylesheet"></link>

<form:form name="Eye_Colour" id="Eye_Colour" action="Eye_ColourAction" method="post" class="form-horizontal" commandName="Eye_ColourCMD"> 
	<div class="animated fadeIn">
	    	<div class="container" align="center">
	    		<div class="card">
	    		<div class="card-header"><h5>ADD/UPDATE EYE COLOUR</h5> <h6 class="enter_by"><span style="font-size:12px;color:red;">(To be entered by MISO)</span></h6></div>
	          			<div class="card-body card-block">
	            			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> COLOR NAME</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="eye_cl_name" name="eye_cl_name" class="form-control autocomplete" oninput="this.value = this.value.toUpperCase()" maxlength="50" autocomplete="off" onkeypress="return onlyAlphabets(event);"> 	
						                   <!-- onkeyup="return specialcharecter(this)"  -->					                   
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
<!-- 												<option value="0">--Select--</option> -->
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
							<a href="Eye_ColourUrl" class="btn btn-success btn-sm" >Clear</a>  
		              		<input type="submit" class="btn btn-primary btn-sm" value="Save" onclick="return Validation();">
		              		<input type="button" class="btn btn-info btn-sm" onclick="Search();" value="Search">  
			            </div> 		
	        	</div>
			</div>
	</div>
	<div align="right" class="container">
	<i class="fa fa-file-excel-o" id="btnExport" style="font-size:x-large; color:green;text-align: right;" aria-hidden="true" title="EXPORT TO EXCEL" onclick="getExcel();"></i>
	</div><br>
	<div class="container" id="getEye_colour" style="display: block;"> 	 
			<div  class="watermarked" data-watermark="" id="divwatermark"><span id="ip"></span>
		           <table id="getEye_colour" class="table no-margin table-striped  table-hover  table-bordered report_print">
		                 <thead>
		                       <tr>
			                         <th style="text-align: center; width:10%;">Ser No</th>
			                         <th style="text-align: center;"> Colour Name </th>			                         
			                         <th style="text-align: center; width: 20%;">Action</th>
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
										<td style="font-size: 15px;text-align: center ;width: 20%;">${item[1]} ${item[2]}</td> 
									</tr>
								</c:forEach>
		                     </tbody>
		                 </table>
		            </div>				  
			   </div> 	
</form:form>


<c:url value="GetSearch_Eye_Colour" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="eye_cl_name2">
		<input type="hidden" name="eye_cl_name2" id="eye_cl_name2" value="0"/> 
		<input type="hidden" name="status2" id="status2" value="0"/>
	</form:form> 
	
<c:url value="Edit_Eye_ColourUrl" var="editUrl"/>
<form:form action="${editUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="id2">
	  <input type="hidden" name="id2" id="id2" value="0">
</form:form>

<c:url value="Delete_Eye_Colour" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm" name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0"/> 
</form:form>
<c:url value="EyeColourreport" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2" name="search2" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form>
				
<Script>
$(document).ready(function() {
	
	 if('${list.size()}' == ""){
		   $("div#getEye_colour").hide();
		 }	
	 if('${eye_cl_name2}'!= ""){
			
			$("#eye_cl_name2").val('${eye_cl_name2}') ;
		}
	 
	 if('${status2}' != ""){
		 
		 	$("Select#status").val('${status2}') ;
			}
		//$("#eye_cl_name").val('${eye_cl_name2}');
});
function getExcel() {
	document.getElementById('typeReport1').value = 'excelL';
	document.getElementById('search2').submit();
} 
function Search(){
	
	$("#eye_cl_name2").val($("#eye_cl_name").val()) ;		
	$("#status2").val($("#status").val()) ;	
	document.getElementById('searchForm').submit();

}

function editData(id){	
	$("#id2").val(id);
	document.getElementById('updateForm').submit();
		 
}

function deleteData(id){
	$("#id1").val(id);
	document.getElementById('deleteForm').submit();
}  
	
</Script>

<script>
function Validation(){
	  if ($("input#eye_cl_name").val().trim() == "") {
			alert("Please Enter Colour Name");
			$("input#eye_cl_name").focus();
			return false;
		}
	  
	  if ($("select#status").val() == "inactive") {
			alert("Only Select Active Status");
			$("select#status").focus();
			return false;
		}
	  return true;
}
	  
</script>