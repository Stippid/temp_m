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
	    		<div class="card-header"><h5>AD HOC</h5> <h6 class="enter_by">
<!-- 	    		<span style="font-size:12px;color:red;">(To be entered by Unit)</span> -->
	    		</h6></div>
	    		
	    				<div class="card-body card-block">
	    					<div class="col-md-12">	              					
	              		<div class="col-md-4">
						<div class="row form-group">
							<div class="col-md-4" align="left">
								<label class=" form-control-label"><strong
									style="color: red;"></strong>Table Name</label>
							</div>
							<div class="col-md-8">
								<div class="multiselect">

									<div class="selectBox" onclick="Table_showCheckboxes()">
										<select name="sub_table" id="sub_table"
											class=" form-control">
											<option>Select Tables</option>
										</select>
										<div class="overSelect"></div>
									</div>
									<div id="checkboxes_table" class="checkboxes"
										style="max-height: 200px; overflow: auto;">
										<div style="">
											<input type="text" id="table_search"
												class="form-control autocomplete" autocomplete="off"
												placeholder="Search Table">
										</div>
										<div>
											<c:forEach var="item" items="${getTableNameList}" varStatus="num">
												<label for="one" class="table_list"> <input onclick="Table_Name();"
													type="checkbox" name="multisub_table" value="${item}-a${num.index+1}" id = "${item}-a${num.index+1}"/>
													${item}
												</label>
											</c:forEach>
										</div>
										<input type="text" id="hd_table" name="hd_table" class="form-control autocomplete" autocomplete="off"></input>
									</div>
								</div>
							</div>
						</div>
					</div>
					
					
						<div class="col-md-4" id = "join_div" style="display: none;">
						<div class="row form-group">
							<div class="col-md-4" align="left">
								<label class=" form-control-label"><strong
									style="color: red;"></strong>Select Join</label>
							</div>
							<div class="col-md-8">
								<div class="row">
										 <select name="join" id="join" class="form-control-sm form-control"  onchange="fn_join();"> 
												<option value="0">--Select--</option>
												<option value="LEFT JOIN">LEFT JOIN</option>
												<option value="RIGHT JOIN">RIGHT JOIN</option>
												<option value="INNER JOIN">INNER JOIN</option>
												<option value="FULL JOIN">FULL JOIN</option>
											</select> 
								</div>
							</div>
						</div>
					</div>
					
						<div class="col-md-4" id = "table2_div" style="display: none;">
						<div class="row form-group">
							<div class="col-md-4" align="left">
								<label class=" form-control-label"><strong
									style="color: red;"></strong>Table Name</label>
							</div>
							<div class="col-md-8">
								<div class="multiselect">

									<div class="selectBox" onclick="Table2_showCheckboxes()">
										<select name="sub_table2" id="sub_table2"
											class=" form-control">
											<option>Select Tables</option>
										</select>
										<div class="overSelect"></div>
									</div>
									<div id="checkboxes_table2" class="checkboxes"
										style="max-height: 200px; overflow: auto;">
										<div style="">
											<input type="text" id="table_search2"
												class="form-control autocomplete" autocomplete="off"
												placeholder="Search Table">
										</div>
										<div>
											<c:forEach var="item" items="${getTableNameList}" varStatus="num">
												<label for="one" class="table_list2"> <input onclick="Table_Name2();"
													type="checkbox" name="multisub_table2" value="${item}-a0${num.index+1}" id = "${item}-a0${num.index+01}"/>
													${item}
												</label>
											</c:forEach>
										</div>
										<input type="text" id="hd_table2" name="hd_table2" class="form-control autocomplete" autocomplete="off"></input>
									</div>
								</div>
							</div>
						</div>
					</div>
					
					
				<!-- 	<div class="col-md-3">
						<div class="row form-group">
							<div class="col-md-4" align="left">
								<label class=" form-control-label"><strong
									style="color: red;"></strong>Selected Tables</label>
							</div>
							<div class="col-md-8">
								<div class="row">
									<div id="table_sub_list"
										style="max-height: 200px; overflow: auto; width: 100%; border: 1px solid;">
									</div>
								</div>
							</div>
						</div>
					</div> -->
	              			</div>
	            	   
	              			<div class="col-md-12">	              					
	              		<div class="col-md-6" id="field1_div" style="display: none;">
						<div class="row form-group">
							<div class="col-md-4" align="left">
								<label class=" form-control-label"><strong
									style="color: red;"></strong>Fields</label>
							</div>
								<div class="col-md-8">
								<div class="multiselect">
									<div class="selectBox" onclick="showCheckboxes()">
										<select name="sub_column" id="sub_column"
											class=" form-control">
											<option>Select Fields</option>
										</select>
										<div class="overSelect"></div>
									</div>
									<div id="checkboxes" class="checkboxes"
										style="max-height: 200px; overflow: auto;">
										<div style="">
											<input type="text" id="column_search"
												class="form-control autocomplete" autocomplete="off"
												placeholder="Search Fields">
										</div>
										<div id = "filed1_check">
										</div> 
										
										<input type="text" id="hd_column" name="hd_column" class="form-control autocomplete" autocomplete="off"></input>
									</div> 
								</div>
							</div>
							
						</div>
					</div>
					
					
						<div class="col-md-6" id="field2_div" style="display: none;">
						<div class="row form-group">
							<div class="col-md-4" align="left">
								<label class=" form-control-label"><strong
									style="color: red;"></strong>Fields</label>
							</div>
								<div class="col-md-8">
								<div class="multiselect">
									<div class="selectBox" onclick="showCheckboxes_field2()">
										<select name="sub_field" id="sub_field"
											class=" form-control">
											<option>Select Fields</option>
										</select>
										<div class="overSelect"></div>
									</div>
									<div id="checkboxes_field2" class="checkboxes"
										style="max-height: 200px; overflow: auto;">
										<div style="">
											<input type="text" id="column_search2"
												class="form-control autocomplete" autocomplete="off"
												placeholder="Search Fields">
										</div>
										<div id = "filed2_check">
										</div> 
										
										<input type="text" id="hd_column2" name="hd_column2" class="form-control autocomplete" autocomplete="off"></input>
									</div> 
								</div>
							</div>
							
						</div>
					</div>
					
				<!-- 		<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4" align="left">
								<label class=" form-control-label"><strong
									style="color: red;"></strong>Selected Fields</label>
							</div>
							<div class="col-md-8">
								<div class="row">

									<div id="column_sub_list"
										style="max-height: 200px; overflow: auto; width: 100%; border: 1px solid;">

									</div>

								</div>

							</div>
						</div>
					</div> -->
	              			</div>	    
            			</div>
	        	</div>
			</div>
	</div>
	
	
	
		<div class="col-md-12"  >
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

								<div class="col-md-12">
						
								<div class="col-md-4">
						<div class="row form-group">
							<div class="col-md-4" align="left">
								<label class=" form-control-label"><strong
									style="color: red;"></strong>Fileds</label>
							</div>
							<div class="col-md-8">
								<div class="multiselect">

									<div class="selectBox" onclick="Filed_showCheckboxes()">
										<select name="sub_select_filed" id="sub_select_filed"
											class=" form-control">
											<option>Select Fileds</option>
										</select>
										<div class="overSelect"></div>
									</div>
									<div id="checkboxes_select_filed" class="checkboxes"
										style="max-height: 200px; overflow: auto;">
										<div style="">
											<input type="text" id="select_filed_search"
												class="form-control autocomplete" autocomplete="off"
												placeholder="Search Table">
										</div>
										<div id="select_filed_check">
											<%-- <c:forEach var="item" items="${getTableNameList}" varStatus="num">
												<label for="one" class="select_filed_list"> <input onclick="Select_Field();"
													type="checkbox" name="multisub_select_filed" value="${item}-${num.index+1}" id = "${item}-${num.index+1}"/>
													${item}
												</label>
											</c:forEach> --%>
										</div>
										<input type="text" id="hd_select_filed" name="hd_select_filed" class="form-control autocomplete" autocomplete="off"></input>
									</div>
									
								</div>
							</div>
						</div>
					</div>
					</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	
	
	
	    			<div class="col-md-12" id="div_rank" >
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
							<th>Value</th> 
							<th>AndOr</th> 
							<th>Action</th>
					   </tr>
					</thead>
				   		<tbody data-spy="scroll" id="allergictbody" style="min-height: 46px; max-height: 650px; text-align: center;">
							<tr id="tr_allergic1">
								<td class="aller_srno">1</td>
								<td>    
										<div class="selectBox" onclick="showCheckboxes_Filter(1)">
										<select name="sub_column" id="sub_column"
											class=" form-control">
											<option>Select Fields</option>
										</select>
										<div class="overSelect"></div>
									</div>
									<div id="checkboxes_filter1" class="checkboxes"
										style="max-height: 200px; overflow: auto;">
										<div style="">
											<input type="text" id="column_search_filter1"
												class="form-control autocomplete" autocomplete="off"
												placeholder="Search Fields">
										</div>
										<div>
											<c:forEach var="item" items="${getColumnList}" varStatus="num">
												<label for="one" class="column_list_filter1"> <input onclick="Coulmn_Name_Filter(1);"
													type="checkbox" name="multisub_filter1" value="${item}" />
													${item}
												</label>
											</c:forEach>
										</div>
										<input type="text" id="hd_column_filter1" name="hd_column_filter1" class="form-control autocomplete"  autocomplete="off"></input>
									</div>
								
								</td>
							<td>
							 <select name="operator1" id="operator1" class="form-control-sm form-control" > <!-- onchange="fn_Operator(1);" -->
												<option value="0">--Select--</option>
												<option value="=">Equal (=)</option>
												<option value="<">Less Than (<)</option>
											</select> 
								</td>
								<td>  
											<input type="text" id="value1" name="value1" maxlength="100"  
									class="form-control autocomplete" autocomplete="off">
								</td>
								<td> <select name="andor1" id="andor1" class="form-control-sm form-control"  > <!-- onchange="fn_AndOr(1);"  -->
												<option value="-1">--Select--</option>
												<option value="and">And</option>
												<option value="or">Or</option>
											</select> 
											
								</td>
							<td>
							<!-- <a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "allergic_save1" onclick="allergic_save_fn(1);" ><i class="fa fa-save"></i></a> -->
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
		
		<div class="col-md-12"  >
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
										<div class="selectBox" onclick="showCheckboxes_Sort(1)">
										<select name="sort_column" id="sort_column"
											class=" form-control">
											<option>Select Fields</option>
										</select>
										<div class="overSelect"></div>
									</div>
									<div id="checkboxes_sort1" class="checkboxes"
										style="max-height: 200px; overflow: auto;">
										<div style="">
											<input type="text" id="column_search_sort1"
												class="form-control autocomplete" autocomplete="off"
												placeholder="Search Fields">
										</div>
										<div>
											<c:forEach var="item" items="${getColumnList}" varStatus="num">
												<label for="one" class="column_list_sort1"> <input onclick="Coulmn_Name_Sort(1);"
													type="checkbox" name="multisub_sort1" value="${item}" />
													${item}
												</label>
											</c:forEach>
										</div>
										<input type="text" id="hd_column_sort1" name="hd_column_sort1" class="form-control autocomplete" autocomplete="off"></input>
									</div>
								</td>
							<td>
							 <select name="direction1" id="direction1" class="form-control-sm form-control" >
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
					<!-- <div class="card-footer" align="right" class="form-control"> 
					<input type="button" id="mrgqualibtn_save" class="btn btn-primary btn-sm" value="Submit" onclick="return submit_fn();" >		              
				    </div> -->
				</div>
			</div>
	</div>
	
	<div class="col-md-12"  >
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
<!-- 	<div class="col-md-12">
				<div class="col-md-12">
						 <div class="col-md-6" id="" >
							<div class="row form-group">
								<div class="col-md-4">
									<strong style="color: red;">  </strong><label class=" form-control-label"> Create Query </label>
								</div>
								<div class="col-md-8">
										<textarea id="create_query" name="create_query" class="form-control autocomplete" ></textarea>
								</div>
							</div>
						</div>
					</div>
		</div> -->
	</form>
	
	<div class="col-md-12">
				<div class="card-footer" align="right" class="form-control"> 
					<input type="button" id="mrgqualibtn_save" class="btn btn-primary btn-sm" value="Submit" onclick="return submit_fn();" >		              
				</div>
		</div>
		
		
			<div class="col-md-12">
			<table id="show_data" class="display table no-margin table-striped  table-hover table-bordered " style="display: none;">
				<thead id = "thead_data">
			        <tr id = "tr_data">
			    </tr>
				</thead>
				<tbody id = "td_data">
				<!-- <tr id = "tr_data">
			    </tr> -->
				</tbody>
			</table>
		</div>
		
	

	
	<c:url value="GetSearch_Pers" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="p_type1">
		<input type="hidden" name="p_type1" id="p_type1" />				
		<input type="hidden" name="rank_parameter1" id="rank_parameter1" />		
		<input type="hidden" name="rk_type1" id="rk_type1" />		
		<input type="hidden" name="name1" id="name1" />		
	</form:form>
	
	
	<Script>
	
	/* function Search(s){
		
		
		if(s == "rank"){
			$("#rk_type1").val($("#rk_type").val()) ;	
			$("#rank_parameter1").val($("#rank_parameter").val()) ;	
			
		}
		
		if(s == "name"){
			$("#name1").val($("#name").val()) ;	
		}
		
		
		$("#p_type1").val($("#p_type").val());
		document.getElementById('searchForm').submit();
		
	}
	
	function Type(a) {
		if(a == "rank"){
			$("#div_rank").show();
			$("#div_name").hide();
		}
		if(a == "name"){
			$("#div_name").show();
			$("#div_rank").hide();
		}
	} */
	
	
	$(document).ready(function() {
	/* 	 if('${p_type}' != ""){
			 
			 Type('${p_type}');
			 
			 $("#p_type").val('${p_type}') ;
			 
			 if('${p_type}' == "rank"){
				 
				 //showheader("");
				 //divN(obj);
				 
				 if('${rk_type}' != ""){
						$("#rk_type").val('${rk_type}') ;
					}
				 
				 if('${rank_parameter}' != ""){
						$("#rank_parameter").val('${rank_parameter}') ;
					}
			 }
			 
			 
         if('${p_type}' == "name"){
				 if('${name}' != ""){
						$("#name").val('${name}') ;
					}
			 }
			} */
		 
	});
	</Script>
	
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
		
		if (obj.id == "c_final") {
			if(!hasC){
				get_query();
			}
		}
		
		}
		

function add_fn(aller_srno){

	   $("#allergic_add" + aller_srno).hide();
	   $("#allergic_remove" + aller_srno).hide();

		aller_srno+=1; 
		
		 $("input#count_filter").val(aller_srno);
	
	$("table#ad_hoc_table").append('<tr id="tr_allergic'+aller_srno+'">'
			+'<td class="aller_srno">'+aller_srno+'</td>'
			+'<td>'
			+ '<div class="selectBox" onclick="showCheckboxes_Filter('+ aller_srno+ ')">'
			+ '<select name="sub_column" id="sub_column"'
			+ '	class=" form-control">'
			+ '	<option>Select Fields</option>'
			+ '</select>'
			+ '	<div class="overSelect"></div>'
			+ '</div>'
			+ '<div id="checkboxes_filter'+aller_srno+'" class="checkboxes"'
			+ 'style="max-height: 200px; overflow: auto;">'
			+ '<div style="">'
			+ '	<input type="text" id="column_search_filter'+aller_srno+'"'
			+ '	class="form-control autocomplete" autocomplete="off"'
			+ '	placeholder="Search Fields">'
			+ '</div>'
			+ '<div>'
			+ '	<c:forEach var="item" items="${getColumnList}" varStatus="num">'
			+ '		<label for="one" class="column_list_filter'+aller_srno+'"> <input onclick="Coulmn_Name_Filter('+aller_srno+');"'
			+ '			type="checkbox" name="multisub_filter'+aller_srno+'" value="${item}" />'
			+ '			${item}'
			+ '		</label>'
			+ '	</c:forEach>'
			+ '</div>'
			+ '<input type="text" id="hd_column_filter'+aller_srno+'" name="hd_column_filter'+aller_srno+'" class="form-control autocomplete" autocomplete="off"></input>'
			+ '</div>'
			+ '</td>'
			+'<td>'
			+ '<select name="operator'+aller_srno+'" id="operator'+aller_srno+'" class="form-control-sm form-control"  ><option value="0">--Select--</option><option value="=">Equal (=)</option><option value="<">Less Than (<)</option></select> </td>'
			+'<td><input type="text" id="value'+aller_srno+'" name="value'+aller_srno+'" maxlength="100"  class="form-control autocomplete" autocomplete="off"></td>'
			+'<td>'
			+ '<select name="andor'+aller_srno+'" id="andor'+aller_srno+'" class="form-control-sm form-control"  ><option value="-1">--Select--</option><option value="and">And</option><option value="or">Or</option></select> </td>'
			+'<td>'
			+'<a class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "allergic_add'+aller_srno+'" onclick="add_fn('+aller_srno+');" ><i class="fa fa-plus"></i></a>'
			+'<a  class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "allergic_remove'+aller_srno+'" onclick="remove_fn('+aller_srno+');"><i class="fa fa-trash"></i></a> '
			+'</td></tr>');
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

	   $("#sort_add" + x).hide();
	   $("#sort_remove" + x).hide();

		x+=1; 
		
		$("input#count_sort").val(x);
	
	$("table#sort_table").append('<tr id="tr_sort'+x+'">'
			+'<td class="sort_srno">'+x+'</td>'
			+'<td>'
			+ '<div class="selectBox" onclick="showCheckboxes_Sort('+ x+ ')">'
			+ '<select name="sort_column" id="sort_column"'
			+ '	class=" form-control">'
			+ '	<option>Select Fields</option>'
			+ '</select>'
			+ '	<div class="overSelect"></div>'
			+ '</div>'
			+ '<div id="checkboxes_sort'+x+'" class="checkboxes"'
			+ 'style="max-height: 200px; overflow: auto;">'
			+ '<div style="">'
			+ '	<input type="text" id="column_search_sort'+x+'"'
			+ '	class="form-control autocomplete" autocomplete="off"'
			+ '	placeholder="Search Fields">'
			+ '</div>'
			+ '<div>'
			+ '	<c:forEach var="item" items="${getColumnList}" varStatus="num">'
			+ '		<label for="one" class="column_list_sort'+x+'"> <input onclick="Coulmn_Name_Sort('+x+');"'
			+ '			type="checkbox" name="multisub_sort'+x+'" value="${item}" />'
			+ '			${item}'
			+ '		</label>'
			+ '	</c:forEach>'
			+ '</div>'
			+ '<input type="text" id="hd_column_sort'+x+'" name="hd_column_sort'+x+'" class="form-control autocomplete" autocomplete="off"></input>'
			+ '</div>'
			+ '</td>'
			+'<td>'
			+ '<select name="direction'+x+'" id="direction'+x+'" class="form-control-sm form-control" ><option value="0">--Select--</option><option value="Asec">Asec</option><option value="Desc">Desc</option></select> </td>'
			+'<td>'
			+'<a class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "sort_add'+x+'" onclick="add_fn_sort('+x+');" ><i class="fa fa-plus"></i></a>'
			+'<a  class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "sort_remove'+x+'" onclick="remove_fn_sort('+x+');"><i class="fa fa-trash"></i></a> '
			+'</td></tr>');
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

	</script>
	
	<script>
	
	function showCheckboxes() {
	  var checkboxes = document.getElementById("checkboxes");
	  $("#checkboxes").toggle();
	  $("#column_search").val(''); 
	  $('.column_list').each(function () {       
	  $(this).show()
	})
	}
	
	
	
	$("input[type='checkbox'][name='multisub']").click(function() {
	    // access properties using this keyword
	    var num=0;
	    $('#column_sub_list').empty()
	    $("thead#thead_data").empty();
	    $("input[type='checkbox'][name='multisub']").each(function () {  
	        if ( this.checked ) {   
	        	/* $('#column_sub_list').append("<span class='subspan'>"+this.parentElement.innerText+"<span> <br>");
	        	num=num+1; */
	        	
	        	$('#column_sub_list').append("<span class='subspan'>"+this.parentElement.innerText+"<span> <br>");
	        	
	        	/*  $("thead#thead_data").append('<tr>'
	   				 +'<th>'+this.parentElement.innerText+'</th>'
	 	   		    +'</tr>'); */
	 	   		    
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
	
	 //var query = "";
	function Coulmn_Name() {
		var subjectvar = $('input[name="multisub"]:checkbox:checked').map(function() {
			return this.value;
		}).get();
		var subject = subjectvar.join(",");
		 $("#hd_column").val(subject);
		 $("#show_data").hide();
		 $( "input[name='multisub']" ).prop({disabled: true});
		 $("#field2_div").show();
	}
	
	/* function check_column() {
		var check = $("input[name='multisub']:checked").val();
		 alert("this---" + check);
		
	} */
	
	function showCheckboxes_Filter(n) {
		  var checkboxes = document.getElementById("checkboxes");
		  $("#checkboxes_filter"+n).toggle();
		  $("#column_search_filter"+n).val(''); 
		  $('.column_list_filter'+n).each(function () {       
		  $(this).show()
		})
		}
	
	function Coulmn_Name_Filter(a) {
		var subjectvar1 = $('input[name="multisub_filter'+a+'"]:checkbox:checked').map(function() {
			return this.value;
		}).get();
		var subject1 = subjectvar1.join(",");
		 $("#hd_column_filter"+a).val(subject1);
		 $("#show_data").hide();
		 var q1 = "";
		 if(a == 1){
			q1 = "where ";
		 }
		 var query1=$("#create_query").val() + q1 + $("#hd_column_filter"+a).val();
		 $("#create_query").val(query1);
	}
	
	
	function showCheckboxes_Sort(nk) {
		  var checkboxes = document.getElementById("checkboxes");
		  $("#checkboxes_sort"+nk).toggle();
		  $("#column_search_sort"+nk).val(''); 
		  $('.column_list_sort'+nk).each(function () {       
		  $(this).show()
		})
		}
	
	function Coulmn_Name_Sort(aa) {
		var subjectvar2 = $('input[name="multisub_sort'+aa+'"]:checkbox:checked').map(function() {
			return this.value;
		}).get();
		var subject2 = subjectvar2.join(",");
		 $("#hd_column_sort"+aa).val(subject2);
		 $("#show_data").hide();
	}
	var h="";
	function submit_fn() {
		 var formdata=$('#filed_form').serialize();
		 
		   $.post('Ad_hoc_query_Action?' + key + "=" + value, formdata, function(j){ 
			
			     var i=0;
				 var v=j.length;
				if(v!=0){
					$('#show_data').show(); 
					$("tbody#td_data").empty();
					
			xaller=1;
			for(i;i<v;i++){
				xaller=xaller+1;
					var hedar =  $("#hd_column").val();
					var rowgen = '<tr id="tr_data'+xaller+'">';
					
					var hedarslist = hedar.split(',');
								 for(k = 0; k < hedarslist.length; k++) {
									h= hedarslist[k];
									 console.log("h--"+h	);
									var col = j[i][h];
									 console.log("col--"+col);
									rowgen +='<td><label  id="allergic'+xaller+'" name="allergic'+xaller+'" class=" autocomplete" >'+col+'</label> </td>';
							} 
								rowgen +='</tr>';
	                            $("tbody#td_data").append(rowgen);   
			}
				}
                                   
                                }).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
                       });   
	}
	
	
	/* function fn_Operator(o) {
		var query2 = $("#create_query").val() + $("#operator"+o).val();
		 $("#create_query").val(query2);
	}
	
	function Value(v) {
		//alert("value---" + $("#value"+v).val());
		var query4 = $("#create_query").val() + $("#value"+v).val() + " ";
		$("#create_query").val(query4);
	}
	
	function fn_AndOr(nd) {
		var query3 = $("#create_query").val() + $("#value"+nd).val() + " " + $("#andor"+nd).val() + " ";
		$("#create_query").val(query3);
	} */
	
	function get_query() {
		var hd_table1 =  $("#hd_table").val();
		var t1 = hd_table1.split('-').shift();
		var aliase = hd_table1.split('-').pop();
		var hd_table2 =  $("#hd_table2").val();
	    var t2 = hd_table2.split('-').shift();
	    var aliase2 = hd_table2.split('-').pop();
	    var join =  $("#join").val();
	    
	    var	query="";
		
		if($("#hd_table2").val()!=""){
		query+="SELECT * FROM " + t1+" "+aliase+" "+join+" "+t2+" "+aliase2+" " + "on "+" "+aliase+"."+ $("#hd_column").val() +"="+aliase2+"."+$("#hd_column2").val(); 
		alert("query---" + query);
		$("#create_query").val(query);
		}
		else{
			query+= "SELECT ";
			var count_filter = $("#count_filter").val();
			var count_sort = $("#count_sort").val();
			query+= $("#hd_column").val() + " FROM table_name "; 
			
			for (var i = 1; i <= count_filter; i++) {
				var hd_column_filter = $("#hd_column_filter"+i).val();
				var operator = "";
				var value = $("#value"+i).val();
				var andor = "";
				
				 if($("#hd_column_filter"+i).val()!=""){
					 if(i==1){
						query+= "WHERE ";
					}
				 }
				
				if($("#operator"+i).val()!=0){
					operator+=$("#operator"+i).val();
				}
				
				if($("#andor"+i).val()!=-1){
					andor+=$("#andor"+i).val();
				}
				query+= hd_column_filter + operator + value + andor + " ";
			}
			
			
			for (var n = 1; n <=count_sort; n++) {
				var hd_column_sort = $("#hd_column_sort"+n).val();
				var direction = "";
				
					if($("#hd_column_sort"+n).val()!=""){
						if(n==1){
						query+= "ORDER BY "
						}
					}
				
				if(n>1){
					query+=",";
				}
				
				if($("#direction"+n).val()!=0){
					direction+= $("#direction"+n).val();
				}
				
				query+= hd_column_sort + " "+ direction;
			}
			
			query+=";";
			$("#create_query").val(query);
		}
		
		
	}
	
	
	function Table_showCheckboxes() {
		  var checkboxes = document.getElementById("checkboxes");
		  $("#checkboxes_table").toggle();
		  $("#table_search").val(''); 
		  $('.table_list').each(function () {       
		  $(this).show()
		})
		}
	
	
	function Table_Name() {
		var subjectvar = $('input[name="multisub_table"]:checkbox:checked').map(function() {
			return this.value;
			//document.getElementById("checkboxes").disabled = true;
		}).get();
		 
		 var subject = subjectvar.join(",");
		 $("#hd_table").val(subject);
		 $("#show_data").hide();
		 $("#join_div").show();
		 //$('#checkboxes').prop('disabled');
		 $( "input[name='multisub_table']" ).prop({disabled: true});
	}
	
	
	$("input[type='checkbox'][name='multisub_table']").click(function() {
	    // access properties using this keyword
	    var num=0;
	    $('#table_sub_list').empty()
	    $("input[type='checkbox'][name='multisub_table']").each(function () {  
	        if ( this.checked ) {   
	        	$('#table_sub_list').append("<span class='subspan'>"+this.parentElement.innerText+"<span> <br>");
	        	 num=num+1;
	        }
	    });
	    if(num!=0)
	        $("#sub_table option:first").text('Tables('+num+')');
	    else
	        $("#sub_table option:first").text("Select Tables");
	});
	
	
	function Table2_showCheckboxes() {
		  var checkboxes = document.getElementById("checkboxes");
		  $("#checkboxes_table2").toggle();
		  $("#table_search2").val(''); 
		  $('.table_list2').each(function () {       
		  $(this).show()
		})
		}
	
	
	
	$("input[type='checkbox'][name='multisub_table2']").click(function() {
	    // access properties using this keyword
	    var num=0;
	    $('#table_sub_list2').empty()
	    $("input[type='checkbox'][name='multisub_table2']").each(function () {  
	        if ( this.checked ) {   
	        	$('#table_sub_list2').append("<span class='subspan'>"+this.parentElement.innerText+"<span> <br>");
	        	 num=num+1;
	        }
	    });
	    if(num!=0)
	        $("#sub_table2 option:first").text('Tables('+num+')');
	    else
	        $("#sub_table2 option:first").text("Select Tables");
	});
	
	
	
	function Table_Name2() {
		var subjectvar = $('input[name="multisub_table2"]:checkbox:checked').map(function() {
			return this.value;
			//document.getElementById("checkboxes").disabled = true;
		}).get();
		 
		 var subject = subjectvar.join(",");
		 $("#hd_table2").val(subject);
		 $("#show_data").hide();
		 $( "input[name='multisub_table2']" ).prop({disabled: true});
		 $('#join').attr('disabled', true);
		 $("#field1_div").show();
		 
		 get_table1_field();
		 
	}
	
	function fn_join() {
		 $("#table2_div").show();
	}
	
	function get_table1_field() {
		var hd_table1 =  $("#hd_table").val();
		var t1 = hd_table1.split('-').shift();
		
		var hd_table2 =  $("#hd_table2").val();
	    var t2 = hd_table2.split('-').shift();
		
		
		 $.post("get_table1_field_List?"+key+"="+value, {
			 t1 : t1
     }, function(j) {
     }).done(function(j) {
    	 if (j != "") {
				for(var l=0;l<j.length;l++){
					$("#filed1_check").append( '<label class="dropdown-option"><input type="checkbox" name="multisub" onclick="Coulmn_Name();"  value="'+j[l]+'" />'+j[l]+' </label><br/>' );
				}
			}

     }).fail(function(xhr, textStatus, errorThrown) {
     });
		 
		 
		 $.post("get_table2_field_List?"+key+"="+value, {
			 t2 : t2
     }, function(j1) {
     }).done(function(j1) {
    	 if (j1 != "") {
				for(var kl=0;kl<j1.length;kl++){
					$("#filed2_check").append( '<label class="dropdown-option"><input type="checkbox" name="multisub_filed2" onclick="Coulmn_Name2();"  value="'+j1[kl]+'" />'+j1[kl]+' </label><br/>' );
				}
			}

     }).fail(function(xhr, textStatus, errorThrown) {
     });
		 
	}
	
	
	function showCheckboxes_field2() {
		  var checkboxes = document.getElementById("checkboxes");
		  $("#checkboxes_field2").toggle();
		  $("#column_search2").val(''); 
		  $('.column_list').each(function () {       
		  $(this).show()
		})
		}
	
	
	function Coulmn_Name2() {
		var subjectvar = $('input[name="multisub_filed2"]:checkbox:checked').map(function() {
			return this.value;
		}).get();
		var subject = subjectvar.join(",");
		 $("#hd_column2").val(subject);
		 $("#show_data").hide();
		 $( "input[name='multisub_filed2']" ).prop({disabled: true});
		 //$("#field2_div").show();
	}
	
	
	function Filed_showCheckboxes() {
		
		  var checkboxes = document.getElementById("checkboxes");
		  $("#checkboxes_select_filed").toggle();
		  $("#select_filed_search").val(''); 
		  $('.select_filed_list').each(function () {       
		  $(this).show()
		})
		
		get_query();
		
	    var query=$("#create_query").val();
		 
		$.post("get_select_field_List?"+key+"="+value, {
				 query : query
	     }, function(j1) {
	     }).done(function(j1) {
	    	 alert("J nk-----" + j1);
	    	 if (j1 != "") {
					for(var kl=0;kl<j1.length;kl++){
						$("#select_filed_check").append( '<label class="dropdown-option"><input type="checkbox" name="multisub_select_filed" onclick="Select_Field();"  value="'+j1+'" />'+j1+' </label><br/>' );
					}
				}
	     }).fail(function(xhr, textStatus, errorThrown) {
	     });
		}
	
	function Select_Field() {
		var subjectvar = $('input[name="multisub_select_filed"]:checkbox:checked').map(function() {
			return this.value;
			//document.getElementById("checkboxes").disabled = true;
		}).get();
		 var subject = subjectvar.join(",");
		 $("#hd_select_filed").val(subject);
		 $("#show_data").hide();
	
	}
	
	</script>