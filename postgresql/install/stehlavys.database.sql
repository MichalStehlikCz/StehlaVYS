CREATE DATABASE stehlavys
    WITH 
    OWNER = owner
    TEMPLATE = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'en_US.utf8'
    LC_CTYPE = 'en_US.utf8'
    TABLESPACE = stehlavys
    CONNECTION LIMIT = -1;

COMMENT ON DATABASE stehlavys
    IS 'Database that stores all StehlaVys configuration and data objects';
