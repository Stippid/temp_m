<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="Stylesheet" href="js/Calender/jquery-ui.css" >
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<link href="js/cue/cueWatermark.css" rel="Stylesheet"></link>

<link rel="stylesheet" href="js/cue/cueWatermark.css">

<form:form action="Search_Capture_Opd_CasesAction" method="post" class="form-horizontal" commandName="Search_Capture_Opd_CasesCMD">
  
      <div class="container" align="center">
          <div class="card">
              <div class="card-header">
		             <h5>SEARCH BLOOD DESCRIPTION</h5>
		             
		      </div>
			<div class="card-body card-block">
				<div class="row">
					<div class="col-md-12">
						<div class="col-md-8">
							<div class="row form-group">
								<div class="col-md-3">
									<label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Blood Description</label>
								</div>
								<div class="col-md-9">
									<input type="text" id="blood_desc" name="blood_desc" value="${blood_desc}"maxlength="100" class="form-control-sm form-control"
									placeholder="Search..." autocomplete="off" title="Type Blood Description to Search">
								</div>
							</div>
						</div>
						
					</div>
					 
			
				</div>
			</div>
	    	<div class="card-footer" align="center">
			     <a href="SearchBloodURL"class="btn btn-primary btn-sm"  id="btn_clc">Clear</a>
		          <i class="fa fa-search"></i><input type="button" class="btn btn-success btn-sm"  value="Search" onclick="Search();" />            
              </div>
          </div>
          
          <div class="container" id="divPrint" style="display: none;">				  
				   <div  class="watermarked" data-watermark="" id="divwatermark"><span id="ip"></span>
		                 <table id="BloodReport" class="table no-margin table-striped  table-hover  table-bordered report_print">
		                      <thead >
		                          <tr>
			                         <th>Ser No</th>                                                                              
                                     <th >Blood Description</th>
                                     <th >Action</th>
		                          </tr>                                                        
		                      </thead> 
		                      <tbody>
		                           <c:forEach var="item" items="${list}" varStatus="num" >
							          <tr>
										<td >${num.index+1}</td>
                                        <td >${item.blood_desc}</td>
                                        <td id="thAction1">${item.id}</td>
							          </tr>
							       </c:forEach>
		                     </tbody>
		                     <c:if test="${list.size()==0}">
						<tr>
							<td style="font-size: 15px; text-align: center; color: red;"
								colspan="2">Data Not Available</td>
						</tr>
					</c:if>
		                 </table>
		            </div>	          
	        </div> 
      </div>
  
</form:form>

<c:url value="search_Blood" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="blood_desc1">
     <input type="hidden" name="blood_desc1" id="blood_desc1"/>
    
</form:form>

 <c:url value="edit_Blood" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="id" >
	<input type="hidden" name="updateid" id="updateid" value="0" />
</form:form> 
	
 <c:url value="blood_delete" var="deleteUrl" />
	<form:form action="${deleteUrl}" method="post" id="deleteForm" name="deleteForm" modelAttribute="id1">
		<input type="hidden" name="id1" id="id1" value="0"/> 
	</form:form>
 
<!-- for Functions -->
<script>
function btn_clc(){
	location.reload(true);
}

 function deleteData(id){
	$("#id1").val(id);
	document.getElementById('deleteForm').submit();
} 
 
 function editData(id){
	document.getElementById('updateid').value=id;
	document.getElementById('updateForm').submit();
} 
function Search(){
	
	if($("#blood_desc").val() == "") {
		alert("Please Enter the Blood Description");
		$("#blood_desc").focus();
		return false;
	}
		

	$("#blood_desc1").val($("#blood_desc1").val());
	
	$("#searchForm").submit();
}


</script>

<!-- for On Load Methods -->
<script> 
$(document).ready(function() {
	 
	if('${blood_desc1}' != "" || '${blood_desc1}' != "" ){	
		$("#divPrint").hide();
	}
	if('${size}' != 0){
		$("#divPrint").show();
	} 
	
	var q = '${blood_desc1}';
	
	if(q != ""){
		$("#blood_desc").val(q);
	}
	 
    if('${size}' == 0 && '${size}' != ""){
    	$("#divPrint").show();
	}
	
	try{
		if(window.location.href.includes("msg=")){
			var url = window.location.href.split("?")[0];
		    window.location = url;
	    } 
	}catch (e) {
		// TODO: handle exception
	}	
});	
</script>