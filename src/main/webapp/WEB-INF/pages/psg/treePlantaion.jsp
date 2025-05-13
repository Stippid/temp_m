 <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<!-- <script src="js/Calender/jquery-2.2.3.min.js"></script> -->
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script> 



<form:form name="prop_comm_letter" id="prop_comm_letter" action="treeplantaionaction?${_csrf.parameterName}=${_csrf.token}" method="post" class="form-horizontal" commandName="treeplantaion_cmd" enctype="multipart/form-data"> 
	<div class="animated fadeIn">
	    	<div class="container" align="center">
	    		<div class="card">
	    		<div class="card-header"><h5>Tree Plantation</h5> <h6 class="enter_by"><span style="font-size:18px;color:red;">All Fields Mandatory</span></h6><h6 class="enter_by"><!-- <span style="font-size:12px;color:red;">(To be filled by PCTA only)</span> --></h6></div>
	          			<div class="card-body card-block">
	          					
	              			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;"> </strong> Unit SUS No </label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="unit_sus_no" name="unit_sus_no" class="form-control autocomplete" autocomplete="off" maxlength="8" onkeypress="return AvoidSpace(event)"  onkeyup="return specialcharecter(this)" readonly > 
						                </div>
						            </div>
	              				</div>
	              				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;"> </strong> Unit Name </label>
						                </div>
						                <div class="col-md-8">
						                  <input type="text" id="unit_posted_to" name="unit_posted_to" class="form-control autocomplete" autocomplete="off" maxlength="50" onkeyup="return specialcharecter(this)" readonly>
						                </div>
						            </div>
	              				</div>
	              			</div>
	            			<div class="col-md-12">
	            					<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;"> </strong>  Date</label>
						                </div>
						                <div class="col-md-8">
						                <input type="hidden" id="id" name="id"
										class="form-control autocomplete" value="0" autocomplete="off">
										<input type="hidden" id="id1" name="id1"
										class="form-control autocomplete" value="0" autocomplete="off">
						                <input type="text" name="date_of_authority" id="date_of_authority" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" required/>
						                </div>
						            </div>
	              				</div>	
	            				<!-- <div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;"> </strong>Remarks</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="authority" name="authority" class="form-control autocomplete" autocomplete="off" maxlength="100" onkeyup="return specialcharecter(this)"> 
						                </div>
						            </div>
	              				</div> -->
	              				              					
	              		
	              			</div>    
	  
	            				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">Tree Name</label>
						                </div>
						                <div class="col-md-8">
						                  <label class=" form-control-label">Quantity</label> 
						                </div>
						            </div>
	              				</div>
	              				              					
	              		
	              		
<div class="col-md-12">	
	            				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">Fruit</label>
						                </div>
						                <div class="col-md-4">
						                   <input type="text" id="tree1" name="tree1" class="form-control autocomplete" autocomplete="off"  maxlength="100" oninput="this.value = this.value.replace(/[^0-9]/g, '');" required/> 
						                </div>
						            </div>
	              				</div>
	              				              					
	              		
	              			</div> 
	              			<div class="col-md-12">	
	            				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">Shade</label>
						                </div>
						                <div class="col-md-4">
						                   <input type="text" id="tree2" name="tree2" class="form-control autocomplete" autocomplete="off"  maxlength="100" oninput="this.value = this.value.replace(/[^0-9]/g, '');" required/> 
						                </div>
						            </div>
	              				</div>
	              				              					
	              		
	              			</div> 
	              			<div class="col-md-12">	
	            				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">Medicinal</label>
						                </div>
						                <div class="col-md-4">
						                   <input type="text" id="tree3" name="tree3" class="form-control autocomplete" autocomplete="off" maxlength="100"  oninput="this.value = this.value.replace(/[^0-9]/g, '');" required/> 
						                </div>
						            </div>
	              				</div>
	              				              					
	              		
	              			</div> 
	              		<div class="col-md-12">	
    <div class="col-md-6">
        <div class="row form-group">
            <div class="col-md-4">
                <label class="form-control-label">Misc</label>
            </div>
            <div class="col-md-4">
                <input type="text" id="tree4" name="tree4" class="form-control autocomplete" autocomplete="off" maxlength="100" oninput="this.value = this.value.replace(/[^0-9]/g, '');" required/>
            </div>
            
            
        </div>
    </div>
    
    
</div>

              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <c:if test="${roleAccess == 'Unit'}">
								Upload Image : <span style="color: red;">(*.zip, *.rar
									only, Max size 5 MB)</span>
							</c:if>
						                    <!-- <label class=" form-control-label"><strong style="color: red;"> </strong> Upload </label> -->
						                </div>
						                
						                <div class="col-md-8">
						                   <input type="file" id="uploadtree" name="uploadtree"
											title="TREE Upload .ZIP ,.RAR only " required/> 
						                </div>
						            </div>
	              	</div>

     
	              			
	              				
            			</div>

					<div class="card-footer" align="center" class="form-control">
								<a href="treeplantaion" class="btn btn-success btn-sm" >Clear</a> 
			              		<input type="submit" class="btn btn-primary btn-sm" value="Save" id="btnSubmit" onclick=" return validate();" ><!---->
				    </div> 		
	        	</div>
			</div>
	</div>
</form:form>

  <script>
  
  var roleAccess;
  $(document).ready(function() {
       roleAccess = '${roleAccess}';
       sus_no='${roleSusNo}';
       $('#unit_sus_no').val('${roleSusNo}');
       
    
			 $.post("getTargetUnitNameList?"+key+"="+value, {
				 sus_no:sus_no
        }, function(j) {
               
        }).done(function(j) {
       	 var length = j.length-1;
            var enc = j[length].substring(0,16);
            $("#unit_posted_to").val(dec(enc,j[0]));   
                
        }).fail(function(xhr, textStatus, errorThrown) {
        });
		
			 var unit_name = jQuery("#unit_name").val();
				${con}
				if('${roleAccess}' == 'Unit'){
					var file = document.getElementById('uploadtree');
					file.onchange = function(e) {
					  	var ext = this.value.match(/\.([^\.]+)$/)[1];
					  	var FileSize = file.files[0].size /1024/1024/1024/1024/1024; // in MB
					  	if (FileSize > 5) {
				           alert('File size exceeds 5 MB');
				           this.value = '';
				        } else {
				        	switch (ext) {
							  	case 'rar':
							  	case 'zip':
							 	//alert('Allowed');
							    break;
							  	default:
							    	alert('Please Only Allowed *.zip , *.rar File Extensions.');
							   	this.value = '';
							}
				        }
					};
				}
			 
  
  });
	
  
  
  
	 jQuery(function($){ 
		
		 datepicketDate('date_of_authority');            
		 
   });

	
	// Unit SUS No

	$("#unit_sus_no").keyup(function(){
		var sus_no = this.value;
		var susNoAuto=$("#unit_sus_no");

		susNoAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        type: 'POST',
		        url: "getTargetSUSNoList?"+key+"="+value,
		        data: {sus_no:sus_no},
		          success: function( data ) {
		        	  var susval = [];
	                  var length = data.length-1;
	                  var enc = data[length].substring(0,16);
	                        for(var i = 0;i<data.length;i++){
	                                susval.push(dec(enc,data[i]));
	                        }
	                        var dataCountry1 = susval.join("|");
	                        var myResponse = []; 
					        var autoTextVal=susNoAuto.val();
					$.each(dataCountry1.toString().split("|"), function(i,e){
						var newE = e.substring(0, autoTextVal.length);
						if (e.toLowerCase().includes(autoTextVal.toLowerCase())) {
						  myResponse.push(e);
						}
					});      	          
					response( myResponse ); 
		          }
		        });
		      },
			  minLength: 1,
		      autoFill: true,
		      change: function(event, ui) {
		    	 if (ui.item) {   	        	  
		        	  return true;    	            
		          } else {
		        	  alert("Please Enter Approved Unit SUS No.");
		        	  document.getElementById("unit_name").value="";
		        	  susNoAuto.val("");	        	  
		        	  susNoAuto.focus();
		        	  return false;	             
		          }   	         
		    }, 
			select: function( event, ui ) {
				var sus_no = ui.item.value;			      	
				 $.post("getTargetUnitNameList?"+key+"="+value, {
					 sus_no:sus_no
	         }, function(j) {
	                
	         }).done(function(j) {
	        	 var length = j.length-1;
	             var enc = j[length].substring(0,16);
	             $("#unit_posted_to").val(dec(enc,j[0]));   
	                 
	         }).fail(function(xhr, textStatus, errorThrown) {
	         });
			} 	     
		});	
	});
	
	// unit name
	 $("input#unit_posted_to").keypress(function(){
			var unit_name = this.value;
				 var susNoAuto=$("#unit_posted_to");
				  susNoAuto.autocomplete({
				      source: function( request, response ) {
				        $.ajax({
				        	type: 'POST',
					    	url: "getTargetUnitsNameActiveList?"+key+"="+value,
				        data: {unit_name:unit_name},
				          success: function( data ) {
				        	 var susval = [];
				        	  var length = data.length-1;
				        	  var enc = data[length].substring(0,16);
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
				        	  alert("Please Enter Approved Unit Name.");
				        	  document.getElementById("unit_posted_to").value="";
				        	  susNoAuto.val("");	        	  
				        	  susNoAuto.focus();
				        	  return false;	             
				          }   	         
				      }, 
				      select: function( event, ui ) {
				    	 var unit_name = ui.item.value;
				    
					            $.post("getTargetSUSFromUNITNAME?"+key+"="+value,{target_unit_name:unit_name}, function(data) {
					            }).done(function(data) {
					            	var length = data.length-1;
						        	var enc = data[length].substring(0,16);
						        	$("#unit_sus_no").val(dec(enc,data[0]));
					            }).fail(function(xhr, textStatus, errorThrown) {
					            });
				      } 	     
				}); 			
		});
	

		// Parent SUS No

			$("#parent_sus_no").keyup(function(){
				var sus_no = this.value;
				var susNoAuto=$("#parent_sus_no");

				susNoAuto.autocomplete({
				      source: function( request, response ) {
				        $.ajax({
				        type: 'POST',
				        url: "getTargetSUSNoList?"+key+"="+value,
				        data: {sus_no:sus_no},
				          success: function( data ) {
				        	  var susval = [];
			                  var length = data.length-1;
			                  var enc = data[length].substring(0,16);
			                        for(var i = 0;i<data.length;i++){
			                                susval.push(dec(enc,data[i]));
			                        }
			                        var dataCountry1 = susval.join("|");
			                        var myResponse = []; 
							        var autoTextVal=susNoAuto.val();
							$.each(dataCountry1.toString().split("|"), function(i,e){
								var newE = e.substring(0, autoTextVal.length);
								if (e.toLowerCase().includes(autoTextVal.toLowerCase())) {
								  myResponse.push(e);
								}
							});      	          
							response( myResponse ); 
				          }
				        });
				      },
					  minLength: 1,
				      autoFill: true,
				      change: function(event, ui) {
				    	 if (ui.item) {   	        	  
				        	  return true;    	            
				          } else {
				        	  alert("Please Enter Approved Parent SUS No.");
				        	  document.getElementById("parent_unit").value="";
				        	  susNoAuto.val("");	        	  
				        	  susNoAuto.focus();
				        	  return false;	             
				          }   	         
				    }, 
					select: function( event, ui ) {
						var sus_no = ui.item.value;			      	
						 $.post("getTargetUnitNameList?"+key+"="+value, {
							 sus_no:sus_no
			         }, function(j) {
			                
			         }).done(function(j) {
			        	 var length = j.length-1;
			             var enc = j[length].substring(0,16);
			             $("#parent_unit").val(dec(enc,j[0]));   
			                 
			         }).fail(function(xhr, textStatus, errorThrown) {
			         });
					} 	     
				});	
			});
			
			
			// Parent unit name
			 $("input#parent_unit").keypress(function(){
					var unit_name = this.value;
						 var susNoAuto=$("#parent_unit");
						  susNoAuto.autocomplete({
						      source: function( request, response ) {
						        $.ajax({
						        	type: 'POST',
							    	url: "getTargetUnitsNameActiveList?"+key+"="+value,
						        data: {unit_name:unit_name},
						          success: function( data ) {
						        	 var susval = [];
						        	  var length = data.length-1;
						        	  var enc = data[length].substring(0,16);
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
						        	  alert("Please Enter Approved Parent Unit.");
						        	  document.getElementById("parent_unit").value="";
						        	  susNoAuto.val("");	        	  
						        	  susNoAuto.focus();
						        	  return false;	             
						          }   	         
						      }, 
						      select: function( event, ui ) {
						    	 var unit_name = ui.item.value;
						    
							            $.post("getTargetSUSFromUNITNAME?"+key+"="+value,{target_unit_name:unit_name}, function(data) {
							            }).done(function(data) {
							            	var length = data.length-1;
								        	var enc = data[length].substring(0,16);
								        	$("#parent_sus_no").val(dec(enc,data[0]));
							            }).fail(function(xhr, textStatus, errorThrown) {
							            });
						      } 	     
						}); 			
				});
				
				
	 
	 

	 function calculate_age(obj){    
			
			
	     var from_d=$("#"+obj.id).val();
		 
		 var from_d=$("#"+obj.id).val();
	     from_d = from_d.replaceAll("/", "-");
	  
		 
	     var from_d1=from_d.substring(6,10);
	     var from_d2=from_d.substring(3,5);
	     var from_d3=from_d.substring(0,2);
	     var frm_d = from_d3+"-"+from_d2+"-"+from_d1;         
	   
	     var today=new Date();
	     var to_d3 = today.getDate();
	     var to_d2 = today.getMonth() + 1;
	     var to_d1 = today.getFullYear();        
	     var to_d0 = to_d3+"-"+to_d2+"-"+to_d1;
	     if(to_d2 > from_d2 && to_d3 > from_d3 || to_d3 == from_d3){
	     var year = to_d1 - from_d1        
	     var month = to_d2 - from_d2
	     }
	     if(to_d2 > from_d2 && to_d3 < from_d3){
	             var year = to_d1 - from_d1        
	             var month = to_d2 - from_d2 - 1
	             }
	     if(from_d2 > to_d2){
	             var year = to_d1 - from_d1 - 1
	             var month1 = from_d2 - to_d2
	             var month = 12 - month1
	     }
	     if(from_d2 == to_d2 && from_d3 > to_d3){
	             var year = to_d1 - from_d1 - 1
	             var days = from_d3 - to_d3
	     }
	     if(from_d2 == to_d2 && to_d3 > from_d3){
	             var year = to_d1 - from_d1 
	             var days =  to_d3 - from_d3 
	             
	     }
	     if(from_d2 == to_d2 && to_d3 == from_d3){
	             var year = to_d1 - from_d1 
	             var days = 0
	     }
	     //days
	     if(from_d2 > to_d2 && from_d3 > to_d3){
	             var m = from_d2 - to_d2 
	             var n = m * 30
	             var m1 = from_d3 - to_d3 
	             var m2 = n+m1
	             var days =  m2
	     }
	     if(from_d2 > to_d2 && to_d3 > from_d3){
	             var m = from_d2 - to_d2 
	             var n = m * 30
	             var m1 =  to_d3 - from_d3 
	             var m2 = n+m1
	             var days =  m2
	     }
	     if(to_d2 > from_d2   && to_d3 > from_d3){
	             var m =  to_d2 - from_d2 
	             var n = m * 30
	             var m1 =  to_d3 - from_d3 
	             var m2 = n+m1        
	             var days =  m2 
	     
	     }
	     if(to_d2 >  from_d2 && from_d3 > to_d3){
	             var m = to_d2 - from_d2   
	             var n = m * 30
	             var m1 = from_d3 - to_d3 
	             var m2 = n+m1
	             var days =  m2
	             
	     }
	    

	     
	     if (month == undefined)
	      {
	             month = 0;
	      }
	    
	     if(year < 16)
	     {
	    	 alert("Please enter age above 16");
	    	 $("#"+obj.id).val("");
	     }
	 }

	 
	 function onChangePerNo(obj)
	 {
	 	var data = obj.value;

	 	
	 	if(data.length == 5)
	 	{
	 		
		 
	 		var suffix="";
	 		var summation = 6*parseInt(data[0])+5*parseInt(data[1])+4*parseInt(data[2])+3*parseInt(data[3])+2*parseInt(data[4]);
	 		
	 		summation = parseInt( parseInt(summation) % 11);
	 	
	 		if(summation == 0)
	 		{
	 			suffix="A";
	 		}
	 		if(summation == 1)
	 		{
	 			suffix="F";
	 		}
	 		if(summation == 2)
	 		{
	 			suffix="H";
	 		}
	 		if(summation == 3)
	 		{
	 			suffix="K";
	 		}
	 		if(summation == 4)
	 		{
	 			suffix="L";
	 		}
	 		if(summation == 5)
	 		{
	 			suffix="M";
	 		}
	 		if(summation == 6)
	 		{
	 			suffix="N";
	 		}
	 		if(summation == 7)
	 		{
	 			suffix="P";
	 		}
	 		if(summation == 8)
	 		{
	 			suffix="W";
	 		}
	 		if(summation == 9)
	 		{
	 			suffix="X";
	 		}
	 		if(summation == 10)
	 		{
	 			suffix="Y";
	 		}
	 		 $.ajaxSetup({
                 async : false
         	});
	 		
	 		$("#persnl_no3").val(suffix);
	 		
	 		 $.ajaxSetup({
                 async : false
        	 });
	 		Personal_no_already_exist();
	 	}
	 	
	 	
	 	
	 }
	 
	 
	 /* jQuery(document).ready(function() {
			var unit_name = jQuery("#unit_name").val();
			${con}
			if('${roleAccess}' == 'Unit'){
				var file = document.getElementById('uploadtree');
				file.onchange = function(e) {
				  	var ext = this.value.match(/\.([^\.]+)$/)[1];
				  	var FileSize = file.files[0].size / 1024 / 1024; // in MB
				  	if (FileSize > 2) {
			           alert('File size exceeds 2 MB');
			           this.value = '';
			        } else {
			        	switch (ext) {
						  	case 'rar':
						  	case 'zip':
						 	alert('Allowed');
						    break;
						  	default:
						    	alert('Please Only Allowed *.zip , *.rar File Extensions.');
						   	this.value = '';
						}
			        }
				};
			}
			jQuery("#btnSubmit").click(function(event) {
				event.preventDefault();
				var sus_no = jQuery("#sus_no").val();
				if(sus_no == ""){
					alert("Please Enter SUS No.");
					jQuery("#sus_no").focus();
					return false;
				}
				
				if(jQuery("#uploadtree").get(0).files.length === 0){
					alert("Please Select Document.");
					jQuery("#uploadtree").focus()
					return false;
				}
				
				openModal();
				

			}); 
				
			var sus_no = '${sus_no}';
			if(sus_no == "")
			{
				jQuery("div#mvcr").hide();

				
			}
			else
			{
				jQuery("div#mvcr").show();
			}
				
			jQuery("#sus_no").val(sus_no);
			jQuery("#unit_name").val('${unit_name}');
		});
 */	


	


	 
  </script>

  