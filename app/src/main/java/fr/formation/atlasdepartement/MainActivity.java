package fr.formation.atlasdepartement;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Departement dept;
    EditText etSearch, etNoDept, etNoRegion, etNom, etNomStd, etSurface, etDateCreation, etChefLieu, etUrlWiki;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        etSearch= findViewById(R.id.etSearch);
        etChefLieu = findViewById(R.id.etChefLieu);
        etDateCreation = findViewById(R.id.etDateCreation);
        etNoDept = findViewById(R.id.etNoDept);
        etNom = findViewById(R.id.etNom);
        etNomStd = findViewById(R.id.etNomStd);
        etNoRegion = findViewById(R.id.etNoRegion);
        etSurface = findViewById(R.id.etSurface);
        etUrlWiki = findViewById(R.id.etUrlWiki);
        dept = new Departement(this);
    }

    public void btSearch(View view) {
        try {
            dept.select(etSearch.getText().toString());
            etNoDept.setText(dept.getNoDept());
            etNoRegion.setText(String.valueOf(dept.getNoRegion()));
            etNom.setText(dept.getNom());
            etNomStd.setText(dept.getNomStd());
            etSurface.setText(String.valueOf(dept.surface));
            etDateCreation.setText(dept.getDateCreation());
            etChefLieu.setText(dept.getChefLieu());
            etUrlWiki.setText(dept.getUrlWiki());
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void btClear(View view) {
        clear();
    }

    public void clear(){
        etSearch.setText("");
        etChefLieu.setText("");
        etDateCreation.setText("");
        etNoDept.setText("");
        etNom.setText("");
        etNomStd.setText("");
        etNoRegion.setText("");
        etSurface.setText("");
        etUrlWiki.setText("");
    }

    public void btInsert(View view) {
            try {
                dept.setNoDept(etNoDept.getText().toString());
                dept.insert();
            }catch (Exception e) {
                Toast.makeText(this, "Impossible d'insérer un nouveau département", Toast.LENGTH_SHORT).show();
            }
    }

    public void btDelete(View view) {
        if (!etNoDept.getText().toString().equals("")) {
            try {
                dept.delete();
                clear();
            } catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, "Pas de numéro de département renseigné", Toast.LENGTH_SHORT).show();
        }

    }

    public void btUpdate(View view) {
        if (!etNoDept.getText().toString().equals("")) {
            try {
                dept.setNoDept(etNoDept.getText().toString());
                dept.setNom(etNom.getText().toString());
                dept.setUrlWiki(etUrlWiki.getText().toString());
                dept.setSurface(Integer.parseInt(etSurface.getText().toString()));
                dept.setNoRegion(Integer.parseInt(etNoRegion.getText().toString()));
                dept.setNomStd(etNomStd.getText().toString());
                dept.setDateCreation(etDateCreation.getText().toString());
                dept.setChefLieu(etChefLieu.getText().toString());
                dept.upDate();
                clear();
            }catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
