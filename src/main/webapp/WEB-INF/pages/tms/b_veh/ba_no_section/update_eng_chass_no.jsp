<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<form:form name="eng_chass_no" id="eng_chass_no" action="eng_chass_noUrl" method="POST" commandName="eng_chass_noCmd">
	<div class="animated fadeIn">
		<div class="">
			<div class="container" align="center">
				<div class="card">
					<div class="card-header"><h5>UPDATION OF ENGINE AND CHASSIS NO<br></h5><h6><span style="font-size: 10px;font-size:15px;color:red">(To be entered by MISO)</span></h6></div>
					<div class="card-body card-block">
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong style="color: red;">*</strong> BA No</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="ba_no" name="ba_no" maxlength="10" class="form-control autocomplete" autocomplete="off">
										<input type="hidden" id="ba_no_hide" name="ba_no_hide" class="form-control autocomplete" autocomplete="off">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong style="color: red;"></strong> Chassis No </label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="chasis_no"  autocomplete="off" name="chasis_no" class="form-control" maxlength="20">
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong style="color: red;"></strong> Engine No</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="engine_no"  autocomplete="off" name="engine_no" class="form-control" maxlength="20">
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="form-control card-footer" align="center">
						<input type="submit" class="btn btn-primary btn-sm" value="Update" onclick="return validation();">
						
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>
<script type="text/javascript">
$(document).ready(function() {
	$("#ba_no").val('${ba_no}');

	if('${ba_no}' != ''){
		
	     $.post("geteng_chas?"+key+"="+value,{ba_no:'${ba_no}'}, function(j) {
         }).done(function(j) {
        	 if(j != ""){
				  var length = j.length-1;
		          var enc = j[length][0].substring(0,16);
		          $("#engine_no").val(dec(enc,j[0][0]));
		          $("#chasis_no").val(dec(enc,j[0][1]));
					$("#ba_no_hide").val(ba_no);
			}
			else{
				alert("Data Not Found.");
			}
         }).fail(function(xhr, textStatus, errorThrown) {
         });
	}
});
$(function() {
	
	$("input#ba_no").keyup(function(){
		var ba_no = this.value;
		var ba_noAuto=$("#ba_no");
		ba_noAuto.autocomplete({
			source: function( request, response ) {
		    	$.ajax({
		    		type: 'POST',
			    	url: "getActiveBaNoList?"+key+"="+value,
		       		data: {ba_no:ba_no},
		        	success: function( data ) {
		        		 var susval = [];
			        	  var length = data.length-1;
			        	  if(data.length != 0){
				        		var enc = data[length].substring(0,16);
				        	}
				        	for(var i = 0;i<data.length;i++){
				        		susval.push(dec(enc,data[i]));
				        	}
				        	response( susval ); 
		          	}
		        });
		 	},
		 	minLength: 1,
			autoFill: true,
		  	change: function(event, ui) {
		    	if (ui.item) {   	        	  
		          	return true;    	            
		        } 
		    	else {
		          	alert("Please Enter Valid BA No.");
		          	$("#engine_no").val("");
					$("#chasis_no").val("");
					$("#ba_no_hide").val("");
		          	ba_noAuto.val("");	        	  
		          	ba_noAuto.focus();
		          	return false;	             
		    	}   	         
		    }, 
		  	select: function( event, ui ) {
		      	var ba_no = ui.item.value;
		     
		        $.post("geteng_chas?"+key+"="+value,{ba_no:ba_no}, function(j) {
	            }).done(function(j) {
	            	if(j != ""){
						var length = j.length-1;
				          var enc = j[length][0].substring(0,16);
				          $("#engine_no").val(dec(enc,j[0][0]));
				          $("#chasis_no").val(dec(enc,j[0][1]));
							$("#ba_no_hide").val(ba_no);
					}
					else{
						alert("Data Not Found.");
					}	
	            }).fail(function(xhr, textStatus, errorThrown) {
	            });
			}
		});
	});
});

function validation() {
	if ($("#ba_no").val() == "") {
		alert("Please Enter BA No.");
		$("#ba_no").focus();
		return false;
	}
	if ($("#chasis_no").val() == "") {
		alert("Please Enter Chassis No.");
		$("#chasis_no").focus();
		return false;
	}
	if ($("#engine_no").val() == "") {
		alert("Please Enter Engine No.");
		$("#engine_no").focus();
		return false;
	}
	return true
}
</script>