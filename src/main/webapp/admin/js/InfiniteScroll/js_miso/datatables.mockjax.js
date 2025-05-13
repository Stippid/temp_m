jsondata = [];
var FilteredRecords = 0;
function dataTable(tableName,centerColArray,rightColArray){
	var table = $('#'+tableName).DataTable({
		"order": [[ 0, "asc" ]],
		"lengthMenu": [[10, 25, 50,100, -1 ], [10, 25, 50,100, "All"]],
		"scrollY":        "400px",
	    "scrollCollapse": true,
	    "sPaginationType": "full",
	    "bLengthChange" : true,        
	    'language': {
            'loadingRecords': '&nbsp;',
            'processing': '<div class="spinner"></div>'
        },
		ajax: '/Data',
		'processing': true,
		"serverSide": true,
		'columnDefs': [
		    { 'className': 'dt-body-center', targets: centerColArray},//align center as  particular Column
		    { 'className': 'dt-body-right', targets: rightColArray},//align center as  particular Column
		    { 'orderable': false, targets: -1} //sorting disable as particular Column 
		]
	});
	return table;
}
function mockjax1(tableName){
	$.mockjax({
	    url: '/Data',
	    responseTime: 1000,
	    response: function(settings){
	    	$.ajaxSetup({
				async : false
			});
			data(tableName);
			this.responseText = {
	    		draw: settings.data.draw,
				data: jsondata,
				recordsTotal: jsondata.length,
	            recordsFiltered: FilteredRecords
			};
	     }
	});
}
