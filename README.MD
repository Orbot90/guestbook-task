Guestbook application.

The demonstration is hosted in a Compute Engine in GCP:

http://35.233.110.226/

Frontend is implemented in ReactJS with Bootstrap.

For editor I took CKeditor as a Rich Text Editor, that allows formatting tags and uploading images.

Images can be uploaded either with a button in the editor, or just drag-and-drop to the editor area.
Also an image can be added as a URL to the editor - it will be automatically embedded in the editor area and uploaded to th server.

The backend is implemented using Spring Boot. The migration for the database is made with Flyway.

Application uses JWT tokens to authenticate at the server.

Registration is not implemented, so currently there are some predefined users stored in the database.

Admin:
username: Dio_Brando
password: zawarudo

User:
username: Jotaro_Kujo
password: oraoraora

User:
username: Joseph_Joestar
password: ohmygod

The new entries are created as pending approval - they are visible for the author and the admin before admin approves them.
Approved messages are visible for everyone.

Anonymous users can see approved messages but can't add new entries.

Repository also contains docker files for both frontend and backend and docker-compose.yml file to start both along with the MySQL database.