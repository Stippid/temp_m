	
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
			
	<div class="animated fadeIn" >
			<div class="col-md-12" align="center">
				<div class="card">
					<div class="card-header">
						<strong style="text-decoration: underline;">RESTRICTED</strong>
					</div>
					<div class="card-header">
						<div class="col-md-2">
							<img src="js/miso/images/indianarmy_smrm5aaa.jpg" style="height: 85px;">
						</div>
						<div class="col-md-8">
							<h3>DETAILS OF ANIMALS</h3>
						</div>
						<div class="col-md-2">
							<img src="js/miso/images/dgis-logo.png" style="height: 85px;">
						</div>
					</div>
			     	<div class="card-body card-block">
						<div class="col-md-12">		 
		  					<div class="col-md-6">
		            			<div class="row form-group"> 
		              				<div class="col col-md-4">
		                				<label for="text-input" class=" form-control-label" >SUS No</label>
		              				</div>
		              				<div class="col-12 col-md-8">
		                				<input type="text" id="sus_no" name="sus_no"  class="form-control autocomplete" autocomplete="off" value="${sus_no1}" maxlength="8"> 
									</div>
								</div>
		  					</div>
		  					<div class="col-md-6">
		 						<div class="row form-group">  								
		               				<div class="col col-md-4">
		                				<label for="text-input" class=" form-control-label">Unit Name</label>
		               				</div>
		               				<div class="col-12 col-md-8">
		                 				<input type="text" id="unit_name" name="unit_name"  class="form-control autocomplete" autocomplete="off" value="${unit_name1}" maxlength="100">
									</div>
		 						</div>
		 					</div>	
		  				</div>
		  				
		  				<div class="col-md-12">		 
		  					<div class="col-md-6">
		            			<div class="row form-group"> 
		              				<div class="col col-md-4">
		                				<label for="text-input" class=" form-control-label" >Command SUS No</label>
		              				</div>
		              				<div class="col-12 col-md-8">
		                				<input type="text" id="comd_sus_no" name="comd_sus_no"  class="form-control autocomplete" autocomplete="off" value="${comd_sus_no1}" maxlength="8"> 
									</div>
								</div>
		  					</div>
		  					<div class="col-md-6">
		 						<div class="row form-group">  								
		               				<div class="col col-md-4">
		                				<label for="text-input" class=" form-control-label">Command</label>
		               				</div>
		               				<div class="col-12 col-md-8">
		                 				<input type="text" id="comd_unit_name" name="comd_unit_name"  class="form-control autocomplete" autocomplete="off" value="${comd_unit_name1}" maxlength="100">
									</div>
		 						</div>
		 					</div>	
		  				</div>
		  				<div class="col-md-12">	
							<div class="col-md-6">
			        			<div class="row form-group">
			        				<div class="col col-md-4">
			        					<label class=" form-control-label"><strong style="color: red;">*</strong> Type of Animal</label>
			        				</div>
			        				<div class="col-12 col-md-8">
			                  			<select  id="anml_type" name="anml_type" class="form-control-sm form-control">
			                  			    <option value="">All</option>
											<option value="Army Dog">Army Dog</option>
											<option value="Army Equines">Army Equines</option>  
										</select>
									</div>
			  					</div>
							</div>
							
								<div class="col-md-6" id ="typedog" style="display: none">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">Type of Dog</label>
									</div>
									<div class="col-12 col-md-8">
										<input type = "hidden" id = "hdtypeofdog" name = "hdtypeofdog" >
										<select id="type_dog" name="type_dog" class="form-control">
											<option value="">--Select--</option>
											<c:forEach var="item" items="${getTypeOfDogList}" varStatus="num" >
												<option value="${item[1]}" name="${item[0]}">${item[0]}</option>
											</c:forEach>
											</select> 	
									</div>
								</div>
							</div>
							
					  <div class="col-md-6" id="tyequ" style="display: none">
							<div class="row form-group" >
								<div class="col col-md-4">
									<label for="text-input" class=" form-control-label">Type of Equines</label>
								</div>
								<div class="col-12 col-md-8" style="padding-bottom: 40px">
								    <input type = "hidden" id = "hdtypeofequ" name = "hdtypeofequ" >
									    <select id="type_equines" name="type_equines"  class="form-control">
											<option value="">--Select--</option>
											<c:forEach var="item" items="${getAnimalTypeList}" varStatus="num" >
												<option value="${item[1]}" name="${item[0]}">${item[0]}</option>
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
									<label for="text-input" class=" form-control-label">Date (From) </label>
								</div>
								<div class="col-12 col-md-8">
									<input type="date" id="from_date" name="from_date" class="form-control" class="form-control" min='1899-01-01' max='${date}' value="${from_date}">
								</div>
							</div>
						</div>
						
							<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label for="text-input" class=" form-control-label">Date (To) </label>
								</div>
								<div class="col-12 col-md-8">
									<input type="date" id="to_date" name="to_date" class="form-control" class="form-control" min='1899-01-01' max='${date}' value="${to_date}" onchange="return checkdate(this,from_date)">
								</div>
							</div>
						</div>
					</div>	
					<div class="col-md-12">
						<div class="col-md-6">
			        			<div class="row form-group">
			        				<div class="col col-md-4">
			        					<label class=" form-control-label"><strong style="color: red;">*</strong> Status</label>
			        				</div>
			        				<div class="col-12 col-md-8">
			                  			<select  id="animal_status" name="animal_status" class="form-control-sm form-control">
			                  				<option value="all">All</option>
											<option value="live">Live</option>
											<option value="dead">Dead</option>  
										</select>
									</div>
			  					</div>
							</div>
					</div>	
				</div>
				<div class="form-control card-footer" align="center">
					 <i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm" value="Search" onclick="Search();">
		             <a href="anml_data" type="reset" class="btn btn-success btn-sm"> Clear </a> 
		             <input type="button" id="printId" class="btn btn-primary btn-sm" value="Print" onclick="printDiv();" disabled>
				</div> 
			</div>
		</div>
	</div>

		<div class="col-md-12" id="reportall" style=" display:none;">
		 	<div id="divShow" style="display: block;"></div>
		    <div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
				<span id="ip"></span>					 
				<table id="alltable" class="table no-margin table-striped  table-hover  table-bordered report_print">
					<thead style="background-color: #9c27b0; color: white;text-align: center;">
						<tr>
							<th width="5%" >Ser No</th>
							<th width="15%" >Unit Name</th>
							<th width="10%" >Army No / Remount No</th>
							<th width="20%" >Name of Dog / Type of Equines</th>
							<th width="10%" >Breed</th>
							<th width="10%" >Color</th>
							<th width="5%" >Sex</th>
							<th width="5%" >Age</th>
							<th width="6%" >Microchip No</th>
							<th width="9%" >Fitness Status</th>
							<th width="5%" >TOS</th>
						</tr>			
					</thead> 
					<tbody>
						<c:if test="${list.size() == 0}" >
							<tr>
								<td align="center" style="color: red;"> Data Not Available </td>
							</tr>
						</c:if>
					<c:forEach var="item1" items="${list}" varStatus="num" >
								<tr>
									<td width="5%" style="text-align: center;">${num.index+1}</td> 
									<td width="15%" >${item1[0]}</td>
									<td width="10%" style="text-align: center;">${item1[1]}</td>
									<td width="20%" >${item1[2]}</td>
									<td width="10%" style="text-align: left;">${item1[3]}</td>  
									<td width="10%" >${item1[4]}</td>
									<td width="5%" >${item1[5]}</td>
									<td width="5%" style="text-align: center;">${item1[6]}</td>
									<td width="6%" style="text-align: center;">${item1[7]}</td>
									<td width="9%" style="text-align: left;">${item1[8]}</td> 
									<td width="5%" style="text-align: center;">${item1[9]}</td>
								</tr>
			        </c:forEach>
					</tbody>
				</table>
			</div>
		
			<div class="row">
				<div  class="col-md-12">
					<div class="card">
						<div class="card-body"> 
			   				<table class="col-md-12">
			   				<tbody style="overflow: hidden;">
			   					<tr>
			   						<td align="left" style="font-size: 15px">
			   							<label>
											Prepared By :<br>
											Station :<br>
											Date :
										</label>	
			   						</td>
			   						<td align="center" style="font-size: 15px">
			   							<label>Checked By : </label>
			   						</td>
			   						<td align="center" style="font-size: 15px">
			   							<label>Approved By : </label><br> 
			   						</td>
			   					</tr>
			   					</tbody>
			   				</table>
			   			</div> 
		    		</div>
		   		</div>
	   		</div>
		</div>	 
		
	
		<div class="col-md-12" id="reportdog" style=" display: none; ">
		 <div id="divShow" style="display: block;">
		 </div>  
	                  
	      <div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
			<span id="ip"></span>					 
				<table id="dogtable" class="table no-margin table-striped  table-hover  table-bordered report_print">
					<thead style="background-color: #9c27b0 ; color: white;text-align: center;">
						<tr>
							<th width="5%">Ser No</th>
							<th width="15%">Unit Name</th>
							<th width="5%">Army No</th>
							<th width="8%">Microchip No</th>
							<th width="17%">Name of Dog</th>
							<th width="10%">Breed</th>
							<th width="5%">Age</th>
							<th width="5%">Sex</th>
							<th width="10%">Color</th>
							<th width="10%">Fitness Status</th>
							<th width="10%">TOS</th>
						</tr>		
					</thead> 
					<tbody>
					<c:if test="${list.size() == 0}" >
							<tr>
								<td align="center" style="color: red;"> Data Not Available </td>
							</tr>
					</c:if>
					<c:forEach var="item1" items="${list}" varStatus="num" >
								<tr>
									<td width="5%" style="text-align: center;">${num.index+1}</td> 
									<td width="15%" >${item1[0]}</td>
									<td width="5%" style="text-align: center;">${item1[1]}</td>
									<td width="8%" style="text-align: center;">${item1[2]}</td>
									<td width="17%" >${item1[3]}</td>  
									<td width="10%" >${item1[4]}</td>
									<td width="5%" style="text-align: center;">${item1[5]}</td>
									<td width="5%" >${item1[6]}</td>
									<td width="10%" >${item1[7]}</td>
									<td width="10%" >${item1[8]}</td> 
									<td width="10%" style="text-align: center;">${item1[9]}</td>
								</tr>
				   </c:forEach>
					</tbody>
				</table>
			</div>
			
			<div class="row">
				<div  class="col-md-12">
					<div class="card">
						<div class="card-body"> 
			   				<table class="col-md-12">
			   				<tbody style="overflow: hidden;">
			   					<tr>
			   						<td align="left" style="font-size: 15px">
			   							<label>
											Prepared By :<br>
											Station :<br>
											Date :
										</label>	
			   						</td>
			   						<td align="center" style="font-size: 15px">
			   							<label>Checked By : </label>
			   						</td>
			   						<td align="center" style="font-size: 15px">
			   							<label>Approved By : </label><br> 
			   						</td>
			   					</tr>
			   					</tbody>
			   				</table>
			   			</div> 
		    		</div>
		   		</div>
	   		</div>
		</div> 
		
		<div class="col-md-12" id="reportequ" style="display: none;">
		 	<div id="divShow" style="display: block;"></div>  
			<div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
			<span id="ip"></span>					 
				<table id="equtable" class="table no-margin table-striped  table-hover  table-bordered report_print">
					<thead style="background-color: #9c27b0; color: white;text-align: center;">
						<tr>
							<th width="5%">Ser No</th>
							<th width="15%">Unit Name</th>
							<th width="10%">Remount/Neck No</th>
							<th width="10%">Microchip No</th>
							<th width="10%">Type of Equines</th>
							<th width="10%">Breed</th>
							<th width="5%">Age</th>
							<th width="5%">Sex</th>
							<th width="10%">Color</th>
							<th width="10%">Fitness Status</th>
							<th width="10%">TOS</th>
						</tr>				
					</thead> 
					<tbody>
					   <c:if test="${list.size() == 0}" >
							<tr>
								<td align="center" style="color: red;"> Data Not Available </td>
							</tr>
						</c:if>
						<c:forEach var="item1" items="${list}" varStatus="num" >
							<tr>
							    <td width="5%" style="text-align: center;">${num.index+1}</td> 
								<td width="15%" >${item1[0]}</td>
								<td width="10%" style="text-align: center;">${item1[1]}</td>
								<td width="10%" style="text-align: center;">${item1[2]}</td>
								<td width="10%" >${item1[3]}</td>  
								<td width="10%" >${item1[4]}</td>
								<td width="5%" style="text-align: center;">${item1[5]}</td>
								<td width="5%" >${item1[6]}</td>
								<td width="10%" >${item1[7]}</td>
								<td width="10%" >${item1[8]}</td> 
								<td width="10%" style="text-align: center;">${item1[9]}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		
			<div class="row">
				<div  class="col-md-12">
					<div class="card">
						<div class="card-body"> 
			   				<table class="col-md-12">
			   				<tbody style="overflow: hidden;">
			   					<tr>
			   						<td align="left" style="font-size: 15px">
			   							<label>
											Prepared By :<br>
											Station :<br>
											Date :
										</label>	
			   						</td>
			   						<td align="center" style="font-size: 15px">
			   							<label>Checked By : </label>
			   						</td>
			   						<td align="center" style="font-size: 15px">
			   							<label>Approved By : </label><br> 
			   						</td>
			   					</tr>
			   					</tbody>
			   				</table>
			   			</div> 
		    		</div>
		   		</div>
	   		</div>
		</div>
	 <!-- </div> -->  
	
	<c:url value="all_data_print" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="sus_no1">
		<input type="hidden" name="sus_no1" id="sus_no1" value="0"/>
		<input type="hidden" name="unit_name1" id="unit_name1" value="0"/>
		<input type="hidden" name="anml_type1" id="anml_type1" value="0"/>
		<input type="hidden" name="type_dog1" id="type_dog1" value="0"/>
		<input type="hidden" name="type_equines1" id="type_equines1" value="0"/>
		<input type="hidden" name="from_date1" id="from_date1" value="0"/>
		<input type="hidden" name="to_date1" id="to_date1" value="0"/>
		<input type="hidden" name="comd_sus_no1" id="comd_sus_no1" value="0"/>
		<input type="hidden" name="comd_unit_name1" id="comd_unit_name1" value="0"/>
		<input type="hidden" name="hdtypeofdog1" id="hdtypeofdog1" value="0"/> 
		<input type="hidden" name="hdtypeofequ1" id="hdtypeofequ1" value="0"/>
		<input type="hidden" name="animal_status1" id="animal_status1" value=""/>
	</form:form> 
		   
	<script>
	 $("select#type_dog").change(function() { 
	   var j1 = $(this).find('option:selected').attr("name");
	   document.getElementById('hdtypeofdog').value = j1;
	 }); 
	
	$("select#type_equines").change(function() {
	    var nk = $(this).find('option:selected').attr("name");
	    document.getElementById('hdtypeofequ').value = nk;
	}); 
	
	$(document).ready(function() {
		$("#anml_type").val('${anml_type1}');
		$("#type_dog").val('${type_dog1}');
		$("#type_equines").val('${type_equines1}');
		$("#from_date").val('${from_date1}');
		$("#to_date").val('${to_date1}');
		$("#comd_sus_no").val('${comd_sus_no1}');
		$("#comd_unit_name").val('${comd_unit_name1}');
		$("#hdtypeofdog").val('${hdtypeofdog1}');
		$("#hdtypeofequ").val('${hdtypeofequ1}');
		if('${animal_status}' != ''){
			$("#animal_status").val('${animal_status}');
		}
		
		$("#sus_no").val('${sus_no1}');
	    $("#unit_name").val('${unit_name1}');
		
			if('${anml_type1}' == "" & '${list.size()}' != 0)
			{
				$("div#divwatermark").val('').addClass('watermarked');								
				watermarkreport(); 
				document.getElementById("printId").disabled = false;
				$('#printDiv').show();
				$('#reportall').show();
				$('#reportdog').hide();
				$('#reportequ').hide(); 
			}
			else if('${anml_type1}' == "Army Dog"  & '${list.size()}' != 0)
			{
				$("div#divwatermark").val('').addClass('watermarked');								
				watermarkreport(); 
				document.getElementById("printId").disabled = false;
				$('#printDiv').show();
				$('#reportall').hide();
				$('#reportdog').show();               
				$('#reportequ').hide();
				$('#typedog').show(); 
                $('#tyequ').hide();
			}
			else if($("#anml_type").val() == "Army Equines"  & '${list.size()}' != 0)
			{
				$("div#divwatermark").val('').addClass('watermarked');								
				watermarkreport(); 
				document.getElementById("printId").disabled = false;
				$('#printDiv').show();
				$('#tyequ').show();
				$('#reportequ').show(); 
				$('#reportall').hide();
				$('#reportdog').hide();
				$('#typedog').hide(); 
			}
			else{
				$('#reportall').hide();
				$('#reportdog').hide();
				$('#reportequ').hide();
				$('#typedog').hide(); 
                $('#tyequ').hide();
			}
		
				if('${anml_type1}' == "" & '${list.size()}' == ''){
					$('#alltable').hide();
				}
				
				if('${anml_type1}' == "" & '${list.size()}' == 0){
					$("div#divwatermark").val('').addClass('watermarked');								
					watermarkreport(); 
					$('#reportall').show();
				} 
				else if('${anml_type1}' == "Army Dog"  & '${list.size()}' == 0){
					$("div#divwatermark").val('').addClass('watermarked');								
					watermarkreport(); 
					$('#reportdog').show();               
					$('#typedog').show(); 

				}
				else if($("#anml_type").val() == "Army Equines"  & '${list.size()}' == 0){
					$("div#divwatermark").val('').addClass('watermarked');								
					watermarkreport(); 
					$('#tyequ').show();
					$('#reportequ').show(); 
				}
	});

	
	function printDiv() 
	{		
		var anml_type = $("#anml_type").val();
		if(anml_type==""  & '${list.size()}' != 0){
			var printLbl = ["SUS NO :","UNIT NAME :","COMD SUS NO :","COMD :","TYPE OF ANIMAL :","FROM DATE :","TO DATE :"];
		 	var date = new Date();
		 	var formattedtoday = date.getDate() + '-' + (date.getMonth() + 1) + '-' + date.getFullYear();		 	 	
			var printVal = [document.getElementById('sus_no').value,document.getElementById('unit_name').value,document.getElementById('comd_sus_no').value,document.getElementById('comd_unit_name').value,
				document.getElementById('anml_type').value,document.getElementById('from_date').value,document.getElementById('to_date').value]; 	
			printDivOptimize('reportall','Details of Animals',printLbl,printVal,"");
			
		}
		else if(anml_type=="Army Dog"  & '${list.size()}' != 0){              
			var printLbl = ["SUS NO :","UNIT NAME :","COMD SUS NO :","COMD :","TYPE OF ANIMAL :","TYPE OF DOG :","FROM DATE :","TO DATE :"];
		 	var date = new Date();
		 	var formattedtoday = date.getDate() + '-' + (date.getMonth() + 1) + '-' + date.getFullYear();		
		    var printVal = [document.getElementById('sus_no').value,document.getElementById('unit_name').value,document.getElementById('comd_sus_no').value,document.getElementById('comd_unit_name').value,
		    	document.getElementById('anml_type').value,document.getElementById('hdtypeofdog').value,document.getElementById('from_date').value,document.getElementById('to_date').value]; 	  
	        printDivOptimize('reportdog','Details of Animals',printLbl,printVal,"");
		   }   	
		else if(anml_type=="Army Equines"  & '${list.size()}' != 0){       
			var printLbl = ["SUS NO :","UNIT NAME :","COMD SUS NO :","COMD :","TYPE OF ANIMAL :","TYPE OF EQUINES :","FROM DATE :","TO DATE :"];
		 	var date = new Date();
		 	var formattedtoday = date.getDate() + '-' + (date.getMonth() + 1) + '-' + date.getFullYear();		 			 	
		    var printVal = [document.getElementById('sus_no').value,document.getElementById('unit_name').value,document.getElementById('comd_sus_no').value,document.getElementById('comd_unit_name').value,
		    	document.getElementById('anml_type').value,document.getElementById('hdtypeofequ').value,document.getElementById('from_date').value,document.getElementById('to_date').value]; 	  
	        printDivOptimize('reportequ','Details of Animals',printLbl,printVal,"");
		 }else{
			alert("Data Not Available");	  
		 }	
	}
	</script>
		   	
	<script>
    	   
    	   function isNumber0_9Only(evt){
    			var charCode = (evt.which) ? evt.which : evt.keyCode;
    			if( ! ( charCode >= 48 && charCode <= 57 ) && charCode != 8 ){
    				 return false;
    			}
    		    return true;
    		}
	</script>
		
	<script>
	  
	        $(function() {
	            $('#anml_type').change(function(){
	            	 if($('#anml_type').val() == ''){
	            		 $('#typedog').hide(); 
	                     $('#tyequ').hide();
	            	 }
	            	 else if($('#anml_type').val() == 'Army Dog') {
	                    $('#typedog').show(); 
	                    $('#tyequ').hide();
	                } 
	            	 else {
	                    $('#typedog').hide(); 
	                    $('#tyequ').show();
	                } 
	            });
	        });
	</script> 
		
 	<script>
		$(function() {
	
		        
		        // Unit SUS No
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
		        	        	  document.getElementById("unit_name").value="";
		        	        	  susNoAuto.val("");	        	  
		        	        	  susNoAuto.focus();
		        	        	  return false;	             
		        	          }   	         
		        	    }, 
		        		select: function( event, ui ) {
		        			var sus_no = ui.item.value;			      	
		        			 $.post("getActiveUnitNameFromSusNo?"+key+"="+value, {
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
				// End
				
	        
	     // Unit Unit Name
        	$("#unit_name").keyup(function(){
        		var unit_name = this.value;
        	var unit_nameAuto=$("#unit_name");

        	unit_nameAuto.autocomplete({
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
                                var dataCountry1 = susval.join("|");
                                var myResponse = []; 
        	                    var autoTextVal=unit_nameAuto.val();
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
        	        	  alert("Please Enter Approved Unit Name.");
        	        	  document.getElementById("sus_no").value="";
        	        	  unit_nameAuto.val("");	        	  
        	        	  unit_nameAuto.focus();
        	        	  return false;	             
        	          }   	         
        	      }, 
        	      select: function( event, ui ) {
        	    	  var target_unit_name = ui.item.value;
        	    		 $.post("getTargetSUSFromUNITNAME?"+key+"="+value, {
        	    			 target_unit_name:target_unit_name
	                 }, function(j) {
	                        
	                 }).done(function(j) {
	                	 var length = j.length-1;
                         var enc = j[length].substring(0,16);
                         $("#sus_no").val(dec(enc,j[0]));   
	                         
	                 }).fail(function(xhr, textStatus, errorThrown) {
	                 });
        	      } 	     
        	}); 			
        });
		
	
    	 $("#comd_sus_no").keyup(function(){
	        	var comd_sus_no = this.value;
	        	var susNoAuto=$("#comd_sus_no");
	        
	        	susNoAuto.autocomplete({
	        	      source: function( request, response ) {
	        	        $.ajax({
	        	        type: 'POST',
	        	        url: "getComdSusNolist?"+key+"="+value,
	        	        data: {comd_sus_no : comd_sus_no},
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
	        	        	  alert("Please Enter Approved Command SUS No.");
	        	        	  document.getElementById("comd_unit_name").value="";
	        	        	  susNoAuto.val("");	        	  
	        	        	  susNoAuto.focus();
	        	        	  return false;	             
	        	          }   	         
	        	    }, 
	        		select: function( event, ui ) {
	        			var comd_sus_no = ui.item.value;			      	
	        			$.post("getcmdunitname?"+key+"="+value, {
	        				comd_sus_no:comd_sus_no
	                 }, function(j) {
	                        
	                 }).done(function(j) {
	                	 var length = j.length-1;
                        var enc = j[length].substring(0,16);
                        $("#comd_unit_name").val(dec(enc,j[0]));   
	                         
	                 }).fail(function(xhr, textStatus, errorThrown) {
	                 });
	        		} 	     
	        	});	
	        });
	
		
		$("input#comd_unit_name").keyup(function(){
    		var comd_unit_name = this.value;
    		var unitNameAuto=$("#comd_unit_name");
    		unitNameAuto.autocomplete({
    		      source: function( request, response ) {
    		        $.ajax({
    		        type: 'POST',
    		        url: "getcomdunitnamelist?"+key+"="+value,
    		        data: {comd_unit_name : comd_unit_name},
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
    		        	  alert("Please Enter Approved Unit Name");
    		        	  $("#comd_unit_name").val("");
    		        	  unitNameAuto.val("");	        	  
    		        	  unitNameAuto.focus();
    		        	  return false;	             
    		          }   	         
    		      }, 
    		      select: function( event, ui ) {
    		      	var comd_unit_name = ui.item.value;
    		      	$.post("getcmdsus?"+key+"="+value, {
    		      		comd_unit_name:comd_unit_name
                 }, function(j) {
                        
                 }).done(function(j) {
                	 var length = j.length-1;
                    var enc = j[length].substring(0,16);
                    $("#comd_sus_no").val(dec(enc,j[0]));   
                         
                 }).fail(function(xhr, textStatus, errorThrown) {
                 });
    		     }
    		});
    	});
	});
</script> 
		
		
<Script>
	function Search(){
		
	    $("#sus_no1").val($("#sus_no").val());
	    $("#unit_name1").val($("#unit_name").val());
	    $("#anml_type1").val($("#anml_type").val());
	    $("#type_dog1").val($("#type_dog").val());
	    $("#type_equines1").val($("#type_equines").val());
	    $("#from_date1").val($("#from_date").val());
	    $("#to_date1").val($("#to_date").val());
	    $("#comd_sus_no1").val($("#comd_sus_no").val());
	    $("#comd_unit_name1").val($("#comd_unit_name").val());
	    $("#hdtypeofdog1").val($("#hdtypeofdog").val());
	    $("#hdtypeofequ1").val($("#hdtypeofequ").val());
	    $("#animal_status1").val($("#animal_status").val());
		document.getElementById('searchForm').submit();
		
	}
	
	
	function checkdate(obj,from_date) {
		
		if(from_date.value !="")
		{
			var id = obj.id;
			var myDate = document.getElementById(id).value;
			var Date1 = from_date.value;
			if ((Date.parse(myDate) < Date.parse(Date1))) {
				alert('To Date should not be less than From Date');
				 $("input#to_date").focus();
				obj.value = "";
			}
		}
	}  

</Script>