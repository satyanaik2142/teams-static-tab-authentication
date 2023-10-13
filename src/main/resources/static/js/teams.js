$(document).ready(function() {

});


async function getTenant(){
    microsoftTeams.app.initialize();
    var context = await microsoftTeams.app.getContext();
    return context.user.tenant.id;
}

async function getUserId(){
    microsoftTeams.app.initialize();
    var context = await microsoftTeams.app.getContext();
    return context.user.id;
}

async function getUserEmail(){
    microsoftTeams.app.initialize();
    var context = await microsoftTeams.app.getContext();
    return context.user.userPrincipalName;
}

async function getTeam(){
    microsoftTeams.app.initialize();

    /*
        For Getting the teamId for a channel, there are multiple options
        For Standard Channel channel.membershipType = 'Regular', then teamId = context.team.groupId
        For Shared Channel, channel.membershipType = 'Shared', then teamId = context.channel.ownerGroupId
        For Private Channel, channel.membershipType = 'Shared', then teamId = context.channel.ownerGroupId
    */

    var context = await microsoftTeams.app.getContext();
    let channel = context.channel;
    if(channel.membershipType == 'Shared')
        return channel.ownerGroupId;
    else if(channel.membershipType == 'Private')
        return channel.ownerGroupId;
    return context.team.groupId;
}

async function getTeamsChannelId(){
    microsoftTeams.app.initialize();
    var context = await microsoftTeams.app.getContext();
    return context.channel.id;
}

async function authorize(callbackURL){

    let MSUSER-MAIL = await getUserEmail();
    let MSTENANT-ID = await getTenant();
    let MSUSER-ID = await getUserId();

    await fetch(baseURL + "/authorize", {
             method: "POST",
             headers: {
                "Authorization": "authorization-123",
                "MSTENANT_ID": MSTENANT_ID,
                "MSUSER_ID": MSUSER_ID,
                "MSUSER_MAIL": MSUSER_MAIL
             }
        })
        .then((response) => {

             if(response.status == 200){
                 redirect(callbackURL);
             }else if(response.status == 401){
                redirect("/unauthorized");
             }
        })
        .then(json => {
            console.log(json);
        })
        .catch(err => console.log(err));
}

function redirect(callbackURL){
    var url = baseURL + callbackURL;
    window.open(url, "_self");
}