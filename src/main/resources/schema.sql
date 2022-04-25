/*==============================================================*/

DROP TABLE IF EXISTS hotel;

/*==============================================================*/
/* Table: hotel                                                 */
/*==============================================================*/
CREATE TABLE hotel
(
  id      INT  NOT NULL PRIMARY KEY AUTO_INCREMENT,
  category int NOT NULL,
  name    TEXT NOT NULL,
  owner   TEXT NOT NULL,
  contact TEXT NOT NULL,
  address TEXT NOT NULL,
  city    TEXT NOT NULL,
  zip     TEXT NOT NULL,
  phone   TEXT NOT NULL,
  rooms   int  not null,
  beds    int  not null,
  url tinyint not null,
  family_friendly tinyint not null,
  dog_friendly tinyint not null,
  spa tinyint not null,
  fitness tinyint not null

);

