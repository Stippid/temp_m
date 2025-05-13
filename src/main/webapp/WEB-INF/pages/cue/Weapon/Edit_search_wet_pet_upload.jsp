<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %>
<%@ taglib prefix="dandelion" uri="http://github.com/dandelion" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>


<script type="text/javascript" src="js/AES_ENC_DEC/lib/excluded/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>  
<script src="js/miso/miso_js/jquery-1.12.3.js"></script>

<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>

<script type="text/javascript">
$(document).ready(function () {
	
    var file = document.getElementById('file_wet');
	file.onchange = function(e) {
	  	var ext = this.value.match(/\.([^\.]+)$/)[1];
		switch (ext) {
		  case 'pdf':
		    break;
		  default:
		    alert('Please Only Allowed *.pdf File Extension');
		    this.value = '';
		}
	};
});
</script>

<script type="text/javascript">
$(document).ready(function() {
	$('body').toggleClass('open');
	$('.nav-item').toggle();
	$("div#divLogoutHidShow").hide();	
	document.getElementById('menuToggle').style.pointerEvents = 'none';
	$('#left-panel , #menuToggle').css("display","none");
});
</script>

</head>
<body>

<form:form id="WetPetEditAction"  name="WetPetEditAction" action="WetPetEditActionUpload?${_csrf.parameterName}=${_csrf.token}" commandName="WetPetEditCMDUpload" method="post" enctype="multipart/form-data"> 
	<div class="animated fadeIn">
			<div class="container" align="center">
	    		<div class="card">
	          		<div class="card-header"><h5>Edit WET/PET/FET Upload</h5></div>
	          			<div class="card-body card-block cue_text">
	            			<div class="col-md-12">	 
	            				<div class="col-md-6">           					
             				     <div class="row form-group">
               					<div class="col col-md-6">
				                	<label for="text-input" class=" form-control-label">Type of Document </label>
				                </div>
               					<div class="col-12 col-md-6">
		                             <div class="form-check-inline form-check">
		                                <label for="inline-radio1" class="form-check-label ">
		                                 <input  type="hidden" id="id" name="id"  value="${WetPetEditCMDUpload.id}" class="form-control">
		                                 <input  type="hidden" id="wet_pet_radio" name="wet_pet_radio"  value="${WetPetEditCMDUpload.wet_pet}" class="form-control">
		                                  <input type="radio" id="wet_pet1" name="wet_pet" value="WET" class="form-check-input" value="${WetPetEditCMDUpload.wet_pet}" disabled="disabled">WET
		                                </label>&nbsp;&nbsp;&nbsp;
		                                <label for="inline-radio2" class="form-check-label ">
		                                 
		                                  <input type="radio" id="wet_pet2" name="wet_pet" value="PET" class="form-check-input" value="${WetPetEditCMDUpload.wet_pet}" disabled="disabled">PET
		                                </label>&nbsp;&nbsp;&nbsp;
		                                <label for="inline-radio2" class="form-check-label ">
		                                 
		                                  <input type="radio" id="wet_pet3" name="wet_pet" value="FET" class="form-check-input" value="${WetPetEditCMDUpload.wet_pet}" disabled="disabled">FET
		                                </label>&nbsp;&nbsp;&nbsp;       
		                             </div>
					              </div>	              						              
               				</div>
               				</div>
               			</div>
               			<div class="col-md-12">	
								<div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-6">
	                  						<label class=" form-control-label">WET/PET/FET Document No</label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                  						<input type="text" id="wet_pet_no" name="wet_pet_no" value="${WetPetEditCMDUpload.wet_pet_no}" readonly="readonly" class="form-control">
	                					</div>
	              					</div>
								</div>
							  <div class="col-md-6">
              			 	     <div class="row form-group"> 
               					<div class="col col-md-6">
                 					<label for="text-input" class=" form-control-label">Document Title <strong style="color: red;">*</strong></label>
               					</div>
               					<div class="col-12 col-md-6">
                 					<input type="text" id="unit_type" name="unit_type" class="form-control" maxlength="100" value="${WetPetEditCMDUpload.unit_type}">
								</div>
 							    </div> 
  						      </div>
						</div>
						<div class="col-md-12">		
  					        <div class="col-md-6">
             					<div class="row form-group">
               					<div class="col col-md-6">
				                	<label for="text-input" class=" form-control-label">New Document <strong style="color: red;">*</strong></label>
				                </div>
               					<div class="col-md-6">
		                             <div class="form-check-inline form-check">
							               <label for="answer1" class="form-check-label ">
							                	<input type="radio" id="answer1" name="answer1"  value="Yes" class="form-check-input" onclick="newdocument();">Yes
							                </label>&nbsp;&nbsp;
							                <label for="answer2" class="form-check-label ">
							                     <input type="radio" id="answer2" name="answer1" value="No" class="form-check-input" onclick="newdocument();">No
							                 </label>
							            </div>
					              </div>	              						              
               				</div>
               			    </div>
  					     <div class="col-md-6" id="divsuperseeded_wet_pet" style="display:none"> 
           					<div class="row form-group">
             					<div class="col col-md-6" >
               						<label class=" form-control-label">Superseded Document No <strong style="color: red;">*</strong></label>
             					</div>
             					<div class="col-12 col-md-6" >
             					 <input type="hidden" id="supercddwetpet" name="supercddwetpet" value="${WetPetEditCMDUpload.superseeded_wet_pet}">	                                  
		                          <select id="superseeded_wet_pet" name="superseeded_wet_pet"  class="form-control"> </select>
             					</div>
             				</div>
           				</div>
  					</div>
               		<div class="col-md-12">		 
  						<div class="col-md-6">
            				<div class="row form-group"> 
              					<div class="col col-md-6">
                					<label for="text-input" class=" form-control-label">Effective From <strong style="color: red;">*</strong></label>
              					</div>
              					<div class="col-12 col-md-6">
                					<input id="valid_from" name="valid_from" placeholder="dd-MM-yyyy" class="form-control" value="${WetPetEditCMDUpload.valid_from}" onchange="checkDate()">
								</div>
							</div>
  						 </div>
  						<div class="col-md-6">
 							<div class="row form-group">  								
               					<div class="col col-md-6">
                 					<label for="text-input" class=" form-control-label">Effective To <strong style="color: red;">*</strong></label>
               					</div>
               					<div class="col-12 col-md-6">
                 					<input  id="valid_till" name="valid_till" placeholder="dd-MM-yyyy" class="form-control"  value="${WetPetEditCMDUpload.valid_till}" onchange="checkDate()">
								</div>
 							</div>
 						</div>
  					</div>
	            	<div class="col-md-12">	
								<div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-6">
	                  						<label class=" form-control-label">Security Classification<strong style="color: red;">*</strong></label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                  						<input type="hidden" id="doc_type_hidden" name="doc_type_hidden" value="${WetPetEditCMDUpload.doc_type}" class="form-control">
	                  						<select id="doc_type" name="doc_type" class="form-control">
				                                 <option value="Restricted">Restricted</option>
				                                 <option value="Confidential">Confidential</option>
				                                 <option value="Secret">Secret</option>		                                   
				                            </select>
	                					</div>
	  								</div>
								</div>
								<div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-6">
	                  						<label class=" form-control-label">Arm <strong style="color: red;">*</strong></label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                						<select class="form-control" id="arm" name="arm" >
<!-- 	                  						<option value="0">--Select--</option> -->
		                 						${selectArmNameList}
		                 						<c:forEach var="item" items="${getArmNameList}" varStatus="num" >
		           									<option value="${item[0]}"  <c:if test="${item[0] eq WetPetEditCMDUpload.arm}">selected="selected"</c:if>> ${item[1]}</option>
		           								</c:forEach>
	                  						</select>
	                					</div>
	  								</div>
								</div>
					</div>
					<div class="col-md-12">
								<div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-6">
	                  						<label class=" form-control-label">Sponsor Directorate <strong style="color: red;">*</strong></label>
	                					</div>
	                					<div class="col-12 col-md-6">
<!-- 	                  						<select class="form-control" id="sponsor_dire" name="sponsor_dire" > -->
<!-- 	                  						 <option value="0">--Select--</option> -->
<%-- 		              						<c:forEach var="item" items="${getsponserListCue}" varStatus="num" > --%>
<%-- 		       									<option value="${item[1]}" <c:if test="${item[1] eq WetPetEditCMDUpload.sponsor_dire}">selected="selected"</c:if>> ${item[1]}</option> --%>
<%-- 		       								</c:forEach> --%>
<!-- 	                  						</select> -->
											<select id="sponsor_dire" name="sponsor_dire" class="form-control-sm form-control" >
	                 						${selectLine_dte}
	                 						<c:forEach var="item" items="${getLine_DteList}" varStatus="num" >
                  								<option value="${item.line_dte_sus}" <c:if test="${item.line_dte_sus eq WetPetEditCMDUpload.sponsor_dire}">selected="selected"</c:if>> ${item.line_dte_name}</option>
                  							</c:forEach>
	                 					</select>
	                					</div>
	  								</div>
								</div>
							 <div class="col-md-6">
              			 	<div class="row form-group"> 
               					<div class="col col-md-6">
                 					<label for="text-input" class=" form-control-label">Upload Document</label>
               					</div>
               					<div class="col-12 col-md-6">
               						<input type="file" id="file_wet" name="file_wet" class="form-control">
								</div>
 							</div> 
  						</div>	 
  				 </div>
  				  <div class="col-md-12">
  					 	 <div class="col-md-6">
              			 	<div class="row form-group"> 
               					<div class="col col-md-6">
                 					<label for="text-input" class=" form-control-label">Download Uploaded Document</label>
               					</div>
               					<div class="col-12 col-md-6">
                                 <a hreF="#" onclick="getDownloadImage('${WetPetEditCMDUpload.id}')" class="btn btn-primary btn-sm">Download</a>
								</div>
 							</div> 
  						</div>
  				 </div>
		 </div>
		            <div class="card-footer" align="center">
						<input type="submit" class="btn btn-primary btn-sm" value="Update"  onclick="return isCueValidation()">
	              		<a href="upload_WET_PET_SEARCH" class="btn btn-danger btn-sm" onClick="javascript:window.close('','_parent','');">  Cancel </a>
		            </div>
		</div>
       </div>
   </div>
   
</form:form>
             
    <c:url value="update_WET_PET_Url" var="updateUrl" />
	<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid"   target="result">
		<input type="hidden" name="updateid" id="updateid" value="0"/>
	</form:form> 
	
	
	<c:url value="getDownloadImagewetpet" var="imageDownloadUrlwetpet" />
       <form:form action="${imageDownloadUrlwetpet}" method="post" id="getDownloadImageFormwetpet" name="getDownloadImageFormwetpet" modelAttribute="id1">
       	<input type="hidden" name="pageUrl" id="pageUrl" value=""/>
       	<input type="hidden" name="contraint" id="contraint" value=""/>        
       	<input type="hidden" name="id1" id="id1" value="0"/>        	
       </form:form> 
       
       
<script >
$(document).ready(function() {
	 $("#valid_from").datepicker({
	        dateFormat: "dd-mm-yy",    //"yy-mm-dd",
	        changeMonth: true,
	        changeYear: true
		}).attr('readonly', 'readonly');
	    
	    $("#valid_till").datepicker({
	        dateFormat: "dd-mm-yy",    //"yy-mm-dd",
	        changeMonth: true,
	        changeYear: true
		}).attr('readonly', 'readonly');
	
	    var newdoc_super = '${WetPetEditCMDUpload.superseeded_wet_pet}';
	    
	var wet_pet_hi = $("#wet_pet_radio").val();
	 if(wet_pet_hi == "WET"){		  
		  document.getElementById("wet_pet1").checked = true;		
	 } 
	 if(wet_pet_hi == "PET"){		  
		  document.getElementById("wet_pet2").checked = true;		
	 } 
	 if(wet_pet_hi == "FET"){		  
		  document.getElementById("wet_pet3").checked = true;		
	 } 
	 
	 $.ajaxSetup({
		    async: false
		});
	 $("select#id").val($("input#wet_pet_id").val());
	$("select#doc_type").val($("input#doc_type_hidden").val());
	
	var doc_path_ = document.links.item("doc_path").href;

	if(newdoc_super != "" && newdoc_super != null && newdoc_super != undefined)
	{
		document.getElementById("answer2").checked = true;
	}
	else
	{
		document.getElementById("answer1").checked = true;
	}	
	
	newdocument();
	
	try{
		if(window.location.href.includes("&msg="))
		{
			var url = window.location.href.split("?updateid=")[0];
			var id= window.location.href.split("?updateid=")[1].split("&msg=")[0];
			//window.location = url;
			
			 document.getElementById('updateid').value=id;
	 		 document.getElementById('updateForm').submit();
		}
		
	}
	catch (e) {
		// TODO: handle exception
	}
	
});
    	    
</script>

<script>
function checkDate()
{
	  var startDate = document.getElementById("valid_from").value;
	  var endDate = document.getElementById("valid_till").value;

	  var b = startDate.split(/\D/);
	  var c = endDate.split(/\D/);	  
	  
	  if ((Date.parse(c.reverse().join('-')) <= Date.parse(b.reverse().join('-'))))
	  {
	    alert("Effective To date should be greater than Effective From date");
	    document.getElementById("valid_till").value = "";
	  }	
}

 function newdocument() {
	var r = $('input:radio[name=answer1]:checked').val();
	
  	var r_w =  $('input:radio[name=wet_pet]:checked').val();
	if(r == 'No' && r_w !=undefined)
  	{
  		$("div#divsuperseeded_wet_pet").show();
  		getWetPetsupercddListNo();
  	}
  	else
  	{
  		$("div#divsuperseeded_wet_pet").hide();
  		document.getElementById("superseeded_wet_pet").value="";	
  		document.getElementById("supercddwetpet").value="";	
  	}
  	
}

 
 /* function newdocument() {
		var r = $('input:radio[name=answer]:checked').val();
		if(r == 'No')
		{
			$("div#divsuperseeded_wet_pet").show();
			getWetPetsupercddListNo();
		}
		else
		{
			$("div#divsuperseeded_wet_pet").hide(); 
			 document.getElementById("superseeded_wet_pet").value="";		
		}
	} */


function getWetPetsupercddListNo()
{
 var r =  $('input:radio[name=wet_pet]:checked').val();	
 
	$.post("getWetPetsupercddList12?"+key+"="+value, {we_r : r}).done(function(j) {
			 var newVal = document.getElementById("supercddwetpet").value;
			var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
	 		for ( var i = 0; i < j.length; i++) {
	 			if(j[i] == newVal){
	 				options += '<option value="'+j[i]+'" name="' + j[i]+ '" selected="selected ">'+ j[i]+ '</option>';
				}
				else{
					options += '<option value="'+j[i]+'" name="' + j[i]+ '" >'+ j[i]+ '</option>';
	 			}
			}
	 		$("select#superseeded_wet_pet").html(options); 
	}).fail(function(xhr, textStatus, errorThrown) { }); 	
}

  function isCueValidation(){
		
		 var x = $('input:radio[name=answer1]:checked').val();
		   if (x == undefined) {
			   alert("Please Select New Document");
			   return false;
		   }
		   	    else 
		   	    {
		   	    	if(x == "No"){
		   	    	 var swetno = document.getElementById("superseeded_wet_pet").value;		   	    	
			   	    	if (swetno==0) {
						alert("Please enter Superseded Document No")
						$("select#superseeded_wet_pet").focus();
						return false;
						} 
		   	    	}
		   	    }
		
		  if($("input#valid_from").val() == "")
		  {
			alert("Please select Effective From");
			$("input#valid_from").focus();
			return false;
		  }
		  if($("input#valid_till").val() == "")
		  {
			alert("Please select Effective To");
			$("input#valid_till").focus();
			return false;
		  }
		  
		  if($("select#doc_type").val() == "")
		  {
			alert("Please select Document Type");
			$("select#doc_type").focus();
			return false;
		  }
		  
		  if($("select#arm").val() == "0")
		  {
			alert("Please select Arm");
			$("select#arm").focus();
			return false;
		  }
		  
		  if($("select#sponsor_dire").val() == "0")
		  {
			alert("Please select Sponsor Directorate");
			$("select#sponsor_dire").focus();
			return false;
		  }		  
		
		  if($("input#unit_type").val() == "")
		  {
			alert("Please enter Document title");
			$("input#unit_type").focus();
			return false;
		  }
		    return true;		
	} 
	  
function getDownloadImage(id)
{  
	 document.getElementById("id1").value=id;
	   document.getElementById("contraint").value="Edit";
  	   document.getElementById("pageUrl").value="create_wet_pet_docEditTiles";
       document.getElementById("getDownloadImageFormwetpet").submit();
}	  
</script>
         
</body>
</html>