<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/excluded/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 

<script type="text/javascript" src="js/miso/miso_js/jquery-1.12.3.js"></script>

<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link rel="stylesheet" href="js/Calender/jquery-ui.css"/>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>

<script src="js/cue/update.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>
<script src="js/cue/printAllPages.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

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
     $.post("deleteWetPetSuperUrlAdd?"+key+"="+value, {deleteid : id}).done(function(j) {
    	alert(j);
		Search();
      }).fail(function(xhr, textStatus, errorThrown) { }); 	
}

function printDiv() 
{
	var printLbl = ["Type :"];
	var printVal = [$('input:radio[name=wet_pet]:checked').val()];
	printDivOptimize('divPrint','CAPTURE WET/PET TITLE',printLbl,printVal,"select#status");
}
</script>


<form:form name="upload_wet_pet_fetSuper" id="upload_wet_pet_fetSuper" action="upload_wet_pet_fetSuperAct" method='POST' commandName="upload_wet_pet_fetSuperCmd" >
  	<div class="container" align="center">
        	<div class="card">
          		<div class="card-header"> <h5><b>CAPTURE WET/PET TITLE</b><br><span style="font-size: 10px;font-size:15px;color:red">(To be entered by Line Dte)</span></h5></div>    
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
		                                  <input type="radio" id="wet1" name="wet_pet" value="WET" maxlength="4" onchange="clearDiscription();" class="form-check-input">WET
		                                </label>&nbsp;&nbsp;&nbsp;
		                                <label for="inline-radio2" class="form-check-label ">
		                                  <input type="radio" id="pet1" name="wet_pet" value="PET" maxlength="4" onchange="clearDiscription();" class="form-check-input">PET
		                                </label>&nbsp;&nbsp;&nbsp;
		                                <label for="inline-radio3" class="form-check-label ">
		                                <input type="radio" id="fet1" name="wet_pet" value="FET" maxlength="4" onchange="clearDiscription();" class="form-check-input">FET
		                                </label>&nbsp;&nbsp;&nbsp;       
		                             </div>
					              </div>							              
               				</div>
               			</div> 
             				<div class="row form-group row_content">
               					<div class="col col-md-6">
                 					<label class=" form-control-label">Approved Uploaded WET/PET <strong style="color: red;">*</strong></label>
               					</div>
               					<div class="col-12 col-md-6">
                 					<input name="uploaded_wetpet" id="uploaded_wetpet" maxlength="50" class="form-control" autocomplete="off" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search" >
               					</div>
               				</div>
             			</div>
             	   <div class="col-md-12" style="display: block;">
             	   <div class="col-md-6">
             				<div class="row form-group">
               					<div class="col col-md-6">
                 					<label class=" form-control-label">MISO WET/PET No <strong style="color: red;">*</strong></label>
               					</div>
               					<div class="col-12 col-md-6">
                 					<input  id="wet_pet_no" name="wet_pet_no" maxlength="50" class="form-control" autocomplete="off" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search" >
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
			                <div class="col-12 col-md-6">    
			                   <div class="form-check-inline form-check">                        
				               <label for="inline-radio1" class="form-check-label ">
				                	<input type="radio" id="answer1" name="answer"  value="Yes" class="form-check-input" onclick="newdocument();" >Yes
				                </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				                <label for="inline-radio1" class="form-check-label ">
				                     <input type="radio" id="answer2" name="answer" value="No" class="form-check-input" onclick="newdocument();">No
				                 </label>				            
				              </div>
				           </div>
				          </div>
		              </div>
		              <div class="col-md-6" id="suprcdd_wet_pet_no_div" style="display:none">
              			 	<div class="row form-group"> 
               					<div class="col col-md-6">
                 					<label for="text-input" class=" form-control-label">Superseded Document No<strong style="color: red;">*</strong></label>
               					</div>
               					<div class="col-12 col-md-6">
                 					<input  id="superseeded_wet_pet" name="superseeded_wet_pet" maxlength="50" class="form-control" autocomplete="off" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search">
								</div>
 							</div> 
  						</div>
  					</div>
  					<div class="col-md-12">
 						<div class="col-md-6">
          					<div class="row form-group">
            					<div class="col col-md-6">
              						<label class=" form-control-label">WET/PET Title <strong style="color: red;">*</strong></label>
            					</div>
            					<div class="col-12 col-md-6">
              						<input  id="unit_type" name="unit_type" maxlength="100" class="form-control">
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
<!-- 				                	<option value="0">--Select--</option> -->
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
<!-- 				                	<select id="sponsor_dire" name="sponsor_dire" class="form-control">  -->
<!-- 					                	<option value="0">--Select--</option> -->
<%-- 	              						<c:forEach var="item" items="${getsponserListCue}" varStatus="num" > --%>
<%-- 	       									<option value="${item[1]}" > ${item[1]}</option> --%>
<%-- 	       								</c:forEach> --%>
<!-- 	                                </select> -->
										<select  class="form-control" id ="sponsor_dire" name ="sponsor_dire">	
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
	                 					<label for="text-input" class=" form-control-label">Remarks</label>
	               					</div>
	               					<div class="col-12 col-md-6">
	                 					<textarea id="remarks" name="remarks" maxlength="255" class="form-control char-counter1"></textarea>
									</div>	 							
		  						</div>
		  						</div>	
  				  </div>  
  		</div>
  				<div class="card-footer" align="center">
					<input type="reset" class="btn btn-success btn-sm" value="Clear" onclick="clearAll();">   
             			<input onclick="return isValid();" type="submit" class="btn btn-primary btn-sm" value="Save" >
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
								<table id="AttributeReportWetSuper" class="table no-margin table-striped  table-hover  table-bordered  report_print">
									 <thead>
										<tr>
											<th class="tableheadSer">Ser No</th>
											<th >Type of Document</th>
											<th >MISO WET/PET No</th>
											<th >Approved Uploaded WET/PET</th>
											<th >Superseded WET/PET</th>
											<th >WET/PET Title</th>
											<th >Effective From</th>
											<th >Effective To</th>
											<th >Security Classification</th>
											<th >Arm/Service</th>
											<th >Sponsor Dte</th>
											<th id="thLetterId" >Letter No</th>
											<th id="thRemarkId" >Error Correction</th>
											<th  class='lastCol'>Action</th>
										</tr>
									</thead>
									<tbody >
									
									<c:forEach var="item" items="${list}" varStatus="num" >
								<tr>
									<td class="tableheadSer">${num.index+1}</td>
									<td >${item.wet_pet}</td>
									<td >${item.wet_pet_no}</td>
									<td >${item.uploaded_wetpet}</td>
									<td >${item.superseeded_wet_pet}</tdh>
									<td >${item.unit_type}</td>
									<td >${item.valid_from}</td>
									<td >${item.valid_till}</td>
									<td >${item.doc_type}</td>
									<td >${item.arm_desc}</td>
									<td >${item.line_dte_name}</td>
									<td id="thLetterId1" >${item.reject_letter_no}</td>
									<td id="thRemarkId1" >${item.reject_remarks}</td>
									<td id="thAction1" class='lastCol'>${item.id}</td>
								</tr>
				</c:forEach>
									</tbody>
								</table>
								</div>
							</div>
              
<c:url value="upload_WET_PETSuper1" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="wet_pet_no1">
		<input type="hidden" name="wet_pet_no1" id="wet_pet_no1" value="0"/>
		<input type="hidden" name="unit_type1" id="unit_type1" value="0"/>
		<input type="hidden" name="sponsor_dire1" id="sponsor_dire1" value="0"/>
		<input type="hidden" name="wet_pet1" id="wet_pet1" value="0"/>
		<input type="hidden" name="status1" id="status1" value="0"/>
		<input type="hidden" name="uploaded_wetpet1" id="uploaded_wetpet1" value="0"/>
		<input type="hidden" name="arm1" id="arm1" value="0"/>
	</form:form>  
              
<c:url value="updateWetPetSuperUrl" var="updateUrl" />
	<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid" target="result">
		<input type="hidden" name="updateid" id="updateid" value="0"/>
	</form:form>
		
<script>

function Search(){
	 var r =  $('input:radio[name=wet_pet]:checked').val();	
	  if(r == undefined)
	  {		 
		    alert("Please select Document");
		    $('input:radio[name=wet_pet]:checked').focus();
			return false;
	  }  	
    $("#wet_pet1").val(r);
	$("#wet_pet_no1").val($("#wet_pet_no").val());
    $("#unit_type1").val($("#unit_type").val());
    $("#sponsor_dire1").val($("#sponsor_dire").val());
    $("#status1").val($("#status").val());
    $("#uploaded_wetpet1").val($("#uploaded_wetpet").val());
    $("#arm1").val($("#arm").val());
    document.getElementById('searchForm').submit();
 	   
	}
function clearDiscription()
{
	document.getElementById("uploaded_wetpet").value="";
	//document.getElementById("table_title1").value="";	
	}
  
$(document).ready(function() {
	
	if('${wet_pet1}' != "")
	{
		$("#sponsor_dire").val('${sponsor_dire1}');
		$("#arm").val('${arm1}');
		$("input[name=wet_pet][value="+'${wet_pet1}'+"]").prop('checked', true);
		$("#uploaded_wetpet").val('${uploaded_wetpet1}');
		$("#wet_pet_no").val('${wet_pet_no1}');
		
		$("div#divwatermark").val('').addClass('watermarked'); 
		watermarkreport(); 
		$("#divPrint").show();
		$("div#divSerachInput").show(); 
		document.getElementById("printId").disabled  = false;
		
		if('${list.size()}' == 0 ){
			$("div#divSerachInput").hide(); 
			document.getElementById("printId").disabled  = true;
			$("table#AttributeReportWetSuper").append("<tr><td colspan='5' style='text-align :center;'>Data Not Available</td></tr>");
		 }

	}
	 
	$("#searchInput").on("keyup", function() {
			var value = $(this).val().toLowerCase();
			$("#AttributeReportWetSuper tbody tr").filter(function() { 
			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
			});
		});
	
	   $('#unit_type').keyup(function() {
        this.value = this.value.toUpperCase();
    });
	   
	   $('#wet_pet_no').keyup(function() {
	        this.value = this.value.toUpperCase();
	    });
	   
	   $('#remarks').keyup(function() {
	        this.value = this.value.toUpperCase();
	    });
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
    
    $.ajaxSetup({
        async: false
    });
    var r =  $('input:radio[name=wet_pet]:checked').val();	
   	if(r != undefined)
    	getWePeNoByType(r);	
	
	$('input[type=radio][name=wet_pet]').change(function() {		
		var radio_doc = $(this).val();		
		getWePeNoByType(radio_doc);		
	});
	
	$.ajaxSetup({
	    async: false
	});

	try{
		  if(window.location.href.includes("msg="))
			{
				var url = window.location.href.split("?msg=")[0];
			var m=  window.location.href.split("?msg=")[1];
			window.location = url;
			
			if(m.includes("Updated+Successfully")){
				window.opener.getRefreshReport('upl_wet_pet_sup_weap',m);
				window.close('','_parent','');
			}
			}
	}
	catch (e) {
		// TODO: handle exception
	}
  
});	

function getWePeNoByType(radio_doc)
{
	 if(radio_doc != ""){		 
		 $("#superseeded_wet_pet").val("");
		 document.getElementById("unit_type").value="";
		 document.getElementById("valid_from").value="";
  		  document.getElementById("valid_till").value="";
  		  $("select#doc_type").val("Restricted");
//   		  $("select#sponsor_dire").val("0");
//    		  $("select#arm").val("0");		 
	 
  		  
  		  
  		var wepetext11=$("#wet_pet_no");
  	
  		
		  wepetext11.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        type: 'POST',
		  	    url: "getWetPetFetNoAll?"+key+"="+value,
   		        data: {val:radio_doc,wet_pet_no: document.getElementById('wet_pet_no').value},
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

		    });	 
		  $.ajaxSetup({
			    async: false
			});
	
  		  
  		  
	 var wepetext=$("#uploaded_wetpet");
	
	  wepetext.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
        	type: 'POST',
	  	    url: "getWetPetFetNoOnlySuperPage?"+key+"="+value,
	        data: {val : radio_doc,wet_pet_no:document.getElementById('uploaded_wetpet').value},
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
	        	alert("Please Enter Approved WET/PET/FET No");
	        	wepetext.val("");	
	        	  
	        	 document.getElementById("valid_from").value="";
	   		  document.getElementById("valid_till").value="";
	   		  $("select#doc_type").val("Restricted");
	   		  $("select#sponsor_dire").val("0");
	   		  $("select#arm").val("0");
	        	  
	        	wepetext.focus();
	        	return false;	             
	          }   	         
	      }, 
       select: function( event, ui ) {
      	$(this).val(ui.item.value);    	        	
      	getDetailsBySupeerseedNo($(this).val());	        	
       } 	     
	    });	  
	  
	  $.ajaxSetup({
		    async: false
		});
	  
	  var wepetext1=$("#superseeded_wet_pet");
	
	  wepetext1.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
        	type: 'POST',
		  	url: "getWetPetsupercddList?"+key+"="+value,
	        data: {we_r : radio_doc,wet_pet_no: document.getElementById('superseeded_wet_pet').value},
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
	        	  return false;	             
	          }   	         
	      },   
	    });
	 }
	 else
		 alert("Please select Document");
	
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
	
function getDetailsBySupeerseedNo(val)
{
	  if(val != "" && val != null)
	  { 
		
	     $.post("getDetailsBySuperseedNo?"+key+"="+value, {val : val}).done(function(j) {
	    	if(j!="" && j!= null){
				document.getElementById("valid_from").value=j[0][0];
				document.getElementById("valid_till").value=j[0][1];
				$("select#doc_type").val(j[0][2]);
 				$("select#sponsor_dire").val(j[0][3]);
				$("select#arm").val(j[0][4]);
			}
			else
			{
				 document.getElementById("valid_from").value="";
				  document.getElementById("valid_till").value="";
				  $("select#doc_type").val("Restricted");
				  $("select#sponsor_dire").val("0");
				  $("select#arm").val("0");
			}
	      }).fail(function(xhr, textStatus, errorThrown) { }); 	
		 
	  }
	  else
	  {
		  alert("Please enter Approved Uploaded Document No");
		  document.getElementById("valid_from").value="";
		  document.getElementById("valid_till").value="";
		  $("select#doc_type").val("Restricted");
		  $("select#sponsor_dire").val("0");
		  $("select#arm").val("0");
	  }
}
  function newdocument() {
		var r = $('input:radio[name=answer]:checked').val();
		if(r == 'No')
		{
			$("div#suprcdd_wet_pet_no_div").show();
		}
		else
		{
			$("div#suprcdd_wet_pet_no_div").hide(); 
			 $("input#superseeded_wet_pet").val("");
		}
	}
  
  function isValid()
  {  	  
  	  var r =  $('input:radio[name=wet_pet]:checked').val();	
	  if(r == undefined)
	  {		 
		    alert("Please select Document");
		    $('input:radio[name=wet_pet]:checked').focus();
			return false;
	  }  	  
  	
  	  if($("input#uploaded_wetpet").val() == "")
	  {
		alert("Please enter Approved Uploaded Document No");
		$("input#uploaded_wetpet").focus();
		return false;
	  }
  	  
	  	if($("input#wet_pet_no").val() == "")
		{
			alert("Please enter WET/PET/FET Document No");
			$("input#wet_pet_no").focus();
			return false;
		}
	  	 
	  	var r1 = $('input:radio[name=answer]:checked').val();
	  	 if(r1 == undefined)
		  {		 
			    alert("Please select New Document");
			    $('input:radio[name=answer]:checked').focus();
				return false;
		  } 
	  	 else if (r1 == "No") {
			if ($("input#superseeded_wet_pet").val() == ""){
				alert("Please Select Superseded Document No");
				$("input#superseeded_wet_pet").focus();
				return false;
			}
		}
	  	
		if($("input#unit_type").val() == "")
		{
			alert("Please enter Document Title");
			$("input#unit_type").focus();
			return false;
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
		if($("select#doc_type").val() == "")
		{
			alert("Please select Document Type");
			$("select#doc_type").focus();
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
		return true;
  }
  
  function clearAll()
  {document.getElementById('divPrint').style.display='none';
	  document.getElementById("printId").disabled = true;
  	var tab = $("#AttributeReportWetSuper");
   	tab.empty(); 
   	$("#searchInput").val("");
   	$("#searchInput").hide();
  //document.location.reload();
 	localStorage.clear();
	localStorage.Abandon();
  }
  
</script>
