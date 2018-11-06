package fr.formation.atlasdepartement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class Departement {
    private SQLiteDatabase db;
    private Context ctxt;
        String noDept;
        int noRegion;
        String nom;
        String nomStd;
        int surface;
        String dateCreation;
        String chefLieu;
        String urlWiki;

    public Departement(Context c) {
        DbGeo dbgeo = DbGeo.getInstance(c);
        db = dbgeo.getWritableDatabase();
        ctxt = c;
    }

    public Departement(Context c, String no) throws Exception {
        DbGeo dbgeo = DbGeo.getInstance(c);
        db = dbgeo.getWritableDatabase();
        ctxt = c;
        select(no);
    }

    public Departement (){}

    public String getNoDept() {
        return noDept;
    }
    public int getNoRegion() {
        return noRegion;
    }
    public String getNom() {
        return nom;
    }
    public String getNomStd() {
        return nomStd;
    }
    public int getSurface() {
        return surface;
    }
    public String getDateCreation() {
        return dateCreation;
    }
    public String getChefLieu() {
        return chefLieu;
    }
    public String getUrlWiki() {
        return urlWiki;
    }
    public void setNoDept(String noDept) {
        this.noDept = noDept;
    }
    public void setNoRegion(int noRegion) {
        this.noRegion = noRegion;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setNomStd(String nomStd) {
        this.nomStd = nomStd;
    }
    public void setSurface(int surface) {
        this.surface = surface;
    }
    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }
    public void setChefLieu(String chefLieu) {
        this.chefLieu = chefLieu;
    }
    public void setUrlWiki(String urlWiki) {
        this.urlWiki = urlWiki;
    }

    public void select(String no) throws Exception{
        String colonnes[] = {"no_dept", "no_region", "nom", "nom_std", "surface", "date_creation", "chef_lieu", "url_wiki"};
        String critere = "no_dept = '" + no + "'";
        Cursor cursor = db.query("departements", colonnes, critere, null, null, null, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            noDept =  cursor.getString(0);
            noRegion = cursor.getInt(1);
            nom = cursor.getString(2);
            nomStd = cursor.getString(3);
            surface = cursor.getInt(4);
            dateCreation = cursor.getString(5);
            chefLieu = cursor.getString(6);
            urlWiki = cursor.getString(7);
        } else {
            throw new Exception("Déartement inexistant");
        }
    }

    public void delete() throws Exception {
        if (noDept != "") {
            String critere = "no_dept = '" + noDept + "'";
            db.delete("departements", critere, null);
            Toast.makeText(ctxt, "Bien supprimé", Toast.LENGTH_SHORT).show();
        } else {
            throw new Exception("Impossible de supprimer");
        }
    }

    public void upDate() throws Exception {
        ContentValues values = new ContentValues();
        values.put("no_Dept", noDept);
        values.put("no_region", noRegion);
        values.put("nom", nom);
        values.put("nom_std", nomStd);
        values.put("surface", surface);
        values.put("date_creation", dateCreation);
        values.put("chef_lieu", chefLieu);
        values.put("url_wiki", urlWiki);
        if (noDept != "") {
            String critere = "no_dept = '" + noDept + "'";
            db.update("departements", values, critere, null);
            Toast.makeText(ctxt, "Donnée modifiée", Toast.LENGTH_SHORT).show();
        }else {
            throw new Exception("Impossible de modifier la BDD");
        }
    }

    public void insert () {
        ContentValues values = new ContentValues();
        values.put("no_Dept", noDept);
        values.put("no_region", noRegion);
        values.put("nom", nom);
        values.put("nom_std", nomStd);
        values.put("surface", surface);
        values.put("date_creation", dateCreation);
        values.put("chef_lieu", chefLieu);
        values.put("url_wiki", urlWiki);
        db.insert("departements", "", values);
        Toast.makeText(ctxt, "Département ajouté à la BDD", Toast.LENGTH_SHORT).show();
    }
}
