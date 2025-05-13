<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>

<form:form id="user_regitrastion_update">
	<div class="container" align="center" id="proposaldetails_div">
		<div class="card">
			<div class="card-header">
				<h5>VIEW USER REGISTRATION</h5>
			</div>
			<div class="card-body card-block ">
				<div class="col-md-12">
					<div class="col-md-2"></div>
					<div class="col-md-8">
						<div class="row form-group">
							<div class="col col-md-6">
								<label for="text-input">User Name </label>
							</div>
							<div class="col-12 col-md-6">
								${l.user_name}
								<input type="hidden" id="id" name="id" class="form-control" value="${l.id}" autocomplete="off">
							</div>
						</div>
					</div>
					<div class="col-md-2"></div>
				</div>
				<div class="col-md-12">
					<div class="col-md-2"></div>
					<div class="col-md-8">
						<div class="row form-group">
							<div class="col col-md-6">
								<label for="text-input">Unit Name</label>
							</div>
							<div class="col-12 col-md-6">
								${l.unit_name}
							</div>
						</div>
					</div>
					<div class="col-md-2"></div>
				</div>
				<div class="col-md-12">
					<div class="col-md-2"></div>
					<div class="col-md-8">
						<div class="row form-group">
							<div class="col col-md-6">
								<label for="text-input">SUS No.</label>
							</div>
							<div class="col-12 col-md-6">
								${l.sus_no}
							</div>
						</div>
					</div>
					<div class="col-md-2"></div>
				</div>
				<div class="col-md-12">
					<div class="col-md-2"></div>
					<div class="col-md-8">
						<div class="row form-group">
							<div class="col col-md-6">
								<label for="text-input">IC No.</label>
							</div>
							<div class="col-12 col-md-6">
								${l.army_no}
							</div>
						</div>
					</div>
					<div class="col-md-2"></div>
				</div>
				<div class="col-md-12">
					<div class="col-md-2"></div>
					<div class="col-md-8">
						<div class="row form-group">
							<div class="col col-md-6">
								<label for="text-input">Rank</label>
							</div>
							<div class="col-12 col-md-6">
								${l.rank}
							</div>
						</div>
					</div>
					<div class="col-md-2"></div>
				</div>
				<div class="col-md-12">
					<div class="col-md-2"></div>
					<div class="col-md-8">
						<div class="row form-group">
							<div class="col col-md-6">
								<label for="text-input">Mobile No<strong style="color: red;"></strong> </label>
							</div>
							<div class="col-12 col-md-6">
								${l.mobile_no}
							</div>
						</div>
					</div>
					<div class="col-md-2"></div>
				</div>
				<div class="col-md-12">
					<div class="col-md-2"></div>
					<div class="col-md-8">
						<div class="row form-group">
							<div class="col col-md-6">
								<label for="text-input">Download User Regn Form <br><span style="font-size: 10px;color: red;">(Counter sign by CO)</span></label>
							</div>
							<div class="col-12 col-md-6">
								<a href='#' onclick='getDownloadAttachment()' class='btn btn-primary btn-sm'><i class='fa fa-download' aria-hidden='true'></i></a>
							</div>
						</div>
					</div>
					<div class="col-md-2"></div>
				</div>
				<div class="col-md-12">
					<div class="col-md-2"></div>
					<div class="col-md-8">
						<div class="row form-group">
							<div class="col col-md-6">
								<label for="text-input">Appointment</label>
							</div>
							<div class="col-12 col-md-6">
								${l.appointment}
							</div>
						</div>
					</div>
					<div class="col-md-2"></div>
				</div>
				<div class="col-md-12">
					<div class="col-md-2"></div>
					<div class="col-md-8">
						<div class="row form-group">
							<div class="col col-md-6">
								<label for="text-input" >User ID  <strong style="color: red;">*</strong> </label> 
							</div>
							<div class="col-12 col-md-6">
								<input type="text" id="user_id" name="user_id" maxlength="30" class="form-control" autocomplete="off" >
							</div>
						</div>
					</div>
					<div class="col-md-2"></div>
				</div>
				<div class="col-md-12">
					<div class="col-md-2"></div>
					<div class="col-md-8">
						<div class="row form-group">
							<div class="col col-md-6">
								<label for="text-input" >Password  <strong style="color: red;">*</strong> </label> 
							</div>
							<div class="col-12 col-md-6">
								<input id="user_password" type="password" maxlength="28" name="user_password"  class="form-control" autocomplete="off" required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" >
							</div>
						</div>
					</div>
					<div class="col-md-2"></div>
				</div>
				<div class="col-md-12">
					<div class="col-md-2"></div>
					<div class="col-md-8">
						<div class="row form-group">
							<div class="col col-md-6">
								<label for="text-input" >Re-Password <strong style="color: red;">*</strong> </label> 
							</div>
							<div class="col-12 col-md-6">
								<input  id="user_re_password" type="password" maxlength="8"  name="user_re_password"   class="form-control" autocomplete="off" required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" >
							</div>
						</div>
					</div>
					<div class="col-md-2"></div>
				</div>
				<div class="col-md-12">
					<div class="col-md-2"></div>
					<div class="col-md-8">
						<div class="row form-group">
							<div class="col col-md-6">
								<label for="text-input">Role <strong style="color: red;">*</strong> </label> 
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
					<div class="col-md-2">
						<input id="access_lve" name="access_lve1" type="hidden" />
	 					<input id="sub_access_lve" name="sub_access_lve1"  type="hidden" />
 						<input id="formation_code" name="user_formation_no"  type="hidden"/>
					</div>
				</div>
			</div>
			<div class="card-footer" align="center">
				
				<a href="Search_User_regUrl" class="btn btn-danger btn-sm"> Close </a>
				<input type="submit" class="btn btn-primary btn-sm" value="Update" onclick="return user_regitrastion_updateFN();">
			</div>
		</div>
	</div>
</form:form>
	
<c:url value="getDownloadAttachmentURl" var="imageDownloadUrl" />
<form:form action="${imageDownloadUrl}" method="post" id="getDownloadAttachmentForm" name="getDownloadAttachmentForm" modelAttribute="id">
	<input type="hidden" name="id" id="id" value="${l.id}"/>
</form:form>

<script>
	function getDownloadAttachment(){
		document.getElementById("getDownloadAttachmentForm").submit();
	}
	function user_regitrastion_updateFN() {
		if($("#user_id").val()==""){
			alert("Please Enter User ID");
			$("#user_id").focus();
			return false;
		} 
		else if($("#user_id").val()==""){
			alert("Please Enter User ID");
			$("#user_id").focus();
			return false;
		} 
		else if($("#user_password").val()==""){
			alert("Please Enter User Password");
			$("#user_password").focus();
			return false;
		} 
		else if($("#user_password").val().length < 8 | $("#user_password").val().length > 28){
			alert("Please Enter Password at least 8 to 28 digit");
			$("#user_password").focus();
			return false;
		} 
		else if($("#user_re_password").val()==""){
			alert("Please Enter User Re-Password");
			$("#user_re_password").focus();
			return false;
		} 
		else if($("#user_re_password").val().length < 8 | $("#user_re_password").val().length > 28){
			alert("Please Enter Re-Password at least 8 to 28 digit");
			$("#user_re_password").focus();
			return false;
		}
		else if($("select#user_role_id").val()=="0" || $("select#user_role_id").val()==""){
			alert("Please Select Role Id");
			$("select#user_role_id").focus();
			return false;
		}else{
			var id = $('#id').val();
			var formdata = $('#user_regitrastion_update').serialize();
			formdata += "&id=" + id;
			$.post('user_regitrastionUpdate_action?' + key + "=" + value,formdata,function(data) {
				alert(data);
				if(data == "Data updated Successfully"){
					window.location.href='Search_User_regUrl';
				}else{
					return false;
				}
			}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")});
		}
		return false;
	}
	
	var access_lvl,sub_access_lvl,role_id;
  	function getaccess_lev(val) {
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
		if((access_lvl != "" ) || (access_lvl != '')){
			if(access_lvl == 'MISO' ||  (access_lvl == 'Line_dte' && sub_access_lvl == 'Staff') || access_lvl == 'CTPartI' ||  access_lvl == 'CTPartII' ||  access_lvl == 'HeadQuarter' ){
				document.getElementById('sub_access_lve').value="";
				document.getElementById('formation_code').value=""; 			
			} else{
				
		  	}
	    }
	   	else{
			alert("Access level is not defined.");
	   	}  
   	}
</script>