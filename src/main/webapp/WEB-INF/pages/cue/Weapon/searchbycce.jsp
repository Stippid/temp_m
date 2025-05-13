<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/excluded/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 

<script src="js/JS_CSS/jquery.dataTables.min.js"></script>
<script src="js/JS_CSS/dataTables.bootstrap.min.js"></script>
<script src="js/cue/wepe_cce.js" type ="text/javascript"></script>
<script type="text/javascript" src="js/miso/miso_js/jquery-1.12.3.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/cue/update.js" type="text/javascript"></script>
<script src="js/cue/printAllPages.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>

<script>
function printDiv() 
{
	var printLbl = ["Type :"];
	var printVal = [$('input:radio[name=ces_cces]:checked').val()];
	printDivOptimize('divPrint','Search Complete Equipment Schedule',printLbl,printVal,"select#status");
}
</script>

<form action="" method="post" enctype="multipart/form-data" class="form-horizontal" > 
	<div class="animated fadeIn">
			<div class="container" align="center">
	    		<div class="card">
	    			<div class="card-header"><h5><b>SEARCH Complete Equipment Schedule</b><br><span style="font-size: 10px;font-size:15px;color:red">(To be approved by MISO)</span></h5></div>
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
				                                  <input type="radio" id="inline-radio1" name="ces_cces" value="CES" class="form-check-input" onchange="clearDescription();getcceno(this.value);">CES
				                                </label>&nbsp;&nbsp;&nbsp;
				                                <label for="inline-radio2" class="form-check-label">
				                                  <input type="radio" id="inline-radio2" name="ces_cces" value="CCES" class="form-check-input" onchange="clearDescription();getcceno(this.value);">CCES
				                                </label>&nbsp;&nbsp;&nbsp;
				                                <label for="inline-radio3" class="form-check-label ">
				                                  <input type="radio" id="inline-radio3" name="ces_cces" value="ETS" class="form-check-input" onchange="clearDescription();getcceno(this.value);">ETS
				                                </label>&nbsp;&nbsp;&nbsp;   
				                              </div>
							             </div>					              
	                				</div>
	                				</div>
	                				<div class="col-md-6">
	              					  <div class="row form-group">
	              					    <div class="col col-md-6">
						                	<label for="text-input" class=" form-control-label">CES/CCES No</label>
						                </div>
          				                   <div class="col-12 col-md-6">
          				                    <input id="ces_ccestype" type="hidden" value=""> 
			                         			<input  id="ces_cces_no" name="ces_cces_no"  class="form-control" onchange="SerachIngetName(this.value);" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search" />
	                					</div>
	                				</div>
	                				</div>
	                	     </div>
	               		  <div class="col-md-12">	
	            			<div class="col-md-6">
              					<div class="row form-group">
                					<div class="col col-md-6">
                  						<label class=" form-control-label">Name of CES/CCES </label>
                					</div>
                					<div class="col-12 col-md-6">
                						<input class="form-control" id="nomenClature" name="nomenClature" autocomplete="off" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search">
                					</div>
                				</div>
              				</div>				
	              			<div class="col-md-6">
	              			 	<div class="row form-group"> 
	                					<div class="col col-md-6">
	                  						<label  class=" form-control-label" >Item Code </label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                  						<input id="item_seq_no" name="item_seq_no" class="form-control" autocomplete="off" readonly="readonly">	                  						
										</div>
	  								</div> 
	  						</div>
	  					</div>
				  </div>	
					<div class="card-footer" align="center">
						<input type="reset" class="btn btn-success btn-sm" value="Clear" onclick="clearAll();">   
	              		<i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm" onclick="Search();" value="Search">
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
					<table id="AttributeReportCCES" class="table no-margin table-striped  table-hover  table-bordered report_print" >
						<thead >
							<tr> 
								<th class="tableheadSer">Ser No</th>
								<th >CCES No</th>	
								<th >Name of CES/CCES</th>				
								<th >Effective Date</th>
								<th >Expiry Date</th>				 
								<th >Nomenclature Stores</th>
								<th >Proposed Auth</th>
								<th >Remarks</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach var="item" items="${list}" varStatus="num" >
								<tr>
									<td class="tableheadSer">${num.index+1}</td>
									<td >${item.ces_cces_no}</td>
									<td >${item.ces_namem}</td>
									<td >${item.efctiv_date}</td>
									<td >${item.expiry_date}</td>
									<td >${item.sub_item_seq}</td>
									<td >${item.auth_proposed}</td>
									<td >${item.remarks}</td>
									
								</tr>
			</c:forEach></tbody>
					</table>
				</div>	
				</div>
	<c:url value="search_miso_cue_comp_equip_schedule1" var="searchUrl" />
    		<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="prf_group1">
    			<input type="hidden" name="ces_namem1" id="ces_namem1" value="0"/>
    			<input type="hidden" name="ces_cces1" id="ces_cces1" value="0"/>
    			<input type="hidden" name="ces_cces_no1" id="ces_cces_no1" value="0"/>
    			<input type="hidden" name="item_seq_no1" id="item_seq_no1" value="0"/>
    			<input type="hidden" name="status1" id="status1" value="0"/>
    		</form:form>


<script>
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
		        	  document.getElementById("nomenClature").value="";       	 
		        	  wepetext2.focus();
		        	  return false;	             
		          }   	         
		      }, 
		        select: function( event, ui ) {
		        	$(this).val(ui.item.value);    	        	
		        	 $.post("getitemnamefromcode?"+key+"="+value, {val : ui.item.value}).done(function(j) {
		    	    		if(j != "" && j != null)
				        	{
			            		document.getElementById("nomenClature").value=j;
				        	}
				        	else
				        	{
				        		document.getElementById("nomenClature").value="";
				        	}
			    	      }).fail(function(xhr, textStatus, errorThrown) { }); 
      	
		        }  	     
		    });
		  
		  
		  $.ajaxSetup({
	  	    async: false
	  	});  
		  
		  var wepetext1=$("#nomenClature");     
		 
		  wepetext1.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
	        	type: 'POST',
	 	        url: "getitemtype?"+key+"="+value,
		        data: {item_type:document.getElementById('nomenClature').value},
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
		        	  alert("Please Enter Approved Nomenclature");
		        	  wepetext1.val("");
		        	  $("input#std_nomclature").val("");
		        	  document.getElementById("item_seq_no").value="";
		        	 
		        	  wepetext1.focus();
		        	  return false;	             
		          }   	         
		      }, 
		      select: function( event, ui ) {
		        	$(this).val(ui.item.value);    	        	
		        	$.post("getitemcode?"+key+"="+value, {val : ui.item.value}).done(function(j) {
	    	    		if(j != "" && j != null)
		        		{
	            			document.getElementById("item_seq_no").value=j;	
		        		}
		        	else
		        	{
		        		document.getElementById("item_seq_no").value="";	
		        	}
	    	      }).fail(function(xhr, textStatus, errorThrown) { });	      	
		        }  	     
		    });  
	  
	  }); 

$(document).ready(function() {
	if('${ces_cces1}' != "")
	{
		$("#AttributeReportCCES").show();
		$("input[name=ces_cces][value="+'${ces_cces1}'+"]").prop('checked', true);
		$("#ces_cces_no").val('${ces_cces_no1}');
		$("#nomenClature").val('${ces_namem1}');
		$("#item_seq_no").val('${item_seq_no1}');
		getcceno('${ces_cces1}');
		$("div#divwatermark").val('').addClass('watermarked'); 
		watermarkreport();
		$("#divPrint").show();
		 $("div#divSerachInput").show();
		document.getElementById("printId").disabled = false;	
		
		if('${list.size()}' == 0 ){
			 $("div#divSerachInput").hide(); 
			 document.getElementById("printId").disabled = true;
			$("table#AttributeReportCCES").append("<tr><td colspan='9' style='text-align :center;'>Data Not Available</td></tr>");
		 }
		}
	
	$("#searchInput").on("keyup", function() {
			var value = $(this).val().toLowerCase();
			$("#AttributeReportCCES tbody tr").filter(function() { 
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
			else if(window.location.href.includes("count="))
			{
				var url = window.location.href.split("?count")[0];
				window.location = url;
			}
				
		}
		catch (e) {
			// TODO: handle exception
		} 
});

function Search(){
   var r =  $('input:radio[name=ces_cces]:checked').val();	
	  if(r == undefined)
	  {		 
		    alert("Please select CES Type");
		    $('input:radio[name=ces_cces]:checked').focus();
			return false;
	  }
  	 
     $("#ces_cces1").val(r);
     $("#ces_cces_no1").val($("#ces_cces_no").val());
     $("#ces_namem1").val($("#nomenClature").val());
     $("#item_seq_no1").val($("#item_seq_no").val());
    $("#status1").val($("#status").val());
    document.getElementById('searchForm').submit();
 	   
	}
	
function clearAll()
{	document.getElementById('divPrint').style.display='none';
	var tab = $("#AttributeReportCCES > tbody");
 	tab.empty(); 	
 	document.getElementById("printId").disabled = true;
 	$("#searchInput").val("");
 	$("div#divSerachInput").hide();
 	//document.location.reload();
 	localStorage.clear();
	localStorage.Abandon();
}

function clearDescription(){
	 document.getElementById('ces_cces_no').value = "";
	 document.getElementById('nomenClature').value = "";
	 document.getElementById('item_seq_no').value = "";
}

</script>
