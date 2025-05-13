<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SEARCH WE/PE</title>
</head>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/excluded/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 

<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script type="text/javascript" src="js/miso/miso_js/jquery-1.12.3.js"></script>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<script>
$(document).ready(function() {
	
	$('#we_pe_no').keyup(function() {
        this.value = this.value.toUpperCase();
    });
	   
	   $('#table_title').keyup(function() {
	        this.value = this.value.toUpperCase();
	    });
	$.ajaxSetup({
	    async: false
	});
    var r =  $('input:radio[name=we_pe]:checked').val();	
   	if(r != undefined)
    	getWePeNoByType(r);	
		
	getarmvalue();	
	
	$("#letter_date").datepicker({
        dateFormat: "dd-mm-yy",    //"yy-mm-dd",
        changeMonth: true,
        changeYear: true
	}).attr('readonly', 'readonly');
    
    $("#eff_frm_date").datepicker({
        dateFormat: "dd-mm-yy",    //"yy-mm-dd",
        changeMonth: true,
        changeYear: true
	}).attr('readonly', 'readonly');
    
    $("#eff_to_date").datepicker({
        dateFormat: "dd-mm-yy",    //"yy-mm-dd",
        changeMonth: true,
        changeYear: true
	}).attr('readonly', 'readonly');  
    
    try{
		if(window.location.href.includes("msg="))
		{
			var url = window.location.href.split("?msg")[0];
			window.location = url;
		}
	}
	catch (e) {
		// TODO: handle exception
	}
  
});	

function checkDate()
{
	  var startDate = document.getElementById("eff_frm_date").value;
	  var endDate = document.getElementById("eff_to_date").value;

	  var b = startDate.split(/\D/);
	  var c = endDate.split(/\D/);	  
	  
	  if ((Date.parse(c.reverse().join('-')) <= Date.parse(b.reverse().join('-'))))
	  {
	    alert("Effective To date should be greater than Effective From date");
	    document.getElementById("eff_to_date").value = "";
	  }	
}

function clearDescription(){
	document.getElementById('we_pe_no_from').value = "";
	document.getElementById('uploaded_wepe').value = "";
	document.getElementById('suprcdd_we_pe_no').value = "";
	document.getElementById('table_title').value = "";
	document.getElementById('eff_frm_date').value = "";
	document.getElementById('eff_to_date').value = "";
	document.getElementById('doc_type').value = "";
	document.getElementById('sponsor_dire').value = 0;
	$('#arm').val(0);
}

 function getarmvalue(){
	$('input[type=radio][name=we_pe]').change(function() {		
		var radio_doc = $(this).val();	
		getWePeNoByType(radio_doc);			
	});	
}
 
 function getWePeNoByType(radio_doc)
 {
	
	 if(radio_doc != ""){
	 var wepetext=$("#we_pe_no_from");
	
	  wepetext.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
	       
	       /*  url: "getWePeCondiByNo",
	        data: {type1 : radio_doc}, */
	        type: 'POST',
		       url: "getWePeCondiByNoSearch1?"+key+"="+value,
   	        data: {we_pe : radio_doc , newRoleid: "aw",we_pe_no : document.getElementById('we_pe_no_from').value},
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
	        	alert("Please Enter Approved WE/PE No");
	        	wepetext.val("");	
	        	  
	        	$("#eff_frm_date").val("");
	 			$("#eff_to_date").val("");
	 			$("#letter_date").val("");
	 			$("#table_title").val("");
	 			$("#uploaded_wepe").val("");
	 			$("#suprcdd_we_pe_no").val("");
	 			$('#arm').val(0);
	 			$('#doc_type').val("");
	 			$('#sponsor_dire').val(0);
	        	  
	        	wepetext.focus();
	        	return false;	             
	          }   	         
	      }, 
        select: function( event, ui ) {
       	$(this).val(ui.item.value);    	        	
       	getDataByWePeNo($(this).val());	        	
        } 	     
	    });
	  
	  $.ajaxSetup({
		    async: false
		});

var wepetext11=$("#uploaded_wepe");
wepetext11.autocomplete({
  source: function( request, response ) {
    $.ajax({
    	type: 'POST',
        url: "getWePenumber?"+key+"="+value,
    data: {type : radio_doc,we_pe_no : document.getElementById('uploaded_wepe').value},
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
    	alert("Please Enter Approved Uploaded Document No");
    	wepetext11.val("");	
    	wepetext11.focus();
    	return false;	             
      }   	         
  }
 });


$.ajaxSetup({
	    async: false
	});

var wepetext1=$("#suprcdd_we_pe_no");
wepetext1.autocomplete({
  source: function( request, response ) {
    $.ajax({
	type: 'POST',
    url: "getWePeCondiByNoInWEPECon1?"+key+"="+value,
    data: {type : radio_doc, newRoleid: "aw",suprcdd_we_pe_no : document.getElementById('suprcdd_we_pe_no').value},
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
    	  alert("Please Enter Approved Superseded Document No");
    	  wepetext1.val("");	        	  
    	  wepetext1.focus();
    	 return false;	             
      }   	         
  }	     
});
	 }
	 else
		 alert("Please select Document");
	
 }
 
 function getDataByWePeNo(val)
 {
	
	 var we_pe_no = val;   
	 	$.post("getWEPENODetailsList?"+key+"="+value, {we_pe_no:we_pe_no}).done(function(j) {
	 		for ( var i = 0; i < j.length; i++) {
	 			$("#eff_frm_date").val(j[i].eff_frm_date);
	 			$("#eff_to_date").val(j[i].eff_to_date);
	 			$("#letter_date").val(j[i].letter_date);
	 			$("#table_title").val(j[i].table_title);
	 			$("#uploaded_wepe").val(j[i].uploaded_wepe);
	 			$("#suprcdd_we_pe_no").val(j[i].suprcdd_we_pe_no);
	 			
	 			var arm = $("#arm").val(j[i].arm);
	 			$('#arm option[value="'+arm+'"]').attr("selected", "selected");
	 			
	 			var doc_type = $("#doc_type").val(j[i].doc_type);
	 			$('#doc_type option[value="'+doc_type+'"]').attr("selected", "selected");
	 			
	 			var sponsor_dire = $("#sponsor_dire").val(j[i].sponsor_dire);
	 			$('#sponsor_dire option[value="'+sponsor_dire+'"]').attr("selected", "selected");
	 			
	 	 	}
	 		 }).fail(function(xhr, textStatus, errorThrown) { }); 	
 }
</script>

<script>
function validate(){	
	var we_pe = document.getElementsByName('we_pe');
	var genValue = false;

    for(var i=0; i<we_pe.length; i++){
        if(we_pe[i].checked == true){
            genValue = true;    
        }
    }
    if(!genValue){
    	alert("Please Select Document");
        return false;
    }
	
	if($("#we_pe_no_from").val() == ""){
		alert("Please Enter Existing WE/PE No");
		$("#we_pe_no_from").focus();
		return false;
	}
	if($("#we_pe_no").val() == ""){
		alert("Please Enter New WE/PE No");
		$("#we_pe_no").focus();
		return false;
	}	
}
</script>

<body>

<form:form name="copy_we_pe_weap" id="copy_we_pe_weap" action="copy_we_pe_weapAction?${_csrf.parameterName}=${_csrf.token}" method='POST' commandName="copy_we_pe_weapCMD">
	     	<div class="container" align="center">
	        	<div class="card">
	          		<div class="card-header"><h5><b>COPY WE/PE</b><br><span style="font-size:12px;color:red">(To be entered by Line Dte)</span></h5></div>
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
						                              <input type="radio" id="inline-radio1" name="we_pe" value="WE" class="form-check-input" onchange="clearDescription()">WE
						                          </label>&nbsp;&nbsp;&nbsp;
						                          <label for="inline-radio2" class="form-check-label ">
						                              <input type="radio" id="inline-radio2" name="we_pe" value="PE" class="form-check-input" onchange="clearDescription()">PE
						                          </label>&nbsp;&nbsp;&nbsp;
						                          <label for="inline-radio3" class="form-check-label ">
						                              <input type="radio" id="inline-radio3" name="we_pe" value="FE" class="form-check-input" onchange="clearDescription()">FE
						                          </label>&nbsp;&nbsp;&nbsp;
						                          <label for="inline-radio4" class="form-check-label ">
						                              <input type="radio" id="inline-radio4" name="we_pe" value="GSL" class="form-check-input" onchange="clearDescription()">GSL
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
	                  						<label class=" form-control-label">Existing WE/PE No <strong style="color: red;">*</strong></label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                  						<input  id="we_pe_no_from" name="we_pe_no_from"  class="form-control autocomplete" autocomplete="off" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search">
	                					</div>
	                					</div>
	              					</div>
	              					<div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-6">
	                  						<label class=" form-control-label">New WE/PE No <strong style="color: red;">*</strong></label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                  						<input  id="we_pe_no" name="we_pe_no" autocomplete="off" class="form-control autocomplete" style="font-family: 'FontAwesome',Arial;" >
	                					</div>
	                					</div>
	              					</div>             					
	            				</div>            			
	            			<div class="col-md-12" style="display:block;">				
		  						<div class="col-md-12" style="display:block;">
		  							<div class="row form-group row_content">
						                <div class="col col-md-4">
						                  <label class=" form-control-label" id="lbluc">Approved Uploaded Document No </label>
						                </div>
						                <div class="col-12 col-md-7">
						                <input  class="form-control" id ="uploaded_wepe" name ="uploaded_wepe" autocomplete="off" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search">
						                </div>
						            </div>	  								
		  						</div>
	  					 </div>
						<div class="col-md-12">	              					
	              			<div class="col-md-6">
	              			 	<div class="row form-group"> 
	                					<div class="col col-md-6">
	                  						<label for="suprcdd_we_pe_no" class=" form-control-label">Superseded Document No</label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                  						<input  id="suprcdd_we_pe_no" name="suprcdd_we_pe_no" autocomplete="off" class="form-control" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search" >
										</div>
	  								</div> 
	  						</div>
	  						<div class="col-md-6">
              					<div class="row form-group">
                					<div class="col col-md-6">
                  						<label class=" form-control-label">Document Title</label>
                					</div>
                					<div class="col-12 col-md-6">
                  						<input  id="table_title" name="table_title" autocomplete="off" class="form-control">
                					</div>
                					</div>
              					</div>
	  					</div>
	  					<div class="col-md-12">				
	  						<div class="col-md-6">
	  							<div class="row form-group">
					                <div class="col col-md-6">
					                  <label class=" form-control-label">Letter Date </label>
					                </div>
					                <div class="col-12 col-md-6">
					               		<input placeholder="dd-MM-yyyy" class="form-control" id ="letter_date" autocomplete="off" name ="letter_date">                                           
		                           </div>
					            </div>	  								
	  						</div>
	  					</div>
	  					<div class="col-md-12">		 
	  						<div class="col-md-6">
              					<div class="row form-group"> 
                					<div class="col col-md-6">
                  						<label for="text-input" class=" form-control-label">Effective From</label>
                					</div>
                					<div class="col-12 col-md-6">
                  						<input  id="eff_frm_date" name="eff_frm_date" autocomplete="off" class="form-control" placeholder="dd-MM-yyyy" onchange="checkDate()">
									</div>
  								</div>
	  						</div>
	  						<div class="col-md-6">
  								<div class="row form-group">  								
                					<div class="col col-md-6">
                  						<label for="text-input" class=" form-control-label">Effective To</label>
                					</div>
                					<div class="col-12 col-md-6">
                  						<input   id="eff_to_date" name="eff_to_date" autocomplete="off" class="form-control" placeholder="dd-MM-yyyy" onchange="checkDate()">
									</div>
  								</div>
  							</div>
	  					</div>
	  					<div class="col-md-12">	  								
	  						<div class="col-md-6">
	  							<div class="row form-group">
					                <div class="col col-md-6">
					                  <label class=" form-control-label">Security Classification</label>
					                </div>
					                <div class="col-12 col-md-6">					                
					                <select name="doc_type" class="form-control" id ="doc_type">					                
					                  	 <option value="">--Select--</option>
		                                 <option value="Restricted">Restricted</option>
		                                 <option value="Confidential">Confidential</option>
		                                 <option value="Secret">Secret</option>		                                   
		                            </select>
					                </div>
					            </div>	  								
	  						</div>
	  						<div class="col-md-6">
	  							<div class="row form-group">
					                <div class="col col-md-6">
					                  <label class=" form-control-label">Arm</label>
					                </div>
					                <div class="col-12 col-md-6">					                
					                <select  class="form-control" id="arm" name="arm">	
					                 <option value="0">--Select--</option>
                 						<c:forEach var="item" items="${getArmNameList}" varStatus="num" >
           									<option value="${item[0]}" > ${item[1]}</option>
           								</c:forEach>					                                                    
		                            </select>
					                </div>
					            </div>	  								
	  						</div>
	  					</div>
	  					<div class="col-md-12">				
	  						<div class="col-md-6">
	  							<div class="row form-group">
					                <div class="col col-md-6">
					                  <label class=" form-control-label">Sponsor Directorate</label>
					                </div>
					                <div class="col-12 col-md-6">
					                <select  class="form-control" id ="sponsor_dire" name ="sponsor_dire">
					                <option value="0">--Select--</option>
              						<c:forEach var="item" items="${getsponserListCue}" varStatus="num" >
       									<option value="${item[0]}" > ${item[1]}</option>
       								</c:forEach>
					                </select>
					                </div>
					            </div>	  								
	  						</div>
	  					</div>
	  		</div>
	  		<div class="card-footer" align="center">
				<input type="reset" class="btn btn-success btn-sm" value="Clear">   
	        	<input type="submit" class="btn btn-primary btn-sm" value="Save" onclick="return validate();">
		   </div> 						
	  </div>
	 </div>
	
	
	
</form:form>
</body>
</html>