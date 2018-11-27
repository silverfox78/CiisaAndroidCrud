package cl.ciisa.tranfer;

public class TrLibro {
    private int id;
    private String nombre;
    private String editorial;
    private String autores;
    private int paginas;

    public TrLibro(int id, String nombre, String editorial, String autores, int paginas) {
        this.id = id;
        this.nombre = nombre;
        this.editorial = editorial;
        this.autores = autores;
        this.paginas = paginas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getAutores() {
        return autores;
    }

    public void setAutores(String autores) {
        this.autores = autores;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }
}
