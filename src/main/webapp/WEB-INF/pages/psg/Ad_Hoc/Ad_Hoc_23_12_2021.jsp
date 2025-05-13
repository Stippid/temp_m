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

	
	
		<table id="head_table" class="table-bordered" style="margin-top: 10px; width: 100%;">
								<thead style="color: white; text-align: center;">
									<tr>
										<th>Sr No</th>
										<th><strong style="color: red;"> </strong>Table Name</th>
										<th style="display: none;"><strong style="color: red;"> </strong>Join</th>
										<th><strong style="color: red;"> </strong>Aliase</th>
										<th>Action</th>
									</tr>
								</thead>
								<tbody data-spy="scroll" id="head_tbody" style="min-height: 46px; max-height: 650px; text-align: center;">
									<tr id="tr_head1">
										<td class="head_srno" >1</td>
												<td>    
										<div class="selectBox" onclick="Table_showCheckboxes(1)">
										<select name="sub_table" id="sub_table"
											class=" form-control">
											<option>Select Tables</option>
										</select>
										<div class="overSelect"></div>
									</div>
										<div id="checkboxes_table1" class="checkboxes"
										style="max-height: 200px; overflow: auto;">
										<div style="">
											<input type="text" id="table_search1"
												class="form-control autocomplete" autocomplete="off"
												placeholder="Search Table">
										</div>
										<div>
											<c:forEach var="item" items="${get_table_name_List}" varStatus="num">
												<label for="one" class="table_list"> <input onclick="Table_Name(1);"
													type="checkbox" name="multisub_table1" value="${item.codevalue}-a1" id = "${item.codevalue}-a1"/>
													${item.label}
												</label>
											</c:forEach>
										</div>
										<input type="text" id="hd_table1" name="hd_table1" class="form-control autocomplete" autocomplete="off"></input>
									</div>
								</td>
							<td style="display: none;">
							 <select name="join1" id="join1" class="form-control-sm form-control"  onchange="fn_join();"> 
												<!-- <option value="0">--Select--</option>
												<option value="LEFT JOIN">LEFT JOIN</option>
												<option value="RIGHT JOIN">RIGHT JOIN</option> -->
												<option value="INNER JOIN">INNER JOIN</option>
												<!-- <option value="FULL JOIN">FULL JOIN</option> -->
											</select> 
								</td>
								<td>a1</td>
										<td>
										<a id="head_add1" class="btn btn-success btn-sm" 
										value="ADD" title="ADD" onclick="add_fn_head(1);">
										<i class="fa fa-plus"></i>
										</a>
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
										<th style="width: 10%;">Sr No</th>
										<th><strong style="color: red;"> </strong>Filed</th>
									</tr>
								</thead>
								<tbody id = "selected_tbody">
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
										<div id="check_filter1">
										</div>
										<input type="text" id="hd_column_filter1" name="hd_column_filter1" class="form-control autocomplete"  autocomplete="off"></input>
									</div>
								
								</td>
							<td>
							  <%-- <select name="operator1" id="operator1" class="form-control-sm form-control" onchange="fn_Operator(1);" > 
												<option value="0">--Select--</option>
												<option value="=">Equal (=)</option>
												<option value="<">Less Than (<)</option>
											</select>  --%>
											
											<select  id="operator1" name="operator1" class="form-control" onchange="fn_Operator(1);" >
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${get_operator_List}" varStatus="num">
														<option value="${item.codevalue}" name="${item.label}">${item.label}</option>
														</c:forEach>
											</select> 
								</td>
								<td>  
											<input type="text" id="value1" name="value1" maxlength="100"   onkeyup="Value(1);"
									class="form-control autocomplete" autocomplete="off">
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
										<div id = "check_filter_sort1">
										</div>
										<input type="text" id="hd_column_sort1" name="hd_column_sort1" class="form-control autocomplete" autocomplete="off"></input>
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
					<input type="hidden" id="count_filter" name="count_filter" value="1">
					<input type="hidden" id="count_sort" name="count_sort" value="1">
					<input type="hidden" id="count_head" name="count_head" value="1">
					<input type="hidden" id="check_filed" name="check_filed" >
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
	
		<div class="col-md-12">
			<table id="show_data" class="display table no-margin table-striped  table-hover table-bordered " style="display: none;">
				<thead id = "thead_data">
			        <tr id = "tr_data">
			    </tr>
				</thead>
				<tbody id = "td_data">
				</tbody>
			</table>
		</div>
		
		<div class="col-md-12" id="button_div" style="display: none;">
				<div class="card-footer" align="right" class="form-control">
				   <a href="ad_hoc_Url" class="btn btn-success btn-sm">Clear</a>  
					<input type="button" id="mrgqualibtn_save" class="btn btn-primary btn-sm" value="Submit" onclick="return submit_fn();" >		              
				</div>
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
				get_selectColumn();
			}
		}
		
		if (obj.id == "b_final") {
			if(!hasC){
				//get_sort();
			}
		}
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
			+ '<div id="check_filter_sort'+x+'">'
			+ '</div>'
			+ '<input type="text" id="hd_column_sort'+x+'" name="hd_column_sort'+x+'" class="form-control autocomplete" autocomplete="off"></input>'
			+ '</div>'
			+ '</td>'
			+'<td>'
			+ '<select name="direction'+x+'" id="direction'+x+'" class="form-control-sm form-control" onchange="fn_direction('+x+');" ><option value="0">--Select--</option><option value="Asc">Asc</option><option value="Desc">Desc</option></select> </td>'
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
	
	
	function showCheckboxes_Filter(n) {
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
		}
	
	function Coulmn_Name_Filter(a) {
		var subjectvar1 = $('input[name="multisub_filter'+a+'"]:checkbox:checked').map(function() {
			return this.value;
		}).get();
		var subject1 = subjectvar1.join(",");
		 $("#hd_column_filter"+a).val(subject1);
		 $("#show_data").hide();
		 $( 'input[name="multisub_filter'+a+'"]' ).prop({disabled: true});
		 get_query();
	}
	
	
	function showCheckboxes_Sort(nk) {
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
		}
	
	function Coulmn_Name_Sort(aa) {
		var subjectvar2 = $('input[name="multisub_sort'+aa+'"]:checkbox:checked').map(function() {
			return this.value;
		}).get();
		var subject2 = subjectvar2.join(",");
		 $("#hd_column_sort"+aa).val(subject2);
		 $("#show_data").hide();
		 $( 'input[name="multisub_sort'+aa+'"]' ).prop({disabled: true});
		 
		 get_query();
	}
	
	//var h="";
	function submit_fn() {
		 var formdata=$('#filed_form').serialize();
		 var create_query = $('#create_query').val().toLowerCase();
		 
		 var update = create_query.includes("update");
		 var delete1 = create_query.includes("delete");
		 var drop = create_query.includes("drop");
		/*  if(update==true || delete1==true || drop==true){
		 } 
		 else{ */
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
		   
	//}
	
	function fn_Operator(o) {
		get_query();
	}
	
	function Value(v) {
		 $("#div_sort").show(); 
		 get_query();
	}
	
	 function fn_AndOr(nd) {
		 get_query();
		}

	function Table_showCheckboxes(pk) {
		  var checkboxes = document.getElementById("checkboxes");
		  $("#checkboxes_table"+pk).toggle();
		  $("#table_search"+pk).val(''); 
		  $('.table_list'+pk).each(function () {       
		  $(this).show()
		})
		}
	
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
		
	}
	
	function fn_join() {
		 get_query();
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
	}
	
	function add_fn_head(xk){

		if ($("#join"+xk).val() == "0") {
			alert("Please Select Join");
			$("#join"+xk).focus();
			return false;
		} 
		
		   $("#head_add" + xk).hide();
		   $("#head_remove" + xk).hide();

			xk+=1; 
			
			$("input#count_head").val(xk);
			
			
		$("table#head_table").append('<tr id="tr_head'+xk+'">'
				+'<td class="head_srno">'+xk+'</td>'
				+'<td>'
				+ '<div class="selectBox" onclick="Table_showCheckboxes('+ xk+ ')">'
				+ '<select name="sub_table" id="sub_table"'
				+ '	class=" form-control">'
				+ '	<option>Select Tables</option>'
				+ '</select>'
				+ '	<div class="overSelect"></div>'
				+ '</div>'
				+ '<div id="checkboxes_table'+xk+'" class="checkboxes"'
				+ 'style="max-height: 200px; overflow: auto;">'
				+ '<div style="">'
				+ '	<input type="text" id="table_search'+xk+'"'
				+ '	class="form-control autocomplete" autocomplete="off"'
				+ '	placeholder="Search Table">'
				+ '</div>'
				+ '<div>'
				+ '	<c:forEach var="item" items="${get_table_name_List}" varStatus="num">'
				+ '		<label for="one" class="table_list'+xk+'"> <input onclick="Table_Name('+xk+');"'
				+ '			type="checkbox" name="multisub_table'+xk+'" value="${item.codevalue}-a'+xk+'" />'
				+ '			${item.label}'
				+ '		</label>'
				+ '	</c:forEach>'
				+ '</div>'
				+ '<input type="text" id="hd_table'+xk+'" name="hd_table'+xk+'" class="form-control autocomplete" autocomplete="off"></input>'
				+ '</div>'
				+ '</td>'
				+'<td style="display: none;">'
				+ '<select name="join'+xk+'" id="join'+xk+'" class="form-control-sm form-control" ><option value="INNER JOIN">INNER JOIN</option> </td>'
				+'<td> a'+xk+''
				+'</td>'
				+'<td>'
				+'<a class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "head_add'+xk+'" onclick="add_fn_head('+xk+');" ><i class="fa fa-plus"></i></a>'
				+'<a  class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "head_remove'+xk+'" onclick="remove_fn_head('+xk+');"><i class="fa fa-trash"></i></a> '
				+'</td></tr>');
	}
	
	
	function Table_Name(aa) {
		var subjectvar2 = $('input[name="multisub_table'+aa+'"]:checkbox:checked').map(function() {
			return this.value;
		}).get();
		var subject2 = subjectvar2.join(",");
		 $("#hd_table"+aa).val(subject2);
		 $("#show_data").hide();
		 $('input[name="multisub_table'+aa+'"]').prop({disabled: true});
		 
		 $("#col_filed").show();
		 $("#button_div").show();
		 
		 get_query();
		 
	}
	
	
	function remove_fn_head(av){
		   $("tr#tr_head"+av).remove();
		   av = av-1;
		// x = x-1;
		   $("input#count_head").val(av);
		   $("#head_add"+av).show();
		   $("#head_remove"+av).show();
		}
	
	
	function get_selectColumn() {
		
			var v = $("input#count_head").val();
			if (v != 0) {
				$('#selected_tbody').empty();
				for (var i=1; i <= v; i++) {
					var x = i;
					
					
		           var rowgen = '<tr id="tr_selected'+x+'">';
		                         rowgen +='<td style="width: 10%;text-align: center;">'+x+'</td>';
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
								 rowgen += '<div id = "filed1_check'+x+'"></div> ';
									
							 	var hd_table1 =  $("#hd_table"+x).val();
								var t1 = hd_table1.split('-').shift();
								var aliase = hd_table1.split('-').pop();
								
								 rowgen += '<input type="text" id="hd_column'+x+'" name="hd_column'+x+'" class="form-control autocomplete" autocomplete="off"></input>';
								 rowgen += '</div>';
								 rowgen += '</td>';
								 rowgen +='</tr>'; 
								 
	                             $("tbody#selected_tbody").append(rowgen);  
					
	                        	 $.ajaxSetup({
									 async : false
								 });
								 	 $.post("get_table1_field_List?"+key+"="+value, {
										 t1 : t1
							     }, function(j) {
							     }).done(function(j) {
							    	 if (j != "") {
											for(var l=0;l<j.length;l++){
												$("#filed1_check"+x).append( '<label class="dropdown-option"><input type="checkbox" name="multisub_check'+ x+ '" onclick="Coulmn_Name('+ x+ ');"  value="a'+ x+ '.'+j[l]+'" />'+j[l]+' </label><br/>' );
											}
										}

							     }).fail(function(xhr, textStatus, errorThrown) {
							     });
					
				}
			}
	}
	
	
	function showCheckboxes(an) {
		  var checkboxes = document.getElementById("checkboxes");
		  $("#checkboxes"+an).toggle();
		  $("#column_search"+an).val(''); 
		  $('.column_list'+an).each(function () {       
		  $(this).show()
		})
		}
	
	
	function Coulmn_Name(ml) {
		var subjectvar4 = $('input[name="multisub_check'+ml+'"]:checkbox:checked').map(function() {
			return this.value;
		}).get();
		var subject4 = subjectvar4.join(",");
		 $("#hd_column"+ml).val(subject4);
		 $("#show_data").hide();
		 $("#div_filter").show();
		 
		 get_check_filed();
		 get_query();
		 
		   var num=0;
		    $('#column_sub_list').empty()
		    $("thead#thead_data").empty();
		    $('input[name="multisub_check'+ml+'"]').each(function () {  
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
					q+= hd_column_filter + operator +value+" " + andor + " ";
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
				alert("query---" + q);
			$("#create_query").val(q);
	}
	
	
	
	function get_selectColumn() {
		
			var v = $("input#count_head").val();
			if (v != 0) {
				$('#selected_tbody').empty();
				for (var i=1; i <= v; i++) {
				   var x = i;
		           var rowgen = '<tr id="tr_selected'+x+'">';
		                         rowgen +='<td style="width: 10%;text-align: center;">'+x+'</td>';
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
								 rowgen += '<div id = "filed1_check'+x+'"></div> ';
									
							 	var hd_table1 =  $("#hd_table"+x).val();
								var t1 = hd_table1.split('-').shift();
								var aliase = hd_table1.split('-').pop();
								
								 rowgen += '<input type="text" id="hd_column'+x+'" name="hd_column'+x+'" class="form-control autocomplete" autocomplete="off"></input>';
								 rowgen += '</div>';
								 rowgen += '</td>';
								 rowgen +='</tr>'; 
								 
	                             $("tbody#selected_tbody").append(rowgen);  
					
	                        	 $.ajaxSetup({
									 async : false
								 });
								 	 $.post("get_table1_field_List?"+key+"="+value, {
										 t1 : t1
							     }, function(j) {
							     }).done(function(j) {
							    	 if (j != "") {
											for(var l=0;l<j.length;l++){
												$("#filed1_check"+x).append( '<label class="dropdown-option"><input type="checkbox" name="multisub_check'+ x+ '" onclick="Coulmn_Name('+ x+ ');"  value="a'+ x+ '.'+j[l]+'" />'+j[l]+' </label><br/>' );
											}
										}
							     }).fail(function(xhr, textStatus, errorThrown) {
							     });
					
				}
			}
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
				+ '<div id="check_filter'+aller_srno+'">' 
				+ '</div>'
				+ '<input type="text" id="hd_column_filter'+aller_srno+'" name="hd_column_filter'+aller_srno+'" class="form-control autocomplete" autocomplete="off"></input>'
				+ '</div>'
				+ '</td>'
				+'<td>'
				//+ '<select name="operator'+aller_srno+'" id="operator'+aller_srno+'" class="form-control-sm form-control" onchange="fn_Operator('+aller_srno+');" ><option value="0">--Select--</option><option value="=">Equal (=)</option><option value="<">Less Than (<)</option></select> </td>'
				
				+ '<select  id="operator'+aller_srno+'" name="operator'+aller_srno+'" class="form-control-sm form-control" onchange="fn_Operator('+aller_srno+');" ><option value="0">--Select--</option><c:forEach var="item" items="${get_operator_List}" varStatus="num"><option value="${item.codevalue}" name="${item.label}">${item.label}</option></c:forEach></select></td>'
				+'<td><input type="text" id="value'+aller_srno+'" name="value'+aller_srno+'" onkeyup="Value(1);"  maxlength="100"  class="form-control autocomplete" autocomplete="off"></td>'
				+'<td>'
				+ '<select name="andor'+aller_srno+'" id="andor'+aller_srno+'" class="form-control-sm form-control" onchange="fn_AndOr('+aller_srno+');"  ><option value="-1">--Select--</option><option value="and">And</option><option value="or">Or</option></select> </td>'
				+'<td>'
				+'<a class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "allergic_add'+aller_srno+'" onclick="add_fn('+aller_srno+');" ><i class="fa fa-plus"></i></a>'
				+'<a  class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "allergic_remove'+aller_srno+'" onclick="remove_fn('+aller_srno+');"><i class="fa fa-trash"></i></a> '
				+'</td></tr>');
	}
	
	function get_check_filed() {
		var v = $("input#count_head").val();
		 var check_filed_Name =new Array();
		 for (var l = 1; l <= v; l++) {
			 var h = $("input#hd_column"+l).val()
			    check_filed_Name.push(h);	    
				document.getElementById('check_filed').value=check_filed_Name;
		} 
	}
	
	function fn_direction(qw) {
		$("#div_query").show();
		get_query();
	}
	
	$(document).ready(function() {
		//alert("hello----" + '${get_operator_List}');
	}); 
	
	
	</script>