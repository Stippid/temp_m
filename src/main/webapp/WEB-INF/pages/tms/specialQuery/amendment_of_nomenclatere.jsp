<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<form:form name="amendment_of_nomenclatere" id="amendment_of_nomenclatere" action="amendment_of_nomenclatereAction" method="post" class="form-horizontal" commandName="amendment_of_nomenclatereCMD">
	<div class="animated fadeIn">
		<div class="">
			<div class="container" align="center">
				<div class="card">
					<div class="card-header"><h3>AMENDMENT OF VEH NOMENCLATURE</h3></div>
					<div class="card-body card-block">
								<div class="col-md-12">
                                        <div class="col-md-6">
                                           <div class="row form-group">
								<div class=" col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>BA No</label>
								</div>
								<div class=" col-md-8">
									<input type="text" id="ba_no" name="ba_no"class="form-control autocomplete" autocomplete="off" maxlength="10" onchange="getveh_cat();">
                                    <input type="hidden" id="vehicle_category" name="vehicle_category" value="">
								</div>
							</div>
                                   </div>
                                <div class="col-md-6">
                                  </div>
                           </div>
					</div>

					<div style="margin-left: 700px; padding-bottom: 10px;">						
						<input type="button" class="btn btn-primary btn-sm" value="Search" id="search_ba" onclick="return existingBANo();"> 
					</div>
					
					<div class="card-body card-block" style="display: none;" id="kk">
						<div class="col-md-11 ">
							<div class="row form-group">
								<div class="col col-md-2">
									<label class=" form-control-label"><strong
										style="color: red;"></strong>MCT</label>
								</div>
								<div class="col-12 col-md-10">
									
									<input type="text" id="mct_no"name="mct_no" class="form-control autocomplete"  autocomplete="off">
								</div>
							</div>
						</div>
						<div class="col-md-11 col-md-offset-1">
							<div class="row form-group">
								<div class="col col-md-2">
									<label class=" form-control-label">Nomenclature</label>
								</div>
								<div class="col-12 col-md-10">
									<input type="text" id="std_nomclature" name="std_nomclature" class="form-control autocomplete" autocomplete="off">
								</div>
							</div>
						</div>
					</div>
					<div class="form-control card-footer" >
						<a href="amendment_of_nomenclatere" class="btn btn-success btn-sm" >Clear</a> 
						<input type="submit" class="btn btn-primary btn-sm" value="Update" style="display: none;" id="update_btn">
					</div>
               </div>
           </div>
       </div>
   </div>
</form:form>

<script>
function existingBANo(){
	 var ba_no = document.getElementById("ba_no").value;
	 
	if(ba_no == "")
	{
		alert("Please Select BA No.");
	}
	else
	{
		 $.ajax({
		        type: 'POST',
		        url: "getSearchBaNoDir?"+key+"="+value,
		        data: {ba_no:ba_no},
		       	success: function(response) {
		       		if(response == "Success dtl"){ 
		       			document.getElementById('kk').style.display='block'; 
		       			document.getElementById('update_btn').style.display='inline-block'; 
		       			
		       			var ba_no = $("#ba_no").val();
		       			$('#search_ba').attr("disabled", true);
		       			$("#ba_no").attr('readonly',true);
		       		    $('#btn_submit').attr('disabled',true);
		       			
			       		 $.post("getBaNoToMCT?"+key+"="+value, {ba_no:ba_no}).done(function(j) {				
			       			for ( var i = 0; i < j.length; i++) {
		       		   	 		
				       		   	  var length = j.length-1;
						          var enc = j[length].substring(0,16);
						          $("#mct_no").val(dec(enc,j[0]));

		       		   	 		var mct = dec(enc,j[0]);
			       		   	 	$.post("getMctNotoStdNomenclatureList?"+key+"="+value, {mct:dec(enc,j[0])}).done(function(j) {				
			       		   	 	var length = j.length-1;
					        	var enc = j[length].substring(0,16);
					        	$("#std_nomclature").val(dec(enc,j[0]));
			       			   	 	
			       				}).fail(function(xhr, textStatus, errorThrown) {
			       				});
		       		   		}
			 			}).fail(function(xhr, textStatus, errorThrown) {
			 			});
		        	}
		        	else{
		        		alert("Data Does Not Exists.");
		        	}	
		        }
		 }); 
	}
}
</script>

<script>
$(document).ready(function() {

});


$(function() {
	
    $("#ba_no").keypress(function(){
			var ba_no = this.value;
		 	var susNoAuto=$("#ba_no");
		  	susNoAuto.autocomplete({
		  	source: function( request, response ) {
		        $.ajax({
		        	type: 'POST',
		        	url: "getActiveBaNoList?"+key+"="+value,
		        	data: {ba_no:ba_no},
		        	success: function( data ) {
		        		  var susval = [];
			        	  var length = data.length-1;
			        	  var enc = data[length].substring(0,16);
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
		        } else {
		        	alert("Please Enter BA No.");
		        	document.getElementById("ba_no").value="";
		        	susNoAuto.val("");	        	  
		        	susNoAuto.focus();
		        	return false;	             
		    	}   	         
		    }, 
		    select: function( event, ui ) {
		      	var ReName = ui.item.value;
				
			} 	     
		});
	});
	
});

$(function() {

	$("input#mct_no").keyup(function(){
        var mct = this.value;
        var mct_numberAuto=$("#mct_no");
        var type_of_vehicle = document.getElementById("vehicle_category").value ; // 2 changes 
        if(type_of_vehicle == "0"){
                alert("please select  Type of Vehicle.");
                $("#mct").focus();
                 $("#std_nomclature").val("");
             mct_numberAuto.val("");
                return false;
        }
        mct_numberAuto.autocomplete({
              source: function( request, response ) {
                $.ajax({
                type: 'POST',
                url: "getMctNoDetailList?"+key+"="+value,
                data: {mct:mct ,type_of_vehicle:type_of_vehicle},
                  success: function( data ) {
                	  var susval = [];
		        	  var length = data.length-1;
		        	  var enc = data[length].substring(0,16);
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
                  } else {
                          alert("Please Enter Valid MCT NO.");
                          $("#std_nomclature").val("");
                          mct_numberAuto.val("");                          
                          mct_numberAuto.focus();
                          return false;                     
                    }                    
            }, 
                  select: function( event, ui ) {
                      var mct_num = ui.item.value;
                      $.post("getMctNotoStdNomenclatureList?"+key+"="+value, {mct:mct_num}).done(function(j) {				
                    	    var length = j.length-1;
  			        		var enc = j[length].substring(0,16);
  			        		$("#std_nomclature").val(dec(enc,j[0]));
          			}).fail(function(xhr, textStatus, errorThrown) {
          			});
                }
        });
});


	$("input#std_nomclature").keyup(function(){
		var mctNomen = this.value;
		  var type_of_vehicle = document.getElementById("vehicle_category").value ; // 2 changes 
		  var mct_nomenAuto=$("#std_nomclature");
		
		if(type_of_vehicle == "0"){
			alert("please select  Type of Vehicle.");
			$("#std_nomclature").focus();
			$("#mct_no").val("");
	    	mct_nomenAuto.val("");	  
			return false;
		}
		mct_nomenAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        type: 'POST',
		        url: "getStdNomenclatureListFromVeh_cat?"+key+"="+value,
		        data: {mctNomen:mctNomen,type_of_vehicle:type_of_vehicle},
		          success: function( data ) {
		        	  var susval = [];
		        	  var length = data.length-1;
		        	  var enc = data[length].substring(0,16);
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
		          } else {
		        	  alert("Please Enter Valid Std Nomenclature.");
		        	  $("#mct_no").val("");
		        	  mct_nomenAuto.val("");	        	  
		        	  mct_nomenAuto.focus();
		        	  return false;	             
		    	}   	         
		    }, 
		  	select: function( event, ui ) {
		      	var nomencatre = ui.item.value;
		      	$.post("getStdNomenclaturetoMctNoList?"+key+"="+value, {std_nomclature:nomencatre}).done(function(data) {				
		      		var length = data.length-1;
		        	var enc = data[length].substring(0,16);
		        	$("#mct_no").val(dec(enc,data[0]));
			        	$.post("getMctNotoStdNomenclatureList?"+key+"="+value, {mct : dec(enc,data[0])}).done(function(j) {				
			        		var length = j.length-1;
				        	var enc = j[length].substring(0,16);
				        	$("#std_nomclature").val(dec(enc,j[0]));
						}).fail(function(xhr, textStatus, errorThrown) {
						});

				}).fail(function(xhr, textStatus, errorThrown) {
				});
			}
		});
	});
});	
function getveh_cat(){
	var ba_no = $("#ba_no").val();	
	var vehicle_category;
	$.post("getvehcatfromBANo?"+key+"="+value, {ba_no:ba_no}).done(function(k) {
		vehicle_category = k;
		document.getElementById("vehicle_category").value = vehicle_category;
	}).fail(function(xhr, textStatus, errorThrown){});
}
</script>

