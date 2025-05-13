<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 


<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 

<script src="js/JS_CSS/jquery.dataTables.min.js"></script>
<script src="js/JS_CSS/dataTables.bootstrap.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<script src="js/miso/miso_js/jquery-1.12.3.js"></script>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>



<form:form name="delink_WE_PE_unitPers" id="delink_WE_PE_unitPers" action="delink_WE_PE_unitPersAction"  method='POST' commandName="delink_WE_PE_unitPersCmd">
	<div class="animated fadeIn">
		<div class="">
	    	<div class="container" align="center">
	    		<div class="card">
	    		   <div class="card-header">  <h5><b>DELINK SUS NO WITH WE/PE PERSONNEL</b><br><span style="font-size:12px;color:red">(To be entered by Line Dte)</span></h5></div>
		    		<div class="card-body card-block cue_text">
		    			<div class="col-md-12">
		    			   <div class="col-md-6">
				    			<div class="row form-group">  								
		               					<div class="col col-md-5">
		                 					<label for="text-input" class=" form-control-label">SUS No<strong style="color: red;">*</strong></label>
		               					</div>
		               					<div class="col-12 col-md-7">
		                 					<input  id="unit_sus_no" name="sus_no" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search" class="form-control autocomplete" autocomplete="off">
										</div>
		 							</div>
		 						</div>
		 						<div class="col-md-6">
					    			 <div class="row form-group">  								
			               					<div class="col col-md-5">
			                 					<label for="text-input" class=" form-control-label">Unit Name</label>
			               					</div>
			               					<div class="col-12 col-md-7">
			                 					<input  id="unit_name" name="unit_name" class="form-control autocomplete" autocomplete="off">
											</div>
			 							</div>				           
		    		          </div>
		    			</div>
		    			<div class="col-md-12">	
		    				 <div class="col-md-6">
	 							<div class="row form-group">
		                			<div class="col col-md-5">
		                  				<label class=" form-control-label">MISO WE/PE No</label>
		                			</div>
		                			<div class="col-12 col-md-7">
		                  				<textarea id="wepe_pers_no" name="wepe_pers_no" class="form-control autocomplete" autocomplete="off" disabled></textarea>
		                			</div>
		                		</div>
		                		</div>
		                	  <div class="col-md-6">
			                		<div class="row form-group">
			                			<div class="col col-md-5">
			                  				<label class=" form-control-label">Table Title</label>
			                			</div>
			                			<div class="col-12 col-md-7">
			                  				<textarea id="table_title" name="table_title" class="form-control autocomplete" autocomplete="off" disabled></textarea>
			                			</div>
			                		</div>
		    			    </div>
		    		 </div> 
		    		 <div class="col-md-12">	
		  					<div class="col-md-6">
		              			<div class="row form-group">	              			 	
	               					<div class="col col-md-5">
	                 					<label for="text-input" class=" form-control-label">Remarks</label>
	               					</div>
	               					<div class="col-12 col-md-7">
	                 					<textarea id="remarks" name="remarks" class="form-control char-counter1"></textarea>
									</div>	 							
		  						</div>
		  						</div>	
	   					</div>	
		    		 
		    		 
		    		 
		    		 </div>
			    		<div class="card-footer" align="center">
								<input type="reset" class="btn btn-success btn-sm" value="Clear">   
			              		<input type="submit" class="btn btn-primary btn-sm" value="Delink" onclick="return isValid();">
		               </div> 
               </div>
	    	</div>
	    </div>
	</div>
</form:form>


<script>
function isValid()
{
	if($("input#unit_sus_no").val() == "")
	{
		alert("Please enter Unit SUS No");
		$("input#unit_sus_no").focus();
		return false;
	}
	
	return true;
}

function toHex(str) {
	var hex = '';
	for(var i=0;i<str.length;i++) {
		hex += ''+str.charCodeAt(i).toString(16);
	}
   	return hex;    		
}
</script>

   
<script>
 $(function() {
                
        var wepetext=$("#unit_sus_no");
      
  	  wepetext.autocomplete({
  	      source: function( request, response ) {
  	        $.ajax({
  	      	type: 'POST',
	        url: "getCUEUnitSUSNoList_pers?"+key+"="+value,
  	        data: {sus_no:document.getElementById('unit_sus_no').value},
  	          success: function( data ) {
  	            //response( data );
  	            if(data.length > 1){
  	        	var susval = [];
  	        	var length = data.length-1;
	        		 var enc = data[length].columnName1.substring(0,16);
                 for(var i = 0;i<data.length-1;i++){
                  susval.push(dec(enc,data[i].columnName));
       	  			}
		        	var dataCountry1 = susval.join("|");
  	            var myResponse = []; 
  	            var autoTextVal=wepetext.val();
  				$.each(dataCountry1.toString().split("|"), function(i,e){
  					var newE = e.substring(0, autoTextVal.length);
  					//if (newE.toLowerCase() === autoTextVal.toLowerCase()) {
					if (e.toLowerCase().includes(autoTextVal.toLowerCase())) {
  					  myResponse.push(e);
  					}
  				});      	          
  				response( myResponse ); 
  				}
  	          }
  	        });
  	      },
  	      minLength: 1,
  	      autoFill: true,
  	      change: function(event, ui) {
  	    	 if (ui.item) {   	        	  
  	        	  return true;    	            
  	          } else {
  	        	  alert("Please Enter Approved Unit SUS No");
  	        	$("#unit_name").val("");
  	        	$("#wepe_pers_no").val("");  
  				 $("#table_title").val("");
  	        	  wepetext.val("");	        	  
  	        	  wepetext.focus();
  	        	  return false;	             
  	          }   	         
  	      }, 
  	       select: function( event, ui ) {
  	        	$(this).val(ui.item.value);    	        	
  	        	getDetailsBySusNo($(this).val());	        	
  	        } 	     
  	    });
      
        try{
    		if(window.location.href.includes("?"))
    		{
    			var url = window.location.href.split("?")[0];
    			window.location = url;
    		}
    	}
    	catch(e){ } 
      }); 
 
 
 function getDetailsBySusNo(val)
 {
	if(val != "" || val != null)
	{
		$.ajaxSetup({
		    async: false
		});
		$.post("getCUEUnitsList?"+key+"="+value,{sus_no : val}).done(function(j){
	   	 	if(j !="" && j != null){
				$("input#unit_name").val(j);
			}else
				$("#unit_name").val("");
	     }).fail(function(xhr, textStatus, errorThrown) { }); 
		

		$.ajaxSetup({
		   async: false
		});
		$.post("getwepePersno?"+key+"="+value,{sus_no : val}).done(function(j){
	   		if(j[0] !="" && j[0] != null){
				$("#wepe_pers_no").val(j[0][0]);  
				$("#table_title").val(j[0][1]);
		 	}else{
				$("#wepe_pers_no").val("");  
				$("#table_title").val("");
			} 
		}).fail(function(xhr, textStatus, errorThrown) { }); 
	}
}
</script>