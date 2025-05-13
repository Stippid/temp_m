<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
});
</script>
<form:form name="WetPetSuperEditFrom" id="WetPetSuperEditFrom" action="WetPetSuperEditAct" method='POST' commandName="editWetPetSuperCmd" >
  	<div class="container" align="center">
        	<div class="card">
          		<div class="card-header"> <h5>Edit WET/PET/FET CONDITIONS</h5></div>  
          		<div class="card-body card-block cue_text">
           			<div class="col-md-12">	            					
             				<div class="col-md-6">
             					<div class="row form-group">
               					<div class="col col-md-6">
               						<input type="hidden" id="id" name="id" placeholder="" class="form-control" value="${editWetPetSuperCmd.id}">
				                	<label for="text-input" class=" form-control-label">Type of Document</label>
				                </div>
               					<div class="col-12 col-md-6">
		                             <div class="form-check-inline form-check">
		                             <input type="hidden" id="wet_petHid" value="${editWetPetSuperCmd.wet_pet}">
		                                <label for="inline-radio1" class="form-check-label ">
		                                  <input type="radio" id="wet1" name="wet_pet" value="WET" class="form-check-input" disabled="disabled">WET
		                                </label>&nbsp;&nbsp;&nbsp;
		                                <label for="inline-radio2" class="form-check-label ">
		                                  <input type="radio" id="pet1" name="wet_pet" value="PET" class="form-check-input" disabled="disabled">PET
		                                </label>&nbsp;&nbsp;&nbsp;
		                                <label for="inline-radio2" class="form-check-label ">
		                                  <input type="radio" id="fet1" name="wet_pet" value="FET" class="form-check-input" disabled="disabled">FET
		                                </label>&nbsp;&nbsp;&nbsp;       
		                             </div>
					              </div>							              
               				 </div>
               			 </div>  
                         <div class="col-md-6">
             				<div class="row form-group">
               					<div class="col col-md-6">
                 					<label class=" form-control-label">WET/PET/FET Document No</label>
               					</div>
               					<div class="col-12 col-md-6">
                 					<input value="${editWetPetSuperCmd.wet_pet_no}" id="wet_pet_no" name="wet_pet_no" class="form-control"  readonly="readonly">
               					    <input  id="hidwepeno" type="hidden">
               					</div>
               				</div>
             			</div>    
             	</div>       	
             	      <div class="col-md-12" style="display: block;">		
               			<div class="col-md-12" style="display: block;">
             				<div class="row form-group row_content">
               					<div class="col col-md-4">
                 					<label class=" form-control-label">Approved Uploaded Document No <strong style="color: red;">*</strong></label>
               					</div>
               					<div class="col-12 col-md-7">
                 					<input value="${editWetPetSuperCmd.uploaded_wetpet}" name="uploaded_wetpet" id="uploaded_wetpet" maxlength="50" class="form-control" autocomplete="off" onchange="getDetailsBySupeerseedNo(this.value);" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search" >
               					</div>
               				</div>
             			</div>
           		     </div>
  					<div class="col-md-12">
  						<div class="col-md-6">
  						<div class="row form-group">
  							<div class="col col-md-6">
			                	<label for="text-input" class=" form-control-label">New Document <strong style="color: red;">*</strong></label>
			                </div>
			                <div class="col-12 col-md-6">  
			                    <div class="form-check-inline form-check">                          
				               <label for="inline-radio1" class="form-check-label ">
				                	<input type="radio" id="answer1" name="answer"  value="Yes" class="form-check-input" onclick="newdocument();" >Yes
				                </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				                <label for="inline-radio1" class="form-check-label ">
				                     <input type="radio" id="answer2" name="answer" value="No" class="form-check-input" onclick="newdocument();" >No
				                 </label>				            
				            </div>
				            </div>
				          </div>
		                 </div>
		                 <div class="col-md-6" id="suprcdd_wet_pet_no_div" style="display:none">
              			 	<div class="row form-group"> 
               					<div class="col col-md-6">
                 					<label for="text-input" class=" form-control-label">Superseded Document No<strong style="color: red;">*</strong></label>
               					</div>
               					<div class="col-12 col-md-6">
                 					<input value="${editWetPetSuperCmd.superseeded_wet_pet}" id="superseeded_wet_pet" name="superseeded_wet_pet" maxlength="50" class="form-control" autocomplete="off" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search">
								</div>
 							</div> 
  						</div>
  					</div>
  					<div class="col-md-12">
 						<div class="col-md-6">
          					<div class="row form-group">
            					<div class="col col-md-6">
              						<label class=" form-control-label">Document Title <strong style="color: red;">*</strong></label>
            					</div>
            					<div class="col-12 col-md-6">
              						<input value="${editWetPetSuperCmd.unit_type}" id="unit_type" name="unit_type" maxlength="150" class="form-control" >
            					</div>
            					</div>
          					</div>	
          			</div> 
          			<div class="col-md-12">
  						<div class="col-md-6">
            				<div class="row form-group"> 
              					<div class="col col-md-6">
                					<label for="text-input" class=" form-control-label">Effective From <strong style="color: red;">*</strong></label>
              					</div>
              					<div class="col-12 col-md-6">
                					<input value="${editWetPetSuperCmd.valid_from}"  id="valid_from" name="valid_from" placeholder="dd-MM-yyyy" class="form-control" onchange="checkDate()">
								</div>
							</div>
  						</div>
  						<div class="col-md-6">
 							<div class="row form-group">  								
               					<div class="col col-md-6">
                 					<label for="text-input" class=" form-control-label">Effective To <strong style="color: red;">*</strong></label>
               					</div>
               					<div class="col-12 col-md-6">
                 					<input value="${editWetPetSuperCmd.valid_till}" id="valid_till" name="valid_till" placeholder="dd-MM-yyyy" class="form-control" onchange="checkDate()" >
								</div>
 							</div>
 						</div>	  	
 					 </div>
 					   <div class="col-md-12">							
  						<div class="col-md-6">
  							<div class="row form-group">
				                <div class="col col-md-6">
				                  <label class=" form-control-label">Security Classification<strong style="color: red;">*</strong></label>
				                </div>
				                <div class="col-12 col-md-6">
				                <input type="hidden" id="doc_typeHid" value="${editWetPetSuperCmd.doc_type}">
				                <select id="doc_type" name="doc_type" class="form-control" >
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
				                  	<label class=" form-control-label">Arm <strong style="color: red;">*</strong></label>
				                </div>
				                <div class="col-12 col-md-6">
				                	<select class="form-control" id="arm" name="arm">
<!-- 				                	<option value="0">--Select--</option> -->
                 						${selectArmNameList}
                 						<c:forEach var="item" items="${getArmNameList}" varStatus="num" >
           									<option value="${item[0]}"  <c:if test="${item[0] eq editWetPetSuperCmd.arm}">selected="selected"</c:if>> ${item[1]}</option>
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
				                  	<label class=" form-control-label">Sponsor Directorate <strong style="color: red;">*</strong></label>
				                </div>
				                <div class="col-12 col-md-6">
<!-- 									 <select id="sponsor_dire" name="sponsor_dire" class="form-control">  -->
<!-- 									 <option value="0">--Select--</option> -->
<%-- 		              						<c:forEach var="item" items="${getsponserListCue}" varStatus="num" > --%>
<%-- 		       									<option value="${item[1]}" <c:if test="${item[1] eq editWetPetSuperCmd.sponsor_dire}">selected="selected"</c:if>> ${item[1]}</option> --%>
<%-- 		       								</c:forEach> --%>
<!-- 					                	</select> -->
											<select  class="form-control" id ="sponsor_dire" name ="sponsor_dire">	
					   			              ${selectLine_dte}
	              						<c:forEach var="item" items="${getLine_DteList}" varStatus="num" >
                  								<option value="${item.line_dte_sus}" <c:if test="${item.line_dte_sus eq editWetPetSuperCmd.sponsor_dire}">selected="selected"</c:if>> ${item.line_dte_name}</option> 
                  							</c:forEach>
	                 					                                                  
		                            </select>
				                </div>
				            </div>	  								
  						</div>
  					</div>  
  			</div>
  				<div class="card-footer" align="center">
						<input onclick="return isValid();" type="submit" class="btn btn-primary btn-sm" value="Update" >
             			<a href="searchWetPetSuper" class="btn btn-danger btn-sm" onClick="javascript:window.close('','_parent','');">  Cancel </a>
            	</div> 		
  		</div>
	</div>

</form:form>	

<script>

$(document).ready(function() {	
	$("#valid_from").datepicker({
        dateFormat: "dd-mm-yy",    //"yy-mm-dd",
        changeMonth: true,
        changeYear: true
	}).attr('readonly', 'readonly');
    
    $("#valid_till").datepicker({
        dateFormat: "dd-mm-yy",    //"yy-mm-dd",
        changeMonth: true,
        changeYear: true
	}).attr('readonly', 'readonly');
    
    $.ajaxSetup({
        async: false
    });
	 $('input[type=date]').change(function() {
	  	var startDate = document.getElementById("valid_from").value;
	  	var endDate = document.getElementById("valid_till").value;
	 	if ((Date.parse(endDate) <= Date.parse(startDate)))
	  	{
	  		alert("To date should be greater than From date");
	  	    document.getElementById("valid_till").value = "";
	  	    $("input#valid_till").focus();
	  	}
	});
	
	if(document.getElementById("wet_petHid").value != "" || document.getElementById("wet_petHid").value != null || document.getElementById("wet_petHid").value != undefined)
	{
		var radioButtons = document.getElementsByName("wet_pet");

	    if (radioButtons != null) {
	        for (var radioCount = 0; radioCount < radioButtons.length; radioCount++) {
	            if (radioButtons[radioCount].value == document.getElementById("wet_petHid").value) {
	                radioButtons[radioCount].checked = true;
	                wet_petNo(document.getElementById("wet_petHid").value);
	            }
	        }
	    }
	}
	if(document.getElementById("superseeded_wet_pet").value != "" && document.getElementById("superseeded_wet_pet").value != null && document.getElementById("superseeded_wet_pet").value != undefined)
	{
		document.getElementById("answer2").checked = true;
	}
	else
	{
		document.getElementById("answer1").checked = true;
	}	

	newdocument();
	
	if(document.getElementById("doc_typeHid").value != "" || document.getElementById("doc_typeHid").value != null)
	{
		$("select#doc_type").val(document.getElementById("doc_typeHid").value);
	}
		
	$('input[type=radio][name=wet_pet]').change(function() {		
		var radio_doc = $(this).val();
		wet_petNo(radio_doc);		
	});
	
	try{
		if(window.location.href.includes("msg="))
		{
			var url = window.location.href.split("?msg")[0];
			window.location = url;
		}
		else if(window.location.href.includes("deleteid="))
			{
				var url = window.location.href.split("?deleteid")[0];
				window.location = url;
			}
	}
	catch (e) {
		// TODO: handle exception
	}
  
});	

function wet_petNo(wetno)
{
      if(wetno != ""){  
	var wepetext=$("#uploaded_wetpet");

	  wepetext.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
	        type: 'POST',
	  	    url: "getWetPetFetNoOnlySuperPage?"+key+"="+value,
	        data: {val : wetno,wet_pet_no:document.getElementById('uploaded_wetpet').value},
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
	        	  
	        	 document.getElementById("valid_from").value="";
	   		  document.getElementById("valid_till").value="";
	   		  $("select#doc_type").val("Restricted");
	   		  $("select#sponsor_dire").val("0");
	   		  $("select#arm").val("0");
	        	  
	        	wepetext.focus();
	        	return false;	             
	          }   	         
	      }, 
     select: function( event, ui ) {
    	$(this).val(ui.item.value);    	        	
    	getDetailsBySupeerseedNo($(this).val());	        	
     } 	     
	    });
	  
	  
	  $.ajaxSetup({
		    async: false
		});
	  
	  var wepetext1=$("#superseeded_wet_pet");
	
	  wepetext1.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
	        type: 'POST',
		  	url: "getWetPetsupercddList?"+key+"="+value,
	        data: {we_r : wetno,wet_pet_no: document.getElementById('superseeded_wet_pet').value},
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
	      },	     
	    });
	 }
	 else
		 alert("Please select Document");
}
	
function getDetailsBySupeerseedNo(val)
{
	  if(val != "" && val != null)
	  {
		  $.post("getDetailsBySuperseedNo?"+key+"="+value, {val : val}).done(function(j) {
			if(j!="" && j!= null){
			 document.getElementById("valid_from").value=j[0][0];
			document.getElementById("valid_till").value=j[0][1];
			$("select#doc_type").val(j[0][2]);
			$("select#sponsor_dire").val(j[0][3]);
			$("select#arm").val(j[0][4]);
			}
			else{
				  document.getElementById("valid_from").value="";
				  document.getElementById("valid_till").value="";
				  $("select#doc_type").val("Restricted");
				  $("select#sponsor_dire").val("0");
				  $("select#arm").val("0");
			}
		  }).fail(function(xhr, textStatus, errorThrown) { }); 
	  }
	  else
	  {
		  alert("Please enter Approved Uploaded Document No");
		  document.getElementById("valid_from").value="";
		  document.getElementById("valid_till").value="";
		  $("select#doc_type").val("Restricted");
		  $("select#sponsor_dire").val("0");
		  $("select#arm").val("0");
	  }
}
  function newdocument() {
		var r = $('input:radio[name=answer]:checked').val();
		if(r == 'No')
		{
			$("div#suprcdd_wet_pet_no_div").show();
		}
		else
		{
			$("div#suprcdd_wet_pet_no_div").hide(); 
			 document.getElementById("superseeded_wet_pet").value="";			
		}
	}
  function checkDate()
  {  	
  	  var startDate = document.getElementById("valid_from").value;
  	  var endDate = document.getElementById("valid_till").value;

  	  var b = startDate.split(/\D/);
  	  var c = endDate.split(/\D/);	  
  	  
  	  if ((Date.parse(c.reverse().join('-')) <= Date.parse(b.reverse().join('-'))))
  	  {
  	    alert("Effective To date should be greater than Effective From date");
  	    document.getElementById("valid_till").value = "";
  	  }	
  }
  
  function isValid()
  {  	  
  	  var r =  $('input:radio[name=wet_pet]:checked').val();	
	  if(r == undefined)
	  {		 
		    alert("Please select Document");
		    $('input:radio[name=wet_pet]:checked').focus();
			return false;
	  }  	  
  	
  	  if($("input#uploaded_wetpet").val() == "")
	  {
		alert("Please enter Approved Uploaded Document No");
		$("input#uploaded_wetpet").focus();
		return false;
	  }
  	  
	  	if($("input#wet_pet_no").val() == "")
		{
			alert("Please enter WET/PET/FET Document No");
			$("input#wet_pet_no").focus();
			return false;
		}
	  	
	  	var r1 = $('input:radio[name=answer]:checked').val();
	  	 if(r1 == undefined)
		  {		 
			    alert("Please select New Document");
			    $('input:radio[name=answer]:checked').focus();
				return false;
		  } 
	  	 else if (r1 == "No") {
			if ($("input#superseeded_wet_pet").val() == ""){
				alert("Please Select Superseded Document No");
				$("input#superseeded_wet_pet").focus();
				return false;
			}
		}
	  	
		if($("input#unit_type").val() == "")
		{
			alert("Please enter Document Title");
			$("input#unit_type").focus();
			return false;
		}
		if($("input#valid_from").val() == "")
		{
			alert("Please select Effective From");
			$("input#valid_from").focus();
			return false;
		}
		if($("input#valid_till").val() == "")
		{
			alert("Please select Effective To");
			$("input#valid_till").focus();
			return false;
		}
		if($("select#doc_type").val() == "")
		{
			alert("Please select Document Type");
			$("select#doc_type").focus();
			return false;
		}
		if($("select#arm").val() == "0")
		{
			alert("Please select Arm");
			$("select#arm").focus();
			return false;
		}
		if($("select#sponsor_dire").val() == "0")
		{
			alert("Please select Sponsor Directorate");
			$("select#sponsor_dire").focus();
			return false;
		}
		
		return true;
  }
  
</script>

