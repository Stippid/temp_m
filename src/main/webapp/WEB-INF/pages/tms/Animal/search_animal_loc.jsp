	
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
	<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
	<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
	<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 
	
	<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
	
	<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
	
	<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
	<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
	<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
	
    <link rel="stylesheet" href="js/cue/cueWatermark.css">
	<script src="js/cue/cueWatermark.js"></script>
	<script src="js/cue/printAllPages.js" type="text/javascript"></script>
	
	<form:form action="" method="post" class="form-horizontal" commandName="search_animal_loc_CMD" enctype="multipart/form-data">
		<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<strong><u>ANIMALS</u></strong>
					<strong><h3>ANIMAL LOC : SEARCH</h3></strong>
				</div>
				<div class="card-body card-block">
					 	<div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-6">	                					
						                	<label for="text-input" class=" form-control-label">Select Animal Type </label>
						                </div>
						                            
							             <div class="form-check-inline form-check">
											<label for="inline-radio1" class="form-check-label ">
											 <input type="radio" id="anml_type1" name="anml_type" value="Army Dog" class="form-check-input">Army Dogs</label>&nbsp;&nbsp; 
												
											<label for="inline-radio2" class="form-check-label ">
												 <input type="radio" id="anml_type2" name="anml_type" value="Army Equines" class="form-check-input">Army Equines
											</label>
							             </div>				              
	                				 </div>
	                	</div>
					
					<div class="col-md-12">
						<div class="card" id="textboxes" style="display: none; margin-bottom: 0; border: 0;">
							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col col-md-4">
											<label class=" form-control-label">Army No</label>
										</div>
										<div class="col-md-8">
											<input id="army_num" name="army_num" class="form-control" autocomplete="off" value="${army_num1}" maxlength="20">
										</div>
									</div>
								</div>
								
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col col-md-4">
											<label class=" form-control-label">Microchip No</label>
										</div>
										<div class="col-md-8">
											<input id="microchip_no" name="microchip_no" class="form-control" autocomplete="off" value="${microchip_no4}" maxlength="20">
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
	
						<div class="col-md-12" align="left">
						<div class="card" id="textboxes1" style="display: none; margin-bottom: 0; border: 0;">
							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col col-md-4">
											<label class=" form-control-label">Remount No</label>
										</div>
										<div class="col-md-8">
											<input id="remount_no" name="remount_no" class="form-control" autocomplete="off" value="${remount_no1}" maxlength="8">
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col col-md-4">
											<label class=" form-control-label">Microchip No</label>
										</div>
										<div class="col-md-8">
											<input id="microchip_no1" name="microchip_no1" class="form-control" autocomplete="off" value="${microchip_no5}" maxlength="20">
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
	
			<div class="form-control card-footer" align="center" id="dog123" style="display: none; margin-bottom: 0; border: 0;">
				<a href="search_animal_loc" type="reset" class="btn btn-success btn-sm"> Clear </a> 
				<i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm" value="Search" onclick="SearchDog();">
			</div>
	
			<div class="form-control card-footer" align="center" id="equin123" style="display: none; margin-bottom: 0; border: 0;">
				<a href="search_animal_loc" type="reset" class="btn btn-success btn-sm"> Clear </a> 
				<i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm" value="Search"onclick="SearchEqu();">
			</div>
	  </div>
	  
	<div class="container" id="reportshowsearchanml" style="overflow: auto;display: none;">
      <div  class="watermarked" data-watermark="" id="divwatermark">
		<span id="ip"></span>					 
			<table id="SearchanimallocReport" class="table no-margin table-striped  table-hover  table-bordered report_print">
				<thead style="background-color: #9c27b0; color: white;text-align: center;">
					<tr> 
						<th width="5%" >Ser No</th>
						<th width="10%">Army No</th>
						<th width="10%">Microchip No</th>
						<th width="10%">SUS No</th>
						<th width="10%">Unit Name</th>
						<th width="20%">Type of Dog</th>
						<th width="5%">Age</th>
						<th width="20%">Specialisation</th>
						<th width="10%">Action</th>
					</tr>		
				</thead> 
				<tbody>
					<c:if test="${listdog.size() == 0}" >
						<tr>
							<td align="center" style="color: red;"> Data Not Available </td>
						</tr>
					</c:if>
					<c:forEach var="item" items="${listdog}" varStatus="num" >
								<tr>
									<td width="5%" style="text-align: center;">${num.index+1}</td> 
									<td width="10%" style="text-align: center;">${item[0]}</td>
									<td width="10%" style="text-align: center;">${item[1]}</td> 
									<td width="10%" style="text-align: center;">${item[2]}</td>
									<td width="10%" >${item[3]}</td> 
									<td width="20%" >${item[4]}</td>
									<td width="5%" style="text-align: center;">${item[5]}</td> 
									<td width="20%" >${item[6]}</td>
									<td width="10%" style="text-align: center;">${item[8]}</td> 
							</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>	
	</div>
	
	 <div class="container" id="reportshowsearchanmlequ" style="overflow: auto;display: none;">	 
      <div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
		<span id="ip"></span>					 
			<table id="SearchanimallocReportequ" class="table no-margin table-striped  table-hover  table-bordered report_print">
				<thead style="background-color: #9c27b0; color: white;text-align: center;">
					<tr>
						<th width="5%">Ser No</th>
						<th width="10%">Remount No</th>
						<th width="10%">Microchip No</th>
						<th width="20%">SUS No</th>
						<th width="20%">Unit Name</th>
						<th width="20%">Type of Equines</th>
						<th width="5%">Age</th>
						<th width="10%">Action</th>
					</tr>					
				</thead> 
				<tbody>
					   <c:if test="${listequ.size() == 0}" >
							<tr>
								<td align="center" style="color: red;"> Data Not Available </td>
							</tr>
						</c:if>
						<c:forEach var="item" items="${listequ}" varStatus="num" >
								<tr>
									<td width="5%" style="text-align: center;">${num.index+1}</td> 
									<td width="10%" style="text-align: center;">${item[0]}</td>
									<td width="10%" style="text-align: center;">${item[1]}</td> 
									<td width="20%" style="text-align: center;">${item[2]}</td>
									<td width="20%" >${item[3]}</td> 
									<td width="20%" >${item[4]}</td>
									<td width="5%" style="text-align: center;">${item[5]}</td> 
									<td width="10%" style="text-align: center;">${item[7]}</td>
								</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</form:form>
	
	<c:url value="ViewSearchAnimalapprove" var="viewSearchUrlAnimal1" />
	<form:form action="${viewSearchUrlAnimal1}" method="post" id="viewForm1" name="viewForm1" modelAttribute="army_num8">
		<input type="hidden" name="army_num8" id="army_num8" />
		<input type="hidden" name="microchip_no8" id="microchip_no8" />
		<input type="hidden" name="anml_type8" id="anml_type8" />
	</form:form>
	
	<c:url value="ViewSearchAnimalequ" var="viewSearchUrlAnimalequ" />
	<form:form action="${viewSearchUrlAnimalequ}" method="post" id="viewForm2" name="viewForm2" modelAttribute="remount_no9">
		<input type="hidden" name="remount_no9" id="remount_no9"/>
		<input type="hidden" name="microchip_no9" id="microchip_no9"/>
		<input type="hidden" name="anml_type9" id="anml_type9" />
	</form:form>

	<script>
		$(function() {
			$('input[name="anml_type"]').on('click', function() {
				if ($(this).val() == 'Army Dog') {
					$('#textboxes').show();
					$('#textboxes1').hide();
					$('#textboxes2').show();
					$('#armydog').show();
					$('#armyequin').hide();
					$('#dog123').show();
					$('#equin123').hide();
				} else {
					$('#textboxes').hide();
					$('#textboxes1').show();
					$('#textboxes2').show();
					$('#armydog').hide();
					$('#armyequin').show();
					$('#equin123').show();
					$('#dog123').hide();
				}
			});
		});
	</script>
	
	<script>
		function View(army_num) {
			var anml_type = $('input[name=anml_type]:checked').val(); 
			$("#anml_type8").val(anml_type);
			$("input#army_num8").val(army_num);
			document.getElementById('microchip_no8').value = $("#microchip_no").val();
			document.getElementById('viewForm1').submit();
		}
	
		function View1(remount_no) {
			var anml_type = $('input[name=anml_type]:checked').val(); 
			$("#anml_type9").val(anml_type);
			$("input#remount_no9").val(remount_no);
			document.getElementById('microchip_no9').value = $("#microchip_no").val();
			document.getElementById('viewForm2').submit();
		}
	</script>
	
	<script>
	 	$(function() {
	 		
		
		$("input#army_num").keyup(function(){
	    		var army_num = this.value;
	    		var unitNameAuto=$("#army_num");
	    		unitNameAuto.autocomplete({
	    		      source: function( request, response ) {
	    		        $.ajax({
	    		        type: 'POST',
	    		        url: "Getarmyno?"+key+"="+value,
	    		        data: {army_num:army_num},
	    		          success: function( data ) {
	    		        	  var susval = [];
                              var length = data.length-1;
                              var enc = data[length].substring(0,16);
                                    for(var i = 0;i<data.length;i++){
                                            susval.push(dec(enc,data[i]));
                                    }
                                    var dataCountry1 = susval.join("|");
                                    var myResponse = [];  
	    		                    var autoTextVal=unitNameAuto.val();
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
	    		        	  alert("Please Enter Approved ARMY NO");
	    		        	  $("#army_num").val("");
	    		        	  unitNameAuto.val("");	        	  
	    		        	  unitNameAuto.focus();
	    		        	  return false;	             
	    		          }   	         
	    		      }, 
	    		      select: function( event, ui ) {
	    		      	var army_num = ui.item.value;
	    		      	$.post("getmicrochipno?"+key+"="+value, {
	    		      		army_num : army_num
	                 }, function(j) {
	                        
	                 }).done(function(j) {
	                	 var length = j.length-1;
                        var enc = j[length].substring(0,16);
                        $("#microchip_no").val(dec(enc,j[0]));   
	                         
	                 }).fail(function(xhr, textStatus, errorThrown) {
	                 });
	    		     }
	    		});
	    	});
			
			$("input#microchip_no").keyup(function(){
	    		var microchip_no = this.value;
	    		var unitNameAuto=$("#microchip_no");
	    		unitNameAuto.autocomplete({
	    		      source: function( request, response ) {
	    		        $.ajax({
	    		        type: 'POST',
	    		        url: "microchiplist?"+key+"="+value,
	    		        data: {microchip_no : microchip_no},
	    		          success: function( data ) {
	    		        	  var susval = [];
                              var length = data.length-1;
                              var enc = data[length].substring(0,16);
                                    for(var i = 0;i<data.length;i++){
                                            susval.push(dec(enc,data[i]));
                                    }
                                    var dataCountry1 = susval.join("|");
                                    var myResponse = []; 
	    		                    var autoTextVal=unitNameAuto.val();
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
	    		        	  alert("Please Enter Approved Microchip No");
	    		        	  $("#microchip_no").val("");
	    		        	  unitNameAuto.val("");	        	  
	    		        	  unitNameAuto.focus();
	    		        	  return false;	             
	    		          }   	         
	    		      }, 
	    		      select: function( event, ui ) {
	    		      	var microchip_no = ui.item.value;
	    		      	$.post("armynolist?"+key+"="+value, {
	    		      		microchip_no : microchip_no
	                 }, function(j) {
	                        
	                 }).done(function(j) {
	                	 var length = j.length-1;
                        var enc = j[length].substring(0,16);
                        $("#army_num").val(dec(enc,j[0]));   
	                         
	                 }).fail(function(xhr, textStatus, errorThrown) {
	                 });
	    		     }
	    		});
	    	});
			
			
			$("input#microchip_no1").keyup(function(){
	    		var microchip_no = this.value;
	    		var unitNameAuto=$("#microchip_no1");
	    		unitNameAuto.autocomplete({
	    		      source: function( request, response ) {
	    		        $.ajax({
	    		        type: 'POST',
	    		        url: "microchiplistequ?"+key+"="+value,
	    		        data: {microchip_no : microchip_no},
	    		          success: function( data ) {
	    		        	  var susval = [];
                              var length = data.length-1;
                              var enc = data[length].substring(0,16);
                                    for(var i = 0;i<data.length;i++){
                                            susval.push(dec(enc,data[i]));
                                    }
                                     var dataCountry1 = susval.join("|");
		                             var myResponse = []; 
			    		             var autoTextVal=unitNameAuto.val();
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
	    		        	  alert("Please Enter Approved Microchip No");
	    		        	  $("#microchip_no1").val("");
	    		        	  unitNameAuto.val("");	        	  
	    		        	  unitNameAuto.focus();
	    		        	  return false;	             
	    		          }   	         
	    		      }, 
	    		      select: function( event, ui ) {
	    		      	var microchip_no = ui.item.value;
	    		      	$.post("remountnolist?"+key+"="+value, {
	    		      		microchip_no : microchip_no
	                 }, function(j) {
	                        
	                 }).done(function(j) {
	                	 var length = j.length-1;
                        var enc = j[length].substring(0,16);
                        $("#remount_no").val(dec(enc,j[0]));   
	                 }).fail(function(xhr, textStatus, errorThrown) {
	                 });
	    		     }
	    		});
	    	});
			$("input#remount_no").keyup(function(){
	    		var remount_no = this.value;
	    		var unitNameAuto=$("#remount_no");
	    		unitNameAuto.autocomplete({
	    		      source: function( request, response ) {
	    		        $.ajax({
	    		        type: 'POST',
	    		        url: "Getremountno?"+key+"="+value,
	    		        data: {remount_no:remount_no},
	    		          success: function( data ) {
	    		        	  var susval = [];
                              var length = data.length-1;
                              var enc = data[length].substring(0,16);
                                    for(var i = 0;i<data.length;i++){
                                            susval.push(dec(enc,data[i]));
                                    }
                                    var dataCountry1 = susval.join("|");
                                    var myResponse = []; 
	    		                    var autoTextVal=unitNameAuto.val();
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
	    		        	  alert("Please Enter Approved Remount No");
	    		        	  $("#remount_no").val("");
	    		        	  unitNameAuto.val("");	        	  
	    		        	  unitNameAuto.focus();
	    		        	  return false;	             
	    		          }   	         
	    		      }, 
	    		      select: function( event, ui ) {
	    		      	var remount_no = ui.item.value;
	    		      	$.post("getmicrochipnoequ?"+key+"="+value, {
	    		      		remount_no : remount_no
	                 }, function(j) {
	                        
	                 }).done(function(j) {
	                	 var length = j.length-1;
                        var enc = j[length].substring(0,16);
                        $("#microchip_no1").val(dec(enc,j[0]));   
	                 }).fail(function(xhr, textStatus, errorThrown) {
	                 });
	    		     }
	    		});
	    	});
	 	});	
	</script> 
	
	<script>
		function SearchDog(){
		var anml_type = $('input[name=anml_type]:checked').val(); 
		document.getElementById(anml_type1.id).checked == true
		$("#anml_type3").val(anml_type);
        $("#army_num1").val($("#army_num").val());   
        $("#microchip_no4").val($("#microchip_no").val()); 
        document.getElementById('searchFormDog').submit();
	}
		function SearchEqu(){
		var anml_type = $('input[name=anml_type]:checked').val(); 
		document.getElementById(anml_type2.id).checked == true
		$("#anml_type4").val(anml_type);
        $("#remount_no1").val($("#remount_no").val());   
        $("#microchip_no5").val($("#microchip_no1").val()); 
        document.getElementById('searchFormEqu').submit();
	}
</script>
		
		 <c:url value="search_loc_dog" var="searchUrl" />
		<form:form action="${searchUrl}" method="post" id="searchFormDog" name="searchFormDog" modelAttribute="anml_type3">
			<input type="hidden" name="army_num1" id="army_num1" value="0"/>
			<input type="hidden" name="microchip_no4" id="microchip_no4" value="0"/>
			<input type="hidden" name="anml_type3" id="anml_type3" value="0"/>
		</form:form> 
		
	    <c:url value="search_loc_equ" var="searchUrl" />
		<form:form action="${searchUrl}" method="post" id="searchFormEqu" name="searchFormEqu" modelAttribute="anml_type4">
			<input type="hidden" name="remount_no1" id="remount_no1" value="0"/>
			<input type="hidden" name="microchip_no5" id="microchip_no5" value="0"/>
			<input type="hidden" name="anml_type4" id="anml_type4" value="0"/>
		</form:form> 
<script>
$(document).ready(function() {
	$("#anml_type").val('${anml_type3}');
	$("#anml_type").val('${anml_type4}');
		if('${anml_type3}' == "Army Dog")
		{
			document.getElementById(anml_type1.id).checked = true;
			$("#army_num").val('${army_num1}');
			$("#microchip_no").val('${microchip_no4}');
			$("div#divwatermark").val('').addClass('watermarked');								
			watermarkreport(); 
			$("#reportshowsearchanml").show();
			$('#textboxes').show();
			$('#dog123').show();
		}
		
		else if('${anml_type4}' == "Army Equines"){
			$("#remount_no").val('${remount_no1}');
			$("#microchip_no1").val('${microchip_no5}');
			document.getElementById(anml_type2.id).checked = true;
			$("div#divwatermark").val('').addClass('watermarked');								
			watermarkreport(); 
			$("#reportshowsearchanmlequ").show();
			$('#textboxes1').show();
			$('#equin123').show();
			$("#reportshowsearchanml").hide();
			$('#textboxes').hide();
			$('#dog123').hide();
		}
		else{
			document.getElementById(anml_type1.id).checked = false;
			document.getElementById(anml_type2.id).checked = false;
		}
});
</script>

