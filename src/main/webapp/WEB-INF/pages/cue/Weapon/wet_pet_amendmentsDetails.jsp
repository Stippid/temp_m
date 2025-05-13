<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
	var printLbl = ["Type :", "WET/PET/FET No :", "Table Title :"];
	var printVal = [$('input:radio[name=wet_type]:checked').val(),document.getElementById('wet_pet_no').value,document.getElementById('amdt_title_wet_pet').value];
	printDivOptimize('divPrint','CAPTURE WET/PET DATA',printLbl,printVal,"select#status");
}
</script>

<form:form name="wet_pet_amendDetail" id="wet_pet_amendDetail" action="wet_pet_amendDetailAct" method='POST' commandName="wet_pet_amendDetailCmd" >
	 	<div class="container" align="center">
        	<div class="card">
        		<div class="card-header"> <h5><b>CAPTURE WET/PET DATA</b><br><span style="font-size: 10px;font-size:15px;color:red">(To be entered by Line Dte)</span></h5></h5></div>        		
          		<div class="card-body card-block cue_text">
           			<div class="col-md-12">	            					
             				<div class="col-md-6">
             					<div class="row form-group">
               					<div class="col col-md-4">
				                	<label for="text-input" class=" form-control-label">Type of Document <strong style="color: red;">*</strong></label>
				                </div>
               					<div class="col-12 col-md-8">
		                             <div class="form-check-inline form-check">
		                                <label for="inline-radio1" class="form-check-label ">
		                                  <input type="radio" id="wet1" name="wet_type" value="WET" maxlength="4" onchange="clearDiscription();" class="form-check-input">WET
		                                </label>&nbsp;&nbsp;&nbsp;
		                                <label for="inline-radio2" class="form-check-label ">
		                                  <input type="radio" id="pet1" name="wet_type" value="PET" maxlength="4" onchange="clearDiscription();" class="form-check-input">PET
		                                </label>&nbsp;&nbsp;&nbsp;
		                                <label for="inline-radio2" class="form-check-label ">
		                                  <input type="radio" id="fet1" name="wet_type" value="FET" maxlength="4" onchange="clearDiscription();" class="form-check-input">FET
		                                </label>&nbsp;&nbsp;&nbsp;       
		                             </div>
					              </div>							              
               				</div>
               			</div>               			
               			<div class="col-md-6">
             				<div class="row form-group">
               					<div class="col col-md-4">
                 					<label class=" form-control-label">WET/PET No <strong style="color: red;">*</strong></label>
               					</div>
               					<div class="col-12 col-md-6">
                 					<input name="wet_pet_no"  id="wet_pet_no" class="form-control" maxlength="50" autocomplete="off" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search">
               					</div>
               				</div>
             			</div>
           			</div>
           			<div class="col-md-12">	
           			 <div class="col-md-6">
              					<div class="row form-group">
                					<div class="col col-md-4">
                  						<label class=" form-control-label">WET/PET Title</label>
                					</div>
                					<div class="col-12 col-md-6">
                  						<textarea id="amdt_title_wet_pet" name="" rows="3" class="form-control" readonly="readonly"></textarea>
                					</div>
              					</div>
							</div>
  				</div>
  				
  				 <div class="col-md-12"><span class="line"></span></div>
  					<div class="col-md-12">	
  						<div class="col-md-6">
  							<div class="row form-group">
				                <div class="col col-md-4">
				                  	<label class=" form-control-label">Item/Eqpt Name <strong style="color: red;">*</strong></label>
				                </div>
				                <div class="col-12 col-md-6">
				                	<input class="form-control" id="item_name"  autocomplete="off" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search"> 
				                </div>
				            </div>	  								
  						</div>
  						<div class="col-md-6">
	              			 	<div class="row form-group"> 
	                					<div class="col col-md-4">
	                  						<label  class=" form-control-label" >Item Code <strong style="color: red;">*</strong></label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                  						<input id="item_seq_no" name="item_seq_no" maxlength="8" class="form-control" autocomplete="off" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search">
										</div>
	  								</div> 
	  						</div>
	  					</div>
	  					<div class="col-md-12">	
	  						<div class="col-md-6">
	              			 	<div class="row form-group"> 
	                					<div class="col col-md-4">
	                  						<label  class=" form-control-label" >Related CES/CCES No</label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                  						<input id="ces_cces_no" name="" class="form-control" readonly="readonly">
										</div>
	  								</div> 
	  						</div>
  						<div class="col-md-6">
							<div class="row form-group">           					
             					<div class="col col-md-4">
               						<label class=" form-control-label">Authorisation <strong style="color: red;">*</strong></label>
             					</div>
             					<div class="col-12 col-md-6">
               						<input id="authorization" name="authorization" class="form-control" maxlength="8" 
               						onkeypress="return isNumberKey(event,this);" onchange="return checkAuth_amtLength()">
             					</div>
             				</div>	
             			</div>
  					</div>
	  					<div class="col-md-12">	
  						<div class="col-md-6">
							<div class="row form-group">
             					<div class="col col-md-4">
               						<label class=" form-control-label">Remarks</label>
             					</div>
             					<div class="col-12 col-md-6">
               						<textarea rows="2" id="remarks" name="remarks" maxlength="255" class="form-control char-counter1"></textarea>
             					</div>
             				</div>	
             			</div>
  					</div> 		
  				</div>
  				<div class="card-footer" align="center">
					<input type="reset" class="btn btn-success btn-sm" value="Clear" onclick="clearAll();">   
             			<input  type="submit" class="btn btn-primary btn-sm" value="Save" onclick="return isValid();">
             			<i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm" onclick="Search();" value="Search">
             			<i class="fa fa-print"></i><input type="button" id="printId" class="btn btn-primary btn-sm btn_report" value="Print" onclick="printDiv();" disabled>
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
				
						<table id="AttributeReportnew" class="table no-margin table-striped  table-hover  table-bordered  report_print"  >
							 <thead>
								<tr>
								<th class="tableheadSer">Ser No</th>
								<th>WET/PET/FET No</th>
								<th>Item Code</th>
								<th>Item/Eqpt Name</th>
								<th>Authorisation</th>
								<th>Remarks</th>
								<th id="thLetterId">Letter No</th>
								<th id="thRemarkId" >Error Correction</th>
								<th class='lastCol'>Action</th>
								</tr>
							</thead>
							<tbody  >
							 <c:forEach var="item" items="${list}" varStatus="num" >
								<tr>
									<td class="tableheadSer">${num.index+1}</td>
									<td>${item.wet_pet_no}</td>
									<td>${item.item_seq_no}</td>
									<td>${item.item_type}</td>
									<td>${item.authorization}</td>
									<td>${item.remarks}</td>
									<td  id="thLetterId1" >${item.reject_letter_no}</td>
									<td id="thRemarkId1">${item.reject_remarks}</td>
									<td id="thAction1" class='lastCol'>${item.id}</td>
								</tr>
				</c:forEach>
							</tbody>
						</table>
						</div>
					</div>	
		
		<c:url value="wet_pet_amendDetail1" var="searchUrl" />
    		<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="wet_pet_no1">
    			<input type="hidden" name="wet_pet_no1" id="wet_pet_no1" value="0"/>
    			<input type="hidden" name="item_seq_no1" id="item_seq_no1" value="0"/>
    			<input type="hidden" name="authorization1" id="authorization1" value="0"/>
    			<input type="hidden" name="wet_type1" id="wet_type1" value="0"/>
    		</form:form>
    		
<c:url value="updateWetPetAmendmentsUrl" var="updateUrl" />
	<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid" target="result">
		<input type="hidden" name="updateid" id="updateid" value="0"/>
	</form:form>
	
<script>

function checkAuth_amtLength() {
	var auth_weapon= $("input#authorization").val();
	
 	if(auth_weapon.length > 8)
	{
		alert("Please Enter Valid Digit");
		$("input#authorization").val("");
 		return false;
		
	}
	if(auth_weapon != "" && auth_weapon.includes(".")) {
		var auth_weapon1 = auth_weapon.toString().split(".");			
	 	if(auth_weapon1[0].length > 5 || auth_weapon1[1].length > 2 )
		{
	 		alert("Please Enter Valid Digit");
			$("input#authorization").val("");
	 		return false;
		}
	 	
	 }
	else {
		if(auth_weapon.length > 5)
		{
			alert("Please Enter Valid Digit");
			$("input#authorization").val("");
	 		return false;
			
		}
	}
 	return true;
}

function Search(){
	 var r =  $('input:radio[name=wet_type]:checked').val();	
	  if(r == undefined)
	  {		 
		    alert("Please select Document");
		    $('input:radio[name=wet_type]:checked').focus();
			return false;
	  }  	  
  	
	  	if($("input#wet_pet_no").val() == "")
		{
			alert("Please enter WET/PET/FET Document No");
			$("input#wet_pet_no").focus();
			return false;
		}
    var wet_type1 = $("input[name='wet_type']:checked").val();
    $("#wet_type1").val(wet_type1);
	$("#wet_pet_no1").val($("#wet_pet_no").val());
    $("#item_seq_no1").val($("#item_seq_no").val());
    $("#authorization1").val($("#authorization").val());
    document.getElementById('searchForm').submit();
 	   
}	
	
var newWin;
function editData(id){	
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
	$.post("deleteWetPetAmendmentsUrlAdd?"+key+"="+value, {deleteid : id}).done(function(j) {
	   	 alert(j);
			 Search();
	}).fail(function(xhr, textStatus, errorThrown) { }); 
}
</script>
	
<script>

function clearDiscription()
{
	document.getElementById("wet_pet_no").value="";
	document.getElementById("amdt_title_wet_pet").value="";	
	}

$(document).ready(function() {
	if('${wet_type1}' != "")
	{
		$("input[name=wet_type][value="+'${wet_type1}'+"]").prop('checked', true);
		$("#wet_pet_no").val('${wet_pet_no1}');
		getarmvalue('${wet_pet_no1}');
		$("div#divwatermark").val('').addClass('watermarked'); 
		watermarkreport();
		$("#divPrint").show();
		$("div#divSerachInput").show();
		document.getElementById("printId").disabled = false;
		
		if('${list.size()}' == 0 ){
			$("div#divSerachInput").hide(); 
			document.getElementById("printId").disabled = true;
			$("table#AttributeReportnew").append("<tr><td colspan='6' style='text-align :center;'>Data Not Available</td></tr>");
		 }
		}
	 
	$("#searchInput").on("keyup", function() {
			var value = $(this).val().toLowerCase();
			$("#AttributeReportnew tbody tr").filter(function() { 
			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
			});
		});
	
	  $('#remarks').keyup(function() {
	        this.value = this.value.toUpperCase();
	    });

	 $.ajaxSetup({
	        async: false
	    });
	    var r =  $('input:radio[name=wet_type]:checked').val();	
	   	if(r != undefined)
	    	getWePeNoByType(r);	
	
	$('input[type=radio][name=wet_type]').change(function() {		
		var radio_doc = $(this).val();		
		getWePeNoByType(radio_doc);	
	});
	
	try{
		if(window.location.href.includes("count="))
		{
			var url = window.location.href.split("?count=")[0];
			var m=  window.location.href.split("&msg=")[1];
			window.location = url;
			
			if(m.includes("Updated+Successfully")){
				window.opener.getRefreshReport('wet_pet_amend_weap',m);
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
		
 	     $.post("getWetPetFetByUnit_type?"+key+"="+value, {val : val}).done(function(j) {
 	    	if(j !="" && j!=null){
 				document.getElementById("amdt_title_wet_pet").value=j[0].unit_type;
 				}
 				else{
 					document.getElementById("amdt_title_wet_pet").value="";					
 				}	
 	      }).fail(function(xhr, textStatus, errorThrown) { }); 	
	  }
	else
	{
		document.getElementById("amdt_title_wet_pet").value="";
	}
}

function getWePeNoByType(radio_doc)
{
	 if(radio_doc != ""){
	 var wepetext=$("#wet_pet_no");
	
	  wepetext.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
	        type: 'POST',
	  	    url: "getWetPetFetNodata?"+key+"="+value,
	        data: {val : radio_doc,wet_pet_no:document.getElementById('wet_pet_no').value},
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
	        	wepetext.focus();
	        	document.getElementById("amdt_title_wet_pet").value="";
	        	return false;	             
	          }   	         
	      } ,
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
   	        	  document.getElementById("item_name").value="";  
   	        	document.getElementById("ces_cces_no").value=""; 
   	        	  wepetext2.focus();
   	        	  return false;	             
   	          }   	         
   	      }, 
   	        select: function( event, ui ) {
   	        	$(this).val(ui.item.value); 
   	 	     $.post("getitemnamefromcode?"+key+"="+value, {val : ui.item.value}).done(function(j) {
   	 	    		alert(j)
	        		document.getElementById("item_name").value=j;
   	 	      }).fail(function(xhr, textStatus, errorThrown) { });      	
   	        	
   	        }  	     
   	    });
   	  
   	  
   	  $.ajaxSetup({
     	    async: false
     	});  	  
   	  var wepetext1=$("#item_name");     
   	
   	  wepetext1.autocomplete({
   	      source: function( request, response ) {
   	        $.ajax({
        	type: 'POST',
 	        url: "getitemtype?"+key+"="+value,
   	        data: {item_type:document.getElementById('item_name').value},
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
   	    		 var val = this.value; 	            	
   	        	  return true;    	            
   	          } else {
   	        	  alert("Please Enter Approved Item Name");
   	        	  wepetext1.val("");
   	        	  $("input#std_nomclature").val("");
   	        	  document.getElementById("item_seq_no").value="";
   	        	document.getElementById("ces_cces_no").value="";
   	        	 
   	        	  wepetext1.focus();
   	        	  return false;	             
   	          }   	         
   	      }, 
   	      select: function( event, ui ) {
   	        	$(this).val(ui.item.value);    	        	
   	        	$.post("getitemcode?"+key+"="+value, {val : ui.item.value}).done(function(j) {
   	   	 	    	document.getElementById("item_seq_no").value=j;	
   	   	 	      }).fail(function(xhr, textStatus, errorThrown) { }); 
   	        	
   	        	$.post("getCesnoByCesName?"+key+"="+value, {val :  document.getElementById("item_seq_no").value}).done(function(j) {
   	     	 	 	if(j[0] == undefined || j[0] == "" || j[0] == null || j[0] == '' ){
   	         			document.getElementById("ces_cces_no").value="";
   	         		}   	        			
   	         		else
   	  		 			document.getElementById("ces_cces_no").value=j[0].ces_cces_no;		
   	     	 	 	
   	     	 	}).fail(function(xhr, textStatus, errorThrown) { }); 
   	        	
   	        }  	     
   	    });
  });
   
function getItemCode(val)
{
    if(val != "" && val != null && val !="null")
    {
   	
   	  $.post("getitemcode?"+key+"="+value, {val : val}).done(function(j) {
   		  document.getElementById("item_seq_no").value=j[0];
   	   }).fail(function(xhr, textStatus, errorThrown) { }); 	
    }
    else
    {
     alert("Please enter Item/Eqpt Name");
     document.getElementById("item_seq_no").value="";
    }
}
  
function isValid()
{  	  
	  var r =  $('input:radio[name=wet_type]:checked').val();	
 if(r == undefined)
 {		 
    alert("Please select Document");
    $('input:radio[name=wet_type]:checked').focus();
	return false;
 }  	  
	
 	if($("input#wet_pet_no").val() == "")
{
	alert("Please enter WET/PET/FET Document No");
	$("input#wet_pet_no").focus();
	return false;
}
if($("input#item_name").val() == "")
{
	alert("Please enter Item/Eqpt Name");
	$("input#item_name").focus();
	return false;
}

if($("input#authorization").val() == "")
{
	alert("Please enter Authorisation");
	$("input#authorization ").focus();
	return false;
}

return true;
}
  
function clearAll()
{
  document.getElementById('divPrint').style.display='none';
  document.getElementById("printId").disabled = true;
 	var tab = $("#AttributeReportnew");
  	tab.empty();
  	$("#searchInput").val("");
  	$("#searchInput").hide();
 //document.location.reload();
	localStorage.clear();
	localStorage.Abandon();
}
  
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
</script>
