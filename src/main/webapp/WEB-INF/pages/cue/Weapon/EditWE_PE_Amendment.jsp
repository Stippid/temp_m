
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %>
<%@ taglib prefix="dandelion" uri="http://github.com/dandelion" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update WE PE Amendment</title>
<script type="text/javascript" src="js/miso/miso_js/jquery-1.12.3.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

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

 <script type="text/javascript">
 
 $(document).ready(function () {	    
	    $('#remark').keyup(function() {
	        this.value = this.value.toUpperCase();
	    });
	    
	    getarmvalue($('#we_pe_no').val());
	    
	    try{
			if(window.location.href.includes("msg="))
			{
				var url = window.location.href.split("?msg")[0];
				window.location = url;
			}
	    }catch (e) {
			// TODO: handle exception
		}
	});

        </script>

</head>


<body>
<form:form id="EditWEPEamendmentForm" name="EditWEPEamendmentForm" action="EditWEPEamendmentAction?${_csrf.parameterName}=${_csrf.token}" method="post"  class="form-horizontal" commandName="EditWEPEamendmentCMD" enctype="multipart/form-data">

	<div class="animated fadeIn">
		 	<div class="container" align="center">
	    		<div class="card">
	          		<div class="card-header"> <h5>Edit WE/PE AMENDMENT</h5> </div>
	          			<div class="card-body card-block cue_text">
						   <div class="col-md-12">
						     <div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-6">
	                  						<label class=" form-control-label">MISO WE/PE No</label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                					<input  type="hidden" id="id" name="id" value="${EditWEPEamendmentCMD.id}" class="form-control">
	                  					 <input type="text" id="we_pe_no" name="we_pe_no" class="form-control" value="${EditWEPEamendmentCMD.we_pe_no}" readonly="readonly">
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
                  						<input type="text" id="table_title_WE_PE" name="" value="${EditWEPEamendmentCMD.amdt_title_we_pe}" placeholder="" value="" class="form-control" disabled>
                					</div>
              					</div>
							</div>
							<div class="col-md-6">
              					<div class="row form-group">
                					<div class="col col-md-6">
                  						<label for="text-input" class=" form-control-label">Type</label>
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
                  						<input id="date_upload2" name="" class="form-control" disabled>
									</div>
  								</div>
							</div> 		
						</div>
						<div class="col-md-12"><span class="line"></span></div>
						<div class="col-md-12">	
              				<div class="col-md-6">
              					<div class="row form-group">
                					<div class="col col-md-6">
                  						<label class=" form-control-label">Amendment Document</label>
                					</div>
                					<div class="col-12 col-md-6">
										<a hreF="#" onclick="getWEPE_Amdnment_Image('${EditWEPEamendmentCMD.id}');" class="btn btn-primary btn-sm">Download</a>
                					</div>
                				</div> 
              				</div>   
              				 <div class="col-md-6">
              					<div class="row form-group">
                					<div class="col col-md-6">
                  						<label class=" form-control-label">New Amendment Document</label>
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
                  						<label class=" form-control-label">Remark</label>
                					</div>
                					<div class="col-12 col-md-6">
                  						<textarea id="remark" name="remark" maxlength="255" class="form-control">${EditWEPEamendmentCMD.remark}</textarea>
                					</div>
                				</div>
              				</div>  
              				 <div class="col-md-6">
      					   <div class="row form-group">
        					<div class="col col-md-6" style="padding-right:0;">
          						<label class=" form-control-label">Letter No </label>
        					</div>
        					<div class="col-12 col-md-6">
          						<input type="text" id="letter_no" name="letter_no" maxlength="50" class="form-control" autocomplete="off" value="${EditWEPEamendmentCMD.letter_no}">
        					</div>
        					</div>
      				  </div> 	  
						</div>
	            </div>
		            <div class="card-footer" align="center">
						<input type="submit" class="btn btn-primary btn-sm" value="Update">
	              		<a href="search_uploded_amendment_to_WEPEdocument" class="btn btn-danger btn-sm" onClick="javascript:window.close('','_parent','');">  Cancel </a>
		            </div>  
	        	</div>
			</div>
  </div>
</form:form>


<c:url value="getWEPE_Amdnment_Image" var="imageDownloadUrl" />
        <form:form action="${imageDownloadUrl}" method="post" id="getWEPE_Amdnment_ImageForm" name="getWEPE_Amdnment_ImageForm" modelAttribute="id">
        	<input type="hidden" name="id1" id="id1" value="0"/>
        	<input type="hidden" name="pageUrl" id="pageUrl" value=""/>
        	<input type="hidden" name="contraint" id="contraint" value=""/>
        </form:form> 

<script>
function getarmvalue(val){	
	if(val != "" && val != null)
	  {
		$.post("getsuperseededvalue?"+key+"="+value, {val : val}).done(function(j) {
			if(j!="" && j!= null){		
			document.getElementById("table_title_WE_PE").value=j[0].table_title;
			document.getElementById("doc_type").value=j[0].doc_type;
			document.getElementById("date_upload").value=j[0].eff_frm_date;
			document.getElementById("date_upload2").value=j[0].eff_to_date;
			}
			else
			{
				document.getElementById("table_title_WE_PE").value="";
				document.getElementById("doc_type").value="";
				document.getElementById("date_upload").value="";
				document.getElementById("date_upload2").value="";
			}
			
		 }).fail(function(xhr, textStatus, errorThrown) { }); 	
	  }
	else
	{
		document.getElementById("table_title_WE_PE").value="";
		document.getElementById("doc_type").value="";
		document.getElementById("date_upload").value="";
		document.getElementById("date_upload2").value="";
	}
}


function getWEPE_Amdnment_Image(id){
	   document.getElementById("id1").value=id;
	   document.getElementById("contraint").value="Edit";
	   document.getElementById("pageUrl").value="EditWE_PE_AmendmentTiles";
     document.getElementById("getWEPE_Amdnment_ImageForm").submit();
	
}
</script>
</body>
</html>
