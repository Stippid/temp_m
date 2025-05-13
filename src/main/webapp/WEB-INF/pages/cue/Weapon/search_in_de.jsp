<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Increment Decrement General Notes For Weapon</title></head>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/excluded/jquery-2.1.3.min.js"></script>
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
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<script>
function printDiv() 
  	{
	 	var printLbl = ["Type :", "MISO WE/PE No :"];
	 	var printVal = [$('input:radio[name=we_pe]:checked').val(),document.getElementById('we_pe_no').value];
	 	printDivOptimize('divPrint','SEARCH INCREMENT/DECREMENT GENERAL NOTES FOR WEAPON',printLbl,printVal,"select#status");
 	 }
</script>

<body>
<form:form name="" id="" action="AttributeReportSearchfootnote" method='POST' commandName="in_de_footnoteCMD">
	    	<div class="container" align="center">
	        	<div class="card">
	          		<div class="card-header"><h5><b>SEARCH INCREMENT/DECREMENT GENERAL NOTES FOR WEAPON</b><br><span style="font-size: 10px;font-size:15px;color:red">(To be approved by SD Dte)</span></h5></div>
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
					                                  <input type="radio" id="inline-radio1" name="we_pe" value="WE" onchange="clearWEPE();" class="form-check-input">WE
					                                </label>&nbsp;&nbsp;&nbsp;
					                                <label for="inline-radio2" class="form-check-label ">
					                                  <input type="radio" id="inline-radio2" name="we_pe" value="PE" onchange="clearWEPE();" class="form-check-input">PE
					                                </label>&nbsp;&nbsp;&nbsp;
					                                <label for="inline-radio3" class="form-check-label ">
					                                  <input type="radio" id="inline-radio3" name="we_pe" value="FE" onchange="clearWEPE();" class="form-check-input">FE
					                                </label>&nbsp;&nbsp;&nbsp;
					                                <label for="inline-radio4" class="form-check-label ">
					                                  <input type="radio" id="inline-radio4" name="we_pe" value="GSL" onchange="clearWEPE();" class="form-check-input">GSL
					                                </label>&nbsp;&nbsp;&nbsp;
					                              </div>
							                 </div>					              
	                				</div>
	                			</div>
	                			
								 <div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-6">
	                  						<label class=" form-control-label">MISO WE/PE No <strong style="color: red;">*</strong></label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                  						<input  id="we_pe_no" name="we_pe_no"  class="form-control" autocomplete="off" onchange="getArmByWePeNo(this.value);" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search">
	                					</div>
	                					</div>
	              					</div>
	              			</div>
	              			<div class="col-md-12">
								  <div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-6">
	                  						<label class=" form-control-label">WE/PE Nomenclature</label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                  						<input class="form-control" id="item_type"  onchange="getbaseauth(this.value);" autocomplete="off" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search"> 
				                	    </div>
	                					</div>
	              					</div> 
	              				<div class="col-md-6">
	              			 	<div class="row form-group"> 
	                					<div class="col col-md-6">
	                  						<label  class=" form-control-label" >Item Code </label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                  						<input id="item_seq_no" name="item_seq_no" class="form-control" autocomplete="off" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search">
	                  						
										</div>
	  								</div> 
	  						</div>
	              		  </div>
	              		    <div class="col-md-12">	
								<div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-6">
	                  						<label class=" form-control-label">Status of Records</label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                  						<select name="status" id="status" class="form-control">
	                  						        <option value="0">Pending</option>
								                    <option value="1">Approved</option>
								                    <option value="2">Rejected</option>
								                    <option value="all">All</option>
								            </select>
										</div>
	  								</div>
								</div>
							</div>
			       </div>
					 <div class="card-footer" align="center">
						    <input type="reset" class="btn btn-success btn-sm" value="Clear" onclick="clearAllfoo();">   
					    	<i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm" onclick="return isvalidData();" value="Search">
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
						<table id="FootnoteReport1" class="table no-margin table-striped  table-hover  table-bordered report_print" >
							 <thead >
								<tr>
								   <th class="tableheadSer">Ser No</th>
									<th >Nomenclature</th>
									<th >Gen Notes Condition</th>
									<th >Base Authorisation</th>
									<th >Amt of Incr/Decr</th>
									<th >Gen Notes Scenario</th>
					   				 <th >Loc/ Fmn/ Unit</th>					
									<th id="thLetterId" style="display: none;">Letter No</th>
									<th id="thRemarkId" style="display: none;">Error Correction</th>
									<th id="thAction" class='lastCol' >Action</th>
								</tr>
							</thead> 
							<tbody>
							<c:forEach var="item" items="${list}" varStatus="num" >
								<tr>
									<td class="tableheadSer">${num.index+1}</td>
									<td >${item.item_type}</td>
									<td >${item.condition}</td>
									<td >${item.baseauth}</td>
									<td >${item.amt_inc_dec}</td>
									<td >${item.scenario}</td>
									<td >${item.loc_for_unit}</td>
									<td id="thLetterId1" style="display: none;">${item.reject_letter_no}</td>
									<td id="thRemarkId1" style="display: none;">${item.reject_remarks}</td>
									<td id="thAction1" class='lastCol'>${item.id}</th>
								</tr>
				</c:forEach>
				</tbody>
						</table>
					</div>	
					</div>
					
					
		  
  <div class="modal fade" id="rejectModal" tabindex="-1" role="dialog" aria-labelledby="rejectModalLabel" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title">Rejection Remarks/Reason</h4>
          <button type="button" class="close" data-dismiss="modal" onclick="closereject()">&times;</button>
        </div>
        <div class="modal-body">
        	<div class="form-group">	 
				<div class="col-md-12">			
				<div class="row" style="color: maroon; font-size: 16px; font-weight: bold;">
					<div class="col-sm-6">				 
				  		<input id="rejectid_model" name="rejectid_model" placeholder="" class="form-control" type ="hidden" >
						<input type="checkbox" name="chk_model" value="Error"  onclick="modeldocument();" > Error<br>
					</div>
					<div class="col-sm-6">
		        		<input type="checkbox" name="chk_model" value="Ammedment" onclick="modeldocument();"> Amendment<br>
		        	</div>
					
		       	</div>
		       	</div>
		       	<div class="col-md-12"><span class="line"></span></div>
		       <div class="col-md-12">
		       	<div class="row">
		        	<div class="col-sm-6 form-group" id="divremark" style="display: none;">
						<label for="text-input" class=" form-control-label">Reject Remarks</label>
						<textarea id="reject_remarks" name="reject_remarks" maxlength="255" class="form-control"  ></textarea>
			   		</div>
		         	<div class="col-sm-6 form-group" id="divletter" style="display: none;">							 
						<label for="text-input" class=" form-control-label">Reject Letter No</label>
						<input id="reject_letter_no" name="reject_letter_no" maxlength="50" class="form-control" >
	        		</div>
		      	</div>
		      	</div>									
			</div>		 
		
			<div align="center">
				<button type="button" name="submit" class="btn btn-primary login-btn" onclick="return updatedata();">Save</button>
				<button type="reset" name="reset" class="btn btn-primary login-btn" onclick="cleardata();">Reset</button>
			</div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-danger" data-dismiss="modal"  onclick="closereject()">Close</button>
        </div>
        
      </div>
    </div>
  </div>	  
	<c:url value="updateIncrementUrl" var="updateUrl" />
	<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid" target="result">
		<input type="hidden" name="updateid" id="updateid" value="0"/>
		</form:form> 
	
	  <c:url value="search_in_de1" var="searchUrl" />
    		<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="we_pe_no1">
    			<input type="hidden" name="we_pe_no1" id="we_pe_no1" value="0"/>
    			<input type="hidden" name="item_seq_no1" id="item_seq_no1" value="0"/>
    			<input type="hidden" name="item_type1" id="item_type1" value="0"/>
    			<input type="hidden" name="we_pe01" id="we_pe01" value="0"/>
    			<input type="hidden" name="status1" id="status1" value="0"/>
    		</form:form> 
    			     
<c:url value="ApprovedIncrementUrl" var="appUrl" />
	<form:form action="${appUrl}" method="post" id="appForm" name="appForm" modelAttribute="appid">
		<input type="hidden" name="appid" id="appid" value="0"/>
		<input type="hidden" name="we_pe_no2" id="we_pe_no2" value="0"/>
		<input type="hidden" name="we_pe2" id="we_pe2" value="0"/> 
		<input type="hidden" name="status2" id="status2" value="0"/>
		<input type="hidden" name="item_seq_no2" id="item_seq_no2" value="0"/>
		<input type="hidden" name="item_type2" id="item_type2" value="0"/> 
	</form:form> 
	
<script>
function getWePeNoByType(we_pe1)
{
	 if(we_pe1 != ""){
   var wepetext13=$("#we_pe_no");
  
   wepetext13.autocomplete({
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
          var autoTextVal=wepetext13.val();
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
      	  alert("Please Enter Approved WE/PE No");
      	  wepetext13.val("");
      	  document.getElementById("table_title1").value="";
      	  wepetext13.focus();
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
$(document).ready(function() {
	
	
	
	$("div#divwatermark").val('').addClass('watermarked'); 
	watermarkreport();
	
	if('${status1}' != ""){
		var we_pe3 = '${we_pe01}';
		$("input[name=we_pe][value="+we_pe3+"]").prop('checked', true);
		$("#we_pe_no").val('${we_pe_no1}');
		$("#item_seq_no").val('${item_seq_no1}');
		$("#item_type").val('${item_type1}');
		$("#FootnoteReport1").show();
		$("#status").val('${status1}');
		$("#divPrint").show();
		$("div#divSerachInput").show();
		document.getElementById("printId").disabled = false;	
			
		 if('${list.size()}' == 0 ){
			 $("div#divSerachInput").hide();
			 document.getElementById("printId").disabled = true;
			 $("table#FootnoteReport1").append("<tr><td colspan='10' style='text-align :center;'>Data Not Available</td></tr>");
		 } 
	}
	
	if($("#status").val() == "2"){
			$("th#thLetterId").show();
			$("th#thRemarkId").show();
			$("td#thLetterId1").show();
			$("td#thRemarkId1").show();
			$("th#thAction").show();
			$("td#thAction1").show();
    } 
	 if($("#status").val() == "all"){
			$("th#thLetterId").show();
			$("th#thRemarkId").show();
			$("td#thLetterId1").show();
			$("td#thRemarkId1").show();
			$("th#thAction").hide();
			$("td#thAction1").hide();
   } 
	
	$("#searchInput").on("keyup", function() {
			var value = $(this).val().toLowerCase();
			$("#FootnoteReport1 tbody tr").filter(function() { 
			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
			});
		});

    
 	var r =  $('input[type=radio][name=we_pe]:checked').val();	
 	if(r != undefined)
 		getWePeNoByType(r);	
 	

 	 $("input[type='radio'][name='we_pe']").click(function(){
 			var we_pe1 = $("input[name='we_pe']:checked").val();
 			$("#we_pe_no").val("");
 			getWePeNoByType(we_pe1);
 	 });
     
     
	    var z = document.getElementById("item_type").value;
		getbaseauth(z);
		  $.ajaxSetup({
			    async: false
			});	
			  
		  var wepetext14=$("#item_type");		 
		 
			  wepetext14.autocomplete({
			      source: function( request, response ) {
			        $.ajax({
		        	type: 'POST',
		 	        url: "getitemtype?"+key+"="+value,
			        data: {item_type:document.getElementById('item_type').value},
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
			            var autoTextVal=wepetext14.val();
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
			        	  alert("Please Enter Approved Nomenclature");
			        	  wepetext14.val("");
							document.getElementById("item_seq_no").value=""; 
			        	 
			        	  wepetext14.focus();
			        	  return false;	             
			          }   	         
			      }, 
			      select: function( event, ui ) {
			        	$(this).val(ui.item.value);    	        	
			        	 $.post("getitemcode?"+key+"="+value, {val : ui.item.value}).done(function(j) {
			    	    		if(j !="" && j!= null)
				        		{
			            		document.getElementById("item_seq_no").value=j;
				        		}
				        	else{
				        		document.getElementById("item_seq_no").value=""; 
				        	}
			    	      }).fail(function(xhr, textStatus, errorThrown) { });	      	
			        }  
			    });
			  
			  $.ajaxSetup({
			  	    async: false
			  	}); 
				 var wepetext2=$("#item_seq_no");     
				 
				  wepetext2.autocomplete({
				      source: function( request, response ) {
				        $.ajax({
			        	type: 'POST',
			 	        url: "getItemCodeList?"+key+"="+value,
				        data: {item_code:document.getElementById('item_seq_no').value},
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
				            var autoTextVal=wepetext2.val();
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
				        	  alert("Please Enter Approved Item Code");
				        	  wepetext2.val("");
				        	  document.getElementById("item_type").value="";       	 
				        	  wepetext2.focus();
				        	  return false;	             
				          }   	         
				      }, 
				        select: function( event, ui ) {
				        	$(this).val(ui.item.value);    	        	
				        	  $.post("getitemnamefromcode?"+key+"="+value, {val : ui.item.value}).done(function(j) {
				    	    		if(j !="" && j!= null)
					        		{
					        			document.getElementById("item_type").value=j;	
					        		}
					        	else{
					        		document.getElementById("item_type").value="";      
					        	}
				    	      }).fail(function(xhr, textStatus, errorThrown) { });      	
				        }  	     
				    });
		    
	 
	 try{
			if(window.location.href.includes("appid="))
			{
				var url = window.location.href.split("?appid")[0];
				window.location = url;
			}
			else if(window.location.href.includes("rejectid="))
			{
				var url = window.location.href.split("?rejectid")[0];
				window.location = url;
			}
			else if(window.location.href.includes("deleteid="))
			{
				var url = window.location.href.split("?deleteid")[0];
				window.location = url;
			}
			
   			else if(window.location.href.includes("msg="))
   			{
   				var url = window.location.href.split("?msg=")[0];
   				window.location = url;   				
   			} 			
		}
		catch (e) {
			// TODO: handle exception
		} 
});

function clearWEPE()
{
	 document.getElementById("we_pe_no").value="";
}

</script>		
			
  <script>
  function isvalidData()
	    {
	    
	  	
		  	var r =  $('input:radio[name=we_pe]:checked').val();	
			  if(r == undefined)
			  {		 
				    alert("Please select Document");
				    $('input:radio[name=we_pe]:checked').focus();
					return false;
			  } 
		  	 
		  	if($("input#we_pe_no").val() == "")
		  	{
		  		alert("Please Select WE/PE No");
		  		$("input#we_pe_no").focus();
		  		return false;
		  	} 
		  	Search();
	  	  return true;
	   }
			 
  function getbaseauth(val){
	     $.post("getitemcode?"+key+"="+value, {val : val}).done(function(j) {
	    	if(j!=null) 
	    	  {  
			document.getElementById("item_seq_no").value=j;
	    	  }
		else
			document.getElementById("item_seq_no").value="";
	      }).fail(function(xhr, textStatus, errorThrown) { }); 
		  }


  function getArmByWePeNo(val)
        {
        	  if(val != "" && val != null)
        	  {
        	   $.post("getDetailsByWePeCondiNo?"+key+"="+value, {val : val}).done(function(j) {
        			if(j !="" && j!= null)
          		{
        			document.getElementById("table_title1").value=j[0].table_title;
          		}
          	else{
          		  document.getElementById("table_title1").value=""; 
          	}
      	      }).fail(function(xhr, textStatus, errorThrown) { }); 
        	  }
        	  else
        	  {
        		  alert("Please enter WE/PE No");	
        		  document.getElementById("table_title1").value="";
        	  }
        }

 </script>			
 <script>
function clearAllfoo()
{
 	document.getElementById('divPrint').style.display='none';
  	document.getElementById("printId").disabled = true;
  	var tab = $("#FootnoteReport1"); 
	tab.empty(); 
  	$("#searchInput").val("");
  	$("div#divSerachInput").hide();
 //document.location.reload();
	localStorage.clear();
	localStorage.Abandon();
} 
	  
function Search(){
    var we_pe1 = $("input[name='we_pe']:checked").val();
    $("#we_pe01").val(we_pe1);
 	$("#we_pe_no1").val($("#we_pe_no").val());
    $("#item_seq_no1").val($("#item_seq_no").val());
    $("#item_type1").val($("#item_type").val());
    $("#status1").val($("#status").val());
    document.getElementById('searchForm').submit();
}
    	
function Approved(id){
  document.getElementById('appid').value=id;
  var we_pe1 = $("input[name='we_pe']:checked").val();
  	$("#we_pe2").val(we_pe1);
	$("#we_pe_no2").val($("#we_pe_no").val());
	$("#item_seq_no2").val($("#item_seq_no").val());
	$("#item_type2").val($("#item_type").val());
	$("#status2").val($("#status").val());
	document.getElementById('appForm').submit();
}
    	   
function Reject(id){
	document.getElementById('rejectid_model').value=id;
	cleardata();
}
    	   
</script>  
<script>
function updatedata()
{
	var val = null, remarks = null, letter_no = null;
	var radioButtons = document.getElementsByName("chk_model");

	if (radioButtons != null) {
		for (var radioCount = 0; radioCount < radioButtons.length; radioCount++) {
		 
			if (radioButtons[radioCount].checked) {
				 
				val = radioButtons[radioCount].value;				 
				if (val=="Error") {					 
					remarks = $("textarea#reject_remarks").val();
				}
				if (val == "Ammedment") {
					letter_no = $("input#reject_letter_no").val();
				}
			}
		}
	}
	
	if(remarks == "")
	{
		alert("Please enter Reject Remarks");
		$("textarea#reject_remarks").focus();
		return false;
	}
	else if(letter_no == "")
	{
		alert("Please enter Reject Letter No");
		$("input#reject_letter_no").focus();
		return false;
	}
	else if((remarks != "" && remarks != null) || (letter_no != "" && letter_no != null))
	{
		var id =document.getElementById('rejectid_model').value; 
		 $.post("updaterejectactionfootnote?"+key+"="+value, {	remarks : remarks,
				letter_no : letter_no,
				id:id}).done(function(j) {
					alert(j);
					if(j == "Rejected Successfully.")
					{
						 document.getElementById('rejectid_model').value ="";
						 document.getElementById('reject_remarks').value ="";
						 document.getElementById('reject_letter_no').value ="";
						 
						 //////////////////// close pop up
						 $('.modal').removeClass('in');
						 $('.modal').attr("aria-hidden","true");
						 $('.modal').css("display", "none");
						 $('.modal-backdrop').remove();
						 $('body').removeClass('modal-open');
						 //////////////////// end close pop up
						 Search();
						 
					}
	      }).fail(function(xhr, textStatus, errorThrown) { }); 
		return true;
	}
	
	return true;
}

function cleardata()
{
	  	var inputs = document.getElementsByName("chk_model");  //document.querySelectorAll('chk_model');
	  	for (var i = 0; i < inputs.length; i++) {
	    	inputs[i].checked = false;
	  	}	 
	  	document.getElementById("reject_letter_no").value ="";
	  	document.getElementById("reject_remarks").value ="";
		$("div#divletter").hide();
		$("div#divremark").hide();
} 
function modeldocument() {	
	 	$("div#divletter").hide();
		$("div#divremark").hide();
		var radioButtons = document.getElementsByName("chk_model");
		if (radioButtons != null) {
			for (var radioCount = 0; radioCount < radioButtons.length; radioCount++) {
				if (radioButtons[radioCount].checked == true) {
					val = radioButtons[radioCount].value;
					if (val == "Error") {
						$("div#divremark").show();
						$("div#divletter").hide();	
					}
					else if (val == "Ammedment") {
						$("div#divletter").show();
						$("div#divremark").hide();
					}
				}
			}
		}
		var c=$('[name="chk_model"]:checked').length;
		if(c>1)
		{
			$("div#divremark").show();
			$("div#divletter").show();			 
		} 
	}
	
var newWin;
function Update(id){	
	var x = screen.width/2 - 1100/2;
    var y = screen.height/2 - 900/2;
   
	newWin = window.open("", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');
	 
	 window.onfocus = function () { 
		 //newWin.close();
	 }

	document.getElementById('updateid').value=id;
	document.getElementById('updateForm').submit();
}
function closeWindow()
{
	newWin.close();   
}

function Delete1(id){
	
	 $.post("deleteIncrementUrlAdd?"+key+"="+value, {deleteid : id}).done(function(j) {
	   	 alert(j);
			 Search();
	     }).fail(function(xhr, textStatus, errorThrown) { });  
}	
</script>
</body>
</html>