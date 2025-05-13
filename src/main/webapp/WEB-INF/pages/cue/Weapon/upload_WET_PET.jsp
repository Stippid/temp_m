<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %>
<%@ taglib prefix="dandelion" uri="http://github.com/dandelion" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Upload WET/PET/FET</title>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/excluded/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 

<script src="js/miso/miso_js/jquery-1.12.3.js"></script>

<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>

<script src="js/cue/update.js" type="text/javascript"></script>
<script src="js/cue/printAllPages.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>

</head>

<body>

<script>
function printDiv() 
  	{
	 	var printLbl = ["Type :"];
	 	var printVal = [$('input:radio[name=wet_pet]:checked').val()];
	 	printDivOptimize('divPrint','UPLOAD WET/PET',printLbl,printVal,"select#status");
 	 }
</script>

 
<form:form name="upload_wet_pet_fet" id="upload_wet_pet_fet" action="upload_wet_pet_fetAct?${_csrf.parameterName}=${_csrf.token}" method='POST' commandName="upload_wet_pet_fetCmd" enctype="multipart/form-data" >
	          		
		<div class="container" align="center">
        	<div class="card">
        		<div class="card-header"><h5><b>UPLOAD WET/PET</b><br><span style="font-size: 10px;font-size:15px;color:red">(To be entered by Etrc)</span></h5></div>
          		<div class="card-body card-block cue_text">
           			<div class="col-md-12">	            					
             				<div class="col-md-6">
             					<div class="row form-group">
               					<div class="col col-md-6">
				                	<label for="text-input" class=" form-control-label">Type of Document <strong style="color: red;">*</strong></label>
				                </div>
               					<div class="col-12 col-md-6">
		                             <div class="form-check-inline form-check">
		                                <label for="inline-radio1" class="form-check-label">
		                                  <input type="radio" id="wet_pet1" name="wet_pet" value="WET" maxlength="4" class="form-check-input">WET
		                                </label>&nbsp;&nbsp;&nbsp;
		                                <label for="inline-radio2" class="form-check-label">
		                                  <input type="radio" id="wet_pet2" name="wet_pet" value="PET" maxlength="4" class="form-check-input">PET
		                                </label>&nbsp;&nbsp;&nbsp;
		                                <label for="inline-radio2" class="form-check-label">
		                                  <input type="radio" id="wet_pet3" name="wet_pet" value="FET" maxlength="4" class="form-check-input">FET
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
                 					<label class=" form-control-label">WET/PET No <strong style="color: red;">*</strong></label>
               					</div>
               					<div class="col-12 col-md-6">
                 					<input  id="wet_pet_no" name="wet_pet_no" maxlength="50"  class="form-control">
               					</div>
               				</div>
             			</div>
             			<div class="col-md-6">
              			 	<div class="row form-group"> 
               					<div class="col col-md-6">
                 					<label for="text-input" class=" form-control-label">WET/PET Title <strong style="color: red;">*</strong></label>
               					</div>
               					<div class="col-12 col-md-6">
                 					<input type="text" id="unit_type" name="unit_type" maxlength="100"  class="form-control">
								</div>
 							</div> 
  				        </div>
             		</div>
             		<div class="col-md-12">
  					  <div class="col-md-6">
             					<div class="row form-group">
               					<div class="col col-md-6">
				                	<label for="text-input" class=" form-control-label">Whether New WET/PET <strong style="color: red;">*</strong></label>
				                </div>
               					<div class="col-md-6">
		                             <div class="form-check-inline form-check">
							               <label for="inline-radio1" class="form-check-label ">
							                	<input type="radio" id="answer01" name="answer1"  value="Yes" class="form-check-input" onclick="newdocument();">Yes
							                </label>&nbsp;&nbsp;
							                <label for="inline-radio1" class="form-check-label ">
							                     <input type="radio" id="answer02" name="answer1" value="No" class="form-check-input" onclick="newdocument();getWetPetsupercddListNo();">No
							                 </label>
							            </div>
					              </div>	              						              
               				</div>
               			</div>
  					  <div class="col-md-6" id="divsuperseeded_wet_pet" style="display:none"> 
           					<div class="row form-group">
             					<div class="col col-md-6" >
               						<label class=" form-control-label">Superseded Document No<strong style="color: red;">*</strong></label>
             					</div>
             					<div class="col-12 col-md-6" >      
		                      		<select id="superseeded_wet_pet" name="superseeded_wet_pet"  class="form-control"> </select>
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
                					<input id="valid_from" name="valid_from" placeholder="dd-MM-yyyy" class="form-control" onchange="checkDate()">
								</div>
							</div>
  						</div>
  						<div class="col-md-6">
 							<div class="row form-group">  								
               					<div class="col col-md-6">
                 					<label for="text-input" class=" form-control-label">Effective To <strong style="color: red;">*</strong></label>
               					</div>
               					<div class="col-12 col-md-6">
                 					<input  id="valid_till" name="valid_till" placeholder="dd-MM-yyyy" class="form-control" onchange="checkDate()">
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
				                <select id="doc_type" name="doc_type" class="form-control">
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
				                  	<label class=" form-control-label">Arm/Service <strong style="color: red;">*</strong></label>
				                </div>
				                <div class="col-12 col-md-6">
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
				                <select id="sponsor_dire" name="sponsor_dire" class="form-control-sm form-control" >
	                 						${selectLine_dte}
	                 						<c:forEach var="item" items="${getLine_DteList}" varStatus="num" >
                  								<option value="${item.line_dte_sus}"  name="${item.line_dte_name}" >${item.line_dte_name}</option>
                  							</c:forEach>
	                 					</select>
				              <!--   <input type="text" id="sponsor_dire" name="sponsor_dire" class="form-control"> -->
				                	<%-- <select id="sponsor_dire" name="sponsor_dire" class="form-control">
				                	 <option value="0">--Select--</option>
              						<c:forEach var="item" items="${getsponserListCue}" varStatus="num" >
       									<option value="${item[1]}" > ${item[1]}</option>
       								</c:forEach>
				                	 </select> --%>
				                </div>
				            </div>	  								
  						</div>
  					<div class="col-md-6">
              			 	<div class="row form-group"> 
               					<div class="col col-md-6">
                 					<label for="text-input" class=" form-control-label">Upload Document <strong style="color: red;">*</strong></label>
               					</div>
               					<div class="col-12 col-md-6">
                 					<input type="file" id="file_wet" name="file_wet" class="form-control">
								</div>
 							</div> 
  						</div>	
  				</div>
  				<div class="col-md-12">	
		  					<div class="col-md-6">
		              			<div class="row form-group">	              			 	
	               					<div class="col col-md-6">
	                 					<label for="text-input" class=" form-control-label">Remarks</label>
	               					</div>
	               					<div class="col-12 col-md-6">
	                 					<textarea id="remarks" name="remarks" maxlength="255"  class="form-control char-counter1"></textarea>
									</div>	 							
		  						</div>
		  						</div>	
	   					</div>	
  				
  			</div>
  				<div class="card-footer" align="center">
					    <input type="reset" class="btn btn-success btn-sm" value="Clear" onclick="clearAll()">   
             			<input type="submit" class="btn btn-primary btn-sm" value="Save" onclick="return isCueValid()">
             			<i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm" onclick="Search();" value="Search">
             			<i class="fa fa-print"></i><input type="button" id="printId" class="btn btn-primary btn-sm btn_report" value="Print" onclick="printDiv();" disabled>
            	</div> 
           </div>
  		</div>
	</form:form>
	
<div style="width: 20%; padding-left: 1%; display: none;" id="divSerachInput">
	<input id="searchInput" type="text" style="font-size:12px; font-family: 'FontAwesome',Arial;margin-bottom: 5px;" placeholder="&#xF002; Search Word"  size="35" class="form-control">
</div>
 <div class="col s12" style="display: none;" id="divPrint">
				<div id="divShow" style="display: block;">
				</div>
				<div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
					<span id="ip"></span>
							<table id="AttributeReportWetpetUpload" class="table no-margin table-striped  table-hover  table-bordered  report_print " >
								<thead>
									<tr>
										<th class="tableheadSer">Ser No</th>
									    <th >Document Type</th> 
										<th >MISO WE/PE No</th>
										<th >WET/PET Title</th>
										<th >Superseded WET/PET</th>
										<th >Sponsor Dte</th>
										<!-- <th >Security Classification</th> -->
										<th >Arm/Service</th>
										<th >Effective From</th>
										<th >Effective To</th>
										<th  class='lastCol'>Uploaded Document</th>
										<th  class='lastCol'>Action</th>
									</tr>
								</thead>
								<tbody >
								<c:forEach var="item" items="${list}" varStatus="num" >
								<tr>
									<td class="tableheadSer">${num.index+1}</td>
									<td >${item.wet_pet}</td>
									<td >${item.wet_pet_no}</td>
									<td >${item.unit_type}</td>
									<td >${item.superseeded_wet_pet}</td>
									<td >${item.line_dte_name}</td>
									<%-- <td >${item.doc_type}</td> --%>
									<td >${item.arm_desc}</td> 
									<td >${item.valid_from}</td>
									
									<td >${item.valid_till}</td>
									<td id="thAction" class='lastCol' >${item.doc_path}</td>
									<td id="thAction1"  class='lastCol'>${item.id}</td>
								</tr>
				          </c:forEach>
								
								</tbody>
							</table>
							</div>
						</div>
	       
	<c:url value="getDownloadImageWetPetUpload" var="imageDownloadUrl" />
        <form:form action="${imageDownloadUrl}" method="post" id="getDownloadImageForm" name="getDownloadImageForm" modelAttribute="id1">
        	<input type="hidden" name="id1" id="id1" value="0"/>
        </form:form> 
	
	<c:url value="update_WET_PET_Url" var="updateUrl" />
	<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid"   target="result">
		<input type="hidden" name="updateid" id="updateid" value="0"/>
	</form:form> 
	
	 
     <c:url value="upload_WET_PET1" var="searchUrl" />
    		<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="wet_pet01">
    			<input type="hidden" name="wet_pet01" id="wet_pet01" value="0"/>
    			<input type="hidden" name="wet_pet_no1" id="wet_pet_no1" value="0"/>
    			<input type="hidden" name="unit_type1" id="unit_type1" value="0"/>
    			<input type="hidden" name="valid_from1" id="valid_from1" value="0"/>
    			<input type="hidden" name="valid_till1" id="valid_till1" value="0"/>
    			<input type="hidden" name="arm1" id="arm1" value="0"/>
    			<input type="hidden" name="doc_type1" id="doc_type1" value="0"/>
    			<input type="hidden" name="sponsor_dire1" id="sponsor_dire1" value="0"/>
    			<input type="hidden" name="remarks1" id="remarks1" value="0"/>
    			<input type="hidden" name="status1" id="status1" value="0"/>
    	      </form:form> 
	
<script>
var newWin;
function editData(id){	
	var x = screen.width/2 - 1100/2;
    var y = screen.height/2 - 900/2;
   
	newWin = window.open("", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');
	 
	 window.onfocus = function () { 
		 // newWin.close();
	 }

	document.getElementById('updateid').value=id;
	document.getElementById('updateForm').submit();
}
function closeWindow()
{
	newWin.close();   
}
function deleteData(id){
	  $.post("delete_WET_PET_UrlAdd?"+key+"="+value, {deleteid : id}).done(function(j) {
    		alert(j);
			Search();
      }).fail(function(xhr, textStatus, errorThrown) { }); 	
}	
							
	
</script>
<script>	
function newdocument() {
 	var r = $('input:radio[name=answer1]:checked').val();
 	var r_w =  $('input:radio[name=wet_pet]:checked').val();
	if(r == 'No' && r_w !=undefined)
 	{
 		$("div#divsuperseeded_wet_pet").show();
 	}
 	else
 	{
 		$("div#divsuperseeded_wet_pet").hide(); 
 		$("input#superseeded_wet_pet").val("0");
 	}
 }	

function getWetPetsupercddListNo()
{
	 var r =  $('input:radio[name=wet_pet]:checked').val();	

     $.post("getWetPetsupercddList12?"+key+"="+value, {we_r : r}).done(function(j) {
    		var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
    		for ( var i = 0; i < j.length; i++) {
    			
    			options += '<option value="'+j[i]+'" name="' + j[i]+ '" >'+ j[i]+ '</option>';
    		}	
    		$("select#superseeded_wet_pet").html(options); 
      }).fail(function(xhr, textStatus, errorThrown) { }); 	
}


	
function checkDate()
{
	  var startDate = document.getElementById("valid_from").value;
	  var endDate = document.getElementById("valid_till").value;

	  var b = startDate.split(/\D/);
	  var c = endDate.split(/\D/);	  
	  
	  if ((Date.parse(c.reverse().join('-')) <= Date.parse(b.reverse().join('-'))))
	  {
	    alert("Effective To date should be greater than Effective From date");
	    document.getElementById("valid_till").value = "";
	  }	
}

function isCueValid(){
	var r =  $('input:radio[name=wet_pet]:checked').val();	
	  if(r == undefined)
	  {		 
		    alert("Please select Document");
		    $('input:radio[name=wet_pet]:checked').focus();
			return false;
	  }
	  if($("input#wet_pet_no").val() == "")
	  {
		alert("Please enter WET/PET/FET Document No");
		$("input#wet_pet_no").focus();
		return false;
	  }
	  
	  if($("input#unit_type").val() == "")
	  {
		alert("Please enter Table Tittle");
		$("input#unit_type").focus();
		return false;
	  }
	  var x = $('input:radio[name=answer1]:checked').val();
	   if (x == undefined) {
		   alert("Please Select New Document");
		   return false;
	   }
   	    else 
   	    {
   	    	if(x == "No"){
   	    	 var swetno = document.getElementById("superseeded_wet_pet").value;
   	    	
	   	    	if (swetno==0) {
				alert("Please enter Superseded Document No")
				$("select#superseeded_wet_pet").focus();
				return false;
				} 
   	    	}
   	    }
	  if($("input#valid_from").val() == "")
	  {
		alert("Please select Effective From");
		$("input#valid_from").focus();
		return false;
	  }
	  if($("input#valid_till").val() == "")
	  {
		alert("Please select Effective To");
		$("input#valid_till").focus();
		return false;
	  }
	  if($("select#arm").val() == "0")
	  {
		alert("Please select Arm");
		$("select#arm").focus();
		return false;
	  }
	  if($("select#sponsor_dire").val() == "0")
	  {
		alert("Please select Sponsor Directorate");
		$("select#sponsor_dire").focus();
		return false;
	  }
	  if($("select#doc_type").val() == "")
	  {
		alert("Please select Document Type");
		$("select#doc_type").focus();
		return false;
	  }
	  if($("input#file_wet").val() == "")
	  {
		alert("Please select Upload Document");
		$("input#file_wet").focus();
		return false;
	  }
	  if($("input#unit_type").val() == "")
	  {
		alert("Please enter Document Title");
		$("input#unit_type").focus();
		return false;
	  }  
	  return true;
}

function isCueValidSerach(){
 var r =  $('input:radio[name=wet_pet]:checked').val();	
	  if(r == undefined)
	  {		 
		    alert("Please select Document");
		    $('input:radio[name=wet_pet]:checked').focus();
			return false;
	  }
	  
	  if($("input#wet_pet_no").val() == "")
	  {
		alert("Please enter WET/PET/FET Document No");
		$("input#wet_pet_no").focus();
		return false;
	  } 
	  
	  return true;
}

$(document).ready(function() {
 if('${wet_pet01}' != "")
	{
	 $("#divPrint").show();
	 $("input[name=wet_pet][value="+'${wet_pet01}'+"]").prop('checked', true);
	
		$("#wet_pet_no").val('${wet_pet_no1}');
		$("#unit_type").val('${unit_type1}');
		$("#valid_from").val('${valid_from1}');
		$("#valid_till").val('${valid_till1}');
		$("#remarks").val('${remarks1}');
		$("#sponsor_dire").val('${sponsor_dire1}');
		$("#arm").val('${arm1}');
		$("#doc_type").val('${doc_type1}');
		
		$("div#divwatermark").val('').addClass('watermarked'); 
		watermarkreport();
		$("div#divSerachInput").show();  
		document.getElementById("printId").disabled = false;
		
		if('${list.size()}' == 0 ){
			 $("div#divSerachInput").hide(); 
			 document.getElementById("printId").disabled = true;
			 $("table#AttributeReportWetpetUpload").append("<tr><td colspan='12' style='text-align :center;'>Data Not Available</td></tr>");
		 }
}

 var file = document.getElementById('file_wet');
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
	 
	 $("#searchInput").on("keyup", function() {
			var value = $(this).val().toLowerCase();
			$("#AttributeReportWetpetUpload tbody tr").filter(function() { 
			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
			});
		});
	 $('#remarks').keyup(function() {
	        this.value = this.value.toUpperCase();
	    });
	 
	 $('#wet_pet_no').keyup(function() {
	        this.value = this.value.toUpperCase();
	    });
 	   
 	   $('#unit_type').keyup(function() {
   	        this.value = this.value.toUpperCase();
   	    });
 //	  $('#sponsor_dire').val("${roleAccess}");
 	 /*  if("${roleAccess}" == "Line_dte"  && "${roleSubAccess}" == 'Arm' ){
			 $('select#arm').val("${getArmNameList}");
			
			// document.getElementById("arm").disabled = true;   
		 } */
	 $("#valid_from").datepicker({
        dateFormat: "dd-mm-yy",   
        changeMonth: true,
        changeYear: true
	}).attr('readonly', 'readonly');
    
    $("#valid_till").datepicker({
        dateFormat: "dd-mm-yy",   
        changeMonth: true,
        changeYear: true
	}).attr('readonly', 'readonly');
    
    try{
    	if(window.location.href.includes("msg="))
		{
			var url = window.location.href.split("?msg=")[0];
		var m=  window.location.href.split("?msg=")[1];
		window.location = url;
		
		if(m.includes("Updated+Successfully") || m.includes("File+size+should+be+less+then+2+MB")){
			window.opener.getRefreshReport('wet_pet_upload_weap',m);
			window.close('','_parent','');
		}
		} 	
		}
		catch (e) {
			// TODO: handle exception
		}  
	
 });

 function clearAll()
 {		document.getElementById('divPrint').style.display='none';
	document.getElementById("printId").disabled = true;
 	var tab = $("#AttributeReportWetpetUpload");
 	tab.empty();
 	
 	$("div#divSerachInput").hide(); 
 	$("#searchInput").val("");
	$("#searchInput").hide();
	//document.location.reload();
 	localStorage.clear();
	localStorage.Abandon();
 	
 }

function getDownloadImageWetPetUpload(id)
{  
	document.getElementById("id1").value=id;
	document.getElementById("getDownloadImageForm").submit();	  	
}
	 
function Search() {
	
 var r =  $('input:radio[name=wet_pet]:checked').val();	
  if(r == undefined)
  {		 
	    alert("Please select Document");
	    $('input:radio[name=wet_pet]:checked').focus();
		return false;
  }
  
  		$("#wet_pet01").val(r);
  		$("#wet_pet_no1").val($("#wet_pet_no").val());    
  	    $("#unit_type1").val($("#unit_type").val());
  	    $("#valid_from1").val($("#valid_from").val());
  	    $("#valid_till1").val($("#valid_till").val());
        $("#arm1").val($("#arm").val());
        $("#doc_type1").val($("#doc_type").val());
        
        $("#sponsor_dire1").val($("#sponsor_dire").val());
 	        $("#remarks1").val($("#remarks").val());
  	    document.getElementById('searchForm').submit();
} 
	 
</script>


</body>
</html>