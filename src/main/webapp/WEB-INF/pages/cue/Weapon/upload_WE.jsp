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
	var printVal = [$('input:radio[name=we_pe]:checked').val()];
	printDivOptimize('divPrint','UPLOAD OF WE/PE DOCUMENT',printLbl,printVal,"select#status");
}
</script>

<form:form id="cuewepeAction1" name="cuewepeAction1" action="cuewepeAction1?${_csrf.parameterName}=${_csrf.token}" method="post"  class="form-horizontal" commandName="cuewepeCMD" enctype="multipart/form-data"> 
<div class="container" align="center">
	        	<div class="card">
	        		<div class="card-header"><h5><b>UPLOAD OF WE/PE DOCUMENT</b><br><span style="font-size: 10px;font-size:15px;color:red">(To be entered by SD Dte)</span></h5></div>
	          			<div class="card-body card-block cue_text">
	                 	<div class="col-md-12">	            					
             				<div class="col-md-6">
             					<div class="row form-group">
               					<div class="col col-md-6">
				                	<label for="text-input" class=" form-control-label">Type of Document <strong style="color: red;">*</strong></label>
				                </div>
               					<div class="col-12 col-md-6">
		                             <div class="form-check-inline form-check">
		                                <label for="inline-radio1" class="form-check-label ">
		                                  <input type="radio" id="we_pe1" name="we_pe" value="WE" maxlength="4" class="form-check-input">WE	
		                                </label>&nbsp;&nbsp;&nbsp;
		                                <label for="inline-radio2" class="form-check-label ">
		                                  <input type="radio" id="we_pe2" name="we_pe" value="PE" maxlength="4" class="form-check-input">PE
		                                </label>&nbsp;&nbsp;&nbsp;
		                                <label for="inline-radio3" class="form-check-label ">
		                                  <input type="radio" id="we_pe3" name="we_pe" value="FE" maxlength="4" class="form-check-input">FE
		                                </label>&nbsp;&nbsp;&nbsp;
		                                <label for="inline-radio4" class="form-check-label ">
		                                  <input type="radio" id="we_pe4" name="we_pe" value="GSL" maxlength="4" class="form-check-input">GSL
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
	                  						<label class=" form-control-label">MISO WE/PE No <strong style="color: red;">*</strong></label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                						<input  id="we_pe_no" name="we_pe_no" maxlength="100" class="form-control" autocomplete="off" >
	                					</div>
	                					</div>
	              				</div>
  								<div class="col-md-6">
              					<div class="row form-group">
                					<div class="col col-md-6">
                  						<label class=" form-control-label">WE/PE Title <strong style="color: red;">*</strong></label>
                					</div>
                					<div class="col-12 col-md-6">
                  						<input  id="table_title" name="table_title" maxlength="150" autocomplete="off" class="form-control" >
                					</div>
                					</div>
              					</div>
	              		</div>			
	              		  <div class="col-md-12">  
	  							<div class="col-md-6">
             					<div class="row form-group">
               					<div class="col col-md-6">
				                	<label for="text-input" class=" form-control-label">Whether New WE/PE<strong style="color: red;">*</strong></label>
				                </div>
               					<div class="col-12 col-md-6">
		                             <div class="form-check-inline form-check">
							               <label for="inline-radio1" class="form-check-label ">
							                	<input type="radio" id="answer" name="answer"  value="Yes" class="form-check-input" onclick="newdocument();">Yes
							                </label>&nbsp;&nbsp;
							                <label for="inline-radio1" class="form-check-label ">
							                     <input type="radio" id="answer" name="answer" value="No" class="form-check-input" onclick="newdocument();getsupercddListNo();">No
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
                  					 <input id="suprcdd_we_pe_no" name="suprcdd_we_pe_no" maxlength="100"  class="form-control" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search"> 
									</div>				
  								</div>
  							</div>
	              	   </div>
	  						<div class="col-md-12">    		
	  							<div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-6">
	                  						<label class=" form-control-label">Effective From <strong style="color: red;">*</strong></label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                  						<input  id="eff_frm_date" name="eff_frm_date" placeholder="dd-MM-yyyy"  class="form-control" onchange="checkDate()">
	                					</div>
	                				</div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-6">
	                  						<label for="text-input" class=" form-control-label">Effective To <strong style="color: red;">*</strong></label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                  						<input id="eff_to_date" name="eff_to_date" placeholder="dd-MM-yyyy" class="form-control" onchange="checkDate()">
										</div>
	  								</div>
	  							</div>
	  						</div>		
	  							<div class="col-md-12"> 
	  								<div class="col-md-6">
	  								<div class="row form-group">
	  								     <div class="col col-md-6">
               									<label class=" form-control-label">Security Classification<strong style="color: red;">*</strong></label> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
             							  </div>
             							<div class="col-12 col-md-6">
							                 <select class="form-control" id="doc_type" name="doc_type" >
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
	                  						<label for="text-input" class=" form-control-label">Arm/Service <strong style="color: red;">*</strong></label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                  						<select  id="arm"  name="arm" class="form-control" >
	                  						${selectArmNameList}
	                  						<!-- <option value="0">--Select--</option> -->
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
	                  						<%-- <select  id="sponsor_dire" name="sponsor_dire" class="form-control" >
	                  						<!-- <option value="0">--Select--</option> -->
			              						<c:forEach var="item" items="${getsponserListCue}" varStatus="num" >
			       									<option value="${item[1]}" > ${item[1]}</option>
			       								</c:forEach>
	                  						</select> --%>
	                  						<!-- <input type="text" id="sponsor_dire" name="sponsor_dire" class="form-control"> -->
	                  						<select id="sponsor_dire" name="sponsor_dire" class="form-control-sm form-control" >
	                 						${selectLine_dte}
	                 						<c:forEach var="item" items="${getLine_DteList}" varStatus="num" >
                  								<option value="${item.line_dte_sus}"  name="${item.line_dte_name}" >${item.line_dte_name}</option>
                  							</c:forEach>
	                 					</select>
	                					</div>
	                				</div>
	              				</div>       
							    <div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-6">
	                  						<label class=" form-control-label">Upload File <strong style="color: red;">*</strong></label>
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
        					<div class="col col-md-6" style="padding-right:0;">
          						<label class=" form-control-label">Remarks  </label>
        					</div>
        					<div class="col-12 col-md-6">
          						<textarea id="remarks" name="remarks" maxlength="255" class="form-control char-counter1"></textarea>
        					</div>
        					</div>
      				  </div> 	
	             </div>   	
	              	</div>
	              	<div class="card-footer" align="center">
						<input type="reset" class="btn btn-success btn-sm" value="Clear" onclick="clearall()">   
	              		<input type="submit" class="btn btn-primary btn-sm" value="Save"  onclick="return isCueValid()" >
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
						<table  id="AttributeReportSearchWePeUpload" class="table no-margin table-striped  table-hover  table-bordered  report_print" >
							<thead >
								<tr>
									<th class="tableheadSer">Ser No</th>
								    <th >Type of Document</th> 
									<th >MISO WE/PE No</th>
									<th >WE/PE Title</th>
									<th >Superseded Document No</th>
									<th >Sponsor Directorate</th>
									<th >Arm/Service</th>
									<th id="thLetterId" >Letter No</th>
									<th id="thRemarkId" >Error Correction</th>
									<th class='lastCol'>Uploaded Document</th>
									<th class='lastCol'>Action</th>
								</tr>
							</thead>
				<tbody>
				<c:forEach var="item" items="${list}" varStatus="num" >
					<tr>
						<td class="tableheadSer">${num.index+1}</td>
						<td >${item.we_pe}</td>
						<td >${item.we_pe_no}</td>
						<td >${item.table_title}</td>
						<td >${item.suprcdd_we_pe_no}</td>
						<td >${item.line_dte_name}</td>
						<td >${item.arm_desc}</td>
						<td id="thLetterId1"  >${item.reject_letter_no}</td>
						<td id="thRemarkId1"  >${item.reject_remarks}</td>
						<td id="thAction" class='lastCol'>${item.softdelete}</td>
						<td id="thAction1"   class='lastCol'>${item.id}</td>
					</tr>
				</c:forEach>
				</tbody>
						</table>
						</div> 
					</div>
             <c:url value="upload_WE1" var="searchUrl" />
    			<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="parent_code1">
    			<input type="hidden" name="we_pe01" id="we_pe01" value="0"/>
    			<input type="hidden" name="we_pe_no1" id="we_pe_no1" value="0"/>
    			<input type="hidden" name="sponsor_dire1" id="sponsor_dire1" value="0"/>
    			<input type="hidden" name="doc_type1" id="doc_type1" value="0"/>
    			<input type="hidden" name="arm1" id=arm1 value="0"/>
    			<input type="hidden" name="status1" id="status1" value="0"/>
    		</form:form> 
    		
    		
<c:url value="getDownloadImageWePeUpload" var="imageDownloadUrl" />
        <form:form action="${imageDownloadUrl}" method="post" id="getDownloadImageForm" name="getDownloadImageForm" modelAttribute="id1">
        	<input type="hidden" name="id1" id="id1" value="0"/>
</form:form> 

 <c:url value="update_WE_PE_Url" var="updateUrl" />
	<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid" target="result">
		<input type="hidden" name="updateid" id="updateid" value="0"/>
	</form:form>	
	
<script>

function Search(){
	var we_pe1 = $("input[name='we_pe']:checked").val(); 
	if(we_pe1 == undefined)
	  {		 			
		    alert("Please Select Document");
		    $('input:radio[name=we_pe]:checked').focus();
			return false;
	  }
	   	
	$("#we_pe01").val(we_pe1);
	$("#we_pe_no1").val($("#we_pe_no").val());
    $("#sponsor_dire1").val($("#sponsor_dire").val());
    $("#doc_type1").val($("#doc_type").val());
    $("#arm1").val($("#arm").val());
    $("#status1").val($("#status").val());
    document.getElementById('searchForm').submit();
	}
	
	
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
	 $.post("delete_WE_PE_UrlAdd?"+key+"="+value, {deleteid : id}).done(function(j) {
    		alert(j);
    		Search();
      }).fail(function(xhr, textStatus, errorThrown) { }); 	
}
	
							
	
</script>
<script> 
$(document).ready(function() {
	if('${we_pe01}' != "")
	{
		$("#sponsor_dire").val('${sponsor_dire1}');
		$("#arm").val('${arm1}');
		$("input[name=we_pe][value="+'${we_pe01}'+"]").prop('checked', true);
		// $("#doc_type1").val($("#doc_type").val());
		 $("#doc_type").val('${doc_type1}');
		$("#divPrint").show();
		$("div#divwatermark").val('').addClass('watermarked'); 
		watermarkreport();
		$("div#divSerachInput").show(); 
		document.getElementById("printId").disabled = false;
		
		if('${list.size()}' == 0 ){
			$("div#divSerachInput").hide(); 
			document.getElementById("printId").disabled = true;
			$("table#AttributeReportSearchWePeUpload").append("<tr><td colspan='9' style='text-align :center;'>Data Not Available</td></tr>");
		 }
	}
		
	
	$("#searchInput").on("keyup", function() {
	  			var value = $(this).val().toLowerCase();
	  			$("#AttributeReportSearchWePeUpload tbody tr").filter(function() { 
	  			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
	  			});
	  		});
	
	  $('#remarks').keyup(function() {
	        this.value = this.value.toUpperCase();
	    });
	 
	 // $('#sponsor_dire').val("${roleAccess}");
// 	  if("${roleAccess}" === "Line_dte"  && "${roleSubAccess}" === 'Arm' ){
// 			 $('select#arm').val("${getArmNameList}");
			
// 			// document.getElementById("arm").disabled = true;   
// 		 }
	  $.ajaxSetup({
	        async: false
	    });
	  			
	  try{		  
		  if(window.location.href.includes("msg="))
				{
					var url = window.location.href.split("?msg=")[0];
				var m=  window.location.href.split("?msg=")[1];
				window.location = url;
				
				if(m.includes("Updated+Successfully") || m.includes("File+size+should+be+less+then+2+MB")){
					window.opener.getRefreshReport('we_pe_upload_weap',m);
					window.close('','_parent','');
				}
				}
		}
		catch (e) {
			// TODO: handle exception
		}
	  
});

function clearall()
{	document.getElementById('divPrint').style.display='none';
	document.getElementById("printId").disabled = true;
	
	var tab = $("#AttributeReportSearchWePeUpload");
	tab.empty();
	$("#searchInput").val("");
	$("#searchInput").hide();
	//document.location.reload();
 	localStorage.clear();
	localStorage.Abandon();
}

</script>

<script>
function isCueValid(){
	var r =  $('input:radio[name=we_pe]:checked').val();	
		  if(r == undefined)
	  {		 			
		    alert("Please Select Document");
		    $('input:radio[name=we_pe]:checked').focus();
			return false;
	  }
	   if ($("input#we_pe_no").val() == "") {
			alert("Please Enter WE/PE No")
			$("input#we_pe_no").focus();
			return false;
		}
	   if ($("input#table_title").val() == "") {
			alert("Please Enter Table Title")
			$("input#table_title").focus();
			return false;
		}
	  var answer = $('input:radio[name=answer]:checked').val();
	  if (answer == null) {
		   alert("Please Select New Document");
		   return false;
	   }  
 	 else if (answer == "No") {				
				  var sweno1 = $("#suprcdd_we_pe_no").val();  	 
				 if (sweno1=="") {
						alert("Please enter Superseded Document No")
						$("input#suprcdd_we_pe_no").focus();
						return false;
					}
			} 
	  	  
	  
	  	  if ($("input#eff_frm_date").val() == "") {
			alert("Please Enter Effective From Date")
			$("input#eff_frm_date").focus();
			return false;
		}	  	
	  
	   if ($("input#eff_to_date").val() == "") {
			alert("Please Enter Effective To Date");
			
			$("input#eff_to_date").focus();
			return false;
		}
	 var e = document.getElementById("arm");
	 var strUser = e.options[e.selectedIndex].value;

	 
	if (strUser==0) {
			alert("Please Select Arm")
			$("select#arm").focus();
			return false;
		} 
	 var s = document.getElementById("sponsor_dire");
	 var SponsorDire = s.options[s.selectedIndex].value;
	if (SponsorDire==0) {
			alert("Please Select Sponsor Directorate")
			$("select#sponsor_dire").focus();
			return false;
		} 
	 
		  if ($("input#doc_path1").val() == "") {
			alert("Please Enter Document Path")
			$("input#doc_path1").focus();
			return false;
		}
	  return true;
	  }
 </script>

<script>
 
$(document).ready(function() {
	
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
	
	$("#eff_frm_date").datepicker({
	        dateFormat: "dd-mm-yy",   
	        changeMonth: true,
	        changeYear: true
		}).attr('readonly', 'readonly');
	    
	    $("#eff_to_date").datepicker({
	        dateFormat: "dd-mm-yy", 
	        changeMonth: true,
	        changeYear: true
		}).attr('readonly', 'readonly');
	    
	    
	   
	    
});
 
function checkDate()
{
	  var startDate = document.getElementById("eff_frm_date").value;
	  var endDate = document.getElementById("eff_to_date").value;

	  var b = startDate.split(/\D/);
	  var c = endDate.split(/\D/);	  
	  
	  if ((Date.parse(c.reverse().join('-')) <= Date.parse(b.reverse().join('-'))))
	  {
	    alert("Effective To date should be greater than Effective From date");
	    document.getElementById("eff_to_date").value = "";
	  }	
}

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
</script> 
<script>
function newdocument() {
	var r = $('input:radio[name=answer]:checked').val();
	if(r == 'No'){
		$("div#suprcdd_we_pe_no_div").show();
	}
	else{		
		$("div#suprcdd_we_pe_no_div").hide(); 
		$("input#suprcdd_we_pe_no").val("");
	}
}
</script>
<script>
  function getDownloadImageWePeUpload(id)
  {  
  	 document.getElementById("id1").value=id;
  	 document.getElementById("getDownloadImageForm").submit();  	
  }  
</script> 
</body>
</html>