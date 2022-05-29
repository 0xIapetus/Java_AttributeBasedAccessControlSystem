$(document).ready(function () {
    $('#myForm').submit(function () {

        var policyIdList = []

        $('.policyID-block').each(function () {

            var temp = {
                policyId: $(this).find('.policyId').val()
            };

            policyIdList.push(temp);
            PolicySetObject = PolicySetObject + policyIdList
            console.log(JSON.stringify(PolicySetObject))
        });
        var PolicySetObject = {
            policySetName: $('#policySetName').val(),
            policySetCombAlg: $('#policySetCombAlg').val(),
            policySetDesc: $('#policySetDesc').val(),
            policyTargetResource: $('#policyTargetResource').val(),
            policyTargetAction: $('#policyTargetAction').val(),
            policyTargetSubject: $('#policyTargetSubject').val(),
            policyIdList : policyIdList

        }
        console.log(JSON.stringify(PolicySetObject))
        $.ajax({
            type: "POST",
            contentType: "application/json",
            dataType: "json",
            async: false,
            data: JSON.stringify(PolicySetObject),
            url: "http://localhost:8080/PolicySetCreation",
            success: function (responseData) {

                alert(responseData.message);
            },
            error: function () {
            }
        });
    });
});
var number = 0;
function addPolicyId(){
    number++
    $("#myForm").append(''+
        '<div class="policyID-block">' +
        '    <p className="field required half">\n' +
        '        <label class="label denmenoiazei" for="policyId">PolicySet'+number+'Reference </label>\n' +
        '        <input class="text-input policyId" id="policyId" name="policyId" required="<%= true %>" type="text"/>\n' +
        '    </p></div>'


    )
}