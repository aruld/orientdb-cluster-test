Create a plocal document database:
==========
create database plocal:userdb admin admin plocal document;

<pre> 
~/github/orient/orientdb-community-2.1.15/bin: ./console.sh 

OrientDB console v.2.1.15 (build 2.1.x@r243c566d47d8877ea0075b8f61abc8880f810a42; 2016-04-04 12:07:47+0000) www.orientdb.com
Type 'help' to display all the supported commands.
Installing extensions for GREMLIN language v.2.6.0

orientdb> create database plocal:userdb admin admin plocal document

Creating database [plocal:userdb] using the storage type [plocal]...
Database created successfully.
</pre>

Create User schema
=======
CREATE CLASS User;
CREATE PROPERTY User.FirstName STRING;
CREATE INDEX User.FirstName NOTUNIQUE;
CREATE PROPERTY User.LastName STRING;
CREATE INDEX User.LastName NOTUNIQUE;
CREATE PROPERTY User.MailAddress STRING;
CREATE INDEX User.MailAddress UNIQUE;

<pre>
orientdb {db=userdb}> CREATE CLASS User

Class created successfully. Total classes in database now: 9

orientdb {db=userdb}> CREATE PROPERTY User.FirstName STRING;

Property created successfully with id=1

orientdb {db=userdb}> CREATE INDEX User.FirstName NOTUNIQUE;


Creating index...
Created index successfully with 0 entries in 0.021000 sec(s).


Index created successfully
orientdb {db=userdb}> CREATE PROPERTY User.LastName STRING;

Property created successfully with id=2

orientdb {db=userdb}> CREATE INDEX User.LastName NOTUNIQUE;


Creating index...
Created index successfully with 0 entries in 0.018000 sec(s).


Index created successfully
orientdb {db=userdb}> CREATE PROPERTY User.MailAddress STRING;

Property created successfully with id=3

orientdb {db=userdb}> CREATE INDEX User.MailAddress UNIQUE;


Creating index...
Created index successfully with 0 entries in 0.021000 sec(s).


Index created successfully
orientdb {db=userdb}> desc User

CLASS 'User'

Default cluster......: user (id=9)
Supported clusters...: user(9)
Cluster selection....: round-robin
Oversize.............: 0.0

PROPERTIES
-------------------------------+-------------+-------------------------------+-----------+----------+----------+-----------+-----------+----------+
 NAME                          | TYPE        | LINKED TYPE/CLASS             | MANDATORY | READONLY | NOT NULL |    MIN    |    MAX    | COLLATE  |
-------------------------------+-------------+-------------------------------+-----------+----------+----------+-----------+-----------+----------+
 LastName                      | STRING      | null                          | false     | false    | false    |           |           | default  |
 FirstName                     | STRING      | null                          | false     | false    | false    |           |           | default  |
 MailAddress                   | STRING      | null                          | false     | false    | false    |           |           | default  |
-------------------------------+-------------+-------------------------------+-----------+----------+----------+-----------+-----------+----------+

INDEXES (3 altogether)
-------------------------------+----------------+
 NAME                          | PROPERTIES     |
-------------------------------+----------------+
 User.MailAddress              | MailAddress    |
 User.LastName                 | LastName       |
 User.FirstName                | FirstName      |
-------------------------------+----------------+

</pre>