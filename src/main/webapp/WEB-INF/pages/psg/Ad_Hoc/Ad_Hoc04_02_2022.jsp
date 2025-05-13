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

<!-- <link rel="stylesheet" href="js/assets/css/bootstrap.min.css"> -->
<link rel="stylesheet" href="js/layout/css/animation.css">
<!-- <link rel="stylesheet" href="js/assets/scss/style.css">  -->
<link rel="stylesheet" href="js/assets/collapse/collapse.css">

<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
<!-- <script src="js/common/commonmethod.js" type="text/javascript"></script> -->

<!-- resizable_col -->
<link rel="stylesheet" href="js/assets/adjestable_Col/jquery.resizableColumns.css">
<script src="js/assets/adjestable_Col/jquery.resizableColumns.js" type="text/javascript"></script>

 
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
			<div class="card">
				<div class="card-body card-block">
					<div class="col-md-12">
					<%-- 	<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;"></strong>Category</label>
								</div>
								<div class="col-md-8">
               					   <select name="service_category" id="service_category"  class="form-control">											
								    <option value="">--Select--</option>
			                                    <c:forEach var="item" items="${getServiceCategoryList}" varStatus="num" >
	                  								<option value="${item[0]}"  name="${item[1]}" >${item[1]}</option>
	                  							</c:forEach>
								  </select>
								</div>
							</div>
						</div> --%>
        			<!-- 		<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;"></strong>Select Formation</label>
								</div>
								<div class="col-md-8">
								<select id="formation" name="formation" class="form-control" >
               						<option value="0">---SELECT---</option>
									<option value="Command">Command</option>
								    <option value="Corps">Corps</option>
								    <option value="Div">Div</option>
								    <option value="Brigade">Brigade</option>
								    <option value="Unit">Unit</option>
               					</select>
								</div>
							</div>
						</div> -->
						
						
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;"></strong>Category</label>
								</div>
								<div class="col-md-8">
               					   <div class="selectBox" onclick="Category_showCheckboxes(1)">
										<select name="sub_category" id="sub_category"
											class=" form-control">
											<option>Select Category</option>
										</select>
										<div class="overSelect"></div>
									</div>
										<div id="checkboxes_category1" class="checkboxes"
										style="max-height: 200px; overflow: auto;">
										<div style="">
											<input type="text" id="category_search1"
												class="form-control autocomplete" autocomplete="off"
												placeholder="Search Category">
										</div>
										<div>
											<c:forEach var="item" items="${get_catgory_List}" varStatus="num">
												<label for="one" class="category_list"> <input onclick="Category_Name();"
													type="checkbox" name="multisub_category" value="${item.codevalue}" id = "${item.codevalue}-a1"/>
													${item.label}
												</label>
											</c:forEach>
										</div>
										<input type="text" id="hd_category" name="hd_category" class="form-control autocomplete" autocomplete="off"></input>
									</div>
						</div>
								</div>
							</div>
						</div>
						
						
        			<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
								    	<div class="col col-md-4">
								        	<label class=" form-control-label">Cont Comd </label>
								       	</div>
								        <div class="col-12 col-md-8">
								            <select id="cont_comd" name="cont_comd" class="form-control" >
									            <option value="">--Select--</option>
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
			                  				<label class=" form-control-label">Cont Corps</label>
			               				</div>
			                			<div class="col-12 col-md-8">
			                			
			                 				<select id="cont_corps" name="cont_corps" class="form-control" >
			                 				${select}
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
						                 	${select}</select>
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
		                 					${select}
		                 					</select>
		                				</div>
					            	</div>
								</div>
							</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;"></strong>Month</label>
								</div>
								<div class="col-md-8">
									<select name="month" id="month" class="form-control">
									<option value="0">---SELECT---</option>
									<option value="1">January</option>
								    <option value="2">February</option>
								    <option value="3">March</option>
								    <option value="4">April</option>
								    <option value="5">May</option>
								    <option value="6">June</option>
								    <option value="7">July</option>
								    <option value="8">August</option>
								    <option value="9">September</option>
								    <option value="10">October</option>
								    <option value="11">November</option>
								    <option value="12">December</option>
									</select>
								</div>
							</div>
						</div>
						
							<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;"></strong>Year</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="year" name="year" class="form-control autocomplete" autocomplete="off"
									maxlength="4"  onkeypress="return isNumber0_9Only(event)" />
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	 </div> 
	
	
		<table id="head_table" class="table-bordered" style="margin-top: 10px; width: 100%;">
								<thead style="color: white; text-align: center;">
									<tr>
										<th><strong style="color: red;"> </strong>Details with Aliase</th>
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
										<div>
											<c:forEach var="item" items="${get_table_name_List}" varStatus="num">
												<label for="one" class="table_list"> <input onclick="Table_Name();" 
													type="checkbox" name="multisub_table" value="${item.codevalue}" id = "${item.codevalue}"/>
													${item.label}
												</label>
											</c:forEach>
										</div>
											<input type="text" id="hd_table" name="hd_table" class="form-control autocomplete" autocomplete="off"></input>
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
								><b>FILEDS</b></a>
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
										<th><strong style="color: red;"> </strong>Filed</th>
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
											<input type="text" id="hd_column" name="hd_column" class="form-control autocomplete" autocomplete="off"></input>
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
	
	
			 	<!-- <div class="col-md-12"  id="col_no_total" style="display: none;">
		<div class="card">
			<div class="panel-group" id="accordion99">
				<div class="panel panel-default" id="e_div1">
					<div class="panel-heading">
						
						<h4 class="panel-title main_title blue" id="e_div5">
							<a class="droparrow collapsed" data-toggle="collapse" 
								data-parent="#accordion8" href="#" id="e_final" onclick="divN(this)"
								><b>No Of Person/Officers</b></a>
						</h4>
						
					</div>
					<div id="collapse1e" class="panel-collapse collapse">
					<div class="card-body card-block">
						<div align="left">
						</div>
								<div class="card-body-header">
									<h5></h5>
								</div>
								<table id="total_table" class="table-bordered" style="margin-top: 10px; width: 100%;">
								<thead style="color: white; text-align: center;">
									<tr>
										<th style="width: 10%;">Sr No</th>
										<th><strong style="color: red;"> </strong>No Of Person/Officers</th>
									</tr>
								</thead>
								
								
									<tbody data-spy="scroll" id="total_tbody" style="min-height: 46px; max-height: 650px; text-align: center;">
									<tr id="tr_total">
												<td>    
										<div class="selectBox" onclick="total_showCheckboxes()">
										<select name="total_column" id="total_column"
											class=" form-control">
											<option>Select Details</option>
										</select>
										<div class="overSelect"></div>
									</div>
										<div id="total_checkboxes" class="checkboxes" 
										style="max-height: 200px; overflow: auto;">
										<div style="">
											<input type="text" id="total_search"
												class="form-control autocomplete" autocomplete="off"
												placeholder="No of Total Column Search Details">
										</div>
										<div id = "total_check">
										</div>
											<input type="text" id="hd_total" name="hd_total" class="form-control autocomplete" autocomplete="off"></input>
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
	</div>	 -->
	
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
							<th id = "from_dt" style="display: none;">From Date</th> 
							<th id = "to_dt" style="display: none;">To Date</th> 
							<th>AndOr</th> 
							<th>Action</th>
					   </tr>
					</thead>
				   		<tbody data-spy="scroll" id="allergictbody" style="min-height: 46px; max-height: 650px; text-align: center;"> 
							
							 <tr id="tr_allergic1">
								<td class="aller_srno">1</td>
								<td>    
									
												<select id="multisub_filter1" name="multisub_filter1" class="form-control" onchange="Coulmn_Name_Filter('1');">
										        </select>
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
											<input type="text" id="value1" name="value1" maxlength="100"   onkeyup="Value(1);"
									class="form-control autocomplete" autocomplete="off">
								</td>
								
							<td id="value_date1" style="display: none;">
								<input type="text" name="value_dt1" id="value_dt1" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);Value(1);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
								</td>
								
								<td id="td_from_date1" style="display: none;">
								<input type="text" name="from_date1" id="from_date1" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);Value(1);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
								</td>
								
								<td id="td_to_date1" style="display: none;">
								<input type="text" name="to_date1" id="to_date1" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);Value(1);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
								</td>
								
								<td> <select name="andor1" id="andor1" class="form-control-sm form-control" onchange="fn_AndOr(1);" > 
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
									<select id="multisub_sort1" name="multisub_sort1" class="form-control">
										</select>
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
					<input type="hidden" id="count_filter" name="count_filter" value="1">
					<input type="hidden" id="count_sort" name="count_sort" value="1">
					<input type="hidden" id="count_head" name="count_head" value="1">
					<input type="hidden" id="check_filed" name="check_filed" >
					<input type="hidden" id="table_name_hidden_list" name="table_name_hidden_list" >
	                <input type="hidden" id="category_hidden_list" name="category_hidden_list" >
				</div>
			</div>
	</div>
	
	
	
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
									<strong style="color: red;">  </strong><label class=" form-control-label"> Query </label>
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
	
		<div class="col-md-12" id="button_div" > 
				<div class="card-footer" align="right" class="form-control">
				    <a href="ad_hoc_Url" class="btn btn-success btn-sm">Clear</a>  
					<input type="button" id="mrgqualibtn_save" class="btn btn-primary btn-sm" value="Genrate Result" onclick="return submit_fn();" >		              
				   <i class="action_icons action_download"></i><input type="button"  class="btn btn-primary btn-sm" value="Print" onclick="downloaddata()" >
				   <i class="fa fa-file-excel-o" id="btnExport" style="font-size: x-large; color: green; text-align: right;" aria-hidden="true" title="EXPORT TO EXCEL" onclick="getExcel();"></i>
				</div>
		</div>
	
		<div class="col-md-12">
			<table id="show_data" class="display table no-margin table-striped  table-hover table-bordered " style="display: none;">
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
				get_filter();
			}
		}
		
		if (obj.id == "c_final") {
			if(!hasC){
				//get_query();
			}
		}
		
		if (obj.id == "d_final") {
			if(!hasC){
				get_filed();
			}
		}
		
		if (obj.id == "b_final") {
			if(!hasC){
				get_sort();
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
	   //getPersCount();
	}
	
	
	
function add_fn_sort(x){
	
	
	if ($("#direction"+x).val() == "0") {
		alert("Please Select Direction");
		$("#direction"+x).focus();
		return false;
	} 
	

	   $("#sort_add" + x).hide();
	   $("#sort_remove" + x).hide();

		x+=1; 
		
		 $("input#count_sort").val(x);
	
	$("table#sort_table").append('<tr id="tr_sort'+x+'">'
			+'<td class="sort_srno">'+x+'</td>'
			+'<td><select id="multisub_sort'+x+'" name="multisub_sort'+x+'" class="form-control"></select>'
			+ '</td>'
			+'<td>'
			+ '<select name="direction'+x+'" id="direction'+x+'" class="form-control-sm form-control" onchange="fn_direction('+x+');" ><option value="0">--Select--</option><option value="Asc">Asc</option><option value="Desc">Desc</option></select> </td>'
			+'<td>'
			+'<a class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "sort_add'+x+'" onclick="add_fn_sort('+x+');" ><i class="fa fa-plus"></i></a>'
			+'<a  class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "sort_remove'+x+'" onclick="remove_fn_sort('+x+');"><i class="fa fa-trash"></i></a> '
			+'</td></tr>');
	
	var tn = $("#table_name_hidden_list").val();

	 $.post('get_selected_field_List?' + key + "=" + value,{ tn : tn },function(j) {
     }).done(function(j) {
     	
       	var options = '<option   value="0">' + "--Select--"
			+ '</option>';
	for (var i = 0; i < j.length; i++) {

		options += '<option   value="' + j[i] + '" name="'+j[i]+'" >'
				+ j[i]+ '</option>';
	}

	$("select#multisub_sort"+x).html(options);
	
     }).fail(function(xhr, textStatus, errorThrown) {
     }); 
	
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
	
	
	/* function showCheckboxes_Filter(n) {
		  var checkboxes = document.getElementById("checkboxes");
		  $("#checkboxes_filter"+n).toggle();
		  $("#column_search_filter"+n).val(''); 
		  $('.column_list_filter'+n).each(function () {       
		  $(this).show()
		})
		
		  $("#column_search_filter"+n).val(''); 
		  $("#check_filter"+n).empty(); 
		  
		   var hedar1 =  $("#check_filed").val();
		   var hedarslist = hedar1.split(',');
						 for(k = 0; k < hedarslist.length; k++) {
							h= hedarslist[k];
							var col = h;
							$("#check_filter"+n).append( '<label class="dropdown-option"><input type="checkbox" name="multisub_filter'+n+'" onclick="Coulmn_Name_Filter('+n+');"  value="'+col+'" />'+col+' </label><br/>' );
						 }  
		} */
	
	 function Coulmn_Name_Filter(a) {
			var f = $("#multisub_filter"+a).val();
			var nt= f.includes("date");
			if(nt==true){
				$("td#value_date"+a).show();
				$("td#value"+a).hide();
			}
			else{
				$("td#value_date"+a).hide();
				$("td#value"+a).show();
			}
		// get_query();
	} 
	
	
	/* function showCheckboxes_Sort(nk) {
		  var checkboxes = document.getElementById("checkboxes");
		  $("#checkboxes_sort"+nk).toggle();
		  $("#column_search_sort"+nk).val(''); 
		  $('.column_list_sort'+nk).each(function () {       
		  $(this).show()
		})
		
		$("#check_filter_sort"+nk).empty();
		var hedar1 =  $("#check_filed").val();
		var hedarslist = hedar1.split(',');
						 for(k = 0; k < hedarslist.length; k++) {
							h= hedarslist[k];
							var col = h;
							$("#check_filter_sort"+nk).append( '<label class="dropdown-option"><input type="checkbox" name="multisub_sort'+nk+'" onclick="Coulmn_Name_Sort('+nk+');"  value="'+col+'" />'+col+' </label><br/>' );
						 } 
		} */
	
	/* function Coulmn_Name_Sort(aa) {
		var subjectvar2 = $('input[name="multisub_sort'+aa+'"]:checkbox:checked').map(function() {
			return this.value;
		}).get();
		var subject2 = subjectvar2.join(",");
		 $("#hd_column_sort"+aa).val(subject2);
		 $("#show_data").hide();
		 $( 'input[name="multisub_sort'+aa+'"]' ).prop({disabled: true});
		 
		 //get_query();
	} */
	
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
                    
                /*      if ($("input[type = checkbox][name = 'multisub_check']:checked").length == 0) {
                        alert("Please Select Fileds");
                        return false;
                                      } */

                         for(var ps = 1;ps<=$("#count_filter").val();ps++){
                                         
                                                  if($("#count_filter").val()==1){
                                                          if($("#multisub_filter"+ps).val() != null){
                                                                  if($("select#operator"+ps).val() == "0" ){
                                                                          alert("Please Select Operator");
                                                                          $("input#operator"+ps).focus();
                                                                          return false;
                                                                  }
                                                                  /* if($("input#value"+ps).val() == "" ){
                                                                      alert("Please Enter Value");
                                                                      $("input#value"+ps).focus();
                                                                       return false;
                                                      
                                                               }    */
                                                          }
                                                  }
                                                  else {
                                                          if($("select#multisub_filter"+ps).val() == null || $("select#multisub_filter"+ps).val() == "0"){
                                                                  alert("Please Select Filed Name");
                                                                  $("input#multisub_filter"+ps).focus();
                                                                  return false;
                                                          }
                                                          if($("#multisub_filter"+ps).val() !="" || $("#multisub_filter"+ps).val() != null){
                                                                  if($("select#operator"+ps).val() == "0" ){
                                                                          alert("Please Select Operator");
                                                                          $("input#operator"+ps).focus();
                                                                          return false;
                                                                  }
                                                                  
                                                                 /*  if($("input#value"+ps).val() == "" ){
                                                                      alert("Please Enter Value");
                                                                      $("input#value"+ps).focus();
                                                                       return false;
                                                      
                                                               }    
                                                                   */
                                                          }
                                                  
                                                  }
                                          }
                     
                     
                                  for(var ps = 1;ps<=$("#count_sort").val();ps++){
                                          
                                                          if($("#count_sort").val()==1){
                                                                  if($("#multisub_sort"+ps).val() != null){
                                                                          if($("select#direction"+ps).val() == "0" ){
                                                                                  alert("Please Select Direction");
                                                                                  $("input#direction"+ps).focus();
                                                                                  return false;
                                                                          }
                                                                          
                                                                          
                                                                  }
                                                                  
                                                          }
                                                          else {
                                                                  if($("select#multisub_sort"+ps).val() == null || $("select#multisub_sort"+ps).val() == "0"){
                                                                          alert("Please Select Order");
                                                                          $("input#multisub_sort"+ps).focus();
                                                                          return false;
                                                                  }
                                                                  if($("select#multisub_sort"+ps).val() !="0" || $("select#multisub_sort"+ps).val() != null){
                                                                          if($("select#direction"+ps).val() == "0" ){
                                                                                  alert("Please Select Direction");
                                                                                  $("input#direction"+ps).focus();
                                                                                  return false;
                                                                          }
                                                                          
                                                                          if($("input#value"+ps).val() == "" ){
                                                                              alert("Please Enter Value");
                                                                              $("input#value"+ps).focus();
                                                                               return false;
                                                              
                                                                       }      
                                                                  }
                                                          
                                                          }
                                                  }
                        
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
			  $.post('Ad_hoc_query_Action?' + key + "=" + value, formdata , function(j){ 
					
				     var i=0;
					 var v=j.length;
					if(v!=0){
						$('#show_data').show(); 
						$("tbody#td_data").empty();
						
				xaller=1;
				for(i;i<v;i++){
					xaller=xaller+1;
						var hedar =  $("#check_filed").val();
						var rowgen = '<tr id="tr_data'+xaller+'">';
						
						var hedarslist = hedar.split(',');
						
									 for(k = 0; k < hedarslist.length; k++) {
										h= hedarslist[k].split('.').pop();
										var col = j[i][h];
										rowgen +='<td><label  id="allergic'+xaller+'" name="allergic'+xaller+'" class=" autocomplete" >'+col+'</label> </td>';
								} 
									rowgen +='</tr>';
		                            $("tbody#td_data").append(rowgen);   
				}
					}
	                                }).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	                       }); 
		 }
		   
	
	
	function fn_Operator(o) {
	//	get_query();
		var f1 = $("#operator"+o).val();
		var nt1= f1.includes("between");
		if(nt1==true){
			$("td#value_date"+o).hide();
			$("td#value"+o).hide();
			$("th#th_value").hide();
			$("th#from_dt").show();
			$("th#to_dt").show();
			$("td#td_from_date"+o).show();
			$("td#td_to_date"+o).show();
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
		  var checkboxes = document.getElementById("checkboxes");
		  $("#checkboxes_table"+pk).toggle();
		  $("#table_search"+pk).val(''); 
		  $('.table_list'+pk).each(function () {       
		  $(this).show()
		})
		}
	
/* 	function Table2_showCheckboxes() {
		  var checkboxes = document.getElementById("checkboxes");
		  $("#checkboxes_table2").toggle();
		  $("#table_search2").val(''); 
		  $('.table_list2').each(function () {       
		  $(this).show()
		})
		} */
	
	
	function fn_join() {
		// get_query();
	}

	
/* 	function showCheckboxes_field2() {
		  var checkboxes = document.getElementById("checkboxes");
		  $("#checkboxes_field2").toggle();
		  $("#column_search2").val(''); 
		  $('.column_list').each(function () {       
		  $(this).show()
		})
		} */
	
	
/* 	function Coulmn_Name2() {
		var subjectvar = $('input[name="multisub_filed2"]:checkbox:checked').map(function() {
			return this.value;
		}).get();
		var subject = subjectvar.join(",");
		 $("#hd_column2").val(subject);
		 $("#show_data").hide();
		 $( "input[name='multisub_filed2']" ).prop({disabled: true});
	}
	 */
	
	
	
	function Table_Name() {
		
	    $('input[name="multisub_table"]').each(function () {  
	        if ( this.checked ) {  
	        	
	        	 var tw1 = this.value.toString();
	        	
	        	$('input[id="'+this.id+'"]').prop({disabled: true});
	        	
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
		
		 
		 //var result;
		 
		
		// $.ajaxSetup({
			// async : false
		// });
		
		
		var hd_table_name = [];
		hd_table_name.push(subject2);
		$("#table_name_hidden_list").val(hd_table_name);
	
	}
	
	
	
/* 	function get_selectColumn() {
		
		var v = $("input#count_head").val();
		if (v != 0) {
			$('#selected_tbody').empty();
			for (var i=1; i <= v; i++) {
				var x = 1;
				
				
	           var rowgen = '<tr id="tr_selected'+x+'">';
	                         //rowgen +='<td style="width: 10%;text-align: center;">'+x+'</td>';
							 rowgen +='<td>';
							 rowgen += '<div class="selectBox" onclick="showCheckboxes('+ x+ ')">';
							 rowgen += '<select name="sub_column" id="sub_column"';
							 rowgen += '	class=" form-control">';
							 rowgen += '	<option>Select Fields</option>';
							 rowgen += '</select>';
							 rowgen += '	<div class="overSelect"></div>';
							 rowgen += '</div>';
							 rowgen += '<div id="checkboxes'+x+'" class="checkboxes"';
							 rowgen += 'style="max-height: 200px; overflow: auto;">';
							 rowgen += '<div style="">';
							 rowgen += '	<input type="text" id="column_search'+x+'"';
							 rowgen += '	class="form-control autocomplete" autocomplete="off"';
							 rowgen += '	placeholder="Search Fields">';
							 rowgen += '</div>';
							 rowgen += '<div id = "filed1_check1"></div> ';
							 rowgen += '<input type="hidden" id="hd_column'+x+'" name="hd_column'+x+'" class="form-control autocomplete" autocomplete="off"></input>';
							 rowgen += '</div>';
							 rowgen += '</td>';
							 rowgen +='</tr>'; 
							 
                             $("tbody#selected_tbody").append(rowgen);  
				
                        	 $.ajaxSetup({
								 async : false
							 });
						
				
			}
		}
} */

	
	
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
		 
		// get_check_filed();
		// get_query();
		 
		   var num=0;
		    $('#column_sub_list').empty()
		    $("thead#thead_data").empty();
		    $('input[name="multisub_check"]').each(function () {  
		        if ( this.checked ) {   
		        	var text = this.parentElement.innerText;
					var h1 = text.split('.').pop();
		        	$('#column_sub_list').append("<span class='subspan'>"+this.parentElement.innerText+"<span> <br>");
		 	   		$("thead#thead_data").append("<td>"+h1+"</td>");
		        	 num=num+1;
		        }
		    });
		    if(num!=0)
		        $("#sub_column option:first").text('Fields('+num+')');
		    else
		        $("#sub_column option:first").text("Select Fields");
		 
	}
	
	
	/*     use join
	
	
	
	function get_query() {
		var	query="";
		var vk = $("input#count_head").val();
			for (var nm = 1; nm <=vk; nm++) {
				var hd_column =  $("#hd_column"+nm).val();
				
				  if(nm==1){
						query+="SELECT ";
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
					query+=" "+join;
				}
				else
				{
					query+=" "+t1+" "+aliase+" "+join + "  " ;
				}
			}
			
			 var q  = query.substring(0,query.length - 1);
			 var count_filter = $("#count_filter").val();
			 var count_sort = $("#count_sort").val();
			 
				for (var i = 1; i <= count_filter; i++) {
					var hd_column_filter = $("#hd_column_filter"+i).val();
					var operator = "";
					var value = $("#value"+i).val();
					var andor = "";
					
					 if($("#hd_column_filter"+i).val()!=""){
						 if(i==1){
							q+= " WHERE ";
						}
					 }
					
					if($("#operator"+i).val()!=0){
						operator+=$("#operator"+i).val();
					}
					
					if($("#andor"+i).val()!=-1){
						andor+=$("#andor"+i).val();
					}
					q+= hd_column_filter + operator + value+" " + andor + " ";
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
			$("#create_query").val(q);
	} */
	
	
	/* by default inner join use */
	
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
				/* +'<td><select name="filed'+aller_srno+'"  id="filed'+aller_srno+'" class="form-control"><option value="0">--Select--</option><c:forEach var="item" items="${get_table_name_List}" varStatus="num"><option value="${item.codevalue}" name="${item.label}">${item.label}</option></c:forEach></select></td>' */
				
				+'<td><select id="multisub_filter'+aller_srno+'" name="multisub_filter'+aller_srno+'" class="form-control"></select></td>'
				+'<td>'
				+ '<select  id="operator'+aller_srno+'" name="operator'+aller_srno+'" class="form-control-sm form-control" onchange="fn_Operator('+aller_srno+');" ><option value="0">--Select--</option><c:forEach var="item" items="${get_operator_List}" varStatus="num"><option value="${item.codevalue}" name="${item.label}">${item.label}</option></c:forEach></select></td>'
				+'<td id="value'+aller_srno+'"><input type="text" id="value'+aller_srno+'" name="value'+aller_srno+'" onkeyup="Value('+aller_srno+');"  maxlength="100"  class="form-control autocomplete" autocomplete="off"></td>'
				+ '<td id="value_date'+aller_srno+'" style="display: none;">'
				+' <input type="text" id="value_dt'+aller_srno+'" name="value_dt'+aller_srno+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
				+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
				+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);Value('+aller_srno+');" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">'
				+ '</td>'
				
				+ '<td id="td_from_date'+aller_srno+'" style="display: none;">'
				+' <input type="text" id="from_date'+aller_srno+'" name="from_date'+aller_srno+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
				+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
				+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);Value('+aller_srno+');" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">'
				+ '</td>'
				
				+ '<td id="td_to_date'+aller_srno+'" style="display: none;">'
				+' <input type="text" id="to_date'+aller_srno+'" name="to_date'+aller_srno+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
				+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
				+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);Value('+aller_srno+');" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">'
				+ '</td>'
				
				+'<td>'
				+ '<select name="andor'+aller_srno+'" id="andor'+aller_srno+'" class="form-control-sm form-control" onchange="fn_AndOr('+aller_srno+');"  ><option value="-1">--Select--</option><option value="and">And</option><option value="or">Or</option></select> </td>'
				+'<td>'
				+'<a class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "allergic_add'+aller_srno+'" onclick="add_fn('+aller_srno+');" ><i class="fa fa-plus"></i></a>'
				+'<a  class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "allergic_remove'+aller_srno+'" onclick="remove_fn('+aller_srno+');"><i class="fa fa-trash"></i></a> '
				+'</td></tr>');
		
		datepicketDate('value_dt'+aller_srno);
		datepicketDate('from_date'+aller_srno);
		datepicketDate('to_date'+aller_srno);
		
		var tn = $("#table_name_hidden_list").val();
		
		 $.post('get_selected_field_List?' + key + "=" + value,{ tn : tn },function(j) {
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
	
	/* function get_check_filed() {
		var v = $("input#count_head").val();
		
		 var check_filed_Name =new Array();
		
		 for (var l = 0; l < hd_table_name.length; l++) {
			 var h = hd_table_name[l];
			    check_filed_Name.push(h);	    
				document.getElementById('check_filed').value=check_filed_Name;
		} 
	} */
	
	function fn_direction(qw) {
		//$("#div_query").show();
		//get_query();
	}
	
	$(document).ready(function() {
		datepicketDate('value_dt1');
		datepicketDate('from_date1');
		datepicketDate('to_date1');
	}); 
	
	function get_filter() {
		var tn = $("#table_name_hidden_list").val();
		
		 $.post('get_selected_field_List?' + key + "=" + value,{ tn : tn },function(j) {
	        }).done(function(j) {
	        	
	          	var options = '<option   value="0">' + "--Select--"
				+ '</option>';
		for (var i = 0; i < j.length; i++) {

			options += '<option   value="' + j[i] + '" name="'+j[i]+'" >'
					+ j[i]+ '</option>';

		}

		//var spz = '${AnimalEditCMDUpload.specialization}'
		
		$("select#multisub_filter1").html(options);
		
	    				   
	        }).fail(function(xhr, textStatus, errorThrown) {
	        }); 
	}
	
	
function get_sort() {
		
		var tn = $("#table_name_hidden_list").val();
		
		 $.post('get_selected_field_List?' + key + "=" + value,{ tn : tn },function(j) {
	        }).done(function(j) {
	        	
	          	var options = '<option   value="0">' + "--Select--"
				+ '</option>';
		for (var i = 0; i < j.length; i++) {

			options += '<option   value="' + j[i] + '" name="'+j[i]+'" >'
					+ j[i]+ '</option>';

		}

		$("select#multisub_sort1").html(options);
	    				   
	        }).fail(function(xhr, textStatus, errorThrown) {
	        }); 
	}
	
	
function get_filed() {
	 var tw1 = $("#table_name_hidden_list").val();
	 
	
		 $.post('get_selected_field_List?' + key + "=" + value,{ tn : tw1 },function(j) {
	        }).done(function(j) {
	        	for(var l=0;l<j.length;l++){
					$("#filed1_check").append( '<label for="one" class="filedlist"><input type="checkbox" name="multisub_check" onclick="Coulmn_Name();"  value="'+j[l]+'" />'+j[l]+' </label><br/>' );
				}
	        }).fail(function(xhr, textStatus, errorThrown) {
	        }); 
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
								
						 	/* var hd_table1 =  $("#hd_table"+x).val();
							var t1 = hd_table1.split('-').shift();
							var aliase = hd_table1.split('-').pop(); */
							
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
		 
		// get_check_filed();
		// get_query();
		 
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
	
	/// formation wise
	
	 	$('select#cont_comd').change(function() {
   	   		var fcode = this.value;
   	       	getCONTCorps(fcode);
   	    	
   	       	fcode += "00";	
   	   		getCONTDiv(fcode);
   	       	
   	       	fcode += "000";	
   	   		getCONTBde(fcode);
   	   	});
   	   	$('select#cont_corps').change(function() {
   	   	   	var fcode = this.value;
   	       	getCONTDiv(fcode);
   	       	
   	       	fcode += "000";	
   	   		getCONTBde(fcode);
   	   	});
   	   	$('select#cont_div').change(function() {
   	   		var fcode = this.value;    	   	
   		   	getCONTBde(fcode);
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
		
		
		
		function Category_showCheckboxes(pk) {
			  var checkboxes = document.getElementById("checkboxes");
			  $("#checkboxes_category"+pk).toggle();
			  $("#category_search"+pk).val(''); 
			  $('.category_list'+pk).each(function () {       
			  $(this).show()
			})
			}
		
		
		function Category_Name() {
		
			var subjectvar21 = $('input[name="multisub_category"]:checkbox:checked').map(function() {
				return this.value;
			}).get();
			
			var subject21 = subjectvar21.join(",");
			$("#hd_category").val(subject21);
			
			var hd_category_name = [];
			hd_category_name.push(subject21);
			$("#category_hidden_list").val(hd_category_name);
		
		}
		
		
		$("#category_search1").keyup(function () {
			  var re = new RegExp($(this).val(), "i")
			  $('.category_list').each(function () {
			      var text = $(this).text(),
			          matches = !! text.match(re);
			      $(this).toggle(matches)
			  })
			});
	
	
	</script>