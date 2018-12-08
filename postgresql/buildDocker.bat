docker network create stehlavys
cd PgAdmin
cmd /C buildPgAdmin.bat
cd ..
cd TitleCatalogueDb
cmd /C buildTitleCatalogueDb.bat
cd ..
