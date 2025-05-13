<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Standard Authorisation For Weapons</title>
</head>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/excluded/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>  

<script src="js/cue/printAllPages.js" type="text/javascript"></script>
<script type="text/javascript" src="js/miso/miso_js/jquery-1.12.3.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/cue/update.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<script>
function printDiv() 
{
	var printLbl = ["Type :", "MISO WE/PE No :"];
	var printVal = [$('input:radio[name=we_pe]:checked').val(),document.getElementById('we_pe_no').value];
	printDivOptimize('divPrint','STANDARD AUTHORISATION FOR WEAPONS',printLbl,printVal,"select#status");
}
</script>

<body>
<form:form action="standard_auth_for_weaponsAction" method="post" id="standard_auth_for_weaponsFORM" name="standard_auth_for_weaponsFORM" class="form-horizontal" commandName="standard_auth_for_weaponsActionCMD"> 
   	<div class="container" align="center">
	        	<div class="card">
	        		<div class="card-header"><h5><b>STANDARD AUTHORISATION FOR WEAPONS</b><br><span style="font-size: 10px;font-size:15px;color:red">(To be entered by Line Dte)</span></h5></div>
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
					                                  <input type="radio" id="inline-radio1" name="we_pe" value="WE" onchange="clearDiscription();" class="form-check-input">WE
					                                </label>&nbsp;&nbsp;&nbsp;
					                                <label for="inline-radio2" class="form-check-label ">
					                                  <input type="radio" id="inline-radio2" name="we_pe" value="PE" onchange="clearDiscription();"  class="form-check-input">PE
					                                </label>&nbsp;&nbsp;&nbsp;
					                                <label for="inline-radio3" class="form-check-label ">
					                                  <input type="radio" id="inline-radio3" name="we_pe" value="FE" onchange="clearDiscription();"  class="form-check-input">FE
					                                </label>&nbsp;&nbsp;&nbsp;
					                                <label for="inline-radio4" class="form-check-label ">
					                                  <input type="radio" id="inline-radio4" name="we_pe" value="GSL" onchange="clearDiscription();"  class="form-check-input">GSL
					                                </label>&nbsp;&nbsp;&nbsp;
					                              </div>
							                 </div>					              
	                				</div>
	                			</div>
	                			</div>
	            			<div class="col-md-12">	            					
	              				<div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col-12 col-md-6">
						                	<label class=" form-control-label">MISO WE/PE No <strong style="color: red;">*</strong></label>
						                </div>
										 <div class="col-12 col-md-6">
					                              <input  id="we_pe_no" name="we_pe_no" class="form-control" maxlength="100" autocomplete="off" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search"> 
					                     </div>					              
	                				</div>
	                			</div>
	                			<div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-4">
	                  						<label class=" form-control-label">WE/PE Title</label>
	                					</div>
	                					<div class="col-12 col-md-8">
	                  						<textarea id="table_title" name="table_title" class="form-control" autocomplete="off" readonly="readonly"></textarea>
	                					</div>
	                					</div>
	              					</div>
	            			</div>
	            			<div class="col-md-12"><span class="line"></span></div>
						<div class="col-md-12">	
	            			<div class="col-md-6">
              					<div class="row form-group">
                					<div class="col col-md-6">
                  						<label class=" form-control-label">Weapon Nomenclature <strong style="color: red;">*</strong></label>
                					</div>
                					<div class="col-12 col-md-6">
                						<input class="form-control" id="nomenClature" name="nomenClature" autocomplete="off" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search">
                					</div>
                					
                					</div>
              					</div>				
	              			<div class="col-md-6">
	              			 	<div class="row form-group"> 
	                					<div class="col col-md-6">
	                  						<label  class=" form-control-label" >Item Code <strong style="color: red;">*</strong></label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                  						<input id="item_seq_no" name="item_seq_no"  maxlength="8" class="form-control" autocomplete="off" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search">
										</div>
	  								</div> 
	  						</div>
	  					</div>
	  					<div class="col-md-12">		  
	  						<div class="col-md-6">
              					<div class="row form-group"> 
                					<div class="col col-md-6">
                  						<label for="text-input" class=" form-control-label">Authorisation <strong style="color: red;">*</strong></label>
                					</div>
                					<div class="col-12 col-md-6">
                  						<input  id="auth_weapon" name="auth_weapon" value="0" onfocus="if(this.value=='0') this.value='';" class="form-control"  maxlength="8" onkeypress="return isNumberKey(event,this);" onchange="return checkAuth_amtLength();">
									</div>
  								</div>
	  						</div>
  							<div class="col-md-6">
  								<div class="row form-group">  								
                					<div class="col col-md-6">
                  						<label for="text-input" class=" form-control-label">Remarks</label>
                					</div>
                					<div class="col-12 col-md-6">
                						<textarea  id="remarks" name="remarks"  maxlength="255" class="form-control char-counter1"> </textarea>
									</div>
  								</div>
  							</div>
	  					</div>  
	  		</div>
	  		    <div class="card-footer" align="center">
						<input type="reset" class="btn btn-success btn-sm" value="Clear" onclick="clearall();">   
	              		<input type="submit" class="btn btn-primary btn-sm" value="Save" onclick="return isValid();">
	              		<i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm" onclick="Search();" value="Search" >
		         		<i class="fa fa-print"></i><input type="button" id="printId" class="btn btn-primary btn-sm btn_report" value="Print" onclick="printDiv();" disabled>
		         </div>  						
	    </div>
	 </div>
</form:form>

  <div style="width: 20%; padding-left: 1%; display: none;" id="divSerachInput">
<input id="searchInput" type="text" style="font-size:12px; font-family: 'FontAwesome',Arial;margin-bottom: 5px;" placeholder="&#xF002; Search Word"  size="35" class="form-control">
</div>
					<div class="col s12" id="divPrint" style="display: none;">
							<div id="divShow" style="display: block;">
							</div>
			<div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
					<span id="ip"></span>
							 <table id="WeaponReport1" class="table no-margin table-striped  table-hover  table-bordered report_print" >
								<thead >
									<tr>
										<th class="tableheadSer">Ser No</th>
										<th >Item Code</th>
										<th >Nomenclature</th>
										<th >Authorisation</th>
										<th id="thLetterId" >Letter No</th>
										<th id="thRemarkId" >Error Correction</th>
										<th  class='lastCol'>Action</th>
										
									</tr>
								</thead>
								<tbody >
								<c:forEach var="item" items="${list}" varStatus="num" >
								<tr>
									<td class="tableheadSer">${num.index+1}</td>
									<td >${item.item_seq_no}</td>
									<td >${item.item_type}</td>
									<td >${item.auth_weapon}</td>
									<td id="thLetterId1" >${item.reject_letter_no}</td>
									<td id="thRemarkId1" >${item.reject_remarks}</td>
									<td id="thAction1" class='lastCol'>${item.id}</td>
								</tr>
				</c:forEach>
								</tbody>
							</table> 
					</div>
					</div>
					
					
		<c:url value="standard_auth_for_weapons1" var="searchUrl" />
    		<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="we_pe_no1">
    			<input type="hidden" name="auth_weapon1" id="auth_weapon1" value="0"/>
    			<input type="hidden" name="we_pe_no1" id="we_pe_no1" value="0"/>
    			<input type="hidden" name="item_seq_no1" id="item_seq_no1" value="0"/>
    			<input type="hidden" name="we_pe1" id="we_pe1" value="0"/>
    			<input type="hidden" name="status1" id="status1" value="0"/>
    		</form:form> 
    						
	<c:url value="updatePSawUrl" var="updateUrl" />
	<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid"  target="result"> 
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
		alert("Please Select WE/PE No");
		$("input#we_pe_no").focus();
		return false;
	} 
      $("#we_pe1").val(r);
   $("#we_pe_no1").val($("#we_pe_no").val());
      $("#auth_weapon1").val($("#auth_weapon").val());
      $("#item_seq_no1").val($("#item_seq_no").val());
      $("#status1").val($("#status").val());
      document.getElementById('searchForm').submit();
   	   
}
	 
var newWin;
function editData(id){	
	var x = screen.width/2 - 1100/2;
    var y = screen.height/2 - 900/2;
   
	newWin = window.open("", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');
	 
	 window.onfocus = function () { 
		 //   newWin.close();
	 }

	document.getElementById('updateid').value=id;
	document.getElementById('updateForm').submit();
}
function closeWindow()
{
	newWin.close();   
}
function deleteData(id){
     $.post("deletePSawUrlAdd?"+key+"="+value, {deleteid : id}).done(function(j) {
    	 alert(j);
			Search();
      }).fail(function(xhr, textStatus, errorThrown) { }); 	
}
	
							
</script>
	
<script>

function checkAuth_amtLength() {
	var auth_weapon= $("input#auth_weapon").val();
	
 	if(auth_weapon.length > 8)
	{
		alert("Please Enter Valid Digit");
		$("input#auth_weapon").val("");
 		return false;
		
	}
	if(auth_weapon != "" && auth_weapon.includes(".")) {
		//var scale_vald1=[] ;
		var auth_weapon1 = auth_weapon.toString().split(".");			
	 	if(auth_weapon1[0].length > 5 || auth_weapon1[1].length > 2 )
		{
	 		alert("Please Enter Valid Digit");
			$("input#auth_weapon").val("");
	 		return false;
		}
	 	
	 }
	else {
		if(auth_weapon.length > 5)
		{
			alert("Please Enter Valid Digit");
			$("input#auth_weapon").val("");
	 		return false;
			
		}
	}
 	return true;
}

function clearDiscription()
{
	document.getElementById("we_pe_no").value="";
	document.getElementById("table_title").value="";
}

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
	    	      	 document.getElementById("table_title").value=""; 
	    	      	  wepetext1.val("");
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
<script>
$(document).ready(function() {
	if('${we_pe1}' != "")
	{
		$("input[name=we_pe][value="+'${we_pe1}'+"]").prop('checked', true);
		$("#we_pe_no").val('${we_pe_no1}');
		getArmByWePeNo('${we_pe_no1}');
		$("div#divwatermark").val('').addClass('watermarked'); 
		watermarkreport(); 
		$("#divPrint").show();
		 $("div#divSerachInput").show();
		document.getElementById("printId").disabled  = false;
		
		 if('${list.size()}' == 0 ){
			 $("div#divSerachInput").hide();
			 document.getElementById("printId").disabled  = true;
			 $("table#WeaponReport1").append("<tr><td colspan='5' style='text-align :center;'>Data Not Available</td></tr>");
		 }	
	}
		
	
	
	$("#searchInput").on("keyup", function() {
 			var value = $(this).val().toLowerCase();
 			$("#WeaponReport1 tbody tr").filter(function() { 
 			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
 			});
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
   					window.opener.getRefreshReport('auth_std_weap',m);
   					window.close('','_parent','');
   				}   				
   			}
		  else if(window.location.href.includes("msg="))
 			{
 				var url = window.location.href.split("?msg=")[0];
 				var m=  window.location.href.split("&msg=")[1];
 				window.location = url;
 				
 				if(m.includes("Updated+Successfully")){
 					window.opener.getRefreshReport('auth_std_weap',m);
 					window.close('','_parent','');
 				}   				
 			}
		}
		catch (e) {
			// TODO: handle exception
		}
	 
});

function clearall()
{		
	document.getElementById('divPrint').style.display='none';
	document.getElementById("printId").disabled = true;
	var tab = $("#WeaponReport1 > tbody");
 	tab.empty();
	$("#searchInput").val("");
	$("#searchInput").hide();
	$("div#divSerachInput").hide();
	//document.location.reload();
 	localStorage.clear();
	localStorage.Abandon();
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
			alert("Please Select WE/PE No");
			$("input#we_pe_no").focus();
			return false;
		} 
	if($("input#nomenClature").val() == "")
	{
		alert("Please enter Nomenclatue(Item Type)");
		$("input#nomenClature").focus();
		return false;
	}
	if($("input#item_seq_no").val() == "")
	{
		alert("Please enter Item Code");
		$("input#item_seq_no").focus();
		return false;
	}
	if($("input#auth_weapon").val() == "" ||$("input#auth_weapon").val() == "0" )
	{
		alert("Please enter Authorisation");
		$("input#auth_weapon").focus();
		return false;
	}		
	return true;
}


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


$(function() {      
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
	        	  document.getElementById("nomenClature").value="";       	 
	        	  wepetext2.focus();
	        	  return false;	             
	          }   	         
	      }, 
	        select: function( event, ui ) {
	        	$(this).val(ui.item.value);    	        	
	        	$.post("getitemnamefromcode?"+key+"="+value, {val : ui.item.value}).done(function(j) {
	    	    	 document.getElementById("nomenClature").value=j;
	    	      }).fail(function(xhr, textStatus, errorThrown) { });       	
	        }  	     
	    });
	  
	  
	  $.ajaxSetup({
  	    async: false
  	});  	  
	  var wepetext1=$("#nomenClature");     
	
	  wepetext1.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
        	type: 'POST',
 	        url: "getitemtype?"+key+"="+value,
	        data: {item_type:document.getElementById('nomenClature').value},
	          success: function( data ) {
	            //response( data );
	            if(data.length > 1){
	        	  var susval = [];
	        	  var length = data.length-1;
	        		 var enc = data[length].columnName1.substring(0,16);
                   for(var i = 0;i<data.length-1;i++){
                    susval.push(dec(enc,data[i].columnName));
	        	  	}
		        	/* var dataCountry1 = susval.join("|");
	            var myResponse = []; 
	            var autoTextVal=wepetext1.val();
				$.each(dataCountry1.toString().split("|"), function(i,e){
					var newE = e.substring(0, autoTextVal.length);
					if (newE.toLowerCase() === autoTextVal.toLowerCase()) {
					//if (e.toLowerCase().includes(autoTextVal.toLowerCase())) {
					  myResponse.push(e);
					  
					}
				});  */     	          
				response( susval ); 
				}
	          }
	        });
	      },
	      minLength: 1,
	      autoFill: true,
	      change: function(event, ui) {
	    	 if (ui.item) { 
	    		 var val = this.value; 	            	
	        	  return true;    	            
	          } else {
	        	  alert("Please Enter Approved Nomenclature");
	        	  wepetext1.val("");
	        	  $("input#std_nomclature").val("");
	        	  document.getElementById("item_seq_no").value="";
	        	 
	        	  wepetext1.focus();
	        	  return false;	             
	          }   	         
	      }, 
	      select: function( event, ui ) {
	        	$(this).val(ui.item.value);    	        	
	        	$.post("getitemcode?"+key+"="+value, {val : ui.item.value}).done(function(j) {
	    	    	 document.getElementById("item_seq_no").value=j;	
	    	      }).fail(function(xhr, textStatus, errorThrown) { }); 	      	
	        }  	     
	    }); 
  }); 	   
</script>

	 
<script>	
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
function Delete1(id){
	document.getElementById('deleteid').value=id;
	document.getElementById('deleteForm').submit();
}

function Update(id){
	document.getElementById('updateid').value=id;
	document.getElementById('updateForm').submit();
} 
</script>

</body>
</html>
