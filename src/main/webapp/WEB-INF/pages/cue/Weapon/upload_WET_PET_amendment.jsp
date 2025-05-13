<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %>
<%@ taglib prefix="dandelion" uri="http://github.com/dandelion" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>UPLOAD WET/PET/FET AMENDMENT</title>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/excluded/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 

<script src="js/JS_CSS/jquery.dataTables.min.js"></script>
<script src="js/JS_CSS/dataTables.bootstrap.min.js"></script>

<script src="js/miso/miso_js/jquery-1.12.3.js"></script>

<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>

<script src="js/cue/update.js" type="text/javascript"></script>
<script src="js/cue/printAllPages.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

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

<script>
$(document).ready(function () {	
	
	if('${wet_pet01}' != "")
	{
		$("input[name=wet_pet][value="+'${wet_pet01}'+"]").prop('checked', true);
		$("#wet_pet_no").val('${wet_pet_no01}');
		getArmByWePeNo('${wet_pet_no01}');
		$("th#thLetterId").show();
		$("th#thRemarkId").show();
		$("td#thLetterId1").show();
		$("td#thRemarkId1").show();
		$("#divPrint").show();
		$("div#divwatermark").val('').addClass('watermarked'); 
		watermarkreport();
		$("div#divSerachInput").show(); 
		document.getElementById("printId").disabled = false;
		
		if('${list.size()}' == 0 ){
			$("div#divSerachInput").hide();  
			document.getElementById("printId").disabled = true;
			$("table#AttributeReportSearchWetPetAmendment").append("<tr><td colspan='7' style='text-align :center;'>Data Not Available</td></tr>");
		 }
	}
	
	$("#searchInput").on("keyup", function() {
			var value = $(this).val().toLowerCase();
			$("#AttributeReportSearchWetPetAmendment tbody tr").filter(function() { 
			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
			});
		});
	
	 $('#remark').keyup(function() {
	        this.value = this.value.toUpperCase();
	    });  
	 
	 $('#wet_pet_no').keyup(function() {
	        this.value = this.value.toUpperCase();
	    });
		
	
	  var r =  $('input:radio[name=wet_pet]:checked').val();	
	   	if(r != undefined)
	    	getWePeNoByType(r);	
		
		$('input[type=radio][name=wet_pet]').change(function() {		
			var radio_doc = $(this).val();
			getWePeNoByType(radio_doc);
		});		
	  
	     
	   try{
			if(window.location.href.includes("msg="))
					{
						var url = window.location.href.split("?msg=")[0];
					var m=  window.location.href.split("?msg=")[1];
					window.location = url;
					
					if(m.includes("Updated+Successfully")|| m.includes("File+size+should+be+less+then+2+MB")){
						window.opener.getRefreshReport('wet_pet_amndmt_weap',m);
						window.close('','_parent','');
					}
					}  	
		}
		catch (e) {
			// TODO: handle exception
		}  
});

</script>


<script>
function printDiv() 
{
	var printLbl = ["Type :", "WET/PET No :", "Table Title :","Document Type :","Effective From :","Effective To :"];
	var printVal = [$('input:radio[name=wet_pet]:checked').val(),document.getElementById('wet_pet_no').value,document.getElementById('arm_code').value,document.getElementById('doc_type').value,document.getElementById('date_upload').value,document.getElementById('date_upload2').value];
	printDivOptimize('divPrint','UPLOAD WET/PET/FET AMENDMENT',printLbl,printVal,"select#status");
}
</script>
 <script>
function getWePeNoByType(radio_doc)
{
	if(radio_doc != ""){         
	 var wepetext=$("#wet_pet_no");
	
	  wepetext.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
        	type: 'POST',
		  	url: "getWetPetNumberforamed?"+key+"="+value,
	        data: {we_r : radio_doc,wet_pet_no:document.getElementById('wet_pet_no').value},
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
	      change: function(event, ui) {
	    	 if (ui.item) {   	        	  
	        	  return true;    	            
	          } else {
	        	alert("Please Enter Approved WET/PET/FET Document No");
	        	wepetext.val("");	
	        	wepetext.focus();
	        	return false;	             
	          }   	         
	      }, 
    select: function( event, ui ) {
     	$(this).val(ui.item.value);    	        	
     	getArmByWePeNo($(this).val());	        	
      }      
	    });
	 }
	 else
		 alert("Please select Document");
}   
     
</script> 
 
<body>
 <form:form  id="upload_wet_pet_amendment_to_documentAction1" name="upload_wet_pet_amendment_to_documentAction1" action="upload_wet_pet_amendment_to_documentAction1?${_csrf.parameterName}=${_csrf.token}" method="post"  class="form-horizontal" commandName="upload_wet_pet_amendment_to_documentCMD" enctype="multipart/form-data"> 
	<div class="animated fadeIn">
			<div class="container" align="center">
	    		<div class="card">
	    			<div class="card-header"><h5><b>UPLOAD WET/PET/FET AMENDMENT</b><br><span style="font-size: 10px;font-size:15px;color:red">(To be entered by etrc)</span></h5></div>
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
		                                  <input type="radio" id="wet_pet1" name="wet_pet" value="WET" class="form-check-input" onchange="clearWEPE();">WET
		                                </label>&nbsp;&nbsp;&nbsp;
		                                <label for="inline-radio2" class="form-check-label ">
		                                  <input type="radio" id="wet_pet2" name="wet_pet" value="PET" class="form-check-input" onchange="clearWEPE();">PET
		                                </label>&nbsp;&nbsp;&nbsp;
		                                <label for="inline-radio2" class="form-check-label ">
		                                  <input type="radio" id="wet_pet3" name="wet_pet" value="FET" class="form-check-input" onchange="clearWEPE();">FET
		                                </label>&nbsp;&nbsp;&nbsp;       
		                             </div>
					              </div>							              
               				</div>
               			</div>
								<div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-6">
	                  						<label class=" form-control-label">WET/PET No<strong style="color: red;">*</strong></label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                  					     <input type="text" id="wet_pet_no" name="wet_pet_no" maxlength="50" class="form-control"  autocomplete="off" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search">
	                					</div>
	              					</div>
							   </div>
						</div>
					    <div class="col-md-12"> 
						      <div class="col-md-6">
              					<div class="row form-group">
                					<div class="col col-md-6">
                  						<label class=" form-control-label">WET/PET Title</label>
                					</div>
                					<div class="col-12 col-md-6">
                  						<textarea id="arm_code" name="" class="form-control" disabled></textarea>
                					</div>
              					</div>
							</div>
							<div class="col-md-6">
              					<div class="row form-group">
                					<div class="col col-md-6">
                  						<label for="text-input" class=" form-control-label">Document Type</label>
                					</div>
                					<div class="col-12 col-md-6">
                  							<input type="text" id="doc_type" name="" class="form-control" disabled>
									</div>
  								</div>
							</div> 	 
	   					</div>  
	   		          <div class="col-md-12"> 		
	                     <div class="col-md-6">
              					<div class="row form-group">
                					<div class="col col-md-6">
                  						<label for="text-input" class=" form-control-label">Effective From</label>
                					</div>
                					<div class="col-12 col-md-6">
                  						<input id="date_upload" name="" class="form-control" disabled>
									</div>
								</div>
						</div>
						<div class="col-md-6">
             					<div class="row form-group">
               					<div class="col col-md-6">
                 						<label for="text-input" class=" form-control-label">Effective To</label>
               					</div>
               					<div class="col-12 col-md-6">
                 						<input id="date_upload2" name="" class="form-control" disabled>
								</div>
 								</div>
						</div> 		
					</div>	
					
					 <div class="col-md-12"><span class="line"></span></div>
				    <div class="col-md-12"> 
      				   <div class="col-md-6">
      					   <div class="row form-group">
        					<div class="col col-md-6" style="padding-right:0;">
          						<label class=" form-control-label">Amendment Document <strong style="color: red;">*</strong></label>
        					</div>
        					<div class="col-12 col-md-6">
          						<input type="file" id="doc_path1" name="doc_path1" class="form-control">
        					</div>
        					</div>
      				  </div> 	
					 <div class="col-md-6">
          					<div class="row form-group">
            					<div class="col col-md-6">
              						<label class=" form-control-label">Remarks </label>
            					</div>
            					<div class="col-12 col-md-6">
              						<textarea id="remark" name="remark" maxlength="255" class="form-control char-counter1"></textarea>
            					</div>
            				</div>
	              	</div>  
	             </div>  
	             
	              <div class="col-md-12"> 
      				   <div class="col-md-6">
      					   <div class="row form-group">
        					<div class="col col-md-6" style="padding-right:0;">
          						<label class=" form-control-label">Letter No </label>
        					</div>
        					<div class="col-12 col-md-6">
          						<input type="text" id="letter_no" name="letter_no" maxlength="50" class="form-control" autocomplete="off">
        					</div>
        					</div>
      				  </div> 	
	             </div>   	 		
		 </div> 		
		            <div class="card-footer" align="center">
						<input type="reset" class="btn btn-success btn-sm" value="Clear" onclick="clearAll();">   
	              		<input type="submit" class="btn btn-primary btn-sm" value="Save" onclick="return isCueAmdtWetPetValid();">
	              		<i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm" onclick="Search();" value="Search">
	              		<i class="fa fa-print"></i><input type="button" id="printId" class="btn btn-primary btn-sm btn_report" value="Print" onclick="printDiv();" disabled>
		            </div>  
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
	<div  class="watermarked" data-watermark="" id="divwatermark" >
					<span id="ip"></span>
						
							<table id="AttributeReportSearchWetPetAmendment"  class="table no-margin table-striped  table-hover  table-bordered report_print" >
								 <thead >
									<tr>
									  	<th class="tableheadSer">Ser No</th>
										<th >WET/PET No</th>	
										<th >Amendment Letter No</th>									
										<th >Remark</th>
										<th id="thLetterId" style="display: none;">Letter No</th>
										<th id="thRemarkId" style="display: none;">Error Correction</th>
										<th class='lastCol'>Action</th>
									</tr>
								</thead> 
								<tbody>
								<c:forEach var="item" items="${list}" varStatus="num" >
								<tr>
									<td class="tableheadSer">${num.index+1}</td>
									<td >${item.wet_pet_no}</td>
									<td >${item.letter_no}</td>
									<td >${item.remark}</td>
									<td id="thLetterId1" style="display: none;">${item.reject_letter_no}</td>
									<td id="thRemarkId1" style="display: none;">${item.reject_remarks}</td>
									<td id="thAction1"  class='lastCol'>${item.id}</td>
								</tr>
								</c:forEach>
								</tbody>													
							</table>
							</div>
						</div>

	 <c:url value="update_WET_PET_amdt_Url" var="updateUrl" />
	<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid"  target="result"> 
		<input type="hidden" name="updateid" id="updateid" value="0"/>
	</form:form>
	
	 <c:url value="upload_WET_PET_Amendment1" var="searchUrl" />
    			<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="parent_code1">
    			<input type="hidden" name="wet_pet01" id="wet_pet01" value="0"/>
    			<input type="hidden" name="wet_pet_no1" id="wet_pet_no1" value="0"/>
    			<input type="hidden" name="arm_code1" id="arm_code1" value="0"/>
    			<input type="hidden" name="doc_type1" id="doc_type1" value="0"/>
    			<input type="hidden" name="date_upload1" id="date_upload1" value="0"/>
    			<input type="hidden" name="date_upload3" id="date_upload3" value="0"/>
    			<input type="hidden" name="remark1" id="remark1" value="0"/>
    			<input type="hidden" name="letter_no1" id="letter_no1" value="0"/>
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
	
	 $.post("delete_WET_PET_amdt_UrlAdd?"+key+"="+value, {deleteid : id}).done(function(j) {
			alert(j);
			Search();
	  }).fail(function(xhr, textStatus, errorThrown) { }); 	
}


</script>


<script>
function clearAll()
{	
	document.getElementById('divPrint').style.display='none';
	document.getElementById("printId").disabled = true;
	var tab = $("#AttributeReportSearchWetPetAmendment");
	tab.empty();
	$("#searchInput").val("");
	$("#searchInput").hide();
	$("div#divSerachInput").hide(); 
	//document.location.reload();
 	localStorage.clear();
	localStorage.Abandon();
}

function isCueAmdtWetPetValid(){	   
	  var r =  $('input:radio[name=wet_pet]:checked').val();	
	  if(r == undefined)
	  {		 
	  	    alert("Please select Document");
	  	    $('input:radio[name=wet_pet]:checked').focus();
	  		return false;
	  }

		if ($("input#wet_pet_no").val() == "") {
			alert("Please Enter WET/PET No")
			$("input#wet_pet_no").focus();
			return false;
		}
	  
	  if ($("input#doc_path1").val() == "") {
			alert("Please select Amendment Document")
			$("input#doc_path1").focus();
			return false;
		}
		return true;
}

</script>
<script>

function clearWEPE()
{
	document.getElementById('wet_pet_no').value = "";	
	document.getElementById('arm_code').value = "";
	document.getElementById('doc_type').value = "";
	document.getElementById('date_upload').value = "";
	document.getElementById('date_upload2').value = "";
}
</script>

<script>
function getArmByWePeNo(val)
{
	  if(val != "" && val != null)
	  {
		
 	     $.post("getDetailsByWetPetCondiNo?"+key+"="+value, {val : val}).done(function(j) {
 	    	if(j!="" && j!= null){
 				 document.getElementById("arm_code").value=j[0].unit_type;	
 				
 				document.getElementById("doc_type").value=j[0].doc_type;
 				document.getElementById("date_upload").value=j[0].valid_from;	
 				document.getElementById("date_upload2").value=j[0].valid_till;
 				}
 				else
 				{
 					 document.getElementById("arm_code").value="";	
 					  document.getElementById("doc_type").value="";
 					  document.getElementById("date_upload").value="";	
 					 document.getElementById("date_upload2").value="";
 				}
 	      }).fail(function(xhr, textStatus, errorThrown) { }); 	
		 
	  }
	  else
	  {
		document.getElementById("arm_code").value="";	
		  document.getElementById("doc_type").value="";
		  document.getElementById("date_upload").value="";	
		 document.getElementById("date_upload2").value="";
	  }
}



function Search(){
	
	 var r =  $('input:radio[name=wet_pet]:checked').val();	
	  if(r == undefined)
	  {		 
	  	    alert("Please select Document");
	  	    $('input:radio[name=wet_pet]:checked').focus();
	  		return false;
	  }

		if ($("input#wet_pet_no").val() == "") {
			alert("Please Enter WET/PET No")
			$("input#wet_pet_no").focus();
			return false;
		}
	$("#wet_pet01").val(r);
	$("#wet_pet_no1").val($("#wet_pet_no").val());
    $("#arm_code1").val($("#arm_code").val());
    $("#doc_type1").val($("#doc_type").val());
	$("#date_upload1").val($("#date_upload").val());
    $("#date_upload3").val($("#date_upload2").val());
    $("#remark1").val($("#remark").val());
    $("#letter_no1").val($("#letter_no").val());
    $("#status1").val($("#status").val());
    document.getElementById('searchForm').submit();
	}
</script> 

</body>
</html>
     
