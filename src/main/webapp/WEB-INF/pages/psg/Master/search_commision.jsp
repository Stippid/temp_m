<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<link href="js/cue/cueWatermark.css" rel="Stylesheet"></link>

<form:form name="" id="" action="" method="post" class="form-horizontal" commandName=""> 
	<div class="animated fadeIn">
	    	<div class="container" align="center">
	    		<div class="card">
	    		<div class="card-header"><h5>Search Type of Commission</h5> <h6 class="enter_by"><span style="font-size:12px;color:red;">(To be entered by MISO)</span></h6></div>
	          			<div class="card-body card-block">
	            			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Commission Name</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="comn_name" name="comn_name"  class="form-control autocomplete" autocomplete="off" > 
						                </div>
						            </div>
	              				</div>	              				
	              			</div>	              			
	              			
            			</div>
									
			<div class="form-control card-footer" align="center">
				<a	href="search_commission" type="reset" class="btn btn-primary btn-sm"> Clear </a> 
				<i class="fa fa-search"></i><input type="button" class="btn btn-success btn-sm" value="Search"	onclick="SearchData();">
			</div>		
	        	</div>
	        	<div class="container" id="divPrint" style="display: none;">
			<div class="watermarked" data-watermark="" id="divwatermark">
				<span id="ip"></span>
				<table id="SearchReport"
					class="table no-margin table-striped  table-hover  table-bordered report_print">
					<thead>
						<tr>
							<th width="55px">Ser No</th>
							<th>Commission</th>							
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${not empty list}">
							<c:forEach var="item" items="${list}" varStatus="num">
								<tr>
									<td width="55px">${num.index+1}</td>
									<td>${item.comn_name}</td>									
									<td id="thAction1">${item.comn_id}</td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
					<c:if test="${list.size()==0}">
						<tr>
							<td style="font-size: 15px; text-align: center; color: red;"
								colspan="3">Data Not Available</td>
						</tr>
					</c:if>
				</table>
			</div>

		</div>
			</div>
	</div>
</form:form>
<c:url value="Search_commissionUrl" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"	name="searchForm" modelAttribute="comn_name1">
	<input type="hidden" name="comn_name1" id="comn_name1" value="0" />
	
</form:form>

<c:url value="edit_commision" var="editUrl" />
<form:form action="${editUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2" value="0"/>
</form:form>
 
 <c:url value="delete_comm" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form> 
<script>
function SearchData(){
	
	$("#comn_name1").val($("#comn_name").val());
	$("#searchForm").submit();
}
 	
 function editData(id2){	
	
	 document.getElementById('id2').value=id2;
		document.getElementById('updateForm').submit();

} 
 function deleteData(id){
	
	$("#id1").val(id);
	document.getElementById('deleteForm').submit();
}  

</script>

<script>
$(document).ready(function() {
	
	
	if('${size}' != 0){
		$("#divPrint").show();
	} 
	
	if('${size}' == 0 && '${size}' != ""){
		$("#divPrint").show();
	}
	

	var q = '${comn_name1}';
	
	
	if(q != ""){		
		$("#comn_name").val(q);
	}
	
    
	try{
		if(window.location.href.includes("msg=")){
			var url = window.location.href.split("?")[0];
		    window.location = url;
	    } 
	}catch (e) {
		// TODO: handle exception
	}
});



</script>
