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
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
<form:form action="foodMasterAct" id="foodMaster" method="post"  class="form-horizontal" commandName="foodMasterCMD"> 	
      <div class="container" align="center">
          <div class="card">          
              <div class="card-header">
	    		  <h5><span id="lbladd"></span>FOOD</h5>
	    		  <h6>
				     <span style="font-size: 12px; color: red">(To be entered by MISO)</span>
				  </h6>	  
	    	  </div>	          
	          <div class="card-body card-block"> 
	             <div class="row">  
	              <div class="col-md-12">
	                <div class="col-md-6">
		               <div class="row form-group ">
			              <div class="col-md-4" > 
			               	   <label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong> FOOD</label>
			              </div>
			              
			              <div class="col-md-8">		                			                  
						      <input type="text" name="food" id="food" class="form-control-sm form-control"  onkeypress="return onlyAlphaNumeric(event);" autocomplete ="off" maxlength="100" placeholder="Enter..">
						      <input type="hidden" id="id" name="id" onkeypress="return onlyAlphaNumeric(event);" class="form-control" value="0" autocomplete="off">						   
			              </div>
			            </div>
		         </div>
		              
		       
		    </div>
		          
		   
		    </div>
		</div>
		   
            
            
            <div class="form-control card-footer" align="center">
            <a href="mnh_Food_URL" type="reset" class="btn btn-primary btn-sm"> Clear </a> 
            <input type="submit" id="foodbtn" class="btn btn-success btn-sm" value="Save" onclick="return Validation();">
              <i class="fa fa-search"></i><input type="button" id="fooddog" class="btn btn-primary btn-sm" value="Search" onclick="return Search();">
                                </div>
            
     </div>


	 	 <div class="container" id="divPrint" style="display: none;">
                   <div id="divShow" style="display: block;">
                   
                   <div id="divShow1" align="center" style="display: none;"></div>
						<div class="watermarked" data-watermark="" id="divwatermark">
							<span id="ip"></span>
							<table id="foodReport7" class="table no-margin table-striped  table-hover  table-bordered report_print">
								<thead >
									<tr>
										<th style="font-size: 15px;text-align: center;width: 10%;">Ser No</th>
										<th style="font-size: 15px;text-align: center;">Food</th>
										
										<th style="font-size: 15px;text-align: center; width: 20%;">Action</th>
									</tr>
								</thead>
								<tbody>
								<c:forEach var="item" items="${list}" varStatus="num" >
									<tr>
										<td style="font-size: 15px;text-align: center;width: 10%;">${num.index+1}</td> 
										<td style="font-size: 15px;text-align: left;">${item[0]}</td>
																		
										<td style="font-size: 15px;text-align: center; width: 20%;">${item[1]} ${item[2]}</td> 
									</tr>
							  </c:forEach>									
								</tbody>
							</table>
						</div>
					</div> 
					</div> 
				  
</form:form>
<c:url value="fooddetaillist" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="">
		<input type="hidden" name="food1" id="food1" />
		
	</form:form> 

<c:url value="deletefood" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm" name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0"/> 
</form:form>

<script>
$(document).ready(function() {
	
	if('${food1}' != ""){
		$("#divPrint").show();
	}
	
	if('${size}' == 0 && '${size}' != ""){
		$("#divPrint").show();
	} 
	   
	var q = '${food1}';
	if(q != ""){ 
		$("#food").val(q);
	}

	
	
	

	
});

/* $("#target_diseases").keyup(function(){
    var d1 = this.value;       
	$().Autocomplete2('GET','getMedTargetDiseases',target_diseases,{a:d1},'','','1','','','');
});
 */

function editData(id,target_diseases,target_diseases_sub,investigation){	
	document.getElementById('lbladd').innerHTML = "UPDATE";
		 $("#id").val(id);
		 $("food").val(food);
		
		 
}

function deleteData(id){
	$("#id1").val(id);
	document.getElementById('deleteForm').submit();
	} 

function Search(){
	if(($("#food").val() == "")){
		alert("Please Enter Food");
		$("#food").focus();
		return false;
	}
	
	$("#food1").val($("#food").val()) ;
	
	$("#searchForm").submit();


}

function Validation(){
	  if ($("input#food").val() == "") {
			alert("Please Enter Food.");
			$("input#food").focus();
			return false;
		}	
	  return true;
}
</script>							
				