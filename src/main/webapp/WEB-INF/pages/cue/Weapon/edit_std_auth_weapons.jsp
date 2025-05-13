<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Standard Authorisation For Weapons</title>
</head>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/excluded/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 

<script type="text/javascript" src="js/miso/miso_js/jquery-1.12.3.js"></script>
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
<body>
<form:form action="standard_auth_for_weaponsEditAction" method="post" id="standard_auth_for_weaponsFORM" name="standard_auth_for_weaponsFORM" class="form-horizontal" commandName="stAuthWeaponsEditCMD"> 
   	<div class="container" align="center">
	        	<div class="card">
	          		<div class="card-header"><h5>Edit STANDARD AUTHORISATION FOR WEAPONS</h5></div>
	          			<div class="card-body card-block cue_text">
	            			
	            			<div class="col-md-12">	            					
	              				<div class="col-md-6">
	              					<div class="row form-group"> 
	                					<div class="col-12 col-md-6">
						                	<label class=" form-control-label">MISO WE/PE No</label>
						                </div>
										 <div class="col-12 col-md-6">
					                          <input type="hidden" id="id" name="id"  class="form-control" value="${stAuthWeaponsEditCMD.id}">
					                          <input  id="we_pe_no" name="we_pe_no"  class="form-control" autocomplete="off" onchange="getArmByWePeNo(this.value);" value="${stAuthWeaponsEditCMD.we_pe_no}" readonly="readonly"> 
					                     </div>					              
	                				</div>
	                			</div>
	                			<div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-6">
	                  						<label class=" form-control-label">Table Title</label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                						
	                  						<input id="table_title" class="form-control" autocomplete="off" disabled>
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
                						<input class="form-control" id="nomenClature" name="nomenClature" autocomplete="off" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search">
                					</div>
                				</div>
              				</div>				
	              			<div class="col-md-6">
	              			 	<div class="row form-group"> 
	                					<div class="col col-md-6">
	                  						<label  class=" form-control-label" >Item Code <strong style="color: red;">*</strong></label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                  						<input type="text" id="item_seq_no" name="item_seq_no" maxlength="8" class="form-control" value="${stAuthWeaponsEditCMD.item_seq_no}" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search">
										</div>
	  								</div> 
	  						</div>
	  					</div>
	  					<div class="col-md-12">		 
	  						<div class="col-md-6">
              					<div class="row form-group"> 
                					<div class="col col-md-6">
                  						<label for="text-input" class=" form-control-label">Authorisation <strong style="color: red;">*</strong></label>
                					</div>
                					<div class="col-12 col-md-6">
                  						<input  id="auth_weapon" name="auth_weapon" class="form-control"  maxlength="8" onkeypress="return isNumberKey(event,this);" onchange="return checkAuth_amtLength();" value="${stAuthWeaponsEditCMD.auth_weapon}">
									</div>
  								</div>
	  						</div>
  							<div class="col-md-6">
  								<div class="row form-group">  								
                					<div class="col col-md-6">
                  						<label for="text-input" class=" form-control-label">Remarks</label>
                					</div>
                					<div class="col-12 col-md-6">
                						<textarea  id="remarks" name="remarks" maxlength="255" class="form-control char-counter1" >${stAuthWeaponsEditCMD.remarks}</textarea>
									</div>
  								</div>
  							</div>
	  					</div>
	  		</div>
	  		   <div class="card-footer" align="center">
						<input type="submit" class="btn btn-primary btn-sm" value="Update" onclick="return isValid();">
	              		<a href="search_std_auth_weapon" class="btn btn-danger btn-sm" onClick="javascript:window.close('','_parent','');">  Cancel </a>
		      </div>  						
	  </div>
	 </div>
</form:form>
	
	<c:url value="updatePSawUrl" var="updateUrl" />
	<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid"  target="result"> 
		<input type="hidden" name="updateid" id="updateid" value="0"/>
	</form:form>
	
<script>
function isValid()
{
	if($("input#nomenClature").val() == "")
	{
		alert("Please enter Nomenclatue(Item Type)");
		$("input#nomenClature").focus();
		return false;
	}
	if($("input#item_seq_no").val() == "")
	{
		alert("Please enter Item Code");
		$("input#item_seq_no").focus();
		return false;
	}
	if($("input#auth_weapon").val() == "" ||$("input#auth_weapon").val() == "0" )
	{
		alert("Please enter Authorisation");
		$("input#auth_weapon").focus();
		return false;
	}
	
	return true;
}  

function checkAuth_amtLength() {
	var auth_weapon= $("input#auth_weapon").val();
	
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

function getArmByWePeNo(val)
{
	  if(val != "" && val != null)
	  {		
		  $.post("getDetailsWePeCondiNo?"+key+"="+value, {val : val}).done(function(j) {
			if(j!="" && j!=null)
				document.getElementById("tableTitle").value=j[0].table_title;
			else
				document.getElementById("tableTitle").value="";
		  }).fail(function(xhr, textStatus, errorThrown) { }); 	
	  }
	  else
	  {
		  alert("Please enter WE/PE No"); 
		  document.getElementById("tableTitle").value="";
	  }
} 
 

$(function() {
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
	        	  alert("Please Enter Approved Item Code");
	        	  wepetext2.val("");
	        	  document.getElementById("nomenClature").value="";       	 
	        	  wepetext2.focus();
	        	  return false;	             
	          }   	         
	      }, 
	        select: function( event, ui ) {
	        	$(this).val(ui.item.value);    	        	
	        	$.post("getitemnamefromcode?"+key+"="+value, {val : ui.item.value}).done(function(j) {
	           		document.getElementById("nomenClature").value=j;	
		        	 }).fail(function(xhr, textStatus, errorThrown) { });    	
	        }  	     
	    });
	  
	  
	  $.ajaxSetup({
 	    async: false
 	});  
	  
	  var wepetext1=$("#nomenClature");     
	
	  wepetext1.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
        	type: 'POST',
 	        url: "getitemtype?"+key+"="+value,
	        data: {item_type:document.getElementById('nomenClature').value},
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
	    		 var val = this.value; 	            	
	        	  return true;    	            
	          } else {
	        	  alert("Please Enter Approved Nomenclature");
	        	  wepetext1.val("");
	        	  $("input#std_nomclature").val("");
	        	  document.getElementById("item_seq_no").value="";
	        	 
	        	  wepetext1.focus();
	        	  return false;	             
	          }   	         
	      }, 
	      select: function( event, ui ) {
	        	$(this).val(ui.item.value);    	        	
	        	$.post("getitemcode?"+key+"="+value, {val : ui.item.value}).done(function(j) {
           			document.getElementById("item_seq_no").value=j;	
	        	 }).fail(function(xhr, textStatus, errorThrown) { });     	
	        }  	     
	    });
    
  }); 
function getItemCode(val)
{
	  if(val != "" && val != null)
	  {
		  
		$.post("getitemcode?"+key+"="+value, {val : ui.item.value}).done(function(j) {
			 if(j!="" && j!= null)
				document.getElementById("item_seq_no").value=j;
			 else
				 document.getElementById("item_seq_no").value="";	  				
		 }).fail(function(xhr, textStatus, errorThrown) { }); 	
	  }
	  else
	  {
		  alert("Please enter Item Name");
		  document.getElementById("item_seq_no").value="";
	  }
} 	 
	  	
</script>

<script>

$(document).ready(function() {
	
	$('#remarks').keyup(function() {
	        this.value = this.value.toUpperCase();
	    });
	
	var we_pe_no = $("input#we_pe_no").val();	
 	

	$.post("getDetailsWePeCondiNo?"+key+"="+value, {we_pe_no : we_pe_no}).done(function(j) {
     	$("input#table_title").val(j);	
	 }).fail(function(xhr, textStatus, errorThrown) { }); 	
	
		if( document.getElementById("item_seq_no").value!=null &&  document.getElementById("item_seq_no").value!="")
			{
			 
			var val =document.getElementById("item_seq_no").value;
			$.post("getitemnamefromcode?"+key+"="+value, {val : val}).done(function(j) {
 				document.getElementById("nomenClature").value=j;  				 
			 }).fail(function(xhr, textStatus, errorThrown) { }); 
			}
		
		document.getElementById("auth_weapon").value=parseFloat(document.getElementById("auth_weapon").value);
		
		 try{
			  if(window.location.href.includes("?count="))
	   			{
	   				var id= window.location.href.split("&updateid=")[1].split("&msg=")[0];
					
					 document.getElementById('updateid').value=id;
			 		 document.getElementById('updateForm').submit();			
	   			}
			}
			catch (e) {
				// TODO: handle exception
			}
			
	});

</script>
  <script>	
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
 
</body>
</html>
