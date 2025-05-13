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

 
	<div class="animated fadeIn">
	    	<div class="col-md-12" align="center">
	    		<div class="card">
	    		<div class="card-header"><h5>AD HOC</h5> <h6 class="enter_by">
<!-- 	    		<span style="font-size:12px;color:red;">(To be entered by Unit)</span> -->
	    		</h6></div>
	    		
	    				<div class="card-body card-block">
	            	   
	              			<div class="col-md-12">	              					
	              		<div class="col-md-6">
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
										<div>
											<c:forEach var="item" items="${getColumnList}" varStatus="num">
												<label for="one" class="column_list"> <input onclick="Coulmn_Name();"
													type="checkbox" name="multisub" value="${item}" />
													${item}
												</label>
											</c:forEach>
										</div>
										
										<input type="text" id="hd_column" name="hd_column" class="form-control autocomplete" autocomplete="off"></input>
									</div>

								</div>
							</div>
						</div>
					</div>
					
						<div class="col-md-6">
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
									<div id="checkboxes_filter" class="checkboxes"
										style="max-height: 200px; overflow: auto;">
										<div style="">
											<input type="text" id="column_search_filter"
												class="form-control autocomplete" autocomplete="off"
												placeholder="Search Fields">
										</div>
										<div>
											<c:forEach var="item" items="${getColumnList}" varStatus="num">
												<label for="one" class="column_list_filter"> <input onclick="Coulmn_Name_Filter(1);"
													type="checkbox" name="multisub_filter" value="${item}" />
													${item}
												</label>
											</c:forEach>
										</div>
										<input type="text" id="hd_column_filter1" name="hd_column_filter1" class="form-control autocomplete" autocomplete="off"></input>
									</div>
								
								</td>
							<td>
							 <select name="operator1" id="operator1" class="form-control-sm form-control" >
												<option value="0">--Select--</option>
												<option value="=">Equal (=)</option>
												<option value="less than">Less Than (<)</option>
											</select> 
								</td>
								<td>  
											<input type="text" id="value1" name="value1" maxlength="100" 
									class="form-control autocomplete" autocomplete="off">
								</td>
								<td> <select name="andor1" id="andor1" class="form-control-sm form-control" onchange="Type(this.value);"  >
												<option value="0">--Select--</option>
												<option value="and">And</option>
												<option value="or">Or</option>
											</select> 
											
								</td>
							<td>
							<!-- <a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "allergic_save1" onclick="allergic_save_fn(1);" ><i class="fa fa-save"></i></a> -->
							   <a class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "allergic_add1" onclick="add_fn(1);" ><i class="fa fa-plus"></i></a>
							    <a style="display:none;" class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "allergic_remove1" onclick="allergic_remove_fn(1);"><i class="fa fa-trash"></i></a> 
							</td>
							</tr>
				   </tbody> 
						</table>
			</div>
						 
						</div>
						
							<div class="card-footer" align="center" class="form-control">
							<a href="ad_hoc_Url" class="btn btn-success btn-sm" >Clear</a>  
		              		<input type="button" class="btn btn-info btn-sm" onclick="Search('rank');" value="Search"> 
			            </div> 
						
					</div>
				</div>
			</div>
		</div>
		</div>
	
	
	
	
	<c:url value="GetSearch_Pers" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="p_type1">
		<input type="hidden" name="p_type1" id="p_type1" />				
		<input type="hidden" name="rank_parameter1" id="rank_parameter1" />		
		<input type="hidden" name="rk_type1" id="rk_type1" />		
		<input type="hidden" name="name1" id="name1" />		
	</form:form>
	
	
	<Script>
	
	function Search(s){
		
		
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
	}
	
	
	$(document).ready(function() {
		
		 if('${p_type}' != ""){
			 
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
			}
		 
		
		 
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
		
		if (obj.id == "a_final") {
		}
		}
		
		
		
aller=1;
aller_srno=1;
function add_fn(q){
	 if( $('#allergic_add'+q).length )         
	 {
			$("#allergic_add"+q).hide();
	 }
	 aller=q+1;
	if(q!=0)
		aller_srno+=1;
	$("table#ad_hoc_table").append('<tr id="tr_allergic'+aller+'">'
			+'<td class="aller_srno">'+aller_srno+'</td>'
			+'<td>'
			/* + '<select name="firing_event_qe'+aller_srno+'" id="firing_event_qe'+aller_srno+'" class="form-control"   >'
			+'<option value="rank">RANK</option><option value="name">NAME</option><option value="qualification">QUALIFICATION</option>'
			+ '</select>
			
			nmk
			 */
			
			+ '<div class="selectBox" onclick="showCheckboxes_Filter('+ aller_srno+ ')">'
			+ '<select name="sub_column" id="sub_column"'
			+ '	class=" form-control">'
			+ '	<option>Select Fields</option>'
			+ '</select>'
			+ '	<div class="overSelect"></div>'
			+ '</div>'
			+ '<div id="checkboxes_filter" class="checkboxes"'
			+ 'style="max-height: 200px; overflow: auto;">'
			+ '<div style="">'
			+ '	<input type="text" id="column_search_filter"'
			+ '	class="form-control autocomplete" autocomplete="off"'
			+ '	placeholder="Search Fields">'
			+ '</div>'
			+ '<div>'
			+ '	<c:forEach var="item" items="${getColumnList}" varStatus="num">'
			+ '		<label for="one" class="column_list_filter"> <input onclick="Coulmn_Name_Filter(1);"'
			+ '			type="checkbox" name="multisub_filter" value="${item}" />'
			+ '			${item}'
			+ '		</label>'
			+ '	</c:forEach>'
			+ '</div>'
			+ '<input type="text" id="hd_column_filter'+aller_srno+'" name="hd_column_filter'+aller_srno+'" class="form-control autocomplete" autocomplete="off"></input>'
			+ '</div>'
			
			+ '</td>'
			+'<td>'
			+ '<select name="firing_event_qe'+aller_srno+'" id="firing_event_qe'+aller_srno+'" class="form-control"   >'
			+'<option value="rank">RANK</option><option value="name">NAME</option><option value="qualification">QUALIFICATION</option>'
			+ '</select> </td>'
			+'<td>'
			+ '<select name="firing_event_qe'+aller_srno+'" id="firing_event_qe'+aller_srno+'" class="form-control"   >'
			+'<option value="rank">RANK</option><option value="name">NAME</option><option value="qualification">QUALIFICATION</option>'
			+ '</select> </td>'
			+'<a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "allergic_save'+aller+'" onclick="allergic_save_fn('+aller+');" ><i class="fa fa-save"></i></a>'
			+'<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "allergic_add'+aller+'" onclick="add_fn('+aller+');" ><i class="fa fa-plus"></i></a>'
			+'<a style="display:none;" class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "allergic_remove'+aller+'" onclick="allergic_remove_fn('+aller+');"><i class="fa fa-trash"></i></a> '
			+'</td></tr>');
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
	    $("input[type='checkbox'][name='multisub']").each(function () {  
	        if ( this.checked ) {   
	        	
	        	/* $('#column_sub_list').append("<span class='subspan'>"+this.parentElement.innerText+"<i class='fa fa-times' style='margin: 5px;  font-size: 15px;' onclick='removeSubFn('"+this.value+"')'></i><span> <br>"); */
	            
	        	$('#column_sub_list').append("<span class='subspan'>"+this.parentElement.innerText+"<span> <br>");
	        	num=num+1;
	        }
	        
	    });
	    if(num!=0)
	        $("#sub_column option:first").text('Subjects('+num+')');
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
	        $("#sub_column option:first").text('Subjects('+num+')');
	    else
	        $("#sub_column option:first").text("Select Fields");
	}
	
	
	function Coulmn_Name() {
		var subjectvar = $('input[name="multisub"]:checkbox:checked').map(function() {
			return this.value;
		}).get();
		var subject = subjectvar.join(",");
		 $("#hd_column").val(subject);
	}
	
	
	
	function showCheckboxes_Filter(n) {
		  var checkboxes = document.getElementById("checkboxes");
		  $("#checkboxes_filter"+n).toggle();
		  $("#column_search_filter").val(''); 
		  
		  $('.column_list_filter').each(function () {       
		  $(this).show()
		})
		}
	
	
	function Coulmn_Name_Filter(a) {
		var subjectvar1 = $('input[name="multisub_filter"]:checkbox:checked').map(function() {
			return this.value;
		}).get();
		var subject1 = subjectvar1.join(",");
		 $("#hd_column_filter1").val(subject1);
	}
	
	</script>