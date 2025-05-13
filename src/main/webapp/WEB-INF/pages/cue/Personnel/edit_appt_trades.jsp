<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>


<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 

<script type="text/javascript" src="js/miso/miso_js/jquery-1.12.3.js"></script>

<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
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
</head>
<body>
<form:form action="app_tradesEditAction" method="post"  class="form-horizontal" commandName="apptTradesEditCMD"> 
	<div class="animated fadeIn">
		 	<div class="container" align="center">
	        	<div class="card">
	          		<div class="card-header"><h5>Edit APPT/TRADE and rank</h5></div>
	          			<div class="card-body card-block cue_text">
								<div class="col-md-12">
								<div class="col-md-7">
	              					<div class="row form-group">
	                					<div class="col col-md-6">
	                					<input type="hidden" id="id" name="id" class="form-control" value="${apptTradesEditCMD.id}">
	                  						<label class=" form-control-label">Category</label>	
	                					</div>
	                					<div class="col col-md-6">
	                					  <input type="hidden" id="parent_code1" name="parent_code" value="${apptTradesEditCMD.parent_code}">
	                  						<select  id="parent_code" class="form-control" disabled="disabled">	                  						
	                  						<option value="Select">--Select--</option>	
	                  						  	<c:forEach var="item" items="${getcategoryListFinal}" varStatus="num" >
		                 							<option value="${item.codevalue}" <c:if test="${item.codevalue eq apptTradesEditCMD.parent_code}">selected="selected"</c:if> >${item.label}</option>
		                 						</c:forEach>	                  						
	                  						</select>
										</div>
									</div>
	              					</div>
								</div>
              					<div class=" col-md-12">
              						<div class="col-md-7">
	    								<div class="row form-group">
	    									<div class="col col-md-6">
              									<label class=" form-control-label">Type of Category <strong style="color: red;">*</strong></label>	
            								</div>
            								<div class="col col-md-6">
												<label for="app1">
									  			 <input  type="hidden" id="hidlevel_in_hierarchy" class="form-control" value="${apptTradesEditCMD.level_in_hierarchy}"/>
									   			 <input type="radio" id="app1" name="level_in_hierarchy" value="APPOINTMENT" maxlength="12" onchange="clearDescription()"  readonly="readonly"/>
									   			 APPT/Trade
												</label>
											    <label for="rank1">
									    		<input type="radio" id="rank1" name="level_in_hierarchy"  value="RANK" maxlength="12" onchange="clearDescription()"  readonly="readonly"/>
									   			 Rank
												</label>
									       </div>
									</div>
								  </div>
								</div>	
								<div class="col-12 col-md-12">
								<div class="col-md-7">
									<div class="row form-group" id="dvdescription">
   										<div class="col col-md-6" >
     											<label class=" form-control-label" id="lbl_rank"></label> <strong style="color: red;">*</strong>
   										</div>
   										<div class="col-12 col-md-6">
     											<input type="text" id="description" name="description" maxlength="50" class="form-control" autocomplete="off" onkeypress="return blockSpecialChar(event)" value="${apptTradesEditCMD.description}" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search">
   										</div>
 										</div>  
								</div>
		            		</div>
		            </div>
					<div class="card-footer" align="center">
	              		<input type="submit" class="btn btn-primary btn-sm" value="Update" onclick="return isValid();" >
						<a href="search_appt_trades" class="btn btn-danger btn-sm" onClick="javascript:window.close('','_parent','');">  Cancel </a>
		            </div>
		      </div> 
	       </div>
   </div>	
</form:form>

<c:if test="${not empty msg}">
	<input type="hidden" name="msg" id="msg" value="${msg}" disabled="disabled"/>
</c:if>

<c:url value="updateAPPTUrl" var="updateUrl" />
	<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid">
		<input type="hidden" name="updateid" id="updateid" value="0"/>
	</form:form>

<script>
var testdesc;
$(document).ready(function() {
	$("input[name='level_in_hierarchy']").click(function() {
	  if($(this).val()=="APPOINTMENT")
	   {    
		  testdesc = $(this).val();
		   $("label#lbl_rank").text("Description of APPT/Trade");
	       $("div#dvdescription").show();
	   }
	   else
	   {
		   testdesc = $(this).val();
		   $("label#lbl_rank").text("Description of RANK");
		   $("div#dvdescription").show();
	    }
	}); 
	
	
$(function() {
		 var val = $('input:radio[name=level_in_hierarchy]:checked').val();
		 var parent_code = $("#parent_code").val();
		 var wepetext1=$("#description");
		
	      wepetext1.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
	        type: 'POST', 
	        url: "getdesciption?"+key+"="+value,
	        data: {val : val,parent_code : parent_code ,description : document.getElementById('description').value},
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
	          } 
	      }, 
	    });
}); 

try{
	if(window.location.href.includes("&msg="))
	{
		var url = window.location.href.split("?updateid=")[0];
		var id= window.location.href.split("?updateid=")[1].split("&msg=")[0];
		window.location = url;
		
		 document.getElementById('updateid').value=id;
 		 document.getElementById('updateForm').submit();
	}
	
}
catch (e) {
	// TODO: handle exception
}
}); 
$.ajaxSetup({
    async: false
});
</script>
<script>
function blockSpecialChar(e){
	$('#description').keyup(function() {
		this.value = this.value.toUpperCase();
	});   
}

$(document).ready(function() {
	var  description = $("#description").val();
	var level_in_hierarchy = $("#hidlevel_in_hierarchy").val();
		if(level_in_hierarchy == 'RANK')
		{	       	 				
			$("#rank1").prop("checked", true);
		}
		else 
		{
			$("#app1").prop("checked", true);
		}
	if(level_in_hierarchy=="APPOINTMENT")
	{    
		$("label#lbl_rank").text("Description of APPT/Trade");
     	$("div#dvdescription").show();
    }
	else
	{
		$("label#lbl_rank").text("Description of RANK");
		$("div#dvdescription").show();
	}
	
	try{
		 if(window.location.href.includes("count="))
		{
			var url = window.location.href.split("?count=")[0];
			var m=  window.location.href.split("&msg=")[1];
			window.location = url;			
		} 
	}
	catch (e) {
		// TODO: handle exception
	}
	
});

function clearData()
{	
	document.getElementById("app1").checked = false;
	document.getElementById("rank1").checked = false;
	if("input[name='level_in_hierarchy']"== null )
	{
		$("#dvdescription").show();
	}
	else
	{
		$("#dvdescription").hide();
	} 
}

function clearDescription(){
	 document.getElementById('description').value = "";
}

</script>
<script>
function isValid()
{	
	 var level_in_hierarchy = document.getElementsByName('level_in_hierarchy');
     var genValue = false;
     for(var i=0; i<level_in_hierarchy.length;i++){
         if(level_in_hierarchy[i].checked == true){
             genValue = true;    
         }
     }
     if(!genValue){
         alert("Please Choose Atleast One Button");
         return false;
     }
     if($("input#description").val() == "")
		{
			alert("Please Enter the data");
			return false;
		} 
	return true;
}

</script>
</body>
</html>