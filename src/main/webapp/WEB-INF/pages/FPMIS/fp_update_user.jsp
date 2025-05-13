<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %>
<%@ taglib prefix="dandelion" uri="http://github.com/dandelion" %>

<script type="text/javascript" src="js/amin_module/rbac/jquery-1.12.3.js"></script> 
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<link rel="stylesheet" href="js/common/nrcss.css">
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>
<script src="js/cue/printAllPages.js" type="text/javascript"></script> 
<script>
	var role = "${role}";
</script>

<body onload="setMenu();">
<form:form name="search_user_mst" id="search_user_mst" class="fp-form" action="fp_update_user?${_csrf.parameterName}=${_csrf.token}" method='POST'>
	<c:if test="${data.size() gt 0}">
		<c:set var="user_id" value="${data[0][1]}"></c:set>
		<c:set var="u_id" value="${data[0][0]}"></c:set>
	</c:if>
	
	<input type="hidden" class="input" name="u_id" id="u_id" value="${u_id}"/>
	
	<div class="container" align="center">
    	<div class="card" >
        	<div class="mms-card-header"><h5>Update User Details</h5></div>
         	<div class="card-body card-block ncard-body-bg" >
	 			<div class="col-md-12">
	         		<div class="col-md-6" >
	 					<div class="row form-group"> 
	               			<div class="col col-md-6">
	                 			<label for="text-input" >User ID<strong style="color: red;">*</strong></label> 
	               			</div>
	               			<div class="col-12 col-md-6">
	                 			<input id="user_id" value="${user_id}" class="form-control" name="user_id"  maxlength="70" style="font-family: 'FontAwesome',Arial;"  placeholder="&#xF002; Search"  autocomplete="off">

							</div>
	 					</div>
	 				</div>
	 				
	 				<c:if test="${data.size() gt 0}">
		 				<div class="col-md-6" >
			 				<div class="row form-group"> 
			               		<div class="col col-md-6">
			                		<label for="text-input" >Army No<strong style="color: red;">*</strong></label> 
			               		</div>
			               		<div class="col-12 col-md-6">
			                		<input id="army_no" value="${data[0][3]}" class="form-control input" name="army_no"  maxlength="10">
								</div>
			 				</div>
			 			</div>
		 			</c:if>
 				</div>
 				
 				<c:if test="${data.size() gt 0}">	 				
	 				<div class="col-md-12">
		 				<div class="col-md-6" >
		 					<div class="row form-group"> 
		               			<div class="col col-md-6">
		                 			<label for="text-input">Unit/SUS No<strong style="color: red;">*</strong></label> 
		               			</div>
		               			<div class="col-12 col-md-6">
		                 			<input id="sus_no" value="${data[0][2]}" class="form-control input" name="sus_no" style="font-family: 'FontAwesome',Arial;"  placeholder="&#xF002; Search Unit Name/SUS"  autocomplete="off">
		                 			<label class="text-input"><span class="input" id="u_name" style="font-size: 12px;font-weight: bold;">${data[0][4]}</span></label>
								</div>
		 					</div>
		 				</div>
		 				<div class="col-md-6" >
		 					<div class="row form-group"> 
		               			<div class="col col-md-6">
		                 			<label class="text-input">Arm Code &nbsp;<strong id="arm_code" class="input">${data[0][5]}</strong></label> 
		               			</div>
		               			<div class="col-12 col-md-6">
		                 			 <label class="text-input">Form Code &nbsp;<strong id="form_code" class="input">${data[0][6]}</strong></label>
		                 			 <label class="text-input"><span class="input" id="f_name" style="font-size: 12px;font-weight: bold;">${data[0][8]}</span></label>
								</div>
		 					</div>
		 				</div>
         			</div>
         			<div class="col-md-12">
		 				<div class="col-md-6" >
		 					<div class="row form-group"> 
		               			<div class="col col-md-6">
		                 			<label for="text-input">Nodal Officer<strong style="color: red;">*</strong></label> 
		               			</div>
		               			<div class="col-12 col-md-6">
		                 			<input id="sus_nodal" value="${data[0][7]}" class="form-control input" name="sus_nodal" style="font-family: 'FontAwesome',Arial;"  placeholder=""  autocomplete="off">		                 		
								</div>
		 					</div>
		 				</div>		 		
         			</div>
         		</c:if>
         	</div>
         	<div class="card-footer" align="center">
	         	<input type="submit" class="btn btn-primary btn-sm nGlow" id="btnUpdate" value="Update Details">
         	</div>
		</div>
	</div>
	
</form:form>

<c:url value="fp_user_details" var="searchURL" />
<form:form action="${searchURL}" method="POST" id="updateForm" class="fp-form">
	<input type="hidden" name="usr_id" id="usr_id" value="0"/>
</form:form>
<script src="js/common/commonmethod.js" type="text/javascript"></script>
<script>
$(document).ready(function () {
	
	var user_id=$("#user_id");
    user_id.autocomplete({
		source: function(request,response) {
			$.ajax({
	        	type: 'POST',
	        	url: "getUsernameList?"+key+"="+value,
	        	data: {userName:user_id.val()},
	          	success: function( data ) {
	        		var userVal = [];
        	  		var length = data.length-1;
        	  		var enc = data[length].substring(0,16);
        	  		var user = ""
	        		for(var i = 0;i<data.length;i++){
	        			user = dec(enc,data[i]);
	        			userVal.push(user.trim());
	        		}
	        		response(userVal); 
	          	}
	        });
	    },
	    change: function(event, ui) {
	    	if(!ui.item){
	        	alert("Please Enter Valid User ID");
	        	$(this).val("").focus();
	        	clear();
	        	return false;	             
	        }
	    	return true;
	    },
	    select: function( event, ui ) {
	    	$(this).val(ui.item.value);
	    	$("#usr_id").val(ui.item.value);
	    	$("#updateForm").submit();
	    },
	    minLength: 0,
	    autoFill: true   	     
	});
    
    var sus_no=$("#sus_no");
    sus_no.autocomplete({
		source: function(request,response) {
			$.ajax({
	        	type: 'POST',
	        	url: "getAllOrbatUnitSusList?"+key+"="+value,
	        	data: {param:sus_no.val()},
	          	success: function(data){
	          		var dataList = [],meta="",row="",sus="",unit="";
	          		for(var i = 0;i<data.length;i++){
	          			row = data[i];
	          			if(i==0)
	          				meta = row["meta"];
	          			sus = dec(meta,row["sus_no"]);
	          			unit = dec(meta,row["unit"]);
	        			dataList.push({label: unit+' - '+sus,id:unit,value:sus,arm_code:row["arm_code"],form_code:row["form_code"]});
	        		}
	        		response(dataList); 
	          }
	        });
	    },
	    change: function(event, ui) {
	    	if(!ui.item){
	        	alert("Please Enter Valid Unit Name/SUS No");
	        	$(this).val("").focus();
	        	$("#sus_no").val("");
	        	$(".text-input .input").html("");
	        	return false;	             
	        }
	    	return true;
	    },
	    select: function( event, ui ) {
	    	/* $(this).val(ui.item.id); */
	    	$("#u_name").html(ui.item.id);
	    	$("#arm_code").html(ui.item.arm_code);
	    	$("#form_code").html(ui.item.form_code);
	    },
	    minLength: 1,
	    autoFill: true   	     
	});
    
    $("#btnClear").click(function(){
    	clear();
    });
    
	$("#btnUpdate").click(function(){
		if($("#user_id").val() == ""){
			$("#user_id").focus();
			alert("Please enter User ID");
			return false;
		}
		if($("#army_no").val() == ""){
			$("#army_no").focus();
			alert("Please enter Army No");
			return false;
		}
		if($("#sus_no").val() == ""){
			$("#sus_no").focus();
			alert("Please select Unit");
			return false;
		}
		return true;
    });
    
    function clear(){
    	$(".input").val("");
    	$(".text-input .input").html("");
    	
    }
});
</script>
