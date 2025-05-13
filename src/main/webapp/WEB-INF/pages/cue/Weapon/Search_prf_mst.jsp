<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/excluded/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/JS_CSS/jquery.dataTables.min.js"></script>
<script src="js/JS_CSS/dataTables.bootstrap.min.js"></script>
	<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css"> 

<link rel="stylesheet" href="js/Calender/jquery-ui.css"/>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/cue/printAllPages.js" type="text/javascript"></script>
<script src="js/cue/update.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<script>
function printDiv() 
  	{
	 	var printLbl = ["PRF Group :"];
	 	var printVal = [document.getElementById('prf_group').value];
	 	printDivOptimize('divPrint','SEARCH PRF MASTER',printLbl,printVal,"select#status");
 	 }
</script>


<script>
$(function() {     
        var wepetext=$("#prf_group");
       
  	  wepetext.autocomplete({
  	      source: function( request, response ) {
  	        $.ajax({
        	type: 'POST',
	  	    url: "getprfList?"+key+"="+value,
  	        data: {prf_group:document.getElementById('prf_group').value},
  	          success: function( data ) {
  	            //response( data );
  	           // var dataCountry1 = data.join("|");
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
  	        	alert("Please Enter Approved PRF Group");
  	        	wepetext.val("");
  	        	wepetext.focus();
  	        	return false;	             
  	          }   	         
  	      }, 
         
  	    });

      });
</script>

<form:form action="prf_mstAction" method="post" class="form-horizontal" commandName="prf_mstCMD">
	<div class="animated fadeIn">
			<div class="container" align="center">
	    		<div class="card">
	          		<div class="card-header"><h5><b>SEARCH PRF MASTER</b><br><span style="font-size: 10px;font-size:15px;color:red">(To be approved by MISO)</span></h5></div>
	          			<div class="card-body card-block cue_text">
	            			<div class="col-md-12">	
								<div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-6">
	                  						<label class=" form-control-label">PRF Group</label>
	                					</div>
	                					<div class="col-12 col-md-6"> 
	                  						<input type="text" id="prf_group" name="prf_group"  class="form-control"   style="text-transform:uppercase;font-family: 'FontAwesome',Arial;"  autocomplete="off"   placeholder="&#xF002; Search">
	                					</div>
	                					  </div>
	                					  </div>
	                					  <div class="col-md-6">
								  <div class="row form-group">
	                					<div class="col col-md-6">
	                  						<label class=" form-control-label">COS Section</label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                  						<select name="coss_section" id="coss_section" class="form-control"> </select>
	                					</div>
	                					</div>
								 
								   </div>
								  </div>
								  <div class="col-md-12">	
								 
								   <div class="col-md-6">
								   <div class="row form-group">
	                					<div class="col col-md-6">
	                  						<label class=" form-control-label">Nodal Dte</label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                  						<select name="nodal_dte" id="nodal_dte" class="form-control"> </select>
	                					</div>
	              					</div></div>
	              					 	
								 
								   <div class="col-md-6">
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
						<input type="reset" class="btn btn-success btn-sm" value="Clear" onclick="clearAll();">   
	              		<i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm" onclick="isvalidation();" value="Search" >
	              		<i class="fa fa-print"></i><input type="button" id="printId" class="btn btn-primary btn-sm btn_report" value="Print" onclick="printDiv();" disabled>
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
			<table id="PrfReport" class="table no-margin table-striped  table-hover  table-bordered  report_print" >
				 <thead >
					<tr>
						<th class="tableheadSer">Ser No</th>
						<th >PRF Group</th>
						<th >COS Section</th>
						<th >Nodal Dte</th>
						 <th id="thAction" class='lastCol'>Action</th>
						
					</tr>
				</thead> 
				<tbody>
				<c:forEach var="item" items="${list}" varStatus="num" >
								<tr>
									<td class="tableheadSer">${num.index+1}</td>
									<td >${item.prf_group}</td>
									<td >${item.coss_section}</td>
									<td >${item.label}</td>
									<td id="thAction1" class='lastCol'>${item.id}</td>
								</tr>
							</c:forEach>
				</tbody>
			</table>
			</div>
		</div>	
	<c:url value="search_prf_mstUrl1" var="searchUrl" />
    		<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="prf_group1">
    			<input type="hidden" name="prf_group1" id="prf_group1" value="0"/>
    			<input type="hidden" name="coss_section1" id="coss_section1" value="0"/>
    			<input type="hidden" name="nodal_dte1" id="nodal_dte1" value="0"/>
    			<input type="hidden" name="status1" id="status1" value="0"/>
    		</form:form>
    		
	<c:url value="ApprovedprfMstArmUrl" var="appUrl" />
	<form:form action="${appUrl}" method="post" id="appForm" name="appForm" modelAttribute="appid">
	<input type="hidden" name="appid" id="appid" value="0"/>
	<input type="hidden" name="prf_group2" id="prf_group2" value="0"/>
	<input type="hidden" name="coss_section2" id="coss_section2" value="0"/>
	<input type="hidden" name="nodal_dte2" id="nodal_dte2" value="0"/>
	<input type="hidden" name="status2" id="status2" value="0"/>
	</form:form> 
	
	 <c:url value="rejectPrf_MstUrl" var="rejectUrl" />
	<form:form action="${rejectUrl}" method="post" id="rejectForm" name="rejectForm" modelAttribute="rejectid">
		<input type="hidden" name="rejectid" id="rejectid" value="0"/>
		<input type="hidden" name="prf_group3" id="prf_group3" value="0"/>
		<input type="hidden" name="coss_section3" id="coss_section3" value="0"/>
		<input type="hidden" name="nodal_dte3" id="nodal_dte3" value="0"/>
		<input type="hidden" name="status3" id="status3" value="0"/>
	</form:form> 
	
<script>
var size;
       $(document).ready(function() {
    	  
    		if('${status1}' != ""){
    			$("#prf_group").val('${prf_group1}');
    			$("#status").val('${status1}');
    			$("#PrfReport").show();
    			$("#divPrint").show();
    			$("div#divSerachInput").show();
    			document.getElementById("printId").disabled = false;	
    			$("div#divwatermark").val('').addClass('watermarked'); 
    	    	watermarkreport();
    	    	
    	    	if('${list.size()}' == 0 ){
    	    		   $("div#divSerachInput").hide(); 
    	    		   document.getElementById("printId").disabled = true;	
    	    		   $("table#PrfReport").append("<tr><td colspan='11' style='text-align:center'>Data Not Available</td></tr>");
    	    		 }
    		}
    		
    		if($("#status").val() == "2"){
    				
    				$("th#thAction").show();
    				$("th#thAction1").show();
    	    } 
    		 if($("#status").val() == "all"){
    				
    				$("th#thAction").hide();
    				$("th#thAction1").hide();
    	   } 
    		 
    	   $("#searchInput").on("keyup", function() {
     			var value = $(this).val().toLowerCase();
     			$("#PrfReport tbody tr").filter(function() { 
     			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
     			});
     		});

    	
  	      $.ajaxSetup({
				    async: false
				});
  	   
  	    if('${nodal_dte1}' != null && '${nodal_dte1}' != "")
		     { 
  	    	 $.post("getnodal_dirList?"+key+"="+value).done(function(j) {
		 	   	 var length = j.length-1;
					var enc = j[length].columnName1.substring(0,16);
		    		 var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
		    			for ( var i = 0; i < j.length-1; i++) {	
		    				if('${nodal_dte1}' == dec(enc,j[i].columnCode))
		    				{
		    					options += '<option value="'+dec(enc,j[i].columnCode)+'" selected="selected">'+dec(enc,j[i].columnName)+'</option>';
		    				}
		    				else
		    				{
		    					options += '<option value="'+dec(enc,j[i].columnCode)+'">'+dec(enc,j[i].columnName)+'</option>';
		    				}
		    			}	
		    			$("select#nodal_dte").html(options);
		 	      }).fail(function(xhr, textStatus, errorThrown) { }); 
		    } 
	        else{
		 
	        	$.post("getnodal_dirList?"+key+"="+value).done(function(j) {
	   	    	 var length = j.length-1;
	   	 		var enc = j[length].columnName1.substring(0,16);		 
	   	 		  var options = '<option value="">'+ "--Select--" + '</option>'; 
	   	 			for ( var i = 0; i < j.length-1; i++) {
	   	 				 options += '<option value="' + dec(enc,j[i].columnCode)+ '" >'+ dec(enc,j[i].columnName)+ '</option>';  					
	   	 			}	
	   	 			$("select#nodal_dte").html(options);  
	   	      }).fail(function(xhr, textStatus, errorThrown) { }); 

	        }
			$.ajaxSetup({
			    async: false
			});
			  if('${coss_section1}' != null && '${coss_section1}' != "")
		     { 
				  $.post("getcoss_sectionList?"+key+"="+value).done(function(j) {
			 	    	var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
		    			for ( var i = 0; i < j.length; i++) {	
		    				if('${coss_section1}' == j[i])
		    				{
		    					options += '<option value="'+j[i]+'" selected="selected">'+ j[i]+ '</option>';
		    				}
		    				else
		    				{
		    					options += '<option value="'+j[i]+'">'+ j[i]+ '</option>';
		    				}
		    			}	
		    			$("select#coss_section").html(options); 
			 	      }).fail(function(xhr, textStatus, errorThrown) { }); 
		    }
			  else{
				  $.post("getcoss_sectionList?"+key+"="+value).done(function(j) {
			 	    	 var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>'; 
							for ( var i = 0; i < j.length; i++) {
								 options += '<option value="' +j[i]+ '" >'+ j[i]+ '</option>';  					
							}	
							$("select#coss_section").html(options);  
			 	      }).fail(function(xhr, textStatus, errorThrown) { }); 
			  }
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

function clearAll()
{
	document.getElementById('divPrint').style.display='none';	
	var tab = $("#PrfReport > tbody"); 
	tab.empty(); 
	document.getElementById("printId").disabled = true;

	$("#searchInput").val("");
	$("div#divSerachInput").hide();
	//document.location.reload();
 	localStorage.clear();
	localStorage.Abandon();
}
    	   
     
     function Search(){
    	   	
       
  	   $("#coss_section1").val($("#coss_section").val());
         $("#prf_group1").val($("#prf_group").val());
         $("#nodal_dte1").val($("#nodal_dte").val());
         $("#status1").val($("#status").val());
         document.getElementById('searchForm').submit();
      	   
  	}
     
     
     
       function Approved(id){
		   document.getElementById('appid').value=id;
		    $("#coss_section2").val($("#coss_section").val());
	         $("#prf_group2").val($("#prf_group").val());
	         $("#nodal_dte2").val($("#nodal_dte").val());
	         $("#status2").val($("#status").val());
			document.getElementById('appForm').submit();		   
	   }
	   
	  function Reject(id){
		  document.getElementById('rejectid').value=id;
		  $("#coss_section3").val($("#coss_section").val());
	         $("#prf_group3").val($("#prf_group").val());
	         $("#nodal_dte3").val($("#nodal_dte").val());
	         $("#status3").val($("#status").val());
			document.getElementById('rejectForm').submit();
	   }
	   
	
       /////////////////////////
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
       
       function blockSpecialChar(e){
           var k;
           document.all ? k = e.keyCode : k = e.which;
           return ((k > 64 && k < 91));
           }       
      
       function isvalidation(){
    	   
    	 /*   if($("input#prf_group").val()==""){
	    			alert("Please Enter PRF Group")
	    			$("input#prf_group").focus();
	    			return false;
	    		} */
    	   Search();    	
       }       
       
   </script>
