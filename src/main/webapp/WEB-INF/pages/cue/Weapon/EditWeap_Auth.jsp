<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>   
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Weapons Authorisation Modification</title>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/excluded/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 

<script type="text/javascript" src="js/miso/miso_js/jquery-1.12.3.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link rel="stylesheet" href="js/Calender/jquery-ui.css"/>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<script type="text/javascript">
$(document).ready(function() {
	$('body').toggleClass('open');
	$('.nav-item').toggle();
	$("div#divLogoutHidShow").hide();	
	document.getElementById('menuToggle').style.pointerEvents = 'none';
	$('#left-panel , #menuToggle').css("display","none");
	
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
</script>
<body>

<script>
$(document).ready(function() {
	 $('#remarks').keyup(function() {
	        this.value = this.value.toUpperCase();
	    });
	  $.ajaxSetup({
		    async: false
		});
	locfor1();
	incredecre1();
	$("select#scenario").val($("input#scenario_hid").val());
	 $.ajaxSetup({
		    async: false
		});
	  var wepetext=$("#modification");
	  wepetext.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
	        type: 'POST',
	        url: "getTypeofModiList?"+key+"="+value,
	        data: {label:document.getElementById('modification').value},
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
	        	alert("Please Enter Approved Modification");
	        	wepetext.val("");
	        	wepetext.focus();
	        	return false;	             
	          }   	         
	      }, 
	    });
	
	var x =document.getElementById("we_pe_no").value;
	getArmByWePeNo(x);
	var y = document.getElementById("item_seq_no").value;
	getbaseauth_type(y);
	  
		   var loc_code = document.getElementById("formation_code").value;
		   $.post("editgetFormationlist?"+key+"="+value, {loc_code:loc_code}).done(function(j) {
				$("#formation").val(j); 
		   }).fail(function(xhr, textStatus, errorThrown) { }); 

			$.ajaxSetup({
			    async: false
			});
			
			var unit_code = document.getElementById("unit_code").value;

			$.post("editgetUnitlist?"+key+"="+value, {unit_code:unit_code}).done(function(j) {
				$("#unit").val(j); 
			});

			$.ajaxSetup({
			    async: false
			});  
			var location_code = document.getElementById("location_code").value;

			$.post("getLocationByName?"+key+"="+value, {code:location_code}).done(function(j) {
 					$("#location").val(j); 
			});

			$.ajaxSetup({
			    async: false
			});
              $(function() {              		
            	  var wepetext1=$("#location");
            	  wepetext1.autocomplete({
            	      source: function( request, response ) {
            	        $.ajax({
            	        type: 'POST',
            	        url: "getLocation?"+key+"="+value,  
            	        data: {code_value : document.getElementById('location').value},
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
            	            var autoTextVal=wepetext1.val();
            				$.each(dataCountry1.toString().split("|"), function(i,e){
            					var newE = e.substring(0, autoTextVal.length);
            					if (newE.toLowerCase() === autoTextVal.toLowerCase()) {
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
            	        	  alert("Please Enter Approved Location");
            	        	  wepetext1.val("");    
            	        	  document.getElementById("location").value="";	
            	        	  document.getElementById("location").focus();
            	        	  document.getElementById("location_code").value="";
            	        	  wepetext1.focus();
            	        	  return false;	             
            	          }   	         
            	      }, 
            	      select: function( event, ui ) {
          	        	$(this).val(ui.item.value);    	        	
          	        	var code_value=ui.item.value;
          	        	$.post("getLocationByCode?"+key+"="+value, {code_value : code_value}).done(function(j) {
                			document.getElementById("location_code").value=j[0];
                	  }).fail(function(xhr, textStatus, errorThrown) { });	        	
          	        } 	      
            	    });
            	
            });

              $(function() {
              	 var wepetext1=$("#formation");
              
              	  wepetext1.autocomplete({
              	      source: function( request, response ) {
              	        $.ajax({
              	        type: 'POST',
              	        url: "getFormationUrl?"+key+"="+value, 
              	        data: {formation : document.getElementById('formation').value},
              	          success: function( data ) {
              	            //response( data );
              	           // var dataCountry1 = data.join("|");
              	           if(data.length > 1){
              	        	var susval = [];
              	        	var length = data.length-1;
         	        		 var enc = data[length].columnName1.substring(0,16);
                             for(var i = 0;i<data.length-1;i++){
                              susval.push(dec(enc,data[i].columnName));
       	        	  	}
              		        	var dataCountry1 = susval.join("|");
              	            var myResponse = []; 
              	            var autoTextVal=wepetext1.val();
              				$.each(dataCountry1.toString().split("|"), function(i,e){
              					var newE = e.substring(0, autoTextVal.length);
              					if (newE.toLowerCase() === autoTextVal.toLowerCase()) {
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
              	          } 
              	    	 else {
              	        	  alert("Please Enter Approved Formation");
              	        	  wepetext1.val("");
              				 wepetext1.focus();
              				document.getElementById("formation_code").value="";
              	        	  return false;	             
              	          }   	         
              	      }, 
              	         select: function( event, ui ) {
              	        	$(this).val(ui.item.value);    	        	
              	        	var unit_name=ui.item.value;
              	        	$.post("getUnitNameFromSusNo?"+key+"="+value, {unit_name : unit_name}).done(function(j) {
         	        			document.getElementById("formation_code").value=j[0];
         	        	 }).fail(function(xhr, textStatus, errorThrown) { });	        	
              	        }  	      
              	    });
                   
                   
                 });  
              
              
              $(function() {
              	 var wepetext1=$("#unit");
              
              	  wepetext1.autocomplete({
              	      source: function( request, response ) {
              	        $.ajax({
              	        type: 'POST',
              		    url: "getUnitUrl?"+key+"="+value,
              	        data: {unit : document.getElementById('unit').value},
              	          success: function( data ) {
              	            //response( data );
              	           // var dataCountry1 = data.join("|");
              	        	if(data.length > 1){
              	           var susval = [];
              	        	var length = data.length-1;
         	        		 var enc = data[length].columnName1.substring(0,16);
                             for(var i = 0;i<data.length-1;i++){
                              susval.push(dec(enc,data[i].columnName));
       	        	  	}
      			        	var dataCountry1 = susval.join("|");

              	           var myResponse = []; 
              	            var autoTextVal=wepetext1.val();
              				$.each(dataCountry1.toString().split("|"), function(i,e){
              					var newE = e.substring(0, autoTextVal.length);
              					if (newE.toLowerCase() === autoTextVal.toLowerCase()) {
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
              	          } 
              	    	 else {
              	        	  alert("Please Enter Approved Unit");
              	        	  wepetext1.val("");
              				 wepetext1.focus();
              				document.getElementById("unit_code").value="";
              	        	  return false;	             
              	          }   	         
              	      }, 
              	         select: function( event, ui ) {
              	        	$(this).val(ui.item.value);    	        	
              	        	var unit_name=ui.item.value;
              	        	$.post("getUnitNameFromSusNo?"+key+"="+value, {unit_name : unit_name}).done(function(j) {
        	        			document.getElementById("unit_code").value=j[0];
        	        	 }).fail(function(xhr, textStatus, errorThrown) { });	        	
              	        }  	      
              	    });
                });  
                 
   
	    var wepetext14=$("#item_type");    
	    
		  wepetext14.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
	        	type: 'POST',
	 	        url: "getitemtype?"+key+"="+value,
		        data: {item_type:document.getElementById('item_type').value},
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
		            var autoTextVal=wepetext14.val();
					$.each(dataCountry1.toString().split("|"), function(i,e){
						var newE = e.substring(0, autoTextVal.length);
						if (newE.toLowerCase() === autoTextVal.toLowerCase()) {
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
		        	  alert("Please Enter Approved Nomenclature");
		        	  wepetext14.val("");
		        	  document.getElementById("base_auth").value=0.00;
		  				$("input[type=radio][id=ct-radio2]").attr('disabled', true);
						document.getElementById("item_seq_no").value="";
		        	 
		        	  wepetext14.focus();
		        	  return false;	             
		          }   	         
		      }, 
		       select: function( event, ui ) {
		        	$(this).val(ui.item.value); 
		        	$.post("getitemcode?"+key+"="+value, {val : ui.item.value}).done(function(j) {	
	            		document.getElementById("item_seq_no").value=j;	
		        	  }).fail(function(xhr, textStatus, errorThrown) { });
		        	
		        	 $.ajaxSetup({
		     	  	    async: false
		     	  	}); 
		        	getbaseauth($(this).val());	        	
		        } 	     
		    });
		  
		  $.ajaxSetup({
		  	    async: false
		  	}); 
			 var wepetext2=$("#item_seq_no");  
			
			  wepetext2.autocomplete({
			      source: function( request, response ) {
			        $.ajax({
		        	type: 'POST',
		 	        url: "getItemCodeList?"+key+"="+value,
			        data: {item_code:document.getElementById('item_seq_no').value},
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
			            var autoTextVal=wepetext2.val();
						$.each(dataCountry1.toString().split("|"), function(i,e){
							var newE = e.substring(0, autoTextVal.length);
							if (newE.toLowerCase() === autoTextVal.toLowerCase()) {
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
			        	  alert("Please Enter Approved Item Code");
			        	  wepetext2.val("");
			        	  document.getElementById("item_type").value="";  
			        	  document.getElementById("base_auth").value=0.00;
			  				$("input[type=radio][id=ct-radio2]").attr('disabled', true);
			        	  wepetext2.focus();
			        	  return false;	             
			          }   	         
			      }, 
			        select: function( event, ui ) {
			        	$(this).val(ui.item.value);    	        	
			        	$.post("getitemnamefromcode?"+key+"="+value, {val : ui.item.value}).done(function(j) {	
		            		document.getElementById("item_type").value=j;	
			        	  }).fail(function(xhr, textStatus, errorThrown) { });  
			        	
			        	 $.ajaxSetup({
			     	  	    async: false
			     	  	}); 
			        	getbaseauth(document.getElementById("item_type").value);	 
			        }  	     
			    });
			  
			  document.getElementById("base_auth").value=parseFloat(document.getElementById("base_auth").value);
			  document.getElementById("amt_inc_dec").value=parseFloat(document.getElementById("amt_inc_dec").value);
   });
</script>
   <script>
   function isNumberKey(evt) {
   				if (charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57)) {
   					return false;
   				} else {
   					if (evt.target.value.search(/\./) > -1 && charCode == 46) {

   						return false;
   					}
   				}
   				return true;
   			}	
  function incredecre1(){
		var str = document.getElementById("amt_inc_dec_hid").value;
		var res = str.split("",1);
		
		   if(res == '-'){
			  
			   document.getElementById("ct-radio13").checked = true;
			  document.getElementById("amt_inc_dec").value = str.substr(1);
		 }  
		   else{
			   
			   document.getElementById("ct-radio12").checked = true;
			   document.getElementById("amt_inc_dec").value=str;
		   }
		} 
function getbaseauth(val){ 
	$.post("getitemcode?"+key+"="+value, {val : val}).done(function(j) {
	if(j!=null) 
	 {  
		document.getElementById("item_seq_no").value=j;				
		var item=document.getElementById("item_seq_no").value;
		var we_pe=document.getElementById("we_pe_no").value;
		$.post("getAutoWepen?"+key+"="+value, {we_pe:we_pe, item_code:item}).done(function(j) {	
			document.getElementById("base_auth").value=0.00;
			if(j != ""){
			if(j[0].auth_weapon == '0.00' || j[0].auth_weapon == '0' || j[0].auth_weapon == '' || j[0].auth_weapon== undefined)
	          { 
				document.getElementById("base_auth").value=0.00;
	          $("input[type=radio][id=ct-radio13]").attr('disabled', true);
	          }
			else
				{
				document.getElementById("base_auth").value=j[0].auth_weapon;
				 $("input[type=radio][id=ct-radio13]").attr('disabled', false);
				}
			}	    			
		 }).fail(function(xhr, textStatus, errorThrown) { })
	}
	 }).fail(function(xhr, textStatus, errorThrown) { })
}

function selectradiobase()
{
	var r =  $('input:radio[name=ct-radio1]:checked').val();	
	var base = document.getElementById("base_auth").value;
	 if(r == undefined)
	 {		 
		 alert("Please Select Increment/Decrement");
			return false;
	 }
	return true;
}

function getbaseauth_type(val){ 
	$.post("getitemtype_code?"+key+"="+value, {val : val}).done(function(j) {	
		  document.getElementById("item_type").value=j	;
		  getbaseauth(document.getElementById("item_type").value);
  	}).fail(function(xhr, textStatus, errorThrown) { }); 	
}

</script>
<form:form  action="Editweap_aut_modiAction" method='post' commandName="weap_aut_modiCMD" class="form-horizontal">

<div class="container" align="center">
	        	<div class="card">
	          		<div class="card-header"> <h5>Edit AUTHORISATION OF WEAPON UNDER MODIFICATION</h5></div>
	          			<div class="card-body card-block cue_text">
	          			<div class="col-md-12">	
								<div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-6">
	                  						<label class=" form-control-label">MISO WE/PE No</label>
	                					</div>
	                					   <div class="col-12 col-md-6">
	                					   <input type="hidden" id="id" name="id" class="form-control" value="${weap_aut_modiCMD.id}">
	                  						<input  id="we_pe_no" name="we_pe_no" maxlength="100" readonly="readonly"  class="form-control" autocomplete="off" onchange="getArmByWePeNo(this.value);" value="${weap_aut_modiCMD.we_pe_no}">
	            					         </div>      
	                		       </div>
	                        	</div>
	              					
 						<div class="col-md-6">
						<div class="row form-group">
	                	    <div class="col col-md-6">
	                  			<label for="text-input" class=" form-control-label">Table Title</label>
	                	    </div>
	                     	<div class="col-12 col-md-6">
		                 		<input id="table_title1" class="form-control" readonly="readonly">
							</div>
	  					</div>
                       </div>
	              </div> 
	              
	              <div class="col-md-12">	
	              	<div class="col-md-6">				
						<div class="row form-group">
							<div class="col col-md-6">
						         <label class=" form-control-label">Choose Scenario <strong style="color: red;">*</strong></label>
						    </div>
						    <div class="col-12 col-md-6">
	                       		<input type="hidden" id="scenario_hid" name="scenario_hid" class="form-control"  value="${weap_aut_modiCMD.scenario}" >
                           		<select name="scenario" id="scenario" class="form-control" >
				                    <option value="">--Select--</option>
				                    <option value="Location">Location</option>
					                <option value="Formation">Formation</option>
					                <option value="Unit">Unit</option>
					                <option value="Others">Others</option>
				               	</select> 
   							 </div>   
						 </div>
				    </div>
					 <div class="col-md-6">
		              		<div class="row form-group" id="getloc" style="display:none;">
	                					<div class="col col-md-6" >
	                  						<label class=" form-control-label">Location <strong style="color: red;">*</strong> </label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                  						<input type="hidden" id="location_code" name="location" value="${weap_aut_modiCMD.location}" >
	                						<input type="text" id="location" name="location_name" maxlength="5" class="form-control" autocomplete="off" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search">
	                					
	                					</div>
	              					</div>  
	              					<div class="row form-group" id="getform" style="display:none;" >
	                					<div class="col col-md-6">
	                  						<label class=" form-control-label">Formation <strong style="color: red;">*</strong> </label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                  						<input type="hidden" id="formation_code" name="formation" value="${weap_aut_modiCMD.formation}" >
	                						<input type="text" id="formation" name="formation_name" maxlength="8" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search"  class="form-control" autocomplete="off">
	                					</div>
	              					</div> 
	              					<div class="row form-group" id="getunit" style="display:none;" >
                					<div class="col col-md-6">
                  						<label class=" form-control-label">Unit <strong style="color: red;">*</strong> </label>
                					</div>
                					<div class="col-12 col-md-6">
	                					<input type="hidden" id="unit_code" name="scenario_unit"  class="form-control" value="${weap_aut_modiCMD.scenario_unit}">
	                					<input type="text" id="unit" name="scenario_unit_name" maxlength="8" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search"  class="form-control" autocomplete="off">
	                				</div>
              					</div>  
	              				</div>
							</div>	
				<div class="col-md-12"><span class="line"></span></div>
	 
                 <div class="col-md-12">
					 <div class="col-md-6">
	              		 <div class="row form-group">
	                		 <div class="col col-md-6">
	                  		<label class=" form-control-label">Modification <strong style="color: red;">*</strong></label>
	                		</div>
	                		<div class="col-12 col-md-6">
	                  		<input  id="modification" name="modification" maxlength="15" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search"  autocomplete="off" class="form-control" value="${weap_aut_modiCMD.modification}">
	                	  
	                		</div>
	                	</div>
	              	</div>
 				</div>
 				 <div class="col-md-12">	
					<div class="col-md-6">
    					<div class="row form-group">
	      					<div class="col col-md-6">
	       						<label class=" form-control-label">Nomenclature <strong style="color: red;">*</strong></label>
	      					</div>
	      					<div class="col-12 col-md-6">
	       						<input class="form-control" id="item_type" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search"  autocomplete="off">
	      					</div>
      					</div>
   					</div>
   					<div class="col-md-6">
        			 	<div class="row form-group"> 
          					<div class="col col-md-6">
           						<label  class=" form-control-label" >Item Code <strong style="color: red;">*</strong></label>
          					</div>
          					<div class="col-12 col-md-6">
           						<input id="item_seq_no" name="item_seq_no" class="form-control" maxlength="8" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search" value="${weap_aut_modiCMD.item_seq_no}">
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
           						<input  id="base_auth" name="base_auth" readonly="readonly" class="form-control">
         					</div>
          					</div>
       					</div> 
       					<div class="col-md-6">
							<div class="row form-group">
               					<div class="col col-md-6">
                 						<label for="text-input" class=" form-control-label">Increment/Decrement <strong style="color: red;">*</strong></label>
               					</div>
			                 	<div class="col-12 col-md-6">
			                      	<div class="form-check-inline form-check">
			                            <label for="ct-radio12" class="form-check-label ">
			                             <input type="radio" id="ct-radio12" name="ct-radio1" value="Increase" class="form-check-input">Increase
			                             </label>&nbsp;&nbsp;&nbsp;
			                              <label for="ct-radio13" class="form-check-label ">
			                             <input type="radio" id="ct-radio13" name="ct-radio1" value="Decrease" class="form-check-input">Decrease
			                              </label>&nbsp;&nbsp;&nbsp;
			                    	</div>
								</div>
 							</div>
						</div>
 					</div>
				<div class="col-md-12">
			 		<div class="col-md-6">
						<div class="row form-group">
					 	<div class="col col-md-6">
							<label for="text-input" class=" form-control-label">Amount of Increment/Decrement <strong style="color: red;">*</strong></label>
       					</div>
       					<div class="col-12 col-md-6">
       						<input type="hidden" id="amt_inc_dec_hid" name="amt_inc_dec_hid" class="form-control" value="${weap_aut_modiCMD.amt_inc_dec}">
       						<input  id="amt_inc_dec" name="amt_inc_dec" onkeypress="return isNumberKey(event, this);" maxlength="8"
       						 onkeyup="selectradiobase();" onchange="return checkAmt_inc_decLength();" class="form-control">
						</div>
					</div>
   				</div>             				
					<div class="col-md-6">
					       <div class="row form-group">
							  <div class="col col-md-6">
							      <label class=" form-control-label">Remarks</label>
							  </div>
							  <div class="col-12 col-md-6">
									<textarea class="form-control char-counter1" id="remarks" name="remarks" maxlength="255">${weap_aut_modiCMD.remarks}</textarea>
							  </div>
							</div>
						</div>         				 
                         </div>	
					  </div>
					  </div>
					 
 					<div class="card-footer" align="center">
						<input type="submit" class="btn btn-primary btn-sm" value="Update" onclick="return isvalidation();">
	              		<a href="search_Weap_Auth" class="btn btn-danger btn-sm" onClick="javascript:window.close('','_parent','');">  Cancel </a>
		            </div> 
			</div>			              				

</form:form>
<c:if test="${not empty msg}">
	<input type="hidden" name="msg" id="msg" value="${msg}" disabled="disabled"/>
</c:if>
<c:url value="updateWeapnmod1Url" var="updateUrl" />
	<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid">
		<input type="hidden" name="updateid" id="updateid" value="0"/>
	</form:form> 
<script>

function checkAmt_inc_decLength() {
	var amt_inc_dec= $("input#amt_inc_dec").val();
	var r =  $('input:radio[name=ct-radio1]:checked').val();	
	var lenval=0;
	 if(r == "Increase")
		 lenval = 8;
	 else if(r == "Decrease")
		 lenval = 9;
	 
	 if(amt_inc_dec.length > lenval) {
		alert("Please Enter Valid Digit");
		$("input#amt_inc_dec").val("");	
		return false;
	 }
	 else {
		 if(amt_inc_dec != "" && amt_inc_dec.includes(".")) {
				var amt_inc_dec1 = amt_inc_dec.toString().split(".");			
			 	if(amt_inc_dec1[0].length > 5 || amt_inc_dec1[1].length > 2 )
				{
			 		alert("Please Enter Valid Digit");
					$("input#amt_inc_dec").val("");
			 		return false;
				}
			 	
			 }
			else if(amt_inc_dec != "" && amt_inc_dec.includes(".") && amt_inc_dec.includes("-")) {
				var amt_inc_dec1 = amt_inc_dec.toString().split("-")[1].split(".");	
				if(amt_inc_dec1[0].length > 5 || amt_inc_dec1[1].length > 2) 
				{
					alert("Please Enter Valid Digit");
					$("input#amt_inc_dec").val("");
			 		return false;
				}
			}
			else {
				if(auth_weapon.length > 5)
				{
					alert("Please Enter Valid Digit");
					$("input#amt_inc_dec").val("");
			 		return false;
					
				}
			}
		 	return true;
		}	 
}

function setfun()
{
	if($("#amt_inc_dec").val() == ""){
		$("#amt_inc_dec").val(0);
    }
}

function locfor1(){
	$("#location").val("");
	$("#formation").val("");
	$("#unit").val("");
	var loc = document.getElementById("scenario_hid").value;
	   if(loc == "Location")
	   { 
		   $("label#labelcon").text("Location");
		   $("div#getloc").show();
	       $("div#getform").hide();
	       $("div#getunit").hide();
	   	   $("input#formation").val("");
	   	   $("input#unit").val("");
	   }
	    else if(loc=="Formation"){
	    	
	     $("label#labelcon").text("Formation");
		   $("div#getform").show();
		   $("div#getloc").hide();
		   $("div#getunit").hide();
		   $("input#location").val("");
	   	   $("input#unit").val("");
	   } 
	    else if(loc=="Unit"){
		     $("label#labelcon").text("Unit");
			   $("div#getunit").show();
			   $("div#getform").hide();
			   $("div#getloc").hide();
			   $("#location").val("");
			   $("#formation").val("");
		   } 
	   else
	   {
		   $("label#labelcon").text("Others");
		   $("div#getloc").hide();
		   $("div#getform").hide();
		   $("div#getunit").hide();
		   $("input#formation").val("");
	   	   $("input#unit").val("");
	   	   $("#location").val("");
	    }
}

   
function getArmByWePeNo(val)
{ 
	  if(val != "" && val != null)
	  {   
			$.post("getDetailsByWePeCondiNo?"+key+"="+value, {val : val}).done(function(j) { 
			if(j!=null)
				document.getElementById("table_title1").value=j[0].table_title;
			else
				document.getElementById("table_title1").value="";
			  }).fail(function(xhr, textStatus, errorThrown) { });
	  }
	  else
	  {
		document.getElementById("table_title1").value="";
	  } 
}
</script>	

<script>
function isvalidation(){	  
	setfun();
	if($("select#scenario").val()==""){
		alert("Please Select Scenario");
		$("select#scenario").focus();
		return false;
	     } 
	  var f = document.getElementById("scenario").value;
	  if(f == "Formation"){
	 	 if($("input#formation").val()=="" ){
	 		 alert("Please Enter Formation");
	 			$("input#formation").focus();
	 			return false;
	 	    }
	  }
	 if(f == "Location"){
	 	if($("input#location").val()=="" ){
	 		alert("Please Enter Location");
	 		   $("input#location").focus();
	 		    return false;
	 	      } 
	 }
	 if(f == "Unit"){
		 if($("input#unit").val()=="" ){
			 alert("Please Enter Unit");
				$("input#unit").focus();
				return false;
			} 
	}
	 if($("input#modification").val()==""){
			alert("Please Enter Modification")
			$("input#modification").focus();
			return false;
		}
	 if($("input#item_type").val()==""){
			alert("Please Enter Nomenclature")
			$("input#item_type").focus();
			return false;
		}
	 	if($("input#item_seq_no").val()==""){
			alert("Please Enter Item Code")
			$("input#item_seq_no").focus();
			return false;
		}
	var r =  $('input:radio[name=ct-radio1]:checked').val();	
	  if(r == undefined)
	 {		 
		 alert("Please Select Increment/Decrement");
			return false;
	 }
	var amt ;
	var base;	
	if($("input#amt_inc_dec").val()==""){
		alert("Please Enter Amt Incr/Decr")
		$("input#amt_inc_dec").focus();
		return false;
	}
	
	
	 else
	  {		 
	 var r_s =  $('input:radio[name=ct-radio1]:checked').val();	
    	amt = $("input#amt_inc_dec").val();  
	 	base = document.getElementById("base_auth").value;	

	 	 if(r_s == "Increase")
		 {
			
			if ($("input#amt_inc_dec").val().includes("-"))
			 {
				 alert("Please Remove Dec Value of Amt Inc/Dec");
				 $("input#amt_inc_dec").focus();
				 return false;
			 }
		 }
		 
	 	
		 if(r_s == "Decrease")
			 {
			
			 base = parseFloat(base);
			 amt = parseFloat(amt);
			if (base < amt){
				 
				 alert("Please Check Amount of Inc/Dec");
				 $("input#amt_inc_dec").focus();
				 return false;
			 }
			 else
				 return true;
			 }	
	  }	
	 return true; 
}
</script>

<script>
$("#scenario").change(function(){
  	$("#location").val("");
  	$("#formation").val("");
  	$("#unit").val("");
  	
	if($(this).val()=="Location")
	   {    
		   $("label#labelcon").text("Location");
	       $("div#getloc").show();
	       $("div#getform").hide();
	       $("div#getunit").hide();
	       $("#formation").val("");
		   $("#unit").val("");
		   $("#unit_code").val("");
		   $("#formation_code").val("");
		   
		   
	   }
	   else if($(this).val()=="Formation"){
		   $("label#labelcon").text("Formation");
		   $("div#getform").show();
		   $("div#getloc").hide();
		   $("div#getunit").hide();
		   $("#location").val("");
		   $("#unit").val("");
		   $("#unit_code").val("");
		   $("#location_code").val("");
	   }
	   else if($(this).val()=="Unit"){
		   $("label#labelcon").text("Unit");
		   $("div#getunit").show();
		   $("div#getform").hide();
		   $("div#getloc").hide();
		   $("#location").val("");
		   $("#formation").val("");
		   $("#formation_code").val("");
		   $("#location_code").val("");
	   }
	   else
	   {
		   $("label#labelcon").text("Others");
		   $("div#getloc").hide();
		   $("div#getform").hide();
		   $("div#getunit").hide();
		   $("#location").val("");
		   $("#formation").val("");
		   $("#unit").val("");
		   $("#unit_code").val("");
		   $("#formation_code").val("");
		   $("#location_code").val("");
		   
	    }
  	});
</script>	
	

</body>
</html>
