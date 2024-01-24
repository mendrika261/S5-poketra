CREATE VIEW v_format AS
SELECT
    f.id id_format,
    m.nom nom_modele,
    f."nomFormat" nom_format,
    s.id id_style,
    s.nom nom_style,
    f."idModele" id_modele
FROM
    "Format" f
    JOIN "Modele" m ON f."idModele" = m.id
    JOIN "Style" s ON m."idStyle" = s.id;

CREATE VIEW style_mp AS
SELECT
    "idStyle" id_style,
    "idMatierePremiere" id_mp,
    nom nom_mp
FROM
    "MPStyle"
    JOIN "MatierePremiere" mp ON "idMatierePremiere" = mp.id;

CREATE VIEW v_liste AS
SELECT
    m.id idModele,
    f.id idFormat,
    mp.id idMatierePremiere,
    mp.quantite quantite
FROM
    "Modele" m
    JOIN "Format" f ON m."idStyle" = f."idModele"
    JOIN "MpModele" mp ON f.id = mp."idFormat";

CREATE VIEW v_modele_mp AS
SELECT
    "MpModele".id,
    "MpModele"."idFormat",
    "MpModele"."idMatierePremiere",
    mp.nom mp_nom,
    "MpModele".quantite,
    "MpModele".quantite * mp.prix AS prix,
    v_format.id_format,
    v_format.nom_format,
    v_format.id_modele,
    v_format.nom_modele,
    v_format.id_style,
    v_format.nom_style
FROM
    "MpModele"
    JOIN v_format ON "MpModele"."idFormat" = v_format.id_format
    JOIN "MatierePremiere" mp ON "MpModele"."idMatierePremiere" = mp.id;

CREATE VIEW v_prix_format AS
SELECT
    id_format,
    sum(prix) AS prix,
    nom_format,
    nom_modele,
    nom_style
FROM
    v_modele_mp
GROUP BY
    "id_format",
    "nom_format",
    "nom_modele",
    "nom_style";

SELECT
    *
FROM
    v_prix_format
WHERE
    prix BETWEEN 30000 AND 50000;

CREATE VIEW v_stock_entree AS
SELECT
    *
FROM
    "MouvementStock"
WHERE
    quantite > 0;

CREATE VIEW v_stock_sortie AS
SELECT
    *
FROM
    "MouvementStock"
WHERE
    quantite < 0;

CREATE VIEW v_stock AS
SELECT
    "idMatierePremiere" AS id,
    mp.nom,
    sum(quantite)
FROM
    "MouvementStock"
    JOIN "MatierePremiere" mp ON "MouvementStock"."idMatierePremiere" = mp.id
GROUP BY
    "idMatierePremiere",
    mp.nom;

SELECT
    *
FROM
    v_service_prix;

CREATE OR REPLACE VIEW v_service_prix AS
SELECT
    "idModele",
    sum("Service"."prixHoraire" * "ServiceParProduit".heure * "ServiceParProduit".personnel) AS cout
FROM
    "Service"
    JOIN "ServiceParProduit" ON "Service".id = "ServiceParProduit"."idService"
GROUP BY
    "idModele";

CREATE OR REPLACE VIEW v_cout_fabrication_produit AS
SELECT
    id AS id_format,
    "Format".coefficient,
    cout AS cout_base,
    cout * "Format".coefficient AS cout_total
FROM
    "Format"
    JOIN v_service_prix ON "Format"."idModele" = "v_service_prix"."idModele";

CREATE OR REPLACE VIEW v_cout_mp_produit AS
SELECT
    id_format,
    SUM(prix)
FROM
    v_modele_mp
GROUP BY
    id_format;

CREATE OR REPLACE VIEW v_cout_produit AS
SELECT
    v_cout_mp_produit.id_format,
    cout_total + sum AS cout_total,
    prixvente,
    prixvente - (cout_total + sum) AS marge,
    "nomFormat" AS nom_format,
    "Modele"."nom" AS nom_modele,
    "Style"."nom" AS nom_style
FROM
    v_cout_fabrication_produit
    JOIN v_cout_mp_produit ON v_cout_fabrication_produit.id_format = v_cout_mp_produit.id_format
    JOIN "Format" ON "Format".id = v_cout_mp_produit.id_format
    JOIN "Modele" ON "Modele".id = "Format"."idModele"
    JOIN "Style" ON "Style".id = "Modele"."idStyle";

