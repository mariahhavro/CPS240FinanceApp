# CPS240FinanceApp

Cup O’ Java Financial Institute is a financial application that uses Java Swing to allow users to view and interact with important financial information. 

## Features
- Users can make accounts with a personal username and password
- Can store a User's information, savings, transaction history, loans, and debt information within their accont
- Allows User to Choose different Savings Categories
- Includes financial planning for loans, including a loan calculator to predict what interest will gain on loans depending on amount and frequency of payments and will also include a credit score ratings tool.
- Contains a graphical user interface that allows a user to make an account with their own username and password.

## Resources Used
---INCLUDE REFERENCES HERE AFTER THEY ARE INCLUDED IN THE PAPER---

## Running and Installation
Tested and Verified to work on the IDE Eclipse. In order for the program to run, make sure a folder name "Users" is created under the project folder. This will allow to .txt files for the user to be created and saved. When the application is initially launched, the user will have the option to log in or create a new account. Once logged in, there will be a variety of tabs for the user to use. These include creating bank accounts to handle their savings, a loan calculator to predict interest on loans, a debt management tool and a credit score ratings tool to use. After information is entered in the app, a .txt file will be created for the user in the "Users" folder once the user logs out. The user needs to log out on the log out page for this information to be updated. The .txt file for the user will include their login information, bank account information, and even their debt managment information that has been entered. 

## Talk about how to add in an account

## Class Breakdown

### Accounts
The Accounts class stores name of account, unique ID for account, and balance for a particular user. To create an account, a User must input a name and a balance of money to store in the account. 

### Credit Score Ratings
This method accepts an inputted user credit score factors using slider bars and displays to the user various assessments of the entered information. 

### Debt Management 
Allows users to View/Manage Debt, view a Debt Payment Plans, Add/Edit Debts and look at debt payment plans. To create a new Debt, a user must input the Name, value, and rate. 

### Menu
Main menu - Allows User to login and choose which part of the application they would like to interact with. 

### User 
Lets the User Access the Loan Calculator and View/Manage Accounts
 
### LoanCalc
The Loan Calculator allows users to create loans of different types and view the loan data.

### UserSavings
Stores User Savings information

