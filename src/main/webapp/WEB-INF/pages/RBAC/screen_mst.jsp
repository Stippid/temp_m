<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script type="text/javascript" src="js/amin_module/rbac/jquery-1.12.3.js"></script> 
<script src="js/amin_module/rbac/update.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>
<script src="js/cue/printAllPages.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>


	<script type="text/javascript">
		window.history.forward();
		function noBack() {
			window.history.forward();
		}
		$(document).ready(function () {
			 $('html').bind('cut copy paste', function (e) {
		        e.preventDefault();
		    });
		   
		    $("html").on("contextmenu",function(e){
		        return false;
		    }); 
		    $('#screen_name').keyup(function() {
		        this.value = this.value.toUpperCase();
		    }); 
			watermarkreport();
			
		    if('${module_id1}' != ""){
			    $("div#divwatermark").val('').addClass('watermarked'); 
		   	
		   		$("div#divSerachInput").show();
		   		$("#divPrint").show();
		   		$("#screen_module_id").val('${module_id1}');
		   		sm_id_sel('${module_id1}','${sub_module_id1}');
		    }
		    
			$("#searchInput").on("keyup", function() {
	  			var value = $(this).val().toLowerCase();
	  			$("#ScreenReport tbody tr").filter(function() { 
	  			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
	  			});
	  			
	  			
	  		});
		    
		    try{
	    		   if(window.location.href.includes("msg="))
	    			{
	    				var url = window.location.href.split("?msg")[0];
	    				window.location = url;
	    			} 	
	    		}
	    		catch (e) {
	    			
	    		} 
		    
			});
	</script>

<body >

  <form:form name="screen_mst" id="screen_mst" action="screen_mstAction" method='POST' commandName="screen_mstCMD">

	<div class="container" align="center">
       	<div class="card">
       	
         		<div class="card-header"> <h5>Screen Master</h5></div>
         			<div class="card-body card-block">
         				<div class="col-md-12">
	         				<div class="col-md-6">
	 							<div class="row form-group"> 
	               					<div class="col col-md-6">
	                 					<label for="text-input"  >Screen Name <strong style="color: red;">*</strong></label> 
	               					</div>
	               					<div class="col-12 col-md-6">
	                 					<input  id="screen_name" name="screen_name" style="font-family: 'FontAwesome',Arial;text-transform :uppercase"  class="form-control" autocomplete="off" maxlength="80">
									</div>
	 							</div>
	 						</div> 
	 						<div class="col-md-6">
	 							<div class="row form-group"> 
	               					<div class="col col-md-6">
	                 					<label for="text-input"  >Screen URL <strong style="color: red;">*</strong></label> 
	               					</div>
	               					<div class="col-12 col-md-6">
	                 					<input  id="screen_url" name="screen_url" style="font-family: 'FontAwesome',Arial;"  class="form-control" autocomplete="off" maxlength="125">
									</div>
	 							</div>
	 						</div> 
 						</div>
 					
 						
 						<div class="col-md-12" >
	 						<div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-6">
	                  						<label class=" form-control-label">Module <strong style="color: red;">*</strong></label>
	                					</div>
	                					<div class="col-12 col-md-6">               							
               								<select name="module.id" class="form-control" id ="screen_module_id"  >
		                 						<option value="0">--Select--</option>
		               							<c:forEach var="item" items="${getModuleNameList}" varStatus="num" >
		               								<option value="${item.id}">${item.module_name}</option>
		               							</c:forEach>
		                 					</select>
               							</div>
	                					
							   </div> 
							  </div>
							<div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-6">
	                  						<label class=" form-control-label">Sub-Module <strong style="color: red;">*</strong></label>
	                					</div>
	                					<div class="col-12 col-md-6">
               								<!-- <select name="" class="form-control" id ="modulelist"  onchange="myFunction(event)">  </select> -->
               								<select name="sub_module.id" class="form-control" id ="screen_submodule_id" >
		                 						<option value="0">--Select--</option>
		                 					</select>
               							</div>
							   		</div> 
							  </div>
 						</div>
 						
 						
 						<input type="hidden" name="screen_id" id="screen_id" />
 						
         			</div>
         			<div class="card-footer" align="center">
         	            <input type="reset" class="btn btn-success btn-sm" value="Clear" onclick="clearall();">    	
	              		<input type="submit" id="save_btn"  class="btn btn-primary btn-sm" value="Save" onclick="return isValid();">
	              		<input type="submit" id="update_btn" class="btn btn-primary btn-sm" value="Update" onclick="return isValid();" style="display: none">
	              		<i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm" onclick="return reportScreen();" value="Search">
	              		
         	       </div>
         	</div>
	</div>
      
   </form:form>
   
   
	          <div class="container" id="divSerachInput">
						<div class="col-md-6">
				 			<input id="searchInput" type="text" style="font-family: 'FontAwesome',Arial;margin-bottom: 5px;" placeholder="&#xF002; Search Word"  size="35" width="20%" class="form-control">
				 		</div>
				</div>
   <div class="container"  id="divPrint" >
    <div class="col-md-12">
     <div id="divShow"></div>
	   <div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;"><span id="ip"></span>
		<table id="ScreenReport" class="table no-margin table-striped  table-hover  table-bordered report_print" >
						<thead style="background-color: #9c27b0; color: white;">
							<tr style="font-size: 15px;">
								<th style="text-align:center; width:10%; " >Ser No</th>	
								<th style="width:40%;">Screen Name</th>	
								<th style=" width:15%;">Module Name</th>	
								<th style=" width:15%;">Sub Module Name</th>		
								<th class='lastCol' style="text-align:center; width:10%;">Action</th>
							</tr>
						</thead> 
						<tbody>
							<c:forEach var="item" items="${list}" varStatus="num" >
									<tr style="font-size: 13px;">
										<td style="text-align:center; width:10%; ">${num.index+1}</td>
										<td style="width:40%;">${item.screen_name}</td>
										<td style=" width:15%;">${item.module_name}</td>
										<td style=" width:15%;">${item.submodule_name}</td>	
										<td style="text-align:center; width:10%;">${item.id}</td>					
									</tr>
							</c:forEach>
				        </tbody>
	   </table>
	</div>	
</div>
</div> 	
	<c:url value="screen_report" var="screen_reportUrl" />
		<form:form action="${screen_reportUrl}" method="post" id="searchForm" name="searchForm" >
			<input type="hidden" name="module_id1" id="module_id1" value="0"/>
			<input type="hidden" name="sub_module_id1" id="sub_module_id1" value="0"/>
			
		</form:form> 
   
   
 
   
   <script>
  
	   function isValid()
	   {	
	   	
		   if($("input#screen_name").val()==""){
				alert("Please Enter Screen Name");
				$("input#screen_name").focus();
				return false;
			}
		   
	   		if($("input#screen_url").val()==""){
	   			alert("Please Select Screen URL");
	   			$("input#screen_url").focus();
	   			return false;
	   		} 
	   		
	   		if($("select#screen_module_id").val() == 0){
	   			alert("Please Select Module");
	   			$("select#screen_module_id").focus();
	   			return false;
	   		} 
	   		
	   		if($("select#screen_submodule_id").val() == 0){
	   			alert("Please Select Sub Module");
	   			$("select#screen_submodule_id").focus();
	   			return false;
	   		} 
	  
	   	return true;
	   
	   }
   
   </script>
       


<script>
   $(document).ready(function () {
		    $('select#screen_module_id').change(function() {
		    var mid = this.value; 
		    var sList = new Array();
		    var options = '<option value="'+"0"+'">'+ "--Select--" + '</option>';
			<c:forEach var="item" items="${getSubModuleNameList}" varStatus="num" >
				if('${item.module.id}' == mid){
					options += '<option value="${item.id}">${item.submodule_name}</option>';
				}
			</c:forEach>
			$("select#screen_submodule_id").html(options); 
		  });  
   });
  
   function sm_id_sel(mid,smid){
	   var options = '<option value="'+"0"+'">'+ "--Select--" + '</option>';
	   <c:forEach var="item" items="${getSubModuleNameList}" varStatus="num" >
		if('${item.module.id}' == mid){
			 if('${item.id}' == smid){
				options += '<option value="${item.id}" selected >${item.submodule_name}</option>';
			 }else{
				 options += '<option value="${item.id}" >${item.submodule_name}</option>';
			 }
		}	
		</c:forEach>
		$("select#screen_submodule_id").html(options);
   }
   
   function clearall()
   {		
   	document.getElementById('divPrint').style.display='none';
  
   	$("#searchInput").val("");
   	$("div#divSerachInput").hide();  
   	
   }
  
</script>

<script>
function reportScreen(){
	
	$("#module_id1").val($("select#screen_module_id").val());
	$("#sub_module_id1").val($("select#screen_submodule_id").val());
	
	document.getElementById('searchForm').submit();
}
</script>

<script>
function Update(id,sc_name,sc_url,mid,smid){	
	document.getElementById('screen_name').value=sc_name;
	document.getElementById('screen_url').value=sc_url;
	document.getElementById("screen_url").readOnly = true; 	
	$("select#screen_module_id").val(mid);	
	sm_id_sel(mid,smid);
	document.getElementById('screen_id').value=id;
	document.getElementById('update_btn').style.display='inline-block'; 
	document.getElementById('save_btn').style.display='none'; 
}

</script>
<script>
$(function(){
	$('#screen_name').keyup(function(){	
		yourInput= this.value.toUpperCase();
		re = /[0-9`~!@#$%^&*()_|+\-=?;:'",.<>\{\}\[\]\\]/gi;
		var isSplChar = re.test(yourInput);
		if(isSplChar)
		{
			//alert("Don't Enter Special Character");
			var no_spl_char = yourInput.replace(/[0-9`~!@#$%^&*()_|+\-=?;:'",.<>\{\}\[\]\\]/gi, '');
			$(this).val(no_spl_char);
		}
	});
});
</script>


</body>
</html>

