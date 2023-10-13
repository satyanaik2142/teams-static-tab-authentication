# teams-static-tab-authentication
The Static Tab in Teams is an iframe which gets rendered in the Teams surface.

In order to get the context, eg. which user is trying to access the tab. This information is available through request params inside the teams tab entityURL which is not a recommended way of handling a webapp. 

Attackers can impersonate a user if they get hold of the context details. So, this project is a way to help perform the authentication of the tab user and allow only the webapp to be surfaced inside the Teams surface. 
