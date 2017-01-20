DROP DATABASE IF EXISTS property;

CREATE DATABASE property;

USE property;

CREATE TABLE OWNERS
(
   Name varchar(20) NOT NULL,
   Street varchar(30) NOT NULL,
   City varchar(30) NOT NULL,
   State varchar(30) NOT NULL,
   Zip int(5) NOT NULL,
   Balance float(10.2) NOT NULL
);

CREATE TABLE PROPERTIES
(
   ID int NOT NULL AUTO_INCREMENT PRIMARY KEY,
   Price float(20.2) NOT NULL,
   Type varchar(30) NOT NULL,
   Subtype varchar(30) NOT NULL,
   Date date NOT NULL,
   Street varchar(30) NOT NULL,
   City varchar(30) NOT NULL,
   State varchar(30) NOT NULL,
   Zip int(5) NOT NULL,
   DaysOverDue int(5) NOT NULL,
   Owner varchar(30) NOT NULL
);

CREATE TABLE TAXRATES
(
   Type varchar(30) NOT NULL,
   Rate float(5.2) NOT NULL
);

CREATE TABLE OWNERPROPERTIES
(
   Owner varchar(30) NOT NULL,
   PropertyID int NOT NULL AUTO_INCREMENT PRIMARY KEY
);
   

INSERT INTO OWNERS(Name, Street, City, State, Zip, Balance) VALUES('Jones','Davis','Arlington', 'Texas','76019','1000000');
INSERT INTO OWNERS(Name, Street, City, State, Zip, Balance) VALUES('Smith','Elm','Euless','Texas','76123','2500000');
INSERT INTO OWNERS(Name, Street, City, State, Zip, Balance) VALUES('Howard','Oak','Dallas','Texas','76009','500000');
INSERT INTO OWNERS(Name, Street, City, State, Zip, Balance) VALUES('Davis','Rodeo','Cupertino','California','90063','75000');
INSERT INTO OWNERS(Name, Street, City, State, Zip, Balance) VALUES('Willis','Foster','San Diego','California','91267','3750750');
INSERT INTO OWNERS(Name, Street, City, State, Zip, Balance) VALUES('Baker','Tucker','Ft. Worth','Texas','73456','125000');
INSERT INTO OWNERS(Name, Street, City, State, Zip, Balance) VALUES('Sanchez','Azalea','Flower Mound','Texas','76958','775700');
INSERT INTO OWNERS(Name, Street, City, State, Zip, Balance) VALUES('Jetson','South','Tulsa','Oklahoma','54678','68950');
INSERT INTO OWNERS(Name, Street, City, State, Zip, Balance) VALUES('Piccoli','Quarter','New Orleans','Louisiana','34891','125750');

INSERT INTO PROPERTIES(Price, Type, Subtype, Date, Street, City, State, Zip, DaysOverDue, Owner) VALUES('75000','Residential','null',STR_TO_DATE('2/2/2010','%m/%e/%Y'),'Smith Street','Euless','Texas','70025','30','Jones');
INSERT INTO PROPERTIES(Price, Type, Subtype, Date, Street, City, State, Zip, DaysOverDue, Owner) VALUES('124500','Commercial','SHOPPING',STR_TO_DATE('5/23/2010','%m/%e/%Y'),'Main Street','Bedford','Texas','70030','60','Smith');
INSERT INTO PROPERTIES(Price, Type, Subtype, Date, Street, City, State, Zip, DaysOverDue, Owner) VALUES('399000','Residential','null',STR_TO_DATE('8/5/2009','%m/%e/%Y'),'Knight Avenue','Seattle','Washington','90685','45','Willis');
INSERT INTO PROPERTIES(Price, Type, Subtype, Date, Street, City, State, Zip, DaysOverDue, Owner) VALUES('45750','Commercial','HAZARD',STR_TO_DATE('3/4/2011','%m/%e/%Y'),'Josy Lane','Denver','Colorado','28054','0','Sanchez');
INSERT INTO PROPERTIES(Price, Type, Subtype, Date, Street, City, State, Zip, DaysOverDue, Owner) VALUES('376459','Residential','null',STR_TO_DATE('6/14/2009','%m/%e/%Y'),'Allen Street','Arlington','Texas','76019','90','Baker');

INSERT INTO TAXRATES(Type, Rate) VALUES('Industry','2.0');
INSERT INTO TAXRATES(Type, Rate) VALUES('Shopping','4.5');
INSERT INTO TAXRATES(Type, Rate) VALUES('Hazard','8.0');
INSERT INTO TAXRATES(Type, Rate) VALUES('Residential','3.25');

INSERT INTO OWNERPROPERTIES(Owner) VALUES('Jones');
INSERT INTO OWNERPROPERTIES(Owner) VALUES('Smith');
INSERT INTO OWNERPROPERTIES(Owner) VALUES('Willis');
INSERT INTO OWNERPROPERTIES(Owner) VALUES('Sanchez');
INSERT INTO OWNERPROPERTIES(Owner) VALUES('Baker');
