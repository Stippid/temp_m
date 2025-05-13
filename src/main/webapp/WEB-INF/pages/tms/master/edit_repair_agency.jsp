<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<form:form name="#" id="#" action="edit_repairAction?${_csrf.parameterName}=${_csrf.token}" method='POST' commandName="edit_repairCMD" enctype="multipart/form-data">
	<div class="animated fadeIn">
		<div class="">
			<div class="container" align="center">
				<div class="card">
				<div class="card-header"><h5>Repair Agency<br></h5><h6><span style="font-size: 10px;font-size:15px;color:red">(To be entered by MISO)</span></h6></div>
					
					<div class="card-body card-block" id="mainViewSelection" style="display: block;">
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong style="color: red;">* </strong>Repair Agency </label>
									</div>
									<div class="col-12 col-md-8">
								 <input type="hidden" id="id" name="id" value="${edit_repairCMD.id}"/>
										<input type="text" id="re_agency" name="re_agency"   maxlength="8" class="form-control" autocomplete="off">
									</div>
								</div>
							</div>
						<div class="col-md-6">
	              					<div class="row form-group">
					                	<div class="col-md-5">
					                  		<label class=" form-control-label">MCT  (4 Digit)</label>
					                	</div>
					                	<div class="col-md-7">
					                	<input type="hidden" id="mct_main_id" name="mct_main_id" placeholder="" class="form-control autocomplete" autocomplete="off" maxlength="100">
					                 		<input type="text" id="sermct_main_nomen" name="sermct_main_nomen" placeholder="" class="form-control autocomplete" autocomplete="off" maxlength="100">
				            			</div>
				        			</div>	
	              				</div>
						</div>
						</div>
					<div class="card-footer" align="center">
						<input type="reset" class="btn btn-success btn-sm" value="Clear">   
					
					<input type="submit"  class="btn btn-primary btn-sm" value="Update" onclick=" return validate();" >
					
				
					</div>
					
					
				</div>
			</div>
		</div>
	</div>
</form:form>


<script>


$("input#sermct_main_nomen").keyup(function(){
	var sermct_main_nomen = this.value;
	
	var mct_nomenAuto=$("#sermct_main_nomen");
	mct_nomenAuto.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
	        type: 'POST',
	        url: "getserMctMainnomainTmsList?"+key+"="+value,
	        data: {sermct_main_nomen:sermct_main_nomen},
	          success: function( data ) {
	        	  var susval = [];
	        	  var length = data.length-1;
	        		if(data.length != 0){
		        		var enc = data[length].substring(0,16);
		        	}
		        	for(var i = 0;i<data.length;i++){
		        		susval.push(dec(enc,data[i]));
		        	}
		        	response( susval ); 
	          }
	        });
	      },
	      minLength: 1,
	      autoFill: true,
	      change: function(event, ui) {
	    	 if (ui.item) {   	        	  
	        	  return true;    	            
	          } else {
	        	  alert("Please Enter Valid MCT Nomenclature.");
	        	  $("#sermct_main_nomen").val("");
	        	  mct_nomenAuto.val("");	        	  
	        	  mct_nomenAuto.focus();
	        	  return false;	             
	    	}   	         
	    }, 
		select: function( event, ui ) {
	      	var mct_nomen = ui.item.value;
		
			$.post("getserMctMainTmsListclikid?"+key+"="+value, {
				mct_nomen:mct_nomen
			}).done(function(j) {
				 var length = j.length-1;
		          var enc = j[length].substring(0,16);
		          $("#mct_main_id").val(dec(enc,j[0]));
			}).fail(function(xhr, textStatus, errorThrown) {
			});	    	
		}
	});
});
function validate() {
	 $.ajaxSetup({
       async : false
	});

if ($("input#re_agency").val() == "") {
	alert("Please Enter Repair Agency ");
	$("input#re_agency").focus();
	return false;
}

if ($("input#sermct_main_nomen").val() == "") {
	alert("Please Enter MCT  (4 Digit)");
	$("input#sermct_main_nomen").focus();
	return false;
} 

return true;
}
$(document).ready(function() {
 
	
		
	
	$("#re_agency").val('${edit_repairCMD.repair}');
	$("#mct_main_id").val('${edit_repairCMD.mct}');

	$.post("getserMctMainTmsListcliknomen?"+key+"="+value, {
		mct_main_id:'${edit_repairCMD.mct}'
	}).done(function(j) {
		  var length = j.length-1;
          var enc = j[length].substring(0,16);
          $("#sermct_main_nomen").val(dec(enc,j[0]));
	}).fail(function(xhr, textStatus, errorThrown) {
	});	
	
});
	</script>