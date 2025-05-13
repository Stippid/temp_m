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
<form:form action="srvlnc_details_MasterAct" id="srvlnc_details_Master" method="post"  class="form-horizontal" commandName="srvlnc_details_MasterCMD"> 	
      <div class="container" align="center">
          <div class="card">          
              <div class="card-header">
	    		  <h5><span id="lbladd"></span>SHO/FHO DISEASE SURVEILLANCE</h5>
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
			               	   <label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Target Diseases</label>
			              </div>
			              
			              <div class="col-md-8">		                			                  
						      <input type="text" name="target_diseases" id="target_diseases" onkeypress="return onlyAlphaNumeric(event);" class="form-control-sm form-control" autocomplete ="off" maxlength="100" placeholder="Search...">
						      <input type="hidden" id="id" name="id"  class="form-control" value="0" autocomplete="off">						   
			              </div>
			            </div>
		         </div>
		              
		        <div class="col-md-6" >
		          <div class="row form-group">
		              <div class="col-md-4" style="padding-left: 0;padding-right: 0;"> 
		               	   <label for="text-input" class=" form-control-label">Target Sub Diseases</label>
		              </div>
		              	<div class="col-md-8">
					      <input type="text" name="target_diseases_sub" onkeypress="return onlyAlphaNumeric(event);" id="target_diseases_sub" class="form-control-sm form-control" autocomplete ="off" maxlength="100" placeholder="Enter Target diseases Sub...">
		              </div> 
		             </div>
		       </div> 
		    </div>
		          
		     <div class="col-md-12" >
		          <div class="col-md-6">
		             <div class="row form-group ">
		                <div class="col-md-4" > 
		               	   <label for="text-input" class=" form-control-label">Investigation</label>
		                </div>
		              <div class="col-md-8">
					      <input type="text" name="investigation" id="investigation" onkeypress="return onlyAlphaNumeric(event);" class="form-control-sm form-control" autocomplete ="off" maxlength="100" placeholder="Enter Investigation...">
		              </div>
		              </div>
		          </div>
		       </div>
		    </div>
		</div>
		   
            
            
            <div class="form-control card-footer" align="center">
            <a href="mnh_surv_det" type="reset" class="btn btn-primary btn-sm"> Clear </a> 
            <input type="submit" id="svbtn" class="btn btn-success btn-sm" value="Save" onclick="return Validation();">
              <i class="fa fa-search"></i><input type="button" id="btdog" class="btn btn-primary btn-sm" value="Search" onclick="return Search();">
                                </div>
            
     </div>


	 	 <div class="container" id="divPrint" style="display: none;">
                   <div id="divShow" style="display: block;">
                   
                   <div id="divShow1" align="center" style="display: none;"></div>
						<div class="watermarked" data-watermark="" id="divwatermark">
							<span id="ip"></span>
							<table id="survdetailsReport7" class="table no-margin table-striped  table-hover  table-bordered report_print">
								<thead >
									<tr>
										<th style="font-size: 15px;text-align: center;width: 10%;">Ser No</th>
										<th style="font-size: 15px;text-align: center;">Target Diseases</th>
										<th style="font-size: 15px;text-align: center;">Target Sub Diseases</th>
										<th style="font-size: 15px;text-align: center;">Investigation</th>
										<th style="font-size: 15px;text-align: center; width: 20%;">Action</th>
									</tr>
								</thead>
								<tbody>
								<c:forEach var="item" items="${list}" varStatus="num" >
									<tr>
										<td style="font-size: 15px;text-align: center;width: 10%;">${num.index+1}</td> 
										<td style="font-size: 15px;text-align: left;">${item[0]}</td>
										<td style="font-size: 15px; text-align: left;">${item[1]}</td>
										<td style="font-size: 15px; text-align: left;">${item[2]}</td>										
										<td style="font-size: 15px;text-align: center; width: 20%;">${item[3]} ${item[4]}</td> 
									</tr>
							  </c:forEach>									
								</tbody>
							</table>
						</div>
					</div> 
					</div> 
				  
</form:form>
<c:url value="surv_detaillist" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="">
		<input type="hidden" name="target_diseases1" id="target_diseases1" value="0"/>
		<input type="hidden" name="target_diseases_sub1" id="target_diseases_sub1" value="0"/>
		<input type="hidden" name="investigation1" id="investigation1" value="0"/>		
	</form:form> 

<c:url value="deleteSho_Fho_Svl_Details" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm" name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0"/> 
</form:form>

<script>
$(document).ready(function() {
	
	if('${target_diseases1}' != "" || '${target_diseases_sub1}' != "" || '${investigation1}' != ""){
		$("#divPrint").show();
	}
	
	if('${size}' == 0 && '${size}' != ""){
		$("#divPrint").show();
	} 
	   
	var q = '${target_diseases1}';
	if(q != ""){ 
		$("#target_diseases").val(q);
	}
	
	var q1 = '${target_diseases_sub1}';
	if(q1 != ""){ 
		$("#target_diseases_sub").val(q1);
	}
	
	var q2 = '${investigation1}';
	if(q2 != ""){ 
		$("#investigation").val(q2);
	}
	
	
	

	
});

$("#target_diseases").keyup(function(){
    var d1 = this.value;       
	$().Autocomplete2('GET','getMedTargetDiseases',target_diseases,{a:d1},'','','1','','','');
});


function editData(id,target_diseases,target_diseases_sub,investigation){	
	document.getElementById('lbladd').innerHTML = "UPDATE";
		 $("#id").val(id);
		 $("#target_diseases").val(target_diseases);
		 $("#target_diseases_sub").val(target_diseases_sub);
		 $("#investigation").val(investigation);
		 
}

function deleteData(id){
	$("#id1").val(id);
	document.getElementById('deleteForm').submit();
	} 

function Search(){
	if(($("#target_diseases").val() == "") && ($("#target_diseases_sub").val() == "") && ($("#investigation").val() == "")){
		alert("Please Select Either Target Diseases,Target Diseases Sub and Investigation");
		$("#service").focus();
		return false;
	}
	
	$("#target_diseases1").val($("#target_diseases").val()) ;
	$("#target_diseases_sub1").val($("#target_diseases_sub").val()) ;
	$("#investigation1").val($("#investigation").val()) ;
	$("#searchForm").submit();


}

function Validation(){
	  if ($("input#target_diseases").val().trim() == "") {
			alert("Please Enter Target Diseases.");
			$("input#target_diseases").focus();
			return false;
		}	
	  if ($("input#target_diseases_sub").val().trim() == "") {
			alert("Please Enter Target Sub Diseases.");
			$("input#target_diseases_sub").focus();
			return false;
		}
	/*   if ($("input#investigation").val().trim() == "") {
			alert("Please Enter Investigation.");
			$("input#investigation").focus();
			return false;
		}	 */
	  return true;
}
</script>							
				