package MODELO;

import java.time.LocalDate;

/**
 * Modelo Packs
 * 
 * @author jfa
 */
public class Packs
{

    private Integer idPack;
    private Integer idUsuario;
    private String nombre;
    private String descripcion;
    private LocalDate fechaPack;
    private Integer favorito;

    public Integer getIdPack()
    {
        return idPack;
    }

    public void setIdPack( Integer idPack )
    {
        this.idPack = idPack;
    }

    public Integer getIdUsuario()
    {
        return idUsuario;
    }

    public void setIdUsuario( Integer idUsuario )
    {
        this.idUsuario = idUsuario;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre( String nombre )
    {
        this.nombre = nombre;
    }

    public String getDescripcion()
    {
        return descripcion;
    }

    public void setDescripcion( String descripcion )
    {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaPack()
    {
        return fechaPack;
    }

    public void setFechaPack( LocalDate fechaPack )
    {
        this.fechaPack = fechaPack;
    }

    public Integer getFavorito()
    {
        return favorito;
    }

    public void setFavorito( Integer aFavorito )
    {
        favorito = aFavorito;
    }
}
