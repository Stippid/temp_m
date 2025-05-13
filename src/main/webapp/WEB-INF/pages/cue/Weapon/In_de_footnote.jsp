<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Increment Decrement General Notes For Weapon</title></head>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/excluded/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>  

<script type="text/javascript" src="js/miso/miso_js/jquery-1.12.3.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/cue/update.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>
<script src="js/cue/printAllPages.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<script>
function printDiv() 
  	{
	 	var printLbl = ["Type :", "MISO WE/PE No :", "Table Title :"];
	 	var printVal = [$('input:radio[name=we_pe]:checked').val(),document.getElementById('we_pe_no').value,document.getElementById('table_title1').value];
	 	printDivOptimize('divPrint','INCR/DECR ON GENERAL NOTES FOR WEAPONS',printLbl,printVal,"select#status");
 	 }
</script>


<body>
<script>

function Search(){
 	
    var we_pe1 = $("input[name='we_pe']:checked").val();
    $("#we_pe1").val(we_pe1);
	$("#we_pe_no1").val($("#we_pe_no").val());
    $("#amt_inc_dec1").val($("#amt_inc_dec").val());
    $("#item_seq_no1").val($("#item_seq_no").val());
    $("#status1").val($("#status").val());
    $("#scenario1").val($("#scenario").val());
    $("#location1").val($("#location").val());
    $("#formation1").val($("#formation").val());
    $("#unit1").val($("#unit").val());
    $("#location1_hid").val($("#location_code").val());
    $("#formation1_hid").val($("#formation_code").val());
    $("#unit1_hid").val($("#unit_code").val());
    document.getElementById('searchForm').submit();
}	
	
function clearDiscription()
{
	document.getElementById("we_pe_no").value="";
	document.getElementById("table_title1").value="";
}

$(document).ready(function() {
	if('${we_pe1}' != "")
	{
		
		$("input[name=we_pe][value="+'${we_pe1}'+"]").prop('checked', true);
		$("#we_pe_no").val('${we_pe_no1}');
		getArmByWePeNo('${we_pe_no1}');
		$("#scenario").val('${scenario1}');
		if('${scenario1}' == "Location")
		{
			$("#location").val('${location1}');
			$("#location_code").val('${location1_hid}');
			$("#getloc").show();
		}
		if('${scenario1}' == "Formation")
		{
			$("#formation").val('${formation1}');
			$("#formation_code").val('${formation1_hid}');
			$("#getform").show();
		}
		if('${scenario1}' == "Unit")
		{
			$("#unit").val('${unit1}');
			$("#unit_code").val('${unit1_hid}');
			$("#getunit").show();
		}
		$("div#divwatermark").val('').addClass('watermarked'); 
		watermarkreport(); 
		$("#divPrint").show();
		$("div#divSerachInput").show();
		document.getElementById("printId").disabled  = false;
		
		if('${list.size()}' == 0 ){
			$("div#divSerachInput").hide();
			document.getElementById("printId").disabled  = true;
			$("table#FootnoteReport1").append("<tr><td colspan='9' style='text-align :center;'>Data Not Available</td></tr>");
		 }
	}
	
	$("#searchInput").on("keyup", function() {
			var value = $(this).val().toLowerCase();
			$("#FootnoteReport1 tbody tr").filter(function() { 
			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
			});
		});
	
	 $('#condition').keyup(function() {
	        this.value = this.value.toUpperCase();
	    });

	 $('#remarks').keyup(function() {
	        this.value = this.value.toUpperCase();
	    });
	
	
	
	var r =  $('input[type=radio][name=we_pe]:checked').val();	
	if(r != undefined)
		getWePeNoByType(r);	
	

	 $("input[type='radio'][name='we_pe']").click(function(){
			var we_pe1 = $("input[name='we_pe']:checked").val();
			getWePeNoByType(we_pe1);
	 });
	
    var z = document.getElementById("item_type").value;
	getbaseauth(z); 
	try{
		if(window.location.href.includes("count="))
			{
				var url = window.location.href.split("?count=")[0];
				var m=  window.location.href.split("&msg=")[1];
				window.location = url;
				
				if(m.includes("Updated+Successfully")){
					window.opener.getRefreshReport('in_de_foot_weap',m);
					window.close('','_parent','');
				}   				
			}	
	}
	catch (e) {
		// TODO: handle exception
	}
	
      $.ajaxSetup({
    	    async: false
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
	        		if(j != "" && j!=null)
	        			{	        	
          		document.getElementById("item_seq_no").value=j;
	        			}
	        		else{
	        			document.getElementById("item_seq_no").value="";
	        		}
	        			
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
		        	  alert("Please Enter Approved Item Code");
		        	  wepetext2.val("");
		        	  document.getElementById("item_type").value="";  
		        	  document.getElementById("base_auth").value=0.00;
		  				$("input[type=radio][id=ct-radio2]").attr('disabled', true);
		        	  wepetext2.focus();
		        	  return false;	             
		          }   	         
		      },select: function( event, ui ) {
		        	$(this).val(ui.item.value);    	        	
		        	$.post("getitemnamefromcode?"+key+"="+value, {val : ui.item.value}).done(function(j) {
		        		if(j != "" && j!=null)
	        			{	        	
		        			document.getElementById("item_type").value=j;
	        			}
	        		else{
	        			document.getElementById("item_type").value="";
		        	}
	            			
		        	 }).fail(function(xhr, textStatus, errorThrown) { });  
		        	
		        	 $.ajaxSetup({
		     	  	    async: false
		     	  	}); 
		        	getbaseauth(document.getElementById("item_type").value);	 
		        }    	     
		    });
  
    
    });
    
    
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

function clearall()
{		
	document.getElementById('divPrint').style.display='none';
	document.getElementById("printId").disabled = true;
	var tab = $("#FootnoteReport1");
	tab.empty();
   	$("#searchInput").val("");
   	$("#searchInput").hide();
  //document.location.reload();
 	localStorage.clear();
	localStorage.Abandon();
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
								document.getElementById("base_auth_hidden").value=j[0].auth_weapon;
				    			if(j[0].auth_weapon == '0.00' || j[0].auth_weapon == '0' || j[0].auth_weapon == '' || j[0].auth_weapon== undefined)
				    	          { 
				    				document.getElementById("base_auth").value=0.00;
				    			 $("input[type=radio][id=ct-radio2]").attr('disabled', true);
				    	          }
				    			else
				    				{
		    						document.getElementById("base_auth").value=j[0].auth_weapon;
		    						 $("input[type=radio][id=ct-radio2]").attr('disabled', false);
				    				}
							}
	 					}).fail(function(xhr, textStatus, errorThrown) { })
			}
	 }).fail(function(xhr, textStatus, errorThrown) { })
 }

</script>

<form:form name="in_de_footnoteAction" id="in_de_footnoteAction" action="in_de_footnoteAction" method='POST' commandName="in_de_footnoteCMD">
        	<div class="container" align="center">
	        	<div class="card">
	          		<div class="card-header"> <h5>INCREMENT/DECREMENT ON GENERAL NOTES FOR WEAPONS</h5></div>
	          			<div class="card-body card-block cue_text">
						  <div class="col-md-12">
						  <div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-6">
						                	<label for="text-input" class=" form-control-label">Type of Document <strong style="color: red;">*</strong></label>
						                </div>
							                <div class="col-12 col-md-6">
					                              <div class="form-check-inline form-check">
					                                <label for="inline-radio1" class="form-check-label ">
					                                  <input type="radio" id="inline-radio1" name="we_pe" value="WE" onchange="clearDiscription();" class="form-check-input">WE
					                                </label>&nbsp;&nbsp;&nbsp;
					                                <label for="inline-radio2" class="form-check-label ">
					                                  <input type="radio" id="inline-radio2" name="we_pe" value="PE" onchange="clearDiscription();" class="form-check-input">PE
					                                </label>&nbsp;&nbsp;&nbsp;
					                                <label for="inline-radio3" class="form-check-label ">
					                                  <input type="radio" id="inline-radio3" name="we_pe" value="FE" onchange="clearDiscription();" class="form-check-input">FE
					                                </label>&nbsp;&nbsp;&nbsp;
					                                <label for="inline-radio4" class="form-check-label ">
					                                  <input type="radio" id="inline-radio4" name="we_pe" value="GSL" onchange="clearDiscription();" class="form-check-input">GSL
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
	                  						<label class=" form-control-label">MISO WE/PE No <strong style="color: red;">*</strong></label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                  						<input  id="we_pe_no" name="we_pe_no"  class="form-control" maxlength="100" autocomplete="off" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search">
	                					</div>                      			
	                					</div>
	              					</div>
	  								<div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-6">
	                  						<label class=" form-control-label">WE/PE Title</label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                  						<input  id="table_title1" name="" class="form-control" autocomplete="off" readonly="readonly">
	                					</div>
	                					</div>
	              					</div>
	              			</div>
	              		     <div class="col-md-12">	
            		              <div class="col-md-6">
	              					<div class="row form-group">
									<div class="col col-md-6">
	                  							<label class=" form-control-label">General Notes Scenario <strong style="color: red;">*</strong></label>
	                						</div>
	                						<div class="col-12 col-md-6">
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
                  						<label class=" form-control-label">Location <strong style="color: red;">*</strong></label>
                					</div>
                					<div class="col-12 col-md-6">
                  					<input type="hidden" id="location_code" name="location"  class="form-control" >
                  						<input type="text" id="location" name="location_name" maxlength="5" class="form-control" autocomplete="off" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search">
                					</div>
              					</div>  
              					<div class="row form-group" id="getform" style="display:none;" >
                					<div class="col col-md-6">
                  						<label class=" form-control-label">Formation <strong style="color: red;">*</strong> </label>
                					</div>
                					
                					<div class="col-12 col-md-6">
										<input type="hidden" id="formation_code" name="formation"  class="form-control" >
										<input type="text" id="formation" name="formation_name" maxlength="8" class="form-control autocomplete" autocomplete="off" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search">
									</div>                					
              					</div>  
              					<div class="row form-group" id="getunit" style="display:none;" >
                					<div class="col col-md-6">
                  						<label class=" form-control-label">Unit <strong style="color: red;">*</strong> </label>
                					</div>
                					<div class="col-12 col-md-6">
	                					<input type="hidden" id="unit_code" name="scenario_unit"  class="form-control" >
	                					<input type="text" id="unit" name="scenario_unit_name" maxlength="8" class="form-control" autocomplete="off" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search">
	                				</div>
              					</div> 
              					</div>
              			</div>
	                 	  <div class="col-md-12"><span class="line"></span></div>		
							<div class="col-md-12">	
            						<div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-6">
	                  						<label class=" form-control-label">Weapon Nomenclature <strong style="color: red;">*</strong></label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                  						<input class="form-control" id="item_type" autocomplete="off" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search"> 
	                					</div>
	                					</div>
	              					</div>
	              				<div class="col-md-6">
	              			 	<div class="row form-group"> 
	                					<div class="col col-md-6">
	                  						<label  class=" form-control-label" >Item Code <strong style="color: red;">*</strong></label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                  						<input id="item_seq_no" name="item_seq_no" maxlength="8" class="form-control" autocomplete="off"  style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search">
										</div>
	  								</div> 
	  						</div>
	  					</div>
	              		<div class="col-md-12">	
	  						      <div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-6">
	                  						<label class=" form-control-label">Base Authorisation</label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                  						<input  id="base_auth" name="base_auth" class="form-control" readonly="readonly">
	                  						 <input type="hidden" id="base_auth_hidden" name="base_auth_hidden">
	                					</div>
	                					</div>
	              					</div>
	              		  </div>
	                      <div class="col-md-12">
            		         <div class="col-md-6">
	                               <div class="row form-group">
	                					<div class="col col-md-6">
	                  							<label for="text-input" id="incr_decr" class="form-control-label">Whether Incr/Decr<strong style="color: red;">*</strong></label>
	                						</div>
               						        <div class="col-12 col-md-6">
					                              <div class="form-check-inline form-check">
					                                <label for="ct-radio1" class="form-check-label ">
					                                  <input type="radio" id="ct-radio1" name="ct-radio1" value="Increase" class="form-check-input">Increment
					                                </label>&nbsp;&nbsp;&nbsp;
					                                <label for="ct-radio2" class="form-check-label ">
					                                  <input type="radio" id="ct-radio2" name="ct-radio1" value="Decrease" class="form-check-input">Decrement
					                                </label>&nbsp;&nbsp;&nbsp;
					                              </div>
               					        	</div>
	              						</div>
	                           </div>
	          				     <div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-6">
	                  						<label class=" form-control-label">Amount of Increment/ Decrement <strong style="color: red;">*</strong></label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                  						<input  id="amt_inc_dec" onfocus="if(this.value=='0') this.value='';" value="0" name="amt_inc_dec" 
	                  						onkeyup="selectradiobase();" maxlength="8" onkeypress="return isNumberKey(event, this);" 
	                  						onchange="return checkAmt_inc_decLength();"  autocomplete="off" class="form-control">&nbsp;
	                					</div>
	                					</div>
	              					</div>					
	  					   </div>
	  						<div class="col-md-12">	
            		               <div class="col-md-6">
	  								<div class="row form-group">
											  <div class="col col-md-6">
											      <label class=" form-control-label">General Notes Condition <strong style="color: red;">*</strong></label>
											  </div>
											  <div class="col-12 col-md-6">
											       <textarea class="form-control" id="condition" name="condition" maxlength="255"></textarea>
											  </div>
										</div>
									</div>	
										<div class="col-md-6">
	  								       <div class="row form-group">
											  <div class="col col-md-6">
											      <label class=" form-control-label">Remarks</label>
											  </div>
											  <div class="col-12 col-md-6">
											      <textarea class="form-control char-counter1" id="remarks" name="remarks" maxlength="255"></textarea> 
											  </div>
										</div>
										</div>
							</div>
	                 </div>
					    <div class="card-footer" align="center">
							<input type="reset" class="btn btn-success btn-sm" value="Clear" onclick="clearall();">   
		              		<input type="submit" class="btn btn-primary btn-sm"  value="Save" onclick="return isvalidation();">
		              		<i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm" onclick="Search();" value="Search">
		              		<i class="fa fa-print"></i><input type="button" id="printId" class="btn btn-primary btn-sm btn_report" value="Print" onclick="printDiv();" disabled>
			            </div> 
        		</div>
		</div>
					  	
</form:form>

<div style="width: 20%; padding-left: 1%; display: none;" id="divSerachInput">
	<input id="searchInput" type="text" style="font-size:12px; font-family: 'FontAwesome',Arial;margin-bottom: 5px;" placeholder="&#xF002; Search Word"  size="35" class="form-control">
</div>
 <div class="col s12" style="display: none;" id="divPrint">
					  	 <div id="divShow" style="display: block;">
						</div>
		 <div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
					      <span id="ip"></span>
							<table id="FootnoteReport1" class="table no-margin table-striped  table-hover  table-bordered report_print" >
								 <thead>
									<tr>
								   <th class="tableheadSer">Ser No</th>
									<th >Item Code</th>
									<th >Nomenclature</th>
									<th >Condition</th>
									<th >Base Authorisation</th>
									<th >Amt of Incr/Decr</th>
									<th >Gen Notes Scenario</th>
					    			<th >Loc/ Fmn/ Unit</th>					
									<th id="thLetterId" >Letter No</th>
									<th id="thRemarkId" >Error Correction</th>
									<th class='lastCol'>Action</th>
								</tr>
								</thead> 
								<tbody>
								<c:forEach var="item" items="${list}" varStatus="num" >
								<tr>
									<td class="tableheadSer">${num.index+1}</td>
									<td >${item.item_seq_no}</td>
									<td >${item.item_type}</td>
									<td >${item.condition}</td>
									<td >${item.baseauth}</td>
									<td >${item.amt_inc_dec}</td>
									<td >${item.scenario}</td>
									<td >${item.loc_for_unit}</td>
									<td id="thLetterId1" >${item.reject_letter_no}</td>
									<td id="thRemarkId1" >${item.reject_remarks}</td>
									<td id="thAction1" class='lastCol'>${item.id}</td>
								</tr>
					</c:forEach>
								</tbody>
							</table>
						</div>
						</div>
                  
                  
             <c:url value="in_de_footnote1" var="searchUrl" />
    		<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="we_pe_no1">
    			<input type="hidden" name="amt_inc_dec1" id="amt_inc_dec1" value="0"/>
    			<input type="hidden" name="we_pe_no1" id="we_pe_no1" value="0"/>
    			<input type="hidden" name="item_seq_no1" id="item_seq_no1" value="0"/>
    			<input type="hidden" name="we_pe1" id="we_pe1" value="0"/>
    			<input type="hidden" name="scenario1" id="scenario1" value="0"/>
    			<input type="hidden" name="location1" id="location1" value="0"/>
    			<input type="hidden" name="formation1" id="formation1" value="0"/>
    			<input type="hidden" name="unit1" id="unit1" value="0"/>
    			<input type="hidden" name="status1" id="status1" value="0"/>
    			<input type="hidden" name="location1_hid" id="location1_hid" value="0"/>
    			<input type="hidden" name="formation1_hid" id="formation1_hid" value="0"/>
    			<input type="hidden" name="unit1_hid" id="unit1_hid" value="0"/>
    		</form:form> 
    		
<c:url value="updateIncrementUrl" var="updateUrl" />
	<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid" target="result">
		<input type="hidden" name="updateid" id="updateid" value="0"/>
		</form:form> 
		
		<script>
var newWin;
function editData(id){	
	var x = screen.width/2 - 1100/2;
    var y = screen.height/2 - 900/2;
   
	newWin = window.open("", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');
	 
	 window.onfocus = function () { 
		 //newWin.close();
	 }

	document.getElementById('updateid').value=id;
	document.getElementById('updateForm').submit();
}
function closeWindow()
{
	newWin.close();   
}
function deleteData(id){
		
		 $.post("deleteIncrementUrlAdd?"+key+"="+value, {deleteid : id}).done(function(j) {
		   	 alert(j);
				 Search();
		     }).fail(function(xhr, textStatus, errorThrown) { });  
}	
							
</script>
		
<script>
function getWePeNoByType(we_pe1)
{
	if(we_pe1 != ""){
var wepetext13=$("#we_pe_no");

wepetext13.autocomplete({
    source: function( request, response ) {
      $.ajax({
   	  type: 'POST',
   	  url: "getWePeCondiByNo?"+key+"="+value,
      data: {type1 : we_pe1, newRoleid : "aw", we_pe_no : document.getElementById('we_pe_no').value},
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
          var autoTextVal=wepetext13.val();
			$.each(dataCountry1.toString().split("|"), function(i,e){
				var newE = e.substring(0, autoTextVal.length);
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
      	  alert("Please Enter Approved WE/PE No");
      	  wepetext13.val("");
      	  document.getElementById("table_title1").value="";
      	  wepetext13.focus();
      	  return false;	             
        }   	         
    }, 
     select: function( event, ui ) {
      	$(this).val(ui.item.value);    	        	
      	getArmByWePeNo($(this).val());	        	
      } 	     
  });
	 }
 	 else
 		 alert("Please select Document");
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
		   $("#location_code").val("");
		   $("#unit").val("");
		   $("#unit_code").val("");
	   }
	   else if($(this).val()=="Unit"){
		   $("label#labelcon").text("Unit");
		   $("div#getunit").show();
		   $("div#getform").hide();
		   $("div#getloc").hide();
		   $("#location").val("");
		   $("#location_code").val("");
		   $("#formation").val("");
		   $("#formation_code").val("");
	   }
	   else
	   {
		   $("label#labelcon").text("Others");
		   $("div#getloc").hide();
		   $("div#getform").hide();
		   $("div#getunit").hide();
		   $("#location").val("");
		   $("#location_code").val("");
		   $("#formation").val("");
		   $("#unit").val("");
		   $("#unit_code").val("");
		   $("#formation_code").val("");
		   
	    }
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
	        	  document.getElementById("formation_code").value="";
				 wepetext1.focus();
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
	        	  document.getElementById("unit_code").value="";
				 wepetext1.focus();
	        	  return false;	             
	          }   	         
	      }, 
	         select: function( event, ui ) {
	        	$(this).val(ui.item.value);    	        	
	        	var unit_name=ui.item.value;
	        	 $.post("getUnitNameFromSusNo?"+key+"="+value, {unit_name : unit_name}).done(function(j) {
	        		 if(j !="" && j!=null){
	        			 document.getElementById("unit_code").value=j[0];
	    				}
	    				else{
	    					document.getElementById("unit_code").value="";	
	    				}
	        	     }).fail(function(xhr, textStatus, errorThrown) { });  
	        }  	      
	    });
  });  
   
$(function() {
	  var wepetext11=$("#location");
	  
	  wepetext11.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
	        type: 'POST',
	        url: "getLocation?"+key+"="+value,  
	        data: {code_value : document.getElementById('location').value},
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
	            var autoTextVal=wepetext11.val();
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
	        	  wepetext11.val("");	 
	        	  document.getElementById("location").value="";
				  document.getElementById("location").focus();
				  document.getElementById("location_code").value="";
				 
	        	  wepetext11.focus();
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


</script>
     <script>
   
     
     function Delete1(id){
   	   document.getElementById('deleteid').value=id;
   		document.getElementById('deleteForm').submit();
    }
    
    function Update(id){
   	  document.getElementById('updateid').value=id;
   	   document.getElementById('updateForm').submit();
     }        
        
      </script>
 <script>    
      function getArmByWePeNo(val)
      { 
      	  if(val != "" && val != null)
      	  {   
      		 
      		 $.post("getDetailsByWePeCondiNo?"+key+"="+value, {val : val}).done(function(j) {
      			if(j!=null && j!="")
  				{
  			document.getElementById("table_title1").value=j[0].table_title;
  				}else
  					{
  					document.getElementById("table_title1").value="";
  					}
        	     }).fail(function(xhr, textStatus, errorThrown) { }); 
      	  }
      	  else
      	  {
      		  document.getElementById("table_title1").value="";
      	  } 
      }

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

 <script>
 	 function isvalidation(){
 		var r =  $('input:radio[name=we_pe]:checked').val();	
 		  if(r == undefined)
 		  {		 
 			    alert("Please select Document");
 			    $('input:radio[name=we_pe]:checked').focus();
 				return false;
 		  } 
 		 
  	   if($("input#we_pe_no").val()==""){
  			alert("Please Enter WE/PE No")
  			$("input#we_pe_no").focus();
  			return false;
  		}
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
	if($("input#amt_inc_dec").val()=="" || $("input#amt_inc_dec").val()=="0"){
		alert("Please Enter Amt Incr/Decr")
		$("input#amt_inc_dec").focus();
		return false;
	}
	 var cond = $("textarea#condition").val();
	  if(cond.trim()=="" )
	  {
		  alert("Please Enter Condition");
		  $("textarea#condition").focus();
			return false;
	  }
	  var amt;
	  var base;
 if($("input#amt_inc_dec").val()!="")
	  {		 
	 amt = $("input#amt_inc_dec").val();
    var r_s =  $('input:radio[name=ct-radio1]:checked').val();	
	 base = document.getElementById("base_auth").value;	
	
		 if(r_s == "Decrease")
			 {			 
	var base1 = parseFloat(base);
	 var amt1 = parseFloat(amt);
			 if (base1 < amt1){
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
</body>
</html>
