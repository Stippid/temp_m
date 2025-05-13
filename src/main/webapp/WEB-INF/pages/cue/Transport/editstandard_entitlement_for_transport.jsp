

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script src="js/JS_CSS/jquery.dataTables.min.js"></script>
<script src="js/JS_CSS/dataTables.bootstrap.min.js"></script>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/miso/miso_js/jquery-1.12.3.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<script type="text/javascript">
$(document).ready(function() {
	$('body').toggleClass('open');
	$('.nav-item').toggle();
	$("div#divLogoutHidShow").hide();	
	document.getElementById('menuToggle').style.pointerEvents = 'none';
	$('#left-panel , #menuToggle').css("display","none");
});
</script>

<form:form  action="edittrans_AuthAction" method='POST' commandName="edititem_masterCmd" > 	
<div class="animated fadeIn">
			<div class="container" align="center">
	    		<div class="card">
	            	<div class="card-header"> <h5>Edit STANDARD AUTHORISATION FOR TRANSPORT</h5></div>
	            		<div class="card-body card-block cue_text">
	            			<div class="col-md-12">
	            			<div class="col-md-6">
	            				<div class="row form-group">
                					<div class="col col-md-6">
                  						<label class=" form-control-label">MISO WE/PE No </label>
                					</div>
                					<div class="col-12 col-md-6">
                  						<input type="text" id="we_pe_no" readonly="readonly" name="we_pe_no" maxlength="100" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search" class="form-control autocomplete" autocomplete="off"  value="${edititem_masterCmd.we_pe_no}">
                  						<input type="hidden" id="id" name="id" placeholder="" class="form-control" value="${edititem_masterCmd.id}">
	                  				</div>
              					</div>
              					</div>
              					<div class="col-md-6">
			            			<div class="row form-group">
		                				<div class="col col-md-6">
		                  					<label class=" form-control-label">Table Title</label>
		                				</div>
		                				<div class="col-12 col-md-6">
		                  					<input type="text" id="table_title" readonly="readonly" name="table_title" placeholder="" class="form-control autocomplete">
		                				</div>
		              				</div>
              				 </div>
							</div>
	            			<div class="col-md-12">
	            				<div class="col-md-6">
	            				<div class="row form-group">
                					<div class="col col-md-6">
                  						<label class=" form-control-label">MCT No</label>
                					</div>
                					<div class="col-12 col-md-6">
                  						<input type="text" id="mct_no" readonly="readonly" name="mct_no" maxlength="4" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search" class="form-control autocomplete"  autocomplete="off" value="${edititem_masterCmd.mct_no}">
                					</div>
              					</div>
              					</div>
	              				<div class="col-md-6">
									<div class="row form-group" >
	                					<div class="col col-md-6" >
	                  						<label class=" form-control-label">Std. Nomenclature </label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                  						<input type="text" id="std_nomclature" readonly="readonly" name="std_nomclature" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search" class="form-control autocomplete">
	                					</div>
	              					</div>
	              				</div>
              				</div>
              				<div class="col-md-12">
	              				<div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-6">
	                  						<label class=" form-control-label">Authorisation <strong style="color: red;">*</strong></label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                  						<input type="text" id="auth_amt1" name="auth_amt1" maxlength="5" class="form-control"  value="${edititem_masterCmd.auth_amt}" onkeypress="return validateNumber(event, this)">
	                					</div>
	              					</div>
								</div>
	              				<div class="col-md-6">	
		            			<div class="row form-group" >
	                					<div class="col col-md-6" >
	                  						<label class=" form-control-label">Remarks </label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                						<input type="hidden" id="remarks_hid" name="remarks_hid" class="form-control char-counter1"  value="${edititem_masterCmd.remarks}">
	                  						<textarea class="form-control char-counter1" maxlength="255" id="remarks" name="remarks" value="${edititem_masterCmd.remarks}" ></textarea> 
	                					</div>
	              					</div>
		            			
		            			</div>
	            			</div>
						</div>
						 <div  class="card-footer" align="center" >
							<input type="hidden" id="count" name="count" > 
							 <input type="submit" class="btn btn-primary btn-sm" value="Update" onclick="return checkDublicateMCT_WEPENO();" >
							<a href="searchstandardentitlement" class="btn btn-danger btn-sm" onClick="javascript:window.close('','_parent','');">  Cancel </a>
						</div>
	    		</div>
	    	</div>
	    	</div>
</form:form>

<c:if test="${not empty msg}">
	<input type="hidden" name="msg" id="msg" value="${msg}" disabled="disabled"/>
</c:if>
<c:url value="updatedeleterejectTransEntitlementDtl" var="updateUrl" />
	<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid">
		<input type="hidden" name="updateid" id="updateid" value="0"/>
	</form:form>

<script type="text/javascript">


$(document).ready(function() {
	
	 $('#remarks').keyup(function() {
	        this.value = this.value.toUpperCase();
	    });

	$("textarea#remarks").val($("input#remarks_hid").val());
	$('#we_pe_no').change(function() {
	   	var we_pe_no = this.value;     
	   
	    $.post("getWEPENoDetailsList?"+key+"="+value, {we_pe_no:we_pe_no}).done(function(j) {
	  		for ( var i = 0; i < j.length; i++) {           			
	   	 		$("input#table_title").val(j);	
	   	 	}
	        }).fail(function(xhr, textStatus, errorThrown) { }); 
	});  
	 
	  try{
			if(window.location.href.includes("&msg="))
			{
				var id= window.location.href.split("?updateid=")[1].split("&msg=")[0];
				 document.getElementById('updateid').value=id;
		 		 document.getElementById('updateForm').submit();
			}
			
		}
		catch (e) {
			// TODO: handle exception
		}
	document.getElementById("auth_amt1").value=parseFloat(document.getElementById("auth_amt1").value);
});

</script>

<script>
function toHex(str) {
	var hex = '';
	for(var i=0;i<str.length;i++) {
		hex += ''+str.charCodeAt(i).toString(16);
	}
   	return hex;    		
}

function validateNumber(evt) {
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57)) {
            return false;
    } else {
        if (charCode == 46) {
                return false;
        }
    }
    return true;
}
</script>

<script>
$(document).ready(function(){
	var we_pe_no = $("input#we_pe_no").val();	
 	
	$.post("getWEPENoDetailsList?"+key+"="+value, {we_pe_no:we_pe_no}).done(function(j) {
 		for ( var i = 0; i < j.length; i++) {           			
   	 		$("input#table_title").val(j);		
   	 	}
       }).fail(function(xhr, textStatus, errorThrown) { }); 
	
  	var mct_no =$("input#mct_no").val();	
	
  	 $.post("getMctNoDetailsList?"+key+"="+value, {mct: mct_no}).done(function(j) {
   		$("input#std_nomclature").val(j);
         }).fail(function(xhr, textStatus, errorThrown) { }); 
  
});

function checkDublicateMCT_WEPENO()
{
	
	if($("#we_pe_no").val() == ""){
		alert("Please Enter the We/PE No");
		$("#we_pe_no").focus();
		return false;
	}
	
	if($("#mct_no").val() == ""){
		alert("Please Select MCT No");
		$("#mct_no").focus();
		return false;
    }
	if($("#auth_amt1").val() == "" || $("#auth_amt1").val() == "0"){
		alert("Please enter Authorisation");
		$("#auth_amt1").focus();
		return false;
    }
	
	var mct_no = $("input#mct_no").val();
	var we_pe_no = $("input#we_pe_no").val();
	
	var count =   $("input#count").val();
	if(mct_no != '' && we_pe_no != '')
		{
		if(count > 0)
			{
				alert("Already exist pair of WE/PE No and MCT No!!");
				document.getElementById("we_pe_no").focus();
				return false;
			}
		else
			{
				return true;
			}
		}
	else
		{
				alert("Please Select WE/PE No and MCT No");
				document.getElementById("we_pe_no").focus();
				return false;
		}
	
	return true;
}
</script>

