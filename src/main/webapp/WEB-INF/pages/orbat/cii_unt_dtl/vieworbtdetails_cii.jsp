<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %>
<%@ taglib prefix="dandelion" uri="http://github.com/dandelion" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<link href="js/JS_CSS/jquery.dataTables.min.css" rel="stylesheet">  
<script src="js/JS_CSS/jquery.dataTables.js"></script> 
<style type="text/css">
	table.dataTable, table.dataTable th, table.dataTable td {
		-webkit-box-sizing: content-box;
		-moz-box-sizing: content-box;
		box-sizing: content-box;
		/* width: 80px; */
		text-align: center;
		
	}
	.dataTables_scrollHead {
		overflow-y:scroll !important;
		overflow-x:hidden !important;
	}
	.DataTables_sort_wrapper{
		/* width : 80px; */
	}
	
	table.dataTable tr.odd {
  			background-color: #f0e2f3;
	}
	table.dataTable thead {
  			background-color: #9c27b0;
  			color: white;
	}
	.dataTables_paginate.paging_full_numbers{
		margin-top: 0.755em;
	}
	.dataTables_paginate.paging_full_numbers a{
		background-color: #9c27b0;
		border: 1px solid #9c27b0;
		color: #fff;
		border-radius: 5px;
		padding: 3px 10px;
		margin-right: 5px;
	}
	.dataTables_paginate.paging_full_numbers a:hover{
		background-color: #cb5adf;
		border-color: #cb5adf;
	}
	.dataTables_info{
		color:#9c27b0 !important;
		font-weight: bold;
	}
	
	.print_btn{
	  margin:0 auto;
	}
	.print_btn input{
	  background-color: #9c27b0;
         border-color: #9c27b0;
	}
	.print_btn input:hover{
		background-color: #cb5adf;
		border-color: #cb5adf;
	}
</style>
<form action="" method="post" enctype="multipart/form-data" class="form-horizontal"> 
	<div class="animated fadeIn">
			<div class="container" align="center">
	    		<div class="card">
	    			<div class="card-header"><h5>View Orbat Details</h5></div>
	          			<div class="card-body">
	            			<div class="col-md-12">
		          				<div class="col-md-6">
		          					<div class="row form-group">
										<div class="col col-md-4">
											<label class=" form-control-label">Unit Name </label>
										</div>
										<div class="col-12 col-md-8">			
											<input type="text" id="unit_name" name="unit_name" maxlength="100" placeholder="select Unit Name" class="form-control autocomplete" >
										</div>
									</div>
		          				</div>
		          				<div class="col-md-6">
		          					<div class="row form-group">
	                					<div class="col col-md-4">
	                  						<label class=" form-control-label">SUS No</label>
							            </div>
							            <div class="col-12 col-md-8">
											<input type="text" id="sus_no" name="sus_no" maxlength="8" placeholder="Select SUS No" class="form-control autocomplete" >
										</div>
	              					</div>
		          				</div>
		          			</div>
		          			
		          			<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
								    	<div class="col col-md-4">
								        	<label class=" form-control-label">Level 1</label>
								       	</div>
								        <div class="col-12 col-md-8">
								        	<select id="level1" name="level1" class="form-control-sm form-control" >
								            	 <option value="0">--Select--</option>
	                  						</select>
								    	</div>
								  	</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
								    	<div class="col col-md-4">
								        	<label class=" form-control-label">Level 2</label>
								       	</div>
								        <div class="col-12 col-md-8">
								        	<select id="level2" name="level2" class="form-control-sm form-control" >
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
								        	<label class=" form-control-label">Level 3</label>
								       	</div>
								        <div class="col-12 col-md-8">
								        	<select id="level3" name="level3" class="form-control-sm form-control" >
								            	 <option value="0">--Select--</option>
	                  						</select>
								    	</div>
								  	</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
								    	<div class="col col-md-4">
								        	<label class=" form-control-label">Level 4</label>
								       	</div>
								        <div class="col-12 col-md-8">
								        	<select id="level4" name="level4" class="form-control-sm form-control" >
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
								        	<label class=" form-control-label">Level 5</label>
								       	</div>
								        <div class="col-12 col-md-8">
								        	<select id="level5" name="level5" class="form-control-sm form-control" >
								            	 <option value="0">--Select--</option>
	                  						</select>
								    	</div>
								  	</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
								    	<div class="col col-md-4">
								        	<label class=" form-control-label">Level 6</label>
								       	</div>
								        <div class="col-12 col-md-8">
								        	<select id="level6" name="level6" class="form-control-sm form-control" >
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
								        	<label class=" form-control-label">Level 7</label>
								       	</div>
								        <div class="col-12 col-md-8">
								        	<select id="level7" name="level7" class="form-control-sm form-control" >
								            	 <option value="0">--Select--</option>
	                  						</select>
								    	</div>
								  	</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
								    	<div class="col col-md-4">
								        	<label class=" form-control-label">Level 8</label>
								       	</div>
								        <div class="col-12 col-md-8">
								        	<select id="level8" name="level8" class="form-control-sm form-control" >
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
								        	<label class=" form-control-label">Level 9</label>
								       	</div>
								        <div class="col-12 col-md-8">
								        	<select id="level9" name="level9" class="form-control-sm form-control" >
								            	 <option value="0">--Select--</option>
	                  						</select>
								    	</div>
								  	</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
								    	<div class="col col-md-4">
								        	<label class=" form-control-label">Level 10</label>
								       	</div>
								        <div class="col-12 col-md-8">
								        	<select id="level10" name="level10" class="form-control-sm form-control" >
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
		                  					<label class=" form-control-label">Line Dte</label>
		                				</div>
		                				<div class="col-12 col-md-8">
		                 					<select id="line_dte_sus" name="line_dte_sus" class="form-control-sm form-control" >
		                 						${selectLine_dte}
		                 						<c:forEach var="item" items="${getLine_DteList}" varStatus="num" >
	                  								<option value="${item.line_dte_sus}"  name="${item.line_dte_name}" >${item.line_dte_name}</option>
	                  							</c:forEach>
		                 					</select>
		                				</div>
					            	</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
		                				<div class="col col-md-4">
		                  					<label class=" form-control-label">Location</label>
		                				</div>
		                				<div class="col-12 col-md-8">
		                 					<select id="location" name="location" class="form-control-sm form-control" >
		                 						<option value="0">--Select--</option>
			                                    <c:forEach var="item" items="${getlocList}" varStatus="num" >
	                  								<option value="${item[0]}">${item[1]}</option>
	                  							</c:forEach>
		                 					</select>
		                				</div>
					            	</div>
								</div>
							</div>
						</div>
						<div class="card-footer" align="center">
							<a href="OrbatDetails_Report_Formation_Cii" type="reset" class="btn btn-success btn-sm" style="border-radius: 5px;"> Clear </a> 
		              		<i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm" style="border-radius: 5px;" onclick="return getOrbatDetailsReport_cii();"  value="Search" /> 
		              		
			            </div>
			            <div class="card-body">
			            	<div class="" id="OrbatDetailsReport" >
								<datatables:table id="selection" url="getOrbatDetails_Formation_Rpt_cii" serverSide="true" pipelining="true" pipeSize="3"	row="latlon" rowIdBase="applicant_id" rowIdPrefix="latlon_" displayLength="5"  jqueryUI="true" lengthMenu="5,10,25,50,100,200,500,1000"
				    					bDestroy="true" filterable="false" sortable="false" processing="true" scrollY="398" scrollX="100%" scrollCollapse="true"  border="1" pageable="true" paginationType="full_numbers" stateSave="false" deferRender="true" >  
										    <datatables:column title="Ser No" property="id" searchable="false" visible="false" />
										    <datatables:column title="SUS No" property="sus_no" />
										    <datatables:column title="Unit Name" property="unit_name" />
										    <datatables:column title="Level 1" property="level1" />
										    <datatables:column title="Level 2" property="level2" />
										    <datatables:column title="Level 3" property="level3" />
										    <datatables:column title="Level 4" property="level4" />
										    <datatables:column title="Level 5" property="level5" />
										    <datatables:column title="Level 6" property="level6" />
										    <datatables:column title="Level 7" property="level7" />
										    <datatables:column title="Level 8" property="level8" />
										    <datatables:column title="Level 9" property="level9" />
										    <datatables:column title="Level 10" property="level10" />
										    
										    <datatables:column title="Loc" property="location" />
										    <datatables:column title="NRS" property="nrs" />
									</datatables:table>    
                        			<br/>
		        				</div>
			           	 	</div>
						</div>
	        		</div>
			</div>
		</form>
		
	    <c:url value="search_OrbatDetails_profileSetSession_cii" var="reloadUrl" />
		<form:form action="${reloadUrl}" method="post" id="reloadReport" name="reloadReport">
			<input type="hidden" name="sus_no1" id="sus_no1" />
			<input type="hidden" name="unit_name1" id="unit_name1" />
			
			<!-- for level hiearchy -->
			<input type="hidden" name="level1_1" id="level1_1" />
			<input type="hidden" name="level2_1" id="level2_1" />
			<input type="hidden" name="level3_1" id="level3_1" />
			<input type="hidden" name="level4_1" id="level4_1" />
			<input type="hidden" name="level5_1" id="level5_1" />
			<input type="hidden" name="level6_1" id="level6_1" />
			<input type="hidden" name="level7_1" id="level7_1" />
			<input type="hidden" name="level8_1" id="level8_1" />
			<input type="hidden" name="level9_1" id="level9_1" />
			<input type="hidden" name="level10_1" id="level10_1" />
			
			
			<input type="hidden" name="line_dte_sus1" id="line_dte_sus1" />
			<input type="hidden" name="location1" id="location1" />
		</form:form>

<script>

	$(document).ready(function() {   
	//	$("#OrbatDetailsReport").show();
		
    	$("#sus_no").val('${sus_no1}');
    	$("#unit_name").val('${unit_name1}');
    	
    	if('${location1}' != ""){
    		$("#location").val('${location1}');
    	}
    	
    	if('${level1_1}' != ""){
    		$("#level1").val('${level1_1}');
    	}
    	
    	if('${level2_1}' != ""){
    		$("#level2").val('${level2_1}');
    	}
    	if('${level3_1}' != ""){
    		$("#level3").val('${level3_1}');
    	}
    	if('${level4_1}' != ""){
    		$("#level4").val('${level4_1}');
    	}
    	if('${level5_1}' != ""){
    		$("#level5").val('${level5_1}');
    	}
    	if('${level6_1}' != ""){
    		$("#level6").val('${level6_1}');
    	}
    	if('${level7_1}' != ""){
    		$("#level7").val('${level7_1}');
    	}
    	if('${level8_1}' != ""){
    		$("#level8").val('${level8_1}');
    	}
    	
    	if('${level9_1}' != ""){
    		$("#level9").val('${level9_1}');
    	}
    	
    	if('${level10_1}' != ""){
    		$("#level10").val('${level1_1}');
    	}
    	
    	
	}); 
    	
    	
    	
 		
 		function getOrbatDetailsReport_cii() {
 			$("div#OrbatDetailsReport").show();
 			document.getElementById('unit_name1').value = $("#unit_name").val();
  	    	document.getElementById('sus_no1').value = $("#sus_no").val();
  	    	document.getElementById('level1_1').value = $("#level1").val();
  	    	document.getElementById('level2_1').value = $("#level2").val();
  	    	document.getElementById('level3_1').value = $("#level3").val();
  	    	document.getElementById('level4_1').value = $("#level4").val();
  	    	document.getElementById('level5_1').value = $("#level5").val();
  	    	document.getElementById('level6_1').value = $("#level6").val();
  	    	document.getElementById('level7_1').value = $("#level7").val();
  	    	document.getElementById('level8_1').value = $("#level8").val();
  	    	document.getElementById('level9_1').value = $("#level9").val();
  	    	document.getElementById('level10_1').value = $("#level10").val();
  	    	
  	    	
  	    	document.getElementById('line_dte_sus1').value = $("#line_dte_sus").val();
  	    	document.getElementById('location1').value = $("#location").val();
  	    	$("#WaitLoader").show();
  	    	document.getElementById('reloadReport').submit();
  			$("div#OrbatDetailsReport").show();
  			return false;	
  		}
 		
</script>


<script>
	function clearAll(){
    	$("#unit_name").val("");	
    	$("#sus_no").val("");	
    	$("#cont_comd").val("-1");	
    	$("#cont_corps").val("-1");	
    	$("#cont_div").val("-1");	
    	$("#cont_bde").val("-1");
    	$("#line_dte_sus").val("-1");
    	$("#location").val("0");
    }
</script>


<!-- <script>
$(document).ready(function() {
  	    // Load Level 1 options on page load
  	    $.ajax({
  	        url: 'loadLevel?level=0',
  	        type: 'GET',
  	        success: function(response) {
  	            var level1Dropdown = $("#level1");
  	            level1Dropdown.html('<option value="">Select Level 1</option>');
  	            response.forEach(function(option) {
  	                level1Dropdown.append('<option value="' + option + '">' + option + '</option>');
  	            });
  	        },
  	        error: function(xhr, status, error) {
  	            console.error('Error loading level1 options:', error);
  	        }
  	    });

  	    // Bind onchange events for all level dropdowns
  	    $('#level1').change(function() {
  	        loadNextLevel(1); // Load Level 2 when Level 1 is selected
  	    });
  	    $('#level2').change(function() {
  	        resetDropdownsAfter(2); // Reset subsequent levels after Level 2 is changed
  	        loadNextLevel(2); // Load Level 3 when Level 2 is selected
  	    });
  	    $('#level3').change(function() {
  	        resetDropdownsAfter(3); // Reset subsequent levels after Level 3 is changed
  	        loadNextLevel(3); // Load Level 4 when Level 3 is selected
  	    });
  	    // Add similar bindings for more levels as needed

  	    // Reset all dropdowns after the selected level
  	    function resetDropdownsAfter(level) {
  	        for (var i = level + 1; i <= 10; i++) {
  	            var nextDropdown = $("#level" + i);
  	            nextDropdown.html('<option value="">Select Level ' + i + '</option>');
  	           // nextDropdown.hide(); // Hide the dropdown after resetting
  	        }
  	    }

  	    // Function to load next level based on selected value
  	    function loadNextLevel(level) {
  	        var selectedValue = $("#level" + level).val();

  	        if (selectedValue !== "") {
  	            var url = "loadLevel?level=" + level + "&selectedValue=" + encodeURIComponent(selectedValue);

  	            $.ajax({
  	                url: url,
  	                type: 'GET',
  	                success: function(response) {
  	                    var nextLevel = level + 1;
  	                    var nextDropdown = $("#level" + nextLevel);

  	                    nextDropdown.html('<option value="">Select Level ' + nextLevel + '</option>');
  	                    response.forEach(function(option) {
  	                        nextDropdown.append('<option value="' + option + '">' + option + '</option>');
  	                    });

  	                    // Show the next dropdown if it has options
  	                    /* if (response.length > 0) {
  	                        nextDropdown.show();
  	                    } else {
  	                        nextDropdown.hide(); // If no options, hide the next level dropdown
  	                    } */
  	                },
  	                error: function(xhr, status, error) {
  	                    console.error('Error loading next level data:', error);
  	                }
  	            });
  	        } else {
  	            resetDropdownsAfter(level); // Reset the following levels if no value is selected
  	        }
  	    }
  	});
</script> -->
<script>
//raj 1.2.3.35476.56 + 66
$(document).ready(function() {
    // Load Level 1 options on page load and set the selected value if available
    loadLevel1Options('${level1_1}', '${level2_1}', '${level3_1}', '${level4_1}', '${level5_1}', '${level6_1}', '${level7_1}', '${level8_1}', '${level9_1}', '${level10_1}');

    // Bind onchange events for all level dropdowns
    $('#level1').change(function() {
        resetDropdownsAfter(1); // Reset subsequent levels after Level 1 is changed
        loadNextLevel(1); // Load Level 2 when Level 1 is selected
    });

    $('#level2').change(function() {
        resetDropdownsAfter(2); // Reset subsequent levels after Level 2 is changed
        loadNextLevel(2); // Load Level 3 when Level 2 is selected
    });

    $('#level3').change(function() {
        resetDropdownsAfter(3); // Reset subsequent levels after Level 3 is changed
        loadNextLevel(3); // Load Level 4 when Level 3 is selected
    });

    // More bindings for other levels as needed

    // Reset all dropdowns after the selected level
    function resetDropdownsAfter(level) {
        for (var i = level + 1; i <= 10; i++) {
            var nextDropdown = $("#level" + i);
            nextDropdown.html('<option value="">Select Level ' + i + '</option>');
        }
    }

    // Function to load Level 1 options and cascade down to other levels if values are passed
    function loadLevel1Options(level1, level2, level3, level4, level5, level6, level7, level8, level9, level10) {
        $.ajax({
            url: 'loadLevel?level=0',
            type: 'GET',
            success: function(response) {
                var level1Dropdown = $("#level1");
                level1Dropdown.html('<option value="">Select Level 1</option>');
                response.forEach(function(option) {
                    if (option === level1) {
                        level1Dropdown.append('<option value="' + option + '" selected>' + option + '</option>');
                    } else {
                        level1Dropdown.append('<option value="' + option + '">' + option + '</option>');
                    }
                });
                // Load Level 2 options if level1 is pre-selected
                if (level1) {
                    loadNextLevel(1, level2, level3, level4, level5, level6, level7, level8, level9, level10);
                }
            },
            error: function(xhr, status, error) {
                console.error('Error loading level1 options:', error);
            }
        });
    }

    // Function to load the next level based on selected value
    function loadNextLevel(level, nextSelectedValue = '', ...restSelectedValues) {
        var selectedLevelValue = $("#level" + level).val();

        if (selectedLevelValue !== "") {
            var url = "loadLevel?level=" + level + "&selectedValue=" + encodeURIComponent(selectedLevelValue);

            $.ajax({
                url: url,
                type: 'GET',
                success: function(response) {
                    var nextLevel = level + 1;
                    var nextDropdown = $("#level" + nextLevel);

                    nextDropdown.html('<option value="">Select Level ' + nextLevel + '</option>');
                    response.forEach(function(option) {
                        if (option === nextSelectedValue) {
                            nextDropdown.append('<option value="' + option + '" selected>' + option + '</option>');
                        } else {
                            nextDropdown.append('<option value="' + option + '">' + option + '</option>');
                        }
                    });

                    // Cascade down to the next level if a value is pre-selected
                    if (nextSelectedValue) {
                        loadNextLevel(nextLevel, ...restSelectedValues);
                    }
                },
                error: function(xhr, status, error) {
                    console.error('Error loading next level data:', error);
                }
            });
        } else {
            resetDropdownsAfter(level); // Reset the following levels if no value is selected
        }
    }
});

</script>


