<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>


<form:form name="make_master_aviation" id="make_master_aviation" action="make_master_aviationActionRPAS" method="post" class="form-horizontal" commandName="make_master_aviationCMD"> 
<spring:htmlEscape defaultHtmlEscape="true" /> 
	<div class="animated fadeIn">
	    	<div class="container" align="center">
	    		<div class="card">
	    			<div class="card-header"><h5>ALLOTMENT OF MAKE FOR RPAS</h5> <h6 class="enter_by"><span style="font-size:12px;color:red;">(To be entered by MISO)</span></h6></div>
    				<div class="card-body card-block">
            			<div class="col-md-12">
            				<div class="col-md-6">
            					<div class="row form-group">
                					<div class="col-md-4">
                  						<label class=" form-control-label"><strong style="color: red;">* </strong>ACT Nomenclature</label>
                					</div>
                					<div class="col-md-8">
                						<input type="text" id="act_nomen" name="act_nomen" placeholder="" maxlength="100" class="form-control autocomplete" autocomplete="off">
                  					    <input type="hidden" id="act_slot_id" name="act_slot_id" >
		        					</div>
              					</div>
            				</div>
            				<div class="col-md-6">
            					<div class="row form-group">
                					<div class="col-md-12">
                					    <label class=" form-control-label">ACT - </label> &emsp;
                  						<label id="actSlotId"></label>
                					</div>
                				</div>
            				</div>
            			</div>
						<div class="col-md-12">
            				<div class="col-md-6">
            					<div class="row form-group">
                					<div class="col-md-4">
                  						<label class=" form-control-label"><strong style="color: red;">* </strong>Make No</label>
                					</div>
                					<div class="col-md-8">
                  						<input type="text" id="make_no" name="make_no" class="form-control" maxlength="3" onkeypress="return isNumber0_9Only(event);" autocomplete="off" >
                  					</div>
              					</div>
            				</div>
            			</div>
            			<div class="col-md-12">
            				<div class="col-md-6">
            					<div class="row form-group">
               						<div class="col-md-4">
                 							<label class=" form-control-label"><strong style="color: red;">* </strong>Make Nomenclature</label>
               						</div>
               						<div class="col-md-8">
                 						<input type="text" id="description" name="description" maxlength="100" placeholder="" class="form-control" autocomplete="off"  oninput="this.value = this.value.toUpperCase()" onkeyup="checkSpace('description');"/>
               					    </div>
              				    </div>
            				</div>
            			</div>
            		</div>
					<div class="card-footer" align="center" class="form-control">
						<input type="reset" class="btn btn-success btn-sm" value="Clear"/>   
	              		<input type="submit" class="btn btn-primary btn-sm" value="Save" onclick="return validate();">
		            </div> 		
	        	</div>
			</div>
    </div>
</form:form>

<script>
function validate(){
	if($("#act_slot_id").val() == ""){
		alert("Please Enter ACT Nomenclature.");
		return false;
	} 
	if($("#make_no").val() == ""){
		alert("Please Enter MAKE Nomenclature.");
		return false;
	}
	if($("#description").val() == ""){
		alert("Please Enter MAKE Nomenclature.");
		return false;
	}
	return true;
}

function isNumber0_9Only(evt) {
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if (!(charCode >= 48 && charCode <= 57) && charCode != 8) {
		return false;
	}
	return true;
}
</script>
<script>
$(document).ready(function() {
	$("#act_nomen").keyup(function(){
		var actMain = this.value;
	 	var mctMainAuto=$("#act_nomen");
	  	mctMainAuto.autocomplete({
	  	source: function( request, response ) {
	        $.ajax({
	        	type: 'POST',
			    url: "getRPASActNomenList?"+key+"="+value,
	        	data: {actMain:actMain},
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
	        	alert("Please Enter Approved ACT Nomenclature.");
	        	$("#actSlotId").text("");
 	        	$("#act_slot_id").val("");
 	        	$("#make_no").val("");
	        	mctMainAuto.val("");	        	  
	        	mctMainAuto.focus();
	        	return false;	             
	    	}   	         
	    },	    
	    select: function( event, ui ) {
	      	var act_nomen = ui.item.value;	      	
		
			$.post("getmakemasteraviationtMctNomenToNoList?"+key+"="+value, {
				act_nomen:act_nomen
			}).done(function(j) {
				var length = j.length-1;
		        var enc = j[length].substring(0,16);
	      		$("#actSlotId").text(dec(enc,j[0]));
 	        	$("#act_slot_id").val(dec(enc,j[0]));
		 				
		 				$.post("getmaxMakeNoaviation?"+key+"="+value, {
		 					actSlotId:dec(enc,j[0])
		 				}).done(function(j) {
		 					var max1 = ('000' + j).substr(-3);
			    	   	 	$("#make_no").val(max1);
		 				}).fail(function(xhr, textStatus, errorThrown) {
		 				});

			}).fail(function(xhr, textStatus, errorThrown) {
			});      	
		}	     
	});
});
	
});

</script>