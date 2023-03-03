/*==============================================================*/
/* DBMS name:      PostgreSQL 9.x                               */
/* Created on:     3/2/2023 10:45:57 AM                         */
/*==============================================================*/


drop table AUTHOR;

/*==============================================================*/
/* Table: AUTHOR                                                */
/*==============================================================*/
create table AUTHOR (
   IDENTIFICATION       VARCHAR(15)          not null,
   IDENTIFICATION_TYPE  VARCHAR(3)           not null,
   NAME                 VARCHAR(32)          not null,
   LASTNAME             VARCHAR(32)          not null,
   FULLNAME             VARCHAR(128)         not null,
   CAREER               VARCHAR(32)          null,
   constraint PK_AUTHOR primary key (IDENTIFICATION, IDENTIFICATION_TYPE)
);

