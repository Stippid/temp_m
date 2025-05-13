<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 

<script src="js/JS_CSS/jquery.dataTables.min.js"></script>
<script src="js/JS_CSS/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="js/miso/miso_js/jquery-1.12.3.js"></script>

<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/cue/printAllPages.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/Calender/jquery-ui.css"/>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/cue/update.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>


</script>
<script>
function printDiv() 
  	{
	 	var printLbl = ["Month of Calculation :", "Year of Calculation :"];
	 	var printVal = [document.getElementById('month_cal').value,document.getElementById('year_cal').value];
	 	printDivOptimize('divPrint','SEARCH PROVISION POLICY (TRANSPORT)',printLbl,printVal,"select#status");
 	 }
</script>

<form action="" method="post" enctype="multipart/form-data" class="form-horizontal"> 
	<div class="animated fadeIn">
		<div class="">
	    	<div class="container" align="center">
	    		<div class="card">
	    			<div class="card-header"><h5>SEARCH PROVISION POLICY</h5></div>
	          			<div class="card-body card-block cue_text">
	            		<div class="col-md-12">	
	            			<div class="col-md-6">
	  						<div class="row form-group">	           					
             					<div class="col col-md-6">
               						<label class=" form-control-label">Month of Calculation <strong style="color: red;">*</strong></label>
             					</div>
             					<div class="col-12 col-md-6">
             						<input id="month_cal" name="month_cal" type="number" min="1" max="12" step="1" class="form-control" maxlength="2" />
               					</div>
             				</div>
             				</div>		  							
             				<div class="col-md-6">
	  						<div class="row form-group">	           					
             					<div class="col col-md-6">
               						<label class=" form-control-label">Year of Calculation <strong style="color: red;">*</strong></label>
             					</div>
             					<div class="col-12 col-md-6">
             						<input id="year_cal" name="year_cal" type="number" min="1900" max="2099" step="1" value="2019" class="form-control"/>
               					</div>
             				</div>
             				</div>
	  					</div>	
	          			<div class="col-md-12">	
	          			<div class="col-md-6">	
	              			<div class="row form-group">              			 	
               					<div class="col col-md-6">
                 					<label for="text-input" class=" form-control-label">Auth Letter No </label>
               					</div>
               					<div class="col-12 col-md-6">
                 					<input id="letter_no" name="letter_no" class="form-control">
								</div>
							</div>	 							
	  						</div>	                					
	              			<div class="col-md-6">	
	              			<div class="row form-group">              			 	
               					<div class="col col-md-6">
                 					<label for="text-input" class=" form-control-label">MISO WE/PE No </label>
               					</div>
               					<div class="col-12 col-md-6">
                 					<input  id="we_pe_no" name="we_pe_no" class="form-control" autocomplete="off" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search">
								</div>
							</div>	 							
	  						</div>
	           				
	  					</div>	
	  					<div class="col-md-12">	         					
	              			 <div class="col-md-6">	
	  						<div class="row form-group">           					
             					<div class="col col-md-6">
               						<label class=" form-control-label">Force Type </label>
             					</div>
             					<div class="col-12 col-md-6">
               						<select id="force_type" name="force_type" class="form-control">
               						<option value="">--Select--</option>
                 						<c:forEach var="item" items="${getForceTypeList}" varStatus="num" >
           									<option value="${item[0]}" > ${item[1]}</option>
           								</c:forEach>
               						</select>
             					</div>
             				</div>
             				</div>	
	           				 <div class="col-md-6">	
	  						<div class="row form-group">           					
             					<div class="col col-md-6">
               						<label class=" form-control-label">Status of Records</label>
             					</div>
             					<div class="col-12 col-md-6">
               						<select id="status" name="status" class="form-control">
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
						<input type="reset" class="btn btn-success btn-sm" value="Clear">   
	              		<i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm" onclick="Search();" value="Search">
	              		<i class="fa fa-print"></i><input type="button" id="printId" class="btn btn-primary btn-sm btn_report" value="Print" onclick="printDiv();" disabled>
		            </div> 
	        	</div>
			</div>
		</div>
	</div>
	
</form>

<div style="width: 20%; padding-left: 1%; display: none;" id="divSerachInput">
	<input id="searchInput" type="text" style="font-size:12px; font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search Word"  size="35" class="form-control">
</div>
<div class="col s12" style="display: none;" id="divPrint">
<div id="divShow" style="display: block;">
</div>

<div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
					<span id="ip"></span>
		<table id="AttributeReporttra"  class="table no-margin table-striped  table-hover  table-bordered report_print" >
			<thead >
				<tr>
					<th class="tableheadSer">Ser No</th>
					<th >Month of Calculation</th>
					<th >Year of Calculation</th>
					<th >Auth Letter No</th>
					<th >Auth Letter Date</th>
					<th >WE/PE No</th>
					<th >Type of Unit</th>
					<th >Force Type</th>
					<th >No of Units</th>
					<th id="thAction" class='lastCol'>Action</th>
					
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${list}" varStatus="num" >
					<tr>
						<td class="tableheadSer">${num.index+1}</td>
						<td >${item.month_cal}</td>
						<td >${item.year_cal}</td>
						<td >${item.letter_no}</td>
						<td >${item.letter_date}</td>
						<td >${item.we_pe_no}</td>
						<td >${item.unit_type}</td>
						<td >${item.force_type}</td>
						<td >${item.no_of_units}</td>
						<td id="thAction1"   class='lastCol'>${item.id}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</div>
	</div>	

	<c:url value="searchProvisionMstTransUrl1" var="searchUrl" />
   		<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="we_pe_no1">
   		<input type="hidden" name=year_cal1 id="year_cal1" value="0"/>
   		<input type="hidden" name="we_pe_no1" id="we_pe_no1" value="0"/>
   		<input type="hidden" name="month_cal1" id="month_cal1" value="0"/>
		<input type="hidden" name="letter_no1" id="letter_no1" value="0"/>
		<input type="hidden" name="force_type1" id="force_type1" value="0"/>
		<input type="hidden" name="status1" id="status1" value="0"/>
	</form:form>
	<c:url value="ApprovedProTraUrl" var="appUrl" />
	<form:form action="${appUrl}" method="post" id="appForm" name="appForm" modelAttribute="appid">
		<input type="hidden" name="appid" id="appid" value="0"/>
		 <input type="hidden" name=year_cal2 id="year_cal2" value="0"/>
   		<input type="hidden" name="we_pe_no2" id="we_pe_no2" value="0"/>
   		<input type="hidden" name="month_cal2" id="month_cal2" value="0"/>
		<input type="hidden" name="letter_no2" id="letter_no2" value="0"/>
		<input type="hidden" name="force_type2" id="force_type2" value="0"/>
		<input type="hidden" name="status2" id="status2" value="0"/> 
	</form:form> 
	
	<c:url value="rejectProTraUrl" var="rejectUrl" />
	<form:form action="${rejectUrl}" method="post" id="rejectForm" name="rejectForm" modelAttribute="rejectid">
		<input type="hidden" name="rejectid" id="rejectid" value="0"/>
	</form:form> 
	
<script>


function Search(){
   $("#we_pe_no1").val($("#we_pe_no").val());
   $("#year_cal1").val($("#year_cal").val());
   $("#month_cal1").val($("#month_cal").val());
   $("#letter_no1").val($("#letter_no").val());
   $("#force_type1").val($("#force_type").val());
   $("#status1").val($("#status").val());
   document.getElementById('searchForm').submit();
}

$(document).ready(function() {
	
	 $("#month_cal").val('${month_cal1}');
	
	 if($("#month_cal").val() == "" )
		{
		  ParseDateColumn();
		}
	 
	if('${month_cal1}' != "")
	{ 
		$("#month_cal").val('${month_cal1}');
		$("#we_pe_no").val('${we_pe_no1}');
		$("#status").val('${status1}');
		$("#year_cal").val('${year_cal1}'); 
		$("#letter_no").val('${letter_no1}');
		$("#divPrint").show();
		$("div#divwatermark").val('').addClass('watermarked'); 
		watermarkreport();
		 $("div#divSerachInput").show(); 
		document.getElementById("printId").disabled = false;
		
		if('${list.size()}' == 0 ){
			 $("div#divSerachInput").hide(); 
			 document.getElementById("printId").disabled = true;
			$("table#AttributeReporttra").append("<tr><td colspan='11' style='text-align:center;'>Data Not Available</td></tr>");
		 }
	 }
	
	
	if('${status1}' == "all")
		{
		$("#we_pe_no").val('${we_pe_no1}');
		$("#year_cal").val('${year_cal1}');
		$("#month_cal").val('${month_cal1}'); 
		$("#letter_no").val('${letter_no1}');
		$("#force_type").val('${force_type1}'); 
		$("#status").val('${status1}');
		$("th#thAction").hide();
		$("th#thAction1").hide();
		
		}
	
	
	 
	$("#searchInput").on("keyup", function() {
			var value = $(this).val().toLowerCase();
			$("#AttributeReporttra tbody tr").filter(function() { 
			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
			});
		});
	
	
	$("#month_cal").keypress(function (e) {	     
	     var maxlengthNumber = parseInt($('#month_cal').attr('maxlength'));
	     var inputValueLength = $('#month_cal').val().length + 1;
	    
	     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {	        
	               return false;
	    }
	    if(maxlengthNumber < inputValueLength) {
	    	return false;
	    }	    
	});
	
	$("#year_cal").keypress(function (e) {	     
	     var maxlengthNumber = parseInt($('#year_cal').attr('maxlength'));
	     var inputValueLength = $('#year_cal').val().length + 1;
	     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {	        
	               return false;
	    }
	    if(maxlengthNumber < inputValueLength) {
	    	return false;
	    }
	});
	    
	    var wepetext=$("#we_pe_no");
	   
		  wepetext.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
	        	type: 'POST',
		  	    url: "getWePeCondiByNoprov?"+key+"="+value,
		        data: {newRoleid : "at",we_pe_no: document.getElementById('we_pe_no').value},
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
		        	alert("Please Enter Approved WE/PE No");
		        	wepetext.val("");
		        	wepetext.focus();
		        	return false;	             
		          }   	         
		      }, 
	     /*   select: function( event, ui ) {
	      	$(this).val(ui.item.value);    	        	
	      	getDetailsBySupeerseedNo($(this).val());	        	
	       }  */	     
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
		
	}
	catch (e) {
		// TODO: handle exception
	}
  
});	

function ParseDateColumn() {
	var d = new Date();
	document.getElementById("month_cal").value=(d.getMonth()+1);
	document.getElementById("year_cal").value=d.getFullYear();
	
	
}
function clearAll() {
	var tab = $("#AttributeReporttra");
 	tab.empty();
 	document.getElementById("printId").disabled = true;
 	$("#divPrint").hide();
 	
 	$("#searchInput").val("");
 	 $("#searchInput").hide();
 	//document.location.reload();
  	localStorage.clear();
 	localStorage.Abandon();
}

function isValid()
{  	  
	 if($("input#month_cal").val() == "")
	 {
		 alert("Please select Month of Calculation");
		 $("input#month_cal").focus();
		 return false;
	 } 
	 else if($('input#month_cal').val() > 12)
	{
		alert("Please select correct Month of Calculation ");
		$('input#month_cal').focus();
		return false;
	}
		
	 if($("input#year_cal").val() == "")
	 {
		 alert("Please select Year of Calculation");
		 $("input#year_cal").focus();
		 return false;
	 } 
	return true;
}

function Approved(id){
	document.getElementById('appid').value=id;
	$("#we_pe_no2").val($("#we_pe_no").val());
   $("#year_cal2").val($("#year_cal").val());
   $("#month_cal2").val($("#month_cal").val());
   $("#letter_no2").val($("#letter_no").val());
   $("#force_type2").val($("#force_type").val());
   $("#status2").val($("#status").val());
	document.getElementById('appForm').submit();
}
function Reject(id){
	document.getElementById('rejectid').value=id;
	document.getElementById('rejectForm').submit();
}


</script>
