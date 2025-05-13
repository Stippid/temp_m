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

<form:form action="ODPcasesAction" id="ODPcases" method="post" class="form-horizontal" commandName="ODPcasesCMD">
	<div class="container" align="center">
		<div class="card">
			<div class="card-header ">
				<h5>OPD DETAILS</h5>
				<h6>
					<span style="font-size: 12px; color: red">(To be entered by Medical Unit)</span>
				</h6>
			</div>

			<div class="card-body card-block">
				<div class="row">
					<div class="col-md-12">
						<div class="col-md-8">
							<div class="row form-group">
								<div class="col-md-3">
									<label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Hospital Name</label>
								</div>
								<div class="col-md-9">
									<input type="text" id="unit_name" name="unit_name" value="${unit_name}" class="form-control-sm form-control" placeholder="Search..." autocomplete="off" maxlength="100" 
									title="Type Unit Name or Part of Unit Name to Search">
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong>SUS No</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="sus_no" name="sus_no" value="${sus_no}"class="form-control-sm form-control" placeholder="Search..."
										autocomplete="off" maxlength="8"  title="Type SUS No or Part of SUS No to Search" />
								</div>
							</div>
						</div>
					</div>
					  <div class="col-md-12">
                                                <div class="col-md-8">
                                                        <div class="row form-group">
                                                                <div class="col-md-3">
                                                                        <label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Command</label>
                                                                </div>

                                                                <div class="col-md-9">
                                                                        <input type="text" id="command" name="command" class="form-control-sm form-control" readonly> 
                                                                        <input type="hidden" id="command_sus" name="command_sus" class="form-control-sm form-control">
                                                                </div>
                                                        </div>
                                                </div>
                                                <div class="col-md-4">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;"> </strong>Ward</label>
								</div>
								<div class="col-md-8">
									 <select name="ward" id="ward" class="form-control-sm form-control" onchange="Clearvalue()" >
							<option value="-1">--Select--</option>
							<c:forEach var="item" items="${m12}" varStatus="num">
								<option value="${item}" name="${item}">${item}</option>
							</c:forEach>
						</select>
								</div>
							</div>
						</div>
                                          </div>
                           <div class="col-md-12">
                        <div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Quarter</label>
							</div>
							<div class="col-md-8">
								 <select name="quater" id="quater" class="form-control-sm form-control">
                                             <option value="-1">--Select--</option>
                                             <c:forEach var="item" items="${getMedSystemCodeQuaQUARTER}" varStatus="num">
                                                     <option value="${item[0]}" name="${item[1]}">${item[1]}</option>
                                             </c:forEach>
                                     </select>
							</div>
						</div>
					</div>
                   <div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									 <label class=" form-control-label">Year</label>
								</div>
								<div class="col-md-8">
								 <input type="text" id="year" name="year" class="form-control-sm form-control" autocomplete="off"
                                   maxlength="4" onchange="Checkyear(this)" onkeypress="return isNumberPointKey(event);">
								</div>
							</div>
						</div>
					</div>
                                        

					<div class="col-md-12">

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label">Officer</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="officer_self" name="officer_self" class="form-control-sm form-control" value="0"
									onkeypress="return isNumberPointKey(event)" maxlength="10" autocomplete="off" onkeyup="totalcal();">
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">Families</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="officer_family" name="officer_family" class="form-control-sm form-control" value="0"
									onkeypress="return isNumberPointKey(event)" maxlength="10" autocomplete="off" onkeyup="totalcal();">
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label">JCO</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="jco_ors_self" name="jco_ors_self" class="form-control-sm form-control" value="0"
									onkeypress="return isNumberPointKey(event)" maxlength="10" autocomplete="off" onkeyup="totalcal();">
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">Families</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="jco_ors_family" name="jco_ors_family" class="form-control-sm form-control" value="0"
								   onkeypress="return isNumberPointKey(event)" maxlength="10" autocomplete="off" onkeyup="totalcal();">
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label">ESM</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="ex_serv_self" name=ex_serv_self class="form-control-sm form-control" value="0"
									onkeypress="return isNumberPointKey(event)" maxlength="10" autocomplete="off" onkeyup="totalcal();">
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">Families</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="ex_serv_family" name="ex_serv_family" class="form-control-sm form-control" value="0"
									onkeypress="return isNumberPointKey(event)" maxlength="10" autocomplete="off" onkeyup="totalcal();">
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label">Para Mil Pers</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="para_mil_pers_self" name="para_mil_pers_self" class="form-control-sm form-control"
									value="0" onkeypress="return isNumberPointKey(event)" maxlength="10" autocomplete="off" onkeyup="totalcal();">
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">Families</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="para_mil_pers_family" name="para_mil_pers_family" class="form-control-sm form-control" value="0"
										onkeypress="return isNumberPointKey(event)" maxlength="10" autocomplete="off" onkeyup="totalcal();">
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label">Defence Civilians</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="civilian_self" name="civilian_self" class="form-control-sm form-control" value="0"
									onkeypress="return isNumberPointKey(event)" maxlength="10" autocomplete="off" onkeyup="totalcal();">
								</div>
							</div>
						</div>
 				<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">Families</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="civilian_family" name="civilian_family" class="form-control-sm form-control" value="0"
									onkeypress="return isNumberPointKey(event)" maxlength="10" autocomplete="off" onkeyup="totalcal();">
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">Total</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="total_during_month" name="total_during_month" class="form-control-sm form-control"
										value="0" readonly="readonly" autocomplete="off" maxlength="10" />
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12">
						<div class="col-md-2">
							<label for="text-input" class=" form-control-label">Remarks</label>
						</div>
						<div class="col-md-10" >
							<textarea rows="2" cols="250" class="form-control char-counter1" maxlength="250" value="Enter Your Remarks..." id="remarks" name="remarks"></textarea>
						</div>
					</div>
				</div>
			</div>
			<div class="card-footer" align="center">
				<a href="mnh_input_opd_cases" class="btn btn-primary btn-sm" id="btn_clc" >Clear</a>
				<input type="submit" class="btn btn-success btn-sm" value="Save" onclick="return validate1();"> 
			</div>
		</div>
	</div>
</form:form>



<!-- for Functions -->
<script>
function btn_clc(){
	location.reload(true);
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

function Checkyear(obj){
   var d = new Date();
   var year = d.getFullYear() - 1;
   if(obj.value < year ){
           $("#"+obj.id).focus();
           alert("Please Enter the Valid Year");
           $("#"+obj.id).val("");
   }
}
function totalcal(){
	var a1 = $("#officer_self").val();
	if(a1 == "" || a1 == null){
		a1 = 0;
	}
	var a2 = $("#officer_family").val();
	if(a2 == "" || a2 == null){
		a2 = 0;
	}
	var a3 = $("#jco_ors_self").val();
	if(a3 == "" || a3 == null){
		a3 = 0;
	}
	var a4 = $("#jco_ors_family").val();
	if(a4 == "" || a4 == null){
		a4 = 0;
	}
	var a5 = $("#ex_serv_self").val();
	if(a5 == "" || a5 == null){
		a5 = 0;
	}
	var a6 = $("#ex_serv_family").val();
	if(a6 == "" || a6 == null){
		a6 = 0;
	}
	var a7 = $("#para_mil_pers_self").val();
	if(a7 == "" || a7 == null){
		a7 = 0;
	}
	var a8 = $("#para_mil_pers_family").val();
	if(a8 == "" || a8 == null){
		a8 = 0;
	}
	var a9 = $("#civilian_self").val();
	if(a9 == "" || a9 == null){
		a9 = 0;
	}
	var a10 = $("#civilian_family").val();
	if(a10 == "" || a10 == null){
		a10 = 0;
	}
	var t1 = (parseInt(a1)+parseInt(a2)+parseInt(a3)+parseInt(a4)+parseInt(a5)+parseInt(a6)+parseInt(a7)+parseInt(a8)+parseInt(a9)+parseInt(a10));
	$("#total_during_month").val(t1);
}

function defaultval(){
	if($("#officer_self").val() == "") {
		$("#officer_self").val('0');
	}
	if($("#officer_family").val() == "") {
		$("#officer_family").val('0');
	}
	if($("#jco_ors_self").val() == "") {
		$("#jco_ors_self").val('0');
	}
	if($("#jco_ors_family").val() == "") {
		$("#jco_ors_family").val('0');
	}
	if($("#ex_serv_self").val() == "") {
		$("#ex_serv_self").val('0');
	}
	if($("#ex_serv_family").val() == "") {
		$("#ex_serv_family").val('0');
	}
	if($("#para_mil_pers_self").val() == "") {
		$("#para_mil_pers_self").val('0');
	}
	if($("#para_mil_pers_family").val() == "") {
		$("#para_mil_pers_family").val('0');
	}
	if($("#civilian_self").val() == "") {
		$("#civilian_self").val('0');
	}
	if($("#civilian_family").val() == "") {
		$("#civilian_family").val('0');
	}
	if($("#total_during_month").val() == "") {
		$("#total_during_month").val('0');
	}
}

function validate1(){
	var d = new Date();
	var year = d.getFullYear();
	
	if($("#unit_name").val() == ""){
		alert("Please Enter the Unit Name");
		$("#unit_name").focus();
		return false;
	}
	if($("#sus_no").val() == ""){
		alert("Please Enter the SUS No");
		$("#sus_no").focus();
		return false;
	}	
	if($("#quater").val() == "-1"){
		alert("Please Select the Quarter");
		$("#quater").focus();
		return false;
	}
	if(quarter_validate($("#quater").val()) == 0){
		 alert("Future Quarter is not allowed to select");
		 return false;
	}
	if($("#year").val() > year){
		alert("Future Year cannot be allowed");
		$("#year").focus();
		return false;
	} 
	
/* 	else{
		alert("hii");
		var q1 = $("#sus_no").val();
		var q3 = $("#quater").val();
		var q4 = $("#year").val();

		if(q2 != ""){
			$("#sun_no").attr('readonly',false);
			$("#unit_name").attr('readonly',false);
			defaultval();
			$("#EditODPcases").submit();
		}
		else{
			$.post("checkDetailsOfopdcases?"+key+"="+value, {d1:q1,d2:q3,d3:q4}, function(j){
				var enc;
				var a;
				if(j != ""){
					enc = j[j.length-1].substring(0,16);
					a = dec(enc,j[0]);
				}
					
			    if(q1 == a){
					alert("SUS No already exists against Quarter & Year");	
					$("#sus_no").focus();
					$("#quater").focus();
					$("#year").focus();
				}  else{
					$("#sus_no").attr('readonly',false);
					$("#unit_name").attr('readonly',false);
					defaultval();
					$("#ODPcases").submit();
				}
			});
		}
	} */
	//return true;
	
}	
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

var key = "${_csrf.parameterName}";
var value = "${_csrf.token}";


jQuery(function() {
	// Source SUS No

	jQuery("#sus_no").keypress(function(){
		var sus_no = this.value;
			 var susNoAuto=jQuery("#sus_no");
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
			        	  document.getElementById("sus_no").value="";
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
  			        	  	document.getElementById("unit_name").value="";
  			        	  	susNoAuto.val("");
  			        	  	susNoAuto.focus();
  	 			      	}else{
	    	 	   	        	var length = j.length-1;
	    	    				var enc = j[length].substring(0,16);
	    	    				$("#unit_name").val(dec(enc,j[0]));	
	    	 	   	        		
  	 	   	        	}
  	 				}).fail(function(xhr, textStatus, errorThrown) {
  	 				});
			      } 	     
			});	
	});
	// End
	
	// Source Unit Name

    jQuery("#unit_name").keypress(function(){
 			var unit_name = this.value;
				 var susNoAuto=jQuery("#unit_name");
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
				        	  document.getElementById("unit_name").value="";
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
				 				         jQuery("#sus_no").val(dec(enc,j[0]));	
									}).fail(function(xhr, textStatus, errorThrown) {
							});
					} 	     
				}); 			
 			});

});
</script>
<!-- for On Load Methods -->
<script>
$(document).ready(function() {
	
	if('${sus_no}' != ""){
		
		getCommand('${sus_no}');	
	}
	
	
	if('${sus1}' != "" || '${unit1}' != ""){	
		$("#divPrint").hide();
	}
	
	var d = new Date();
	var year = d.getFullYear();
	$("#year").val(year);
	

	$('#unit_name').change(function(){
		var y = this.value;
		
		$.post("getMNHSUSNoByUnitName?"+key+"="+value, {y:y},function(j) {
			var enc = j[j.length-1].substring(0,16);
			var a = dec(enc,j[0]);
			getCommand(a);
		});
		
    }); 
	
	$('#sus_no').change(function(){
		var y = this.value;
		getCommand(y);
    });  	
	
});
</script>
