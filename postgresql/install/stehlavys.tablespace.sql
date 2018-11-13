-- Tablespace: stehlavys

CREATE TABLESPACE stehlavys
  OWNER postgres
  LOCATION 'C:\PostgreSQL\db';

ALTER TABLESPACE stehlavys
  OWNER TO postgres;

COMMENT ON TABLESPACE stehlavys
  IS 'Tablespace to store all StehlaVys objects';

GRANT CREATE ON TABLESPACE stehlavys TO owner;

GRANT CREATE ON TABLESPACE stehlavys TO postgres;