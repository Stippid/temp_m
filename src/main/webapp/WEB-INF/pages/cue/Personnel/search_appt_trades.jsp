<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

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
<script src="js/JS_CSS/jquery.dataTables.min.js"></script>
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
	var printLbl = ["Category :"];
	var printVal = [$('select#parent_code option:selected').text()];
	printDivOptimize('divPrint','SEARCH APPT TRADES',printLbl,printVal,"select#status");
}
</script>

</head>
<body>
<form:form action="AttributeReportSearchRANKCategory" method="post"  class="form-horizontal" commandName="app_tradesActionCMD"> 
	<div class="animated fadeIn">
		<div class="">
	    	<div class="container" align="center">
	        	<div class="card">
	        		<div class="card-header"> <h5><b>SEARCH APPT TRADES</b><br><span style="font-size:12px;color:red">(To be approved by miso)</span></h5></div>
	          			<div class="card-body card-block cue_text">
								<div class="col-md-12">
								<div class="col-md-7">
	              					<div class="row form-group">
	                					<div class="col col-md-6">
	                  						<label class=" form-control-label">Category <strong style="color: red;">*</strong></label>	
	                					</div>
	                					<div class="col col-md-6">
	                  						<select name="parent_code" id="parent_code" class="form-control" onchange="clearData()">
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
								  <div class="col-md-7">
								  	<div class="row form-group">
	                					<div class="col col-md-6">
	                  						<label class=" form-control-label">Type of Category</label>	
	                					</div>
                						<div class="col col-md-6">
   		    							<label for="app1">
										    <input type="radio" id="app1" name="level_in_hierarchy" value="APPOINTMENT" onchange="clearDescription()"/>
										    Appt/Trade
										</label>
										<label for="rank1" style="margin-left:5px;">
										    <input type="radio" id="rank1" name="level_in_hierarchy"  value="RANK" onchange="clearDescription()"/>
										    Rank
										</label>
										</div>
										</div>
									</div>
								</div>
											<div class="col-12 col-md-12">
              					<div class="col-md-7">
											<div class="row form-group" id="dvdescription" style="display:none;">
                					<div class="col col-md-6" >
                  						<label class=" form-control-label" id="lbl_rank">Enter the Rank</label>
                					</div>
                					<div class="col-12 col-md-6">
                  						<input type="text" id="description" name="description"  class="form-control" autocomplete="off" onkeypress="return blockSpecialChar(event)" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search">
                					</div>
              					</div> 
              					</div> 
								 </div>
              						<div class="col-md-12">
              						<div class="col-md-7">
	              					<div class="row form-group">
	                					<div class="col col-md-6">
	                  						<label class=" form-control-label">Status</label>
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
						<input type="reset" class="btn btn-success btn-sm" value="Clear" onclick="clearall()">   
	              		<i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm" onclick="Search(); " value="Search">
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
	 
			<table  id="AppTradeReport" class="table no-margin table-striped  table-hover  table-bordered report_print" >
				<thead >
					<tr>
						<th class="tableheadSer">Ser No</th>	
						<th >Category</th>
						<th >Level In Hierarchy</th>
						<th >Description</th>
						<th id="thLetterId" style="display: none;">Letter No</th>
						<th id="thRemarkId" style="display: none;">Error Correction</th>
						<th id="thAction" class='lastCol' >Action</th>
	
					</tr>
				</thead> 
				<tbody>
				<c:forEach var="item" items="${list}" varStatus="num" >
								<tr>
									<td class="tableheadSer">${num.index+1}</td>
									<td >${item.label}</td>
									<td >${item.level_in_hierarchy}</td>
									<td >${item.description}</td>
									<td id="thLetterId1" style="display: none;">${item.reject_letter_no}</td>
									<td id="thRemarkId1" style="display: none;">${item.reject_remarks}</td>
									<td id="thAction1" class='lastCol'>${item.id}</td>
								</tr>
				</c:forEach>
				</tbody>
			</table>
			</div>
			</div>
		
		<c:url value="search_appt_trades1" var="searchUrl" />
    		<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="parent_code1">
    			<input type="hidden" name="parent_code1" id="parent_code1" value="0"/>
    			<input type="hidden" name="level_in_hierarchy1" id="level_in_hierarchy1" value="0"/>
    			<input type="hidden" name="description1" id="description1" value="0"/>
    			<input type="hidden" name="status1" id="status1" value="0"/>
    		</form:form> 
    		
<c:url value="ApprovedAPPTUrl" var="appUrl" />
	<form:form action="${appUrl}" method="post" id="appForm" name="appForm" modelAttribute="appid">
		<input type="hidden" name="appid" id="appid" value="0"/>
		<input type="hidden" name="parent_code2" id="parent_code2" value="0"/>
    	<input type="hidden" name="level_in_hierarchy2" id="level_in_hierarchy2" value="0"/>
    	<input type="hidden" name="description2" id="description2" value="0"/>
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
						<textarea id="reject_remarks" name="reject_remarks" class="form-control"  ></textarea>
			   		</div>
		         	<div class="col-sm-6 form-group" id="divletter" style="display: none;">							 
						<label for="text-input" class=" form-control-label">Reject Letter No</label>
						<input id="reject_letter_no" name="reject_letter_no" placeholder="" class="form-control" >
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
var testdesc;
$(document).ready(function() {
	'${parent_code1}';
	if('${status1}' != ""){
		$("#AppTradeReport").show();
		$("#parent_code").val('${parent_code1}');
		if('${level_in_hierarchy1}' != "")
		{
			$("input[name=level_in_hierarchy][value="+'${level_in_hierarchy1}'+"]").prop('checked', true);
		}
		
		if('${level_in_hierarchy1}'=="APPOINTMENT")
		   {    
			   $("label#lbl_rank").text("Description of APPT/Trade");
		       $("div#dvdescription").show();
		   }
		   else
		   {
			   $("label#lbl_rank").text("Description of RANK");
			   $("div#dvdescription").show();
		    }
		
		getdesciptionList('${level_in_hierarchy1}');
		$("#status").val('${status1}');
		$("#divPrint").show();
		$("div#divSerachInput").show();
		document.getElementById("printId").disabled = false;
		$("div#divwatermark").val('').addClass('watermarked'); 
		watermarkreport();
			
		if('${list.size()}' == 0 ){
			$("div#divSerachInput").hide();
			document.getElementById("printId").disabled = true;
			$("table#AppTradeReport").append("<tr><td colspan='7' style='text-align :center;'>Data Not Available</td></tr>");
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
			$("#AppTradeReport tbody tr").filter(function() { 
			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
			});
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
	
	 $("input[name='level_in_hierarchy']").click(function(){
		  if($(this).val()=="APPOINTMENT")
		   {    
			  testdesc = $(this).val();
			   $("label#lbl_rank").text("Description of APPT/Trade");
		       $("div#dvdescription").show();
		   }
		   else
		   {
			   testdesc = $(this).val();
			   $("label#lbl_rank").text("Description of RANK");
			   $("div#dvdescription").show();
		    }
		  getdesciptionList(testdesc);
	}); 
});
</script>
 <script>
 
 function getdesciptionList(testdesc) {
	 var wepetext1=$("#description");
	 var val = testdesc;
		var parent_code = $("#parent_code").val();
		
	  wepetext1.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
	        type: 'POST', 
	        url: "getdesciption?"+key+"="+value,
	        data: {val : val,parent_code : parent_code ,description : document.getElementById('description').value},
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
	          }  else {
	        	  alert("Please Enter Approved Appt/Trade");
	        	  wepetext1.val("");
	        	         	 
	        	  wepetext1.focus();
	        	  return false;	             
	          }   	          
	      }, 
	    });
}
 
 function isValid()
 {	
 	
 	 if($("select#parent_code").val() == "select")
 		{
 			alert("Please Select Category");
 			return false;
 		} 
 	return true;
 }
 </script>
 
 <script>
function clearall()
{	
	
	$('select option:selected').removeAttr('selected');
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
	$("#searchInput").hide();
	//document.location.reload();
 	localStorage.clear();
	localStorage.Abandon();
}

function blockSpecialChar(e){
    
	   $('#description').keyup(function() {
     this.value = this.value.toUpperCase();
 });
	   
 }

function clearData()
{	

	document.getElementById("app1").checked = false;
	document.getElementById("rank1").checked = false;
	
	
	 if($("#level_in_hierarchy").val()!= null )
	{
	
	 $("#dvdescription").show();
	}
else
	{
	$("#dvdescription").hide();
	} 
}

function clearDescription(){
	 document.getElementById('description').value = "";
}
	
function Search(){
	if($("select#parent_code").val() == "Select")
	{
		alert("Please Select Category");
		return false;
	} 
 	var level_in_hierarchy2 = $("input[name='level_in_hierarchy']:checked").val();
 	$("#parent_code1").val($("#parent_code").val());
 	$("#level_in_hierarchy1").val(level_in_hierarchy2);
    $("#description1").val($("#description").val());
    $("#status1").val($("#status").val());
    document.getElementById('searchForm').submit();
}

function Approved(id){
	document.getElementById('appid').value=id;
	$("#parent_code2").val($("#parent_code").val());
	var level_in_hierarchy2 = $("input[name='level_in_hierarchy']:checked").val(); 
	$("#level_in_hierarchy2").val(level_in_hierarchy2);
	$("#description2").val($("#description").val());
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
		$.post("updaterejecttrade?"+key+"="+value,{remarks : remarks,letter_no : letter_no,id:id}).done(function(j) {
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
</body>
</html>