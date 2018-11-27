package cl.ciisa.movil.crudexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import cl.ciisa.data.Libro;
import cl.ciisa.data.dbHelper;
import cl.ciisa.tranfer.TrLibro;

public class MainActivity extends AppCompatActivity {
    private dbHelper dbhelper = new dbHelper(this);
    private Libro libro = new Libro(this.dbhelper);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CrearActivity.class));
            }
        });
        this.CargaGrilla();
    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.CargaGrilla();
        Toast.makeText(MainActivity.this, "Volvi...", Toast.LENGTH_LONG).show();
    }*/

    @Override
    public void onResume()
    {
        super.onResume();
        this.CargaGrilla();
        //Toast.makeText(MainActivity.this, "Volvi 2...", Toast.LENGTH_LONG).show();
    }

    private void CargaGrilla(){
        try {
            int totalLibros = libro.size();
            TextView texto = (TextView) findViewById(R.id.txt_prueba);
            if(totalLibros <= 0){
                texto.setText("La biblioteca actualmente no posee libros.");
            } else {
                texto.setText("La biblioteca contiene " + Integer.toString(totalLibros) + " libros registrados.");
                TableLayout tabla = (TableLayout) findViewById(R.id.tbl_datos);
                tabla.removeAllViews();
                boolean par = false;
                for (TrLibro libro: libro.listar()) {
                    TableRow fila = new TableRow(MainActivity.this);
                    TextView textoFila = new TextView(MainActivity.this);
                    textoFila.setText(Integer.toString(libro.getId()) + ".- " + libro.getNombre());
                    textoFila.setPadding(3, 3, 3, 8);
                    textoFila.setGravity(Gravity.CENTER_VERTICAL);
                    fila.addView(textoFila, new TableRow.LayoutParams());
                    fila.setBackgroundResource(par ? R.color.grisClaro : R.color.grisOscuro);
                    fila.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View view)
                        {
                            view.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
                            TableRow t = (TableRow) view;
                            TextView firstTextView = (TextView) t.getChildAt(0);
                            String texto = firstTextView.getText().toString();
                            String id = texto.split(".-")[0];

                            Intent intent = new Intent(MainActivity.this, EditarActivity.class);
                            intent.putExtra("ID", id);
                            startActivity(intent);
                            //Toast.makeText(MainActivity.this, "CUEK " + id, Toast.LENGTH_SHORT).show();
                        }
                    });
                    par = !par;
                    tabla.addView(fila, new TableLayout.LayoutParams());
                    fila.requestLayout();
                }
            }
        } catch (Exception ex){
            Toast.makeText(MainActivity.this, "Ha ocurrido un error al listar los libros.\n" + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
