
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
<title>Search WET/PET Amendment</title>
<script src="js/JS_CSS/jquery.dataTables.min.js"></script>
<script src="js/JS_CSS/dataTables.bootstrap.min.js"></script>
<script src="js/miso/miso_js/jquery-1.12.3.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>

<script type="text/javascript">
$(document).ready(function () {

    var file = document.getElementById('doc_path1');
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

<form:form id="WetPetEditAction" name="WetPetEditAction" action="WetPetUpdateAction?${_csrf.parameterName}=${_csrf.token}" method="post"  class="form-horizontal" commandName="WetPetEditCMD" enctype="multipart/form-data"> 

<div class="container" align="center">
	        	<div class="card">
	          		<div class="card-header"><h5> Edit WET/PET/FET Amendment</h5></div>
	          			<div class="card-body card-block cue_text">
                            <div class="col-md-12">
								<div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-6">
	                  						<label class=" form-control-label">WET/PET No</label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                					<input  type="hidden" id="id" name="id" value="${WetPetEditCMD.id}" class="form-control">
	                  						<input  id="wet_pet_no" name="wet_pet_no" value="${WetPetEditCMD.wet_pet_no}" class="form-control" readonly="readonly">
	                					</div>
	                					</div>
	              				</div>
	              			</div>
	              			<div class="col-md-12"> 
	  						    <div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-6">
	                  						<label class=" form-control-label">Table Title</label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                  						<input type="text" id="table_title_wet_pet" class="form-control" disabled>
	                					</div>
	              					</div>
							    </div>
						     	<div class="col-md-6">
              					<div class="row form-group">
                					<div class="col col-md-6">
                  						<label for="text-input" class=" form-control-label">Type of Document</label>
                					</div>
                					<div class="col-12 col-md-6">
                  							<input type="text" id="doc_type" name="" class="form-control" disabled>
									</div>
  								</div>
							 </div> 	
						</div> 
				   		<div class="col-md-12"> 			     
				        	<div class="col-md-6">
              					<div class="row form-group">
                					<div class="col col-md-6">
                  						<label for="text-input" class=" form-control-label">Effective From</label>
                					</div>
                					<div class="col-12 col-md-6">
                  						<input id="date_upload" name="" class="form-control" disabled>
									</div>
								</div>
							</div>
							<div class="col-md-6">
              					<div class="row form-group">
                					<div class="col col-md-6">
                  						<label for="text-input" class=" form-control-label">Effective To</label>
                					</div>
                					<div class="col-12 col-md-6">
                  						<input  id="date_upload2" name="" class="form-control" disabled>
									</div>
  								</div>
							</div> 		
	              		</div>
					<div class="col-md-12">
					<span class="line"></span></div>        
	              		<div class="col-md-12"> 
	              		   <div class="col-md-6">
              			 	<div class="row form-group"> 
               					<div class="col col-md-6">
                 					<label for="text-input" class=" form-control-label">Download Uploaded Document</label>
               					</div>
               					<div class="col-12 col-md-6">
               				     <a hreF="#" onclick="getDownloadImageWetPetAmdt('${WetPetEditCMD.id}');" class="btn btn-primary btn-sm">Download</a>
								</div>
 							 </div> 
  						   </div>	
	              			<div class="col-md-6">
              			 	<div class="row form-group"> 
               					<div class="col col-md-6">
                 					<label for="text-input" class=" form-control-label">Upload Document</label>
               					</div>
               					<div class="col-12 col-md-6">
               						<input type="file" id="doc_path1" name="doc_path1" class="form-control">
								</div>
 							</div> 
  						  </div>
  						</div>
  						<div class="col-md-12">
  						  <div class="col-md-6">
              					<div class="row form-group">
                					<div class="col col-md-6">
                  						<label class=" form-control-label">Remarks</label>
                					</div>
                					<div class="col-12 col-md-6">
                  						<textarea id="remark" name="remark" maxlength="255" class="form-control char-counter1">${WetPetEditCMD.remark}</textarea>
                					</div>
                					</div>
	              			</div>  
	              			 <div class="col-md-6">
      					   <div class="row form-group">
        					<div class="col col-md-6" style="padding-right:0;">
          						<label class=" form-control-label">Letter No </label>
        					</div>
        					<div class="col-12 col-md-6">
          						<input type="text" id="letter_no" name="letter_no" maxlength="50" class="form-control" autocomplete="off" value="${WetPetEditCMD.letter_no}">
        					</div>
        					</div>
      				  </div> 	                     
							</div> 
							  	
	  		        </div>	
	              	<div class="card-footer" align="center">
	              	    <input type="submit" class="btn btn-primary btn-sm" value="Update"  >
	              		<a href="search_WET_PET_Amendment" class="btn btn-danger btn-sm" onClick="javascript:window.close('','_parent','');">  Cancel </a>
		            </div>   
		      </div>  				      
</div>

</form:form>

<c:url value="getDownloadImageWetPetAmdt" var="imageDownloadUrl" />
        <form:form action="${imageDownloadUrl}" method="post" id="getDownloadImageForm" name="getDownloadImageForm" modelAttribute="id">
        	<input type="hidden" name="id1" id="id1" value="0"/>
        	<input type="hidden" name="pageUrl" id="pageUrl" value=""/>
        	<input type="hidden" name="contraint" id="contraint" value=""/>
        </form:form> 

<script >
$(document).ready(function() {
	
	    $('#remark').keyup(function() {
	        this.value = this.value.toUpperCase();
	    });	
	
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
	   	
	$("select#doc_type").val($("input#doc_type_hidden").val());
	$.ajaxSetup({
	    async: false
	});
	getArmByWePeNo($("input#wet_pet_no").val());
		
});
    	    
</script>
<script>
function getArmByWePeNo(val)
{
	  if(val != "" && val != null)
	  {
		  $.post("getDetailsByWetPetCondiNo?"+key+"="+value, {val : val}).done(function(j) {
			if(j!="" && j!= null){
			 document.getElementById("table_title_wet_pet").value=j[0].unit_type;	
			
			document.getElementById("doc_type").value=j[0].doc_type;
			document.getElementById("date_upload").value=j[0].valid_from;	
			document.getElementById("date_upload2").value=j[0].valid_till;
			}
			else
			{
				 document.getElementById("table_title_wet_pet").value="";	
				  document.getElementById("doc_type").value="";
				  document.getElementById("date_upload").value="";	
				 document.getElementById("date_upload2").value="";
			}
		
		  }).fail(function(xhr, textStatus, errorThrown) { }); 	
	  }
	  else
	  {
		document.getElementById("table_title_wet_pet").value="";	
		  document.getElementById("doc_type").value="";
		  document.getElementById("date_upload").value="";	
		 document.getElementById("date_upload2").value="";
	  }
}

function getDownloadImageWetPetAmdt(id)
{  
	   document.getElementById("id1").value=id;
	   document.getElementById("contraint").value="Edit";
	   	 document.getElementById("pageUrl").value="edit_search_WET_PET_amendment_Tiles";
       document.getElementById("getDownloadImageForm").submit();
	
}  
</script>
</body>

</html>
