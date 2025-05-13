jQuery(document).ready(function() {
	//Oprational
	jQuery('select#op_comd').change(function() {
   		var fcode = this.value;
   		getOPCorps(fcode);
   		
   		fcode += "00";
   		getOPDiv(fcode);
   		
   		fcode += "000";
   		getOPBde(fcode);
   	});
	jQuery('select#op_corps').change(function() {
       	var fcode = this.value;
       	getOPDiv(fcode);
       	fcode += "000";
       	getOPBde(fcode);
   	});
   	jQuery('select#op_div').change(function() {
       	var fcode = this.value;    	   	
	   	getOPBde(fcode);
   	});
   	
   	//Control
   	jQuery('select#cont_comd').change(function() {
   		var cont_cname = jQuery(this).find('option:selected').attr("name");    	   	
        jQuery("#cont_cname").val(cont_cname);
     	jQuery("#cont_aname").val("");
  		jQuery("#cont_bname").val("");
		jQuery("#cont_dname").val("");
   		
   		var fcode = this.value;
       	getCONTCorps(fcode);
       	fcode += "00";	
   		getCONTDiv(fcode);
       	fcode += "000";	
       	getCONTBde(fcode);
   	});
   	jQuery('select#cont_corps').change(function() {
   		var cont_aname = jQuery(this).find('option:selected').attr("name");    	   	
        jQuery("#cont_aname").val(cont_aname);
     	jQuery("#cont_bname").val("");
		jQuery("#cont_dname").val("");
	        
       	var fcode = this.value;
       	getCONTDiv(fcode);
       	
       	fcode += "000";	
       	getCONTBde(fcode);
   	});
   	jQuery('select#cont_div').change(function() {
   		var cont_dname = jQuery(this).find('option:selected').attr("name");    	   	
        jQuery("#cont_dname").val(cont_dname);
        jQuery("#cont_bname").val("");
		var fcode = this.value;    	   	
	   	getCONTBde(fcode);
   	});
   	
   	jQuery('select#cont_bde').change(function() {
   		var cont_bname = jQuery(this).find('option:selected').attr("name");    	   	
        jQuery("#cont_bname").val(cont_bname);
   	});
   	
   	//Admin
	jQuery('select#adm_comd').change(function() {
   		var fcode = this.value;
       	getADMCorps(fcode);
       	fcode += "00";
       	getADMDiv(fcode);
       	fcode += "000";	
       	getADMBde(fcode);
   	});
   	jQuery('select#adm_corps').change(function() {
       	var fcode = this.value;
       	getADMDiv(fcode);
       	fcode += "000";	
       	getADMBde(fcode);
   	});
   	jQuery('select#adm_div').change(function() {
       	var fcode = this.value;    	   	
	   	getADMBde(fcode);
   	});
	
});



	function getOPCorps(fcode){
		var fcode1 = fcode[0];
		$.post("getCorpsDetailsList?"+key+"="+value,{fcode:fcode1}, function(j) {
			if(j != ""){
				var length = j.length-1;
				var enc = j[length][0].substr(0,16);
				var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
				for ( var i = 0; i < length; i++) {
					if(fcode ==  dec(enc,j[i][0])){
						options += '<option value="' + dec(enc,j[i][0]) +'" name="'+dec(enc,j[i][1])+'" selected=selected >'+ dec(enc,j[i][1]) + '</option>';
					}else{
						options += '<option value="' + dec(enc,j[i][0]) +'" >'+ dec(enc,j[i][1]) + '</option>';
					}
				}	
				jQuery("select#op_corps").html(options);
			}else{
				var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
				jQuery("select#op_corps").html(options);
			}
		});
	}
	function getOPDiv(fcode){
		var fcode1 = fcode[0] + fcode[1] + fcode[2];
		$.post("getDivDetailsList?"+key+"="+value,{fcode:fcode1}, function(j) {
			if(j != ""){
				var length = j.length-1;
				var enc = j[length][0].substr(0,16);
				var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
				for ( var i = 0; i < length; i++) {
					if(fcode ==  dec(enc,j[i][0])){
						options += '<option value="' + dec(enc,j[i][0]) +'" name="'+dec(enc,j[i][1])+'" selected=selected >'+ dec(enc,j[i][1]) + '</option>';
					}else{
						options += '<option value="' + dec(enc,j[i][0]) +'" >'+ dec(enc,j[i][1]) + '</option>';
					}
				}	
				jQuery("select#op_div").html(options);
			}else{
				var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
				jQuery("select#op_div").html(options);
			}
		});
	} 
	function getOPBde(fcode){
		var fcode1 = fcode[0] + fcode[1] + fcode[2] + fcode[3] + fcode[4] + fcode[5];
		//alert(fcode1)
		$.post("getBdeDetailsList?"+key+"="+value,{fcode:fcode1}, function(j) {
			if(j != ""){
				var length = j.length-1;
				var enc = j[length][0].substr(0,16);
				var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
				for ( var i = 0; i < length; i++) {
					if(fcode ==  dec(enc,j[i][0])){
						options += '<option value="' + dec(enc,j[i][0]) +'" name="'+dec(enc,j[i][1])+'" selected=selected >'+ dec(enc,j[i][1]) + '</option>';
					}else{
						options += '<option value="' + dec(enc,j[i][0]) +'" >'+ dec(enc,j[i][1]) + '</option>';
					}
				}	
				jQuery("select#op_bde").html(options);
			}else{
				var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
				jQuery("select#op_bde").html(options);
			}
			
		});
	}

// Control

	function getCONTCorps(fcode){
		var fcode1 = fcode[0];
		$.post("getCorpsDetailsList?"+key+"="+value,{fcode:fcode1}, function(j) {
			if(j != ""){
				var length = j.length-1;
				var enc = j[length][0].substring(0,16);
				var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
				for ( var i = 0; i < length; i++) {
					if(fcode == dec(enc,j[i][0])){
						options += '<option value="' + dec(enc,j[i][0]) + '" name="'+dec(enc,j[i][1])+'" selected=selected>'+ dec(enc,j[i][1]) + '</option>';
						jQuery("#cont_aname").val(dec(enc,j[i][1]));
					}else{
						options += '<option value="' + dec(enc,j[i][0]) +'" name="'+dec(enc,j[i][1])+'">'+ dec(enc,j[i][1]) + '</option>';
					}
				}	
				jQuery("select#cont_corps").html(options);
			}else{
				var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
				jQuery("select#cont_corps").html(options);
			}
		});
	} 
	function getCONTDiv(fcode){
		var fcode1 = fcode[0] + fcode[1] + fcode[2];
	   $.post("getDivDetailsList?"+key+"="+value,{fcode:fcode1}, function(j) {
		   if(j != ""){
			   	var length = j.length-1;
				var enc = j[length][0].substring(0,16);
				var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
				for ( var i = 0; i < length; i++) {
					if(fcode == dec(enc,j[i][0])){
						options += '<option value="' + dec(enc,j[i][0]) + '" name="'+dec(enc,j[i][1])+'" selected=selected>'+ dec(enc,j[i][1]) + '</option>';
						jQuery("#cont_dname").val(dec(enc,j[i][1]));
					}else{
						options += '<option value="' + dec(enc,j[i][0]) +'" name="'+dec(enc,j[i][1])+'">'+ dec(enc,j[i][1]) + '</option>';
					}
				}	
				jQuery("select#cont_div").html(options);
		   }else{
				var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
				jQuery("select#cont_div").html(options);
			}
		});
	} 
	function getCONTBde(fcode){
		var fcode1 = fcode[0] + fcode[1] + fcode[2] + fcode[3] + fcode[4] + fcode[5];
	  $.post("getBdeDetailsList?"+key+"="+value,{fcode:fcode1}, function(j) {
		  if(j != ""){
				var length = j.length-1;
				var enc = j[length][0].substring(0,16);
				var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
				for ( var i = 0; i < length; i++) {
					if(fcode == dec(enc,j[i][0])){
						options += '<option value="' + dec(enc,j[i][0]) + '" name="'+dec(enc,j[i][1])+'" selected=selected>'+ dec(enc,j[i][1]) + '</option>';
						jQuery("#cont_bname").val(dec(enc,j[i][1]));
					}else{
						options += '<option value="' + dec(enc,j[i][0]) +'" name="'+dec(enc,j[i][1])+'">'+ dec(enc,j[i][1]) + '</option>';
					}
				}	
				jQuery("select#cont_bde").html(options);
		  }else{
			  var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
			  jQuery("select#cont_bde").html(options);
		  }
	  });
	}

// ADM 

	function getADMCorps(fcode){
		var fcode1 = fcode[0];
		$.post("getCorpsDetailsList?"+key+"="+value,{fcode:fcode1}, function(j) {
			if(j != ""){
				var length = j.length-1;
				var enc = j[length][0].substring(0,16);
				var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
				for ( var i = 0; i < length; i++) {
					if(fcode ==  dec(enc,j[i][0])){
						options += '<option value="' + dec(enc,j[i][0]) +'" name="'+dec(enc,j[i][1])+'" selected=selected >'+ dec(enc,j[i][1]) + '</option>';
					}else{
						options += '<option value="' + dec(enc,j[i][0]) +'" >'+ dec(enc,j[i][1]) + '</option>';
					}
				}	
				jQuery("select#adm_corps").html(options);
			}else{
				 var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
				 jQuery("select#adm_corps").html(options);
			}
		});
	}
	function getADMDiv(fcode){
		var fcode1 = fcode[0] + fcode[1] + fcode[2];
		$.post("getDivDetailsList?"+key+"="+value,{fcode:fcode1}, function(j) {
			if(j != ""){
				var length = j.length-1;
				var enc = j[length][0].substring(0,16);
				var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
				for ( var i = 0; i < length; i++) {
					if(fcode ==  dec(enc,j[i][0])){
						options += '<option value="' + dec(enc,j[i][0]) +'" name="'+dec(enc,j[i][1])+'" selected=selected >'+ dec(enc,j[i][1]) + '</option>';
					}else{
						options += '<option value="' + dec(enc,j[i][0]) +'" >'+ dec(enc,j[i][1]) + '</option>';
					}
				}	
				jQuery("select#adm_div").html(options);
			}else{
				 var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
				 jQuery("select#adm_div").html(options);
			}
		});
	} 
	function getADMBde(fcode){
		var fcode1 = fcode[0] + fcode[1] + fcode[2] + fcode[3] + fcode[4] + fcode[5];
		$.post("getBdeDetailsList?"+key+"="+value,{fcode:fcode1}, function(j) {
			if(j != ""){
				var length = j.length-1;
				var enc = j[length][0].substring(0,16);
				var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
				for ( var i = 0; i < length; i++) {
					if(fcode ==  dec(enc,j[i][0])){
						options += '<option value="' + dec(enc,j[i][0]) +'" name="'+dec(enc,j[i][1])+'" selected=selected >'+ dec(enc,j[i][1]) + '</option>';
					}else{
						options += '<option value="' + dec(enc,j[i][0]) +'" >'+ dec(enc,j[i][1]) + '</option>';
					}
				}	
				jQuery("select#adm_bde").html(options);
			}else{
				 var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
				 jQuery("select#adm_bde").html(options);
			}
		});
	}
	
	
	function checkFileExt(file) {
	  	var ext = file.value.match(/\.([^\.]+)$/)[1];
		switch (ext) {
		  	case 'pdf':
		  	//alert('Allowed');
		    break;
		  	default:
		    	alert('Only *.pdf file extension allowed');
		   	file.value = "";
		}
	}
	
	
	
///////////////////////////awards and medal////////////////////////////
	
	
	//Control FOR DYNAMIC BINDING
	function commandChanged(e) {
	
	var ind = e.id.substring(20);
	var cont_cname = jQuery(e).find('option:selected').attr("name");                       
	jQuery("#cont_cname").val(cont_cname);
	jQuery("#cont_aname").val("");
	jQuery("#cont_bname").val("");
	jQuery("#cont_dname").val("");
	
	var fcode = e.value;
	getCONTCorps_d(fcode,ind);
	fcode += "00";        
	getCONTDiv_d(fcode,ind);
	fcode += "000";        
	getCONTBde_d(fcode,ind);
	}
	
	function corpsChanged(e) {
	var ind = e.id.substring(23);
	var cont_aname = jQuery(e).find('option:selected').attr("name");                       
	jQuery("#cont_aname").val(cont_aname);
	jQuery("#cont_bname").val("");
	jQuery("#cont_dname").val("");
	
	var fcode = e.value;
	getCONTDiv_d(fcode,ind);
	
	fcode += "000";        
	getCONTBde_d(fcode,ind);
	}
	
	function divChanged(e) {
	var ind = e.id.substring(24);
	var cont_dname = jQuery(e).find('option:selected').attr("name");                       
	jQuery("#cont_dname").val(cont_dname);
	jQuery("#cont_bname").val("");
	var fcode = e.value;                       
	getCONTBde_d(fcode,ind);
	}
	
	
	
	function getCONTCorps_d(fcode,ind){
	
	var fcode1 = fcode[0];
	$.post("getCorpsDetailsList?"+key+"="+value,{fcode:fcode1}, function(j) {
	if(j != ""){
	var length = j.length-1;
	var enc = j[length][0].substring(0,16);
	var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
	for ( var i = 0; i < length; i++) {
	    if(fcode == dec(enc,j[i][0])){
	            options += '<option value="' + dec(enc,j[i][0]) + '" name="'+dec(enc,j[i][1])+'" selected=selected>'+ dec(enc,j[i][1]) + '</option>';
	            jQuery("#cont_aname").val(dec(enc,j[i][1]));
	    }else{
	            options += '<option value="' + dec(enc,j[i][0]) +'" name="'+dec(enc,j[i][1])+'">'+ dec(enc,j[i][1]) + '</option>';
	    }
	}        
	jQuery("select#awardsNmedal_corps_area"+ind).html(options);
	}else{
	var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
	jQuery("select#awardsNmedal_corps_area"+ind).html(options);
	}
	});
	} 
	function getCONTDiv_d(fcode,ind){
	var fcode1 = fcode[0] + fcode[1] + fcode[2];
	$.post("getDivDetailsList?"+key+"="+value,{fcode:fcode1}, function(j) {
	if(j != ""){
	var length = j.length-1;
	var enc = j[length][0].substring(0,16);
	var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
	for ( var i = 0; i < length; i++) {
	    if(fcode == dec(enc,j[i][0])){
	            options += '<option value="' + dec(enc,j[i][0]) + '" name="'+dec(enc,j[i][1])+'" selected=selected>'+ dec(enc,j[i][1]) + '</option>';
	            jQuery("#cont_dname").val(dec(enc,j[i][1]));
	    }else{
	            options += '<option value="' + dec(enc,j[i][0]) +'" name="'+dec(enc,j[i][1])+'">'+ dec(enc,j[i][1]) + '</option>';
	    }
	}
	
	jQuery("select#awardsNmedal_div_subarea"+ind).html(options);
	}else{
	var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
	jQuery("select#awardsNmedal_div_subarea"+ind).html(options);
	}
	
	});
	} 
	function getCONTBde_d(fcode,ind){
	var fcode1 = fcode[0] + fcode[1] + fcode[2] + fcode[3] + fcode[4] + fcode[5];
	$.post("getBdeDetailsList?"+key+"="+value,{fcode:fcode1}, function(j) {
	if(j != ""){
	var length = j.length-1;
	var enc = j[length][0].substring(0,16);
	var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
	for ( var i = 0; i < length; i++) {
	    if(fcode == dec(enc,j[i][0])){
	            options += '<option value="' + dec(enc,j[i][0]) + '" name="'+dec(enc,j[i][1])+'" selected=selected>'+ dec(enc,j[i][1]) + '</option>';
	            jQuery("#cont_bname").val(dec(enc,j[i][1]));
	    }else{
	            options += '<option value="' + dec(enc,j[i][0]) +'" name="'+dec(enc,j[i][1])+'">'+ dec(enc,j[i][1]) + '</option>';
	    }
	}        
	jQuery("select#awardsNmedal_bde"+ind).html(options);
	}else{
	var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
	jQuery("select#awardsNmedal_bde"+ind).html(options);
	}
	});
	}
	
	//medal desc
	
	function getMedalDescList(e){
	var ind = e.id.substring(10);
	var cat = e.value;
	$.post("getMedalDescList?"+key+"="+value,{cat:cat}, function(j) {
	if(j != ""){
	
	var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
	for ( var i = 0; i < j.length; i++) {
	    
	            options += '<option value="' + j[i].id + '" name="'+j[i].medal_name+'" selected=selected>'+ j[i].medal_name + '</option>';
	    
	}        
	jQuery("select#select_desc"+ind).html(options);
	}else{
	var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
	jQuery("select#select_desc"+ind).html(options);
	}
	});
	}
	
	
	//unit name
	function unitData(e){
	var unit_name = e.value;
	var susNoAuto=$("#"+e.id);
	susNoAuto.autocomplete({
	source: function( request, response ) {
	$.ajax({
		type: 'POST',
		url: "PSG_CADET_getTargetUnitsNameActiveList?"+key+"="+value,
	data: {unit_name:unit_name},
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
		  alert("Please Enter Approved Unit Name.");
		  susNoAuto.val("");	        	  
		  susNoAuto.focus();
		  return false;	             
	}   	         
	}, 
	select: function( event, ui ) {
	var unit_name = ui.item.value;
	
	      $.post("getTargetSUSFromUNITNAME?"+key+"="+value,{target_unit_name:unit_name}, function(data) {
	      }).done(function(data) {
	      	var length = data.length-1;
	      	var enc = data[length].substring(0,16);
	      	$("#unit_sus_no").val(dec(enc,data[0]));
	      }).fail(function(xhr, textStatus, errorThrown) {
	      });
	} 	     
	}); 			
	}
	
	///////////////////////////awards and medal ENDS////////////////////////////
	
	
	
	

///////////////////////////battle and physical casuality////////////////////////////


function isNumber(evt) {
	
evt = (evt) ? evt : window.event;
var charCode = (evt.which) ? evt.which : evt.keyCode;
if (charCode > 31 && (charCode < 48 || charCode > 57)) {
return false;
}
return true;
}

function onBattleDesc(e){
var desc = e.value;
if(desc.toLowerCase() == "others"){	
$("#battle_desc_othersdiv").show();
}
else {
$("#battle_desc_othersdiv").hide();
}
}

function onCauseOfCasuality(e){
var c = e.value;
var a = $('input:radio[name=classification_of_casuality]:checked').val();
if(c.toLowerCase() == "others"){
if(a.toLowerCase() == "battle_casuality"){
$("#battle_descdiv").hide();
$("#battleothersdiv").show();
}
if(a.toLowerCase() == "physical_casuality"){	
$("#physical_descdiv").hide();
$("#physicalothersdiv").show();
}
}
else {
if(a.toLowerCase() == "battle_casuality"){
$("#battle_descdiv").show();
$("#battleothersdiv").hide();
$("#battle_desc_othersdiv").hide();
}
if(a.toLowerCase() == "physical_casuality"){
$("#physical_descdiv").show();
$("#physicalothersdiv").hide();
$("#physical_desc_othersdiv").hide();
}
}
}
function onphysicalDesc(e){
var phy = e.value;
if(phy.toLowerCase() == "others"){	
$("#physical_desc_othersdiv").show();
}
else {
$("#physical_desc_othersdiv").hide();
}
}

function Casuality_radio()
{
var a = $('input:radio[name=classification_of_casuality]:checked').val();
if(a.toLowerCase() == "battle_casuality"){	
$("#batnpc_battle_desc").show();
$("#batnpc_physical_desc").hide();
$("#battle_descdiv").show();
$("#battleothersdiv").hide();
$("select#battle_desc").val(0);
$("#battle_desc_othersdiv").hide();
}
if(a.toLowerCase() == "physical_casuality"){	
$("#batnpc_physical_desc").show();
$("#batnpc_battle_desc").hide();
$("#physical_descdiv").show();
$("#physicalothersdiv").hide();
$("select#physical_desc").val(0);
$("#physical_desc_othersdiv").hide();
}

var idd=a.toUpperCase();
$.post("getCauseOfCasualityList?"+key+"="+value, {idd:idd}).done(function(j) {debugger
                                    
                            
           var options = '<option   value="0">'+ "--Select--" + '</option>';
          for ( var i = 0; i < j.length; i++) {
          
                          options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
                  
          }
          
          
          $("select#cause_of_casuality").html(options);
         
  }).fail(function(xhr, textStatus, errorThrown) {
});
}


function AutoCompleteDiagnosis(ele){

var code = ele.value;
var susNoAuto =$("#"+ele.id);
susNoAuto.autocomplete({
source : function(request, response) {
$.ajax({
type : 'POST',
url : "getMNHDistinctICDList?" + key + "=" + value,
data : {
	a : code,b:"ALL"
},
success : function(data) {
	var susval = [];
	var length = data.length - 1;
	var enc = data[length].substring(0, 16);
	for (var i = 0; i < data.length; i++) {
		susval.push(dec(enc, data[i]));
	}

	response(susval);
}
});
},
minLength : 1,
autoFill : true,
change : function(event, ui) {
if (ui.item) {
return true;
} else {
alert("Please Enter Approved Diagnosis ");			
susNoAuto.val("");
susNoAuto.focus();
return false;
}
},
select : function(event, ui) {

}
});

}



//Control
function onCommandChanged(e) {
var cont_cname = jQuery(e).find('option:selected').attr("name");                       
jQuery("#cont_cname").val(cont_cname);
jQuery("#cont_aname").val("");
jQuery("#cont_bname").val("");
jQuery("#cont_dname").val("");

var fcode = e.value;
getCONTCorps1(fcode);
fcode += "00";        
getCONTDiv1(fcode);
fcode += "000";        
getCONTBde1(fcode);
}
function onCorpsChanged(e) {
var cont_aname = jQuery(e).find('option:selected').attr("name");                       
jQuery("#cont_aname").val(cont_aname);
jQuery("#cont_bname").val("");
jQuery("#cont_dname").val("");

var fcode = e.value;
getCONTDiv1(fcode);

fcode += "000";        
getCONTBde1(fcode);
}
function onDivChanged(e) {
var cont_dname = jQuery(e).find('option:selected').attr("name");                       
jQuery("#cont_dname").val(cont_dname);
jQuery("#cont_bname").val("");
var fcode = e.value;                       
getCONTBde1(fcode);
}




function getCONTCorps1(fcode){
var fcode1 = fcode[0];
$.post("getCorpsDetailsList?"+key+"="+value,{fcode:fcode1}, function(j) {
if(j != ""){
  var length = j.length-1;
  var enc = j[length][0].substring(0,16);
  var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
  for ( var i = 0; i < length; i++) {
          if(fcode == dec(enc,j[i][0])){
                  options += '<option value="' + dec(enc,j[i][0]) + '" name="'+dec(enc,j[i][1])+'" selected=selected>'+ dec(enc,j[i][1]) + '</option>';
                  jQuery("#cont_aname").val(dec(enc,j[i][1]));
          }else{
                  options += '<option value="' + dec(enc,j[i][0]) +'" name="'+dec(enc,j[i][1])+'">'+ dec(enc,j[i][1]) + '</option>';
          }
  }        
  jQuery("select#batnpc_corps_area").html(options);
}else{
  var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
  jQuery("select#batnpc_corps_area").html(options);
}
});
} 
function getCONTDiv1(fcode){
var fcode1 = fcode[0] + fcode[1] + fcode[2];
$.post("getDivDetailsList?"+key+"="+value,{fcode:fcode1}, function(j) {
if(j != ""){
     var length = j.length-1;
  var enc = j[length][0].substring(0,16);
  var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
  for ( var i = 0; i < length; i++) {
          if(fcode == dec(enc,j[i][0])){
                  options += '<option value="' + dec(enc,j[i][0]) + '" name="'+dec(enc,j[i][1])+'" selected=selected>'+ dec(enc,j[i][1]) + '</option>';
                  jQuery("#cont_dname").val(dec(enc,j[i][1]));
          }else{
                  options += '<option value="' + dec(enc,j[i][0]) +'" name="'+dec(enc,j[i][1])+'">'+ dec(enc,j[i][1]) + '</option>';
          }
  }        
  jQuery("select#batnpc_div_subarea").html(options);
}else{
  var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
  jQuery("select#batnpc_div_subarea").html(options);
}
});
} 
function getCONTBde1(fcode){
var fcode1 = fcode[0] + fcode[1] + fcode[2] + fcode[3] + fcode[4] + fcode[5];
$.post("getBdeDetailsList?"+key+"="+value,{fcode:fcode1}, function(j) {
if(j != ""){
  var length = j.length-1;
  var enc = j[length][0].substring(0,16);
  var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
  for ( var i = 0; i < length; i++) {
          if(fcode == dec(enc,j[i][0])){
                  options += '<option value="' + dec(enc,j[i][0]) + '" name="'+dec(enc,j[i][1])+'" selected=selected>'+ dec(enc,j[i][1]) + '</option>';
                  jQuery("#cont_bname").val(dec(enc,j[i][1]));
          }else{
                  options += '<option value="' + dec(enc,j[i][0]) +'" name="'+dec(enc,j[i][1])+'">'+ dec(enc,j[i][1]) + '</option>';
          }
  }        
  jQuery("select#batnpc_bde").html(options);
}else{
var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
jQuery("select#batnpc_bde").html(options);
}
});
}



function onCountryChange(e) {
	var contry_id = e.value;

	var text = $("#batnpc_country option:selected").text();
	if(text == "OTHERS"){
		$("#country_other_div").show();
	}
	else{
		$("#country_other_div").hide();
	}

	           $.post("getStateListFormcon1?"+key+"="+value, {contry_id:contry_id}).done(function(j) {
	                                            
	                                    
	                   var options = '<option   value="0">'+ "--Select--" + '</option>';
	                   $("select#batnpc_district").html(options);
	                   $("select#batnpc_tehsil").html(options);
	                  for ( var i = 0; i < j.length; i++) {
	                  
	                                  options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
	                          
	                  }
	                  
	                  $("select#batnpc_state").html(options);

	          }).fail(function(xhr, textStatus, errorThrown) {
	});
	}

	function onStateChange(e) {
	var state_id = e.value;

	var text = $("#batnpc_state option:selected").text();
	if(text == "OTHERS"){
		$("#state_other_div").show();
	}
	else{
		$("#state_other_div").hide();
	}

	   $.post("getDistrictListFormState1?"+key+"="+value, {state_id:state_id}).done(function(j) {
	                                    
	                            
	           var options = '<option   value="0">'+ "--Select--" + '</option>';
	           $("select#batnpc_tehsil").html(options);
	          for ( var i = 0; i < j.length; i++) {
	          
	                          options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
	                  
	          }
	          
	          $("select#batnpc_district").html(options);

	  }).fail(function(xhr, textStatus, errorThrown) {
	});
	}

	function onDistrictChange(e) {
	var Dist_id = e.value;

	var text = $("#batnpc_district option:selected").text();
	if(text == "OTHERS"){
		$("#district_other_div").show();
	}
	else{
		$("#district_other_div").hide();
	}

	$.post("getTehsilListFormDistrict1?"+key+"="+value, {Dist_id:Dist_id}).done(function(j) {
				 	
			 	
		var options = '<option   value="0">'+ "--Select--" + '</option>';
		for ( var i = 0; i < j.length; i++) {
		
				options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
			
		}
		
		$("select#batnpc_tehsil").html(options);

	}).fail(function(xhr, textStatus, errorThrown) {
	});
	}


	function onTehsilChange(e) {
	var teh_id = e.value;

	var text = $("#batnpc_tehsil option:selected").text();
	if(text == "OTHERS"){
		$("#tehsil_other_div").show();
	}
	else{
		$("#tehsil_other_div").hide();
	}

	     /*      $.post("getvillageListFormteh1?"+key+"="+value, {teh_id:teh_id}).done(function(j) {
	                                            
	                   
	                   var options = '<option   value="0">'+ "--Select--" + '</option>';
	                  for ( var i = 0; i < j.length; i++) {
	                  
	                                  options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
	                          
	                  }
	                  
	                  $("select#batnpc_village").html(options);

	          }).fail(function(xhr, textStatus, errorThrown) {
	});*/
	}





function getDescList(e){
var a = $('input:radio[name=classification_of_casuality]:checked').val();
if(a.toLowerCase() == "battle_casuality"){
var id = e.value;

$.post("getBattleDescList?"+key+"="+value,{id:id}, function(j) {
if(j != ""){
        
        var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
        for ( var i = 0; i < j.length; i++) {
                
                        options += '<option value="' + j[i][0] + '" name="'+j[i][1]+'" selected=selected>'+ j[i][1] + '</option>';
                
        }        
        $("select#battle_desc").html(options);
        $("select#battle_desc").val(0);
}else{
  var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
  $("select#battle_desc").html(options);
}
});
}


if(a.toLowerCase() == "physical_casuality"){
var c = e.value;
if(c.toLowerCase() == "disease"){
$("#physical_desc_othersdiv").hide();
$("#physicalothersdiv").hide();
var t="";
$.post("getMedDiseaseName?"+key+"="+value,{enc:t,a:t}, function(j) {
if(j != ""){
            
            var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
            for ( var i = 0; i < j.length; i++) {
                    
                            options += '<option value="' + j[i][1] + '" name="'+j[i][0]+'" selected=selected>'+ j[i][0] + '</option>';
                    
            }
            options += '<option value="OTHERS" name="Others" selected="selected">Others</option>'        
            $("select#physical_desc").html(options);
            $("select#physical_desc").val(0);
}else{
      var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
      $("select#physical_desc").html(options);
}
});
}

else if(c.toLowerCase() == "accidental"){
$("#physical_desc_othersdiv").hide();
$("#physicalothersdiv").hide();
$.post("getAccidentalList?"+key+"="+value, function(j) {
if(j != ""){
            
            var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
            for ( var i = 0; i < j.length; i++) {
                    
                            options += '<option value="' + j[i][0] + '" name="'+j[i][1]+'" selected=selected>'+ j[i][1] + '</option>';
                    
            }        
            $("select#physical_desc").html(options);
            $("select#physical_desc").val(0);
}else{
      var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
      $("select#physical_desc").html(options);
}
});
}

else if(c.toLowerCase() == "others"){
$("#physical_desc_othersdiv").hide();
$("#physicalothersdiv").show();
}
else {
$("#physical_desc_othersdiv").hide();
$("#physicalothersdiv").hide();
}
}




}


function getNameOfOperation(e){
var unit_name = e.value;
var susNoAuto=$("#"+e.id);
susNoAuto.autocomplete({
source: function( request, response ) {
$.ajax({
type: 'POST',
url: "getNameOfOperationList?"+key+"="+value,
data: {unit_name:unit_name},
success: function( data ) {
var susval = [];
for(var i = 0;i<data.length;i++){
susval.push(data[i][1]);
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
alert("Please Enter Approved operation Name.");
susNoAuto.val("");	        	  
susNoAuto.focus();
return false;	             
}   	         
}, 
select: function( event, ui ) {



} 	     
}); 			
}

///////////////////////////battle and physical casuality ends////////////////////////////

/*------------------Approval Batttle and Physical*/

function bindRadioBt(){
    $.post("getNatureOfCasualityList?"+key+"="+value,function(j) {
                            $("#nature_of_casualityDiv").empty();
                             for ( var i = 0; i < j.length ;i++) {
                                    $("#nature_of_casualityDiv").append ( '<span style="display: inline-block;"><input type="radio" style="margin-left: 3px;" id="nature_of_casuality'+(i+1)+'" name="nature_of_casuality" value="'+ j[i][0] +'"> <label for="'+ j[i][1] +'">'+ j[i][1] +'</label> </span>' );
                             } 
                     });
}


function getDescListApprove(e){
    var a = $("#classification_of_casuality_hid").val();
    
    if(a == "battle_casuality"){
    	
    var id = $("#cause_of_casuality_val").val();

    $.post("getBattleDescList?"+key+"="+value,{id:id}, function(j) {
    if(j != ""){
            var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
            for ( var i = 0; i < j.length; i++) {
                    
                            options += '<option value="' + j[i][0] + '" name="'+j[i][1]+'" selected=selected>'+ j[i][1] + '</option>';
                    
            }        
            $("select#battle_desc_val").html(options);
            $("select#battle_desc_val").val(0);
    }else{
      var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
      $("select#battle_desc_val").html(options);
    }
    });
    }
   
    if(a == "physical_casuality"){
            
            var c = $("#cause_of_casuality_val").val();
            
            if(c.toLowerCase() == "disease"){
            $("#physical_desc_othersdiv").hide();
            $("#physicalothersdiv").hide();
            var t="";
            $.post("getMedDiseaseName?"+key+"="+value,{enc:t,a:t}, function(j) {
            if(j != ""){
                        
                        var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
                        for ( var i = 0; i < j.length; i++) {
                                
                                        options += '<option value="' + j[i][1] + '" name="'+j[i][0]+'" selected=selected>'+ j[i][0] + '</option>';
                                
                        }
                        options += '<option value="OTHERS" name="Others" selected="selected">Others</option>'        
                        $("select#physical_desc_val").html(options);
                        $("select#physical_desc_val").val(0);
            }else{
                  var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
                  $("select#physical_desc_val").html(options);
            }
            });
            }

            else if(c.toLowerCase() == "accidental"){
            $("#physical_desc_othersdiv").hide();
            $("#physicalothersdiv").hide();
            $.post("getAccidentalList?"+key+"="+value, function(j) {
            if(j != ""){
                        
                        var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
                        for ( var i = 0; i < j.length; i++) {
                                
                                        options += '<option value="' + j[i][0] + '" name="'+j[i][1]+'" selected=selected>'+ j[i][1] + '</option>';
                                
                        }        
                        $("select#physical_desc_val").html(options);
                        $("select#physical_desc_val").val(0);
            }else{
                  var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
                  $("select#physical_desc_val").html(options);
            }
            });
            }

            else if(c.toLowerCase() == "others"){
            $("#physical_desc_othersdiv").hide();
            $("#physicalothersdiv").show();
            }
            else {
            $("#physical_desc_othersdiv").hide();
            $("#physicalothersdiv").hide();
            }
            }
}
function Casuality_radioApproval()
{
	var a = $("#classification_of_casuality_hid").val();
			if(a == "battle_casuality"){	
				$("#batnpc_battle_desc").show();
				$("#batnpc_physical_desc").hide();
				$("#battle_descdiv").show();
				$("#battleothersdiv").hide();
				$("select#battle_desc").val(0);
				$("#battle_desc_othersdiv").hide();
			}
			if(a == "physical_casuality"){	
				$("#batnpc_physical_desc").show();
				$("#batnpc_battle_desc").hide();
				$("#physical_descdiv").show();
				$("#physicalothersdiv").hide();
				$("select#physical_desc").val(0);
				$("#physical_desc_othersdiv").hide();
			}
			
			var idd=a.toUpperCase();
			$.post("getCauseOfCasualityList?"+key+"="+value, {idd:idd}).done(function(j) {
			           var options = '<option   value="0">'+ "--Select--" + '</option>';
			          for ( var i = 0; i < j.length; i++) {
			               options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
			          }
			          $("select#cause_of_casuality_val").html(options);
			         
			  }).fail(function(xhr, textStatus, errorThrown) {
			});
}

function onCauseOfCasualityApprove(e){
	 
    var a = $("#classification_of_casuality_hid").val();
  
    if(e == "OTHERS"){
            if(a == "battle_casuality"){
                    $("#battle_descdiv").hide();
                    $("#battleothersdiv").show();
            }
            if(a == "physical_casuality"){        
                    $("#physical_descdiv").hide();
                    $("#physicalothersdiv").show();
            }
    }
    else {
            if(a == "battle_casuality"){
                    $("#battle_descdiv").show();
                    $("#battleothersdiv").hide();
                    $("#battle_desc_othersdiv").hide();
            }
            if(a == "physical_casuality"){
                    $("#physical_descdiv").show();
                    $("#physicalothersdiv").hide();
                    $("#physical_desc_othersdiv").hide();
            }
    }
    
}

    

function onCountryChangeApprove(e) {
var contry_id = e.value;
       $.post("getStateListFormcon1?"+key+"="+value, {contry_id:contry_id}).done(function(j) {
               var options = '<option   value="0">'+ "--Select--" + '</option>';
              for ( var i = 0; i < j.length; i++) {
                              options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
              }
              $("select#batnpc_state_val").html(options);
      }).fail(function(xhr, textStatus, errorThrown) {
});
}

function onStateChangeApprove(e) {
var state_id = e.value;
$.post("getDistrictListFormState1?"+key+"="+value, {state_id:state_id}).done(function(j) {
       var options = '<option   value="0">'+ "--Select--" + '</option>';
      for ( var i = 0; i < j.length; i++) {
                      options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
      }
      $("select#batnpc_district_val").html(options);
}).fail(function(xhr, textStatus, errorThrown) {
});
}

function onDistrictChangeApprove(e) {
var Dist_id = e.value;
$.post("getTehsilListFormDistrict1?"+key+"="+value, {Dist_id:Dist_id}).done(function(j) {
    var options = '<option   value="0">'+ "--Select--" + '</option>';
    for ( var i = 0; i < j.length; i++) {
                    options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
    }
    $("select#batnpc_tehsil_val").html(options);
}).fail(function(xhr, textStatus, errorThrown) {
});
}

function onTehsilChangeApprove(e) {
var teh_id = e.value;
       $.post("getvillageListFormteh1?"+key+"="+value, {teh_id:teh_id}).done(function(j) {
               var options = '<option   value="0">'+ "--Select--" + '</option>';
              for ( var i = 0; i < j.length; i++) {
                              options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
                      
              }
              $("select#batnpc_village_val").html(options);
      }).fail(function(xhr, textStatus, errorThrown) {
});
}


function onphysicalDescApprove(e){

	var phy = e.value;
         if(phy == "others"){        
             $("#physical_desc_othersdiv").show();
         }
         else {
             $("#physical_desc_othersdiv").hide();
         }
	}

function onBattleDescApprove(e){
	var desc = e.value;
	    if(desc == "others"){        
	       $("#battle_desc_othersdiv").show();
	    }
	    else {
	       $("#battle_desc_othersdiv").hide();
	    }
}

function isAadhar(e,evt) {
	
	if(e.value==0 || e.value=="null" || e.value == null){
		e.value="";
		}

	var bool=isNumber(evt);	
	if(bool){
	  var value = e.value;
	  value = value.replace(/\D/g, "").split(/(?:([\d]{4}))/g).filter(s => s.length >0 ).join("-");
	  e.value=value;
	  return true;
	  }
	  else{
	  return false;}
	}
	
	
function isPAN(e) {    
	var inputvalues = e.value;      
	  var regex = /[A-Z]{5}[0-9]{4}[A-Z]{1}$/;    
	  if(!regex.test(inputvalues)){      
	  e.value="";   
	  alert("Invalid PAN Number");    
	   
	  }    
	}

	function isValidYear(e) {    
	var inputvalues = e.value;    
	var currentYear = new Date().getFullYear();  
	 if(input < 1900 == false && input > currentYear == false)
	{      
	  e.value="";   
	  alert("Invalid Year");    

	  }    
	}
	
	function onlyAlphabets(e, t) {
	    return (e.charCode > 64 && e.charCode < 91) || (e.charCode > 96 && e.charCode < 123) || e.charCode == 32;   
	}
	
	
	function onlyAlphaNumeric(e, t) {	
	    return (e.charCode > 64 && e.charCode < 91) || (e.charCode > 96 && e.charCode < 123) || (e.charCode >= 48 && e.charCode <= 57 ) || e.charCode == 32;   
	}
	
	
	function validateEmail(emailField){
	    var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;

	    if (reg.test(emailField.value) == false) 
	    {
	        alert('Invalid Email Address');
	       
	       // emailField.value="";
	        return false;
	    }

	    return true;
	}
	
	
	
	
    function specialcharecter(a)
    {
        var iChars = "@#^&*$,.:;%!+";   
        var data = a.value;
        for (var i = 0; i < data.length; i++)
        {      
            if (iChars.indexOf(data.charAt(i)) != -1)
            {    
            alert ("This " +data.charAt(i)+" special characters not allowed.");
            a.value = "";
            return false; 
            } 
        }
        return true;
    }
    
    function AvoidSpace(event) {
	    var k = event ? event.which : window.event.keyCode;
	    if (k == 32) return false;
	}
    
    
    
      function calculate_agediff(dob, marrage_date) {
    	var from_d = dob.value;
    	from_d = from_d.replaceAll("/", "-");
    	var from_d1 = from_d.substring(6, 10);
    	var from_d2 = from_d.substring(3, 5);
    	var from_d3 = from_d.substring(0, 2);
    	var frm_d = from_d3 + "-" + from_d2 + "-" + from_d1;
    	var today = marrage_date.value;
    	today = today.replaceAll("/", "-");
    	var to_d1 = today.substring(6, 10);
    	var to_d2 = today.substring(3, 5);
    	var to_d3 = today.substring(0, 2);
    	var to_d0 = to_d3 + "-" + to_d2 + "-" + to_d1;
    	if(to_d2 > from_d2 && to_d3 > from_d3 || to_d3 == from_d3) {
    		var year = to_d1 - from_d1
    		var month = to_d2 - from_d2
    	}
    	if(to_d2 > from_d2 && to_d3 < from_d3) {
    		var year = to_d1 - from_d1
    		var month = to_d2 - from_d2 - 1
    	}
    	if(from_d2 > to_d2) {
    		var year = to_d1 - from_d1 - 1
    		var month1 = from_d2 - to_d2
    		var month = 12 - month1
    	}
    	if(from_d2 == to_d2 && from_d3 > to_d3) {
    		var year = to_d1 - from_d1 - 1
    		var days = from_d3 - to_d3
    	}
    	if(from_d2 == to_d2 && to_d3 > from_d3) {
    		var year = to_d1 - from_d1
    		var days = to_d3 - from_d3
    	}
    	if(from_d2 == to_d2 && to_d3 == from_d3) {
    		var year = to_d1 - from_d1
    		var days = 0
    	}
    	//days
    	if(from_d2 > to_d2 && from_d3 > to_d3) {
    		var m = from_d2 - to_d2
    		var n = m * 30
    		var m1 = from_d3 - to_d3
    		var m2 = n + m1
    		var days = m2
    	}
    	if(from_d2 > to_d2 && to_d3 > from_d3) {
    		var m = from_d2 - to_d2
    		var n = m * 30
    		var m1 = to_d3 - from_d3
    		var m2 = n + m1
    		var days = m2
    	}
    	if(to_d2 > from_d2 && to_d3 > from_d3) {
    		var m = to_d2 - from_d2
    		var n = m * 30
    		var m1 = to_d3 - from_d3
    		var m2 = n + m1
    		var days = m2
    	}
    	if(to_d2 > from_d2 && from_d3 > to_d3) {
    		var m = to_d2 - from_d2
    		var n = m * 30
    		var m1 = from_d3 - to_d3
    		var m2 = n + m1
    		var days = m2
    	}
    	//    document.getElementById("age_of_joining").value=year+"-"+month+"-"+days; 
    	//document.getElementById('age_year1').value = year;
    	if(month == undefined) {
    		month = 0;
    	}
    	
    	return year;
    }
    
    
    
    function calculate_age(dob, marrage_date) {
    	var from_d = dob.value;
    	from_d = from_d.replaceAll("/", "-");
    	var from_d1 = from_d.substring(6, 10);
    	var from_d2 = from_d.substring(3, 5);
    	var from_d3 = from_d.substring(0, 2);
    	var frm_d = from_d3 + "-" + from_d2 + "-" + from_d1;
    	var today = marrage_date.value;
    	today = today.replaceAll("/", "-");
    	var to_d1 = today.substring(6, 10);
    	var to_d2 = today.substring(3, 5);
    	var to_d3 = today.substring(0, 2);
    	var to_d0 = to_d3 + "-" + to_d2 + "-" + to_d1;
    	if(to_d2 > from_d2 && to_d3 > from_d3 || to_d3 == from_d3) {
    		var year = to_d1 - from_d1
    		var month = to_d2 - from_d2
    	}
    	if(to_d2 > from_d2 && to_d3 < from_d3) {
    		var year = to_d1 - from_d1
    		var month = to_d2 - from_d2 - 1
    	}
    	if(from_d2 > to_d2) {
    		var year = to_d1 - from_d1 - 1
    		var month1 = from_d2 - to_d2
    		var month = 12 - month1
    	}
    	if(from_d2 == to_d2 && from_d3 > to_d3) {
    		var year = to_d1 - from_d1 - 1
    		var days = from_d3 - to_d3
    	}
    	if(from_d2 == to_d2 && to_d3 > from_d3) {
    		var year = to_d1 - from_d1
    		var days = to_d3 - from_d3
    	}
    	if(from_d2 == to_d2 && to_d3 == from_d3) {
    		var year = to_d1 - from_d1
    		var days = 0
    	}
    	//days
    	if(from_d2 > to_d2 && from_d3 > to_d3) {
    		var m = from_d2 - to_d2
    		var n = m * 30
    		var m1 = from_d3 - to_d3
    		var m2 = n + m1
    		var days = m2
    	}
    	if(from_d2 > to_d2 && to_d3 > from_d3) {
    		var m = from_d2 - to_d2
    		var n = m * 30
    		var m1 = to_d3 - from_d3
    		var m2 = n + m1
    		var days = m2
    	}
    	if(to_d2 > from_d2 && to_d3 > from_d3) {
    		var m = to_d2 - from_d2
    		var n = m * 30
    		var m1 = to_d3 - from_d3
    		var m2 = n + m1
    		var days = m2
    	}
    	if(to_d2 > from_d2 && from_d3 > to_d3) {
    		var m = to_d2 - from_d2
    		var n = m * 30
    		var m1 = from_d3 - to_d3
    		var m2 = n + m1
    		var days = m2
    	}
    	//    document.getElementById("age_of_joining").value=year+"-"+month+"-"+days; 
    	//document.getElementById('age_year1').value = year;
    	if(month == undefined) {
    		month = 0;
    	}
    	if(year < 18) {
    		alert("Age Should be above 18");
    		//    	 $("#"+marrage_date.id).val("");
    		$("#" + marrage_date.id).focus();
    		return false;
    	}
    	return true;
    }
    function fn_getUnitnamefromSusHis(sus_no){
    	var un="";
    	$.ajaxSetup({
    		async : false
    	});
    	$.post("getTargetUnitNameList?"+key+"="+value, {sus_no:sus_no}).done(function(data) {
    		var l=data.length-1;
    		  var enc = data[l].substring(0,16);    	   	 
    	 		un=dec(enc,data[0]);
    		}).fail(function(xhr, textStatus, errorThrown) {
    });
    	return un;
    }
    function bindRadioButton(){
        $.post("getclassification_of_casualitylist?"+key+"="+value,function(j) {
                                $("#classification_of_casualityDIV").empty();
                                 for ( var i = 0; i < j.length ;i++) {
                                        $("#classification_of_casualityDIV").append ( '<span style="display: inline-block;"><input type="radio" id="classification_of_casuality'+(i+1)+'" name="classification_of_casuality" value="'+ j[i][0] +'" onchange="Casuality_radio();"> <label for="'+ j[i][1] +'">'+ j[i][1] +'</label></span> ' );
                                 } 
                         });
    }
    
    function FnTehsilDisableEnable(obj,input_id,other_div,other_id){
 	   
	     var country = $("#"+obj.id+" option:selected").text();
	     if(country.toUpperCase()=="BHUTAN"){
	      $("#"+input_id).val("0");
	     $("#"+input_id).prop("disabled",true);
	      $("#"+other_div).hide()
	       $("#"+other_id).val('');
	     }
	     else{
	      $("#"+input_id).prop("disabled",false);
	     }
    }
		
    var auth_length=100; //authority Length Intialization
    function lengthValidation(value,Ilength){   
    	if(value.length>Ilength)
    		return true;
    	else
    		return false;
    }
    
    function addMaxLengthToInput(len){
	    $('input').each(function() {
		 var mx = $(this).attr('maxlength');
		 if(mx == null || mx == ""){
		 	$(this).attr('maxlength',len);
		    }
				 });		
    }
    
//    function skipTabForReadOnly(){
//    	$('input').each(function() {
//    	    var readonly = $(this).attr("readonly");				
//    	    if(readonly && readonly.toLowerCase()!=='false') { 
//    	       $(this).prop("tabindex",'-1');
//    	    }
//    	});
//    	}
    
    function setInvalidToNull(val){
        if(val==0 || val=="0" || val==null || val=="null"){
        	val="";
        }
        return val;
       }
    
    function chgarm(e,reg_id){

    	var options = '<option   value="0">' + "--Select--" + '</option>';
    	var arm_code = e.value;
    	if(arm_code == "0800" || arm_code == "0700"){
    		$("#"+reg_id).attr('disabled',false);			
    		$.post("getRegimentFromArmCode?" + key + "=" + value, {arm_code : arm_code}).done(function(j) {		
    	    		for (var i = 0; i < j.length; i++) {
    	    			options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
    	    						+ j[i][1] + '</option>';
    	    		}
    	    		$("#"+reg_id).html(options);
    	    }).fail(function(xhr, textStatus, errorThrown) {
    	    });
    		}
    		else{
    			 $("#"+reg_id).attr('disabled',true);
    			  $("#"+reg_id).val("0");
    			  
    		}
    		
    	$("#"+reg_id).html(options);
      }
    
    
    function inputToUpperCase(obj){
        obj.value = obj.value.toUpperCase()
        }
    
    function addRemarkModel(method,id)
    {     
    	  console.log("Val of id " + id);
	      if( $('#btnTrigger').length ){}
	      else{
				var btn = document.createElement("button"); 
				btn.id="btnTrigger";
				document.body.appendChild(btn);
				$( "#btnTrigger" ).attr("data-toggle", "modal");
				$( "#btnTrigger" ).attr("data-target", "#remarkModel");
				$( "#btnTrigger" ).hide();
			  }
	
		  if( $('#remarkModel').length )        
			 {
			    $("#remarkModel").remove();
			 }	
			 	    	
	       	var div = document.createElement("div");   
	        div.id="remarkModel";
	  		document.body.appendChild(div);
	  		$( "#remarkModel" ).addClass( "modal fade" );
	  		$( "#remarkModel" ).attr("role", "dialog");
	  		$( "#remarkModel" ).attr("data-backdrop", "static");
	  		
	           div.innerHTML=''
	   						 +'<div class="modal-dialog" style="width: 500px !important;">'
	     					 +'  <div class="modal-content" style="width: 500px !important;">'
	      					 +'  <div class="modal-header" >'
	    				     +'  <h4 class="modal-title" align="left">Reject Remarks</h4>'
	     				     +'    <button type="button" class="close" data-dismiss="modal">&times;</button> '     
	      					 +'   </div>'
	                         +'  <div class="modal-body">'
	                         +' <textarea id="reject_remarks"'
							 +'name="reject_remarks" class="form-control autocomplete" '
							 +'autocomplete="off" maxlength="1000"></textarea>'
	                         +'   </div>'
	                         +'   <div class="modal-footer" align="center">'
	                         +'      <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>'
	                         +'      <input type="button" id="remarkSubmit" data-dismiss="modal" value="Submit" onclick="'+method+'('+id+')"class="btn btn-success" >'
	                         +'   </div>'
	                         +' </div>'    
	                         +'  </div>';
	        
	         $('#btnTrigger').click(); 	         
	         if( $('.modal-backdrop').length ){
	        	  $(".modal-backdrop").remove();    
	         }
         
    }  
    
    function getPersonnelNo(comm_id,Inpersonnel_no_id,Incensus_id,Inevent_id){
		$.ajaxSetup({
					async : false
				});
	var flag=false;
	$.post("getPersonelNoFromComm?"+key+"="+value, {comm_id:comm_id}).done(function(data) {
		if(data.error == null) {
		
			if( $('#'+Inpersonnel_no_id).length ) {	
				$('#'+Inpersonnel_no_id).val(data.personnel_no);
			}
			if( $('#'+Incensus_id).length ) {	
				$('#'+Incensus_id).val(data.census_id);	
			}
			if( $('#'+Inevent_id).length ) {
				$('#'+Inevent_id).val(data.marital_status);	
			}
			flag=true;
		} else {
		
			
		}
		
		}).fail(function(xhr, textStatus, errorThrown) {
});
	
	return flag;
}
    
    function getArmyNoFromJco(jco_id,InArmy_no,Inevent_id){
		$.ajaxSetup({
					async : false
				});
	var flag=false;
	$.post("getArmyNoFromJco?"+key+"="+value, {jco_id:jco_id}).done(function(data) {
		if(data.error == null) {
		
		  if( $('#'+InArmy_no).length ) {
			$('#'+InArmy_no).val(data.army_no);
		}	
		if( $('#'+Inevent_id).length ) {
			$('#'+Inevent_id).val(data.marital_status);	
		}
			flag=true;
		} else {
		
			
		}
		
		}).fail(function(xhr, textStatus, errorThrown) {
});
	
	return flag;
}


 //260194
    function calculate_age_marr(dob, marrage_date) {
    
    	var from_d = dob.value;
    	from_d = from_d.replaceAll("/", "-");
    	var from_d1 = from_d.substring(6, 10);
    	var from_d2 = from_d.substring(3, 5);
    	var from_d3 = from_d.substring(0, 2);
    	var frm_d = from_d3 + "-" + from_d2 + "-" + from_d1;
    	var today = marrage_date.value;
    	today = today.replaceAll("/", "-");
    	var to_d1 = today.substring(6, 10);
    	var to_d2 = today.substring(3, 5);
    	var to_d3 = today.substring(0, 2);
    	var to_d0 = to_d3 + "-" + to_d2 + "-" + to_d1;
    	if(to_d2 > from_d2 && to_d3 > from_d3 || to_d3 == from_d3) {
    		var year = to_d1 - from_d1
    		var month = to_d2 - from_d2
    	}
    	if(to_d2 > from_d2 && to_d3 < from_d3) {
    		var year = to_d1 - from_d1
    		var month = to_d2 - from_d2 - 1
    	}
    	if(from_d2 > to_d2) {
    		var year = to_d1 - from_d1 - 1
    		var month1 = from_d2 - to_d2
    		var month = 12 - month1
    	}
    	if(from_d2 == to_d2 && from_d3 > to_d3) {
    		var year = to_d1 - from_d1 - 1
    		var days = from_d3 - to_d3
    	}
    	if(from_d2 == to_d2 && to_d3 > from_d3) {
    		var year = to_d1 - from_d1
    		var days = to_d3 - from_d3
    	}
    	if(from_d2 == to_d2 && to_d3 == from_d3) {
    		var year = to_d1 - from_d1
    		var days = 0
    	}
    	//days
    	if(from_d2 > to_d2 && from_d3 > to_d3) {
    		var m = from_d2 - to_d2
    		var n = m * 30
    		var m1 = from_d3 - to_d3
    		var m2 = n + m1
    		var days = m2
    	}
    	if(from_d2 > to_d2 && to_d3 > from_d3) {
    		var m = from_d2 - to_d2
    		var n = m * 30
    		var m1 = to_d3 - from_d3
    		var m2 = n + m1
    		var days = m2
    	}
    	if(to_d2 > from_d2 && to_d3 > from_d3) {
    		var m = to_d2 - from_d2
    		var n = m * 30
    		var m1 = to_d3 - from_d3
    		var m2 = n + m1
    		var days = m2
    	}
    	if(to_d2 > from_d2 && from_d3 > to_d3) {
    		var m = to_d2 - from_d2
    		var n = m * 30
    		var m1 = from_d3 - to_d3
    		var m2 = n + m1
    		var days = m2
    	}
    	//    document.getElementById("age_of_joining").value=year+"-"+month+"-"+days; 
    	//document.getElementById('age_year1').value = year;
    	if(month == undefined) {
    		month = 0;
    	}
    	if(year < 18) {
    		alert("Age Should be above 18");
    		//    	 $("#"+marrage_date.id).val("");
    		$("#" + marrage_date.id).focus();
    		return false;
    	}
    	return true;
    }
    function getEmployeeNo(civ_id,Inpersonnel_no_id,Inevent_id){
		$.ajaxSetup({
					async : false
				});
	var flag=false;
	$.post("getEmpNoFromCiv?"+key+"="+value, {civ_id:civ_id}).done(function(data) {
		if(data.error == null) {
		
			if( $('#'+Inpersonnel_no_id).length ) {	
				$('#'+Inpersonnel_no_id).val(data.employee_no);
			}
			
			if( $('#'+Inevent_id).length ) {
				$('#'+Inevent_id).val(data.marital_status);	
			}
			flag=true;
		} else {
		
			
		}
		
		}).fail(function(xhr, textStatus, errorThrown) {
});
	
	return flag;
}
    
 /* //Inactive and active unit name for medal screen
	function activeAndInactiveUnitData(e){	
	var unit_name = e.value;
	var susNoAuto=$("#"+e.id);
	susNoAuto.autocomplete({
	source: function( request, response ) {
	$.ajax({
		type: 'POST',
		url: "UnitsNameList_active_or_inactive?"+key+"="+value,
	data: {unit_name:unit_name},
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
		  alert("Please Enter Approved Unit Name.");
		  susNoAuto.val("");	        	  
		  susNoAuto.focus();
		  return false;	             
	}   	         
	}, 
	select: function( event, ui ) {
	var unit_name = ui.item.value;
	
	      $.post("getTargetSUSFromUNITNAME?"+key+"="+value,{target_unit_name:unit_name}, function(data) {
	      }).done(function(data) {
	      	var length = data.length-1;
	      	var enc = data[length].substring(0,16);
	      	$("#unit_sus_no").val(dec(enc,data[0]));
	      }).fail(function(xhr, textStatus, errorThrown) {
	      });
	} 	     
	}); 			
	}*/
    
  //Inactive and active unit name for medal screen
    function activeAndInactiveUnitData(e) {
      var unit_name = e.value;
      var susNoAuto = $("#" + e.id);

      susNoAuto.autocomplete({
        source: function(request, response) {
          $.ajax({
            type: 'POST',
            url: "UnitsNameList_active_or_inactive?" + key + "=" + value,
            data: { unit_name: unit_name },
            success: function(data) {
              var susval = [];
              var length = data.length - 1;
              var enc = data[length].substring(0, 16);

              for (var i = 0; i < data.length; i++) {
                susval.push(dec(enc, data[i])); 
              }
              response(susval);
            }
          });
        },
        minLength: 1,
        autoFill: true,
        change: function(event, ui) {
          if (!ui.item) {
            alert("Please Enter Approved Unit Name.");
            susNoAuto.val("");
            susNoAuto.focus();
            return false;
          }
          return true;
        },
        select: function(event, ui) {
           
        }
      });
    }

	
	///////////////////////////awards and medal ENDS////////////////////////////

	