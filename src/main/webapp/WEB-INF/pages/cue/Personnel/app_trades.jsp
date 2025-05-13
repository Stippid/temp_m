<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

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

</head>
<body>
<form:form action="app_tradesAction" method="post"  class="form-horizontal" commandName="app_tradesActionCMD"> 
	<div class="animated fadeIn">
		<div class="">
	    	<div class="container" align="center">
	        	<div class="card">
	          		<div class="card-header"><h5><b>APPT/TRADE and rank</b><br><span style="font-size:12px;color:red">(To be entered by MISO)</span></h5> </div>
	          			<div class="card-body card-block cue_text">
								<div class="col-md-12">
								  <div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-6" style=" max-width: 245px;">
	                  						<label class=" form-control-label">Category <strong style="color: red;">*</strong></label>	
	                					</div>
	                					<div class="col col-md-6">
	                  						<select name="parent_code" id="parent_code" class="form-control" onchange="clearData()" >
	                  						  	<option value="Select">--Select--</option>	
	                  						  	<c:forEach var="item" items="${getcategoryListFinal}" varStatus="num" >
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
	                					<div class="col col-md-6" style=" max-width: 245px;">
	                  						<label class=" form-control-label">Type of Category <strong style="color: red;">*</strong></label>	
	                					</div>
	                					<div class="col col-md-6">
    		    							<label for="app1">
    		    							<input type="radio" id="app1" name="level_in_hierarchy" value="APPOINTMENT" maxlength="12" onclick="return clearDescription('APPOINTMENT');"> 
											    Appt/Trades
											</label>&nbsp;&nbsp;&nbsp;
											<label for="rank1" >
											    <input type="radio" id="rank1" name="level_in_hierarchy"  value="RANK" maxlength="12" onchange="clearDescription()"/>
											    Rank
											</label>
										</div>
									</div>
									</div>
								 </div>
								<div class="col-md-12">
								    <div class="col-md-6">
									<div class="row form-group" id="dvdescription" style="display:none;">
                						<div class="col col-md-6" style=" max-width: 245px;">
                  							<label class=" form-control-label" id="lbl_rank"></label> <strong style="color: red;">*</strong>
                						</div>
                						<div class="col-12 col-md-6">
                  							<input type="text" id="description" name="description" maxlength="50" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search" class="form-control" autocomplete="off" onkeypress="return blockSpecialChar(event)">
                						</div>
              						</div>  
								</div>
							</div>
							<div class="col-md-12">	
		  						<div class="col-md-6">
			              			<div class="row form-group">	              			 	
		               					<div class="col col-md-6" style=" max-width: 245px;">
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
						<input type="reset" class="btn btn-success btn-sm" value="Clear" onclick="clearall()">   
	              		<input type="submit" id="submit" name="submit" class="btn btn-primary btn-sm" onclick="return isValid()"  value="Save" >
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
					<table id="AppTradeReport" class="table no-margin table-striped  table-hover  table-bordered report_print" >
						<thead>
							<tr>
								<th class="tableheadSer">Ser No</th>	
								<th >Category</th>
								<th >Level In Hierarchy</th>
								<th >Description</th>
								<th id="thLetterId" >Letter No</th>
								<th id="thRemarkId" >Error Correction</th>
								<th  class='lastCol'>Action</th>
							</tr>
						</thead> 
						<tbody>
							<c:forEach var="item" items="${list}" varStatus="num" >
									<tr>
										<td class="tableheadSer">${num.index+1}</td>
										<td >${item.label}</td>
										<td >${item.level_in_hierarchy}</td>
										<td >${item.description}</td>
										<td id="thLetterId1" >${item.reject_letter_no}</td>
										<td id="thRemarkId1" >${item.reject_remarks}</td>
										<td id="thAction1"   class='lastCol'>${item.id}</td>
									</tr>
							</c:forEach>
						</tbody>
					</table>
					</div>
			 </div>
			
			<c:url value="app_trades1" var="searchUrl" />
    			<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="parent_code1">
    			<input type="hidden" name="parent_code1" id="parent_code1" value="0"/>
    			<input type="hidden" name="level_in_hierarchy1" id="level_in_hierarchy1" value="0"/>
    			<input type="hidden" name="description1" id="description1" value="0"/>
    			<input type="hidden" name="status1" id="status1" value="0"/>
    		</form:form> 
    		 
	<c:url value="updateAPPTUrl" var="updateUrl" />
	<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid" target="result">
		<input type="hidden" name="updateid" id="updateid" value="0"/>
	</form:form>

<script>
function Search(){
	if($("select#parent_code").val() == "Select")
	{
		alert("Please Select Category");
		return false;
	}
	var level_in_hierarchy2 = $("input[name='level_in_hierarchy']:checked").val();
	if(level_in_hierarchy2 == undefined)
	{		 
	   alert("Please select Type of Category");
	   $('input:radio[name=level_in_hierarchy]:checked').focus();
	return false;
	} 
	
	$("#level_in_hierarchy1").val(level_in_hierarchy2);
	$("#parent_code1").val($("#parent_code").val());
    $("#description1").val($("#description").val());
    $("#status1").val($("#status").val());
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

	 $.post("deleteAPPTUrlAdd?"+key+"="+value, {deleteid : id}).done(function(j) {
   	 alert(j);
		 Search();
     }).fail(function(xhr, textStatus, errorThrown) { }); 					
}

</script>

<script>
function printDiv() 
{
	var printLbl = ["Category :", "Type of Category :"];
	var printVal = [$('select#parent_code option:selected').text(),$('input:radio[name=level_in_hierarchy]:checked').val()];
	printDivOptimize('divPrint','Appointment/Trade Details',printLbl,printVal,"select#status");
}
</script>

<script>
function isValid()
{	
	if($("select#parent_code").val() == "Select")
	{
		alert("Please Select Category");
		return false;
	} 
	var level_in_hierarchy2 = $("input[name='level_in_hierarchy']:checked").val();
	if(level_in_hierarchy2 == undefined)
	{		 
	   alert("Please select Type of Category");
	   $('input:radio[name=level_in_hierarchy]:checked').focus();
	return false;
	} 
	var level_in_hierarchy = document.getElementsByName('level_in_hierarchy');
   	var genValue = false;

    for(var i=0; i<level_in_hierarchy.length;i++){
       if(level_in_hierarchy[i].checked == true){
           genValue = true;    
       }
   }
   if(!genValue){
       alert("Please Select Description");
       return false;
   }
   	if($("input#description").val() == "")
	{
		alert("Please Enter the Description");
		return false;
	}
	return true;
}
</script>

<script>
var testdesc;

$(document).ready(function() {
	if('${parent_code1}' != ""){
		$("select#parent_code").val('${parent_code1}');
   		$("input[name=level_in_hierarchy][value="+'${level_in_hierarchy1}'+"]").prop('checked', true);
   		$("#description").val('${description1}');
   		$("div#divwatermark").val('').addClass('watermarked'); 
   		watermarkreport();
   		$("div#divSerachInput").show();
   		$("#divPrint").show();
   		document.getElementById("printId").disabled = false;	
   		if('${list.size()}' == 0 ){
   		 $("div#divSerachInput").hide();
   		document.getElementById("printId").disabled = true;	
   	     $("table#AppTradeReport").append("<tr><td colspan='5' style='text-align :center;'>Data Not Available</td></tr>");
   	 }
   	}
	
	$("#searchInput").on("keyup", function() {
			var value = $(this).val().toLowerCase();
			$("#AppTradeReport tbody tr").filter(function() { 
			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
			});
		});
	
	 $('#remarks').keyup(function() {
	        this.value = this.value.toUpperCase();
	    });
	
	$.ajaxSetup({
	    async: false
	});
	
	$("input[name='level_in_hierarchy']").click(function() {
	  if($(this).val()=="APPOINTMENT")
	   {    
		  testdesc = $(this).val();
		 $("label#lbl_rank").text("Description of Appt/ Trades");		  
	       $("div#dvdescription").show();	      		
	   }
	   else
	   {
		   testdesc = $(this).val();
		   $("label#lbl_rank").text("Description of Rank");
		   $("div#dvdescription").show();
	   }		
		
	 getdescription($(this).val());
		  
	}); 
	
	$.ajaxSetup({
	    async: false
	});
	
 
	 if('${level_in_hierarchy1}'=="APPOINTMENT")
	   {    
		  testdesc = $(this).val();
		 $("label#lbl_rank").text("Description of Appt/ Trades");		  
	       $("div#dvdescription").show();	
	       getdescription('${level_in_hierarchy1}');
	   }
	   else
	   {
		   testdesc = $(this).val();
		   $("label#lbl_rank").text("Description of Rank");
		   $("div#dvdescription").show();
		   getdescription('${level_in_hierarchy1}');
	   }
	 if('${level_in_hierarchy1}'=="")
		 {
		 $("div#dvdescription").hide();
		 
		 }

	try{
		 if(window.location.href.includes("msg="))
		{
			var url = window.location.href.split("?msg=")[0];
			var m=  window.location.href.split("&msg=")[1];
			window.location = url;
			
			if(m.includes("Updated+Successfully")){
				window.opener.getRefreshReport('apptrade_per',m);
				window.close('','_parent','');
			}
		} 
	}
	catch (e) {
		// TODO: handle exception
	}
}); 

function getdescription(val){
 if(val != ""){
	 var wepetext1=$("#description");
	var parent_code = $("#parent_code").val();
	
	  wepetext1.autocomplete({
 	      source: function( request, response ) {
	        $.ajax({ 
	        type: 'POST',
	        url: "getdesciption?"+key+"="+value,
	        data: {val : val,parent_code : parent_code,description : document.getElementById('description').value },
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
					//if (newE.toLowerCase() === autoTextVal.toLowerCase())encodeURIComponent {
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
	      }, 
	      
	    });
 	}
}

</script>
<script>
function blockSpecialChar(e){    
	$('#description').keyup(function() {
		this.value = this.value.toUpperCase();
	});	   
}

function clearall()
{		
	document.getElementById('divPrint').style.display='none';
	document.getElementById("printId").disabled = true;
	
	var tab = $("#AppTradeReport");
 	tab.empty();

	if("input[name='level_in_hierarchy']"==null )
	{	
		 $("div#dvdescription").show();	     
	}
else
	{
	 $("div#dvdescription").hide();   
	}
	  $("#searchInput").val("");
	$("div#divSerachInput").hide();  
	//document.location.reload();
 	localStorage.clear();
	localStorage.Abandon();
}

function clearData()
{	
	document.getElementById("app1").checked = false;
	document.getElementById("rank1").checked = false;
	
	if("input[name='level_in_hierarchy']"==null )
	{	
		 $("div#dvdescription").show();	      
	}
else
	{
	 $("div#dvdescription").hide();   
	}	
}

function clearDescription(){
	 document.getElementById('description').value = "";
}
</script>


</body>
</html>