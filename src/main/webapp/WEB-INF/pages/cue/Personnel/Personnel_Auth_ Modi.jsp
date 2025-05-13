<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 


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
function printDiv() 
{
	var printLbl = ["Type :", "MISO WE/PE No :", "Table Title :","User Arm :"];
	var printVal = [$('input:radio[name=we_pe]:checked').val(),document.getElementById('we_pe_no1').value,document.getElementById('tableTitle').value,document.getElementById('user_arm_hi').value];
	printDivOptimize('divPrint','AUTHORISATION OF PERSONNEL UNDER MODIFICATION',printLbl,printVal,"select#status");
}
</script>

<script>
function getWePeNoByType(we_pe1)
{
	 if(we_pe1 != "" || we_pe1 != "undefined" || we_pe1 != undefined){ 
      var wepetext1=$("#we_pe_no1");     
	  wepetext1.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
        	type: 'POST',
 	        url: "getWePeCondiByNo?"+key+"="+value,
	        data: {type1 : we_pe1, newRoleid : "ap", we_pe_no : document.getElementById('we_pe_no1').value},
	          success: function( data ) {
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
	        	  document.getElementById("user_arm").value="";	
				  document.getElementById("tableTitle").value="";
	        	  wepetext1.focus();
	        	  return false;	             
	          }   	         
	      }, 
	       select: function( event, ui ) {
	        	$(this).val(ui.item.value);    	        	
	        	getarmvalue($(this).val());	        	
	       } 	     
	    });
	  
	 }
 	 else
 		 alert("Please select Document");
} 
      
 $(function() {
	 
	 var wepetext1=$("#modification");	
	  wepetext1.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
	        type: 'POST',
	        url: "getTypeofModiList?"+key+"="+value,
	        data: {label:document.getElementById('modification').value},
	          success: function( data ) {
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
	        	  alert("Please Enter Approved Modification");
	        	  wepetext1.val("");
	        	
	        	  wepetext1.focus();
	        	  return false;	             
	          }   	         
	      }, 
	       select: function( event, ui ) {
	        	$(this).val(ui.item.value);    	        	
	        	compulsaryvalid($(this).val());	        	
	        } 	     
	    });
}); 
     
</script>
<form:form name="Wepe_pers_mdfs" id="Wepe_pers_mdfs" action="Wepe_pers_mdfsAct" method='POST' commandName="Wepe_pers_mdfsCmd">
		<div class="container" align="center">
        	<div class="card">
        	<div class="card-header"><h5><b>AUTHORISATION OF PERSONNEL UNDER MODIFICATION</b><br><span style="font-size:12px;color:red">(To be entered by Line Dte)</span></h5></div>
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
					                                  <input type="radio" id="inline-radio1" name="we_pe" value="WE" class="form-check-input" onchange="clearWEPE();">WE
					                                </label>&nbsp;&nbsp;&nbsp;
					                                <label for="inline-radio2" class="form-check-label ">
					                                  <input type="radio" id="inline-radio2" name="we_pe" value="PE" class="form-check-input" onchange="clearWEPE();">PE
					                                </label>&nbsp;&nbsp;&nbsp;
					                                <label for="inline-radio3" class="form-check-label ">
					                                  <input type="radio" id="inline-radio3" name="we_pe" value="FE" class="form-check-input" onchange="clearWEPE();">FE
					                                </label>&nbsp;&nbsp;&nbsp;
					                                <label for="inline-radio4" class="form-check-label ">
					                                  <input type="radio" id="inline-radio4" name="we_pe" value="GSL" class="form-check-input" onchange="clearWEPE();">GSL
					                                </label>&nbsp;&nbsp;&nbsp;
					                              </div>
							                 </div>					              
	                				</div>
	                			</div>       					
              			<div class="col-md-6">
              			 	<div class="row form-group"> 
               					<div class="col col-md-6">
                 					<label for="text-input" class=" form-control-label">MISO WE/PE No <strong style="color: red;">*</strong></label>
               					</div>
               					<div class="col-12 col-md-6">
                 					<input  id="we_pe_no1" name="we_pe_no"  class="form-control" maxlength="100" autocomplete="off" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search">
								</div>
 							</div> 
  						</div>
  						</div>
  						<div class="col-md-12">	
  						<div class="col-md-6">	
	              			<div class="row form-group">              			 	
               					<div class="col col-md-6">
                 					<label for="text-input" class=" form-control-label">WE/PE Title</label>
               					</div>
               					<div class="col-12 col-md-6">
                 					<textarea id="tableTitle" class="form-control" autocomplete="off" readonly="readonly"></textarea>
								</div>
							</div>	 							
	  						</div>
  						
  						<div class="col-md-6">
 							<div class="row form-group">  								
               					<div class="col col-md-6">
                 					<label for="text-input" class=" form-control-label">User Arm</label>
               					</div>
               					<div class="col-12 col-md-6">
               					   <input type="text" name="user_arm_hi" id="user_arm_hi" readonly="readonly" class="form-control">
                 					<input type="hidden" id="user_arm" name="" readonly="readonly" class="form-control">
								</div>
 							</div>
 						</div>
  					</div>
  					
  					<div class="col-md-12">	
  					<div class="col-md-6">
  					<div class="row form-group"> 
  					<div class="col col-md-6">
					               	<label class=" form-control-label">Modification Scenario <strong style="color: red;">*</strong></label>
					 </div>
					  <div class="col-12 col-md-6">
					              <select name="scenario" id="scenario" class=" form-control" onchange="return compulsaryvalid();" >
									  <option value="">--Select--</option>
									  <option value="Location">Location</option>
						              <option value="Formation">Formation</option>
						              <option value="Unit">Unit</option>
					                  <option value="Others">Others</option>
									</select>
					     </div>
					     </div>
					     </div>
					     <div class="col-md-6">
	              		<div class="row form-group" id="getloc" style="display:none;">
                					<div class="col col-md-6" >
                  						<label class=" form-control-label">Location <strong style="color: red;">*</strong> </label>
                					</div>
                					<div class="col-12 col-md-6">
                					<input type="hidden" id="location_code" name="location"  class="form-control" >
                  					<input type="text" id="location" name="location_name" maxlength="5" class="form-control" autocomplete="off" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search">
                					</div>
              					</div>  
              					<div class="row form-group" id="getform" style="display:none;" >
                					<div class="col col-md-6">
                  						<label class=" form-control-label">Formation <strong style="color: red;">*</strong> </label>
                					</div>
                					<div class="col-12 col-md-6">
	                					<input type="hidden" id="formation_code" name="formation" class="form-control" >
	                					<input type="text" id="formation" name="formation_name" maxlength="8" class="form-control" autocomplete="off" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search">
	                				</div>
              					</div>  
              					<div class="row form-group" id="getunit" style="display:none;" >
                					<div class="col col-md-6">
                  						<label class=" form-control-label">Unit <strong style="color: red;">*</strong> </label>
                					</div>
                					<div class="col-12 col-md-6">
	                					<input type="hidden" id="unit_code" name="scenario_unit" class="form-control" >
	                					<input type="text" id="unit" name="scenario_unit_name" maxlength="8" class="form-control" autocomplete="off" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search">
	                				</div>
              					</div>  
              				</div>
              			</div> 
              			
              			<div class="col-md-12"><span class="line"></span></div>
  					
  					<div class="col-md-12">	  								
  						<div class="col-md-6">
  							<div class="row form-group">  								
               					<div class="col col-md-6">
                 					<label for="text-input" class=" form-control-label">Modification </label> <strong style="color: red;">*</strong>
               					</div>
               					<div class="col-12 col-md-6">
                 					<input  id="modification" name="modification" maxlength="15" class="form-control" autocomplete="off"   onkeyup="return compulsaryvalid();" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search">
								</div>
 							</div>  								
  						</div>
  						<div class="col-md-6">
  						<div class="row form-group">
				                <div class="col col-md-6">
				                  	<label class=" form-control-label">Category of Personnel</label> <strong style="color: red;">*</strong>
				                </div>
				                <div class="col-12 col-md-6">
				                	<select  id="cat_per" name="cat_per" class="form-control"  onchange="return bbb();"> 
				                		<option value="">--Select--</option>
	                                    <c:forEach var="item" items="${getPersonCatListFinal}" varStatus="num" >
                 							<option value="${item.codevalue}"  name="${item.label}" >${item.label}</option>
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
					                  	<label class=" form-control-label">Parent Arm</label> <strong style="color: red;">*</strong>
					                </div>
					                <div class="col-12 col-md-6">
					                <input  id="parent_arm"  placeholder="" class="form-control" >
					                <select  id="parent_arm1"  class="form-control" style="display: none;"></select>
					                <input type="hidden" name="arm_code" id="arm_code" maxlength="4">
					                </div>
					            </div>	  								
	  						</div>
	  						<div class="col-md-6">
	  							<div class="row form-group">
					                <div class="col col-md-6">
					                  	<label class=" form-control-label">Category <strong style="color: red;">*</strong></label> 
					                </div>
					                <div class="col-12 col-md-6">
					                <select  id="rank_cat" name="rank_cat" class="form-control" onchange="select_rank_cat();select_rank_app_trade();return bbb111();"> 
					                	<option value="">--Select--</option>
	                                    <c:forEach var="item" items="${getTypeofRankcategoryListFinal}" varStatus="num" >
                 							<option value="${item.codevalue}"  name="${item.label}" >${item.label}</option>
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
					                  	<label class=" form-control-label">Appt/Trade <strong style="color: red;">*</strong></label>
					                </div>
					                <div class="col-12 col-md-6">
					                <input type="hidden" id="appt_trade_code" name="appt_trade">
					                <input  id="appt_trade" name="appt_trade_name" maxlength="20" class="form-control" autocomplete="off" onkeyup="return ccc111();" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search" >
					                </div>
					            </div>	  								
	  						</div>
	  						<div class="col-md-6">
  							<div class="row form-group">
				                <div class="col col-md-6">
				                  	<label class=" form-control-label">Rank</label> <strong style="color: red;">*</strong>
				                </div>
				                <div class="col-12 col-md-6">
				                <select id="rank" name="rank" class="form-control"  ></select>
				                <input type="hidden"  id="rank_hide" placeholder="" class="form-control" >
				                </div>
				            </div>						
  						</div>
  					</div> 
  				<div class="col-md-12">	
  					<div class="col-md-6">
  							<div class="row form-group">
				                <div class="col col-md-6">
				                  	<label class=" form-control-label">Base Authorisation</label> 
				                </div>
				                <div class="col-12 col-md-6">
				                <input  id="base_autho" name="base_autho" class="form-control"  readonly="readonly" maxlength="4">
				                 <input type="hidden" id="base_autho_hidden" name="base_autho_hidden">
				                </div>
				            </div>	  								
  						</div>	
  						<div class="col-md-6">
  							<div class="row form-group">
				                <div class="col col-md-6">
				                  	<label class=" form-control-label">Whether Incr/Decr</label> <strong style="color: red;">*</strong>
				                </div>
				                <div class="col-12 col-md-6">
				                	 <div class="form-check-inline form-check">
		                                <label for="radio_status2" class="form-check-label ">
		                                  <input type="radio" id="radio_status2" name="radio_status" value="Increment" class="form-check-input">Increment
		                                </label>&nbsp;&nbsp;&nbsp;
		                                <label for="radio_status3" class="form-check-label ">
		                                  <input type="radio" id="radio_status3" name="radio_status" value="Decrement" class="form-check-input">Decrement
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
				                  	<label class="form-control-label">Amount of Increment/ Decrement<strong style="color: red;">*</strong> </label>
				                </div>
				                <div class="col-12 col-md-6">
				                <input  id="amt_inc_dec" name="amt_inc_dec" maxlength="5" onfocus="if(this.value=='0') this.value='';" value="0" placeholder="" class="form-control" onkeypress="return isNumberPointKey(event, this);" onkeyup="selectradiobase();" onchange="validDif(this.value);">
				                </div>
				            </div>	  								
  						</div>
  						<div class="col-md-6">
  							<div class="row form-group">
				                <div class="col col-md-6">
				                  	<label class=" form-control-label">Remarks</label> 
				                </div>
				                <div class="col-12 col-md-6">
				                	<textarea  id="remarks" name="remarks" maxlength="255" class="form-control char-counter1" ></textarea>
				                </div>
				            </div>	  								
  						</div>	
  					</div>   	
  				</div>
  		
  					<div class="card-footer" align="center">
						<input type="reset" class="btn btn-success btn-sm" value="Clear" onclick="clearAll();">   
             			<input type="submit" class="btn btn-primary btn-sm" value="Save" onclick="return isvalidData()">
             			<i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm" value="Search" onclick="Search();">
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
			<table id="SearchPersReport" class="table no-margin table-striped  table-hover  table-bordered report_print" >
				 <thead >
					<tr>
					    <th class="tableheadSer">Ser No</th>
						<th >Pers Cat</th>
						<th >Parent Arm</th>
						<th >Category</th>
						<th >Common Appt/Trade</th>
						<th >Rank</th>
						<th >Modification</th>
						<th >Base Authorisation</th>
						<th >Amt of Incr/Decr</th>
						<th >Modification Scenario</th>
						<th >Loc/ Fmn/ Unit</th>					
						<th id="thLetterId" >Letter No</th>
						<th id="thRemarkId" >Error Correction</th>
						<th id="thAction" class='lastCol'>Action</th>
					</tr>
				</thead> 
				<tbody>
				<c:forEach var="item" items="${list}" varStatus="num" >
					<tr>
						<td class="tableheadSer">${num.index+1}</td>
						<td >${item.cat_per}</td>
						<td >${item.parent_arm}</td>
						<td >${item.rank_cat}</td>
						<td >${item.app_trade}</td>
						<td >${item.rank}</td>
						<td >${item.modification}</td>
						<td >${item.base_amt}</td>
						<td >${item.amt_inc_dec}</td>
						<td >${item.scenario}</td>
						<td >${item.loc_for_unit}</td>
						<td id="thLetterId1" >${item.reject_letter_no}</td>
						<td id="thRemarkId1" >${item.reject_remarks}</td>
						<td id="thAction1" class='lastCol'>${item.id}</td>
					</tr>
			</c:forEach>
			</tbody>
			</table>
			</div>
			</div>
		
		<c:url value="Wepe_pers_mdfsUrl1" var="searchUrl" />
    		<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="we_pe_no2">
    			<input type="hidden" name="we_pe1" id="we_pe1" value="0"/>
    			<input type="hidden" name="we_pe_no2" id="we_pe_no2" value="0"/>
    			<input type="hidden" name="modification1" id="modification1" value="0"/>
    			<input type="hidden" name="cat_per1" id="cat_per1" value="0"/>
    			<input type="hidden" name="rank_cat1" id="rank_cat1" value="0"/>
    			<input type="hidden" name="appt_trade1" id="appt_trade1" value="0"/>
    			<input type="hidden" name="arm_code1" id="arm_code1" value="0"/>
    			<input type="hidden" name="rank1" id="rank1" value="0"/>
    			<input type="hidden" name="scenario1" id="scenario1" value="0"/>
    			<input type="hidden" name="location1" id="location1" value="0"/>
    			<input type="hidden" name="formation1" id="formation1" value="0"/>
    			<input type="hidden" name="unit1" id="unit1" value="0"/>
				<input type="hidden" name="location1_hid" id="location1_hid" value="0"/>
    			<input type="hidden" name="formation1_hid" id="formation1_hid" value="0"/>
    			<input type="hidden" name="unit1_hid" id="unit1_hid" value="0"/>
    			<input type="hidden" name="status1" id="status1" value="0"/>
    		</form:form> 
    		
		<c:url value="Wepe_pers_mdfsUrl" var="Wepe_pers_mdfs" />
		<form:form action="${Wepe_pers_mdfs}" method="post" id="pers_entitForm" name="pers_entitForm" modelAttribute="getwe_pe_no">
			<input type="hidden" name="getwe_pe_no" id="getwe_pe_no" value="0"/>
			<input type="hidden" name="we_pe1" id="we_pe1" value="0"/>
    		<input type="hidden" name="we_pe_no2" id="we_pe_no2" value="0"/>
    		<input type="hidden" name="scenario1" id="scenario1" value="0"/>
   			<input type="hidden" name="location1" id="location1" value="0"/>
   			<input type="hidden" name="formation1" id="formation1" value="0"/>
   			<input type="hidden" name="unit1" id="unit1" value="0"/>
		</form:form>
	
	<c:url value="updateWEPEUrl" var="updateUrl" />
	<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid" target="result">
		<input type="hidden" name="updateid" id="updateid" value="0"/>
	</form:form>

<script>

function isNumberPointKey(evt) {

	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if (charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57)) {
		return false;
	} 
	return true;

}

function Search(){
	var we_pe1 = $("input[name='we_pe']:checked").val();
	  if(we_pe1 == undefined)
	  {		 
		    alert("Please select Document");
		    $('input:radio[name=we_pe]:checked').focus();
			return false;
	  } 
	 if($("input#we_pe_no1").val()=="" ){
		alert("Please Enter WE/PE No");
		$("input#we_pe_no1").focus();
		return false;
	}
   
   $("#we_pe1").val(we_pe1);
   $("#we_pe_no2").val($("#we_pe_no1").val());
   $("#modification1").val($("#modification").val());
   $("#cat_per1").val($("#cat_per").val());
   $("#rank_cat1").val($("#rank_cat").val());
   $("#appt_trade1").val($("#appt_trade").val());
   $("#arm_code1").val($("#arm_code").val());
   $("#rank1").val($("#rank").val());
   $("#scenario1").val($("#scenario").val());
   $("#location1").val($("#location").val());
   $("#formation1").val($("#formation").val());
   $("#unit1").val($("#unit").val());
   $("#status1").val($("#status").val());
   $("#location1_hid").val($("#location_code").val());
   $("#formation1_hid").val($("#formation_code").val());
   $("#unit1_hid").val($("#unit_code").val());
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
	 $.post("deleteWEPEUrlAdd?"+key+"="+value,{deleteid : id}).done(function(j) {
		    alert(j);
			Search();
		 }).fail(function(xhr, textStatus, errorThrown) { }); 					
}
</script>
	

<script>
$(function() {
	$('#amt_inc_dec').keyup(function(){
		  if ($(this).val() >= 100){
		    alert("Authorisation of manpower entered is more than 100");
		  }
		});

$("#scenario").change(function(){
	$("#location").val("");
	$("#formation").val("");
	$("#unit").val("");
	
	   if($(this).val()=="Location")
	   {    
		   $("label#labelcon").text("Location");
	       $("div#getloc").show();
	       $("div#getform").hide();
	       $("div#getunit").hide();
	       $("#formation").val("");
		   $("#unit").val("");
		   $("#unit_code").val("");
		   $("#formation_code").val("");
	   }
	   else if($(this).val()=="Formation"){
		   $("label#labelcon").text("Formation");
		   $("div#getform").show();
		   $("div#getloc").hide();
		   $("div#getunit").hide();
		   $("#location").val("");
		   $("#location_code").val("");
		   $("#unit").val("");
		   $("#unit_code").val("");
	   }
	   else if($(this).val()=="Unit"){
		   $("label#labelcon").text("Unit");
		   $("div#getunit").show();
		   $("div#getform").hide();
		   $("div#getloc").hide();
		   $("#location").val("");
		   $("#location_code").val("");
		   $("#formation").val("");
		   $("#formation_code").val("");
	   }
	   else
	   {
		   $("label#labelcon").text("Others");
		   $("div#getloc").hide();
		   $("div#getform").hide();
		   $("div#getunit").hide();
		   $("#location").val("");
		   $("#location_code").val("");
		   $("#formation").val("");
		   $("#unit").val("");
		   $("#unit_code").val("");
		   $("#formation_code").val("");
		   
	    }
	});
	
	
	$.ajaxSetup({
	    async: false
	});
	
 var wepetext1=$("#formation");

  wepetext1.autocomplete({
      source: function( request, response ) {
        $.ajax({
    	type: 'POST',
        url: "getFormationUrl?"+key+"="+value, 
        data: {formation : document.getElementById('formation').value},
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
        	  alert("Please Enter Approved Formation");
        	  wepetext1.val("");
        		wepetext1.focus();
        	  return false;	             
          }   	         
      }, 
         select: function( event, ui ) {
        	$(this).val(ui.item.value);    	        	
        	var unit_name=ui.item.value;
        	
        	 $.post("getUnitNameFromSusNo?"+key+"="+value,{unit_name : unit_name}).done(function(j) {
           		document.getElementById("formation_code").value=j[0];
     		 }).fail(function(xhr, textStatus, errorThrown) { });       	
        }  	      
    });
  });  
   
   
$(function() {
	 var wepetext1=$("#unit");

	  wepetext1.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
	        	type: 'POST',
	  	      url: "getUnitUrl?"+key+"="+value,
	        data: {unit : document.getElementById('unit').value},
	          success: function( data ) {
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
	        	  alert("Please Enter Approved Unit");
	        	  wepetext1.val("");
	        	 wepetext1.focus();
	        	  return false;	             
	          }   	         
	      }, 
	         select: function( event, ui ) {
	        	$(this).val(ui.item.value);    	        	
	        	var unit_name=ui.item.value;
	        	 $.post("getUnitNameFromSusNo?"+key+"="+value,{unit_name : unit_name}).done(function(j) {
	        		 document.getElementById("unit_code").value=j[0];
	     		 }).fail(function(xhr, textStatus, errorThrown) { });	        	
	        }  	      
	    });
  });  
   
$(function() {
	var wepetext1=$("#location");
	
	  wepetext1.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
	        type: 'POST',
	        url: "getLocation?"+key+"="+value,  
	        data: {code_value : document.getElementById('location').value},
	          success: function( data ) {
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
	        	  alert("Please Enter Approved Location");
	        	  wepetext1.val("");
	        	  document.getElementById("location").value="";
				  document.getElementById("location").focus();
				  document.getElementById("location_code").value="";
				 
	        	  wepetext1.focus();
	        	  return false;	             
	          }   	         
	      }, 
	      select: function( event, ui ) {
	        	$(this).val(ui.item.value);    	        	
	        	var code_value=ui.item.value;
	        	 $.post("getLocationByCode?"+key+"="+value,{code_value : code_value}).done(function(j) {
		        	 document.getElementById("location_code").value=j[0];
		     		 }).fail(function(xhr, textStatus, errorThrown) { });	        	
	        }       
	    });
}); 
</script>
	
<script>
$(document).ready(function() {
	if('${we_pe1}' != "")
	{
		$("input[name=we_pe][value="+'${we_pe1}'+"]").prop('checked', true);
		$("#we_pe_no1").val('${we_pe_no2}');
		$("#scenario").val('${scenario1}');
		
		if('${scenario1}' == "Location")
			{
			$("#location").val('${location1}');
			$("#location_code").val('${location1_hid}');
			$("#getloc").show();
			}
		if('${scenario1}' == "Formation")
		{
			$("#formation").val('${formation1}');
			$("#formation_code").val('${formation1_hid}');
			$("#getform").show();
		}
		if('${scenario1}' == "Unit")
		{
		$("#unit").val('${unit1}');
		$("#unit_code").val('${unit1_hid}');
		$("#getunit").show();
		
		}
		getarmvalue('${we_pe_no2}');
		$("#divPrint").show();
		$("div#divSerachInput").show();
		$("div#divwatermark").val('').addClass('watermarked'); 
		watermarkreport();
		document.getElementById("printId").disabled = false;
		
		if('${list.size()}' == 0 ){
			 $("div#divSerachInput").hide();
			 document.getElementById("printId").disabled = true;
		     $("table#SearchPersReport").append("<tr><td colspan='6' style='text-align :center;'>Data Not Available</td></tr>");
		 }
	}
	
	$('#remarks').keyup(function() {
       this.value = this.value.toUpperCase();
   });
	 
	$("#searchInput").on("keyup", function() {
			var value = $(this).val().toLowerCase();
			$("#SearchPersReport tbody tr").filter(function() { 
			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
			});
		});
		
	$.ajaxSetup({
	    async: false
	});
	var r =  $('input[type=radio][name=we_pe]:checked').val();	
	if(r != undefined){
		getWePeNoByType(r);	
	}
	
	 $("input[type='radio'][name='we_pe']").click(function(){
			var we_pe1 = $("input[name='we_pe']:checked").val();
			getWePeNoByType(we_pe1);
	 });
	 

$('select#cat_per').change(function() {
	var code = $(this).find('option:selected').attr("name");  
	if(code == "Regimental")	        		
	{
		$("#parent_arm").val( $("#user_arm_hi").val());
		document.getElementById("parent_arm").disabled = true; 		
		$("select#parent_arm1").hide();		
		$("input#parent_arm").show();
		$("#arm_code").val($("#user_arm").val());
	}
	else if(code == "ERE")
	{
		$("#parent_arm").val("");
		document.getElementById("parent_arm").disabled = false; 
		$("input#parent_arm").hide();
		$("select#parent_arm1").show();
		parentArm();        		
	}
	else
	{
		$("#parent_arm").val("");
		document.getElementById("parent_arm").disabled = true; 
		$("select#parent_arm1").val("0");
		$("select#parent_arm1").hide();		
		$("input#parent_arm").show();
	}
	
});

$('select#parent_arm1').change(function() {	
	$("#arm_code").val($("#parent_arm1").val());
});

$('input#appt_trade').change(function() {
	var a = $(this).val();
	var b = $("select#rank_cat").val();
	$.post("getappt_trade_codelist1?"+key+"="+value,{a : a,b:b}).done(function(j) {
		document.getElementById("appt_trade_code").value=j[0];
	 }).fail(function(xhr, textStatus, errorThrown) { });
});

 $('select#rank').change(function() {
	
	var r_c = $("select#rank_cat").val();
	var c_p = $("select#cat_per").val();
	var r = $(this).val();
	var arm = $("input#arm_code").val();
	var a_t =  $("input#appt_trade_code").val();
	var we_pe = $("input#we_pe_no1").val();
	$.post("getwepe_pers_detBaselist?"+key+"="+value,{r_c:r_c,c_p:c_p,r:r,arm:arm,a_t:a_t,we_pe :we_pe}).done(function(j) {
		 document.getElementById("base_autho").value=j;	
			if(j == "" || j == null){
				document.getElementById("base_autho").value=0.00;
				document.getElementById("base_autho_hidden").value=j;
			}
			else
			{
				document.getElementById("base_autho_hidden").value=j;
				document.getElementById("base_autho").value=j;
			}
			if(j == '0.00' || j == '0' ||j == "")
			{ 				
			$("input[type=radio][id=radio_status3]").attr('disabled', true);
			}
			else
				{
				$("input[type=radio][id=radio_status3]").attr('disabled', false);
				}
	 }).fail(function(xhr, textStatus, errorThrown) { });
}); 

try{
	 if(window.location.href.includes("msg="))
	{
		var url = window.location.href.split("?msg=")[0];
		var m=  window.location.href.split("?msg=")[1];
		window.location = url;
		
		if(m.includes("Updated+Successfully")){
			window.opener.getRefreshReport('mod_per',m);
			window.close('','_parent','');
		}
	}  
}
catch (e) {
	// TODO: handle exception
}

});

function clearWEPE()
{
	document.getElementById('we_pe_no1').value = "";	
	document.getElementById('tableTitle').value = "";
	document.getElementById('user_arm_hi').value = "";
}


function selectradiobase()

{
	var r =  $('input:radio[name=radio_status]:checked').val();	
	var base = document.getElementById("base_autho").value;
	 if(r == undefined)
	 {		 
		 alert("Please Select Increment/Decrement");
			return false;
	 }
	return true;
}

function compulsaryvalid(){

	var m = document.getElementById("modification").value;
	
	if(m == "" ){
		document.getElementById("cat_per").disabled = true;
		document.getElementById("rank_cat").disabled = true;
		document.getElementById("appt_trade").disabled = true;
		document.getElementById("rank").disabled = true;
		
		return false;
	}
	else{
		document.getElementById("cat_per").disabled = false;
		
	}
	return true;
}
function bbb(){
	
	var s = document.getElementById("cat_per").value;
	
	if(s == "" ){
		alert("Please Enter Category of Personnel");
		$("select#cat_per").focus();
		document.getElementById("rank_cat").disabled = true;
		document.getElementById("appt_trade").disabled = true;
		document.getElementById("rank").disabled = true;
		
		return false;
	}
	else{ 
		if(s == ""){
			document.getElementById("rank_cat").disabled = true;
		}
			
		$("#rank_cat").val("");
		document.getElementById("rank_cat").disabled = false;
		document.getElementById("appt_trade").disabled = true;
		document.getElementById("rank").disabled = true;
	}
	 return true;
}

function bbb111(){
	
	var s = document.getElementById("rank_cat").value;
	
	if(s == "" ){
		alert("Please Enter Category of Personnel");
		$("select#rank_cat").focus();
		document.getElementById("appt_trade").disabled = true;
		document.getElementById("rank").disabled = true;
		
		return false;
	}
	else{
		
		document.getElementById("appt_trade").disabled = false;
		
	}
	 return true;
}

function ccc111(){
	
	var s = document.getElementById("appt_trade").value;
	
	if(s == "" ){
		alert("Please Enter Rank");
		$("input#appt_trade").focus();
		document.getElementById("rank").disabled = true;
		
		return false;
	}
	else{
		$("#rank").val("");
		document.getElementById("rank").disabled = false;
		
	}
	 return true;
	
}

function parentArm()
{
	var u_a = document.getElementById("user_arm").value;
	 $.post("getArmNameListCue?"+key+"="+value,{ u : u_a}).done(function(j) {
			var length = j.length-1;
			var enc = j[length].columnName1.substring(0,16);
			var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
			for ( var i = 0; i < j.length-1; i++) {
				options += '<option value="'+dec(enc,j[i].columnCode)+'" name="' + dec(enc,j[i].columnName)+ '" >'+ dec(enc,j[i].columnName)+ '</option>';
			}	
			$("select#parent_arm1").html(options);
		 }).fail(function(xhr, textStatus, errorThrown) { }); 
	
}


function ex(){
	var parent_arm_name = document.getElementById("user_arm").value;
	 $.post("getArmNameByCode?"+key+"="+value,{parent_arm_name:parent_arm_name}).done(function(j) {
		 $("#user_arm_hi").val(j); 		
		 }).fail(function(xhr, textStatus, errorThrown) { });
}

function getarmvalue(val){
  if(val != "" && val != null)
  {
	  $.post("getDetailsByWePeCondiNo?"+key+"="+value,{val : val}).done(function(j) {
		  document.getElementById("user_arm").value=j[0].arm;	
			document.getElementById("tableTitle").value=j[0].table_title;		
			ex();
		 }).fail(function(xhr, textStatus, errorThrown) { });  
  }
  else
  {
	  alert("Please enter WE/PE No");
	  document.getElementById("user_arm").value="";	
	  document.getElementById("tableTitle").value="";
  }
}

function isvalidData(){
	var r =  $('input:radio[name=we_pe]:checked').val();	
	  if(r == undefined)
	  {		 
		    alert("Please select Document");
		    $('input:radio[name=we_pe]:checked').focus();
			return false;
	  } 
	 if($("input#we_pe_no1").val()=="" ){
		alert("Please Enter WE/PE No");
		$("input#we_pe_no1").focus();
		return false;
	}

	 if($("select#scenario").val()==""){
		alert("Please Select Scenario");
		$("select#scenario").focus();
		return false;
	} 
 var f = document.getElementById("scenario").value;
 
  if(f == "Formation"){
	 if($("input#formation").val()=="" ){
		 alert("Please Enter Formation");
			$("input#formation").focus();
			return false;
		}
 } 
	   if(f == "Location"){
		 if($("input#location").val()=="" ){
			 alert("Please Enter Location");
				$("input#location").focus();
				return false;
			} 
		
	}
	  if(f == "Unit"){
			 if($("input#unit").val()=="" ){
				 alert("Please Enter Unit");
					$("input#unit").focus();
					return false;
				} 
		}
		
	  if($("input#modification").val()==""){
			alert("Please Enter Modification");
			$("input#modification").focus();
			return false;
		} 
	   if( $("select#cat_per").val()=="" ){
			alert("Please Select Category of Personnel");
			$("select#cat_per").focus();
			return false;
		} 
		else if($("select#cat_per").find('option:selected').attr("name") == "ERE")
		{
			if($("select#parent_arm1").val() == "0")
			{
				alert("Please select Parent Arm");
				$("select#parent_arm1").focus();
				return false;
			}
		}
	
	   if($("select#rank_cat").val()==" " || $("select#rank_cat").val()==null ){
			alert("Please Select Category");
			$("select#rank_cat").focus();
			return false;
		}  
	   if($("input#appt_trade").val()=="" ){
			alert("Please Enter Common Appt/Trade");
			$("input#appt_trade").focus();
			return false;
		} 
	 
	  if($("select#rank").val()=="" || $("select#rank").val()==null){
			alert("Please select Rank");
			$("select#rank").focus();
			return false;
		}
	
	  var r =  $('input:radio[name=radio_status]:checked').val();	
	  if(r == undefined)
	 {		 
		 alert("Please Select Increment/Decrement");
			return false;
	 }
	  if($("input#amt_inc_dec").val()=="" || $("input#amt_inc_dec").val()=="0"){
			alert("Please Enter Amount of Increment/Decrement");
			$("input#amt_inc_dec").focus();
			return false;
		}
	
		  var base;
		  var amt;
		  if($("input#amt_inc_dec").val()!="")
			  {		
		   amt = $("input#amt_inc_dec").val();
		  var r_s =  $('input:radio[name=radio_status]:checked').val();	
		  base = document.getElementById("base_autho").value;	
			 base = parseFloat(base);
			 amt = parseFloat(amt);
				 if(r_s == "Decrement")
					 {
					 if (base < amt)
						 {
						 alert("Please Check Amount of Inc/Dec");
						 $("input#amt_inc_dec").focus();
						 return false;
						 }
					
					 } 
			  }

			$("input[name=we_pe][value="+'${we_pe1}'+"]").prop('checked', true);
			document.getElementById('we_pe_no2').value = $("#we_pe_no1").val();
			document.getElementById('pers_entitForm').submit();
			
		return true;
	
}

function select_rank_app_trade(){
	
	var rnk = $("select#rank_cat").val();
	 var wepetext1=$("#appt_trade");
	 $('#appt_trade').val("");
		$('#app_trd_code').val("");
	
	  wepetext1.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
        	type: 'POST',
	  	    url: "getTypeappt_tradeList?"+key+"="+value,
	        data: {rnk : rnk,appt_trade : document.getElementById('appt_trade').value},
	          success: function( data ) {
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
	        	  alert("Please Enter Approved Common Appt/Trade");
	        	  wepetext1.val("");
	        	     	  wepetext1.focus();
	        	  return false;	             
	          }   	         
	      }, 
	    });
}
		
function select_rank_cat(){
	var rnk = $("select#rank_cat").val();
	 $('select#rank').val("");

     $.post("getTypeofRankList?"+key+"="+value, {rnk : rnk}).done(function(j) {
        	 var length = j.length-1;
			 var enc = j[length].columnName1.substring(0,16); 	
			 var options = '<option value="">'+ "--Select--" + '</option>';
				for ( var i = 0; i < j.length-1; i++) {
					options += '<option value="' + dec(enc,j[i].columnCode)+ '" >'+ dec(enc,j[i].columnName)+ '</option>';					
				}	
				$("select#rank").html(options); 
      }).fail(function(xhr, textStatus, errorThrown) { }); 
}

$('select#rank').change(function() {		
	var rnk = $(this).val();
		
	$.post("getrank_des1?"+key+"="+value,{rnk : rnk}).done(function(j) {
		document.getElementById("rank_hide").value=j[0];
		 }).fail(function(xhr, textStatus, errorThrown) { });  
});

</script>
<script>
function clearAll()
{
	document.getElementById('divPrint').style.display='none';
	document.getElementById("printId").disabled = true;

	var tab = $("#SearchPersReport");
 	tab.empty();
 	
 	$("#searchInput").val("");
 	$("#divSerachInput").hide();
 	//document.location.reload();
 	localStorage.clear();
	localStorage.Abandon();
}
</script>