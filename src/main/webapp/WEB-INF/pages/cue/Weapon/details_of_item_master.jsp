<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/excluded/jquery-2.1.3.min.js"></script>
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

<script>
function printDiv() 
  	{
	 	var printLbl = ["PRF Group :", "COS Section :", "Item Code :"];
	 	var printVal = [document.getElementById('prf_group').value,document.getElementById('cos_sec_no').value,document.getElementById('item_code').value];
	 	printDivOptimize('divPrint','DETAILS OF ITEM MASTER',printLbl,printVal,"select#status");
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
	        	alert("Please Enter Approved PRF Group");
	        	wepetext.val("");
	        	wepetext.focus();
	        	$("#cos_sec_no").val(""); 	
	 			$("#prf_group_code").val(""); 	
	 		   	document.getElementById("item_code").value = "";
	        	return false;	             
	          }   	         
	      }, 
    select: function( event, ui ) {
    	$(this).val(ui.item.value);    	        	
    	getprfDetails($(this).val());	        	
     }  	     
	    });

      });
</script>


<form:form  action="item_masterAction" method='POST' commandName="item_masterCmd" > 
	<div class="animated fadeIn">
			<div class="container" align="center">
	    		<div class="card">
	          		<div class="card-header"><h5><b>DETAILS OF ITEM MASTER</b><br><span style="font-size: 10px;font-size:15px;color:red">(To be entered by MISO)</span></h5></div>
	          		<div class="card-body card-block cue_text">
	            		<div class="col-md-12">	  
	            		
	            		<div class="col-md-6">
	  						<div class="row form-group">	           					
             					<div class="col col-md-6">
               						<label class=" form-control-label">PRF Group <strong style="color: red;">*</strong></label>
             					</div>
             					<div class="col-12 col-md-6">
               						<input  id="prf_group" name="prf_group"  class="form-control" maxlength="255" autocomplete="off" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search">
               						<input type="hidden" id="prf_group_code" name="prf_group_code" maxlength="8" class="form-control"  >
             					</div>
             				</div>
             				</div>	           					
	              			<div class="col-md-6">	
	              			<div class="row form-group">              			 	
               					<div class="col col-md-6">
                 					<label for="text-input" class=" form-control-label">COS Section</label>
               					</div>
               					<div class="col-12 col-md-6">
                 					<input  id="cos_sec_no" name="cos_sec_no" maxlength="5" class="form-control" readonly="readonly"  >
								</div>
							</div>	 							
	  						</div>
	  						           					
	  					</div>	
	  					
	  					<div class="col-md-12">	              					
	              			<div class="col-md-6">	
	              			<div class="row form-group">              			 	
               					<div class="col col-md-6">
	                  						<label class=" form-control-label">Nomenclature <strong style="color: red;">*</strong></label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                  						<input  id="item_type" name="item_type" maxlength="255" class="form-control" style="text-transform:uppercase" onkeypress="return blockSpecialChar(event)">
	                					</div>	 							
	  						</div>
	  						</div>
	              			<div class="col-md-6">	
	              			<div class="row form-group">              			 	
               					<div class="col col-md-6">
                 					<label for="text-input" class=" form-control-label">Item Code</label>
               					</div>
               					<div class="col-12 col-md-6">
                 					<input  id="item_code" name="item_code" maxlength="8" class="form-control" readonly="readonly"> 
								</div>
							</div>	 							
	  						</div>
	  					</div>
	  					
	  					<div class="col-md-12">	              					
	              			<div class="col-md-6">
	              			<div class="row form-group">	              			 	
               					<div class="col col-md-6">
                 					<label for="text-input" class=" form-control-label">Item Group <strong style="color: red;">*</strong></label>
               					</div>
               					<div class="col-12 col-md-6">
									<select name="item_group" id="item_group" class="form-control">
									<option value="0">--Select--</option>
                 						<c:forEach var="item" items="${getitemgroup}" varStatus="num" >
           									<option value="${item[0]}" > ${item[1]}</option>
           								</c:forEach>
									</select>
								</div> 							
	  						</div>
	  						</div>
	  						<div class="col-md-6">
							<div class="row form-group">           					
             					<div class="col col-md-6">
               						<label class=" form-control-label">Item Category <strong style="color: red;">*</strong></label>
             					</div>
             					<div class="col-12 col-md-6">
									<select name="category_code" id="category_code" class="form-control">
										<c:forEach var="item" items="${getitemcat}" varStatus="num" >
           									<option value="${item[1]}" > ${item[1]}</option>
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
                 					<label for="text-input" class=" form-control-label">Critical Equipment (Yes/No) <strong style="color: red;">*</strong></label>
               					</div>
               					<div class="col-12 col-md-6">
									<select name="critical_equipment" id="critical_equipment" class="form-control">
										<option value="Yes">Yes</option>
										<option value="No">No</option>
									</select>
								</div> 							
	  						</div>
	  						</div>
	  						<div class="col-md-6">
							<div class="row form-group">           					
             					<div class="col col-md-6">
               						<label class=" form-control-label">Broad Category of Stores <strong style="color: red;">*</strong></label>
             					</div>
             					<div class="col-12 col-md-6">
									<select name="engr_stores_origin" id="engr_stores_origin" class="form-control">
									<option value="0">--Select--</option>
                 						<c:forEach var="item" items="${getbroadcat}" varStatus="num" >
           									<option value="${item[1]}" > ${item[1]}</option>
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
                 					<label for="text-input" class=" form-control-label">Accounting Unit <strong style="color: red;">*</strong></label>
               					</div>
               					<div class="col-12 col-md-6">
									<select name="acc_units" id="acc_units" class="form-control">
										<option value="0">--Select--</option>
                 						<c:forEach var="item" items="${getaccunit}" varStatus="num" >
           									<option value="${item[1]}" > ${item[1]}</option>
           								</c:forEach>
									</select>
								</div> 							
	  						</div>
	  						</div>
	  						
	  						<div class="col-md-6">
	              			<div class="row form-group">	              			 	
               					<div class="col col-md-6">
                 					<label for="text-input" class=" form-control-label">Class <strong style="color: red;">*</strong></label>
               					</div>
               					<div class="col-12 col-md-6">
									<select name="class_item" id="class_item" class="form-control">
									<option value="0">--Select--</option>
                 						<c:forEach var="item" items="${getDomainListClassData}" varStatus="num" >
           									<option value="${item}" > ${item}</option>
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
                 					<label for="text-input" class=" form-control-label">Nodal Dte <strong style="color: red;">*</strong></label>
               					</div>
               					<div class="col-12 col-md-6">
								<select name="nodal_dte" id="nodal_dte" class="form-control" >	
	           							<option value="0">--Select--</option>
										<c:forEach var="item" items="${getLine_DteList}" varStatus="num">
										<option value="${item.line_dte_sus}"  name="${item.line_dte_name}" >${item.line_dte_name}</option>
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
               						<label class=" form-control-label">Remarks</label>
               					</div>
               					<div class="col-12 col-md-6">
               						<textarea  id="remarks" name="remarks" maxlength="255" class="form-control char-counter1"></textarea>
               					</div>	 							
	  						</div>
	  						</div>
	  					</div>
	  						</div>
	        	
	        	<div class="card-footer" align="center">
						<input type="reset" class="btn btn-success btn-sm" value="Clear" onclick="clearall();">   
	              		<input type="submit" class="btn btn-primary btn-sm" value="Save" onclick="return isValidation();">
	              		<i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm" onclick="Search();" value="Search">
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
			<table id="getSearchReport" class="table no-margin table-striped  table-hover  table-bordered report_print" >
				<thead >
					<tr>
						<th class="tableheadSer">Ser No</th>
						<th >Prf Group</th>
						<th >Item Code</th>
						<th >Nomenclature</th>
						<th >Item Group</th>
						<th >Class</th>
						<th >Nodal Dte</th>
						<th class='lastCol'>Action</th>
					</tr>
				</thead>
				<tbody>
				
					<c:forEach var="item" items="${list}" varStatus="num" >
								<tr>
									<td class="tableheadSer">${num.index+1}</td>
									<td >${item.prf_group}</td>
									<td >${item.item_code}</td>
									<td >${item.item_type}</td>
									<td >${item.label}</td>
									<td >${item.class_item}</td>
									<td >${item.line_dte_name}</td>
									<td id="thAction1" class='lastCol'>${item.id}</td>
								</tr>
							</c:forEach>
						</tbody>
			</table>
		</div>
	</div>
	
	<c:url value="item_master1" var="searchUrl" />
    		<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="prf_group1">
    			<input type="hidden" name="prf_group1" id="prf_group1" value="0"/>
    			<input type="hidden" name="item_code1" id="item_code1" value="0"/>
    			<input type="hidden" name="item_type1" id="item_type1" value="0"/>
    			<input type="hidden" name="item_group1" id="item_group1" value="0"/>
    			<input type="hidden" name="class_item1" id="class_item1" value="0"/>
    			<input type="hidden" name="status1" id="status1" value="0"/>
    			<input type="hidden" name="category_code1" id="category_code1" value="0"/>
    		</form:form>

<c:url value="updateItemArmUrl" var="updateUrl" />
	<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid"  target="result">
		<input type="hidden" name="updateid" id="updateid" value="0"/>
	</form:form>
	<script>
	function Search(){
		/*if($("input#prf_group").val()==""){
			alert("Please Enter PRF Groups ");
			$("input#prf_group").focus();
			return false;
		}*/
		$("#prf_group1").val($("#prf_group").val());
	    $("#item_code1").val($("#item_code").val());
	    $("#item_type1").val($("#item_type").val());
	    $("#item_group1").val($("#item_group").val());
	    $("#class_item1").val($("#class_item").val());
	    $("#category_code1").val($("#category_code").val());
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
	$.post("deleteItemArmUrlAdd?"+key+"="+value, {deleteid : id}).done(function(j) {
		alert(j);
		Search();
	  }).fail(function(xhr, textStatus, errorThrown) { }); 	
}	
					
</script>

<script type="text/javascript">
$(document).ready(function() {
	if('${prf_group1}' != "" && '${prf_group1}' != "" )
	{
		$("#prf_group").val('${prf_group1}');
		getprfDetails('${prf_group1}');
		$("#class_item").val('${class_item1}');
		$("#category_code").val('${category_code1}');
	}
			
	if('${list.size()}' == 0 ){
		$("div#divSerachInput").hide(); 
		document.getElementById("printId").disabled =true;
		$("table#getSearchReport").append("<tr><td colspan='7' style='text-align :center;'>Data Not Available</td></tr>");
	}else{
		$("#divPrint").show();
		$("div#divwatermark").val('').addClass('watermarked'); 
		watermarkreport();
		$("div#divSerachInput").show();
		document.getElementById("printId").disabled = false;
	}
	
	$("#searchInput").on("keyup", function() {
			var value = $(this).val().toLowerCase();
			$("#getSearchReport tbody tr").filter(function() { 
			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
			});
		});
	
	 $('#remarks').keyup(function() {
	        this.value = this.value.toUpperCase();
	    });
	 
	try{
		if(window.location.href.includes("msg="))
		{
			var url = window.location.href.split("?msg=")[0];
		var m=  window.location.href.split("?msg=")[1];
		window.location = url;
		
		if(m.includes("Updated+Successfully")){
			window.opener.getRefreshReport('item_mst_weap',m);
			window.close('','_parent','');
		}
		}
	}
	catch (e) {
		// TODO: handle exception
	}
	
	});
 
function getprfDetails(val)
{
	if( val != "" && val != null){ 
		 $.post("getcosno?"+key+"="+value, {prf_group: val}).done(function(j) {
 		for ( var i = 0; i < j.length; i++) {
 			$("#cos_sec_no").val(j[i].coss_section); 	
 			$("#prf_group_code").val(j[i].prf_group_code); 	
 			var cos = j[i].coss_section;
 			$.post("getitemcodeUrl?"+key+"="+value, {cos:cos}).done(function(j) {
 		   	     document.getElementById("item_code").value = j;
 			  }).fail(function(xhr, textStatus, errorThrown) { }); 	
 		}	
		  }).fail(function(xhr, textStatus, errorThrown) { }); 	
	}
}

function blockSpecialChar(e){
	 $('#item_type').keyup(function() {
	        this.value = this.value.toUpperCase();
	    });
} 

function clearall()
{		
	document.getElementById('divPrint').style.display='none';
	document.getElementById("printId").disabled = true;
	 var tab = $("#getSearchReport");
     tab.empty();
     $("#searchInput").val("");
     $("#searchInput").hide();
  	localStorage.clear();
 	localStorage.Abandon();
}

function isValidation()
{
	
	if($("input#prf_group").val() == "")
	{
		alert("Please enter PRF Group");
		$("input#prf_group").focus();
		return false;
	}
	 if($("input#item_type").val() == "")
	{
		alert("Please enter Nomenclature");
		$("input#item_type").focus();
		return false;
	} 
	  if($("select#item_group").val() == "0")
	{
		alert("Please select Item Group");
		$("select#item_group").focus();
		return false;
	}  
	if($("select#engr_stores_origin").val() == "0")
	{
		alert("Please select Broad Category of Stores");
		$("select#engr_stores_origin").focus();
		return false;
	}
	if($("select#acc_units").val() == "0")
	{
		alert("Please select Accountng Unit");
		$("select#acc_units").focus();
		return false;
	}
	if($("select#class_item").val() == "0")
	{
		alert("Please select class");
		$("select#class_item").focus();
		return false;
	}
	
	if($("select#nodal_dte").val() == "ALL")
	{
		alert("Please select Nodal Dte");
		$("select#nodal_dte").focus();
		return false;
	}
	return true;
}
</script>
