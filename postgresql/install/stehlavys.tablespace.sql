-- Tablespace: stehlavys

CREATE TABLESPACE stehlavys
  OWNER owner
  LOCATION '/var/lib/postgresql/data/pgdata/';

COMMENT ON TABLESPACE stehlavys
  IS 'Tablespace to store all StehlaVys objects';

ALTER TABLESPACE stehlavys
  OWNER TO owner;
