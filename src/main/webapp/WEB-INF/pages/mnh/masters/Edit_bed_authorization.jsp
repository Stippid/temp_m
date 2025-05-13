<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="Stylesheet" href="js/Calender/jquery-ui.css" >
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<link rel="stylesheet" href="js/cue/cueWatermark.css">

<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
<form:form action="Edit_bed_authorizationACT" name="Edit_bed_authorization" id="Edit_bed_authorization"  method="post" class="form-horizontal" 
commandName="Edit_bed_authorizationCMD">		
<div class="container" align="center">
    <div class="card">
         <div class="card-header mnh-card-header">
		        <h5> Edit BED Authorization</h5>
		        <h6>
					<span style="font-size: 12px; color: red">(To be entered by MISO)</span>
				</h6>
		 </div>
		 
		
		 <div class="card-body card-block" >
		     
		           <div class="col-md-12">							
	               		 <div class="col-md-2" style="text-align: left;">
	                 		  <label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Hospital Name</label>
	               		 </div>
	               		 <div class="col-md-6">
	               		   <input type="hidden" id="id" name="id" class="form-control" value="0" autocomplete="off">		
	               			  <input type="text" id="unit_name1" name ="unit_name1" class="form-control-sm form-control" 
	               			  placeholder="Search..." autocomplete="off" maxlength="100" title="Type Unit Name or Part of Unit Name to Search" >
						 </div>	 
						 
						 <div class="col-md-2" style="text-align: left;"> 
	               			  <label class=" form-control-label"><strong style="color: red;">* </strong>SUS No</label>
	             		 </div>
	             		 <div class="col-md-2">	             		 		
	             			  <input type="text" id="sus_no1" name="sus_no" class="form-control-sm form-control" 
	             			  placeholder="Search..." autocomplete="off" maxlength="8" title="Type SUS No or Part of SUS No to Search" />
	               		 </div>  
  					</div> 
  					
  					<div class="col-md-12">
  					     <div class="col-md-2" style="text-align: left;">
	                 		  <label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Command</label>
	               		 </div>
	               		 
	               		 <div class="col-md-4">
	               		      <input type="text" id="command" name="command" class="form-control-sm form-control" readonly>
	               		      <input type="hidden" id="command_sus" name="command_sus" class="form-control-sm form-control">
	               		 </div>
	               		 	             
	             
  					</div>
  					
  					<div class="col-md-12">
  					     <div class="col-md-2" style="text-align: left;">
	                 		  <label for="text-input" class=" form-control-label">Officer</label>
	               		 </div>
	               		 
	               		 <div class="col-md-4">
	               		      <input type="text" id="off_auth" name="off_auth1" value="0" class="form-control-sm form-control" 
	               		       autocomplete="off" onkeypress="return isNumberPointKey(event);" onkeyup="totalcal();">
	               		   
	               		 </div>
	               		 <div class="col-md-2" style="text-align: left;">
	                 		  <label for="text-input" class=" form-control-label">JCO/OR</label>
	               		 </div>
	               		 
	               		 <div class="col-md-4">
	               		      <input type="text" id="jco_or_auth" name="jco_or_auth1" value="0" class="form-control-sm form-control" 
	               		      autocomplete="off"  onkeypress="return isNumberPointKey(event);" onkeyup="totalcal();">
	               		   
	               		 </div>
  					</div>
  					
  						<div class="col-md-12">
  					     <div class="col-md-2" style="text-align: left;">
	                 		  <label for="text-input" class=" form-control-label">Officer Family</label>
	               		 </div>
	               		 
	               		 <div class="col-md-4">
	               		      <input type="text" id="off_fam_auth" name="off_fam_auth1" value="0" class="form-control-sm form-control" 
	               		      autocomplete="off"  onkeypress="return isNumberPointKey(event);" onkeyup="totalcal();">
	               		 
	               		 </div>
	               		 <div class="col-md-2" style="text-align: left;">
	                 		  <label for="text-input" class=" form-control-label">JCO/OR Family</label>
	               		 </div>
	               		 
	               		 <div class="col-md-4">
	               		      <input type="text" id="jco_or_fam_auth" name="jco_or_fam_auth1" value="0" class="form-control-sm form-control" 
	               		      autocomplete="off"  onkeypress="return isNumberPointKey(event);" onkeyup="totalcal();">
	               		 
	               		 </div>
  					</div>
  					
  						<div class="col-md-12">
  					     <div class="col-md-2" style="text-align: left;">
	                 		  <label for="text-input" class=" form-control-label">Total Bed Auth</label>
	               		 </div>
	               		 
	               		 <div class="col-md-4">
	               		      <input type="text" id="total_all" name="total_all1" value="0" class="form-control-sm form-control" 
	               		      autocomplete="off"  onkeypress="return isNumberPointKey(event);">
	               		   
	               		 </div>
	               		 <div class="col-md-2" style="text-align: left;">
	                 		  <label for="text-input" class=" form-control-label">Bed Laid Out</label>
	               		 </div>
	               		 
	               		 <div class="col-md-4">
	               		      <input type="text" id="laid_out" name="laid_out1" value="0" class="form-control-sm form-control" 
	               		      autocomplete="off"  onkeypress="return isNumberPointKey(event);">
	               		   
	               		 </div>
	               		 
  					</div>
  					<div class="col-md-12">
  					     <div class="col-md-2" style="text-align: left;">
	                 		  <label for="text-input" class=" form-control-label">Others</label>
	               		 </div>
	               		 
	               		 <div class="col-md-4">
	               		      <input type="text" id="others" name="others1" value="0" class="form-control-sm form-control" 
	               		      autocomplete="off"  onkeypress="return isNumberPointKey(event);">
	               		   
	               		 </div>
  		 
  					</div>   
		 </div>
		 
		 <div class="card-footer" align="center">	        
	          <a href="bed_authorization" class="btn btn-primary btn-sm"  id="btn_cler">Clear</a>	         
	          <input type="submit" class="btn btn-success btn-sm"value="Save" id="btn_save" onclick="return validate();"><!--  onclick="return validate();"	 -->
	          	<i class="fa fa-search"></i><input type="button" id="btdog" class="btn btn-primary btn-sm" value="Search" onclick="return Search();">	  
         </div>
    </div>
</div>

</form:form>

<script>
function btn_clc(){
	location.reload(true);
}


function isNumberPointKey(evt) {
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	
	if(charCode != 46 && charCode > 31 && (charCode<48 || charCode>57)){
		return false;
	}else{
		if(charCode == 46){
			return false;
		}
	}
	return true;
}

function getCommand(y){
	var FindWhat = "COMMAND";
	$.post("getMNHHirarNameBySUS?"+key+"="+value, {FindWhat:FindWhat,a:y},function(j) {
		var a = [];
		var enc = j[j.length-1].substring(0,16);
		for(var i = 0; i < j.length; i++){
			a[i] = dec(enc,j[i]);
		}
		var data=a[0].split(",");
		var datap;
		
		for(var i = 0; i < data.length-1; i++) {
			datap=data[i].split(":");
			$("#command").val(datap[1]);  
			$("#command_sus").val(datap[5]); 
		}	
	}); 
} 

function validate(){
	
	var d = new Date();
	var year = d.getFullYear();
	
	
		
			
			if($("#sus_no1").val() == ""){
				alert("Please Enter the SUS No");
				$("#sus_no1").focus();
				return false;
			}
			if($("#command").val() == ""){
				alert("Please Enter the Command");
				$("#command").focus();
				return false;
			}
			if($("#command").val() == ""){
				alert("Please Enter the Command");
				$("#command").focus();
				return false;
			}
			if($("#total_all").val() == "0" || $("#total_all").val() == " "){
				alert("Total Bed Auth Should not be Null or Zero");
				$("#total_all").focus();
				return false;
			}
			if(parseInt($("#off_auth").val()) > parseInt($("#total_all").val())){
				alert("Please Enter The Proper Officer Value");
				$("#off_auth").focus();
				return false;
			}
			if(parseInt($("#jco_or_auth").val()) > parseInt($("#total_all").val())){
				alert("Please Enter The Proper JCO/OR Value");
				$("#jco_or_auth").focus();
				return false;
			}
			if(parseInt($("#off_fam_auth").val()) > parseInt($("#total_all").val())){
				alert("Please Enter The Proper Officer Family Value");
				$("#off_fam_auth").focus();
				return false;
			}
			if(parseInt($("#jco_or_fam_auth").val()) > parseInt($("#total_all").val())){
				alert("Please Enter The Proper JCO/OR Family Value");
				$("#jco_or_fam_auth").focus();
				return false;
			}
			var a1 = $("#off_auth").val();
			if(a1 == "" || a1 == null){
				a1 = 0;
			}
			var a2 = $("#jco_or_auth").val();
			if(a2 == "" || a2 == null){
				a2 = 0;
			}
			var a3 = $("#off_fam_auth").val();
			if(a3 == "" || a3 == null){
				a3 = 0;
			}
			var a4 = $("#jco_or_fam_auth").val();
			if(a4 == "" || a4 == null){
				a4 = 0;
			}
			var a5 = $("#others").val();
			if(a5 == "" || a5 == null){
				a5 = 0;
			}
			
			
			var t1 = (parseInt(a1)+parseInt(a2)+parseInt(a3)+parseInt(a4)+parseInt(a5));
			
			if(parseInt(t1) > parseInt($("#total_all").val())){
				alert("Total Bed Auth Value should be less than or equal to the total of Officer,JCO/OR,Officer Family and JCO/OR Family Auth.");
				$("#total_all").focus();
				return false;
			}
			
			if($("#laid_out").val() == "0"){
				alert("Please Enter the Bed Laid Out");
				$("#laid_out").focus();
				return false;
			}
			
}

function totalcal(){
	var a1 = $("#off_auth").val();
	if(a1 == "" || a1 == null){
		a1 = 0;
	}
	var a2 = $("#jco_or_auth").val();
	if(a2 == "" || a2 == null){
		a2 = 0;
	}
	var a3 = $("#off_fam_auth").val();
	if(a3 == "" || a3 == null){
		a3 = 0;
	}
	var a4 = $("#jco_or_fam_auth").val();
	if(a4 == "" || a4 == null){
		a4 = 0;
	}
	var a5 = $("#others").val();
	if(a5 == "" || a5 == null){
		a5 = 0;
	}
	
	
	var t1 = (parseInt(a1)+parseInt(a2)+parseInt(a3)+parseInt(a4)+parseInt(a5));
	$("#total_all").val(t1);
}




</script>
<script>


var key = "${_csrf.parameterName}";
var value = "${_csrf.token}";


jQuery(function() {
	// Source SUS No

	jQuery("#sus_no1").keypress(function(){
		var sus_no = this.value;
			 var susNoAuto=jQuery("#sus_no1");
			  susNoAuto.autocomplete({
			      source: function( request, response ) {
			        jQuery.ajax({
			        type: 'POST',
			        url: "getTargetSUSNoList?"+key+"="+value,
			        data: {sus_no:sus_no},
			          success: function( data ) {
			        	  var susval = [];
			        	  var length = data.length-1;
			        	  if(data.length != 0){
				        		var enc = data[length].substring(0,16);
				        	}
				        	for(var i = 0;i<data.length;i++){
				        		susval.push(dec(enc,data[i]));
				        	}
				        	var dataCountry1 = susval.join("|");
			            var myResponse = []; 
			            var autoTextVal=susNoAuto.val();
						jQuery.each(dataCountry1.toString().split("|"), function(i,e){
							var newE = e.substring(0, autoTextVal.length);
							if (e.toLowerCase().includes(autoTextVal.toLowerCase())) {
							  myResponse.push(e);
							}
						});      	          
						response( myResponse ); 
			          }
			        });
			      },
			      minLength: 1,
			      autoFill: true,
			      change: function(event, ui) {
			    	 if (ui.item) {   	        	  
			        	  return true;    	            
			          } else {
			        	  alert("Please Enter Approved SUS No.");
			        	  document.getElementById("sus_no1").value="";
			        	  susNoAuto.val("");	        	  
			        	  susNoAuto.focus();
			        	  return false;	             
			          }   	         
			      }, 
			      select: function( event, ui ) {
			    	  var unit_sus_no = ui.item.value;
			    	 	
  	 			 	$.post("getActiveUnitNameFromSusNo?"+key+"="+value, {sus_no:unit_sus_no}).done(function(j) {				
  	 			 		if(j == ""){
  	 			      	 	alert("Please Enter Approved Unit SUS No.");
  			        	  	document.getElementById("unit_name1").value="";
  			        	  	susNoAuto.val("");
  			        	  	susNoAuto.focus();
  	 			      	}else{
	    	 	   	        	var length = j.length-1;
	    	    				var enc = j[length].substring(0,16);
	    	    				$("#unit_name1").val(dec(enc,j[0]));	
	    	 	   	        		
  	 	   	        	}
  	 				}).fail(function(xhr, textStatus, errorThrown) {
  	 				});
			      } 	     
			});	
	});
	// End
	
	// Source Unit Name

    jQuery("#unit_name1").keypress(function(){
 			var unit_name = this.value;
				 var susNoAuto=jQuery("#unit_name1");
				  susNoAuto.autocomplete({
				      source: function( request, response ) {
				        jQuery.ajax({
				        type: 'POST',
				        url: "getTargetUnitsNameActiveList?"+key+"="+value,
				        data: {unit_name:unit_name},
				          success: function( data ) {
				        	  var susval = [];
				        	  var length = data.length-1;
				        	  if(data.length != 0){
					        		var enc = data[length].substring(0,16);
					        	}
					        	for(var i = 0;i<data.length;i++){
					        		susval.push(dec(enc,data[i]));
					        	}
					        	var dataCountry1 = susval.join("|");
				            var myResponse = []; 
				            var autoTextVal=susNoAuto.val();
							jQuery.each(dataCountry1.toString().split("|"), function(i,e){
								var newE = e.substring(0, autoTextVal.length);
								if (e.toLowerCase().includes(autoTextVal.toLowerCase())) {
								  myResponse.push(e);
								}
							});      	          
							response( myResponse ); 
				          }
				        });
				      },
				      minLength: 1,
				      autoFill: true,
				      change: function(event, ui) {
				    	 if (ui.item) {   	        	  
				        	  return true;    	            
				          } else {
				        	  alert("Please Enter Approved Unit Name.");
				        	  document.getElementById("unit_name1").value="";
				        	  susNoAuto.val("");	        	  
				        	  susNoAuto.focus();
				        	  return false;	             
						}   	         
					}, 
					select: function( event, ui ) {
						 var target_unit_name = ui.item.value;
						
								 	$.post("getTargetSUSFromUNITNAME?"+key+"="+value, {target_unit_name:target_unit_name}).done(function(j) {				
								 		 var length = j.length-1;
				 				         var enc = j[length].substring(0,16);
				 				         jQuery("#sus_no1").val(dec(enc,j[0]));	
									}).fail(function(xhr, textStatus, errorThrown) {
							});
					} 	     
				}); 			
 			});

});
</script>
<Script>
(function ($) {
    "use strict";
    $.fn.charCounter = function (options) {
        if (typeof String.prototype.format == "undefined") {
            String.prototype.format = function () {
                var content = this;
                for (var i = 0; i < arguments.length; i++) {
                    var replacement = '{' + i + '}';
                    content = content.replace(replacement, arguments[i]);
                }
                return content;
            };
        }
        var options = $.extend({
            backgroundColor: "#FFFFFF",
            position: {
                right: 10,
                top: 10
            },
            font:   {
                size: 10,
                color: "#a59c8c"
            },
            limit: 250
        }, options);
        return this.each(function () {
            var el = $(this),
                wrapper = $("<div/>").addClass('focus-textarea').css({
                    "position": "relative",
                        "display": "inline-block"
                }),
                label = $("<span/>").css({
                    "zIndex": 999,
                        "backgroundColor": options.backgroundColor,
                        "position": "absolute",
                        "font-size": options.font.size,
                        "color": options.font.color
                }).css(options.position);
            if(options.limit > 0){
                label.text("{0}/{1}".format(el.val().length, options.limit));
                el.prop("maxlength", options.limit);
            }else{
                label.text(el.val().length);
            }
            el.wrap(wrapper);
            el.before(label);
            el.on("keyup", updateLabel)
                .on("keypress", updateLabel)
                .on('keydown', updateLabel);
            function updateLabel(e) {
                if(options.limit > 0){
                    label.text("{0}/{1}".format($(this).val().length, options.limit));
                }else{
                    label.text($(this).val().length);
                }
            }
        });
    }
})(jQuery);
$(".char-counter1").charCounter();
</Script>
<script>
$(document).ready(function() {
	$("#unit_name1").attr('readonly',true);
	$("#sus_no1").attr('readonly',true);
	$("#sus_no1").val('${Edit_bed_authorizationCMD.sus_no}');
	$.post("getMNHUnitNameBySUSNo?"+key+"="+value, {y:'${Edit_bed_authorizationCMD.sus_no}'},function(j) {
		var enc = j[j.length - 1].substring(0, 16);
		var a = dec(enc, j[0]);
		$("#unit_name1").val(a);
	});
	$("#command").val('${Edit_bed_authorizationCMD.command}');
	$("#off_auth").val('${Edit_bed_authorizationCMD.off_auth}');
	$("#jco_or_auth").val('${Edit_bed_authorizationCMD.jco_or_auth}');
	$("#off_fam_auth").val('${Edit_bed_authorizationCMD.off_fam_auth}');
	$("#jco_or_fam_auth").val('${Edit_bed_authorizationCMD.jco_or_fam_auth}');
	$("#total_all").val('${Edit_bed_authorizationCMD.total_all}');
	$("#laid_out").val('${Edit_bed_authorizationCMD.laid_out}');
	$("#others").val('${Edit_bed_authorizationCMD.others}');
	$("#id").val('${Edit_bed_authorizationCMD.id}');

	 if('${sus1}' != "" ){
		$("#divPrint").show();
	} 
	
	if('${size}' == 0 && '${size}' != ""){
		$("#divPrint").show();
	} 
	var q = '${sus1}';
	if(q != ""){ 
		$("#sus_no1").val(q);
	}
	
	
	

	var d = new Date();
	
	$('#unit_name1').change(function(){
		var y = this.value;
		
		$.post("getMNHSUSNoByUnitName?"+key+"="+value, {y:y},function(j) {
			var enc = j[j.length-1].substring(0,16);
			var a = dec(enc,j[0]);
			getCommand(a);
		});
		
    }); 
	
	$('#sus_no1').change(function(){
		var y = this.value;
		getCommand(y);
    });   
	
	
});
</script>