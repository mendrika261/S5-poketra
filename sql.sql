create view v_format as
select f.id id_format, m.nom nom_modele, f."nomFormat" nom_format, s.id id_style, s.nom nom_style, f."idModele" id_modele
from "Format" f
         join "Modele" m on f."idModele" = m.id
         join "Style" s on m."idStyle" = s.id;


create view style_mp as select "idStyle" id_style, "idMatierePremiere" id_mp, nom nom_mp from "MPStyle" JOIN "MatierePremiere" mp on "idMatierePremiere" = mp.id;

create view v_liste as select m.id idModele, f.id idFormat,  mp.id idMatierePremiere, mp.quantite quantite
                       from "Modele" m JOIN "Format" f on m."idStyle" = f."idModele" JOIN "MpModele" mp on f.id = mp."idFormat";

create view v_modele_mp as
SELECT "MpModele".id,
       "MpModele"."idFormat",
       "MpModele"."idMatierePremiere",
       mp.nom mp_nom,
       "MpModele".quantite,
       "MpModele".quantite * mp.prix as prix,
       v_format.id_format,
       v_format.nom_format,
       v_format.id_modele,
       v_format.nom_modele,
       v_format.id_style,
       v_format.nom_style
FROM "MpModele"
         JOIN v_format ON "MpModele"."idFormat" = v_format.id_format
         JOIN "MatierePremiere" mp on "MpModele"."idMatierePremiere" = mp.id;

create view v_prix_format as
select id_format, sum(prix) as prix, nom_format, nom_modele, nom_style from v_modele_mp
group by "id_format", "nom_format", "nom_modele", "nom_style";



select * from v_prix_format where prix between 30000 and 50000;

create view v_stock_entree as
select * from "MouvementStock" WHERE quantite > 0;

create view v_stock_sortie as
select * from "MouvementStock" WHERE quantite < 0;

create view v_stock as
    select "idMatierePremiere" as id, mp.nom, sum(quantite) from "MouvementStock"
                                              join "MatierePremiere" mp on "MouvementStock"."idMatierePremiere" = mp.id
                                              group by "idMatierePremiere", mp.nom;



select * from v_service_prix;

CREATE OR REPLACE VIEW v_service_prix AS
SELECT "idModele", sum("Service"."prixHoraire" * "ServiceParProduit".heure * "ServiceParProduit".personnel) as cout
FROM "Service" JOIN "ServiceParProduit"
                    ON "Service".id="ServiceParProduit"."idService" GROUP BY "idModele";

CREATE OR REPLACE VIEW v_cout_fabrication_produit AS
SELECT id as id_format, "Format".coefficient, cout as cout_base, cout * "Format".coefficient as cout_total
FROM "Format" JOIN v_service_prix
                   ON "Format"."idModele"="v_service_prix"."idModele";

CREATE OR REPLACE VIEW v_cout_mp_produit AS
SELECT id_format, SUM(prix) from v_modele_mp group by id_format;

CREATE OR REPLACE VIEW v_cout_produit AS
SELECT v_cout_mp_produit.id_format, cout_total + sum as cout_total,
       prixvente, prixvente - (cout_total + sum) as marge, "nomFormat" as nom_format,
       "Modele"."nom" as nom_modele, "Style"."nom" as nom_style
FROM v_cout_fabrication_produit JOIN v_cout_mp_produit
                                     ON v_cout_fabrication_produit.id_format=v_cout_mp_produit.id_format
                                JOIN "Format" ON "Format".id=v_cout_mp_produit.id_format
                                JOIN "Modele" ON "Modele".id="Format"."idModele"
                                JOIN "Style" ON "Style".id="Modele"."idStyle";