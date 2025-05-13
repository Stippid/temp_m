<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link rel="stylesheet" href="js/InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="js/InfiniteScroll/js/datatables.min.js"></script>
<script type="text/javascript" src="js/InfiniteScroll/js/jquery.mockjax.min.js"></script>
<script type="text/javascript" src="js/InfiniteScroll/js/datatables.mockjax.js"></script>

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<script src="js/Calender/datePicketValidation.js"></script>

<form:form name="Search_agencies_officer_formation" id="Search_agencies_officer_formation"
	action="Search_agencies_officer_formationAction" method="post"
	class="form-horizontal" commandName="">
	<div class="animated fadeIn">
		<div class="col-md-12" align="center">
			<div class="card">
				<div class="card-header">
					<h5>Search:Emoluments Details</h5>
					<h6 class="enter_by">Reported On: ${date}</h6>
				</div>
				<div class="card-body card-block">
						          			<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
								    	<div class="col col-md-4">
								        	<label class=" form-control-label">Cont Comd </label>
								       	</div>
								        <div class="col-12 col-md-8">
								        	<select id="cont_comd" name="cont_comd" class="form-control" >
								            	${selectcomd}
									            <c:forEach var="item" items="${getCommandList}" varStatus="num" >
									            	<option value="${item[0]}"  name="${item[1]}" >${item[1]}</option>
	                  							</c:forEach>
	                  						</select>
								    	</div>
								  	</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
			                			<div class="col col-md-4">
			                  				<label class="form-control-label">Cont Corps</label>
			               				</div>
			                			<div class="col-12 col-md-8">
			                 				<select id="cont_corps" name="cont_corps" class="form-control" >
			                 					${selectcorps}
			                 					<c:forEach var="item" items="${getCorpsList}" varStatus="num" >
	                  								<option value="${item[0]}"  name="${item[1]}" >${item[1]}</option>
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
						                  <label class=" form-control-label">Cont Div</label>
						                </div>
						                <div class="col-12 col-md-8">
						                 	<select id="cont_div" name="cont_div" class="form-control" >
						                 		${selectDiv}
						                 		<c:forEach var="item" items="${getDivList}" varStatus="num" >
	                  								<option value="${item[0]}"  name="${item[1]}" >${item[1]}</option>
	                  							</c:forEach>
						                 	</select>
						                </div>
						            </div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
		                				<div class="col col-md-4">
		                  					<label class=" form-control-label">Cont Bde</label>
		                				</div>
		                				<div class="col-12 col-md-8">
		                 					<select id="cont_bde" name="cont_bde" class="form-control" >
		                 						${selectBde}
		                 						<c:forEach var="item" items="${getBdeList}" varStatus="num" >
	                  								<option value="${item[0]}"  name="${item[1]}" >${item[1]}</option>
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
											<input type="text" id="unit_name" name="unit_name" maxlength="100" placeholder="Search Unit Name" class="form-control autocomplete" >
										</div>
									</div>
		          				</div>
		          				<div class="col-md-6">
		          					<div class="row form-group">
	                					<div class="col col-md-4">
	                  						<label class=" form-control-label">SUS No</label>
							            </div>
							            <div class="col-12 col-md-8">
											<input type="text" id="sus_no" name="sus_no" maxlength="8" placeholder="Search SUS No" class="form-control autocomplete" >
										</div>
	              					</div>
		          				</div>
		          			</div>
				
                      <div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> Personal No </label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="personnel_no" name="personnel_no" class="form-control autocomplete" autocomplete="off" maxlength="9" onkeyup="return specialcharecter(this)" onkeypress="return AvoidSpace(event)" > 
						                </div>
						            </div>
	              				</div>
	             				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> Rank</label>
						                </div>
						                <div class="col-md-8">
						                   <select name="rank" id="rank" class="form-control-sm form-control"   >
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getTypeofRankList}" varStatus="num">
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
						               <div class="col-md-4">
						                    <label class=" form-control-label"> Agency </label>
						                </div>
						                <div class="col-md-8">
						                  <select name="agency_id"
										id="agency_id" class="form-control" onchange="fn_benifit();"> 
											<option value="0">--Select--</option>
										<c:forEach var="item" items="${getAgencyList}" 
												varStatus="num">
 												<option value="${item[0]}">${item[1]}</option> 
 											</c:forEach>  
									</select>
						                </div>
						            </div>
	              				</div>
	             				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> Type of Benefits</label>
						                </div>
						                <div class="col-md-8">
						                   <select name="type_of_benefits_id"
										id="type_of_benefits_id" class="form-control" >
											<option value="0">--Select--</option>
									</select>
						                </div>
						            </div>
	              				</div>              				
	              			</div>
	              	<div class="col-md-12"  >
	              		<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Date of Causality </label>
								</div>
								
									
											
						 <div class="col-md-8">
						                   <input type="text" name="dt_of_casuality" id="dt_of_casuality" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
						                
						                
						                
						                
						                </div>
					</div>				
							
						</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class="form-control-label"> Case</label>
							</div>
							<div class="col-md-8">
								<select name="scase" id="scase" class="form-control autocomplete" autocomplete="off" ">
									<option value="0">--Select--</option>
									<option value="1">Intial</option>
									<option value="2">On going</option>
									<option value="3">Closed</option>
								</select>

							</div>
						</div>
					</div>     					              				
	              			</div> 
				</div>

				<div class="card-footer" align="center" class="form-control">
					<a href="SearchagenciesFormationOfficer_url"
						class="btn btn-success btn-sm">Clear</a> 
						<i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm"  id="btn-reload"  value="Search" />
					  
				</div>
			</div>
		</div>
	</div>

	<div id="viewpage" class=col-md-12>
		<div class="col-md-12"  style="display: block;">
			<div class="watermarked" data-watermark="" id="divwatermark">
				<span id="ip"></span>
				<table id="getSearch_agencies_wise"
					class="table no-margin table-striped  table-hover  table-bordered report_print_scroll">
					<thead>
						<tr>
							<th id ="2">Personal No</th>
							<th id ="5">Agency Name</th>
							<th id ="7">Beneficial Name</th>
							<th id ="9">Amount Due(Rs)</th>
							<th id ="6">Total Amount Release(Rs)</th>
							<th id ="2">Reason For not Releasing</th>
							<th id ="2">Update</th>
							 
							
							
							

						</tr>
					</thead>

				</table>
			</div>
		</div>
	</div>


</form:form>







<c:url value="Searchagenciesbattle" var="Searchagencie" />
	<form:form action="${Searchagencie}" method="post" id="searchForm" name="searchForm" modelAttribute="id7">
		<input type="hidden" name="personnel_no1" id="personnel_no1" />				
		<input type="hidden" name="rank1" id="rank1" />		
		<input type="hidden" name="dt_of_casuality1" id="dt_of_casuality1" />		
		<input type="hidden" name="scase1" id="scase1" />		
		<input type="hidden" name="agency_id1" id="agency_id1" />		
		<input type="hidden" name="type_of_benefits_id1" id="type_of_benefits_id1" />		
	</form:form> 
	
<%-- <c:url value="view_Emoluments_History_formationUrl" var="viewUrl"/>
<form:form action="${viewUrl}" method="post" id="viewForm" name="viewForm" modelAttribute="comm_id_a">
	   <input type="hidden" name="comm_id_a" id="comm_id_a"  value="0">
</form:form> --%>

<script type="text/javascript">
function data(getSearch_agencies_wise){
	jsondata = [];

	var table = $('#'+getSearch_agencies_wise).DataTable();
	var info = table.page.info();
	var currentPage = info.page;
	var pageLength = info.length;
	var startPage = info.start;
	var endPage = info.end;
	var Search = table.search();
	var order = table.order();
	var orderColunm = $(table.column(order[0][0]).header()).attr('id');
	var orderType = order[0][1];
	
	var cont_comd=$("#cont_comd").val();
 	var cont_corps=$("#cont_corps").val();
 	var cont_div=$("#cont_div").val();
 	var cont_bde=$("#cont_bde").val();
 	var unit_name=$("#unit_name").val();
 	var sus_no=$("#sus_no").val();
	var personnel_no=$("#personnel_no").val() ;
	var rank=$("#rank").val() ;
	var dt_of_casuality=$("#dt_of_casuality").val() ;
	var scase=$("#scase").val() ;
	var agency_id=$("#agency_id").val() ;
	var type_of_benefits_id=$("#type_of_benefits_id").val() ;
	

	
	var s_total = 0;
 	
	$.post("getagenciesdatasearchdetail?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,orderType:orderType,cont_comd:cont_comd,cont_corps:cont_corps,cont_div:cont_div,cont_bde:cont_bde,unit_name:unit_name,sus_no:sus_no,personnel_no:personnel_no,
		rank:rank,dt_of_casuality:dt_of_casuality,scase:scase,agency_id:agency_id,type_of_benefits_id:type_of_benefits_id,
		},function(j) {
			
		for (var i = 0; i < j.length; i++) {
			
			jsondata.push([j[i].personnel_no,j[i].agency_name,j[i].benefits_name,j[i].amount_due_v,j[i].amount_rel_v,j[i].reason,
				j[i].updated_as_on,]);
			
			 //s_total += j[i].total;
		}
		
	});
	$.post("getagenciesCount?"+key+"="+value,{Search:Search,cont_comd:cont_comd,cont_corps:cont_corps,cont_div:cont_div,cont_bde:cont_bde,unit_name:unit_name,sus_no:sus_no,personnel_no:personnel_no,
		rank:rank,dt_of_casuality:dt_of_casuality,scase:scase,agency_id:agency_id,type_of_benefits_id:type_of_benefits_id,
		},function(j) {
		
		FilteredRecords = j;
	});
}
</script>



<Script>
	$(document).ready(function() {
		
		mockjax1('getSearch_agencies_wise');
		table = dataTable('getSearch_agencies_wise');
		$('#btn-reload').on('click', function(){
	    	table.ajax.reload();
	    });
		
		

		
		
		 datepicketDate('dt_of_casuality');
		
		if('${roleAccess}' == 'Unit')
		{
			$("#cont_comd").attr("disabled", true);
			$("#cont_corps").attr("disabled", true); 
			$("#cont_div").attr("disabled", true); 
			$("#cont_bde").attr("disabled", true);
			$("#sus_no").attr("disabled", true); 
			$("#unit_name").attr("disabled", true);
			
			$("#sus_no").val('${sus_no}');
			$("#unit_name").val('${unit_name}');
		}
		if('${roleSubAccess}' == 'Brigade')
		{
			$("#cont_comd").attr("disabled", true);
			$("#cont_corps").attr("disabled", true); 
			$("#cont_div").attr("disabled", true); 
			$("#cont_bde").attr("disabled", true);
		}
		if('${roleSubAccess}' == 'Division')
		{
			$("#cont_comd").attr("disabled", true);
			$("#cont_corps").attr("disabled", true); 
			$("#cont_div").attr("disabled", true); 
			if('${cont_div1}' != ""){
	   	    	getCONTBde('${cont_div1}');
	   	    }
		}
		if('${roleSubAccess}' == 'Corps')
		{
			$("#cont_comd").attr("disabled", true);
			$("#cont_corps").attr("disabled", true);
			if('${cont_corps1}' != ""){
	   		 	getCONTDiv('${cont_corps1}');
	       	}
	   	    if('${cont_div1}' != ""){
	   	    	getCONTBde('${cont_div1}');
	   	    }
		}
		if('${roleSubAccess}' == 'Command')
		{
			$("#cont_comd").attr("disabled", true);
			if('${cont_comd1}' != ""){
		    	$("#cont_comd").val('${cont_comd1}');
		    	getCONTCorps('${cont_comd1}');
	    	}
	    	if('${cont_corps1}' != ""){
	    		 getCONTDiv('${cont_corps1}');
	    	}
		    if('${cont_div1}' != ""){
		    	getCONTBde('${cont_div1}');
		    }
		}
		
		if('${roleAccess}' == 'MISO' || '${roleAccess}' == 'HeadQuarter' || '${roleAccess}' == 'Line_dte')
		{
			if('${cont_comd1}' != ""){
		    	$("#cont_comd").val('${cont_comd1}');
		    	getCONTCorps('${cont_comd1}');
	    	}
	    	if('${cont_corps1}' != ""){
	    		 getCONTDiv('${cont_corps1}');
	    	}
		    if('${cont_div1}' != ""){
		    	getCONTBde('${cont_div1}');
		    }
		}
		
		var select = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
	   	$('select#cont_comd').change(function() {
		   		var fcode = this.value;
		   		if(fcode == "0"){
		   			$("select#cont_corps").html(select);
		   			$("select#cont_div").html(select);
		   			$("select#cont_bde").html(select);
	   		}else{	
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
		 		$("select#cont_bde").html(select)
		   	}else{
		   		$("select#cont_bde").html(select)
			   		getCONTBde(fcode);
		   	}
			});

	
		
		
		
		
		
		
		if ('${personnel_no1}' != "0" && '${personnel_no1}' != "") {
			$("#personnel_no").val('${personnel_no1}');
		}
		if ('${rank1}' != "0" && '${rank1}' != "") {
			$("#rank").val('${rank1}');
		}
		
		
		if ('${dt_of_casuality1}' != "0" && '${rank1}' != "") {
			$("#dt_of_casuality").val('${dt_of_casuality1}');
		}
		
		if ('${scase1}' != "0" && '${scase1}' != "") {
			$("#scase").val('${scase1}');
		}
		
		if ('${agency_id1}' != "0" && '${agency_id1}' != "") {
			$("#agency_id").val('${agency_id1}');
		}
		if ('${type_of_benefits_id1}' != "0" && '${type_of_benefits_id1}' != "") {
			$("#type_of_benefits_id").val('${type_of_benefits_id1}');
		}
		
		

		//unservisable();
	});
	
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
	
	
	
	
	
	function Search() {
		$("#personnel_no1").val($("#personnel_no").val()) ;	
	
			
		
		$("#rank1").val($("#rank").val()) ;	
		$("#dt_of_casuality1").val($("#dt_of_casuality").val()) ;	
		$("#scase1").val($("#scase").val()) ;	
		$("#agency_id1").val($("#agency_id").val()) ;	
		$("#type_of_benefits_id1").val($("#type_of_benefits_id").val()) ;	
		document.getElementById('searchForm').submit();
	}

	 function fn_B_Head() {
			
			
			var b_head = $("select#b_head").val();
			$.post("getBudgetCodeList?" + key + "=" + value, {
				b_head: b_head
			}).done(function(j) {
				var options = '<option   value="0">' + "--Select--" + '</option>';
				for(var i = 0; i < j.length; i++) {
					options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
				}
				$("select#b_code").html(options);
			}).fail(function(xhr, textStatus, errorThrown) {});
		}
	 
	 function fn_makeName() {
			
		 var options = '<option   value="0">' + "--Select--" + '</option>';
			var assets_name = $("select#asset_type").val();
			$.post("getCategoryList?" + key + "=" + value, {
				categogry_id: assets_name
			}).done(function(j) {
				
				for(var i = 0; i < j.length; i++) {
					options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
				}
				
			}).fail(function(xhr, textStatus, errorThrown) {});
			$("select#a_type").html(options);
		}


</script>

<script>


	$("#sus_no").keyup(function(){
		var sus_no = this.value;
		var susNoAuto=$("#sus_no");
		 susNoAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        type: 'POST',
		        url: "getTargetSUSNoList?"+key+"="+value,
		        data: {sus_no:sus_no},
		          success: function( data ) {
		        	  var susval = [];
	                  var length = data.length-1;
	                  var enc = data[length].substring(0,16);
	                        for(var i = 0;i<data.length;i++){
	                                susval.push(dec(enc,data[i]));
	                        }
	                        var dataCountry1 = susval.join("|");
	                        var myResponse = []; 
					        var autoTextVal=susNoAuto.val();
					$.each(dataCountry1.toString().split("|"), function(i,e){
						var newE = e.substring(0, autoTextVal.length);
						if (e.toLowerCase().includes(autoTextVal.toLowerCase())) {
						  myResponse.push(e);
						}
					});      	          
					response( myResponse ); 
		          }
		        });
		      },
			  minLength: 1,
		      autoFill: true,
		      change: function(event, ui) {
		    	 if (ui.item) {   	        	  
		        	  return true;    	            
		          } else {
		        	  alert("Please Enter Approved Unit SUS No.");
		        	  document.getElementById("sus_no").value="";
		        	  susNoAuto.val("");	        	  
		        	  susNoAuto.focus();
		        	  return false;	             
		          }   	         
		    }, 
			select: function( event, ui ) {
				var sus_no = ui.item.value;			      	
				 $.post("getTargetUnitNameList?"+key+"="+value, {
					 sus_no:sus_no
	         }, function(j) {	                
	         }).done(function(j) {
	        	 var length = j.length-1;
	             var enc = j[length].substring(0,16);
	             $("#unit_name").val(dec(enc,j[0]));                    
	         }).fail(function(xhr, textStatus, errorThrown) {
	         });
			} 	     
		});	
	});
	
	
	 $("input#unit_name").keypress(function(){
			var unit_name = this.value;
			var susNoAuto=$("#unit_name");
				  susNoAuto.autocomplete({
				      source: function( request, response ) {
				        $.ajax({
				        	type: 'POST',
					    	url: "getTargetUnitsNameActiveList?"+key+"="+value,
				        data: {unit_name:unit_name},
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
				        	  alert("Please Enter Approved Unit Name.");
				        	  document.getElementById("unit_name").value="";
				        	  susNoAuto.val("");	        	  
				        	  susNoAuto.focus();
				        	  return false;	             
				          }   	         
				      }, 
				      select: function( event, ui ) {
				    	 var unit_name = ui.item.value;			    
				            $.post("getTargetSUSFromUNITNAME?"+key+"="+value,{target_unit_name:unit_name}, function(data) {
				            }).done(function(data) {
				            	var length = data.length-1;
					        	var enc = data[length].substring(0,16);
					        	$("#sus_no").val(dec(enc,data[0]));
				            }).fail(function(xhr, textStatus, errorThrown) {
				            });
				      } 	     
				}); 			
		});
	
	 $("input#personnel_no").keypress(function(){
			
			var personel_no = this.value;
				 var susNoAuto=$("#personnel_no");
				  susNoAuto.autocomplete({
				      source: function( request, response ) {
				        $.ajax({
				        	type: 'POST',
					    	url: "getpersonnel_no?"+key+"="+value,
				        data: {personel_no:personel_no},
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
				        	  alert("Please Enter Approved Personal No");
				        	  document.getElementById("personnel_no").value="";
				        	  susNoAuto.val("");	        	  
				        	  susNoAuto.focus();
				        	  return false;	             
				          }   	         
				      }, 
				      select: function( event, ui ) {
				    	
				      } 	     
				}); 			
		});
	 function onlyAlphabets(e, t) {
		    return (e.charCode > 64 && e.charCode < 91) || (e.charCode > 96 && e.charCode < 123) || e.charCode == 32;   
	}
	
		
		function fn_benifit() {
			
			 
			var agency_id = $("select#agency_id").val();
			 
			$.post("geBenifitList?" + key + "=" + value, {
				agency_id: agency_id
			}).done(function(j) {
				var options = '<option   value="0">' + "--Select--" + '</option>';
				for(var i = 0; i < j.length; i++) {
					options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
				}
				$("select#type_of_benefits_id").html(options);
			}).fail(function(xhr, textStatus, errorThrown) {});
		}		
	 </script>