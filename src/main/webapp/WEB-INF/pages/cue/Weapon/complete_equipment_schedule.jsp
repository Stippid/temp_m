<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Upload WE/PE</title>
</head>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/excluded/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 

<script type="text/javascript" src="js/miso/miso_js/jquery-1.12.3.js"></script>
<script src="js/cue/wepe_cce.js" type ="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/cue/update.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>
<script src="js/cue/printAllPages.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>


 <script>
 
$(document).ready(function() {
	if('${ces_cces1}' != "")
	{
		$("input[name=ces_cces][value="+'${ces_cces1}'+"]").prop('checked', true);
		
		$("#ces_cces_no").val('${ces_cces_no1}');
		cceno('${ces_cces_no1}');
		getcceno('${ces_cces1}');
		$("#ces_namem").val('${ces_namem1}');
		$("div#divwatermark").val('').addClass('watermarked'); 
		watermarkreport();
		$("#divPrint").show();
		$("div#divSerachInput").show();
		document.getElementById("printId").disabled = false;	
		
		if('${list.size()}' == 0 ){
			$("div#divSerachInput").hide(); 
			document.getElementById("printId").disabled = true;
			$("table#AttributeReportCCES").append("<tr><td colspan='8' style='text-align :center;'>Data Not Available</td></tr>");
		 }
	}
	
	$("#searchInput").on("keyup", function() {
			var value = $(this).val().toLowerCase();
			$("#AttributeReportCCES tbody tr").filter(function() { 
			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
			});
		});
	
	
	  $('#remarks').keyup(function() {
 	        this.value = this.value.toUpperCase();
 	    });

	$(function() {
		 var wepetext=$("#ces_cces_name1");
		 
		  wepetext.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        type: 'POST',
	 	        url: "getitemtype?"+key+"="+value,
		        data: {item_type:document.getElementById('ces_cces_name1').value},
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
		        	alert("Please Enter Approved Name of CES/CCES");
		        	wepetext.val("");
		        	wepetext.focus();
		        	return false;	             
		          }   	         
		      }, 
		    });
        
		  $.ajaxSetup({
			    async: false
			});
		  
		  var wepetext1=$("#item_seq_no1");
		
		  wepetext1.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
	        	type: 'POST',
	 	        url: "getitemtype?"+key+"="+value,
		        data: {item_type:document.getElementById('item_seq_no1').value},
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
		        	alert("Please Enter Approved Nomenclature Stores");
		        	wepetext1.val("");
		        	wepetext1.focus();
		        	return false;	             
		          }   	         
		      }, 
		    });
      }); 
	
	
	document.getElementById("ces_ccestype").value=$('input:radio[name=ces_cces]:checked').val();
	
	try{
		 if(window.location.href.includes("msg="))
		{
			var url = window.location.href.split("?msg=")[0];
		var m=  window.location.href.split("?msg=")[1];
		window.location = url;
		
		if(m.includes("Updated+Successfully")){
			window.opener.getRefreshReport('ces_wea',m);
			window.close('','_parent','');
		}
		}
	}
	catch (e) {
		// TODO: handle exception
	}
});
</script>
<script>
$(function () {
	$("#efctiv_date").datepicker({
        dateFormat: "dd-mm-yy",    //"yy-mm-dd",
        changeMonth: true,
        changeYear: true
	}).attr('readonly', 'readonly');
    
    $("#expiry_date").datepicker({
        dateFormat: "dd-mm-yy",    //"yy-mm-dd",
        changeMonth: true,
        changeYear: true
	}).attr('readonly', 'readonly');

});
	  	
function checkDate()
{
	  var startDate = document.getElementById("efctiv_date").value;
	  var endDate = document.getElementById("expiry_date").value;
	  var b = startDate.split(/\D/);
	  var c = endDate.split(/\D/);	  
	  
	  if ((Date.parse(c.reverse().join('-')) <= Date.parse(b.reverse().join('-'))))
	  {
		  alert("Expiry Date should be greater than Effective Date");
		  document.getElementById("expiry_date").value = "";
	  }	
}	
</script>
<body>
<form:form name="Miso_cue_we_pe_fe_superForm" id="Miso_cue_we_pe_fe_superForm" action="miso_cue_comp_equip_scheduleAction" method='POST' commandName="miso_cue_comp_equip_scheduleCMD">
   	<div class="container" align="center">
	        	<div class="card">
	          		<div class="card-header"><h5><b>Complete Equipment Schedule</b><br><span style="font-size: 10px;font-size:15px;color:red">(To be entered by Etrc)</span></h5></div>
	          			<div class="card-body card-block cue_text">
	            			<div class="col-md-12">	            					
	              				<div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-6">
						                	<label for="text-input" class=" form-control-label">Type <strong style="color: red;">*</strong></label>
						                </div>
							              <div class="col-12 col-md-6">
			                              <div class="form-check-inline form-check">
			                                <label for="inline-radio1" class="form-check-label">
			                                  <input type="radio" id="inline-radio1" name="ces_cces" value="CES" maxlength="4" class="form-check-input" onchange="getcceno(this.value);clearDescription();">CES
			                                </label>&nbsp;&nbsp;&nbsp;
			                                <label for="inline-radio2" class="form-check-label">
			                                  <input type="radio" id="inline-radio2" name="ces_cces" value="CCES" maxlength="4" class="form-check-input" onchange="getcceno(this.value);clearDescription();">CCES
			                                </label>&nbsp;&nbsp;&nbsp;
			                                <label for="inline-radio3" class="form-check-label ">
			                                  <input type="radio" id="inline-radio3" name="ces_cces" value="ETS" maxlength="4" class="form-check-input" onchange="getcceno(this.value);clearDescription();">ETS
			                                </label>&nbsp;&nbsp;&nbsp;                            
			                              </div>
			                            </div>					              
	                				</div>
	                			</div>
	                			<div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-6">
						                	<label for="text-input" class=" form-control-label">CES/CCES No <strong style="color: red;">*</strong></label>
						                </div>
					                 	<div class="col-12 col-md-6">
							                 <input id="id" type="hidden" value="0">  
							                 <input id="ces_ccestype" type="hidden" value=""> 
		                  					 <input onchange="cceno(this.value);" id="ces_cces_no" name="ces_cces_no"  class="form-control" maxlength="15" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search">
	                					</div>
	                			   </div>
	                			</div>
	                		</div>
	                		<div class="col-md-12">
	                			<div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-6">
	                  						<label class=" form-control-label">Name of CES/CCES <strong style="color: red;">*</strong></label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                  						<input  id="ces_cces_name1"  style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search" class="form-control" autocomplete="off" onchange="ccename(this.value);">
	                  						<input type="hidden" id="hidces_cces_name1Exist"  class="form-control">
	                					</div>
	                					</div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-6">
	                  						<label class=" form-control-label">Item Code </label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                						<input id="cescodehidden" type="text" name="ces_cces_name" maxlength="8" class="form-control" readonly="readonly">
	                					</div>
	                				</div>
	              				</div>
	            			</div>
	            			<div class="col-md-12">		 
	  						  <div class="col-md-6">
              					<div class="row form-group"> 
                					<div class="col col-md-6">                					
                  						<label for="text-input" class=" form-control-label">Effective Date <strong style="color: red;">*</strong></label>
                					</div>
                					<div class="col-12 col-md-6">
                  						<input id="efctiv_date" name="efctiv_date" placeholder="dd-MM-yyyy" maxlength="10" class="form-control" onchange="checkDate()" >
									</div>
  								</div>
	  						</div>
	  						<div class="col-md-6">
  								<div class="row form-group">  								
                					<div class="col col-md-6">
                  						<label for="text-input" class=" form-control-label">Expiry Date <strong style="color: red;">*</strong> </label>
                					</div>
                					<div class="col-12 col-md-6">
                  						<input id="expiry_date" name="expiry_date" placeholder="dd-MM-yyyy" maxlength="10" class="form-control" onchange="checkDate()" >
									</div>
  								</div>
  							</div>	  								
	  		              </div>
	            		<div class="col-md-12"><span class="line"></span></div>          		
						<div class="col-md-12">	              					
	              			<div class="col-md-6">
	              			 	<div class="row form-group"> 
                					<div class="col col-md-6">
                  						<label for="" class="form-control-label">Nomenclature <strong style="color: red;">*</strong></label>
                					</div>
                				 	<div class="col-12 col-md-6">
                  						<input  id="item_seq_no1"  class="form-control" onchange="ccenameforseq(this.value);" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search">
									</div>
	  							</div> 
	  						</div>
  							<div class="col-md-6">
              					<div class="row form-group">
                					<div class="col col-md-6">
                  						<label class=" form-control-label">Item Code </label>
                					</div>
                					<div class="col-12 col-md-6">
                						<input id="item_seq_nohidden" type="text" name="item_seq_no" maxlength="8" class="form-control" readonly="readonly">
                					</div>
                				</div>
              				</div>
	  				     </div>
	  					<div class="col-md-12">
	  						<div class="col-md-6">
	  							<div class="row form-group">
					                <div class="col col-md-6">
					                  <label class=" form-control-label">Authorisation <strong style="color: red;">*</strong></label>
					                </div>
					                <div class="col-12 col-md-6">					                
					               <input  id="auth_proposed" name="auth_proposed"  class="form-control" maxlength="8" autocomplete="off" onkeypress="return isNumberKey(event,this);"
					               onchange="return checkAuth_amtLength()">					               
					               
					                </div>
					            </div>	  								
	  						</div>  
	  						<div class="col-md-6">
	  							<div class="row form-group">
					                <div class="col col-md-6">
					                  <label class=" form-control-label">Remarks</label>
					                </div>
					                <div class="col-12 col-md-6">
					                	<textarea class="form-control char-counter1" id ="remarks" name="remarks" maxlength="255" ></textarea>
					                </div>
					            </div>	  								
	  						</div>	  						
	  					</div> 			
	  			</div>
	  		<div class="card-footer" align="center">
				<input type="reset" class="btn btn-success btn-sm" value="Clear" onclick="clearAll();">   
           		<input type="submit" class="btn btn-primary btn-sm" value="Save" onclick="return existsave();">
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
		<table id="AttributeReportCCES" class="table no-margin table-striped  table-hover  table-bordered  report_print" >
			 <thead >
				<tr> 
					<th class="tableheadSer">Ser No</th>
					<th >Name of CES/CCES</th>				
					<th >Effective Date</th>
					<th >Expiry Date</th>				 
					<th >Nomenclature Stores</th>
					<th >Proposed Auth</th>
					<th >Remarks</th>
					<th class='lastCol'>Action</th>
				</tr>
			</thead> 
			<tbody>
			<c:forEach var="item" items="${list}" varStatus="num" >
				<tr>
					<td class="tableheadSer">${num.index+1}</td>
					<td >${item.ces_namem}</td>
					<td >${item.efctiv_date}</td>
					<td >${item.expiry_date}</td>
					<td >${item.sub_item_seq}</td>
					<td >${item.auth_proposed}</td>
					<td >${item.remarks}</td>
					<td id="thAction1" class='lastCol'>${item.id}</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>	
	</div>
</body>
<c:url value="miso_cue_comp_equip_schedule1" var="searchUrl" />
  		<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="prf_group1">
  			<input type="hidden" name="ces_namem1" id="ces_namem1" value="0"/>
  			<input type="hidden" name="ces_cces1" id="ces_cces1" value="0"/>
  			<input type="hidden" name="ces_cces_no1" id="ces_cces_no1" value="0"/>
  			<input type="hidden" name="efctiv_date1" id="efctiv_date1" value="0"/>
  			<input type="hidden" name="expiry_date1" id="expiry_date1" value="0"/>
  			<input type="hidden" name="sub_item_seq1" id="sub_item_seq1" value="0"/>
  			<input type="hidden" name="auth_proposed1" id="auth_proposed1" value="0"/>
  			<input type="hidden" name="remarks1" id="remarks1" value="0"/>
  			<input type="hidden" name="status1" id="status1" value="0"/>
  		</form:form>
<c:url value="updatecceUrl" var="updateUrl" />
	<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid"  target="result">
		<input type="hidden" name="updateid" id="updateid" value="0"/>
	</form:form>
	
	
<script>
function Search(){
   var r =  $('input:radio[name=ces_cces]:checked').val();	
	  if(r == undefined)
	  {		 
		    alert("Please select CES Type");
		    $('input:radio[name=ces_cces]:checked').focus();
			return false;
	  } 
	  
  	 if($("input#ces_cces_no").val() == "")
	 {
		 alert("Please enter CES/CCES No");
		 $("input#ces_cces_no").focus();
		 return false;
	 } 
  	var ces_cces1 = $("input[name='ces_cces']:checked").val();
    $("#ces_cces1").val(ces_cces1);ces_cces_no
    $("#ces_cces_no1").val($("#ces_cces_no").val());
    $("#ces_namem1").val($("#ces_namem").val());
	$("#efctiv_date1").val($("#efctiv_date").val());
    $("#expiry_date1").val($("#expiry_date").val());
    $("#sub_item_seq1").val($("#sub_item_seq").val());
	$("#auth_proposed1").val($("#auth_proposed").val());
    $("#remarks1").val($("#remarks").val());
    $("#status1").val($("#status").val());
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
	
	$.post("deletecceUrlAdd?"+key+"="+value, {deleteid : id}).done(function(j) {
		alert(j);
		Search();
	  }).fail(function(xhr, textStatus, errorThrown) { }); 	
}
function clearDescription(){
	 document.getElementById('ces_cces_no').value = "";
}	

</script>

<script type="text/javascript">
function printDiv() 
{
	var printLbl = ["Type :", "CES/CCES No :", "Name of CES/CCES :", "Item Code :", "Effective Date :", "Expiry Date :"];
	var printVal = [$('input:radio[name=ces_cces]:checked').val(),document.getElementById('ces_cces_no').value,document.getElementById('ces_cces_name1').value,document.getElementById('cescodehidden').value,document.getElementById('efctiv_date').value,document.getElementById('expiry_date').value];
	printDivOptimize('divPrint','COMPLETE EQUIPMENT SCHEDULE',printLbl,printVal,"select#status");
}

</script>
<script type="text/javascript">
function clearAll()
{
	document.getElementById('divPrint').style.display='none';
	document.getElementById("printId").disabled = true;
	var tab = $("#AttributeReportCCES");
 	tab.empty(); 	
 	$("#searchInput").val("");
 	$("#searchInput").hide();
 	localStorage.clear();
	localStorage.Abandon();
}

function isValid()
{  	  
	  var r =  $('input:radio[name=ces_cces]:checked').val();	
	  if(r == undefined)
	  {		 
		    alert("Please select CES Type");
		    $('input:radio[name=ces_cces]:checked').focus();
			return false;
	  } 
  	 if($("input#ces_cces_no").val() == "")
	 {
		 alert("Please enter CES/CCES No");
		 $("input#ces_cces_no").focus();
		 return false;
	 }
	return true;
}

function checkAuth_amtLength() {
	var auth_weapon= $("input#auth_proposed").val();
	
 	if(auth_weapon.length > 8)
	{
		alert("Please Enter Valid Digit");
		$("input#auth_proposed").val("");
 		return false;
		
	}
	if(auth_weapon != "" && auth_weapon.includes(".")) {
		var auth_weapon1 = auth_weapon.toString().split(".");			
	 	if(auth_weapon1[0].length > 5 || auth_weapon1[1].length > 2 )
		{
	 		alert("Please Enter Valid Digit");
			$("input#auth_proposed").val("");
	 		return false;
		}
	 	
	 }
	else {
		if(auth_weapon.length > 5)
		{
			alert("Please Enter Valid Digit");
			$("input#auth_proposed").val("");
	 		return false;
			
		}
	}
 	return true;
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

</html>