<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Upload WE/PE</title></head>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/excluded/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>  

<script type="text/javascript" src="js/miso/miso_js/jquery-1.12.3.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link rel="stylesheet" href="js/Calender/jquery-ui.css"/>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/cue/printAllPages.js" type="text/javascript"></script>
<script src="js/cue/update.js" type="text/javascript"></script>	
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<script>
function printDiv() 
  	{
	 	var printLbl = ["WE/PE Document :"];
	 	var printVal = [$('input:radio[name=we_pe]:checked').val()];
	 	printDivOptimize('divPrint','LINK UPLOAD WE/PE TO UPLOAD WET/PET/FET',printLbl,printVal,"select#status");
 	 }
</script>


<script>
      $(document).ready(function() {   
    	 
    	  if('${we_pe02}' != "")
    		{
    			$("input[name=we_pe][value="+'${we_pe02}'+"]").prop('checked', true);
    			getWepeNo('${we_pe02}');
    			$("#we_pe_no").val('${we_pe_no1}');
    			$("#table_title").val('${table_title1}');
    			$("div#divwatermark").val('').addClass('watermarked'); 
  				watermarkreport(); 
  				$("#divPrint").show();
  				$("div#divSerachInput").show();
  				document.getElementById("printId").disabled  = false;	
  		
  				 if('${list.size()}' == 0 ){
  		    		  $("div#divSerachInput").hide();
  		    			document.getElementById("printId").disabled  = true;
  		    		  $("table#Link_WE_PE_Report").append("<tr><td colspan='6' style='text-align :center;'>Data Not Available</td></tr>");
  		 		 }
    		}
    	  $('#wet_pet_no').keyup(function() {
  	        this.value = this.value.toUpperCase();
  	    });  
    	  
    	  $("#searchInput").on("keyup", function() {
    			var value = $(this).val().toLowerCase();
    			$("#Link_WE_PE_Report tbody tr").filter(function() { 
    			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    			});
    		});
    	  
      $('input[type=radio][name=we_pe]').change(function() {		
    		var val = $(this).val();
    		getWepeNo(val);    	
    	});      
      
      try{
    		
  		 if(window.location.href.includes("msg="))
  		{
  			var url = window.location.href.split("?msg=")[0];
  		var m=  window.location.href.split("?msg=")[1];
  		window.location = url;
  		
  		if(m.includes("Updated+Successfully")){
  			window.opener.getRefreshReport('link_we_wet_weap',m);
  			window.close('','_parent','');
  		}
  		} 	
  	   }
  	   catch (e) {
  	   	// TODO: handle exception
  	   }
      });
      
      function getarmvalue(val){    		
  		if(val != "" && val != null)
  		  {   
  			$.post("getsuperseededvalue?"+key+"="+value, {val : val}).done(function(j) {
  				 if(j != "" && j != null){		
  					 document.getElementById("table_title").value=j[0].table_title;
  				 }
  				 else{
  					 document.getElementById("table_title").value="";
  				 }
  			 }).fail(function(xhr, textStatus, errorThrown) { });  
  		  }
  		else
  			document.getElementById("table_title").value="";
  	}

      
      function getWepeNo(val)
      {
    	  if(val !="" || val != null)
    	  {
    		  var wepetext=$("#we_pe_no");
    		  wepetext.autocomplete({ 
    		      source: function( request, response ) {
    		        $.ajax({
    		        	type: 'POST',
    		            url: "getWePenumber?"+key+"="+value,
        		    data: {type : val ,we_pe_no : document.getElementById('we_pe_no').value},
    		          success: function( data ) {
    		            //response( data );
    		            //var dataCountry1 = data.join("|");
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
    		        	alert("Please Enter Approved WE/PE No");
    		        	wepetext.val("");
    		        	  
    		        	document.getElementById("table_title").value="";
    		        	wepetext.focus();
    		        	return false;	             
    		          }   	         
    		      }, 
    	       select: function( event, ui ) {
    	      	$(this).val(ui.item.value);    	        	
    	      	getarmvalue($(this).val());	        	
    	       } 	     
    		    });
    	  }
      }
      
 </script>
 
 <script>
 $(document).ready(function() {
    
	  $('input[type=radio][name=wet_pet]').change(function() {			 
  		var val = $(this).val();
  		getWetpetNo(val);
   	});
 });
 
 function getarmvalue2(val){
	 if(val != "" && val != null)
		  {
			
			 $.post("getsuperseededvalue2?"+key+"="+value, {val : val}).done(function(j) {
				 document.getElementById("table_title_WETPET").value=j[0].unit_type;
			 }).fail(function(xhr, textStatus, errorThrown) { }); 
		  }
	}
 
 function getWetpetNo(val)
 {
	  if(val !="" || val != null)
	  {
		  var wepetext=$("#wet_pet_no");
		
		  wepetext.autocomplete({
		      source: function( request, response ) {
		        $.ajax({ 
		        type: 'POST',
		  	    url: "getWetPetFetUploadNo?"+key+"="+value,
   		        data: {val:val,wet_pet_no: document.getElementById('wet_pet_no').value },
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
		        	alert("Please Enter Approved WET/PET No");
		        	wepetext.val("");
		        	  
		        	document.getElementById("table_title_WETPET").value="";
		        	wepetext.focus();
		        	return false;	             
		          }   	         
		      }, 
	       select: function( event, ui ) {
	      	$(this).val(ui.item.value);    	        	
	      	getarmvalue2($(this).val());	        	
	       } 	     
		    });
	  }
 }
 </script>
<body>
<form:form id="" name="" action="link_we_wetAction" method="post"  class="form-horizontal" commandName="link_we_wetActionCMD">
	<div class="animated fadeIn">
			<div class="container" align="center">
	    		<div class="card">
	          		<div class="card-header"><h5><b>LINK UPLOAD WE/PE TO UPLOAD WET/PET/FET </b><br><span style="font-size: 10px;font-size:15px;color:red">(To be entered by Line Dte/Etrc)</span></h5></h5></div>
	          			<div class="card-body card-block cue_text">
	            			<div class="col-md-11">
	            					<div class="row form-group">
						                	<div class="col col-md-3">
						                		<label for="text-input" class=" form-control-label">WE/PE Document <strong style="color: red;">*</strong></label>
						                	</div>
						                
											  <div class="col col-md-4">
							                              <div class="form-check-inline form-check">
							                                <label for="we_pe1" class="form-check-label ">
							                                  <input type="radio" id="we_pe1" name="we_pe" value="WE" maxlength="4" onchange="clearDiscription();" class="form-check-input" >WE
							                                </label>&nbsp;&nbsp;&nbsp;
							                                <label for="we_pe2" class="form-check-label ">
							                                  <input type="radio" id="we_pe2" name="we_pe" value="PE" maxlength="4" onchange="clearDiscription();" class="form-check-input">PE
							                                </label>&nbsp;&nbsp;&nbsp;
							                                <label for="we_pe3" class="form-check-label ">
							                                  <input type="radio" id="we_pe3" name="we_pe" value="FE" maxlength="4" onchange="clearDiscription();" class="form-check-input">FE
							                                </label>&nbsp;&nbsp;&nbsp;
							                               <label for="we_pe4" class="form-check-label ">
							                                  <input type="radio" id="we_pe4" name="we_pe" value="GSL" maxlength="4" onchange="clearDiscription();" class="form-check-input">GSL
							                               </label>
							                                </div>
							                                </div>
							                                </div>
		        				</div>
		        			<hr style="display:none">	
		        				
								<div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-6">
	                  						<label class=" form-control-label">WE/PE Document No <strong style="color: red;">*</strong></label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                  					     <input type="text" id="we_pe_no" name="we_pe_no" maxlength="100" class="form-control" autocomplete="off" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search">
	                					</div>
	              					</div>
	              			   </div>
							<div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-6">
	                  						<label class=" form-control-label">Table Title</label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                  					     <textarea id="table_title" name="table_title" rows="3" class="form-control" readonly="readonly"></textarea>
	                					</div>
	              					</div>
	              					</div>
	              					<div class="col-md-11">
	            					<div class="row form-group">
						                	<div class="col col-md-3">
						                		<label for="text-input" class=" form-control-label">WET/PET/FET Document <strong style="color: red;">*</strong></label>
						                	</div>
						                	<div class="col col-md-4">
					                              <div class="form-check-inline form-check">
					                                <label for="inline-radio1" class="form-check-label ">
					                                  <input type="radio" id="wet_pet1" name="wet_pet" value="WET" onchange="clearWETPET();" class="form-check-input">WET
					                               </label>&nbsp;&nbsp;&nbsp;
					                                <label for="inline-radio2" class="form-check-label ">
					                                  <input type="radio" id="wet_pet2" name="wet_pet" value="PET" onchange="clearWETPET();" class="form-check-input">PET
					                                </label>&nbsp;&nbsp;&nbsp;
					                                <label for="inline-radio3" class="form-check-label ">
					                                  <input type="radio" id="wet_pet3" name="wet_pet" value="FET" onchange="clearWETPET();" class="form-check-input">FET
					                                </label>&nbsp;&nbsp;&nbsp;
					                              </div>
							                    </div>
										</div>
	            				</div>
	            					<hr style="display:none">	
	              					
	              			 <div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-6">
	                  						<label class=" form-control-label">WET/PET/FET Document No <strong style="color: red;">*</strong> </label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                  					     <input type="text" id="wet_pet_no" name="wet_pet_no" maxlength="50" class="form-control" autocomplete="off" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search">
	                					</div>
	              					</div>
           					</div>
	              			<div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-6">
	                  						<label class=" form-control-label">Table Title</label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                  					     <textarea id="table_title_WETPET" name="table_title_WETPET" rows="3" class="form-control" readonly="readonly" ></textarea>
	                					</div>
	              					</div>
							   </div>
							   <div class="col-md-8">
	              					<div class="row form-group">
	                					<div class="col col-md-5">
	                  						<label class=" form-control-label">Should be visible to Unit or not? <strong style="color: red;">*</strong></label>
	                					</div>
	                					<div class="col col-md-3">
				                              	<div class="form-check-inline form-check">
				                                	<label for="inline-radio1" class="form-check-label ">
				                                  		<input type="radio" id="unit_visible1" name="unit_visible" maxlength="3" value="Yes" class="form-check-input">Yes
				                                	</label>&nbsp;&nbsp;&nbsp;
				                                	<label for="inline-radio2" class="form-check-label ">
				                                  		<input type="radio" id="unit_visible2" name="unit_visible" maxlength="3" value="No" class="form-check-input">No
				                                	</label>
				                              	</div>
				                            </div>
	              					</div>
							   </div>
		  					<div class="col-md-7">
		              			<div class="row form-group">	              			 	
	               					<div class="col col-md-5">
	                 					<label for="text-input" class=" form-control-label">Remarks</label>
	               					</div>
	               					<div class="col-12 col-md-5">
	                 					<textarea id="remarks" name="remarks" maxlength="255" class="form-control char-counter1"></textarea>
									</div>	 							
		  						</div>
		  						</div>
	              				</div>
	              		<div class="card-footer" align="center">
	              		<input type="reset" class="btn btn-success btn-sm" value="Clear" onclick="clearAll()"> 
						<input type="submit" class="btn btn-success btn-sm" value="LINK" onclick="return isLinkvalid();">   
	              		<i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm" onclick="Search();" value="VIEW">
	              		<i class="fa fa-print"></i><input type="button" id="printId" class="btn btn-primary btn-sm btn_report" value="Print" onclick="printDiv();" disabled>
		            </div>
		            </div>
	              </div>
	          </div>
	        </form:form>
	  
<div style="width: 20%; padding-left: 1%; display: none;" id="divSerachInput">
	<input id="searchInput" type="text" style="font-size:12px; font-family: 'FontAwesome',Arial;margin-bottom: 5px;" placeholder="&#xF002; Search Word"  size="35" class="form-control">
</div>
	       <div class="col-md-12" style="display: none;" id="divPrint">
	       <div id="divShow" style="display: block;">
			</div>
			
			 <div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
					<span id="ip"></span>
			<table id="Link_WE_PE_Report" class="table no-margin table-striped  table-hover  table-bordered report_print" >
				 <thead>
					<tr>
						<th class="tableheadSer">Ser No</th>
						<th >WE/PE Document</th>
						<th >WE/PE Document No</th>
					   <th >Table Title</th>
					   <th >WET/PET/FET Document No</th>
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
									<td >${item.wet_pet_no}</td>
									<td id="thAction1"   class='lastCol'>${item.id}</td>
								</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>	
	</div>
	
	<c:url value="link_we_wet1" var="searchUrl" />
    		<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="we_pe_no1">
    			<input type="hidden" name="we_pe02" id="we_pe02" value="0"/>
    			<input type="hidden" name="we_pe_no1" id="we_pe_no1" value="0"/>
    			<input type="hidden" name="wet_link_status1" id="wet_link_status1" value="0"/>
    			<input type="hidden" name="table_title1" id="table_title1" value="0"/>
    		</form:form> 
    		              		
	<c:url value="update_WE_linkwith_WET_Url" var="updateUrl" />
	<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid" target="result">
		<input type="hidden" name="updateid" id="updateid" value="0"/>
	</form:form> 
	         		
<script>

function Search(){
	var WE = $('input:radio[name=we_pe]:checked').val();
	 if(WE == null){
		 alert("Please select WE/PE Document")
		 return false;
	 }
    $("#we_pe02").val(WE);
	$("#we_pe_no1").val($("#we_pe_no").val());
    $("#table_title1").val($("#table_title").val());
    $("#wet_link_status1").val($("#wet_link_status").val());
    document.getElementById('searchForm').submit();
}
	
var newWin;
function editData(id){	
	var x = screen.width/2 - 1100/2;
    var y = screen.height/2 - 900/2;
   
	newWin = window.open("", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');
	 
	 window.onfocus = function () { 
		 //  newWin.close();
	 }

	document.getElementById('updateid').value=id;
	document.getElementById('updateForm').submit();
}
function closeWindow()
{
	newWin.close();   
}
function deleteData(id){
	$.post("delete_WE_linkwith_WET_UrlAdd?"+key+"="+value, {deleteid : id}).done(function(j) {
		alert(j);
		Search();
	  }).fail(function(xhr, textStatus, errorThrown) { }); 	
}	

</script>          		
<script>
function clearDiscription()
{
	document.getElementById("we_pe_no").value="";
	document.getElementById("table_title").value="";
	
	}

function clearWETPET()
{
	document.getElementById("wet_pet_no").value="";
	document.getElementById("table_title_WETPET").value="";
}
	

function clearAll()
{	
	document.getElementById('divPrint').style.display='none';
	document.getElementById("printId").disabled = true;
	$("div#divPrint").hide();
	 var tab = $("#Link_WE_PE_Report"); 
	  tab.empty(); 
	  $("#searchInput").val("");
	  $("#searchInput").hide();
	//document.location.reload();
	 	localStorage.clear();
		localStorage.Abandon();
} 

</script>
<script>


function isLinkvalid(){	
	 var WE = $('input:radio[name=we_pe]:checked').val();
	 if(WE == null){
		 alert("Please select WE/PE Document")
		 return false;
	 }
	 
	 if($("input#we_pe_no").val()==""){
			alert("Please Enter WE/PE No")
			$("input#we_pe_no").focus();
			return false;
		}
	 
	 var WET = $('input:radio[name=wet_pet]:checked').val();
	 if(WET == null){
		 alert("Please select WET/PET/FET Document")
		 return false;
	 }
	 if($("input#wet_pet_no").val()==""){
			alert("Please Enter WET/PET/FET No")
			$("input#wet_pet_no").focus();
			return false;
		}
	 var unit = $('input:radio[name=unit_visible]:checked').val();
	 if(unit == null){
		 alert("Please Select Visibility to Unit or not")
		 return false;
	 }
	return true;
}
</script>
</body>
</html>
