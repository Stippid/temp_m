<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>

<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

<link rel="stylesheet" href="js/layout/css/animation.css">
<link rel="stylesheet" href="js/assets/collapse/collapse.css">



<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>

<!-- resizable_col -->
<link rel="stylesheet" href="js/assets/adjestable_Col/jquery.resizableColumns.css">
<script src="js/assets/adjestable_Col/jquery.resizableColumns.js" type="text/javascript"></script>



<link href="js/cue/cueWatermark.css" rel="Stylesheet"></link>

 
<style>
.sticky {
    position: fixed;
    top: 0;
    z-index: 1000;
    padding-right: 35px;
}
.sticky + .subcontent {
  padding-top: 330px;
}
</style>
<style>
.selectBox {
	position: relative;
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
.go-top {
  right:1em;
  bottom:2em;
  color:#FAFAFA;
  text-decoration:none;
  background:#F44336;
  padding:5px;
  border-radius:5px;
  border:1px solid #e0e0e0;
  position:fixed;
  font-size: 180%;
  

}

label {
    word-break: break-word;
}


textarea {
    text-transform: none!important;
}


</style>

<script>

$(window).scroll(function(){
    if($(this).scrollTop() > 100){
      $('.go-top').fadeIn(200);      
    } else {
      $('.go-top').fadeOut(300);
    }
  });
  $('.go-top').click(function() {
    event.preventDefault();
    
    $('html , body').animate({scrollTop: 0}, 1000);
  });

</script>

  <form id = "filed_form" >
	<div class="animated fadeIn"> 
		<div class="col-md-12" align="center">
		<div class="card-header">
									<h5> DYNAMIC QUERY</h5>
								<!-- 	<h6 class="enter_by">
										<span style="font-size: 12px; color: red;">(To be
											entered by the first Unit of Commissioned Offr)</span>
									</h6> -->
								</div>
			<div class="card">
<!-- 				<div class="card-body card-block"> -->
<%-- 					<div class="col-md-12">
<%-- 						<div class="col-md-6"> --%>
<%-- 							<div class="row form-group"> --%>
<%-- 								<div class="col-md-4"> --%>
<%-- 									<label class=" form-control-label"><strong style="color: red;"></strong>Category</label> --%>
<%-- 								</div> --%>
<%-- 								<div class="col-md-8"> --%>
<%--                					   <div class="selectBox" onclick="Category_showCheckboxes(1)"> --%>
<%-- 										<select name="sub_category" id="sub_category" --%>
<%-- 											class=" form-control"> --%>
<%-- 											<option>Select Category</option> --%>
<%-- 										</select> --%>
<%-- 										<div class="overSelect"></div> --%>
<%-- 									</div> --%>
<%-- 										<div id="checkboxes_category1" class="checkboxes" --%>
<%-- 										style="max-height: 200px; overflow: auto;"> --%>
<%-- 										<div style=""> --%>
<%-- 											<input type="text" id="category_search1" --%>
<%-- 												class="form-control autocomplete" autocomplete="off" --%>
<%-- 												placeholder="Search Category"> --%>
<%-- 										</div> --%>
<%-- 										<div> --%>
<%-- 											<c:forEach var="item" items="${get_catgory_List}" varStatus="num"> --%>
<%-- 												<label for="one" class="category_list"> <input onclick="Category_Name();" --%>
<%-- 													type="checkbox" name="multisub_category" value="${item.codevalue}" id = "${item.codevalue}-a1"/> --%>
<%-- 													${item.label} --%>
<%-- 												</label> --%>
<%-- 											</c:forEach> --%>
<%-- 									<c:forEach var="item" items="${get_catgory_List}" varStatus="num">			               --%>
<%-- 				                     <label for="one" class="category_list"> <input type="radio" name="multisub_category" value="${item.codevalue}" id = "${item.codevalue}-a1" onclick="Category_Name();"/>      --%>
<%-- 				                   ${item.label} --%>
<%-- 									</label> --%>
<%-- 				                 </c:forEach> --%>
<%-- 										</div> --%>
<%-- 										<input type="hidden" id="hd_category" name="hd_category" class="form-control autocomplete" autocomplete="off"></input> --%>
<%-- 									</div> --%>
<%-- 						    </div> --%>
<%-- 								</div> --%>
<%-- 							</div> --%>
<%-- 							</div> --%> 
							
							
<!-- 								<div class="col-md-12"> -->
<!-- 						<div class="col-md-6"> -->
<!-- 							<div class="row form-group"> -->
<!-- 								<div class="col-md-4"> -->
<!-- 									<label class=" form-control-label"><strong style="color: red;"></strong>Category</label> -->
<!-- 								</div> -->
<!-- 								<div class="col-md-8"> -->
<!-- 									<div id = "divContainer" class="form-check-inline form-check"></div> -->
<!-- 									<input type="hidden" id="hd_category" name="hd_category" class="form-control autocomplete" autocomplete="off"></input> -->
<!-- 						</div> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
							
							
<!-- 							<div class="col-md-12" id ="civilian_type_hid" style="display: none;"> -->
<!-- 							<div class="col-md-6"> -->
<!-- 							<div class="row form-group"> -->
<!-- 								<div class="col-md-4"> -->
<!-- 									<label class=" form-control-label"><strong -->
<!-- 												style="color: red;"> </strong> Civilian type</label> -->
<!-- 								</div> -->
<!-- 								<div class="col-md-8"> -->
<!-- 								 <select name="civilian_type" id="civilian_type" -->
<!-- 										class="form-control" > -->
<!-- 										<option value="0">--Select--</option> -->
<!-- 										<option value="r">Regular</option> -->
<!-- 										<option value="nr">Non-Regular</option> -->
<!-- 									</select>  -->
<!-- 						</div> -->
						
<!-- 						</div> -->
<!-- 						</div> -->
<!-- 						</div> -->
						
<!--         			<div class="col-md-12"> -->
<!-- 								<div class="col-md-6"> -->
<!-- 									<div class="row form-group"> -->
<!-- 								    	<div class="col col-md-4"> -->
<!-- 								        	<label class=" form-control-label">Cont Comd </label> -->
<!-- 								       	</div> -->
<!-- 								        <div class="col-12 col-md-8"> -->
<!-- 								          	<select id="cont_comd" name="cont_comd" class="form-control" > -->
<%-- 								            	${selectcomd} --%>
<%-- 									            <c:forEach var="item" items="${getCommandList}" varStatus="num" > --%>
<%-- 									            	<option value="${item[0]}"  name="${item[1]}" >${item[1]}</option> --%>
<%-- 	                  							</c:forEach> --%>
<!-- 	                  						</select> -->
<!-- 								    	</div> -->
<!-- 								  	</div> -->
<!-- 								</div> -->
<!-- 								<div class="col-md-6"> -->
<!-- 									<div class="row form-group"> -->
<!-- 			                			<div class="col col-md-4"> -->
<!-- 			                  				<label class=" form-control-label">Cont Corps</label> -->
<!-- 			               				</div> -->
<!-- 			                			<div class="col-12 col-md-8"> -->
<!-- 			                 				<select id="cont_corps" name="cont_corps" class="form-control" > -->
<%-- 			                 					${selectcorps} --%>
<%-- 			                 					<c:forEach var="item" items="${getCorpsList}" varStatus="num" > --%>
<%-- 	                  								<option value="${item[0]}"  name="${item[1]}" >${item[1]}</option> --%>
<%-- 	                  							</c:forEach> --%>
<!-- 			                 				</select> -->
<!-- 			                			</div> -->
<!-- 					              	</div> -->
<!-- 								</div> -->
<!-- 							</div> -->
							
<!-- 								<div class="col-md-12"> -->
<!-- 								<div class="col-md-6"> -->
<!-- 									<div class="row form-group"> -->
<!-- 						                <div class="col col-md-4"> -->
<!-- 						                  <label class=" form-control-label">Cont Div</label> -->
<!-- 						                </div> -->
<!-- 						                <div class="col-12 col-md-8"> -->
<!-- 						                 	<select id="cont_div" name="cont_div" class="form-control" > -->
<%-- 						                 		${selectDiv} --%>
<%-- 						                 		<c:forEach var="item" items="${getDivList}" varStatus="num" > --%>
<%-- 	                  								<option value="${item[0]}"  name="${item[1]}" >${item[1]}</option> --%>
<%-- 	                  							</c:forEach> --%>
<!-- 						                 	</select> -->
<!-- 						                </div> -->
<!-- 						            </div> -->
<!-- 								</div> -->
<!-- 								<div class="col-md-6"> -->
<!-- 									<div class="row form-group"> -->
<!-- 		                				<div class="col col-md-4"> -->
<!-- 		                  					<label class=" form-control-label">Cont Bde</label> -->
<!-- 		                				</div> -->
<!-- 		                				<div class="col-12 col-md-8"> -->
<!-- 		                 					<select id="cont_bde" name="cont_bde" class="form-control" > -->
<%-- 		                 						${selectBde} --%>
<%-- 		                 						<c:forEach var="item" items="${getBdeList}" varStatus="num" > --%>
<%-- 	                  								<option value="${item[0]}"  name="${item[1]}" >${item[1]}</option> --%>
<%-- 	                  							</c:forEach> --%>
<!-- 		                 					</select> -->
<!-- 		                				</div> -->
<!-- 					            	</div> -->
<!-- 								</div> -->
<!-- 							</div> -->
							
<!-- 								<div class="col-md-12"> -->
<!-- 		          				<div class="col-md-6"> -->
<!-- 		          					<div class="row form-group"> -->
<!-- 										<div class="col col-md-4"> -->
<!-- 											<label class=" form-control-label">Unit Name </label> -->
<!-- 										</div> -->
<!-- 										<div class="col-12 col-md-8">			 -->
<!-- 											<input type="text" id="unit_name" name="unit_name" maxlength="100" placeholder="Select Unit Name" class="form-control autocomplete" > -->
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 		          				</div> -->
<!-- 		          				<div class="col-md-6"> -->
<!-- 		          					<div class="row form-group"> -->
<!-- 	                					<div class="col col-md-4"> -->
<!-- 	                  						<label class=" form-control-label">SUS No</label> -->
<!-- 							            </div> -->
<!-- 							            <div class="col-12 col-md-8"> -->
<!-- 											<input type="text" id="sus_no" name="sus_no" maxlength="8" placeholder="Select SUS No" class="form-control autocomplete" > -->
<!-- 										</div> -->
<!-- 	              					</div> -->
<!-- 		          				</div> -->
<!-- 		          			</div> -->
		          			
<!-- 		          			<div class="col-md-12"> -->
<!-- 		          				<div class="col-md-6"> -->
<!-- 		          					<div class="row form-group"> -->
<!-- 										<div class="col col-md-4"> -->
<!-- 											<label class=" form-control-label">CT PART </label> -->
<!-- 										</div> -->
<!-- 										<div class="col-12 col-md-8">			 -->
<!-- 											<select id="ct_part_i_ii" name="ct_part_i_ii" class="form-control" > -->
<!-- 									            <option value="0">--Select--</option> -->
<!-- 									            <option value="CTPartI">CTPartI</option> -->
<!-- 									            <option value="CTPartII">CTPartII</option> -->
<!-- 	                  						</select> -->
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 		          				</div> -->
<!-- 		          			<div class="col-md-6"> -->
<!-- 		          					<div class="row form-group"> -->
<!-- 	                					<div class="col col-md-4"> -->
<!-- 	                  						<label class=" form-control-label">Peace/Field</label> -->
<!-- 							            </div> -->
<!-- 							            <div class="col-12 col-md-8"> -->
<!-- 											<input type="text" id="type_of_location" name="type_of_location"   class="form-control autocomplete" > -->
<!-- 										<select name="type_of_location" id="type_of_location" class="form-control"> -->
<!-- 											<option value="0">---Select---</option> -->
<!-- 											<option value="A">A</option> -->
<!-- 										    <option value="B">B</option> -->
<!-- 										    <option value="C">C</option> -->
<!-- 										    <option value="HAA">HAA</option> -->
<!-- 										    <option value="FD">FD</option> -->
<!-- 										    <option value="CI Ops">CI Ops</option> -->
<!-- 										    <option value="LC">LC</option> -->
<!-- 										    <option value="CI">CI</option> -->
<!-- 								    </select> -->
<!-- 										</div> -->
<!-- 	              					</div> -->
<!-- 		          				</div>  -->
<!-- 		          			</div> -->
		          			
<!-- 					<div class="col-md-12"> -->
<!-- 						<div class="col-md-6"> -->
<!-- 							<div class="row form-group"> -->
<!-- 								<div class="col-md-4"> -->
<!-- 									<label class=" form-control-label"><strong style="color: red;"></strong>Month</label> -->
<!-- 								</div> -->
<!-- 								<div class="col-md-8"> -->
<!-- 									<select name="month" id="month" class="form-control"> -->
<!-- 									<option value="0">---Select---</option> -->
<!-- 									<option value="1">January</option> -->
<!-- 								    <option value="2">February</option> -->
<!-- 								    <option value="3">March</option> -->
<!-- 								    <option value="4">April</option> -->
<!-- 								    <option value="5">May</option> -->
<!-- 								    <option value="6">June</option> -->
<!-- 								    <option value="7">July</option> -->
<!-- 								    <option value="8">August</option> -->
<!-- 								    <option value="9">September</option> -->
<!-- 								    <option value="10">October</option> -->
<!-- 								    <option value="11">November</option> -->
<!-- 								    <option value="12">December</option> -->
<!-- 									</select> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
						
<!-- 							<div class="col-md-6"> -->
<!-- 							<div class="row form-group"> -->
<!-- 								<div class="col-md-4"> -->
<!-- 									<label class=" form-control-label"><strong style="color: red;"></strong>Year</label> -->
<!-- 								</div> -->
<!-- 								<div class="col-md-8"> -->
<!-- 									<input type="text" id="year" name="year" class="form-control autocomplete" autocomplete="off" -->
<!-- 									maxlength="4"  onkeypress="return isNumber0_9Only(event)" /> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 				</div> -->
			</div>
		</div>
	 </div> 
	 
	 	<div class="col-md-12" id="button_div" > 
				<div class="card-footer" align="right" class="form-control">
				    <a href="ad_hoc_mnh_Query_Url" class="btn btn-success btn-sm">Clear</a>  
					<input type="button" id="mrgqualibtn_save" class="btn btn-primary btn-sm" value="Genrate Result" onclick="return submit_fn();" >		              
				   <i class="action_icons action_download" id="print_div_image" style="display: none;"></i><input type="button" id="print_div" style="display: none;" class="btn btn-primary btn-sm" value="Print" onclick="printDataAppend()" >
				  <!-- <i class="fa fa-file-excel-o"></i><input type="button" id="btnExport" style="display: none;" style="font-size: x-large; color: green; text-align: right;" aria-hidden="true" title="EXPORT TO EXCEL" onclick="getExcel();" > -->
				  <i class="fa fa-file-excel-o" id="btnExport"  style="font-size: x-large; color: green; text-align: right;display: none;" aria-hidden="true" title="EXPORT TO EXCEL" onclick="getExcel();"></i>
				</div>
		</div>
	
	
		<table id="head_table" class="table-bordered" style="margin-top: 10px; width: 100%;">
								<thead style="color: white; text-align: center;">
									<tr>
										<th><strong style="color: red;"> </strong>Details with Aliase</th>
											<th><strong style="color: red;"> </strong>Selected Details with Aliase</th>
									</tr>
								</thead>
								<tbody data-spy="scroll" id="head_tbody" style="min-height: 46px; max-height: 650px; text-align: center;">
									<tr id="tr_head1">
												<td>    
										<div class="selectBox" onclick="Table_showCheckboxes(1)">
										<select name="sub_table" id="sub_table"
											class=" form-control">
											<option>Select Details</option>
										</select>
										<div class="overSelect"></div>
									</div>
										<div id="checkboxes_table1" class="checkboxes"
										style="max-height: 200px; overflow: auto;">
										<div style="">
											<input type="text" id="table_search1"
												class="form-control autocomplete" autocomplete="off"
												placeholder="Search Details">
										</div>
										<div id = "table_div">
										 	<c:forEach var="item" items="${get_table_name_List_mnh}" varStatus="num">
												<label for="one" class="table_list"> <input onclick="Table_Name();" 
													type="checkbox" name="multisub_table" value="${item.codevalue}" id = "${item.codevalue}"/>
													${item.label}
												</label>
											</c:forEach> 
										</div>
											<input type="hidden" id="hd_table" name="hd_table" class="form-control autocomplete" autocomplete="off"></input>
									</div>
									</td>
								<td>    
											<div id="spouse_quali_sub_list_details"
												style="max-height: 200px; overflow: auto; width: 100%; ">

											</div>

									</td> 

									</tr>
								</tbody>
							</table>
	
		<div class="col-md-12"  id="col_filed" style="display: none;">
		<div class="card">
			<div class="panel-group" id="accordion99">
				<div class="panel panel-default" id="d_div1">
					<div class="panel-heading">
						
						<h4 class="panel-title main_title blue" id="d_div5">
							<a class="droparrow collapsed" data-toggle="collapse" 
								data-parent="#accordion8" href="#" id="d_final" onclick="divN(this)"
								><b>FIELDS</b></a>
						</h4>
					</div>
					<div id="collapse1d" class="panel-collapse collapse">
					<div class="card-body card-block">
						<div align="left">
						</div>
								<div class="card-body-header">
									<h5></h5>
								</div>
								<table id="selected_table" class="table-bordered" style="margin-top: 10px; width: 100%;">
								<thead style="color: white; text-align: center;">
									<tr>
										<!-- <th style="width: 10%;">Sr No</th> -->
										<th><strong style="color: red;"> </strong>Field</th>
										<th><strong style="color: red;"> </strong>Selected Field</th>
									</tr>
								</thead>
								
									<tbody data-spy="scroll" id="selected_tbody" style="min-height: 46px; max-height: 650px; text-align: center;">
									<tr id="tr_selected">
												<td>    
										<div class="selectBox" onclick="showCheckboxes()">
										<select name="sub_column" id="sub_column"
											class=" form-control">
											<option>Select Details</option>
										</select>
										<div class="overSelect"></div>
									</div>
										<div id=checkboxes class="checkboxes"
										style="max-height: 200px; overflow: auto;">
										<div style="">
											<input type="text" id="column_search"
												class="form-control autocomplete" autocomplete="off"
												placeholder="Column Search Details">
										</div>
										<div id = "filed1_check">
										</div>
											<input type="hidden" id="hd_column" name="hd_column" class="form-control autocomplete" autocomplete="off"></input>
									</div> 
								</td>
								
								
								<td>    
											<div id="spouse_quali_sub_list"
												style="max-height: 200px; overflow: auto; width: 100%;">
											</div>

								</td> 
									</tr>
								</tbody>
							</table>
							</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	    			<div class="col-md-12" id="div_filter" style="display: none;">
            			<div class="card">
			<div class="panel-group" id="accordion4">
				<div class="panel panel-default" id="a_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title red" id="a_div5">
								<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion4" href="#" id="a_final" onclick="divN(this)"
								><b>FILTER</b></a>
						</h4>
					</div>
					<div id="collapse1a" class="panel-collapse collapse">
			                <div class="card-body card-block"><br>
							<div id="divShow" style="display: block;"></div>
			<div class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
				<span id="ip"></span>
					<table id="ad_hoc_table" class="table-bordered " style="margin-top:10px; width: -webkit-fill-available;">
					<thead style=" color: white; text-align: center;">
						<tr>
							<th>Sr No</th>
							<th>Filed Name</th> 
							<th>Operator</th> 
							<th id = "th_value">Value</th> 
							<th id = "from_dt"  >From Date</th> 
							<th id = "to_dt"  >To Date</th>
							<th id = "from_val"  >From Value</th> 
							<th id = "to_val"  >To Value</th>  
							<th>AndOr</th> 
							<th>Action</th>
					   </tr>
					</thead>
				   		<tbody data-spy="scroll" id="allergictbody" style="min-height: 46px; max-height: 650px; text-align: center;"> 
							
							 <tr id="tr_allergic1">
								<td class="aller_srno">1</td>
								<td>    
									
								<div class="selectBox" onclick="FiledName_show_checkBox(1)">
										<select name="multisub_filter1" id="multisub_filter1"
											class=" form-control"  onchange="Coulmn_Name_Filter('1');">
											<option>Select Filed Name</option>
										</select>
										<div class="overSelect"></div>
									</div>
										<div id="filed_list1" class="checkboxes"
										style="max-height: 200px; overflow: auto;">
										<div style="">
											<input type="text" id="filed_search1"
												class="form-control autocomplete" autocomplete="off"
												placeholder="Search Filed Name" onkeypress="get_filed_search(1);">
												
										</div>
										<div id="Filed_drop1">
										
										</div>
										<input type="hidden" id="hd_Filed_List1" name="hd_Filed_List1" class="form-control autocomplete" autocomplete="off"></input>
									</div>  
					  
										   
								</td>
							<td>
											<select  id="operator1" name="operator1" class="form-control" onchange="fn_Operator('1');" >
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${get_operator_List}" varStatus="num">
														<option value="${item.codevalue}" name="${item.label}">${item.label}</option>
														</c:forEach>
											</select> 
								</td>
								<td id="value1">  
											<input type="text" id="ne_value1" name="ne_value1" maxlength="100"   onkeyup="Value(1);"
									class="form-control autocomplete" autocomplete="off">
								</td>
								
							<td id="value_date1" style="display: none;">
								<input type="text" name="value_dt1" id="value_dt1" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);Value(1);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
								</td>
								
								<td id="td_from_date1"  >
								<input type="text" name="from_date1" id="from_date1" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
									readonly		onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);Value(1);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
								</td>
								
								<td id="td_to_date1" >
								<input type="text" name="to_date1" id="to_date1" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
										readonly	onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);Value(1);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
								</td>
								
								
								<td id="valuef1">  
											<input type="text" id="ne_valuef1" name="ne_valuef1" maxlength="100"   onkeyup="Value(1);"
												readonly class="form-control autocomplete" autocomplete="off">
								</td>
								
								<td id="valuet1">  
											<input type="text" id="ne_valuet1" name="ne_valuet1" maxlength="100"   onkeyup="Value(1);"
									readonly class="form-control autocomplete" autocomplete="off">
								</td>
								
								<td> <select name="andor1" id="andor1" class="form-control-sm form-control" onchange="fn_AndOr(1);" readonly> 
												<option value="-1">--Select--</option>
												<option value="and">And</option>
												<option value="or">Or</option>
											</select> 
											
								</td>
							<td>
							   <a class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "allergic_add1" onclick="add_fn(1);" ><i class="fa fa-plus"></i></a>
							</td>
							</tr> 
						
				   </tbody> 
						</table>
			</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		</div>
		
		
		
		<div class="col-md-12" id="div_sort" style="display: none;">
			<div class="card">
			<div class="panel-group" id="accordion3">
				<div class="panel panel-default" id="b_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title lightblue" id="b_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion3" href="#" id="b_final" onclick="divN(this)"
								><b>SORT</b></a>
						</h4>
					</div>
					<div id="collapse1b" class="panel-collapse collapse">
						<div class="card-body card-block">
						<div align="left">
						</div>
								<div class="card-body-header">
									<h5></h5>
								</div>
								<table id="sort_table" class="table-bordered" style="margin-top: 10px; width: 100%;">
								<thead style="color: white; text-align: center;">
									<tr>
										<th>Sr No</th>
										<th><strong style="color: red;"> </strong>Order</th>
										<th><strong style="color: red;"> </strong>Direction</th>
										<th>Action</th>
									</tr>
								</thead>
								<tbody data-spy="scroll" id="sort_tbody" style="min-height: 46px; max-height: 650px; text-align: center;">
									<tr id="tr_sort1">
										<td class="sort_srno" >1</td>
												<td>    
									<!-- <select id="multisub_sort1" name="multisub_sort1" class="form-control">
										</select> -->
										
											<div class="selectBox" onclick="Short_show_checkBox(1)">
										<select name="multisub_sort1" id="multisub_sort1"
											class=" form-control">
											<option>Select Filed Name</option>
										</select>
										<div class="overSelect"></div>
									</div>
										<div id="short_list1" class="checkboxes"
										style="max-height: 200px; overflow: auto;">
										<div style="">
											<input type="text" id="short_search1"
												class="form-control autocomplete" autocomplete="off"
												placeholder="Search Filed Name" onkeypress="get_sort_search(1);">
												
										</div>
										<div id="short_drop1">
										
										</div>
										<input type="hidden" id="hd_short_List1" name="hd_short_List1" class="form-control autocomplete" autocomplete="off"></input>
									</div>  
								</td>
							<td>
							 <select name="direction1" id="direction1" class="form-control-sm form-control" onchange="fn_direction(1);">
												<option value="0">--Select--</option>
												<option value="Asc">Asc</option>
												<option value="Desc">Desc</option>
											</select> 
								</td>
										<td>
										<a id="sort_add1" class="btn btn-success btn-sm" 
										value="ADD" title="ADD" onclick="add_fn_sort(1);">
										<i class="fa fa-plus"></i>
										</a>
										</td>
									</tr>
								</tbody>
							</table>
							</div>
						</div>
					</div>
					
				</div>
			</div>
	</div>
	
	                <input type="hidden" id="count_filter" name="count_filter" value="0">
					<input type="hidden" id="count_sort" name="count_sort" value="0">
					<input type="hidden" id="count_head" name="count_head" value="1">
					<input type="hidden" id="check_filed" name="check_filed" >
					<input type="hidden" id="table_name_hidden_list" name="table_name_hidden_list" >
				    <input type="hidden" id="category_hidden_list" name="category_hidden_list" >
				    <input type="hidden" id="roleAccess" name="roleAccess" > 
				    <input type="hidden" id="hd_cont_comd" name="hd_cont_comd" >
					<input type="hidden" id="hd_cont_corps" name="hd_cont_corps" >
				    <input type="hidden" id="hd_cont_div" name="hd_cont_div" >
				    <input type="hidden" id="hd_cont_bde" name="hd_cont_bde" > 
				    <input type="hidden" id="hd_unit_name" name="hd_unit_name" > 
				    
	
	<div class="col-md-12"  id="div_query" style="display: none;">
		<div class="card">
			<div class="panel-group" id="accordion99">
				<div class="panel panel-default" id="c_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title lightgreen" id="c_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion99" href="#" id="c_final" onclick="divN(this)"
								style="color: #90EE90;"><b>SHOW QUERY</b></a>
						</h4>
					</div>
					<div id="collapse1c" class="panel-collapse collapse">
								<div class="col-md-12">
						 <div class="col-md-6" id="" >
							<div class="row form-group">
								<div class="col-md-4">
									<strong style="color: red;"></strong><label class=" form-control-label"> Query </label>
								</div>
								<div class="col-md-8">
										<textarea id="create_query" name="create_query" class="form-control autocomplete" ></textarea>
								</div>
							</div>
						</div>
					</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</form>
	
		<!-- <div class="col-md-12" id="button_div" > 
				<div class="card-footer" align="right" class="form-control">
				    <a href="ad_hoc_Url" class="btn btn-success btn-sm">Clear</a>  
					<input type="button" id="mrgqualibtn_save" class="btn btn-primary btn-sm" value="Genrate Result" onclick="return submit_fn();" >		              
				   <i class="action_icons action_download"></i><input type="button"  class="btn btn-primary btn-sm" value="Print" onclick="printDataAppend()" >
				   <i class="fa fa-file-excel-o" id="btnExport" style="font-size: x-large; color: green; text-align: right;" aria-hidden="true" title="EXPORT TO EXCEL" onclick="getExcel();"></i>
				</div>
		</div> -->
		
			<div class="col-md-12" style="text-align: center;display: none;" id="div_result" > 
			<div class="card-header">
									<h5> DYNAMIC QUERY RESULT</h5>
									<div id ="div_size" style="display: non;">
									</div>
								</div>
								</div>
		<div class="col-md-12">
		
		<!-- <table id="show_data" class="table no-margin table-striped  table-hover  table-bordered report_print"  style="display: none;"> -->
			<table id="show_data" class="table no-margin table-striped  table-hover table-bordered report_print" style="display: none;">
				<thead id = "thead_data" style="text-align: center;">
			        <tr id = "tr_data">
			    </tr>
				</thead>
				<tbody id = "td_data">
				</tbody>
			</table>
		</div>

	
<a href="#" class="go-top fa fa-arrow-up" style="display:none"></a>
<script type="text/javascript">




function divN(obj){
	var id = obj.id;
	 var sib_id = $("#"+id).parent().parent().next().attr('id');
	var hasC=$("#"+sib_id).hasClass("show");
		$(".panel-collapse").removeClass("show");
		 $('.droparrow').each(function(i, obj) {
			 $("#"+obj.id).attr("class", "droparrow collapsed");
			 });
	
		
		if (hasC) {	
		$("#"+id).addClass( " collapsed");		 
		}  else {				
			$("#"+sib_id).addClass("show");	
			$("#"+id).removeClass("collapsed");
	    }
		
		if (obj.id == "a_final") {
			if(!hasC){
				$("#count_filter").val("1");
				//get_filter();
			}
		}
		
		if (obj.id == "c_final") {
			if(!hasC){
				//get_query();
			}
		}
		
		if (obj.id == "d_final") {
			if(!hasC){
				//get_filed();
			}
		}
		
		if (obj.id == "b_final") {
			if(!hasC){
				$("#count_sort").val("1");
				//get_sort();
			}
		}
		
		/* if (obj.id == "e_final") {
			if(!hasC){
				get_total();
			}
		} */
		}

function remove_fn(R){
	   $("tr#tr_allergic"+R).remove();
	   R = R-1;
	// x = x-1;
	   $("input#count_filter").val(R);
	   $("#allergic_add"+R).show();
	   $("#allergic_remove"+R).show();
	   $("#andor"+R).val("-1");
	   //getPersCount();
	}
	
	
	
function add_fn_sort(x){
	if($("#count_sort").val()!= 0){
		if ($("#hd_short_List"+x).val() == "") {
			alert("Please Select Order");
			$("#multisub_sort"+x).focus();
			return false;
		} 
		
		if ($("#direction"+x).val() == "0") {
			alert("Please Select Direction");
			$("#direction"+x).focus();
			return false;
		} 
	}
	
	

	   $("#sort_add" + x).hide();
	   $("#sort_remove" + x).hide();

		x+=1; 
		
		 $("input#count_sort").val(x);
	
	$("table#sort_table").append('<tr id="tr_sort'+x+'">'
			+'<td class="sort_srno">'+x+'</td>'
// 			+'<td><select id="multisub_sort'+x+'" name="multisub_sort'+x+'" class="form-control"></select>'
 		+'<td><div class="selectBox" onclick="Short_show_checkBox('+x+')"><select name="multisub_sort'+x+'" id="multisub_sort'+x+'" class=" form-control">'
 			+'<option>Select Filed Name</option></select><div class="overSelect"></div></div>'
 			+'<div id="short_list'+x+'" class="checkboxes" style="max-height: 200px; overflow: auto;">'
 			+'<div style=""><input type="text" id="short_search'+x+'" class="form-control autocomplete" autocomplete="off" placeholder="Search Filed Name" onkeypress="get_sort_search('+x+');">'
 			+'</div><div id="short_drop'+x+'"></div><input type="hidden" id="hd_short_List'+x+'" name="hd_short_List'+x+'" class="form-control autocomplete" autocomplete="off"></input>'
 			+'</div> '
			+ '</td>'
			+'<td>'
			+ '<select name="direction'+x+'" id="direction'+x+'" class="form-control-sm form-control" onchange="fn_direction('+x+');" ><option value="0">--Select--</option><option value="Asc">Asc</option><option value="Desc">Desc</option></select> </td>'
			+'<td>'
			+'<a class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "sort_add'+x+'" onclick="add_fn_sort('+x+');" ><i class="fa fa-plus"></i></a>'
			+'<a  class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "sort_remove'+x+'" onclick="remove_fn_sort('+x+');"><i class="fa fa-trash"></i></a> '
			+'</td></tr>');
	
	var tn = $("#table_name_hidden_list").val();

    	
    	
var jn = $("#check_filed").val();
	
	var hedarslist = jn.split(',');
    	
      	var options = '<option   value="0">' + "--Select--"
			+ '</option>';
			for (var i = 0; i < hedarslist.length; i++) {
				options += '<option   value="' + hedarslist[i] + '" name="'+hedarslist[i]+'" >'
						+ hedarslist[i]+ '</option>';
			}
	$("select#multisub_sort"+x).html(options);
	
    /* }).fail(function(xhr, textStatus, errorThrown) {
    }); */ 
	
}



function remove_fn_sort(Rv){
	   $("tr#tr_sort"+Rv).remove();
	   Rv = Rv-1;
	// x = x-1;
	   $("input#count_sort").val(Rv);
	   $("#sort_add"+Rv).show();
	   $("#sort_remove"+Rv).show();
	   //getPersCount();
	}

	
	
	
	
	
	$("input[type='checkbox'][name='multisub']").click(function() {
	    // access properties using this keyword
	    var num=0;
	    $('#column_sub_list').empty()
	    $("thead#thead_data").empty();
	    $("input[type='checkbox'][name='multisub']").each(function () {  
	        if ( this.checked ) {   
	        	$('#column_sub_list').append("<span class='subspan'>"+this.parentElement.innerText+"<span> <br>");
	 	   		$("thead#thead_data").append("<td>"+this.parentElement.innerText+"</td>");
	        	 num=num+1;
	        }
	    });
	    if(num!=0)
	        $("#sub_column option:first").text('Fields('+num+')');
	    else
	        $("#sub_column option:first").text("Select Fields");
	});
	
	
	
	function removeSubFn(value){
		
		$("input[type='checkbox'][name='multisub'][value='" + value + "']").attr('checked', false);
		
		var num=0;
	    $('#column_sub_list').empty()
	    $("input[type='checkbox'][name='multisub']").each(function () {  
	        if ( this.checked ) {   
	        	
	        	$('#column_sub_list').append("<span class='subspan'>"+this.parentElement.innerText+"<span> <br>");
	            num=num+1;
	        }
	    });
	    if(num!=0)
	        $("#sub_column option:first").text('Fields('+num+')');
	    else
	        $("#sub_column option:first").text("Select Fields");
	}
	

	
	 function Coulmn_Name_Filter(a) {
		 
		 alert("ttt");
			var f = $("#multisub_filter"+a).val();
			var nt= f.includes("date");
		  	
               alert(nt);
	      
			$("#ne_value"+a).val("");
			$("#value_dt"+a).val("");
			
			if(nt==true){
		        alert("hi");
				$("td#value_date"+a).show();
				$("td#value"+a).hide();
			}
			else{
		        alert("el");
				$("td#value_date"+a).hide();
				$("td#value"+a).show();
			}
		// get_query();
	} 
	
	
	
	//var h="";
	function submit_fn() {
		 var formdata=$('#filed_form').serialize();
		 var create_query = $('#create_query').val().toLowerCase();
		 
		 var update = create_query.includes("update");
		 var delete1 = create_query.includes("delete");
		 var drop = create_query.includes("drop");
		
		 
		   if ($("input[type = checkbox][name = 'multisub_table']:checked").length == 0) {
             alert("Please Select Details with Aliase");
             return false;
                       } 
		   
		   if ($("input[type = checkbox][name = 'multisub_check']:checked").length == 0) {
	             alert("Please Select Fileds");
	             return false;
	             }  
                         /* for(var ps = 1;ps<=$("#count_filter").val();ps++){
                                         
                                                  if($("#count_filter").val()==1){
                                                          if($("#multisub_filter"+ps).val() != null){ */
//                                                                   if($("select#operator"+ps).val() == "0" ){
//                                                                           alert("Please Select Operator");
//                                                                           $("input#operator"+ps).focus();
//                                                                           return false;
//                                                                   }  
                                                                  /* if($("input#value"+ps).val() == "" ){
                                                                      alert("Please Enter Value");
                                                                      $("input#value"+ps).focus();
                                                                       return false;
                                                      
                                                               }    */
                                                  /*         }
                                                  }
                                                  else {
                                                          if($("select#multisub_filter"+ps).val() == null || $("select#multisub_filter"+ps).val() == "0"){
                                                                  alert("Please Select Filed Name");
                                                                  $("input#multisub_filter"+ps).focus();
                                                                  return false;
                                                          }
                                                          if($("#multisub_filter"+ps).val() !="" || $("#multisub_filter"+ps).val() != null){ */
//                                                                   if($("select#operator"+ps).val() == "0" ){
//                                                                           alert("Please Select Operator");
//                                                                           $("input#operator"+ps).focus();
//                                                                           return false;
//                                                                   }
                                                                  
                                                                 /*  if($("input#value"+ps).val() == "" ){
                                                                      alert("Please Enter Value");
                                                                      $("input#value"+ps).focus();
                                                                       return false;
                                                      
                                                               }    
                                                                   */
                                                      /*     }
                                                  
                                                  }
                                          } */
                                          
                                          
                                          
		   if($("#count_filter").val() != 0){
			  for(var ps = 1;ps<=$("#count_filter").val();ps++){
	               
				   
	             	  
	             	    if($("#hd_Filed_List"+ps).val() == ""){
	                       alert("Please Select Filed Name");
	                       $("input#multisub_filter"+ps).focus();
	                       return false;
	               }  
	             	      if($("select#multisub_filter"+ps).val() != null && $("select#operator"+ps).val() =="0"){
	                         alert("Please Select Operator");
	                         $("input#operator"+ps).focus();
	                         return false;
	                 }   
	             	      
	             	     var f = $("#multisub_filter"+ps).val();
	             	     
	             	     if(f!=null){
	             	    	var np= f.includes("date");
	             		  	
	               	      if(np == true){
	               	    	 if($("select#multisub_filter"+ps).val() != null && $("input#value_dt"+ps).val() == "" && $("select#operator"+ps).val() != "between"){
	               	    	 alert("Please Select Value of Date");
	                           $("input#value_dt"+ps).focus();
	                            return false;
	                	    	 }
	               	      }
	             	     }
	         			
	             	      else{
	             	    	 if($("select#multisub_filter"+ps).val() != null && $("input#ne_value"+ps).val() == "" && $("select#operator"+ps).val() != "between"){
	                             alert("Please Enter Value");
	                             $("input#ne_value"+ps).focus();
	                              return false;
	                             }   
	             	      }
	             	     
	             	    if($("select#operator"+ps).val()=="between"){
	            	    	 
	             	    	var ml = $("#multisub_filter"+ps).val();
	             	    	var va = ml.includes("age") || ml.includes("month") || ml.includes("year") || ml.includes("bpet_result") 
	            			|| ml.includes("bpet_year")  || ml.includes("duration") || ml.includes("firing_year") || ml.includes("foreign_language_exam_pass") 
	            			|| ml.includes("shape_status")  || ml.includes("height")  || ml.includes("year_of_service")  || ml.includes("passing_year")
	            			if(va == true){
	            				if($("input#ne_valuef"+ps).val() == ""){
	                                alert("Please Enter From Value");
	                                $("input#ne_valuef"+ps).focus();
	                                 return false;
	                         }   
	               	    	 if($("input#ne_valuet"+ps).val() == ""){
	                                alert("Please Enter To Value");
	                                $("input#ne_valuet"+ps).focus();
	                                 return false;
	                
	                         }
	            			}
	            			else{
	            				 if($("input#from_date"+ps).val() == "" || $("input#from_date"+ps).val() == "DD/MM/YYYY" ){
	                                 alert("Please Select From Date");
	                                 $("input#from_date"+ps).focus();
	                                  return false;
	                          }   
	                	    	 if($("input#to_date"+ps).val() == "" || $("input#to_date"+ps).val() == "DD/MM/YYYY" ){
	                                 alert("Please Select To Date");
	                                 $("input#to_date"+ps).focus();
	                                  return false;
	                 
	                          }
	            			}
	             	    	 
	             	       
	             	    	 }
	       }
		  } 
                     
                     
                                  for(var ps = 1;ps<=$("#count_sort").val();ps++){
                                          
                                                          if($("#count_sort").val()!=0){
                                                                  //if($("#multisub_sort"+ps).val() != null){
                                                                	  
                                                                	  if($("#hd_short_List"+ps).val() == ""){
                                                                          alert("Please Select Order");
                                                                          $("input#multisub_sort"+ps).focus();
                                                                          return false;
                                                                  }
                                                                	  
                                                                          if($("select#direction"+ps).val() == "0" ){
                                                                                  alert("Please Select Direction");
                                                                                  $("input#direction"+ps).focus();
                                                                                  return false;
                                                                          }
                                                                  //}
                                                           }
                                                       /*    else {
                                                                  if($("#hd_short_List"+ps).val() == null || $("#multisub_sort"+ps).val() == ""){
                                                                          alert("Please Select Order");
                                                                          $("input#multisub_sort"+ps).focus();
                                                                          return false;
                                                                  }
                                                                  if($("#hd_short_List"+ps).val() !="" || $("#hd_short_List"+ps).val() != null){
                                                                          if($("select#direction"+ps).val() == "0" ){
                                                                                  alert("Please Select Direction");
                                                                                  $("input#direction"+ps).focus();
                                                                                  return false;
                                                                          }
                                                                          
                                                                         
                                                                  }
                                                          }  */
                                                  }
                        
			  $.post('Ad_hoc_query_Action_mnh?' + key + "=" + value, formdata , function(j){ 
				  //console.log(j)
				  var i=0;
				  var v=j.length;
				 /*  var size = $("#size").val(v); 
				  alert("v----" + v); */
					
					 if(v==0){
						$('#show_data').hide(); 
						$('#div_result').hide(); 
						alert("Data not available");
					}
					else{
						if(j[0].msg[0]!=""){
							alert(j[0].msg[0]);
						}
						else{
							/* String re_size = "<h6 style='text-align: left;' >";
							re_size+=$("#size").val(v);
							re_size+="Records Found</h6>"; */
							
							$("#div_size").empty();
							$("#div_size").append('<h6 style="text-align: left;">'+ j.length +' Records Found</h6>');  
							$("#div_size").show();
							
							if(v!=0){
							$('#show_data').show(); 
							$('#div_result').show();
							$("tbody#td_data").empty();
							
							var r = $("#roleAccess").val();
							if( r == "Unit"){
								 $("#print_div").show();
								 $("#print_div_image").show();
								 
							}
							else  if(r == "MISO"){
								 $("#print_div").show();	
								 $("#print_div_image").show();
								 $("#btnExport").show();
							}
							
							/*  $("#print_div").show();		 
							 $("#btnExport").show(); */
							
					xaller=1;
					for(i;i<v;i++){
						xaller=xaller+1;
							var hedar =  $("#check_filed").val();
							var rowgen = '<tr id="tr_data'+xaller+'">';
							rowgen +='<td style="text-align: center;width: 5%;">'+parseInt(i+1)+'</td>';
							var hedarslist = hedar.split(',');
							
										 for(k = 0; k < hedarslist.length; k++) {
											h= hedarslist[k].split('.').pop();
											var col = j[i][h];
											
											if(col == null){
												col = "";
											}
											rowgen +='<td><label  id="allergic'+xaller+'" name="allergic'+xaller+'" class=" autocomplete" >'+col+'</label> </td>';
									} 
										rowgen +='</tr>';
			                            $("tbody#td_data").append(rowgen);  
					}
						}
					}
			  }
	                                }).fail(function(xhr, textStatus, errorThrown) {
	                       }); 
		 }
		   
	
	function fn_Operator(o) {
		//	get_query();
			var f1 = $("#operator"+o).val();
			var nt1= f1.includes("between");
			var ml = $("#multisub_filter"+o).val();
			var va = ml.includes("age") || ml.includes("month") || ml.includes("year") || ml.includes("bpet_result") 
			|| ml.includes("bpet_year")  || ml.includes("duration") || ml.includes("firing_year") || ml.includes("foreign_language_exam_pass") 
			|| ml.includes("shape_status")  || ml.includes("height")  || ml.includes("year_of_service")  || ml.includes("passing_year")
			
			if(nt1==true){
				
				if(va==true){
					
				
					$("#ne_value"+o).attr('readonly',true);
					$("#from_date"+o).attr('readonly',true);
					$("#to_date"+o).attr('readonly',true);
					$("#ne_valuef"+o).attr('readonly',false);
					$("#ne_valuet"+o).attr('readonly',false);
				}
				else{
				$("#ne_value"+o).attr('readonly',true);
				$("#value_dt"+o).attr('readonly',true);
				$("#from_date"+o).attr('readonly',false);
				$("#to_date"+o).attr('readonly',false);
				$("#ne_valuef"+o).attr('readonly',true);
				$("#ne_valuet"+o).attr('readonly',true);
				
				$("#ne_value"+o).val("");
				$("#value_dt"+o).val("");
				}
			}
			else{
				
				$("#ne_value"+o).attr('readonly',false);
				$("#value_dt"+o).attr('readonly',false);
				$("#from_date"+o).attr('readonly',true);
				$("#to_date"+o).attr('readonly',true);
				$("#ne_valuef"+o).attr('readonly',true);
				$("#ne_valuet"+o).attr('readonly',true);
				
				$("#from_date"+o).val("");
				$("#to_date"+o).val("");
				
			}
		}
	
	function Value(v) {
		 $("#div_sort").show(); 
		// get_query();
	}
	
	 function fn_AndOr(nd) {
		// get_query();
		}

	function Table_showCheckboxes(pk) {
		
// 		if ($("#hd_category").val() == "") {
// 			alert("Please Select Category");
// 			$("#hd_category").focus();
// 			return false;
// 		} 
		if ($("#hd_category").val() == "civilian") {
			
			if ($("#civilian_type").val() == "0") {
				alert("Please Select Civilian Type");
				$("#civilian_type").focus();
				return false;
			} 
		} 
		  var checkboxes = document.getElementById("checkboxes");
		  $("#checkboxes_table"+pk).toggle();
		  $("#table_search"+pk).val(''); 
		  $('.table_list'+pk).each(function () {       
		  $(this).show()
		})
		
		
		}
	

	function fn_join() {
		// get_query();
	}

	
	function Table_Name() {
		
	    $('input[name="multisub_table"]').each(function () {  
	        if ( this.checked ) {  
	        	
	        	 var tw1 = this.value.toString();
	        	
	        	$('input[id="'+this.id+'"]').prop({disabled: true});
	        	
	        	divN('d_final');
	        	//get_filter();
	        	
	        }
	    });
	    $('#spouse_quali_sub_list_details').empty()
		$("input[type='checkbox'][name='multisub_table']").each(function() {
			if(this.checked) {
				$('#spouse_quali_sub_list_details').append("<span class='subspan'>" + this.parentElement.innerText + "<span> <br>");
			}
		});
		var subjectvar2 = $('input[name="multisub_table"]:checkbox:checked').map(function() {
			return this.value;
		}).get();
		
		var subject2 = subjectvar2.join(",");
		 $("#hd_table").val(subject2);
		 $("#show_data").hide();
		 $("#col_filed").show();
		 $("#button_div").show();
		 $("#col_no_total").show();
		
		 
		
		var hd_table_name = [];
		hd_table_name.push(subject2);
		$("#table_name_hidden_list").val(hd_table_name);
		
		get_filed();
		//divN('d_final');
	}
	
	function showCheckboxes() {
		  var checkboxes = document.getElementById("checkboxes");
		  $("#checkboxes").toggle();
		  $("#column_search").val(''); 
		  $('.column_list').each(function () {       
		  $(this).show()
		})
		}
	
	function Coulmn_Name() {
		var subjectvar4 = $('input[name="multisub_check"]:checkbox:checked').map(function() {
			return this.value;
		}).get();
		var subject4 = subjectvar4.join(",");
		 $("#hd_column").val(subject4);
		 $("#show_data").hide();
		 $("#div_filter").show();
		 
		 var check_filed_Name =new Array();
		 check_filed_Name.push(subject4);
	     $("#check_filed").val(check_filed_Name);
	
		    var num=0;
		    $('#column_sub_list').empty();
		    $("thead#thead_data").empty();
		    $("thead#thead_data").append("<td style='font-size: 15px ;width: 5%;'>Sr No</td>");
		    $('input[name="multisub_check"]').each(function () {  
		        if ( this.checked ) {   
		        	var text = this.parentElement.innerText;
					var h1 = text.split('.').pop();
		        	$('#column_sub_list').append("<span class='subspan'>"+this.parentElement.innerText+"<span> <br>");
		 	   		$("thead#thead_data").append("<td>"+h1+"</td>");
		        	 num=num+1;
		        }
		        
		        
		       // divN('b_final');
		    });
		    
		    $('#spouse_quali_sub_list').empty()
			$("input[type='checkbox'][name='multisub_check']").each(function() {
				if(this.checked) {
// 					$('#spouse_quali_sub_list').append("<span class='subspan'>" + this.parentElement.innerText + "<i class='fa fa-times' style='margin: 5px;  font-size: 15px;' onclick='removeSpouseSubFn(" + this.value + ")'></i><span> <br>");
					$('#spouse_quali_sub_list').append("<span class='subspan'>" + this.parentElement.innerText + "<span> <br>");
				}
			});
		    if(num!=0)
		        $("#sub_column option:first").text('Fields('+num+')');
		    else
		        $("#sub_column option:first").text("Select Fields");
		
	}
	

	
	function get_query() {
		var	query="";
		var vk = $("input#count_head").val();
			for (var nm = 1; nm <=vk; nm++) {
				var hd_column =  $("#hd_column"+nm).val();
				
				  if(nm==1){
						query+="SELECT distinct ";
					}  
				
				query+=hd_column+",";
			}
			
			var q1  = query.substring(0,query.length - 1);
			
			query= q1+" FROM";
			
			for (var ik = 1; ik <=vk; ik++) {
				var hd_table1 =  $("#hd_table"+ik).val();
				var t1 = hd_table1.split('-').shift();
				var aliase = hd_table1.split('-').pop();
				var join ="";
				if($("#join"+ik).val()!=0){
					join +=  $("#join"+ik).val();
				}
				
				if(ik > 1)
				{
					var hd_table =  $("#hd_table"+ik).val();
					var t1 = hd_table.split('-').shift();
					var aliase1 = hd_table.split('-').pop();
					
					var p = 1;
					var hd_table_p =  $("#hd_table"+p).val();
					var t1_p = hd_table_p.split('-').shift();
					var aliase_p = hd_table_p.split('-').pop();
					
					query+=" "+ t1+" "+aliase + " ON " ;
					query+=aliase1+".personal_no=";
					query+=aliase_p+".personal_no";
					if(ik!=vk){
						query+=" "+join;
					}
					
				}
				else
				{
					//query+=" "+t1+" "+aliase+" "+join + "  " ;
					query+=" "+t1+" "+aliase;
					if(ik!=vk){
						query+=" "+join;
					}
				}
			}
			
			 //var q  = query.substring(0,query.length - 1);
			 var q  = query;
			 var count_filter = $("#count_filter").val();
			 var count_sort = $("#count_sort").val();
			 
				for (var i = 1; i <= count_filter; i++) {
					var hd_column_filter = $("#hd_column_filter"+i).val();
					var operator = "";
					var value = "";
					if($("#value"+i).val()!=""){
						value+= "'"+$("#value"+i).val()+"'";
					}
					var andor = "";
					var flag=0;
					 if($("#hd_column_filter"+i).val()!=""){
						 if(i==1){
							q+= " WHERE ";
							flag=1;
						}
					 }
					
					if($("#operator"+i).val()!=0){
						operator+=$("#operator"+i).val();
					}
					
					if($("#andor"+i).val()!=-1){
						andor+=$("#andor"+i).val();
					}
					if(hd_column_filter!= "" && flag==1)
					{
						q+= "upper("+ hd_column_filter +")" + operator + "upper(" +value+")" +" " + andor + " ";
					}
					else
					{
						q+=  hd_column_filter  + operator  +value +" " + andor + " ";
					}
					
				}
				
				
				for (var n = 1; n <=count_sort; n++) {
					var hd_column_sort = $("#hd_column_sort"+n).val();
					var direction = "";
					
						if($("#hd_column_sort"+n).val()!=""){
							if(n==1){
							q+= "ORDER BY "
							}
						}
					
					if(n>1){
						q+=",";
					}
					
					if($("#direction"+n).val()!=0){
						direction+= $("#direction"+n).val();
					}
					q+= hd_column_sort + " "+ direction;
				}
				//alert("query---" + q);
			$("#create_query").val(q);
	}
	
	
	
	

	
	function add_fn(aller_srno){
		$("#andor"+aller_srno).attr('readonly',false);

		if ($("#andor"+aller_srno).val() == "-1") {
			alert("Please Select AndOr");
			$("#andor"+aller_srno).focus();
			return false;
		} 
	
		   $("#allergic_add" + aller_srno).hide();
		   $("#allergic_remove" + aller_srno).hide();

			aller_srno+=1; 
			
			 $("input#count_filter").val(aller_srno);
		
		$("table#ad_hoc_table").append('<tr id="tr_allergic'+aller_srno+'">'
				+'<td class="aller_srno">'+aller_srno+'</td>'
				+'<td>  <div class="selectBox" onclick="FiledName_show_checkBox('+aller_srno+')"><select name="multisub_filter'+aller_srno+'" id="multisub_filter'+aller_srno+'" class=" form-control">'
				+'<option>Select Category</option></select><div class="overSelect"></div></div><div id="filed_list'+aller_srno+'" class="checkboxes" style="max-height: 200px; overflow: auto;">'
				+'<div style="">'
				+'<input type="text" id="filed_search'+aller_srno+'" class="form-control autocomplete" autocomplete="off" placeholder="Search Category" onkeypress="get_filed_search('+aller_srno+');">'
				+'</div>'
				+'<div id="Filed_drop'+aller_srno+'"></div>'
				+'<input type="hidden" id="hd_Filed_List'+aller_srno+'" name="hd_Filed_List'+aller_srno+'" class="form-control autocomplete" autocomplete="off"></input></div> </td> '
				+'<td>'
				+ '<select  id="operator'+aller_srno+'" name="operator'+aller_srno+'" class="form-control-sm form-control" onchange="fn_Operator('+aller_srno+');" ><option value="0">--Select--</option><c:forEach var="item" items="${get_operator_List}" varStatus="num"><option value="${item.codevalue}" name="${item.label}">${item.label}</option></c:forEach></select></td>'
				+'<td id="value'+aller_srno+'"><input type="text" id="ne_value'+aller_srno+'" name="ne_value'+aller_srno+'" onkeyup="Value('+aller_srno+');"  maxlength="100"  class="form-control autocomplete" autocomplete="off"></td>'
				+ '<td id="value_date'+aller_srno+'" style="display: none;">'
				+' <input type="text" id="value_dt'+aller_srno+'" name="value_dt'+aller_srno+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
				+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
				+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);Value('+aller_srno+');" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">'
				+ '</td>'
				+ '<td id="td_from_date'+aller_srno+'"  >'
				+' <input type="text" id="from_date'+aller_srno+'" name="from_date'+aller_srno+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
				+' readonly	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
				+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);Value('+aller_srno+');" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">'
				+ '</td>'
				+ '<td id="td_to_date'+aller_srno+'" >'
				+' <input type="text" id="to_date'+aller_srno+'" name="to_date'+aller_srno+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
				+' readonly	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
				+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);Value('+aller_srno+');" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">'
				+ '</td>'
                +'<td id="valuef1'+aller_srno+'"><input type="text" id="ne_valuef'+aller_srno+'" name="ne_valuef'+aller_srno+'" onkeyup="Value('+aller_srno+');"  maxlength="100" readonly  class="form-control autocomplete" autocomplete="off"></td>'
				+'<td id="valuet1'+aller_srno+'"><input type="text" id="ne_valuet'+aller_srno+'" name="ne_valuet'+aller_srno+'" onkeyup="Value('+aller_srno+');"  maxlength="100"  readonly  class="form-control autocomplete" autocomplete="off"></td>'
				+'<td>'
				+ '<select name="andor'+aller_srno+'" id="andor'+aller_srno+'" class="form-control-sm form-control" onchange="fn_AndOr('+aller_srno+');" readonly ><option value="-1">--Select--</option><option value="and">And</option><option value="or">Or</option></select> </td>'
				+'<td>'
				+'<a class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "allergic_add'+aller_srno+'" onclick="add_fn('+aller_srno+');" ><i class="fa fa-plus"></i></a>'
				+'<a  class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "allergic_remove'+aller_srno+'" onclick="remove_fn('+aller_srno+');"><i class="fa fa-trash"></i></a> '
				+'</td></tr>');
		
		datepicketDate('value_dt'+aller_srno);
		datepicketDate('from_date'+aller_srno);
		datepicketDate('to_date'+aller_srno);
		
		var tn = $("#table_name_hidden_list").val();
		
		 $.post('get_selected_field_List_mnh?' + key + "=" + value,{ tn : tn },function(j) {
	        }).done(function(j) {
	        	
	          	var options = '<option   value="0">' + "--Select--"
				+ '</option>';
		for (var i = 0; i < j.length; i++) {

			options += '<option   value="' + j[i] + '" name="'+j[i]+'" >'
					+ j[i]+ '</option>';
		}

		$("select#multisub_filter"+aller_srno).html(options);
	    				   
	        }).fail(function(xhr, textStatus, errorThrown) {
	        }); 
		
	}
	
	function fn_direction(qw) {
		//$("#div_query").show();
		//get_query();
	}
	
	$(document).ready(function() {
		
		
		$.post("get_catgory_List?"+key+"="+value,function(j) {
			$("#divContainer").empty();
			
	 		for ( var i = 0; i < j.length ;i++) {
	 			
	 			$("#divContainer").append ( "<label>" + j[i].label + " </label><input type='radio' onchange=Category_Name(this); name='label' id='" + j[i].label + "' value='" + j[i].codevalue + "'  /> &nbsp;&nbsp " );
			} 
	 	});
	
		
	
		 colAdj("show_data");
		datepicketDate('value_dt1');
		datepicketDate('from_date1');
		datepicketDate('to_date1');
		
		var r =('${roleAccess}');
		
		$("#roleAccess").val(r);
		
		
		
		var date = new Date();
		var f = date.getMonth()
		var monthName;
		switch (f) {
		case 0:
			monthName = "1";
			break;
		case 1:
			monthName = "2";
			break;
		case 2:
			monthName = "3";
			break;
		case 3:
			monthName = "4";
			break;
		case 4:
			monthName = "5";
			break;
		case 5:
			monthName = "6";
			break;
		case 6:
			monthName = "7";
			break;
		case 7:
			monthName = "8";
			break;
		case 8:
			monthName = "9";
			break;
		case 9:
			monthName = "10";
			break;
		case 10:
			monthName = "11";
			break;
		case 11:
			monthName = "12";
			break;
		}
		
		
		$("#month").val(monthName);
		
		$("#year").val(date.getFullYear());
		
		//alert('${roleAccess}');
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
		
		if('${roleAccess}' == 'MISO' || '${roleAccess}' == 'HeadQuarter')
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
   		   		
   		   	$("#hd_cont_comd").val($("#cont_comd").val());
   		   	
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
   		   	
   		$("#hd_cont_comd").val($("select#cont_comd").val());
   		$("#hd_cont_corps").val($("select#cont_corps").val());
   		$("#hd_cont_div").val($("select#cont_div").val());
   		$("#hd_cont_bde").val($("select#cont_bde").val());
   		$("#hd_unit_name").val($("#unit_name").val());
   		   	
   		$('select#cont_bde').change(function() {  	
   		$("#hd_cont_bde").val($("#cont_bde").val());
   		});
	}); 
	


function get_sort() {
	
	var tn = $("#table_name_hidden_list").val();
	
	var jn = $("#check_filed").val();
	
	var hedarslist = jn.split(',');
	
	
	var f = $( "#multisub_sort1 option:selected" ).val();
		
	if ((f == "" || f == undefined) && (tn !="")){
		 
	/*  $.post('get_selected_field_List?' + key + "=" + value,{ tn : tn },function(j) {
        }).done(function(j) { */
        	
          	var options = '<option   value="0">' + "--Select--"
			+ '</option>';
	for (var i = 0; i < hedarslist.length; i++) {
		options += '<option   value="' + hedarslist[i] + '" name="'+hedarslist[i]+'" >'
				+ hedarslist[i]+ '</option>';

	}
    
	$("select#multisub_sort1").html(options);
	

	
        /* }).fail(function(xhr, textStatus, errorThrown) {
        }); */ 
	}
	

  if ((f != "" || f != undefined) && (tn !="")){
		
		
		$('select#multisub_sort1').val(f);
	}
   else
	   {
	   
		$("select#multisub_sort1").html(options);
	   }
}

	
	
function get_filed() {
	
	 var tw1 = $("#table_name_hidden_list").val();
	 var check = $("#check_filed").val();
	 var check_list = check.split(",");
	 $.ajaxSetup({
			async : false
		});
	 $("#filed1_check").empty();
		 $.post('get_selected_field_List_mnh?' + key + "=" + value,{ tn : tw1 },function(j) {
	        }).done(function(j) {
	        	for(var l=0;l<j.length;l++){
	        		/* var check_array =new Array();
	        		check_array = ["a19.rank","a19.unit_sus_no"] */
	        		//alert("arr--" + check_array.length);
	        		
	        	 	/* for (var i1 = 0; i1 < check_array.length; i1++) {
	        			
	        			// alert("j[l]---" + j[l] + "----" + "check_array[i1]----" + check_array[i1]);
	        			if(j[l]!=check_array[i1]){
	        				$("#filed1_check").append( '<label for="one" class="filedlist"><input type="checkbox" name="multisub_check" onclick="Coulmn_Name();"   id="'+j[l].replace('.', '-')+'"  value="'+j[l]+'" />'+j[l]+' </label> ' );
		        		}
		        		else{
		        			$("#filed1_check").append( '<label for="one" class="filedlist"><input type="checkbox" name="multisub_check" onclick="Coulmn_Name();"   id="'+j[l].replace('.', '-')+'"  value="'+j[l]+'" />'+j[l]+' </label> ' );
		        		}
					}  */
	        		
					if (j[l] == "a19.rank"
						|| j[l] == "a19.unit_sus_no"
						|| j[l] == "a19.month"
						|| j[l] == "a19.year"
						|| j[l] == "a20.rank"
						|| j[l] == "a20.unit_sus_no"
						|| j[l] == "a20.month"
						|| j[l] == "a20.year"
						|| j[l] == "a2.personal_no"
						|| j[l] == "a3.personal_no"
						|| j[l] == "a4.personal_no"
						|| j[l] == "a5.personal_no"
						|| j[l] == "a6.personal_no"
						|| j[l] == "a7.personal_no"
						|| j[l] == "a8.personal_no"
						|| j[l] == "a9.personal_no"
						|| j[l] == "a10.personal_no"
						|| j[l] == "a11.personal_no"
						|| j[l] == "a12.personal_no"
						|| j[l] == "a13.personal_no"
						|| j[l] == "a14.personal_no"
						|| j[l] == "a15.personal_no"
						|| j[l] == "a16.personal_no"
						|| j[l] == "a18.personal_no"
						|| j[l] == "a19.personal_no"
						|| j[l] == "a20.personal_no"
						|| j[l] == "a1.month"
						|| j[l] == "a2.month"
						|| j[l] == "a3.month"
						|| j[l] == "a4.month"
						|| j[l] == "a5.month"
						|| j[l] == "a6.month"
						|| j[l] == "a7.month"
						|| j[l] == "a8.month"
						|| j[l] == "a9.month"
						|| j[l] == "a10.month"
						|| j[l] == "a11.month"
						|| j[l] == "a12.month"
						|| j[l] == "a13.month"
						|| j[l] == "a14.month"
						|| j[l] == "a15.month"
						|| j[l] == "a16.month"
						|| j[l] == "a18.month"
						|| j[l] == "a19.month"
						|| j[l] == "a20.month"
						|| j[l] == "a1.year"
						|| j[l] == "a2.year"
						|| j[l] == "a3.year"
						|| j[l] == "a4.year"
						|| j[l] == "a5.year"
						|| j[l] == "a6.year"
						|| j[l] == "a7.year"
						|| j[l] == "a8.year"
						|| j[l] == "a9.year"
						|| j[l] == "a10.year"
						|| j[l] == "a11.year"
						|| j[l] == "a12.year"
						|| j[l] == "a13.year"
						|| j[l] == "a14.year"
						|| j[l] == "a15.year"
						|| j[l] == "a16.year"
						|| j[l] == "a18.year"
						|| j[l] == "a19.year"
						|| j[l] == "a20.year"
						|| j[l] == "a22.army_no"
						|| j[l] == "a23.army_no"
						|| j[l] == "a24.army_no"
						|| j[l] == "a25.army_no"
						|| j[l] == "a26.army_no"
						|| j[l] == "a27.army_no"
						|| j[l] == "a28.army_no"
						|| j[l] == "a29.army_no"
						|| j[l] == "a30.army_no"
						|| j[l] == "a31.army_no"
						|| j[l] == "a32.army_no"
						|| j[l] == "a33.army_no"
						|| j[l] == "a34.army_no"
						|| j[l] == "a35.army_no"
						|| j[l] == "a36.army_no"
						|| j[l] == "a37.army_no"
						|| j[l] == "a38.army_no"
						|| j[l] == "a39.army_no"
						|| j[l] == "a22.month"
						|| j[l] == "a23.month"
						|| j[l] == "a24.month"
						|| j[l] == "a25.month"
						|| j[l] == "a26.month"
						|| j[l] == "a27.month"
						|| j[l] == "a28.month"
						|| j[l] == "a29.month"
						|| j[l] == "a30.month"
						|| j[l] == "a31.month"
						|| j[l] == "a32.month"
						|| j[l] == "a33.month"
						|| j[l] == "a34.month"
						|| j[l] == "a35.month"
						|| j[l] == "a36.month"
						|| j[l] == "a37.month"
						|| j[l] == "a38.month"
						|| j[l] == "a39.month"
						|| j[l] == "a22.year"
						|| j[l] == "a23.year"
						|| j[l] == "a24.year"
						|| j[l] == "a25.year"
						|| j[l] == "a26.year"
						|| j[l] == "a27.year"
						|| j[l] == "a28.year"
						|| j[l] == "a29.year"
						|| j[l] == "a30.year"
						|| j[l] == "a31.year"
						|| j[l] == "a32.year"
						|| j[l] == "a33.year"
						|| j[l] == "a34.year"
						|| j[l] == "a35.year"
						|| j[l] == "a36.year"
						|| j[l] == "a37.year"
						|| j[l] == "a38.year"
						|| j[l] == "a39.year"
						|| j[l] == "c1.month"
						|| j[l] == "c1.year") {
	        			$("#filed1_check").append( '<label for="one" class="filedlist"> </label> ');
	        		}
	        		else{
	        			$("#filed1_check").append( '<label for="one" class="filedlist"><input type="checkbox" name="multisub_check" onclick="Coulmn_Name();"   id="'+j[l].replace('.', '-')+'"  value="'+j[l]+'" />'+j[l]+' </label> ' );
	        		} 
				}
	        }).fail(function(xhr, textStatus, errorThrown) {
	        }); 
		 
		 for(var lm=0;lm<check_list.length;lm++){
				$("#"+check_list[lm].replace('.', '-')).attr('checked', true);
			}
}
	
function isNumber0_9Only(evt) {
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if(charCode != 46 && charCode > 31 && (charCode<48 || charCode>57)){
		return false;
	}else{
		if(charCode == 46){
			return false;
		}
	}
	return true;
}


$("#table_search1").keyup(function () {
	  var re = new RegExp($(this).val(), "i")
	  $('.table_list').each(function () {
	      var text = $(this).text(),
	          matches = !! text.match(re);
	      $(this).toggle(matches)
	  })
	});
	
	
$("#column_search").keyup(function () {
	  var re = new RegExp($(this).val(), "i")
	  $('.filedlist').each(function () {
	      var text = $(this).text(),
	          matches = !! text.match(re);
	      $(this).toggle(matches)
	  })
	});
	
	
	//// No of Persons
	
	function get_selectColumn1() {
		
		var v = $("input#count_head").val();
		if (v != 0) {
			$('#total_tbody').empty();
			for (var i=1; i <= v; i++) {
				var x = 1;
				
				
	           var rowgen = '<tr id="tr_total'+x+'">';
	                         //rowgen +='<td style="width: 10%;text-align: center;">'+x+'</td>';
							 rowgen +='<td>';
							 rowgen += '<div class="selectBox" onclick="total_showCheckboxes('+ x+ ')">';
							 rowgen += '<select name="total_column" id="total_column"';
							 rowgen += '	class=" form-control">';
							 rowgen += '	<option>Select Fields</option>';
							 rowgen += '</select>';
							 rowgen += '	<div class="overSelect"></div>';
							 rowgen += '</div>';
							 rowgen += '<div id="total_checkboxes'+x+'" class="checkboxes"';
							 rowgen += 'style="max-height: 200px; overflow: auto;">';
							 rowgen += '<div style="">';
							 rowgen += '	<input type="text" id="total_search'+x+'"';
							 rowgen += '	class="form-control autocomplete" autocomplete="off"';
							 rowgen += '	placeholder="Search Fields">';
							 rowgen += '</div>';
							 rowgen += '<div id = "total_check"></div> ';
							 rowgen += '<input type="hidden" id="hd_no'+x+'" name="hd_no'+x+'" class="form-control autocomplete" autocomplete="off"></input>';
							 rowgen += '</div>';
							 rowgen += '</td>';
							 rowgen +='</tr>'; 
							 
                             $("tbody#total_tbody").append(rowgen);  
				
                        	 $.ajaxSetup({
								 async : false
							 });
						
				
			}
		}
}
	
	
	function total_showCheckboxes() {
		  var checkboxes = document.getElementById("total_checkboxes");
		  $("#total_checkboxes").toggle();
		  $("#total_search").val(''); 
		  $('.total_list').each(function () {       
		  $(this).show()
		})
		}
	
	
	function get_total() {
		 var tw1 = $("#table_name_hidden_list").val();
		
			 $.post('get_group_by_List?' + key + "=" + value,function(j) {
		        }).done(function(j) {
		        	for(var l=0;l<j.length;l++){
						$("#total_check").append( '<label for="one" class="total_list"><input type="checkbox" name="multisub_check_total" onclick="Coulmn_Name_Total();"  value="'+j[l].codevalue+'" />'+j[l].label+' </label><br/>' );
					}
		        }).fail(function(xhr, textStatus, errorThrown) {
		        }); 
	}
	
	
	$("#total_search").keyup(function () {
		  var re = new RegExp($(this).val(), "i")
		  $('.total_list').each(function () {
		      var text = $(this).text(),
		          matches = !! text.match(re);
		      $(this).toggle(matches)
		  })
		});
	
	
	
	
	function Coulmn_Name_Total() {
		var subjectvar4 = $('input[name="multisub_check_total"]:checkbox:checked').map(function() {
			return this.value;
		}).get();
		var subject4 = subjectvar4.join(",");
		 $("#hd_total").val(subject4);
		 $("#show_data").hide();
		 $("#div_filter").show();
		 
		 
		 var check_filed_Name =new Array();
		 check_filed_Name.push(subject4);
	     $("#check_total").val(check_filed_Name);
		   var num=0;
		    $('#column_sub_list').empty()
		    $("thead#thead_data").empty();
		    $('input[name="multisub_check_total"]').each(function () {  
		        if ( this.checked ) {   
		        	var text = this.parentElement.innerText;
					var h1 = text.split('.').pop();
		        	$('#column_sub_list').append("<span class='subspan'>"+this.parentElement.innerText+"<span> <br>");
		 	   		$("thead#thead_data").append("<td>"+h1+"</td>");
		        	 num=num+1;
		        }
		    });
		    if(num!=0)
		        $("#total_column option:first").text('Fields('+num+')');
		    else
		        $("#total_column option:first").text("Select Fields");
		 
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
   	   	
  
		
		function get_filed_search(ind) {
			
			  var re = new RegExp($("#filed_search"+ind).val(), "i")
			  

			  $('.filed_list_class'+ind).each(function () {
			      var text = $(this).text(),
			          matches = !! text.match(re);
			      $(this).toggle(matches)
			  })
		}
		
		function Category_showCheckboxes(pk) {
			  var checkboxes = document.getElementById("checkboxes");
			  $("#checkboxes_category"+pk).toggle();
			  $("#category_search"+pk).val(''); 
			  $('.category_list'+pk).each(function () {       
			  $(this).show()
			})
			}
		function setFilterFild(e,pk) {
			
			var f=e.textContent;
			$("#hd_Filed_List"+pk).val(f);
			$("#multisub_filter"+pk).empty();
			$("#multisub_filter"+pk).append('<option>'+f+'</option>');
		}
		
		
		function FiledName_show_checkBox(pk) {
			  var checkboxes = document.getElementById("checkboxes");
			  $("#filed_list"+pk).toggle();
			  $("#filed_search"+pk).val(''); 
			  
				
				var tn = $("#table_name_hidden_list").val();
				
				var f = $( "#multisub_filter1 option:selected" ).val();
					
				if ((f == "" || f == undefined) || (tn !="")){
					 
				 $.post('get_selected_field_List_mnh?' + key + "=" + value,{ tn : tn },function(j) {
					
			        }).done(function(j) {
			        	
			          	var options = '';
				for (var i = 0; i < j.length; i++) {
					options += '<label  class="filed_list_class'+pk+'" onclick="setFilterFild(this,'+pk+')">'+j[i]+'</label>';
				}
		       
				$("#Filed_drop"+pk).html(options);
			        }).fail(function(xhr, textStatus, errorThrown) {
			        }); 
				}
			}
		
		
		function Category_Name(obj) {
			
			$("#hd_category").val(obj.value);
			//var subjectvar21 = $('input[name="multisub_category"]:checkbox:checked').map(function() {
				/* var subjectvar21 = $('input[name="multisub_category"]:checked').map(function() {
				return this.value;
			}).get(); */
			
			var subject21 = obj.value;
			//$("#hd_category").val(subject21);
			
			if(obj.value == "civilian")
				{
				$("#civilian_type_hid").show();
				}
			
			var hd_category_name = [];
			hd_category_name.push(subject21);
			$("#category_hidden_list").val(hd_category_name);
			
			var t11 = $("#category_hidden_list").val();
			
			 var check = $("#hd_table").val();
			 var check_list = check.split(",");
			
			  $("#table_div").empty();
			  $.post('get_categorywise_tabel_List_mnh?' + key + "=" + value,{ t11 : subject21 },function(j) {
		        }).done(function(j) {
		        	
		        	// alert("ss-"+j);
		        	for(var l1=0;l1<j.length;l1++){
						//$("#table_div").append( '<label for="one" class="table_list"><input type="checkbox" name="multisub_table" onclick="Table_Name();"  value="'+j[l1][0]+'" />'+j[l1][1]+' </label>' );
						$("#table_div").append( '<label for="one" class="table_list"><input type="checkbox" name="multisub_table" onclick="Table_Name();"   id="'+j[l1][0].replace('.', '-')+'"  value="'+j[l1][0]+'" />'+j[l1][1]+' </label> ' );
		        	}
		        }).fail(function(xhr, textStatus, errorThrown) {
		        });  
			  
			  
			  for(var lm=0;lm<check_list.length;lm++){
					$("#"+check_list[lm].replace('.', '-')).attr('checked', true);
					//$("#ne_value"+o).attr('readonly',true);
				}
		}
		
		
		$("#category_search1").keyup(function () {
			  var re = new RegExp($(this).val(), "i")
			  $('.category_list').each(function () {
			      var text = $(this).text(),
			          matches = !! text.match(re);
			      $(this).toggle(matches)
			  })
			});
	
	
	
	//------------------------------HET---------------------------------------------------
	function printDataAppend(){
		var header_Intialize = Array();
		var header = $("#check_filed").val();
		header_Intialize = header.split(',');
		header = "";
		for(var i = 0 ; i < header_Intialize.length ; i++){
			header_Intialize[i] = header_Intialize[i].split('.').pop();
		}
		
var formdata=$('#filed_form').serialize();
		$.post('Download_Adhoc_mnh_qry_Serialize?' + key + "=" + value,formdata,function(j) {
        }).done(function(j) {
        	if(j == "SET"){
        	$("input#header1").val(header_Intialize);

        	document.getElementById('Download_Adhoc_Data_FORM').submit();
        	}
        }).fail(function(xhr, textStatus, errorThrown) {
        }); 

		
		
	}
	
	//------------------------------HET---------------------------------------------------
	function getExcel() {
		var header_Intialize = Array();
		var header = $("#check_filed").val();
		header_Intialize = header.split(',');
		header = "";
		for(var i = 0 ; i < header_Intialize.length ; i++){
			header_Intialize[i] = header_Intialize[i].split('.').pop();
		}
		
		var formdata=$('#filed_form').serialize();
		$.post('Download_Adhoc_mnh_qry_Serialize?' + key + "=" + value,formdata,function(j) {
        }).done(function(j) {
        	if(j == "SET"){
        		$("input#header2").val(header_Intialize);
        		document.getElementById('ExcelForm').submit();
        	}
        }).fail(function(xhr, textStatus, errorThrown) {
        }); 
    	
	}
	//------------------------------Dipal---------------------------------------------------

	
	
	$("#filter_filed_search").keyup(function () {
	  var re = new RegExp($(this).val(), "i")
	  $('.filter_filed_search_list').each(function () {
	      var text = $(this).text(),
	          matches = !! text.match(re);
	      $(this).toggle(matches)
	  })
	});
	
	
	
	$(function() {
		$("#unit_name").keypress(function(){
			var unit_name = this.value;
				 var susNoAuto=$("#unit_name");
				  susNoAuto.autocomplete({
				      source: function( request, response ) {
				        $.ajax({
				        type: 'POST',
				        url: "getUnitsNameActiveList?"+key+"="+value,
				        data: {unit_name:unit_name},
				          success: function( data ) {
				        	  var susval = [];
				        	  var length = data.length-1;
				        	  var enc = "";
					        	if(data.length != 0){
					        		enc = data[length].substring(0,16);
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
				        	  alert("Please Enter Approved Unit Name");
				        	  document.getElementById("sus_no").value="";
				        	  susNoAuto.val("");	        	  
				        	  susNoAuto.focus();
				        	  return false;	             
				          }   	         
				      }, 
				      select: function( event, ui ) {
					     
					      	var unit_name = ui.item.value;	
					      	$("#hd_unit_name").val(unit_name);
							 $.post("getActiveSusNoFromUnitName?"+key+"="+value, {unit_name:unit_name}, function(j) {
				                
				         }).done(function(j) {
				        	var length = j.length-1;
				         	var enc = j[length][0].substring(0,16);
				   			$("#sus_no").val(dec(enc,j[0][0]));
				                 
				         }).fail(function(xhr, textStatus, errorThrown) {
				         });
					      	
					     }
				      /* select: function( event, ui ) {
				      	$(this).val(ui.item.value);
				        getOrbatDetailsFromUnitName($(this).val());
				  	} 	 */     
				});
			}); 
		
		 // Source Sus No auto
		$("input#sus_no").keyup(function(){
			var sus_no = this.value;
			var unitNameAuto=$("#sus_no");
			unitNameAuto.autocomplete({
			      source: function( request, response ) {
			        $.ajax({
			        type: 'POST',
			        url: "getSusNoActiveList?"+key+"="+value,
			        data: {sus_no:sus_no},
			          success: function( data ) {
			        	  var susval = [];
			        	  var length = data.length-1;
			        	  var enc = "";
				        	if(data.length != 0){
				        		enc = data[length].substring(0,16);
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
			        	  alert("Please Enter Approved SUS NO");
			        	  $("#unit_name").val("");
			        	  unitNameAuto.val("");	        	  
			        	  unitNameAuto.focus();
			        	  return false;	             
			          }   	         
			      }, 
			      select: function( event, ui ) {
			      	var sus_no = ui.item.value;
			      	$.post("getActiveUnitNameFromSusNo?"+key+"="+value,{sus_no:sus_no}, function(j) {
			       		var length = j.length-1;
						var enc = j[length].substring(0,16);
				   		$("#unit_name").val(dec(enc,j[0]));
				   		$("#hd_unit_name").val(dec(enc,j[0]));
				   		//getOrbatDetailsFromUnitName(dec(enc,j[0]))
				   	});
			     }
			});
		});
	});
	
	
	
	
	
	
	function Short_show_checkBox(pk2) {
		
	
		  var checkboxes = document.getElementById("checkboxes");
		  $("#short_list"+pk2).toggle();
		  $("#short_search"+pk2).val(''); 
		  
			
			/* var tn = $("#table_name_hidden_list").val();
			
			var f = $( "#multisub_filter1 option:selected" ).val(); */
			
			
			var tn = $("#table_name_hidden_list").val();
			
			var jn = $("#check_filed").val();
			
			var hedarslist = jn.split(',');
			
				
			var f = $( "#multisub_sort1 option:selected" ).val();
			
			if ((f == "" || f == undefined) || (tn !="")){
				 
			/*  $.post('get_selected_field_List?' + key + "=" + value,{ tn : tn },function(j) {
				
		        }).done(function(j) {
		        	
		          	var options = '';
			for (var i = 0; i < j.length; i++) {
				
				

				options += '<label  class="filed_list_class'+pk+'" onclick="setFilterFild(this,'+pk+')">'+j[i]+'</label>';
		
			}
	       
			$("#Filed_drop"+pk).html(options); */
			
			
			var options = '';
			
			
// 			var options = '<option   value="0">' + "--Select--"
// 			+ '</option>';
	for (var i = 0; i < hedarslist.length; i++) {
		
		options += '<label  class="short_list_class'+pk2+'" onclick="setShortFild(this,'+pk2+')">'+hedarslist[i]+'</label>';

	}
    
	$("#short_drop"+pk2).html(options);
			
		
			
		       /*  }).fail(function(xhr, textStatus, errorThrown) {
		        	
		        });  */
			}
			

		}
	
	
	
	function setShortFild(e1,pk1) {
			
			var f=e1.textContent;
			$("#hd_short_List"+pk1).val(f);
			$("#multisub_sort"+pk1).empty();
			$("#multisub_sort"+pk1).append('<option>'+f+'</option>');
		}
	
	
	function get_sort_search(ind1) {
		
		  var re = new RegExp($("#short_search"+ind1).val(), "i")
		  

		  $('.short_list_class'+ind1).each(function () {
		      var text = $(this).text(),
		          matches = !! text.match(re);
		      $(this).toggle(matches)
		  })
	}
	

	
	</script>
	<c:url value="Download_Adhoc_mnh_Data" var="Download_Adhoc_mnh_Data" />
    <form:form action="${Download_Adhoc_mnh_Data}" method="post" id="Download_Adhoc_Data_FORM"
	name="Download_Adhoc_Data_FORM">
	<input type="hidden" name="header1" id="header1" value="" />
	
</form:form>
<c:url value="Download_Adhoc_mnh_qry_Excel" var="Download_Adhoc_mnh_qry_Excel" />
<form:form action="${Download_Adhoc_mnh_qry_Excel}" method="post" id="ExcelForm" name="ExcelForm">
	<input type="hidden" name="header2" id="header2" value="" />
</form:form>