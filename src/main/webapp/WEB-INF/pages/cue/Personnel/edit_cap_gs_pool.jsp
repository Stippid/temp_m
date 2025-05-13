<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script type="text/javascript" src="js/miso/miso_js/jquery-1.12.3.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
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

<form:form name="Cap_gs_pool" id="Cap_gs_pool" action="updateGsPoolUrlAction" method='POST' commandName="CapGsPoolEditCMD">
		<div class="container" align="center">
        	<div class="card">
        		<div class="card-header"><h5>Edit Capture GS Pool </h5> </div>   
          		<div class="card-body card-block cue_text">
            		<div class="col-md-12">	
            		  <div class="col-md-6">
	  						<div class="row form-group">	           					
             					<div class="col col-md-6">
               						<label class=" form-control-label">Month of Calculation</label> <strong style="color: red;">*</strong>
             					</div>
             					<div class="col-12 col-md-6">
             					<input id="id" name="id" type="hidden" value="${CapGsPoolEditCMD.id}">
                					<input id="month" name="month" type="number" min="1" max="12" step="1"  class="form-control" value="${CapGsPoolEditCMD.month}" readonly="readonly"/>
             					</div>
             				</div>
             			</div>
             			<div class="col-md-6">
	  						<div class="row form-group">	           					
             					<div class="col col-md-6">
               						<label class=" form-control-label">Year of Calculation</label> <strong style="color: red;">*</strong>
             					</div>
             					<div class="col-12 col-md-6">
             						<input id="year" name="year" type="number" min="1900" max="2099" step="1" value="${CapGsPoolEditCMD.year}" class="form-control" readonly="readonly"/>
               					</div>
             				</div>
             			</div>
  					</div>
  					<div class="col-md-12">	
  						<div class="col-md-6">
 							<div class="row form-group">  								
               					<div class="col col-md-6">
                 					<label for="text-input" class=" form-control-label">Arm</label> <strong style="color: red;">*</strong>
               					</div>
               					<div class="col-12 col-md-6">
<!--                						<select  id="arm" name="arm" class="form-control" disabled="disabled"> -->
<!--                  					<option value="0">--Select--</option> -->
<%--                  						<c:forEach var="item" items="${getArmNameList}" varStatus="num" > --%>
<%--            									<option value="${item[0]}"  <c:if test="${item[0] eq CapGsPoolEditCMD.arm}">selected="selected"</c:if>> ${item[1]}</option> --%>
<%--            								</c:forEach>	 --%>
<!--                  					</select> -->
								<input type="hidden" id="arm" name="arm" class="form-control"  value="${CapGsPoolEditCMD.arm}"  >
								 <input id ="arm_desc" name="arm_desc"  class="form-control"  value="${CapGsPoolEditCMD.arm_desc}" disabled ="disabled">
								</div>
 							</div>
 						</div>
  						<div class="col-md-6">
  							<div class="row form-group">
				                <div class="col col-md-6">
				                  	<label class=" form-control-label">Category</label> <strong style="color: red;">*</strong>
				                </div>
				                <div class="col-12 col-md-6">
				                 <input type ="hidden" id ="rank_cat1" value="${CapGsPoolEditCMD.rank_cat}">
				                <select  id="rank_cat" name="rank_cat" class="form-control" disabled ="disabled">
				                 <option value="">--Select--</option>
                 						<c:forEach var="item" items="${getTypeofRankcategory}" varStatus="num" >
           									<option value="${item[0]}" <c:if test="${item[0] eq CapGsPoolEditCMD.rank_cat}">selected="selected"</c:if>> ${item[1]}</option>
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
				                  	<label class=" form-control-label">Rank </label> <strong style="color: red;">*</strong>
				                </div>
				                <div class="col-12 col-md-6">
				                  	<input id="rank" class="form-control"  readonly="readonly"  >
				              		<input type="hidden" id="rank_hide" name="rank" class="form-control"  value="${CapGsPoolEditCMD.rank}"  >
				             </div>
				            </div>						
  						</div>
  						<div class="col-md-6">
  							<div class="row form-group">
				                <div class="col col-md-6">
				                  	<label class=" form-control-label">Authorisation</label> <strong style="color: red;">*</strong>
				                </div>
				                <div class="col-12 col-md-6">
				                <input  id="auth_amt"  name="auth_amt" maxlength="8" class="form-control" value="${CapGsPoolEditCMD.auth_amt}" onkeypress="return isNumberKey(event,this);" onchange="return checkAuth_amtLength();">
				              </div>
				           </div>	  								
  					   </div>
  					</div> 
  			</div>
  				<div class="card-footer" align="center">
 					<input type="submit" class="btn btn-primary btn-sm" value="Update" onclick="return isvalidData();">
             			<a href="search_cap_gs_pool" class="btn btn-danger btn-sm" onClick="javascript:window.close('','_parent','');">  Cancel </a>
            	</div> 
 			</div>				
  		</div>

</form:form>

<c:if test="${not empty msg}">
	<input type="hidden" name="msg" id="msg" value="${msg}" disabled="disabled"/>
</c:if>

	<c:url value="updateGsPoolUrl" var="updateUrl" />
	<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid">
		<input type="hidden" name="updateid" id="updateid" value="0"/>
	</form:form>
<script>

function checkAuth_amtLength() {
	var scale_vald= $("input#auth_amt").val();
	
 	if(scale_vald.length > 8)
	{
		alert("Please Enter Valid Digit");
		$("input#auth_amt").val("");
 		return false;
		
	}
	if(scale_vald != "" && scale_vald.includes(".")) {
		//var scale_vald1=[] ;
		var scale_vald1 = scale_vald.toString().split(".");			
	 	if(scale_vald1[0].length > 5 || scale_vald1[1].length > 2 )
		{
	 		alert("Please Enter Valid Digit");
			$("input#auth_amt").val("");
	 		return false;
		}
	 	
	 }
	else {
		if(scale_vald.length > 5)
		{
			alert("Please Enter Valid Digit");
			$("input#auth_amt").val("");
	 		return false;
			
		}
	}
 	return true;
}

$(document).ready(function() {
	
	var rnk = document.getElementById("rank_hide").value;
	$.post("editTypeofRankList_enti_wepe?"+key+"="+value, {rnk:rnk}).done(function(j) {
		$("#rank").val(j[0][0]);  
	 }).fail(function(xhr, textStatus, errorThrown) { }); 
	
	document.getElementById("auth_amt").value=parseFloat(document.getElementById("auth_amt").value);

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

function isAlphabeticsA_ZOnly(evt){
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if(( charCode >= 48 && charCode <= 57 ) && ! charCode != 8 ){
		 return false;
	}
    return true;
}

//////////////////only numeric and point(.)
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

function isvalidData()
{	
	if($("input#month").val() == "")
	{
		alert("Please Enter Month");
		$("input#month").focus();
		return false;
	}
	if($("input#year").val() == "")
	{
		alert("Please Enter Year");
		$("input#year").focus();
		return false;
	}
	 if($("select#arm").val() == "0")
		{
			alert("Please Select Arm");
			$("select#arm").focus();
			return false;
		} 
	
	 if($("input#rank_cat").val() == "")
		{
			alert("Please Enter Category");
			$("input#rank_cat").focus();
			return false;
		}
		 if($("input#rank").val() == "")
			{
				alert("Please Enter Rank");
				$("input#rank").focus();
				return false;
			}
		 if($("input#auth_amt").val() == "")
			{
				alert("Please Enter Authorisation");
				$("input#auth_amt").focus();
				return false;
			}
	return true; 
}

</script>