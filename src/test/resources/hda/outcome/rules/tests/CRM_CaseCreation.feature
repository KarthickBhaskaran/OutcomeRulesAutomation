Feature: CRM Manual case creation in UAT Environment

 # Covers the outcome rule regression test
 @CRMCaseCreation

 Scenario: Test outcome rules and  capture booking ID

  Given I login to CRM
  And I load Manual case creation page
  Then I Enter Caller details
  And I Enter Call Source state
  Then I Save case and fill identity
  And I Enter symptoms and triage
  And I complete the case and resolve
