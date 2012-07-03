
var init = function()
{
	var selectQueryStr = "select.feedback_select";
	var $feedbackCombo = $( selectQueryStr );
	$feedbackCombo.change( comboBoxHandler );
};

var waitDivQuery = "div.wait-div";

var hideWaitDiv = function()
{
	var $waitDiv = $( waitDivQuery );
	$waitDiv.hide();
};

var showWaitDiv = function()
{
	
	var $waitDiv = $( waitDivQuery );
	$waitDiv.show();
};

var comboBoxHandler = function()
{
	var $this = $(this);
	var $docId = $this.attr( "docId" );
	var $feedbackValue = $this.val();
	
	showWaitDiv();
	FeedbackUpdater.updateFeedback( $docId, $feedbackValue, dwrCallback );
};

var dwrCallback = function( returnedValue )
{
	if( returnedValue === true )
	{
		alert("Feedback updated.");
	}
	else
	{
		alert("Feedback not updated.");
	}
	hideWaitDiv();
};

// wait for the DOM to be loaded 
$(document).ready(function() { 
	init();
}); 
