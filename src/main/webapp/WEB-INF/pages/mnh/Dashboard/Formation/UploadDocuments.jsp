<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Upload Documents</title>
<script type="text/javascript" src="js/jquery/jquery-1.12.3.js"></script>

<script>
$(document).ready(function() {	
 	$('body').bind('cut copy paste', function (e) {
        e.preventDefault();
    });
   
    $("body").on("contextmenu",function(e){
        return false;
    });
    
	try{
	  	if(window.location.href.includes("msg="))
	  	{
		 	var url = window.location.href.split("?msg")[0];
		 	window.location = url;		 
		} 
	}
	catch (e) {
		// TODO: handle exception
	}
		
});

function fileValid(e) {
  	var ext = e.value.match(/\.([^\.]+)$/)[1];
	switch (ext) {
	  case 'pdf':
	    break;
	  default:
	    alert('Please only allowed *.pdf File Extension');
	    e.value = '';
	    e.focus();
	}
}

</script>

</head>
<body>
<c:if test="${not empty msg}">
	<input type="hidden" name="msg" id="msg" value="${msg}" />
</c:if>

<form:form name="UploadDocsId" id="UploadDocsId" action="saveUploadDocs?${_csrf.parameterName}=${_csrf.token}" method='POST' commandName="UploadDocsCMD" enctype="multipart/form-data">
<div class="container" align="center">
	<div class="card">
		<div class="card-header"> <h5>Upload Documents</h5></div>
		<div class="card-body card-block cue_text">
			<div class="col-md-12">             	
            	<div class="col col-md-8">
                	<label for="text-input" class=" form-control-label">1) Authorized Establishment and Held Strength of Regular Army (Commands and Formations)</label>
                </div>
            	<div class="col col-md-4">
             		<input type="file" id="auth_esta_str_regarmy_half" name="auth_esta_str_regarmy_half" class="form-control" onchange="fileValid(this);">
				</div>             	
			</div>
			<div class="col-md-12" style="margin-top: 5px;">             	
            	<div class="col col-md-8">
                	<label for="text-input" class=" form-control-label">2) Statistical Digest on Manpower</label>
                </div>
            	<div class="col col-md-4">
             		<input type="file" id="stati_digest_manpwr_restr_half" name="stati_digest_manpwr_restr_half" class="form-control" onchange="fileValid(this);">
				</div>             	
			</div>
			<div class="col-md-12" style="margin-top: 8px;">             	
            	<div class="col col-md-8">
                	<label for="text-input" class=" form-control-label">3) Statistical Digest on ‘A’ & ‘B’ Vehicles</label>
                </div>
            	<div class="col col-md-4">
             		<input type="file" id="stati_digest_a_b_veh_conf_four" name="stati_digest_a_b_veh_conf_four" class="form-control" onchange="fileValid(this);">
				</div>             	
			</div>
			<div class="col-md-12" style="margin-top: 8px;">             	
            	<div class="col col-md-8">
                	<label for="text-input" class=" form-control-label">4) Statistical Digest on Armament and Equipment</label>
                </div>
            	<div class="col col-md-4">
             		<input type="file" id="stati_digest_arm_equi_conf_four" name="stati_digest_arm_equi_conf_four" class="form-control" onchange="fileValid(this);">
				</div>             	
			</div>
			<div class="col-md-12" style="margin-top: 8px;">             	
            	<div class="col col-md-8">
                	<label for="text-input" class=" form-control-label">5) Statistical Digest on FOL, Ration, Losses, Animals, Labour etc.</label>
                </div>
            	<div class="col col-md-4">
             		<input type="file" id="stati_digest_fol_ration_losses_etc_restr_half" name="stati_digest_fol_ration_losses_etc_restr_half" class="form-control" onchange="fileValid(this);">
				</div>             	
			</div>
			<div class="col-md-12" style="margin-top: 8px;">             	
            	<div class="col col-md-8">
                	<label for="text-input" class=" form-control-label">6) Strength of the Indian Army</label>
                </div>
            	<div class="col col-md-4">
             		<input type="file" id="stre_indian_army_conf_annual" name="stre_indian_army_conf_annual" class="form-control" onchange="fileValid(this);">
				</div>             	
			</div>
			<div class="col-md-12" style="margin-top: 8px;">             	
            	<div class="col col-md-8">
                	<label for="text-input" class=" form-control-label">7) MT Accidents (WIR) on Army ‘B’ Vehicles</label>
                </div>
            	<div class="col col-md-4">
             		<input type="file" id="mt_accid_army_bveh_restr_annual" name="mt_accid_army_bveh_restr_annual" class="form-control" onchange="fileValid(this);">
				</div>             	
			</div>
			<div class="col-md-12" style="margin-top: 8px;">             	
            	<div class="col col-md-8">
                	<label for="text-input" class=" form-control-label">8) Study Report on Closed Court of Inquiry</label>
                </div>
            	<div class="col col-md-4">
             		<input type="file" id="studrpt_court_inquiry_restr_fiveyear" name="studrpt_court_inquiry_restr_fiveyear" class="form-control" onchange="fileValid(this);">
				</div>             	
			</div>
		</div>
		<div class="card-footer" align="center">
			<input type="reset" class="btn btn-success btn-sm" value="Clear" onclick="clearall()">   
           	<input type="submit" class="btn btn-primary btn-sm" value="Upload">        	
        </div> 	
	</div>
</div>
</form:form>
</body>
</html>