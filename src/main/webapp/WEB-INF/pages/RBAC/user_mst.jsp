<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 

<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>


	<form:form name="user_mst" id="user_mst" action="user_mstAction" method='POST' commandName="user_mstCMD">
		<div class="container" align="center">
    	   	<div class="card" >
       	 		<div class="card-header"> <h5>User Master</h5></div>
         		<div class="card-body card-block" >
        				<div class="col-md-12"  >
         				<div class="col-md-6" >
 							<div class="row form-group"> 
               					<div class="col col-md-6">
                 					<label for="text-input" >User Name  <strong style="color: red;">*</strong> </label> 
               					</div>
               					<div class="col-12 col-md-6">
                 					<input type="text" id="login_name" name="login_name" maxlength="70" style="font-family: 'FontAwesome',Arial;"  class="form-control" autocomplete="off" required>
								</div>
 							</div>
 						</div> 
 						
						</div>
        				<div class="col-md-12"  >
         				<div class="col-md-6" >
 							<div class="row form-group"> 
               					<div class="col col-md-6">
                 					<label for="text-input" >User ID  <strong style="color: red;">*</strong> </label> 
               					</div>
               					<div class="col-12 col-md-6">
                 					<input type="text" id="user_name" name="user_name" maxlength="30" class="form-control" autocomplete="off" required>
								</div>
 							</div>
 						</div> 
 					</div>
 					<div class="col-md-12"  >
         				<div class="col-md-6" >
 							<div class="row form-group"> 
               					<div class="col col-md-6">
                 					<label for="text-input" >Army No  <strong style="color: red;">*</strong> </label> 
               					</div>
               					<div class="col-12 col-md-6">
                 					<input type="text" id="army_no" name="army_no" maxlength="9"  onkeyup="this.value = this.value.toUpperCase();" placeholder="Ex. IC123456A" class="form-control" autocomplete="off" required>
								</div>
 							</div>
 						</div> 
 					</div>
					<div class="col-md-12">
						<div class="col-md-6">
 							<div class="row form-group"> 
               					<div class="col col-md-6">
                 					<label for="text-input" >Password  <strong style="color: red;">*</strong> </label> 
               					</div>
               					<div class="col-11 col-md-6">
                 					<input id="user_password" type="password" maxlength="28" name="user_password"  class="form-control" autocomplete="off" required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" >
								</div>
 							</div>
 						</div>	 					
 					</div>
 					<div class="col-md-12">
 						<div class="col-md-6">
 							<div class="row form-group"> 
               					<div class="col col-md-6">
                 					<label for="text-input" >Re-Password <strong style="color: red;">*</strong> </label> 
               					</div>
               					<div class="col-12 col-md-6">
                 					<input  id="user_re_password" type="password" maxlength="28"  name="user_re_password"   class="form-control" autocomplete="off" required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" > <!--  -->
 								</div>
 							</div> 
						</div>
 					</div>
						<div class="col-md-12">
							<div class="col-md-6">
 							<div class="row form-group"> 
               					<div class="col col-md-6">
                 					<label for="text-input"  >Role <strong style="color: red;">*</strong> </label> 
               					</div>
               					<div class="col-12 col-md-6">
                 					<select name="user_role_id" class="form-control" id ="user_role_id" onchange="getaccess_lev(this.value);">
                 						<option value="0">--Select--</option>
               							<c:forEach var="item" items="${getRoleNameList}" varStatus="num" >
               								<option value="${item.roleId}">${item.role}</option>
               							</c:forEach>					                
				                  	</select>
								</div> 
 							</div>
 						</div> 
 					</div>
 					<div class="col-md-12" id="sus_div" style="display: none">
 						 <div class="col-md-6">
 							<div class="row form-group"> 
               					<div class="col col-md-6">
                 					<label for="text-input" >SUS No</label> 
               					</div>
               					<div class="col-12 col-md-6">
                 					<input name="user_sus_no" class="form-control" id ="user_sus_no" maxlength="8"  style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search"  autocomplete="off" >					                
 								</div>
 							</div>  
						</div>
						 <div class="col-md-6">
 							<div class="row form-group"> 
               					<div class="col col-md-4">
                 					<label for="text-input" >Unit Name</label> 
               					</div>
               					<div class="col-12 col-md-8">
                 					<textarea name="unit_name" class="form-control" id="unit_name" maxlength="100" autocomplete="off" ></textarea>					                
 								</div>
 							</div>  
						</div>
 					</div>
 					<%-- <div class="col-md-12" id="arm_div" style="display: none">
	 					<div class="col-md-6">
	 						<div class="row form-group"> 
	               				<div class="col col-md-6">
	                 				<label for="text-input" >Arm </label> 
	               				</div>
	               				<div class="col-12 col-md-6">
	                 				<select name="user_arm_code" class="form-control" id ="user_arm_code" >
                							<option value="0">--Select--</option>
           									<c:forEach var="item" items="${getUserarm_codeList}" varStatus="num" >
           										<option value="${item.arm_code}">${item.arm_desc}</option>
           									</c:forEach>
               						</select>					                
	 							</div>
	 						</div>  
 						</div>
 					</div> --%>
 					<div class="col-md-12" align="left">
                   		<label for="passid">
                   			<b>1) Password should be a mix of alphabets, numerals and special characters ($#^@&%_.~!*) without any space in between.</b><br>
							<b>2) Password must contain both upper and lowercase letters.</b><br>
							<b>3) Password length should be between 8 to 28 characters.</b>
						</label>
					</div>	
				</div>
        			<div class="card-footer" align="center">
        	            <input type="reset" class="btn btn-success btn-sm" value="Clear" onclick="clearall();">    	
              		<input type="submit" class="btn btn-primary btn-sm" value="Save" onclick="return isValid();">
              	</div>
        	    <input id="access_lve" name="access_lve1" type="hidden" />
	 			<input id="sub_access_lve" name="sub_access_lve1"  type="hidden" /> 		 	
 				<input id="formation_code" name="user_formation_no"  type="hidden"/>
 			</div>
		</div>
	</form:form>
 
<script>
	function isValid()
	{	
		
		if($("#login_name").val()==""){
			alert("Please Enter User Name");
			$("#login_name").focus();
			return false;
		} 
		if($("#user_name").val()==""){
			alert("Please Enter User ID");
			$("#user_name").focus();
			return false;
		} 
		if($("#army_no").val()==""){
			alert("Please Enter Army No");
			$("#army_no").focus();
			return false;
		}
		if($("#user_password").val()==""){
			alert("Please Enter User Password");
			$("#user_password").focus();
			return false;
		} 
		if($("#user_password").val().length < 8 | $("#user_password").val().length > 28){
			alert("Please Enter Password at least 8 to 28 digit");
			$("#user_password").focus();
			return false;
		} 
		if($("#user_re_password").val()==""){
			alert("Please Enter User Re-Password");
			$("#user_re_password").focus();
			return false;
		} 
		if($("#user_re_password").val().length < 8 | $("#user_re_password").val().length > 28){
			alert("Please Enter Re-Password at least 8 to 28 digit");
			$("#user_re_password").focus();
			return false;
		}
		if($("select#user_role_id").val()=="0" || $("select#user_role_id").val()==""){
			alert("Please Select Role Id");
			$("select#user_role_id").focus();
			return false;
		}
		return true;
	} 

	$(document).ready(function () {
		$("input#login_name").val("");
		$("#user_name").val("");
		$("#army_no").val("");
		$("#user_password").val("");
		$("#user_re_password").val("");
	});

   var access_lvl,sub_access_lvl,role_id;
   
   function getaccess_lev(val) {
		$("#user_sus_no").val("");
		document.getElementById('sub_access_lve').value="";
		document.getElementById('formation_code').value=""; 
	 	role_id = val;
		<c:forEach var="item" items="${getRoleNameList}" varStatus="num" >
			if('${item.roleId}' == val){		
				$("#access_lve").val('${item.access_lvl}');
				$("#sub_access_lve").val('${item.sub_access_lvl}');
				access_lvl = '${item.access_lvl}';
				sub_access_lvl = '${item.sub_access_lvl}';
			}
		</c:forEach>
		abd();
	}
   
	function abd() {
		//alert(access_lvl +"=="+sub_access_lvl);
		if((access_lvl != "" ) || (access_lvl != '' )){
			if(access_lvl == 'MISO' ||  (access_lvl == 'Line_dte' && sub_access_lvl == 'Staff') || access_lvl == 'CTPartI' ||  access_lvl == 'CTPartII' ||  access_lvl == 'HeadQuarter' ){
				document.getElementById('sus_div').style.display='none';
				document.getElementById('sub_access_lve').value="";
				document.getElementById('formation_code').value=""; 			
			} else{
				document.getElementById('sus_div').style.display='block';
				var user_sus_no_autocomplete=$("#user_sus_no");
				user_sus_no_autocomplete.autocomplete({
				      source: function( request, response ) {
				        $.ajax({
				        type: 'POST',
				        url: "getusersusnoList?"+key+"="+value,
				        data: {role_id:role_id,sus_no:$("#user_sus_no").val()},
				          success: function( data ) {				        	
				        	var codeval = [];
				        	for(var i = 0;i<data.length;i++){
					        	codeval.push(data[i]);
					        } 
							response( codeval );  
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
				        	  $("#unit_name").val("");
				        	  $("#user_sus_no").val("");
				        	  return false;	             
				          }   	         
				      }, 
				    select: function( event, ui ) {
				 		//$(this).val(ui.item.value);  
				 		var user_sus_no = ui.item.value;
				 		$.post("getActiveUnitNameFromSusNo?"+key+"="+value,{sus_no:user_sus_no}, function(j) {
    			       		var length = j.length-1;
    						var enc = j[length].substring(0,16);
    				   		$("#unit_name").val(dec(enc,j[0]));
						});
					}
				});
				
				// unit Name Autocompalete
				var user_unit_name_autocomplete=$("#unit_name");
				user_unit_name_autocomplete.autocomplete({
				      source: function( request, response ) {
				        $.ajax({
				        type: 'POST',
				        url: "getuserunit_nameList?"+key+"="+value,
				        data: {role_id:role_id,unit_name:$("#unit_name").val()},
				          success: function( data ) {				        	
				        	var codeval = [];
				        	for(var i = 0;i<data.length;i++){
					        	codeval.push(data[i]);
					        } 
							response( codeval );  
						  }
				        });
				      },
				      minLength: 1,
				      autoFill: true,
				      change: function(event, ui) {
				    	 if (ui.item) { 
				    		 return true;    	            
				          } else {
				        	  alert("Please Enter Approved Unit name."); 
				        	  $("#unit_name").val("");
				        	  $("#user_sus_no").val("");
				        	  return false;	             
				          }   	         
				      }, 
				    select: function( event, ui ) {
				 		//$(this).val(ui.item.value);  
				 		var unitName = ui.item.value;
				 		$.post("getActiveSusNoFromUnitName?"+key+"="+value,{unit_name:unitName}, function(j) {
		   	        		if(j.length != 0){
		   	        			var length = j.length-1;
								var enc = j[length][0].substring(0,16);
								$("#user_sus_no").val(dec(enc,j[0][0]));
		   	        		}else{
		   	        			$("#unit_name").val("");
		   	        		}
		   	        	});
		 	       	} 
				});
		  	}
	    }
	   	else{
			alert("Access level is not defined.");
	   	}  
   	}
</script>