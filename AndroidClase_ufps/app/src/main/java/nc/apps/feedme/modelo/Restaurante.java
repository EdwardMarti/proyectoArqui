package nc.apps.feedme.modelo;

public class Restaurante {
    private String id;
    private String nombre;
    private String descripcion;
    private double longitud;
    private double latitud;
    private int like;
    private String img;

    public Restaurante(String img, String nombre, String descripcion, double longitud, double latitud, int like) {
        this.id = img;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.longitud = longitud;
        this.latitud = latitud;
        this.like = like;
    }

    public Restaurante(String nombre, String descripcion, double longitud, double latitud, int like, String img) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.longitud = longitud;
        this.latitud = latitud;
        this.like = like;
        this.img = img;
    }

    public Restaurante(String id, String nombre, String descripcion, double longitud, double latitud, int like, String img) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.longitud = longitud;
        this.latitud = latitud;
        this.like = like;
        this.img = img;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Restaurante{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", longitud=" + longitud +
                ", latitud=" + latitud +
                ", like=" + like +
                ", imagen='" + img + '\'' +
                '}';
    }
}
