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
$(document).ready(function() {
	
	if('${wet_link_status1}' != ""){
		$("#SearchReport").show();
		$("#wet_link_status").val('${wet_link_status1}');
		$("#divPrint").show();
		var we_pe3 = '${we_pe02}';
		$("input[name=we_pe1][value="+we_pe3+"]").prop('checked', true);
		$("#we_pe_no").val('${we_pe_no1}');
		$("#wet_pet_no").val('${wet_pet_no1}');
		$("div#divwatermark").val('').addClass('watermarked'); 
		watermarkreport();
		 $("div#divSerachInput").show();
		document.getElementById("printId").disabled = false;	
		
		 if('${list.size()}' == 0 ){
			 $("div#divSerachInput").hide();
			 document.getElementById("printId").disabled = true;
			 $("table#SearchReport").append("<tr><td colspan='9' style='text-align :center;'>Data Not Available</td></tr>");
		 }
			
	}
	if($("#wet_link_status").val() == "2"){
			$("th#thLetterId").show();
			$("th#thRemarkId").show();
			$("td#thLetterId1").show();
			$("td#thRemarkId1").show();
			$("th#thAction").show();
			$("td#thAction1").show();
    } 
	 if($("#wet_link_status").val() == "all"){
			$("th#thLetterId").show();
			$("th#thRemarkId").show();
			$("td#thLetterId1").show();
			$("td#thRemarkId1").show();
			$("th#thAction").hide();
			$("td#thAction1").hide();
   } 
	 
	$("#searchInput").on("keyup", function() {
			var value = $(this).val().toLowerCase();
			$("#SearchReport tbody tr").filter(function() { 
			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
			});
		});

 	  var r =  $('input[type=radio][name=we_pe1]:checked').val();	
	 	if(r != undefined)
	 		getWePeNoByType(r);	

 	  $("input[type='radio'][name='we_pe1']").click(function(){
 			var we_pe1 = $("input[name='we_pe1']:checked").val();
 			getWePeNoByType(we_pe1);
 	 }); 
	  
	  
	         $.ajaxSetup({
            async: false
        });
   		  
   		var wepetext1=$("#wet_pet_no");
   		
		  wepetext1.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        type: 'POST',
		  	    url: "getWetPetFetNo?"+key+"="+value,
   		        data: {val : "",wet_pet_no:document.getElementById('wet_pet_no').value},
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
		        	alert("Please Enter Approved WET/PET No");
		        	wepetext1.val("");
		        	wepetext1.focus();
		        	return false;	             
		          }   	         
		      }, 
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

function getWePeNoByType(we_pe1)
{
	 if(we_pe1 != ""){
		var wepetext=$("#we_pe_no");
		
		wepetext.autocomplete({
	    source: function( request, response ) {
	      $.ajax({
    	  type: 'POST',
          url: "getWePenumber?"+key+"="+value,
	       data: {type:we_pe1 ,we_pe_no : document.getElementById('we_pe_no').value},
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
      	document.getElementById("wet_pet_no").value="";
      	wepetext.focus();
      	return false;	             
        }   	         
    }, 
 select: function( event, ui ) {
	$(this).val(ui.item.value);    	        	
	getarmvalue($(this).val());	        	
 } 	     
  });
	 }
 	 else
 		 alert("Please select Document");
} 
 </script> 
 <script>
 function getarmvalue(val){
		if(val != "" && val != null)
		  {
	 	     $.post("getsuperseededvalue?"+key+"="+value, {val : val}).done(function(j) {
	 	    	 if(j != "" && j != null){			 
	 				document.getElementById("wet_pet_no").value=j[0].wet_pet_no;
	 				 }
	 				 else
	 				 {
	 					 document.getElementById("wet_pet_no").value="";
	 				 }
	 	      }).fail(function(xhr, textStatus, errorThrown) { }); 
		  }
		else
		{
			document.getElementById("wet_pet_no").value="";		
		}
	}


</script>
<script>
	function issearcWELinkWETvalid()
  	{
		var r =  $('input:radio[name=we_pe1]:checked').val();	
 	 	if(r == undefined)
 	 	{		 
 	    	alert("Please select Document");
 		    $('input:radio[name=we_pe1]:checked').focus();
 			return false;
 	  	} 
  		/* if($("input#we_pe_no").val() == "")
  		{
  			alert("Please enter WE/PE No");
  			$("input#we_pe_no").focus();
  			return false;
  		} */
  		Search();
  		return true;
	}
</script>
 
<form:form id="Search_link_we_wetAction" name="Search_link_we_wetAction" action="Search_link_we_wetAction" method="post"  class="form-horizontal" commandName="link_we_wetActionCMD">
	<div class="animated fadeIn">
			<div class="container" align="center">
	    		<div class="card">
	    			<div class="card-header"><h5><b>SEARCH Linked UPLOAD WE/PE TO UPLOAD WET/PET/FET</b><br>
	    			<!-- <span style="font-size: 10px;font-size:15px;color:red">(To be approved by sd dte)</span> -->
	    			</h5></div>
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
								
	        			
								<div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-6">
	                  						<label class=" form-control-label">WE/PE Document No </label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                  					     <input type="text" id="we_pe_no" name="we_pe_no" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search" class="form-control" autocomplete="off" >
	                					</div>
	              					</div>
	              				
							   </div>
							   </div>
							   <div class="col-md-12" >
							   <div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-6">
	                  						<label class=" form-control-label">WET/PET/FET No</label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                  					     <input type="text" id="wet_pet_no" name="wet_pet_no" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search" class="form-control" autocomplete="off">
	                					</div>
	              					</div>
	   					      </div>
	   					      
	   					      
	   					      <div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-6">
	                  						<label class=" form-control-label">Status</label>
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
						<input type="reset" class="btn btn-success btn-sm" value="Clear" onclick="clearall()">   
	              		<i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm" onclick="return issearcWELinkWETvalid();" value="Search">
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
			<table id="SearchReport" class="table no-margin table-striped  table-hover  table-bordered report_print" >
				 <thead>
					<tr>
						<th class="tableheadSer">Ser No</th>
						<th >WE/PE</th>
						<th >MISO WE/PE No</th>
						<th >WET/PET/FET Table Title</th>
						<th >WET/PET/FET No</th>
						<th >Unit Visibility</th>
						<th id="thLetterId" style="display: none;">Letter No</th>
						<th id="thRemarkId" style="display: none;">Error Correction</th>
						<th id="thAction" class='lastCol' >Action</th>
					</tr>
				</thead> 
				<tbody>
				<c:forEach var="item" items="${list}" varStatus="num" >
					<tr>
						<th class="tableheadSer">${num.index+1}</th>
						<th >${item.we_pe}</th>
						<th >${item.we_pe_no}</th>
						<th >${item.table_title}</th>
						<th >${item.wet_pet_no}</th>
						<th >${item.unit_visible}</th>
						<th id="thLetterId1" style="display: none;">${item.reject_letter_no}</th>
						<th id="thRemarkId1" style="display: none;">${item.reject_remarks}</th>
						<th id="thAction1"  class='lastCol'>${item.id}</th>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
		
		
	<c:url value="search_we_link_wet1" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="we_pe_no1">
		<input type="hidden" name="we_pe02" id="we_pe02" value="0"/>
		<input type="hidden" name="we_pe_no1" id="we_pe_no1" value="0"/>
		<input type="hidden" name="wet_pet_no1" id="wet_pet_no1" value="0"/>
		<input type="hidden" name="wet_link_status1" id="wet_link_status1" value="0"/>
	</form:form> 
    		
	<c:url value="Approved_WE_linkwith_WET_Url" var="appUrl" />
	<form:form action="${appUrl}" method="post" id="appForm" name="appForm" modelAttribute="appid">
		<input type="hidden" name="appid" id="appid" value="0"/>
		<input type="hidden" name="we_pe03" id="we_pe03" value="0"/>
    	<input type="hidden" name="we_pe_no2" id="we_pe_no2" value="0"/>
    	<input type="hidden" name="wet_link_status2" id="wet_link_status2" value="0"/>
	</form:form> 
	
	<c:url value="reject_WE_linkwith_WET_Url" var="rejectUrl" />
	<form:form action="${rejectUrl}" method="post" id="rejectForm" name="rejectForm" modelAttribute="rejectid">
		<input type="hidden" name="rejectid" id="rejectid" value="0"/>
		<input type="hidden" name="we_pe04" id="we_pe04" value="0"/>
    	<input type="hidden" name="we_pe_no4" id="we_pe_no4" value="0"/>
    	<input type="hidden" name="wet_link_status4" id="wet_link_status4" value="0"/>
	</form:form> 

<script>
function Search(){
	var we_pe2 = $("input[name='we_pe1']:checked").val();
	$("#we_pe02").val(we_pe2);
	$("#we_pe_no1").val($("#we_pe_no").val());
	$("#wet_pet_no1").val($("#wet_pet_no").val());
	$("#wet_link_status1").val($("#wet_link_status").val());
	document.getElementById('searchForm').submit();
}
function printDiv() 
{
 	var printLbl = ["WE/PE Document :", "WE/PE Document No :"];
 	var printVal = [$('input:radio[name=we_pe1]:checked').val(),document.getElementById('we_pe_no').value];
 	printDivOptimize('divPrint','SEARCH Link UPLOAD WE/PE TO UPLOAD WET/PET/FET',printLbl,printVal,"select#status");
}
</script>

<script>

function clearall()
{	document.getElementById('divPrint').style.display='none';
	var tab = $("#SearchReport > tbody");
	tab.empty();
	document.getElementById("printId").disabled = true;
	$("#searchInput").val("");
	$("div#divSerachInput").hide();
	//document.location.reload();
 	localStorage.clear();
	localStorage.Abandon();
}

function clearDiscription()
{	
	document.getElementById("wet_pet_no").value="";
	 document.getElementById("we_pe_no").value="";
}

    	   function Approved(id){
    		   document.getElementById('appid').value=id;
    		   var we_pe2 = $("input[name='we_pe1']:checked").val();
    		    $("#we_pe03").val(we_pe2);
     		    $("#we_pe_no2").val($("#we_pe_no").val());
     			$("#wet_link_status2").val($("#wet_link_status").val());
    			document.getElementById('appForm').submit();    		   
    	   }
    	   
    	   function Reject1(id){  			
  			   document.getElementById('rejectid').value=id;
  			 var we_pe2 = $("input[name='we_pe1']:checked").val();
 		    $("#we_pe04").val(we_pe2);
  		    $("#we_pe_no4").val($("#we_pe_no").val());
  			$("#wet_link_status4").val($("#wet_link_status").val());
  				document.getElementById('rejectForm').submit();
  		} 
    	   
</script>

<script>    
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
        return ((k > 64 && k < 91) || (k > 96 && k < 123) || k == 8 || k == 32 || k == 40 || k== 41 || k== 38 || k == 45 || k == 44);
        }
    
</script>

