<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Upload WE/PE</title>
</head>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/excluded/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 

<script type="text/javascript" src="js/miso/miso_js/jquery-1.12.3.js"></script>
<script src="js/cue/wepe_cce.js" type ="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>


<script type="text/javascript">
$(document).ready(function() {
	$('body').toggleClass('open');
	$('.nav-item').toggle();
	$("div#divLogoutHidShow").hide();	
	document.getElementById('menuToggle').style.pointerEvents = 'none';
	$('#left-panel , #menuToggle').css("display","none");
});
</script>
 <script>
$(document).ready(function() { 
	  $('#remarks').keyup(function() {
	        this.value = this.value.toUpperCase();
	    });
	  
	if ( document.getElementById("ces_ccestype").value !=null)
		{		
		var radioButtons = document.getElementsByName("ces_cces");
		if (radioButtons != null) 
		{
		    for (var radioCount = 0; radioCount < radioButtons.length; radioCount++) 
		    {
		        if (radioButtons[radioCount].value ==  document.getElementById("ces_ccestype").value  ) 
		        {
		            radioButtons[radioCount].checked = true;
		        }
		    }
		}		
		 
		}
	
	
	if ( document.getElementById("cescodehidden").value !=null)
		{
		if ( document.getElementById("item_seq_nohidden").value !=null)
		{
		edititemname(document.getElementById("cescodehidden").value + "," +document.getElementById("item_seq_nohidden").value );
		}
		else
			{
			edititemname(document.getElementById("cescodehidden").value);
			}
			}
	
	document.getElementById("auth_proposed").value=parseFloat(document.getElementById("auth_proposed").value);
	
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


function checkAuth_amtLength() {
	var auth_weapon= $("input#auth_proposed").val();
	
 	if(auth_weapon.length > 8)
	{
		alert("Please Enter Valid Digit");
		$("input#auth_proposed").val("");
 		return false;
		
	}
	if(auth_weapon != "" && auth_weapon.includes(".")) {
		var auth_weapon1 = auth_weapon.toString().split(".");			
	 	if(auth_weapon1[0].length > 5 || auth_weapon1[1].length > 2 )
		{
	 		alert("Please Enter Valid Digit");
			$("input#auth_proposed").val("");
	 		return false;
		}
	 	
	 }
	else {
		if(auth_weapon.length > 5)
		{
			alert("Please Enter Valid Digit");
			$("input#auth_proposed").val("");
	 		return false;
			
		}
	}
 	return true;
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
<body>
<c:if test="${not empty msg}">
	<input type="hidden" name="msg" id="msg" value="${msg}" disabled="disabled"/>
</c:if>
<c:url value="updatecceUrl" var="updateUrl" />
	<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid"  target="result">
		<input type="hidden" name="updateid" id="updateid" value="0"/>
	</form:form>
	
<form:form name="editcceCmdForm" id="editcceCmdForm" action="cceEditAct" method='POST' commandName="editcceCmd">
   	<div class="container" align="center">
	        	<div class="card">
	          		<div class="card-header"><h5>Edit Complete Equipment Schedule</h5></div>
	          			<div class="card-body card-block cue_text">
	            			
	            			<div class="col-md-12">	            					
	              				<div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-6">
						                	<label for="text-input" class=" form-control-label">Type</label>
						                </div>
							              
							                <div class="col-12 col-md-6">
							                              <div class="form-check-inline form-check">
							                                <label for="inline-radio1" class="form-check-label">
							                                 <input id="ces_ccestype" name="ces_ccestype" type="hidden" value="${editcceCmd.ces_cces}">     
							                                  <input type="radio" id="inline-radio1" name="ces_cces" value="CES" class="form-check-input" disabled="disabled">CES
							                                </label>&nbsp;&nbsp;&nbsp;
							                                <label for="inline-radio2" class="form-check-label">
							                                  <input type="radio" id="inline-radio2" name="ces_cces" value="CCES" class="form-check-input" disabled="disabled">CCES
							                                </label>&nbsp;&nbsp;&nbsp;
							                                <label for="inline-radio3" class="form-check-label ">
							                                  <input type="radio" id="inline-radio3" name="ces_cces" value="ETS" class="form-check-input" disabled="disabled">ETS
							                                </label>&nbsp;&nbsp;&nbsp;                            
							                                
							                              </div>
							                              
							                            </div>					              
	                				</div>
	                			</div>
	                			<div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-6">
						                	<label for="text-input" class=" form-control-label">CES/CCES No</label>
						                </div>				                
							              
	                			<div class="col-12 col-md-6">
				                      <input id="id" type="hidden" name="id" value="${editcceCmd.id}">    
                 					<input  id="ces_cces_no" name="ces_cces_no" class="form-control" autocomplete="off" value="${editcceCmd.ces_cces_no}" readonly="readonly"> 
               					</div>
	                			</div>
	                			</div>
	                			</div>
	                			
	                			<div class="col-md-12">	
	                			<div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-6">
	                  						<label class=" form-control-label">Name of CES/CCES </label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                					<input  id="ces_cces_name1" class="form-control" autocomplete="off" readonly="readonly">
	                					</div>
	                					</div>
	              					</div>
	              					
	              					<div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-6">
	                  						<label class=" form-control-label">Item Code </label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                					<input class="form-control" id="cescodehidden" type="text" name="ces_cces_name" value="${editcceCmd.ces_cces_name}" readonly="readonly">
	                				</div>
	                					</div>
	              					</div>
	              					
	            			</div>
	            			<div class="col-md-12">		 
	  						<div class="col-md-6">
              					<div class="row form-group"> 
                					<div class="col col-md-6">                					
                  						<label for="text-input" class=" form-control-label">Effective Date</label>
                					</div>
                					<div class="col-12 col-md-6">
                						<input value="${editcceCmd.efctiv_date}" id="efctiv_date" name="efctiv_date" placeholder="" class="form-control" autocomplete="off" readonly="readonly">
									</div>
  								</div>
	  						</div>
	  						<div class="col-md-6">
  								<div class="row form-group">  								
                					<div class="col col-md-6">
                  						<label for="text-input" class=" form-control-label">Expiry Date</label>
                					</div>
                					<div class="col-12 col-md-6">
                  						<input  value="${editcceCmd.expiry_date}" id="expiry_date" name="expiry_date" placeholder="" class="form-control" autocomplete="off"  readonly="readonly">
									</div>
  								</div>
  							</div>	  								
	  					</div>
	            		<div class="col-md-12"><span class="line"></span></div>		
						<div class="col-md-12">	              					
	              			<div class="col-md-6">
	              			 	<div class="row form-group"> 
	                					<div class="col col-md-6">
	                  						<label for=" " class=" form-control-label">Nomenclature Stores <strong style="color: red;">*</strong></label>
	                					</div>
	                					<div class="col-12 col-md-6">
			                				<input  id="item_seq_no1" class="form-control" onchange="ccenameforseq(this.value);"  style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search">
			                				<input type="hidden" id="checkwe_pe_no" class="form-control" >
										</div>
	  								</div> 
	  						</div>
	  						<div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-6">
	                  						<label class=" form-control-label">Item Code </label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                					 	<input id="item_seq_nohidden" type="text" name="item_seq_no" maxlength="8"  value="${editcceCmd.item_seq_no}" class="form-control" readonly="readonly">
	                				    </div>
	                					</div>
	              					</div>	  						 
	  					</div>	  					
	  					<div class="col-md-12">	  								
	  						<div class="col-md-6">
	  							<div class="row form-group">
					                <div class="col col-md-6">
					                  <label class=" form-control-label">Proposed Auth <strong style="color: red;">*</strong></label>
					                </div>
					                <div class="col-12 col-md-6">
					               		<input  id="auth_proposed" name="auth_proposed" maxlength="8" class="form-control" value="${editcceCmd.auth_proposed}" 
					               		onkeypress="return isNumberKey(event,this);" onchange="return checkAuth_amtLength()">
					               	</div>
					            </div>	  								
	  						</div>
	  						<div class="col-md-6">
	  							<div class="row form-group">
					                <div class="col col-md-6">
					                  <label class=" form-control-label">Remarks</label>
					                </div>
					                <div class="col-12 col-md-6">
					                <textarea class="form-control char-counter1"  id ="remarks" maxlength="255" name="remarks">${editcceCmd.remarks}</textarea> 
					               
					                </div>
					            </div>	  								
	  						</div>	  						
	  					</div>	  		
	  		
	  		</div>
	  		<div class="card-footer" align="center">
				
	             <input type="submit" class="btn btn-primary btn-sm" value="Update" onclick="return existsave();">
				 <a href="searchccename" class="btn btn-danger btn-sm" onClick="javascript:window.close('','_parent','');">  Cancel </a>
	             
		    </div> 						
	  </div>
	 </div>
	
	
</form:form>
</body>
</html>