<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %>
<%@ taglib prefix="dandelion" uri="http://github.com/dandelion" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<dandelion:asset cssExcludes="datatables"/>
<dandelion:asset jsExcludes="datatables"/>
<dandelion:asset jsExcludes="jquery"/>

<link href="js/JS_CSS/jquery.dataTables.min.css" rel="stylesheet"> 
<script src="js/JS_CSS/jquery-3.3.1.js"></script>
<script src="js/JS_CSS/jquery.dataTables.js"></script>

<style type="text/css">
	/* 	.dataTable{
	    position: relative;
	    z-index: -2;	
	} */
		table.dataTable, table.dataTable th, table.dataTable td {
			-webkit-box-sizing: content-box;
			-moz-box-sizing: content-box;
			box-sizing: content-box;
			width: auto; 
			text-align: center;
			font-size: 12px;
			padding: 0px;
			font-weight: bold;
		}
		.dataTables_scrollHead {
			/* overflow-y:scroll !important; */
			overflow-x:hidden !important;
		}
		/* .DataTables_sort_wrapper{
			 width : auto;  
		} */

		table.dataTable tr.odd {
	  			background-color: #f0e2f3;
		}
		table.dataTable thead {
  			background-color: #9c27b0;
  			color: white;
  			font-weight: bold;
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
		<datatables:table id="applicanttbl" url="getPSG_UPLOADReportList" serverSide="true" pipelining="true" pipeSize="3"	row="latlon" rowIdBase="applicant_id" rowIdPrefix="latlon_" displayLength="10" lengthMenu="10,20,100,500,50000"  jqueryUI="true"  
	    					bDestroy="true" filterable="true" sortable="true" width="100%" processing="true" border="1" autoWidth="true" pageable="true" paginationType="full_numbers" stateSave="false" deferRender="true"  scrollCollapse="true" >  
			    <datatables:column title="Ser No" cssStyle="text-align:center;" property="sr" searchable="true" data-halign="left" data-valign="left" />
			     <datatables:column title="Unit Name" cssStyle="text-align:center;" property="unit_name" searchable="true" data-halign="left" data-valign="left" />
			     <datatables:column title="Year-Month" cssStyle="text-align:center;" property="date" searchable="true" data-halign="left" data-valign="left" />
			     <datatables:column title="OFFRS DO-II [IAFF-3010]" cssStyle="text-align:center;" property="up_offrs_do_2_iaff_3010"  />
			     <datatables:column title="STR Return [IAFF-3008]" cssStyle="text-align:center;" property="up_str_return_iaff_3008"  />
			    <datatables:column title="OFFRS ARRIVAL REPORT" cssStyle="text-align:center;" property="up_offrs__arrival_rp" />
			    <datatables:column  title="JCO/OR DO-II [IAFF-3010]" cssStyle="text-align:center;" property="up_jco_do_2" />			   
				 <datatables:column title="STR Return [IAFF-3009] [APPX A & B]" cssStyle="text-align:center;" property="up_str_return_iaff_3009_a_b"  />
		</datatables:table>  
<c:url value="getDownloadImagePSG" var="imageDownloadUrl" />
<form:form action="${imageDownloadUrl}" method="post" id="getDownloadImageForm" name="getDownloadImageForm" modelAttribute="id">
	<input type="hidden" name="id" id="id" value="0"/>
	<input type="hidden" name="file_name" id="file_name" value="0"/>
</form:form>
<script>
function getDownloadImage(id,file_name){  
	
	document.getElementById("id").value = id;
	document.getElementById("file_name").value = file_name;
	document.getElementById("getDownloadImageForm").submit();
} 
</script>