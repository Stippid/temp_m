<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/excluded/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>  

<script type="text/javascript" src="js/miso/miso_js/jquery-1.12.3.js"></script>

<script src="js/JS_CSS/jquery.dataTables.min.js"></script>
<script src="js/JS_CSS/dataTables.bootstrap.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/cue/printAllPages.js" type="text/javascript"></script>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/cue/update.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<script>
function printDiv() 
  	{
	 	var printLbl = ["WE/PE Type :", "WE/PE/FE/GSL Document No:", "Table Title :"];
	 	var printVal = [$('input:radio[name=we_pe1]:checked').val(),document.getElementById('we_pe_no').value,document.getElementById('table_title').value];
	 	printDivOptimize('divPrint','LINK WE/PE TO WET/PET/FET',printLbl,printVal,"select#status");
 	 }
</script>

<form:form name="link_wet_we" id="link_wet_we" action="link_wet_weAction" method="post" class="form-horizontal" commandName="link_wet_weCMD"> 
	<div class="animated fadeIn">
		 	<div class="container" align="center">
	    		<div class="card">
	          		<div class="card-header" ><h5>LINK WE/PE TO WET/PET/FET</h5> </div>
	          			<div class="card-body card-block cue_text">
	          			   <div class="col-md-12">
								<div class="col-md-7">
		              					<div class="row form-group">
								             <div class="col col-md-6">
								                <label for="text-input" class=" form-control-label">WE/PE Document <strong style="color: red;">*</strong></label>
								             </div>
											 <div class="col col-md-6">
							                        <div class="form-check-inline form-check">
							                              <label for="inline-radio1" class="form-check-label ">
							                                  <input type="radio" id="we_pe12" name="we_pe1" value="WE"  maxlength="4"  onchange="clearDiscription();" class="form-check-input">WE
							                               </label>&nbsp;&nbsp;&nbsp;
							                               <label for="inline-radio2" class="form-check-label ">
							                                  <input type="radio" id="we_pe2" name="we_pe1" value="PE"  maxlength="4"  onchange="clearDiscription();" class="form-check-input">PE
							                               </label>&nbsp;&nbsp;&nbsp;
							                               <label for="inline-radio3" class="form-check-label ">
							                                  <input type="radio" id="we_pe3" name="we_pe1" value="FE"  maxlength="4"  onchange="clearDiscription();" class="form-check-input">FE
							                               </label>&nbsp;&nbsp;&nbsp;
							                               <label for="inline-radio4" class="form-check-label ">
							                                  <input type="radio" id="we_pe4" name="we_pe1" value="GSL" maxlength="4" onchange="clearDiscription();" class="form-check-input">GSL
							                               </label>
							                         </div>
								               </div>
										 </div>
									</div>
								</div>
								<div class="col-md-12">
								  <div class="col-md-7">
								 	 <div class="row form-group">
	                                     <div class="col col-md-6">
	                                          <label class=" form-control-label">WE/PE Document No <strong style="color: red;">*</strong></label>
	                                    </div>
	                                    <div class="col-12 col-md-5">
		                  					  <input type="text" id="we_pe_no" name="we_pe_no" maxlength="100" class="form-control autocomplete" autocomplete="off" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search">
		                				</div>
	                                </div>
								</div>
								<div class="col-md-5">
								    <div class="row form-group">
	                                    <div class="col col-md-5">
	                                         <label class=" form-control-label">Table Title </label>
	                                     </div>
	                                     <div class="col-12 col-md-7">
		                  					<textarea id="table_title" name="table_title" maxlength="150" rows="3" placeholder="" class="form-control" readonly="readonly"></textarea>
		                				</div>
	                                </div>
								</div>
	            		   </div>
	            		  <div class="col-md-12">
		            		  <div class="col-md-7">
		            		        <div class="row form-group">
							             <div class="col col-md-6">
							                	<label for="text-input" class=" form-control-label">WET/PET/FET Document <strong style="color: red;">*</strong></label>
							             </div>
										 <div class="col col-md-6">
						                        <div class="form-check-inline form-check">
						                              <label for="inline-radio5" class="form-check-label ">
						                                  <input type="radio" id="wet" name="wet_pet1" value="WET" maxlength="4" onchange="clearWETPET();" class="form-check-input">WET
						                               </label>&nbsp;&nbsp;&nbsp;
						                               <label for="inline-radio6" class="form-check-label ">
						                                  <input type="radio" id="pet" name="wet_pet1" value="PET" maxlength="4"  onchange="clearWETPET();" class="form-check-input">PET
						                               </label>&nbsp;&nbsp;&nbsp;
						                               <label for="inline-radio7" class="form-check-label ">
						                                  <input type="radio" id="fet" name="wet_pet1" value="FET" maxlength="4"  onchange="clearWETPET();" class="form-check-input">FET
						                               </label>
						                         </div>
						                 </div>
									</div>
								</div>
							  </div>
							  <div class="col-md-12">
							    <div class="col-md-7">
									<div class="row form-group">
	                                             <div class="col col-md-6">
	                                                 <label class=" form-control-label">WET/PET/FET Document No <strong style="color: red;">*</strong></label>
	                                             </div>
	                                             <div class="col-12 col-md-5">
		                  						    <input type="text" id="wet_pet_no" name="wet_pet_no" maxlength="50" class="form-control autocomplete" autocomplete="off" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search">
		                					     </div>
	                               </div>
		            		   </div>
		            		  <div class="col-md-5">
		            		     <div class="row form-group">
	                                             <div class="col col-md-5" >
	                                                     <label class=" form-control-label">Table Title <strong style="color: red;">*</strong></label>
	                                             </div>
	                                             <div class="col-12 col-md-7">
		                  						         <textarea id="unit_type" name="unit_type" rows="3" class="form-control" disabled="disabled"></textarea>
		                					     </div>
	                             </div>
		            		  </div>
	            		   </div>
	            		   <div class="col-md-12">
	            		   <div class="col-md-7">
		              			<div class="row form-group">	              			 	
	               					<div class="col col-md-6">
	                 					<label for="text-input" class=" form-control-label">Remarks</label>
	               					</div>
	               					<div class="col-12 col-md-5">
	                 					<textarea id="remarks" name="remarks" maxlength="255" class="form-control char-counter1"></textarea>
									</div>	 							
		  						</div>
		  						</div>	
	   						</div>
	            		   
					</div>        
					<div class="card-footer" align="center">
						<input type="reset" class="btn btn-success btn-sm" value="Clear" onclick="clearAll()">   
	              		<input type="submit" class="btn btn-primary btn-sm" value="Save" onclick="return validate()">
	              		<i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm" value="Search" onclick="Search()">
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
					<table id="getSearchReportListnew"  class="table no-margin table-striped  table-hover  table-bordered  report_print" >
						<thead>
							<tr align="center">
								<th class="tableheadSer">Ser No</th>
								<th >MISO WE/PE No</th>
								<th >Table Title</th>
								<th >WET/PET No</th>
                                <th class='lastCol'>Action</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach var="item" items="${list}" varStatus="num" >
								<tr>
									<td class="tableheadSer">${num.index+1}</td>
									<td >${item.we_pe_no}</td>
									<td >${item.table_title}</td>
									<td >${item.wet_pet_no}</td>
									<td id="thAction1" class='lastCol'>${item.id}</td>
								</tr>
						</c:forEach>
				</tbody>
				   </table>
		         </div>	
		         </div>
       
       <c:url value="link_wet_to_we1" var="searchUrl" />
    		<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="we_pe_no1">
    			<input type="hidden" name="we_pe01" id="we_pe01" value="0"/>
    			<input type="hidden" name="we_pe_no1" id="we_pe_no1" value="0"/>
    			<input type="hidden" name="wet_link_status1" id="wet_link_status1" value="0"/>
    		</form:form> 
    		
    		
	
	<c:url value="UpdateWETToWE" var="updateUrl" />
	<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid" target="result"> 
		<input type="hidden" name="updateid" id="updateid" value="0"/>
	</form:form>
	
	
<script>

function Search(){
	 var we_pe1 = document.getElementsByName('we_pe1');
     var we_peValue = false;

     for(var i=0; i<we_pe1.length;i++){
         if(we_pe1[i].checked == true){
        	 we_peValue = true;    
         }
     }
     if(!we_peValue){
         alert("Please select Document (WE/PE)");
         return false;
     }
	    
	 if($("#we_pe_no").val() == ""){
		alert("Please Enter WE/PE No");
		$("#we_pe_no").focus();
		return false;
	 } 
	 var r = $("input[name='we_pe1']:checked").val();
	   $("#we_pe01").val(r);
	   $("#we_pe_no1").val($("#we_pe_no").val());
	   $("#wet_link_status1").val($("#wet_link_status").val());
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
	$.post("DeleteWETToWEUrlAdd?"+key+"="+value, {deleteid : id}).done(function(j) {
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
	document.getElementById("unit_type").value="";
}
	

$(document).ready(function() { 
	
	
	if('${we_pe01}' != ""){
	$("input[name=we_pe1][value="+'${we_pe01}'+"]").prop('checked', true);
	change_doc('${we_pe01}');
	$("#we_pe_no").val('${we_pe_no1}');
	getWePeNoByType('${we_pe01}');
	getArmByWePeNo('${we_pe_no1}');
	$("div#divwatermark").val('').addClass('watermarked'); 
	watermarkreport();
	$("#divPrint").show();
	$("div#divSerachInput").show();
	document.getElementById("printId").disabled = false;	
	
	if('${list.size()}' == 0 ){
		$("div#divSerachInput").hide(); 
		document.getElementById("printId").disabled = true;
		$("table#getSearchReportListnew").append("<tr><td colspan='5' style='text-align :center;'>Data Not Available</td></tr>");
	 }
	
	}
	$("#searchInput").on("keyup", function() {
			var value = $(this).val().toLowerCase();
			$("#getSearchReportListnew tbody tr").filter(function() { 
			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
			});
		});
	
	 $('#remarks').keyup(function() {
	        this.value = this.value.toUpperCase();
	    });
 	   try
	   {
		    if(window.location.href.includes("msg="))
				{
					var url = window.location.href.split("?msg=")[0];
				var m=  window.location.href.split("?msg=")[1];
				window.location = url;
				
				if(m.includes("Updated+Successfully")){
					window.opener.getRefreshReport('link_wet_we_weap',m);
					window.close('','_parent','');
				}
				}   	
		}
		catch (e)
		{
			// TODO: handle exception
		}

});
</script>
<script>
function clearAll()
{	
	document.getElementById("printId").disabled = true;
	$("div#divPrint").hide();
	 var tab = $("#getSearchReportListnew"); 
	  tab.empty(); 
	  $("#searchInput").val("");
	  $("#searchInput").hide();
	//document.location.reload();
	 	localStorage.clear();
		localStorage.Abandon();
} 

function validate(){
	 var we_pe1 = document.getElementsByName('we_pe1');
     var we_peValue = false;

     for(var i=0; i<we_pe1.length;i++){
         if(we_pe1[i].checked == true){
        	 we_peValue = true;    
         }
     }
     if(!we_peValue){
         alert("Please select Document (WE/PE)");
         return false;
     }
	    
	 if($("#we_pe_no").val() == ""){
		alert("Please Enter WE/PE No");
		$("#we_pe_no").focus();
		return false;
	 } 

	 var wet_pet1 = document.getElementsByName('wet_pet1');
     var wet_petValue = false;

     for(var i=0; i<wet_pet1.length;i++){
         if(wet_pet1[i].checked == true){
        	 wet_petValue = true;    
         }
     }
     if(!wet_petValue){
         alert("Please select Document (WET/PET/FET)");
         return false;
     }
     
	 if($("#wet_pet_no").val() == ""){
		  alert("Please Enter WET/PET No");
		  $("#wet_pet_no").focus();
		  return false;
	 } 
  
	return true;
}

</script>

<script>
function change_doc(val){
	if(val != ""){
		if(val == 'WE'){
			var we_pe = val;
			$("#wet").attr("disabled", false);
			document.getElementById("we_pe_no").value = "";
			document.getElementById("table_title").value = "";
			document.getElementById("wet_pet_no").value = "";
			document.getElementById("unit_type").value = "";
			$("#pet,#fet").attr("disabled", true);
			$('input[id=pet]').prop('checked',false);
			$('input[id=fet]').prop('checked',false);
			$('input[id=wet]').prop('checked',true);
			
			getWePeNoByType(we_pe);
			wet_pet($("input[name='wet_pet1']:checked").val());
		    
		}
		else if(val == 'PE'){
			var we_pe = val;
			$("#pet").attr("disabled", false);
		    document.getElementById("we_pe_no").value = "";
		    document.getElementById("table_title").value = "";
		    document.getElementById("wet_pet_no").value = "";
			document.getElementById("unit_type").value = "";
		    $("#wet,#fet").attr("disabled", true);
		    $('input[id=wet]').prop('checked',false);
		    $('input[id=pet]').prop('checked',true);
		    
		    getWePeNoByType(we_pe);
		    wet_pet($("input[name='wet_pet1']:checked").val());
		}
		else if(val == 'FE'){
			var we_pe = val;
			$("#fet").attr("disabled", false);
			document.getElementById("we_pe_no").value = "";
			document.getElementById("table_title").value = "";
			document.getElementById("wet_pet_no").value = "";
			document.getElementById("unit_type").value = "";
			 $("#wet,#pet").attr("disabled", true);
			 $('input[id=pet]').prop('checked',false);
			 $('input[id=wet]').prop('checked',false);
			 $('input[id=fet]').prop('checked',true);
			 
			 getWePeNoByType(we_pe);
			 wet_pet($("input[name='wet_pet1']:checked").val());
		
		}
		else if(val == 'GSL'){
			var we_pe = val;
			$("#wet,#pet,#fet").attr("disabled", false);
			document.getElementById("we_pe_no").value = "";
			document.getElementById("table_title").value = "";
			document.getElementById("wet_pet_no").value = "";
			document.getElementById("unit_type").value = "";
			$('input[id=fet]').prop('checked',false);
			$('input[id=pet]').prop('checked',false);
			$('input[id=wet]').prop('checked',false); 
			
			getWePeNoByType(we_pe);
		}
	}
	
}

function wet_pet(val){
	
	if(val != ""){
	 if(val == 'WET'){
		var wet_pet = val;
		$('input[id=pet]').prop('checked',false);
		$('input[id=fet]').prop('checked',false); 
		document.getElementById("wet_pet_no").value = "";
		document.getElementById("unit_type").value = "";
		getWetPetNoByType(wet_pet);
	}	
	else if(val == 'PET'){
		var wet_pet = val;
		$('input[id=wet]').prop('checked',false);
		$('input[id=fet]').prop('checked',false); 
		document.getElementById("wet_pet_no").value = "";
		document.getElementById("unit_type").value = "";
		
		getWetPetNoByType(wet_pet);
	}
	else if(val == 'FET'){
		var wet_pet = val;
		$('input[id=wet]').prop('checked',false);
		$('input[id=pet]').prop('checked',false); 
		document.getElementById("wet_pet_no").value = "";
		document.getElementById("unit_type").value = "";
		getWetPetNoByType(wet_pet);
	}	
	 }
}
$(function() {	
	
	 $("input[type='radio'][name='wet_pet1']").click(function(){
			var wet_pet1 = $("input[name='wet_pet1']:checked").val();
			wet_pet(wet_pet1);
	 });
	 
	 
 $("input[type='radio'][name='we_pe1']").click(function(){
	var we_pe1 = $("input[name='we_pe1']:checked").val();
	change_doc(we_pe1);	
   });	 
 
});	

function getWePeNoByType(we_pe1)
{
	
 if(we_pe1 != ""){
	 var wepetext=$("#we_pe_no");
	
	  wepetext.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
        	type: 'POST',
       	    url: "getWePeCondiByNo?"+key+"="+value,
	        data: {type1 : we_pe1, newRoleid : "aw", we_pe_no : document.getElementById('we_pe_no').value},
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
	    	      	  alert("Please Enter Approved WE/PE No");
	    	      	 document.getElementById("table_title").value=""; 
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

function getWetPetNoByType(wet_pet)
{
	if(wet_pet != ""){
	 var wepetext=$("#wet_pet_no");
	 
	  wepetext.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
	        type: 'POST',
	  	    url: "getWetPetFetNo?"+key+"="+value,
	        data: {val : wet_pet ,wet_pet_no:document.getElementById('wet_pet_no').value},
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
	        	alert("Please Enter Approved WET/PET No");
	        	wepetext.val("");	        	
	        	wepetext.focus();
	        	return false;	             
	          }   	         
	      }, 
	    });
	 }
	 else
		 alert("Please select Document");
}
</script>
<script>
function getArmByWePeNo(val)
{ 
	  if(val != "" && val != null)
	  {   
		  $.post("getDetailsByWePeCondiNo?"+key+"="+value, {val : val}).done(function(j) {
			if(j!=null && j != ""){
				document.getElementById("table_title").value=j[0].table_title;
			}
			else
			{
				document.getElementById("table_title").value="";
			}
		  }).fail(function(xhr, textStatus, errorThrown) { }); 	
	  } 
}
$('#wet_pet_no').change(function() {
    var wet_pet_no = this.value;
    $.post("getWetpetNoDetailsList?"+key+"="+value, {wet_pet_no:wet_pet_no}).done(function(j) {
   		for ( var i = 0; i < j.length; i++) {
   			if(j !="" && j!=null){

				for ( var i = 0; i < j.length; i++) {
					$("#unit_type").val(j[i].unit_type);
		   		}
			}
				else{
					$("#unit_type").val("");				
				}	
   	 		
   		}
    }).fail(function(xhr, textStatus, errorThrown) { }); 
});

</script>
