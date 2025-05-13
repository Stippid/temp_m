<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>UPLOAD OF WE/PE DOCUMENT</title>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/excluded/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 


<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/cue/update.js" type="text/javascript"></script>
<script src="js/cue/printAllPages.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>


<script type="text/javascript">
$(document).ready(function () {

    var file = document.getElementById('doc_path1');
	file.onchange = function(e) {
	  	var ext = this.value.match(/\.([^\.]+)$/)[1];
		switch (ext) {
		  case 'pdf':
		    break;
		  default:
		    alert('Please Only Allowed *.pdf File Extension');
		    this.value = '';
		}
	};
});

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

<form:form id="WePeEditAction" name="WePeEditAction" action="WePeEditAction?${_csrf.parameterName}=${_csrf.token}" method="post"  class="form-horizontal" commandName="WePeEditCMD" enctype="multipart/form-data">
<div class="container" align="center">
	        <div class="card">
         		<div class="card-header"><h5>Edit WE/PE Upload </h5></div> 
         			<div class="card-body card-block cue_text">
                     	<div class="col-md-12">	
             			   <div class="col-md-6">
             				<div class="row form-group">
               					<div class="col col-md-6">
						                <label for="text-input" class=" form-control-label">Type of Document </label>
						         </div>
								 <div class="col col-md-6">
		                              <div class="form-check-inline form-check">
		                                <label for="inline-radio1" class="form-check-label ">
		                                <input type="hidden" id="id" name="id" value="${WePeEditCMD.id}" class="form-control" >
		                                <input  type="hidden" id="we_pe_radio" name="we_pe_radio" placeholder="" value="${WePeEditCMD.we_pe}" class="form-control">
		                                  <input type="radio" id="we_pe1" name="we_pe" value="WE" class="form-check-input"  value="${WePeEditCMD.we_pe}" disabled="disabled">WE
		                                </label>&nbsp;&nbsp;&nbsp;
		                                <label for="inline-radio2" class="form-check-label ">
		                                  <input type="radio" id="we_pe2" name="we_pe" value="PE" class="form-check-input" value="${WePeEditCMD.we_pe}" disabled="disabled">PE
		                                </label>&nbsp;&nbsp;&nbsp;
		                                <label for="inline-radio3" class="form-check-label ">
		                                  <input type="radio" id="we_pe3" name="we_pe" value="FE" class="form-check-input" value="${WePeEditCMD.we_pe}" disabled="disabled">FE
		                                </label>&nbsp;&nbsp;&nbsp;
		                                <label for="inline-radio4" class="form-check-label ">
		                                  <input type="radio" id="we_pe4" name="we_pe" value="GSL" class="form-check-input" value="${WePeEditCMD.we_pe}" disabled="disabled">GSL
		                                </label>&nbsp;&nbsp;&nbsp;
		                              </div>
					              </div>	              						              
               				</div>
               			</div>
               		</div>
					<div class="col-md-12">	  
						<div class="col-md-6">
             					<div class="row form-group">
               					<div class="col col-md-6">
                 						<label class=" form-control-label">MISO WE/PE No</label>
               					</div>
               					<div class="col-12 col-md-6">
               					<input  type="hidden" id="we_pe_id" name="we_pe_id" value="${WePeEditCMD.id}" class="form-control">
                 						<input  id="we_pe_no" name="we_pe_no" value="${WePeEditCMD.we_pe_no}" class="form-control" readonly="readonly">
               					</div>
               					</div>
             				</div>
             				<div class="col-md-6">
            					<div class="row form-group">
              					<div class="col col-md-6">
                						<label class=" form-control-label">Table Title</label>
              					</div>
              					<div class="col-12 col-md-6">
                						<input  id="table_title" name="table_title"  class="form-control" value="${WePeEditCMD.table_title}">
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
                 					<label for="text-input" class=" form-control-label">Superseded Document No <strong style="color: red;">*</strong></label>
               					</div>
               					<div class="col-12 col-md-6">
               					 <input type="hidden" id="supercddwepe" name="supercddwepe" value="${WePeEditCMD.suprcdd_we_pe_no}">
                 					<input value="${WePeEditCMD.suprcdd_we_pe_no}" id="suprcdd_we_pe_no" name="suprcdd_we_pe_no" class="form-control" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search">
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
	                  						<input  id="eff_frm_date" name="eff_frm_date" placeholder="dd-MM-yyyy" class="form-control" onchange="checkDate()"   value="${WePeEditCMD.eff_frm_date}">
										</div>
	  								</div>
		  						</div>
		  						<div class="col-md-6">
	  								<div class="row form-group">  								
	                					<div class="col col-md-6">
	                  						<label for="text-input" class=" form-control-label">Effective To <strong style="color: red;">*</strong></label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                  						<input   id="eff_to_date" name="eff_to_date" placeholder="dd-MM-yyyy" class="form-control" onchange="checkDate()"  value="${WePeEditCMD.eff_to_date}">
										</div>
	  								</div>
	  							</div>
	  							</div>

	  				 <div class="col-md-12">	  				
  							<div class="col-md-6">
  								<div class="row form-group">
  								<div class="col col-md-6">
        									<label class=" form-control-label">Security Classification</label> 
      								</div>
          							<div class="col-12 col-md-6">
          								<input type="hidden" id="doc_type_hidden" name="doc_type_hidden" value="${WePeEditCMD.doc_type}">
					                 <select class="form-control" id="doc_type" name="doc_type" >
					                  <option>--Select--</option>
					               			  <option  value="Restricted" >Restricted</option>
					                          <option  value="Confidential"> Confidential</option>
					                          <option  value="Secret">Secret</option>
					                  </select>
								  </div>
								 </div>
						   </div>       
							 <div class="col-md-6">
              					<div class="row form-group">
                				<div class="col col-md-6">
                  						<label for="text-input" class=" form-control-label">Arm <strong style="color: red;">*</strong></label>
                					</div>
                					<div class="col-12 col-md-6">
                					<input type="hidden" id="arm1" name="arm1" value="${WePeEditCMD.arm}">
                  						<select  id="arm"  name="arm" class="form-control">
<!--                   						 <option value="0">--Select--</option> -->
                 						${selectArmNameList}
                 						<c:forEach var="item" items="${getArmNameList}" varStatus="num" >
           									<option value="${item[0]}"  <c:if test="${item[0] eq WePeEditCMD.arm}">selected="selected"</c:if>> ${item[1]}</option>
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
<!--                   						<select  id="sponsor_dire" name="sponsor_dire" class="form-control"> -->
<!--                   						<option value="0">--Select--</option> -->
<%-- 		              						<c:forEach var="item" items="${getsponserListCue}" varStatus="num" > --%>
<%-- 		       									<option value="${item[1]}" <c:if test="${item[1] eq WePeEditCMD.sponsor_dire}">selected="selected"</c:if>> ${item[1]}</option> --%>
<%-- 		       								</c:forEach> --%>
<!--                   						</select> -->
												<select id="sponsor_dire" name="sponsor_dire" class="form-control-sm form-control" >
	                 						${selectLine_dte}
	                 						<c:forEach var="item" items="${getLine_DteList}" varStatus="num" >
                  								<option value="${item.line_dte_sus}" <c:if test="${item.line_dte_sus eq WePeEditCMD.sponsor_dire}">selected="selected"</c:if>> ${item.line_dte_name}</option>
                  							</c:forEach>
	                 					</select>
                					</div>
                				</div>
              				</div>
	              		<div class="col-md-6">
              			 	<div class="row form-group"> 
               					<div class="col col-md-6">
                 					<label for="text-input" class=" form-control-label">Upload File</label>
               					</div>
               					<div class="col-12 col-md-6">
               						<input type="file" id="doc_path1" name="doc_path1" class="form-control">
								</div>
 							</div> 
  						</div>	
  					</div> 
  					<div class="col-md-12"> 
  					 <div class="col-md-6">
              			 	<div class="row form-group"> 
               					<div class="col col-md-6">
                 					<label for="text-input" class=" form-control-label">Download Uploaded Document</label>
               					</div>
               					<div class="col-12 col-md-6">              					
									<a hreF="#" onclick="getDownloadImage('${WePeEditCMD.id}')" class="btn btn-primary btn-sm">Download</a>
								</div>
 							</div> 
  						</div>	
  						
  						
      				   <div class="col-md-6">
      					   <div class="row form-group">
        					<div class="col col-md-6" style="padding-right:0;">
          						<label class=" form-control-label">Remarks  </label>
        					</div>
        					<div class="col-12 col-md-6">
          						<textarea id="remarks" name="remarks" maxlength="255" class="form-control char-counter1">${WePeEditCMD.remarks}</textarea>
        					</div>
        					</div>
      				  </div> 	
	             
	  			  </div>
	     </div>	
	              	<div class="card-footer" align="center">
	              		<input type="submit" class="btn btn-primary btn-sm" value="Update" onclick=" return isCueValid();">  
	              		<a href="search_WE_PE" class="btn btn-danger btn-sm" onClick="javascript:window.close('','_parent','');">  Cancel </a>
		            </div> 		      
     </div>
  </div>

</form:form>
<c:if test="${not empty msg}">
	<input type="hidden" name="msg" id="msg" value="${msg}" disabled="disabled"/>
</c:if>

 <c:url value="update_WE_PE_Url" var="updateUrl" />
	<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid">
		<input type="hidden" name="updateid" id="updateid" value="0"/>
	</form:form> 



	<c:url value="getDownloadImage" var="imageDownloadUrl" />
        <form:form action="${imageDownloadUrl}" method="post" id="getDownloadImageForm" name="getDownloadImageForm" modelAttribute="id">
        	<input type="hidden" name="id1" id="id1" value="0"/>
        	<input type="hidden" name="pageUrl" id="pageUrl" value=""/>
        	<input type="hidden" name="contraint" id="contraint" value=""/>
        </form:form>


<script>

function newdocument() {
	var r = $('input:radio[name=answer]:checked').val();
	if(r == 'No')
	{
		$("div#suprcdd_we_pe_no_div").show();
		getsupercddListNo();
	}
	else
	{
		$("div#suprcdd_we_pe_no_div").hide(); 
		 document.getElementById("suprcdd_we_pe_no").value="";		
	}
}

</script>
<script >


$(document).ready(function() {
	  $('#remarks').keyup(function() {
	        this.value = this.value.toUpperCase();
	    });
	  $("#eff_frm_date").datepicker({
			dateFormat: "dd-mm-yy",    //"yy-mm-dd",
	        changeMonth: true,
	        changeYear: true
		});
	    
	    $("#eff_to_date").datepicker({
	        dateFormat: "dd-mm-yy",    //"yy-mm-dd",
	        changeMonth: true,
	        changeYear: true
		});

	var we_pe_hi = $("#we_pe_radio").val();
	 if(we_pe_hi == "WE"){		  
		  document.getElementById("we_pe1").checked = true;		
	 } 
	 if(we_pe_hi == "PE"){		  
		  document.getElementById("we_pe2").checked = true;		
	 } 
	 if(we_pe_hi == "FE"){		  
		  document.getElementById("we_pe3").checked = true;		
	 } 
	 if(we_pe_hi == "GSL"){		  
		  document.getElementById("we_pe4").checked = true;		
	 } 
	
	$("select#doc_type").val($("input#doc_type_hidden").val());
	
	if(document.getElementById("suprcdd_we_pe_no").value != "" && document.getElementById("suprcdd_we_pe_no").value != null && document.getElementById("suprcdd_we_pe_no").value != undefined)
	{
		document.getElementById("answer2").checked = true;
	}
	else
	{
		document.getElementById("answer1").checked = true;
	}	

	newdocument();	
	
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
    	    
</script>
<script>
 function isCueValid(){	
	 if($("input#table_title").val()==""){
			alert("Please Enter Document Title");
			$("input#table_title").focus();
			return false;
		}
			if($("select#suprcdd_we_pe_no").val()=="--Select--" ||  $("select#suprcdd_we_pe_no").val()=="0" ||  $("select#suprcdd_we_pe_no").val()=="")
			{
				alert("Please Select Superseded Document No");
				$("select#suprcdd_we_pe_no").focus();
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
		
			 return true;			
		}
</script>
<script>
function getsupercddListNo()
{
		var r =  $('input:radio[name=we_pe]:checked').val();
	var wepetext=$("#suprcdd_we_pe_no");
	
	wepetext.autocomplete({
	    source: function( request, response ) {
	      $.ajax({
	      type: 'POST',
	  	  url: "getsupercddList?"+key+"="+value,
	      data: {we_r : r,we_pe_no:document.getElementById('suprcdd_we_pe_no').value},
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
	          var autoTextVal=wepetext.val();
				$.each(dataCountry1.toString().split("|"), function(i,e){
					var newE = e.substring(0, autoTextVal.length);
					if (newE.toLowerCase() === autoTextVal.toLowerCase()) {
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
	        	  wepetext.val("");	        	  
	        	  wepetext.focus();
	        	  return false;	             
	          }   	         
	      }, 
	});
}

function getDownloadImage(id)
{  
	 document.getElementById("id1").value=id;
	   document.getElementById("contraint").value="Edit";
	   	 document.getElementById("pageUrl").value="edit_search_WE_PE_Tiles";
	    document.getElementById("getDownloadImageForm").submit();
	
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

