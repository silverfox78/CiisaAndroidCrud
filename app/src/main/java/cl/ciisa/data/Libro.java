package cl.ciisa.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import cl.ciisa.tranfer.TrLibro;

import java.util.ArrayList;

public class Libro {
    private dbHelper dbhelper;

    public Libro(dbHelper dbhelper){
        this.dbhelper = dbhelper;
    }

    public int size(){
        SQLiteDatabase db = this.dbhelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(ID) FROM tbllibros", null);
        if (cursor.moveToFirst()) {
            return cursor.getInt(0);
        }
        else {
            return 0;
        }
    }

    public int maximo(){
        SQLiteDatabase db = this.dbhelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT MAX(ID) FROM tbllibros", null);
        if (cursor.moveToFirst()) {
            return cursor.getInt(0);
        }
        else {
            return 0;
        }
    }

    public ArrayList<TrLibro> listar(){
        ArrayList<TrLibro> retorno = new ArrayList<TrLibro>();
        SQLiteDatabase db = this.dbhelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tbllibros", null);
        if (cursor.moveToFirst()) {
            do {
                TrLibro libro =
                    new TrLibro(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getInt(4)
                    );
                retorno.add(libro);
            } while (cursor.moveToNext());
        }
        return retorno;
    }

    public void agregar(TrLibro libro){
        SQLiteDatabase db = this.dbhelper.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", this.maximo() + 1);
        contentValues.put("NOMBRE", libro.getNombre());
        contentValues.put("EDITORIAL", libro.getEditorial());
        contentValues.put("AUTORES", libro.getAutores());
        contentValues.put("PAGINAS", libro.getPaginas());
        db.insert("tbllibros", null, contentValues);
        db.close();
    }

    public void actualizar(TrLibro libro){
        SQLiteDatabase db = this.dbhelper.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NOMBRE", libro.getNombre());
        contentValues.put("EDITORIAL", libro.getEditorial());
        contentValues.put("AUTORES", libro.getAutores());
        contentValues.put("PAGINAS", libro.getPaginas());
        db.update("tbllibros", contentValues,"ID=?", new String[] { String.valueOf(libro.getId()) });
        db.close();
    }

    public void eliminar(String id){
        SQLiteDatabase db = this.dbhelper.getReadableDatabase();
        db.delete("tbllibros","ID=?", new String[] { id });
        db.close();
    }

    public TrLibro buscarPorID(String id){
        TrLibro retorno;
        SQLiteDatabase db = this.dbhelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tbllibros WHERE ID=?", new String[] { id });
        if (cursor.moveToFirst()) {
            do {
                return
                        new TrLibro(
                                cursor.getInt(0),
                                cursor.getString(1),
                                cursor.getString(2),
                                cursor.getString(3),
                                cursor.getInt(4)
                        );
            } while (cursor.moveToNext());
        }
        return null;
    }
}
