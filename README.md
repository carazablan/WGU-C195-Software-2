# WGU-C195-Software-2
README

C195 Project
This application helps maintain an existing database containing appointment management information.

Cara Zablan 
201-680-1503 
QAM2 — QAM2 TASK 1: JAVA APPLICATION DEVELOPMENT
07/31/2022

IntelliJ IDEA 2021.1.3 
JDK 11.0.12
JAVAFX-SDK-11.0.12

To use this program you will have to login with a username and password present in the database. 
Once logged in an alert will pop-up with information regarding any appointments you may have within 15 mins.
Once you confirm the pop-up, the scene with the Appointments List Tableview will open.

In the Appointments list scene, you can choose between radio buttons to view appointments by week, by month, or all time.
At the bottom of the page will be the buttons to Add an Appointment, Update an Appointment, Delete an Appointment, and to navigate to Reports page, Customers page, and to Exit the application.

To Add an appointment you can click on the Add button at the bottom of the appointment tableview scene, that will take you to the Add Appointment form.
In the Add Appointment form are textfields and comboBoxes to add appropriate information regarding the appointment being made.
Fill out the form and click Save to make the appointment, or click Cancel and go back to the previous scene.

To update an appointment select an appointment in the Appointment Tableview and click on the Update button at the bottom of the  scene.
In the update appointment scene you will find prefilled textfields and comboBoxes to help you make changes to the selected appointment.
Once you're done, click Save to save the changes you've made, or click Cancel to go back to the previous page.

To view the Customers list, click on the Customers button at the bottom of the Appointments Tableview scene.
This will take you to the Customers Tableview scene, where you will be able to view, add, update, or delete customer records from the database.
This scene also has buttons for going back to the appointments tableview, to go to the reports scene, and to exit.

To add a customer record, click on the Add Customer button at the bottom of the Customers Tableview scene.
This will take you to the Add Customers form, where you will find textfields and comboBoxes for adding a customer record.
Once you've filled out the form, click Save to add a new customer or Cancel to go to the previous page.

To update an existing customer record, select a customer from the Customers Tableview and click on the Update button at the bottom of the scene.
This will take you to the update customer form, in it you will find textfields and comboBoxes to help you make changes to the customer record.
Click Save to save changes, or Cancel to go to the previous scene.

To go to the Reports scene, you can click on the Reports button at the bottom of either the Appointments, or Customers Tableview scenes.
This will take you to the Reports scene where you will find a Contacts Tableview with a comboBox to filter by Contact ID; a Login Tracker TextArea, with a list of all the logins; 
a listview of first level divisions filtered by country; and comboBoxes for displaying the total appointments by type within a month.

To exit the application you can find Exit buttons in the Appointment List, Customers List, and Reports scenes.

The additional report is a listview of first level divisions filtered by a countries comboBox.

mysql-connector-java-8.0.29
