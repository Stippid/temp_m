<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>  

<script src="js/miso/miso_js/jquery-1.12.3.js"></script>

<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>

<script src="js/cue/update.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>

<script src="js/cue/printAllPages.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<script>
	
	 var wepe ="${wepe}"; 
	
</script>
 <script type="text/javascript">
$(document).ready(function () {
	document.getElementById("getTypeOnclick").value=wepe;

});
</script> 

<script>
function printDiv() 
  	{
	 	var printLbl = ["Type :"];
	 	var printVal = [$('input:radio[name=we_pe]:checked').val()];
	 	printDivOptimize('divPrint','SEARCH WE/PE TITLES',printLbl,printVal,"select#status");
 	 }
</script>
</head>

<body>	
<form:form  action="" method="post"  class="form-horizontal" commandName=""  enctype="multipart/form-data">
<div class="container card_outer" align="center">

	        	<div class="card">
	        		<div class="card-header"> <h5><b> WE/PE List</b><br><span style="font-size: 10px;font-size:15px;color:red">(To vet UE Data by Line Dte)</span></h5></div>
	          			<div class="card-body card-block cue_text">
						<div class="col-md-12">
						<div class="col-md-6">
           					<div class="row form-group">
				                	<div class="col col-md-6">
				                	<input type="hidden" name="getroleid" id="getroleid" value="${roleid}"/>
				                		<label for="text-input" class=" form-control-label">Type of Document <strong style="color: red;">*</strong></label>
				                	</div>
									  <div class="col col-md-6">
			                              <div class="form-check-inline form-check">
			                                <label for="inline-radio1" class="form-check-label ">
			                                  <input type="radio" id="we_pe1" name="we_pe" value="WE" class="form-check-input">WE
			                                </label>&nbsp;&nbsp;&nbsp;
			                                <label for="inline-radio2" class="form-check-label ">
			                                  <input type="radio" id="we_pe2" name="we_pe" value="PE" class="form-check-input">PE
			                                </label>&nbsp;&nbsp;&nbsp;
			                                <label for="inline-radio3" class="form-check-label ">
			                                  <input type="radio" id="we_pe3" name="we_pe" value="FE" class="form-check-input">FE
			                                </label>&nbsp;&nbsp;&nbsp;
			                                <label for="inline-radio4" class="form-check-label ">
			                                  <input type="radio" id="we_pe4" name="we_pe" value="GSL" class="form-check-input">GSL
			                                </label>&nbsp;&nbsp;&nbsp;
			                              </div>
					                  </div>
							</div>
	            		 </div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
             					<div class="row form-group">
               					<div class="col col-md-6">
                 						<label class=" form-control-label">MISO WE/PE No</label>
               					</div>
               					<div class="col-12 col-md-6">
                 						<input  id="we_pe_no" name="we_pe_no"  class="form-control " autocomplete="off" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search" >
                 						<input type="hidden" name="getTypeOnclick" id="getTypeOnclick" value="${wepe}"/> 
               					</div>
               					</div>
              				</div>
              				<div class="col-md-6">
             					<div class="row form-group">
               					<div class="col col-md-6">
                 						<label class=" form-control-label">WE/PE Title </label>
               					</div>
               					<div class="col-12 col-md-6">
                 						<input  id="table_title" name="table_title" placeholder="" class="form-control"   autocomplete="off" readonly="readonly" >
               					</div>
               					</div>
             				</div>             				 
						</div>	
							
						<div class="col-md-12">	       
						  <div class="col-md-6">
             				 <div class="row form-group">
               				     <div class="col col-md-6">
                 						<label for="text-input" class=" form-control-label">Arm/Service</label>
               					</div>
               					<div class="col-12 col-md-6">
                 						<select  id="arm"  name="arm" class="form-control">
<!--                  						<option value="0">--Select--</option> -->
											${selectArmNameList}
                 						<c:forEach var="item" items="${getArmNameList}" varStatus="num" >
           									<option value="${item[0]}" > ${item[1]}</option>
           								</c:forEach>
                 						
                 						</select>
								</div>
 							</div>
 							</div>							
							       <div class="col-md-6">
       					<div class="row form-group">
         					<div class="col col-md-6">
           						<label class=" form-control-label">Sponsor Directorate</label>
         					</div>
         					<div class="col-12 col-md-6">
									 <select  class="form-control" id ="sponsor_dire" name ="sponsor_dire">	
					                 ${selectLine_dte}
	              						<c:forEach var="item" items="${getLine_DteList}" varStatus="num" >
                  								<option value="${item.line_dte_sus}"  name="${item.line_dte_name}" >${item.line_dte_name}</option>
                  							</c:forEach>
	                 					                                                  
		                            </select>
         					</div>
         				</div>
             			</div>  
						</div>	 
 					</div>
					<div class="card-footer" align="center">
						<input type="reset" class="btn btn-success btn-sm" value="Clear" onclick="clearall();">   
	              		<i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm" onclick="Search();" value="Search">
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
							<table id="SearchWePeSuperReport" class="table no-margin table-striped  table-hover  table-bordered report_print" >
								<thead >
									<tr>
										<th class="tableheadSer">Ser No</th>
										<th>Type of Document</th>
										<th>MISO WE/PE No</th>
										<th>WE/PE Title</th>
										<th>Sponsor Dte</th>
										<th>Arm/Service</th>
										<th>Vetted By</th>
										<th>Vetted On</th>
<!-- 										<th>From Date</th> -->
<!-- 										<th>To Date</th> -->
										<th>View Data</th>				
									</tr>
								</thead>
								<tbody>
								<c:forEach var="item" items="${list}" varStatus="num" >
								<tr>
									<td class="tableheadSer">${num.index+1}</td>
									<td >${item.we_pe}</td>
									<td >${item.we_pe_no}</td>
									<td >${item.table_title}</td>
									<td >${item.line_dte_name}</td>
									<td >${item.arm_desc}</td>
									<td >${item.vetted_by}</td>
									<td >${item.vetted_on}</td>
<%-- 									<td >${item.eff_frm_date}</td> --%>
<%-- 									<td >${item.eff_to_date}</td> --%>
									<td>${item.id}</td>
								</tr>
								</c:forEach>
								</tbody>
							</table>
						</div>	
			</div>


		<c:url value="WePecondition_data1" var="searchUrl" />
    		<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="we_pe_no1">
    			<input type="hidden" name="we_pe01" id="we_pe01" value="0"/>
    			<input type="hidden" name="we_pe_no1" id="we_pe_no1" value="0"/>
    			<input type="hidden" name="table_title1" id="table_title1" value="0"/>
    			<input type="hidden" name="sponsor_dire1" id="sponsor_dire1" value="0"/>
    			<input type="hidden" name="arm_desc1" id="arm_desc1" value="0"/>
    			<input type="hidden" name="setTypeOnclick1" id="setTypeOnclick1" value="0"/>
    		</form:form> 	
	
	<c:url value="add_moreUrl_line_dte" var="add_moreUrl" />
	<form:form action="${add_moreUrl}" method="post" id="add_moreForm" name="add_moreForm" modelAttribute="add_moreid">
		<input type="hidden" name="add_moreid" id="add_moreid" value="0"/>
		<input type="hidden" name="we_pe_no2" id="we_pe_no2" value="0"/>
		<input type="hidden" name="add_moreType" id="add_moreType" value=""/>
		<input type="hidden" name="arm" id="arm" value="0"/>
		<input type="hidden" name="spdir" id="spdir" value=""/>
	</form:form>
	
	<c:url value="update_WE_PE_conditionUrl" var="updateUrl" />
	<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid" target="result">
		<input type="hidden" name="updateid" id="updateid" value=""/>
		<input type="hidden" name="updateType" id="updateType" value=""/>
	</form:form> 
	
 	<div class="modal fade" id="rejectModal" tabindex="-1" role="dialog" aria-labelledby="rejectModalLabel" aria-hidden="true" data-backdrop="static">
    	<div class="modal-dialog">
      		<div class="modal-content">
		        <div class="modal-header">
		          <h4 class="modal-title">Rejection Remarks/Reason</h4>
		          <button type="button" class="close" data-dismiss="modal">&times;</button>
		        </div>
		        
        <div class="modal-body">
        	<div class="form-group">	 
				<div class="col-md-12">			
				<div class="row" style="color: maroon; font-size: 16px; font-weight: bold;">
					<div class="col-sm-6">				 
				  		<input id="rejectid_model" name="rejectid_model" class="form-control" type ="hidden" >
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
						<textarea id="reject_remarks" name="reject_remarks" maxlength="255" class="form-control"></textarea>
			   		</div>
		         	<div class="col-sm-6 form-group" id="divletter" style="display: none;">							 
						<label for="text-input" class=" form-control-label">Reject Letter No</label>
						<input id="reject_letter_no" name="reject_letter_no" maxlength="50" class="form-control">
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
			<button type="button" class="btn btn-danger" data-dismiss="modal"  onclick="$('#rrr').attr('data-target','#')">Close</button>
        </div>
	</div>
	</div>
	</div>
	
	<script>
       $(document).ready(function() {debugger;
    	   
//     	   if('${status1}' != "")
//     		{
    			$("input[name=we_pe][value="+'${we_pe01}'+"]").prop('checked', true);
    			$("#setTypeOnclick").val('${wepe}');
    			$("#we_pe_no").val('${we_pe_no1}');
    			getArmByWePeNo('${we_pe_no1}');
    			$("#status").val('${status1}');
    			$("#uploaded_wepe").val('${uploaded_wepe1}');
    	//		$("#sponsor_dire").val('${sponsor_dire1}');
    	//		$("#arm").val('${arm_desc1}');
    			$("#divPrint").show();
    			$("div#divwatermark").val('').addClass('watermarked'); 
    			watermarkreport();
    			 $("div#divSerachInput").show(); 
    			
    			if('${list.size()}' == 0 ){
    	    		   $("div#divSerachInput").hide(); 
    	    		   $("table#SearchWePeSuperReport").append("<tr><td colspan='10' style='text-align :center;'>Data Not Available</td></tr>");
    	           }
//     		}
    			
//     		if($("#status").val() == "2"){
//     			$("th#thLetterId").show();
//     			$("th#thRemarkId").show();
//     			$("td#thLetterId1").show();
//     			$("td#thRemarkId1").show();
//     			$("th#thAction").show();
// 	        } 
// 	    	 if($("#status").val() == "all"){
//     			$("th#thLetterId").show();
//     			$("th#thRemarkId").show();
//     			$("td#thLetterId1").show();
//     			$("td#thRemarkId1").show();
//     			$("th#thAction").hide();
//     			$("td#thAction1").hide();
// 	       } 
    		
    	   $("#searchInput").on("keyup", function() {
     			var value = $(this).val().toLowerCase();
     			$("#SearchWePeSuperReport tbody tr").filter(function() { 
     			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
     			});
     		});
    	   
    	    var r =  $('input:radio[name=we_pe]:checked').val();	
    	   	if(r != undefined)
    	    	getWePeNoByType(r);	
    	   	
    	    $.ajaxSetup({
    	        async: false
    	    });
  				  var wepetext=$("#uploaded_wepe");

  				  wepetext.autocomplete({
  				      source: function( request, response ) {
  				        $.ajax({
  				        type: 'POST',
  			        	url: "getUploadedDocCondiByNoSearch?"+key+"="+value,
  				        data: {uploaded_wepe:document.getElementById('uploaded_wepe').value},
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
  				        	  alert("Please Enter Approved Uploaded WE/PE No");
  				        	  wepetext.val("");	        	  
  				        	  wepetext.focus();
  				        	  return false;	             
  				          }   	         
  				      }, 
  				        	     
  				    });
    		   
    		   
    		   $("input[type='radio'][name='we_pe']").click(function(){
    				var we_pe1 = $("input[name='we_pe']:checked").val();
    				document.getElementById("we_pe_no").value="";
    				document.getElementById("table_title").value="";
    				$("#uploaded_wepe").val("");
    				getWePeNoByType(we_pe1);
    		   });
    		   
    	   try{
    		   var type = "" ;
   			type = document.getElementById("getTypeOnclick").value;		
   			document.getElementById("getroleid").value=type;
    	 
    		
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
				else if(window.location.href.includes("add_moreid="))
				{
					var url = window.location.href.split("?add_moreid")[0];
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

       });
       function Update(id){	
   		var x = screen.width/2 - 1100/2;
   	    var y = screen.height/2 - 900/2;
   	   // window.open(meh.href, 'sharegplus','height=485,width=700,left='+x+',top='+y);
   	    
   		  newWin = window.open("", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');
   		 
   		 window.onfocus = function () { 
   		 }
   		
   		 document.getElementById('updateid').value=id;
   		   document.getElementById('updateType').value=document.getElementById("getTypeOnclick").value;
   		   document.getElementById('updateForm').submit();
   	}
       function closeWindow()
       {
       	newWin.close();   
       }
       function Delete1(id,superno){
    	   alert(superno);
      
   		$.post("delete_WE_PE_conditionUrlAdd?"+key+"="+value, {deleteid : id, deleteType: document.getElementById("getTypeOnclick").value,superno : superno}).done(function(j) {
   		 	alert(j);
   			Search();
   		}).fail(function(xhr, textStatus, errorThrown) { });
   		 
   	}
       function clearall()
       {	
    	document.getElementById('divPrint').style.display='none';	
       	var tab = $("#SearchWePeSuperReport > tbody");
       	tab.empty();
       	$("select#sponsor_dire").val("0");
       	$("select#arm").val("0");
       	$("#searchInput").val("");
       	$("div#divSerachInput").hide();
      //document.location.reload();
     	localStorage.clear();
    	localStorage.Abandon();
       }
       
       function getWePeNoByType(we_pe1)
       {
       	 if(we_pe1 != ""){
       	 var wepetext=$("#we_pe_no");
       
       	  wepetext.autocomplete({
       	      source: function( request, response ) {
       	        $.ajax({
       	        type: 'POST',
 	        	url: "getWePeCondiByNoSearch?"+key+"="+value,
       	        data: {we_pe : we_pe1 , newRoleid: document.getElementById("getroleid").value,we_pe_no:document.getElementById('we_pe_no').value},
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
       	        	document.getElementById("table_title").value="";
       	        	wepetext.focus();
       	        	return false;	             
       	          }   	         
       	      }, 
              select: function( event, ui ) {
             	$(this).val(ui.item.value);    	        	
             	getArmByWePeNo($(this).val());	        	
              }  	     
       	    });
       	 }
       	 else
       		 alert("Please select Document");       	
       }

       function getArmByWePeNo(val)
       {
       	 if(val != "" && val != null)
       	  {
	       	 $.post("getDetailsByWePeCondiNo?"+key+"="+value, {val : val}).done(function(j) {
	       		if(j!="" && j!=null)
	       		 {
	       			document.getElementById("table_title").value=j[0].table_title;
	       		 }
	       			 else
	       				 {
	       				 document.getElementById("table_title").value="";
	       				 }
	       			
	       	}).fail(function(xhr, textStatus, errorThrown) { }); 
       	  }
       	  else
       	  {
       		  document.getElementById("table_title").value="";
       	  }
       }
   </script>
<script >

 function Approved(id,superno,nwepe,type,Pstatus){
	 
var copy="no";
 if (superno != ""){
	 
 if(Pstatus!="2"){
	 
		if(confirm("Do you want to copy data?"))
		{
			copy="yes";
		}
 }}
	 document.getElementById('appid').value=id;
	   document.getElementById('appType').value=document.getElementById("getTypeOnclick").value;
	   var we_pe1 = $("input[name=we_pe]:checked").val();
	   $("#we_pe02").val(we_pe1);
	   $("#we_pe_no2").val($("#we_pe_no").val());
	   $("#uploaded_wepe2").val($("#uploaded_wepe").val());
	   $("#suprcdd_we_pe_no2").val($("#suprcdd_we_pe_no").val());
	   $("#table_title2").val($("#table_title").val());
	   $("#sponsor_dire2").val($("#sponsor_dire").val());
	   $("#arm_desc2").val($("#arm").val());
	   $("#doc_type2").val($("#doc_type").val());
	   $("#setTypeOnclick2").val('${wepe}');
	   $("#getroleid2").val($("#getroleid").val());
	   $("#status2").val($("#status").val());
	   $("#superno2").val(superno);
	   $("#nwepe2").val(nwepe);
	   $("#type2").val(type);
	   $("#copy2").val(copy);
	   $("#statusp").val(Pstatus);
		document.getElementById('appForm').submit();
	
	
	}


 function Reject(id){
	  document.getElementById('rejectid_model').value=id;
	  cleardata();	  
} 

</script>

<script>
function Search(){
	var r =  $('input:radio[name=we_pe]:checked').val();	
	if(r == undefined)
	{		 
		alert("Please Select WE/PE");
		return false;
	}
   $("#we_pe01").val(r);
   $("#we_pe_no1").val($("#we_pe_no").val());
   $("#table_title1").val($("#table_title").val());
   $("#sponsor_dire1").val($("#sponsor_dire").val());
   $("#arm_desc1").val($("#arm").val());
   $("#setTypeOnclick1").val('${wepe}');
   document.getElementById('searchForm').submit();
}

function AddMoreData(id,wepeno,spdir,arm){	debugger;

 
$("#we_pe_no2").val(wepeno);
  document.getElementById('add_moreid').value=id;
   document.getElementById('add_moreType').value=document.getElementById("getTypeOnclick").value;
   $("#arm").val(arm);
   $("#spdir").val(spdir);
   document.getElementById('add_moreForm').submit();
}

	
// function AddMoreData(id){	debugger;
// 	var x = screen.width/2 - 1100/2;
//     var y = screen.height/2 - 900/2;
    
// 	  newWin = window.open("", 'result', 'height=900,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');
	 
// 	 window.onfocus = function () { 
// 		 // newWin.close();
// 	 }
	
// 	//window.open('', 'result', 'width=500,height=500');
// 	  document.getElementById('add_moreid').value=id;
// 	   document.getElementById('add_moreType').value=document.getElementById("getTypeOnclick").value;
// 	   document.getElementById('add_moreForm').submit();
// }

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
		
		 $.post("updaterejectaction?"+key+"="+value, {remarks : remarks,letter_no : letter_no,id:id}).done(function(j) {
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
	  	var inputs = document.getElementsByName("chk_model");  //document.querySelectorAll('chk_model');
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