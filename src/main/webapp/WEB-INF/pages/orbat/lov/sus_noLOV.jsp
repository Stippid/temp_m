<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %>
<%@ taglib prefix="dandelion" uri="http://github.com/dandelion" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	HttpSession sess = request.getSession(false);
	if (sess.getAttribute("userId") == null) { response.sendRedirect("~/login"); return; } 
%>
<html>
  <head>
	<title> Unit Name LOV</title>
	<link href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css"  rel="stylesheet">
	<link href="js/JS_CSS/jquery.dataTables.min.css" rel="stylesheet"> 
	<script src="js/JS_CSS/jquery-3.3.1.js"></script>
	<script src="js/JS_CSS/jquery.dataTables.js"></script>
	<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
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
	</style>
	<style>
		.search_header{
			padding: .75rem 1.25rem;
			margin-bottom: 0;
			background-color: rgba(0,0,0,.03);
			border-bottom: 1px solid rgba(0,0,0,.125);
			text-align: center;
			position: relative;
		}
	 	.search_content{
			display: flex;
		 	padding: 1.25rem;
		 	align-items: center;
		 	padding-bottom: 0;
	 	}
   		.search_content label{
			display: inline-block;
		 	margin-bottom: .5rem;
	 	}
	 	.form-control{
			display: block;
			width: 100%;
			padding: .375rem .75rem;
			font-size: 1rem;
			line-height: 1.5;
			color: #495057;
			background-color: #fff;
			background-clip: padding-box;
			border: 1px solid #ced4da;
			border-radius: .25rem;
			transition: border-color .15s ease-in-out,box-shadow .15s ease-in-out;
	 	}
	 	.search_buttons{
			text-align: right;
			position: absolute;
     		top: 9px;
     		right: 9px;
	 	}
	 	.search_buttons_footer{
			text-align: right;
	 	}
	 	.search_content label{
			width: 100%;
	 	}
	 	.search_content input{
			width: 100%;
	 	}
	 	.search_header h3{
			margin: 0;
			margin-right: 55px;
	 	}
	 	.btn{
    		text-align: center;
    		transition: all .15s ease-in-out;
    		cursor: pointer;
			padding: 2px 8px;
    		font-size: .875rem;
    		line-height: 1.5;
			color: #fff;
	 	}
	 	.btn-success{
    		background-color: #28a745;
    		border: 1px solid #28a745;
	 	}
	 	.btn-success:hover{
			background-color: #1e7e34;
    		border-color: #1c7430;
	 	}
	 	.btn-primary{
    		background-color: #0069d9;
			border: 1px solid #0069d9;
	 	}
	 	.btn-primary:hover{
    		background-color: #0d4d92;
			border-color:  #0d4d92;
		}
	</style>
	<script>
	
		$(document).ready(function() {
			$("#target_unit_name").val('${target_unit_name_lov}');
			$("#target_sus_no").val('${target_sus_no_lov}');
			var status = sessionStorage.getItem("susStatusLov"); 
			if(status == null){
				$("#addUnit").hide();
			}
			
		});
		
		function actionsRadioButton(data, type, full) {
			var f="";
			var radio = "onclick=\"  setid('"+full.unit_name+"','"+full.sus_no+"')\"";
			f += '<input type="radio" style=" width: 1.4em;height: 1.4em;background-color: red;" class="redioBtn" id="redioBtn" name="redioBtn" '+radio+' title="Select Data">'
			return f;
		}
		function setid(target_unit_name,target_sus_no){
			$("#target_unit_name1").val(target_unit_name);
			$("#target_sus_no1").val(target_sus_no);
			return true;
		}  
		function CloseMySelf(){
			var unit_name = $("#target_unit_name1").val();
			var sus_no = $("#target_sus_no1").val();
			if(unit_name == ""){
				alert("please select any one");
				return false;
			}
			if(sus_no == ""){
				alert("please select any one");
				return false;
			}
			try {
				window.opener.HandlePopupResultUnitName(unit_name,sus_no);
		    }
			catch (err) {}
			window.close();
			return false;
		} 
		function searchUnitList(){
			sessionStorage.setItem("susStatusLov","1");
			var status = sessionStorage.getItem("susStatusLov"); 
			if(status = "1"){
				$("#addUnit").show();
			}
			document.getElementById('target_unit_name12').value = $("#target_unit_name").val();
			document.getElementById('target_sus_no12').value =  $("#target_sus_no").val();
			document.getElementById("searchUnitListForm").submit();
		}
		function Close()
		{
		  	try {
		    	//loc,nrs_name,loc_code,trn_type,typeofloc,nrscode
		        window.opener.HandlePopupResult('','','','','','');
		    }
		    catch (err) {}
		    window.close();
		    return false; 
		    return true;	   
		}
		function clearAll()
		{
			$("#target_unit_name").val('');
			$("#target_sus_no").val('');
			$("table#addQuantity tbody").empty();
		}
	</script> 
</head>	
<body>
	<div class="animated fadeIn">
		<div class="row">
	    	<div class="container" align="center">
	    		<div class="card">
	    			<div class="search_header"> 
						 <h3>SEARCH UNIT NAME</h3>
						<div class="search_buttons">
								<input type="reset" class="btn btn-success" value="Clear" onclick="clearAll();">   
								<input type="button" class="btn btn-primary" value="Search" onclick="searchUnitList();">
						</div> 
					</div> 
					<div class="card-body card-block">
	          			<div class="col-md-12">
              				<div class="row form-group">
                				<div class="search_content">
                				    <label class=" form-control-label">Unit Name  </label>
								   	<input type="text" id="target_unit_name" name="target_unit_name" class="form-control " autocomplete="off">
									<label class=" form-control-label">SUS No  </label>
								   	<input type="text" id="target_sus_no" name="target_sus_no" class="form-control" placeholder="Search" autocomplete="off">
								</div>
                			</div>
                		</div>
                	</div>
                </div>
             </div>
        </div>
        <hr>
        <div  class="row">
        	<div class="col-md-12" id="addUnit" >						
				<datatables:table id="applicanttbl" url="LovUnit_SusNoList" serverSide="true" pipelining="true" pipeSize="3"	row="latlon" rowIdBase="applicant_id" rowIdPrefix="latlon_" displayLength="10"  jqueryUI="true"
		 					bDestroy="true" filterable="false" sortable="true" processing="true" border="1" scrollY="398" scrollX="100%" scrollCollapse="true" autoWidth="true" pageable="true" paginationType="full_numbers" stateSave="false" deferRender="true">  
					    <datatables:column title="Ser No" property="id" searchable="false" data-halign="left" data-valign="left" visible="false" />							    
					     <datatables:column title="Actions" renderFunction="actionsRadioButton" sortable="false" searchable="false" display="HTML" />
						<datatables:column title="Unit Name" property="unit_name"  data-halign="left" data-valign="left" />
						<datatables:column title="SUS No" property="sus_no"  data-halign="left" data-valign="left"/>  
				</datatables:table>  						  
		    </div>
        </div>
        <hr>
        <div  class="row" align="right">
	        <input type="hidden" id="target_unit_name1" name="target_unit_name1" placeholder="" class="form-control">
			<input type="hidden" id="target_sus_no1" name="target_sus_no1" placeholder="" class="form-control">
			<input type="submit" class="btn btn-primary btn-sm" onclick="return CloseMySelf();"  value="Select" >
			<input type="button" class="btn btn-success btn-sm" onclick="return Close();" value="Close">
        </div>
    </div>
	          				        	
    <c:url value="searchUnitListUrl" var="unitUrl" />
	<form:form action="${unitUrl}" method="post" id="searchUnitListForm" name="searchUnitListForm" modelAttribute="target_sus_no12">
		<input type="hidden" name="target_unit_name12" id="target_unit_name12" value="0"/>
		<input type="hidden" name="target_sus_no12" id="target_sus_no12" value="0"/>
	</form:form> 
</body>
</html>

