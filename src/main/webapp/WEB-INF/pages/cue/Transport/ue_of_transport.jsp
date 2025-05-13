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

<div class="animated fadeIn" id="printableArea">
	<div class="">
		<div class="col-md-12" align="center">
			<div class="card">
				<div class="card-header" align="center">
					<h5> UE TPT REPORT</h5>
				</div>
				<div class="card-body card-block">
					<div class="col-md-12">
						<div class="col-md-2">
							<label for="text-input" class=" form-control-label">PRF
								Group</label>
						</div>

						<div class="col-md-2">
							<input type="text" id="from_prf_Search" name="from_prf_Search"
								placeholder="Search..." class="form-control-sm form-control"
								autocomplete="off" size="10" maxlength="10"
								title="Search PRF Name or part of PRF Name" />
						</div>

						<div class="col-md-1">
							<img src="js/miso/images/searchImg.jpg" width="30px;"
								height="30px;" onclick="getfromprf();" title="Click to Search"
								style="cursor: pointer;">
						</div>

						<div class="col-md-7">
							<select name="prf_code" id="prf_code"
								class="form-control-sm form-control" title="Select a PRF Group"
								disabled="disabled" onchange="select_nomenlist();">
								<option value="ALL">--All PRF Groups --</option>
								<c:forEach var="item" items="${m_11}">
									<option value="${item[0]}" name="${item[1]}">${item[0]}
										- ${item[1]}</option>
								</c:forEach>
							</select>
						</div>
					</div>

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label for="text-input" class="form-control-label">Veh
										Nomenclature</label>
								</div>
								<div class="col-12 col-md-8">
									<select name="std_nomclature[]" id="std_nomclature"
										class="form-control-sm form-control" multiple="multiple"></select>
								</div>
							</div>
						</div>
						
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label">Line Dte</label>
								</div>
								<div class="col-12 col-md-8">
									<select id="line_dte_sus" name="line_dte_sus"
										class="form-control-sm form-control">
										${selectLine_dte}
										<c:forEach var="item" items="${getLine_DteList}"
											varStatus="num">
											<option value="${item.line_dte_sus}"
												name="${item.line_dte_name}">${item.line_dte_name}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>

					</div>

					<!-- <div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
							    	<div class="col col-md-4">
							        	<label class=" form-control-label"> SUB CAT</label>
							       	</div>
							        <div class="col-12 col-md-8">
										<select id="mct_main"  class="form-control-sm form-control" style="width: 100%">
										</select>
									</div>
								</div>		
							</div>
						</div> -->



					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label"> Command</label>
								</div>
								<div class="col-12 col-md-8">
									<select id="cont_comd" name="cont_comd"
										class="form-control-sm form-control"> ${selectcomd}
										<c:forEach var="item" items="${getCommandList}"
											varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class="form-control-label"> Corps</label>
								</div>
								<div class="col-12 col-md-8">
									<select id="cont_corps" name="cont_corps"
										class="form-control-sm form-control"> ${selectcorps}
										<c:forEach var="item" items="${getCorpsList}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label"> Division</label>
								</div>
								<div class="col-12 col-md-8">
									<select id="cont_div" name="cont_div"
										class="form-control-sm form-control"> ${selectDiv}
										<c:forEach var="item" items="${getDivList}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label"> Brigade</label>
								</div>
								<div class="col-12 col-md-8">
									<select id="cont_bde" name="cont_bde"
										class="form-control-sm form-control"> ${selectBde}
										<c:forEach var="item" items="${getBdeList}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label">Unit Name </label>
								</div>
								<div class="col-12 col-md-8">
									<textarea id="unit_name" name="unit_name"
										class="form-control autocomplete" style="font-size: 12px;"
										autocomplete="off" maxlength="100">${unit_name}</textarea>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label">SUS No</label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="sus_no" value='${sus_no}' name="sus_no"
										class="form-control autocomplete" maxlength="8"
										autocomplete="off">
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<br>
						<%-- <div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label">Line Dte</label>
								</div>
								<div class="col-12 col-md-8">
									<select id="line_dte_sus" name="line_dte_sus"
										class="form-control-sm form-control">
										${selectLine_dte}
										<c:forEach var="item" items="${getLine_DteList}"
											varStatus="num">
											<option value="${item.line_dte_sus}"
												name="${item.line_dte_name}">${item.line_dte_name}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div> --%>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label">MISO WE/PE No </label>
								</div>
								<div class="col-12 col-md-8">
									<input type="hidden" id="id" name="id" class="form-control"
										value="0"> <input type="text" id="we_pe_no"
										name="we_pe_no" maxlength="100"
										style="font-family: 'FontAwesome', Arial;"
										placeholder="&#xF002; Search"
										class="form-control autocomplete" autocomplete="off">
								</div>
							</div>
						</div>
					
					
					
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label">Force Type</label>
								</div>
								<div class="col-12 col-md-8">
									<select id="type_force" name="type_force"
										class="form-control-sm form-control">
										<option value="">--Select--</option>
													<c:forEach var="item" items="${getTypeOfUnitList}">
														<option value="${item[1]}">${item[0]}- ${item[1]}</option>
													</c:forEach>
									</select>
								</div>
							</div>
						</div>
					</div>


					<!-- 					<div class="col-md-12"> -->
					<!-- 						<div class="col-md-6"> -->
					<!-- 							<div class="row form-group"> -->
					<!-- 								<div class="col-md-4"> -->
					<!-- 									<label class=" form-control-label">Effective From</label> -->
					<!-- 								</div> -->
					<!-- 								<div class="col-md-8"> -->
					<!-- 									<input type="text" name="eff_from" -->
					<!-- 										id="eff_from" maxlength="10" -->
					<!-- 										onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" -->
					<!-- 										style="width: 85%; display: inline;" -->
					<!-- 										onfocus="this.style.color='#000000'" -->
					<!-- 										onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" -->
					<!-- 										onkeyup="clickclear(this, 'DD/MM/YYYY')" -->
					<!-- 										onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true" -->
					<!-- 										autocomplete="off" style="color: rgb(0, 0, 0);"> -->
					<!-- 								</div> -->
					<!-- 							</div> -->
					<!-- 						</div> -->

					<!-- 						<div class="col-md-6"> -->
					<!-- 							<div class="row form-group"> -->
					<!-- 								<div class="col-md-4"> -->
					<!-- 									<label class=" form-control-label">Effective To</label> -->
					<!-- 								</div> -->
					<!-- 								<div class="col-md-8"> -->
					<!-- 									<input type="text" name="eff_to" -->
					<!-- 										id="eff_to" maxlength="10" -->
					<!-- 										onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" -->
					<!-- 										style="width: 85%; display: inline;" -->
					<!-- 										onfocus="this.style.color='#000000'" -->
					<!-- 										onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" -->
					<!-- 										onkeyup="clickclear(this, 'DD/MM/YYYY')" -->
					<!-- 										onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true" -->
					<!-- 										autocomplete="off" style="color: rgb(0, 0, 0);"> -->
					<!-- 								</div> -->
					<!-- 							</div> -->
					<!-- 						</div> -->
					<!-- 					</div> -->

					<div class="col-md-12">
						<!-- <div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label">MISO WE/PE No </label>
								</div>
								<div class="col-12 col-md-8">
									<input type="hidden" id="id" name="id" class="form-control"
										value="0"> <input type="text" id="we_pe_no"
										name="we_pe_no" maxlength="100"
										style="font-family: 'FontAwesome', Arial;"
										placeholder="&#xF002; Search"
										class="form-control autocomplete" autocomplete="off">
								</div>
							</div>
						</div> -->
						<!-- <div class="col-md-6" id="table_title_div">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label">Table Title</label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="table_title" name="table_title"
										class="form-control autocomplete" autocomplete="off">
								</div>
							</div>
						</div> -->
					</div>


					<!-- <div class="col-md-12">
						<div class="col-md-6" id="modification_div">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label">Modification</label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="modification" name="modification"
										maxlength="15" style="font-family: 'FontAwesome', Arial;"
										placeholder="&#xF002; Search"
										class="form-control autocomplete" autocomplete="off">
								</div>
							</div>
						</div>
					</div> -->

					<input type="hidden" id="table_title" name="table_title">
				</div>
				<div class="form-control card-footer" align="center" id="buttonDiv">
					<a href="ue_of_transport" class="btn btn-success btn-sm"
						style="border-radius: 5px;">Clear</a>
					<button class="btn btn-success btn-sm" id="btn-reload" value="List Data" style="border-radius: 5px;">List Data</button>
					<button class="btn btn-success btn-sm" id="btn_summ" style="border-radius: 5px;">Summary</button>
				</div>
				<div class="card-body">
					<div id="listDataDiv" >
						<div class="col-md-12">
							<div class="watermarked" data-watermark="" id="divwatermark">
								<span id="ip"></span>
								
								<table border="1" class="report_print" id="getVehicleStatusTbl">
									<thead style="text-align: center;">
										<tr>
											<th>Command</th>
											<th>Corps</th>
											<th>Division</th>
											<th>Brigade</th>
											<th>Unit Name</th>
											<th>MCT Nomenclature</th>
											<th>WE/PE/GSL Type</th>
											<th>WE/PE/GSL No</th>
											<th>Mod</th>
											<th>Base Auth</th>
											<th>Modification</th>
											<th>General Notes</th>
<!-- 										<th>Footnotes</th> -->
											<th>Total</th>
										</tr>
									</thead>
									<tbody></tbody>
								</table>
								
<!-- 								<table class="table no-margin table-striped table-hover table-bordered report_print" -->
<!-- 									style="margin-bottom: 0rem;"> -->
<!-- 									<tbody> -->
<!-- 										<tr> -->
<!-- 											<td colspan="4" style="width: 60%; text-align: center;"><B>Total</B></td> -->
<%-- 											<td colspan="1" align="left" style="width: 3%; text-align: center;"><B>${sumUE}</B></td> --%>
<!-- 										</tr> -->
<!-- 									</tbody> -->
<!-- 								</table> -->
								
							</div>
						</div>
						</div>
						<div id="summaryDiv" >
							<div class="col-md-12">
								<div class="watermarked" data-watermark="" id="divwatermark">
									<table border="1" class="report_print" id="getVehicleStatusTbl1">
										<thead style="text-align: center;">
											<tr>
												<th style="width:10%; text-align: center; ">MCT</th>
												<th style="width:50%; text-align: center;">Veh Nomenclature</th>
												<th style="width:10%; text-align: center;">FF</th>
												<th style="width:10%; text-align: center;">NFF</th>
												<th style="width:10%; text-align: center;">Reserve</th>
												<th style="width:10%; text-align: center;">Total UE</th>
											</tr>
										</thead>
										<tbody></tbody>
									</table>
								</div>
							</div>
						</div>
					

				</div>
			</div>
		</div>
	</div>

</div>
<div class="animated fadeIn" id="printDiv" style="display: none;">
	<div class="">
		<div class="container" align="center">
			<div class="col-md-12" align="center">
				<input type="button" class="btn btn-primary btn-sm" value="Print"
					onclick="printDiv('printableArea')">
			</div>
		</div>
	</div>
</div>

<script>

$(function() {
	 var wepetext1=$("#we_pe_no");
	 
	  wepetext1.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
       		type: 'POST',
	        url: "getWePeByNo?"+key+"="+value,
	        data: {we_pe_no : document.getElementById('we_pe_no').value},
	          success: function( data ) {
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
	        	  wepetext1.val("");
				  document.getElementById("table_title").value=""; 
	        	  wepetext1.focus();
	        	  return false;	             
	          }   	         
	      }, 
	        select: function( event, ui ) {
	        	$(this).val(ui.item.value); 
	        	getarmvalue($(this).val());
	        	var we_pe_no = ui.item.value;     
    		   	$.ajaxSetup({
    		   	    async: false
    		   	});
	        }  	     
	    });
});

function getarmvalue(val){
	
	  if(val != "" && val != null) {
		  $.post("getDetailsByWePeCondiNo?"+key+"="+value, {val : val}).done(function(j) {
			  document.getElementById("table_title").value=j[0].table_title;
		  }).fail(function(xhr, textStatus, errorThrown) { }); 
	  } else {
		 alert("Please enter WE/PE No");
		  document.getElementById("table_title").value="";
	  }
}

function getfromprf(){
	var nParaValue = $("#from_prf_Search").val();
	if(nParaValue.length<=0) {
		alert("Enter Search Word...");
	    return false;
	}else{
		 $.post("getTransportUEListBySearch?"+key+"="+value, {
			 nParaValue:nParaValue
    }, function(j) {
       
    }).done(function(j) {
	 if(j.length <= 0 || j == null || j == ''){
			alert("No Search Result Found");
			$("#from_prf_Search").focus();
		}else{
			$("#prf_code").attr('disabled',false);
			var options = '<option value="' + "ALL" + '">'+ "--All PRF Groups--" + '</option>';
			
			var a = [];
			var enc = j[j.length-1].substring(0,16);
			for(var i = 0; i < j.length; i++){
				a[i] = dec(enc,j[i]);
         		}
			
			var data=a[0].split(",");
			var datap;
			for ( var i = 0; i < data.length-1; i++) {
				datap=data[i].split(":");
				if (datap!=null) {
					if (datap[1].length>60) {
						options += '<option value="'+datap[0]+'" name="' + datap[1]+ '" >'+ datap[0]+ ' - '+ datap[1].substring(1,60)+ '</option>';
					} else {
						options += '<option value="'+datap[0]+'" name="' + datap[1]+ '" >'+ datap[0]+ ' - '+ datap[1]+ '</option>';
					}
				}
			}	
			$("select#prf_code").html(options); 
		
			var q = '${m_6}';
			if(q != ""){
				$("#prf_code").val(q);
				select_nomenlist();
			}
		}
     
}).fail(function(xhr, textStatus, errorThrown) {
});
	}
}

function select_nomenlist() {
    var prf_code = $("select#prf_code").val();
    $('select#std_nomclature').val(""); // Clear the selection
    var options = '<option value="0">' + "--Select--" + '</option>';

    if (prf_code != "") {
        $.post("getTransportUEprfcode?" + key + "=" + value, { prf_code: prf_code })
            .done(function(j) {
                for (var i = 0; i < j.length; i++) {
                    options += '<option value="' + j[i][0] + '" name="' + j[i][1] + '">' + j[i][0] + ' - ' + j[i][1] + '</option>';
                }
                $("select#std_nomclature").html(options);

                // Enable multiple selection by clicking
                $('#std_nomclature option').mousedown(function(e) {
                    e.preventDefault(); // Prevent the default behavior
                    $(this).prop('selected', !$(this).prop('selected')); // Toggle selected state
                    return false; // Prevent further propagation of the event
                });

            }).fail(function(xhr, textStatus, errorThrown) {
                console.error("Error: ", errorThrown);
            });
    } else {
        $("select#std_nomclature").html(options);
    }
}
    
$(function() {
	 var wepetext1=$("#modification");
	  wepetext1.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
	        type: 'POST',
	        url: "getTypeofModiList?"+key+"="+value,
	        data: {label:document.getElementById('modification').value},
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
	        	  alert("Please Enter Approved Modification");
	        	  wepetext1.val("");
	        	 wepetext1.focus();
	        	  return false;	             
	          }   	         
	      }, 
	    });
      });
    
$(function() {
	 var wepetext1=$("#mct_no");
	 
	  wepetext1.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
       	type: 'POST',
	        url: "getMctNoListOfPrf?"+key+"="+value,
	        data: {mct_main_id:document.getElementById('mct_no').value,prf_code:document.getElementById('prf_code').value},
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
	        	  alert("Please Enter Approved MCT No");
	        	  wepetext1.val("");
	        	  $("input#std_nomclature").val("");
	        	  wepetext1.focus();
	        	  return false;	             
	          }   	         
	      }, 
	      select: function( event, ui ) {
	        	$(this).val(ui.item.value);    	        	
	        	var mct = $(this).val(); 
         	$.ajaxSetup({
         	    async: false
         	});
                
         	var mct_no = this.value;
	           
         	$.post("getMctNoDetailsList?"+key+"="+value, {mct: mct_no}).done(function(j) {
           	$("input#std_nomclature").val(j);		
 		    }).fail(function(xhr, textStatus, errorThrown) { });	
          	
          	var we_pe_no = $("input#we_pe_no").val();
      	 	if(mct_no != '' && we_pe_no != '')
	   		{
      	 	$.post("getCheckDublicateMCT_WEPENOList?"+key+"="+value, {mct_no:mct_no,we_pe_no:we_pe_no}).done(function(j) {
       	 	   count = j;
   		       $("input#count").val(j)
   		 }).fail(function(xhr, textStatus, errorThrown) { }); 
	   		}     	
	        } 
	    });
     });
     
$(function() {
	   var wepetext1=$("#std_nomclature");
	   
		  wepetext1.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
	        	type: 'POST',
		    	url: "getStdNomenclaturePrfListTrans?"+key+"="+value,
		        data: {mct_nomen:document.getElementById('std_nomclature').value,prf_code:document.getElementById('prf_code').value},
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
		        	  alert("Please Enter Approved Standard Nomenclature");
		        	  wepetext1.val("");
		        	  $("input#mct_no").val("");	
		        	 
		        	  wepetext1.focus();
		        	  return false;	             
		          }   	         
		      }, 
		      
		      select: function( event, ui ) {
		        	$(this).val(ui.item.value);    	        	
		        	var std_nomclature = $(this).val();           
		        	$.post("getMctBYNomenclature?"+key+"="+value, {std_nomclature:std_nomclature}).done(function(j) {
		        		 for ( var i = 0; i < j.length; i++) {           			
		               	 		$("input#mct_no").val(j);		
		               	 	}
	     		  }).fail(function(xhr, textStatus, errorThrown) { });
		        	
		        	      	
		        }  
		    });
	      });    
    
// function getPRFList(val) {
// 	var options = '<option value="0">'+ "--- Select All Veh Type ---" + '</option>';
// 	if(val !=""){
// 	    $.post("getPRFTransportList?"+key+"="+value,{type : val}).done(function(j) {
// 	    	var prfcd='${prf_code}';
//     		prfcd = prfcd +",";
//     		var prfcd1=[];
// 			for ( var i = 0; i < j.length; i++) {				
// 				if(prfcd.indexOf(j[i].prf_code+",")>=0){
// 					options += '<option value="'+j[i].prf_code+'" name="' + j[i].group_name+ '" selected=selected>'+ j[i].group_name+ '</option>';
// 					prfcd1.push(j[i].prf_code);							
// 				}else{
// 					options += '<option value="'+j[i].prf_code+'" name="' + j[i].group_name+ '" >'+ j[i].group_name+ '</option>';
// 				}
// 			}	
			
// 			console.log(prfcd1);
// 			$("select#prf_code").html(options);
// 			getMCTMainNameList(prfcd1);
// 	    }).fail(function(xhr, textStatus, errorThrown) {
// 		});
// 	}
// 	else {
// 		$("select#prf_code").html(options);
// 	}
	
// }
function getMCTMainList(val) {
	var options = '<option value="0">'+ "--Select--" + '</option>';
	if(val !="") {
	    $.post("getMCtMain_from_prf_code?"+key+"="+value,{prf_code : val}).done(function(j) {
	    	var prfcd='${prf_code}';
    		prfcd = prfcd +",";
    		var prfcd1=[];
			for (var i = 0; i < j.length; i++) {
				if(prfcd.indexOf(j[i].prf_code+",")>=0){
					options += '<option value="'+j[i].mct_main_id+'" name="' + j[i].mct_nomen+ '" selected=selected>'+ j[i].mct_nomen+ '</option>';
					prfcd1.push(j[i].prf_code);
				}else{
					options += '<option value="'+j[i].mct_main_id+'" name="' + j[i].mct_nomen+ '" >'+ j[i].mct_nomen+ '</option>';
				}
			}	
			$("select#prf_code").html(options);
	    }).fail(function(xhr, textStatus, errorThrown) {
		});
	}
	else {
		$("select#prf_code").html(options);
	}
}

//////////////////////////////////////////////////////
function getMCTMainNameList(val){
	val=$("#prf_code").val();	
	console.log("PRF-",$("#prf_code").val());
	if (val !=',' && typeof val !== "string") {
		val=val.join(":");
	}
	$.post("getMCtMain_from_prf_code?"+key+"="+value,{prf_code:val}).done(function(data) {	
	    if(data == ""){
			$("#srctable").empty();
			$("#tartable").empty();
			$("#sregn").text(data.length);
			$("#tregn").text(data.length);
		}else{
			$("#d_reg").show();
			$("#d_reg2").show();
			$("#d_reg3").show();
			$("#srctable").val(data);
			drawregn(data);
		}
	}).fail(function(xhr, textStatus, errorThrown) {
	});
}
function drawregn(j) {
	var ii=0;
	$("#srctable").empty();
	$("#tartable").empty();
	for(var i = 0; i < j.length; i++){
		var row="<tr id='SRC"+j[i].mct_main_id+"' padding='5px;'>";
		row=row+"<td>&nbsp;<input class='nrCheckBox' type=checkbox id='"+j[i].mct_main_id+"' name='"+j[i].mct_nomen+"' onclick='findselected();'>&nbsp;";
		row=row+ j[i].mct_main_id+" - "+j[i].mct_nomen +"</td>";
		$("#srctable").append(row);
		ii=ii+1;
	}
	
	if('${mct_main_list1}' != ""){
    	var mctmain_list = '${mct_main_list1}';
    	const mctmainArray = mctmain_list.split(":");
    	for(var i=0;i<mctmainArray.length;i++){
			$("#"+mctmainArray[i]).prop("checked",true);
    	}
    	findselected();
    }
	
	$("#sregn").text(ii);
}
function callsetall(){
	var chkclk=$('#nSelAll').prop('checked');
	if (chkclk) {
		$('.nrCheckBox').prop('checked',true);		
	} else {
		$('.nrCheckBox').prop('checked',false);
	}
	findselected();
}
function findselected() {
	$("#srctable tr").css('background-color','white');
	
	var nrSel=$('.nrCheckBox:checkbox:checked').map(function() {
		var bb=$(this).attr('id');
		$("#SRC"+bb).css('background-color','yellow');
		return bb;
	}).get();
	var b=nrSel.join(':');
	
	$("#c_val").val(nrSel.length);

	$("#sus_no_list").val(b);
	
	var nrSel=$('.nrCheckBox:checkbox:checked').map(function() {
		var bb1=$(this).attr('name');
		return bb1;
	}).get();
	var c=nrSel.join(':');
	$("#unit_name_list").val(c);
	
	drawtregn(c);
}
function drawtregn(data) {
	var ii=0;
	$("#tartable").empty();
	var datap=data.split(":");
	for (var i = 0; i <datap.length; i++){
		var nkrow="<tr id='tarTableTr' padding='5px;'>";
		nkrow=nkrow+"<td>&nbsp;&nbsp;";
		nkrow=nkrow+ datap[i]+"</td>";
		$("#tartable").append(nkrow);
		ii=ii+1;
	}
	$("#tregn").text(ii);
}


function getCONTCorps(fcode){
 	var fcode1 = fcode[0];
	$.post("getCorpsDetailsList?"+key+"="+value,{fcode:fcode1}, function(j) {
		if(j != ""){
			var length = j.length-1;
			var enc = j[length][0].substring(0,16);
			var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
			
			for ( var i = 0; i < length; i++) {
				if('${cont_corps1}' ==  dec(enc,j[i][0])){
					options += '<option value="' + dec(enc,j[i][0]) +'" name="'+dec(enc,j[i][1])+'" selected=selected >'+ dec(enc,j[i][1]) + '</option>';
				}else{
					options += '<option value="' + dec(enc,j[i][0]) +'" >'+ dec(enc,j[i][1]) + '</option>';
				}
			}	
			$("select#cont_corps").html(options);
		}
	});
 } 
 function getCONTDiv(fcode){
	  
 	var fcode1 = fcode[0] + fcode[1] + fcode[2];
   	$.post("getDivDetailsList?"+key+"="+value,{fcode:fcode1}, function(j) {
   		if(j != ""){
		   	var length = j.length-1;
		var enc = j[length][0].substring(0,16);
		var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
		for ( var i = 0; i < length; i++) {
			if('${cont_div1}' ==  dec(enc,j[i][0])){
				options += '<option value="' + dec(enc,j[i][0]) +'" name="'+dec(enc,j[i][1])+'" selected=selected >'+ dec(enc,j[i][1]) + '</option>';
			}else{
				options += '<option value="' + dec(enc,j[i][0]) +'" >'+ dec(enc,j[i][1]) + '</option>';
			}
		}
	   		$("select#cont_div").html(options);
   		}
	});
 } 
function getCONTBde(fcode){
	var fcode1 = fcode[0] + fcode[1] + fcode[2] + fcode[3] + fcode[4] + fcode[5];
	$.post("getBdeDetailsList?"+key+"="+value,{fcode:fcode1}, function(j) {
		if(j != ""){
			var length = j.length-1;
		var enc = j[length][0].substring(0,16);
		var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
		jQuery("select#cont_bde").html(options);
		for ( var i = 0; i < length; i++) {
			if('${cont_bde1}' ==  dec(enc,j[i][0])){
				options += '<option value="' +  dec(enc,j[i][0])+ '" name="'+dec(enc,j[i][1])+'" selected=selected>'+  dec(enc,j[i][1]) + '</option>';
				$("#cont_bname").val(dec(enc,j[i][1]));
			}else{
				options += '<option value="' +  dec(enc,j[i][0]) +'" name="'+dec(enc,j[i][1])+'">'+ dec(enc,j[i][1]) + '</option>';
			}
		}	
		$("select#cont_bde").html(options);
		}
	});
}
	
jQuery(function() {
	// Source SUS No
	jQuery("#sus_no").keypress(function(){
		var sus_no = this.value;
			 var susNoAuto=jQuery("#sus_no");
			  susNoAuto.autocomplete({
			      source: function( request, response ) {
			        jQuery.ajax({
			        type: 'POST',
			        url: "getTargetSUSNoList?"+key+"="+value,
			        data: {sus_no:sus_no},
			          success: function( data ) {
			        	  var susval = [];
			        	  var length = data.length-1;
			        	  if(data.length != 0){
				        		var enc = data[length].substring(0,16);
				        	}
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
			        	  document.getElementById("unit_name").value="";
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
					   	jQuery("#unit_name").val(dec(enc,j[0]));	
			    	}).fail(function(xhr, textStatus, errorThrown) {
			    });
			} 	     
		});	
	});
	// End
	
	// Source Unit Name
    jQuery("#unit_name").keyup(function(){
 			var unit_name = this.value;
				 var susNoAuto=jQuery("#unit_name");
				  susNoAuto.autocomplete({
				      source: function( request, response ) {
				        jQuery.ajax({
				        type: 'POST',
				        url: "getTargetUnitsNameActiveList?"+key+"="+value,
				        data: {unit_name:unit_name},
				          success: function( data ) {
				        	  var susval = [];
				        	  var length = data.length-1;
				        	  if(data.length != 0){
					        		var enc = data[length].substring(0,16);
					        	}
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
				        	  document.getElementById("unit_name").value="";
				        	  document.getElementById("sus_no").value="";
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
	 				         jQuery("#sus_no").val(dec(enc,j[0]));	
						}).fail(function(xhr, textStatus, errorThrown) {
					});
				}
			}); 			
 		});
	});
	

$(document).ready(function() {
	$.ajaxSetup({
		async : false
	});
	$("#listDataDiv").hide();
	$("#summaryDiv").hide();
	jQuery(function($){ 
		 datepicketDate('eff_from');
		 datepicketDate('eff_to');
  	});
	
	$("#prf_code").val('${prf_code}');
	getMCTMainList('${prf_code}');	
	$("#sus_no").val('${sus_no1}');
	$("#unit_name").val('${unit_name1}');
	
	if('${type_force1}' != ""){
		$("#type_force").val('${type_force1}');
	}
	
	if('${mct_main_list1}' != ""){
		$("#mct_no").val('${mct_main_list1}');
	}
	
	if('${prf_code1}' != ""){
		$("#prf_code").val('${prf_code1}');
	}
	
	if('${cont_comd1}' != ""){
    	$("#cont_comd").val('${cont_comd1}');
    	getCONTCorps('${cont_comd1}');
    	var fcode = '${cont_comd1}';
    	getCONTCorps(fcode);    	
   		fcode += "00";	
		getCONTDiv(fcode);   	
   		fcode += "000";	
		getCONTBde(fcode);   	
	}

	if('${cont_corps1}' != ""){
		getCONTDiv('${cont_corps1}');
		var fcode = '${cont_corps1}';	  	
		fcode += "000";	
		getCONTBde(fcode);
	}
	
    if('${cont_div1}' != ""){
    	getCONTBde('${cont_div1}'); 
    } 
	
    if('${line_dte_sus1}' != ""){
		$("#line_dte_sus").val('${line_dte_sus1}');
	}
    
	var select = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
   	$('select#cont_comd').change(function() {
	   	var fcode = this.value;
	   	if(fcode == "0"){
	   		$("select#cont_corps").html(select);
	   		$("select#cont_div").html(select);
	   		$("select#cont_bde").html(select);
   		} else {
   			$("select#cont_corps").html(select);
   			$("select#cont_div").html(select);
   			$("select#cont_bde").html(select);
   			getCONTCorps(fcode);
       		fcode += "00";	
   			getCONTDiv(fcode);
       		fcode += "000";	
   			getCONTBde(fcode);
   		}
	});
   	
	$('select#cont_corps').change(function() {
		var fcode = this.value;
   	   	if(fcode == "0"){
   	   		$("select#cont_div").html(select);
   	   		$("select#cont_bde").html(select);
   	     	var fcode = $('#cont_comd').val();
			fcode += "00";	
			 getCONTDiv(fcode);   	
   		      fcode += "000";	
			getCONTBde(fcode); 
	   	}else{
	   		$("select#cont_div").html(select);
   	   		$("select#cont_bde").html(select);
	   	   		getCONTDiv(fcode);
	       		fcode += "000";	
	   			getCONTBde(fcode);
	   	}
	});
	
	
	$('select#cont_div').change(function() {
	   		var fcode = this.value;    	   	
	   		if(fcode == "0"){
	   			if($('#cont_corps').val() == 0){
	   				$("select#cont_bde").html(select)
	   		 		var fcode = $('#cont_comd').val();   
	   		 		  fcode += "00000";	
	   				getCONTBde(fcode);
	   			}else{
	   				$("select#cont_bde").html(select)
	   			}
	   	}else{
	   		$("select#cont_bde").html(select)
		   		getCONTBde(fcode);
	   	}
	});
	
	$("#InputSearch").on("keyup", function() {
		var value = $(this).val().toLowerCase();
		$("#srctable tr").filter(function() {
		$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		});
	});
	
	
	
	
});

/* mockjax1('getVehicleStatusTbl');
table = dataTable('getVehicleStatusTbl');

mockjax1('getVehicleStatusTbl1');
table2 = dataTable('getVehicleStatusTbl1');
 */
function addTotalRow(tableBody, totalBase, totalMod, totalGenNotes,totalAuth) {
    tableBody.append(
        $('<tr>', { class: 'total-row' }).append([
            $('<td>',{ colspan: 9, style: 'text-align: right; font-weight: bold;' }).text('Total:'),
            $('<td>',{style: 'text-align: center; font-weight: bold;'}).text(totalBase),
            $('<td>',{style: 'text-align: center; font-weight: bold;'}).text(totalMod),
            $('<td>',{style: 'text-align: center; font-weight: bold;'}).text(totalGenNotes),
            $('<td>',{style: 'text-align: center; font-weight: bold;'}).text(totalAuth),
            $('<td>')
        ])
    );
}

function data(tableName) {
	jsondata = [];

    var table = $('#'+tableName).DataTable();
    var info = table.page.info();
    var currentPage = info.page;
    var pageLength = info.length;
    var startPage = info.start;
    var endPage = info.end;
    var Search = table.search();
    var order = table.order();
    var orderColunm = order[0][0] + 1;
    var orderType = order[0][1];
    const formData = {
        startPage: startPage,
        pageLength: pageLength,
        Search: Search,
        orderColunm: orderColunm,
        orderType: orderType,
        type_force: $("#type_force").val(),
        prf_code: $("#prf_code").val(),
        sus_no: $("#sus_no").val(),
        unit_name: $("#unit_name").val(),
        cont_comd: $("#cont_comd").val(),
        cont_corps: $("#cont_corps").val(),
        cont_div: $("#cont_div").val(),
        cont_bde: $("#cont_bde").val(),
        line_dte_sus: $("#line_dte_sus").val(),
        eff_from: $("#eff_from").val(),
        eff_to: $("#eff_to").val(),
        modification: $("#modification").val(),
        we_pe_no: $("#we_pe_no").val(),
        table_title: $("#table_title").val(),
        std_nomclature: $("#std_nomclature").val()
    };
    //UE TABLE
    if (tableName == "getVehicleStatusTbl") {
        Promise.all([
            $.post("ue_of_transportCount?" + key + "=" + value, formData),
            $.post("ue_of_transportTable?" + key + "=" + value, formData)
        ]).then(([countData, tableData]) => {
            FilteredRecords = countData;
            
            let tableBody = $('#' + tableName + ' tbody');
            tableBody.empty();
         
            tableData.sort((a, b) => (a.group_name > b.group_name) ? 1 : -1);
			
            let currentGroup = '';
            let totalBase = 0;
            let totalMod = 0;
            let totalGenNotes = 0;
            let totalAuth = 0;
            tableData.forEach(row => {
                if (currentGroup !== row.group_name) {
                	if (currentGroup !== '') {
                        addTotalRow(tableBody, totalBase, totalMod, totalGenNotes,totalAuth);
                        totalBase = 0;
                        totalMod = 0;
                        totalGenNotes = 0;
                        totalAuth = 0;
                    }
                    tableBody.append(
                        $('<tr>').append(
                            $('<th>', {
                                colspan: '13',
                                style: 'text-align: center',
                                text: 'PRF Group: ' + row.group_name
                            })
                        )
                    );
                    currentGroup = row.group_name;
                }
            
//                 ['reserve', 'FF', 'NFF', 'ue'].forEach(key => {
//                     row[key] = row[key] ?? "0";
//                 });
            	
            	row.base = parseInt(row.base) || 0;
                row.mod = parseInt(row.mod) || 0;
                row.gennotes = parseInt(row.gennotes) || 0;
            	row.total = parseInt(row.total) || 0;
                
            	totalBase += row.base;
                totalMod += row.mod;
                totalGenNotes += row.gennotes;
                totalAuth += row.total;
                
                tableBody.append(
                    $('<tr>').append([
                        $('<td>').text(row.comd_name),
                        $('<td>').text(row.corps_name),
                        $('<td>').text(row.div_name),
                        $('<td>').text(row.bde_name),
                        $('<td>').text(row.unit_name),
                        $('<td>').text(row.mct_nomen),
                        $('<td>').text(row.we_pe),
                        $('<td>').text(row.we_pe_no),
                        $('<td>',{style: 'text-align: center'}).text(row.modification),
                        $('<td>',{style: 'text-align: center'}).text(row.base),
                        $('<td>',{style: 'text-align: center'}).text(row.mod),
                        $('<td>',{style: 'text-align: center'}).text(row.gennotes),
//                         $('<td>').text(row.foot),
                        $('<td>',{style: 'text-align: center'}).text(row.total)
                    ])
                );
            });
            
            addTotalRow(tableBody, totalBase, totalMod, totalGenNotes,totalAuth);
            
        }).catch(error => {
            console.error('Error loading table data:', error);
        }).finally(() => {           
            $('#' + tableName).removeClass('loading');
        });
    }
    
    //SUMMARY TABLE
    
    else if (tableName == "getVehicleStatusTbl1"){
		 /* $.post("ue_of_transport_summaryCount?" + key + "=" + value,{
			Search: Search,
	        type_force: $("#type_force").val(),
	        prf_code: $("#prf_code").val(),
	        sus_no: $("#sus_no").val(),
	        unit_name: $("#unit_name").val(),
	        cont_comd: $("#cont_comd").val(),
	        cont_corps: $("#cont_corps").val(),
	        cont_div: $("#cont_div").val(),
	        cont_bde: $("#cont_bde").val(),
	        line_dte_sus: $("#line_dte_sus").val(),
	        eff_from: $("#eff_from").val(),
	        eff_to: $("#eff_to").val(),
	        modification: $("#modification").val(),
	        we_pe_no: $("#we_pe_no").val(),
	        table_title: $("#table_title").val(),
	        std_nomclature: $("#std_nomclature").val()
		 },  function(j) {
			FilteredRecords = j;
		});
		$.post("ue_of_transport_summaryTable?" + key + "=" + value, {
			startPage: startPage,
	        pageLength: pageLength,
	        Search: Search,
	        orderColunm: orderColunm,
	        orderType: orderType,
	        type_force: $("#type_force").val(),
	        prf_code: $("#prf_code").val(),
	        sus_no: $("#sus_no").val(),
	        unit_name: $("#unit_name").val(),
	        cont_comd: $("#cont_comd").val(),
	        cont_corps: $("#cont_corps").val(),
	        cont_div: $("#cont_div").val(),
	        cont_bde: $("#cont_bde").val(),
	        line_dte_sus: $("#line_dte_sus").val(),
	        eff_from: $("#eff_from").val(),
	        eff_to: $("#eff_to").val(),
	        modification: $("#modification").val(),
	        we_pe_no: $("#we_pe_no").val(),
	        table_title: $("#table_title").val(),
	        std_nomclature: $("#std_nomclature").val()
		}, function(j) {
			for (var i = 0; i < j.length; i++) {
				var sr = i + 1;
				var reserveValue = j[i].reserve != null && j[i].reserve != undefined ? j[i].reserve : 0;
				jsondata.push([j[i].mct_no, j[i].mct_nomen, j[i].ff, j[i].nff, reserveValue, j[i].total]);
			}
			
		});  */
		
    	Promise.all([
            $.post("ue_of_transport_summaryCount?" + key + "=" + value, formData),
            $.post("ue_of_transport_summaryTable?" + key + "=" + value, formData)
        ]).then(([countData, tableData]) => {
            FilteredRecords = countData;
            console.log("filtered countdata value " + FilteredRecords)
            let tableBody = $('#' + tableName + ' tbody');
            tableBody.empty();
         
            tableData.sort((a, b) => (a.group_name > b.group_name) ? 1 : -1);
			
            let currentGroup = '';
            
            tableData.forEach(row => {
                if (currentGroup !== row.group_name) {
                	
                    tableBody.append(
                        $('<tr>').append(
                            $('<th>', {
                                colspan: '6',
                                style: 'text-align: left',
                                text: 'PRF Group: ' + row.group_name
                            })
                        )
                    );
                    currentGroup = row.group_name;
                }
            
//                 
            	$('<th>', {
                                colspan: '13',
                                style: 'text-align: center',
                                text: 'PRF Group: ' + row.group_name
                            })
            	
                
                tableBody.append(
                    $('<tr>').append([
                        $('<td>',{style: 'text-align: center'}).text(row.mct_no),
                        $('<td>').text(row.mct_nomen),
                        $('<td>',{style: 'text-align: center'}).text(row.ff),
                        $('<td>',{style: 'text-align: center'}).text(row.nff),
                        $('<td>',{style: 'text-align: center'}).text(row.reserve),
                        $('<td>',{style: 'text-align: center'}).text(row.total),
                      ])
                );
            });
            
            
            
        }).catch(error => {
            console.error('Error loading table data:', error);
        }).finally(() => {           
            $('#' + tableName).removeClass('loading');
        }); 
	}
    
}

/* $('#btn-reload').on('click', function() {
	$("#listDataDiv").show();
	$("#summaryDiv").hide();
	
	
table.ajax.reload();

});

$('#btn_summ').on('click', function() {
	debugger
	$("#listDataDiv").hide();
	$("#summaryDiv").show();
	table2.ajax.reload();
}); */


</script>

<script>
    $(document).ready(function () {
        let tableInitialized = false; // Track if Table 1 is initialized
        let table2Initialized = false; // Track if Table 2 is initialized
        let table, table2; // Declare table variables globally for reuse

        // Button to show List Data Table
        $('#btn-reload').on('click', function () {
            $("#listDataDiv").show(); // Show List Data div
            $("#summaryDiv").hide(); // Hide Summary div

            if (!tableInitialized) {
                // Initialize mockjax1 and dataTable for Table 1 only on first click
                mockjax1('getVehicleStatusTbl');
                table = dataTable('getVehicleStatusTbl');
                tableInitialized = true; // Mark Table 1 as initialized
            } else {
                table.ajax.reload(); // Reload Table 1 data if already initialized
            }
        });

        // Button to show Summary Table
        $('#btn_summ').on('click', function () {
            $("#listDataDiv").hide(); // Hide List Data div
            $("#summaryDiv").show(); // Show Summary div

            if (!table2Initialized) {
                // Initialize mockjax1 and dataTable for Table 2 only on first click
                mockjax1('getVehicleStatusTbl1');
                table2 = dataTable('getVehicleStatusTbl1');
                table2Initialized = true; // Mark Table 2 as initialized
            } else {
                table2.ajax.reload(); // Reload Table 2 data if already initialized
            }
        });
    });
</script>



 