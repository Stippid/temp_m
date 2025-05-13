<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<form:form  action="tms_model_masterAction" method='POST' commandName="tms_model_masterCmd" > 
<div class="animated fadeIn">
   		<div class="container" align="center">
 			<div class="card">
	   			<div class="card-header"><h5>ALLOTMENT OF MODEL</h5> <h6 class="enter_by"><span style="font-size:12px;color:red;">(To be entered by MISO)</span></h6></div>
	          		<div class="card-body card-block">
	            		<div class="col-md-12">	              					
	              			<div class="col-md-6">
	              				<div class="row form-group">	              			 	
               						<div class="col-md-4">
                 						<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> MCT Nomenclature</label>
               						</div>
               						<div class="col-md-8">
	               						<input type="text" id="mct_nomen" name="mct_nomen" class="form-control autocomplete" autocomplete="off">
	               						<input type="hidden" id="mct_main_id" name="mct_main_id" maxlength="4" class="form-control">
									</div> 							
	  							</div>
	  						</div>
	  						<div class="col-md-6">
								<div class="row form-group">           					
             						<div class="col-md-4">
               							<label class=" form-control-label"><strong style="color: red;">*</strong> Make Nomenclature</label>
             						</div>
             						<div class="col-md-8">
             							<select name="make_id" id="make_id" class="form-control">
											<option value="0">--Select--</option>
										</select>
									</div> 
             					</div>	
             				</div>           					
	  					</div>	
	  					<div class="col-md-12">	  
		            		<div class="col-md-6">
		  						<div class="row form-group">	           					
        	     					<div class="col-md-4">
            	   						<label class=" form-control-label"><strong style="color: red;">*</strong> Model </label>
             						</div>
             						<div class="col-md-8">
               							<input id="model_id" name="model_id" class="form-control" maxlength="3" onkeypress="return isNumberKey(event)" autocomplete="off">
             						</div>
             					</div>
             				</div>	           					
	              			<div class="col-md-6">	
		              			<div class="row form-group">              			 	
	              					<div class="col-md-4">
	                					<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Model Nomenclature</label>
	              					</div>
	              					<div class="col-md-8">
	                					<input  id="description" name="description" placeholder="" maxlength="100" class="form-control" autocomplete="off"  oninput="this.value = this.value.toUpperCase()" onkeyup="checkSpace('description');" />
									</div>
								</div>	 							
	  						</div>
	  					</div>	
	  				</div>
					<div class="card-footer" align="center" class="form-control">
						<input type="reset" class="btn btn-success btn-sm" value="Clear">   
	              		<input type="submit" class="btn btn-primary btn-sm" value="Save" onclick="return isValid();">
	              		
		            </div> 
	        	</div>
			</div>
	</div>
</form:form>

<script type="text/javascript">

$(document).ready(function() {
	
		$("#mct_nomen").keyup(function(){
			var mctMain = this.value;
		 	var mctMainAuto=$("#mct_nomen");
		  	mctMainAuto.autocomplete({
			  	source: function( request, response ) {
			        $.ajax({
			        	type: 'POST',
  				        url: "getMctNomenList?"+key+"="+value,
			        	data: {mctMain:mctMain},
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
			        	alert("Please Enter Approved MCT Nomenclature.");
			        	$("#mct_main_id").text("");
		 	        	$("#make_id").val("");
		 	        	$("#make_id").val("");
			        	mctMainAuto.val("");	        	  
			        	mctMainAuto.focus();
			        	return false;	             
			    	}   	         
			    }, 
			    select: function( event, ui ) {
			      	var mct_nomen = ui.item.value;   	 
					
						$.post("getmakemastertransportMctNomenToNoList?"+key+"="+value, {
							mct_nomen:mct_nomen
						}).done(function(j) {
							  if(j != "")
					 	      {
					      		var length = j.length-1;
					      		var enc = j[length].substring(0,16);
					 	   		$("#mct_main_id").val(dec(enc,j[0]));					 	   			
									
										$.post("getmodeltmsmakeid?"+key+"="+value, {
											slotid :  dec(enc,j[0])
										}).done(function(data) {											
											 if(data.length != 0)
									 	      { 
								   		    	   var length = data.length-1;
									 	   			var enc = data[length][0].substring(0,16);
									   		   		var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
										     		for ( var i = 0; i < length; i++) {
										     			options += '<option value="' + dec(enc,data[i][0])+ '" >'+ dec(enc,data[i][1])+ '</option>';
										     		}	
										     		$("select#make_id").html(options); 
									 	      }
										}).fail(function(xhr, textStatus, errorThrown) {
										});
	   	
					 	       }
						}).fail(function(xhr, textStatus, errorThrown) {
						}); 
				} 	     
			});
		});
		
	    $('select#make_id').change(function() {
			if(this.value != "0"){
				var max;
				var m_s = $("input#mct_main_id").val();
				var m_k = $("select#make_id").val();
				
				
			 	   	$.post("gettmsmodelid?"+key+"="+value, {
			 	   	m_s:m_s, m_k:m_k
					}, function(j) {												
					}).done(function(j) {	
						var max1 = ('000' + j).substr(-3);
						$("#model_id").val(max1);
					}).fail(function(xhr, textStatus, errorThrown) {
					});				
			}else{
				$("#model_id").val("");
			}
		});
	});
	
</script>

<script>
	function isValid(){
		if($("input#mct_main_id").val() == "")
		{
			alert("Please Enter MCT Nomenclature.");
			$("select#mct_main_id").focus();
			return false;
		}   
	 	if($("select#make_id").val() == "0")
		{
			alert("Please Select MAKE Nomenclature.");
			$("select#make_id").focus();
			return false;
		}  
	 	if($("input#model_id").val() == "")
		{
			alert("Please Enter Model No.");
			$("input#model_id").focus();
			return false;
		}
	 	if($("input#description").val() == "")
		{
			alert("Please Enter MODEL Nomenclature.");
			$("input#description").focus();
			return false;
		} 
	 	
	 	return true;
	}
	function isNumberKey(evt) {
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