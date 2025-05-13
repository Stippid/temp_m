<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script type="text/javascript" src="js/amin_module/rbac/jquery-1.12.3.js"></script>
<!-- <script type="text/javascript" src="js/cue/cue.js"></script> -->
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<style type="text/css">
.cue_text input, textarea {
	text-transform: unset;
}

.btn-group-sm>.btn, .btn-sm {
	font-size: 12px;
	line-height: 1.5;
}

.glyphicon-refresh::before {
	content: "\e031";
}

.glyphicon {
	font-family: 'Glyphicons Halflings';
	font-style: normal;
	font-weight: 400;
	line-height: 1;
}
</style>
<script type="text/javascript">
	window.history.forward();
	function noBack() {
		window.history.forward();
	}
	$(document).ready(function () {
	    $('body').bind('cut copy paste', function (e) {
    	    e.preventDefault();
    	});
   	$("body").on("contextmenu",function(e){
    	return false;
    });
});
</script>
<body>
	<form:form name="edit_user_mst" action="edit_User_Mst_Action"
		method='POST' commandName="edit_User_MstCMD">
		<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<h5>Edit User Master</h5>
				</div>
				<div class="card-body card-block cue_text">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
									<label for="text-input">User Name <strong
										style="color: red;">*</strong>
									</label>
								</div>
								<div class="col-12 col-md-6">
									<input type="hidden" id="userId" name="userId"
										value="${edit_User_MstCMD.userId}"> <input
										id="login_name" name="login_name"
										style="font-family: 'FontAwesome', Arial;"
										value="${edit_User_MstCMD.login_name}" class="form-control"
										autocomplete="off" maxlength="70">
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
									<label for="text-input">User ID <strong
										style="color: red;">*</strong>
									</label>
								</div>
								<div class="col-12 col-md-6">
									<input type="hidden" id="user_name" name="user_name"
										value="${edit_User_MstCMD.userName}"> <input id="user"
										name="user" style="font-family: 'FontAwesome', Arial;"
										readonly="readonly" value="${edit_User_MstCMD.userName}"
										maxlength="30" class="form-control" autocomplete="off"
										required>
								</div>
							</div>
						</div>

					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
									<label for="text-input">Army No <strong
										style="color: red;">*</strong>
									</label>
								</div>
								<div class="col-12 col-md-6">
									<input type="text" id="army_no" name="army_no" maxlength="9"
										onkeyup="this.value = this.value.toUpperCase();"
										placeholder="Ex. IC123456A" class="form-control"
										autocomplete="off" value="${edit_User_MstCMD.army_no}"
										required>
								</div>
							</div>
						</div>
						</div>
						
						
						<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
								<label for="text-input">Name 
									</label>
								</div>
								<div class="col-12 col-md-6">
								<input type="hidden" id="username1" name="username1"
										value='${username1}'>
									<input type="text" id="name" name="name" maxlength="9"
										onkeyup="this.value = this.value.toUpperCase();"
										 class="form-control"
										autocomplete="off" 
										>
								</div>
							</div>
						</div>

					</div>
						<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
								<label for="text-input">Rank </label>
								</div>
									
								  <div class="col-12 col-md-6">
						                   <select name="rank" id="rank" class="form-control-sm form-control"   >
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getTypeofRankList}" varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
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
									<label for="text-input">Password <strong
										style="color: red;">*</strong>
									</label>
								</div>
								<div class="col-12 col-md-6">
									<input id="user_password" type="password" name="password"
										pattern="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[$#^@&%_.~!*](?!.*\s).{8,28}"
										class="form-control" value="${password}" autocomplete="off"
										title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters">
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
									<label for="text-input">Re-Password <strong
										style="color: red;">*</strong>
									</label>
								</div>
								<div class="col-12 col-md-6">
									<input id="user_re_password" type="password" name="re_password"
										pattern="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[$#^@&%_.~!*](?!.*\s).{8,28}"
										class="form-control" value="${password}" autocomplete="off"
										required>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
									<label for="text-input">Role <strong
										style="color: red;">*</strong>
									</label>
								</div>

								<div class="col-12 col-md-6">
									<input type="hidden" id="role1" name="role1" class="form-control" value="${getRole[0].role}" autocomplete="off" required> 
									<select name="user_role_id" class="form-control" id="user_role_id" onchange="getaccess_lev(this.value);">
										<c:forEach var="item" items="${getRoleNameList}"
											varStatus="num">
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
									<label for="text-input">SUS No</label>
								</div>
								<div class="col-12 col-md-6">
									<input name="user_sus_no" class="form-control" id="user_sus_no" maxlength="8" autocomplete="off">
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
					<div class="col-md-12" id="arm_div" style="display: none">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
									<label for="text-input">Arm </label>
								</div>
								<div class="col-12 col-md-6">
									<select name="user_arm_code" class="form-control" id="user_arm_code">
										<!-- <option value="0">--Select--</option> -->
										<c:forEach var="item" items="${getUserarm_codeList}" varStatus="num">
											<option value="${item.arm_code}">${item.arm_desc}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12" align="left">
					<label for="passid"> 
						<b>1) Password should be a mix of alphabets, numerals and special characters ( $#^@\&%_.~!*) without any space in between.</b><br> 
						<b>2) Password must contain both upper and lowercase letters.</b><br> 
						<b>3) Password length should be between 8 to 28 characters.</b>
					</label>
				</div>
				<div class="card-footer" align="center">
					<input type="submit" class="btn btn-primary btn-sm" value="Update" onclick="return isValid();">
					<a href="search_user_mstUrl" class="btn btn-danger btn-sm" onClick="javascript:window.close('','_parent','');"> Cancel </a>
				</div>
				<input id="access_lve" name="access_lve1" type="hidden" /> 
				<input id="sub_access_lve" name="sub_access_lve1" type="hidden" /> 
				<input id="formation_code" name="user_formation_no" type="hidden" />
			</div>
		</div>
	</form:form>
<script>
$(document).ready(function (){
	var a=$("input#user_name").val();
	var role= $("input#role1").val();
    var sList = new Array();
    
    if('${edit_User_MstCMD.user_sus_no}' != ""){
    	$.post("getActiveUnitNameFromSusNo?"+key+"="+value,{sus_no:'${edit_User_MstCMD.user_sus_no}'}, function(j) {
       		var length = j.length-1;
			var enc = j[length].substring(0,16);
			$("#user_sus_no").val('${edit_User_MstCMD.user_sus_no}');
	   		$("#unit_name").val(dec(enc,j[0]));
	   	});
    }
    
    var options = '<option value="'+"0"+'">'+ "--Select--" + '</option>';
	<c:forEach var="item" items="${getRoleNameList}" varStatus="num" >
		if('${item.role}' == role.trim()){
			options += '<option value="${item.roleId}" selected >${item.role}</option>';
			getaccess_lev('${item.roleId}');
		}else{
			options += '<option value="${item.roleId}" >${item.role }</option>';
		}
	</c:forEach>
	$("select#user_role_id").html(options); 
	
 var username1 = '${username1}';
	// alert(username1);
	 $.post('GetUsernameandRank?' + key + "=" + value,{ username1 : username1 },function(k) {
		  
   		 if(k.length > 0){
	   			$('#name').val(k[0][0]); 
	   			$('#rank').val(k[0][1]);	   			
	   						   			
   		 }					   							   		 
      });
});
function isValid(){
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
	if($("#user_password").val()==""){
		alert("Please Enter User Password");
		$("#user_password").focus();
		return false;
	}
	if($("#user_re_password").val()==""){
		alert("Please Enter  Re-Password");
		$("#user_re_password").focus();
		return false;
	}
	if($("#user_password").val()!=$("#user_re_password").val()){
		alert("Password and Re-Password didn't match");
		$("#user_re_password").focus();
		return false;
	}
	if($("select#user_role_id").val()=="0"){
		document.getElementById('arm_div').style.display='none';
		document.getElementById('sus_div').style.display='none';
		alert("Please Select Role Id");
		$("select#user_role_id").focus();
		return false;
	} 
	return true;
} 
</script>
<script>
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
		if ((access_lvl != "") || (access_lvl != '')) {
			if (access_lvl == 'MISO' || sub_access_lvl == 'Staff' || access_lvl == 'CTPartI' || access_lvl == 'CTPartII') {
				document.getElementById('arm_div').style.display = 'none';
				document.getElementById('sus_div').style.display = 'none';
				document.getElementById('sub_access_lve').value = "";
				document.getElementById('formation_code').value = "";
			} else if (sub_access_lvl == "Arm") {
				document.getElementById('arm_div').style.display = 'block';
				document.getElementById('sus_div').style.display = 'block';
				document.getElementById('formation_code').value = "";
				getarm();
			} else {
				document.getElementById('arm_div').style.display = 'none';
				document.getElementById('sus_div').style.display = 'block';
				var wepetext = $("#user_sus_no");
				wepetext.autocomplete({
					source : function(request, response) {
						$.ajax({
							type : 'POST',
							url : "getusersusnoList?" + key + "=" + value,
							data : {
								role_id : role_id,
								sus_no : $("#user_sus_no").val()
							},
							success : function(data) {
								var codeval = [];
								var length = data.length - 1;
								var enc = data[length].substring(0, 16);
								for (var i = 0; i < data.length; i++) {
									codeval.push(data[i]);
								}
								response(codeval);
							}
						});
					},
					minLength : 1,
					autoFill : true,
					change : function(event, ui) {
						if (ui.item) {
							return true;
						} else {
							alert("Please Enter Approved Unit SUS No.");
							$("#unit_name").val("");
				        	$("#user_sus_no").val("");
							wepetext.focus();
							return false;
						}
					},
					select : function(event, ui) {
						var user_sus_no = ui.item.value;
				 		$.post("getActiveUnitNameFromSusNo?"+key+"="+value,{sus_no:user_sus_no}, function(j) {
    			       		var length = j.length-1;
    						var enc = j[length].substring(0,16);
    				   		$("#unit_name").val(dec(enc,j[0]));
    				   	});        	
					}
				});

				// unit Name Autocompalete
				var user_unit_name_autocomplete = $("#unit_name");
				user_unit_name_autocomplete.autocomplete({
					source : function(request, response) {
						$.ajax({
							type : 'POST',
							url : "getuserunit_nameList?" + key + "="
									+ value,
							data : {
								role_id : role_id,
								unit_name : $("#unit_name").val()
							},
							success : function(data) {
								var codeval = [];
								for (var i = 0; i < data.length; i++) {
									codeval.push(data[i]);
								}
								response(codeval);
							}
						});
					},
					minLength : 1,
					autoFill : true,
					change : function(event, ui) {
						if (ui.item) {
							return true;
						} else {
							alert("Please Enter Approved Unit name.");
							$("#unit_name").val("");
							$("#user_sus_no").val("");
							return false;
						}
					},
					select : function(event, ui) {
						//$(this).val(ui.item.value);  
						var unitName = ui.item.value;
						$.post("getActiveSusNoFromUnitName?" + key + "="
								+ value, {
							unit_name : unitName
						}, function(j) {
							if (j.length != 0) {
								var length = j.length - 1;
								var enc = j[length][0].substring(0, 16);
								$("#user_sus_no").val(dec(enc, j[0][0]));
							} else {
								$("#unit_name").val("");
							}
						});
					}
				});
			}
		} else {
			alert("Access level is not defined.");
		}
	}
	function getarm() {
		var options = '<option value="'+"0"+'">' + "--Select--" + '</option>';
		<c:forEach var="item" items="${getUserarm_codeList}">
			if ('${item.arm_code}' == '${edit_User_MstCMD.user_Arm_code}') {
				options += '<option value="${item.arm_code}" selected >${item.arm_desc}</option>';
			} else {
				options += '<option value="${item.arm_code}" >${item.arm_desc }</option>';
			}
		</c:forEach>
		$("select#user_arm_code").html(options);
	}
	</script>
</body> 