*** Settings ***
Documentation    		Recherche job par un mot clé et la localisation
Force Tags        		FindFirstJob
Library           		robotframework.GettingStarted    https://www.randstad.fr
Suite Setup
Suite Teardown

*** Variables ***
${jobName}        		ingénieur
${localisation}    		Île-de-France

*** Test Cases ***
Search By JobName and Localisation
    [Setup]         Open Browser
    [Teardown]      Close Browser
    When Job Search    	${JobName}	   ${localisation}
    Then Check Level Studies
    And Check Salary
    And Check Type Salary

