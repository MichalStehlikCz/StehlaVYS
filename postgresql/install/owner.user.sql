-- User: owner

CREATE USER owner IDENTIFIED BY "atlanta" WITH
  LOGIN
  SUPERUSER
  INHERIT
  CREATEDB
  CREATEROLE
  NOREPLICATION;