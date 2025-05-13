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

<form:form name="pers_entit" id="pers_entit" action="pers_entitAct" method='POST' commandName="pers_entitCmd" > 
	<div class="animated fadeIn">
		<div class="">
	    	<div class="container" align="center">
	    		<div class="card">
	    			<div class="card-header"><h5><b>STANDARD Authorisation FOR PERSONNEL</b><br><span style="font-size: 12px;color:red">(To be entered by Line Dte)</span></h5></div>
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
                 					<input  id="we_pe_no" name="we_pe_no" class="form-control" maxlength="100" autocomplete="off" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search">
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
                 					<input id="tableTitle" class="form-control" autocomplete="off" readonly="readonly">
								</div>
							</div>	 							
	  						</div>
	  						<div class="col-md-6">
	  						<div class="row form-group">	           					
             					<div class="col col-md-6">
               						<label class=" form-control-label">User Arm</label>
             					</div>
             					<div class="col-12 col-md-6">
               						<input type="text" id="user_arm_hi" readonly="readonly" class="form-control">
                 					<input type="hidden" id="user_arm" name="" readonly="readonly" class="form-control">
             					</div>
             				</div>
             				</div>	           					
	  					</div>	
	  					<div class="col-md-12"><span class="line"></span></div>
	  					<div class="col-md-12">	              					
	              			<div class="col-md-6">	
	              			<div class="row form-group">              			 	
               					<div class="col col-md-6">
                 					<label for="text-input" class=" form-control-label">Category of Personnel <strong style="color: red;">*</strong></label>
               					</div>
               					<div class="col-12 col-md-6">
                 					<select id="category_of_persn" name="category_of_persn" class="form-control">
                 					<option value="">--Select--</option>
	                                    <c:forEach var="item" items="${getPersonCatListFinal}" varStatus="num" >
                 							<option value="${item.codevalue}"  name="${item.label}" >${item.label}</option>
                 						</c:forEach>
                 					</select>
								</div>	 							
	  						</div>
	  						</div>
	  						<div class="col-md-6">	
	              			<div class="row form-group">              			 	
               					<div class="col col-md-6">
                 					<label for="text-input" class=" form-control-label">Parent Arm <strong style="color: red;">*</strong></label>
               					</div>
               					<div class="col-12 col-md-6">
                 					<input id="parent_arm"  placeholder="" class="form-control">
                 					 <select id="parent_arm1" class="form-control" style="display: none;">
                 					 </select>
				                <input type="hidden" name="arm_code" id="arm_code" maxlength="4">
								</div>	 							
	  						</div>
	  						</div>  
	  						</div> 
	  						<div class="col-md-12">
	  						<div class="col-md-6">	
	  						<div class="row form-group">           					
             					<div class="col col-md-6">
               						<label class=" form-control-label">Category <strong style="color: red;">*</strong></label>
             					</div>
             					<div class="col-12 col-md-6">
               					<select id="rank_cat" name="rank_cat" class="form-control" onchange="select_rank_app_trade();select_rank_cat();">
             					<option value="">--Select--</option>
	                                    <c:forEach var="item" items="${getTypeofRankcategoryListFinal}" varStatus="num" >
                 							<option value="${item.codevalue}"  name="${item.label}" >${item.label}</option>
                 						</c:forEach>
             					</select>
             					</div>
             				</div>
             				</div>	 
             				<div class="col-md-6">	
	  						<div class="row form-group">           					
             					<div class="col col-md-6">
               						<label class=" form-control-label">Common Appt/Trade <strong style="color: red;">*</strong></label>
             					</div>
             					<div class="col-12 col-md-6">
               						<input id="appt_trade" name="appt_trade" class="form-control" autocomplete="off" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search">
               						<input type="hidden" id="app_trd_code" name="app_trd_code">
             					</div>
             				</div>
             				</div>          					
	  					</div>
	  					<div class="col-md-12">	
		  					<div class="col-md-6">
		              			<div class="row form-group">	              			 	
	               					<div class="col col-md-6">
	                 					<label for="text-input" class=" form-control-label">Rank <strong style="color: red;">*</strong></label>
	               					</div>
	               					<div class="col-12 col-md-6">
	                 					<select id="rank" name="rank" class="form-control"></select>
									</div>	 							
		  						</div>
		  						</div>	
	  						<div class="col-md-6">
							<div class="row form-group">           					
             					<div class="col col-md-6">
               						<label class=" form-control-label">Authorisation <strong style="color: red;">*</strong></label>
             					</div>
             					<div class="col-12 col-md-6">
               						<input id="auth_amt" name="auth_amt" onfocus="if(this.value=='0') this.value='';" maxlength="5"  value="0" class="form-control" onkeypress="return isNumberKey(event,this)">
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
	                 					<textarea id="remarks" name="remarks" maxlength="255" class="form-control char-counter1"></textarea>
									</div>	 							
		  						</div>
		  						</div>	
	  					</div>	  					  					
	              	</div>
					<div class="card-footer" align="center">
						<input type="reset" class="btn btn-success btn-sm" value="Clear" onclick="clearAll();">   
	              		<input type="submit" class="btn btn-primary btn-sm" value="Save" onclick="return isValid();" >
	              		 <i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm" onclick="Search();" value="Search">
		           		<i class="fa fa-print"></i><input type="button" id="printId" class="btn btn-primary btn-sm btn_report" value="Print" onclick="printDiv();" disabled>
		            </div> 
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

<div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
	<span id="ip"></span>
	<h3 class="heading_content_inner">Summary</h3>
	<table id="getSummaryPersonnelEntitlement" class="table no-margin table-striped  table-hover  table-bordered report_print">
		<thead >
			<tr>
				<th>Category</th>
				<th>Regimental</th>
				<th>ERE</th>
				<th>Total</th>	
			</tr>
		</thead> 
		<tbody>
		<c:forEach var="item1" items="${list1}" varStatus="num" >
			<tr>
				<td>${item1.rank_cat}</td>
				<td>${item1.regt}</td>
				<td>${item1.ere}</td>
				<td>${item1.total}</td>
			</tr>
		</c:forEach>
	</tbody>
	</table>
	<table id="AttributeReportPersAuth" class="table no-margin table-striped  table-hover  table-bordered report_print">
		<thead >
			<tr>
				<th class="tableheadSer">Ser No</th>
				<th>Pers Cat</th>
				<th>Parent Arm</th>
				<th>Category</th>
				<th>Rank</th>
				<th>Common Appt/Trade</th>
				<th>Base Authorisation</th>
				<th id="thLetterId" >Letter No</th>
				<th id="thRemarkId" >Error Correction</th>
				<th class='lastCol'>Action</th>
			</tr>
		</thead> 
		<tbody>
	<c:forEach var="item" items="${list}" varStatus="num" >
				<tr>
					<td class="tableheadSer">${num.index+1}</td>
					<td >${item.person_cat}</td>
					<td >${item.arm_code}</td>
					<td >${item.rank_cat}</td>
					<td >${item.rank}</td>
					<td >${item.appt_trade}</td>
					<td >${item.auth_amt}</td>
					<td id="thLetterId1" >${item.reject_letter_no}</td>
					<td id="thRemarkId1" >${item.reject_remarks}</td>
					<td id="thAction1" class='lastCol'>${item.id}</td>
				</tr>
		</c:forEach>
		</tbody>
	</table>
	
	</div>
	</div>

<c:url value="pers_entitUrl1" var="searchUrl" />
    		<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="we_pe_no1">
    			<input type="hidden" name="we_pe1" id="we_pe1" value="0"/>
    			<input type="hidden" name="we_pe_no1" id="we_pe_no1" value="0"/>
    			<input type="hidden" name="category_of_persn1" id="category_of_persn1" value="0"/>
    			<input type="hidden" name="rank_cat1" id="rank_cat" value="0"/>
    			<input type="hidden" name="appt_trade1" id="appt_trade1" value="0"/>
    			<input type="hidden" name="parent_arm1" id="parent_arm1" value="0"/>
    			<input type="hidden" name="rank1" id="rank1" value="0"/>
    			<input type="hidden" name="auth_amt1" id="auth_amt1" value="0"/>
    			<input type="hidden" name="status1" id="status1" value="0"/>
    			<input type="hidden" name="regt1" id="regt1" value="0"/>
    			<input type="hidden" name="ere1" id="ere1" value="0"/>
    			<input type="hidden" name="total1" id="total1" value="0"/>
    		</form:form> 
   		
<c:url value="pers_entitUrl" var="pers_entit" />
<form:form action="${pers_entit}" method="post" id="pers_entitForm" name="pers_entitForm" modelAttribute="getwe_pe_no">
	<input type="hidden" name="getwe_pe_no" id="getwe_pe_no" value="0"/>
</form:form>

 <c:url value="updatePersonal_EntitalUrl" var="updateUrl" />
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

	if($("input#we_pe_no").val() == "")
	{
		alert("Please enter WE/PE No");
		$("input#we_pe_no").focus();
		return false;
	} 
	
   $("#we_pe1").val(r);
   $("#we_pe_no1").val($("#we_pe_no").val());
   $("#category_of_persn1").val($("#category_of_persn").val());
   $("#rank_cat1").val($("#rank_cat").val());
   $("#appt_trade1").val($("#appt_trade").val());
   $("#parent_arm1").val($("#parent_arm").val());
   $("#rank1").val($("#rank").val());
   $("#auth_amt1").val($("#auth_amt").val());
   $("#regt1").val($("#regt").val());
   $("#ere1").val($("#ere").val());
   $("#total1").val($("#total").val());
   $("#status1").val($("#status").val());
   document.getElementById('searchForm').submit();
}

var newWin;
function editData(id){	
	var x = screen.width/2 - 1100/2;
    var y = screen.height/2 - 900/2;
  	newWin = window.open("", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes,directories=no,titlebar=no,location=no,menubar=no');
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

function clearWEPE()
{
	document.getElementById('we_pe_no').value = "";	
	document.getElementById('tableTitle').value = "";	
	document.getElementById('user_arm_hi').value = "";	
}

function deleteData(id){
	var footnode_det;
	var mdfs_det;	
	
		 $.post("getpers_det?"+key+"="+value, {id:id}).done(function(data) {
				for (var i = 0; i < data.length; i++) {
					var we_pe_no= data[i].we_pe_no;
					var app_trd_code= data[i].app_trd_code;
					var arm_code = data[i].arm_code;
					var rank =data[i].rank;
					var rank_cat = data[i].rank_cat;
					var category_of_persn = data[i].category_of_persn; 
					
					$.post("getfootnode_det?"+key+"="+value,{we_pe_no:we_pe_no,app_trd_code:app_trd_code,arm_code:arm_code,rank:rank,rank_cat:rank_cat,category_of_persn:category_of_persn}).done(function(j) {
						footnode_det = j;
						$.post("getmdfs_det?"+key+"="+value,{we_pe_no:we_pe_no,app_trd_code:app_trd_code,arm_code:arm_code,rank:rank,rank_cat:rank_cat,category_of_persn:category_of_persn}).done(function(j1) {
							mdfs_det = j1;									
							if(footnode_det == false && mdfs_det == false){
								
								$.post("deletePersonal_EntitalUrlAdd?"+key+"="+value,{deleteid : id}).done(function(j) {
									alert(j);
									Search();
									 }).fail(function(xhr, textStatus, errorThrown) { });  
								
							}	
							else{
								alert("Data Not Deleted.Because of Already Exist in General Notes/Modification!!!");	
							}
							 }).fail(function(xhr, textStatus, errorThrown) { }); 
						
						 }).fail(function(xhr, textStatus, errorThrown) { }); 
					
			
				}
      }).fail(function(xhr, textStatus, errorThrown) { }); 
	}
</script>
	
<script>
function printDiv() 
{
	var printLbl = ["Type :", "MISO WE/PE No :", "Table Title :","User Arm :"];
	var printVal = [$('input:radio[name=we_pe]:checked').val(),document.getElementById('we_pe_no').value,document.getElementById('tableTitle').value,document.getElementById('user_arm_hi').value];
	printDivOptimize('divPrint','STANDARD Authorisation FOR PERSONNEL',printLbl,printVal,"select#status");
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
	        data: {type1 : we_pe1, newRoleid : "ap", we_pe_no : document.getElementById('we_pe_no').value},
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
					//if (newE.toLowerCase() === autoTextVal.toLowerCase()) {
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
	        	  document.getElementById("user_arm_hi").value="";
	    		  document.getElementById("tableTitle").value="";
	        	  wepetext1.focus();
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

<script type="text/javascript">
$(document).ready(function() {
	

	if('${we_pe1}' != "")
	{	
		$("select#category_of_persn").val('${category_of_persn1}');
		$("select#rank_cat").val('${rank_cat1}');
		$("input[name=we_pe][value="+'${we_pe1}'+"]").prop('checked', true);
		$("#we_pe_no").val('${we_pe_no1}');
		getArmByWePeNo('${we_pe_no1}');
		$("#divPrint").show();
		$("div#divSerachInput").show();
		$("div#divwatermark").val('').addClass('watermarked'); 
		watermarkreport();
		document.getElementById("printId").disabled = false;
		
		if('${list.size()}' == 0 ){
			$("div#divSerachInput").hide();
			document.getElementById("printId").disabled = true;
		     $("table#AttributeReportPersAuth").append("<tr><td colspan='10' style='text-align :center;'>Data Not Available</td></tr>");
		 } 
		if('${list1.size()}' == 0 ){
			$("div#divSerachInput").hide();
			document.getElementById("printId").disabled = true;
		     $("table#getSummaryPersonnelEntitlement").append("<tr><td colspan='4' style='text-align :center;'>Data Not Available</td></tr>");
		 }
	}
		
	
		$('#auth_amt').keyup(function(){
			if ($(this).val() > 9999){
		    	alert("Authorisation of manpower entered is more than 9999");
			}
		});
	  	$('#remarks').keyup(function() {
			this.value = this.value.toUpperCase();
	    });
	 
	$("#searchInput").on("keyup", function() {
		var value = $(this).val().toLowerCase();
			$("#AttributeReportPersAuth tbody tr").filter(function() { 
			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
			});
		});
	
	$("#searchInput").on("keyup", function() {
		var value = $(this).val().toLowerCase();
			$("#getSummaryPersonnelEntitlement tbody tr").filter(function() { 
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
	 
	
	  $('select#category_of_persn').change(function() {
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
        		$("#arm_code").val($("#user_arm").val());
        	}
   	});
	  $('select#parent_arm1').change(function() {			
		$("#arm_code").val($("#parent_arm1").val());
	 });
	try{
		 if(window.location.href.includes("msg="))
		{
			var url = window.location.href.split("?msg=")[0];
			var m=  window.location.href.split("?msg=")[1];
			window.location = url;
			
			if(m.includes("Updated+Successfully")){
				window.opener.getRefreshReport('std_per',m);
				window.close('','_parent','');
			}
		} 
	}
	catch (e) {
	} 

});

function ex(){
	var parent_arm_name = document.getElementById("user_arm").value;
	 $.post("getArmNameByCode?"+key+"="+value,{parent_arm_name:parent_arm_name}).done(function(j) {
		 $("#user_arm_hi").val(j); 		
		 }).fail(function(xhr, textStatus, errorThrown) { });
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
	function getArmByWePeNo(val)
	 {
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
	function isNumber0_9Only(evt){
		var charCode = (evt.which) ? evt.which : evt.keyCode;
		if( ! ( charCode >= 48 && charCode <= 57 ) && charCode != 8 ){
			 return false;
		}
	    return true;
	}
	
function select_rank_app_trade(){
	var rnk = $("select#rank_cat").val();
	
	$('#appt_trade').val("");
	$('#app_trd_code').val("");
	
	 var wepetext1=$("#appt_trade");

	  wepetext1.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
        	type: 'POST',
	  	    url: "getTypeappt_tradeList?"+key+"="+value,
	        data: {rnk : rnk,appt_trade : document.getElementById('appt_trade').value },
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
	        	  alert("Please Enter Approved Common Appt/Trade");
	        	  wepetext1.val("");	
	        	  document.getElementById("app_trd_code").value="";
	        	  wepetext1.focus();
	        	  return false;	             
	          }   	         
	      }, 
	       select: function( event, ui ) {
	        	$(this).val(ui.item.value);    	        	
	        	$.post("getappt_trade_codelist?"+key+"="+value,{a :$(this).val(),rank_cat :$("select#rank_cat").val()}).done(function(j) {
	        		 document.getElementById("app_trd_code").value=j[0];		
					 }).fail(function(xhr, textStatus, errorThrown) { });   

	        } 	     
	    });
	
	}
function select_rank_cat(){
	var rnk = $("select#rank_cat").val();
	 $('select#rank').val("");
	
	 
	 $.post("getTypeofRankList?"+key+"="+value,{rnk : rnk}).done(function(j) {
		 var length = j.length-1;
			var enc = j[length].columnName1.substring(0,16);
			var options = '<option value="">'+ "--Select--" + '</option>';
			for ( var i = 0; i < j.length-1; i++) {
				options += '<option value="' + dec(enc,j[i].columnCode)+ '" >'+ dec(enc,j[i].columnName) + '</option>';					
			}	
			$("select#rank").html(options); 		
		 }).fail(function(xhr, textStatus, errorThrown) { });   

		
}

function isValid()
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
		alert("Please enter WE/PE No");
		$("input#we_pe_no").focus();
		return false;
	} 
	
	 else if($("select#category_of_persn").val() == "")
	{
		alert("Please select Category of Personnel");
		$("select#category_of_persn").focus();
		return false;
	}
	  else if($("select#category_of_persn").find('option:selected').attr("name") == "ERE")
	{
		if($("select#parent_arm1").val() == "0")
		{
			alert("Please select Parent Arm"); 
			$("select#parent_arm1").focus();
			return false;
		}
	}  
	
	 if($("select#rank_cat").val() == "")
	{
		alert("Please select Category");
		$("select#rank_cat").focus();
		return false;
	}
	
	if($("input#appt_trade").val() == "")
	{
		alert("Please enter Common Appt/Trade");
		$("input#appt_trade").focus();
		return false;
	} 
	
	if($("select#rank").val() == "")
	{
		alert("Please select Rank");
		$("select#rank").focus();
		return false;
	}
	
	
	 if($("input#auth_amt").val() == "" || $("input#auth_amt").val() == "0")
	{
		alert("Please enter Authorisation");
		$("input#auth_amt").focus();
		return false;
	}   
	

	return true;
}

function clearAll()
{
	document.getElementById('divPrint').style.display='none';
	document.getElementById("printId").disabled = true;
	var tab = $("#AttributeReportPersAuth");
 	tab.empty(); 
 	var tab1 = $("#getSummaryPersonnelEntitlement");
 	tab1.empty(); 
 	
 	$("#searchInput").val("");
 	$("#searchInput").hide();
 	//document.location.reload();
 	localStorage.clear();
	localStorage.Abandon();
}

//////////////////only numeric and point(.)
function isNumberKey(evt) {
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if (charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57)) {
		return false;
	} else {
		if (evt.target.value.search(/\./) > -1 && charCode == 46) {
			return false;
		}
	}
	return true;
}

/////////////////////==== Only 0-9 numeric value
function validateNumber(evt) {
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57)) {
            return false;
    } else {
        if (charCode == 46) {
                return false;
        }
    }
    return true;
}
</script>
