<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Upload WE/PE</title>
</head>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 

<script type="text/javascript" src="js/miso/miso_js/jquery-1.12.3.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/cue/update.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>
<script src="js/cue/printAllPages.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<script>
var roleid="${roleid}";
var wepe = '${wepe}';
</script>

<script type="text/javascript">
$(document).ready(function () {
	 setvalue();
	 /* if("${roleAccess}" === "Line_dte"  && "${roleSubAccess}" === 'Arm' ){
		 $('select#arm').val("${roleArmCode}");
		 document.getElementById("arm").disabled = true;   
	 } */
});

function setvalue(){
	document.getElementById("getTypeOnclick").value = wepe;
	var type = "" ;
	type = document.getElementById("getTypeOnclick").value;		
	if(type != null){		
		document.getElementById("setTypeOnclick").value=type;
		if(document.getElementById("getTypeOnclick").value == "1") //for PERSONNEL value is 1
		{
			$("div#divPersTraUnitId").show();
			document.getElementById("getroleid").value=roleid;
		}
		else 
		{
			$("div#divPersTraUnitId").hide();
			document.getElementById("getroleid").value=roleid;
		}
	}
}

</script>


<script>
function printDiv() 
  	{
	 	var printLbl = ["Type :"];
	 	var printVal = [$('input:radio[name=we_pe]:checked').val()];
	 	printDivOptimize('divPrint','CAPTURE WE/PE TITLE',printLbl,printVal,"select#status");
 	 }
</script>


<script>
$(function () {    
    $("#eff_frm_date").datepicker({
		//maxDate: new Date(),
        dateFormat: "dd-mm-yy",    //"yy-mm-dd",
        changeMonth: true,
        changeYear: true
	}).attr('readonly', 'readonly');
    
    $("#eff_to_date").datepicker({
		//maxDate: new Date(),
        dateFormat: "dd-mm-yy",    //"yy-mm-dd",
        changeMonth: true,
        changeYear: true
	}).attr('readonly', 'readonly');
    
});

$(document).ready(function() {

	
	if('${we_pe01}' != "")
	{
		$("#sponsor_dire").val('${sponsor_dire1}');
		$("#arm").val('${arm_desc1}');
		$("input[name=we_pe][value="+'${we_pe01}'+"]").prop('checked', true);
		$("#we_pe_no").val('${we_pe_no1}');
		$("#setTypeOnclick").val('${wepe}');
		$("#divPrint").show();
		$("div#divwatermark").val('').addClass('watermarked'); 
		watermarkreport();
		$("div#divSerachInput").show(); 
		document.getElementById("printId").disabled = false;
		getWePeNoByType('${we_pe01}');
		
		if('${list.size()}' == 0 ){
			 $("div#divSerachInput").hide();  
			 document.getElementById("printId").disabled = true;
			$("table#AttributeReportSearchWePe").append("<tr><td colspan='11' style='text-align :center;'>Data Not Available</td></tr>");
		 }
	}
		
		
	$("#searchInput").on("keyup", function() {
			var value = $(this).val().toLowerCase();
			$("#AttributeReportSearchWePe tbody tr").filter(function() { 
			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
			});
		});
	
	   $('#table_title').keyup(function() {
     this.value = this.value.toUpperCase();
 });
	   
	   $('#we_pe_no').keyup(function() {
	        this.value = this.value.toUpperCase();
	    });
	   
	   $('#remarks').keyup(function() {
	        this.value = this.value.toUpperCase();
	    });
		    
	$('input[type=radio][name=we_pe]').change(function() {		
		var radio_doc = $(this).val();	
		$("#uploaded_wepe").val("");
		getWePeNoByType(radio_doc);
	});
	
	var r =  $('input[type=radio][name=we_pe]:checked').val();	
	if(r != undefined){
		getWePeNoByType(r);
		$("#uploaded_wepe").val("");
	}

	try{
		if(window.location.href.includes("wepetypesearch="))
		{
			var url = window.location.href.split("?wepetypesearch=")[1].split("&")[0];
			var m = window.location.href.split("?wepetypesearch=")[1].split("&msg=")[1];
			if(m.includes("Updated+Successfully")){
				window.opener.getRefreshReport('wepecond_per',m);
				//window.close('','_parent','');				
			}
		}
		 if(window.location.href.includes("msg="))
		{
			var url = window.location.href.split("?msg")[0];
			window.location = url;
		}
		 if(window.location.href.includes("wepetype="))
		{
			var url = window.location.href.split("?wepetype")[0];
			window.location = url;
		} 
	}
	catch (e) {
		// TODO: handle exception
	}
 
 });
 
function getWePeNoByType(radio_doc)
{
	
	 if(radio_doc != ""){
		/* $("#suprcdd_we_pe_no").val(""); changed paresh 14-09-2020 delhi */
		 document.getElementById("table_title").value="";
		  document.getElementById("eff_frm_date").value="";
		  document.getElementById("eff_to_date").value="";
		  $("select#doc_type").val("Restricted");
// 		  $("select#sponsor_dire").val("0");
		  
		  var wepetext11=$("#we_pe_no");
		  wepetext11.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
	        	type: 'POST',
		        url: "getWePeCondiByNoInWEPECon?"+key+"="+value,
		        data: {type : radio_doc, newRoleid :wepe,we_pe_no:document.getElementById('we_pe_no').value},
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
		            var autoTextVal=wepetext11.val();
		            $.each(dataCountry1.toString().split("|"), function(i,e){
						var newE = e.substring(0, autoTextVal.length);
						//if (newE.toLowerCase() === autoTextVal.toLowerCase()) {							
						if (e.toLowerCase().includes(autoTextVal.toLowerCase())) {
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

		    });	 
		  $.ajaxSetup({
			    async: false
			});
	
	 var wepetext=$("#uploaded_wepe");

	  wepetext.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
        	type: 'POST',
            url: "getWePenumber?"+key+"="+value,
	        data: {type : radio_doc,we_pe_no : document.getElementById('uploaded_wepe').value},
	          success: function( data ) {
	            //response( data );
	        	  var susval = [];
	        	  var length = data.length-1;
	        		 var enc = data[length].columnName1.substring(0,16);
                   for(var i = 0;i<data.length-1;i++){
                    susval.push(dec(enc,data[i].columnName));
	        	  	}
		        	var dataCountry1 = susval.join("|");
	            var myResponse = []; 
	            var autoTextVal=wepetext.val();
				$.each(dataCountry1.toString().split("|"), function(i,e){
					var newE = e.substring(0, autoTextVal.length);
					if (newE.toLowerCase() === autoTextVal.toLowerCase()) {
					//if (e.toLowerCase().includes(autoTextVal.toLowerCase())) {
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
	        	alert("Please Enter Approved WE/PE No");
	        	wepetext.val("");	
	        	wepetext.focus();
	        	return false;	             
	          }   	         
	      } , 
	        select: function( event, ui ) {
	        	$(this).val(ui.item.value);    	        	
	        	getDetailsByUploadedwepeNo($(this).val());	        	
	        }  
           
	    });
	  
	  
	  $.ajaxSetup({
		    async: false
		});
	  
	  var wepetext1=$("#suprcdd_we_pe_no");
	  wepetext1.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
        	type: 'POST',
	        url: "getWePeCondiByNoInWEPECon?"+key+"="+value,
	          data: {type : radio_doc,newRoleid:wepe,we_pe_no:document.getElementById('suprcdd_we_pe_no').value},
	          success: function( data ) {
	            //response( data );
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
	        });
	      },
	      minLength: 1,
	      autoFill: true,
	      change: function(event, ui) {
	    	 if (ui.item) {   	        	  
	        	  return true;    	            
	          } else {
	        	  alert("Please Enter Approved Superseded Document No");
	        	  wepetext1.val("");	        	  
	        	  wepetext1.focus();
	        	  
	        	  document.getElementById("table_title").value="";
	    		  document.getElementById("eff_frm_date").value="";
	    		  document.getElementById("eff_to_date").value="";
	    		  $("select#doc_type").val("Restricted");
	    		  $("select#sponsor_dire").val("0");
	        	  return false;	             
	          }   	         
	      }, 
	        select: function( event, ui ) {
	        	$(this).val(ui.item.value);    	        	
	        	getDetailsBySupeerseedNo($(this).val());	        	
	        } 	     
	    });
	 }
	 else
		 alert("Please select Document");
	
}


function isNumber0_9Only(evt){
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if( ! ( charCode >= 48 && charCode <= 57 ) && charCode != 8 ){
		 return false;
	}
    return true;
}
	  
function getDetailsBySupeerseedNo(val)
{
	  if(val != "" && val != null)
	  {
		  
		       	 $.post("getDetailsByWePeCondiNo?"+key+"="+value, {val : val}).done(function(j) {
		       		if(j!="" && j!= null){	 
		   			 document.getElementById("table_title").value=j[0].table_title;
		   			document.getElementById("eff_frm_date").value=j[0].eff_frm_date;
		   			document.getElementById("eff_to_date").value=j[0].eff_to_date;
		   			$("select#doc_type").val(j[0].doc_type);
		   			$("select#sponsor_dire").val(j[0].sponsor_dire);
		   			$("select#arm").val(j[0].arm); 
		   		}
		   		else
		   		{
		   			 document.getElementById("table_title").value="";
		   			  document.getElementById("eff_frm_date").value="";
		   			  document.getElementById("eff_to_date").value="";
		   			  $("select#doc_type").val("Restricted");
		   			  $("select#sponsor_dire").val("0");
		   		}
		       	}).fail(function(xhr, textStatus, errorThrown) { });
	  }
	  else
	  {
		  alert("Please enter Approved Superseded Document No");
		  document.getElementById("table_title").value="";
		  document.getElementById("eff_frm_date").value="";
		  document.getElementById("eff_to_date").value="";
		  $("select#doc_type").val("Restricted");
		  $("select#sponsor_dire").val("0");
	  }
}


function getDetailsByUploadedwepeNo(val)
{
	  if(val != "" && val != null)
	  {
		 
			 $.post("getDetailsByWePeCondiUploadNo?"+key+"="+value,{val : val}).done(function(j) {
			
				 if(j!="" && j!= null){	 
						document.getElementById("table_title").value=j[0].table_title;
						document.getElementById("eff_frm_date").value=j[0].eff_frm_date;
						document.getElementById("eff_to_date").value=j[0].eff_to_date;
						$("select#doc_type").val(j[0].doc_type);
						$("select#sponsor_dire").val(j[0].sponsor_dire);
						$("select#arm").val(j[0].arm); 
					}
					else
					{
						 document.getElementById("table_title").value="";
						  document.getElementById("eff_frm_date").value="";
						  document.getElementById("eff_to_date").value="";
						  $("select#doc_type").val("Restricted");
						  $("select#sponsor_dire").val("0");
					}
			}).fail(function(xhr, textStatus, errorThrown) { });
	  }
	  else
	  {
		  alert("Please enter Approved Uploaded We/Pe No");
		  document.getElementById("table_title").value="";
		  document.getElementById("eff_frm_date").value="";
		  document.getElementById("eff_to_date").value="";
		  $("select#doc_type").val("Restricted");
		  $("select#sponsor_dire").val("0");
	  }
}
	 
	  
function clearall()
 {	document.getElementById('divPrint').style.display='none';
	 document.getElementById("printId").disabled = true;
	 $("div#background").hide();
 	var tab = $("#AttributeReportSearchWePe");
 	tab.empty();
 	$("#searchInput").val("");
 	$("#searchInput").hide();
 	//document.location.reload();
 	localStorage.clear();
	localStorage.Abandon();	
 }

function checkDate()
{
	  var startDate = document.getElementById("eff_frm_date").value;
	  var endDate = document.getElementById("eff_to_date").value;

	  var b = startDate.split(/\D/);
	  var c = endDate.split(/\D/);	  
	  
	  if ((Date.parse(c.reverse().join('-')) <= Date.parse(b.reverse().join('-'))))
	  {
	    alert("End date should be greater than Start date");
	    document.getElementById("eff_to_date").value = "";
	  }	
}
</script>
<script>
 function isvalidData(){		
	 
	 var r =  $('input:radio[name=we_pe]:checked').val();	
		  if(r == undefined)
		 {		 
			 alert("Please Select WE/PE");
				return false;
		 }
		  if($("input#we_pe_no").val()==""){
				alert("Please Enter WE/PE No");
				$("input#we_pe_no").focus();
				return false;
			} 
		
		  
		  if($("input#uploaded_wepe").val()==""){
				alert("Please Enter Uploaded WE/PE No");
				$("input#uploaded_wepe").focus();
				return false;
			}
		  
		  var r =  $('input:radio[name=answer]:checked').val();	
		  if(r == undefined)
		 {		 
			 alert("Please Select Yes/No");
				return false;
		 }
			var r = $('input:radio[name=answer]:checked').val();		
			if (r == "No") 
			{		
				if ($("input#suprcdd_we_pe_no").val() == ""){
				alert("Please Select Superseded WE/PE No");
				$("input#suprcdd_we_pe_no").focus();
				return false;
				}
			}
		 
			 if($("input#table_title").val()==""){
					alert("Please Enter WE/PE title");
					$("input#table_title").focus();
					return false;
				}
		  if($("input#eff_frm_date").val()==""){
				alert("Please Enter Effective Date");
				$("input#eff_frm_date").focus();
				return false;
			}
		  if($("input#eff_to_date").val()==""){
				alert("Please Enter Expiry Date");
				$("input#eff_to_date").focus();
				return false;
			}
		  var startDate = document.getElementById("eff_frm_date").value;
		  var endDate = document.getElementById("eff_to_date").value;

		  var b = startDate.split(/\D/);
		  var c = endDate.split(/\D/);	  
		  
		  if ((Date.parse(c.reverse().join('-')) <= Date.parse(b.reverse().join('-'))))
		  {
		    alert("End Date should be greater than Start Date");
		    document.getElementById("eff_to_date").value = "";
		  }				
		
		if($("select#doc_type").val()=="" )
		{
			alert("Please Select Document Type");
			$("select#doc_type").focus();
			return false;
		} 		
		if($("select#arm").val()=="--Select--" ||  $("select#arm").val()=="0" ||  $("select#arm").val()=="")
		{
			alert("Please Select Arm");
			$("select#arm").focus();
			return false;
		} 		
		if($("select#sponsor_dire").val()=="--Select--" ||  $("select#sponsor_dire").val()=="0" ||  $("select#sponsor_dire").val()=="")
		{
			alert("Please Select Sponsor Directorate");
			$("select#sponsor_dire").focus();
			return false;
		} 	
		
		if(document.getElementById("getTypeOnclick").value == "1"){ //for PERSONNEL value is 1
		 if($("input#training_capacity").val()=="" ){
			alert("Please Enter Training Capacity");
			$("input#training_capacity").focus();
			return false;
		}  		
		if($("select#unit_category").val()=="" ){
			alert("Please Select Unit Category");
			$("select#unit_category").focus();
			return false;
		}  
		
		}
		
		 return true;		
	} 
 </script>
<script>
function newdocument() {
	var r = $('input:radio[name=answer]:checked').val();
	if(r == 'No')
	{
		$("div#suprcdd_we_pe_no_div").show();
		
	}
	else
	{
		$("div#suprcdd_we_pe_no_div").hide(); 
		 $("input#suprcdd_we_pe_no").val("");
	}
}
</script>
<body >

<input type="hidden" name="getTypeOnclick" id="getTypeOnclick"  value="${wepe}"/> 	
	
<form:form name="Miso_cue_we_pe_fe_superForm" id="Miso_cue_we_pe_fe_superForm" action="miso_cue_we_pe_fe_superAction" method='POST' commandName="miso_cue_we_pe_fe_superCMD">

	    	<div class="container" align="center">
	        	<div class="card">
	        	<div class="card-header"><h5><b>CAPTURE WE/PE TITLE</b><br><span style="font-size:12px;color:red">(To be entered by Line Dte)</span></h5></div>
	          			<div class="card-body card-block cue_text">
	            			
	            			<div class="col-md-12">	            					
	              				<div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-6">
	                					<input type="hidden" name="getroleid" id="getroleid" value="${roleid}"/>
						                	<label for="text-input" class=" form-control-label">Type of Document <strong style="color: red;">*</strong></label>
						                </div>
							              
							                <div class="col-12 col-md-6">
				                              <div class="form-check-inline form-check">
				                                <label for="inline-radio1" class="form-check-label ">
				                                  <input type="radio" id="inline-radio1" name="we_pe" value="WE" maxlength="4" class="form-check-input">WE
				                                </label>&nbsp;&nbsp;&nbsp;
				                                <label for="inline-radio2" class="form-check-label ">
				                                  <input type="radio" id="inline-radio2" name="we_pe" value="PE" maxlength="4" class="form-check-input">PE
				                                </label>&nbsp;&nbsp;&nbsp;
				                                <label for="inline-radio3" class="form-check-label ">
				                                  <input type="radio" id="inline-radio3" name="we_pe" value="FE" maxlength="4" class="form-check-input">FE
				                                </label>&nbsp;&nbsp;&nbsp;
				                                <label for="inline-radio4" class="form-check-label ">
				                                  <input type="radio" id="inline-radio4" name="we_pe" value="GSL" maxlength="4" class="form-check-input">GSL
				                                </label>&nbsp;&nbsp;&nbsp;
				                              </div>
				                            </div>					              
	                				</div>
	                			</div>
	                			
	  							<div class="row form-group row_content">
					                <div class="col col-md-6">
					                  <label class=" form-control-label" id="lbluc">Approved Uploaded WE/PE No <strong style="color: red;">*</strong></label>
					                </div>
					                <div class="col-12 col-md-6">              
					                <input  class="form-control" id ="uploaded_wepe" name ="uploaded_wepe" maxlength="100"  autocomplete="off" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search">
					                
					                </div>
					            </div>	  								
	            			</div>	            			
	            			<div class="col-md-12" style="display: block;">				
	  						
	  						<div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-6">
	                  						<label class=" form-control-label">MISO WE/PE No <strong style="color: red;">*</strong></label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                  						<input  id="we_pe_no" name="we_pe_no" maxlength="100" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search" class="form-control" maxlength="75"  autocomplete="off" > 
	                					</div>
	                					</div>
	              					</div>	 
	  					</div>
									
						<div class="col-md-12">  
	  							<div class="col-md-6">
             					<div class="row form-group">
               					<div class="col col-md-6">
				                	<label for="text-input" class=" form-control-label">Whether New WE/PE <strong style="color: red;">*</strong></label>
				                </div>
               					<div class="col-12 col-md-6">
		                             <div class="form-check-inline form-check">
							               <label for="answer1" class="form-check-label ">
							                	<input type="radio" id="answer1" name="answer"  value="Yes" class="form-check-input" onclick="newdocument();">Yes
							                </label>&nbsp;&nbsp;
							                <label for="answer2" class="form-check-label ">
							                     <input type="radio" id="answer2" name="answer" value="No" class="form-check-input" onclick="newdocument();">No
							                      <input type="hidden"  id="prompt" name="prompt"  >	 
							                </label>
							            </div>
					              </div>	              						              
               				</div>
               			</div>
              					<div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-6" id="suprcdd_we_pe_no_div" style="display:none">
	                  						<label for="text-input" class=" form-control-label">Superseded Document No<strong style="color: red;">*</strong></label>
	                					</div>
	                					<div class="col-12 col-md-6" id="suprcdd_we_pe_no_div" style="display:none">
	                  						<input id="suprcdd_we_pe_no" name="suprcdd_we_pe_no" maxlength="100" class="form-control" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search">
										</div>
	  								</div>
	  							</div>	              					
       					</div>         					
	              		
	  						<div class="col-md-12">
	  						<div class="col-md-6">
              					<div class="row form-group">
                					<div class="col col-md-6">
                  						<label class=" form-control-label">WE/PE Title <strong style="color: red;">*</strong></label>
                					</div>
                					<div class="col-12 col-md-6">
                  						<input  id="table_title" name="table_title" maxlength="150" class="form-control" >
                					</div>
                					</div>
              					</div>
              					</div>
	  					
	  					<div class="col-md-12">		 
	  						<div class="col-md-6">
              					<div class="row form-group"> 
                					<div class="col col-md-6">
                  						<label for="text-input" class=" form-control-label">Effective From <strong style="color: red;">*</strong></label>
                					</div>
                					<div class="col-12 col-md-6">
                  						<input id="eff_frm_date" name="eff_frm_date" maxlength="10"  class="form-control" onchange="checkDate()" placeholder="dd-mm-yyyy">
									</div>
  								</div>
	  						</div>
	  						<div class="col-md-6">
  								<div class="row form-group">  								
                					<div class="col col-md-6">
                  						<label for="text-input" class=" form-control-label">Effective To <strong style="color: red;">*</strong></label>
                					</div>
                					<div class="col-12 col-md-6">
                  						<input id="eff_to_date" name="eff_to_date" maxlength="10" class="form-control" onchange="checkDate()" placeholder="dd-mm-yyyy">
									</div>
  								</div>
  							</div>
	  								
	  					</div>
	  					<div class="col-md-12">	  								
	  						<div class="col-md-6">
	  							<div class="row form-group">
					                <div class="col col-md-6">
					                  <label class=" form-control-label">Security Classification <strong style="color: red;">*</strong></label>
					                </div>
					                <div class="col-12 col-md-6">					                
					                <select name="doc_type" class="form-control" id ="doc_type">					                
		                                 <option value="Restricted">Restricted</option>
		                                 <option value="Confidential">Confidential</option>
		                                 <option value="Secret">Secret</option>		                                   
		                            </select>
					                </div>
					            </div>	  								
	  						</div>
	  						<div class="col-md-6">
	  							<div class="row form-group">
					                <div class="col col-md-6">
					                  <label class=" form-control-label">Arm/Service <strong style="color: red;">*</strong> </label>
					                </div>
					                <div class="col-12 col-md-6">	
					                 <input type="hidden" name="setTypeOnclick" id="setTypeOnclick"  value="${wepe}"/>				                
					                <select  class="form-control" id="arm" name="arm">	
					                	 ${selectArmNameList}
                 						<c:forEach var="item" items="${getArmNameList}" varStatus="num" >
           									<option value="${item[0]}" > ${item[1]}</option>
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
					                  <label class=" form-control-label">Sponsor Directorate <strong style="color: red;">*</strong></label>
					                </div>
					                <div class="col-12 col-md-6">	                 
					                
					                <select  class="form-control" id ="sponsor_dire" name ="sponsor_dire">	
					                 ${selectLine_dte}
	              						<c:forEach var="item" items="${getLine_DteList}" varStatus="num" >
                  								<option value="${item.line_dte_sus}"  name="${item.line_dte_name}" >${item.line_dte_name}</option>
                  							</c:forEach>
	                 					                                                  
		                            </select>
					                </div>
					            </div>	  								
	  						</div> 								
	  					
	  					</div>
	  					
	  					<div id="divPersTraUnitId" class="col-md-12">	
	  						<div class="col-md-6">
	  							<div class="row form-group">
					                <div class="col col-md-6">
					                  <label class=" form-control-label" id="lbltc" >Training Capacity <strong id="strongPers" style="color: red;">*</strong></label>
					                </div>
					                <div class="col-12 col-md-6">
					               		<input class="form-control" id ="training_capacity" name ="training_capacity" maxlength="5" onkeypress="return isNumber0_9Only(event)" autocomplete="off">
					                </div>
					            </div>	  								
	  						</div>			
	  						<div class="col-md-6" >
	  							<div class="row form-group">
					                <div class="col col-md-6">
					                  <label class=" form-control-label" id="lbluc" >Unit Category <strong id="strongPers" style="color: red; ">*</strong> </label>
					                </div>
					                <div class="col-12 col-md-6">
					               <select  class="form-control" id ="unit_category" name ="unit_category" >
					                  <option value="">--Select--</option>
		                                 <option value="Major">Major</option>
		                                 <option value="Minor">Minor</option>
		                                  	                                 
		                            </select>
					                </div>
					            </div>	  								
	  						</div>
	  					</div>
	  					<div class="col-md-12">				
	  						<div class="col-md-6">
	  							<div class="row form-group">
					                <div class="col col-md-6">
					                  <label class=" form-control-label">Remarks</label>
					                </div>
					                <div class="col-12 col-md-6">	                 
					                <textarea   class="form-control char-counter1" id ="remarks" name ="remarks" maxlength="255" ></textarea>
					                </div>
					            </div>	  								
	  						</div> 								
	  					
	  					</div>
	  		
	  		</div>
	  		<div class="card-footer" align="center">
				<input type="reset" class="btn btn-success btn-sm" value="Clear" onclick="clearall()">   
            		<input type="submit" class="btn btn-primary btn-sm"   value="Save"  onclick="return isvalidData();">
            		<i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm" onclick="Search();" value="Search">
         		<i class="fa fa-print"></i><input type="button" id="printId" class="btn btn-primary btn-sm btn_report" value="Print" onclick="printDiv();" disabled>
           </div> 						
	  </div>
	 </div>
	 	
</form:form>

<div style="width: 20%; padding-left: 1%; display: none;" id="divSerachInput">
	<input id="searchInput" type="text" style="font-size:12px; font-family: 'FontAwesome',Arial;margin-bottom: 5px;" placeholder="&#xF002; Search Word"  size="35" class="form-control">
</div>
<div class="col s12" id="divPrint" style="display: none;">
	 <div id="divShow" style="display: block;">	 
	 </div>
	 <div class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
					<span id="ip"></span>
			<table id="AttributeReportSearchWePe" class="table no-margin table-striped  table-hover  table-bordered report_print" > 
				 <thead >
					<tr>
						<th class="tableheadSer">Ser No</th>
						<th >MISO WE/PE No</th>
						<th >Uploaded WE/PE No</th>
						<th >Superseded WE/PE</th>
						<th >WE/PE Title</th>
						<th >Sponsor Dte</th>
						<th >Arm/Service</th>
						<th >Security Classification</th>
						<th id="thLetterId" >Letter No</th>
						<th id="thRemarkId" >Error Correction</th>
						<th class='lastCol'>Action</th>
					</tr>
				</thead> 
				<tbody>
					<c:forEach var="item" items="${list}" varStatus="num" >
						<tr>
							<td class="tableheadSer">${num.index+1}</td>
							<td >${item.we_pe_no}</td>
							<td >${item.uploaded_wepe}</td>
							<td >${item.suprcdd_we_pe_no}</td>
							<td >${item.table_title}</td>
							<td >${item.line_dte_name}</td>
							<td >${item.arm_desc}</td>
							<td >${item.doc_type}</td>
							<td id="thLetterId1" >${item.reject_letter_no}</td>
							<td id="thRemarkId1" >${item.reject_remarks}</td>
							<td id="thAction1" class='lastCol'>${item.id}</td>
						</tr>							
					</c:forEach>
				</tbody>
			</table>
		</div>
</div>

			<c:url value="uploadWePecondition1" var="searchUrl" />
    		<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="we_pe_no1">
    			<input type="hidden" name="we_pe01" id="we_pe01" value="0"/>
    			<input type="hidden" name="we_pe_no1" id="we_pe_no1" value="0"/>
    			<input type="hidden" name="uploaded_wepe1" id="uploaded_wepe1" value="0"/>
    			<input type="hidden" name="suprcdd_we_pe_no1" id="suprcdd_we_pe_no1" value="0"/>
    			<input type="hidden" name="table_title1" id="table_title1" value="0"/>
    			<input type="hidden" name="sponsor_dire1" id="sponsor_dire1" value="0"/>
    			<input type="hidden" name="arm_desc1" id="arm_desc1" value="0"/>
    			<input type="hidden" name="doc_type1" id="doc_type1" value="0"/>
    			<input type="hidden" name="status1" id="status1" value="0"/>
    			<input type="hidden" name="setTypeOnclick1" id="setTypeOnclick1" value="0"/>
    		</form:form> 

	
	<c:url value="delete_WE_PE_conditionUrl" var="deleteUrl" />
	<form:form action="${deleteUrl}" method="post" id="deleteForm" name="deleteForm" modelAttribute="deleteid">
		<input type="hidden" name="deleteid" id="deleteid" value="0"/>
		<input type="hidden" name="deleteType" id="deleteType" value=""/>
	</form:form> 
	
	  <c:url value="update_WE_PE_conditionUrl" var="updateUrl" />
	<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid" target="result">
		<input type="hidden" name="updateid" id="updateid" value="0"/>
		<input type="hidden" name="updateType" id="updateType" value=""/>
	</form:form> 
	
	 <c:url value="add_moreUrl" var="add_moreUrl" />
	<form:form action="${add_moreUrl}" method="post" id="add_moreForm" name="add_moreForm" modelAttribute="add_moreid" target="result">
		<input type="hidden" name="add_moreid" id="add_moreid" value="0"/>
		<input type="hidden" name="add_moreType" id="add_moreType" value="${wepe}"/>
		<input type="hidden" name="we_pe_no01" id="we_pe_no01" value="0"/>
	</form:form>


<script>
function Search(){
	var r =  $('input:radio[name=we_pe]:checked').val();	
	if(r == undefined)
	{		 
		alert("Please Select WE/PE");
		return false;
	}
	$("#we_pe01").val(r);
	$("#we_pe_no1").val($("#we_pe_no").val());
	$("#uploaded_wepe1").val($("#uploaded_wepe").val());
	$("#suprcdd_we_pe_no1").val($("#suprcdd_we_pe_no").val());
	$("#table_title1").val($("#table_title").val());
	$("#sponsor_dire1").val($("#sponsor_dire").val());
	$("#arm_desc1").val($("#arm").val());
	$("#doc_type1").val($("#doc_type").val());
	$("#status1").val($("#status").val());
	$("#setTypeOnclick1").val('${wepe}');
	document.getElementById('searchForm').submit();
}

var newWin;
function editData(id){	
	var x = screen.width/2 - 1100/2;
    var y = screen.height/2 - 900/2;
   // window.open(meh.href, 'sharegplus','height=485,width=700,left='+x+',top='+y);
    
	  newWin = window.open("", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');
	 
	 window.onfocus = function () { 
	 }
	
	 document.getElementById('updateid').value=id;
	   document.getElementById('updateType').value=document.getElementById("getTypeOnclick").value;
	   document.getElementById('updateForm').submit();
}

function closeWindow()
{
	newWin.close();   
}

function deleteData(id){
	$.post("delete_WE_PE_conditionUrlAdd?"+key+"="+value, {deleteid : id, deleteType: document.getElementById("getTypeOnclick").value}).done(function(j) {
	 	alert(j);
		Search();
	}).fail(function(xhr, textStatus, errorThrown) { });
	 
}
	
	
function AddMoreData(id,we_pe_no){	
	 var x = screen.width/2 - 1100/2;
	 var y = screen.height/2 - 900/2;
		  newWin = window.open("", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');
		 window.onfocus = function () { 
		}
		  document.getElementById('add_moreid').value=id;
		   document.getElementById('add_moreType').value=document.getElementById("getTypeOnclick").value;
		   $("#we_pe_no01").val(we_pe_no);
		  document.getElementById('add_moreForm').submit();
	}
	
</script>

<script>
function myFunction() {
  var prompt;
  var j =$('input:radio[name=prompt]:checked').val();
  var r = $('input:radio[name=answer]:checked').val();
		if(r=="No"){
			if (confirm("Do you want copy of superceded WE/PE data?")) {
    			 }
			else {
				document.getElementById('prompt').value ="No";
   				 }
			}
		}
</script>

</body>
</html>