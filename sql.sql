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