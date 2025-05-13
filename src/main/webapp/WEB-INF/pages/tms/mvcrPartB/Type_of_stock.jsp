<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<script type="text/javascript" src="js/printWatermark/common.js"></script>


<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js"></script>
<script src="js/cue/printAllPages.js" type="text/javascript"></script>

<form:form name="type_of_stock" id="type_of_stock" action="type_of_stockAction" method="post" class="form-horizontal" commandName="type_of_stockCMD">
	<div class="animated fadeIn">
		<div class="">
			<div class="container" align="center">
				<div class="card">
					<div class="card-header" align="center"><strong><h5>TYPE OF STOCK</h5></strong></div>
					<div class="card-body card-block" id="mainViewSelection" style="display: block;">
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label"> <strong style="color: red;">*</strong>Type
											of Stock
										</label>
									</div>
									<div class="col-12 col-md-8">
										<select name="stock" id="stock"
											class="form-control-sm form-control">
											<option value="0">Free Stock</option>
											<option value="1">WWR</option>
											<option value="2">Issue Reserve</option>									
										</select>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">* </strong>Depot SUS No</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="sus_no" name="sus_no" placeholder="DEPOT SUS No" class="form-control autocomplete"
											autocomplete="off" maxlength="8">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">* </strong> Nomenclature</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="hidden" id="app"> 
										<select id="std_nomclature" name="std_nomclature" class="form-control-sm form-control">
											<option value="0">--Select--</option>
										</select>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">* </strong>Depot Name</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="depot_name" name="depot_name" placeholder="DEPOT Name" class="form-control autocomplete" 
										autocomplete="off" maxlength="100">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">MCT </label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="mct" name="mct" class="form-control autocomplete" autocomplete="off" readonly="readonly" maxlength="10">
									</div>
								</div>

							</div>
						</div>
						
					</div>
					<div class="form-control card-footer" align="center" id="btnhide" style="display: block;">
						<a href="type_of_stock" class="btn btn-success btn-sm" >Clear</a>
						<button type="button" class="btn btn-success btn-sm" onclick="return Search();">Search</button>
					</div>
				</div>
				
	
			
		</div>
	</div>
			
	    		<div class="container" align="center" id="tableshow">
	    		<div align="right"><h6>Total Count : ${list.size()}</h6></div>
						<div id="divShow" style="display: block;">
						</div> 							                  
					<div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
								<span id="ip"></span>
		<table id="StockReport" class="table no-margin table-striped  table-hover  table-bordered report_print" width="100%">
			<thead>
				<tr>
					<th style="text-align:center;">Ser No</th>
					<th style="text-align:center;">BA No</th>
					<th style="text-align:center;">Engine No</th>
					<th style="text-align:center;">Chassis No</th>
					<th style="text-align:center;">Type of Stock</th>
					<th style="text-align:center;">Remarks</th>
					<th style="text-align:center;">Action</th>
				</tr>
			</thead>
			<tbody style="text-align: center;">
					<c:if test="${list.size() == 0}" >
						<tr>
							<td colspan="7" align="center" style="color: red;"> Data Not Available </td>
						</tr>
					</c:if>
						<c:forEach var="item" items="${list}" varStatus="num" >
									<tr>									
									<td>${num.index+1}</td> 									 									
									<td>${item[0]}</td> 
									<td>${item[1]}</td>
									<td>${item[2]}</td>
									<td>${item[5]}</td> 	
									<td>${item[6]}</td> 
									<td>${item[7]}</td> 																		
									</tr>
								</c:forEach>								
						</tbody>
						</table>
						</div>
					</div>
				
</div>

	<div id="PrintViewSelection" style="display: none;">
		<div class="col-md-12">
			<div class="row form-group">
				<div class="col col-md-12">
					<label class=" form-control-label">Depot Sus No : </label> <label
						id="lbldepot_no"></label>
				</div>
			</div>
			<div class="row form-group">
				<div class="col col-md-12">
					<label class=" form-control-label">Depot Name : </label> <label
						id="lbldepot_name"></label>
				</div>
			</div>
			<div class="row form-group">
				<div class="col col-md-12">
					<label class=" form-control-label">MCT : </label> <label
						id="lblmct"></label>
				</div>
			</div>
			<div class="row form-group">
				<div class="col col-md-12">
					<label class=" form-control-label">Std Nomenclature: </label> <label
						id="lblstd_nomclature"></label>
				</div>
			</div>
			<div class="row form-group">
				<div class="col col-md-12">
					<label class=" form-control-label">Type of Stock: </label> <label
						id="lblstock_type"></label>
				</div>
			</div>
		</div>
	</div>
	
</form:form>

<c:url value="getTypeofstock" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="code_value1">
		<input type="hidden" name="sus_no1" id="sus_no1" value="0"/>
		<input type="hidden" name="mct1" id="mct1" value="0"/>
		<input type="hidden" name="type_of_stock1" id="type_of_stock1" value="0"/>
		<input type="hidden" name="depot_name1" id="depot_name1" value="0"/>
		<input type="hidden" name="std_nomclature1" id="std_nomclature1" value="0"/>
	
	</form:form> 

<c:url value="Update" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="updateForm"
	name="updateForm" modelAttribute="updateid">
	<input type="hidden" name="ba_no" id="ba_no" value="0" />
	<input type="hidden" name="type_of_stock" id="type_of_stock" value="0" />
	<input type="hidden" name="updateid" id="updateid" value="0" />
</form:form>
<script>
//---------------------------- Print View ---------------------------------------------------------
function getView()
{
	if(document.getElementById('PrintViewSelection').style.display=='none') 
	{ 
		document.getElementById('PrintViewSelection').style.display='block'; 
		document.getElementById('mainViewSelection').style.display='none'; 
		document.getElementById('btnhide').style.display='none';
	} 
	else
	{
		document.getElementById('mainViewSelection').style.display='block'; 
	}
	 window.print();
}
</script>
<script>

function Search(){
	$("#sus_no1").val($("#sus_no").val()) ;
	$("#mct1").val($("#mct").val()) ;
	$("#type_of_stock1").val($("#stock").val()) ;
	$("#depot_name1").val($("#depot_name").val()) ;
	$("#std_nomclature1").val($("#std_nomclature").val()) ;
    $("#printable").hide();
    
  /*   if($("#sus_no").val() == "")
    {
    	alert("Please Enter the Depot SUS No.");
    }
    else if  ($("#std_nomclature").val() == "0")
    {
    	alert("Please select Std Nomclature.");
    }
    else
    { */
	document.getElementById('searchForm').submit();
   // }
}
 
function update(id){
	var type_of_stock = $("select#type_of_stock"+id).val();
	$.ajax({
        type: 'POST',
        url: "Updatestock?"+key+"="+value,
        data: {type_of_stock:type_of_stock , id:id },
   		success: function(response) {
   			Search()
   			alert(" Data Updated Successfully.");
        }
    }); 
} 
</script>

<script>
$(document).ready(function() {
	$("div#divwatermark").val('').addClass('watermarked'); 
	watermarkreport();
	
	if('${sus_no}' == "" || '${std_nomclature}' == "0")
	{
		$("#tableshow").hide();
	}
	else
	{
		$("#stock").val('${type_of_stock}');
		${type_of_stock1}
		$("#tableshow").show();
		$("#sus_no").val('${sus_no}');
		var sus_no = '${sus_no}';
		$("#mct").val('${mct}');
		$("#depot_name").val('${depot_name}');
      	var unit_name=$("#depot_name").val();
    
    		 	$.post("getTargetSUSFromUNITNAME?"+key+"="+value, {target_unit_name:unit_name}).done(function(j) {				
    		 		var length = j.length-1;
    		        var enc = j[length].substring(0,16);
    		        $("#sus_no").val(dec(enc,j[0]));
    		      
    		    		 	$.post("getMCT_NomenList?"+key+"="+value, {sus_no:dec(enc,j[0]),stock:$("select#stock").val()}).done(function(j) {				
    		    		 		var options = '<option value="0" >--select--</option>';
    		    				for ( var i = 0; i < j.length; i++) {
    		    					if(j[i] == '${std_nomclature}'){
    		    			    		options += '<option value="' + j[i]	+ '" name="' + j[i]+ '" selected="selected" >' + j[i] + '</option>';
    		    			    	}
    		    			    	else
    		    			    	{
    		    			    		options += '<option value="'+j[i]+'" name="' + j[i]+ '" >'+ j[i] + '</option>';   
    		    			    	}
    		    			    }	   
    		    			    $("select#std_nomclature").html(options); 
    		    			}).fail(function(xhr, textStatus, errorThrown) {
    		    			});
    			}).fail(function(xhr, textStatus, errorThrown) {
    		});
     } 	    
}); 

$(function() {
			// Source SUS No
	$("#sus_no").keypress(function(){
		var sus_no = this.value;
	 	var susNoAuto=$("#sus_no");
	  	susNoAuto.autocomplete({
	      	source: function( request, response ) {
	        	$.ajax({
	        		type: 'POST',
	        		url: "getDepotSUSNoList?"+key+"="+value,
	        		data: {sus_no:sus_no},
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
	        		alert("Please Enter Approved SUS No.");
	        	  	document.getElementById("sus_no").value="";
	        	  	susNoAuto.val("");	        	  
	        	  	susNoAuto.focus();
	        	  	return false;	             
	          	}   	         
			}, 
	      	select: function( event, ui ) {
		    	var sus_no = ui.item.value;
		 
		    		 	$.post("getActiveUnitNameFromSusNo?"+key+"="+value, {sus_no:sus_no}).done(function(j) {				
		    		 		var length = j.length-1;
				        	var enc = j[length].substring(0,16);
				        	$("#depot_name").val(dec(enc,j[0]));	
		    			}).fail(function(xhr, textStatus, errorThrown) {
		    			});		
		 
		   			 	$.post("getMCT_NomenList?"+key+"="+value, {sus_no : sus_no,stock:$("select#stock").val()}).done(function(j) {				
							var options = '<option value="0" >--select--</option>';
				    			for ( var i = 0; i < j.length; i++) {
					    			if( j[i] == '${std_nomclature}')
					    			{
					    				options += '<option value="' + j[i]	+ '" name="' + j[i]+ '" selected="selected" >' + j[i] + '</option>';
							    	}
							    	else
							    	{
							    		options += '<option value="'+j[i]+'" name="' + j[i]+ '" >'+ j[i] + '</option>';   
							    	}
				    		}	   
				    		$("select#std_nomclature").html(options); 
		   				}).fail(function(xhr, textStatus, errorThrown) {
		   			});
			} 	     
		});	
	});
		// End
		
	// Source Unit Name
    $("#depot_name").keypress(function(){
			var unit_name = this.value;
		 	var susNoAuto=$("#depot_name");
		  	susNoAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        type: 'POST',
		        url: "getDepotUnitNameList?"+key+"="+value,
		        data: {unit_name:unit_name},
		          success: function( data ) {
	        	
	        	  var unitval = [];
	        	  var length = data.length-1;
	        	  var enc = data[length].substring(0,16);
		        	for(var i = 0;i<data.length;i++){
		        		unitval.push(dec(enc,data[i]));
		        	}
		        	response( unitval ); 
		          }
		        });
		      },
		      minLength: 1,
		      autoFill: true,
		      change: function(event, ui) {
		    	 if (ui.item) {   	        	  
		        	  return true;    	            
		          } else {
		        	  alert("Please Enter Approved Depot Name.");
		        	  document.getElementById("depot_name").value="";
		        	  susNoAuto.val("");	        	  
		        	  susNoAuto.focus();
		        	  return false;	             
		          }   	         
		      }, 
		      select: function( event, ui ) {
		    	  var target_unit_name = ui.item.value;
		    			 	$.post("getTargetSUSFromUNITNAME?"+key+"="+value, {target_unit_name:target_unit_name}).done(function(j) {				
		    			 		var length = j.length-1;
					        	var enc = j[length].substring(0,16);
					        	$("#sus_no").val(dec(enc,j[0]));
					        		 	$.post("getMCT_NomenList?"+key+"="+value, {sus_no:dec(enc,j[0]),stock:$("select#stock").val()}).done(function(j) {				
					        		 		var options = '<option value="0" >--select--</option>';
								    		for ( var i = 0; i < j.length; i++) {
								    			if( j[i] == '${std_nomclature}')
								    			{
								    				options += '<option value="' + j[i]	+ '" name="' + j[i]+ '" selected="selected" >' + j[i] + '</option>';
										    	}
										    	else
										    	{
										    		options += '<option value="'+j[i]+'" name="' + j[i]+ '" >'+ j[i] + '</option>';   
										    	}
								    		}	   
								    		$("select#std_nomclature").html(options); 	
					        			}).fail(function(xhr, textStatus, errorThrown) {
					        			});	
		    				}).fail(function(xhr, textStatus, errorThrown) {
		    		});
			     } 	     
			}); 			
		});
	
    try{ 
    if(window.location.href.includes("?"))
		{
			var url = window.location.href.split("?")[0];
			window.location = url;
		}
	}
	catch (e) {
	} 
});
</script>
<script>
	$('#std_nomclature').on('change', function() {
		var nomen = this.value;
	
			 	$.post("getNomclature?"+key+"="+value, {nomen:nomen}).done(function(j) {				
			 		var length = j.length-1;
			    	var enc = j[length].substring(0,16);
			    	$("#mct").val(dec(enc,j[0]));	
				}).fail(function(xhr, textStatus, errorThrown) {
			});
	});
	
	$('select#stock').on('change', function() {
		$("input#sus_no").val("");
		$("input#depot_name").val("");
		var options = '<option value="0" >--select--</option>';
		$("select#std_nomclature").html(options); 	
		$("input#mct").val("");
	});
</script>

<script>
function validate(){
	if($("#sus_no").val() == ""){
		alert("Please Enter the Depot SUS No.");
		$("#sus_no").focus();
		return false;
	}

	localStorage.setItem("sus_no", document.getElementById("sus_no").value);
	var e = document.getElementById("SelectLm");
	var strUser = e.options[e.selectedIndex].value;
	return true; 

}
function clear(){   
	document.getElementById("type_of_stock").reset();
}
</script>