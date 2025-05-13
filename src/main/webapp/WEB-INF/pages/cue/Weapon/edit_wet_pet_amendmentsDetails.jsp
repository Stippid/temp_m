<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<script type="text/javascript" src="js/AES_ENC_DEC/lib/excluded/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 

<script type="text/javascript" src="js/miso/miso_js/jquery-1.12.3.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<script type="text/javascript">
$(document).ready(function() {
	$('body').toggleClass('open');
	$('.nav-item').toggle();
	$("div#divLogoutHidShow").hide();	
	document.getElementById('menuToggle').style.pointerEvents = 'none';
	$('#left-panel , #menuToggle').css("display","none");
	
});
</script>

<form:form name="editWetPetAmendmentsFrom" id="editWetPetAmendmentsFrom" action="WetPetAmendmentsEditAct" method='POST' commandName="editWetPetAmendmentsCmd" >
	    <div class="container" align="center">
        	<div class="card">
          		<div class="card-header"><h5>Edit WET/PET CONDITIONS AMENDMENT</h5></div>        		
          		  <div class="card-body card-block cue_text">
           			<div class="col-md-12">	            					
             				<div class="col-md-6">
             				  <div class="row form-group">
               					<div class="col col-md-4">
               						<input type="hidden" id="id" name="id" placeholder="" class="form-control" value="${editWetPetAmendmentsCmd[0][0]}">
				                	<label for="text-input" class=" form-control-label">Type of Document <strong style="color: red;">*</strong></label>
				                </div>
               					<div class="col-12 col-md-8">
		                             <div class="form-check-inline form-check">
		                             <input type="hidden" id="wet_petHid" value="${editWetPetAmendmentsCmd[0][1]}">
		                                <label for="inline-radio1" class="form-check-label ">
		                                  <input type="radio" id="wet1" name="radio" value="WET" class="form-check-input" disabled="disabled">WET
		                                </label>&nbsp;&nbsp;&nbsp;
		                                <label for="inline-radio2" class="form-check-label ">
		                                  <input type="radio" id="pet1" name="radio" value="PET" class="form-check-input" disabled="disabled">PET
		                                </label>&nbsp;&nbsp;&nbsp;
		                                <label for="inline-radio2" class="form-check-label ">
		                                  <input type="radio" id="fet1" name="radio" value="FET" class="form-check-input" disabled="disabled">FET
		                                </label>&nbsp;&nbsp;&nbsp;       
		                             </div>
					              </div>							              
               				</div>
               			</div>               			
               			<div class="col-md-6">
             				<div class="row form-group">
               					<div class="col col-md-4">
                 					<label class=" form-control-label">WET/PET/FET Document No <strong style="color: red;">*</strong></label>
               					</div>
               					<div class="col-12 col-md-6">
                 					<input name="wet_pet_no"  id="wet_pet_no" class="form-control" autocomplete="off" value="${editWetPetAmendmentsCmd[0][2]}" readonly="readonly">
               					</div>
               				</div>
             			</div>
             					
           			</div>
           			<div class="col-md-12">	
           			 <div class="col-md-6">
              					<div class="row form-group">
                					<div class="col col-md-4">
                  						<label class=" form-control-label">Table Title</label>
                					</div>
                					<div class="col-12 col-md-6">
                  						<input type="text" id="amdt_title_wet_pet" name="" class="form-control" readonly="readonly">
                					</div>
              					</div>
							</div>
  				</div>
  				
           			
           			 <div class="col-md-12"><span class="line"></span></div>
  	
  					<div class="col-md-12">	
  						<div class="col-md-6">
  							<div class="row form-group">
				                <div class="col col-md-4">
				                  	<label class=" form-control-label">Item/Eqpt Name <strong style="color: red;">*</strong></label>
				                </div>
				                <div class="col-12 col-md-6">
				                <input value="${editWetPetAmendmentsCmd[0][5]}" class="form-control" id="item_name"  onchange="getItemCode(this.value);" autocomplete="off"  style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search" > 
				                	<input type="hidden" id="item_seq_no" name="item_seq_no" maxlength="8" value="${editWetPetAmendmentsCmd[0][6]}">
				                </div>
				            </div>	  								
  						</div>
  						
  						<div class="col-md-6">
	              			 	<div class="row form-group"> 
	                					<div class="col col-md-4">
	                  						<label  class=" form-control-label" >CES/CCES No</label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                  						<input id="ces_cces_no" name="" class="form-control" readonly="readonly">
	                  						
										</div>
	  								</div> 
	  						</div>
  						
  						
  					</div> 
  					<div class="col-md-12">
  						
             			<div class="col-md-6">
							<div class="row form-group">           					
             					<div class="col col-md-4">
               						<label class=" form-control-label">Authorisation <strong style="color: red;">*</strong></label>
             					</div>
             					<div class="col-12 col-md-6">
               						<input value="${editWetPetAmendmentsCmd[0][7]}" id="authorization" name="authorization" class="form-control" maxlength="8" onkeypress="return isNumberKey(event,this);" onchange="return checkAuth_amtLength();">
             					</div>
             				</div>	
             			</div>
             			
             			<div class="col-md-6">
							<div class="row form-group">           					
             					<div class="col col-md-4">
               						<label class=" form-control-label">Remarks</label> 
             					</div>
             					<div class="col-12 col-md-6">
               						<textarea rows="2" id="remarks" name="remarks" maxlength="255" class="form-control char-counter1">${editWetPetAmendmentsCmd[0][8]}</textarea>
             					</div>
             				</div>	
             			</div>
             			
  					</div> 		
  		
  				</div>
  		
  				<div class="card-footer" align="center">
						<input onclick="return isValid();" type="submit" class="btn btn-primary btn-sm" value="Update" >
             			<a href="searchWetPetAmendmentsDetails" class="btn btn-danger btn-sm" onClick="javascript:window.close('','_parent','');">  Cancel </a>
            	</div> 
 								
  			</div>
		 </div>
</form:form>	

<script>
$(document).ready(function() {
	$('#remarks').keyup(function() {
        this.value = this.value.toUpperCase();
    });
	if(document.getElementById("wet_pet_no").value !="" && document.getElementById("wet_pet_no").value !=null)
		getarmvalue(document.getElementById("wet_pet_no").value);
	
	if(document.getElementById("wet_petHid").value != "" || document.getElementById("wet_petHid").value != null || document.getElementById("wet_petHid").value != undefined)
	{
		var radioButtons = document.getElementsByName("radio");

	    if (radioButtons != null) {
	        for (var radioCount = 0; radioCount < radioButtons.length; radioCount++) {
	            if (radioButtons[radioCount].value == document.getElementById("wet_petHid").value) {
	                radioButtons[radioCount].checked = true;
	                getWePeNoByType(document.getElementById("wet_petHid").value);
	            }
	        }
	    }
	}
	
	
	if(document.getElementById("item_seq_no").value != "" ){		
		 $.post("getCesnoByCesName?"+key+"="+value, {val :  document.getElementById("item_seq_no").value}).done(function(j) {
   	        if(j[0] == undefined || j[0] == "" || j[0] == null || j[0] == '' ){
       			document.getElementById("ces_cces_no").value="";
       			}   	        			
       		else
		 		document.getElementById("ces_cces_no").value=j[0].ces_cces_no;	
		 }).fail(function(xhr, textStatus, errorThrown) { });  
	}     		
        
	
	
	$('input[type=radio][name=radio]').change(function() {		
		var radio_doc = $(this).val();
		getWePeNoByType(radio_doc);	
	});
	
	
  
});	

function checkAuth_amtLength() {
	var auth_weapon= $("input#authorization").val();
	
 	if(auth_weapon.length > 8)
	{
		alert("Please Enter Valid Digit");
		$("input#auth_weapon").val("");
 		return false;
		
	}
	if(auth_weapon != "" && auth_weapon.includes(".")) {
		//var scale_vald1=[] ;
		var auth_weapon1 = auth_weapon.toString().split(".");			
	 	if(auth_weapon1[0].length > 5 || auth_weapon1[1].length > 2 )
		{
	 		alert("Please Enter Valid Digit");
			$("input#auth_weapon").val("");
	 		return false;
		}
	 	
	 }
	else {
		if(auth_weapon.length > 5)
		{
			alert("Please Enter Valid Digit");
			$("input#auth_weapon").val("");
	 		return false;
			
		}
	}
 	return true;
}

function getarmvalue(val){
	
	if(val != "" && val != null)
	  {
		$.post("getWetPetFetByUnit_type?"+key+"="+value, {val : val}).done(function(j) {if(j!="" && j!= null)
			 	document.getElementById("amdt_title_wet_pet").value=j[0].unit_type;
			else
				document.getElementById("amdt_title_wet_pet").value="";
		}).fail(function(xhr, textStatus, errorThrown) { }); 	
	  }
	else
	{
		document.getElementById("amdt_title_wet_pet").value="";
	}
}

function getWePeNoByType(radio_doc)
{
	 if(radio_doc != ""){
	 var wepetext=$("#wet_pet_no");
	
	  wepetext.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
	        type: 'POST',
	  	    url: "getWetPetFetNo?"+key+"="+value,
	        data: {val : radio_doc,wet_pet_no:document.getElementById('wet_pet_no').value},
	          success: function( data ) {
	            //response( data );
	            //var dataCountry1 = data.join("|");
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
					if (newE.toLowerCase() === autoTextVal.toLowerCase()) {
					//if (e.toLowerCase().includes(autoTextVal.toLowerCase())) {
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
	        	alert("Please Enter Approved WET/PET/FET No");
	        	wepetext.val("");
	        	wepetext.focus();
	        	document.getElementById("amdt_title_wet_pet").value="";
	        	return false;	             
	          }   	         
	      } ,
	      select: function( event, ui ) {
		   		$(this).val(ui.item.value);    	        	
		   		getarmvalue($(this).val());	        	
		    } 
      	     
	    });
	 }
	 else
		 alert("Please select Document");	
}

$(function() {
	var wepetext=$("#item_name");
	
	  wepetext.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
        	type: 'POST',
 	        url: "getitemtype?"+key+"="+value,
	        data: {item_type:document.getElementById('item_name').value},
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
					if (newE.toLowerCase() === autoTextVal.toLowerCase()) {
					//if (e.toLowerCase().includes(autoTextVal.toLowerCase())) {
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
	        	alert("Please Enter Approved Item/Eqpt Name");
	        	document.getElementById("item_seq_no").value="";
	        	wepetext.val("");	
	        	wepetext.focus();
	        	return false;	             
	          }   	         
	      }, 
	    select: function( event, ui ) {
	   		$(this).val(ui.item.value);    	        	
	   		getItemCode($(this).val());	        	
	    } 	     
	    });
    
  });
  
function getItemCode(val)
{
	  document.getElementById("item_seq_no").value="";  

	  if(val != "" && val != null && val !="null")
	  {
		  
		  $.post("getitemcode?"+key+"="+value, {val : val}).done(function(j) {
		  
          document.getElementById("item_seq_no").value=j[0];
          $.post("getCesnoByCesName?"+key+"="+value, {val :  j[0]}).done(function(j) {
        	        		if(j[0] == undefined || j[0] == "" || j[0] == null || j[0] == '' ){
        	        			document.getElementById("ces_cces_no").value="";
        	        		}   	        			
        	        		else
        	 		 		document.getElementById("ces_cces_no").value=j[0].ces_cces_no;		
     	  }).fail(function(xhr, textStatus, errorThrown) { }); 	
		  }).fail(function(xhr, textStatus, errorThrown) { }); 	
	  }
	  else
	  {
		  alert("Please enter Item/Eqpt Name");
		  document.getElementById("item_seq_no").value="";
	  }
}
  
  function isValid()
  {  	  
  	  var r =  $('input:radio[name=radio]:checked').val();	
	  if(r == undefined)
	  {		 
		    alert("Please select Document");
		    $('input:radio[name=radio]:checked').focus();
			return false;
	  }  	  
  	
	  	if($("input#wet_pet_no").val() == "")
		{
			alert("Please enter WET/PET/FET Document No");
			$("input#wet_pet_no").focus();
			return false;
		}
		if($("input#item_name").val() == "")
		{
			alert("Please enter Item/Eqpt Name");
			$("input#item_name").focus();
			return false;
		}
		
		if($("input#authorization").val() == "")
		{
			alert("Please enter Authorisation");
			$("input#authorization ").focus();
			return false;
		}
		
		return true;
  }
  
//////////////////only numeric and point(.)
  function isNumberKey(evt) {
  	var charCode = (evt.which) ? evt.which : evt.keyCode;
  	if (charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57)) {
  		return false;
  	} else {
  		if (evt.target.value.search(/\./) > -1 && charCode == 46) {
  			return false;
  		}
  	}
  	return true;
  }
</script>
