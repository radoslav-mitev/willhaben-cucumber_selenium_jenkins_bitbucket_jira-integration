$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:src/test/java/behaviorDrivenTesting/features/WillhabenLogin.feature");
formatter.feature({
  "name": "WillhabenLoginErrorAlerts",
  "description": "",
  "keyword": "Feature"
});
formatter.background({
  "name": "",
  "description": "",
  "keyword": "Background"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "an user opens www.willhaben.at in a chrome browser",
  "keyword": "Given "
});
formatter.match({
  "location": "behaviorDrivenTesting.stepDefinitions.WillhabenLoginStepdefs.anUserOpensWwwWillhabenAtInChromeBrowser()"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Login test using incorrect e-mail format (address without @ and domain)",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@database"
    }
  ]
});
formatter.step({
  "name": "trying to log into account using non-valid e-mail address format",
  "keyword": "When "
});
formatter.match({
  "location": "behaviorDrivenTesting.stepDefinitions.WillhabenLoginStepdefs.tryingToLogIntoAccountUsingNonValidEMailAddressFormat()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "should see [Bitte gib eine gueltige E-Mail-Adresse an]",
  "keyword": "Then "
});
formatter.match({
  "location": "behaviorDrivenTesting.stepDefinitions.WillhabenLoginStepdefs.shouldSeeBitteGibEineGueltigeEMailAdresseAn()"
});
formatter.result({
  "status": "passed"
});
formatter.background({
  "name": "",
  "description": "",
  "keyword": "Background"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "an user opens www.willhaben.at in a chrome browser",
  "keyword": "Given "
});
formatter.match({
  "location": "behaviorDrivenTesting.stepDefinitions.WillhabenLoginStepdefs.anUserOpensWwwWillhabenAtInChromeBrowser()"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Login test using correct e-mail format but non valid credentials",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@database"
    },
    {
      "name": "@last"
    }
  ]
});
formatter.step({
  "name": "trying to log into account using an e-mail address in valid format",
  "keyword": "When "
});
formatter.match({
  "location": "behaviorDrivenTesting.stepDefinitions.WillhabenLoginStepdefs.tryingToLogIntoAccountUsingAnEMailAddressInValidFormat()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "user should see [Login fehlgeschlagen]",
  "keyword": "Then "
});
formatter.match({
  "location": "behaviorDrivenTesting.stepDefinitions.WillhabenLoginStepdefs.userShouldSeeLoginFehlgeschlagen()"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
formatter.scenarioOutline({
  "name": "Login test using correct e-mail format but without a password",
  "description": "",
  "keyword": "Scenario Outline"
});
formatter.step({
  "name": "trying to log into account using an \"\u003ce-mail address\u003e\" in valid format without a \"\u003cpassword\u003e\"",
  "keyword": "When "
});
formatter.step({
  "name": "user should see [Bitte gib dein Passwort ein]",
  "keyword": "Then "
});
formatter.examples({
  "name": "",
  "description": "",
  "keyword": "Examples",
  "rows": [
    {
      "cells": [
        "e-mail address",
        "password"
      ]
    },
    {
      "cells": [
        "test@gmail.at",
        ""
      ]
    },
    {
      "cells": [
        "test2@gmail.at",
        ""
      ]
    }
  ]
});
formatter.background({
  "name": "",
  "description": "",
  "keyword": "Background"
});
formatter.step({
  "name": "an user opens www.willhaben.at in a chrome browser",
  "keyword": "Given "
});
formatter.match({
  "location": "behaviorDrivenTesting.stepDefinitions.WillhabenLoginStepdefs.anUserOpensWwwWillhabenAtInChromeBrowser()"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Login test using correct e-mail format but without a password",
  "description": "",
  "keyword": "Scenario Outline"
});
formatter.step({
  "name": "trying to log into account using an \"test@gmail.at\" in valid format without a \"\"",
  "keyword": "When "
});
formatter.match({
  "location": "behaviorDrivenTesting.stepDefinitions.WillhabenLoginStepdefs.tryingToLogIntoAccountUsingAnInValidFormatWithoutAAnd(java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "user should see [Bitte gib dein Passwort ein]",
  "keyword": "Then "
});
formatter.match({
  "location": "behaviorDrivenTesting.stepDefinitions.WillhabenLoginStepdefs.userShouldSeeBitteGibDeinPasswortEin()"
});
formatter.result({
  "status": "passed"
});
formatter.background({
  "name": "",
  "description": "",
  "keyword": "Background"
});
formatter.step({
  "name": "an user opens www.willhaben.at in a chrome browser",
  "keyword": "Given "
});
formatter.match({
  "location": "behaviorDrivenTesting.stepDefinitions.WillhabenLoginStepdefs.anUserOpensWwwWillhabenAtInChromeBrowser()"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Login test using correct e-mail format but without a password",
  "description": "",
  "keyword": "Scenario Outline"
});
formatter.step({
  "name": "trying to log into account using an \"test2@gmail.at\" in valid format without a \"\"",
  "keyword": "When "
});
formatter.match({
  "location": "behaviorDrivenTesting.stepDefinitions.WillhabenLoginStepdefs.tryingToLogIntoAccountUsingAnInValidFormatWithoutAAnd(java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "user should see [Bitte gib dein Passwort ein]",
  "keyword": "Then "
});
formatter.match({
  "location": "behaviorDrivenTesting.stepDefinitions.WillhabenLoginStepdefs.userShouldSeeBitteGibDeinPasswortEin()"
});
formatter.result({
  "status": "passed"
});
});