package Entidades;

/**
 *
 * @author Agustín Picos
 */
public class Vehiculo {
    private boolean tag;
    private String tipo;
    private boolean genera_accidente;
    private long horaEntrada;
    private long horaSalida;
    private String matricula;
    private String direccion;
    
    public Vehiculo(String linea){
        String[]datos = linea.split(",");
        this.matricula = datos[0];
        this.tipo = datos[1];
        this.tag = Boolean.valueOf(datos[2]);
        this.genera_accidente = Boolean.valueOf(datos[3]);
        this.direccion=datos[4];
    }
    public String pasar_a_String(){
        String dato="";
        dato+=String.valueOf(matricula)+",";
        dato+=tipo+",";
        dato+=String.valueOf(tag)+",";
        dato+=String.valueOf(genera_accidente)+",";
        dato+=String.valueOf(horaEntrada)+",";
        dato+=String.valueOf(horaSalida)+",";
        dato+=String.valueOf(horaSalida-horaEntrada+",");
        dato+=direccion;
        return dato;
    }

    public boolean isTag() {
        return tag;
    }

    public String getTipo() {
        return tipo;
    }

    public void setHoraEntrada(long horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public void setHoraSalida(long horaSalida) {
        this.horaSalida = horaSalida;
    }
    public String getMatricula(){
        return matricula;
    }

    public String getDireccion() {
        return direccion;
    }
    
    public boolean getGeneraAccidente(){
        return genera_accidente;
    }

}
