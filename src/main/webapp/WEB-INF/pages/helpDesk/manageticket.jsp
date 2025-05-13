<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="datatables"
	uri="http://github.com/dandelion/datatables"%>
<%@ taglib prefix="dandelion" uri="http://github.com/dandelion"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script src="js/amin_module/helpdesk/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/printWatermark/common.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/printWatermark/cueWatermark.css">

<script type="text/javascript" src="js/amin_module/rbac/jquery-1.12.3.js"></script> 
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>
<script src="js/cue/printAllPages.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<script type="text/javascript" src="js/miso/miso_js/jquery-1.12.3.js"></script>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script src="js/amchart4/core.js"></script>
<script src="js/amchart4/charts.js"></script>
<script src="js/amchart4/animated.js"></script>
<!-- <link rel="stylesheet" href="js/miso/tmsDashboard/tmsDashboardCSS.css"> -->


<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link rel="stylesheet" href="js/InfiniteScroll/css/datatables.min.css">
<script type="text/javascript"
	src="js/InfiniteScroll/js/datatables.min.js"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/jquery.mockjax.min.js"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/datatables.mockjax.js"></script>

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<script>
var newWin;
function editData(id,label3){	
	/*var x = screen.width/2 - 1100/2;
    var y = screen.height/2 - 900/2;
	newWin = window.open("", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');	*/ 
	document.getElementById('updateid').value=id;
	document.getElementById('label1').value=label3;
	document.getElementById('updateForm').submit();
}
function transferData(id){
	document.getElementById('transferid').value=id;
	document.getElementById('transferForm').submit();
}
</script>

<c:url value="viewticketDetails" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid"  > <%--  target="result" --%>
	<input type="hidden" name="updateid" id="updateid" value="0"/>
	<input type="hidden" name="label3" id="label3" value="${label}"/>
</form:form> 
<c:url value="transferTicketDetails" var="transferUrl" />
<form:form action="${transferUrl}" method="post" id="transferForm" name="transferForm" modelAttribute="transferid"  > <%--  target="result" --%>
	<input type="hidden" name="transferid" id="transferid" value="0"/>
</form:form> 
<form:form name="myticket" id="myticket" action="myticketAction" method='POST' commandName="myticketCMD">

<div onFocus="parent_disable1();" onclick="parent_disable1();">
<div class="container" align="center">
        <div class="card">
                <div class="card-header"> <h5>${label}</h5></div>
                <div class="card-body card-block cue_text">
                <div class="col-md-12">
                  <div class="col-md-6">
   					<div class="row form-group">
     					<div class="col col-md-6">
		               	<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Search by</label>
		               </div>
		                <div class="col-12 col-md-6">
                            <div class="form-check-inline form-check">
                              <label for="inline-radio1" class="form-check-label ">
                              	<input type="hidden" id="label1" name="label1"  class="form-control" value="${label}">
                                <input type="radio" id="inline-radio1" value="TicketId" name="type" class="form-check-input" onchange="clearTYPE();">Ticket Id
                              </label>&nbsp;&nbsp;&nbsp;
                              <label for="inline-radio2" class="form-check-label ">
                                <input type="radio" id="inline-radio2" value="Status" name="type" class="form-check-input" onchange="clearTYPE1();">Status
                              </label>&nbsp;&nbsp;&nbsp;
                            </div>
		                 </div>					              
          				</div>
          			</div>
                </div>
                       <div class="col-md-12">
                        <div class="col-md-6">
                             <div class="row form-group"> 
                                     <div class="col col-md-4">                                     
                                        <label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Ticket Id</label>
                                </div>
                                <div class="col-12 col-md-8">
                                	<input id="ticket" name="ticket" class="form-control" autocomplete="off" >  
                                	<input type="hidden" id="sus_no_id" name="sus_no_id"  class="form-control" >
                                	<input type="hidden" id="unit_name" name="unit_name" style="font-family: 'FontAwesome',Arial;" value='${unit_name}' class="form-control" readonly="readonly">
                                 </div>
                             </div>
                       </div>                                                              
                       <div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Status </label>
							</div>
							<div class="col-12 col-md-8">
								<select name="ticket_status" id="ticket_status" class="form-control">
									<!-- <option value="">--Select--</option> -->
                                     	<!-- <option value="0">New</option>
                                    	<option value="1">In Progress</option>
                    					<option value="2">Completed</option> -->
                                  </select>
                                </div>                                                         
                            </div>
                       </div>
                   </div>
                        <div class="col-md-12" id="hid_id">  
                       <div class="col-md-6">
                             <div class="row form-group"> 
                                     <div class="col col-md-4">
                                        <label for="text-input" class=" form-control-label">Date (from)</label>
                                </div>
                                     <div class="col-12 col-md-8">                                     
                                               <input type="date" id="from_date" name="from_date" class="form-control" autocomplete="off" >
                                                </div>
                             </div>
                     </div>                           
                <div class="col-md-6">
                             <div class="row form-group"> 
                                <div class="col col-md-4">
                                   <label for="text-input" class=" form-control-label">Date (to) </label>
                                </div>
                                 <div class="col-12 col-md-8">                                     
                                    <input type="date" id="to_date" name="to_date" class="form-control" autocomplete="off" onchange="checkDate();">
                                 </div>
                             </div>
                     </div>    
            </div>
            <div class="col-md-12">	           
               <div class="col-md-6">
	             	<div class="row form-group"> 
	             		<div class="col col-md-4">	             		
		                	<label for="text-input" class=" form-control-label">Help Topic </label>
		                </div>
		                <div class="col-12 col-md-8">
               				<select  id="help_topic" name="help_topic" class="form-control"> 
               				<option value="0">--Select--</option>
       							<c:forEach var="item" items="${GetHelpTopic}" varStatus="num" >
       								<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
       							</c:forEach>
						    </select>
						</div>
	             	</div>
                </div> 
                <div class="col-md-6">
	             	<div class="row form-group"> 
	             		<div class="col col-md-4">	             		
		                	<label for="text-input" class=" form-control-label"> Module</label>
		                </div>
		                <div class="col-12 col-md-8">
               				<select id="module" name="module" class="form-control" > 
               				<option value="0">--Select--</option>
       							<c:forEach var="item" items="${getModuleNameList}" varStatus="num" >
       								<option style="text-transform: uppercase;" value="${item.id}">${item.module_name}</option>
       							</c:forEach>
						    </select>
						</div>
	             	</div>
                </div>
           </div>
      </div>

				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label class=" form-control-label">Cont Comd </label>
							</div>
							<div class="col-12 col-md-8">
								<select id="cont_comd" name="cont_comd" class="form-control">
<%-- 									${selectcomd} --%>
									<option value="">--Select--</option>
									<c:forEach var="item" items="${getCommandList}" varStatus="num">
										<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>

					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"> Unit Name </label>
							</div>
							<div class="col-md-8">
							<input type="text" id="unit" name="unit" class="form-control-sm form-control autocomplete" onkeyup="return specialcharecter(this)" autocomplete="off" maxlength="50">
							</div>
						</div>
					</div>



					
				</div>

				<div class="card-footer" align="center">
	                 <input type="reset" class="btn btn-success btn-sm" value="Clear">                                  
	                 <i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm" value="Search" onclick="Search();">
	       </div> 
   </div>
  </div>

</div>

<div class="col-md-12"  id="divPrint" style="display: none; "  >
			<div id="divShow" style="display: block;"></div>
			<div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
				<span id="ip"></span>
		       <table id="TicketReport" class="table no-margin table-striped  table-hover  table-bordered report_print">
				<thead style="background-color: #9c27b0; color: white;">
					<tr>
						<th style="width: 5%;">Ser No</th>
						<th style="width: 8%;">Ticket Id</th>
						<th style="width: 13%;">Unit Name</th>
						<th style="width: 12%;">Module</th>
						<th style="width: 10%;">Date of Creation</th>
						<th style="width: 10%;">Date of Assignment</th>
						<th style="width: 10%;">Date of Completed</th>
						<th style="width: 12%;">Help Topic</th>
						<th style="width: 32%;">Issue Summary</th>
						<th class='lastCol' style="text-align: center; width: 8%;">Action</th>
					</tr>
		               </thead>
				<tbody>
		              
					<c:forEach var="item" items="${list}" varStatus="num">
						<tr>
							<th style="width: 5%;">${num.index+1}</th>
							<th style="width: 8%;">${item.ticket}</th>
							<th style="width: 13%;">${item.unit_name}</th>
							<th style="width: 12%;">${item.module_name}</th>
							<th style="width: 10%;">${item.created_on}</th> 
							<th style="width: 10%;">${item.assigned_dt}</th>
							<th style="width: 10%;">${item.completed_dt}</th>
							<th style="width: 12%;">${item.help_topic}</th>
							<th style="width: 32%;">${item.issue_summary}</th>
							<th id="thAction1" class='lastCol' style="text-align: center; width: 8%;">${item.id}</th>
						</tr>
					</c:forEach>
		                </tbody>
		        </table>
        	</div>	
		</div>	
</form:form>
<c:url value="manageticketList" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="ticket1">
	<input type="hidden" name="ticket1" id="ticket1" value="0"/>
	<input type="hidden" name="ticket_status1" id="ticket_status1" value="0"/>
	<input type="hidden" name="from_date1" id="from_date1" value="0"/>
	<input type="hidden" name="to_date1" id="to_date1" value="0"/>
	<input type="hidden" name="help_topic1" id="help_topic1" value=""/>
	<input type="hidden" name="type1" id="type1" value=""/>
    <input type="hidden" name="module1" id="module1" value=""/>
	<input type="hidden" name="label" id="label" value="${label}"/>
	<input type="hidden" name="unit_name1" id="unit_name1" value=""/>
	<input type="hidden" name="cont_comd1" id="cont_comd1" value=""/>
</form:form> 
<script>
function parent_disable1() {
	if(newWin && !newWin.closed)
		newWin.focus();
}
function getRefreshReport(page,msg){
	if(msg.includes("Updated+Successfully"))
	{
		if(page == "myticket1")
		{
			Search();
		}
	}
}
function clearTYPE(){
	 document.getElementById("ticket").disabled = false;
	document.getElementById("ticket_status").disabled = true;
	$("#ticket_status").val("");
	$("#divPrint").hide();
}
function clearTYPE1(){
	 document.getElementById("ticket").disabled = true;
	 document.getElementById("ticket_status").disabled = false;	
	 $("#ticket").val("");
	 $("#divPrint").hide();
}
function Search(){
	watermarkreport();
	var r =  $('input:radio[name=type]:checked').val();	
	if(r == undefined)
	 {		 
		 alert("Please Select type");
		 $("#type").focus();
		return false;
	 }
	if(r == 'TicketId')
	 {		 
		if($("#ticket").val() == "")
		 {		 
			 alert("Please Enter ticket");
			 $("#ticket").focus();
			return false;
		 }
	 }
	if(r == 'Status')
	 {		 
		if($("#ticket_status").val() == "")
		 {		 
			 alert("Please Select Ticket Status");
			 $("#ticket_status").focus();
			return false;
		 }
	 }
		$("#ticket1").val($("#ticket").val());
	    $("#ticket_status1").val($("#ticket_status").val());
		$("#to_date1").val($("#to_date").val());
		$("#from_date1").val($("#from_date").val());
		$("#help_topic1").val($("#help_topic").val());
		$("#type1").val(r);
		$("#module1").val($("#module").val());
		$("#label").val($("#label1").val());
 		$("#cont_comd1").val($("#cont_comd").val());
		$("#unit_name1").val($("#unit").val());
		
	    document.getElementById('searchForm').submit();
	
}
$(document).ready(function (){
	var options = '<option value="">--Select--</option>';
	if('${typeWhr}' == "SHOW"){
		options += '<option value="0">New</option>';
		options += '<option value="1">In Progress</option>';
		options += '<option value="2">Completed</option>';
	}else if('${typeWhr}' == "sd9"){
		options += '<option value="0">New</option>';
		options += '<option value="1">Completed</option>';
	} else{
		options += '<option value="1">New</option>';
		options += '<option value="2">Completed</option>';
	}
	$("select#ticket_status").html(options);
	
	watermarkreport();
	document.getElementById("ticket_status").disabled = true;
	document.getElementById("ticket").disabled = true;
	$("#sus_no_id").val('${list[0][0]}');
	$("#unit_name").val('${list[0][1]}');
	if('${list.size()}' == 0 ){
	     $("table#TicketReport").append("<tr><td colspan='8' style='text-align:center;color:red;'>Data Not Available</td></tr>");
	}
	if('${ticket1}'!= ""){
 		document.getElementById("ticket_status").disabled = true;
		 document.getElementById("ticket").disabled = false;
 	}
 	if('${ticket_status1}'!= ""){
 		document.getElementById("ticket_status").disabled = false;
		 document.getElementById("ticket").disabled = true;
 	}
	if('${ticket1}'!= "" || '${ticket_status1}'!= ""){debugger;
		$("#label1").val('${label}');
		$("#ticket").val('${ticket1}');
	 	$("#ticket_status").val('${ticket_status1}');
	 	$("#from_date").val('${from_date1}');
	 	$("#to_date").val('${to_date1}');
	 	$("#help_topic").val('${help_topic1}');
	  	$("input[name=type][value="+'${type1}'+"]").prop('checked', true);
	 	$("#module").val('${module1}');
	 	$("#cont_comd").val('${cont_comd1}');
	 	$("#unit").val('${unit_name1}');
	 	$("#divPrint").show();	
	}
	 try{
	     if(window.location.href.includes("msg="))
		{
			var url = window.location.href.split("?msg=")[0];
			var m=  window.location.href.split("?msg=")[1];
			window.location = url;
			
			if(m.includes("Updated+Successfully")){
				window.opener.getRefreshReport('myticket1',m);
				window.close('','_parent','');
			}
		} if( document.getElementById("msg").value != "")
		{
			$("div#divwatermark").val('').removeClass('watermarked'); 
			$("div#divSerachInput").hide();
			$("div#divPrint").hide();
		}
	}
catch (e) {
}  	 







});

function checkDate(){
	  var startDate = document.getElementById("from_date").value;
	  var endDate = document.getElementById("to_date").value;
	
	  if ((Date.parse(endDate) <= Date.parse(startDate))) {
	        alert("Effective To date should be greater than Effective From date");
	        document.getElementById("to_date").value = "";
	    }
}
$("#searchInput").on("keyup", function() {
	var value = $(this).val().toLowerCase();
	$("#TicketReport tbody tr").filter(function() { 
	$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
	});
}); 


function printDiv() 
{
 	var printLbl = ["Status:","Ticket Id:","From Date:","To Date:"];
 	var status = document.getElementById('ticket_status').value;
 	var help_tp = document.getElementById('help_topic').value;
 	if(status == "0"){
 		status = "New";
 	}else if(status == "1"){
 		status = "In Progress";
 	}else if(status == "2"){
 		status = "Completed";
 	}else if(status == "3"){
 		status = "Feedback";
 	}else if(status == "4"){
 		status = "Feature Request";
 	}
 	if(help_tp == "1"){
 		help_tp = "Feedback";
 	}else if(help_tp == "2"){
 		help_tp = "General Inquiry";
 	}else if(help_tp == "3"){
 		help_tp = "Report a problem";
 	}else if(help_tp == "4"){
 		help_tp = "Report a problem/Access Issues";
 	}else if(help_tp == "5"){
 		help_tp = "Feature Request";
 	}
 	var printVal = [status,document.getElementById('ticket').value,help_tp];
 	printDivOptimize('divPrint','My Ticket',printLbl,printVal,"");
} 


$("input#unit").keypress(function(){
	var unit_name = this.value;
		 var susNoAuto=$("#unit");
		  susNoAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        	type: 'POST',
			    	url: "getTargetUnitsNameActiveList?"+key+"="+value,
		        data: {unit_name:unit_name},
		          success: function( data ) {
		        	 var susval = [];
		        	  var length = data.length-1;
		        	  var enc = data[length].substring(0,16);
			        	for(var i = 0;i<data.length;i++){
			        		susval.push(dec(enc,data[i]));
			        	}
					response( susval ); 
		          }
		        });
		      },
		      minLength: 1,
		      autoFill: true,
		      change: function(event, ui) {
		    	 if (ui.item) {   	        	  
		        	  return true;    	            
		          } else {
		        	  alert("Please Enter Approved Unit Name.");
		        	  document.getElementById("unit").value="";
		        	  susNoAuto.val("");	        	  
		        	  susNoAuto.focus();
		        	  return false;	             
		          }   	         
		      }, 
		   	     
		}); 			
});
</script>				