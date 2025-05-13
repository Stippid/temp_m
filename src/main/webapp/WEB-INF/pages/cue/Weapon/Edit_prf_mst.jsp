<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/excluded/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 

<script src="js/JS_CSS/jquery.dataTables.min.js"></script>
<script src="js/JS_CSS/dataTables.bootstrap.min.js"></script>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
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
<form:form action="Editprf_mstAction" method="post" class="form-horizontal" commandName="Editprf_mstCMD"> 
	<div class="animated fadeIn">
		 	<div class="container" align="center">
	    		<div class="card">
	          		<div class="card-header"><h5>Edit PRF MASTER</h5></div>
	          			<div class="card-body card-block cue_text">
	            			<div class="col-md-12">
								<div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-6">
	                  						<label class=" form-control-label">PRF Group </label>
	                					</div>
	                					<div class="col-12 col-md-6"> 
	                					<input type="hidden" id="id" name="id" placeholder="" class="form-control"  value="${Editprf_mstCMD.id}">
	                  						<input type="text" id="prf_group" name="prf_group" value="${Editprf_mstCMD.prf_group}" class="form-control"  readonly="readonly" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search">
	                					</div>
								   </div>
								   </div>
								   </div>
								   <div class="col-md-12">
								   <div class="col-md-6">
								   <div class="row form-group">
	                					<div class="col col-md-6">
	                  						<label class=" form-control-label">COS Section </label>
	                					</div>
	                					<div class="col-12 col-md-6"> 
	                  						<input type="text" id="coss_section" name="coss_section" class="form-control" value="${Editprf_mstCMD.coss_section}" readonly="readonly">
	                					</div>
								   </div>
								   </div>
								   </div>
								   <div class="col-md-12">
								   <div class="col-md-6">
								   <div class="row form-group">
	                					<div class="col col-md-6">
	                  						<label class=" form-control-label">Nodal Dte <strong style="color: red;">*</strong></label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                					<input type="hidden" id="nodal_dte_hidden" name="nodal_dte_hidden" class="form-control" value="${Editprf_mstCMD.nodal_dte}">
	                  						<select name="nodal_dte" id="nodal_dte" class="form-control" > </select>
	                					</div>
	              					</div>
	              					
								</div>
							</div>
	            		</div>
						
							
					<div class="card-footer" align="center">
	              		<input type="submit" class="btn btn-primary btn-sm" value="Update" onclick="return isvalidation();">
	              		<a href="search_prf_mstUrl" class="btn btn-danger btn-sm" onClick="javascript:window.close('','_parent','');">  Cancel </a>
		            </div> 
	        	</div>
			</div>
	</div>
</form:form>

<c:if test="${not empty msg}">
	<input type="hidden" name="msg" id="msg" value="${msg}" disabled="disabled"/>
</c:if>

<c:url value="updateprf_mstUrl" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid">
	<input type="hidden" name="updateid" id="updateid" value="0"/>
</form:form> 


<script>
var size;
       $(document).ready(function() {
    	   var nodal_dte_hidden = $("#nodal_dte_hidden").val();
    	 
    	   $.post("getnodal_dirList?"+key+"="+value).done(function(j) {
    		   var length = j.length-1;
   			var enc = j[length].columnName1.substring(0,16); 
    		   var options = '<option value="">'+ "--Select--" + '</option>'; 
				for ( var i = 0; i < j.length-1; i++) {
					if(nodal_dte_hidden == dec(enc,j[i].columnCode)){
					options += '<option value="' + dec(enc,j[i].columnCode)+ '" selected=selected  >'+ dec(enc,j[i].columnName)+ '</option>';
					}
				else{
					 options += '<option value="' + dec(enc,j[i].columnCode)+ '" >'+ dec(enc,j[i].columnName)+ '</option>';
					}
				}	
				$("select#nodal_dte").html(options);  		
					
    	   }).fail(function(xhr, textStatus, errorThrown) { }); 
    	   
    	   
    	   try{
               if(window.location.href.includes("&msg="))
               {
                       var url = window.location.href.split("?updateid=")[0];
                       var id= window.location.href.split("?updateid=")[1].split("&msg=")[0];
                        document.getElementById('updateid').value=id;
                         document.getElementById('updateForm').submit();
               }
               
       }
       catch (e) {
               // TODO: handle exception
       }

 });    
       
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
   
   function blockSpecialChar(e){
       var k;
       document.all ? k = e.keyCode : k = e.which;
       return ((k > 64 && k < 91));
       }
   
    function cool(d)
   {
       size=d.value.length;
      if(size == 1)
    	   alert("Enter 2 Digit Code")
    	 $("input#code").focus();
   } 
   
   function isvalidation(){
	      if($("select#nodal_dte").val()==""){
  			alert("Please Select Nodal Dte")
  			$("select#nodal_dte").focus();
  			return false;
  		}
	      return true;
   }
   
       
</script>
