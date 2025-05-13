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
	    	<div class="container" align="center">
	    		<div class="card">
	    		<div class="card-header"><h5>AD HOC</h5> <h6 class="enter_by">
<!-- 	    		<span style="font-size:12px;color:red;">(To be entered by Unit)</span> -->
	    		</h6></div>
	        	</div>
			</div>
	</div>
	
	
		<div class="container" id="ad_hoc_div">
		<div class="">
			<div id="divShow" style="display: block;"></div>
			<div class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
				<span id="ip"></span>
			<%-- 	<table id="pay_level_search" class="table no-margin table-striped  table-hover  table-bordered report_print" width="100%">
					<thead style="text-align: center;">
						<tr>
							<th>Ser No</th>
							<th>Personal No</th>
							<th>Cadet No</th>
							<th>Name</th>
							<th>Rank</th>
							
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${list}" varStatus="num">
							<tr>
								<td style="text-align: center;">${num.index+1}</td>
								<td>${item.personnel_no}</td>
								<td>${item.cadet_no}</td>
								<td>${item.per_name}</td>
								<td style="text-align: center;">${item.rank}</td>
							</tr>
						</c:forEach>
					</tbody>
					<c:if test="${list.size()==0}">
						<tr>
							<td style="font-size: 15px; text-align: center; color: red;" colspan="3">Data Not Available</td>
						</tr>
					</c:if>
				</table> --%>
				
				<table id="ad_hoc_table" class="table-bordered " style="margin-top:10px; width: -webkit-fill-available;">
					<thead style=" color: white; text-align: center;">
						<tr>
							<th>Sr No</th>
							<th>Type</th> 
							<th>Column</th> 
							<th>Condition</th> 
							<th>Parameter</th> 
							<th>Action</th>
					   </tr>
					</thead>
				   		<tbody data-spy="scroll" id="allergictbody" style="min-height: 46px; max-height: 650px; text-align: center;">
							<tr id="tr_allergic1">
								<td class="aller_srno">1</td>
								<td>    <select name="p_type" id="p_type" class="form-control-sm form-control" onchange="Type(this.value);"  >
												<option value="0">--Select--</option>
												<option value="rank">RANK</option>
												<option value="name">NAME</option>
												<option value="qualification">QUALIFICATION</option>
												<%-- <c:forEach var="item" items="${getTypeofRankList}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach> --%>
											</select> 
								</td>
							<td>
							   <select name="rk_type" id="rk_type" class="form-control"   >
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${getOFFTypeofRankList}" varStatus="num">
														<option value="${item[1]}" name="${item[1]}">${item[1]}</option>
														</c:forEach>
											      </select> 
								</td>
								<td>   <select name="p_type" id="p_type" class="form-control-sm form-control" onchange="Type(this.value);"  >
												<option value="0">--Select--</option>
												<option value="less than">Less Than (<)</option>
												<!-- <option value="name">NAME</option>
												<option value="qualification">QUALIFICATION</option> -->
												<%-- <c:forEach var="item" items="${getTypeofRankList}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach> --%>
											</select> 
								</td>
								<td><input type="text" id="allergic1" name="allergic1" maxlength="100"onkeypress="return onlyAlphaNumeric(event);"
									class="form-control autocomplete" autocomplete="off">
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
			+ '<select name="firing_event_qe'+aller_srno+'" id="firing_event_qe'+aller_srno+'" class="form-control"   >'
			+'<option value="rank">RANK</option><option value="name">NAME</option><option value="qualification">QUALIFICATION</option>'
			+ '</select> </td>'
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