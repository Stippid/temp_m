<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/excluded/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>  

<script src="js/JS_CSS/jquery.dataTables.min.js"></script>
<script src="js/JS_CSS/dataTables.bootstrap.min.js"></script>

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
function printDiv() 
{
	var printLbl = [];
	var printVal = [];
	printDivOptimize('tableshow','Search In Lieu GENERAL NOTES For Transport',printLbl,printVal,"select#status");
}
</script>

<form name="search_inlieu_foot_transport" id="search_inlieu_foot_transport" action="" method="post" class="form-horizontal"> 
	<div class="animated fadeIn">
		<div class="">
	    	<div class="container" align="center">
	    		<div class="card">
	    		<div class="card-header"><h5><b>Search In Lieu GENERAL NOTES For Transport</b><br><span style="font-size: 10px;font-size:15px;color:red">(To be approved by SD Dte)</span></h5></div>
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
					                                  <input type="radio" id="inline-radio1" name="we_pe" value="WE" class="form-check-input" onchange="clearDescription()">WE
					                                </label>&nbsp;&nbsp;&nbsp;
					                                <label for="inline-radio2" class="form-check-label ">
					                                  <input type="radio" id="inline-radio2" name="we_pe" value="PE" class="form-check-input" onchange="clearDescription()">PE
					                                </label>&nbsp;&nbsp;&nbsp;
					                                <label for="inline-radio3" class="form-check-label ">
					                                  <input type="radio" id="inline-radio3" name="we_pe" value="FE" class="form-check-input" onchange="clearDescription()">FE
					                                </label>&nbsp;&nbsp;&nbsp;
					                                <label for="inline-radio4" class="form-check-label ">
					                                  <input type="radio" id="inline-radio4" name="we_pe" value="GSL" class="form-check-input" onchange="clearDescription()">GSL
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
	                  						<input type="text" id="we_pe_no" name="we_pe_no" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search" class="form-control autocomplete" autocomplete="off">
	                					</div>
	              					</div>
	              				</div>
	              				<div class="col-md-6">
								    <div class="row form-group">
	                					<div class="col col-md-6">
	                  						<label class=" form-control-label">WE/PE Title</label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                  						<input type="text" id="table_title" name="table_title" placeholder="" class="form-control" autocomplete="off" readonly="readonly">
	                					</div>
	              					</div>
	              				</div>
	              			</div>
	              			<div class="col-md-12">
	              			   <div class="col-md-6">
	              					<div class="row form-group">
	                						<div class="col col-md-6">
	                  							<label class=" form-control-label">MCT No</label>
	                						</div>
	                						<div class="col-12 col-md-6">
	                  						     <input type="text" id="mct_no" name="mct_no" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search" class="form-control autocomplete" autocomplete="off">
	                					    </div>
	              				    </div>
	              			   </div>
              				    <div class="col-md-6">
              					<div class="row form-group">
						                <div class="col col-md-6">
						                    <label class=" form-control-label">Veh Nomenclature</label>
						                </div>
						                <div class="col-12 col-md-6">
						                     <input type="text" id="std_nomclature" name="std_nomclature" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search" class="form-control autocomplete" autocomplete="off">
						                </div>
						        </div>
						     </div>
							 </div>
							 <div class="col-md-12">
							    <div class="col-md-6">
	              				    <div class="row form-group">
	                						<div class="col col-md-6">
	                  							<label class=" form-control-label">In Lieu MCT No </label>
	                						</div>
	                						<div class="col-12 col-md-6">
	                  						     <input type="text" id="in_lieu_mct" name="in_lieu_mct" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search" class="form-control autocomplete" autocomplete="off">
	                					    </div>
	              					</div>
								</div>
								<div class="col-md-6">
							        <div class="row form-group">
							                <div class="col col-md-6">
							                    <label class=" form-control-label">In Lieu Nomenclature</label>
							                </div>
							                <div class="col-12 col-md-6">
							                     <input type="text" id="in_std_nomclature" name="in_std_nomclature" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search" class="form-control autocomplete" autocomplete="off">
							                </div>
							        </div>	
	              				</div>
	              			</div>
	              			<div class="col-md-12">
	              			   <div class="col-md-6">
								    <div class="row form-group">
	                					<div class="col col-md-6">
                 					          <label for="text-input" class=" form-control-label">Status of Records</label>
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
							<input type="reset" class="btn btn-success btn-sm" value="Clear" onclick="clearAll();" >   
		              		<i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm" value="Search" onclick="Search();">
		              		<i class="fa fa-print"></i><input type="button" id="printId" class="btn btn-primary btn-sm btn_report" value="Print" onclick="printDiv();" disabled>
			            </div> 		
	        	</div>
			</div>
		</div>
    </div>
</form>

  <div style="width: 20%; padding-left: 1%; display: none;" id="divSerachInput">
<input id="searchInput" type="text" style="font-size:12px; font-family: 'FontAwesome',Arial;margin-bottom: 5px;" placeholder="&#xF002; Search Word"  size="35" class="form-control">
</div>  
					<div class="col s12" style="display: none;" id="tableshow">
							<div id="divShow" style="display: block;">
	                        </div>
	                         <div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
							<span id="ip"></span>
	                        
								<table id="dtBasicExample" class="table no-margin table-striped  table-hover  table-bordered">
									 <thead >
											<tr>
												<th class="tableheadSer">Ser No</th>
												<th >MISO WE/PE No</th>
												<th >MCT No</th>
												<th >Veh Nomenclature</th>
												<th >Authorisation</th>
												<th >Std MCT Total</th>
												<th >In Lieu MCT</th>									
												<th >Std Nomenclature</th>
												<th >Actual In Lieu Authorisation</th>
												<th id="thLetterId" style="display: none;">Letter No</th>
												<th id="thRemarkId" style="display: none;">Error Correction</th>
												<th id="thAction" class='lastCol' >Action</th>
											</tr>
										</thead>
									    <tbody>
									    <c:forEach var="item" items="${list}" varStatus="num" >
											<tr>
												<td class="tableheadSer">${num.index+1}</td>
												<td >${item.we_pe_no}</td>
												<td >${item.mct_no}</td>
												<td >${item.mct_name}</td>
												<td >${item.auth_amt}</td>
												<td >${item.total}</td>
												<td >${item.in_lieu_mct}</td>
												<td >${item.inle_name}</td>
												<td >${item.actual_inlieu_auth}</td>
												<td id="thLetterId1" style="display: none;">${item.reject_letter_no}</td>
												<td id="thRemarkId1" style="display: none;">${item.reject_remarks}</td>
												<td id="thAction1"   class='lastCol'>${item.id}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
						</div>
					</div>							


 <c:url value="search_inlieu_foot_transport1" var="searchUrl" />
   		<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="we_pe_no1">
   			<input type="hidden" name="we_pe1" id="we_pe1" value="0"/>
   			<input type="hidden" name="we_pe_no1" id="we_pe_no1" value="0"/>
   			<input type="hidden" name="mct_no1" id="mct_no1" value="0"/>
   			<input type="hidden" name="std_nomclature1" id="std_nomclature1" value="0"/>
   			<input type="hidden" name="table_title1" id="table_title1" value="0"/>
   			<input type="hidden" name="in_lieu_mct1" id="in_lieu_mct1" value="0"/>
   			<input type="hidden" name="in_std_nomclature1" id="in_std_nomclature1" value="0"/>
   			<input type="hidden" name="status1" id="status1" value="0"/>
   		</form:form> 
    		
<c:url value="ApprovedFootTransport" var="appUrl" />
	<form:form action="${appUrl}" method="post" id="appForm" name="appForm" modelAttribute="appid">
		<input type="hidden" name="appid" id="appid" value="0"/>
		<input type="hidden" name="we_pe2" id="we_pe2" value="0"/>
		<input type="hidden" name="we_pe_no2" id="we_pe_no2" value="0"/>
		<input type="hidden" name="mct_no2" id="mct_no2" value="0"/>
		<input type="hidden" name="table_title2" id="table_title2" value="0"/>
	
		<input type="hidden" name="std_nomclature2" id="std_nomclature2" value="0"/>
   		<input type="hidden" name="in_std_nomclature2" id="in_std_nomclature2" value="0"/>
		<input type="hidden" name="in_lieu_mct2" id="in_lieu_mct2" value="0"/>
		<input type="hidden" name="status2" id="status2" value="0"/>
	</form:form> 
	
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
<script>
function getWePeNoByType(we_pe1)
{
	 if(we_pe1 != ""){
      var wepetext1=$("#we_pe_no");
    
	  wepetext1.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
        	type: 'POST',
 	        url: "getWePeCondiByNo?"+key+"="+value,
	        data: {type1 : we_pe1, newRoleid : "at", we_pe_no : document.getElementById('we_pe_no').value},
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
	        	  wepetext1.val("");
				  document.getElementById("table_title").value=""; 
	        	 
	        	  wepetext1.focus();
	        	  return false;	             
	          }   	         
	      }, 
	      select: function( event, ui ) {
	        	$(this).val(ui.item.value);   
	        	
	        	var we_pe_no = ui.item.value;     
	        		$.post("getWEPENoDetailsList?"+key+"="+value, {we_pe_no:we_pe_no}).done(function(j) {
	        		  if(j != "" && j !=null)
		           		{
			           		for ( var i = 0; i < j.length; i++) {           			
			           	 		$("input#table_title").val(j);		
			           	 	}
		           		}
		           		else{
		           			$("input#table_title").val("");
		           		} 	 	      
	              	        }).fail(function(xhr, textStatus, errorThrown) { }); 
	        	       	
	        }
	      
	    });
	 }
	 else
	 	alert("Please select Document");
	 }

</script>

<script>
$(function(){
 var wepetext1=$("#mct_no");

	  wepetext1.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
        	type: 'POST',
	        url: "getMctNoListDetail?"+key+"="+value,
	        data: {mct_main_id:document.getElementById('mct_no').value},
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
	        	  alert("Please Enter Approved MCT No");
	        	  wepetext1.val("");
	        	  $("input#std_nomclature").val("");
	        	  wepetext1.focus();
	        	  return false;	             
	          }   	         
	      }, 
	        select: function( event, ui ) {
	        	$(this).val(ui.item.value); 
	        	
	        	 var mct = ui.item.value; 
	            	$.ajaxSetup({
	            	    async: false
	            	});
           		  $.post("getMctNoDetailsList?"+key+"="+value, {mct: mct}).done(function(j) {
            		  if(j != "" && j != null)
            	 		{
            	 			$("input#std_nomclature").val(j);	
            	 		}
            	 	else
            	 		{
            	 			$("input#std_nomclature").val("");	
            	 		}
	              }).fail(function(xhr, textStatus, errorThrown) { }); 
		            	  
	            	var we_pe_no = $("input#we_pe_no").val();
	                	if(mct != '' && we_pe_no != '')
        				{
	     		           
	     	            	  $.post("getBaseAuthAmountDetailsList?"+key+"="+value, {we_pe_no:we_pe_no,mct_no:mct}).done(function(j) {
	     	            		 if(j != "" && j != null)
	 	            	 		{	
	 	     		           	 	$("#auth_amt").val(j);  		           			
	 	            	 		}
	 	     		           	else
	      		           		{
	      		           			$("#auth_amt").val("");
	      		           		}
	  		           		    if(j == 0){	 		           		       
	  		           		     	$("#auth_amt").val(0);
	  		           		    }    		           	
	     		            }).fail(function(xhr, textStatus, errorThrown) { }); 
        				}
	        }  	     
	    });
	       	
           	
});
</script>     	
        
<script>
$(function(){
 var wepetext1=$("#in_lieu_mct");

	  wepetext1.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
        	type: 'POST',
	        url: "getMctNoListDetail?"+key+"="+value,
	        data: {mct_main_id:document.getElementById('in_lieu_mct').value},
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
	        	  alert("Please Enter Approved MCT No");
	        	  wepetext1.val("");
	        	  $("input#in_std_nomclature").val("");
	        	  wepetext1.focus();
	        	  return false;	             
	          }   	         
	      }, 
	        select: function( event, ui ) {
	        	$(this).val(ui.item.value);
	        	
	    		 var mct = ui.item.value; 
	            	$.ajaxSetup({
	            	    async: false
	            	});
	                  
	            	 $.post("getMctNoDetailsList?"+key+"="+value, {mct: mct}).done(function(j) {
	            		 if(j != "" && j != null)
		            		{
		            			$("input#in_std_nomclature").val(j);	
		            		}
		            		else
		            		{
		            			$("input#in_std_nomclature").val("");
		            		}
		              }).fail(function(xhr, textStatus, errorThrown) { }); 
	        }  	     
	    });          	
});
</script>     	
        
<script>

$(function() {
var wepetext1=$("#std_nomclature");

 wepetext1.autocomplete({
      source: function( request, response ) {
        $.ajax({
        type: 'POST',
		url: "getStdNomenclatureListTrans?"+key+"="+value,
        data: {mct_nomen:document.getElementById('std_nomclature').value},
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
    	 }else {
        	  alert("Please Enter Approved Standard Nomenclature");
        	  wepetext1.val("");
        	  $("input#mct_no").val("");	
        	 
        	  wepetext1.focus();
        	  return false;	             
          }   	         
      },select: function( event, ui ) {
        	$(this).val(ui.item.value);    	        	
 		 
    		var std_nomclature = this.value;           
    		$.post("getStdNomclatureDetailsList?"+key+"="+value, {std_nomclature:std_nomclature}).done(function(j) {
          		if(j != "" && j != null)
           		{
               		for ( var i = 0; i < j.length; i++) {           			
               	 		$("input#mct_no").val(j);		
               	 	}
           		} 
           		else
           		{
           			$("input#mct_no").val("");
           		}
                	        }).fail(function(xhr, textStatus, errorThrown) { });
        }  	     
    });
});
</script>

<script>

$(function() {
var wepetext1=$("#in_std_nomclature");

wepetext1.autocomplete({
    source: function( request, response ) {
      $.ajax({
  	  type: 'POST',
   	  url: "getStdNomenclatureListTrans?"+key+"="+value,
      data: {mct_nomen:document.getElementById('in_std_nomclature').value},
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
       
  	 }
         	else {
      	  alert("Please Enter Approved Standard Nomenclature");
      	  wepetext1.val("");
      	  $("input#in_lieu_mct").val("");	
      	 
      	  wepetext1.focus();
      	  return false;	             
        }   	         
    },select: function( event, ui ) {
      	$(this).val(ui.item.value); 
      	
  		var std_nomclature = this.value;           
  		$.post("getStdNomclatureDetailsList?"+key+"="+value, {std_nomclature:std_nomclature}).done(function(j) {
  	  		if(j != "" && j != null)
  	 		{
  	  		for ( var i = 0; i < j.length; i++) {           			
  	  	 		$("input#in_lieu_mct").val(j);		
  	  	 	}
  	 		}
  	 		else
  	 		{
  	 		$("input#in_lieu_mct").val("");	
  	 		}
  	          	        }).fail(function(xhr, textStatus, errorThrown) { }); 
      	        	
      } 	     
  });
});
</script>

<script>

function Search(){
	var r =  $('input:radio[name=we_pe]:checked').val();	
		 if(r == undefined)
		 {		 
			    alert("Please select Document");
			    $('input:radio[name=we_pe]:checked').focus();
				return false;
		 }
    $("#we_pe1").val(r);
	$("#we_pe_no1").val($("#we_pe_no").val());
    $("#mct_no1").val($("#mct_no").val());
    $("#in_lieu_mct1").val($("#in_lieu_mct").val());
    $("#status1").val($("#status").val());
    $("#std_nomclature1").val($("#std_nomclature").val());
    $("#in_std_nomclature1").val($("#in_std_nomclature").val());
    $("#table_title1").val($("#table_title").val());
    document.getElementById('searchForm').submit();
 	   
	}

function Approved(id){
   document.getElementById('appid').value=id;
   var we_pe1 = $("input[name='we_pe']:checked").val();
    $("#we_pe2").val(we_pe1);
	$("#we_pe_no2").val($("#we_pe_no").val());
    $("#mct_no2").val($("#mct_no").val());
    $("#in_lieu_mct2").val($("#in_lieu_mct").val());
    $("#status2").val($("#status").val());
    $("#std_nomclature2").val($("#std_nomclature").val());
    $("#in_std_nomclature2").val($("#in_std_nomclature").val());
    $("#table_title2").val($("#table_title").val());
	document.getElementById('appForm').submit();
}

function Reject(id){
	  document.getElementById('rejectid_model').value=id;
	  cleardata();
}

function clearAll()
{	
	document.getElementById('tableshow').style.display='none';
	var tab = $("#dtBasicExample");
	tab.empty();
 	$("#searchInput").val("");
	$("#searchInput").hide();
 	
 	document.getElementById("printId").disabled = true;
 	//document.location.reload();
 	localStorage.clear();
	localStorage.Abandon();
}

function clearDescription(){
	 document.getElementById('we_pe_no').value = "";
	 document.getElementById('table_title').value = "";
}
</script>

<script>
$(document).ready(function() {
  
   $("div#divwatermark").val('').addClass('watermarked'); 
	watermarkreport();
	if('${status1}' != ""){
		$("#dtBasicExample").show();
		var we_pe3 = '${we_pe1}';
		$("input[name=we_pe][value="+we_pe3+"]").prop('checked', true);
		$("#we_pe_no").val('${we_pe_no1}');
		$("#mct_no").val('${mct_no1}');
		$("#std_nomclature").val('${std_nomclature1}');
		$("#in_std_nomclature").val('${in_std_nomclature1}');
		$("#in_lieu_mct").val('${in_lieu_mct1}');
		$("#status").val('${status1}');
		$("#table_title").val('${table_title1}');
		$("#tableshow").show();
		$("div#divSerachInput").show();
		document.getElementById("printId").disabled = false;	
		
	   if('${list.size()}' == 0 ){
   		   $("div#divSerachInput").hide();    
   		   document.getElementById("printId").disabled = true;	
   		   $("table#dtBasicExample").append("<tr><td colspan='12' style='text-align :center;'>Data Not Available</td></tr>");
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
			$("#dtBasicExample tbody tr").filter(function() { 
			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
			});
		});

		var r =  $('input[type=radio][name=we_pe]:checked').val();	
		if(r != undefined)
			getWePeNoByType(r);	

		 $("input[type='radio'][name='we_pe']").click(function(){
				var we_pe1 = $("input[name='we_pe']:checked").val();
				getWePeNoByType(we_pe1);
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
		
		$.post("updaterejectaction_foot_transport?"+key+"="+value, {remarks : remarks,letter_no : letter_no,id:id}).done(function(j) {
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
	  	var inputs = document.getElementsByName("chk_model");  
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
</script>