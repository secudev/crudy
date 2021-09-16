-- PUBLIC.PRODUIT definition

-- Drop table

-- DROP TABLE PUBLIC.PRODUIT;

CREATE TABLE PUBLIC.PRODUIT (
	ID VARCHAR(255) NOT NULL,
	DATE_ACHAT DATE,
	DESCRIPTION VARCHAR(255),
	LIBELLE VARCHAR(255) NOT NULL,
	PRIX_ACHAT DOUBLE NOT NULL,
	STOCK INTEGER NOT NULL	
);
CREATE UNIQUE INDEX PRIMARY_KEY_1 ON PUBLIC.PRODUIT (ID);
CREATE UNIQUE INDEX UK_KJ5LBQW12VQYIGOEOLVPAPFPP_INDEX_1 ON PUBLIC.PRODUIT (LIBELLE);