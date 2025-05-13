	
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

		
<div class="animated fadeIn"  style="display: block;">
	<div class="">
		<div class="col 12" align="center">
			<div class="card">
				<div class="card-header">
					<strong style="text-decoration: underline;">RESTRICTED</strong>
				</div>
				<div class="card-header">
					<div class="col-md-2">
						<img src="js/miso/images/indianarmy_smrm5aaa.jpg" style="height: 85px;">
					</div>
					<div class="col-md-8">
						<h3> SUMMARY OF AGE OF ANIMALS </h3>
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
	                				<label for="text-input" class=" form-control-label" ><strong style="color: red;">*</strong> SUS No</label>
	              				</div>
	              				<div class="col-12 col-md-8">
	                				<input type="text" id="sus_no" name="sus_no"  class="form-control autocomplete" autocomplete="off" value="${sus_no1}" maxlength="8">
								</div>
							</div>
	  					</div>
	  					<div class="col-md-6">
	 						<div class="row form-group">  								
	               				<div class="col col-md-4">
	                				<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Unit Name</label>
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
					</div>
				</div>
				<div class="form-control card-footer" align="center">
		           <i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm" value="Search" onclick="Search();">
		           <a href="sum_of_anml_untwise" type="reset" class="btn btn-success btn-sm"> Clear </a>  
		           <input type="button" id="printId" class="btn btn-primary btn-sm btn_report" value="Print" onclick="printDiv();" disabled>
				</div> 
			</div>
		</div>
	</div>
</div>

	 <div class="col 12" id="ArmyDogTable" style="display: none;" >
	 <div id="divShow" style="display: block;">
	 </div>
	                  
      <div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
		<span id="ip"></span>					 
			<table id="AttributeReport" class="table no-margin table-striped  table-hover  table-bordered report_print">
				<thead style="background-color: #9c27b0; color: white;text-align: center;">
					<tr>
						<th width="5%">Ser No</th>
						<th width="25%">Type of Dog</th>
						<th width="5%">Below One Year</th>
						<th width="5%">1 Year</th>
						<th width="5%">2 Year</th>
						<th width="5%">3 Year</th>
						<th width="5%">4 Year</th>
						<th width="5%">5 Year</th>
						<th width="5%">6 Year</th>
						<th width="5%">7 Year</th>
						<th width="5%">8 Year</th>
						<th width="5%">9 Year</th>
						<th width="5%">10 Year</th>
						<th width="5%">11 Year</th>
						<th width="5%">Above 12 Year</th>
						<th width="5%">Total</th>
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
							<td width="25%">${item1[1]}</td>
							<td width="5%" style="text-align: center;">${item1[2]}</td>							
							<td width="5%" style="text-align: center;">${item1[3]}</td>
							<td width="5%" style="text-align: center;">${item1[4]}</td>
							<td width="5%" style="text-align: center;">${item1[5]}</td>  
							<td width="5%" style="text-align: center;">${item1[6]}</td>
							<td width="5%" style="text-align: center;">${item1[7]}</td>							
							<td width="5%" style="text-align: center;">${item1[8]}</td>
							<td width="5%" style="text-align: center;">${item1[9]}</td>
							<td width="5%" style="text-align: center;">${item1[10]}</td> 
							<td width="5%" style="text-align: center;">${item1[11]}</td>
							<td width="5%" style="text-align: center;">${item1[12]}</td>
							<td width="5%" style="text-align: center;">${item1[13]}</td>
							<td width="5%" style="text-align: center;">${item1[14]}</td> 
							<td width="5%" style="text-align: center;">${item1[15]}</td>
						</tr>
						 </c:forEach>
						 <tr id = "total">
							<td width="30%" colspan="2"><B>Total</B></td>
							<td width="5%" align='center' ><B>${c1}</B></td>
							<td width="5%" align='center' ><B>${c2}</B></td>
							<td width="5%" align="center" ><B>${c3}</B></td>
							<td width="5%" align='center' ><B>${c4}</B></td>
							<td width="5%" align='center' ><B>${c5}</B></td>
							<td width="5%" align='center' ><B>${c6}</B></td>
							<td width="5%" align="center" ><B>${c7}</B></td>
							<td width="5%" align='center' ><B>${c8}</B></td>
							<td width="5%" align='center' ><B>${c9}</B></td>
							<td width="5%" align='center' ><B>${c10}</B></td>
							<td width="5%" align='center' ><B>${c11}</B></td>
							<td width="5%" align='center' ><B>${c12}</B></td>
							<td width="5%" align='center' ><B>${c13}</B></td>  
						 	<td width="5%" align='center' ><B>${c14}</B></td>  
						</tr>	
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
	
<div class="col 12" id="ArmyEquinesTable" style="display: none;" >
	 <div id="divShow" style="display: block;">
	 </div>
	                  
      <div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
		<span id="ip"></span>					 
			<table id="AttributeReport2" class="table no-margin table-striped  table-hover  table-bordered report_print">
				<thead style="background-color: #9c27b0; color: white;text-align: center;">
					<tr>
						<th style="width: 3%">Ser No</th>
						<th style="width: 25%">Type of Equines</th>
						<th style="width: 3%">Below One Year</th>
						<th style="width: 3%">1 Year</th>
						<th style="width: 3%">2 Year</th>
						<th style="width: 3%">3 Year</th>
						<th style="width: 3%">4 Year</th>
						<th style="width: 3%">5 Year</th>
						<th style="width: 3%">6 Year</th>
						<th style="width: 3%">7 Year</th>
						<th style="width: 3%">8 Year</th>
						<th style="width: 3%">9 Year</th>
						<th style="width: 3%">10 Year</th>
						<th style="width: 3%">11 Year</th>
						<th style="width: 3%">12 Year</th>
						<th style="width: 3%">13 Year</th>
						<th style="width: 3%">14 Year</th>
						<th style="width: 3%">15 Year</th>
						<th style="width: 3%">16 Year</th>
						<th style="width: 3%">17 Year</th>
						<th style="width: 3%">18 Year</th>
						<th style="width: 3%">19 Year</th>
						<th style="width: 3%">20 Year</th>
						<th style="width: 3%">21 Year</th>
						<th style="width: 3%">Above 22 Year</th>
						<th style="width: 3%">Total</th>
					</tr>							
				</thead> 
				<tbody>
				
				 <c:if test="${listequ.size() == 0}" >
					<tr>
						<td align="center" style="color: red;"> Data Not Available </td>
					</tr>
				</c:if>
				
				<c:forEach var="item1" items="${listequ}" varStatus="num" >
						<tr>
							<td style="text-align: center;width: 3%">${num.index+1}</td> 
							<td style="width: 25%">${item1[1]}</td>
							<td style="text-align: center;width: 3%">${item1[2]}</td>							
							<td style="text-align: center;width: 3%">${item1[3]}</td>
							<td style="text-align: center;width: 3%">${item1[4]}</td>
							<td style="text-align: center;width: 3%">${item1[5]}</td>  
							<td style="text-align: center;width: 3%">${item1[6]}</td>
							<td style="text-align: center;width: 3%">${item1[7]}</td>							
							<td style="text-align: center;width: 3%">${item1[8]}</td>
							<td style="text-align: center;width: 3%">${item1[9]}</td>
							<td style="text-align: center;width: 3%">${item1[10]}</td> 
							<td style="text-align: center;width: 3%">${item1[11]}</td>
							<td style="text-align: center;width: 3%">${item1[12]}</td>
							<td style="text-align: center;width: 3%">${item1[13]}</td>
							<td style="text-align: center;width: 3%">${item1[14]}</td> 
							<td style="text-align: center;width: 3%">${item1[15]}</td>							
							<td style="text-align: center;width: 3%">${item1[16]}</td>
							<td style="text-align: center;width: 3%">${item1[17]}</td>
							<td style="text-align: center;width: 3%">${item1[18]}</td> 
							<td style="text-align: center;width: 3%">${item1[19]}</td>
							<td style="text-align: center;width: 3%">${item1[20]}</td>
							<td style="text-align: center;width: 3%">${item1[21]}</td>
							<td style="text-align: center;width: 3%">${item1[22]}</td> 
							<td style="text-align: center;width: 3%">${item1[23]}</td>  
							<td style="text-align: center;width: 3%">${item1[24]}</td>  
							<td style="text-align: center;width: 3%">${item1[25]}</td>
						</tr>
						 </c:forEach>
						 
						 <tr id = "total1">
							<td colspan="2" style="width: 28%"><B>Total</B></td>
							<td align='center' style="width: 3%"><B>${c1}</B></td>
							<td align='center' style="width: 3%"><B>${c2}</B></td>
							<td align="center" style="width: 3%"><B>${c3}</B></td>
							<td align='center' style="width: 3%"><B>${c4}</B></td>
							<td align='center' style="width: 3%"><B>${c5}</B></td>
							<td align='center' style="width: 3%"><B>${c6}</B></td>
							<td align="center" style="width: 3%"><B>${c7}</B></td>
							<td align='center' style="width: 3%" ><B>${c8}</B></td>
							<td align='center' style="width: 3%"><B>${c9}</B></td>
							<td align='center' style="width: 3%"><B>${c10}</B></td>
							<td align='center' style="width: 3%"><B>${c11}</B></td>
							<td align='center' style="width: 3%"><B>${c12}</B></td>
							<td align='center' style="width: 3%"><B>${c13}</B></td>  
						 	<td align='center' style="width: 3%"><B>${c14}</B></td>  
						 	<td align='center' style="width: 3%"><B>${c15}</B></td>   
							<td align='center' style="width: 3%"><B>${c16}</B></td>
							<td align="center" style="width: 3%"><B>${c17}</B></td>
							<td align='center' style="width: 3%"><B>${c18}</B></td>
							<td align='center' style="width: 3%"><B>${c19}</B></td>
							<td align='center' style="width: 3%"><B>${c20}</B></td>
							<td align='center' style="width: 3%"><B>${c21}</B></td>
							<td align='center' style="width: 3%"><B>${c22}</B></td>
							<td align='center' style="width: 3%"><B>${c23}</B></td> 
						    <td align='center' style="width: 3%"><B>${c24}</B></td>
						</tr>	 
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

<script>
function printDiv() 
{
	var anml_type = $("#anml_type").val();
	if(anml_type=="Army Dog"){
		var printLbl = ["SUS No :","Unit Name :","Animal Type :","Date :"];
	 	var date = new Date();
	 	var formattedtoday = date.getDate() + '-' + (date.getMonth() + 1) + '-' + date.getFullYear();		
	 	var printVal = [document.getElementById('sus_no').value,document.getElementById('unit_name').value,document.getElementById('anml_type').value,formattedtoday]; 	
	 	printDivOptimize('ArmyDogTable','Summary of Age of Animals',printLbl,printVal,"");
	}
	else{
		var printLbl = ["SUS No :","Unit Name :","Animal Type :","Date :"];
	 	var date = new Date();
	 	var formattedtoday = date.getDate() + '-' + (date.getMonth() + 1) + '-' + date.getFullYear();		
	 	var printVal = [document.getElementById('sus_no').value,document.getElementById('unit_name').value,document.getElementById('anml_type').value,formattedtoday]; 	
	 	printDivOptimize('ArmyEquinesTable','Summary of Age of Animals',printLbl,printVal,"");
	   }   		
 	}
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
     	
	  });
</script>



 <Script>
		function Search(){
			 if ($("input#sus_no").val() == "") {
				alert("Please Select SUS No");
				return false;
			}
			else if ($("input#unit_name").val() == "") {
				alert("Please Select Unit Name");
				return false;
				}
			else if ($("select#anml_type").val() == "") {
				alert("Please Select Animal Type");
				return false;
			} 
			
			var anml_type = $("#anml_type").val();
			if(anml_type == "Army Dog")
			{
				$('#ArmyDogTable').show();               
				$('#ArmyEquinesTable').hide();
			    $("#sus_no1").val($("#sus_no").val());
			    $("#anml_type1").val($("#anml_type").val());
			    $("#unit_name1").val($("#unit_name").val());
				document.getElementById('searchForm').submit();
			}
			
			else{
				
				$('#ArmyDogTable').hide();               
				$('#ArmyEquinesTable').show();
				$("#sus_no2").val($("#sus_no").val());
			    $("#anml_type2").val($("#anml_type").val());
			    $("#unit_name2").val($("#unit_name").val());
				document.getElementById('searchFormEqu').submit();
			}
		}
		
		jQuery(document).ready(function() {
			 if('${anml_type1}' == "Army Dog")
			{
				$("#anml_type").val('${anml_type1}');
				$("div#divwatermark").val('').addClass('watermarked');								
				watermarkreport(); 
				document.getElementById("printId").disabled = false;
				$('#ArmyDogTable').show();               
				$('#ArmyEquinesTable').hide();
				$('#printDiv').show();
			    $("#sus_no").val('${sus_no1}');
			    $("#unit_name").val('${unit_name1}');
			}
			 else  if('${anml_type2}' == "Army Equines") {
					$("#anml_type").val('${anml_type2}');
				    $("div#divwatermark").val('').addClass('watermarked');								
					watermarkreport(); 
					document.getElementById("printId").disabled = false;
					$("#sus_no").val('${sus_no2}');
					$("#unit_name").val('${unit_name2}');
					$('#ArmyDogTable').hide();               
					$('#ArmyEquinesTable').show();
					$('#printDiv').show();
					$("#sus_no").val('${sus_no2}');
					$("#unit_name").val('${unit_name2}');
			 }
			 
			 if('${list.size()}' == 0){
				 $("#total").hide();
				 $("#printId").hide();
			}
			 else if('${list.size()}' != 0){
				 $("#total").show();
			} 
			if('${listequ.size()}' == 0){
				 $("#total1").hide();
				 $("#printId").hide();
			}
			 else if('${listequ.size()}' != 0){
				 $("#total1").show();
			} 
			
			if( '${list.size()}' != 0 || '${listequ.size()}' != 0){
				$("#printId").show();
			}
		});
</Script>
    <c:url value="dog_sum_list" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="sus_no1">
		<input type="hidden" name="sus_no1" id="sus_no1" value="0"/>
		<input type="hidden" name="anml_type1" id="anml_type1" value="0"/>
		<input type="hidden" name="unit_name1" id="unit_name1" value="0"/>
	</form:form> 
	
	<c:url value="equ_sum_list" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchFormEqu" name="searchFormEqu" modelAttribute="sus_no2">
		<input type="hidden" name="sus_no2" id="sus_no2" value="0"/>
		<input type="hidden" name="anml_type2" id="anml_type2" value="0"/>
		<input type="hidden" name="unit_name2" id="unit_name2" value="0"/>
	</form:form> 