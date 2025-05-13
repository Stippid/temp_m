	
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
	
	<style>
.selectBox {
	position: relative;
}
element.style {
    width: 70%;
}


span.subspan {
	padding: 5px;
	background-color: #79cece54;
	border-radius: 20px;
	margin: 3px;
	display: block;
	width: fit-content;
}

.selectBox select {
	width: 100%;
	font-weight: bold;
}

.overSelect {
	position: absolute;
	left: 0;
	right: 0;
	top: 0;
	bottom: 0;
}

.checkboxes {
	display: none;
	border: 1px #dadada solid;
}

.checkboxes label {
	margin-left: 10px;
	text-align: left;
	display: block;
}

.checkboxes label:hover {
	background-color: #1e90ff;
}
</style>
		
			
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
							<h3>ANIMALS REPORTS</h3>
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
		                				<label for="text-input" class=" form-control-label" >Command</label>
		              				</div>
		              				<div class="col-12 col-md-8">
		                				<%-- <input type="text" id="comd_sus_no" name="comd_sus_no"  class="form-control autocomplete" autocomplete="off" value="${comd_sus_no1}" maxlength="8"> --%> 
									<select id="comd_sus_no" name="comd_sus_no" class="form-control" >
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
		                				<label for="text-input" class=" form-control-label">Corps</label>
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
		                				<label for="text-input" class=" form-control-label" >Div</label>
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
		                				<label for="text-input" class=" form-control-label">Bde</label>
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
			        					<label class=" form-control-label"><strong style="color: red;">*</strong> Type of Animal</label>
			        				</div>
			        				<div class="col-12 col-md-8">
			                  			<select  id="anml_type" name="anml_type" class="form-control-sm form-control">
			                  			    <option value="">--Select--</option>
											<option value="Army Dog">Army Dog</option>
											<option value="Army Equines">Army Equines</option>  
										</select>
									</div>
			  					</div>
							</div>
							
						
							
								<div class="col-md-6" id ="typedog" style="display: none">
						<div class="row form-group">
							<div class="col-md-4" align="left">
								<label class=" form-control-label"><strong
									style="color: red;">* </strong>Specialization</label>
							</div>
							<div class="col-md-8">
								<div class="multiselect">

									<div class="selectBox" onclick="showCheckboxes()">
										<select name="sub_quali" id="sub_quali"
											class=" form-control">
											<option>Select Specialization</option>
										</select>
										<div class="overSelect"></div>
									</div>
									<div id="checkboxes" class="checkboxes"
										style="max-height: 200px; overflow: auto;">
										<div style="">
										<b><input type=checkbox id='nSelAll' name='tregn' onclick="callsetall();">&nbsp;
									Select all </b>&nbsp;&nbsp;
											<input type="text" id="dog_search"
												class="form-control autocomplete" autocomplete="off"
												placeholder="Search Specialization">
										</div>
										<div>
											<c:forEach var="item" items="${getsplzList}" varStatus="num">
												<label for="one" class="doglist"> <input class="nrCheckBox"
													type="checkbox" name="multisub_dog" value="${item[1]}" onclick="Table_Name();"  />
													${item[0]}
												</label>
											</c:forEach>
											
											<input type = "hidden" id = "type_dog" name = "type_dog" >
											
										</div>
									</div>

								</div>
							</div>

						</div>
					</div>
							
								<div class="col-md-6" id="tyequ" style="display: none">
						<div class="row form-group">
							<div class="col-md-4" align="left">
								<label class=" form-control-label"><strong
									style="color: red;">* </strong>Type of Equines</label>
							</div>
							<div class="col-md-8">
								<div class="multiselect">

									<div class="selectBox" onclick="showCheckboxes_Equines()">
										<select name="sub_quali" id="sub_quali"
											class=" form-control">
											<option>Select Type of Equines</option>
										</select>
										<div class="overSelect"></div>
									</div>
									<div id="checkboxes_equines" class="checkboxes"
										style="max-height: 200px; overflow: auto;">
										<div style="">
										<b><input type=checkbox id='nSelAll_equines' name='tregn' onclick="callsetall_equines();">&nbsp;
									Select all </b>&nbsp;&nbsp;
											<input type="text" id="equines_search"
												class="form-control autocomplete" autocomplete="off"
												placeholder="Search Type of Equines">
										</div>
										<div>
											<c:forEach var="item" items="${getAnimalTypeList}" varStatus="num">
												<label for="one" class="equineslist"> <input class="nrCheckBox_equines"
													type="checkbox" name="multisub_equines" value="${item[1]}" onclick="Table_Name_equines();"  />
													${item[0]}
												</label>
											</c:forEach>
											
											<input type = "hidden" id = "type_equines" name = "type_equines" >
											
										</div>
									</div>

								</div>
							</div>

						</div>
					</div>
						
					</div>
					<div class="col-md-12" style="display: none;">
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
				</div>
				<div class="form-control card-footer" align="center">
					 <i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm" value="Search" onclick="Search();">
		             <a href="anml_data_ue_uh" type="reset" class="btn btn-success btn-sm"> Clear </a> 
		             <!-- <input type="button"exportIdintId" class="btn btn-primary btn-sm" value="Print" onclick="printDiv();" disabled> -->
				<i class="fa fa-download"></i><input type="button" id="exportId" class="btn btn-sm btn_report" style="background-color: #e37c22;color: white;" value="Export" onclick="fnExcelReport();">
				
				</div> 
			</div>
		</div>
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
		<!-- </div> -->	 
		
	
		<div class="col-md-12" id="reportdog" style=" display: none; ">
		 <div id="divShow" style="display: block;">
		 </div>  
	                  
	      <div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
			<span id="ip"></span>					 
				<table id="dogtable" class="table no-margin table-striped  table-hover  table-bordered report_print">
					<thead style="background-color: #9c27b0 ; color: white;text-align: center;">
						<tr>
							<th width="5%">Ser No</th>
							<th width="15%">Command</th>
							<th width="5%">Corps</th>
							<th width="8%">Div</th>
							<th width="17%">Bde</th>
							<th width="10%">Unit Name</th>
							<th width="5%">Sus No</th>
							<th width="5%">Specialization</th>
							<th width="10%">UE</th>
							<th width="10%">UH</th>
							<!-- <th width="10%">TOS</th> -->
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
									<%-- <td width="10%" style="text-align: center;">${item1[9]}</td> --%>
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
							<th width="15%">Command</th>
							<th width="10%">Corps</th>
							<th width="10%">Div</th>
							<th width="10%">Bde</th>
							<th width="10%">Unit Name</th>
							<th width="5%">Sus No</th>
							<th width="5%">Type of Animal</th>
							<th width="10%">UE</th>
							<th width="10%">UH</th>
							<!-- <th width="10%">TOS</th> -->
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
								<%-- <td width="10%" style="text-align: center;">${item1[9]}</td> --%>
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
	
	<c:url value="all_data_print_ue_uh" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="sus_no1">
		
		<input type="hidden" name="anml_type1" id="anml_type1" value="0"/>
		<input type="hidden" name="type_dog1" id="type_dog1" value="0"/>
		<input type="hidden" name="type_equines1" id="type_equines1" value="0"/>
		<!-- <input type="hidden" name="from_date1" id="from_date1" value="0"/>
		<input type="hidden" name="to_date1" id="to_date1" value="0"/> -->
		
		<input type="hidden" name="hdtypeofdog1" id="hdtypeofdog1" value="0"/> 
		<input type="hidden" name="hdtypeofequ1" id="hdtypeofequ1" value="0"/>
		<input type="hidden" name="animal_status1" id="animal_status1" value=""/>
		
		<input type="hidden" name="comd_sus_no1" id="comd_sus_no1" value="0"/>
		<input type="hidden" name="cont_corps1" id="cont_corps1" value="0"/>
		<input type="hidden" name="cont_div1" id="cont_div1" value="0"/>
		<input type="hidden" name="cont_bde1" id="cont_bde1" value="0"/>
		<input type="hidden" name="sus_no1" id="sus_no1" value="0"/>
		<input type="hidden" name="unit_name1" id="unit_name1" value="0"/>
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
		
		//alert('${getsplzList}');
		
		var j = '${getsplzList}';
		for ( var i = 0; i < j.length ;i++) {
	 			
	 			$("#divContainer").append ( "<label>" + j[i].label + " </label><input type='radio' onchange=Category_Name(this); name='label' id='" + j[i].label + "' value='" + j[i].codevalue + "'  /> &nbsp;&nbsp " );
			} 
		
		
		$("#anml_type").val('${anml_type1}');
		$("#type_dog").val('${type_dog1}');
		$("#type_equines").val('${type_equines1}');
		/* $("#from_date").val('${from_date1}');
		$("#to_date").val('${to_date1}'); */
		/* $("#comd_sus_no").val('${comd_sus_no1}');
		$("#comd_unit_name").val('${comd_unit_name1}'); */
		$("#hdtypeofdog").val('${hdtypeofdog1}');
		$("#hdtypeofequ").val('${hdtypeofequ1}');
		/* if('${animal_status}' != ''){
			$("#animal_status").val('${animal_status}');
		} */
		
		$("#sus_no").val('${sus_no1}');
	    $("#unit_name").val('${unit_name1}');
		
			if('${anml_type1}' == "" & '${list.size()}' != 0)
			{
				$("div#divwatermark").val('').addClass('watermarked');								
				watermarkreport(); 
				document.getElementById("exportId").disabled = false;
				$('#printDiv').show();
				$('#reportdog').hide();
				$('#reportequ').hide(); 
			}
			else if('${anml_type1}' == "Army Dog"  & '${list.size()}' != 0)
			{
				$("div#divwatermark").val('').addClass('watermarked');								
				watermarkreport(); 
				document.getElementById("exportId").disabled = false;
				$('#printDiv').show();
				$('#reportdog').show();               
				$('#reportequ').hide();
				$('#typedog').show(); 
                $('#tyequ').hide();
			}
			else if($("#anml_type").val() == "Army Equines"  & '${list.size()}' != 0)
			{
				$("div#divwatermark").val('').addClass('watermarked');								
				watermarkreport(); 
				document.getElementById("exportId").disabled = false;
				$('#printDiv').show();
				$('#tyequ').show();
				$('#reportequ').show(); 
				$('#reportdog').hide();
				$('#typedog').hide(); 
			}
			else{
				$('#reportdog').hide();
				$('#reportequ').hide();
				$('#typedog').hide(); 
                $('#tyequ').hide();
			}
		
				if('${anml_type1}' == "" & '${list.size()}' == ''){
					$('#alltable').hide();
				}
				
			 if('${anml_type1}' == "Army Dog"  & '${list.size()}' == 0){
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
				
				if('${roleAccess}' == 'Unit')
				{
					$("#comd_sus_no").attr("disabled", true);
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
					$("#comd_sus_no").attr("disabled", true);
					$("#cont_corps").attr("disabled", true); 
					$("#cont_div").attr("disabled", true); 
					$("#cont_bde").attr("disabled", true);
				}
				if('${roleSubAccess}' == 'Division')
				{
					$("#comd_sus_no").attr("disabled", true);
					$("#cont_corps").attr("disabled", true); 
					$("#cont_div").attr("disabled", true); 
					if('${cont_div1}' != ""){
			   	    	getCONTBde('${cont_div1}');
			   	    }
				}
				if('${roleSubAccess}' == 'Corps')
				{
					$("#comd_sus_no").attr("disabled", true);
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
					$("#comd_sus_no").attr("disabled", true);
					if('${comd_sus_no1}' != ""){
				    	$("#comd_sus_no").val('${comd_sus_no1}');
				    	getCONTCorps('${comd_sus_no1}');
			    	}
			    	if('${cont_corps1}' != ""){
			    		 getCONTDiv('${cont_corps1}');
			    	}
				    if('${cont_div1}' != ""){
				    	getCONTBde('${cont_div1}');
				    }
				}
				
				if('${roleAccess}' == 'MISO' || '${roleAccess}' == 'HeadQuarter')
				{
					if('${comd_sus_no1}' != ""){
				    	$("#comd_sus_no").val('${comd_sus_no1}');
				    	getCONTCorps('${comd_sus_no1}');
			    	}
			    	if('${cont_corps1}' != ""){
			    		 getCONTDiv('${cont_corps1}');
			    	}
				    if('${cont_div1}' != ""){
				    	getCONTBde('${cont_div1}');
				    }
				}
				
				
				var select = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
		   	   	$('select#comd_sus_no').change(function() {
		   		   		var fcode = this.value;
		   		   		
		   		   	$("#hd_comd_sus_no").val($("#comd_sus_no").val());
		   		   	
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
		   		   	$("#hd_cont_corps").val($("#cont_corps").val());
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
		   		   	$("#hd_cont_div").val($("#cont_div").val());
		   		   		var fcode = this.value;    	   	
		   		   		if(fcode == "0"){
		   		 		$("select#cont_bde").html(select)
		   		   	}else{
		   		   		$("select#cont_bde").html(select)
		   			   		getCONTBde(fcode);
		   		   	}
		   			});
		   		   	
		   		$("#hd_comd_sus_no").val($("select#comd_sus_no").val());
		   		$("#hd_cont_corps").val($("select#cont_corps").val());
		   		$("#hd_cont_div").val($("select#cont_div").val());
		   		$("#hd_cont_bde").val($("select#cont_bde").val());
		   		$("#hd_unit_name").val($("#unit_name").val());
		   		   	
		   		$('select#cont_bde').change(function() {  	
		   		$("#hd_cont_bde").val($("#cont_bde").val());
		   		});
				
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
	   	   	
	  
	
	function printDiv() 
	{		
		var anml_type = $("#anml_type").val();
	/* 	if(anml_type==""  & '${list.size()}' != 0){
			var printLbl = ["SUS NO :","UNIT NAME :","COMD SUS NO :","COMD :","TYPE OF ANIMAL :","FROM DATE :","TO DATE :"];
		 	var date = new Date();
		 	var formattedtoday = date.getDate() + '-' + (date.getMonth() + 1) + '-' + date.getFullYear();		 	 	
			var printVal = [document.getElementById('sus_no').value,document.getElementById('unit_name').value,document.getElementById('comd_sus_no').value,document.getElementById('comd_unit_name').value,
				document.getElementById('anml_type').value,document.getElementById('from_date').value,document.getElementById('to_date').value]; 	
			printDivOptimize('reportall','Details of Animals',printLbl,printVal,"");
			
		}
		else  */
			if(anml_type=="Army Dog"  & '${list.size()}' != 0){              
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
		
		if ($("select#anml_type").val() == "") {
			alert("Please Select Animal Type");
			$("select#anml_type").focus();
			return false;
		}
		
		var anml_type = $("#anml_type").val();
		if(anml_type == "Army Dog")
		{
			
			if ($("#type_dog").val() == "") {
				alert("Please Select Specialization");
				$("#type_dog").focus();
				return false;
			}
			
		}
		else
		{
			if ($("#type_equines").val() == "") {
				alert("Please Select Type of Equines");
				$("#type_equines").focus();
				return false;
			}
		}
	    
		
	    $("#anml_type1").val($("#anml_type").val());
	    $("#type_dog1").val($("#type_dog").val());
	    $("#type_equines1").val($("#type_equines").val());
	   /*  $("#from_date1").val($("#from_date").val());
	    $("#to_date1").val($("#to_date").val()); */
	    $("#hdtypeofdog1").val($("#hdtypeofdog").val());
	    $("#hdtypeofequ1").val($("#hdtypeofequ").val());
	   /*  $("#animal_status1").val($("#animal_status").val()); */
	    
	    $("#comd_sus_no1").val($("#comd_sus_no").val());
	    $("#cont_corps1").val($("#cont_corps").val());
	    $("#cont_div1").val($("#cont_div").val());
	    $("#cont_bde1").val($("#cont_bde").val());
	    $("#sus_no1").val($("#sus_no").val());
	    $("#unit_name1").val($("#unit_name").val());
	    
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
	
	
	function showCheckboxes() {
		var checkboxes = document.getElementById("checkboxes");
		$("#checkboxes").toggle();
		$("#dog_search").val('');
		$('.doglist').each(function() {
			$(this).show()
		})
	}
	
	$("#dog_search").keyup(function() {
		var re = new RegExp($(this).val(), "i")
		var search = $(this).val();
		var searchL = search.length;
		$('.doglist').each(function() {
			var text = $(this).text(),
				matches = !!text.match(re);
			if (text.trim().substring(0,searchL).toUpperCase() == search.trim().toUpperCase()) {
				$(this).toggle(true);
			}
			else{
				$(this).toggle(false);
			}
		})
	});
	
	function callsetall(){
		var chkclk=$('#nSelAll').prop('checked');
		if (chkclk) {
			$('.nrCheckBox').prop('checked',true);	
			
			var subjectvar2 = $('input[name="multisub_dog"]:checkbox:checked').map(function() {
				return this.value;
			}).get();
			
			var subject2 = subjectvar2.join(",");
			 $("#type_dog").val(subject2);
			 
		} else {
			$('.nrCheckBox').prop('checked',false);
		}
		//findselected();
	}
	
	
	function Table_Name(){
		
		var subjectvar2 = $('input[name="multisub_dog"]:checkbox:checked').map(function() {
			return this.value;
		}).get();
		
		var subject2 = subjectvar2.join(",");
		 $("#type_dog").val(subject2);
		
	}
	
	
	
	function showCheckboxes_Equines() {
		var checkboxes_equines = document.getElementById("checkboxes");
		$("#checkboxes_equines").toggle();
		$("#equines_search").val('');
		$('.equineslist').each(function() {
			$(this).show()
		})
	}
	
	
	$("#equines_search").keyup(function() {
		var re = new RegExp($(this).val(), "i")
		var search = $(this).val();
		var searchL = search.length;
		$('.equineslist').each(function() {
			var text = $(this).text(),
				matches = !!text.match(re);
			if (text.trim().substring(0,searchL).toUpperCase() == search.trim().toUpperCase()) {
				$(this).toggle(true);
			}
			else{
				$(this).toggle(false);
			}
		})
	});
	
	
	function callsetall_equines(){
		var chkclk=$('#nSelAll_equines').prop('checked');
		if (chkclk) {
			$('.nrCheckBox_equines').prop('checked',true);	
			
			var subjectvar3 = $('input[name="multisub_equines"]:checkbox:checked').map(function() {
				return this.value;
			}).get();
			
			var subject3 = subjectvar3.join(",");
			 $("#type_equines").val(subject3);
			
		} else {
			$('.nrCheckBox_equines').prop('checked',false);
		}
		//findselected();
	}
	
	
	
function Table_Name_equines(){
	
		var subjectvar3 = $('input[name="multisub_equines"]:checkbox:checked').map(function() {
			return this.value;
		}).get();
		
		var subject3 = subjectvar3.join(",");
		 $("#type_equines").val(subject3);
		
	}
	
	
function fnExcelReport() {	
	
	var comd_sus_no_txt = $("#comd_sus_no option:selected").text();
 	var cont_corps_txt=$("#cont_corps option:selected").text();
 	var cont_div_txt=$("#cont_div option:selected").text();
 	var cont_bde_txt=$("#cont_bde option:selected").text();
 	var anml_type_txt=$("#anml_type option:selected").text();
 	
 	
	var comd_sus_no=$("#comd_sus_no").val();
 	var cont_corps=$("#cont_corps").val();
 	var cont_div=$("#cont_div").val();
 	var cont_bde=$("#cont_bde").val();
 	var unit_name=$("#unit_name").val();
 	var sus_no=$("#sus_no").val();
 	var anml_type=$("#anml_type").val();
 	var type_dog=$("#type_dog").val();
 	var type_equines=$("#type_equines").val();
 	
 	
	
 	if(comd_sus_no_txt == "--Select--") {
		cont_comd_txt = "";
	}
	if(cont_corps_txt == "--Select--") {
		cont_corps_txt = "";		
	}
	if(cont_div_txt == "--Select--") {
		cont_div_txt = "";
	}
	if(cont_bde_txt == "--Select--") {
		cont_bde_txt = "";
	}
	
	if(anml_type_txt == "--Select--") {
		anml_type_txt = "";
	}
	
	
 	$("#comd_sus_no_txt").val(comd_sus_no_txt);
	$("#cont_corps_txt").val(cont_corps_txt);
	$("#cont_div_txt").val(cont_div_txt);
	$("#cont_bde_txt").val(cont_bde_txt);
	$("#anml_type_txt").val(anml_type_txt);
	
	$("#comd_sus_no_ex").val(comd_sus_no);
	$("#cont_corps_ex").val(cont_corps);
	$("#cont_div_ex").val(cont_div);
	$("#cont_bde_ex").val(cont_bde);
	$("#unit_name_ex").val(unit_name);
	$("#sus_no_ex").val(sus_no);
	$("#anml_type_ex").val(anml_type);
	$("#type_dog_ex").val(type_dog);
	$("#type_equines_ex").val(type_equines);
	
	document.getElementById('typeReport1').value = 'excelL';
	document.getElementById('ExcelForm').submit();
}

</Script>

<c:url value="Excel_data_dog" var="excelUrl" />
<form:form action="${excelUrl}" method="post" id="ExcelForm" name="ExcelForm" modelAttribute="typeReport1">
	 <input type="hidden" name="typeReport1" id="typeReport1" value="0" />
	  <input type="hidden" name="comd_sus_no_ex" id="comd_sus_no_ex"  value="0">
	   <input type="hidden" name="cont_corps_ex" id="cont_corps_ex" value="0">
	   <input type="hidden" name="cont_div_ex" id="cont_div_ex" value="0">
	   <input type="hidden" name="cont_bde_ex" id="cont_bde_ex" value="0">
	   <input type="hidden" name="comd_sus_no_txt" id="comd_sus_no_txt" >
	   <input type="hidden" name="cont_corps_txt" id="cont_corps_txt">
	   <input type="hidden" name="cont_div_txt" id="cont_div_txt">
	   <input type="hidden" name="cont_bde_txt" id="cont_bde_txt">
	   <input type="hidden" name="unit_name_ex" id="unit_name_ex">
	   <input type="hidden" name="sus_no_ex" id="sus_no_ex">
	    <input type="hidden" name="anml_type_ex" id="anml_type_ex"  value="0">
	    <input type="hidden" name="anml_type_txt" id="anml_type_txt" value="0">
	    <input type="hidden" name="type_dog_ex" id="type_dog_ex">
	    <input type="hidden" name="type_equines_ex" id="type_equines_ex">
	    
</form:form> 