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
 	var wepe ="${updateType}";
</script>

<script type="text/javascript">
$(document).ready(function() {
	$('body').toggleClass('open');
	$('.nav-item').toggle();
	$("div#divLogoutHidShow").hide();	
	document.getElementById('menuToggle').style.pointerEvents = 'none';
	$('#left-panel , #menuToggle').css("display","none");
});
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
		 document.getElementById("suprcdd_we_pe_no").value="";		
	}
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
$(document).ready(function() {	
	  
	 $('#table_title').keyup(function() {
	     this.value = this.value.toUpperCase();
	 });
	 
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
	
	
	 $("div#divPersTraUnitId").hide();
	    if(roleid == 154 || roleid == 155 || roleid == 1)
	    	$("div#divPersTraUnitId").show();
	
	if ( document.getElementById("we_pe1").value !=null)
		{		
		var radioButtons = document.getElementsByName("we_pe");
		if (radioButtons != null) 
		{
		    for (var radioCount = 0; radioCount < radioButtons.length; radioCount++) 
		    {
		        if (radioButtons[radioCount].value ==  document.getElementById("we_pe1").value  ) 
		        {
		            radioButtons[radioCount].checked = true;
		            getWePeNoByType(radioButtons[radioCount].value);
		        }
		    }
		}			 
		}		
	if ( document.getElementById("doc_type1").value !=null)
	{  
		var mySelect = document.getElementById('doc_type');

		for(var i, j = 0; i = mySelect.options[j]; j++) {
		    if(i.value == document.getElementById("doc_type1").value  ) {
		        mySelect.selectedIndex = j;
		        break;
		    }
		}	
	}
			
	  	if ( document.getElementById("unit_category1").value !=null)
		{  
			var mySelect = document.getElementById('unit_category');

			for(var i, j = 0; i = mySelect.options[j]; j++) {
			    if(i.value == document.getElementById("unit_category1").value  ) {
			        mySelect.selectedIndex = j;
			        break;
			    }
			}	
		}
			//from 
			
// 	  	 if("${roleAccess}" === "Line_dte"  && "${roleSubAccess}" === 'Arm' ){	
// 			 $('select#arm').val("${roleArmCode}");
// 			 document.getElementById("arm").disabled = true;   
// 		 }	
	
	if(document.getElementById("suprcdd_we_pe_no").value != "" && document.getElementById("suprcdd_we_pe_no").value != null && document.getElementById("suprcdd_we_pe_no").value != undefined)
	{
		document.getElementById("answer2").checked = true;
	}
	else
	{
		document.getElementById("answer1").checked = true;
	}	

	newdocument();
	
	$('input[type=radio][name=we_pe]').change(function() {		
		var radio_doc = $(this).val();		
		getWePeNoByType(radio_doc);
	});
	
	
	try{
		document.getElementById("getTypeOnclick").value = wepe;
		var type = "" ;
		type = document.getElementById("getTypeOnclick").value;	
		if(type != null){		
			document.getElementById("setTypeOnclick").value=document.getElementById("getTypeOnclick").value;
			if(document.getElementById("getTypeOnclick").value == "1") //for PERSONNEL value is 1
			{
				$("div#divPersTraUnitId").show();
			}
			else 
			{
				$("div#divPersTraUnitId").hide();
			}
			
		}
		
		if(window.location.href.includes("?wepetype="))
		{
			 var url = window.location.href.split("?wepetype=")[1].split("&msg=")[0];
			//window.location = url; 
			 document.getElementById('updateid').value=id;
			   document.getElementById('updateType').value=document.getElementById("getTypeOnclick").value;
			   document.getElementById('updateForm').submit();
		} 	
	}catch (e) {
		// TODO: handle exception
	}
	
	});

function getWePeNoByType(radio_doc)
{
	 if(radio_doc != ""){
	  var wepetext1=$("#suprcdd_we_pe_no");
	  wepetext1.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
	        type: 'POST',
	        url: "getWePeCondiByNoInWEPECon?"+key+"="+value,
	        data: {type : radio_doc,newRoleid:wepe,we_pe_no:document.getElementById('suprcdd_we_pe_no').value},
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
	          } else {
	        	  alert("Please Enter Approved Superseded Document No");
	        	  wepetext1.val("");	        	  
	        	  wepetext1.focus();
	        	  
	        	  document.getElementById("table_title").value="";
	    		  document.getElementById("eff_frm_date").value="";
	    		  document.getElementById("eff_to_date").value="";
	    		  $("select#doc_type").val("Restricted");
	    		  $("select#sponsor_dire").val("0");
	    		  $("select#arm").val("0");
		        	  
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
	  
function getDetailsBySupeerseedNo(val)
{
	  if(val != "" && val != null)
	  {
		 $.post("getDetailsByWePeCondiNo?"+key+"="+value, {val : val}).done(function(j) {
			 document.getElementById("table_title").value=j[0].table_title;
				document.getElementById("eff_frm_date").value=j[0].eff_frm_date;
				document.getElementById("eff_to_date").value=j[0].eff_to_date;
				$("select#doc_type").val(j[0].doc_type);
				$("select#sponsor_dire").val(j[0].sponsor_dire);
				$("select#arm").val(j[0].arm); 
		
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
		  $("select#arm").val("0");
	  }
}

function isvalidData(){
	if ($("input#uploaded_wepe").val() == "")
	{
		alert("Please Select Approved Uploaded Document No");
		$("select#uploaded_wepe").focus();
		return false;
	} 	
	if ($("input#table_title").val() == "")
	{
		alert("Please Select Document Title");
		$("select#table_title").focus();
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
	
	if($("input#eff_frm_date").val() == "")
	{
		alert("Please Select Effective From");
		$("select#eff_frm_date").focus();
		return false;
	} 	
	
	 if($("input#eff_to_date").val() == "")
	{
		alert("Please Select Effective To");
		$("select#eff_to_date").focus();
		return false;
	} 		
	
	if($("select#doc_type").val()=="--Select--" ||  $("select#doc_type").val()=="0" ||  $("select#doc_type").val()=="")
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
<body>

<form:form name="Miso_cue_we_pe_fe_editconditionForm" id="Miso_cue_we_pe_fe_editconditionForm" action="WePeconditionEditAction" method='POST' commandName="WePeconditionEditCMD">

	    	<div class="container" align="center">
	        	<div class="card">
	          		<div class="card-header"><h5>Edit WE/PE Conditions</h5></div>
	          			<div class="card-body card-block cue_text">
	            			<div class="col-md-12">	            					
	              				<div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-6">
						                	<label for="text-input" class=" form-control-label">Type of Document</label>
						                </div>		
						                <input type="hidden" name="getTypeOnclick" id="getTypeOnclick" value="${wepe}"/> 					              
							                <div class="col-12 col-md-6">
				                              <div class="form-check-inline form-check">
				                               <input id="id" type="hidden" name="id" value="${WePeconditionEditCMD.id}">    
				                                  <input type ="hidden" id= "we_pe1" value="${WePeconditionEditCMD.we_pe}">	
				                                <label for="inline-radio1" class="form-check-label ">
				                                  <input type="radio" id="inline-radio1" name="we_pe" value="WE" maxlength="4" class="form-check-input" disabled>WE
				                                </label>&nbsp;&nbsp;&nbsp;
				                                <label for="inline-radio2" class="form-check-label ">
				                                  <input type="radio" id="inline-radio2" name="we_pe" value="PE" maxlength="4" class="form-check-input" disabled>PE
				                                </label>&nbsp;&nbsp;&nbsp;
				                                <label for="inline-radio3" class="form-check-label ">
				                                  <input type="radio" id="inline-radio3" name="we_pe" value="FE" maxlength="4" class="form-check-input" disabled>FE
				                                </label>&nbsp;&nbsp;&nbsp;
				                                <label for="inline-radio4" class="form-check-label ">
				                                  <input type="radio" id="inline-radio4" name="we_pe" value="GSL" maxlength="4" class="form-check-input" disabled>GSL
				                                </label>&nbsp;&nbsp;&nbsp;
				                              </div>
				                            </div>					              
	                				</div>
	                			</div>
	  							<div class="row form-group row_content">
					                <div class="col col-md-6">
					                  <label class=" form-control-label" id="lbluc">Approved Uploaded Document No <strong style="color: red;">*</strong></label>
					                </div>
					                <div class="col-12 col-md-6">
					                <input  class="form-control" id ="uploaded_wepe" name ="uploaded_wepe" maxlength="50" value="${WePeconditionEditCMD.uploaded_wepe}" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search">
					                </div>
					            </div>	  								
	  						</div>           					
	            			</div>	            			
	            			<div class="col-md-12" style="display: block;">				
	  						<div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-6">
	                  						<label class=" form-control-label">MISO WE/PE No</label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                  						<input  id="we_pe_no" name="we_pe_no" maxlength="100" class="form-control" disabled value="${WePeconditionEditCMD.we_pe_no}">
	                					</div>
	                					</div>
	              					</div>	
	  					</div>
						<div class="col-md-12">	
	  						<div class="col-md-6">
	  						<div class="row form-group">
	  							<div class="col col-md-6">
				                	<label for="text-input" class=" form-control-label"> New Document </label>
				                </div>
				                <div class="col-12 col-md-6"> 
				                  <div class="form-check-inline form-check">                           
					               <label for="inline-radio1" class="form-check-label ">
					                	<input type="radio" id="answer1" name="answer"  value="Yes" class="form-check-input" onclick="newdocument();" >Yes
					                </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					                <label for="inline-radio1" class="form-check-label ">
					                     <input type="radio" id="answer2" name="answer" value="No" class="form-check-input" onclick="newdocument();" >No
					                 </label>				            
					            </div>
					            </div>
					          </div>
			              </div>
							 <div class="col-md-6" id="suprcdd_we_pe_no_div" style="display:none">
	              			 	<div class="row form-group"> 
	               					<div class="col col-md-6">
	                 					<label for="text-input" class=" form-control-label">Superseded Document No<strong style="color: red;">*</strong></label>
	               					</div>
	               					<div class="col-12 col-md-6">
	                 					<input  id="suprcdd_we_pe_no" name="suprcdd_we_pe_no" maxlength="50" class="form-control" value="${WePeconditionEditCMD.suprcdd_we_pe_no}" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search">
									</div>
	 							</div> 
	  						</div>
	  						</div>
	  						<div class="col-md-12">
		  						<div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-6">
	                  						<label class=" form-control-label">Document Title <strong style="color: red;">*</strong></label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                  						<input  id="table_title" name="table_title" maxlength="150" class="form-control" value="${WePeconditionEditCMD.table_title}">
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
	                  						<input  id="eff_frm_date" name="eff_frm_date" placeholder="dd-MM-yyyy" class="form-control" onchange="checkDate()"   value="${WePeconditionEditCMD.eff_frm_date}">
										</div>
	  								</div>
		  						</div>
		  						<div class="col-md-6">
	  								<div class="row form-group">  								
	                					<div class="col col-md-6">
	                  						<label for="text-input" class=" form-control-label">Effective To <strong style="color: red;">*</strong></label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                  						<input   id="eff_to_date" name="eff_to_date" placeholder="dd-MM-yyyy" class="form-control" onchange="checkDate()"  value="${WePeconditionEditCMD.eff_to_date}">
										</div>
	  								</div>
	  							</div>
	  							</div>
	  					<div class="col-md-12">	  								
	  						<div class="col-md-6">
	  							<div class="row form-group">
					                <div class="col col-md-6">
					                  <label class=" form-control-label">Security Classification<strong style="color: red;">*</strong></label>
					                </div>
					                <div class="col-12 col-md-6">	
					               <input type ="hidden" id= "doc_type1" value="${WePeconditionEditCMD.doc_type}">					                
					                <select name="doc_type" class="form-control" id ="doc_type"   >					                
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
					                  <label class=" form-control-label">Arm <strong style="color: red;">*</strong></label>
					                </div>
					                <div class="col-12 col-md-6">	
		                            <select  class="form-control" id="arm" name="arm_sel"  >
<!-- 					                <option value="0">--Select--</option> -->
	              						${selectArmNameList}
	              						<c:forEach var="item" items="${getArmNameList}" varStatus="num" >
	       									<option value="${item[0]}"  <c:if test="${item[0] eq WePeconditionEditCMD.arm}">selected="selected"</c:if>> ${item[1]}</option>
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
<!-- 					                <select  class="form-control" id ="sponsor_dire" name ="sponsor_dire" > -->
<!-- 	                                <option value="0">--Select--</option> -->
<%-- 	              						<c:forEach var="item" items="${getsponserListCue}" varStatus="num" > --%>
<%-- 	       									<option value="${item[1]}" <c:if test="${item[1] eq WePeconditionEditCMD.sponsor_dire}">selected="selected"</c:if>> ${item[1]}</option> --%>
<%-- 	       								</c:forEach>                    --%>
<!-- 		                            </select> -->
										 <select id="sponsor_dire" name="sponsor_dire" class="form-control-sm form-control" >
	                 							${selectLine_dte}
	                 					
	                 						<c:forEach var="item" items="${getLine_DteList}" varStatus="num" >
                  									<option value="${item.line_dte_sus}" <c:if test="${item.line_dte_sus eq WePeconditionEditCMD.sponsor_dire}">selected="selected"</c:if>> ${item.line_dte_name}</option>                   					
                  							</c:forEach>
	                 					</select>
					                </div>
					            </div>	  								
	  						</div>
	  					</div>
	  					<div id="divPersTraUnitId" class="col-md-12" style="display: none;">	
	  					<div class="col-md-6">
	  							<div class="row form-group">
					                <div class="col col-md-6">
					                  <label class=" form-control-label" id="lbltc" >Training Capacity <strong id="strongPers" style="color: red;">*</strong></label>
					                  <input type="hidden" name="setTypeOnclick" id="setTypeOnclick" />
					                </div>
					                <div class="col-12 col-md-6"> 
					               <input class="form-control" id ="training_capacity" name ="training_capacity" maxlength="19" value="${WePeconditionEditCMD.training_capacity}">
					               </div>
					            </div>	  								
	  					</div>	
	  					<div class="col-md-6">
	  							<div class="row form-group">
					                <div class="col col-md-6">
					                  <label class=" form-control-label" id="lbluc" >Unit Category <strong id="strongPers" style="color: red;">*</strong></label>
					                </div>
					                <div class="col-12 col-md-6">
					                   <input type ="hidden" id= "unit_category1" value="${WePeconditionEditCMD.unit_category}">
					                <select  class="form-control" id ="unit_category" name ="unit_category"  >
					                  <option value="">--Select--</option>
		                                 <option value="Major">Major</option>
		                                 <option value="Minor">Minor</option>
		                            </select>
					                </div>
					            </div>	  								
	  						</div>
	  					</div>
	  		    </div>
	  		        <div class="card-footer" align="center">
						<input type="submit" class="btn btn-primary btn-sm" value="Update" onclick="return isvalidData();">
	              		<a href="#" class="btn btn-danger btn-sm" onClick="javascript:window.close('','_parent','');setTrainUnitCategorySearch();">  Cancel </a>
		            </div> 						
	  </div>
			
</form:form>

	 <c:url value="update_WE_PE_conditionUrl" var="updateUrl" />
	<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid">
		<input type="hidden" name="updateid" id="updateid" value="0"/>
		<input type="hidden" name="updateType" id="updateType" value="${updateType}"/>
	</form:form>	
	
	<c:url value="searchWePecondition" var="wepeSearchUrl" />
	<form:form action="${wepeSearchUrl}" method="post" id="wepeSearchForm" name="wepeSearchForm">
		<input type="hidden" name="wepetypesearch" id="wepetypesearch"  value="${updateType}"/>
	</form:form> 
	
	
	<script type="text/javascript">	
	function setTrainUnitCategorySearch()
	{
		var type=document.getElementById('setTypeOnclick').value;
		if(type == "1") //for PERSONNEL value is 1
		{		
			document.getElementById('wepetypesearch').value=type;
			document.getElementById('wepeSearchForm').submit();		 
		}
		else if(type == "2") // for TRANSPORT value is 2
		{
			document.getElementById('wepetypesearch').value=type;
			document.getElementById('wepeSearchForm').submit();		
		}
		else if(type == "3") // for WEAPON value is 3
		{
			 document.getElementById('wepetypesearch').value=type;
			document.getElementById('wepeSearchForm').submit();
		}
	}
	function getWePeNoByType(radio_doc)
	{
		 
		 if(radio_doc != ""){
		
		
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
		        	//getDetailsByUploadedwepeNo($(this).val());	        	
		        }  
	           
		    });
		  
		  
		  $.ajaxSetup({
			    async: false
			});
		  
		 	 }
		 else
			 alert("Please select Document");
		
	}
	</script>
</body>
</html>