<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="Stylesheet" href="js/Calender/jquery-ui.css" >
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>
 
<link href="js/JS_CSS/jquery.dataTables.min.css" rel="stylesheet"> 

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>



<link rel="stylesheet" href="js/cue/cueWatermark.css">

<form:form action="searchStrengthDetailsAction" method="post" class="form-horizontal" commandName="searchStrengthDetailsCMD">
<div class="">
      <div class="container" align="center">
          <div class="card">
              <div class="card-header mnh-card-header">
		             <b>SEARCH STRENGTH DETAILS</b>
		      </div> 
		      
		       <div class="card-body card-block">    
		           <div class="col-md-12">
		                <div class="col-md-2" style="text-align: left;">
                 			 <label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Command</label>
               			</div>
		            			  	 
		            	<div class="col-md-4">
		            	     <select name="cmd" id="cmd" data-placeholder="Select the value..." class="form-control-sm form-control">
								   <option value="-1">--Select the Command--</option>
								  <%--  <c:if test="${not empty ml_1[0]}"> 
								         <c:set var="data" value="${ml_1[0]}"/>
    								     <c:set var="datap" value="${fn:split(data,',')}"/>
    								    
	    								 <c:forEach var="j" begin="0" end="${fn:length(datap)-1}">
	    								     <c:set var="dataf" value="${fn:split(datap[j],':')}"/>
	    								     <option value="${dataf[0]}" name="${dataf[2]}">${dataf[2]}</option>
	    								 </c:forEach>
								   </c:if> --%>
								     <c:forEach var="item" items="${getCommandList}" varStatus="num" >
                  								<option value="${item[0]}"  name="${item[1]}" >${item[1]}</option>
                  							</c:forEach>
							 </select>
						</div>
		                							
	               		<div class="col-md-2" style="text-align: left;">
					       <label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Quarter</label>
	               		 </div>	               		 
	               		 <div class="col-md-4">
	             			    <select name="qtr" id="qtr"  class="form-control-sm form-control">
               					      <option value="-1">--Select--</option>
								<c:forEach var="item" items="${ml_2}" varStatus="num">
									<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
								</c:forEach>
					                </select>
	               		 </div>
				    </div>
					
					<div class="col-md-12">    
					    <div class="col-md-2" style="text-align: left;"> 
					   
	                 		 <label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Year</label>
	               	    </div>
	               	    
	               	    <div class="col-md-2">
					         <input type="text" id="year" name="year" class="form-control-sm form-control" maxlength="4" 
					         autocomplete="off" onkeypress="return isNumberPointKey(event);"  onchange="Checkyear(this)">
					    </div>				           
				    </div>
             </div>
	 <div class="form-control card-footer" align="center">
			        <a href="mnh_input_strengthSearch" class="btn btn-primary btn-sm" >Clear</a> 
		          <i class="fa fa-search"></i><input type="button" id="btn_serach" class="btn btn-success btn-sm" value="Search" onclick="return search_str()" />      
              </div>           		
	      </div>
	   
  
</form:form>

 		 <div class="card-body" id="divPrint" style="display: none;">
				   <div id="divShow" style="display: block;">
				   
		                 <table id="SearchReport" class="table no-margin table-striped  table-hover  table-bordered report_print">
		                      <thead>
		                          <tr>
			                         <th style="text-align: center;" >Ser No</th>        
   								     <th style="text-align: center;">Quarter</th>                                                                        
                                     <th style="text-align: center;" width="15%">Year</th>
                                     <th style="text-align: center;">Command</th>
                                     <th style="text-align: center;" width="10%">Action</th>
		                          </tr>                                                        
		                      </thead> 
		                      <tbody>
		                           <c:forEach var="item" items="${list}" varStatus="num" >
							          <tr>
										<th style="text-align: center;">${num.index+1}</th>
    									<th style="text-align: left;">${item.qtr}</th>
                                        <th style="text-align: center;" width="15%">${item.year}</th>
                                        <th style="text-align: left;">${item.hq_name}</th>
                                        <th id="thAction1" style="text-align: center;" width="10%">${item.id}</th>
							          </tr>
							       </c:forEach>
		                     </tbody>
		                     <c:if test="${list.size()==0}">
								<tr>
									<td style="font-size: 15px; text-align: center; color: red;"
										colspan="17">Data Not Available</td>
								</tr>
							</c:if>
		                 </table>
		            </div>	
		          </div>
	        

<c:url value="search_strength_details_input" var="searchUrl"/>
<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="cmd1">
 	  <input type="hidden" name="cmd1" id="cmd1">
 	  <input type="hidden" name="qtr1" id="qtr1">
 	  <input type="hidden" name="yr1" id="yr1">
</form:form>
 <c:url value="deletestrength" var="deleteUrl" />
	<form:form action="${deleteUrl}" method="post" id="deleteForm" name="deleteForm" modelAttribute="id1">
		<input type="hidden" name="id1" id="id1" value="0"/> 
	</form:form>		
<c:url value="edit_strength_details" var="editUrl"/>
<form:form action="${editUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid" >
	<input type="hidden" name="updateid" id="updateid" value="0" />
</form:form>


<script>
 function btn_clc(){

	$("#unit_name1").val('');
	$("#sus_no1").val('');
} 


function isNumberPointKey(evt) {
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if(charCode != 46 && charCode > 31 && (charCode<48 || charCode>57)){
		return false;
	}else{
		if(charCode == 46){
			return false;
		}
	}
	return true;
}

function search_str(){
	var d = new Date();
	var year = d.getFullYear();
	
	
	 if($("#cmd").val() == "-1"){
		alert("Please Select the Command");
		$("#cmd").focus();
		return false;
	} 
	if($("#qtr").val() == "-1"){
		alert("Please select the Quarter");
		$("#quarter").focus();
		return false;
	}	
	
	if(quarter_validate($("#qtr").val()) == 0 && $("#year").val() == d.getFullYear()){
		 alert("Future Quarter is not allowed to select");
		 return false;
	}
	if($("#year").val() > year){
		alert("Future Year cannot be allowed");
		$("#year").focus();
		return false;
	} 
	if($("#year").val() == ""){
		alert("Please Enter the Year");
		$("#year").focus();
		return false;
	}
	if($("#year").val().length < 4){
		alert("Year Format Require in YYYY");
		$("#year").focus();
		return false;
	}
	
	$("#cmd1").val($("#cmd").val());
	$("#qtr1").val($("#qtr").val());	
	$("#yr1").val($("#year").val());
	$("#searchForm").submit();
	
}

function editData(id){
	document.getElementById('updateid').value=id;
	document.getElementById('updateForm').submit();
}
function deleteData(id){
	$("#id1").val(id);
	document.getElementById('deleteForm').submit();
} 

</script> 	

<!-- for On Load Methods -->
<script>
$(document).ready(function() {
	if('${size}' != 0){
		$("#divPrint").show();
	} 
	
	if('${size}' == 0 && '${size}' != ""){
		$("#divPrint").show();
	}
	
	
	var q = '${cmd1}';
	var q1 = '${qtr1}';
	
	if(q != ""){
		$("#cmd").val(q);
	}
	
    if(q1 != ""){
    	$("#qtr").val(q1);
	}
    
    var q2 = '${yr1}';
	var d = new Date();
	var year = d.getFullYear();
	if(q2 != ""){
		$("#year").val(q2);
	}else{
		$("#year").val(year);
	}
	

});	
</script>
