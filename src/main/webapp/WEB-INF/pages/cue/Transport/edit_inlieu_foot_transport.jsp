<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

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

<form:form action="edit_inlieu_foot_transportAction" method="post" class="form-horizontal" commandName="edit_inlieu_foot_transportCMD"> 
	<div class="animated fadeIn">
		 	<div class="container" align="center">
	    		<div class="card">
	    			<div class="card-header"><h5>Edit In Lieu GENERAL NOTES For Transport</h5></div>
	          			<div class="card-body card-block cue_text">
	            			<div class="col-md-12">
								<div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-6">
	                  						<label class=" form-control-label">MISO WE/PE No</label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                					    <input type="hidden" id="id" name="id" placeholder="" class="form-control" value="${edit_inlieu_foot_transportCMD.id}">
	                  						<input type="text" id="we_pe_no" name="we_pe_no" maxlength="100" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search" class="form-control autocomplete" value="${edit_inlieu_foot_transportCMD.we_pe_no}" autocomplete="off" readonly="readonly">
	                					</div>
	              					</div>
								</div>
								<div class="col-md-6">
								    <div class="row form-group">
	                					<div class="col col-md-6">
	                  						<label class=" form-control-label">Table Title </label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                  						<input type="text" id="table_title" name="table_title" placeholder="" class="form-control autocomplete" autocomplete="off" readonly="readonly">
	                					</div>
	              					</div>
								</div>
	            		</div>
						 <div class="col-md-12"><span class="line"></span></div>
							<div class="col-md-12">
									<div class="col-md-6">
	              						<div class="row form-group">
	                						<div class="col col-md-6">
	                  							<label class=" form-control-label">Std. MCT</label>
	                						</div>
	                						<div class="col-12 col-md-6">
	                  						     <input type="text" id="mct_no" name="mct_no" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search" class="form-control autocomplete" value="${edit_inlieu_foot_transportCMD.mct_no}" autocomplete="off" readonly="readonly">
	                					    </div>
	              						</div>
	              					</div>	
	              					<div class="col-md-6">
							            <div class="row form-group">
							                <div class="col col-md-6">
							                    <label class=" form-control-label">Std. Nomenclature </label>
							                </div>
							                <div class="col-12 col-md-6">
							                     <input type="text" id="std_nomclature" name="std_nomclature" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search" class="form-control autocomplete" autocomplete="off" readonly="readonly"> 
							                </div>
							            </div>
							       </div>
						    </div>
						    <div class="col-md-12">
						        <div class="col-md-6">
						              	<div class="row form-group">
	                						<div class="col col-md-6">
	                  							<label class=" form-control-label">Base Authorisation </label>
	                						</div>
	                						<div class="col-12 col-md-6">
	                  						     <input type="text" id="auth_amt" name="auth_amt" placeholder=""  class="form-control" readonly="readonly">
	                					    </div>
	              						</div>
	              				</div>
	              					<div class="col-md-6">
							            <div class="row form-group">
							                <div class="col col-md-6">
							                    <label class=" form-control-label">Actual In Lieu Authorisation <strong style="color: red;">*</strong></label>
							                </div>
							                <div class="col-12 col-md-6">
							                     <input type="text" id="actual_inlieu_auth" name="actual_inlieu_auth" placeholder="" class="form-control" value="${edit_inlieu_foot_transportCMD.actual_inlieu_auth}" onkeypress="return isNumber0_9Only(event);" autocomplete="off" maxlength="5">
							                </div>
							            </div>
							          </div>
							  </div>
							  <div class="col-md-12">
							       <div class="col-md-6">
										<div class="row form-group">
	                						<div class="col col-md-6">
	                  							<label class=" form-control-label">In Lieu MCT </label>
	                						</div>
	                						<div class="col-12 col-md-6">
	                  						     <input type="text" id="in_lieu_mct" name="in_lieu_mct" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search" class="form-control autocomplete" value="${edit_inlieu_foot_transportCMD.in_lieu_mct}" autocomplete="off" readonly="readonly">
	                					    </div>
	              						</div>
	              				  </div>
	              				  <div class="col-md-6">
							            <div class="row form-group">
							                <div class="col col-md-6">
							                    <label class=" form-control-label">In Lieu Nomenclature </label>
							                </div>
							                <div class="col-12 col-md-6">
							                     <input type="text" id="in_std_nomclature" name="in_std_nomclature" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search" class="form-control autocomplete" autocomplete="off" readonly="readonly">
							                </div>
							            </div>	
							      </div>
							</div>
							<div class="col-md-12">
								<div class="col-md-6">
										<div class="row form-group">
							                <div class="col col-md-6">
							                     <label class=" form-control-label">Condition <strong style="color: red;">*</strong></label>
							                </div>
							                <div class="col-12 col-md-6">
							                     <textarea class="form-control" name="condition" id="condition" maxlength="255">${edit_inlieu_foot_transportCMD.condition}</textarea>
							                </div>
                                        </div>	
				               </div>
				               <div class="col-md-6">
							            <div class="row form-group">
							                <div class="col col-md-6">
							                     <label class=" form-control-label">Remarks </label>
							                </div>
							                <div class="col-12 col-md-6">
							                     <textarea class="form-control char-counter1" name="remarks" maxlength="255">${edit_inlieu_foot_transportCMD.remarks}</textarea>
							                </div>
                                        </div>			
              			     </div>
              		    </div>
	              	</div>
					<div class="card-footer" align="center">
	              		<input type="submit" class="btn btn-primary btn-sm" value="Update" onclick="return validate();setfun();">
	              		<a href="search_inlieu_foot_transport" class="btn btn-danger btn-sm" onClick="javascript:window.close('','_parent','');">  Cancel </a>
		            </div> 		
	        	</div>
			</div>
	</div>
</form:form>

<c:if test="${not empty msg}">
	<input type="hidden" name="msg" id="msg" value="${msg}" disabled="disabled"/>
</c:if>
	<c:url value="UpdateFootTransport" var="updateUrl" />
	<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid">
		<input type="hidden" name="updateid" id="updateid" value="0"/>
	</form:form>
<script>
function setfun()
{
	if($("#actual_inlieu_auth").val() == ""){
		$("#actual_inlieu_auth").val(0);
		
    }
}
function isNumber0_9Only(evt){
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if( ! ( charCode >= 48 && charCode <= 57 ) && charCode != 8 ){
		 return false;
	}
    return true;
} 

function validate(){    
	if($("#we_pe_no").val() == ""){
		alert("Please Enter the WE/PE Number");
		 $("#we_pe_no").focus();
		return false;
	 } 
     
	 if($("#mct_no").val() == ""){
		alert("Please Enter the Std. MCT Number");
		 $("#mct_no").focus();
		return false;
	 } 
	 if($("#std_nomclature").val() == ""){
			alert("Please Enter the Std. Nomenclature");
			 $("#std_nomclature").focus();
			return false;
     } 
	 
	 var a = document.getElementById("auth_amt").value;
	 if (isNaN(a) || a < 1) {
		 alert("In Lieu Foot Transport Not Possible.");
		 $("#auth_amt").focus();
		 return false;
     }
	 
	 var b = document.getElementById("actual_inlieu_auth").value;
	 var numbers = /^[0-9]+$/;	
	 if(b == ""){
		 alert("Please Enter the Actual In Lieu Authorisation");
		 $("#actual_inlieu_auth").focus();
		 return false;
	 }else{
		 if(!b.match(numbers)){
			 alert("Only Numeric Value is Allowed in the Actual In Lieu Authorisation");
			 $("#actual_inlieu_auth").focus();
			 return false;
	     } 
	 }
 
	 if($("#in_lieu_mct").val() == ""){
		  alert("Please Enter the In Lieu MCT Number");
		  $("#in_lieu_mct").focus();
		  return false;
	 } 
     
     if($("#condition").val() == ""){
		  alert("Please Enter the Condition");
		  $("#condition").focus();
		  return false;
	  } 
     
	return true;
}
</script>
<script>
$(function(){
	
});
</script>

<script>
$(document).ready(function() {
   	   
   		 $('#remarks').keyup(function() {
   		        this.value = this.value.toUpperCase();
   		    });
   	   var mct_no = '${edit_inlieu_foot_transportCMD.mct_no}';
   	   var mct = mct_no;
   	   var we_pe_no = '${edit_inlieu_foot_transportCMD.we_pe_no}';
   	   var mct = '${edit_inlieu_foot_transportCMD.in_lieu_mct}';
   	 
   	$.post("getBaseAuthAmountDetailsList?"+key+"="+value, {we_pe_no:we_pe_no,mct_no:mct_no}).done(function(j) {
 		 $("input#auth_amt").val(j);	
       }).fail(function(xhr, textStatus, errorThrown) { }); 


   	  $.ajaxSetup({
   		    async: false
   		});
   	 $.post("getMctMaintoStdNomenclatureList?"+key+"="+value, {mct:mct}).done(function(j) {
   		 $("#in_std_nomclature").val(j);	
        }).fail(function(xhr, textStatus, errorThrown) { });

        
          $.ajaxSetup({
       	    async: false
       	});
          $.post("getWePetoTableTitleList?"+key+"="+value, {we_pe_no:we_pe_no}).done(function(j) {
       		 for ( var i = 0; i < j.length; i++) {
     			   $("#table_title").val(j[i]);
     	   	   }
             }).fail(function(xhr, textStatus, errorThrown) { });
      	   
      	$.ajaxSetup({
      	    async: false
      	});
      	$.post("getMctMaintoStdNomenclatureList?"+key+"="+value, {mct:mct}).done(function(j) {
 		   for ( var i = 0; i < j.length; i++) {
     		   $("#std_nomclature").val(j[i]);
 	   		}
           }).fail(function(xhr, textStatus, errorThrown) { }); 
      
   	   
       try{
			if(window.location.href.includes("&msg="))
			{
				var url = window.location.href.split("?updateid=")[0];
				var id= window.location.href.split("?updateid=")[1].split("&msg=")[0];
				window.location = url;
				document.getElementById('updateid').value=id;
		 		document.getElementById('updateForm').submit();
			}
			
		}
		catch (e) {
			// TODO: handle exception
		}
        
          document.getElementById("auth_amt").value=parseFloat(document.getElementById("auth_amt").value);
});
</script>
