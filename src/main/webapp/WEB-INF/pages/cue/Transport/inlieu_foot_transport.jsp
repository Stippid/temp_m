<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

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

<script>
function printDiv() 
{
	var printLbl = ["Type :", "MISO WE/PE No :", "Table Title :"];
	var printVal = [$('input:radio[name=we_pe]:checked').val(),document.getElementById('we_pe_no').value,document.getElementById('table_title').value];
	printDivOptimize('tableshow','In Lieu GENERAL NOTES For Transport',printLbl,printVal,"select#status");
}

</script>

<form:form name="inlieu_foot_transport" id="inlieu_foot_transport" action="inlieu_foot_transportAction" method="post" class="form-horizontal" commandName="inlieu_foot_transportCMD"> 
	<div class="animated fadeIn">
		  	<div class="container" align="center">
	    		<div class="card">
	    			<div class="card-header"><h5><b>In Lieu GENERAL NOTES For Transport</b><br><span style="font-size: 12px;color:red">(To be entered by Line Dte)</span></h5></div>
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
	                  						<label class=" form-control-label">MISO WE/PE No <strong style="color: red;">*</strong></label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                  						<input type="text" id="we_pe_no" name="we_pe_no" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search" class="form-control autocomplete" maxlength="100" autocomplete="off">
	                					</div>
	              					</div>
								</div>
								 <div class="col-md-6">
								    <div class="row form-group">
	                					<div class="col col-md-6">
	                  						<label class=" form-control-label">WE/PE Title</label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                  						<input type="text" id="table_title" name="table_title" class="form-control autocomplete" autocomplete="off" readonly="readonly">
	                					</div>
	              					</div>
								</div>
	            		</div>
						<div class="col-md-12"><span class="line"></span></div>
							<div class="col-md-12">
									<div class="col-md-6">
	              						<div class="row form-group">
	                						<div class="col col-md-6">
	                  							<label class=" form-control-label">Std. MCT <strong style="color: red;">*</strong></label>
	                						</div>
	                						<div class="col-12 col-md-6">
	                  						     <input type="text" id="mct_no" name="mct_no" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search" class="form-control autocomplete" autocomplete="off">
	                					    </div>
	              						</div>
	              					</div>
	              					<div class="col-md-6">
							            <div class="row form-group">
							                <div class="col col-md-6">
							                    <label class=" form-control-label">Veh Nomenclature <strong style="color: red;">*</strong></label>
							                </div>
							                <div class="col-12 col-md-6">
							                     <input type="text" id="std_nomclature" name="std_nomclature"  style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search" class="form-control autocomplete" autocomplete="off">
							                </div>
							            </div>
							        </div>
							  </div>
							  <div class="col-md-12">
	              					<div class="col-md-6">
						              	<div class="row form-group">
	                						<div class="col col-md-6">
	                  							<label class=" form-control-label">Base Authorisation </label>
	                						</div>
	                						<div class="col-12 col-md-6">
	                  						     <input type="text" id="auth_amt" name="auth_amt" class="form-control" readonly="readonly">
	                					    </div>
	              						</div>
	              					</div>
	              					<div class="col-md-6">
							            <div class="row form-group">
							                <div class="col col-md-6">
							                    <label class=" form-control-label">In Lieu Authorisation <strong style="color: red;">*</strong></label>
							                </div>
							                <div class="col-12 col-md-6">
							                     <input type="text" id="actual_inlieu_auth" onfocus="if(this.value=='0') this.value='';" maxlength="5" name="actual_inlieu_auth" value="0" placeholder="" class="form-control" onkeypress="return isNumber0_9Only(event);" autocomplete="off" maxlength="3">
							                </div>
							            </div>
							     </div>
	              			 </div>
	              			 <div class="col-md-12">
	              			     <div class="col-md-6">
										<div class="row form-group">
	                						<div class="col col-md-6">
	                  							<label class=" form-control-label">In Lieu MCT <strong style="color: red;">*</strong></label>
	                						</div>
	                						<div class="col-12 col-md-6">
	                  						     <input type="text" id="in_lieu_mct" name="in_lieu_mct" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search" class="form-control autocomplete" autocomplete="off">
	                					    </div>
	              						</div>
	              					</div>
	              					<div class="col-md-6">
							            <div class="row form-group">
							                <div class="col col-md-6">
							                    <label class=" form-control-label">In Lieu Veh Nomenclature <strong style="color: red;">*</strong></label>
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
					                     <label class=" form-control-label">Condition <strong style="color: red;">*</strong></label>
					                </div>
					                <div class="col-12 col-md-6">
					                     <textarea class="form-control" name="condition" id="condition" maxlength="255"></textarea>
					                </div>
                                 </div>
				             </div> 	
							 <div class="col-md-6">     
				                <div class="row form-group">
					                <div class="col col-md-6">
					                     <label class=" form-control-label">Remarks </label>
					                </div>
					                <div class="col-12 col-md-6">
					                     <textarea class="form-control char-counter1" name="remarks" maxlength="255"></textarea>
					                </div>
                               </div>			
              			   </div>
              			 </div>
	              </div>
					<div class="card-footer" align="center">
						<input type="reset" class="btn btn-success btn-sm" value="Clear" onclick="clearAll()">   
	              		<input type="submit" class="btn btn-primary btn-sm" value="Save" onclick="return validate()">
	              		<i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm" value="Search" onclick="Search();">
	              		<i class="fa fa-print"></i><input type="button" id="printId" class="btn btn-primary btn-sm btn_report" value="Print" onclick="printDiv();" disabled>
		            </div> 		
	        	</div>
			</div>
	</div>

</form:form>

  <div style="width: 20%; padding-left: 1%; display: none;" id="divSerachInput">
<input id="searchInput" type="text" style="font-size:12px; font-family: 'FontAwesome',Arial;margin-bottom: 5px;" placeholder="&#xF002; Search Word"  size="35" class="form-control">
</div>  
<div class="col s12" id="tableshow" style="display: none;">
	<div id="divShow" style="display: block;">
	</div>
<div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
		<span id="ip"></span>
			<table id="dtBasicExample" class="table no-margin table-striped  table-hover  table-bordered report_print" >
				   <thead>
						<tr>
							<th class="tableheadSer">Ser No</th>
							<th>MISO WE/PE No</th>
							<th>MCT No</th>
							<th>Veh Nomenclature</th>									
							<th>Authorisation</th>
							<th>Std. MCT Total</th>
							<th>In Lieu MCT</th>									
							<th>Std. Nomenclature</th>
							<th>Actual In Lieu Authorisation</th>									
							<th class='lastCol'>Action</th>
						</tr>
					</thead> 
				   <tbody>
				    <c:forEach var="item" items="${list}" varStatus="num" >
								<tr>
									<td class="tableheadSer">${num.index+1}</td>
									<td>${item.we_pe_no}</td>
									<td>${item.mct_no}</td>
									<td>${item.mct_name}</td>
									<td>${item.auth_amt}</td>
									<td>${item.total}</td>
									<td>${item.in_lieu_mct}</td>
									<td>${item.inle_name}</td>
									<td>${item.actual_inlieu_auth}</td>
									<td id="thAction1" class='lastCol'>${item.id}</td>
								</tr>
								</c:forEach>
					</tbody>
			</table>
		</div>
		
		</div>
<c:url value="inlieu_foot_transport1" var="searchUrl" />
    		<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="we_pe_no1">
    			<input type="hidden" name="we_pe01" id="we_pe01" value="0"/>
    			<input type="hidden" name="we_pe_no1" id="we_pe_no1" value="0"/>
    			<input type="hidden" name="mct_no1" id="mct_no1" value="0"/>
    			<input type="hidden" name="in_lieu_mct1" id="in_lieu_mct1" value="0"/>
    			<input type="hidden" name="status1" id="status1" value="0"/>
    		</form:form> 
<c:url value="UpdateFootTransport" var="updateUrl" />
	<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid" target="result">
		<input type="hidden" name="updateid" id="updateid" value="0"/>
	</form:form>

<script>


function Search(){
	var r =  $('input:radio[name=we_pe]:checked').val();	
	  if(r == undefined)
	  {		 
		    alert("Please select Document");
		    $('input:radio[name=we_pe]:checked').focus();
			return false;
	  } 
	if($("#we_pe_no").val() == ""){
		alert("Please Enter the WE/PE No");
		$("input#we_pe_no").focus();
		return false;
	 } 
	
    $("#we_pe01").val(r);
	$("#we_pe_no1").val($("#we_pe_no").val());
    $("#mct_no1").val($("#mct_no").val());
    $("#in_lieu_mct1").val($("#in_lieu_mct").val());
    $("#status1").val($("#status").val());
    document.getElementById('searchForm').submit();
}



var newWin;
function editData(id) {	
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
function deleteData(id){
	 $.post("DeleteFootTransportAdd?"+key+"="+value, {deleteid : id}).done(function(j) {
			alert(j);
			Search();
	        }).fail(function(xhr, textStatus, errorThrown) { }); 				
	}
	
function clearDescription(){
  	 document.getElementById('we_pe_no').value = "";
  	 document.getElementById('table_title').value = "";
  }

</script>
	
<script>

$(document).ready(function() {

	if('${we_pe01}' != "")
	{
		$("input[name=we_pe][value="+'${we_pe01}'+"]").prop('checked', true);
		$("#we_pe_no").val('${we_pe_no1}');
		getarmvalue('${we_pe_no1}');
		
		$("div#divwatermark").val('').addClass('watermarked'); 
		watermarkreport(); 
		$("#tableshow").show();
		 $("div#divSerachInput").show();
		document.getElementById("printId").disabled  = false;	
		
		if('${list.size()}' == 0 ){
			 $("div#divSerachInput").hide(); 
			 document.getElementById("printId").disabled  = true;
			$("table#dtBasicExample").append("<tr><td colspan='12' style='text-align :center;'>Data Not Available</td></tr>");
		 }
	}
	
	$("#searchInput").on("keyup", function() {
			var value = $(this).val().toLowerCase();
			$("#dtBasicExample tbody tr").filter(function() {  
				$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
			});
		});
	
	 $('#condition').keyup(function() {
	        this.value = this.value.toUpperCase();
	    });	
	 
	 $('#remarks').keyup(function() {
	        this.value = this.value.toUpperCase();
	    });
	
	
		var r =  $('input[type=radio][name=we_pe]:checked').val();	
		if(r != undefined)
			getWePeNoByType(r);	
		

		 $("input[type='radio'][name='we_pe']").click(function(){
				var we_pe1 = $("input[name='we_pe']:checked").val();
				getWePeNoByType(we_pe1);
		 });
	 
	
	 try{
		 if(window.location.href.includes("count="))
			{
				var url = window.location.href.split("?count=")[0];
				var m=  window.location.href.split("&msg=")[1];
				window.location = url;
				
				if(m.includes("Updated+Successfully")){
					window.opener.getRefreshReport('inlieu_trans',m);
					window.close('','_parent','');
				}   				
			} 	
		 if(window.location.href.includes("count="))
  		{
  			var url = window.location.href.split("?count")[0];
  			window.location = url;
  		}
  		
  	}
  	catch (e) {
  		// TODO: handle exception
  	} 
});

function isNumber0_9Only(evt){
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if( ! ( charCode >= 48 && charCode <= 57 ) && charCode != 8 ){
		 return false;
	}
    return true;
} 

function validate(){    
	var r =  $('input:radio[name=we_pe]:checked').val();	
	  if(r == undefined)
	  {		 
		    alert("Please select Document");
		    $('input:radio[name=we_pe]:checked').focus();
			return false;
	  } 
	if($("#we_pe_no").val() == ""){
		alert("Please Enter the WE/PE No");
		$("input#we_pe_no").focus();
		return false;
	 } 
     
	 if($("#mct_no").val() == ""){
		alert("Please Enter the Std. MCT Number");
		$("input#mct_no").focus();
		return false;
	 } 
	 if($("#std_nomclature").val() == ""){
			alert("Please Enter the Std. Nomenclature");
			$("input#std_nomclature").focus();
			return false;
     } 
	 
	 var a = document.getElementById("auth_amt").value;
	 if (isNaN(a) || a < 1) {
		 alert("In Lieu Foot Transport Not Possible.");
		 return false;
     }
	 
	 if($("#actual_inlieu_auth").val() == ""){
		  alert("Please Enter the actual In Lieu Authorisation");
		  $("input#actual_inlieu_auth").focus();
		  return false;
	 } 
 
	 if($("#in_lieu_mct").val() == ""){
		  alert("Please Enter the In Lieu MCT Number");
		  $("input#in_lieu_mct").focus();
		  return false;
	 } 
     if($("#in_std_nomclature").val() == ""){
		  alert("Please Enter the In lieu Nomenclature");
		  $("input#in_std_nomclature").focus();
		  return false;
	  } 
     if($("#in_std_nomclature").val() == ""){
		  alert("Please Enter the In lieu Nomenclature");
		  $("input#in_std_nomclature").focus();
		  return false;
	  } 
     if($("#condition").val() == ""){
		  alert("Please Enter the Condition");
		  $("#condition").focus();
		  return false;
	  } 
     
      
	return true;
}

function getarmvalue(val){
	
	  if(val != "" && val != null)
	  {
		  $.post("getDetailsByWePeCondiNo?"+key+"="+value, {val : val}).done(function(j) {
			  document.getElementById("table_title").value=j[0].table_title;
		  }).fail(function(xhr, textStatus, errorThrown) { }); 
	  }
	  else
	  {
		 alert("Please enter WE/PE No");
		  document.getElementById("table_title").value="";

		
	  }

}
function clearAll()
{
	document.getElementById('tableshow').style.display='none';
	document.getElementById("printId").disabled = true;
	var tab = $("#dtBasicExample");
 	tab.empty();
 	$("#searchInput").val("");
 	$("#searchInput").hide();
 	//document.location.reload();
 	localStorage.clear();
	localStorage.Abandon();
}
</script>


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
	        	getarmvalue($(this).val());
	        	var we_pe_no = ui.item.value;     
 			 
    		   	$.ajaxSetup({
    		   	    async: false
    		   	});
    		 
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
	        	  alert("Please Enter Approved MCT No");
	        	  wepetext1.val("");
	        	  $("input#std_nomclature").val("");	        	
	        	  wepetext1.focus();
	        	  return false;	             
	          }   	         
	      }, 
	       select: function( event, ui ) {
	        	$(this).val(ui.item.value);    	        	
	       	 var mct = $(this).val(); 
         	$.ajaxSetup({
         	    async: false
         	});
                
         	$.post("getMctNoDetailsList?"+key+"="+value, {mct: mct}).done(function(j) {
    			$("input#std_nomclature").val(j);	
    	    }).fail(function(xhr, textStatus, errorThrown) { }); 	
         		var we_pe_no = $("input#we_pe_no").val();
             	if(mct != '' && we_pe_no != '')
     				{
             		$.post("getBaseAuthAmountDetailsList?"+key+"="+value, {we_pe_no:we_pe_no,mct_no:mct}).done(function(j) {
            			 $("#auth_amt").val(j); 
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
	        	var mct = $(this).val(); 
            	$.ajaxSetup({
            	    async: false
            	});
                   
            	$.post("getMctNoDetailsList?"+key+"="+value, {mct: mct}).done(function(j) {
        			$("input#in_std_nomclature").val(j);
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
  		    	 }
  		           	else {
  		        	  alert("Please Enter Approved Standard Nomenclature");
  		        	  wepetext1.val("");
  		        	  $("input#mct_no").val("");	
  		        	 
  		        	  wepetext1.focus();
  		        	  return false;	             
  		          }   	         
  		      }, 
  		       select: function( event, ui ) {
  		        	$(this).val(ui.item.value);    	        	
  		        	var std_nomclature = $(this).val();           
  		           	
  		          $.post("getStdNomclatureDetailsList?"+key+"="+value, {std_nomclature:std_nomclature}).done(function(j) {
  		        		for ( var i = 0; i < j.length; i++) {           			
		           	 		$("input#mct_no").val(j);		
		           	 	}   		           	 	                  	 	      
	         	        }).fail(function(xhr, textStatus, errorThrown) { }); 
  		          
  		          var mct = $("input#mct_no").val(); 
  	         	$.ajaxSetup({
  	         	    async: false
  	         	});
  	                
  	         	$.post("getMctNoDetailsList?"+key+"="+value, {mct: mct}).done(function(j) {
  	         		$("input#std_nomclature").val(j);	
     	        }).fail(function(xhr, textStatus, errorThrown) { });
  	         	
  	         		var we_pe_no = $("input#we_pe_no").val();
  	             	if(mct != '' && we_pe_no != '')
  	     				{
  	             	 		$.post("getBaseAuthAmountDetailsList?"+key+"="+value, {we_pe_no:we_pe_no,mct_no:mct}).done(function(j) {
	  	             		 $("#auth_amt").val(j); 
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
  		    		var std_nomclature = this.value;           
  		    	  $.post("getStdNomclatureDetailsList?"+key+"="+value, {std_nomclature:std_nomclature}).done(function(j) {
	            		for ( var i = 0; i < j.length; i++) {           			
		           	 		$("input#mct_no").val(j);		
		           	 	}  		           	 	                  	 	      
	         	        }).fail(function(xhr, textStatus, errorThrown) { }); 
		        	  return true;   
  		         
  		    	 }
  		           	else {
  		        	  alert("Please Enter Approved Standard Nomenclature");
  		        	  wepetext1.val("");
  		        	  $("input#in_lieu_mct").val("");	
  		        	 
  		        	  wepetext1.focus();
  		        	  return false;	             
  		          }   	         
  		      },    
  		    });
      });
</script>
<script>
function getCheck(){
    validate();
    if($("#we_pe_no").val() != "" && $('#status').val() != null){
    	Search();
    } 
}
</script>