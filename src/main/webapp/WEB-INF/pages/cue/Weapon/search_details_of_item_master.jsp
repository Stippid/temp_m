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

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/cue/update.js" type="text/javascript"></script>
<script src="js/cue/printAllPages.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<script>
function printDiv() 
  	{
		var printLbl = ["PRF Group :"];
	 	var printVal = [document.getElementById('prf_group').value];
	 	printDivOptimize('divPrint','SEARCH DETAILS OF ITEM MASTER',printLbl,printVal,"select#status");
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
        
	  
	  $.ajaxSetup({
		    async: false
		});
	  var wepetext1=$("#item_type");
	 
	  wepetext1.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
	        type: 'POST',
		  	url: "getItemNameInMst?"+key+"="+value,
	        data: {item_type:document.getElementById('item_type').value},
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
	          } else {
	        	alert("Please Enter Approved Nomenclature");
	        	wepetext1.val("");
	        	wepetext1.focus();	        	
	        	return false;	             
	          }   	         
	      }	     
	    });

      });
</script>

<form action="" method="post" enctype="multipart/form-data" class="form-horizontal"> 
	<div class="animated fadeIn">
			<div class="container" align="center">
	    		<div class="card">
	          		<div class="card-header"> <h5><b>SEARCH DETAILS OF ITEM MASTER</b><br><span style="font-size: 10px;font-size:15px;color:red">(To be approved by MISO)</span></h5></div>
	          		<div class="card-body card-block cue_text">
	            		<div class="col-md-12">	  
	            		
	            		<div class="col-md-6">
	  						<div class="row form-group">	           					
             					<div class="col col-md-6">
               						<label class=" form-control-label">PRF Group </label>
             					</div>
             					<div class="col-12 col-md-6">
               						<input  id="prf_group" name="prf_group" class="form-control"  autocomplete="off" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search">
             					</div>
             				</div>
             				</div>	           					
	              			<div class="col-md-6">	
	              			<div class="row form-group">              			 	
               					<div class="col col-md-6">
	                  						<label class=" form-control-label">Nomenclature <strong style="color: red;">*</strong></label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                  						<input  id="item_type" name="item_type" class="form-control"  style="font-family: 'FontAwesome',Arial;text-transform:uppercase" placeholder="&#xF002; Search" ></input>
	                					</div>	 							
	  						</div>
	  						</div>
	  						           					
	  					</div>	
	  					
	  					<div class="col-md-12">	              					
	              			
	  						<div class="col-md-6">
	              			<div class="row form-group">	              			 	
               					<div class="col col-md-6">
                 					<label for="text-input" class=" form-control-label">Item Group </label>
               					</div>
               					<div class="col-12 col-md-6">
									<select name="item_group" id="item_group" class="form-control">
										<option value="0">--Select--</option>
                 						<c:forEach var="item" items="${getitemgroup}" varStatus="num" >
           									<option value="${item[1]}" > ${item[1]}</option>
           								</c:forEach>
									</select>
								</div> 							
	  						</div>
	  						</div>
	  						<div class="col-md-6">
	              			<div class="row form-group">	              			 	
               					<div class="col col-md-6">
                 					<label for="text-input" class=" form-control-label">Class</label>
               					</div>
               					<div class="col-12 col-md-6">
               					
									<select name="class_item" id="class_item" class="form-control">
									<option value="0">--Select--</option>
                 						<c:forEach var="item" items="${getDomainListClassData}" varStatus="num" >
           									<option value="${item}"> ${item}</option>
           								</c:forEach>
									</select>
								</div> 							
	  						</div>
	  						</div>
	  					</div>	  
	  				
	  						<div class="col-md-12">	 
	  						<div class="col-md-6">
	              			<div class="row form-group">	              			 	
               					<div class="col col-md-6">
                 					<label for="text-input" class=" form-control-label">Status </label>
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
	              		<i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm" value="Search" onclick="Search();">
	              		<i class="fa fa-print"></i><input type="button" id="printId" class="btn btn-primary btn-sm btn_report" value="Print" onclick="printDiv();" disabled>
		            </div> 
	        	</div>
			</div>
	</div>
	
	
</form>
<div style="width: 20%; padding-left: 1%; display: none;" id="divSerachInput">
	<input id="searchInput" type="text" style="font-size:12px; font-family: 'FontAwesome',Arial;margin-bottom: 5px;" placeholder="&#xF002; Search Word"  size="35" class="form-control">
</div>
<div class="col s12" style="display: none;" id="divPrint">
	<div id="divShow" style="display: block;">
	</div>
	
	 <div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
					<span id="ip"></span>
			<table id="getSearchReport" class="table no-margin table-striped  table-hover  table-bordered report_print" >
				<thead >
					<tr>
						<th class="tableheadSer">Ser No</th>
						<th >Prf Group</th>
						<th >Item Code</th>
						<th >Nomenclature</th>
						<th >Item Group</th>
						<th >Class</th>
						<th id="thLetterId" style="display: none;">Letter No</th>
						<th id="thRemarkId" style="display: none;">Error Correction</th>
						<th id="thAction" class='lastCol' >Action</th>
					
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${list}" varStatus="num" >
						<tr>
							<td class="tableheadSer">${num.index+1}</td>
							<td >${item.prf_group}</td>
							<td >${item.item_code}</td>
							<td >${item.item_type}</td>
							<td >${item.item_group}</td>
							<td >${item.class_item}</td>
							<td id="thAction1" class='lastCol'>${item.id}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>	
	</div>

	<c:url value="search_item_master1" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="prf_group1">
		<input type="hidden" name="prf_group1" id="prf_group1" value="0"/>
		<input type="hidden" name="item_code1" id="item_code1" value="0"/>
		<input type="hidden" name="item_type1" id="item_type1" value="0"/>
		<input type="hidden" name="item_group1" id="item_group1" value="0"/>
		<input type="hidden" name="class_item1" id="class_item1" value="0"/>
		<input type="hidden" name="status1" id="status1" value="0"/>
		<input type="hidden" name="category_code1" id="category_code1" value="0"/>
	</form:form>
    		
	<c:url value="ApprovedItemArmUrl" var="appUrl" />
	<form:form action="${appUrl}" method="post" id="appForm" name="appForm" modelAttribute="appid">
		<input type="hidden" name="appid" id="appid" value="0"/>
		<input type="hidden" name="prf_group2" id="prf_group2" value="0"/>
		<input type="hidden" name="item_type2" id="item_type2" value="0"/>
		<input type="hidden" name="item_group2" id="item_group2" value="0"/>
		<input type="hidden" name="class_item2" id="class_item2" value="0"/>
		<input type="hidden" name="status2" id="status2" value="0"/>
	</form:form> 
	
	<c:url value="rejectItemArmUrl" var="rejectUrl" />
	<form:form action="${rejectUrl}" method="post" id="rejectForm" name="rejectForm" modelAttribute="rejectid">
		<input type="hidden" name="rejectid" id="rejectid" value="0"/>
		
		<input type="hidden" name="prf_group3" id="prf_group3" value="0"/>
		<input type="hidden" name="item_type3" id="item_type3" value="0"/>
		<input type="hidden" name="item_group3" id="item_group3" value="0"/>
		<input type="hidden" name="class_item3" id="class_item3" value="0"/>
		<input type="hidden" name="status3" id="status3" value="0"/>
	</form:form> 

<script type="text/javascript">

$(document).ready(function() { 
	
	if('${status1}' != ""){
    			$("#getSearchReport").show();
    			$("#status").val('${status1}');
    			$("#divPrint").show();
    			$("#item_type").val('${item_type1}');
    			$("#item_group").val('${item_group1}');
    			$("#class_item").val('${class_item1}');
    			document.getElementById("printId").disabled = false;	
    			$("div#divwatermark").val('').addClass('watermarked'); 
    	    	watermarkreport();
    	    	$("div#divSerachInput").show();
    	    	
    	    	if('${list.size()}' == 0 ){
    	    		$("div#divSerachInput").hide(); 
    	    		document.getElementById("printId").disabled = true;	
    	    		$("table#getSearchReport").append("<tr><td colspan='7' style='text-align :center;'>Data Not Available</td></tr>");
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
			$("#getSearchReport tbody tr").filter(function() { 
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
		 	
	}
	catch (e) {
		// TODO: handle exception
	}
});
		function Search(){
			/* if($("input#item_type").val() == "")
		  	{
		  		alert("Please enter Nomenclature");
		  		$("input#item_type").focus();
		  		return false;
		  	}  */
			$("#prf_group1").val($("#prf_group").val());
		    $("#item_type1").val($("#item_type").val());
		    $("#item_group1").val($("#item_group").val());
		    $("#class_item1").val($("#class_item").val());
		    $("#status1").val($("#status").val());
		    document.getElementById('searchForm').submit();
		 	   
			}
function clearAll()
{	
	document.getElementById('divPrint').style.display='none';
	document.getElementById("printId").disabled = true;
	 var tab = $("#getSearchReport > tbody"); 
	tab.empty(); 
	$("#searchInput").val("");
	$("div#divSerachInput").hide();
	//document.location.reload();
 	localStorage.clear();
	localStorage.Abandon();
}


</script>

<script>
      
function Approved(id){
	document.getElementById('appid').value=id;
	$("#prf_group2").val($("#prf_group").val());
	$("#item_type2").val($("#item_type").val());
	$("#item_group2").val($("#item_group").val());
	$("#class_item2").val($("#class_item").val());
	$("#status2").val($("#status").val());
	document.getElementById('appForm').submit();
 }
	   
function Reject(id){
	document.getElementById('rejectid').value=id;
	$("#prf_group3").val($("#prf_group").val());
	$("#item_type3").val($("#item_type").val());
	$("#item_group3").val($("#item_group").val());
	$("#class_item3").val($("#class_item").val());
	$("#status3").val($("#status").val());
	document.getElementById('rejectForm').submit();
}
</script>
