package cl.ciisa.movil.crudexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import cl.ciisa.data.Libro;
import cl.ciisa.data.dbHelper;
import cl.ciisa.tranfer.TrLibro;

public class EditarActivity extends AppCompatActivity {

    private TextView txtId;
    private TextView txtEditNombre;
    private TextView txtEditEditorial;
    private TextView txtEditAutores;
    private TextView txtEditPaginas;

    private Button btnEditActualizar;
    private Button btnEditEliminar;
    private dbHelper dbhelper = new dbHelper(this);
    private Libro libro = new Libro(this.dbhelper);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        btnEditActualizar = (Button) findViewById(R.id.btnEditActualizar);
        btnEditEliminar = (Button) findViewById(R.id.btnEditEliminar);

        try {
            String idLibro = getIntent().getStringExtra("ID");
            TrLibro informacionLibro = this.libro.buscarPorID(idLibro);

            txtId = (TextView) findViewById(R.id.txt_ID);
            txtEditNombre = (TextView) findViewById(R.id.txtEditNombre);
            txtEditEditorial = (TextView) findViewById(R.id.txtEditEditorial);
            txtEditAutores = (TextView) findViewById(R.id.txtEditAutores);
            txtEditPaginas = (TextView) findViewById(R.id.txtEditPaginas);

            txtId.setText(Integer.toString(informacionLibro.getId()));
            txtEditNombre.setText(informacionLibro.getNombre());
            txtEditEditorial.setText(informacionLibro.getEditorial());
            txtEditAutores.setText(informacionLibro.getAutores());
            txtEditPaginas.setText(Integer.toString(informacionLibro.getPaginas()));
        } catch (Exception ex){
            Toast.makeText(EditarActivity.this, "Ha ocurrido un error al buscar el libro.\n" + ex.getMessage(), Toast.LENGTH_LONG).show();
        }

        btnEditEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    libro.eliminar(txtId.getText().toString());
                    finish();
                } catch (Exception ex) {
                    Toast.makeText(EditarActivity.this, "Ha ocurrido un error al eliminar el libro.\n" + ex.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        btnEditActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int id = Integer.parseInt(txtId.getText().toString());
                    String nombre = txtEditNombre.getText().toString();
                    String editorial = txtEditEditorial.getText().toString();
                    String autores = txtEditAutores.getText().toString();
                    int paginas = Integer.parseInt(txtEditPaginas.getText().toString());

                    libro.actualizar(
                            new TrLibro(id, nombre, editorial, autores, paginas)
                    );
                    finish();
                } catch (Exception ex) {
                    Toast.makeText(EditarActivity.this, "Ha ocurrido un error al actualizar el libro.\n" + ex.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
