# teams-static-tab-authentication
The Static Tab in Teams is an iframe which gets rendered in the Teams surface.

**Microsoft Teams** takes the help of 2 possible mechanisms inorder to get the context of the user and the tenant, invoking the iframe.
*Approach 1*: Using the Query Parameters for the tab, using a predefined param keys.
*Approach 2*. Using the **Teams JS**

**Context Details Fetched**
1. TenantId
2. UserId
3. User Email

**Approach 1** involves exposing the details over the network in the webURL itself (*entityURL* in Teams Manifest) which is not a recommended approach to hide details required to render the iframe.
**Approach 2** is more secured way of handling as the JS works inside Teams surface and it cannot be exposed as a normal web application. This is the 1st layer of security provided by **Microsoft Teams**

**But**, Attackers can impersonate a user if they get hold of the context details. So, this project adds an extra layer of security and validation which is a way to help perform the authentication of the tab user and allow only the webapp to be surfaced inside the Teams surface. 
