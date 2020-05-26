package nc.apps.feedme.modelo;

public class Restaurante {
    private String id;
    private String nombre;
    private String descripcion;
    private double longitud;
    private double latitud;
    private int like;
    private String imagen;

    public Restaurante(String imagen, String nombre, String descripcion, double longitud, double latitud, int like) {
        this.id = imagen;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.longitud = longitud;
        this.latitud = latitud;
        this.like = like;
    }

    public Restaurante(String nombre, String descripcion, double longitud, double latitud, int like, String imagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.longitud = longitud;
        this.latitud = latitud;
        this.like = like;
        this.imagen = imagen;
    }

    public Restaurante(String id, String nombre, String descripcion, double longitud, double latitud, int like, String imagen) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.longitud = longitud;
        this.latitud = latitud;
        this.like = like;
        this.imagen = imagen;
    }

    public Restaurante() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
