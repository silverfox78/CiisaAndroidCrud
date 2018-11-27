package cl.ciisa.movil.crudexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import cl.ciisa.data.Libro;
import cl.ciisa.data.dbHelper;
import cl.ciisa.tranfer.TrLibro;

public class CrearActivity extends AppCompatActivity {
    private Button btnGuardar;
    private Button btnLimpiar;
    private EditText txtNombre;
    private EditText txtEditorial;
    private EditText txtAutores;
    private EditText txtPaginas;
    private dbHelper dbhelper = new dbHelper(this);
    private Libro libro = new Libro(this.dbhelper);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear);

        btnGuardar = (Button) findViewById(R.id.btnGrabar);
        btnLimpiar = (Button) findViewById(R.id.btnLimpiar);
        txtNombre = (EditText) findViewById(R.id.txt_Nombre);
        txtEditorial = (EditText) findViewById(R.id.txt_Editorial);
        txtAutores = (EditText) findViewById(R.id.txt_Autores);
        txtPaginas = (EditText) findViewById(R.id.txt_Cantidad);

        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtNombre.setText("");
                txtEditorial.setText("");
                txtAutores.setText("");
                txtPaginas.setText("");
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String nombre = txtNombre.getText().toString();
                    String editorial = txtEditorial.getText().toString();
                    String autores = txtAutores.getText().toString();
                    int paginas = Integer.parseInt(txtPaginas.getText().toString());
                    libro.agregar(
                            new TrLibro(0, nombre, editorial, autores, paginas)
                    );
                    finish();
                } catch (Exception ex) {
                    Toast.makeText(CrearActivity.this, "Ha ocurrido un error al grabar el libro.\n" + ex.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_OK);
        super.onBackPressed();
    }
}
