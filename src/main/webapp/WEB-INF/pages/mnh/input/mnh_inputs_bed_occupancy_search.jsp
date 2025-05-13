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

<form:form action="" id="" method="post"  class="form-horizontal">
<div class="container" align="center">
          <div class="card">
              <div class="card-header">
		     
		             <b>SEARCH BED OCCUPANCY</b>
		             
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
									<input type="text" id="unit_name1" name="unit_name1" value="${unit_name}" class="form-control-sm form-control" placeholder="Search..." autocomplete="off" maxlength="100" 
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
									<input type="text" id="sus_no1" name="sus_no1" value="${sus_no}"class="form-control-sm form-control" placeholder="Search..."
										autocomplete="off" maxlength="8"  title="Type SUS No or Part of SUS No to Search" />
								</div>
							</div>
						</div>
					</div>
  					<div class="col-md-12">
						<div class="col-md-8">
							<div class="row form-group">
								<div class="col-md-3">
								 <label for="text-input" class=" form-control-label">Quarter</label>								
								 </div>
								<div class="col-md-9">
									<select name="quater" id="quater"  class="form-control-sm form-control">
               					     <option value="-1">--Select--</option>
									<c:forEach var="item" items="${getMedSystemCodeQuarter}" varStatus="num">
										<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
									</c:forEach>
					         </select>
					         </div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="row form-group">
								<div class="col-md-4">
								 <label for="text-input" class=" form-control-label">Year</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="year" name="year" class="form-control-sm form-control" 
	               		      autocomplete="off" maxlength="4" onchange="yrlength();"onkeypress="return isNumberPointKey(event);">
  							<!-- Checkyear(this); --> 	               	  
							</div>
							</div>
						</div>
					</div>
             <div class="col-md-12">
                        <div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
			                <label class=" form-control-label"><strong style="color: red;">* </strong>Service Status</label>
							</div>
							<div class="col-md-8">
								<select name="type" id="type" class="form-control-sm form-control">		           					
           					     <option value="-1">--Select--</option>
							<c:forEach var="item" items="${service_type}" varStatus="num">
								<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
							</c:forEach>			               								
						    </select>
                                   
							</div>
							</div>
						</div>
					</div>
			
				</div>
			</div>
             <div class="card-footer" align="center">
             	<a href="mnh_input_search_bed_occupancy"class="btn btn-primary btn-sm" id="btn_cancl" >Clear</a>				
				 <i class="fa fa-search"></i><input type="button"  class="btn btn-success btn-sm"   value="Search" onclick="searchData();"> 
				
			 </div>
          </div>
          
			
    
      
      <div class="card-body" id="divPrint" style="display: none;">
				   <div id="divShow" style="display: block;">
				   
		                 <table id="SearchReport" class="table no-margin table-striped  table-hover  table-bordered report_print">
		                      <thead>
		                          <tr>
			                         <th style="text-align: center;" width="5%">Ser No</th>                                                                              
                                     <th style="text-align: center;">Unit Name</th>
                                     <th style="text-align: center;">OFFICER Total</th>
                                     <th style="text-align: center;">JCOs/OR Total</th>
                                     <th style="text-align: center;">OFFICER FAMILY Total</th>
                                     <th style="text-align: center;">JCOs/OR FAMILY Total</th>
                                     <th style="text-align: center;">Action</th>
		                          </tr>                                                        
		                      </thead> 
		                      <tbody>
		                           <c:if test="${not empty list}">
		                                <c:forEach var="item" items="${list}" varStatus="num" >
							               <tr>
											   <th style="text-align: center;" width="5%">${num.index+1}</th>
											   <th style="text-align: left;">${item.unit_name}</th>
											   <th style="text-align: center;">${item.ofcr_tot}</th>
											   <th style="text-align: center;">${item.jco_tot}</th>
											   <th style="text-align: center;">${item.ofcr_fmly_tot}</th>
											   <th style="text-align: center;">${item.jco_fmly_tot}</th>
											   <th id="thAction1"  style="text-align: center;">${item.id}</th>
								            </tr>
							            </c:forEach>
		                           </c:if>
		                     </tbody>
		                     <c:if test="${list.size()==0}">
						<tr>
							<td style="font-size: 15px; text-align: center; color: red;"
								colspan="17">Data Not Available</td>
						</tr>
					</c:if>
		                 </table>
		            </div>	
		            </div>
	    </div> 	
	
</form:form>

<c:url value="search_bed_occupancy_Input" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="sus1">
     <input type="hidden" name="sus1" id="sus1"/>
     <input type="hidden" name="unit1" id="unit1"/>
     <input type="hidden" name="type1" id="type1"/>
     <input type="hidden" name="qtr1" id="qtr1"/>
     <input type="hidden" name="yr1" id="yr1"/>
</form:form>
 		
<c:url value="edit_bed_occupancy_Input" var="editUrl" />
<form:form action="${editUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="id2">
     <input type="hidden" name="id2" id="id2"/>
     <input type="hidden" name="type2" id="type2"/>
</form:form>	






<script>
function btn_clc(){
	
	$("#unit_name1").val('');
	$("#sus_no1").val('');
	$("#type").val('-1');
	$("#quater").val('-1');
	var d = new Date();
	var year = d.getFullYear();
	$("#year").val(year);
}
function yrlength(){
	if($("#year").val().length < 4){
		alert("Please Enter Valid Year");
		$("#year").focus();
		return false;
	}
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

function searchData(){
	
	var d = new Date();
	var c_d = d.getFullYear();
	
	if ($("#unit_name1").val() == "") {
		alert("Please Enter the Unit Name");
		$("#unit_name1").focus();
		return false;
	}
	if ($("#sus_no1").val() == "") {
		alert("Please Enter the SUS No");
		$("#sus_no1").focus();
		return false;
	}
	if($("#type").val() == "-1"){
		alert("Please Select the Type");
		$("#type").focus();
		return false;
	}
	if($("#year").val().length < 4){
		alert("Year Format Require in YYYY");
		$("#year").focus();
		return false;
	}
	if($("#year").val() > c_d){
		$("#year").focus();
		alert("Can't select the Future Year");
		return false;
	}

	$("#sus1").val($("#sus_no1").val());
	$("#unit1").val($("#unit_name1").val());
	$("#type1").val($("#type").val());
	
	$("#qtr1").val($("#quater").val());
	$("#yr1").val($("#year").val());
	$("#searchForm").submit();
}
	
function editData(id){	
	$("#id2").val(id);
	$("#type2").val($("#type").val());
	$("#updateForm").submit();
}


function deleteData(id){	
	var type = $("#type").val();
	$.post("delete_bed_occupancy_Input?"+key+"="+value,{deleteid:id,type:type}, function(j) {
		alert(j);
		searchData();
	}); 
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
	
	
	if('${sus1}' != "" || '${unit1}' != ""){	
		$("#divPrint").hide();
	}
	var d = new Date();
	var year = d.getFullYear();
	$("#year").val(year);
	
	if('${size}' != 0){
		$("#divPrint").show();
	} 
	
	if('${size}' == 0 && '${size}' != ""){
		$("#divPrint").show();
	}
	
	
	var q = '${sus1}';
	var q1 = '${unit1}';
	var q2 = '${type1}';
	
	var q3 = '${qtr1}';
	var q4 = '${yr1}';
	
	if(q != ""){
		$("#sus_no1").val(q);
	}
	
    if(q1 != ""){
    	$("#unit_name1").val(q1);
	}
   
     if(q2 != ""){
    	 $("#type").val(q2);
	} 
    
    if(q3 != ""){
    	$("#quater").val(q3);
	}
    
    if(q4 != ""){
    	$("#year").val(q4);
	}
    
});
</script>