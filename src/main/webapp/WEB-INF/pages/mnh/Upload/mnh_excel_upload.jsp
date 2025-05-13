<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="Stylesheet" href="js/Calender/jquery-ui.css" >
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<!-- <link rel="stylesheet" href="js/common/mnh_css.css"> -->

<script src="js/upload_xls/xlsx.core.min.js"> </script>
<script src="js/upload_xls/xls.min.js"> </script>
<script src="js/upload_xls/jquery.1.10.js"> </script>    

<script type="text/javascript" src="js/Calender/jquery-ui.js"></script>
<script type="text/javascript" src="js/common/commonmethod.js"></script>
<script type="text/javascript" charset="utf8" src="js/JS_CSS/jquery.dataTables.min.css"></script> 
<script src="js/JS_CSS/jquery.dataTables.js"></script>


<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script> 
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 


<%
   String nPara=request.getParameter("Para");
    
%>
<style>

.scrollit {
    overflow:scroll;
    height:300px;
    width: 100%;
}
.scrollit table td input{
   border: 0;
   background: transparent;
   font-size: 12px;
   height: 20px;
}
</style>

<form:form id="exportExcelData" action="exportExcelAction?${_csrf.parameterName}=${_csrf.token}" method="POST" enctype="multipart/form-data">
   <div class="">
      <div class="container" align="center">
          <div class="card">
              <div class="card-header mnh-card-header">
		                  <b>DATA UPLOAD(EXCEL)</b>
		                  <h6>
								<span style="font-size: 12px; color: red">(To be entered by Hospital)</span>
						    </h6>
		      </div> 
		       
               <div class="card-body card-block">   
               		<!-- <div class="col-md-12 row form-group" >
		                 <div class="col-md-2" style="text-align: left;">
	                 		  <label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>File Type</label>
	               		 </div>	               		 
	               		 <div class="col-md-4">
	             			  <select name="unit_name" id="unit_name" class="form-control-sm form-control">
	             			  <option value="01">Access File</option>
	             			  <option value="02">Excel</option>
	                  		  <option value="03">File</option>
	                  		</select>
	               		 </div>	                		 
		            </div> -->  
		            					
  					<div class="col-md-12">
		                 <div class="col-md-2" style="text-align: left;">
	                 		  <label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Hospital Name</label>
	               		 </div>	               		 
	               		 <div class="col-md-6">
	             			  <input name="unit_name" id="unit_name1" placeholder="Search..." class="form-control-sm form-control">
	               		 </div>	 
	               		  <div class="col-md-2" style="text-align: left;">
	                 		  <label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>SUS No</label>
	               		 </div>	               		 
	               		 <div class="col-md-2">
	             			  <input name="sus_no" id="sus_no1" placeholder="Search..." class="form-control-sm form-control">
	               		 </div>	                     		 
		            </div> 
		            
	    					     <div class="col-md-12">	
                					 <div class="col-md-2" style="text-align: left;">
                  						<label class=" form-control-label"><strong style="color: red;">*</strong> Month</label>
                					</div>
                					 <div class="col-md-4">
	                  					     <select name="month_select" id="month_select" class="form-control-sm form-control">
	                  							<option value="01">January</option>
	                  							<option value="02">February</option>
	                  							<option value="03">March</option>
	                  							<option value="04">April</option>
	                  							<option value="05">May</option>
	                  							<option value="06">June</option>
	                  							<option value="07">July</option>
	                  							<option value="08">August</option>
	                  							<option value="09">September</option>
	                  							<option value="10">October</option>
	                  							<option value="11">November</option>
	                  							<option value="12">December</option>
	                  						</select>
                					</div>
                                        <div class="col-md-2" style="text-align: left;">
	                  						<label  class=" form-control-label" ><strong style="color: red;">*</strong> Year </label>
	                					</div>
	                			         <div class="col-md-4">
	                						<input  id="year_select" name="year_select" class="form-control" autocomplete="off" >
	  								</div> 
	  						</div>
	  						<div class="col-md-12">
		                 <div class="col-md-2" style="text-align: left;">
	                 		  <label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Choose File</label>
	               		 </div>	               		 
	               		 <div class="col-md-4">
	             			<input type="file" id="excelfile" name="excelfile" accept="application/vnd.ms-excel, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" class="form-control-sm form-control"/>
	               		 </div>	               		 
		            </div>      
	  			</div>
	  					
	  					
	  			<!-- <div class="card-body card-block">
	    		    <div id="result"></div>
		    		<div>
		    		     <table id="exceltable" style=" width: 100%;font-size: 12px;">
					     </table> 
		    		</div>
	    		</div> -->
	    		
	    		
	    		<div id="exltb" class="card-body card-block" style="display: none;padding-right:0;">
		 
				<div class="col-md-12 row form-group" style="margin-top: -10px;padding-right: 0;" >
					<div class="scrollit" align="center" >
					<table id="exceltable" class="table no-margin table-striped  table-hover  table-bordered" >
						<thead style="background-color: #9c27b0; color: white;">
							
						</thead>
						<tbody>
						</tbody>
					</table>
					</div>
				</div>
				
		</div>
	  		
		      <div class="card-footer" align="center">
		      		<!-- <input type="hidden"  id="count" name="count">
		      		<input type="hidden"  id="countrow" name="countrow"> -->
		      		<!-- <input type="button" id="viewfile" value="Export To Table" onclick="" />  -->
			     <input type="button" class="btn btn-primary btn-sm" value="Clear"  onclick="btn_clc();" /> 
		         <input type="button" id="btn_serach" class="btn btn-success btn-sm" value="Save" onclick="file_browser()" />      		         
              </div>
                           
          </div>
      </div>     
   </div>
</form:form>





<script>
var key = "${_csrf.parameterName}";
var value = "${_csrf.token}";


jQuery(function() {
	// Source SUS No

	jQuery("#sus_no1").keypress(function(){
		var sus_no = this.value;
			 var susNoAuto=jQuery("#sus_no1");
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
				        	var dataCountry1 = susval.join("|");
			            var myResponse = []; 
			            var autoTextVal=susNoAuto.val();
						jQuery.each(dataCountry1.toString().split("|"), function(i,e){
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
			        	  alert("Please Enter Approved SUS No.");
			        	  document.getElementById("sus_no1").value="";
			        	  susNoAuto.val("");	        	  
			        	  susNoAuto.focus();
			        	  return false;	             
			          }   	         
			      }, 
			      select: function( event, ui ) {
			    	  var unit_sus_no = ui.item.value;
			    	 	
  	 			 	$.post("getActiveUnitNameFromSusNo?"+key+"="+value, {sus_no:unit_sus_no}).done(function(j) {				
  	 			 		if(j == ""){
  	 			      	 	alert("Please Enter Approved Unit SUS No.");
  			        	  	document.getElementById("unit_name1").value="";
  			        	  	susNoAuto.val("");
  			        	  	susNoAuto.focus();
  	 			      	}else{
	    	 	   	        	var length = j.length-1;
	    	    				var enc = j[length].substring(0,16);
	    	    				$("#unit_name1").val(dec(enc,j[0]));	
	    	 	   	        		
  	 	   	        	}
  	 				}).fail(function(xhr, textStatus, errorThrown) {
  	 				});
			      } 	     
			});	
	});
	// End
	
	// Source Unit Name

    jQuery("#unit_name1").keypress(function(){
 			var unit_name = this.value;
				 var susNoAuto=jQuery("#unit_name1");
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
					        	var dataCountry1 = susval.join("|");
				            var myResponse = []; 
				            var autoTextVal=susNoAuto.val();
							jQuery.each(dataCountry1.toString().split("|"), function(i,e){
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
				        	  document.getElementById("unit_name1").value="";
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
				 				         jQuery("#sus_no1").val(dec(enc,j[0]));	
									}).fail(function(xhr, textStatus, errorThrown) {
							});
					} 	     
				}); 			
 			});

});

</script>


<script>

/* function ExportToTable() { */
	
	$("input#excelfile").on("change", function (){
		
	$("#exltb").show();
    var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.xlsx|.xls)$/;  
    /*Checks whether the file is a valid excel file*/  
    if (regex.test($("#excelfile").val().toLowerCase())) {  
        var xlsxflag = false; /*Flag for checking whether excel is .xls format or .xlsx format*/  
        if ($("#excelfile").val().toLowerCase().indexOf(".xlsx") > 0) {  
            xlsxflag = true;  
        }  
        /*Checks whether the browser supports HTML5*/  
        if (typeof (FileReader) != "undefined") {  
            var reader = new FileReader();  
            reader.onload = function (e) {  
                var data = e.target.result;  
                /*Converts the excel data in to object*/  
                if (xlsxflag) {  
                    var workbook = XLSX.read(data, { type: 'binary' });  
                }  
                else {  
                    var workbook = XLS.read(data, { type: 'binary' });  
                }  
                /*Gets all the sheetnames of excel in to a variable*/  
                var sheet_name_list = workbook.SheetNames;  
 
                var cnt = 0; /*This is used for restricting the script to consider only first sheet of excel*/  
                sheet_name_list.forEach(function (y) { /*Iterate through all sheets*/  
                    /*Convert the cell value to Json*/  
                    if (xlsxflag) {  
                        var exceljson = XLSX.utils.sheet_to_json(workbook.Sheets[y]);
                    }
                    else {  
                        var exceljson = XLS.utils.sheet_to_row_object_array(workbook.Sheets[y]);  
                    }  
                    if (exceljson.length > 0 && cnt == 0) {  
                        BindTable(exceljson, '#exceltable');  
                        cnt++;  
                    }  
                });  
                $('#exceltable').show();  
            }  
            if (xlsxflag) {/*If excel file is .xlsx extension than creates a Array Buffer from excel*/  
                reader.readAsArrayBuffer($("#excelfile")[0].files[0]);  
            }  
            
            else {  
                reader.readAsBinaryString($("#excelfile")[0].files[0]);  
            }  
        }  
        else {  
            alert("Sorry! Your browser does not support HTML5!");  
        }  
    }  
    else {  
        alert("Please upload a valid Excel file!");
        
        document.getElementById("excelfile").value = "";
        
        $("#exltb").hide();
        
    }  
});  

</script>


<script>

function BindTable(jsondata, tableid) {/*Function used to convert the JSON array to Html Table*/  
    var columns = BindTableHeader(jsondata, tableid); /*Gets all the column headings of Excel*/
    
    for (var i = 0; i < jsondata.length; i++) {  
	//alert(columns.length)
	
		/* var thead = "<thead style='background-color: red;text-align:center;'><tr>";
		//alert(thead);
		
		var column ;
		for (var colIndex = 0; colIndex < column; colIndex++) {
			column = jsondata[i][column[colIndex]];
            thead += "<th>"+column+"</th>"
        }
		thead += "</tr></thead>";
        $("table#exceltable").append(thead); */
	
       /*  var thead = "<thead style='background-color: red;text-align:center;'><tr>";
        
        thead += "</tr></thead>";
        $("table#exceltable").append(thead); */
        
        var row$ = $('<tr/>'); 
		var td = "<tr>";
        for (var colIndex = 0; colIndex < columns.length; colIndex++) {
            var cellValue = jsondata[i][columns[colIndex]];  
            if (cellValue == null)  
                cellValue = "";
            
            var TH = jsondata[i][columns[colIndex]];
            td += "<td style='text-align:center; '><input name='"+TH+"_"+i+"' id='"+TH+"_"+i+"' value='"+cellValue+"' style='text-align:center;'/></td>";
            //row$.append($('<td/>').html(cellValue));  
        } 
        td += "</tr>";
        $("table#exceltable").append(td);
        //$(tableid).append(row$);  
    }  
}  
function BindTableHeader(jsondata, tableid) {/*Function used to get all column names from JSON and bind the html table header*/  
    var columnSet = [];  
    var headerTr$ = $('<tr/>');  
    for (var i = 0; i < jsondata.length; i++) {  
        var rowHash = jsondata[i];  
        for (var key in rowHash) {  
            if (rowHash.hasOwnProperty(key)) {  
                if ($.inArray(key, columnSet) == -1) {/*Adding each unique column names to a variable array*/  
                    columnSet.push(key);  
                    headerTr$.append($("<th style='text-align:center; font-size:12px; background-color: #943126; color: yellow; height:20px;'/>").html(key));  
                }  
            }  
        }  
    }  
    $(tableid).append(headerTr$);  
    return columnSet;  
}  

</script>

<script>
$(document).ready(function() {
	var para = '${r_1[0][1]}';
	if(para == "UNIT"){
		$("#sus_no1").val('${a_2}');
		$("#unit_name1").val('${a_3}');
		
	}
	
	if('${sus1}' != "" || '${unit1}' != "" || '${a_2}' != "" || '${a_3}' != ""){	
		$("#divPrint").hide();
	}
	var d = new Date();
	var year = d.getFullYear();
	$("#year").val(year);
	
	$("#exportAccessData").DataTable({   
		sPaginationType: "full_numbers",
	});
     

	try{
		if(window.location.href.includes("msg=")){
			var url = window.location.href.split("?msg")[0];
		    window.location = url;
	    } 
	}catch (e) {
		// TODO: handle exception
	}	
});
</script>
    

