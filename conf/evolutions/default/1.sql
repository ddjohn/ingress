# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table Ingresscore (
  id                        bigint not null,
  agent                     varchar(255),
  color                     varchar(255),
  level                     integer,
  score                     bigint,
  silver                    integer,
  gold                      integer,
  platinum                  integer,
  black                     integer,
  constraint pk_Ingresscore primary key (id))
;

create sequence Ingresscore_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists Ingresscore;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists Ingresscore_seq;

