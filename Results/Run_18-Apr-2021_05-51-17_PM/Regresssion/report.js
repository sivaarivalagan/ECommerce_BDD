$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("WebAutomationTask.feature");
formatter.feature({
  "comments": [
    {
      "line": 1,
      "value": "#Author: sivasurya.prasand@qualitestgroup.com"
    },
    {
      "line": 2,
      "value": "#Keywords Summary : E-Commerce Automation"
    }
  ],
  "line": 4,
  "name": "Web Automation Task",
  "description": "",
  "id": "web-automation-task",
  "keyword": "Feature",
  "tags": [
    {
      "line": 3,
      "name": "@tag"
    }
  ]
});
formatter.before({
  "duration": 7221772100,
  "status": "passed"
});
formatter.background({
  "line": 6,
  "name": "",
  "description": "",
  "type": "background",
  "keyword": "Background"
});
formatter.step({
  "line": 7,
  "name": "user launch the e-commerce application",
  "keyword": "Given "
});
formatter.match({
  "location": "GeneralStepDefs.launchApplication()"
});
formatter.embedding("image/png", "embedded0.png");
formatter.result({
  "duration": 11956343700,
  "status": "passed"
});
formatter.scenario({
  "line": 11,
  "name": "Order T-Shirt (and Verify in Order History)",
  "description": "",
  "id": "web-automation-task;order-t-shirt-(and-verify-in-order-history)",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 9,
      "name": "@ECommerce"
    },
    {
      "line": 10,
      "name": "@OrderTShirt"
    }
  ]
});
formatter.step({
  "line": 13,
  "name": "user able to login the application",
  "keyword": "Given "
});
formatter.step({
  "line": 14,
  "name": "user adds a product to the cart",
  "keyword": "Then "
});
formatter.step({
  "line": 15,
  "name": "checks out and Order the product",
  "keyword": "Then "
});
formatter.step({
  "line": 16,
  "name": "logout the Application",
  "keyword": "And "
});
formatter.match({
  "location": "LoginStepDefs.userAbleToLogin()"
});
formatter.embedding("image/png", "embedded1.png");
formatter.result({
  "duration": 9047226200,
  "status": "passed"
});
formatter.match({
  "location": "MainPageStepDefs.addProductToCart()"
});
formatter.embedding("image/png", "embedded2.png");
formatter.result({
  "duration": 19792634100,
  "status": "passed"
});
formatter.match({
  "location": "MainPageStepDefs.checkOutAndOrder()"
});
formatter.embedding("image/png", "embedded3.png");
formatter.result({
  "duration": 23933754600,
  "status": "passed"
});
formatter.match({
  "location": "LoginStepDefs.iLogout()"
});
formatter.embedding("image/png", "embedded4.png");
formatter.result({
  "duration": 13205741100,
  "status": "passed"
});
formatter.write("Scenario passed");
formatter.after({
  "duration": 1091758500,
  "status": "passed"
});
formatter.before({
  "duration": 4271674600,
  "status": "passed"
});
formatter.background({
  "line": 6,
  "name": "",
  "description": "",
  "type": "background",
  "keyword": "Background"
});
formatter.step({
  "line": 7,
  "name": "user launch the e-commerce application",
  "keyword": "Given "
});
formatter.match({
  "location": "GeneralStepDefs.launchApplication()"
});
formatter.embedding("image/png", "embedded5.png");
formatter.result({
  "duration": 16731917200,
  "status": "passed"
});
formatter.scenario({
  "line": 21,
  "name": "Update Personal Information (First Name) in My Account",
  "description": "",
  "id": "web-automation-task;update-personal-information-(first-name)-in-my-account",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 19,
      "name": "@ECommerce"
    },
    {
      "line": 20,
      "name": "@PersonalInfoEdit"
    }
  ]
});
formatter.step({
  "line": 23,
  "name": "user able to login the application",
  "keyword": "Given "
});
formatter.step({
  "line": 24,
  "name": "navigate to Personal Info page",
  "keyword": "And "
});
formatter.step({
  "line": 25,
  "name": "Update the first name",
  "keyword": "Then "
});
formatter.step({
  "line": 26,
  "name": "logout the Application",
  "keyword": "And "
});
formatter.match({
  "location": "LoginStepDefs.userAbleToLogin()"
});
formatter.embedding("image/png", "embedded6.png");
formatter.result({
  "duration": 10694130300,
  "status": "passed"
});
formatter.match({
  "location": "MainPageStepDefs.navigateToPersonalInfo()"
});
formatter.embedding("image/png", "embedded7.png");
formatter.result({
  "duration": 3009367900,
  "status": "passed"
});
formatter.match({
  "location": "MainPageStepDefs.updateFirstName()"
});
formatter.embedding("image/png", "embedded8.png");
formatter.result({
  "duration": 4866887200,
  "status": "passed"
});
formatter.match({
  "location": "LoginStepDefs.iLogout()"
});
formatter.embedding("image/png", "embedded9.png");
formatter.result({
  "duration": 11311837200,
  "status": "passed"
});
formatter.write("Scenario passed");
formatter.after({
  "duration": 1268086700,
  "status": "passed"
});
});