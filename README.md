Alfresco-Encryption-Module
==========================

Based on a free open course code contest in Vietnam: Mùa Hè Sáng Tạo. 
This is a completely open source code project.
License: GPL 2.0


This module extends features of Alfresco system, which allows 
users to encrypt and decrypt their data on repository.
The encryption algorithm is AES.
Module is implemented in the form of Alfresco Module Pakage File.



Development Team:
  Nguyễn Khánh Thịnh
  Đồng Xuân Thủy
  Nguyễn Mạnh Cường

Mentor:
  Nguyễn Vũ Hưng
  Hoàng Chí Linh

Supporting company:
  EcoIT: www.ecoit.asia

For more detail information such as design and other documentation, please check the wiki pages.
For collaboration please contact:
khanhthinh.45a4@gmail.com

How to deploy :
 1) Point to the encryption-extension-repo
 2) Change build.properties to point to your local installation of the Alfresco SDK, location of your Alfresco webapps
 3) Use command : ant -f build.xml deploy to deploy to alfresco
 4) Point to encryption-extension-share
 5) Change build.properties to point to your local installation of the Alfresco SDK, location of your Alfresco webapps
 6) Use command : ant -f build.xml
 7) Restart server and run alfresco 