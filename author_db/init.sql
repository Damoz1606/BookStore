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
   NAME                 VARCHAR(32)          not null,
   LASTNAME             VARCHAR(32)          not null,
   FULLNAME             VARCHAR(128)         not null,
   CAREER               VARCHAR(32)          null,
   constraint PK_AUTHOR primary key (IDENTIFICATION)
);

INSERT INTO public.author (identification, career, fullname, lastname, "name") VALUES('1751990332', 'Student', 'David Muñoz', 'Muñoz', 'David');
INSERT INTO public.author (identification, career, fullname, lastname, "name") VALUES('1751990333', 'Student', 'Devid Muñoz', 'Muñoz', 'Devid');
INSERT INTO public.author (identification, career, fullname, lastname, "name") VALUES('1751990334', 'Student', 'Divid Muñoz', 'Muñoz', 'Divid');
INSERT INTO public.author (identification, career, fullname, lastname, "name") VALUES('1751990335', 'Student', 'Dovid Muñoz', 'Muñoz', 'Dovid');
INSERT INTO public.author (identification, career, fullname, lastname, "name") VALUES('1751990336', 'Student', 'Duvid Muñoz', 'Muñoz', 'Duvid');