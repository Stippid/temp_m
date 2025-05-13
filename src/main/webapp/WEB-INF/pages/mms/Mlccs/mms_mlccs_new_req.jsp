<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="Stylesheet" href="js/Calender/jquery-ui.css" >
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link rel="stylesheet" href="js/common/nrcss.css"> 

<body class="mmsbg">
<form:form name="mms_mlccs_new_reqAction" id="mms_mlccs_new_reqAction" action="mms_mlccs_new_reqAction?${_csrf.parameterName}=${_csrf.token}" method='post' commandName="mms_mlccs_new_reqCMD" enctype="multipart/form-data">      		
	<div class="">
    	<div class="container" align="center">
    		<div class="card">
    		
    		    <div class="card-header mms-card-header">
		             <b>REQUEST TO ADD NEW EQPT IN MLCCS</b><br><span class="mms-card-subheader">(To be used by Dte/Fmn/Unit based on Role)</span>
		        </div> 
		        
          		<div class="card-body card-block">	
            		<div class="col-md-12 row form-group">		
	             		 <div class="col-md-3" style="text-align: left;">
	               			  <label class=" form-control-label"><strong style="color: red;">* </strong>Name of Dte/Fmn/Unit </label>
	             		 </div>
	             		 <div class="col-md-3">
	                		  <input type="text" id="req_agency" name="req_agency" placeholder="Search..." 
	                		  class="form-control-sm form-control autocomplete" autocomplete="off" maxlength="50"/>
	             		 </div>		
  					</div>
  					
  					<div class="col-md-12 row form-group" style="margin-top: -10px;">
             	 		 <div class="col-md-3" style="text-align: left;"> 
	               			  <label class=" form-control-label"><strong style="color: red;">* </strong>Auth/Letter No.</label>
	             		 </div>
	             		 <div class="col-md-3">
	             			  <input type="text" id="auth_lett_no" name="auth_lett_no" class="form-control-sm form-control" maxlength="50" autocomplete="off" placeholder="Enter Auth/Letter No..."/>
	               		 </div>
	             		 								
	               		 <div class="col-md-3" style="text-align: left;">
	                 		  <label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Auth Dated</label>
	               		 </div>
	               		 <div class="col-md-3">
	               			  <input type="date" class="form-control-sm form-control" id ="auth_date" name ="auth_date" maxlength="10">
						  </div>	    
  					</div>
  					
  					<div class="col-md-12 row form-group" style="margin-top: -10px;">
             	 		 <div class="col-md-3" style="text-align: left;"> 
	               			  <label class=" form-control-label"><strong style="color: red;">* </strong>Cat Part No.</label>
	             		 </div>
	             		 <div class="col-md-3">
	             			  <input type="text" id="cat_part_no" name="cat_part_no" class="form-control-sm form-control" autocomplete="off" placeholder="Enter Cat Part No..." maxlength="50"/>
	               		 </div>    
  					</div>
  				
		            <div class="col-md-12 row form-group" style="margin-top: -10px;">
		       	        <div class="col-md-3" style="text-align: left;padding-left: 28px;">
		                  		<label class=" form-control-label">PRF Group</label>
		                </div>
		                			
		                <div class="col-md-2">
		                  	    <input type="text" id="from_prf_Search" name="from_prf_Search" placeholder="Search..." class="form-control-sm form-control" autocomplete="off" size="10" maxlength="100"/>
		                </div>
		                			
		                <div class="col-md-1">
		                	    <img src="js/miso/images/searchImg.jpg" width="30px;" height="30px;" onclick="getfromprf();" title="Click to Search" style="cursor:pointer;">
		                </div>
		                			
		                <div class="col-md-6">
               			        <select id="prf_code" name="prf_code" class="form-control-sm form-control" disabled="disabled">
                                    <option selected value="">--Select the Value--</option>     
                                </select>    
		                </div>
				    </div>
  					
  					<div class="col-md-12 row form-group" style="margin-top: -10px;">
             	 		 <div class="col-md-3" style="text-align: left;"> 
	               			  <label class=" form-control-label"><strong style="color: red;">* </strong>Nomenclature</label> 
	             		 </div>
	             		 <div class="col-md-9">
	             			  <textarea id="nomen" name="nomen" placeholder="Exact Nomenclature" class="form-control-sm form-control"
	             			   autocomplete="off" maxlength="255"></textarea>
	               		 </div>    
  					</div>
  					
  					<div class="col-md-12 row form-group" style="margin-top: -5px;">
             	 		 <div class="col-md-3" style="text-align: left;"> 
	               			  <label class=" form-control-label" style="margin-left: 13px;">Brief Description</label>
	             		 </div>
	             		 <div class="col-md-9">
	             			  <textarea  id="brief_desc" name="brief_desc" placeholder="Brief Details with make and models" class="form-control-sm form-control" 
	             			  autocomplete="off" maxlength="255"></textarea>
	               		 </div>    
  					</div>
  					
  					<div class="col-md-12 row form-group" style="margin-top: -10px;">
             	 		 <div class="col-md-3" style="text-align: left;"> 
	               			  <label class=" form-control-label"><strong style="color: red;">* </strong>Accounting Units</label> 
	             		 </div>
	             		 <div class="col-md-3">
	             			  <select name="au" id="au" class="form-control-sm form-control" >	
           							<option value="">--Select--</option>
           							<c:forEach var="item" items="${au_ca}">
           								<option value="${item[0]}">${item[1]}</option>	
           							</c:forEach>                  								
							  </select>
	               		 </div>
	             		 								
	               		 <div class="col-md-3" style="text-align: left;">
	                 		  <label class=" form-control-label"><strong style="color: red;">* </strong>Item Status</label>
	               		 </div>
	               		 <div class="col-md-3">
	               			  <select name="item_status" id="item_status" class="form-control-sm form-control" >	
           							<option value="">--Select--</option>
           							<c:forEach var="item" items="${item_st}">
           								<option value="${item[0]}">${item[1]}</option>	
           							</c:forEach>                  								
							 </select>
						  </div>	    
  					</div>
  					
  					<div class="col-md-12 row form-group" style="margin-top: -10px;">
             	 		 <div class="col-md-3" style="text-align: left;"> 
	               			  <label class=" form-control-label"><strong style="color: red;">* </strong>Item Category</label> 
	             		 </div>
	             		 <div class="col-md-3">
                  			 <select name="item_category" id="item_category" class="form-control-sm form-control" >	
           							<option value="">--Select--</option>
           							<c:forEach var="item" items="${item_ca}">
           								<option value="${item[0]}">${item[1]}</option>	
           							</c:forEach>                  								
							 </select>
	               		 </div>
	             		 								
	               		 <div class="col-md-3" style="text-align: left;">
	                 		  <label class=" form-control-label"><strong style="color: red;">* </strong>Upload Document</label> 
	               		 </div>
	               		 <div class="col-md-3">
						 <input type="file" id="doc_upload1" name="doc_upload1" class="form-control-sm form-control" onchange="checkFileExtImage(this)" autocomplete="off">
						 
						 </div>	    
  					</div>
  					
  					<div class="col-md-12 row form-group" style="margin-top: -10px;">
             	 		 <div class="col-md-3" style="text-align: left;"> 
	               			  <label class=" form-control-label" style="margin-left: 13px;">Remarks (if any)</label> 
	             		 </div>
	             		 <div class="col-md-9">
	             			 <textarea  id="spl_remarks" name="spl_remarks" placeholder="Enter Remarks..." class="form-control-sm form-control" 
	             			 autocomplete="off" maxlength="255"></textarea>
	               		 </div>    
  					</div>
  				</div>
  				
  				<div class="card-footer" align="center" style="margin-top: -10px;">
					<input type="button" class="btn btn-primary btn-sm" value="Clear"  onclick="btn_clc();" /> 
             		<input type="submit" class="btn btn-success btn-sm" value="Save" onclick="return isvalidData()" />            
             		<a href="mmsDashboard"><input type="button" class="btn btn-danger btn-sm" value="Cancel"></a>
            	</div> 
  			</div>   
  		</div>
	</div>
</form:form>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/Calender/jquery-ui.js"></script>
<script type="text/javascript" src="js/common/commonmethod.js"></script>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script> 
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 

<script>
function btn_clc(){
	location.reload(true);
}

function getfromprf(){
	var nParaValue = $("#from_prf_Search").val();
	if(nParaValue.length<=0) {
		alert("Enter Search Word...");
	    return false;
	}else{
	
		
                 $.post("getMMSPRFtListBySearch?"+key+"="+value, {
                	 nParaValue:nParaValue
             }, function(j) {
                   

             }).done(function(j) {
            		if(j.length <= 0 || j == null || j == ''){
        				alert("No Search Result Found");
        	 			$("#from_prf_Search").focus();
        	 		}else{
        	 			$("#prf_code").attr('disabled',false);
        	 			var options = '<option value="' + "-1" + '">'+ "--Select PRF Group--" + '</option>';
        	 			
        	 			var a = [];
        	 			var enc = j[j.length-1].substring(0,16);
        	 			for(var i = 0; i < j.length; i++){
        					a[i] = dec(enc,j[i]);
                        }
        	 			
        				var data=a[0].split(",");
        				var datap;
        				for ( var i = 0; i < data.length-1; i++) {
        					datap=data[i].split(":");
        					if (datap!=null) {
        						if (datap[1].length>90) {
        							options += '<option value="'+datap[0]+'" name="' + datap[1]+ '" >'+ datap[0]+ ' - '+ datap[1].substring(1,90)+ '</option>';
        						} else {
        							options += '<option value="'+datap[0]+'" name="' + datap[1]+ '" >'+ datap[0]+ ' - '+ datap[1]+ '</option>';
        						}
        					}
        				}	
        				$("select#prf_code").html(options); 
        	 		}
                     
             }).fail(function(xhr, textStatus, errorThrown) {
             });
		
	}
}

 function isvalidData(){	
	if($("#req_agency").val() == ""){
		alert("Please Enter Inroducing Agency");
		$("#req_agency").focus();
		return false;
	}
	
	if($("#auth_lett_no").val() == ""){
		alert("Please Enter Auth/Letter No.");
		$("#auth_lett_no").focus();
		return false;
	}
	
	if($("#auth_date").val() == ""){
		alert("Please Select Auth Date");
		$("#auth_date").focus();
		return false;
	} 
	
	var d = new Date();
	var c_d = d.getFullYear()+"-"+("0" + (d.getMonth()+1)).slice(-2)+"-"+d.getDate();
	
	if($("#auth_date").val() > c_d){
		$("#auth_date").focus();
		alert("Can't select the Future Date");
		return false;
	}
	
	if($("#cat_part_no").val() == ""){
		alert("Please Enter Cat/Part No");
		$("#cat_part_no").focus();
		return false;
	}
	
	if($("#prf_code").val() == ""){
		alert("Please Enter PRF Group");
		$("#prf_code").focus();
		return false;
	}
	
	if($("#nomen").val() == ""){
		alert("Please Enter Nomenclature");
		$("#nomen").focus();
		return false;
	}
	 
	if($("#au").val() == ""){
		alert("Please Select Accounting Units");
		$("#au").focus();
		return false;
	}
	
	if($("#item_status").val() == ""){
		alert("Please Select Item Status");
		$("#item_status").focus();
		return false;
	}
	
	if($("#item_category").val() == ""){
		alert("Please Select Item Category");
		$("#item_category").focus();
		return false;
	}
	 	
  
 
 if($("#upload_file_name").val() == ""){
		alert("Please Upload file");
		$("#upload_file_name").focus();
		return false;
	}  
	
	 if($("#doc_upload1").get(0).files.length == 0){
		alert("Please Select Upload File.");
		$("#doc_upload1").focus();
		return false;
	} 
	

	
	return true; 
} 

$("#req_agency").keyup(function(){
	var y = this.value;

  
	$().Autocomplete2('POST','getIntroAgencyList',req_agency,{y:y},'','','1');
	
});

$(document).ready(function() {
	
	$().getCurDt(auth_date);
	 
	try{
		if(window.location.href.includes("msg=")){
			var url = window.location.href.split("?msg")[0];
		    window.location = url;
	    } 
	}catch (e) {
		// TODO: handle exception
	}	
});




function checkFileExtImage(file) {
    var ext = file.value.match(/\.([^\.]+)$/)[1];
  switch (ext) {
            case 'jpg':
            case 'jpeg':
            case 'png':
            case 'pdf':  
            case 'JPG':
            case 'JPEG':
            case 'PNG':
            case 'PDF': 
           alert('Allowed');
      break;
            default:
              alert('Only *.jpg, *.jpeg *.png and *.pdf  file extensions allowed');
             file.value = "";
  }
}
</script>