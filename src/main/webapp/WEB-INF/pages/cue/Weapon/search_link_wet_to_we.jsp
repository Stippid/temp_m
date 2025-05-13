<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/excluded/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>  

<script type="text/javascript" src="js/miso/miso_js/jquery-1.12.3.js"></script>

<script src="js/JS_CSS/jquery.dataTables.min.js"></script>
<script src="js/JS_CSS/dataTables.bootstrap.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">


<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/cue/printAllPages.js" type="text/javascript"></script>
<script src="js/cue/update.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<script>
function printDiv() 
 {
	 	var printLbl = ["WE/PE Type :", "WE/PE Document No :"];
	 	var printVal = [$('input:radio[name=we_pe1]:checked').val(),document.getElementById('we_pe_no').value];
	 	printDivOptimize('tableshow','SEARCH LINK WE/PE TO WET/PET/FET',printLbl,printVal,"select#status");
 }
</script>

<form:form name="search_link_wet_we" id="search_link_wet_we" action="" method="post" class="form-horizontal"> 
	<div class="animated fadeIn">
		 	<div class="container" align="center">
	    		<div class="card">
	          		<div class="card-header"><h5><b>SEARCH LINKED WE/PE TO WET/PET/FET</b><br>
	          		<!-- <span style="font-size: 10px;font-size:15px;color:red">(To be approved by Line Dte)</span> -->
	          		</h5> </div>
	          			<div class="card-body card-block cue_text">
	            			<div class="col-md-12" >
								<div class="col-md-6">
		              					<div class="row form-group">
								             <div class="col col-md-6">
								                	<label for="text-input" class=" form-control-label">WE/PE Document <strong style="color: red;">*</strong></label>
								             </div>
											 <div class="col col-md-6">
							                        <div class="form-check-inline form-check">
							                              <label for="inline-radio1" class="form-check-label ">
							                                  <input type="radio" id="we_pe1" name="we_pe1" value="WE" onchange="clearDiscription();" class="form-check-input">WE
							                               </label>&nbsp;
							                               <label for="inline-radio2" class="form-check-label ">
							                                  <input type="radio" id="we_pe2" name="we_pe1" value="PE" onchange="clearDiscription();" class="form-check-input">PE
							                               </label>&nbsp;
							                               <label for="inline-radio3" class="form-check-label ">
							                                  <input type="radio" id="we_pe3" name="we_pe1" value="FE" onchange="clearDiscription();" class="form-check-input">FE
							                               </label>&nbsp;
							                               <label for="inline-radio4" class="form-check-label ">
							                                  <input type="radio" id="we_pe4" name="we_pe1" value="GSL" onchange="clearDiscription();" class="form-check-input">GSL
							                               </label>
							                         </div>
							                 </div>
										</div>
								</div>
									<div class="col-md-6" >
										<div class="row form-group">
	                                             <div class="col col-md-6">
	                                                     <label class=" form-control-label">WE/PE Document No <strong style="color: red;"></strong></label>
	                                             </div>
	                                             <div class="col-12 col-md-6">
		                  						         <input type="text" id="we_pe_no" name="we_pe_no" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search" class="form-control autocomplete" autocomplete="off">
		                					     </div>
	                                    </div>
								   </div>
							</div>
						<div class="col-md-12" >
								<div class="col-md-6" >
								    <div class="row form-group">
	                					<div class="col col-md-6">
                 					          <label for="text-input" class=" form-control-label">Status</label>
               					        </div>
		               					<div class="col-12 col-md-6">
											<select name="wet_link_status" id="wet_link_status" class="form-control">
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
						<input type="reset" class="btn btn-success btn-sm" value="Clear" onclick="clearAll();">   
	              		<i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm" value="Search" onclick="getCheck();">
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
					<table id="getSearchReport" class="table no-margin table-striped  table-hover  table-bordered  report_print"  >
						 <thead >
							<tr align="center">
								<th class="tableheadSer">Ser No</th>
								<th >MISO WE/PE No</th>
								<th >Table Title</th>
								<th >WET/PET No</th>
								<th id="thAction" class='lastCol' >Action</th>
							</tr>
						</thead> 
					    <tbody>
					    <c:forEach var="item" items="${list}" varStatus="num" >
								<tr>
									<td class="tableheadSer">${num.index+1}</td>
									<td >${item.we_pe_no}</td>
									<td >${item.table_title}</td>
									<td >${item.wet_pet_no}</td>
									<td id="thAction1" class='lastCol'>${item.id}</td>
								</tr>
						</c:forEach>
				        </tbody>
				   </table>
		         </div>	
            </div>
            
      <c:url value="search_link_wet_to_we1" var="searchUrl" />
    		<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="we_pe_no1">
    			<input type="hidden" name="we_pe01" id="we_pe01" value="0"/>
    			<input type="hidden" name="we_pe_no1" id="we_pe_no1" value="0"/>
    			<input type="hidden" name="wet_link_status1" id="wet_link_status1" value="0"/>
    		</form:form>     
<c:url value="ApprovedWETToWE" var="appUrl" />
	<form:form action="${appUrl}" method="post" id="appForm" name="appForm" modelAttribute="appid">
		<input type="hidden" name="appid" id="appid" value="0"/>
		<input type="hidden" name="we_pe02" id="we_pe02" value="0"/>
    	<input type="hidden" name="we_pe_no2" id="we_pe_no2" value="0"/>
    	<input type="hidden" name="wet_link_status2" id="wet_link_status2" value="0"/>
	</form:form> 
	
	<c:url value="RejectWETToWE" var="rejectUrl" />
	<form:form action="${rejectUrl}" method="post" id="rejectForm" name="rejectForm" modelAttribute="rejectid">
		<input type="hidden" name="rejectid" id="rejectid" value="0"/>
		<input type="hidden" name="we_pe03" id="we_pe03" value="0"/>
    	<input type="hidden" name="we_pe_no3" id="we_pe_no3" value="0"/>
    	<input type="hidden" name="wet_link_status3" id="wet_link_status3" value="0"/>
	</form:form> 
	
<script>
function Search(){
	var we_pe1 = document.getElementsByName('we_pe1');
    var we_peValue = false;

    for(var i=0; i<we_pe1.length;i++){
        if(we_pe1[i].checked == true){
       	 we_peValue = true;    
        }
    }
    if(!we_peValue){
        alert("Please select the Type of Document (WE/PE)");
        return false;
    }
    
   /*  if($("#we_pe_no").val() == ""){
    	document.getElementById('tableshow').style.display='none';
		alert("Please Enter WE/PE No");
		$("#we_pe_no").focus();
		return false;
	 }  */
    
	   var we_pe1 = $("input[name='we_pe1']:checked").val();
	   $("#we_pe01").val(we_pe1);
	   $("#we_pe_no1").val($("#we_pe_no").val());
	   $("#wet_link_status1").val($("#wet_link_status").val());
	   document.getElementById('searchForm').submit();
	}

function clearDiscription()
{
	 document.getElementById("we_pe_no").value="";
}

$(document).ready(function() {
    	   
    	   
    	      	if('${wet_link_status1}' != ""){
    			$("input[name=we_pe1][value="+'${we_pe01}'+"]").prop('checked', true);
    			getWePeNoByType('${we_pe01}');
        		$("#we_pe_no").val('${we_pe_no1}');
        	   	$("div#divwatermark").val('').addClass('watermarked'); 
        		watermarkreport();
    			$("#getSearchReport").show();
    			$("#wet_link_status").val('${wet_link_status1}');
    			$("#tableshow").show();
    			$("div#divSerachInput").show();
    			document.getElementById("printId").disabled = false;	
    			
    			 if('${list.size()}' == 0 ){
    	    		   $("div#divSerachInput").hide();
    	    		   document.getElementById("printId").disabled = true;
    	    		   $("table#getSearchReport").append("<tr><td colspan='5' style='text-align :center;'>Data Not Available</td></tr>");
    	  		 	}
    		}
    		
    		 if($("#wet_link_status1").val() == "all"){
    				
    				$("th#thAction").hide();
    				$("th#thAction1").hide();
    	   } 
    	
    		 
    		
    	   $("#searchInput").on("keyup", function() {
     			var value = $(this).val().toLowerCase();
     			$("#getSearchReport tbody tr").filter(function() { 
     			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
     			});
     		});
    	   $.ajaxSetup({
	            async: false
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
</script>
<script>
$(function() {	
	
 $("input[type='radio']").click(function(){ 
	
	if(this.value == 'WE'){
		var we_pe = this.value;
		document.getElementById("we_pe_no").value = "";
		getWePeNoByType(we_pe);
			
	}
	else if(this.value == 'PE'){
		var we_pe = this.value;
	    document.getElementById("we_pe_no").value = "";
	    
	    getWePeNoByType(we_pe);
	}
	else if(this.value == 'FE'){
		var we_pe = this.value;
		document.getElementById("we_pe_no").value = "";
		
		getWePeNoByType(we_pe);
	}
	else if(this.value == 'GSL'){
		var we_pe = this.value;
		document.getElementById("we_pe_no").value = "";
		getWePeNoByType(we_pe);
	}
   });	 
 
});	

function getWePeNoByType(we_pe)
{
	if(we_pe != ""){
	 var wepetext=$("#we_pe_no");
	
	  wepetext.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
        	type: 'POST',
            url: "getWePeCondiByNo?"+key+"="+value,
	        data: {type1 : we_pe, newRoleid : "aw", we_pe_no : document.getElementById('we_pe_no').value},
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
	        	wepetext.val("");	        	
	        	wepetext.focus();
	        	return false;	             
	          }   	         
	      }, 
	    });
	 }
	 else
		 alert("Please select Document");
}

</script>

<script>
function getCheck(){
	validate();
    var we_pe = $("input[name='we_pe1']:checked").val();
   if($("input[name='we_pe1']:checked").val() != null && $('#wet_link_status').val() != null){
    	Search();
    } 
}

function validate(){
	var we_pe1 = document.getElementsByName('we_pe1');
    var we_peValue = false;

    for(var i=0; i<we_pe1.length;i++){
        if(we_pe1[i].checked == true){
       	 we_peValue = true;    
        }
    }
    if(!we_peValue){
        alert("Please select the Type of Document (WE/PE)");
        return false;
    }
    
  /*   if($("#we_pe_no").val() == ""){
    	document.getElementById('tableshow').style.display='none';
		alert("Please Enter WE/PE No");
		$("#we_pe_no").focus();
		return false;
	 } */ 
    
	return true;
}

function clearAll()
{	
	document.getElementById('tableshow').style.display='none';
	document.getElementById("printId").disabled = true;
	 var tab = $("#getSearchReport > tbody"); 
	 tab.empty(); 
	 $("#searchInput").val("");
	 $("div#divSerachInput").hide();
	//document.location.reload();
	 	localStorage.clear();
		localStorage.Abandon();
}
function Approved(id){
	    document.getElementById('appid').value=id;
	    var we_pe1 = $("input[name='we_pe1']:checked").val();
     	$("#we_pe02").val(we_pe1);
		$("#we_pe_no2").val($("#we_pe_no").val());
		$("#wet_link_status2").val($("#wet_link_status").val());
		document.getElementById('appForm').submit();
}

function Reject(id){
	  document.getElementById('rejectid').value=id;
	  var we_pe1 = $("input[name='we_pe1']:checked").val();
   		$("#we_pe03").val(we_pe1);
		$("#we_pe_no3").val($("#we_pe_no").val());
		$("#wet_link_status3").val($("#wet_link_status").val());
		document.getElementById('rejectForm').submit();
}

/* function Delete1(id){
	   document.getElementById('deleteid').value=id;
		document.getElementById('deleteForm').submit();
}

function Update(id){
	  document.getElementById('updateid').value=id;
	   document.getElementById('updateForm').submit();
} */
</script>

