$(document).ready(function(){
    $('#myForm').submit(function(){

        var RuleObject = []


        $('.rule-block').each(function () {

            var temp = {
                rule1Effect: $(this).find('.rule1Effect').val(),
                rule1Name: $(this).find('.rule1Name').val(),
                rule1TargetResource:$(this).find('.rule1TargetResource').val(),
                rule1TargetAction:$(this).find('.rule1TargetAction').val(),
                rule1TargetSubject: $(this).find('.rule1TargetSubject').val(),
                rule1location1: $(this).find('#rule1location1').val(),
                rule1location2: $(this).find('#rule1location2').val(),
                rule1timeFrom: $(this).find('.rule1timeFrom').val(),
                rule1timeUntil: $(this).find('.rule1timeUntil').val(),
                rule1ipRequest: $(this).find('.rule1ipRequest').val(),
                rule1ipRecipient: $(this).find('.rule1ipRecipient').val(),
                rule1typeofdev1: $(this).find('#rule1typeofdev1').val(),
                rule1typeofdev2: $(this).find('#rule1typeofdev2').val()

            };

            RuleObject.push(temp);
            var policyFObjects ={
                PolicyObject,
                RuleObject

            }

            PolicyObject = PolicyObject + RuleObject
            console.log(JSON.stringify(PolicyObject))

        });
        var PolicyObject = {
            policyName : $('#policyName').val(),
            ruleCombAl: $('#ruleCombAl').val(),
            policyDesc:$('#policyDesc').val(),
            policyTargetResource:$('#policyTargetResource').val(),
            policyTargetAction:$('#policyTargetAction').val(),
            policyTargetSubject:$('#policyTargetSubject').val(),
            RuleObject : RuleObject

        }
        console.log(JSON.stringify(PolicyObject))
        $.ajax({
            type: "POST",
            contentType: "application/json",
            dataType: "json",
            async : false,
            data: JSON.stringify(PolicyObject),
            url: "http://localhost:8080/createPolicy",
            success: function(responseData){

                alert(responseData.message);
            },
            error: function(){
            }
        });
    });
});
var number =0;
function addRule(){
    number++

    $("#myForm").append('' +
        '<div class="rule-block">' +
        '    <p class="field required half">\n' +
        '        <label class="label required denmenoiazei"  required="<%= true %>" for="rule1Effect">Rule '+number+' effect </label>\n' +
        '        <select class="denmenoiazei required rule1Effect " name="rule1Effect" required="<%= true %>" id="rule1Effect">\n' +
        '           <option value="">--Please select an option--</option>\n' +
        '           <option value="Permit">Permit</option>\n' +
        '           <option value="Deny">Deny</option>\n' +
        '       </select>\n' +
        '    </p>\n' +
        '\n' +
        '    <p class="field required half">\n' +
        '        <label class="label" for="rule1Name">Rule '+number+' Name</label>\n' +
        '        <input class="text-input rule1Name" id="rule1Name"  name="rule1Name" required="<%= true %>" type="text"/>\n' +
        '    </p>\n'+
        '\n' +
        '    <p class="field  half">\n' +
        '        <label class="label" for="rule1TargetResource">Rule '+number+' Target Resource</label>\n' +
        '        <input class="text-input rule1TargetResource" id="rule1TargetResource" name="rule1TargetResource"  type="text">\n' +
        '    </p>\n' +
        '    <p class="field  half">\n' +
        '        <label class="label" for="rule1TargetAction">Rule '+number+' Target Action</label>\n' +
        '        <input class="text-input rule1TargetAction" id="rule1TargetAction" name="rule1TargetAction"  type="text">\n' +
        '    </p>\n' +
        '    <p class="field  half">\n' +
        '        <label class="label" for="rule1TargetSubject">Rule '+number+' Target Subject</label>\n' +
        '        <input class="text-input rule1TargetSubject" id="rule1TargetSubject" name="rule1TargetSubject"  type="text">\n' +
        '    </p>\n' +
        '    <p class="field  half">\n' +
        '        <label class="label" for="rule1location1">Rule '+number+' Location 1</label>\n' +
        '        <input class="text-input rule1location1" id="rule1location1" name="rule1location1" type="text">\n' +
        '    </p>\n' +
        '    <p class="field  half">\n' +
        '        <label class="label" for="rule1location2">Rule '+number+' Location 2</label>\n' +
        '        <input class="text-input rule1location2" id="rule1location2" name="rule1location2"  type="text">\n' +
        '    </p>\n' +
        '\n' +
        '    <p class="field  half">\n' +
        '        <label class="label" for="rule1timeFrom">Rule '+number+' Time from :</label>\n' +
        '        <input class="text-input rule1timeFrom" id="rule1timeFrom" name="rule1timeFrom" placeholder="00:00:00"  type="text">\n' +
        '    </p>\n' +
        '\n' +
        '    <p class="field  half">\n' +
        '        <label class="label" for="rule1timeUntil">Rule '+number+' Time Until:</label>\n' +
        '        <input class="text-input rule1timeUntil" id="rule1timeUntil" name="rule1timeUntil" placeholder="00:00:00" type="text">\n' +
        '    </p>\n' +
        '    <p class="field  half">\n' +
        '        <label class="label" for="rule1ipRequest">Rule '+number+' Ip Address Client </label>\n' +
        '        <input class="text-input rule1ipRequest" id="rule1ipRequest" name="rule1ipRequest"  type="text">\n' +
        '    </p>\n' +
        '\n' +
        '    <p class="field  half">\n' +
        '        <label class="label" for="rule1ipRecipient">Rule '+number+' Ip Server:</label>\n' +
        '        <input class="text-input rule1ipRecipient" id="rule1ipRecipient" name="rule1ipRecipient"  type="text">\n' +
        '    </p>\n' +
        '\n' +
        '    <p class="field  half">\n' +
        '        <label class="label" for="rule1typeofdev1">Rule '+number+' Type Of Device 1:</label>\n' +
        '        <input class="text-input rule1typeofdev1" id="rule1typeofdev1" name="rule1typeofdev1"  type="text">\n' +
        '    </p>\n' +
        '    <p class="field  half">\n' +
        '        <label class="label" for="rule1typeofdev2">Rule '+number+' Type Of Device 2 </label>\n' +
        '        <input class="text-input rule1typeofdev2" id="rule1typeofdev2" name="rule1typeofdev2"  type="text">\n' +
        '    </p></div>')
}
$(document).ready(function(){
    $('#policyName').change(function(e){
        createDynamText();
    });
    $('#ruleCombAl').change(function(e){
        createDynamText();
    });
    $('#policyDesc').change(function(e){
        createDynamText();
    });
    $('#policyTargetResource').change(function(e){
        createDynamText();
    });
    $('#policyTargetAction').change(function(e){
        createDynamText();
    });
    $('#policyTargetSubject').change(function(e){
        createDynamText();
    });
    $('#btn').click(function(e){
        activateRuleListeners();
    });
});
function activateRuleListeners () {
    $('.rule-block').each(function () {
        $(this).find('.rule1Effect').change(function(e){
            createDynamText();
        });
        $(this).find('.rule1Name').change(function(e){
            createDynamText();
        });
        $(this).find('.rule1TargetResource').change(function(e){
            createDynamText();
        });
        $(this).find('.rule1TargetAction').change(function(e){
            createDynamText();
        });
        $(this).find('.rule1TargetSubject').change(function(e){
            createDynamText();
        });
        $(this).find('#rule1location1').change(function(e){
            createDynamText();
        });

        $(this).find('#rule1location2').change(function(e){
            createDynamText();
        });
        $(this).find('.rule1timeFrom').change(function(e){
            createDynamText();
        });
        $(this).find('.rule1timeUntil').change(function(e){
            createDynamText();
        });
        $(this).find('.rule1ipRequest').change(function(e){
            createDynamText();
        });
        $(this).find('.rule1ipRecipient').change(function(e){
            createDynamText();
        });
        $(this).find('#rule1typeofdev1').change(function(e){
            createDynamText();
        });
        $(this).find('#rule1typeofdev2').change(function(e){
            createDynamText();
        });
    });
}
function createDynamText(){
    var ruleText = "";
    var counter = 0;
    $('.rule-block').each(function () {
        counter++;
        ruleText +=
            ("Rule"+counter+" Effect:"+ $(this).find('.rule1Effect').val() + "<br/>"+
                "Rule"+counter+" Name:"+ $(this).find('.rule1Name').val()+ "<br/>"+
                "Rule"+counter+" Target Resource: "+$(this).find('.rule1TargetResource').val()+ "<br/>"+
                "Rule"+counter+" Target Action: "+$(this).find('.rule1TargetAction').val()+ "<br/>"+
                "Rule"+counter+" Target Subject: "+$(this).find('.rule1TargetSubject').val()+ "<br/>"+
                "Rule"+counter+" Location 1: "+$(this).find('#rule1location1').val()+ "<br/>"+
                "Rule"+counter+" location 2: "+$(this).find('#rule1location2').val()+ "<br/>"+
                "Rule"+counter+" Time From: "+$(this).find('.rule1timeFrom').val()+ "<br/>"+
                "Rule"+counter+" Time Until: "+$(this).find('.rule1timeUntil').val()+ "<br/>"+
                "Rule"+counter+" Ip Request: "+$(this).find('.rule1ipRequest').val()+ "<br/>"+
                "Rule"+counter+" Ip Recipient: "+$(this).find('.rule1ipRecipient').val()+ "<br/>"+
                "Rule"+counter+" Type Of device 1: "+$(this).find('#rule1typeofdev1').val()+ "<br/>"+
                "Rule"+counter+" Type of Device 2: "+$(this).find('#rule1typeofdev2').val())
    });
    $('#respText').html("Policy Name: "+$('#policyName').val()+
        "<br/> Rule Combining Algorithm: "+$('#ruleCombAl option:selected').text()+
        "<br/> Policy Description: "+$('#policyDesc').val()+
        "<br/> Policy Target Resource: "+$('#policyTargetResource').val()+
        "<br/> Policy Target Action: "+$('#policyTargetAction').val()+
        "<br/> Policy Target Subject: "+$('#policyTargetSubject').val() + "<br/>" +ruleText
    );
}